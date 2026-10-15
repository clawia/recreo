// Reduit une sortie de pilote-r3.mjs ou de pilote-r3-echantillon.mjs aux seuls champs que lisent agreger-r3.mjs,
// tableau-r3.mjs et analyser-echantillon-r3.mjs (meme structure, moins de champs, JSON sans indentation) : le depot garde
// le releve, pas les cotes de chaque boite de la page.
// Usage : node compacter-r3.mjs <sortie.json> <compacte.json> <largeurs,csv> [--sans-echec2]
import fs from 'node:fs';
const [src, dst, largeurs, ...opts] = process.argv.slice(2);
const LARG = largeurs.split(',');
const d = JSON.parse(fs.readFileSync(src, 'utf8'));
const box = b => b ? { top: b.top, bottom: b.bottom, h: b.h } : b;
const BOITES = ['indice', 'verdict', 'signal', 'messageJeu', 'cabinet', 'attendue', 'explication'];
function mes(m) {
  if (!m) return m;
  const o = { docH: m.docH, scrollW: m.scrollW, clientW: m.clientW, etat: m.etat, enonceTexte: m.enonceTexte };
  if (m.propositions) o.propositions = m.propositions.map(p => ({ texte: p.texte, essayee: p.essayee, box: { top: p.box.top, left: p.box.left, w: p.box.w, h: p.box.h } }));
  for (const k of BOITES) o[k] = box(m[k]);
  if (m.ordres) o.ordres = m.ordres.map(r => ({ ordre: r.ordre, rangees: r.rangees, signalBas: r.signalBas, verdictBas: r.verdictBas, indiceBas: r.indiceBas, attendueBas: r.attendueBas }));
  return o;
}
// Echantillon : un tableau par mesure, [bas du dernier bloc du message ou du meuble, bas du meuble, hauteur du bloc message,
// bas de .indice, bas de la reponse revelee, 1 si un element depasse a droite], null si l'element est absent.
function mesEchantillon(m) {
  return [m.msgBas, m.cabinet ? m.cabinet.bottom : null, m.messageJeu ? m.messageJeu.h : null, m.indice ? m.indice.bottom : null, m.attendue ? m.attendue.bottom : null, m.deborde ? 1 : 0];
}
let out;
if (Object.values(d)[0] && Object.values(d)[0].etats) {
  out = {};
  for (const [jeu, r] of Object.entries(d)) out[jeu] = { anomalies: r.anomalies, etats: r.etats.map(e => ({ etat: e.etat, enonce: (e.enonce || '').replace(/\s+/g, ' ').trim().slice(0, 80), nProps: e.nProps, champs: ['msgBas', 'cabinetBas', 'messageH', 'indiceBas', 'attendueBas', 'deborde'], lignes: Object.fromEntries(Object.entries(e.lignes).filter(([w]) => LARG.includes(w)).map(([w, l]) => [w, { base: mesEchantillon(l.base), cand: mesEchantillon(l.cand) }])) })) };
} else {
  out = {};
  for (const [w, jeux] of Object.entries(d)) {
    if (!LARG.includes(w)) continue;
    out[w] = {};
    for (const [jeu, r] of Object.entries(jeux)) {
      const items = {};
      for (const [k, it] of Object.entries(r.items)) {
        const o = { idx: it.idx, enonce: it.enonce, bonneReponse: it.bonneReponse, propositionsServies: it.propositionsServies };
        for (const s of ['echec1', 'echec2', 'revelation']) if (it[s] && !(s === 'echec2' && opts.includes('--sans-echec2'))) o[s] = mes(it[s]);
        items[k] = o;
      }
      out[w][jeu] = { parties: r.parties, items, anomalies: r.anomalies, manquants: r.manquants };
    }
  }
}
fs.writeFileSync(dst, JSON.stringify(out));
console.log(dst.split('/').pop(), fs.statSync(dst).size, 'octets');
