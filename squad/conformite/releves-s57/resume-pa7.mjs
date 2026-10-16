// Resume de la mesure PA7 : par largeur et par item, rangees de propositions et bas de .indice dans les six ordres.
// Usage : node resume-pa7.mjs <releve-propre.json> [item,csv]
import fs from 'node:fs';
const d = JSON.parse(fs.readFileSync(process.argv[2], 'utf8'));
const focus = (process.argv[3] || '13,17,18').split(',');
const jeu = Object.keys(d['744'])[0];
for (const w of Object.keys(d)) {
  const its = d[w][jeu].items;
  let tot = 0, ok = 0, mx = 0; const hors = [];
  for (const [k, it] of Object.entries(its)) {
    const o = it.echec1.base ? it.echec1.base.ordres : it.echec1.ordres;
    for (const x of o) { tot++; if (x.indiceBas <= 551) ok++; mx = Math.max(mx, x.indiceBas); if (x.indiceBas > 551) hors.push(k + ':' + x.ordre + '=' + x.indiceBas); }
  }
  console.log(`L=${w} : ${Object.keys(its).length} items, ${tot} couples, au plus 551 : ${ok}/${tot}, max ${mx}, hors : ${hors.join(' ') || 'aucun'}`);
  for (const k of focus) {
    const it = its[k]; const o = it.echec1.ordres || it.echec1.base.ordres;
    const bas = o.map(x => x.indiceBas), r = o.map(x => x.rangees);
    console.log(`   item ${k} : rangees ${[...new Set(r)].sort()} (3 rangees dans ${r.filter(x => x === 3).length}/6) ; indiceBas ${Math.min(...bas)}-${Math.max(...bas)} ; marge a 551 : ${551 - Math.max(...bas)} ; par ordre : ${o.map(x => x.ordre + ':' + x.rangees + 'r/' + x.indiceBas).join(' ')}`);
  }
}
