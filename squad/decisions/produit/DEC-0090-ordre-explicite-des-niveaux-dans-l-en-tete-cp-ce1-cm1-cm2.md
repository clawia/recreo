# DEC-0090 - L'ordre des niveaux affiches dans l'en-tete devient explicite (CP, CE1, CM1, CM2), l'ordre du catalogue ne suffit plus a partir d'un troisieme niveau

- **Type** : portee (garantie de comportement transverse, pas de contenu)
- **Date** : 2026-09-21
- **Auteur** : `camille-po`
- **Sprint** : s36 (constat F1, avis de faisabilite de `theo-archi`, `planning.md` section 3)
- **Competence programme visee** : transverse - pas de contenu pedagogique. Cette decision porte
  sur la navigation permanente de l'application, pas sur une competence de programme.
- **Statut** : Acceptee

## Contexte

`theo-archi`, en instruisant l'ouverture du perimetre CE1 (`DEC-0089`), releve le constat `F1` :
`PartieService.niveauxDuCatalogue()` (`application/src/main/java/com/recreo/application/usecase/
PartieService.java`, lignes 42-52) rend les niveaux distincts **dans l'ordre d'apparition au
catalogue**, et c'est la specification voulue et ecrite par `ADR-0044` ("les valeurs distinctes
sont rendues dans l'ordre du catalogue"). Avec deux niveaux (CP, CM1), cet ordre affiche par
hasard CP avant CM1 - un ordre qui coincide aujourd'hui avec la progression scolaire, sans qu'aucun
code ni aucun test ne le garantisse.

`DEC-0089` ouvre un troisieme niveau (CE1, mathematiques seule) ; ce sprint n'engage aucun paquet
CE1 et la barre d'en-tete ne porte donc aujourd'hui aucune puce CE1. Le defaut reste latent : rien
ne le rend visible tant qu'aucune entree CE1 n'existe dans `catalogue.yaml`. Ce document tranche la
question posee par `theo-archi`, pas parce qu'elle est urgente aujourd'hui, mais parce qu'elle
cesse de l'etre des l'instant ou un paquet CE1 - ou, plus tard, CM2 - entre au catalogue, et qu'une
decision ecrite maintenant coute moins cher qu'une decouverte a la livraison du premier paquet
CE1.

## Decision

**L'ordre des niveaux affiches dans l'en-tete est desormais une propriete garantie du produit, pas
un effet de bord de l'ordre de declaration du catalogue.** L'ordre attendu est la progression
scolaire telle que ce depot la nomme deja (`CLAUDE.md`, en-tete : "CP (cycle 2)" et "CM1 (cycle
3)" ; `DEC-0089`, section 2 : "CE1 est le second niveau du cycle 2 ... CM2 le dernier niveau du
cycle 3") : **CP, CE1, CM1, CM2**. Un niveau absent du catalogue n'affiche simplement pas sa puce ;
l'ordre relatif des niveaux presents reste celui de cette sequence.

**Ce que cette decision ne fait pas** : elle ne prescrit aucun mecanisme. Comment cette sequence
est encodee - une table de correspondance dans `adapter-content`, un comparateur porte par
`application`, une cle de tri ajoutee au descripteur de contenu, ou toute autre forme - reste
entierement a `theo-archi`. La seule contrainte de fond que cette decision pose a la solution
technique : **le niveau reste une donnee (`String`), jamais un type enumere du domaine** -
`ADR-0006` n'est pas rouverte, et ce document ne demande rien qui la contredise. Un ordre explicite
et un typage en donnee ne sont pas contradictoires : l'un dit ce que l'ecran montre, l'autre dit
comment le code represente un niveau.

## Justification pedagogique

Transverse : cette decision ne porte sur aucun contenu ni aucune competence, elle porte sur la
navigation permanente de l'interface. Ce qu'elle vise, du point de vue de l'enfant qui utilise
l'application - et, pour le CP, de l'adulte qui l'accompagne (`CLAUDE.md`, "L'enfant de CP n'est
jamais seul") : retrouver son niveau scolaire toujours range dans l'ordre qu'il connait deja de
l'ecole, CP puis CE1 puis CM1 puis CM2, plutot que dans un ordre qui ne correspond a rien de connu
et qui peut changer sans lien avec l'usage de l'application.

Deux raisons distinctes motivent d'ecrire cette garantie maintenant plutot que de laisser l'ordre
incidentel en l'etat :

1. **Ce n'est pas seulement un ordre "qui reste stable" par hasard - c'est un ordre qui peut
   deriver sans que rien ne le signale.** L'ordre actuel depend de l'ordre de declaration des
   entrees dans `catalogue.yaml`, un fichier edite pour des raisons qui n'ont rien a voir avec
   l'affichage (ajout d'un paquet, retrait, reorganisation). Rien dans le code ni dans les tests
   n'exprime aujourd'hui "CP avant CM1" comme une regle : `ADR-0044` garantit uniquement que les
   valeurs sont distinctes et rendues dans l'ordre du catalogue, ce qui est exactement la
   specification qui laisse cette derive possible. Un ordre qui coincide aujourd'hui avec la
   progression scolaire par la seule coincidence de deux entrees ne le garantit pas pour un
   troisieme niveau, ni pour une reorganisation future du fichier.
2. **Le cout de le garantir maintenant est bas, le cout de le decouvrir plus tard ne l'est pas.**
   Ecrire la regle avant que CE1 ou CM2 n'entrent reellement au catalogue evite exactement le
   defaut que `squad/README.md` sanctionne a plusieurs reprises pour d'autres proprietes : une
   propriete non gardee qui se decouvre seulement quand un contenu neuf la rencontre par hasard
   (amendement s17, "un chiffre cite ... n'a jamais ete verifie" ; amendement s21, "une condition
   pedagogique qui filtre un contenu est mesuree contre la population entiere ... pas seulement
   testee sur les tirages qu'elle rencontre").

Ce que cette decision ne pretend pas : que l'ordre d'affichage ait, en lui-meme, une valeur
pedagogique au sens ou `nadia-prof` juge une competence. C'est une propriete d'ergonomie et de
previsibilite d'interface, pas un contenu pedagogique - d'ou le champ "transverse" ci-dessus.

## Impact

- **Sur le backlog** : story neuve `S36-ORDRE-NIVEAUX`, ecrite dans le meme geste
  (`docs/backlog/backlog.md`, section "Sprint 36"), routee a `sofia-sm`. Aucun developpeur n'est
  libre pour cette tache a s36 (`planning.md`, section 4) : la story n'est pas engagee ce sprint,
  elle attend une affectation nominative a un sprint futur - non "budget libere" au sens de
  `PAR-16` puisqu'aucun poste de composition n'existe a s36 (`planning.md`, section 5), mais une
  story ecrite et non engagee, explicitement.
- **Sur `theo-archi`** : la story demande un avis de faisabilite et le choix du mecanisme technique
  avant tout developpement - c'est a lui, pas a moi, de le trancher.
- **Sur la matrice de couverture** : aucun impact. Aucune ligne de `MATRICE-couverture.md` ne porte
  sur la navigation.
- **Sur les jeux existants** : aucun. Avec deux niveaux (CP, CM1), l'ordre affiche aujourd'hui est
  deja CP puis CM1 - la story n'a donc aucune regression visible a corriger sur le catalogue
  actuel, seulement une garantie a poser avant qu'elle ne soit mise a l'epreuve par un troisieme
  niveau.
- **Sur `ADR-0044`** : cette decision ne la contredit pas mais rend sa clause "dans l'ordre du
  catalogue" incomplete face a un troisieme niveau. `theo-archi` juge lui-meme si `ADR-0044` doit
  recevoir une note ou un amendement date au moment ou il instruit la story.
