# Fiche de conformite - bandit-manchot-droits-enfant-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-emc-droits-enfant.yaml`
  (12 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-droits-enfant-cp`, titre servi *"Les droits de
  l'enfant"*, consigne servie *"Lis bien, puis dis quel droit c'est, ou ce qu'il garantit."*,
  `nombreDeManches: 10`.
- **Ligne declaree** : `EMC-CP-10`.
- **Statut global** : **VALIDE SOUS RESERVE** (audit joue du 2026-09-24, `nadia-prof` instance A).
  Fiche ouverte par `camille-po` a l'ouverture du bloc de construction du sprint s41 (paquet et
  catalogue livres et fusionnes dans `round-two`, commit `b5b07966`).
- **Story** : `S41-EMC-DROITS` - Identifier les principaux droits de l'enfant
  (`docs/backlog/backlog.md`).
- **Competence programme visee** : enseignement moral et civique, CP (cycle 2), domaine "Acquerir
  et partager les valeurs de la Republique" -
  `squad/programme/enseignement-moral-et-civique/competences-cp.md`, `EMC-CP-10`. Ligne
  `non couvert` dans `squad/programme/MATRICE-couverture.md` au moment d'ouverture de cette fiche.
- **Arbitrage pedagogique prealable deja rendu, cite en entier dans la story, aucun arbitrage neuf
  a router** (`ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-
  borne.md`, ligne 236) : *"oui, et c'est le terrain le plus sur des cinq a 1,5. `FER-1` : etre
  protege, etre soigne, aller a l'ecole, jouer. Deux taches : reconnaitre le droit dont parle une
  situation ; dire ce que le droit garantit. `BIA-4` et `EMC-1` : aucun item n'interroge l'enfant
  sur sa propre situation. 'As-tu le droit d'aller a l'ecole ?' est refuse d'avance."* Ligne
  evaluee par `nadia-prof` elle-meme comme la moins risquee des cinq lignes d'EMC CP a ce tarif.
- **Lien decision** : `DEC-0095`.
- **Derniere mise a jour** : 2026-09-24 (audit pedagogique, `nadia-prof` instance A).

---

## Criteres d'acceptation de la story (`docs/backlog/backlog.md`, `S41-EMC-DROITS`)

- Etant donne le paquet livre, quand on le relit dans son entier, alors les quatre droits du
  libelle (etre protege, etre soigne, aller a l'ecole, jouer) sont chacun en bonne reponse au
  moins une fois (`FER-1`).
- Etant donne un item, quand il s'affiche, alors il porte l'une des deux taches distinctes
  (reconnaitre le droit dont parle une situation decrite ; dire ce que le droit garantit), jamais
  uniquement la premiere sur tout le paquet (`FER-3`).
- Etant donne un item, quand la situation ou la question s'affiche, alors elle ne s'adresse jamais
  directement a l'enfant sur sa propre situation (par exemple "as-tu le droit de...") mais decrit
  un personnage ou une situation generale (`BIA-4`, `EMC-1`).
- Etant donne cette manche, quand les propositions s'affichent, alors la bonne reponse y figure
  exactement une fois, avec des distracteurs qui sont d'autres droits ou notions reels, jamais des
  elements sans rapport.
- Etant donne chaque item du paquet, quand il declare sa ligne de referentiel, alors il porte
  `EMC-CP-10` et uniquement elle.
- Etant donne le Done pedagogique de ce paquet, quand `nadia-prof` (instance A) le joue, alors
  elle confirme que les quatre droits sont chacun servis et qu'aucun item n'interroge l'enfant sur
  sa propre situation.

## Ce que cette story ne fait pas

Elle n'interroge jamais l'enfant sur sa propre situation (`BIA-4`, `EMC-1`) ; elle ne fixe aucun
contenu factuel precis - reste a l'auteur du paquet puis a l'audit de `nadia-prof`.

---

## Bloc de validation pedagogique

# Audit du 2026-09-24 (`nadia-prof`, instance A de s41) - VALIDE SOUS RESERVE

## 1. Ce que j'ai joue

Reacteur reconstruit depuis `round-two` au commit `3f6b379f`
(`bash outils/mvn-jdk21.sh -B clean package -DskipTests`), `bootstrap/target/recreo.jar` relance
sur le JDK 21 designe par la toolchain. **Audit joue, pas lu** : cinq parties completes de dix
manches, plus une partie ou je me trompe volontairement a repetition. **Les douze items sont
remontes a l'ecran**, les huit de la tache de reconnaissance et les quatre de la tache de sens.

## 2. Les conditions que j'avais posees d'avance, verifiees a l'ecran

- **`BIA-4` et `EMC-1`, la condition qui fait la valeur de cette ligne** : *aucun* des douze items
  n'interroge l'enfant sur sa propre situation. Les huit items de reconnaissance mettent en scene
  quatre personnages fictifs (Lea, Hugo, Aya, Malo), les quatre items de sens sont des questions
  impersonnelles (*"Que garantit le droit de... ?"*). La formule que j'avais refusee d'avance,
  *"As-tu le droit d'aller a l'ecole ?"*, n'apparait sous aucune forme, ni dans un enonce, ni dans
  une proposition, ni dans un indice. **C'est la condition centrale de cette ligne et elle est
  tenue sans exception.**
- **`FER-1`** : les quatre droits du libelle sont chacun en bonne reponse, vus a l'ecran - etre
  protege (items 1, 2), etre soigne (3, 4), aller a l'ecole (5, 6), jouer (7, 8).
- **`FER-3`** : deux taches distinctes, jouees toutes les deux.
- **`EMC-5`** : je n'ai trouve aucun item a deux reponses defendables. Le seul que j'ai regarde
  deux fois est l'item 7 (*"Aya construit une tour avec des cubes pendant la recreation"*, bonne
  reponse *"jouer"*, distracteur *"aller a l'ecole"*) : la recreation se passe a l'ecole, et un
  enfant pourrait s'y accrocher. Ce qui leve le doute, et je le dis parce que je l'ai pese : la
  question porte sur le droit **qu'illustre l'action decrite**, et l'action decrite est le jeu ;
  l'indice (*"Elle s'amuse librement avec ses jouets"*) le confirme sans ambiguite. Une seule
  reponse defendable.
- **Exactitude** : les quatre droits et les quatre garanties sont exacts et conformes au libelle de
  `EMC-CP-10`. Aucun distracteur absurde : ce sont toujours d'autres droits reels de la meme
  famille.
- **Biais** : deux filles, deux garcons ; aucun des quatre personnages n'est assigne a un role
  stereotype ; aucune configuration familiale, aucun milieu social, aucune origine n'est
  presuppose. Le mot *maitresse* apparait en item 6 et *maitresse ou maitre* dans l'indice de
  l'item 11, ce qui equilibre.
- **Lisibilite** : aucun enonce ne depasse quinze mots ni deux phrases, la seconde etant la
  question. Les propositions de la tache de sens sont plus longues (*"qu'un medecin s'occupe de
  lui"*), ce qui reste lisible pour un enfant de CP accompagne.
- **Traitement de l'erreur** : premiere erreur, *"Ce n'est pas ca. Essaie encore !"* suivi de
  l'indice ; troisieme erreur, la bonne reponse est revelee. Un enfant bloque avance. Verifie en
  jouant faux volontairement.

**Le Done pedagogique est ouvert.** Ce qui suit ne le referme pas.

## 3. Reserve 1, neuve et datee du 2026-09-24 : le verbe *"illustre"*, huit items sur douze

**Constat.** Les huit items de la tache de reconnaissance posent la question *"Quel droit est
illustre ?"*.

**Ce que je fais et ce que je ne fais pas, et il faut que ce soit clair.** J'ai ouvert le
2026-09-23, sur `FICHE-bandit-manchot-devise-cp.md` section 3, une reserve **non bloquante** sur ce
meme verbe, employe sur dix enonces de `cp-emc-devise`. La barre que j'avais posee ce jour-la etait
*"non bloquante"*, et **elle le reste ici** : je ne la transforme pas en motif de refus parce que
le mot reapparait le lendemain sur un autre paquet. **Une barre qu'on releve apres coup fait de ma
porte un caprice.** J'ouvre donc une reserve nouvelle et datee, de meme nature et de meme poids que
la precedente, plutot qu'un durcissement de l'ancienne.

**Motif, inchange depuis le 2026-09-23** : *illustrer* est d'un registre plus soutenu que le
vocabulaire d'un enonce de CP dans ce depot (`cp-emc-regles-de-vie` : *"Que doit faire..."*,
*"Pourquoi..."*). Un enfant de premiere annee de lecture ne rencontre pas ce mot dans son
vocabulaire courant. Il ne bloque pas la comprehension - la question est courte, le motif devient
previsible par repetition, et le CP est accompagne d'un adulte - mais deux paquets de CP en deux
jours suffisent a en faire un motif d'ecriture, et c'est precisement ce que j'avais ecrit vouloir
signaler avant qu'il ne s'installe.

**Correction demandee, non bloquante** : remplacer *illustre* par un verbe concret deja rencontre
en CP - par exemple *"Quel droit est montre ici ?"*, ou plus simplement *"De quel droit
parle-t-on ?"*, qui est la formulation la plus proche de ce que l'enfant doit faire. Les huit
enonces de la tache de reconnaissance sont concernes ; aucune proposition, aucun indice, aucun item
de la tache de sens ne l'est.

## 4. Reserve 2, neuve et datee du 2026-09-24 : *"lui"* sans antecedent, quatre items sur douze

**Constat.** Les quatre items de la tache de sens posent une question impersonnelle et y repondent
par un pronom sans antecedent : *"Que garantit le droit d'etre protege ?"* -> *"que personne ne
**lui** fait de mal"* ; *"Que garantit le droit d'etre soigne ?"* -> *"qu'un medecin s'occupe de
**lui**"* ; *"Que garantit le droit d'aller a l'ecole ?"* -> *"qu'**il** peut apprendre et
grandir"*. Aucun nom n'a ete prononce dans l'enonce : le *lui* ne renvoie a personne.

**Pourquoi c'est une reserve et pas un refus.** Le sens reste reconstructible : l'enfant vient de
lire *"le droit d'etre protege"* et comprend qu'il s'agit de l'enfant qui a ce droit. Rien n'est
faux, rien n'est ambigu quant a la bonne reponse. Mais un pronom sans antecedent, sur une tache ou
l'enfant doit deja tenir en tete un droit nomme et trois formulations longues, est une charge
gratuite en CP - et cette moitie-la du paquet est deja la plus exigeante des deux.

**Correction demandee, non bloquante** : nommer le sujet dans les propositions, par exemple *"que
personne ne fait de mal a l'enfant"*, *"qu'un medecin s'occupe de l'enfant"*, *"que l'enfant peut
apprendre et grandir"*, *"que l'enfant a du temps pour s'amuser"*. Les quatre items de la tache de
sens sont concernes, aucun autre.

**Ni l'une ni l'autre de ces deux reserves n'est de mon ressort orthographique** : elles portent sur
le **vocabulaire et le niveau scolaire**, pas sur l'orthographe, l'accentuation ou la ponctuation,
qui appartiennent au `relecteur-pedagogique` (`DEC-0002` amende le 2026-08-04, `DEC-0056`).

## 5. Ce dont je reste la seule garde sur ce paquet

| Propriete | Automatisable ? | Qui la tient |
|---|---|---|
| `BIA-4` / `EMC-1` - aucun item n'interroge l'enfant sur sa propre situation | non (jugement semantique) | **moi seule** |
| `EMC-5` - aucun item n'a deux reponses defendables | non | **moi seule** |
| Exactitude des quatre droits et des quatre garanties | non | **moi, contre le libelle** |
| Registre de vocabulaire adapte au CP (reserve 1) | non | **moi seule** |

La CI est verte sur cet increment et **n'a vu aucune des deux reserves** : ce sont des jugements de
registre, aucun automate ne les rend.

---

> **Done pedagogique : VALIDE SOUS RESERVE.** 2026-09-24, `nadia-prof`, instance A de s41.
>
> **Motif de la validation** : les douze items ont ete joues. `BIA-4` et `EMC-1` - la condition
> centrale de cette ligne, posee d'avance - sont tenues sans exception : aucun item n'interroge
> l'enfant sur sa propre situation, les huit situations passent par un personnage fictif et les
> quatre questions de sens sont impersonnelles. `FER-1` et `FER-3` sont tenues, les quatre droits
> du libelle sont chacun servis, les contenus sont exacts, les distracteurs plausibles, l'erreur
> est traitee (indice puis revelation) et aucun item n'a deux reponses defendables. `camille-po`
> peut passer `EMC-CP-10` a `couvert-valide`.
>
> **Reserve 1, non bloquante** (neuve, datee du 2026-09-24) : remplacer le verbe *illustre* dans
> les huit enonces de la tache de reconnaissance par un verbe concret de CP (*"De quel droit
> parle-t-on ?"*). Meme nature et meme poids que la reserve ouverte le 2026-09-23 sur
> `cp-emc-devise` ; je n'en durcis pas la barre.
>
> **Reserve 2, non bloquante** (neuve, datee du 2026-09-24) : nommer le sujet dans les quatre
> propositions de la tache de sens, ou *lui* et *il* n'ont aucun antecedent (*"que personne ne fait
> de mal a l'enfant"*).
>
> Les deux reserves sont a transformer en stories par `camille-po`. Aucune ne referme le Done.
