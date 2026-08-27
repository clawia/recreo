#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""Audit du paquet ENTIER d'un jeu servi, item par item, et non d'une partie de dix manches.

Pourquoi cet outil existe, et c'est un motif mesure et non une preference. Le releve du pli sur
lequel tout le chantier REP-8 s'appuie
(squad/conformite/releves-s21/s21-pli-du-catalogue-744x551.json) a ete produit en jouant **une
partie par jeu**. Une partie tire dix manches sans remise (ListContentSource.next) : sur un paquet
de seize, vingt-quatre ou vingt-huit items, elle en montre dix. Le chiffre de "56 items sous le
pli" porte donc sur 167 items vus sur les 428 du catalogue, et un paquet peut cacher sous le pli
des items qu'aucune partie de ce releve n'a tires. auditer-paquet.py rend le meme service mais
s'arrete lui aussi a ce que les parties lui ont montre ; celui-ci rejoue jusqu'a avoir vu le
nombre d'items annonce.

Ce qu'il rend par item, au repos puis apres un echec, et deux fois : avec et sans les deux
declarations de S21-2 injectees.

  - la HAUTEUR rendue de l'enonce, et non le nombre de rectangles de sa boite. Le piege est
    nomme ici parce qu'il a deja fait conclure a tort : .enonce est un <p>, donc un element de
    bloc, et getClientRects().length y vaut 1 quel que soit le nombre de lignes du texte. Un
    enonce de deux lignes rend 96 px de hauteur et un seul rectangle. La hauteur tranche, le
    compte de rectangles ne tranche rien ;
  - le nombre de rangees occupees par les propositions, leur nombre, leur texte, et la LARGEUR
    RENDUE de chacun avec la police reellement calculee du bouton, plus leur somme - c'est cette
    somme, et non le libelle le plus large, qui decide du repli ;
  - le bas du bloc de verdict et le bas de la boite d'indice, et le defilement.

Ce script n'est pas un garde-fou de CI et ne s'execute pas dans "mvn verify" : il a besoin d'un
navigateur, que rien dans le depot ne peut garantir (meme limite qu'outils/mesurer-rendu.sh).

Prerequis nommes, jamais leur emplacement (ADR-0010) : un Chrome designe par la variable CHROME ou
sur le PATH, l'application servie, le module python "websocket-client", et la variable PAQUET qui
designe le fichier de contenu servi - elle sert a repondre juste pour avancer sans user les trois
essais de M5.

PORT_DEV designe le port de deverminage. Tant que la story S21-1 n'a pas ete confirmee,
l'outillage de ce repertoire s'attache au premier navigateur qui repond sur ce port sans verifier
que c'est le sien : donner ici un port distinct est la seule protection disponible quand une autre
mesure tourne sur le meme poste.

Usage :
  CHROME=... PAQUET=... [PORT_DEV=9361] python squad/outils/nadia-prof/auditer-un-paquet-entier.py       <url_de_base> <id_du_jeu> <nombre_d_items_du_paquet> <json_de_sortie>
"""
import importlib.util, json, os, shutil, sys, tempfile, time

_d = os.path.join(os.getcwd(), "squad", "outils", "nadia-prof")
_spec = importlib.util.spec_from_file_location("blr", os.path.join(_d, "balayage-largeur-rangee.py"))
blr = importlib.util.module_from_spec(_spec)
_spec.loader.exec_module(blr)

S21_2 = ".score{margin:0.25rem 0}.proposition{padding:0.5rem 1.25rem}"

POSER = """
(function(t){
  var n = document.getElementById('s21-2-nadia');
  if (!n) { n = document.createElement('style'); n.id = 's21-2-nadia'; document.head.appendChild(n); }
  n.textContent = t;
  return n.textContent.length;
})(TEXTE);
"""

LIRE = r"""
(function(){
  var r = function(v){ return (v===null||v===undefined) ? null : Math.round(v*100)/100; };
  var q = function(s){ return document.querySelector(s); };
  var rg = q('.propositions');
  if (!rg) { return {rangees:null, fin: !!q('.fin-de-partie')}; }
  var b = Array.prototype.slice.call(rg.querySelectorAll('.proposition'));
  var hauts = {}; b.forEach(function(x){ hauts[r(x.getBoundingClientRect().top)] = 1; });
  var en = q('.enonce'); var co = q('.consigne');
  var ind = q('.message-jeu .indice');
  var bas = function(n){ return n ? r(n.getBoundingClientRect().bottom) : null; };
  var c = document.createElement('canvas').getContext('2d');
  var sp = getComputedStyle(b[0]);
  c.font = sp.fontWeight + ' ' + sp.fontSize + ' ' + sp.fontFamily;
  var largeurs = b.map(function(x){ return r(c.measureText(x.textContent.trim()).width); });
  return {
    enonce: en ? en.textContent.trim() : null,
    lignesEnonce: en ? en.getClientRects().length : null,
    hauteurEnonce: en ? r(en.getBoundingClientRect().height) : null,
    lignesConsigne: co ? co.getClientRects().length : null,
    lignesIndice: ind ? ind.getClientRects().length : null,
    policeProposition: sp.fontSize,
    rangees: Object.keys(hauts).length,
    nbPropositions: b.length,
    propositions: b.map(function(x){ return x.textContent.trim(); }),
    largeursPropositions: largeurs,
    sommeLibelles: r(largeurs.reduce(function(a,x){ return a+x; }, 0)),
    hauteurBouton: r(b[0].getBoundingClientRect().height),
    basVerdict: bas(q('.message-jeu .verdict')),
    basIndice: bas(ind),
    texteIndice: ind ? ind.textContent.trim() : null,
    defilement: window.scrollY
  };
})();
"""

CLIC = ("Array.prototype.slice.call(document.querySelectorAll('.proposition'))"
        ".filter(function(b){return b.textContent.trim()===%s;})[0].click(); true;")


def main():
    base, jeu, attendus, sortie = sys.argv[1].rstrip("/"), sys.argv[2], int(sys.argv[3]), sys.argv[4]
    bonnes = blr.lire_bonnes_reponses(os.environ.get("PAQUET"))
    binaire = os.environ.get("CHROME") or shutil.which("chrome")
    profil = tempfile.mkdtemp(prefix="a16-nadia-")
    nav = blr.Navigateur(binaire, profil, int(os.environ.get("PORT_DEV", "9361")))
    vus, parties = {}, 0
    try:
        nav.appeler("Page.navigate", {"url": base + "/"})
        time.sleep(2.0)
        while len(vus) < attendus and parties < 30:
            parties += 1
            ouvert = False
            for tentative in range(3):
                ouvert = nav.evaluer(
                    "(function(){var l=document.querySelector('[hx-post*=\"%s\"]');"
                    " if(l){l.click(); return true;} return false;})();" % jeu)
                if ouvert:
                    break
                nav.appeler("Page.navigate", {"url": base + "/"})
                time.sleep(2.0)
            if not ouvert:
                break
            time.sleep(1.2)
            for _ in range(14):
                etat = nav.evaluer(LIRE)
                if etat.get("rangees") is None:
                    break
                enonce = etat["enonce"]
                bonne = bonnes.get(enonce)
                if enonce not in vus:
                    fausses = [p for p in etat["propositions"] if p != (bonne or "")]
                    mesures = {}
                    for etiquette, regle in (("sans", ""), ("avec", S21_2)):
                        nav.evaluer(POSER.replace("TEXTE", json.dumps(regle)))
                        time.sleep(0.2)
                        mesures[etiquette] = nav.evaluer(LIRE)
                    if fausses:
                        nav.evaluer(CLIC % json.dumps(fausses[0]))
                        time.sleep(0.8)
                    apres = {}
                    for etiquette, regle in (("sans", ""), ("avec", S21_2)):
                        nav.evaluer(POSER.replace("TEXTE", json.dumps(regle)))
                        time.sleep(0.2)
                        apres[etiquette] = nav.evaluer(LIRE)
                    nav.evaluer(POSER.replace("TEXTE", json.dumps("")))
                    vus[enonce] = {"repos": mesures, "apresUnEchec": apres}
                    a = apres["sans"]
                    sys.stderr.write(
                        "  %-42s lignesEnonce=%s rangees=%s n=%s somme=%s indice=%s | avecS21-2 indice=%s\n"
                        % (enonce[:42], a["lignesEnonce"], a["rangees"], a["nbPropositions"],
                           a["sommeLibelles"], a["basIndice"], apres["avec"]["basIndice"]))
                if bonne:
                    nav.evaluer(CLIC % json.dumps(bonne))
                else:
                    nav.evaluer("document.querySelector('.proposition').click(); true;")
                time.sleep(0.7)
            nav.evaluer("var s=document.querySelector('[data-role=sortie]'); if(s){s.click();} true;")
            time.sleep(1.0)
    finally:
        nav.fermer()
        shutil.rmtree(profil, ignore_errors=True)
    open(sortie, "w", encoding="utf-8").write(json.dumps(
        {"jeu": jeu, "viewport": [744, 551], "pli": 551, "parties": parties,
         "itemsVus": len(vus), "items": vus}, ensure_ascii=False, indent=2, sort_keys=True))
    print("ecrit " + sortie + " (%d items)" % len(vus))


main()
