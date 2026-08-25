# [Sprint s20] Planning - mot cible designe et conception de la saisie

- **Horodatage de debut** : 2026-08-25 17:00 +0200
- **Anime par** : sofia-sm, casquette Scrum Master.
- **Repertoire de sprint** : `s20-mot-cible-designe-et-conception-de-la-saisie`, deja cree par le
  lot de `theo-archi` (`avis-faisabilite.md`, `confirmation-archunit.md`). Je le garde tel quel.

## Ordre du process respecte

`camille-po` a instruit le perimetre (`DEC-0047` et son amendement), `nadia-prof` a rendu en
parallele le troisieme amendement de son arbitrage prealable sur la saisie (`SAI-2a/2b/2c`, `R6`),
`theo-archi` a rendu son avis de faisabilite sur les quatre chantiers avant tout commit de code de
story. Je gele, affecte et ecris.

## Lots fusionnes avant ce planning, provenance verifiee

Les quatre lots forkent du meme point (`c48c0e6`, `git merge-base round-two <branche>` rend ce SHA
pour les quatre - `bash squad/outils/sofia-sm/verifier-provenance-lots.sh` confirme les quatre
lignees et les quatre tetes annoncees). Fusion sequentielle, aucun conflit sur aucun des quatre
merges :

1. **`camille-po`** : `s20-camille` (`82de824`, `e8f1c92`, `11b96a0`). `DEC-0047`, backlog, matrice,
   `DEC-index`, fiche des reperes.
2. **`theo-archi`** : `s20-theo` (`7a398b9`). Avis de faisabilite, `confirmation-archunit.md`,
   `ADR-0062`, amendements a `ADR-0058`, `ADR-0059` et `ADR-index`.
3. **`nadia-prof`** : `hs-nadia-arbitrages` (`9707538`). Amendements de `SAI-2` (`SAI-2a`/`2b`/`2c`),
   condition `R6`, notes sur les proxys.
4. **`ines-design`** : `hs-ines-nat5` (`e5a8360`). Mesure du cout du renfort visuel, proposition
   `.mot-designe`, script de mesure.

## Porte technique rejouee sur l'arbre fusionne, par ma propre methode

`bash outils/mvn-jdk21.sh -B clean verify` a la racine du reacteur (complet, donc pas de `-am` a
citer) : **BUILD SUCCESS**, six modules `SUCCESS` (`recreo`, `recreo-domain`, `recreo-application`,
`recreo-adapter-web`, `recreo-adapter-content`, `recreo-bootstrap`).

**668 tests, 0 echec** - lu sur les lignes de recapitulation de surefire et de failsafe du meme
journal, apres le `clean` de la commande ci-dessus : `recreo-domain` 48, `recreo-application` 13,
`recreo-adapter-web` 328, `recreo-adapter-content` 181, `recreo-bootstrap` 92 (surefire) + 6
(failsafe). 48+13+328+181+92+6 = **668**. C'est le meme chiffre que `theo-archi` annonce dans son
avis de faisabilite et dans `confirmation-archunit.md` ; je le confirme ici par ma propre mesure sur
l'arbre fusionne des quatre lots, je ne le recopie pas (regle de s16). Aucun des quatre lots ne
touche de code ni de test : le compte est identique a celui de `s20-theo` seul.

## Objectif gele

**Le mot cible de "Nom ou verbe ?" cesse de reposer sur les seuls guillemets francais, sur un
contrat de contenu qui ne coute rien aux jeux qui ne designent rien ; une competence neuve du
referentiel CM1 (reperer et corriger la majuscule et la ponctuation) entre en contenu seul ; et la
conception de la mecanique de saisie reprend, sans code, sur la question laissee ouverte par le
clavier qui corrige. Le correctif de largeur `S19-6a` est suspendu, sa piste verticale en cours de
chiffrage par `theo-archi`, hors de ce planning tant qu'elle n'est pas rendue.**

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
   reel.
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
| **`S20-1`** geste 2 - forme visuelle | idem | `ines-design` | Regle CSS a confirmer sur `cp-nature-mot` seul une fois le contrat reel disponible ; proposition deja mesuree par injection. | Deux canaux non colores, zero item sur 16 qui bascule a deux lignes a 744 x 551. |
| **`S20-1`** geste 3 - reaudition | idem | `nadia-prof` | Sans objet (porte pedagogique). | Reaudit complet de `bandit-manchot-nature-mot-cp`, verdict ecrit dans la fiche. |
| **`S20-1`** geste 4 - retour catalogue | idem | `sofia-sm` | Sans objet. | Entree `catalogue.yaml` au meme geste que la fusion d'un verdict favorable. |
| **`S20-2`** - reperes CM1 (`cm1-reperes`) | Francais, CM1, "Orthographe et grammaire", ligne detachee et amendee par `DEC-0046` | `lea-dev` | Faisable en l'etat, aucun `ADR` du. `REP-1` a `REP-4` deja instruits par `nadia-prof` avant tout code. | `mvn verify` vert (garde-fous listes ci-dessus). Puis Done pedagogique de `nadia-prof` contre `REP-1` a `REP-4`. |
| **Piste verticale de `S19-6a`** | Transverse, aucune ligne de matrice | **Non affectee** | En cours de chiffrage par `theo-archi`, en parallele de ce planning. Ne pas affecter tant que l'avis n'est pas rendu (consigne explicite de `camille-po` et de `theo-archi`). | Avis de faisabilite ecrit, puis affectation par `sofia-sm` si retenue. |
| **`S19-6b`** (`BPM-8`) | Transverse | **Non affectee, differee** | Infaisable ce sprint : une mesure prise aujourd'hui decrirait une mise en page que `S19-6a` va changer. A remesurer une fois un correctif de `S19-6a` effectivement fusionne. | Sans objet ce sprint. |
| **Conception de la saisie** | Transverse, chantier documentaire | `theo-archi`, `nadia-prof` - **aucun developpeur affecte** | Chantier de conception, pas de story. `SAI-2c` amende `ADR-0058` (la chaine jugee voyage sur le verdict, pas sur `derniereTentative`) : deja fait dans le lot fusionne. Reste a instruire : la consequence des trois issues possibles de `SAI-2` sur la conception, tranchee par `nadia-prof`. | Pas de porte de sortie testable : decisions et arbitrages, sur le meme mode qu'a s19. |

**Capacite.** `marc-dev` et `lea-dev` portent chacun un chantier de code independant (aucun fichier
partage entre `S20-1` et `S20-2`, contrairement au conflit double de `S19-5`/Lot A a s19) : les deux
peuvent avancer en parallele. Les gestes 2 a 4 de `S20-1` restent strictement sequentiels apres la
fusion du geste 1, et n'attendent pas `S20-2`.

## Tableau des obstacles

| Obstacle | Categorie | Destinataire | Etat |
|---|---|---|---|
| Piste verticale de `S19-6a` non chiffree, correctif de largeur exclu par l'inegalite du plafond (`P >= 687 + R`) | Technique | `theo-archi` | Ouvert, chiffrage en cours en parallele de ce planning. Pas d'affectation tant que l'avis n'est pas rendu. |
| Avis de faisabilite sur `BPM-8` (`S19-6b`) | Technique | `theo-archi` | Clos : avis rendu (infaisable ce sprint, motif de sequencement). Story reste non affectee, candidate d'un sprint suivant apres fusion d'un correctif de `S19-6a`. |
| Trois arbitrages routes a l'utilisatrice par `DEC-0047` (saisie en conception seule, clavier ordinateur/tablette avec correction acceptee, produit qui ne s'adresse jamais a l'adulte) | Produit | `camille-po` | Clos : les trois reponses sont rendues et tracees dans l'amendement du 2026-08-25 a `DEC-0047`. |
| Issue de `SAI-2` face au clavier correcteur (la condition tient, est amendee, ou disparait) | Pedagogie | `nadia-prof` | Clos pour ce planning : tranchee dans le meme lot (`SAI-2a`/`2b`/`2c`, la chaine jugee voyage sur le verdict). Reste a instruire : sa consequence complete sur la conception de la saisie, dans le chantier documentaire. |
| Declencheur de la dette verticale d'`ADR-0059` porte par le premier lot qui touche un terme vertical - `S20-1` et `S19-6a` touchent tous deux ce flux dans le meme sprint | Technique | `theo-archi` (deja corrige dans son lot : declencheur porte par le **dernier** des deux lots fusionnes) | Clos pour la redaction. **Point de controle qui m'appartient au fil du sprint** : je note quel lot fusionne en second entre `S20-1` et un eventuel correctif de `S19-6a`, et je verifie qu'aucun des deux n'est fusionne en declarant la dette fermee. |
| Deux affirmations chiffrees fausses dans `ADR-0059` ("141 sur 144" recopie dans l'index ; "elles changent une ligne de constante chacune") | Technique | `theo-archi` | Clos : trouvees et corrigees par `theo-archi` lui-meme dans le lot fusionne (`ADR-0059` et `ADR-index` amendes). Aucune action restante. |
| `camille-po` a tranche le residu de `S19-6a` (3 px sur 144) sur un chiffre que son propre amendement du meme jour (`DEC-0043`, portant `cm1-accord-sujet-verbe` a 647 px) avait deja perime | Produit | `camille-po` | Clos : corrige le meme jour dans l'amendement de `DEC-0047` (residu reel 111 px sur 144, piste de largeur suspendue). |
| Premiere commande de mesure ArchUnit ciblee de `theo-archi` a rendu `BUILD SUCCESS` en executant zero test (`-Dtest='com.recreo.archi.*'` sans le suffixe `Test`, combine a `-Dsurefire.failIfNoSpecifiedTests=false`) | Qualite, mesure | `yanis-qa` | Verse a son dossier pour le rapport qualite de la review : ce n'est pas mon lot a corriger, c'est un mode de defaillance de conduite de mesure (un vert sans son compte de tests) a garder visible plutot qu'a taire. Pas de code a corriger. |

## Ce que j'ai trouve de faux dans le brief qui m'ouvre ce sprint

Rien. Les trois erreurs de chiffre et l'episode du faux vert ArchUnit que le brief annonce sont tous
verifies dans les lots fusionnes (`avis-faisabilite.md` section 6, `confirmation-archunit.md`,
l'amendement de `DEC-0047`) : je ne les recopie pas sur parole, je les ai relus a la source et je
les verse au tableau des obstacles ci-dessus plutot que de les laisser vieillir sans destinataire.
