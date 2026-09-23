# Fiche de conformite - bandit-manchot-regle-loi-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-emc-regle-loi.yaml`
  (11 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-regle-loi-cm1`, titre servi *"Regle ou loi"*, consigne
  servie *"Lis bien, puis dis si c'est une regle ou une loi, ou ce que fait cette loi."*,
  `nombreDeManches: 10`.
- **Ligne declaree** : `EMC-CM1-08`.
- **Statut global** : **REFUSE**. Motif ecrit ci-dessous, actionnable sans redemander.
- **Arbitrage prealable** : `ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-
  plafond-a-huit-lignes.md`, ligne 425 - "les trois verbes de la parenthese. Jamais de definition
  juridique dans l'enonce". Referentiel : `squad/programme/enseignement-moral-et-
  civique/competences-cm1.md`, ligne 8.
- **Risque signale pour l'audit par `theo-archi`** (`squad/sprints/s40-couverture-maximale-emc-
  histoire-sciences/avis-faisabilite.md`, section 5.3) : `EMC-5` (deux reponses defendables),
  "aucune regle executable ne peut mordre EMC-5". **Ce risque-la n'est pas celui que j'ai trouve en
  jouant** - voir section 2.
- **Derniere mise a jour** : 2026-09-23 (premier audit, s40).

---

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

## Bloc de validation

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
