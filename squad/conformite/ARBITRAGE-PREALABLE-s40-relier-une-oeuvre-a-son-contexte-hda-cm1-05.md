# Arbitrage prealable - s40, `S40-HDA` (`HDA-CM1-05`)

- **Auteur** : `nadia-prof`. **Date** : 2026-09-23. **Sprint** : s40.
- **Ce qui m'est pose** : `DEC-0093`, section 5, et la story `S40-HDA` de
  `docs/backlog/backlog.md`, section "Sprint 40" - arbitrage pedagogique prealable, avant tout avis
  de faisabilite de `theo-archi`. Question posee explicitement par `camille-po`, non prejugee :
  quel sous-ensemble d'oeuvres et de faits de contexte est assez etabli et non discute pour porter
  un item sur `HDA-CM1-05`, et comment le distracteur reste-t-il ecarte du risque `HIS-2` ?
- **Audit degrade, et je le declare** : aucun paquet n'existe encore pour cette ligne
  (`non couvert` dans `MATRICE-couverture.md`). Il n'y a rien a jouer. Ce que j'ai fait a la place :
  relu ma propre fiche de referentiel (`squad/programme/histoire-des-arts/competences-cm1.md`,
  ligne 5), relu `HIS-2`/`HIS-3` tels que je les ai ecrits (`ARBITRAGE-PREALABLE-s27...`, section
  7.5), relu `ART-1` a `ART-5` (`squad/programme/arts-plastiques/competences-cm1.md`), et verifie
  moi-meme `DEC-0075` plutot que de presumer son perimetre.
- **Lien** : `DEC-0093`, `squad/programme/histoire-des-arts/competences-cm1.md`,
  `squad/programme/MATRICE-couverture.md` (ligne 1941), `DEC-0075` (et `DEC-0077`),
  `squad/conformite/ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md` (`HIS-1` a
  `HIS-3`), `squad/programme/arts-plastiques/competences-cm1.md` (`ART-1` a `ART-5`).

---

## 1. Verdict

**`S40-HDA` est instruisible en l'etat.** Je fixe ci-dessous les criteres qui font tenir `HIS-2`
sur cette ligne. Ils ne sont pas une relaxation d'une exigence anterieure : `HIS-2` reste entiere,
je dis seulement comment un item peut la satisfaire ici. Aucun contenu factuel ni aucune oeuvre
n'est choisi par ce document - cela reste, comme la story le prevoit, entierement a moi au moment
ou je jouerai le paquet.

## 2. `DEC-0075` ne s'applique pas a cette ligne - verifie, pas suppose

`DEC-0075` (et son arbitrage `DEC-0077`) porte sur le **droit de servir une image de
reproduction** d'une oeuvre. Son tableau (section 2) liste neuf lignes bloquees : arts plastiques
CP 9-10, arts plastiques CM1 7-8, histoire des arts CM1 **1, 2, 3, 4, 6**. `HDA-CM1-05` (ligne 5
du referentiel) n'y figure pas - je l'avais deja note dans le referentiel comme "la seule ligne de
la matiere sans image requise", et je le reconfirme en relisant le tableau de `DEC-0075` lui-meme.

**Consequence directe du critere que je fixe en section 3** : puisqu'aucun item ne nomme une
oeuvre individuelle (voir `HDA-1`), la question meme de "quelles oeuvres sont eligibles" au sens
d'un corpus a identifier (titre, auteur, date, lieu - `ART-3`) ne se pose pas pour cette ligne.
`ART-3` reste ecrit pour "toute oeuvre servie en image" ; cette ligne n'en sert aucune. Ce n'est
pas une exemption que j'ouvre, c'est un perimetre qui ne rencontre jamais la clause.

## 3. Critere de forme - `HDA-1` : le style, jamais l'oeuvre unique

**`HDA-1` - Un item de cette ligne decrit les caracteristiques d'un type/style/genre artistique
etabli au programme de cycle 3 (par exemple : architecture romane, architecture gothique, temple
de l'Antiquite grecque ou romaine, art funeraire egyptien antique), jamais celles d'une oeuvre
individuelle nommee.** L'enonce reprend la forme deja validee dans le referentiel ("une eglise
dont les fenetres sont immenses et les murs sont perces") : il decrit, il ne montre pas, et il ne
designe aucun titre d'oeuvre precis.

**Motif.** C'est le risque que je nommais deja dans le referentiel : le contexte d'une oeuvre
*individuelle* admet souvent plusieurs lectures - l'intention d'un artiste precis, la datation
exacte d'un edifice precis, la fonction disputee d'un monument precis sont des objets de debat
d'historiens, pas des connaissances de cycle 3 a bonne reponse unique. Le contexte d'un **style**
(quelle periode, quelle civilisation, quel usage produit typiquement des colonnes et un fronton
triangulaire, ou des arcs brises et de grands vitraux) est au contraire une classification stable,
enseignee comme telle en cycle 3, et sourcable. `HDA-1` deplace l'item du terrain ou `HIS-2` mord
vers le terrain ou il ne mord pas.

Ce choix ne restreint pas la competence a une lecture plus facile : `HDA-CM1-05` demande
explicitement de relier une **caracteristique** a un **usage et un contexte historique et
culturel**, et c'est exactement ce qu'une classification de style fait - contrairement a
`HDA-CM1-04` (associer une oeuvre a une epoque *a partir d'elements observes*), qui suppose de
voir l'oeuvre et reste donc a bon droit bloquee par `DEC-0075`.

## 4. Faits de contexte admissibles - `HDA-2`

**`HDA-2` - Le fait de contexte demande en reponse est une classification etablie (periode,
civilisation, usage/fonction) attachee au style decrit par l'enonce, jamais une interpretation,
une intention d'artiste ou un fait date avec precision disputee chez les historiens.** Sont
admissibles : la periode (Antiquite, Moyen Age...), la civilisation ou aire culturelle (Grece
antique, Egypte antique, Europe medievale chretienne...), l'usage (lieu de culte, tombeau,
residence de pouvoir, defense). Sont exclus : pourquoi un artiste precis a fait tel choix, ce
qu'une oeuvre precise "represente" ou "exprime" (c'est `HDA-CM1-07`, deja jugee
`non couvrable en l'etat`), et toute datation ou attribution plus precise que ce que la
classification de style permet raisonnablement.

Chaque fait de contexte cite garde l'obligation deja posee par `HIS-3` : il porte sa source dans
le commentaire du paquet. Je ne suis pas la source de l'histoire des arts, je le redis comme je
l'ai deja ecrit dans le referentiel.

**Remarque sur `ART-1`.** Un enonce de cette ligne peut nommer une couleur comme trait decrit
("des vitraux aux couleurs vives") sans que `ART-1` morde : `ART-1` protege un enfant qui ne peut
pas *percevoir* une couleur affichee a l'ecran. Ici aucune image n'est affichee, la couleur est un
mot lu comme les autres. `ART-1` ne s'applique donc pas a cette ligne faute d'image - je le
precise pour eviter qu'un futur audit croie la clause engagee alors qu'elle ne rencontre jamais
son propre motif ici.

## 5. Distracteurs - `HDA-3`

**`HDA-3` - Chaque proposition fausse est elle-meme un style/periode/civilisation reel et etabli,
jamais une categorie inventee ni une affirmation fausse sur ce style lui-meme ; elle est ecartee
de la bonne reponse par un marqueur specifique au style vise, pas par un trait que les deux styles
partagent.** Deux consequences operationnelles :

- Un distracteur ne dit jamais quelque chose de faux sur lui-meme (ex. ne jamais faire porter
  "art egyptien" par une propriete qui n'est pas la sienne) : il est vrai en tant que style, faux
  seulement en tant que reponse a *cet* enonce. C'est la difference entre une **fausseté factuelle
  grave**, motif de refus a elle seule, et une **simple erreur d'appariement**, seule forme
  admissible ici.
- L'enonce ne cite jamais un trait partage par plusieurs styles sans le croiser a un second trait
  propre. "Des colonnes" seul ne distingue pas l'Antiquite grecque du classicisme ; "des colonnes
  et un fronton triangulaire soutenant un temple dedie a des dieux" distingue suffisamment pour
  qu'un distracteur "Moyen Age" ou "art gothique" reste une erreur simple, jamais une lecture
  defendable.

## 6. Diversite du corpus - `ART-5` transpose, `HDA-4`

**`HDA-4` - Un paquet ne tire pas tous ses items d'une seule periode, d'une seule civilisation ou
d'un seul type d'edifice.** Meme motif que `ART-5` : un paquet qui ne montrerait, par exemple, que
des eglises medievales enseignerait cela comme etant l'art, pas comme un exemple parmi d'autres.

## 7. Vocabulaire et lisibilite pour un CM1

Le vocabulaire descriptif de l'enonce (colonnes, fronton, arcs, vitraux, murs...) reste dans le
registre deja rencontre par un CM1 en histoire-geographie sur les periodes couvertes par
`squad/programme/histoire-geographie/competences-cm1.md`. Je verifierai ce point item par item, au
moment du Done pedagogique, sur le paquet reellement ecrit - ce n'est pas un critere que ce
document peut fixer a l'avance sans texte a lire.

## 8. Ce que ce document ne fait pas

Il ne choisit aucune oeuvre, aucun style precis au-dela des exemples donnes a titre illustratif,
aucun fait de contexte, aucun distracteur reel. Il ne fixe aucune borne de tirage ni aucun
mecanisme technique. Il ne prononce aucun Done pedagogique : celui-ci reste entierement conditionne
au paquet reellement ecrit et joue, verifie contre `HDA-1` a `HDA-4`, `HIS-2`, `HIS-3` et `ART-5`.

## 9. Ce dont je reste la seule garde

`HDA-1` a `HDA-4` ne sont mordues par aucun test automatique : le caractere "diagnostique" d'un
marqueur de style, l'absence de deuxieme lecture defendable et l'exactitude de chaque fait
resteront verifies par moi seule, a la lecture du paquet, comme `HIS-2` et `HIS-3` le sont deja sur
les autres lignes d'histoire.

---

**Signe** : `nadia-prof`, 2026-09-23.
