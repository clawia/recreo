// Bas de .indice, du signal et du dernier bloc du message apres une erreur, sous neuf polices imposees au <body>,
// sans puis avec le candidat R3 (mesure appariee sur le meme DOM), pour un item designe. Derive de polices-poste-8.mjs.
// Usage : node polices-r3.mjs <chrome> <profil-isole-jetable> <url> <css-candidat> <port-de-debogage> <largeur> <sortie.json> <jeu-id> <texte-d-une-proposition-de-l-item> <bonne-reponse>
// L'item est reconnu a la proposition donnee (comparaison exacte du texte normalise) ; l'erreur est la premiere proposition
// qui n'est pas la bonne reponse. Variable CAPTURES : repertoire des deux captures (sans, avec le candidat), police du poste. Les polices imposees ne remplacent pas un appareil reel.
import { spawn } from 'node:child_process';
import fs from 'node:fs';
const [chrome, profile, base, cssFile, cdp, largeur, out, jeu, repere, bonne] = process.argv.slice(2); const port = +cdp;
const css = fs.readFileSync(cssFile, 'utf8');
const proc = spawn(chrome, ['--headless=new', '--disable-gpu', '--remote-debugging-port=' + port, '--user-data-dir=' + profile, '--no-first-run', 'about:blank'], { stdio: 'ignore' });
console.error('CHROME_PID', proc.pid);
const sleep = ms => new Promise(r => setTimeout(r, ms));
let targets; for (let i = 0; i < 50; i++) { try { targets = await (await fetch(`http://127.0.0.1:${port}/json`)).json(); if (targets.length) break; } catch {} await sleep(200); }
const ws = new WebSocket(targets.find(t => t.type === 'page').webSocketDebuggerUrl); await new Promise(r => ws.onopen = r);
let id = 0; const pend = new Map(); ws.onmessage = e => { const m = JSON.parse(e.data); if (m.id && pend.has(m.id)) { pend.get(m.id)(m); pend.delete(m.id); } };
const send = (method, params = {}) => new Promise(r => { const i = ++id; pend.set(i, r); ws.send(JSON.stringify({ id: i, method, params })); });
const ev = async expr => { const r = await send('Runtime.evaluate', { expression: expr, returnByValue: true, awaitPromise: true }); if (r.result.exceptionDetails) throw new Error(JSON.stringify(r.result.exceptionDetails)); return r.result.result.value; };
await send('Network.enable'); await send('Page.enable');
await send('Page.addScriptToEvaluateOnNewDocument', { source: `document.addEventListener('DOMContentLoaded',()=>{const s=document.createElement('style');s.id='candidat';s.textContent=${JSON.stringify(css)};document.head.appendChild(s);});` });
await send('Emulation.setDeviceMetricsOverride', { width: +largeur, height: 551, deviceScaleFactor: 1, mobile: false });
const N = `s=>(s||'').replace(/\\s+/g,' ').trim()`;
const fonts = ['(police du poste)', 'Arial', 'Georgia', 'Times New Roman', 'Verdana', 'Courier New', 'Comic Sans MS', 'Trebuchet MS', 'Tahoma'];
const M = police => `(()=>{document.body.style.fontFamily=${JSON.stringify(police === '(police du poste)' ? '' : '"' + police + '"')};
 const B=s=>{const e=document.querySelector(s);return e?+(e.getBoundingClientRect().bottom+scrollY).toFixed(1):null;};
 const kids=[...document.querySelectorAll('.message-jeu > *')].map(e=>e.getBoundingClientRect().bottom+scrollY);
 const v=document.querySelector('.verdict'),sg=document.querySelector('.signal'); let debord=null; if(v&&sg){const r=document.createRange();r.selectNodeContents(v);debord=+(r.getBoundingClientRect().right-sg.getBoundingClientRect().right).toFixed(1);}
 return {indice:B('.indice'),signal:B('.signal'),verdict:B('.verdict'),debordVerdict:debord,dernier:kids.length?+Math.max(...kids).toFixed(1):null,lignesIndice:(()=>{const e=document.querySelector('.indice');return e?Math.round(e.getBoundingClientRect().height):null;})()};})()`;
const res = []; let ok = false;
for (let t = 0; t < 60 && !ok; t++) {
  await send('Network.clearBrowserCookies'); await send('Page.navigate', { url: base + '/niveaux/CE1/matieres/francais' }); await sleep(800);
  await ev(`htmx.ajax('POST','/parties/${jeu}',{target:'#jeu',swap:'innerHTML'})`); await sleep(900);
  for (let m = 0; m < 10 && !ok; m++) {
    const trouve = await ev(`(()=>{const n=${N};return [...document.querySelectorAll('.propositions .proposition')].some(p=>n(p.textContent)===${JSON.stringify(repere)})})()`);
    if (trouve) {
      await ev(`(()=>{const n=${N};const b=[...document.querySelectorAll('.propositions .proposition')].find(p=>n(p.textContent)!==${JSON.stringify(bonne)});b.click();})()`); await sleep(900);
      if (!await ev(`!!document.querySelector('.signal.est-faux')`)) throw new Error('pas de signal faux');
      for (const f of fonts) {
        await ev(`document.getElementById('candidat').disabled=true`); const b = await ev(M(f));
        if (f === fonts[0] && process.env.CAPTURES) { const r = await send('Page.captureScreenshot', { format: 'png' }); fs.mkdirSync(process.env.CAPTURES, { recursive: true }); fs.writeFileSync(`${process.env.CAPTURES}/${largeur}-${jeu}-base.png`, Buffer.from(r.result.data, 'base64')); }
        await ev(`document.getElementById('candidat').disabled=false`); const c = await ev(M(f));
        if (f === fonts[0] && process.env.CAPTURES) { const r = await send('Page.captureScreenshot', { format: 'png' }); fs.writeFileSync(`${process.env.CAPTURES}/${largeur}-${jeu}-cand.png`, Buffer.from(r.result.data, 'base64')); }
        res.push({ largeur: +largeur, jeu, repere, police: f, base: b, cand: c });
        console.log(f.padEnd(18), 'base indice', b.indice, 'signal', b.signal, '| cand indice', c.indice, 'signal', c.signal, 'dernier', c.dernier, 'h indice', c.lignesIndice, '| texte du verdict - bord droit du signal (px, >0 = deborde)', c.debordVerdict);
      }
      ok = true; break;
    }
    // pas l'item : appuyer sur la premiere proposition non barree jusqu'a une reponse juste (au plus trois appuis)
    for (let k = 0; k < 3; k++) {
      await ev(`(()=>{const b=[...document.querySelectorAll('.propositions .proposition')].find(p=>!p.classList.contains('deja-essayee'));if(b)b.click();})()`); await sleep(650);
      if (await ev(`!!document.querySelector('.signal.est-juste')||!!document.querySelector('.sortie-pleine')`)) break;
    }
    if (await ev(`!!document.querySelector('.sortie-pleine')`)) break;
  }
}
if (!ok) throw new Error('item non atteint');
fs.writeFileSync(out, JSON.stringify(res, null, 1));
process.exit(0);
