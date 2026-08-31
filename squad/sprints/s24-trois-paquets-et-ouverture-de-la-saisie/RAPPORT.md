# [Sprint s24] Rapport de sprint - trois paquets et ouverture de la saisie

- **Debut** : 2026-08-30 22:32 - **Fin** : 2026-08-31 14:10 - **Duree effective** : 15 h 38 min
- **Redige par** : sofia-sm (casquette Scrum Master), le 2026-08-31
- **Ceremonies** : `planning.md` (avec son amendement date de cloture), `review.md` (sections 1 a 8
  rendues a 11:45, section 11 a la cloture ; sections 9 et 10 de `nadia-prof`), `retro.md` (avec son
  amendement date). Le daily n'est pas journalise ; ses obstacles sont dans `review.md`.
- **Ce rapport decrit l'etat final.** Le sprint a connu deux etats successifs - porte technique rouge
  et saisie non jouable a 11:45, puis les deux corriges - et **les deux figurent ici** : effacer le
  premier ferait de ce rapport une description fausse d'une journee qui a coince avant d'aboutir.

---

## 1. L'increment livre, en une phrase

> **s24 livre quatre jeux neufs qu'un enfant peut ouvrir depuis le catalogue - un texte narratif tres
> court et les marques du pluriel au CP, la notion de fraction simple au CM1, et les complements a 10
> en saisie au clavier - et avec le dernier, la premiere mecanique de ce produit ou un enfant qui ne
> sait pas peut echouer.**

---

## 2. L'etat des deux Done

### 2.1 Done technique - ATTEINT

```
mvn -B clean verify
```

a la racine du reacteur, **reacteur complet, aucun `-pl`, donc aucun `-am` a citer**. Mesure par moi
sur l'arbre de cloture, jamais relayee depuis l'annonce d'un agent.

> **BUILD SUCCESS. Six modules sur six en SUCCESS.**

**Compte de tests, avec sa methode et son perimetre** : somme des attributs `tests`, `failures`,
`errors` et `skipped` de l'element `<testsuite>` de chaque `TEST-*.xml` des rapports surefire et
failsafe produits par ce build apres un `clean`. **133 rapports, 926 tests, 0 echec, 0 erreur,
0 ignore. Perimetre : le depot.** Repartition : `adapter-content` 355, `adapter-web` 349, `bootstrap`
125 surefire et 6 failsafe, `domain` 78, `application` 13.

**31 regles ArchUnit**, une de plus qu'a l'ouverture. **JDK 21.0.10**, version rapportee par la
machine virtuelle des tests elle-meme et verifiee a chaque build depuis `ADR-0071` - egalite avec
l'exigence du build, jamais plancher.

**L'enchainement des chiffres de la journee, chacun exact sur son arbre, aucun relaye** : 811 a
l'ouverture ; 825 annonces par deux agents sur **deux arbres differents** (chacun mesurant la base
plus son propre lot) ; 787 sur quatre modules a la porte rouge de 11:45, un chiffre qui ne se compare
a aucun autre et que je ne soustrais de rien ; 923 apres le correctif du fil-piege ; **926 a la
cloture** - les trois de plus sont les trois clauses de `CPL-3`.

**La porte a d'abord ete rouge, et le dire fait partie du rapport.** A 11:45, `mvn -B clean verify`
rendait BUILD FAILURE : le garde-fou d'`ADR-0072` transtypait `(Question)` sans classer les formes
d'item et cassait sur le paquet de saisie. **Quinzieme garde-fou de contenu, ecrit apres que le tri du
lot 5a eut ferme sa liste a quatorze. Vert sur la branche de `theo-archi`, vert sur celle de
`sofia-sm`, rouge une fois les deux ensemble.** Corrige par son auteur sur le patron du lot 5a - le
garde-fou **declare son domaine** au lieu de transtyper en aveugle, avec son motif, son compte
d'ecartes et sa morsure. **Aucune assertion affaiblie, aucun second classement invente.**

Les autres criteres de sortie sont tenus : `R-A` a `R-D` dans les commits de leur code, l'extension
des pressables a `input` avec sa morsure dans le commit du fragment, les garde-fous de contenu etendus
ou bornes avec zero exemption muette, zero chemin de poste ou nom de compte sur l'index.

### 2.2 Done pedagogique - QUATRE VALIDE, prononces par `nadia-prof` et par elle seule

**Je ne le prononce pas et je ne l'anticipe pas. Je le rapporte.** Il vit sous sa plume dans
`review.md` sections 9 et 10, et dans quatre fiches de conformite.

| Increment | Premier passage | Verdict final |
|---|---|---|
| `bandit-manchot-pluriel-cp` | VALIDE | **VALIDE** - non rejoue, rien n'a bouge dessus |
| `bandit-manchot-texte-narratif-cp` | VALIDE SOUS RESERVE | **VALIDE**, reserve levee |
| `bandit-manchot-fractions-cm1` | VALIDE SOUS RESERVE | **VALIDE**, reserve levee |
| `saisie-complements-10-cp` | **REFUSE** | **VALIDE**, refus leve |
| `S24-SAI-1` - la mecanique de saisie | audit rendu, degrade sur un point | **confirme**, joue depuis le catalogue livre |

**Aucun verdict anterieur n'est efface** : les quatre fiches portent les deux passages, dates. Elle a
rejoue **sans aucune sonde**, depuis la liste du CP, et distingue explicitement ce second passage du
premier qu'elle avait declare degrade faute d'entree de catalogue.

**La decharge des lots 3 et 4 est prononcee** par elle, sur la troisieme forme de preuve de structure
de `theo-archi` - garde invariablement vraie plus corps identique a l'octet - qu'elle a reverifiee
elle-meme.

### 2.3 L'arbre des verdicts et l'arbre livre sont le meme - verifie, pas suppose

**L'arbre de reference donne a `nadia-prof` ne portait qu'un des trois correctifs**, les deux autres
vivant sur `s24-lea` et `s24-marc-2`, et aucune branche poussee ne portait les trois. **Elle a
construit l'arbre qu'elle a joue et l'a identifie par quatre empreintes**, en ecrivant : *"si
l'integration de cloture ne rend pas ces quatre empreintes, elle ne porte pas ce que j'ai valide."*

**Methode, reproductible par un tiers** : `git hash-object` sur l'arbre de cloture, compare a sa
table. Empreintes de **blob git**, methode etablie en recalculant les deux formes sur sa branche
avant de comparer plutot qu'en la supposant.

**Quatre sur quatre.** `cp-complements-10-saisie.yaml`, `cp-texte-narratif.yaml`, `cm1-fractions.yaml`
et `catalogue.yaml` rendent exactement les empreintes de sa fiche.

**Le fait de methode qu'elle verse, et il vaut au-dela de ce cas** : son compte de tests coincide avec
celui d'un arbre a **un seul** correctif, les deux autres ne touchant que du YAML. *"Coincidence
expliquee, pas suspecte - mais elle montre qu'un compte de tests ne distingue pas deux arbres."*
**Ce sont les empreintes qui identifient l'arbre ici, pas le 926.**

---

## 3. Les decisions du sprint - liste complete

**Dix decisions creees ou modifiees, plus deux amendements de process. Aucune section vide.**

### `ADR` - `theo-archi`

| # | Titre | Statut |
|---|---|---|
| `ADR-0069` | Le mode de forme d'un item de saisie | Accepte |
| `ADR-0070` | Aucune cle de schema d'un paquet servi avant que son champ existe | Accepte |
| `ADR-0071` | La version de JDK citee par une mesure est celle que la machine virtuelle des tests rapporte | Accepte |
| `ADR-0072` | Aucune phrase du texte support ne suffit a gagner la majorite d'un paquet | Accepte, **amende a la cloture** - le garde-fou declare son domaine au lieu de transtyper en aveugle |
| `ADR-0058` | (anterieur) | **Modifie** - amendement 4 |

### `DEC` - `camille-po`

| # | Titre | Statut |
|---|---|---|
| `DEC-0059` | Perimetre de s24 | **Proposee**, corrigee et amendee (le point de bascule, ecrit avant le declencheur) |
| `DEC-0060` | La porte pedagogique porte sur les elements neufs ou modifies | **Acceptee**, amendee des six reponses de l'utilisatrice |
| `DEC-0061` | Le score parfait par elimination est un defaut a corriger, non un score decoratif | **Acceptee** - non engagee a s24, premier report compte |
| `DEC-0062` | Convention d'attribution d'un enonce recu hors des neuf agents | **Acceptee** - le canal se nomme MASTER, et un enonce recu par lui **ne vaut jamais motif** |
| `DEC-0063` | Sous `Q1`, les 27 jeux deviennent modifies ; regime de matrice tranche ; file de reaudit ouverte non engagee | **Acceptee** |

### Arbitrages de `nadia-prof` - aucun n'est un Done

Clause opposable de la porte pedagogique (neuf clauses `C1` a `C9`, trois regimes d'audit `R-A` /
`R-B` / `R-C`, quatre exigences refusables) ; `ARBITRAGE-FRA` (le seuil de rang tient, `FRA-5`
amendee, famille (e)) ; `ARBITRAGE-D5` (variante A avec condition, forme (b) retiree) ; `SUITES-s24`
(lot 1 discharge, `CPL-9` posee) ; `CPL-3` reaffirmee au refus, puis le second passage.

### Amendements de process - `squad/README.md`

- **Section 7** : le perimetre de la porte pedagogique y etait decrit **sans borne**. Il l'est
  desormais, par transcription de `DEC-0060` et de la clause opposable de `nadia-prof`.
- **Section 2 bis, neuve** : **la convention `MASTER`** - les trois formes d'attribution, les deux
  bornes de la forme 3, la regle d'ecriture du nom, et l'exemple reel de ce qu'elle empeche. Due par
  `DEC-0062` depuis son acceptation. **C'etait un garde-fou annonce mais absent** - la dette 7 de
  `CLAUDE.md` reproduite dans le sprint qui l'a creee.
- **Section 7 encore** : la description d'une *"integration H2 une fois `adapter-persistence` cree"*,
  supprimee du produit par `DEC-0028` le 2026-08-14, est corrigee par `relecteur-pedagogique`.

---

## 4. L'etat de la matrice de couverture

**Methode** : comptage de la colonne Statut des quatre tableaux de matiere de
`MATRICE-couverture.md` sur l'arbre de cloture. **Perimetre : les quatre tableaux de matiere, pas les
tableaux de synthese historiques du meme fichier.** `relecteur-pedagogique` a recompte independamment
et rend le meme resultat.

**Etat du fichier a la cloture : 24 `couvert-valide`, 4 `en cours`, 15 `non couvert`, 4 `non couvrable
en l'etat`, 47 lignes, 43 atteignables. 24/43, soit 55,8 %.**

**Ce que les quatre `VALIDE` rendent du, recompte et non infere** : trois lignes portees `en cours` a
l'ouverture ont chacune un verdict `VALIDE`. **Le ratio devient 27/43, soit 62,8 %** - exactement
l'objectif que le planning avait ecrit comme objectif et jamais comme Done. **Le paquet de saisie
n'ajoute aucune ligne** : il porte sur une ligne deja verte, ce que `camille-po` avait etabli sur 18
lignes et `nadia-prof` confirme sur 23.

> **Cette mise a jour n'est pas ecrite par moi, et c'est la frontiere, pas une prudence.**
> `squad/programme/MATRICE-couverture.md` a un proprietaire nomme par `squad/README.md`, et ce n'est
> pas moi : *"`camille-po` la met a jour [...] a la review, [...] seulement si `nadia-prof` a rendu un
> verdict `VALIDE`."* Le geste est mecanique et les verdicts existent sur le disque ; il reste le
> sien. **Je fournis le comptage avec sa methode ; elle porte l'ecriture.**

**Deux dettes sur ce fichier, meme proprietaire, groupees pour qu'elles ne se cherchent pas** : les
trois lignes a passer `couvert-valide` **avec la mention exacte de `FRA-9` que `nadia-prof` exige sur
la ligne des fractions**, et la mention datee de `DEC-0063` section 3 sur les lignes touchees par les
lots 1 et 3. **C'est le premier obstacle echu de s25.**

**Catalogue : 27 entrees a l'ouverture, 31 a la cloture. 31 paquets servis, aucun paquet orphelin** -
verifie par `nadia-prof`, qui en avait trouve un au premier passage.

**Le chiffre de `DEC-0063` avec la reserve d'unite que `camille-po` exige qu'on lui attache** : 31
objets se presentent a la porte contre une borne de 4 a 5 **paquets**. **Les deux nombres ne sont pas
dans la meme unite**, et la question qui decide si le chiffre reel est 31 ou beaucoup moins n'est pas
repondue. Cite sans cette reserve, *"31 contre 4 a 5"* serait un chiffre sans son perimetre.

**Etat de la file de reaudit** : lots 1, 3 et 4 **discharges** par `nadia-prof`. Ce qui reste de la
file porte sur les sprints a venir, pas sur s24.

---

## 5. Ce qui est livre, ce qui ne l'est pas

**Livre** :

- **quatre paquets de contenu, 76 items**, relus mot par mot par `relecteur-pedagogique` - controle
  systematique et non par echantillon - **zero faute d'orthographe, d'accentuation ou de ponctuation**
  dans le texte lu par l'enfant ;
- **la mecanique de saisie, jouable** : `Saisie`, `SaisieSession`, `SaisieVerdict`,
  `NormalisationDeSaisie`, sa fabrique de contenu, le retypage d'`adapter-web`, son ecran, son entree
  de catalogue - titre a cinq mots, consigne a huit contre une borne de huit ;
- le tri ecrit des garde-fous de contenu face a un item de saisie et son execution ;
- **quatre `ADR`, cinq `DEC`**, la clause opposable de la porte pedagogique, deux amendements de
  process.

**Non livre** :

- **le rapport qualite de `yanis-qa`.** Sa branche n'est pas poussee a la cloture - `git fetch` puis
  `git branch -r`, dix-sept references distantes, **aucune `s24-yanis`** - alors que le rapport de
  tokens le compte a **218 tours et 77 954 tokens d'output**. **Il a travaille et n'a rien rendu au
  depot** : au sens du principe de gouvernance, ce rapport qualite **n'existe pas**. **Second sprint
  consecutif sans rapport qualite**, et je le dis plutot que de fermer en silence.

**Ce que le lot de saisie apporte, et le chiffre est de `nadia-prof`** - c'est la phrase qui a
remplace le chiffre faux de la section 6 :

> *"Un enfant qui ne sait rien obtient aujourd'hui 7 manches sur 7. En saisie, avec trois essais sur
> onze reponses possibles, il en obtient au plus trois sur onze par manche. La saisie est la premiere
> mecanique de ce produit ou un enfant qui ne sait pas peut echouer."*

Et son fait de cloture : **le catalogue contient desormais un jeu sur trente et un ou un enfant qui ne
sait pas peut echouer.** *"C'est ce qu'aucun compteur de couverture ne verra."*

---

## 6. Les faits recus de MASTER

Sous la forme que `DEC-0062` etablit. **Aucun n'est attribue a un agent et aucun ne vaut motif.**

| Enonce, cite | Recu | Ce que la mesure a rendu |
|---|---|---|
| *"l'ouverture de la saisie ferait passer la couverture de 28 a 33 lignes sur 43"* | **de MASTER, le 2026-08-30, sans source dans le depot** | **Les deux bornes etaient fausses.** Depart 24, pas 28 ; gain nul - `camille-po` sur 18 lignes et `nadia-prof` sur les 23 non vertes trouvent independamment **zero ligne ouverte**. L'arbitrage rendu dessus tient par les motifs de `DEC-0059` |
| *"toutes les mesures locales tournaient sur un JDK 25"* | **de MASTER, le 2026-08-30, sans source dans le depot** | **Demontee par `theo-archi`** : deux fichiers de toolchains sur ce poste, **Maven lit le bon, les tests tournent sur un JDK 21**. Le chiffre du depot etait juste, la methode ne l'etait pas |
| *"un fichier de toolchains du poste declare une toolchain `jdk` de version `21` en designant une racine de JDK 25"* | **de MASTER, le 2026-08-30, sans source dans le depot** | **Quatrieme fait, identifie a la cloture.** Il etait attribue a `nadia-prof` par le planning. Son constat existe - `FICHE-bandit-manchot-additions-cp.md`, commit `8877015` du 2026-08-05 - mais il dit *"le poste d'audit ne dispose que d'un JDK 25"*, ce qui est **un autre enonce**. `ADR-0071` ne se fonde sur aucun des deux : `theo-archi` a reproduit le mecanisme |
| *"le lot 3 n'existe pas, donc le chemin C est ecarte pour s24"* | **de MASTER, le 2026-08-31, sans source dans le depot** | **Faux, et mesurable.** Le lot 3 etait pousse **quatorze minutes** avant la phrase qui le nie. **Deux enonces transmis sans etre mesures ont circule comme vrais** ; le second aurait referme 27 jeux sur un lot que personne n'avait regarde. Arrete parce que sa destinataire est allee verifier |
| *"`yanis-qa` et `relecteur-pedagogique` ont ete lances apres ta review au lieu d'avant"* | **de MASTER, le 2026-08-31, sans source dans le depot** | Verse tel quel. La retro attribuait ce manque a ma seule charge ; **le moment de l'invocation d'un agent ne m'appartient pas.** Ce qui reste de moi : ma review a ete rendue sans les avoir demandes |

**Une limite de la convention, decouverte en l'appliquant** : le meme enonce est cite avec deux verbes
differents dans le depot - *"fera"* chez `camille-po`, *"ferait"* chez moi. La forme 3 exige le mot
pour mot d'un canal qui n'ecrit nulle part. **Je refuse de choisir** : choisir fabriquerait la
citation que la convention protege. **L'incertitude est nommee et non levee, aucun libelle n'est
reecrit**, et le cas part a `camille-po` comme materiau d'amendement (obstacle 20).

**La lecon que la retro porte** : le canal de transmission n'est pas ce qui rend un enonce vrai. Un
fait relaye par le Scrum Master, par l'architecte ou par MASTER a exactement la meme valeur - celle de
la mesure qui l'accompagne. **La borne `Q6` a ete acceptee le matin du 2026-08-31 et a servi le jour
meme.**

---

## 7. Obstacles - resolus et ouverts

### 7.1 Resolus, avec le fichier qui les clot sur le disque

| Obstacle | Ce qui l'a clos |
|---|---|
| Le garde-fou d'`ADR-0072` cassait le build apres fusion | `0fe674a` - domaine declare par rapport a `ClassementDesItems`, avec motif, compte d'ecartes et morsure |
| La saisie n'etait atteignable par aucun enfant | `5a8a428` - entree de catalogue sous `CPL-9a` a `CPL-9e`. `nadia-prof` a rejoue sans sonde |
| `CPL-3` etait une condition ecrite que rien n'executait | `5a8a428` - trois clauses qui **calculent** les formes attendues depuis l'enonce au lieu de les lire dans le paquet, morsure mesuree sur le paquet refuse |
| Le lot 3 n'etait discharge par personne | Decharge de `nadia-prof`, sur la troisieme forme reverifiee par elle |
| La bascule livrerait une saisie non jouable | `DEC-0059`, amendement ecrit **avant** le declencheur. La bascule ne s'est pas produite |
| La forme (d) de `FRA-2` contre le seuil minimal de rang | `ARBITRAGE-FRA` - `FRA-5` amendee, famille (e), branche passee verte |
| `D5`, `D2`, `D6`, `D7` | Arbitrages et lots, chacun avec son fichier |
| Le perimetre sans borne de la porte pedagogique | Amendement `squad/README.md` section 7 |
| La convention `MASTER` annoncee mais absente du depot | Amendement `squad/README.md` section 2 bis |
| `squad/README.md` decrivait une integration H2 supprimee par `DEC-0028` | `5c0964f`, par `relecteur-pedagogique` |
| La fausse attribution *"il est de moi"* du planning | Amendement date du `planning.md`, sous la forme 3 |

### 7.2 Ouverts a la cloture - vingt-quatre, chacun avec son proprietaire

**Le tableau complet est dans `review.md`, sections 7 et 11.7.** Les six qui commandent la suite :

| # | Obstacle | Proprietaire | Ce qui est attendu |
|---|---|---|---|
| 5 | **La matrice porte deux dettes echues** : trois lignes a passer `couvert-valide` avec la mention exacte de `FRA-9`, et la mention datee de `DEC-0063` | `camille-po` | Ecriture dans `MATRICE-couverture.md`. **Premier obstacle echu de s25** |
| 22 | **`TYP-1` ne voit que la moitie du texte que l'enfant lit** - 11 occurrences, dont 3 neuves a s24, entrees sous une porte verte | `theo-archi` ; `nadia-prof` pour le choix de caractere | Extension du perimetre du cliquet d'`ADR-0067` |
| 4 | **Le score parfait par elimination sur 94,6 % des items enumeres.** `DEC-0061` a tranche : c'est un defaut a corriger | `theo-archi` | `ADR` sur `M5`. **Premier report compte** |
| 11 | **Un reaudit apres retypage coute-t-il un paquet contre la borne de 4 a 5 ?** | `nadia-prof` | Avis ecrit. **Si oui, la regle et la borne sont arithmetiquement incompatibles et le fait retourne a l'utilisatrice** |
| 8 et 9 | **La dette d'outillage de `S21-6`** (quatrieme occurrence) et les **51 occurrences historiques** de la formule de JDK disqualifiee | **moi** pour la capacite ; `camille-po` pour le rang | **Portees au planning de s25 comme items de plein droit**, patron *amender, jamais reecrire* |
| 20 | **La forme 3 de `DEC-0062` exige le mot pour mot d'un canal qui n'ecrit nulle part** | `camille-po` | Amendement : la citation est figee par son premier destinataire a la reception, ou la divergence est assumee |

**Les dix-huit autres** : le brief `DEC-0062` absent de la fiche du relecteur (21, **configuration
d'agent - je ne la modifie pas**) ; la formule de JDK dans le document d'`ines-design` (6, son
auteure, non invoquee ce sprint) ; le maximum de strategie a 50,0 % du paquet narratif, dont
`nadia-prof` refuse la reparation evidente (23) ; trois noms pour un concept (24) ; un item de
`cm1-fractions` a juger (25) ; `CPL-3` qui ne gardera pas le prochain bareme et `SAI-6`/`SAI-7` dans
la seule main de `nadia-prof` (26) ; la file de reaudit et son sequencement (10, moi) ; `PLU-1`
mecanisable (12, `theo-archi`) ; `nombreDeManches: 10` jamais decide (13, `camille-po`) ; `D4` et le
cout d'un accent sur tablette (14, 18, non echus) ; les deux points de forme de `D5` et les deux
marques de focus (15, 16, `ines-design` et `nadia-prof`) ; les proprietes hors de tout garde-fou (17) ;
`DEC-0058`, `TYP-1` moyen et l'obstacle 25 residuel de s21 (19, moi).

---

## 8. Consommation de tokens

`squad/sprints/s24-trois-paquets-et-ouverture-de-la-saisie/RAPPORT-tokens.md`, produit par
`python outils/rapport-tokens.py --sprint s24`. **Aucun chiffre n'est recopie a la main ni invente.**
Le detail par agent, par modele et par compteur y figure ; il n'est pas duplique ici pour qu'il n'y
pourrisse pas a moitie.

**Ce que le rapport dit d'important au-dela des chiffres, et il fallait borner la fenetre a la
cloture reelle pour le voir** : `ines-design` y apparait comme *"aucune invocation mesuree"* alors
qu'elle a livre - son lot est dans la fusion d'ouverture et sa fenetre tombe avant le debut du sprint.
**`yanis-qa`, lui, y apparait avec 218 tours et zero fichier dans le depot.** Un premier tirage,
borne a l'horodatage de la review de 11:45, ne montrait ni lui ni `relecteur-pedagogique` : la
troncature de fenetre aurait fait passer pour une absence d'invocation ce qui est **un travail sans
trace**. Les deux faits appellent des reponses opposees, et un rapport tronque les confondait.

**Une reserve de methode qui vaut pour les rapports suivants** : lance depuis le worktree de review,
le script rend *"aucune invocation mesuree"* pour les neuf agents - il deduit le dossier des
transcriptions du chemin du depot, et celui d'un worktree est vide. **La commande reussit, le rapport
s'ecrit, et il decrit un autre arbre que celui qu'on mesure.** Les chiffres commites sont obtenus en
pointant explicitement le dossier du depot principal. **Route a `theo-archi` avec les deux autres
dettes d'outillage** - meme famille : un outil qui repond a cote sans le dire.
