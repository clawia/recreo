# s57, palier 2, instance B - rejeu de `nadia-prof` : p2-12, `RDO-CP-R3` (`ART-CP-10`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-16, 19:52 (UTC+02:00), lu par `date`.
- **Arbre** : branche `s57-nadia-p2-b3`, worktree dedie, partie de `round-two` a `3eef868d`. Code de la fusion `19c8a7b2` :
  `git diff --quiet 19c8a7b2 3eef868d -- adapters domain application bootstrap pom.xml` sort 0 ; `23222465` (`lea-dev`) est
  ancetre de `3eef868d`.
- **Jeu** : `bandit-manchot-rapprocher-deux-oeuvres-cp`.
- **Audit joue** : jar de `3eef868d` construit et joue au navigateur ; captures regardees.

## Verdict

**`RDO-CP-R3` levee.** Plus aucune reserve ouverte sur ce jeu : statut **VALIDE**.

**Motif** : la levee ecrite le 2026-10-15 (fiche, section 6), inchangee, est tenue. A 720, 744 et 768 x 551, a la revelation,
sur les six items, avec et sans barre, aucun mot de *"Voici la bonne œuvre. Appuie dessus."* ne passe le bord droit de sa boite
(38 px de marge a 720 avec barre, 53 px ailleurs), et `scrollWidth` egale `clientWidth` dans les 36 revelations. Deux lignes,
coupees entre les deux phrases, partout ; bas de la phrase a 528 a 744.

Detail, cotes par largeur et par mode, et ce que l'enfant lit : `squad/conformite/FICHE-bandit-manchot-rapprocher-deux-oeuvres-cp.md`,
rejeu du 2026-10-16.

## Ce que j'ai lance

- `bash outils/mvn-jdk21.sh -B clean package -DskipTests` : `BUILD SUCCESS` ; jar sur le port 18513 (libre avant), Java 21.0.10,
  PID 53504, arrete par ce PID.
- `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl`, meme arbre : `BUILD SUCCESS`, 2669 tests, 0 echec (job `build`).
  `chemins`, `prefixes`, `outils` : non lances sur cet arbre.
- Deux Chrome sans tete, port de debogage 19213, un avec barre classique et un sans barre peinte. Chacun s'est ferme par
  `Browser.close` ; plus aucun processus a son PID ensuite.
- 13 parties ; les six items reveles au troisieme essai faux a chaque largeur et dans chaque mode.

## Constats sur les autres etats

| Etat | Constat |
|---|---|
| Phrase et verdict | la boite rose *"Ce n'est pas ça."* finit 12 px avant la phrase, qui est sur le fond clair, sans cadre : elle ne se lit pas comme la suite du verdict |
| Adulte (`DEC-0046`) | aucun bloc *"Adulte, à voix haute :"* sur ce jeu ; tout ce qui est a l'ecran est ecrit pour l'enfant |
| Echec | verdict sur deux lignes, coupe entre les phrases ; indices servis egaux au paquet ; pas de defilement horizontal |
| Pli de `RDO-CP-R1` | bas de `.indice` apres une erreur a 548 a 744 (marge de 3 px, inchangee depuis le 2026-10-15) ; 543 a 720 avec barre |
| Ecran de fin | aucune oeuvre, aucun texte qui en designe une |

## Ce que je n'ai pas joue

992 et 1200 de large ; une autre police ; une tablette ; le clavier ; l'orthotypographie (au `relecteur-pedagogique`).

## Ce dont je reste la seule garde

Le debordement de la phrase de revelation et le defilement horizontal : aucun automate ne compare un texte a sa boite. Une
phrase plus longue demain sortirait de nouveau sans qu'un test rougisse. Que la phrase ne se lise pas comme la suite du
verdict : aucune cote ne le garde.

**Tarif** : rejeu 0,5 (p2-12, instance B). Aucun identifiant neuf ni renomme.

**Signe** : `nadia-prof`, s57, palier 2, instance B, 2026-10-16.
