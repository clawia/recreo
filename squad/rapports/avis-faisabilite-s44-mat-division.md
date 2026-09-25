# s44 - Avis de faisabilite (`theo-archi`) - `S44-MAT-DIVISION` (`MAT-CE1-18`)

- **Date** : 2026-09-25
- **Base** : `round-two` a `16c96a2d`, verifie sur le worktree avant ecriture. Aucun diff de code ni
  de contenu dans cet avis ; le seul autre fichier touche est `docs/backlog/backlog.md` (section 2).
- **Pourquoi ce fichier est ici** : aucun dossier `squad/sprints/s44-...` n'existe, et l'ouvrir
  n'est pas mon role. `sofia-sm` le reprend ou le cite dans `planning.md` a l'ouverture.
- **Ce que cet avis ne vaut pas** : il ne fixe aucun contenu, aucun critere d'acceptation
  (`camille-po`), aucune affectation (`sofia-sm`), et le Done pedagogique reste a `nadia-prof`. Il
  ne rejuge pas `DIV-CE1-6` : il dit comment la tester.

---

## 1. Verdict

**Faisable en l'etat. Aucun `ADR` neuf, aucune `ArchRule` neuve, aucun garde-fou transverse.**
Complexite basse sur le mecanisme, moyenne sur le test du paquet.

Le paquet est un paquet statique de type `question` joue au bandit manchot, dans un lot de matiere
et de niveau deja servi. **Cout technique, mesure sur le patron** : le commit qui a livre
`ce1-problemes-durees` (`e980f31a`) touche cinq fichiers et aucune classe de production -
`catalogue.yaml`, le paquet YAML, l'enum `SousPartieDeMathematiquesCe1` de
`ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest` (entree neuve `MAT-CE1-18`), le test de
paquet, et la liste de titres de `TexteAccentueExposeParLePortEntrantTest`. `S44-MAT-DIVISION`
touche les memes cinq, pas un de plus.

Les proprietes generiques - indice present, propositions distinctes dans un item, ecran non repete,
rang de la bonne reponse, longueur d'enonce au CE1, ligne de referentiel declaree - sont deja
derivees du repertoire de contenu par les garde-fous existants. Le test de paquet ne les repete pas,
comme `PaquetCe1ProblemesDureesTest` ne les repete pas.

**Pas d'`ArchRule`** : ArchUnit lit du bytecode, et un enonce de paquet YAML n'y est pas une
propriete lisible - meme motif qu'`ADR-0101`. Le mecanisme automatique qui garde cette story est le
test de paquet de la section 4, execute par le job `build`.

## 2. Le prealable : verifie leve, backlog corrige

Verifie sur le depot, pas sur la consigne :

- l'addendum est `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md`, section 11,
  commit `5e6f4969`, ancetre de `round-two` par la fusion `b2eba157` (`git merge-base
  --is-ancestor`) ;
- `DIV-CE1-6` est alloue dans `squad/decisions/REGISTRE-identifiants.md`, section 4.

Deux paragraphes de `docs/backlog/backlog.md` le disaient encore bloquant : l'introduction du
sprint 44 et la puce "Prealable bloquant" de la story. Les deux portent un amendement date qui cite
`DIV-CE1-6`, sa source et sa fusion ; le texte d'origine est conserve. Ils disaient aussi la
frontiere routee a `theo-archi` : je l'ai declinee et `nadia-prof` l'a reprise (section 11.1),
l'amendement le dit.

**Ce que je n'ai pas corrige, parce que ce n'est pas a moi** : les criteres d'acceptation de la
story ne citent que `DIV-CE1-1` a `DIV-CE1-5`, et le critere de Done demande a `nadia-prof` de
confirmer "les cinq conditions". Ajouter `DIV-CE1-6` est une redaction de story, donc `camille-po`
(Q1).

## 3. Marge sur `ADR-0101`

**Une correction de chiffre d'abord.** La marge mesuree a s43 n'est pas "19/20 caracteres sur le mot
le plus long" : c'est **19 mots sur l'enonce le plus long**, contre une barre de 20
(`squad/sprints/s43-trois-lignes-qlm-eps-et-duree-ce1/confirmation-archunit.md`, ligne 2 du
tableau, mesure faite par le garde-fou Java et prouvee par mutation).

**Mesure de cet avis.** Methode : un script Python de poste, non commite, qui reprend la convention
de `AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.mots` et `phrases` (decoupe sur les espaces
ordinaires et U+00A0, un token est un mot s'il porte une lettre ou un chiffre), applique aux champs
`enonce` des paquets servis. Il retrouve le 19 de s43 sur `ce1-problemes-durees`, ce qui le recoupe
avec le garde-fou reel.

| Materiau | Enonces | Maximum | Phrases |
|---|---|---|---|
| `ce1-problemes-durees.yaml` (servi) | 24 | 19 mots (trois enonces) | 2 |
| `ce1-monnaie.yaml` (servi) | 12 | 18 mots | 2 |
| Exemples de `nadia-prof`, section 4 et 11.5 de l'arbitrage | 3 | 9 mots | 1 |
| Trois formulations de ma main, en trois phrases, **non proposees comme contenu** | 3 | 16, 19 et 24 mots | 3 |

**Lecture.** Le patron n'est pas le bon etalon : un instant `9 h 30` y coute trois mots, deux
instants en coutent six ; un probleme de division porte deux nombres d'un mot chacun. La pression
vient d'ailleurs : `DIV-CE1-6` point 3 impose de nommer les objets, et une situation posee en trois
phrases (une quantite, un geste, la question) atteint 16 a 19 mots sans effort et 24 des qu'on
precise le contexte. **La marge est donc etroite, mais le risque n'est pas muet** : un paquet de
CE1 absent de la table de dette est borne a zero par le cliquet exact d'`ADR-0101`, et un enonce de
21 mots fait rougir `AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest` au premier `mvn -B
verify`. Le prix est une reecriture, pas un defaut livre (R1).

## 4. `DIV-CE1-1` a `DIV-CE1-6` sont toutes encodables dans le test du paquet

Sur le patron de `PaquetCe1ProblemesDureesTest` : `tousLesItems()` tire les items par le chargeur
reel et les caste en `Question`, les nombres sont **recalcules depuis l'enonce, jamais recopies**.

**Une convention de test, a connaitre avant d'ecrire le paquet.** Le test lit le dividende `a` et le
diviseur `b` comme les deux entiers ecrits en chiffres dans l'enonce, `a` etant le plus grand
(`a = q x b` avec `q >= 1`). Aucune condition de `nadia-prof` n'impose deux nombres en chiffres ;
c'est ce que le test exige pour recalculer. Un enonce qui ecrit "trois enfants" en lettres, ou qui
porte un troisieme nombre, rougit : direction sure, mais l'auteur doit le savoir (R2).

| Condition | Assertion | Ce qui reste a `nadia-prof` |
|---|---|---|
| `DIV-CE1-1` | `4 <= a <= 60`, `2 <= b <= 10`, `a % b == 0`, bonne reponse `== a / b` | rien |
| `DIV-CE1-2` | chaque question porte exactement une de deux marques fixees dans le test (partage, groupement), sur le modele de `QUESTION_DUREE` ; un item qui n'en porte aucune ou les deux est rouge ; chaque sens `>= ceil(n / 3)` | que la marque dise vraiment le sens de l'item |
| `DIV-CE1-3` | contenue dans `DIV-CE1-6` point 2, qui l'elargit (section 11.1) : une seule assertion, qui nomme les deux identifiants dans son message | rien |
| `DIV-CE1-4` | items a `b == 2` au plus `floor(n / 3)` ; `moiti` absent, sans casse, de l'enonce, des propositions et de l'indice (couvre `moitie`, `moitié`, `moitiés`) | "ne se reduit pas au fait de repertoire" au-dela de la forme |
| `DIV-CE1-5` | chaque proposition est un entier `> 0` ; au moins un distracteur dans `{a x b, a - b, b}` prive de la bonne reponse | la lecture du troisieme cas (Q2) |
| `DIV-CE1-6` point 1 | `item.illustration()` vide pour chaque item ; le cast en `Question` refuse deja un `ZoneADesigner`, dont l'illustration est obligatoire | le perimetre des autres champs visuels (Q3) |
| `DIV-CE1-6` point 2 | aucun `=` ; aucun des signes `+ - x × : ÷` entre deux termes numeriques, dans l'enonce, les propositions et l'indice (detail ci-dessous) | rien |
| `DIV-CE1-6` point 3 | **partiel** : le dividende est suivi d'un mot qui commence par une lettre, sans ponctuation intercalee | qu'un objet soit bien nomme (detail ci-dessous) |

### 4.1 Point 2, prouve sur materiau reel et non sur une fixture

Motif propose : `[\d?][\s ]*[+\-x×:÷][\s ]*[\d?]`, plus toute occurrence de
`=`. Le `?` compte comme terme parce que le contenu servi ecrit l'inconnue ainsi (`21 + ? = 30`).
U+00A0 est ecrit en toutes lettres : `\s` de Java ne le contient pas, et `TYP-1` en pose un avant
chaque `?` final - meme piege que celui nomme par `ESPACES` dans le garde-fou du CP.

Methode : le meme script Python, applique aux champs `enonce`, `bonneReponse`, `distracteurs` et
`indice` des **103 paquets** de `adapters/adapter-content/src/main/resources/contenu/`, lus ligne a
ligne.

- **Il mord ou il doit** : tous les paquets de calcul ecrit sont rouges, par exemple
  `ce1-additions-jusqu-a-100` (32 champs), `ce1-soustractions-jusqu-a-100` (32),
  `ce1-complements-dizaine` (18), `ce1-moities` (21, `Moitié de 2 = ?`), `cm1-division-posee` (11,
  `÷`), `cm1-tables-multiplication` (99, `×`).
- **Il se tait ou il doit** : zero champ sur les cinq paquets de problemes en mots,
  `ce1-problemes-durees`, `ce1-monnaie`, `ce1-longueurs`, `cm1-problemes-durees`,
  `cm1-problemes-etapes` - tirets de `a-t-il`, `x` de `deux`, `:` de ponctuation compris.
- **Artefact de ma methode, pas du motif** : trois paquets d'histoire-geographie sortent, par des
  dates (`2026-09-03`, `1799-1804`) ecrites dans des **commentaires YAML** en fin de ligne. Le test
  Java lit les champs de `Question`, ou ces commentaires n'existent pas.

**Le faux rouge qui compte, trouve en essayant une forme que `nadia-prof` autorise.** L'indice
*"Compte de 3 en 3 : 3, 6, 9, 12."* est rouge : `3 : 3` est une ponctuation francaise, et rien dans
le texte ne la distingue de `12 : 3`. Je ne l'affaiblis pas : le distinguer demanderait de deviner
l'intention, et le rouge est visible. **Consigne pour l'auteur : aucun deux-points directement
apres un nombre** (*"Compte de 3 en 3 et dis : 3, 6, 9, 12."* passe). R3.

### 4.2 Point 3, ce qu'on encode et ce qu'on n'encode pas

Encodable : **le dividende est suivi d'un mot**. Verifie sur les formes que l'addendum nomme :
*"Combien de paquets de 3 peut-on faire avec 12 ?"* et *"Combien de fois 3 dans 12 ?"* sont rouges
(`12` suivi de `?`), *"Avec 12 billes, combien de paquets de 3 ?"* et *"12 billes partagées entre 3
enfants, combien chacun ?"* passent, *"Avec 12, on fait des paquets de 3."* est rouge (virgule).

Non encodable sans lexique : que ce mot nomme des objets. *"12 partagé en 3"* passe l'assertion et
viole le point 3. La regle n'est pas decorative - elle interdit exactement l'exemple exclu de la
section 11.5 - mais elle est partielle, et **`nadia-prof` reste la garde du residu**, comme elle
l'ecrit elle-meme pour ce point.

## 5. Aucun garde-fou transverse n'est du

**Un test de paquet ordinaire suffit, et le developpeur n'a rien a inventer au-dela.** Motifs :

1. Les six conditions sont des proprietes d'un item ou de la liste des items du seul paquet
   `MAT-CE1-18`. Aucune ne compare deux paquets.
2. La frontiere avec `MAT-CE1-06` est disjointe **par construction, item par item** (addendum,
   section 11.4) : `MUL-CE1-2` exige un dessin ou une addition reiteree ecrite, `DIV-CE1-6` interdit
   l'un et l'autre. Chaque cote se verifie dans le test de son propre paquet ; aucun paquet de
   `MAT-CE1-06` n'existe aujourd'hui.
3. `DIV-CE1-4` ne demande pas de lire `ce1-moities` : l'absence de `moiti` et l'interdiction de `=`
   rendent la forme `Moitié de n = ?` inecrivable dans ce paquet.
4. Etendu au depot, le point 2 serait faux : il rougit sur quinze paquets servis dont le signe est
   la competence meme (methode de la section 4.1 : 18 paquets sortent, moins les trois artefacts
   de commentaire YAML).

La limite connue depuis s42 - aucun garde-fou du depot ne compare deux paquets - reste vraie et
n'est pas touchee par cette story. **Ce qui me ferait changer d'avis** : une condition qui interdit
a un item de reprendre un item d'un autre paquet par son contenu, et non par sa forme.

## 6. Risques

| Code | Risque | Direction | Parade |
|---|---|---|---|
| R1 | Enonces en trois phrases au-dela de 20 mots (section 3) | rouge au premier `mvn -B verify` | deux phrases quand la situation le permet |
| R2 | Nombres ecrits en lettres ou troisieme nombre dans l'enonce : le test ne sait plus recalculer | rouge | deux entiers en chiffres par enonce |
| R3 | Deux-points directement apres un nombre dans un indice qui compte en sautant | faux rouge | voir 4.1 |
| R4 | Marques de sens de `DIV-CE1-2` choisies par l'auteur du test : une marque qui ne dit pas le sens passerait | faux vert | `nadia-prof` verifie a l'audit que chaque marque dit le sens |
| R5 | Point 3 partiel (4.2) | faux vert sur le residu | `nadia-prof`, en jouant |

## 7. Questions routees, via `sofia-sm`

| Code | Question | A qui |
|---|---|---|
| Q1 | Ajouter `DIV-CE1-6` aux criteres d'acceptation et au critere de Done de `S44-MAT-DIVISION` | `camille-po` |
| Q2 | `DIV-CE1-5`, troisieme cas : je lis "le quotient de l'autre sens" comme le nombre donne par l'enonce, `b` - repondre le nombre de parts quand on demande la valeur d'une part. Le quotient de la situation lue dans l'autre sens vaut `a / b`, c'est-a-dire la bonne reponse. Laquelle des deux est la condition ? | `nadia-prof` |
| Q3 | Perimetre de `DIV-CE1-6`. Le point 1 nomme le champ `illustration` ; `Question` porte aussi `tableauSupport`, une grille en lignes et colonnes qui peut dessiner le reseau G1 sans etre une illustration. Le point 2 nomme l'enonce, les propositions et l'indice ; `explication`, `texteSupport` et `texteAVoixHaute` n'y sont pas. Un paquet entierement textuel n'en utilise aucun, donc rien ne bloque s44 ; mais nommer un champ n'est pas nommer une situation, et le test n'etendra la clause que si elle est etendue | `nadia-prof` |

## 8. Ce que cet avis ne fait pas

Il n'ecrit ni le paquet ni son test : la section 4 est la specification que le developpeur affecte
encode, dans le meme commit que le paquet. Il ne tranche aucune des trois questions. Il ne touche a
aucun garde-fou existant.
