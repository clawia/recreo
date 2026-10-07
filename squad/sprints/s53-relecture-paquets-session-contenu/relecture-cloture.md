# Relecture de cloture s53 - relecteur-pedagogique

- **Auteur** : `relecteur-pedagogique`
- **Date** : 2026-10-07
- **Arbre mesure** : worktree `s53-rel-cloture`, branche issue de `round-two` a `54953faa`. Plage relue :
  `git diff --stat 9d44d510..HEAD` (59 fichiers : 45 YAML de paquet ajoutes, 4 tests Java, `docs/backlog/backlog.md`,
  `DEC-0112` et son index, l'arbitrage de `nadia-prof`, 6 fichiers du dossier du sprint).
- **Aucun Java edite.** Aucune bonne reponse ni aucun nombre d'items modifie. Aucun push, rebase ni reset.

## 1. Defauts corriges (texte seul)

| # | Fichier | Defaut | Correction | Preuve |
|---|---|---|---|---|
| C1 | `squad/conformite/ARBITRAGE-PREALABLE-s53-points-de-relecture-p1-a-p11.md` ligne 65 | Bloc YAML "a appliquer" de P2 : l'indice porte une espace insecable en tete, une espace insecable suivie d'une espace ordinaire apres "Elle" (`" Elle  désigne ..."`). Le bloc ne se colle pas tel quel et diverge du fichier de paquet | Texte remplace par celui de `ce1-pronoms.yaml` : `"Elle désigne un mot féminin : cherche-le dans la phrase d'avant."` (une seule espace ordinaire, espace insecable avant ":") | `od -c` de la ligne avant correction : `302 240 E l l e 302 240 <espace> d`. La valeur du fichier de paquet a ete relue par script et recopiee |
| C2 | `adapters/adapter-content/src/main/resources/contenu/cp-qlm-situer-la-france.yaml` (en-tete, `FER-3`) | La tache "situer un espace par son point cardinal par rapport a la France" est donnee pour les items 5 a 11. L'item 5 est "La France est dans quel continent ?" et l'item 11 "Paris est dans quel pays ?" : ni l'un ni l'autre ne porte un point cardinal. Les items de point cardinal sont les items 6 a 10 | Items 6 a 10 pour le point cardinal ; items 5, 11 et 12 pour l'emboitement (la France dans un continent, Paris dans un pays, la France dans l'Europe). **Ce changement deplace des numeros d'items dans un commentaire d'en-tete ; il ne change ni un item, ni une bonne reponse, ni un compte** (12 items inchanges) | Items relus un par un (enonce, bonne reponse) ; la suite `adapter-content` et `bootstrap` reste verte (section 5) |
| C3 | `DEC-0112-...md`, section 3 Q4 | "un espace ordinaire" : "espace" typographique est feminin, le depot ecrit partout "l'espace", "une espace" | "une espace ordinaire" | `grep` des formes "un espace" dans les fichiers de s53 |
| C4 | `planning.md` ligne 73 | "un espace insecable" (meme defaut) | "une espace insecable" | idem |

## 2. Les six `enonce` avec une espace ordinaire avant ":" renvoyes par l'arbitrage (section P11)

**L'arbitrage nomme six fichiers. La mesure en trouve un seul, et le corriger casse un test Java que je n'ai pas le droit
d'editer. Aucun `enonce` n'est donc modifie.**

Mesure (script Python de lecture seule, valeur de la cle `enonce:` entre guillemets, espace U+0020 suivie de ":", tous les
`contenu/*.yaml`, arbre `54953faa`) :

| Fichier cite par l'arbitrage | `enonce` avec " :" | Ce que "releve par script sur les lignes `enonce`" a probablement compte |
|---|---|---|
| `cm1-grands-nombres.yaml` | **14** (lignes 12, 16, 20, 24, 28, 32, 36, 41, 45, 49, 53, 57, 61, 65) | - |
| `cm1-anglais.yaml` | 0 | les lignes 204, 209, 219 portent " :" dans le **commentaire** qui suit l'`enonce` |
| `cm1-comprehension.yaml` | 0 | idem (commentaires `COMP-3 : ...`) |
| `cm1-fractions.yaml` | 0 | idem (ligne 251, commentaire `S25-FRA-1 : ...`) |
| `cm1-histoire-geographie-nommer-en-geographie.yaml` | 0 | idem (commentaires `HIS-1: ... ; HG-R3 ... :`) |
| `cm1-histoire-geographie-nommer-en-histoire.yaml` | 0 | idem (ligne 94) |

Les 14 `enonce` de `cm1-grands-nombres.yaml` sont de deux formes : `"Écris en chiffres : <nombre en lettres>."` (7) et
`"Lis ce nombre : <nombre en chiffres>."` (7).

**Tentative de correction et retour arriere.** J'ai remplace l'espace ordinaire par U+00A0 sur ces 14 lignes, puis lance
`PaquetCm1GrandsNombresTest`. Resultat : `chacunDesQuatreVerbesEstRepresenteParAuMoinsUnQuartDuPaquet` echoue,
`[chaque item est classe dans exactement un des quatre verbes] expected: 28L but was: 14L`. Cause : le test classe les items
par `item.enonce().startsWith(PREFIXE_ECRIRE)` et `startsWith(PREFIXE_LIRE)`, dont les constantes (lignes 60 et 61) portent
une espace ordinaire (`"Écris en chiffres : "`, `"Lis ce nombre : "`). J'ai annule la modification (`git checkout` du
fichier) : l'arbre livre ne contient aucun changement sur `cm1-grands-nombres.yaml`.

**A trancher, par `theo-archi` ou le developpeur du test** : la correction du texte de l'enfant exige de changer les deux
constantes en U+00A0 dans `PaquetCm1GrandsNombresTest.java`, dans le meme commit que les 14 lignes. C'est du Java, hors de
ma porte. Les deux constantes et les 14 lignes forment un seul geste.

**Corollaire non demande, a connaitre** : 13 fichiers portent " :" avec une espace ordinaire dans un `indice` (par exemple
`cm1-tables-multiplication` 80 fois, `cm1-conjugaison-present` 25, `cm1-accords-groupe-nominal` 24, `cm1-grands-nombres` 12), et au
moins 9 fichiers portent une espace ordinaire avant "?" dans un `enonce` (`cm1-mesures`, `cm1-perimetre`,
`cm1-tables-multiplication`, `cp-comparer`, `cp-complements-10`, `cp-problemes-additifs`, `cp-soustractions`,
`cm1-homophones`, `cm1-accords-groupe-nominal`). Ce sont des paquets d'avant s53, relevant du cliquet de `TYP-1`
(`ADR-0067`), et je n'y touche pas. Mesure : scripts Python de lecture seule sur `contenu/*.yaml`, valeurs entre guillemets
hors commentaires.

## 3. Points a trancher ou a router (aucune correction appliquee)

| # | Fichier et ligne | Constat | A qui |
|---|---|---|---|
| R1 | `DEC-0112`, section 0 mesure 3 et section 3 Q4 ("l'espace insecable n'est pas la cause (0 fichier)") ; `lecture-testabilite.md` lignes 200-203 et 252 ("0 fichier sur 182") | Contredit par `relecture-p11.md` section 2, `avis-faisabilite.md` ligne 62 et `propositions-lea-dev.md` : **4 fichiers** portent U+00A0 avant ":" dans `competence` (`ce1-accord-groupe-nominal`, `ce1-classes-de-mots`, `ce1-inference`, `ce1-texte-narratif`). Je l'ai refait : script Python sur `contenu/*.yaml`, ligne commencant par `competence:` et contenant U+00A0 suivi de ":" rend ces 4 fichiers. Plus largement 161 fichiers sur 182 portent au moins un U+00A0. La cause du faux zero n'est pas etablie (ma propre tentative de `grep -P` a echoue avec "-P supports only unibyte and UTF-8 locales" dans cet environnement ; `lecture-testabilite.md` ligne 201 decrit deja un piege de `grep -P` hors mode UTF-8). Une mesure ecrite dans une `DEC` est a corriger | `camille-po` (amendement de `DEC-0112`), `yanis-qa` (sa lecture) |
| R2 | `DEC-0112`, section 3 Q4 et Q8 : "Relaye de l'utilisatrice, 2026-10-07" | N'est pas l'une des trois formes de `DEC-0062` : la forme 2 exige ses mots cites entre guillemets, et la section 0 de la meme `DEC` dit "Je n'ai pas ses mots". C'est un message d'agent, a ecrire comme tel (forme 1, ou "message de l'agent lanceur, ne fonde rien") ou a remplacer par ses mots. La section 0 le dit correctement ; ce sont les deux occurrences de la section 3 qui ne le disent pas. Meme remarque pour `relecture-p11.md` lignes 7 a 9 : l'auteur signale lui-meme ne pas avoir verifie, la forme est honnete mais n'est pas l'une des trois | `camille-po`, `relecteur-pedagogique` (moi, pour `relecture-p11.md`, que je ne reecris pas ici) |
| R3 | `ARBITRAGE-PREALABLE-s53-...md` section 11, "six fichiers portent une espace ordinaire avant ':' dans un `enonce`" ; `propositions-lea-dev.md` ligne 264 | Voir section 2 : un seul fichier, `cm1-grands-nombres`, 14 `enonce`. Les cinq autres noms viennent de commentaires. Ce chiffre est aussi repris dans la consigne de cette relecture | `nadia-prof` (correction du document), `sofia-sm` (`review.md`) |
| R4 | `cm1-grands-nombres.yaml` + `PaquetCm1GrandsNombresTest.java` lignes 60-61 | Voir section 2 | `theo-archi` ou developpeur du test |
| R5 | `docs/backlog/backlog.md`, story `S53SPE-RELECTURE`, point P11 : "onze paquets plus anciens" | Le chiffre est reste tel quel apres `DEC-0112`, qui l'a juge non explique et l'arbitrage qui l'a laisse. Pas une faute de redaction : un chiffre ecrit non reconcilie. `relecture-p11.md` section 3 donne neuf | `camille-po` (pose une note d'erratum, comme pour "51 paquets") |

## 4. Verifications faites, sans defaut

- **Caracteres** : balayage des lignes ajoutees de tous les `.md` et des commentaires des YAML pour tiret cadratin,
  demi-cadratin, guillemets typographiques, emoji, symboles : 0 occurrence.
- **Chemins de poste** : motif `[A-Za-z]:\\`, `/c/`, `/home/`, `/Users/` sur les memes lignes : 0 occurrence.
- **Espaces avant `? ! ; :`** dans les chaines des 45 YAML ajoutes (hors commentaires) : seules les 4 lignes `competence`
  de P11, acceptees par l'arbitrage (section 11), portent une espace ordinaire. Aucun signe double colle a un mot, hors
  l'anglais de `cm1-anglais-lire-texte.yaml` (`Hello!`), correct. Guillemets francais : espaces insecables presentes.
- **Accents** : liste fermee d'environ 200 formes courantes (ete, ecole, reponse, matiere, oeuvre, coeur, etc.) cherchee sous leur forme sans
  accent dans les chaines des 45 YAML : 0 occurrence. Limite : liste fermee, pas un correcteur orthographique.
- **Comptes d'en-tete, recomptes sur les items** : `ce1-pronoms` (il 5, elle 5, ils 4, elles 3, substituts 3, total 20 ;
  dix par le sens, dix par l'accord, avec la liste d'items) ; `cm1-emc-principes-republique` (11 items ; bonnes reponses par
  principe 2, 2, 2, 3, 2 ; taches items 1-5 et 6-11 ; item 4 sans "la liberte") ; `ce1-masses-contenances` (12 masse, 9
  contenance, 13 comparaisons et conversions, rang 5 / 5 / 3 soit 38 %, 38 %, 23 %) ; `ce1-texte-documentaire` (11 textes
  documentaires, 9 consignes) ; `ce1-accord-groupe-nominal` (21 adjectifs sur 24, -s 6, -x 12, feminin 6) ;
  `ce1-mots-invariables` (24 mots cibles distincts, 24 bonnes reponses distinctes) ; `cp-emc-personnes-differentes` (taches
  A items 1-4 a 14 ou 15 mots, B items 5-11) ; `cp-qlm-modes-de-vie` (4 domaines de 3 items) ; `cp-qlm-vocabulaire-de-position`
  (14 items, FER-1 et FER-3 conformes aux items). Seul defaut : C2.
- **Mots, `ce1-masses-contenances`** : items 12 (19 mots) et 19 (18 mots) sous la borne de 20.
- **References qui resolvent** (script sur les `.md` du diff et les commentaires des 45 YAML) : tous les `DEC-NNNN` et
  `ADR-NNNN` existent ; tous les fichiers cites par nom existent (les noms coupes en fin de ligne dans les en-tetes YAML
  ont ete recolles et verifies a la main : `ARBITRAGE-PREALABLE-s27-...programme.md`, `...s29-le-prix-des-quatre-...md`,
  `...s30-...quinze-reserves.md`, `...s43-mathematiques-ce1-lot-3.md`) ; toutes les classes Java citees existent ; les
  sections `2`, `3.5`, `3.7`, `5.6` de l'arbitrage s48, `4.1`, `4.3` de s29, `7.1`, `7.4` de s27, `2.2`, `2.5` de s30 existent ;
  les deux citations de `nadia-prof` dans l'en-tete de `cp-qlm-vocabulaire-de-position` ("bien meilleur en illustre",
  "difficulte classique du CP") sont dans leurs fichiers. Les 17 hashes cites dans les documents du sprint existent
  (`git cat-file -t`). Les trois renvois non resolus par mon script (`-B.md`, `-C.md`, `cm1-histoire-geographie.yaml`,
  `ADR-0067-...md`, `application/.../GameDefinition.java`) sont des anciens passages du backlog ou des abreviations ; le
  chemin reel de `GameDefinition.java` est `application/src/main/java/com/recreo/application/catalog/GameDefinition.java`.
- **Provenance (`DEC-0062`)** : une seule occurrence de MASTER dans le sprint, correcte (`planning.md` ligne 29, pour dire
  que le message n'en est pas un). Voir R2 pour la formule "Relaye de l'utilisatrice".

## 5. Ce que je n'ai pas verifie

- Le sens pedagogique et la justesse des items (a `nadia-prof`) ; je n'ai lu que ce qu'il faut pour recompter les en-tetes.
- Les 45 YAML hors des 13 fichiers touches par un point : seuls les balayages mecaniques de la section 4 leur ont ete
  appliques, pas une relecture complete.
- `propositions-marc-dev.md` et `propositions-lea-dev.md` : balayages mecaniques (caracteres, chemins, references) ;
  lecture complete non faite, hors les sections sur P11.
- `lecture-testabilite.md` : lue sections 1, 5 et 6 et le passage sur P11. Ses comptes de tests par module (401, 1318, 312,
  17) recoupent ma mesure (section 6) ; le compte de `domain` (120) n'a pas ete releve dans ma sortie. Ses cinq echecs de
  `bootstrap` ne se reproduisent plus : ils ont ete corriges par les commits `[MARC]` et `[LEA]` de s53 (`S53-REL-10`).
- `docs/backlog/backlog.md` : seuls les sept ajouts de s53 (six lignes d'etat `Etat au 2026-10-07 ...` et la section
  "Session hors sprint s53 special") ont ete lus, pas leur contexte.
- Les commandes citees par les autres agents (`grep`, `awk`, `git log`) : relues, non relancees, sauf les mesures de P11.
- Job `chemins`, `prefixes`, `outils` de `.github/workflows/ci.yml` : **non lances**. Seul le contenu du job `build`
  partiel a tourne (ci-dessous).

## 6. Mesure

Commande, lancee a la racine du worktree apres mes edits : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content,bootstrap -am test`
(perimetre : `domain`, `application`, `adapter-web`, `adapter-content`, `bootstrap`, avec `-am`). Code de sortie 0.

```
Tests run: 17, Failures: 0, Errors: 0, Skipped: 0      (recreo-application)
Tests run: 401, Failures: 0, Errors: 0, Skipped: 0     (recreo-adapter-web)
Tests run: 1318, Failures: 0, Errors: 0, Skipped: 0    (recreo-adapter-content)
Tests run: 312, Failures: 0, Errors: 0, Skipped: 0     (recreo-bootstrap)
recreo-domain ... SUCCESS ; BUILD SUCCESS
```

La version de la machine virtuelle des tests n'a pas ete lue (`ADR-0071`) : la mesure est donnee sur la toolchain 21 que
l'enveloppe a resolue.
