# [Sprint s20] Planning - mot cible designe et conception de la saisie

- **Horodatage de debut** : 2026-08-25 17:00 +0200
- **Mise a jour du 2026-08-25 20:32 +0200**, avant toute cloture de cette ceremonie et avant tout
  travail de developpement lance dessus : deux lots supplementaires sont arrives (`theo-archi`
  chiffre la piste verticale, `camille-po` fait trancher l'utilisatrice sur la bande). Je reprends
  ce meme fichier plutot que d'ecrire un second planning : la ceremonie n'a jamais ete rapportee ni
  close, ce n'est pas une nouvelle ceremonie qui commence, c'est la meme qui integre un fait
  survenu avant sa fin.
- **Anime par** : sofia-sm, casquette Scrum Master.
- **Repertoire de sprint** : `s20-mot-cible-designe-et-conception-de-la-saisie`, deja cree par le
  lot de `theo-archi` (`avis-faisabilite.md`, `confirmation-archunit.md`). Je le garde tel quel.

## Ordre du process respecte

`camille-po` a instruit le perimetre (`DEC-0047` et ses amendements), `nadia-prof` a rendu en
parallele le troisieme amendement de son arbitrage prealable sur la saisie (`SAI-2a/2b/2c`, `R6`),
`theo-archi` a rendu son avis de faisabilite puis chiffre la piste verticale qu'il ouvrait. Je gele,
affecte et ecris.

## Lots fusionnes, provenance verifiee

Six lots au total, tous forkes du meme point (`c48c0e6`, `git merge-base round-two <branche>` rend
ce SHA pour chacun - `bash squad/outils/sofia-sm/verifier-provenance-lots.sh` confirme chaque
lignee et chaque tete annoncee). Fusion sequentielle, aucun conflit sur aucune des six fusions :

1. **`camille-po`** : `s20-camille` (`82de824`, `e8f1c92`, `11b96a0`). `DEC-0047`, backlog, matrice,
   `DEC-index`, fiche des reperes.
2. **`theo-archi`** : `s20-theo` (`7a398b9`). Avis de faisabilite, `confirmation-archunit.md`,
   `ADR-0062`, amendements a `ADR-0058`, `ADR-0059` et `ADR-index`.
3. **`nadia-prof`** : `hs-nadia-arbitrages` (`9707538`). Amendements de `SAI-2` (`SAI-2a`/`2b`/`2c`),
   condition `R6`, notes sur les proxys.
4. **`ines-design`** : `hs-ines-nat5` (`e5a8360`). Mesure du cout du renfort visuel, proposition
   `.mot-designe`, script de mesure.
5. **`theo-archi`** : `s20-theo` (`3cf891b`, au-dessus de `7a398b9`). La piste verticale chiffree
   sur l'application servie : elle ne ferme pas la bande, un second amendement d'`ADR-0059`, deux
   outils sous `squad/outils/theo-archi/`.
6. **`camille-po`** : `s20-camille` (`7bec9d5` puis `aaf874a`, au-dessus des trois deja fusionnes).
   L'amendement de `DEC-0040` (la bande sort de la cible sur arbitrage de l'utilisatrice), notes
   datees a `DEC-0041` et `DEC-0043`, troisieme amendement de `DEC-0047`, `S19-6a` et `S19-6b`
   retirees du backlog, note de matrice, puis une correction du meme jour par `camille-po`
   elle-meme (dix jeux `couvert-valide`, pas deux).

## Porte technique rejouee sur l'arbre fusionne, par ma propre methode

`bash outils/mvn-jdk21.sh -B clean verify` a la racine du reacteur (complet, donc pas de `-am` a
citer) : **BUILD SUCCESS**, six modules `SUCCESS`.

**668 tests, 0 echec** - lu sur les lignes de recapitulation de surefire et de failsafe du meme
journal : `recreo-domain` 48, `recreo-application` 13, `recreo-adapter-web` 328,
`recreo-adapter-content` 181, `recreo-bootstrap` 92 (surefire) + 6 (failsafe). 48+13+328+181+92+6 =
**668**. Chiffre inchange depuis ma premiere mesure de ce planning : les deux lots supplementaires
sont documentaires, aucun ne touche de code ni de test (verifie par `git diff --stat` sur chacun
avant fusion). Je remesure quand meme sur l'arbre fusionne plutot que de le deduire.

## L'arbitrage de l'utilisatrice qui reoriente ce sprint

`theo-archi` a chiffre la piste verticale qu'il avait lui-meme rouverte : **elle ne ferme pas la
bande**. A son budget honnete (16 px, sans toucher a un autre ecran de l'application), elle ferme 19
items sur 134 dans la bande 576-719 px ; a son plafond absolu (136 px, en rouvrant un meuble deja
arbitre trois fois par `nadia-prof`), elle en ferme 114 sur 134 et laisse vingt items dont le pire
manque encore 172 px ; elle ne ferme **aucun** des dix items a trois rangees de propositions, le
terme `.propositions` y depassant a lui seul le budget entier. Les trois pistes envisagees depuis
`S19-6` (largeur, verticale, puis la troisieme lecture nommee sans etre choisie) etaient donc toutes
chiffrees, et aucune ne fermait `SYN-16` a la totalite des items, seul critere que la story portait.

**L'utilisatrice a tranche, verbatim** : *"amende `DEC-0040`, la bande passe hors cible."* La bande
576-719 px - une fenetre de navigateur non maximisee ou partagee, sur un ordinateur de la cible -
sort du perimetre produit. `camille-po` ecrit elle-meme que cet arbitrage **reverse** le sien propre
de la cloture de s18 (`DEC-0041`), et le nomme comme un renversement plutot que comme une nuance. La
reference unique du produit (744 x 551 px, `DEC-0040`) ne bouge pas ; seule la bande secondaire sort
de la cible. `S19-6a` et `S19-6b` sont **retirees** du backlog affecte : le viewport qu'elles
visaient n'est plus dans la cible du produit.

## Le perimetre se resserre a trois chantiers

`S20-1` (`NAT-5` en trois gestes), `S20-2` (les reperes CM1) et la conception de la saisie sans code
restent inchanges par cet arbitrage. Le sequencement de `S20-1`, le piege du cliquet d'`ADR-0050` et
les garde-fous mordants de `S20-2`, tous portes dans la premiere version de ce planning, ne bougent
pas : voir les sections dediees plus bas.

**Je ne reaffecte pas la capacite liberee par le retrait de `S19-6a`/`S19-6b`, et je suis en cela la
demande explicite de `camille-po` (`DEC-0047`, troisieme amendement, section 4) plutot que de m'en
ecarter.** Son motif tient a la relecture, pas seulement a sa demande : `S19-6a` etait deja
**suspendue** avant cet arbitrage, non affectee a un developpeur dans la premiere version de ce
planning (je l'avais explicitement classee "non affectee, en cours de chiffrage" plutot que confiee
a `marc-dev` ou `lea-dev`). Il n'y a donc, de mon propre planning, aucune capacite de developpement a
liberer : `marc-dev` et `lea-dev` restent sur les memes deux chantiers qu'a la premiere version de ce
document. Je n'ouvre pas non plus la mesure de `BPM-8` en story malgre le fait, verse par
`theo-archi`, que "Le bon petit mot" aurait ete le cas le moins cher des dix jeux touches dans la
bande retiree : la bande qui motivait cette mesure n'est plus visee, et personne ne me route cette
ouverture comme une instruction nouvelle.

## Objectif gele

**Le mot cible de "Nom ou verbe ?" cesse de reposer sur les seuls guillemets francais, sur un
contrat de contenu qui ne coute rien aux jeux qui ne designent rien ; une competence neuve du
referentiel CM1 (reperer et corriger la majuscule et la ponctuation) entre en contenu seul ; et la
conception de la mecanique de saisie reprend, sans code, sur la question laissee ouverte par le
clavier qui corrige. La bande 576-719 px sort du perimetre produit (amendement de `DEC-0040`) ;
aucun correctif de pli n'est du ce sprint.**

Aucune ligne de la matrice de couverture ne passe `couvert-valide` par ce seul planning : `S20-1`
leve une reserve bloquante sans a elle seule fermer sa ligne (deja `en cours` depuis `S19-4`),
`S20-2` porte une ligne `non couvert` -> `en cours` deja actee par `DEC-0047`. Le denominateur reste
43 lignes atteignables, le compte `couvert-valide` reste 13 avant tout mouvement de ce sprint
(13/43, 30,2 %).

## Le sequencement de `S20-1` - le seul chantier qui traverse toute la squad

Trois gestes, chez trois agents differents, strictement sequentiels : chacun ne peut commencer
qu'une fois le precedent livre, parce que chacun a besoin de l'objet que le precedent produit.

1. **Geste 1 - le contrat de contenu (`marc-dev`, sur `ADR-0062`).** `Question` gagne une
   composante facultative unique (`partDesignee`), l'enonce reste la chaine entiere, le controleur
   derive une suite de segments consommee par le gabarit en une boucle unique. Regle executable
   deja ecrite et rouge (`LaPartDesigneeDUnItemServiEstMarqueeALEcranTest`,
   `UnePartDesigneeApparaitUneSeuleFoisDansSonEnonceTest`) : ce lot les fait passer au vert, dans le
   meme commit que le contenu de `cp-nature-mot` qui declare sa part.
2. **Geste 2 - la forme visuelle (`ines-design`).** Une fois le mot cible expose distinctement dans
   le rendu servi, elle pose la regle CSS (`.mot-designe` est un candidat mesure, pas une valeur
   figee) sur au moins deux canaux non colores. Elle ne peut pas commencer avant que le geste 1 ne
   soit fusionne : sa mesure du 2026-08-25 portait sur une injection manuelle, pas sur le contrat
   reel. **Obligation neuve, portee par le second amendement d'`ADR-0059`** : `.mot-designe` touche
   un terme vertical du flux au-dessus du verdict (`font-size: 1.15em` sur `.enonce`, +7,19 px
   uniformement et jusqu'a +55,19 px sur un item a deux lignes, deja mesure par `ines-design`). Ce
   geste fait donc tourner `bash squad/outils/theo-archi/campagne-budget-vertical.sh` avant et
   apres son changement, et porte les deux chiffres dans son commit - pas un garde-fou automatique,
   une mesure obligatoire que `theo-archi` a substitue a la table qu'il avait promise et qu'il a
   renoncee a ecrire (elle serait restee verte pendant que ce geste pousse des items sous le pli).
3. **Geste 3 - la reaudition (`nadia-prof`).** Reaudit complet de `bandit-manchot-nature-mot-cp`
   contre toutes les reserves de sa fiche, `NAT-5` en tete, une fois la forme posee. Verdict
   favorable ou non.
4. **Geste 4 - le retour au catalogue (`sofia-sm`).** Au meme geste que la fusion d'un verdict
   favorable, l'entree revient dans `catalogue.yaml` (mecanisme deja etabli par `DEC-0045` : absence
   d'entree, pas de drapeau). Si `nadia-prof` refuse, le jeu reste hors catalogue et la reserve
   rouverte est nommee dans la fiche.

**Le piege que je porte a `marc-dev`, pour qu'il ne le decouvre pas par un rouge.** Ajouter la
composante `partDesignee` a `Question` rend rouge
`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest`
(`chaqueComposanteDUnItemEstClasseeDansLIdentiteDEcranOuHorsDElle`) : c'est le cliquet d'`ADR-0050`,
qui refuse qu'une composante entre sans que personne ait dit si l'enfant la voit. **La reponse est
dans le meme commit que le geste 1, pas un rouge a corriger apres coup** : la part designee entre
dans l'identite d'ecran - deux items de meme enonce et memes propositions qui designent deux mots
differents sont deux ecrans differents (`ADR-0062`, section 4).

**Ce que le geste 1 ne touche pas** : `cm1-synonymes` et `cm1-antonymes` ne declarent rien, restent
un segment unique, et `ADR-0062` verifie explicitement qu'aucune marque n'apparait sur ces deux
paquets. Portee decidee par `DEC-0047` : le renfort n'entre en service que sur `cp-nature-mot` ce
sprint.

## Garde-fous mordants - portes telles quelles, la liste n'est pas exhaustive

`theo-archi` le nomme lui-meme : l'enumeration complete des garde-fous qu'une story fait bouger
n'est pas atteignable par construction, une bonne partie du perimetre etant derive du repertoire de
contenu plutot que d'une liste. Ce qui suit sont ceux dont **une story de ce sprint change le
perimetre d'une facon qui demande une ecriture**, pas ceux qui jugeront sans rien demander.

**Pour `S20-2` (`lea-dev`, `cm1-reperes`) :**

1. **`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`** - une ecriture est due. Ce
   garde-fou tient une table nommee des paquets hors du domaine numerique ; les propositions de
   `cm1-reperes` sont des phrases, donc le paquet doit y entrer avec son comptage reel, mesure sur
   le paquet servi, dans le commit qui le livre. Sans cette ecriture, l'exclusion est silencieuse.
2. **`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest`** - juge sans ecriture, mais la marge est
   plus mince que d'habitude. `REP-4` fait porter aux items les memes quatre propositions : deux
   items ne se distinguent que par leur phrase. A dire a l'auteur du contenu avant qu'il ecrive.
3. **`AucunIndiceNeContreditUnAutreItemDuMemePaquetTest`** - ne mord pas ce paquet (aucun indice de
   reperes ne peut prendre la forme litterale de conjugaison qu'il detecte), mais il porte un
   cliquet a zero : si un indice prenait par hasard cette forme, la CI rougirait sur le cliquet et
   non sur la contradiction. A savoir, pas a corriger d'avance.

**Pour `S20-1` (`marc-dev`) :**

4. **`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest`** - voir le piege ci-dessus, reponse due
   dans le meme commit que le geste 1.

## Tableau des stories - affectation nominative

| Story | Competence programme visee | Affectation | Avis de faisabilite (`theo-archi`) | Porte de sortie |
|---|---|---|---|---|
| **`S20-1`** geste 1 - contrat `ADR-0062` (mot cible, "Nom ou verbe ?") | Francais, CP, "Distinguer les principaux types de mots" - leve la derniere reserve de `S19-4` | `marc-dev` | Faisable moyennant `ADR-0062`, ecrit dans ce lot. Regle executable deja posee, rouge sur l'arbre d'aujourd'hui. | `mvn verify` vert, les deux clauses d'`ADR-0062` passent au vert, cliquet d'`ADR-0050` classe dans le meme commit. |
| **`S20-1`** geste 2 - forme visuelle | idem | `ines-design` | Regle CSS a confirmer sur `cp-nature-mot` seul une fois le contrat reel disponible ; proposition deja mesuree par injection. | Deux canaux non colores, zero item sur 16 qui bascule a deux lignes a 744 x 551 ; les deux passages de `campagne-budget-vertical.sh` portes dans le commit. |
| **`S20-1`** geste 3 - reaudition | idem | `nadia-prof` | Sans objet (porte pedagogique). | Reaudit complet de `bandit-manchot-nature-mot-cp`, verdict ecrit dans la fiche. |
| **`S20-1`** geste 4 - retour catalogue | idem | `sofia-sm` | Sans objet. | Entree `catalogue.yaml` au meme geste que la fusion d'un verdict favorable. |
| **`S20-2`** - reperes CM1 (`cm1-reperes`) | Francais, CM1, "Orthographe et grammaire", ligne detachee et amendee par `DEC-0046` | `lea-dev` | Faisable en l'etat, aucun `ADR` du. `REP-1` a `REP-4` deja instruits par `nadia-prof` avant tout code. | `mvn verify` vert (garde-fous listes ci-dessus). Puis Done pedagogique de `nadia-prof` contre `REP-1` a `REP-4`. |
| **Conception de la saisie** | Transverse, chantier documentaire | `theo-archi`, `nadia-prof` - **aucun developpeur affecte** | Chantier de conception, pas de story. `SAI-2c` amende `ADR-0058` (la chaine jugee voyage sur le verdict, pas sur `derniereTentative`) : deja fait dans le lot fusionne. Reste a instruire : la consequence des trois issues possibles de `SAI-2` sur la conception, tranchee par `nadia-prof`. | Pas de porte de sortie testable : decisions et arbitrages, sur le meme mode qu'a s19. |

**`S19-6a` et `S19-6b` sont retirees de ce tableau** : la bande qu'elles visaient sort de la cible
du produit (amendement de `DEC-0040`). Elles n'etaient affectees a aucun developpeur dans la
premiere version de ce planning ; leur retrait ne libere donc aucune capacite, et je n'en reaffecte
aucune ailleurs (voir section "Le perimetre se resserre" ci-dessus).

**Capacite.** `marc-dev` et `lea-dev` portent chacun un chantier de code independant (aucun fichier
partage entre `S20-1` et `S20-2`, contrairement au conflit double de `S19-5`/Lot A a s19) : les deux
peuvent avancer en parallele. Les gestes 2 a 4 de `S20-1` restent strictement sequentiels apres la
fusion du geste 1, et n'attendent pas `S20-2`.

## Tableau des obstacles

| Obstacle | Categorie | Destinataire | Etat |
|---|---|---|---|
| **95 items sur 296 (32 %), sur dix jeux, ont deja leur bloc de message sous le pli a la reference du produit (744 x 551) ; les dix jeux sont tous `couvert-valide`** | Pedagogie | `nadia-prof` | **Ouvert, le plus lourd du sprint.** Retirer la bande retire la part aggravee du defaut (134 items dans la bande), pas le defaut a la reference. Route par `theo-archi` puis `camille-po` : dire lesquels de ces 95 items relevent d'une reserve deja acceptee et lesquels n'ont jamais ete regardes. **Je ne le transforme pas en story** : personne n'a qualifie ces items, `camille-po` ne prononce aucun statut de couverture dessus et je ne lui en pretend pas un. |
| Piste verticale de `S19-6a`, chiffree | Technique | `theo-archi` | Clos : chiffree (19 items sur 134 au budget honnete, 114 sur 134 au plafond absolu, zero des dix items a trois rangees), elle ne ferme pas la bande. La bande sort de la cible avant que la question de la retenir se pose. |
| Arbitrage de l'utilisatrice sur la bande 576-719 px | Produit | `camille-po` | Clos : *"amende `DEC-0040`, la bande passe hors cible"*, trace dans l'amendement du 2026-08-25 (troisieme) a `DEC-0040`, avec note datee versee a `DEC-0041` et `DEC-0043`. `S19-6a`/`S19-6b` retirees du backlog. |
| `DEC-0043` avait scinde `S19-6` au motif de "mecanismes differents" | Technique | `theo-archi` (deja verse) | Clos pour la redaction : mesure a l'appui, meme ecran, meme cause (bloc de message sous le pli), meme population de dix jeux - note datee versee a `DEC-0043`, section d'origine non reecrite. |
| Chiffre de 43,19 px (puis 68,19 px) utilise trois sprints pour comparer les pistes de correctif | Technique | `theo-archi` (deja corrige) | Clos : c'etait le bas du texte du verdict, pas du bloc de message entier - l'indice, objet meme de `BPM-8`, portait le deficit reel a 152,19 px sur le meme item. Le deficit de la population vaut 308,19 px, pas 43,19. Corrige dans la mesure du budget vertical et le second amendement d'`ADR-0059`. |
| Trois arbitrages routes a l'utilisatrice par `DEC-0047` (saisie en conception seule, clavier ordinateur/tablette avec correction acceptee, produit qui ne s'adresse jamais a l'adulte) | Produit | `camille-po` | Clos : les trois reponses sont rendues et tracees dans l'amendement du 2026-08-25 a `DEC-0047`. |
| Issue de `SAI-2` face au clavier correcteur (la condition tient, est amendee, ou disparait) | Pedagogie | `nadia-prof` | Clos pour ce planning : tranchee dans le meme lot (`SAI-2a`/`2b`/`2c`, la chaine jugee voyage sur le verdict). Reste a instruire : sa consequence complete sur la conception de la saisie, dans le chantier documentaire. |
| Deux affirmations chiffrees fausses dans `ADR-0059` ("141 sur 144" recopie dans l'index ; "elles changent une ligne de constante chacune") | Technique | `theo-archi` | Clos : trouvees et corrigees par `theo-archi` lui-meme dans son premier lot du jour (`ADR-0059` et `ADR-index` amendes). |
| `camille-po` a tranche le residu de `S19-6a` (3 px sur 144) sur un chiffre que son propre amendement du meme jour avait deja perime | Produit | `camille-po` | Clos : corrige le meme jour, avant meme le retrait de la bande. |
| `camille-po` a cite deux jeux `couvert-valide` (`cm1-synonymes`, `cm1-antonymes`) sur les dix touches par la mesure a la reference | Produit | `camille-po` (deja corrige) | Clos : verifiee ligne par ligne contre `MATRICE-couverture.md` par `camille-po` elle-meme, plutot que reprise du brief qui la routait - les dix jeux sont `couvert-valide`, pas deux. Un second chiffre invente sans verification ("295 items sur 296") est retire dans le meme commit. |
| Premiere commande de mesure ArchUnit ciblee de `theo-archi` a rendu `BUILD SUCCESS` en executant zero test | Qualite, mesure | `yanis-qa` | Verse a son dossier pour le rapport qualite de la review : mode de defaillance de conduite de mesure, pas de code a corriger. |

## Ce que j'ai trouve de faux dans le brief qui reoriente ce sprint

Rien qui reste ouvert. Le brief attribue a `camille-po` la verification ligne par ligne des dix
jeux `couvert-valide` : verifiee, exacte, et c'est bien elle qui l'a faite, dans son propre commit
de correction (`aaf874a`) plutot que sur ma seule lecture du brief. Les deux corrections de fond
nommees (le deficit reel de 152,19 px sur l'item qui avait servi de comparaison a 43,19 px ; le
motif de scission de `DEC-0043` etabli faux) sont verifiees a la source dans
`mesure-budget-vertical.md` et dans les notes datees versees a `DEC-0043`. Je ne les recopie pas sur
parole : je les ai relues et je les verse au tableau des obstacles ci-dessus, closes cote redaction,
avec leurs suites encore ouvertes (la lecture des 95 items) portees a leur veritable destinataire.
