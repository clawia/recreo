# Retro - s42 - fusion de deux sprints projetes (histoire-geographie CM1, questionner le monde
CP, arts plastiques CP, EMC CM1, mathematiques CP), deux instances de `nadia-prof`

- **Date** : 2026-09-24.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Nature du sprint** : entierement pilote par agents, sans ceremonie de daily - conforme a
  `squad/README.md`. Quinze commits (`9fed12bc..7389e9e1`), aucun commit de fusion au sens Git :
  chaque developpeur a commite directement son lot sur `round-two`.

---

## 1. Ce qui a marche

**La mesure de pli d'`ines-design` a evite d'ecrire un paquet inutilisable - exactement son role.**
`S42-FR-LECTURE-SILENCIEUSE` etait une des six lignes chiffrees par `DEC-0097`, sur une reserve
pedagogique ouverte depuis s25 et jamais levee. Le critere d'acceptation de la story posait la
mesure comme un prealable explicite a l'ecriture : *"le paquet n'est ecrit qu'apres cette mesure ;
si elle montre que le texte ne tient pas, la ligne ne s'ouvre pas a ce sprint."* `ines-design` a
mesure au plafond haut exact du critere pedagogique (`LIS-2`/`LIS-3`), sur le rendu reel de
l'application et non une maquette, et a rendu un verdict net et chiffre - 357 px de debordement,
deux propositions sur trois entierement hors champ - sans se prononcer sur la consequence, qu'elle a
routee telle que son mandat le prevoit. **Aucun contenu n'a ete ecrit pour cette ligne, aucun
developpeur n'y a passe de temps, et aucune instance de `nadia-prof` n'a eu a auditer un paquet qui
n'aurait de toute facon pas pu etre joue sans defiler.** C'est le cas d'usage exact pour lequel ce
role existe dans la squad : verifier un gabarit avant qu'un cout de contenu ne soit engage dessus,
plutot qu'apres. Le fait que le verdict soit negatif n'est pas un echec du sprint - c'est la preuve
que la verification a servi a quelque chose.

**La correction iterative sur `MAT-CP-10` a tenu, comme sur `EMC-CP-08` a s41.** Un refus a deux
motifs chacun suffisant, une correction complete du paquet par `lea-dev` reprenant les deux motifs
mot pour mot depuis la fiche de refus, un second passage qui verifie que les deux items nommes au
refus sont bien reapparus dans la partie rejouee. `nadia-prof` nomme elle-meme, dans son verdict du
second passage, ce qu'elle avait annonce a tort au refus (elle attendait que le troisieme constat
tombe avec le premier motif ; il ne tombe qu'a moitie) - la meme discipline de ne pas se relire
complaisamment qu'elle avait deja tenue sur `EMC-CP-08`.

**Le decouplage effectif entre les deux terrains a tenu sans qu'aucune collision ne se produise.**
Quinze paires de lignes verifiees par `DEC-0098` avant l'ouverture du sprint contre le seul critere
de frontiere que `nadia-prof` a ecrit (un objet du monde reel partage entre deux lignes de matieres
differentes, affectees a deux instances differentes) : aucune clause de frontiere due, verifie apres
coup sur les cinq fiches - aucune ne signale de contradiction avec le terrain voisin.

## 2. Ce qui a coince

**`nadia-prof` a cause le meme rouge de la meme facon, pour la deuxieme fois en deux sprints
consecutifs.** A s40 deja, elle avait ecrit des identifiants de condition dans une fiche sans les
allouer dans `REGISTRE-identifiants.md` dans le meme commit, causant un rouge sur `PAR-5`. Ce
sprint-ci, exactement le meme geste : sept identifiants (`HG-R23` a `HG-R25`, `MOB-R1` a `MOB-R4`)
ecrits dans son commit de verdict (`1d5c00de`) sans allocation, trouves par `lea-dev` en relancant
`mvn -B clean verify` apres sa propre correction. Elle le nomme elle-meme dans sa note de reparation,
sans en attenuer la portee : *"c'est la deuxieme fois que je cause ce rouge de la meme facon [...]
j'ecris mes reserves dans la fiche et j'appelle le verdict fini avant d'avoir ouvert le registre."*
C'est une bonne pratique de reconnaitre la recidive dans le depot plutot que de la taire ; ce n'est
pas une bonne pratique de la laisser se reproduire une troisieme fois sans rien changer au geste
lui-meme. Une intention ecrite deux fois n'a pas suffi.

**Un second fait, trouve par moi a la cloture, touche exactement le meme dispositif sans avoir cause
de rouge.** Les sept identifiants de reserve de l'instance B (`R-EMC-CM1-05-1` a `-4`,
`R-MAT-CP-10-1`, `-1 bis`, `-2`) sont ecrits sous une forme a cinq segments que ni l'une ni l'autre
des deux expressions regulieres du garde-fou `PAR-5` ne reconnait - verifie par moi par lecture des
deux motifs et par grep direct, absence confirmee des deux cotes. Ces sept identifiants n'ont donc
jamais pu declencher le rouge que l'instance A a declenche deux fois : **ils sont silencieusement
hors de la garantie d'unicite que `PAR-5` existe pour tenir**, sans qu'aucun test ne le signale.
Rien ne prouve qu'une collision se soit deja produite - la table de la section 4 du registre ne
contient de toute facon aucune de ces sept formes a comparer - mais le risque que `PAR-5` a ete ecrit
pour fermer (deux instances qui allouent le meme numero sans se lire) reste entierement ouvert sur
cette forme precise. C'est un angle mort de couverture du garde-fou, pas un defaut de `nadia-prof` :
la forme n'est fautive nulle part dans `ADR-0085`, elle est simplement en dehors de ce que le
garde-fou sait lire.

**Le point commun des deux constats ci-dessus n'est pas une personne, c'est une convention non
tenue.** L'instance A nomme ses reserves sur la forme a deux segments deja allouee ailleurs (`HG-R23`,
sur le patron de `DVS-CP-R1`) ; l'instance B en invente une autre, plus lisible en apparence
(`R-EMC-CM1-05-1` porte la ligne de referentiel dans son propre nom) mais invisible au garde-fou. Les
deux instances de `nadia-prof`, tournant en parallele sans se lire, n'ont eu aucune occasion de
converger sur une forme commune avant d'ecrire.

## 3. Actions pour la suite

**Une checklist explicite avant tout commit `[NAD]` de verdict, plutot qu'une bonne intention
reformulee une troisieme fois.** Ecrire, en tete de chaque verdict de `nadia-prof`, la question
fermee *"chaque identifiant neuf cite dans ce commit a-t-il sa ligne dans
`REGISTRE-identifiants.md`, dans le meme commit ?"*, au meme titre que les autres controles qu'elle
mene deja avant de commiter (jobs de CI touches par le diff). Ce n'est pas a moi de l'imposer dans
`squad/README.md` sans que `nadia-prof` ou `theo-archi` ne se prononcent sur la forme - je le
signale ici comme le geste concret qui manque, a distinguer de l'intention deja ecrite deux fois sans
effet.

**La forme des identifiants de reserve merite d'etre unifiee avant qu'une troisieme instance
parallele n'augmente le risque de collision silencieuse.** Je route ce point a `theo-archi` (le
garde-fou doit-il apprendre a lire la forme a cinq segments, ou doit-elle disparaitre) et a
`nadia-prof` (la convention a deux ou trois segments deja allouee ailleurs suffit-elle a ce qu'elle
veut exprimer). Je ne tranche pas moi-meme entre etendre le garde-fou et changer la convention -
c'est un arbitrage d'architecture et de nommage, pas de process.

**Je ne propose pas d'amendement a `squad/README.md`.** Les deux points ci-dessus sont des defauts
de geste et de portee de garde-fou, pas des lacunes de la ceremonie de cloture elle-meme : je les
route, je ne les absorbe pas dans le process que je tiens.

## Decisions prises

**Aucune decision de sprint neuve n'est prise dans cette retro**, au sens `DEC`/`ADR`. Elle constate
ce qui a fonctionne et ce qui a coince dans l'orchestration de s42 et ne modifie pas
`squad/README.md` - la decision du sprint (`DEC-0098`) est deja enregistree dans `review.md`,
section "Decisions prises", et n'est pas reprise ici une seconde fois.
