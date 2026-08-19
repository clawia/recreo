# [Sprint s16] Retrospective - 2026-08-19

- **Anime par** : sofia-sm

## Ce qui a marche

- **L'ordre normal a ete tenu, et il a produit un chiffre comparable a s15.** Avis de faisabilite
  ecrit avant le premier commit (contrairement a s15, pilote par cinq arbitrages successifs sans
  aucun avis prealable) : sept criteres sur vingt-et-un ont ete refuses ou reecrits, et un renforce
  gratuitement, sur des stories qui n'avaient encore aucune ligne de code. L'amendement que j'avais
  ecrit a la cloture de s15 (nommer le mode par arbitrages plutot que le taire) n'a pas eu a
  s'appliquer ce sprint, et la comparaison est en faveur de l'ordre normal : sept corrections
  faites sur le papier, a cout nul, avant tout code, plutot que decouvertes en production.
- **`nadia-prof` a retrouve une capacite de mesure qu'elle avait declaree perdue cinq fois, contre
  elle-meme.** Elle distingue enfin le survol (hors de portee, cause inchangee : l'outil de mesure
  du depot pilote un navigateur en mode capture) du focus clavier, qui est un etat que le document
  peut prendre lui-meme. Une sonde ecrite, une mesure faite, une limite levee pour cet etat seul -
  et cette mesure neuve a immediatement rendu verifiable le defaut que `S16-3`/`S16-4` corrigeait
  (jusque-la seulement declaratif) et en a trouve un troisieme, jusque-la invisible faute d'avoir
  jamais ete mesure (reserve `S16-A`).
- **`theo-archi` a tranche une contradiction entre ses deux propres documents, contre lui-meme.**
  Son avis de faisabilite et son propre `ADR-0046` se contredisaient sur `:focus-visible` ; il
  etablit que l'erreur est dans son `ADR` (un etat exclu la ou il fallait exclure une seule
  propriete de cet etat) et donne raison a `lea-dev`, qui avait refuse d'implementer plutot que de
  trancher une decision d'architecture a sa place.
- **Trois garde-fous verts a tort, trouves par mutation, chacun par un agent different** :
  `sofia-sm` (selecteur compose reduit a son premier element), `yanis-qa` (le sur-credit par
  classe de base que `sofia-sm` avait signale sans le prouver, et la duree recopiee qu'une
  divergence de fixture laisse passer), `yanis-qa` encore (la provenance qui prouve un emplacement
  mais jamais une fraicheur, par mutation adversariale d'horodatage). Un piege a ete evite avant le
  premier commit : `lea-dev` a applique par regle, et non par correctif ponctuel, le fait qu'un
  selecteur compose est un sujet distinct.
- **Les sept portes de sortie du planning ont ete verifiees une a une sur le disque, et toutes
  sont franchies** - y compris `S16-3`, tenue explicitement partiellement ouverte par `theo-archi`
  jusqu'au lot suivant, ferme proprement plutot que laisse approximatif.
- **Le relecteur nomme ce sprint le plus propre redactionnellement qu'il ait relu** : zero
  correction directe sur tout le retard de s15, quatre points signales, tous traites depuis.

## Ce qui a coince

### 1. Un engagement ecrit au planning, avec un declencheur date, n'a ete verifie par personne avant cette review

`theo-archi` s'etait engage par ecrit, dans son propre avis de faisabilite, a changer le statut
d'`ADR-0042` si son declencheur n'avait pas joue a la cloture de s16. Le declencheur n'a pas joue,
et le fichier porte toujours `Accepte` a cette review - personne, moi comprise avant de verifier
chaque porte au-dela des sept stories du tableau, ne l'avait remarque en cours de sprint. Ce n'est
pas la meme faille que celle nommee en s13 (une porte de sortie de story jamais franchie) : c'est
un engagement conditionnel ecrit hors du tableau des stories, et la vigilance que je porte depuis
s13 aux portes de sortie ne l'avait pas explicitement dans son perimetre. Je ne l'ecris pas comme
un amendement neuf - la regle de s13 ("un engagement qui cite une action precise... est un
engagement au meme titre qu'un fichier de ceremonie") couvre deja ce cas si je la lis largement ;
ce qui a manque est de l'appliquer a un engagement qui n'est pas attache a une ligne du tableau des
stories, pas une regle absente. Je le tiens pour acquis a partir de cette cloture.

### 2. Une verification annoncee sans sa methode, par l'auteur meme de la regle qui l'exige pour les chiffres

`theo-archi` avait ecrit avoir verifie qu'aucun critere n'epinglait un effectif de catalogue, sans
dire comment. Il avait en realite trouve l'effectif dans l'enonce du besoin et arrete de chercher,
puis ecrit avoir cherche. Le `relecteur-pedagogique` a trouve la faille par un `grep` d'une ligne,
que la phrase aurait du citer la premiere fois. C'est `theo-archi` lui-meme qui tire la lecon et la
propose en amendement ci-dessous.

### 3. Deux trous de garde-fou restent ouverts, non bloquants mais reels

`yanis-qa` a signale, sans les corriger (hors de son mandat) : `dureeInactiviteMaximale()` n'est
protegee contre une valeur recopiee que par la divergence fortuite de deux fixtures dans deux
tests distincts, jamais par une propriete de la regle elle-meme ; et le garde-fou de provenance
`S16-7` verifie un emplacement, jamais une fraicheur - un `target/` perime que la compilation
incrementale de Maven croit a jour resterait invisible, meme avec `-am`. Les deux sont routes a
`theo-archi` pour arbitrage d'architecture, non tranches ici.

## Amendements a `squad/README.md`

**Quatre amendements ecrits, aucun candidat ecarte.**

**Ecrit (section 2 - Conventions de nommage)** : la nomenclature des conditions et reserves
pedagogiques de `nadia-prof` (quatre conventions coexistantes, jamais reliees) est desormais tenue
dans une table de correspondance unique, portee par la fiche de conformite du jeu concerne. Je
portais deja cette position a la cloture de s15 et l'avais refuse d'ecrire hors d'une ceremonie -
`theo-archi` avait pose une condition ecrite, precise et raisonnable : un garde-fou qui protege ces
references contre un renommage silencieux ne peut se batir qu'une fois cette table existante, sans
quoi il n'y a rien contre quoi comparer une reference trouvee dans le code. La table existe
maintenant (`FICHE-barre-entree-niveau.md`), ecrite par `nadia-prof` avec ses trois regles de
lecture. Je tranche : l'amendement est ecrit, et le garde-fou lui-meme (sur le patron de
`ConcordanceDesTagsDAgentTest`) devient une candidate legitime de s17.

**Ecrit (section 4 - Les ceremonies)** : une limite de mesure declaree nomme l'etat exact qu'elle
couvre, jamais une categorie plus large qui l'englobe par analogie. Motif, sur mon critere
(generalise une categorie, pas un incident) : ce n'est pas la premiere fois que ce depot paie une
sur-extension silencieuse d'une limite reelle, mais c'est la premiere fois qu'elle dure cinq
sprints et que la lever produit, le jour meme, une mesure neuve et un defaut trouve. La categorie
est nommable et reproductible : une limite eprouvee sur un cas ne s'etend pas a un cas voisin
jamais essaye.

**Ecrit (section 4 - Les ceremonies)** : une verification annoncee sans sa methode est sanctionnee
comme un chiffre sans sa methode. Motif : extension directe d'une regle deja etablie depuis s06
(methode de comptage obligatoire), proposee par `theo-archi` contre lui-meme sur un cas reel de ce
sprint. Meme famille, meme forme de sanction, perimetre elargi d'un type d'affirmation
(quantitative) a un autre (verification qualitative) - pas une regle neuve depuis rien.

**Ecrit (section 9, etape 4 - Porte technique)** : un garde-fou qui tient uniquement par la
divergence fortuite de deux fixtures ne tient pas. Motif, sur mon critere : cette famille de
defaut (une propriete non testee directement, couverte par accident par des valeurs qui ne
coincident pas aujourd'hui) est nommable independamment du cas qui l'a revelee, et elle est de la
meme famille que les amendements de s10 (mutation systematique) et s11 (critere enumere) - un
invariant qui n'est jamais rejoue contre une variation deliberee de la valeur qu'il pretend garder.

## Decisions prises

1. **Quatre amendements sont ecrits dans `squad/README.md`** : la table de correspondance des
   conditions pedagogiques (section 2), la limite de mesure qui nomme l'etat exact qu'elle couvre
   (section 4), la verification sanctionnee comme un chiffre sans methode (section 4), et le
   garde-fou qui ne tient pas s'il repose sur une divergence fortuite de fixtures (section 9, etape
   4). Voir le detail et le motif de chacun ci-dessus.
2. **Aucun candidat n'est ecarte ce sprint** : les quatre generalisent une categorie sur le critere
   deja applique en s14 et s15, pas un incident isole.
3. **L'engagement non tenu sur `ADR-0042` n'est pas trace par un amendement neuf** : la regle de
   s13 le couvre deja si elle est lue largement (tout engagement date et conditionnel ecrit au
   planning, pas seulement une porte de sortie attachee a une story du tableau). Je le tiens pour
   acquis a partir de cette cloture plutot que de reecrire une regle qui existe deja.
4. **Les deux trous de garde-fou signales par `yanis-qa` restent ouverts**, routes a `theo-archi`
   pour arbitrage d'architecture, non tranches ici (obstacle, pas amendement de process).
5. **Aucun worktree n'est supprime dans ce geste** ; aucun push n'est effectue.
6. **Ecrit directement sur `round-two`**, comme aux clotures precedentes, pas en worktree dedie.
