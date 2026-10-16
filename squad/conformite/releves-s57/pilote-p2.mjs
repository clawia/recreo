// Pilote de la cote de reference et de la mesure appariee de la forme commune (s57, palier 2, ines-design). Derive de pilote-r3.mjs.
// Joue un jeu bandit-manchot a propositions en texte : pour chaque item du paquet, une erreur (echec1), la seconde erreur
// (echec2), la revelation, puis la bonne reponse. Cotes : getBoundingClientRect, fenetre de 551 de haut.
// Usage : node pilote-p2.mjs <chrome> <profil-isole-jetable> <url> <sortie-sans-extension> <feuille-de-base|-> <largeurs,csv> <port-de-debogage> <items.json> <jeu-id,csv> [<repertoire-de-captures>]
// <feuille-de-base> : le recreo.css servi par le JAR DE BASE (curl sur /css/recreo.css de ce jar). Le jeu est joue sur le jar du
// sommet ; a chaque etat, la mesure est prise deux fois sur le MEME DOM : feuille servie par le sommet (bras "cand"), puis cette
// feuille de la base a la place de recreo.css (bras "base" : le lien est desactive, la feuille de la base est inseree a sa place).
// Les gabarits des deux jars sont identiques (git diff aa9d703c 0c0d2142 -- adapters/adapter-web/src/main : recreo.css seul).
// Avec "-", une seule mesure, sur la feuille servie par le jar joue : sortie <sortie>.propre.json (cote d'un jar pris seul).
// items.json : { "<jeu-id>": [ {enonce, bonneReponse, distracteurs, indice, texteSupport?} ... ] }, extrait du YAML du paquet.
// Structure : { "<largeur>": { "<jeu>": { parties, items: { "<idx>": { arrivee, echec1, echec2, revelation, ... } }, anomalies, manquants } } }
// Variables : PARTIES_MAX (60 par defaut), SANS_REVELATION=1. Chrome est arrete par son PID (stderr), jamais par son nom d'image.
import { spawn } from 'node:child_process';
import fs from 'node:fs';
const [chrome, profile, base, outPrefix, cssFile, widths, cdp, itemsFile, jeux, shotDir] = process.argv.slice(2);
const niveauDe = jeu => /-cp$/.test(jeu) ? 'CP' : /-cm1$/.test(jeu) ? 'CM1' : 'CE1';
if (shotDir) fs.mkdirSync(shotDir, { recursive: true });
const css = cssFile === '-' ? '' : fs.readFileSync(cssFile, 'utf8');
const ITEMS = JSON.parse(fs.readFileSync(itemsFile, 'utf8'));
const W = widths.split(',').map(Number);
const port = +cdp;
const PARTIES_MAX = +(process.env.PARTIES_MAX || 60);
const SANS_REV = process.env.SANS_REVELATION === '1';
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
if (css) await send('Page.addScriptToEvaluateOnNewDocument', { source: `document.addEventListener('DOMContentLoaded',()=>{const l=document.querySelector('link[rel=stylesheet][href*="recreo.css"]');const s=document.createElement('style');s.id='feuille-de-base';s.textContent=${JSON.stringify(css)};s.disabled=true;l.after(s);});` });
const norm = s => (s || '').replace(/\s+/g, ' ').trim();
const MES = `(()=>{
 const R=e=>{if(!e)return null;const b=e.getBoundingClientRect();return {top:+(b.top+scrollY).toFixed(1),bottom:+(b.bottom+scrollY).toFixed(1),left:+b.left.toFixed(1),right:+b.right.toFixed(1),w:+b.width.toFixed(1),h:+b.height.toFixed(1)};};
 const T=s=>{const e=document.querySelector(s);return e?R(e):null;};
 const N=s=>(s||'').replace(/\\s+/g,' ').trim();
 const props=[...document.querySelectorAll('.propositions .proposition')];
 const ens=document.querySelector('.enonce');
 return {innerW:innerWidth,innerH:innerHeight,docH:document.documentElement.scrollHeight,scrollW:document.documentElement.scrollWidth,clientW:document.documentElement.clientWidth,
  enonceTexte:ens?N(ens.textContent):null,
  propositions:props.map(p=>({texte:N(p.textContent),essayee:p.classList.contains('deja-essayee'),box:R(p)})),
  score:T('.ecran-de-jeu > .score'),consigne:T('.ecran-de-jeu > .consigne'),enonce:T('.enonce'),
  cabinet:T('.cabinet'),bandeau:T('.cabinet-bandeau'),visage:T('.cabinet-visage'),propositionsBox:T('.propositions'),socle:T('.cabinet-socle'),levier:T('.cabinet-levier'),
  messageJeu:T('.message-jeu'),signal:T('.signal'),icone:T('.icone-signal'),verdict:T('.verdict'),verdictTexte:N((document.querySelector('.verdict')||{}).textContent),
  revelee:T('.reponse-revelee'),attendue:T('.reponse-attendue'),explication:T('.explication'),indice:T('.indice'),indiceTexte:N((document.querySelector('.indice')||{}).textContent),
  etat:{juste:!!document.querySelector('.signal.est-juste'),faux:!!document.querySelector('.signal.est-faux'),revele:!!document.querySelector('.reponse-revelee'),retour:!!document.querySelector('.sortie-pleine'),manche:!!document.querySelector('.cabinet')}};})()`;
// STYLE(true) : feuille servie par le jar joue (sommet) ; STYLE(false) : feuille de la base a la place.
// Toutes les permutations des propositions (le serveur les tire dans un ordre aleatoire, et le repli des rangees en depend) :
// les boutons sont reranges dans le DOM, mesures, puis remis dans leur ordre d'origine.
const ORDRES = `(()=>{
 const c=document.querySelector('.propositions'); if(!c) return [];
 const orig=[...c.children]; const out=[];
 const perms=a=>a.length<=1?[a]:a.flatMap((x,i)=>perms([...a.slice(0,i),...a.slice(i+1)]).map(p=>[x,...p]));
 const B=s=>{const e=document.querySelector(s);return e?+(e.getBoundingClientRect().bottom+scrollY).toFixed(1):null;};
 for (const p of perms(orig)) { p.forEach(e=>c.appendChild(e));
  const tops=new Set([...c.children].map(e=>Math.round(e.getBoundingClientRect().top+scrollY)));
  out.push({ordre:p.map(e=>orig.indexOf(e)).join(''),rangees:tops.size,cabinetBas:B('.cabinet'),signalBas:B('.signal'),verdictBas:B('.verdict'),indiceBas:B('.indice'),attendueBas:B('.reponse-attendue'),messageBas:B('.message-jeu'),docH:document.documentElement.scrollHeight}); }
 orig.forEach(e=>c.appendChild(e)); return out; })()`;
const STYLE = on => ev(`(()=>{const s=document.getElementById('feuille-de-base');const l=document.querySelector('link[rel=stylesheet][href*="recreo.css"]');if(s){s.disabled=${on ? 'true' : 'false'};l.disabled=${on ? 'false' : 'true'};}})()`);
// Mesure un etat a chaque largeur, sur la feuille de la base puis sur celle du sommet. Rend { "<largeur>": { base, cand } }.
async function mesurer(ordres = false) {
  const out = {};
  for (const w of W) {
    await send('Emulation.setDeviceMetricsOverride', { width: w, height: 551, deviceScaleFactor: 1, mobile: false });
    const o = {};
    if (css) { await STYLE(false); o.base = await ev(MES); if (ordres) o.base.ordres = await ev(ORDRES); await STYLE(true); o.cand = await ev(MES); if (ordres) o.cand.ordres = await ev(ORDRES); }
    else { o.base = await ev(MES); if (ordres) o.base.ordres = await ev(ORDRES); }
    out[w] = o;
  }
  await send('Emulation.setDeviceMetricsOverride', { width: W[0], height: 551, deviceScaleFactor: 1, mobile: false });
  return out;
}
async function settle() { await sleep(350); await ev(`Promise.all([...document.images].map(i=>i.complete?1:new Promise(r=>{i.onload=i.onerror=r})))`); }
async function attend(cond, ms = 4000) { const t0 = Date.now(); while (Date.now() - t0 < ms) { if (await ev(cond)) return true; await sleep(80); } return false; }
async function shot(jeu, idx, label) {
  if (!shotDir) return;
  for (const arm of css ? ['base', 'cand'] : ['base']) {
    if (css) await STYLE(arm === 'cand');
    const r = await send('Page.captureScreenshot', { format: 'png', captureBeyondViewport: false });
    fs.writeFileSync(`${shotDir}/${W[0]}-${jeu}-i${idx}-${label}-${arm}.png`, Buffer.from(r.result.data, 'base64'));
  }
  if (css) await STYLE(true);
}
const cliquer = (texte, essayee) => ev(`(()=>{const N=s=>(s||'').replace(/\\s+/g,' ').trim();const b=[...document.querySelectorAll('.propositions .proposition')].find(p=>${essayee ? `p.classList.contains('deja-essayee')` : `N(p.textContent)===${JSON.stringify(texte)}`});if(!b)return false;b.click();return true;})()`);
const retrouver = (jeu, m) => {
  const lot = ITEMS[jeu]; const ps = m.propositions.map(p => p.texte).sort().join('|');
  const c = [];
  lot.forEach((it, i) => { if (norm(it.enonce) === m.enonceTexte && [it.bonneReponse, ...it.distracteurs].map(norm).sort().join('|') === ps) c.push(i); });
  return c.length === 1 ? c[0] : (c.length ? c : null);
};
const resB = {}, resC = {};
for (const w of W) { resB[w] = {}; resC[w] = {}; }
for (const jeu of jeux.split(',')) {
  const lot = ITEMS[jeu]; const items = {}; const anomalies = []; let parties = 0;
  const fait = () => lot.every((_, i) => items[i] && items[i].echec1 && (SANS_REV || items[i].revelation));
  let nShots = 0;
  while (!fait() && parties < PARTIES_MAX) {
    parties++;
    await send('Network.clearBrowserCookies');
    await send('Emulation.setDeviceMetricsOverride', { width: W[0], height: 551, deviceScaleFactor: 1, mobile: false });
    await send('Page.navigate', { url: base + '/niveaux/' + niveauDe(jeu) + '/matieres/francais' }); await sleep(900);
    const ok = await ev(`(()=>{if(!window.htmx)return false;htmx.ajax('POST','/parties/${jeu}',{target:'#jeu',swap:'innerHTML'});return true;})()`);
    if (!ok) throw new Error('htmx absent');
    await attend(`!!document.querySelector('.cabinet')`); await settle();
    for (let manche = 0; manche < 12; manche++) {
      const a = await ev(MES);
      if (a.etat.retour || !a.etat.manche) break;
      const idx = retrouver(jeu, a);
      if (idx === null || Array.isArray(idx)) { anomalies.push({ manche, probleme: Array.isArray(idx) ? 'item ambigu' : 'item non retrouve', enonce: a.enonceTexte, props: a.propositions.map(p => p.texte) }); break; }
      const it = lot[idx]; const bonne = norm(it.bonneReponse);
      const rec = items[idx] ||= { idx, enonce: norm(it.enonce), bonneReponse: bonne, propositionsServies: a.propositions.map(p => p.texte) };
      const nouveau = !(rec.echec1 && (SANS_REV || rec.revelation));
      if (nouveau) {
        const sh = shotDir && nShots < 2; if (sh) nShots++;
        const fausses = a.propositions.map(p => p.texte).filter(t => t !== bonne);
        if (!rec.arrivee) rec.arrivee = await mesurer();
        if (!rec.echec1) {
          await cliquer(fausses[0], false); await attend(`!!document.querySelector('.signal.est-faux')`); await settle();
          const m1 = await mesurer(true); if (!m1[W[0]].base.etat.faux) anomalies.push({ idx, probleme: 'pas de signal faux apres echec1' }); else rec.echec1 = m1;
          if (sh) await shot(jeu, idx, 'echec1');
          if (fausses[1] !== undefined) {
            await cliquer(fausses[1], false); await attend(`!!document.querySelector('.signal.est-faux')`); await settle();
            const m2 = await mesurer(); if (m2[W[0]].base.etat.faux) rec.echec2 = m2;
          }
        } else {
          for (const f of fausses) { await cliquer(f, false); await sleep(450); }
        }
        if (!SANS_REV && !rec.revelation) {
          await cliquer(null, true); await attend(`!!document.querySelector('.reponse-revelee')`); await settle();
          const mr = await mesurer(true); if (!mr[W[0]].base.etat.revele) anomalies.push({ idx, probleme: 'pas de revelation' }); else rec.revelation = mr;
          if (sh) await shot(jeu, idx, 'revelation');
        }
      }
      const avant = a.enonceTexte + '|' + a.propositions.map(p => p.texte).join('|');
      await cliquer(bonne, false);
      await attend(`(()=>{const N=s=>(s||'').replace(/\\s+/g,' ').trim();const e=document.querySelector('.enonce');return !!document.querySelector('.sortie-pleine')||(e&&(N(e.textContent)+'|'+[...document.querySelectorAll('.propositions .proposition')].map(p=>N(p.textContent)).join('|'))!==${JSON.stringify(avant)})})()`, 4000);
      await settle();
    }
  }
  const manquants = lot.map((_, i) => i).filter(i => !(items[i] && items[i].echec1));
  console.error(jeu, 'parties', parties, 'items mesures', Object.keys(items).filter(i => items[i].echec1).length + '/' + lot.length, 'revelations', Object.values(items).filter(i => i.revelation).length, 'anomalies', anomalies.length, 'manquants', JSON.stringify(manquants));
  for (const w of W) {
    for (const [res, arm] of [[resB, 'base'], [resC, 'cand']]) {
      if (arm === 'cand' && !css) continue;
      const its = {};
      for (const [k, r] of Object.entries(items)) {
        const o = { idx: r.idx, enonce: r.enonce, bonneReponse: r.bonneReponse, propositionsServies: r.propositionsServies };
        for (const s of ['arrivee', 'echec1', 'echec2', 'revelation']) if (r[s]) o[s] = r[s][w][arm];
        its[k] = o;
      }
      res[w][jeu] = { parties, items: its, anomalies, manquants };
    }
  }
}
if (css) { fs.writeFileSync(outPrefix + '.base.json', JSON.stringify(resB, null, 1)); fs.writeFileSync(outPrefix + '.cand.json', JSON.stringify(resC, null, 1)); }
else fs.writeFileSync(outPrefix + '.propre.json', JSON.stringify(resB, null, 1));
ws.close(); process.exit(0);
