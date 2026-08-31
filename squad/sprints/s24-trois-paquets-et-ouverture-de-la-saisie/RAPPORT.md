# [Sprint s24] Rapport de sprint - trois paquets et ouverture de la saisie

- **Debut** : 2026-08-30 22:32 - **Fin** : 2026-08-31 11:45 - **Duree effective** : 13 h 13 min
- **Redige par** : sofia-sm (casquette Scrum Master), le 2026-08-31
- **Ceremonies** : `planning.md`, `review.md`, `retro.md`. Le daily n'est pas journalise ; ses
  obstacles sont dans le tableau de `review.md` et repris ici.

---

## 1. L'increment livre, en une phrase

> **s24 livre trois jeux de contenu neufs qu'un enfant peut ouvrir - un texte narratif tres court et
> les marques du pluriel au CP, la notion de fraction simple au CM1 - et une mecanique de saisie
> complete, du domaine jusqu'a l'ecran, qu'aucun enfant ne peut encore ouvrir parce que l'entree de
> catalogue qui la joindrait a son paquet n'existe pas.**

---

## 2. L'etat des deux Done

### 2.1 Done technique - NON ATTEINT

```
mvn -B clean verify
```

a la racine du reacteur, **reacteur complet, aucun `-pl`, donc aucun `-am` a citer**. Mesure par moi
sur l'arbre fusionne de cloture, jamais relayee depuis l'annonce d'un agent.

> **BUILD FAILURE.**

| Module | Verdict |
|---|---|
| `recreo`, `recreo-domain`, `recreo-application`, `recreo-adapter-web` | SUCCESS |
| `recreo-adapter-content` | **FAILURE** - 2 erreurs |
| `recreo-bootstrap` | **SKIPPED - non mesure** |

**Compte de tests, avec sa methode et son perimetre** : somme des attributs `tests`, `failures`,
`errors` et `skipped` de l'element `<testsuite>` de chaque `TEST-*.xml` des rapports surefire et
failsafe produits par ce build apres un `clean`. **101 rapports, 787 tests, 0 echec, 2 erreurs,
0 ignore.** **Perimetre : les quatre modules qui ont tourne, et ce n'est pas le depot** - `bootstrap`
n'a pas ete execute. **Il n'existe pas de total de depot pour s24 et je n'en fabrique pas un par
addition.**

**D'ou vient l'ecart avec les chiffres annonces** : `marc-dev` annonce 825 sur `s24-marc-fra` (811 +
14), `sofia-sm` casquette dev annonce 825 sur `s24-sofia-dev` (811 + 7 + 7). **Le meme nombre sur deux
arbres differents**, chacun mesurant la base plus son propre lot - comme a chaque sprint depuis s01.
Aucun des deux n'est l'arbre fusionne, et le total de celui-ci n'est pas etabli parce que le build
s'arrete avant `bootstrap`.

**La cause, unique a ce stade** : `AucunePhraseDuTexteSupportNeSuffitAGagnerLaMajoriteTest`, le
garde-fou d'`ADR-0072`, transtype `(Question)` sans classer les formes d'item - il casse sur le
`ReponseAEcrire` du paquet de saisie. **C'est le quinzieme garde-fou de contenu**, ecrit pendant le
sprint apres que le tri du lot 5a eut ferme sa liste a quatorze. Deux verts d'agents, mesures chacun
sur son arbre, produisent un rouge apres fusion. **Route a `theo-archi`, obstacle 1.**

**Je n'ai pas assoupli la definition du Done pour tenir la cloture, et je n'ai pas corrige le
garde-fou d'un autre agent a sa place.**

Les quatre autres criteres de sortie sont tenus : `R-A` a `R-D` dans les commits de leur code,
l'extension des pressables a `input` avec sa morsure dans le commit du fragment, quatorze garde-fous
etendus ou bornes avec zero exemption muette, et zero chemin de poste ou nom de compte sur l'index.
**31 regles ArchUnit**, une de plus qu'a l'ouverture.

### 2.2 Done pedagogique - NON PRONONCE

**Il appartient a `nadia-prof` et a elle seule. Elle n'a pas ete appelee. Ce rapport n'en anticipe
aucun et aucune ligne de matrice ne bouge de son fait.**

Ce qui l'attend : trois paquets neufs (`S24-NAR`, `S24-PLU`, `S24-FRA`), le paquet de saisie
(`S24-SAI-2`), et **l'audit d'une mecanique neuve, hors du compte de sa borne**. Elle a ecrit etre
**a** sa borne de 4 a 5 paquets, **pas en dessous**.

---

## 3. Les decisions du sprint - liste complete

**Neuf decisions creees ou modifiees. Aucune section vide.**

### `ADR` - `theo-archi`

| # | Titre | Statut | Cree / modifie |
|---|---|---|---|
| `ADR-0069` | Le mode de forme d'un item de saisie | Accepte | Cree |
| `ADR-0070` | Aucune cle de schema d'un paquet servi avant que son champ existe | Accepte | Cree |
| `ADR-0071` | La version de JDK citee par une mesure est celle que la JVM des tests rapporte | Accepte | Cree |
| `ADR-0072` | Aucune phrase du texte support ne suffit a gagner la majorite d'un paquet | Accepte | Cree - **son garde-fou casse le build apres fusion** |
| `ADR-0058` | (anterieur) | Accepte | **Modifie** - amendement 4 |

### `DEC` - `camille-po`

| # | Titre | Statut | Cree / modifie |
|---|---|---|---|
| `DEC-0059` | Perimetre de s24 - trois paquets et ouverture de la saisie | **Proposee** | Cree, puis **corrige** (le compte de fichiers de `contenu/`) et **amende** (le point de bascule, ecrit avant le declencheur) |
| `DEC-0060` | La porte pedagogique porte sur les elements neufs ou modifies | **Acceptee** | Cree, puis amende des six reponses de l'utilisatrice |
| `DEC-0061` | Le score parfait par elimination est un defaut a corriger, non un score decoratif | **Acceptee** | Cree - non engagee a s24, **premier report compte** |
| `DEC-0062` | Convention d'attribution d'un enonce recu hors des neuf agents | **Acceptee** | Cree - le canal se nomme **MASTER**, et un enonce recu par lui **ne vaut jamais motif** |
| `DEC-0063` | Sous `Q1`, les 27 jeux deviennent modifies a la cloture ; regime de matrice tranche ; file de reaudit ouverte non engagee | **Acceptee** | Cree |

### Arbitrages de `nadia-prof` - aucun n'est un Done

- **Clause opposable de la porte pedagogique** : neuf clauses `C1` a `C9`, trois regimes d'audit
  `R-A` / `R-B` / `R-C`, quatre exigences refusables pour qu'une mesure d'invariance la decharge.
- **`ARBITRAGE-FRA`** : le seuil de rang tient sans bouger d'un point, `FRA-5` est amendee -
  famille (e), `n + d`, sur la forme (d) seule.
- **`ARBITRAGE-D5`** : variante A avec condition, forme (b) retiree.
- **`SUITES-s24`** : lot 1 discharge sous `R-C`, lot 3 non ; exigence 2 amendee **par ajout** ;
  `CPL-9` posee (cinq clauses pour le titre et la consigne de l'entree de catalogue de saisie).

### Amendement de process

- **`squad/README.md`, section 7** : le perimetre de la porte pedagogique y etait decrit sans borne.
  Il l'est desormais, par transcription de `DEC-0060` et de la clause opposable de `nadia-prof`.
  Ecrit dans le geste de cette review, sur routage de `camille-po`.

---

## 4. L'etat de la matrice de couverture

**Methode** : comptage de la colonne Statut des quatre tableaux de matiere de
`MATRICE-couverture.md` sur l'arbre fusionne. **Perimetre : les quatre tableaux de matiere, pas les
tableaux de synthese historiques du meme fichier.**

**24 / 43 atteignables, soit 55,8 %. Inchangee. Aucune ligne n'a bouge.**

47 lignes : 24 `couvert-valide`, 4 `en cours`, 15 `non couvert`, 4 `non couvrable en l'etat`.

Trois lignes sont passees `en cours` a l'ouverture et attendent un verdict qui n'a pas ete demande.
L'objectif du planning - 27/43 (62,8 %) *"si les trois aboutissent sans cycle de refus"* - etait ecrit
comme un objectif et **jamais comme un Done**.

**Catalogue** : 27 entrees a l'ouverture, **30** a la cloture. **`contenu/`** : 31 fichiers. L'ecart
de un est `cp-complements-10-saisie.yaml`, le paquet de saisie **qu'aucune entree ne sert**.

**Le chiffre de `DEC-0063` avec la reserve que `camille-po` exige qu'on lui attache** : a la cloture,
**31 objets se presentent a la porte de `nadia-prof` contre une borne de 4 a 5 paquets** - 27 jeux
devenus modifies par les lots 1 et 3, plus les quatre paquets neufs, plus l'audit d'une mecanique
neuve hors compte. **Les deux nombres ne sont pas dans la meme unite**, et `nadia-prof` n'a pas encore
repondu a la question qui decide si le chiffre reel est 31 ou beaucoup moins (obstacle 11). **Cite
sans cette reserve, "31 contre 4 a 5" serait un chiffre sans son perimetre.**

**Etat de la file de reaudit** : le **lot 1 est discharge** sous `R-C` (preuve de structure acceptee
par `nadia-prof`, son exigence 2 amendee par ajout). **Le lot 3 ne l'est pas** : `theo-archi` a rendu
son verdict, `nadia-prof` ecrit que la decharge reste la sienne, et elle n'a pas ete appelee depuis.

---

## 5. Ce qui est livre, ce qui ne l'est pas

**Livre** :

- trois paquets de contenu, **65 items** au total, chacun avec son test qui recalcule ses invariants
  depuis les declarations du fichier plutot que de les recopier ;
- la mecanique de saisie complete - `Saisie`, `SaisieSession`, `SaisieVerdict`,
  `NormalisationDeSaisie` dans `domain` ; sa fabrique de contenu ; son retypage d'`adapter-web` ; son
  ecran et son fragment ; `R-A` a `R-D` dans les commits de leur code ;
- le tri ecrit des garde-fous de contenu face a un item de saisie - **quatorze decisions motivees,
  zero muette** - et son execution ;
- quatre `ADR`, cinq `DEC`, la clause opposable de la porte pedagogique.

**Non livre** :

- **la saisie jouable.** Le paquet existe, l'ecran existe, l'entree de catalogue qui les joint
  n'existe pas ;
- **la porte technique.** Le build ne passe pas ;
- **le rapport qualite de `yanis-qa` et les corrections du `relecteur-pedagogique`** : aucun des deux
  n'a ete invoque. C'est un manque de conduite de ceremonie et il est de moi.

**Ce que le lot de saisie apporte, et le chiffre est de `nadia-prof`, pas de moi** - elle a demande
que cette phrase figure au rapport :

> *"Un enfant qui ne sait rien obtient aujourd'hui 7 manches sur 7. En saisie, avec trois essais sur
> onze reponses possibles, il en obtient au plus trois sur onze par manche. La saisie est la premiere
> mecanique de ce produit ou un enfant qui ne sait pas peut echouer."*

---

## 6. Les trois faits recus de MASTER

Sous la forme que `DEC-0062` etablit. **Ils ne sont attribues a aucun agent et aucun ne vaut motif.**

| Enonce, cite | Recu | Ce que la mesure a rendu |
|---|---|---|
| *"l'ouverture de la saisie ferait passer la couverture de 28 a 33 lignes sur 43"* | **de MASTER, le 2026-08-30, sans source dans le depot** | **Les deux bornes etaient fausses.** Le depart est **24**, pas 28 (verifie par moi sur l'arbre fusionne). Le gain est **nul** : `camille-po` sur 18 lignes et `nadia-prof` sur les 23 non vertes trouvent independamment **zero ligne ouverte**. L'arbitrage rendu sur ce chiffre tient - il vaut par les motifs de `DEC-0059`, pas par lui |
| *"toutes les mesures locales tournaient sur un JDK 25"* | **de MASTER, le 2026-08-30, sans source dans le depot** | **Demontee par `theo-archi`** : deux fichiers de toolchains sur ce poste, **Maven lit le bon, les tests tournent sur un JDK 21**. Le chiffre du depot etait juste, c'est la methode qui ne l'etait pas - desormais tenue par un garde-fou qui exige l'**egalite** entre la version que la JVM des tests rapporte et celle que le build exige, jamais un plancher |
| *"le lot 3 n'existe pas, donc le chemin C est ecarte pour s24"* | **de MASTER, le 2026-08-31, sans source dans le depot** | **Faux, et mesurable.** Le lot 3 etait pousse **quatorze minutes** avant que la phrase qui le nie ne soit ecrite (`3dac4f5` a 10:02, la phrase a 10:16). **Deux enonces transmis sans etre mesures ont circule comme vrais** : le second serait entre dans la porte de `nadia-prof` et y aurait referme 27 jeux sur un lot que personne n'avait regarde. *"Le chemin C est ecarte"* **n'est pas etabli** |

**La lecon que la retro porte** : le canal de transmission n'est pas ce qui rend un enonce vrai. Un
fait relaye par le Scrum Master, par l'architecte ou par MASTER a exactement la meme valeur - celle de
la mesure qui l'accompagne. La borne `Q6` de `DEC-0062` a ete acceptee le matin du 2026-08-31 et **a
servi le jour meme**.

---

## 7. Obstacles - resolus et ouverts

### 7.1 Resolus, avec le fichier qui les clot sur le disque

| Obstacle | Ce qui l'a clos |
|---|---|
| La bascule livrerait une saisie non jouable, ce que `DEC-0059` 5.3 refuse d'avance | **`DEC-0059`, amendement du 2026-08-31**, ecrit **avant** le declencheur comme demande. La bascule ne s'est pas produite |
| `DEC-0059` annoncait 28 fichiers de `contenu/` pour 27 | **`DEC-0059` corrigee** par `camille-po` |
| La forme (d) de `FRA-2` contre le seuil minimal de rang - branche rouge de `marc-dev` | **`ARBITRAGE-FRA` de `nadia-prof`** : `FRA-5` amendee, famille (e). Deux items reecrits, la branche est passee verte |
| `D5` - champ pre-rempli ou vide apres un refus | **`ARBITRAGE-D5` de `nadia-prof`** : variante A avec condition, forme (b) retiree |
| `D2`, `D6` - mode de clavier, nom de la classe | Tranches dans les lots 1 et 2 ; `ADR-0069` et `ADR-0070` mordent dessus |
| `D7` - le slug du repertoire de sprint | Clos par le planning, `git mv` sans deplacement de contenu, zero occurrence de l'ancien slug |
| La clause opposable de la porte pedagogique, due par `DEC-0060` | **Ecrite par `nadia-prof`**, neuf clauses, sur le disque |
| Le perimetre sans borne de la porte pedagogique dans `squad/README.md` section 7 | **Amendement ecrit dans le geste de cette review** |

### 7.2 Ouverts a la cloture - dix-neuf, chacun avec son proprietaire

**Le tableau complet est dans `review.md`, section 7.** Les cinq qui commandent la suite :

| # | Obstacle | Proprietaire | Ce qui est attendu |
|---|---|---|---|
| 1 | **Le garde-fou d'`ADR-0072` casse le build apres fusion** | `theo-archi` | Correctif sur le patron de `ClassementDesItems` - domaine declare, motif ecrit, assertion de population. **Jamais un affaiblissement d'assertion.** **Bloque le Done technique** |
| 2 | **Le lot 3 n'est discharge par personne** | `nadia-prof` | Avis ecrit : `R-C`, chemin B, ou chemin C |
| 3 | **La saisie n'est atteignable par aucun enfant** - les deux conditions de retenue de l'entree de catalogue sont levees, le texte propose ne tient pas `CPL-9c` | **moi** (affectation du lot), `camille-po` (question de fond) | Lot nomme, **apres l'obstacle 1 et jamais avant** ; et la reponse de `camille-po` a sa propre question : une mecanique livree et inatteignable est-elle acceptable, ou appelle-t-elle un `ADR` ? |
| 10 | **La file de reaudit des 27 jeux** | **moi** pour le sequencement | **Point nomme de l'ordre du jour du planning de s25, avant l'affectation des lots.** Comptee ouverte depuis le 2026-08-31 ; report ecrit si rien a l'ouverture de s26 |
| 11 | **Un reaudit apres retypage coute-t-il un paquet contre la borne de 4 a 5 ?** | `nadia-prof` | Avis ecrit. **Si la reponse est "un paquet", la regle et la borne sont arithmetiquement incompatibles et le fait retourne a l'utilisatrice avec les deux chiffres cote a cote** |

**Les quatorze autres** : le score parfait par elimination (produit tranche par `DEC-0061`, technique
ouvert a `theo-archi`) ; la mention datee de `DEC-0063` due sur la matrice (`camille-po`, echue) ; la
formule de JDK disqualifiee dans le document d'`ines-design` (son auteure) ; l'integration H2 decrite
dans `squad/README.md` section 7 alors que `DEC-0028` l'a supprimee (`relecteur-pedagogique`) ; la
dette d'outillage de `S21-6` et les 51 occurrences historiques, **portees au planning de s25** ; le
garde-fou de `PLU-1` mecanisable (`theo-archi`) ; `nombreDeManches: 10` jamais decide (`camille-po`) ;
`D4` et le cout d'un accent sur tablette (non echus) ; les deux points de forme de `D5` et les deux
marques de focus (`ines-design`, `nadia-prof`) ; les proprietes hors de tout garde-fou dont
`nadia-prof` reste seule garde ; `DEC-0058`, `TYP-1` et l'obstacle 25 residuel de s21.

---

## 8. Consommation de tokens

`squad/sprints/s24-trois-paquets-et-ouverture-de-la-saisie/RAPPORT-tokens.md`, produit par
`python outils/rapport-tokens.py --sprint s24`. **Aucun chiffre n'est recopie a la main ni invente.**

| Agent | Tours | Output | Cache lu |
|---|---:|---:|---:|
| `marc-dev` | 373 | 249 255 | 144 312 735 |
| `sofia-sm` | 381 | 196 514 | 55 870 089 |
| `theo-archi` | 131 | 108 003 | 56 052 130 |
| `lea-dev` | 173 | 111 888 | 20 303 024 |
| `nadia-prof` | 145 | 105 148 | 16 509 587 |
| `camille-po` | 138 | 116 178 | 15 536 749 |
| orchestrateur (fil principal) | 69 | 180 698 | 10 676 649 |
| **Total** | **1410** | **1 067 684** | **319 260 963** |

**Aucune invocation mesuree sur cette fenetre pour `yanis-qa`, `relecteur-pedagogique`,
`ines-design`.** Pour les deux premiers c'est un fait de ceremonie - ils n'ont pas ete appeles, et la
retro le porte. **Pour `ines-design`, ce n'est pas une absence de travail** : son lot est dans la
fusion d'ouverture et sa fenetre de mesure tombe avant le debut du sprint.

**Une reserve de methode sur ce rapport, qui vaut pour les suivants** : lance depuis le worktree de
review, le script rend *"aucune invocation mesuree"* pour les neuf agents - il deduit le dossier des
transcriptions du chemin du depot, et celui d'un worktree est vide. **La commande reussit, le rapport
s'ecrit, et il decrit un autre arbre que celui qu'on mesure.** Les chiffres ci-dessus sont obtenus en
pointant explicitement le dossier du depot principal. **Route a `theo-archi` avec les deux autres
dettes d'outillage** - meme famille : un outil qui repond a cote sans le dire.
