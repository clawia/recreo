# Releve Q7 - le gabarit livre du paquet `cp-rapprocher-deux-oeuvres`, mesure sur le jar (s56)

- Auteure : `ines-design`.
- Date : 2026-10-11.
- Branche : `s56-ines-q7`, issue de `round-two` a `a6ca1751`.
- Demande : `cloture-nadia.md` de s55, section 4.4, `Q7` ; `rapport-lea-dev.md` de s56, section 11.3 (la phrase `M4` a y = 642 a l'oeil sur captures).
- Fichiers : ce document et `pilote-q7.mjs` (le pilote de mesure) dans ce dossier.

## 0. Ce que ce document est, et ce qu'il n'est pas

Un releve. **Aucun gabarit, aucune feuille de style, aucun controleur n'est modifie.** Il ne prononce aucun Done pedagogique. Les chiffres disent ou sont les elements ; ce qu'il faut en conclure pour `Q3` est a `nadia-prof`.

Difference avec `proposition-ines-forme-art-cp-10.md` : ici l'application est servie par le jar, le paquet est au catalogue, et chaque cote est un `getBoundingClientRect()` lu sur la page rendue, pas sur un vehicule statique.

## 1. Methode

- Jar construit depuis la racine du worktree (`bash outils/mvn-jdk21.sh -B clean verify`, sans `-pl`), lance avec `java -jar bootstrap/target/recreo.jar --server.port=18080` sur un JDK 21, arrete par son PID apres verification de sa ligne de commande.
- Chrome headless, profil isole jetable, pilote par le protocole de debogage (`pilote-q7.mjs`). La fenetre est fixee par `Emulation.setDeviceMetricsOverride` a **744, 390 et 992 de large sur 551 de haut** (`mobile: false`, echelle 1) : ce n'est pas la fenetre de Chrome headless et le plancher de 516 px d'`ADR-0025` ne joue pas.
- Parcours : accueil, clic sur "Jouer" de l'entree du paquet, puis clics sur les cases. Etats mesures : manche fraiche, apres un echec, apres deux echecs, revelation (deux echecs puis un troisieme appui sur une case deja essayee), bonne reponse (au premier appui, ou apres un echec).
- **Les six items du paquet** sont rendus a chaque largeur (reconnus par l'image de reference). 56, 34 et 40 parties ont ete jouees (744, 390, 992) pour atteindre la revelation sur chacun.
- Cotes en px, origine en haut de la page, `top`/`bottom` en coordonnees de page. **Pli = 551** (`DEC-0040`, meme reference que s42 et que la proposition). Une cote dont le `top` est >= 551 est entierement sous le pli.
- Les croix sur les cases ne sont pas des elements : leur boite est calculee depuis la case et le style calcule du `::before` (`top`, `left`, `width`, `height`, `margin-left`).

## 2. Resultat principal

**A la revelation, la phrase `M4` ("C'est cette oeuvre qui va avec. Appuie dessus.") est entierement sous le pli de 551 px, aux trois largeurs.** Les six items rendent les memes cotes.

| Largeur | `M4` haut - bas | Ecart du haut au pli | Ecart du bas au pli |
|---|---|---|---|
| 744 | 626 - 656 | 75 | 105 |
| 992 | 670 - 700 | 119 | 149 |
| 390 | 901,7 - 961,7 | 350,7 | 410,7 |

A 744, la mesure rejoint celle de `lea-dev` (y = 642 a l'oeil : c'est le milieu de la boite, 626 + 15 = 641). Le chiffre "91 px sous le pli" est l'ecart du milieu ; le haut de la phrase est a 75 px, le bas a 105 px.

## 3. A 744 x 551

Memes cotes pour les six items, sauf la hauteur de la boite de reference (118,5 a 176 selon l'image, sans effet sur ce qui suit : les cases ne bougent pas).

| Element | Fraiche | Echec (1 ou 2) | Revelation | Bonne reponse |
|---|---|---|---|---|
| Reference (`.illustration`) | 194 - (312,5 a 370) | idem | idem | idem |
| Meuble (`.cabinet`) | 286 - 518 | 286 - **558** | 286 - **558** | 286 - 518 |
| Cases (144 x 144) | 338 - 482 | 338 - 482 | 338 - 482 | 338 - 482 |
| Croix sur les cases (40 x 40) | - | 484 - 524 | 484 - 524 (deux) | - |
| Icone du signal (croix ou coche, 48 px) | - | **566 - 614** | **566 - 614** | **526 - 574** |
| Verdict | - | **567 - 597** | **567 - 597** | 527 - 557 |
| `M4` | - | - | **626 - 656** | - |
| Explication | - | - | **660 - 710** | - |
| Indice | - | **626 - 676** | **714 - 764** | - |
| Hauteur du document | 551 | 708 | 796 | 614 |

Lecture contre le pli de 551 :

- Au-dessus : la reference, les trois cases, les deux croix sur les cases (bas a 524, marge de 27). A la revelation, la case revelee (anneau) est dans les cases, donc au-dessus.
- A cheval : le socle du meuble (bas a 558, depasse de 7 px) ; a la bonne reponse, l'icone (526 - 574) et le verdict (527 - 557) sont coupes par le pli.
- Au-dessous : a l'echec, le verdict, l'icone du signal et l'indice (le verdict commence a 567, soit 16 px sous le pli). A la revelation, `M4`, l'explication et l'indice.
- Le document defile de 157 px a l'echec et de 245 px a la revelation.

## 4. A 992 x 551

| Element | Fraiche | Echec | Revelation | Bonne reponse |
|---|---|---|---|---|
| Meuble | 286 - 562 | 286 - 602 | 286 - 602 | 286 - 562 |
| Cases (188 x 188) | 338 - 526 | 338 - 526 | 338 - 526 | 338 - 526 |
| Croix sur les cases | - | **528 - 568** | **528 - 568** | - |
| Icone du signal | - | 610 - 658 | 610 - 658 | 570 - 618 |
| Verdict | - | 611 - 641 | 611 - 641 | 571 - 601 |
| `M4` | - | - | **670 - 700** | - |
| Explication | - | - | 704 - 754 | - |
| Indice | - | 670 - 720 | 758 - 808 | - |
| Hauteur du document | 594 | 752 | 840 | 658 |

- Les croix sur les cases commencent a 528 et finissent a 568 : **23 px de leur hauteur sont visibles sur 40, le pli passe a 17 px de leur bas.** La proposition (section 1.5) donnait 481 - 521 sur le vehicule, "oui, marge de 30 px" ; sur le gabarit livre elles sont 47 px plus bas.
- La manche fraiche elle-meme depasse : meuble jusqu'a 562, document de 594.

## 5. A 390 x 551

La reference occupe 274 - 450 ; le meuble commence a 610 ; **les trois cases (95,7 x 95,7) sont a 662 - 757,7, entierement sous le pli des la manche fraiche.**

| Element | Fraiche | Echec | Revelation | Bonne reponse |
|---|---|---|---|---|
| Meuble | 610 - 793,7 | 610 - 833,7 | 610 - 833,7 | 610 - 793,7 |
| Cases | 662 - 757,7 | 662 - 757,7 | 662 - 757,7 | 662 - 757,7 |
| Croix sur les cases (40 x 40) | - | 759,7 - 799,7 | 759,7 - 799,7 | - |
| Icone du signal | - | 855,7 - 903,7 | 841,7 - 889,7 | 801,7 - 849,7 |
| Verdict | - | 841,7 - 901,7 (2 lignes) | 842,7 - 872,7 | 802,7 - 832,7 |
| `M4` | - | - | 901,7 - 961,7 (2 lignes) | - |
| Explication | - | - | 965,7 - 1045,7 | - |
| Indice | - | 929,7 - (979,7 ou 1009,7) | 1049,7 - (1099,7 ou 1129,7) | - |
| Hauteur du document | 826 | 1012 (1042 pour un item) | 1132 (1162 pour un item) | 890 |

Un seul item (celui dont l'indice tient sur trois lignes, 80 px au lieu de 50) differe des cinq autres de 30 px de hauteur de document ; tout le reste est identique d'un item a l'autre.

## 6. Ecarts avec le vehicule de la proposition (a 744 et 992)

Le "bas du contenu" du vehicule n'est pas defini comme celui-ci : je compare le bas du bloc de message (`.message-jeu`) ou, a defaut, du meuble.

| Etat | Vehicule (O1) | Gabarit livre | Ecart |
|---|---|---|---|
| 744, croix sur les cases | 437 - 477 | 484 - 524 | +47 |
| 992, croix sur les cases | 481 - 521 | 528 - 568 | +47 |
| 744, fraiche, bas | 470 | 518 | +48 |
| 744, echec, bas | 628 | 676 | +48 |
| 744, revelation, bas | 662 | 764 | +102 |
| 992, fraiche, bas | 514 | 562 | +48 |
| 992, echec, bas | 672 | 720 | +48 |
| 992, revelation, bas | 706 | 808 | +102 |

Un decalage constant de 47 a 48 px sur tout ce qui est au-dessus des cases et jusqu'a l'echec, et de 102 px a la revelation (la phrase `M4` et l'explication s'ajoutent, et le vehicule ne portait ni l'une ni l'autre a leur hauteur reelle). Je n'ai pas cherche la cause des 47 px (ce qui precede les cases est plus haut sur le gabarit livre) : c'est un fait de mesure.

## 7. Autres faits mesures

- **Les cases se deplacent de 7,5 px en largeur a 744 entre la manche fraiche et le premier echec** (`left` de la premiere case : 244, puis 236,5). Cause : le document passe de 551 (egal a la fenetre, pas de barre de defilement) a 708, et la barre de defilement de 15 px de l'emulation retrecit la page (largeur utile 744, puis 729). A 992 et 390 le document depasse deja la fenetre des la manche fraiche : aucun deplacement. Sur un navigateur dont la barre de defilement se superpose (tablette, telephone), ce deplacement n'existe pas ; je ne l'ai pas verifie.
- Aucun defilement horizontal : la largeur de defilement du document est egale a la largeur utile a chaque largeur et dans chaque etat.
- Les cases, les croix sur les cases, le meuble, le verdict, l'icone et `M4` ont les memes cotes sur les six items ; seules la hauteur de la boite de reference et celle de l'indice (a 390) varient.

## 8. Ce que je ne conclus pas, et ce que je n'ai pas mesure

- Si `M4` sous le pli change la reponse a `Q3`, et ce que vaut "tenu avec O1 et le meuble" de l'arbitrage : c'est `nadia-prof`. Le chiffre est sous le pli, tel quel.
- Je ne propose aucun changement de forme dans ce document.
- **Dix largeurs n'ont pas ete faites** : trois ont ete demandees pour ce releve (744, 390, 992). Les sept autres de la proposition (360, 576, 640, 720, 768, 1200, 1440) ne sont pas mesurees sur le gabarit livre.
- Une seule hauteur de fenetre, 551. Rien n'est mesure a une autre hauteur.
- Aucun jugement a l'oeil : pas de lecture de capture, pas de verification de la lisibilite de la croix de 40 px, ni de l'interaction avec l'anneau de focus.
- La mesure n'a pas ete faite sans le meuble (`Q5`).

## 9. Jobs lances

- Job `build` de `.github/workflows/ci.yml` : `bash outils/mvn-jdk21.sh -B clean verify`, racine du worktree, sans `-pl` : `BUILD SUCCESS`.
- Job `chemins` : `verifier-chemins-test.sh` puis `verifier-chemins.sh` sur `git ls-files` et les deux fichiers neufs : 0.
- Job des prefixes : `verifier-prefixes-test.sh`, puis `verifier-prefixes.sh` sur le message de commit : 0. `commit-atteignable-test.sh` et les juges purs des outils ne sont pas lances.

## 10. Reproduction

`CHROME` designe un Chrome ou Chromium, `<profil>` un repertoire jetable et isole.

```text
bash outils/mvn-jdk21.sh -B clean verify
java -jar bootstrap/target/recreo.jar --server.port=18080
node squad/conformite/releves-s56/pilote-q7.mjs "$CHROME" <profil> http://localhost:18080 sortie.json
```

Le jar et le Chrome s'arretent chacun par leur PID, apres verification de leur ligne de commande.

## Impact

- Cree : ce document et `pilote-q7.mjs`.
- Aucun fichier sous `domain/`, `application/`, `adapters/` ou `bootstrap/` n'est modifie.
