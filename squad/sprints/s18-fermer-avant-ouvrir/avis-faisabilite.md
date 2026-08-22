# Avis de faisabilite - theo-archi, sprint 18

- **Auteur** : theo-archi. **Date** : 2026-08-22.
- **Arbre mesure** : `round-two` a **`494da1aa7841fb8ccb752f7a42573aee8b09ff94`**, SHA releve par
  `git rev-parse HEAD` sur ce worktree, jamais repris d'un message de commit. Arbre propre a
  l'ouverture.
- **Perimetre instruit** : `DEC-0041` (statut Proposee), lu par
  `git show s18-camille-po-perimetre:squad/decisions/produit/DEC-0041-*.md`, et les trois entrees de
  `docs/backlog/backlog.md` qu'elle designe (`S17-3`, `S17-4`, story de correction `S17-C`).
  Le slug de ce repertoire est celui que `DEC-0041` propose (`s18-fermer-avant-ouvrir`) ;
  `sofia-sm` le garde ou le renomme en gelant.
- **Etape 1 du planning** : cet avis precede tout commit de code de story.
- **Ce que je ne fais pas** : je ne juge aucun rendu et aucune pedagogie, je ne fixe aucune reference,
  je ne tranche pas le perimetre, je ne prononce aucun Done pedagogique, et je ne resous pas la
  question de la police - elle reste un perimetre de mesure, entiere, comme `ADR-0054` l'a laissee.
- **Aucun chiffre de rendu revendique** : je n'ai pas de navigateur, je n'ai lance aucune instance.
  Tout chiffre de pixel cite dans ce document est celui de `nadia-prof`, avec sa source.
- **Toute mesure de ce document porte sa methode a cote d'elle**, et les mesures partielles portent
  `-am` (`CLAUDE.md`, prerequis de build). JDK **21.0.10**, par la toolchain declaree.

## 0. Verdict d'ensemble

| Action | Verdict |
|---|---|
| `S17-4` (`BPM-1` reclamee) | **faisable, et son cout est mal estime** : l'entree de catalogue est bien triviale et je le confirme sur l'arbre, mais la mesure demandee est **la plus grosse campagne jamais demandee a `nadia-prof`**, et un critere de la story nomme une reference qui n'existe plus. **Deux criteres a reecrire.** |
| `S17-3` (`SYN-11` a `SYN-13`) | **faisable moyennant un `ADR`, et il est ouvert dans ce lot** (`ADR-0055`). Le critere de `SYN-11` est encodable **comme obligation de remesure**, jamais comme mesure. **Un critere a reecrire** (`SYN-13`), **un a laisser entierement a `nadia-prof`** (`SYN-12`). |
| `S17-C` (tabulation a la revelation) | **faisable en l'etat, et le motif qui l'avait differee est faux sur un point verifiable** : le faux DOM modelise deja `tabindex`. Deux mecanismes possibles, un seul rend la clause 1 verifiable par un automate. **Un `ADR` est du, dans le lot du correctif, pas ici.** |

**Un `ADR` neuf, avec sa regle dans le meme commit** : **`ADR-0055`** - la largeur demandee par une
rangee est epinglee comme celle qui lui est offerte. Motif en une phrase : `ADR-0054`, que j'ai ecrit
la veille, n'epinglait **qu'un membre** de la comparaison qui decide du repli, et le levier le moins
cher du correctif de `SYN-11` ne faisait rougir aucun test.

**Un second `ADR` est du et n'est pas ouvert ici** : celui de `S17-C`. Sa regle ne peut pas etre verte
avant que le correctif existe ; je l'ecris dans le lot du correctif, avec sa regle, et la section 4
dit exactement laquelle. Ce n'est pas un silence : c'est un engagement date, avec son declencheur.

**Aucun autre `ADR` n'est ouvert par cet avis**, et je l'ecris explicitement pour que le silence ne
tranche rien : ni pour `BPM-1`, ni pour la migration `ADR-0049`, ni pour la question de la police.

---

## 1. `S17-4` - `BPM-1` reclamee a `nadia-prof`

### 1.1 L'entree de catalogue : `camille-po` a raison, et voici sur quoi je l'ai verifie

Elle qualifie la restitution de triviale et me demande de le confirmer plutot que de le supposer.
**Confirme, et voici la methode.**

- **Le catalogue sert 12 entrees**, comptees par les lignes `- id:` de
  `adapters/adapter-content/src/main/resources/catalogue.yaml`. Le repertoire de contenu du meme
  module porte **13 fichiers**. `cm1-homophones.yaml` est le seul present et non catalogue - le meme
  constat que `camille-po`, obtenu independamment.
- **L'entree est de six lignes**, sur le patron exact des douze : `id`, `titre`, `consigne`,
  `mecanique: bandit-manchot`, `paquet: cm1-homophones`, `nombreDeManches`. **Zero ligne de Java,
  zero mecanique neuve, aucun mecanisme neuf.** Confirme.
- **Le paquet est deja au format du chargeur unique** : `schema: 1`, `type: question`, un
  `descripteur` complet, 24 items a `enonce` / `bonneReponse` / `distracteurs` / `indice`. Il est deja
  charge et deja juge par les garde-fous de contenu **aujourd'hui**, sans etre catalogue - verifie
  sur la population de ces regles, qui est le **balayage du repertoire `contenu/`** et non le
  catalogue (Javadoc de `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` : *"les paquets
  viennent du balayage de `contenu/`"*). **Consequence qui compte pour le cout** : l'entree n'expose
  le paquet a **aucune** regle de contenu qu'il n'affronte pas deja. C'est le contraire de ce que
  j'aurais suppose sans regarder.
- **Ce que l'entree ajoute reellement comme perimetre de regle** : les clauses dont la population est
  le catalogue - `ChaqueEntreeDuCatalogueChargeBienSonPaquetTest`,
  `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest`, `AucuneEntreeDuCatalogueNEnDoubleUneAutreTest`,
  `TexteAccentueExposeParLePortEntrantTest`. Les trois premieres sont satisfaites par construction (24
  items pour 10 manches ; aucun doublon d'`id` ni de `paquet`). La quatrieme demande une edition de
  son inventaire : `titre` et `consigne` de l'entree neuve y entrent, comme pour `cm1-synonymes` en
  s17. **Une edition, pas cinq.**

**Un ecart trouve en verifiant, que je verse plutot que de le garder.** Le commentaire de
`TexteAccentueExposeParLePortEntrantTest`, ligne 132, dit *"valeur unique pour les treize jeux du
catalogue reel"*. Le catalogue en porte **douze**. Ce commentaire est faux aujourd'hui, et il
redeviendrait vrai **par accident** le jour ou l'entree de `cm1-homophones` serait posee. Ce n'est pas
un defaut de garde-fou - la clause, elle, derive du catalogue - c'est un chiffre sans methode dans un
fichier commite, la famille que ce depot sanctionne. **A corriger dans le lot qui touche ce fichier**,
c'est-a-dire celui de `S17-4` s'il est gele. Je ne le corrige pas ici : je ne veux pas qu'une edition
de mon avis se confonde avec le lot de la story.

### 1.2 `outils/mesurer-rendu.sh` ne repond pas a la question posee, et il faut le dire avant

`camille-po` me route : *"confirmer que `outils/mesurer-rendu.sh` ou la methode de `nadia-prof` pour
`SYN-5` s'applique telle quelle aux 24 items"*. **Reponse : le script ne s'applique pas, la methode de
`nadia-prof` s'applique, et ce n'est pas la meme chose.**

- Le script prend `<largeur_css> <url> <png> [hauteur_css]` et rend **une capture d'ecran**. Il ne
  rend aucune position d'element, aucun `scrollY`. Ce qu'il produit doit etre **regarde** par
  quelqu'un.
- **Il ne peut pas atteindre un etat d'echec.** Un etat d'echec s'obtient en **postant** une mauvaise
  reponse dans une session de partie ; le script fait un rendu d'URL. Or `DEC-0041` demande - a bon
  droit, et c'est la lecon de `SYN-11` - que `BPM-1` soit produite *"aux trois etats d'echec [...] pas
  seulement a l'etat de repos"*. **Le script est structurellement hors sujet pour trois quarts de ce
  qui est demande.**
- **La methode qui s'applique est celle de `nadia-prof` sur `SYN-5` et `SYN-11`** : jouer au clavier
  dans un navigateur reel, a `scrollY = 0` verifie, et relever les positions. C'est elle qui a produit
  les chiffres au centieme de pixel (bandeau a 4,19 px, verdict a 13,19 px), pas un script du depot.
- **Ce que cela ne change pas** : la levee du transfert de `DEC-0039` reste fondee. Le motif de ce
  transfert etait une **capacite** (*"aucun outil du depot ne pilote un navigateur reel"*), et cette
  capacite existe desormais chez `nadia-prof` - elle l'a exercee trois fois en deux jours. Ce que je
  corrige est la phrase qui nomme l'outil, pas la decision.

### 1.3 Le cout reel de `BPM-1`, chiffre avec sa methode

`DEC-0041` chiffre le cout comme *"une entree de catalogue (triviale) plus une mesure de rendu de
`nadia-prof`, deja routee"*. La premiere moitie est juste. **La seconde est sous-estimee, et c'est le
seul point de cette section qui devrait changer une decision de planning.**

La derniere campagne comparable de `nadia-prof` : `SYN-11`, **9 parties, 360 lectures**, sur **16
items**, trois etats d'echec. `BPM-1` telle que `DEC-0041` la demande porte sur **24 items**, trois
etats d'echec chacun, au pire tirage (`SYN-10` : le comportement face au pli est une propriete du
couple item-tirage, pas de l'item). **C'est une campagne d'un ordre superieur a celle qu'elle vient de
finir**, et `DEC-0041` la met en concurrence, sur la meme capacite, avec la verification du correctif
de `SYN-11` et de `S17-C` - un risque que `DEC-0041` nomme lui-meme en section 5 a propos de `M4`,
puis ne s'applique pas a `BPM-1`.

**Deux faits declaratifs qui bornent cette campagne vers le bas, et que je donne sans en tirer de
conclusion de rendu** (le rendu est a `nadia-prof`, `ADR-0025`) :

1. **Les 24 items de `cm1-homophones` ont tous exactement deux propositions** (compte sur le fichier :
   `1 + len(distracteurs)` vaut 2 sur les 24). `SYN-10` coute donc **2 arrangements par item**, la ou
   trois propositions en coutent 6. Le "pire tirage" est ici presque gratuit.
2. **Le total de caracteres des deux propositions ne depasse jamais 7** (pire cas : `sont` + `son`).
   Le mecanisme que `nadia-prof` a mesure pour `SYN-11` est une **seconde rangee de propositions a
   90 px** ; sur ce paquet, une seconde rangee exigerait que deux etiquettes totalisant au plus sept
   caracteres demandent plus que les 500 px offerts. **Je ne conclus pas qu'elle n'arrivera pas** - la
   largeur intrinseque d'un texte n'est pas une propriete de ce depot, c'est exactement la question de
   police que je laisse entiere - **je dis ce qu'il faudrait pour qu'elle arrive**, et c'est a
   `nadia-prof` de mesurer.
3. En sens inverse, un fait qui **augmente** la campagne : la revelation est atteignable sur un paquet
   a deux propositions. `ESSAIS_AVANT_REVELATION = 3` (`BanditManchotSession`) et une proposition deja
   essayee **reste cliquable** (`manche.html`, M9) : le troisieme echec s'obtient en pressant trois
   fois la meme mauvaise reponse. Les trois etats d'echec sont donc tous les trois **reellement
   atteignables** sur ce paquet, et aucun ne peut etre saute.

### 1.4 Verdict critere par critere sur `S17-4`

| Critere de la story | Verdict |
|---|---|
| `BPM-1` : propositions entierement visibles au-dessus du pli sur les 24 items, jamais un critere de caracteres, jamais un echantillon | **A REECRIRE, sur un point factuel** : le critere dit *"a 360 x 640 avec `outils/mesurer-rendu.sh`"*. Cette reference n'existe plus (`DEC-0040` : 744 x 551) et cet outil ne peut pas atteindre les etats d'echec (1.2). Le critere doit nommer la reference en vigueur et la methode de `nadia-prof`. **Le fond du critere ne change pas** et je n'y touche pas : c'est sa reference et son outil qui ont vieilli. La reference est a `camille-po`, la methode a `nadia-prof` ; je signale l'ecart, je ne le comble pas. |
| `BPM-1` etendue aux trois etats d'echec (`DEC-0041`) | **JUSTE, et non chiffre** : c'est la lecon de `SYN-11` correctement appliquee. Voir 1.3 pour son cout. Non encodable en test : c'est du rendu (`ADR-0025`, `ADR-0028`). |
| `BPM-2` : la mauvaise proposition ne devient jamais grammaticalement acceptable | **PORTE DE `nadia-prof`, deja tenue.** Non encodable : aucun automate de ce depot ne juge l'acceptabilite grammaticale d'une phrase, et un dictionnaire est refuse par `ADR-0039` et `ADR-0049`. |
| `BPM-3` : au moins 24 items | **DEJA ENCODE et vert** : 24 items comptes sur le fichier, et `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest` le tient des que l'entree existe. |
| indices corriges (24 sur 24, note du 2026-08-19) | **DEJA ENCODE et vert** : `PaquetsFrancaisS04Test`, et la reserve de `camille-po` est fermee (0 indice sur 24, 2026-08-21). Rien a refaire. |
| `BPM-4` : rejugement comme un contenu neuf, barre en six points | **PORTE DE `nadia-prof`**, integralement. Aucun test ne la remplace et aucun ne doit pretendre le faire. |
| Reserve non rouverte : deux propositions par item | **VERIFIEE sur l'arbre** : 2 sur les 24, inchange. Le raccourcissement de s17 n'a reduit aucun nombre de propositions. |

**Faisabilite de `S17-4` : faisable.** Une entree de catalogue, une edition d'inventaire, un
commentaire faux a corriger, et une campagne de mesure a la bonne taille. **Le seul risque de
planning est la capacite de `nadia-prof`**, pas le code.

---

## 2. `S17-3` - `SYN-11`, et le mecanisme que `camille-po` me route

### 2.1 Ce que `SYN-11` demande, et ce qu'un test peut en porter

`SYN-11` est une propriete de **rendu** : *"le bas du texte du verdict est au-dessus de 551 px a
`scrollY = 0`"*. Elle n'est pas encodable, pour la raison qu'`ADR-0025` et `ADR-0028` ont deja
tranchee deux fois : aucun moteur de rendu ne vit dans ce depot, et en faire entrer un pour cette
propriete serait la dependance de test lourde que j'ai deja ecartee. **`SYN-11` reste garde par
`nadia-prof` seule**, et son critere est bon tel qu'il est ecrit : il nomme l'objet (bandeau `.signal`
et texte du verdict), l'etat (les trois etats d'echec, revelation comprise), la reference, le pire
tirage, et sa verification de fermeture. Je ne le reecris pas.

**Ce qu'un test peut porter, et que ce lot livre** : l'**obligation de remesure**. Ce que `nadia-prof`
mesure est une comparaison entre deux largeurs. Depuis ce lot, les deux membres de cette comparaison
sont epingles par une egalite, et **aucun ne peut changer sans qu'un automate le dise** - ce qui rend
impossible qu'un correctif rende sa mesure caduque en silence. C'est tout ce qu'un garde-fou peut
faire ici, et c'est plus que ce qui existait hier.

### 2.2 Ce que ma propre regle autorise et interdit, puisqu'un correctif devra passer devant elle

C'est la question qui m'est posee, et je la traite terme par terme sur la table d'`ADR-0054`
(25 lignes) et celle d'`ADR-0055` (34 lignes).

**Ce qu'elle interdit vraiment - un seul mecanisme, et c'est le plus tentant.** Retirer `container`
de `main#jeu` pour laisser la zone de jeu prendre toute la largeur (728 px a 744 px de viewport, au
lieu de 500 px de rangee) : **ce geste ne se rattrape pas en mettant la table a jour.** La derivation
d'`ADR-0054` exige **une et une seule** largeur gouvernee sur la chaine d'une rangee ; zero est un
echec d'assertion, pas une ligne de table qui change. Le seul chemin vers le vert serait de restaurer
un element gouvernant ou d'amender l'`ADR`. `EnTeteEtMenuTest` le refuse deja par ailleurs, depuis
s06, pour le centrage. **Ce mecanisme est donc structurellement ferme, et il l'est deliberement** :
c'est celui qui rendrait caducs les seize chiffres du tableau de `nadia-prof` d'un seul geste.

**Ce qu'elle autorise, en le rendant bruyant.** Tout le reste. Trois familles, avec ce que chacune
coute :

1. **Elargir le plafond de la zone de jeu par une declaration maison.** Techniquement disponible :
   `recreo.css` est charge **apres** `bootstrap.min.css` (verifie sur `accueil.html`, lignes 11 et
   12), et `.zone-jeu` a la meme specificite que `.container` ; une declaration maison sur le meme
   element l'emporte donc par l'ordre des feuilles. **Effet sur ma regle** : une ligne de plus dans la
   table d'`ADR-0054`, donc rouge, donc mise a jour dans le meme commit, donc remesure due. **C'est le
   comportement voulu.** **Consequence de mise en page a nommer, qui n'est pas de mon ressort** : une
   valeur fixe aplatit l'escalier au-dela de 768 px (les marches 720, 960, 1140, 1320 deviennent
   mortes) sauf si la declaration est elle-meme nichee sous une requete de media - `ADR-0054` voit les
   deux formes, c'est `ines-design` qui choisit et `nadia-prof` qui juge.
2. **Reduire les termes lateraux de la chaine** : les 24 px de rembourrage de `.container` et les
   16 px de bordure de `.cabinet` (les deux termes que l'arithmetique d'`ADR-0054` nomme). **Effet sur
   ma regle** : lignes modifiees, rouge, meme canal. **Mais l'arithmetique ne suffit pas** : 40 px
   recuperes au mieux, pour un pire deficit de 38 px mesure par `nadia-prof`, soit **2 px de marge**.
   Et la bordure de `.cabinet` est un objet arbitre par `nadia-prof` (S8-1, condition C1 : *"un seul
   objet ferme sur ses quatre cotes"*), donc pas une variable de mise en page libre.
3. **Reduire la largeur demandee par les colonnes** : le rembourrage lateral d'un bouton de
   proposition (1,25 rem aujourd'hui, soit 96 px sur trois colonnes si on le ramene a 0,25 rem), sa
   base flexible, sa largeur minimale. **Effet sur ma regle avant ce lot : aucun. C'est exactement le
   trou que `ADR-0055` ferme dans ce commit** - jusqu'a hier, ce mecanisme-la etait le moins cher et
   le plus muet des trois. Depuis ce commit, il rougit comme les deux autres. **Deux voisins de cette
   famille sont deja fermes par un acquis pedagogique** et je ne les rouvre pas : l'ecart entre deux
   boutons (plancher de 1 rem, `ADR-0026`) et la taille de police d'une proposition (plancher de
   2 rem, exception unique a 1,875 rem).

**Le fait de contrainte qu'il faut garder en tete** : `camille-po` a fixe la reference a 744 px **en
refusant deliberement la marche des 768**. Aucun mecanisme ne peut donc compter sur cette marche, et
je le dis parce que la tentation existe : l'escalier tiers rend 680 px de rangee des 768 px, et
`nadia-prof` a mesure que les seize items y tiennent sur une rangee. **Cette marche n'est pas un
mecanisme, c'est une reference que `camille-po` a ecartee.**

### 2.3 Mon avis de mecanisme, borne a ce qui est de mon ressort

Je ne choisis pas la mise en page - c'est `ines-design`, sous le veto de `nadia-prof`. Ce que
j'apporte est un critere de **robustesse du correctif**, et il est chiffre :

- Le deficit vertical mesure est de **13,19 px** (bas du texte du verdict sous le pli). Un correctif
  qui recupere 16 px laisse **2,81 px** de marge.
- **2,81 px est exactement le chiffre que `nadia-prof` a du retirer** : *"l'item pilote TIENT a
  2,81 px"* etait vrai du tirage qu'elle avait sous les yeux et **faux de l'item**, qui etait 87 px
  sous le pli sur un autre tirage (`FICHE-bandit-manchot-synonymes-cm1.md`, correction du 2026-08-21).
- Et `nadia-prof` borne elle-meme son incertitude : les deficits de 14 a 38 px sur une rangee de
  500 px sont *"du meme ordre que l'ecart de largeur entre deux polices systeme"*, soit **3 a 7 %** -
  c'est-a-dire 15 a 35 px sur cette rangee.

**Conclusion de mecanisme, en une phrase** : un correctif dont la marge est inferieure a
l'incertitude que `nadia-prof` a nommee n'est pas un correctif, c'est un tirage au sort qui donnera un
faux vert la premiere fois qu'on changera de poste de mesure. **Le levier discret - celui qui change
le nombre de rangees, 90 px d'un coup - est structurellement plus robuste que le levier continu, qui
grappille des pixels sous le seuil de bruit.** C'est un avis de mecanisme, pas une decision de mise en
page.

**Un mecanisme que je nomme pour qu'il ne soit pas propose par inadvertance** : deplacer le bloc de
retour **au-dessus** du cabinet resoudrait `SYN-11` geometriquement et **contredit une condition
existante de `nadia-prof`** - `manche.html` porte, depuis s10, *"le seul emplacement ou le jeu parle
de la reponse de l'enfant, sous le meuble, hors du cadre du cabinet - jamais au-dessus (M1, M4)"*. Ce
n'est pas moi qui l'interdis : c'est une porte pedagogique deja franchie dans l'autre sens, et la
rouvrir demande `nadia-prof`, pas un lot de mise en page.

### 2.4 Verdict critere par critere sur `S17-3`

| Critere | Verdict |
|---|---|
| `SYN-11` (bloquante) | **BON TEL QU'ECRIT, non encodable, porte de `nadia-prof`.** Ce qui devient encodable et l'est depuis ce commit : l'obligation de remesure des que l'un des deux membres de la comparaison change (`ADR-0054` + `ADR-0055`). |
| `SYN-12` (indice sous le pli, non bloquante) | **A LAISSER ENTIEREMENT A `nadia-prof`, et a ne pas transformer en critere de lot.** Meme nature que `SYN-11`, meme impossibilite d'encodage. Son classement non bloquant est son jugement, et l'argument qu'elle donne (*"un verdict visible signale a l'enfant qu'il y a plus bas"*) **depend de la fermeture de `SYN-11`** : si `SYN-11` se ferme par un mecanisme qui ne remonte pas l'indice, `SYN-12` reste ouverte avec son argument intact. Rien a ecrire, rien a encoder. |
| `SYN-13` (revelation par elimination visuelle, observation) | **A REECRIRE, ou plutot a ne pas ecrire comme un critere de lot.** Telle qu'elle est formulee, elle *"disparaitra d'elle-meme si `SYN-11` est fermee par la largeur"* - c'est-a-dire qu'elle est **conditionnee au mecanisme retenu**, alors qu'un critere d'acceptation ne doit pas l'etre. En critere de lot, elle serait tenue ou non selon le levier choisi, ce qui la rend impossible a verifier avant d'avoir choisi. **Ma proposition** : la laisser au statut d'observation datee dans la fiche, et la reprononcer - ou non - apres la remesure, ce qui est exactement ce que `nadia-prof` a ecrit en la nommant. |
| `S17-C` se reproduit sur `cm1-synonymes` | **Exact, et sans consequence de perimetre** : c'est le meme defaut, une seule fois a corriger. Voir section 4. |

**Faisabilite de `S17-3` : faisable moyennant un `ADR`, ouvert ici.** Aucun blocage technique. Le
choix du mecanisme est devant `ines-design` et `nadia-prof`, avec les trois familles chiffrees en 2.2
et le critere de robustesse de 2.3.

---

## 3. `ADR-0055` - ce que j'ouvre, et pourquoi maintenant

**Fichier** :
`squad/decisions/adr/ADR-0055-la-largeur-demandee-par-une-rangee-est-epinglee-un-plancher-ne-garde-qu-une-moitie-de-comparaison.md`
**Regle** :
`adapters/adapter-web/src/test/java/com/recreo/adapter/web/LaLargeurDemandeeParUneRangeeEstEpingleeTest.java`
(huit tests, meme commit).

**Le defaut de mon propre lot de la veille, en trois lignes.** Le repli d'une rangee est l'issue d'une
comparaison entre la largeur offerte et la largeur demandee. `ADR-0054` a mis une egalite sur la
premiere et laisse la seconde gardee par **deux planchers**. Un plancher interdit de descendre ; ce
qui defait la mesure de `nadia-prof` est de **monter**. J'avais ecarte le cliquet a plancher dans
`ADR-0054` en expliquant pourquoi, puis laisse l'autre membre garde par cette forme-la.

**Ce que la regle interdirait reellement a quelqu'un qui essaierait de mal faire** - la question que
je me pose a chaque regle, et la reponse est mesuree, pas supposee :

| Geste | Avant ce lot | Depuis ce lot |
|---|---|---|
| Rembourrage lateral d'un bouton de proposition ramene a zero (96 px rendus sur trois colonnes, plus que le pire deficit de 38 px) | **aucun test ne rougit** | rouge |
| Ecart entre deux boutons porte de 1 a 2 rem | le plancher d'`ADR-0026` reste **vert** (la morsure le constate sur la feuille mutee avant d'exiger le rouge) | rouge |
| Police d'une proposition portee de 2 a 2,5 rem | le plancher de 2 rem reste **vert** | rouge |
| Base flexible d'une colonne portee a `1 1 100%` (repli certain quel que soit le contenu) | **aucun test ne rougit** | rouge |
| Rembourrage de `.btn` change par une mise a jour de bibliotheque, empreinte recalculee | l'empreinte d'`ADR-0014` redevient verte et **ne dit rien de la consequence** | rouge |

**Le motif de l'ecrire maintenant plutot que de la nommer en dette** : son declencheur est realise
**ce sprint**. Le levier le moins cher du correctif de `SYN-11` est precisement un terme de colonne.
Une regle ecrite apres le geste qu'elle devait rendre visible documente une histoire ; elle n'empeche
rien.

**Ce que cet `ADR` ne fait pas** : il ne fixe aucune largeur, il ne tranche pas la question de la
police, il ne retire ni n'affaiblit les deux planchers - ils gardent un acquis pedagogique que cette
regle ne garde pas - et il ne change **aucun octet de ce que voit un enfant**. Ses limites, ses
mauvaises consequences et ses trois dettes avec declencheurs sont dans l'`ADR`, pas paraphrasees ici.

---

## 4. `S17-C` - le mecanisme, et un motif de report a corriger

### 4.1 Le motif qui l'avait differee est faux sur un point verifiable

`docs/backlog/backlog.md` et `DEC-0039` different `S17-C` en partie sur ceci :
*"`RecreoJsDeplaceLeFocusTest` ne modelise aujourd'hui aucun attribut `tabindex`"*.

**C'est faux, et verifiable en une lecture.** Le faux DOM de ce test porte une table d'attributs, et
l'un de ses tests **assertionne deja `tabindex`** :
`assertThat(enonce.getAttribute("tabindex")).isEqualTo("-1")`. Ce que le faux DOM ne modelise pas,
c'est autre chose et cela change tout le mecanisme : **la focalisabilite native d'un element**. Il n'a
pas de nom de balise, donc il ne sait pas qu'un `<button>` est focalisable et qu'un `<p>` ne l'est
pas. Le motif du report etait donc juste dans sa conclusion et faux dans sa cause - et la cause est
ce qui decide du correctif.

### 4.2 Le piege que le correctif evident ne verrait pas

La cause du defaut, telle que `nadia-prof` l'a lue : `recreo.js` pose `tabindex="-1"` sur sa cible
**meme quand cette cible est deja focalisable**. Le correctif evident serait donc *"ne pas poser
l'attribut dans la branche qui designe un bouton"*.

**Ce correctif casserait l'ecran de confirmation.** Verifie sur les gabarits : la branche
`[data-focus-cible="true"]` designe un `<button class="btn proposition">` dans `fragments/manche.html`
**et** un `<p class="question-confirmation">` dans `fragments/confirmation.html`. Le second n'est pas
focalisable nativement ; sans attribut, `focus()` ne fait rien, et le point de focus de l'ecran de
confirmation - celui qu'`ADR-0040` a construit et dont la clause 3 de
`AucunArretDeTabulationNEstDestructeurTest` garde le caractere non destructeur - disparait en
silence. **Le predicat correct n'est pas la branche du script, c'est la focalisabilite de la cible.**

Recensement complet des cibles du script dans les gabarits servis : `p.enonce` (manche),
`button.proposition` (manche), `p.question-confirmation` (confirmation), `div.signal` (retour, par la
branche de repli). **Trois non focalisables, une focalisable.**

### 4.3 Deux mecanismes, et celui que je recommande

**Mecanisme A - le script decide.** `recreo.js` ne pose l'attribut que si la cible n'est pas deja
focalisable (`if (pointDeFocus.tabIndex < 0)`, la propriete que tout navigateur rend a 0 sur un bouton
et a -1 sur un paragraphe sans attribut). **Cout de garde-fou** : le faux DOM doit modeliser cette
propriete, et sa valeur serait **ecrite par moi dans une fixture** - la lecon de s05, celle dont j'ai
paye le prix en s05 et encore une fois avant-hier. Et la clause 1 de `S17-C` (l'ordre de tabulation
reel rencontre le bouton nomme) resterait invisible a tout automate, parce que le defaut naitrait
**apres** le rendu du serveur, dans un materiau que rien ne lit.

**Mecanisme B - le serveur declare.** Les trois cibles non focalisables portent `tabindex="-1"` dans
leur gabarit ; le script se reduit a `pointDeFocus.focus()` et ne pose plus aucun attribut.
**C'est celui que je recommande**, sur quatre motifs dont trois sont des faits du depot :

1. **C'est la regle que ce depot s'est deja donnee**, ecrite dans `manche.html` depuis s10 : *"le
   serveur declare ou doit aller le focus apres cet echange, jamais un script qui le devine"*. Le
   mecanisme A fait deviner au script une propriete de plus.
2. **Il fait reculer la dette 4 de `CLAUDE.md`** au lieu de l'agrandir : *"le comportement des
   mecaniques loge dans le JavaScript client, que rien ne testait"*. Le mecanisme A ajoute une branche
   a ce fichier ; le mecanisme B lui retire sa seule ecriture de DOM. Le script passe de **seize lignes
   dont une mutation d'attribut a quinze lignes qui ne mutent rien** (compte : `wc -l` sur
   `static/js/recreo.js`).
3. **Il deplace la clause 1 dans un materiau qu'un garde-fou existant lit deja.**
   `AucunArretDeTabulationNEstDestructeurTest` calcule l'ordre de tabulation du **document assemble**,
   avec la semantique de `tabindex` (negatif exclut, positifs d'abord, masquage de la feuille maison
   pris en compte) sur un corpus d'ecrans qui comprend la revelation. Aujourd'hui ce calcul est
   **juste et sans valeur pour `S17-C`** : le document servi ne porte pas l'attribut que le script
   ajoutera apres. Sous le mecanisme B, le document servi **est** celui que le navigateur a, et la
   clause 1 devient derivable.
4. **La clause 3 de la story disparait au lieu d'etre testee** : si le script ne pose plus aucun
   attribut, *"il ne lui ajoute pas `tabindex="-1"`"* devient une propriete textuelle du script, du
   ressort de `FrontieresDuJavaScriptTest`, qui lit deja ce fichier.

**La regle que j'ecrirai avec le correctif** (derivee, sur le materiau reel, jamais sur une liste de
noms) : pour chaque point de focus declare par le document assemble de chaque ecran du corpus, la
cible est **soit** un element nativement focalisable et alors elle ne porte aucun `tabindex`, **soit**
un element qui ne l'est pas et alors elle porte `tabindex="-1"`. Zero point de focus sur un ecran a
partie en cours est un echec, une troisieme situation est un echec. **Ce qu'elle interdirait
reellement** : un gabarit qui designerait comme point de focus un element que `focus()` ne peut pas
atteindre - c'est-a-dire un point de focus muet, exactement le defaut que le mecanisme A introduirait
sur l'ecran de confirmation.

### 4.4 Ce qui reste hors de portee d'un automate, et ne doit pas etre annonce comme couvert

**La clause 1 telle que `nadia-prof` l'a ecrite** - *"l'ordre de tabulation reel, mesure au clavier
dans un vrai navigateur, rencontre effectivement le bouton nomme"* - reste sa verification, comme la
clause 1 de `M13`. Ce que le mecanisme B ajoute est que la **cause declarative** est desormais gardee ;
l'effet reste a elle. C'est la meme frontiere que partout ailleurs dans ce depot, et je ne pretends
pas la deplacer.

### 4.5 `ADR` du, et pourquoi il n'est pas ici

Le choix entre A et B est une decision d'architecture : elle deplace une responsabilite du script vers
le gabarit. Elle merite un `ADR`, et cet `ADR` **doit porter sa regle dans le meme commit**. Or cette
regle ne peut pas etre verte avant que les trois attributs existent dans les gabarits - c'est-a-dire
avant le correctif, qui est le lot d'un developpeur affecte par `sofia-sm`, pas le mien.

**Engagement date, avec son declencheur** : j'ecris cet `ADR` et sa regle dans le lot du correctif de
`S17-C`, dans le meme commit que les trois attributs. **Si `S17-C` n'est pas gelee au perimetre de
s18**, cette section reste l'instruction du mecanisme et la dette reste ouverte, nommee ici, avec le
meme declencheur - et je ne l'aurai pas presentee comme fermee.

**Faisabilite de `S17-C` : faisable en l'etat.** Trois attributs de gabarit, une ligne de script
retiree, une clause derivee neuve. Le cout que `DEC-0039` disait *"non cerne"* est cerne : il est
petit.

---

## 5. La migration `ADR-0049` hors perimetre - mon avis, puisqu'il est demande

`camille-po` ne l'a pas mise au perimetre. **Je pense que c'est le bon arbitrage, et pas parce que la
story serait mauvaise - c'est la mieux specifiee du depot, et je l'ai instruite entierement.**

**Ce qu'elle vaut** : 13 paquets, 3 chaines de descripteur supplementaires, 2 entrees d'inventaire,
**un seul commit** (tout etat intermediaire est rouge par construction, propriete de la regle
d'unicite elle-meme), **zero ligne de code de production** (`grep -rn "\.matiere()"` sur `src/main` :
aucune occurrence), **aucun garde-fou neuf du**, deux pieges nommes a l'avance. Elle est prete a etre
executee par n'importe qui.

**Pourquoi elle ne devrait pas entrer a s18, malgre la directive "le moins de dette"** - et l'argument
est une propriete de la dette, pas une preference d'ordonnancement :

1. **Ce n'est pas une dette qui degrade quoi que ce soit aujourd'hui.** Aucun gabarit ne rend
   `matiere`, `domaine` ni `competence` a un enfant (verifie par moi en s17, inchange sur cet arbre).
   La dette est un **ecart de graphie dans une donnee non rendue**. `SYN-11` est un enfant qui ne voit
   pas la reponse qu'on lui donne. Les deux ne sont pas dans la meme famille, et faire primer la
   seconde n'est pas un arbitrage de confort.
2. **Son cout croit d'un fichier par paquet neuf, et s18 n'ouvre aucun paquet neuf.** C'est ecrit dans
   la story : *"chaque paquet neuf ecrit en ASCII avant que cette story ne soit executee agrandit ce
   perimetre"*. `DEC-0041` ferme deliberement le contenu neuf. **Attendre un sprint ne coute donc
   exactement rien cette fois-ci**, ce qui n'etait pas vrai en s17 et ne le sera plus au sprint
   suivant si `S17-5` ouvre les antonymes. **C'est le seul sprint ou la reporter est gratuit.**
3. **Elle ne consomme pas la capacite qui manque.** Elle ne demande **aucune** mesure de
   `nadia-prof` : la story dit explicitement qu'elle ne repasse pas devant elle, seulement devant
   `relecteur-pedagogique`. Or la ressource critique de s18 est exactement la capacite de mesure de
   `nadia-prof` (section 1.3). **Elle serait donc le meilleur candidat a un ajout tardif** si un
   developpeur se libere pendant que `nadia-prof` mesure - c'est ce que je verse a `sofia-sm` pour le
   gel, et c'est un avis d'ordonnancement, pas une demande de perimetre.

**Ce que je ne dis pas** : je ne demande pas qu'elle entre, et je ne conteste pas la decision de
`camille-po`. Je nomme la fenetre ou son cout est nul, pour qu'elle ne soit pas reportee une
quatrieme fois sans que quiconque ait remarque que la fenetre s'est refermee.

---

## 6. Mes propres dettes - ce que ce sprint ferme et ce qu'il ne ferme pas

| Dette | Etat apres ce lot |
|---|---|
| **Prefixe d'ancetre d'une regle maison non verifie** (`ADR-0046` amendement 3, partagee par `ADR-0053`) | **Ouverte, declencheur inchange, et non aggravee.** Verifie plutot que suppose : la regle d'`ADR-0055` **ne partage pas** cette dette, parce qu'elle n'infere aucune specificite - elle demande au moteur de selection quels elements du document assemble un selecteur designe, ce qui evalue le prefixe d'ancetre par construction (`.entete-contenu .lien-accueil` est retrouve sur le vrai lien). Le declencheur (*le premier lot qui ajoute un ancetre a une regle d'**etat** dont le pressable peut etre rendu hors de cet ancetre*) **n'est pas realise par le correctif attendu de `SYN-11`**, qui est une regle de geometrie, pas d'etat. **Ne devrait pas etre ouverte a s18** : deux garde-fous la portent, aucun des deux n'a d'instance realisee, et la fermer demande de decider comment comparer deux specificites conditionnelles - une decision sans objet a trancher aujourd'hui. |
| **Regles tierces sous `@media` invisibles a la derivation de specificite** (`ADR-0046`, clause) | **Ouverte pour la clause, fermee pour la propriete de largeur**, comme `ADR-0054` l'a etabli avant-hier : la cause est realisee, la clause n'a **aucune** instance (zero pseudo-classe d'etat dans les 109 blocs `@media` de la feuille tierce servie). Declencheur reformule : *le premier etat interactif tiers declare sous une regle-`@`, ou la premiere mise a jour de bibliotheque qui en introduit un*. **Ne devrait pas etre ouverte a s18** : sans instance, tout travail dessus serait une abstraction pour un usage imagine. |
| **Variables tierces non resolues numeriquement** (`ADR-0054`, heritee par `ADR-0055`) | **Ouverte, declencheur verifie non realise** : `grep -n "\-\-bs-" static/css/recreo.css` ne rend aucune occurrence, la feuille maison ne redefinit aucune variable tierce. Desormais partagee par deux garde-fous, ce qui augmente son cout sans changer sa nature. |
| **Cinq proprietes de largeur de texte hors de la table d'`ADR-0055`** (`letter-spacing`, `word-spacing`, `font-weight`, `font-family`, `white-space`) | **Neuve, nommee dans l'`ADR` avec son declencheur** : la premiere declaration de l'une d'elles sur une colonne d'une rangee. Aucune n'existe aujourd'hui, et `font-family` appartient a la question de police que je laisse entiere. |
| **Le nombre de colonnes d'une rangee n'est pas epingle** | **Neuve, assumee et motivee** dans l'`ADR` : l'epingler epinglerait le catalogue de test. |
| **`ADR-0055` lui-meme** | **Ferme la moitie manquante d'`ADR-0054`.** C'est la dette que ce sprint devrait fermer de ma production, et elle est fermee dans ce commit plutot que promise. |

**Ce que ce sprint ne devrait pas me faire faire** : la question de la police. Elle reste ce que
`ADR-0054` en a dit - un **perimetre de mesure**, qui appartient a `camille-po` et a `nadia-prof`. Je
ne l'ecris ni comme regle, ni comme dette technique, et `ADR-0055` ne l'entame pas : il epingle des
declarations dont la largeur demandee est la somme, jamais la largeur d'un mot dans une police que ce
depot ne contient pas.

---

## 7. Porte technique, avec sa methode

- **Commande** : `bash outils/mvn-jdk21.sh -B clean verify` sur la racine du worktree. **BUILD
  SUCCESS**, six modules.
- **612 tests, 0 echec.** **Methode du chiffre** : somme des six totaux par module que Surefire et
  Failsafe impriment sur la racine apres `clean` (48 + 13 + 312 + 144 + 89 + 6), tests d'integration
  compris. C'est la meme methode et le meme perimetre que les **604** d'`ADR-0054`
  (48 + 13 + **304** + 144 + 89 + 6) : l'ecart de **8** est entierement le fichier de test ajoute par
  ce lot, et `adapter-web` est le seul module dont le total bouge. Le comptage par rapports texte de
  classe rend un chiffre plus bas, pour la cause etablie en `ADR-0054` (quatre classes executees par
  deux moteurs JUnit, un rapport texte par classe) ; le chiffre a citer est celui du reacteur.
- **Mesure partielle du lot**, `-am` porte parce qu'elle est partielle
  (`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test -Dtest='LaLargeurDemandeeParUneRangeeEstEpingleeTest,LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest'`)
  : **18 tests, 0 echec** - 8 pour la regle neuve, 10 pour celle d'`ADR-0054`, dont le verdict est
  inchange apres l'extraction des methodes que les deux partagent desormais.
- **Le meta-garde-fou est passe** : `LesGardeFousSontMisALEpreuveTest`, 10 tests verts, **sans
  exemption ajoutee et sans plafond de dette releve**. La regle neuve possede son jeu fautif commite
  et s'y execute en exigeant le rouge.
- **JDK** : 21.0.10, celui de la toolchain declaree. Son emplacement n'est pas ecrit ici (`ADR-0010`).

---

## 8. Ce que je route

- **`camille-po`** : les deux criteres de `S17-4` a reecrire (1.4 - la reference 360 x 640 et l'outil
  nomme), et `SYN-13` a laisser au statut d'observation plutot qu'a en faire un critere de lot (2.4).
  Le fait que `DEC-0041` sous-estime le cout de `BPM-1` (1.3), pour qu'il soit chiffre avant le gel et
  non a la cloture.
- **`ines-design`** : les trois familles de mecanisme chiffrees (2.2), ce que ma regle rend bruyant et
  le seul mecanisme qu'elle ferme, le critere de robustesse de 2.3 (une marge inferieure a
  l'incertitude nommee n'est pas une marge), et la consequence d'aplatissement de l'escalier au-dela
  de 768 px si le plafond est redeclare sans requete de media.
- **`nadia-prof`** : que `outils/mesurer-rendu.sh` ne peut pas produire `BPM-1` aux etats d'echec, et
  que la methode applicable est la sienne (1.2) ; les deux faits declaratifs qui bornent la campagne
  (24 items a deux propositions, 7 caracteres au plus pour les deux etiquettes) et le troisieme qui
  l'allonge (la revelation est atteignable en pressant trois fois la meme mauvaise reponse) ; le
  mecanisme de `S17-C` retenu et ce qui reste sa verification seule (4.4).
- **`sofia-sm`** : la capacite de mesure de `nadia-prof` est la ressource critique de ce sprint, pas
  le code (1.3) ; la migration `ADR-0049` est le meilleur candidat a un ajout tardif si un
  developpeur se libere, parce qu'elle ne demande aucune mesure (5) ; le commentaire faux de
  `TexteAccentueExposeParLePortEntrantTest` a corriger dans le lot de `S17-4` (1.1).
- **`yanis-qa`** : `ADR-0055` est le second membre de la comparaison d'`ADR-0054`, et il porte les
  memes obligations de mise a jour de table. Un correctif de `SYN-11` qui ne rougirait **ni** l'une
  **ni** l'autre est un correctif dont il faut se demander ce qu'il change reellement.
