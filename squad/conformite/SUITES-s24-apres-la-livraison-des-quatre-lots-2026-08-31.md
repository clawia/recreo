# Suites de s24 apres la livraison des quatre lots - une correction de fait sur le lot 3, l'invariance
# du lot 1 acceptee, `NAR` a la borne, `CPL-9`, et `D5` refermee sur la forme (a)

- **Auteur** : nadia-prof
- **Date** : 2026-08-31, apres les commits `3dac4f5`, `c83d4c3`, `a0f5d33` de `marc-dev`, `1f721df` et
  `2989bc5` de `theo-archi`, `acfecb3` de `sofia-sm`.
- **Ce document ne prononce aucun Done pedagogique**, n'en anticipe aucun, et n'en retire aucun. La
  review n'a pas eu lieu et `sofia-sm` ne m'a pas appelee.
- **Il ne modifie aucun fichier** sous `domain/`, `application/`, `adapters/` ou `bootstrap/`. J'en ai
  lu six pour verifier des faits.
- L'arbitrage sur `S24-FRA` vit dans un document separe, parce qu'il debloque un paquet et qu'un
  developpeur doit le trouver seul :
  `squad/conformite/ARBITRAGE-FRA-la-forme-d-contre-le-seuil-minimal-de-rang.md`.

---

## 1. Correction de fait - le lot 3 existe, et le chemin C n'est pas ecarte

**Ce qui m'a ete transmis** : *"le lot 3 n'existe pas, donc le chemin C est ecarte pour s24"*.
**Recu de MASTER, le 2026-08-31, sans source dans le depot** (`DEC-0062`, forme 3), et par
consequent sans valeur de motif. Je suis allee voir.

**Ce que j'ai lu, avec sa methode** : `git log` sur `origin/s24-marc`, apres `git fetch`.

| Commit | Horodatage | Objet |
|---|---|---|
| `3dac4f5` | 2026-08-31 10:02 | **lot 3** - retypage d'`adapter-web` sur `GameSession` et `R-A` verte |
| `c83d4c3` | 2026-08-31 10:30 | lot 4 - l'ecran de saisie |
| `2989bc5` | 2026-08-31 10:16 | la reponse d'invariance de `theo-archi` |

**Le lot 3 est pousse, et il l'etait quatorze minutes avant que `theo-archi` n'ecrive qu'il
n'existait pas.** Sa section 2 recense huit branches et `s24-marc` n'y figure pas. **Ce n'est pas un
reproche** : son recensement etait exact sur ce qu'il voyait, il en donne la methode, et c'est parce
qu'il l'a donnee que l'ecart se voit. Je verse le fait pour qu'il le rende a nouveau, pas pour le lui
opposer.

**Consequence pour ma porte** : *"le chemin C est ecarte pour s24"* **n'est pas etabli**. Le lot 3
existe, il touche du code qui s'execute pour les 27 jeux, et rien ne le discharge a ce jour.

**Et la lecon est celle que `theo-archi` vient lui-meme d'ecrire, sur un autre sujet** : *"une regle
ecrite sur la description relayee aurait garde la mauvaise propriete, et serait passee verte sur le
paquet qui l'a motivee"*. Un verdict fonde sur un resume relaye aurait ici referme ma porte sur un
lot que je n'avais pas regarde. **C'est la deuxieme fois en un jour que la verification d'un enonce
relaye change la reponse**, et c'est exactement ce que la reponse Q6 de l'utilisatrice m'impose.

---

## 2. Lot 1 - j'accepte le fait de structure, et j'amende mon exigence 2 par ajout

### 2.1 Ce que `theo-archi` rend, et ce qu'il refuse de revendiquer

Il ne produit pas de comparaison de rendu et **le dit** : *"je ne satisfais pas [l'exigence 2], je
propose autre chose a sa place et c'est a elle de dire si cela lui suffit"*. **C'est exactement ce
que mon exigence 4 demandait**, et c'est la premiere fois dans ce sprint qu'un agent decline une de
mes exigences au lieu de la recouvrir. Je le note parce que c'est ce comportement-la qui rend mes
exigences utiles.

Ce qu'il etablit, en deux moities :

- **zero fichier touche sous `adapters/adapter-web` et sous `application`** - donc le controleur, les
  gabarits, la feuille maison et le JavaScript sont identiques a l'octet ;
- **les trois modifications de code existant sont inatteignables depuis les 27** - une entree de
  table de fabriques sous une cle qu'aucun paquet ne declare, une permission de couture scellee sans
  effet a l'execution, une branche de `switch` que seule une fabrique non selectionnee peut
  atteindre. Son enumeration des 27 entrees de catalogue **reproduit ma mesure M5 a l'identique**,
  faite independamment le meme jour.

### 2.2 J'accepte, et voici pourquoi ce n'est pas une complaisance

**Mon exigence 2 - *"la comparaison porte sur ce que l'enfant recoit"* - existait contre un abus
precis** : qu'une CI verte tienne lieu de preuve d'invariance. Ce que `theo-archi` rend n'est pas
une CI verte : c'est un enonce sur le programme, recomptable par un tiers, et il conclut plus fort
qu'une comparaison. **Une comparaison de rendu compare deux executions ; ici il n'y a rien a
comparer, parce que le code qui rend n'a pas change et qu'aucun chemin neuf n'est accessible.**

**Amendement de mon exigence 2, ajoute le 2026-08-31, non retroactif** :

> Une **preuve de structure** vaut la comparaison de rendu exigee en 2, **a la double condition** que
> les deux moities soient etablies ensemble : **(i)** aucun fichier du code qui produit l'ecran n'est
> modifie, et **(ii)** tout code ajoute est inatteignable depuis les jeux concernes, l'inatteignabilite
> etant etablie par une enumeration recomptable et non par une lecture.
>
> **Si l'une des deux moities manque, la voie de structure est fermee et la comparaison de rendu
> redevient exigible.** Une seule des deux ne prouve rien : du code inatteignable ajoute a cote d'une
> ligne modifiee ne dit rien de la ligne modifiee.

**Ce que j'ecris comme un ajout et non comme un assouplissement de l'exigence 2** : le libelle du
2026-08-31 matin reste lisible a sa place, et il continue de gouverner tout ce qui ne remplit pas les
deux moities. Je ne deplace pas ce poteau-la, j'en pose un second a cote.

### 2.3 Verdict sur le lot 1

**Les deux moities sont etablies. Le lot 1 est discharge sous R-C : il ne fait entrer aucun des 27
jeux dans un rejeu.** C'est le chemin A, acquis, pour ce lot et pour lui seul.

---

## 3. Lot 3 - ce que j'ai lu, ce que je n'en conclus pas, et la question que je route

**J'ai lu le diff de `3dac4f5`.** Sept fichiers, trois de production, tous sous `adapter-web` :
`EtatDePartie`, `EtatDePartieEnSession`, `PartieController`.

**La moitie (i) de mon exigence amendee tombe d'emblee** : `PartieController` est modifie, et il
s'execute pour les 27. La voie de structure du lot 1 n'est donc **pas** disponible ici - c'est
exactement ce que `theo-archi` avait predit.

**Ce que je vois quand meme, et que je donne comme une lecture, pas comme un verdict** : la forme du
retypage n'est pas quelconque. Les transtypages disparaissent, `mancheModele` devient deux lignes de
garde - `if (session instanceof BanditManchotSession ...)` - et **le corps entier part inchange dans
`mancheDeBanditManchot`**. Pour les 27, dont `theo-archi` a enumere que les 27 entrees portent la
mecanique `bandit-manchot`, cette garde est vraie a chaque passage. **Cela ressemble a une troisieme
forme de preuve de structure - garde invariablement vraie plus corps identique - qui n'est ni (i) ni
(ii).**

**Je ne l'ecris pas dans mon exigence et je ne discharge rien avec.** Deux motifs :

1. **Ce serait un enonce sur le code, et je ne suis pas architecte.** Etablir qu'une garde est
   invariablement vraie sur les 27 demande de tenir ensemble le catalogue, le registre de mecaniques
   et le port ; je peux le lire, je ne peux pas en repondre.
2. **Ma section 2.2 vient d'ecrire qu'une seule moitie ne prouve rien.** M'en dispenser le paragraphe
   suivant, pour un lot qui touche precisement le chemin de rendu, serait le contraire d'une porte.

**Ce que je route a `theo-archi`, comme une question et non comme une demande** : cette troisieme
forme est-elle etablissable pour le lot 3, avec la meme rigueur que celle du lot 1 ? Si oui, elle
merite d'entrer dans mon exigence comme une moitie **(iii)**, et je l'y ajouterai avec sa date. Si
non, le lot 3 reste sur le chemin C, et **la cloture de s24 laisse 27 jeux entres dans ma porte et
non ressortis** - ce que ma clause de ce matin decrit deja, section 3.3.

**Sur la capture de reference qu'il chiffre** : il a raison de nommer le cout cache, et il a raison
de dire que ce n'est pas une contradiction avec ma clause C5. **Je le confirme ici pour qu'il n'ait
pas a le redemander** : un harnais de non-regression qui m'evite des rejeux ne me transforme pas en
harnais. Ce que C5 refuse, c'est que **je** sois le harnais, pas qu'il en existe un. **Je ne le
demande pas** - c'est une decision de capacite qui appartient a `sofia-sm` - et si elle est prise, je
m'en servirai.

---

## 4. `NAR` a la borne - le fait, une correction d'attribution, et ce que j'en fais

### 4.1 La correction d'attribution, d'abord, parce qu'elle change qui peut bouger quoi

`ADR-0072` route le fait vers moi en ecrivant : *"Le seuil est le sien"*. **Il ne l'est pas, et la
source est dans le garde-fou lui-meme.**

- **Le seuil de majorite de 50 % n'est pas de moi.** La Javadoc de
  `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` dit comment il a ete construit par son
  auteur : *"le milieu exact entre le hasard mesure (33,3 %) et le taux disqualifiant mesure par
  nadia-prof (64,6 %)"*. **Ce qui est de moi, c'est le 64,6 %** - un fait mesure sur le paquet que
  j'avais refuse - **pas le choix du milieu.**
- **Le seuil minimal de 10 %, lui, est de moi**, et le meme fichier l'ecrit : *"il n'est pas negocie
  ici"*.

**Pourquoi je le corrige plutot que de laisser courir** : parce que la reponse Q6 de l'utilisatrice
vient d'etablir qu'un enonce doit porter sa provenance, et parce qu'un seuil qu'on m'attribue est un
seuil que je serais seule a pouvoir deplacer. **Je ne veux pas de ce pouvoir-la sur un chiffre que je
n'ai pas choisi.** `theo-archi` a d'ailleurs ecrit la bonne phrase juste apres : *"Je ne deplace pas
un seuil que je n'ai pas fixe"* - elle vaut pour moi aussi.

**La question de fond, elle, est bien la mienne**, et il la pose exactement : *"c'est a elle de dire
si une marge nulle lui convient sur une ligne de comprehension de lecture"*.

### 4.2 Le fait, mesure par moi, avec sa methode et son perimetre

**Perimetre** : le paquet `cp-texte-narratif` tel que `lea-dev` l'a corrige, lu sur `origin/s24-lea`,
commit `44188a8`. **20 items, tous a texte support, tous a trois propositions** - verifie par
comptage. **Aucun build, aucun `mvn`.**

**Methode** : je reproduis le decoupage et le repliage du garde-fou - coupure sur `.`, `!`, `?` ;
comparaison en minuscules ; occurrence par sous-chaine - puis j'ajoute **une** chose que le garde-fou
s'interdit deliberement : le cas ou la phrase lue porte la bonne reponse **et** un ou plusieurs
distracteurs. Le garde-fou compte alors zero, parce qu'il compte des gains decisifs ; l'enfant, lui,
est ramene a un choix entre les propositions presentes.

| Phrase lue | Gains decisifs (metrique du garde-fou) | Esperance de gain |
|---|---|---|
| phrase 1 | **10 / 20 = 50,0 %** | **56,7 %** |
| phrase 2 | 10 / 20 = 50,0 % | 50,0 % |

**Je reproduis le 50,0 % de `theo-archi` a la decimale**, par une lecture independante, ce qui vaut
verification de sa mesure. **Et l'esperance de gain de la meme strategie est de 56,7 %**, contre
33,3 % par hasard : au-dessus de la moitie, alors que la metrique du garde-fou est exactement a la
moitie.

**Le garde-fou n'a pas tort de compter ainsi** - sa Javadoc dit pourquoi, et elle a raison :
modeliser le tiers de chance restant ferait entrer une hypothese de comportement dans un automate.
**Mais l'hypothese de comportement, c'est mon metier**, et c'est precisement le genre de fait que je
suis seule a produire.

### 4.3 Ce que j'en fais, et ce que je n'en fais pas

- **Cela ne bloque pas `NAR` et je ne m'en servirai pas pour refuser a la porte.** Aucune de mes
  conditions `NAR-1` a `NAR-10` ne porte cette propriete ; elle est apparue aujourd'hui. **Refuser
  sur une barre posee apres coup serait deplacer mes poteaux**, et je ne le fais pas.
- **Un fait utile que personne n'a encore nomme, et qui leve un obstacle** : la marge nulle est
  epinglee dans un **contre-exemple gele**
  (`cp-texte-narratif-apres-correction`), **pas dans le paquet servi**. Le paquet servi n'est juge
  que par la clause de production. **Ameliorer le paquet servi ne casse donc aucun test et ne coute
  rien.** Je le dis parce que la lecture inverse - *"on ne peut pas y toucher, un test epingle
  50,0 %"* - est facile a faire et decouragerait la seule amelioration disponible.
- **Une condition neuve, datee du 2026-08-31, non retroactive** :

  > **`NAR-11`** - Sur un paquet dont les items portent un texte support, **je lis a l'audit les
  > items qu'une strategie a phrase fixe gagne**, et je juge si le raccourci en est un qu'un enfant
  > du niveau vise prendrait reellement. Je ne pose **aucun seuil chiffre** : je n'ai pas de mesure
  > qui en fonde un, et j'ai assez paye deux listes fermees cette semaine pour ne pas inventer un
  > troisieme nombre.

  **Elle ne s'applique pas a `NAR` de s24.** Elle vaut pour les paquets a texte support suivants, et
  elle est ecrite maintenant pour ne pas etre decouverte plus tard.
- **Ce que je verse a `theo-archi` et `yanis-qa`, comme une question et non comme une demande** :
  une variante du garde-fou qui compterait l'esperance plutot que les gains decisifs est-elle
  ecrivable sans y loger une hypothese de comportement ? **Je dis que le fait existe et qu'il differe
  de 6,7 points ; je ne dis pas qu'il faut l'encoder.** C'est du backlog, pas ce lot.

### 4.4 Ce que `lea-dev` a fait, et qui ne doit pas se perdre dans ce paragraphe

**Elle a joue son propre paquet avant de le pousser et elle y a trouve un defaut qu'aucun automate ne
voyait.** C'est exactement la conduite que ma clause C8 decrit comme la seule garde disponible sur ce
genre de propriete, et elle l'a tenue sans qu'on la lui demande. Le fait que sa **description** du
defaut ait ete inexacte - `theo-archi` le corrige, et il a raison de le faire - **ne retire rien au
constat**, et il le dit lui-meme.

---

## 5. `CPL-9` - le titre et la consigne de l'entree de catalogue

`sofia-sm` a raison et le point est reel : **le titre et la consigne d'une entree de catalogue sont
du texte lu par l'enfant, et aucune de mes conditions `CPL-1` a `CPL-8` ne les couvre.** Elles
portent toutes sur les items du paquet. **Je comble le trou ici plutot qu'a la porte.**

Verifie par moi : le paquet `cp-complements-10-saisie.yaml` existe sur `origin/s24-sofia-dev`
(`acfecb3`), **et aucune entree de catalogue ne le sert encore** - le diff de `catalogue.yaml` est
vide sur ce commit. Le texte en question n'est donc pas encore ecrit, et cette condition arrive
avant lui.

> **`CPL-9` (neuve, 2026-08-31)** - Le titre et la consigne de l'entree de catalogue du paquet de
> saisie.
>
> - **`CPL-9a` - Ils se distinguent de l'entree existante, et c'est la clause qui compte.** Le
>   catalogue portera **deux entrees sur la meme competence, au meme niveau** :
>   `bandit-manchot-complements-10-cp`, titre *"Les compléments à 10"*, consigne *"Combien manque-t-il
>   pour faire 10 ?"*, et l'entree de saisie. Un enfant de CP qui voit les deux dans la meme liste
>   doit pouvoir dire en quoi elles different **sans les ouvrir**. Un titre qui ne se distingue que
>   par un mot technique - *"saisie"*, *"clavier"* - ne convient pas : ce n'est pas le vocabulaire
>   d'un enfant de six ans.
> - **`CPL-9b` - La consigne dit que l'enfant ecrit.** La consigne existante a ete ecrite pour des
>   boutons ; reprise telle quelle, elle demanderait de choisir la ou il faut ecrire. La difference
>   d'action doit etre dans la consigne, pas decouverte a l'ecran.
> - **`CPL-9c` - Longueur.** Titre : **au plus cinq mots**. Consigne : **au plus huit mots**. *Unite
>   de compte : le mot est une suite de caracteres sans espace, la ponctuation isolee non comptee -
>   la meme que `NAR-2`.* Mesure de reference sur l'existant : les consignes du catalogue vont de
>   cinq a six mots, je ne durcis rien.
> - **`CPL-9d` - Vocabulaire de CP.** Chaque mot du titre et de la consigne est lisible par un enfant
>   de CP en fin d'annee. Aucun mot d'interface, aucun mot de mecanique.
> - **`CPL-9e` - La consigne ne nomme pas la reponse** et ne donne aucune methode de calcul. Meme
>   regle que `CPL-5` sur les indices.

**Ce que `CPL-9` ne fait pas** : elle n'ecrit pas le texte. Je pose le resultat, `camille-po` et le
developpeur ecrivent, et je juge a la porte.

---

## 6. `D5` - la forme (b) est retiree, la forme (a) tient, et deux points restent ouverts

### 6.1 La forme (b) est retiree de mon arbitrage, le 2026-08-31

Mon arbitrage de ce matin nommait deux formes et n'en designait aucune. **`marc-dev` etablit que la
forme (b) - une marque de refus sur le champ, qui s'efface a la premiere frappe - n'est pas
implementable dans ce depot** : elle demande un comportement declenche par une frappe, donc du
JavaScript maison qui decide de ce que l'ecran montre, ce qui est la dette 4 de la version
precedente et ce que `FrontieresDuJavaScriptTest` garde. La variante serveur qu'il decrit n'efface la
marque qu'apres l'appui, c'est-a-dire trop tard pour ma condition.

**J'accepte ce fait et je retire la forme (b) de mon arbitrage**, en ajout date et sans effacer le
texte de ce matin, qui reste lisible a sa place. **Il ne reste qu'une forme connue qui serve ma
condition, et c'est celle qui a ete implementee.**

**Ce que cela m'apprend, et je l'ecris contre moi** : j'ai nomme deux formes en croyant elargir le
choix d'`ines-design`, et l'une des deux etait impossible. **Nommer une forme, meme sans la
prescrire, c'est deja entrer dans un terrain qui n'est pas le mien.** La bonne conduite aurait ete de
poser la condition seule. Je m'y tiendrai.

### 6.2 La forme (a) sert ma condition, et je le confirme

`marc-dev` a verifie sur le jar servi : une tentative au deuxieme essai, deux au troisieme, trois a
la revelation ; le champ porte la derniere ; a la revelation le champ disparait et les deux chaines
sont montrees ensemble. **L'ecart que j'avais releve sur `SAI-12` n'est donc pas parti dans le lot**,
et la liste porte bien toutes les tentatives de la manche.

**Ce que cette verification prouve, et ce qu'elle ne prouve pas** - il le dit lui-meme et il a
raison : *"ce que ces documents prouvent est ce que le serveur rend, jamais ce que l'enfant voit"*.
**Le jugement sur ce que l'enfant voit reste entier et il est a moi**, a la porte, en jouant.

### 6.3 La place de la liste - je ne la tranche pas, et je dis pourquoi

`marc-dev` a place la liste **au-dessus du champ**, contre la proposition d'`ines-design` qui la
placait sous le verdict. **Son motif cite ma condition correctement** : la marque doit etre dans le
regard de l'enfant au moment ou il peut appuyer.

**Je ne tranche pas la place, parce que ce n'est pas ma porte.** Ce que je fixe est un resultat, et
il est servi par l'une comme par l'autre disposition **a une condition que je precise ici** : que la
chaine barree soit visible **en meme temps que le champ, sans defilement**, sur les largeurs que le
produit vise. **Cette condition-la est mesurable et elle ne l'est pas par moi** : c'est le releve aux
dix largeurs d'`ines-design`, et `marc-dev` ecrit lui-meme que son deplacement change le budget
vertical et **n'est pas mesure**.

**Ce que je demande avant de prononcer sur l'ecran de saisie** : que le releve existe sur la
disposition reelle. **Je ne mesure aucun pixel** - je n'en ai ni l'instrument ni le metier - et je ne
prononcerai pas sur ce point sans ce releve. Si le releve montre que la liste sort de l'ecran, la
disposition change, et ce sera un fait, pas un gout.

---

## 7. Sur quelles proprietes je reste la seule garde - complement du 2026-08-31

Ajout a la liste du 2026-08-30, apres ce que ce sprint a fait apparaitre.

- **La plausibilite de `n + d` sur la forme (d) de `FRA-2`**, item par item. Aucun test ne la dira.
- **`NAR-11`** - qu'un raccourci mesure soit un raccourci qu'un enfant prendrait reellement.
- **`CPL-9a`** - qu'un enfant de CP distingue deux entrees de catalogue sur la meme competence sans
  les ouvrir. Rien ne le verifiera jamais qu'un enfant, ou moi a sa place.
- **La propriete generale que `ADR-0072` etablit comme non formulable** : *une bonne reponse ne doit
  pas etre reconnaissable a une marque de surface que ses distracteurs ne portent pas*. `theo-archi`
  a mesure trois familles avant de conclure, plutot que de conclure par raisonnement. **Elle
  n'appartient donc a aucun automate, aujourd'hui et probablement pour longtemps.** Je suis la seule
  garde de la famille entiere ; le garde-fou neuf n'en tient qu'un membre, celui du decoupage en
  phrases.
- **Ce que l'enfant voit** - par opposition a ce que le serveur rend. Nomme par `marc-dev` sur le lot
  4, et vrai de tout ce sprint.

---

## 8. Ce que je route

| Suite | Destinataire | Ce que je demande |
|---|---|---|
| **Le lot 3 existe** (`3dac4f5`, pousse a 10:02) et la section 2 de la reponse d'invariance est perimee | `theo-archi`, par `sofia-sm` | Que la phrase soit rendue a nouveau sur le lot 3 tel qu'il est ecrit. Ce n'est pas un reproche : son recensement portait sa methode, et c'est elle qui rend l'ecart visible |
| **Exigence 2 amendee par ajout** : la preuve de structure vaut la comparaison sous deux conditions conjointes (2.2) | `theo-archi`, `sofia-sm` | Rien a produire. C'est ma porte qui se precise |
| **Lot 1 discharge sous R-C** : aucun des 27 jeux n'entre en rejeu de son fait | `sofia-sm`, `camille-po` | Que le cout de cloture soit relu : le lot 1 sort de l'equation, le lot 3 y entre |
| **Question sur une troisieme forme de preuve de structure** pour le lot 3 - garde invariablement vraie plus corps identique (3) | `theo-archi` | Une question, pas une demande. Si elle est etablissable, je l'ajoute a mon exigence avec sa date |
| **La capture de reference ne contredit pas ma clause C5** | `theo-archi`, `sofia-sm` | Confirmation donnee pour qu'il n'ait pas a la redemander. Je ne la demande pas : c'est une decision de capacite |
| **Le seuil de majorite de 50 % n'est pas de moi** (4.1), le seuil minimal de 10 % l'est | `theo-archi`, `camille-po` | Que l'attribution soit corrigee la ou elle est ecrite. Un seuil qu'on m'attribue est un seuil que je serais seule a pouvoir bouger, et je ne veux pas de ce pouvoir sur un chiffre que je n'ai pas choisi |
| **`NAR` n'est pas bloque par la marge nulle**, et ameliorer le paquet servi ne casse aucun test (4.3) | `lea-dev`, `sofia-sm` | Que la marge nulle ne soit pas lue comme un interdit d'y toucher |
| **`NAR-11`, neuve et non retroactive** | `camille-po`, `sofia-sm` | Qu'elle soit portee au dossier des conditions, pour les paquets a texte support suivants |
| **Esperance de gain contre gains decisifs : 56,7 % contre 50,0 %** (4.2) | `theo-archi`, `yanis-qa` | Une question de faisabilite, pas une demande d'encodage. Du backlog |
| **`CPL-9a` a `CPL-9e`** | `camille-po`, `sofia-sm` | De quoi ecrire le titre et la consigne sans me redemander ce que je voulais dire. Le point est clos |
| **La forme (b) de `D5` est retiree** ; la forme (a) tient | `ines-design`, `marc-dev` | Que personne ne repropose la forme (b) : elle est impossible, pour un motif technique etabli |
| **Le releve aux dix largeurs sur la disposition reelle est du avant que je prononce** (6.3) | `ines-design`, par `sofia-sm` | Je ne mesure aucun pixel. Sans ce releve, je ne prononce pas sur l'ecran de saisie |

---

## 9. Ce que ce document ne fait pas

- Il **ne prononce aucun Done pedagogique**, n'en anticipe aucun, n'en retire aucun.
- Il **ne refuse rien**. `NAR` n'est pas bloque, le lot 4 n'est pas bloque, et le seul paquet bloque
  ce matin est debloque par l'autre document.
- Il **ne deplace aucun seuil** : ni le 10 % qui est de moi, ni le 50 % qui ne l'est pas.
- Il **n'efface aucun texte** : le libelle de mon exigence 2 et les deux formes de `D5` restent
  lisibles a leur place ; les corrections sont des ajouts dates.
- Il **ne decide rien de technique** : l'invariance du lot 3, la capture de reference et la forme
  d'un garde-fou sont a `theo-archi`.
- Il **ne rouvre aucun perimetre**, n'ecrit aucune story et ne repriorise rien.
