# Fiche de conformite - bandit-manchot-histoire-des-arts-relier-contexte-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-histoire-des-arts-relier-
  contexte.yaml` (11 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-histoire-des-arts-relier-contexte-cm1`, titre servi
  *"L'art dans son contexte"*, consigne servie *"Lis bien, puis relie l'oeuvre decrite a son
  epoque, sa civilisation ou son usage."*, `nombreDeManches: 10`.
- **Ligne declaree** : `HDA-CM1-05`. **Premier paquet d'histoire des arts du produit.**
- **Statut global** : **VALIDE**. Done pedagogique **prononce le 2026-09-23** par `nadia-prof`,
  apres avoir joue le jar servi.
- **Arbitrage prealable** : `squad/conformite/ARBITRAGE-PREALABLE-s40-relier-une-oeuvre-a-son-
  contexte-hda-cm1-05.md` (`HDA-1` a `HDA-4`, allouees dans `squad/decisions/REGISTRE-
  identifiants.md`). Referentiel : `squad/programme/histoire-des-arts/competences-cm1.md`, ligne 5.
- **Derniere mise a jour** : 2026-09-23 (premier audit, s40).

---

## 1. Ce que j'ai joue

Reacteur construit (`mvn -B clean package -DskipTests` via `bash outils/mvn-jdk21.sh`, worktree
`s40-nadia-2` a `652cafc8`), `bootstrap/target/recreo.jar` lance sur le JDK 21 designe par la
toolchain. Partie complete de dix manches jouee jusqu'a l'ecran de fin (10 reussites sur 10), les
six familles de style vues a l'ecran (gothique, roman, temple grec, temple romain, art funeraire
egyptien, chateau fort) et les trois taches (epoque, civilisation, usage).

## 2. `HDA-1` a `HDA-4` - verifies un par un, sur les onze items

- **`HDA-1` (style, jamais une oeuvre nommee)** : les onze enonces decrivent un type ("une
  eglise", "une abbaye", "un temple", "une construction", "un chateau"), aucun ne nomme une oeuvre
  precise, un lieu ou un artiste. Tenue.
- **`HDA-2` (classification etablie, jamais une interpretation)** : chaque reponse demandee est une
  periode (Moyen Age, Antiquite), une civilisation (grecque antique, romaine antique, egyptienne
  antique) ou un usage (lieu de culte, tombeau, defense) - jamais une intention d'artiste ni une
  datation disputee. Tenue.
- **`HDA-3` (distracteur = style reel, ecarte par un marqueur specifique)** : verifie sur la paire
  temple grec / temple romain, la plus exposee au risque (meme forme architecturale - colonnes et
  fronton triangulaire) : le marqueur qui les distingue est la langue de l'inscription et les dieux
  nommes (grecs contre latins et divinites protectrices de la cite), jamais les colonnes seules -
  exactement le croisement de deux traits que `HDA-3` exige. Sur les autres paires (roman/gothique :
  epaisseur des murs et taille des fenetres ; egyptien/autres : pyramide et momification ; chateau
  fort/autres : murailles, fosse, tours de guet), chaque distracteur reste un style reel et vrai en
  tant que tel, jamais une propriete fausse sur lui-meme. Tenue.
- **`HDA-4` (diversite du corpus)** : deux periodes (Antiquite, Moyen Age), quatre civilisations ou
  aires culturelles (Grece antique, Rome antique, Egypte antique, Europe medievale chretienne et
  feodale) et les trois usages du paquet (culte, tombeau, defense) sont tous representes, aucun
  n'ecrase les autres. Tenue.

## 3. `HIS-2`, `HIS-3` - verifies

- **`HIS-2` (aucune deuxieme lecture defendable)** : chaque style decrit porte au moins deux traits
  croises qui l'identifient sans ambiguite (ex. "colonnes et fronton triangulaire" seul ne
  distinguerait pas grec de romain, mais l'enonce ajoute la langue de l'inscription ou les dieux
  nommes). Aucun item ne repose sur un seul trait partage par plusieurs styles.
- **`HIS-3` (chaque fait porte sa source)** : les six classifications sont commentees dans le YAML
  avec leur justification (periode, civilisation, usage), coherentes avec le programme
  d'histoire-geographie CM1 deja servi par ce produit (`bandit-manchot-histoire-geographie-...-
  cm1`). Exactitude verifiee par moi : les six associations style/periode/civilisation/usage sont
  correctes (architecture romane anterieure a l'architecture gothique dans le Moyen Age europeen,
  temples grec et romain tous deux dedies au culte mais a des pantheons distincts, pyramides et
  momification egyptiennes funeraires, chateau fort medieval defensif).

## 4. `ART-1` - sans objet, verifie plutot que suppose

Un enonce nomme la couleur comme trait descriptif ("vitraux colores"). `ART-1` protege un enfant
qui ne peut pas percevoir une couleur **affichee a l'ecran** ; aucune image n'est servie sur cette
ligne, la couleur est un mot lu comme les autres. Conforme a ce que l'arbitrage prealable
anticipait deja.

## 5. Lisibilite, vocabulaire, ton, biais pour un CM1

Vocabulaire descriptif (colonnes, fronton, arcs, vitraux, murailles, momifie) du registre deja
rencontre par un CM1 en histoire-geographie sur les memes periodes. Enonces en deux phrases
(description, puis question), longueur raisonnable pour le cycle 3. Aucun biais : les six styles
sont traites au meme niveau de detail et de respect, aucune hierarchie implicite entre
civilisations.

## 6. Ce dont je reste la seule garde

`HDA-1` a `HDA-4`, `HIS-2` et l'exactitude de chaque fait de contexte (sections 2 et 3) ne sont
verifies par aucun garde-fou automatique. C'est moi qui les tiens, fait ce jour, comme je l'avais
deja annonce dans l'arbitrage prealable.

---

## Bloc de validation

> **Done pedagogique : VALIDE.**
>
> **Prononce le 2026-09-23 par `nadia-prof`, apres avoir joue le jar servi.**
>
> **Ce que j'ai verifie** : les 11 items, `HDA-1` a `HDA-4` (tenues), `HIS-2`, `HIS-3`, `ART-1`
> (sans objet ici), l'ancrage sur `HDA-CM1-05`, la lisibilite et l'absence de biais.
>
> **Mention a reprendre par `camille-po` dans `MATRICE-couverture.md`** (referentiel, section
> "Mention exigee d'avance si une ligne bouge") : *"reconnaissance et situation d'oeuvres seules ;
> aucun avis argumente ni aucune frequentation d'un lieu d'art n'est servi par ce produit."*
>
> **Mouvement de ligne** : ce Done ouvre la voie a `HDA-CM1-05` en `couvert-valide` - premiere
> ligne d'histoire des arts du produit. Le mouvement lui-meme est ecrit par `camille-po`, pas par
> moi.
