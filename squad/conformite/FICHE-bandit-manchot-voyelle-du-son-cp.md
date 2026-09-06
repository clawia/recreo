# Fiche de conformite - bandit-manchot-voyelle-du-son-cp

- **Mecanique** : bandit manchot ; champ d'item a voix haute (`ADR-0075`).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-voyelle-du-son.yaml`.
- **Entree de catalogue** : `bandit-manchot-voyelle-du-son-cp`, titre servi *"La bonne voyelle"*, `nombreDeManches: 10`.
- **Statut global** : **VALIDE** (Done pedagogique prononce le 2026-09-03). **La ligne "Associer les graphemes aux phonemes" se ferme** : ma clause 1 du 2026-08-10 exigeait les voyelles simples **et** les consonnes simples ; les consonnes etaient servies par `cp-lettre-du-son` depuis s26, les voyelles le sont par ce paquet. Clauses 2, 3 et 4 deja remplies. Ligne de matrice : **`en cours` -> `couvert-valide`**.
- **Arbitrage prealable de `nadia-prof`** : barre du 2026-08-10 (clause 1) ; `ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md` pour `VOI-1` a `VOI-12` ; `DEC-0069` section 4.
- **Derniere mise a jour** : 2026-09-03 (**verdict de `nadia-prof` apres avoir joue**, s27).

## Competence visee

`Associer les graphèmes aux phonèmes (voyelles, consonnes simples, puis digraphes ch, ou, on...)`
(Français, CP, Lecture et compréhension de l'écrit).

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `d259b5f`, jar reconstruit et lance, parties reelles par appels
HTTP contre l'application, **y compris en me trompant volontairement**. Ce n'est pas un audit
degrade.

### 1. Les seize mots, dits a voix haute, un par un

**C'est le controle que rien ne fait a ma place, et je l'ai fait entierement, pas par
echantillon.** Pour chaque item j'ai prononce le mot et ecoute son tout premier son.

| Voyelle | Mots servis | Tout premier son reellement produit |
|---|---|---|
| **a** | ami, arbre, avion, abricot | /a/ sur les quatre |
| **i** | igloo, image, iguane, ici | /i/ sur les quatre |
| **o** | orange, os, olive, ogre | /o/ ouvert sur les quatre |
| **u** | usine, uniforme, ustensile, utile | /y/ sur les quatre |

**Seize sur seize justes. Aucune erreur de fait.** Aucun mot ne commence par une voyelle nasale ni
par un digraphe - verifie mot a mot, c'est la confusion qui aurait rendu ce paquet faux.

### 2. La distribution, comptee sur le fichier

Chaque voyelle est **reponse 4 fois et distracteur 8 fois** - ratio 1:2, le meme que
`cp-lettre-du-son`. Aucune voyelle n'est sur-representee en bonne reponse. **Un enfant qui
repondrait toujours la meme lettre aurait 25 %**, pas davantage.

### 3. `VOI-12` - la clause dont l'absence avait coute cinq refus a s26

**Tenue, et je l'ai lue a l'ecran, pas dans le fichier.** Sur l'ecran d'item, sur l'ecran d'erreur
et sur l'ecran de revelation, la ligne de l'adulte porte :

> *"Dites le mot entier, jamais le son tout seul. Ne montrez pas cette ligne a l'enfant."*

Elle dit **ce qu'il ne doit pas faire**, elle est **a l'endroit ou l'adulte lit son texte**, et
elle est distincte de la consigne de l'enfant. C'est exactement ce que j'avais demande.

### 4. Ce que j'ai verifie en me trompant expres - et c'est la que ce paquet gagne

**Cinq erreurs volontaires de suite sur le meme item.** L'enfant reste sur l'item, recoit
*"Ce n'est pas ca. Essaie encore !"* puis son indice, et **au bout de plusieurs echecs l'ecran
revele la reponse** : *"C'est o. Appuie sur o."* **Un enfant bloque peut avancer.**

**Et le point que j'avais price a s26 sans pouvoir le verifier : sur l'ecran de revelation, la
ligne de l'adulte porte toujours *"Le mot est orange"* et rien d'autre.** Elle ne contient pas la
lettre. **L'adulte ne peut donc pas dire la reponse en lisant son texte** - c'etait le risque exact
que j'avais chiffre a un paquet dans la branche "mecanique" de s26. Il ne se realise pas.

### 5. Le retrait du `e` - **accepte, et le motif est meilleur que le mien**

`lea-dev` sert **quatre voyelles et non cinq**, avec un motif ecrit : au debut d'un mot francais
courant, la lettre `e` non accentuee ne porte quasiment jamais le son que son nom suggere a un
enfant de CP ; *ecole* et *elephant* s'ecrivent avec un accent, qui change le grapheme.

**C'est exact et c'est fin.** Enseigner `e` -> [e] sur *ecole* apprendrait a l'enfant que le
grapheme `e` fait /e/, alors que le grapheme est `e accent aigu`. **Ce serait une erreur de fait,
et je l'aurais refusee.** Mon propre critere autorisait explicitement *"un sous-ensemble couvrant
au moins une voyelle simple en bonne reponse"* : **je m'y tiens et je ne durcis pas apres coup.**

### 6. `VOI-9` ne gouverne pas ce paquet, et je le dis avant qu'on le "decouvre"

Trois mots servis font trois syllabes (*abricot*, *uniforme*, *ustensile*), alors que `VOI-9` dit
*"deux syllabes au plus"*. **`VOI-9` ne s'applique pas ici**, et son motif ecrit le dit : *"un mot
dont l'ecriture ne se deduit pas du son n'evalue pas cette ligne, il evalue une memoire
d'orthographe"*. **Cette clause gouverne la ligne ou l'enfant ECRIT le mot**, pas celle ou il
designe la lettre du premier son. Ici l'enfant n'ecrit rien et n'a a tenir que le debut du mot.

**Je l'ecris explicitement parce que c'est ma clause, que sa lettre est plus large que son motif,
et que je ne veux pas qu'on refuse ce paquet dans deux sprints sur une extension que je viens de
refuser moi-meme.**

### 7. Reserves - **nouvelles et datees du 2026-09-03, non bloquantes**

**Aucune n'est un durcissement.** Les deux premieres portent sur des textes que **j'ai valides
identiques a s26** : je ne deplace pas mes poteaux, je vois plus loin et je l'ecris comme neuf.

- **`VOY-R1` - la consigne et l'enonce disent deux fois la meme chose.** A l'ecran, l'enfant lit
  *"Ecoute l'adulte lire le mot, puis choisis la lettre qui fait le tout premier son."* **puis**
  *"Ecoute l'adulte, puis choisis la lettre qui fait le tout premier son."* **Vingt-six mots de
  consigne quasi identique pour un enfant qui lit peu et lentement.** **Present a l'identique dans
  `cp-lettre-du-son` et `cp-digraphe-du-son`, que j'ai valides le 2026-09-01 sans le voir.** La
  reserve porte donc sur **les trois paquets**, pas sur celui-ci seul.
- **`VOY-R2` - l'indice invite l'adulte a un geste que `VOI-3` interdit.** *"Demande a l'adulte de
  redire le mot **doucement**"* : *doucement* est a un pas de *lentement*, et *lentement* est a un
  pas du decoupage syllabique, que `VOI-3` et `VOI-8` refusent. L'enfant demande, l'adulte fait ce
  qu'on lui demande, pas ce que disait la ligne qu'il a lue dix secondes plus tot. **Formulation
  identique dans les deux paquets de s26.** Piste : *"Demande a l'adulte de redire le mot en
  entier."*
- **`VOY-R3` - le `e` n'apparait jamais, meme comme distracteur.** L'enfant ne voit que quatre
  lettres sur l'ensemble du jeu. **Le mettre en distracteur ne porte aucun des risques que
  `lea-dev` a identifies** - un distracteur n'est jamais enseigne comme juste.
- **`VOY-R4` - *ustensile* est un mot que peu d'enfants de CP connaissent.** Non bloquant : la
  tache est phonologique et le mot est dit par l'adulte, donc l'enfant n'a pas besoin de le
  connaitre.
- **`VOY-R5` - sur l'ecran de revelation, l'indice reste affiche** sous *"C'est o. Appuie sur o."*
  Envoyer l'enfant reecouter alors qu'on vient de lui donner la reponse est du bruit.

### 8. Ce dont je reste la seule garde sur ce jeu

`VOI-3`, `VOI-5` et `VOI-8` - ce que l'adulte fait reellement de sa voix - **ne sont couvertes par
aucun test et ne le seront pas**. `VOI-12` est le seul paiement que le produit puisse faire, et il
le fait. **Rien ne doit suggerer que ces trois conditions sont gardees.**

---

## Reverification de s29 (`S29-8`, `6b2edf1`) - **la dette des paquets a voix est payee**

- **Verdict** : **la dette est payee sur ce jeu**, constate le **2026-09-06** par `nadia-prof`,
  **Instance A**, sur le contenu servi. **Aucun Done n'est rouvert, aucune ligne de matrice ne
  bouge** : la reserve etait non bloquante des son ouverture et le reste.
- **Origine** : reserve ouverte a s26, **elargie par ma propre mesure a s27** (six jeux et non trois),
  **chiffree a s28** (0,25, un passage), **non payee a s28**. Elle aurait eu quatre sprints si s29 ne
  l'avait pas payee. **Elle est payee.**
- **Regime et prix** : mot pour mot, **0,25 pour les six jeux ensemble** - une reverification
  d'identite, pas une pedagogie.

### Ce que j'ai verifie sur le contenu servi

| Cle | Servi le 2026-09-06 | Conforme au texte que j'ai fourni ? |
|---|---|---|
| `enonce` (16 items) | *"Quelle lettre fait le premier son ?"* | **oui, mot pour mot** |
| `indice` (16 items) | *"Demande a l'adulte de redire le mot en entier, puis ecoute le tout premier son."* | **oui, mot pour mot** |
| consigne de catalogue | inchangee | **oui** |

**`VOI-3` cesse d'etre contredite par ecrit** et la redondance de l'enonce est levee du bon cote.

### Ce que ce constat ne prouve pas

**Je n'ai verifie que le texte ecrit.** `VOI-3`, `VOI-5` et `VOI-8` portent sur ce que l'adulte **fait
de sa voix**, et **personne ne le garde, pas meme moi** : je ne suis pas dans la piece quand l'enfant
joue. Ce que ce lot repare, c'est que **le produit cessait de demander par ecrit le contraire de ce
qu'il demande par ailleurs a l'adulte**. Un adulte qui ralentit quand meme n'est arrete par rien, et
ne le sera jamais. **`VOI-3` n'a aucun garde-fou et n'en aura pas.**

**Un ecart de forme que je constate et que je ne compte pas comme un ecart** : les enonces servis
portent une espace insecable devant le point d'interrogation, la ou mes textes portaient une espace
ordinaire. C'est la typographie du canal enfant ; elle appartient au `relecteur-pedagogique`
(`DEC-0002`, amendement du 2026-08-04 ; `DEC-0056`), et j'avais moi-meme ecrit que l'orthographe,
l'accentuation et la ponctuation de ces textes ne m'appartiennent pas. **Le mot pour mot est tenu.**

**Un jeu voisin que j'ai verifie et que j'ecarte, pour qu'on ne le rouvre pas** :
`bandit-manchot-syllabes-cp` sert trois indices qui disent *"tout doucement"*, *"tres lentement"*,
*"Chante le mot tres lentement"*. **Ce n'est pas un defaut `VOI-3`** : ce jeu **ecrit le mot a
l'ecran** et c'est **l'enfant** qui le prononce, pas l'adulte ; ralentir pour frapper les syllabes est
le geste de classe correct. `VOI-3` gouverne la voix de l'adulte, et ce jeu n'en a pas. **Verifie sur
le catalogue et sur le contenu, pas suppose.**
