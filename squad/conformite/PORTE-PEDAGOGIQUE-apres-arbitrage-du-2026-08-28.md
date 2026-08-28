# Ma porte pedagogique apres l'arbitrage de l'utilisatrice du 2026-08-28 - nadia-prof

- **Auteure** : `nadia-prof`. **Date** : 2026-08-28. **Sprint** : s22 (ouverture).
- **Branche** : `s22-nadia`, ouverte sur `round-two` a `f0d5d5a`. Aucun fichier de code, aucune
  fiche de conformite, aucun contenu touche : je n'ecris que ce document.
- **Ce que ce document est** : l'etat de ma porte pedagogique apres l'arbitrage de l'utilisatrice
  du 2026-08-28 sur `REP-8`, la relecture de mes propres arbitrages prealables des trois paquets
  candidats de s22, ma decision sur la regle de methode proposee a la retro de s21, et mon debit
  chiffre pour ce sprint.
- **Ce que ce document n'est pas** : ni un Done pedagogique, ni un audit. **Rien n'est ecrit,
  rien n'est joue, rien n'est valide par ce fichier.** Les Done pedagogiques de s22 seront
  prononces dans les blocs de validation des fiches de conformite, apres avoir joue.
- **Ou il vit, et pourquoi ici** : dans `squad/conformite/`, a cote de mes arbitrages prealables,
  et **pas** dans `squad/sprints/s22-.../`. Deux motifs : le repertoire de sprint et son slug
  appartiennent a `sofia-sm`, qui ne les a pas encore crees, et `camille-po` met a jour les fiches
  de conformite en parallele sur sa propre branche. Ce fichier ne touche donc rien que quiconque
  edite aujourd'hui.
- **Ce que je declare degrade** : je n'ai lance ni jar, ni navigateur, ni mesure. C'est normal ici -
  il n'y a rien a jouer. Aucune phrase de ce document ne vaut jugement sur un increment.

---

## 1. Ce que l'arbitrage retire de ma main, et ce qu'il y laisse

### 1.1 L'arbitrage, et la portee que je retiens

L'utilisatrice, le 2026-08-28 : *"on va mettre de cote `REP-8` car la livraison test doit arriver
pour la semaine prochaine, donc la mesure n'est plus necessaire a la validation du jeu meme si ca
peut ouvrir une dette croissante sur le reste du catalogue."* Portee confirmee par elle : **tout le
catalogue, y compris les paquets neufs de s22**, et pas seulement les items CM1 deja mesures.

**La phrase qui m'a ete relue par `sofia-sm` est juste sur le fond. Je la confirme, et je la
precise sur trois points ou elle serait lue plus largement ou plus etroitement qu'elle ne doit.**

### 1.2 Ce qui sort de ma porte - je ne refuse plus la-dessus

**`REP-8`, nommement : l'indice, et plus generalement tout texte d'aide qui suit une erreur, n'est
plus un motif de `REFUSE` quand il est hors de vue sans defilement.** Cela vaut :

- sur les jeux deja au catalogue, y compris les **64 items CM1** que j'ai mesures le 2026-08-27 ;
- sur les paquets **neufs** de s22, y compris les trois candidats de la section 3 ;
- que le defaut vienne du contenu (un enonce long) ou de la forme (le budget vertical du cabinet).

**Cessent avec lui, parce qu'ils n'existaient que pour l'instruire** :

- mes **campagnes de mesure du pli** comme condition d'un Done pedagogique. Je ne lance plus
  `compter-le-pli-du-catalogue-entier.py` ni `auditer-un-paquet-entier.py` pour ouvrir ou fermer une
  porte. S'ils tournent encore, ce sera pour informer `camille-po`, jamais pour valider ;
- mes **arbitrages de forme sur le budget vertical** (le gel de la police de `.proposition`, le bloc
  de message epingle - `749244a` et `f701f37`). Ils ne sont pas effaces : ils gardent leur date et
  leur motif, comme tout verdict de ce depot. Ils cessent d'etre opposables comme conditions ;
- toute condition prealable de moi dont le budget vertical est **le seul** motif. La section 3 les
  enumere une par une.

**Ce que je continue de faire, et qui ne coute presque rien** : quand je vois le defaut en jouant,
je l'ecris en **reserve non bloquante, datee**, dans la fiche du jeu. Voir section 2.

### 1.3 Ce qui reste entierement dans ma main

Sans changement, et un `REFUSE` sur l'un de ces motifs reste un `REFUSE` :

1. **L'exactitude du contenu.** Un compte de syllabes faux, un produit faux, une duree fausse, un
   synonyme qui n'en est pas un. Une erreur de fait dans un contenu scolaire est un motif de refus a
   elle seule, et elle l'est restee a travers tous les arbitrages de ce produit.
2. **La decidabilite d'un item** : un item qui a deux reponses defendables n'a pas de bonne reponse.
   C'est le motif des trois refus de s21.
3. **Le niveau scolaire** : l'ancrage au referentiel, et que l'increment travaille **cette**
   competence et pas une voisine plus facile.
4. **La lisibilite de ce que l'enfant lit** - le vocabulaire, la longueur de la consigne, la charge
   de ce qu'il doit tenir en tete. **Ce n'est pas le pli** : c'est le texte lui-meme. Une consigne
   de quatre lignes en CP reste un refus, sur un ecran de n'importe quelle hauteur.
5. **La qualite des distracteurs** : un distracteur absurde transforme l'exercice en devinette, un
   distracteur qui n'est pas une erreur reelle est un nombre de remplissage.
6. **La qualite des indices** : un indice qui donne la reponse, qui paraphrase l'enonce, qui est
   identique sur tout un paquet, ou **qui enseigne une regle fausse** (`GRA-15` de s21). L'indice
   sort de ma porte par sa **position a l'ecran**, pas par son **contenu**.
7. **Les biais et le ton** : genre, origine, milieu, configuration familiale, condescendance.
8. **Qu'aucun chemin ne contourne la competence annoncee.** C'est mon motif de s02 et il n'a jamais
   bouge.

### 1.4 Les trois precisions, parce que la phrase relue serait lue de travers sans elles

**Precision 1 - le verdict n'est pas l'indice, et il ne sort pas avec lui.**
`REP-8` porte sur l'indice. **Que l'enfant sache s'il a eu raison ou tort est une autre propriete**,
mesuree separement depuis le debut (le 2026-08-27 : indice sous le pli sur 8 jeux sur 17 ; verdict
sous le pli sur 3 jeux sur 17), portee par une autre entree de backlog (`REP-9`), et c'est elle - et
non l'indice - qui a fonde mon `REFUSE` de `cm1-reperes` a s20 : *"l'enfant presse un bouton et ne
voit pas s'il a eu raison"*. **Un jeu ou l'enfant repond et ne recoit aucun retour visible n'est pas
un jeu ou l'aide est mal placee : c'est un jeu qui ne fonctionne pas.** Je garde ce motif.

**Precision 2 - les propositions non plus.** Un ecran ou l'enfant ne voit **aucun bouton a presser**
n'est pas evaluable : c'est le Done technique qui manque, pas le mien
(`squad/README.md`, section 7 ; mon releve `s17-comp5-compose-45mots-4props.png` en est l'image).
Je ne mesure rien pour cela : si je joue et que je ne vois pas les propositions, le jeu ne se joue
pas, et je le dirai.

**Precision 3 - ce que je ne fais plus, c'est mesurer, pas regarder.** Je ne lance plus de campagne
et je ne compte plus d'items. Mais je joue chaque increment, et ce que je vois en jouant, je
l'ecris. **Un jugement porte en jouant n'est pas une mesure**, et l'arbitrage ne me demande pas de
fermer les yeux.

**Si l'une de ces trois precisions depasse ce que l'utilisatrice a voulu retirer, c'est a elle de le
dire, pas a moi de le deviner.** Je route la question a `sofia-sm` (section 7). En attendant, elles
s'appliquent : une porte lue trop largement bloque un sprint pour rien, une porte lue trop
etroitement laisse passer un jeu ou l'enfant ne voit pas s'il s'est trompe.

---

## 2. Comment j'ecris la dette desormais, pour qu'elle reste comptable

L'utilisatrice a nomme la contrepartie de son arbitrage : *"ca peut ouvrir une dette croissante sur
le reste du catalogue"*. Une dette assumee n'est une dette que si elle est ecrite ; sinon c'est un
oubli.

- **Forme.** Dans la fiche du jeu, section Reserves, une reserve datee portant l'etiquette exacte
  **`REP-8-CONSTAT`**, pour qu'un `grep 'REP-8-CONSTAT' squad/conformite/*.md` rende la dette
  entiere a n'importe quel moment, sans me la redemander.
- **Contenu.** Ce que j'ai vu **en jouant** : quel jeu, quel item, ce qui etait hors de vue.
  **Aucun chiffre en pixels**, puisque je ne mesure plus. Un constat qualitatif date vaut mieux
  qu'un chiffre invente.
- **Statut.** Non bloquante, toujours. Elle ne retient aucun Done, elle n'entre dans aucun motif.
- **Ce qu'elle ne fait pas** : elle ne rouvre pas `REP-8`, qui reste ouverte au backlog et
  appartient a `camille-po`.

**Un avertissement sur le seul chiffre existant, et il compte.** Mon compte du 2026-08-27 - **64
items d'indice et 10 items de verdict sous le pli, sur 478 items, 20 jeux enumeres sur 21, a
744 x 551, arbre fusionne de s21** - est **date, et il derivera des le premier contenu ecrit a s22**.
Il decrit un etat du 2026-08-27, pas l'etat courant. **Personne ne doit le citer au present**, et je
ne le remesurerai pas : c'est exactement ce que l'arbitrage retire. Le vingt-et-unieme jeu
(`bandit-manchot-additions-cp`, paquet genere non enumere) n'a jamais ete mesure et ne l'est
toujours pas.

---

## 3. Les trois paquets candidats de s22, relus condition par condition

Les trois arbitrages ont ete rendus sous un regime ou le pli comptait. Je les relis pour dire ce qui
tombe et ce qui tient. **Je n'ajoute aucune condition nouvelle** dans cette relecture, sauf la ou
j'amende une de mes propres formulations pour la rendre verifiable - et ces amendements-la sont
signales comme tels, dates, et ne durcissent rien.

### 3.1 `S13-2` - multiplier et diviser par 10 et 100 (`ARBITRAGE-PREALABLE-s13-multiplications-divisions-et-durees-cm1.md`)

**Aucune de mes dix conditions `MD-1` a `MD-10` ne tombe.** C'est le resultat le plus simple des
trois, et il tient a une raison precise : aucune n'avait le budget vertical pour motif.

- **`MD-9` (enonce d'une seule ligne, `23 x 10 = ?`) tient inchangee.** Son motif propre est la
  competence : la ligne du referentiel dit **calcul mental**, et un enonce qui n'est pas le calcul
  lui-meme ajoute une lecture a une epreuve qui n'en demande pas. **La phrase du pli qui suivait
  n'etait qu'un avantage de plus, pas le motif.** Elle est desormais **caduque** : voir 3.4.
- Les neuf autres portent sur l'operateur, la distribution des familles, les bornes des nombres, ce
  que l'enfant lit, le rang de la bonne reponse, les distracteurs, l'indice, les formes exclues et
  le titre. Aucune n'est affectee.

### 3.2 `S13-3` - problemes de durees (meme fichier)

**Aucune de mes onze conditions `D-1` a `D-11` ne tombe non plus**, et pour `D-8` la raison merite
d'etre dite parce qu'elle prouve que je n'ai pas a me dedire aujourd'hui.

- **`D-8` (vingt-cinq mots au plus, deux phrases au plus) tient inchangee.** J'avais deja ecrit dans
  la condition elle-meme : *"`M4` ne bloque plus depuis `DEC-0025` et je ne la ressuscite pas ; je
  mesurerai, je l'ecrirai, et ce sera un fait, pas un refus."* **Ce plafond n'a donc jamais ete une
  condition de pli** : c'est un plafond de **charge de lecture** pour un CM1 qui doit tenir deux
  instants et une duree en tete pendant qu'il calcule. Ce qui tombe, c'est la phrase de mesure qui
  suivait - et elle ne bloquait deja rien.
- **`D-7` (rang de la bonne reponse) tient, et son cout baisse fortement : je leve son exigence de
  comptage manuel.** Voir 3.4, ou je corrige un fait technique que j'avais moi-meme enregistre.
- **`D-9` est amendee par moi, aujourd'hui, en application de la regle de la section 4.** Elle
  enumerait des exemples (*"aucune situation qui suppose que l'enfant prend l'avion, part en
  vacances, a une voiture a la maison"*) la ou elle devait nommer la propriete. **Formulation qui
  fait foi a partir d'aujourd'hui** : *aucune situation dont la comprehension suppose un bien, un
  service ou une pratique que tous les enfants n'ont pas chez eux.* L'avion, les vacances et la
  voiture familiale restent des instances vraies de cette propriete ; elles cessent d'en etre la
  liste. **Ce n'est pas un durcissement** - la propriete est celle que la liste servait deja - et ce
  n'est pas non plus un relachement : un cinquieme cas que ma liste ne nommait pas est desormais
  attrape.

### 3.3 `S17-2` - comprehension de texte CM1 (`ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md` et `FICHE-bandit-manchot-comprehension-cm1.md`)

**C'est ici que l'arbitrage change le plus, et dans le sens qui debloque.**

**Ce qui tombe :**

- **`COMP-4` (support, question et toutes les propositions entierement visibles a 360 x 640 sans
  defiler, sur la totalite des items) tombe comme condition bloquante.** C'etait, mot pour mot,
  *"la seule condition de ce document que je devrai mesurer moi-meme"*.
- **`COMP-7` tombe avec elle** : elle n'existait que pour interdire qu'un autre chiffre soit
  substitue a `COMP-4`. Sans `COMP-4`, elle n'a plus d'objet.
- **Le premier motif de `COMP-6` (consigne sur une ligne) tombe** : il valait 30 px de budget. Son
  **second motif tient** - voir ci-dessous.
- **Le blocage de la story tombe.** J'avais ecrit, le 2026-08-19, que `marc-dev` **ne pouvait pas**
  ecrire les vingt items, parce que le plafond mesure de 4 lignes / 20 mots ne portait pas
  d'inference et que les ecrire aurait ete *"les ecrire pour rien"*. **Ce blocage n'a plus de
  fondement : il etait entierement bati sur le budget vertical. `S17-2` est developpable a s22.**
  C'est la consequence la plus utile de l'arbitrage pour ce sprint, et je la dis en clair pour que
  personne ne continue a lire mon avis de s17 comme un veto.

**Ce qui tient, inchange :**

- **`COMP-1`** (texte visible, question masquee : la reponse n'est pas trouvable) ;
- **`COMP-2`** (question visible, texte masque : la reponse n'est pas trouvable - ni culture
  generale, ni distracteur absurde) ;
- **`COMP-3`** (au moins un tiers des items exigent de relier deux informations distinctes du
  texte, et pour ceux-la la bonne reponse n'est pas un fragment recopie).

Ces trois-la sont **le motif de s02** applique a cette competence, et le motif de s02 ne depend
d'aucun ecran. Ce sont elles qui font la difference entre un exercice de comprehension et le
reperage litteral que j'ai refuse. **Elles restent, et elles restent bloquantes.**

- **`COMP-5` reste satisfaite et close** : la variante nue d'`ines-design` a ete acceptee le
  2026-08-19 sur sa **lisibilite** - taille, graisse, contrastes recalcules - pas sur son cout
  vertical. Un jugement de lisibilite ne tombe pas avec le pli. **Je ne rouvre pas cette porte.**
- **`COMP-6` est reformulee en propriete, sans son moyen.** Ce que la condition protegeait vraiment
  et qui n'a rien a voir avec les pixels : `.consigne` et `.texte-support` sont rendues a
  l'identique, si bien qu'un CM1 lit deux paragraphes typographiquement indiscernables et **ne sait
  pas ou l'histoire commence**. **Formulation qui fait foi a partir d'aujourd'hui** : *un enfant de
  CM1 distingue, sans qu'on le lui dise, la consigne du texte a lire.* Le moyen - une consigne d'une
  ligne, un signal visuel propre, autre chose - **ne m'appartient pas** : c'est `ines-design` pour la
  forme et `theo-archi` pour le mecanisme. Je juge le resultat en jouant. **Ce n'est pas un
  durcissement** : ma barre de s17 (une consigne d'une ligne) reste une facon suffisante de la
  tenir, et si l'equipe la retient, la condition est tenue sans autre travail.

**La reponse a la question precise qui m'est posee : que reste-t-il, comme exigence pedagogique de
ma part, du texte de deux phrases, une fois le rendu sorti de ma porte ?**

**Il reste un plancher, pas un plafond - et c'est l'inverse de ce que le regime precedent
produisait.** Le texte doit etre assez long pour porter **deux informations distinctes a relier**,
sans quoi `COMP-3` est inatteignable et le paquet retombe dans le reperage litteral que j'ai refuse.
Deux phrases sont le minimum vraisemblable ; je ne fixe pas de nombre, parce qu'un nombre est
exactement le genre d'exemple que la regle de la section 4 m'interdit desormais d'ecrire a la place
d'une propriete.

Le plafond, lui, n'est plus vertical. **Il redevient ce qu'il aurait toujours du etre : la charge de
lecture d'un CM1**, et il se juge en jouant, pas en mesurant - le texte se lit d'un trait sans que
l'enfant perde le fil, et le vocabulaire est de son niveau. C'est la meme exigence que `D-8` sur les
durees, et elle vit dans ma porte depuis le premier sprint.

**Une propriete de cette story, et d'elle seule, que je nomme sans en faire un motif.** Pour cette
competence-ci, la position du texte n'est pas un confort : si l'enfant ne peut pas **revenir au
texte** pendant qu'il repond, l'inference qu'on lui demande devient un exercice de memoire, et ce
n'est plus la competence annoncee. **Ma propriete n'est pas "visible sans defiler" - c'est
"relisible"** : un texte qu'on rejoint en faisant defiler la page satisfait ma propriete ; un texte
qu'on ne peut pas rejoindre du tout ne la satisfait pas. La distinction est reelle et le backlog la
porte deja (`REP-8` : *"l'indice est hors de vue, pas necessairement inatteignable"*, et l'etat avec
une barre de defilement visible n'a jamais ete mesure par personne).

**Ce que je fais de cette propriete, sans ambiguite** : **je ne refuserai pas dessus a s22.**
L'arbitrage couvre le rendu et je ne le contourne pas par un autre nom. Je l'ecris comme
`REP-8-CONSTAT` si je la vois manquer en jouant, et **je nomme, dans le Done pedagogique de `S17-2`
s'il est prononce, la propriete qu'il ne couvre pas** : *l'enfant peut avoir a memoriser le texte
pour repondre.* Une ligne de matrice qui passerait `couvert-valide` sans cette mention dirait
quelque chose de faux. **Et je demande a `sofia-sm` de porter cette seule question a l'utilisatrice
avant que les vingt items ne soient ecrits** (section 7) : c'est la seule des trois stories ou le
rendu et la competence ne sont pas separables, et savoir cela avant d'ecrire coute une question,
apres coute vingt items.

### 3.4 Trois de mes propres constats etaient perimes, et je les corrige avant de m'appuyer dessus

Je verifie ce que j'ai ecrit avant de l'opposer a quiconque. Trois faits enregistres par moi ne
tiennent plus.

**(a) Le garde-fou de rang sait desormais lire une duree. Toute la section 3.6 de mon arbitrage de
s13 est caduque.** J'y avais ecrit, apres avoir lu le code de l'epoque, que
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` extrayait l'entier de tete de chaque
proposition (`^-?\d+`), donc lisait `1 h 30` comme **1** et `45 min` comme **45**, et classait tout
item aux trois heures de tete egales en *"la plus petite"*. **Verifie aujourd'hui dans le code
courant, ce n'est plus vrai** : `lireValeur` essaie d'abord `dureeEnMinutes`, qui lit `1 h 30`,
`1 h 30 min`, `1 heure 30 minutes`, `2 h` et `45 min` **en minutes**, avant toute lecture de nombre
nu ; et sa Javadoc cite nommement `D-3` de mon arbitrage pour justifier qu'un instant du jour se lise
de la meme facon, aucun item du depot ne franchissant minuit. En outre, `rangDe` rend desormais
`null` quand deux valeurs coincident, au lieu de classer silencieusement l'item en *"la plus
petite"*, et l'exclusion silencieuse d'items hors du domaine est elle-meme gardee par
`LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest`, qui exige que tout paquet juge sur une partie
de ses items le declare avec le nombre exact d'items ecartes.

**Consequences, toutes dans le sens d'un allegement, aucune dans celui d'un durcissement :**

- **Je leve l'exigence de comptage manuel de `D-7`.** Elle demandait que le compte des rangs soit
  *"donne par qui ecrit le paquet, dans le rapport de la story, parce qu'aucun automate ne sait le
  calculer ici"*. **Un automate sait le calculer maintenant.** La barre elle-meme - aucun rang au-dela
  de 50 %, aucun sous 10 %, mesuree sur la valeur reelle - ne bouge pas d'un point ; c'est son moyen
  de verification qui devient la CI. Je recompterai par sondage, pas item par item.
- **Je retire la prevision de CI rouge** que j'avais donnee pour `S13-3` (*"un paquet de durees
  correctement ecrit fera rougir le garde-fou en affichant 100 % la plus petite"*). Elle etait juste
  a sa date, elle est fausse aujourd'hui, et laisser une equipe attendre un faux rouge qui ne viendra
  pas est une facon de lui faire ignorer un vrai.
- **Le point 5 de ma section 4 de s13** (*"le rang de `S13-3` est a ma garde seule, ici l'automate ne
  se contente pas d'etre aveugle, il se trompe"*) **est retire**. Il ne l'est plus.
- **Une seule chose reste a savoir avant d'ecrire, et c'est une information, pas une condition** :
  la lecture automatique ne connait que les ecritures ci-dessus. Si l'ecriture des durees retenue au
  titre de `D-2` en sortait (*"une heure et demie"*, par exemple), les items sortiraient du domaine
  du garde-fou - le depot l'exigerait alors declare, et `D-7` retomberait a ma charge. **`D-2` ne
  change pas** : le choix de l'ecriture appartient toujours a qui ecrit, c'est sa constance qui est
  ma condition. Je dis seulement ce que ce choix coute.

**(b) `cm1-perimetre` ne pousse plus son indice sous le pli sur la totalite de ses items.** Mes
conditions `MD-9` et `D-8` citaient toutes deux, en motif secondaire, que ce paquet poussait son
message d'erreur et son indice sous le pli *"a 360 x 640 sur 100 % de ses echecs"*. **Deux raisons
independantes rendent cette phrase caduque** : ma mesure du 2026-08-27 donne `perimetre-cm1` a
**1 item sur 22** apres le travail vertical de s21, et **360 x 640 n'est plus la reference du
produit depuis `DEC-0040`** (2026-08-22, cible ordinateur et tablette, reference 744 x 551). Je ne
declare pas ma mesure d'alors fausse : je declare la phrase perimee sur ces deux points, et sans
effet sur les conditions, dont ce n'etait pas le motif.

**(c) `COMP-4`, `COMP-6` et `COMP-7` etaient deja perimees sur leur reference avant l'arbitrage
d'aujourd'hui, et je ne l'avais pas ecrit.** Elles sont datees du 2026-08-19 et toutes trois
formulees a **360 x 640**. `DEC-0040` a change la reference du produit **le 2026-08-22**, trois jours
plus tard. **Elles ont donc vecu neuf jours dans une fiche de conformite, en citant une reference que
le produit avait abandonnee, sans que je les redresse.** C'est la meme famille que `SEG-1` : une
condition ecrite par moi qui reste vraie a l'oeil et fausse au fond parce que personne, moi comprise,
ne va la relire quand son fondement bouge. Elles tombent aujourd'hui pour un autre motif ; cela ne
m'exonere pas, et je l'ecris pour que la trace existe.

---

## 4. La regle proposee a la retro de s21 : je l'ADOPTE, avec sa borne et son cout

### 4.1 Ce que je tranche

La retro de s21 propose, a trancher par moi seule :

> *"Une condition pedagogique qui filtre un contenu (une clause de la forme 'exclure si...') est
> mesuree contre la population entiere qu'elle gouverne au moment ou elle est ecrite, pas seulement
> testee sur les tirages qu'elle rencontre au fil des paquets livres."*

**Je l'adopte.** Le fait qui la fonde est le mien et il est accablant : `SEG-1` clause (c),
ecrite par moi avant que le paquet existe, excluait **30 mots a tort sur 39 attrapes** sur le corpus
CP entier - `petit`, `cheval`, `renard`, `semaine`, `regarde`, `premier`, c'est-a-dire le coeur du
lexique de CP - et elle a survecu jusqu'a ce qu'un arbitrage de l'utilisatrice **sur un seul mot**
la fasse tomber par accident. Si `cheminee` n'avait pas ete tire dans ce paquet-la, la clause serait
encore vivante et encore fausse. **Une clause trop large qui n'attrape jamais un mot tire ne se
revele jamais**, et je n'ai aujourd'hui aucun moyen de savoir combien d'autres j'en ai ecrites.

`sofia-sm` a eu raison de me la renvoyer plutot que de la trancher : c'est ma methode de
specification, et le cout est le mien a porter.

### 4.2 Sa borne, parce qu'une regle sans borne ne s'applique pas

**Elle s'applique a une clause qui filtre une population enumerable et deja existante** : un corpus
de mots, un catalogue, un paquet livre, une liste de la reserve `contenu-repris/`. C'est le cas de
`SEG-1` : les 650 mots du corpus CP existaient, et la mesure etait a une commande.

**Elle ne s'applique pas telle quelle a une condition qui gouverne un contenu pas encore ecrit.**
`MD-1` (*aucun operateur autre que 10 et 100*) ou `D-1` (*deux formes, et seulement elles*) ne
filtrent aucune population : il n'y a rien a balayer, et exiger une mesure serait exiger l'impossible
- donc, en pratique, ne rien exiger du tout.

**Pour ce second cas, l'obligation de remplacement est celle que j'ai deja nommee contre moi-meme a
s21 :** la condition est ecrite comme une **propriete decidable item par item par un tiers**, jamais
comme un exemple ni comme une liste fermee, et la **population qu'elle gouverne est nommee** - avec,
si elle n'est pas enumerable, la mention explicite qu'elle ne l'est pas. **Une population non
enumerable non declaree est le meme angle mort que le taux calcule sur une partie des items sans
declaration** : le depot vient d'y mettre un garde-fou du cote du code (`ADR-0041`,
`LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest`) ; je me l'applique du cote de la
specification, ou il n'y en a aucun et ou il ne peut pas y en avoir.

### 4.3 Le cout, que j'evalue plutot que de l'esquiver

- **Clause sur population enumerable** : une passe de script sur le corpus, puis la lecture a la
  main de **tous** les cas attrapes, pas d'un echantillon. Ordre de grandeur mesure sur le seul cas
  que j'aie reellement traite : `SEG-1` contre 650 mots, 39 cas attrapes, 39 lus - fait en une
  session le 2026-08-28. **Le releve est commite**, ce qui est la condition pour qu'une instance
  neuve puisse le relire sans me reprendre (`squad/README.md`, section 6, amendement du 2026-08-28).
- **Condition sur contenu a ecrire** : cout nul en mesure, mais un cout de **redaction** - ecrire la
  propriete demande plus de soin qu'ecrire l'exemple, et c'est precisement ce que je n'ai pas fait
  trois fois sur quatre depuis s20.
- **Ce que la regle rapporte, et pourquoi je l'adopte malgre le cout** : ces trois cycles de refus
  ont coute, chacun, un refus prononce, un lot renvoye a un developpeur, une correction, une
  reaudition complete et une decision produit. **Payer une passe de corpus a l'ecriture est moins
  cher qu'un cycle de refus a la livraison**, et c'est directement ce qui rend le chiffre de la
  section 5 tenable.

### 4.4 Ce que son application aux trois paquets donne aujourd'hui

Je l'ai appliquee aux vingt-huit conditions des trois candidats.

- **Aucune des vingt-et-une conditions de `S13-2` et `S13-3` n'est une clause de filtrage sur une
  population enumerable.** Elles gouvernent toutes un contenu a ecrire. **La regle ne mord donc pas
  par sa clause principale ; elle mord par son obligation de remplacement**, et elle attrape
  **une** condition : `D-9`, qui enumerait trois exemples de situations exclues au lieu de nommer la
  propriete. Amendee en 3.2.
- **Aucune des sept conditions de `S17-2`** n'est un filtre sur une population enumerable non plus.
  `COMP-2` interdit les questions de culture generale et les distracteurs absurdes : c'est un
  jugement item par item, que j'ai deja rendu decidable en donnant sa procedure de verification
  (*masquer le texte, item par item*). Elle passe. `COMP-6` est reformulee en 3.3, pour la meme
  raison que `D-9`.
- **Ce que je ne peux pas faire, et que je dis plutot que de le laisser croire** : je n'ai **pas**
  rebalaye les conditions des paquets deja valides des sprints anterieurs. La regle vaut *"au moment
  ou la condition est ecrite"*, et je l'applique a partir d'aujourd'hui. **Il peut rester, dans mes
  specifications passees, d'autres clauses de la famille de `SEG-1`, et je n'ai aucun moyen de le
  savoir sans les rebalayer une par une.** Ce n'est pas dans mon debit de s22 (section 5) ; si
  `camille-po` veut ce balayage, c'est une story, et je la ferai.

---

## 5. Mon debit pour s22

### 5.1 Ma methode, et son perimetre

Je pars du chiffre existant et je ne l'invente pas. `DEC-0051`, section 5, tient **deux a trois
paquets atteignant un Done pedagogique par sprint**, etabli sur les six paquets audites en s19 et
s20, dont trois avaient exige un cycle de refus dans le sprint meme. Les faits observes depuis :

| Sprint | Paquets menes a un Done pedagogique | Cycles de refus dans le sprint |
|---|---|---|
| s19 | **4** | 1 (plus une reserve bloquante fermee au sprint suivant) |
| s20 | **2** | 1 |
| s21 | **3** | 3, sur deux paquets |

**Le 4 de s19 est le fait qui compte le plus** : il a ete atteint sous un regime **plus lourd** que
celui de s22, le pli etant alors une condition bloquante de ma porte. Il n'y a donc pas a extrapoler
pour poser 4 : il a deja ete fait, une fois, avec plus de contraintes.

**Ce que l'arbitrage m'enleve comme charge, chiffre avec sa methode et sa limite** : sur mes **dix
commits de s21**, **trois** sont entierement consacres au pli ou a la forme (`749244a` gel de la
police, `f701f37` bloc de message epingle, `3cfe9e3` refutation de `S21-3`), et **deux des quatre
points routes** d'un quatrieme (`18d1220`) le sont aussi - la mesure du catalogue entier a 478 items
et la remesure des trois paquets neufs. **Perimetre et limite de ce chiffre** : c'est un **compte de
commits sur le seul s21**, pas une mesure de temps ni de tokens, et des commits ne pesent pas le
meme poids. Il situe un ordre de grandeur - **environ un tiers de mon lot de s21** - il ne le
mesure pas. Je n'ai pas d'instrument pour mesurer mon propre temps, et je prefere le dire que de
fabriquer une precision.

**Ce que l'arbitrage ne m'enleve pas** : l'audit lui-meme, item par item - jouer, se tromper
volontairement, verifier chaque enonce, chaque reponse, chaque distracteur, chaque indice. C'est le
gros du cout, et il ne bouge pas.

**Ce que la regle de la section 4 ajoute** : un cout **en amont**, a l'ecriture des conditions. Pour
s22, il est **deja paye sur les trois candidats** : leurs vingt-huit conditions sont ecrites depuis
s13 et s17, et la relecture de ce document les a redressees. Un **quatrieme** paquet, lui, exigerait
un arbitrage prealable neuf, donc ce cout en entier.

### 5.2 Le chiffre

**Quatre paquets menes jusqu'a un Done pedagogique a s22.**

Ce que ce nombre suppose, et il tombe si l'une de ces conditions manque :

1. **Trois des quatre sont les candidats deja arbitres** - `S13-2`, `S13-3`, `S17-2`. Leur cout
   d'amont est paye. Un quatrieme paquet **sans arbitrage prealable ecrit** ne rentre pas dans ce
   compte : il faut m'ecrire l'arbitrage, et cela consomme la place que le quatrieme occupait.
2. **Les paquets arrivent echelonnes, pas tous le dernier jour.** Un paquet livre la veille de la
   cloture ne peut pas absorber un cycle de refus a l'interieur du sprint : c'est arithmetique, pas
   une preference. `sofia-sm` decide de l'ordre ; je demande seulement qu'il y en ait un.
3. **Chaque auteur ecrit dans le rapport de sa story les comptes que je devrais sinon etablir moi-
   meme** - repartition des familles, part des items portant chaque propriete de distribution,
   erreur nommee pour chaque distracteur. **Je recompte, je ne compte pas.** La difference entre
   verifier un compte et l'etablir est, sur un paquet de vingt-quatre items, de l'ordre d'un facteur
   trois de mon cote.
4. **Aucun arbitrage de forme ne m'est route**, et aucune mecanique neuve n'est ouverte. Les deux
   ont consomme s21, et le premier n'est plus dans ma porte.
5. **Au plus un cycle de refus complet** dans le sprint. C'est le taux observe a s19 et s20 ; s21 en
   a eu trois, tous imputables a mes propres conditions, ce que la section 4 traite a la racine.

**A quoi il retombe, pour que `sofia-sm` planifie sur une borne basse et pas sur un espoir** :

- **trois** si un cycle de refus complet survient sur l'un des paquets et qu'un quatrieme paquet non
  arbitre est ajoute au sprint ;
- **deux** si deux cycles de refus surviennent, ou si un arbitrage de forme neuf m'est route, ou si
  les quatre paquets arrivent dans les deux derniers jours.

**Je ne promets pas cinq.** Le maximum jamais atteint sur ce produit est quatre, et je n'ai aucune
raison mesuree de croire que la sortie du pli en achete un cinquieme plutot que de la marge sur les
quatre. **L'utilisatrice demande la couverture maximale ; la facon de l'obtenir n'est pas que
j'annonce un chiffre que je n'ai jamais tenu, c'est que les quatre arrivent tot et complets.**

### 5.3 Le cout relatif des trois candidats, pour que `sofia-sm` ordonne la livraison

Ce classement est une estimation de ma part, fondee sur la nature des conditions, pas une mesure.

| Paquet | Cout d'audit | Pourquoi |
|---|---|---|
| **`S13-2`** (multiplier et diviser par 10 et 100) | **le plus faible** | Chaque resultat se recalcule de tete. Le rang est desormais mesure par la CI. Il reste la plausibilite des distracteurs, l'indice, la distribution des quatre familles. **A livrer en premier.** |
| **`S13-3`** (problemes de durees) | **moyen** | Chaque duree se recalcule, mais `D-2` (instant contre duree), `D-5` (franchissement de l'heure sur le bon tiers) et `D-9` (situations) se verifient item par item, a la main. |
| **`S17-2`** (comprehension CM1) | **le plus eleve, de loin** | `COMP-1` et `COMP-2` demandent **deux lectures par item, texte masque puis question masquee** : vingt items, quarante epreuves, avant meme de jouer. `COMP-3` demande de trancher, sur un tiers des items, si la reponse se lit dans une seule phrase. **A livrer en premier ou en deuxieme, jamais en dernier** : s'il arrive tard, il ne pourra pas absorber une correction. |

---

## 6. Sur quelles proprietes je reste la seule garde, apres l'arbitrage

L'equipe doit savoir ce qui n'est couvert par aucun automate, sinon elle croira couvert ce qui ne
l'est pas. La liste **raccourcit** d'un cote et **s'aggrave** de l'autre.

**Ce qui sort de cette liste** (pour la premiere fois depuis que je la tiens) :

- **le rang de la bonne reponse sur un paquet de durees** : la CI le mesure correctement
  desormais - voir 3.4(a) ;
- **la position de l'indice apres un echec** : plus personne ne la garde. **Ce n'est pas qu'elle est
  couverte : c'est qu'elle est assumee comme dette par l'utilisatrice.** La difference est
  importante et personne ne doit la lire comme une fermeture.

**Ce qui y reste, entierement, et sans aucun garde-fou executable** :

1. **L'exactitude de chaque enonce, de chaque reponse, de chaque duree, de chaque produit.**
   La porte technique etait verte a 722 tests a chacun des trois refus de s21 et n'en a vu aucun.
2. **La plausibilite de chaque distracteur** et l'unicite de la reponse defendable.
3. **Qu'un indice n'enseigne pas une regle fausse.**
4. **Que le proxy ne contourne pas la competence** - `COMP-1`, `COMP-2`, `COMP-3`.
5. **Que deux ecritures - un instant, une duree - ne se confondent jamais** (`D-2`).
6. **Les distributions sur un paquet entier** : les quatre familles de `MD-2`, la retenue de 60 de
   `D-5`, le quart de `MD-3`, la moitie de `D-4`, le tiers de `MD-6`.
7. **Les biais de genre, d'origine, de milieu et de configuration familiale.** Rappel de s21 : mes
   propres conditions n'ont pas attrape l'absence d'adulte masculin dans `cp-mot-manquant` ni les
   deux taches menageres confiees aux deux personnages feminins. **C'est un manque de ma
   specification, pas de celui qui l'appliquait.**
8. **Que le niveau est le bon** : lisibilite, charge cognitive, progression sur une partie entiere.
   Cela ne se lit pas, cela se joue.
9. **Ce qu'un lecteur d'ecran annonce** : jamais couvert par aucun de mes Done, transfere hors de la
   squad a s17, et toujours pas couvert.
10. **Qu'un enfant de neuf ans comprenne qu'il peut faire defiler la page** pour rejoindre un texte
    ou un indice hors de vue. Le backlog nomme deja ce jugement comme mien et non mesurable par
    l'outillage. **Il l'est encore moins aujourd'hui** : l'arbitrage retire la mesure, il ne fournit
    pas l'enfant.

---

## 7. Ce que je route, et qui n'est pas de moi

| Suite | Destinataire | Ce que je demande |
|---|---|---|
| **La portee exacte de l'arbitrage sur le verdict et sur les propositions** (section 1.4, precisions 1 et 2). Je retiens que `REP-8` porte sur l'indice, et que "l'enfant ne voit pas s'il a eu raison" reste un motif de refus | `sofia-sm`, puis l'utilisatrice si elle le juge utile | Confirmer ou corriger ma lecture. **Je n'attends pas cette reponse pour travailler** : ma lecture s'applique en attendant, elle est ecrite, et un tiers peut la contredire sans me la redemander |
| **`S17-2` : le rendu et la competence n'y sont pas separables** (section 3.3). Un texte qu'on ne peut pas rejoindre pendant qu'on repond transforme l'inference en exercice de memoire | `sofia-sm`, puis l'utilisatrice | Poser **cette seule question** avant que les vingt items ne soient ecrits. **Je ne refuserai pas dessus**, quelle que soit la reponse ; je demande a savoir si le Done devra porter la mention de non-couverture ou non. Une question aujourd'hui, ou vingt items ecrits contre un statut inconnu |
| **`REP-8` reste ouverte, sa dette croit, et mon compte du 2026-08-27 se perime** (section 2) | `camille-po` | Ne jamais citer les 64 items au present. La dette courante se lira par `grep 'REP-8-CONSTAT' squad/conformite/*.md`, pas par une remesure |
| **`COMP-4`, `COMP-6`, `COMP-7` de `FICHE-bandit-manchot-comprehension-cm1.md` sont a mettre a jour**, et je ne touche aucune fiche dans ce lot | `camille-po` | Reprendre la section 3.3 de ce document. `COMP-4` et `COMP-7` tombent ; `COMP-6` est remplacee par la propriete que j'y ecris ; `COMP-5` reste satisfaite et close |
| **Le blocage que j'avais pose sur `S17-2` est leve** (*"marc-dev ne peut pas ecrire les vingt items"*) | `camille-po`, `sofia-sm`, `marc-dev` | La story est developpable a s22. Mon avis de s17 ne doit plus etre lu comme un veto |
| **Ma section 3.6 de l'arbitrage de s13 est caduque**, la prevision de CI rouge sur `S13-3` avec elle | `theo-archi`, `yanis-qa`, `sofia-sm` | Ne pas provisionner de travail sur l'obstacle que j'avais remonte a s13 : il est traite. `D-7` est desormais mesuree par la CI |
| **Le rebalayage de mes specifications anterieures** contre la regle de la section 4 (autres clauses de la famille de `SEG-1`) | `camille-po` | Ce n'est pas dans mon debit de s22. Si elle le veut, c'est une story, et je la ferai |
| **Deux motifs de story que j'avais ecartes sans les condamner a s13** : multiplier par 1000 et par les multiples composes ; retrouver l'heure de depart | `camille-po` | Inchange, rappele pour que le compte ne se perde pas |

---

## 8. Ce que ce document ne fait pas

- **Il ne prononce aucun Done pedagogique**, et n'en anticipe aucun. Les trois candidats seront
  juges en jouant, et un paquet qui tient toutes mes conditions peut encore etre refuse pour ce que
  seul le jeu montre.
- **Il ne modifie aucune fiche de conformite**, aucun contenu, aucun fichier sous `domain/`,
  `application/`, `adapters/` ou `bootstrap/`. J'ai **lu** le garde-fou de rang pour verifier un
  fait ; je n'ai rien ecrit.
- **Il n'efface aucun verdict**, y compris mes deux arbitrages de forme de s21 (`749244a`,
  `f701f37`) que l'arbitrage d'aujourd'hui rend sans objet : ils gardent leur date et leur motif.
  Un refus efface n'a aucune valeur de preuve.
- **Il ne durcit aucune condition existante.** Les deux reformulations (`D-9`, `COMP-6`) remplacent
  un exemple par la propriete qu'il servait ; les trois levees (`COMP-4`, `COMP-7`, le comptage
  manuel de `D-7`) allegent. **Aucune barre n'est relevee apres coup.**
- **Il ne fixe ni le perimetre de s22, ni la liste des paquets, ni leur ordre** : c'est
  `camille-po` et `sofia-sm`. Je donne un debit et un classement de cout ; ils en font un planning.
- **Il ne cree ni le repertoire de sprint, ni `planning.md`, ni aucune story.**

---

## 9. Addendum du 2026-08-28, apres decouverte de `DEC-0053` - une divergence, et une correction contre moi

**Ce que j'ai trouve, et quand.** Apres avoir commite les sections 1 a 8, j'ai trouve dans l'arbre
de travail `DEC-0053` de `camille-po`, non commite au moment ou j'ecris, qui trace les memes
arbitrages d'ouverture de s22. Elle l'a ecrite en parallele de moi, sans que nous nous soyons lues.
**Elle diverge de moi sur deux points. Sur l'un, elle a raison contre moi ; sur l'autre, ni elle ni
moi ne tranchons.** J'ajoute plutot que je ne reecris : les sections 1 a 8 restent telles qu'elles
ont ete commitees, et cet addendum dit ou elles sont a corriger.

### 9.1 La correction contre moi : mon debit passe de quatre a trois

**Ma section 5 s'appuie sur un compte faux, et c'est `camille-po` qui l'a etabli correctement.**

J'ai ecrit *"le 4 de s19 est le fait qui compte le plus : il a ete atteint sous un regime plus lourd
que celui de s22"*, en m'appuyant sur `DEC-0051` section 5 (*"quatre paquets ont ete audites dans le
sprint"*). **J'ai compte des paquets audites la ou la metrique est le Done pedagogique atteint dans
le sprint.** Ce ne sont pas les memes.

**Verification faite par moi contre la source primaire, et non contre le resume** :
`FICHE-bandit-manchot-nature-mot-cp.md` porte, pour le 2026-08-24 (s19), *"VALIDE SOUS RESERVE -
une reserve BLOQUANTE, `NAT-5`"*, et son Done n'est prononce que le **2026-08-25**, hors sprint.
**Le quatrieme paquet de s19 n'a donc pas atteint de Done dans s19.** Le compte juste est celui de
`DEC-0053` : **s19 = 3, s20 = 2, s21 = 3**, moyenne 2,67, plafond deja observe **3**. Mon tableau de
la section 5.1 porte 4 pour s19 : **il est faux sur cette ligne**, et l'argument central de mon
chiffre reposait dessus.

**Je corrige mon debit : trois paquets menes jusqu'a un Done pedagogique a s22**, et je m'aligne
sur la borne de `DEC-0053`. Les cinq conditions de ma section 5.2 restent, mot pour mot : elles ne
soutenaient pas le passage de trois a quatre, elles soutiennent le fait de tenir trois plutot que de
retomber a deux.

**Ce que je maintiens de mon raisonnement, et qui n'est pas un repli deguise** : un lever
structurel existe reellement a s22 et n'a jamais existe avant - **les vingt-huit conditions des
trois candidats sont ecrites depuis s13 et s17**, alors qu'a s21 j'ai ecrit les vingt-sept
conditions des trois paquets **et** audite les trois dans le meme sprint. Ce levier ne suffit pas a
poser un quatrieme paquet, pour la raison que `camille-po` nomme et que j'accepte contre moi :
`MD-1` a `MD-10` et `D-1` a `D-11` ont ete ecrites **par categorie de raisonnement, jamais
confrontees a un contenu reel** - c'est exactement le mecanisme de `SEG-1`, et rien ne garantit
qu'elles survivent intactes au premier paquet ecrit contre elles. **Un quatrieme paquet ne devient
possible que si les trois aboutissent sans aucun cycle de refus, et il n'existe aujourd'hui aucun
quatrieme candidat dont les conditions soient deja ecrites.** Donc : trois.

### 9.2 La divergence de portee, que je ne tranche pas et que je n'oppose pas

`DEC-0053`, arbitrage 1, ecrit que je ne prononce plus de `REFUSE` sur le motif *"un element du jeu
(indice, verdict, proposition) reste hors de vue sans defiler"* - **l'indice, le verdict, et tout
autre element du meme bloc**. **Ma section 1.4 retient la lecture etroite** : `REP-8` porte sur
l'indice, et je gardais le verdict et les propositions.

**Ce que je fais, et je le dis sans ambiguite : je n'oppose pas ma lecture a la sienne.** Le
perimetre d'un arbitrage de l'utilisatrice appartient a l'utilisatrice et sa trace au produit, pas a
moi. **Si `DEC-0053` est commitee telle qu'elle est ecrite, la lecture large s'applique et je
l'applique** - je ne refuserai ni sur le verdict, ni sur l'indice, ni sur une proposition hors de
vue. Ma section 1.4 cesse alors de decrire ma porte et ne vaut plus que comme la question que j'ai
posee.

**Ce que je demande en echange, et c'est la seule chose que je demande** : que la consequence soit
ecrite plutot que decouverte. Sous la lecture large, **un jeu ou l'enfant presse un bouton et ne
recoit aucun retour visible peut obtenir un Done pedagogique de moi** - c'etait le motif exact de
mon `REFUSE` de `cm1-reperes` a s20 (*"l'enfant presse un bouton et ne voit pas s'il a eu raison"*),
et il ne serait plus un motif. **Je le nommerai dans chaque Done concerne** comme propriete non
couverte, au meme titre que ce qu'un lecteur d'ecran annonce. C'est le prix de l'arbitrage, il est
peut-etre le bon prix pour une livraison test, et il doit etre lisible par celle qui l'a paye.

**Un seul point ne depend d'aucune des deux lectures**, parce qu'il n'appartient pas a ma porte
pedagogique : **je ne peux auditer que ce que je peux jouer.** Un ecran dont aucune proposition
n'est atteignable ne me donne pas un increment refusable, il me donne un increment non evaluable, et
c'est le Done technique qui manque alors, pas le mien (`squad/README.md`, section 7). Ce n'est ni
une reserve de pouvoir ni un motif de repli : c'est la meme phrase que ma fiche de role porte depuis
le premier sprint.

**Route a `sofia-sm`** : la ligne de ma section 7 sur la portee est **fermee par `DEC-0053`** des
qu'elle est commitee, et n'a plus a etre reposee a l'utilisatrice. Reste ouverte, elle, la question
de `S17-2` (le rendu et la competence n'y sont pas separables) : celle-la n'est pas une question de
portee, c'est une question de ce que le Done devra dire.
