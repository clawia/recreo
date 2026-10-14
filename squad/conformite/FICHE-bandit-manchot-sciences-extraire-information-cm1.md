# Fiche de conformite - bandit-manchot-sciences-extraire-information-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-extraire-information.yaml`
  (12 items, type `question`). **Premier paquet a utiliser `tableauSupport`
  (`ADR-0099`).**
- **Entree de catalogue** : `bandit-manchot-sciences-extraire-information-cm1`, titre servi *"Lis
  le document scientifique"*, consigne servie *"Lis le texte ou le tableau, puis reponds a la
  question."*, `nombreDeManches: 10`.
- **Ligne declaree** : `SCI-CM1-20`, **couverture partielle** (texte et tableau seuls).
- **Statut global** : **VALIDE**. Done pedagogique **prononce le 2026-09-23** par `nadia-prof`,
  apres avoir joue le jar servi.
- **Arbitrage prealable** : `ARBITRAGE-PREALABLE-s32-trois-instances-ma-borne-a-douze-le-terrain-sans-image-et-le-prix-d-une-ligne-marginale.md`,
  ligne 1033, mention exigee d'avance : *"document textuel et tableau seuls ; le schema et le
  graphique restent non couverts"*. Referentiel :
  `squad/programme/sciences-et-technologie/competences-cm1.md`, ligne `SCI-CM1-20`.
- **Derniere mise a jour** : 2026-10-14 (rejeu de `S54-OBS-SCI-CONSIGNE`, s57, bloc en fin de fiche).

---

## 1. Ce que j'ai joue

Reacteur construit (`mvn -B clean package -DskipTests` via `bash outils/mvn-jdk21.sh`, worktree
`s40-nadia-2` a `652cafc8`), `bootstrap/target/recreo.jar` lance sur le JDK 21 designe par la
toolchain. Deux parties completes jouees pour couvrir les deux formes de document sur les dix
manches de chaque tirage (`nombreDeManches: 10` sur un pool de 12 items) : quatre items a tableau
et six items a texte vus a l'ecran au total entre les deux parties, sur les six de chaque type que
porte le paquet.

## 2. Verification demandee en priorite : le tableau ne fuit pas, et ses reponses en sont des cellules

**Rendu inspecte a la source HTML brute** (`curl`, avant tout traitement visuel), sur l'item
"regime alimentaire" avant toute reponse : la consigne, un `<table>` HTML reel (`<thead>` avec
`scope="col"`, `<tbody>` de trois `<tr>`), l'enonce, puis le formulaire de propositions - **rien
d'autre a l'ecran**. Aucun indice, aucune explication, aucune reponse revelee ne sont presents tant
qu'aucune reponse n'est soumise (`th:if` sur `message-jeu`, verifie dans le gabarit
`manche.html` et confirme par la sortie HTML reelle).

**Bonne reponse et distracteurs verifies comme cellules du meme tableau, item par item** :

- Tableau "Animal / Regime alimentaire" (vache-herbivore, loup-carnivore, ours brun-omnivore) :
  chaque item demande le regime d'un des trois animaux, et les deux distracteurs sont exactement
  les deux autres valeurs de la colonne "Regime alimentaire" - jamais une valeur hors du tableau.
- Tableau "Materiau / Etat a temperature ambiante" (fer-solide, eau-liquide, dioxygene-gazeux) :
  meme verification, les deux distracteurs sont les deux autres etats de la meme colonne.

**Aucune reponse ne fuit ailleurs sur l'ecran** : le tableau affiche les trois lignes completes (ce
qui est le but meme de l'exercice - lire un tableau pour en extraire une information), et rien en
dehors du tableau et des boutons de proposition ne nomme une valeur. Verifie a l'ecran et a la
source.

## 3. Exactitude scientifique des deux tableaux et des deux textes

- Regimes alimentaires : vache herbivore, loup carnivore, ours brun omnivore - exacts.
- Etats de la matiere a temperature ambiante : fer solide, eau liquide, dioxygene gazeux - exacts.
- Texte "atmosphere" : la fine couche d'air qui entoure la Terre protege des rayons dangereux du
  Soleil et retient la chaleur, ce qui permet a l'eau de rester liquide - simplification exacte au
  niveau CM1.
- Texte "abeilles" : les abeilles butinent pour recolter du nectar, transportent aussi du pollen,
  ce qui permet aux plantes de produire fruits et graines - exact.

## 4. Chaque reponse est un extrait recopiable, aucune deduction

Verifie sur les 12 items : la reponse attendue est toujours une valeur ou une expression presente
telle quelle (ou a l'identique au genre/nombre pres) dans le texte ou dans une cellule du tableau,
jamais un calcul ni une inference. Sur les textes, la phrase porteuse de la reponse varie d'un item
a l'autre du meme texte (`ADR-0072`/`ADR-0099` decision 4), evitant qu'une seule phrase ne
concentre la majorite des reponses.

**Deux distracteurs faibles releves, sans consequence sur le verdict** : sur l'item "que
transportent aussi les abeilles" (bonne reponse "du pollen"), le distracteur "des epines" est peu
plausible pour un enfant de CM1 (une abeille ne "transporte" pas des epines, l'idee est
immediatement ecartable par bon sens plutot que par lecture) ; de meme "de changer de couleur" sur
l'item suivant. Ce sont deux distracteurs sur douze items, les dix autres etant solides ; je le
note pour un prochain paquet plutot que d'en faire un motif de refus sur ce paquet-ci.

## 5. Lisibilite, vocabulaire, ton, biais

Enonces en deux phrases (consigne de lecture puis question), vocabulaire de cycle 3 (regime
alimentaire, temperature ambiante, atmosphere, nectar, pollen). Aucun biais, ton neutre et
factuel.

## 6. Ce dont je reste la seule garde

L'exactitude scientifique des quatre documents (section 3) et le jugement sur la solidite des
distracteurs (section 4) ne sont verifies par aucun garde-fou automatique. C'est moi qui les tiens,
fait ce jour. Le non-leakage du tableau (section 2) est verifie par construction du gabarit
(`manche.html`), garde d'architecture qui releve de `theo-archi`, pas de moi - je l'ai verifie a
l'ecran en plus de le lire.

---

## Bloc de validation

> **Done pedagogique : VALIDE.**
>
> **Prononce le 2026-09-23 par `nadia-prof`, apres avoir joue le jar servi.**
>
> **Ce que j'ai verifie** : les 12 items, le rendu isole du tableau sans fuite (section 2), les
> bonne reponse et distracteurs comme cellules du meme tableau, l'exactitude des quatre documents,
> l'absence de deduction, la lisibilite et l'absence de biais.
>
> **Mention obligatoire a reprendre par `camille-po` dans `MATRICE-couverture.md`** (arbitrage
> prealable s32, sans laquelle la ligne ne ferme pas) : *"document textuel et tableau seuls ; le
> schema et le graphique restent non couverts."*
>
> **Mouvement de ligne** : ce Done ouvre la voie a `SCI-CM1-20` en `couvert-valide` **avec cette
> mention**. Le mouvement lui-meme est ecrit par `camille-po`, pas par moi.

---

### Rejeu du 2026-10-14 (`nadia-prof`, s57, palier 1, instance B) : `S54-OBS-SCI-CONSIGNE` **VALIDE**

**Objet joue** : branche `s57-sci-consigne`, commit `c3875936` (`lea-dev`), **non fusionne**. Les 12 enonces perdent leur
premiere phrase ; `PaquetCm1SciencesExtraireInformationTest` recoit le test de substitution. `SCI-CM1-20` reste
`couvert-valide` avec sa mention ; **non rouverte**, le statut global ci-dessus ne change pas.

**Build** : `bash outils/mvn-jdk21.sh -B clean verify` dans le worktree de la branche, racine, sans `-pl` : `BUILD
SUCCESS`, 2499 tests (120, 31, 486, 1485, 371 et 6 d'integration), 0 echec ; JDK 21.0.10. Job `build` seul, lance par
moi. **Jar** : port 18513 (B, palier 1, libre avant), *"Tomcat started on port 18513"*, PID 42788, arrete par ce PID seul.
Dossier de poste `s57-p1-nadia-prof-b`.

| Critere | Constat |
|---|---|
| 1. Chaque enonce commence par *"D'après ce tableau,"* ou *"D'après ce texte,"* | `grep -c "puis réponds à la question"` sur le paquet de la branche : **0**. **Tenu** |
| 2. Rien d'autre ne change (commande de paires amendee, `DEC-0120` A.70) | `git diff -U0 3a3ea97e c3875936 -- <paquet>`, passe dans l'`awk` de l'amendement : **`12 0 0`**. **Tenu.** Le commit precede l'amendement (`git merge-base --is-ancestor 829db5eb c3875936` sort 1) : la porte P-4 est une affaire de lignee que `sofia-sm` a nommee ; le contenu, lui, tient le critere amende |
| 3. La consigne du catalogue ne change pas | `git diff 3a3ea97e c3875936 -- catalogue.yaml` : vide ; servie a chaque manche : *"Lis le texte ou le tableau, puis réponds à la question."* **Tenu** |
| 4. Tests du paquet verts | inclus dans les 2499 ci-dessus. Je n'ai pas rejoue la mutation du test neuf : elle est a `yanis-qa` |
| 5. **Rejeu : une partie de dix manches, chaque enonce lu a l'ecran une fois** | une partie de dix manches (10 items, 0 ecart) : a chaque manche, la consigne une fois, puis le document, puis l'enonce, qui ne redit plus la consigne. Puis 5 parties de plus pour voir **les 12 enonces** a l'ecran (au moins 3 fois chacun), dont une jusqu'a la revelation. **Tenu** |

**Les 12 enonces, relus a l'ecran un par un** (servis identiques au fichier, comparaison caractere par caractere par mon
script) :

1. D'après ce tableau, quel est le régime alimentaire de la vache ?
2. D'après ce tableau, quel est le régime alimentaire du loup ?
3. D'après ce tableau, quel est le régime alimentaire de l'ours brun ?
4. D'après ce tableau, dans quel état se trouve le fer à température ambiante ?
5. D'après ce tableau, dans quel état se trouve l'eau à température ambiante ?
6. D'après ce tableau, dans quel état se trouve le dioxygène à température ambiante ?
7. D'après ce texte, comment s'appelle la fine couche d'air qui entoure la Terre ?
8. D'après ce texte, de quoi cette couche d'air protège-t-elle les êtres vivants ?
9. D'après ce texte, que permet la chaleur retenue par cette couche d'air ?
10. D'après ce texte, que récoltent les abeilles en butinant les fleurs ?
11. D'après ce texte, que transportent aussi les abeilles en passant de fleur en fleur ?
12. D'après ce texte, que ce transport de pollen permet-il à de nombreuses plantes ?

Accents lus : *après* sur les douze, *régime*, *état*, *à*, *température*, *dioxygène*, *protège*, *êtres*, *récoltent*.
Aucun manquant a ma lecture ; chaque `?` est precede d'une espace insecable (U+00A0, lu sur le texte servi). **Cette
lecture ne remplace pas le controle de l'orthographe, de l'accentuation et de la ponctuation, qui est au
`relecteur-pedagogique`** (`DEC-0002`, amendement du 2026-08-04 ; `DEC-0056`).

**Ce que l'enfant de CM1 lit maintenant** : une consigne, un document, une question. La charge de lecture inutile que
j'avais nommee (deux fois *"puis réponds à la question"*) a disparu ; rien de ce qui servait a repondre n'a bouge.

**Observation, sans reserve** : sur l'ecran de fin, quand la derniere manche est revelee, la question rappelee commence
par *"D'après ce tableau,"* ou *"D'après ce texte,"* sans le document a l'ecran. C'etait deja le cas avant le lot (avec une
phrase de plus). Pour un enfant de CM1 qui sort de la manche, *"ce tableau"* est celui qu'il vient de lire, le seul de la
manche ; ce n'est pas le cas de `RDO-CP-R2`, ou *"celle-ci"* designe une image parmi plusieurs pour un enfant de CP.

**Tarif consomme** : rejeu 0,25 (poste 6, instance B). Marge de rejeu de B intacte, 0,75 non affectes intacts.

**Signe** : `nadia-prof`, s57, palier 1, instance B, 2026-10-14.
