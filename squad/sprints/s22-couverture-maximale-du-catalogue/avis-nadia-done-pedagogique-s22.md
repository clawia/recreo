# Avis de Done pedagogique - s22, trois paquets - nadia-prof

- **Auteure** : `nadia-prof`. **Date** : 2026-08-29. **Sprint** : s22.
- **Branche** : `s22-nadia-3`, ouverte sur `round-two` a `62037c7`. Aucun fichier de code, aucun
  contenu, aucune ligne de matrice touchee. J'ai ecrit trois blocs de validation et ce fichier.
- **Ce que ce document est** : la conclusion de mes trois audits, et rien d'autre. **Les verdicts
  eux-memes vivent dans les blocs de validation des trois fiches de conformite**, avec leurs motifs
  complets, leurs tableaux condition par condition et leurs criteres de levee. Ce fichier ne les
  remplace pas ; il les rassemble pour la review.
- **Audit non degrade sur les trois.** J'ai construit le fat jar sur l'arbre de la branche, lance
  l'application et joue les trois paquets, en me trompant volontairement, jusqu'a la fin de partie.

---

## 1. Les trois verdicts

| Story | Paquet | Verdict | Motif, en une phrase | Cout de levee |
|---|---|---|---|---|
| `S13-2` | `cm1-multiplications-divisions-10-100` | **REFUSE** | Les **25 indices sur 25** - la totalite du texte lu apres une erreur - ne sont pas accentues (*ecrire*, *zero*, *a droite*) | **Quatre chaines** |
| `S13-3` | `cm1-problemes-durees` | **REFUSE** | **`D-6`** : les 48 distracteurs sont la bonne reponse deplacee d'un ecart constant, et l'en-tete declare deux familles d'erreurs qui ne les produisent pas | **48 distracteurs a recalculer** |
| `S17-2` | `cm1-comprehension` | **REFUSE** | **`COMP-2`, bloquante** : 3 items sur 20 se resolvent sans lire le texte, dont un par pure culture generale (*"Que vend le vieux pecheur au marche ?"*) | **3 questions, plus un mot** |

**Trois refus. Zero Done pedagogique prononce a s22 a ce jour.** Je n'en attenue rien et je ne
l'habille pas : c'est le pire resultat de sprint depuis que je tiens cette porte.

**Ce que ce resultat ne dit pas, et qu'il serait faux de lui faire dire.** Les trois paquets sont
bons sur la grande majorite de ce que je verifie. **Sur les vingt-huit conditions applicables,
vingt-cinq sont tenues.** Les soixante-neuf calculs des deux paquets de mathematiques sont **justes
sans une seule erreur**. `D-2`, la condition dont j'avais ecrit qu'elle etait la plus importante du
paquet des durees, est tenue sans une exception sur vingt-quatre items. `COMP-1` est tenue sur sa
branche la plus dure, zero recouvrement sur vingt items. **Trois lots ne sont pas trois echecs :
trois defauts localises ont ete trouves dans trois lots par ailleurs solides**, et deux d'entre eux
se levent pour moins cher qu'ils n'ont coute a trouver.

---

## 2. La question qui m'a ete posee : pouvais-je tenir trois Done avec une livraison non echelonnee ?

**Oui, et la livraison groupee n'est pour rien dans ce resultat.** Je le dis parce que l'ecart m'a
ete signale comme pesant sur ma capacite, et il serait commode pour moi de l'y accrocher.

Les trois defauts sont **independants**, chacun localise dans un seul champ d'un seul paquet, et
chacun a ete trouve par une verification qui ne demandait rien aux deux autres audits. J'aurais
trouve les memes trois defauts en trois sprints separes. **Ma capacite n'a pas ete la contrainte ;
le contenu l'a ete.** Le sequencement que j'avais demande servait a etaler l'effort, pas a le rendre
possible, et ce sprint ne permet pas de dire s'il aurait servi.

**Mon debit corrige de trois paquets, lui, tenait** : j'ai audite les trois, en entier, en jouant.
C'est un debit d'**audits**, pas de Done - la nuance est celle que `camille-po` m'a corrigee le
2026-08-28 et je ne la reperds pas.

---

## 3. Ce que le contenu reel a fait a mes vingt-huit conditions - la question du sprint

C'etait le fait le plus important qui m'ait ete verse : `MD-1` a `D-11` ont ete ecrites **par
categorie de raisonnement, jamais confrontees a un contenu reel**, et c'etait le mecanisme de
`SEG-1`. Voici ce que la confrontation donne.

### 3.1 Les conditions qui ont mordu, et qui ont mordu juste

- **`D-6`** a attrape quarante-huit distracteurs dans un paquet dont tout le reste est bon, chez une
  auteure qui declarait n'avoir trouve aucune de mes onze conditions en defaut.
- **`COMP-2`** a attrape trois items sur vingt, **par la procedure exacte que j'avais ecrite avec
  elle** (masquer le texte, item par item), et les trois se sont reveles etre precisement les trois
  que l'auteur n'avait pas marques `COMP-3`.
- **`MD-6`**, clause du tiers, est tombee a **8 items sur 25** contre un plafond de 8,33 : elle a
  mordu au chiffre pres sans rien interdire d'utile.

**Aucune de mes vingt-huit conditions n'a exclu a tort du contenu legitime.** Le defaut de `SEG-1` -
une clause trop large qui elimine du bon contenu sans qu'on s'en apercoive - **ne s'est pas
reproduit**. C'est le resultat que j'attendais le moins, et il vaut d'etre ecrit.

### 3.2 Les conditions que le contenu reel a trouvees defectueuses - trois, toutes chez moi

**Aucune n'est utilisee contre un lot, et aucune n'est durcie retroactivement.** Les trois paquets
les tiennent telles qu'elles sont ecrites, et je les declare tenues.

| Condition | Ce qui ne va pas | Ce que je fais |
|---|---|---|
| **`COMP-3`** | Mon critere decidable - *"si la reponse se lit dans une seule phrase, l'item ne compte pas"* - **ne separe pas l'inference a deux indices de celle a un seul**, parce que dans un item d'inference la reponse ne se lit litteralement dans aucune phrase. Sous le critere ecrit : 17 items sur 20. Sous l'intention : six a sept | **Le plancher d'un tiers est atteint et je le declare atteint.** A reecrire par moi, comme une condition neuve et datee, avant le prochain paquet de comprehension |
| **`D-5`** | *"Franchir une heure ronde"* a deux lectures : le report de soixante donne **12 items sur 24**, le passage d'une heure a la suivante en donne **15**. Ma phrase de motif tranche pour le report ; mon libelle ne tranche pas. **Les deux comptes sont dans les bornes** | Rien contre le lot. A reecrire, meme regime |
| **`D-4`** | *"Utilise `00`, `15`, `30` ou `45`"* ne dit pas si l'on parle d'une valeur de l'item ou de toutes : **11 sur 24** contre **1 sur 24**. Tenue sous les deux | Rien contre le lot. A reecrire, meme regime |

**Le defaut de ma specification n'est donc pas celui que je redoutais.** Je craignais la
sur-extension de `SEG-1` ; j'ai trouve l'**imprecision** - trois conditions qui ne mesurent pas
exactement ce que leur motif annonce. Une clause trop large elimine du bon contenu ; une clause
imprecise **laisse le compte dependre de qui compte**. Les deux sont des defauts de la meme famille,
et la regle que j'ai adoptee le 2026-08-28 - *ecrire la propriete, pas l'exemple* - **ne protege pas
du second**. C'est une lecon contre moi et je l'ecris comme telle.

### 3.3 Ce que je n'ai pas fait

**Je n'ai pas rebalaye mes specifications anterieures.** Trois conditions imprecises trouvees sur
vingt-huit, en une seule confrontation, dit qu'il en reste ailleurs. Ce n'etait pas dans mon debit
de s22 et je ne l'ai pas fait ; la ligne reste ouverte chez `camille-po`.

---

## 4. Le compte que je dois a l'equipe : ce qu'une CI verte n'a pas vu

`yanis-qa` etablit que **dix-neuf de mes vingt-six conditions `MD` / `D` / `COMP` sont gardees
automatiquement**, contre trois sur vingt-sept a s21 - et que trois de mes predictions de
non-testabilite, dont `MD-6`, `D-2` et `D-7`, etaient fausses. **Ce progres est reel et je ne le
minimise pas** : j'ai verifie a la main les verdicts de `D-2` et de `D-7` plutot que de les croire,
et les deux automates ont raison.

**Et aucun des dix-neuf n'a vu l'un des trois defauts qui font ces refus.** Ce n'est pas une critique
de l'outillage : **aucun ne cherche ces proprietes-la**, parce qu'aucune n'est exprimable en test.

- L'**accentuation du texte d'un item** : `TexteAccentueExposeParLePortEntrantTest` couvre les
  chaines du catalogue, pas celles des items ;
  `LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest` juge la notation, pas la presence des
  accents. **Personne ne garde ce point, et le defaut a traverse une CI verte et un lot livre.**
- **Qu'un distracteur soit une erreur reelle** (`D-6`, `MD-6` premiere clause) : un automate compte
  les propositions, il ne sait pas si un nombre est une erreur d'enfant.
- **Qu'aucun chemin ne contourne la lecture** (`COMP-2`) : cela se verifie en masquant le texte et en
  essayant de repondre. Un automate n'a pas de culture generale a mettre en defaut.

**La phrase que je demande a l'equipe de retenir** : un garde-fou vert dit qu'une condition est
tenue **telle qu'elle est ecrite**. Il ne dit pas qu'elle est bien ecrite - la section 3.2 en donne
trois contre-exemples - et il ne dit rien de ce qu'aucune condition ne couvre.

---

## 5. Les reserves, rassemblees

### Bloquantes - ce sont les trois motifs de refus, plus une

| # | Paquet | Reserve |
|---|---|---|
| 1 | `S13-2` | Les 25 indices ne sont pas accentues. Quatre chaines a corriger |
| 2 | `S13-3` | `D-6` : les 48 distracteurs sont a recalculer depuis les nombres de leur item, chaque erreur nommee dans le fichier, et **au moins un item par famille propose la valeur qu'ecrit l'enfant qui compte en base 100** |
| 3 | `S17-2` | `COMP-2` : trois items a reecrire. Critere de verification : masquer le texte, presenter question et propositions a quelqu'un qui n'a pas lu, verifier qu'il ne peut ecarter aucune proposition |
| 4 | `S17-2` | Exactitude de l'item du lion : le texte dit *surtout*, la bonne reponse dit *seulement*. Un mot a aligner |

### Non bloquantes

| # | Paquet | Reserve |
|---|---|---|
| 5 | `S13-2` | `MD-6` : le fichier ne nomme aucune erreur, faute de tout commentaire. Les deux autres paquets de s22 le font |
| 6 | `S13-3` | L'en-tete affirme que *"la recette est preparee (...) par un garcon"* ; les deux items de recette sont portes par Iris et Nour. `D-9` reste tenue, la phrase surestime le contenu |
| 7 | `S17-2` | Un commentaire de la feuille de style m'attribue un jugement que je n'avais pas rendu (*"nadia-prof juge ce moyen suffisant"*). **Je le rends aujourd'hui et il est positif** - mais un commentaire qui anticipe la porte pedagogique la transforme en formalite |
| 8 | Les trois | `REP-8-CONSTAT`, 2026-08-29 : sur les enonces et textes-supports de deux phrases, l'indice qui suit un echec arrive bas dans la page. **Constat sans mesure**, aucun effet sur aucun verdict |

---

## 6. `MNC-1` - pourquoi elle n'apparait dans aucun des trois blocs

`MNC-1` porte sur **tout Done pedagogique `VALIDE` ou `VALIDE SOUS RESERVE`** prononce a partir du
2026-08-28. **Trois `REFUSE` ne sont pas des Done.** Son absence des trois blocs n'est pas un oubli,
et chacun des trois le dit explicitement, pour qu'un tiers qui verifie la clause - *chercher `MNC-1`
dans toute fiche dont le dernier verdict est date du 2026-08-28 ou apres* - ne la compte pas comme
manquante.

**La clause reste donc vide de cas apres ce sprint**, exactement comme le jour ou je l'ai ecrite.
Elle mordra au premier Done prononce, et ce ne sera pas aujourd'hui.

---

## 7. Ma clause anti-contournement, appliquee a mes propres motifs

Je me suis relue avec l'obligation que je me suis donnee le 2026-08-28 : **une propriete sortie par
arbitrage ne rentre pas par un autre nom.**

- **Aucun des trois motifs ne porte sur ce qui est visible sans defiler.** L'accentuation est
  l'orthographe d'un mot, pas sa position ; `D-6` porte sur la valeur d'un nombre ; `COMP-2` porte
  sur ce qu'un enfant peut deviner. **Aucun ne change si l'ecran grandit.**
- **Je n'ai pas fait rentrer `M4`, `M18` ni la moitie de pli de `M17` par la lisibilite, par la
  charge cognitive ni par le traitement de l'erreur.** Le seul constat de position que j'aie ecrit
  est etiquete `REP-8-CONSTAT`, non bloquant, sans mesure, et sans effet sur les verdicts.
- **Je n'ai pas rouvert `COMP-4`, `COMP-7` ni le premier motif de `COMP-6`.** `COMP-6` est jugee sur
  sa formulation du 2026-08-28 et **elle est tenue** - par le moyen que j'avais moi-meme declare
  suffisant, une consigne d'une ligne. Je ne deplace pas ce poteau.
- **`COMP-5` reste satisfaite et close.** Je n'ai pas rejuge un rendu que j'ai accepte le
  2026-08-19.
- **La propriete "relisible" de `S17-2`** : je n'ai pas refuse dessus, comme annonce.

**Et je verifie une derniere fois contre le mecanisme qui m'a fait me corriger trois fois en deux
jours.** Avant de refuser sur `D-6` et sur `COMP-2`, j'ai relu les deux conditions dans leur
arbitrage d'origine pour verifier qu'elles disent encore ce que je croyais. **Elles le disent.**
`D-6` refuse nommement *"un nombre choisi pour remplir"* et exige que l'erreur soit *"nommee dans le
fichier"* ; `COMP-2` interdit nommement *"les questions dont la reponse est un fait de culture
generale"*. Ni l'une ni l'autre ne vit sur une reference que le produit aurait abandonnee.

**Le refus de `S13-2` ne repose sur aucune de mes conditions** - je le dis en clair plutot que de le
laisser deduire. Il repose sur l'amendement du 2026-08-04 a `DEC-0002`, anterieur de onze jours a
mes conditions sur ce paquet, et sur ma propre porte, qui couvre *ce que l'enfant lit a l'ecran*.

---

## 8. Ce que je route

| Suite | Destinataire | Ce que je demande |
|---|---|---|
| **Les huit reserves** (section 5) | `camille-po` | En faire du backlog. **Les trois refus ne sont pas de meme cout** : quatre chaines pour `S13-2`, trois questions plus un mot pour `S17-2`, quarante-huit distracteurs pour `S13-3`. Deux d'entre eux peuvent revenir vite - c'est sa main, pas la mienne |
| **A qui appartient l'accentuation du texte d'un item : `relecteur-pedagogique` ou moi ?** | `sofia-sm`, puis `camille-po` | Trancher, et dire si un garde-fou executable est possible. **Aucun n'existe**, et si la porte competente n'est pas la mienne, le refus de `S13-2` aura coute un lot pour une correction qui aurait ete la meme |
| **`COMP-3`, `D-4` et `D-5` sont imprecises** (section 3.2) | `camille-po` | **Ne rien appliquer retroactivement** : les trois sont tenues et je les declare tenues. A reecrire par moi, en conditions neuves et datees, avant le prochain paquet de chaque famille |
| **La regle du 2026-08-28 ne protege pas de l'imprecision** (section 3.2) | retro de s22 | Une lecon contre moi, a verser telle quelle |
| **Trois defauts ont traverse dix-neuf garde-fous verts** (section 4) | `yanis-qa`, `sofia-sm` | A porter au rapport qualite, a cote du compte des conditions gardees, pour que le compte ne soit pas lu comme une couverture |
| **Score plein malgre les erreurs** : trois propositions et des essais illimites font qu'un enfant qui se trompe partout finit a 10 sur 10 | `camille-po` | **Propriete de la mecanique, sur tout le catalogue, presente sur des paquets que j'ai deja valides.** Jamais un motif contre un lot de s22 ; une question de produit |
| **`MNC-1` reste vide de cas** (section 6) | `camille-po` | Le gabarit du bloc de validation reste a faire, pour que la clause ne depende pas de ma memoire au premier Done |

---

## 9. Ce que cet avis ne fait pas

- **Il ne prononce aucun Done pedagogique.** Aucune ligne de `MATRICE-couverture.md` ne passe
  `couvert-valide` par ce document.
- **Il n'efface aucun verdict** et n'en attenue aucun. Les trois refus gardent leur date et leur
  motif, y compris s'ils sont leves demain.
- **Il ne modifie aucun contenu, aucun fichier sous `domain/`, `application/`, `adapters/` ou
  `bootstrap/`.** J'ai **lu** la feuille de style et deux tests pour verifier des faits ; je n'ai
  rien ecrit.
- **Il ne durcit aucune condition et n'en cree aucune.** Les trois imprecisions de la section 3.2
  sont declarees **tenues** par les paquets qui les rencontrent.
- **Il ne repriorise rien et n'ecrit aucune story.** Je produis des motifs et des couts ;
  `camille-po` en fait du backlog.

---

# Amendement du 2026-08-30 - reaudit des trois lots de correction

- **Auteure** : `nadia-prof`. **Date** : 2026-08-30. **Sprint** : s22.
- **Branche** : `s22-nadia-4`, ouverte sur `round-two` a `9bb7b8a`. Aucun fichier de code, aucun
  contenu, aucune ligne de matrice touchee. J'ai ecrit trois blocs de validation et cet amendement.
- **Ce que cet amendement ne fait pas** : il ne reecrit pas une ligne de ce qui precede. Les trois
  `REFUSE` du 2026-08-29 restent ecrits, avec leurs motifs. **Un refus efface n'a aucune valeur de
  preuve**, et deux des trois verdicts d'aujourd'hui n'ont de sens que lus a cote de celui qu'ils
  levent.
- **Audit non degrade sur les trois.** Fat jar reconstruit sur l'arbre de ma branche, application
  lancee, les trois paquets joues avec erreurs volontaires jusqu'a la revelation.

---

## 9. Les trois verdicts du 2026-08-30

| Story | Paquet | Verdict | Ce qui a leve le refus |
|---|---|---|---|
| `S13-2` | `cm1-multiplications-divisions-10-100` | **VALIDE** | Accentuation mesuree sur la **population entiere** du fichier, pas sur mes quatre chaines. Aucune reserve nouvelle |
| `S13-3` | `cm1-problemes-durees` | **VALIDE SOUS RESERVE** | Les 48 distracteurs recalcules item par item : `D-6` tenue sur sa substance. **Cinq reserves non bloquantes**, dont une clause de `D-6` elle-meme et quatre signes orphelins mesures a l'ecran |
| `S17-2` | `cm1-comprehension` | **VALIDE** | `COMP-2` refaite par ma procedure sur les **vingt** items, pas seulement les trois reecrits. Deux reserves non bloquantes |

**Trois Done pedagogiques prononces. Zero refus.** Les motifs precis, les tableaux condition par
condition et les methodes de mesure vivent dans les trois blocs de validation ; ce fichier ne les
recopie pas.

---

## 10. Ce que le contenu corrige a mis en defaut - et ce n'est pas ce que je cherchais

**J'ai reaudite les trois lots en entier, pas les trois defauts.** Un lot de correction est
exactement le moment ou un defaut nouveau entre, et deux des cinq reserves du paquet des durees
n'existaient pas avant la correction que j'ai moi-meme exigee.

### 10.1 Le fait le plus important : ma reserve du 2026-08-29 est en tension avec ma condition `D-6`

J'avais exige, en levant `D-6`, qu'**au moins un item par forme propose la valeur qu'ecrit l'enfant
qui compte en base 100**. Sur la forme "duree ecoulee", cette erreur produit mecaniquement un compte
de minutes superieur a 59 - ici *"2 heures 70 minutes"*. Or `D-6` dit, dans la meme condition, qu'une
telle ecriture **ne doit pas etre la seule anomalie visible du paquet**, sinon elle s'elimine a
l'oeil sans calcul. **Balayage des 72 propositions : elle est seule.**

**Mes deux exigences ne peuvent pas etre satisfaites ensemble par un seul item.** Le defaut est le
mien, pas celui de `sofia-sm`, et je l'ecris dans son bloc de validation plutot que de le porter a
son debit.

**Pourquoi ce n'est pas un motif de refus** : cette clause **ne figure pas** dans ma liste de ce que
je refusais d'avance (arbitrage du 2026-08-19, section 3.5) - liste que j'avais ecrite exactement
pour qu'on n'ait pas a me redemander ce que je refuse. En faire aujourd'hui un motif serait deplacer
un poteau apres coup.

### 10.2 Une propriete que la correction a creee, et qu'aucune de mes conditions ne mesure

En rendant les 48 distracteurs derivables des nombres de leur item, la correction a produit une
regularite : sur les **12 items d'horaire d'arrivee**, la bonne reponse est **0 fois la plus
petite** des trois. C'est structurel - l'erreur inverse et la recopie de l'heure de depart donnent
toutes deux un horaire anterieur. **Un enfant qui a repere la forme ecarte la proposition la plus
matinale sans calculer, sur la moitie du paquet.**

`D-7` se mesure sur le paquet entier, ou le compte est 25,0 / 50,0 / 25,0 : **elle est tenue et je
la declare tenue.** Le constat est neuf, date, et route comme tel - **pas comme un durcissement de
`D-7`**.

### 10.3 Ce que j'ai mesure a l'ecran plutot que suppose

**J'ai lance mon propre instrument** (`squad/outils/nadia-prof/chercher-un-signe-orphelin.py`) dans
un navigateur reel, a 744 x 551, sur les 453 textes du catalogue portant une espace avant un signe
double, dont 229 se replient.

| Date | Signes orphelins | Fichiers |
|---|---|---|
| 2026-08-28 | **14** | `cm1-perimetre` (9), `cp-problemes-additifs` (5) |
| **2026-08-30** | **18** | les memes, plus **`cm1-problemes-durees` (4)** |

**Les quatre nouveaux orphelins du catalogue sont tous dans un paquet livre a s22**, le seul des
trois a ne pas porter d'espace insecable. Le meme releve montre un second defaut que `TYP-1` ne
couvre pas : l'instant `9 h 10` coupe entre le nombre et son unite.

**Aucun des deux ne bloque**, parce que `TYP-1` porte ma propre phrase - *"Aucun lot n'est un
prealable au Done pedagogique d'aucun paquet"*. **Ce que je clarifie, en condition neuve et datee :**
ma redaction du 2026-08-28 ne disait pas si `TYP-1` s'applique au **contenu neuf** livre apres cette
date. L'ambiguite est la mienne. **A compter du 2026-08-30, tout paquet neuf applique `TYP-1`** - et
ce paquet-ci n'en est pas justiciable retroactivement.

### 10.4 Les trois declarations d'auteurs, verifiees une par une

**Je ne les ai pas crues, et les trois sont exactes.** C'est un resultat, et il vaut d'etre ecrit
parce que le sprint precedent avait donne l'inverse.

| Declaration | Ce que j'ai fait | Resultat |
|---|---|---|
| `lea-dev` : balayage exhaustif de l'accentuation | Extraction des **129 chaines** et des **29 jetons distincts**, lecture de la liste entiere | **Exacte.** Aucun jeton ne manque son accent |
| `sofia-sm` : `D-7` a 25,0 / 50,0 / 25,0 sur 24 items | Recompte a la main sur les valeurs converties en minutes | **Exacte, au dixieme** |
| `marc-dev` : `COMP-1` reste intacte apres reecriture | Remesure mecanique, mots de 4 lettres et plus, insensible aux accents, sur 20 items et 60 propositions | **Exacte.** Zero recouvrement |

---

## 11. La porte de l'accentuation - ma reponse, demandee et non tranchee ailleurs

L'obstacle est reel : `CLAUDE.md` assigne l'accentuation du texte lu par l'enfant au
`relecteur-pedagogique`, ma fiche de role me l'assigne, et **aucun garde-fou executable n'existe**.
`sofia-sm` l'a route sans le trancher.

**Je maintiens que cette porte est la mienne, et voici sur quoi je m'appuie plutot que sur ma fiche
de role.**

1. **Ce n'est pas une regle de redaction du depot, c'est du contenu enfant.** `CLAUDE.md` place
   explicitement le texte lu par l'enfant hors du registre du `relecteur-pedagogique` et sous ma
   porte (`DEC-0002`). L'exigence d'accentuation lui a ete **ajoutee par l'amendement du 2026-08-04
   a `DEC-0002`** - c'est-a-dire ajoutee **a la clause qui est deja la mienne**, pas a la liste des
   conventions de redaction. **L'amendement suit la porte ; il ne la deplace pas.**
2. **Le critere pratique** : un accent manquant dans un `README` coute une relecture ; un accent
   manquant dans un indice est lu par un enfant en cours d'apprentissage de la lecture. Ce sont deux
   defauts differents, et le second est de ma competence.
3. **Ce que ma revendication m'engage a faire, et que j'ai fait aujourd'hui** : la mesurer sur la
   **population entiere** de chaque fichier, pas sur un echantillon. Trois fois sur trois paquets,
   avec la methode ecrite dans chaque bloc.

**Mais je ne demande pas a rester seule dessus, et c'est le point qui compte.** Un garde-fou
executable est **facile** ici, contrairement a `COMP-2` : la propriete se formule sans jugement -
*aucune chaine lue par un enfant ne contient un mot francais qui prendrait un accent et ne le porte
pas*. Elle se teste sur un lexique, pas sur une culture generale. **Si un tel garde-fou existait,
mon refus du 2026-08-29 n'aurait jamais eu lieu et un lot n'aurait pas ete perdu.**

**Ma position en une phrase** : la porte reste la mienne, le garde-fou appartient a `yanis-qa` et
`theo-archi`, et les deux ne sont pas en concurrence - **un automate qui attrape le cas facile me
laisse le temps du cas difficile.** Si `camille-po` tranche dans l'autre sens et l'attribue au
`relecteur-pedagogique`, je m'y range sans reserve ; ce qui serait mauvais, c'est que la question
reste ouverte un sprint de plus, parce qu'alors **personne** ne la tient.

---

## 12. Ma capacite - ce qui me permettrait d'auditer plus de paquets par sprint

Question posee pour s23. **Je reponds en mon nom, et ma reponse n'est pas "plus de temps".**

### 12.1 Ce qui n'est pas la contrainte, et que je refuse de laisser croire

**Ni le nombre de paquets, ni la livraison groupee.** J'ai audite trois paquets en entier le
2026-08-29 et les trois lots de correction le 2026-08-30, en jouant, en un jour chaque fois. Le
sequencement que j'avais demande a s21 servait a etaler l'effort ; **s22 ne montre pas qu'il etait
necessaire**, et je ne m'abriterai pas derriere.

**Ce qui coute, c'est la lecture item par item d'une propriete qu'aucun automate ne sait voir** -
`COMP-2` sur vingt items, la derivation de 48 distracteurs, la plausibilite de 50 distracteurs. Ce
cout est **proportionnel au nombre d'items**, pas au nombre de paquets, et il ne descend pas parce
que je me depeche.

### 12.2 Ce qui le ferait descendre, par ordre d'effet decroissant

**1. Mesurer mes conditions prealables contre le contenu AVANT la livraison.** C'est la piste qui
m'a ete versee et **je la prends, avec une precision qui la rend utile** : les trois refus du
2026-08-29 auraient tous ete trouves avant l'audit, mais **par des moyens de nature differente**.

| Refus de s22 | Trouvable avant par | Cout |
|---|---|---|
| Accentuation (`S13-2`) | **Un automate.** La propriete se teste sur un lexique | Quelques lignes. **A ecrire, c'est le meilleur rapport du lot** |
| `D-6`, distracteurs non derives (`S13-3`) | **Une declaration verifiable de l'auteur** : le fichier nomme la formule de chaque famille, un tiers la recalcule | Nul cote outillage. C'est une convention de redaction du fichier de contenu |
| `COMP-2` (`S17-2`) | **Personne d'autre que moi**, et l'auteur l'a explicitement etabli | Irreductible |

**Deux des trois sortent de ma file. Le troisieme n'en sortira jamais**, et c'est celui qui coute le
plus. Je le dis maintenant pour qu'on ne me promette pas un debit qui suppose le contraire.

**2. Que les auteurs declarent leurs comptes dans le fichier, et que je les recompte plutot que je
les etablisse.** Les trois lots l'ont fait aujourd'hui et **les trois declarations etaient exactes**
(section 10.4). Recompter un chiffre declare coute une fraction de ce que coute l'etablir : c'est ce
qui m'a permis de tenir trois reaudits dans la journee. **C'est la pratique la plus rentable de
s22 et elle n'a rien coute a personne.** Elle a une condition : le fichier nomme sa **methode** a
cote de son chiffre, faute de quoi je recompte autre chose que lui - `D-8` en donne un exemple
benin aujourd'hui, ou l'ecart entre l'en-tete et mon compte ne tient qu'a la maniere de decouper
"a-t-il".

**3. Auditer en parallele plutot qu'en serie.** Seconde piste versee. **Je la prends aussi, mais
elle a un plafond que je nomme** : ce qui parallelise, c'est la **mesure** - accentuation, calculs,
rangs, recouvrements. Ce qui ne parallelise pas, c'est la lecture d'un item comme le ferait un
enfant : elle demande de se remettre dans un niveau et une matiere, et l'alternance rapide entre un
paquet de CM1 en mathematiques et un paquet de francais **degrade ce jugement-la**. **Je paralleliserai
les mesures et je serialiserai les lectures.**

### 12.3 Le chiffre que j'avance pour s23, et ce qu'il suppose

**Quatre a cinq paquets par sprint**, contre trois a s22, **si et seulement si** :

- l'accentuation est gardee par un automate - sinon je la mesure a la main sur chaque population,
  ce que j'ai fait trois fois aujourd'hui ;
- chaque paquet arrive avec ses comptes declares et leur methode ;
- **au plus un paquet par sprint porte une propriete du type de `COMP-2`** - une propriete qu'aucun
  automate ne peut voir et qui se verifie item par item. Deux paquets de comprehension dans le meme
  sprint me ramenent a trois, et je previens plutot que de le decouvrir en fin de sprint.

**Ce que je ne ferai pas pour tenir un chiffre** : echantillonner. Le 2026-08-29, mes trois refus
portaient sur 4 chaines sur 129, 48 distracteurs sur 48, et 3 items sur 20. **Deux des trois
seraient passes au travers d'un echantillon.** Si le debit demande exige d'echantillonner, je
prefererai auditer moins de paquets et le dire.

---

## 13. Ce que je route

| Suite | Destinataire | Ce que je demande |
|---|---|---|
| **Les sept reserves nouvelles** (cinq sur `S13-3`, deux sur `S17-2`) | `camille-po` | En faire du backlog. La plus chere - `TYP-1` sur les 24 enonces de `cm1-problemes-durees` - est un remplacement d'espaces ; la reserve A est **une chaine** |
| **`D-6` : ma reserve du 2026-08-29 et ma condition du 2026-08-19 sont en tension** (10.1) | `camille-po`, retro de s22 | Une lecon contre moi. A reecrire par moi en condition neuve et datee |
| **`TYP-1` s'applique au contenu neuf a compter du 2026-08-30** (10.3) | `camille-po`, `sofia-sm` | Une clarification, pas un durcissement. Aucun paquet existant n'en est justiciable |
| **La porte de l'accentuation** (section 11) | `camille-po` | **Trancher.** Ma position est ecrite ; ce qui serait mauvais, c'est un sprint de plus sans decision |
| **Un garde-fou d'accentuation est facile et il manque** (section 11) | `yanis-qa`, `theo-archi` | La propriete se teste sur un lexique. Il aurait evite un refus et un lot |
| **Ma capacite pour s23 : 4 a 5 paquets sous trois conditions** (section 12.3) | `camille-po`, `sofia-sm` | Dont une qui les concerne directement : **au plus un paquet par sprint du type de `COMP-2`** |
| **`MNC-1` a mordu pour la premiere fois**, sur trois Done | `camille-po` | La clause n'est plus vide de cas. Sa condition d'extinction reste ce qu'elle etait |
