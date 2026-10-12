# DEC-0117 - Ouverture du chantier hors sprint "accueil par matiere" : V2 (cartes groupees par matiere)
# par defaut, V1 (deux etages), un bouton de l'accueil bascule, le choix vit en session. Aucun sprint s57 n'est ouvert.

- **Type** : decision d'ouverture d'un chantier hors sprint (perimetre, ordre, routage, questions ouvertes)
- **Date** : 2026-10-11
- **Auteur** : `camille-po`
- **Sprint** : hors sprint. s56 est clos ; **aucun `planning.md` n'est ouvert** et cette decision n'en ouvre pas
  (section 3).
- **Competence programme visee** : transverse - pas de contenu pedagogique. Aucune ligne de
  `MATRICE-couverture.md` ne change de statut. Les matieres citees sont donnees pour information.
- **Statut** : **Acceptee** le 2026-10-12, amendee (sections 9, 10 et 11). Les mots de l'utilisatrice pour `Q-U1` et `Q-U2` sont
  consignes (section 10), `Q-U2` corrigee par elle le meme jour (section 11) : audit **avant** fusion. `Q-U4` est sans objet
  (section 11). Le chantier est fusionne sur `round-two` (`75290b26`). Aucune question de l'utilisatrice ne reste ouverte.
- **Lien** : `DEC-0112` (precedent d'une session hors sprint) ; `DEC-0028` (rien de persiste) ; `DEC-0032`
  section 3 (intention : boutons de matiere derives du catalogue) ; `DEC-0002` (pictogrammes) ;
  `DEC-0053` (le pli) ; `ADR-0006`, `ADR-0044`, `ADR-0049`, `ADR-0095` ;
  `squad/conformite/FICHE-barre-entree-niveau.md` ; `squad/conformite/FICHE-accueil-par-matiere.md` ;
  `docs/backlog/backlog.md`, section "Chantier hors sprint : accueil par matiere".

## 1. La demande, mots exacts

Utilisatrice, 2026-10-11 : *"il liste comme actuellement prend trop de place et on perd les enfants au
scroll, on n'a aucune idee de la matiere associee"*.

Puis, le meme jour : *"ajoute l'implementation de V1, V2 avec un switch de la vue sur un bouton de
l'accueil"*.

## 2. Ce que l'utilisatrice a arbitre

Cinq arbitrages rendus le 2026-10-11 par questionnaire. **Ils me sont rapportes par l'agent qui m'a lance, qui
les atteste ; je n'ai pas leurs mots, donc je ne les cite pas entre guillemets.** Forme 2 de `DEC-0116` A.2 ne
s'applique pas : ce sont des arbitrages rapportes, pas des mots recus.

1. La vue par defaut est **V2**.
2. **Un bouton de l'accueil** bascule entre V1 et V2.
3. Le choix de vue vit dans la **session HTTP** : rien n'est persiste (`DEC-0028`).
4. Les **libelles courts de matiere et les pictogrammes** sont proposes par `ines-design` et arbitres par
   `nadia-prof` **avant tout code**.
5. Process : squad complete (avis `theo-archi`, implementation `lea-dev`, audit `nadia-prof`, rapport
   `yanis-qa`, relecture).

**V1** : deux etages. Etage 1, les tuiles de matiere (pictogramme et mot) du niveau choisi. Etage 2, les jeux
de la matiere en cartes sur 2 ou 3 colonnes. **V2** : une page de cartes groupees par matiere sous des
en-tetes collants portant un pictogramme.

## 3. Ou range-t-on un chantier hors sprint : verifie, et ce que je n'invente pas

Lu : `squad/README.md` sections 4 (ceremonies) et 9 (derouler un sprint), `DEC-0055`, `DEC-0045`.

- Les sections 4 et 9 decrivent un sprint qui s'ouvre par `planning.md` et se ferme par `review.md`. **Aucune
  ne cree un regime de chantier hors sprint.** Les mots "hors sprint" y designent des amendements de process
  (sections 3, 6, 9), pas un mode de travail.
- `DEC-0045` section 3 et `DEC-0055` regissent un verdict `REFUSE` sur une entree de catalogue : retrait
  immediat, fenetre de grace bornee, **48 heures au plus hors sprint**. Ce n'est pas un regime de chantier. Il
  ne s'applique ici qu'en cas de `REFUSE`, et une vue d'accueil n'est pas une entree de catalogue : rien ne
  s'y retire. Ce vide est la question `Q-U2`.
- **Le seul precedent d'un travail mene hors sprint est la session "s53 special"** : une decision d'ouverture
  (`DEC-0112`), une section de `docs/backlog/backlog.md` qui porte les stories, un fait verse a
  `squad/rapports/JOURNAL-gouvernance-hors-sprint.md`. Elle a ensuite ete absorbee par un sprint (s53).

**Je reprends ce precedent et rien d'autre.** Un chantier = cette `DEC` + une section du backlog + une fiche de
conformite + une entree de journal. **Pas de dossier `squad/sprints/s57-...`** : s57 n'est pas ouvert, ses
postes connus (`S54-OBS-SCI-CONSIGNE`, troisieme report interdit ; reprise d'`ART-CP-10` ; `VSJ-CE1-R1`) sont
inchanges, et **ce chantier n'est pas un report de l'un d'eux**. Je ne cree pas non plus de dossier
`squad/chantiers/` : le depot n'en a pas, et un dossier neuf pour un seul chantier serait une convention que
personne n'a decidee.

Rangement des pieces, sur le modele des fichiers hors sprint deja presents :

| Piece | Auteur | Fichier |
|---|---|---|
| Avis de faisabilite | `theo-archi` | `squad/rapports/avis-faisabilite-accueil-par-matiere.md` |
| Propositions V1, V2, libelles, pictogrammes | `ines-design` | `squad/design/proposition-ines-accueil-par-matiere.md` |
| Arbitrage prealable (libelles, pictogrammes, conditions) | `nadia-prof` | `squad/conformite/ARBITRAGE-PREALABLE-accueil-par-matiere.md` |
| Fiche de conformite (j'ouvre, elle valide) | `camille-po` puis `nadia-prof` | `squad/conformite/FICHE-accueil-par-matiere.md` |
| Rapport qualite | `yanis-qa` | `squad/rapports/rapport-qualite-accueil-par-matiere.md` |

Ces noms suivent `avis-faisabilite-sNN-...` (rapports), `proposition-ines-sNN-...` (design) et
`ARBITRAGE-PREALABLE-sNN-...` (conformite) en remplacant le numero de sprint par le nom du chantier. Si
`sofia-sm` prefere un autre rangement, elle le dit dans le journal ; je ne le tiens pas pour une regle.

## 4. Etat de depart, mesure le 2026-10-11

Perimetre : jar construit depuis le worktree `camille-accueil`, base `8ac33440` (= `round-two`), par
`bash outils/mvn-jdk21.sh -B -q -DskipTests package` sur la racine du reacteur (pas de `-pl`, donc pas d'ecart
de `~/.m2`) ; sert par `java -jar bootstrap/target/recreo.jar`. Ce n'est pas la commande de la porte technique
(`mvn -B verify`) : c'est une mesure de rendu, pas un verdict de CI.

**Methode de defilement (a rejouer, par `yanis-qa`)** : Chrome headless, profil neuf, fenetre emulee 744 x 551
(`DEC-0040`, facteur d'echelle 1, 744 est au-dessus du plancher de 516 px d'`ADR-0025`), page chargee, puis
`getBoundingClientRect()` de chaque carte. **Une carte est "visible sans defiler" si son `bottom` est inferieur
ou egal a `innerHeight` (551)** : une carte coupee ne compte pas. Les cartes comptees sont les elements de la
classe de bouton de demarrage d'un jeu. Les cotes sont lues sur le jar qui sert, jamais sur un gabarit
(`lessons.md`, s56). Aucun script n'est commite par cette decision : la commande reste a ecrire et a commiter
par `yanis-qa` (`HS-ACCUEIL-MESURE`).

| Niveau | Jeux (boutons) | Cartes visibles sans defiler, 744 x 551 | Cartes coupees par le pli | Hauteur du document | Matieres nommees a l'ecran |
|---|---|---|---|---|---|
| CP | **59** | **5** | 1 | 4216 px (7,65 ecrans) | **0** |
| CE1 | **30** | **5** | 1 | 2244 px | **0** |
| CM1 | **58** | **5** | 1 | 4148 px | **0** |

Un bouton fait 56 px de haut, au pas de 68 px, de 199 a 246 px de large, sur une seule colonne. La liste
commence a y = 168. `accueil.html` ne contient pas le mot "matiere" (0 occurrence).

Comptes derives du catalogue et des paquets (`catalogue.yaml` a 147 entrees, 147 paquets distincts ; la cle
`matiere` du `descripteur` de chaque paquet lu par un script jetable, non commite) :

- **147** jeux = 59 CP + 30 CE1 + 58 CM1. `GET /` rend 147 boutons ; `GET /niveaux/CP` en rend 59.
- **11 valeurs distinctes de matiere** : CP en porte 8, CE1 2, CM1 8. Le plus gros groupe d'un niveau est **19**
  jeux (sciences et technologie, CM1) ; le plus petit 1 (langues vivantes, CP et CM1 ; histoire des arts, CM1).
- Longueur du libelle de matiere tel qu'il est ecrit dans la donnee, en caracteres : 15 a 30. Les plus longs :
  `Education physique et sportive` 30, `Enseignement moral et civique` 29, `Langues vivantes (anglais)` 26,
  `Sciences et technologie` 23.
- Aucune source de production ne lit `ContentDescriptor.matiere()` ; aucun mot de matiere n'est ecrit dans un
  `.java`, un `.html`, un `.css` ou un `.js` de production (0 occurrence de quatre libelles testes).

**Ce que je n'ai pas mesure** : `GET /` a 744 x 551 (ma commande n'a rendu aucune valeur pour cette page ; je ne
cite donc aucun chiffre pour elle) ; les largeurs autres que 744 ; le temps de rendu.

## 5. Questions ouvertes, posees toutes ensemble

- **`Q-U1` (utilisatrice) - Que montre `/`, quand aucun niveau n'est choisi ?** Aujourd'hui, `/` liste les 147
  jeux des trois niveaux sans pastille active. V1 est defini sur "le niveau choisi" ; V2 aussi, par la demande
  ("niveau actif respecte"). Sans niveau, il n'y a pas de niveau a respecter. Options, sans ma preference
  imposee : (a) `/` garde la liste actuelle telle quelle ; (b) `/` groupe par matiere les trois niveaux (un
  enfant de CP voit alors des jeux de CM1, comme aujourd'hui) ; (c) `/` demande le niveau d'abord. **Tant
  qu'elle n'a pas repondu, les stories ne couvrent que `/niveaux/{niveau}`**, et `/` n'est pas modifie.
- **`Q-U2` (utilisatrice) - L'audit de `nadia-prof` precede-t-il la fusion sur `round-two`, ou la suit-il ?**
  Une vue refusee n'est pas une entree de catalogue : `DEC-0045` section 3 n'a rien a retirer, et `DEC-0055`
  fixerait 48 heures a une fenetre qu'aucun mecanisme n'ouvre. Si la fusion precede l'audit, un `REFUSE`
  laisse l'accueil refuse en service. **Ma recommandation de PO, que je lui soumets sans l'appliquer : audit
  avant fusion, pour ce seul chantier, parce que l'accueil est la premiere page que voit chaque enfant.**
- **`Q-T1` (`theo-archi`, via `sofia-sm`) - Ou vit le libelle court ?** `ADR-0049` point 2 ecrit : *"Il n'y a pas
  de second champ, pas de libelle d'affichage distinct, pas de table de correspondance"*, et nomme d'avance ce
  qui le ferait changer d'avis : *"un bouton trop etroit pour 'Questionner le monde', par exemple"*. L'utilisatrice
  demande des libelles courts : le besoin n'est plus anticipe. L'ADR reste a amender ou a confirmer par lui ;
  **je ne choisis ni le champ, ni l'endroit, ni la table**.
- **`Q-T2` (`theo-archi`) - L'ordre d'affichage des matieres.** `ADR-0095` porte l'ordre des niveaux par une
  sequence declaree une fois, avec un garde-fou de completude. Les matieres n'ont aucun ordre. Sans decision,
  l'ordre est celui d'apparition au catalogue, qui n'a pas ete ecrit pour cela (`ADR-0095`, contexte). Le
  produit demande seulement : un ordre **stable** (meme niveau, meme ordre a chaque chargement) et **derive de
  la donnee**, jamais d'une liste de matieres ecrite a la main (`ADR-0006`, `DEC-0032` section 3). Je n'ai
  pas d'avis sur l'ordre pedagogique des matieres : c'est `nadia-prof` qui l'a, si elle en a un.
- **`Q-T3` (`theo-archi`) - Le port entrant porte-t-il la matiere jusqu'au gabarit ?** Je n'ai pas trouve de
  lecture de `matiere()` en production (section 4). Constat, pas jugement : je ne sais pas ce que cela coute.
- **`Q-N1` (`nadia-prof`) - Le choix de vue en session contredit-il `S15-5` ?** Elle a refuse en s15 qu'un filtre
  survive a un chargement de page (`FICHE-barre-entree-niveau.md`). Le choix de vue n'est pas un filtre, et
  `ADR-0044` point 4 range le *niveau* dans l'URL, pas la vue. **Je pense que les deux ne se recouvrent pas ; je
  ne le juge pas.** Elle le dit dans son arbitrage prealable.
- **`Q-N2` (`nadia-prof`, `ines-design`) - Depuis un jeu, `Les jeux` ramene a quoi en V1 : l'etage 1 ou l'etage
  2 de la matiere jouee ?** Question d'usage, pas de technique. Les stories exigent que la vue (V1 ou V2) soit
  conservee ; l'etage n'est pas dit.

## 6. Decision

1. **J'ouvre le chantier hors sprint** sous ce numero, avec les six stories de la section "Chantier hors sprint :
   accueil par matiere" de `docs/backlog/backlog.md` (ordre ci-dessous). **Je n'ouvre pas s57.**
2. **Ordre d'execution.** (i) `theo-archi` et `ines-design` en parallele, sans code ; (ii) `nadia-prof`
   arbitre libelles et pictogrammes **avant tout code** (arbitrage 4 de l'utilisatrice) ; (iii) `lea-dev`
   implemente ; (iv) `yanis-qa` mesure ; (v) `nadia-prof` joue ; (vi) `relecteur-pedagogique` relit. Rien n'est
   fusionne sur `round-two` tant que `Q-U2` n'a pas de reponse.
3. **Les criteres de defilement sont des criteres de produit mesures par `yanis-qa`, pas un motif de `REFUSE`.**
   `DEC-0053` (arbitrage 1) a sorti le pli de la porte pedagogique "pour tout le catalogue". Je n'affirme pas que
   cette decision couvre l'accueil, qui n'est pas un jeu : si `nadia-prof` considere qu'elle s'y applique ou
   non, elle l'ecrit. Les criteres des stories ne dependent pas de sa reponse.
4. **Rien dans le catalogue, les paquets ou la matrice ne change.** Les 147 jeux restent exposes ; les
   verdicts de `nadia-prof` ne sont ni rouverts ni anticipes.
5. **Le suivi du chantier se fait dans la fiche et dans l'entree de journal**, pas dans un `RAPPORT.md` : il
   n'y a pas de sprint pour le recevoir.
6. **Capacite de `nadia-prof`.** L'arbitrage et l'audit consomment du temps qu'aucun sprint n'a prevu. Je
   n'ecris aucun prix : c'est elle qui le fixe (`PAR-10`), et `sofia-sm` le deduit du planning de s57 quand il
   s'ouvrira.
7. **Rapport au filtre par matiere.** Le filtre par matiere avait ete differe trois fois (`DEC-0032` section 2,
   `ADR-0049` contexte ; je cite l'ADR sans avoir releve les trois dates). Ce chantier n'est pas un quatrieme
   report : il met la matiere a l'ecran sans filtre. `DEC-0032` section 3 (boutons derives du catalogue, une
   matiere sans jeu n'affiche rien) devient un critere de `HS-ACCUEIL-V1` et `HS-ACCUEIL-V2`.

## 7. Risques, a lire avant l'avis de `theo-archi`

- **`R1` - Cible de touche.** Le garde-fou `TailleDesTextesEtBoutonsTest` exige 44 px de hauteur minimale et un
  plancher de texte de 1,25 rem. Une tuile, une carte ou le bouton de bascule qui n'entre pas dans sa
  population echappe au garde-fou. La population est derivee du gabarit brut (`button, a[href]`) : un
  composant place dans un fragment ou un gabarit neuf peut ne pas y etre.
- **`R2` - Couleur seule.** `M11` clause 1 : *"le signal non textuel accompagne le texte, jamais la couleur
  seule"*. Une matiere distinguee par sa couleur de groupe, ou la vue active du bouton de bascule marquee par un
  fond, violerait la clause. Le nom de la matiere et la vue active doivent etre lus en mots.
- **`R3` - Garde-fous de l'accueil.** `FICHE-barre-entree-niveau.md` : `BARRE-5` (un seul `Les jeux` visible par
  ecran : ni tuile ni bouton de bascule ne porte ce mot), `BARRE-6` (une puce n'est jamais plus saillante
  qu'une action de l'ecran : le bouton de bascule n'est pas une action de jeu non plus), `BARRE-8` (un seul rang
  dans l'en-tete : le bouton est dans l'accueil, pas dans l'en-tete, par la demande), `BARRE-4` (aucun
  pictogramme dans une puce de niveau : les pictogrammes de matiere ne touchent pas les puces).
- **`R4` - En-tetes collants a 551 px.** Un en-tete d'application de 76 px et un en-tete de groupe collant ne
  laissent qu'une partie de 551 px aux cartes. `ines-design` chiffre la place laissee, sous la cote mesuree.
- **`R5` - Pictogrammes.** `DEC-0002` : un pictogramme d'interface lu par un enfant est juge par `nadia-prof`. Un
  pictogramme sans mot, ou un mot sans pictogramme accessible, est une question d'accessibilite avant d'etre
  une question de gout.
- **`R6` - Un jeu perdu.** Deux etages (V1) ajoutent un chemin ou un jeu peut devenir inatteignable (une matiere
  dont la tuile n'est pas rendue). La completude se compte sur le catalogue servi, par niveau.
- **`R7` - Le comportement dans le JavaScript.** Dette 4 de la version precedente : pas de comportement de
  mecanique dans le JS client. La bascule est une action serveur ; je ne decide pas du mecanisme.
- **`R8` - Session.** `ADR-0044` point 4 et `S15-5` : rien n'est conserve d'un chargement a l'autre hors de
  l'URL. Le choix de vue est la premiere donnee d'affichage mise en session ; `Q-N1`.
- **`R9` - Contenu nouveau, matiere nouvelle.** Une douzieme matiere doit apparaitre sans edition de code ni de
  gabarit (`ADR-0006`, `ADR-0049` point 3). C'est le critere de derivation de `HS-ACCUEIL-LIBELLES`.

## 8. Ce que je route

- **`sofia-sm`** : (a) verser l'ouverture du chantier a `squad/rapports/JOURNAL-gouvernance-hors-sprint.md`
  (journal qu'elle tient) ; (b) affecter les six stories ; (c) lancer `theo-archi` et `ines-design` ; (d)
  consigner dans le planning de s57, quand il s'ouvrira, la capacite deja consommee par ce chantier ; (e) poser
  `Q-U1` et `Q-U2` a l'utilisatrice, et me renvoyer ses mots tels quels.
- **`theo-archi`** : l'avis, `Q-T1` a `Q-T3`, et la regle ArchUnit de tout ADR qu'il ecrit.
- **`ines-design`** : une proposition pour V1, V2, le bouton de bascule, onze libelles courts et onze
  pictogrammes, chiffree a 744 x 551 ; elle propose, elle ne tranche pas.
- **`nadia-prof`** : libelles et pictogrammes (arbitrage 4), `Q-N1`, `Q-N2`, puis l'audit.
- **`yanis-qa`** : la commande de mesure de la section 4, commitee (`HS-ACCUEIL-MESURE`).
- **`lea-dev`** : rien avant l'arbitrage de `nadia-prof` et l'avis de `theo-archi`.

## 9. Amendement du 2026-10-12 (cloture du chantier, `camille-po`) : `Q-U1` et `Q-U2` sont repondues

Rien au-dessus n'est reecrit. Pour les deux questions, **les mots de l'utilisatrice : reponse non retrouvee dans
le depot** (aucun fichier ne les cite). Ce qui est ecrit, et par qui :

- **`Q-U1` : l'option (c), `/` demande le niveau d'abord.** Sources : la javadoc de `PartieController.accueil`
  (*"decision de l'utilisatrice (Q-U1) : `/` demande d'abord le niveau"*) ; `rapport-lea-dev.md` de s56, section 16
  (*"Decisions de l'utilisatrice appliquees : [...] `/` demande d'abord le niveau (`Q-U1`)"*, R2) ;
  `audit-nadia.md` P5 et P7 ; `rapport-qualite.md` section 3, etape 1 (`/` rend *"Choisis ton niveau."* sur le
  jar mesure, avant `APM-12`). **Effet** : la restriction de la section 5 (*"les stories ne couvrent que
  `/niveaux/{niveau}`, `/` n'est pas modifie"*) est levee ; `/`, `POST /retour` et `POST /retour/confirmer`
  rendent le choix de la classe, sans aucune liste de jeux. Le niveau choisi s'ouvre dans la vue retenue en
  session.
- **`Q-U2` : l'audit precede la fusion.** Source : `rapport-lea-dev.md`, section 16 (*"l'accueil ne va pas dans
  `round-two` avant l'audit de `nadia-prof` (`Q-U2`)"*). Fait de l'historique Git, relu ici : le verdict de
  `nadia-prof` (`99008d84`, 2026-10-11 20:49) precede les trois fusions de la branche d'implementation, de son
  audit et de la mesure (`62b4aa75`, `99a896c8`, `75290b26`, 21:11). C'est ma recommandation de la section 5,
  suivie ; le critere de fusion de la section 6, point 2, est tenu.
- **Ce que je ne peux pas dire** : l'exigence `APM-12` (*"Choisis ta classe."*) est posee par `3187d7f2`
  (21:08), **apres** l'audit. `FICHE-accueil-par-matiere.md` ecrit que le Done vaut pour `def23ead` *augmente
  d'`APM-12`*. Que `nadia-prof` ait rejoue `APM-12` sur l'arbre fusionne : non retrouve dans le depot. Le texte
  est present dans `accueil.html` et dans l'inventaire de `TexteAccentueDesGabaritsTest` (lu, non lance par moi).
  A `nadia-prof` de dire si cela suffit a sa condition ; je ne le juge pas.

**Les arbitrages de la section 2** (V2 par defaut, bouton de bascule, vue en session, libelles avant le code,
squad complete) sont livres et joues ; ils restent des arbitrages rapportes, non cites.

**Ce que le chantier laisse ouvert, par proprietaire** (rien n'est tranche ici ; detail dans le journal du
2026-10-12) : `theo-archi` (adresse apres `/retour/confirmer`, `;jsessionid` dans les URL, forme de reference du
pictogramme) ; `nadia-prof` (clavier et bandeau colle, reconnaissance des pictogrammes, `APM-12` rejoue) ;
`yanis-qa` (E2 : aucun test Maven ne joue le catalogue reel dans les deux vues) ; `camille-po` (`APM-R1`,
reserve de `nadia-prof`, et E5 : pas encore ecrits en story, voir le journal). Seuil de defilement : `DEC-0118`.

## 10. Amendement du 2026-10-12 (apres la cloture, `camille-po`) : mots de l'utilisatrice, comptes, `Q-U4`

Rien au-dessus n'est reecrit. Les sections 4 a 9 gardent leur texte ; ce qu'elles disent des mots absents est remplace par ce qui suit.

### 10.1 Les mots de l'utilisatrice, forme 2 de `DEC-0116` A.2

Mots recus directement dans la session du 2026-10-12, cites tels quels :

- **`Q-U1`** : *"ok pour c"*. Option (c) : `/` demande le niveau d'abord. Confirme la section 9 ; la source n'est plus
  seulement un rapport d'agent.
- **`Q-U2`** : *"non audit apres fusion"*. **Remplacee le meme jour par la section 11 : la regle est audit avant fusion.** Le
  texte qui suit est conserve tel qu'ecrit avant la correction. **Ce n'est pas ma recommandation** (section 5 : audit avant fusion), et **ce n'est pas ce
  qui s'est passe pour ce chantier**. Les deux faits, cote a cote, sans que je les concilie a sa place :
  1. Sa reponse regit les chantiers hors sprint.
  2. Pour ce chantier, l'audit a precede la fusion (verdict `99008d84` a 20:49, fusions `62b4aa75`, `99a896c8`, `75290b26` a
     21:11) et le verdict est **VALIDE SOUS RESERVE** : aucun `REFUSE` n'a eu a etre traite.

  La section 9 (*"l'audit precede la fusion"*) decrit donc ce qui s'est passe, pas la regle que l'utilisatrice a choisie. Le
  critere de fusion de la section 6, point 2, etait ma proposition ; il ne vaut plus pour les chantiers suivants.

### 10.2 Question residuelle

- **`Q-U4` (utilisatrice, sans objet depuis la section 11) - Quel mecanisme s'applique a un `REFUSE` prononce apres la fusion d'un chantier hors sprint ?** Je ne
  le decide pas. Ce que le depot donne : `DEC-0045` section 3 et `DEC-0055` regissent le retrait d'une entree de catalogue, avec
  48 heures au plus hors sprint ; une vue d'accueil n'est pas une entree de catalogue, rien ne s'y retire (section 5, `Q-U2`).
  Aucun code ni story ne depend de la reponse pour ce chantier.

### 10.3 Compte des jeux : 146, pas 147

Verifie le 2026-10-12 sur l'arbre de `cloture-camille2` : `catalogue.yaml` porte 146 entrees (`grep -c '^  - id:'` = 146) et
0 entree `rapprocher-deux-oeuvres`. Les 147 (CP 59) des sections 4 et 5 sont la mesure du 2026-10-11 sur la base `8ac33440`,
avant le retrait de `13d00bfe` ; le catalogue servi compte **146 jeux (CP 58, CE1 30, CM1 58)**. Ils ne sont pas reecrits au-dessus ;
c'est 146 qu'il faut lire pour le catalogue actuel.

### 10.4 Statut, routage, limites

- Statut `Proposee` au moment de cette section, motif : `Q-U4`, levee par la section 11. Aucun changement de code : rien a router a `sofia-sm` pour cette annotation.
- Ne prononce aucun Done, ne touche ni catalogue, ni paquet, ni matrice, ni code.

## 11. Amendement du 2026-10-12 (correction de `Q-U2` par l'utilisatrice, `camille-po`)

Rien au-dessus n'est reecrit ; la section 10.1 est annotee, pas supprimee.

Mots recus directement dans la session du 2026-10-12, cites tels quels (forme 2 de `DEC-0116` A.2), a propos de `Q-U4` :
*"non nadia passe avant la fusion comme ca on peut re corrgier si besoin"*.

1. **Regle, pour tout chantier hors sprint : l'audit de `nadia-prof` precede la fusion sur `round-two`.** Elle corrige sa reponse
   precedente a `Q-U2` (*"non audit apres fusion"*, section 10.1, conservee). Le critere de fusion de la section 6, point 2 (rien
   n'est fusionne sans l'audit) est **maintenu** et vaut pour les chantiers suivants, contrairement a ce que ecrivait la section 10.1.
2. **`Q-U4` est sans objet** : sans fusion avant verdict, un `REFUSE` ne laisse rien en service, donc rien a retirer apres. Le vide
   de `DEC-0045` section 3 et `DEC-0055` (une vue n'est pas une entree de catalogue) n'a plus a etre comble pour ce cas. Un `REFUSE`
   se corrige avant fusion, par une story corrective.
3. **Pour ce chantier**, rien ne change : l'audit avait precede la fusion (section 10.1).
4. **Routage.** La regle est une regle de process, pas un changement de code. `squad/README.md` est tenu par `sofia-sm` : je lui
   demande, par le journal, de l'y ecrire (sections 4 et 9, ou le precedent hors sprint n'a pas de regle). Tant que ce n'est pas
   fait, cette section est la seule source.
5. **Relecture complete** : aucune question adressee a l'utilisatrice ne reste ouverte (`Q-U1` repondue, `Q-U2` corrigee, `Q-U4`
   sans objet). `Q-T1` a `Q-T3`, `Q-N1` et `Q-N2` sont adressees a `theo-archi` et `nadia-prof`, pas a elle ; elles ne bloquent pas
   le statut de cette decision, et leur suivi est dans le journal du 2026-10-12.
6. **Statut : `Acceptee`.** Ne prononce aucun Done, ne touche ni catalogue, ni paquet, ni matrice, ni code.

## 12. Amendement du 2026-10-12 (vue par defaut, `camille-po`, `DEC-0119`)

Rien au-dessus n'est reecrit. Le titre, la section 2 (*"1. La vue par defaut est **V2**"*), la section 6 et les stories de
`HS-ACCUEIL-V2` gardent leur texte d'origine.

Mots recus directement dans la session du 2026-10-12, cites tels quels : *"par defaut apres avoir choisi sa classe montre le
choix de la matiere"*.

1. **La decision 1 de la section 2 est amendee** : la vue par defaut d'une session qui n'a rien choisi est **V1**
   (etage 1, une tuile par matiere), non V2. Les decisions 2 a 5 de la section 2 (bouton de l'accueil, choix en session,
   libelles arbitres avant le code, squad complete) sont inchangees. V2 reste accessible par le bouton.
2. **Le titre se lit** : "V1 par defaut, V2 par la bascule". Les occurrences de "V2 par defaut" de cette decision, du
   backlog et de la fiche decrivent l'etat du 2026-10-11 au 2026-10-12.
3. **Les criteres, la condition de fusion (audit de `nadia-prof` avant fusion, section 11), le routage et les questions
   pour `theo-archi` et `nadia-prof` sont dans `DEC-0119`.** Statut `Acceptee` inchange.
4. Ne prononce aucun Done, ne touche ni catalogue, ni paquet, ni matrice, ni code.

## Impact

- `squad/decisions/produit/DEC-0117-...md` (ce fichier) : cree.
- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0117` ajoutee, statut `Proposee`.
- `docs/backlog/backlog.md` : section "Chantier hors sprint : accueil par matiere" ajoutee en fin de fichier.
- `squad/conformite/FICHE-accueil-par-matiere.md` : ouverte ; bloc de validation vide.
- `squad/programme/MATRICE-couverture.md`, `catalogue.yaml`, les paquets, tout fichier sous `domain/`,
  `application/`, `adapters/`, `bootstrap/` : **inchanges**.
