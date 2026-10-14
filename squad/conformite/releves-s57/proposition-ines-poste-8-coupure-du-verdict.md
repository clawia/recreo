# Proposition - la coupure du verdict de l'echec entre ses deux phrases (poste 8 de s57, `Q-T1`)

- Auteure : `ines-design`.
- Date : 2026-10-14, 22:14 (UTC+02:00), lu par `date`.
- Branche : `s57-ines-forme2`, issue de `round-two` a `9229ca28`.
- Demande : `reponse-nadia-forme-poste-8.md` section 5 (la coupure tombe entre les deux phrases, sans dependre de la police) ; `avis-theo-poste-8-art-cp-10.md` 2.3 et `Q-T1`.
- Fichiers : ce document et `coupure-poste-8.mjs` (le pilote de mesure). **Aucun gabarit, aucune feuille, aucun controleur n'est modifie.** C'est une proposition : `nadia-prof` juge ce que
  l'enfant lit, `theo-archi` la dependance et l'`ADR`, `sofia-sm` et `lea-dev` le code.

## 0. Resume

**Reponse a `Q-T1` : la feuille seule ne tient pas la condition, et c'est mesure.** Sur 27 cas (3 largeurs, 9 polices), la mise en page actuelle coupe 6 fois a l'interieur d'une phrase
(*"Ce n'est pas / ça. Essaie / encore !"*). Deux formes tiennent sur les 27 cas : des espaces insecables a l'interieur de chaque phrase (forme C, un fichier de code), ou un balisage
d'une phrase par element (forme B, controleur et gabarits). **Je recommande C avec un juge.**

Le verdict est un seul `<p class="verdict">` dont le texte vient du modele, et la meme chaine sert les trois mecaniques (bandit manchot, designation, saisie). **La coupure d'`ART-CP-10`
n'est donc pas separable des autres jeux** : elle corrige aussi, a 360 et 390 px, une coupure fautive deja servie (section 3).

## 1. Ce qui coupe aujourd'hui

`PartieController` pose `MESSAGE_ECHEC` (*"Ce n'est pas ça. Essaie encore !"*) aux lignes 73, 575 a 587, 659 a 671 et 736 a 745, avec des espaces ordinaires, y compris avant le `!`. Le gabarit
le rend tel quel (`manche.html` 195, `designation.html` 72, `saisie.html` 117). Le navigateur peut couper a chaque espace.

Mesure sur le jar, a l'echec, avec les regles de la revision 2 de `proposition-ines-poste-8-art-cp-10.md` (colonne du signal de 15 rem). Forme A = balisage actuel.

| Largeur | Polices | Coupes a l'interieur d'une phrase (forme A) |
|---|---|---|
| 744 | neuf polices | 2 : Verdana (*"Ce n'est pas / ça. Essaie / encore !"*) et Courier New |
| 720 | neuf polices | 2 : les memes |
| 992 | neuf polices | 2 : les memes |

La police du poste, Arial, Georgia, Times New Roman, Comic Sans MS, Trebuchet MS et Tahoma coupent entre les phrases, **par chance de largeur** : la condition de `nadia-prof` ne doit pas
dependre de la police.

## 2. Trois formes

| Forme | Ce qu'elle fait | Coupes dans une phrase, sur 27 cas | Debordement du bandeau |
|---|---|---|---|
| A | rien (actuel) | **6** | 0 |
| B | une phrase par `<span>` en `white-space: nowrap`, separes par une espace | **0** | 3 (Courier New) |
| C | espaces insecables entre les mots de chaque phrase, une espace ordinaire entre les phrases | **0** | 3 (Courier New) |

Les 3 debordements sont ceux de Courier New, police a chasse fixe : *"Ce n'est pas ça."* depasse le bandeau de quelques pixels, le texte sort du fond colore au lieu de se couper. Aucun autre cas
ne deborde.

**Un `<span>` en `display: inline-block` sans `nowrap` ne suffit pas** : mesure, il se coupe a l'interieur de lui-meme sur Verdana et Courier New (*"Ce n'est pas / ça. / Essaie encore !"*).

## 3. Impact sur les autres jeux

Le catalogue sert 148 jeux : 143 bandit manchot, 3 designation, 2 saisie. La chaine est commune.

Mesure sur un jeu servi (`bandit-manchot-additions-cp`), a l'echec, forme A contre formes B et C, aux largeurs ou le verdict passe sur deux lignes :

| Largeur | Police | Forme A (aujourd'hui) | Formes B et C |
|---|---|---|---|
| 320 | poste | *"Ce n'est pas ça. Essaie / encore !"* | *"Ce n'est pas ça. / Essaie encore !"* |
| 320 | Verdana | *"Ce n'est pas ça. / Essaie encore !"* | idem |
| 360 | poste et Verdana | *"Ce n'est pas ça. Essaie / encore !"* | *"Ce n'est pas ça. / Essaie encore !"* |
| 390 | poste | *"Ce n'est pas ça. Essaie encore / !"* | *"Ce n'est pas ça. / Essaie encore !"* |
| 390 | Verdana | *"Ce n'est pas ça. Essaie / encore !"* | *"Ce n'est pas ça. / Essaie encore !"* |
| 576 | poste et Verdana | une ligne | une ligne |

**Sur les 6 cas de 320 a 390 px, 5 coupent aujourd'hui a l'interieur d'une phrase, et un detache le `!` de son mot** (390, police du poste). A 576 et plus, rien ne change. Dans les deux formes
non limitees a `.ecran-deux-oeuvres`, **un jeu servi change d'aspect a 390 px et moins, en mieux** : c'est a `nadia-prof` de le dire, elle a le veto.

## 4. Ce que chaque forme demande

| Point | Forme B : balisage | Forme C : espaces insecables |
|---|---|---|
| Fichiers | `PartieController` (le modele porte les phrases), `manche.html`, et `designation.html` et `saisie.html` pour les deux autres mecaniques, plus une regle de `recreo.css` | `PartieController` seul : les constantes `MESSAGE_ECHEC` (et, pour la meme garantie, `MESSAGE_BRAVO` et `MESSAGE_REVELATION`) |
| Independante de la police | oui, par construction | oui, par construction |
| Autres jeux | la regle peut etre limitee a `.ecran-deux-oeuvres` : aucun autre jeu ne change a l'oeil, mais les coupures fautives de la section 3 restent | change tous les jeux, y compris designation et saisie |
| Regression silencieuse | faible : la structure porte la coupure | **reelle** : une espace ordinaire retapee dans la constante annule la garantie sans bruit |
| Pour la garder | un test de gabarit | un test sur les constantes : aucune espace ordinaire dans une phrase du verdict, une seule entre les phrases |
| Tests connus a changer | ceux qui lisent le balisage du verdict | `PartieControllerTest` ligne 309 (la chaine litterale) |
| Decision d'architecture | oui : le modele porte une liste (meme motif qu'`ADR-0062` : le gabarit rend ce que le controleur decoupe) | oui, plus petite : etendre `ADR-0067` (espaces insecables avant les signes doubles) a la colle des mots d'une phrase |

**Recommandation : C, avec le test.** Un fichier de code, aucun gabarit, aucune feuille, et la propriete (jamais de coupure dans une phrase du verdict) est portee par la chaine elle-meme,
donc par les trois mecaniques. B est la forme si `theo-archi` refuse de loger une convention de mise en page dans une constante de texte.

Je n'ecris ni l'une ni l'autre : le controleur et son contrat ne sont pas a moi (`sofia-sm`, `lea-dev`, `theo-archi`).

## 5. Ce que je n'ai pas pu mesurer

1. **Aucune police reelle d'appareil de CP** : les polices sont imposees par le style de `<body>`, sur un poste, avec Chrome sans tete.
2. **Le garde-fou de l'accent et les tests qui decoupent un texte sur les blancs** ne sont pas rejoues avec des espaces insecables dans `MESSAGE_ECHEC` : un decoupage sur `\s` ne reconnait pas
   l'espace insecable. A verifier par `theo-archi` et `yanis-qa` avant la forme C.
3. **Les formes B et C sont simulees dans le navigateur** (le DOM du verdict est reecrit apres l'echange), pas servies par un jar qui les porte.
4. **Seul le verdict de l'echec** est mesure. La phrase de la revelation (*"C'est cette œuvre qui va avec. Appuie dessus."*) se coupe a 744 dans sa premiere phrase (*"C'est cette œuvre qui va / avec."*) :
   meme technique possible, a la demande de `nadia-prof`.
5. **Le lecteur d'ecran** : une espace insecable est lue comme une espace par les lecteurs d'ecran courants, non verifie ici.
6. **Les jeux de designation et de saisie** ne sont pas rendus.

## 6. Questions

1. `nadia-prof` : la coupure entre les deux phrases est-elle voulue pour tous les jeux a 390 px et moins (section 3), ou seulement pour `ART-CP-10` ?
2. `nadia-prof` : veux-tu la meme garantie pour la phrase de la revelation (*"C'est cette œuvre qui va avec. Appuie dessus."*) ?
3. `theo-archi` : forme C (extension d'`ADR-0067`) ou forme B (modele a liste, comme `ADR-0062`) ? Et le garde-fou de l'accent face a l'espace insecable (section 5, point 2).
4. `sofia-sm` : a router. Le poste 8 ne peut pas fermer la condition 5 de `nadia-prof` par une regle de feuille.

## 7. Jobs de `ci.yml` lances pour ce commit

Lances avant le commit : `chemins`, `prefixes` (sans le controle du proprietaire du garde-fou de l'accent), `outils`, et deux mesures partielles du job `build` avec `-am` (perimetre `bootstrap` et
ses dependances). Aucun de ces resultats n'est celui de la CI distante.
