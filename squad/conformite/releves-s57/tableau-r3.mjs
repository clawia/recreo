// Ecrit en Markdown, pour le rapport d'ines-design (R8 / Q-R3), les tableaux par item d'une sortie de pilote-r3.mjs.
// Usage : node tableau-r3.mjs <sortie.base.json> <sortie.cand.json> <largeur> <jeu-id,csv>
// Une ligne par item : rangees de propositions, cotes de .indice et du verdict apres une erreur (jar de base), les propositions
// servies, la cote de .indice avec le candidat et le gain. Les cotes sont celles du JSON, rien n'est recalcule a la main.
import fs from 'node:fs';
const [fb, fc, w, jeux] = process.argv.slice(2);
const B = JSON.parse(fs.readFileSync(fb, 'utf8')), C = JSON.parse(fs.readFileSync(fc, 'utf8'));
const esc = s => s.replace(/\|/g, '\\|');
for (const jeu of jeux.split(',')) {
  const items = Object.values(B[w][jeu].items).sort((a, b) => a.idx - b.idx);
  console.log(`\n#### ${jeu}, ${w} x 551, une erreur par item (${items.length} items)\n`);
  console.log('| Item | Rangees | Indice, jar de base | Verdict, jar de base | Propositions servies | Indice, candidat | Gain |');
  console.log('|---|---|---|---|---|---|---|');
  for (const it of items) {
    const c = C[w][jeu].items[it.idx].echec1, b = it.echec1;
    const rangees = new Set(b.propositions.map(p => p.box.top)).size;
    const gain = +(b.indice.bottom - c.indice.bottom).toFixed(1);
    console.log(`| ${it.idx} - ${esc(it.enonce.slice(0, 46))} | ${rangees} | ${b.indice.top} - ${b.indice.bottom} | ${b.verdict.top} - ${b.verdict.bottom} | ${esc(it.propositionsServies.join(' / '))} | ${c.indice.top} - ${c.indice.bottom} | ${gain} |`);
  }
}
