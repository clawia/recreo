# Avis de faisabilite - theo-archi, sprint 17

- **Auteur** : theo-archi. **Date** : 2026-08-19.
- **Branche** : `s17-theo`, worktree dedie, forkee de `round-two` a `e3933d5` ; lignee verifiee par
  `outils/verifier-lignee.sh`.
- **Perimetre instruit** : `DEC-0039` et les quatre stories de la section Sprint 17 de
  `docs/backlog/backlog.md`, lues dans le worktree de `camille-po` (`s17-camille-couverture`).
- **Etape 1 du planning** : cet avis precede tout commit de code.
- **Ce que je ne fais pas** : rediscuter le perimetre tranche par l'utilisatrice, ni la couverture
  partielle assumee de `S17-3`, ni aucun arbitrage de `nadia-prof`.
- **Aucun chiffre de rendu revendique** : je n'ai pas de navigateur. Aucune instance ne tourne, je n'en
  ai lance aucune.
- **Toute mesure de ce document porte sa methode a cote d'elle**, et les mesures partielles portent
  `-am` (`CLAUDE.md`, prerequis de build).

## 0. Verdict d'ensemble

| Story | Verdict |
|---|---|
| S17-1 | **faisable en l'etat**, aucun `ADR` |
| S17-2 | **faisable, mais ce n'est pas une conversion** : la matiere premiere couvre 8 des 20 items exiges |
| S17-3 | **faisable, et ce n'est pas une conversion non plus** : la matiere premiere n'est pas du meme genre de jeu |
| S17-4 | **faisable techniquement en deux lignes, et bloquee sur une mesure que la squad ne peut pas produire** |

**Un `ADR` neuf, avec sa regle dans le meme commit** : `ADR-0049`, qui ferme enfin `DEC-0032` point 2.
**Un changement de statut** : `ADR-0042`, l'engagement que j'avais pris a la cloture de s16.

**Le fait qui domine tout ce sprint, et que personne n'avait chiffre** : `contenu-repris/` n'est pas un
format d'entree du chargeur. Ce n'est pas une nuance de forme - c'est un schema different, une
arborescence differente, des cles differentes, et une graphie de matiere differente. Les deux stories
de contenu sont donc des **stories d'ecriture de contenu** avec un appoint de reprise, pas des stories
de conversion. Section 1.

## 1. Ce que le chargeur accepte de la matiere premiere, et ce qu'il refuse

**Methode** : lecture des deux fichiers nommes par `camille-po`
(`contenu-repris/quiz/cm1-comprehension.yml`, `contenu-repris/associer/cm1-synonymes.yml`) et d'un
paquet servi de reference (`adapters/adapter-content/src/main/resources/contenu/cm1-homophones.yaml`),
sur l'arbre `e3933d5`.

**Le chargeur pilote par schema (`ADR-0007`) refuse ces fichiers en entier, des la premiere cle.** Il
exige `schema: 1`, `id`, `type`, `descripteur` en correspondance imbriquee a quatre cles, puis `items`
ou `parametres` - et il jette une exception nommee sur chaque champ obligatoire absent. Le tableau des
ecarts, cle par cle :

| Reprise | Servi | Verdict |
|---|---|---|
| aucun `schema` | `schema: 1`, refuse si different | **refuse** |
| aucun `id` ni `type` | `id`, `type` obligatoires | **refuse** |
| `matiere`/`niveau`/`domaine` **a plat**, pas de `competence` | `descripteur:` imbrique, **quatre** cles dont `competence` | **refuse** |
| `questions:` / `question:` / `bonne:` | `items:` / `enonce:` / `bonneReponse:` | **refuse** |
| aucun `indice` | `indice` par item, exige par `ChaquePaquetPorteUnIndiceTest` | **refuse** |
| `entryId`, `title`, `description`, `gameKey`, `gameLabel` | rien - ces roles vivent dans `catalogue.yaml` (`id`, `titre`, `consigne`, `mecanique`, `paquet`, `nombreDeManches`) | ignore, mais a reporter a la main |
| `matiere` **accentuee** | `matiere` **en ASCII** | **incoherence**, section 6 et `ADR-0049` |

**Ce que cela coute, dit sans l'habiller** : chaque item doit etre reecrit cle par cle, chaque paquet
doit recevoir une `competence` qui n'existe pas dans la reprise, et chaque item doit recevoir un
`indice` qui n'y existe pas non plus. **L'indice est le poste le plus lourd, et il est pedagogique, pas
technique** : un indice de comprehension qui ne revele pas la reponse est un texte a ecrire, item par
item, et `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` le confrontera aux autres items du paquet.

**Un outil de conversion serait une mauvaise reponse, et je le dis avant que quelqu'un le propose** :
la moitie des champs manquants ne se derive pas du fichier source (`competence`, `indice`, `consigne`),
donc un convertisseur produirait des trous a remplir a la main de toute facon, en ajoutant du code de
production ou un script a maintenir pour deux fichiers. Trois lignes semblables valent mieux qu'une
abstraction prematuree ; ici, deux fichiers ecrits a la main valent mieux qu'un convertisseur.

**Verdict de perimetre, et il change la nature des deux stories** : `contenu-repris/` fournit la
**matiere pedagogique** - des textes, des paires de mots, deja jugeables - et **aucune structure**.
C'est exactement ce que `CLAUDE.md` en dit ; ce sprint est le premier a le payer.

## 2. Les garde-fous de contenu, un par un, sur du contenu qu'aucun n'a jamais rencontre

**Methode** : lecture de chaque garde-fou et de son critere, confrontee aux deux fichiers de reprise et
aux criteres d'acceptation de `camille-po`. Comptes mesures : 8 items dans `cm1-comprehension.yml`
(`grep -c "^  - question:"`), 12 paires dans `cm1-synonymes.yml`, `nombreDeManches` valant 10 pour dix
des onze entrees du catalogue servi et 7 pour la onzieme.

**Ceux qui vont poser une exigence que la matiere premiere ne satisfait pas d'emblee :**

| Garde-fou | Ce qu'il exigera |
|---|---|
| **`ADR-0023`** (strictement plus d'items que de manches) | **le blocage le plus dur si le contenu restait tel quel** : 8 items contre 10 manches serait rouge. `camille-po` l'a devance en exigeant **au moins 20 items** ; la matiere premiere en fournit **8**, donc **40 %**. C'est ce chiffre qui dit la nature de la story |
| **`ChaquePaquetPorteUnIndiceTest`** | un `indice` par item, absent de toute la reprise : douze a vingt indices a ecrire, et ce sont des textes pedagogiques |
| **`AucunIndiceNeContreditUnAutreItemDuMemePaquetTest`** | que ces indices neufs ne se contredisent pas entre eux dans le paquet - un cout qui croit plus vite que le nombre d'items |
| **`TexteAccentueExposeParLePortEntrantTest`** (S16-5) | l'inscription de `titre`, `consigne` et des quatre cles du descripteur de chaque entree neuve dans son inventaire, **avec leur motif**, dans le meme commit. C'est ce garde-fou qui rend `DEC-0032` point 2 non differable - section 6 |
| **`ADR-0045`** (chaque entree charge son paquet, dans les deux sens) | une methode de test par entree neuve, et la restitution de celle de `S17-4` |

**Ceux qui vont mordre sur du contenu narratif alors qu'ils n'ont jamais vu que du calcul et de
l'orthographe, et dont le verdict ne se predit pas par lecture :**

| Garde-fou | Ce qu'il faut savoir |
|---|---|
| **`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`** | il classe la bonne reponse par **valeur comparable** (`ADR-0041`). Sur des reponses textuelles non numeriques, la lecture de valeur ne rend rien et le rang se classe autrement : **je ne sais pas dire par lecture quel taux ce contenu produira, et je refuse de le deviner.** Mesure a faire au premier depot du paquet, quitte a reordonner des distracteurs. **A prevoir dans le lot, pas apres** |
| **`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest`** | identite = enonce + jeu de propositions. Sur la comprehension, chaque texte-support etant unique, il passera sans effort. Sur les **synonymes** le risque est reel : deux items qui partagent un distracteur et un mot rapprochent leurs ecrans |
| **`AucunePropositionNEstEgaleAUneAutreDansUnItemTest`** | mordra si un distracteur de synonyme egale la bonne reponse - **facile a produire par accident** quand les distracteurs sont pris dans les autres paires du meme fichier |

**Ceux qui ne verront rien de ce contenu, et il faut le dire pour ne pas les crediter :**

- **tous les garde-fous de rendu** (`TailleDesTextesEtBoutonsTest`, `ViewportEtLargeurTelephoneTest`,
  `AucunEtatDePressableNEstAbandonneAuTiersTest`) lisent des **declarations** de feuille, jamais un
  texte : un texte narratif de six lignes leur est invisible ;
- **`UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest`**, la clause que j'ecris dans ce lot, ne voit
  que la **graphie** de la matiere, jamais la justesse d'un item ;
- **aucun garde-fou du depot ne juge qu'un texte est comprehensible par un enfant de CM1**, ni qu'un
  distracteur est plausible sans etre juste. C'est `nadia-prof`, integralement.

## 3. Le pli et le texte narratif : ce qui est gardable, et ce qui ne l'est pas

**Ce qui n'est pas gardable, et le motif n'a pas bouge d'un mot depuis `ADR-0025` et `ADR-0028`** : la
position d'un element rendu est une propriete de pixels, pas de declarations. Elle depend de la police
installee et du repli du texte, aucun fichier maison ne declare de `font-family`, et `DEC-0016` interdit
nommement tout critere en nombre de caracteres - motif mesure, un raisonnement par lettres manque son
pire cas reel. **Un texte narratif ne change rien a ce raisonnement, il en augmente seulement l'enjeu.**

**Et je ne peux pas mesurer** : pas de navigateur. Les chiffres qui circulent - pli a `y=411`, reserve
de +14 px sur les grands nombres - ne sont pas de moi et je ne les reprends pas comme des mesures.

**Ce qui est gardable, et ce n'est pas le pli** : le **mode de defaillance**, comme en s15. A largeur
insuffisante, un bloc de texte passe a la ligne ou deborde, et la difference entre les deux est une
declaration, donc lisible. Si le lot introduit un habillage neuf pour le texte-support, sa regle declare
son repli plutot que de le laisser au defaut - la forme qu'`ADR-0028` juge acceptable, garder une cause
et jamais un effet. **Je ne demande rien de plus, et surtout pas un plafond de longueur** : `ADR-0028`
a deja refuse cette forme au motif qu'elle serait rouge sur un contenu que `nadia-prof` a valide.

**Le fait de sprint a nommer clairement** : `DEC-0024` a retire un jeu **pour cette raison exacte** - un
enonce de six lignes a 2 rem, environ 288 px - et `S17-2` ajoute un contenu dont chaque item porte un
texte a lire **puis** des propositions. **Le risque de `DEC-0024` est donc reintroduit par construction
sur un paquet neuf**, et le seul instrument qui le mesure est hors de la squad depuis que l'utilisatrice
a repris la dette de capacite a sa charge. Ce n'est pas un motif d'infaisabilite, c'est un **ordre de
gestes** : mesurer un item de forme representative **avant** d'en ecrire vingt, jamais apres.

## 4. S17-4 : une resurrection dont deux des trois portes ne sont pas du code

**Le motif du retrait etait de rendu, ni de contenu ni de technique**, et `DEC-0024` le dit
explicitement : le contenu de `cm1-homophones` etait **valide** par `nadia-prof` sans reserve bloquante
au 2026-08-10, et ce qui a ete sanctionne est *"la longueur de son enonce le plus long au regard de
l'ecran"* - six lignes a 2 rem, non rachetable par le resserrement de marges qui a sauve deux autres
jeux.

**Sa condition de retour est ecrite, et ce n'est pas un garde-fou** : l'etat le plus charge de chaque
manche, rendu a 360 px avec les feuilles reellement servies, mesure par `outils/mesurer-rendu.sh`,
laisse les propositions entierement au-dessus du pli **sur la totalite des manches du paquet**.
`DEC-0024` precise que cette mesure ne devient verte qu'en jouant, et refuse nommement un garde-fou qui
bornerait la longueur d'un enonce.

**D'ou la chaine reelle de `S17-4`, dans cet ordre, dont une seule porte est du code :**

1. **raccourcir le paquet** - travail de contenu, et un paquet raccourci est un contenu modifie qui
   repasse devant `nadia-prof` comme un contenu neuf ;
2. **mesurer le pli sur toutes les manches** - hors de portee de la squad, dans la dette de capacite que
   l'utilisatrice a reprise a sa charge ;
3. **restituer l'entree de catalogue et sa methode de test** - **deux lignes**, desormais gardees dans
   les deux sens par `ADR-0045` : l'entree restituee fait exister le cas par derivation, et le couple ne
   peut plus se rompre a la main comme il l'a fait en s13.

**Verdict** : techniquement trivial, **et ce n'est pas une story de developpement**. La compter hors du
plafond des ouvertures est juste ; la porter au plan de charge d'un developpeur serait faux. Sa porte 2
ne peut pas se fermer a l'interieur du sprint sans l'utilisatrice.

**Ce que je ne fais pas** : deduire du fait que le pli a bouge depuis s10 que le paquet passe
maintenant. Ce serait le chiffre sans methode que ce depot sanctionne - la mesure de s10 portait sur un
ecran qui n'existe plus, et aucune mesure neuve n'existe.

## 5. Verdict story par story, et les criteres refuses ou reecrits

### S17-1 - l'indicateur de focus clavier absent : faisable en l'etat, aucun `ADR`

Elle a un atout que les trois autres n'ont pas : **elle porte sur une declaration**, donc elle est
entierement gardable. Ses deux criteres sont **encodables tels qu'ils sont ecrits** - une propriete
declaree par la feuille sur chacun des sept pressables, et le fait que cette propriete differe de celles
que la bibliotheque gouverne. Une precision de forme, gratuite : le second critere doit nommer la
propriete **par sa derivation** (elle ne figure pas parmi celles qu'un etat tiers ecrase, au sens de
`touche()` d'`ADR-0046`) plutot que par une liste, sans quoi une propriete de plus dans la bibliotheque
le rendrait faux en silence.

### S17-2 - comprehension CM1 : faisable, mais requalifiee

**Faisable, et ce n'est pas une conversion** : la matiere premiere fournit **8 items sur les 20 exiges**,
aucune structure, aucun indice, aucune competence. C'est une story d'ecriture de contenu avec 40 %
d'appoint.

Ses criteres sont **remarquablement bien ecrits pour ce qu'un automate peut tenir**, et deux d'entre eux
sont meme plus forts que ce que j'aurais demande - le comptage a 20 items devance `ADR-0023`, et la
classification explicite/implicite est une propriete du paquet entier plutot que d'un item. Deux
reecritures :

| Critere | Verdict |
|---|---|
| *chaque item rendu a 360 x 640, texte-support, question et propositions...* | **a reecrire ou a deplacer** : c'est une mesure de rendu, non gardable (`ADR-0025`, `ADR-0028`), et personne dans la squad ne peut la produire. Elle appartient a la porte de `nadia-prof`, pas aux criteres testables - la laisser parmi eux ferait croire que `mvn verify` la tient |
| *une seule des propositions repond a la question* | **encodable, mais pas par ce que la phrase dit** : un automate ne sait pas si une proposition repond a une question. Ce qui est encodable est que les distracteurs **diffèrent** de la bonne reponse et entre eux, ce que deux garde-fous tiennent deja. La justesse reste a `nadia-prof` ; le critere doit dire lequel des deux il demande |

### S17-3 - synonymes CM1 : faisable, et la matiere premiere n'est pas du meme genre de jeu

**Le fait mesure** : `contenu-repris/associer/cm1-synonymes.yml` est au format d'un jeu
d'**appariement** - `puzzles:` / `associations:` / `item` + `cible`, 12 paires, **aucun `bonne`, aucun
`distracteur`**. Le depot n'a qu'une mecanique, le bandit manchot, et ce fichier n'en porte pas les
donnees.

**Deux voies, et une seule est du perimetre d'un sprint a deux ouvertures :**

- **convertir les paires en items a choix** : chaque mot devient un enonce, son synonyme la bonne
  reponse, et **les distracteurs sont a inventer**. C'est du contenu neuf, qui passe devant `nadia-prof`
  comme tel. **Le piege est nommable d'avance** : prendre les distracteurs dans les autres paires du
  fichier produit facilement un second synonyme acceptable - `content` contre `heureux` avec `joli` et
  `tendre` en distracteurs est sur, mais `doux` contre `tendre` avec `heureux` l'est moins. C'est
  exactement ce que `nadia-prof` juge, et ce que `AucunePropositionNEstEgaleAUneAutreDansUnItemTest` ne
  verra pas, puisqu'il compare des chaines et non des sens ;
- **ouvrir une seconde mecanique d'appariement** : hors perimetre, et de loin - `ADR-0004`,
  `DecouplageMecaniqueContenuTest` (sept regles) et `ToutesLesMecaniquesSontTesteesTest` s'appliqueraient
  a une mecanique neuve. **A ne pas entreprendre au detour d'une story de contenu.**

Ses criteres sont **encodables tels qu'ils sont ecrits**, sauf celui qui dit qu'une bonne reponse est
*"un synonyme reellement synonyme"* : c'est un jugement de langue, pas une propriete calculable. Il
appartient a la porte de `nadia-prof` et doit y etre range explicitement.

### S17-4 - la resurrection : section 4

Verdict et chaine des trois portes en section 4. **Aucun critere a reecrire** : ceux de `camille-po`
renvoient a la condition de retour de `DEC-0024`, qui est deja formulee comme une mesure et non comme un
test.

## 6. La nomenclature accentuee des matieres : tranchee, et elle ne pouvait plus etre differee

**Reponse due depuis le 2026-08-17, differee trois fois. Elle est ecrite dans `ADR-0049`, avec sa regle
dans le meme commit.** En resume, et avec les chiffres qui la fondent :

- **la matiere est portee accentuee par la donnee de contenu elle-meme** - pas de second champ, pas de
  libelle d'affichage distinct, pas de table de correspondance ;
- **le cout est mesure et borne** : 12 paquets servis et une assertion de test, une fois. L'option
  inverse - garder l'ASCII - coute une reecriture **a chaque conversion**, sur une reserve de 61 fichiers
  qui porte la matiere accentuee, donc indefiniment, et chaque conversion est une occasion de l'oublier ;
- **rien ne casse** : `ContentDescriptor.matiere()` n'est appele nulle part dans les sources de
  production des cinq modules, et le seul comparateur du depot est une assertion de test ;
- **un identifiant sans accent pour une future route de matiere sera une cle de plus du paquet**, jamais
  un depliage calcule - ce point n'est pas ouvert par ce sprint, il est ecrit pour ne pas etre rouvert de
  zero.

**Pourquoi la quatrieme deferral n'etait plus disponible, et ce n'est pas un argument de process** : le
garde-fou que `S16-5` a fait naitre inventorie **toutes les chaines exposees par le port entrant**, y
compris la matiere de chaque entree, avec le motif de chacune. Toute entree neuve de francais CM1 oblige
donc a inscrire une graphie de matiere dans cet inventaire, dans le meme commit. Differer une quatrieme
fois voudrait dire y inscrire une graphie dont on sait qu'elle est fausse pour un enfant.

**La regle ecrite dans ce lot** :
`UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest` - une matiere s'ecrit d'une seule facon dans tout
le contenu servi, population derivee du repertoire de contenu et de la chaine du chargeur reel, repliage
par `Normalizer` sans aucune liste de mots. **Elle interdit exactement le geste que ce sprint est sur le
point de faire** : deposer un paquet converti accentue a cote des paquets servis en ASCII. Mesure :
`bash outils/mvn-jdk21.sh -B -o -pl adapters/adapter-content -am test
-Dtest=UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest` - **3 tests, 0 echec** (production,
non-vacuite, morsure). Ce qu'elle ne dit pas, et `ADR-0049` le dit a sa place : **laquelle** des deux
graphies est la bonne.

## 7. `ADR-0042` : l'engagement est tenu

**Statut change** : `Accepte` devient **`En attente de son objet`**. C'est mot pour mot ce que j'avais
ecrit a la cloture de s16 - *"si le declencheur n'a pas joue a la cloture de s16, `ADR-0042` doit changer
de statut"*. Verifie sur le perimetre de s17 : aucune des quatre stories ne fait apparaitre, sur un ecran
a partie en cours, un arret dont la requete demarre une partie. Le catalogue n'est toujours montre a
aucun moment ou une partie vit.

**Ce qui ne change pas** : la contrainte de forme ecrite pour `ines-design` reste valable et opposable
telle quelle - elle se derive des clauses d'`ADR-0038` et d'`ADR-0040`, qui sont ecrites et vertes. Les
mesures M0 a M5 et les neuf non-preuves sont des faits, pas des promesses.

**Ce que je ne fais pas, et ce n'est pas de la complaisance** : ecrire le predicat aujourd'hui. La mesure
M1 de cet `ADR` etablit qu'il serait vert sur une population vide, et son obligation 3 refuse d'appeler
garde-fou une clause livree sans son objet. Le livrer pour pouvoir reecrire `Accepte` serait
l'abstraction morte que ce depot s'interdit, avec un vernis d'executable.

**Ce que je retiens, et c'est la troisieme fois de deux sprints** : `ADR-0047` et `ADR-0048` attendaient
un statut, celui-ci attendait un changement de statut, et les trois ont du etre reclames par quelqu'un
d'autre. Le point commun n'est pas l'oubli d'une decision : **j'avais ecrit la condition et laisse son
echeance a un moment qui n'appartenait au mandat de personne**. C'est la lecon qu'`ADR-0013` a tiree en
s13 d'une clause adossee a une ceremonie, et elle vaut pour mes propres engagements.

## 8. Les trois dettes de garde-fou routees a la cloture de s16

### 8.1 La regle de la duree du marqueur, verte par une divergence fortuite - **entre au perimetre**

**J'ai d'abord ecrit que le lot avait implemente autre chose que ce que l'`ADR` demandait. C'est faux, et
je l'ai verifie avant de le laisser.** Les deux tests lisent bien la duree attendue **sur la session de
la requete jugee** - `assertThat(marqueur.getMaxAge()).isEqualTo(session.getMaxInactiveInterval())` dans
`PartieControllerTest`, la meme forme dans `EtatDePartieEnSessionTest` - donc aucun nombre n'est recopie
dans l'assertion. `marc-dev` a implemente exactement ce que `ADR-0047` demandait.

**Le defaut est ailleurs, et il est de moi.** L'egalite testee ne prouve pas la propriete qui compte -
*la duree n'est pas une constante* - parce qu'une constante cablee satisferait l'egalite dans **chaque**
test pris separement. Ce qui la refute aujourd'hui est que les deux tests fixent des intervalles
**differents** : 900 dans l'un, 1800 dans l'autre. Une valeur cablee a 1800 passerait le premier et
echouerait le second. **La propriete tient donc par une divergence entre deux fixtures de deux classes
distinctes, que personne n'a choisie pour cela** - si l'une des deux etait alignee sur l'autre par un
lot futur, la regle deviendrait verte en cessant de comparer, et rien ne le signalerait.

**Ce que `ADR-0047` aurait du enoncer** : non pas "l'egalite est verifiee sur la session de la requete",
qui est vrai et insuffisant, mais **"l'egalite est verifiee sur au moins deux durees differentes dans la
meme epreuve"**. C'est la seule forme ou une constante cablee echoue sans dependre d'un voisin.

**Correctif : quelques lignes**, dans une seule classe - poser un intervalle, lire le marqueur, poser un
second intervalle different, relire - plus la mutation qui prouve qu'elle mord : remplacer la lecture par
une constante et constater qu'une seule des deux durees suffit a la faire rougir. **Recommandation : au
perimetre de s17, avant les stories de contenu s'il faut choisir**, parce que c'est la seule des trois
dettes dont la regle ne garde pas aujourd'hui ce qu'elle annonce - et parce que l'enonce a corriger est
dans un `ADR` que j'ai accepte il y a deux jours.

### 8.2 Les garde-fous de provenance prouvent un emplacement, jamais une fraicheur - **entre au perimetre**

`yanis-qa` a obtenu du **bytecode perime accepte au vert** : la clause verifie que les classes amont
viennent de l'arbre du depot et non du depot local, ce qui etait bien la propriete que j'avais enoncee -
mais un `target/classes` present et perime satisfait cette propriete tout en decrivant un autre arbre.
**Mon enonce de s14 etait donc juste et insuffisant** : j'ai nomme l'emplacement en croyant nommer la
fraicheur.

**Ce qui est gardable en plus, et ce qui ne l'est pas** : comparer l'horodatage d'un `.class` amont a
celui de son `.java` est faisable et derive, mais il faut dire ce que ca ne prouve pas - un `touch`
suffirait a le tromper, et un `clean` en amont d'une mesure reste la seule garantie. **Ordre de grandeur
: un petit lot**, et sa valeur est moindre que celle de 8.1 : ce garde-fou attrape aujourd'hui le cas
frequent (le `-pl` sans `-am`) et manque le cas rare (un `target` perime). **Recommandation : au
perimetre si la place le permet, apres 8.1.**

### 8.3 La migration du marqueur d'ecran de jeu vers un attribut - **hors perimetre, et le motif tient toujours**

Ma recommandation de s16 n'a pas change et son fait mesurable non plus : `.ecran-de-jeu` n'est nomme par
aucun selecteur qui s'en serve, il ne lui reste qu'une regle vide, et le vocabulaire deviendrait **trie
par nature** - une classe quand une feuille la selectionne, un attribut quand la declaration est
purement d'etat. **Mais son cout est dans les fixtures** : une quinzaine de jeux fautifs dont chaque
preuve de morsure doit etre reverifiee. **Ordre de grandeur : un lot petit a moyen.**
**Recommandation : hors perimetre de s17**, et pour un motif de sprint et non de fond - ce sprint ouvre
du contenu neuf sur deux stories dont les garde-fous n'ont jamais rencontre ce genre de contenu
(section 2), et toucher quinze fixtures en meme temps melangerait deux sources de rouge.

## 9. Ce que cet avis ne dit pas

- **Si le perimetre est le bon**, ni le plafond a deux ouvertures : l'utilisatrice a tranche,
  `camille-po` a motive, et je n'ai rien a y ajouter.
- **Aucun Done pedagogique**, ni son anticipation. La justesse d'un synonyme, la plausibilite d'un
  distracteur, la comprehensibilite d'un texte pour un CM1 : `nadia-prof`, integralement.
- **Aucune mesure de rendu.** Elle est hors de la squad depuis que l'utilisatrice a repris la dette de
  capacite, et la section 3 dit ce que cela coute a `S17-2` comme a `S17-4`.
- **Quelle story affecter a qui, ni dans quel ordre** : `sofia-sm`. Je dis seulement, en 8.1, laquelle
  des trois dettes ne garde rien aujourd'hui.

## 10. Ce que ce lot mesure, avec sa methode

`bash outils/mvn-jdk21.sh -B clean verify` sur ce worktree, JDK 21.0.10 designe par la toolchain :
**BUILD SUCCESS**, `Total time: 01:30 min`, les six modules `SUCCESS`.

**548 tests, 0 echec, 0 erreur, 0 ignore. 29 regles ArchUnit.**

**Methode du comptage des tests, dans le meme paragraphe que le chiffre** : somme de l'attribut `tests`
de la balise racine `testsuite` des **88 rapports XML** produits par cette seule execution, sous
`*/target/surefire-reports/` et `*/target/failsafe-reports/` des **cinq modules declares dans `modules`**
du pom agregateur, lus **apres le `clean`** de cette commande - jamais un balayage lance depuis la racine
du depot, qui verrait les worktrees des autres agents. Repartition : `adapter-web` 269,
`adapter-content` **126**, `bootstrap` 93 (dont **6 en failsafe**), `domain` 48, `application` 12.

**Methode du comptage des regles** : balayage des seuls `src/test/java` des cinq modules declares au pom,
critere `ArchRule <nom> =` - **29**, inchange. La clause de ce lot n'est pas une regle ArchUnit :
l'objet juge est la valeur d'une cle d'un fichier de ressources, qu'ArchUnit ne voit pas.

**Le delta, et il est entierement le mien** : de **545** a la cloture de s16 - chiffre de `sofia-sm`, que
je ne remesure pas sur cet arbre-la et que je cite comme le sien - a **548**, soit **+3** : les trois
methodes de `UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest` (la clause sur la production, la
non-vacuite, la morsure). Aucune ligne de production n'est touchee par ce lot.
