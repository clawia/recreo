// Totaux de l'echantillon apparie (s57, palier 2, ines-design), par largeur, sur tous les jeux de la sortie moins ceux qu'on exclut.
// Usage : node totaux-p2.mjs <echantillon.json (brut ou compacte)> [<jeu-id,csv a exclure>]
// Memes definitions que analyser-echantillon-r3.mjs : "pli perdu" = indice (erreur) ou reponse revelee <= 551 sur la feuille de la base et
// > 551 sur celle du sommet ; "debordement neuf" = un element touche hors de la fenetre avec la forme et pas sans.
import fs from 'node:fs';
const d = JSON.parse(fs.readFileSync(process.argv[2], 'utf8'));
const exclus = (process.argv[3] || '').split(',').filter(Boolean);
const PLI = 551, EPS = 0.5;
const norm = m => Array.isArray(m) ? { msgBas: m[0], cabinet: m[1] === null ? null : { bottom: m[1] }, messageJeu: m[2] === null ? null : { h: m[2] }, indice: m[3] === null ? null : { bottom: m[3] }, attendue: m[4] === null ? null : { bottom: m[4] }, deborde: !!m[5] } : m;
const cote = (m, etat) => etat === 'revelation' ? (m.attendue ? m.attendue.bottom : null) : (etat.startsWith('echec') ? (m.indice ? m.indice.bottom : null) : null);
const bas = m => m.msgBas !== null && m.msgBas !== undefined ? m.msgBas : (m.cabinet ? m.cabinet.bottom : null);
const ws = Object.keys(Object.values(d)[0].etats[0].lignes);
for (const w of ws) {
  const t = { jeux: 0, etats: 0, comparables: 0, perdu: 0, gagne: 0, debord: 0, indiceHausse: 0, plusHaut: 0, pireHausse: 0, gainMin: 1e9, gainMax: -1e9 };
  const perdus = [], debords = [];
  for (const [jeu, r] of Object.entries(d)) {
    if (exclus.includes(jeu)) continue;
    t.jeux++;
    for (const e of r.etats) {
      const b = norm(e.lignes[w].base), c = norm(e.lignes[w].cand); t.etats++;
      const hb = b.messageJeu ? b.messageJeu.h : 0, hc = c.messageJeu ? c.messageJeu.h : 0;
      if (b.messageJeu && hc > hb + EPS) { t.plusHaut++; t.pireHausse = Math.max(t.pireHausse, hc - hb); }
      const ob = cote(b, e.etat), oc = cote(c, e.etat);
      if (ob !== null && oc !== null) {
        t.comparables++;
        if (ob <= PLI && oc > PLI) { t.perdu++; perdus.push([jeu, e.etat, ob, oc]); }
        if (ob > PLI && oc <= PLI) t.gagne++;
        if (e.etat.startsWith('echec') && oc > ob + EPS) t.indiceHausse++;
      }
      if (bas(b) !== null && bas(c) !== null) { const g = bas(b) - bas(c); t.gainMin = Math.min(t.gainMin, g); t.gainMax = Math.max(t.gainMax, g); }
      if (c.deborde && !b.deborde) { t.debord++; debords.push([jeu, e.etat]); }
    }
  }
  console.log(`L=${w} jeux ${t.jeux}, etats ${t.etats} (dont ${t.comparables} avec indice ou reponse revelee a comparer) | pli perdu ${t.perdu} | pli gagne ${t.gagne} | indice plus bas apres erreur ${t.indiceHausse} | debordement neuf a droite ${t.debord} | bloc message plus haut ${t.plusHaut} (max +${t.pireHausse.toFixed(1)} px) | bas de l'ecran gagne ${t.gainMin}..${t.gainMax} px`);
  if (perdus.length) console.log('   pli perdu :', JSON.stringify(perdus.slice(0, 10)));
  if (debords.length) console.log('   debord :', JSON.stringify(debords.slice(0, 10)));
}
