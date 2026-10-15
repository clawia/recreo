// Echantillon de non-regression de la forme candidate R3 (s57, ines-design). Mesure APPARIEE : chaque etat de jeu est
// mesure sur le meme DOM, sans et avec le candidat (le <style id="candidat"> est desactive puis reactive), a chaque largeur
// demandee (la fenetre est redimensionnee sur place, l'etat de la partie ne bouge pas).
// Usage : node pilote-r3-echantillon.mjs <chrome> <profil-isole-jetable> <url> <sortie.json> <css-candidat> <largeurs,csv> <port-de-debogage> <jeu-id,csv>
// Variable PARTIES (3 par defaut) : parties de dix manches par jeu. Politique aveugle, sans connaitre la bonne reponse :
// deux appuis sur la premiere proposition non barree (echec1, echec2), puis un appui sur une proposition barree (revelation),
// puis la reponse revelee ; si un appui est juste, la manche suivante arrive (etat "juste"). Le contenu n'est pas lu.
// La partie est lancee par htmx.ajax('POST', '/parties/<id>') depuis la page d'une matiere (meme requete que le bouton).
// Fenetre de 551 de haut. Chrome est arrete par son PID (stderr), jamais par son nom d'image.
import { spawn } from 'node:child_process';
import fs from 'node:fs';
const [chrome, profile, base, outFile, cssFile, widths, cdp, jeux] = process.argv.slice(2);
const css = fs.readFileSync(cssFile, 'utf8');
const W = widths.split(',').map(Number);
const PARTIES = +(process.env.PARTIES || 3);
const port = +cdp;
const proc = spawn(chrome, ['--headless=new', '--disable-gpu', '--remote-debugging-port=' + port, '--user-data-dir=' + profile, '--no-first-run', 'about:blank'], { stdio: 'ignore' });
console.error('CHROME_PID', proc.pid);
const sleep = ms => new Promise(r => setTimeout(r, ms));
let targets;
for (let i = 0; i < 50; i++) { try { targets = await (await fetch(`http://127.0.0.1:${port}/json`)).json(); if (targets.length) break; } catch {} await sleep(200); }
const ws = new WebSocket(targets.find(t => t.type === 'page').webSocketDebuggerUrl);
await new Promise(r => ws.onopen = r);
let id = 0; const pend = new Map();
ws.onmessage = e => { const m = JSON.parse(e.data); if (m.id && pend.has(m.id)) { pend.get(m.id)(m); pend.delete(m.id); } };
const send = (method, params = {}) => new Promise(r => { const i = ++id; pend.set(i, r); ws.send(JSON.stringify({ id: i, method, params })); });
const ev = async expr => { const r = await send('Runtime.evaluate', { expression: expr, returnByValue: true, awaitPromise: true }); if (r.result.exceptionDetails) throw new Error(JSON.stringify(r.result.exceptionDetails)); return r.result.result.value; };
await send('Network.enable'); await send('Page.enable');
await send('Page.addScriptToEvaluateOnNewDocument', { source: `document.addEventListener('DOMContentLoaded',()=>{const s=document.createElement('style');s.id='candidat';s.textContent=${JSON.stringify(css)};document.head.appendChild(s);});` });
const MES = `(()=>{
 const R=e=>{if(!e)return null;const b=e.getBoundingClientRect();return {top:+(b.top+scrollY).toFixed(1),bottom:+(b.bottom+scrollY).toFixed(1),h:+b.height.toFixed(1),left:+b.left.toFixed(1),right:+b.right.toFixed(1)};};
 const T=s=>R(document.querySelector(s));
 const kids=[...document.querySelectorAll('.message-jeu > *')].map(e=>({c:e.className,b:R(e)}));
 const props=[...document.querySelectorAll('.propositions .proposition')].map(R);
 return {docH:document.documentElement.scrollHeight,scrollW:document.documentElement.scrollWidth,clientW:document.documentElement.clientWidth,
  cabinet:T('.cabinet'),propBas:props.length?Math.max(...props.map(p=>p.bottom)):null,propSig:props.map(p=>p.top+'/'+p.left+'/'+p.h).join(),
  score:T('.ecran-de-jeu > .score'),enonce:T('.enonce'),messageJeu:T('.message-jeu'),signal:T('.signal'),verdict:T('.verdict'),indice:T('.indice'),
  revelee:T('.reponse-revelee'),attendue:T('.reponse-attendue'),explication:T('.explication'),
  msgKids:kids,msgBas:kids.length?Math.max(...kids.map(k=>k.b.bottom)):null,
  nProps:props.length,deborde:[...document.querySelectorAll('.message-jeu *, .propositions *, .enonce, .consigne')].some(e=>e.getBoundingClientRect().right>document.documentElement.clientWidth+0.5)};})()`;
// STYLE(true) : candidat actif ; STYLE(false) : feuille reelle seule.
const STYLE = on => ev(`document.getElementById('candidat').disabled=${on ? 'false' : 'true'}`);
async function etat(label, rec) {
  const lignes = {};
  for (const w of W) {
    await send('Emulation.setDeviceMetricsOverride', { width: w, height: 551, deviceScaleFactor: 1, mobile: false });
    await STYLE(true); const c = await ev(MES);
    await STYLE(false); const b = await ev(MES);
    await STYLE(true);
    lignes[w] = { base: b, cand: c };
  }
  await send('Emulation.setDeviceMetricsOverride', { width: W[0], height: 551, deviceScaleFactor: 1, mobile: false });
  rec.push({ etat: label, enonce: await ev(`(document.querySelector('.enonce')||{}).textContent`), nProps: lignes[W[0]].base.nProps, lignes });
}
const settle = async () => { await sleep(300); await ev(`Promise.all([...document.images].map(i=>i.complete?1:new Promise(r=>{i.onload=i.onerror=r})))`); };
const attend = async (cond, ms = 4000) => { const t0 = Date.now(); while (Date.now() - t0 < ms) { if (await ev(cond)) return true; await sleep(80); } return false; };
const cliqueNonBarree = () => ev(`(()=>{const b=[...document.querySelectorAll('.propositions .proposition')].find(p=>!p.classList.contains('deja-essayee'));if(!b)return false;b.click();return true;})()`);
const cliqueBarree = () => ev(`(()=>{const b=[...document.querySelectorAll('.propositions .proposition')].find(p=>p.classList.contains('deja-essayee'));if(!b)return false;b.click();return true;})()`);
const cliqueRevelee = () => ev(`(()=>{const b=document.querySelector('.propositions .proposition[data-focus-cible=true]')||[...document.querySelectorAll('.propositions .proposition')].find(p=>!p.classList.contains('deja-essayee'));if(!b)return false;b.click();return true;})()`);
const sig = () => ev(`(()=>{const e=document.querySelector('.enonce');return (document.querySelector('.sortie-pleine')?'retour':'')+'|'+(e?e.textContent:'')+'|'+[...document.querySelectorAll('.propositions .proposition')].map(p=>p.textContent).join('/')+'|'+(document.querySelector('.signal.est-faux')?'F':'')+(document.querySelector('.reponse-revelee')?'R':'')+[...document.querySelectorAll('.deja-essayee')].length})()`);
const res = {};
for (const jeu of jeux.split(',')) {
  const rec = []; const anomalies = [];
  for (let p = 0; p < PARTIES; p++) {
    await send('Network.clearBrowserCookies');
    await send('Page.navigate', { url: base + '/niveaux/CE1/matieres/francais' }); await sleep(800);
    await ev(`htmx.ajax('POST','/parties/${jeu}',{target:'#jeu',swap:'innerHTML'})`);
    if (!await attend(`!!document.querySelector('.cabinet')`)) { anomalies.push({ p, probleme: 'pas de manche' }); continue; }
    await settle();
    for (let manche = 0; manche < 11; manche++) {
      if (await ev(`!!document.querySelector('.sortie-pleine')||!document.querySelector('.cabinet')`)) break;
      if (manche === 0 && p === 0) await etat('fraiche', rec);
      let step = 0;
      while (step < 6) {
        const avant = await sig();
        const ok = step < 2 ? await cliqueNonBarree() : await cliqueBarree();
        if (!ok) { anomalies.push({ p, manche, step, probleme: 'rien a cliquer' }); break; }
        const t0 = Date.now(); while (Date.now() - t0 < 3000 && (await sig()) === avant) await sleep(80);
        await settle();
        const etat0 = await ev(`({faux:!!document.querySelector('.signal.est-faux'),juste:!!document.querySelector('.signal.est-juste'),revele:!!document.querySelector('.reponse-revelee'),retour:!!document.querySelector('.sortie-pleine')})`);
        if (etat0.retour) break;
        if (etat0.faux && !etat0.revele) { await etat('echec' + (step + 1), rec); step++; continue; }
        if (etat0.revele) { await etat('revelation', rec); await cliqueRevelee(); await sleep(400); await settle(); break; }
        if (etat0.juste) { await etat('juste', rec); break; }
        anomalies.push({ p, manche, step, probleme: 'etat inconnu' }); break;
      }
    }
  }
  console.error(jeu, 'etats', rec.length, 'anomalies', anomalies.length);
  res[jeu] = { etats: rec, anomalies };
}
fs.writeFileSync(outFile, JSON.stringify(res));
ws.close(); process.exit(0);
