# Choix des oeuvres du paquet a reproductions d'`ART-CP-10` (`S56-ART-CP-10-OEUVRES`)

`nadia-prof`, instance B, le 2026-10-09 (21:37, UTC+02:00). Branche `s56-nadia-b`, a jour de `round-two` a `a772c822`. Poste finance par la reponse
de l'utilisatrice du 2026-10-09, *"Oui, a 4,75"* (transmise par l'agent qui me lance ; `PAR-2` tenue, B a 4,75).

**Ce que ce document fait** : il donne a `lea-dev` les six items du paquet, oeuvre par oeuvre, avec les mots lus par l'enfant, et la liste
exacte des reproductions a restaurer (T4). Elle recopie ; elle ne choisit et ne redige rien. **Ce qu'il ne fait pas** : aucun Done, aucun
statut de matrice ; l'audit (`S56-AUD-ART-CP-10`) juge le paquet en jouant. Aucun identifiant neuf.

**Comment j'ai choisi** : sur les images elles-memes. Les 28 reproductions de `1db6348f^` (avis de `theo-archi`, `AV2` : provenance prouvee,
restaurables a l'octet) extraites par `git show` dans mon scratchpad, hors depot, et regardees une a une pour les douze qui servent ou
pouvaient servir ; formats lus dans l'en-tete `SOF` de chaque JPEG. Regles appliquees : 1 a 5 de mon arbitrage de s55 (instance A, 3.4),
`B1` a `B10` de mon arbitrage prealable de s56 (instance B).

## 1. Une correction de mon propre exemple, avant de choisir

La regle 2 donnait en exemple, pour Vermeer, le sujet *"une femme pres d'une fenetre"*. **La fenetre fait partie de la propriete, pas du
sujet** : la propriete rapprochee est la lumiere d'une fenetre a gauche qui eclaire la femme et le mur. Le sujet partage est *une femme dans
une piece*. Lu comme je l'avais ecrit, aucun distracteur ne pourrait partager le sujet sans la propriete, puisqu'une fenetre a gauche dans un
interieur peint en apporte la lumiere. La regle ne change pas ; mon exemple etait mal decoupe.

## 2. Les six items

Trois paires, chacune dans les deux sens (`B3`) : **six items, `nombreDeManches: 5`** (`ADR-0023`). Formats : L en largeur, H en hauteur.

| Item | Reference | Bonne oeuvre | Distracteurs | Regle 2 : ce qui piege par le sujet | Formats ref / bonne / distracteurs |
|---|---|---|---|---|---|
| 1 | `oeuvre-chambre` | `oeuvre-champ-de-ble` | `oeuvre-moissonneurs`, `oeuvre-jeune-femme-aiguiere` | la bonne ne partage pas le sujet (une piece, un champ) ; `aiguiere` partage le sujet de la reference (une piece) sans la touche ; `moissonneurs` partage le sujet de la bonne (un champ de ble) sans la touche | L / L / L, H |
| 2 | `oeuvre-champ-de-ble` | `oeuvre-chambre` | `oeuvre-moissonneurs`, `oeuvre-femme-au-luth` | `moissonneurs` partage le sujet de la reference (un champ de ble) sans la touche | L / L / L, H |
| 3 | `oeuvre-grande-vague` | `oeuvre-averse-sur-le-pont` | `oeuvre-rue-sous-la-pluie`, `oeuvre-quatre-saisons` | la bonne partage l'eau ; `rue-sous-la-pluie` aussi (la pluie, les paves mouilles), sans la maniere de l'estampe | L / H / L, H |
| 4 | `oeuvre-averse-sur-le-pont` | `oeuvre-grande-vague` | `oeuvre-rue-sous-la-pluie`, `oeuvre-marche-aux-chevaux` | idem : `rue-sous-la-pluie` partage la pluie sans la maniere | H / L / L, L |
| 5 | `oeuvre-femme-au-luth` | `oeuvre-jeune-femme-aiguiere` | `oeuvre-autoportrait-leyster`, `oeuvre-chambre` | la bonne partage le sujet (une femme dans une piece) ; `autoportrait-leyster` aussi (une femme dans une piece, sans fenetre ni mur eclaire) ; `chambre` montre une piece avec une fenetre, sans sa lumiere | H / H / H, L |
| 6 | `oeuvre-jeune-femme-aiguiere` | `oeuvre-femme-au-luth` | `oeuvre-autoportrait-leyster`, `oeuvre-moissonneurs` | idem, `autoportrait-leyster` | H / H / H, L |

**Verification, item par item** :

- **Regle 2, premiere clause** : aucun distracteur ne partage la propriete. Ni Bruegel ni Vermeer n'ont la touche visible de Van Gogh ;
  Caillebotte peint la pluie sans les traits de l'estampe ; Leyster peint une femme sur un fond sombre, sans fenetre ni lumiere sur un mur.
  `oeuvre-grande-jatte` (Seurat) est ecartee des items de Van Gogh : ses points sont une touche visible, elle partagerait la propriete.
- **Regle 2, seconde clause** : items 3 a 6, la bonne oeuvre partage le sujet, et un distracteur le partage sans la propriete
  (`rue-sous-la-pluie`, `autoportrait-leyster`). Items 1 et 2 : sans objet, et un distracteur piege quand meme par le sujet.
- **Regle 3** : quand la bonne partage le format de la reference (items 1, 2, 5, 6), un distracteur le partage aussi.
- **`B5`** : la bonne n'est jamais seule de son format parmi les trois propositions (item 3 : `averse` H et `quatre-saisons` H ; item 4 :
  trois L).
- **`B6`** : aucune statuette ni objet en volume ; six peintures et une estampe en plus des deux de la paire. `oeuvre-hippopotame` absente.
- **`B3`** : chaque oeuvre d'une paire est reference une seule fois ; aucun couple (reference, bonne) ne revient.
- **`X5`** : aucune proposition n'est la reference, ni un detail, un recadrage ou un miroir d'elle.
- **`ART-5`** : une artiste femme entre au paquet (Judith Leyster) ; Europe et Japon, du XVIe au XIXe siecle.

**Ce que je jugerai a l'ecran, a l'audit, et qui peut faire sortir une paire** (regles 1 et 6, `Q8`) : la touche de `chambre` a 132 px,
plus fine que celle du champ ; la lumiere de `femme-au-luth`, tableau sombre et use ; la maniere de l'estampe a 84 px. Et un risque que je
nomme sans le trancher : les deux estampes portent chacune un cartouche ecrit en japonais. Un enfant peut les rapprocher par ce cartouche
plutot que par l'eau. C'est encore rapprocher sur ce qu'on voit, pas sur le sujet ; l'indice dirige le regard vers l'eau.

## 3. Le fichier, a recopier

Mots lus par l'enfant : les miens (`CON-5`). Enonce et texte alternatif identiques sur les six items (`B2`, `B8`). **L'espace devant le
point d'interrogation de l'enonce est une espace insecable U+00A0** (`TYP-1`). Indices de huit mots au plus, pour tenir sur une ligne a
744 px (`Q3` du fichier de forme). L'en-tete de commentaire, l'ordre des cles et le nom du fichier sont a `lea-dev` ; `descripteur` sur le
modele de `cp-decrire-une-oeuvre.yaml`, avec la competence de la ligne `ART-CP-10` du referentiel.

```yaml
lignesDeReferentiel: ["ART-CP-10"]
items:
  - enonce: "Quelle œuvre est faite de la même façon que celle-ci ?"
    illustration: oeuvre-chambre
    illustrationTexteAlternatif: "Une reproduction d'œuvre à regarder attentivement avant de répondre."
    bonneReponse: "oeuvre-champ-de-ble"
    distracteurs: ["oeuvre-moissonneurs", "oeuvre-jeune-femme-aiguiere"]
    indice: "Regarde bien les traits de peinture."
    explication: "Dans ces deux œuvres, on voit chaque trait du pinceau."
  - enonce: "Quelle œuvre est faite de la même façon que celle-ci ?"
    illustration: oeuvre-champ-de-ble
    illustrationTexteAlternatif: "Une reproduction d'œuvre à regarder attentivement avant de répondre."
    bonneReponse: "oeuvre-chambre"
    distracteurs: ["oeuvre-moissonneurs", "oeuvre-femme-au-luth"]
    indice: "Regarde bien les traits de peinture."
    explication: "Dans ces deux œuvres, on voit chaque trait du pinceau."
  - enonce: "Quelle œuvre est faite de la même façon que celle-ci ?"
    illustration: oeuvre-grande-vague
    illustrationTexteAlternatif: "Une reproduction d'œuvre à regarder attentivement avant de répondre."
    bonneReponse: "oeuvre-averse-sur-le-pont"
    distracteurs: ["oeuvre-rue-sous-la-pluie", "oeuvre-quatre-saisons"]
    indice: "Regarde comment l'eau est dessinée."
    explication: "Dans ces deux œuvres, l'eau est dessinée avec des traits fins."
  - enonce: "Quelle œuvre est faite de la même façon que celle-ci ?"
    illustration: oeuvre-averse-sur-le-pont
    illustrationTexteAlternatif: "Une reproduction d'œuvre à regarder attentivement avant de répondre."
    bonneReponse: "oeuvre-grande-vague"
    distracteurs: ["oeuvre-rue-sous-la-pluie", "oeuvre-marche-aux-chevaux"]
    indice: "Regarde comment l'eau est dessinée."
    explication: "Dans ces deux œuvres, l'eau est dessinée avec des traits fins."
  - enonce: "Quelle œuvre est faite de la même façon que celle-ci ?"
    illustration: oeuvre-femme-au-luth
    illustrationTexteAlternatif: "Une reproduction d'œuvre à regarder attentivement avant de répondre."
    bonneReponse: "oeuvre-jeune-femme-aiguiere"
    distracteurs: ["oeuvre-autoportrait-leyster", "oeuvre-chambre"]
    indice: "Regarde d'où vient la lumière."
    explication: "Dans ces deux œuvres, la lumière entre par une fenêtre, à gauche."
  - enonce: "Quelle œuvre est faite de la même façon que celle-ci ?"
    illustration: oeuvre-jeune-femme-aiguiere
    illustrationTexteAlternatif: "Une reproduction d'œuvre à regarder attentivement avant de répondre."
    bonneReponse: "oeuvre-femme-au-luth"
    distracteurs: ["oeuvre-autoportrait-leyster", "oeuvre-moissonneurs"]
    indice: "Regarde d'où vient la lumière."
    explication: "Dans ces deux œuvres, la lumière entre par une fenêtre, à gauche."
```

Entree de catalogue (`B9`), au commit de branchement : titre *"Des œuvres faites de la même façon"*, consigne *"Regarde l'œuvre, puis trouve
celle qui est faite de la même façon."*, `nombreDeManches: 5`.

**Exactitude, verifiee sur les images** : Van Gogh, touche visible sur les deux ; Hokusai et Hiroshige, eau et pluie en traits fins sur les
deux ; Vermeer, fenetre a gauche sur les deux, lumiere sur la femme et le mur. Aucun indice ne nomme le sujet de la bonne oeuvre : *"l'eau"*
et *"la lumière"* sont ou regarder (regle 5), et un distracteur les montre aussi (`rue-sous-la-pluie`, `autoportrait-leyster`), donc ils
ne designent pas la reponse.

## 4. T4 : ce que `lea-dev` restaure

**Quatre reproductions**, chacune declaree par au moins un item (*"aucune ne dort"*), restaurees a l'octet depuis `1db6348f^` avec leur
entree de registre :

| Reproduction | Octets (avis de `theo-archi`, `AV4`) | Role |
|---|---|---|
| `oeuvre-champ-de-ble` | 31 398 | paire Van Gogh |
| `oeuvre-averse-sur-le-pont` | 21 707 | paire des estampes |
| `oeuvre-jeune-femme-aiguiere` | 21 336 | paire Vermeer, distracteur de l'item 1 |
| `oeuvre-autoportrait-leyster` | 24 248 | distracteur des items 5 et 6 (registre de `1db6348f^` : National Gallery of Art, `1949.6.1`, `CC0-1.0`, lu par moi) |

Constantes, par la methode d'`AV4` (mon arithmetique ; le cliquet exact juge) : `REPRODUCTIONS_SERVIES` 8 -> **12** ; `POIDS_TOTAL_MAXIMUM`
176 474 -> **275 163** (176 474 + 31 398 + 21 707 + 21 336 + 24 248) ; `ITEMS_A_PROPOSITIONS_EN_IMAGE` 0 -> **6** ; `ITEMS_QUI_DECLARENT_UNE_ILLUSTRATION`
81 -> **87**. **Les valeurs 11 et 250 915 d'`ADR-0104` ne valent plus** : elles supposaient trois restaurations, il y en a quatre. Toute autre
constante qu'un garde-fou fait rougir dans le commit du paquet est nommee dans `rapport-lea-dev.md`, avec sa valeur avant et apres.

## 5. Prix, et ce dont je reste la seule garde

**0,25** (`S56-ART-CP-10-OEUVRES`, sur B). Je reste la seule garde, sans automate : le sujet et la propriete de chaque oeuvre (ils ne se
lisent que sur l'image), la fidelite des reproductions, l'exactitude des explications, la plausibilite des distracteurs. Ce qu'un test peut
mordre, si `theo-archi` le juge du : `B3`, `B5` (formats lus dans les JPEG), `B6`, les six couples ci-dessus.

**Signe** : `nadia-prof`, s56, instance B, 2026-10-09.

---

## Bloc du 2026-10-09, 22:07 - `K1` : les explications restent, issue (b) - nadia-prof UNIQUEMENT

Source : amendement de l'avis de `theo-archi` (`3f5d68c7`, section A.3, `K1`), lu sur `round-two` fusionne dans `s56-nadia-b`. Le paquet de la
section 3 porte une `explication` sur ses six items et aucune `graphie` ; la regle 1 d'`ADR-0024` le fait entrer dans un controle ecrit pour
le francais, et rougit.

**Je choisis (b) : les six explications restent ; `theo-archi` amende `ADR-0024` pour que le controle de sa regle 1 n'entre que sur `graphie`.**
Le fichier de la section 3 ne change pas.

**Pourquoi, cote enfant.** La revelation est le seul moment ou l'enfant voit les deux oeuvres ensemble avec la bonne designee (`X7` : rien a
l'ecran de fin). La phrase de `M4` lui dit *laquelle* ; seule l'explication lui dit *pourquoi* : *"l'eau est dessinée avec des traits fins"*,
*"la lumière entre par une fenêtre, à gauche"*. Sans elle, un enfant qui s'est trompe deux fois apprend qu'il s'est trompe, pas ce qu'il
fallait regarder : c'est le *"faux"* qui n'apprend rien. L'explication est aussi ma regle 5 (arbitrage A de s55, 3.4), ecrite avant le
paquet ; la retirer pour faire passer un garde-fou concu pour l'accord en francais serait plier le contenu a un outil qui ne le vise pas.

**Ce que (b) coute, et qui le garde.** Une faute de frappe sur la cle (`explicaton`) portee par tous les items d'un paquet sans graphie ne
rougit plus : les explications disparaissent sans bruit. Sur ce paquet, **je reste la garde** : a l'audit, je pousse chaque item jusqu'a la
revelation et je lis les six explications a l'ecran ; une explication absente est un motif de refus de ce paquet. Pour les paquets suivants,
le trou est a `theo-archi` (un garde-fou qui refuserait une cle inconnue dans un item est une autre regle, que je ne demande pas ici).

**Ce que je demande de l'amendement**, sans en choisir la forme : qu'un paquet sans graphie porte `explication` sur tous ses items ou sur
aucun (`theo-archi`, A.3) ; ce paquet la porte sur ses six. Ordre : l'amendement avant le commit du paquet. Prix : aucun pour moi.

**Signe** : `nadia-prof`, s56, instance B, 2026-10-09.
