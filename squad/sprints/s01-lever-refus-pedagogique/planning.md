# [Sprint s01] Planning - 2026-08-05

- **Horodatage de debut** : 2026-08-05 13:03
- **Anime par** : sofia-sm
- **Participants** : theo-archi, camille-po, marc-dev, lea-dev, ines-design, sofia-sm

## Objectif du sprint

Lever le refus pedagogique prononce par nadia-prof sur bandit-manchot-additions-cp en corrigeant
ses trois motifs de refus (S1-1, S1-2, S1-3 du backlog), pour que l'increment repasse devant
nadia-prof en fin de sprint. Objectif gele : il ne change plus pendant le sprint.

## Stories retenues

| Story | Titre | Competence programme visee | Decision liee | Affectee a |
|---|---|---|---|---|
| S1-1 | Un texte que l'enfant peut lire sans apprendre une faute (motif de refus 1) | transverse | DEC-0002 (amendement du 2026-08-04) | marc-dev |
| S1-2 | Un score que l'enfant de CP sait lire (motif de refus 2) | transverse | - | marc-dev |
| S1-3 | Un ecran franchissable quand quelque chose tourne mal (motif de refus 3) | transverse | ADR-0011 | marc-dev |
| S1-4 | Une partie qui a une fin et une sortie (reserve 1) | transverse | ADR-0012 | lea-dev |
| S1-5 | Un enfant qui echoue plusieurs fois est aide, pas enferme (reserve 3) | transverse | - | lea-dev |
| S1-6 | Un signal juste/faux qui ne depend pas de distinguer les couleurs (reserve 4) | transverse | - | ines-design |
| S1-7 | Des additions qui font vraiment calculer (reserve 2) | mathematiques, CP - Nombres et calculs, calcul mental additions limite 10 | - | lea-dev |
| S1-8 | Des mots d'interface que l'enfant de CP comprend (reserve 6) | transverse | - | marc-dev |
| S1-9 | Un calcul et un signal plus grands que les boutons (reserve 5) | transverse | - | ines-design |

Cinq de ces stories (S1-2, S1-3, S1-4, S1-5, S1-7) attendent un prealable produit avant de
demarrer : voir "Prochaines etapes". Aucune `DEC` n'existe encore pour ces cinq points a l'heure
ou ce planning est ecrit ; la colonne ci-dessus ne peut donc pas les citer sans les inventer.

## Avis de faisabilite - theo-archi

Reproduit depuis `squad/sprints/s01-lever-refus-pedagogique/avis-faisabilite.md`.

| Story | Avis | ADR requis | Point a trancher hors architecture |
|---|---|---|---|
| S1-1 | Faisable en l'etat | aucun | perimetre exact du controle automatique de redaction - route relecteur-pedagogique / yanis-qa |
| S1-2 | Faisable en l'etat | aucun | forme exacte du score (etoiles, jetons, nombre seul) - route camille-po / nadia-prof |
| S1-3 | Faisable moyennant ADR | ADR-0011 (cree) | libelle exact du message d'erreur et du bouton - route camille-po / nadia-prof |
| S1-4 | Faisable moyennant ADR, un critere nuance | ADR-0012 (cree) | nombre exact de manches (Nadia suggere 10) ; formulation du message de reprise - route camille-po |
| S1-5 | Faisable en l'etat pour l'indice ; pour la "manche plus simple", faisable sans toucher aux contrats selon l'interpretation retenue | aucun si l'interpretation la plus simple est retenue | ce qu'on entend par "plus simple" est une decision pedagogique - route camille-po / nadia-prof |
| S1-6 | Faisable techniquement ; le fond est perceptif | aucun | absence d'agent design - obstacle du sprint 0, route sofia-sm |
| S1-7 | Faisable en l'etat | aucun | le critere ne fixe aucun seuil chiffre ("nettement inferieure au tiers") - route camille-po pour le rendre testable |
| S1-8 | Faisable en l'etat | aucun | idem S1-1, controle de redaction - route relecteur-pedagogique |
| S1-9 | Faisable techniquement ; le fond est perceptif | aucun | absence d'agent design - route sofia-sm |

Deux ADR nouveaux ce sprint, deja crees a l'heure ou ce planning est ecrit : `ADR-0011` (rendre
toute erreur imprevue en HTML francais, pour S1-3) et `ADR-0012` (la longueur de partie vit dans le
catalogue, pour S1-4). `ADR-0008` a ete amende (renvoi ajoute sur le chemin d'exception, sans
changer ses trois gardes existantes). Les sept autres stories ne demandent aucun ADR.

## Criteres testables vs intestables par construction (premiere application de l'amendement retro s00)

La retrospective du sprint 0 a retenu que l'avis de faisabilite doit distinguer, pour chaque
story, ce qu'un automate peut verifier de ce qui reste un jugement humain - plutot que de laisser
cette distinction emerger seulement au rapport qualite. `theo-archi` l'a fait pour la premiere fois
dans son avis de ce sprint ; je la reprends ici pour qu'elle guide le developpement des le depart.

| Story | Testable par automate | Intestable par construction |
|---|---|---|
| S1-1 | Absence litterale des trois fautes connues ("ca", "reponse", "etait" sans accent) dans les trois gabarits, en comparaison de sous-chaine | "Aucune faute d'orthographe ou de ponctuation" en toute generalite - jugement du relecteur-pedagogique a chaque livrable |
| S1-2 | Absence du caractere "/" et de la formulation "reussites sur tentatives" dans le fragment de score (jsoup), absence des symboles +, -, x, / | Si la forme retenue est iconographique (jeton, etoile), qu'elle n'evoque pas visuellement un signe operatoire - jugement visuel |
| S1-3 | Statut HTTP 200, absence des marqueurs techniques ("status", "timestamp", "Internal Server Error"), presence d'un bouton vers l'accueil (test d'integration nomme par ADR-0011) | "Message court" et "francais correctement accentue" en toute generalite - meme limite que S1-1 |
| S1-4 | Terminaison au bon compte de manches (seed fixe), reprise apres un second GET / dans la meme session, visibilite du bouton d'accueil sur chaque fragment | Que le compte de manches restant soit annonce de facon comprehensible pour un enfant de CP - jugement de nadia-prof |
| S1-5 | Presence effective d'un indice au bon moment, declenchement de la manche de reprise au seuil, persistance du bouton de sortie pendant une serie d'echecs | Que l'aide aide reellement l'enfant plutot que de le frustrer davantage - jugement pedagogique |
| S1-6 | Les deux formes (coche, croix) sont structurellement differentes (deux jeux de regles CSS distincts) | Qu'un enfant de six ans, daltonien ou non, reconnaisse les deux formes comme "juste" et "faux" - jugement perceptif, aucun agent n'avait mandat dessus avant ce sprint |
| S1-7 | Proportion d'operandes nuls sur N tirages, graine fixe - a condition qu'un seuil chiffre remplace "nettement inferieure au tiers" | Rien d'intestable une fois le seuil fixe ; le point bloquant est l'absence du seuil, pas la testabilite |
| S1-8 | Les cinq remplacements nommes par comparaison de sous-chaine litterale sur les gabarits et sur catalogue.yaml | "Vocabulaire courant de CP" en toute generalite - jugement de nadia-prof / relecteur-pedagogique |
| S1-9 | Les valeurs declarees dans recreo.css (au moins 2 rem, au moins 3 rem), lues par comparaison de valeur sur le fichier texte | Que le texte apparaisse effectivement plus grand a l'ecran compte tenu des reglages d'accessibilite du navigateur de l'enfant - hors de portee de mvn verify |

## Obstacles herites du sprint 0

| Obstacle | Etat a l'ouverture de ce sprint |
|---|---|
| `DifficulteTest.leContratDeContenuNePorteQueDuTexte` admet encore un enum arithmetique place dans le paquet de contrat lui-meme | Ouvert. theo-archi ne le corrige pas hors pression d'un besoin reel ; si S1-4 ou S1-5 introduit un enum dans `domain.content`, le trou doit etre ferme dans le meme commit, verifie a la review. |
| Increment `bandit-manchot-additions-cp` non valide pedagogiquement | Ouvert, dans `squad/validation/FILE-ATTENTE.md` (1 increment en attente, sous le seuil de gel de 2-3). C'est l'objet meme de ce sprint. |
| Absence de mandat design (accessibilite visuelle, tailles, semantique des formes) | Clos ce sprint. `ines-design` est cree et enregistre (commit 64e23bd), prefixe `[INES]` present dans le garde-fou de CI et dans son test, 15 cas verts. Le fichier de decision (retro du sprint 0, section Amendements au process) existe physiquement sur le disque : l'obstacle est bien clos, pas seulement annonce. |

## Decisions prises

Deux ADR ont ete crees pendant ce planning par theo-archi, dans le cadre de son avis de
faisabilite (README section 9, etape 1) :

- `ADR-0011` - Rendre toute erreur imprevue en HTML francais, jamais un corps technique brut.
  Statut : Accepte.
- `ADR-0012` - Porter la longueur d'une partie par l'entree de catalogue, jamais par le contenu.
  Statut : Accepte.

`ADR-0008` a ete amende (ajout d'un renvoi vers `ADR-0011` sur le chemin d'exception, sans
modification de ses trois gardes existantes).

Aucune `DEC` n'est encore ecrite a l'heure ou ce planning est redige : camille-po tranche en
parallele les cinq points produit routes par theo-archi (forme du score de S1-2, libelles d'erreur
de S1-3, nombre de manches de S1-4, sens de "manche plus simple" de S1-5, seuil chiffre de S1-7).
Ces decisions ne sont pas anticipees ici ; elles seront nommees dans review.md et RAPPORT.md une
fois leur fichier ecrit.

## Prochaines etapes

**Deja en cours pendant que ce planning est ecrit** :

- `ines-design` developpe S1-6 et S1-9 (signal coche/croix independant de la couleur, tailles de
  l'enonce et du signal) dans `adapters/adapter-web/src/main/resources/`.
- `camille-po` tranche les cinq points produit routes par theo-archi, prealables a S1-2, S1-3,
  S1-4, S1-5, S1-7.

**Des que camille-po a ecrit ses decisions** :

- `marc-dev` demarre S1-1, S1-2, S1-3, S1-8 : le texte lu par l'enfant, la forme du score, l'ecran
  d'erreur d'`ADR-0011`.
- `lea-dev` demarre S1-4, S1-5, S1-7 : la longueur de partie d'`ADR-0012` et sa repercussion sur
  `GameDefinition`, le chargeur et le catalogue, l'indice et la manche plus simple, la proportion
  d'operandes nuls.
- `sofia-sm`, casquette dev : aucune tache de code ce sprint, comme au sprint 0. Elle tient les
  ceremonies et le routage des obstacles.

**Changement assume par rapport au sprint 0** : ce sprint est parallelise. Le motif qui avait
impose une execution serie au sprint 0 - un arbre de travail partage - est leve : `marc-dev`,
`lea-dev` et `ines-design` travaillent chacun dans un worktree git isole. Cout accepte : la fusion
des worktrees revient a l'orchestration de ce sprint, pas a un agent en particulier, et un
`mvn verify` vert dans un worktree ne prouve que ce worktree - la porte technique reelle ne peut
etre jugee qu'une fois les worktrees fusionnes dans un seul arbre.

**Une fois les trois worktrees fusionnes** :

1. `yanis-qa` fait tourner `mvn verify` sur l'arbre fusionne et ecrit le rapport qualite.
2. `theo-archi` confirme ArchUnit sur ce meme arbre, avec le tableau regle par regle exige depuis
   l'amendement de process du sprint 0 (regle, ADR, contre-exemple, test de non-vacuite), en
   particulier pour les deux regles nouvelles d'`ADR-0011`.
3. `relecteur-pedagogique` releve les livrables non executables, y compris le texte de categorie B
   (S1-1, S1-8), mandat explicite depuis l'amendement de `DEC-0002`.
4. `nadia-prof` rejoue l'increment et prononce son verdict dans
   `squad/conformite/FICHE-bandit-manchot-additions-cp.md`.
