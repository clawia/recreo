# s57 - palier 4 : releve d'ecran de LP6 `ce1-emc-interet-commun` au pli 744 x 551, troisieme passe (c975a7b8)

- **Auteure** : `ines-design`. **Date** : 2026-10-23, 10:27 (UTC+02:00), lue par `date`.
- **Ce que ce fichier est** : des cotes en pixels sur le rendu reel de LP6 apres la troisieme passe de `sofia-sm` (10 items changes, dont l'item 7 a 79 caracteres avec la question). Il ne modifie aucun gabarit ni aucune feuille, ne prononce aucun Done, ne dit pas si une reserve est recevable : le veto pedagogique est a `nadia-prof`.
- **Ce fichier complete, sans le remplacer,** `mesure-ines-palier-4-lp6-pli-744x551.md` (mesure a `ebbb2136`). Methode et seuils : les siens. Critere du palier 3 : bas de `.indice` au plus a 551.

## Resume

| Code | Constat a 744 x 551 | Section |
|---|---|---|
| ML6c-1 | **0 element sous le pli**, sur les 24 items, dans les six ordres des propositions et dans les trois jeux de propositions. Apres une erreur : bas de `.indice` **510 au plus** (41 px de marge), bas du signal (verdict et indice) **536 au plus** (15 px de marge) | 2 |
| ML6c-2 | Item 7 (79 caracteres avec la question) : enonce sur **2 lignes**, bas a 206, comme les 20 autres enonces a 2 lignes. Aucun enonce a 3 lignes | 2 |
| ML6c-3 | Ecart avec la mesure a `ebbb2136` : **aucun** sur les cotes verticales (510, 536, 524, 436, 568), les tailles et les contrastes | 3 |
| ML6c-4 | Aucun debordement horizontal de 360 a 1200 de large. Chemin de sortie : bas a 58 dans tous les etats a 720 et plus | 4 |
| ML6c-5 | 0 espace ordinaire devant `? ! : ; »` ni apres `«`, au rendu et dans la source | 5 |

**Nombre d'elements sous le pli : 0.**

## 1. Arbre, jar, methode

- **Commit mesure** : `c975a7b8c50f8ca690d72402b6cb2d17d9fcdb09` (sommet de `s57-sofia-emc-ce1-04`), worktree detache a ce commit, hors du depot partage.
- **Jar** : `mvn -B -pl bootstrap -am package -DskipTests` (JDK 21.0.10, via l'enveloppe `outils/mvn-jdk21.sh`), BUILD SUCCESS. Mesure partielle : pas de `verify`, aucune porte technique annoncee (le jeton RF1 appartient a `yanis-qa`). SHA-256 du jar : `9b6601bb6ec7e3e43000139d70bb967c65c01dfc26a34ff425f492ad74e31701`.
- **Correspondance jar et arbre** : `ce1-emc-interet-commun.yaml` (`5efdf02b...`), `catalogue.yaml` (`c68250ba...`) et `recreo.css` (`1fa775ca...`, le meme que dans la mesure a `ebbb2136`) lus dans le jar sont egaux, octet pour octet (SHA-256), a ceux du worktree. `git diff --stat ebbb2136 c975a7b8 -- adapters/adapter-web/src/main` : vide. Meme feuille, memes gabarits : seul le contenu differe.
- **Lancement** : un jar sur le port 18560 (PID 37868 lu dans son journal), un Chrome 155 sans tete par le protocole devtools sur le port 19260 (PID 46824), profil jetable, Node 24. Les deux arretes par PID (`taskkill /PID`) : le port 18560 ne repond plus, le PID 46824 n'existe plus. Aucun arret par nom d'image.
- **Pli** : 744 x 551 (`mobile: false`, facteur 1). Cote = `getBoundingClientRect().bottom + scrollY`.
- **Parcours** : 11 parties de 10 manches (8 par la mauvaise reponse, 3 par la bonne), jusqu'a couvrir les 24 items par une premiere erreur. Etats : arrivee, premiere erreur, seconde erreur (suivie du clic sur la bonne reponse : le jeu n'a pas de revelation), manche apres une bonne reponse, fin de partie, ecran d'erreur.
- **Couverture** : 24 items sur 24 (80 releves a la premiere erreur), items non retrouves par leur enonce : 0. Les 5 propositions distinctes du paquet sont toutes servies.
- **Largeurs** : a la premiere erreur, sur le meme DOM, 360, 600, 720, 744, 768, 992, 1200 ; a 720 et 744 avec la barre de defilement classique et avec `scrollbar-width: none`.
- **Six ordres** : a la premiere erreur, les trois boutons sont permutes dans leurs six ordres sur le meme DOM, a 744 (24 items x 6 = 144 releves).

## 2. Resultat au pli (744 x 551)

| Etat | Releves | Element mesure | Bas | Sous le pli |
|---|---|---|---|---|
| Arrivee | 11 | propositions | 330 a 436 | 0 |
| Premiere erreur | 80 (24 items) | `.indice` | **404 a 510** | **0** |
| Premiere erreur | 80 | signal (verdict et indice) | **430 a 536** | **0** |
| Seconde erreur | 80 | `.indice` / signal | 404 a 510 / 430 a 536 | 0 |
| Manche apres une bonne reponse | 99 (23 items) | signal "Bravo !" | 418 a **524** | 0 |
| Six ordres, premiere erreur | 144 | `.indice` / signal | 404 a 510 / 430 a 536 | **0 item sur 24 dans aucun ordre** |

- **Marge** : 41 px sur le bas de `.indice` (551 - 510), **15 px sur le bas du signal** (551 - 536), 27 px apres une bonne reponse, 115 px sur les boutons de reponse (bas 436 au plus, 58 de haut).
- **Item 7** ("Lina colle son affiche des horaires au mur de la salle. Pour qui est-ce utile ?", 79 caracteres, indice "Qui lit cette affiche ?") : enonce sur 2 lignes, bas a 206, indice sur 1 ligne. Son jeu de propositions ("quelques eleves") a 3 rangees : bas d'indice 510, bas du signal 536, comme les 7 autres items du jeu.
- Enonces : 1 ligne (items 2, 16, 20) ou 2 lignes (les 21 autres), de 45 a 90 caracteres (item 10 le plus long). Indice : 12 a 34 caracteres, 1 ligne pour les 24 items.
- Hauteur de la page : 551 a l'arrivee, 556 au plus apres une bonne reponse, **568 au plus apres une erreur**. Ces 17 px de defilement ne sont pas un element : c'est le rembourrage bas du conteneur sous le signal (536 + 32). La barre de defilement classique reduit alors la largeur utile de 744 a 729.
- Verdict : bas a 528 au plus.

## 3. Les trois jeux de propositions et l'ecart avec `ebbb2136`

| Jeu | Items | Releves (ordres) | A 3 rangees | Bas `.indice` | Bas du signal |
|---|---|---|---|---|---|
| "quelques eleves" | 1, 6, 7, 12, 13, 18, 19, 24 | 48 | **48 sur 48** | 510 | 536 |
| "deux eleves" | 2, 3, 8, 9, 14, 15, 20, 21 | 48 | 19 sur 48 | 404 a 510 | 430 a 536 |
| "trois eleves" | 4, 5, 10, 11, 16, 17, 22, 23 | 48 | 14 sur 48 | 404 a 510 | 430 a 536 |

- Le pire cas est stable : 3 rangees, 510 et 536. Une rangee de plus ne serait pas a prevoir (trois propositions au plus).
- Les memes jeux, les memes huit items par jeu et les memes bornes que la mesure a `ebbb2136`. Le nombre d'ordres a 3 rangees dans les jeux "deux" et "trois" differe (19 et 14 au lieu de 13 et 16) parce que le serveur tire l'ordre au hasard a chaque partie : les bornes (404 a 510, 430 a 536), elles, sont identiques.
- Propositions : 5 textes distincts, 16 a 23 caracteres, 58 px de haut, police 32 px, largeur 310 a 680.
- Aucun ecart, a l'unite pres, avec la mesure a `ebbb2136` sur : bas d'indice (510), bas du signal (536), bas apres une bonne reponse (524), bas des boutons (436), hauteurs de page (551, 556, 568), bas de la sortie (58), tailles de police, contrastes (14,09 enonce ; 9,75 a 10,03 propositions ; 13,24 verdict faux ; 13,46 verdict juste ; 13,93 indice).

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

- A 360 et 600, tous les items passent sous le pli, comme dans la mesure a `ebbb2136` et dans les gabarits de reference : ce ne sont pas des largeurs du perimetre de mesure, donnees pour la limite basse.
- `scrollWidth` egal a `clientWidth` dans tous les etats, aux sept largeurs, deux modes : aucun element ne depasse horizontalement la fenetre.
- Chemin de sortie (`a.lien-accueil`) : 109 x 50, bas a 58 de 720 a 1200.

## 5. Tailles, signal, espaces insecables, ecrans hors manche

- Enonce 32 px, propositions 32 px, verdict et indice 20 px, sortie 20 px : ce que l'enfant lit en premier reste plus grand que les boutons secondaires. Plus petit bouton de la zone de jeu : 58 px de haut. Signal : icone 48 x 48 (coche ou croix) et texte dans tous les etats. Apres une erreur, le focus est sur la proposition essayee.
- **Rendu** : 0 espace ordinaire devant `? ! : ; »` ni apres `«` sur tous les enonces et indices servis.
- **Source** : lecture statique des 129 chaines entre guillemets du YAML. Espace ordinaire a corriger : **0**. Espaces insecables ecrits : 48.
- Fin de partie : un seul bouton "Les jeux" (117 x 56, bas a 154), page de 551 de haut. Ecran d'erreur : "Oups ! Le jeu a un souci." et un seul bouton "Recommencer" (175 x 56), sans debordement. Tuile "Pour un seul ou pour tous" sur la page "Vivre ensemble" : 342 x 56, au-dessus du pli.

## 6. Proposition

Je propose, je ne tranche pas : **rien a corriger au pli pour LP6 a `c975a7b8`.** Aucune proposition de gabarit ni de feuille de style.

## 7. Limites

- Un navigateur (Chrome 155 sans tete), une police (celle du poste), pas d'appareil reel, pas de clavier virtuel.
- Pas mesure : les neuf polices imposees, le focus clavier, le toucher, le zoom, un enonce rendu avec une police de remplacement, un enonce de plus de 90 caracteres.
- Un signe de ponctuation seul en debut de ligne apres un retour a la ligne n'est pas verifie ligne par ligne.
- La lisibilite des scenes, la portee et la lecture defendable de chaque situation ne sont pas de mon ressort : `nadia-prof`.

## Decisions prises

**Aucune.** Ce fichier ne cree ni n'amende aucune `ADR` ni `DEC`, ne leve aucune reserve et ne prononce aucun Done.
