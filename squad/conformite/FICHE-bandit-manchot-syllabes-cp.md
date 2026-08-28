# Fiche de conformite - bandit-manchot-syllabes-cp

- **Mecanique** : bandit manchot (mecanique generique du catalogue, conditions `M1` a `M13`,
  `FICHE-bandit-manchot-additions-cp.md`). Cette fiche n'audite pas la mecanique : elle audite le
  paquet servi au travers d'elle.
- **Contenu servi** : `adapters/adapter-content/src/main/resources/contenu/cp-syllabes.yaml`,
  `type: question`, **16 items**, story `S21-9`, ecrit par `marc-dev`.
- **Entree de catalogue** : `bandit-manchot-syllabes-cp`, titre *"Les syllabes"*, consigne
  *"Tape dans tes mains. Combien de syllabes ?"*, 10 manches.
- **Competence visee** : Francais CP, domaine "Langage oral et conscience phonologique",
  *"Denombrer les syllabes orales d'un mot (segmentation syllabique)"*
  (`squad/programme/francais/competences-cp.md`, ligne 9 ; verifie le 2026-08-27).
- **Conditions prealables** : `SEG-1` a `SEG-9`,
  `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md`, section 3.
- **Statut global** : **REFUSE par `nadia-prof` le 2026-08-28**, sur **un motif unique et isole**
  (item `CHEMINEE`). Quinze items sur seize sont exacts et toutes les autres conditions sont
  tenues ; le detail est ecrit ci-dessous pour que la reprise ne refasse pas le travail deja bon.
- **Derniere mise a jour** : 2026-08-28 (`nadia-prof`, audit de s21).

## Table de correspondance des jetons de cette fiche

Convention exigee par `squad/README.md`, section 2, amendement s16 : toute famille de jetons neuve
ou toute extension introduite par `nadia-prof` est reprise dans une table unique portee par la
fiche du jeu concerne.

| Jeton | Ce qu'il designe | Ou il est ecrit |
|---|---|---|
| `SEG-1` a `SEG-9` | **Conditions prealables** a l'ecriture, posees le 2026-08-27 **avant** que le paquet existe | `ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md`, section 3.3 |
| `SEG-10` et suivants | **Reserves ouvertes a l'audit** du 2026-08-28, sur ce que j'ai vu en jouant et que je ne pouvais pas voir avant | cette fiche, section 6 |
| `M1` a `M13` | Conditions de la **mecanique**, hors du perimetre de cette fiche | `FICHE-bandit-manchot-additions-cp.md` |

**Regle de lecture** : `SEG-1` a `SEG-9` n'ont pas ete durcis. Une reserve numerotee `SEG-10` ou
au-dela est **nouvelle et datee**, jamais un resserrement retroactif d'une condition anterieure.

## 1. Ce que j'ai fait, et ce que ma methode ne couvre pas

**J'ai joue, puis j'ai mesure**, dans cet ordre.

L'application a ete construite depuis cet arbre (`clean package -DskipTests`, lance par
`bash outils/mvn-jdk21.sh`) et servie par `java -jar bootstrap/target/recreo.jar
--server.port=<port>` sur un JDK 21. Le serveur a ete arrete **par son identifiant de processus**,
jamais par un nom d'image.

**Parties jouees le 2026-08-27**, par l'interface HTTP de l'application, en me trompant
volontairement : trois parties completes de dix manches - l'une en repondant juste du premier coup,
l'une en me trompant une ou deux fois par manche, l'une en brulant les trois essais pour voir ce
qu'un enfant bloque recoit. **J'ai lu chaque ecran** : consigne, enonce, libelles, verdict apres
une bonne reponse, verdict apres un echec, indice, message de deblocage, ecran de fin.

**Mesure de rendu**, meme date, viewport de reference de `DEC-0040`, **744 x 551**, navigateur avec
`--hide-scrollbars`, sur le **paquet entier et non sur les dix manches d'une partie** :

```
CHROME=<navigateur> PAQUET=adapters/adapter-content/src/main/resources/contenu/cp-syllabes.yaml \
    PORT_DEV=9381 python squad/outils/nadia-prof/auditer-un-paquet-entier.py \
    http://<hote>:<port> bandit-manchot-syllabes-cp 16 \
    squad/conformite/releves-s21/s21-nadia2-syllabes-paquet-entier-744x551.json
```

Le releve est commite. Le viewport qu'il porte (`[744, 551]`) est **lu du navigateur**, pas recopie
de l'argument.

**Limites, avec l'etat exact qu'elles couvrent :**

- **Un seul viewport, 744 x 551.** **360 px n'est pas couvert** et rien de ce que je dis ici sur la
  geometrie n'y vaut.
- **Aucun enfant n'a joue ce paquet.** Tout ce qui suit sur la charge de lecture, sur la coupe
  syllabique en classe et sur ce qu'un enfant fait d'un *"Bravo !"* est un **jugement de
  professeure**, pas une observation.
- **L'outillage de mesure de ce repertoire s'attache au premier navigateur qui repond sur un port de
  deverminage fixe, sans verifier que c'est le sien** (defaut trouve par `theo-archi` le
  2026-08-27, story `S21-1`). J'ai donne a chaque execution un port distinct (9381, 9383, 9385,
  9393, 9395, 9397, 9403) : c'est une mitigation, pas une garantie.
- **Aucun garde-fou du depot ne verifie ce qui suit**, a une exception nommee en section 7.

## 2. Le motif du refus

### `CHEMINEE` (item 16) n'a pas une bonne reponse : il en a deux

Le paquet declare `bonneReponse: "3"` pour `CHEMINEE`. **Che-mi-nee vaut trois syllabes en forme de
citation et deux dans la prononciation ordinaire** : *ch'minee*, avec chute du `e` caduc de la
premiere syllabe, est du francais standard et non un relachement. Un enfant de CP qui tape deux
fois dans ses mains a raison, et l'application lui repond qu'il a tort.

**Verifie en jouant, le 2026-08-27**, manche 9 d'une partie : sur l'enonce `CHEMINEE`, l'appui sur
`2` rend *"Ce n'est pas ca. Essaie encore !"* ; apres trois essais, l'ecran affiche *"C'est 3.
Appuie sur 3."* et **l'enfant doit presser `3` pour avancer**. L'application ne se contente donc pas
de refuser une reponse defendable : elle exige que l'enfant valide celle qu'elle a choisie.

**Ce motif est l'application de `SEG-1`, pas un durcissement.** `SEG-1` exclut nommement *"les mots
ou un `e` interieur peut disparaitre a l'oral (`samedi` se dit couramment en deux syllabes)"*, et
son motif ecrit est : *"un item dont deux comptes sont defendables n'a pas une bonne reponse, il en
a deux, et c'est une erreur factuelle."* J'ai ecarte `parapluie` et `dinosaure` du fichier repris
pour **une seule** instabilite chacun. `CHEMINEE` porte exactement la meme, au meme titre. Le
laisser passer ne serait pas de la mesure : ce serait rendre `SEG-1` decorative trois heures apres
l'avoir ecrite.

**Precision que je porte contre moi** : mon exemple disait `samedi`, ou le `e` instable est en
deuxieme syllabe, et `CHEMINEE` le porte en premiere. **C'est le mecanisme que `SEG-1` nomme, pas
une position** ; je le dis explicitement pour qu'on ne me lise pas comme etendant ma condition apres
coup.

**Ce que ce defaut coute, en chiffre et avec sa methode.** Une partie tire **10 manches sans
remise** parmi les 16 items, uniformement sur les items non encore vus (`ListContentSource.next`,
relu ligne a ligne le 2026-08-27 : `items.stream().filter(item -> !dejaVus.contains(item.id()))`
puis tirage uniforme sur les restants). Par symetrie, chaque item a donc **10/16 = 0,625** de
probabilite de sortir dans une partie donnee. **Un enfant rencontre cet item dans pres de deux
parties sur trois.**

**Ce qui repare le motif, sans rien demander d'autre** : remplacer `CHEMINEE` par un mot de trois
syllabes a coupe stable et dechiffrable au CP, pour conserver la repartition 5/6/5 verifiee en
section 4. `SEG-1` a `SEG-9` restent la specification ; **je n'en ajoute aucune**, et je ne dicte
pas le mot - c'est le travail de `marc-dev`, pas le mien.

**Ce que ce refus n'est pas.** Ce n'est pas un jugement sur le reste du paquet, qui est bon, ni sur
la forme, ni sur l'ancrage au programme, ni sur le pli. Les sections 3 a 5 disent ce que j'ai
verifie et qui tient, pour que la reprise ne recommence pas ce travail.

## 3. Ancrage au programme : verifie, et il tient

La competence annoncee par le descripteur du paquet est **mot pour mot** la ligne 9 de
`squad/programme/francais/competences-cp.md`. **L'increment travaille cette competence-la** et non
une voisine plus facile : la tache demandee est de compter des syllabes, la reponse attendue est un
nombre, et rien d'autre n'est demande.

**La transposition d'un support oral vers un support ecrit tient**, pour le motif que j'ai ecrit
avant l'ecriture du paquet et que je maintiens apres l'avoir joue : l'objet de la tache est de
**compter**, la reponse est un **nombre**, et montrer le mot est la donnee de l'exercice, pas sa
reponse. C'est ce qui distingue ce paquet du proxy ecrit de *"Decoder un mot regulier inconnu par
assemblage syllabique"*, que j'ai refuse le 2026-08-24 et dont le refus tient
(`ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md`, section 3).

**La fuite que j'avais nommee reste ouverte, et le fait de jouer ne l'a pas changee** : un enfant
peut compter les **groupes de lettres** au lieu des **syllabes entendues**, et sur les seize mots de
ce paquet les deux routes donnent le meme nombre. **Ce paquet ne distinguera jamais les deux
enfants.** Je l'avais assume, je le reconfirme, et j'en tire la meme consequence de statut : la
ligne pourra passer `couvert-valide` sur un paquet corrige, **mais le domaine "conscience
phonologique" ne sera pas traite pour autant** - ses deux autres lignes restent hors d'atteinte sans
emission de son (`DEC-0042`, arbitrage 2).

## 4. Les conditions `SEG-1` a `SEG-9`, verifiees une par une

| Condition | Verdict | Ce que j'ai verifie, et comment |
|---|---|---|
| `SEG-1` coupe stable | **TENUE SUR 15 ITEMS SUR 16** | Les seize comptes relus a la main le 2026-08-27. Quinze sont exacts et stables. `CHEMINEE` est le seul defaut : c'est le motif de la section 2 |
| `SEG-2` dechiffrable au CP | **TENUE, 16 sur 16** | `roi` (`oi`), `dos` (`s` muet final), `coussin` (`ou`, `in`), `domino`, `escargot`, `kangourou` (`an`, `ou`, `g` dur) sont decodables en fin de CP. **Correction que je porte contre moi** : ma redaction de `SEG-2` enumerait des graphemes *"du referentiel"* en omettant `ai`, alors que ma propre table du meme jour retenait `maison` **pour** son `ai`. Le referentiel ne donne aucun inventaire ferme (`competences-cp.md`, ligne 15, se termine par des points de suspension) : ma liste etait une construction personnelle et incomplete, et **je ne refuse rien sur elle** |
| `SEG-3` plage 1-3, trois memes valeurs a chaque item | **TENUE, 16 sur 16** | Verifie dans le fichier **et a l'ecran** : les trois boutons portent toujours `1`, `2`, `3`. Leur **ordre change a chaque manche** (`1 2 3`, `1 3 2`, `2 1 3`, `3 1 2` releves en jouant), ce qui oblige a lire le chiffre et interdit de memoriser une position. Le comptage d'etiquettes fait avant l'ecriture tient : trois etiquettes, trois reperes, aucun cas supplementaire a loger |
| `SEG-4` plage 1-4 | sans objet | L'auteur a choisi `SEG-3`, la route la moins risquee. Rien a verifier |
| `SEG-5` au moins 16 items | **TENUE** | 16 items exactement |
| `SEG-6` equilibre des reponses | **TENUE** | Compte a la main : **1 sur 5 items** (`CHAT`, `RIZ`, `LOUP`, `ROI`, `DOS`), **2 sur 6** (`PAPA`, `LAPIN`, `BATEAU`, `CADEAU`, `MAISON`, `COUSSIN`), **3 sur 5** (`CHOCOLAT`, `DOMINO`, `ESCARGOT`, `KANGOUROU`, `CHEMINEE`). Barre : entre 4 et 8 par valeur sur 16 items. 5, 6 et 5 sont dedans. La repartition annoncee 5/6/5 est exacte |
| `SEG-7` indice qui aide sans donner le compte | **TENUE, 16 sur 16** | Les seize indices lus a l'ecran apres un echec. **Aucun ne donne le compte** et aucun ne repete la consigne. Pour les mots de 2 et 3 syllabes, l'indice nomme la premiere syllabe (*"Ce mot commence par << cho >>."*) ; pour les mots d'une syllabe, il donne un procede (*"Tape dans tes mains en disant le mot tout doucement."*). **Verification supplementaire que le patron appelait** : les cinq formulations de relance (*"Continue a taper."*, *"Tape le reste du mot."*, *"Tape la suite du mot."*, *"Continue a taper les autres morceaux."*, *"Continue a taper les morceaux suivants."*) sont **brassees entre les items a 2 et a 3 syllabes** - aucune ne correle avec la reponse. C'est delibere et c'est bien fait |
| `SEG-8` consigne qui nomme le geste oral | **TENUE** | *"Tape dans tes mains. Combien de syllabes ?"* - deux phrases courtes, le geste puis la question, une ligne a l'ecran (mesure, section 5). C'est le patron de `cp-le-bon-son` |
| `SEG-9` accents conserves sur les capitales | **TENUE** | `CHEMINEE` est le seul mot accentue du paquet et il s'affiche **avec son accent** a l'ecran, verifie en jouant. La condition est tenue ; le mot part pour une autre raison |

## 5. Le pli : mesure sur le paquet entier, pas sur un echantillon

Mesure du **2026-08-27**, a **744 x 551**, commande citee en section 1, releve
`squad/conformite/releves-s21/s21-nadia2-syllabes-paquet-entier-744x551.json`. **Population : les 16
items du paquet, tous vus, en 4 parties. Ce n'est pas un echantillon.**

| Grandeur lue, apres un echec | Pire valeur sur les 16 items |
|---|---|
| Rangees de propositions | **1** |
| Lignes de l'enonce | **1** |
| Lignes de l'indice | **1** |
| Somme des trois libelles | **55,23 px** sur un budget de 522 px, soit **10,6 %** |
| Bas du verdict | **407 px** |
| Bas de l'indice | **486 px**, soit **65 px au-dessus du pli de 551** |
| Defilement | **0** |

**16 items sur 16 sont entierement au-dessus du pli, verdict et indice compris.** C'est l'objet du
sprint, et il est atteint sur ce paquet.

**Un fait a porter au dossier de `S21-2`, parce qu'il change la lecture d'une mesure.** L'outil du
depot mesure chaque item **deux fois**, avec et sans les deux declarations de `S21-2` injectees. Sur
cet arbre fusionne, **les deux colonnes sont identiques au centieme sur les 16 items** (486 px des
deux cotes). Ce n'est pas une anomalie : `S21-2` **est deja dans la feuille servie**
(`.score{margin:0.25rem 0}` ligne 1107, `.proposition{padding:0.5rem 1.25rem}` ligne 737,
`recreo.css`, relues le 2026-08-27). L'injection ne fait que reposer une valeur deja posee. **Une
mesure "avec et sans" sur cet arbre ne mesure donc plus rien**, et un lecteur pourrait en conclure a
tort que `S21-2` est sans effet. Ce que j'ai mesure a la place est en section 8 de
`FICHE-bandit-manchot-comparer-cp.md`.

## 6. Reserves ouvertes a l'audit - **nouvelles et datees**, pas un durcissement

Elles n'entrent pas dans le motif du refus. Je les ecris pour que `camille-po` les ait au moment de
faire du backlog, et je les numerote hors de `SEG-1`..`SEG-9` pour qu'on ne les confonde pas avec
mes conditions prealables.

- **`SEG-10` (2026-08-28) - le retour apres une bonne reponse est attache au mauvais mot.** J'avais
  assume, avant l'ecriture, que ce paquet n'aurait **aucun** retour pedagogique apres une bonne
  reponse. **Ma decision tient, et je m'etais trompee sur un fait.** En jouant, j'ai vu que la
  mecanique ne se contente pas d'afficher un *"Bravo !"* vide : elle **re-rend l'ecran entier avec
  le mot suivant**, si bien que le *"Bravo !"* du mot repondu s'affiche **a cote du mot d'apres**.
  Releve textuel du 2026-08-27, apres avoir repondu juste sur `CADEAU` : *"Score : 1 / Tape dans tes
  mains. Combien de syllabes ? / COUSSIN / 1 3 2 / Bravo !"* - le mot `CADEAU` n'est plus a l'ecran.
  **Sur ce paquet-ci, ou l'enseignement *est* la segmentation du mot qu'on vient de compter, il
  n'existe aucun instant ou le mot et sa coupe sont montres ensemble.** Cela reste une **reserve et
  non un motif de refus** : le defaut est dans la mecanique, partagee par vingt autres jeux deja
  valides, pas dans ce paquet. Je le route inchange a `theo-archi` et `camille-po`, avec l'obstacle
  deja ouvert : le champ `explication` (`ADR-0024`) est verrouille derriere `graphie` par
  `ChaquePaquetExpliqueSesItemsAVariationTest`, et **je continue de ne pas autoriser** qu'un paquet
  de syllabes declare une `graphie` pour contourner ce garde-fou - ce serait detourner le sens d'un
  champ de phonologie.
- **`SEG-11` (2026-08-28) - l'ecran de fin ne revient sur rien.** Apres la dixieme manche, l'enfant
  lit *"Score : 10 / Bravo, c'est juste ! / C'est fini !"* et un bouton *"Les jeux"*. Aucun retour
  sur les mots rates. Sur un jeu de denombrement, c'est le dernier endroit ou la coupe pourrait etre
  montree. **Constat de mecanique, a `camille-po`** ; il ne pese pas sur ce paquet.
- **`SEG-12` (2026-08-28) - le bouton fautif est barre, et c'est bien, mais l'enfant peut le
  represser.** Verifie a l'ecran : apres un echec, la proposition pressee recoit la classe
  `deja-essayee` et s'affiche barree (`recreo.css`, ligne 1305). Elle **reste cliquable**, et un
  second appui dessus consomme un essai. Sur trois essais, un enfant de CP peut en bruler deux sur
  le meme bouton. **Constat de mecanique, hors de ce paquet**, route a `camille-po` pour arbitrage :
  c'est une question de conception, pas un defaut de contenu, et je ne la tranche pas ici.

## 7. Sur quelles proprietes je reste la seule garde

Je les nomme pour que personne ne croie couvert ce qui ne l'est pas. **Quatre defauts visibles par
un enfant ont deja traverse une CI entierement verte sur ce produit.**

- **Neuf conditions, dont une seule partiellement tenue par un test.** La regle 1 du contrat de
  forme (`S21-5`, au plus trois propositions par item) couvre la **clause de nombre** de `SEG-3`, et
  rien de plus : ni la plage des reponses, ni la constance du jeu de propositions. **`SEG-1`,
  `SEG-2` et `SEG-5` a `SEG-9` n'ont aucun garde-fou et n'en auront pas** - un classifieur qui
  deciderait tout seul si une coupe syllabique est stable se tromperait, et une regle qui passe pour
  une mauvaise raison est pire qu'une regle absente.
- **L'exactitude des seize comptes de syllabes.** Personne d'autre ne les relit. **C'est exactement
  la propriete sur laquelle ce paquet echoue aujourd'hui, et aucune CI ne l'aurait dit** : la porte
  technique de s21 est verte a 722 tests, 0 echec.
- **Qu'un paquet qui passe la porte de forme enseigne encore quelque chose.** Non mecanisable.
- **Le pli a 744 x 551.** Aucun test du depot ne mesure ce que l'enfant voit sans defiler ; la
  mesure de la section 5 s'appuie sur un outillage a navigateur, hors `mvn verify`.

## 8. Ce que j'ai mesure sur `S21-2` et sur le catalogue

La mesure porte sur le catalogue et non sur ce paquet. Elle est ecrite une seule fois, en section 8
de `FICHE-bandit-manchot-comparer-cp.md`, et n'est pas recopiee ici pour qu'elle ne puisse pas y
pourrir a moitie.

## 9. Ce qui appartient a d'autres, et que je ne touche pas

- **Le retrait de l'entree `bandit-manchot-syllabes-cp` du catalogue.** `DEC-0045`, section 3,
  attache ce geste a un `REFUSE`. **Il appartient a `camille-po` et `sofia-sm`, pas a moi** : je n'ai
  touche ni `catalogue.yaml`, ni aucun fichier de contenu, et je n'ai rien pousse ni fusionne.
- **La reecriture de l'item.** A `marc-dev`, par une story de `camille-po`.
- **La ligne 485 de `MATRICE-couverture.md`** reste **`non couvert`** et ne bouge pas aujourd'hui.

---

## Verdict du 2026-08-28 (s21) - `S21-9`, "Les syllabes"

## Verdict : **REFUSE**

**Motif, unique et actionnable sans me redemander ce que je voulais dire** : l'item `CHEMINEE`
(item 16) declare `bonneReponse: "3"` alors que *che-mi-nee* vaut **trois syllabes en forme de
citation et deux dans la prononciation ordinaire** (*ch'minee*, chute du `e` caduc de la premiere
syllabe). L'item n'a pas une bonne reponse : il en a deux. Verifie **en jouant** le 2026-08-27 -
l'appui sur `2` rend *"Ce n'est pas ca. Essaie encore !"*, puis *"C'est 3. Appuie sur 3."* apres
trois essais, l'application exigeant de l'enfant qu'il valide la reponse qu'elle a choisie. C'est le
mecanisme que `SEG-1` exclut nommement, celui pour lequel j'ai ecarte `parapluie` et `dinosaure` du
fichier repris. **Un item sur seize ; une partie en tire dix sans remise, donc l'enfant le rencontre
avec une probabilite de 10/16 = 0,625.**

**Correction demandee** : remplacer `CHEMINEE` par un mot de trois syllabes a coupe stable et
dechiffrable au CP, en conservant la repartition 5/6/5 de `SEG-6`. Aucune autre correction n'est
demandee, et **aucune condition nouvelle n'est posee** : `SEG-1` a `SEG-9` restent la specification.

**Ce que j'ai verifie et qui tient**, pour que la reprise ne le refasse pas : les quinze autres
comptes sont exacts ; `SEG-2` a `SEG-9` sont tenues, y compris le brassage des formulations
d'indice entre 2 et 3 syllabes, qui est du bon travail ; l'ancrage a la ligne 9 de
`competences-cp.md` est exact et l'increment travaille bien **cette** competence ; **les 16 items
sur 16 sont entierement au-dessus du pli** a 744 x 551, indice compris, avec 65 px de marge et zero
defilement.

**Ce que ce refus ne fait pas** : il ne bloque pas le sprint suivant. Il renvoie l'increment a
`camille-po`, qui en fait du backlog. Le retrait de l'entree du catalogue releve de `DEC-0045`
section 3 et **appartient a `camille-po` et `sofia-sm`, pas a moi**.

**Sur la question que `camille-po` m'a routee comme obstacle transverse** (un enfant de CP qui
compte juste et ne recoit qu'un *"Bravo !"* apprend-il quelque chose ?) : ma decision d'accepter
l'absence de retour pedagogique **tient** - mais **je m'etais trompee sur un fait**, le retour n'est
pas vide, il est **attache au mot suivant** (`SEG-10`). Cela reste une reserve et non un motif : le
defaut est dans la mecanique, pas dans ce paquet.

- **Auditrice** : `nadia-prof`. **Verdict prononce le** 2026-08-28. **Mesures et parties** :
  2026-08-27, entre 22:02 et 22:35. **Arbre audite** : `round-two` a `8e21613`, reconstruit, servi
  et joue depuis le worktree `s21-nadia-2`.
- **Audit non degrade** : l'application a ete construite, servie et **jouee**.
