# Arbitrage de sortie sur `EMC-CP-07` - `BIN-1`, la sortie (a), et c'est l'item 4
# Plus ce que la tache B de `cp-emc-exclusion` doit rendre a l'enfant

**`nadia-prof`, 2026-09-10.** Arbre `round-two` a `8f37bc6b`. **Rendu avant que `marc-dev` n'ouvre
son worktree**, comme le planning de s34 le demande (section 8, point 2).

**Ce document est un arbitrage, pas un verdict.** Aucun Done pedagogique n'y est prononce, aucune
ligne de matrice ne bouge, et rien de ce que j'ai ecrit les 2026-09-07, 09-08, 09-09 et 09-10 n'est
efface ni rejuge.

---

## 0. Mode d'audit, declare avant l'arbitrage

- **Joue** : deux parties completes de dix manches, avec erreurs volontaires, le 2026-09-10 sur
  l'arbre `b45e5927` - `bandit-manchot-regle-souhait-loi-cp` (releve dans
  `squad/sprints/s33-plan-2-tenu-le-palier-2-differe-a-s34/verdicts-nadia-instance-B.md`,
  sections 7 et 8) et `bandit-manchot-exclusion-cp` (arbitrage prealable de s34, section 5.5).
- **Je n'ai pas rejoue pour cet arbitrage, et je dis pourquoi je n'en avais pas besoin** :
  `git diff b45e5927..8f37bc6b` sur les deux paquets servis, sur `catalogue.yaml` et sur les deux
  fichiers de test de paquet **est vide**. **Ce que j'arbitre est exactement ce que j'ai eu a
  l'ecran.** Je l'ecris parce qu'un audit sur fichier qui ne se declare pas est un audit degrade
  cache, et parce qu'un tiers doit pouvoir refaire cette verification en une commande.
- **Lu sur fichier, et declare comme lecture** : `PaquetCpEmcRegleSouhaitLoiTest`,
  `PaquetCpEmcExclusionTest`, `ListContentSource.next`, l'entree de catalogue des deux jeux.
- **La reprise des deux tests par `theo-archi` n'est pas dans `8f37bc6b`.** Je n'arbitre pas sur
  elle : j'arbitre sur mes clauses et sur le paquet servi. Son avis, sections 1 a 1.6, est lu.

---

## 1. Ce que je confirme, et ce que je ne rejuge pas

**`theo-archi` a mesure ce que j'avais etabli, et sa mesure va plus loin que la mienne** : elle
etablit qu'aucun **troisieme** garde-fou ne mord sur la sortie, ce que je n'avais pas verifie. Rien
de ce que j'ai ecrit le 2026-09-10 ne bouge : `FER-1`, `EMC-11`, `EMC-5` et `PLA-1` restent ce
qu'elles sont, et ce sont les deux tests qui mesuraient autre chose que les clauses.

**Le fait de droit de sa section 1.6, reverifie par moi sur les onze items servis, item par item** :

- **item 4**, *"En France, le casque à vélo est obligatoire avant douze ans"* - **porte une
  obligation legale**, deja servie, deja verifiee exacte a trois passages ;
- **item 5**, *"À la cantine, chaque élève doit attendre son tour pour se servir"* - regle d'ecole,
  **aucune loi** ;
- **item 6**, *"Aya aimerait que la maîtresse donne moins de devoirs le week-end"* - souhait,
  **aucune loi**.

**Il a raison sur les trois.** Deplacer l'item 5 ou l'item 6 obligerait a inventer un fait de droit,
donc a payer le prix de (b) **en perdant en plus un item de la tache A**. Cette branche est fermee,
et elle l'est pour un motif de contenu, pas de cout.

---

## 2. La sortie retenue : **(a)**, et l'item deplace est **l'item 4**

### 2.1 Le fait qui decide, et ce n'est pas la marge

**`theo-archi` conclut (a) sur la marge de `BIN-1` - un demi-item contre zero. Sa conclusion est la
bonne ; son motif n'est pas le mien, et le mien est plus dur.** Une marge est un argument de
robustesse : elle dit ce qui arrivera au prochain changement. **`BIN-1` ne parle pas du prochain
changement, elle parle de la partie d'aujourd'hui** - son texte est *"une partie ne se joue pas
majoritairement a pile ou face"*, et le paquet n'y est l'unite de mesure que **parce que le tirage
est presque exhaustif**.

**Alors j'ai compte les parties, pas les items.** *Methode : `ListContentSource.next` tire
uniformement parmi les items **non deja vus** ; une partie de dix manches presente donc **dix items
distincts**, et l'entree de catalogue de ce jeu porte `nombreDeManches: 10`. Lu dans le code et dans
le catalogue a `8f37bc6b`, pas suppose.*

| | **(a)** 11 items, 5 binaires | **(b)** 12 items, 6 binaires |
|---|---|---|
| Items non tires dans une partie | 1 | 2 |
| Manches binaires possibles sur 10 | **4 ou 5** | **4, 5 ou 6** |
| Une partie majoritairement a pile ou face est-elle possible ? | **non, jamais** | **oui** |
| Frequence de ce cas | - | les deux non tires tous deux ternaires : **15 tirages sur 66, un peu plus d'une partie sur cinq** |

> **Ce que ce compte etablit : (b) satisfait l'arithmetique de `BIN-1` sur le paquet et reproduit,
> une partie sur cinq environ, exactement la partie sur laquelle j'ai ecrit `BIN-1`** - dix manches
> de `bandit-manchot-melanges-cm1`, dont sept de classement et six a deux propositions. **(a) ne le
> peut pas, dans aucun tirage.**

**Ce n'est donc pas un ecart de robustesse entre deux sorties conformes : (b) est conforme au compte
et infidele au motif.** C'est le meme genre d'ecart que celui qui a coute ce sprint - un chiffre
juste qui mesure autre chose que ce qu'il prescrit. **Je ne le repete pas dans l'autre sens.**

### 2.2 Et l'item 4 est le bon item pour un motif pedagogique, pas seulement parce qu'il ne coute rien

Le casque a velo est **la seule des trois situations que l'enfant de CP rencontre dans sa propre
vie**, et la seule ou il a deja vecu ce que la seconde moitie du libelle enseigne : une obligation
qui ne vient ni de la maitresse ni de ses parents, et a laquelle **personne parmi ceux qu'elle nomme
n'echappe**, pas meme celui dont les parents ne la connaissent pas. **C'est le meilleur item de
tache B que ce paquet puisse porter**, et il est deja ecrit.

### 2.3 Ce que (a) coute, et que je paie sciemment

La tache A tombe a **cinq items, tous binaires**. Une partie y joue quatre a cinq manches a deux
boutons, contre trois aujourd'hui. **C'est le prix de `EMC-11`**, il etait dans la clause le jour ou
je l'ai ecrite, et je ne le decouvre pas ici. Un enfant qui choisit entre deux categories vraies
apprend plus qu'un enfant a qui l'on marque fausse une reponse vraie ; c'est tout l'objet du refus.

**Et je tranche la lecture que `theo-archi` me route plutot que de la laisser ouverte** (son avis,
fin de section 1.6) :

> **Datee du 2026-09-10.** `BIN-1` s'applique a ce paquet apres la sortie (a), **bien que la tache de
> classement y devienne minoritaire** (5 items contre 6). Son entree - *"une ligne dont la tache
> principale est un classement a deux categories"* - **qualifie la ligne, pas un decompte d'items** :
> `EMC-CP-07` est une ligne de classement dont la seconde moitie se sert autrement. **Une clause qui
> cesserait de s'appliquer au moment precis ou le paquet s'en approche ne garderait rien.**

---

## 3. La consigne, au format `RES-1`

**Je ne cree aucun identifiant de clause.** Ce qui suit **execute** `EMC-11` et `BIN-1` ; cela ne les
complete pas et n'ajoute aucune exigence. On la cite par sa section.

### 3.1 Constat

**Joue le 2026-09-10, manche 2, arbre `b45e5927`, contenu identique a `8f37bc6b`.** Item 4 servi :
*"En France, le casque à vélo est obligatoire avant douze ans. Est-ce une règle ?"*, trois boutons
*une loi*, *une règle qui vaut pour tous*, *un simple souhait*. **J'ai presse *une règle qui vaut
pour tous* et le jeu a repondu *"Ce n'est pas ça."*** Le meme ternaire non disjoint dort dans les
items 5 et 6, ou *une loi* n'est qu'un distracteur.

### 3.2 Objet - ce que l'enfant doit pouvoir faire une fois la consigne payee

**Deux objets, parce que le mouvement d'item en cree un second.**

> **O-1, sur la tache de classement.** *L'enfant choisit entre les deux categories que la premiere
> moitie du libelle lui demande de distinguer - une regle qui vaut pour tous, un simple souhait - et
> aucune de ses reponses vraies n'est marquee fausse.* Il n'y a plus de troisieme categorie a
> l'ecran, sur aucun item de cette tache.
>
> **O-2, sur l'item deplace.** *L'enfant y apprend que la loi du casque ne laisse dehors aucun des
> enfants qu'elle nomme - ni celui dont les parents l'ignorent, ni celui qui ne fait pas de
> competition, ni celui qui ne roule que cinq minutes.* **L'axe de la discrimination est *tous ceux
> que la loi nomme, sans exception* contre *seulement une partie d'entre eux*.**

### 3.3 Mode de defaillance - ce qu'une execution litterale et fidele produirait de faux

> **`FD-1` - opposer les enfants aux adultes sur l'item deplace. C'est un refus d'avance.**
> Ma propre phrase du 2026-09-10 dit *"sous la forme 'Une loi ... Qui doit ... ?'"*. Executee a la
> lettre sur le casque, elle donne *"Qui doit porter un casque ?"* avec *seulement les enfants* en
> bonne reponse contre *les adultes aussi*. **C'est vrai, c'est fidele a ma phrase, et cela apprend a
> un enfant de six ans que les lois sont pour les enfants et que les adultes en sont dispenses** -
> soit l'exacte croyance que la seconde moitie du libelle existe pour defaire, et l'inverse de ce que
> disent les cinq items voisins (*y compris les adultes*, *pas même les adultes*, *même adulte*).
> **Si je trouve cet axe a l'ecran, je refuse le cinquieme passage sans autre motif.**
>
> **Pourquoi je le nomme plutot que d'en faire une regle de plus** : l'item 11 sert deja une loi qui
> ne vise pas tout le monde (*seulement les adultes*), **je l'ai validee et je ne la rouvre pas**. Ce
> n'est donc pas *"aucune bonne reponse non universelle"* que je demande - ce serait deplacer un
> poteau. C'est **cet axe-la**, sur **cet item-la**, et l'objet `O-2` dit lequel le remplace.
>
> **`FD-2` - corriger l'item 4 seul.** Les items 5 et 6 portent le meme ternaire et aucun motif de
> refus ne les nomme ; une correction item par item laisse le defaut dormir a cote. *(Deja ecrit dans
> mon refus d'avance numero 6 de s33 et dans ma section 5.2 ; remis ici a sa place.)*
>
> **`FD-3` - ecrire le sixieme item de la tache B sans recompter `FAB-1c` sur six items.** La forme
> des propositions de la tache B a ete payee en deux sprints **sur cinq items** : un item ajoute a
> cette tache sans que le compte soit refait peut, a lui seul, rendre a nouveau la bonne reponse
> reconnaissable a sa forme. **C'est la troisieme lecture de `RES-3` - relire le paquet, pas
> l'item** - et c'est ici qu'elle mord.

### 3.4 Perimetre, mesure sur l'etat d'arrivee

**Ce que la story touche :**

| | |
|---|---|
| **Item 4** | quitte la tache A pour la tache B : enonce, question, deux distracteurs et indice reecrits. **Il est transpose, jamais supprime** |
| **Items 5 et 6** | *une loi* retire des propositions. **Enonces, bonnes reponses et indices inchanges** |
| **Items 1, 2, 3, 7 a 11** | **non touches** |

**Etat d'arrivee, compte** : **11 items** - tache A 5 (items 1, 2, 3, 5, 6), tache B 6 (items 7 a 11
plus le transpose). **Binaires : 5 sur 11.** Bonnes reponses de la tache A : trois *une règle qui
vaut pour tous*, deux *un simple souhait*.

**Conditions que l'etat d'arrivee doit encore satisfaire, comptees sur cet etat-la** : `EMC-11` ;
`EMC-5` sur les onze items ; `FER-1` sur les deux moities du libelle ; `FER-2` a 11 ; `FER-3` (5 et
6, minorite a 5, plancher 3) ; `BIN-1` a 5 sur 11 ; `DEC-0064` (11 items pour 10 manches, **au
plancher exact, inchange**) ; `PLA-1` sur les deux distracteurs neufs ; `FAB-1a` (six bonnes reponses
distinctes en tache B) ; **`FAB-1c` recomptee sur six items** ; `CAT-1` ; `ADR-0086`, clauses 1 et
3 ; `LON-1` et `LON-2`, executees par `ADR-0089`.

**Ce que je n'ai pas compte, dit comme non compte :**

- **la consigne du catalogue et le titre de l'entree.** Relus apres la sortie retenue : *"Lis bien,
  puis dis ce que c'est, ou qui doit la respecter."* nomme toujours les deux taches, et *"Règle,
  souhait ou loi"* reste juste - le paquet parle bien des trois, c'est la tache A qui cesse de les
  opposer. **Je ne demande aucun changement et je ne les ai pas mis dans le prix** ;
- **la reprise des deux tests**, qui n'est ni de mon perimetre ni de mon prix ;
- **les trois acquis de s31-s32 que la story ne rejoue pas** : les cinq faits de droit, la forme des
  propositions de la tache B acquise par `FAB-1c` sur les items 7 a 11, les onze longueurs sous
  `LON-1`. **Trois choses payees en deux sprints ne se rejouent pas pour reparer un item.**

**Ce que je ne fournis pas, et pourquoi** : l'enonce, la question, les propositions et l'indice de
l'item transpose. **Je ne les ai pas recolles a leur place** - `RES-2`, interdit 1. J'ecris l'axe que
l'item doit tenir, pas ses mots. **Le fait de droit reste celui qui est deja servi ; s'il est
reformule, je le reverifie a l'ecran contre sa source.**

### 3.5 Regime et prix

**Inchange : rejeu, 0,5.** La sortie (a) ne touche pas plus de cinq items et ne reecrit aucun enonce
de la tache B existante : **`PAR-15` ne s'applique pas.** **Attente de fermeture : 1**, conditionnee
comme en section 5.2 de mon arbitrage prealable - *si le prealable technique est obtenu avant
l'ouverture de la branche, 0 sinon*.

---

## 4. `S34-EXC-TACHEB` - ce que la tache B de `cp-emc-exclusion` doit rendre

**Ma decision de forme du 2026-09-10 (arbitrage prealable, section 4.4) tient. Je la corrige sur deux
points avant qu'elle ne soit executee, et je ne l'efface pas.**

### 4.1 Deux corrections a ma propre decision, trouvees en la relisant contre le paquet et contre son test

> **`C-1`, datee du 2026-09-10.** Ma phrase *"ne pas se taire, ne pas rendre le coup, et ne pas s'en
> remettre a un camarade"* **nomme la competence. Lue comme une liste de distracteurs, elle casse
> `CAT-1`** : *rendre le coup* s'ecarte au ton sur cinq items sur cinq, et c'est exactement le motif
> pour lequel j'ai fait retirer le bouton *"à personne, ..."* de cette meme tache. **Les deux autres
> propositions doivent etre des conduites qu'aucun enfant ne classe comme vilaines** : l'item se
> decide alors sur *"est-ce que cela fait cesser la moquerie qui se repete ?"*, jamais sur
> *"laquelle a l'air sage ?"*.
>
> **Portee de mon interdit de s31 sur *"à personne"*, dite pour qu'on ne me la fasse dire ni trop
> large ni trop etroite** : il visait **un bouton ecartable au ton**, pas toute conduite qui ne nomme
> pas d'adulte. Une conduite du genre *attendre que cela s'arrete* n'est pas couverte par cet
> interdit ; **elle reste jugee par `CAT-1` et `PLA-1`, a l'ecran, par moi.** Je ne l'autorise pas
> d'avance et je ne l'interdis pas d'avance.

> **`C-2`, datee du 2026-09-10, et c'est la plus lourde.** **Mon objet, execute a la lettre, donne
> cinq items dont la bonne reponse est la meme conduite.** `FAB-1a` sanctionne exactement cela : *une
> tache dont tous les items ont la meme bonne reponse compte pour **un** item au titre de `FER-2` et
> de `FER-3`*. Le paquet tomberait a **sept items comptables sur dix requis** - **le defaut meme qui
> a refuse ce paquet au premier passage**, et que j'ai vu a l'ecran ce jour-la : *"des la deuxieme
> manche, l'enfant presse le bouton sans lire"*.
>
> **Donc : la bonne reponse varie d'un item a l'autre de la tache B**, sans que la discrimination
> cesse d'etre une conduite contre une conduite.
>
> **Ce que j'ai verifie et ce que je n'ai pas verifie.** J'ai verifie qu'une forme existe ou la bonne
> reponse varie tout en gardant l'objet - l'adulte que la situation rend present change d'un item a
> l'autre, pendant que les deux autres propositions restent des conduites sans adulte. **Je n'ai pas
> verifie que cette forme satisfasse en meme temps `EXC-R7`** - *l'enfant ne doit pas pouvoir gagner
> en appariant deux mots identiques* - puisque nommer l'adulte dans la reponse pendant que l'enonce
> installe le lieu est precisement ce qui a produit `EXC-R7`. **Si `marc-dev` trouve les deux
> conjointement insatisfaisables, il me le route avant d'ecrire, et il n'arbitre pas seul.** C'est ce
> que `lea-dev` a fait a s33 sur `EMC-CP-07`, c'etait le bon geste, et cela a evite un contournement
> qui aurait rendu quatre jobs verts sur un contenu faux.

### 4.2 L'objet, le mode de defaillance et le perimetre

- **Objet** : celui de la section 4.4 de mon arbitrage prealable - *devant une moquerie qui se
  repete, l'enfant reconnait, parmi trois conduites qu'un enfant de son age a reellement, celle qui
  consiste a en parler a un adulte* - **plus** `EXC-R7` (aucun item ne se gagne en appariant deux
  mots) et `EXC-R8` (la question recollee a chacune de ses trois propositions forme une phrase
  francaise).
- **Mode de defaillance** : les deux de la section 4.4 - **garder trois adultes et changer une
  troisieme fois la question**, et **payer les reserves une par une sans relire le paquet
  assemble** - **plus `C-1` et `C-2` ci-dessus**, qui sont des modes de defaillance de ma propre
  consigne.
- **Perimetre** : inchange, section 5.5 de mon arbitrage prealable. Les cinq items de la tache B en
  entier, plus l'item 2 de la tache A. **La consigne du catalogue ne bouge pas.**
- **Prix** : inchange, **1,0**, tarif de lot.

### 4.3 Un fait que je dois signaler sur le referentiel, et que je ne corrige pas moi-meme

`EMC-CP-03` dit *"savoir a quel adulte en parler"*. **Dans une mecanique a trois boutons, cette
formulation n'a pas de reponse unique** : a l'ecole, plusieurs adultes conviennent, et tout item qui
en credite un seul marque fausse une reponse vraie (`EMC-5`). **Ce n'est pas un referentiel faux** -
la competence existe et elle est reelle ; c'est **la mecanique qui ne peut pas servir sa lettre**. Ce
que le paquet sert alors est *en parler a un adulte plutot que se taire*, ce que je tiens pour la
competence effective au CP. **Je le route a `camille-po` via `sofia-sm`** ; je ne modifie aucun
referentiel et je ne rouvre pas `EMC-CP-03`, qui reste `couvert-valide`. **Ce que la ligne couvrira
apres la refonte est a redire dans ma fiche au prochain audit, et c'est mon travail, pas celui de
`marc-dev`.**

### 4.4 Quatre tests de `PaquetCpEmcExclusionTest` sont plus durs que mes clauses - symptome nomme, cause a `theo-archi`

**C'est le meme defaut qu'`EMC-CP-07`, sur le paquet d'a cote, dans le meme sprint et pour le meme
developpeur. Il a coute un sprint la premiere fois. Je le nomme avant l'ouverture de la branche.**
*Lu dans le fichier de test a `8f37bc6b`, non execute.*

| Methode | Symptome |
|---|---|
| `laTacheDAdulteNeSertPlusDeCandidatAuRabais` | exige que **chaque distracteur** de la tache B soit l'un des trois adultes litteraux. **Ma condition de s31 etait *"pas de bouton ecartable au ton"*, pas *"tout distracteur est un adulte"*.** Le test interdit donc l'objet meme de la refonte |
| `laTacheDAdulteRepresenteLesTroisAdultesEnBonneReponse` | exige les **trois adultes litteraux** en bonne reponse. Ce que `FAB-1a` demande est que la bonne reponse **varie**, jamais qu'elle prenne trois valeurs nommees d'avance |
| `chaqueItemAppartientAExactementUneDesDeuxTaches` | classe un item par l'appartenance de sa bonne reponse a deux ensembles de chaines litterales. Une bonne reponse qui est une conduite n'appartient a aucun des deux |
| `fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems` | compte les taches par ces memes ensembles litteraux : apres la refonte, la tache B compterait **zero** item |

**Je ne dis ni la forme du test, ni comment le reecrire, ni si les quatre doivent etre repris.**
`theo-archi` mesure et tranche, comme il vient de le faire sur `EMC-CP-07`.

### 4.5 Correction a ma section 4.4, apres la mesure de `theo-archi` du 2026-09-10 - **ils sont trois, et le quatrieme est pire**

**Sa mesure est commitee en `909d7029`. Elle me corrige sur un point, et l'effet reel est plus mauvais
que celui que j'avais decrit. Je la reprends ici sans effacer la section 4.4, qui reste lisible avec
sa faute.**

> **Ce que j'ai ecrit** : *quatre* methodes plus dures que mes clauses, dont
> `laTacheDAdulteNeSertPlusDeCandidatAuRabais`, *"l'ecart le plus large"*, qui *"interdit donc
> l'objet meme de la refonte"*.
>
> **Ce que sa sonde mesure** : **7 tests, 3 echecs.** `laTacheDAdulteNeSertPlusDeCandidatAuRabais`
> **ne rougit pas** : sa boucle parcourt `itemsDAdulte()`, qui ne rend plus aucun item apres la
> refonte, et son corps ne s'execute jamais. **Il n'interdit pas la refonte : il cesse d'exister
> apres elle.**

**J'avais raison sur l'ecart et faux sur son effet.** Un test qui bloque se voit : il rougit, on le
lit, on le tranche. **Un test qui devient vert sur une population vide laisse passer la refonte en ne
gardant plus rien, et aucun rouge ne le dit.** C'est la famille de defaut que je nomme moi-meme a
chaque verdict quand je demande si un balayage examine bien des objets ; je ne l'ai pas vue ici parce
que j'ai lu le fichier au lieu de le mesurer, et c'est la limite que ma section 4.4 declarait.

**Ce que cette correction change pour moi, et c'est le point a retenir** : cette methode est celle qui
encodait **ma** condition de s31 - le bouton *"a personne"* ne revient pas. Apres la refonte, **cette
condition n'est plus gardee par rien, silencieusement**. **Je redeviens donc la seule garde du
non-retour de ce bouton**, et je l'ecris pour que personne ne le croie couvert. La propriete que je
veux voir gardee est celle que `theo-archi` a deja formulee - *aucune proposition du paquet, dans
aucune des deux taches, n'est ce bouton* - **et sa forme lui appartient, comme toujours.**

### 4.6 Les trois questions pedagogiques que `theo-archi` me laisse - je les tranche ici

**Il les nomme en section 5 de son avis et il a raison de ne pas les prendre.**

**`Q1` - `C-2` et `EXC-R7` sont-elles satisfaisables ensemble ? Oui.** *Verifie en relisant les onze
items servis, pas a l'ecran : la refonte n'existe pas encore, et je le redirai devant l'ecran.*
**`EXC-R7` porte sur l'identite de deux mots, pas sur la presence d'un adulte dans la reponse.** Ce
qui l'a produite est le couple *"devant le surveillant"* dans l'enonce et *"a un surveillant de la
cour"* en bonne reponse. Elle est donc tenue des lors que **l'enonce installe le moment ou le lieu
sans employer le mot que la bonne reponse emploie** : l'enfant doit alors savoir quel adulte est la
au moment decrit, ce qui est une inference et non un appariement.

**Et j'ajoute la condition qui va avec, parce qu'elle n'est pas neuve et qu'elle serait manquee** : si
la bonne reponse est, sur les cinq items, **la seule proposition qui nomme un adulte**, l'item se
gagne a la forme sans la competence. **C'est `FAB-1c`, deja opposable, recomptee sur la tache
refondue - ce n'est pas une barre neuve.** La sortie est donc qu'une part des items porte un
distracteur qui nomme lui aussi un adulte, tout en restant une conduite qui ne fait pas cesser la
moquerie.

**`Q2` - le degre de variation exige de la bonne reponse. Je ne pose aucun seuil, et je dis pourquoi
plutot que de le taire.** Je n'ai aucune mesure de ce qu'un enfant retient a partir de deux, trois ou
cinq formulations : inventer un chiffre ici serait exactement ce que je reproche a un test qui recopie
trois chaines. **La propriete, en termes d'enfant** : *un enfant qui a deja joue deux items de cette
tache ne doit pas pouvoir repondre au troisieme sans lire les propositions.*

**Ce que j'accepte d'avance, pour ne pas laisser `marc-dev` deviner** : la forme de la sonde de
`theo-archi` - **trois valeurs distinctes sur cinq items** - **je ne la refuserai pas sur ce motif.**
Ce n'est pas une barre que je pose, c'est un refus que j'exclus d'avance. **Le minimum encodable reste
celui qu'il nomme** - *la tache B ne porte pas une bonne reponse unique* - **et il ne couvre pas ma
propriete : je reste seule garde dessus, en jouant une partie complete.**

**`Q3` - une conduite du genre *attendre que cela s'arrete* est-elle un candidat acceptable ? Oui, et
c'est meme le meilleur distracteur de cette famille.** `PLA-1` : un enfant de six ans envisage vraiment
d'attendre, c'est la conduite la plus frequente. `CAT-1` : elle ne s'ecarte pas au ton, elle n'a rien
de vilain - c'est precisement ce qui la separe de *rendre le coup*, que `C-1` ecarte. **Et elle est
refutee par ce que la tache A vient d'enseigner** : une moquerie qui se repete n'est pas un hasard,
donc elle ne s'arrete pas toute seule. **La condition que j'y mets** : *l'indice servi apres l'echec
dit pourquoi attendre ne marche pas, il ne se contente pas de renommer la bonne reponse.* **Mon
interdit de s31 sur le bouton *"a personne"* ne la couvre pas**, et `C-1` en a deja ecrit la portee.

**Un point de sa section 4 qui touche mes barres, et il ne demande rien de moi** : le cliquet de
`LON-1` est a egalite sur ce paquet et l'item 2 de la tache A y est deja en dette declaree. **Ma barre
ne bouge pas** ; la table de dette se met a jour dans le meme diff que les enonces, ce qui est le
travail de `marc-dev`. L'espace insecable devant le signe double (`TYP-1`) n'est pas de mon registre :
c'est le `relecteur-pedagogique` (`DEC-0002`, amendement du 2026-08-04 ; `DEC-0056`).

---

## 5. Ce dont je reste la seule garde sur ces deux paquets

**Dit explicitement pour que l'equipe ne croie pas couvert ce qui ne l'est pas.**

| Propriete | Garde executable ? |
|---|---|
| **Qu'aucune proposition vraie ne soit marquee fausse** (`EMC-5`) | **aucun.** Aucun test ne sait qu'une proposition est vraie. **C'est moi, en jouant** |
| **`FD-1`** - l'axe de la discrimination de l'item transpose | **aucun**, et aucun n'est concevable : c'est un jugement sur ce qu'un enfant retient, pas un compte |
| **`PLA-1`** - qu'une proposition soit un candidat envisageable | **aucun** |
| **`CAT-1`** - qu'un item ne se gagne pas au ton | **aucun**, et `C-1` montre que ma propre consigne pouvait le rouvrir |
| **L'exactitude de chaque fait de droit** | **aucun.** Reverifies un par un contre leur source |
| **`BIN-1` mesuree sur la partie plutot que sur le paquet** | **aucun** aujourd'hui. Le compte de la section 2.1 est de moi, refait a la main sur le tirage lu dans `ListContentSource` ; **je ne demande pas de garde-fou et je ne pose pas de seuil de partie** |
| **`LON-1`, `LON-2`** | **mordues** par `ADR-0089`, dette zero sur les deux paquets |
| **Le non-retour du bouton *"a personne"*** sur `cp-emc-exclusion` | **aucun apres la refonte**, et c'est neuf : la methode qui l'encodait devient verte a vide (section 4.5). **Je redeviens seule garde d'une condition qui l'etait** |

---

## 6. Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **Sortie (a), item 4** - sections 2 et 3 | `marc-dev`, via `sofia-sm` | La consigne est en section 3, aux cinq parties de `RES-1`. **Elle est engageable : elle porte un objet distinct de son remede** (`RES-4`) |
| **La lecture de `BIN-1` apres (a)** - section 2.3 | `theo-archi`, `camille-po` | La clause s'applique bien que la tache de classement devienne minoritaire. **Tranche ici, plus a re-instruire** |
| **`C-1` et `C-2`** - corrections a ma decision de forme du 2026-09-10 | `marc-dev`, `camille-po` | **`C-2` est bloquante** : ma decision de forme, executee telle qu'ecrite, ramene le defaut du premier passage |
| **Les quatre tests de `PaquetCpEmcExclusionTest`** - section 4.4 | `theo-archi`, via `sofia-sm` | **Symptome seul.** Avis du **avant** que `marc-dev` n'ouvre `S34-EXC-TACHEB`, au meme titre que pour `EMC-CP-07` |
| **La lettre de `EMC-CP-03` face a une mecanique a trois boutons** - section 4.3 | `camille-po`, via `sofia-sm` | Aucun mouvement de matrice, aucune ligne rouverte. **Un fait a inscrire, pas une story** |
| **Ce que `EMC-CP-03` couvrira apres la refonte** | moi-meme | A redire dans la fiche d'`exclusion` au prochain audit |
| **`Q1`, `Q2`, `Q3`** - les trois questions pedagogiques de son avis du 2026-09-10 | `theo-archi`, `marc-dev` | **Tranchees en section 4.6.** Aucune n'attend plus rien de moi |
| **Le non-retour du bouton *"a personne"*, apres que la methode qui l'encodait sera devenue verte a vide** | `theo-archi` | **Propriete seule**, deja formulee par lui : aucune proposition du paquet n'est ce bouton. **Sa forme lui appartient** |
| **`ZON-11` sur une frise** - section 7 | `theo-archi`, `ines-design` | La geometrie livree ne change pas. **Le repli propose par `ines-design` est ecarte** : il casse `ZON-13` |

**Aucune ligne de matrice ne bouge par ce document.** `EMC-CP-07` reste `non couvert` jusqu'au
cinquieme passage ; `EMC-CP-03` reste `couvert-valide`.

---

## 7. `ZON-11` sur `frise-soir-routine.svg` - route par `ines-design`, tranche ici

**Sa note est `proposition-ines-palier2-designation.md`, sections 5.2 et 7. Elle nomme le point sans
le trancher, ce qui est son role. Je le tranche, et je ne dessine rien.**

**Le fait, verifie sur ses quatre cadres.** L'application litterale de `ZON-11` - haut en bas, puis
gauche a droite - donne l'ordre `diner`, `brosse-dents`, `coucher`, `pyjama`. **Recolle aux libelles
positionnels du dessin, cela fait entendre a l'enfant qui navigue au clavier : *"le premier, le
deuxieme en haut, le troisieme, le deuxieme en bas"*.**

> **Ma decision, datee du 2026-09-10.** **Cet ordre est refuse, et il l'est par le motif meme de
> `ZON-11`, pas contre lui.** J'ai ecrit cette clause avec trois motifs, dont le troisieme :
> *"c'est le seul qui ne contredise pas un libelle positionnel"*. **Ici il le contredit** : il annonce
> le troisieme avant le deuxieme. **Une regle de lecture appliquee a la lettre contre son propre motif
> n'est plus une regle.**
>
> **Ce que la lecture doit rendre** : *l'ordre dans lequel l'enfant traverse les zones ne dit jamais
> le troisieme avant le deuxieme.* Sur une frise - un axe oriente, qui porte sa propre lecture et que
> `ZON-12` oblige d'ailleurs a la declarer - cela veut dire **l'ordre de l'axe d'abord, puis le haut
> avant le bas a l'interieur d'une meme colonne**. **La geometrie d'`ines-design` n'a pas a changer** :
> ses quatre cadres sont bons et `ZON-13` est tenue.
>
> **`ZON-11` n'est pas amendee et je ne la reecris pas** : *"de haut en bas, puis de gauche a droite"*
> a ete decide sur un plan et sur un planisphere - deux dimensions sans ordre propre - et c'est la que
> cette formulation vaut. **Ce que je dis ici est comment elle se lit sur une image qui porte deja un
> ordre.** Si cela oblige l'algorithme a connaitre la famille de l'image, **le cout est a `theo-archi`
> et je ne le prejuge pas** : je donne la propriete, jamais la fonction qui trie.
>
> **Et j'ecarte le repli qu'`ines-design` propose, apres l'avoir lu** : donner aux deux zones empilees
> *"le meme bord superieur que leurs voisines"* en ne les distinguant que par la hauteur met deux
> cadres au meme `x` et au meme `y`. **Ils se chevauchent alors entierement et `ZON-13` tombe** - deux
> boutons superposes, le defaut invisible du code et visible du doigt. **Ce repli ne doit pas etre
> tente.**
>
> **Ce que ce point n'est pas** : un refus du lot. **Rien de faux n'est enseigne** - *"le deuxieme, en
> haut"* et *"le deuxieme, en bas"* restent vrais dans n'importe quel ordre, et `ines-design` a raison
> de le dire en attenuation. C'est une **liste desordonnee, pas une liste fausse**. Je la tranche
> maintenant parce qu'elle coute une ligne de tri avant que le contenu n'existe, et un rejeu complet
> apres.
>
> **Ce que je ne prononce pas ici** : le Done pedagogique des deux lignes, ni `ZON-9`, qui demandent
> l'ecran de manche et n'existent pas encore. **C'est mon instance B qui auditera ce lot**, et ce sera
> la premiere fois que mes huit conditions `ZON-*` rencontrent du contenu reel.

---
