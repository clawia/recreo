# [Sprint 32] Retrospective - 2026-09-09

- **Anime par** : sofia-sm

## Ce qui a marche

- **Le mecanisme de decote de `DEC-0083` a fait exactement ce pour quoi il a ete ecrit.** Les trois
  instances de `nadia-prof` ont annonce d'avance, chacune dans son propre document et avant meme la
  fusion, le risque d'ecrire dans `squad/decisions/REGISTRE-identifiants.md` en partant du meme
  compte de base (547/94). La fusion a produit un conflit reel et visible sur ce fichier, jamais
  silencieux, resolu en gardant les notes des trois instances verbatim et en recomptant sur le
  fichier assemble (578 identifiants, 97 prefixes, 0 doublon). C'est la troisieme occurrence de
  cette forme de couture sur ce depot, et la premiere ou elle a ete geree plutot que decouverte au
  rouge - `DEC-0084` en tire la consequence prevue : la retenue de 3,0 sur la borne de s33 ne tombe
  pas, parce que la condition de `DEC-0083` porte sur l'occurrence, pas sur sa gestion.
- **Le geste que `CLAUDE.md` reclame depuis la cloture de s30 a ete tenu par tous.** Chaque agent a
  lance les quatre jobs de `.github/workflows/ci.yml` sur son propre diff avant de commiter, et les
  a nommes un par un - `build` compris, jamais deduit d'un seul `mvn verify`. Deux rouges reels ont
  ete trouves de cette facon avant d'atteindre `round-two` : l'incident de mesure de l'instance A
  (`maven-clean-plugin` bloque par un `recreo.jar` tenu ouvert par une partie en cours) et la
  regression sur `PaquetCpEmcExclusionTest` que `theo-archi` a mesuree avant de la corriger.
- **Un garde-fou annonce comme fermant une dette a ete mis a l'epreuve, pas cru sur parole.**
  `yanis-qa` a reinjecte l'etat refuse fige de `cp-emc-regle-souhait-loi.yaml` pour verifier que
  l'exemption `DETTE_DE_MARQUEUR` mordait reellement une fois retiree (rouge attendu obtenu, puis
  restauration verte), plutot que de constater le vert de l'arbre fusionne et s'arreter la. Deux
  autres agents ont fait le meme geste sur leurs propres regles ce sprint : `theo-archi` en mesurant
  le blast radius exact d'`EXC-R1` (4 tests, pas 5, en isolant l'echec impute a son propre diff) et
  `nadia-prof` en rejouant `SCO-1` sur huit parties completes plutot que de se fier a la correction
  deja annoncee.
- **Un correctif de contenu qui reproduisait la classe de defaut qu'il payait a ete trouve avant la
  cloture, pas apres.** `lea-dev` a identifie que ses trois corrections de `LON-1` sur des paquets
  distincts (`cp-emc-regle-souhait-loi` item 11, `cp-emc-emotions-autrui` item 4,
  `cp-emc-formules-de-politesse` item 11) partageaient une seule et meme cause - retirer une
  subordonnee pour tenir la barre de longueur sans reconstruire la phrase autour - et l'a nommee
  comme telle plutot que de traiter trois defauts independants.

## Ce qui a coince

- **Un correctif a remplace un repere de registre par un repere lexical, et c'est exactement la
  classe de defaut qu'il devait payer.** Le correctif de `CAT-1` sur `EMC-CP-02` et `EMC-CP-06`
  fait passer *maitresse* de 1 a 7 occurrences dans les distracteurs, sans qu'aucune bonne reponse
  ne la porte : un enfant qui reconnaitrait le mot plutot que de lire la clause de registre trouve
  la reponse par un raccourci different, pas absent. `nadia-prof` a refuse le paiement pour ce
  motif. Cout : `CAT-1` reste ouverte pour la quatrieme fois consecutive.
- **Une regle neuve, rendue executable pour la premiere fois, a produit un defaut de francais des
  son premier effet mesurable.** `ADR-0089` rend `LON-1` executable ; le premier resultat concret
  a ete un partitif retire dans un enonce servi a un enfant de CP ("vendre l'alcool" au lieu de
  "vendre de l'alcool"), trouve par le `relecteur-pedagogique` et route sans etre corrige, parce que
  reinserer le mot manquant fait rougir le garde-fou qui vient d'etre ecrit.
- **Un garde-fou ecrit ce sprint pour corriger une lecture de tache par sous-chaine d'enonce a
  coexiste, dans le meme fichier et le meme jour, avec deux occurrences neuves du meme patron
  fragile.** `ADR-0089` corrige `PaquetCpEmcExclusionTest` pour ce motif ; `marc-dev` introduit
  entretemps, dans `PaquetCm1HistoireGeographieTest`, deux tests qui lisent une tache par
  `startsWith(...)` sur l'enonce - trouves par `yanis-qa`, non faux aujourd'hui, mais fragiles au
  premier renom de l'enonce qui les motive.
- **Des chiffres se sont corriges par leur propre auteur, plusieurs fois, dans le meme sens - ce qui
  est bon en soi, mais c'est une serie a nommer.** `nadia-prof` sur trois de ses comptes de longueur,
  sur sa definition litterale de la phrase (22 enonces servis rendus rouges par une definition
  fausse) et sur le compte de reserves de `S32-4` (dix annoncees, neuf nommees) ; `theo-archi` sur
  le compte de garde-fous a exemption (cinq annonces, quatre reels) ; `yanis-qa` sur le compte
  d'`ArchRule` (35 annonce, 34 reel, par soustraction d'une population de deux factices comptee pour
  une) ; `marc-dev` sur le plancher de `FER-3` contre une consigne fautive de `sofia-sm` ;
  `sofia-sm` elle-meme sur le delta de tests de son propre lot (+10, pas +12 comme annonce avant
  mesure) et sur le SHA `dcbcd65a` transmis a `camille-po` avant reecriture de commit. Aucune de ces
  erreurs n'a atteint `round-two` sans avoir ete retrouvee et ecrite, mais leur nombre dans un seul
  sprint deplace la question : ce n'est plus l'exception d'un compte a verifier, c'est la regle.
- **Trois refus sont tombes sous une CI entierement verte, dont deux sur un fait faux enseigne a
  l'enfant.** Le sacre de Clovis en 481 (evenement qui n'a jamais eu lieu a cette date - Clovis y
  succede a son pere, son bapteme est vers 496) et un item de "chat perche" qui declare une faute le
  fait de toucher un joueur perche, la ou la regle du jeu dit l'inverse. La porte pedagogique a fait
  exactement ce que la porte technique ne peut pas faire : aucun test structurel ne verifie un fait
  d'histoire ou une regle de jeu de cour de recreation.
- **`nadia-prof` a mis a son propre debit une regression de fond, pas seulement des comptes.** La
  regression de l'item 4 de `regle-souhait-loi` (le casque a velo obligatoire marque a tort comme
  une regle qui ne vaut pas pour tous) vient d'une contradiction entre deux de ses propres textes du
  meme jour : l'un disait de conserver la tache A telle quelle, l'autre prescrivait d'y supprimer
  l'enumeration. `lea-dev` a suivi le second, choix raisonnable au vu de ce texte seul. `nadia-prof`
  signale aussi avoir credite deux fois le meme paiement (`PLA-1`) sans voir qu'une chaine qu'elle
  avait condamnee la veille survivait, relue le meme jour, sur un item voisin du meme paquet.
- **`marc-dev` a commence a ecrire dans le checkout partage plutot que dans un worktree dedie.** Il
  l'a vu avant de commiter, a deplace l'ensemble de son travail et verifie la lignee du worktree
  correct avant sa premiere ecriture : rien n'a atteint `round-two` par ce chemin, mais c'est le
  protocole de worktree lui-meme, impose depuis plusieurs sprints justement pour eviter la collision
  entre trois developpeurs sur les memes fichiers, qui a manque d'etre contourne des l'ouverture.

## Amendements au process

Aucun amendement a `squad/README.md` ce sprint. La reponse de gouvernance au risque recurrent
d'allocation d'identifiants entre instances paralleles (troisieme occurrence de cette forme sur ce
depot) vit deja au niveau de `DEC-0083`, dont le mecanisme de decote de la borne vient de
fonctionner comme concu - ce n'est pas une lacune de ceremonie a combler ici.

## Decisions prises

Aucune decision produit, pedagogique ou d'architecture n'est prise pendant cette retro.
