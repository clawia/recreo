// Mesure des 65 enonces de bandit-manchot-additions-cp (s57, palier 2, S57-SCRIPT-ADDITIONS-CP). Derive de pilote-r3.mjs.
// Le jeu tire ses enonces au hasard (aucune graine) : l'ensemble est derive de sommeMaximale (contenu/cp-additions-jusqu-a-10.yaml) et
// de la regle de AdditionsBorneesContentSource.couplesValides (couples (a, b) avec a + b <= sommeMaximale, (0, 0) excepte), jamais recopie.
// Parcours de TD5 : l'enonce donne la bonne reponse, le script appuie sur une autre proposition, releve le bas de .indice
// (getBoundingClientRect, 744 x 551), puis repond juste. Les parties se rejouent jusqu'a ce que chaque enonce de l'ensemble ait une cote.
// Chaque manche jouee est mesuree : un enonce vu deux fois donne deux observations, ce qui verifie l'hypothese "item = enonce" de TD4.
//
// Mesure : node mesurer-additions-cp.mjs <chrome> <port-de-jeu> <port-de-debogage> <repertoire-de-sortie>
// Ensemble : node mesurer-additions-cp.mjs --ensemble [<yaml>]   (un enonce par ligne, aucun reseau)
// Sortie : <repertoire-de-sortie>/additions-cp-744.json ; le profil de Chrome vit dans <repertoire-de-sortie>/profil-chrome.
// Codes de sortie : 0 chaque enonce de l'ensemble a une cote et aucun enonce hors ensemble ; 3 fichier ecrit mais des enonces
// ne sont pas mesures (liste dans le fichier) ; 1 rien n'ecoute sur le port de jeu, port de debogage occupe, Chrome muet, ou
// erreur en cours de route : aucun fichier de resultat n'est alors ecrit ; 2 arguments invalides.
// Variable PARTIES_MAX (300 par defaut). Chrome est arrete par son PID (stderr, CHROME_PID), jamais par son nom d'image.
import { spawn } from 'node:child_process';
import fs from 'node:fs';
import net from 'node:net';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const JEU = 'bandit-manchot-additions-cp';
const LARGEUR = 744;
const HAUTEUR = 551;
const YAML_PAQUET = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '../../../adapters/adapter-content/src/main/resources/contenu/cp-additions-jusqu-a-10.yaml');

function sommeMaximale(texte) {
  const m = /^\s*sommeMaximale:\s*(\d+)\s*$/m.exec(texte);
  if (!m) throw new Error('sommeMaximale absente du paquet');
  return +m[1];
}

function deriverEnsemble(somme) {
  const ensemble = [];
  for (let a = 0; a <= somme; a++) {
    for (let b = 0; a + b <= somme; b++) {
      if (a === 0 && b === 0) continue;
      ensemble.push(`${a} + ${b}`);
    }
  }
  return ensemble;
}

const fin = (code, message) => { console.error(message); process.exit(code); };

const args = process.argv.slice(2);
if (args[0] === '--ensemble') {
  let ensemble;
  try { ensemble = deriverEnsemble(sommeMaximale(fs.readFileSync(args[1] || YAML_PAQUET, 'utf8'))); } catch (e) { fin(1, e.message); }
  console.log(ensemble.join('\n'));
  process.exit(0);
}
if (args.length !== 4 || !/^\d+$/.test(args[1]) || !/^\d+$/.test(args[2])) {
  fin(2, 'Usage : node mesurer-additions-cp.mjs <chrome> <port-de-jeu> <port-de-debogage> <repertoire-de-sortie> | --ensemble [<yaml>]');
}
const [chrome, portJeuTexte, portDebogageTexte, sortieTexte] = args;
const portJeu = +portJeuTexte;
const portDebogage = +portDebogageTexte;
const sortie = path.resolve(sortieTexte);
const PARTIES_MAX = +(process.env.PARTIES_MAX || 300);

let ensemble;
try { ensemble = deriverEnsemble(sommeMaximale(fs.readFileSync(YAML_PAQUET, 'utf8'))); } catch (e) { fin(1, e.message); }

const base = `http://127.0.0.1:${portJeu}`;
try {
  const r = await fetch(base + '/', { signal: AbortSignal.timeout(5000) });
  if (!r.ok) fin(1, `le port de jeu ${portJeu} repond ${r.status} sur / : ce n'est pas le jar attendu`);
} catch {
  fin(1, `rien n'ecoute sur le port de jeu ${portJeu}`);
}

const portLibre = port => new Promise(r => {
  const s = net.createServer();
  s.once('error', () => r(false));
  s.listen(port, '127.0.0.1', () => s.close(() => r(true)));
});
const repondEnHttp = async port => { try { await fetch(`http://127.0.0.1:${port}/json/version`, { signal: AbortSignal.timeout(2000) }); return true; } catch { return false; } };
if (!(await portLibre(portDebogage)) || (await repondEnHttp(portDebogage))) {
  fin(1, `le port de debogage ${portDebogage} est deja occupe : ce navigateur n'est pas le mien, je ne m'y connecte pas`);
}

const sleep = ms => new Promise(r => setTimeout(r, ms));
fs.mkdirSync(sortie, { recursive: true });
const proc = spawn(chrome, ['--headless=new', '--disable-gpu', '--remote-debugging-port=' + portDebogage, '--user-data-dir=' + path.join(sortie, 'profil-chrome'), '--no-first-run', 'about:blank'], { stdio: 'ignore' });
let lancementEchoue = null;
proc.once('error', e => { lancementEchoue = e; });
console.error('CHROME_PID', proc.pid);

let ws = null;
let code = 1;
try {
  let cibles = null;
  for (let i = 0; i < 50 && !lancementEchoue && !cibles; i++) {
    try { const t = await (await fetch(`http://127.0.0.1:${portDebogage}/json`, { signal: AbortSignal.timeout(2000) })).json(); if (t.length) cibles = t; } catch { /* Chrome demarre */ }
    if (!cibles) await sleep(200);
  }
  if (lancementEchoue) throw new Error('Chrome ne se lance pas : ' + lancementEchoue.message);
  if (!cibles) throw new Error(`Chrome ne repond pas sur le port de debogage ${portDebogage}`);
  ws = new WebSocket(cibles.find(t => t.type === 'page').webSocketDebuggerUrl);
  await new Promise(r => { ws.onopen = r; });
  let id = 0;
  const pend = new Map();
  ws.onmessage = e => { const m = JSON.parse(e.data); if (m.id && pend.has(m.id)) { pend.get(m.id)(m); pend.delete(m.id); } };
  const send = (method, params = {}) => new Promise(r => { const i = ++id; pend.set(i, r); ws.send(JSON.stringify({ id: i, method, params })); });
  const ev = async expr => { const r = await send('Runtime.evaluate', { expression: expr, returnByValue: true, awaitPromise: true }); if (r.result.exceptionDetails) throw new Error(JSON.stringify(r.result.exceptionDetails)); return r.result.result.value; };
  await send('Network.enable');
  await send('Page.enable');

  const MES = `(()=>{
 const N=s=>(s||'').replace(/\\s+/g,' ').trim();
 const bas=s=>{const e=document.querySelector(s);return e?+(e.getBoundingClientRect().bottom+scrollY).toFixed(1):null;};
 return {enonce:N((document.querySelector('.enonce')||{}).textContent),
  propositions:[...document.querySelectorAll('.propositions .proposition')].map(p=>N(p.textContent)),
  indiceBas:bas('.indice'),faux:!!document.querySelector('.signal.est-faux'),
  retour:!!document.querySelector('.sortie-pleine'),manche:!!document.querySelector('.cabinet'),
  innerW:innerWidth,innerH:innerHeight,clientW:document.documentElement.clientWidth};})()`;
  const settle = async () => { await sleep(350); await ev(`Promise.all([...document.images].map(i=>i.complete?1:new Promise(r=>{i.onload=i.onerror=r})))`); };
  const attend = async (cond, ms = 4000) => { const t0 = Date.now(); while (Date.now() - t0 < ms) { if (await ev(cond)) return true; await sleep(80); } return false; };
  const cliquer = texte => ev(`(()=>{const N=s=>(s||'').replace(/\\s+/g,' ').trim();const b=[...document.querySelectorAll('.propositions .proposition')].find(p=>N(p.textContent)===${JSON.stringify(texte)});if(!b)return false;b.click();return true;})()`);

  const enonces = Object.fromEntries(ensemble.map(e => [e, []]));
  const horsEnsemble = [];
  const anomalies = [];
  let parties = 0;
  let manches = 0;
  const nonMesures = () => ensemble.filter(e => enonces[e].length === 0);
  while (nonMesures().length > 0 && parties < PARTIES_MAX) {
    parties++;
    await send('Network.clearBrowserCookies');
    await send('Emulation.setDeviceMetricsOverride', { width: LARGEUR, height: HAUTEUR, deviceScaleFactor: 1, mobile: false });
    await send('Page.navigate', { url: base + '/niveaux/CP/matieres/mathematiques' });
    await sleep(900);
    const htmx = await ev(`(()=>{if(!window.htmx)return false;htmx.ajax('POST','/parties/${JEU}',{target:'#jeu',swap:'innerHTML'});return true;})()`);
    if (!htmx) throw new Error('htmx absent de la page de la matiere');
    if (!(await attend(`!!document.querySelector('.cabinet')`))) {
      if (parties === 1) throw new Error(`le jeu ${JEU} ne sert aucune manche`);
      anomalies.push({ partie: parties, probleme: 'pas de manche' });
      continue;
    }
    await settle();
    for (let manche = 0; manche < 12; manche++) {
      const a = await ev(MES);
      if (a.retour || !a.manche) break;
      const lu = /^(\d+) \+ (\d+)$/.exec(a.enonce);
      if (!lu) { anomalies.push({ partie: parties, manche, probleme: 'enonce hors forme', enonce: a.enonce }); break; }
      const bonne = String(+lu[1] + +lu[2]);
      const fausse = a.propositions.find(p => p !== bonne);
      if (!a.propositions.includes(bonne) || fausse === undefined) { anomalies.push({ partie: parties, manche, probleme: 'la bonne reponse ou une fausse manque', enonce: a.enonce, propositions: a.propositions }); break; }
      manches++;
      const enonceNorme = `${+lu[1]} + ${+lu[2]}`;
      if (!(enonceNorme in enonces)) { horsEnsemble.push(a.enonce); }
      await cliquer(fausse);
      await attend(`!!document.querySelector('.signal.est-faux')`);
      await settle();
      const m = await ev(MES);
      if (!m.faux || m.indiceBas === null) {
        anomalies.push({ partie: parties, manche, probleme: m.faux ? 'pas de .indice apres l\'erreur' : 'pas de signal faux apres l\'erreur', enonce: a.enonce });
      } else if (enonceNorme in enonces) {
        enonces[enonceNorme].push({ cote: m.indiceBas, propositions: a.propositions, erronee: fausse, innerW: m.innerW, innerH: m.innerH, clientW: m.clientW });
      }
      const avant = a.enonce + '|' + a.propositions.join('|');
      await cliquer(bonne);
      await attend(`(()=>{const N=s=>(s||'').replace(/\\s+/g,' ').trim();const e=document.querySelector('.enonce');return !!document.querySelector('.sortie-pleine')||(e&&(N(e.textContent)+'|'+[...document.querySelectorAll('.propositions .proposition')].map(p=>N(p.textContent)).join('|'))!==${JSON.stringify(avant)})})()`);
      await settle();
    }
    console.error('partie', parties, 'enonces mesures', ensemble.length - nonMesures().length + '/' + ensemble.length);
  }

  const manquants = nonMesures();
  const plusieursCotes = ensemble.filter(e => new Set(enonces[e].map(o => o.cote)).size > 1);
  const cotes = ensemble.flatMap(e => enonces[e].map(o => o.cote));
  const complet = manquants.length === 0 && horsEnsemble.length === 0;
  const resultat = {
    methode: { jeu: JEU, largeur: LARGEUR, hauteur: HAUTEUR, mesure: 'bas de .indice, getBoundingClientRect().bottom + scrollY, apres une erreur', ensembleDeriveDe: 'sommeMaximale de cp-additions-jusqu-a-10.yaml', sommeMaximale: sommeMaximale(fs.readFileSync(YAML_PAQUET, 'utf8')) },
    complet,
    parties,
    manches,
    ensemble,
    nonMesures: manquants,
    horsEnsemble,
    enoncesAvecPlusieursCotes: plusieursCotes,
    coteMin: cotes.length ? Math.min(...cotes) : null,
    coteMax: cotes.length ? Math.max(...cotes) : null,
    anomalies,
    enonces,
  };
  fs.writeFileSync(path.join(sortie, 'additions-cp-744.json'), JSON.stringify(resultat, null, 1));
  console.error('enonces', ensemble.length, 'non mesures', manquants.length, 'hors ensemble', horsEnsemble.length, 'avec plusieurs cotes', plusieursCotes.length, 'cote min', resultat.coteMin, 'max', resultat.coteMax, 'anomalies', anomalies.length);
  code = complet ? 0 : 3;
} catch (e) {
  console.error('ERREUR', e.message);
  code = 1;
} finally {
  try { if (ws) { ws.send(JSON.stringify({ id: 1000000, method: 'Browser.close' })); await sleep(500); ws.close(); } } catch { /* deja ferme */ }
  proc.kill();
}
process.exit(code);
