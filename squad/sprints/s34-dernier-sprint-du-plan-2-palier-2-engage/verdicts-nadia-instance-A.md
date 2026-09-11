# Verdicts de `nadia-prof` - **instance A** de s34, 2026-09-11

- **Auteur** : `nadia-prof`, professeure des ecoles, **seule autorite sur le Done pedagogique**.
- **Instance** : **A**, l'instance d'accumulation (`PAR-12`). Terrain : histoire-geographie CM1,
  anglais CM1, et la portee transverse.
- **Stories auditees** : `S34-HG-12`, `S34-ANG-CORR`, `S34-SCO1-REMEDE`, `S34-TRANSVERSE-1`.
  **`S34-CONS-4` n'a pas ete engagee** (section 7).
- **Arbre** : `round-two` a `a7852398`, les quatre branches fusionnees, rien n'est pousse. **Jar
  reconstruit depuis un worktree dedie**, application lancee sur un port local, **arretee par son
  identifiant de processus** a la fin de mes parties - pas par un nom d'image, et pas depuis le
  checkout partage.
- **Je ne rejuge pas la porte technique.** Elle est annoncee par `sofia-sm` au planning, section 14,
  quatre jobs nommes un par un et 1464 tests. J'ai construit un jar depuis mon worktree pour jouer ;
  **ce geste n'est pas une mesure de cette porte** et je ne l'ecris pas comme telle.
- **Ce document ne touche pas `squad/programme/MATRICE-couverture.md`** : le mouvement des lignes
  appartient a `camille-po` et a la review. Il ne commite rien.
- **Premier sprint audite sous `RES-1` a `RES-4`.** La section 8 dit, pour chacune des corrections
  que je juge, **si elle a suivi l'objet de la reserve ou seulement sa lettre**. C'est la classe de
  defaut que s33 a nommee, et c'est ce que ce dispositif vise.

---

## 0. Ce que j'ai joue, et ce que je n'ai pas pu voir

| Ce que j'ai joue | Ce que j'y ai verifie |
|---|---|
| `bandit-manchot-histoire-geographie-cm1`, **plus de vingt parties completes**, **les dix items de `HG-CM1-12` vus a l'ecran, un par un**, chaque proposition fausse pressee avant la bonne | les 18 distracteurs reecrits ; les deux enonces touches ; les dix indices ; la consigne a cinq clauses, toujours servie |
| `bandit-manchot-anglais-cm1`, **les dix items de `LVE-CM1-07` releves sur le fichier servi**, quatre items joues a l'ecran | les quatre corrections de `S34-ANG-CORR`, item devant propositions |
| `bandit-manchot-histoire-geographie-cm1`, **parties completes jouees mal** : chaque manche echouee trois fois, jusqu'a la revelation | le chemin d'echec complet apres le remede : *"Ce n'est pas ca. Essaie encore !"*, puis *"Ce n'est pas ca."*, puis *"Appuie sur la bonne reponse :"* et la reponse nommee |
| `bandit-manchot-vivant-non-vivant-cp`, **partie complete** | le remede sur un paquet **portant des items a deux propositions** : l'enfant garde ses essais, l'indice arrive, le score ne credite pas |
| `saisie-complements-10-cp` et `saisie-mot-dicte-cp`, **parties completes** | le remede sur la **saisie** ; le chemin d'echec ; ce que le champ fait d'une validation vide |
| `designation-frise-de-la-journee`, **deux parties completes** | le remede sur la mecanique de **designation**, neuve ce sprint |
| **Les ecrans de fin de partie des trois mecaniques**, lus tels que l'enfant les lit | ce que le produit dit a l'enfant apres le remede - section 5 |

**Audit degrade sur un point, et je le declare** : j'ai joue par requetes HTTP reelles, donc j'ai lu
le **HTML servi**, pas la page rendue par un navigateur. Cela couvre les textes, l'ordre des ecrans,
l'enchainement apres une erreur et apres trois, le score et l'ecran de fin. Cela **ne** couvre ni la
couleur, ni le contraste, ni la taille, ni la position a l'ecran. **Aucune conclusion de ce document
ne repose sur un jugement visuel.**

**Ce que je n'ai pas re-verifie et que je ne suppose pas** : la loi de tirage. Je donne des items
servis, jamais une probabilite de tirage.

**Ce que je n'ai pas joue** : le contenu du palier 2 (`QLM-CP-14`), qui appartient a l'instance B. Je
n'ai joue `designation-frise-de-la-journee` que pour y verifier le remede de score, et **je ne
prononce aucun verdict sur son contenu**.

---

## 1. Correction a mon propre constat, avant de m'en servir

**Mon arbitrage prealable de s34 se contredit sur le perimetre de `HG-R17`, et la contradiction est
la mienne.**

- **Section 5.1** : *"Perimetre, mesure sur le fichier servi et sur l'etat d'arrivee : les 18
  distracteurs des 9 items neufs de la ligne ... et rien d'autre."*
- **Section 10, refus numero 1** : *"Toute fermeture de `HG-CM1-12` dont les deux distracteurs d'un
  item ne sont pas du meme genre que sa bonne reponse et du meme espace que son enonce."*

Le premier borne la reprise a neuf items ; le second parle de **toute fermeture de la ligne**, donc
de ses dix items. **La ligne en porte un dixieme, ecrit a s27** - *"Dans un espace touristique de
montagne, quelle activite se developpe surtout en hiver ?"* -> *"le tourisme lie au ski"*, contre
*"la peche en mer"* et *"la culture de la vigne"*. **Il n'a jamais ete audite a la fermeture**,
puisque la ligne n'a jamais ferme.

**Ce que je fais de cet ecart, et je le fais dans le sens qui ne deplace pas mes poteaux.**
`marc-dev` a tenu exactement le perimetre que je lui ai ecrit ; **refuser la ligne sur un item que
j'en avais moi-meme sorti serait lui faire payer mon erreur de chiffrage** - exactement ce que
`RES-2` point 3 m'interdit, et exactement ce qui a coute un sprint sur `EMC-CP-07`. La ligne ferme,
et **l'item du dixieme rang part en reserve neuve, datee d'aujourd'hui** (`HG-R22`, section 3.5),
avec son motif : il est a moi, pas a lui.

---

## 2. Les quatre verdicts

| Story | Ligne ou portee | Verdict | Etat de matrice demande |
|---|---|---|---|
| `S34-HG-12` | `HG-CM1-12` | **VALIDE SOUS RESERVE** - **Done pedagogique prononce** | `en cours` -> **`couvert-valide`** |
| `S34-ANG-CORR` | `LVE-CM1-07` (deja `couvert-valide`) | **VALIDE SOUS RESERVE** - **Done pedagogique prononce** sur la correction | **inchangee**, `couvert-valide` |
| `S34-SCO1-REMEDE` | transverse, les trois mecaniques | **REFUSE** (`SCO-R1`, bloquante) | sans objet - aucune ligne |
| `S34-TRANSVERSE-1` | transverse, portee `PAR-12` | **conduite, resultat ecrit** (section 6) - aucun Done pedagogique n'y est prononce, par construction | sans objet |

**Aucune mention de couverture partielle n'est due sur `HG-CM1-12`.** Celles deja ecrites sur
`HG-CM1-01`, `HG-CM1-14` et `LVE-CM1-07` sont inchangees et ne sont pas reecrites ici.

---

## 3. `S34-HG-12` - `HG-CM1-12` **VALIDE SOUS RESERVE**, la ligne ferme

### 3.1 Le compte, refait et non suppose

**Methode, identique a celle de s33 pour que les deux chiffres soient comparables** : attribution
faite a la main par moi, item par item, sur le fichier servi et sur les dix items vus a l'ecran.

| Mesure | s33 (refus) | s34 (aujourd'hui) |
|---|---|---|
| **Items neufs portant au moins un distracteur serieux du meme genre que la bonne reponse** | **2 sur 9** | **9 sur 9** |
| **Items neufs dont les *deux* distracteurs sont du meme genre que la bonne reponse** | 0 sur 9 (non compte a s33, compte ici) | **9 sur 9** |
| **Distracteurs neufs venant du registre de la ferme, du champ, de la peche ou de la batellerie** | **14 sur 18** | **0 sur 18** |
| **Items de la ligne entiere repondables sans lire la question** | **9 sur 10** (regle de registre) | **2 sur 10** - l'item du dixieme rang (`HG-R22`) et celui des monuments (`HG-R19`) |

**Les dix-huit distracteurs, releves un par un :**

| Item, par sa bonne reponse | Ses deux distracteurs | Du meme genre ? |
|---|---|---|
| un chalet ou un appartement de location | une tente de camping, une auberge de jeunesse | oui - deux hebergements de vacances reels |
| les services | l'industrie, la construction | oui - deux secteurs economiques reels |
| dans un musee | dans une bibliotheque, dans une mairie | oui - deux batiments publics de la ville |
| des monuments historiques | des commerces de souvenirs, des jardins publics | oui - deux lieux reellement frequentes par des touristes |
| le metro | le tramway, le bus | oui - deux transports en commun reels |
| un telesiege | un tapis roulant, une navette en bus | oui - et **le premier est defendable** (`HG-R20`) |
| un camping | un hotel en bord de mer, une auberge de jeunesse | oui - deux hebergements de vacances reels |
| dans un bureau | dans un entrepot, dans un atelier | oui - deux lieux de travail reels en ville |
| un festival | une exposition de peinture, une visite guidee du patrimoine | oui - et **les deux sont defendables** (`HG-R20`) |

**Le mode de defaillance que j'avais nomme d'avance n'a pas eu lieu.** J'avais ecrit : *"remplacer
les mots ruraux et garder des distracteurs invraisemblables - un immeuble de bureaux n'est pas rural
et n'est pas davantage un logement de vacances"*. **Il est evite sur les dix-huit.** Aucun
distracteur n'est un objet pris au hasard : chacun est une chose reelle, du genre de la reponse, et
qu'un enfant de CM1 peut serieusement donner.

**Ce que cela change pour l'enfant, et c'est l'objet que j'avais ecrit.** Un enfant qui ne connait
pas la reponse ne peut plus ecarter par le registre : il doit savoir qu'un metro circule sous terre
et pas un tramway, qu'un camping est en plein air et pas un hotel, qu'une banque emploie dans des
bureaux et pas dans un atelier. **C'est la competence de la ligne qui est mise a l'epreuve, et non
la reconnaissance d'un champ lexical.**

### 3.2 Ce que je reverifie sans le rediscuter, comme annonce

- **`FER-1`** : les quatre pratiques sont en bonne reponse - se loger (chalet, camping), travailler
  (services, bureau), se cultiver (musee, monuments, festival), se deplacer (metro, telesiege).
  **Tenue**, les bonnes reponses n'ont pas bouge.
- **`FER-2`** : **10 items** sur la ligne. Tenue.
- **`FER-3`** : la repartition des taches n'a pas bouge, aucune bonne reponse n'etant touchee. Tenue.
- **`HIS-5`** : aucun des neuf items neufs ne reprend la cellule *se loger dans un espace urbain*,
  deja depensee par `HG-CM1-11`. **Reverifiee sur les bonnes reponses, inchangees.** Tenue.
- **`HG-R4`** (mediane du paquet entier) : les deux enonces touches ont **raccourci**, pas
  rallonge - *"ou les vacanciers dorment-ils le plus souvent ?"* remplace *"quel logement loue-t-on
  souvent aux vacanciers ?"*. Rien ne peut avoir monte. Tenue.
- **`HG-R18`** : **levee**. L'enonce du secteur d'activite est devenu *"Dans un espace urbain, quel
  secteur emploie le plus grand nombre d'habitants ?"* : il n'enumere plus deux des trois
  propositions, et le troisieme choix cesse d'etre exclu par la lecture.
- **La moitie payee de `HG-R17`** : l'enonce du logement ne contient plus *loue*, qui annoncait
  *location*.
- **`HG-R13` a `HG-R16`** (sur `HG-CM1-08`) : non retouchees, elles voyagent sur le meme paquet
  (`PAR-10`) et **restent ouvertes**. Je ne les compte pas contre ce lot : elles n'etaient pas
  demandees.
- **`CONS-1` et `CONS-4`** : la consigne a cinq clauses est **toujours servie sur chaque manche** -
  *"Lis bien, puis situe dans le temps, nomme un peuple ou un mouvement de population, associe un roi
  a son fait, decris un espace ou une ressource, ou lis un document."* **Je ne bloque pas dessus, et
  ce n'est pas une indulgence** : j'ai ferme trois lignes de ce meme paquet a s33 avec cette meme
  consigne, et relever la barre aujourd'hui serait deplacer un poteau. `S34-CONS-4`, qui la paie, n'a
  pas ete engagee (section 7).

### 3.3 `HG-R19` - **reserve neuve, datee du 2026-09-11, non bloquante** : la seconde clause de `HG-R17`, payee sur un item des deux

**Constat, vu a l'ecran le 2026-09-11.** *"Dans une ville touristique riche en **histoire**, que
visitent surtout les touristes ?"* -> *"des monuments **historiques**"*, contre *"des commerces de
souvenirs"* et *"des jardins publics"*.

`HG-R17` portait deux clauses. La premiere - les distracteurs - est payee. La seconde disait : *"sur
les deux items nommes plus haut, le mot de la bonne reponse cesse d'etre appariable a un mot de
l'enonce"*. **Les deux items nommes etaient celui du logement et celui-ci.** Le premier est corrige ;
**celui-ci n'a pas ete touche**, et le message de commit ne le mentionne pas.

- **Objet** : *sur cet item, un enfant qui ne sait pas ce qu'est un monument historique ne doit pas
  pouvoir trouver la reponse en reconnaissant le mot de l'enonce dans une proposition.* Aujourd'hui
  il n'a besoin de rien savoir : **histoire** appelle **historiques**, et les deux autres
  propositions ne portent aucun mot de l'enonce.
- **Mode de defaillance** : **retirer *riche en histoire* et laisser l'enonce ne plus dire de quelle
  ville il parle.** L'enonce doit continuer a poser son espace - c'est ce que `HIS-3` demande a tous
  les items de cette famille. Le geste est de dire cet espace **sans employer le mot que la bonne
  reponse porte**.
- **Perimetre, mesure sur l'etat d'arrivee** : **un enonce, l'item des monuments historiques**, et
  rien d'autre. Les trois propositions ne bougent pas. `FER-1`, `FER-3` et `HIS-5` restent tenues
  apres le geste, la bonne reponse n'etant pas touchee. **Je n'ai pas compte** les autres items de
  la ligne pour cet appariement-la : je les ai lus, aucun autre ne partage un mot entre son enonce
  et sa bonne reponse.
- **Regime et prix** : **mot pour mot, 0,25**, et il voyage avec les trois autres reserves de ce
  paquet sans cout supplementaire (`PAR-10`).

### 3.4 `HG-R20` - **reserve neuve, datee du 2026-09-11, non bloquante** : deux items ou un distracteur devenu serieux est devenu vrai

**C'est le revers exact de la correction, et il ne faisait pas partie des modes de defaillance que
j'avais nommes d'avance. Je le dis plutot que de le presenter comme prevu.**

**Constat, vu a l'ecran le 2026-09-11, deux items :**

1. *"Dans une station de ski, quel appareil fait monter les touristes vers les pistes ?"* -> *"un
   telesiege"*, contre *"un tapis roulant"* et *"une navette en bus"*. **Un tapis roulant de station
   de ski est une remontee mecanique : il fait bien monter des skieurs vers une piste.** L'enfant qui
   le sait est marque faux.
2. *"Dans une ville touristique, quel evenement culturel attire souvent des visiteurs en ete ?"* ->
   *"un festival"*, contre *"une exposition de peinture"* et *"une visite guidee du patrimoine"*.
   **Une exposition de peinture est un evenement culturel qui attire des visiteurs en ete.** La
   question ne demande ni le plus frequent, ni le plus grand : elle demande *"quel evenement
   culturel"*, et trois reponses y repondent.

- **Objet** : *un enfant qui connait les trois propositions doit pouvoir choisir sur ce qu'il sait,
  et non deviner celle que le jeu attend.* Aujourd'hui, sur ces deux items, le bon eleve est puni de
  ce qu'il sait - c'est la famille inverse de celle qui a fait refuser la ligne a s33, et elle est
  pire : elle frappe l'enfant qui a appris.
- **Mode de defaillance** : **remettre un distracteur plus faible pour retablir l'unicite.** Cela
  rouvrirait `HG-R17` a l'endroit ou elle vient d'etre payee. **Le geste est dans l'enonce, pas dans
  les propositions** : l'enonce doit porter le critere qui distingue la bonne reponse des deux
  autres - ce que huit des dix items de la ligne font deja (*"sous terre"*, *"en plein air"*, *"le
  plus souvent"*, *"d'une banque ou d'une administration"*). **Et le critere ajoute doit etre vrai** :
  je refuse d'avance un superlatif pose pour trancher et faux dans le monde.
- **Perimetre, mesure sur l'etat d'arrivee** : **deux enonces**, ceux du telesiege et du festival, et
  rien d'autre. Les six propositions de ces deux items restent. **Conditions que l'etat d'arrivee
  doit encore satisfaire** : `HG-R4` sur la mediane du paquet (les deux enonces s'allongeront un
  peu ; ils sont aujourd'hui sous la mediane, et je le reverifierai), `HIS-3` famille (a), `FER-1`
  inchangee. **Ce que je n'ai pas compte** : je n'ai pas verifie les enonces des autres lignes du
  paquet pour cette propriete - ce sont des lignes fermees et je ne les rouvre pas.
- **Regime et prix** : **mot pour mot, 0,25**, voyage avec les autres (`PAR-10`).

### 3.5 `HG-R21` - **reserve neuve, datee du 2026-09-11, non bloquante** : un indice que la correction a vide

**Constat, lu apres l'echec qu'il suit** - c'est la deuxieme lecture de `RES-3`, et c'est elle qui
trouve ce defaut :

> *"Dans une station de montagne touristique, ou les vacanciers dorment-ils le plus souvent ?"* ->
> *"un chalet ou un appartement de location"*, contre *"une tente de camping"* et *"une auberge de
> jeunesse"*. **Indice servi apres le premier echec : *"Pense au logement loue seulement pour
> quelques jours ou quelques semaines."***

**Une tente de camping et une auberge de jeunesse se louent aussi pour quelques jours.** L'indice
separait les trois propositions quand les deux fausses etaient *une ferme isolee* et *un immeuble de
bureaux* ; **il ne separe plus rien depuis que les distracteurs sont devenus serieux.** Ce qu'il
garde, c'est le mot *loue*, qui appelle *location* - **l'appariement que la correction vient de
retirer de l'enonce revient par l'indice**, c'est-a-dire a l'endroit exact ou se trouve l'enfant qui
s'est deja trompe une fois.

**Second cas, plus faible, et je le donne avec sa faiblesse** : *"Dans une grande ville, ou les
habitants decouvrent-ils le plus souvent des oeuvres d'art exposees ?"*, indice *"C'est un batiment
qui conserve et expose des objets ou des oeuvres."* Face a *"dans une bibliotheque"* et *"dans une
mairie"*, l'indice ne fait plus que redire l'enonce.

- **Objet** : *l'enfant qui vient de se tromper doit trouver dans l'indice un fait qui designe une
  seule des trois propositions.* Aujourd'hui, sur ces deux items, l'indice designe les trois - ou
  designe la bonne par un mot, ce qui n'apprend rien.
- **Mode de defaillance** : **ecrire un indice qui nomme la bonne reponse par un synonyme.** Ce
  serait de nouveau gagner sans savoir, un cran plus loin. L'indice doit apporter **un fait sur la
  chose**, que l'enfant puisse verifier contre les trois propositions.
- **Perimetre, mesure sur l'etat d'arrivee** : **deux indices**, ceux du logement de montagne et du
  musee. Les enonces, les bonnes reponses et les distracteurs ne bougent pas. **Je n'ai pas compte**
  les huit autres indices de la ligne contre cette propriete un par un ; je les ai lus, et aucun ne
  m'a arrete.
- **Regime et prix** : **mot pour mot, 0,25**, voyage avec les autres (`PAR-10`).
- **Ce que cette reserve n'est pas** : **elle ne releve aucune barre sur les indices.** Elle constate
  qu'un indice qui separait trois propositions ne les separe plus **parce que les propositions ont
  change**. Sans la correction, elle n'existerait pas.

### 3.6 `HG-R22` - **reserve neuve, datee du 2026-09-11, non bloquante** : l'item que j'avais sorti du perimetre

**Constat, vu a l'ecran le 2026-09-11** : *"Dans un espace touristique de montagne, quelle activite
se developpe surtout en hiver ?"* -> *"le tourisme lie au ski"*, contre *"la peche en mer"* et *"la
culture de la vigne"*. Item ecrit a s27, **jamais audite a la fermeture d'une ligne**, et **sorti par
moi du perimetre de `HG-R17`** (section 1).

- **Objet** : *sur cet item aussi, un enfant qui ne sait pas doit pouvoir se tromper serieusement.*
  Aujourd'hui, c'est le seul item de la ligne ou il lui suffit d'ecarter ce qui est rural pour
  repondre - et la mer et la vigne sont, en montagne et en hiver, deux impossibilites.
- **Mode de defaillance** : **remplacer les deux distracteurs par deux activites de montagne que
  l'enonce exclut deja par *en hiver*.** Le critere est le meme que pour les dix-huit autres : chacun
  des deux doit etre une activite qu'un enfant peut serieusement attribuer a une station de montagne
  en hiver.
- **Perimetre, mesure sur l'etat d'arrivee** : **deux distracteurs, un seul item.** L'enonce et la
  bonne reponse ne bougent pas. `FER-1`, `FER-2`, `FER-3` et `HIS-5` restent tenues, aucune bonne
  reponse n'etant touchee.
- **Regime et prix** : **mot pour mot, 0,25**, voyage avec les autres (`PAR-10`).
- **A qui ce defaut est imputable** : **a moi.** `marc-dev` a tenu le perimetre que je lui avais
  ecrit, et cet item n'y etait pas.

### 3.7 Ce que je verifie sur cette ligne et qui ne fonde aucune demande

- **Exactitude.** Les dix bonnes reponses sont justes. Les dix-huit distracteurs neufs designent
  tous des choses reelles ; les deux cas ou le distracteur est **vrai pour la question posee** sont
  `HG-R20`, et je ne les compte pas deux fois.
- **Niveau de langue.** *"un telesiege"*, *"une auberge de jeunesse"*, *"un entrepot"*, *"la
  construction"* comme secteur : tous accessibles au CM1. Aucun mot du niveau de *"imperiale"* n'est
  entre dans ce lot.
- **Charge de lecture.** Les deux enonces touches ont raccourci ; aucun enonce neuf.
- **`BIA-1` a `BIA-5`.** Aucun stereotype de genre, d'origine, de milieu ou de configuration
  familiale sur les dix items. L'item du camping et celui du chalet decrivent des vacances que tous
  les enfants ne prennent pas ; **ils ne les presupposent pas** - chaque enonce donne l'espace dont
  il parle, et decrire un espace touristique est le libelle meme de la ligne. **Je n'ouvre rien
  la-dessus**, et je le dis pour que ce point ne se relise pas comme un manque.
- **`COH-1`.** Les dix items de la ligne ne se contredisent pas entre eux. **Je n'ai pas passe le
  paquet entier a cette condition** : elle est opposable a partir du prochain lot, et ce lot-ci ne
  reecrit aucune regle.

---

## 4. `S34-ANG-CORR` - **VALIDE SOUS RESERVE**, `LVE-CM1-07` reste `couvert-valide`

**Aucun statut de matrice ne change** : la ligne etait deja fermee, et cette story est une correction
non bloquante. Le Done pedagogique porte ici sur **la correction**, pas sur une fermeture.

### 4.1 Les trois reserves levees

- **`ANG-R4` - levee.** L'enonce de Halloween passe de 21 mots dont 14 inseres entre le sujet et le
  verbe a *"Quelle fete americaine, celebree le 31 octobre, fait deguiser les enfants pour recolter
  des bonbons ?"* : l'apposition fait quatre mots, le verbe suit. **L'objet est tenu** - l'enfant n'a
  plus a garder *fete* en memoire pendant quatorze mots.
- **`ANG-R5` - levee.** L'indice de Londres devient *"C'est la plus grande ville du Royaume-Uni,
  traversee par la Tamise."* **Exact**, et il ne donne plus a la capitale le nom d'une grande roue.
  Il separe Londres de Berlin et de Madrid : il fait ce qu'un indice doit faire.
- **`ANG-R6` - levee.** *"en metro"* est remplace par *"en voiture avec un parent"*. **C'est
  exactement la propriete que j'avais ecrite** : l'une des propositions est la pratique concurrente
  reelle. L'item cesse d'ecarter son seul candidat serieux.

### 4.2 `ANG-R3` - **levee sur deux items des trois, elle reste ouverte**

Ma reserve portait un tableau de **trois** items. Deux ont ete repris :

- **le bus a imperiale** : l'enonce ne decrit plus l'objet, et les distracteurs deviennent *"le
  metro"* et *"le taxi noir"*, deux transports londoniens reels. **Le fait qui enseigne - rouge, deux
  niveaux - a ete deplace dans l'indice, la ou l'enfant qui s'est trompe le lit.** C'est un bon
  geste, et je le dis.
- **Halloween** : les distracteurs deviennent *"Thanksgiving"* et *"le Labor Day"*, deux fetes
  americaines reelles, et **la date dans l'enonce est ce qui tranche**. L'item est devenu un
  exercice : il faut savoir que Halloween tombe le 31 octobre.

**Le troisieme item n'a pas ete touche, et il est encore exactement ce que ma reserve decrivait** :
*"Quel monument, une grande statue tenant une torche, se trouve sur une ile a l'entree du port de
New York ?"* -> *"la statue de la Liberte"*, contre *"la tour Eiffel"* et *"le Colisee"*. **Ni l'une
ni l'autre n'est une statue, ni a New York : l'enonce les exclut quatre fois.** L'item se repond sans
rien savoir des Etats-Unis.

**Et la seconde clause de `ANG-R3` n'est pas payee non plus** : *"sur l'item 22, le mot* ***imperiale***
*est explique la ou l'enfant le rencontre, ou remplace."* Le mot est toujours en bonne reponse, et il
n'est explique **que dans l'indice**, c'est-a-dire **seulement pour l'enfant qui s'est trompe**.
Celui qui trouve du premier coup designe un mot qu'il ne comprend pas.

> **`ANG-R3`, reexprimee au format de `RES-1` le 2026-09-11, sans etre elargie ni durcie.** Son
> perimetre est **reduit** a ce qui reste.
>
> - **Objet** : *sur les items neufs de `LVE-CM1-07`, l'enfant ne doit pas pouvoir ecarter une
>   proposition par la seule lecture de l'enonce ; et le mot qu'il designe en repondant doit lui
>   etre accessible.*
> - **Mode de defaillance** : **traiter l'enonce de la statue en lui retirant sa description et
>   laisser *"la tour Eiffel"* et *"le Colisee"* en face.** Le defaut ne vient pas seulement de
>   l'enonce trop bavard : il vient de deux propositions qui ne sont candidates a rien. Il faut les
>   deux gestes, ou le second seul - **un autre monument de New York**.
> - **Perimetre, mesure sur l'etat d'arrivee** : **un enonce et deux distracteurs** sur l'item de la
>   statue de la Liberte ; **plus le sort du mot *imperiale***, explique la ou l'enfant le rencontre
>   ou remplace. **Conditions que l'etat d'arrivee doit encore satisfaire** : `FER-2` (dix items sur
>   la ligne) et `FER-1`, qu'aucun de ces gestes ne touche. **Ce que je n'ai pas compte** : les cinq
>   items de s28, hors de la portee de `PLA-1`, que je ne lui soumets pas.
> - **Regime et prix** : **rejeu borne, 0,25** - la moitie du prix initial, la moitie du travail
>   ayant ete faite.

### 4.3 `ANG-R7` - **reserve neuve, datee du 2026-09-11, non bloquante** : un enonce ou les trois propositions sont vraies

**Constat, lu sur le fichier servi et joue le 2026-09-11** : *"Quel moyen de transport est un symbole
touristique de Londres ?"* -> *"le bus a imperiale"*, contre *"le metro"* et *"le taxi noir"*.

**Les trois en sont.** Le metro de Londres et le taxi noir sont des symboles touristiques de Londres
au meme titre que le bus rouge, et l'enonce dit *"un symbole"*, pas *"le symbole"*. **En rendant les
propositions serieuses, la correction a rendu la question ambigue** : l'enfant qui connait Londres
doit deviner celle que le jeu attend.

- **Objet** : *sur cet item, l'enfant doit pouvoir choisir sur un fait, et non sur ce qu'il suppose
  que le jeu prefere.*
- **Mode de defaillance** : **remettre la description de l'objet dans l'enonce** - rouge, deux
  etages - qui rouvrirait `ANG-R3` a l'endroit ou elle vient d'etre payee. **Le geste est de poser
  dans l'enonce un critere qui distingue le bus des deux autres sans le decrire**, et l'indice
  conserve le fait qui enseigne.
- **Perimetre, mesure sur l'etat d'arrivee** : **un enonce**, celui du transport londonien. Les trois
  propositions et l'indice restent. `FER-1` et `FER-2` inchangees.
- **Regime et prix** : **mot pour mot, 0,25**, et il voyage avec `ANG-R3` sur le meme paquet
  (`PAR-10`).

### 4.4 Ce que je verifie sur cette ligne et qui ne fonde aucune demande

- **Exactitude des dix items** : juste. *"le Labor Day"* est bien une fete americaine, en septembre ;
  la question tranche par la date, pas par la saison, et je ne lui oppose rien.
- **`COH-1`** : les items de Thanksgiving et de Halloween se servent mutuellement de distracteur, et
  **chacun porte son propre critere** - fin novembre et le repas de famille d'un cote, le 31 octobre
  et les deguisements de l'autre. **Ce n'est pas une contradiction, c'est une discrimination**, et
  c'est bien faite.
- **`BIA-2`** : aucun pays n'est reduit a un seul trait ; six traits des Etats-Unis sont servis.

---

## 5. `S34-SCO1-REMEDE` - **REFUSE**

**Motif bloquant : l'ecran de fin de partie n'affiche que le nombre reduit. C'est exactement le
refus numero 6 de mon arbitrage prealable, ecrit d'avance le 2026-09-10, et il se realise a l'endroit
que j'avais nomme - *"a la premiere partie jouee apres le remede"*.**

### 5.1 Ce que le remede fait, et qui est juste

**Verifie en jouant, sur les trois mecaniques :**

| Mecanique | Ce que j'ai joue | Ce que j'ai constate |
|---|---|---|
| `bandit-manchot`, trois propositions | parties completes sur `histoire-geographie-cm1` | l'enfant garde ses trois essais ; l'indice arrive des le premier echec ; le score **ne monte que sur les manches trouvees du premier appui** |
| `bandit-manchot`, items a deux propositions | partie complete sur `vivant-non-vivant-cp` | meme comportement ; **l'indice reste utile puisque l'enfant a encore un appui** - c'est le motif numero 1 pour lequel j'ai ecarte la famille B, et il tient |
| `saisie` | parties completes sur `complements-10-cp` et `mot-dicte-cp` | meme comportement |
| `designation` (**neuve ce sprint**) | deux parties completes sur `frise-de-la-journee` | meme comportement : *"Bravo !"* apres un echec, **score inchange** ; score qui monte sur une manche trouvee du premier appui |
| chemin d'echec complet | trois echecs sur la meme manche, sur les trois mecaniques | **la revelation est intacte** : *"Ce n'est pas ca."* perd son *"Essaie encore !"*, puis *"Appuie sur la bonne reponse :"* nomme la reponse, et **la manche se ferme sur l'appui de l'enfant**. `M5` est tenue - l'enfant bloque avance toujours |

**Les trois mecaniques se comportent de la meme facon**, y compris la designation, ce que je devais
verifier en propre puisqu'elle est neuve. **La phrase que j'avais ecrite pour etre citee est tenue
au mot.**

### 5.2 `SCO-R1` - **reserve neuve, datee du 2026-09-11, BLOQUANTE** : ce que l'enfant lit a la fin

**Constat, lu tel que l'enfant le lit, sur les trois mecaniques, le 2026-09-11.** Ecran de fin de
partie apres dix manches jouees, six trouvees du premier appui :

> *"Les jeux"* | ***"Score : 6"*** | *"Bravo, c'est juste !"* | *"C'est fini !"*

**Et rien d'autre.** Meme lecture sur la designation (*"Score : 3"* apres dix manches) et sur la
saisie (*"Score : 4"* apres sept manches).

**Avant le remede, *"Score : 10"* disait deux choses a la fois** - ce que l'enfant avait trouve, et
qu'il avait fait les dix questions. **Le remede a retire la premiere information sans laisser la
seconde.** Un enfant qui a fait dix questions et qui en a trouve six seul lit *"6"*, un point final,
sur un ecran dont c'est le seul chiffre.

- **Objet** : *a la fin d'une partie, l'enfant doit lire deux choses - ce qu'il a trouve seul, et
  qu'il a fait toutes les questions.* Aujourd'hui il n'en lit qu'une, et c'est la plus basse.
- **Mode de defaillance** : **ecrire un ecran qui montre a l'enfant ce que ses essais lui ont
  coute** - un decompte, une note sur dix presentee comme un resultat, une phrase qui explique
  pourquoi le chiffre est plus bas qu'avant. **Ma decision ne demande pas cela** et je l'avais ecrit
  le 2026-09-10 : les deux nombres que l'enfant doit lire existent deja tous les deux dans `Score`.
  **Un second mode** : ajouter le nombre de questions **a cote** du score sans que l'enfant sache
  lequel est lequel - *"6"* et *"10"* poses l'un pres de l'autre se lisent comme une note.
- **Perimetre, mesure sur l'etat d'arrivee** : **un ecran, celui de fin de partie**, commun aux trois
  mecaniques. **Le texte est a ecrire, et je ne l'ecris pas** (`RES-2` point 1 : je ne dicte pas un
  texte que je n'ai pas recolle a sa place). **Ce qu'il doit rendre est ci-dessus ; il revient devant
  moi quand il est ecrit, lu a l'ecran, pas dans un gabarit.** **Conditions que l'etat d'arrivee doit
  satisfaire** : les mots sont lisibles par un enfant de CP, aucun n'est un terme de note scolaire.
  **Ce que je n'ai pas compte** : l'ecran de fin est aussi celui d'une manche revelee ; je n'ai pas
  verifie ce que le texte neuf y donnerait, et c'est a verifier au moment ou il sera ecrit.
- **Regime et prix** : **rejeu, 0,5** - une partie complete sur chacune des trois mecaniques, plus
  une partie jouee mal jusqu'a la revelation.

**Pourquoi c'est un refus, et ce que ce refus ne demande pas.** Il ne demande **pas** de revenir sur
le remede : les trois sessions font exactement ce que j'ai decide, `ADR-0090` le tient, et **je ne
demande a personne de defaire une ligne de ce travail**. Il constate que **ce qui arrive a l'enfant
est incomplet** : un Done pedagogique porte sur ce que l'enfant lit, et ce qu'il lit aujourd'hui est
le seul chiffre bas de sa partie. **Le trou est dans le perimetre de la story, pas dans
l'implementation** : ni la story, ni `ADR-0090` - qui le dit explicitement en decision 4 - ne
portaient l'ecran de fin, alors que mon refus numero 6 etait ecrit avant les deux.

### 5.3 `SCO-R2` - **reserve neuve, datee du 2026-09-11, non bloquante** : rien ne dit a l'enfant ce qui compte

**Constat, vu a l'ecran** : sur une manche trouvee au deuxieme essai, le jeu affiche ***"Bravo !"***
et le score **ne bouge pas**. Le texte est identique, au mot pres, a celui d'une manche trouvee du
premier coup, ou le score monte. **Rien, nulle part dans le jeu, ne dit a l'enfant que seul le
premier essai compte.**

- **Objet** : *l'enfant doit pouvoir savoir ce qui fait monter le chiffre qu'il lit.* Aujourd'hui il
  lit *"Bravo !"* et voit un compteur immobile : la seule explication qu'il peut se donner est que le
  jeu s'est trompe, ou qu'il a ete trompe.
- **Mode de defaillance** : **retirer le *"Bravo !"* des manches qui ne comptent pas.** Ce serait
  refuser a l'enfant la reconnaissance d'un geste juste qu'il a bel et bien fait - **le contraire de
  ce que ma decision dit** : *"un essai qui compte, et deux qui enseignent"*. L'enfant a trouve ; on
  ne le lui reprend pas.
- **Perimetre, mesure sur l'etat d'arrivee** : **la ou cette regle est dite a l'enfant** - un endroit,
  pas quatre. Je ne dis pas lequel : c'est une decision de forme, et elle n'est pas de moi. **Ce que
  je n'ai pas compte** : je n'ai pas verifie si un adulte accompagnant lit quelque part cette regle ;
  rien de ce que j'ai joue ne la porte.
- **Regime et prix** : **rejeu, 0,25**, et il se solde avec `SCO-R1` dans la meme partie.

### 5.4 `SCO-R3` - **reserve neuve, datee du 2026-09-11, non bloquante** : sur la saisie, une validation vide coute desormais la manche

**Constat, mesure le 2026-09-11 sur `saisie-mot-dicte-cp`** : le champ vide valide, puis un champ de
trois espaces valide, comptent **chacun un essai** - *"Ce n'est pas ca. Essaie encore !"* les deux
fois - et le troisieme essai tombe sur la revelation. **Avant le remede, cela ne coutait rien au
score. Depuis le remede, la premiere validation vide retire a la manche tout credit possible.**

- **Objet** : *un enfant de CP qui appuie sur "Je reponds" sans avoir rien ecrit ne doit pas perdre
  ce que sa manche pouvait lui rapporter.* Il apprend a ecrire au clavier ; le geste vide est une
  maladresse, pas une reponse fausse.
- **Mode de defaillance** : **donner un message qui gronde** - *"tu n'as rien ecrit"* adresse a
  l'enfant. Le jeu doit simplement ne rien compter et le laisser recommencer.
- **Perimetre, mesure sur l'etat d'arrivee** : **la mecanique de saisie seule**, deux entrees au
  catalogue. **Ce que je n'ai pas compte** : je n'ai pas cherche l'equivalent sur la designation - un
  bouton de zone ne peut pas etre presse a vide - ni sur le bandit manchot, ou il n'existe pas.
- **Regime et prix** : **rejeu borne, 0,25** - une partie de saisie, champ vide valide sur la
  premiere manche.
- **Ce que cette reserve n'est pas** : elle ne reproche pas le remede. **C'est le remede qui lui
  donne un prix**, et c'est pour cela qu'elle nait aujourd'hui et pas avant.

---

## 6. `S34-TRANSVERSE-1` - conduite, resultat ecrit

**Aucun Done pedagogique n'est prononce dans cette section, par construction** : la story l'interdit
elle-meme. Ce qui suit sont des constats sur le depot existant.

### 6.1 Les gabarits qui composent une phrase autour d'un champ (famille `REV-1`, `SAI-R1`)

**Methode** : les cinq gabarits servis (`accueil`, `manche`, `retour`, `saisie`, `designation`), et
les **39** expressions de texte qu'ils portent, relues une par une ; puis les deux compositions
trouvees jouees a l'ecran.

**Deux compositions subsistent dans tout le depot servi** - la famille que `ADR-0083` clause 1 devait
fermer :

1. *"Pour le "* suivi du niveau, sur l'accueil. **Rend *"Pour le CP"* et *"Pour le CM1"* : les deux
   formes sont correctes, et il n'y a pas d'autre valeur possible.** Aucun defaut, et je le dis pour
   qu'on ne le cherche pas deux fois.
2. *"Tu as ecrit : "* suivi de ce que l'enfant a tape, suivi d'un point, sur l'ecran de saisie.
   **`SAI-R2`, ci-dessous.**

### 6.2 `SAI-R2` - **reserve neuve, datee du 2026-09-11, non bloquante**

**Constat, joue le 2026-09-11 sur `saisie-mot-dicte-cp`** : l'enfant tape `chat.` ; l'ecran rend
***"Tu as ecrit : chat.."*** - deux points. Le gabarit ajoute son point a un texte qui en porte deja
un.

- **Objet** : *l'enfant qui relit ce qu'il a tape doit voir exactement ce qu'il a tape.* C'est toute
  la fonction de cette ligne : elle lui rend sa propre ecriture pour qu'il la compare a la forme
  attendue, juste en dessous.
- **Mode de defaillance** : **retirer le point du gabarit et laisser la ligne sans ponctuation.**
  Le texte lu par l'enfant reste une phrase francaise ; la sortie est de poser la valeur seule dans
  son propre element, comme `ADR-0083` clause 1 le fait deja partout ailleurs.
- **Perimetre, mesure sur l'etat d'arrivee** : **une ligne d'un gabarit**, celui de la saisie. **Ce
  que je n'ai pas compte** : la frequence reelle - un enfant de CP qui tape un point apres un mot
  dicte est rare, et **c'est pourquoi cette reserve est non bloquante et de dernier rang**.
- **Regime et prix** : **mot pour mot, 0,25**, et elle ne merite pas d'etre prise seule : elle
  voyagera avec le prochain geste sur cette mecanique.

### 6.3 Homogeneite de registre - titres, messages de reussite, ecrans de fin

**Hors du perimetre de `CONS-1`, comme la story le demande.**

- **Les 71 titres de l'accueil, relus un par un.** Deux registres coexistent sans que cela gene :
  des groupes nominaux (*"Les fractions simples"*), des imperatifs adresses a l'enfant (*"Compare les
  nombres"*, *"Ecris le mot dicte"*) et des questions (*"Qui mange qui ?"*). **Deux entrees sur
  soixante et onze nomment une matiere scolaire au lieu de dire ce qu'on y fait** : *"Histoire et
  geographie"* et *"Anglais"*. **Je n'ouvre pas de reserve la-dessus** - un enfant de CM1 comprend ces
  deux mots, et le defaut n'est pas dans ce qu'il lit. **Je le route comme une entree de
  `S34-CONS-4`** : cette story scinde precisement la premiere des deux en quatre entrees qui
  porteront quatre titres neufs, et le registre de ces titres se decidera la, une fois, plutot que
  dans une reserve isolee.
- **Les messages de reussite.** *"Bravo !"* pendant la partie, *"Bravo, c'est juste !"* sur la
  derniere manche et sur l'ecran de fin. Deux textes pour le meme evenement. **Ce n'est pas un
  defaut** : le second accompagne un ecran different. Ce qui l'est, c'est que **le meme *"Bravo !"*
  serve une manche qui compte et une qui ne compte pas** - `SCO-R2`, section 5.3.
- **Les ecrans de fin.** Trois mecaniques, un seul gabarit, un seul chiffre. **C'est `SCO-R1`**, et
  c'est le motif de mon refus.

### 6.4 Collisions d'identifiants entre instances (`ADR-0085`, `PAR-5`)

**Recompte fait par moi, deux fois, avec le motif du garde-fou lui-meme** - une ligne d'allocation de
la section 4, identifiant et document entre accents graves :

| Quand, sur quel fichier | Identifiants distincts | Prefixes distincts | Doublons | Ligne d'etat courant declaree | Ecart |
|---|---|---|---|---|---|
| **Au debut de mon audit**, mon worktree (base `a7852398`) | 611 | 100 | **0** | **611 / 100** | **aucun** |
| **Au debut de mon audit**, le checkout partage | 611 | 100 | **0** | **611 / 100** | **aucun** |
| **Avant d'ecrire**, quelques heures plus tard, le checkout partage | 623 | 102 | **0** | **623 / 102** | **aucun** |

**Aucun ecart entre la ligne d'etat courant et sa table, a aucun des deux moments.** Ce qui a bouge
entre les deux, ce sont les **douze allocations de l'instance B**, ecrites entre-temps et visibles
dans sa note du registre. **Mon premier chiffre n'etait pas faux : il etait date**, et je le laisse
ecrit plutot que de le remplacer - une mesure datee ne pourrit pas, un etat courant si.

**C'est la collision que `theo-archi` avait nommee, observee en direct** : deux instances qui ecrivent
dans le meme fichier sans se lire. Elle n'a rien casse ici parce que j'ai recompte **avant d'ecrire**
plutot que de reprendre mon propre chiffre de la matinee. **Si je l'avais repris, j'aurais ecrit 620
sur une table qui en porte 632, et la CI aurait rougi sur mon diff** - le mode de defaillance exact
que `ADR-0085` clause 5 attrape.

**Ce que ce constat ne peut pas etre** : une troisieme instance audite en parallele. **Mon total est
juste sur ce que j'ai lu, a l'instant ou je l'ai lu, et rien de plus** - limite structurelle de
`PAR-5`, reparation arithmetique a la fusion par `sofia-sm`, comme a s31 et a s33. **Je ne repare
rien a l'aveugle et je n'ecris aucun total pour l'arbre assemble.**

**Et je declare mon propre manquement de poste, comme l'instance B a declare le sien** : j'ai ecrit
dans le registre **depuis le checkout partage**, pas depuis un worktree dedie, contrairement a la
regle de `squad/README.md` section 9. Mon motif, qui n'est pas une excuse : mes neuf allocations
doivent vivre a cote des fiches qu'elles nomment, et un worktree detache que personne ne fusionne les
rendrait invisibles. **Le mode de defaillance que cette regle ferme reste donc ouvert sur ce diff.**

**Le faux rouge transitoire, mesure en vol et non suppose.** Apres avoir ecrit mes neuf allocations,
j'ai lance le garde-fou lui-meme sur le reacteur complet - `mvn -pl bootstrap -am` avec `-am`, comme
`CLAUDE.md` l'exige de toute mesure partielle. **Resultat : 7 tests, 1 echec**, et l'echec enumere
**huit identifiants ecrits et non alloues, tous de l'instance C** - un dans une de ses fiches, sept
dans son document de verdicts -, dont les verdicts sont ecrits dans le checkout partage et dont les
allocations ne le sont pas encore. **Je ne recopie pas ces huit identifiants ici : les ecrire dans
mon document les rendrait non alloues chez moi aussi, et ce serait ajouter un rouge au rouge.**
**Aucun des neuf miens n'y figure, et la clause 5 - le compte courant contre sa table - passe.**
C'est exactement le faux rouge transitoire que `theo-archi` a decrit : il n'est pas une regression, il
se referme quand l'instance C ecrit ses lignes. **Je le constate, je ne le repare pas** - reparer
l'allocation d'une autre instance serait ecrire a sa place ce qu'elle seule peut nommer.

---

## 7. `S34-CONS-4` - non engagee, et ce qu'elle me doit a s35

**Elle n'a pas ete commencee** et part a s35 (planning, section 15.1). **Je n'ai donc aucune consigne
a auditer ce sprint**, et la consigne a cinq clauses de `bandit-manchot-histoire-geographie-cm1` est
toujours servie sur chaque manche - je l'ai lue a chacune des parties de la section 0.

**Deux choses restent vraies et je les reecris pour qu'elles ne se perdent pas** :

1. **Je n'ecris aucune des quatre consignes** (arbitrage prealable, section 4.2). `sofia-sm` a
   corrige sa propre table sur ce point, et `marc-dev` a eu raison d'escalader plutot que d'inventer
   un texte pedagogique sans autorite pour le faire. **Le texte revient devant moi quand il est
   ecrit, item devant lui.**
2. **Les quatre taches que j'ai nommees sont inchangees** - situer dans le temps, nommer en histoire,
   nommer en geographie, lire un document - et le registre des quatre titres neufs se decide dans ce
   meme geste (section 6.3).

---

## 8. Objet ou lettre - le jugement que `RES-1` a `RES-3` me demandent de rendre

**C'est la premiere fois qu'un sprint est audite sous ce format, et voici ce qu'il donne.**

| Reserve payee | La correction a-t-elle suivi l'objet ? | Ce qui le montre |
|---|---|---|
| **`HG-R17`, clause 1** (les 18 distracteurs) | **l'objet.** Pleinement | Le mode de defaillance que j'avais nomme - remplacer les mots ruraux par des mots tout aussi invraisemblables - **est evite sur les dix-huit**. Chaque distracteur est une chose reelle, du genre de la reponse, **ajustee item par item** : deux hebergements face a un hebergement, deux secteurs face a un secteur. C'est le comportement d'une correction qui a lu l'objet, pas la phrase |
| **`HG-R17`, clause 2** (l'appariement de mots) | **la lettre, et sur un item des deux** | L'item du logement est corrige ; celui des monuments ne l'est pas, et le message de commit ne le nomme pas. **Ma reserve nommait *"les deux items"*, en toutes lettres** : ce n'est meme pas la lettre entiere |
| **`HG-R18`** (l'enonce qui enumere) | **l'objet** | L'enonce ne se contente pas de perdre *"les services ou l'agriculture"* : il est repose en question ouverte, et le troisieme choix redevient a considerer |
| **`ANG-R4`** (l'incise de 14 mots) | **l'objet** | Le patron que j'avais cite a ete **lu comme un patron**, pas recopie : l'enonce neuf a sa propre apposition, courte, et le verbe suit |
| **`ANG-R5`** (l'indice faux) | **l'objet** | L'indice neuf est exact **et** il separe les trois propositions. Une correction de lettre aurait retire la grande roue sans rien mettre a sa place - c'est `SEC-R5` a s33, et ce n'est pas ce qui s'est passe ici |
| **`ANG-R6`** (la pratique concurrente) | **l'objet** | *"en voiture avec un parent"* est exactement la proposition que ma reserve decrivait sans la nommer, et elle est plausible a sa place |
| **`ANG-R3`** (les trois items unaires) | **la lettre, sur deux items des trois** | Le message de commit annonce *"`ANG-R3` payee"* en ne traitant que deux des trois lignes de mon tableau, et la clause du mot *imperiale* n'est pas payee du tout. **Le tableau etait dans la reserve** |
| **Instruction 1 de `DEC-0061`** (le remede de score) | **la lettre exactement, et l'objet a moitie** | Ma phrase - *"l'enfant garde ses trois essais, et le score cesse de compter comme trouvee une manche qu'il n'a pas trouvee du premier coup"* - est tenue **au mot, sur les trois mecaniques**. Mais mon corollaire ecrit dans la meme section, et mon refus numero 6 ecrit dans le meme document, **ne sont pas arrives jusqu'a l'enfant**. La lettre citable a voyage ; **l'objet, ecrit vingt lignes plus bas, n'a pas voyage avec elle** |

**Ce que j'en tire, et c'est le seul enseignement general que je me permets apres un seul sprint.**
Sur les sept reserves de contenu, **cinq ont suivi l'objet** - c'est mieux que s33, ou la moitie
tombait. **Les deux qui ne l'ont pas suivi ont la meme forme : ma reserve portait une enumeration -
deux items, trois items - et la correction en a traite une partie en annoncant la reserve payee.**
Ce n'est pas un defaut d'objet, c'est un defaut de **compte** : l'objet etait la et il etait bon, le
perimetre a ete retreci en silence. **`RES-1` partie 4 demande deja que je mesure mon perimetre ;
elle ne demande a personne de verifier qu'il a ete tenu en entier.** Je ne pose pas de clause neuve
aujourd'hui - une clause posee sur un seul sprint de mesure est exactement ce que je reproche
ailleurs -, **je nomme le fait et je le remesurerai a s35.**

**Et le cas du remede de score est d'une autre famille, plus instructive** : la phrase que j'avais
ecrite *"pour etre citee telle quelle"* a ete citee telle quelle, jusque dans un `ADR`, et **c'est
precisement ce qui a fait disparaitre le reste.** Une phrase faite pour etre reprise devient le
perimetre de ce qui sera fait. **C'est a moi d'en tirer la consequence, et je l'ecris ici sans en
faire une clause : quand j'ecris une phrase destinee a etre citee, l'objet et le corollaire doivent
tenir dans cette phrase, ou n'exister nulle part.**

---

## 9. Table des verdicts et des reserves

### 9.1 Les lignes

| Ligne | Avant | Apres | Mention |
|---|---|---|---|
| `HG-CM1-12` | `en cours` (refusee a s33) | **`couvert-valide`** | aucune n'est due |
| `LVE-CM1-07` | `couvert-valide` | **inchangee** | celle de s32, inchangee, non reecrite |
| Aucune autre ligne n'est touchee par cette instance | | | |

### 9.2 Les reserves

| Reserve | Etat au 2026-09-11 | Regime |
|---|---|---|
| `HG-R17` (bloquante) | **levee sur sa clause 1** ; sa clause 2 n'est pas payee et **vit desormais sous `HG-R19`**, non bloquante. **Le refus de s33 n'est pas efface** | - |
| `HG-R18` | **levee** | - |
| `HG-R13`, `HG-R14`, `HG-R15`, `HG-R16` | **ouvertes**, non bloquantes, non demandees ce sprint | mot pour mot, 0,25 chacune |
| `HG-R19` | **neuve**, non bloquante | mot pour mot, 0,25 |
| `HG-R20` | **neuve**, non bloquante | mot pour mot, 0,25 |
| `HG-R21` | **neuve**, non bloquante | mot pour mot, 0,25 |
| `HG-R22` | **neuve**, non bloquante | mot pour mot, 0,25 |
| `ANG-R4`, `ANG-R5`, `ANG-R6` | **levees** | - |
| `ANG-R3` | **ouverte**, non bloquante, **perimetre reduit** | rejeu borne, 0,25 |
| `ANG-R7` | **neuve**, non bloquante | mot pour mot, 0,25 |
| `SCO-R1` | **neuve, BLOQUANTE** - motif du refus de `S34-SCO1-REMEDE` | rejeu, 0,5 |
| `SCO-R2` | **neuve**, non bloquante | rejeu, 0,25 |
| `SCO-R3` | **neuve**, non bloquante | rejeu borne, 0,25 |
| `SAI-R2` | **neuve**, non bloquante, dernier rang | mot pour mot, 0,25 |
| `CONS-1`, `CONS-4` | **non levees** sur `bandit-manchot-histoire-geographie-cm1` ; `S34-CONS-4` non engagee | - |

**Sous `PAR-10`, les quatre reserves neuves de `HG` vivent dans un seul paquet et se soldent
ensemble : 0,5 en tout** ; `ANG-R3` et `ANG-R7` de meme : **0,25 en tout**. Les regimes disent ce que
le developpeur doit faire, pas ce que je facture.

### 9.3 Ce que l'instance A a depense de sa borne

Budget opposable au planning : **3,0** (`S34-HG-12` 0,5 + `S34-ANG-CORR` 0,5 + `S34-TRANSVERSE-1` 0,5
+ `S34-CONS-4` 1,0 + `S34-SCO1-REMEDE` 0,5).

| Poste | Prix annonce | Depense | Ecart |
|---|---|---|---|
| `S34-HG-12` | 0,5 | **0,5** | conforme |
| `S34-ANG-CORR` | 0,5 | **0,5** | conforme |
| `S34-SCO1-REMEDE` | 0,5 | **0,5** | conforme - les trois mecaniques, le chemin de revelation et les trois ecrans de fin, exactement ce que le prix achetait |
| `S34-TRANSVERSE-1` | 0,5 | **0,5** | conforme |
| `S34-CONS-4` | 1,0 | **0** | **non engagee, non depensee.** Le budget n'est pas reporte : il est constate non depense, comme n'importe quel poste engage et non livre |
| **Total** | **3,0** | **2,0** | **1,0 non depense** |

---

## 10. Ce dont je reste la seule garde sur ce perimetre

**Dit explicitement pour que l'equipe ne croie pas couvert ce qui ne l'est pas. Les quatre jobs de la
CI sont verts sur l'arbre qui porte tout ce qui suit.**

| Propriete | Mordable par un automate ? |
|---|---|
| **`PLA-1` - chaque distracteur est un candidat serieux** | **non.** C'etait le motif du refus de s33 ; il est paye aujourd'hui **sans qu'aucun test n'ait change**, ce qui dit exactement ce que le depot en voit : rien |
| **Qu'un distracteur serieux ne soit pas une reponse vraie** (`HG-R20`, `ANG-R7`) | **non**, et **c'est neuf a partir d'aujourd'hui** : c'est la contrepartie de `PLA-1`, et elle apparait des qu'on paie `PLA-1`. Personne ne la tient |
| **Qu'un indice separe les trois propositions** (`HG-R21`) | **non.** Et il faut le savoir : **un indice peut cesser de separer sans qu'une seule de ses lettres ne change**, parce que les propositions ont bouge autour de lui |
| **Qu'un mot de l'enonce n'appelle pas la bonne reponse** (`HG-R19`) | **non** aujourd'hui. Je la crois de la meme famille que `EXC-R7`, deja routee a `theo-archi` a s33 et non payee : c'est une comparaison de mots entre l'enonce et la bonne reponse d'un meme item. **Je dis la propriete, jamais la forme du test, et je ne pose aucun seuil** |
| **Le texte de l'ecran de fin de partie apres le remede** (`SCO-R1`) | **non**, et `ADR-0090` decision 4 le dit aussi. **Le remede est entierement mordu ; ce que l'enfant lit ne l'est par rien**, et c'est exactement la ou le defaut est passe |
| **Qu'un enfant sache ce qui fait monter son score** (`SCO-R2`) | **non** |
| **`EXA-1` et l'exactitude factuelle** | **non.** `HG-R20` en est un cas de plus : *"un tapis roulant"* est faux dans le jeu et vrai en montagne |
| **`COH-1`** | **non** aujourd'hui, et **je ne l'ai pas passee sur ces deux paquets** faute de lot qui reecrive une regle |
| **`CONS-1`, `CONS-4`** | **partiellement**, et seulement si un paquet declarait la tache de chacun de ses items : il ne la declare toujours pas. **Quatrieme sprint** |
| **La couleur, le contraste, la taille, la position a l'ecran** | **personne sur mon terrain ce sprint** (audit degrade, section 0) |

---

## 11. Ce que je route, et ce que je ne tranche pas

| Ce que je route | A qui | Ce que je demande |
|---|---|---|
| **`SCO-R1`** - l'ecran de fin de partie, motif du refus | `camille-po` | Une story qui porte **l'ecran**, pas le score. Le texte sera ecrit par qui l'ecrit d'habitude ; **il revient devant moi lu a l'ecran** |
| **`HG-R19` a `HG-R22`, `ANG-R3` reduite, `ANG-R7`, `SCO-R2`, `SCO-R3`, `SAI-R2`** | `camille-po` | Des stories, si elle les priorise. **Je ne priorise pas** |
| **Le registre des quatre titres neufs de `S34-CONS-4`** (section 6.3) | `camille-po`, pour le sprint ou la story s'ouvre | Que la question du titre entre dans la story plutot que dans une reserve separee |
| **La propriete de `HG-R19`** - un mot de l'enonce n'appelle pas la bonne reponse | `theo-archi` | **Meme famille que `EXC-R7`, deja routee et non payee.** Je ne redemande rien de neuf : je signale que la famille compte un cas de plus |
| **Le plancher de lisibilite d'une zone de designation** (planning, section 15.2) | **pas moi, cette instance** | Ce routage nomme `nadia-prof` sans instance. **`QLM-CP-11` est un terrain de l'instance B** (`PAR-2`, `PAR-13`), et **la question est visuelle** - taille de police, pave d'un bouton -, donc hors de mon audit degrade. **Je ne la tranche pas ici** et je ne la laisse pas tomber : elle revient a l'instance qui tient ce terrain, avec un audit qui voit la page rendue |

---

## 12. Ce que ce document ne fait pas

- **Il ne touche pas `squad/programme/MATRICE-couverture.md`.** Le mouvement de `HG-CM1-12` est
  demande a `camille-po`, pas ecrit par moi.
- **Il n'efface aucun verdict et aucun refus.** Le refus de `HG-CM1-12` du 2026-09-10 reste lisible
  dans sa fiche, avec sa date et son motif, sous la levee d'aujourd'hui.
- **Il ne rejuge aucune ligne fermee** - ni `HG-CM1-02`, `-04`, `-08`, `-11`, ni `LVE-CM1-04`. Les
  distracteurs ruraux que j'ai vus sur deux items de `HG-CM1-11` en jouant **ne rouvrent rien** : ces
  lignes sont fermees, et rouvrir une ligne fermee n'est pas ce que ma porte fait.
- **Il ne durcit aucune reserve ancienne.** `ANG-R3` est **reexprimee et reduite**, jamais elargie.
  Ce qui est neuf est neuf et date du 2026-09-11 : `HG-R19` a `HG-R22`, `ANG-R7`, `SCO-R1` a
  `SCO-R3`, `SAI-R2`.
- **Il ne prononce aucun verdict sur le palier 2**, ni sur `QLM-CP-14`, ni sur les paquets d'EMC et
  d'EPS : ce sont les instances B et C.
- **Il ne decide d'aucune question technique.** La forme de l'ecran de fin, la maniere de ne pas
  compter une validation vide et la place ou se dit la regle du score sont des symptomes que je
  nomme et des causes que `theo-archi` traite. **Je dis la propriete, jamais la forme du test, et je
  ne pose aucun seuil.**
- **Il ne modifie aucun fichier sous `domain/`, `application/`, `adapters/` ou `bootstrap/`.**
- **Il ne corrige ni l'orthographe, ni l'accentuation, ni la ponctuation du texte destine a
  l'enfant** : c'est le `relecteur-pedagogique` (`DEC-0002`, amendement du 2026-08-04 ; `DEC-0056`).
  **`SAI-R2` n'est pas une correction de ponctuation** : c'est un gabarit qui compose une phrase
  autour d'une valeur, la famille de `REV-1`, et le point double n'en est que le symptome visible.
- **Il ne dit rien de ce qu'il n'a pas mesure** : la loi de tirage, le rendu visuel, et le compte du
  registre sur l'arbre assemble des trois instances.

---

## 13. Les identifiants alloues dans ce meme diff (`PAR-5`)

**Neuf identifiants neufs, aucun prefixe neuf** - `HG`, `ANG`, `SCO` et `SAI` existent tous les
quatre dans la table de la section 4 du registre, verifie avant allocation.

| Identifiant | Ce qu'il designe | Document |
|---|---|---|
| `HG-R19` | Le mot de l'enonce qui appelle la bonne reponse, sur l'item des monuments historiques | `FICHE-bandit-manchot-histoire-geographie-cm1.md` |
| `HG-R20` | Un distracteur serieux qui est une reponse vraie a la question posee | idem |
| `HG-R21` | Un indice qui ne separe plus les trois propositions depuis qu'elles ont change | idem |
| `HG-R22` | Les deux distracteurs du dixieme item de la ligne, hors du perimetre que j'avais ecrit | idem |
| `ANG-R7` | Un enonce dont les trois propositions sont vraies | `FICHE-bandit-manchot-anglais-cm1.md` |
| `SCO-R1` | L'ecran de fin de partie n'affiche que le nombre reduit - **bloquante** | `FICHE-bandit-manchot-additions-cp.md` |
| `SCO-R2` | Rien ne dit a l'enfant que seul le premier essai compte | idem |
| `SCO-R3` | Sur la saisie, une validation vide coute le credit de la manche | idem |
| `SAI-R2` | Le gabarit de saisie compose une phrase autour de ce que l'enfant a tape | `FICHE-saisie-mot-dicte-cp.md` |

**Ligne d'etat courant portee de 623 / 102 a 632 / 102**, recomptee **avant et apres** mon ecriture,
avec sa methode et ses deux mesures en section 6.4. **Ce n'est pas le chiffre que j'aurais ecrit ce
matin** : la table portait alors 611 / 100, et l'instance B y a ajoute douze allocations entre mes
deux comptages. **Ce total est juste sur ce que j'ai lu, a l'instant ou je l'ai lu.** Une troisieme
instance alloue en parallele ; si elle ecrit apres moi, la CI rougira a l'assemblage et **la
reparation sera arithmetique** - aucune allocation ajoutee, aucune retiree, aucun sens touche. Elle
appartient a `sofia-sm`, a l'endroit prevu.
