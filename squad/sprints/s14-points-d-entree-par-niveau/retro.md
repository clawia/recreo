# [Sprint s14] Retrospective - 2026-08-17

- **Anime par** : sofia-sm

## Ce qui a marche

- **Un refus pedagogique a ete traite par une remesure, pas par une contestation.** `nadia-prof` a
  refuse le premier increment de `marc-dev` en comptant quatre boites pressables sur la
  confirmation et trois sur l'erreur ; `theo-archi` a arbitre une forme de correctif dans la
  journee, `marc-dev` l'a livree, et `nadia-prof` a rejoue **les memes gestes et la meme commande**
  avant de valider - exactement ce qu'elle reprochait a l'increment refuse. Le cycle refus ->
  arbitrage -> correctif -> remesure a tenu en une seule journee, sans que personne n'ait ecarte le
  premier verdict pour aller plus vite.
- **Trois agents ont corrige leur propre chiffre contre eux-memes**, sans qu'un tiers ait eu a le
  leur signaler : `theo-archi` sur son 506 (contre le 507 de `marc-dev`), `nadia-prof` sur sa
  prevision de cout pessimiste de 53 px (l'ecart reel est de 13 px) et sur sa propre clause
  BARRE-3 qu'elle avait ecrite inapplicable, `theo-archi` encore sur sa derivation d'impact du
  geste du jour. Aucun de ces quatre chiffres n'a ete releve par un audit externe.
- **Une cause racine unique a ete identifiee pour toute la cascade du sprint.** `theo-archi` a
  trace le refus, l'oscillation de la clause 2 d'`ADR-0022` et la clause orpheline a une seule
  assertion mal placee (`masquages(...).isNotEmpty()`), et en a tire un test de methode
  reutilisable pour toute clause future qui depend d'un jugement humain : *si l'humain change
  d'avis, combien de fichiers de garde-fou bougent ? La bonne reponse est zero.*
- **Les deux dettes techniques routees par `DEC-0032` ont ete fermees dans le meme sprint que la
  story principale**, sans concurrence de capacite (modules et fichiers disjoints, verifie au
  planning avant affectation).

## Ce qui a coince

### 1. Aucun des trois rapports de cloture ne couvre l'increment reellement fusionne

`rapport-qualite.md` (`yanis-qa`) et `confirmation-archunit.md` (`theo-archi`) ont tous deux ete
ecrits avant le developpement de S14-2, avant le refus pedagogique et avant le correctif de
masquage - `rapport-qualite.md` a l'ouverture du sprint (`c1abb47`), `confirmation-archunit.md`
juste avant le correctif (`911af6a`). `relecture.md` s'arrete encore plus tot (`81a43ac`), avant
243 lignes neuves versees a `FICHE-barre-entree-niveau.md`. Aucun des trois n'a ete republie apres
le correctif. Ce n'est le defaut d'aucun de ces trois documents pris isolement - chacun est exact
sur l'arbre qu'il decrit - c'est l'absence d'une republication de cloture qui manque, une variante
du meme defaut que `theo-archi` avait deja nomme a s13 sur ce meme fichier ("je l'amenderai a la
review, sur l'arbre fusionne") et qui n'avait pas ete tenu non plus. J'ai ferme la boucle
technique moi-meme (ma propre execution de `mvn -B clean verify` sur `a7c7401`) et pose un filet
mecanique limite sur la redaction, mais aucun des deux ne remplace la ceremonie qui manque.

### 2. Un chemin dangling illustre a nouveau qu'un commit hors lignee n'existe pas pour le depot

Un commit historique `5761e48` (mention "toolchain jdk 21 exigee par le pom, CLAUDE.md aligne sur
ADR-0010") existe dans la base d'objets Git mais n'est l'ancetre d'aucune reference locale au
moment de cette fusion : `git log --all` ne le trouve pas. Verifie sans consequence sur ce
sprint - le worktree fusionne (`s14-nadia-porte`) descend proprement de `round-two`, confirme par
`outils/verifier-lignee.sh` - mais c'est un rappel de plus, dans la lignee de la lecon de s13, que
verifier la lignee **avant** de fusionner reste le seul geste qui protege contre ce genre d'objet
orphelin.

## Amendements a `squad/README.md`

**Un amendement ecrit, un candidat ecarte.**

**Ecrit** : `squad/README.md`, section 9, etape 7 (Review) - **une condition declaree "tenue par
construction" n'est pas une condition tenue tant qu'elle n'a pas ete verifiee sur le document
assemble.** Motif : ce sprint est la **quatrieme instance nommee** (par `theo-archi` lui-meme) de
la meme famille - un garde-fou ou une mesure credite d'une propriete qu'il ne peut pas voir depuis
son propre perimetre. `marc-dev` a raisonne juste sur le fragment qu'il ecrivait (l'en-tete n'est
effectivement pas reinjecte dans le fragment de confirmation) et faux sur ce que l'enfant voit
(l'en-tete du document assemble, lui, survit). `ADR-0020` porte deja ce principe pour
l'architecture ("l'unite de verification d'un ecran est le document assemble") ; l'amendement de
s12 le porte deja pour les releves automatiques cites en preuve. Ce qui manquait est la meme regle
pour une affirmation de raisonnement humaine, non instrumentee - c'est elle qui a produit le
refus de ce sprint, pas un outil de mesure. L'amendement est ecrit a l'etape ou `nadia-prof` ou
`sofia-sm` peuvent le lever : toute affirmation "tenu par construction" citee dans une revue de
code ou un accord d'architecte est verifiee sur le document reellement rendu avant d'entrer dans
un `review.md`, pas seulement raisonnee sur le fragment source.

**Ecarte** : "un lot qui retire un test dit ce que le test gardait avant de le retirer". Ce
sprint en fournit deux occurrences, toutes deux sur `theo-archi` et a l'interieur de la meme
cascade (le retrait force de la clause du marqueur, puis le refus d'un geste sur cinq dans le
premier lot de S14-2 parce qu'il emportait une propriete intacte). Contrairement au candidat
retenu, je n'ai pas trouve trace d'une occurrence anterieure a ce sprint dans les retros
precedentes : c'est un seul agent, un seul sprint, une seule cascade causale - la lecon que
`theo-archi` en tire ("si l'humain change d'avis, combien de fichiers bougent ? zero") est deja
ecrite dans l'`ADR-0022` lui-meme, ou elle sert directement. L'ecrire une seconde fois comme
process de squad generaliserait un incident plutot qu'une categorie, exactement la distinction
qu'`yanis-qa` avait posee en s11. Si une troisieme occurrence apparait dans un sprint futur, sur
un agent different ou un `ADR` different, elle justifiera de revenir sur ce choix.

## Decisions prises

1. **Un amendement est ecrit dans `squad/README.md`, section 9, etape 7** : une condition "tenue
   par construction" doit etre verifiee sur le document assemble avant d'entrer dans un
   `review.md` (voir ci-dessus).
2. **Le second candidat d'amendement ("un lot qui retire un test dit ce qu'il gardait") n'est pas
   ecrit** : il generalise un incident de ce sprint, pas une categorie recurrente au sens de la
   distinction posee par `yanis-qa` en s11 (voir ci-dessus).
3. **`rapport-qualite.md`, `confirmation-archunit.md` et `relecture.md` ne sont pas completes a la
   place de leurs auteurs.** L'ecart entre leur date d'arret et l'increment reellement fusionne est
   route comme obstacle ouvert dans `review.md` et `RAPPORT.md`, pas absorbe dans une ceremonie que
   je fabriquerais en leur nom.
4. **Aucun worktree n'est supprime dans ce geste.** Les treize worktrees de s14 restent enregistres ;
   la suppression est une operation sur instruction, pas de ma seule initiative.
5. **La mise a jour de `MATRICE-couverture.md`** ne s'applique pas ce sprint - aucune ligne de
   programme n'a ete ouverte, choix acte au planning et non un manque.
