// Elements 1, 2 et 4 de la clause Q-R4 (s57, palier 2, ines-design), lus dans les sorties de pilote-p2.mjs.
// Usage : node elements-p2.mjs <jar-de-base-seul.propre.json> <sommet-apparie.cand.json> <items.json> <largeur,csv>
// Pour chaque largeur : combien d'items de l'element etaient au plus a 551 sur le jar de base seul (ordre servi et pire des six ordres),
// combien le sont sur le sommet, et combien ont perdu le pli. Element 3 (additions-cp) : script mesurer-additions-cp.mjs.
import fs from 'node:fs';
const [fSeule, fCand, fItems, largeurs] = process.argv.slice(2);
const L = k => JSON.parse(fs.readFileSync(k, 'utf8'));
const seule = L(fSeule), cand = L(fCand), ITEMS = L(fItems);
const PLI = 551;
const servi = (r, w, jeu, i) => r[w][jeu].items[i].echec1.indice.bottom;
const pire = (r, w, jeu, i) => Math.max(...r[w][jeu].items[i].echec1.ordres.map(o => o.indiceBas));
const V = 'bandit-manchot-verbe-sujet-ce1', G = 'bandit-manchot-accord-groupe-nominal-ce1', T = 'bandit-manchot-texte-narratif-ce1', C = 'bandit-manchot-comprehension-cm1';
const gateaux = ITEMS[G].findIndex(it => it.bonneReponse === 'les gâteaux');
for (const w of largeurs.split(',')) {
  const out = [];
  const ligne = (nom, jeu, idx) => {
    const bS = idx.map(i => servi(seule, w, jeu, i)), bP = idx.map(i => pire(seule, w, jeu, i));
    const sS = idx.map(i => servi(cand, w, jeu, i)), sP = idx.map(i => pire(cand, w, jeu, i));
    const perduS = idx.filter(i => servi(seule, w, jeu, i) <= PLI && servi(cand, w, jeu, i) > PLI).length;
    const perduP = idx.filter(i => pire(seule, w, jeu, i) <= PLI && pire(cand, w, jeu, i) > PLI).length;
    const mm = a => `${Math.min(...a)}-${Math.max(...a)}`;
    out.push(`L=${w} ${nom} (${idx.length} items) : jar de base seul ${mm(bS)} servi, ${mm(bP)} pire ordre, au-dessus de ${PLI} ${bS.filter(v => v <= PLI).length}/${idx.length} servi, ${bP.filter(v => v <= PLI).length}/${idx.length} pire | sommet ${mm(sS)} servi, ${mm(sP)} pire ordre, au-dessus ${sS.filter(v => v <= PLI).length}/${idx.length} servi, ${sP.filter(v => v <= PLI).length}/${idx.length} pire | pli perdu : ${perduS} servi, ${perduP} pire ordre | items en dessous de ${PLI} sur le sommet (servi) : [${idx.filter(i => servi(cand, w, jeu, i) > PLI).join(',')}], (pire ordre) : [${idx.filter(i => pire(cand, w, jeu, i) > PLI).join(',')}]`);
  };
  const tous = jeu => [...Array(ITEMS[jeu].length).keys()];
  // Element 1 : les items de verbe-sujet dont le bas etait a 534 (servi), et "les gateaux"
  ligne('E1 verbe-sujet, items a 534 sur la base', V, tous(V).filter(i => servi(seule, w, V, i) === 534));
  ligne('E1 verbe-sujet, les 24 items', V, tous(V));
  ligne(`E1 "les gateaux" (accord-groupe-nominal, item ${gateaux})`, G, [gateaux]);
  ligne('E2 texte-narratif, items a 550 sur la base', T, tous(T).filter(i => servi(seule, w, T, i) === 550));
  ligne('E2 texte-narratif, les 20 items', T, tous(T));
  ligne('E4 comprehension-cm1, les 20 items', C, tous(C));
  console.log(out.join('\n'));
}
