// Tableau des 65 enonces d'additions-cp, cote avant (jar de base) et apres (sommet), a partir des deux sorties de mesurer-additions-cp.mjs.
// Usage : node tableau-additions-p2.mjs <additions-jar-de-base.json> <additions-sommet.json>
import fs from 'node:fs';
const [fb, fs2] = process.argv.slice(2);
const b = JSON.parse(fs.readFileSync(fb, 'utf8')), s = JSON.parse(fs.readFileSync(fs2, 'utf8'));
const cotes = a => [...new Set(a.map(o => o.cote))].sort().join(' ou ');
console.log('| Enonce | Jar de base : cote (observations) | Sommet : cote (observations) | Trois propositions servies (sommet, premiere observation) |');
console.log('|---|---|---|---|');
for (const e of b.ensemble) {
  const ob = b.enonces[e], os = s.enonces[e];
  console.log(`| ${e} | ${cotes(ob)} (${ob.length}) | ${cotes(os)} (${os.length}) | ${os[0].propositions.join(' / ')} |`);
}
