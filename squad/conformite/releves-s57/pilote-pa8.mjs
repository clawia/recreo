// Pilote de la mesure PA8 / RDO-CP-R3 (s57, palier 2, ines-design), voie (i) de Q-R7 : largeur de la colonne du texte de M4.
// Joue bandit-manchot-rapprocher-deux-oeuvres-cp : pour chaque oeuvre de reference, deux erreurs puis la revelation. A chaque etat
// (echec1, echec2, revelation), mesure a chaque largeur, avec une barre de defilement classique ("barre") et sans ("sans"), et pour
// chaque variante de feuille, sur le MEME DOM : la variante est un <style> active ou desactive, la fenetre est redimensionnee sur place.
// Usage : node pilote-pa8.mjs <chrome> <profil-isole-jetable> <url> <sortie.json> <variantes.json|-> <largeurs,csv> <port-de-debogage>
// variantes.json : { "<nom>": "<css>" } ; la variante "reel" (feuille servie seule) est toujours mesuree.
// Variables : ITEMS (6 par defaut), PARTIES_MAX (80), CAPTURES=<repertoire> (une capture par variante a la revelation du premier item, 720 et 744 avec barre), JUSTES=1 (continue jusqu'a un etat juste par oeuvre), POLICES=1 (revelation seule, neuf polices imposees au <body>, 744 et 720).
// Cotes : getBoundingClientRect et Range.getClientRects, fenetre de 551 de haut. Les polices imposees ne remplacent pas un appareil reel.
// Sans barre : html { scrollbar-width: none } (Emulation.setScrollbarsHidden ne change pas la largeur utile en mode sans tete). Chrome est arrete par son PID (stderr), jamais par son nom d'image.
import { spawn } from 'node:child_process';
import fs from 'node:fs';
const [chrome, profile, base, outFile, variantesFile, widths, cdp] = process.argv.slice(2);
const VAR = variantesFile === '-' ? {} : JSON.parse(fs.readFileSync(variantesFile, 'utf8'));
const NOMS = ['reel', ...Object.keys(VAR)];
const W = widths.split(',').map(Number);
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
await send('Page.addScriptToEvaluateOnNewDocument', { source: `document.addEventListener('DOMContentLoaded',()=>{const V=${JSON.stringify(VAR)};const sb=document.createElement('style');sb.id='sans-barre';sb.textContent='html{scrollbar-width:none}';sb.disabled=true;document.head.appendChild(sb);for(const [n,c] of Object.entries(V)){const s=document.createElement('style');s.dataset.variante=n;s.textContent=c;s.disabled=true;document.head.appendChild(s);}});` });
const VARIANTE = n => ev(`(()=>{for(const s of document.querySelectorAll('style[data-variante]'))s.disabled=(s.dataset.variante!==${JSON.stringify(n)});})()`);
const MES = `(police=>{
 if(police!==undefined) document.body.style.fontFamily = police==='(police du poste)' ? '' : '"'+police+'"';
 const R=e=>{if(!e)return null;const b=e.getBoundingClientRect();return {top:+(b.top+scrollY).toFixed(1),bottom:+(b.bottom+scrollY).toFixed(1),left:+b.left.toFixed(1),right:+b.right.toFixed(1),w:+b.width.toFixed(1),h:+b.height.toFixed(1)};};
 const T=s=>{const e=document.querySelector(s);return e?R(e):null;};
 // Un mot du texte = une suite sans espace ordinaire ; les U+00A0 de l'ADR-0067 en font une phrase entiere.
 const phrases=el=>{ if(!el) return []; const out=[]; const w=document.createTreeWalker(el,NodeFilter.SHOW_TEXT); let n; while(n=w.nextNode()){ const re=/[^ ]+/g; let m; while(m=re.exec(n.data)){ const r=document.createRange(); r.setStart(n,m.index); r.setEnd(n,m.index+m[0].length); const rects=[...r.getClientRects()]; const b=rects[0]; out.push({t:m[0].replace(/\\u00a0/g,'_'),left:+b.left.toFixed(1),right:+Math.max(...rects.map(x=>x.right)).toFixed(1),w:+(Math.max(...rects.map(x=>x.right))-b.left).toFixed(1),top:Math.round(b.top+scrollY),morceaux:rects.length}); } } return out; };
 const m4=document.querySelector('.reponse-revelee'); const v=document.querySelector('.verdict'); const sg=document.querySelector('.signal');
 const ph4=phrases(m4), phv=phrases(v);
 const colonne=document.querySelector('.message-jeu > :not(.signal)');
 const cs=sg?getComputedStyle(sg):null;
 return {innerW:innerWidth,clientW:document.documentElement.clientWidth,scrollW:document.documentElement.scrollWidth,docH:document.documentElement.scrollHeight,
  cabinet:T('.cabinet'),messageJeu:T('.message-jeu'),signal:R(sg),verdict:R(v),colonne:R(colonne),m4:R(m4),indice:T('.indice'),
  signalPad:cs?{g:parseFloat(cs.paddingLeft),d:parseFloat(cs.paddingRight)}:null,
  m4Phrases:ph4,m4Lignes:[...new Set(ph4.map(x=>x.top))].length,m4DepasseBoite:m4&&ph4.length?+(Math.max(...ph4.map(x=>x.right))-m4.getBoundingClientRect().right).toFixed(1):null,m4DepasseEcran:ph4.length?+(Math.max(...ph4.map(x=>x.right))-document.documentElement.clientWidth).toFixed(1):null,
  verdictPhrases:phv,verdictPlusLarge:phv.length?Math.max(...phv.map(x=>x.w)):null,verdictResteDansLeSignal:phv.length&&sg?+(sg.getBoundingClientRect().right-Math.max(...phv.map(x=>x.right))).toFixed(1):null,verdictLignes:[...new Set(phv.map(x=>x.top))].length,
  m4Texte:m4?m4.textContent.replace(/\\u00a0/g,'_'):null,verdictTexte:v?v.textContent.replace(/\\u00a0/g,'_').replace(/\\s+/g,' ').trim():null,
  cases:[...document.querySelectorAll('.propositions-oeuvres .proposition')].map(R),refImg:R(document.querySelector('.illustration img')),score:T('.ecran-de-jeu > .score'),consigne:T('.ecran-de-jeu > .consigne'),
  etat:{juste:!!document.querySelector('.signal.est-juste'),faux:!!document.querySelector('.signal.est-faux'),revele:!!document.querySelector('[data-oeuvre-revelee]'),manche:!!document.querySelector('.propositions-oeuvres')},
  refSrc:(document.querySelector('.illustration img')||{getAttribute(){return null}}).getAttribute('src')};})`;
async function mesurer(police) {
  const out = {};
  for (const w of W) {
    out[w] = {};
    for (const mode of ['barre', 'sans']) {
      await ev(`document.getElementById('sans-barre').disabled=${mode !== 'sans'}`);
      await send('Emulation.setDeviceMetricsOverride', { width: w, height: 551, deviceScaleFactor: 1, mobile: false });
      out[w][mode] = {};
      for (const n of NOMS) { await VARIANTE(n === 'reel' ? null : n); out[w][mode][n] = await ev(`(${MES})(${police ? JSON.stringify(police) : 'undefined'})`); }
    }
  }
  await ev(`document.getElementById('sans-barre').disabled=true`);
  await send('Emulation.setDeviceMetricsOverride', { width: W[0], height: 551, deviceScaleFactor: 1, mobile: false });
  return out;
}
async function settle() { await sleep(450); await ev(`Promise.all([...document.images].map(i=>i.complete?1:new Promise(r=>{i.onload=i.onerror=r})))`); }
const cliquer = essayee => ev(`(()=>{const b=[...document.querySelectorAll('.propositions-oeuvres .proposition')].find(p=>${essayee}?p.classList.contains('deja-essayee'):!p.classList.contains('deja-essayee'));if(!b)return false;b.click();return true;})()`);
const ref = () => ev(`(document.querySelector('.illustration img')||{getAttribute(){return null}}).getAttribute('src')`);
const ITEMS = +(process.env.ITEMS || 6), PARTIES_MAX = +(process.env.PARTIES_MAX || 80);
const items = {}; let parties = 0;
await send('Emulation.setDeviceMetricsOverride', { width: W[0], height: 551, deviceScaleFactor: 1, mobile: false });
const fini = () => Object.values(items).filter(x => x.revelation).length >= ITEMS && (process.env.JUSTES !== '1' || Object.values(items).filter(x => x.juste1).length >= ITEMS);
while (parties < PARTIES_MAX && !fini()) {
  parties++;
  await send('Network.clearBrowserCookies');
  await send('Page.navigate', { url: base + '/niveaux/CP/matieres/arts-plastiques' }); await sleep(900);
  const ok = await ev(`(()=>{if(!window.htmx)return false;htmx.ajax('POST','/parties/bandit-manchot-rapprocher-deux-oeuvres-cp',{target:'#jeu',swap:'innerHTML'});return true;})()`);
  if (!ok) throw new Error('htmx absent');
  await sleep(700); await settle();
  const key = await ref(); if (!key) continue;
  const it = items[key] ||= {};
  if (it.revelation && !(process.env.JUSTES === '1' && !it.juste1)) continue;
  if (!await cliquer(false)) continue; await sleep(500); await settle();
  let m = await mesurer(); if (m[W[0]].barre.reel.etat.juste && !it.juste1) it.juste1 = m;
  if (!m[W[0]].barre.reel.etat.faux) continue;
  if (!it.echec1) it.echec1 = m;
  if (!await cliquer(false)) continue; await sleep(500); await settle();
  m = await mesurer(); if (m[W[0]].barre.reel.etat.juste && !it.juste_apres_echec) it.juste_apres_echec = m;
  if (!m[W[0]].barre.reel.etat.faux) continue;
  if (!it.echec2) it.echec2 = m;
  if (!await cliquer(true)) continue; await sleep(500); await settle();
  m = await mesurer(); if (!m[W[0]].barre.reel.etat.revele) continue;
  it.revelation = m;
  if (process.env.CAPTURES && !it.capture) {
    it.capture = 1; fs.mkdirSync(process.env.CAPTURES, { recursive: true });
    for (const w of [720, 744]) { await send('Emulation.setDeviceMetricsOverride', { width: w, height: 551, deviceScaleFactor: 1, mobile: false }); await ev(`document.getElementById('sans-barre').disabled=true`);
      for (const n of NOMS) { await VARIANTE(n === 'reel' ? null : n); const r = await send('Page.captureScreenshot', { format: 'png' }); fs.writeFileSync(`${process.env.CAPTURES}/${w}-barre-${n}.png`, Buffer.from(r.result.data, 'base64')); } }
    await send('Emulation.setDeviceMetricsOverride', { width: W[0], height: 551, deviceScaleFactor: 1, mobile: false });
  }
  if (process.env.POLICES === '1' && !it.polices) {
    it.polices = {};
    for (const p of ['(police du poste)', 'Arial', 'Georgia', 'Times New Roman', 'Verdana', 'Courier New', 'Comic Sans MS', 'Trebuchet MS', 'Tahoma']) it.polices[p] = await mesurer(p);
    await ev(`document.body.style.fontFamily=''`);
  }
  console.error('item', key, 'partie', parties, 'reveles', Object.values(items).filter(x => x.revelation).length);
}
fs.writeFileSync(outFile, JSON.stringify({ parties, variantes: NOMS, items }, null, 1));
ws.close(); process.exit(0);
