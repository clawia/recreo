# Fiche de conformite - ce1-qlm-08

- **Mecanique** : `bandit-manchot`, `type: question`, sans texte support, comme les paquets d'EMC CE1 ; aucune modification de
  la mecanique, aucune illustration (`docs/backlog/backlog.md`, annotation du 2026-10-25, cinquieme, *"Ce qui est commun aux
  neuf stories"*).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-qlm-dangers-electricite.yaml` (12 items, lus par
  `grep -c "bonneReponse:"`), ecrit par `marc-dev` (instance neuve, `S57-BR-QLM-CE1`).
- **Entree de catalogue** : aucune. `QC3` : un paquet n'entre au catalogue qu'apres un Done pedagogique ou un refus leve ;
  `grep -c "ce1-qlm" adapters/adapter-content/src/main/resources/catalogue.yaml` rend 0 le 2026-10-27.
- **Ligne declaree** : `QLM-CE1-08`. **Story** : `S57-BR-QLM-CE1-08` (`docs/backlog/backlog.md`). **Decision** : `DEC-0120` A.227, A.250 a A.255,
  A.268 a A.280.
- **Competence programme visee** : `squad/programme/questionner-le-monde/competences-ce1.md`, `QLM-CE1-08`, *"Connaitre les dangers de l'electricite de la maison (prise, appareil pres de l'eau, fil abime) et les gestes qui les evitent."*
- **Conditions opposables** : celles de la story `S57-BR-QLM-CE1-08` et les reperes `QC1` a `QC9` du bloc commun, **non recopiees ici** :
  la story fait foi, ses mots sont ceux de `nadia-prof`. Plafond de la ligne : `couvert-valide`.
- **Statut global** : **en attente d'audit a l'ecran**. Fiche ouverte par `camille-po` le 2026-10-27 (`DEC-0120` A.280, reponse
  de l'utilisatrice a `Q2` : nom `FICHE-ce1-qlm-<lot>.md`). Aucun verdict. La ligne `QLM-CE1-08` reste `non couvert` dans la
  matrice, qui ne bouge qu'au verdict de `nadia-prof`.
  **VALIDE SOUS RESERVE** le 2026-10-27 par `nadia-prof` (s57, palier 5, `P2`, rang 7 sur 9), sur `round-two` a `d6f2e007` :
  une reserve, `DEL-CE1-R1`. Voir le bloc de validation.
- **Derniere mise a jour** : 2026-10-27 (ouverture, `camille-po` ; verdict, `nadia-prof`).

---

## Competence visee

| Matiere | Niveau | Competence | Reference au referentiel |
|---|---|---|---|
| questionner le monde | CE1 (cycle 2) | Connaitre les dangers de l'electricite de la maison (prise, appareil pres de l'eau, fil abime) et les gestes qui les evitent. | `squad/programme/questionner-le-monde/competences-ce1.md`, `QLM-CE1-08` |

## Criteres attendus - camille-po

Les criteres d'acceptation de cette fiche sont ceux de la story `S57-BR-QLM-CE1-08` (`docs/backlog/backlog.md`, titre *"Connaitre les dangers de l'electricite de la maison et les gestes qui les evitent"*) et
les reperes `QC1` a `QC9` du bloc commun. Je ne les recopie pas : une copie derive de la source. Je n'ecris aucune mesure ici.

- [ ] Criteres de la story `S57-BR-QLM-CE1-08`, numerotes dans le backlog.
- [ ] `QC1` a `QC9` du bloc commun (paquet, ligne declaree, branchement, test du paquet, densite, conditions negatives,
  lecture avant `verify`, Done technique, ce que `nadia-prof` garde seule).
- [ ] Texte servi a l'enfant integralement accentue, orthographe et ponctuation irreprochables (`DEC-0002`).

## Parcours du lot, faits lus au depot

Source : `planning.md` du sprint s57, amendement du 2026-10-27 15:40, section 1 ; chaque SHA est un commit de `round-two`.

| Geste | Auteur | Commit | Fichier |
|---|---|---|---|
| premier jet | `marc-dev` | `c8609344` | `ce1-qlm-dangers-electricite.yaml` |
| lecture du premier jet | `nadia-prof` | `4dfcda36` | `lecture-nadia-premier-jet-ce1-qlm-dangers-electricite.md` |
| corrections | `marc-dev` | `fd24ca67` | `ce1-qlm-dangers-electricite.yaml` |
| relecture des corrections (seconde lecture) | `nadia-prof` | `6eef6852` | `relecture-nadia-corrections-ce1-qlm-dangers-electricite.md` |

Les fichiers de `nadia-prof` sont dans `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/`. Cette fiche n'en reprend aucun
verdict : la lecture et la relecture sont des gestes avant `verify`, pas le Done pedagogique.

## Etat de la porte technique

**Ce qu'un fichier du depot prouve, et rien de plus.**

- **Aucun `mvn verify` complet n'a ete lance pour ce lot** (`planning.md`, amendement du 2026-10-27 15:40, section 1 : fait donne
  par l'agent lanceur, non verifie ; les relectures ecrivent *"aucun autre job de `ci.yml` lance"*).
- **Regle en vigueur** (`DEC-0120` A.280, reponse de l'utilisatrice a `Q1`, option `O1`) : un `mvn verify` complet **avant** l'audit
  a l'ecran de `nadia-prof` de ce lot ; passage CI complet (`prefixes`, `chemins`, `outils`, `build`) a la cloture du palier ; toute
  annonce de porte nomme les jobs lances.
- Premier `verify` complet du lot : **a faire**, resultat a inscrire ici par qui le lance, avec les jobs nommes.

## Ce dont `nadia-prof` reste la seule garde sur cette ligne

Ce qu'aucun test ne tient (`QC9`, `arbitrage-nadia-palier-5-qlm-9-lots.md`, section 6) : l'exactitude de chaque fait contre la source
ecrite en commentaire du paquet, `NRC-CE1-1` sur le sujet de l'item, `CYC-1` contre les paquets voisins, `TRV-CE1-6`, l'operation
mentale de `FER-3`, et les conditions propres a la ligne que la story nomme.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Verdict du 2026-10-27 (`nadia-prof`, s57, palier 5, `P2`, rang 7 sur 9) : **VALIDE SOUS RESERVE**

**Done pedagogique prononce.** Une reserve, `DEL-CE1-R1` (section 5). `camille-po` peut faire l'entree de catalogue (`QC3`,
etape distincte, que je n'ai pas faite) puis passer `QLM-CE1-08` a `couvert-valide`, sans mention de couverture partielle, et
faire une story de la reserve.

#### 1. Ce que j'ai fait

**Porte technique, ce que je sais et d'ou.** Le dernier commit qui change le paquet est `3166dd40` (C3 et C4), ancetre de
`90c421a7` (`git merge-base --is-ancestor`, sortie 0). `sofia-sm` a lance `bash outils/mvn-jdk21.sh -B clean verify`, reacteur
complet, sur `90c421a7` : `BUILD SUCCESS`, 3618 tests unitaires et 6 d'integration, 0 echec (`planning.md`, amendement du
2026-10-27 21:34, section 3). C'est le `verify` par lot qu'exige `DEC-0120` A.280 point 2 ; il ne couvre que le job `build`.
De `90c421a7` a `d6f2e007`, seuls `planning.md` et un fichier de lecture changent (`git diff --stat`). Je n'ai lance aucun
`verify`. Les jobs `prefixes`, `chemins` et `outils` sont a la CI de cloture du palier.

**Audit joue, rendu non vu au navigateur.** J'ai envoye de vrais appels HTTP au jar servi par l'agent lanceur et j'ai lu le HTML
et les fragments htmx renvoyes. Je n'ai vu ni la mise en page, ni les retours a la ligne, ni les couleurs.
- **Le jar** : son paquet `ce1-qlm-dangers-electricite.yaml` et son `catalogue.yaml` sont identiques octet par octet a ceux de
  `d6f2e007` (comparaison par script, hors du depot). Le catalogue ne porte aucune entree `ce1-qlm`.
- **Catalogue d'audit, hors du depot** : dans une copie du jar, j'ai ajoute une ressource `catalogue.yaml` qui reprend le
  catalogue courant plus une entree : id `bandit-manchot-dangers-electricite-ce1`, titre *"Les dangers de l'électricité"*,
  consigne *"Lis bien, puis choisis la bonne réponse."* (celle des paquets d'EMC CE1), `bandit-manchot`, 10 manches. Meme
  procede que `FICHE-bandit-manchot-nature-mot-cp.md`, section 0. Aucun fichier du depot n'a ete modifie pour jouer.
- **Lancement** : JDK 21.0.10, celui de la toolchain, `java -jar <copie> --server.port=18108` (le port n'est fixe par aucun
  fichier de configuration du depot : propriete standard de Spring Boot, verifiee dans le journal de demarrage, *"Tomcat started
  on port 18108"*). Serveur arrete par son PID, port libre ensuite.
- **Parcours** : accueil, puis `CE1`, puis *Questionner le monde* : la tuile *"Les dangers de l'électricité"* est seule dans la
  matiere. **Huit parties completes de dix manches, 80 manches** : deux parties sans erreur, deux avec une erreur par manche,
  une avec deux erreurs par manche, une ou j'appuie sur les boutons dans l'ordre affiche, deux mixtes. Une partie d'essai en
  plus : trois appuis faux sur le meme item, pour voir la revelation.
- **Les 12 items sont servis** sur les huit parties (4 a 8 fois chacun), dix items distincts par partie. Ordre des items et
  ordre des propositions changent d'une partie a l'autre. Rang de la bonne reponse sur les 80 manches : 25, 34 et 21 aux trois
  places.

**Ce qui se passe apres une erreur** (vu a l'ecran) :
- **Premiere erreur** : *"Ce n'est pas ça. Essaie encore !"*, l'indice de l'item, et le bouton deja essaye marque.
- **Deuxieme erreur** : meme message, meme indice. Il ne reste alors qu'un bouton non essaye, la bonne reponse.
- **Troisieme erreur**, vue une fois sur Tom en rappuyant sur un bouton deja essaye : *"Ce n'est pas ça."*, *"Appuie sur la
  bonne réponse : ne pas toucher le fil"*, et l'indice.
- Aucune revelation avant la troisieme erreur. Un enfant bloque avance toujours.
- **Ecran de fin** : *"Tu as trouvé, tout seul : 10"* (0 ou 3 selon la conduite), *"Tu as joué toutes les questions : 10"*,
  *"C'est fini !"* et le bouton *Les jeux*.

**Texte servi.** Les douze enonces, 36 propositions et douze indices servis sont ceux du fichier, accents precomposes. Une espace
insecable precede le `?` de chaque enonce et le `!` des messages du jeu. Les enonces font de 6 a 13 mots, une ou deux phrases, une seule question. Les
propositions font 3 a 6 mots. Chaque indice n'apparait qu'apres une erreur, et c'est celui de l'item.

**Ce que je n'ai pas fait** : je n'ai pas rejuge le paquet en entier au sens d'`A.277` d. Mes lectures du premier jet, des
corrections et la troisieme lecture tiennent, aucune n'est rouverte. Ce que j'ai regarde a l'ecran est ce que ces lectures
m'avaient laisse a regarder : le raccourci *ne* (lecture du premier jet, 3.2 ; relecture, 4).

#### 2. Ancrage au programme

Le paquet travaille **cette** ligne, `QLM-CE1-08`. Ses deux taches sont celles de ma regle 1.
- **A, le danger** (4 items) : juger l'etat de trois choses de la maison.
- **B, le geste** (8 items) : choisir la conduite devant un danger donne.

Les trois situations de la parenthese (prise, appareil pres de l'eau, fil abime) sont chacune bonne reponse en A et situation
en B. Aucun item ne demande un savoir de `-07` (aucune pile, aucun circuit). Aucun ne se resout avec le seul `cp-emc-alerte` :
il reste a chaque fois un distracteur qui demande la competence de la ligne.

#### 3. Les conditions

| Condition | Tenue | Ce que j'ai verifie |
|---|---|---|
| `DEL-CE1-1` | **oui** | bonnes reponses : ne pas toucher, ne pas approcher de l'eau, mains seches ; aucune reparation ni manipulation en bonne reponse ; aucune pile ; aucune alerte en proposition |
| `DEL-CE1-2` | **oui** | aucun accident, aucune blessure, aucun personnage qui a mal, ni par un mot ni par une periphrase, sur les 80 manches |
| `FER-1` | **oui** | prise (Jade ; Sami, Noe), fil (Mehdi ; Tom, Ines), eau (Maya, Adam ; Lea, Zoe, Hugo, Nina) |
| `FER-2` | **oui** | 12 items |
| `FER-3` | **oui** | deux operations distinctes, 4 et 8 items ; aucun item de A ne se resout par un geste, aucun item de B par une comparaison d'etats |
| `TRV-CE1-1` | **oui** | `CE1`, une ligne, aucun texte support, aucun champ a voix haute, aucune illustration |
| `TRV-CE1-2` | **oui, avec la reserve `DEL-CE1-R1`** | trois propositions distinctes, une seule acceptable ; familles declarees et justes ; bonne reponse strictement la plus longue dans 2 items sur 12, et a l'ecran dans 11 manches sur 80 ; le raccourci de forme de la tache B est en section 5 |
| `TRV-CE1-5` | **oui** | une methode ou un lieu du danger, 7 a 11 mots, douze indices distincts ; aucun ne nomme le geste ni l'etat de la bonne reponse |
| `TRV-CE1-6` | **oui** | six filles, six garcons ; *un adulte*, jamais *papa* ou *maman* ; la maison, la cuisine, le salon, le couloir sont ceux d'un personnage, jamais ceux de l'enfant |
| `LON-CE1-1`, `-2`, `-5` | **oui** | 13 mots et deux phrases au plus par enonce ; propositions de 6 mots au plus |
| `QMT-CE1-1` | **oui** | aucun des quinze mots exclus a l'ecran |
| `QMT-CE1-2` | **oui** | situations et gestes sources par `DEL-CE1-1` ; etats sans danger dits comme leur negation ; exacts |
| `NRC-CE1-1` | **oui** | aucun item de CP repris ; le refus de signaler, que le CP apprend a ecarter, n'est jamais seul a ecarter |
| `CYC-1` | **oui** | aucun paquet de CP ne sert l'electricite de la maison ; rien ne contredit `cp-emc-alerte` |

#### 4. Exactitude, item par item, a l'ecran

Chaque bonne reponse est juste devant sa situation, et chaque distracteur est faux.
- **Tache A** : les distracteurs sont sans danger electrique (*une prise bien fixée*, *une lampe allumée*, *un fil électrique bien
  rangé*, *une lampe éteinte*, appareil branche loin de l'eau, appareil debranche pres de l'eau).
- **Tache B** : les distracteurs sont des gestes qu'un enfant ferait et qui sont faux (*réparer le fil avec du scotch*, *le
  débrancher en tirant le fil*, *secouer ses mains puis l'allumer*, *toucher le fil pour voir*) ou un refus de signaler.
- La radio de Hugo est *branchée* : le danger que l'item affirme est porte par la scene.

**Aucune erreur factuelle.**

#### 5. La reserve, datee du 2026-10-27

**`DEL-CE1-R1` - dans la tache B, la forme seule designe la bonne reponse dans 5 items sur 8 (`TRV-CE1-2`).**

| Item | Bonne reponse | Les deux autres propositions |
|---|---|---|
| Tom, `:69` | *ne pas toucher le fil* | *réparer le fil avec du scotch*, *ne pas en parler* |
| Sami, `:76` | *ne pas toucher la prise* | *réparer la prise avec un tournevis*, *ne pas déranger un adulte* |
| Lea, `:90` | *ne pas toucher l'appareil* | *le débrancher en tirant le fil*, *ne pas déranger un adulte* |
| Hugo, `:118` | *ne pas l'approcher de l'eau* | *la poser près du robinet ouvert*, *ne rien dire à personne* |
| Noe, `:125` | *ne rien mettre dans la prise* | *nettoyer la prise avec un chiffon*, *ne pas en parler* |

**Ce que j'ai vu a l'ecran.** Dans ces cinq items, deux boutons commencent par *ne*. L'un est un refus de signaler, et ce refus
n'est jamais juste. L'autre est toujours la bonne reponse. Le troisieme bouton ne commence jamais par *ne*.

Pour le mesurer, j'ai applique a chaque manche de la tache B une regle qui ne sait rien de l'electricite : *"prends le bouton qui
commence par ne et qui ne parle pas de dire, de parler ou de deranger"*. Mesure par script sur les manches servies :
- elle trouve la bonne reponse dans **34 manches de la tache B sur 52** ;
- elle le fait dans **4 ou 5 manches par partie de 10**, dans chacune des huit parties ;
- **elle ne se trompe jamais** : dans les trois autres items de B (Ines, Zoe, Nina), elle ne designe aucun bouton.

Un enfant qui a appris au CP qu'il faut le dire (`cp-emc-alerte`) et qui a remarque, en une partie, que la reponse commence par
*ne pas* trouve ces cinq reponses sans juger le geste de reparation ou d'imprudence. C'est ce que j'avais ecrit d'avance : *"s'il
s'y voit, ce sera une reserve datee et la regle 3 sera rouverte a ce moment-la"* (lecture du premier jet, 3.2). Il s'y voit. Ce
n'est pas une barre relevee.

**Pourquoi je ne refuse pas.**
- Les douze reponses sont justes et aucun fait n'est faux.
- La regle que le raccourci fait suivre (*ne pas toucher*, *ne rien mettre*, *ne pas approcher*) est le geste de la ligne
  lui-meme : l'enfant qui la suit retient un geste vrai.
- Ce qu'il manque est le jugement du distracteur de reparation ou d'imprudence sur ces cinq items. La tache A et les trois
  autres items de B le demandent toujours.

**D'ou vient le defaut.** De mes regles 2 et 3 prises ensemble, pas du developpeur :
- les formes admises pour la prise et pour le fil commencent presque toutes par *ne* ;
- l'abstention et la precaution niee sont interdites en distracteur ;
- le seul distracteur en *ne* qui reste permis est donc le refus de signaler.

**Ce qui leve la reserve** (critere mesurable par script) : dans la tache B, **au plus un item** ou la bonne reponse est la seule
proposition commencant par *ne*, *ne pas*, *ne rien* ou *ne jamais* qui n'est pas un refus de signaler. Un item seul n'apprend
pas un signe, comme pour la clause 3 d'`ADR-0086`. Le moyen n'est pas fixe ici : il passe par une rouverture de mes regles 2 et 3,
que je dois ecrire **avant** toute correction du paquet.
- Pistes, non tranchees : de nouvelles formes affirmatives de bonne reponse a me soumettre, du type de *laisser le fil sans le
  toucher* deja admise ; ou un autre distracteur en *ne* qui ne soit ni une abstention, ni une precaution niee.
- Rien n'est a corriger avant cet arbitrage.
- Ce qui ne leve pas la reserve : retirer le refus de signaler sans rien d'autre. La bonne reponse deviendrait le seul bouton en
  *ne*, ce que ma regle 3 interdit deja.

#### 6. Corrections de mes propres constats

- **Mes deux fichiers precedents sur ce lot ont lance `verifier-chemins.sh` avec `< /dev/null`** (lecture du premier jet,
  section 9 ; relecture, section 7). Cette forme verifie une liste vide : son *"Aucun chemin de poste de travail"* n'est pas une
  mesure (`DEC-0120` A.280 point 2). Ces deux lignes ne prouvent rien. Cette fiche lance le garde-fou dans la forme de la CI.
- **La fiche annonce *"Premier `verify` complet du lot : a faire"*.** Ce n'est plus vrai a `90c421a7` (section 1). Je ne reecris
  pas la ligne de `camille-po` : je le dis ici.

#### 7. Ce dont je reste la seule garde sur cette ligne

`PaquetCe1QlmDangersElectriciteTest` et la garde transverse tiennent la forme : abstentions ancrees, alerte, mots des indices,
mot commun aux distracteurs de A, mots exclus, longueurs. **Ils ne lisent pas le sens.** Aucune des proprietes suivantes n'est
couverte par un test, et je ne les tiens qu'en jouant :
- la realite du danger en A et son absence dans les distracteurs ;
- qu'une bonne reponse de A ne soit pas un geste deguise ;
- la plausibilite des distracteurs de B ;
- qu'aucun appareil de B ne puisse marcher a piles ;
- qu'aucun personnage nomme ne soit blesse, meme par une periphrase ;
- la methode des indices au-dela des listes de `T3` ;
- `NRC-CE1-1` au-dela du texte ;
- le raccourci de forme de la tache B (`DEL-CE1-R1`) : aucun garde-fou ne compte les *ne*.

**Ce que ce verdict ne couvre pas.** L'entree de catalogue n'est pas auditee : elle est a faire (`QC3`). Je l'ai jouee avec le
titre *"Les dangers de l'électricité"*, la consigne *"Lis bien, puis choisis la bonne réponse."* et 10 manches. Si l'entree
ecrite differe sur un de ces trois points, ce point n'est pas couvert, et il me revient avant le branchement.

### Mentions de non-couverture opposables

> **`MNC-1`** - Ce Done ne couvre pas que l'enfant voie, sans faire defiler la page, un element quelconque du jeu, y compris le
> retour qui suit sa reponse - verdict, indice, proposition. Cette propriete est sortie de la porte pedagogique par arbitrage de
> l'utilisatrice du 2026-08-28 (`DEC-0053`) ; sa dette est ouverte, croissante et assumee. Aucune mesure ne l'a instruite sur ce
> paquet ; je n'ai pas vu le rendu au navigateur.

### Duree de l'audit, a l'horloge

Donnee brute, horloge du poste lue par `date` (UTC+01:00). Aucune conversion en bareme (`DEC-0120` A.276).

| Fenetre | Debut | Fin | Duree |
|---|---|---|---|
| lecture de la procedure, de mes lectures du lot et du modele de fiche | 21:52:08 | 21:58:00 | 5 min 52 s |
| jar d'audit, lancement, parties jouees, mesures a l'ecran, arret par PID | 21:58:00 | 22:07:09 | 9 min 9 s |
| redaction de ce bloc et verifications avant commit | 22:07:09 | voir le message de commit | non ecrite ici |

L'heure de fin de la derniere fenetre est rapportee a l'agent lanceur et non ecrite ici : l'ecrire changerait le fichier mesure.

**Identifiants** : un neuf, `DEL-CE1-R1`. Forme copiee de `SYC-CE1-R1` et de `RCT-CE1-R1` : trois segments, niveau au milieu,
marqueur `R` dans le dernier (`ADR-0100`). Le prefixe `DEL-CE1` est deja alloue (`DEL-CE1-1`, `-2`), et `DEL-CE1-R1` etait absent
de la section 4 avant que je l'ecrive. Il est alloue dans `squad/decisions/REGISTRE-identifiants.md`, section 4, dans le meme
diff. Aucun identifiant renomme.

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-27.
