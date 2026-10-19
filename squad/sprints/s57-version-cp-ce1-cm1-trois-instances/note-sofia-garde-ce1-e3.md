# [Sprint s57] Note de routage - ecart E3, garde generique de LON-CE1-3 a -5 et TRV-CE1-1

- **Date** : 2026-10-19.
- **Auteure** : `sofia-sm`, casquette Scrum Master. Cette note route, elle ne tranche pas.
- **Source** : `avis-theo-preparation-palier-3-ce1.md`, section 3.2 ligne `G5` et question `Q4`.
- **Lancement** : aucun `mvn` lance. Tous les constats ci-dessous sont des lectures du depot (`git grep`, `ls`, lecture de fichiers), pas des verdicts de build.

## 1. Etat mesure

Base : `round-two` a `ad65827e`.

| Constat | Commande | Rendu |
|---|---|---|
| Tests citant `LON-CE1-3`, `-4` ou `-5` | `git grep -lE "LON-CE1-[345]" -- '*Test.java'` | 19 fichiers, tous `PaquetCe1*Test` (adapter-content). Aucun garde generique |
| Tests citant `TRV-CE1-1` | `git grep -l "TRV-CE1-1" -- '*Test.java'` | 18 fichiers |
| Tests `PaquetCe1*Test` au total | `git ls-files` filtre sur `PaquetCe1.*Test.java` | 31 |
| Paquets `ce1-*.yaml` | `ls` du repertoire `contenu` | 47 |
| Paquets CE1 absents de `catalogue.yaml` | boucle `grep -q "paquet: <id>$"` | 12 |
| Garde generique existant de meme famille | `AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest` (bootstrap, 367 lignes) | couvre `LON-CE1-1` et `-2`, perimetre derive du descripteur `niveau: "CE1"` |

Perimetre des regles, lu dans les arbitrages :

- `LON-CE1-5` : francais seulement a s48 (mathematiques explicitement exclues, population non mesuree). L'arbitrage s57 (`ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md`, ligne I1) la reprend pour QLM et EMC.
- `LON-CE1-3` et `-4` : `texteSupport`, portes par `FRA-CE1-04` a `-07` seulement.
- `TRV-CE1-1` : aucun champ `texteAVoixHaute` au CE1. Des gardes generiques sur ce champ existent pour le CP (`AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest`, `LeTexteAVoixHauteEtLaConsigneAdulteSeDeclarentEnsembleTest`), je n'ai pas lu s'ils bornent le niveau.
- Les chiffres "0 proposition de plus de 6 mots" et le reste de la mesure de `theo-archi` sont les siens, hors depot, par script. Je ne les ai pas rejoues.

## 2. Formes possibles

| Code | Forme | Ce qu'elle couvre | Ce qu'elle ne couvre pas |
|---|---|---|---|
| F1 | Test generique neuf, perimetre derive du descripteur `niveau: "CE1"`, sur le patron de `G4` | Les 47 paquets actuels et tout paquet futur, francais, QLM et EMC | Rien, si le perimetre par matiere est fixe. La borne par matiere reste a decider (voir section 4) |
| F2 | Etendre `AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest` | Meme couverture que F1, un seul fichier de perimetre | Melange deux familles de regles (enonce, proposition) dans un test de 367 lignes. Son cliquet de dette (`ce1-longueurs`) et son nom, qui dit "enonce", sont a ajuster |
| F3 | Rien : un `PaquetCe1<Nom>Test` par paquet, comme jusqu'ici | Les paquets pour lesquels un test est ecrit | Les 12 paquets non branches (aucun n'a de test de ce type verifie ici) et tout oubli futur. Le trou est silencieux : un paquet sans test passe |

## 3. Cout estime

Estimations de planning, non mesurees. Aucune de ces formes n'a ete ecrite.

| Forme | En poste (un developpeur) | En lot |
|---|---|---|
| F1 | un test neuf, a l'echelle de `G4` (entre 200 et 370 lignes) avec ses cas de rejet. `LON-CE1-3` et `-4` limitees aux paquets qui portent un `texteSupport`. Ampleur **M** selon l'echelle de `theo-archi` | un lot, a placer apres `RL5`, `RL6`, `RL7` (`K3`) |
| F2 | un peu moins qu'F1 en code, plus en relecture du cliquet existant | un lot, meme ordre |
| F3 | zero pour le garde, un test par paquet a chaque paquet branche (31 tests de ce genre existent pour 47 paquets) | repart sur chaque lot de contenu du palier 3 : au moins 9 paquets QLM et 4 EMC prevus |

Effet sur le contenu existant : si F1 ou F2 est retenue, elle s'applique aussi aux 12 paquets non branches. L'avis de `theo-archi` rapporte zero faute par script ; un cliquet a zero serait possible. A confirmer par la mesure du reacteur complet au moment de l'ecriture.

## 4. Qui decide quoi

| Question | Decideur | Artefact attendu |
|---|---|---|
| Garde generique ou test par paquet (F1, F2 ou F3), place dans l'arbre de tests | `theo-archi` | ADR si une regle d'architecture est creee, sinon avis |
| Garde dans ce palier ou plus tard, avant ou apres les lots de contenu, et pour quelles matieres | `camille-po` | `DEC-XXXX` |
| `LON-CE1-5` s'applique-t-elle aux mathematiques CE1 (exclue a s48) et la borne de six mots tient-elle pour QLM et EMC | `nadia-prof` | avis dans la fiche de conformite ou amendement de l'arbitrage |
| Affectation du lot une fois decide | `sofia-sm` | planning du palier 3 |

Les regles `LON-CE1-3` a `-5` et `TRV-CE1-1` sont des conditions de `nadia-prof`. Un garde generique ne les modifie pas, il les execute. Le choix du perimetre par matiere les modifierait. C'est la seule partie pedagogique de la question.

## Decisions prises

Aucune `ADR` ni `DEC` creee ou amendee. L'ecart E3 reste ouvert : il n'est clos que par le fichier de decision qui le tranche, et ce fichier n'existe pas.
