#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""Mesure ce qu'une image fixe, et ce qu'un texte long, coutent au pli - aux dix largeurs.

Pourquoi ce script existe. nadia-prof a etabli le 2026-08-31 que "le pli n'a jamais ete mesure avec
une image ; personne ne le sait, parce que personne ne l'a mesure" (arbitrage prealable de s25,
section 3.1). Elle a pose dans le meme document une seconde question de la meme famille : un
texteSupport de 45 a 70 mots, sa question et ses trois propositions tiennent-ils au-dessus du pli
(section 9.1, condition LIS-2, dont le plafond de 70 est declare provisoire et remis a cette
mesure). Les deux questions demandent le meme instrument et il n'existait pas : tous les outils de
mesure de pli de ce depot jouent l'application servie, et ni une image ni un paquet LIS n'y sont
servis.

Ce que ce script rend, et c'est un chiffre par largeur, pas un verdict global :

  - LE PLAFOND DE HAUTEUR D'IMAGE, par dichotomie au pixel : la plus grande valeur de max-height
    pour laquelle le bas de l'ecran entier reste au-dessus du pli. Une reponse "oui/non" a
    "l'image tient-elle" serait fausse dans les deux sens - elle tient si elle est bornee, et le
    chiffre EST la borne ;
  - LE PLAFOND DE LONGUEUR DE TEXTE, par le meme procede sur le nombre de mots du texteSupport ;
  - la boite reellement rendue a la valeur candidate de la proposition, et le rapport de geometrie
    du fichier avant/apres mise en page (FIG-1 : un rectangle reste un rectangle a toute taille
    d'ecran, verifiable autrement que par mon oeil).

Le pli est 551 px, la hauteur de reference du produit (DEC-0040, second amendement du 2026-08-22).
Les dix largeurs encadrent les paliers de la feuille tierce : mesurer a une seule largeur est
exactement ce qui a laisse passer le defaut de rembourrage lateral de s05.

Ce script n'est pas un garde-fou de CI et ne s'execute pas dans "mvn verify" : il a besoin d'un
navigateur, que rien dans le depot ne peut garantir (meme limite qu'outils/mesurer-rendu.sh). Les
juges purs dont il depend - port, viewport lu et non suppose, un seul etat visible a la fois - sont
ceux de squad/outils/nadia-prof/balayage-largeur-rangee.py, importes et non recopies.

Prerequis nommes, jamais leur emplacement (ADR-0010) : un Chrome designe par la variable
d'environnement CHROME ou present sur le PATH, et le module python "websocket-client".

Usage :
  CHROME=... [PORT_DEV=9224] python squad/outils/ines-design/mesurer-l-illustration.py \
      <chemin_du_prototype> <json_de_sortie>
"""
import importlib.util
import json
import os
import shutil
import sys
import tempfile
import time

_dossier = os.path.dirname(os.path.abspath(__file__))
_chemin_blr = os.path.join(_dossier, "..", "nadia-prof", "balayage-largeur-rangee.py")
_spec = importlib.util.spec_from_file_location("balayage_largeur_rangee", _chemin_blr)
blr = importlib.util.module_from_spec(_spec)
_spec.loader.exec_module(blr)

PLI_PX = 551
LARGEURS = [360, 390, 576, 640, 720, 744, 768, 992, 1200, 1440]

# Les douze etats du prototype. "haut" et "bas" nomment la place de l'illustration par rapport a
# l'enonce ; "large" reprend les trois etats "haut" avec un rapport de geometrie de 3:1.
ETATS = [
    "haut-fraiche", "haut-echec", "haut-revelation",
    "large-fraiche", "large-echec", "large-revelation",
    "bas-fraiche", "bas-echec", "bas-revelation",
    "lis-fraiche", "lis-echec", "lis-revelation",
]

# La valeur candidate de la proposition, en pixels a la racine par defaut (11rem = 176px). Le
# releve la porte a cote du plafond mesure, pour que la marge se lise sans calcul.
CANDIDATE_PX = 176

SELECTEURS = [
    ".entete-contenu",
    ".zone-jeu",
    ".score",
    ".consigne",
    ".texte-support",
    ".illustration",
    ".image-item",
    ".enonce",
    ".cabinet",
    ".propositions",
    ".proposition",
    ".message-jeu",
    ".signal",
    ".icone-signal",
    ".verdict",
    ".explication",
    ".indice",
    ".lien-accueil",
]

LIRE = r"""
(function () {
    var r = function (v) { return v === null ? null : Math.round(v * 100) / 100; };
    var racine = document.querySelector('[data-etat="ETAT"]');
    function dans(selecteur) {
        return racine ? racine.querySelector(selecteur) : null;
    }
    function boite(selecteur) {
        // L'etat mesure d'abord ; a defaut, un element de la coquille permanente SEULEMENT -
        // jamais un element d'un autre etat, qui est masque et rendrait une boite de zero pixel
        // au lieu d'une absence. Une boite fausse est pire qu'une case vide.
        var e = dans(selecteur);
        if (!e) {
            e = Array.prototype.find.call(
                document.querySelectorAll(selecteur),
                function (c) { return !c.closest('[data-etat]'); });
        }
        if (!e) { return null; }
        var rect = e.getBoundingClientRect();
        var style = getComputedStyle(e);
        return {
            gauche: r(rect.left), droite: r(rect.right),
            haut: r(rect.top), bas: r(rect.bottom),
            largeur: r(rect.width), hauteur: r(rect.height),
            police: style.fontSize,
            fond: style.backgroundColor,
            couleur: style.color,
            hauteurMinimale: style.minHeight,
            rembourrage: style.paddingTop + ' ' + style.paddingRight + ' '
                + style.paddingBottom + ' ' + style.paddingLeft,
            // Meme motif que le releve frere : le texte n'est garde que sur les blocs dont le
            // contenu explique la hauteur. Ailleurs, il recopiait l'ecran entier a chaque lecture.
            texte: AVEC_TEXTE.indexOf(selecteur) === -1 ? null : e.textContent
        };
    }
    var boites = {};
    SELECTEURS.forEach(function (s) { boites[s] = boite(s); });

    var basReel = null;
    if (racine) {
        basReel = racine.getBoundingClientRect().bottom;
        Array.prototype.forEach.call(racine.querySelectorAll('*'), function (n) {
            var b = n.getBoundingClientRect().bottom;
            if (b > basReel) { basReel = b; }
        });
    }

    // Le rapport de geometrie du FICHIER contre celui de la BOITE RENDUE. C'est la seule facon de
    // dire, autrement que par un oeil, qu'une regle de feuille n'a pas etire le dessin (FIG-1).
    var img = dans('.image-item');
    var geometrie = null;
    if (img) {
        var rect = img.getBoundingClientRect();
        geometrie = {
            rapportDuFichier: img.naturalWidth && img.naturalHeight
                ? r(img.naturalWidth / img.naturalHeight) : null,
            rapportRendu: rect.height ? r(rect.width / rect.height) : null,
            largeurNaturelle: img.naturalWidth,
            hauteurNaturelle: img.naturalHeight,
            ajustement: getComputedStyle(img).objectFit
        };
    }

    // Chaque proposition, une par une. Le releve de s24 n'avait a mesurer qu'un champ ; ici c'est
    // la LONGUEUR DES PROPOSITIONS qui decide du nombre de rangees, donc du budget vertical, et un
    // seul selecteur ".proposition" ne rendrait que la premiere.
    var propositions = Array.prototype.map.call(
        racine ? racine.querySelectorAll('.proposition') : [],
        function (b) {
            var rect = b.getBoundingClientRect();
            return {
                texte: b.textContent.trim(),
                caracteres: b.textContent.trim().length,
                haut: r(rect.top), bas: r(rect.bottom),
                largeur: r(rect.width), hauteur: r(rect.height),
                police: getComputedStyle(b).fontSize
            };
        });

    var support = dans('.texte-support');
    return {
        boites: boites,
        propositionsUneAUne: propositions,
        rangeesDePropositions: Object.keys(propositions.reduce(function (a, p) {
            a[p.haut] = 1; return a; }, {})).length,
        geometrieDeLImage: geometrie,
        motsDuTexteSupport: support
            ? support.textContent.trim().split(/\s+/).length : null,
        basDeLEcran: r(basReel),
        defilement: window.scrollY,
        viewport: [window.innerWidth, window.innerHeight]
    };
})()
"""

POSER_HAUTEUR = (
    "(function(){var p=document.getElementById('hauteur-ines');"
    "if(p){p.remove();}"
    "var s=document.createElement('style');s.id='hauteur-ines';"
    "s.textContent='.image-item{max-height:%dpx}';"
    "document.head.appendChild(s);"
    "return document.getElementById('hauteur-ines').textContent;})()"
)

# Masque l'explication d'un etat de revelation. Elle n'est portee que par un paquet du catalogue
# servi sur 31 (cp-le-bon-son) : la mesurer comme si elle etait toujours la donnerait un plafond
# trop severe pour trente paquets, et l'omettre en donnerait un trop genereux pour le trente et
# unieme. Les deux chiffres sont donc rendus cote a cote, jamais l'un a la place de l'autre.
POSER_EXPLICATION = (
    "(function(){var p=document.getElementById('explication-ines');"
    "if(p){p.remove();}"
    "if(%d){return 'visible';}"
    "var s=document.createElement('style');s.id='explication-ines';"
    "s.textContent='.explication{display:none}';"
    "document.head.appendChild(s);return 'masquee';})()"
)

# La variante FAUTIVE, mesuree a cote de la regle proposee et non decrite de memoire : "height"
# fixe au lieu de "max-height". Elle existe dans ce releve parce qu'une regle de feuille ne se
# justifie pas par ce qu'elle est censee eviter, mais par le rendu de ce qu'elle evite. Le mode de
# defaillance n'apparait qu'aux largeurs ou max-width mord.
POSER_HAUTEUR_FAUTIVE = (
    "(function(){var p=document.getElementById('hauteur-ines');"
    "if(p){p.remove();}"
    "var s=document.createElement('style');s.id='hauteur-ines';"
    "s.textContent='.image-item{height:%dpx;max-height:none}';"
    "document.head.appendChild(s);"
    "return document.getElementById('hauteur-ines').textContent;})()"
)

POSER_MOTS = (
    "(function(){var p=document.querySelector('[data-etat=\"%s\"] .texte-support');"
    "if(!p){return null;}"
    "if(!p.dataset.original){p.dataset.original=p.textContent;}"
    "var mots=p.dataset.original.trim().split(/\\s+/);"
    "p.textContent=mots.slice(0,%d).join(' ');"
    "return p.textContent.trim().split(/\\s+/).length;})()"
)


# Chaque levier de forme, isole. Aucun n'est propose ici : ils sont mesures pour que le prix de
# chacun soit connu avant qu'on en discute, et parce que la moitie d'entre eux touche des blocs que
# nadia-prof a deja arbitres (le cabinet en s10, le rang d'en-tete en s15). Un levier chiffre est
# une information ; un levier applique sans mesure est ce que ce depot a deja sanctionne.
LEVIERS = {
    "bordure-du-cabinet-retiree": ".cabinet{border-width:0}",
    "bandeau-du-cabinet-masque": ".cabinet-bandeau{display:none}",
    "socle-du-cabinet-masque": ".cabinet-socle{display:none}",
    "cabinet-entierement-retire": ".cabinet{border-width:0;background:none}"
                                  ".cabinet-bandeau,.cabinet-socle,.cabinet-levier{display:none}",
    "rembourrage-haut-de-zone-a-1rem": ".zone-jeu{padding-top:1rem}",
    "score-masque": ".score{display:none}",
    "marge-de-l-illustration-a-zero": ".illustration{margin:0}",
    "enonce-a-1-5rem": ".enonce{font-size:1.5rem}",
}

POSER_LEVIER = (
    "(function(){var p=document.getElementById('levier-ines');"
    "if(p){p.remove();}"
    "if(!%s){return 'aucun';}"
    "var s=document.createElement('style');s.id='levier-ines';"
    "s.textContent=%s;document.head.appendChild(s);return 'pose';})()"
)

POSER_CARACTERES = (
    "(function(){var l=document.querySelectorAll('[data-etat=\"%s\"] .proposition');"
    "if(!l.length){return null;}var n=0;"
    "Array.prototype.forEach.call(l, function(b){"
    "  if(!b.dataset.original){b.dataset.original=b.textContent;}"
    "  b.textContent=b.dataset.original.slice(0,%d);"
    "  if(b.textContent.length>n){n=b.textContent.length;}});"
    "return n;})()"
)


def plafond_de_caracteres(nav, etat, basse=0, haute=120):
    """Le plus grand nombre de caracteres par proposition qui garde l'ecran au-dessus du pli.

    C'est la variable que LIS-2 ne borne pas et que la mesure designe comme la vraie contrainte :
    le texteSupport se replie sous les propositions, pas l'inverse.
    """
    if nav.evaluer(POSER_CARACTERES % (etat, basse)) is None:
        return None
    if lire(nav, etat)["basDeLEcran"] > PLI_PX:
        return None
    nav.evaluer(POSER_CARACTERES % (etat, haute))
    if lire(nav, etat)["basDeLEcran"] <= PLI_PX:
        return haute
    while haute - basse > 1:
        milieu = (basse + haute) // 2
        nav.evaluer(POSER_CARACTERES % (etat, milieu))
        if lire(nav, etat)["basDeLEcran"] <= PLI_PX:
            basse = milieu
        else:
            haute = milieu
    return basse


# Ce que coute une ligne d'indice, mesure et non deduit. C'est le chiffre qui gouverne le budget
# d'image d'un item : l'indice est le dernier bloc de l'ecran, sa longueur est une propriete du
# CONTENU, et chaque ligne rendue de plus se prend sur la hauteur laissee au dessin. Le texte
# injecte est un mot neutre repete - il ne fixe aucun contenu, il fixe une longueur.
POSER_INDICE = (
    "(function(){var p=document.querySelector('[data-etat=\"%s\"] .indice');"
    "if(!p){return null;}"
    "if(!p.dataset.original){p.dataset.original=p.textContent;}"
    "p.textContent=%s;"
    "var b=p.getBoundingClientRect();var st=getComputedStyle(p);"
    "var chrome=2*parseFloat(st.paddingTop)+2*parseFloat(st.borderTopWidth);"
    "return {hauteurDeBoite: Math.round(b.height*100)/100,"
    " interligne: st.lineHeight, rembourrageEtBordure: chrome,"
    " lignesRendues: Math.round((b.height-chrome)/parseFloat(st.lineHeight)*100)/100};})()"
)


def afficher_un_seul_etat(nav, etat):
    """Rend visible le seul etat mesure, et refuse si le document n'en montre pas exactement un."""
    nav.evaluer(
        "(function () {"
        "  var p = document.getElementById('commutateur-ines');"
        "  if (p) { p.remove(); }"
        "  var s = document.createElement('style');"
        "  s.id = 'commutateur-ines';"
        "  s.textContent = '[data-etat=\"%s\"] { display: block; }';"
        "  document.head.appendChild(s);"
        "})()" % etat
    )
    visibles = nav.evaluer(
        "Array.prototype.filter.call(document.querySelectorAll('[data-etat]'),"
        " function (e) { return getComputedStyle(e).display !== 'none'; })"
        ".map(function (e) { return e.getAttribute('data-etat'); })"
    )
    if visibles != [etat]:
        raise blr.MesureNonFiable(
            "le document montre %s alors que la mesure porte sur \"%s\" : un etat de trop "
            "fausserait la hauteur et le pli. Mesure refusee." % (visibles, etat)
        )


# Les seuls selecteurs dont le releve garde le texte : ceux dont le contenu explique la hauteur.
AVEC_TEXTE = [".enonce", ".consigne", ".verdict", ".indice", ".explication", ".texte-support"]


def lire(nav, etat):
    return nav.evaluer(
        LIRE.replace("ETAT", etat)
        .replace("SELECTEURS", json.dumps(SELECTEURS))
        .replace("AVEC_TEXTE", json.dumps(AVEC_TEXTE)))


def plafond_de_hauteur(nav, etat, basse=0, haute=480):
    """La plus grande max-height d'image qui garde le bas de l'ecran au-dessus du pli.

    Dichotomie au pixel entier, jamais une formule : la hauteur d'une image n'est pas seule a
    entrer dans le bas de l'ecran (marges qui fusionnent, meuble qui se replie), et une soustraction
    aurait l'air juste sans l'etre.
    """
    nav.evaluer(POSER_HAUTEUR % basse)
    if lire(nav, etat)["basDeLEcran"] > PLI_PX:
        return None  # l'ecran ne tient pas meme sans image : la borne n'existe pas
    nav.evaluer(POSER_HAUTEUR % haute)
    if lire(nav, etat)["basDeLEcran"] <= PLI_PX:
        # Saturation : a la taille intrinseque du dessin de mesure, le pli n'est pas encore
        # atteint. Le plafond n'est alors pas pose par le pli mais par la LARGEUR disponible et par
        # la taille du fichier lui-meme - une regle max-height ne fait jamais grandir une image
        # au-dela de sa taille naturelle, et c'est cette propriete-la qui rend la borne portable.
        return haute
    while haute - basse > 1:
        milieu = (basse + haute) // 2
        nav.evaluer(POSER_HAUTEUR % milieu)
        if lire(nav, etat)["basDeLEcran"] <= PLI_PX:
            basse = milieu
        else:
            haute = milieu
    return basse


def plafond_de_mots(nav, etat, basse=0, haute=120):
    """Le plus grand nombre de mots de texteSupport qui garde le bas de l'ecran au-dessus du pli."""
    if nav.evaluer(POSER_MOTS % (etat, basse)) is None:
        return None
    if lire(nav, etat)["basDeLEcran"] > PLI_PX:
        return None
    nav.evaluer(POSER_MOTS % (etat, haute))
    if lire(nav, etat)["basDeLEcran"] <= PLI_PX:
        return haute
    while haute - basse > 1:
        milieu = (basse + haute) // 2
        nav.evaluer(POSER_MOTS % (etat, milieu))
        if lire(nav, etat)["basDeLEcran"] <= PLI_PX:
            basse = milieu
        else:
            haute = milieu
    return basse


def compacter(valeur):
    """Arrondit au centieme de pixel et retire les cases vides du releve commite.

    Le centieme est en dessous de ce qu'un ecran affiche : rien de mesure n'est perdu. Une boite
    absente disparait au lieu d'occuper trois lignes de "null" - et son absence reste lisible,
    puisque le selecteur n'apparait plus du tout pour cet etat.
    """
    if isinstance(valeur, float):
        return round(valeur, 2)
    if isinstance(valeur, dict):
        return {c: compacter(v) for c, v in valeur.items() if v is not None}
    if isinstance(valeur, list):
        return [compacter(v) for v in valeur]
    return valeur


def resumer(lecture):
    """Ne garde d'une lecture de controle que ce qu'elle prouve : le bas de l'ecran et la boite du
    dessin. Le detail des dix-huit boites est deja dans la lecture a nu du meme etat ; le repeter a
    la hauteur candidate quadruplait le poids du releve sans rien y ajouter."""
    return {
        "basDeLEcran": lecture["basDeLEcran"],
        "boiteDeLImage": lecture["boites"].get(".image-item"),
        "geometrieDeLImage": lecture["geometrieDeLImage"],
        "defilement": lecture["defilement"],
    }


def naviguer(nav, url):
    nav.appeler("Page.navigate", {"url": url})
    for _ in range(120):
        if nav.evaluer("document.readyState") == "complete":
            time.sleep(0.4)
            return
        time.sleep(0.25)
    raise blr.MesureNonFiable("le document %s n'a pas fini de charger. Mesure refusee." % url)


def main():
    if len(sys.argv) != 3:
        print(__doc__)
        return 2
    chemin, sortie = sys.argv[1], sys.argv[2]
    if not os.path.isfile(chemin):
        raise SystemExit("prototype introuvable : %s" % chemin)
    url = "file:///" + os.path.abspath(chemin).replace("\\", "/").lstrip("/")

    binaire = os.environ.get("CHROME") or shutil.which("chrome") \
        or shutil.which("chromium") or shutil.which("google-chrome")
    if not binaire:
        raise SystemExit(
            "aucun navigateur : renseigner la variable d'environnement CHROME, ou en mettre un "
            "sur le PATH."
        )
    port = int(os.environ.get("PORT_DEV", "9224"))

    profil = tempfile.mkdtemp(prefix="ines-illustration-")
    nav = blr.Navigateur(binaire, profil, port)
    releve = {
        "prototype": os.path.basename(chemin), "pli": PLI_PX, "largeurs": LARGEURS,
        "etats": ETATS, "hauteurCandidatePx": CANDIDATE_PX, "mesures": [],
    }
    try:
        for largeur in LARGEURS:
            vu = nav.regler_viewport(largeur, PLI_PX)
            naviguer(nav, url)
            nav.regler_viewport(largeur, PLI_PX)
            for etat in ETATS:
                afficher_un_seul_etat(nav, etat)
                mesure = {"etat": etat, "largeurDemandee": largeur, "viewportLu": vu}
                if etat.startswith("lis-"):
                    mesure["plafondDeMots"] = plafond_de_mots(nav, etat)
                    nav.evaluer(POSER_MOTS % (etat, 70))
                    mesure["a70Mots"] = lire(nav, etat)
                    nav.evaluer(POSER_MOTS % (etat, 0))
                    mesure["sansTexteSupport"] = lire(nav, etat)
                    for mots in (45, 70):
                        nav.evaluer(POSER_MOTS % (etat, mots))
                        mesure["plafondDeCaracteresDePropositionA%dMots" % mots] =                             plafond_de_caracteres(nav, etat)
                    nav.evaluer(POSER_CARACTERES % (etat, 1000))
                    nav.evaluer(POSER_MOTS % (etat, 70))
                else:
                    nav.evaluer(POSER_EXPLICATION % 1)
                    mesure["plafondDeHauteurPx"] = plafond_de_hauteur(nav, etat)
                    nav.evaluer(POSER_HAUTEUR % 0)
                    mesure["sansImage"] = lire(nav, etat)
                    nav.evaluer(POSER_HAUTEUR % CANDIDATE_PX)
                    mesure["aLaHauteurCandidate"] = resumer(lire(nav, etat))
                    if etat.startswith("large-"):
                        nav.evaluer(POSER_HAUTEUR_FAUTIVE % CANDIDATE_PX)
                        mesure["varianteFautiveHeightFixe"] = resumer(lire(nav, etat))
                        nav.evaluer(POSER_HAUTEUR % CANDIDATE_PX)
                    if etat.endswith("revelation"):
                        nav.evaluer(POSER_EXPLICATION % 0)
                        mesure["plafondDeHauteurPxSansExplication"] = plafond_de_hauteur(nav, etat)
                        nav.evaluer(POSER_HAUTEUR % 0)
                        mesure["sansImageSansExplication"] = lire(nav, etat)
                        nav.evaluer(POSER_HAUTEUR % CANDIDATE_PX)
                        mesure["aLaHauteurCandidateSansExplication"] = resumer(lire(nav, etat))
                        nav.evaluer(POSER_EXPLICATION % 1)
                if etat == "haut-echec":
                    nav.evaluer(POSER_HAUTEUR % 0)
                    origine = nav.evaluer(
                        "document.querySelector('[data-etat=\"haut-echec\"] .indice').textContent")
                    cout = {"indiceDuPrototype": nav.evaluer(
                        POSER_INDICE % (etat, json.dumps(origine)))}
                    for mots in (4, 10, 18, 26):
                        cout["a%dMots" % mots] = nav.evaluer(
                            POSER_INDICE % (etat, json.dumps(" ".join(["longueur"] * mots))))
                    nav.evaluer(POSER_INDICE % (etat, json.dumps(origine)))
                    mesure["coutDeLIndice"] = cout
                    nu = lire(nav, etat)["basDeLEcran"]
                    gains = {}
                    for nom, regle in LEVIERS.items():
                        nav.evaluer(POSER_LEVIER % ("1", json.dumps(regle)))
                        gains[nom] = round(nu - lire(nav, etat)["basDeLEcran"], 2)
                    nav.evaluer(POSER_LEVIER % ("0", '""'))
                    mesure["leviers"] = {"basSansImageNiLevier": nu, "gainEnPx": gains}
                releve["mesures"].append(mesure)
                sys.stderr.write(
                    "  %5d %-16s plafond %-6s bas a la candidate %s\n"
                    % (largeur, etat,
                       mesure.get("plafondDeHauteurPx", mesure.get("plafondDeMots")),
                       (mesure.get("sansImage") or mesure.get("sansTexteSupport"))
                       ["basDeLEcran"]))
                sys.stderr.flush()
    finally:
        nav.fermer()
        shutil.rmtree(profil, ignore_errors=True)

    with open(sortie, "w", encoding="utf-8") as fichier:
        json.dump(compacter(releve), fichier, ensure_ascii=False, indent=1,
                  sort_keys=True)
    print("%d mesures ecrites dans %s" % (len(releve["mesures"]), sortie))
    return 0


if __name__ == "__main__":
    sys.exit(main())
