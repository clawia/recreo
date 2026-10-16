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
- **Derniere mise a jour** : 2026-10-16 (rejeu de `S57-OBS-SCI-N1-N2`, s57, palier 2, bloc en fin de fiche).
  Rejeu precedent : 2026-10-14 (`S54-OBS-SCI-CONSIGNE`).

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

---

### Rejeu du 2026-10-16 (`nadia-prof`, s57, palier 2, instance B) : `S57-OBS-SCI-N1-N2`, **N1 pris en compte : oui ; N2 pris en compte : oui**

**Objet joue** : `round-two` a `e8d14ddb`, qui contient `69dd7631` (`marc-dev`, fusion `9cbf3571`) : `git merge-base
--is-ancestor` sort 0 pour les deux. Le commit ne touche que le paquet, 4 lignes ajoutees et 4 retirees (`git show --stat`).
Le statut global de cette fiche ne change pas : **VALIDE**, `SCI-CM1-20` reste `couvert-valide` avec sa mention, **non
rouverte**. Aucune ligne de matrice ne bouge.

**Jar** : `bash outils/mvn-jdk21.sh -B clean package -DskipTests` dans le worktree `s57-nadia-p2-b`, `BUILD SUCCESS` ;
port 18513 libre avant, *"Tomcat started on port 18513"*, Java 21.0.10, arrete par son PID seul. Chrome sans interface,
profil dans mon dossier de poste `s57-p2-nadia-prof-b`, 744 x 551, arrete par son PID. **Done technique** : je ne l'ai pas
relance ; je m'appuie sur les quatre jobs de `ci.yml` que `marc-dev` nomme avec leur sortie pour p2-5
(`rapport-marc-dev-palier-2.md`, section 4 : `build` 2597 tests 0 echec, `chemins`, `prefixes`, `outils`). La CI distante
n'est lue par personne.

**Joue** : deux parties de dix manches, les 12 items vus (de 1 a 2 fois chacun). Sur les trois items touches (9, 11, 12),
une erreur, puis une deuxieme, puis la bonne reponse ; ailleurs, la bonne reponse. Captures regardees a l'ecran, pas
seulement le DOM : la manche avant reponse, l'enonce et l'indice agrandis deux fois, la page apres deux erreurs, l'ecran de
fin.

| Critere | Ce que j'ai vu a l'ecran | Constat |
|---|---|---|
| 1. `N1` | Item 12, deux lignes : *"D'après ce texte, que permet ce transport de pollen"* / *"à de nombreuses plantes ?"*. Le point d'interrogation ne passe pas seul a la ligne. Bonne reponse et distracteurs servis : *"de produire des fruits et des graines"*, *"de pousser plus vite"*, *"de changer de couleur"*, inchanges | Texte servi identique a ma recommandation (`reponse-nadia-q-palier-1.md` 4.1), caractere par caractere, U+00A0 devant `?`. **Tenu** |
| 2. `N2` | Item 9 : *"Relis la dernière phrase du texte, après les"* / *"mots « ce qui permet »."* ; item 11 : *"... après le mot"* / *"« aussi »."* ; item 12 : *"... après le mot"* / *"« permet »."*. Les chevrons ne se separent jamais du mot cite au retour a la ligne ; le point final reste apres le chevron fermant | Les trois indices servis egaux au fichier (comparaison par mon script) et a mes textes (4.2). **Tenu** |
| 3. Aucun indice ne contient une proposition | Relu sur les trois items a l'ecran, propositions servies a cote | **Tenu** |
| 4 et 5 | `git show --stat 69dd7631` : un fichier, 4 et 4 ; tests : rapport de `marc-dev` | Lu, pas rejoue |

**Ce que l'enfant de CM1 lit maintenant.** La question de l'item 12 commence par *"que permet"*, comme l'item 9 : il ne
peut plus la lire d'abord comme une affirmation. Dans les indices, *"après le mot « aussi »"* ne se lit plus *"apres le mot,
aussi"*, et *"« ce qui permet »"* ne se lit plus comme le debut d'une relative : le mot a chercher dans le texte est
designe. Chacun des trois mots cites figure une fois dans la phrase designee (*"ce qui permet"* dans la troisieme phrase du
texte sur l'atmosphere, *"aussi"* dans la deuxieme du texte sur les abeilles, *"permet"* dans la troisieme). Exactitude :
rien n'a change dans les textes, les reponses ni les distracteurs ; ma section 3 vaut toujours.

**Apres deux erreurs** : les deux distracteurs sont barres, seule la bonne reponse reste a appuyer, l'indice reste
affiche. L'enfant bloque avance. **Ecran de fin** : *"Tu as trouvé, tout seul : 8"*, *"Tu as joué toutes les questions :
10"*, *"C'est fini !"* ; rien de neuf.

**Observation datee du 2026-10-16, sans reserve ouverte : l'indice sous le pli.** A 744 x 551, apres une erreur, le bas de
`.indice` est a 690 px sur l'item 12, a 616 ou 690 px sur l'item 9 selon l'ordre de tirage des propositions (deux ou trois
rangees de boutons), a 542 px sur l'item 11. **Ce n'est pas un effet de `N1` ni de `N2`** : sur la meme manche servie, j'ai
remplace dans la page l'indice (et l'enonce, sur l'item 12) par le texte d'avant `69dd7631` ; les cotes sont identiques au
pixel (indice de 80 px de haut, enonce de 96 px, dans les deux cas). Le releve `releves-s57/p2-echantillon.json`
(`ines-design`) donne les memes chiffres pour la forme commune. **Pourquoi je n'en fais pas une reserve ici** : la levee de
cette famille se juge sur tous les items du paquet (arbitrage du palier 2, 6.3), et je n'en ai mesure que trois, a une
seule largeur ; et ce rejeu a pour barre `N1` et `N2`, que je ne deplace pas. Si je l'ouvre, ce sera par un ecrit separe,
avec la mesure des 12 items et l'allocation de son identifiant.

**Ce dont je reste la seule garde, pour ce jeu** : qu'un mot cite dans un indice ou un enonce soit entre chevrons et figure
dans la phrase designee (aucun test n'epingle ces textes, criteres 4 et 5 de la story) ; la position de `.indice` sous le
pli (aucun test ne la tient).

**Non joue** : la revelation, qui vient au troisieme essai faux (`BanditManchotSession`, `ESSAIS_AVANT_REVELATION = 3`) ;
apres deux erreurs j'ai appuye sur la bonne reponse. `N1` et `N2` n'y changent que l'enonce et l'indice deja lus ; les
largeurs autres que 744 ; l'orthographe et la ponctuation, que je n'ai pas controlees moi-meme
(`relecteur-pedagogique`, `releve-relecteur-palier-2.md` 3.1 : correct, U+00A0).

**Tarif consomme** : rejeu 0,25 (p2-5, instance B).

**Signe** : `nadia-prof`, s57, palier 2, instance B, 2026-10-16.
