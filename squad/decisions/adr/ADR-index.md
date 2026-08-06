# Index des ADR

Une ligne par decision d'architecture. Numeros jamais reutilises.

| Reference | Titre | Sprint | Date | Statut |
|---|---|---|---|---|
| [ADR-0001](ADR-0001-decoupage-en-modules-maven.md) | Decouper le build en cinq modules Maven et orienter les dependances vers l'interieur | s00 | 2026-08-04 | Accepte |
| [ADR-0002](ADR-0002-purete-du-domaine-et-de-application.md) | Interdire toute dependance externe dans `domain` et dans `application` | s00 | 2026-08-04 | Accepte |
| [ADR-0003](ADR-0003-prouver-que-chaque-regle-archunit-mord.md) | Prouver que chaque regle ArchUnit mord, par un contre-exemple qui doit la faire echouer | s00 | 2026-08-04 | Accepte |
| [ADR-0004](ADR-0004-decoupler-la-mecanique-du-contenu.md) | Decoupler la mecanique du contenu par deux contrats fermes et une regle bidirectionnelle | s00 | 2026-08-04 | Accepte |
| [ADR-0005](ADR-0005-abandonner-ruleset.md) | Abandonner `RuleSet` et interdire les abstractions fantomes | s00 | 2026-08-04 | Accepte |
| [ADR-0006](ADR-0006-la-difficulte-est-portee-par-le-paquet-de-contenu.md) | Supprimer la configuration de difficulte du domaine et la porter par le paquet de contenu | s00 | 2026-08-04 | Accepte |
| [ADR-0007](ADR-0007-un-seul-chargeur-de-contenu-pilote-par-un-schema.md) | Charger tout le contenu avec un seul chargeur pilote par un schema | s00 | 2026-08-04 | Accepte |
| [ADR-0008](ADR-0008-le-comportement-de-jeu-vit-sur-le-serveur.md) | Loger tout le comportement de jeu sur le serveur et brider le JavaScript par un budget | s00 | 2026-08-04 | Accepte |
| [ADR-0009](ADR-0009-les-garde-fous-de-ci-sont-des-scripts-testes.md) | Extraire les garde-fous de CI dans des scripts versionnes, et leur donner des contre-exemples | s00 | 2026-08-04 | Accepte |
| [ADR-0010](ADR-0010-refuser-les-chemins-de-poste-de-travail.md) | Refuser les chemins de poste de travail et les comptes utilisateur dans un fichier suivi, par un script teste | s00 | 2026-08-05 | Accepte |
| [ADR-0011](ADR-0011-rendre-toute-erreur-imprevue-en-html-francais.md) | Rendre toute erreur imprevue en HTML francais, jamais un corps technique brut | s01 | 2026-08-05 | Accepte |
| [ADR-0012](ADR-0012-la-longueur-de-partie-vit-dans-le-catalogue.md) | Porter la longueur d'une partie par l'entree de catalogue, jamais par le contenu | s01 | 2026-08-05 | Accepte |
| [ADR-0013](ADR-0013-un-garde-fou-derive-son-perimetre-et-se-prouve-mordant.md) | Deriver le perimetre de tout garde-fou de la source, et le prouver mordant sur un objet neuf | s02 | 2026-08-05 | Accepte |
| [ADR-0014](ADR-0014-vendoriser-une-bibliotheque-css-tierce-et-deriver-les-garde-fous-du-rendu.md) | Faire entrer une bibliotheque CSS tierce par vendorisation tracee, et faire deriver un garde-fou pedagogique du rendu plutot que du fichier | s03 | 2026-08-06 | Accepte |
| [ADR-0015](ADR-0015-deriver-le-perimetre-de-la-source-qui-gouverne-le-rendu.md) | Deriver le perimetre d'un garde-fou de la source qui gouverne le rendu, et supprimer celui dont le nom promet plus que son perimetre | s03 | 2026-08-06 | Accepte |
| [ADR-0016](ADR-0016-le-contenu-porte-l-aide-que-la-mecanique-promet.md) | Faire porter par le contenu l'aide que la mecanique promet, et deriver le perimetre du garde-fou du repertoire de contenu | s05 | 2026-08-06 | Accepte |
| [ADR-0017](ADR-0017-comparer-deux-boutons-dun-meme-ecran.md) | Comparer deux boutons d'un meme ecran, et dire ce que cette comparaison ne voit pas | s05 | 2026-08-06 | Accepte |

## Amendements

Un ADR accepte n'est pas fige : il est amende quand le depot montre qu'il dit faux, et l'amendement
est date dans le corps de l'ADR. Ceux du sprint 0 :

| ADR | Date | Ce que l'amendement corrige |
|---|---|---|
| ADR-0001 | 2026-08-04 | ses deux regles n'avaient pas de contre-exemple (comble par `yanis-qa`) ; piege de la couche declaree vide |
| ADR-0002 | 2026-08-04 | la purete de `application` n'avait pas de contre-exemple ; nuance sur les primitifs |
| ADR-0003 | 2026-08-04 | perimetre exact de l'exigence de contre-exemple, deux dispenses motivees, deux exceptions d'ecriture |
| ADR-0003 | 2026-08-04 | (premier amendement) `hasMessageContaining` sur la classe fautive, un `AssertionError` ne suffit pas |
| ADR-0004 | 2026-08-04 | trois de ses sept regles n'avaient pas de contre-exemple, dont l'isolation entre mecaniques |
| ADR-0005 | 2026-08-04 | correction de `itemExiste`, puis contre-exemples des quatre regles |
| ADR-0006 | 2026-08-04 | **sa regle centrale ne mordait pas** : primitifs vus dans `java.lang`, arguments generiques ignores. Regle reecrite |
| ADR-0007 | 2026-08-04 | sa regle ArchUnit n'avait pas de contre-exemple |
| ADR-0008 | 2026-08-04 | la regle d'assertion web n'avait pas de contre-exemple ; comment contre-exemplifier une regle qui juge du code de test |
| ADR-0009 | 2026-08-05 | la plage de commits ne resistait pas a une base reecrite et inatteignable apres un push force (`bad object`) ; ajout de `commit-atteignable.sh` et de son repli, avant la construction de la plage |
| ADR-0010 | 2026-08-05 | **le garde-fou a mordu son propre auteur** : verifie avant que `verifier-chemins.sh` soit suivi, il n'avait jamais ete juge par lui-meme ; une fois suivi, son propre commentaire d'en-tete contenait un vrai segment reel. Commentaire corrige, pas la regle |
| ADR-0008 | 2026-08-05 | ajout d'un renvoi vers ADR-0011 : le fragment d'erreur est du rendu serveur comme les autres, deja couvert par les regles existantes sans les modifier |
| ADR-0003 | 2026-08-05 | **son propre mecanisme enumerait son perimetre** : l'inventaire des regles contre-exemplifiees etait tenu a la main a chaque cloture, et `LesReglesMordentTest` avait vingt et une methodes ecrites une par une. Rien n'obligeait la vingt-quatrieme regle a recevoir son contre-exemple. Recensement rendu executable par ADR-0013 |
| ADR-0014 | 2026-08-06 | **sa garantie centrale ne mordait pas sur la vraie bibliotheque** : `declareEnImportant` cherchait `font-size:` par sous-chaine et tombait sur `--bs-btn-font-size:`, le motif de nommage de tous les composants Bootstrap 5. Trouve par `yanis-qa`, corrige par ADR-0015. Son jeu fautif ecrivait `font-size:1rem!important` en clair, ce que Bootstrap n'ecrit jamais |
| ADR-0014 | 2026-08-06 | **une de ses limites nommees ne gardait rien** : la puce "le garde-fou lit la classe statique du gabarit, pas la classe calculee" documentait un vert silencieux au lieu de le fermer. Mesure : retirer `.sortie-pleine` de `recreo.css` laissait la suite verte. Ferme par ADR-0015 |
| ADR-0012 | 2026-08-05 | **sa section executable nommait le mauvais paquet** : un contre-exemple range dans `domain.content.contreexemple` aurait ete invisible pour la regle qu'il doit faire echouer. Paquet corrige, contre-exemple et test de non-vacuite ecrits ; et la derniere ligne de son tableau de gardes de comportement, seule des quatre a etre restee sur le papier, est honoree dans `CatalogueChargeableIT` |

## Lecture rapide

Les dix ADR du sprint 0 forment un socle indissociable, dans cet ordre de lecture :

1. **ADR-0001** dit ou vit le code (cinq modules) ;
2. **ADR-0002** dit ce que le coeur n'a pas le droit de connaitre (liste blanche, pas liste noire) ;
3. **ADR-0003** dit comment on sait qu'une regle mord (contre-exemple obligatoire) - c'est lui qui
   donne leur valeur aux huit autres ;
4. **ADR-0004** tient le principe directeur du produit : mecanique et contenu s'ignorent, dans les
   deux sens ;
5. **ADR-0005**, **ADR-0006**, **ADR-0007** tranchent trois dettes nommees de la version
   precedente : l'abstraction fantome, la difficulte arithmetique imposee a toutes les matieres,
   les neuf chargeurs ;
6. **ADR-0008** ramene le comportement de jeu la ou il est testable ;
7. **ADR-0009** applique l'exigence d'ADR-0003 aux garde-fous qui ne sont pas en Java ;
8. **ADR-0010** applique la forme d'ADR-0009 a un nouveau motif : aucun chemin de poste de travail
   ni compte utilisateur dans un fichier suivi.

Sprint 1 en ajoute deux, tous deux nes du refus pedagogique de `bandit-manchot-additions-cp` :

9. **ADR-0011** ferme le trou qu'ADR-0008 ne couvrait pas : le chemin d'erreur, pas seulement le
   chemin nominal, doit rendre du HTML francais ;
10. **ADR-0012** tranche un point qu'ADR-0006 avait explicitement laisse ouvert en le nommant a
    l'avance : la longueur d'une partie vit dans l'entree de catalogue, jamais dans le contenu.

Sprint 2 en ajoute un, ne de deux mesures de `nadia-prof` sur un garde-fou vert qui ne gardait
rien :

11. **ADR-0013** generalise ADR-0003 au-dela d'ArchUnit et nomme la propriete que les deux
    occurrences partageaient : un garde-fou qui **enumere** son perimetre ne verifie que ce qu'on a
    pense a lui donner. Le perimetre se derive, le critere peut s'enumerer, et la preuve de morsure
    se fait sur un objet neuf, pas sur la mutation d'un objet deja connu.

Sprint 3 en ajoute un, ne d'une demande de l'utilisateur - passer le rendu de base sur une
bibliotheque CSS embarquee :

12. **ADR-0014** dit par quel mecanisme une bibliotheque tierce entre (vendorisation tracee par
    empreinte, jamais un webjar ni un CDN) et repond a la question que cette entree pose a
    ADR-0013 : de quelle source un garde-fou derive-t-il son perimetre quand le CSS servi n'est plus
    ecrit ici. La reponse n'est pas unique - un garde-fou de style juge une declaration et derive de
    la **provenance** du fichier ; un garde-fou pedagogique juge un **element rendu** et derive du
    balisage, le CSS n'etant pour lui qu'une matiere. D'ou la regle centrale : une garantie
    pedagogique est toujours portee par une regle maison ;
13. **ADR-0015** tranche les deux dettes que s03 avait nommees sans les fermer, et corrige la faute
    qu'ADR-0013 ne savait pas encore attraper : deriver correctement, mais **de la mauvaise source**.
    Un garde-fou qui juge un element rendu derive de tous les etats que cet element peut prendre, pas
    du seul etat ecrit en dur ; et un garde-fou dont le nom promet une propriete generale sur un
    fichier nomme est supprime, pas retreci.

Sprint 5 en ajoute un, ne du cinquieme motif de refus de "Le bon son" :

14. **ADR-0016** applique la doctrine d'ADR-0013 a une population que le depot n'avait encore jamais
    derivee, le **contenu**. La mecanique promet une aide depuis S3-2 ; un type de contenu entier
    etait incapable de la fournir, en silence, parce que rien ne confrontait la promesse a ce que le
    contenu sait donner. Le perimetre du garde-fou derive du repertoire de contenu et non du
    catalogue - DEC-0009 venait justement d'en sortir le paquet fautif ;
15. **ADR-0017** ferme le trou que `nadia-prof` a nomme apres trois occurrences identiques : aucun
    garde-fou ne comparait deux selecteurs **entre eux** sur un meme ecran. Il derive une population
    de **paires** - un role inerte dans le balisage, un appariement qui ne confronte que des etats
    capables de s'afficher ensemble - et il se prouve sur les trois etats reels du depot, deux qui
    doivent rougir et un, accepte par `nadia-prof`, qui doit rester vert. Il apporte aussi la
    premiere section "ce que cette regle ne couvre pas" ecrite pour etre lue par elle : une regle
    declarative ne voit ni la largeur d'un libelle, ni une couleur, ni une position.
