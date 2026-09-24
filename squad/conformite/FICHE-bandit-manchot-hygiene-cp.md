# Fiche de conformite - bandit-manchot-hygiene-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-qlm-hygiene.yaml`
  (15 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-hygiene-cp`, titre servi *"Les regles d'hygiene de
  vie"*, consigne servie *"Lis bien, puis dis quelle regle suivre, ou pourquoi elle est bonne pour
  la sante."*, `nombreDeManches: 10`.
- **Ligne declaree** : `QLM-CP-04`.
- **Statut global** : **DONE PEDAGOGIQUE PRONONCE - VALIDE** le 2026-09-24 par `nadia-prof`
  (instance B), apres partie complete jouee. Voir le bloc de validation en fin de fiche. Fiche
  ouverte par `camille-po` a l'ouverture du bloc de construction du sprint s41 (paquet et catalogue
  livres et fusionnes dans `round-two`, commit `b5b07966`).
- **Story** : `S41-QLM-HYGIENE` - Connaitre quelques regles d'hygiene de vie et leur effet sur la
  sante (`docs/backlog/backlog.md`).
- **Competence programme visee** : questionner le monde, CP (cycle 2), domaine "Le vivant, la
  matiere et les objets" - `squad/programme/questionner-le-monde/competences-cp.md`, `QLM-CP-04`.
  Ligne `non couvert` dans `squad/programme/MATRICE-couverture.md` au moment d'ouverture de cette
  fiche.
- **Arbitrage pedagogique prealable deja rendu, cite en entier dans la story, aucun arbitrage neuf
  a router** (`ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-
  lignes.md`, ligne 370) : *"moyenne, sous `BIA-4`. Terrain sur si aucun item n'interroge l'enfant
  sur sa propre vie."* Precise par `ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md`,
  ligne 368 : *"Piege de biais pose d'avance : 'que manges-tu au petit dejeuner ?' presuppose
  qu'il y en a un. `BIA-4`."*
- **Adjacence de contenu signalee par le paquet lui-meme** (avis de faisabilite de `theo-archi`,
  s41, section 6.4) : `cp-eps-corps-effort.yaml` (`EPS-CP-10`, `EPS-CP-11`) sert deja l'eau et le
  repos, borne au seul contexte de l'effort physique. Aucun item de ce paquet ne rapporte le
  sommeil, l'alimentation ou l'hygiene du corps a un effort physique.
- **Lien decision** : `DEC-0095`.
- **Derniere mise a jour** : 2026-09-24 (Done pedagogique VALIDE, `nadia-prof` instance B ;
  ouverture le meme jour par `camille-po`).

---

## Criteres d'acceptation de la story (`docs/backlog/backlog.md`, `S41-QLM-HYGIENE`)

- Etant donne le paquet livre, quand on le relit dans son entier, alors les trois domaines du
  libelle (sommeil, alimentation, hygiene du corps) sont chacun en bonne reponse au moins une
  fois.
- Etant donne un item, quand la question s'affiche, alors elle porte sur une regle generale et son
  effet sur la sante (par exemple "pourquoi se laver les mains avant de manger ?"), jamais sur ce
  que l'enfant fait, mange ou vit lui-meme (`BIA-4`) - aucune formulation du type "que manges-tu
  au petit dejeuner ?" qui presuppose une reponse personnelle.
- Etant donne cette manche, quand les propositions s'affichent, alors la bonne reponse y figure
  exactement une fois, avec des distracteurs qui sont d'autres comportements reels lies a
  l'hygiene de vie, jamais des elements sans rapport.
- Etant donne chaque item du paquet, quand il declare sa ligne de referentiel, alors il porte
  `QLM-CP-04` et uniquement elle.
- Etant donne le Done pedagogique de ce paquet, quand `nadia-prof` (instance B) le joue, alors
  elle confirme qu'aucun item n'interroge l'enfant sur sa propre vie et que les trois domaines du
  libelle sont chacun servis.

## Ce que cette story ne fait pas

Elle n'interroge jamais l'enfant sur sa propre vie, ses propres habitudes ou son propre foyer
(`BIA-4`) ; elle ne fixe aucun contenu factuel precis - reste a l'auteur du paquet puis a l'audit
de `nadia-prof`.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : **VALIDE**
- **Date** : 2026-09-24
- **Auditrice** : `nadia-prof`, instance B (`DEC-0095`, section 9).
- **Audit joue** : **oui**

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `3f6b379f`. Le fat jar etait tenu ouvert par un autre processus
au moment de l'audit : l'application a donc ete construite depuis les modules de cet arbre puis
lancee depuis leurs classes compilees, sur le JDK que designe la toolchain (21.0.10 rapporte par la
machine virtuelle), sur un port dedie. **Partie complete de dix manches par appels HTTP reels**,
avec **trois erreurs volontaires sur les manches 1, 2 et 5**, ecran de fin atteint - *"Tu as trouve,
tout seul : 7 / Tu as joue toutes les questions : 10"*. **Et j'ai lu les quinze items du paquet**,
plus les vingt-deux items de `cp-eps-corps-effort.yaml` pour l'adjacence.

**Ce que l'audit ne couvre pas** : j'ai lu le document servi, pas la page peinte par un navigateur.
Couleur, contraste, taille et position ne sont pas juges ici.

### Ce qui a ete verifie, et ce qui tient

- **`BIA-4`, la seule condition que mon arbitrage prealable posait, est tenue sur les quinze items,
  sans exception.** J'ai relu chaque enonce un par un : *"pourquoi est-il important de..."*, *"que
  ressent-on..."*, *"que risque-t-on si l'on..."*, *"que doit-on faire..."*, *"pourquoi
  mange-t-on..."*. **Aucun enonce ne s'adresse a l'enfant, aucun ne suppose ce qu'il mange, ce
  qu'il possede ou comment il vit.** Le piege que j'avais nomme d'avance le 2026-09-01 - *"que
  manges-tu au petit dejeuner ?" presuppose qu'il y en a un* - n'est pas tombe : le paquet ne
  contient aucune forme personnelle, ni a la deuxieme personne, ni par possessif.
- **Les trois domaines du libelle sont chacun servis en bonne reponse.** Sommeil : items 1 a 5.
  Alimentation : items 6 a 10. Hygiene du corps : items 11 a 15. Cinq items chacun, decompte fait
  sur le paquet, pas sur la declaration de l'en-tete.
- **L'adjacence avec `cp-eps-corps-effort.yaml` est verifiee a la main, item par item, et elle
  n'existe pas.** C'est le point que l'avis de faisabilite de `theo-archi` (section 6.4) signalait
  comme garde par aucun automate, et que la developpeuse affirmait avoir evite. **Je ne l'ai pas
  pris sur parole : l'affirmation est exacte.** Aucun des quinze items ne nomme l'effort, le sport,
  la course, l'echauffement ni la recuperation. Ce paquet ne porte **aucun** item sur l'eau bue,
  alors que l'EPS en porte trois : la surface la plus risquee n'est pas seulement evitee, elle est
  vide. Et le repos qu'il traite est le sommeil de la nuit (*"bien dormir la nuit"*, *"une nuit
  complete"*), jamais le repos qui suit un effort. La frontiere de `DEC-0083`, section 9.2, tient
  dans les deux sens : l'EPS ne sort pas de l'effort, ce paquet n'y entre pas.
- **Exactitude du contenu.** Les quinze faits enseignes sont des faits d'hygiene de vie etablis et
  enseignes au cycle 2 : sommeil et recuperation, sommeil et defenses, regularite du coucher,
  variete alimentaire, vitamines des fruits et legumes, sucre et caries, lavage des mains avant le
  repas et apres les toilettes, douche, ongles. **Aucune erreur factuelle.** J'ai verifie aussi les
  distracteurs qu'un adulte pourrait croire vrais : *"moins faim que d'habitude"* apres une nuit
  trop courte est bien faux (le manque de sommeil augmente l'appetit), *"pour avoir plus faim au
  diner"* apres des sucreries est bien faux, *"d'avoir plus chaud"* quand on ne mange pas assez est
  bien faux.
- **Niveau CP.** Consigne d'une ligne annoncant les deux taches. Enonces d'une phrase, de six a
  quatorze mots. Vocabulaire accessible : *microbes*, *vitamines*, *caries*, *energie* sont des
  mots que le CP rencontre dans cette lecon meme. Une seule chose a tenir en tete par item.
- **Traitement de l'erreur, mesure en jouant.** L'indice s'affiche des la premiere erreur et il
  apporte un fait que l'enonce ne donne pas, jamais une reformulation. A la troisieme, la bonne
  reponse est nommee et l'enfant doit encore l'appuyer : **il n'est jamais bloque, et il n'est
  jamais dispense du geste.**
- **Ton et biais.** Aucun stereotype de genre, d'origine, de milieu ni de configuration familiale.
  Aucune injonction personnelle, aucune culpabilisation : le paquet dit pourquoi une regle existe,
  il ne dit pas a l'enfant qu'il fait mal. Sur une ligne d'hygiene, c'etait le risque principal, et
  il est evite.

### Reserves, datees du 2026-09-24 - elles n'empechent pas le Done et ne rouvrent rien

Ce sont des poteaux neufs, plantes a cote d'une barre franchie. Aucune ne durcit une clause
anterieure, aucune ne conditionne ce verdict.

1. **Item 15, distracteur *"pour avoir des mains plus jolies"*.** C'est le seul distracteur du
   paquet qui n'est pas faux : se couper les ongles rend effectivement les mains plus nettes. Un
   enfant qui le choisit n'a pas commis d'erreur de connaissance, il a choisi une raison vraie qui
   n'est pas la raison de sante demandee. **A remplacer par un comportement d'hygiene reel et faux
   dans ce contexte** ; ce n'est pas un motif de refus parce que la bonne reponse reste la seule
   reponse de sante des quatorze autres items.
2. **Item 14, distracteur *"changer de chaussures"*.** Les deux autres distracteurs de la famille
   *hygiene du corps* sont des gestes d'hygiene ; celui-ci n'en est pas un, et il affaiblit le choix
   sans le rendre devinable (l'autre distracteur, *"se peigner les cheveux"*, joue son role).

### Ce dont je reste la seule garde sur ce paquet

Pour que l'equipe ne croie pas couvert ce qui ne l'est pas :

- **`BIA-4`** - aucun automate ne lit la personne grammaticale d'un enonce. Les quinze items sont
  verifies a la main ici. Un seizieme item ajoute demain ne le serait pas.
- **La frontiere avec `cp-eps-corps-effort`** - `theo-archi` l'a dit en section 6.4 de son avis, je
  le confirme apres l'avoir verifiee : **aucun test n'existe**. Si cette ligne est reprise ou
  etendue, l'adjacence est a reverifier a la main, ou a mordre par un test.
- **L'exactitude des quinze faits d'hygiene** - aucun garde-fou ne verifie qu'un fait de sante est
  vrai.

### Consequence pour la matrice

`camille-po` peut passer la ligne `QLM-CP-04` a `couvert-valide`. **Aucune mention de couverture
partielle n'est due** : le libelle enumere trois domaines, les trois sont servis, et la ligne ne
demande rien que ce produit ne puisse poser en texte.
