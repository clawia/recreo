# DEC-0070 - Le titre "Calcul CP" devient "Les petites additions" : retour de l'utilisatrice, choix tranche par nadia-prof, recommandation de regle generale revisee sur deux points distincts

- **Statut** : **Acceptee.** Titre tranche par `nadia-prof` le 2026-09-02 (amendement ci-dessous) :
  `"Les petites additions"`, l'option A. La partie "regle generale" reste **recommandee, forme
  technique non ecrite** - routee a `theo-archi`, instruite apres le garde-fou de couleur (voir
  amendement).
- **Date** : 2026-09-02 (amendee le 2026-09-02, meme jour)
- **Auteur** : `camille-po`
- **Sprint** : hors-sprint (branche `retours-utilisatrice`, avant ouverture de s27)
- **Retour qui la fonde** : l'utilisatrice, 2026-09-02, en jouant l'application, verbatim :
  *"le premier exercice s'appelle calcul CP alors que les autres jeux n'ont pas le niveau dans le
  titre, il faut l'adapter"*.
- **Competence programme visee** : mathematiques, CP (cycle 2), domaine "Nombres et calculs" -
  "Calcul mental - additions dans la limite de 10" (`squad/programme/mathematiques/competences-cp.md`,
  ligne 11) pour le jeu concerne ; la regle recommandee en section 3 est **transverse - pas de
  contenu pedagogique**.

---

## 1. Le fait, verifie

`adapters/adapter-content/src/main/resources/catalogue.yaml`, ligne 4 : `titre: "Calcul CP"`,
jeu `bandit-manchot-additions-cp`. **Sur les 40 titres du catalogue, c'est le seul qui contient
`CP` ou `CM1`** - verification exhaustive sur les 40 entrees du fichier, aucune autre occurrence.

Le depot porte deja, ailleurs, une regle qui va dans le meme sens sans avoir jamais ete appliquee
au titre d'un jeu. `adapters/adapter-web/src/main/resources/templates/accueil.html`, ligne 110,
commentaire lie a `BARRE-5` et `ADR-0022` : le libelle du filtre de niveau est *"Pour le CP"*,
jamais *"Les jeux pour le CP"*, precisement pour ne pas repeter un mot de sortie deja porte
ailleurs sur l'ecran. Le principe sous-jacent - ne pas faire dire deux fois la meme chose a deux
endroits de l'ecran quand l'un des deux suffit - s'applique tout autant a un titre de jeu affiche
sous un en-tete qui annonce deja le niveau : c'est une redite, pas une precision.

**Ce defaut existe depuis le premier jeu du produit** (`docs/backlog/backlog.md`, story `S0-4`,
sprint 0) et n'a ete releve par aucun des neuf agents en 26 sprints. C'est l'utilisatrice, en
jouant, qui l'a vu.

---

## 2. Deux titres de remplacement, proposes et non tranches

Je propose, je ne tranche pas : ce texte est lu par un enfant de CP, et le choix appartient a
`nadia-prof`. J'ai regarde les six autres titres de mathematiques CP du catalogue avant de
proposer, pour rester dans le meme registre et ne collisionner avec aucun (`Les petites
soustractions`, `Petits problèmes`, `Compare les nombres`, `Les compléments à 10`, `Les mesures`,
`La monnaie`, `La journée et la semaine`) :

### Option A - "Les petites additions"

- **Motif** : echo structurel exact de `Les petites soustractions`, jeu voisin sur la meme ligne
  de domaine ("Nombres et calculs"), meme borne ("dans la limite de 10" pour les deux -
  `cp-additions-jusqu-a-10.yaml`, `parametres.sommeMaximale: 10` ; `cp-soustractions.yaml`,
  competence "soustractions dans la limite de 10"). Meme registre, meme construction
  grammaticale, pas de collision.
- **Le depot porte deja ce nom pour ce contenu, avant meme que le jeu existe** : la story qui a
  cree ce jeu s'appelle `S0-4 - S'entrainer aux petites additions en jouant`
  (`docs/backlog/backlog.md`), et son besoin est ecrit *"je veux m'entrainer sur des petites
  additions [...] en jouant au bandit manchot"*. Le titre de remplacement ne serait pas invente :
  il reprendrait un mot que le depot utilise deja pour designer ce contenu.

### Option B - "Additionne jusqu'à 10"

- **Motif** : registre imperatif, precedent dans le catalogue (`Compare les nombres`, `Range les
  nombres`, `Écris le complément à 10`). Nomme l'operation et sa borne exacte
  (`sommeMaximale: 10`) sans jugement de difficulte porte par le mot "petites", au cas ou
  `nadia-prof` le jugerait imprecis ou minorant pour un enfant qui vient d'apprendre a compter
  jusqu'a 10.

**Je recommande l'option A**, pour la double correspondance (jeu voisin + story fondatrice), mais
les deux sont proposees sans arbitrage de ma part.

---

## 3. Cas isole ou regle generale ? Je recommande une regle, executable

**Ce n'est pas seulement un titre a corriger : c'est une proprete du catalogue qui n'a jamais ete
posee, et le catalogue grandit a chaque sprint.** Trois faits me font recommander une regle plutot
qu'un correctif isole :

1. **Rien, aujourd'hui, n'empeche un titre futur de porter son niveau.** Le champ `titre` de
   `catalogue.yaml` n'est contraint par aucun test ni aucun schema sur ce point ; seule
   l'accentuation et l'orthographe du texte enfant sont verifiees (`relecteur-pedagogique`, sous
   `DEC-0002`).
2. **Le defaut a survecu 26 sprints sans etre vu par l'equipe**, precisement parce qu'il ne se
   detecte qu'en relisant les 40 titres cote a cote - ce qu'aucun agent ne fait a chaque ajout de
   paquet. C'est la meme famille de defaut que `CLAUDE.md` nomme deja pour d'autres sujets
   ("un garde-fou annonce mais absent") : un principe qui existe dans un commentaire
   (`accueil.html`, ligne 110) sans jamais avoir ete rendu executable sur le catalogue.
   `squad/README.md` et ce depot tiennent qu'une regle non executable est un souhait.
3. **Le cout d'un garde-fou est bas** : c'est une verification de donnee (aucun titre ne contient
   les jetons `CP` ou `CM1`), pas une regle d'architecture Java - donc pas necessairement un test
   ArchUnit, mais un test de contenu du meme esprit que `verifier-chemins.sh`.

**Je recommande donc la regle : aucun titre de jeu ne porte son niveau scolaire.** Je n'en decide
pas la forme technique (test JUnit sur le module `adapter-content`, script au meme titre que
`verifier-chemins.sh`, ou autre) : c'est une question technique, routee a `sofia-sm` puis
`theo-archi`.

---

## 4. Ce que ce document demande, et a qui

| Ce qui est du | Proprietaire | Etat |
|---|---|---|
| Choisir entre l'option A et l'option B (ou proposer un autre titre), et prononcer le jugement pedagogique sur le texte retenu | `nadia-prof` | **Pose ici, non tranche** |
| Story de correction du titre dans `catalogue.yaml`, une fois le titre choisi | `camille-po` (ecrite), `sofia-sm` (planification), `marc-dev`/`lea-dev`/`sofia-sm` (developpement) | **Ecrite** ci-dessous dans `docs/backlog/backlog.md`, non affectee a un sprint |
| Forme technique du garde-fou "aucun titre de jeu ne porte son niveau" | `sofia-sm` puis `theo-archi` | **Route**, recommandation en section 3 |

---

## 5. Ce que ce document ne fait pas

- Il ne choisit pas le titre final : c'est `nadia-prof`.
- Il ne decide d'aucune forme technique pour le garde-fou : c'est `theo-archi`.
- Il ne fait passer aucune ligne de la matrice de couverture a un autre statut : cette ligne est
  deja `couvert-valide` et rien dans ce retour ne remet en cause le jugement pedagogique deja
  rendu sur le contenu du jeu, seulement sur son titre.
- Il ne touche pas au second retour de l'utilisatrice sur la monnaie, instruit separement.


---

## Amendement du 2026-09-02 (meme jour) - le titre est tranche, deux motifs que je n'avais pas, et ma recommandation de regle generale se revise

`nadia-prof` a rendu son arbitrage :
`squad/conformite/ARBITRAGE-s27-titre-du-jeu-d-additions-et-portee-de-ma-clause-sur-la-couleur.md`,
section 1 (branche `matieres-absentes`, commit `fcdc365`, fusionnee dans `round-two`).

### A.1 Le titre retenu, et ce qu'elle a verifie que je n'avais pas fait

**"Les petites additions" - l'option A, ma recommandation, suivie.** Mais `nadia-prof` a fait un
geste que je n'avais pas fait : elle a releve **les quarante titres**, pas les six voisins de
mathematiques CP, et le releve complet lui a appris un fait que ma proposition n'avait pas vu :

**L'imperatif n'est pas un registre libre dans ce catalogue : il fait deja un travail.** Le paquet
de saisie des complements s'appelle `"Ecris le complement a 10"`, son jumeau en choix
`"Les complements a 10"` ; le second jeu de saisie s'appelle `"Ecris le mot dicte"`. **Le verbe a
l'imperatif est ce qui distingue, a l'ecran d'accueil, le jumeau de saisie de son jumeau de choix.**
C'est le motif principal de l'ecartement de mon option B (`"Additionne jusqu'a 10"`) : poser un
imperatif sur un jeu de choix affaiblirait un signal qui fonctionne deja pour l'enfant.

**Un second motif, que je n'avais pas non plus, ecarte l'option B** : elle grave une borne que la
competence annonce comme provisoire - *"additions dans la limite de 10 **(puis 20)**"*. Le jour ou
la borne 20 entre au perimetre de ce jeu, `"Additionne jusqu'a 10"` devient faux. `"Les petites
soustractions"` avait deja evite ce piege en disant *petites* plutot qu'un nombre ; `nadia-prof`
applique la meme regle au jeu frere.

**Aucun rejeu n'est du.** Le Done pedagogique du 2026-08-06 sur `bandit-manchot-additions-cp`
porte sur les items ; aucun item, aucune reponse, aucun distracteur ne bouge. C'est un changement
de titre, pas de contenu.

### A.2 Le second defaut de "Calcul CP", que ni l'utilisatrice ni moi n'avions nomme

**"Calcul CP" echouait sur deux points independants, pas un seul, et retirer le seul "CP" aurait
laisse le second en place.**

1. **Le niveau** - le seul defaut que l'utilisatrice a releve, et le seul que j'avais instruit.
2. **La sous-specification** - *calcul* couvre l'addition **et** la soustraction, alors que ce jeu
   ne sert que des additions et que son jeu frere est nomme par son operation
   (`"Les petites soustractions"`). Le titre promettait plus large que le contenu ne tenait.
   `"Les petites additions"` corrige les deux points a la fois : il ne porte plus le niveau, et il
   nomme l'operation exacte, comme son jumeau.

### A.3 Ma recommandation de regle generale change : une regle en attrape la moitie

**Ma section 3 ne tient plus telle quelle.** J'y recommandais une seule regle - *aucun titre de
jeu ne porte son niveau scolaire* - au motif que c'etait le defaut trouve. **Ce defaut avait une
seconde moitie que cette regle n'aurait jamais attrapee** : un titre qui ne porte aucun niveau mais
qui reste plus large que le contenu servi (par exemple un "Calcul CM1" renomme "Calcul" resterait
sous-specifie) passerait cette regle sans probleme.

**Je maintiens la premiere regle, executable au meme cout que decrit en section 3** : aucun titre
de jeu ne porte les jetons `CP` ou `CM1`. Verification de donnee, cout bas, routee a `theo-archi`.

**J'ajoute une seconde propriete, et je suis honnete sur sa nature : elle n'est pas mecanisable au
meme titre.** *Un titre ne nomme jamais une categorie plus large que ce que le contenu sert
reellement* (l'operation, le champ lexical, la competence precise). Verifier cette propriete
suppose de comparer le sens d'un titre au contenu reel d'un paquet - un jugement, pas une
correspondance de jetons. Je ne sais pas si un proxy mecanique existe (par exemple, confronter un
mot d'operation dans le titre au champ `type` ou `competence` d'un contenu structure comme
`additions-bornees`) : **je pose la question a `theo-archi` plutot que d'en presumer la reponse**,
sans exiger qu'elle recoive un garde-fou si aucun proxy fiable n'existe. A defaut d'un garde-fou
executable, cette propriete devient un point de controle explicite pour `nadia-prof` (et pour
`relecteur-pedagogique` sous ses criteres) au moment ou tout titre est propose ou modifie - une
regle non executable est un souhait, mais un souhait ecrit et verifie a la main a chaque decision
reste superieur a un souhait qui ne l'est meme pas.

**Routage** : forme technique de la premiere regle a `theo-archi`, deja saisi par `sofia-sm`,
instruite apres le garde-fou de couleur. Faisabilite d'un proxy pour la seconde propriete posee a
`theo-archi` comme question ouverte, pas comme une demande.

### A.4 Ce que cet amendement ne fait pas

- Il ne redecide pas le titre : `nadia-prof` l'a fait, section A.1.
- Il ne fixe aucune forme technique de garde-fou.
- Il ne touche a aucune ligne de la matrice de couverture : la ligne "Calcul mental - additions
  dans la limite de 10" reste `couvert-valide`, le titre n'etant pas une propriete de statut.
