# [Sprint s01] Relecture pedagogique et QA de contenu - relecteur-pedagogique

- **Date** : 2026-08-05
- **Auteur** : relecteur-pedagogique
- **Livrables lus** : `squad/sprints/s01-lever-refus-pedagogique/planning.md`,
  `squad/sprints/s01-lever-refus-pedagogique/avis-faisabilite.md`,
  `squad/decisions/produit/DEC-0004-textes-score-et-seuils-lever-refus-s01.md`,
  `squad/decisions/produit/DEC-0005-indice-s1-5-vit-dans-le-yaml-pas-dans-le-domaine.md`,
  `squad/decisions/adr/ADR-0011-rendre-toute-erreur-imprevue-en-html-francais.md`,
  `squad/decisions/adr/ADR-0012-la-longueur-de-partie-vit-dans-le-catalogue.md`,
  `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, `CLAUDE.md`, `docs/backlog/backlog.md`
  (section sprint 1), et le code de production touche par s01 (`PartieController`,
  `GestionnaireDesErreurs`, `BanditManchotSession`, `BanditManchot`, `GameMechanic`,
  `GameDefinition`, `Question`, `YamlContentPackLoader`, `AdditionsBorneesContentSource`, les
  gabarits `accueil.html`, `fragments/manche.html`, `fragments/retour.html`, `fragments/erreur.html`,
  `catalogue.yaml`) et les tests associes.

## Methode

Pour chaque livrable non executable, verification des conventions de redaction de `CLAUDE.md`
(tirets simples, guillemets droits, absence d'emoji ou de symbole Unicode decoratif, absence de
commentaire qui paraphrase le code, absence de chemin absolu de poste de travail ou de nom
d'utilisateur), puis verification que la doc decrit bien ce que le code fait (Javadoc contre
implementation, ADR contre regle ArchUnit ecrite, DEC contre gabarit rendu).

## Ce qui a ete verifie et est conforme

- Aucune occurrence de tiret cadratin, de guillemet typographique ou d'emoji dans `planning.md`,
  `avis-faisabilite.md`, `DEC-0004`, `DEC-0005`, `ADR-0011`, `ADR-0012`, la `FICHE` et `CLAUDE.md`
  (recherche par expression sur les caracteres `‘ ’ “ ” – —` et sur la plage Unicode des
  pictogrammes decoratifs : aucune correspondance).
- Aucun chemin absolu de poste de travail ni nom d'utilisateur dans ces memes fichiers. Les seules
  occurrences de `C:\...` ou `/c/...` du depot sont dans `ADR-0010`, qui les cite comme motifs
  interdits a des fins d'exemple (pre-existant, hors perimetre de ce sprint) - conforme a l'usage
  attendu, rien a corriger.
- Les Javadoc introduites ou modifiees ce sprint decrivent fidelement le comportement reel, verifie
  contre le code : `GestionnaireDesErreurs` (ADR-0011) correspond exactement a
  `GestionDesErreursTest` et au gabarit `fragments/erreur.html` ; `Question` et `BanditManchotSession`
  documentent correctement le mecanisme de l'indice et de la "manche plus simple" tel qu'implemente ;
  `GameDefinition` et `GameMechanic` documentent correctement `nombreDeManches` conforme a
  `ADR-0012` ; `TexteAccentueDesGabaritsTest` fait exactement ce que sa Javadoc annonce (filet de
  regression, pas une preuve d'exactitude, formule identique a celle d'`avis-faisabilite.md`).
- Aucun commentaire de code releve ne paraphrase une ligne qu'il surplombe ; chaque commentaire
  ajoute une raison (ADR, DEC, story) que la signature seule ne porte pas.
- Coherence terminologique : "manche", "partie", "jeu", "contenu", "mecanique" sont employes de
  facon stable entre le code, les ADR, les DEC et la fiche de conformite.

## Correction appliquee

**`CLAUDE.md`, section "Conventions de redaction" : exigence positive de categorie B manquante.**

L'amendement du 2026-08-04 a `DEC-0002` (repris dans `FICHE-bandit-manchot-additions-cp.md`,
section "Relecture de DEC-0002") demande explicitement que `CLAUDE.md` porte, en plus de
l'exemption de categorie B deja ecrite, l'exigence positive : le texte destine a l'enfant est
redige en francais correct et integralement accentue. `DEC-0002` elle-meme, dans son "Impact" et
dans l'amendement, dit deux fois que ce complement est du a `CLAUDE.md` ("la meme precision
devrait figurer dans le complement de CLAUDE.md que DEC-0002 signale deja"). Le fichier ne portait
que la distinction des deux categories, pas l'exigence positive : la doc ne decrivait donc pas ce
qu'une decision deja actee avait tranche. Corrige en ajoutant une phrase reprenant l'exigence
telle que formulee par `DEC-0002`, avec renvoi a `relecteur-pedagogique` pour son controle. C'est
une correction de texte pure (report d'une decision deja ecrite ailleurs dans le depot), pas une
nouvelle decision de ma part.

Commit : `[REL] s01: report dans CLAUDE.md de l'exigence positive de DEC-0002 pour le texte enfant`.

## Cas nomme - l'indice d'AdditionsBorneesContentSource (constat, pas correction)

`domain/src/main/java/com/recreo/domain/content/source/AdditionsBorneesContentSource.java`, ligne
34 :

```java
private static final String INDICE = "Compte avec tes doigts pour t'aider.";
```

C'est du contenu pedagogique destine a l'enfant : je ne le valide pas et je ne le reecris pas.

- **Conformite typographique verifiee** : guillemet droit dans l'apostrophe (`t'aider`, pas de
  guillemet typographique), aucun emoji, phrase integralement accentuee. Rien a corriger sur ce
  plan.
- **Sur le fond, ce texte n'est pas mon perimetre** : sa pertinence pedagogique (est-ce une bonne
  aide pour un enfant de CP qui bloque sur une addition ?) releve de `nadia-prof`, pas de moi.
- **Obstacle de gouvernance que je constate, sans le trancher** : la Javadoc de `Question`
  (`ADR-0012 / S1-5 : l'indice est optionnel et vient du contenu, jamais de la mecanique`)
  annonce un indice porte par le contenu. Dans les faits, ce texte precis vit dans une constante
  Java du domaine, pas dans le paquet YAML `cp-additions-jusqu-a-10.yaml` (verifie : ce fichier ne
  porte que `sommeMaximale` et `nombreDePropositions` sous `parametres`, aucune cle d'indice). La
  doc et le code se contredisent donc sur ce point precis - un contenu destine a l'enfant est code
  en dur dans le domaine plutot que porte par le contenu, exactement la dette listee par `CLAUDE.md`
  a ne pas reproduire ("le comportement des mecaniques loge dans le JavaScript client" a sa
  contrepartie ici : un texte pedagogique loge dans une classe Java plutot que dans le YAML).
- **Constat au moment de cette relecture** : `camille-po` a deja route cet obstacle en review sous
  `DEC-0005-indice-s1-5-vit-dans-le-yaml-pas-dans-le-domaine.md` (creee le meme jour), qui tranche
  exactement l'emplacement (le texte doit venir du paquet YAML, jamais d'une constante du domaine),
  sans juger la qualite du texte, et route la correction au backlog de s02 comme non bloquante
  pour la cloture de s01. La `FICHE-bandit-manchot-additions-cp.md` porte deja une ligne de critere
  non cochee pour ce point (section "Criteres pedagogiques attendus", dernier item), qui nomme
  `DEC-0005` et renvoie l'indice comme contenu neuf a l'audit de `nadia-prof`. Je confirme donc que
  l'obstacle est deja route par les bonnes portes ; je n'ai rien de plus a trancher ni a ajouter,
  au-dela de la verification typographique ci-dessus.

## Libelles remontes a camille-po et nadia-prof

Ce n'est pas mon perimetre : ce registre est gouverne par la porte pedagogique de `nadia-prof`
(`DEC-0002`), pas par les conventions de redaction du depot. Je liste ici, sans les corriger, tous
les libelles de contenu et d'interface destines a l'enfant introduits ou modifies pendant s01, pour
que `camille-po` et `nadia-prof` les traitent lors du prochain audit de l'increment. Les textes
deja fixes noir sur blanc par `DEC-0004` sont marques comme tels ; les autres ont ete ecrits
directement dans le gabarit ou le code, sans DEC dediee, et restent donc a valider au meme titre.

| Texte | Emplacement | Origine / statut |
|---|---|---|
| "Oups ! Le jeu a un souci." | `adapters/adapter-web/src/main/resources/templates/fragments/erreur.html:5` | Fixe par `DEC-0004`, point 2 |
| "Recommencer" (bouton) | `adapters/adapter-web/src/main/resources/templates/fragments/erreur.html:6` | Fixe par `DEC-0004`, point 2 |
| "Ta partie s'est arrêtée. Choisis un nouveau jeu !" | `adapters/adapter-web/src/main/java/com/recreo/adapter/web/PartieController.java:39` | Fixe par `DEC-0004`, point 3 |
| "Score : " + nombre de reussites | `adapters/adapter-web/src/main/resources/templates/fragments/manche.html:7` et `fragments/retour.html:7` | Forme fixee par `DEC-0004`, point 1 |
| "Retour à l'accueil" (bouton, nouveau ce sprint) | `fragments/manche.html:5-6` et `fragments/retour.html:5-6` | Nouveau libelle d'interface (S1-4), aucune DEC ne fixe ce texte precis |
| "Encore !" (bouton de continuation, remplace "Manche suivante") | `fragments/retour.html:17` | Ecrit par le developpeur en reponse a la reserve 6 de la fiche (S1-8), aucune DEC ne fixe ce texte precis |
| "La partie est finie. Bravo, tu as bien joué !" (remplace "La partie est terminee. Merci d'avoir joue !") | `fragments/retour.html:19` | Idem, reserve 6 (S1-8), aucune DEC |
| "Non, c'est X." (remplace "La bonne réponse était X.") | `fragments/retour.html:11-13` | Idem, reserve 6 (S1-8), aucune DEC |
| "Il n'y a pas de jeu pour le moment." (remplace "Aucune partie disponible pour le moment.") | `accueil.html:18` | Idem, reserve 6 (S1-8), aucune DEC |
| "Calcul CP" (remplace "Additions CP", titre du bouton de jeu) | `adapters/adapter-content/src/main/resources/catalogue.yaml:4` | Reserve 6 / S1-8, donnee de catalogue, aucune DEC |
| "Compte avec tes doigts pour t'aider." (indice apres trois echecs sur le meme item) | `domain/src/main/java/com/recreo/domain/content/source/AdditionsBorneesContentSource.java:34` | Cas nomme ci-dessus. Contenu neuf, non juge par `nadia-prof`. Emplacement (domaine vs YAML) deja route par `camille-po` via `DEC-0005`, non bloquant pour s01 |
| "Bravo, c'est juste !" | `fragments/retour.html:10` | Inchange depuis le sprint 0, deja valide par `nadia-prof` dans la fiche ("Biais et ton") - liste ici par exhaustivite, aucune action attendue |

Point d'attention pour `camille-po` : six des libelles ci-dessus ("Retour à l'accueil", "Encore !",
"La partie est finie. Bravo, tu as bien joué !", "Non, c'est X.", "Il n'y a pas de jeu pour le
moment.", "Calcul CP") repondent a des reserves de la fiche (6, 1, 7) mais n'ont pas de formulation
fixee par une DEC, contrairement aux textes de S1-2, S1-3, S1-4 et a l'indice de S1-5. Ils ont ete
ecrits directement par le developpeur a partir de la suggestion de `nadia-prof` dans la fiche
("Encore !" est repris mot pour mot, "Non, c'est 4." aussi). Rien ne m'indique une faute de
redaction ou un probleme typographique sur ces six textes ; je les remonte parce que leur
formulation exacte n'a encore ete validee ni par `camille-po` ni par `nadia-prof` en tant que
texte definitif, seulement suggeree en reserve.

## Points a trancher hors de mon mandat

Rien d'autre a signaler que ce qui precede. Les points hors architecture routes par
`avis-faisabilite.md` (forme iconographique du score, obstacle de mandat design pour S1-6/S1-9,
seuil chiffre de S1-7) sont deja traites par `DEC-0004` et restent hors de mon perimetre de
relecture de toute facon : ce sont des jugements produit et pedagogiques, pas des questions de
redaction.
