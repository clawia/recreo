# Fiche de conformite - bandit-manchot-regle-loi-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-emc-regle-loi.yaml`
  (11 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-regle-loi-cm1`, titre servi *"Regle ou loi"*, consigne
  servie *"Lis bien, puis dis si c'est une regle ou une loi, ou ce que fait cette loi."*,
  `nombreDeManches: 10`.
- **Ligne declaree** : `EMC-CM1-08`.
- **Statut global** : **VALIDE** (second audit, 2026-09-23, apres correction de `marc-dev`).
  Historique complet ci-dessous : le premier audit (meme jour) a refuse, son motif et sa trace
  restent ecrits en section 1 a 5 et dans le premier bloc de validation, non effaces.
- **Arbitrage prealable** : `ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-
  plafond-a-huit-lignes.md`, ligne 425 - "les trois verbes de la parenthese. Jamais de definition
  juridique dans l'enonce". Referentiel : `squad/programme/enseignement-moral-et-
  civique/competences-cm1.md`, ligne 8.
- **Risque signale pour l'audit par `theo-archi`** (`squad/sprints/s40-couverture-maximale-emc-
  histoire-sciences/avis-faisabilite.md`, section 5.3) : `EMC-5` (deux reponses defendables),
  "aucune regle executable ne peut mordre EMC-5". **Ce risque-la n'est pas celui que j'ai trouve en
  jouant** - voir section 2.
- **Derniere mise a jour** : 2026-09-23 (second audit, s40, apres correction).

---

# Premier audit (2026-09-23) - REFUSE

Sections 1 a 5 et bloc de validation ci-dessous : trace du premier audit, non modifiee. Le
second audit commence en section 6.

## 1. Ce que j'ai joue

Reacteur construit (`mvn -B clean package -DskipTests` via `bash outils/mvn-jdk21.sh`, worktree
`s40-nadia-2` a `652cafc8`), `bootstrap/target/recreo.jar` lance sur le JDK 21 designe par la
toolchain. Partie complete de dix manches jouee jusqu'a l'ecran de fin (10 reussites sur 10), les
deux taches vues a l'ecran integralement (les six items de la tache A "regle ou loi", les cinq
items de la tache B "que fait cette loi ?").

## 2. Le defaut trouve en jouant, et pourquoi ce n'est pas `EMC-5`

**`EMC-5` (deux reponses defendables) n'est pas viole sur la tache A.** Chaque item porte un
marqueur de portee explicite et coherent (*"dans notre classe"* / *"dans notre ecole"* pour une
regle, *"en France"* pour une loi), verifie sur les six items : aucune ambiguite, le raisonnement
demande (la portee determine la reponse) est exactement celui que l'arbitrage prealable attend. Je
leve ce risque nomme d'avance, non trouve.

**Le defaut reel est different, et je l'ai trouve en jouant, pas en lisant le YAML seul** : sur les
**cinq items de la tache B (7 a 11, "que fait cette loi ?")**, l'enonce **nomme litteralement le
verbe attendu comme reponse**, avant meme la question :

- item 7 : *"La loi **protege** le droit de chaque enfant a etre soigne [...]. Que fait cette
  loi ?"* -> bonne reponse *"elle protege"*.
- item 8 : *"La loi **protege** un enfant contre toute forme de violence [...]. Que fait cette
  loi ?"* -> *"elle protege"*.
- item 9 : *"La loi **interdit** de fumer a l'interieur d'une ecole [...]. Que fait cette loi ?"*
  -> *"elle interdit"*.
- item 10 : *"La loi **interdit** de vendre de l'alcool a un mineur. Que fait cette loi ?"* ->
  *"elle interdit"*.
- item 11 : *"La loi **autorise** chacun a choisir librement sa religion [...]. Que fait cette
  loi ?"* -> *"elle autorise"*.

**Verifie a l'ecran, pas seulement dans le fichier** : les cinq items apparaissent tels quels au
jeu (capture, item 11 - *"La loi autorise chacun a choisir librement sa religion, ou de n'en avoir
aucune. Que fait cette loi ?"*, boutons *"elle autorise"* / *"elle interdit"* / *"elle protege"*).

**Objet.** *L'enfant identifie ce que la loi autorise, interdit ou protege, sur un exemple concret
qui ne le lui dit pas d'avance.* C'est litteralement la seconde moitie du libelle de `EMC-CM1-08`.
Un item ou le mot-reponse est deja ecrit dans l'enonce ne demande plus cette identification : il
demande de recopier un mot deja lu, sans le comprendre. Un enfant qui ne sait pas ce que veut dire
*autoriser*, *interdire* ou *proteger* reussit quand meme les cinq items en repetant le mot le plus
proche de "Que fait cette loi ?". **C'est pire qu'une absence, parce que ca fait croire a une
couverture** - le meme principe que `EMC-9` pose pour une autre ligne du meme referentiel.

**Ce que ce defaut n'est pas** : ce n'est pas une erreur factuelle (les cinq faits de droit cites
sont exacts et deja verifies sur le paquet voisin de CP), et ce n'est pas `EMC-5`. C'est un defaut
de forme d'item qu'aucun garde-fou existant ne mord : `ADR-0072` (`AucunePhraseDuTexteSupportNe-
SuffitAGagnerLaMajoriteTest`) porte sur un `texteSupport` separe de l'enonce, que ce paquet n'a pas
- ici le mot fuit dans l'enonce lui-meme, un terrain qu'aucun garde-fou ecrit ne couvre.

**Ampleur mesuree** : 5 items sur 5 de la tache B (100 %), donc 5 items sur 11 du paquet (45 %).

## 3. Ce qui est solide et n'a pas a etre reecrit

**La tache A (items 1 a 6, "regle ou loi ?") est entierement tenue** : six exemples concrets, portee
explicite et coherente a chaque fois, aucune definition juridique dans l'enonce, `TYP-1` (espace
insecable) respecte, `EMC-10` respecte (aucune personne reelle nommee). Les cinq faits de droit
cites dans la tache B (protection de l'enfant malade, protection contre la violence, interdiction
de fumer a l'ecole, interdiction de vente d'alcool aux mineurs, liberte de conscience et de culte)
sont exacts et deja verifies sur `cp-emc-regle-souhait-loi.yaml` ou `cm1-emc-droit-devoir.yaml`.
**Rien de cela n'a besoin d'etre reecrit.**

## 4. Motif de refus, actionnable

**Reecrire les cinq items de la tache B (7 a 11) pour que le verbe de reponse (autorise / interdit
/ protege) n'apparaisse jamais dans l'enonce.** L'enonce decrit la situation ou le fait de droit
sans nommer d'avance ce que la loi en fait, et la question reste *"que fait cette loi ?"* ou une
formulation equivalente. Exemple de correction pour l'item 11 : *"Chacun peut choisir librement sa
religion, ou de n'en avoir aucune : c'est la loi. Que fait cette loi ?"* reste fautif (le mot
"autorise" est implicite mais la construction reste une reformulation directe) - une description
par consequence plutot que par l'acte lui-meme est preferable, par exemple : *"Personne ne peut
obliger un enfant a croire ou a ne pas croire : c'est la loi. Que fait cette loi ?"*. Je ne fixe pas
la formulation exacte ici, ce n'est pas mon role ; je fixe le critere : **le mot-reponse absent de
l'enonce**, verifiable a la lecture avant tout nouveau jeu.

Les cinq faits de droit et les cinq bonnes reponses (le verbe correct pour chacun) restent
inchanges. Seule la formulation de l'enonce doit changer.

## 5. Ce dont je reste la seule garde

Aucun garde-fou automatique ne peut detecter qu'un enonce nomme d'avance sa propre reponse : c'est
une lecture de sens, pas une correspondance de chaine que `ADR-0072` couvrirait (il porte sur un
support separe, absent ici). C'est moi qui la tiens, trouvee en jouant ce jour.

---

## Bloc de validation (premier audit, 2026-09-23) - trace non effacee

> **Done pedagogique : REFUSE.**
>
> **Motif** : sur la tache B (items 7 a 11, "que fait cette loi ?"), le verbe de reponse attendu
> (autorise / interdit / protege) est deja ecrit dans l'enonce lui-meme, sur 5 items sur 5. La tache
> devient une copie du mot deja lu, pas une identification de ce que fait la loi - la moitie du
> libelle de `EMC-CM1-08` que cette tache doit servir. La tache A (items 1 a 6) est solide et n'a
> pas besoin d'etre reecrite.
>
> **Correction demandee** : reecrire les cinq enonces de la tache B pour que le mot-reponse
> n'apparaisse jamais dans l'enonce, en conservant les cinq faits de droit et les cinq bonnes
> reponses. Detail en section 4.
>
> **Ce refus renvoie a `camille-po`, qui en fait du backlog.** Il ne bloque pas le sprint suivant.
> `EMC-CM1-08` reste `non couvert` dans `MATRICE-couverture.md` ; je ne touche pas la matrice.

---

# Second audit (2026-09-23, apres correction de `marc-dev`) - VALIDE

## 6. Ce que j'ai rejoue

Reacteur reconstruit (`bash outils/mvn-jdk21.sh -B clean package -DskipTests`, worktree
`s40-nadia-3`, sur le commit `3aadc447`), `bootstrap/target/recreo.jar` relance sur le JDK 21
designe par la toolchain. Partie jouee de bout en bout jusqu'a l'ecran de fin (8 reussites sur 10
- deux echecs de ma faute, un encodage d'accent dans mon outillage de jeu en ligne de commande, pas
un defaut du jeu : rejoues et confirmes corrects juste apres). J'ai vu a l'ecran les cinq items de
la tache B (7 a 11) et cinq des six items de la tache A (1, 3, 4, 5, 6 - le sixieme, deja verifie
et non touche par cette correction, n'est pas remonte dans le tirage de dix manches sur onze
items).

## 7. Verification du correctif : le verbe ne fuit plus

Les cinq enonces de la tache B, vus a l'ecran :

- item 7 (protege) : *"Une loi dit que personne ne peut empecher un enfant malade d'etre soigne,
  meme si ses parents manquent d'argent. Que fait cette loi ?"*
- item 8 (protege) : *"Une loi dit qu'un enfant doit toujours etre en securite, meme face a un
  adulte de sa famille. Que fait cette loi ?"*
- item 9 (interdit) : *"Une loi dit que fumer a l'interieur d'une ecole, meme dans la cour, est
  puni d'une amende. Que fait cette loi ?"*
- item 10 (interdit) : *"Une loi dit que vendre de l'alcool a un enfant est puni d'une amende pour
  le vendeur. Que fait cette loi ?"*
- item 11 (autorise) : *"Une loi dit que personne ne peut obliger un enfant a croire en une religion,
  ou a ne pas y croire. Que fait cette loi ?"*

Aucun des cinq n'ecrit plus "autorise", "interdit" ou "protege" dans l'enonce : le mot-reponse est
absent, exactement ce que mon refus demandait. La tache redevient une identification - l'enfant
doit lire un fait et en deduire l'effet de la loi, pas recopier un mot deja lu. Les cinq faits de
droit et les cinq bonnes reponses sont inchanges par rapport a mon premier audit (section 3), donc
toujours exacts.

## 8. Le point que j'ai controle de pres : l'item 11 fuit-il un raisonnement plutot qu'un mot ?

Items 7 et 11 partagent la meme construction ("personne ne peut [verbe] un enfant...") pour deux
bonnes reponses differentes (protege / autorise), ce qui aurait pu ouvrir une nouvelle ambiguite -
un enfant qui a rencontre "personne ne peut empecher" -> "protege" sur un item pourrait plaquer le
meme reflexe sur "personne ne peut obliger" en item 11, et repondre "protege" au lieu d'"autorise",
avec "elle interdit" tout aussi tentant par lecture litterale de "ne peut" comme un interdit.

J'ai controle ce risque specifiquement, en le jouant et en le relisant plusieurs fois. Ce qui leve
le risque : l'item 11 ne se limite pas a "personne ne peut obliger" - il ajoute *"a croire en une
religion, ou a ne pas y croire"*, qui nomme explicitement les deux issues possibles pour l'enfant
et met en avant un choix, pas une interdiction faite a un tiers ni une mise a l'abri d'un danger.
C'est ce choix explicitement nomme qui signale "autorise" sans ambiguite reelle : la loi permet a
l'enfant de choisir entre deux options nommees, ce qui est la definition meme d'autoriser. Les
items 7 et 8, a l'inverse, ne nomment aucun choix pour l'enfant - ils decrivent une mise a l'abri
(le soin, la securite), qui est la definition de proteger. Les deux items different donc sur ce qui
compte reellement : la presence ou l'absence d'un choix nomme pour l'enfant, pas la seule
construction "personne ne peut X". Je leve ce risque, trouve et instruit en jouant, non trouve en
lisant seul le YAML.

## 9. Ce qui reste solide

La tache A (items verifies : 1, 3, 4, 5, 6) est inchangee et reste ce que j'avais valide en
section 3 du premier audit. `EMC-10`, `TYP-1`, le traitement de l'erreur (indice affiche apres un
premier echec, jeu qui laisse recommencer) sont inchanges et corrects.

## 10. Ce dont je reste la seule garde

Comme en section 5 du premier audit : aucun garde-fou automatique ne detecte qu'un enonce nomme
d'avance sa reponse, ni qu'une construction de phrase entre deux items d'un meme paquet cree un
faux raisonnement par analogie (section 8 ci-dessus). C'est une lecture de sens que seule une
lecture humaine, faite en jouant, peut couvrir.

## Bloc de validation (second audit, 2026-09-23)

> **Done pedagogique : VALIDE.**
>
> **Ce qui a ete verifie** : les cinq enonces de la tache B (items 7 a 11) ne nomment plus le verbe
> de reponse attendu - le defaut motivant le refus du premier audit est corrige, verifie a l'ecran
> sur le jar reconstruit. Les cinq faits de droit et les cinq bonnes reponses sont inchanges et
> exacts. La tache A (items 1 a 6) est inchangee et reste solide. J'ai specifiquement controle un
> risque nouveau ouvert par la reformulation - une confusion entre "protege" et "autorise" sur
> l'item 11, par analogie de construction avec l'item 7 - et je le leve : le choix explicitement
> nomme dans l'item 11 ("a croire [...] ou a ne pas y croire") signale sans ambiguite "autorise",
> a la difference des items 7 et 8 qui ne nomment aucun choix et decrivent une mise a l'abri.
>
> **`EMC-CM1-08` peut passer a `couvert-valide` dans `MATRICE-couverture.md`** - c'est `camille-po`
> qui fait ce geste, pas moi.
