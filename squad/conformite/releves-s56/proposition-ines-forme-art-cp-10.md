# Proposition de forme - la marque d'echec, le plafond de 176 px et le pli de LIS-2 (s56, `S56-ART-CP-10-FORME`, `S56-ART-CP-10-ECHEC`)

- Auteure : `ines-design`.
- Date : 2026-10-09.
- Branche : `s56-ines-design`, issue de `round-two` a `dfcec37c`.
- Demande : `docs/backlog/backlog.md`, section Sprint 56 (`S56-ART-CP-10-FORME`, `S56-ART-CP-10-ECHEC`) ; `DEC-0116` 2.3 et 3.1 ; `cloture-nadia.md` de s55, sections 4.2 et 4.4 (`Q1`) ; `rapport-lea-dev-mecanique.md` de s55 (`E3`) ; `planning.md` de s56, action A15.
- Fichiers de cette proposition : ce document et, dans ce dossier, les vehicules de mesure (`vehicule-echec.html`, `mesure-echec.html`, `marque-echec-candidate.svg`, `vehicule-lis.html`, `mesure-lis.html`, `lis-items-mesures.js`) et six captures.

## 0. Ce que ce document est, et ce qu'il n'est pas

Une proposition de forme. **Aucun gabarit, aucune feuille de style, aucun controleur n'est modifie** : les regles candidates vivent dans le `<style>` d'un vehicule de mesure, apres les deux feuilles reelles (ordre d'`ADR-0014`), comme elles s'appliqueraient si elles etaient versees. Elle ne prononce aucun Done pedagogique. `nadia-prof` juge la marque (`Q1`), `theo-archi` dit la voie. Je ne tranche aucune des deux.

Ce qui est mesure l'est sur un vehicule statique, **pas sur le jar** : aucun paquet a reproductions n'est au catalogue, aucun ecran de ce dispositif ne peut etre rendu par l'application (meme limite que `squad/design/proposition-ines-s55-deux-oeuvres-ensemble.md`, section 9). Les cotes sont des `getBoundingClientRect()` sur les feuilles reelles du depot. Ce qui ne se voit qu'a l'oeil d'un humain qui joue est range en section 5, declare degrade.

## 1. La marque d'echec (`Q1`, `M3`)

### 1.1 Ce que le rendu livre montre aujourd'hui

`echec-base-744x551.png` : l'oeuvre pressee (la grande vague, deja claire) passe a 50 % et **ressemble a ses deux voisines a pleine opacite** : un papier clair a 50 % et un papier clair a 100 % ne se distinguent pas a l'oeil sur cette capture. C'est le constat de `nadia-prof` (section 4.2), mesure : le signal depend de ce que l'image montre.

### 1.2 Contraintes de lecture qui bornent la forme

| Contrainte | Source | Effet sur la forme |
|---|---|---|
| Rien d'ecrit ni de dessine dans la boite de l'image, aucun aplat | `X3`, `M3` precise le 2026-10-08 | La marque n'est ni sur l'image, ni derriere elle |
| Aucune difference de presentation avant le geste | `X6` | La marque n'existe que sur une proposition deja pressee |
| Un `border` sur un sujet `.proposition.deja-essayee` oblige ses trois etats (dette `ADR-0046`) | commentaire de `recreo.css`, regle `.propositions-oeuvres .proposition.deja-essayee img` | Pas de cadre pose sur le bouton |
| `opacity: 0.5` est posee sur l'`<img>` et **attenue aussi son cadre** | `recreo.css` | Un cadre pose sur l'image perd la moitie de son contraste (voir O2) |
| Un signe doit etre connu d'un cahier avant d'etre choisi pour son esthetique | mandat | Coche et croix, pas disque ni triangle |

### 1.3 Options

| | Marque | Canal de forme | Hors de la boite de l'image | Cout de hauteur |
|---|---|---|---|---|
| **O1** | Pastille portant une croix, accrochee **sous** la case pressee | Un signe : la croix | Oui, hors de l'image et hors du bouton | +40 px sur l'ecran d'echec et de revelation, 0 sur la manche fraiche |
| **O2** | Cadre en tirets de 4 px autour de l'image pressee | Le trait : tirets contre plein | Oui (bordure de l'`<img>`) | 0 |
| **O3** | O1 et O2 | Les deux | Oui | +40 px |

Dans tous les cas l'attenuation a 50 % reste : `nadia-prof` ecrit qu'elle *"peut s'y ajouter, jamais la remplacer"*.

**O1 est ma proposition.** Raisons, dans l'ordre :

1. **C'est la croix de l'ecran.** `.signal.est-faux .icone-signal` dessine deja une croix de deux barres, 48 px, sous le meuble, a chaque echec (`recreo.css`, "Croix : le signe que l'enfant connait deja de son cahier"). La meme croix sur la case dit "celle-la, c'est non" avec le signe que l'enfant vient de voir. Les tirets de O2 ne disent rien a un enfant de CP : ce n'est pas un signe de cahier.
2. **Elle ne depend pas de l'image.** Un disque clair a anneau d'encre sur le meuble sombre, quelle que soit la reproduction (contrastes en 1.5).
3. **Elle est lisible sans la couleur.** La forme porte le sens ; le rouge `#c62828` est le meme que celui du signal faux et n'est qu'un second canal.
4. **Les cases ne bougent pas.** La pastille pend sous la case, la rangee gagne de la hauteur en dessous seulement quand une case est pressee (`:has(.deja-essayee)`) : l'enfant ne voit aucune case se deplacer entre deux essais.

**O2 en repli, et ce qu'il vaut** (`echec-B-744x551.png`) : les tirets sont portes par l'`<img>` et donc attenues a 50 % avec elle. Encre `#1c2733` a 50 % sur le fond de case `#f4f7fb` donne `#888f97`, soit **3,04:1** contre la case, au ras du plancher de 3:1 d'un composant non textuel. Il se voit sur la capture, faiblement ; c'est un jugement a l'oeil, a confirmer par un humain (section 5).

### 1.4 Regle candidate de O1 (a verser seulement apres l'avis de `nadia-prof` et la voie de `theo-archi`)

```css
.propositions-oeuvres:has(.proposition.deja-essayee) {
    padding-bottom: 3.25rem;
}

.propositions-oeuvres .proposition.deja-essayee::before {
    content: "";
    position: absolute;
    top: calc(100% + 0.25rem);
    left: 50%;
    width: 2.5rem;
    height: 2.5rem;
    margin-left: -1.25rem;
    background: url('/images/marque-echec.svg') center / contain no-repeat;
    pointer-events: none;
}
```

`marque-echec.svg` (295 octets, `marque-echec-candidate.svg` dans ce dossier) : un disque `#f4f7fb` a anneau `#1c2733` de 2 px, une croix `#c62828` de 5 px a bouts ronds. Aucune couleur neuve : les trois viennent de `:root` et du signal faux. Aucune modification de gabarit : `deja-essayee` existe, `::before` est libre sur `.proposition` (seul `::after` y est regle).

Taille : 2,5 rem (40 px), un peu moins que la croix du signal faux (3 rem, 48 px). A 3 rem la pastille couterait 8 px de plus et, a 360 px, passerait la moitie de la largeur d'une case (90,7 px) ; a 2 rem elle gagnerait 8 px et deviendrait plus petite que la croix deja vue. Ce n'est pas une cible tactile : `pointer-events: none`.

### 1.5 Mesures

Commande de capture (section 6) et de cotes (section 6). Perimetre : meuble conserve, trois propositions, dix largeurs, vehicule `vehicule-echec.html`.

**Contrastes** (luminance relative WCAG 2.x, calcul reproductible) : croix `#c62828` sur disque `#f4f7fb` **5,23:1** ; disque sur meuble `#081c2c` **16,11:1** ; anneau d'encre sur disque **14,09:1**.

**Boites, `opt=A`, etat d'echec** (une case pressee), en px, origine en haut de la page :

| Largeur | Disposition | Pastille (haut - bas) | Au-dessus du pli de 551 ? |
|---|---|---|---|
| 360 a 390 | empilee | 678 - 718 a 640 - 680 | non (comme le reste de l'ecran, voir 1.6) |
| 576, 640 | empilee | 632 - 672 | non |
| 720, 744, 768 | "cote" | 437 - 477 | **oui, marge de 74 px** |
| 992, 1200, 1440 | "cote" | 481 - 521 | **oui, marge de 30 px** |

Controles faits sur les 160 cas (10 largeurs, 4 etats, 4 options) : **aucun chevauchement** de la pastille avec une image, une case ou le socle du meuble ; aucune pastille hors du cadre du meuble ni hors de l'ecran ; aucun defilement horizontal.

### 1.6 Prix en hauteur, et pli de l'etat d'echec (`Q3`)

Bas du contenu, en px, "cote" sans le levier du meuble. `base` est le rendu livre, `O1` la regle de 1.4.

| Largeur | Fraiche base / O1 | Echec base / O1 | Revelation base / O1 |
|---|---|---|---|
| 720, 744, 768 | 470 / 470 | 588 / **628** | 622 / **662** |
| 992, 1200, 1440 | 514 / 514 | 632 / **672** | 666 / **706** |
| 576, 640 (empilee) | 665 / 665 | 783 / 823 | 817 / 857 |
| 360 (empilee) | 711 / 711 | 859 / 899 | 923 / 963 |
| 390 (empilee) | 673 / 673 | 821 / 861 | 885 / 925 |

Lecture contre 744 x 551 : la manche fraiche tient (470), **l'echec depassait deja** (588, soit 37 px) et depasse de 77 px avec O1 ; la revelation depasse de 71 px et passerait a 111. Ce qui depasse est le bas du bloc de message (indice, explication), pas la pastille, qui reste visible (477). Le depassement est un fait de `Q3` ; le pli est hors de la porte generale (`DEC-0051`, `DEC-0053`), et c'est `nadia-prof` qui juge si l'ecart de 40 px change sa reponse a `Q3`. Les 37 px de l'echec livre ne dependent pas de la marque : O2 les laisse tels quels.

## 2. Le plafond de 176 px

### 2.1 Ce que la mesure rend

`ADR-0104` 4.3 nomme 176 px, soit 11 rem, le plafond de `.image-item`. Mesure sur le vehicule, a 992 px et plus :

| Image | Boite (bordure comprise) | Image seule | Plafond declare |
|---|---|---|---|
| Reference (`.illustration .image-item`) | 176 px | 176 px | `max-height: 11rem` |
| Proposition (`.propositions-oeuvres .proposition img`) | **178 px** | 174 px | **`max-height: 100%`** (valeur calculee lue sur le navigateur) |

Deux faits :

1. **176 n'est declare sur aucune des images de proposition.** La regle `.propositions-oeuvres .proposition img` pose `max-height: 100%` et `max-width: 100%`, qui l'emportent sur les 11 rem de `.image-item`. Le plafond est un effet de la case : `max-width: calc(11rem + 0.5rem + 0.25rem)` donne 188 px, moins le cadre du bouton (1 px de chaque cote) et son rembourrage (4 px de chaque cote), soit 178 px pour la boite de l'image. C'est ce que la mutation de `S55-ART-CP-10-PLAFOND` a mesure : monter le 11 rem de la case ne fait rougir rien.
2. **La proposition est 2 px plus haute que la reference** (178 contre 176). Mon mandat dit que ce que l'enfant doit reconnaitre en premier n'est jamais plus petit que ce qu'il presse : la reference reste 2 px sous la proposition a 992 px et plus. L'ecart est petit et il n'a pas de motif.

Sous 992 px la case est le facteur limitant et rien ne change (134 px a 744, 140,7 a 576).

### 2.2 La forme proposee : le plafond sur l'image elle-meme

```css
.propositions-oeuvres .proposition img {
    max-width: min(100%, 11rem);
    max-height: min(100%, 11rem);
}
```

Remplace les deux `100%` actuels. Une seule valeur, 11 rem, portee par l'image, sur ses deux axes, bordure comprise. Mesure (`opt=P`) : **176 px a 992, 1200 et 1440** (portrait, paysage et paysage allonge), **134, 140,7 et 80,7 inchanges** a 744, 576 et 360. La case reste a 188 px : seul l'ecart de 2 px disparait.

Ce que cette forme donne au garde-fou de `S55-ART-CP-10-PLAFOND` : le plafond est lisible dans `recreo.css` sur l'image, a cote de celui de `.image-item`, et `theo-archi` peut deriver la valeur sans calculer la case. La forme du garde-fou est a lui ; je dis seulement que la regle ci-dessus lui donne quelque chose a egaler.

Ce que je ne juge pas : si 174 px d'image au lieu de 178 change la visibilite de la propriete (`Q8`, a l'audit).

## 3. Le pli de `LIS-2` pour `cm1-lire-et-restituer` (A15)

**Faisable sans coder : oui**, sur un vehicule statique hors `adapters/`, avec les feuilles reelles, comme la mesure de s42 (`squad/conformite/mesure-ines-lis-pli-s42.md`, meme reference de 744 x 551, `DEC-0040`). **Degrade** : le paquet n'est pas au catalogue, l'application ne peut pas le servir, aucun rendu n'a ete fait sur le jar. La consigne est un marqueur d'une ligne. Je ne modifie ni gabarit ni feuille.

Difference avec s42 : cette fois ce sont **les vingt items reels du fichier** (45 a 53 mots, enonce de 6 mots, propositions de 21 a 39 caracteres), plus un cas au plafond de 70 mots avec les propositions les plus longues du paquet (item 6).

### 3.1 Chiffres, 744 x 551 (identiques a 720 et 768)

Cotes verticales, origine en haut de la page, pli a 551 :

| Bloc | 17 items sur 20 | Items 3, 5 et 17 | Cas de 70 mots |
|---|---|---|---|
| `.texte-support` | 186 - 306 (4 lignes) | 186 - 306 | 186 - 366 (6 lignes) |
| `.enonce` | 310 - 358 | 310 - 358 | 370 - 418 |
| 1re proposition | 402 - 468 | 402 - 468 | 462 - 528 |
| 2e proposition | 484 - 550 | 402 - 468 (items 3 et 17, meme rangee que la 1re) ou 484 - 550 (item 5) | 544 - 610 (**7 px visibles** sur 66) |
| 3e proposition | **566 - 632, hors champ** | 484 - 550 (deux rangees) | **626 - 692, hors champ** |
| `.cabinet` (bas) | **656, depasse de 105 px** | 574, depasse de 23 px | 716, depasse de 165 px |

Les vingt items rendent trois mises en page seulement, parce que la longueur d'un texte de 45 a 53 mots donne toujours quatre lignes a 744 px. A 744, les propositions tiennent **une par rangee** (66 px, jour de 16 px) pour 17 items ; trois items (3, 5, 17) ont deux propositions assez courtes pour partager une rangee.

### 3.2 Ce que les chiffres disent

- `texteSupport` et `enonce` tiennent au-dessus du pli, avec 245 px et 193 px de marge au plancher du paquet, 185 px et 133 px au plafond de 70 mots.
- Pour **17 items sur 20**, la 3e proposition est entierement sous le pli, 15 px apres lui ; la 2e finit a 550, **1 px de marge**. Pour 3 items sur 20, les trois propositions sont visibles mais le socle du meuble depasse de 23 px.
- **Au plafond de 70 mots**, la 2e proposition n'est visible que sur 7 px et la 3e est hors champ : la conclusion de s42 tient avec les propositions courtes du paquet reel.
- Raccourcir le texte ne ramene pas le meuble dans le pli : le plancher de `LIS-2` (45 mots) rend deja quatre lignes, soit 186 - 306 ; pour faire tenir les trois propositions il manquerait 105 px, plus de trois lignes de texte.

### 3.3 Ce que je ne conclus pas

- Je ne dis pas si `LIS-2` doit etre releve ou abaisse, ni si la ligne doit s'ouvrir : c'est `camille-po` pour le perimetre et `nadia-prof` pour le motif ; le pli est hors de la porte generale (`DEC-0053`).
- Je ne propose aucune correction de forme (une rangee de trois propositions, un meuble allege) : c'est une decision de gabarit que je route plutot que je ne la dessine ici.
- Je n'ai mesure ni une autre hauteur de viewport (`vehicule-lis.html` ne contient aucune regle fonction de `vh`, les cotes ci-dessus ne dependent pas de la hauteur, comme en s42), ni un 1024 de large au-dela du releve brut (les propositions 2 et 3 y partagent une rangee : non analyse).

## 4. Ce que je route

| A | Question | Pourquoi |
|---|---|---|
| `nadia-prof` | `Q1` : O1, O2 ou O3 ? Et la lecture de `X3` et de `X2` pour une pastille **sous** la case, hors du bouton : un signe n'est ni un numero, ni une lettre, ni une legende, mais elle seule dit si c'est "sous une image" au sens de `X2` | Veto pedagogique. Si elle lit O1 comme un `X2`, O2 reste, avec les 3,04:1 de 1.3 |
| `nadia-prof` | `Q3` : les 40 px de plus sur l'echec et la revelation | 1.6 |
| `theo-archi` | La voie de O1 : un `::before` sur un sujet existant, un SVG statique sous `static/images/` (`cabinet-fronton.svg` est le precedent), aucun gabarit touche. Le garde-fou de plafond (section 2). Je n'ai **lance aucun garde-fou** : `HabillageDesEcransTest`, le garde-fou de pseudo-elements d'`ADR-0027` et la derivation d'`ADR-0046` n'ont pas ete executes sur la regle candidate | Architecture |
| `lea-dev` | Ecrire la regle une fois `Q1` repondu. Le critere de `S56-ART-CP-10-ECHEC` s'ajoute par amendement a l'entree du backlog avant l'ouverture du worktree | Hors de mon mandat |
| `sofia-sm` | Rien cote controleur : `deja-essayee` et `propositionsEssayees` existent deja | Aucune donnee neuve |
| `camille-po` | La mesure de LIS-2 (section 3) rend son verdict a A15 ; le perimetre de s57 est le sien | Produit |
| `ines-design` | `Q7` (releve a dix largeurs sur le gabarit livre) apres `Q1` et apres le versement de la regle | `cloture-nadia.md` 4.4 |

## 5. Ce que seul un humain qui joue voit : degrade

- **Les deux jugements "a l'oeil" de ce document** : une reproduction claire a 50 % ressemble a ses voisines (1.1) et les tirets de O2 se voient faiblement (1.3). Ce sont des lectures de capture, pas des mesures.
- **La croix se reconnait-elle d'un enfant de CP a 40 px, et dit-elle "pas celle-la" ?** Aucune mesure ne repond.
- **L'interaction avec l'anneau de focus clavier.** Apres un echec le focus arrive sur la case pressee (`data-focus-cible`). L'anneau est un `box-shadow` interieur de 3 px (`recreo.css`, `S17-1`) : il n'atteint pas la pastille, qui est sous la case. Mais la capture headless le montre de facon inegale d'une capture a l'autre (visible sur `echec-A`, `echec-B` et `revelation-A`, absent sur `echec-base`) : je n'en conclus rien, c'est le point `M4` a l'ecran que `nadia-prof` a deja laisse a l'audit.
- **Le retrait du meuble** (`Q5`) : la pastille a ete rendue sur le meuble sombre. Sans meuble elle serait sur le fond de page `#f4f7fb` : le disque ne se verrait plus, seul l'anneau d'encre (14,09:1 par le calcul) la delimiterait. **Non rendu.**
- **Le dalton.** Les contrastes sont calcules en luminance. La croix rouge a 5,23:1 contre son disque se lit aussi en niveaux de gris ; aucune simulation de daltonisme n'a ete faite.
- **La visibilite de la propriete rapprochee a 132-137 px** (`Q8`) est a l'audit de `nadia-prof`, comme prevu.

## 6. Commandes

`CHROME` designe un Chrome ou Chromium. Racine du depot : `<racine>`.

Captures, a 744 px de large et 551 de haut (`ADR-0025`, `ADR-0028`) :

```text
bash outils/mesurer-rendu.sh 744 "file:///<racine>/squad/conformite/releves-s56/vehicule-echec.html?etat=echec&opt=base" echec-base-744x551.png 551
bash outils/mesurer-rendu.sh 744 "file:///<racine>/squad/conformite/releves-s56/vehicule-echec.html?etat=echec&opt=A" echec-A-744x551.png 551
bash outils/mesurer-rendu.sh 744 "file:///<racine>/squad/conformite/releves-s56/vehicule-echec.html?etat=echec&opt=B" echec-B-744x551.png 551
bash outils/mesurer-rendu.sh 744 "file:///<racine>/squad/conformite/releves-s56/vehicule-echec.html?etat=revelation&opt=A" revelation-A-744x551.png 551
bash outils/mesurer-rendu.sh 744 "file:///<racine>/squad/conformite/releves-s56/vehicule-lis.html?i=0" lis-item0-744x551.png 551
bash outils/mesurer-rendu.sh 744 "file:///<racine>/squad/conformite/releves-s56/vehicule-lis.html?i=20" lis-plafond70-744x551.png 551
```

Cotes (les iframes de largeur exacte contournent le plancher de fenetre de Chrome headless, `ADR-0025` ; `mesurer-rendu.sh` ne rend que des captures, les `getBoundingClientRect()` viennent de ces deux pilotes). Le resultat est le JSON de l'element `#sortie` du DOM imprime :

```text
"$CHROME" --headless=new --disable-gpu --allow-file-access-from-files --user-data-dir=<profil-isole-jetable> --virtual-time-budget=150000 --dump-dom file:///<racine>/squad/conformite/releves-s56/mesure-echec.html
"$CHROME" --headless=new --disable-gpu --allow-file-access-from-files --user-data-dir=<profil-isole-jetable> --virtual-time-budget=150000 --dump-dom file:///<racine>/squad/conformite/releves-s56/mesure-lis.html
```

`mesure-echec.html` : 160 cas (10 largeurs, etats `fraiche`, `echec`, `echec2`, `revelation`, options `base`, `A`, `P`, `AP`). `mesure-lis.html` : 84 cas (720, 744, 768, 1024 de large, 21 items). Arbre de depart : `dfcec37c`.

## Impact

- Ce document et le contenu de `squad/conformite/releves-s56/` : crees.
- Aucun fichier sous `domain/`, `application/`, `adapters/` ou `bootstrap/` n'est modifie.
