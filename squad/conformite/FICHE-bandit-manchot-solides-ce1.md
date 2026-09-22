# Fiche de conformite - bandit-manchot-solides-ce1

- **Mecanique** : bandit manchot, `item illustre` (`ADR-0073`).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-solides.yaml`
  (15 items, type `question`), 15 dessins SVG neufs dans
  `adapters/adapter-web/src/main/resources/static/illustrations/`.
- **Entree de catalogue** : `bandit-manchot-solides-ce1`, titre servi *"Les solides"*, consigne
  servie *"Regarde bien le dessin, puis choisis son nom."*, `nombreDeManches: 10`.
- **Ligne declaree** : `MAT-CE1-16`.
- **Statut global** : **VALIDE**. Done pedagogique **prononce le 2026-09-22** par `nadia-prof`,
  apres avoir joue le jar servi et regarde chaque dessin a l'ecran, a la taille reelle de rendu.
- **Arbitrage prealable** : `ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md`, section 4 et
  son amendement du meme jour (`SOL-CE1-1` a `SOL-CE1-4`, forme de la boule).
- **Derniere mise a jour** : 2026-09-22 (premier audit, s39).

---

## 1. Ce que j'ai joue, et comment - attention particuliere portee a la boule

**Audit non degrade.** Reacteur construit (`mvn -B clean verify` via `bash outils/mvn-jdk21.sh`,
`BUILD SUCCESS`, six modules verts), `bootstrap/target/recreo.jar` lance sur le JDK 21 designe par
la toolchain. J'ai joue plusieurs parties completes de `bandit-manchot-solides-ce1`, chaque manche
rendue a l'ecran avec la feuille de style reelle du jar (pas seulement le SVG source lu isolement) :
`cylindre-b`, `pave-droit-c`, `boule-b`, `cube-b`, `pave-droit-b`, `cone-c`, `cube-c`, `boule-a`,
`cube-a`, `pave-droit-a` sur une partie de dix manches, puis une seconde amorce pour capturer
`cylindre-c` et provoquer volontairement trois erreurs de suite sur une manche afin de lire le
traitement de l'erreur jusqu'a la revelation.

**Ce que j'ai regarde specifiquement pour la boule** : les trois dessins `solide-boule-a`,
`solide-boule-b` et `solide-boule-c`, rendus cote a cote a leur taille d'ecran reelle (`.image-item`,
plafond 176 px), et non seulement lus comme coordonnees SVG. **Verdict : l'ellipse d'equateur se lit
sans ambiguite sur les trois tailles.** Le rond est clairement traverse par une courbe fermee
distincte du contour, dans la convention du ballon a bande - aucune des trois ne se lit comme un
simple cercle ni comme deux cercles concentriques. Le ratio vertical/horizontal de l'ellipse
(`13/38`, `10/30`, `14/42`, soit environ un tiers dans les trois cas) est constant et suffisant pour
que la bande reste visible meme sur la plus petite des trois tailles (`boule-b`).

## 2. Ce que j'ai vu sur les autres paires a risque

- **Cube contre pave droit** : `cube-a`/`cube-b`/`cube-c` se lisent comme un volume aux aretes
  visiblement egales ; `pave-droit-a`/`b`/`c` comme un volume nettement plus allonge dans une
  dimension. L'ecart de proportion est visible d'un coup d'oeil sur les quatre paires jouees, pas
  seulement mesurable dans le fichier source.
- **Cylindre contre cone** : `cylindre-b` (deux ellipses paralleles, pas de pointe) contre `cone-c`
  (une pointe nette au sommet, une ellipse a la base) - aucune confusion possible a l'ecran.
- **Couleur** : un seul trait, une seule couleur sur les 15 dessins vus - jamais discriminante.

## 3. `SOL-CE1-1` a `SOL-CE1-4` - verifiees a l'ecran

- **`SOL-CE1-1`** (proportions declarees par le contenu, jamais un texte ni un glyphe) : tenue -
  chaque dessin est un trace vectoriel propre, aucune legende, aucun texte dans l'image (verifie
  aussi sur l'attribut `alt`, identique et non revelateur sur les 15 items : *"Un solide à
  reconnaître."*).
- **`SOL-CE1-2`** (au moins trois exemples par solide, variant orientation et taille) : 3 dessins
  par solide x 5 solides = 15, tailles et proportions differentes constatees sur les captures
  (`boule-a` grande, `boule-b` petite, `boule-c` intermediaire ; meme variation sur les autres
  solides).
- **`SOL-CE1-3`** (couleur jamais discriminante) : tenue, section 2.
- **`SOL-CE1-4`** (cube/pave droit et cylindre/cone jamais confondus, boule sans ambiguite) : tenue,
  sections 1 et 2.

## 4. Traitement de l'erreur - joue jusqu'a la revelation

Une manche de `cylindre-c` jouee avec trois reponses fausses volontaires : premiere erreur, la
proposition choisie est barree et reste cliquable, l'indice apparait ("Regarde si le solide a une
pointe.") ; deuxieme erreur, meme traitement ; troisieme erreur, la bonne reponse ("un cylindre")
est revelee explicitement sous la consigne "Appuie sur la bonne réponse :", l'indice restant
affiche. Aucun enfant n'est laisse sans issue apres trois erreurs - comportement identique aux
autres paquets illustres deja valides (figures planes CP).

## 5. Ancrage sur `MAT-CE1-16`

Les 15 items portent exclusivement sur la reconnaissance et la denomination d'un solide dessine en
projection, jamais sur une description verbale de ses proprietes - exactement le verbe
"reconnaitre" que mon arbitrage prealable exigeait, par opposition a la forme textuelle refusee.
Les enonces ("Quel est ce solide ?" / "Comment s'appelle ce solide ?") ne decrivent ni la forme ni
la position du solide montre : la reponse est entierement dans le dessin (`CON-1` a `CON-3`).

## 6. Lisibilite, vocabulaire, ton, biais

Deux formulations d'enonce courtes, vocabulaire de CE1 (cube, pave droit, boule, cylindre, cone -
tous deja rencontres a l'oral avant le CE1). Indices qui portent un critere partage par les trois
solides proposes, jamais le nom de la bonne reponse (`CON-6`, verifie item par item). Aucun biais,
aucun ton infantilisant.

## 7. Ce dont je reste la seule garde

Le garde-fou technique (`chaqueDessinQuiSertUnSolideTraceUneProjectionEtNonUneFigurePlane`) verifie
qu'un dessin trace au moins deux formes rendues - il ne verifie pas que la seconde forme est une
ellipse d'equateur credible et non un trait quelconque colle au cercle, comme `theo-archi` l'a
ecrit dans son avis de faisabilite (section 12). **C'est exactement le jugement que j'ai rendu en
section 1** : les trois boules passent ce test a l'oeil, pas seulement au compte de formes. Restent
en ma seule main, sans automate : la lisibilite de l'ellipse d'equateur (verifiee ce jour), le
rapport d'aretes cube/pave droit (verifie ce jour), et la plausibilite du choix d'indice par item
(`CON-6`, verifie ce jour).

---

## Bloc de validation

> **Done pedagogique : VALIDE.**
>
> **Prononce le 2026-09-22 par `nadia-prof`, apres avoir joue le jar servi et regarde les 15
> dessins a l'ecran, a leur taille reelle de rendu (176 px).**
>
> **Ce que j'ai verifie** : `SOL-CE1-1` a `SOL-CE1-4` (tenues, section 3), la lisibilite de
> l'ellipse d'equateur de la boule sur ses trois tailles (section 1, point d'attention explicite de
> ce sprint - **tenue, sans reserve**), la distinction cube/pave droit et cylindre/cone a l'ecran
> (section 2), le traitement de l'erreur jusqu'a la revelation (section 4), l'ancrage sur
> `MAT-CE1-16`, la lisibilite et l'absence de biais.
>
> **Mouvement de ligne** : ce Done ouvre la voie a `MAT-CE1-16` en `couvert-valide`. Le mouvement
> lui-meme est ecrit par `camille-po`, pas par moi.
