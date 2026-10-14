// Agrege la sortie de pilote-poste-8.mjs : par largeur, les cotes les plus basses sur tous les items joues.
// Usage : node agreger-poste-8.mjs <sortie.json> [<largeur> ...]
import fs from 'node:fs';
const [f,...ws]=process.argv.slice(2);
const d=JSON.parse(fs.readFileSync(f,'utf8'));
const mx=a=>a.length?Math.max(...a):null, mn=a=>a.length?Math.min(...a):null;
for (const w of (ws.length?ws:Object.keys(d))) { const items=Object.values(d[w]);
 const get=(s,fn)=>items.filter(i=>i[s]).map(i=>fn(i[s])).filter(v=>v!=null);
 const nrev=items.filter(i=>i.revelation).length;
 const B=(e)=>m=>m[e]?m[e].bottom:null;
 const casesStable=items.every(i=>{const ref=i.fraiche.cases.map(c=>c.box.top+'/'+c.box.w); return ['echec1','echec2','revelation','juste1'].every(s=>!i[s]||i[s].cases.map(c=>c.box.top+'/'+c.box.w).join()===ref.join());});
 const overflow=items.some(i=>Object.values(i).some(m=>m&&m.scrollW!==undefined&&m.scrollW>m.clientW+0.5));
 console.log(`L=${w} items=${items.length} revelations=${nrev} | casesBas fraiche max ${mx(get('fraiche',m=>m.cases[0].box.bottom))} | echec: indice bas max ${mx([...get('echec1',B('indice')),...get('echec2',B('indice'))])}, verdict bas max ${mx(get('echec1',B('verdict')))}, signal bas max ${mx(get('echec1',B('signal')))}, croix bas max ${mx([...get('echec1',m=>mx(m.marques.map(x=>x.bottom))),...get('echec2',m=>mx(m.marques.map(x=>x.bottom)))])} | revel: M4 bas max ${mx(get('revelation',B('m4')))} (min ${mn(get('revelation',B('m4')))}), M4 haut min ${mn(get('revelation',m=>m.m4&&m.m4.top))}, explic ${mn(get('revelation',m=>m.explication.top))}-${mx(get('revelation',B('explication')))}, croix bas max ${mx(get('revelation',m=>mx(m.marques.map(x=>x.bottom))))} | juste: signal ${mn(get('juste1',m=>m.signal.top))}-${mx(get('juste1',B('signal')))} | docH fraiche ${mx(get('fraiche',m=>m.docH))} | cases immobiles(top,w) ${casesStable} | debord horizontal ${overflow}`);
}
