# Fiche de conformite - bandit-manchot-texte-documentaire-ce1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-texte-documentaire.yaml` (20 items, type `question`,
  texte support ; 11 documentaires, 9 consignes), items 5, 9 et 17 corriges par `marc-dev` (`ac986aca`, fusion `afd33e88`).
- **Entree de catalogue** : `bandit-manchot-texte-documentaire-ce1`, titre servi *"Lis pour savoir"*, consigne servie *"Lis le
  petit texte, puis réponds à la question."*, `nombreDeManches: 10`.
- **Ligne declaree** : `FRA-CE1-07`. **Story** : `S57-BR-FRA-CE1-07`.
- **Competence programme visee** : `squad/programme/francais/competences-ce1.md`, `FRA-CE1-07`, *"Comprendre un texte
  documentaire court ou une consigne écrite, et y prélever une information."*
- **Conditions opposables** : `DOC-CE1-1` a `DOC-CE1-3` (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`, 3.7) ; communes de
  `arbitrage-nadia-preparation-palier-3-ce1.md` 4.1 et 4.2, dont le jeu texte masque de chaque item ; corrections de
  `lecture-nadia-palier-3.md` (section 2) et de `verdict-nadia-palier-3-ce1.md` (section 4.4).
- **Statut global** : **VALIDE**, prononce le 2026-10-21 par `nadia-prof` (s57, palier 3), apres un refus du meme jour. Fiche
  ouverte par ce verdict.
- **Derniere mise a jour** : 2026-10-21.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

### Refus du 2026-10-21 (`nadia-prof`, s57, palier 3) : **REFUSE**, leve le meme jour

Items 5, 9 et 17 resolus sans lire le texte (culture, logique du geste) : condition du jeu texte masque non tenue.
Motif et corrections : `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/verdict-nadia-palier-3-ce1.md`, section 4.

### Verdict du 2026-10-21 (`nadia-prof`, s57, palier 3) : **VALIDE**

**Done pedagogique prononce** sur `round-two` a `ea5d9429` (YAML de blob `5f3e6c95`). `camille-po` peut passer `FRA-CE1-07` a
`couvert-valide`.

**Ce qui fonde le verdict** : `verdict-nadia-palier-3-ce1.md`, sections 4 et les deux sections `-07` qui suivent la
rectification. Audit en partie degrade, declare : jeu du pilote d'`ines-design` (`releve-ecran-ines-palier-3.md`, section A,
`56ce720e` ; sections I a L, `89f4afeb`), lu en texte DOM et sur 27 captures ; jar non lance par moi.

**Verifie** : lecture complete (`7301cbbc`) ; 20 items joues texte masque, aucun ne se resout sans lire ; bonne reponse et au
moins un distracteur dans chaque texte (`DOC-CE1-2`) ; aucun fait faux (`DOC-CE1-3`), deux generalisations acceptees au CE1
(*"Le pull se fabrique avec de la laine"*, *"Le cheval dort debout"*) ; texte rendu egal au fichier ; indice qui designe le mot
ou la phrase ; pli : 0 item sur 20 apres une erreur ; revelation de la bonne reponse apres deux erreurs.

**Aucune reserve.**

**Je reste la seule garde** de ce qu'un item ne se reponde pas sans le texte : aucun test ne lit la culture d'un enfant, et
l'etat masque n'existe pas dans le produit.

**Signe** : `nadia-prof`, 2026-10-21.
