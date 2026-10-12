# Audit pedagogique - accueil par matiere, V1 par defaut (`DEC-0119`)

- **Auteure** : `nadia-prof`. **Date** : 2026-10-12 16:41 (UTC+02:00), lue par `date`.
- **Arbre** : worktree `v1-nadia`, tip `95909939` (fusions candidates de `v1-defaut-camille` et `v1-defaut-lea`, code
  `a74ff775`), `bash outils/verifier-lignee.sh` sort 0. Audit avant fusion (`DEC-0117` section 11, `DEC-0119` section 5).
- **Verdict** : **VALIDE SOUS RESERVE**, sans condition avant fusion. Reserve neuve `APM-R2`, non bloquante. `APM-R1`
  levee. Ecrit dans `squad/conformite/FICHE-accueil-par-matiere.md`, bloc de validation ; ce rapport en donne le detail.

## 0. Methode

`bash outils/mvn-jdk21.sh -B -DskipTests package` a la racine, JDK 21.0.10 rapporte par l'enveloppe. Jar servi par
`java -jar bootstrap/target/recreo.jar --server.port=8092` sur le JDK de la toolchain, port verifie libre par `netstat`,
PID lu dans le journal de demarrage et confirme par `netstat`, arrete par ce seul PID, port libere verifie. Chrome headless
a profil jetable, pilote par le protocole de debogage, clics souris au centre des elements rendus, fenetre 744 x 551 puis
390 x 551 ; chaque Chrome arrete par son PID. Releve du DOM (`getBoundingClientRect()`, methode de `DEC-0117` section 4) et
capture a chaque etape. Six sessions, trois parties jouees jusqu'a "C'est fini !" ou interrompues, en me trompant d'abord.
Scripts, releves et captures hors du depot. Porte technique : section 5.

## 1. Le parcours, tel que je l'ai joue

| # | Geste | Ce que l'ecran rend |
|---|---|---|
| 1 | `/`, session neuve | *"Choisis ta classe."*, trois puces, aucune puce active |
| 2 | puce CP | **etage 1** : "Pour le CP", "Tout voir", 8 tuiles entieres sur 8, aucun titre de jeu |
| 3 | tuile Maths | etage 2 : 13 cartes, 8 entieres ; chevron "Par matière" |
| 4 | "Par matière" | etage 1, "Tout voir" |
| 5 | "Tout voir" | V2 : 58 cartes, 8 entieres ; bouton "Par matière" |
| 6 | "Par matière" | etage 1 ; puces CE1 et CM1 : etage 1 (2 et 8 tuiles entieres), vue retenue |
| 7 | session neuve, CP, Maths, "Les petites additions", partie jusqu'a la fin en me trompant | retour d'erreur, indice, revelation *"Appuie sur la bonne réponse : 4"*, *"C'est fini !"* |
| 8 | "Les jeux", puis puce CP | *"Choisis ta classe."*, adresse `/` ; puis **etage 1**, sans aucun appui sur la bascule (C1) |
| 9 | session neuve, CP, "Tout voir", une partie, puce CE1 pendant la partie | *"Si tu pars, ta partie s'arrête."* ; "Continuer la partie" rend la meme manche |
| 10 | "Les jeux" de l'en-tete, puis "Les jeux" de la confirmation, puis puce CE1 | *"Choisis ta classe."*, adresse `/` ; puis **V2** du CE1, 8 cartes entieres sur 30, "Par matière" (C2, D4) |
| 11 | "Par matière", puis puce CM1 | etage 1 du CM1 (D2) |
| 12 | session neuve, CM1, Histoire-géographie, partie jusqu'a la fin en me trompant | 4 cartes entieres sur 4 ; six retours d'erreur lus, fin ; "Les jeux" mene a `/` |
| 13 | session neuve, CP, Maths, partie, "Les jeux", "Les jeux", rechargement | adresse `/`, puis *"Choisis ta classe."* au rechargement |
| 14 | session neuve, CP, Français, partie, **retour arriere du navigateur** | l'etage 1 du CP, avec "Tout voir", la partie en cours |
| 15 | "Tout voir", puis "Continuer la partie", puis sortie par "Les jeux" et la confirmation, puis puce CP | confirmation, puis la meme manche ; puis **V2** (section 3, `APM-R2`) |
| 16 | 390 x 551, session neuve par niveau | etage 1 CP : 4 tuiles entieres sur 8, la troisieme rangee coupee au bas de l'ecran ; CE1 : 2 sur 2 ; CM1 : 4 sur 8 ; V2 CP : 3 cartes entieres |

Reponses relevees : seul `Set-Cookie` de `/niveaux/CP` et de la bascule, `JSESSIONID` sans `Max-Age` ni `Expires` (D3).

## 2. Les questions de `camille-po` (`DEC-0119` section 6)

### Q1 - `N4` et ma lecture de `S15-5` tiennent-elles avec V1 par defaut ?

**`N4` est remplacee, ma lecture de `S15-5` tient.** `N4` acceptait une decision de l'utilisatrice, V2 par defaut ; la
decision a change (`DEC-0119`), `N4` n'a plus d'objet et je ne la reecris pas. Ma part etait la lecture de `S15-5`, et elle
vaut pour l'etage 1 comme pour V2, pour les memes raisons, verifiees en jouant :

1. **L'etage 1 est une presentation, pas une selection.** Il montre toutes les matieres de la classe et aucun jeu, et
   chaque jeu y est a un appui de sa tuile ; "Tout voir" est a l'ecran. Un enfant devant l'etage 1 ne croit pas que des jeux
   ont disparu : il n'en voit aucun, et il voit ou les trouver.
2. **Aucun controle ne marque la vue courante.** Un seul bouton, dont le libelle dit la destination ; aucune coche.
3. **La seule marque active est celle que l'enfant a produite** : la puce de la classe qu'il vient de presser.
4. **La session ne retient que la vue** (`APM-8`) : apres une partie lancee depuis l'etage 2 de Maths, la puce CP rend
   l'etage 1, jamais l'etage 2 (ligne 8).

**V1 par defaut : ACCEPTEE**, aux memes conditions que `N4` : `APM-7`, `APM-8`, `APM-9`, inchangees et tenues.

### Q2 - ma raison de refuser `O2` vaut-elle pour l'etage 1 ?

**Oui, sans changement.** Le motif ne dependait pas de la vue choisie par defaut : quelle qu'elle soit, une coche sur la
vue courante montre a l'enfant qui arrive un etat qu'il n'a pas produit. L'etage 1 par defaut le montrerait coche des
l'arrivee, exactement comme V2 l'aurait ete. Le second motif (un symbole dans un controle, refuse le 2026-08-18) est
independant de la vue. `O2` reste refusee.

### Q3 - "8 me suffit" vaut-il pour V2 atteinte par un bouton ?

**Oui.** Mon motif (`audit-nadia.md` P8) etait que l'enfant trouve au premier ecran le nom de la matiere et de quoi
commencer. Un enfant qui presse "Tout voir" a demande a tout voir : l'exigence est plus faible pour un ecran demande que
pour un ecran impose, pas plus forte. Mesure en jouant, V2 atteinte par le bouton : **8 cartes entieres en CP, CE1 et
CM1** a 744 x 551 (CP ligne 5, CE1 ligne 10 ; CM1 dans la session ou j'ai envoye la bascule pendant une partie, section 3). Le seuil reste un critere de produit de
`camille-po` (`DEC-0118`, annotee par `DEC-0119` section 7) ; je ne le change pas.

### Q4 - l'etage 1 a 390 px demande du defilement (4 tuiles sur 8) : accepte, refuse, condition ?

**ACCEPTE, sans condition.** Mesure : 4 tuiles entieres sur 8 en CP et en CM1, 2 sur 2 en CE1, a 390 x 551.

- **Je ne deplace pas mon poteau.** J'ai ecrit le 2026-10-11 (arbitrage prealable N8, `audit-nadia.md` P8) que le pli de
  l'accueil n'est pas un motif de `REFUSE`, "a 744 comme a 390". Que cet ecran soit devenu la page par defaut ne change
  pas ce motif : faire defiler une liste est un geste normal (`R-BARRE-2`, 2026-08-17).
- **Ce que l'enfant voit sans defiler est ce qu'il cherche le plus souvent.** L'ordre de N5 met en tete Français et Maths
  (puis Questionner le monde et Vivre ensemble en CP, Sciences et Histoire-géographie en CM1). Sous le pli : Anglais, Arts
  plastiques, Musique, Sport en CP ; Vivre ensemble, Anglais, Histoire des arts, Sport en CM1.
- **L'ecran dit qu'il y a une suite.** La troisieme rangee est coupee au bas de l'ecran (son haut est a l'ecran, son bas a
  662 px pour 551), et la barre de defilement est visible. Une tuile coupee se lit "il y en a d'autres", la ou une coupe
  tombee exactement entre deux rangees ne dirait rien. **Ce signe n'est tenu par aucun test** : il depend de la hauteur de
  l'ecran. Je ne le pose pas en condition, ce serait une barre neuve sur un motif que j'ai deja ecarte ; je le garde en
  jouant (section 4).

Je ne fixe aucune valeur a 390 px ; `camille-po` non plus (`DEC-0119` section 6, point (d)).

### Q5 - le premier ecran apres le choix de la classe est-il lisible ?

**Oui, pour le CP accompagne comme pour le CM1, et mieux que V2 par defaut.**

- **CP.** L'enfant lit "Pour le CP", "Tout voir" et huit mots courts, chacun a cote de son pictogramme. Aucun titre de jeu :
  sur V2 par defaut, il avait devant lui 58 titres, dont 8 a l'ecran. Huit choix illustres, c'est ce qu'un enfant de six ans
  sait parcourir. "Questionner le monde" reste le mot difficile (N1, `-tion`) ; la loupe et l'adulte qui l'accompagne
  portent la reconnaissance, comme le 2026-10-11. **L'adulte ne dispense d'aucune lisibilite** (`CLAUDE.md`, `DEC-0068`) :
  cet ecran n'a pas besoin de lui pour etre compris, il a seulement besoin de lui pour "Questionner le monde", comme avant.
- **CM1.** Huit libelles lisibles a ce niveau, dont "Histoire-géographie" et "Histoire des arts" sur deux lignes : rien
  d'autre a lire.
- **Pas de phrase de consigne sur l'etage 1, et je n'en demande pas.** Sur `/`, *"Choisis ta classe."* est due parce que
  les puces sont dans la barre, loin d'une page sinon vide. Ici, les tuiles sont les seuls choix de la page, sous la phrase
  "Pour le CP" ; une consigne ajouterait une lecture sans rien apprendre a l'enfant.
- **Le cout, je le nomme** : un appui de plus jusqu'a un jeu (classe, matiere, jeu), et apres chaque partie ("Les jeux",
  classe, matiere, jeu). C'est le choix de l'utilisatrice ; l'appui de plus est un choix lisible, pas une recherche dans
  une liste. En CE1, deux tuiles seulement : l'appui de plus y rapporte peu, et n'y coute rien d'autre.

## 3. Ce que j'ai trouve en jouant

### `APM-R2` - un "Tout voir" annule par "Continuer la partie" est quand meme retenu (reserve neuve et datee, non bloquante)

**Constat** (ligne 15). Pendant une partie, un retour arriere du navigateur rend l'etage 1 avec son bouton "Tout voir".
L'enfant le presse : la confirmation *"Si tu pars, ta partie s'arrête."* s'affiche, il choisit "Continuer la partie", la
manche revient intacte (`APM-11` tenue). Mais quand il quitte ensuite la partie et choisit sa classe, l'accueil s'ouvre en
**V2** : la vue a ete ecrite en session au moment du "Tout voir", avant la confirmation, et "Continuer la partie" ne l'a
pas defaite. Reproduit hors du navigateur, meme session, avec et sans la bascule envoyee pendant la partie : sans, l'etage
1 ; avec, V2. Le symptome se lit dans `PartieController.choisirLaVue` : l'ecriture de la vue precede le test de la partie
en cours. Ce n'est pas `a74ff775` qui l'introduit : le meme ordre existait avec V2 par defaut, dans l'autre sens.

**Motif.** Un geste que l'enfant a annule ne doit pas avoir d'effet. "Continuer la partie" veut dire "je n'ai rien fait".
Non bloquante : il faut un retour arriere pendant une partie, l'ecran atteint est V2 qui ne cache rien et ne porte aucune
marque d'etat, et le bouton "Par matière" le defait en un appui. Ce n'est pas un manquement a `S15-5` : l'enfant a presse
le bouton.

**Attendu, verifiable sans moi** : apres "Tout voir" (ou "Par matière") presse pendant une partie, puis "Continuer la
partie", la vue retenue en session est celle d'avant l'appui ; apres "Tout voir" puis "Les jeux" de la confirmation, la
vue retenue est celle que l'enfant a demandee. Le moyen est a `theo-archi` ; la propriete se tient par un test de route,
pas par mon jeu.

**Correction de mon propre constat du 2026-10-11.** J'ai ecrit dans la fiche : *"La bascule envoyee pendant une partie rend
cette confirmation et la partie reste en cours."* C'est exact, et incomplet : je n'avais pas regarde la vue retenue apres
la confirmation. Le defaut etait deja la ; je ne l'avais pas vu.

### `APM-R1` - levee

La barre que j'avais ecrite : *"apres "Les jeux" de la confirmation, l'adresse est `/`, comme apres "Les jeux" de la fin de
partie"*. Joue (ligne 13) : partie lancee depuis `/niveaux/CP/matieres/maths`, "Les jeux", "Les jeux" ; l'adresse est `/`,
et le rechargement rend *"Choisis ta classe."*. Joue aussi depuis `/niveaux/CE1` (ligne 10). La barre est atteinte
(`a475df4b`, ancetre de l'arbre audite) ; la reserve est levee, son texte reste dans la fiche.

### Rien d'autre

- **`APM-5`** : les deux manches rejouees n'ont aucune teinte de matiere ; aucune n'a ete relevee element par element,
  contrairement au 2026-10-11 (voir section 4).
- **Le chevron "Par matière" de l'etage 2 et "Tout voir" de l'etage 1** ne ressemblent toujours pas a "Les jeux" (P2 du
  2026-10-11).

## 4. Ce qui n'est pas verifie

- **Clavier** : non joue. Le point du bandeau colle sous le focus (V2) reste ouvert ; s'y ajoute l'ordre de tabulation de
  l'etage 1 devenu premier ecran (puces, "Les jeux", "Tout voir", puis les tuiles : non verifie).
- **Lecteur d'ecran, tablette reelle, Safari, daltonisme** : non joues. La coupe de la troisieme rangee a 390 est mesuree
  dans une fenetre emulee de 551 px de haut, pas sur une tablette, dont la barre du navigateur change la hauteur utile.
- **Critere A3** (somme des etages 2 egale au catalogue du niveau) : je n'ai ouvert que trois etages 2 sur dix-huit (Maths CP, 13 jeux ; Français CP ;
  Histoire-géographie CM1, 4 jeux). C'est une mesure de `yanis-qa` (`HS-ACCUEIL-MESURE-DEFAUT`).
- **D1, session expiree** : non jouee ; une session neuve (profil jetable) l'a ete. **D3, partie JavaScript** : non lue.
- **`APM-5` releve element par element** : non refait. **La revelation** a ete vue en CP (ligne 7) ; aucune teinte a l'oeil.
- **Aucun enfant n'a joue.** Ce que je dis de la lecture d'un enfant de CP est un jugement de maitresse.

**Je reste seule garde**, en jouant, de la reconnaissance des pictogrammes, de la teinte qui ne se lit pas "desactive", de
la confusion entre le bouton de vue et "Les jeux", du bandeau colle sous le focus clavier, et desormais **du signe de suite
a 390 px** (une rangee coupee au bas de l'etage 1). Aucun test ne tient ces cinq proprietes.

## 5. Porte technique : ce que j'ai lance

Jobs `build` (son contenu), `prefixes` et `chemins` lances par moi, `outils` non : detail en section 7. Je ne prononce pas
la porte technique ; elle se juge sur la CI de la branche fusionnee.

## 6. Routage et prix

| Destinataire | Ce que je route |
|---|---|
| `camille-po` | `APM-R2` en story ; `N4` remplacee (Q1) ; reponses Q2 a Q5 |
| `theo-archi` | le moyen d'`APM-R2` (l'ecriture de la vue precede le test de la partie en cours) |
| `yanis-qa` | A3, et le test de route d'`APM-R2` |
| `sofia-sm` | le prix : **0,5** (trois niveaux, deux vues, deux largeurs, six sessions) ; la fusion n'attend plus que la porte technique |

## 7. Porte technique, resultat

- **Job `build`, contenu** : `bash outils/mvn-jdk21.sh -B clean verify` a la racine, JDK 21.0.10 rapporte par
  l'enveloppe, BUILD SUCCESS : 120 / 31 / 486 / 1484 / 371 / 6 tests (`domain`, `application`, `adapter-web`,
  `adapter-content`, `bootstrap` unitaires puis integration), 0 echec. Arbre : `95909939` augmente de mon diff.
  **Un premier passage a rougi par ma faute** : `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, seul echec des 371
  de `bootstrap`, parce que j'avais ecrit la ligne d'`APM-R2` sans encore porter la ligne d'etat ; son message donne le
  compte que je recopie dans le registre (947 / 160). Le second passage, apres la mise a jour, est celui qui est cite.
- **Garde-fou du registre seul** : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test
  -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest -Dsurefire.failIfNoSpecifiedTests=false` : 10 tests, 0 echec.
- **Job `prefixes`** : `verifier-prefixes-test.sh` et `commit-atteignable-test.sh` passent ; `verifier-prefixes.sh` sur
  mon message de commit, lance avant de commiter.
- **Job `chemins`** : `verifier-chemins-test.sh` passe ; `verifier-chemins.sh` sur `git ls-files` et sur mes trois
  fichiers : aucun chemin de poste de travail ni nom de compte.
- **Job `outils`** : non lance. Mon diff ne touche aucun outil.
