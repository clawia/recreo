# [Sprint s18] Retro - fermer avant ouvrir

## Ce qui a marche

- **Le process en trois etapes a tenu sous tension**, y compris quand le premier tour l'a rate.
  `theo-archi` a trouve deux defauts factuels dans le perimetre propose par `camille-po` avant tout
  commit de code (une reference d'ecran perimee, un outil structurellement hors sujet) ; `camille-po`
  a corrige, et le sprint n'a demarre son code qu'une fois ces corrections fusionnees. Personne n'a
  code sur un critere qu'on savait deja faux.
- **La distinction entre Done technique et Done pedagogique a fonctionne exactement comme concue,
  pas seulement sur le papier.** Le matin du 2026-08-23, un increment techniquement irreprochable
  (612 puis 621 tests verts) a ete **refuse** deux fois par `nadia-prof` (`BPM-5`, `SYN-11`), parce
  que la mesure portait sur une regle injectee et non sur l'increment servi. L'apres-midi, le meme
  increment, corrige et **servi**, a ete accorde. Aucun Done pedagogique n'a ete credite sur une
  promesse de code vert.
- **La discipline de reverification d'un chiffre plutot que de le relayer a tenu au moins quatre
  fois dans ce seul sprint**, chez quatre agents differents : `lea-dev` a corrige le "une ligne de
  plafond" de `theo-archi` en deux (deux selecteurs, deux chaines) ; `theo-archi` a corrige mon
  "deux criteres de `S17-4` a reecrire" en un seul critere a deux defauts ; `camille-po` a corrige
  ma propre mise en garde ("verifier que l'entree n'expose a aucune regle neuve") en verifiant
  elle-meme la population reelle des regles de contenu plutot que de la supposer risquee ; et
  `nadia-prof` a corrige deux fois sa propre main dans la meme journee - un faux positif de sa
  sonde qui aurait masque `M23`, et une agregation erronee de mesures de deux ecrans differents qui
  aurait invente un defaut de 5 px inexistant.
- **`nadia-prof` a refuse de relire sa propre condition plus severement qu'elle ne l'avait ecrite**,
  alors que 2,19 px l'auraient arrangee (item 13 de `BPM-5` : la boite deborde de 2,19 px, le texte
  qu'elle avait promis de garder lisible ne deborde pas). Une barre qui bouge apres coup pour
  confirmer un verdict deja rendu ne vaut plus rien ; elle l'a tenue immobile.
- **`nadia-prof` a declare un audit degrade plutot que de le masquer ou de le forcer** : l'ecran
  `erreur.html` (`M22`) demande une erreur technique qu'elle ne sait pas provoquer en jouant, et
  elle a refuse de fabriquer un appel HTTP pour l'atteindre. Un audit degrade nomme reste plus utile
  qu'un audit complet obtenu par un geste qu'un enfant ne peut pas faire.

## Ce qui a coince

- **La meme famille d'incident de process que celle de s06 s'est reproduite**, avec un agent
  different : `camille-po` a edite la matrice de couverture directement dans le depot principal au
  lieu d'un worktree dedie pour son lot de cloture. Detecte avant tout commit, rien perdu - mais la
  regle ecrite depuis douze sprints n'a pas empeche le reflexe de la relire au lieu de l'appliquer.
  Amendement porte ci-dessous.
- **Trois defauts de garde-fou etaient invisibles a la lecture, y compris a la mienne et a celle de
  `theo-archi` lui-meme sur son propre travail de la veille** : un seuil evitable en nichant une
  declaration sous `@media`, une clause textuelle contournable par l'orthographe la plus naturelle
  de la faute qu'elle interdit, et un mecanisme dont la moitie pouvait disparaitre sans faire
  rougir un seul test. Les trois n'ont ete trouvees qu'en appliquant reellement un candidat concret
  et en mesurant, jamais en relisant du code deja revu. Amendement porte ci-dessous.
- **Une hypothese de mesure est restee implicite pendant un sprint entier** : l'inventaire
  d'appareils d'`ines-design` bornait le plancher "ordinateur" a 1280 px sous une methode "fenetre
  maximisee" jamais ecrite comme une hypothese limitante, jusqu'a ce que `camille-po` la retrouve en
  instruisant `SYN-16`. Amendement porte ci-dessous.
- **La capacite de mesure de `nadia-prof` est restee la ressource critique du sprint de bout en
  bout**, comme prevu au planning, mais deux de ses trois campagnes ont du etre rejouees dans la
  meme journee (regle injectee le matin, increment servi l'apres-midi) parce que le premier passage
  mesurait un materiau qui n'etait pas encore celui livre. Ce n'est pas un defaut de discipline -
  c'est exactement la regle qu'elle applique - mais cela double le cout reel d'une campagne qui
  arrive avant que son increment ne soit stabilise. A garder en tete pour le sequencement d'un
  prochain sprint de meme forme : mesurer une fois l'increment est **stable**, pas des qu'un
  correctif existe.

## Amendements a `squad/README.md`

**Portes, dans le meme geste que cette retro** (edites directement dans le fichier, pas seulement
annonces ici) :

1. **Un garde-fou deja accepte, avec sa propre preuve de morsure, n'a ete mis a l'epreuve que par
   une tentative reelle de le defaire, jamais par une relecture** - ajoute a la suite des
   amendements s11/s12/s17 sur la meme famille (un signal ne remplace pas une lecture, une lecture
   ne remplace pas une tentative reelle). Motif : trois defauts trouves le meme sprint dans des
   regles deja acceptees et deja pourvues de leur morsure, tous invisibles a la relecture.
2. **Une mesure de reference doit nommer son hypothese de contexte a cote de sa valeur** - motif :
   l'inventaire d'appareils d'`ines-design`, dont le plancher "ordinateur" portait une hypothese de
   fenetre maximisee jamais ecrite comme telle, et qui a change la reponse a une question posee un
   sprint plus tard.
3. **Suite a l'amendement s06 sur le worktree dedie** : la regle a ete relue et pourtant
   recontournee par un agent different, meme famille de faute, douze sprints plus tard. Motif :
   nommer la recurrence pour qu'elle reste visible, et faire de la verification de provenance d'un
   lot (jamais ecrit hors d'un worktree) une pratique explicite de `sofia-sm` a chaque fusion,
   plutot qu'une consequence implicite de sa vigilance.

**Decline, avec son motif** :

- **Le chiffre corrige par un tiers plutot que relaye** (les quatre instances de ce sprint : `lea-
  dev`/`theo-archi`, `theo-archi`/`sofia-sm`, `camille-po`/`sofia-sm`, `nadia-prof`/elle-meme). Je
  ne porte pas de nouvel amendement ici : la regle existe deja et date de s16 ("un chiffre annonce
  par un agent se reverifie, il ne se relaie pas"), avec deux instances anterieures qui l'ont deja
  justifiee. Ce sprint n'est pas une nouvelle faille de cette regle, **c'est une preuve qu'elle
  fonctionne**, quatre fois dans le meme sprint plutot qu'une. L'ecrire comme un amendement neuf
  laisserait croire a un gap qui n'existe pas ; je le verse ici, en retro, comme confirmation plutot
  que comme correction.
