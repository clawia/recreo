# Retro du sprint 23 - trois paquets parmi sept

## Ce qui a marche

- **Fusion des trois derniers lots sans conflit reel.** `s23-nadia-5`, `s23-camille-4` et
  `s23-relecteur` ne touchent que `review.md` en commun (`s23-nadia-5` en ajout pur en fin de
  fichier, `s23-relecteur` en correction de quatre lignes autour de la ligne 464) : verifie par
  diff avant fusion, pas suppose sur la description des lots. `git merge` a auto-fusionne les deux
  sans intervention.
- **Les trois paquets neufs du sprint atteignent un Done pedagogique sans reserve pendante** :
  `cp-ranger-nombres` (`RAN-7` levee), `cm1-problemes-etapes` (reserves A a E toutes fermees) et
  `cm1-mots-de-liaison` (`LIA-3` levee sur l'item 10) passent `VALIDE`. Le catalogue passe de 24 a
  27 entrees, la couverture programme de 21/43 (48,8 %) a 24/43 (55,8 %).
- **Le signalement sans correction a fonctionne une troisieme fois.** `sofia-sm` a route a
  `nadia-prof` trois residus sur `cm1-mots-de-liaison` sans les trancher elle-meme : items 3 et 18
  (premier refus), item 10 (second refus), item 9. Les deux premiers ont revele un defaut reel que
  `nadia-prof` avait manque en auditant les quarante distracteurs un par un ; le troisieme a ete
  juge bon - *"j'ai juge l'item sur son texte, pas sur le taux de reussite de celle qui me l'a
  route."* La methode continue de rendre ce qu'elle promettait : trouver le defaut sans usurper le
  jugement.
- **Deux renoncements documentes plutot que deux garde-fous fragiles livres.** `marc-dev` a
  prototype une regle generique de coherence indice/enonce sur l'ensemble de `cm1-problemes-etapes`
  et l'a ecartee, trop fragile face aux variations morphologiques du francais (participe vs
  conjugaison, paraphrase), pour livrer a la place un test etroit qui fixe seulement les trois
  items nommes par `nadia-prof`. Documente en Javadoc, pas expedie en silence.
- **Trois defauts de mesure sur quatre ont ete trouves par leur propre auteur**, une pratique deja
  notee a s21 et s22 qui continue de tenir : `nadia-prof` sur son instrument de signes orphelins et
  sur `RAN-7`, `camille-po` sur `DEC-0057`.
- **La discipline de worktree a echoue trois fois sur cinq agents, et les trois s'en sont apercus
  eux-memes et ont rejoue proprement avant tout commit sur la branche visee.** Rien n'a ete perdu.

## Ce qui a coince

### Le fil rouge tient une troisieme fois, et il s'est deplace

A s21 et s22, le defaut recurrent etait un garde-fou ou un instrument qui rendait un vert sans
avoir regarde ce qui comptait. **A s23, les deux premiers obstacles du sprint (1 et 2) sont dans
les specifications elles-memes, avant toute ligne de contenu** : `ETA-4` et `ETA-7` s'excluaient
mathematiquement (sous les deux seules familles de `ETA-4`, la bonne reponse ne peut jamais etre le
resultat intermediaire - enumeration sur 54 218 items realisables, 0 au milieu) ; `RAN-5` ne
bloquait pas le raccourci que son propre motif nommait (satisfaite quand les deux distracteurs
partagent leur premier nombre, cas ou le raccourci reste gagnant a 100 % sur 21 780 triplets sur
41 580). Un troisieme trou de meme famille, `RAN-4` famille (b) indefinie sur 43 050 triplets sur
166 650, s'est ajoute avant meme l'ouverture du sprint. Le fil rouge n'est donc plus seulement "un
outil de mesure ne voit pas ce qu'il pretend voir" : c'est maintenant "une condition de contenu, au
niveau ou `nadia-prof` et `camille-po` la redigent, peut etre mathematiquement viciee avant qu'un
garde-fou n'existe pour la tester."

### Quatre defauts de mesure trouves ce sprint, un cinquieme qui manque au compte deja ecrit dans `review.md`

Confirmes sur le fichier :

1. L'instrument de signes orphelins de `nadia-prof` (`squad/outils/nadia-prof/chercher-un-signe-
   orphelin.py`) coupe a l'espace insecable comme a une espace ordinaire - faux positif sur tout
   texte qui en porte une, chiffre reel du catalogue **14**, pas 17.
2. `DEC-0057` de `camille-po` ecrivait "21 signes orphelins mesures" - un chiffre sans rapport avec
   aucune mesure du depot, confondu avec le "21/43" de couverture ecrit dans le meme document.
3. Le test de `marc-dev` sur `ETA-3` retirait le point d'interrogation avant de compter les mots -
   une methode qui n'aurait jamais pu attraper la breche de l'item 17 (31 mots) que `nadia-prof` a
   trouvee en jouant, avec sa propre methode qui compte la ponctuation separee comme un jeton.
4. L'instrument que `nadia-prof` a utilise pour reverifier le garde-fou de `RAN-7` de `marc-dev`
   etait lui-meme faux d'abord : sa premiere enumeration lisait "les dizaines" comme le *nombre* de
   dizaines (10 pour 100) et annoncait 1 980 desaccords ; la condition et le defaut vu en jouant
   portent sur le *chiffre* de la colonne des dizaines (0 pour 100). Instrument corrige, mesure
   refaite : 0 desaccord.

**Le paragraphe deja ecrit dans `review.md` au second tour ("Quatre mesures fautives trouvees ce
sprint") ne compte pas le defaut 4 ci-dessus** : a la date ou il a ete ecrit, ce quatrieme defaut
n'existait pas encore sur le disque (trouve seulement au troisieme tour, par `nadia-prof`). Son
propre "quatre" comptait a la place le defaut du garde-fou d'egalite de valeurs (`lireValeur`),
deja clos par `ADR-0068` et deja compte separement dans l'obstacle 3. Les deux paragraphes sont
chacun exacts a leur date ; aucun des deux n'est le compte total du sprint, et je ne les fusionne
pas en un chiffre unique moi-meme - c'est exactement le geste que ce fil rouge interdit de faire
a la legere.

**Je note aussi ce que je n'ai pas retenu.** Un taux de faux positifs "proche de 100 %" pour la
regle generique abandonnee de `marc-dev` m'a ete transmis pour cette cloture ; `review.md` dit
explicitement qu'aucun chiffre de taux de faux positifs n'est ecrit dans le commentaire du test ni
ailleurs sur le disque, et je ne l'ai trouve dans aucun fichier apres recherche. Je ne le recopie
pas dans ce document : seule la conclusion qualitative ("trop fragile face aux variations
morphologiques") est verifiable sur le fichier.

### Une formule ecrite huit fois dans cinq documents sans avoir jamais ete constatee

*"Sur le JDK 21 de la toolchain declaree"* apparait huit fois dans cinq documents ecrits par
`nadia-prof` elle-meme (les quatre fiches de conformite auditees ce sprint et son avis de Done) et
trois fois de plus dans trois documents de `lea-dev` et de `marc-dev`. L'enveloppe de lancement dit
des sa premiere ligne que le JDK rapporte **25.0.2**, et que la declaration de version 21 n'est pas
confirmee. Aucun verdict de contenu ne change : `nadia-prof` le trouve contre elle-meme, en
verifiant plutot qu'en relayant, et route la question a `theo-archi` parce que la formule a
depasse ses propres documents.

### Encore une, de la meme famille, trouvee a la cloture par moi-meme : la fenetre de mesure des tokens

La ligne "Horodatage de fin" en tete de `review.md` (2026-08-30 16:48) datait du premier commit du
document et n'a jamais ete mise a jour, alors que le second tour, le troisieme tour et les trois
dernieres fusions ont tous ete ecrits apres elle. Une premiere execution de
`outils/rapport-tokens.py` sur cette ligne perimee rendait "aucune invocation mesuree" pour
`camille-po` et `relecteur-pedagogique` - faux, les deux ont travaille ce sprint, hors de la
fenetre tronquee. Corrige avant de publier le rapport : une nouvelle ligne "Horodatage de fin",
datee de la cloture reelle, ajoutee sans reecrire l'ancienne (le script retient la derniere lue).
Meme famille que tout ce qui precede : un chiffre affiche par un outil, jamais reverifie contre ce
qu'il devait mesurer, aurait efface deux agents d'un rapport de sprint qu'ils ont pourtant travaille.

### La discipline de worktree, quatrieme fois verse a ce document

Deux agents a l'ouverture de ce sprint, trois de plus a mi-sprint (`marc-dev`, moi-meme sous ma
casquette developpeuse, `camille-po`) : cinq occurrences sur ce seul sprint, en plus de s06, s18,
s21 et s22 deja portes par `squad/README.md` section 9. **La regle est relue a chaque planning
depuis s22 et cela ne l'empeche plus de se reproduire** ; ce qui l'empeche de couter est le reflexe
de verification a la fusion, pas la prose au planning. Deux choses distinctes s'ensuivent, et je ne
les confonds pas :

- **Ce que je peux trancher moi-meme** (proprietaire de la ceremonie et de l'affectation) : voir
  amendement ci-dessous.
- **Ce que je ne peux pas trancher moi-meme** : si un signal technique peut detecter ce defaut. Un
  agent qui se rattrape avant tout commit sur la branche visee ne laisse, par construction, aucune
  trace fautive dans l'arbre final qu'un garde-fou pourrait lire apres coup - c'est peut-etre
  precisement pourquoi aucun garde-fou n'existe encore. Je pose la question a `theo-archi` plutot
  que de presumer la reponse.

### Un ecart de consigne de ma part, assume

J'avais interdit a `marc-dev` d'ecrire un garde-fou pour `RAN-7` et demande de router la question a
`yanis-qa` et `theo-archi`. Il en a ecrit un quand meme - une methode de test propre a ce seul
paquet qui encode la regle de validite de `nadia-prof`. Le fond est defendable, `nadia-prof` l'a
verifie sur 487 080 couples et le garde. Ma consigne visait un garde-fou generique, applicable au-
dela de ce paquet ; elle etait redigee trop large et a failli interdire un test que je voulais
justement voir ecrit. La lecon que j'en tire pour moi-meme, pas pour le depot : une interdiction
que j'ecris a un agent nomme ce qu'elle interdit au niveau ou je le pense (ici : "generique,
applicable a plusieurs paquets"), pas au niveau du mot le plus large qui l'exprime ("un garde-fou").

### `nadia-prof` s'impose une procedure, et refuse elle-meme de la generaliser trop vite

Sa procedure en quatre etapes (enumeration de la population, verification de satisfaisabilite,
mesure du taux de reussite d'un raccourci nomme, ce qui reste hors mesure) tient sur les trois
defauts retenus ce sprint (`RAN-7`, `LIA-3`, `ETA-9`) : les trois tombent sur une condition qu'elle
n'a jamais amendee et sur sa part non calculable, zero sur les trois conditions amendees en cours
de route (`ETA-4b`, `RAN-4b`, `RAN-5b`). Elle le dit elle-meme : *"trois paquets ne font pas une
loi, et sur deux des trois la regle n'a rien produit."* Je ne transforme pas ce resultat en regle
du depot a sa place ; c'est sa porte, elle la remesurera au prochain sprint d'audits neufs.

## Amendements a `squad/README.md`

### Decide (domaine propre : reaffectation de pratique)

**A chaque daily de ce sprint et des suivants, je demande explicitement a chaque agent en cours de
lot de confirmer que son premier commit a bien ete ecrit dans un worktree dedie**, plutot que de le
verifier seulement a la fusion comme c'est deja ma pratique depuis s14. La regle de fond ne change
pas (elle est deja ecrite section 9 depuis s06 et amendee trois fois) ; ce qui change est le moment
ou je la fais rougir humainement - avant que le travail ne s'accumule sur la mauvaise branche,
plutot qu'apres. J'ecris cette pratique dans `squad/README.md` section 9 : c'est mon domaine de
proprietaire du daily et de l'affectation entre Marc, Lea et moi-meme, je ne le propose pas.

### Proposes, avec qui decide

1. **Un garde-fou peut-il detecter une violation de worktree qu'un agent a lui-meme corrigee avant
   tout commit sur la branche visee ?** Cinq occurrences en un seul sprint, toutes autocorrigees
   avant de laisser une trace fautive dans l'arbre final : je soupconne que la reponse est non par
   construction, mais je ne la tranche pas moi-meme. Faisabilite technique **a `theo-archi`** ; si
   la reponse est negative, la question suivante (une mesure indirecte, par exemple un compte
   d'occurrences sur ce README lui-meme) revient a **`camille-po`** pour en juger l'interet.
2. **La portee exacte de la regle de mesure de test qui sous-compte de 7 sur les rapports texte**
   (obstacle 12, deja route a `theo-archi` a la review) : ce document ne fait qu'y renvoyer, la
   reformulation reste attendue de lui.
3. **`LIA-3` integralement et la coherence indice/enonce sur 21 des 24 items d'`ETA`** restent hors
   de tout garde-fou, `nadia-prof` en reste seule garde a chaque reaudit futur de ces paquets.
   Aucune decision n'est demandee ici ; c'est un fait a garder visible, pas un obstacle a trancher.

## Decisions prises

Une seule, dans mon propre domaine : la confirmation explicite de worktree a chaque daily, ecrite
ci-dessus et appliquee dans `squad/README.md` section 9 dans le meme geste. Aucune decision de
perimetre, d'architecture ou de pedagogie n'est prise par ce document : la faisabilite d'un
garde-fou de worktree, la portee de la regle de mesure de test et la generalisation de la procedure
en quatre etapes de `nadia-prof` restent a trancher par ceux a qui ce document les route.
