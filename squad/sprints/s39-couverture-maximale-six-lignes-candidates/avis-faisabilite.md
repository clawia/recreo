# s39 - Avis de faisabilite (`theo-archi`) - `S39-ENERGIE` et `S39-OUTILS`

- **Date** : 2026-09-22
- **Base** : `round-two` a `5a7a2312`, stories de `camille-po` dans `docs/backlog/backlog.md`,
  section "Sprint 39" (non commitees a l'heure de cet avis).
- **Perimetre de cet avis** : **deux** lots sur les six, ceux que `DEC-0092` dispense d'arbitrage
  pedagogique prealable - `S39-ENERGIE` (`SCI-CM1-04`) et `S39-OUTILS` (`ART-CP-11`). Les quatre
  autres (`S39-DEN`, `S39-LONGUEUR`, `S39-MONNAIE`, `S39-SOLIDES`) sont en arbitrage prealable
  chez `nadia-prof` et **ne sont pas instruits ici**.
- **Ce que cet avis ne vaut pas** : il ne vaut pas livraison. L'affectation nominative du
  developpement revient a `sofia-sm` (`DEC-0092`, section 5, reconduisant `DEC-0091`). Aucun code
  n'est ecrit avec cet avis.

---

## 1. Verdicts

**`S39-ENERGIE` : faisable en l'etat, sans `ADR` neuf et sans regle neuve.** C'est le cas le plus
banal du depot : un paquet statique de type `question` de plus dans un lot de matiere deja servi
(`Sciences et technologie` / `CM1`, 8 paquets aujourd'hui). Complexite **basse**.

**`S39-OUTILS` : faisable en l'etat, sans `ADR` neuf et sans regle neuve, mais ce n'est pas le
meme cas.** Le mecanisme est identique ; ce qui change est que **`Arts plastiques` serait la
treizieme matiere du corpus servi et la premiere de cette matiere**. Complexite **basse cote
architecture**, le cout reel etant editorial et pedagogique. Section 4.

## 2. Le mecanisme existe deja, et aucun `ADR` neuf n'est du

La question posee - *existe-t-il un mecanisme du produit qui couvre le choix textuel pour du
contenu de connaissance fixe, sans code neuf ?* - a deja trois reponses ecrites, et elles se
composent :

- **`ADR-0004`** decouple la mecanique du contenu : `bandit-manchot` ne connait pas la matiere
  qu'elle sert.
- **`ADR-0007`** impose **un seul chargeur pilote par un schema** : un paquet de type `question`
  entre par sa donnee, pas par une classe.
- **`ADR-0096`** a acte que **le paquet statique est la norme du depot**, le generatif
  l'exception, et que le choix du moyen est reversible sans toucher aux garde-fous.

**Preuve, sur du materiau reel plutot que sur mon estimation.** Commit `712c293b` (`[LEA]`, s30),
qui a livre deux paquets de sciences CM1 servant `SCI-CM1-05` et `SCI-CM1-17` - exactement le
regime que la story de `S39-ENERGIE` invoque : **7 fichiers, 512 lignes ajoutees, zero ligne de
Java de production**.

Je n'ecris donc **aucun `ADR`** pour ces deux lots. Un `ADR` qui redirait ce que `ADR-0004`,
`ADR-0007` et `ADR-0096` decident deja serait un `ADR` decoratif, et ecrire un `ADR` apres coup
pour documenter une pratique deja reglee est precisement ce que mon mandat m'interdit.

**Ce qui me ferait changer d'avis, ecrit maintenant** : si `nadia-prof` refusait la liste finie au
motif de la repetition sur une partie de dix manches, la question du generatif se rouvrirait - et
elle se paierait alors en production seule, les garde-fous portant la propriete et jamais le moyen
(`ADR-0096`, decision 3).

## 3. `S39-ENERGIE` (`SCI-CM1-04`) - fichiers concernes

Derives du commit `712c293b`, un paquet au lieu de deux :

| Fichier | Nature |
|---|---|
| `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-<nom>.yaml` | paquet neuf, `type: question`, `lignesDeReferentiel: ["SCI-CM1-04"]` |
| `adapters/adapter-content/src/main/resources/catalogue.yaml` | une entree, titre et consigne propres |
| `adapters/adapter-content/src/test/java/com/recreo/adapter/content/PaquetCm1Sciences<Nom>Test.java` | garde-fou du paquet |
| `adapters/adapter-content/src/test/java/com/recreo/adapter/content/LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java` | inventaire d'exemption du domaine numerique, une entree avec son motif **mesure** |
| `bootstrap/src/test/java/com/recreo/bootstrap/catalogue/TexteAccentueExposeParLePortEntrantTest.java` | inventaire des chaines exposees par le port entrant |

**Zero fichier de production Java, zero schema, zero contrat.**

## 4. `S39-OUTILS` (`ART-CP-11`) - ce qui differe, et ce qui ne differe pas

**Ce qui ne differe pas** : la meme liste de fichiers qu'en section 3, avec
`adapters/adapter-content/src/main/resources/contenu/cp-arts-<nom>.yaml` et
`lignesDeReferentiel: ["ART-CP-11"]`.

**Ce qui differe : `Arts plastiques` serait une matiere neuve du corpus servi.** Comptage du
2026-09-22 sur `adapters/adapter-content/src/main/resources/contenu/` (78 fichiers lus, descripteur
par descripteur) : **12 couples (matiere, niveau) servis, aucun en arts plastiques**. J'ai verifie
ce que cela coute **structurellement**, et la reponse est : rien.

1. **Aucune enumeration de matieres en production.** `matiere` est un champ de
   `domain/.../ContentDescriptor` ; aucune classe de production, aucun gabarit Thymeleaf, aucune
   feuille de style ne nomme une matiere. Recherche sur `"Questionner le monde"` - la derniere
   matiere neuve entree (s27) - hors du repertoire de contenu : **que des tests**.
2. **Aucun garde-fou a perimetre enumere par matiere.**
   `UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest` (`ADR-0049`) ne porte aucune liste de
   matieres : il exige une seule graphie par matiere, ce qu'une matiere neuve satisfait par
   construction. `UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest` (`ADR-0079`)
   derive ses lots des descripteurs ; ses assertions de rapprochement ne portent que sur le lot
   `Questionner le monde / CP` et un lot neuf ne les touche pas.
3. **Le code de ligne est deja alloue.**
   `ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest` (`ADR-0081`) lit les codes dans
   `squad/programme`, jamais dans une liste. `ART-CP-11` y est
   (`squad/programme/arts-plastiques/competences-cp.md`, ligne 47) et `SCI-CM1-04` aussi
   (`squad/programme/sciences-et-technologie/competences-cm1.md`, ligne 50). Rien a allouer.

**Le cout reel de ce lot est donc editorial, pas architectural**, et il vit sous une contrainte que
le lot sciences n'a pas : **c'est du CP**.

## 5. Garde-fous qui mordent sur ces deux lots

Aucun n'est a ecrire ; tous sont a tenir. Ceux qui decident la forme du paquet, donc a lire
**avant** d'ecrire un item et non apres un rouge :

| Regle | Ce qu'elle impose ici |
|---|---|
| `ADR-0023` | le paquet offre **strictement plus** d'items que la partie n'a de manches : 11 items pour `nombreDeManches: 10`, ce que font les paquets de sciences deja fermes |
| `ADR-0063` | au plus **trois** propositions par item |
| `ADR-0032` / `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` | deux items d'un meme paquet ne presentent jamais le meme ecran |
| `ADR-0086` / `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` | la longueur ni la forme des propositions ne designent la bonne reponse |
| `ADR-0067` (`TYP-1`) | blanc insecable avant `?` et `:` dans tout enonce francais neuf |
| `ADR-0065` / `AucunMotServiNeSEcarteDeSonAccentTest` | chaque mot servi est juge sur le lexique exterieur - **point de vigilance sur le vocabulaire plastique** (`esquisse`, `argile`, `fusain`) |
| `ADR-0049` | `Arts plastiques` s'ecrit d'une seule graphie dans toute la donnee |
| **`ADR-0089`** | **`S39-OUTILS` seulement** : au CP, **au plus quinze mots et deux phrases par enonce** (`AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest`). Contrainte reelle sur une definition de vocabulaire - *"Avec quel outil peint-on ?"* passe, une glose ne passe pas |

Conditions pedagogiques opposables, rappelees pour memoire et **non arbitrees par moi** : `ART-1`
(aucune reponse portee par une couleur percue) et `ART-2` (aucune question sur le ressenti) sur
`S39-OUTILS` ; `SCI-1` a `SCI-4` et `FER-1` a `FER-3` sur `S39-ENERGIE`. `nadia-prof` a deja tarife
les arts plastiques CP a *"non au-dela d'`ART-1`"* (`squad/conformite/ARBITRAGE-PREALABLE-s32-trois-
instances-ma-borne-a-douze-le-terrain-sans-image-et-le-prix-d-une-ligne-marginale.md`, ligne 524) :
c'est ce qui rend l'absence d'arbitrage prealable coherente, et non une omission.

## 6. Risques

**R1 - l'inventaire d'exemption du domaine numerique pourrit en silence (les deux lots).**
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` demande un **motif mesure** par paquet
exempte. Ce fichier porte deja la trace de sa propre derive : les motifs de trois paquets
`cp-qlm-*` dataient du 2026-09-02 et n'avaient pas suivi le lot de s28 qui les a portes de huit a
quatorze items, **deux d'entre eux etant faux sur la forme** ; recomptes le 2026-09-10. Un motif
recopie d'un paquet voisin au lieu d'etre compte sur le paquet livre reproduit ce defaut.
**Parade** : compter sur le paquet servi, ecrire le compte et sa date.

**R2 - `ART-CP-11` est du vocabulaire, et le vocabulaire se heurte a `ADR-0089` (S39-OUTILS).**
C'est le seul endroit ou un rouge est previsible. Il est mecanique et se voit au premier `verify`.

**R3 - `SCI-CM1-04` touche a l'energie, ou la simplification fausse est facile (S39-ENERGIE).**
*"Le soleil produit de l'electricite"* est le genre d'item qu'un garde-fou ne verra jamais et que
`nadia-prof` refusera. Hors de ma main, nomme ici pour qu'il ne soit pas decouvert au Done.

**R4 - `-am` sur toute mesure partielle** (`CLAUDE.md`). `mvn -pl adapters/adapter-content test`
sans `-am` mesure le depot local, pas le worktree. La porte reste `mvn -B clean verify` sur la
racine, et la porte technique est **l'ensemble des jobs de `.github/workflows/ci.yml`**, pas
`mvn verify` seul.

## 7. Ce que cet avis ne dit pas

Il ne dit pas quels faits, quels outils ni quels exemples de conversion retenir - cela reste a
l'auteur du paquet puis a `nadia-prof`. Il ne prononce aucun Done pedagogique. Il ne dit pas si
ces deux lignes passeront `couvert-valide` : `FER-2` et l'audit d'items sont dans la seule main de
`nadia-prof`.
