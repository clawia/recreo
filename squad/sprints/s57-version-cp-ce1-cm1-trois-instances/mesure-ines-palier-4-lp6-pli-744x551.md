# s57 - palier 4 : releve d'ecran de LP6 `ce1-emc-interet-commun` au pli 744 x 551

- **Auteure** : `ines-design`. **Date** : 2026-10-23, 09:12 (UTC+02:00), lue par `date`.
- **Ce que ce fichier est** : des cotes en pixels sur le rendu reel de LP6. Il ne modifie aucun gabarit ni aucune feuille, ne prononce aucun Done, ne dit pas si une reserve est recevable : le veto pedagogique est a `nadia-prof`.
- **Methode et seuils** : ceux de `mesure-ines-palier-4-pli-744x551.md` (LP1, LP2, LP4), meme pilote. Critere du palier 3 : bas de `.indice` au plus a 551.

## Resume

| Code | Constat a 744 x 551 | Section |
|---|---|---|
| ML6-1 | **0 element sous le pli**, sur les 24 items, dans les six ordres des propositions et dans les trois jeux de propositions. Apres une erreur : bas de `.indice` **510 au plus** (41 px de marge), bas du signal (verdict et indice) **536 au plus** (15 px de marge) | 2 |
| ML6-2 | Apres une bonne reponse : bas du signal de la manche suivante **524 au plus** (27 px de marge), 0 sous le pli. Boutons de reponse : bas 436 au plus | 2 |
| ML6-3 | Pire cas : le jeu "quelques eleves" (items 1, 6, 7, 12, 13, 18, 19, 24) tient sur **3 rangees dans les 48 ordres sur 48** ; c'est lui qui donne 510 et 536. Les jeux "deux" et "trois eleves" : 2 ou 3 rangees selon l'ordre | 3 |
| ML6-4 | Aucun debordement horizontal de 360 a 1200 de large. Chemin de sortie : bas a 58 dans tous les etats a 720 et plus | 4 |
| ML6-5 | Contrastes et tailles de police identiques au gabarit de choix de LP4, aucun ecart. Plus petit bouton dans la zone de jeu : 58 px de haut. Espaces insecables : 0 espace ordinaire devant `? ! : ; »` ni apres `«`, au rendu et dans la source | 5, 6 |
| ML6-6 | La tuile du jeu "Pour un seul ou pour tous" est la derniere de la page "Vivre ensemble" (2 tuiles) : haut du bouton **au-dessus du pli** (342 x 56) | 7 |

**Nombre d'elements sous le pli : 0.**

## 1. Arbre, jar, methode

- **Commit mesure** : `ebbb2136a72ea8429f95c212c3e3f0d4441fca42` (branche `s57-sofia-emc-ce1-04`, reprise de LP6), worktree detache a ce commit, hors du depot partage.
- **Jar** : `mvn -B -pl bootstrap -am package -DskipTests` (JDK 21.0.10, via l'enveloppe `outils/mvn-jdk21.sh`), BUILD SUCCESS. Mesure partielle : pas de `verify`, aucune porte technique annoncee (le jeton RF1 appartient a `yanis-qa`). SHA-256 du jar : `e1699c0041c6dee9bd81ed1784b6fa49d0792f501d19c8adecb20ced7f850a08`.
- **Correspondance jar et arbre** : le paquet `ce1-emc-interet-commun.yaml`, `catalogue.yaml` et `recreo.css` lus dans le jar sont egaux, octet pour octet (SHA-256), a ceux du worktree. `recreo.css` : `1fa775cadc55f9b7a2252312340aae077c36218b019cdbf9ad2ebc11dc3654b5`, le meme que dans les releves de LP1, LP2 et LP4. `git diff --stat eb267ba7 ebbb2136 -- adapters/adapter-web/src/main` : 0 ligne. Meme feuille, memes gabarits : seul le contenu differe.
- **Lancement** : un jar sur le port 18560 (PID 34464 lu dans son journal), un Chrome 155 sans tete par le protocole devtools sur le port 19260 (PID 41796), profil jetable, Node 24. Les deux arretes par PID (`taskkill /PID`), le port ne repond plus ensuite. Aucun arret par nom d'image.
- **Pli** : 744 x 551 (`mobile: false`, facteur 1). Cote = `getBoundingClientRect().bottom + scrollY`.
- **Parcours** : 9 parties de 10 manches (6 par la mauvaise reponse, 3 par la bonne), jusqu'a couvrir les 24 items par une premiere erreur. Etats releves sur le meme DOM : arrivee, premiere erreur, seconde erreur, manche apres une bonne reponse, fin de partie, ecran d'erreur. Le jeu n'a pas de revelation : la seconde erreur est suivie du clic sur la bonne reponse.
- **Couverture** : 24 items sur 24 (60 releves a la premiere erreur, 2,5 par item en moyenne), items non retrouves par leur enonce : 0. Les 5 propositions distinctes du paquet sont toutes servies. Les 3 jeux de propositions (une formule pour une seule personne, une pour toute la classe, plus un nombre : "quelques", "deux" ou "trois eleves") sont tous releves, 8 items chacun.
- **Largeurs** : a la premiere erreur, sur le meme DOM, 360, 600, 720, 744, 768, 992, 1200 ; a 720 et 744 avec la barre de defilement classique et avec `scrollbar-width: none`.
- **Six ordres** : a la premiere erreur, les trois boutons de reponse sont permutes dans leurs six ordres sur le meme DOM, a 744 (24 items x 6 = 144 releves). Le serveur tire l'ordre au hasard.

## 2. Resultat au pli (744 x 551)

| Etat | Releves | Element mesure | Bas | Sous le pli |
|---|---|---|---|---|
| Arrivee | 9 | propositions | 330 a 436 | 0 |
| Premiere erreur | 60 (24 items) | `.indice` | **404 a 510** | **0** |
| Premiere erreur | 60 | signal (verdict et indice) | **430 a 536** | **0** |
| Seconde erreur | 60 | `.indice` / signal | 404 a 510 / 430 a 536 | 0 |
| Manche apres une bonne reponse | 81 (24 items) | signal "Bravo !" | 418 a **524** | 0 |
| Six ordres, premiere erreur | 144 | `.indice` / signal | 404 a 510 / 430 a 536 | **0 item sur 24 dans aucun ordre** |

- Boutons de reponse : bas 330 a 436, 58 de haut, dans tous les etats : aucun bouton de reponse ne s'approche du pli (115 px de marge au pire).
- Le verdict ("Ce n'est pas ça. Essaie encore !") finit a 528 au plus, l'indice a 510 au plus.
- Hauteur de la page : 551 a l'arrivee, 556 au plus apres une bonne reponse, **568 au plus apres une erreur**. Ces 17 px de defilement ne sont pas un element : c'est le rembourrage bas du conteneur sous le signal (536 + 32). Une barre de defilement apparait alors et la largeur utile passe de 744 a 729 (decalage de 7 px du contenu). Meme comportement sur les gabarits de LP1 et LP4.
- Enonces : 1 ligne (items 2, 16, 20) ou 2 lignes (les 21 autres), de 45 a 90 caracteres. Le plus long, item 10 (90 caracteres), tient sur 2 lignes. Aucun enonce a 3 lignes. Indice : 12 a 34 caracteres, **1 ligne** pour les 24 items (boite de 50 de haut).
- Ce que le releve ne mesure pas : un enonce de plus de 90 caracteres. Les seuils eventuels d'ecriture du paquet (112 pour l'enonce, 46 pour l'indice, issus de LP4) ne sont ni confirmes ni infirmes par ce releve : aucun item n'en approche.
- Reference de comparaison : `mesure-ines-palier-4-pli-744x551.md` donne pour `EMC-CE1-01` un bas d'indice de 404 a 540 et pour LP4 apres reprise un maximum de 484. Ces chiffres sont repris de ce fichier, non remesures ici (autre session, autre jar). LP6 : 510.

## 3. Les trois jeux de propositions

| Jeu | Items | Rangees | Bas `.indice` | Bas du signal | Ordres a 3 rangees |
|---|---|---|---|---|---|
| "pour quelques eleves" | 1, 6, 7, 12, 13, 18, 19, 24 | **3** | 510 | 536 | **48 sur 48** |
| "pour deux eleves" | 2, 3, 8, 9, 14, 15, 20, 21 | 2 ou 3 | 404 a 510 | 430 a 536 | 13 sur 48 |
| "pour trois eleves" | 4, 5, 10, 11, 16, 17, 22, 23 | 2 ou 3 | 404 a 510 | 430 a 536 | 16 sur 48 |

- Propositions : 5 textes distincts, 16 a 23 caracteres, 3 a 4 mots, **58 px de haut**, police 32 px.
  - "pour une seule personne" (23 caracteres), "pour quelques élèves" et "pour toute la classe" (20) : largeur **680**, une rangee entiere chacune.
  - "pour deux élèves" (16 caracteres) et "pour trois élèves" (17) : largeur 314 et 310 quand ils partagent une rangee, 680 quand ils sont seuls.
- La rangee depend de l'ordre servi : avec "quelques", les trois boutons ont toujours une rangee chacun. Avec "deux" ou "trois", le bouton court peut partager une rangee avec un voisin. Le pas d'une rangee est 66 px (58 de bouton, 8 d'ecart), comme dans LP4.
- Le pire cas mesure est donc stable : 3 rangees, 510 et 536. Une rangee de plus ne serait pas a prevoir (trois propositions au plus).

## 4. Largeurs et debordement horizontal (premiere erreur, 24 items)

| Largeur, mode | Items dont le bas de `.indice` depasse 551 | Bas de `.indice` | Debordement horizontal |
|---|---|---|---|
| 360, barre | 24 | 958 a 1102 | 0 |
| 600, barre | 24 | 756 a 804 | 0 |
| 720, barre | 0 | 404 a 510 | 0 |
| 720, sans barre | 0 | 404 a 510 | 0 |
| 744, barre | 0 | 404 a 510 | 0 |
| 744, sans barre | 0 | 404 a 510 | 0 |
| 768, barre | 0 | 404 a 510 | 0 |
| 992 | 0 | 404 a 444 | 0 |
| 1200 | 0 | 338 a 444 | 0 |

- A 360 et 600, tous les items passent sous le pli. Les gabarits de reference aussi (`mesure-ines-palier-4-pli-744x551.md`, section 6) : ce ne sont pas des largeurs du perimetre de mesure, je les donne pour la limite basse. A 360 l'en-tete passe sur deux rangees (bas de la sortie a 124 au lieu de 58) ; a 600 aussi.
- `scrollWidth` egal a `clientWidth` dans tous les etats, aux sept largeurs, deux modes : aucun element ne depasse horizontalement la fenetre.
- Chemin de sortie (`a.lien-accueil`) : 109 x 50, bas a 58 de 720 a 1200.

## 5. Tailles et contraste

Identiques au gabarit de choix de LP4 (section 2 de `mesure-ines-palier-4-pli-744x551.md`) :

| Element | Police | Dimensions | Contraste |
|---|---|---|---|
| Enonce | 32 px | 1 a 2 lignes, interligne 40 | 14,09 |
| Consigne | 20 px | - | 14,09 |
| Proposition | 32 px | 58 de haut, 310 a 680 de large | 9,75 a 10,03 |
| Verdict faux / juste | 20 px | icone 48 x 48 | 13,24 / 13,46 |
| Indice | 20 px | boite de 50 de haut | 13,93 |
| Sortie / niveau | 20 px | 109 x 50 / 66 x 50 | - |

- Tous les contrastes depassent 4,5. Ce que l'enfant lit en premier (enonce 32, propositions 32) est plus grand que les boutons secondaires (20) : aucun renversement.
- Signal juste/faux : icone 48 x 48 (coche ou croix) et texte present dans tous les etats ; la couleur ne porte pas seule le sens. La proposition essayee garde un trait, pas une couleur. Apres une erreur, le focus est sur la proposition essayee.
- Fin de partie : un seul bouton "Les jeux" (117 x 56, haut a 98, bas a 154) ; libelles de score a 16 px, nombres a 20 px, "C'est fini !" a 24 px ; page de 551 de haut.
- Ecran d'erreur : "Oups ! Le jeu a un souci." et un seul bouton "Recommencer" (175 x 56, 20 px), sans debordement.
- Limite : le contraste est calcule sur les fonds unis composes ; le fond du cabinet (degrade et etoiles) n'est pas un fond de texte.

## 6. Espaces insecables

- **Rendu** : sur tous les enonces et indices servis, 0 espace ordinaire devant `? ! : ; »` et 0 apres `«`.
- **Source** : lecture statique des 124 valeurs du YAML (enonce, bonne reponse, distracteurs, indice, consigne, titre). Espace ordinaire a corriger : **0**. Espaces insecables ecrits : 48.
- Non mesure : un signe de ponctuation seul en debut de ligne apres un retour a la ligne, ligne par ligne.

## 7. Ecrans hors manche

Page "Vivre ensemble" du niveau CE1 (744 x 551) : la tuile "Pour un seul ou pour tous" mesure 342 x 56, police 20 px, titre sur une ligne ; elle est au-dessus du pli (la page n'a que deux tuiles). Aucun debordement.

## 8. Proposition

Je propose, je ne tranche pas : **rien a corriger au pli pour LP6.** Aucune proposition de gabarit ni de feuille de style.

## 9. Limites

- Un navigateur (Chrome 155 sans tete), une police (celle du poste), pas d'appareil reel, pas de clavier virtuel.
- Une partie par item en moyenne 2,5 fois ; les cotes d'un item sont les memes dans tous ses releves, hors ordre des propositions (releve dans les six ordres pour les 24 items).
- Pas mesure : les neuf polices imposees, le focus clavier, le toucher, le zoom du navigateur, un enonce rendu avec une police de remplacement.
- La lisibilite des scenes, la portee et la lecture defendable de chaque situation ne sont pas de mon ressort : `nadia-prof`.

## Decisions prises

**Aucune.** Ce fichier ne cree ni n'amende aucune `ADR` ni `DEC`, ne leve aucune reserve et ne prononce aucun Done.
