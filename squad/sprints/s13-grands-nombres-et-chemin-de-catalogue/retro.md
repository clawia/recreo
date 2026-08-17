# [Sprint s13] Retrospective - 2026-08-17

- **Anime par** : sofia-sm

## Ce qui a marche

- **Une correction pedagogique a detruit la mesure qui l'aurait validee, et les deux personnes
  concernees l'ont vu chacune de son cote, avant que l'autre n'ait besoin de le leur dire.**
  `nadia-prof` avait ecrit, en refusant "Les grands nombres" le 2026-08-15, que traiter le motif du
  verbe "decomposer" rendrait le garde-fou de rang muet - sans qu'aucun diff ne le montre. C'est
  arrive exactement (le garde-fou de rang est sorti rouge apres la correction), et `theo-archi`
  puis `nadia-prof` l'ont chacun mesure independamment avant de se rencontrer sur le meme chiffre
  (3/6/5, 42,9 % maximum) : une prediction qui se realise et une reconciliation qui concorde sans
  concertation prealable valent mieux qu'un incident evite par chance.
- **Sept chiffres corriges ce sprint l'ont ete par leur propre auteur, avant qu'un tiers ne les
  cite.** Six par `nadia-prof` (deux fois deux corrections de rendu hauteur/largeur, un
  depouillement refait, une esperance recalculee), un par `theo-archi` sur sa propre table de
  distribution (`ADR-0041`). Aucun n'a ete trouve par une relecture ou un audit externe : chacun
  vient de son auteur qui rejoue son propre travail avant de le citer comme preuve.
- **Une regle de garde-fou neuve (la voie 4) a ete mise a l'epreuve par mutation, pas seulement
  lue.** `yanis-qa` a etabli qu'elle mord bien sur l'etat reel refuse par `nadia-prof` et qu'elle
  reste verte a tort sur un contournement different (`white-space: nowrap`) - les deux faits sont
  mesures sur le fichier de production, jamais sur une fixture ecrite pour l'occasion.
- **`theo-archi` a decouple une clause de sa ceremonie de publication.** L'amendement du 2026-08-15
  a `ADR-0013` retire la dependance du plafond de dette a la republication de
  `confirmation-archunit.md` : "une clause dont l'execution depend d'une ceremonie n'est pas une
  clause". C'est la bonne reponse a quatre sprints d'absence, meme si la ceremonie elle-meme
  continue de manquer sa promesse cette fois encore (voir ci-dessous).
- **Un blocage circulaire a ete leve par un amendement ecrit, pas par un contournement tacite.**
  `camille-po` a explicitement amende `DEC-0031` pour permettre une reinstallation de verification
  hors catalogue, plutot que de laisser `nadia-prof` et le catalogue se bloquer mutuellement.

## Ce qui a coince

### 1. `confirmation-archunit.md` : publie plus tot pour eviter l'absorption de fin de sprint, et la section de cloture manque quand meme

`theo-archi` a publie ce fichier a l'**ouverture** du sprint plutot qu'a la review, exactement pour
eviter le mecanisme qui l'avait fait manquer quatre fois d'affilee (une ceremonie de fin de sprint
qui absorbe le temps qui reste). Le changement de calendrier n'a pas suffi : la section de cloture
qu'il annoncait lui-meme ("je l'amenderai a la review, sur l'arbre fusionne") n'a pas ete ecrite. Ce
n'est plus une absence de ceremonie, c'est une promesse non tenue a l'interieur d'une ceremonie qui
a eu lieu - une variante du meme defaut sous une forme que je n'avais pas anticipee en l'ecrivant
comme critere de cloture. Je ne fabrique pas cette section a la place de `theo-archi` ; je la route
a s14 (voir `review.md`).

### 2. Deux portes de sortie ecrites au planning n'ont jamais ete franchies, et personne ne l'a signale avant la cloture

S13-4 et S13-5 exigeaient une reverification de `nadia-prof` sur `FICHE-bandit-manchot-tables-
multiplication-cm1.md` apres la correction de `lea-dev` : elle n'a jamais eu lieu, et cette fiche
n'a plus ete touchee depuis s12. S12-5 exigeait un jugement de `nadia-prof` sur le retrait de
l'`aria-label` : aucun fichier de ce sprint ne le porte. Le code des deux est fusionne et vert
depuis plusieurs jours ; rien dans le rapport de `yanis-qa`, dans les fusions successives ni dans
mon propre suivi de sprint n'avait signale l'ecart avant que je ne verifie chaque porte de sortie
ligne par ligne a la cloture. Amendement ecrit dans `squad/README.md`, section 9 (voir plus bas).

### 3. Un commit de `nadia-prof` a existe une fois et n'existe plus pour le depot

`653b915` ("reserve B des tables - 0 sur 1x1 est un distracteur legitime, S13-5 se limite a 11 et
110") vit sur le worktree `s13-nadia-audit`, dont la tete n'est l'ancetre d'aucun commit de
`round-two` - a la difference du reste de ce meme worktree, capture par un commit different
(`a7ebb29`) qui porte le meme contenu sous un autre message. Le fond n'est pas perdu : `lea-dev` a
corrige exactement dans l'esprit de cette note. Mais la note elle-meme a disparu du depot alors que
son autrice l'a bien ecrite une fois - c'est la meme lecon que `theo-archi` a nommee ce sprint sur
le contenu mesure ("deposer un fichier dans un repertoire est un geste sans trace"), retrouvee ici
sur un commit de guidance pedagogique plutot que sur un fichier de mesure. Je verifie desormais la
lignee exacte (le commit, pas seulement l'equivalence de contenu) avant de conclure qu'un travail
est capture.

### 4. Une reserve datee porte un chiffre deja faux le jour de son ecriture, et rien ne l'a pris avant la relecture de ce matin

`nadia-prof` a ecrit, le 2026-08-15, que "les huit jeux partagent le meme gabarit" pour justifier
qu'une seule ecoute suffit a couvrir tout le catalogue. Le catalogue en comptait deja plus a cette
date et en compte onze aujourd'hui. Le raisonnement tient toujours (un seul jeu suffit a renseigner
le gabarit partage), mais le chiffre cite est faux depuis l'ecriture et personne, moi comprise, ne
l'avait remarque avant que `relecteur-pedagogique` ne le releve ce matin. Route a `nadia-prof` pour
correction par note datee (voir `review.md`).

### 5. Le rendu reste garde par une seule personne qui joue a 360 px, et ce sprint le prouve deux fois de plus

Vingt-neuf debordements (20 sur les grands nombres, 1 sur le perimetre, 8 sur les mesures) ont
traverse une CI verte a 487 puis 489 tests sans qu'aucun test ne bouge - `nadia-prof` le nomme
elle-meme ("aucun garde-fou ne connait la largeur d'un ecran") et `yanis-qa` le confirme par
mutation. Ce n'est pas un fait nouveau architecturalement (`ADR-0025` l'a deja pose), mais le risque
grandit avec le nombre de jeux au catalogue, et la voie 4 elle-meme a cree une propriete neuve
qu'aucun test ne garde (qu'une boite ne devienne pas un signal de longueur ou de largeur).

## Amendements a `squad/README.md`

Un amendement, ecrit dans le meme geste que cette cloture (section 9, apres le paragraphe sur la
review) : **une porte de sortie nommee au planning est verifiee physiquement par moi a la review,
au meme titre qu'un fichier de ceremonie.** Motif : les deux ecarts de la section "Ce qui a coince"
2 ci-dessus, qui n'auraient jamais ete vus sans une relecture ligne par ligne du tableau de stories
du planning contre l'etat reel du depot a la cloture.

## Decisions prises

1. **`review.md` est complete par moi** (porte technique rejouee, rapport qualite reference,
   decision sur `confirmation-archunit.md`, corrections du relecteur, tableau des obstacles,
   synthese des verdicts de `nadia-prof`), sans effacer ni reformuler ses blocs existants.
2. **`confirmation-archunit.md` n'est pas complete a la place de `theo-archi`.** L'absence de sa
   section de cloture est nommee comme un obstacle route a s14, pas fabriquee.
3. **Aucun worktree n'est supprime dans ce geste.** 23 des 24 worktrees de s13 sont verifies
   ancetres de `round-two` et supprimables sans perte ; le vingt-quatrieme (`s13-nadia-audit`) l'est
   presque entierement, a l'exception du commit `653b915` (voir ci-dessus).
4. **Un amendement est ecrit dans `squad/README.md`, section 9**, sur la verification physique des
   portes de sortie a la review (voir ci-dessus).
5. **Les gaps trouves sur S13-4/S13-5 et S12-5 sont routes a `nadia-prof`** pour s14, pas absorbes
   dans un Done que je n'ai pas l'autorite de prononcer.
6. **La mise a jour de `MATRICE-couverture.md` et de `squad/validation/FILE-ATTENTE.md`** selon les
   verdicts du 2026-08-16 (soir) reste a la charge de `camille-po`, non faite par moi.
