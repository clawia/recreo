# Fiche de conformite - barre d'entree par niveau (en-tete)

- **Increment** : deux boutons d'entree par niveau (CP / CM1) dans l'en-tete de recreo, toujours
  visibles y compris sur un ecran de jeu, story `S14-1` (`docs/backlog/backlog.md`, section
  "Sprint 14").
- **Statut global au 2026-08-18** : **Done pedagogique de S15-1, S15-2 et du troisieme arbitrage
  prononce par `nadia-prof` : VALIDE SOUS RESERVE**, sur l'increment fusionne (`b109f98`), apres
  l'avoir joue. L'increment applique quatre arbitrages de l'utilisatrice, dont deux defont des
  decisions que j'avais rendues (BARRE-7 defaite, BARRE-9 annulee, le compte de BARRE-3 annule) : **ces
  arbitrages ne se rediscutent pas et je ne les rediscute pas**, je juge ce qui est livre et j'ecris ce
  qu'il coute. Il **ferme trois defauts** - le survol qui effacait tous les boutons du depot
  (`ADR-0046`, defaut vu par l'utilisatrice et que l'outillage de ce depot ne peut pas voir), ma
  reserve **S14-A**, et la seconde moitie de ma reserve **S14-B** (le chemin vers les onze jeux
  existe). **Reserve neuve S15-C** : rien ne distingue plus, a l'oeil, le bouton qui montre tout de
  ceux qui filtrent.
- **Arbitrage prealable du 2026-08-18 rendu dans le meme geste**, sur les deux demandes neuves de
  l'utilisatrice : **variante 1 d'`ines-design` (flex simple) RETENUE**, variante 2 (groupe insecable)
  **REFUSEE** pour son debordement de page entiere ; **etat actif plein contre contour ACCEPTE sous
  condition** ; **sept conditions bloquantes S15-1 a S15-7** et six refus nommes. Detail au bloc de
  validation.
- **Done pedagogique de S14-1 et de S14-2 prononce le 2026-08-17 par
  `nadia-prof` : VALIDE SOUS RESERVE**, sur l'increment corrige (`23267ea`), apres l'avoir rejoue.
  **Les neuf conditions BARRE-1 a BARRE-9 sont tenues**, remesurees une par une. Deux reserves
  nommees, non bloquantes : **S14-A**, une bande sombre vide de 16 px sur les deux ecrans exclusifs ;
  **S14-B**, aucune puce ne se distingue jamais et rien ne nomme le filtre en cours - maintenue non
  bloquante apres avoir rejoue, avec la condition future datee qui s'appliquera au second axe de
  filtre de s15. Detail au bloc de validation.
- **Le refus du 2026-08-17 (premier verdict du jour) n'est pas efface** : il reste lisible en entier
  au bloc de validation, sous le present verdict. Ses deux motifs - quatre boites pressables sur
  l'ecran de confirmation au lieu de deux, trois sur l'ecran d'erreur au lieu d'une - sont traites par
  la correction de `marc-dev`, et je les ai **remesures** plutot que constates sur la regle : la barre
  est reellement absente de l'image sur ces deux ecrans, et la geometrie de la manche est identique au
  pixel a ce qu'elle etait avant la correction.
- **Arbitrage prealable RENDU le 2026-08-17** par la meme autrice, non efface et lisible sous le
  verdict : **variante 1 d'`ines-design` (bandeau empile) RETENUE, variante 2 (defilement horizontal)
  REFUSEE**, sous **neuf conditions bloquantes BARRE-1 a BARRE-9** et avec **cinq reserves non
  bloquantes R-BARRE-1 a R-BARRE-5**
  (`DEC-0032-perimetre-sprint-14-barre-entree-niveau-arbitrage-prealable-matiere-differee.md`,
  section 1).
- **Ce que cette story rouvre, et pourquoi cette fiche existe avant tout code** : elle ajoute deux
  elements visibles et actionnables dans l'en-tete pendant un ecran de jeu, la ou l'arbitrage de
  S6-9 / `ADR-0022` (2026-08-07) n'en laisse survivre qu'un seul, masque justement pour eviter
  qu'un enfant hesite entre plusieurs elements qui ressemblent a une sortie. La demande vient de
  l'utilisatrice, qui l'assume en connaissance de cet arbitrage anterieur - ce n'est pas une
  reouverture que `camille-po` propose de son propre chef, voir `DEC-0032` pour le detail.
- **Releves de mesure** : `squad/conformite/releves-s14/`, **vingt-et-une** captures a 360 px et au pli
  de 640 px, produites par `outils/mesurer-rendu.sh 360 <url> <png> 640` sur l'application reelle -
  sept prefixees `barre-` pour l'arbitrage prealable, sept prefixees `livre-` pour le refus, sept
  prefixees `corrige-` pour le Done. Les trois series portent les memes noms d'ecran, pour que la
  comparaison se fasse image contre image.
- **Deux points routes a `nadia-prof` et traites dans le verdict** : l'exemption des libelles "CP" et
  "CM1" au garde-fou de texte accentue (`yanis-qa`) - **jugee legitime, avec sa frontiere ecrite, et le
  trou du garde-fou a fermer avant s15** ; et le residu de 11 mutations sur 121 qu'`ADR-0045` laisse
  sans automate (`theo-archi`) - **pris, sous deux clauses verifiables N-1 et N-2**.
- **Releves de mesure du 2026-08-18** : `squad/conformite/releves-s15/`, sept captures a 360 px et au
  pli de 640 px, memes noms d'ecran que les series `barre-`, `livre-` et `corrige-` de s14, pour que la
  comparaison se fasse image contre image sur quatre etats successifs du meme produit.
- **Derniere mise a jour** : 2026-08-18 (Done pedagogique de S15-1, S15-2 et du troisieme arbitrage
  prononce VALIDE SOUS RESERVE ; arbitrage prealable rendu sur les deux demandes neuves ; tous les
  verdicts anterieurs, dont le refus du 2026-08-17, sont inchanges et lisibles sous eux).

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| transverse | - | - | pas de contenu pedagogique : navigation entre les jeux du catalogue par niveau | - |

Cette story n'ouvre, ne ferme et ne rejuge aucune ligne de `squad/programme/MATRICE-couverture.md`.

## Criteres pedagogiques attendus - camille-po

- [ ] Les boutons de niveau restent visibles dans l'en-tete sur un ecran de jeu, au meme titre qu'en
  dehors d'une partie.
- [ ] Un appui sur un bouton de niveau pendant une partie en cours ouvre l'ecran de confirmation
  existant avant tout changement ; la partie n'est pas modifiee avant confirmation.
- [ ] Un enfant qui continue sa partie depuis cet ecran de confirmation la retrouve exactement telle
  qu'elle etait avant l'appui sur le bouton de niveau.
- [ ] Le texte des boutons de niveau et de l'etat filtre est integralement accentue, sans faute
  d'orthographe ni de ponctuation (`DEC-0002`, amendement du 2026-08-04).
- [ ] Un enfant de CP ne confond pas un bouton de niveau avec une sortie de jeu sur un ecran de jeu.
- [ ] Le passage par l'ecran de confirmation est percu comme un signal de consequence sur la partie,
  et non comme un obstacle arbitraire.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. Il reste vide tant qu'aucun
> arbitrage prealable n'a ete rendu et qu'aucun increment n'a ete livre et joue.

### Arbitrage prealable du 2026-08-18 (s15) - rang unique et etat actif de la puce : variante 1 retenue, sept conditions - le plus recent de cette fiche

> **Ce que cette section fait.** Elle repond aux cinq questions d'`ines-design`
> (`squad/sprints/s15-la-sortie-vit-dans-l-en-tete/proposition-ines-etat-actif-et-rang-unique.md`,
> section 3) **avant tout code**, sur deux demandes que l'utilisatrice a deja tranchees dans leur
> principe (un seul rang, une couleur sur la puce choisie). Elle ne rouvre ni l'un ni l'autre de ces
> principes. Elle n'est **pas un Done pedagogique** : celui du lot a venir sera prononce en jouant.

#### 1. Reponse a la question 1 - variante 1, et le motif est le meme que celui de mon refus de s14

**Variante 1 (flex simple) RETENUE. Variante 2 (groupe insecable) REFUSEE.**

Je suis la recommandation d'`ines-design` et je donne le motif qui, de mon cote, la rend
non-negociable : **le mode de panne de la variante 2 est celui que j'ai deja refuse le 2026-08-17.**
Sous 346 px, elle rend une partie de "CM1" **inatteignable sans un geste de defilement horizontal
qu'aucun autre ecran de cette application ne demande**. C'est mot pour mot ce que j'ai refuse en s14
sur la variante a defilement de la barre de niveaux, et pour les memes deux raisons : un enfant de CP
ne devine pas un geste que rien ne lui a appris, et un debordement de page entiere est un defaut que
ce depot traite deja comme bloquant ailleurs. **Je ne l'accepte pas ici parce que le seuil est sous le
plancher accepte : un plancher est une garantie, pas une frontiere au-dela de laquelle le produit peut
devenir inutilisable.**

Le mode de panne de la variante 1 - "CM1" change de voisin - est **visible, reversible, et ne coute
aucune fonction** : les deux puces restent entieres, lisibles et pressables. Un mauvais groupement se
lit ; un bouton coupe hors champ ne se lit pas.

**Et je note le chiffre qui va dans le meme sens, sans qu'il decide** : la variante 1 repliee a 360 px
mesure **143 px contre 151 px aujourd'hui**, soit **8 px rendus au budget du pli**. Je l'accepte comme
un bonus et non comme un argument - si elle avait coute 8 px, ma reponse serait la meme.

**Je credite en outre la correction de chiffre d'`ines-design`** : le seuil du rang unique complet est
**470 px** et non 420 px. Elle corrige une estimation qui avait ete donnee a l'utilisatrice, elle le
dit explicitement, et elle donne la methode qui l'etablit. C'est exactement la facon de faire que
j'attends, et c'est la deuxieme fois de ce sprint que je vois un chiffre corrige par son propre auteur.

#### 2. Reponse a la question 2 - le report du plein/contour est valide, mais sous une condition qui le rend sur

**Ma reponse : oui, ce report tient - a une condition, et c'est elle qui fait la difference entre une
forme sure et une forme ambigue.**

`ines-design` nomme elle-meme le risque, et elle a raison de le nommer : dans cette application,
**"plein" a toujours voulu dire "c'est ce qu'il faut presser"** (`.demarrer`, `.continuer-partie`,
`.proposition`) et "contour" a toujours voulu dire "secondaire, ou sortie". Lui faire dire, en plus,
"c'est ce qui est deja choisi" ajoute un second sens a un code visuel qui n'en avait qu'un. Or ma
regle sur ce produit est constante : **un code, un sens** - c'est pour cela que j'ai impose un seul mot
de sortie, et que j'ai refuse un second vocabulaire pour une meme destination.

**Deux faits me font accepter quand meme, et je les donne dans l'ordre de leur poids.**

1. **La couleur du plein n'est celle d'aucune action.** L'aplat des actions est
   `--recreo-accent` ; la puce active serait **blanche**. `ines-design` a d'elle-meme ecarte le
   remplissage a `--recreo-accent` parce qu'il aurait donne a la puce l'exacte apparence du bouton
   "Jouer" visible sur le meme ecran. **Je confirme cet ecart et j'en fais un refus** (voir le 6) :
   c'est le point qui empeche la confusion, et il ne doit pas se perdre.
2. **Et surtout : le seul enfant qui voit une puce pleine est celui qui vient de la presser.** C'est ma
   condition **S15-5**. Un enfant ne "tombe" jamais sur un etat plein qu'il n'a pas produit : le plein
   est alors la confirmation immediate de son propre geste, jamais une invitation a en faire un
   nouveau. C'est la meme mecanique que j'ai creditee le 2026-08-17 pour R-BARRE-3 - une reponse
   immediate a l'appui de l'enfant est le retour le plus lisible qui existe a six ans.

**Sans la condition S15-5, ma reponse serait non.** Un enfant qui arriverait devant une liste deja
filtree, avec une puce pleine qu'il n'a pas produite, aurait toutes les raisons de lire ce plein comme
"presse-moi" - et il presserait une puce qui ne changerait rien, ce qui est le defaut de PORTE-1 sous
une autre forme.

#### 3. Reponse a la question 3 - l'extinction silencieuse suffit, et elle est meme obligatoire

**Oui, l'extinction silencieuse suffit**, et je reprends les trois motifs d'`ines-design`, qui sont
justes, sans en ajouter : le changement d'ecran est deja un signal massif, l'enfant regarde la zone de
jeu et pas l'en-tete, et une extinction manquee ne lui fait tirer aucune conclusion fausse. **Rien a
animer**, et je le pose en refus (voir le 6) : je n'ouvre pas une troisieme surface animee sur ce
produit pour un detail que personne ne fixe.

**Je vais plus loin qu'elle sur un point, et c'est une condition** : l'extinction n'est pas seulement
acceptable, elle est **due**. Pendant une partie, une puce de niveau **n'est plus un filtre** : c'est
une porte, et l'appuyer ouvre l'ecran de confirmation. Une puce qui resterait pleine pendant une
partie dirait "voici ton niveau" a un enfant dont l'appui **abandonnerait sa partie**. C'est **S15-4**.

#### 4. Reponse a la question 4 - le filtre ne survit pas, ni a une partie, ni a un chargement de page

**Ce qu'un enfant attend, et c'est la question qu'on me pose** : un enfant de six ans qui arrive
devant la machine attend de voir **tous** les jeux. Il n'a aucun moyen de savoir qu'un filtre a ete
pose avant lui - par lui la veille, ou par un frere de CM1 dix minutes plus tot - et un etat qu'il n'a
pas choisi et qu'il ne peut pas expliquer est exactement ce que je refuse depuis le debut de ce
produit. Un enfant de CP qui trouverait cinq jeux de CM1 en arrivant conclurait que ses jeux ont
disparu.

**Donc : pas de persistance.** Le filtre vit le temps de la navigation qui l'a produit ; une sortie de
partie et un chargement de page repartent sur le catalogue complet. Cela **coincide** avec ce que ce
produit est deja (`DEC-0028` : rien ne survit a une session) et cela **fonde** la condition S15-5 qui
rend le plein sur : les deux reponses tiennent ensemble et je les ai voulues ensemble.

Je note pour `camille-po`, a qui la question est aussi posee : **ma reponse ne lui retire rien**. Si
elle voulait la persistance, elle devrait d'abord me faire lever S15-5, et j'ai dit au 2 pourquoi je ne
le ferais pas sans autre chose a la place.

#### 5. Reponse a la question 5 - la phrase au-dessus de la liste est due, meme si la couleur est livree

C'est ici que je ne suis pas `ines-design` : elle presente la phrase comme un **repli** si je refuse la
couleur. **Je ne refuse pas la couleur, et je demande la phrase quand meme.** Trois raisons.

1. **Les deux ne disent pas la meme chose.** La phrase dit **ce qui est montre** ; la puce pleine dit
   **quel bouton l'a produit, donc comment en changer**. Un enfant qui se demande pourquoi il y a six
   jeux a besoin de la premiere ; un enfant qui veut l'autre niveau a besoin de la seconde.
2. **La couleur seule demande une inference que je ne garantis pas a six ans** : relier l'apparence
   d'un bouton en haut de l'ecran a la longueur d'une liste en dessous. La phrase, elle, **le dit**.
   C'est le meme raisonnement que M11 sur le retour juste/faux : le signal non textuel accompagne le
   texte, il ne le remplace pas.
3. **La phrase est la seule des deux qui soit certaine d'arriver.** Et c'est le point technique qui
   decide : la phrase vit dans le fragment que la route rend deja a chaque appui, **donc elle apparait
   au moment ou l'enfant presse**. La puce, elle, vit dans l'en-tete, que `DEC-0012` et `ADR-0044`
   point 3 mettent hors de portee d'un echange qui ne remplace que la zone de jeu. **Si `theo-archi`
   refuse le mecanisme hors bande, l'etat actif n'apparaitra jamais au moment ou il servirait** - il ne
   serait vu qu'apres un chargement complet de page, c'est-a-dire jamais, puisque le seul chemin vers
   un filtre est l'appui sur une puce. C'est ma condition **S15-3** : je n'accepte pas un etat actif
   qui n'apparait pas dans la reponse a l'appui qui le cause.

**Le libelle, que je tranche parce que c'est du texte lu par un enfant : "Pour le CP" et "Pour le
CM1".** Trois mots, chacun accessible en debut de CP, et il repond exactement a la question que la
liste raccourcie pose. **J'ai ecarte la forme "Jeux pour le CP" et toute forme contenant "Les jeux"
pour une raison que je verifie sur ma propre condition** : BARRE-5 exige que le mot "Les jeux"
n'apparaisse **au plus qu'une fois par ecran**, et l'en-tete le porte deja sur le bouton de sortie. Une
phrase qui le repeterait me mettrait en contradiction avec ma propre barre, et je prefere changer le
libelle plutot que d'amender la barre apres coup.

**Consequence sur R-BARRE-3 / S14-B** : cette phrase est ce qui la ferme. Je ne la leve pas
aujourd'hui - rien n'est encore code - mais **je dis d'avance ce qui la levera** : la phrase, seule,
suffit ; la couleur, seule, ne suffit pas. Et la bascule que j'avais datee au 2026-08-17 ("des qu'un
second axe de filtre s'ajoutera") reste ecrite telle quelle : avec deux axes, la phrase devra nommer
les deux.

#### 6. Mes sept conditions bloquantes pour le lot a venir

Elles decrivent ce que l'enfant doit voir ; les moyens appartiennent a `theo-archi`.

- **S15-1. Variante 1, et rien ne deborde du document a aucune largeur.** Verification, et c'est la
  methode d'`ines-design` que je reprends parce qu'elle est meilleure que la mienne sur ce point :
  `document.documentElement.scrollWidth` compare a `clientWidth` **a 300, 320, 345, 346, 360 et
  470 px**, la valeur ecrite dans la page puis capturee par `outils/mesurer-rendu.sh <largeur> <url>
  <png>`. **Zero px de debordement partout**, y compris sous le plancher accepte. Une capture seule ne
  suffit pas ici : elle decoupe silencieusement a la largeur demandee et masque le defaut, `ines-design`
  l'a etabli et je le reprends.
- **S15-2. La phrase qui nomme le filtre est due, et elle est due meme sans l'etat actif.** "Pour le
  CP" ou "Pour le CM1", au-dessus de la liste, dans le fragment echange. **Verification : capture a
  360 px de la liste filtree, la phrase presente et lisible au-dessus du premier bouton de jeu.**
- **S15-3. Si l'etat actif est livre, il apparait dans la reponse a l'appui qui le cause.** Un etat
  actif qui n'existe qu'apres un chargement complet de page n'est pas livre, il est decoratif.
  **Verification : appuyer sur une puce depuis l'accueil et constater que cette puce a change
  d'apparence sans avoir recharge la page.**
- **S15-4. Aucune puce ne porte l'etat actif sur un ecran ou l'appuyer ouvrirait la confirmation.**
  Donc sur aucun etat de manche. **Verification : capture a 360 px d'une manche fraiche, aucune puce
  pleine a l'image.**
- **S15-5. Aucun enfant ne voit un etat actif qu'il n'a pas produit.** Le filtre ne survit ni a une
  sortie de partie, ni a un chargement de page. **Verification : charger la page apres une partie
  jouee depuis une liste filtree, et constater le catalogue complet et aucune puce pleine.**
- **S15-6. La colonne de contenu d'un ecran de jeu ne commence jamais par une porte.** C'est la seule
  forme de BARRE-3 qui survit aux arbitrages (voir le verdict ci-dessous), et **elle est tenue
  aujourd'hui** : cette condition est donc une non-regression, pas une demande neuve. **Verification :
  capture a 360 px d'une manche fraiche, la premiere bande de texte sous l'en-tete est le score.**
- **S15-7. Le lot ne coute pas plus de hauteur qu'il n'en annonce.** En-tete a **143 px au plus** a
  360 px, la valeur qu'`ines-design` a mesuree pour la variante 1. **Verification : capture a 360 px,
  bandes relevees a la colonne.** Motif : ma reserve sur le pli vient de perdre 22 px depuis s14 (voir
  le verdict), et les 8 px que cette variante rend sont les seuls qui reviennent.

#### 7. Ce que je refuse, nommement, pour que cela ne revienne pas par la fenetre

1. **La variante 2** (debordement de page entiere sous 346 px).
2. **Un etat actif porte par la couleur seule.** Et je precise la barre que `theo-archi` a eu raison de
   nommer sans la fixer : **un contour de 0,0625rem ne me convient pas**. Sa clause garantit qu'un
   second canal existe ; ce que je demande est qu'il **se voie**, et le seul que je valide aujourd'hui
   est **le plein contre le contour**. Un autre porteur non chromatique m'est proposable, il n'est pas
   pre-accepte.
3. **Le remplissage de la puce active a `--recreo-accent`.** `ines-design` l'a ecarte ; j'en fais un
   refus pour que ce ne soit pas rediscute : ce serait l'exacte apparence du bouton "Jouer" affiche sur
   le meme ecran.
4. **Toute animation ou transition sur l'allumage ou l'extinction de l'etat actif.**
5. **La persistance du filtre** entre deux sessions ou apres une sortie de partie.
6. **Un pictogramme, un disque ou une coche dans la puce** - BARRE-4 du 2026-08-17, inchangee. Le
   porteur non chromatique est l'aplat, pas un symbole de plus a apprendre.

#### 8. Ce dont je reste la seule garde sur ce lot

- **Que le plein soit lu comme "deja choisi" et non comme "presse-moi".** Aucun automate ne le mesure,
  ma condition S15-5 le rend probable, **elle ne le prouve pas**. C'est le point que je rejugerai en
  jouant, et c'est celui ou je peux encore refuser le lot livre.
- **Que "Pour le CP" soit lu et comprise** par un enfant qui commence a dechiffrer.
- **Le budget du pli et l'absence de debordement**, que je suis seule a pouvoir mesurer sur ce poste.
- **Que trois boites de meme apparence dans le meme en-tete ne se confondent pas** - voir la reserve
  S15-C du verdict ci-dessous, que ce lot est justement en position de fermer.

### Done pedagogique du 2026-08-18 (s15) sur S15-1, S15-2 et le troisieme arbitrage - VALIDE SOUS RESERVE

> **Ce que cette section fait.** Elle juge l'increment fusionne a `b109f98`, qui applique **quatre
> arbitrages successifs de l'utilisatrice**, dont deux defont des decisions que j'avais rendues. **Ces
> arbitrages ne se rediscutent pas et je ne les rediscute pas** : ils viennent de l'autorite au-dessus
> de la squad, qui a joue le produit et lu mes motifs. Ce qui m'appartient est de juger **ce qui est
> livre**, et de dire ce que cela coute a un enfant pour que ce soit ecrit. Mes refus anterieurs
> restent lisibles en dessous, non effaces.

- **Verdict** : **VALIDE SOUS RESERVE**. Done pedagogique prononce sur **S15-1**, **S15-2** et le
  troisieme arbitrage (la sortie permanente pendant une manche). **Motif, en deux phrases** :
  l'increment applique fidelement les quatre arbitrages, et il **ferme trois defauts** - le survol qui
  effacait tous les boutons du depot, ma reserve S14-A, et l'impossibilite d'atteindre le catalogue
  complet depuis une liste filtree. Ce qu'il coute a un enfant - **trois portes de meme apparence lues
  avant l'enonce** et une boite qui rend encore un ecran identique sur l'accueil non filtre - decoule
  de decisions qui ne sont pas celles de l'equipe, et je l'inscris comme **defait par arbitrage**,
  jamais comme un motif contre le lot.
- **Auteur** : nadia-prof. **Increment juge** : `round-two` a `b109f98`, worktree `s15-nadia`,
  lignee verifiee par `outils/verifier-lignee.sh`, aucun fichier de production modifie de mon fait.
- **Porte technique, verifiee par mes propres commandes** : `bash outils/mvn-jdk21.sh -B clean verify`,
  **BUILD SUCCESS, 523 tests, 0 echec** ; `bash .github/scripts/verifier-chemins.sh` en code 0.

#### 0. Comment j'ai joue, et ce que je declare degrade

Meme methode que les deux verdicts precedents, pour que les chiffres se comparent : jar reconstruit sur
le JDK de la toolchain 21, application servie sur un port a moi, parties ouvertes par requetes HTTP
reelles, etat echange obtenu en rejouant cote serveur le remplacement que htmx fait cote navigateur.
**Serveurs arretes par leur PID reel.** Sept captures dans `squad/conformite/releves-s15/`, par

```
outils/mesurer-rendu.sh 360 <url> <png> 640
```

**Les gestes que j'ai reellement faits**, parce que c'est ce qui a produit les faits du 2 : presser
"Les jeux" depuis l'accueil non filtre ; presser "Les jeux" depuis la liste filtree sur CP ; presser
"Les jeux" pendant une manche puis verifier que la partie est intacte ; presser CP puis CM1 ; ouvrir la
confirmation depuis une puce ; provoquer l'ecran d'erreur ; jouer une partie jusqu'a "C'est fini !".

**Ce que je declare degrade.**

- **Je n'ai pas survole les boutons.** La fermeture du defaut de survol (`ADR-0046`) est **lue dans la
  feuille** - les etats `:hover` et `:active` reprennent les valeurs du repos - et non constatee sous
  un pointeur : `outils/mesurer-rendu.sh` capture, il ne survole pas. C'est le seul moyen que le depot
  me donne, et je ne peux donc **pas** confirmer par la mesure le defaut que l'utilisatrice a vu ni sa
  correction. Je la credite sur la lecture de la regle, en le disant.
- **Je n'ai pas presse la touche de tabulation**, donc l'ordre des arrets reste lu dans le document.
- **Je n'ai pas joue au doigt sur un telephone**, ni observe un enfant : la question de savoir si un
  enfant de CP distingue trois boites de meme apparence reste **sans reponse mesurable**, et c'est
  precisement la reserve S15-C.
- **Un seul item echantillonne** pour le pli, le meme que les deux fois precedentes.

#### 1. Ce que l'increment ferme, et je le mets avant le reste parce que c'est le plus important

1. **Un defaut que je n'avais pas pu voir, et qui touchait tous les boutons du depot.** La feuille
   tierce declarait `:hover` sur `.btn` a une specificite superieure a celle des regles maison : sous
   le pointeur, un bouton perdait son contour et son texte. **Je n'aurais pas pu le trouver** -
   l'outil de mesure de ce depot capture une page, il ne survole rien - et c'est l'utilisatrice qui
   l'a vu en jouant. `ADR-0046` le ferme en **forme neutre**, c'est-a-dire que rien ne change sous le
   pointeur. **C'est le bon choix et je le dis** : ce produit n'a jamais eu de retour visuel transitoire
   au geste, et lui en ajouter un aurait cree un vocabulaire de plus. Je note pour la trace que **ce
   defaut a traverse quinze sprints et une CI verte**, et qu'il ne se voyait ni dans une capture ni
   dans un test - c'est le cinquieme defaut visible par un enfant qui echappe a tout ce que ce depot
   sait mesurer.
2. **Ma reserve S14-A est fermee, et je le verifie plutot que de le croire.** La bande sombre du second
   rang a disparu : mesure a la colonne x = 180, l'en-tete de l'accueil et de la manche est **une seule
   bande `#1d4b6b` de y 0 a 150**, sans aucun fond propre au rang des puces. Le residu de 16 px vides
   qui subsistait sur la confirmation et sur l'erreur ne se distingue plus de rien. **Reserve S14-A :
   LEVEE.**
3. **La seconde moitie de ma reserve R-BARRE-3 / S14-B est fermee, et par un chemin que je n'avais pas
   vu.** Je reprochais qu'"rien ne dit comment revoir les onze jeux". **Mesure en jouant : depuis la
   liste filtree sur CP, presser "Les jeux" rend les onze jeux.** La sortie d'en-tete est devenue le
   chemin de retour au catalogue complet, et elle est visible en permanence. Je n'avais pas anticipe
   que le retour du lien que j'avais fait retirer fermerait une de mes propres reserves ; **je
   l'inscris parce que c'est vrai**, et cela reduit S14-B a sa premiere moitie (rien ne dit **pourquoi**
   la liste a raccourci), que l'arbitrage ci-dessus met en condition S15-2.

#### 2. Ce que l'increment coute, et qui est defait par arbitrage et non par negligence

**Trois portes de meme apparence sont lues avant l'enonce, sur tout ecran de manche.** Capture
`s15-3-manche-360x640.png` : de haut en bas, "Les jeux" en haut a droite, puis "CP" et "CM1" a gauche,
puis le score, la consigne, l'enonce. C'est le constat que j'ai fait le 2026-08-07, redit le
2026-08-14, et dont j'ai refuse le triplement le 2026-08-17. **Il est aujourd'hui defait par deux
arbitrages, et l'un des deux est le mien** - les puces viennent de l'arbitrage prealable que j'ai rendu
en s14. Je n'en fais donc pas un motif ; je l'inscris.

**Et je dis ce que l'unification d'apparence change, en credit comme en risque, parce que ma propre
preference est ici contredite.** Mon arbitrage de s14 demandait "une bande plus sombre" pour que les
puces ne se confondent pas avec la sortie. L'arbitrage B l'annule, et **il y a un argument reel de son
cote que je dois reconnaitre** : pendant une partie, les trois boutons ont desormais **la meme
consequence exacte** - les trois ouvrent le meme ecran de confirmation, verifie en jouant les trois.
Trois boites de meme apparence pour trois gestes de meme consequence est **coherent**, et c'est plus
coherent que ce que je demandais. Sur l'accueil, les trois forment aussi une famille : "tout", "CP",
"CM1", trois facons de dire ce que la liste montre. **Le risque residuel est ailleurs** et il est reel :
rien ne distingue plus, a l'oeil, le bouton qui montre tout de ceux qui filtrent. C'est la reserve
S15-C, et le lot que je viens d'arbitrer est justement celui qui peut la fermer.

**Une boite rend encore un ecran identique a celui qui l'affiche, mais dans un seul cas sur deux.**
Verifie en jouant : depuis l'accueil **non filtre**, presser "Les jeux" rend les onze memes jeux -
c'est le defaut de PORTE-1, que `DEC-0033` avait ferme et que `DEC-0034` rouvre en connaissance de
cause. Mais depuis l'accueil **filtre**, la meme boite rend les onze jeux **alors que six etaient
affiches** : elle repond. Le defaut est donc **strictement plus etroit qu'avant s14** : il ne subsiste
que sur l'ecran d'arrivee. **BARRE-7 est defaite par arbitrage** ; je note que ce qui la motivait -
l'enfant apprend d'un voisin qu'un bouton ne repond pas - est desormais **a moitie vrai** au lieu
d'etre vrai.

**Le budget du pli recule de 9 px de plus.** Sur l'item "Décompose 305 421 en valeurs de position.",
haut du cabinet mesure a la colonne x = 180 : **y = 397** avant s14, **y = 410** apres s14, **y = 419**
aujourd'hui. L'en-tete passe de 142 a **151 px** parce que la sortie, plus haute que l'icone, gouverne
desormais la hauteur du premier rang ; la sortie retiree de la zone de contenu ne rend rien, puisqu'elle
etait **sous** les propositions. **151 px est exactement le plafond que BARRE-8 avait fixe** - la
condition est tenue, au pixel, et il n'y a plus de marge. Les 8 px que la variante 1 rendra sont donc
les seuls qui reviennent, et c'est pourquoi j'en fais la condition S15-7.

#### 3. Etat de mes conditions apres ces quatre arbitrages

| | Etat | |
|---|---|---|
| **BARRE-1** | **TENUE** | 2 boites pressables sur la confirmation (`s15-4`), en-tete a 92 px, puces et sortie masquees |
| **BARRE-2** | **TENUE** | 1 boite pressable sur l'erreur (`s15-5`) |
| **BARRE-3** | **compte ANNULE par arbitrage ; substance conservee** | voir le 4 |
| **BARRE-4** | **TENUE** | les puces ne portent que leur texte |
| **BARRE-5** | **TENUE** | "Les jeux" : accueil 1, manche 1, confirmation 1, erreur 0, fin de partie 1. Le second marqueur `ecran-porte-sa-sortie` est ce qui l'assure sur la fin de partie et sur la confirmation, et il le fait proprement |
| **BARRE-6** | **TENUE** | les trois boutons ont desormais les memes composantes de boite, donc aucun ne depasse une action |
| **BARRE-7** | **DEFAITE par arbitrage** (`DEC-0034`), residu reduit a l'accueil non filtre |
| **BARRE-8** | **TENUE, sans marge** | 151 px, exactement le plafond |
| **BARRE-9** | **ANNULEE par arbitrage** (arbitrage A) |

#### 4. BARRE-3 et la clause derivee que `theo-archi` me propose - ma reponse

**Je constate avec lui que le compte est mort, et je ne fais pas semblant de le garder.** Trois portes
avant l'enonce, dont deux que j'ai moi-meme accordees : une condition qu'on ne peut plus tenir sans
defaire un arbitrage n'est plus une condition, c'est un souhait. **Le compte de BARRE-3 est annule, et
je l'ecris ici pour que personne ne le ressorte contre un lot futur.**

**Ce que je garde, et c'est la formulation que j'avais deja corrigee le 2026-08-17** : *"la colonne de
contenu d'un ecran de jeu ne commence jamais par une porte"*. Elle est **tenue aujourd'hui** - la
colonne d'une manche commence par le score - et elle devient la condition **S15-6** du lot suivant, en
non-regression. Ce n'est pas une barre neuve : c'est le meme texte, deja ecrit, deja date.

**Sur sa clause derivee - "sur une manche fraiche, le point de focus declare est l'enonce" - ma reponse
est : je la prends, et NON, elle ne remplace pas BARRE-3.** `theo-archi` a raison sur les deux points
et je le suis exactement :

- **Je la prends** parce qu'elle garde quelque chose de reel : elle garantit que **ce que le serveur
  designe** a l'arrivee sur une manche est la question, jamais une porte. C'est une propriete que rien
  ne garde aujourd'hui et qu'un automate peut garder. **Qu'il l'ecrive.**
- **Elle ne remplace rien**, parce qu'un point de focus et un ordre de lecture ne s'adressent pas au
  meme enfant. Le focus gouverne l'enfant qui navigue au clavier ou qui ecoute ; **l'ordre de lecture
  gouverne l'oeil**, et un oeil qui descend une page rencontre les trois portes avant l'enonce quoi que
  le focus declare. **Aucune clause de focus ne protege cela**, et personne ne doit lire celle-ci comme
  si elle le faisait. Il a eu raison de refuser de la presenter comme un equivalent, et j'aurais refuse
  qu'il le fasse.

#### 5. Mes reserves, dont une neuve

- **S14-A** : **LEVEE**, mesuree (voir le 1).
- **S14-B / R-BARRE-3** : **reduite a sa premiere moitie**. Le chemin vers les onze jeux existe
  desormais ; ce qui manque est de dire **pourquoi** la liste a raccourci. **Non bloquante**, et
  l'arbitrage ci-dessus en fait la condition S15-2. Sa bascule datee du 2026-08-17 (un second axe de
  filtre) reste inchangee.
- **Reserve S15-C, neuve et datee du 2026-08-18** : **rien ne distingue plus, a l'oeil, le bouton qui
  montre tout de ceux qui filtrent.** Trois boites de meme apparence dans le meme en-tete, dont l'une
  a un role different des deux autres sur l'accueil. **Non bloquante** - la coherence des consequences
  pendant une partie compense en partie, et je n'ai aucun moyen de mesurer la confusion - mais c'est
  une reserve **neuve**, pas un durcissement de l'arbitrage B : elle nait de l'unification que cet
  arbitrage a voulue. Le lot que je viens d'arbitrer la ferme si S15-2 et S15-3 sont livrees.
- **Reserve sur le pli** : la mienne du 2026-08-17 sur les grands nombres perd **9 px de plus**, soit
  **22 px** depuis s14. Reste non bloquante et reste sur la fiche des grands nombres ; la condition
  S15-7 est ce que je fais pour la contenir.

#### 6. Ce que ce Done ne fait pas

- **Il ne rediscute aucun des quatre arbitrages de l'utilisatrice**, et il n'en approuve aucun non plus :
  il constate ce qui est livre et en nomme le cout.
- **Il ne retire aucun de mes verdicts anterieurs**, qui restent lisibles en dessous - y compris le
  refus du 2026-08-17, dont les deux motifs restent traites.
- **Il ne leve ni F11, ni la reserve J, ni la reserve O seconde moitie.**
- **Il ne prejuge pas le lot de s15** : les sept conditions de l'arbitrage ci-dessus sont a tenir, et un
  increment qui les tiendrait toutes peut encore etre refuse pour ce que seul le rendu montre.

### Done pedagogique du 2026-08-17 (s14), second verdict du jour - le refus est LEVE : S14-1 et S14-2 sont VALIDE SOUS RESERVE

> **Ce que cette section fait.** Elle **leve le refus** que j'ai prononce quelques heures plus tot sur
> cette meme fiche, apres avoir rejoue l'increment corrige par `marc-dev`. Elle **n'efface pas ce
> refus** : il reste lisible mot pour mot juste en dessous, et l'arbitrage prealable sous lui. Elle
> ouvre le Done pedagogique de **S14-1** et de **S14-2** sous **deux reserves nommees**, qui sont deux
> points de cet increment et non des reserves reportees d'ailleurs.

- **Verdict** : **VALIDE SOUS RESERVE**. Done pedagogique prononce. **Motif, en deux phrases** : les
  deux motifs de mon refus sont traites et je les ai remesures moi-meme - l'ecran de confirmation
  porte desormais **exactement deux** boites pressables et l'ecran d'erreur **exactement une**, la
  barre etant reellement invisible au rendu et non seulement declaree masquee. **Les sept autres
  conditions ne sont pas entamees**, verifie au pixel : la geometrie de la manche est **identique** a
  celle d'avant la correction, et les deux ecrans corriges gagnent 50 px au lieu d'en perdre.
- **Auteur** : nadia-prof. **Increment juge** : `round-two` a `23267ea`, worktree `s14-nadia-porte`,
  descendance verifiee par `outils/verifier-lignee.sh`, aucun fichier de production modifie de mon
  fait.
- **Porte technique, verifiee par mes propres commandes** : `bash outils/mvn-jdk21.sh -B clean verify`,
  **BUILD SUCCESS, 510 tests, 0 echec**, et `bash .github/scripts/verifier-chemins.sh` en code de
  sortie 0.
- **Ce que ce Done permet** : la story S14-1 et la story S14-2 peuvent passer a done. Aucune ligne de
  `squad/programme/MATRICE-couverture.md` ne bouge - cette story est transverse, elle n'en ouvre ni
  n'en ferme aucune.

#### 0. Comment j'ai rejoue, et ce que je declare degrade

**J'ai rejoue**, avec la meme methode et les memes gestes que pour le refus, pour que les deux series
de chiffres soient comparables : jar reconstruit sur le JDK de la toolchain 21, application servie sur
un port a moi, partie ouverte par requetes HTTP reelles, etat echange obtenu en remplacant le contenu
de `#jeu` par le fragment que le serveur rend reellement pour la route appelee - c'est-a-dire en
refaisant cote serveur ce que `hx-target="#jeu" hx-swap="innerHTML"` fait cote navigateur. Serveur et
service de fichiers **arretes par leur PID reel**.

```
outils/mesurer-rendu.sh 360 <url> <png> 640
```

Sept captures neuves dans `squad/conformite/releves-s14/`, prefixees `corrige-`, aux memes noms que
les sept `livre-` du refus pour que la comparaison se fasse image contre image.

**Ce que je declare degrade, et deux points sont nouveaux.**

- **Je n'ai pas presse la touche de tabulation.** Que `display: none` retire les puces de l'ordre des
  arrets est un comportement du navigateur et non une chose que j'ai constatee. Le piege que
  `theo-archi` avait nomme - une regle qui nommerait le conteneur laisserait les puces dans l'ordre
  de tabulation tout en les rendant invisibles - **est ferme dans la forme retenue** (la regle nomme
  la puce), mais **c'est une lecture de la regle, pas une mesure de ma part**. Je le dis parce que
  c'est exactement le genre de propriete dont j'ai deja constate qu'elle traverse une CI verte.
- **Je n'ai pas joue au doigt sur un telephone**, ni observe un enfant. La question de `camille-po` -
  un enfant de CP confond-il une puce et une sortie - **reste sans reponse mesurable**, et ce Done ne
  la tranche pas : il constate que le nombre de boites et leur place sont ceux que j'avais exiges.
- **Un seul item echantillonne** pour les grands nombres, le meme que les deux fois precedentes.

#### 1. Les deux motifs du refus sont traites, mesures et non pris sur parole

`marc-dev` annonce avoir corrige par une regle de visibilite. **J'ai regarde ce que l'enfant voit, pas
ce que la regle declare** - c'est la distinction meme que mon refus reprochait a l'increment
precedent.

| Ecran | Boites pressables avant la correction | Apres la correction | Ma condition |
|---|---|---|---|
| confirmation avant abandon (`corrige-4`) | **4** : CP, CM1, "Continuer la partie", "Les jeux" | **2** : "Continuer la partie", "Les jeux" | BARRE-1 en exigeait 2 - **TENUE** |
| erreur (`corrige-5`) | **3** : CP, CM1, "Recommencer" | **1** : "Recommencer" | BARRE-2 en exigeait 1 - **TENUE** |

**Et la correction rend de la place plutot qu'en prendre**, ce que je ne demandais pas et que je note
parce que c'est mesure. Bandes relevees a la colonne x = 180 :

- sur la manche, l'accueil et la fin de partie : en-tete **142 px**, bande des puces **66 px** -
  **exactement les valeurs d'avant la correction, au pixel** ;
- sur la confirmation et sur l'erreur : en-tete **92 px**, la bande des puces tombant de 66 px a
  **16 px**. Les deux ecrans que ma condition visait **gagnent 50 px** au lieu d'en perdre, et ils ne
  coutent plus que 16 px de plus qu'avant tout ce sprint.

**Le fait que je cherchais, et qui est le vrai objet de cette reverification.** `marc-dev` ecrit
n'avoir verifie que la coherence structurelle du couple declaratif, et que le reste me revient. Le
reste, c'est ceci : **le `:has()` est honore par le navigateur reel et les puces sont absentes de
l'image**. Elles le sont. Ce n'etait pas acquis - une regle juste peut ne pas s'appliquer - et
c'est desormais un fait date sur un rendu date.

#### 2. Rien des sept autres conditions n'est entame - verifie, parce que c'est le scenario que ce depot connait

Je l'ai verifie plutot que de le supposer : une correction de rendu qui repare deux ecrans et en abime
un troisieme est arrivee plusieurs fois ici.

| | Etat | Ce que j'ai constate |
|---|---|---|
| **BARRE-1** | **TENUE** | 2 boites pressables sur la confirmation (`corrige-4`) |
| **BARRE-2** | **TENUE** | 1 boite pressable sur l'erreur (`corrige-5`) |
| **BARRE-3** | **TENUE, intacte** | `corrige-3` : l'ordre reste score, consigne, enonce, propositions, **puis** la sortie. Et la geometrie est **identique** : sur l'item "Décompose 305 421 en valeurs de position.", le haut du cabinet est a **y = 410** avant comme apres la correction. Rien n'a bouge d'un pixel sur la manche. |
| **BARRE-4** | **TENUE** | les puces ne portent que "CP" et "CM1", aucun disque, aucun symbole |
| **BARRE-5** | **TENUE** | comptage du mot "Les jeux" sur les cinq etats, commentaires retires : accueil 0, manche 1, confirmation 1, erreur 0, fin de partie 1 |
| **BARRE-6** | **TENUE** | `entree` reste un role domine, la regle est verte sur les 510 tests |
| **BARRE-7** | **TENUE** | le lien de l'en-tete reste retire, pas masque (`DEC-0033`) |
| **BARRE-8** | **TENUE** | un seul rang de puces, en-tete **142 px** sur les trois ecrans qui les montrent, sous ma borne de 151 px ; puces **56 x 50** et **74 x 50 px**, au-dessus du plancher de 44 x 44 px ; rien de pressable n'a ete retreci |
| **BARRE-9** | **TENUE** | il n'y a toujours aucun "Les jeux" dans l'en-tete : les deux ne peuvent pas partager un rang |

**Neuf sur neuf.** Et le gain que j'avais annonce dans l'arbitrage prealable est toujours la, remesure
sur `corrige-2` : **filtree sur CP, la liste des six jeux tient entiere au-dessus du pli, avec de la
marge**, la ou le catalogue complet n'y tient plus.

#### 3. Mes deux reserves, et pourquoi ce n'est pas un VALIDE sec

- **Reserve S14-A - une bande sombre vide de 16 px sur les deux ecrans exclusifs.** La regle masque la
  **puce** et non le bandeau, ce qui est le bon choix technique et je le credite ; la consequence est
  que le bandeau reste, avec son fond et son rembourrage vertical, et **sans rien dedans**. Mesure sur
  `corrige-4` et `corrige-5` : une bande `#081c2c` de **16 px** entre le titre et le contenu. Ce n'est
  ni un piege, ni un texte, ni quelque chose de pressable - **c'est pour cela que ce n'est pas un
  refus** - mais c'est une bande de couleur qui ne veut rien dire sur les deux ecrans ou l'enfant doit
  comprendre vite. A traiter dans un sprint suivant, et le moyen n'est pas de moi.
- **Reserve S14-B - aucune puce ne se distingue jamais, et rien ne nomme le filtre en cours.** Voir le
  4 : je la maintiens **non bloquante**, apres l'avoir rejouee, et je dis exactement pourquoi et
  jusqu'a quand.

**Ces deux reserves ne bloquent pas le Done**, et `camille-po` en fait deux stories si elle le juge.

#### 4. R-BARRE-3 : je la maintiens NON BLOQUANTE, et je corrige mon propre cri d'alarme

C'est la question qu'on me demande de trancher maintenant plutot qu'a s15, et elle le merite.

**Ce que j'avais ecrit dans mon refus** : que la reserve etait *"AGGRAVEE"*, parce que la forme
d'`ines-design` que j'avais creditee portait un etat actif en plein contre contour et que l'increment
ne l'a pas (`ADR-0044` point 3 interdit a l'en-tete de porter un etat de filtre). **Le fait est exact
et je ne le retire pas. Mon alarme, elle, etait trop forte, et c'est en rejouant que je le vois.**

**Les trois faits qui me font la maintenir non bloquante.**

1. **Le retour n'est pas absent, il est immediat.** L'enfant presse "CP" et **la liste change sous ses
   yeux dans le meme geste**, de onze jeux a six. Une reponse immediate a son propre appui est le
   retour le plus lisible qui existe pour un enfant de six ans - plus lisible qu'une marque sur un
   ecran statique qu'il faudrait interpreter. Et c'est **l'exact contraire** du defaut de PORTE-1, ou
   la boite ne repondait rien.
2. **Il n'est jamais coince.** Les deux puces restent visibles sur tous les ecrans ou elles servent :
   un enfant tombe sur la liste de l'autre niveau presse la sienne et retrouve ses jeux. **La sortie
   d'un filtre faux est toujours a l'ecran**, ce qui etait ma vraie inquietude.
3. **Ce qu'il perd - la vue melangee des onze jeux - n'est pas une perte pedagogique.** Cette liste ne
   tient pas au-dessus du pli, elle melange deux niveaux, et un enfant de CP n'a aucun usage des cinq
   jeux de CM1. Je ne vais pas bloquer un Done pour rendre atteignable une vue qui est moins bonne que
   celle qui la remplace.

**Quand elle deviendrait bloquante, et je l'ecris aujourd'hui pour ne pas avoir a l'inventer a s15 :
le jour ou un second axe de filtre s'ajoute.** Avec les matieres, l'enfant pourra combiner deux
filtres - CP et mathematiques - et **deux filtres combines ne se devinent pas depuis la liste** : trois
jeux affiches ne disent pas lesquels des deux criteres les ont selectionnes, ni comment en relacher un
seul. C'est a ce moment-la que l'absence d'etat visible cesse d'etre un confort manquant pour devenir
une impasse, et **c'est a ce moment-la que je la poserai en condition**, pas avant. Ce n'est pas un
durcissement de la reserve d'aujourd'hui : c'est une condition **datee du 2026-08-17 et suspendue a un
evenement nomme**, que `camille-po` peut lire d'avance.

#### 5. L'etat de mes cinq reserves de l'arbitrage prealable

| | Etat |
|---|---|
| **R-BARRE-1** (la reserve J des grands nombres) | **Ouverte, inchangee par la correction** : le haut du cabinet reste a y = 410 contre y = 397 avant le sprint, soit **+13 px**, et non les 66 px que j'avais annonces. Story candidate, sur la fiche des grands nombres. |
| **R-BARRE-2** (l'accueil non filtre perd des jeux) | **Levee**, remesuree : le solde est positif des que le filtre sert. |
| **R-BARRE-3** (rien ne dit le filtre en cours) | **Maintenue NON BLOQUANTE**, voir le 4, avec la condition future datee et nommee. Devient la reserve **S14-B** de cette fiche. |
| **R-BARRE-4** (F11 s'enrichit) | **Ouverte.** L'ecoute au lecteur d'ecran devra couvrir les deux puces, et un fait s'y ajoute : elles disparaissent sur deux ecrans sur cinq, donc **ce que la voix annonce n'est pas le meme selon l'ecran**. Cela ne change rien a ma demande d'ecoute, cela l'enrichit d'un ecran a ecouter. |
| **R-BARRE-5** (la troisieme forme) | **Refermee**, sans objet. |

#### 6. S14-2 - le retrait du lien de l'en-tete, juge dans le meme geste

**VALIDE, sans reserve propre.** `DEC-0033` a choisi la premiere de mes deux issues de BARRE-7, et
c'etait la meilleure : retirer l'element supprime le defaut au lieu de le deplacer. Verifie sur le
document servi - plus aucun lien dans l'en-tete - et sur le depot : l'element, la classe et la regle de
masquage sont partis ensemble, seuls des commentaires de trace et des fixtures de contre-exemple
citent encore l'ancien nom. **Le mot "Les jeux" reste exactement la ou il sert** : la fin de partie et
la confirmation, une fois chacune.

Une consequence que je note et qui prolonge mon Done du matin sur le retrait de l'`aria-label` :
l'en-tete ne porte plus qu'une seule zone de navigation, celle des puces, **et elle porte deux
controles sans aucun nom**. Ma position est inchangee - **aucun nom est meilleur qu'un nom faux**, je
n'en exige pas, et le geste 1 de F11 dira ce qu'une voix en fait. "Menu principal" reste refuse sur
ses trois motifs qui ne dependaient pas du nombre de liens.

#### 7. Ce dont je reste la seule garde apres ce Done

- **Le compte des boites pressables par ecran**, et il vient de servir deux fois : c'est ce comptage
  qui a produit le refus du matin et c'est lui qui le leve. Aucun garde-fou ne le fait, et
  `theo-archi` a eu raison de refuser que mes "deux boites" et "une boite" deviennent des constantes
  de test - ce sont les contenus du jour de deux ecrans, pas des proprietes. **Le corollaire est que
  cette verification me revient a chaque increment qui touche ces ecrans**, et personne ne doit la
  croire acquise.
- **Que la barre soit reellement invisible au rendu**, et non seulement declaree masquee. Les deux
  clauses neuves gardent le **couple declaratif** ; elles ne regardent aucune image.
- **Que la colonne de contenu ne recommence pas par une porte** (BARRE-3, formulation corrigee).
  Cinquieme fois que je le nomme.
- **Le budget du pli a 360 px.** Je le redis en tant que risque d'organisation et pas seulement de
  produit : **je suis la seule de cette squad a pouvoir produire un chiffre de rendu sur ce poste**,
  trois agents ayant echoue sur `outils/mesurer-rendu.sh` faute d'un navigateur designe. Tant que ce
  prerequis est une dette de poste, cette porte-la depend d'une seule personne.
- **La justesse de l'association titre / paquet**, prise dans mon refus sous les clauses N-1 et N-2, et
  qui reste prise : rien dans la correction ne la touche.

#### 8. Ce que ce Done ne fait pas

- **Il ne repond pas a la question de `camille-po`** sur ce qu'un enfant de six ans distingue. Il
  constate des boites et des places, pas une comprehension.
- **Il ne leve ni F11, ni la reserve J, ni la reserve O seconde moitie.**
- **Il ne prejuge pas de s15** : aucun second rang de puces, aucun libelle de matiere et aucun
  pictogramme n'est accorde d'avance, et le 4 nomme la condition qui s'appliquera au second axe de
  filtre.
- **Il ne tranche pas le nom du marqueur `ecran-exclusif`**, qui n'est pas de mon ressort et sur
  lequel je n'ai rien a dire.

### Done pedagogique du 2026-08-17 (s14) sur S14-1 - REFUSE, deux motifs, et tout le reste est credite - le premier verdict du jour, LEVE par le verdict ci-dessus

> **Ce que cette section fait.** Elle prononce - ici, elle **refuse** - le Done pedagogique de
> **S14-1** sur l'increment fusionne, apres l'avoir joue. Elle **n'efface pas** l'arbitrage prealable
> du meme jour, qui reste lisible en entier en dessous, et elle **n'ajoute aucune condition neuve** :
> les deux motifs sont deux des neuf conditions que j'avais ecrites **avant** le code. Elle corrige en
> revanche deux choses de ma main : le **texte** de BARRE-3, que j'avais ecrit inapplicable, et un
> **chiffre** de prevision que la mesure demode a l'avantage de l'increment.

- **Verdict** : **REFUSE**. Aucun Done pedagogique. **Motif, en deux phrases** : sur l'ecran de
  confirmation avant abandon, l'enfant a **quatre** boites pressables au lieu des deux que BARRE-1
  exige, et **deux d'entre elles sont la puce qu'il vient de presser**, qui ne repond plus rien si
  on la represse ; sur l'ecran d'erreur, il en a **trois** au lieu de la seule que BARRE-2 exige, sur
  l'ecran ou il est le plus perdu. **Les deux se corrigent par une regle de visibilite sur deux
  ecrans, pas par une reprise de la barre**, qui est bonne.
- **Ce refus ne bloque pas le sprint suivant.** Il renvoie l'increment a `camille-po`, qui en fait du
  backlog. **La barre reste en production en l'etat si elle le decide** : elle n'est ni fausse, ni
  dangereuse, et elle ne detruit aucune partie - je l'ai verifie en jouant.
- **Auteur** : nadia-prof. **Increment juge** : `round-two` a `911af6a`, worktree `s14-nadia-porte`,
  descendance verifiee par `outils/verifier-lignee.sh`, aucun fichier de production modifie de mon
  fait.
- **Porte technique, verifiee par moi et non prise sur parole** : `bash outils/mvn-jdk21.sh -B clean
  verify` sur mon worktree, **BUILD SUCCESS, 507 tests, 0 echec**. Je ne juge pas un jeu qui plante ;
  celui-ci ne plante pas.

#### 0. Comment j'ai joue, et ce que je declare degrade

**J'ai joue.** Jar reconstruit sur le JDK de la toolchain 21, application servie sur un port a moi,
et **j'ai fait les gestes qui m'inquietaient** : presser CP sans partie, presser CM1 sans partie,
demarrer une partie puis presser une puce, verifier que la partie est encore la apres l'ouverture de
la confirmation, confirmer depuis la puce, provoquer l'ecran d'erreur, et jouer une partie de
"Les complements a 10" jusqu'a "C'est fini !". Serveur et service de fichiers **arretes par leur PID
reel, jamais par nom d'image**.

**Methode des captures, et elle est la meme que celle que j'ai acceptee de `theo-archi` le
2026-08-14** : le document complet est celui que le serveur rend pour la racine ; l'etat echange est
obtenu en remplacant le contenu de `#jeu` par le fragment que le serveur rend **reellement** pour la
route que la puce ou le bouton appelle - c'est-a-dire en refaisant cote serveur ce que
`hx-target="#jeu" hx-swap="innerHTML"` fait cote navigateur. Aucune feuille de style n'est dupliquee
ni modifiee. Captures :

```
outils/mesurer-rendu.sh 360 <url> <png> 640
```

Sept captures dans `squad/conformite/releves-s14/`, prefixees `livre-` :

| Capture | Ce qu'elle etablit |
|---|---|
| `livre-1-accueil-complet-360x640.png` | en-tete a 142 px, un seul rang de puces, 6 jeux entiers sur 11, le septieme coupe |
| `livre-2-accueil-filtre-cp-360x640.png` | **les six jeux de CP entiers au-dessus du pli, avec de la marge** |
| `livre-3-manche-360x640.png` | l'ordre de lecture : score, consigne, enonce, propositions, **puis** la sortie |
| `livre-4-confirmation-depuis-une-puce-360x640.png` | **quatre boites pressables** - le motif 1 du refus |
| `livre-5-erreur-360x640.png` | **trois boites pressables** - le motif 2 du refus |
| `livre-6-fin-de-partie-360x640.png` | un seul "Les jeux", en aplat, dans la zone de contenu |
| `livre-7-manche-grands-nombres-360x640.png` | le meme item que ce matin, pour chiffrer ma reserve J |

**Ce que je declare degrade.**

- **Je n'ai pas joue au doigt sur un telephone**, ni observe un enfant. La question de `camille-po` -
  un enfant de CP confond-il une puce et une sortie - **je ne peux pas y repondre par une mesure**.
  Ce que je peux faire, et que j'ai fait, c'est compter les boites et regarder leur place. C'est la
  meme limite irreductible que le 2026-08-14, et c'est la plus serieuse de ce bloc.
- **Je n'ai pas essaye la tabulation au clavier.** L'ordre des arrets que je cite au 7 est **lu dans
  l'ordre du document**, pas eprouve avec un clavier.
- **Un seul item echantillonne** pour les grands nombres, choisi parce que c'est celui que j'avais
  deja mesure ce matin, pour que les deux chiffres soient comparables.

#### 1. Etat de mes neuf conditions, verifiees une par une

| | Etat | Ce que j'ai constate, et comment |
|---|---|---|
| **BARRE-1** | **NON TENUE** | Capture `livre-4` : **quatre** boites pressables sur l'ecran de confirmation - CP, CM1, "Continuer la partie", "Les jeux". Ma condition en exigeait **deux**. Motif detaille au 2. |
| **BARRE-2** | **NON TENUE** | Capture `livre-5` : **trois** boites pressables sur l'ecran d'erreur - CP, CM1, "Recommencer". Ma condition en exigeait **une**. Motif detaille au 2. |
| **BARRE-3** | **TENUE sur son objet**, et **mon texte etait fautif** | Capture `livre-3` : la colonne de contenu d'une manche commence par "Score : 0" et la sortie est passee **sous les propositions**. C'est mon propre remede de 2026-08-07, applique pour la premiere fois. Le compte litteral que j'avais ecrit etait inapplicable : voir ma correction au 4. |
| **BARRE-4** | **TENUE** | Captures `livre-1` a `livre-6` : les puces ne portent que "CP" et "CM1". Aucun disque, aucune pastille, aucun pictogramme. Le commentaire de `recreo.css` nomme la condition a l'endroit ou elle se tient. |
| **BARRE-5** | **TENUE** | Comptage du mot "Les jeux" sur les cinq etats, commentaires HTML retires : accueil **0**, manche **1** (sous les propositions), confirmation **1**, erreur **0**, fin de partie **1**. Aucune puce ne le porte. |
| **BARRE-6** | **TENUE, et tenue par un automate** | J'avais demande la propriete, pas l'attribut. `ADR-0043` fait du troisieme role un **role domine** : `LActionNeDeclarePasMoinsQueLaSortieTest` met `entree` dans `ROLES_DOMINES` avec `sortie`, confronte le role a **toutes** les familles d'action, et porte un contre-exemple dedie (`entree-permanente-plus-grosse`) qui le fait rougir. Je verifie donc que la regle existe, qu'elle mord et qu'elle est verte, et **je cesse de compter cette propriete a la main**. |
| **BARRE-7** | **TENUE** | `camille-po` a choisi la premiere de mes deux issues (`DEC-0033`) : la boite disparait. Verifie dans le depot - l'element, la classe `.menu`, `.lien-accueil` **et** sa regle de masquage sont retires, pas masques ; il n'en reste que des commentaires de trace et des fixtures de contre-exemple. Le document servi ne porte plus aucun lien d'en-tete. |
| **BARRE-8** | **TENUE, et mieux que ma borne** | Mesure au pixel sur `livre-1`, colonne x = 180 : bandeau d'en-tete `#1d4b6b` de y 0 a 59 puis de y 126 a 141, bande des puces `#081c2c` de y 60 a 125 - **66 px exactement pour un seul rang**, total d'en-tete **142 px**, contenu a partir de y 142. Ma borne disait 151 px : l'increment est **9 px sous** ma borne, parce que S14-2 a retire le bouton du premier rang. Puces mesurees **56 x 50 px** ("CP") et **74 x 50 px** ("CM1"), au-dessus du plancher de cible de 44 x 44 px. Rien de pressable n'a ete retreci. |
| **BARRE-9** | **TENUE, et durablement** | Il n'y a plus aucun "Les jeux" dans l'en-tete : les deux ne peuvent plus partager un rang, et **ce n'est pas un masquage qu'un `flex-wrap` pourrait defaire** - l'element n'existe plus. |

**Sept sur neuf tenues, dont deux mieux que ce que je demandais.** Et deux non tenues, qui sont
**deux des trois que j'avais nommees comme portant ma reponse "oui"**.

#### 2. Les deux motifs du refus

**Motif 1 - l'ecran de confirmation porte quatre boites, et deux sont le geste qui l'a ouvert.**

Capture `livre-4-confirmation-depuis-une-puce-360x640.png`, prise apres avoir demarre une partie puis
presse "CM1". De haut en bas, l'enfant voit : **CP**, **CM1**, puis *"Si tu pars, ta partie
s'arrête."*, puis **"Continuer la partie"**, puis **"Les jeux"**.

- **Cet ecran pose une question et n'a que deux reponses.** C'est l'ecran dont j'ai accepte la forme
  le 2026-08-14 sous une condition unique - 44 px entre les deux boutons - **precisement parce que
  l'enfant doit choisir entre deux gestes**. Il en a quatre.
- **Et les deux boites en trop sont celle qu'il vient de presser.** J'ai lu le controleur pour savoir
  ce qui se passe s'il represse "CM1" ici : `GET /niveaux/{niveau}` avec une partie en cours rend **le
  meme ecran de confirmation**. Donc l'enfant presse, **et rien ne change a l'ecran**. C'est
  exactement le defaut pour lequel j'ai ecrit PORTE-1 le 2026-08-14 - *"un bouton qui ne repond pas
  apprend a un enfant de six ans que les boutons ne repondent pas"* - que `DEC-0033` vient de fermer
  sur l'accueil, et qui **reapparait ici, sur un autre ecran, avec les puces**.
- **Ceci ne contredit pas l'arbitrage de l'utilisatrice, et je l'avais ecrit avant le code.** Sa
  decision veut qu'un enfant puisse demander un changement de niveau a tout moment. Sur cet ecran,
  **il l'a deja demande** : la barre n'y sert plus la decision, elle n'y ajoute qu'un piege. Si
  l'utilisatrice veut la barre la aussi, c'est sa decision, il suffit de l'ecrire et je l'inscrirai -
  je nomme le cout, je ne le lui impose pas.

**Motif 2 - l'ecran d'erreur porte trois boites la ou j'en exigeais une.**

Capture `livre-5-erreur-360x640.png` : **CP**, **CM1**, *"Oups ! Le jeu a un souci."*,
**"Recommencer"**. BARRE-2 reaffirmait PORTE-3 du 2026-08-14 mot pour mot. Le motif est inchange :
c'est l'ecran ou l'enfant est le plus perdu, ou j'ai deja constate que le seul mot present n'est meme
pas dechiffrable au CP, et **augmenter le choix la ou il a le moins les moyens de choisir est le
contraire de ce qu'il faut faire**. Ici il n'y a plus de partie du tout : l'arbitrage de
l'utilisatrice ne dit rien de cet ecran.

**Ce que ces deux motifs demandent, et ce qu'ils ne demandent pas.** Ils demandent que la barre ne
s'affiche pas sur ces deux ecrans. **Le moyen n'est pas de moi** - mais j'ai verifie qu'il existe,
pour ne pas poser une condition impossible : la forme contractuelle `body:has(.<marqueur>)
.<habillage> { display: none }` est deja employee par le depot, et l'ecran de confirmation porte
**deja** un attribut qui le distingue des autres (`data-focus="phrase"`), tandis que l'ecran d'erreur
n'en a pas encore. **Ils ne demandent aucune reprise de la barre, aucun changement de la variante
retenue, aucun changement des routes.**

#### 3. Ce que je credite, et il y en a beaucoup

Je l'ecris parce qu'un refus qui ne dit pas ce qui est bon fait recommencer ce qui etait juste.

1. **Le gain que j'avais annonce est verifie sur l'increment reel, et il est la.** Capture `livre-2` :
   apres un appui sur "CP", **les six jeux de CP tiennent entiers au-dessus du pli, avec de la
   marge**, la ou le catalogue complet n'y tient plus - 6 sur 11, le septieme coupe. Un enfant de CP
   ne defile plus pour trouver son jeu et ne voit plus cinq jeux qui ne sont pas pour lui. **C'est le
   besoin de S14-1 et il est atteint.**
2. **La partie est reellement protegee, verifie en jouant** : apres avoir presse "CM1" pendant une
   partie, j'ai redemande la page et **la manche etait toujours la**. Et en confirmant depuis la
   puce, j'ai bien atterri sur la liste **filtree sur CM1** - cinq jeux. Les deux criteres que
   `camille-po` avait ecrits sur ce point sont tenus.
3. **L'ordre de lecture d'une manche est repare, et c'est un acquis que j'attendais depuis le
   2026-08-07.** La sortie n'ouvre plus la colonne : elle est passee sous les propositions. Un enfant
   qui vient d'echouer trois fois ne lit plus une invitation a partir comme premier mot.
4. **Le troisieme role est un vrai garde-fou, pas une etiquette.** Voir BARRE-6 : `entree` est domine
   par construction, avec un contre-exemple qui le prouve. C'est une propriete de plus que je cesse
   de garder seule, et c'en est la deuxieme de ce sprint.
5. **`DEC-0033` a tranche BARRE-7 par la meilleure des deux issues.** Retirer l'element plutot que lui
   inventer un comportement supprime le defaut au lieu de le deplacer, et le mot "Les jeux" reste
   exactement la ou il sert : la fin de partie et la confirmation.

#### 4. Je corrige deux choses de ma main, avant de m'appuyer sur le reste

**Correction 1 - le texte de BARRE-3 etait inapplicable, et l'erreur est de moi.** J'ai ecrit *"sur
les trois etats de manche, l'enfant ne lit pas plus d'une porte avant l'enonce"*, en comptant les deux
puces parmi les portes. **Or l'utilisatrice a decide que les deux puces sont visibles sur un ecran de
jeu, et elles sont dans l'en-tete, donc au-dessus de l'enonce par construction : ma condition exigeait
donc, litteralement, de supprimer une puce.** C'etait impossible a tenir sans defaire une decision que
je ne dois pas rouvrir. **Ce que ma condition voulait dire, et ce que son motif dit sans ambiguite,
c'est que la zone de contenu ne devait pas ajouter une porte par-dessus la barre** - et c'est
exactement ce que `marc-dev` a fait. **Je compte donc BARRE-3 comme tenue, et je ne fais pas payer a
l'equipe une condition que j'ai mal ecrite.** La formulation juste, pour la prochaine fois : *"la
colonne de contenu d'un ecran de jeu ne commence jamais par une porte"*.

**Correction 2 - ma prevision de cout sur les grands nombres etait pessimiste de 53 px.** Ce matin,
j'avais mesure sur une barre injectee que l'item *"Décompose 305 421 en valeurs de position."*
perdrait 66 px et que sa deuxieme proposition serait reduite a une bande de quelques pixels. Mesure
sur l'increment livre, meme item, meme commande, colonne x = 180 : le haut du cabinet passe de
**y = 397 avant le sprint** a **y = 410**, soit **+13 px** et non +66. **La sortie descendue sous les
propositions rend 53 des 66 px que la barre prend**, et la deuxieme proposition affiche encore sa
premiere ligne, comme avant. **Ma prevision etait fausse dans le sens defavorable a l'increment ; je
la corrige.**

#### 5. Les libelles "CP" et "CM1" et le garde-fou de texte accentue - la question de `yanis-qa`

**Mon jugement : l'exemption est legitime pour ces deux libelles-la, et le trou du garde-fou est reel
mais il n'est pas sur eux.** Les deux choses sont distinctes et il faut les separer.

- **"CP" et "CM1" ne contiennent aucune lettre qui puisse porter un accent.** Pas une voyelle : C, P,
  C, M, 1. L'exigence *"integralement accentue"* n'est pas contournee sur eux, **elle est satisfaite
  a vide** : il n'existe aucune autre facon d'ecrire ces deux chaines. Ce n'est donc meme pas une
  exemption, c'est une exigence sans objet. **Et sur le fond pedagogique, je maintiens ce que j'ai
  ecrit dans mon arbitrage** : ce sont les etiquettes que l'enfant lit deja sur son cahier, pas des
  mots a decoder.
- **Le trou du garde-fou, lui, est reel, et il faut le nommer plutot que de le decouvrir a s15.** Un
  libelle rendu par le modele echappe a l'inventaire. Aujourd'hui cela ne coute rien. **A s15, cela
  coutera exactement le defaut que `DEC-0032` section 2 a differe pour l'eviter** : les libelles de
  matiere sont ecrits `"Francais"` et `"Mathematiques"` dans les descripteurs des paquets, **sans
  accent**, et ils passeront par le meme chemin que "CP" et "CM1". Le garde-fou ne les verra pas
  davantage. **Ce trou doit etre ferme avant que le premier libelle accentue ne passe par la, pas
  apres.**
- **La frontiere exacte de mon exemption, pour qu'on ne l'etende pas toute seule** : elle vaut pour
  un libelle de niveau **sans lettre accentuable**. Le jour ou un paquet declarerait un niveau dont le
  nom en porte une - "Première" en est un, et ce produit n'est pas garanti de s'arreter au CM1 -
  l'exemption tombe d'elle-meme et le libelle redevient du texte a verifier.

**Ce que je demande donc**, et c'est actionnable : que l'exemption soit **ecrite explicitement** dans
la source du garde-fou avec sa raison (aucune lettre accentuable) et sa frontiere, plutot que laissee
implicite ; et que la fermeture du trou pour les libelles rendus par le modele soit instruite **avant
s15**. Le moyen appartient a `theo-archi`. **Ce point ne fait pas partie des motifs de mon refus.**

#### 6. Le residu d'`ADR-0045` - je prends la propriete, et voici la forme verifiable

`theo-archi` ecrit que la justesse de l'association entre une entree de catalogue et son paquet - que
le jeu intitule "Les mesures" serve bien un paquet de mesures - n'est pas encodable et qu'elle
m'appartient. **Je la prends.** C'est un jugement sur le **sens** d'un titre, et aucun test ne juge un
sens.

**Je commence par lire son chiffre au lieu de le repeter** : sur **121** mutations possibles, son
garde-fou en attrape **110** et il en echappe **11**, et ces onze ne sont pas quelconques - ce sont
**les onze renvois vers `cm1-homophones`, le seul paquet de `contenu/` que plus aucune entree ne
sert**. Le residu n'est donc pas "n'importe quelle mauvaise association" : c'est **"une entree qui
pointe vers un paquet qu'aucune entree ne sert"**. Cette precision change la forme que je peux tenir.

**La forme sous laquelle je la prends, en deux clauses verifiables par un tiers sans moi :**

- **N-1. A chaque Done pedagogique d'un jeu, j'inscris dans sa fiche le paquet servi et un enonce que
  l'application m'a reellement servi sous ce titre.** Verification par un tiers : presser le titre
  dans l'application et comparer l'enonce obtenu a celui de la fiche. Une association devenue fausse
  se voit alors sans me redemander quoi que ce soit - et elle se voit sur le **materiau joue**, pas
  sur une chaine de caracteres.
- **N-2. Je veux pouvoir connaitre a tout moment le nombre de paquets de `contenu/` qu'aucune entree
  du catalogue ne sert.** Il vaut **1** aujourd'hui (`cm1-homophones`) et **il borne exactement le
  residu** : onze mutations tant qu'il vaut 1, **zero si ce nombre tombe a zero**. Je ne prescris pas
  de retirer ce paquet - ce n'est pas ma decision - je nomme le seul levier qui fait disparaitre le
  residu, pour que personne ne le cherche ailleurs.

Je note enfin ce que cette prise **ne** couvre pas : je ne relis pas les 121 associations, je ne juge
que **les jeux que je joue**, et un jeu jamais rejoue garde l'association que sa derniere fiche a
enregistree. C'est une garde par echantillon date, pas une garde continue, et personne ne doit la lire
autrement.

#### 7. Mes cinq reserves non bloquantes, apres verification

| | Etat apres l'increment livre |
|---|---|
| **R-BARRE-1** (la reserve J des grands nombres empire) | **Toujours ouverte, mais l'aggravation est de 13 px et non de 66** - voir ma correction 2. Reste une story candidate pour `camille-po`, et le chiffre a viser est inchange : aucun item ne place une proposition sous le pli a 360 x 640. |
| **R-BARRE-2** (l'accueil non filtre perd des jeux de visibilite) | **Levee dans les faits.** Elle est remplacee par son contraire : le catalogue complet passe de 8 a 6 jeux entiers, mais **la liste filtree tient en entier**, ce qui est l'usage que la barre installe. Le solde est positif et je le dis. |
| **R-BARRE-3** (le mot "Les jeux" confirme un geste demande sous un autre nom) | **AGGRAVEE, et c'est le point que je route en premier a `camille-po`.** Ce qui devait la reparer - la puce active, visible sur la liste qui apparait - **n'existe pas dans l'increment** : `ADR-0044` point 3 pose qu'aucun etat de filtre ne vit dans l'en-tete, donc **aucune puce ne se distingue jamais**. Consequence mesuree sur `livre-2` : apres avoir presse "CP", l'enfant voit six jeux et **rien a l'ecran ne dit pourquoi la liste a raccourci, ni comment revoir les onze**. La forme d'`ines-design` que j'avais creditee portait un etat actif en plein contre contour ; l'increment ne l'a pas. **Non bloquant** - je n'en avais pas fait une condition et je ne me rattrape pas apres coup - mais c'est une story, et son motif est ecrit ici. |
| **R-BARRE-4** (F11 s'enrichit) | **Confirmee et precisee.** L'ecoute devra couvrir les deux puces, et un fait neuf s'y ajoute : **les deux puces sont les deux premiers arrets de tabulation d'un ecran de jeu**, avant les propositions - lu dans l'ordre du document, non eprouve au clavier. Mon geste 1 demandait les mots annonces pour l'unique lien de l'en-tete ; cet unique lien n'existe plus, et ce sont les deux puces qu'il faudra entendre. |
| **R-BARRE-5** (la troisieme forme, non mesuree) | **Sans objet, et de la meilleure facon.** Elle proposait de recuperer les 66 px en logeant les puces dans le vide laisse par "Les jeux" sur un ecran de jeu. `DEC-0033` a retire ce lien partout, et la sortie descendue sous les propositions rend deja 53 des 66 px. **Je la referme.** |

#### 8. Ce dont je reste la seule garde sur cet increment

- **Qu'un enfant de CP distingue une puce de niveau d'une sortie de jeu.** C'est la question de
  `camille-po`, elle reste sans reponse mesurable, et **mon refus ne la tranche pas non plus** : il
  reduit le nombre de boites sur deux ecrans, il ne prouve pas la comprehension.
- **Le compte des boites pressables par ecran.** Aucun garde-fou ne le fait, et les deux motifs de ce
  refus n'existent que parce que je les ai comptes en jouant. Si la correction arrive, **c'est encore
  moi qui la verifierai, sur deux captures.**
- **Que la colonne de contenu ne recommence pas par une porte** (BARRE-3 dans sa formulation
  corrigee). Rien ne l'encode. Quatrieme fois que je le nomme.
- **Le budget du pli a 360 px**, et je suis la seule a pouvoir le mesurer sur ce poste : trois agents
  ont echoue sur `outils/mesurer-rendu.sh` faute d'un navigateur designe. **Tant que ce prerequis est
  une dette de poste, aucun chiffre de rendu n'entre dans ce depot sans passer par moi**, et c'est
  une dependance a une seule personne que je signale comme telle.
- **La justesse de l'association titre / paquet**, que je viens de prendre au 6, sous ses deux clauses
  N-1 et N-2.

#### 9. Ce que ce refus ne fait pas

- **Il ne rouvre pas l'arbitrage de l'utilisatrice** : la barre existe, elle porte CP et CM1, elle est
  visible sur un ecran de jeu, un appui pendant une partie passe par la confirmation. Mes deux motifs
  portent sur **deux ecrans qui ne sont pas des ecrans de jeu au sens de sa decision** - l'un ou le
  geste a deja ete fait, l'autre ou il n'y a plus de partie.
- **Il ne retire aucun Done deja prononce** et ne rouvre aucune ligne de la matrice : cette story n'en
  touche aucune.
- **Il n'ajoute aucune condition neuve.** Les neuf de l'arbitrage prealable restent les neuf, dont une
  corrigee dans sa formulation par son autrice et **a l'avantage de l'equipe**.
- **Il ne demande pas de defaire la variante retenue**, ni les routes, ni le troisieme role, ni
  `DEC-0033`.

### Arbitrage prealable au developpement - barre d'entree par niveau toujours visible (DEC-0032, s14) - rendu le 2026-08-17

> Question posee par `camille-po` dans `DEC-0032`, sans la trancher :
>
> Un enfant de CP retrouve-t-il la sortie et ne confond-il pas les boutons de niveau avec une sortie
> de jeu, si l'en-tete porte desormais trois elements actionnables visibles en permanence sur un
> ecran de jeu (le lien "Les jeux", habituellement masque, restant masque ; les deux boutons de
> niveau, nouvellement visibles) ? Le passage par l'ecran de confirmation deja existant est-il un
> signal suffisant pour qu'il comprenne qu'appuyer sur un bouton de niveau pendant une partie a une
> consequence sur cette partie ?

**Ce bloc n'est pas un Done pedagogique.** C'est l'arbitrage prealable que `DEC-0032` section 1 exige
avant toute affectation au planning. Il ne prononce aucun Done, n'ouvre ni ne ferme aucune ligne de
`squad/programme/MATRICE-couverture.md`, et il **ne rouvre rien de ce que l'utilisatrice a tranche
elle-meme** : la barre existe, elle porte CP et CM1, elle est visible y compris sur un ecran de jeu,
un appui pendant une partie passe par la confirmation existante. Je ne juge que la **forme**, ce que
la visibilite permanente coute a un enfant, et les conditions que je pose.

#### Verdict, en tete pour qu'il ne se cherche pas

- **Variante 1 (bandeau empile) RETENUE** pour ce sprint.
- **Variante 2 (bandeau a defilement horizontal) REFUSEE**, trois motifs en section 2, dont un qui
  suffirait seul : elle montre a un enfant qui apprend a lire un mot coupe en deux.
- **Neuf conditions bloquantes, BARRE-1 a BARRE-9** (section 6), chacune verifiable par un tiers sur
  une capture ou sur le document servi, sans me redemander ce que je voulais dire.
- **Cinq reserves non bloquantes, R-BARRE-1 a R-BARRE-5** (section 7), a charge pour `camille-po`
  d'en faire des stories si elle le juge.
- **Reponse aux deux questions de `camille-po`** : oui, un enfant retrouve la sortie, **a condition
  que BARRE-1, BARRE-2 et BARRE-3 soient tenues** - sans elles, il lit trois portes avant l'enonce
  sur une manche, quatre boutons sur l'ecran de confirmation et trois sur l'ecran d'erreur. Et non,
  le passage par la confirmation **n'est pas a lui seul** le signal suffisant : il l'est pour la
  consequence sur la partie, il ne dit rien de la confusion entre deux portes, qui se joue avant
  l'appui et non apres.

#### 0. Comment j'ai joue, et ce que je declare degrade

**J'ai joue, et j'ai mesure moi-meme plutot que de reprendre les chiffres d'`ines-design`.** Worktree
dedie `s14-nadia-dettes`, descendance de `round-two` verifiee par `outils/verifier-lignee.sh`, aucun
fichier de production modifie de mon fait. Jar reconstruit sur le JDK de la toolchain 21
(`bash outils/mvn-jdk21.sh -B clean verify`, BUILD SUCCESS, 489 tests), application servie sur un
port a moi. Parties ouvertes par requetes HTTP reelles, `GET /` en reprise pour obtenir le document
assemble de l'ecran de manche (methode `ADR-0028` section 2, celle qu'`ines-design` cite). La barre
de niveaux a deux puces est injectee dans l'en-tete de ce document assemble, avec la boite exacte
qu'`ines-design` decrit en section 1 de sa proposition ; les feuilles reelles sont chargees depuis
l'application en cours d'execution, **aucune ligne de `recreo.css` n'est modifiee**. Captures :

```
outils/mesurer-rendu.sh 360 <url> <png> 640
```

Sept captures, toutes a 360 px de large et au pli de 640 px, versees dans
`squad/conformite/releves-s14/` pour qu'un tiers puisse relire mes chiffres sans me croire :

| Capture (`squad/conformite/releves-s14/`) | Ce qu'elle montre |
|---|---|
| `barre-accueil-production-360x640.png` | l'accueil d'aujourd'hui : 8 jeux entiers sur 11, le neuvieme coupe |
| `barre-accueil-avec-barre-360x640.png` | le meme avec la barre : 6 jeux entiers, le septieme coupe |
| `barre-accueil-filtre-cp-avec-barre-360x640.png` | la liste filtree sur CP avec la barre : les six jeux entiers, avec de la marge |
| `barre-manche-problemes-additifs-cp-production-360x640.png` | une manche de CP aujourd'hui |
| `barre-manche-problemes-additifs-cp-avec-barre-360x640.png` | la meme manche avec la barre : les trois propositions restent visibles |
| `barre-manche-grands-nombres-cm1-production-360x640.png` | l'item "Décompose 305 421..." aujourd'hui : 2e proposition coupee, 3e invisible |
| `barre-manche-grands-nombres-cm1-avec-barre-360x640.png` | le meme avec la barre : 2e proposition reduite a une bande de quelques pixels |

Les deux processus que j'ai lances - l'application et le service de fichiers des documents assembles -
**ont ete arretes par leur PID, jamais par nom d'image**.

**Ce que je declare degrade, et ce n'est pas rien.**

- **Je n'ai pas joue au doigt sur un telephone.** C'est la meme limite irreductible que le
  2026-08-14 : mon jugement sur ce qu'une main de six ans atteint ou evite reste un jugement de
  professeure appuye sur une geometrie, **pas une observation d'enfant**.
- **Je n'ai capture ni l'ecran de confirmation, ni la fin de partie, ni l'ecran d'erreur avec la
  barre.** Ces trois etats ne s'atteignent pas par une URL, et je n'ai pas fabrique de document
  assemble pour eux. **BARRE-1, BARRE-2 et BARRE-5 reposent donc sur un comptage des boites
  pressables lu dans les gabarits reellement servis**, pas sur une capture : `confirmation.html`
  porte "Continuer la partie" et "Les jeux", `erreur.html` porte "Recommencer" seul, `retour.html`
  porte "Les jeux" seul. Le comptage est solide ; la geometrie manque, et je le dis plutot que de la
  supposer.
- **Un seul item echantillonne par jeu** pour le budget du pli. Mes chiffres disent ce qu'ils ont
  mesure sur ces items-la, pas sur les 28 items d'un paquet.

#### 1. Ce que la visibilite permanente fait a l'ecran, mesure

**Le cout de hauteur, verifie sur deux ecrans de jeu reels.** L'en-tete passe de **85 px a 151 px**,
soit **+66 px**, identique sur les deux jeux mesures et identique au chiffre d'`ines-design` - je le
credite, je ne l'ai pas repris sur parole.

| Ecran mesure | Aujourd'hui | Avec la barre a deux puces |
|---|---|---|
| Accueil, catalogue complet de onze jeux | **8 jeux entierement visibles**, le neuvieme coupe au pli | **6 jeux entierement visibles**, le septieme coupe |
| Accueil, liste filtree sur CP (six jeux) | sans objet | **les six jeux entierement visibles**, avec de la marge |
| "Petits problemes" CP, item "Le fermier a 1 chat et en adopte 2. Combien en a-t-il ?" | enonce et les trois propositions au-dessus du pli | **inchange sur le fond** : les trois propositions restent entierement visibles |
| "Les grands nombres" CM1, item "Décompose 305 421 en valeurs de position." | 1re proposition entiere, **2e coupee au pli, 3e invisible** | 1re proposition entiere, **2e reduite a une bande de quelques pixels, 3e invisible** |

**Le compte des portes, par ecran, si rien d'autre ne change.** C'est le chiffre qui decide de mes
conditions, et il ne se lit sur aucune capture d'`ines-design` parce qu'il ne se mesure pas en
pixels : il se compte.

| Ecran | Portes visibles aujourd'hui | Avec la barre |
|---|---|---|
| manche, echec, revelation | 1 ("Les jeux", **premiere bande de la colonne de contenu**) | **3**, et les trois sont **au-dessus de l'enonce** |
| fin de partie | 1 ("Les jeux" en aplat, la seule chose a faire) | **3** |
| erreur | 1 ("Recommencer", la seule chose pressable) | **3** |
| confirmation avant abandon | 2, et c'est exactement le nombre de reponses a la question posee | **4**, dont deux qui **redemandent ce que l'ecran est en train de faire confirmer** |

#### 2. Question 1 d'`ines-design` - la variante retenue, et le refus motive de l'autre

**La question telle qu'elle est posee n'a pas de reponse ce sprint, et je le dis avant de trancher.**
"Laquelle protege le mieux un enfant de CP qui joue sur un item deja proche du pli ?" - **a deux
puces, ni l'une ni l'autre : les deux coutent 66 px, je l'ai mesure moi-meme sur deux jeux.** Le pli
ne separe ces deux variantes qu'a partir de quatre boutons, c'est-a-dire a s15. **Je ne tranche donc
pas s14 sur le pli**, et je ne veux pas qu'on me lise comme ayant choisi pour un chiffre qui est le
meme des deux cotes. Je tranche sur deux autres proprietes, et je borne le pli par BARRE-8 pour que
s15 n'herite pas d'un cheque en blanc.

**Variante 2, REFUSEE. Trois motifs, le premier suffirait seul.**

1. **Elle montre a un enfant qui apprend a lire un mot coupe en deux.** `ines-design` le mesure
   elle-meme : des quatre boutons, "Mathematiques" apparait tronque a mi-mot sur le bord droit a
   360 px. Un enfant de CP **decode ce qu'il voit** : un debut de mot n'est pas un indice de
   defilement pour lui, c'est un mot a lire, et il le lira faux. Tout ce produit repose sur la regle
   inverse - ce que l'enfant lit est complet et correct. Je ne l'ouvre pas pour un bandeau.
2. **Elle demande un geste que cette application n'a jamais demande.** Un balayage horizontal a
   l'interieur d'une bande de 66 px, pendant que la page, elle, defile verticalement. Discriminer
   ces deux gestes est un apprentissage, et il n'est pas au programme de ce produit.
3. **La propriete qui la sauverait n'est mesurable par personne, et `ines-design` l'ecrit elle-meme**
   ("aucun garde-fou de ce depot ne peut mesurer si un enfant reel percoit cet indice"). Entre un
   cout que je peux **borner** (la variante 1 coute `8 + 58n` px, chiffrable a l'avance et gardable
   par une condition) et un cout que **personne ne peut constater**, je prends celui que je peux
   borner. Ce n'est pas une preference de forme, c'est la seule des deux qui me laisse une porte a
   tenir.

**Variante 1, RETENUE**, sous BARRE-8 qui l'empeche de grandir sans un arbitrage neuf. Ce que je
retiens d'elle, positivement : son cout est **visible, mesure, et il ne cache rien**. Rien ne sort du
champ, aucun mot n'est coupe, aucun geste n'est requis. Un enfant voit les deux niveaux ou il ne les
voit pas ; il n'y a pas d'etat intermediaire ou il croirait avoir tout vu.

#### 3. Le gain que je credite, parce qu'un arbitrage qui ne compte que les couts est une caricature

**La barre repaie son propre cout des qu'elle sert, et je l'ai mesure.** Aujourd'hui, a 360 x 640, un
enfant qui arrive sur l'accueil voit **8 des 11 jeux**, le neuvieme coupe : la liste ne tient deja
plus, et c'est un fait que j'ai releve ce matin avant de connaitre cette story. Avec la barre, elle
tient encore moins - 6 sur 11. **Mais des qu'un niveau est choisi, la liste filtree tient en entier :
les six jeux de CP sont entierement visibles avec de la marge, capture a l'appui, et les cinq de CM1
a plus forte raison.** Un enfant de CP ne defile plus du tout pour trouver son jeu, et il ne voit
plus cinq jeux qui ne sont pas pour lui. C'est un gain reel et c'est exactement le besoin de S14-1.

Je le dis nettement pour qu'on ne me prete pas une reticence que je n'ai pas : **sur l'accueil, cette
barre rend le catalogue plus lisible qu'il ne l'est aujourd'hui.** Mon reserve porte sur les ecrans de
jeu, pas sur l'accueil.

#### 4. Les quatre autres questions d'`ines-design`

**Question 2 - pictogramme sur CP et CM1 : aucun, et j'ajoute le motif qui manque a son raisonnement.**
Son raisonnement est juste et je le credite : "CP" et "CM1" ne sont pas des mots a decoder, ce sont
des etiquettes que l'enfant lit deja sur son cahier. J'ajoute ce qui decide vraiment : **un
pictogramme de niveau devrait representer un age ou un rang**, et il n'existe aucune image de "l'annee
ou tu es" qu'un enfant de six ans lise sans qu'on lui ait appris. Un symbole qu'il faut apprendre
pour lire une etiquette qu'il sait deja lire est un pas en arriere. Aucun pictogramme.

**Et je REFUSE le petit disque decoratif** que ses mockups posent a cote du texte des puces, qu'elle
propose elle-meme de retirer a cout nul. Motif, et c'est un precedent a moi : le **2026-08-11**, j'ai
exige que **"le disque decoratif quitte `.demarrer` et `.encore`"** - une reserve que j'avais ouverte
sur mon propre manquement, ayant approuve cette portee la veille. Un disque muet pose **a l'interieur
d'une boite pressable** est une marque dont un enfant cherche le sens ; je l'ai deja refuse sur ce
produit et je ne l'accepte pas ici parce qu'il prepare l'alignement d'un bouton qui n'existe pas
encore. **Reserver l'espace en CSS ne me gene pas ; afficher quelque chose dedans, oui.** C'est
BARRE-4.

**Question 3 - `data-role="sortie"` : oui, c'est la bonne lecture pedagogique.** Du point de vue de
l'enfant, appuyer sur "CP" pendant une partie **quitte l'activite en cours** - c'est precisement pour
cela que l'utilisatrice a exige le passage par la confirmation. Nommer cela une sortie est honnete.
**Ce que je demande n'est pas l'attribut mais la propriete qu'il achete** : une puce de niveau n'est
**jamais** plus saillante qu'une action de l'ecran ou elle s'affiche (BARRE-6). Le vocabulaire
`data-role` et le sort de `LActionNeDeclarePasMoinsQueLaSortieTest` appartiennent a `theo-archi`, et
`ines-design` a raison de le lui router : je ne choisis pas le moyen. Si une troisieme valeur est
retenue, la propriete reste due et le garde-fou doit la tenir dans le meme commit.

**Question 4 - l'etat actif en plein contre contour : oui, lisible, je l'ai regarde a 360 px.** Sur ma
capture, la puce active est un **bloc blanc plein** et l'inactive un **contour blanc sur fond
sombre** : la difference se voit avant qu'on ait lu les lettres, et c'est une difference de **forme**,
pas de teinte - un enfant qui ne distingue pas les couleurs la voit. Elle reprend la meme opposition
que `.demarrer` (aplat) face a `.retour-accueil` (contour) ailleurs dans l'application, donc elle
n'apprend rien de neuf a l'enfant. Contrastes, calcules a la main sur les hexadecimaux **declares**
dans `recreo.css` et non echantillonnes sur un pixel : puce inactive, `#ffffff` sur
`--recreo-cabinet-fond` `#081c2c`, **17,5:1** ; puce active, `--recreo-entete-fond` `#1d4b6b` sur
`#ffffff`, **9,25:1**, la valeur que j'ai deja etablie pour cette paire le 2026-08-14. Les deux sont
tres au-dessus de tout plancher que ce produit s'impose.

**Question 5 - la separation avec "Les jeux" : oui, et je la transforme en condition pour qu'elle ne
se perde pas.** Sur ma capture de l'accueil, les puces sont sur un **second rang, a gauche, sur une
bande plus sombre**, et "Les jeux" reste au **premier rang, a droite** : les deux ne sont jamais
adjacents, ils sont diagonalement opposes. `ines-design` a ecarte d'elle-meme la variante ou "CM1" et
"Les jeux" finissaient cote a cote apres un retour a la ligne non maitrise, **et elle l'a ecartee pour
la bonne raison**. Je la credite et je ferme la porte derriere elle : c'est BARRE-9.

#### 5. Ce qui tient et ce qui tombe de mon arbitrage du 2026-08-07

On me demande de le dire, et c'est juste de me le demander. Je commence par ce que je dois corriger
chez moi, avant de m'appuyer sur quoi que ce soit.

**Ce qui tombe, et une partie tombait deja avant cette story.**

1. **"Une seule sortie par ecran de jeu" comme un compte tombe.** Elle tombe **par la decision de
   l'utilisatrice**, qui l'a prise en connaissance de cet arbitrage, et non par mon consentement -
   je le note pour que la trace soit exacte, pas pour marquer une reserve : c'est sa decision a
   prendre, et elle l'a prise.
2. **Et je ne peux pas invoquer contre cette barre le perimetre large que je donnais a mon propre
   motif, parce que je l'ai deja declare trop large moi-meme.** Le **2026-08-14**, section "0 bis"
   point 3 de `FICHE-bandit-manchot-additions-cp.md`, j'ai ecrit que ma clause de 2026-08-07 avait
   ete **"ecrite pour proteger l'ecran de fin de partie"** et qu'elle etait **"appliquee depuis a
   cinq ecrans"** - une extension qui etait la mienne. Je ne la ressors pas aujourd'hui contre un
   lot qui me deplait : elle est corrigee, elle reste corrigee.
3. **Mon argument de position tombe aussi, et il tombait deja.** Le 2026-08-14 j'ai mesure que sur
   une manche l'enfant ne regarde ni le coin de l'en-tete ni le haut de la colonne : il regarde
   l'enonce et les colonnes de la machine. **Cet argument ne discrimine rien ici non plus** - a une
   difference que je dis en face, parce qu'elle joue dans l'autre sens : les puces d'`ines-design`
   ne sont **pas** dans le coin superieur droit lointain, elles sont **au bord gauche, sur la ligne
   de lecture, immediatement au-dessus d'une colonne qui commence deja par une porte**. Ce n'est
   plus la meme geometrie que celle dont j'avais dit qu'elle etait hors du regard.

**Ce qui tient, et ce que j'en fais.**

1. **Ma reserve 2 du 2026-08-07 - "sur un ecran de jeu il en faut un, pas deux" - tient, parce que son
   objet est le mot, pas le compte.** Elle disait qu'un enfant qui lit **deux fois le meme mot** ne
   conclut pas qu'il y a deux chemins, il se demande lequel est le bon. "CP" et "CM1" ne sont pas un
   second nom de la sortie : **ils sont des mots differents pour une destination differente.** La
   reserve ne s'oppose donc pas a cette barre - **elle interdit en revanche qu'une puce porte le mot
   "Les jeux"**, et c'est BARRE-5.
2. **Mon constat 1 du 2026-08-07, redit le 2026-08-14, tient et c'est le point le plus grave de ce
   dossier.** Je l'avais ecrit ainsi : *"sur les cinq etats de l'ecran de jeu, la sortie est le
   premier element de la zone de contenu, au-dessus du score, de la consigne, de l'enonce"*, et
   ajoute que *"le premier mot qu'on lui donne au moment ou il vient d'echouer trois fois est une
   invitation a partir"*. **Ce defaut existe encore en production : je l'ai revu ce jour sur mes deux
   captures**, la colonne d'une manche commence par "Les jeux". Cette story le fait passer de **une
   porte a trois portes avant l'enonce**. Je n'invente pas une barre : je refuse de tripler un defaut
   que j'ai nomme deux fois et dont j'avais moi-meme ecrit le remede ("il se corrige en descendant ce
   bouton"). C'est BARRE-3.
3. **PORTE-2 du 2026-08-14 tient telle quelle** : l'ecran de fin de partie affiche exactement une fois
   le mot "Les jeux", en aplat dans la zone de contenu, a la place et a la taille qu'il a. La barre
   peut y etre visible - il n'y a plus de partie a proteger - mais elle ne devient pas la porte de
   cet ecran.
4. **PORTE-3 du 2026-08-14 tient, et je la reaffirme telle quelle** : l'ecran d'erreur garde
   "Recommencer" comme seule chose pressable. C'est BARRE-2, et voir son motif la.
5. **PORTE-5 tient** : la boite de la sortie de l'en-tete ne descend pas sous 109 x 53 px, et **les
   66 px du bandeau ne se paient pas en retrecissant une boite pressable** - plancher de cible
   44 x 44 px inchange. C'est dans BARRE-8.
6. **PORTE-1 n'a jamais ete satisfaite, et cette story la rend due.** Voir BARRE-7.

**Consequence sur mon Done du 2026-08-17 de ce matin sur le retrait de l'`aria-label`, que je date
plutot que de le laisser flotter** : ce Done vaut, comme je l'ai ecrit, **pour l'en-tete a un seul
lien**. Avec cette barre, la zone en portera trois sur l'accueil. **Un nom de zone redevient
defendable** - mais "Menu principal" reste refuse sur ses trois autres motifs, qui ne dependaient pas
du nombre de liens, et **tout nom ajoute est un texte lu par un enfant, donc il passe par ma porte**.
Je n'en exige aucun.

#### 6. Mes neuf conditions bloquantes

Elles decrivent **ce que l'enfant doit voir**. Les moyens techniques appartiennent a `theo-archi` et
je n'en choisis aucun. Chacune se verifie sans moi.

- **BARRE-1. La barre de niveaux n'est pas visible sur l'ecran de confirmation avant abandon.** Motif :
  cet ecran pose **une** question et offre **deux** reponses - c'est l'ecran dont j'ai accepte la forme
  le 2026-08-14 sous la condition unique des 44 px entre les deux boutons, precisement parce que
  l'enfant doit choisir entre **deux** gestes. Deux puces de niveau y ajoutent **deux reponses de
  plus, dont l'une est le geste meme qui vient d'ouvrir l'ecran** : un enfant qui a presse "CP" par
  megarde, a qui l'on demande de confirmer, et qui retrouve "CP" pressable sur l'ecran de
  confirmation, n'a aucun moyen de comprendre ce qu'il est en train de faire. **Verification :
  capture a 360 px de l'ecran de confirmation, exactement deux boites pressables a l'image.**
- **BARRE-2. La barre de niveaux n'est pas visible sur l'ecran d'erreur.** C'est PORTE-3 du
  2026-08-14, reaffirmee sans un mot de plus : "Recommencer" y est la seule chose pressable. Motif :
  c'est l'ecran ou l'enfant est le plus perdu, et ou j'ai deja constate que le mot lui-meme n'est pas
  dechiffrable au CP ; y ajouter deux boites augmente le choix la ou il a le moins les moyens de
  choisir. **Ceci ne contredit pas l'arbitrage de l'utilisatrice** : elle a tranche pour un ecran de
  jeu et pour une partie en cours, et sur l'ecran d'erreur il n'y a **plus de partie**. Si elle veut
  la barre la aussi, c'est sa decision et il suffit de l'ecrire ; je nomme le cout, je ne le lui
  impose pas. **Verification : capture a 360 px de l'ecran d'erreur, une seule boite pressable a
  l'image.**
- **BARRE-3. Sur les trois etats de manche, l'enfant ne lit pas plus d'une porte avant l'enonce.**
  Aujourd'hui il en lit une, et elle est deja de trop - c'est mon constat de 2026-08-07. Avec la barre
  et sans rien d'autre, il en lira **trois**. **Verification : capture a 360 px de la manche fraiche
  et de l'etat de revelation ; compter les boites qui quittent l'activite au-dessus de l'enonce ; au
  plus une.** Le chemin le moins couteux, que je **nomme sans le prescrire** parce que c'est mon
  propre remede de 2026-08-07 : **descendre la porte du fragment sous les propositions** plutot que
  de la retirer. Aucun composant neuf, et l'ecran de fin de partie n'est pas touche.
- **BARRE-4. Aucun disque, aucune pastille, aucun symbole a l'interieur d'une puce de niveau.** Elle
  porte "CP" ou "CM1" et rien d'autre. Precedent : ma reserve datee du 2026-08-11 sur `.demarrer` et
  `.encore`. Reserver l'espace en CSS pour un futur pictogramme de matiere est accepte ; y afficher
  quelque chose ce sprint ne l'est pas. **Verification : capture a 360 px, les puces ne contiennent
  que du texte.**
- **BARRE-5. Une puce de niveau ne porte jamais le mot "Les jeux" ni aucun second nom de la sortie, et
  le mot "Les jeux" n'apparait au plus qu'une fois par ecran.** Ma reserve 2 du 2026-08-07 et PORTE-2
  du 2026-08-14, inchangees. **Verification : sur chacun des cinq etats, compter les occurrences du
  mot dans le document servi, commentaires HTML retires** - la precaution est de moi, je m'y suis
  fait prendre ce matin meme.
- **BARRE-6. Une puce de niveau n'est jamais plus saillante qu'une action de l'ecran ou elle
  s'affiche**, sur les trois composantes que le depot compare deja (taille de police, hauteur
  minimale, rembourrage), face a **toutes** les familles d'action existantes. Je demande la propriete,
  pas l'attribut : si le moyen retenu la sort du perimetre du garde-fou existant, le garde-fou est
  etendu **dans le meme commit** que le gabarit. **Verification : la regle executable, verte sur
  materiau reel.**
- **BARRE-7. La boite "Les jeux" de l'en-tete ne doit plus rendre un ecran identique a celui qui
  l'affiche.** C'est PORTE-1 du 2026-08-14, **jamais satisfaite**, et je l'ai reverifiee aujourd'hui
  plutot que de la citer : depuis l'accueil, cette boite rend une liste dont le texte est exactement
  celui deja affiche, **les memes onze boutons de jeu**. Elle devient due **avec** cette story et non
  apres, pour un motif qui n'existait pas quand je l'ai ecrite : cette story installe, **dans la meme
  zone**, deux boites qui, elles, repondent. Un enfant apprend d'un voisin - deux boutons cote a cote,
  l'un fait quelque chose, l'autre non, et c'est du second qu'on lui dit qu'il est la sortie. **Je ne
  durcis pas cette condition d'un mot** : ses deux issues acceptables restent celles du 2026-08-14 -
  cette boite disparait de l'accueil, ou elle y fait quelque chose - et le choix est celui de
  `camille-po`. **Verification : appuyer sur cette boite depuis l'accueil et constater que l'ecran
  rendu differe de celui qui l'affichait, ou constater qu'elle n'y est plus.**
- **BARRE-8. La barre n'ajoute qu'un seul rang, et les 66 px ne se paient sur rien.** A 360 px, le
  bandeau tient sur une ligne : **151 px d'en-tete au total, +66 px mesures, et pas un pixel de
  plus**. **Un second rang est un arbitrage prealable neuf** - je ne l'accorde pas d'avance, ni pour
  s15, ni pour un libelle qui s'allongerait. Et aucune boite pressable n'est retrecie pour recuperer
  cette hauteur : plancher de cible **44 x 44 px** partout, **109 x 53 px** pour la sortie de
  l'en-tete (PORTE-5). **Verification : capture a 360 px de l'accueil et d'un ecran de jeu, bandeau
  sur un rang, et les boites mesurees au-dessus de leurs planchers.**
- **BARRE-9. Les puces de niveau et la sortie "Les jeux" ne partagent jamais le meme rang**, a aucune
  largeur, et pas davantage a 360 px apres un retour a la ligne. C'est la variante qu'`ines-design` a
  ecartee d'elle-meme ; je la ferme pour qu'un `flex-wrap` ne la ramene pas par accident. **Motif** :
  deux boites de forme voisine, cote a cote, dont l'une change de niveau et l'autre quitte le jeu, est
  exactement la confusion que `camille-po` me demande d'eviter. **Verification : capture a 360 px de
  l'accueil, les puces et "Les jeux" sur deux rangs distincts.**

**BARRE-1, BARRE-2 et BARRE-3 sont les trois qui portent ma reponse "oui" a `camille-po`.** Sans
elles, ma reponse est non. BARRE-4 a BARRE-6 et BARRE-9 protegent ce que la forme d'`ines-design`
obtient de bien. BARRE-7 et BARRE-8 sont deux conditions deja ecrites que cette story rend dues.

#### 7. Mes cinq reserves non bloquantes

- **R-BARRE-1. Ma reserve J des grands nombres empire avec la barre, et je l'ai mesure.** Sur l'item
  "Décompose 305 421 en valeurs de position.", la deuxieme proposition passe de **coupee au pli** a
  **reduite a une bande de quelques pixels**. La barre ne cree pas ce defaut, elle l'aggrave. Non bloquant, parce que le
  defaut est deja ouvert et que je ne fais pas payer a cette story une dette qui n'est pas la sienne.
  **Motif de story pour `camille-po`**, et le chiffre a viser est connu : aucun item ne doit placer une
  proposition sous le pli a 360 x 640.
- **R-BARRE-2. L'accueil non filtre perd deux jeux de visibilite** (de 8 a 6 entierement visibles sur
  11). Non bloquant pour deux raisons que je donne toutes les deux : defiler une liste est un geste
  normal, et **le filtre repaie ce cout des qu'il sert** (section 3).
- **R-BARRE-3. L'ecran de confirmation ouvert par une puce de niveau fait confirmer par un bouton qui
  dit "Les jeux" alors que l'enfant a presse "CP", et la liste qui apparait ensuite est filtree.** Le
  mot et le resultat ne se recouvrent pas exactement. **Je ne demande pas de changer ce mot** : ce
  serait un second vocabulaire de sortie, que j'ai refuse et que je continue de refuser. Ce qui
  repare l'ecart est deja dans la forme d'`ines-design` - **la puce active, visible sur la liste qui
  apparait**, qui dit a l'enfant pourquoi elle est courte. A verifier sur l'increment livre.
- **R-BARRE-4. Ma reserve F11 s'enrichit et ne se leve pas.** L'ecoute au lecteur d'ecran que
  l'utilisatrice fera devra desormais couvrir **les deux puces** : ce sont deux controles de plus,
  places **avant** le score, la consigne et l'enonce dans l'ordre du document. Mon geste 1 demandait
  les mots exacts annonces pour l'unique lien de sortie de l'en-tete ; il en demandera trois. Je ne
  reecris pas le protocole ici : je le noterai sur la fiche qui le porte le jour ou cette barre
  existera, pour ne pas faire ecouter un arbre qui n'est pas encore le bon.
- **R-BARRE-5. Une troisieme forme, que je n'ai pas mesuree et que je ne demande pas.** Sur un ecran
  de jeu, "Les jeux" est masque et laisse un vide au premier rang de l'en-tete : des puces posees
  **dans ce vide** pourraient couter **0 px** la ou le budget est le plus serre, et ne couter leurs
  66 px que sur l'accueil, ou j'ai montre que le cout se repaie. Je ne la retiens pas et **elle ne
  retarde pas S14-1** : elle heurte de front BARRE-9, et `ines-design` a ecarte une forme voisine pour
  une raison que je partage. **Question ouverte a `ines-design`**, a chiffrer si et seulement si
  quelqu'un veut recuperer ces 66 px sur les ecrans de jeu ; je ne la pose pas en condition.

#### 8. Ce dont je reste la seule garde, et qu'il ne faut pas croire couvert

- **Le compte des portes visibles par ecran.** Aucun garde-fou du depot ne compte les boites qui
  quittent l'activite, ni ne mesure la **visibilite** d'un element : `LEntreeDeMenuNeSurvitPasAUnEcran
  DeJeuTest` lit la **forme** d'une regle de masquage, pas ce que l'enfant voit. **BARRE-1, BARRE-2,
  BARRE-3 et BARRE-5 seront tenues par moi, en jouant, ecran par ecran** - et si l'on reecrit ce
  garde-fou pour le faire redevenir vert, il n'en gardera pas davantage.
- **L'ordre de lecture de la colonne** (BARRE-3). Il n'est encode nulle part. C'est la troisieme fois
  que je le nomme, apres le 2026-08-07 et le 2026-08-14.
- **Le budget du pli a 360 px** (BARRE-8, R-BARRE-1). Aucun test ne connait la largeur ni la hauteur
  d'un ecran. C'est moi, par capture, et c'est pour cela que je cite la commande a chaque chiffre.
- **Qu'un enfant de six ans distingue "changer de niveau" de "sortir du jeu"** - la question meme de
  `camille-po`. Mes conditions reduisent le nombre de portes et separent leurs places ; **elles ne
  prouvent pas qu'il fait la difference.** Aucun automate ne le prouvera, et moi non plus sans
  l'observer.
- **L'atteignabilite reelle et l'evitement reel d'une puce par une main de six ans.** Le trou de mon
  arbitrage du 2026-08-14, toujours ouvert, et il s'aggrave ici : les puces sont **plus faciles** a
  atteindre que la sortie d'aujourd'hui, ce qui est bon pour l'usage voulu et mauvais pour l'appui
  accidentel. La confirmation absorbe ce risque - c'est le seul endroit de ce dossier ou elle est le
  bon outil, et c'est pour cela que je ne demande rien de plus la-dessus.

#### 9. Ce que cet arbitrage ne tranche pas

- **Aucun Done pedagogique.** Celui de S14-1 sera prononce en jouant l'increment livre, sur cette
  fiche, et il pourra etre refuse pour ce que seul le rendu montre - y compris si mes neuf conditions
  sont tenues.
- **Aucun moyen technique** : ni comment la barre survit a un echange htmx, ni le vocabulaire
  `data-role`, ni ou vit le niveau courant, ni ce qu'il advient des garde-fous existants. Je decris ce
  que l'enfant voit.
- **Aucune reserve deja ouverte n'est levee** : ni la reserve J, ni la reserve O seconde moitie, ni
  F11.
- **La forme de s15** : je n'accorde d'avance aucun second rang, aucun libelle de matiere, aucun
  pictogramme de matiere. Le critere que j'appliquerai le jour ou on me les proposera est ecrit ici :
  un pictogramme n'est acceptable que s'il **retire** un effort de lecture, jamais s'il en ajoute un.
- **Le texte de l'etat filtre**, qui n'existe pas encore. Il sera juge sur l'increment livre.
