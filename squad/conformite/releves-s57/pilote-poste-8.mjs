// Pilote du poste 8 de s57 (mesure de la reprise d'ART-CP-10 sur le jar). Derive de pilote-q7.mjs (s56).
// Usage : node pilote-poste-8.mjs <chrome> <profil-isole-jetable> <url-de-l-application> <sortie.json> <css-candidat|-> <largeurs,csv> <port-de-debogage> [<repertoire-de-captures>]
// Variable ITEMS : nombre d'items dont la revelation est attendue avant d'arreter (6 par defaut, tous les items du paquet).
// Fenetre de 551 de haut. Chrome headless pilote par son protocole de debogage ; le CSS candidat, s'il est donne,
// est ajoute au <head> apres la feuille reelle a chaque chargement de page. Le jar doit servir le paquet
// (entree de catalogue) : la page de matiere est /niveaux/CP/matieres/arts-plastiques.
// Chrome sans tete se ferme avec la connexion de deverminage ; verifier qu'aucun processus ne reste et l'arreter par son PID, jamais par son nom d'image.
import { spawn } from 'node:child_process';
import fs from 'node:fs';
const [chrome, profile, base, outFile, cssFile, widths, cdp, shotDir] = process.argv.slice(2);
if (shotDir) fs.mkdirSync(shotDir,{recursive:true});
const css = cssFile==='-' ? '' : fs.readFileSync(cssFile,'utf8');
const port = +cdp;
const proc = spawn(chrome, ['--headless=new','--disable-gpu','--remote-debugging-port='+port,'--user-data-dir='+profile,'--no-first-run','about:blank'],{stdio:'ignore'});
console.error('CHROME_PID', proc.pid);
const sleep = ms => new Promise(r=>setTimeout(r,ms));
let targets;
for (let i=0;i<50;i++){ try{ targets = await (await fetch(`http://127.0.0.1:${port}/json`)).json(); if(targets.length) break;}catch{} await sleep(200);}
const page = targets.find(t=>t.type==='page');
const ws = new WebSocket(page.webSocketDebuggerUrl);
await new Promise(r=>ws.onopen=r);
let id=0; const pend=new Map();
ws.onmessage=e=>{const m=JSON.parse(e.data); if(m.id&&pend.has(m.id)){pend.get(m.id)(m);pend.delete(m.id);}};
const send=(method,params={})=>new Promise(r=>{const i=++id;pend.set(i,r);ws.send(JSON.stringify({id:i,method,params}));});
const ev = async expr => { const r = await send('Runtime.evaluate',{expression:expr,returnByValue:true,awaitPromise:true}); if(r.result.exceptionDetails) throw new Error(JSON.stringify(r.result.exceptionDetails)); return r.result.result.value; };
await send('Network.enable'); await send('Page.enable');
if (css) await send('Page.addScriptToEvaluateOnNewDocument',{source:`document.addEventListener('DOMContentLoaded',()=>{const s=document.createElement('style');s.id='candidat';s.textContent=${JSON.stringify(css)};document.head.appendChild(s);});`});
const MES = `(()=>{
 const R=e=>{if(!e)return null;const b=e.getBoundingClientRect();return {top:+(b.top+scrollY).toFixed(1),bottom:+(b.bottom+scrollY).toFixed(1),left:+b.left.toFixed(1),right:+b.right.toFixed(1),w:+b.width.toFixed(1),h:+b.height.toFixed(1)};};
 const T=s=>{const e=document.querySelector(s);return e?R(e):null;};
 const props=[...document.querySelectorAll('.propositions-oeuvres .proposition')];
 const cases=props.map(p=>({cls:p.className.replace('btn proposition','').trim(),rev:p.hasAttribute('data-oeuvre-revelee'),box:R(p),img:R(p.querySelector('img'))}));
 const marques=props.filter(p=>p.classList.contains('deja-essayee')).map(p=>{const cs=getComputedStyle(p,'::before');const b=R(p);const t=parseFloat(cs.top),h=parseFloat(cs.height),l=parseFloat(cs.left),ml=parseFloat(cs.marginLeft)||0;return {top:+(b.top+t).toFixed(1),bottom:+(b.top+t+h).toFixed(1),w:parseFloat(cs.width),h};});
 const ref=document.querySelector('.illustration img');
 return {refSrc:ref?ref.getAttribute('src'):null,innerW:innerWidth,innerH:innerHeight,docH:document.documentElement.scrollHeight,scrollW:document.documentElement.scrollWidth,clientW:document.documentElement.clientWidth,
  score:T('.ecran-de-jeu > .score'),consigne:T('.ecran-de-jeu > .consigne'),illustration:T('.illustration'),refImg:R(ref),enonce:T('.enonce'),
  cabinet:T('.cabinet'),bandeau:T('.cabinet-bandeau'),propositions:T('.propositions'),socle:T('.cabinet-socle'),levier:T('.cabinet-levier'),
  signal:T('.signal'),icone:T('.icone-signal'),verdict:T('.verdict'),messageJeu:T('.message-jeu'),m4:T('.reponse-revelee'),explication:T('.explication'),indice:T('.indice'),
  cases,marques,
  etat:{juste:!!document.querySelector('.signal.est-juste'),faux:!!document.querySelector('.signal.est-faux'),revele:!!document.querySelector('[data-oeuvre-revelee]')}};})()`;
async function shot(w,label,full){ if(!shotDir) return; const r=await send('Page.captureScreenshot',{format:'png',captureBeyondViewport:!!full}); fs.writeFileSync(`${shotDir}/${w}-${label}${full?'-full':''}.png`,Buffer.from(r.result.data,'base64')); }
let shotKey=null;
async function nav(url){ await send('Page.navigate',{url}); await sleep(1000); }
async function settle(){ await sleep(500); await ev(`Promise.all([...document.images].map(i=>i.complete?1:new Promise(r=>{i.onload=i.onerror=r})))`); }
const res = {};
for (const w of widths.split(',').map(Number)) {
  await send('Emulation.setDeviceMetricsOverride',{width:w,height:551,deviceScaleFactor:1,mobile:false});
  const items = {}; let essais=0; shotKey=null;
  while (essais<80 && Object.values(items).filter(x=>x.revelation).length<(+process.env.ITEMS||6)) {
    essais++;
    await send('Network.clearBrowserCookies'); await nav(base+'/niveaux/CP/matieres/arts-plastiques'); await sleep(300);
    const ok = await ev(`(()=>{const b=[...document.querySelectorAll('button[hx-post]')].find(b=>(b.getAttribute('hx-post')||'').includes('rapprocher-deux-oeuvres'));if(!b)return false;b.click();return true;})()`);
    if(!ok) throw new Error('bouton Jouer introuvable');
    await sleep(500); await settle();
    const fraiche = await ev(MES);
    const key = fraiche.refSrc; const it = items[key] ||= {};
    if (!it.fraiche) it.fraiche = fraiche; let step=0; if(shotKey===null) shotKey=key; const doShot=(key===shotKey); if(doShot&&!it.sh_fraiche){it.sh_fraiche=1; await shot(w,'fraiche');}
    while(step<6){
      const clicked = await ev(`(()=>{const b=[...document.querySelectorAll('.propositions-oeuvres .proposition')].find(p=>${step>=2}?p.classList.contains('deja-essayee'):!p.classList.contains('deja-essayee'));if(!b)return false;b.click();return true;})()`);
      if(!clicked) break;
      await sleep(450); await settle();
      const m = await ev(MES); step++; const lab=(m.etat.revele?'revelation':(m.etat.juste?'juste':(m.marques.length>1?'echec2':'echec1'))); if(doShot&&!it['sh_'+lab]){it['sh_'+lab]=1; await shot(w,lab); await shot(w,lab,true);}
      if(m.etat.juste && !m.etat.revele){ if(step===1) it.juste1=m; else it.juste_apres_echec=m; break; }
      if(m.etat.revele){ if(!it.revelation) it.revelation=m; break; }
      if(!it.echec1) it.echec1=m; else if(!it.echec2) it.echec2=m;
    }
  }
  console.error(w,'essais',essais,Object.keys(items).length,'items');
  res[w]=items;
}
fs.writeFileSync(outFile, JSON.stringify(res,null,1));
ws.close(); process.exit(0);
