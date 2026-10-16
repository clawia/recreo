// Resume de la mesure PA8 : pour un etat, par largeur et mode, valeurs min-max sur les six items et nom de variante.
// Usage : node resume-pa8.mjs <pa8.json> [etat=revelation] [variante=reel]
import fs from 'node:fs';
const d = JSON.parse(fs.readFileSync(process.argv[2], 'utf8')); const etat = process.argv[3] || 'revelation', nom = process.argv[4] || 'reel';
const mm = a => { const v = a.filter(x => x !== null && x !== undefined); return v.length ? (Math.min(...v) === Math.max(...v) ? `${Math.min(...v)}` : `${Math.min(...v)}-${Math.max(...v)}`) : '-'; };
const items = Object.values(d.items).filter(i => i[etat]);
console.log(`etat ${etat}, variante ${nom}, ${items.length} items`);
for (const w of Object.keys(items[0][etat])) for (const mode of ['barre', 'sans']) {
  const x = items.map(i => i[etat][w][mode][nom]);
  console.log(`L=${w} ${mode.padEnd(5)} clientW ${mm(x.map(a => a.clientW))} scrollW ${mm(x.map(a => a.scrollW))} | cabinet.w ${mm(x.map(a => a.cabinet.w))} signal.w ${mm(x.map(a => a.signal.w))} colonne.w ${mm(x.map(a => a.colonne && a.colonne.w))} | m4: phrases ${mm(x.map(a => a.m4Phrases.map(p => p.w).join('/')).map(s => s.split('/')[0]*1))} (1re) lignes ${mm(x.map(a => a.m4Lignes))} depasse boite ${mm(x.map(a => a.m4DepasseBoite))} depasse ecran ${mm(x.map(a => a.m4DepasseEcran))} bas m4 ${mm(x.map(a => a.m4 && a.m4.bottom))} | verdict phrase la plus large ${mm(x.map(a => a.verdictPlusLarge))} reste dans signal ${mm(x.map(a => a.verdictResteDansLeSignal))} lignes ${mm(x.map(a => a.verdictLignes))} | indice bas ${mm(x.map(a => a.indice && a.indice.bottom))}`);
}
