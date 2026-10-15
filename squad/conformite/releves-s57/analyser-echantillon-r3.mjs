// Resume de la sortie de pilote-r3-echantillon.mjs : par jeu et par largeur, ce que le candidat R3 change sur les memes etats.
// Usage : node analyser-echantillon-r3.mjs <echantillon.json> [<largeur,csv>] [--etats]
// Colonnes : etats (echec* / revelation / juste / fraiche) ; "bloc message plus haut" = etats ou .message-jeu est plus haut
// avec le candidat ; "pli perdu" = indice (echec) ou reponse revelee (revelation) au-dessus de 551 sans le candidat et en
// dessous avec ; "pli gagne" = l'inverse ; "debord" = un element touche hors de la fenetre avec le candidat et pas sans.
import fs from 'node:fs';
const args = process.argv.slice(2); const d = JSON.parse(fs.readFileSync(args[0], 'utf8'));
const ws = (args[1] && !args[1].startsWith('--')) ? args[1].split(',') : null; const detail = args.includes('--etats');
const PLI = 551, EPS = 0.5;
// Format compact (compacter-r3.mjs) : un tableau [msgBas, cabinetBas, messageH, indiceBas, attendueBas, deborde] ; format brut : la mesure entiere.
const norm = m => Array.isArray(m) ? { msgBas: m[0], cabinet: m[1] === null ? null : { bottom: m[1] }, messageJeu: m[2] === null ? null : { h: m[2] }, indice: m[3] === null ? null : { bottom: m[3] }, attendue: m[4] === null ? null : { bottom: m[4] }, deborde: !!m[5] } : m;
const cote = (m, etat) => etat === 'revelation' ? (m.attendue ? m.attendue.bottom : null) : (etat.startsWith('echec') ? (m.indice ? m.indice.bottom : null) : null);
const lignes = [];
for (const [jeu, r] of Object.entries(d)) {
  for (const w of Object.keys(r.etats[0] ? r.etats[0].lignes : {})) {
    if (ws && !ws.includes(w)) continue;
    let n = { echec: 0, revelation: 0, juste: 0, fraiche: 0 }, plusHaut = 0, perdu = 0, gagne = 0, debord = 0, pireHausse = 0, gainMin = 1e9, gainMax = -1e9, cabinetMemeForme = 0, tot = 0, indiceHausse = 0;
    const pires = [];
    for (const e of r.etats) {
      const b = norm(e.lignes[w].base), c = norm(e.lignes[w].cand); tot++;
      const k = e.etat.startsWith('echec') ? 'echec' : e.etat; n[k]++;
      const hb = b.messageJeu ? b.messageJeu.h : 0, hc = c.messageJeu ? c.messageJeu.h : 0;
      if (b.messageJeu && hc > hb + EPS) { plusHaut++; pireHausse = Math.max(pireHausse, hc - hb); pires.push([e.etat, +(hc - hb).toFixed(1), (e.enonce || '').trim().slice(0, 60)]); }
      const ob = cote(b, e.etat), oc = cote(c, e.etat);
      if (ob !== null && oc !== null) {
        if (ob <= PLI && oc > PLI) perdu++;
        if (ob > PLI && oc <= PLI) gagne++;
        if (e.etat.startsWith('echec') && oc > ob + EPS) indiceHausse++;
      }
      const bas = (m) => m.msgBas !== null && m.msgBas !== undefined ? m.msgBas : (m.cabinet ? m.cabinet.bottom : null);
      if (bas(b) !== null && bas(c) !== null) { const g = bas(b) - bas(c); gainMin = Math.min(gainMin, g); gainMax = Math.max(gainMax, g); }
      if (c.deborde && !b.deborde) debord++;
    }
    lignes.push(`L=${w} ${jeu.replace('bandit-manchot-', '').padEnd(42)} etats ${String(tot).padStart(3)} (echec ${n.echec}, rev ${n.revelation}, juste ${n.juste}, fraiche ${n.fraiche}) | bloc message plus haut : ${plusHaut}${plusHaut ? ' (max +' + pireHausse.toFixed(1) + ')' : ''} | indice plus bas apres echec : ${indiceHausse} | pli perdu : ${perdu} | pli gagne : ${gagne} | debord neuf : ${debord} | bas de l'ecran gagne ${gainMin}..${gainMax}${detail && pires.length ? '\n     ' + pires.slice(0, 5).map(p => p.join(' | ')).join('\n     ') : ''}`);
  }
}
console.log(lignes.join('\n'));
