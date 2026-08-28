# -*- coding: utf-8 -*-
"""Audit du paquet ENTIER d'un jeu servi, ou un item est identifie par son TRIPLET de propositions
et non par la chaine de son enonce.

Pourquoi cet outil existe, et c'est un motif mesure et non une preference.
auditer-un-paquet-entier.py identifie un item par son enonce et indexe les bonnes reponses par
cette meme chaine. Un paquet qui repete une chaine d'enonce en fait donc confondre plusieurs :
cp-comparer porte DIX chaines d'enonce distinctes pour DIX-HUIT items, quatre d'entre elles servant
trois items chacune. L'outil y clique une bonne reponse qui n'appartient pas a l'item affiche et
s'arrete en erreur (execution du 2026-08-27, nadia-prof, audit de S21-11).

Le mode de defaillance est ici bruyant, donc visible. RIEN NE GARANTIT QU'IL LE SOIT TOUJOURS :
une table qui confond deux items peut aussi rendre un chiffre plutot que de s'arreter, et c'est la
famille de defaut que ce depot a deja sanctionnee plusieurs fois. La cle employee ici est le couple
(enonce, triplet trie des propositions) ; elle est unique des lors que deux items ne partagent pas
le meme triplet sous le meme enonce.

Ce script n'est pas un garde-fou de CI et ne s'execute pas dans "mvn verify" : il a besoin d'un
navigateur, que rien dans le depot ne peut garantir (meme limite que outils/mesurer-rendu.sh).

Prerequis nommes, jamais leur emplacement (ADR-0010) : un Chrome designe par la variable CHROME,
l'application servie, le module python "websocket-client", et la variable PAQUET qui designe le
fichier de contenu servi.

PORT_DEV designe le port de deverminage. Tant que la story S21-1 n'a pas ete confirmee, l'outillage
de ce repertoire s'attache au premier navigateur qui repond sur ce port sans verifier que c'est le
sien : donner ici un port distinct est la seule protection disponible.

Usage :
  CHROME=... PAQUET=... [PORT_DEV=9391] python squad/outils/nadia-prof/auditer-un-paquet-par-triplet.py       <url_de_base> <id_du_jeu> <nombre_d_items_du_paquet> <json_de_sortie>
"""
import importlib.util, json, os, sys, tempfile, time
_d = os.path.join(os.getcwd(), "squad", "outils", "nadia-prof")
_s = importlib.util.spec_from_file_location("blr", os.path.join(_d, "balayage-largeur-rangee.py"))
blr = importlib.util.module_from_spec(_s); _s.loader.exec_module(blr)
_s2 = importlib.util.spec_from_file_location("aud", os.path.join(_d, "auditer-un-paquet-entier.py"))
# on ne charge pas aud (il a un main), on recopie ses constantes
LIRE = open(os.path.join(_d, "auditer-un-paquet-entier.py"), encoding="utf-8").read()
LIRE = LIRE.split('LIRE = r"""',1)[1].split('"""',1)[0]
CLIC = ("Array.prototype.slice.call(document.querySelectorAll('.proposition'))"
        ".filter(function(b){return b.textContent.trim()===%s;})[0].click(); true;")

def items_du_paquet(chemin):
    """[(enonce, bonneReponse, [distracteurs])] dans l'ordre du fichier."""
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
            cur[2] = [x.strip().strip('"') for x in b.strip("[]").split('", "')] if b.startswith("[") else []
            cur[2] = [x.strip().strip('"') for x in b.strip("[] ").split(",")]
    if cur: out.append(cur)
    return out

def main():
    base, jeu, attendus, sortie = sys.argv[1].rstrip("/"), sys.argv[2], int(sys.argv[3]), sys.argv[4]
    paquet = items_du_paquet(os.environ["PAQUET"])
    par_triplet = {}
    for e, b, ds in paquet:
        par_triplet[frozenset([b] + ds)] = (e, b)
    nav = blr.Navigateur(os.environ["CHROME"], tempfile.mkdtemp(prefix="nad2-"), int(os.environ.get("PORT_DEV","9391")))
    vus, parties = {}, 0
    try:
        nav.appeler("Page.navigate", {"url": base + "/"}); time.sleep(2.0)
        while len(vus) < attendus and parties < 40:
            parties += 1
            ouvert = False
            for _ in range(3):
                ouvert = nav.evaluer("(function(){var l=document.querySelector('[hx-post*=\"%s\"]');"
                                     "if(l){l.click();return true;}return false;})();" % jeu)
                if ouvert: break
                nav.appeler("Page.navigate", {"url": base + "/"}); time.sleep(2.0)
            if not ouvert: break
            time.sleep(1.2)
            for _ in range(14):
                etat = nav.evaluer(LIRE)
                if etat.get("rangees") is None: break
                cle = frozenset(etat["propositions"])
                enonce_yaml, bonne = par_triplet.get(cle, (None, None))
                if bonne is None:
                    sys.stderr.write("triplet inconnu: %r\n" % (etat["propositions"],)); break
                sig = "%s || %s" % (etat["enonce"], " / ".join(sorted(etat["propositions"])))
                if sig not in vus:
                    fausses = [p for p in etat["propositions"] if p != bonne]
                    mesures = {}
                    if fausses:
                        nav.evaluer(CLIC % json.dumps(fausses[0])); time.sleep(0.35)
                        mesures["apresUnEchec"] = nav.evaluer(LIRE)
                    mesures["enonceYaml"] = enonce_yaml
                    mesures["bonneReponse"] = bonne
                    vus[sig] = mesures
                nav.evaluer(CLIC % json.dumps(bonne)); time.sleep(0.35)
    finally:
        nav.fermer()
    rap = {"jeu": jeu, "viewport": nav.viewport_lu, "pli": nav.viewport_lu[1],
           "itemsVus": len(vus), "itemsAttendus": attendus, "parties": parties, "items": vus}
    json.dump(rap, open(sortie, "w", encoding="utf-8"), ensure_ascii=False, indent=1, sort_keys=True,
              default=lambda o: sorted(o) if isinstance(o, frozenset) else str(o))
    for sig, m in sorted(vus.items()):
        a = m.get("apresUnEchec") or {}
        print("  %-58s rangees=%s somme=%-7s lignesEnonce=%s lignesIndice=%s basIndice=%s defilement=%s"
              % (sig[:58], a.get("rangees"), a.get("sommeLibelles"), a.get("lignesEnonce"),
                 a.get("lignesIndice"), a.get("basIndice"), a.get("defilement")))
    print("%d items vus sur %d attendus -> %s" % (len(vus), attendus, sortie))
main()
