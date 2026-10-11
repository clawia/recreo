// Pilote du releve Q7 (s56). Usage : node pilote-q7.mjs <chrome> <profil-isole-jetable> <url-de-l-application> <sortie.json>
// Chrome headless, protocole de debogage, fenetre 551 de haut aux largeurs 744, 390 et 992 ; joue le paquet jusqu a la revelation sur chacun des six items.
import { spawn } from 'node:child_process';
import fs from 'node:fs';
const [chrome, profile, base, outFile] = process.argv.slice(2);
const port = 19222;
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
await send('Network.enable');
const MES = `(()=>{
 const R=e=>{if(!e)return null;const b=e.getBoundingClientRect();return {top:+(b.top+scrollY).toFixed(1),bottom:+(b.bottom+scrollY).toFixed(1),left:+b.left.toFixed(1),right:+b.right.toFixed(1),w:+b.width.toFixed(1),h:+b.height.toFixed(1)};};
 const T=s=>{const e=document.querySelector(s);return e?R(e):null;};
 const props=[...document.querySelectorAll('.propositions-oeuvres .proposition')];
 const cases=props.map(p=>({cls:p.className.replace('btn proposition','').trim(),rev:p.hasAttribute('data-oeuvre-revelee'),box:R(p),img:R(p.querySelector('img'))}));
 const marques=props.filter(p=>p.classList.contains('deja-essayee')).map(p=>{const cs=getComputedStyle(p,'::before');const b=R(p);const t=parseFloat(cs.top),h=parseFloat(cs.height),w=parseFloat(cs.width),l=parseFloat(cs.left),ml=parseFloat(cs.marginLeft)||0;return {content:cs.content,top:+(b.top+t).toFixed(1),bottom:+(b.top+t+h).toFixed(1),left:+(b.left+l+ml).toFixed(1),w,h};});
 return {refSrc:(document.querySelector('.illustration img')||document.createElement('i')).getAttribute('src'),innerW:innerWidth,innerH:innerHeight,docH:document.documentElement.scrollHeight,scrollW:document.documentElement.scrollWidth,
  reference:T('.illustration, .reference'),cabinet:T('.cabinet'),verdict:T('.verdict'),signalIcone:T('.icone-signal'),signal:T('.signal'),messageJeu:T('.message-jeu'),
  m4:T('.reponse-revelee'),m4Text:(document.querySelector('.reponse-revelee')||{}).textContent||null,explication:T('.explication'),indice:T('.indice'),enonce:T('.enonce'),
  cases,marques,
  etat:{juste:!!document.querySelector('.signal.est-juste'),faux:!!document.querySelector('.signal.est-faux'),revele:!!document.querySelector('[data-oeuvre-revelee]')}};})()`;
async function nav(url){ await send('Page.navigate',{url}); await sleep(1200); }
async function settle(){ await sleep(700); await ev(`Promise.all([...document.images].map(i=>i.complete?1:new Promise(r=>{i.onload=i.onerror=r})))`); }
const res = {};
for (const w of [744,390,992]) {
  await send('Emulation.setDeviceMetricsOverride',{width:w,height:551,deviceScaleFactor:1,mobile:false});
  const items = {}; let essais=0;
  while (essais<60 && Object.values(items).filter(x=>x.revelation).length<6) {
    essais++;
    await send('Network.clearBrowserCookies'); await nav(base+'/'); await sleep(500);
    const ok = await ev(`(()=>{const b=[...document.querySelectorAll('button.demarrer')].find(b=>(b.getAttribute('hx-post')||'').includes('rapprocher-deux-oeuvres'));if(!b)return false;b.click();return true;})()`);
    if(!ok) throw new Error('bouton Jouer introuvable');
    await sleep(700); await settle();
    const fraiche = await ev(MES);
    const key = fraiche.refSrc; const it = items[key] ||= {};
    it.fraiche = fraiche; let step=0;
    while(step<6){
      const clicked = await ev(`(()=>{const b=[...document.querySelectorAll('.propositions-oeuvres .proposition')].find(p=>${step>=2}?p.classList.contains('deja-essayee'):!p.classList.contains('deja-essayee'));if(!b)return false;b.click();return true;})()`);
      if(!clicked) break;
      await sleep(600); await settle();
      const m = await ev(MES); step++;
      if(m.etat.juste && !m.etat.revele){ if(step===1) it.juste1=m; else it.juste_apres_echec=m; break; }
      if(m.etat.revele){ it.revelation=m; break; }
      if(!it.echec1) it.echec1=m; else it.echec2=m;
    }
    console.error(w,'essai',essais,key,Object.keys(it).join(','));
  }
  res[w]=items;
}
fs.writeFileSync(outFile, JSON.stringify(res,null,1));
ws.close(); process.exit(0);
