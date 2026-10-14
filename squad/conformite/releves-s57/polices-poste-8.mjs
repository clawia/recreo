// Bas de l'indice (echec) et de la phrase de M4 (revelation) sous neuf polices imposees par le style de <body>.
// Usage : node polices-poste-8.mjs <chrome> <profil-isole-jetable> <url> <css-candidat> <port-de-debogage> <largeurs,csv> <sortie.json>
// Les polices imposees ne remplacent pas un appareil reel.
import { spawn } from 'node:child_process';
import fs from 'node:fs';
const [chrome, profile, base, cssFile, cdp, widths, out] = process.argv.slice(2); const port=+cdp;
const css = fs.readFileSync(cssFile,'utf8');
const proc = spawn(chrome, ['--headless=new','--disable-gpu','--remote-debugging-port='+port,'--user-data-dir='+profile,'--no-first-run','about:blank'],{stdio:'ignore'});
const sleep = ms => new Promise(r=>setTimeout(r,ms));
let targets; for (let i=0;i<50;i++){ try{ targets = await (await fetch(`http://127.0.0.1:${port}/json`)).json(); if(targets.length) break;}catch{} await sleep(200);}
const ws = new WebSocket(targets.find(t=>t.type==='page').webSocketDebuggerUrl); await new Promise(r=>ws.onopen=r);
let id=0; const pend=new Map(); ws.onmessage=e=>{const m=JSON.parse(e.data); if(m.id&&pend.has(m.id)){pend.get(m.id)(m);pend.delete(m.id);}};
const send=(method,params={})=>new Promise(r=>{const i=++id;pend.set(i,r);ws.send(JSON.stringify({id:i,method,params}));});
const ev = async expr => { const r=await send('Runtime.evaluate',{expression:expr,returnByValue:true,awaitPromise:true}); if(r.result.exceptionDetails) throw new Error(JSON.stringify(r.result.exceptionDetails)); return r.result.result.value; };
await send('Page.enable');
await send('Page.addScriptToEvaluateOnNewDocument',{source:`document.addEventListener('DOMContentLoaded',()=>{const s=document.createElement('style');s.textContent=${JSON.stringify(css)};document.head.appendChild(s);});`});
const fonts=['(police du poste)','Arial','Georgia','Times New Roman','Verdana','Courier New','Comic Sans MS','Trebuchet MS','Tahoma'];
const M=(police,sel)=>`(()=>{document.body.style.fontFamily=${JSON.stringify('')}; if(${JSON.stringify(police)}!=='(police du poste)') document.body.style.fontFamily='"'+${JSON.stringify(police)}+'"'; const e=document.querySelector(${JSON.stringify(sel)}); const b=e.getBoundingClientRect(); return {bas:+(b.bottom+scrollY).toFixed(1),h:+b.height.toFixed(1)};})()`;
const res=[];
for (const W of widths.split(',').map(Number)) {
  await send('Emulation.setDeviceMetricsOverride',{width:W,height:551,deviceScaleFactor:1,mobile:false});
  let ok=false;
  for (let t=0;t<30&&!ok;t++){
    await send('Network.clearBrowserCookies'); await send('Page.navigate',{url:base+'/niveaux/CP/matieres/arts-plastiques'}); await sleep(900);
    await ev(`[...document.querySelectorAll('button[hx-post]')].find(b=>(b.getAttribute('hx-post')||'').includes('rapprocher-deux-oeuvres')).click()`); await sleep(900);
    await ev(`document.querySelectorAll('.propositions-oeuvres .proposition')[0].click()`); await sleep(700);
    if(!await ev(`!!document.querySelector('.signal.est-faux')`)) continue;
    for (const f of fonts) { const i=await ev(M(f,'.indice')); res.push({largeur:W,etat:'echec',police:f,element:'indice',...i}); }
    await ev(`document.body.style.fontFamily=''`);
    await ev(`document.querySelectorAll('.propositions-oeuvres .proposition')[1].click()`); await sleep(700);
    if(await ev(`!!document.querySelector('.signal.est-juste')`)) continue;
    await ev(`document.querySelectorAll('.propositions-oeuvres .proposition')[0].click()`); await sleep(700);
    if(!await ev(`!!document.querySelector('[data-oeuvre-revelee]')`)) continue;
    for (const f of fonts) { const m=await ev(M(f,'.reponse-revelee')); res.push({largeur:W,etat:'revelation',police:f,element:'M4',...m}); }
    ok=true;
  }
  if(!ok) throw new Error('pas de revelation a '+W);
}
fs.writeFileSync(out,JSON.stringify(res,null,1));
for (const r of res) console.log(r.largeur,r.etat.padEnd(10),r.police.padEnd(18),r.element,'bas',r.bas,'h',r.h);
process.exit(0);
