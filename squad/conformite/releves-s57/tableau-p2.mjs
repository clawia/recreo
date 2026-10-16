// Cotes item par item de la cote de reference (s57, palier 2, ines-design), en tableau markdown, a une largeur.
// Usage : node tableau-p2.mjs <jar-de-base-seul.propre.json> <sommet-apparie.cand.json> <items.json> <largeur> <jeu-id>
// Colonnes : item (compte depuis 0), cote du bas de .indice apres une erreur sur le jar de base seul (ordre servi / meilleur / pire des six
// ordres) et sur le jar du sommet (idem), propositions servies. Pli : 551.
import fs from 'node:fs';
const [fSeule, fCand, fItems, w, jeu] = process.argv.slice(2);
const L = k => JSON.parse(fs.readFileSync(k, 'utf8'));
const seule = L(fSeule), cand = L(fCand), ITEMS = L(fItems);
const n = ITEMS[jeu].length;
const resume = r => { const o = r.echec1.ordres.map(x => x.indiceBas); return `${r.echec1.indice.bottom} / ${Math.min(...o)} / ${Math.max(...o)}`; };
console.log(`| Item | Enonce | Jar de base seul : servi / meilleur / pire | Sommet : servi / meilleur / pire | Propositions servies (sommet) |`);
console.log('|---|---|---|---|---|');
for (let i = 0; i < n; i++) {
  const a = seule[w][jeu].items[i], b = cand[w][jeu].items[i];
  if (!a || !b) { console.log(`| ${i} | - | non mesure | non mesure | - |`); continue; }
  console.log(`| ${i} | ${a.enonce.slice(0, 44)} | ${resume(a)} | ${resume(b)} | ${b.propositionsServies.join(' / ').slice(0, 70)} |`);
}
