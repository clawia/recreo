// Coupure du verdict de l'echec ("Ce n'est pas ça. Essaie encore !") sous trois formes, par police et par largeur.
// Usage : node coupure-poste-8.mjs <chrome> <profil-isole-jetable> <url> <css-candidat> <port-de-debogage> <largeurs,csv> <sortie.json>
// Variantes : A = balisage actuel ; B = une phrase par <span> en white-space: nowrap ; C = espaces insecables a l'interieur de chaque phrase.
// Par defaut sur le jeu du paquet (matiere arts-plastiques). Variable AUTRES=1 : un jeu bandit-manchot de la matiere maths, sans CSS candidat utile.
// Les polices sont imposees par le style de <body> : elles ne remplacent pas un appareil reel.
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
await send('Page.addScriptToEvaluateOnNewDocument',{source:`document.addEventListener('DOMContentLoaded',()=>{const s=document.createElement('style');s.textContent=${JSON.stringify(css)}+'.ph{white-space:nowrap}';document.head.appendChild(s);});`});
const AUTRES=process.env.AUTRES==='1';
const SEL=AUTRES?'.ecran-de-jeu .verdict':'.ecran-deux-oeuvres .verdict';
const MATIERE=AUTRES?'maths':'arts-plastiques';
const BTN=AUTRES?'/parties/':'rapprocher-deux-oeuvres';
const CASE=AUTRES?'.propositions .proposition':'.propositions-oeuvres .proposition';
const fonts=AUTRES?['(police du poste)','Verdana']:['(police du poste)','Arial','Georgia','Times New Roman','Verdana','Courier New','Comic Sans MS','Trebuchet MS','Tahoma'];
const MES=`((variante,police)=>{
 const v=document.querySelector('${SEL}'); if(!v) return null;
 if(v.dataset.o===undefined) v.dataset.o=v.textContent;
 const o=v.dataset.o;
 document.body.style.fontFamily = police==='(police du poste)' ? '' : '"'+police+'"';
 const i=o.indexOf('. ');
 const ph=i<0?[o]:[o.slice(0,i+1),o.slice(i+2)];
 if(variante==='A') v.textContent=o;
 if(variante==='B') v.innerHTML=ph.map(p=>'<span class="ph">'+p+'</span>').join(' ');
 if(variante==='C') v.textContent=ph.map(p=>p.replace(/ /g,'\u00a0')).join(' ');
 const tn=[]; const w=document.createTreeWalker(v,NodeFilter.SHOW_TEXT); let n; while(n=w.nextNode()) tn.push(n);
 const words=[]; for(const t of tn){ const re=/[^ \u00a0]+/g; let m; while(m=re.exec(t.data)){ const r=document.createRange(); r.setStart(t,m.index); r.setEnd(t,m.index+m[0].length); const b=r.getClientRects()[0]; words.push({w:m[0],top:Math.round(b.top),right:b.right}); } }
 // phrase de chaque mot : fin de phrase = mot finissant par . ou ! ou ?
 let ph0=0; const lab=words.map(x=>{const p=ph0; if(/[.!?]$/.test(x.w)||x.w==='!'||x.w==='?') ph0++; return p;});
 const lignes=[...new Set(words.map(x=>x.top))].length;
 let dedans=false; for(let k=0;k<words.length;k++) for(let j=k+1;j<words.length;j++) if(lab[k]===lab[j]&&words[k].top!==words[j].top) dedans=true;
 const sig=v.closest('.signal').getBoundingClientRect();
 const deborde=Math.max(...words.map(x=>x.right))>sig.right+0.5;
 return {lignes,coupureDansUnePhrase:dedans,depasseLeBandeau:deborde,texte:[...new Set(words.map(x=>x.top))].map(t=>words.filter(x=>x.top===t).map(x=>x.w).join(' ')).join(' / ')};
})`;
const res=[];
for (const W of widths.split(',').map(Number)) {
  await send('Emulation.setDeviceMetricsOverride',{width:W,height:551,deviceScaleFactor:1,mobile:false});
  let ok=false;
  for (let t=0;t<15&&!ok;t++){
    await send('Network.clearBrowserCookies'); await send('Page.navigate',{url:base+'/niveaux/CP/matieres/'+MATIERE}); await sleep(900);
    await ev(`[...document.querySelectorAll('button[hx-post]')].find(b=>(b.getAttribute('hx-post')||'').includes('${BTN}')).click()`); await sleep(900);
    await ev(`(()=>{const b=document.querySelector('${CASE}');b.click();})()`); await sleep(800);
    ok = await ev(`!!document.querySelector('.signal.est-faux')`);
  }
  if(!ok) throw new Error('pas d echec');
  for (const f of fonts) for (const v of ['A','B','C']) { const r=await ev(`(${MES})('${v}','${f}')`); res.push({largeur:W,police:f,variante:v,...r}); }
}
fs.writeFileSync(out,JSON.stringify(res,null,1));
for (const r of res) console.log(r.largeur,r.police.padEnd(18),r.variante,'lignes',r.lignes,'dedans',r.coupureDansUnePhrase,'deborde',r.depasseLeBandeau,'|',r.texte);
process.exit(0);
