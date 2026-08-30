# Fiche de conformite - bandit-manchot-problemes-durees-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du catalogue).
- **Contenu(s)** : contenu neuf a creer (`type: question`, au moins 20 items), story `S13-3`
  (`docs/backlog/backlog.md`). Matiere premiere partielle identifiee : `contenu-repris/quiz/
  cm1-heure.yml` porte deux items reellement conformes ("de 14h a 16h, combien de temps s'est
  ecoule ?", le film de 8h30 a 10h) sur ses dix questions ; les autres relevent de la lecture de
  l'heure (competence CP) ou de la conversion d'unite de temps seule (hors de cette ligne), et aucun
  ne porte sur un "horaire d'arrivee". Reprise = reprise des deux items conformes, pas de la
  structure du fichier (`contenu-repris/README.md`) : ils repassent devant `nadia-prof` comme un
  contenu neuf.
- **Statut global** : **ouverte, aucun increment livre a ce jour.** Rien n'a encore ete joue.
- **Arbitrage prealable de `nadia-prof` : rendu le 2026-08-15** (voir section dediee plus bas, `D-1`
  a `D-11`, plus un obstacle technique signale d'avance section 3.6) - la ligne ci-dessus disait
  encore "demande et non encore rendu" jusqu'au 2026-08-28, perimee depuis huit sprints ; corrigee
  ici plutot que laissee en l'etat. Les criteres ci-dessous, notamment les bornes, restent ceux de
  `camille-po` a l'ouverture, **completes et confirmes** par l'arbitrage rendu.
- **Arbitrage prealable de mecanique en vigueur pour tout le catalogue** (`squad/conformite/
  FICHE-bandit-manchot-additions-cp.md`, branche `s10-nadia-mecanique` et audits suivants) :
  conditions M1 a M13. Rien n'est mesure ici a l'ouverture de la fiche, faute d'increment a mesurer.
  **Mise a jour du 2026-08-29 (`camille-po`)** : `M4` est sortie de la porte pedagogique le
  2026-08-28 (`DEC-0053`) et sa reference `360 x 640` etait deja morte depuis `DEC-0040`
  (2026-08-22) ; voir `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, en-tete, et
  `squad/conformite/ARBITRAGE-M4-ET-BALAYAGE-DES-CONDITIONS-DE-MECANIQUE-2026-08-29.md`
  (`nadia-prof`) avant de citer ce bloc.
- **Priorite s22** (`DEC-0053`, section 4) : seconde candidate du perimetre du sprint, verrou
  d'arbitrage leve. Obstacle technique nomme d'avance (section 3.6 de l'arbitrage) : les garde-fous
  de rang liraient l'entier de tete d'une proposition et classeraient `1 h 30` sous `45 min` sur ce
  contenu. **Ferme le 2026-08-28** : `nadia-prof` a relu
  `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`
  (`adapters/adapter-content/src/test/java/com/recreo/adapter/content/`) et signale que
  `dureeEnMinutes`/`lireValeur` lisent deja une duree en minutes avant toute lecture de nombre nu, et
  que `rangDe` rend `null` sur un conflit de valeurs (`DEC-0053`, amendement du 2026-08-28) -
  verifie independamment par `camille-po` dans le fichier cite. Plus rien a router a `theo-archi`
  ou `yanis-qa` sur ce point.
- **Derniere mise a jour** : 2026-08-28 (`camille-po`, correction de statut et priorisation s22,
  `DEC-0053`)

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| mathematiques | CM1 (cycle 3) | Grandeurs et mesures | Resoudre des problemes de durees (calcul d'une duree, d'un horaire d'arrivee) | `squad/programme/mathematiques/competences-cm1.md` |

**Perimetre propose, sous reserve de l'arbitrage prealable de `nadia-prof`** : deux formes seulement,
"calcul d'une duree" (deux heures donnees, duree ecoulee a trouver) et "horaire d'arrivee" (heure de
depart et duree donnees, heure d'arrivee a trouver) - jamais une simple lecture de l'heure ni une
conversion d'unite de temps seule.

## Criteres pedagogiques attendus - camille-po

- [ ] Chaque item releve de l'une des deux formes exactes du referentiel (calcul d'une duree, ou
  horaire d'arrivee), jamais d'une lecture de l'heure ou d'une conversion d'unite de temps seule.
- [ ] Les deux formes sont chacune representees par au moins un tiers du paquet.
- [ ] Toutes les heures d'un meme item restent dans une seule journee (aucun item ne franchit
  minuit), exprimees en heures et minutes rondes de 5 en 5 minutes, sans seconde.
- [ ] Chaque item porte exactement trois propositions, dont deux distracteurs plausibles (erreur de
  report d'heure ou de minute, inversion du sens de l'operation, oubli de la retenue de 60 minutes),
  jamais deux propositions egales dans le meme item.
- [ ] Le paquet compte au moins 20 items.
- [ ] Chaque item porte un indice non vide qui donne la methode, pas le resultat.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. Il reste vide tant qu'aucun
> increment n'a ete livre et joue. L'arbitrage prealable demande par `DEC-0030` (formes admises,
> bornes horaires) sera rendu ici ou dans un fichier d'arbitrage dedie, avant tout code.

### Arbitrage prealable rendu le 2026-08-15 (s13) - ce n'est pas un Done pedagogique

- **Ou** : `squad/conformite/ARBITRAGE-PREALABLE-s13-multiplications-divisions-et-durees-cm1.md`,
  section 3. **Onze conditions, D-1 a D-11**, plus la liste de ce que je refuse d'avance
  (section 3.5).
- **Ce qu'il tranche** : la ligne ouvre **en entier** sur les deux formes que le referentiel nomme.
  La troisieme forme - retrouver l'heure de depart - est **refusee d'avance dans ce paquet** et son
  absence ne pourra pas etre comptee comme un trou de couverture. Les trois bornes de `camille-po`
  (une journee, minutes de 5 en 5, aucune seconde) sont **confirmees** et **completees** : au plus la
  moitie du paquet en quarts d'heure, entre un tiers et deux tiers des items franchissant une heure
  ronde, duree maximale de cinq heures, enonce de vingt-cinq mots au plus. **La condition la plus
  importante est D-2** : un instant et une duree ne s'ecrivent pas de la meme facon, et le contenu
  repris les ecrit a l'identique.
- **Un obstacle technique signale avant le code** (section 3.6) : les deux garde-fous de rang lisent
  **l'entier de tete** d'une proposition, donc ils classent `1 h 30` sous `45 min` et rangent trois
  propositions de meme heure comme si la bonne reponse etait la plus petite. Sur ce paquet, leur
  verdict sera faux dans un sens ou dans l'autre. **Personne ne modifie le contenu pour les faire
  verdir** ; le symptome est route a `theo-archi` et `yanis-qa`, et le rang reste a ma garde.
- **Ce qu'il ne fait pas** : il ne vaut aucun Done pedagogique par avance.
- **Statut** : le verrou d'arbitrage prealable de `DEC-0030` sur cette story est **leve**. Le code
  peut commencer des que `theo-archi` a rendu son avis de faisabilite - l'obstacle de la section 3.6
  ne bloque pas l'ecriture du contenu, il conditionne la lecture de la CI a la review.

---

### Done pedagogique - 2026-08-29 (s22) - REFUSE

**Verdict : REFUSE.** Motif : **`D-6` n'est pas tenue.** Les quarante-huit distracteurs du paquet ne
sont pas des erreurs qu'un CM1 fait ; ce sont **la bonne reponse deplacee d'un ecart constant**, et
l'en-tete du fichier declare deux familles d'erreurs qui ne les produisent pas. C'est nommement ce
que ma section 3.5 refuse d'avance : *"le distracteur qui n'est pas une erreur nommee dans le
fichier"*.

**Ce que je dis d'abord, parce qu'il serait injuste de lire ce refus comme un jugement sur le lot.**
Dix des onze conditions sont tenues, plusieurs avec une marge nette. **Les vingt-quatre calculs sont
justes**, l'ecriture d'un instant ne se confond jamais avec celle d'une duree, le francais est
integralement accentue, les situations sont accessibles a tous les enfants, et l'en-tete du fichier
documente le paquet condition par condition mieux qu'aucun autre du catalogue. Le defaut est reel et
il est structurel, mais il ne touche qu'un seul des quatre champs de chaque item.

#### Comment j'ai audite, et ce que je declare

- **J'ai joue.** Application lancee depuis le fat jar construit sur l'arbre de la branche. Une
  partie complete de dix manches, en me trompant volontairement sur chaque manche jusqu'a epuiser
  les propositions, pour voir ce que l'enfant recoit apres un echec, apres deux, et a la fin.
  **Audit non degrade.**
- **J'ai recalcule moi-meme les vingt-quatre items** : la duree ou l'horaire attendu, le rang de la
  bonne reponse converti en minutes, le franchissement d'heure ronde, les minutes en quart d'heure,
  et l'ecart de chaque distracteur a sa bonne reponse.
- **Perimetre de tout chiffre ci-dessous** : le seul paquet `cm1-problemes-durees`, vingt-quatre
  items, quarante-huit distracteurs, arbre de `s22-nadia-3` issu de `round-two` a `62037c7`.

#### Le motif du refus - `D-6`

**Ce que l'en-tete du fichier declare** : deux familles, `ERR-INVERSE` (l'operation est inversee) et
`ERR-REPORT` (le report d'une heure est oublie ou ajoute en trop, ou l'heure de depart est
recopiee).

**Ce que le contenu fait.** Converties en minutes, les valeurs des distracteurs ne dependent pas des
nombres de leur propre item : ce sont des **ecarts constants a la bonne reponse**, appliques par
blocs de quatre items.

| Items | Ecart des deux distracteurs a la bonne reponse | Rang de la bonne reponse |
|---|---|---|
| 1 a 4 et 13 a 16 | **+15 min et +45 min** | la plus petite |
| 5 a 8 et 17 a 20 | **-15 min et +30 min** | intermediaire |
| 9 et 10 | -20 min et -5 min | la plus grande |
| 11, 12 et 21 a 24 | **-45 min et -15 min** | la plus grande |

**Un distracteur derive d'une erreur d'enfant varie avec les nombres de l'item ; ceux-ci ne varient
pas.** Sur les quarante-huit, trois seulement coincident avec une valeur que les deux familles
declarees produisent - et les trois coincidences sont *l'heure de depart recopiee*, sur deux items.

**La preuve la plus parlante, et celle qui ne depend d'aucun modele que j'aurais construit** :
l'erreur de base 100 - *"`10 h 30` + `45 min` = `10 h 75`"*, celle que `D-5` et `D-6` nomment toutes
deux comme **la** difficulte de cette competence - **n'est proposee sur aucun des vingt-quatre
items.** Verifie item par item : un enfant qui calcule *9 h 10 moins 8 h 45* en base 100 trouve
65 minutes, et le jeu lui offre 10, 25 et 55.

**Pourquoi c'est un motif de refus et pas une remarque.** Un distracteur sert a deux choses : donner
a l'enfant qui se trompe la possibilite de reconnaitre son erreur, et donner a l'enseignant
l'information de laquelle il a faite. **Un ecart constant ne fait ni l'un ni l'autre.** L'enfant qui
compte en base 100 - l'erreur que ce paquet existe pour corriger - ne voit jamais son propre
resultat a l'ecran ; il constate seulement qu'aucune proposition ne lui ressemble, refait au hasard,
et l'indice qui suit lui dit *"Une heure, c'est 60 minutes, pas 100"* sans qu'il ait jamais vu
pourquoi. **Je l'ai vu en jouant** : sur l'item d'Iris (`8 h 45` a `9 h 10`), j'ai presse
*55 minutes* puis *10 minutes*, et le jeu m'a repondu deux fois la meme chose, parce qu'il n'y avait
rien a me dire - je n'avais fait aucune erreur identifiable, j'avais presse un nombre.

**Et l'en-tete declare une chose qui n'est pas dans le fichier.** C'est la partie que je tiens pour
la plus serieuse au regard de la gouvernance de ce depot : un tiers qui lit `# D-6 : les deux
familles de distracteurs, nommees ici` croit la condition verifiee et ne la verifie pas. Une
declaration fausse dans un fichier commite coute plus qu'un champ vide.

**Ce que ce refus coute a lever.** Les quarante-huit distracteurs sont a recalculer depuis les
nombres de leur item. C'est le plus cher des trois refus de ce sprint, et je le dis pour que
`camille-po` le priorise en le sachant. **Rien d'autre n'est a toucher** : ni un enonce, ni une
bonne reponse, ni un indice, ni une situation.

#### Les onze conditions, une par une, contre le contenu reel

| Condition | Etat | Ce que j'ai verifie moi-meme |
|---|---|---|
| `D-1` deux formes | **tenue** | **12 calculs de duree, 12 horaires d'arrivee** - plancher d'un tiers (8) tenu deux fois. Aucune lecture de l'heure, aucune conversion seule, aucune recherche de l'heure de depart |
| `D-2` instant contre duree | **tenue, et c'est ma condition la plus importante ici** | Instant en abrege (`10 h 15`), duree en toutes lettres (`25 minutes`, `1 heure 15 minutes`), **sans une seule exception sur 24 items**, et les trois propositions d'un item portent toujours la meme ecriture |
| `D-3` horaires | **tenue** | Journee de 24 h, aucun *"du matin"* ni *"de l'apres-midi"*, aucun franchissement de minuit, aucune seconde. Amplitude reelle : 8 h 30 a 18 h 30 |
| `D-4` pas que des quarts d'heure | **tenue, avec marge** | **11 items sur 24** font intervenir une valeur en `00`/`15`/`30`/`45` (plafond : la moitie, 12) ; **1 seul** item a toutes ses valeurs sur un quart d'heure exact |
| `D-5` retenue de 60 | **tenue** | **12 items sur 24** exigent le report de soixante, soit **50 %** - dans les bornes d'un tiers a deux tiers. Duree la plus longue : **2 heures 10 minutes**, sous le plafond de cinq heures |
| `D-6` distracteurs | **NON TENUE** | Motif du refus |
| `D-7` aboutissement | **tenue** | **8 / 8 / 8** sur 24, soit **33,3 %** par rang - dans les bornes 10 %-50 %. Recompte par moi sur la valeur convertie en minutes, comme la condition l'exige, et non sur l'ordre d'ecriture. Le compte de l'auteure est exact |
| `D-8` enonce | **tenue** | Deux phrases au plus, vingt-cinq mots au plus, sur les 24. Toutes les donnees sont dans l'enonce |
| `D-9` situations (version reformulee du 2026-08-28) | **tenue** | Ecole, cantine, recreation, bus et car scolaires, train, bibliotheque, musee, ferme pedagogique, match, film, recette en classe. **Aucune ne suppose un bien, un service ou une pratique que tous les enfants n'ont pas chez eux.** Aucun avion, aucunes vacances, aucune voiture familiale. Prenoms varies d'origine ; Salma joue au basket, aucune mere n'est a la cuisine, aucun retard n'est dramatise |
| `D-10` indice | **tenue** | **7 indices distincts** - quatre pour le calcul de duree, trois pour l'horaire d'arrivee. Methode, jamais resultat. *"Une heure, c'est 60 minutes, pas 100 : compte bien."* est exactement ce que je demandais |
| `D-11` vingt items au moins | **tenue** | 24 items. Les deux items de `contenu-repris/quiz/cm1-heure.yml` ne sont pas repris, et **le choix est documente dans l'en-tete avec son motif** - c'etait ma demande, elle est satisfaite autrement que je ne l'avais prevu, et cela me convient |

**Exactitude : les vingt-quatre calculs sont justes.** Recalcules un par un, aucun ecart. C'est la
propriete que je regarde en premier, et sur un paquet de durees elle n'est pas acquise d'avance.

#### Ce que le contenu reel a mis en defaut dans mes propres conditions

**Deux de mes onze conditions sont ambigues, et le paquet les tient sous les deux lectures. Je
l'ecris parce qu'il aurait pu ne pas les tenir.**

- **`D-5` - "franchir une heure ronde" a deux lectures.** Le report de soixante (les minutes
  s'additionnent au-dela de 60) en donne **12 sur 24** ; le passage d'une heure a la suivante entre
  le debut et la fin en donne **15 sur 24** - c'est le chiffre de l'auteure, et il est exact sous sa
  lecture. **Ma phrase de motif tranche pourtant** : j'ai ecrit *"l'enfant qui compte en base 100
  tombe juste tant qu'aucune heure n'est franchie, et faux des qu'elle l'est"*, ce qui ne peut
  designer que le report. Les deux comptes tombent dans les bornes, donc **la condition est tenue et
  je ne rouvre rien** ; mais le libelle et le motif ne disent pas la meme chose, et une prochaine
  fois ils pourraient diverger de part et d'autre de la barre.
- **`D-4` - "utilise `00`, `15`, `30` ou `45`" ne dit pas de quoi on parle.** Un item qui porte une
  seule valeur en quart d'heure, ou un item dont toutes les valeurs le sont ? **11 sur 24** sous la
  premiere lecture, **1 sur 24** sous la seconde ; sous les deux, la condition est tenue.

**Aucune des onze n'a en revanche fait ce que craignait `SEG-1`** - aucune n'a exclu a tort du
contenu legitime. Le defaut de mes conditions ici est l'**imprecision**, pas la sur-extension.

**`D-6`, elle, a fait exactement son travail.** Elle a attrape un defaut qu'aucun garde-fou ne peut
voir, sur quarante-huit distracteurs, dans un paquet dont toutes les autres proprietes sont bonnes,
et l'auteure declarait n'avoir trouve aucune de mes onze conditions en defaut. C'est la premiere
fois qu'elle rencontre un contenu reel et elle tient.

**Ce que la CI verte ne dit pas.** `yanis-qa` etablit que `D-2` et `D-7` sont desormais gardees
automatiquement, contre ma prevision. **C'est vrai, et j'ai verifie leurs deux verdicts a la main
plutot que de les croire** : les deux sont justes. Mais `D-6` n'est gardee par rien, et c'est elle
qui fait ce refus. Un garde-fou vert dit qu'une condition est tenue telle qu'elle est ecrite ; il ne
dit rien de celles que personne ne garde.

**Je confirme au passage ma correction du 2026-08-28** : ma section 3.6 d'arbitrage etait bien
caduque, ma prevision de CI rouge sur ce paquet ne s'est pas produite, et le garde-fou de rang a
rendu le meme verdict que mon recompte a la main. **J'avais eu tort, la correction etait la bonne, et
personne n'a perdu de temps sur un faux rouge annonce par moi.**

#### Reserves

- **Reserve 1, BLOQUANTE, 2026-08-29 - `D-6`, les quarante-huit distracteurs.** Motif du refus.
  **Critere de levee, verifiable par un tiers sans me le redemander** : pour chaque distracteur, le
  fichier nomme l'erreur, et la valeur du distracteur se recalcule depuis les nombres de son propre
  item par cette erreur. **Au moins un item par famille propose la valeur qu'ecrit l'enfant qui
  compte en base 100.**
- **Reserve 2, non bloquante, 2026-08-29 - une phrase de l'en-tete que le contenu ne soutient
  pas.** L'en-tete affirme, au titre de `D-9`, que *"la recette est preparee en classe ou par un
  garcon"*. Les deux items de recette sont portes par Iris et par Nour, prenoms lus l'un et l'autre
  comme feminins. **La condition `D-9` reste tenue** - aucune mere a la cuisine, roles varies par
  ailleurs, Salma au basket - mais la phrase surestime ce que le contenu fait. A corriger avec la
  reserve 1, ou a rendre vraie en changeant un prenom.
- **`REP-8-CONSTAT`, 2026-08-29** - vu en jouant : sur les enonces de deux phrases pleines, l'indice
  qui suit un echec arrive bas dans la page. **Constat, non bloquant, sans aucune mesure** : je ne
  mesure plus, l'arbitrage du 2026-08-28 l'a retire. Aucun effet sur le verdict.

#### Une observation qui n'appartient pas a ce paquet, et que je verse ailleurs

En jouant dix manches en me trompant deux fois sur presque chacune, **j'ai fini a 10 sur 10 avec
"Bravo, c'est fini !"**. Trois propositions et des essais illimites font qu'un enfant qui se trompe
systematiquement obtient le meme score qu'un enfant qui reussit du premier coup. **Ce n'est pas un
defaut de ce paquet** - c'est une propriete de la mecanique, presente sur les vingt-et-un jeux du
catalogue et sur des paquets que j'ai deja valides. **Je ne l'oppose pas a `sofia-sm` et elle
n'entre dans aucun motif de ce verdict.** Je la route a `camille-po` pour ce qu'elle est : une
question de produit, pas un defaut de lot.

#### Ce que je reste seule a garder sur ce paquet

1. **L'exactitude des vingt-quatre durees et horaires.** Aucun automate ne la calcule.
2. **Que chaque distracteur soit une erreur reelle** - c'est ce refus meme.
3. **Que `D-2` ne se relache jamais** : le garde-fou lit les deux ecritures, il ne juge pas qu'elles
   restent distinctes.
4. **Les distributions sur le paquet entier** : la retenue de 60 de `D-5`, la moitie de `D-4`.
5. **Les biais de genre, d'origine, de milieu et de configuration familiale** - la reserve 2 montre
   que meme une declaration ecrite de bonne foi peut ne pas correspondre au contenu.
6. **Que le niveau soit le bon** : cela ne se lit pas, cela se joue.

#### `MNC-1`

**Aucun Done pedagogique n'etant prononce ici, `MNC-1` n'a pas a y figurer** - elle porte sur tout
Done `VALIDE` ou `VALIDE SOUS RESERVE` a partir du 2026-08-28, et un `REFUSE` n'est pas un Done. Son
absence de ce bloc n'est pas un oubli. Le bloc qui prononcera le Done de ce paquet devra la porter.

#### Ce que je route

| Suite | Destinataire | Ce que je demande |
|---|---|---|
| **La reserve 1, bloquante** - quarante-huit distracteurs a recalculer. **Le plus cher des trois refus de s22** | `camille-po` | En faire du backlog, en sachant le cout. Critere de levee donne, applicable sans moi |
| **La reserve 2** - une phrase de l'en-tete que le contenu ne soutient pas | `camille-po` | A joindre au meme lot |
| **`D-4` et `D-5` sont ambigues** ; le paquet les tient sous les deux lectures | `camille-po` | **Ne rien appliquer retroactivement** : les deux conditions sont tenues et je les declare tenues. A reecrire par moi avant le prochain paquet de durees, comme des conditions neuves et datees |
| **Score plein malgre les erreurs** : une propriete de la mecanique, sur tout le catalogue | `camille-po` | Une question de produit, jamais un motif contre ce lot |
| **`D-6` n'est gardee par aucun automate**, et c'est elle qui a fait tomber le lot | `yanis-qa`, `sofia-sm` | A porter au rapport qualite, a cote du compte des dix-neuf conditions gardees |

---

### Done pedagogique - 2026-08-30 (s22) - VALIDE SOUS RESERVE

**Reaudit apres le lot de `sofia-sm` (casquette dev). Audit non degrade : fat jar reconstruit sur
l'arbre de ma branche, application lancee, paquet joue.**

#### Ce que j'ai joue

**Neuf parties, 24 items sur 24 servis.** Sur chaque item, les **deux** propositions fausses
pressees avant la bonne, puis une troisieme pression pour atteindre la revelation. Propositions
relues dans le HTML rendu et comparees au fichier apres desechappement : **zero ecart** sur les
24 enonces, 24 bonnes reponses, 48 distracteurs et 24 indices. L'indice apparait des le premier
echec ; apres trois echecs la reponse est nommee et la manche se debloque.

#### `D-6` - le motif du refus du 2026-08-29 est leve sur sa substance

**J'ai recalcule les 48 distracteurs un par un depuis les instants et la duree de leur propre item**,
et non verifie un echantillon. Les trois familles declarees en en-tete produisent exactement les
valeurs servies :

- **Forme "duree ecoulee" (items 1 a 12)** : `ERR-INVERSE` donne `60 - (duree reelle modulo 60)`,
  `ERR-REPORT` donne `duree reelle -/+ 60`. Verifie sur les 24 distracteurs de la forme.
- **Forme "horaire d'arrivee" (items 13 a 24)** : `ERR-INVERSE` soustrait la duree au lieu de
  l'ajouter, `ERR-REPORT` ajoute une heure en trop **ou** recopie l'heure de depart. Verifie sur les
  24 distracteurs de la forme.
- **`ERR-BASE100`** : presente une fois sur chaque forme, comme ma reserve l'exigeait - item 11,
  "2 heures 70 minutes" (190 minutes, reellement distinct des 90 de la bonne reponse) ; item 14,
  "10 h 50" (l'enfant compte 140 minutes pour une duree de 1 heure 40 minutes).

**Aucun ecart constant par bloc ne subsiste** : c'etait le coeur de mon refus et il est corrige.
**Les 24 calculs sont justes**, aucune collision de valeur dans aucun item sur les 72 propositions.

#### `D-7` - recompte a la main, contre la declaration de l'auteure

Je ne reprends pas son chiffre, je le refais. **Rang de la bonne reponse mesure sur la valeur reelle**
(durees converties en minutes, instants convertis en minutes depuis minuit) :

| Rang | Items | Part |
|---|---|---|
| la plus petite | 1, 3, 4, 6, 8, 10 | **6 / 24 = 25,0 %** |
| intermediaire | 2, 5, 9, 11, 13, 14, 16, 17, 19, 20, 22, 23 | **12 / 24 = 50,0 %** |
| la plus grande | 7, 12, 15, 18, 21, 24 | **6 / 24 = 25,0 %** |

**Le chiffre de `sofia-sm` est exact.** `D-7` exige qu'aucun rang ne depasse 50 % ni ne tombe sous
10 % : **50,0 % n'excede pas 50 %**, la comparaison est stricte et je la lis comme je l'ai ecrite le
2026-08-19. **Tenue.**

#### Les autres conditions

| Condition | Verdict | Mesure |
|---|---|---|
| `D-1` | **tenue** | Douze items par forme, aucune lecture d'heure seule, aucune conversion seule, aucune recherche d'heure de depart |
| `D-2` | **tenue** | Verifiee par appariement de forme sur les **72** propositions : aucun item ne melange une ecriture d'instant et une ecriture de duree |
| `D-3` | **tenue** | Plage horaire servie de 8 h a 19 h 30, aucun franchissement de minuit, aucune seconde |
| `D-4`, `D-5` | **tenues** | Declarees imprecises **par moi** le 2026-08-29 et tenues sous les deux lectures. **Je ne les rouvre pas** : elles seront reecrites en conditions neuves et datees, jamais appliquees en arriere. Duree maximale servie : 2 heures 10 minutes, sous le plafond de cinq heures |
| `D-8` | **tenue** | Mesure sur les 24 enonces : **20 a 25 mots, deux phrases**, contre un plafond de vingt-cinq mots et deux phrases. Deux items sont a 25 pile ; le plafond est inclusif, comme je l'ai ecrit |
| `D-9` | **tenue - reserve 6 levee** | Ecole, recreation, cantine, bus, train, bibliotheque, musee, ferme pedagogique, match, film, recette. Prenoms varies. **L'attribution de la recette a un garcon a disparu de l'en-tete**, ce qui etait ma reserve 6 |
| `D-10` | **tenue** | **Quatre** indices distincts sur la forme "duree", **trois** sur la forme "horaire" |
| `D-11` | **tenue** | 24 items, plancher de 20 |
| **Accentuation** | **tenue** | Meme methode que sur `S13-2` : 124 chaines, 133 jetons distincts, 104 sans caractere accentue, **dont aucun ne devrait en porter**. Les homographes accentuables (a, la, du, des, sur) ont ete releves en contexte un par un : tous corrects |

#### VERDICT : VALIDE SOUS RESERVE

**Done pedagogique prononce le 2026-08-30 par `nadia-prof`.** `camille-po` peut passer la ligne de
matrice de `S13-3` a `couvert-valide`. **La reserve 1 (bloquante, 2026-08-29) est levee sur sa
substance** ; la reserve 6 (non bloquante) aussi. Les verdicts du 2026-08-29 restent ecrits au-dessus.

**Pourquoi "sous reserve" et pas "valide" :** cinq points ci-dessous, dont un qui porte sur une
clause de `D-6` elle-meme. Le paquet est **utilisable** - il n'enseigne rien de faux, ses 24 calculs
sont justes, aucun item ne devient une devinette - mais ces cinq points sont reels et deux d'entre
eux sont **mesures a l'ecran**.

#### Reserves

- **Reserve A, non bloquante, 2026-08-30 - `D-6`, derniere clause : l'anomalie visible est seule.**
  `D-6` (arbitrage du 2026-08-19, point 16) dit : "si une ecriture de ce genre est retenue, elle ne
  doit pas etre la seule anomalie visible du paquet, sinon elle s'elimine a l'oeil sans calcul".
  **Balayage mecanique des 72 propositions : une seule porte un compte de minutes superieur a 59**,
  "2 heures 70 minutes" a l'item 11. Le second `ERR-BASE100`, "10 h 50" a l'item 14, est un horaire
  parfaitement bien forme et ne constitue donc pas une seconde anomalie. **La clause n'est pas
  tenue.**
  **Pourquoi elle ne bloque pas, et je le dis plutot que de le laisser deduire** : cette clause
  **ne figure pas** dans ma propre liste de ce que je refusais d'avance (arbitrage du 2026-08-19,
  section 3.5), liste que j'avais ecrite "pour qu'aucun lot ne revienne avec ceci dedans". En faire
  aujourd'hui un motif de refus serait deplacer un poteau apres coup, et je me l'interdis.
  **Second fait que je dois a l'auteure** : c'est **ma** reserve du 2026-08-29 - "au moins un item
  par famille propose la valeur qu'ecrit l'enfant qui compte en base 100" - qui rend cette ecriture
  obligatoire sur la forme "duree", ou l'erreur de base 100 produit mecaniquement plus de
  59 minutes. **Mes deux exigences sont en tension et c'est mon defaut, pas le sien.**
  **Levee** : une seconde ecriture de base 100 sur un autre item de la forme "duree", ou le retrait
  de celle-ci. Une chaine.
- **Reserve B, non bloquante, 2026-08-30 - `TYP-1` : quatre signes orphelins, mesures.** Ce paquet
  est le seul des trois de s22 a ne pas porter d'espace insecable devant le signe - 0 sur 24, contre
  25 sur 25 pour `S13-2` et 20 sur 20 pour `S17-2`. **Mesure au navigateur reel a 744 x 551 avec mon
  propre instrument** (`squad/outils/nadia-prof/chercher-un-signe-orphelin.py`, 453 textes mesures,
  229 se replient) : **18 signes orphelins dans le catalogue contre 14 le 2026-08-28, et les 4
  nouveaux sont tous dans ce paquet** - items Malo, Iris, Zoe, Maya, dont le point d'interrogation
  occupe seul une troisieme ligne.
  **Pourquoi elle ne bloque pas** : `TYP-1` porte sa propre phrase - "Aucun lot n'est un prealable
  au Done pedagogique d'aucun paquet". **Cette phrase est mienne et je m'y tiens.**
  **Ce que je clarifie, comme une condition neuve et datee et non comme un durcissement** : ma
  redaction du 2026-08-28 ne disait pas si `TYP-1` s'applique au **contenu neuf** livre apres cette
  date. **L'ambiguite est la mienne.** A compter du 2026-08-30, tout paquet neuf applique `TYP-1` ;
  ce paquet-ci n'en est pas justiciable retroactivement, et son en-tete disait honnetement pourquoi
  il ne l'appliquait pas.
- **Reserve C, non bloquante, 2026-08-30 - un horaire coupe en deux lignes.** Dans le meme releve,
  l'item d'Iris rend "de 8 h 45 a 9" en fin de ligne et "h 10." au debut de la suivante :
  **l'instant 9 h 10 est coupe entre le nombre et son unite.** Pour un enfant qui apprend a lire
  l'heure, c'est plus genant qu'un signe orphelin. `TYP-1` ne couvre que la ponctuation double et
  **ne couvre pas ce cas** ; je le nomme sans le transformer en condition aujourd'hui.
- **Reserve D, non bloquante, 2026-08-30 - la bonne reponse n'est jamais la plus petite sur la forme
  "horaire d'arrivee".** Sur les 12 items de cette forme, le compte est **0 la plus petite / 8
  intermediaire / 4 la plus grande**. C'est structurel : `ERR-INVERSE` et la recopie de l'heure de
  depart produisent toutes deux un horaire anterieur, jamais posterieur. **Un enfant qui a repere la
  forme peut ecarter la proposition la plus matinale sans calculer**, sur la moitie du paquet.
  **`D-7` est tenue et je la declare tenue** : elle se mesure sur le paquet, ou le compte est
  25,0 / 50,0 / 25,0. **Ceci est un constat neuf et date, pas un durcissement de `D-7`**, et il est
  une consequence directe de la correction que j'ai exigee le 2026-08-29.
- **Reserve E, non bloquante, 2026-08-30 - l'en-tete depasse encore un peu son contenu.** Il ecrit
  que "la recette est preparee en classe par des filles - Iris, Nour" ; l'item d'Iris dit bien "en
  classe", celui de Nour ne le dit pas. `D-9` reste tenue - c'est la phrase qui devance le contenu,
  pas le contenu qui manque. Meme famille que ma reserve 6 du 2026-08-29, en beaucoup plus petit.

#### `MNC-1`

**`MNC-1`** - Ce Done ne couvre pas que l'enfant voie, sans faire defiler la page, un element
quelconque du jeu, y compris le retour qui suit sa reponse - verdict, indice, proposition. Cette
propriete est sortie de la porte pedagogique par arbitrage de l'utilisatrice du 2026-08-28
(`DEC-0053`) ; sa dette est ouverte, croissante et assumee. Aucune mesure ne l'a instruite sur ce
paquet.

Le rappel n'est pas de pure forme sur ce paquet : ce sont ses enonces qui sont les plus longs du
catalogue, et c'est moi qui l'ai ecrit le 2026-08-19.

#### Ce que je reste seule a garder sur ce paquet

**Que 48 distracteurs soient des erreurs reelles d'enfant** - un automate compte les propositions, il
ne sait pas si un nombre est une erreur ; **l'exactitude des 24 calculs de duree** ;
**l'accentuation** ; **et la derniere clause de `D-6`**, dont la reserve A montre qu'aucune mesure
existante ne la voit.
