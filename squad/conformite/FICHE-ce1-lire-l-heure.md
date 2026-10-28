# Fiche de conformité - ce1-lire-l-heure

- **Mécanique** : `bandit-manchot`, `type: question`, sans texte support, **un dessin de cadran par item** (`.image-item`, 176 px) ;
  aucune modification de la mécanique, aucun cadran généré par le serveur (`arbitrage-nadia-palier-5-lp7-d5.md` 3.5).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-lire-l-heure.yaml` (12 items, lus par
  `grep -vE '^\s*#' ... | grep -c "bonneReponse:"`) et douze dessins `horloge-a.svg` à `horloge-l.svg` sous
  `adapters/adapter-web/src/main/resources/static/illustrations/`. Écrits par `lea-dev` (instance B, premier jet).
- **Entrée de catalogue** : aucune. `QC3` : un paquet n'entre au catalogue qu'après un Done pédagogique ou un refus levé ;
  `grep -c "paquet: ce1-lire-l-heure" adapters/adapter-content/src/main/resources/catalogue.yaml` rend 0 le 2026-10-28.
- **Ligne déclarée** : `MAT-CE1-11`, moitié *lire l'heure sur une horloge* (la moitié *calendrier* est `ce1-calendrier-dates`,
  `FICHE-bandit-manchot-calendrier-dates-ce1.md`). **Story** : `S57-BR-MAT-CE1-11-HORLOGE` (`docs/backlog/backlog.md`, annotation du
  2026-10-28, treizième). **Décision** : `DEC-0120` A.263, A.265, A.267, A.280, A.282 à A.284.
- **Compétence programme visée** : `squad/programme/mathematiques/competences-ce1.md`, `MAT-CE1-11`, *"Lire l'heure sur une horloge
  (heures, demi-heures, quarts d'heure) et se repérer dans le calendrier (mois, date)."*
- **Conditions opposables** : celles de la story `S57-BR-MAT-CE1-11-HORLOGE`, qui reprend les mots de `nadia-prof` (3.5 de
  `arbitrage-nadia-palier-5-lp7-d5.md` et sa lecture du premier jet), **non recopiées ici** : la story fait foi. Plafond de la
  ligne : `couvert-valide`.
- **Statut global** : **premier jet lu, en attente de la correction et de l'audit à l'écran**. Fiche ouverte par `camille-po` le
  2026-10-28. Aucun verdict. La ligne `MAT-CE1-11` reste `en cours` dans la matrice, qui ne bouge qu'au verdict de `nadia-prof`, à
  l'entrée de catalogue fusionnée et à la CI complète.
- **Poids** : le relèvement du cliquet des illustrations de 69 203 à 96 873 octets est accordé par l'utilisatrice
  (*"Q1 O1 on l'accorde"*, 2026-10-28, `DEC-0120` A.284) ; douze cadrans, 27 670 octets.
- **Dernière mise à jour** : 2026-10-28 (`camille-po`, `DEC-0120` A.284).

---

## Compétence visée

| Matière | Niveau | Compétence | Référence au référentiel |
|---|---|---|---|
| mathématiques | CE1 (cycle 2) | Lire l'heure sur une horloge (heures, demi-heures, quarts d'heure) et se repérer dans le calendrier (mois, date). Cette fiche porte la première moitié. | `squad/programme/mathematiques/competences-ce1.md`, `MAT-CE1-11` |

## Critères attendus - camille-po

Les critères d'acceptation de cette fiche sont ceux de la story `S57-BR-MAT-CE1-11-HORLOGE` (`docs/backlog/backlog.md`, titre
*"Écrire et brancher le paquet de la lecture de l'heure"*). Je ne les recopie pas : une copie dérive de la source. Je n'écris aucune
mesure ici.

- [ ] Critères de la story `S57-BR-MAT-CE1-11-HORLOGE`, numérotés de 1 à 12 dans le backlog.
- [ ] Conditions de `nadia-prof` sur l'horloge : 3.5 de `arbitrage-nadia-palier-5-lp7-d5.md` (`HOR-1` à `HOR-3`, `HOR-5`
  transposée, `HOR-6` transposée, `CON-5`) et exigences 1 et 2 de sa lecture du premier jet.
- [ ] Texte servi à l'enfant intégralement accentué, orthographe et ponctuation irréprochables (`DEC-0002`).

## Parcours du lot, faits lus au dépôt

Source : `git log` de `round-two` ; chaque SHA est un commit de `round-two` (existence vérifiée par `git cat-file -e`). Les fichiers de
`lea-dev` et de `nadia-prof` sont dans `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/` pour les rapports, sous
`adapters/` pour le paquet et les dessins.

| Geste | Auteur | Commit | Fichier |
|---|---|---|---|
| premier jet (paquet, 12 dessins, garde M, test de paquet, cliquets) | `lea-dev` | `a5d5026b` | `ce1-lire-l-heure.yaml`, `horloge-a.svg` à `horloge-l.svg` |
| rapport du premier jet | `lea-dev` | `d242fe5b` | `rapport-lea-dev-palier-5-horloge-mat-ce1-11.md` |
| lecture du premier jet | `nadia-prof` | `844c524f` | `lecture-nadia-premier-jet-horloge-mat-ce1-11.md` |
| correction (exigence 1) | `lea-dev` | à venir | `ce1-lire-l-heure.yaml` |
| glyphes et relevé (exigence 2) | `ines-design` | à venir | à écrire |
| seconde lecture | `nadia-prof` | à venir | à écrire |

Cette fiche n'en reprend aucun verdict : la lecture du premier jet est un geste avant `verify`, pas le Done pédagogique.

## État de la porte technique

**Ce qu'un fichier du dépôt prouve, et rien de plus.**

- **Aucun `mvn verify` complet n'a été lancé pour ce lot** (`lea-dev`, rapport, section 8 : *"Je ne déclare pas la porte technique"*).
- **Lancé par `camille-po` le 2026-10-28, mesure partielle** : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test
  -Dtest=LesCadransMontrentCeQueLeursItemsAffirmentTest,PaquetCe1LireLHeureTest,ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest
  -Dsurefire.failIfNoSpecifiedTests=false`, périmètre `domain`, `application`, `adapter-web`, `adapter-content`, `bootstrap`, ces
  trois classes seulement : code 0, 10, 6 et 17 tests, 0 échec. **Ce n'est ni le job `build` entier, ni la CI.**
- **Règle en vigueur** (`DEC-0120` A.280, réponse de l'utilisatrice à `Q1`, option `O1`) : un `mvn verify` complet **avant** l'audit
  à l'écran de `nadia-prof` de ce lot ; passage CI complet (`prefixes`, `chemins`, `outils`, `build`) à la clôture du palier ; toute
  annonce de porte nomme les jobs lancés.
- Premier `verify` complet du lot : **à faire**, résultat à inscrire ici par qui le lance, avec les jobs nommés.

## Ce dont `nadia-prof` reste la seule garde sur cette ligne

Ce qu'aucun test ne tient : les cinq propriétés de la section 6 de sa lecture du premier jet (`lecture-nadia-premier-jet-horloge-mat-ce1-11.md`),
résumées ici, sa section fait foi : qu'aucune stratégie qui ignore le cadran ne gagne plus de la moitié des items, que la petite aiguille se lise
entre deux nombres à 176 px, que les glyphes se lisent comme des chiffres, que le texte alternatif ne livre ni l'heure ni la place
d'une aiguille (`CON-5`), que chaque distracteur soit plausible.

---

## Bloc de validation pédagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pré-rempli ni anticipé par un autre agent.
