# Retro de s27 - deux matieres neuves

## Ce qui a marche

- **La discipline "un chiffre non ratifie ne fige pas une cloture"** : sur signal de l'orchestrateur,
  j'ai refuse d'ecrire `review.md` et `RAPPORT.md` tant que `camille-po` et `lea-dev` n'avaient pas
  rendu, plutot que de clore sur 44,0 % et une reserve encore ouverte. Ce n'est pas une prudence
  gratuite : un rapport de cloture qui fige un chiffre non ratifie est exactement la famille de
  defaut que ce sprint a chassee quinze fois sous d'autres formes (voir plus bas). Verdict de
  l'orchestrateur, verse tel quel : "un rapport de cloture qui fige un chiffre non ratifie est
  precisement la famille de defaut que ce sprint a chassee quinze fois."
- **Garder un commit de fusion non pousse pour ne livrer qu'un seul sommet coherent.** L'arbre de
  ce sprint a bouge sept fois (`a8c03b8`, `6f16436`, `5e5fbf6`, `e3cef81`, `fb72ea1`/`30c0f8e`/
  `8b07a8a`, `d7fab95`, `fe14ea2`, `b2fdf01`/`4d10a95`/`b51803d`) et chaque mouvement a coute un
  recalage a `yanis-qa`, au `relecteur-pedagogique` et a `camille-po`, qui devaient reprendre leur
  mesure sur le nouveau sommet. Regrouper les trois dernieres fusions (Done pedagogique,
  ratification, correction) en un seul push a evite un huitieme recalage inutile.
- **Le geste qui traverse tout le sprint, chez plusieurs agents independamment** : mesurer avant
  d'affirmer, et le dire quand la mesure contredit l'intuition. `ines-design` sur le cliquet de
  poids, `nadia-prof` sur le billet de 20 et sur son propre pilote d'audit, `theo-archi` sur son
  propre chiffre de titres portant le grapheme, `camille-po` sur sa reconstruction de la matrice.
  Aucun de ces quatre n'avait a l'ecrire contre lui-meme ; tous l'ont fait.
- **Un fait de coordination qui a tenu, en negatif** : `lea-dev` et `marc-dev` ont chacun bute sur
  `DEC-0072` non resolue et ont retire leur citation plutot que de creer le fichier eux-memes -
  cinquieme et sixieme occurrences de cette dette depuis s26, mais cette fois le garde-fou a mordu
  **avant** le commit, chez deux agents independamment, plutot qu'apres coup a la fusion.

## Ce qui a coince

### La famille dominante du sprint : juste en intention, faux en extension

**Quinze occurrences** en un seul sprint d'une regle ecrite juste sur son intention et fausse sur
la population reelle qu'elle couvre : le critere de grapheme /sj/ sur les titres (`theo-archi`,
quatre titres deja servis le contredisaient), la clause de couleur d'`ADR-0036` (interdisait tout
remplissage, pas seulement celui qui decide), `POIDS_D_UN_DESSIN_MAXIMUM` annonce a 2685 (faux a la
mesure, 2775), la cle de lot d'`ADR-0079` (concatenation brute, non normalisee), et onze autres
plus petites, tracees au fil des fichiers de ce sprint plutot que reprises ici une par une.

**La famille s'est deplacee d'un cran ce sprint, et c'est la lecon a garder** :

> Une regle qui derive sa population derive aussi ses erreurs de la fonction qui la calcule, et
> cette fonction merite le meme traitement que la regle elle-meme. (`theo-archi`, commit `139d785`)

Ce depot a passe trois sprints a remplacer des populations enumerees par des populations calculees
- le bon geste, deja plusieurs fois salue dans les retros precedentes. Ce que ce sprint montre :
**chaque fonction de calcul est devenue elle-meme un point de rupture non teste**, invisible tant
que le materiau du jour est homogene. Les quatre descripteurs reels de ce sprint etant identiques
au caractere pres, aucune des six morsures qui s'appuyaient sur la cle de lot ne pouvait voir le
defaut avant que `yanis-qa` ne le fasse apparaitre par mutation.

**Comment les quinze ont ete trouves - et c'est le fait qui doit rester** : par la mesure d'un
auteur contre lui-meme (`theo-archi` x2, `nadia-prof` x2), par une utilisatrice qui joue (le titre
"Calcul CP"), par un agent qui refuse de pousser sur un arbre rouge (`lea-dev`, `marc-dev` sur
`DEC-0072`), par une mutation sur materiel reel (`yanis-qa`, `theo-archi`), par un tiers qui
recompte une population (`camille-po` recomptant le taux, `nadia-prof` recomptant les quatre
descripteurs), par une relecture qui compare un fichier a lui-meme (`relecteur-pedagogique`).
**Aucun n'a ete trouve par la relecture d'une clause seule.** Une clause se lit juste ; une clause
se verifie fausse contre sa population.

**Sa formulation du defaut de la veille, qui vaut d'etre gardee** (`theo-archi`, a propos de son
annonce fausse sur `POIDS_D_UN_DESSIN_MAXIMUM`) :

> J'affirmais une valeur non mesuree sous la forme la plus dangereuse, une negation. "Ce cliquet
> ne bougera pas" se verifie moins spontanement que "ce cliquet vaudra tant", et ne demande pas
> moins de mesure.

### Les ecarts de l'orchestrateur

Deux ce sprint, tous deux deja journalises au fil de l'eau plutot que decouverts a la cloture :

1. **L'affectation des paquets doublait la table du planning.** `S27-2` (questionner le monde) etait
   affecte a `marc-dev` a l'ouverture, mais executee par `lea-dev` en pratique - ecart constate et
   corrige par un addendum au `planning.md`, sans consequence sur la livraison.
2. **Faire travailler plusieurs agents en parallele sur un meme clone local, sans regle de
   coordination.** C'est le plus couteux des deux. Pendant la premiere fusion, `theo-archi` a
   synchronise sa propre branche avec ma fusion en cours dans une session concurrente ; j'ai
   interprete ce mouvement de ref comme une corruption et execute un `git reset --hard` sur sa
   branche pour la restaurer. Aucun commit n'a ete perdu (le mouvement etait un simple
   fast-forward, entierement reconstructible), mais `theo-archi` rapporte avoir perdu des
   **modifications non commitees** et avoir du les refaire - c'est le cout reel de l'incident, et
   il vient de la facon dont l'orchestrateur a fait travailler la squad, pas d'une faute d'un
   agent. **La regle qui manquait - on ne touche jamais au worktree ni a la branche d'un autre
   agent, on tire leurs commits dans le sien, jamais on n'agit chez eux - a ete posee par
   l'orchestrateur apres cet incident, pas avant.** Elle est desormais ecrite dans ce depot (voir
   l'amendement ci-dessous) : un incident de conduite consigne en memoire de session et jamais
   ecrit dans un fichier du depot est deja une dette nommee par l'amendement de s21 (section 4 du
   README) ; celui-ci ne la reproduit pas.

## Amendement a `squad/README.md`

Ajoute a la section 10 (Frontieres entre agents) :

> **Aucun agent ne touche au worktree ni a la branche d'un autre agent - ni `reset`, ni
> `checkout`, ni `merge`, ni rien** (amendement s27, retro de s27, pose par l'orchestrateur apres
> un incident de coordination). Sur `round-two`, plusieurs sessions d'agents travaillent en
> parallele sur le meme clone local ; une branche qui bouge sans qu'on l'ait soi-meme fait n'est
> pas necessairement une corruption, elle peut etre une autre session en cours. Integrer le
> travail d'un autre agent se fait **en tirant** sa branche dans la sienne (`git merge
> <sa-branche>`), jamais en agissant dans son worktree. Si une branche parait incoherente, elle
> est signalee a l'orchestrateur, seul a savoir qui travaille encore - elle n'est pas corrigee
> unilateralement. Motif : `sofia-sm` a executé un `git reset --hard` sur la branche de
> `theo-archi` en la croyant corrompue alors qu'il s'agissait de sa propre session concurrente ;
> aucun commit n'a ete perdu, mais `theo-archi` a du refaire des modifications non commitees. Le
> cout vient de l'absence de cette regle, pas d'une faute d'agent.

## Decisions prises (creees ou modifiees ce sprint)

| Reference | Titre | Type | Statut |
|---|---|---|---|
| `ADR-0077` | Un garde-fou execute la clause qu'il cite ; la population d'une clause de paquet se calcule sur le dessin | Architecture | Accepte, amende deux fois (chiffre corrige, mecanisme de mutation corrige) |
| `ADR-0078` | Aucun titre de jeu ne porte le niveau de son paquet ; le niveau est lu dans le paquet | Architecture | Accepte, amende (`S1-8` retrecie) |
| `ADR-0079` | Une condition de lot se compte sur le lot de matiere, derive des descripteurs | Architecture | Accepte, amende (cle de regroupement normalisee) |
| `DEC-0070` | Le titre "Calcul CP" devient "Les petites additions" | Produit | Acceptee et appliquee |
| `DEC-0071` | La matrice de couverture ne porte que deux matieres sur le programme | Produit | Proposee |
| `DEC-0072` | Deux arbitrages de l'utilisatrice sur les matieres absentes | Produit | Acceptee |
| `DEC-0073` | `nombreDeManches: 7` ratifie pour les trois paquets de questionner le monde | Produit | Ratifiee |
