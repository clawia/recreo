// Tableaux de la cote de reference de Q-R4 (s57, palier 2, ines-design) a partir des sorties de pilote-p2.mjs.
// Usage : node cote-p2.mjs <jar-de-base-seul.propre.json> <sommet-apparie.base.json> <sommet-apparie.cand.json> <items.json> [<largeur,csv>]
// - "base seule" : le jar de base joue seul, sa propre feuille (cote de reference, Q-R5).
// - "bras base" : la feuille du jar de base a la place de celle du sommet, sur le DOM du sommet.
// - "sommet" : la feuille servie par le jar du sommet.
// Pli : bas de .indice apres une erreur <= 551 (meme lecture que agreger-r3.mjs).
import fs from 'node:fs';
const [fSeule, fBase, fCand, fItems, largeurs] = process.argv.slice(2);
const L = k => JSON.parse(fs.readFileSync(k, 'utf8'));
const seule = L(fSeule), base = L(fBase), cand = L(fCand), ITEMS = L(fItems);
const PLI = 551;
const ws = largeurs ? largeurs.split(',') : Object.keys(seule);
const mn = a => a.length ? Math.min(...a) : null, mx = a => a.length ? Math.max(...a) : null;
const coteServie = (res, w, jeu, i) => { const r = res[w][jeu].items[i]; return r && r.echec1 && r.echec1.indice ? r.echec1.indice.bottom : null; };
const ordresDe = (res, w, jeu, i) => { const r = res[w][jeu].items[i]; return r && r.echec1 && r.echec1.ordres ? r.echec1.ordres : []; };
for (const w of ws) {
  for (const jeu of Object.keys(ITEMS)) {
    if (!seule[w] || !seule[w][jeu]) continue;
    const n = ITEMS[jeu].length;
    const idx = [...Array(n).keys()].filter(i => coteServie(seule, w, jeu, i) !== null);
    const cs = idx.map(i => coteServie(seule, w, jeu, i));
    const cb = idx.map(i => coteServie(base, w, jeu, i));
    const cc = idx.map(i => coteServie(cand, w, jeu, i));
    const dessus = a => a.filter(v => v !== null && v <= PLI).length;
    // equivalence : le bras base (feuille de la base sur le DOM du sommet) rend la cote du jar de base pris seul. Les deux jeux de mesures
    // viennent de parties differentes, donc l'ordre servi et la proposition barree different : les couples sont donc apparies par la
    // suite de textes des trois propositions et par la proposition barree, jamais par le numero d'ordre.
    const seq = (r, o) => o.ordre.split('').map(k => r.propositionsServies[+k]).join('|');
    const barree = r => r.echec1.propositions.filter(p => p.essayee).map(p => p.texte).join('|');
    let couples = 0, diff = 0, nonComparables = 0;
    for (const i of idx) {
      const r1 = seule[w][jeu].items[i], r2 = base[w][jeu].items[i];
      if (barree(r1) !== barree(r2)) { nonComparables += ordresDe(seule, w, jeu, i).length; continue; }
      for (const o of ordresDe(seule, w, jeu, i)) {
        const o2 = ordresDe(base, w, jeu, i).find(x => seq(r2, x) === seq(r1, o));
        if (!o2) continue; couples++;
        if (o.indiceBas !== o2.indiceBas) diff++;
      }
    }
    // pire ordre
    const pire = (res) => idx.map(i => mx(ordresDe(res, w, jeu, i).map(o => o.indiceBas)));
    const pS = pire(seule), pC = pire(cand);
    // pli perdu / gagne, en ordre servi et par couple
    let perdu = 0, gagne = 0, perduCouples = 0, totCouples = 0;
    for (const i of idx) {
      const a = coteServie(base, w, jeu, i), b = coteServie(cand, w, jeu, i);
      if (a <= PLI && b > PLI) perdu++; if (a > PLI && b <= PLI) gagne++;
      for (const o of ordresDe(base, w, jeu, i)) { const o2 = ordresDe(cand, w, jeu, i).find(x => x.ordre === o.ordre); if (!o2) continue; totCouples++; if (o.indiceBas <= PLI && o2.indiceBas > PLI) perduCouples++; }
    }
    console.log(`L=${w} ${jeu.replace('bandit-manchot-', '')} : items mesures ${idx.length}/${n} | jar de base seul : ${mn(cs)}-${mx(cs)}, au-dessus de ${PLI} ${dessus(cs)}/${cs.length}, pire ordre au-dessus ${pS.filter(v => v <= PLI).length}/${pS.length} | bras base : ${mn(cb)}-${mx(cb)}, ${dessus(cb)}/${cb.length}, ecart au jar de base seul : ${diff}/${couples} couples (suite de textes, meme proposition barree), ${nonComparables} non comparables (autre proposition barree) | sommet : ${mn(cc)}-${mx(cc)}, au-dessus ${dessus(cc)}/${cc.length}, pire ordre au-dessus ${pC.filter(v => v <= PLI).length}/${pC.length} | pli perdu (bras base -> sommet) ${perdu} items, ${perduCouples}/${totCouples} couples ; pli gagne ${gagne}`);
  }
}
