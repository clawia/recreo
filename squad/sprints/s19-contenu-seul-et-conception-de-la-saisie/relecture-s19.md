# Relecture pedagogique - s19 (contenu seul et conception de la saisie)

- **Auteur** : relecteur-pedagogique. **Date** : 2026-08-24.
- **Branche** : `s19-relecteur`, worktree dedie, lignee verifiee par `outils/verifier-lignee.sh`.
- **Arbre relu** : `round-two` a `86837e1`.
- **Premiere intervention du sprint** : aucune trace de mon invocation n'existe avant ce fichier, et
  le rapport de tokens de s18 le confirme deja ("Aucune invocation mesuree sur cette fenetre pour :
  `yanis-qa`, `relecteur-pedagogique`").

## Perimetre relu

Dans l'ordre du brief : `DEC-0042`, `DEC-0043`, `ADR-0058` a `ADR-0060`, `ADR-index.md`,
`DEC-index.md`, l'integralite de `squad/sprints/s19-contenu-seul-et-conception-de-la-saisie/`
(`planning.md`, `avis-faisabilite.md`, `confirmation-archunit.md`, `rapport-qualite.md`,
`proposition-ines-plafond-syn16.md`, `proposition-marc-antonymes-cm1.md`,
`proposition-s19-4-lea-dev.md`, les trois journaux de fusion), les trois
`ARBITRAGE-PREALABLE-s19-*.md`, les quatre `FICHE-bandit-manchot-*` neuves, les quatre paquets YAML
servis et leurs entrees de `catalogue.yaml`, la section "Sprint 19" de `docs/backlog/backlog.md`, et
les sections s19 de `squad/programme/MATRICE-couverture.md`.

## Ce que j'ai corrige

Neuf fichiers touches, uniquement du texte - aucune ligne de code, aucun critere, aucun chiffre
pedagogique modifie.

1. **`squad/decisions/adr/ADR-index.md`** - une ligne vide coupait le tableau des ADR entre la ligne
   `ADR-0055` et la ligne `ADR-0056` (Markdown : un tableau qui reprend sans en-tete apres une ligne
   blanche cesse d'etre lu comme le meme tableau). Ligne blanche retiree. Ce defaut n'est pas ne ce
   sprint - `git blame` le date du premier commit du depot - mais il touche un fichier que compulsent
   les six lignes `ADR-0058` a `ADR-0060` livrees cette semaine ; corrige a cette occasion plutot que
   laisse pourrir.
2. **`squad/sprints/.../proposition-ines-plafond-syn16.md`** - deux mots portaient un accent
   (`mêmes`, `propriété`) dans un document integralement redige sans accent comme le reste du corpus
   squad (registre developpeur, `CLAUDE.md`). Corrige en `memes`, `propriete`.
3. **`squad/sprints/.../fusion-lots-7-a-9.md`** - meme defaut isole, un mot (`indépendamment`) accentue
   au milieu d'un texte ASCII. Corrige en `independamment`.
4. **`squad/conformite/ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md`**, section 5 - l'exemple de
   retour donne a l'enfant citait *"Oui, c'est bien eleve - regarde, il y a deux accents"* sans aucun
   accent sur le mot que la phrase demande justement de regarder. Contenu destine a l'enfant, donc
   sous l'exigence commune de `DEC-0002` (francais integralement accentue) : corrige en *"Oui, c'est
   bien élève"*. Les occurrences volontairement fautives ailleurs dans le meme fichier (*"Tu as écrit :
   elleve. On écrit : élève."*, ou `elleve` represente la faute de l'enfant) ne sont pas touchees :
   elles jouent un role different et sont deja coherentes avec elles-memes.
5. **`squad/conformite/FICHE-bandit-manchot-antonymes-cm1.md`** - accord manquant, *"(memes motif)"*
   corrige en *"(meme motif)"*.
6. **`docs/backlog/backlog.md`**, critere d'acceptation de `S19-4` - *"representes par au moins la
   moitie moins un quart des items"* est une facon de dire *"un quart"* (1/2 - 1/4 = 1/4) qui oblige le
   lecteur a faire le calcul pour comprendre un seuil simple, contre l'exigence de clarte pour un
   lecteur qui n'a pas de contexte externe. Simplifie en *"un quart"* : meme valeur, aucune ligne de
   critere deplacee.
7-9. **Les trois fiches de conformite restees factuellement perimees apres livraison** -
   `FICHE-bandit-manchot-nature-mot-cp.md`, `FICHE-bandit-manchot-accord-sujet-verbe-cm1.md`,
   `FICHE-bandit-manchot-accords-groupe-nominal-cm1.md`. Verifie contre le depot : les trois paquets
   YAML existent et sont serves (`cp-nature-mot.yaml` 16 items, `cm1-accord-sujet-verbe.yaml` 21 items,
   `cm1-accords-groupe-nominal.yaml` 24 items), confirme par les commits `99d0a75`, `1ff200d`,
   `1a53ee4`. Les bullets "Contenu(s)" (encore `a creer`) et "Statut global" (encore *"ouverte, aucun
   increment livre a ce jour"*) n'avaient pas ete mis a jour au moment du depot du paquet - `git show`
   sur les trois commits confirme qu'ils n'ont touche aucune de ces fiches, ou (pour `cp-nature-mot`)
   n'ont touche que les bullets d'arbitrage sans revenir sur "Contenu(s)"/"Statut global". Corrige pour
   que la fiche decrive ce que le code fait reellement, sur le modele deja tenu par
   `FICHE-bandit-manchot-antonymes-cm1.md` (qui, elle, avait ete tenue a jour par son auteur).

## References croisees verifiees

Toutes les references que j'ai pu tester existent et pointent au bon endroit : `ADR-0004`, `ADR-0006`,
`ADR-0008`, `ADR-0013`, `ADR-0016`, `ADR-0024`, `ADR-0025`, `ADR-0028`, `ADR-0039`, `ADR-0044` a
`ADR-0046`, `ADR-0049`, `ADR-0051`, `ADR-0052`, `ADR-0054` a `ADR-0057`, `DEC-0002`, `DEC-0016`,
`DEC-0019`, `DEC-0025`, `DEC-0029`, `DEC-0030`, `DEC-0039`, `DEC-0040`, `DEC-0041`, les trois
`ARBITRAGE-PREALABLE-s19-*.md`, les quatre `FICHE-bandit-manchot-*` neuves et anciennes citees,
`AVIS-PRIORITE-PEDAGOGIQUE-s13-seconde-mecanique.md`, `avis-ordre-de-grandeur-seconde-mecanique.md`,
les trois fichiers `contenu-repris/` cites par les deux arbitrages de `nadia-prof` (verifie en plus
le contenu exact de `cp-formes.yml` : il associe bien un glyphe carre a la cible "rectangle", comme
`DEC-0042` et l'arbitrage l'affirment), et le libelle exact de la ligne "Relire et corriger son texte"
dans `squad/programme/francais/competences-cm1.md` (verifie caractere pres caractere, sans "accords" -
la correction que `camille-po` s'attribue dans l'amendement de `DEC-0042` est donc exacte).

**Aucune reference cassee trouvee.** Contrairement au risque nomme dans le brief (`ADR-0045`
introuvable pendant deux jours), rien de comparable n'a survecu jusqu'a ce commit. `ADR-index.md` et
`DEC-index.md` portent des lignes coherentes avec les fichiers qu'elles indexent, y compris les six
lignes ecrites cette semaine.

## Verifie contre le code : le contenu servi a l'enfant

Les quatre paquets neufs (`cm1-antonymes.yaml`, `cm1-accords-groupe-nominal.yaml`,
`cm1-accord-sujet-verbe.yaml`, `cp-nature-mot.yaml`) et les cinq consignes de `catalogue.yaml` qui les
accompagnent sont en francais integralement accentue, orthographe et ponctuation verifiees phrase par
phrase (accords sujet-verbe des 21 items de `cm1-accord-sujet-verbe.yaml` recontroles un par un).
Guillemets francais et espaces insecables (U+00A0) verifies par script sur les dix-sept items
d'antonymes et les seize items de nature du mot : aucune anomalie. Aucune notation de caractere
(sequence d'echappement, entite HTML) trouvee dans les quatre paquets - seule occurrence du motif
`U+00A0`, un commentaire de code, hors du perimetre que juge `ADR-0060`. Le contenu livre correspond
exactement aux corrections que les deux arbitrages de `nadia-prof` demandaient (items 5, 8, 9, 13, 15
des antonymes ; retrait de "joue"/"lit", remplacement de l'item 11, des cp-nature-mot) : je l'ai
recompare ligne a ligne plutot que de le supposer.

Le contenu servi ne releve pas de mon registre de style (`DEC-0002`) au-dela de cette exigence
commune ; je ne me suis pas prononce sur le niveau scolaire, la justesse pedagogique ou le choix des
mots, qui restent a `nadia-prof`.

## Ce que je route, sans le corriger moi-meme

- **A `sofia-sm`** : `squad/sprints/.../proposition-marc-antonymes-cm1.md` est redige integralement
  sans accent, y compris les dix-huit phrases candidates elles-memes (destinees a devenir du contenu
  servi a l'enfant), alors que le document equivalent de `lea-dev`
  (`proposition-s19-4-lea-dev.md`) accentue ses phrases candidates en respectant deja l'exigence de
  `DEC-0002`. Je n'ai pas reecrit les dix-huit phrases de `marc-dev` : c'est un document de travail non
  servi, son contenu reel (`cm1-antonymes.yaml`) est correctement accentue, et une reecriture d'ampleur
  d'un draft appartenant a un autre agent deborderait mon mandat. Je signale l'incoherence de pratique
  entre les deux drafts du meme sprint pour que la squad choisisse une convention commune sur ce genre
  de document.
- **A `theo-archi` (via `sofia-sm`)** : la section "Lecture rapide" de `ADR-index.md` s'arrete a
  `ADR-0055` (item 52) ; `ADR-0056` a `ADR-0060`, dont les trois ADR de ce sprint, n'y ont pas
  d'entree. Je ne sais pas si cette section a vocation a suivre chaque ADR ou si elle s'est arretee
  deliberement : je le nomme plutot que de trancher, et je n'ai touche a aucune ligne de cette section.

## Avis sur la lisibilite d'ensemble du corpus de s19

**Dense mais suivable, et la densite est justifiee la ou elle existe.** Les trois ADR pesent chacun
15 a 20 pages effectives (notation, arithmetique de pixels, table de decision), mais chacun est
structure en Decision / Regle executable / Consequences / Alternatives considerees, avec des chiffres
qui portent systematiquement leur commande et leur methode - un developpeur qui arriverait dans six
mois n'a jamais a deviner comment un nombre a ete obtenu. L'arithmetique de pixels d'`ADR-0059` est
l'exemple le plus dense du sprint et reste lisible parce qu'elle introduit sa notation (`V`, `P`, `L`,
`R`) une fois et s'y tient jusqu'au bout.

**La pratique d'amendement a sa date, plutot que de reecriture, tient sa promesse ici.** `DEC-0042` a
recu deux amendements et une note le jour meme de son ecriture ; chacun est introduit par une phrase
qui dit explicitement ce qui devient faux dans le texte au-dessus et pourquoi ("la synthese ci-dessus
reste lisible telle qu'ecrite au moment de l'ouverture..."). Meme discipline dans
`avis-faisabilite.md` (section 10, `theo-archi` nomme deux phrases fausses de ses propres sections 1
et 3, sans les effacer) et dans `MATRICE-couverture.md`. Dans les neuf fichiers que j'ai lus en
integralite pour verifier cette pratique, je n'ai trouve aucun cas ou un lecteur ne pourrait pas dire
quel est l'etat courant d'une decision - le seul defaut de cette nature que j'ai trouve n'etait pas un
probleme d'amendement mais l'inverse : trois fiches de conformite jamais mises a jour du tout apres la
livraison du contenu qu'elles decrivent (voir "Ce que j'ai corrige", points 7 a 9). C'est le meme
risque que le brief signale sous un autre angle : une lecture successive d'un fichier amende reste
sure tant que chaque amendement date ce qu'il change ; une fiche qu'on oublie de rouvrir ne le signale
jamais.

**Le seul point qui a demande plusieurs lectures croisees avant de conclure** est la chronologie de
`DEC-0043`, qui denoue en un seul fichier trois questions ouvertes par trois documents differents
(l'avis de `theo-archi`, la mesure de `nadia-prof` sur une branche non fusionnee, une condition posee
le jour meme par `nadia-prof` sur la saisie). Le fichier le dit lui-meme et cite ses trois sources
avec leur commit ; ce n'est pas un defaut de redaction, c'est la nature du sujet qu'il tranche.

## Ce que j'ai trouve de faux dans le brief

Rien. Les deux exemples que le brief signale "sans prejuger" se verifient tous deux : la ligne de
tete de `avis-faisabilite.md` de `theo-archi` est bien declaree fausse par ses propres amendements
(section 10.1, sur `S19-1` et `S19-3`), et `DEC-0042` porte bien deux amendements dates du jour de son
ecriture (section "Amendement du 2026-08-24 (meme jour, second passage)" et "... troisieme passage").
L'affirmation que le sprint precedent s'est clos sans moi est confirmee mot pour mot par
`squad/sprints/s18-fermer-avant-ouvrir/RAPPORT-tokens.md`.
