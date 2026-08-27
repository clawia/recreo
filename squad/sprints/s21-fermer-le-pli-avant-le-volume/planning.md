# [Sprint s21] Planning - fermer le pli avant le volume

- **Horodatage de debut** : 2026-08-27 15:45 +0200.
- **Anime par** : sofia-sm, casquette Scrum Master.
- **Tete de branche a l'ouverture** : `f701f37` (dernier arbitrage de `nadia-prof` du jour), branche
  `round-two`, arbre propre.
- **Repertoire de sprint** : `s21-fermer-le-pli-avant-le-volume`, cree par ce planning.

## Ordre du process respecte

`camille-po` a instruit le perimetre (`DEC-0051`), `ines-design` a mesure et propose deux leviers de
forme, `theo-archi` a rendu son avis de faisabilite en six points, `nadia-prof` a rendu deux
arbitrages le meme jour (le gel `S3-4` puis, sur ma propre demande, le levier de position). Je gele,
affecte et ecris - dans cet ordre, parce que c'est celui dans lequel les avis sont arrives.

## Fichiers deplaces dans ce meme geste

Trois documents ont ete cherches dans `squad/conformite/`, ecrits la faute de repertoire de sprint
existant au moment de leur redaction. **Deux sont trouves, verifie par une recherche sur les
fichiers modifies le 2026-08-27 a la racine de `squad/conformite/` (hors `releves-s21/` et hors les
fiches `FICHE-*.md`, qui vivent en permanence a cet endroit par convention du depot, non affectees
par un sprint) :**

- `squad/conformite/avis-faisabilite-s21.md` -> `avis-faisabilite.md` (ce repertoire).
- `squad/conformite/proposition-ines-rep-8.md` -> `proposition-ines-rep-8.md` (ce repertoire).

**Aucun troisieme fichier de ce type n'existe.** Les autres fichiers touches le 2026-08-27 dans
`squad/conformite/` sont les fiches de conformite (`FICHE-bandit-manchot-additions-cp.md`,
`FICHE-bandit-manchot-reperes-cm1.md`, `FICHE-bandit-manchot-le-bon-son-cp.md`), qui portent
l'histoire transverse de chaque jeu et ne sont jamais deplacees dans un repertoire de sprint (meme
convention que pour tous les sprints precedents, verifie sur s12, s13, s17, s19, s20 : aucune fiche
n'y a jamais ete deplacee). Les releves de `releves-s21/` restent a leur emplacement, comme demande.

## L'objectif tel qu'il m'est arrive, et ce qu'il ne peut pas tenir

L'utilisatrice arbitre le 2026-08-27 (`DEC-0051`) : *"je voudrais qu'un maximum de jeux soient
disponibles apres le prochain sprint"*, puis, mise devant le cout, *"fermer le pli d'abord, puis le
volume"* - moins de jeux livres a s21, contre la garantie qu'aucun jeu neuf ne naisse avec le defaut
que `nadia-prof` vient de nommer sur huit jeux sur dix-sept du catalogue.

**Cette premisse est tombee pendant l'instruction, le jour meme, avant tout planning.** Deux
arbitrages de `nadia-prof` rendus le 2026-08-27, l'un a 14:23 (`749244a`), l'autre a 15:10
(`f701f37`), etablissent, chacun par une mesure et non par une preference :

1. **Aucune valeur de police n'achete quoi que ce soit.** 1,875 rem - le plancher le plus bas qu'elle
   ait jamais accorde, le 2026-08-11, pour une exception deja qualifiee d'extraordinaire - ferme
   **zero** item de plus que la police actuelle sur la population mesuree ce jour (30 items sur 30 le
   restent). Meme 1 rem, la taille d'un texte courant, en laisse encore 4 sous le pli.
2. **Les deux leviers de forme d'`ines-design` (marge de `.score`, rembourrage vertical de
   `.proposition`) ferment 26 items sur 56 - pas les 56.**
3. **Le seul levier qui fermait le reste - epingler le bloc de message au bas de la fenetre - est
   REFUSE**, sur trois motifs mesures en jouant onze parties sur deux jeux : il rend inerte, huit
   revelations sur huit, le bouton que le jeu ordonne de presser, six fois sur huit c'est celui-la
   meme ; il pose le verdict d'une question sur les propositions de la suivante ; il fait varier
   l'aspect du meuble selon la reponse (0 a 146 px de cabinet recouvert), ce que `nadia-prof` garde
   depuis le 2026-08-12 comme le point de garde le plus important du chantier.

**Consequence, ecrite noir sur blanc comme demande : fermer `REP-8` sur tout le catalogue n'est pas
l'objectif de s21, parce que ce n'est atteignable par aucun des trois leviers connus a ce jour** -
ni la police, ni la forme seule, ni la position. Un objectif qui le promettrait echouerait par
construction, et la mesure qui l'etablit date d'avant le sprint, pas d'un refus en cours de route.

**Ce qui reste vrai et qui devient l'objectif** : sur les 56 items ou l'indice passe sous le pli
apres un echec, 26 ferment par la forme (leviers d'`ines-design`, acceptes), 14 autres ne coutent a
l'enfant aucun mot lisible (mesure de `nadia-prof`, section 4 de son arbitrage du matin) et 12 des 16
items veritablement degrades ferment par une reformulation d'enonce sur une seule ligne (48 px
gagnes, motif deja etabli par `nadia-prof` sur `REP-9`). **4 items - deux de
`bandit-manchot-grands-nombres-cm1`, deux de `bandit-manchot-reperes-cm1` - n'ont aujourd'hui aucun
levier connu, ni de forme, ni de contenu, ni de position**, parce que leur libelle long est la
competence meme qu'ils testent. Je ne les mets pas en story : je les nomme, hors perimetre de s21, et
je les route a `camille-po` en fin de ce document.

## Objectif gele

**Fermer, sur le catalogue entier, la part de `REP-8` qui se ferme reellement a s21 - 40 des 56
items degrades par la forme et le contenu - sans promettre les 4 items qui n'ont aucun levier
connu ; solder les cinq dettes trouvees le jour de l'ouverture ; livrer jusqu'a trois paquets neufs
puises dans `contenu-repris/`, sous reserve de l'arbitrage prealable de `nadia-prof` avant tout
code - dans cet ordre, le pli avant le volume, comme l'a arbitre l'utilisatrice.**

## Decisions prises

Quatre decisions m'appartiennent au titre de ce planning, aucune n'entame le role d'un autre agent :

1. **Perimetre de `REP-8` : le catalogue entier, 18 jeux.** `DEC-0051` me laisse fixer 17 ou 18 ; je
   retiens 18, parce que c'est le sens meme de l'arbitrage de l'utilisatrice ("tout le catalogue").
   `bandit-manchot-additions-cp` reste hors de portee de l'outillage standard (paquet combinatoire,
   sans `bonneReponse` lisible), mais `nadia-prof` l'a deja verifie par sa propre methode le
   2026-08-27 dans son balayage `releves-s20/s20-pli-du-catalogue-744x551.json` : 1 item vu, indice
   au-dessus du pli. Les 18 jeux sont donc tous couverts par une mesure, aucun par defaut.
2. **Pas de parallelisation des audits de `nadia-prof` ce sprint.** L'instruction demandait
   d'examiner si des audits sur des paquets distincts, independants, pouvaient tourner en parallele
   pour depasser la borne de deux a trois paquets que `camille-po` chiffre. **Je ne retiens pas cette
   voie a s21**, pour un motif trouve aujourd'hui meme et qui rend le risque concret plutot
   qu'hypothetique : `theo-archi` a mesure que l'outillage de mesure du pli (`balayage-largeur-rangee.py`
   et tout ce qui en derive) se lance sur un port de deverminage **fixe** et s'attache au premier
   navigateur qui y repond, sans jamais verifier qu'il s'agit bien du sien - defaut qui lui a deja
   fait lire, ce jour meme, la partie d'un autre agent pendant trois tentatives, silencieusement.
   Deux audits de `nadia-prof` conduits en parallele sur le meme poste sont exactement le scenario ou
   ce defaut produit un chiffre faux sans le dire - la famille d'ecart muet que ce depot a deja
   sanctionnee plusieurs fois. Le correctif (story `S21-1`) doit d'abord exister et etre confirme
   avant qu'une parallelisation soit seulement envisagee, et je ne l'ecris pas comme un objectif de
   s21 : le gain qu'elle promettrait n'est de toute facon pas un gain de volume de travail humain,
   `nadia-prof` restant seule a jouer chaque item quel que soit le nombre de sessions.
3. **Volume retenu : trois paquets, sous reserve de l'arbitrage prealable.** `cp-syllabes`,
   `cp-mot-manquant`, `cp-comparer` (trie et complete sur la contenance). C'est la borne haute que
   `camille-po` tient sans aleas de refus (`DEC-0051`, section 5) ; je note que `cp-comparer` porte
   un risque plus eleve que les deux autres, parce qu'il exige un tri et un ajout de matiere avant
   audit et n'est donc pas une reprise a l'identique - je le retiens quand meme, en le nommant
   explicitement comme le plus fragile des trois plutot qu'en le laissant deguise en reprise pure.
4. **`outils/mvn-jdk21.sh` (section 6 de l'avis de `theo-archi`) reste hors perimetre de s21.**
   Le defaut est reel (l'enveloppe pose `JAVA_HOME` depuis un fichier de toolchains que Maven ne lit
   pas, et l'affirme sans le verifier), mais sans consequence sur la CI (verifie par `theo-archi` :
   `actions/setup-java` ecrit sa propre toolchain) ni sur le bytecode livre (verifie : version majeure
   65 dans les deux cas). Je le route a `theo-archi` en fin de ce document plutot que de l'affecter :
   la capacite de s21 va au pli et au volume.

## Avis de faisabilite de `theo-archi` - resume des six points

Avis complet : `avis-faisabilite.md` (ce repertoire), commit `f382ebc`, six points demandes par moi
a l'ouverture.

| # | Question posee | Avis |
|---|---|---|
| 1 | Fermer `REP-8` sur tout le catalogue | **Infaisable par la forme ; faisable par une declaration de position, mais suspendu a l'arbitrage de `nadia-prof`.** Cet arbitrage est rendu depuis, REFUSE (`f701f37`) - voir plus haut. |
| 2 | Garder le pli en garde-fou executable | **Non gardable en l'etat** (`ADR-0028`, declencheur - un conteneur de build epingle - non survenu, verifie ce jour). Gardable a la place : la **declaration** du correctif de position si elle existait, et les trois defauts d'outil de mesure (faisable ce sprint, `S21-1`). |
| 3 | Contrat de forme des paquets neufs | **Une regle sur quatre devient un test** : la regle 1 (au plus trois propositions), cliquet de deux paquets, 33 items. Regles 2 et 3 : refusees comme veto par `nadia-prof`, chiffre a l'appui (428 items, deux competences entieres supprimees). Regle 4 : porte de revue, jamais un test. |
| 4 | L'obstacle des 95 sur 296 (devenu 122 sur 348) | **Remesure faite, non fermee.** 12 jeux sur 17, aucun statut de couverture prononce. Recouvre `REP-8` sans lui etre identique. Hors perimetre de s21 (decision 4 ci-dessus a un autre obstacle ; celui-ci reste route a `camille-po` en fin de document). |
| 5 | `ADR-0029` mal cite | **Ni dans un `ADR` ni dans l'index.** L'erreur est dans un test commite, `PaquetCm1ReperesTest`, lignes 73 et 162 - corrigee dans `S21-4`. |
| 6 | La machine virtuelle des tests | **Les tests tournent sur JDK 21.0.10, le bytecode livre est en 21.** Seule la machine virtuelle de Maven lui-meme est un 25.0.2, sans consequence sur la CI ni sur le livre. Hors perimetre de s21 (decision 4). |

## Tableau des stories - affectation nominative

| Story | Competence programme visee | Affectation | Avis de faisabilite (`theo-archi`) | Porte de sortie |
|---|---|---|---|---|
| **`S21-1`** - correctif de l'outillage de mesure du pli (port de deverminage verifie, presence de la regle injectee verifiee a chaque lecture, `viewport` lu du navigateur) | Transverse, outillage de mesure, pas de contenu pedagogique | `theo-archi` | **Faisable ce sprint, en l'etat** : trois juges purs, testables par entrees fabriquees, aucune dependance neuve (avis, point 2). **Prealable** a `S21-8` a `S21-11` (regle 4, porte de rendu) et a toute remesure ulterieure de `REP-8`. | `mvn verify` vert, cas de test qui font rougir chacun des trois defauts sur une entree fabriquee. |
| **`S21-2`** - `REP-8`, leviers de forme (`.score{margin:0.25rem 0}`, `.proposition{padding:0.5rem 1.25rem}`) | Transverse, defaut de catalogue, pas de ligne de referentiel | `marc-dev` | Faisable en l'etat, aucun `ADR` du (regle de style, pas de regle d'architecture). Amendement date de l'acquis 7 de `nadia-prof` (2026-08-11) deja ecrit dans `FICHE-bandit-manchot-reperes-cm1.md`, section 5. | `mvn verify` vert (planchers de cible tactile et de lisibilite inchanges, verifies par `nadia-prof` a 66,0 px de bouton). Remesure par `auditer-paquet.py` sur les 17 jeux : 26 des 56 items "indice sous le pli" repassent au-dessus. |
| **`S21-3`** - `REP-8`, reformulation de 12 enonces sur une seule ligne (`bandit-manchot-reperes-cm1` : 8 items ; `bandit-manchot-conversions-unites-cm1` : 1 item ; `bandit-manchot-accords-groupe-nominal-cm1` : 3 items), sans changer le repere ni la bonne reponse testes | Meme ligne de referentiel que chaque paquet deja couvert - correction, pas extension de couverture | `lea-dev` | Sans objet pour l'architecture (contenu). Cout mesure par `nadia-prof` : 48 px gagnes par enonce ramene a une ligne, largement superieur aux deficits de 16,19 px et 20,19 px mesures. | Reaudit **cible** (les 12 items concernes, pas le paquet entier) par `nadia-prof` : indice entierement lisible sans defiler sur les 12 ; niveau et piege inchanges. |
| **`S21-4`** - correction Javadoc de `PaquetCm1ReperesTest` (lignes 73 et 162) : retirer la citation fautive d'`ADR-0029`, ecrire les trois motifs pedagogiques dates fournis par `nadia-prof` (`FICHE-bandit-manchot-reperes-cm1.md`, complement B) | Transverse, redaction de test, aucune assertion touchee | `lea-dev` (meme lot que `S21-3`) | Deux lignes de Javadoc, aucune assertion modifiee - confirme par `theo-archi` dans son avis, section 5. | `mvn verify` vert (inchange, la Javadoc n'est pas executee), relecture par `relecteur-pedagogique` sur la formulation. |
| **`S21-5`** - `ADR` encodant la regle 1 du contrat de forme (au plus trois propositions par item, chargement par le chargeur reel, cliquet exact de deux paquets et 33 items : `cm1-conjugaison-present`, `cm1-accords-groupe-nominal`) et sa section "Regle executable" motivant pourquoi les regles 2, 3 et 4 ne sont pas encodees | Transverse, regle de contenu, aucune ligne de referentiel | `theo-archi` | Faisable moyennant l'`ADR` - patron deja copiable (avis, section 3.1). Prochain numero disponible ce jour : `ADR-0063` (`ADR-index.md`, verifie avant redaction). | `mvn verify` vert, garde-fou rouge sur un item fabrique a quatre propositions hors des deux paquets exemptes. |
| **`S21-6`** - amendement date (pas reecriture) de la phrase fautive sur l'enveloppe JDK dans les douze `confirmation-archunit.md` deja commites | Transverse, redaction de rapport de sprints clos | `theo-archi` | Chiffre exact (`21.0.10`) inchange dans les douze fichiers ; seule la phrase qui l'explique est fausse (avis, section 6.4). | Douze fichiers amendes et dates, aucun chiffre remplace ; `relecteur-pedagogique` verifie qu'aucune reecriture silencieuse n'a eu lieu. |
| **`S21-7`** - correctif de `squad/outils/ines-design/mesurer-candidats-rep-8.py` (la regle injectee est jetee a la navigation entre deux parties, sans le signaler) | Transverse, outillage de mesure | `ines-design` | Sans objet (outillage, pas d'architecture de production). Les trois releves deja commites sont verifies indemnes (`parties: 1` partout) par `nadia-prof` et par `theo-archi` independamment. | Une execution a plusieurs parties refuse bruyamment ou signale explicitement le melange, au lieu de le taire. |
| **`S21-8`** - arbitrage prealable de `nadia-prof` sur les trois paquets de volume retenus (`cp-syllabes`, `cp-mot-manquant`, `cp-comparer` trie et complete), avant toute ligne de code | Transverse, gouverne les trois competences des stories suivantes | `nadia-prof`, saisie par `sofia-sm` | Sans objet (porte pedagogique). **Sur le chemin critique des trois stories de volume** : aucun developpeur ne commence avant que cet arbitrage existe. | Arbitrage ecrit, avec ses conditions le cas echeant, verse a une fiche de conformite avant tout commit de code sur les trois paquets. |
| **`S21-9`** - volume, `cp-syllabes` (mot -> nombre de syllabes, mode pool deja present dans le fichier repris) | Francais CP, "Denombrer les syllabes orales d'un mot (segmentation syllabique)" - premiere ligne couverte du domaine "Langage oral et conscience phonologique" | `marc-dev` | Transposition directe au schema `bandit-manchot` deja en usage (`schema: 1`, `type: question`), aucune mecanique neuve. Regle 1 du contrat (`S21-5`) et regle 4 (porte de rendu, `S21-1` prealable) s'appliquent. | `mvn verify` vert, porte de rendu (aucun item sous le pli a 744 x 551), puis Done pedagogique de `nadia-prof`. |
| **`S21-10`** - volume, `cp-mot-manquant` (phrase a trou, format quasi identique au schema `bandit-manchot`) | Francais CP, "Utiliser des mots precis pour designer des objets, actions, sensations du quotidien" | `lea-dev` | Transposition directe, memes conditions que `S21-9`. | Identique a `S21-9`. |
| **`S21-11`** - volume, `cp-comparer` (comparaisons long/court, lourd/leger, grand/petit, sans instrument), **apres tri des deux items hors sujet (comparaison de nombres) et ajout de matiere sur la contenance, absente du fichier repris** | Mathematiques CP, "Comparer des longueurs, des masses, des contenances (sans instrument gradue)" | `sofia-sm` (casquette developpeuse) | Pas une reprise a l'identique - risque de cycle de refus plus eleve que `S21-9`/`S21-10`, nomme en decision 3. Memes conditions de porte que `S21-9`. | Identique a `S21-9`, plus verification par `nadia-prof` qu'aucun item de comparaison de nombres ne subsiste et que la contenance est couverte. |

**Capacite.** `S21-1` (`theo-archi`) et `S21-8` (`nadia-prof`) sont sur le chemin critique et
n'attendent personne. `S21-2` (`marc-dev`) et `S21-3`/`S21-4` (`lea-dev`) sont independantes entre
elles (aucun fichier partage) et peuvent avancer des l'ouverture. `S21-5`, `S21-6`, `S21-7` sont
documentaires ou d'outillage, sans dependance de code, et peuvent avancer en parallele de tout le
reste. `S21-9` a `S21-11` n'ouvrent aucun commit de code avant que `S21-8` existe et avant que
`S21-1` soit confirme (la porte de rendu s'appuie sur l'outillage corrige).

## Tableau des obstacles

| Obstacle | Categorie | Destinataire | Etat |
|---|---|---|---|
| `REP-8` non fermable sur tout le catalogue a s21, par aucun des trois leviers connus | Pedagogie | `nadia-prof` | **Clos pour ce planning** : deux arbitrages rendus le 2026-08-27 (`749244a`, `f701f37`). Consequence actee dans l'objectif gele ci-dessus. |
| 4 items sans levier connu (`bandit-manchot-grands-nombres-cm1` x2, `bandit-manchot-reperes-cm1` x2) | Pedagogie, produit | `camille-po` | **Ouvert, explicitement hors perimetre de s21.** Nomme par `nadia-prof` elle-meme comme un fait a porter, pas une story a ecrire. Je ne prononce aucun statut de couverture dessus. |
| 122 items sur 348, 12 jeux sur 17, encore sous le pli au troisieme echec (obstacle distinct de `REP-8`, meme famille de cause) | Pedagogie | `camille-po`, `nadia-prof` | **Ouvert, hors perimetre de s21.** Remesure faite par `theo-archi` le 2026-08-27 ; aucun statut de couverture prononce par personne a ce jour. Non instruit en story ce sprint, faute de capacite. |
| Javadoc de `PaquetCm1ReperesTest` citant `ADR-0029` a tort pour justifier "trois propositions, jamais quatre" | Technique, redaction | `lea-dev` | **Ouvert, en scope (`S21-4`).** |
| Douze `confirmation-archunit.md` portent une phrase fausse sur l'enveloppe JDK (chiffre `21.0.10` exact, methode fausse) | Technique, redaction | `theo-archi` | **Ouvert, en scope (`S21-6`).** |
| `squad/outils/ines-design/mesurer-candidats-rep-8.py` perd la regle injectee des la deuxieme partie sans le signaler | Qualite, outillage | `ines-design` | **Ouvert, en scope (`S21-7`).** Les releves deja commites sont verifies indemnes. |
| `squad/outils/nadia-prof/balayage-largeur-rangee.py` (et tout ce qui en derive) s'attache au premier navigateur qui repond sur un port fixe, a deja fait lire une partie etrangere trois fois le 2026-08-27 | Qualite, outillage | `theo-archi` | **Ouvert, en scope et prealable (`S21-1`).** |
| `outils/mvn-jdk21.sh` pose `JAVA_HOME` depuis un fichier de toolchains different de celui que Maven lit, et l'affirme sans verifier | Technique | `theo-archi` | **Ouvert, route mais hors perimetre de s21** (decision 4) - sans consequence sur la CI ni sur le bytecode livre, verifie par `theo-archi` le jour meme. |
| Parallelisation possible des audits de `nadia-prof` pour depasser la borne de deux a trois paquets | Capacite | `sofia-sm` | **Clos par decision 2** : non retenue a s21, motif le defaut de port ci-dessus. |
| Trois fichiers potentiellement ecrits dans `squad/conformite/` faute de repertoire de sprint | Capacite, gouvernance | `sofia-sm` | **Clos** : deux trouves et deplaces dans ce meme geste, aucun troisieme apres verification. |
| Regles 2 et 3 du contrat de forme (libelle a 10 caracteres, enonce sur une ligne) tentantes a coder comme garde-fous | Produit, pedagogie | deja tranche | **Clos avant ce planning** : refusees comme veto par `nadia-prof` et par `theo-archi`, chiffre a l'appui (35 et 80 items sur 428, deux competences supprimees). Elles restent des conseils, ecrits dans `docs/backlog/backlog.md` et dans `FICHE-bandit-manchot-reperes-cm1.md`, jamais en critere bloquant. |

**Amendement du 2026-08-27, en cours de sprint (fusion de la premiere vague de lots).** Trois lignes
ajoutees ci-dessous pour trois obstacles surgis pendant la livraison, aucun connu a l'ouverture. Le
detail de la verification et la methode vivent dans `fusion-lots-1-a-3.md` (ce repertoire) ; ce
tableau n'en porte que l'etat.

| `s21-marc` (`S21-2`) livre et vert (694 tests, confirme), 26 sur 56 items fermes (confirme), mais `.proposition` (role action, `LActionNeDeclarePasMoinsQueLaSortieTest`) declare 0.5rem de rembourrage vertical contre 0.6rem pour `.lien-accueil` (role sortie) - l'action declare moins que la sortie, la regle qu'encode `ADR-0017` | Pedagogie | `nadia-prof` | **Ouvert, arbitrage en cours au moment de cette fusion.** Je ne fusionne pas `s21-marc` avant son verdict ecrit. Si le verdict change la valeur, `marc-dev` reprend son lot, pas moi. |
| Le garde-fou `LActionNeDeclarePasMoinsQueLaSortieTest` (`ADR-0017`) reste vert sur ce meme ecart : `debutDeDeclaration` (verifie, `TailleDesTextesEtBoutonsTest.java` ligne 241) lit la premiere occurrence de `padding:` dans le corps concatene par `blocsMaison`, jamais la derniere - c'est donc le bloc partage a `0.75rem 1.25rem` (ligne 667) qu'il lit, jamais la declaration `.proposition` de `s21-marc` (ligne 706 sur sa branche) qui gagne pourtant la cascade par ordre de source, a specificite egale (`ADR-0014` point 5) | Technique, garde-fou | `theo-archi` | **Ouvert. Ma decision de perimetre : entre dans s21**, comme prealable au deblocage de `s21-marc`, pas comme une story de volume nouvelle - un garde-fou qui ne voit pas la valeur qui gagne ne peut verifier aucun verdict, quel qu'il soit, que `nadia-prof` rendra sur la ligne ci-dessus. La correction porte sur la lecture (dernier `padding` effectif de la cascade, pas le premier trouve), jamais sur la valeur, qui reste hors de mon role. |
| `S21-3` non livree : `lea-dev` a verifie sans editer aucun fichier de contenu que les 12 enonces vises tiennent deja sur une ligne (`lignes=1, hauteur=48` px, hors des deux items exclus) et que le repli a deux rangees, force par `:has(.proposition:nth-child(4))`, est independant de la longueur du texte - le deficit residuel viendrait de la largeur des libelles de proposition, pas des enonces | Pedagogie | `nadia-prof` | **Ouvert, arbitrage en cours au moment de cette fusion.** Je ne reecris pas la story avant son verdict. `lea-dev` est disponible pour reprendre des que l'arbitrage existe. |

## Ce que je ne fais pas

- Je n'ecris ni `ADR`, ni `DEC`, ni fiche de conformite : ce sont les gestes de `theo-archi`, de
  `camille-po` et de `nadia-prof`, rendus ou a rendre dans leurs propres lots.
- Je ne prononce aucun statut de couverture sur les 4 items sans levier ni sur les 122 items de
  l'obstacle distinct : aucun des deux n'a de Done pedagogique a ce jour.
- Je n'implemente rien dans ce document : les lots viennent apres, chacun affecte nominativement
  ci-dessus.
- Je ne rouvre aucun verdict deja rendu (le gel `S3-4`, le refus du levier de position, le refus des
  regles 2 et 3) : je les cite et j'en tire les consequences de planning.
