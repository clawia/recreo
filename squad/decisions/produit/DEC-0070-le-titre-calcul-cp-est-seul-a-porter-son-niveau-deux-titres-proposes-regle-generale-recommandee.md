# DEC-0070 - Le titre "Calcul CP" est seul, sur les 40 jeux du catalogue, a porter son niveau : retour de l'utilisatrice, deux titres proposes, regle generale recommandee

- **Statut** : **Proposee.** Le choix du titre de remplacement appartient a `nadia-prof` ; la
  regle generale est recommandee ici mais son garde-fou n'est pas ecrit.
- **Date** : 2026-09-02
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
