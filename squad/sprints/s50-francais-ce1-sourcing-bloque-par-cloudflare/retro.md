# Retro - s50 - deux lignes neuves de francais CE1, trois corrections differees, blocage puis
levee de la dette technique `T6` sur l'ensemble de la porte du sprint, premier paquet d'arts
plastiques CP

- **Date** : 2026-10-04.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Nature du sprint** : deux instances de `nadia-prof` en parallele (`DEC-0109` section 3, `PAR-2`
  tenue - francais/mathematiques CE1 contre arts plastiques CP), ouvert par une seule `DEC`
  (`DEC-0108`) puis **reouvert** en cours de route par une seconde (`DEC-0109`), apres que la
  mesure de `yanis-qa` a revele que la dette `T6` ne bloquait pas que le chantier qui la portait.

---

## 1. Ce qui a marche - deux defauts trouves par leurs propres auteurs, avant qu'ils ne coutent
## plus cher

**`theo-archi` trouve son propre defaut de poids en rendant son propre avis de faisabilite, pas en
attendant qu'un autre agent le mesure.** Son commit `56b6b77c` (trois oeuvres recuperees a la main)
aurait pu rester invisible jusqu'a la prochaine mesure de `yanis-qa` ou jusqu'a la fusion : au lieu
de cela, c'est lui-meme qui relance le garde-fou de poids en preparant l'avis de faisabilite sur
`S50-ART`, trouve neuf echecs au lieu des deux attendus, et ecrit explicitement *"la cause est mon
commit"* avant de le corriger dans le meme mouvement (`df135abd`). Le cout reste celui d'un
redimensionnement d'images, pas celui d'une regression decouverte en aval par un autre agent, ni
d'une review qui aurait du le constater a sa place.

**`nadia-prof` trouve sa propre erreur de lecture sur `oeuvre-quatre-saisons`, et le dit sans
l'habiller.** Au premier passage, elle avait ecrit que le visage du tableau est *"fait de
fruits"* ; au second passage, en rejouant l'item reecrit sur la base de ce constat, elle regarde de
nouveau le tableau et ecrit : *"c'est faux, et l'erreur est la mienne [...] j'ai confondu ce qui se
voit dans l'image avec ce dont le visage est fait"*. **La methode qui a trouve cette erreur est
celle qu'elle applique a tout contenu** : elle ne relit pas sa propre fiche, elle regarde de
nouveau l'image, propositions masquees, avant de juger un item - meme au second passage sur une
oeuvre qu'elle croit deja connaitre. C'est cette discipline-la, pas une relecture croisee par un
autre agent, qui a trouve l'ecart. Elle signale ensuite, a deux reprises (second et troisieme
passage), que `DEC-0109` citait encore sa phrase fautive, jusqu'a ce que `camille-po` la corrige
dans le commit de cloture.

**Ce que les deux episodes partagent** : dans les deux cas, l'agent qui a produit le defaut est
aussi celui qui le trouve, en rejouant son propre travail contre la realite (le fichier, le
tableau) plutot qu'en relisant son propre texte. Aucun des deux defauts n'a traverse une CI verte
sans etre vu : le premier a ete trouve avant tout commit de contenu qui s'appuierait dessus, le
second a ete trouve avant que le Done pedagogique ne soit prononce dessus.

## 2. Ce qui a coince - le chiffrage initial de `T6` sous-estimait le cout reel a deux reprises,
## sur deux clauses distinctes du meme garde-fou et de la meme ligne

**Premiere sous-estimation, technique.** `DEC-0109` (ouverture, section 1) engageait `T6` sur
l'hypothese que les identifiants d'oeuvre deja au registre suffiraient a fermer la dette des que le
paquet en declarerait un. `theo-archi` mesure, en rendant son avis de faisabilite sur `S50-ART`,
que le garde-fou *"aucune ne dort"* boucle sur **chaque** reproduction servie et pas sur
l'existence d'au moins une declaration : avec 28 oeuvres au registre et 11 seulement dans le vivier
du paquet, les 17 autres resteraient rouges apres l'ecriture complete du contenu. `camille-po`
corrige en section 9 de `DEC-0109` et retient l'option la moins couteuse (retrait des 17,
restaurables a l'octet depuis l'historique Git).

**Seconde sous-estimation, pedagogique.** La meme `DEC-0109` (section 1) engageait `ART-CP-09` et
`ART-CP-10` dans le meme lot, en lisant le code existant (`Question`, propositions en texte) comme
suffisant pour les deux lignes. L'arbitrage prealable de `nadia-prof` revele que *"rapprocher deux
oeuvres"* exige de les montrer **en meme temps**, ce que la mecanique engagee ne fait pas :
`ART-CP-10` sort du perimetre, reduit a `ART-CP-09` seule (section 10.1).

**Pourquoi je le nomme comme un seul motif, pas deux incidents separes.** Dans les deux cas,
l'estimation s'est arretee a la lecture la plus naturelle d'un texte - le libelle d'un garde-fou,
le libelle d'une competence - sans la confronter a sa source d'autorite : le code qui execute le
garde-fou, l'autorite pedagogique qui a ecrit la competence. Les deux ecarts ont ete trouves avant
de coder le contenu fautif, pas apres : le cout reste celui d'un retrait de fichiers et d'une
reduction de perimetre, pas celui d'une reecriture de contenu deja juge. **Un amendement a
`squad/README.md` (section 9) nomme desormais cette regle** : chiffrer un chantier contre un
garde-fou ou une competence relit ce garde-fou dans le code et cette competence aupres de son
autorite, pas dans leur paraphrase la plus visible.

## 3. Ce qui a coince - un commit attribue au mauvais agent, sans consequence de contenu mais un
## ecart de process reel

**Le fait.** L'instruction recue hors des neuf agents (planning, section 6 ; `DEC-0108` section 2)
demandait de recuperer a la main les trois oeuvres bloquees par Cloudflare depuis s49. Ce geste a
ete execute par la session principale, avec l'utilisatrice, hors du mandat d'un sous-agent - mais
le commit qui en resulte (`56b6b77c`) porte le prefixe `[THEO]`, comme s'il avait ete ecrit par
l'agent `theo-archi`.

**Pourquoi ce n'est pas un defaut de contenu.** Les trois fichiers sont verifies `CC0-1.0` contre
l'API `api.artic.edu`, exactement comme l'exige `ADR-0082` ; le defaut de poids qui en decoule
(section 1 ci-dessus) est reel et corrige, mais il n'a rien a voir avec qui a tenu le clavier. La
CI ne juge pas l'auteur d'un commit, elle juge son contenu : aucun job n'aurait rendu un verdict
different si le prefixe avait ete exact.

**Pourquoi je le nomme quand meme.** Le prefixe de commit est la seule trace, dans ce depot, de qui
a produit un geste donne (`CLAUDE.md`, "Commits prefixes par agent"). Un prefixe errone casse cette
tracabilite-la precisement, et il l'a deja fait une fois dans ce sprint : le rapport de tokens de ce
sprint (`RAPPORT-tokens.md`) mesure les tours de transcription par agent, pas par commit, donc cet
ecart ne fausse aucun chiffre de consommation - mais un futur rapprochement entre un commit et une
transcription d'agent, pour une raison que ce sprint n'a pas eue, trouverait une trace fausse.

**Ce que je ne fais pas.** Je ne corrige pas l'historique Git : reecrire un message de commit deja
pousse est une operation destructive, hors de mon mandat sans confirmation explicite de
l'utilisatrice, pour un ecart qui n'a coute aucune consequence de contenu. Je le nomme ici, dans le
tableau des obstacles de `review.md` (obstacle 7), pour que la prochaine fois qu'une instruction
recue hors des neuf agents se traduit en commit, le prefixe qui le porte soit celui de la session
qui l'a ecrit - `[CAM]`, `[SOF]` ou tout autre identifiant qui dit la verite plutot que celui de
l'agent dont le travail ressemble le plus au geste.

## 4. Actions pour la suite

**Je route, je ne tranche pas.**

- `ART-CP-10` reste `non couvert` et attend une mecanique de vue simultanee (deux oeuvres montrees
  ensemble) : candidate a instruire par `theo-archi` (`ADR` prealable) et `camille-po` (perimetre),
  pas a engager sans cela.
- La story de `HOM-CE1-R1` (prenoms d'origines variees sur `FRA-CE1-16`), deja ecrite au backlog
  dans le commit de cloture, reste a affecter a un developpeur.
- Le prefixe du commit `56b6b77c` reste tel quel (section 3) : a signaler a l'utilisatrice si une
  prochaine instruction hors agent se traduit de nouveau en commit, pour que le prefixe exact soit
  choisi des l'ecriture plutot que constate a la cloture.

## Decisions prises

**Aucune `DEC` ni `ADR` n'est creee ou amendee par cette retro.** Un amendement est apporte a
`squad/README.md` (section 9, voir section 2 ci-dessus) : chiffrer un chantier contre un garde-fou
ou une competence verifie chaque clause opposable aupres de sa source (code ou autorite
pedagogique), pas seulement la lecture la plus visible de son libelle.

## Amendement du 2026-10-05 20:55 (`sofia-sm`, ouverture de s51) : une occurrence de la forme (c), non consignee a l'epoque

**Source** : `squad/conformite/ARBITRAGE-PREALABLE-s51-borne-a-trois-instances.md` (`nadia-prof`, forme 1),
section 2.3, tableau horodate, et section 2.4 (verdict). Je cite cette source ; je n'ai pas relu les
transcriptions de sous-agent, qui vivent hors du depot.

**Le fait.** Les deux instances de `nadia-prof` qui ont audite en parallele a s50 tournaient dans la
meme session, donc dans le meme scratchpad et sur le meme checkout (aucun worktree ni aucune branche
de s50). Elles ont ecrit `jouer.py` au meme endroit, sans le savoir : l'instance du second passage de
`S50-ART` a 21:31:21, l'instance du second passage de `FRA-CE1-02` a 21:31:34 (UTC+02:00, 2026-10-04),
ecrasant le fichier de la premiere. La premiere a detecte la collision a 21:33:49 et repris son travail
dans un sous-repertoire dedie a 21:34:15, sans l'ecrire nulle part. C'est la forme (c) de
`ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp...`, section 8.

**Ce que dit la source sur les consequences.** Aucun verdict de s50 ne repose sur un fichier de l'autre
instance (section 2.3 de l'arbitrage) ; elle compte pourtant l'occurrence, la forme (c) se constatant a
l'ecriture concurrente et non a son effet.

**La regle violee existait.** `squad/README.md` section 9 (amendement de s30, applicable depuis s31) :
*"toute paire d'instances du meme agent tournant en parallele recoit un scratchpad aussi dedie que son
worktree"*. `DEC-0109` ne la citait pas, et ni la review, ni cette retro, ni `RAPPORT.md` de s50 ne
portaient d'occurrence : j'avais lu leur absence de mention comme une absence de collision, alors que
l'incident n'avait ete ecrit par personne.

**Ce que cet amendement ne fait pas.** Il ne reecrit rien des sections 1 a 4, il ne modifie aucun
verdict de s50, et il n'amende pas `squad/README.md` : la regle est deja ecrite, c'est son application
qui a manque. A s51, la regle est appliquee dans la consigne de lancement de chaque instance
(`squad/sprints/s51-trois-instances-francais-sciences-maths/planning.md`, section "Isolation des
instances"). Consequence pour s52, ecrite par `nadia-prof` et non par moi : la condition (ii) de sa borne
a trois instances n'est remplie que si s51 clot sans occurrence des formes (a), (b) ou (c), verifiee par
elle (section 8 de son arbitrage).

**Decision prise par cet amendement.** Aucune `DEC` ni `ADR` : la retro de s50 consigne a posteriori un
fait date, avec sa source.
