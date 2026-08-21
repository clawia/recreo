# [Sprint s17] Retrospective - 2026-08-21

- **Anime par** : sofia-sm

## Ce qui a marche

- **`S17-1` a livre exactement ce que son planning promettait, avec un cycle de sonde-correction-
  remesure complet et documente.** Forme retenue par `nadia-prof` avant tout code, implementee par
  `marc-dev`, sondee sur l'increment livre, deux reserves trouvees (`S17-A`, `S17-D`) puis corrigees
  et remesurees jusqu'a fermeture ou classement explicite (non bloquante pour `S17-B`, differee pour
  `S17-C`). Aucune reserve n'est restee dans un etat ambigu a la cloture.
- **`camille-po` a sacrifie deux de ses propres criteres plutot que d'entamer une condition de
  `nadia-prof`.** `S17-2` sort du perimetre plutot que de rogner `COMP-3` ; le plancher "au moins 20
  items" de `S17-3` cede plutot que de faire reecrire quatre etiquettes de reponse a cout pedagogique
  non borne. `nadia-prof` note elle-meme la symetrie et refuse d'entamer sa propre condition (`SYN-5`)
  "pour lui rendre la politesse" - la regle qui protege ce qui compte le plus pour l'enfant a tenu des
  deux cotes du produit.
- **`theo-archi` a trouve deux defauts sur trois sans une ligne de code, par un reflexe de lecture
  plutot que par un chiffre.** Le meme geste - lire le mecanisme d'un garde-fou plutot que se fier a
  son compte inchange - a trouve la clause `ADR-0052` bornee a un jeu fictif ET, plus tot dans le
  sprint sous ma propre main, le garde-fou de rang vacueux sur `cm1-synonymes`. Aucun des deux n'a ete
  trouve par un test qui rougit.
- **`nadia-prof` a corrige six chiffres de sa propre main ce sprint, dont un qui changeait un
  verdict que toute la squad avait repris pour decider** ("l'item pilote TIENT a 2,81 px", vrai d'un
  seul tirage, faux de l'item). Chaque fois ecrit par elle en premier, sans qu'on le lui demande,
  avant que quiconque d'autre ne le remarque. Aucun automate du depot n'aurait signale un seul de ces
  six ecarts.
- **Deux agents ont refuse ce sprint de corriger le texte d'un autre a sa place, et les deux fois
  l'auteur l'a corrige lui-meme** : `nadia-prof` sur le critere "20 items" et sur la question contre-
  exemple de `SYN-1` dans la fiche de `camille-po` (signales, non reecrits) ; `camille-po` sur les
  reserves fermees de `nadia-prof` dans ses propres fiches (idem). La frontiere entre agents a tenu
  sous tension repetee, pas seulement dans le cas confortable.
- **Chaque conflit Git reel ou annonce a ete verifie au graphe avant d'etre traite** - quatre
  conflits reels sur `DEC-0039`/`DEC-index.md`, tous resolus par concatenation chronologique sans
  perte ; plusieurs conflits annonces qui ne se sont jamais materialises, verifies comme tels avant
  fusion plutot que supposes.

## Ce qui a coince

### 1. La propriete que `SYN-5` doit garder n'est nulle part dans le fichier que les tests lisent

`nadia-prof` le nomme elle-meme comme le cas le plus net du sprint sur ce dont elle reste seule
garde : des tests justes, verts, bien construits, qui lisent le YAML servi - et sept items sur seize
qu'un enfant ne peut pas jouer, parce que la propriete a garder (la derniere proposition tient au-
dessus du pli) depend du couple (item, tirage), jamais du contenu de l'item seul. Un item qui passe a
un tirage echoue a un autre selon l'ordre dans lequel ses propositions sont rendues. Aucun test qui
lit un fichier de contenu ne peut voir cet axe : il n'existe que dans le rendu, apres le tirage,
apres la mise en page. C'est distinct de la limite deja nommee en s15 (un etat interactif que l'outil
de mesure ne peut pas atteindre faute de pointeur pilote) : ici l'outil peut mesurer, il l'a fait sur
80 tirages, mais aucun test de la CI ne peut le faire a sa place parce que la propriete n'est pas une
fonction du contenu ecrit.

### 2. Trois statuts d'`ADR`/`DEC` sont restes en retard sur leur propre engagement, et il a fallu un tiers pour le remarquer chaque fois

`ADR-0042` (engagement pris a la cloture de s16, tenu seulement le 2026-08-19 apres qu'un lecteur
l'ait verifie), `ADR-0047` et `ADR-0048` (les deux `Propose` alors meme que la story qui portait leur
regle etait deja fusionnee, l'acceptation manquant simplement parce que personne ne l'avait ecrite).
Trois instances de la meme famille en deux sprints : un auteur ecrit un engagement conditionnel date
("je changerai ce statut si X") et ne revient pas verifier si X s'est realise sans qu'un tiers le lui
signale. La retro de s16 avait deja rencontre le premier cas et decide de ne pas ecrire de nouvel
amendement, en jugeant que la regle de s13 (verification physique de chaque porte de sortie) le
couvrait deja "si elle est lue largement". **Ce sprint montre que cette lecture large ne s'est pas
produite deux fois de plus** : la regle de s13 porte sur les portes de sortie d'un tableau de stories,
pas sur un engagement conditionnel ecrit ailleurs dans un `ADR`. Je reviens sur la decision de s16.

### 3. Le rapport qualite de `yanis-qa` a ete ecrit a mi-sprint et cite comme s'il couvrait la totalite

`rapport-qualite.md` est mesure sur `round-two` a `150c428`, avant `ADR-0051`, `ADR-0052` et
`ADR-0053`. Rien ne l'a signale avant que je ne verifie sa base de mesure a la review. Ce n'est pas
un defaut de `yanis-qa` - son rapport est juste pour ce qu'il couvre - mais un risque de citation :
un rapport qualite se perime silencieusement s'il n'est pas revu a la fin d'un sprint qui continue
longtemps apres son ecriture, et ce sprint a continue quarante heures de plus.

## Amendements a `squad/README.md`

**Trois amendements ecrits, un candidat decline explicitement, un cinquieme nomme sans etre
encode.**

**Ecrit (section 4 - Les ceremonies)** : **un chiffre cite dans un document qui le reprend d'un
autre document du depot est verifie contre sa source primaire avant d'etre recopie, jamais suppose
exact parce qu'il porte deja une reference.** Motif : le chiffre de couverture programme de
`DEC-0037` section 4 (23 lignes `couvert-valide` sur 74) a ete recopie sans reverification dans mon
`planning.md`, mon `RAPPORT.md` de s16 et le `planning.md` de s17, alors qu'il etait deja fautif dans
`DEC-0037` elle-meme des son premier commit - trois documents de plus ont porte une erreur qu'une
seule verification contre `MATRICE-couverture.md` (la source primaire) aurait arretee a la premiere
recopie. Distinct de la regle de s06 (un chiffre porte sa methode) : ici la methode etait bien citee
a chaque reprise, ce qui manquait est la verification contre la source, pas contre l'absence de
methode.

**Ecrit (section 9, etape 7 - Review)** : **un engagement conditionnel ecrit dans un `ADR` ou une
`DEC` ("je changerai ce statut / cette clause si X se realise") porte, au moment ou il est ecrit, qui
verifie sa realisation et quand - jamais suppose que son auteur le fera de lui-meme.** Motif : trois
instances de la meme famille en deux sprints (`ADR-0042`, `ADR-0047`, `ADR-0048`), chacune fermee
seulement parce qu'un tiers l'a remarquee. Je reviens sur la decision de la retro de s16, qui
jugeait la regle de s13 suffisante par lecture large : elle ne couvre que les portes de sortie d'un
tableau de stories, pas un engagement conditionnel ecrit ailleurs. La regle qui manquait est
distincte, pas une extension d'une regle existante.

**Ecrit (section 9, etape 4 - Porte technique, famille des amendements de `yanis-qa` s11/s12)** :
**a chaque lot qui ajoute du materiau qu'un garde-fou existant est cense couvrir (contenu, entree de
catalogue, fichier neuf), le rapport de cloture de `yanis-qa` verifie si le compte de ce garde-fou a
bouge ; s'il ne bouge pas, ce n'est pas une bonne nouvelle par defaut et appelle une reponse ecrite
disant si le garde-fou a vu le materiau neuf ou non.** Propose par `theo-archi`, qui nomme lui-meme
avoir trouve deux defauts sur trois de la meme facon ce sprint (garde-fou de rang, clause `ADR-0052`)
sans ecrire une ligne de code : "un compte qui ne bouge pas n'est pas une bonne nouvelle par defaut".
Meme famille que les amendements de s11 (critere enumere) et s12 (releve automatique cite en
preuve) : un signal syntaxique mecanique (le compte ne bouge pas) qui declenche une lecture, jamais
une preuve d'exhaustivite en lui-meme. `theo-archi` a examine et decline explicitement d'encoder un
garde-fou automatique unique pour la famille sous-jacente (rang, perimetre, population) : le manque
n'est pas au meme endroit dans les trois cas, un seul des trois se voyait dans l'annotation de test -
un automate unique aurait soit manque les deux autres, soit ete trop large pour rester precis. Je ne
retranche pas cette partie de sa decision, je ne code que le reflexe de revue qu'il route
explicitement a moi.

**Decline** : aucun meta-garde-fou automatique pour la famille "objet non couvert par un garde-fou
qui le declare couvrir" (rang, perimetre de derivation, population de test). Decision de
`theo-archi`, reprise telle quelle : les trois manques sont de nature differente et un automate
unique serait soit incomplet, soit trop large.

**Nomme sans etre encode comme amendement** : la pratique d'auto-correction de `nadia-prof` (six
chiffres corriges de sa propre main ce sprint, dont un qui changeait un verdict deja repris par toute
la squad, chaque fois ecrite par elle en premier sans qu'on le lui demande). Ce n'est pas un defaut a
corriger et ce n'est pas non plus une regle de process : aucun automate ne peut verifier qu'un agent
s'auto-corrige, et une regle qui l'exigerait ne serait pas testable. Je le nomme dans "Ce qui a
marche" ci-dessus plutot que de fabriquer un amendement sans prise sur le process reel.

## Decisions prises

1. **Trois amendements sont ecrits dans `squad/README.md`** : la verification d'un chiffre repris
   contre sa source primaire (section 4), l'engagement conditionnel d'`ADR`/`DEC` qui nomme qui
   verifie sa realisation (section 9, etape 7), et le compte de garde-fou qui ne bouge pas malgre du
   materiau neuf (section 9, etape 4). Voir le detail et le motif de chacun ci-dessus.
2. **Je reviens sur une decision de la retro de s16** (l'engagement non tenu d'`ADR-0042` ne
   justifiait pas un amendement neuf) : deux instances de plus de la meme famille depuis montrent que
   la lecture large de la regle de s13 ne s'est pas produite, et la regle qui manquait est ecrite
   maintenant.
3. **Un candidat est decline explicitement** : aucun meta-garde-fou automatique pour la famille
   "objet non couvert" (rang, perimetre, population), decision de `theo-archi` reprise telle quelle,
   motif nomme.
4. **Un cinquieme candidat (la pratique d'auto-correction de `nadia-prof`) est nomme, pas encode** :
   ce n'est pas une regle de process testable, c'est une pratique a reconnaitre.
5. **La portee partielle du rapport qualite de `yanis-qa` (ecrit a mi-sprint) est nommee dans
   `review.md` et `RAPPORT.md`**, pas laissee filer comme si elle couvrait la totalite du sprint -
   pas un amendement de process, une correction de citation a cette cloture.
6. **Aucun worktree n'est supprime dans ce geste** ; aucun push n'est effectue.
7. **Ecrit directement sur `round-two`**, comme aux clotures precedentes, pas en worktree dedie.
