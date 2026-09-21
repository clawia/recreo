# DEC-0089 - Ouverture du perimetre CE1 : un troisieme niveau, CM2 differe, mathematiques seule instruite pour s36

- **Statut** : Acceptee (confirmee par l'utilisatrice le 2026-09-21, voir "Confirmation du
  2026-09-21" ci-dessous). Aucune competence CE1 n'est fixee par ce document, et rien n'est
  developpable avant que le referentiel qu'il route n'existe.
- **Date** : 2026-09-21
- **Auteur** : `camille-po`
- **Sprint** : hors-sprint (branche `s36-camille`, avant ouverture de s36)
- **Retour qui la fonde** : recu de MASTER, sans source dans le depot, cite mot pour mot,
  2026-09-21 - *"L'utilisatrice a decide d'ouvrir un troisieme niveau, CE1 seul (pas CM2 pour
  l'instant - un seul niveau a la fois, pour mesurer le cout reel avant d'engager le second)."*
  Ce document est le premier endroit du depot ou cet enonce est ecrit ; au moment ou cette phrase
  a ete redigee, il ne fondait que la proposition ci-dessous, pas un fait acquis - deux bornes que
  `DEC-0062` pose explicitement pour un enonce recu par ce canal, et que je m'appliquais a
  moi-meme plutot que de les relacher au motif que la conclusion me paraissait raisonnable. **Ce
  retour est desormais confirme sous forme 2, voir ci-dessous** : la reserve qu'il portait est
  levee, le texte d'origine est laisse tel quel pour que la chronologie reste lisible.

## Confirmation du 2026-09-21 (le meme jour, apres reponse de l'utilisatrice)

**Forme 2** (`squad/README.md`, section 2 bis) : l'enonce vient directement de l'utilisatrice, ses
mots sont cites entre guillemets, la date est celle de la reponse.

Question posee mot pour mot : *"confirmez-vous explicitement : ouverture du perimetre CE1 seul
(CM2 differe), lot s36 limite a mathematiques CE1 ?"*

Reponse de l'utilisatrice, verbatim, 2026-09-21 : *"oui et tu peux lancer le sprint quand c'est
bon."*

Cette reponse confirme, sous une forme desormais citable directement, exactement les deux points
que le retour recu hors des neuf agents avait avances sans pouvoir les fonder seul : le perimetre
CE1 seul / CM2 differe (section 2) et le lot de s36 borne a mathematiques CE1 (section 4). Elle
autorise en outre l'ouverture du sprint - lu ici comme portant sur le routage a `nadia-prof`
(section 3) et sur l'engagement du lot de s36 tel qu'instruit, pas comme une levee du prealable
que ce meme document pose : aucune story de developpement ne devient engageable avant que le
referentiel mathematiques CE1 n'existe (section 4, `docs/backlog/backlog.md`, section "Sprint
36").

- **Competence programme visee** : transverse - ce document porte sur le perimetre des
  referentiels eux-memes, pas sur le contenu d'une ligne.

---

## 0. Ce que ce document fait, dans l'ordre

1. Constate l'ecart entre ce que la matrice couvre aujourd'hui (deux niveaux, CP et CM1) et le
   perimetre demande (un troisieme niveau, CE1).
2. Pose le perimetre : CE1 seul entre, CM2 differe explicitement - ce n'est pas un oubli, c'est un
   choix nomme.
3. Route l'ecriture du ou des referentiels CE1 a `nadia-prof`, exactement comme `DEC-0071` l'a
   fait pour les trois matieres absentes en 2026-09-02 : je ne fabrique aucune competence CE1 de
   ma main.
4. Instruit le perimetre de s36 sur **une seule matiere, mathematiques**, et justifie la taille de
   ce lot contre la pratique deja observee dans ce depot plutot que contre une intuition.
5. Dit ce que ce document ne tranche pas et ce qui reste a confirmer.

---

## 1. Le fait, verifie sur le depot tel qu'il est aujourd'hui

- `squad/programme/MATRICE-couverture.md` ne porte, en colonne `Niveau`, que deux valeurs : `CP
  (cycle 2)` et `CM1 (cycle 3)`, sur les dix-huit tableaux de competence qu'elle contient
  (mathematiques, francais, questionner-le-monde, histoire-geographie, langues vivantes, arts
  plastiques, education musicale, EPS, EMC, histoire des arts, sciences et technologie).
- `squad/programme/` contient dix dossiers de matiere ; aucun ne porte de fichier
  `competences-ce1.md`. Les dix referentiels existants sont tous soit `competences-cp.md`, soit
  `competences-cm1.md` (histoire-geographie, histoire des arts et sciences et technologie n'ont
  jamais eu de version CP, ces trois enseignements etant proprios du cycle 3 - `MATRICE-
  couverture.md`, notes du 2026-09-02 et du 2026-09-03).
- `contenu-repris/` (61 paquets YAML issus de `main`) ne couvre que CP et CM1 : aucune reserve de
  matiere premiere pour CE1 (`contenu-repris/README.md`).
- Aucun `DEC` existant n'ouvre le niveau CE1 : `DEC-index.md` ne cite CE1 dans aucune ligne.

**Ce que l'architecture ne bloque pas** (constat deja fait avant ce document, je le reprends sans
le rederiver) : `ContentDescriptor` porte le niveau comme `String` (`ADR-0006`), et le catalogue
comme la barre d'en-tete derivent la liste des niveaux affiches du contenu charge. Ajouter CE1 est
une question de donnee, pas de code - `CLAUDE.md`, section stack, le dit du niveau et de la
matiere en general.

---

## 2. Le perimetre : CE1 seul, CM2 differe explicitement

CE1 et CM2 ne sont pas symetriques du point de vue du produit : CE1 est le **second niveau du
cycle 2** (CP, CE1, CE2), CM2 le **dernier niveau du cycle 3** (CM1, CM2). Les deux ouvriraient un
troisieme niveau sur un cycle deja entame par un seul de ses niveaux.

**Ce document n'ouvre que CE1.** CM2 reste ferme jusqu'a nouvel arbitrage, pour le motif cite en
section "Retour qui la fonde" : mesurer le cout reel d'un troisieme niveau sur un seul cycle avant
d'engager le second. Rouvrir CM2 avant que ce cout ne soit mesure reproduirait exactement le
defaut que ce motif cherche a eviter - deux couts inconnus melanges dans la meme mesure.

**Ce que "cout reel" designe ici, pour que la mesure de la section 4 se compare a quelque chose de
concret** : le nombre de sprints et le nombre d'arbitrages de `nadia-prof` necessaires pour faire
passer une premiere ligne CE1 a `couvert-valide`, compare au meme chiffre deja mesure pour
l'ouverture d'une matiere neuve sur un niveau existant (`DEC-0071`, section 6 : un arbitrage de
matiere neuve, seul, a deja pris un document entier a `nadia-prof` sans qu'aucun paquet ne soit
encore ecrit). Rien n'indique aujourd'hui que le cout d'un niveau neuf soit du meme ordre que celui
d'une matiere neuve : c'est precisement ce que ce sprint doit mesurer, pas presumer.

---

## 3. Ce que je route, et a qui - le ou les referentiels CE1

**Je ne fabrique aucune competence CE1.** Ecrire moi-meme un referentiel de competences
reproduirait le defaut que `DEC-0071` a deja nomme pour l'anglais, l'histoire-geographie et
questionner le monde : poser un arbitrage de contenu programme sans passer par `nadia-prof`
d'abord.

- **Un referentiel de competences mathematiques CE1**
  (`squad/programme/mathematiques/competences-ce1.md`), sur le meme modele que les referentiels
  existants (socle realiste, non exhaustif, non officiel, avec la meme reserve de fiabilite que
  `squad/programme/mathematiques/competences-cp.md` porte deja) : route a **`nadia-prof`**.
- **Une ligne par competence, ajoutee a `MATRICE-couverture.md`** une fois ce referentiel ecrit,
  sous une nouvelle section `## Mathematiques - CE1 (cycle 2)` : `camille-po`, des que le
  referentiel existe - jamais avant.
- **Les sept autres referentiels CE1** (francais, langues vivantes, EMC, questionner le monde,
  arts plastiques, education musicale, EPS - le cycle 2 ne porte ni histoire-geographie, ni
  histoire des arts, ni sciences et technologie, qui sont propres au cycle 3) restent **hors
  perimetre de s36**, motif en section 4.

---

## 4. Perimetre de s36 : une seule matiere, et pourquoi une seule

**Ce que la pratique deja ecrite dans ce depot montre, verifie contre `docs/backlog/backlog.md`
et `squad/programme/MATRICE-couverture.md` plutot que suppose :**

- Le tout premier sprint du produit (`DEC-0001`, s0) n'a pas ouvert deux matieres a la fois : il a
  choisi **une seule competence, sur une seule matiere** (les additions dans la limite de 10,
  mathematiques CP), justifiee par sa simplicite d'audit avant tout engagement plus large.
  Mathematiques CM1 n'a ouvert sa premiere ligne qu'a s12 (`DEC-0029`), francais CM1 restait encore
  une dette a s17 (`DEC-0039`) - la fondation de CP et de CM1 n'a jamais ete un geste unique sur
  plusieurs matieres a la fois, meme quand les deux matieres existaient deja pour l'autre niveau.
- A l'inverse, quand `DEC-0071` a instruit trois matieres neuves a la fois pour s27 (anglais,
  histoire-geographie, questionner le monde), sa propre section 6 a mesure que **"aucun point de
  comparaison de ce depot n'a jamais porte trois arbitrages de premiere matiere a la fois"** et
  qu'un seul arbitrage de ce type avait deja pris un document entier a `nadia-prof` a lui seul
  (`DEC-0065`, section 6, sur `COMP-2`). Le sprint qui a suivi (s27, `docs/backlog/backlog.md`,
  section "Sprint 27") a ferme exactement une ligne malgre trois paquets livres, et a explicitement
  ecrit d'avance que "un premier paquet de matiere neuve ouvre une ligne, il ne la ferme pas".

**Ce que j'en tire pour CE1** : une nouvelle competence-programme (matiere x niveau qui n'existe
pas encore dans `squad/programme/`) coute a `nadia-prof` un referentiel entier a ecrire avant
qu'un seul paquet ne soit possible, independamment de la matiere. CE1 mathematiques et CE1
francais ouverts le meme sprint seraient **deux referentiels neufs simultanes**, pas un seul -
exactement la charge que `DEC-0071` a mesuree comme couteuse pour trois matieres a la fois, et
que ce document choisit de ne pas reproduire pour un sprint dont l'objet declare est justement de
mesurer un cout, pas de le masquer en le partageant entre deux fronts.

**Le choix de la matiere : mathematiques, pas francais.** Deux raisons, toutes deux verifiables
dans ce depot :

1. **Precedent direct.** Mathematiques CP est la toute premiere ligne jamais couverte par ce
   produit (`DEC-0001`). Reprendre mathematiques comme pilote du troisieme niveau ferme la boucle
   sur le meme choix qui a ouvert le premier : la matiere dont ce depot a le plus d'experience de
   premiere ouverture.
2. **Mecanique la plus mature.** Les tableaux de mathematiques (CP et CM1) sont servis
   integralement par le bandit manchot sur un champ d'item deja stable (`ADR-0075` et ses
   suites) : le pilote de s36 teste le cout d'un **niveau** neuf sans le confondre avec le cout
   d'une **mecanique** ou d'une **forme d'item** neuve, que `nadia-prof` compte hors de sa borne
   ordinaire (`DEC-0065`, section 6).

**Ce que ce choix n'empeche pas** : rien n'interdit a un sprint ulterieur d'ouvrir francais CE1 en
parallele d'un second paquet mathematiques CE1, une fois le cout du premier referentiel mesure. Ce
document pose le premier pas, pas la sequence complete.

---

## 5. Impact sur `squad/programme/MATRICE-couverture.md`

Le denominateur de la matrice **augmentera** dès que le referentiel mathematiques CE1 existera et
que ses lignes y seront copiees - a `non couvert`, comme toute ligne neuve (`MATRICE-
couverture.md`, section "Comment la lire"). **Aujourd'hui, ce document n'ajoute encore aucune
ligne** : aucun referentiel CE1 n'existe, et en ecrire un moi-meme anticiperait le travail de
`nadia-prof`. La matrice recoit uniquement une note structurelle annoncant l'ouverture du
perimetre et le renvoi vers ce document (voir le commit qui l'accompagne).

Suivant la regle deja posee par `DEC-0071` (section 2, elle-meme reprise du precedent de s19) :
**le prochain recul du taux affiche, quand le referentiel mathematiques CE1 entrera, sera ecrit
d'avance comme un effet de perimetre - ancien denominateur, nouveau denominateur, ancien taux,
nouveau taux - jamais decouvert et lu comme une regression du produit.** Etat de reference a
cette date, pour que la comparaison future soit exacte : 76 lignes `couvert-valide` sur un
denominateur atteignable de 138 (55,1 %), mesure a la cloture de s35 (`squad/programme/MATRICE-
couverture.md`, synthese du sprint 35).

---

## 6. Ce que ce document ne fait pas

- Il ne fixe aucune competence CE1, pour aucune matiere.
- Il n'ouvre pas CM2 : ce niveau reste explicitement differe, pas oublie.
- Il ne cree aucune ligne de matrice : aucune ligne CE1 n'existe tant qu'aucun referentiel n'existe
  pour elle.
- Il ne prononce aucun Done, technique ou pedagogique.
- **Point leve le 2026-09-21** : ce document ne confirmait pas, a lui seul, que l'utilisatrice
  avait bien decide ce qu'il lui attribuait ; cette confirmation etait due (section "Retour qui
  la fonde"). Elle est desormais rendue (section "Confirmation du 2026-09-21"). Si elle infirme ou
  ajuste ce perimetre a l'avenir, ce document est amende ou remplace, jamais silencieusement
  contourne.

---

## 7. Ce que ce document demande, et a qui

| Ce qui est du | Proprietaire | Etat |
|---|---|---|
| Confirmation citable, par l'utilisatrice, du perimetre CE1 seul / CM2 differe | l'utilisatrice | **Rendue le 2026-09-21, section "Confirmation du 2026-09-21"** |
| Referentiel de competences mathematiques CE1 (`squad/programme/mathematiques/competences-ce1.md`) | `nadia-prof` | **Route, section 3 - rendu le 2026-09-21, 17 lignes, voir note ci-dessous pour une ligne manquante trouvee a la relecture** |
| Ligne(s) neuve(s) de `MATRICE-couverture.md` une fois ce referentiel ecrit | `camille-po` | **Faite le 2026-09-21, section "Mathematiques - CE1 (cycle 2)"** |
| Story(ies) de developpement du premier paquet mathematiques CE1 | `camille-po` puis `sofia-sm` | **Non engageable avant le referentiel - voir `docs/backlog/backlog.md`, section "Sprint 36"** |

## Note du 2026-09-21 (complement, apres relecture de `nadia-prof` de son propre referentiel)

En relisant `competences-ce1.md` le jour meme ou elle l'a ecrit, `nadia-prof` signale une ligne
absente plutot que de la corriger elle-meme, exactement la frontiere que ce document pose en
section 3 - elle ne fabrique jamais une competence sans le dire, je ne fabrique jamais une
competence du tout : *"`MAT-CE1-08` enumere 'addition, soustraction et multiplication'. Les
problemes de partage et de groupement - le sens de la division - manquent, et ils appartiennent au
CE1. [...] le document prendrait `MAT-CE1-18` sans renumeroter quoi que ce soit. Je ne l'ajoute pas
dans le meme geste que mon verdict de sprint [...] et je la route a `camille-po` pour qu'elle
decide quand."* (`squad/sprints/s36-ouverture-ce1-mathematiques-seule-cm2-differe/verdicts-nadia.md`,
section 3).

**Decision : ajout immediat, route a `nadia-prof`, pas une dette tracee pour un sprint futur.**
Trois motifs, tous verifiables sur l'etat du depot a cette date plutot que sur une preference :

1. **Le cout de corriger maintenant est au plus bas qu'il ne sera jamais.** Aucun paquet de contenu
   CE1 n'existe (`nadia-prof`, meme document, section 3 : "aucun paquet ne la sert encore") : ajouter
   une dix-huitieme ligne ne rejoue aucun audit, ne rouvre aucune fiche de conformite, ne deplace
   aucun statut de matrice au-dela d'une ligne neuve `non couvert`. Le jour ou un premier paquet
   mathematiques CE1 sera ecrit contre `MAT-CE1-08` tel qu'il est aujourd'hui (addition,
   soustraction, multiplication seules), puis que la ligne de division sera ajoutee ensuite, ce
   paquet devra etre repris ou un second paquet ecrit contre une ligne qui aurait pu exister depuis
   le debut - exactement le cout que ce depot a deja nomme plus cher qu'une correction immediate
   (`squad/README.md`, amendement de la retro de s21, sur `DEC-0076` : "corriger et reauditer dans
   le sprint plutot que trois items faux en production").
2. **Ce n'est pas une extension de perimetre de s36, c'est l'achevement de la tache deja routee.**
   `DEC-0089` (ce document, section 3) route "le referentiel de competences mathematiques CE1" a
   `nadia-prof`, pas "seize ou dix-sept lignes de ce referentiel" : une ligne qu'elle identifie
   elle-meme comme appartenant au CE1, dans la matiere deja engagee ce sprint, complete la meme
   livraison plutot que d'en ouvrir une neuve. Aucune reouverture de `PAR-16` n'est due : le poste
   "referentiel mathematiques CE1" reste le meme poste, avec la meme affectation nominative.
3. **Le referentiel se declare lui-meme non exhaustif** (`squad/programme/mathematiques/
   competences-ce1.md`, en-tete : *"Socle realiste de depart, non exhaustif et non officiel"*) et
   nomme lui-meme la forme de la correction (`MAT-CE1-18`, sans renumeroter les dix-sept lignes
   existantes) : je ne decide donc d'aucune forme technique, seulement du moment, et le moment le
   moins couteux est maintenant.

**Ce que cette decision ne fait pas** : elle n'ajoute aucune ligne de matrice pour `MAT-CE1-18` -
comme pour les dix-sept lignes deja entrees, une ligne de matrice n'existe qu'une fois la ligne de
referentiel ecrite (section 3, "je ne fabrique aucune competence CE1"), jamais avant. Elle ne fixe
aucun contenu de cette dix-huitieme competence : son intitule exact reste entierement a
`nadia-prof`, je ne propose ni "problemes de partage et de groupement" ni "sens de la division"
comme un texte a valider, seulement le fait qu'une ligne sur ce sujet est due. Elle n'engage aucun
developpeur ni aucune story : comme les dix-sept lignes existantes, `MAT-CE1-18` n'est pas
developpable avant qu'un arbitrage de perimetre plus large sur le premier paquet mathematiques CE1
ne soit instruit (`docs/backlog/backlog.md`, section "Sprint 36").
