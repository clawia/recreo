// Pilote de mesure de proposition-ines-accueil-vues.md.
// Usage : node pilote-accueil.mjs <chrome> <profil-isole-jetable> <dossier-des-maquettes> <url-de-l-application> <sortie.json>
// Chrome headless pilote par le protocole de debogage ; la fenetre est fixee par Emulation.setDeviceMetricsOverride
// a 744, 390 et 992 de large sur 551 de haut (mobile: false, echelle 1) : le plancher de 516 px d'ADR-0025 ne joue pas.
// Mesure chaque maquette (file://) et, si <url-de-l-application> n'est pas "-", les pages servies par le jar.
import { spawn, spawnSync } from 'node:child_process';
import fs from 'node:fs';
import path from 'node:path';
import { pathToFileURL } from 'node:url';

const [chrome, profile, dossier, base, outFile] = process.argv.slice(2);
const PLI = 551;
const LARGEURS = [744, 390, 992];
const port = 19333;
const proc = spawn(chrome, ['--headless=new', '--disable-gpu', '--remote-debugging-port=' + port,
  '--user-data-dir=' + profile, '--no-first-run', 'about:blank'], { stdio: 'ignore' });
console.error('CHROME_PID', proc.pid);
const sleep = ms => new Promise(r => setTimeout(r, ms));
let targets;
for (let i = 0; i < 60; i++) {
  try { targets = await (await fetch(`http://127.0.0.1:${port}/json`)).json(); if (targets.length) break; } catch {}
  await sleep(200);
}
const page = targets.find(t => t.type === 'page');
const ws = new WebSocket(page.webSocketDebuggerUrl);
await new Promise(r => ws.onopen = r);
let id = 0; const pend = new Map();
ws.onmessage = e => { const m = JSON.parse(e.data); if (m.id && pend.has(m.id)) { pend.get(m.id)(m); pend.delete(m.id); } };
const send = (method, params = {}) => new Promise(r => { const i = ++id; pend.set(i, r); ws.send(JSON.stringify({ id: i, method, params })); });
const ev = async expr => {
  const r = await send('Runtime.evaluate', { expression: expr, returnByValue: true, awaitPromise: true });
  if (r.result.exceptionDetails) throw new Error(JSON.stringify(r.result.exceptionDetails));
  return r.result.result.value;
};

const MES = `(()=>{
 const PLI=${PLI};
 const R=e=>{const b=e.getBoundingClientRect();return {t:+(b.top+scrollY).toFixed(1),b:+(b.bottom+scrollY).toFixed(1),l:+b.left.toFixed(1),r:+b.right.toFixed(1),w:+b.width.toFixed(1),h:+b.height.toFixed(1)};};
 const rgb=s=>{const m=s.match(/rgba?\\(([^)]+)\\)/);if(!m)return null;const p=m[1].split(',').map(Number);return {r:p[0],g:p[1],b:p[2],a:p.length>3?p[3]:1};};
 const lum=c=>{const f=v=>{v/=255;return v<=0.03928?v/12.92:Math.pow((v+0.055)/1.055,2.4);};return 0.2126*f(c.r)+0.7152*f(c.g)+0.0722*f(c.b);};
 const ratio=(a,b)=>{let x=lum(a),y=lum(b);if(x<y)[x,y]=[y,x];return +((x+0.05)/(y+0.05)).toFixed(2);};
 const col=(sel)=>{const e=document.querySelector(sel);if(!e)return null;const cs=getComputedStyle(e);const page=rgb(getComputedStyle(document.body).backgroundColor);
   const fond=rgb(cs.backgroundColor), texte=rgb(cs.color), bord=rgb(cs.borderTopColor), bordG=rgb(cs.borderLeftColor);
   const fondEff=(fond&&fond.a>0)?fond:page;
   return {fond:cs.backgroundColor,texte:cs.color,bord:cs.borderTopColor,bordGauche:cs.borderLeftColor,
     texteSurFond:texte?ratio(texte,fondEff):null, bordSurPage:bord?ratio(bord,page):null, bordGaucheSurPage:bordG?ratio(bordG,page):null,
     taillePolice:cs.fontSize, rayon:cs.borderTopLeftRadius};};
 const cartes=[...document.querySelectorAll('button.demarrer')].map(e=>({...R(e),txt:e.textContent.trim()}));
 const tuiles=[...document.querySelectorAll('a.tuile')].map(e=>({...R(e),txt:e.textContent.trim()}));
 const bandeaux=[...document.querySelectorAll('.entete-matiere')].map(e=>({...R(e),txt:e.textContent.trim()}));
 const press=[...document.querySelectorAll('button, a[href]')].map(e=>{const c=e.className||'';const g=/demarrer/.test(c)?'carte':/tuile/.test(c)?'tuile':/bascule/.test(c)?'bascule':/chip-niveau/.test(c)?'puce':/lien-accueil/.test(c)?'sortie':'autre';return {g,...R(e)};});
 const parGroupe={};press.forEach(p=>{const x=parGroupe[p.g]||(parGroupe[p.g]={n:0,minW:1e9,minH:1e9,maxW:0,maxH:0});x.n++;x.minW=Math.min(x.minW,p.w);x.minH=Math.min(x.minH,p.h);x.maxW=Math.max(x.maxW,p.w);x.maxH=Math.max(x.maxH,p.h);});
 let ecartMin=1e9,chevauche=0;
 for(let i=0;i<press.length;i++)for(let j=i+1;j<press.length;j++){const a=press[i],b=press[j];
   const dx=Math.max(a.l-b.r,b.l-a.r,0),dy=Math.max(a.t-b.b,b.t-a.b,0);
   if(dx===0&&dy===0&&a.l<b.r&&b.l<a.r&&a.t<b.b&&b.t<a.b)chevauche++;
   else{const d=Math.hypot(dx,dy);if(d<ecartMin)ecartMin=d;}}
 const coupes=[...document.querySelectorAll('button.demarrer, a.tuile')].filter(e=>e.scrollWidth>e.clientWidth+1||e.scrollHeight>e.clientHeight+1).length;
 const intrusions=[...document.querySelectorAll('button.demarrer, a.tuile')].filter(e=>{const cs=getComputedStyle(e);const dispo=e.clientWidth-parseFloat(cs.paddingLeft)-parseFloat(cs.paddingRight);
   const lignes=[...e.querySelectorAll('span')].concat(e.querySelector('span')?[]:[e]);return lignes.some(n=>{const r=document.createRange();r.selectNodeContents(n);return Math.max(...[...r.getClientRects()].map(x=>x.width))>dispo+0.5;});}).length;
 const complet=a=>a.filter(x=>x.b<=PLI).length, partiel=a=>a.filter(x=>x.t<PLI).length;
 const entete=document.querySelector('.entete'), barre=document.querySelector('.barre-vue'), filtre=document.querySelector('.filtre-niveau');
 const hauteurs={};cartes.forEach(c=>{hauteurs[c.h]=(hauteurs[c.h]||0)+1;});
 const out={innerW:innerWidth,innerH:innerHeight,docH:document.documentElement.scrollHeight,scrollW:document.documentElement.scrollWidth,clientW:document.documentElement.clientWidth,
  entete:entete?R(entete):null,barre:barre?R(barre):null,filtre:filtre?R(filtre):null,
  nCartes:cartes.length,cartesEntieres:complet(cartes),cartesAuMoinsEnPartie:partiel(cartes),
  nTuiles:tuiles.length,tuilesEntieres:complet(tuiles),tuilesAuMoinsEnPartie:partiel(tuiles),
  nBandeaux:bandeaux.length,bandeauxVisibles:bandeaux.filter(x=>x.t<PLI).map(x=>x.txt),bandeauHaut:bandeaux.length?bandeaux[0].h:null,
  premiereCarteHaut:cartes.length?cartes[0].t:null,derniereCarteBas:cartes.length?cartes[cartes.length-1].b:null,
  hauteursCartes:hauteurs,parGroupe,ecartMinEntreCibles:+ecartMin.toFixed(1),cibleschevauchees:chevauche,coupes,intrusions,
  couleurs:{carte:col('button.demarrer'),tuile:col('a.tuile'),bandeau:col('.entete-matiere'),bascule:col('.bascule'),filtre:col('.filtre-niveau'),puceActive:col('.chip-niveau.est-actif'),sortie:col('.lien-accueil')}};
 return out;})()`;

const COLLE = `(async()=>{const b=[...document.querySelectorAll('.entete-matiere')];if(b.length<2)return null;
 const y=b[1].getBoundingClientRect().top+scrollY+40;scrollTo({top:y,left:0,behavior:'instant'});
 await new Promise(r=>requestAnimationFrame(()=>requestAnimationFrame(r)));
 const hauts=b.map(e=>+e.getBoundingClientRect().top.toFixed(1));scrollTo({top:0,left:0,behavior:'instant'});
 return {scrollA:+y.toFixed(1),hauts,colles:hauts.filter(h=>Math.abs(h)<1).length};})()`;

async function nav(url) {
  await send('Page.navigate', { url });
  for (let i = 0; i < 100; i++) {
    await sleep(100);
    try { if (await ev(`document.readyState==='complete'`)) break; } catch {}
  }
  await ev(`Promise.all([...document.images].map(i=>i.complete?1:new Promise(r=>{i.onload=i.onerror=r})))`);
  await sleep(150);
}

const pages = [];
const fichiers = fs.readdirSync(dossier).filter(f => /^maquette-(actuelle|v1-etage1|v1-etage2|v2)-/.test(f) && !/-(gris|neutre)\.html$/.test(f)).sort();
for (const f of fichiers) pages.push({ cle: 'maquette:' + f.replace(/^maquette-|\.html$/g, ''), url: pathToFileURL(path.join(dossier, f)).href });
if (base !== '-') {
  pages.push({ cle: 'jar:/', url: base + '/' });
  for (const n of ['CP', 'CE1', 'CM1']) pages.push({ cle: 'jar:/niveaux/' + n, url: base + '/niveaux/' + n });
}
const res = {};
for (const w of LARGEURS) {
  await send('Emulation.setDeviceMetricsOverride', { width: w, height: PLI, deviceScaleFactor: 1, mobile: false });
  for (const p of pages) {
    await nav(p.url);
    const m = await ev(MES);
    if (/v2-|etage2-/.test(p.cle)) m.colle = await ev(COLLE);
    (res[p.cle] ||= {})[w] = m;
  }
  console.error('largeur', w, 'faite', pages.length, 'pages');
}
fs.writeFileSync(outFile, JSON.stringify({ pli: PLI, largeurs: LARGEURS, pages: res }, null, 1));
ws.close();
if (process.platform === 'win32') spawnSync('taskkill', ['/PID', String(proc.pid), '/T', '/F'], { stdio: 'ignore' }); else proc.kill();
process.exit(0);
