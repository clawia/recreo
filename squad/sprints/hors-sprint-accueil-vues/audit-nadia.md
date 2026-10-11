# Audit pedagogique - accueil par matiere (V1 "Par matière" / V2 "Tout voir", bascule en session)

- **Auteure** : `nadia-prof`. **Date** : 2026-10-11 20:45 (UTC+02:00), lue par `date`.
- **Arbre** : worktree `nadia-accueil-audit`, base `def23ead` (`lea-accueil-p4`), `bash outils/verifier-lignee.sh` sort 0.
- **Verdict** : **VALIDE SOUS RESERVE**, sous la condition avant fusion `APM-12`. Ecrit dans
  `squad/conformite/FICHE-accueil-par-matiere.md`, bloc de validation ; ce rapport en donne le detail point par point.

## 0. Methode

`bash outils/mvn-jdk21.sh -B clean verify` a la racine : BUILD SUCCESS, JDK 21.0.10, 0 echec (job `build` seul). Jar
servi par `java -jar bootstrap/target/recreo.jar --server.port=<port>`, port verifie libre par `netstat`, PID lu dans le
journal de demarrage du jar et confirme par `netstat`, arrete par ce seul PID. Chrome headless a profil jetable, pilote par
le protocole de debogage avec htmx et le JavaScript du produit, fenetre emulee 744 x 551 puis 390 x 551 ; chaque Chrome
arrete par son PID. Clics sur les elements rendus, releve du DOM et capture a chaque etape. Scripts, releves et captures
hors du depot. Deux sessions, trois parties jouees jusqu'a "C'est fini !", en pressant d'abord une mauvaise reponse.

## 1. Le parcours, tel que je l'ai joue

| # | Geste | Ce que l'ecran rend |
|---|---|---|
| 1 | `/` | *"Choisis ton niveau."*, trois puces, aucune puce active, un "Les jeux" |
| 2 | puce CP | V2 : "Pour le CP", "Par matière" (quatre carres), bandeau "Français", 8 cartes entieres sur 58 |
| 3 | defilement au troisieme groupe | bandeau "Maths" colle en haut (0 a 48), "Questionner le monde" entier a l'ecran |
| 4 | "Par matière" | etage 1 : 8 tuiles entieres sur 8, "Tout voir" (trois lignes) |
| 5 | tuile Anglais | etage 2 : bandeau "Anglais", une carte "Le monde anglophone", chevron "Par matière" |
| 6 | tuile Maths, puis "Les petites additions" | la manche, aucun bouton de vue, aucune teinte |
| 7 | adresse de l'etage 2 de Français tapee pendant la partie | *"Si tu pars, ta partie s'arrête."* ; "Continuer la partie" rend la meme question, score 0 |
| 8 | puce CE1 pendant une partie | le meme ecran, les memes mots |
| 9 | partie finie, "Les jeux" | *"Choisis ton niveau."*, adresse `/` ; puce CP : **l'etage 1** (vue retenue), jamais l'etage 2 |
| 10 | "Les jeux" de l'en-tete, puis "Les jeux" de la confirmation | *"Choisis ton niveau."*, adresse restee `/niveaux/CP/matieres/maths` ; rechargement : l'etage 2 de Maths (`APM-R1`) |
| 11 | CE1, CM1 | CE1 : 2 bandeaux ou 2 tuiles ; CM1 : 8 bandeaux ou 8 tuiles entieres ; etage 2 d'Histoire des arts a une carte |
| 12 | 390 x 551 | V2 CP : 3 cartes entieres ; etage 1 : 4 tuiles entieres sur 8 ; etage 2 de Maths : 3 cartes entieres |

## 2. Les points, un par un

### P1 - Pictogrammes et texte a cote (`APM-3`)

**Constat.** Chaque tuile et chaque bandeau portent le mot en texte a cote de l'image ; aucune carte de jeu ni puce ne
porte d'image. A 744, sur les tuiles et sur les bandeaux : le livre, l'ardoise `+ =`, la loupe sur une feuille, les deux
personnes, la bulle "Hi", la palette, les deux croches, le ballon, la fiole, le globe, le tableau encadre se reconnaissent
chacun. Le `+ =` se lit "calcul" sans effort ; il ne se confond avec aucun autre.
**Motif.** Le pictogramme retire un effort de lecture, le mot porte la matiere : c'est mon critere du 2026-08-17. "Questionner
le monde" reste difficile a dechiffrer en CP ; la loupe et l'adulte qui accompagne portent la reconnaissance, comme prevu en
N1.
**Avant fusion** : rien.

### P2 - "Par matière" / "Tout voir" contre "Les jeux"

**Constat.** "Les jeux" vit dans la barre sombre, a droite, sans icone. Le bouton de vue vit dans la page claire, sous la
barre, avec une icone qui dessine la destination. Sur l'etage 2, deux gestes de retour coexistent : le chevron "Par
matière" (un pas en arriere, vers les tuiles) et "Les jeux" (le choix de la classe).
**Motif.** Ni le mot, ni la place, ni la forme ne se ressemblent. Une erreur entre les deux coute un appui et ne perd aucune
partie : "Les jeux" sur l'etage 2 ramene au choix de la classe, la puce redonne l'etage 1. La faiblesse que j'ai nommee en
N3 ("Par matière" sur une V2 deja rangee par matiere) se voit sur le jar et reste acceptee pour les deux raisons ecrites.
**Avant fusion** : rien.

### P3 - Le magenta d'Histoire des arts (`APM-6`)

**Constat.** Tuile et bandeau d'Histoire des arts en CM1, a cote du rose de "Vivre ensemble" : distincts, et la tuile se lit
comme une couleur parmi d'autres, pas comme "ne marche pas". Aucune teinte grise sur aucun ecran.
**Motif.** Le motif de mon refus du gris est leve sur le jar comme il l'etait sur la maquette.
**Avant fusion** : rien. L'observation de mon rejugement sur le contour de Français (`#2f6c9f`) voisin de l'accent du bouton
(`#2e6f9e`) est regardee en jouant, cote a cote sur l'etage 1 : la tuile a un fond, une image et un mot, le bouton n'en a
pas ; rien ne les confond. Je la ferme.

### P4 - *"Choisis ton niveau."* (R4 de `lea-dev`)

**Constat.** Seule phrase du premier ecran et de l'ecran de retour apres chaque partie. Texte de `lea-dev`, jamais arbitre.
**Motif.** "Niveau" dit a un enfant de six ans le facile et le difficile d'un jeu : la phrase l'invite a prendre "CM1" parce
que c'est le niveau le plus fort. Ce qu'il sait de CP, CE1, CM1, c'est que ce sont des classes : "je suis en CP". "Niveau"
porte la graphie `eau`, complexe en debut de CP ; "classe" se dechiffre. La forme imperative est celle des consignes du
produit.
**Avant fusion : `APM-12`.** La phrase devient **"Choisis ta classe."**, mot pour mot ; l'inventaire de
`TexteAccentueDesGabaritsTest` la porte comme arbitree. Verifiable sans moi : texte rendu par `GET /` et `POST /retour`.

### P5 - Le premier ecran presque vide

**Constat.** Une phrase sous la barre, rien d'autre ; les trois puces sont au-dessus, dans la barre.
**Motif.** Un ecran qui ne demande qu'une chose, sans rien a cote, n'est pas un defaut pour un enfant qui lit peu. Il n'y a
pas de jeu d'une autre classe a l'ecran, ce qui etait mon motif en `Q-U1`. La place de la phrase par rapport aux puces est
une question de forme, a `ines-design` si elle le juge utile ; ce n'est pas une reserve de ma porte.
**Avant fusion** : rien d'autre qu'`APM-12`.

### P6 - L'etage 2 a une carte (`APM-10`)

**Constat.** CP Anglais ("Le monde anglophone") et CM1 Histoire des arts ("L'art dans son contexte") : un bandeau, une carte,
le chevron. Le cas que j'avais signale (CM1, bandeau "Anglais" et carte "Anglais") se voit en V2 et ne gene pas.
**Motif.** L'ecran ne parait pas casse, et l'enfant lit le nom du jeu avant de le lancer.
**Avant fusion** : rien.

### P7 - Lecture de R2 et de R7 (`APM-9`, critere "Tenu en session")

**R2 : je la confirme.** C'est ma reponse a `Q-N2` : depuis `Q-U1`, le premier ecran est le choix de la classe, et la classe
choisie s'ouvre dans la vue retenue. Joue : partie lancee depuis l'etage 2 de Maths, "Les jeux", puce CP, **etage 1**.
**R7 : je la lis ainsi, et je ne la reecris pas.** Le critere "quand elle appuie sur Les jeux depuis un jeu, alors elle revient
en V1" se tient en deux gestes : "Les jeux" mene au choix de la classe, la classe choisie revient en V1. C'est a `camille-po`
de reecrire son critere dans ce sens ; ma porte ne depend pas de sa formulation.
**`APM-R1`, reserve neuve et datee, non bloquante.** Ligne 10 du parcours : apres "Les jeux" sur la confirmation, l'adresse
reste celle de la page quittee, et un rechargement rend l'etage 2 de la matiere jouee, ce qu'`APM-9` refuse au bouton.
Ce n'est pas un echec d'`APM-9`, qui porte sur l'ecran ou mene "Les jeux", et cet ecran est le bon : je ne deplace pas cette
condition. Non bloquante parce qu'il faut un rechargement et que l'ecran atteint dit sa classe, sa matiere et son retour.
Attendu : apres "Les jeux" de la confirmation, l'adresse est `/`, comme apres "Les jeux" de la fin de partie. Cause technique
(R6 de `lea-dev`, deja presente avant ce chantier sous la forme `/niveaux/CP`), moyen a `theo-archi`.

### P8 - 8 cartes contre le seuil de 10 (R5)

**Constat.** 8 cartes entieres en V2 a 744 x 551 en CP, CE1 et CM1 ; a l'etage 1, toutes les tuiles (8, 2, 8) ; a l'etage 2
de Maths CP, 8 cartes entieres sur 13 (`min(n, 6)` tenu). A 390 : 3 cartes en V2, 4 tuiles sur 8, 3 cartes a l'etage 2.
**Motif.** **8 me suffit pedagogiquement.** Ce que l'enfant doit trouver au premier ecran, c'est le nom de la matiere et de
quoi commencer : il les a. Un enfant de CP ne lit pas dix titres avant d'en choisir un ; huit, c'est deja plus qu'il n'en
lira. Je n'ai pas de motif pedagogique pour 10 plutot que 8. Le seuil reste un critere de produit de `camille-po` : c'est a
elle de le changer par une `DEC`, pas a moi de le baisser. Le pli de l'accueil n'est pas un motif de `REFUSE` (mon arbitrage
prealable, N8), a 744 comme a 390.
**Avant fusion** : rien de ma porte.

### P9 - Aucune teinte sur un ecran de jeu (`APM-5`)

**Constat.** Releve a chaque etape de trois parties : 0 element portant une teinte de matiere sur la manche, le retour
d'erreur ("Ce n'est pas ça. Essaie encore !" et l'indice), la fin de partie, la confirmation ; 0 aussi sur la page d'un
segment inconnu et la 404. Les couleurs de la manche sont celles des positions de proposition, anterieures au chantier.
**Motif.** Condition tenue. Je n'ai pas observe de revelation dans mes parties : la, `APM-5` est tenu par le gabarit, pas par
mon jeu.
**Avant fusion** : rien.

### P10 - Aucun gris (`APM-6`)

Voir P3. Les onze teintes de `matieres.yaml` sont des couleurs ; les seize tuiles rendues en CP et CM1 aussi.

## 3. Ce qui est exige avant fusion

1. **`APM-12`** : *"Choisis ta classe."* a la place de *"Choisis ton niveau."* (P4). C'est la seule exigence de ma porte.

Le reste de ce que `lea-dev` remonte n'est pas a moi : R1 (forme de reference du pictogramme) a `theo-archi` ; R8 (b),
l'adresse `/niveaux/CP;jsessionid=...` au premier clic, que j'ai vue aussi, a `theo-archi` ; R5 a `camille-po`. R8 (a), la
puce restee marquee apres la confirmation : la classe reste posee dans le DOM, mais **l'apparence est eteinte** (fond
transparent releve sur les trois puces) ; l'enfant ne voit aucune puce active qu'il n'a pas produite, `S15-5` tient.

## 4. Ce qui n'est pas verifie

- **Clavier** : le bandeau colle peut-il cacher la carte qui recoit le focus (point 4 de ma section 6, ouvert). Ma
  tabulation emulee ne fait pas defiler la page comme un ecran reel ; je ne retiens aucun de ses chiffres.
- **Lecteur d'ecran, tablette reelle, Safari, daltonisme** : non joues.
- **La revelation** apres plusieurs erreurs : non observee.
- **Aucun enfant n'a joue** : la lecture d'un enfant de CP est mon jugement de maitresse, pas une observation.
- **Jobs `prefixes`, `chemins`, `outils` sur l'arbre de `lea-dev`** : non lances par moi ; je ne prononce pas la porte
  technique.

## 5. Routage et prix

| Destinataire | Ce que je route |
|---|---|
| `lea-dev` | `APM-12`, avant fusion |
| `camille-po` | `APM-R1` en story ; R7 (reecrire "Tenu en session" en deux gestes) ; R5 (le seuil de 10, par une `DEC`) |
| `theo-archi` | le moyen d'`APM-R1` (adresse apres la confirmation) ; R8 (b) |
| `ines-design` | rien d'exige ; la place de la phrase du premier ecran, si elle le juge utile |
| `sofia-sm` | le prix : **1,0**, comme annonce en section 7 de mon arbitrage prealable |

**Je reste seule garde**, en jouant, de la reconnaissance des pictogrammes, de la teinte qui ne se lit pas "desactive", de la
confusion entre le bouton de vue et "Les jeux", et du bandeau colle sous le focus clavier. Aucun test ne tient ces quatre
proprietes.
