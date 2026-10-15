// Agrege la sortie de pilote-r3.mjs : par largeur et par jeu, les cotes du bas de .indice apres une erreur,
// le compte au-dessus du pli (551), le bas du verdict, la revelation, la stabilite des cases.
// Usage : node agreger-r3.mjs <sortie.json> [--items] [--ref <autre-sortie.json>]
// Avec --ref, ajoute par item la difference (sortie - ref) du bas de .indice apres echec1 : le gain de la forme.
import fs from 'node:fs';
const args = process.argv.slice(2); const f = args[0]; const showItems = args.includes('--items');
const refF = args.includes('--ref') ? args[args.indexOf('--ref') + 1] : null;
const d = JSON.parse(fs.readFileSync(f, 'utf8')); const ref = refF ? JSON.parse(fs.readFileSync(refF, 'utf8')) : null;
const PLI = 551;
const mn = a => a.length ? Math.min(...a) : null, mx = a => a.length ? Math.max(...a) : null;
for (const w of Object.keys(d)) for (const [jeu, r] of Object.entries(d[w])) {
  const its = Object.values(r.items).filter(i => i.echec1);
  const bas = its.map(i => i.echec1.indice ? i.echec1.indice.bottom : null).filter(v => v !== null);
  const verd = its.map(i => i.echec1.verdict ? i.echec1.verdict.bottom : null).filter(v => v !== null);
  const sig = its.map(i => i.echec1.signal ? i.echec1.signal.bottom : null).filter(v => v !== null);
  const vert = bas.filter(v => v <= PLI).length;
  const e2 = its.filter(i => i.echec2 && i.echec2.indice).map(i => i.echec2.indice.bottom);
  const rev = its.filter(i => i.revelation);
  const revBas = rev.map(i => Math.max(i.revelation.attendue ? i.revelation.attendue.bottom : 0, i.revelation.explication ? i.revelation.explication.bottom : 0));
  const revRep = rev.map(i => i.revelation.attendue ? i.revelation.attendue.bottom : null).filter(v => v !== null);
  const revInd = rev.map(i => i.revelation.indice ? i.revelation.indice.bottom : null).filter(v => v !== null);
  const revMsg = rev.map(i => i.revelation.messageJeu ? i.revelation.messageJeu.bottom : null).filter(v => v !== null);
  const rangees = its.map(i => new Set(i.echec1.propositions.map(p => p.box.top)).size);
  const debord = its.some(i => ['arrivee', 'echec1', 'echec2', 'revelation'].some(s => i[s] && i[s].scrollW > i[s].clientW + 0.5));
  const stable = its.every(i => { const k = m => m.propositions.map(p => p.box.top + '/' + p.box.w + '/' + p.box.h).join(); return ['echec2', 'revelation'].every(s => !i[s] || k(i[s]) === k(i.echec1)); });
  const hautCabinet = its.map(i => i.echec1.cabinet ? i.echec1.cabinet.top : null).filter(v => v !== null);
  console.log(`L=${w} ${jeu} : items ${its.length}, indice bas apres erreur ${mn(bas)}-${mx(bas)}, au-dessus de ${PLI} : ${vert}/${bas.length}, verdict bas ${mn(verd)}-${mx(verd)}, signal bas ${mn(sig)}-${mx(sig)}, indice bas apres 2e erreur ${mn(e2)}-${mx(e2)} | revelation : reponse bas ${mn(revRep)}-${mx(revRep)} (${revRep.filter(v => v <= PLI).length}/${revRep.length} au-dessus), explication ou reponse bas ${mn(revBas)}-${mx(revBas)}, indice bas ${mn(revInd)}-${mx(revInd)} (${revInd.filter(v => v <= PLI).length}/${revInd.length} au-dessus), message bas ${mn(revMsg)}-${mx(revMsg)} | rangees ${[...new Set(rangees)].sort()} | haut du cabinet ${mn(hautCabinet)}-${mx(hautCabinet)} | cases immobiles ${stable} | debord horizontal ${debord} | parties ${r.parties}, anomalies ${r.anomalies.length}, manquants ${r.manquants.length}`);
  // Toutes les permutations des propositions (pilote-r3.mjs, ordres) : le cote d'un item depend de l'ordre servi.
  const ord = its.filter(i => i.echec1.ordres && i.echec1.ordres.length);
  if (ord.length) {
    const pire = ord.map(i => mx(i.echec1.ordres.map(o => o.indiceBas)));
    const meil = ord.map(i => mn(i.echec1.ordres.map(o => o.indiceBas)));
    const toutes = ord.flatMap(i => i.echec1.ordres.map(o => o.indiceBas));
    const verdPire = ord.map(i => mx(i.echec1.ordres.map(o => o.verdictBas)));
    const rev = its.filter(i => i.revelation && i.revelation.ordres && i.revelation.ordres.length);
    const revRepPire = rev.map(i => mx(i.revelation.ordres.map(o => o.attendueBas)));
    let perdus = '';
    if (ref && ref[w] && ref[w][jeu]) {
      let n = 0, tot = 0;
      for (const i of ord) { const r0 = ref[w][jeu].items[i.idx]; if (!r0 || !r0.echec1.ordres) continue; for (const o of i.echec1.ordres) { const o0 = r0.echec1.ordres.find(x => x.ordre === o.ordre); tot++; if (o0 && o0.indiceBas <= PLI && o.indiceBas > PLI) n++; } }
      perdus = `, couples (item, ordre) au-dessus de ${PLI} au depart et en dessous apres : ${n}/${tot}`;
    }
    console.log(`   permutations : ${toutes.length} couples (item, ordre) ; indice bas ${mn(toutes)}-${mx(toutes)} ; pire ordre de chaque item ${mn(pire)}-${mx(pire)}, items dont le pire ordre est au-dessus de ${PLI} : ${pire.filter(v => v <= PLI).length}/${pire.length} ; meilleur ordre ${mn(meil)}-${mx(meil)} ; verdict bas, pire ordre ${mn(verdPire)}-${mx(verdPire)} ; revelation, reponse bas pire ordre ${mn(revRepPire)}-${mx(revRepPire)} (${revRepPire.filter(v => v <= PLI).length}/${revRepPire.length} au-dessus)${perdus}`);
  }
  if (showItems) {
    for (const i of its.sort((a, b) => a.idx - b.idx)) {
      const rf = ref && ref[w] && ref[w][jeu] && ref[w][jeu].items[i.idx] && ref[w][jeu].items[i.idx].echec1;
      const g = rf && rf.indice && i.echec1.indice ? +(rf.indice.bottom - i.echec1.indice.bottom).toFixed(1) : '';
      console.log(`  i${String(i.idx).padStart(2)} rangees ${new Set(i.echec1.propositions.map(p => p.box.top)).size} indice ${i.echec1.indice ? i.echec1.indice.top + '-' + i.echec1.indice.bottom : '-'} verdict ${i.echec1.verdict ? i.echec1.verdict.top + '-' + i.echec1.verdict.bottom : '-'}${rf ? ' (ref ' + rf.indice.top + '-' + rf.indice.bottom + ', gain ' + g + ')' : ''} | ${i.enonce.slice(0, 50)} | ${i.propositionsServies.join(' / ').slice(0, 80)}`);
    }
  }
}
