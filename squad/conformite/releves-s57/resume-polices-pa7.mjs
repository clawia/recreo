// Resume des releves de polices-r3.mjs (item, largeur, tirages) : bas de .indice et du dernier bloc par police, ordre aleatoire du serveur.
// Usage : node resume-polices-pa7.mjs <repertoire> 
import fs from 'node:fs';
const dir = process.argv[2];
const fich = fs.readdirSync(dir).filter(f => /^polices-\d+-i\d+-r\d+\.json$/.test(f)).sort();
const agg = {};
for (const f of fich) { const [, w, it] = f.match(/^polices-(\d+)-(i\d+)-r\d+\.json$/); for (const r of JSON.parse(fs.readFileSync(dir + '/' + f, 'utf8'))) { const k = `${it}|${w}|${r.police}`; (agg[k] ||= []).push(r.cand); } }
const polices = [...new Set(Object.keys(agg).map(k => k.split('|')[2]))];
for (const it of ['i13', 'i17', 'i18']) for (const w of ['744', '720']) {
  const cells = polices.map(p => { const v = agg[`${it}|${w}|${p}`]; return `${p.replace('(police du poste)', 'poste')}:${v.map(x => x.indice).join('/')}`; });
  const tous = polices.flatMap(p => agg[`${it}|${w}|${p}`].map(x => x.indice)); const der = polices.flatMap(p => agg[`${it}|${w}|${p}`].map(x => x.dernier));
  console.log(`${it} L=${w} : indice max ${Math.max(...tous)} ; dernier bloc max ${Math.max(...der)} | ${cells.join(' ; ')}`);
}
