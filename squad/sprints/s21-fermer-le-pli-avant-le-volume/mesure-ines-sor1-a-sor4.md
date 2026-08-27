# Mesure ines-design - valeur du rembourrage de sortie sous SOR-1 a SOR-4

- **Auteure** : ines-design. **Date** : 2026-08-27. **Sprint** : s21, branche `s21-ines-2`,
  forkee de `s21-marc` a `0e0bc4e`.
- **Mandat de ce document** : `nadia-prof` retablit `ADR-0017` en faisant descendre le
  rembourrage vertical des boutons de sortie (`.lien-accueil`, `.retour-accueil`), et me confie
  la valeur exacte sous quatre conditions, `SOR-1` a `SOR-4`
  (`squad/conformite/FICHE-bandit-manchot-reperes-cm1.md`, arbitrage du 2026-08-27, "ADR-0017
  inverse sur un axe apres S21-2"). Ce document donne la valeur, la commande qui la mesure et la
  verification des quatre conditions.

## 0. Ce que je change et ce que je ne change pas

`recreo.css` : `.lien-accueil` (ligne 130 et suivantes) et `.retour-accueil` (ligne 839 et
suivantes) passent de `padding: 0.6rem 1.1rem` a `padding: 0.5rem 1.1rem`. Seul l'axe vertical
bouge - l'axe lateral (1.1rem) n'etait pas en cause : il etait deja inferieur a l'axe lateral de
`.proposition` (1.25rem), et le reste. Je ne touche a aucun autre selecteur, aucun gabarit,
aucun fichier sous `domain/`, `application/`, `bootstrap/`.

**Pourquoi 0.5rem et pas une valeur intermediaire.** `SOR-1` demande "au plus egal" a l'action -
`.proposition` declare `0.5rem` de rembourrage vertical depuis `S21-2`. `0.5rem` est donc a la
fois la valeur qui satisfait l'egalite au sens le plus etroit (aucune marge de securite
inutile, aucune reprise du gain de `S21-2`) et celle que `nadia-prof` a deja projetee dans son
arbitrage (section `SOR-2`) : je reprends sa projection et je la verifie au rendu plutot que
d'en inventer une autre.

## 1. Le jar mesure

`bash outils/mvn-jdk21.sh -B clean package -DskipTests`, le 2026-08-27, sur `s21-ines-2` a
`0e0bc4e` plus les deux changements de `recreo.css` decrits en section 0 (non commites au moment
de la mesure, verifie par lecture du fichier juste avant de lancer le build). Jar servi :
`java -jar bootstrap/target/recreo.jar --server.port=9382` (JDK 21, toolchain declaree).

## 2. La mesure des boites, a l'ecran

**Outil** : `squad/outils/ines-design/mesurer-boutons-role.py` (cree par ce document, meme
protocole que `balayage-largeur-rangee.py` dont il importe le `Navigateur` - navigateur reel
pilote par le protocole de deverminage, viewport `744 x 551`, `deviceScaleFactor` a 1, rien
d'injecte dans le document). Il lit, pour chaque element `[data-role]` visible, sa boite rendue
(`getBoundingClientRect`) et les composantes qu'`ADR-0017` compare (`font-size`, `min-height`,
`padding`).

**Commande, ecran de manche, 2026-08-27** :

```
CHROME=<chemin local de Chrome> python squad/outils/ines-design/mesurer-boutons-role.py \
    http://127.0.0.1:9382 bandit-manchot-reperes-cm1 manche \
    squad/conformite/releves-s21/s21-ines-sor-manche-744x551.json
```

Releve commite : `squad/conformite/releves-s21/s21-ines-sor-manche-744x551.json`.

| Bouton | Role | Hauteur rendue | Largeur rendue | Surface rendue | `padding` haut/bas (calcule) |
|---|---|---|---|---|---|
| `.proposition` la plus etroite de ce tirage (`Phrase correcte`) | action | 66,00 px | 289,00 px | 19 074 px2 | 8 px (0,5rem) |
| `.lien-accueil` (`Les jeux`) | sortie | **50,00 px** | 108,83 px | 5 441,41 px2 | **8 px (0,5rem)** |

**Commande, ecran de confirmation avant abandon, 2026-08-27** :

```
CHROME=<chemin local de Chrome> python squad/outils/ines-design/mesurer-boutons-role.py \
    http://127.0.0.1:9382 bandit-manchot-reperes-cm1 confirmation \
    squad/conformite/releves-s21/s21-ines-sor-confirmation-744x551.json
```

Releve commite : `squad/conformite/releves-s21/s21-ines-sor-confirmation-744x551.json`.

| Bouton | Role | Hauteur rendue | Largeur rendue | Surface rendue |
|---|---|---|---|---|
| `.continuer-partie` (`Continuer la partie`) | action | 56,00 px | 696,00 px | 38 976 px2 |
| `.retour-accueil` (`Les jeux`) | sortie | **50,00 px** | 108,83 px | 5 441,41 px2 |

## 3. Les quatre conditions de `nadia-prof`, verifiees une a une

- **`SOR-1` - le rembourrage vertical de la sortie descend jusqu'a etre au plus egal a celui de
  l'action.** Verifie par lecture de `recreo.css` : `.proposition` et `.lien-accueil` /
  `.retour-accueil` declarent desormais tous `0.5rem` de rembourrage vertical - l'egalite, pas
  une inversion. `ADR-0017` interdit une composante superieure de la sortie ; l'egalite passe.
- **`SOR-2` - le plancher de cible tactile de 2,75rem (44 px) tient.** Mesure a l'ecran (section
  2, colonne "Hauteur rendue") : **50,00 px**, exactement la valeur que `nadia-prof` avait
  projetee depuis les composantes (contenu 29,99 px + 16 px de rembourrage + 4 px de bordure),
  et **6 px au-dessus du plancher**. `min-height: 2.75rem` reste declare (verifie par lecture,
  ligne 134 pour `.lien-accueil`, ligne 843 pour `.retour-accueil`, inchangees par ce lot).
- **`SOR-3` - `.retour-accueil` porte la meme boite que `.lien-accueil` et descend avec elle.**
  Mesure a l'ecran (section 2, deux tableaux) : **50,00 x 108,83 px, 5 441,41 px2, dans les deux
  cas, au centieme pres.** Les deux selecteurs recoivent le meme changement dans le meme commit.
- **`SOR-4` - la sortie reste atteignable du premier coup.** Je verifie ce qui est de mon
  ressort : la boite perd 3,19 px de hauteur (53,19 a 50,00 px) et rien d'autre ne change -
  position dans l'en-tete (`margin-left: auto`), couleur, contour, texte, largeur (108,83 px
  inchangee, elle depend du libelle et du rembourrage lateral que je ne touche pas). Le bouton
  reste visible, lisible et au-dessus du plancher tactile sur les deux ecrans ou il s'affiche.
  **Je ne prononce aucun Done pedagogique** : `nadia-prof` a nomme elle-meme qu'elle verifie
  cette condition en jouant, et c'est elle qui la ferme.

## 4. Garde-fous

**Attention nommee par `nadia-prof`** : `LActionNeDeclarePasMoinsQueLaSortieTest` lit la
**premiere** declaration d'un selecteur repete plutot que la derniere que la cascade applique
(`blocsParSelecteur` / `debutDeDeclaration`), donc son vert ne prouve rien sur l'inversion que ce
lot corrige - `theo-archi` le corrige en parallele. Je ne conclus donc pas de son vert que ma
valeur est bonne : la section 2 et 3 ci-dessus mesurent le rendu reel, pas la declaration lue par
un garde-fou aveugle a la cascade.

Ce que j'ai verifie, et qui n'est pas soumis a cette limite :

- **Un test pin le texte litteral de la cascade sur `.lien-accueil`**
  (`LaLargeurDemandeeParUneRangeeEstEpingleeTest`, `LARGEUR_DEMANDEE_EPINGLEE`) : il epinglait
  `padding: 0.6rem 1.1rem` et est **mis a jour dans ce meme commit** vers `0.5rem 1.1rem` (seule
  ligne touchee, meme fichier, meme geste que le changement de production).
- `mvn -B clean verify` sur la racine, **vert, 2026-08-27** (six modules, `BUILD SUCCESS`,
  reacteur complet - pas de mesure partielle). Aucun autre test ne referencait `0.6rem 1.1rem`
  ni `0.6rem` sur un selecteur de production (verifie par recherche sur
  `adapters/adapter-web/src/`, les seules autres occurrences vivent dans des fixtures de rejeu
  d'etats historiques que ce lot ne touche pas).
- `TailleDesTextesEtBoutonsTest`, `LeConteneurTierGardeSonRembourrageLateralTest` : verts, sans
  regression sur les planchers qu'ils gardent (police, cible tactile, rembourrage lateral du
  conteneur).

## 5. Ce que je ne ferme pas

- **`SOR-4` au sens plein** : reste a `nadia-prof`, en jouant.
- **Le garde-fou aveugle a la cascade** : reste a `theo-archi`.
- **Le symptome route sur `cm1-accords-groupe-nominal`** (deux rangees venant d'une declaration
  de feuille et non de la largeur des libelles) : pas traite par ce document, faute de temps
  dans ce lot - je le signale plutot que de le laisser croire ferme (voir le fil de sprint).

## Impact

- `adapters/adapter-web/src/main/resources/static/css/recreo.css` : `.lien-accueil` et
  `.retour-accueil` passent de `padding: 0.6rem 1.1rem` a `padding: 0.5rem 1.1rem` ; commentaires
  mis a jour, dont la correction d'un commentaire faux signalee par `nadia-prof` (section 4 de
  son arbitrage).
- `adapters/adapter-web/src/test/java/com/recreo/adapter/web/LaLargeurDemandeeParUneRangeeEstEpingleeTest.java` :
  une ligne mise a jour (meme valeur, meme geste).
- `squad/outils/ines-design/mesurer-boutons-role.py` : cree.
- `squad/conformite/releves-s21/s21-ines-sor-manche-744x551.json`,
  `squad/conformite/releves-s21/s21-ines-sor-confirmation-744x551.json` : crees.
- `squad/sprints/s21-fermer-le-pli-avant-le-volume/mesure-ines-sor1-a-sor4.md` : cree (ce
  fichier).
