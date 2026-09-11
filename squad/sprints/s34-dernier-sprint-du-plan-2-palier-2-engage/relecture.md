# Relecture - Sprint s34

- **Auteur** : `relecteur-pedagogique`. **Date** : 2026-09-11.
- **Arbre** : `round-two` a `a7852398`, plus le diff non commite du sprint (planning, avis, arbitrages,
  confirmation ArchUnit, rapport qualite, trois verdicts, deux fiches neuves, six fiches touchees,
  `ADR-0080`, `ADR-0090`, `ADR-index.md`, `REGISTRE-identifiants.md`).
- **Perimetre** : celui donne en tache - `squad/sprints/s34-dernier-sprint-du-plan-2-palier-2-engage/`
  en entier, les fiches de conformite touchees ce sprint, `REGISTRE-identifiants.md`, les deux `ADR`
  et `ADR-index.md`, `squad/README.md`, le contenu servi (`cp-eps-corps-effort.yaml`,
  `cp-frise-de-la-journee.yaml`, et le diff de `cm1-anglais.yaml`, `cm1-histoire-geographie.yaml`,
  `cp-emc-exclusion.yaml`).
- **Ce que je ne fais pas** : je ne prononce aucun Done, je ne touche pas
  `squad/programme/MATRICE-couverture.md`, je ne commite rien.

---

## 1. Corrections appliquees

Six fichiers modifies, tous des corrections directes, sans ambiguite.

### 1.1 Contenu servi - orthographe

- **`cp-eps-corps-effort.yaml`** : `coeur` corrige en `cœur` (ligature) dans les quatre lignes lues
  par l'enfant (items 2, 8, 14, 20 - `enonce`, `enonce`, `indice`, `bonneReponse`). Le depot porte
  deja la ligature ailleurs (`sœur`, `vœux`, `œuvres`, `œil` dans d'autres paquets) : c'etait la
  seule graphie manquante. **Je n'ai pas touche** les trois occurrences de commentaire (lignes 26,
  56, 82) qui citent mot pour mot la frontiere corps-et-effort de `nadia-prof` (`DEC-0083`, section
  9.2) et l'exemple de refus d'`EPS-6` (arbitrages de s32/s33) : ces citations sont figees a leur
  premiere ecriture et je ne les modifie pas, meme pour une graphie que je corrigerais ailleurs.
  Point deja route par les deux instances de `nadia-prof` (verdicts B et C) : traite.
- **`cp-emc-exclusion.yaml`** : `espérer qu'un adulte l'ait remarqué` corrige en `... l'ait
  remarquée` (accord du participe passe avec le complement d'objet direct antepose), sur les deux
  items ou le distracteur est servi (items 8 et 11). `nadia-prof` identifie elle-meme l'antecedent
  du pronom elide comme feminin (*"l' designe la moquerie"*, `FICHE-bandit-manchot-exclusion-cp.md`,
  `EXC-R12`) et route explicitement ce point de grammaire au `relecteur-pedagogique`, distinct du
  fond de la reserve (remplacer la phrase entiere, qui reste a `camille-po`/`marc-dev`, non
  bloquant). Je n'ai pas touche le mode du verbe (subjonctif passe) : ce point est enchevetre avec
  la reecriture complete que `EXC-R12` demande et qui n'est pas de mon ressort.

### 1.2 Documentation technique - exactitude (la doc contre le code)

Trois inexactitudes dans les commentaires d'en-tete de `cp-eps-regles-jeu-collectif.yaml`, toutes
les trois nommees comme fausses par `nadia-prof` elle-meme (`verdicts-nadia-instance-C.md`,
section 4, point 5) et routees au `relecteur-pedagogique` :

- *"aucun mot de la liste des marqueurs de restriction ... n'est employe"* (`ADR-0086`) : faux -
  `seul` est en bonne reponse de l'item 6, `seulement` dans l'indice de l'item 8. Verifie que
  cela ne fait pas mordre la clause 3 d'`ADR-0086` (le mot n'est jamais sur tous les distracteurs
  d'un item en epargnant sa bonne reponse) avant de corriger : le commentaire dit desormais
  precisement quels mots apparaissent, ou, et pourquoi la clause ne mord pas.
- *"le seul personnage qui enfreint encore une regle du paquet est Hugo (item 10)"* : incomplet -
  Hugo enfreint aussi a l'item 11 (*"À 1, 2, 3, soleil, Hugo avance quand le meneur regarde"*).
  Corrige en *"Hugo, aux items 10 et 11"*.
- *"JEU-R11 (items 8 et 10) : les indices sont reecrits pour rester coherents..."* : la reserve
  `JEU-R11` ne porte pas sur les items 8 et 10, elle porte sur l'indice de l'item 6 (*"Porter un
  seul objet permet d'aller plus vite qu'en portant deux à la fois"*, qui presente la regle comme
  un moyen d'aller plus vite - l'inverse de ce qu'elle enseigne) - `nadia-prof` le demontre en
  section 2.4 de son document. Le commentaire attribuait donc a `JEU-R11` un geste qui ne la
  concernait pas, ce qui a fait croire la reserve traitee. Corrige : le point 3 ne cite plus
  `JEU-R11`, et le point 4 dit explicitement que `JEU-R11` reste ouverte, avec sa reference.

### 1.3 `docs/backlog/backlog.md` - contradiction avec une decision deja rendue

`S34-CONS-4` portait, a trois endroits (critere d'acceptation, "ce que la story ne fait pas",
agent pressenti), l'affectation *"`nadia-prof` ecrit elle-meme le texte de chaque consigne"* /
*"`nadia-prof` pour l'ecriture des quatre consignes et l'audit"*. **C'est l'inverse de la decision
rendue et deja corrigee ailleurs** : l'arbitrage prealable de s34 de `nadia-prof` (section 4.2) dit
mot pour mot *"je n'ecris aucune des quatre consignes [...] le texte revient devant moi quand il
est ecrit, item devant lui"*, et `planning.md` (section 15.1, deja committe dans `a7852398`)
corrige exactement cette meme confusion dans sa propre table de la section 4 (*"correction du
2026-09-11 [...] la formulation initiale faisait porter a `nadia-prof` la redaction des quatre
consignes, en contradiction avec son arbitrage"*). **`docs/backlog/backlog.md`, qui gouverne le
travail reel de s35, n'avait pas recu cette meme correction** - c'est le document que `marc-dev`
ou tout autre developpeur lira en premier pour engager la story. Corrige aux trois endroits :
`marc-dev` ecrit les quatre consignes sur le gabarit du catalogue et la tache que `nadia-prof` a
nommee ; `nadia-prof` audite ensuite. Reference ajoutee vers `planning.md`, section 15.1.

C'est une correction de documentation (l'ecart entre ce que dit un fichier de story et ce qu'une
decision deja rendue et citee mot pour mot etablit), pas une decision produit neuve : je ne fais que
propager a `docs/backlog/backlog.md` un choix deja tranche par `nadia-prof` et deja constate par
`sofia-sm` dans son propre fichier.

---

## 2. References verifiees - aucune ne pointe vers rien

Methode : chaque identifiant `ADR-XXXX`, `DEC-XXXX`, nom de test, chemin de fichier et section de
`docs/backlog/backlog.md` cite dans le perimetre a ete recherche et localise sur le disque avant
d'etre tenu pour existant. Verifies et trouves : `ADR-0006`, `ADR-0013`, `ADR-0019`, `ADR-0025`,
`ADR-0050`, `ADR-0051`, `ADR-0063`, `ADR-0067`, `ADR-0071`, `ADR-0080`, `ADR-0081`, `ADR-0085`,
`ADR-0086`, `ADR-0088`, `ADR-0089`, `ADR-0090` ; `DEC-0002`, `DEC-0056`, `DEC-0061`, `DEC-0064`,
`DEC-0074`, `DEC-0078`, `DEC-0083`, `DEC-0085`, `DEC-0086` ; les seize classes de test citees dans
`planning.md`, `avis-faisabilite-theo.md`, `confirmation-archunit.md` et `rapport-qualite.md`
(`UneMancheResolueApresUnEssaiInfructueuxNEstPasUneReussiteTest`, `DeuxCoupsSimultanesTest`,
`ChaqueMecaniqueDuDomaineEstAtteignableDepuisLeProduitTest`,
`AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest`,
`ChaqueZoneDesignableEstUnBoutonDuFormulaireDeCoupTest`,
`AucuneZoneDesignableNeDescendSousLaCibleTactileTest`,
`AucunEnonceDeDesignationNeNommeLeLibelleDeSaBonneZoneTest`, `PaquetCpEmcExclusionTest`,
`PaquetCpEmcRegleSouhaitLoiTest`, `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`,
`PaquetCm1HistoireGeographieTest`, `FrontieresDuJavaScriptTest`,
`AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest`,
`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, `ToutesLesReferencesDeDecisionResolventTest`,
`ConcordanceDesTagsDAgentTest`) ; les fichiers `ARBITRAGE-PREALABLE-s34-...md`,
`RECALCUL-registre-de-dette-ouverture-s34.md`, les trois `.svg` du palier 2 ; les sections de
`docs/backlog/backlog.md` ("Backlog ouvert pour s34 (composition de `DEC-0086`)" et les deux autres
sections "Backlog ouvert pour s34"). **Rien a signaler.**

`REGISTRE-identifiants.md` : 640 lignes de la forme `| identifiant | document |` en section 4, 103
prefixes distincts, aucun doublon - concorde exactement avec la ligne d'etat courant (*"640
identifiants alloues, 103 prefixes distincts"*) et avec la derniere allocation (instance C, 632/102
-> 640/103). Verifie par comptage direct, pas suppose.

---

## 3. Chiffres - un ecart trouve, le reste se recoupe

### 3.1 Ecart trouve : "71 titres de l'accueil" contredit deux mesures independantes a "70"

`verdicts-nadia-instance-A.md`, section 6.3 : *"Les 71 titres de l'accueil, relus un par un [...]
Deux entrees sur soixante et onze nomment une matiere scolaire..."* - chiffre repete en toutes
lettres, donc pas une coquille de frappe. Sur le meme arbre (`a7852398`) :

- `rapport-qualite.md`, section 2 : **"Catalogue : 70 entrees"** (`grep -c "^  - id:"
  catalogue.yaml`, methode citee) ;
- `confirmation-archunit.md`, section 5 : **"70 entrees, 67 `bandit-manchot`, 2 `saisie`, 1
  `designation`"** ;
- verifie moi-meme : `grep -c "^  - id:" adapters/adapter-content/src/main/resources/catalogue.yaml`
  rend **70**.

Trois mesures independantes convergent sur 70 ; le chiffre de l'instance A est seul a 71 et ne
porte aucune methode a cote de lui (pas de commande citee, contrairement a la regle de
`squad/README.md`, section 4, sur tout chiffre de comptage). Cela ne change aucun verdict - le
point qu'elle en tire (deux titres nomment une matiere plutot qu'une action) reste vrai quel que
soit le denominateur exact - mais le chiffre lui-meme est faux tel qu'ecrit. **Je ne le corrige pas
moi-meme** : c'est un chiffre a l'interieur du bloc de validation pedagogique de `nadia-prof`,
qui n'est jamais retouche par un autre agent. **Je le signale a `nadia-prof`, via `sofia-sm`** :
soit la methode qui a produit 71 est ecrite a cote (peut-etre un compte incluant un element hors
catalogue), soit le chiffre est corrige a 70 au prochain geste sur ce document.

### 3.2 Deja resolu dans les documents eux-memes - aucune action requise

- **`lea-dev` (commit `605038a1`) contre `theo-archi` (`confirmation-archunit.md`, section 5) sur
  les huit regles d'`ADR-0080`** : le message de commit de `lea-dev` affirme que les huit mordent
  desormais sur du reel ; `theo-archi` mesure par mutation que deux d'entre elles (garde-fous 1 et
  3) mordent en realite sur un jeu fabrique d'`adapter-web`, structurellement, et non sur
  `cp-frise-de-la-journee`. Un message de commit est un texte fige que je ne reecris pas ; ce qui
  compte est que le document courant et faisant autorite (`ADR-0080`, son amendement, `ADR-index.md`,
  `confirmation-archunit.md`) porte tous la version mesuree et exacte ("six sur huit"), pas celle du
  commit. Verifie : aucun des documents du perimetre n'affirme "les huit mordent" comme un fait
  courant.
- **Le compte du registre d'identifiants (611/100, 623/102, 632/102, 640/103)**, ecrit et reecrit
  par les trois instances en parallele sur le checkout partage : chaque note porte sa propre mesure,
  datee, avec sa methode, et le total final (640/103, section 2 ci-dessus) concorde avec la table.
  Les trois instances et `theo-archi` documentent elles-memes, sans que je doive intervenir, le
  manquement a la regle de poste de `squad/README.md` section 9 (ecrire depuis un worktree dedie) :
  deux des trois s'auto-declarent en faute, la troisieme (l'arbitrage prealable) travaillait seule.
  Rien a corriger : c'est exactement l'usage que cette regle et la clause 5 d'`ADR-0085` attendent
  d'un tel ecart - nomme, mesure, reconcilie a l'arithmetique, jamais tu.
- **"Erreur probable dans le rapport de s33"** (`rapport-qualite.md`, section 2, `bootstrap` a 90 au
  lieu de 84 fichiers de test) : deja signalee avec sa methode par `yanis-qa` lui-meme, qui ne
  recalcule pas plus loin faute de trace de la commande d'origine. Rien a ajouter.

---

## 4. `ADR-0080` et `ADR-0090` - statuts coherents partout

`ADR-0080` reste **`Propose`** (en-tete de l'`ADR`, amendement du 2026-09-11, `ADR-index.md` :
les trois s'accordent mot pour mot - six regles sur huit mordent sur le reel, les garde-fous 1 et
3 mordent sur une fixture structurelle, la clause manquante a `Accepte` est nommee et non ecrite).
`ADR-0090` passe a **`Accepte`** le 2026-09-11 (en-tete de l'`ADR`, `ADR-index.md`,
`confirmation-archunit.md` section 6 : memes trois sources, meme date, meme mesure - rouge sur
`2c3463b3`, vert sur `5bb6f9db`, vert au `verify` complet sur `a7852398`). Recherche croisee dans
tout le perimetre (`grep` sur "ADR-0080" et "Accepte") : **aucun document n'affirme une bascule
d'`ADR-0080` qui n'a pas eu lieu.** `squad/README.md` ne cite ni l'un ni l'autre `ADR` par son
statut ; rien a corriger la.

---

## 5. Conventions de redaction

- **Tirets simples, guillemets droits, aucun emoji ni symbole decoratif, aucun tiret cadratin** :
  verifie par balayage programmatique (recherche de `\u2014`, `\u2018/\u2019`, `\u201c/\u201d`, et
  de la plage Unicode des emoji/symboles) sur l'ensemble du perimetre commite et non commite.
  **Rien trouve.**
- **Aucun chemin de poste de travail, aucun nom de compte** : verifie par recherche des quatre
  formes que `verifier-chemins.sh` refuse (chemin Windows a lettre de lecteur, sa forme Git Bash,
  repertoire personnel Unix ou macOS nomme) et du nom de compte de l'utilisatrice, sur l'ensemble
  du diff, suivi et non suivi. **Rien trouve** - le job `chemins` de la CI, deja relance
  par `sofia-sm`, `theo-archi` et `yanis-qa` chacun de leur cote sur cet arbre, est confirme par une
  quatrieme lecture independante.
- **`squad/README.md` reste integralement en ASCII non accentue** dans son corps, comme l'exige la
  note de perimetre : verifie caractere par caractere, zero caractere non-ASCII sur l'ensemble du
  fichier, y compris l'amendement de section 9 sur la defaillance d'infrastructure (deja committe,
  `0b4017b9`).
- **Convention d'accentuation du reste du corpus squad/** : les fichiers de ceremonie, `ADR`, `DEC`
  et fiches de conformite de ce sprint suivent la meme convention que l'ensemble du depot depuis
  s00 - prose d'agent non accentuee, texte cite comme lu par l'enfant integralement accentue. C'est
  un usage etabli et non une derive de s34 ; je ne le signale pas comme un ecart.
- **Convention d'attribution (`DEC-0062`)** : aucune occurrence de "MASTER" dans le perimetre de ce
  sprint (verifie) ; la citation mot pour mot de l'utilisatrice dans `DEC-0086`, section 12.1, porte
  ses trois elements requis (mention explicite, guillemets, date). Rien a signaler sur ce point.

---

## 6. Clarte pour un developpeur junior

Le sprint produit des documents qui se corrigent les uns les autres (section 0 de `planning.md`
integre l'arbitrage prealable et l'avis de `theo-archi` avant que le sprint ne s'ouvre ; section 15
corrige une affectation fautive de la section 4 du meme fichier ; `verdicts-nadia-instance-A.md`
corrige son propre arbitrage prealable en section 1 ; `arbitrage-emccp07-nadia.md` corrige le motif
de `theo-archi` tout en confirmant sa conclusion). **Dans chaque cas verifie, le fichier dit
lui-meme, a l'endroit de la correction, ce qui est retenu au final** - aucune conclusion n'est
laissee a reconstituer depuis une suite de rectifications. Le seul endroit ou la conclusion n'avait
pas suivi la correction est `docs/backlog/backlog.md` (section 1.3 ci-dessus), corrige dans ce
geste.

---

## 7. Ce que je route, pas ce que je tranche

| Point | Route a | Ce qui est demande |
|---|---|---|
| "71 titres de l'accueil" contredit deux mesures independantes a "70" (section 3.1) | `nadia-prof`, via `sofia-sm` | Methode a citer si 71 est volontaire, ou chiffre corrige a 70 - je ne touche pas son bloc de validation |
| Le mode du verbe dans "esperer qu'un adulte l'ait remarque(e)" (subjonctif passe, juge trop complexe au CP) | `camille-po` / `marc-dev`, deja route par `nadia-prof` sous `EXC-R12` | Reste lie a la reecriture complete du distracteur, non bloquante ; je n'ai corrige que l'accord du participe, point isole que `nadia-prof` m'avait explicitement route |

Points deja routes par les auteurs du sprint eux-memes vers un autre agent competent (choix de sortie
pedagogique, forme technique d'un ecran, plancher de lisibilite, tests de paquet EPS manquants,
etc.) : je ne les reprends pas ici, ils sont deja portes dans `planning.md` section 10,
`arbitrage-emccp07-nadia.md` section 6, `verdicts-nadia-instance-A.md` section 11,
`verdicts-nadia-instance-B.md` section 7 et `verdicts-nadia-instance-C.md` section 8, avec leur
destinataire nomme.

---

## 8. Ce que je ne fais pas

- Je ne prononce aucun Done, technique ou pedagogique.
- Je ne touche pas `squad/programme/MATRICE-couverture.md`.
- Je ne tranche aucun jugement pedagogique, aucune decision produit, aucune regle d'architecture :
  les deux points de la section 7 sont routes, pas tranches.
- Je ne commite rien et ne pousse rien.
