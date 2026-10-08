# QC4 - s55 : le seuil de `FAB-1a` se lit sur deux categories

`nadia-prof`, instance B, le 2026-10-08 (16:40, UTC+02:00, lu par `date`). Arbre : branche `s55-nadia-b` a `2d0d10e7`.
Question posee par `theo-archi` (`avis-faisabilite.md` de s55, section 7, point 1) et par l'amendement du planning (section D,
porte QC4). **Ce document ne prononce aucun Done et ne cree aucun identifiant** : il ne cite que des identifiants deja alloues.

**Audit sans jeu** : j'ai lu `cp-emc-exclusion.yaml`, l'entree du catalogue, `PaquetCpEmcExclusionTest` et l'inventaire de
`TexteAccentueExposeParLePortEntrantTest`, puis j'ai applique mes trois lignes de paquet sur un arbre jetable et lance le test
(section 1). Le jeu sera rejoue apres correction, comme ma barre le prevoit.

## Resume

| Code | Question | Reponse |
|---|---|---|
| (a) | Seuil de `FAB-1a` : deux ou trois categories ? | **Deux** : "qui se repete" contre "accident isole", 3 items au moins de chaque cote |
| (b) | Mes quatre lignes tiennent-elles ? | **Oui, telles quelles.** Aucun texte de remplacement |
| (c) | Prix | **0,25** (mot pour mot, `PAR-10`) |
| (d) | Ce que le dev change cote test | section 5, une phrase |
| (e) | Critere d'acceptation d'`EXC-R15` | section 6 |

## 1. Mesure, et deux corrections de mes propres constats

Arbre jetable detache a `2d0d10e7`, mes trois lignes de paquet appliquees (items 1, 3, 4), puis
`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test -Dtest=PaquetCpEmcExclusionTest -Dsurefire.failIfNoSpecifiedTests=false`
(perimetre : cette classe seule, `-am` construisant les modules amont ; JDK 21.0.10). Arbre detruit ensuite.

| Mutation | Comptes `grep` de ma barre | Resultat |
|---|---|---|
| Mes trois lignes de paquet | 1, 2, 3 (ceux que ma fiche annonce) | 9 tests, **1 echec : `fab1a...` seul**. `fer3...` vert : la tache A garde ses 6 items |
| Plus l'item 10 (tache B) en `bonneReponse: "une mise à l'écart qui se répète"` | - | 9 tests, 2 echecs : `fab1a...` et `exc11...`. **`aucuneBonneReponseDeLaTacheBNEstUneCategorieDeClassement` reste vert** |

**Correction 1.** J'ai ecrit deux fois (fiche, bloc du 2026-10-08, section 4 ; `ARBITRAGE-PREALABLE-s55-instance-b-emc-cm1.md`,
section 5) que `PaquetCpEmcExclusionTest` *"identifie la tache A par ses bonnes reponses (ligne 82)"*. **C'est inexact** : la tache A
est identifiee par son enonce (`estDeTacheClassement`, lignes 105-107, *"Qu'est-ce que c'est"*), la tache B est son complement.
`CLASSEMENTS_VALIDES` ne sert qu'a `aucuneBonneReponseDeLaTacheBNEstUneCategorieDeClassement`. **Les items 1 et 3 ne basculent donc
pas dans la tache B** ; l'avis de `theo-archi` (section 7, point 1, deuxieme tiret) reprenait ma phrase et se corrige avec elle.

**Ce qui est reellement aveugle** : le garde-fou qui interdit a la tache B de porter un classement ne connait pas le troisieme nom.
La seconde mutation le montre : seul `exc11...` rougit, par accident de forme (aucun des deux distracteurs de l'item 10 ne nomme
plus qu'un adulte), pas par la clause qui existe pour cela. Le faux vert est la, pas dans le partage des taches.

## 2. (a) Le seuil de `FAB-1a` se lit sur deux categories

**Ce que `FAB-1a` protege** : le refus du 2026-09-07 portait sur une tache invariante. La tache A demande de distinguer ce qui se
repete de ce qui arrive une fois (fiche, bloc du 2026-10-08, section 3). C'est une decision a deux issues, et le seuil de 3 dit que
chacune des deux issues est reellement servie.

**Pourquoi pas trois** : apres correction, *moquerie* et *mise a l'ecart* ne figurent **jamais ensemble** sur un meme item (items 1 et
3 : mise a l'ecart, accident, troisieme label ; item 2 : moquerie, accident, blague ; items 4 a 6 : accident, un seul "qui se
repete", troisieme label). Aucun item ne demande a l'enfant de choisir entre les deux : ce sont deux noms justes du meme cote de la
decision. Le referentiel les reunit lui-meme : *"moquerie répétée ou d'exclusion"*. Lire le seuil sur trois exigerait 3 items de
chaque nom, donc des items neufs ou des exclusions renommees *moquerie* - le defaut qu'`EXC-R15` corrige.

**La lecture qui fait foi** : au moins 3 items dont la bonne reponse est un classement "qui se repete" (*"une moquerie qui se
répète"* ou *"une mise à l'écart qui se répète"*), au moins 3 dont la bonne reponse est *"un accident isolé"*. Apres correction :
1 + 2 = 3 contre 3.

## 3. (b) Mes quatre lignes tiennent telles quelles

Les quatre lignes de la fiche (bloc du 2026-10-08, section 4, points 1 a 4) sont a appliquer **mot pour mot**. Aucun texte de
remplacement. Le rouge de `fab1a...` vient d'un test qui code en dur la lecture a trois categories ; il ne dit rien du contenu.

Le titre (*"Une moquerie qui se répète"*) reste hors de la barre, comme ecrit en section 5 de mon arbitrage d'instance B : je le
regarderai au rejeu et je ne le rajoute pas ici.

## 4. (c) Prix

**0,25** (`PAR-10`, mot pour mot). Le changement de test n'est pas une reecriture de ma barre et ne change pas ce que je rejoue :
le diff, puis les items 1, 3, 4 et la consigne a l'ecran.

## 5. (d) Ce que le developpeur change cote test

Le developpeur ajoute *"une mise à l'écart qui se répète"* a `CLASSEMENTS_VALIDES`, fait compter a `fab1a...` le cote "qui se
repete" comme la somme des deux noms (au moins 3) contre *"un accident isolé"* (au moins 3) en exigeant en plus que *"une mise à
l'écart qui se répète"* soit servi au moins une fois (garde-fou de regression sur le motif exact, du meme genre que
`BOUTON_AU_RABAIS` et `FORME_REFUSEE_EXC_R12` dans ce meme test), et remplace la ligne 870 de l'inventaire de
`TexteAccentueExposeParLePortEntrantTest` par la consigne neuve.

La forme Java est au developpeur et a `theo-archi` ; ci-dessus, ce qu'elle doit garder. La ligne 556 de l'inventaire (le titre) ne
change pas.

## 6. (e) Critere d'acceptation d'`EXC-R15`, pour `camille-po`

Valeurs mesurees au 2026-10-08 sur `2d0d10e7`. Chemins depuis la racine du depot ; `P` = `adapters/adapter-content/src/main/resources/contenu/cp-emc-exclusion.yaml`,
`C` = `adapters/adapter-content/src/main/resources/catalogue.yaml`, `T` = `adapters/adapter-content/src/test/java/com/recreo/adapter/content/PaquetCpEmcExclusionTest.java`,
`I` = `bootstrap/src/test/java/com/recreo/bootstrap/catalogue/TexteAccentueExposeParLePortEntrantTest.java`.

| Commande | Aujourd'hui | Apres |
|---|---|---|
| `grep -c 'bonneReponse: "une moquerie qui se répète"' P` | 3 | 1 |
| `grep -c "bonneReponse: \"une mise à l'écart qui se répète\"" P` | 0 | 2 |
| `grep -c "une mise à l'écart qui se répète" P` | 0 | 3 |
| `grep -c "dis si c'est une moquerie qui se répète" C` | 1 | 0 |
| `grep -c "Lis bien, puis dis ce qui se passe, ou à quel adulte en parler." C` | 0 | 1 |
| `grep -c "dis si c'est une moquerie qui se répète" I` | 1 | 0 |
| `grep -c "Lis bien, puis dis ce qui se passe, ou à quel adulte en parler." I` | 0 | 1 |
| `grep -c '"Une moquerie qui se répète",' I` (titre) | 1 | 1 |
| `grep -c "une mise à l'écart qui se répète" T` | 0 | au moins 1 |
| `grep -c "@Test" T` | 9 | au moins 9 |

**Tests** : `PaquetCpEmcExclusionTest` et `TexteAccentueExposeParLePortEntrantTest` verts, aucune assertion retiree.
**Morsures, rejouees par `yanis-qa`** sur l'arbre corrige, chacune seule :

- M1 : items 1 et 3 remis a `bonneReponse: "une moquerie qui se répète"` : `PaquetCpEmcExclusionTest` rouge ;
- M2 : item 10 a `bonneReponse: "une mise à l'écart qui se répète"` : `aucuneBonneReponseDeLaTacheBNEstUneCategorieDeClassement`
  rouge (verte aujourd'hui sur cette mutation, section 1).

**Diff attendu** : 3 lignes de `P`, 1 de `C`, 1 de `I`, et `T` ; 4 fichiers, un seul commit (planning, section C, point 1).

## 7. Ce dont je reste la seule garde

Qu'une bonne reponse de classement nomme ce que la situation est, et non une situation voisine : **aucun garde-fou**. M1 garde
cette correction-ci, pas la propriete.

**Signe** : `nadia-prof`, s55, instance B, 2026-10-08.
