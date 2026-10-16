// Pour une variante : par largeur (barre / sans), signal.w par etat, M4 (colonne, depassement), cases, indice a l'echec, sur tous les items.
// Usage : node resume-pa8-etats.mjs <pa8.json> <variante>
import fs from 'node:fs';
const d = JSON.parse(fs.readFileSync(process.argv[2], 'utf8')); const n = process.argv[3];
const mm = a => { const v = a.filter(x => x != null); return v.length ? (Math.min(...v) === Math.max(...v) ? `${Math.min(...v)}` : `${Math.min(...v)}-${Math.max(...v)}`) : '-'; };
for (const w of ['720', '744', '768']) for (const mode of ['barre', 'sans']) {
  const g = e => Object.values(d.items).filter(i => i[e]).map(i => i[e][w][mode][n]);
  const sig = ['juste1', 'echec1', 'echec2', 'revelation'].map(e => `${e}:${mm(g(e).map(x => x.signal && x.signal.w))}`).join(' ');
  const r = g('revelation'), e1 = g('echec1');
  console.log(`${n} L=${w} ${mode.padEnd(5)} meuble ${mm(r.map(x => x.cabinet.w))} | signal.w par etat ${sig} | M4 colonne ${mm(r.map(x => x.colonne.w))} depasse boite ${mm(r.map(x => x.m4DepasseBoite))} ecran ${mm(r.map(x => x.m4DepasseEcran))} lignes ${mm(r.map(x => x.m4Lignes))} scrollW-clientW ${mm(r.map(x => x.scrollW - x.clientW))} | case ${mm(e1.map(x => x.cases[0] && x.cases[0].w))} ref ${mm(r.map(x => x.refImg && x.refImg.w))} | echec1 : bas indice ${mm(e1.map(x => x.indice && x.indice.bottom))} bas verdict ${mm(e1.map(x => x.verdict && x.verdict.bottom))} verdict dans signal ${mm(e1.map(x => x.verdictResteDansLeSignal))} | bas M4 ${mm(r.map(x => x.m4 && x.m4.bottom))}`);
}
