# -*- coding: utf-8 -*-
"""SOR-4 joue : la sortie "Les jeux" est-elle visible, lisible et pressable DU PREMIER COUP ?

Pourquoi cet outil existe. SOR-1 a SOR-3 se verifient dans la feuille de style et au pixel, et
ines-design les a verifiees. SOR-4 - "la sortie reste atteignable du premier coup" - ne se verifie
qu'en pressant : nadia-prof avait ecrit qu'elle la fermerait en jouant, et un garde-fou vert sur la
declaration ne dit rien de ce qu'un enfant atteint.

Ce que l'outil lit et fait, sur chaque jeu et dans deux etats (en cours de manche, apres un echec) :
  - la boite rendue de la sortie, son rembourrage calcule, sa police, sa bordure et son min-height ;
  - si elle tient ENTIEREMENT dans le viewport, et le defilement necessaire pour l'atteindre ;
  - si son CENTRE est libre : elementFromPoint au centre exact doit rendre la sortie elle-meme, et
    non un element qui la recouvrirait. Une sortie visible mais recouverte n'est pas atteignable ;
  - la distance au bord du bouton de reponse le plus proche ;
  - puis il PRESSE ce centre, une fois, et rend l'ecran obtenu ; puis presse la sortie de l'ecran de
    confirmation, une fois, et compte les jeux revenus. Le passage par la confirmation est voulu
    (ADR-0017, S14-1) : ce n'est pas un appui rate.

Limite a nommer partout ou ce chiffre sert : c'est un CLIC PROGRAMME AU CENTRE GEOMETRIQUE de la
boite, a un seul viewport. Il ne dit rien de 360 px ni d'un doigt d'enfant qui vise mal.

Ce script n'est pas un garde-fou de CI et ne s'execute pas dans "mvn verify".

Prerequis nommes, jamais leur emplacement (ADR-0010) : un Chrome designe par la variable CHROME,
l'application servie, le module python "websocket-client". PORT_DEV designe le port de deverminage.

Usage :
  CHROME=... [PORT_DEV=9393] python squad/outils/nadia-prof/presser-la-sortie.py       <url_de_base> <id_du_jeu> [<id_du_jeu> ...] <json_de_sortie>
"""
import importlib.util, json, os, sys, tempfile, time
_d = os.path.join(os.getcwd(), "squad", "outils", "nadia-prof")
_s = importlib.util.spec_from_file_location("blr", os.path.join(_d, "balayage-largeur-rangee.py"))
blr = importlib.util.module_from_spec(_s); _s.loader.exec_module(blr)

LIRE_SORTIE = r"""
(function(){
  var r=function(v){return v===null||v===undefined?null:Math.round(v*100)/100;};
  var s=document.querySelector('.retour-accueil') || document.querySelector('.lien-accueil');
  if(!s) return {trouvee:false, url:location.pathname};
  var b=s.getBoundingClientRect(); var c=getComputedStyle(s);
  var cx=b.left+b.width/2, cy=b.top+b.height/2;
  var au=document.elementFromPoint(cx,cy);
  var dedans=!!(au && (au===s || s.contains(au) || au.contains(s)));
  // plus proche bord d'une proposition
  var d=null;
  Array.prototype.slice.call(document.querySelectorAll('.proposition')).forEach(function(p){
    var q=p.getBoundingClientRect();
    var dx=Math.max(q.left-b.right, b.left-q.right, 0);
    var dy=Math.max(q.top-b.bottom, b.top-q.bottom, 0);
    var e=Math.sqrt(dx*dx+dy*dy); if(d===null||e<d) d=e;
  });
  return {trouvee:true, classe:s.className, texte:s.textContent.trim(),
    haut:r(b.top), bas:r(b.bottom), gauche:r(b.left), droite:r(b.right),
    largeur:r(b.width), hauteur:r(b.height), police:c.fontSize, rembourrage:c.padding,
    minHeight:c.minHeight, bordure:c.borderTopWidth,
    entierementDansLeViewport: b.top>=0 && b.bottom<=window.innerHeight && b.left>=0 && b.right<=window.innerWidth,
    centreLibre:dedans, elementAuCentre: au?au.className||au.tagName:null,
    distanceAuPlusProcheBouton:r(d), defilement:window.scrollY, url:location.pathname};
})();
"""
CLIC_CENTRE = r"""
(function(){
  var s=document.querySelector('.retour-accueil') || document.querySelector('.lien-accueil');
  var b=s.getBoundingClientRect();
  var e=document.elementFromPoint(b.left+b.width/2, b.top+b.height/2);
  if(!e) return {ok:false};
  e.click(); return {ok:true, cible:e.className||e.tagName};
})();
"""
def main():
    base = sys.argv[1].rstrip("/"); jeux = sys.argv[2:-1]; sortie = sys.argv[-1]
    nav = blr.Navigateur(os.environ["CHROME"], tempfile.mkdtemp(prefix="sor4-"), int(os.environ.get("PORT_DEV","9393")))
    rap = {"viewport": nav.viewport_lu, "date": "2026-08-27", "cas": []}
    try:
        for jeu in jeux:
            for etape in ("manche1", "apres-un-echec"):
                nav.appeler("Page.navigate", {"url": base + "/niveaux/CP"}); time.sleep(2.0)
                ouvert=False
                for _ in range(4):
                    ouvert = nav.evaluer("(function(){var l=document.querySelector('[hx-post*=\"%s\"]'); if(l){l.click(); return true;} return false;})();" % jeu)
                    if ouvert: break
                    nav.appeler("Page.navigate", {"url": base + "/niveaux/CP"}); time.sleep(2.0)
                time.sleep(1.3)
                if etape == "apres-un-echec":
                    nav.evaluer("document.querySelectorAll('.proposition')[0].click(); true;"); time.sleep(0.5)
                    nav.evaluer("(function(){var b=document.querySelector('.proposition.deja-essayee');return !!b;})();")
                av = nav.evaluer(LIRE_SORTIE)
                c1 = nav.evaluer(CLIC_CENTRE); time.sleep(1.2)
                ec1 = nav.evaluer("({confirmation: !!document.querySelector('.retour-accueil'),"
                                  " texte:(document.querySelector('.zone-jeu')||document.body).innerText.trim().slice(0,220),"
                                  " jeux:document.querySelectorAll('[hx-post^=\"/parties/\"]').length});")
                av2 = nav.evaluer(LIRE_SORTIE) if ec1.get("confirmation") else None
                c2 = nav.evaluer(CLIC_CENTRE) if ec1.get("confirmation") else None
                if c2: time.sleep(1.2)
                ec2 = nav.evaluer("({jeux:document.querySelectorAll('[hx-post^=\"/parties/\"]').length});")
                cas = {"jeu": jeu, "etape": etape, "sortieEnTete": av, "premierAppui": c1,
                       "ecranApresPremierAppui": ec1, "sortieDeConfirmation": av2,
                       "secondAppui": c2, "ecranApresSecondAppui": ec2}
                rap["cas"].append(cas)
                print("%-32s %-14s visible=%s centreLibre=%s h=%spx police=%s dist=%spx defil=%s | appui1 -> confirmation=%s | appui2 -> %s jeux (h sortie confirmation=%s)"
                      % (jeu, etape, av.get("entierementDansLeViewport"), av.get("centreLibre"),
                         av.get("hauteur"), av.get("police"), av.get("distanceAuPlusProcheBouton"), av.get("defilement"),
                         ec1.get("confirmation"), ec2.get("jeux"), (av2 or {}).get("hauteur")))
    finally:
        nav.fermer()
    json.dump(rap, open(sortie,"w",encoding="utf-8"), ensure_ascii=False, indent=1, sort_keys=True)
    print("ecrit " + sortie)
main()
