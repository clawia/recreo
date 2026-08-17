# Relecture pedagogique et QA de contenu - Sprint 14

- **Auteure** : relecteur-pedagogique
- **Date** : 2026-08-17
- **Provisionnement** : worktree dedie `s14-relecteur`, provisionne hors de l'arbre du depot depuis
  `round-two` a `81a43ac`. Lignee verifiee : `outils/verifier-lignee.sh` rend "le worktree sur la
  branche 's14-relecteur' descend de la lignee de round-two (ancetre commun trouve dans son
  historique)."
- **Perimetre lu** : `planning.md`, `avis-faisabilite.md`, `rapport-qualite.md`,
  `confirmation-archunit.md`, `proposition-ines-barre-de-niveaux.md` (repertoire
  `s14-points-d-entree-par-niveau`) ; `DEC-0032`, `DEC-0033`, `DEC-index.md`, l'amendement du
  2026-08-17 de `DEC-0027` ; `ADR-0043`, `ADR-0044`, `ADR-0045`, les amendements du 2026-08-17
  d'`ADR-0039` et d'`ADR-0042`, l'amendement de `ADR-0022`, `ADR-index.md` ; `FICHE-barre-entree-
  niveau.md`, `FICHE-bandit-manchot-tables-multiplication-cm1.md`, `FICHE-bandit-manchot-additions-
  cp.md` ; `MATRICE-couverture.md` et `FILE-ATTENTE.md` (sections s13/s14) ; `docs/backlog/
  backlog.md` section "Sprint 14" (S13-6 note de cloture, S14-1, S14-2) ; la section 5 de
  `confirmation-archunit.md` de s13 ; `PartieController.java`, `accueil.html`,
  `fragments/confirmation.html`, `fragments/manche.html`, `recreo.css`, `PartieUseCase.java`,
  `PartieService.java`, `ChaqueEntreeDuCatalogueChargeBienSonPaquetTest.java`,
  `AucuneEntreeDuCatalogueNEnDoubleUneAutreTest.java`,
  `LaPositionDeLaBonneReponseALEcranNEstPasPredictibleSurUnGrandNombreDeTiragesTest.java`,
  `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest.java` (touche ce sprint par `theo-archi`,
  dans le meme fichier que la dette 2) ; `CLAUDE.md` (branche fusionnee) et, en lecture seule sans
  commit, la section "Prerequis de build" du worktree non fusionne `../recreo-s14-theo-prerequis`.

## Corrections appliquees (2)

1. `adapters/adapter-content/src/test/java/com/recreo/adapter/content/
   AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest.java`, commentaire de `BUDGET_DE_TIRAGES` :
   "le plus gros paquet du depot en compte 25" corrige en "le plus gros paquet du depot en compte
   99 items, cm1-tables-multiplication, mesure du 2026-08-17". Chiffre perime dans une phrase
   ecrite en s10 (avant que `cm1-tables-multiplication` n'entre au catalogue), jamais retouchee
   quand `theo-archi` a corrige, dans le meme fichier et le meme sprint, deux autres affirmations
   perimees de la classe voisine avec la meme methode. Verifie par comptage direct des lignes
   `  - ` sous `items:` des douze fichiers de `adapters/adapter-content/src/main/resources/
   contenu/` : 99 est bien le maximum, et coincide avec le chiffre deja etabli le meme jour dans
   `ChaqueEntreeDuCatalogueChargeBienSonPaquetTest` et dans `confirmation-archunit.md` section
   5.3. Le budget de 500 tirages reste largement suffisant (99 < 500) : aucune logique modifiee.
2. `squad/decisions/adr/ADR-index.md`, entree 41 (`ADR-0043`/`ADR-0044`) : "`ADR-0044` ajoute la
   29e regle ArchUnit du depot, la premiere depuis s00" corrige en "la premiere depuis s06
   (2026-08-07, `ADR-0019`)". Affirmation historique fausse, verifiee par `git log` sur les douze
   fichiers qui declarent une `ArchRule` : des regles neuves ont ete ajoutees en s01 (2026-08-05,
   +2), le 2026-08-06 (+2, `ADR-0018`) et en s06 (2026-08-07, +2 sur `ADR-0019`, +1 sur l'avis
   `ADR-0020`/`ADR-0021`), soit cinq ajouts distincts entre s00 et s14 avant celui d'`ADR-0044`.
   Le dernier ajout avant s14 est bien celui du 2026-08-07 (s06) : c'est la date correcte, pas s00.

Les deux corrections concernent des chiffres perimes dans des commentaires de code ou de
documentation technique, verifies independamment (comptage de fichiers, `git log`) avant
correction ; aucune ne touche a une decision, une regle executable ou un critere d'acceptation.

## Signale sans corriger

Rien. Les nombreux chiffres croises de ce sprint (comptes de tests 489/495/502/506, comptes de
regles ArchUnit, effectifs du catalogue) portent chacun leur methode, leur date et leur commit
juge, et les ecarts entre eux sont explicitement reconcilies par leurs auteurs (`confirmation-
archunit.md` section 5 reconcilie explicitement son 506 avec le 502 de `rapport-qualite.md` sur le
meme arbre `c1abb47`, ecart nomme et explique). Aucune reference croisee fausse trouvee parmi les
tres nombreuses de ce sprint (BARRE-1 a BARRE-9, ADR-0039 amende par ADR-0045, DEC-0027 amende deux
fois, DEC-0032/DEC-0033/S14-2) : chaque renvoi verifie pointe vers un fichier, une section ou une
regle qui existe reellement sous le nom cite.

## Verifie sans rien trouver

- **Chemins de poste de travail et noms de compte**, sur l'ensemble du perimetre liste ci-dessus :
  recherche des formes `C:\...`, `C:/...`, `/c/...`, `/home/...`, `/Users/...` et du nom de compte
  de l'utilisatrice : aucune occurrence reelle (uniquement des faux positifs, "declaration"/
  "declarative" contenant la sous-chaine "clara").
- **Tirets cadratins et guillemets typographiques**
  (`[\x{2014}\x{2013}\x{201C}\x{201D}\x{2018}\x{2019}]`) sur les quatorze documents `squad/` et
  `docs/` du perimetre : aucune occurrence.
- **Emoji et symboles Unicode decoratifs** sur le meme perimetre : aucune occurrence.
- **Titres coupes, blocs dupliques, sections vides** : aucun trouve. Les seuls cas ou une sous-
  section suit immediatement son titre de section sont des enchainements normaux (une section qui
  s'ouvre directement sur sa premiere sous-section), verifies un par un.
- **Coherence terminologique** des identifiants d'agent, des noms de garde-fous cites d'un document
  a l'autre (`LActionNeDeclarePasMoinsQueLaSortieTest`, `LeChoixDesJeuxResteDerriereLePortTest`,
  etc.) et du vocabulaire produit (`entree`/`sortie`/`action`, BARRE-1 a BARRE-9) : coherents sur
  l'ensemble du perimetre.
- **Paraphrase de code dans les commentaires** : aucune trouvee dans les fichiers `.java`, `.html`
  et `.css` du perimetre - chaque commentaire de ce sprint porte une justification (un ADR, un
  arbitrage date, une mesure) plutot qu'une redite du code qui le suit.
- **La section non fusionnee de `CLAUDE.md`** (`../recreo-s14-theo-prerequis`, troisieme point des
  "Prerequis de build" sur `-am`) : francais correct, pas de tiret cadratin ni de guillemet
  typographique, pas de chemin de poste. Rien a corriger si et quand cette branche fusionne ; non
  commitee ici puisqu'elle n'est pas encore sur `round-two`.
- **Le contenu destine a l'enfant** (libelles "CP"/"CM1", texte de `fragments/confirmation.html`
  et `fragments/manche.html`) : hors de mon registre par construction (`nadia-prof`), francais
  accentue et sans faute constate en le lisant, aucune faute d'orthographe evidente relevee qui
  appellerait un signalement a `nadia-prof`.
