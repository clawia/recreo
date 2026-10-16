// Bas du signal, du dernier bloc du message et du document apres une erreur, par largeur, sur tous les items (six ordres).
// Usage : node bas-pa7.mjs <releve-propre.json>
import fs from 'node:fs';
const d = JSON.parse(fs.readFileSync(process.argv[2], 'utf8'));
const jeu = Object.keys(d['744'])[0];
for (const w of ['720', '744', '768']) {
  const its = d[w][jeu].items; const f = (k, c) => Object.values(its).flatMap(it => it.echec1.ordres.map(o => o[c]));
  const par = (ks, c) => ks.flatMap(k => its[k].echec1.ordres.map(o => o[c]));
  const S = ['13', '17', '18'];
  console.log(`L=${w} signalBas 20 items max ${Math.max(...f(0, 'signalBas'))} ; items 13/17/18 max ${Math.max(...par(S, 'signalBas'))} ; messageBas 20 items max ${Math.max(...f(0, 'messageBas'))} ; items 13/17/18 max ${Math.max(...par(S, 'messageBas'))} ; docH max ${Math.max(...f(0, 'docH'))}`);
}
