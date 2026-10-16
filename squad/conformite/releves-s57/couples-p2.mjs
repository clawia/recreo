// Bas de .indice sur tous les couples (item, ordre des propositions), par largeur et par jeu, sur les trois bras.
// Usage : node couples-p2.mjs <jar-de-base-seul.propre.json> <sommet-apparie.base.json> <sommet-apparie.cand.json> <largeur,csv>
import fs from 'node:fs';
const [a, b, c, largeurs] = process.argv.slice(2);
const L = k => JSON.parse(fs.readFileSync(k, 'utf8'));
const bras = { 'jar de base seul': L(a), 'bras base (feuille de la base sur le DOM du sommet)': L(b), 'sommet': L(c) };
for (const w of largeurs.split(',')) for (const jeu of Object.keys(bras['sommet'][w])) {
  const lignes = [];
  for (const [nom, d] of Object.entries(bras)) {
    const v = Object.values(d[w][jeu].items).flatMap(it => it.echec1.ordres.map(o => o.indiceBas));
    lignes.push(`${nom} : ${v.length} couples, ${Math.min(...v)}-${Math.max(...v)}, au plus 551 : ${v.filter(x => x <= 551).length}/${v.length}`);
  }
  console.log(`L=${w} ${jeu.replace('bandit-manchot-', '')} | ${lignes.join(' | ')}`);
}
