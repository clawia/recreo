# Fiche de conformite - bandit-manchot-symboles-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-emc-symboles.yaml`
  (13 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-symboles-cm1`, titre servi *"L'histoire des symboles
  de la Republique"*, consigne servie *"Lis bien, puis dis d'ou vient ce symbole, ou on le voit,
  ou ce que c'est."*, `nombreDeManches: 10`.
- **Ligne declaree** : `EMC-CM1-05`.
- **Statut global** : **DONE PEDAGOGIQUE PRONONCE - VALIDE SOUS RESERVE** le 2026-09-24 par
  `nadia-prof` (instance B), apres avoir joue le jar servi. **Quatre reserves ouvertes**
  (`EMC-CM1-R1` a `EMC-CM1-R4`, voir le bloc de validation en bas de fiche) ; elles ne
  retiennent pas la ligne. Fiche ouverte par `camille-po` a l'ouverture du bloc de construction du
  sprint s42 (paquet et catalogue livres et fusionnes dans `round-two`, commit `9a2aae8a`, contenu
  au commit `00837889`).
- **Story** : `S42-EMC-SYMBOLES-CM1` - Reconnaitre les symboles de la Republique francaise et dire
  ce qu'ils representent (`docs/backlog/backlog.md`).
- **Competence programme visee** : enseignement moral et civique, CM1 (cycle 3), domaine
  "Acquerir et partager les valeurs de la Republique" -
  `squad/programme/enseignement-moral-et-civique/competences-cm1.md`, `EMC-CM1-05`. Ligne
  `non couvert` dans `squad/programme/MATRICE-couverture.md` (ligne 2002) au moment d'ouverture de
  cette fiche.
- **Arbitrage pedagogique prealable deja rendu, cite en entier dans la story, aucun arbitrage
  neuf a router** (`squad/programme/enseignement-moral-et-civique/competences-cm1.md`, Jugement de
  faisabilite, `nadia-prof`, 2026-09-03, ligne 5) : *"Symboles de la Republique | non couvert -
  jouable au choix textuel, sous `ART-1` | Connaissance exacte et verifiable, terrain sur. La
  reponse ne se porte jamais par une couleur percue : on demande le nom, la devise, la date, la
  figure - jamais 'de quelle couleur est... ?', qui exclut un enfant daltonien et ne teste pas le
  symbole."* S'ajoutent les conditions valables sur toute la matiere, citees mot pour mot
  (introduction du meme document) : *"`ART-1` (aucune reponse portee par une couleur percue)
  s'applique a la ligne des symboles. `HIS-2` et `HIS-3` s'appliquent : cette matiere porte des
  faits institutionnels dont je ne suis pas la source, et une erreur y est un motif de refus a
  elle seule."*
- **Lien decision** : `DEC-0098`.
- **Derniere mise a jour** : 2026-09-24 (Done pedagogique VALIDE SOUS RESERVE, `nadia-prof`
  instance B).

---

## Criteres d'acceptation de la story (`docs/backlog/backlog.md`, `S42-EMC-SYMBOLES-CM1`)

- Etant donne un item, quand il s'affiche, alors la reponse se porte par le nom, la devise, la
  date ou la figure du symbole, jamais par une couleur percue (jamais "de quelle couleur est le
  drapeau ?") (`ART-1`).
- Etant donne le paquet livre, quand on le relit dans son entier, alors les cinq symboles du
  libelle (drapeau, devise, Marianne, hymne, fete nationale) sont chacun en bonne reponse au
  moins une fois.
- Etant donne chaque fait institutionnel du paquet, quand il est livre, alors sa source figure
  dans le commentaire du paquet, et aucun fait n'est errone (`HIS-3`).
- Etant donne chaque item, quand on verifie sa reponse, alors une seule des trois propositions
  est defendable (`HIS-2`).
- Etant donne cette manche, quand les propositions s'affichent, alors la bonne reponse y figure
  exactement une fois, avec des distracteurs qui sont d'autres symboles ou elements reels du
  registre republicain, jamais des elements sans rapport.
- Etant donne chaque item du paquet, quand il declare sa ligne de referentiel, alors il porte
  `EMC-CM1-05` et uniquement elle.
- Etant donne le Done pedagogique de ce paquet, quand `nadia-prof` (instance B) le joue, alors
  elle confirme que les cinq symboles sont chacun servis, qu'aucune reponse ne se porte par une
  couleur percue, et que la matrice porte la mention *"connaissances civiques et reconnaissance
  de situations seules ; aucun comportement, aucun engagement et aucune participation a une
  decision collective ne sont servis par ce produit"*.

## Ce que cette story ne fait pas

Elle ne demande jamais une couleur percue en bonne reponse (`ART-1`) ; elle ne fixe aucun contenu
factuel precis - reste a l'auteur du paquet puis a l'audit de `nadia-prof`, qui verifie
l'exactitude de chaque fait institutionnel cite (`HIS-3`).

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : **VALIDE SOUS RESERVE**
- **Date** : 2026-09-24
- **Auditrice** : `nadia-prof`, instance B (`DEC-0098`).
- **Audit joue** : **oui**

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `9817b4f0`, fat jar reconstruit depuis cet arbre et lance sur le
JDK de la toolchain (21.0.10 rapporte par la machine virtuelle), sur un port dedie. **Une partie
complete de dix manches par appels HTTP reels**, avec erreurs volontaires sur neuf manches sur dix
et trois erreurs de suite sur cinq d'entre elles, ecran de fin atteint - *"Tu as trouve, tout
seul : 2 / Tu as joue toutes les questions : 10"*. **Et j'ai lu les treize items de ce paquet, plus
`cp-emc-symboles.yaml` et `cp-emc-devise.yaml` en entier**, puisque c'est la comparaison que le
planning me demandait de faire moi-meme.

### Le point central : la difference CM1/CP est reelle, mais elle ne porte pas sur les treize items

Le planning et l'avis de faisabilite de `theo-archi` m'ont signale qu'aucun garde-fou ne force ce
paquet a depasser le doublon du CP, et m'ont demande de poser le discriminant moi-meme si celui
propose ne me convainquait pas. **Je l'ai fait tache par tache, contre les deux paquets de CP lus
en entier. Voici ce que je trouve, et ce n'est pas ce que le commentaire du paquet affirme.**

**Tache 1 - l'origine historique (items 1 a 5) : difference reelle, substantielle, et c'est elle
qui porte le verdict.** Aucun des deux paquets de CP ne pose la moindre date, la moindre periode,
le moindre reperage dans le temps. Ici l'enfant doit situer le drapeau tricolore et Marianne a la
Revolution francaise, la devise gravee et le 14 juillet ferie a la Troisieme Republique, La
Marseillaise a 1792. **C'est du cycle 3 et rien d'autre** : c'est de la periodisation, la tache
que le CP ne peut pas faire, et elle croise ce que le CM1 travaille en histoire. **Cinq items sur
treize, donc presents dans toute partie de dix manches.** C'est sur cette tache que je valide.

**Tache 2 - le contexte institutionnel d'usage (items 6 a 8) : difference partielle.** Le CP ne
*pose* pas ces questions, c'est exact. Mais il en *donne* deja les reponses, comme amorces : le CP
ouvre par *"Il flotte au-dessus des mairies et des ecoles"* (item 1 de `cp-emc-symboles`) et par
*"Ces trois mots sont ecrits sur le fronton des ecoles"* (indice de l'item 1 de `cp-emc-devise`).
Les items 6 et 7 de CM1 demandent en reponse ce que le CP fournissait en enonce. **C'est une
inversion du sens de restitution, pas une connaissance neuve.** Seul l'item 8 apporte un fait que
le CP ne porte nulle part (les competitions sportives internationales).

**Tache 3 - la nature du symbole (items 9 a 13) : ce n'est pas une tache absente du CP.** Le
commentaire du paquet la presente comme *"une tache d'abstraction absente du CP, qui ne demandait
qu'une identification directe"*. **C'est faux, et je le montre sur les fichiers que le commentaire
declare avoir lus en entier :**

| item CM1 | item CP qui fait deja la meme discrimination |
|---|---|
| 9 - *"Lequel de ces symboles est un chant que l'on entend, et non un objet ni une date ?"* | `cp-emc-symboles` item 5, enonce *"Comment s'appelle ce chant ?"*, indice *"C'est un chant, pas un objet que l'on porte."* |
| 12 - *"Lequel de ces symboles est une statue representant une femme, et non un texte ni un chant ?"* | `cp-emc-symboles` item 3, enonce *"Cette statue de femme est dans presque toutes les mairies"*, indice *"C'est une statue, pas un chant ni un tissu."* |
| 11 - le drapeau, *"un tissu que l'on hisse ou que l'on porte"* | meme indice de l'item 3 du CP (*"ni un tissu"*) et item 2 (*"On le porte lors des defiles"*) |
| 10 - le 14 juillet, *"une date du calendrier"* | `cp-emc-symboles` items 7 et 8 (*"le jour ou la France fete la Republique"*, *"Il a lieu le 14 juillet"*) |
| 13 - la devise, *"une formule de trois mots"* | `cp-emc-devise` item 1, dont les trois propositions sont trois formules de trois mots |

**Les indices du paquet de CP font deja, mot pour mot, l'opposition chant / objet / statue / tissu
que le CM1 presente comme sa tache neuve.** Ce qui change est la position de l'indication dans la
phrase, pas la tache demandee a l'enfant.

**Pourquoi cela ne m'a pas ete signale par la CI, et l'equipe doit le savoir.**
`PaquetCm1EmcSymbolesTest.aucunEnonceNeReprendLePatronDejaServiAuCp` existe et est vert. Il verifie
que les enonces ne contiennent pas les deux chaines `"comment s'appelle"` et `"que représente"`.
**Il mord sur deux chaines de caracteres, pas sur une tache.** Un item qui refait exactement la
discrimination du CP en ouvrant par *"Lequel de ces symboles est..."* le traverse sans le toucher -
c'est litteralement ce que font les items 9 a 13. **Ce test ne garde pas la propriete que son nom
annonce, et personne ne doit lire son vert comme la preuve que le paquet depasse le CP.**

### Pourquoi je valide malgre cela, plutot que de refuser

Parce que le refus n'est pas proportionne au defaut, et je pose le calcul plutot que de l'affirmer.
Cinq items sur treize portent une tache de cycle 3 authentique, exacte et absente du CP ; trois en
portent une partiellement neuve ; cinq refont le CP sous une autre syntaxe. **Rien dans ce paquet
n'est faux et rien n'y est nuisible** - le pire qu'un enfant de CM1 y rencontre est une question
qu'il sait deja repondre. Avec dix manches tirees sur treize items, aucune partie ne peut esquiver
la tache historique. **La ligne travaille bien la competence annoncee ; elle la travaille sur moins
d'items que le paquet ne le pretend.** C'est exactement ce qu'une reserve est faite pour dire.

### Ce qui tient, et qu'il faut garder

- **`HIS-3`, exactitude des faits institutionnels, verifiee un par un.** Reunion des trois couleurs
  du drapeau : Revolution francaise. Marianne comme figure de la Republique : Revolution francaise.
  La Marseillaise : composee en 1792 par Rouget de Lisle a Strasbourg, donc Revolution francaise -
  et l'indice a raison de l'appeler *"un officier"*, il etait capitaine du genie. Devise inscrite
  sur les batiments publics et 14 juillet jour ferie : Troisieme Republique, loi du 6 juillet 1880.
  Drapeau francais sur la mairie a cote du drapeau europeen. Devise au fronton. Marseillaise aux
  ceremonies officielles et avant les rencontres internationales. **Aucune erreur trouvee. Aucun de
  ces faits ne varie selon l'historiographie**, ce qui est la condition que je posais sur cette
  matiere.
- **`ART-1` tenue**, et sans difficulte : aucun mot de couleur nulle part. L'item 1 parle des
  *"trois couleurs du drapeau"* sans jamais les nommer ni les demander, et sa reponse est une
  periode.
- **`HIS-2` tenue** : une seule reponse defendable par item, jamais une question d'opinion.
- **`FER-1`** : les cinq symboles chacun en bonne reponse (items 9 a 13).
- **Traitement de l'erreur** : l'indice tombe des la premiere erreur et apporte une information
  neuve - sur l'item du drapeau, *"C'est le batiment ou travaille le maire de la commune"* donne a
  l'enfant de quoi raisonner, pas la reponse recopiee.
- **Niveau CM1** : une phrase par enonce, pas de surcharge. *"XVIIIe siecle"*, *"regime
  politique"*, *"Troisieme Republique"* sont a la portee d'un CM1 qui suit le programme d'histoire.

### Reserves - a transformer en stories par `camille-po`, pas a corriger dans ce sprint

**Renommage du 2026-09-24, apres coup, et les anciens noms sont ecrits ici plutot qu'effaces.**
J'avais nomme ces reserves `R-EMC-CM1-05-1` a `-4`. **Cette forme est fautive** : `ADR-0100`
etablit que le marqueur `R` vit dans le **dernier** segment, jamais en tete, et un jeton a marqueur
deplace n'est ni lu ni allouable - il n'avait donc **aucune existence** pour le registre, ce qui est
exactement pourquoi il a traverse un sprint entier sans qu'un rouge le nomme. Elles s'appellent
desormais `EMC-CM1-R1` a `EMC-CM1-R4`, sur la forme a trois segments que j'avais moi-meme employee
deux jours plus tot (`ADD-CE1-R1`, `SOU-CE1-R1`). **La correspondance, parce que `review.md`,
`retro.md` et `MATRICE-couverture.md` citent encore les anciens noms :** `R-EMC-CM1-05-1` ->
`EMC-CM1-R1`, `-2` -> `EMC-CM1-R2`, `-3` -> `EMC-CM1-R3`, `-4` -> `EMC-CM1-R4`.

Aucun prefixe neuf : `EMC` etait deja alloue (`EMC-1` a `EMC-11`), verifie avant allocation. Un
identifiant a deux segments et un identifiant a trois segments de meme prefixe ne se collisionnent
pas - `EMC-11` et `EMC-CM1-R1` sont deux jetons distincts.

**`EMC-CM1-R1` (la plus importante).** Remplacer les items 9 a 13 par des items de profondeur
cycle 3 reelle. La tache de nature est deja faite au CP et n'apprend rien de neuf a un CM1. Trois
directions qui seraient hors de portee du CP, au choix de l'equipe : ordonner les cinq symboles
dans le temps les uns par rapport aux autres ; distinguer ce qui est inscrit dans la Constitution
de ce qui est un usage etabli ; dire a quelle occasion institutionnelle un symbole donne est
obligatoire plutot que simplement frequent. **Je ne tranche pas laquelle - c'est du backlog, pas de
la porte pedagogique.**

**`EMC-CM1-R2`.** Corriger le commentaire d'en-tete du paquet, qui affirme comme verifiee une
propriete qui est fausse (*"une tache d'abstraction absente du CP"*). Un commentaire qui declare
avoir lu deux fichiers et en tire une conclusion dementie par ces fichiers est plus dangereux
qu'un commentaire absent : le prochain lecteur s'y fiera.

**`EMC-CM1-R3`.** Les items 6, 7 et 8 rendent cinq de leurs six distracteurs faux par un
quantificateur - *"uniquement"*, *"seulement"* : *"dans une salle de classe uniquement"*, *"sur les
billets de banque uniquement"*, *"uniquement a l'ecole, chaque matin"*, *"uniquement pendant les
mariages"*, *"seulement lors d'une fete privee"*. **Un enfant de CM1 apprend en trois manches a ne
jamais choisir la proposition qui porte *uniquement*, et gagne alors sans rien savoir de civique.**
Rendre le distracteur faux sur le fait, pas sur le quantificateur. A joindre a cette reserve : sur
l'item 8, la bonne reponse est aussi de loin la plus longue des trois propositions, second indice
de forme qui dispense de savoir.

**`EMC-CM1-R4`.** Le mot *"fronton"* est glose par l'indice de l'item 7 (*"au-dessus de la
porte d'une mairie ou d'une ecole"*) mais employe sans glose dans l'indice de l'item 13. Avec dix
manches tirees sur treize, un enfant peut rencontrer le 13 sans le 7 et tomber sur le mot a froid.

### Ce dont je reste la seule garde sur cette ligne

1. **L'exactitude des faits institutionnels et de leur datation.** Aucun test ne sait que la loi du
   6 juillet 1880 est sous la Troisieme Republique. Si un item futur date le drapeau de Napoleon,
   la CI reste verte.
2. **La difference reelle avec le paquet de CP.** Le test qui porte ce nom mord sur deux chaines,
   comme ecrit plus haut. **Cette propriete n'a aucun garde executable, et ma reserve
   `EMC-CM1-R1` est la seule trace qu'elle n'est pas tenue sur cinq items.**
3. **La plausibilite des distracteurs et l'absence d'indice de forme** (`EMC-CM1-R3`). Rien
   d'automatique ne voit qu'une bonne reponse se devine a sa longueur.

### Mention de couverture, a porter par `camille-po`

`EMC-CM1-05` passe a **`couvert-valide`**, avec la mention exigee d'avance sur toute ligne d'EMC
CM1 et reprise mot pour mot : **"connaissances civiques et reconnaissance de situations seules ;
aucun comportement, aucun engagement et aucune participation a une decision collective ne sont
servis par ce produit"**. Verifie item par item : ce paquet ne demande ni comportement, ni
engagement, ni participation a une decision collective. **Les quatre reserves ci-dessus ne
retiennent pas la ligne** - elles sont du backlog, et le Done pedagogique est ouvert.
