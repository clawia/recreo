// Resume de pilote-pa8.mjs en mode POLICES=1 : a la revelation, 1re phrase de M4, colonne, debordement, par police et variante.
// Usage : node resume-polices-pa8.mjs <pa8-polices.json> <variante,csv>
import fs from 'node:fs';
const d = JSON.parse(fs.readFileSync(process.argv[2], 'utf8')); const it = Object.values(d.items).find(i => i.polices);
for (const [p, m] of Object.entries(it.polices)) for (const w of Object.keys(m)) {
  const out = process.argv[3].split(',').map(v => { const x = m[w].barre[v]; return `${v}: phrase ${x.m4Phrases[0].w} col ${x.colonne.w} depasse ${x.m4DepasseBoite} lignes ${x.m4Lignes} signal ${x.signal.w} verdict hors signal ${x.verdictResteDansLeSignal < 0 ? 'oui' : 'non'}`; });
  console.log(p.padEnd(18), w, '|', out.join(' | '));
}
