# -*- coding: utf-8 -*-
"""Cherche, dans un navigateur reel, les textes lus par un enfant ou un signe de ponctuation double
(? ! ; :) se retrouve SEUL sur une ligne quand le texte se replie.

Pourquoi cet outil existe, et c'est un motif mesure et non une preference. La typographie francaise
demande une espace insecable devant un signe double. Prise comme principe, la regle est
indiscutable et sans portee : l'insecable ne change RIEN au sens, elle ne change que le droit de
couper en fin de ligne. La question qui decide n'est donc pas "la regle est-elle juste" mais "un
signe orphelin est-il seulement possible sur un ecran servi, et sur lesquels". Une regle qui ne
produit aucun effet visible n'a pas le meme poids qu'une regle qui en produit.

Ce que l'outil fait : il extrait les textes que l'enfant lit (enonce, bonneReponse, distracteurs,
indice, explication, titre, consigne - commentaires YAML EXCLUS), construit une replique de la boite
de rendu, la charge dans un navigateur reel, et rend pour chaque texte le contenu de chaque ligne
rendue. Un texte est signale quand sa derniere ligne ne contient QUE de la ponctuation.

Ce que l'outil ne fait pas, et qui doit etre dit a cote de son chiffre :
  - il reproduit la BOITE du document servi, pas le document. Les valeurs de la replique (696 px de
    largeur offerte, 700 32px pour .enonce, 400 20px pour .consigne) sont LUES du document servi et
    non supposees, mais la chaine de rendu n'est pas la meme. Confronter le resultat a un releve du
    document servi (auditer-un-paquet-entier.py) avant de s'appuyer dessus ;
  - il mesure a UN viewport. 360 px n'est pas couvert, et le nombre d'orphelins y est mecaniquement
    plus eleve, jamais moindre ;
  - un indice se rend a 20px et un enonce a 32px : confondre les deux champs fabrique des faux
    positifs. L'outil rend le champ d'origine pour que la verification soit possible.

Ce script n'est pas un garde-fou de CI et ne s'execute pas dans "mvn verify" : il a besoin d'un
navigateur, que rien dans le depot ne peut garantir.

Prerequis nommes, jamais leur emplacement (ADR-0010) : un Chrome designe par la variable CHROME, le
module python "websocket-client". PORT_DEV designe le port de deverminage (voir la limite de S21-1
rappelee dans les autres outils de ce repertoire).

Usage, depuis la racine du depot :
  CHROME=... [PORT_DEV=9451] python squad/outils/nadia-prof/chercher-un-signe-orphelin.py <json_de_sortie>
"""
import glob, importlib.util, io, json, os, re, sys, tempfile, time

_d = os.path.join(os.getcwd(), "squad", "outils", "nadia-prof")
_s = importlib.util.spec_from_file_location("blr", os.path.join(_d, "balayage-largeur-rangee.py"))
blr = importlib.util.module_from_spec(_s); _s.loader.exec_module(blr)

CONTENU = os.path.join("adapters", "adapter-content", "src", "main", "resources")
CHAMP = re.compile(r'^\s*(?:- )?(enonce|bonneReponse|indice|explication|titre|consigne):\s*"(.*)"\s*$')
DISTR = re.compile(r'^\s*distracteurs:\s*\[(.*)\]\s*$')
SIGNE = re.compile(r'[ \u00a0\u202f][?!;:]')
PONCT = re.compile(r'^[?!;:.]+$')
FONT = ('system-ui,-apple-system,"Segoe UI",Roboto,"Helvetica Neue","Noto Sans",'
        '"Liberation Sans",Arial,sans-serif')

LIRE = r"""
(function(){
  var out=[];
  document.querySelectorAll('p[data-id]').forEach(function(p){
    var lignes={}, ordre=[];
    p.querySelectorAll('span').forEach(function(s){
      var r=s.getBoundingClientRect();
      if(r.width===0&&r.height===0) return;
      var t=Math.round(r.top*10)/10;
      if(!(t in lignes)){lignes[t]=[];ordre.push(t);}
      lignes[t].push(s.textContent);
    });
    ordre.sort(function(a,b){return a-b;});
    out.push({id:+p.getAttribute('data-id'), nb:ordre.length,
              lignes:ordre.map(function(t){return lignes[t].join('');})});
  });
  return out;
})();
"""

def textes_lus_par_un_enfant():
    """[(fichier, champ, texte)] des textes portant une espace avant un signe, commentaires exclus."""
    out, vus = [], set()
    fichiers = [os.path.join(CONTENU, "catalogue.yaml")]
    fichiers += sorted(glob.glob(os.path.join(CONTENU, "contenu", "*.yaml")))
    for f in fichiers:
        for ligne in io.open(f, encoding="utf-8"):
            if ligne.lstrip().startswith("#"):
                continue
            trouves = []
            m = CHAMP.match(ligne.rstrip("\n"))
            if m:
                trouves.append((m.group(1), m.group(2)))
            d = DISTR.match(ligne.rstrip("\n"))
            if d:
                trouves += [("distracteurs", x) for x in re.findall(r'"([^"]*)"', d.group(1))]
            for champ, texte in trouves:
                if SIGNE.search(texte) and texte not in vus:
                    vus.add(texte)
                    out.append((os.path.basename(f), champ, texte))
    return out

def ecrire_replique(items, chemin):
    h = ['<!doctype html><meta charset="utf-8"><style>',
         'html{font-size:16px}body{margin:0}.boite{width:696px}',
         '.enonce{font-family:%s;font-size:2rem;font-weight:bold;margin:0 0 .25rem}' % FONT,
         '.consigne{font-family:%s;font-size:1.25rem;font-weight:400;margin:0}' % FONT,
         '</style><body>']
    for i, (f, champ, t) in enumerate(items):
        cls = "consigne" if champ in ("consigne", "titre") else "enonce"
        jetons = re.findall(r"\S+|\s+", t)
        spans = "".join('<span>%s</span>' % x.replace("&", "&amp;").replace("<", "&lt;")
                        for x in jetons)
        h.append('<div class="boite"><p class="%s" data-id="%d">%s</p></div>' % (cls, i, spans))
    h.append("</body>")
    io.open(chemin, "w", encoding="utf-8").write("\n".join(h))

def main():
    sortie = sys.argv[1]
    items = textes_lus_par_un_enfant()
    dossier = tempfile.mkdtemp(prefix="orph-")
    page = os.path.join(dossier, "replique.html")
    ecrire_replique(items, page)
    nav = blr.Navigateur(os.environ["CHROME"], tempfile.mkdtemp(prefix="orph-profil-"),
                         int(os.environ.get("PORT_DEV", "9451")))
    try:
        nav.appeler("Page.navigate", {"url": "file:///" + page.replace("\\", "/")})
        time.sleep(2.5)
        res = {r["id"]: r for r in nav.evaluer(LIRE)}
    finally:
        nav.fermer()
    replie, orphelins = 0, []
    for i, (f, champ, t) in enumerate(items):
        r = res.get(i)
        if not r or r["nb"] < 2:
            continue
        replie += 1
        derniere = (r["lignes"][-1] or "").strip()
        if derniere and PONCT.match(derniere):
            orphelins.append({"fichier": f, "champ": champ, "texte": t, "lignes": r["lignes"]})
    rapport = {"viewport": nav.viewport_lu, "textesMesures": len(items),
               "textesQuiSeReplient": replie, "signesOrphelins": len(orphelins),
               "orphelins": orphelins}
    json.dump(rapport, io.open(sortie, "w", encoding="utf-8"), ensure_ascii=False, indent=1)
    print("viewport lu du navigateur : %s" % (nav.viewport_lu,))
    print("textes lus par un enfant portant une espace avant un signe : %d" % len(items))
    print("dont se replient a 696 px : %d" % replie)
    print("dont un signe reste SEUL en fin de ligne : %d" % len(orphelins))
    par = {}
    for o in orphelins:
        par[o["fichier"]] = par.get(o["fichier"], 0) + 1
    for f, n in sorted(par.items(), key=lambda x: -x[1]):
        print("   %-34s %2d" % (f, n))
    print("ecrit " + sortie)

main()
