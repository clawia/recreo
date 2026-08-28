# -*- coding: utf-8 -*-
"""Compte du pli sur le CATALOGUE ENTIER, item par item, dans deux etats de la feuille de style.

Pourquoi cet outil existe, et c'est un motif mesure et non une preference. Le compte de "26 items
fermes par S21-2" a ete etabli en jouant UNE partie par jeu. Une partie tire dix manches sans
remise (ListContentSource.next) : sur des paquets de seize a quatre-vingt-dix-neuf items, elle en
montre dix. Le chiffre portait donc sur 167 items vus sur 428, et il a circule dans cinq documents
comme s'il etait un compte. Un echantillon presente comme un compte est un chiffre sans sa methode.

Cet outil rejoue jusqu'a avoir vu TOUS les items annonces par chaque paquet enumere, et mesure
chaque item dans deux etats du MEME arbre, sans mutation d'aucun fichier : l'etat SERVI, et un etat
"avant s21" obtenu en reinjectant les trois valeurs d'avant (score, proposition, sorties).

Ce que l'outil ne peut pas faire, et qui doit etre dit a cote de son chiffre :
  - il ne mesure pas les paquets NON ENUMERES (type "additions-bornees", dont les items sont
    generes) : ceux-la sont ignores et ne sont couverts par rien ;
  - les trois declarations sont reinjectees ENSEMBLE. L'ecart qu'il rend est donc celui du travail
    vertical de s21 dans son entier, PAS celui de S21-2 seul. Ne pas attribuer ce chiffre a S21-2.

Ce script n'est pas un garde-fou de CI et ne s'execute pas dans "mvn verify" : il a besoin d'un
navigateur, que rien dans le depot ne peut garantir.

Prerequis nommes, jamais leur emplacement (ADR-0010) : un Chrome designe par la variable CHROME,
l'application servie, le module python "websocket-client". PORT_DEV designe le port de deverminage
(voir la limite de S21-1 rappelee dans les autres outils de ce repertoire).

Usage :
  CHROME=... [PORT_DEV=9401] python squad/outils/nadia-prof/compter-le-pli-du-catalogue-entier.py       <url_de_base> <json_de_sortie>
"""
import importlib.util, json, os, sys, tempfile, time
_d = os.path.join(os.getcwd(), "squad", "outils", "nadia-prof")
_s = importlib.util.spec_from_file_location("blr", os.path.join(_d, "balayage-largeur-rangee.py"))
blr = importlib.util.module_from_spec(_s); _s.loader.exec_module(blr)
SRC = open(os.path.join(_d, "auditer-un-paquet-entier.py"), encoding="utf-8").read()
LIRE = SRC.split('LIRE = r"""',1)[1].split('"""',1)[0]
POSER = SRC.split('POSER = """',1)[1].split('"""',1)[0]
CLIC = ("Array.prototype.slice.call(document.querySelectorAll('.proposition'))"
        ".filter(function(b){return b.textContent.trim()===%s;})[0].click(); true;")
AVANT_S21 = (".score{margin:0.5rem 0}.proposition{padding:0.75rem 1.25rem}"
             ".lien-accueil,.retour-accueil{padding:0.6rem 1.1rem}")

def items_du_paquet(chemin):
    out, cur = [], None
    for l in open(chemin, encoding="utf-8"):
        d = l.strip()
        if d.startswith("- enonce:"):
            if cur: out.append(cur)
            cur = [d.split(":",1)[1].strip().strip('"'), None, []]
        elif cur and d.startswith("bonneReponse:"):
            cur[1] = d.split(":",1)[1].strip().strip('"')
        elif cur and d.startswith("distracteurs:"):
            b = d.split(":",1)[1].strip()
            cur[2] = [x.strip().strip('"').strip("'") for x in b.strip("[] ").split('", "')] if '", "' in b \
                     else [x.strip().strip('"').strip("'") for x in b.strip("[] ").split(",")]
    if cur: out.append(cur)
    return out

def main():
    base = sys.argv[1].rstrip("/"); sortie = sys.argv[2]
    cat = open("adapters/adapter-content/src/main/resources/catalogue.yaml", encoding="utf-8").read()
    import re
    jeux = re.findall(r"- id: (\S+).*?paquet: (\S+)", cat, re.S)
    nav = blr.Navigateur(os.environ["CHROME"], tempfile.mkdtemp(prefix="cat-"), int(os.environ.get("PORT_DEV","9401")))
    rap = {"viewport": nav.viewport_lu, "pli": nav.viewport_lu[1], "date": "2026-08-27", "jeux": {}}
    try:
        for jeu, paquet in jeux:
            f = "adapters/adapter-content/src/main/resources/contenu/%s.yaml" % paquet
            if not os.path.exists(f): continue
            paq = items_du_paquet(f)
            if not paq or paq[0][1] is None:
                rap["jeux"][jeu] = {"nonEnumere": True}; print("%-42s paquet non enumere, ignore" % jeu); continue
            cles = {}
            for e, b, ds in paq:
                cles[(e, tuple(sorted([b]+ds)))] = b
            attendus = len(cles); vus = {}; parties = 0
            nav.appeler("Page.navigate", {"url": base + "/niveaux/CP"}); time.sleep(1.5)
            while len(vus) < attendus and parties < max(6, attendus):
                parties += 1; ouvert = False
                for _ in range(4):
                    ouvert = nav.evaluer("(function(){var l=document.querySelector('[hx-post*=\"%s\"]');"
                                         "if(l){l.click();return true;}return false;})();" % jeu)
                    if ouvert: break
                    for u in ("/niveaux/CP", "/niveaux/CM1"):
                        nav.appeler("Page.navigate", {"url": base + u}); time.sleep(1.4)
                        if nav.evaluer("!!document.querySelector('[hx-post*=\"%s\"]');" % jeu): break
                if not ouvert: break
                time.sleep(1.0)
                for _ in range(12):
                    etat = nav.evaluer(LIRE)
                    if etat.get("rangees") is None: break
                    k = (etat["enonce"], tuple(sorted(etat["propositions"])))
                    bonne = cles.get(k)
                    if bonne is None: break
                    sig = "%s || %s" % k
                    if sig not in vus:
                        fausses = [p for p in etat["propositions"] if p != bonne]
                        m = {}
                        if fausses:
                            nav.evaluer(CLIC % json.dumps(fausses[0])); time.sleep(0.3)
                            for etq, regle in (("servi", ""), ("avantS21", AVANT_S21)):
                                nav.evaluer(POSER.replace("TEXTE", json.dumps(regle))); time.sleep(0.15)
                                m[etq] = nav.evaluer(LIRE)
                            nav.evaluer(POSER.replace("TEXTE", json.dumps(""))); time.sleep(0.1)
                        vus[sig] = m
                    nav.evaluer(CLIC % json.dumps(bonne)); time.sleep(0.3)
            rap["jeux"][jeu] = {"attendus": attendus, "vus": len(vus), "parties": parties, "items": vus}
            pli = nav.viewport_lu[1]
            def sous(etq):
                return sum(1 for m in vus.values() if m.get(etq) and (m[etq].get("basIndice") or 0) > pli)
            print("%-42s vus %3d/%3d  sous le pli : servi %2d  avant-s21 %2d"
                  % (jeu, len(vus), attendus, sous("servi"), sous("avantS21")))
            json.dump(rap, open(sortie,"w",encoding="utf-8"), ensure_ascii=False, indent=1, sort_keys=True)
    finally:
        nav.fermer()
    json.dump(rap, open(sortie,"w",encoding="utf-8"), ensure_ascii=False, indent=1, sort_keys=True)
    print("ecrit " + sortie)
main()
