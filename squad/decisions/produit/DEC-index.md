# Index des decisions produit

Une ligne par decision produit. Numeros jamais reutilises.

| Reference | Titre | Sprint | Date | Statut |
|---|---|---|---|---|
| DEC-0001 | Perimetre du sprint 0 et choix de la premiere competence couverte | s0 | 2026-08-04 | Acceptee |
| DEC-0002 | Statut des emoji et pictogrammes dans le contenu et l'interface destines a l'enfant | s0 | 2026-08-04 | Acceptee |
| DEC-0003 | Le sprint 0 ne cree pas de story de persistence, le critere d'integration est reformule | s0 | 2026-08-04 | Acceptee |
| DEC-0004 | Textes, forme du score et seuils chiffres pour lever le refus pedagogique du sprint 1 | s01 | 2026-08-05 | Acceptee |
| DEC-0005 | L'indice de S1-5 est un contenu YAML, jamais une constante du domaine | s01 | 2026-08-05 | Acceptee |
| DEC-0006 | Perimetre du chantier identite visuelle de s02 et statut de l'icone d'application au regard de DEC-0002 | s02 | 2026-08-05 | Acceptee |
| DEC-0007 | Le sprint 3 complete la ligne mathematiques CP existante et solde la dette de gouvernance, il n'ouvre pas de nouvelle matiere ni de nouvelle competence | s03 | 2026-08-05 | Acceptee (point 1 remplace par DEC-0008, point 7 remplace par DEC-0010, points 2 a 6 et 8 inchanges) |
| DEC-0008 | La couverture du programme reprend sur demande de l'utilisatrice : francais CP et CM1 entrent au backlog | s04 | 2026-08-06 | Acceptee |
| DEC-0009 | Retrait de "Le bon son" du catalogue jusqu'a sa correction | s04/s05 | 2026-08-06 | Acceptee |
| DEC-0010 | Le titre du produit redevient "Recreo", sans accent : le point 7 de DEC-0007 est remplace | s05 | 2026-08-06 | Acceptee |
| DEC-0011 | Le sprint 5 reste entierement correctif, l'ouverture suivante est nommee au sprint 6 | s05 | 2026-08-06 | Acceptee |
| DEC-0012 | Le rhabillage du gabarit (en-tete, icone, menu, contenu centre) ouvre le sprint 6 ; le menu reste a une seule entree | s05/s06 | 2026-08-06 | Acceptee, amendee le 2026-08-07 (constat de l'absorption de S6-1/S6-2 dans s05) |
| DEC-0013 | Perimetre du sprint 6 : trois chantiers actes, choix de textes-trous/cm1-conjugaison.yml pour l'ouverture francais engagee par DEC-0011 | s06 | 2026-08-07 | Acceptee |
| DEC-0014 | Retrait de "Le bon verbe" du catalogue jusqu'a sa correction | s06/s07 | 2026-08-10 | Acceptee |
| DEC-0015 | Le sprint 7 construit la vraie machine a sous et ferme la dette n°1 de CLAUDE.md | s07 | 2026-08-10 | Acceptee, amendee quatre fois le 2026-08-10 (contradiction S7-2 / reintegration de "Le bon verbe", routee par theo-archi ; arbitrage prealable de nadia-prof, reduction a trois acquise puis insuffisante sur la largeur ; rendu en clair de la reprise silencieuse laisse en l'etat face a ADR-0027 ; nadia-prof eteint sa reduction, repli confirme mais non livre) |
| DEC-0016 | Une hauteur de viewport de reference, et ce qui doit rester au-dessus du pli sur un ecran de manche | s07 | 2026-08-10 | Acceptee, amendee le 2026-08-11 (chiffres corriges sur deux jeux, story ouverte au backlog, pas de seuil chiffre) et le 2026-08-12 (attribution et chiffres de pli corriges, meme sens) |
| DEC-0017 | Perimetre du sprint 8 : la machine retrouve son contexte, trois verites du depot corrigees, l'ouverture du futur et de l'imparfait de la conjugaison | s08 | 2026-08-11 | Acceptee, correction documentaire le 2026-08-12 (numerotation des chantiers, aucun contenu rouvert) |
| DEC-0018 | L'activation de "Le bon verbe" est corrigee dans le sprint plutot que retiree, denouement d'un second refus, et sort de la ligne de matrice | s08 | 2026-08-11 | Acceptee |
| DEC-0019 | Perimetre du sprint 9 : quatre lignes textuelles de mathematiques CP ouvertes, denombrement et monnaie differes sous le gel de rendu | s09 | 2026-08-12 | Acceptee, amendee le 2026-08-12 (motifs des lignes denombrement et monnaie corriges sur avis de theo-archi, conclusion inchangee) |
| DEC-0020 | S9-2 debloquee : nombreDeManches abaisse a 7 pour bandit-manchot-complements-10-cp, plutot qu'un elargissement des formes d'items | s09 | 2026-08-12 | Acceptee |
| DEC-0021 | La regularite de magnitude des additions de "resoudre un probleme" (reserve de refus sur S9-4) est reportee en story de backlog, pas un geste de ce sprint | s09 | 2026-08-12 | Acceptee |

Amendement (pas une nouvelle reference) : `DEC-0002` a ete amendee le 2026-08-04, a la suite de la
relecture pedagogique de `nadia-prof` sur le refus de `bandit-manchot-additions-cp` - ajout d'une
exigence positive de francais correct et integralement accentue pour la categorie B, et
correction du jugement sur les deux orientations du triangle de `contenu-repris/associer/
cp-formes.yml` (qualite pedagogique et non ambiguite, selon l'avis de Nadia). Voir la section
"Amendement du 2026-08-04" dans `DEC-0002-emoji-et-pictogrammes-contenu-enfant.md`.

Amendements (pas une nouvelle reference) : `DEC-0004` et `DEC-0007` ont chacune ete amendees le
2026-08-05, sur mesure de `theo-archi` dans son avis de faisabilite du sprint 3
(`squad/sprints/s03-completer-mathematiques-cp/avis-faisabilite.md`) : la table de repartition des
sommes de `DEC-0007` point 2 etait calculee sur le tirage nu, pas sur le tirage reellement compose
avec le filtre d'operandes nuls de `DEC-0004` point 5, ce qui faisait sortir deux sommes de leurs
fourchettes. `DEC-0004` precise desormais que la cible pedagogique retenue est 12,9 %
d'operandes nuls (bande du point 5 inchangee) ; `DEC-0007` porte la table de cibles recalculee sur
le tirage compose. Voir les sections "Amendement du 2026-08-05 (s03)" dans
`DEC-0004-textes-score-et-seuils-lever-refus-s01.md` et
`DEC-0007-perimetre-s03-completer-avant-etendre.md`.

Amendement (pas une nouvelle reference) : `DEC-0007` a ete amendee le 2026-08-06, sur demande
explicite de l'utilisatrice qui renverse la priorisation "completer avant etendre". Seul le
point 1 de `DEC-0007` (aucune matiere ni competence nouvelle avant `couvert-valide` sur
mathematiques CP) est remplace, par `DEC-0008-la-couverture-reprend-francais-cp-et-cm1.md`. Les
points 2 a 8 de `DEC-0007` (repartition des sommes, declenchement de l'indice, appui rate, largeur
d'ecran, titre accentue, question de process, ecran de catalogue vide) restent en vigueur,
inchanges : ils ne sont ni retires ni redebats par cet amendement. La condition ecrite par
`nadia-prof` pour que la ligne mathematiques CP passe a `couvert-valide` n'est ni levee ni
abandonnee par `DEC-0008` : elle reste en attente de son audit de cloture de s03, en parallele de
l'ouverture du francais.

Amendement (pas une nouvelle reference) : `DEC-0007` a ete amendee une seconde fois le
2026-08-06, sur correction explicite de l'utilisatrice sur le nom du produit ("Récréo s'ecrit
Recreo sans accent"). Seul le point 7 de `DEC-0007` (le titre devient "Récréo") est remplace, par
`DEC-0010-le-titre-reste-recreo-sans-accent.md`. Les points 2 a 6 et 8 restent inchanges. La
reserve 10 de `nadia-prof`, enregistree comme tenue apres l'execution du point 7 initial, n'est
ni rouverte ni corrigee par cet amendement : elle est signalee a `nadia-prof`, pas editee, voir
`DEC-0010`.

`DEC-0011-perimetre-s05-reste-correctif.md` tranche que s05 reste entierement correctif (aucune
ligne de matrice de plus n'entre `en cours` ce sprint), pour un motif specifique a l'etat de la
reserve de contenu et non pour reconduire la regle generale de `DEC-0007` : voir `DEC-0011` pour
la distinction.

Amendement (pas une nouvelle reference) : `DEC-0012` a ete amendee le 2026-08-07, pour acter par
ecrit que ses stories S6-1 et S6-2 (rhabillage du gabarit) ont ete livrees et auditees pendant s05,
en dehors de la fenetre que la decision leur assignait, sans amendement au moment des faits - suite
directe a l'amendement de process propose dans
`squad/sprints/s05-lever-le-refus-du-bon-son/retro.md`. Le contenu des points 1 a 4 de `DEC-0012`
n'est pas rouvert ; seul le constat du deplacement et l'etat des quatre reserves restantes sont
ajoutes. Voir la section "Amendement du 2026-08-07 (s06)" dans
`DEC-0012-rhabillage-gabarit-menu-minimal-et-ouverture-s06.md`.

Amendement (pas une nouvelle reference) : `DEC-0015` a ete amendee le 2026-08-10, sur avis de
faisabilite de `theo-archi` (`ADR-0026`), qui mesure qu'a la largeur cible de `DEC-0007` point 5
(375 px), trois colonnes tiennent sans abaisser aucun plancher mais que les quatre propositions de
"Le bon verbe" debordent, et route la contradiction entre l'arbitrage 3 de `DEC-0015` (le nombre
de colonnes suit le contenu, jamais fige a trois) et la reintegration de "Le bon verbe" au
catalogue. `DEC-0015` maintient ses quatre arbitrages et ses quatre stories inchanges, et tranche
que "Le bon verbe" ne rejoint pas le catalogue en rouleaux tant que la reduction de son nombre de
propositions (jugement pedagogique, a `nadia-prof`) ou un repli de mise en page (forme, a
`ines-design`) n'a pas ete choisi ; le debordement assume a 375 px est explicitement exclu. Voir la
section "Amendement du 2026-08-10" dans
`DEC-0015-sprint-7-la-vraie-machine-a-sous-ferme-la-dette-1.md`.

Amendement (pas une nouvelle reference) : `DEC-0015` a ete amendee une seconde fois le 2026-08-10,
sur l'arbitrage prealable de `nadia-prof` (branche `s07-nadia`, commit `04c6b53`,
`squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md` et
`FICHE-bandit-manchot-additions-cp.md`). Elle accepte de ramener "Le bon verbe" a trois
propositions, sur son propre motif (le pli deja mesure a 360 x 640), et fixe nommement les douze
triplets ; mais elle mesure que cette reduction ne fait pas tenir le paquet en colonnes (neuf items
sur douze debordent encore a 360 px, deux rendent des propositions indiscernables). `DEC-0015`
corrige en consequence : la reduction a trois est acquise sur le fond pedagogique et insuffisante
sur la largeur, elle ne ferme donc plus a elle seule la condition de remise au catalogue ; seuls le
repli de mise en page (`ines-design`, jugee par `nadia-prof`) ou un report du jeu peuvent encore la
fermer. Confirme aussi que la metaphore de machine a sous est acceptee seulement diminuee sous
quatre conditions executables et qu'aucune colonne vide n'est acceptee pour completer un compte
fixe - verifie sans y trouver rien a corriger contre S7-1 a S7-4. Voir la section "Second amendement
du 2026-08-10" dans `DEC-0015-sprint-7-la-vraie-machine-a-sous-ferme-la-dette-1.md`.

Amendement (pas une nouvelle reference) : `DEC-0015` a ete amendee une troisieme fois le
2026-08-10, sur `ADR-0027` de `theo-archi` (branche `theo-s07-machine-a-sous`, commit `9c2f1c2`),
qui ferme techniquement le defaut route par `nadia-prof` (la reprise silencieuse de S1-4 rendait la
manche en clair, sans geste, ce qui aurait fait tourner les colonnes toutes seules) par un marqueur
serveur garde et mordu sur trois mutations. Il route la question de retirer ce rendu en clair a la
racine plutot que de le neutraliser par le marqueur. `DEC-0015` tranche que rien ne change ce
sprint : le marqueur ferme deja le defaut, et retirer le rendu en clair serait un geste de dette sans
necessite etablie, qui exigerait soit une verification technique de `theo-archi` (chemin de
remplacement htmx) soit un arbitrage prealable de `nadia-prof` (retrait sans remplacement, qui
defait un acquis de S1-4) - ni l'un ni l'autre n'est saisi ce sprint. Voir la section "Troisieme
amendement du 2026-08-10" dans `DEC-0015-sprint-7-la-vraie-machine-a-sous-ferme-la-dette-1.md`.

Amendement (pas une nouvelle reference) : `DEC-0015` a ete amendee une quatrieme fois le
2026-08-10. `nadia-prof` eteint sa propre acceptation de reduire "Le bon verbe" a trois
propositions - son motif visait une cause fausse (elle a mesure le meme defaut de pli sur "Le bon
petit mot", paquet a deux propositions et jeu du catalogue), et elle refuse d'inventer un motif de
remplacement. `ines-design` a par ailleurs trouve le repli de mise en page (deux colonnes par
rangee des la quatrieme proposition), que `nadia-prof` remesure et confirme sur le fond, mais sur
une maquette jetable, non sur le code livre. `DEC-0015` en tire que sa condition de remise au
catalogue n'a plus qu'une seule sortie candidate (le repli, une fois livre et reverifie par
`nadia-prof` sur le rendu reel), et qu'elle ne se ferme pas aujourd'hui. Un second constat de
`nadia-prof` a cette occasion - un trou de decision produit sur la hauteur de viewport de reference
et sur ce qui doit rester au-dessus du pli, qui touche cette fois un jeu deja catalogue ("Le bon
petit mot") - est traite separement dans `DEC-0016`. Voir la section "Quatrieme amendement du
2026-08-10" dans `DEC-0015-sprint-7-la-vraie-machine-a-sous-ferme-la-dette-1.md`.

Correction documentaire (pas un amendement de fond) : `DEC-0017` numerote ses chantiers 1, 2 et 4,
sans jamais ecrire de chantier 3 - signale a l'ouverture du sprint 9. Verifie contre le depot avant
d'ecrire cette ligne : aucun livrable n'est manquant, le backlog du sprint 8 porte exactement les
stories S8-1 a S8-5 (S8-5 ajoutee ensuite par `DEC-0018`, hors numerotation de chantier de
`DEC-0017`), et aucune ligne de `squad/programme/MATRICE-couverture.md` ne depend d'un chantier 3
qui n'existerait pas. Un paragraphe de clarification est ajoute a `DEC-0017` a cette date ; son
contenu (chantiers 1, 2 et 4) n'est pas rouvert. Voir la section "Correction documentaire du
2026-08-12" dans
`DEC-0017-perimetre-sprint-8-la-machine-retrouve-son-contexte-et-trois-verites-corrigees.md`.

Amendement (pas une nouvelle reference) : `DEC-0016` a ete amendee le 2026-08-11, sur remesure de
`nadia-prof` du meme defaut sur "Le bon petit mot" (jeu catalogue) et "Le bon verbe" (activation
refusee, `DEC-0018`). Corrige un ecart de denominateur entre les deux fractions publiees ailleurs
(11 sur 30, sur l'ensemble des etats d'une partie ; 24 sur 25, sur les seuls etats avec indice) :
ramenees a la meme base (etats avec indice, seule base pertinente puisque le defaut n'existe
jamais sans indice), l'ecart reel est de 50 % contre 96 %, un facteur deux et non un ordre de
grandeur. `DEC-0016` ouvre une story au backlog portant les deux jeux ensemble, sans fixer de
seuil chiffre : ce jugement pedagogique reste celui de `nadia-prof`, comme le point 3 de la
decision d'origine le posait deja, et un seuil que je fixerais unilateralement recompenserait qui
le contourne plutot que qui corrige la cause. Aucun des deux jeux n'est refuse sur ce seul defaut,
maintien assume par ecrit. Voir la section "Amendement du 2026-08-11 (s08)" dans
`DEC-0016-hauteur-de-viewport-de-reference-et-contenu-au-dessus-du-pli.md`.

Amendement (pas une nouvelle reference) : `DEC-0016` a ete amendee une seconde fois le 2026-08-12,
pendant l'arbitrage prealable du sprint 10. `nadia-prof` corrige une attribution (ce constat n'est
pas la reserve 1 de `squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`, qui est un
fait distinct - `mangeons` a 2,0 px) et ses propres chiffres du 2026-08-11, inexacts dans les deux
sens apres nouvelle mesure sur le rendu : 5 / 29 etats coupes par le pli sur "Le bon petit mot"
(etait 11 / 30) et 16 / 34 sur "Le bon verbe" (etait 24 / 33). Le sens du constat ne change pas et
la story ouverte au backlog reste ouverte dans les memes termes. Voir la section "Amendement du
2026-08-12 (s10)" dans
`DEC-0016-hauteur-de-viewport-de-reference-et-contenu-au-dessus-du-pli.md`.

Amendement (pas une nouvelle reference) : `DEC-0019` a ete amendee le 2026-08-12, sur avis de
faisabilite de `theo-archi` rendu apres son ecriture. Son avis contredit le motif technique donne pour
differer les lignes 1 (denombrer) et 6 (monnaie) - un pictogramme-caractere de quantite est
techniquement portable sans code, et la matiere premiere de la monnaie ("utiliser" seul) est prouvee
par du contenu deja joue en production sur `main` - sans contredire la conclusion : les deux lignes
restent `non couvert` ce sprint, sur un motif desormais pedagogique (recevabilite et fiabilite de
rendu d'un pictogramme, risque de couverture partielle si "utiliser" ouvrait seul) et non plus
technique. Aucune ligne n'est ouverte, aucune capacite n'y est affectee. Voir la section "Amendement
du 2026-08-12" dans
`DEC-0019-perimetre-sprint-9-quatre-lignes-textuelles-de-mathematiques-cp-denombrement-et-monnaie-differes.md`.
