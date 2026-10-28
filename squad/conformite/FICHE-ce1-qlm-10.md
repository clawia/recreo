# Fiche de conformite - ce1-qlm-10

- **Mecanique** : `bandit-manchot`, `type: question`, sans texte support, comme les paquets d'EMC CE1 ; aucune modification de
  la mecanique, aucune illustration (`docs/backlog/backlog.md`, annotation du 2026-10-25, cinquieme, *"Ce qui est commun aux
  neuf stories"*).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-qlm-paysages.yaml` (13 items, lus par
  `grep -c "bonneReponse:"`), ecrit par `marc-dev` (instance neuve, `S57-BR-QLM-CE1`).
- **Entree de catalogue** : aucune. `QC3` : un paquet n'entre au catalogue qu'apres un Done pedagogique ou un refus leve ;
  `grep -c "ce1-qlm" adapters/adapter-content/src/main/resources/catalogue.yaml` rend 0 le 2026-10-27.
- **Ligne declaree** : `QLM-CE1-10`. **Story** : `S57-BR-QLM-CE1-10` (`docs/backlog/backlog.md`). **Decision** : `DEC-0120` A.227, A.250 a A.255,
  A.268 a A.280.
- **Competence programme visee** : `squad/programme/questionner-le-monde/competences-ce1.md`, `QLM-CE1-10`, *"Identifier quelques paysages (littoral, montagne, campagne, ville) a partir de leurs elements caracteristiques."*
- **Conditions opposables** : celles de la story `S57-BR-QLM-CE1-10` et les reperes `QC1` a `QC9` du bloc commun, **non recopiees ici** :
  la story fait foi, ses mots sont ceux de `nadia-prof`. Plafond de la ligne : `couvert-valide`.
- **Statut global** : **en attente d'audit a l'ecran**. Fiche ouverte par `camille-po` le 2026-10-27 (`DEC-0120` A.280, reponse
  de l'utilisatrice a `Q2` : nom `FICHE-ce1-qlm-<lot>.md`). Aucun verdict. La ligne `QLM-CE1-10` reste `non couvert` dans la
  matrice, qui ne bouge qu'au verdict de `nadia-prof`. **[complete le 2026-10-27 par `nadia-prof` : verdict VALIDE SOUS RESERVE, voir le bloc de validation ; texte d'origine conserve]**
- **Ligne de matrice, etat du 2026-10-28** : `QLM-CE1-10` reste `non couvert`. Motifs lus au depot : (1) `QC3`, aucune entree de catalogue (`grep -c "ce1-qlm" adapters/adapter-content/src/main/resources/catalogue.yaml` rend 0), donc aucun jeu a nommer dans la cellule "Jeu(x) couvrant" ; (2) Done technique complet, les quatre jobs de `.github/workflows/ci.yml` (`QC8`, A.280 point 2), dont seul `build` a ete lance. Aucun des deux n'est une decision de `nadia-prof`. La ligne bouge quand l'entree est fusionnee et que la CI complete est verte (`S57-QLM-CE1-ENTREES-CATALOGUE`) ; fusion du paquet dans `round-two` : `52eb9c47`.
- **Stories du backlog pour ce lot** (`docs/backlog/backlog.md`, annotation du 2026-10-28, douzieme, `DEC-0120` A.283) : `S57-QLM-CE1-10-ORDRE-DES-MOTS`. Aucune n'est engagee.
- **Derniere mise a jour** : 2026-10-27 ; 2026-10-28 (`camille-po`, `DEC-0120` A.283 : statut, ligne `verify`, etat de la ligne de matrice, stories).

---

## Competence visee

| Matiere | Niveau | Competence | Reference au referentiel |
|---|---|---|---|
| questionner le monde | CE1 (cycle 2) | Identifier quelques paysages (littoral, montagne, campagne, ville) a partir de leurs elements caracteristiques. | `squad/programme/questionner-le-monde/competences-ce1.md`, `QLM-CE1-10` |

## Criteres attendus - camille-po

Les criteres d'acceptation de cette fiche sont ceux de la story `S57-BR-QLM-CE1-10` (`docs/backlog/backlog.md`, titre *"Identifier un paysage par ses elements"*) et
les reperes `QC1` a `QC9` du bloc commun. Je ne les recopie pas : une copie derive de la source. Je n'ecris aucune mesure ici.

- [ ] Criteres de la story `S57-BR-QLM-CE1-10`, numerotes dans le backlog.
- [ ] `QC1` a `QC9` du bloc commun (paquet, ligne declaree, branchement, test du paquet, densite, conditions negatives,
  lecture avant `verify`, Done technique, ce que `nadia-prof` garde seule).
- [ ] Texte servi a l'enfant integralement accentue, orthographe et ponctuation irreprochables (`DEC-0002`).

## Parcours du lot, faits lus au depot

Source : `planning.md` du sprint s57, amendement du 2026-10-27 15:40, section 1 ; chaque SHA est un commit de `round-two`.

| Geste | Auteur | Commit | Fichier |
|---|---|---|---|
| premier jet | `marc-dev` | `7cc77996` | `ce1-qlm-paysages.yaml` |
| lecture du premier jet | `nadia-prof` | `b11e4dfa` | `lecture-nadia-premier-jet-ce1-qlm-paysages.md` |
| corrections | `marc-dev` | `beb1baf5` | `ce1-qlm-paysages.yaml` |
| relecture des corrections (seconde lecture) | `nadia-prof` | `8271c0fb` | `relecture-nadia-corrections-ce1-qlm-paysages.md` |

Les fichiers de `nadia-prof` sont dans `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/`. Cette fiche n'en reprend aucun
verdict : la lecture et la relecture sont des gestes avant `verify`, pas le Done pedagogique.

## Etat de la porte technique

**Ce qu'un fichier du depot prouve, et rien de plus.**

- **Aucun `mvn verify` complet n'a ete lance pour ce lot** (`planning.md`, amendement du 2026-10-27 15:40, section 1 : fait donne
  par l'agent lanceur, non verifie ; les relectures ecrivent *"aucun autre job de `ci.yml` lance"*).
- **Regle en vigueur** (`DEC-0120` A.280, reponse de l'utilisatrice a `Q1`, option `O1`) : un `mvn verify` complet **avant** l'audit
  a l'ecran de `nadia-prof` de ce lot ; passage CI complet (`prefixes`, `chemins`, `outils`, `build`) a la cloture du palier ; toute
  annonce de porte nomme les jobs lances.
- Premier `verify` complet du lot : **fait au niveau du reacteur, pas par lot** (mis a jour le 2026-10-28 par `camille-po`, `DEC-0120` A.283 ; texte d'origine *"a faire"* perime, conserve ici). `bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet, sur `90c421a7` : `BUILD SUCCESS`, 3618 tests unitaires et 6 d'integration, 0 echec, mesure par `sofia-sm` (`planning.md`, amendement du 2026-10-27 21:34, section 3), **job `build` seul**. `prefixes` et `chemins` n'ont ete lances qu'en commande de garde-fou, forme CI, `outils` n'a pas ete lance : la porte technique complete n'est pas annoncee (A.280 point 2). Sur `1a810b79`, base de cette mise a jour : un `verify` complet vert (3 624 tests, `build` seul) est **annonce par l'agent lanceur et n'est ecrit dans aucun fichier du depot que j'aie lu** ; `git diff --name-only 90c421a7 1a810b79` ne liste que des fichiers `.md`. Que ce `verify` unique tienne lieu de `verify` du lot n'est ecrit nulle part ; `nadia-prof` l'a lu ainsi dans son verdict.

## Ce dont `nadia-prof` reste la seule garde sur cette ligne

Ce qu'aucun test ne tient (`QC9`, `arbitrage-nadia-palier-5-qlm-9-lots.md`, section 6) : l'exactitude de chaque fait contre la source
ecrite en commentaire du paquet, `NRC-CE1-1` sur le sujet de l'item, `CYC-1` contre les paquets voisins, `TRV-CE1-6`, l'operation
mentale de `FER-3`, et les conditions propres a la ligne que la story nomme.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Verdict du 2026-10-27 (`nadia-prof`, s57, palier 5, passe `P2`, rang 8 sur 9) : **VALIDE SOUS RESERVE**

**Ce qui a ete joue, et comment.** Contenu de `round-two` a `d6f2e007` : `git diff --stat 90c421a7 d6f2e007` hors fichiers
`.md` ne rend rien, et le dernier commit du paquet est `beb1baf5`. Jar fourni par l'agent lanceur, que je n'ai pas construit ;
j'ai controle qu'il porte ce contenu : `contenu/ce1-qlm-paysages.yaml` et `catalogue.yaml` lus dans le jar ont le meme SHA-256
que dans le worktree (`dca4fbc6...` et `c68250ba...`). **Le paquet n'a pas d'entree au catalogue (`QC3`), le jar ne le sert
donc pas.** Je l'ai servi en lancant le jar decompresse, hors du depot, avec devant lui sur le chemin de classes une copie de
`catalogue.yaml` qui ajoute une seule entree locale (`bandit-manchot`, `ce1-qlm-paysages`, 10 manches, titre et consigne de
remplissage). Rien de cette copie n'est commite. JDK 21.0.10 de la toolchain (*"Starting RecreoApplication using Java
21.0.10"*), port 18110 (`--server.port`), *"Started RecreoApplication"*, PID lu par `netstat`, arrete par ce PID.

Joue par moi, requetes HTTP avec l'en-tete htmx, fragments lus apres retrait des commentaires de gabarit :

- 8 parties de 10 manches, en me trompant d'abord une fois, deux fois, ou pas du tout. **Les 13 items ont ete servis**
  (entre 4 et 8 fois chacun), aucun enonce inconnu du paquet ;
- 4 parties ou je joue comme un enfant qui suit une regle de surface sans lire, au premier essai (point 1 et reserve) ;
- 40 premieres manches de plus, pour lire les caracteres du rendu.

**Non fait** : aucun releve d'ecran ni navigateur (pli, largeurs : `ines-design`) ; je n'ai pas joue le parcours par le menu
CE1, puisque le paquet n'y est pas.

**1. Les items B a l'ecran, et la structure de l'intrus** (point ouvert `P4` de `planning.md`, *"pendant pour la tache B de
`-10`"*). Mon critere est celui de ma lecture de `-02`, section 4 : l'enfant ecarte-t-il les deux distracteurs avant d'avoir
lu le paysage ? J'ai joue les cinq B en cherchant l'intrus sans lire le paysage. Par paysage, chaque item garde deux paires
possibles : le distracteur partage va avec chacun des deux autres. J'ai donc joue la regle qu'un enfant applique vraiment,
une chose construite parmi deux choses de la nature, ou l'inverse :

- **3 bonnes reponses sur 13 manches B au premier essai**, moins que le hasard sur trois propositions ;
- la regle tombe juste sur l'item 5 seul, ou *les pistes de ski* est aussi la reponse la plus longue (`D4`, accepte a la
  lecture) ;
- elle tombe a faux sur l'item 8 (*le blé*), l'item 10 (*les immeubles*) et l'item 12 (*la rivière*) ;
- elle ne designe rien sur l'item 2, ou *le sommet*, *la forêt* et *la mer* sont trois choses de la nature.

**Le defaut de `-02` n'apparait pas a l'ecran sur `-10`** : sans lire le paysage, l'enfant se trompe. Le distracteur partage
(`R10-3`, `N10-5`) fait ce pour quoi je l'avais demande. Je ne pose aucune reserve sur les B. Ce constat porte sur `-10` seul ;
il ne ferme pas `P4` pour `-02`, qui a son propre audit.

**2. Ce qui tient, verifie a l'ecran** (a ne pas defaire) :

- **Competence `QLM-CE1-10`, et elle seule.** En A, on reconnait un paysage par ses elements. En B, on cherche l'element qui
  manque a un paysage nomme. Aucun item ne demande *pourquoi* ni ne fait reciter une definition. `FER-3` : les deux taches
  sont deux operations.
- **Exactitude.** Les 13 enonces et les 39 propositions servis sont ceux du paquet. Je les ai relus contre `R10-1` et la table
  de `R10-3` : aucune erreur. Une seule reponse defendable dans chaque item ; *le littoral* et *la ville* ne sont jamais
  proposes ensemble.
- **Traitement de l'erreur.** Apres une erreur, l'ecran dit *"Ce n'est pas ça. Essaie encore !"*, garde le bouton essaye a sa
  place avec sa marque, et montre l'indice. Les 13 indices sont distincts. Chacun est une methode, et aucun ne nomme une
  proposition. Sur le littoral, *"Sur le littoral, il y a par exemple la mer."* explique le mot sans le definir. Apres deux
  erreurs, il ne reste qu'un bouton, et l'enfant avance.
- **Lisibilite.** Enonces de 6 a 14 mots, en deux phrases courtes au plus ; indices de 8 a 12 mots (recompte par script sur le
  paquet). Vocabulaire du CE1. *Littoral* n'est explique que dans l'item 5 : l'enfant peut le rencontrer d'abord en
  proposition (vu a l'ecran dans une partie ou l'item 7 precede l'item 5). C'est `D8`, accepte a ma lecture ; je ne le rouvre
  pas.
- **Accents et typographie a l'ecran.** Tous les caracteres sont justes (*rivière*, *forêt*, *blé*, *métro*, *Écarte*, *où*,
  *énoncé*). L'espace avant *?* et avant *:* est insecable (`U+00A0`) dans le fragment. Le controle d'orthographe reste au
  `relecteur-pedagogique`.
- **Biais et ton.** Aucun *tu*, aucun prenom, aucun lieu reel, aucun jugement sur un paysage, aucune question sur ou vit
  l'enfant. Le *on* est generique partout (`TRV-CE1-6`, `PAY-CE1-2`).
- **Repetition.** *"Lequel ne voit-on pas à la montagne ?"* (items 2 et 12) revient deux fois dans 4 parties sur 8, avec
  d'autres propositions. C'est un fait deja ecrit a ma lecture, section 2 ; il n'est pas rouvert.

**Reserve, nouvelle et datee du 2026-10-27, non bloquante, trouvee en jouant.** Dans les huit items A, **le premier nom de
l'enonce est toujours l'element propre du paysage attendu** : *sommet*, *plage*, *champ*, *immeubles* (deux fois), *mer*,
*pistes de ski*, *blé*. L'element partage vient toujours apres. Joue : en ne lisant que le premier nom, **27 bonnes reponses
sur 27 manches A, du premier coup**. L'enfant mobilise encore la competence, puisqu'il doit savoir ou l'on voit un sommet ou
une plage : ce n'est pas un motif de refus. Mais le piege du distracteur de famille a (`N10-7`) ne prend jamais, la methode des
indices (*"regarde si on y voit tous les mots"*) ne sert jamais, et la place du mot devient un signe. **Ce qu'il faut** : dans
au moins trois des huit items A, l'element partage est nomme en premier (par exemple *"On voit une rivière et un champ."*). Les
elements, les bonnes reponses, les distracteurs et les indices ne changent pas. **Symptome sans garde, a `theo-archi`** : aucune
garde ne lit l'ordre des elements dans l'enonce ; je ne demande pas qu'il en ecrive une.

**Ce que ce Done ne couvre pas.** Le titre, la consigne et le nombre de manches de la future entree de catalogue : ils
n'existent pas, et ceux que j'ai joues sont les miens, de remplissage. Le texte que l'entree servira a l'enfant passe devant
moi avant que l'entree soit fusionnee (`QC3`). Ce Done ne couvre pas non plus l'ecran de fin de partie ni les messages communs
a toutes les parties : ils sont la mecanique, pas ce lot.

**Porte technique, lue et non prononcee par moi.** `planning.md` (amendement du 2026-10-27 21:34, section 3) : un
`mvn -B clean verify` complet vert a `90c421a7`, lance par `sofia-sm`. Seul le job `build` a ete lance, et seulement son etape
`verify` ; la porte complete n'est pas annoncee. Depuis, seuls des fichiers `.md` ont change. Le jeu n'a pas plante en 12
parties. **La ligne `QLM-CE1-10` ne passe a `couvert-valide` qu'avec le Done technique acquis**, CI complete a la cloture
(`DEC-0120` A.280, `O1`).

**Ce dont je reste la seule garde sur cette ligne** (aucun test ne le tient) : l'exactitude de chaque element et de chaque
scene ; qu'un indice soit une methode qui discrimine ; la lisibilite au CE1 ; `NRC-CE1-1` et `CYC-1` contre les voisins ;
`TRV-CE1-6` ; l'operation de `FER-3` ; la structure de l'intrus en B ; **et, depuis cet audit, la place de l'element propre
dans l'enonce de A**.

**Duree de l'audit, a l'horloge** (`date`) : debut 21:52:14, fin 22:21:53, soit 29 min 39 s, lecture de la procedure, jeu,
ecriture et verifications avant commit comprises. Donnee brute, pour la remesure de `DEC-0120` A.276 ; je ne la convertis pas
en bareme.

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-27.
