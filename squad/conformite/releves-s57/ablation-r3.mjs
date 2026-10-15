// Gain de chaque regle du candidat R3, sur des items designes, apres une erreur : feuille reelle seule, chaque regle seule,
// le candidat entier, le candidat sans chaque regle. Mesure appariee sur le meme DOM (le texte du <style id="candidat"> est remplace).
// Usage : node ablation-r3.mjs <chrome> <profil-isole-jetable> <url> <css-candidat> <port-de-debogage> <largeur> <sortie.json> <jeu|proposition-de-l-item|bonne-reponse> [...]
// Le candidat est decoupe aux commentaires "R3-<n> :" ; le bloc @media (min-width: 720px) est reconstruit autour de chaque morceau.
import { spawn } from 'node:child_process';
import fs from 'node:fs';
const [chrome, profile, base, cssFile, cdp, largeur, out, ...cibles] = process.argv.slice(2); const port = +cdp;
const css = fs.readFileSync(cssFile, 'utf8');
const debut = css.indexOf('@media (min-width: 720px) {'); const fin = css.lastIndexOf('}');
const inner = css.slice(debut + '@media (min-width: 720px) {'.length, fin);
const pieces = inner.split(/(?=\/\* R3-\d)/).filter(p => /R3-\d/.test(p)).map(p => ({ id: /R3-(\d)/.exec(p)[1], css: p }));
const media = ps => `@media (min-width: 720px) {\n${ps.map(p => p.css).join('\n')}\n}`;
const variantes = [{ nom: 'feuille reelle seule', css: '' }, { nom: 'candidat entier', css: media(pieces) }];
for (const p of pieces) variantes.push({ nom: `R3-${p.id} seule`, css: media([p]) });
for (const p of pieces) variantes.push({ nom: `candidat sans R3-${p.id}`, css: media(pieces.filter(q => q !== p)) });
const proc = spawn(chrome, ['--headless=new', '--disable-gpu', '--remote-debugging-port=' + port, '--user-data-dir=' + profile, '--no-first-run', 'about:blank'], { stdio: 'ignore' });
console.error('CHROME_PID', proc.pid);
const sleep = ms => new Promise(r => setTimeout(r, ms));
let targets; for (let i = 0; i < 50; i++) { try { targets = await (await fetch(`http://127.0.0.1:${port}/json`)).json(); if (targets.length) break; } catch {} await sleep(200); }
const ws = new WebSocket(targets.find(t => t.type === 'page').webSocketDebuggerUrl); await new Promise(r => ws.onopen = r);
let id = 0; const pend = new Map(); ws.onmessage = e => { const m = JSON.parse(e.data); if (m.id && pend.has(m.id)) { pend.get(m.id)(m); pend.delete(m.id); } };
const send = (method, params = {}) => new Promise(r => { const i = ++id; pend.set(i, r); ws.send(JSON.stringify({ id: i, method, params })); });
const ev = async expr => { const r = await send('Runtime.evaluate', { expression: expr, returnByValue: true, awaitPromise: true }); if (r.result.exceptionDetails) throw new Error(JSON.stringify(r.result.exceptionDetails)); return r.result.result.value; };
await send('Network.enable'); await send('Page.enable');
await send('Page.addScriptToEvaluateOnNewDocument', { source: `document.addEventListener('DOMContentLoaded',()=>{const s=document.createElement('style');s.id='candidat';document.head.appendChild(s);});` });
await send('Emulation.setDeviceMetricsOverride', { width: +largeur, height: 551, deviceScaleFactor: 1, mobile: false });
const N = `s=>(s||'').replace(/\\s+/g,' ').trim()`;
const MES = `(()=>{const B=s=>{const e=document.querySelector(s);return e?+(e.getBoundingClientRect().bottom+scrollY).toFixed(1):null;};const T=s=>{const e=document.querySelector(s);return e?+(e.getBoundingClientRect().top+scrollY).toFixed(1):null;};
 return {cabinetHaut:T('.cabinet'),cabinetBas:B('.cabinet'),signalBas:B('.signal'),verdictBas:B('.verdict'),indiceBas:B('.indice')};})()`;
const res = [];
for (const cible of cibles) {
  const [jeu, repere, bonne] = cible.split('|'); let ok = false;
  for (let t = 0; t < 80 && !ok; t++) {
    await send('Network.clearBrowserCookies'); await send('Page.navigate', { url: base + '/niveaux/CE1/matieres/francais' }); await sleep(800);
    await ev(`htmx.ajax('POST','/parties/${jeu}',{target:'#jeu',swap:'innerHTML'})`); await sleep(900);
    for (let m = 0; m < 10 && !ok; m++) {
      const trouve = await ev(`(()=>{const n=${N};return [...document.querySelectorAll('.propositions .proposition')].some(p=>n(p.textContent)===${JSON.stringify(repere)})})()`);
      if (trouve) {
        await ev(`(()=>{const n=${N};const b=[...document.querySelectorAll('.propositions .proposition')].find(p=>n(p.textContent)!==${JSON.stringify(bonne)});b.click();})()`); await sleep(900);
        if (!await ev(`!!document.querySelector('.signal.est-faux')`)) throw new Error('pas de signal faux');
        for (const v of variantes) {
          await ev(`document.getElementById('candidat').textContent=${JSON.stringify(v.css)}`);
          const r = await ev(MES); res.push({ largeur: +largeur, jeu, repere, variante: v.nom, ...r });
          console.log(jeu.replace('bandit-manchot-', '').padEnd(26), repere.slice(0, 24).padEnd(25), v.nom.padEnd(26), 'cabinet haut', r.cabinetHaut, 'signal bas', r.signalBas, 'indice bas', r.indiceBas);
        }
        ok = true; break;
      }
      for (let k = 0; k < 3; k++) {
        await ev(`(()=>{const b=[...document.querySelectorAll('.propositions .proposition')].find(p=>!p.classList.contains('deja-essayee'));if(b)b.click();})()`); await sleep(650);
        if (await ev(`!!document.querySelector('.signal.est-juste')||!!document.querySelector('.sortie-pleine')`)) break;
      }
      if (await ev(`!!document.querySelector('.sortie-pleine')`)) break;
    }
  }
  if (!ok) throw new Error('item non atteint : ' + cible);
}
fs.writeFileSync(out, JSON.stringify(res, null, 1));
process.exit(0);
