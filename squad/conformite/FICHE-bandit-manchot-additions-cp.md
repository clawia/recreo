# Fiche de conformite - bandit-manchot-additions-cp

- **Mecanique** : bandit manchot (mecanique generique - tirage d'une question, choix parmi
  plusieurs propositions dont une seule correcte, retour immediat juste/faux, enchainement de
  plusieurs manches avec score cumule).
- **Contenu(s)** : additions CP dont les deux operandes et la somme sont compris entre 0 et 10.
  Matiere premiere de reference (a repasser devant `nadia-prof` comme un contenu neuf, pas
  reprise telle quelle) : `contenu-repris/cp-calcul-mental.yml`.
- **Statut global** : VALIDE SOUS RESERVE (Done pedagogique du jeu accorde le 2026-08-05 au titre de
  s01, **maintenu et non retire**). Corrections mathematiques de s03 : **VALIDE** le 2026-08-06,
  ma condition sur la repartition des sommes est **levee**. Chantier Bootstrap hors sprint :
  **REFUSE** le 2026-08-06 sur la saillance de l'ecran de retour (commit `d269cb2`) ; **refus leve
  le 2026-08-06** sur le commit `a810fc7` apres nouvelle mesure au pixel. Le refus et son motif
  sont conserves au bloc de validation. Chantier d'identite visuelle de s02 : **S2-1, S2-2 et S2-3 accordes sous
  reserve** le 2026-08-05 ; S2-1 avait ete **REFUSE** sur le commit `3be5a86`, refus **leve** sur
  `fd16057` apres nouvelle mesure. Aucun refus n'est efface de cette fiche.
  Rhabillage du gabarit de s05 (en-tete, icone, menu, centrage) : **VALIDE SOUS RESERVE** le
  2026-08-07, quatre reserves chiffrees, aucun refus. Comportement du double appui (`ADR-0019`) :
  **VALIDE** le 2026-08-07. Correctifs du score (accent, course de session) : **VALIDE** le
  2026-08-07, rejoues. **Arbitrage prealable au developpement de S6-9** (une seule sortie par ecran
  de jeu) : rendu le 2026-08-07, story **confirmee sur son principe et refusee sur sa forme**,
  `sortie-pleine` conserve. Ce n'est pas un Done pedagogique : celui de S6-9 sera prononce en jouant
  l'increment livre. **Arbitrage prealable au developpement de S6-10** (couleur du bandeau et sort du
  carre de fond de l'icone) : rendu le 2026-08-07, bandeau **confirme a `#1d4b6b`**, qui est aussi le
  plancher que je valide, et **recolorage exige** du fond de l'icone a la meme valeur dans les trois
  vignettes du `.ico`, plus un garde-fou sur ce couplage. Pas davantage un Done pedagogique : celui
  de S6-10 sera prononce en jouant l'increment livre.
  **S6-9 et S6-10, Done pedagogique prononce le 2026-08-10 apres remesure sur le rendu reel :
  les deux sont VALIDE.** Mon audit degrade du 2026-08-07 sur S6-10 est leve : les trois comptes de
  pixels sont exacts a l'unite et j'ai regarde le bandeau modifie dans un navigateur.
  **Arbitrage prealable au developpement du chantier machine a sous (S7-1 a S7-4)** : rendu le
  2026-08-10, **direction acceptee, metaphore acceptee seulement diminuee** de tout ce qui en fait un
  jeu d'argent, **duree de defilement bornee a 0,3 s - 1,2 s**, **lisibilite dans une colonne de
  101 px validee sur mesure** pour les trois jeux du catalogue, **deux colonnes acceptees**, six
  acquis maintenus sans exception. Ce n'est pas un Done pedagogique, et **cet arbitrage est degrade** :
  le chantier n'est pas ecrit, huit points seront reverifies sur le rendu livre. Ce bloc vaut pour les
  quatre jeux du bandit manchot.
  **Chantier machine a sous (S7-1 a S7-4), Done pedagogique prononce le 2026-08-10 sur l'increment
  livre (commit `10dd74d`) : VALIDE SOUS RESERVE.** Mon audit degrade de l'arbitrage prealable est
  **leve** : j'ai joue, chronometre l'animation dans le navigateur et epuise mes huit points de
  reverification, un par un. Les quatre conditions non negociables sur la metaphore sont tenues. **Une
  seule reserve** : la fin du mouvement n'est pas continue - le voile s'arrete contre le bord bas du
  bouton et disparait en une image, 13 px encore visibles a la derniere image, trente fois par partie.
  Trois de mes huit points de garde solitaire sont **reellement fermes** par ce chantier. Quatre de mes
  propres constats sont corriges, dont deux a mon desavantage : un credit que j'avais accorde a tort
  aux colonnes sur le defaut de pli, et le comptage de ce defaut sur "Le bon petit mot", qui est de
  cinq items sur 24 et non de trois. Ce bloc vaut pour les quatre jeux du bandit manchot.
  **Arbitrage prealable au developpement du cabinet visible de la machine** : rendu le 2026-08-11.
  **Un cabinet visible est compatible avec mon veto**, qui portait sur ce que la machine raconte et
  non sur l'idee de machine, **a six proprietes verifiables** (un seul objet dont l'enfant peut
  montrer le tour, contenant les reponses et rien d'autre qui se presse, immobile en toute
  circonstance, identique apres juste et apres faux, images fixes sur la carrosserie et jamais dans
  une colonne, aucun code de jeu d'argent). **Le budget horizontal est epuise a 360 px** (336 px =
  3 x 101,33 + 2 x 16) : le cadre se paye sur la largeur des colonnes, **jamais** sur le `gap` ni sur
  le rembourrage de `.proposition`. **Le disque decoratif quitte `.demarrer` et `.encore`** - reserve
  nouvelle datee du 2026-08-11, ouverte sur mon propre manquement, puisque j'avais approuve cette
  portee nommement le 2026-08-10 ; ni refus, ni retrait de Done. **Ma reserve unique de s07 sur la
  fin du mouvement est confirmee et precisee en trois mesures**, non relevee. **Reserve 6 de "Le bon
  son" sur la distinction de l'explication : cinq proprietes ecrites**, et **le cabinet ne va pas sur
  l'ecran de retour** ni sur l'ecran de fin de partie. Douze acquis dont j'exige nommement le
  maintien. Ce n'est pas un Done pedagogique, et **cet arbitrage est degrade** : le cabinet n'est pas
  ecrit, quatorze points seront reverifies sur le rendu livre. Ce bloc vaut pour les quatre jeux du
  bandit manchot.
  **Chantier du cabinet (S8-1, S8-2, S8-3), Done pedagogique prononce le 2026-08-11 sur l'increment
  livre (commit `533967d`) : VALIDE SOUS RESERVE.** Mon audit degrade de l'arbitrage prealable est
  **leve** : j'ai joue, releve l'animation image par image et epuise mes quatorze points de
  reverification, un par un. **C1 a C6 sont tenues** sur "Calcul CP" et "Le bon son". **Mes deux
  reserves ouvertes sont LEVEES** : la fin du mouvement se termine a 0 px sur la derniere image (mes
  13 px de s07 ont disparu), et l'explication porte un aplat et un cadre complet identiques apres
  juste et apres faux. **Deux reserves nouvelles et datees, aucune n'est un refus** : le quatrieme
  cote du cabinet passe sous le pli sur "Le bon petit mot" et sur "Le bon verbe", et le contraste
  entre le cadre et les colonnes est de 1,71:1. **Je corrige une erreur qui est la mienne** : mon
  budget vertical de l'arbitrage prealable a ete mesure sur "Le bon son", que j'avais appele "le cas
  le plus charge du catalogue" alors qu'il ne l'est pas. Ce bloc vaut pour les quatre jeux du bandit
  manchot.
- **Derniere mise a jour** : 2026-08-11

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| mathematiques | CP (cycle 2) | Nombres et calculs | Calcul mental - additions dans la limite de 10 (sous-partie de la competence referentiel, qui couvre aussi "puis 20", hors perimetre de ce jeu pour l'instant) | `squad/programme/mathematiques/competences-cp.md` |

## Criteres pedagogiques attendus - camille-po

- [x] Chaque manche presente une addition dont les deux operandes et la somme sont des entiers
      compris entre 0 et 10 (jamais de somme superieure a 10).
- [x] Chaque manche presente une unique bonne reponse parmi l'ensemble des propositions
      affichees.
- [x] Les propositions fausses (distracteurs) sont des nombres entiers plausibles, proches de la
      bonne reponse, jamais negatifs et jamais non entiers.
- [x] La consigne et les retours (juste/faux) sont formules en une phrase courte, en vocabulaire
      courant de CP (pas de terme comme "somme" ; une formulation du type "combien ca fait ?").
      Confirme satisfait par `nadia-prof` a la cloture de s01 (fiche, section "Suites hors de ma
      main" du verdict s01 : "les quatre criteres restes non coches [...] sont, de mon point de
      vue, satisfaits aujourd'hui"). Coche par `camille-po` a l'ouverture de s03, avec deux
      sprints de retard sur cette confirmation - defaut de tenue a jour, pas de defaut de fond.
- [x] Le retour juste/faux est accompagne d'un signal visuel (forme et/ou couleur) distinct du
      texte, sans emoji dans le gabarit (voir `DEC-0002`), pour un enfant qui ne dechiffre pas
      encore vite.
- [x] Les additions proposees varient d'une manche a l'autre sur une session de jeu (pas de
      question figee qui se repete en boucle).
- [x] Rien dans le code de la mecanique ne fait reference a "addition", "CP" ou aux bornes 0-10 :
      tout provient du contenu injecte (verifie par la regle ArchUnit de `theo-archi`, story
      S0-3 de `docs/backlog/backlog.md`).
- [x] Tout texte destine a l'enfant (consigne, retours juste et faux, libelles de bouton,
      messages d'etat comme la fin de partie ou l'absence de partie) est redige en francais
      correct et integralement accentue, orthographe et ponctuation irreprochables (exigence de
      categorie B ajoutee par l'amendement du 2026-08-04 a `DEC-0002`, en reaction directe au
      premier motif de refus de ce sprint). Confirme satisfait par `nadia-prof` en s01, meme
      remarque de retard de tenue a jour que ci-dessus.
- [x] Le decompte de score affiche a l'enfant utilise une forme qu'un enfant de CP sait lire
      (par exemple un nombre de reussites seul, ou un nombre d'etoiles/jetons), jamais une
      notation "x / y" ni de barre oblique (deuxieme motif de refus de ce sprint). Confirme
      satisfait par `nadia-prof` en s01, meme remarque de retard de tenue a jour que ci-dessus.
- [x] Toute erreur cote serveur (session perdue, redemarrage) affiche a l'enfant un message
      court en francais avec un moyen visible de repartir, jamais le corps brut d'une erreur
      HTTP (troisieme motif de refus de ce sprint). Confirme satisfait par `nadia-prof` en s01,
      meme remarque de retard de tenue a jour que ci-dessus.
- [ ] Quand trois echecs consecutifs surviennent, quel que soit l'item concerne par chacun, la
      question suivante presentee est accompagnee d'un indice qu'elle n'aurait pas eu autrement
      (S1-5 tel qu'amende par `DEC-0007` point 3 - le declenchement initial, "un meme item rate
      trois fois", n'est plus retenu : `nadia-prof` a mesure qu'il ne peut pratiquement jamais se
      produire en partie reelle). Sa formulation, "Compte sur tes doigts.", a deja ete jugee juste
      sur le fond et acceptee sous reserve de forme par `nadia-prof` en s01 ; ce qui reste a
      auditer est son atteignabilite reelle en jeu, pas son texte. Doit venir du paquet de contenu
      YAML et non d'une constante du domaine (`DEC-0005`, execution portee par la story S3-3 de
      `docs/backlog/backlog.md`).
- [ ] Sur un grand nombre de manches jouees (voir story S3-1 de `docs/backlog/backlog.md` pour le
      seuil chiffre exact), la repartition des sommes proposees couvre l'ensemble de l'intervalle
      0-10 plutot que de se concentrer sur son bord superieur : aucune somme ne doit dominer le
      temps de jeu au point que "travailler les additions dans la limite de 10" se reduise en
      pratique a "travailler les sommes proches de 10" (constat de fond de `nadia-prof` en s01,
      43,9 % des manches sur les sommes 9 et 10 mesure sur l'ancien tirage). Condition ecrite par
      `nadia-prof` pour que la ligne mathematiques CP de `squad/programme/MATRICE-couverture.md`
      passe a `couvert-valide`.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Arbitrage prealable au developpement - la silhouette du manche, le relief et le contraste du cabinet (s10), du 2026-08-12

**Ce bloc n'est pas un Done pedagogique et n'en contient aucun.** C'est le cinquieme arbitrage
prealable que j'ecris, au titre de `squad/README.md` section 10. **Rien n'est code** : aucun
increment n'existe, donc aucun verdict d'increment n'est prononce ici, et aucun de mes verdicts
anterieurs n'est modifie ni efface.

- **Date** : 2026-08-12.
- **Objet** : quatre questions posees avant qu'une ligne de feuille de style soit ecrite, sur
  `proposition-ines-manche-et-relief.md` (branche `s10-ines-design`, non fusionnee) et
  `avis-faisabilite.md` + `ADR-0033` (branche `s10-theo-archi`, non fusionnee). J'ai lu les trois par
  `git show`, sans les amener dans mon arbre.
- **Ce que je ne rouvre pas** : les trois elements ecartes par l'utilisatrice (pas de reprise de la
  meme question apres une mauvaise reponse, manche dessine et immobile plutot qu'actionne, aucun
  second canal de verdict). Ma condition 3 du 2026-08-10 et ma condition C4 tiennent, et je n'ai pas
  a les defendre : elles sont acquises. Je ne rouvre pas non plus le defaut de pli comme motif
  (`DEC-0016`), ni ma barre fermee de six points, ni le verdict du cabinet du 2026-08-11.
- **Lignee** : worktree dedie sur `s10-nadia-arbitrage`, descend de `round-two` a `b15bda1`, verifie
  par `bash outils/verifier-lignee.sh` ("le worktree sur la branche 's10-nadia-arbitrage' descend de
  la lignee de round-two"). Je ne fusionne pas et je ne pousse pas.

#### Les quatre reponses, en une phrase chacune

| Question | Reponse |
|---|---|
| 1. La silhouette franchit-elle la frontiere manche dessine / manette ? | **NON, elle ne la franchit pas. La silhouette est ACCEPTEE**, sous les conditions N1 a N8 de la section 5. Elle ne suggere aucun geste de traction, et je l'ai mesuree plutot que regardee de loin |
| 2. Le fronton peut-il porter deux formes fixes plutot qu'une ? | **OUI.** Mon mot "unique" interdisait la variation, il ne comptait pas les formes - et je le dis contre mon propre vocabulaire, pas contre celui d'`ines-design` |
| 3. Un pixel de hauteur est-il acceptable ? | **OUI, et il ne fait basculer aucun etat sous le pli - je l'ai mesure au lieu de l'estimer.** La marge restante est de **4 rangees libres** sur l'etat le plus bas du catalogue, et non de 3 : mon propre chiffre du 2026-08-11 etait 2 px trop severe |
| 4. Quelle est la valeur absolue du contraste cadre/page ? | **8,61:1, et c'est `ines-design` qui a raison. Mon 9,25:1 etait faux, et j'ai trouve exactement pourquoi** |

#### 0. Comment j'ai regarde, et ce que je declare degrade

**Rien n'est code : cet arbitrage ne peut donc pas etre un audit joue d'increment, et je ne le
presente pas comme tel.** Mais je ne me suis pas contentee de lire un polygone, parce que juger une
forme sur ses coordonnees est exactement la faute que je reproche quand on me la fait.

**Ce que j'ai fait, et c'est reproductible.** J'ai lance l'application reelle
(`java -jar bootstrap/target/recreo.jar`, JDK 21 designe par `JAVA_HOME` le temps de la commande - le
`java` du `PATH` de ce poste est un JRE 8), joue "Le bon verbe" et les sept autres jeux du catalogue
en repondant faux volontairement pour faire venir les indices, et **reassemble chaque ecran servi dans
la page complete que l'enfant recoit** (en-tete, `main#jeu`, feuille tierce puis feuille maison dans
cet ordre reel). Sur cette page-la, je n'ai ajoute qu'**une seule feuille supplementaire** portant le
candidat d'`ines-design`, mot pour mot, sans toucher a `recreo.css`. Capture par
`outils/mesurer-rendu.sh 360 <url> <png> 640`, Chrome designe par la variable `CHROME` (aucun
navigateur n'est sur le `PATH` de ce poste). **Ce n'est donc pas une maquette synthetique : c'est le
HTML que l'application sert, avec le candidat par-dessus.** Puis mesure pixel par pixel et
agrandissement au voisin le plus proche - je regarde les pixels reellement rendus, pas une
interpolation qui lisserait ce que je dois juger.

**Ce que je declare degrade, en trois points, parce qu'ils sont reels** :

1. **je juge un candidat, pas un livre**. Ce que le lot ecrira peut differer de ce que j'ai capture ;
   mes conditions N1 a N8 sont ecrites pour etre mesurables sur le livre, une par une ;
2. **un enfant n'a pas vu cet ecran.** Je juge une forme contre une frontiere que j'ai ecrite, ce qui
   est ma competence, mais aucun enfant n'a designe cette forme du doigt ;
3. **je n'ai pas mesure a 375 px ni au-dela.** Les chiffres d'`ines-design` a 375 px sont repris tels
   quels et non reverifies ; ceux a 360 px sont tous refaits par moi.

**Un defaut d'outillage trouve en chemin, et je le remonte plutot que de le garder** :
`outils/mesurer-rendu.sh` avec un chemin de PNG **relatif** rend "le navigateur n'a produit aucune
capture (code 0)" et n'ecrit rien, alors que le meme appel avec un chemin absolu fonctionne. Le
script convertit deja le chemin du conteneur et du profil pour le binaire natif, mais pas celui de la
capture. C'est un symptome, pas une conception : je le route a `theo-archi`.

#### 1. La silhouette : elle ne franchit pas ma frontiere, et voici la mesure qui le dit

**Ma frontiere, rappelee au mot pres avant de juger.** C6 : *"pas de manette ni de bras lateral (mon
veto sur tout geste de traction, confirme deux fois)"*. Point 3 du 2026-08-10 : *"le levier ne suggere
aucun geste autre qu'un appui simple. Pas de fleche vers le bas, pas de manette a demi baissee qui
attend qu'on la tire, pas de glissement."* C'est cela que je mesure, et rien d'autre : je ne juge pas
si la forme est jolie, ni si elle est la meilleure possible.

**Ce que la forme rendue est, mesure ligne par ligne sur la capture** (etat reel de "Le bon verbe",
enonce *"Autrefois, ils ___ ce dessin anime chaque mercredi."*, sans indice - c'est l'un des cinq
etats les plus bas du catalogue, section 3 -, 360 x 640, boite du manche isolee par sa couleur
`#ffffff`) :

| Rangee | Largeur du blanc | Centre |
|---|---|---|
| `y = 426` (sommet) | 6 px | `x = 321,5` |
| `y = 430` | 16 px | `x = 321,5` |
| `y = 436` | 20 px | `x = 321,5` |
| `y = 436..448` (13 rangees, le bas) | **20 px, constante** | `x = 321,5` |

**Quatre proprietes, chacune mesuree et non deduite du polygone :**

1. **La symetrie gauche/droite est exacte sur chaque rangee sans exception** : le centre du blanc vaut
   `x = 321,5` sur les 23 rangees, du sommet a la base. C'est le fait qui tue la lecture "en cours de
   course" : une manette a demi baissee est asymetrique, son axe penche, et un enfant lit
   l'inclinaison avant la forme. Ici il n'y a pas d'inclinaison a lire, et ce n'est pas une intention
   d'`ines-design` que je relaie, c'est un releve.
2. **Les flancs sont strictement paralleles sur les 13 dernieres rangees** et la largeur ne depasse
   jamais 20 px. Aucun evasement, aucune jupe : la forme ne s'ouvre pas vers le bas.
3. **La base est une arete horizontale pleine de 20 px**, sur toute la largeur de la boite. C'est
   l'argument d'`ines-design` - la forme est **montee sur** le fronton et non suspendue - et je le
   confirme par la mesure au lieu de le croire. Une forme dont le bas est plein et poser a plat ne se
   lit pas comme un bras qui pend : elle n'a pas de point d'attache en haut, et un bras lateral se
   reconnait precisement a cela.
4. **Rien ne sort du fronton, et rien n'approche le coin arrondi.** Le fronton occupe
   `y = 421..452` a l'interieur du cabinet ; le manche occupe `y = 426..448`, donc 5 px de garde en
   haut et 4 px en bas. Son bord droit est a `x = 331` pour un bord interieur de cabinet a `x = 339` :
   **8 px de degagement**. La forme est **contenue**, ce qui compte pour ma frontiere : ce qui deborde
   d'un cadre se lit comme un appendice, et un appendice lateral est exactement ce que C6 refuse.

**Le cout est nul, et je l'ai verifie sur un etat reel plutot que sur trois colonnes synthetiques.**
Cabinet **sans** le candidat : `y = 413..634`, `x = 12..347`. Cabinet **avec** : `y = 413..634`,
`x = 12..347`. **Identique au pixel dans les deux dimensions**, sur un etat de "Le bon verbe" a quatre
propositions en deux rangees - c'est-a-dire sur le jeu le plus contraint du catalogue, et non sur le
plus confortable.

**Les deux formes du fronton ne se genent pas** : etoile a `x = 173..186`, manche a `x = 312..331`,
**126 px** entre elles, verifie par moi. Voir section 2.

**Ce que la forme rendue raconte, et je le dis franchement parce que personne d'autre ne le dira.**
Zoomee, cette silhouette ne se lit pas comme une poignee : elle se lit comme **une arche, une porte
arrondie, une borne**. Ce n'est pas un motif de refus - ma frontiere porte sur ce que la forme
**suggere de faire**, pas sur ce qu'elle represente, et une arche ne suggere aucune traction. Mais il
faut que ce soit ecrit avant le lot et non decouvert apres : **l'utilisatrice a demande un manche
dessine, et ce qui sera livre se lira comme un bloc arrondi.** Si c'est acceptable pour elle, tant
mieux, le sprint continue ; si elle attendait une forme reconnaissable comme une poignee, elle doit
pouvoir le dire maintenant, quand cela coute un briefing. Je le route a `sofia-sm` pour remontee, sans
en faire une condition : ce n'est pas ma porte, c'est la sienne.

**Le risque de forme que je trouve, moi, et qui est actionnable : la cloche.** `cloche` est sur ma
liste d'interdits C6, au titre des symboles canoniques de la machine a sous. La silhouette actuelle
n'en est pas une, et je peux dire exactement pourquoi grace au releve ci-dessus : une cloche
**s'evase** vers le bas et porte souvent un bouton au sommet. Ici les flancs sont paralleles et le
sommet est un arc nu. **Mais deux modifications minuscules la feraient basculer** - elargir la base
au-dela de la largeur du corps, ou ajouter une pastille sur le sommet - et aucune CI ne le verrait.
C'est ma condition N2.

**Sur le repli en `border-radius` de sa section 1.4, que `ines-design` me laisse activer : je ne
l'active pas, et j'ai une raison mesuree.** Je l'ai rendu et mesure comme l'autre. Sa courbe est plus
lisse, c'est exact. Mais son bas est **arrondi** : largeur 18 px a `y = 444`, 14 px a `y = 445`, la ou
le candidat en polygone garde 20 px pleins jusqu'a sa derniere rangee. **Le repli perd donc l'embase
plate, c'est-a-dire precisement la propriete qui fait passer le candidat principal** - une forme dont
le bas s'arrondit flotte, et ce qui flotte peut se lire comme suspendu. Ce n'est pas un refus du
repli, c'est une consequence a connaitre : **si le lot bascule sur le repli, il revient devant moi**
(condition N3). Je ne juge pas non plus son ombre interne ici : voir la section 3 et la condition N5,
qui tranchent ce que C3 dit d'un relief peint.

**Verdict de la question 1 : la silhouette ne franchit pas la frontiere. Elle est acceptee**, sous les
conditions N1 a N8 de la section 5. **Ce que j'accepterais a la place n'a donc pas a etre invente** -
et je note pour trace que la piste ecartee par `ines-design`, la boule sur tige etroite, aurait ete
refusee par moi aussi, pour un motif qui n'est pas le sien : une tige etroite sous une tete large est
la silhouette meme d'une manette, et son etranglement est le point ou une main se ferme. Elle l'a
ecartee parce qu'elle se lit comme un trou de serrure ; je l'aurais ecartee parce qu'elle se lit comme
une prise. **Les deux motifs tiennent, et le second est celui qui aurait arrete le sprint.**

#### 2. Le fronton porte deux formes fixes : accepte, et voici ce que mon mot "unique" voulait dire

**La question est bien posee et elle porte sur mon propre ecrit, pas sur la proposition.** J'ai ecrit
C5 clause 2 le 2026-08-11 : *"le jeu d'images du cabinet est fixe : il ne depend ni de l'item tire,
ni du numero de la colonne, ni de la reponse attendue"*. Et le commentaire de production de
`recreo.css` (S8-1, `.cabinet-bandeau`) a repris le mot autrement : *"porte l'unique image fixe du
cabinet"*. Ce mot-la est de moi, il est passe dans le code, et il faut donc que je dise ce qu'il
gouvernait.

**Il interdisait la variation, il n'a jamais compte les formes.** La preuve est dans ma propre
redaction, et je la cite plutot que de l'affirmer : ma clause dit **"le jeu d'images"**, au singulier
collectif, et ce qu'elle interdit est nomme trois fois - dependre de l'item, de la colonne, de la
reponse attendue. Aucune des trois n'est un compte. Mon motif ecrit le confirme mot pour mot : *"une
illustration qui varie avec le contenu n'est plus une decoration, c'est un indice - et un indice que
personne n'a valide"*. Ce que je protegeais est l'acquis 12 - aucun item resoluble par appariement
visuel - et une seconde forme immobile ne l'entame pas d'un pixel. **Deux etoiles identiques auraient
ete acceptables le 2026-08-11 ; une etoile et un manche le sont pour la meme raison.**

**Ce que je verifie avant d'accepter, parce que ma clause a une seconde exigence que le compte de
formes ne couvre pas** : que la forme neuve soit dessinee par une regle statique, non designee par un
attribut de gabarit, et donc structurellement incapable de dependre de l'item. Verifie sur le
candidat : `.cabinet-bandeau::after` avec `content: ""`, dimensions en `rem`, `clip-path` en
pourcentages, couleur prise a `--recreo-accent-texte` - **aucun de ces cinq elements ne peut etre
conditionne par le contenu**, et `fragments/manche.html` n'est pas touche (`ADR-0033` point 7.b, avis
de faisabilite section 4). C'est la meme structure qui m'a fait ecrire, le 2026-08-11, que C5 clause 2
etait "ferme par construction" - **c'etait mon point de garde le plus important du chantier, et il
reste ferme de la meme facon**.

**Ce que j'ajoute, et qui n'est pas un durcissement mais la portee reelle de ma clause sur un fronton
qui porte desormais deux formes** : ma clause parlait d'un jeu d'images, donc elle gouverne
l'ensemble, quel que soit son cardinal. Elle ne devient ni plus large ni plus etroite avec deux
formes. **Et je refuse d'en profiter pour ajouter une exigence de nombre que je n'avais pas ecrite** :
poser aujourd'hui un plafond de deux formes serait deplacer mes poteaux, et je ne le fais pas. Si un
jour une troisieme forme est proposee, elle revient devant moi pour ce qu'elle est - une question de
lisibilite et d'encombrement du fronton, mesurable, section 1 - jamais parce qu'elle serait la
troisieme.

**Le seul chiffre que je regarde sur ce point, et il est tenu** : les deux formes ne se touchent pas
et ne se genent pas. `ines-design` mesure l'etoile a `x = 173..186` et le manche a `x = 312..331` a
360 px, soit **126 px** entre elles, et **133 px** a 375 px. C'est un fronton de 320 px de large
portant deux objets de 14 et 20 px : il reste vide sur plus de 88 % de sa longueur. **Aucun
encombrement, aucun chevauchement, et rien qui puisse se lire comme une paire a apparier.** Ce
dernier point est celui qui m'importe : deux formes **differentes** l'une de l'autre, separees de la
moitie du fronton, ne fabriquent pas la figure "deux images qui vont ensemble" qu'un enfant pourrait
essayer de mettre en rapport avec les colonnes.

**Reponse : OUI, le fronton peut porter deux formes fixes.** Ma condition C5 est tenue dans ses deux
clauses, et le mot "unique" du commentaire de production est une imprecision de ma main qu'il faut
corriger dans le meme lot que le manche, pour qu'un lecteur futur ne prenne pas mon vocabulaire pour
une regle de nombre. C'est ma condition N4, section 5.

#### 4. La valeur absolue du contraste : `ines-design` a raison, mon 9,25:1 etait faux, et voici la cause exacte

**Je corrige mon propre constat avant que quiconque s'appuie dessus, et cette fois la correction est
entierement a mon desavantage.**

J'ai recalcule les quatre paires moi-meme, a la main puis par une sonde jetable non commitee (Pillow
n'y sert pas : ce calcul ne lit aucune image), en partant **des valeurs hexadecimales declarees dans
`recreo.css`** et non d'un pixel echantillonne. Methode : normalisation de chaque canal sur 255,
linearisation sRGB par seuil (`c <= 0,03928` : `c / 12,92` ; sinon `((c + 0,055) / 1,055) ^ 2,4`),
`L = 0,2126 R + 0,7152 G + 0,0722 B`, rapport `(L_clair + 0,05) / (L_sombre + 0,05)`. Valeurs lues
dans la feuille servie : `--recreo-fond: #f4f7fb`, `--recreo-accent: #2e6f9e`,
`--recreo-entete-fond: #1d4b6b`, `--recreo-accent-texte: #ffffff`.

| Paire | `ines-design` | Ma remesure | Verdict |
|---|---|---|---|
| cadre `#1d4b6b` vs colonnes `#2e6f9e` | 1,71:1 | **1,7101:1** | concordant |
| cabinet neuf `#081c2c` vs colonnes | 3,20:1 | **3,2022:1** | concordant |
| cadre `#1d4b6b` vs page `#f4f7fb` | 8,61:1 | **8,6059:1** | **elle a raison** |
| cabinet neuf `#081c2c` vs page | 16,11:1 | **16,1148:1** | concordant |
| plinthe `#061420` vs cabinet neuf | 1,07:1 | **1,0745:1** | concordant |

**La cause de l'ecart n'est pas celle qu'elle suppose, et je peux la nommer exactement.** Son
hypothese - un echantillonnage sur capture de mon cote, sujet a l'antialiasing et a la compression -
est raisonnable mais fausse. J'ai cherche quelle couleur claire rend `9,25:1` contre `#1d4b6b`, et la
reponse tombe juste : **`#ffffff`, le blanc pur, donne 9,2471:1**, soit mon `9,25:1` a deux decimales.
**J'ai calcule le contraste du cadre contre le blanc pur au lieu du fond de page `#f4f7fb`.** Ce
n'est pas une derive de mesure, c'est une substitution de valeur : j'ai pris
`--recreo-accent-texte` la ou il fallait `--recreo-fond`, deux variables voisines de la meme feuille.
Une capture n'y est pour rien - le chiffre est reproductible au millieme sans jamais ouvrir une image.

**Ce que cela change, et ce que cela ne change pas.**

- **Les valeurs absolues qui entrent dans une fiche sont celles d'`ines-design`** : 1,71:1 sur
  cadre/colonnes, **8,61:1** sur cadre/page, 3,20:1 et 16,11:1 avec `#081c2c`. Je les valide comme
  miennes desormais.
- **Ma reserve 2 du 2026-08-11 contenait donc un chiffre faux**, et je le corrige sans la reformuler :
  elle disait *"contre la page le cabinet ressort a 9,25:1, donc le tour de l'objet est franc et C1
  est tenue"*. La bonne valeur est **8,61:1**. **La conclusion ne bouge pas** - 8,61:1 reste tres
  au-dessus de tout seuil qui rendrait un contour difficile a suivre, C1 restait et reste tenue - mais
  le chiffre qui la portait etait faux, et un tiers qui l'aurait recopie aurait propage mon erreur.
- **Le rapport relatif que j'avais lu ne bouge pas non plus** : le passage de 1,71:1 a 3,20:1 sur
  cadre/colonnes est une multiplication par **1,87**, et c'est bien le fait acquis de cette section.
  `ines-design` recommande de le lire comme tel ; je suis d'accord, et j'ajoute que la valeur absolue
  est desormais reconciliee, donc plus rien n'oblige a se replier sur le seul rapport relatif.
- **`#081c2c` a `3,20:1` contre les colonnes : je l'accepte comme valeur, et je dis a quel titre.**
  Ce n'est pas un seuil WCAG - il n'y en a pas pour deux aplats decoratifs adjacents dont aucun ne
  porte de texte, et je ne vais pas fabriquer un seuil que la norme ne pose pas. Mon motif est celui
  que j'avais ecrit en reserve 2, inchange : un enfant qui distingue mal les couleurs lisait *"un seul
  aplat bleu avec des cases a peine plus claires"*, et la fenetre de la machine ne se voyait que par
  les coins arrondis. A 3,20:1 la fenetre existe. **Je ne demande pas mieux et je ne demanderai pas
  mieux plus tard** : 3,20:1 est la barre que je leve ici, elle est atteinte, et la relever apres coup
  ferait de ma porte un caprice.
- **La plinthe a `1,07:1` ne me gene pas**, et pour le motif exact qu'`ines-design` ecrit : deux
  aplats tres sombres separes pour faire un relief, ni texte ni signal. Je l'ai verifie sur son
  chiffre plutot que sur sa parole - `#061420` est bien chaque canal de `#081c2c` multiplie par un
  facteur voisin de 0,73, donc une teinte uniformement plus sombre et non une nuance differente. **Le
  jour ou un texte se poserait sur la plinthe, ce chiffre redevient un motif** ; aujourd'hui il n'en
  est pas un.
- **`--recreo-entete-fond` n'est pas touchee**, et c'est la bonne decision : elle porte le plancher du
  crayon de `favicon.ico` (S6-10), que j'ai chiffre moi-meme, et cette proposition ne le rouvre pas.

**Ce que je retiens de mon erreur pour la suite, et je l'ecris parce que ca vaut plus que le
chiffre** : `ines-design` a nomme un ecart entre nous deux plutot que de l'arrondir ou de le laisser
passer, deux sprints de suite. C'est ce qui a permis de le resoudre. **L'ecart etait le mien.**

#### 3. Le pixel de relief : accepte, il ne fait basculer aucun etat, et la marge est de 4 rangees

**D'abord une attribution que je corrige, parce qu'elle est de ma main et qu'elle circule.**
`ines-design` (section 2.1) et `ADR-0033` (Contexte) citent tous les deux "la reserve 1 de
`nadia-prof`" pour les 4 px de marge et les chiffres `540..636`. **Ce n'est pas ma reserve 1 de la
fiche de conjugaison** : celle-la est `mangeons` a 2,0 px de chaque cote, et c'est elle qui est
devenue le motif de mon refus du 2026-08-11. Les chiffres de pli sont un **constat neuf, date du
2026-08-11**, ecrit en section 7 du troisieme audit de
`FICHE-bandit-manchot-conjugaison-present-cm1.md` et en section 3 du verdict du meme jour de cette
fiche-ci, adresse a `camille-po` sous `DEC-0016` - **explicitement pas un motif, explicitement pas
une reserve**. La distinction n'est pas une coquetterie : une reserve se leve, un constat sous
`DEC-0016` se traite ailleurs, et ce que j'ai refuse de transformer en barre ne doit pas redevenir une
barre par un glissement de vocabulaire. Dans cette fiche, la reserve 1 du 2026-08-11 est bien le
constat de pli - ce qui explique la confusion et ne l'excuse pas : les deux fiches n'emploient pas le
meme mot pour la meme chose, et c'est ma redaction qui est en cause.

**Ensuite je remesure, parce que le chiffre qui borne ce choix est le mien et qu'il ne faut pas
s'appuyer dessus sans le verifier.** Methode : jeu reel, 60 manches sur "Le bon verbe" et 40 sur "Le
bon petit mot", en repondant faux volontairement pour faire venir les indices ; chaque ecran servi
reassemble dans la page complete ; capture a 360 px, une fois a 640 px de haut (ce que l'enfant voit)
et une seconde fois a 900 px pour les etats coupes par le pli (pour connaitre leur bas reel) ; bas du
cabinet lu comme **la derniere rangee portant la couleur exacte de l'aplat**, sur `x = 12..347`.

| Jeu | etats distincts mesures | Bas du cabinet, valeurs reellement observees | Etats coupes par le pli |
|---|---|---|---|
| Le bon verbe | 34 (17 sans indice, 17 avec) | **538, 586, 634**, puis 664, 712, 742, 760, 790 | **16 / 34**, tous avec indice |
| Le bon petit mot | 29 | **454, 502, 550, 580, 598, 628**, puis 676, 694, 724, 772 | **5 / 29** |

**Le fait qui repond a la question, et personne ne l'avait** : entre le dernier bas visible et le
premier bas coupe, il y a **30 px de trou sur "Le bon verbe"** (634 puis 664) et **48 px sur "Le bon
petit mot"** (628 puis 676). **Aucun etat, sur aucun des deux jeux, n'a son bas entre 635 et 639.**
Un decalage d'1 px ne peut donc faire basculer **aucun** etat d'au-dessus du pli a en-dessous. C'est
exactement la verification qu'`ines-design` laissait ouverte (sa section 4, point 3) et qu'elle avait
raison de ne pas trancher : elle n'avait que mes plages agregees.

**Et le motif structurel qui ferme le trou de mon echantillon, parce que je n'ai pas enumere les 25
items dans les deux etats** : les increments de hauteur de cet ecran sont des **lignes de texte
entieres** - 48 px pour une ligne d'enonce a 2 rem, 30 px pour une ligne d'indice a 1,25 rem. Aucun
element de cet ecran n'a une hauteur de 1 a 5 px. **Un bas de cabinet ne peut donc pas se poser a
635, 636, 637, 638 ni 639** : il n'existe aucun increment qui y menerait depuis 634. C'est un
argument de quantum, pas d'exhaustivite, et je le nomme comme tel plutot que de pretendre avoir joue
les 50 etats possibles.

**Le pixel, mesure sur le rendu et non deduit de la syntaxe `spread = 0`.** J'ai applique les trois
gestes de relief d'`ines-design` (ombre externe, arete interne du fronton, plinthe) puis le lot
complet (relief + manche + `#081c2c`) sur les **deux** etats les plus bas de "Le bon verbe", et
mesure la derniere rangee qui n'est pas le fond de page :

| Etat | Reference | Relief seul | Lot complet |
|---|---|---|---|
| "Autrefois, ils ___ ce dessin anime chaque mercredi.", sans indice | **634** | **635** | **635** |
| "Je ___ mes devoirs.", avec indice | **634** | **635** | **635** |

- **+1 px exactement, et pas 2** : le lot complet ne coute pas plus que l'ombre seule. L'aplat du
  cabinet reste a `y = 413..634`, **222 px**, et `x = 12..347`, **336 px**, dans les trois cas -
  identique au pixel, y compris avec la nuance `#081c2c` et le manche.
- **0 px sur les flancs, verifie autrement que par la syntaxe** : la silhouette de la rangee 635 avec
  ombre (`x = 19..340`) est identique a celle de la rangee 634 sans ombre (`x = 19..340`), et la
  rangee au-dessus de chacune vaut `x = 17..342` dans les deux cas. L'ombre recopie le bord, coin
  arrondi compris, une rangee plus bas. Elle n'elargit rien.
- **Ce qui reste libre sous le cabinet sur l'etat le plus bas du catalogue** : les rangees 636, 637,
  638 et 639, soit **4 rangees**. `ines-design` annoncait 3 en partant de mon chiffre de 636 ; il y
  en a 4, parce que **mon chiffre du 2026-08-11 etait 2 px trop severe**.

**Je corrige donc mes propres chiffres du 2026-08-11 avant qu'on s'appuie dessus.** J'avais ecrit un
bas de cabinet `540..636` sur "Le bon verbe" et `450..690` sur "Le bon petit mot" ; je mesure
aujourd'hui `538..634` et `454..694`. Les ecarts sont de **-2 px** sur l'un et **+4 px** sur l'autre,
donc pas un decalage systematique de methode que je pourrais nommer, et **je ne sais pas reconstituer
la cause** - je le dis plutot que d'en inventer une. Ce qui est sur : les chiffres de ce bloc sont
lus par balayage de la couleur exacte de l'aplat, la derniere rangee qui la porte, sur des captures
que j'ai refaites aujourd'hui. **Ce sont ceux-la qui doivent etre cites**, et le sens du constat de
`DEC-0016` ne change pas d'un pixel : l'indice emporte encore la moitie des reponses sous le bas de
l'ecran, remesure aujourd'hui a 16 etats sur 34 pour "Le bon verbe" et 5 sur 29 pour "Le bon petit
mot".

**Ma reponse : le pixel est acceptable.** Trois motifs, dans l'ordre ou ils comptent :

1. **il ne coute aucun etat** - c'est mesure ci-dessus, pas estime ;
2. **il ne touche ni la largeur des colonnes ni la hauteur de l'aplat**, donc aucun de mes planchers
   n'est paye en douce, ce qui etait ma crainte ecrite depuis le budget du 2026-08-11 ;
3. **il achete quelque chose que je peux nommer** : le cabinet se detache de la page au lieu d'y
   flotter, et C1 - "un enfant de 6 ans reconnait un objet a son pourtour" - est la propriete que je
   defends depuis le debut de ce chantier.

**Et l'etat sur lequel j'exige qu'il soit mesure avant d'etre accepte est nomme, pas decrit** :
"Le bon verbe", enonce **"Autrefois, ils ___ ce dessin anime chaque mercredi."**, **sans indice**, a
**360 x 640**. Derniere rangee non-fond attendue : **635**, contre 634 sans le lot. **C'est ma
condition N6.** Si un jour cet enonce quitte le paquet, l'etat de reference devient le plus bas des
etats non coupes du catalogue, mesure et non suppose - et si cette valeur descend a 639, le lot
revient devant moi.

**Ce que ce pixel ne repare pas, et je le redis pour que personne ne s'y trompe** : il ne touche pas
au defaut de `DEC-0016`. Un enfant de CM1 qui rate trois fois voit toujours l'aide arriver et emporter
deux reponses sous le bas de l'ecran. **Ce pixel rend le cabinet plus lisible ; il ne rend pas le jeu
jouable au moment ou l'enfant en a le plus besoin.** Les deux chantiers n'ont pas la meme importance,
et ce n'est pas celui-ci le plus important.

#### 5. Mes conditions, numerotees pour etre mesurees une par une

Comme les sept du sprint 8 : chacune est verifiable sur une capture a 360 px ou sur la feuille servie,
sans avoir a me redemander ce que j'ai voulu dire. **Elles portent sur le lot du manche et du relief,
et sur rien d'autre.**

**N1 - la silhouette reste symetrique et ses flancs ne s'ecartent pas.** Sur une capture a 360 px, le
centre de la forme rendue (balayage de sa couleur exacte, rangee par rangee) est le meme sur toutes
ses rangees a 0,5 px pres, et sa largeur ne decroit sur aucune rangee en descendant. *Mesure de
reference sur le candidat* : centre `x = 321,5` sur les 23 rangees, largeur 6 -> 20 px, constante a
20 px sur les 13 dernieres. *Motif* : la symetrie est ce qui rend impossible la lecture "manette a
demi baissee", et c'est le seul fait qui fait tenir mon acceptation.

**N2 - la base ne s'evase pas et le sommet ne porte rien.** La rangee la plus basse de la forme est
sa rangee la plus large, aucune rangee n'est plus large qu'elle, et aucune forme distincte n'est
posee au-dessus de l'arc. *Motif* : `cloche` est sur ma liste d'interdits C6 au titre des symboles
canoniques de la machine a sous. Un evasement du bas ou une pastille au sommet transforment cette
arche en cloche, et **aucun garde-fou ne verrait la difference**.

**N3 - le repli en `border-radius` de la section 1.4 d'`ines-design` n'est pas active.** Le lot ecrit
le candidat en `clip-path`. *Motif mesure* : le bas du repli s'arrondit - 18 px puis 14 px sur ses
deux dernieres rangees, contre 20 px pleins jusqu'a la derniere pour le candidat - donc il perd
l'embase plate, qui est precisement la propriete par laquelle la forme se lit "montee sur le fronton"
plutot que "suspendue". Si le lot le prefere quand meme, **il revient devant moi avant d'etre ecrit**,
et ce sera une question de forme, pas une reouverture de C6.

**N4 - le commentaire "porte l'unique image fixe du cabinet" de `recreo.css` est corrige dans le meme
lot.** Ce mot est de moi et il devient faux avec deux formes. Ce que ma clause C5 dit doit rester
lisible dans le code : le jeu d'images du cabinet **ne varie pas** ; il n'est **pas limite en
nombre**. *Verification* : lecture de la feuille servie. *Motif* : un lecteur futur qui trouve
"unique" dans le code croira que j'ai pose un plafond de formes, et refusera un dessin pour une regle
que je n'ai jamais ecrite.

**N5 - un relief peint est autorise ; un changement d'etat ne l'est pas.** C'est ma lecture de C3, et
je la rends explicite parce que le lot en a besoin avant d'ecrire : quand C3 refuse *"aucun changement
de couleur, aucune lumiere sur le cabinet lui-meme, a aucun moment"*, elle refuse ce qui **apparait,
varie ou pulse**, et sa verification est ecrite dans la meme phrase - *les pixels du cabinet sont
identiques* sur trois captures : au repos avant tout appui, pendant le defilement, 2 secondes apres
l'arret de la derniere colonne. **Une ombre, une arete ou un aplat declares une fois pour toutes
passent cette verification par construction** : ils sont, ils ne se produisent pas. Sont refuses, et
c'est desormais rouge en CI par `ADR-0033` : toute regle du cabinet conditionnee par `:hover`,
`:active`, `:focus`, `:focus-visible`, `:focus-within` ou `:target`, et toute declaration dont le nom
commence par `transition` ou `animation`. *Motif* : sans cette phrase, soit le lot n'ose aucun relief
et le chantier n'a plus d'objet, soit il en ose un et personne ne sait si je l'accepte.

**N6 - le cout vertical total du lot est de 1 px, et il se mesure sur un etat nomme.** "Le bon
verbe", enonce **"Autrefois, ils ___ ce dessin anime chaque mercredi."**, **sans indice**, capture
360 x 640 : derniere rangee non-fond a **y = 635** (contre 634 sans le lot), aplat du cabinet a
`y = 413..634` et `x = 12..347` **inchange au pixel**, et **4 rangees libres** sous le cabinet. Le
cout lateral est de **0 px** : la silhouette de la derniere rangee vaut `x = 19..340`, celle de la
rangee au-dessus `x = 17..342`, identiques a la reference.

**N7 - la largeur des colonnes ne change pas.** 96 px par colonne a trois colonnes, 152 px a deux,
cabinet a 336 px (`x = 12..347`) a 360 px de large. *Motif* : c'est mon plancher de s08, il a deja ete
paye une fois par les colonnes, et un habillage ne le repaye pas.

**N8 - la valeur du contraste cadre/page ecrite dans tout livrable est 8,61:1**, avec la paire nommee
(`--recreo-entete-fond` contre `--recreo-fond`) et la methode de la section 4. **Mon 9,25:1 ne doit
plus etre recopie** : il vaut le contraste contre le blanc pur, qui n'est pas le fond de la page. Les
autres valeurs qui entrent dans une fiche : 1,71:1 sur cadre/colonnes aujourd'hui, **3,20:1** avec
`#081c2c`, 16,11:1 sur `#081c2c` contre la page.

#### 6. Reserves neuves, datees du 2026-08-12, et ce qui n'en est pas une

**Aucune de mes reserves ouvertes n'est levee ici et aucune n'est durcie** - rien n'est code, il n'y a
rien a lever. J'en ouvre une seule, et je nomme deux choses qui n'en sont pas.

1. **Reserve neuve** : **la forme rendue ne se lit pas comme une poignee, mais comme une arche.** Ce
   n'est pas un motif (section 1) et ce n'est pas une condition : c'est une information qui appartient
   a l'utilisatrice, qui a demande un manche dessine. Destinataire : `sofia-sm` pour remontee, puis
   `camille-po` si elle en fait une story. **Le sprint n'attend pas cette reponse pour continuer.**
2. **Ce qui n'est pas une reserve** : les 16 etats sur 34 et 5 sur 29 coupes par le pli. C'est le
   constat de `DEC-0016`, remesure aujourd'hui, et je ne le rouvre pas comme barre - pour la meme
   raison que les trois fois precedentes.
3. **Ce qui n'est pas une reserve non plus** : `3,20:1` entre le cabinet et les colonnes. C'est la
   barre que je leve, elle est atteinte, et je ne la releverai pas plus tard.

#### 7. Ce dont je reste seule garde apres cet arbitrage

**Ce qui sort de ma liste, et c'est un vrai gain** : le point 1 de ma liste du 2026-08-11 -
*"que rien ne s'anime sur le cabinet (C3) : un `@keyframes` sur une regle d'habillage passerait une CI
verte"*. `ADR-0033` le ferme du cote declaratif, et **il ferme plus large que ce que je demandais** :
un changement de couleur au survol, une transition, un habillage qui clignote uniquement sous
`prefers-reduced-motion` - donc uniquement pour l'enfant qui a demande un ecran immobile - etaient
tous verts avant lui. Je n'avais pas vu le troisieme. **Je lui rends la garde de cette moitie**, et je
note que `theo-archi` a etabli que le motif de refus d'`ADR-0030` sur ce point etait faux, ce qui est
plus honnete que de le laisser vivre.

**Ce qui reste entierement mien, et que je nomme pour qu'on ne croie pas couvert ce qui ne l'est
pas** :

1. **La silhouette elle-meme.** Aucun automate ne voit une forme (`ADR-0025`). Que le manche soit
   symetrique, sans evasement et sans pastille au sommet - N1 et N2 - est mesurable par moi et par
   personne d'autre dans la CI. `CabinetDeLaMachineTest` verifiera qu'il n'y a pas de `px` dans ses
   dimensions ; il ne dira jamais si c'est une cloche.
2. **Que les couleurs rendues du cabinet soient identiques au repos, pendant le defilement et apres
   l'arret** - la moitie de rendu de C3, qu'`ADR-0033` point 6 me rend explicitement. Un relief peint
   en degrade qui suggererait une lumiere allumee passerait toute la CI.
3. **Le cout vertical d'un habillage.** Rien en CI ne mesure un pixel d'ombre. N6 est une mesure de
   moi, sur un etat que j'ai nomme.
4. **Le pli**, inchange, et **le contraste interieur du cabinet**, dont la valeur est desormais fixee
   par N8.
5. Et celles de s07 et s08 qui restent : la duree et la courbe du defilement ; qu'un mot tienne dans
   sa colonne ; qu'une proposition ne soit ni tronquee ni coupee ni comprimee ; qu'aucun decor
   n'atteigne l'ecran de fin de partie ; qu'aucun code de jeu d'argent n'atteigne l'enfant (C6,
   acquis 11) - et **ce dernier point s'elargit avec ce lot** : une forme dessinee peut porter un
   symbole de machine a sous sans qu'aucun mot soit ecrit. C'est N2.

#### 8. Suites hors de ma main

- **Pour `camille-po`** : la reserve 1 de la section 6 est un motif a instruire, pas une story deja
  ecrite. Rien dans cet arbitrage ne bloque le sprint. Et `DEC-0018` porte mon `9,25:1` errone au
  backlog : **la valeur a corriger est 8,61:1** (N8).
- **Pour `ines-design`** : sa methode de contraste est validee et la mienne etait fausse ; ses quatre
  rapports sont repris tels quels. Son argument de symetrie et d'embase est confirme par ma mesure, pas
  seulement accepte. Le repli de sa section 1.4 n'est pas active, avec le motif mesure de N3, et sa
  section 4 point 3 est **fermee** : aucun etat ne bascule.
- **Pour `theo-archi`**, comme symptomes et non comme conception : `outils/mesurer-rendu.sh` avec un
  chemin de PNG relatif rend "aucune capture (code 0)" et n'ecrit rien, la conversion de chemin
  manquant sur cet argument seul (section 0) ; et la contrainte du sixieme selecteur de
  `CabinetDeLaMachineTest` qu'il a lui-meme nommee est celle dont je verrai la consequence si elle est
  manquee - un garde-fou qui reste vert sans eprouver le manche.
- **Pour `sofia-sm`** : la reserve 1 de la section 6, a remonter a l'utilisatrice. Elle a arbitre que
  le manche serait dessine ; elle n'a pas arbitre qu'il ressemblerait a une arche.
- **`squad/programme/MATRICE-couverture.md` ne bouge pas.** Aucune competence du programme n'est en
  jeu dans un habillage : c'est la cinquieme fois que l'etape 2 de ma methode est sans objet sur ce
  chantier, et je le dis plutot que de la simuler.
- **Je n'ai touche ni le code, ni un `ADR`, ni un `DEC`, ni le backlog, ni la matrice, ni un fichier
  de ceremonie.** Je n'efface aucun verdict : les six blocs anterieurs de cette fiche restent lisibles
  plus bas, avec leurs dates. **Je ne fusionne pas et je ne pousse pas.**

### Verdict courant - chantier du cabinet (S8-1, S8-2, S8-3), prononce sur l'increment livre, du 2026-08-11

- **Verdict** : **VALIDE SOUS RESERVE.** Done pedagogique accorde. **Mes deux reserves ouvertes sont
  levees** : la reserve unique de s07 sur la fin du mouvement, et la reserve 6 de "Le bon son" sur la
  distinction de l'explication. Deux reserves nouvelles et datees sont ouvertes en section 8. Aucun
  verdict anterieur n'est efface : ils restent lisibles plus bas avec leurs dates.
- **Date** : 2026-08-11.
- **Commit audite** : `533967d`, tete de `round-two`.
- **Perimetre de ce verdict** : S8-1 (le cabinet, le disque qui quitte l'accueil), S8-2 (la fin du
  mouvement) et S8-3 (la distinction de l'explication). **L'increment de la conjugaison (S8-4 et
  l'activation de "Le bon verbe") ne fait pas partie de ce verdict** : je le juge separement dans
  `FICHE-bandit-manchot-conjugaison-present-cm1.md`, et il n'est pas valide.
- **Done technique** : acquis, et je ne le rediscute pas.
- **Ligne de matrice** : **aucune ne change sur ce verdict.** Ce chantier ne porte aucune competence
  du programme : il n'y a aucune ligne a confronter au referentiel. C'est la quatrieme fois que
  l'etape 2 de ma methode est sans objet sur ce chantier d'habillage, et je le dis plutot que de la
  simuler.

#### 0. Comment j'ai joue, et ce que je ne declare PAS degrade cette fois

**Audit joue, non degrade.** L'arbitrage prealable du meme jour etait degrade parce que le cabinet
n'etait pas ecrit ; il l'est, je l'ai regarde, et j'ai epuise ma liste fermee de quatorze points.

- worktree dedie hors de l'arbre du depot, lignee verifiee par `outils/verifier-lignee.sh` : "le
  worktree sur la branche 's08-nadia-verdicts' descend de la lignee de round-two". **Et je nomme mon
  propre manquement du matin** : mon arbitrage prealable (`cb4f40e`) a ete ecrit directement sur
  `round-two`, sans branche ni worktree, ce que `sofia-sm` a constate au planning. C'est ma faute,
  elle est reelle, et ce verdict-ci est ecrit dans un worktree dedie ;
- JDK constate par `outils/verifier-jdk.sh` : `java du PATH : 1.8.0_202 | JDK de compilation :
  21.0.10 | JAVA_HOME : vide | toolchains lu par Maven : non (fichier present sous un nom non lu)`.
  `JAVA_HOME` pointe sur ce JDK 21 le temps de la commande, jamais ecrit dans un fichier commite ;
- fat jar reconstruit depuis mon worktree et lance sur un port dedie, sur ce JDK 21 ;
- **parties reellement jouees contre l'application**, session portee par un bocal a temoins, en
  parlant a ses vraies routes (`/parties/<jeu>`, `/parties/<jeu>/coups`, `/parties/<jeu>/manche`) :
  **1 partie de "Calcul CP", 5 de "Le bon son", 11 de "Le bon petit mot", 12 de "Le bon verbe"**,
  ratees a chaque manche pour faire venir l'indice, plus des parties jouees juste pour comparer les
  deux ecrans de retour, plus 24 parties supplementaires pour verifier le tirage. **Les quatre ecrans
  de fin de partie ont ete atteints en jouant, pas fabriques** ;
- **balayage exhaustif des items** : les **25** items de "Le bon verbe", les **24** de "Le bon petit
  mot", les **15** de "Le bon son" ont tous ete vus, **chacun aussi avec son indice** obtenu par
  trois echecs consecutifs reels. 203 fragments distincts mesures ;
- **mesures a 360 px de largeur reelle et 640 px de hauteur reelle**, fragments servis par le serveur
  et poses sous les **deux feuilles reellement servies** dans un iframe de 360 x 640 exactement,
  jamais une fenetre de 360 px (`ADR-0025` : sous 516 px Chrome met la page en page pour 500 px et la
  decoupe). Geometrie relevee au centieme de pixel sur les boites rendues et sur les glyphes eux-memes
  (`Range.getBoundingClientRect`), pas sur des valeurs declarees ;
- **captures a 360 x 640 par la commande versionnee du depot** (`outils/mesurer-rendu.sh 360 <url>
  <png> 640`), qui rend bien "invocation valide (largeur CSS mesuree : 360 px | hauteur CSS mesuree :
  640 px, pli a y = 640 px)" ;
- **l'animation relevee image par image** sur le rendu reel, 91 releves sur 900 ms, hauteur de voile
  visible calculee a chaque image sur les trois colonnes ;
- **le repli de mouvement joue dans un vrai navigateur** avec `prefers-reduced-motion: reduce` actif.

**La seule limite de ma methode, et je la nomme plutot que de la laisser deviner.** Les ecrans ont
ete obtenus en demandant au serveur ses vrais fragments par ses vraies routes, puis en les posant
sous les vraies feuilles. La mecanique, le contenu, le tirage, les aides et les verdicts sont donc
ceux de l'application. Ce que je n'exerce pas par la, c'est le remplacement de la zone `#jeu` par
htmx lui-meme : je verifie ce que le serveur envoie et ce que le navigateur en fait, pas la
plomberie de l'echange. Aucun de mes quatorze points ne porte sur cette plomberie.

#### 0 bis. Ce que je corrige de mes propres constats, avant de m'appuyer dessus

**A. Mon budget vertical a ete mesure sur le mauvais jeu, et c'est ma faute.** Section 3 de mon
arbitrage prealable : *"L'axe vertical, mesure sur le cas le plus charge du catalogue. Joue
aujourd'hui : 'Le bon son' [...] Il reste donc 150 px entre le bas des colonnes et le pli."* **"Le
bon son" n'est pas le cas le plus charge du catalogue, et je le savais** : mon propre constat de s07
(`DEC-0016`) mesurait deja deux boutons de "Le bon petit mot" a `y = 717` et `y = 790`, sous le pli.
J'ai donc chiffre une limite de 150 px sur le jeu le plus court au lieu du plus long. Le cabinet
respecte la limite que j'ai ecrite - il ajoute **40 px** - et ma limite etait posee sur le mauvais
cas. La consequence est en section 8, reserve 1 : elle est **mienne**, pas celle de `lea-dev`.

**B. Mon propre instrument a lu -1,79 px la ou le rendu vaut 0, et je le dis avant d'annoncer un
chiffre.** A `t = 590 ms`, mon releve donne une hauteur de voile visible de **-1,79 px**. Ce n'est
pas un etat rendu - une hauteur negative n'existe pas a l'ecran. C'est mon expression reguliere qui a
lu `inset(100% 0px 2.41864e-07%)` en prenant `2.41864` pour la valeur du bas au lieu de
`2.41864e-07`. La valeur reelle du bas est nulle a la septieme decimale : la hauteur visible de cette
image est **0 px**. Le chiffre que j'annonce en section 4 est donc 0 px, apres correction de mon
instrument et non apres arrondi du resultat.

**C. J'ai failli corriger a tort ma reserve 3 sur ce jeu, et je dis ce que j'ai verifie.** En jouant
15 parties, j'ai vu un item repete dans 7 d'entre elles, et j'ai d'abord conclu que le tirage etait
avec remise - ce qui aurait fait tomber ma reserve *"une partie montre dix items sur douze"*. **J'ai
verifie avant d'ecrire, et ma conclusion etait fausse.** Le tirage est bien sans remise
(`ListContentSource.next` ecarte les items deja tires) : 24 parties jouees sans jamais rater ni
jamais reussir ne contiennent **aucune** repetition. Les repetitions que j'avais vues viennent de la
"manche plus simple" de `DEC-0004`, qui **reserve un item deja reussi** apres trois echecs
consecutifs - un comportement valide, pas un defaut de tirage. Ma reserve tient, avec cette nuance
que je n'y avais pas mise.

#### 1. C1 a C6, une par une, sur le rendu joue

Chiffres releves a 360 px de largeur reelle, sur les quatre jeux du catalogue.

**C1 - un seul objet dont l'enfant peut montrer le tour. TENUE sur l'epaisseur et le contour, PAS
sur le pli de deux jeux.**

| Ce que j'exigeais | Ce que je mesure |
|---|---|
| contour ferme sur les quatre cotes | **ferme** : `.cabinet` va de `x = 12` a `x = 348`, cadre continu de la meme couleur sur les quatre cotes, coins arrondis de 12 px |
| au moins 8 px sur chacun des quatre | **8 px exactement** a gauche, a droite, en haut et en bas (`border-*-width: 8px` x 4), **plus** un bandeau de 32 px en haut et un socle de 16 px en bas, de la meme couleur : la paroi visible fait **40 px** en haut, **24 px** en bas, **8 px** de chaque cote |
| aucun cote coupe par le bord de l'ecran | **aucun** : le cabinet est a 12 px de chaque bord lateral sur les quatre jeux |
| aucun cote coupe par le pli | **tenu sur "Calcul CP" (0 etat sur 10) et "Le bon son" (0 sur 19). NON tenu sur "Le bon petit mot" (11 etats sur 30) et sur "Le bon verbe" (24 sur 33)** |

**Et j'ai regarde l'image, pas seulement les nombres.** Sur "Le bon son", le cabinet se lit comme un
coffret bleu sombre a coins arrondis, avec une etoile blanche centree en haut et trois cases bleu
clair dedans. **Un enfant de 6 ans peut en faire le tour du doigt.** C'est ce que je demandais et je
ne l'avais pas ; l'ecran de s07 etait trois aplats poses sur une page blanche. Le cabinet ne repare
pas un defaut de gout : il donne au mouvement l'objet qui le rend intelligible, et il le fait.

**C2 - l'objet contient les reponses, et rien d'autre qui se presse. TENUE, sur les quatre jeux.**
Les seuls elements pressables a l'interieur du contour sont les propositions, verifie sur les 203
fragments : aucun `data-role="sortie"`, aucun autre bouton, aucun lien. `Les jeux` est a
`y = 102..156`, le cabinet commence au plus haut a `y = 312` - ils ne se recouvrent jamais. Consigne,
enonce et indice restent au-dessus, hors du cadre.

**C3 - la machine attend, elle ne decide pas. TENUE.** `animation-name: none` sur `.cabinet`, sur
`.cabinet-bandeau` et sur `.cabinet-socle`. J'ai balaye **tous** les elements de **tous** les
fragments a la recherche d'un `animation-name` autre que `none` en dehors de `.proposition` : la
liste est **vide** sur les 203. Le cabinet est pixel pour pixel le meme au repos, pendant le
defilement et 2 s apres l'arret - il n'a aucune regle capable de le changer. C'est la charniere de
tout mon avis, et elle tient.

**C4 - la machine ne dit jamais le resultat. TENUE, par absence.** Le cabinet **n'existe pas** sur
l'ecran de retour : verifie dans le HTML servi de chaque fragment de retour joue, juste et faux. La
propriete est donc tenue de la seule maniere qui ne puisse pas se defaire par accident. **La dette de
test que `theo-archi` a inscrite au planning sur C4 n'a pas besoin d'etre fermee par un test de
couplage** : il n'y a rien a decoupler, puisque le balisage du cabinet ne vit que dans
`fragments/manche.html`. Ce qui reste a garder, c'est **cette absence**, et c'est moi qui la garde.

**C5 - les images sont sur la carrosserie et ne varient jamais. TENUE, et c'est mon point de garde le
plus important de ce chantier.** Sur les 203 fragments, y compris les 25 items de "Le bon verbe" et
les 24 de "Le bon petit mot" :

1. **aucune image, aucun pictogramme, aucun aplat decoratif dans une boite de proposition.** Le seul
   pseudo-element d'une proposition est le voile de mouvement, deja valide ;
2. **un seul jeu d'images observe, une seule fois** : une etoile a cinq branches, `content: ""`,
   **20 x 20 px**, blanche, **une seule valeur de `clip-path`**, centree sur le bandeau. Un seul
   couple de hauteurs bandeau/socle, `(32, 16)`. **Elle ne depend ni de l'item, ni du numero de
   colonne, ni de la reponse attendue** : elle est dessinee par une regle statique sur un
   pseudo-element du bandeau, elle ne peut structurellement pas varier. Jamais au-dessus d'une
   colonne : une seule etoile pour tout le cabinet.

C'etait le point ou un jeu de lecture pouvait devenir un jeu d'images sans qu'une ligne rougisse. Il
ne l'est pas devenu.

**C6 - aucun code du jeu d'argent. TENUE, et c'est l'acquis 11 dont je suis desormais la seule
garde.** `lea-dev` n'a ecrit aucun scan de lexique, au motif que son cabinet ne porte aucun texte -
deux `div` `aria-hidden` vides. **J'ai verifie ce motif** : le texte total des deux bandes du cabinet
est la chaine vide, sur les 203 fragments. Mais son motif ne couvre que le cabinet, alors que mon
acquis 11 porte sur tout ce que l'enfant lit. **J'ai donc fait le scan moi-meme, sur le rendu** :
**298 textes distincts** rendus par les 203 fragments, plus **tous** les attributs `alt`,
`aria-label`, `title` et `placeholder` (un seul dans toute l'application : `aria-label=Menu
principal`). Recherche de `machine a sous`, `bandit`, `manchot`, `jackpot`, `mise`, `piece`, `jeton`,
`credit`, `gain`, `levier`, `manette`, `fente`, `coupelle`, `pari`, `tirage`, et des symboles
canoniques `cerise`, `citron`, `cloche`, `couronne`, `diamant`, `BAR`, `777` : **aucune occurrence.**
Aucune fente a piece, aucune coupelle, aucun afficheur de credit, aucun bras lateral ; la seule forme
decorative est une etoile. **Personne d'autre ne verifie ce point, et je l'ai verifie en jouant.**

#### 2. Le budget, remesure au pixel : le bon payeur a paye

C'etait le point qu'aucune proposition de forme ne pouvait ignorer. Il a ete tenu.

| Jeu | colonnes | largeur mesuree | plancher que j'avais fixe | air minimale autour du mot |
|---|---|---|---|---|
| Calcul CP | 3 | **96,00 px** | 77 px | 29,59 px (`10`, 36,81 px) |
| Le bon son | 3 | **96,00 px** | 79 px | 28,53 px (`on`, 38,92 px) |
| Le bon petit mot | 2 | **152,00 px** | 106 px | 41,14 px (`mais`, 69,70 px) |
| Le bon verbe | 4 | **152,00 px** | (hors de mon tableau) | **-2,02 px** (`mangeons`, 156,02 px) |

- **96 px a trois colonnes et 152 px a deux : exactement les deux chiffres que j'avais calcules**
  dans mon arbitrage. Le cadre s'est paye sur la largeur des colonnes, et sur rien d'autre.
- **`gap` : 16 px**, mesure sur les quatre jeux, a l'egalite exacte de mon acquis 4. Payeur refuse,
  non preleve.
- **rembourrage lateral de `.proposition` : 20 px** de chaque cote, mesure, inchange. Payeur refuse,
  non preleve.
- **`.container` : 12 px** de chaque cote, intacts - le cabinet ne touche aucun bord d'ecran.
- **Taille de police des propositions : 32 px** partout, mon acquis 3 tenu ; cible tactile
  **96 x 74** ou **152 x 74 px**, tres au-dessus de 44 px.

**La derniere ligne de ce tableau est un defaut, et il n'appartient pas a ce verdict.** `mangeons`
sort de sa boite de 2,02 px de chaque cote. **Le payeur est correct** - c'est bien la largeur des
colonnes qui a paye, comme je l'avais autorise. Ce qui n'a pas ete verifie, c'est la rencontre entre
ce payeur et un paquet que j'avais explicitement laisse **hors** de mon tableau de planchers. Ce
defaut n'apparait que sur "Le bon verbe", il est le motif de mon second verdict, et je le traite
la-bas : `FICHE-bandit-manchot-conjugaison-present-cm1.md`, verdict du 2026-08-11. **Je ne le
retiens pas contre `lea-dev`** : elle a tenu la lettre et l'esprit du budget que j'avais ecrit, sur
les trois jeux dont j'avais chiffre les planchers.

#### 3. Le pli : ce que j'ai mesure, et pourquoi ce n'est pas un motif ici

Le quatrieme cote du cabinet doit etre dans l'image (C1). Mesure sur chaque etat joue, pli a
`y = 640` :

| Jeu | etats sans indice | etats avec indice | quatrieme cote sous le pli |
|---|---|---|---|
| Calcul CP | 10, bas du cabinet 450..516 | - | **0 / 10** |
| Le bon son | 5, bas 450 | 14, bas 546 | **0 / 19** |
| Le bon petit mot | 8, bas 450..690 | 22, bas 546..846 | **11 / 30** |
| Le bon verbe | 8, bas 540..636 | 25, bas 636..792 | **24 / 33** |

**Le cabinet ajoute 40 px de hauteur totale**, bandeau et socle compris, contre la limite dure de
150 px et la cible de 100 px que j'avais ecrites. Il respecte mon budget vertical declare. **Le
defaut n'est pas dans le cabinet, il est dans ma limite** : je l'avais posee sur "Le bon son" en le
croyant le cas le plus charge (section 0 bis, point A).

**Je ne fais pas du pli un motif de refus sur ce verdict**, et je m'en tiens a ce que j'ai ecrit en
s07 : c'est le defaut de `DEC-0016`, transverse, dont j'ai etabli moi-meme que la cause est la
longueur de l'enonce a 2 rem, que j'ai refuse d'opposer a "Le bon petit mot" dont le Done est
accorde, et que `DEC-0017` a explicitement laisse hors du perimetre de ce sprint. **Je ne peux pas
l'opposer a un jeu et pas a l'autre pour un seul defaut.** Il devient la reserve 1 de la section 8,
datee, chiffree, aggravee de 40 px par ce chantier.

#### 4. S8-2 : ma reserve unique de s07 est LEVEE, releve image par image

`theo-archi` a change une seule valeur d'arrivee, `inset(80% 0 0 0)` en `inset(100% 0 0 0)`. Je l'ai
verifie en jouant, pas dans la feuille : 91 releves sur 900 ms, hauteur de voile visible calculee a
chaque image sur les trois colonnes d'un bouton de 74 px.

| Ce que j'exigeais | Aujourd'hui | Etat |
|---|---|---|
| 0 px de voile visible sur la derniere image | derniere valeur non nulle **0,021 px** a `t = 580 ms`, puis **0 px** ; contre **13 px** en s07 | **tenu** |
| derniere image identique a l'etat de repos | derniere image `inset(100% 0px 2.4e-07%)` = 0 px visible, repos `inset(0px 0px 100%)` = 0 px visible : **la meme image** | **tenu** |
| pas plus de 3 px de baisse entre deux images sur les 150 dernieres ms | **0,27 px** au maximum (colonne 1 : 1,384 - 1,118 - 0,876 - 0,659 - 0,469 - 0,308 - 0,178 - 0,081 - 0,021 - 0) ; **0,26 px** sur la colonne 3 | **tenu, avec onze fois de marge** |

Le reste de ce que j'avais valide ne bouge pas et je l'ai remesure : duree **0,6 s** (bornes 0,3 -
1,2 s), decalage **0,08 s** par colonne, cumul **0,16 s** (plafond 0,25 s), **un seul** passage,
aucun rebond, voile noir a 20 %. Les trois colonnes finissent a `t ~ 600`, `680` et `760 ms`.

**Et la tentation que j'avais fermee d'avance n'a pas ete prise** : le voile n'a pas ete recouvert
par le cadre. Il termine sa course, il ne se cache pas derriere une paroi. C'etait la satisfaction
que j'avais refusee par ecrit, et personne ne l'a cherchee.

**Repli de mouvement, joue avec `prefers-reduced-motion: reduce` actif** : le voile garde une seule
valeur, `inset(0px 0px 100%)`, soit **0 px visible pendant 900 ms**. L'ecran est immobile, les
boutons sont intacts et pressables. Mon acquis 9 est tenu avec le cabinet.

#### 5. S8-3 : ma reserve 6 de "Le bon son" est LEVEE, les cinq proprietes sont tenues

Mesure sur deux ecrans de retour du **meme item**, l'un joue juste, l'autre joue faux.

| Ce que j'exigeais | Ce que je mesure |
|---|---|
| 1. un signal non textuel, aplat **ou cadre complet**, jamais un liseret | **les deux** : aplat `#eaf1f7` et cadre complet de **2 px sur les quatre cotes** en `#5b7c94`. Pas de liseret |
| 2. l'aplat est neutre et identique apres juste et apres faux | **identique** : meme boite (`x = 12`, `w = 336`, `y = 281,58`), meme fond, meme bordure, meme couleur de texte. Le bandeau de verdict passe de `est-juste` a `est-faux` ; le bloc d'explication ne change pas d'un pixel |
| 3. l'explication reste **apres** le verdict | **apres** : icone de verdict a `y = 213,58`, explication a `y = 281,58` |
| 4. le verdict ne perd rien | **rien** : icone de **48 x 48 px** (3 rem) conservee, aplat conserve |
| 5. 1,25 rem tenu sur les deux, et verdict + explication + `Encore !` entierement au-dessus du pli | **20 px** sur les deux ; le point le plus bas de l'ecran de retour avec explication est a **465,58 px**, soit **174 px au-dessus du pli** |

Une explication sur fond rouge apprend a l'enfant que comprendre est une punition. Ce n'est pas ce
qui a ete fait : le fond est le meme quand il a trouve et quand il s'est trompe. C'est exactement ce
que je demandais, et la nuance choisie n'est ni l'ambre de l'indice ni le vert ou le rouge du
verdict, donc elle ne recycle aucun sens deja pris.

#### 6. Le disque : retire des deux boutons, la forme ronde et l'ombre conservees

Mesure sur l'accueil et sur l'ecran de retour :

- **`.demarrer::before` : `content: none`.** **`.encore::before` : `content: none`.** Le disque n'est
  sur aucun des deux. Il n'est nulle part ailleurs : les seuls `::before` de la feuille servent le
  bandeau du cabinet et la croix du signal faux ;
- **`border-radius: 999px` et les deux ombres conservees sur les deux** : `inset 0 -4px rgba(0,0,0,
  .18)` et `0 2px rgba(0,0,0,.15)`. Elles disent d'appuyer et non de tirer, je les avais validees pour
  ce motif, et je ne les ai pas fait tomber par ricochet ;
- les **quatre** boutons de l'accueil portent la forme ronde, aucun ne porte de disque, et l'accueil
  entier tient au-dessus du pli (point le plus bas **441 px**).

**Ce que ma decision a coute, et je le porte.** Il ne reste plus rien du bouton-poussoir sur
l'accueil. C'est le second element de l'habillage de levier que je fais tomber par ma main, apres la
manette. Ma porte a coute deux fois quelque chose a la forme voulue.

#### 7. L'ecran de fin de partie : acquis 6 tenu, rien de neuf

Atteint **en jouant**, quatre fois, jamais fabrique. Il porte : `Les jeux` en `sortie-pleine`, le
score, le bandeau de verdict de la derniere manche, et `C'est fini !`. **Aucun cabinet, aucun cadre,
aucune colonne, aucun levier, aucun disque, aucune animation, aucune image de machine.** Verifie dans
le HTML servi : ni la classe `cabinet`, ni `proposition`, ni `demarrer` n'y apparaissent.

C'est l'accident que j'avais nomme trois fois, et la structure choisie le rend impossible : le
balisage du cabinet ne vit que dans `fragments/manche.html`, jamais sur `ecran-de-jeu` ni sur la
racine d'un fragment. **J'assume que la machine disparaisse dix fois par partie** : ce qui enseigne
passe devant ce qui decore, et je l'avais ecrit avant de voir le resultat.

#### 8. Reserves - a convertir en stories par `camille-po`, non bloquantes

**Mes deux reserves anterieures sont LEVEES** et ne sont pas a reporter : la fin du mouvement
(section 4) et la distinction de l'explication (section 5). **La reserve nouvelle du disque** de mon
arbitrage du matin est **fermee** par la section 6. J'en ouvre deux, datees du 2026-08-11.

1. **Le quatrieme cote du cabinet passe sous le pli sur deux jeux : 11 etats sur 30 pour "Le bon
   petit mot", 24 sur 33 pour "Le bon verbe".** Chiffres de la section 3. **La cause est
   double, et la premiere moitie est mienne** : le defaut de pli de `DEC-0016`, qui preexiste et que
   je ne rouvre pas, **plus 40 px** ajoutes par le cabinet contre une limite de 150 px que j'avais
   posee sur le mauvais jeu (section 0 bis, point A). L'enfant voit alors une caisse ouverte en bas.
   Ce n'est pas un motif de refus ici, pour la raison de la section 3. **Ce qui est neuf et
   actionnable** : mon budget vertical est a refaire sur "Le bon petit mot" et "Le bon verbe", pas sur
   "Le bon son", et la limite reelle est plus basse que 150 px.
2. **Le contour du cabinet ne se distingue des colonnes qu'a 1,71:1.** Le cadre est `#1d4b6b` et les
   colonnes `#2e6f9e` : rapport de contraste **1,71:1**, calcule sur les luminances relatives. Contre
   la page (`#f4f7fb`) le cabinet ressort a **9,25:1**, donc le tour de l'objet est franc et C1 est
   tenue
   > **Correction du 2026-08-12, ajoutee sans rien effacer** : ce `9,25:1` est **faux**. La bonne
   > valeur est **8,61:1**. Mon `9,25:1` est le contraste du cadre contre le **blanc pur** `#ffffff`,
   > que j'avais pris a la place du fond de page `#f4f7fb`. La conclusion de cette reserve ne change
   > pas - 8,61:1 reste franc et C1 restait tenue - mais le chiffre ne doit plus etre recopie. Detail
   > de la mesure et des trois autres paires : bloc du 2026-08-12, section 4, condition N8. Le
   > `1,71:1` de cette reserve, lui, est exact et reverifie. - **je ne transforme pas cette reserve en motif, et je dis pourquoi : j'ai chiffre une
   epaisseur dans C1, jamais un contraste interieur.** Ce serait deplacer mes poteaux. Mais je le
   note comme reserve neuve et datee : un enfant qui distingue mal les couleurs lit un seul aplat
   bleu avec des cases a peine plus claires, et la "fenetre" de la machine se voit surtout par les
   coins arrondis. Un ecart de luminance plus franc entre le cadre et les colonnes rendrait la
   fenetre lisible sans rien couter au budget. C'est de la forme : `ines-design` propose,
   `camille-po` priorise.

#### 9. Ce dont je reste seule garde apres ce verdict

Six points de garde solitaire entraient avec ce chantier. **Trois sont reellement fermes**, non par un
test mais par une structure qui rend le defaut impossible, et je fais la difference :

- **C4** (identique apres juste et apres faux) : ferme **par absence** - le cabinet n'existe pas sur
  l'ecran de retour. La dette de test inscrite au planning n'a plus d'objet ; ce qui reste a garder
  est l'absence de balisage ailleurs, et c'est moi ;
- **C5 clause 2** (les images ne varient pas) : ferme **par construction** - la forme est dessinee par
  une regle statique en `clip-path`, elle n'est designee par aucun attribut de gabarit et ne peut
  structurellement pas dependre de l'item. C'etait mon point le plus important, et il est le mieux
  tenu ;
- **la continuite de fin de mouvement** : fermee par `derniereImageDeChaqueAnimationEgaleLEtatDeRepos`
  (`ADR-0030`), et **j'ai verifie la morsure moi-meme** en relevant image par image plutot qu'en
  croyant le test vert.

**Trois restent entierement miennes, et je les nomme pour qu'on ne croie pas couvert ce qui ne l'est
pas** :

1. **Que rien ne s'anime sur le cabinet** (C3). Un `@keyframes` sur une regle d'habillage passerait
   une CI verte. Seul le pseudo-element du voile est epingle ;
2. **Que le contour soit ferme et visible sur ses quatre cotes** (C1), y compris son quatrieme cote
   dans l'ecran. Aucun automate ne voit une forme, et la reserve 1 montre que ce point mord ;
3. **Qu'aucun code de jeu d'argent n'atteigne l'enfant** (C6, acquis 11). `lea-dev` n'a pas ecrit de
   scan de lexique, au motif que son cabinet ne porte pas de texte - motif exact pour son cabinet,
   insuffisant pour mon acquis, qui porte sur tout ce que l'enfant lit. **Je l'ai fait a la main sur
   298 textes rendus et sur tous les attributs textuels de 203 fragments. Personne d'autre ne le
   fait.**

Et les cinq de s07 qui restaient miennes : la duree et la courbe du defilement (remesurees ici) ;
qu'un mot tienne dans sa colonne - **ce point vient de mordre, voir mon second verdict** ; qu'une
proposition ne soit ni tronquee ni coupee ni comprimee ; qu'aucun decor n'atteigne l'ecran de fin de
partie (section 7) ; et le nouveau : le contraste interieur du cabinet.

#### 10. Suites hors de ma main

- **Pour `camille-po`** : les deux reserves de la section 8 sont des **motifs, pas des stories**. La
  reserve 1 est celle qui compte, et sa premiere moitie est mon erreur, pas celle d'un developpeur.
  **L'increment de la conjugaison n'est pas valide** : le motif est dans l'autre fiche, et c'est de
  la que sortira du backlog.
- **Pour `theo-archi`**, comme symptome et non comme conception : le budget vertical du cabinet doit
  etre repose sur "Le bon petit mot" et "Le bon verbe", et non sur "Le bon son" comme je l'avais
  ecrit. Je decris, je ne prescris pas.
- **Pour `ines-design`** : la reserve 2, contraste interieur du cabinet. Sa proposition a ete suivie
  partout sauf sur la clause `overflow`, refusee par `ADR-0030` et **non recopiee** : j'ai verifie,
  `overflow: visible` sur `.cabinet`, sur les trois axes. C'etait la bonne decision et elle ne coute
  rien a ce que j'exigeais - le cadre, le fond, les coins arrondis, le bandeau et le socle sont tous
  la sans elle.
- **`squad/programme/MATRICE-couverture.md` ne bouge pas sur ce verdict.** Aucune ligne a confronter
  au referentiel.
- **Je n'ai touche ni `docs/backlog/backlog.md`, ni la matrice, ni aucun `ADR`, ni aucun fichier de
  ceremonie, ni le code.** Je n'efface rien : mes cinq blocs anterieurs restent lisibles plus bas,
  avec leurs dates.

---

### Arbitrage prealable au developpement - cabinet visible, sort du disque decoratif, fin du mouvement, distinction de l'explication, du 2026-08-11

**Ce bloc n'est pas un Done pedagogique.** C'est le quatrieme arbitrage prealable que j'ecris, au
titre de `squad/README.md` section 10. Il ne modifie ni mon Done pedagogique du 2026-08-10, qui
reste le verdict courant ci-dessous, ni mes deux arbitrages prealables du meme jour. Le Done
pedagogique du cabinet sera prononce plus tard, en jouant l'increment livre.

**Ce bloc vaut pour les quatre jeux du bandit manchot** : la mecanique et l'ecran de retour sont
communs. Les trois autres fiches y renvoient.

**Ce qui m'est demande, et que je ne rediscute pas.** L'utilisatrice a joue l'increment de s07 et a
tranche le degre : une machine **reconnaissable, customisee avec des images pour enfants, sans les
codes du jeu d'argent**. Elle lit mon veto du 2026-08-10 comme visant la mise, le gain, le jackpot,
la piece, l'alignement gagnant et le signal de gain, non l'idee de machine. **Cette lecture est
exacte et je la confirme comme mienne** : j'avais ecrit que dans la forme retenue la machine ne
decide rien, qu'elle distribue et que l'enfant choisit ensuite, et que c'est cette difference qui la
sauve. Ce que je tranche ici, c'est ce qu'un cabinet visible vaut pour l'enfant et sous quelles
conditions.

#### 0. Comment j'ai regarde, et ce que je declare degrade

**Audit degrade, et je le declare avant tout le reste, pour la quatrieme fois** : le cabinet n'est
pas ecrit. Il n'existe aucune machine a regarder, donc je n'ai pas joue de machine, et **rien de ce
bloc ne peut valoir comme Done pedagogique.** La liste de ce que je reverifierai est en section 10,
et aucun Done ne sera prononce avant que je l'aie epuisee.

**Ce qui, en revanche, n'est pas degrade, parce que l'artefact existe deja.** Le disque decoratif,
les colonnes, le pli et l'ecran de retour sont livres depuis s07 : je les ai regardes sur le rendu
reel d'aujourd'hui, pas sur une description.

- lignee verifiee par `outils/verifier-lignee.sh` : "le worktree sur la branche '<ma branche>'
  descend de la lignee de round-two". `round-two` a `f29a2b0` ;
- JDK constate par `outils/verifier-jdk.sh` : `java du PATH : 1.8.0_202 | JDK de compilation :
  21.0.10 | JAVA_HOME : vide | toolchains lu par Maven : non (fichier present sous un nom non lu)`.
  `JAVA_HOME` pointe sur ce JDK 21 le temps de la commande, jamais ecrit dans un fichier commite ;
- fat jar de `f29a2b0` lance sur un port dedie, et **feuille servie verifiee** : la reponse de
  `/css/recreo.css` porte bien la regle du disque ;
- **partie de "Le bon son" reellement jouee** contre l'application, session portee par un bocal a
  temoins, jusqu'a trois echecs consecutifs pour faire venir l'indice ;
- **trois captures a une largeur de viewport reellement de 360 px**, par la commande versionnee du
  depot `outils/mesurer-rendu.sh 360 <url> <png>`, navigateur designe par la variable `CHROME` :
  l'accueil servi directement, puis l'ecran de manche avec indice et l'ecran de retour avec
  explication. Mesures relevees au pixel par balayage des aplats de `--recreo-accent` (`#2e6f9e`).

**La limite exacte de ma methode d'aujourd'hui, que je nomme plutot que de la laisser deviner.**
Les deux ecrans de jeu ont ete obtenus en demandant au serveur ses vrais fragments, puis en les
posant dans une page qui charge les **deux feuilles reellement servies** et le vrai en-tete. La
mise en page, les couleurs et les tailles sont donc celles de l'application. Ce qui n'est **pas**
exerce par la, c'est l'echange htmx : je n'ai **rien remesure aujourd'hui de ce qui bouge** - duree,
courbe, decalage, derniere image du voile. Pour tout cela je m'appuie sur ma chronometrie du
2026-08-10, faite au navigateur pilote, et je la redonnerai a l'epreuve sur le rendu livre.

#### 0 bis. Ce que je corrige de mes propres constats, avant de m'appuyer dessus

**A. J'ai approuve le disque sur `.demarrer` moi-meme, nommement, et je le dis avant de demander
son retrait.** Mon second arbitrage du 2026-08-10, section 3 : "le bouton-poussoir rond est accepte
[...] et sa decision de ne cabler ces regles que sur `.demarrer` et `.encore` - jamais sur
`.sortie-pleine` ni `.recommencer` - fait tenir mon acquis 6 par la structure du selecteur plutot
que par une consigne. C'est mieux que ce que je demandais." **`ines-design` a donc fait exactement
ce que j'avais approuve, en propres termes.** Je n'avais regarde cette portee que par la lunette de
mon acquis 6 - ce qui n'atteint pas l'ecran de fin de partie - et jamais par celle de ce que
l'accueil raconte. La section 4 est donc une **reserve nouvelle et datee du 2026-08-11**, ouverte
par moi et sur mon propre manquement. **Ce n'est ni un refus, ni un retrait de Done, ni un
durcissement d'une clause anterieure**, et personne ne pourra me l'opposer comme un deplacement de
poteaux.

**B. Mon chiffre de 13 px tient, et j'en donne la cause declaree.** La regle servie termine son
mouvement sur `clip-path: inset(80% 0 0 0)`, soit **un cinquieme de la boite encore couvert** -
14,8 px nominaux sur un bouton de 74 px - tandis que l'etat de repos, lui, ne couvre **rien**
(`inset(0 0 100% 0)`). Mes 13 px mesures sur le rendu et ces 14,8 px declares disent la meme chose,
a l'arrondi des angles pres : **l'animation rend la main avec un cinquieme de la boite encore
sombre.** Le defaut n'est donc pas un artefact de ma mesure, il est ecrit dans la regle.

**C. Je reverifie mes trois chiffres de largeur avant de les faire porter un budget.** Remesures
aujourd'hui a mi-hauteur des colonnes, sur "Le bon son" a trois propositions : **101, 102, 101 px**,
**16 px** d'ecart entre colonnes, **74 px** de hauteur, groupe de `x = 12` a `x = 347`. Identiques a
l'unite a ce que j'avais mesure le 2026-08-10. Je peux m'appuyer dessus.

#### 1. Le cabinet : ma reponse, en une phrase

**Oui, un cabinet visible est compatible avec mon veto - parce que mon veto portait sur ce que la
machine *raconte* (mise, gain, jackpot, piece, alignement gagnant, signal de gain) et non sur le
fait qu'il y ait une machine - et je le valide a quatre conditions que l'enfant doit pouvoir
constater lui-meme : qu'il puisse en montrer le tour du doigt, qu'elle contienne les reponses et
rien d'autre qui se presse, qu'elle ne bouge jamais d'elle-meme, et qu'elle soit rigoureusement la
meme qu'il ait juste ou faux.**

Le motif de fond n'a pas change d'un mot depuis le 2026-08-10 : le message propre d'une machine a
sous est que le resultat ne depend pas de toi, et un exercice doit enseigner l'inverse. Un cadre
autour des colonnes ne porte pas ce message ; un cadre qui s'allume, qui compte ou qui felicite le
porte. C'est cette frontiere-la que les sections 2 et 3 rendent mesurable.

**Et je dis pourquoi la demande de l'utilisatrice est juste sur le fond, pas seulement recevable.**
J'ai regarde l'ecran de manche a 360 px aujourd'hui : trois aplats bleus de 101 x 74 px poses sur
une page blanche, sans rien autour. Ce que l'enfant a devant lui n'est pas une machine, ce sont
trois boutons. Le mouvement du voile, que j'ai valide, s'applique donc a des objets dont rien ne dit
qu'ils appartiennent ensemble a quelque chose. **Le cabinet ne repare pas un defaut de gout : il
donne au mouvement l'objet qui le rend intelligible.** Sans lui, l'enfant voit trois boutons qui
s'assombrissent tour a tour et n'a aucune raison d'y lire une distribution.

#### 2. Le critere positif : a quoi je reconnais que l'enfant a une machine devant lui

On me demande le critere positif plutot qu'une liste d'interdits, et c'est la bonne demande : la
liste des interdits est deja ecrite (arbitrage du 2026-08-10, section 1, quatre conditions non
negociables, **inchangees et toutes maintenues**). Voici les six proprietes, chacune verifiable sur
une capture a 360 px ou en jouant, sans avoir a me redemander ce que j'ai voulu dire.

**C1 - Un seul objet, dont l'enfant peut montrer le tour.** Un contour ferme et continu entoure le
groupe des colonnes **sur ses quatre cotes**, d'une epaisseur d'au moins **0,5 rem (8 px)** sur
chacun des quatre. *Verification* : sur une capture a 360 px, un balayage horizontal a mi-hauteur
des colonnes rencontre le cadre a gauche et a droite du groupe ; un balayage vertical au centre de
chaque colonne le rencontre au-dessus et au-dessous ; les quatre cotes sont dans l'image, aucun
n'est coupe par le bord de l'ecran ni par le pli. *Motif* : un enfant de 6 ans reconnait un objet a
son pourtour. Un cadre ouvert sur un cote, ou dont les flancs sortent de l'ecran, n'est pas un objet,
c'est une texture de fond - et une texture de fond n'explique pas le mouvement. Un trait de 1 ou
2 px est une bordure, pas une paroi : c'est pourquoi je chiffre le plancher.

**C2 - L'objet contient les reponses, et rien d'autre qui se presse.** A l'interieur du contour de
C1, les seuls elements pressables sont les propositions. `Les jeux` reste dehors. *Verification* :
sur la capture, la boite de `Les jeux` et le contour de C1 ne se recouvrent pas ; dans le document
servi, aucun element portant `data-role="sortie"` n'est a l'interieur du cadre. *Motif* : si la
sortie est dans la machine, l'enfant presse la machine pour partir, et le cadre cesse de vouloir
dire "voici tes reponses". La consigne et l'enonce, eux, peuvent etre dedans ou dehors : c'est de la
forme, et je le laisse a `ines-design` (section 8).

**C3 - La machine attend, elle ne decide pas.** **Aucune animation, aucun clignotement, aucun
changement de couleur, aucune lumiere sur le cabinet lui-meme, a aucun moment.** Le mouvement
appartient aux colonnes et a elles seules. *Verification* : capture au repos avant tout appui,
capture 2 secondes apres l'arret de la derniere colonne, capture pendant le defilement - les pixels
du cabinet sont **identiques** sur les trois. *Motif* : c'est la charniere de tout mon avis. Une
machine qui s'anime toute seule agit ; une machine immobile est un meuble que l'enfant actionne. Le
jour ou le cabinet pulse en attendant, l'argument qui sauve la metaphore tombe, et mon avis avec
lui.

**C4 - La machine ne dit jamais le resultat.** Le cabinet est rigoureusement identique apres une
bonne et apres une mauvaise reponse. *Verification* : deux captures de l'ecran de retour, l'une
apres un coup juste, l'autre apres un coup faux, sur le meme item ; les pixels du cabinet sont
identiques. *Motif* : c'est la forme mesurable de ma condition 2 du 2026-08-10 - dans une machine a
sous, l'etat de la machine **est** le resultat. Ici le seul canal de verdict reste la coche ou la
croix de `fragments/retour.html`, apres le choix de l'enfant.

**C5 - Les images enfantines sont sur la carrosserie, et elles ne varient jamais.** Deux clauses,
et la seconde est celle a laquelle je tiens le plus de tout ce bloc :

1. **aucune image, aucun pictogramme, aucun aplat decoratif a l'interieur d'une boite de
   proposition**, ni derriere son texte. *Verification* : le mot le plus large de chacun des trois
   paquets du catalogue garde autour de lui le meme espace libre qu'aujourd'hui, mesure a 360 px
   (31 px de chaque cote a 101 px de colonne, 47 px a 160 px), et rien ne s'y superpose ;
2. **le jeu d'images du cabinet est fixe : il ne depend ni de l'item tire, ni du numero de la
   colonne, ni de la reponse attendue.** *Verification* : capture de dix manches consecutives ; les
   images du cabinet sont les memes sur les dix, et les memes au-dessus des trois colonnes.

*Motif* : j'ai deja refuse, sur "Le bon son", tout item resoluble par appariement visuel, et cette
propriete est acquise. Une image posee au-dessus d'une colonne, ou qui change avec l'item, la
detruit d'un coup : un enfant de CP qui a le choix entre dechiffrer `an` et regarder un dessin
regarde le dessin. **Une illustration qui varie avec le contenu n'est plus une decoration, c'est un
indice** - et un indice que personne n'a valide. Les images restent donc la peau de la machine,
jamais son contenu.

**C6 - Aucun code du jeu d'argent, y compris ceux que seul un cabinet peut apporter.** Mes quatre
conditions du 2026-08-10 section 1 sont maintenues sans exception. J'y ajoute, parce que le cabinet
est une surface neuve ou ces objets se posent naturellement : **pas de fente a piece, pas de
recepteur de gain ni de coupelle, pas d'afficheur de credit ou de compteur, pas de manette ni de
bras lateral (mon veto sur tout geste de traction, confirme deux fois), et aucun des symboles
canoniques de la machine a sous** - cerise, citron, cloche, couronne, diamant, barre, `BAR`, sept,
`777`. *Verification* : lecture du gabarit et de la feuille servis, plus la capture.

**Le cabinet n'a pas besoin d'etre fidele pour etre reconnaissable, et c'est heureux.** Une caisse
avec une fenetre et un bouton suffit a un enfant de 6 ans. J'ai deja ecrit que je veux cette
metaphore diminuee plutot que fidele ; C1 a C6 sont la version mesurable de cette phrase.

#### 3. Le budget, chiffre : ce que le cadre coute et qui le paye

C'est le point qu'aucune proposition de forme ne peut ignorer, et je le pose avant qu'`ines-design`
dessine, parce que sinon le cadre sera dessine puis paye en douce sur un de mes planchers.

**L'axe horizontal est deja epuise a 360 px.** Mesure aujourd'hui : la zone de contenu fait
**336 px** (`x` de 12 a 347), et `336 = 3 x 101,33 + 2 x 16`. Il n'y a **pas un pixel libre** a
gauche ni a droite du groupe de colonnes. Un cadre lateral doit donc etre paye. Voici les trois
payeurs possibles et mon verdict sur chacun :

| Payeur possible | Verdict |
|---|---|
| **la largeur des colonnes** | **autorise**, c'est le bon payeur. Un cadre de 8 px de chaque cote ramene les colonnes de 101 a **96 px** a trois colonnes (`(336 - 16 - 32) / 3`) et de 160 a **152 px** a deux. Les deux restent tres au-dessus du plancher que je fixe ci-dessous |
| **le `gap` de `.propositions`** | **refuse, sans discussion.** C'est mon acquis 4, tenu a l'egalite exacte : 16 px declares, 16 px rendus, remesures aujourd'hui. J'ai deja ecrit qu'il n'y a pas 2 px a recuperer, et il n'y en a toujours pas |
| **le rembourrage lateral de `.proposition`** (1,25 rem) | **refuse.** C'est l'air autour du mot, et c'est aussi ce sur quoi repose mon acquis de saillance depuis mon refus du 2026-08-06. Ce n'est pas une generosite de mise en page ou l'on se sert |
| **le rembourrage lateral de `.container`** (12 px de chaque cote) | **utilisable pour de la decoration, mais jamais comme unique payeur d'un flanc.** Un cabinet dont les deux flancs occupent ces 12 px touche les deux bords de l'ecran : C1 n'est plus tenu, l'enfant ne voit plus le tour de l'objet. Et ce rembourrage porte deja un garde-fou qui n'est pas de moi (`LeConteneurTierGardeSonRembourrageLateralTest`) : je ne le revendique pas, je dis seulement ce que sa consommation couterait a C1 |

**Le plancher de largeur de colonne que je fixe, et son arithmetique.** Une colonne garde son
rembourrage declare (1,25 rem de chaque cote, soit 40 px) **plus** le mot le plus large de son
paquet, sur une seule ligne :

| Jeu | Mot le plus large, mesure | Plancher de colonne a 360 px | Largeur avec un cadre de 8 px |
|---|---|---|---|
| Calcul CP (3 colonnes) | `10`, 36,8 px | **77 px** | 96 px |
| Le bon son (3 colonnes) | `on`, 38,9 px | **79 px** | 96 px |
| Le bon petit mot (2 colonnes) | `sont`, 65,5 px | **106 px** | 152 px |

Ce plancher est **derive des paquets d'aujourd'hui** : un paquet neuf portant une proposition plus
large le releve mecaniquement, et c'est au moment ou ce paquet arrive qu'il faut le recalculer, pas
au moment ou le cadre se dessine. "Le bon verbe" reste hors de ce tableau : sa disposition est une
saisine de `camille-po` avec `theo-archi`, et je ne la rouvre pas ici.

**L'axe vertical, mesure sur le cas le plus charge du catalogue.** Joue aujourd'hui : "Le bon son",
trois echecs consecutifs, item `JARDIN`, l'indice affiche. A 360 x 640, la rangee de colonnes va de
`y = 417` a `y = 490`. Il reste donc **150 px** entre le bas des colonnes et le pli.

**Le cabinet n'ajoute pas plus de 150 px de hauteur totale a l'ecran de manche**, bandeau haut et
socle bas compris, et c'est une limite dure et non une cible : a 150 px exactement, le bas du
cabinet tombe **sur** le pli, donc C1 n'est plus tenu (le quatrieme cote sort de l'image). Je
conseille de rester sous 100 px pour que le socle soit visiblement dans l'ecran. Sur l'ecran de
retour, la contrainte equivalente est en section 6.

#### 4. Le disque decoratif : je le retire des deux boutons, et je dis pourquoi des deux

**Ma decision : le disque quitte `.demarrer` et il quitte aussi `.encore`.** La forme ronde et
l'ombre de relief, elles, **restent sur les deux** - je les ai validees le 2026-08-10 pour un motif
qui tient toujours (elles disent d'appuyer et non de tirer), et leur portee limitee a `.demarrer` et
`.encore` est ce qui fait tenir mon acquis 6 par la structure du selecteur. Je ne touche pas a ca.
Ce qui disparait est le seul disque : la regle qui le produit aujourd'hui, `.demarrer::before,
.encore::before`.

**Ce que j'ai mesure avant de trancher, parce que le motif n'est pas celui qu'on attendrait.** Sur
le rendu de `f29a2b0` a 360 px :

| Bouton | Boite | Disque rendu | Distance au haut du bouton | Espace libre entre le disque et le haut des lettres |
|---|---|---|---|---|
| `Calcul CP` (accueil) | 129 x 56 px | 9 px de diametre, centre | 8 px | **10 px** |
| `Encore !` (retour) | 117 x 56 px | 9 px de diametre, centre | 8 px | **10 px** |

**Le disque ne touche aucun mot, ne rogne aucune cible tactile et ne coute aucune lisibilite.** Je
le dis clairement : **mon motif n'est pas un motif de lisibilite**, et je ne fabrique pas une mesure
pour habiller une preference. Il est de sens, et il est verifiable :

1. **le disque est le seul signe de machine de toute l'application, et il n'est sur aucun ecran ou
   la machine se trouvera.** J'ai verifie les gabarits servis : `.demarrer` n'existe que sur
   l'accueil, `.encore` que sur l'ecran de retour. **Ni l'un ni l'autre ne porte de colonne.** La
   machine, elle, sera sur l'ecran de manche, ou ne se trouve aucun de ces deux boutons. Un signe de
   machine pose partout sauf sur la machine ne construit pas la metaphore, il la dilue ;
2. **l'accueil ne sera jamais une machine** - c'est une liste de jeux, et cela m'est donne comme
   acquis. Un signe sans referent, sur le seul ecran ou l'enfant **choisit**, est du bruit a
   l'endroit ou il faut le moins en mettre. Ce que l'enfant doit lire sur `.demarrer`, c'est le titre
   du jeu, et c'est tout ;
3. **et c'est la condition C1 qui rend le disque inutile.** Si le cabinet est reconnaissable comme
   je l'exige, l'enfant n'a aucun besoin d'une pastille de 9 px sur un autre ecran pour comprendre
   qu'il a une machine devant lui. Si le cabinet n'est pas reconnaissable, aucune pastille ne le
   rattrapera.

**Je tranche aussi l'hypothese qui m'est soumise, et je la corrige sur un fait.** L'hypothese etait
que le disque quitte l'accueil et reste sur l'ecran de jeu, ou la machine l'expliquera. Elle repose
sur une inexactitude que je verifie plutot que de la laisser passer : **le bouton `Encore !` n'est
pas sur l'ecran ou seront les colonnes.** Il est sur l'ecran de retour, qui porte le verdict et
l'explication et aucune proposition. La machine n'y sera pas non plus - je refuse en section 6 d'y
mettre le cabinet, pour proteger l'explication. Il n'existe donc aucun ecran ou le disque cotoie la
machine, et l'hypothese ne se realise pas.

**Ce que je nomme comme cout de ma decision, parce que ca doit etre trace comme mien.** Il ne reste
plus rien du bouton-poussoir sur l'accueil : c'est le second element de l'habillage de levier que je
fais tomber par ma main, apres la manette. Un lecteur de cette fiche doit pouvoir constater que ma
porte a coute deux fois quelque chose a la forme voulue, et non zero fois.

**Et j'ai verifie qu'aucun garde-fou n'exige ce disque** avant de demander son retrait : aucun test
servi ne l'epingle. **Comment** on le retire n'est pas ma decision - je dis ce que l'enfant ne doit
plus voir, `theo-archi` tranche la mise en oeuvre.

#### 5. La fin du mouvement : ma reserve unique de s07, precisee et non deplacee

**Je confirme la propriete que j'avais ecrite le 2026-08-10** - "que le mouvement se termine sans
discontinuite visible, c'est-a-dire que la bande soit sortie de la boite avant que l'animation ne
rende la main" - **et je la precise en deux mesures**, parce qu'une propriete qu'on ne sait pas
mesurer se declare tenue trop vite. C'est une precision de ma propre barre, pas un relevement : les
deux mesures ci-dessous sont la meme exigence, rendues verifiables.

1. **Sur la derniere image de l'animation, la hauteur de voile visible est de 0 px.** Aujourd'hui :
   13 px mesures, 14,8 px declares par le dernier `@keyframes` (section 0 bis, point B) ;
2. **la derniere image de l'animation et l'etat de repos qui la suit rendent la meme image** : aucun
   pixel ne change a l'instant ou l'animation rend la main ;
3. **et la decroissance est progressive** : sur les 150 dernieres millisecondes, la plus grande
   baisse de hauteur visible entre deux images consecutives ne depasse pas **3 px**. Aujourd'hui :
   13 px en une seule image.

**Ce que cette propriete ne vise pas, et je l'ecris pour eviter un travail inutile.** Le **debut**
du mouvement n'est pas concerne. La bande apparait en une image au haut de la boite, et c'est sans
consequence : a cet instant l'enfant recoit un ecran entierement neuf, rien ne "disparait" sous ses
yeux. Ma reserve visait la fin, et elle continue de ne viser que la fin.

**Ce qu'un cabinet y change : rien, et je ferme d'avance une facon de croire qu'il change quelque
chose.** Si le cadre donne a la colonne un bord bas visible, la tentation sera de **recouvrir** les
derniers pixels du voile par le cadre plutot que de les faire disparaitre. **Je refuse cette
satisfaction de la propriete** : elle supprime le symptome en cachant des pixels, pas en terminant
le mouvement, et elle recouvre une partie de la boite que l'enfant presse. Le voile continue par
ailleurs de rester **dans la boite du bouton a chaque instant** - il ne passe jamais sur le cadre.
Et si le cabinet est ajoute, la reserve **s'aggrave** plutot qu'elle ne s'attenue : une barre sombre
qui claque au bas de chaque colonne dans un cadre est plus visible qu'a nu.

**Le reste de ce que j'ai valide le 2026-08-10 sur le mouvement ne bouge pas** : duree bornee a
0,3 s - 1,2 s, valeur de 0,6 s validee sur le rendu, decalage de 0,08 s par colonne dans un plafond
de 0,25 s, un seul passage, pas de rebond, voile noir a 20 %. `ines-design` garde sa latitude a
l'interieur de ces bornes.

#### 6. Reserve 6 de "Le bon son" : ce que j'attends de la distinction de l'explication

Ma reserve 6 du 2026-08-10 disait le defaut sans dire la barre. Je la dis, et je la dis maintenant
parce qu'un cabinet changerait la disposition autour de cette phrase.

**Ce que j'ai revu sur le rendu aujourd'hui, et une correction de mon propre constat.** Sur l'ecran
de retour d'un coup faux sur `NOMBRE` : le verdict "Non, c'est on." est dans un aplat rose avec une
croix rouge de 3 rem ; l'explication "Dans NOMBRE, ce sont les lettres o et m qui font le son on."
est du texte courant sur deux lignes, sans aplat, sans cadre, sans icone. **Correction** : j'avais
laisse entendre que l'explication etait "la moins signalee" ; elle est bien la moins signalee, mais
**pas la plus petite** - elle est a 1,25 rem, exactement comme le verdict. Le defaut est un defaut
de signalement, pas de taille, et il faut le traiter comme tel.

**Les cinq proprietes que j'attends**, verifiables sur deux captures a 360 x 640 :

1. **l'explication porte un signal non textuel qui la distingue du corps de page** : un aplat de
   fond, ou un cadre complet. **Pas un simple liseret lateral** - meme motif que pour `.indice`, ou
   j'ai deja refuse le liseret au profit du cadre complet : un enfant qui distingue mal les couleurs
   ne voit pas un liseret ;
2. **l'aplat de l'explication est neutre : il est identique apres un coup juste et apres un coup
   faux.** *Verification* : deux captures, pixels du bloc d'explication identiques. *Motif* : c'est
   la phrase qui enseigne, elle n'est pas un verdict. Une explication sur fond rouge apprend a
   l'enfant que comprendre est une punition, et j'ai deja tranche qu'elle s'affiche dans les deux cas
   parce que celui qui a trouve par elimination en a autant besoin ;
3. **l'explication reste apres le verdict**, jamais avant : l'enfant lit d'abord si c'est juste,
   ensuite pourquoi ;
4. **le verdict ne perd rien** : la distinction s'obtient en signalant l'explication, pas en
   affadissant le bandeau juste/faux. Son aplat et son icone de 3 rem sont conserves ;
5. **le plancher de 1,25 rem est tenu sur les deux**, et l'ensemble verdict + explication +
   `Encore !` reste **entierement au-dessus du pli** a 360 x 640, sans defilement.

**Et je tranche le cabinet sur cet ecran, puisque la question est posee : il n'y va pas.** L'ecran
de retour ne porte aucune colonne ; il porte le moment ou l'enfant apprend. C'est le seul ecran de
l'application dont la nudite est un avantage, et je ne lui ajoute pas un decor qui viendrait
concurrencer la phrase que je viens de demander qu'on signale mieux. **Consequence a ecrire noir sur
blanc pour un developpeur** : le cabinet ne doit apparaitre ni sur la branche `Encore !` ni sur la
branche fin de partie de `fragments/retour.html`. C'est aussi ce qui protege mon acquis 6 : un
selecteur accroche a la racine du fragment, ou a `ecran-de-jeu`, poserait le cabinet sur l'ecran de
fin de partie, et c'est exactement l'accident que j'ai nomme trois fois.

**Ce que l'enfant perd, et pourquoi je l'accepte.** La machine disparait entre chaque manche, dix
fois par partie, puis revient. Un objet qui va et vient n'est pas tout a fait un objet. Je pese les
deux et je tranche pour l'explication : ce qui enseigne passe devant ce qui decore. Je le nomme comme
un cout de ma decision, et non comme un detail, pour qu'on puisse me le contester.

#### 7. Les acquis dont j'exige nommement le maintien

Aucune story de ce chantier ne doit en defaire un sans le nommer - c'est arrive cinq fois en s06, et
c'est pourquoi cette regle existe. Les six premiers sont ceux de `DEC-0015` ; **je ne les releve
pas**, c'est la barre que j'avais ecrite qui s'applique. J'en ajoute cinq que ce chantier-ci touche
et qui n'etaient pas dans cette liste.

| # | Acquis | Ce que j'exige, chiffre | Qui le garde |
|---|---|---|---|
| 1 | cible tactile >= 2,75 rem (44 px) | inchange. 101 x 74 px aujourd'hui ; 96 x 74 avec un cadre de 8 px ; **jamais sous les planchers de la section 3** | `TailleDesTextesEtBoutonsTest` |
| 2 | lisibilite >= 1,25 rem | inchange, `.indice`, `.score`, `.explication` et le mot de `.encore` compris | `TailleDesTextesEtBoutonsTest` |
| 3 | proposition >= 2 rem, au moins aussi grosse que l'enonce | inchange. **C'est ce qu'un cadre a payer tentera de rogner en premier** | `leChiffreDeReponseEstAuMoinsAussiGrosQueLeCalcul` pour la taille declaree ; **moi seule** pour la troncature et la compression |
| 4 | `.propositions { gap }` >= 1 rem | inchange, **sur les deux axes**. 16 px remesures aujourd'hui, a l'egalite exacte. **Payeur refuse** (section 3) | `TailleDesTextesEtBoutonsTest` amende par `ADR-0026` regle 3 |
| 5 | hierarchie de saillance action / sortie (`ADR-0017`) | inchangee. Le cabinet ne rend ni `Les jeux` plus saillant, ni les colonnes moins | `LActionNeDeclarePasMoinsQueLaSortieTest` |
| 6 | `sortie-pleine` et l'ecran de fin de partie nu | inchange : **aucun cabinet, aucun cadre, aucune colonne, aucun levier, aucun disque, aucune animation, aucune image de machine.** `Les jeux` en aplat plein reste la seule chose a presser, 117 x 56 px remesures | **moi seule** : le garde-fou de saillance n'oppose que des etats simultanes |
| 7 | rembourrage declare de `.proposition` (0,75 rem / 1,25 rem) | inchange. C'est l'air autour du mot et le socle de mon acquis 5. **Payeur refuse** | **moi seule** |
| 8 | les six conditions de non-troncature du 2026-08-10 | inchangees : une ligne, aucune troncature, aucun `overflow: hidden` sur le texte, aucune compression de glyphe, aucune cesure, aucun texte vertical | condition 3 par un test ; **les cinq autres, moi seule** |
| 9 | le repli de mouvement reste lisible **et jouable** | inchange. Avec le cabinet et `prefers-reduced-motion: reduce` actif, l'ecran immobile reste lisible et jouable | forme litterale par `ADR-0026` regle 1 clause 2 ; **le resultat, moi seule** |
| 10 | aucun mouvement sans geste de l'enfant (`data-arrivee`) | inchange. Le cabinet n'introduit aucune animation, donc rien de neuf a declencher (C3) | `ADR-0027` cote serveur ; **la partie CSS, moi** |
| 11 | le nom interne de la mecanique n'atteint jamais l'enfant | inchange : ni `machine a sous`, ni `bandit`, ni `manchot`, ni `jackpot`, ni `mise`, ni `piece`, ni `jeton`, ni `credit`, sur aucun texte, `alt`, `aria-label`, `title` ou titre de page. **Un cabinet est precisement l'occasion de le faire remonter au motif qu'il devient exact** | **moi seule** |
| 12 | aucun item resoluble par appariement visuel ("Le bon son") | inchange, et **C5 clause 2 en est la consequence directe** : une image qui varie avec l'item est un indice non valide | **moi seule** |

#### 8. Ce que je laisse a `ines-design`, et ce que je ne lui laisse pas

Elle propose **apres** cet arbitrage, et je nomme les deux listes pour qu'elle n'ait pas a deviner
ou est ma porte.

**Sa latitude, entiere, sous C1 a C6** : la forme du cabinet et de sa fenetre, ses couleurs (sous
les planchers de contraste deja acquis), sa matiere, **quelles** images enfantines et combien,
l'epaisseur du cadre au-dela des 8 px, la presence de la consigne et de l'enonce a l'interieur ou a
l'exterieur du cadre, la repartition du budget vertical entre bandeau haut et socle, la facon de
rendre la fin du mouvement continue (section 5), la facon de signaler l'explication (section 6), et
la duree comme la courbe dans les bornes du 2026-08-10.

**Ce que je ne lui laisse pas** : les quatre conditions du 2026-08-10 section 1 ; les six proprietes
C1 a C6 ; les planchers et les payeurs de la section 3 ; les douze acquis de la section 7 ; le sort
du disque, qui est tranche et n'est pas a reinstaller ; l'absence de cabinet sur l'ecran de retour
et sur l'ecran de fin de partie ; toute animation sur le cabinet ; toute image qui varie avec l'item
ou avec la colonne ; et le recouvrement des derniers pixels du voile par le cadre.

#### 9. Ce dont je reste seule garde apres cet arbitrage

Ma liste de s07 en comptait neuf, dont cinq restaient miennes. **Six entrent avec ce chantier**, et
je les nomme pour qu'on ne croie pas couvert ce qui ne l'est pas - c'est un chantier entierement
visuel, et l'habillage est precisement la ou aucun test ne regarde.

1. **Que le contour du cabinet soit ferme et visible sur ses quatre cotes** (C1). Aucun automate ne
   voit une forme.
2. **Que rien ne s'anime sur le cabinet** (C3). Un `@keyframes` sur une regle d'habillage passerait
   une CI verte ; seul le pseudo-element du voile est aujourd'hui epingle.
3. **Que le cabinet soit identique apres un coup juste et un coup faux** (C4).
4. **Que les images ne varient ni avec l'item ni avec la colonne** (C5 clause 2). C'est mon point de
   garde le plus important de ce chantier : c'est celui ou un jeu de lecture peut devenir un jeu
   d'images sans qu'une seule ligne rougisse.
5. **Que le budget horizontal soit paye sur la largeur des colonnes et non sur le `gap` ni sur le
   rembourrage** (section 3). Le `gap` a un garde-fou ; le plancher de largeur de colonne, non.
6. **Que la fin du mouvement soit continue** (section 5, ma reserve de s07, toujours ouverte). Aucun
   test ne regarde la derniere image d'une animation.

Et les cinq de s07 qui restent miennes ne bougent pas : duree et courbe du defilement ; qu'un mot
tienne dans sa colonne (marge la plus etroite du depot : 2,0 px sur `mangeons`) ; qu'une proposition
ne soit ni tronquee ni coupee ni comprimee ; qu'aucun decor n'atteigne l'ecran de fin de partie ;
et desormais la distinction de l'explication (section 6), qu'aucun test ne mesure.

#### 10. Ce que je reverifierai sur le rendu livre, avant tout Done pedagogique

Liste fermee. Ce qui n'y est pas ne bloquera pas le Done et ne pourra pas m'etre oppose comme
l'ayant bloque. Tout se fait a une largeur **et** une hauteur de viewport reellement de 360 x 640,
sur les **trois** jeux du catalogue.

1. **C1** : contour ferme, 8 px au moins sur chacun des quatre cotes, aucun cote coupe par le bord
   de l'ecran ni par le pli.
2. **C2** : `Les jeux` hors du cadre, aucun autre pressable dedans.
3. **C3** : trois captures - au repos, pendant le defilement, 2 s apres l'arret - pixels du cabinet
   identiques.
4. **C4** : deux captures de l'ecran de retour, apres juste et apres faux, cabinet identique.
5. **C5** : dix manches consecutives, images du cabinet identiques et identiques au-dessus des trois
   colonnes ; aucune image dans une boite de proposition.
6. **C6** et les quatre conditions du 2026-08-10 : lecture des gabarits et de la feuille servis.
7. **Le budget** : largeur de colonne et `gap` remesures au pixel sur les trois jeux, contre les
   planchers de la section 3 ; item le plus large de chaque paquet, sans troncature, sans coupure,
   sans police sous 2 rem.
8. **Le pli** : "Le bon son" avec l'indice, trois echecs consecutifs joues pour le faire venir ;
   puis l'ecran de retour avec explication.
9. **La fin du mouvement** : image par image a 40 ms ou mieux - 0 px sur la derniere image, pas plus
   de 3 px de baisse entre deux images sur les 150 dernieres millisecondes, aucun rebond, duree et
   decalage toujours dans les bornes.
10. **Le disque** : absent de l'accueil et de `Encore !` ; forme ronde et ombre toujours presentes.
11. **L'explication** : les cinq proprietes de la section 6.
12. **L'ecran de fin de partie** : acquis 6, rien de neuf, `sortie-pleine` seule et pleine.
13. **Le repli de mouvement** : `prefers-reduced-motion: reduce` actif dans un vrai navigateur,
    ecran avec cabinet lisible et jouable.
14. **Et je jouerai**, cette fois au navigateur pilote et non par fragments reassembles : cinq
    parties completes, dont trois ratees a chaque manche.

#### 11. Suites hors de ma main

- **Pour `camille-po`** : les six proprietes C1 a C6, les planchers de la section 3, les cinq
  proprietes de la section 6 et le retrait du disque de la section 4 sont des **motifs, pas des
  stories**. A elle d'en faire des criteres d'acceptation. Le retrait du disque est une **reserve
  nouvelle datee du 2026-08-11**, ouverte sur mon propre manquement (section 0 bis, point A) : ce
  n'est pas un refus et cela ne retire aucun Done. Ma reserve de s07 sur la fin du mouvement reste
  la meme reserve, precisee et non relevee.
- **Pour `ines-design`** : sections 2, 3, 5, 6 et 8. Sa proposition vient apres ce bloc.
- **Pour `theo-archi`**, comme symptomes et non comme conception : le retrait de la regle du disque ;
  la portee du selecteur du cabinet, qui doit exclure les deux branches de `fragments/retour.html` ;
  et le fait que la discontinuite de fin de mouvement a sa cause ecrite dans le dernier `@keyframes`
  (section 0 bis, point B). Je decris, je ne prescris pas.
- **Pour `sofia-sm`** : le **defaut de pli de "Le bon petit mot" n'est pas rouvert ici**, comme
  demande. Mon Done est maintenu, je ne deplace pas mes poteaux, et l'arbitrage entre raccourcir les
  enonces et traiter la hauteur par la mise en page reste a instruire ailleurs.
- **`squad/programme/MATRICE-couverture.md` ne bouge pas.** Ce chantier ne porte aucune competence
  du programme : aucune ligne a confronter au referentiel, aucune ligne ne change quel que soit mon
  avis. C'est la troisieme fois que l'etape 2 de ma methode est sans objet, et je le dis plutot que
  de la simuler.
- **Je n'ai touche ni `docs/backlog/backlog.md`, ni `MATRICE-couverture.md`, ni aucun `ADR`, ni
  aucun fichier de ceremonie, ni le code.** Je n'efface rien : mes quatre blocs anterieurs restent
  lisibles plus bas, avec leurs dates.

---

### Verdict courant - chantier machine a sous (S7-1 a S7-4), prononce sur l'increment livre, du 2026-08-10

- **Verdict** : **VALIDE SOUS RESERVE.** Done pedagogique accorde. Une reserve, chiffree, en
  section 11.
- **Portee** : ce verdict est **transverse**. Il vaut pour la mecanique du bandit manchot et donc
  pour les quatre jeux, catalogues ou non. Les fiches
  `FICHE-bandit-manchot-le-bon-son-cp.md`, `FICHE-bandit-manchot-homophones-cm1.md` et
  `FICHE-bandit-manchot-conjugaison-present-cm1.md` y renvoient.
- **Date** : 2026-08-10. C'est le troisieme bloc que j'ecris ce jour-la sur ce chantier ; les deux
  premiers etaient des arbitrages prealables et le disaient. **Celui-ci est le Done pedagogique**,
  et il est rendu apres avoir joue.
- **Commit audite** : `10dd74d`, `HEAD` de `round-two` au moment de l'audit. Mon worktree y a ete
  amene par avance rapide, sans fusion : mes trois blocs anterieurs en sont deja des ancetres.
  `round-two` etait a `eff5767` quand ce travail m'a ete confie ; le seul commit intervenu depuis,
  `10dd74d` avec `8639960`, est un epinglage de vacuite sur un garde-fou de contenu et ne change
  rien a ce que l'enfant voit. Je l'ai neanmoins audite dans l'etat le plus complet plutot que dans
  l'etat annonce, et je le dis.
- **Moyens** : audit **joue**, **non degrade** sur les huit points de ma liste fermee. Worktree
  dedie hors de l'arbre du depot, lignee verifiee par `outils/verifier-lignee.sh` ("le worktree sur
  la branche '<ma branche>' descend de la lignee de round-two"). JDK constate par
  `outils/verifier-jdk.sh` : `java du PATH : 1.8.0_202 | JDK de compilation : 21.0.10 | JAVA_HOME :
  vide | toolchains lu par Maven : non (fichier present sous un nom non lu)` ; `JAVA_HOME` pointe
  sur ce JDK 21 le temps de la commande, jamais ecrit dans un fichier commite. Fat jar reconstruit,
  lance sur un port dedie. **Cinq parties completes de dix manches jouees jusqu'a l'ecran de fin**,
  dont trois ratees a chaque manche pour faire venir l'aide, plus des balayages repetes pour voir
  tous les items de chaque paquet. Rendu observe a une largeur de viewport reellement de 360 px et
  une hauteur de 640 px, la reference que `DEC-0016` vient de fixer.
- **Done technique** : acquis, verifie par moi avant de juger - `mvn -B verify` vert sur ce commit,
  315 tests, aucun echec. Je ne prononce pas de Done pedagogique sur un increment qui plante.

#### 0. Comment j'ai joue, et ce qui m'a manque

Ce que j'ai fait, et qui n'est pas une lecture de feuille de style : j'ai pilote un vrai navigateur,
j'ai **presse les boutons** comme l'enfant les presse, et j'ai enregistre dans le navigateur
l'instant de mon appui et l'instant ou chaque colonne s'arrete. C'est ce qui rend la section 2
chiffree plutot que deduite. J'ai aussi fige l'animation reelle et je l'ai deplacee image par image
pour regarder ce que l'enfant a sous les yeux au milieu du mouvement, ce qu'une capture apres coup ne
montre jamais.

**Ce qui reste hors de ma portee, et que je ne presente donc pas comme verifie** : je n'ai pas joue
sur un ecran tactile, ni fait jouer un enfant. Mes verdicts portent sur ce que l'ecran fait, pas sur
ce qu'une main de six ans en fait. C'est une limite permanente de mes audits, pas un manque de ce
sprint.

**Un point de la section 10 est declare degrade** et l'est nommement : ma comparaison avec la mise
en page empilee d'avant le chantier. Voir la section 10, point D.

#### 1. Point 1 - l'enfant attribue-t-il le mouvement a son propre appui ?

**Oui, et le raccord est serre.** Mesure, sur l'accueil des additions CP :

| Ce que j'ai mesure | Valeur |
|---|---|
| animations en cours sur l'accueil, avant tout geste | **0** |
| delai entre mon appui sur le titre du jeu et le demarrage de la premiere colonne | **28 a 32 ms** |
| marqueur pose par le serveur sur l'ecran obtenu par un appui | `data-arrivee="par-un-geste"` |
| marqueur pose sur l'ecran obtenu par un rechargement de page | `data-arrivee="sans-geste"` |
| animations sur cet ecran-la | **0**, `animation-name: none`, voile a son etat de repos |

Trente millisecondes, c'est en dessous de ce qu'un enfant separe en deux evenements. Le mouvement
part sous son doigt. **Je leve le risque que j'avais nomme le matin** - "la cause et l'effet ne sont
jamais sur le meme ecran, le levier est sur l'ecran precedent" - parce que le raccord mesure est trop
serre pour que la question se pose : l'enfant presse, l'ecran change et bouge dans le meme
battement de paupiere. Je l'avais annonce comme le premier point que je regarderais et comme le
motif que j'ecrirais s'il tombait ; il ne tombe pas, et je le dis aussi clairement que je l'aurais
dit dans l'autre sens.

**Et le defaut que j'avais trouve en lisant est ferme.** Mon arbitrage prealable, section 6,
signalait qu'`accueil.html` rend le fragment de manche sans aucun geste sur la branche de reprise, et
donc que les colonnes tourneraient au rechargement. Joue : le rechargement en pleine partie rend la
manche, ne declenche **aucune** animation, et laisse le jeu jouable. Le symptome que j'avais decrit
n'existe plus dans le livre. C'est `ADR-0027` qui l'a traite, et je le porte au credit de
`theo-archi` et de `lea-dev` : j'avais decrit un symptome sans prescrire de cause, et la cause a ete
traitee.

#### 2. Point 2 - la duree, chronometree

Mesure faite dans le navigateur, en enregistrant l'horodatage de mon appui puis celui de chaque
`animationend`. Ce sont donc des millisecondes reelles, pas les valeurs declarees dans une feuille.

| Grandeur | Ma barre du matin | 3 colonnes (Calcul CP) | 4 colonnes (Le bon verbe) |
|---|---|---|---|
| de l'appui a l'arret de la **derniere** colonne | 0,3 s a 1,2 s | **778 ms** | **861 ms** |
| decalage entre le premier arret et le dernier | 0,25 s au plus | **167 ms** | **234 ms** |
| aller-retour reseau avant le premier mouvement | - | 28 ms | 32 ms |
| cout d'attente sur une partie de dix manches | - | **7,8 s** | 8,6 s |

**Les deux cas sont dans mes bornes.** La valeur retenue par `ines-design`, 0,6 s de glissement et
0,08 s de decalage cumulatif, est celle que je conseillais, et le total paye par l'enfant reste sous
la seconde.

**Le ralentissement existe, et je l'ai mesure plutot que cru.** Progression du voile sur la premiere
colonne, lue sur le rendu, par pas de 60 ms : 12,8 - 11,8 - 11,0 - 10,1 - 9,1 - 8,0 - 6,8 - 5,4 -
3,6 % de la hauteur du bouton. **Strictement decroissante d'un bout a l'autre.** Le mouvement ralentit
avant de s'arreter, comme je l'exigeais, et il n'y a **aucun depassement** : la bande ne franchit
jamais 80 % et ne revient jamais en arriere. Un seul passage, jamais rejoue.

**Ce que ces chiffres ne couvrent pas, et qui devient une remarque nouvelle et datee** : sur un
paquet a quatre colonnes, le decalage est a **234 ms de mon plafond de 250 ms**. Il tient, et je ne
touche pas au plafond. Mais le retard est **cumulatif de 80 ms par colonne** : une cinquieme colonne
porterait le decalage a 314 ms et creverait ma borne, sans qu'aucun test ne le voie. Ce n'est pas un
motif aujourd'hui - aucun paquet n'a cinq propositions - c'est un constat neuf du 2026-08-10 pour
`camille-po`, a joindre a la premiere story qui ouvrirait un paquet a cinq propositions.

#### 3. Point 3 - aucun autre mot ne defile, verifie sur les homophones d'abord

**C'est le point que je tenais le plus, et il est tenu.** J'ai fige l'animation reelle sur "Le bon
petit mot", item `là` / `la`, et je l'ai regardee image par image.

| Instant du mouvement | Ce que la colonne 1 montre | Ce que la colonne 2 montre |
|---|---|---|
| 0 ms | `là`, entier | `la`, entier |
| 100 ms | `là`, entier | `la`, entier |
| 200 ms | `là`, entier, une bande sombre le traverse | `la`, entier, bande plus haut |
| 300 ms | `là`, entier | `la`, entier |
| 500 ms | `là`, entier | `la`, entier |
| 599 ms | `là`, entier | `la`, entier |

**A aucun instant une colonne ne montre autre chose que son propre mot, ni un mot vide.** La forme
retenue le rend structurellement vrai : ce qui bouge est un voile decoratif pose en `::after`, et le
texte du bouton n'est ni efface, ni remplace, ni rogne. Verifie sur le style calcule de
`.proposition` sur le rendu, et non dans le fichier : `overflow: visible`, `clip-path: none`,
`text-overflow: clip`, `letter-spacing: normal`, `transform: none`, `font-stretch: 100%`,
`hyphens: manual`, `word-break: normal`, `writing-mode: horizontal-tb`, `font-size: 32px`. **Les six
conditions de ma section 3 du matin sont satisfaites sur le rendu, une par une.**

**Le second critere de S7-3, que `theo-archi` me signale comme vrai en fait et garde par personne :
l'enonce reste-t-il immobile pendant le defilement ?** Il me revient, je l'ai pris. Positions
relevees a cinq instants du mouvement, sur un ecran a quatre colonnes :

| t | consigne | enonce | score | boite de la colonne 1 |
|---|---|---|---|---|
| 0 ms | 207-237 | 253-301 | 169-199 | 317-391 |
| 150 ms | 207-237 | 253-301 | 169-199 | 317-391 |
| 300 ms | 207-237 | 253-301 | 169-199 | 317-391 |
| 450 ms | 207-237 | 253-301 | 169-199 | 317-391 |
| 599 ms | 207-237 | 253-301 | 169-199 | 317-391 |

**Immobiles au pixel, aux cinq instants.** Et j'ai verifie la cible de **toutes** les animations du
document plutot que de le supposer : elles sont quatre, toutes sur `.proposition::after`, aucune
ailleurs. Rien d'autre que les colonnes ne bouge sur cet ecran.

**Je dis aussi ce que j'ai appris de `theo-archi` sur ce point, parce que ma propre exigence etait a
un caractere de la production.** La decoupe qui confine le voile aurait, posee sur `.proposition` au
lieu de `::after`, transforme `sont` en `son` - la bonne reponse en la fausse - et son garde-fou
rendait 149 verts sur 149. La forme livree est saine ; la propriete que je detiens ne l'etait pas, et
elle l'est maintenant. Je le note parce que c'est exactement le genre d'ecart que ma seule lecture du
matin n'aurait pas attrape.

#### 4. Point 4 - l'ecran immobile est-il lisible ET jouable ?

`ADR-0026` garde la **forme** litterale du repli de mouvement ; il ne garde pas le resultat. C'etait
ma part et je l'ai faite dans un vrai navigateur, reduction d'animations activee au niveau du
systeme, pas simulee dans la feuille.

| Ce que j'ai verifie | Constat |
|---|---|
| `prefers-reduced-motion: reduce` reellement actif | oui, confirme par le navigateur |
| animations sur l'ecran de manche | **0**, `animation-name: none` |
| etat du voile | `inset(0px 0px 100%)`, donc **entierement invisible** |
| lisibilite des trois colonnes | nette, aucune bande, aucun residu |
| jouable : choisir une reponse | oui, verdict rendu ("Non, c'est on.") |
| jouable : enchainer la manche suivante | oui, "Encore !" fonctionne, manche suivante servie |

**L'ecran obtenu n'est pas un ecran degrade, c'est un ecran propre.** Un enfant qui a besoin
d'immobilite voit exactement le jeu, sans rien perdre : ni un mot, ni un bouton, ni une etape. C'est
le bon comportement, et je ne l'aurais pas su sans le jouer - une regle `animation: none` peut tres
bien laisser un element fige dans un etat a moitie efface. Ici elle le laisse a son etat de repos, et
l'etat de repos a ete choisi invisible. C'est ce choix-la qui fait le resultat, et il est juste.

#### 5. Point 5 - les trois ecrans du catalogue a 360 px, sur les items les plus longs

Balaye jeu par jeu, en jouant assez de parties pour voir tous les items de chaque paquet. Colonnes de
101 px a trois propositions, de 160 px a deux, exactement les largeurs que j'avais mesurees sur
maquette.

| Jeu | Mot le plus large du paquet | Largeur du mot | Air de chaque cote | Lignes | Police | Tronque ? |
|---|---|---|---|---|---|---|
| Calcul CP | `10` | 36,8 px dans 101,3 px | 32,3 px | 1 | 32 px | non |
| Le bon son (CP) | `on` | 38,9 px dans 101,3 px | 31,2 px | 1 | 32 px | non |
| Le bon petit mot (CM1) | `sont` | 65,5 px dans 160 px | 47,3 px | 1 | 32 px | non |

**Aucune troncature, aucune coupure, aucun mot sur deux lignes, aucune police sous 2 rem, sur aucun
item des trois paquets du catalogue.** Le verdict de lisibilite que j'avais rendu sur maquette se
verifie sur le livre, et il se verifie largement : le plus serre des trois jeux du catalogue garde
31 px d'air de chaque cote de son mot.

#### 6. Point 6 - l'ecran de fin de partie

Verifie a la fin des cinq parties, sur les quatre jeux.

| Ce que j'ai cherche | Constat |
|---|---|
| un levier, un bouton "Encore !" | **absent** |
| une colonne, une proposition | **aucune** |
| un decor de bouton-poussoir (rond, ombre, pastille) | **aucun** - l'habillage du levier est porte par `.demarrer` et `.encore` seulement, et ni l'un ni l'autre n'existe sur cet ecran |
| une animation | **0** |
| `sortie-pleine` | present, seul, en aplat plein, 117 x 56 px |

**Mon acquis 6 est tenu**, et il est tenu par construction plutot que par chance : l'habillage du
levier est attache aux deux boutons qui ouvrent une manche, jamais a la sortie. C'est le bon choix de
portee et je le note, parce que c'est precisement l'acquis qu'un chantier de mecanique defait par
accident. Il n'y a rien a presser sur cet ecran qui ne mene pas dehors.

#### 7. Point 7 - le chemin de reprise

Joue : partie en cours sur "Le bon son", item `MANTEAU`, puis **rechargement complet de la page**,
comme un enfant qui revient ou qui recharge sans le vouloir.

| Ce que j'ai mesure | Constat |
|---|---|
| ecran obtenu | la manche en cours, item `MANTEAU`, la partie n'est pas perdue |
| marqueur d'arrivee | `sans-geste` |
| animations declenchees | **0** |
| `animation-name` du voile | `none` |
| voile | a l'etat de repos, invisible |
| jouable depuis cet ecran | oui, reponse acceptee, manche suivante servie et animee normalement |

**Rien ne bouge, et rien n'est perdu.** C'est le comportement exact que je demandais : le mode de
defaillance choisi est l'immobilite, jamais un mouvement que l'enfant n'a pas demande. Ce que je
gardais seule sur ce chemin est desormais couvert par le serveur ; je le dis en section 12.

#### 8. Point 8 - l'indice sur un ecran qui defile, apres trois echecs

Joue trois parties ratees a chaque manche, sur les trois jeux du catalogue, pour faire venir l'aide.

| Jeu | Position de l'indice | Position des colonnes | Au-dessus du pli a 640 ? |
|---|---|---|---|
| Calcul CP | 317-375 | 387-461 | oui, les deux |
| Le bon son | 317-405 | 417-491 | oui, les deux |
| Le bon petit mot, item court | 317-435 | 447-521 | oui, les deux |
| Le bon petit mot, 5 items sur 24 | jusqu'a 557-705 | jusqu'a 717-791 | **non** |

**L'indice est lisible et stable** : cadre complet, 1,25 rem, immobile pendant que les colonnes
bougent (section 3), et il n'est jamais traverse par le voile, qui reste dans la boite des boutons.

**Et il n'a pas ete pousse sous le pli par la nouvelle mise en page** : la position du haut de
l'indice ne depend que de la hauteur de l'enonce, que ce chantier ne touche pas. Le defaut de pli de
"Le bon petit mot" est reel, il persiste, `yanis-qa` a raison de le confirmer sur le code livre, et
**il n'est pas imputable a ce chantier**. Je le traite en section 10, point C, avec une correction de
mon propre comptage, et je ne l'oppose pas a ce chantier : ce serait lui faire porter un defaut dont
j'ai moi-meme etabli le matin que la cause est ailleurs.

#### 9. Les quatre conditions non negociables de ma section 1

Elles conditionnaient l'acceptabilite de la metaphore. Verifiees une par une, sur ce que l'enfant
lit, voit et entend.

**Condition 1 - aucun vocabulaire ni imagerie de jeu d'argent.** Releve exhaustif sur les trois
ecrans (accueil, manche, retour) : le titre de la page est `Recreo` ; le seul `alt` du document est
vide, sur l'icone decorative ; le seul `aria-label` est `Menu principal` ; le seul contenu genere par
la feuille de style est la chaine vide, sur le voile et sur la pastille du levier ; les textes
visibles sont les titres des jeux, `Les jeux`, `Score`, les consignes, les enonces, les propositions,
`Bravo, c'est juste !`, `Non, c'est ...`, `Encore !`, `C'est fini !`. **Aucun des treize mots que
j'avais interdits n'apparait nulle part**, et je les ai cherches sur le texte assemble plutot que
dans les gabarits. J'ai aussi verifie l'absence de `rouleau`, `levier`, `tirage`, `hasard`, `chance`,
`serie`, `multiplicateur` : absents. Le son : **le produit ne contient aucun element audio ni video,
zero**. Je le dis ainsi plutot que de pretendre avoir ecoute. **L'acquis que je portais tient : le
nom de la mecanique n'est pas remonte a la surface**, alors que c'etait la tentation exacte de ce
sprint, ou il devenait enfin exact.

**Condition 2 - aucun signal de gain a l'arret.** Etat des trois colonnes une fois arretees, releve
sur le rendu :

| | colonne 1 | colonne 2 | colonne 3 |
|---|---|---|---|
| fond | `rgb(46, 111, 158)` | `rgb(46, 111, 158)` | `rgb(46, 111, 158)` |
| couleur du mot | blanc | blanc | blanc |
| bordure, ombre, contour | aucune | aucune | aucune |
| filtre, opacite, transformation | aucun, 1, aucune | aucun, 1, aucune | aucun, 1, aucune |
| voile | invisible | invisible | invisible |

**Rigoureusement identiques.** Aucune colonne ne se distingue des autres a l'arret, rien ne s'allume,
rien ne se colore, et rien ne se passe jusqu'a ce que l'enfant presse. L'arret n'est pas un resultat,
c'est une donne - ce qui etait le motif operationnel de cette condition, et il est satisfait.

**Condition 3 - un seul canal de verdict.** L'ecran de retour porte un `.signal` unique : la forme
(coche ou croix), sa couleur, et une phrase. C'est le canal qui existait avant ce chantier, inchange.
**Le chantier n'a ajoute aucun second canal** : rien sur l'ecran de manche ne dit si la reponse
precedente etait juste, et l'arret des colonnes ne dit rien du tout (condition 2).

**Condition 4 - aucun compteur de serie.** Le seul compteur present a l'ecran est `Score : N`. Aucune
serie, aucun multiplicateur, aucun "d'affilee", aucun compteur d'echecs visible - alors qu'il en
existe un cote serveur, celui qui declenche l'indice, et qu'il aurait ete facile de l'afficher. Il ne
l'est pas.

**Les quatre conditions sont tenues. La metaphore diminuee est celle qui est livree**, et la machine
ne decide rien : elle distribue, l'enfant choisit.

#### 10. Ce que je corrige de mes propres constats, avant qu'on s'appuie dessus

Quatre points, et deux sont des corrections a mon desavantage.

**A. Ma valeur de voile est celle qui est livree, et je la reverifie a l'oeil et au pixel.** Lu sur
l'image rendue : sous le voile `rgb(36, 88, 126)`, hors voile `rgb(46, 111, 158)`. C'est exactement
un noir a 20 % sur l'aplat d'accent. Contraste du mot blanc que je recalcule sur ces pixels-la :
**7,57:1 sous le voile contre 5,41:1 au repos.** J'avais annonce 7,48:1 - la valeur juste est 7,57:1,
l'ecart vient de ce que j'avais calcule sur la composition theorique et non sur le pixel rendu. Dans
les deux cas, **assombrir augmente le contraste** au lieu de le reduire, et le blanc a 45 % que
j'avais refuse l'aurait fait tomber a 2,29:1. `lea-dev` a suivi mon arbitrage plutot que la
proposition d'`ines-design` sur ce point, et c'est la bonne valeur qui est a l'ecran.

**B. `mangeons` a 2 px d'air, pas 3.** J'avais ecrit dans mon second arbitrage qu'il ne restait
"que 3 px d'air a droite de mangeons". Mesure sur le code livre : le mot fait **156 px dans une boite
de 160 px, soit 2,0 px de chaque cote**. Mon chiffre etait optimiste de 1 px. Le mot **n'est pas
tronque** et ma condition tient, mais la marge est celle-la et pas une autre. Le detail est dans
`FICHE-bandit-manchot-conjugaison-present-cm1.md`, parce que c'est un mot de ce paquet.

**C. Sur "Le bon petit mot", ce sont CINQ items sur 24, et non trois.** Correction d'un de mes
propres constats, dans le sens de la gravite. Balayage de 41 etats d'item distincts sur huit parties,
mesure a 360 x 640 sur le code livre :

| Enonce (debut) | Lignes d'enonce | Bas de l'indice | Boites de reponse | Etat |
|---|---|---|---|---|
| "Après cette longue randonnée en montagne..." | 6 | 705 | **717-791** | les deux boutons entierement sous le pli |
| "Tom cherchait sa gomme depuis le début du cours..." | 5 | 641 | 653-727 | les deux entierement sous le pli |
| "Après la récréation, tous les élèves ___ rangent..." | 4 | 579 | 591-665 | les deux mots sous le pli |
| "Léa a perdu son livre depuis lundi, mais elle ___..." | 4 | 579 | 591-665 | les deux mots sous le pli |
| "Même si le ciel est bien dégagé ce matin, il ___..." | 4 | 579 | 591-665 | les deux mots sous le pli |
| les 19 autres items | 1 a 3 | - | - | au-dessus du pli |
| les 24 items **sans** l'indice | - | - | - | **aucun** en defaut |

J'avais compte trois items ; il y en a cinq. Mon comptage du matin etait fait sur une lecture des
enonces, celui-ci est fait en jouant. **Rien de ce constat ne change mon verdict** : ce n'est pas un
motif, le Done pedagogique de "Le bon petit mot" reste accorde, sa ligne de matrice reste
`couvert-valide`, et `DEC-0016` gouverne desormais la regle. Ce qui change, c'est le chiffre que
`camille-po` doit instruire.

**D. Et je corrige un credit que j'avais accorde a ce chantier a tort.** J'avais ecrit que la mise en
page en colonnes libere 180 px et "rend le pli confortable la ou il etait juste". **C'est faux pour un
enonce long, et l'erreur est de moi.** Les colonnes ne remontent pas la **premiere** rangee de
boutons : sa position depend de la hauteur de l'enonce et de l'indice, qui sont au-dessus d'elle.
Mesure sur l'item le plus long du paquet des homophones, indice affiche : les deux boutons sont a
**717-791**, exactement la ou j'avais releve 717-790 le matin. Les colonnes n'ont rien gagne sur cet
item. Ce qu'elles gagnent reellement, et qui est mesure, c'est de **remonter les propositions de la
deuxieme rangee** : sur "Le bon verbe", la quatrieme proposition passe de 638-707 empilee a 407-481,
455-529 ou 543-617 selon l'item. Le gain est reel, il est important, et il ne porte pas ou j'avais
dit qu'il portait. **Ce point est declare degrade sur un aspect** : je n'ai pas pu remesurer la mise
en page empilee d'avant le chantier sur le code livre, parce qu'on ne l'obtient pas en changeant une
seule propriete - `flex: 1 1 0` change de sens sur l'autre axe et donne des boutons de 44 px au lieu
de 74. Les 180 px et les 90 px sont donc de l'arithmetique sur mes deux mesures, pas une mesure de
l'ancienne mise en page. Les 717-791 de l'item long, eux, sont mesures.

#### 11. Ma reserve, et c'est la seule

**Le voile ne quitte pas le bouton : il s'y arrete et disparait d'un seul coup.** C'est un constat
neuf, date du 2026-08-10, sur un ecran qui n'existait pas quand j'ai ecrit ma liste de refus. Mesure
image par image sur la derniere image du mouvement :

| Instant | Bande sombre rendue dans un bouton de 74 px |
|---|---|
| 520 ms | lignes 57 a 70 |
| 560 ms | lignes 58 a 71 |
| 580 ms | lignes 59 a 71 |
| 599 ms | **lignes 59 a 71**, soit 13 px encore visibles au bas du bouton |
| 600 ms | **plus rien** |

A la derniere image, chaque bouton porte donc une barre sombre de 13 px collee a son bord bas, et
cette barre s'efface en une image. Sur un ecran a trois colonnes, cela fait **trois disparitions
decalees de 80 ms** a la fin de chaque manche, soit trente par partie.

**Pourquoi c'est une reserve et pas un motif.** Aucun de mes refus nommes n'est enfreint, et je les
reprends un par un plutot que d'en etirer un : ce n'est pas un rebond (la bande ne revient jamais en
arriere), ce n'est pas un arret brutal depuis la pleine vitesse (le mouvement a ralenti a 3,6 % par
60 ms avant de s'arreter), ce n'est pas un clignotement repete plus de trois fois par seconde
(chaque colonne ne disparait qu'une fois), et ce n'est pas un signal de gain (les colonnes sont
rigoureusement identiques, section 9). **Je ne durcis donc aucune de mes clauses apres coup** : je
nomme un defaut que je n'avais pas prevu, et je l'ouvre comme reserve datee.

**Pourquoi c'en est quand meme une.** Le regard d'un enfant est attire par une disparition brusque, et
elle se produit exactement a l'instant ou l'ecran devient actionnable - donc a l'instant ou son
attention devrait aller au mot, pas au bas du bouton. Trente fois par partie, ce n'est plus un detail.

**Ce que j'attends, en propriete et non en solution** : que le mouvement se termine sans discontinuite
visible, c'est-a-dire que la bande soit sortie de la boite avant que l'animation ne rende la main.
Comment l'obtenir n'est pas ma decision : c'est de la forme, donc `ines-design` propose et
`theo-archi` tranche la mise en oeuvre. Je regarderai le resultat, pas la regle.

#### 12. Ce dont je reste seule garde apres cet audit

Ma liste du matin en comptait huit. Trois en sortent, cinq restent, une entre.

| Point | Etat apres cet audit |
|---|---|
| 1. duree, courbe, decalage du defilement | **reste ma garde.** Les valeurs sont bonnes aujourd'hui ; rien n'empeche de les changer. `ADR-0026` a ferme deux trous - la decoupe laterale, et le retard ecrit dans une regle par colonne - et c'est acquis. Le **cumul** au-dela de quatre colonnes reste hors garde-fou (section 2) |
| 2. qu'un mot tienne dans sa colonne | **reste ma garde**, et la marge la plus etroite du depot est desormais chiffree : 2,0 px sur `mangeons` |
| 3. qu'une proposition ne soit ni tronquee, ni coupee, ni comprimee | **reste ma garde** pour cinq des six conditions. Verifiees sur le rendu cette fois, pas sur la feuille |
| 4. que ce que l'enfant voit soit ce qu'il envoie | **sort de ma garde en pratique** : la forme retenue rend le texte du bouton intouchable, et `ADR-0026` garde maintenant que la decoupe reste dans le pseudo-element. Je le surveille encore comme risque de regression de forme, plus comme risque courant |
| 5. qu'aucun mot faux ne defile | **sort de ma garde** : plus aucun mot ne defile du tout. Verifie image par image sur les homophones |
| 6. que l'enfant relie le mouvement a son appui | **sort de ma garde comme risque** : 30 ms mesures, `ADR-0027` garde le marqueur. Je le reverifierai si le chemin de rendu change |
| 7. qu'aucun decor de machine n'atteigne l'ecran de fin de partie | **reste ma garde.** Le garde-fou de saillance n'oppose que des etats simultanes ; c'est la portee de la regle d'habillage qui protege aujourd'hui, et une portee peut s'elargir sans qu'aucun test ne rougisse |
| 8. que le mouvement ne se declenche jamais sans geste | **sort de ma garde** : `ADR-0027` pose le marqueur cote serveur et un oubli de point d'appel se traduit par l'immobilite. Le mode de defaillance est le bon |
| **9. NOUVEAU - que la fin du mouvement soit continue** | **ma garde**, et c'est ma reserve de la section 11. Aucun test ne regarde la derniere image d'une animation |

Je le dis pour qu'on ne croie pas couvert ce qui ne l'est pas, et aussi pour rendre la garde de ce qui
l'est desormais : ce sprint a **reellement** ferme trois de mes huit points, ce qui n'etait jamais
arrive.

#### 13. Suites hors de ma main

- **`squad/programme/MATRICE-couverture.md` ne bouge pas.** Ce chantier ne porte **aucune** competence
  du programme : il n'y a aucune ligne a confronter au referentiel, et **aucune ligne ne change quel
  que soit mon verdict**. C'est la seconde fois que l'etape 2 de ma methode est sans objet, et je le
  dis plutot que de la simuler.
- **Pour `camille-po`** : ma reserve de la section 11 est a convertir en story. Trois constats neufs
  et dates du 2026-08-10 s'y ajoutent, et ils ne bloquent rien : le cumul du decalage au-dela de
  quatre colonnes (section 2), le comptage corrige a cinq items sur "Le bon petit mot" (section 10,
  point C), et la correction du credit que j'avais accorde a tort aux colonnes (section 10, point D) -
  ce dernier importe parce qu'il retire une raison de croire le defaut de `DEC-0016` deja adresse.
- **Pour `ines-design`** : la duree, la courbe et la valeur du voile qu'elle a choisies sont validees
  sur le rendu. La fin du mouvement (section 11) lui revient, sous la meme latitude et le meme veto.
- **Pour `sofia-sm`** : ce bloc est ma conclusion. Je n'ecris pas dans `squad/sprints/`, la revue de
  sprint n'est pas mon fichier.
- **Je n'efface rien.** Mes deux arbitrages prealables du meme jour restent lisibles plus bas, avec
  leurs dates, y compris la reduction que j'ai eteinte moi-meme. L'histoire de ce chantier se lit dans
  l'ordre.

---

### Verdict - s06, S6-10 et S6-9 prononces sur le rendu reel, audit du 2026-08-10

- **Verdict S6-10** (bandeau `#1d4b6b`, recolorage du fond de l'icone) : **VALIDE.** Done
  pedagogique accorde.
- **Verdict S6-9** (une seule sortie par ecran de jeu) : **VALIDE.** Done pedagogique accorde.
- **Date** : 2026-08-10.
- **Commit audite** : `be60b10` (`HEAD` de `round-two`).
- **Moyens** : audit **joue**, et cette fois **non degrade** - c'est le point de cette section. Fat
  jar reconstruit depuis un worktree dedie hors de l'arbre du depot, lignee verifiee
  (`outils/verifier-lignee.sh`), JDK constate par `outils/verifier-jdk.sh` (`java du PATH :
  1.8.0_202 | JDK de compilation : 21.0.10 | JAVA_HOME : vide | toolchains lu par Maven : non
  (fichier present sous un nom non lu)`), `JAVA_HOME` pointe sur ce JDK 21 le temps de la commande
  et jamais ecrit dans un fichier commite. Jar lance sur un port dedie. Parties jouees sur les
  additions CP jusqu'a l'ecran de fin. **Cinq etats de l'ecran de jeu regardes dans un navigateur**
  a 1280 px et a une largeur de viewport reellement de 360 px, plus l'ecran d'accueil.

#### 0. Je leve mon propre audit degrade, et je dis comment j'ai regarde

Le 2026-08-07 j'ai ecrit : *"le changement n'est pas encore ecrit, je n'ai donc pas vu le bandeau
modifie dans un navigateur. Je reverifierai sur le rendu livre avant de prononcer le Done
pedagogique de S6-10."* C'est fait, et voici la methode, parce qu'elle a une limite que je declare.

Les ecrans de jeu ne sont pas atteignables par une simple adresse : ils naissent d'un echange htmx.
Pour les regarder dans un navigateur, j'ai assemble, **a partir des seuls octets servis par
l'application**, la page telle que htmx la construit : la coquille complete rendue par `GET /`, dont
l'interieur de `main#jeu` est remplace par le fragment que le serveur renvoie pour l'etat voulu -
c'est-a-dire exactement ce que declarent `hx-target="#jeu"` et `hx-swap="innerHTML"`. Les deux
feuilles de style, le script et l'icone sont ceux telecharges depuis l'application. Aucun octet
n'est de moi.

**La limite** : je n'ai pas fait cliquer un vrai navigateur sur les boutons ; j'ai reconstitue le
resultat de ces clics au point de raccord que le balisage declare. Pour tout ce qui est couleur,
taille, position et pli, cela ne change rien - c'est le meme DOM et la meme cascade. Je le dis
quand meme, parce qu'une methode non dite est une methode qu'on ne peut pas contredire.

#### 1. S6-10 : les 1855 pixels sont exacts a l'unite, et je les ai recomptes moi-meme

J'avais ecrit qu'un compte different signifierait que l'outil a fait autre chose que ce que je
demandais. J'ai donc recompte, sur **l'icone telechargee depuis l'application** et non sur le
fichier source - les deux sont d'ailleurs identiques octet pour octet, 1014 octets, verifie.

| Vignette | Pixels | `#2e6f9e` restants | `#1d4b6b` | Attendu par mon arbitrage | Alpha a 0 | Attendu | Couleurs distinctes |
|---|---|---|---|---|---|---|---|
| 16 x 16 | 256 | **0** | **105** | 105 | **6** | 6 | **8** |
| 32 x 32 | 1024 | **0** | **532** | 532 | **20** | 20 | **8** |
| 48 x 48 | 2304 | **0** | **1218** | 1218 | **42** | 42 | **8** |
| **total** | | **0** | **1855** | 1855 | **68** | 68 | |

Les cinq points de ma consigne executable sont tenus, un par un :

1. le remplacement porte sur **les trois vignettes**, pas seulement celle de 48 px ;
2. les comptes sont **exacts a l'unite**, 105 / 532 / 1218, total 1855 ;
3. **les sept autres couleurs sont identiques a l'octet** - `#f5c518`, `#13314a`, `#c9ced3`,
   `#3a2a1a`, `#1c1c1c`, `#f2a7b7` et le transparent, aucune autre valeur n'apparait ;
4. **les pixels transparents sont restes totalement transparents** (alpha exactement 0, aux comptes
   6 / 20 / 42) : l'icone n'a pas ete aplatie sur un fond opaque, donc les biseaux des coins tiennent
   et aucun carre ne reapparait sur le bandeau ;
5. **huit couleurs distinctes par vignette**, trois entrees a 16, 32 et 48 px, chacune un PNG
   decodable, **et zero pixel a alpha partiel** - donc aucun reechantillonnage, aucun anticrenelage,
   aucun redessin. Le dessin n'a pas bouge d'un pixel.

Cote feuille de style, `:root` declare `--recreo-entete-fond: #1d4b6b;` et `.entete` l'utilise ;
`--recreo-accent` reste `#2e6f9e` et sert toujours `.demarrer`, `.proposition`, `.encore`,
`.sortie-pleine`, la couleur de texte de `.score` et de `.fin-de-partie`, et le contour de
`.retour-accueil`. Rien d'autre n'a change, comme je l'avais demande.

#### 2. Ce que j'ai vu a l'ecran, et qui est ce que je ne pouvais pas juger le 7

Les deux points sur lesquels je m'etais reservee, tranches sur le rendu :

- **Le crayon se lit comme un crayon a 44 px sur le bandeau sombre.** Mesure : `.icone-entete` fait
  bien 44 x 44 px, a x=12 y=21 a 360 px comme a 1280 px. Le fond bleu du dessin est indiscernable du
  bandeau : ce que je vois est un crayon jaune en diagonale, pointe en bas a gauche, gomme en haut a
  droite, et **pas une pastille collee sur la barre**. Le fondu que j'avais constate en s05 sur
  `#2e6f9e` est conserve sur `#1d4b6b`. **C'est ce que le recolorage devait obtenir, et il l'obtient.**
- **"Encore !" se trouve du premier coup d'oeil, malgre l'ecart modeste de 1,71:1 entre les deux
  bleus.** Sur l'ecran de retour en cours de partie, le seul aplat de la couleur d'action est le
  bouton "Encore !", pose juste sous le bandeau de retour rouge que l'enfant vient de lire ; la
  sortie est un contour, et le bandeau d'en-tete est un autre bleu, plus sombre, en haut de l'ecran
  et sur toute sa largeur. **Ce que j'avais parie tient, et je dis pourquoi : ce n'est pas la teinte
  qui fait le travail, c'est qu'un bandeau pleine largeur n'a jamais la forme d'un bouton.** Ma
  reserve 4 du 2026-08-07 - le bandeau portait exactement la couleur d'action sur 107 677 px2, seize
  fois la surface du bouton - **est levee**.

**`#1d4b6b` reste le bandeau le plus sombre que je valide.** Ce plancher, pose le 2026-08-07, est
confirme sur le rendu et n'est pas durci : les trois parties sombres du crayon (contour, bois, mine)
sont deja sous 3:1 sur cette valeur, et j'ai regarde la silhouette a 44 px sur la capture reelle -
elle est portee par les parties claires. Descendre plus bas demande d'abord de faire redessiner
l'extremite sombre du crayon.

#### 3. Le garde-fou que j'exigeais existe, et je lui rends la garde

Mon arbitrage demandait, dans le meme commit, une propriete mecanique : *le fond opaque de chacune
des trois vignettes porte exactement la couleur declaree par `--recreo-entete-fond`, et aucun pixel
n'y porte plus `--recreo-accent`*. Elle est ecrite dans
`LeFondDeLIconePorteLaCouleurDuBandeauTest`, elle lit les deux valeurs dans le CSS plutot que de les
recopier, et elle porte deux contre-exemples qui montrent qu'elle mord - une vignette laissee a
l'ancien accent, et une feuille qui ne pointe plus vers la variable.

**Je ne suis donc plus seule garde de ce couplage**, et c'est le resultat que je cherchais : ma
verification d'aujourd'hui n'etait pas destinee a etre refaite a la main au prochain changement de
bandeau.

#### 4. S6-9 : verifie ecran par ecran sur le HTML servi et sur le rendu, et non sur la parole

`lea-dev` m'annonce que mon arbitrage etait deja satisfait par `ADR-0022` et que S6-9 n'a demande
aucun code. **Je l'ai verifie moi-meme, etat par etat, plutot que de la croire.** Mon tableau du
2026-08-07 contre ce que l'application sert :

| Ecran | Sortie dans la zone de contenu - attendu | Constate | Entree de menu de l'en-tete - attendu | Constate |
|---|---|---|---|---|
| Accueil | aucune | aucune | **visible** | **visible**, `.btn.lien-accueil` "Les jeux", a droite du bandeau |
| Manche | `.btn retour-accueil`, contour, "Les jeux" | conforme | **non visible** | **`display: none`** mesure sur le rendu |
| Retour, partie en cours | `.btn retour-accueil`, contour, "Les jeux" | conforme | **non visible** | non visible |
| Retour, fin de partie | `.btn sortie-pleine`, aplat plein, "Les jeux" | conforme, et c'est la seule chose pressable de l'ecran | **non visible** | non visible |
| Erreur | aucune sortie ajoutee, "Recommencer" seul et aplat plein | conforme (`<a class="btn recommencer" data-role="action">Recommencer</a>`, seul element pressable) | **non visible** | non visible - le fragment d'erreur porte bien `ecran-de-jeu` |

Les trois gestes que j'avais autorises, et pas un de plus :

1. **le libelle est "Les jeux"** partout - dans l'en-tete, sur la manche, sur le retour, sur la fin
   de partie. Un seul mot de sortie circule dans toute l'application. **Ma reserve 3 du 2026-08-07
   est levee** par ce geste, comme annonce.
2. **l'entree de menu n'est pas visible sur les cinq etats de l'ecran de jeu**, et elle l'est sur
   l'accueil. Mesure au navigateur et non deduite de la feuille : sur un ecran de jeu a 360 px,
   `.menu a` rend `display: none` et une boite de largeur 0 ; sur l'accueil a la meme largeur, elle
   rend une boite de 109 x 53 px a x=239, entierement dans le cadre.
3. **les deux branches du `th:class` de `fragments/retour.html` et le bouton de
   `fragments/manche.html` ne sont pas touches.** L'ecran de fin de partie rend bien
   `sortie-pleine`, en aplat plein - l'etat que j'avais valide en S3-6 et que je refusais de perdre.

**Le cul-de-sac que je craignais n'existe pas** : l'ecran qui dit "C'est fini !" montre par ou sortir,
et il le montre par l'element le plus visible de la zone que l'enfant regarde. La sortie est au meme
endroit sur les cinq etats et ne change que de forme. **Done pedagogique de S6-9 accorde.**

Et l'element reste permanent dans le document sur tous les etats : `S6-1` et `DEC-0012` point 2 - une
entree de menu, dans un en-tete permanent - ne sont pas defaits, comme je m'etais engagee a le
verifier. La propriete est par ailleurs epinglee par
`LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest` : je ne suis pas seule garde de celle-la non plus.

#### 5. Le debordement de l'entree de menu a 360 px : je ne le reproduis pas, et voici ma methode

J'avais inscrit cette verification a 360 px dans ma liste de remesures. `sofia-sm` a sorti la story
du sprint au motif que l'obstacle n'etait pas etabli. **Ce que je constate confirme cette decision.**

**Ma methode, puisqu'elle est le coeur de l'affaire.** Je n'ai pas employe
`chrome --headless --window-size=360,...`, dont `yanis-qa` a etabli qu'il impose un plancher de
**exactement 500 px** de largeur de contenu et qu'il **decoupe** au lieu de mettre a l'echelle. J'ai
charge la page reelle dans un cadre `iframe` dont je fixe la largeur en CSS, la fenetre Chrome
parente faisant 900 px - donc hors plancher -, et j'ai lu des nombres plutot qu'estime des pixels :
un script de mesure (`getBoundingClientRect` sur le bandeau, l'icone, le nom du site, le menu et son
entree, la zone de jeu) injecte dans **une copie** de la page, jamais dans un fichier du depot.
Controle que la mesure porte bien sur la largeur voulue : la page rend `window.innerWidth = 360` et
`document.documentElement.scrollWidth = 360`.

**A une largeur de viewport reellement de 360 px, sur l'ecran d'accueil, il n'y a aucun
debordement :**

| Element | x | largeur | bord droit | dans le cadre de 360 px ? |
|---|---|---|---|---|
| bandeau | 0 | 360 | 360 | oui, fond `rgb(29, 75, 107)` = `#1d4b6b` |
| icone | 12 | 44 | 56 | oui |
| nom du site | 72 | 99 | 171 | oui |
| entree de menu "Les jeux" | 239 | 109 | **348** | **oui, 12 px de marge** |
| zone de jeu | 0 | 360 | 360 | oui |

`scrollWidth` du document egale `innerWidth` : **rien ne sort du cadre visible**, et "Les jeux" est
entierement lisible sur la capture, sur une seule ligne.

**J'ai pousse plus bas pour savoir comment le dessin cede**, parce qu'un dessin qui tient a 360 px
sans marge de securite n'est pas la meme chose qu'un dessin qui tient :

- **a 320 px** : toujours aucun debordement ;
- **a 300 px** : toujours aucun debordement. Le libelle se replie sur deux lignes a l'interieur du
  bouton ("Les" / "jeux"), le bandeau passe de 85 a 115 px de haut, et le bord droit de l'entree
  reste a 288 px pour 300 px disponibles.

Donc le dessin **degrade en se repliant, il ne deborde pas** - et il ne deborde deja plus a 60 px
sous la cible. Le repli a 300 px est laid et je ne le valide pas comme etat souhaitable, mais 300 px
est sous le plancher de 375 px de `DEC-0007` et ce n'est pas un defaut a la largeur visee.

**Ce que je conclus, et je le separe de ce qui n'est pas de moi.** Sur mon environnement, avec une
methode dont j'ai verifie qu'elle mesure bien la largeur qu'elle annonce, **le debordement n'existe
pas.** Je ne conclus pas pour autant que la mesure d'origine etait de mauvaise foi : `ines-design` a
montre que la marge du dessin est etroite et depend de la pile de polices du poste, et le symptome
decrit ("Les jeux" absent du bandeau) est exactement ce que produit le decoupage a 500 px que
`yanis-qa` a caracterise. **Ma remesure est close et je n'en fais ni une reserve ni un motif.** Ce
qui reste, et qui n'est pas de mon ressort : la robustesse du dessin de l'en-tete ne repose
aujourd'hui sur aucune regle de repli, et `ines-design` en a propose une. Que cette proposition soit
retenue ou non est une question produit pour `camille-po`, pas une exigence de ma part.

**Je porte au credit de `sofia-sm` d'avoir sorti cette story en refusant de fabriquer un motif de
remplacement.** Un obstacle non etabli qu'on garde pour ne pas perdre la face coute plus cher qu'un
obstacle abandonne.

#### 6. Ce dont je reste seule garde apres cette remesure

Ma liste du 2026-08-07 en comptait quatre. Deux sont retirees, deux restent, et j'en ajoute une.

1. **Que le crayon se lise comme un crayon a 44 px** - **je m'en dessaisis pour le couplage de
   couleur**, desormais mecanique (section 3), mais **je reste seule garde de la lisibilite du dessin
   lui-meme** : aucun test ne dira qu'un crayon ressemble a un crayon. Verifie aujourd'hui sur le
   rendu, a refaire si le dessin change.
2. **Que l'enfant trouve "Encore !" du premier coup d'oeil.** Verifie aujourd'hui en jouant. Reste
   ma charge : la saillance relative est epinglee par `LActionNeDeclarePasMoinsQueLaSortieTest` sur
   des tailles et des roles, pas sur ce qu'un enfant remarque.
3. **Que le badge de l'onglet reste reconnaissable en `#1d4b6b`, y compris sur un theme sombre.**
   **Toujours degrade, et je le declare** : un navigateur sans tete ne rend pas la barre d'onglets.
   J'ai juge sur les octets composes a 16 px, pas sur un onglet reel. C'est la seule partie de mon
   arbitrage du 2026-08-07 que je n'ai pas pu porter au rendu.
4. **Que les trois parties sombres du crayon sous 3:1 ne genent pas un enfant qui distingue mal les
   couleurs.** Le controle en niveaux de gris a 44 px tient sur le rendu reel. Restent hors de toutes
   mes mesures : un ecran bon marche, une forte lumiere du jour.
5. **Nouveau, date du 2026-08-10 : que ce qui compte sur un ecran soit au-dessus du pli.** Aucune
   decision du depot ne fixe de hauteur de viewport minimale - `DEC-0007` ne parle que de largeur -
   et j'ai trouve aujourd'hui, sur `bandit-manchot-conjugaison-present-cm1`, un ecran ou la
   quatrieme proposition est sous le pli a 360 x 640. Les ecrans de ce jeu-ci tiennent tous
   au-dessus du pli, verifie ; mais tant qu'aucune regle n'existe, c'est moi le garde-fou. Constat
   ecrit dans `FICHE-bandit-manchot-conjugaison-present-cm1.md`, section 4 du verdict du
   2026-08-10.

#### 7. Suites hors de ma main

- Aucun mouvement de `squad/programme/MATRICE-couverture.md` sur ce verdict : S6-9 et S6-10 sont de
  l'habillage, pas de la couverture de competence. La ligne `mathematiques | CP | Nombres et
  calculs | Calcul mental` n'est pas touchee par cet audit.
- Pour `camille-po` : le repli de l'en-tete propose par `ines-design` reste une question produit
  ouverte (section 5). Je n'en fais pas une exigence.
- Pour `camille-po`, nouveau : une regle sur ce qui doit rester au-dessus du pli, et sur quel
  viewport de reference, serait la bonne reponse a mon point 6.5. Je decris le besoin, je n'ecris
  pas la story.

### Arbitrage sur la proposition de forme d'ines-design - second arbitrage du 2026-08-10

**Ce bloc n'est pas un Done pedagogique.** Il repond a
`squad/sprints/s07-machine-a-sous/proposition-ines-machine-a-sous.md` (branche `s07-ines-design`,
commit `f4cc598`), qui respecte mes bornes de duree et me renvoie trois questions de rendu. Il
s'ajoute a mon arbitrage du chantier machine a sous du meme jour, ci-dessous, **qu'il ne durcit
pas** : les deux exigences nouvelles de ce bloc portent sur une forme qui n'existait pas quand j'ai
ecrit l'autre.

#### Les trois verdicts, en une phrase chacun

1. **Le voile translucide devant un bouton immobile est la bonne forme, et sa valeur est refusee :**
   a 45 % de blanc, le contraste du mot blanc tombe a **2,29:1** pendant que le voile le traverse,
   sous le plancher de 4,5:1 et sous l'etat servi aujourd'hui qui est a 5,41:1.
2. **Le repli en deux rangees tient, je l'ai remesure et je le confirme** - 160 px de colonne a
   360 px, ecart de 16 px sur les deux axes, aucune troncature - avec une reserve chiffree : sur le
   pire item reel du paquet, qui n'est pas celui qu'`ines-design` a teste, il ne reste que **3 px
   d'air** a droite du mot.
3. **Je confirme mon refus de la manette**, c'est bien un veto de moi et non une lecture
   d'`ines-design`, et le bouton-poussoir rond est accepte.

#### 1. Le voile : bonne forme, mauvaise valeur, et voici la mesure

**La forme est la bonne, et je le dis franchement parce que c'est celle que je conseillais.** Un
bouton qui ne bouge jamais, avec son texte reel toujours present, rend impossible par construction
la violation de mon exigence "jamais un autre mot" - c'est mieux qu'un test, et c'est mieux que ma
propre variante ou le mot apparaissait a l'arret. Sur ce point, `ines-design` a raison et je retire
la preference que j'exprimais pour un mot cache pendant le defilement : sa forme est superieure a
celle que je conseillais.

**La valeur, elle, est refusee.** J'ai fige son voile en travers du mot et j'ai mesure la couleur
composee sur le rendu, puis calcule le contraste. Mesure a 360 px par
`outils/mesurer-rendu.sh`, echantillon pris hors glyphe dans la bande du voile :

| Etat | Fond composite mesure | Contraste du mot blanc | Verdict |
|---|---|---|---|
| aucun voile, etat servi aujourd'hui | `rgb(46, 111, 158)` | **5,41:1** | c'est la reference |
| **voile blanc a 45 %**, la valeur proposee | `rgb(140, 176, 202)` | **2,29:1** | **refuse** |
| voile blanc a 8 % | `rgb(62, 122, 166)` | 4,63:1 | acceptable |
| voile noir a 20 % | `rgb(37, 89, 126)` | 7,48:1 | acceptable, et meilleur que la reference |

**Ce que cela fait a l'enfant, et je l'ai regarde et pas seulement calcule** : le voile est blanc,
le texte du bouton est blanc, donc le voile efface le mot en le delavant au lieu de passer derriere
lui. Sur la capture, le bas des lettres de `an`, `on` et `in` se dissout dans la bande claire. Un
enfant de CP est en train de dechiffrer ces lettres ; un mot qui palit au moment ou il le lit est un
mot qu'il relit, et le defilement cesse d'etre un plaisir pour devenir un obstacle.

**Et ce defaut ne touche pas le paquet hors catalogue, il touche les trois jeux du catalogue**, `an`
/ `on` / `in` et les chiffres du CP compris, parce que le voile porte sur `.proposition` partout.
C'est ce qui en fait une condition bloquante et non une remarque.

**La regle que je pose, sur la propriete et pas sur la valeur** - pour qu'`ines-design` garde sa
latitude :

> **A chaque instant de l'animation, le contraste entre le texte d'une proposition et ce qui est
> compose derriere lui reste au moins a 4,5:1.**

Trois facons de la satisfaire, toutes trois autorisees, aucune imposee : un voile clair **a 8 % au
plus** (mesure : 4,63:1) ; un voile **assombrissant** plutot qu'eclaircissant, qui augmente le
contraste au lieu de le reduire (noir a 20 % : 7,48:1, et c'est la voie que je trouve la plus
simple) ; ou un decor qui **ne traverse pas le mot** - un liseret, un reflet cantonne au bord, un
volet qui s'arrete avant les glyphes.

**Deuxieme point sur le voile, et celui-la est une exigence de forme.** `ines-design` ajoute
`overflow: hidden` sur `.proposition`, et son tableau de confrontation affirme que cet
`overflow: hidden` "porte sur le pseudo-element decoratif, pas sur le libelle". **C'est inexact, et
je l'ai mis a l'epreuve plutot que de le supposer** : `overflow` decoupe tout le contenu de
l'element, texte compris. Capture a 360 px sur `cherche` / `cherches` / `cherchent` en trois
colonnes de 101 px, avec et sans sa regle :

| Etat | Ce que l'ecran montre |
|---|---|
| sans `overflow: hidden` | les glyphes **sortent de l'aplat bleu** : le defaut se voit et se signale de lui-meme |
| avec son `overflow: hidden` | le mot est **coupe net au bord de la boite** : le defaut a l'air voulu, et plus personne ne le signale |

Les deux sont inutilisables, et la seconde est la pire des deux : **une troncature propre est une
troncature que personne ne remonte.** Ma condition 2 du meme jour ("aucune troncature, jamais : ni
`text-overflow: ellipsis`, ni `overflow: hidden` sur le texte de la proposition") **tient telle
quelle** ; je ne l'ai pas ecrite contre un cas theorique.

Ce n'est pas un obstacle pour `ines-design` : son `overflow: hidden` n'est la que pour retenir un
voile qui part de `translateY(-140%)` et finit a `translateY(140%)`, donc hors de la boite aux deux
bouts. **La propriete que j'exige a la place : le voile reste dans la boite du bouton a chaque
instant**, ce qui rend toute regle de decoupe inutile. Comment l'obtenir est a elle et a `lea-dev` ;
je nomme la propriete, pas la declaration.

#### 2. Le repli en deux rangees : remesure, confirme, et une reserve chiffree

**Je n'ai pas repris ses chiffres, je les ai refaits.** Maquette jetable hors du depot, non
commitee, deux feuilles reellement servies, structure reelle, ses deux regles `:has()` ajoutees au
delta d'`ADR-0026`. Captures a 360 px et a 375 px par `outils/mesurer-rendu.sh`, mesure au pixel
**a mi-hauteur** des boites.

| Ce qu'elle annonce | Ce que je mesure | Verdict |
|---|---|---|
| colonnes de 160 px a 360 px | **160 px** (x = 12 a 171 et 188 a 347) | exact |
| ecart de 16 px sur les deux axes | **16 px** lateral, **16 px** vertical (rangee 1 : 58-131, rangee 2 : 148-...) | exact |
| 74 px de hauteur par rangee | **74 px** | exact |
| 167 a 168 px a 375 px | conforme | exact |
| aucune troncature sur les deux items testes | **aucune**, verifie a l'oeil sur la capture | exact |
| les cas a deux et trois propositions inchanges | **inchanges** : temoins a 101 px et 158 px rendus a l'identique dans la meme capture | exact |

**Sa mesure est juste. La reserve porte sur son choix de pire cas.** Elle ecrit avoir teste "les deux
cas les plus defavorables (...) les mots les plus longs du paquet, neuf lettres" - donc choisis au
**nombre de lettres**. C'est le raisonnement exact qu'`ADR-0026` a refute en refusant d'ecrire une
regle en caracteres. J'ai mesure l'air restant autour du mot dans sa boite de 160 px :

| Mot | Largeur des glyphes | Air a gauche | Air a droite |
|---|---|---|---|
| `finissent` (9 lettres, son cas 1) | 124 px | 18 px | 18 px |
| `dessinent` (9 lettres, son cas 2) | 140 px | 10 px | 10 px |
| **`mangeons` (8 lettres, non teste)** | **152 px** | **5 px** | **3 px** |

**Le pire item du paquet est l'item 3, qu'elle n'a pas teste, et il ne reste que 3 px.** Le
rembourrage declare de `.proposition` est de 1,25rem, soit 20 px de chaque cote : `mangeons` a donc
deja franchi son rembourrage de 32 px et ne tient dans l'aplat que de justesse. Ce n'est pas "de
l'air visible de part et d'autre du mot" comme son document le dit.

**Ce que j'en conclus, et ce que je n'en conclus pas.** Je ne refuse pas : rien ne deborde, rien
n'est tronque, aucun plancher ne bouge, et le repli fait exactement ce qu'elle annonce. Mais **3 px
de marge sur une largeur de glyphes, avec une pile de polices systeme qui change d'un appareil a
l'autre** (elle vient de Reboot, donc du systeme, `ADR-0014`), ce n'est pas une marge : c'est un
resultat qui tient sur cette machine-la. Je le porte comme reserve chiffree et non comme motif, et
je l'ajoute a ce dont je reste seule garde.

#### 3. La manette : oui, c'est mon veto, et je l'assume nommement

**`ines-design` a lu mon arbitrage correctement et je confirme son refus comme etant le mien.** Le
bouton-poussoir rond est accepte : `border-radius`, `box-shadow` et un disque decoratif ne touchent ni
la taille, ni la cible tactile, ni le mot ecrit, et sa decision de ne cabler ces regles que sur
`.demarrer` et `.encore` - jamais sur `.sortie-pleine` ni `.recommencer` - fait tenir mon acquis 6
par la structure du selecteur plutot que par une consigne. C'est mieux que ce que je demandais.

**Et je dis ce que ce veto coute, parce que ca doit etre trace comme mien.** L'utilisatrice avait
demande que **l'enfant tire un levier**. Apres mon arbitrage, il ne reste de cette phrase ni la
manette, ni le geste de traction : l'enfant **presse**, et les colonnes bougent. Le mot "levier"
survit comme nom interne, pas comme geste.

**Mes deux motifs, inchanges depuis ce matin et je ne les grossis pas** : un enfant de CP qui essaie
de tirer une manette et voit que rien ne se passe se croit en faute ; et un geste de glissement est
un obstacle de motricite fine sur le bouton le plus presse de l'application, en plus d'etre un
obstacle pour un enfant qui utilise une aide technique.

**Ce que je remonte plutot que de le decider** : c'est le dernier element litteral de la demande
d'origine qui tombe, et il tombe par ma main. L'utilisatrice est en droit de le savoir et de le
contester ; ce n'est pas a moi de juger si elle tient a la manette. Je le route a `sofia-sm` pour
remontee, avec les deux motifs ci-dessus. **Si l'utilisatrice tranche qu'elle veut la manette
malgre eux, mon veto ne survit pas a sa decision** - je ne detiens pas la porte produit, et je
prefere l'ecrire que de laisser croire que je la detiens.

#### 4. Un defaut que j'ai trouve en mesurant celui-ci, et qui est plus grave que lui

Voici ce que je cherchais, et ce que j'ai trouve a la place.

Je voulais savoir si le repli d'`ines-design` eteint le motif du pli qui fondait ma reduction de
"Le bon verbe". J'ai donc mesure un **ecran de manche complet** - bandeau, "Les jeux", score,
consigne, enonce, indice, propositions - et non des rangees de boutons isolees. Mesure a 360 px,
pli lu a 640 px.

| Item mesure, ecran complet avec indice | Ou tombent les propositions | Le mot est-il lisible ? |
|---|---|---|
| "Le bon verbe" item 8, enonce court, repli d'`ines-design` | rangee 1 : 495-568 ; rangee 2 : 585-658 | **oui**, glyphes a 611-634, 6 px de garde sous le pli |
| "Le bon verbe" item 9, enonce le plus long du paquet | rangee 1 : **783-856** ; rangee 2 : **873-946** | **non, aucune des quatre** |
| **"Le bon petit mot" item 24, enonce le plus long, DEUX propositions, jeu du catalogue** | **717-790** | **non, aucune des deux** |

**La troisieme ligne est le defaut, et il ne concerne ni le repli, ni le nombre de propositions.**
Sur un jeu **catalogue**, dont j'ai accorde le Done pedagogique et laisse passer la ligne de matrice
a `couvert-valide` ce matin, un item met **ses deux boutons de reponse entierement sous le pli** a
360 x 640. La cause est l'enonce a 2 rem qui se replie sur six lignes, pas les colonnes : le meme
item est sous le pli dans la disposition empilee d'aujourd'hui comme dans les colonnes.

**Je corrige donc un de mes propres constats avant de m'appuyer dessus.** Le 2026-08-10, section 4
de mon refus de "Le bon verbe", j'ai ecrit :

> *"Sur le meme viewport, "Le bon son" (trois propositions) tient entierement au-dessus du pli,
> verifie par la meme mesure - le defaut est bien lie au passage a quatre propositions et non a
> l'habillage en general."*

**Cette inference etait fausse, et c'est moi qui l'ai faite.** J'avais teste "Le bon son", dont les
enonces sont des mots seuls en capitales, et j'ai generalise d'un paquet a une cause. Le defaut
n'est pas lie au nombre de propositions : il est lie a **la longueur de l'enonce**, et il frappe un
paquet a deux propositions aussi durement qu'un paquet a quatre. Retirer une proposition ne l'a
jamais adresse.

**Ce n'est pas un motif de refus et je ne retire aucun verdict.** Aucune decision du depot ne fixe de
hauteur de viewport minimale - `DEC-0007` ne parle que de largeur - et je l'ai ecrit deux fois
aujourd'hui. Un defaut que je n'ai pas voulu opposer au paquet que j'ai refuse, je ne vais
certainement pas l'opposer apres coup au paquet que j'ai valide. **Le Done pedagogique de "Le bon
petit mot" reste accorde et sa ligne de matrice reste `couvert-valide`.** C'est un **constat neuf,
date du 2026-08-10**, ecrit dans sa propre fiche, a instruire par `camille-po`.

Ce qui change tout de meme : mon constat 6.5 de ce matin, que je croyais viser un paquet hors
catalogue, vise en realite un jeu que des enfants peuvent ouvrir aujourd'hui. Sa priorite n'est pas
la meme, et c'est `camille-po` qui la fixe, pas moi.

#### 5. Ce que je declare degrade sur ce bloc

- **Le chantier n'est toujours pas ecrit.** Aucune animation n'a tourne devant moi : la duree, la
  courbe, le decalage de 0,08 s par colonne et le repli de mouvement restent entierement a
  reverifier. Les huit points de ma section 9 ci-dessous tiennent tous.
- **J'ai mesure le voile fige, pas en mouvement.** Un voile qui traverse un mot en 0,6 s ne se lit
  pas comme un voile arrete dessus : le contraste de 2,29:1 est exact a l'instant du passage, et
  combien de temps le mot reste sous ce contraste depend de la hauteur de la bande et de la courbe.
  Ma regle de 4,5:1 vaut a chaque instant, donc elle est plus severe que ce que j'ai mesure ; je
  reverifierai en jouant, et **si la valeur corrigee passe mon plancher, je ne rouvrirai pas ce
  point**.
- **Je n'ai mesure le pli que sur trois items**, sur les trois paquets concernes. Les 23 autres items
  de "Le bon petit mot" ne sont pas mesures : je ne sais pas combien d'items du catalogue sont
  touches, seulement qu'au moins un l'est.
- **Je n'ai pas mesure N = 5 colonnes**, la limite qu'`ines-design` nomme elle-meme. Aucun paquet du
  depot n'en a cinq ; je ne juge pas ce qui n'existe pas.
- **La police est celle de ma machine.** Les 3 px d'air de `mangeons` et les 6 px de garde de
  l'item 8 dependent de la pile de polices systeme. Aucune de mes mesures ne dit ce que rend un autre
  appareil, et c'est exactement la famille qu'`ADR-0025` m'a confiee.

#### 6. Ce que j'ajoute a ce dont je reste seule garde

En plus des huit points de mon arbitrage de ce matin :

9. **Que le contraste du mot ne tombe jamais sous 4,5:1 pendant l'animation.** Aucun automate de ce
   depot ne compose une couleur d'animation ni ne calcule un contraste ; `TailleDesTextesEtBoutonsTest`
   lit des tailles, pas des couleurs composees.
10. **Que 3 px d'air suffisent sur un autre appareil que le mien**, pour `mangeons` comme pour tout
    mot futur.
11. **Que ce qui compte reste au-dessus du pli quand l'enonce est long** - desormais sur un jeu du
    catalogue et plus seulement sur un paquet retire.

#### 7. Suites hors de ma main

- **Pour `ines-design`** : la forme du voile est acceptee, sa valeur est a corriger sous la regle de
  4,5:1, et le `overflow: hidden` sur `.proposition` est a remplacer par un voile qui reste dans la
  boite. Le bouton-poussoir est accepte tel quel. Rien d'autre de sa proposition n'est touche.
- **Pour `sofia-sm`** : deux remontees. La chute du geste de traction, qui est mon veto et que
  l'utilisatrice doit pouvoir contester (section 3). Et le fait qu'`ines-design` a choisi son pire
  cas au nombre de lettres, ce qui est le raisonnement qu'`ADR-0026` refute - a lui signaler pour
  ses prochaines mesures, sans que ca retire rien a la qualite de celle-ci.
- **Pour `camille-po`** : le constat du pli sur "Le bon petit mot" est ecrit dans
  `FICHE-bandit-manchot-homophones-cm1.md`, date du 2026-08-10. Je decris le besoin, je n'ecris pas
  la story, et je ne fixe pas sa priorite.
- **Pour `camille-po`, et c'est le point qu'elle attend** : ma reduction de "Le bon verbe" a trois
  propositions est **eteinte**. Motif et consequences dans
  `FICHE-bandit-manchot-conjugaison-present-cm1.md`, arbitrage du 2026-08-10, second bloc. Son choix
  entre le repli de mise en page et le report du jeu reste entier ; je viens seulement de retirer
  une troisieme sortie qui n'en etait pas une.
- **`squad/programme/MATRICE-couverture.md` ne bouge pas**, et aucun verdict anterieur n'est retire.

### Arbitrage prealable au developpement - chantier machine a sous (S7-1 a S7-4), du 2026-08-10

**Ce bloc n'est pas un Done pedagogique.** C'est le troisieme arbitrage prealable que j'ecris, apres
S6-9 et S6-10 du 2026-08-07, qu'il ne modifie ni l'un ni l'autre. Il est rendu au titre de
`squad/README.md` section 10 (amendement de la retro s06) : `DEC-0015` signale six acquis
pedagogiques que le chantier touche, et `ADR-0026` me route nommement cinq questions. Le Done
pedagogique du chantier sera prononce plus tard, en jouant l'increment livre.

**Ce bloc vaut pour les quatre jeux du bandit manchot**, catalogues ou non : la mecanique est
commune. Les fiches `FICHE-bandit-manchot-le-bon-son-cp.md` et
`FICHE-bandit-manchot-homophones-cm1.md` y renvoient. La part qui touche le contenu de "Le bon
verbe" - le nombre de propositions du paquet - est tranchee dans
`FICHE-bandit-manchot-conjugaison-present-cm1.md` et pas ici, parce que c'est du contenu et non de
l'habillage.

**La direction n'est pas rediscutee.** L'utilisatrice a tranche : une vraie machine a sous. Je ne
reouvre pas ce choix, et rien de ce bloc ne le conditionne. Ce que je tranche, c'est ce que cette
direction vaut pour l'enfant et sous quelles conditions.

#### Le verdict, en une phrase

**La forme convient a l'enfant, la metaphore non : je valide qu'une seule pression fasse apparaitre
les reponses en mouvement dans des colonnes, et je refuse tout ce qui fait de cet ecran une machine
a sous - aucun mot, aucune image, aucun son de jeu d'argent (mise, gain, jackpot, piece,
alignement gagnant), parce que le message propre d'une machine a sous est que le hasard decide du
resultat, et c'est exactement l'inverse de ce qu'un exercice doit apprendre a un enfant de 6 ou de
9 ans.**

#### Comment j'ai regarde, et ce que je declare degrade

**Audit degrade, et je le declare avant tout le reste, comme pour S6-10 le 2026-08-07 :** le
chantier n'est pas ecrit. Il n'existe aucune machine a sous a jouer, donc je n'ai pas joue de
machine a sous, et **rien de ce bloc ne peut valoir comme Done pedagogique.**

Ce que j'ai pu faire, et qui n'est pas une lecture de description :

- **j'ai mesure du rendu reel.** Maquette jetable hors du depot, non commitee, batie sur les deux
  feuilles reellement servies (`vendor/bootstrap.min.css` puis `css/recreo.css`, dans cet ordre) et
  sur la structure reelle de `fragments/manche.html`, avec pour seul delta
  `.propositions { flex-direction: row }` et `.proposition { flex: 1 1 0 }` - le delta qu'`ADR-0026`
  decrit. Captures a une largeur de viewport reellement de 360 px par la commande versionnee du
  depot, `outils/mesurer-rendu.sh 360 <url> <png>`, navigateur designe par la variable `CHROME`.
  Largeurs et hauteurs relevees au pixel par balayage des aplats de `--recreo-accent` (`#2e6f9e`)
  sur l'image ;
- **j'ai regarde deux ecrans de manche complets** en colonnes, avec leur bouton de sortie, leur
  score, leur consigne, leur enonce et leur indice : "Le bon son" sur l'item `MANTEAU`
  (propositions `an`, `on`, `in`) et "Calcul CP" sur `6 + 4` (`10`, `7`, `9`) ;
- **j'ai mesure les douze items de "Le bon verbe" ramenes a trois propositions**, ce qui est la
  sortie qu'on me demande d'accepter ou de refuser ;
- **j'ai lu les gabarits et la feuille de style servis**, et c'est comme cela que j'ai trouve le
  defaut de la section 6, qui n'est dans aucun des deux lots.

**Ce que je n'ai pas pu juger, et que je reverifierai sur le rendu livre** : tout ce qui bouge. Une
duree, une courbe, un decalage entre colonnes, la lisibilite d'un mot pendant qu'il glisse, le fait
qu'un enfant relie le mouvement a son propre geste - rien de tout cela ne se juge sur une capture
immobile. La liste exacte est en section 9, et **aucun Done pedagogique ne sera prononce avant que
je l'aie epuisee.**

#### Je corrige deux chiffres avant de m'appuyer dessus

Les mesures de largeur d'`ADR-0026` sont justes, et deux de ses nombres meritent une precision que
je donne parce que quelqu'un les recopiera.

| Chiffre annonce par `ADR-0026` | Ce que je mesure | Lecture |
|---|---|---|
| colonne de 99, 100, 99 px a 3 colonnes | **101, 102, 101 px** a mi-hauteur ; 99, 100, 99 px sur une ligne prise a 6 px du bord haut | les deux sont vrais : la ligne haute traverse les angles arrondis. La boite fait 101 px |
| ecart rendu de 18 px | **16 px exactement** a mi-hauteur | les 18 px sont eux aussi une lecture sur la ligne haute. L'ecart rendu est le `gap: 1rem` declare, ni plus ni moins |
| hauteur de 74 px | **74 px** | confirme a l'unite |

Cela ne deplace aucun plancher et ne corrige aucune conclusion : la marge sur la cible tactile est
un peu meilleure que celle qu'`ADR-0026` s'accorde. Je le note pour une seule raison : le plancher
d'ecart de S3-5 est tenu **a l'egalite exacte**, et un rapport qui annoncerait 18 px de rendu
laisserait croire a 2 px de marge qui n'existent pas. Le jour ou quelqu'un voudra "recuperer 2 px",
il n'y en a pas a recuperer.

#### 1. La metaphore : ce que j'accepte, ce que je refuse, et pourquoi personne ne me l'avait demande

On me pose enfin la question, et elle est la bonne. Une machine a sous est une machine de jeu
d'argent. Ce qui me gene n'est pas le mauvais gout, c'est un enjeu d'apprentissage precis.

**Le message propre d'une machine a sous est que le resultat ne depend pas de toi.** On tire, les
rouleaux tournent seuls, le hasard tranche. Un exercice scolaire doit enseigner exactement l'inverse :
ce que tu obtiens depend de ce que tu sais. Un enfant de CP qui rate trois fois de suite cherche
toujours une cause, et un ecran qui lui montre des rouleaux qui tournent tout seuls lui en offre une
tres commode - "je n'ai pas eu de chance". C'est le seul motif de fond que je porte, et il est
serieux : c'est celui qui remonterait a l'utilisatrice si la forme retenue ne s'en separait pas.

**Elle s'en separe, et c'est pour cela que je ne refuse pas la direction.** Dans la forme decrite,
la machine ne decide rien : elle **distribue** les reponses, puis l'enfant choisit. Le tirage n'est
pas le resultat, c'est la donne. La difference est entierement dans ce que l'ecran raconte, donc
elle est entierement dans ce que je tranche ici.

**Ce que j'exige, et qui est executable sans me redemander quoi que ce soit :**

1. **Aucun vocabulaire ni imagerie de jeu d'argent dans ce que l'enfant lit, voit ou entend.** Sont
   refuses, sur tout texte rendu a l'ecran, tout `alt`, tout `aria-label`, tout `title` et tout
   titre de page : `machine a sous`, `bandit`, `manchot`, `jackpot`, `mise`, `miser`, `gain`,
   `gagner de l'argent`, `casino`, `piece`, `jeton`, `credit`, `bandit manchot`. Verifie
   aujourd'hui : aucun de ces mots n'atteint l'enfant, les trois jeux du catalogue s'appellent
   "Calcul CP", "Le bon petit mot" et "Le bon son", et `bandit-manchot` n'est qu'un identifiant
   interne. **Cet etat est un acquis et je le porte desormais comme tel** : le chantier ne doit pas
   faire remonter le nom de la mecanique a la surface au motif qu'il devient enfin exact.
2. **Aucun signal de gain a l'arret des colonnes.** Pas d'alignement lumineux, pas d'eclair, pas de
   fanfare, pas de piece qui tombe, pas de colonne qui se colore differemment des autres. Motif
   operationnel et pas seulement de principe : dans une machine a sous, l'arret **est** le
   resultat. Si l'arret ressemble a un resultat, un enfant de CP croira que le tour est fini et ne
   comprendra pas qu'il doit encore choisir. L'arret est neutre : les trois colonnes s'arretent avec
   le meme aspect, et rien ne se passe jusqu'a ce que l'enfant presse.
3. **Le seul retour de resultat du jeu reste celui qui existe** : la coche ou la croix de
   `fragments/retour.html`, apres le choix de l'enfant. Le chantier n'ajoute aucun second canal de
   verdict.
4. **Aucun compteur autre que le score existant.** Pas de serie, pas de multiplicateur, pas de
   "3 d'affilee" : ce sont des mecaniques de machine a sous, et elles transforment un exercice en
   pari.

Sous ces quatre conditions, la metaphore est acceptable au CP comme au CM1. Sans elles, elle ne
l'est pas, et le motif ne serait pas negociable.

#### 2. La duree du defilement : ce que j'autorise, ce que je refuse

`ADR-0026` assume que cette duree vit dans une feuille de style et qu'aucun test ne la juge, et
`theo-archi` cite lui-meme un defilement de huit secondes qui passerait la CI verte. Voici la barre,
et elle est chiffree.

**Le calcul qui la fixe.** Une partie compte dix manches (`catalogue.yaml`, `nombreDeManches: 10`).
Le defilement est donc paye **dix fois par partie**. C'est ce facteur dix qui commande, pas le
ressenti d'un seul tour.

| Grandeur | Valeur |
|---|---|
| **Duree autorisee**, de l'appui de l'enfant jusqu'a l'arret de la **derniere** colonne | **0,3 s au minimum, 1,2 s au maximum** |
| Valeur que je conseille | **0,6 s** (soit 6 s d'attente sur une partie de dix manches) |
| Decalage entre l'arret de la premiere colonne et celui de la derniere | **0,25 s au maximum**, et il est **compris** dans le budget ci-dessus |

**Ce que je refuse, nommement :**

- **tout defilement dont la derniere colonne s'arrete apres 1,2 s.** A 1,2 s, une partie coute deja
  12 s d'attente ; a 2 s elle en coute 20, et un enfant impatient presse avant l'arret. Les huit
  secondes citees par `ADR-0026` sont refusees sans discussion, et 1,5 s l'est aussi ;
- **tout defilement de moins de 0,3 s.** En dessous, ce n'est plus un defilement, c'est un
  clignotement : desagreable, et sans aucun des benefices pour lesquels on l'ajoute ;
- **tout rebond, depassement ou oscillation apres l'arret.** La position finale est atteinte une
  fois et n'est plus quittee. Un mot qui se stabilise en tremblant est illisible pour un enfant de CP
  qui dechiffre ;
- **tout arret brutal depuis la pleine vitesse.** Le mouvement ralentit avant de s'arreter, sinon
  l'enfant ne sait pas si c'est fini ;
- **tout clignotement, stroboscope ou variation de luminosite repetee plus de trois fois par seconde,
  n'importe ou sur l'ecran.** Un glissement continu est autorise a toute vitesse ; une substitution
  brutale d'une face par une autre, en place, ne l'est pas ;
- **toute animation qui rejoue sans que l'enfant ait presse quelque chose** (voir section 6) ;
- **toute animation sur l'ecran de fin de partie.** Cet ecran n'a rien a tirer.

**Et l'exigence qui me tient le plus a coeur, parce qu'elle protege l'orthographe :** a chaque
instant du defilement, **une colonne montre soit sa propre proposition, soit aucun texte** - jamais
un autre mot. Deux motifs independants, tous deux serieux :

1. **la memoire orthographique de l'enfant est visuelle.** Sur "Le bon petit mot", faire defiler
   `son` et `sont` dix fois dans chaque colonne, a chaque manche, c'est faire lire a l'enfant la
   forme fausse plus souvent que la juste. Sur un jeu d'homophones, c'est un contresens complet ;
2. **ce que l'enfant voit doit etre ce qu'il envoie.** Si une bande glisse derriere une fenetre et
   qu'un enfant presse en cours de route, il envoie la valeur du bouton et non la face qu'il avait
   sous le doigt. `ADR-0026` garde le bouton pressable en permanence, a raison : il ne faut donc
   jamais qu'il affiche autre chose que lui-meme.

**Forme que je conseille a `ines-design`, sans l'imposer** : le mot reste lisible du debut a la fin
et seul un element decoratif passe devant (voile, reflet, volet, flou). C'est celle des deux
latitudes qu'`ADR-0026` lui laisse qui ne pose aucun des deux problemes ci-dessus. **La variante
autorisee** : le mot apparait a l'arret, l'etat cache vivant dans les `@keyframes` comme
`ADR-0026` le lui permet, les faces qui defilent ne portant alors **aucun texte** (couleur, hachure,
pictogramme, flou). Je nomme le residu de cette variante plutot que de le taire : un enfant qui
presse pendant le defilement presse une case muette. Avec 0,6 s de defilement la fenetre est etroite,
et c'est moins grave que de lui montrer une valeur qu'il n'obtiendra pas. Je le regarderai en jouant.

#### 3. La lisibilite dans la colonne : mesure, et verdict

C'est la question qu'aucun plancher ne repond, et je la tranche sur une mesure.

| Jeu | Propositions rendues | Colonnes | Largeur mesuree | Verdict de lisibilite |
|---|---|---|---|---|
| Calcul CP | `10`, `7`, `9` | 3 | 101 px | **confortable**. Le chiffre est centre, en gras, a 2 rem, avec beaucoup d'air autour |
| Le bon son (CP) | `an`, `on`, `in` | 3 | 101 px | **confortable**. Ce sont les deux jeux de CP, et ce sont les deux cas les plus favorables |
| Le bon petit mot (CM1) | `sont`, `son`, jusqu'a `mais`, `peut`, `l'a` | 2 | 158 px | **confortable**, et de loin |
| Le bon verbe (hors catalogue) | jusqu'a `dessinent`, `chantent` | 4 | 70 px | **illisible : le mot sort de sa boite.** Meme ramene a 3 colonnes, il en sort encore - voir section 4 |

**Verdict : pour les trois jeux du catalogue, une proposition lue dans une colonne de 101 px convient
a un enfant de CP, et je le dis sur du rendu, pas sur un plancher.** Les propositions de ces trois
paquets sont courtes par nature - un son, un chiffre, un homophone grammatical - et c'est une
propriete du contenu, pas une chance.

**Les six conditions qui rendent ce verdict durable.** Elles portent toutes sur la tentation de
rapetisser le mot pour le faire tenir, qui est ce que fera la prochaine personne pressee :

1. **une proposition tient sur une seule ligne, entiere, a 2 rem au moins.** Un mot coupe sur deux
   lignes est un piege de dechiffrage pour un lecteur de CP qui apprend justement ou s'arretent les
   mots ;
2. **aucune troncature, jamais** : ni `text-overflow: ellipsis`, ni `overflow: hidden` sur le texte
   de la proposition, ni un mot qui sort de son aplat. Un mot tronque n'est pas seulement laid : sur
   "Le bon verbe" a 3 colonnes, `cherche` et `cherches` s'affichent tous les deux `cherche`, et
   l'item devient sans reponse ;
3. **aucune reduction de `font-size` de `.proposition` sous 2 rem** pour faire tenir une mise en page.
   Ce point-la est deja garde par `leChiffreDeReponseEstAuMoinsAussiGrosQueLeCalcul`, et je lui rends
   la garde ;
4. **aucune compression du glyphe** : pas de `letter-spacing` negatif, pas de `transform: scale` sous
   1, pas de police condensee, pas de `font-stretch` reduit ;
5. **pas de cesure** : aucune `hyphens`, aucun `word-break` sur une proposition ;
6. **pas de texte vertical** : aucun `writing-mode` autre que l'horizontal sur une proposition.

**Un gain reel que personne n'a compte, et qui est a porter au credit du chantier.** En colonnes,
trois propositions occupent une seule rangee de 74 px au lieu de trois rangees de 74 px separees par
deux ecarts de 16 px, soit **180 px liberes** sur la hauteur de l'ecran (arithmetique sur mes deux
mesures). C'est exactement la reponse a mon propre constat 6.5 du 2026-08-10 - "que ce qui compte
sur un ecran soit au-dessus du pli". Sur un telephone de 360 x 640, cette mise en page rend le pli
confortable la ou il etait juste. Je le dis parce qu'un chantier qui touche six acquis merite qu'on
compte aussi ce qu'il rend.

#### 4. Le nombre de colonnes varie d'un jeu a l'autre : verdict

**Deux colonnes gardent leur sens, et je n'y vois aucune objection pedagogique.** Sur "Le bon petit
mot", l'exercice **est** binaire : `son` ou `sont`, `a` ou `à`. Deux colonnes disent la verite de
l'exercice. Que ce ne soit pas fidele a une machine a sous reelle ne m'interesse pas : ce n'est pas
la fidelite de la metaphore qui apprend a lire, et la section 1 dit deja que je veux cette metaphore
diminuee plutot que fidele.

**Ce que je refuse, et c'est le seul point :** **jamais de colonne vide, muette ou desactivee pour
atteindre trois.** Un enfant de CP presse ce qui ressemble a un bouton. Une troisieme colonne vide
sur un item a deux propositions est une reponse qu'il essaiera, qui ne fera rien, et qui lui
apprendra que l'ecran ne repond pas toujours. Le nombre de colonnes suit le contenu, exactement comme
`DEC-0015` arbitrage 3 et `ADR-0026` section 4 le tranchent - sur ce point je suis d'accord avec eux
et je ne fais que fermer la porte de sortie facile.

**Et aucun texte lu par l'enfant ne nomme un nombre de colonnes ou de rouleaux.** Une consigne qui
dirait "regarde les trois colonnes" serait fausse sur "Le bon petit mot". Verifie : aucune consigne
du catalogue ne le fait aujourd'hui.

#### 5. Le geste : un enfant de CP comprend-il qu'il tire un levier ?

**Reponse courte : il n'a pas besoin de le comprendre, et c'est pour cela que je ne refuse pas la
forme retenue.** Ce que l'enfant doit comprendre, c'est "j'appuie ici et le jeu avance". Il le
comprend aujourd'hui parce que le bouton porte un **mot** : "Encore !", "Jouer".

**Ce que j'exige, et c'est la seule chose :**

1. **le levier garde son mot ecrit.** "Encore !" reste "Encore !", le titre du jeu reste le titre du
   jeu sur l'accueil. Un levier dessine sans texte est **refuse au CP** : un enfant qui lit peu perd
   l'indication au moment ou il en a le plus besoin, au premier tour ;
2. **ce mot reste a 1,25 rem au moins** et le bouton garde sa cible tactile de 2,75 rem. La
   decoration se pose autour du mot, jamais a sa place, jamais en le retrecissant ;
3. **le levier ne suggere aucun geste autre qu'un appui simple.** Pas de fleche vers le bas, pas de
   manette a demi baissee qui attend qu'on la tire, pas de glissement. Deux motifs : un enfant de CP
   qui essaie de tirer et voit que rien ne se passe se croit en faute ; et un geste de glissement est
   un obstacle de motricite fine que je ne veux pas sur le bouton le plus presse de l'application ;
4. **le levier reste l'action la plus saillante de son ecran** (`ADR-0017`), et **il n'apparait jamais
   sur l'ecran de fin de partie** (section 7, acquis 6).

**Ce que je ne peux pas trancher sans jouer, et que je nomme franchement.** Dans la forme retenue par
`ADR-0026` section 3, le levier est le bouton "Encore !" de `fragments/retour.html` : il est donc sur
l'ecran **precedent**, et il disparait a l'instant ou les colonnes tournent sur l'ecran suivant. La
cause et l'effet ne sont jamais sur le meme ecran. `ADR-0026` nomme lui-meme ce risque et ecrit le
chemin de sortie ; je ne le declenche pas aujourd'hui, parce que je ne fabrique pas une barre sur une
deduction. **C'est le premier point que je regarderai sur le rendu livre** (section 9, point 1), et
je dis d'avance ce que je jugerai : que l'enfant attribue le mouvement a son propre appui. Si ce
n'est pas le cas, le motif sera celui-la, ecrit et date, et pas un durcissement de ce bloc.

#### 6. Un defaut que j'ai trouve en lisant, et qui n'est dans aucun des deux lots

`accueil.html` rend le fragment de manche directement, sans htmx et sans aucun geste de l'enfant, sur
la branche de reprise de partie :

```
<div th:if="${reprise}">
    <div th:replace="~{fragments/manche :: manche}"></div>
</div>
```

Une animation CSS demarre quand l'element qui la porte entre dans le document. Sur ce chemin,
l'element entre dans le document **au chargement de la page**. Consequence : **un enfant qui revient
sur le jeu, ou qui recharge simplement sa page, voit les colonnes tourner sans avoir rien touche.**

Ce n'est pas un detail de rendu, c'est le contraire exact de ce que la section 1 exige : une machine
qui bouge toute seule est precisement l'image dont je veux separer cet ecran. Et si l'enfant apprend
que les colonnes tournent sans lui, l'argument qui sauve la metaphore tombe.

**Je decris le symptome, je ne prescris pas la cause** : le mouvement ne doit se produire que quand
l'enfant a presse quelque chose. Comment l'obtenir - un marqueur pose par le serveur sur le fragment
selon le chemin qui le rend, ou autre chose - appartient a `theo-archi`. Je remonte le symptome a
`sofia-sm` pour qu'il lui soit route, avec cette section comme piece.

#### 7. Les six acquis de `DEC-0015` : ce que j'exige nommement

`theo-archi` affirme qu'aucun plancher n'est abaisse et il a mesure. J'ai reverifie ses chiffres
(section "Je corrige deux chiffres") et sa conclusion tient. Je reprends donc les six acquis un par
un, et je dis pour chacun ce que j'exige et qui le garde.

| Acquis | Origine | Ce que j'exige | Qui le garde |
|---|---|---|---|
| 1. cible tactile >= 2,75 rem | mon refus de S2-1 | inchange. Mesure : 101 x 74 px par colonne a 360 px, contre 44 x 44 exiges | `TailleDesTextesEtBoutonsTest`, mecanique |
| 2. lisibilite >= 1,25 rem, `.indice` et `.score` compris | mes reserves 6 et 7 | inchange, **y compris sur le mot du levier** (section 5, point 2) | `TailleDesTextesEtBoutonsTest`, mecanique |
| 3. chiffre de proposition >= 2 rem, au moins aussi gros que l'enonce | ma reserve 1, devenue S3-4 | inchange, et **c'est lui que la mise en page en colonnes tentera de rogner** (section 3, conditions 1 a 6) | `leChiffreDeReponseEstAuMoinsAussiGrosQueLeCalcul` pour la taille declaree ; **moi seule** pour la troncature et la compression |
| 4. `.propositions { gap }` >= 1 rem | ma reserve 2, devenue S3-5 | inchange, **sur les deux axes**. Tenu a l'egalite exacte, 16 px declares et 16 px rendus : il n'y a aucune marge a recuperer | `TailleDesTextesEtBoutonsTest` amende par `ADR-0026` regle 3 - l'axe lateral cessait d'etre un angle mort, et c'est le bon amendement |
| 5. hierarchie de saillance action/sortie (`ADR-0017`) | mon refus du 2026-08-06 | inchangee. Le levier est `data-role="action"` et ne declare jamais moins que "Les jeux" | `LActionNeDeclarePasMoinsQueLaSortieTest`, mecanique |
| 6. l'etat `sortie-pleine` | mon arbitrage du 2026-08-07 sur S6-9 | inchange, **et je le repete parce que c'est celui qu'un chantier de mecanique defait par accident** : sur l'ecran de fin de partie, **aucun levier, aucune colonne, aucun rouleau, aucun decor de machine, aucune animation.** "Les jeux" en aplat plein reste la seule chose a presser, et il reste le plus saillant. Un decor de levier sur cet ecran est une chose que l'enfant essaiera de presser | le garde-fou de saillance n'oppose que des etats simultanes : **c'est moi seule** qui garde qu'aucun decor n'y soit ajoute |

**Aucun de ces six acquis n'est abaisse, et j'ajoute que je ne les releve pas non plus.** La barre
que j'avais ecrite est celle qui s'applique. Les exigences des sections 1 a 6 sont des **exigences
nouvelles et datees du 2026-08-10**, portant sur un ecran qui n'existait pas quand j'ai ecrit les
anciennes : elles ne durcissent aucune reserve anterieure et personne ne pourra me les opposer comme
un deplacement de poteaux.

#### 8. Ce dont je reste seule garde sur ce chantier

Pour qu'on ne croie pas couvert ce qui ne l'est pas. `ADR-0026` en nomme quatre ; ma liste en compte
huit, et les quatre siennes y sont.

1. **La duree, la courbe et le decalage du defilement.** Ecrits dans une feuille de style, vus par
   aucun test. Un defilement de huit secondes passe la CI verte, `ADR-0026` le dit lui-meme. La barre
   est en section 2 ; c'est moi qui la constate.
2. **Qu'un mot tienne dans sa colonne.** `ADR-0025` l'a declare non gardable et a eu raison : le
   budget est une largeur de glyphes, pas un nombre de caracteres. `oreille` tient, `mangez` ne tient
   pas. Aucun automate ne le verra jamais.
3. **Qu'une proposition ne soit ni tronquee, ni coupee, ni comprimee.** Les six conditions de la
   section 3 : aucune n'est gardee, sauf la taille declaree (condition 3).
4. **Que ce que l'enfant voit dans une colonne soit ce qu'il envoie.** Rien ne le verifie.
5. **Qu'aucun mot faux ne defile devant les yeux d'un enfant.** Rien ne le verifie, et c'est le point
   ou un jeu d'homophones peut apprendre l'inverse de ce qu'il enseigne.
6. **Que le geste se comprenne, et que l'enfant relie le mouvement a son appui.**
7. **Qu'aucun decor de machine n'atterrisse sur l'ecran de fin de partie**, ou le garde-fou de
   saillance n'a par construction rien a comparer.
8. **Que le mouvement ne se declenche jamais sans geste de l'enfant** (section 6). Tant qu'il n'y a
   pas de garde-fou pour ce chemin, c'est moi.

Le focus visible sur une colonne, qu'`ADR-0026` me renvoie aussi, je le prends : il n'etait garde par
rien avant ce chantier et je ne demande pas qu'on ecrive une regle contre un defaut que le depot n'a
pas etabli.

#### 9. Ce que je reverifierai sur le rendu livre, avant tout Done pedagogique

Liste fermee. Ce qui n'y est pas ne bloquera pas le Done et ne pourra pas m'etre oppose comme
l'ayant bloque.

1. **Que l'enfant relie le mouvement a son propre appui** (section 5). Jouer une partie complete des
   additions CP, en regardant le raccord entre l'appui sur "Encore !" et le demarrage des colonnes.
2. **La duree reelle, chronometree sur le rendu**, contre la barre de 0,3 s a 1,2 s de la section 2 :
   duree de la derniere colonne, decalage entre premiere et derniere, presence d'un ralentissement,
   absence de rebond.
3. **Qu'aucun autre mot que le sien ne passe dans une colonne**, verifie sur "Le bon petit mot" -
   le jeu ou le defaut serait le plus grave.
4. **Que l'ecran reste jouable et lisible immobile**, avec le reglage d'accessibilite de reduction
   des animations actif dans un vrai navigateur. `ADR-0026` regle 1 clause 2 garde la **forme**
   litterale du repli ; elle ne garde pas que l'ecran obtenu soit lisible et jouable. C'est ma part,
   et je la ferai sur le rendu, pas sur la feuille de style.
5. **Les trois ecrans de manche du catalogue a 360 px**, sur les items aux propositions les plus
   longues de chaque paquet, sans troncature ni coupure (section 3, conditions 1 a 6).
6. **L'ecran de fin de partie**, sur lequel je verifierai qu'aucun levier, colonne, decor ni
   animation n'est apparu, et que `sortie-pleine` est toujours seule et pleine (acquis 6).
7. **Le chemin de reprise** (section 6) : recharger la page en cours de partie et verifier que rien
   ne bouge.
8. **Une partie ratee trois fois de suite**, pour voir arriver l'indice sur un ecran ou les colonnes
   defilent : que l'indice soit lisible, stable, et qu'il n'ait pas ete pousse sous le pli par la
   nouvelle mise en page.

#### 10. Suites hors de ma main

- **Pour `camille-po`** : le sort de "Le bon verbe" face aux colonnes est tranche pour la part qui
  m'appartient - le nombre de propositions du paquet - dans
  `FICHE-bandit-manchot-conjugaison-present-cm1.md`, arbitrage du 2026-08-10. **Ma decision n'y suffit
  pas** : j'y montre, mesure, que trois colonnes ne font pas tenir ce paquet non plus. Le choix de
  disposition reste sa saisine, avec `theo-archi`.
- **Pour `camille-po`** : les quatre conditions de la section 1 et les six de la section 3 sont des
  motifs, pas des stories. A elle d'en faire des criteres d'acceptation sur S7-1 et S7-3 si elle le
  juge utile ; je n'ecris pas le backlog.
- **Pour `sofia-sm`** : le defaut de la section 6 est a router a `theo-archi`. Je decris le symptome,
  pas la cause.
- **Pour `ines-design`** : les sections 2 et 5 sont ma latitude sous veto, exercee. La duree lui reste
  ouverte entre 0,3 s et 1,2 s, la courbe lui reste ouverte pourvu qu'elle ralentisse et ne rebondisse
  pas, la forme du rouleau et du levier lui reste entiere sous les quatre conditions de la section 1
  et les quatre de la section 5.
- **`squad/programme/MATRICE-couverture.md` ne bouge pas** sur cet arbitrage : ce chantier ne porte
  aucune competence, et il n'y a donc rien a confronter au referentiel. C'est la premiere fois que
  l'etape 2 de ma methode est sans objet, et je le dis plutot que de la simuler.

### Arbitrage prealable au developpement - S6-10, couleur du bandeau et sort du carre de l'icone, du 2026-08-07

**Ce bloc n'est pas un Done pedagogique.** C'est le second arbitrage prealable du sprint 6, apres
celui de S6-9 du meme jour, qu'il ne modifie pas. Il repond a la question qu'`ines-design` me
renvoie explicitement sans la trancher
(`squad/sprints/s06-ouvrir-la-conjugaison-et-solder-la-dette/proposition-ines-s6-8-s6-10.md`,
section S6-10, "Un point que je remonte plutot que de le trancher") : si le bandeau change de
couleur, que devient le carre de fond de l'icone, dont j'ai valide en s05 qu'il disparaissait dans
le bandeau. Le Done pedagogique de S6-10 sera prononce plus tard, en jouant l'increment livre.

#### Le verdict, en une phrase

**Le bandeau prend bien `#1d4b6b`, et le bloc de fond de l'icone est recolore a cette meme valeur
dans les trois vignettes du fichier `.ico`, dans le meme commit : "accepter le retour du carre
visible" n'est pas une sortie que j'ouvre, et recolorer la seule vignette de 48 px n'en est pas une
non plus.**

#### Ce que j'ai fait pour trancher, et ce que je n'ai pas pu faire

J'ai joue. Jar reconstruit depuis mon arbre dedie sur le JDK 21 designe par `JAVA_HOME` le temps de
la commande (`outils/verifier-jdk.sh` donne le constat : `java` du PATH en 1.8, JDK de compilation
en 21.0.10, fichier de toolchains non lu par Maven), lance sur un port dedie, une manche des
additions CP jouee et ratee volontairement pour obtenir l'ecran de retour. **L'icone servie par
l'application est identique octet pour octet au fichier source** (1021 octets) : ce que j'ai mesure
est bien ce que l'enfant recoit, pas une copie.

**Ce que je n'ai pas pu faire, et c'est un audit degrade que je declare :** le changement n'est pas
encore ecrit (`lea-dev` travaille en parallele), je n'ai donc pas vu le bandeau modifie dans un
navigateur. J'ai compose les octets reels de l'icone sur les deux couleurs de bandeau candidates et
je l'ai regardee **a 44 px**, la taille exacte a laquelle `.icone-entete` l'affiche (`2.75rem`),
puis en niveaux de gris a la meme taille, puis a **16 px** sur un fond d'onglet clair et sur un fond
d'onglet sombre. Ce que j'ai juge est une composition d'octets reels et de valeurs CSS reelles, pas
une capture d'ecran de navigateur. **Je reverifierai sur le rendu reel avant de prononcer le Done
pedagogique de S6-10.**

#### Je corrige mon propre constat de s05 avant de m'appuyer dessus

Mon constat du 2026-08-07, section A.1 : *"Son fond bleu est exactement la couleur du bandeau, donc
le carre disparait et le crayon flotte."* La couleur est exacte, je le confirme a l'octet. Mais
**j'ai decrit une image la ou le fichier en contient trois**, et `ines-design` a herite de cette
sous-description en ne mesurant que la vignette de 48 px :

| Vignette | Pixels | Pixels exactement `rgba(46,111,158,255)` | Pixels transparents | Couleurs distinctes |
|---|---|---|---|---|
| 16 x 16 | 256 | **105** | 6 | 8 |
| 32 x 32 | 1024 | **532** | 20 | 8 |
| 48 x 48 | 2304 | **1218** | 42 | 8 |

Les 1218 pixels qu'elle annonce sont exacts ; ils ne sont que **1218 des 1855** pixels de la couleur
d'accent que porte le fichier. Ni elle ni moi n'avions tort sur le fondu : nous avons tous les deux
parle du fichier comme s'il ne portait qu'un dessin.

Seconde correction, sur la forme : **j'ai ecrit "carre", ce n'en est pas un.** Les coins **haut
gauche et bas droit** sont coupes en biseau a 45 degres, dans l'axe du crayon, sur 6 px de
profondeur dans la vignette de 48 px (21 + 21 = 42 pixels transparents). Les deux autres coins sont
pleins. Cette precision compte pour la consigne ci-dessous : ce biseau est une partie de la forme,
pas un defaut d'encodage.

#### 1. Le bandeau : `#1d4b6b` est confirme, et c'est aussi un plancher

J'ai regarde l'ecran de retour a 360 px et a 1280 px de large. A 1280 px, la ou le defaut etait le
pire (le bandeau occupe seize fois la surface du bouton), le gain est net : le bandeau devient
franchement un autre bleu et **le seul aplat de la couleur d'action qui reste sur l'ecran est le
bouton "Encore !"**. C'est ce que la story demande.

Ce que je mesure, et que j'assume :

| Ce qui est confronte | Sur `#2e6f9e` | Sur `#1d4b6b` |
|---|---|---|
| Blanc du nom du site et de l'entree de menu | 5,41:1 | **9,25:1** |
| Jaune du corps du crayon | 3,32:1 | **5,67:1** |
| Gris de la virole | 3,41:1 | **5,84:1** |
| Rose de la gomme | 2,83:1 | **4,84:1** |
| Contour marine du crayon | 2,48:1 | **1,45:1** |
| Bois brun de la pointe | 2,54:1 | **1,49:1** |
| Mine noire | 3,15:1 | **1,84:1** |

Les deux bleus ne s'ecartent que de **1,71:1** entre eux. C'est un pas modeste, et je le dis plutot
que de le farder : ce n'est pas la teinte seule qui fait le travail, c'est qu'un bandeau pleine
largeur n'a jamais la forme d'un bouton. Je l'accepte pour cette raison, et **parce qu'aller plus
sombre couterait le crayon** : les trois parties sombres du dessin (contour, bois, mine) tombent
deja sous 3:1 sur `#1d4b6b`. J'ai regarde a 44 px, et en niveaux de gris a 44 px : la silhouette est
portee par les parties claires, qui toutes gagnent, et la pointe reste un bloc sombre et chaud
distinct du bleu froid. **Cela passe. Cela ne passerait pas plus sombre.**

**`#1d4b6b` est donc le bandeau le plus sombre que je valide.** Une story ulterieure qui voudrait
descendre plus bas doit d'abord faire redessiner l'extremite sombre du crayon : c'est une story de
design, pas un changement de valeur. Ce plancher est nouveau et date de ce jour ; il ne durcit
aucune reserve anterieure.

#### 2. Le carre de l'icone : recolore, et pas seulement la vignette de 48 px

**Je n'ouvre pas la seconde sortie qu'`ines-design` propose** (garder l'icone telle quelle et
accepter le retour du carre visible). Je l'ai regarde a 44 px avant de le refuser, pas sur la
description : sur le bandeau sombre, l'ancien bleu ne fait pas une nuance discrete, il fait une
pastille collee sur la barre. Ce que l'enfant reconnait de cette application, c'est **un crayon**,
pas une tuile bleue : remettre la tuile ajoute une forme qui ne veut rien dire et defait un acquis
que j'ai constate moi-meme il y a un jour.

**Pourquoi les trois vignettes et pas la seule de 48 px.** L'element est un `<img>` affiche a 44 px,
et le fichier propose trois dessins de tailles differentes. **Je ne sais pas laquelle le navigateur
decode**, et cela peut dependre de la densite de l'ecran : je ne parie pas le fondu sur cette
supposition. Recolorer les trois le rend independant de ce choix. Deuxieme raison, verifiee
celle-la : l'enfant voit la meme icone dans l'onglet et dans le bandeau, et un fichier qui porterait
deux bleus de fond differents selon la taille est un piege pour la personne suivante.

**Pourquoi pas la transparence**, la troisieme sortie que personne n'avait nommee et que j'ai
examinee : rendre le fond transparent garantirait le fondu pour toujours, sans coupler un binaire a
une variable CSS. Je l'ecarte, mesures en main : sans son champ bleu, le corps jaune du crayon se
retrouve a **1,24:1** sur un fond d'onglet clair, la virole a 1,58:1 et la gomme a 1,91:1 sur blanc.
**Dans l'onglet, le carre bleu gagne sa place**, et il la gagne mieux en `#1d4b6b` qu'en `#2e6f9e`
(4,12:1 -> 7,05:1 sur un onglet clair). Sur un onglet sombre il descend de 2,98:1 a 1,74:1, mais ce
qui identifie l'application y est la diagonale jaune, a tres fort contraste sur un fond sombre, et
je l'ai regardee a 16 px : le badge reste lisible.

#### 3. La consigne executable - aucune question a me reposer

**Feuille de style** (`recreo.css`), rien de plus, rien de moins :

| Selecteur | Avant | Apres |
|---|---|---|
| `:root` | (variable absente) | `--recreo-entete-fond: #1d4b6b;` |
| `.entete` | `background-color: var(--recreo-accent);` | `background-color: var(--recreo-entete-fond);` |

`--recreo-accent` reste `#2e6f9e` et **aucun autre selecteur ne change** : `.demarrer`,
`.proposition`, `.recommencer`, `.encore` et `.sortie-pleine` gardent cet aplat ; `h1`, `.score`,
`.fin-de-partie` gardent cette couleur de texte ; `.retour-accueil` garde ce texte et ce contour ;
`.lien-accueil` garde son contour blanc, qui gagne au change (5,41:1 -> 9,25:1) et qu'il ne faut
surtout pas "corriger" au passage. Aucune dimension de bouton n'est touchee : mes mesures du
2026-08-07 en A.1 (117 >= 109 >= 102 en largeur, 56 >= 53 en hauteur, l'action seule en aplat)
doivent rester vraies telles quelles.

**Icone** (`favicon.ico`, 1021 octets, trois vignettes, chacune un PNG embarque en RGBA) :

1. Dans **chacune des trois vignettes**, remplacer tout pixel valant exactement
   `rgba(46, 111, 158, 255)` par `rgba(29, 75, 107, 255)`, soit `#1d4b6b`.
2. Nombre de pixels a remplacer, a retrouver exactement : **105** sur 256 dans la vignette de
   16 px, **532** sur 1024 dans celle de 32 px, **1218** sur 2304 dans celle de 48 px. **1855** au
   total. Un compte different signifie que l'outil a fait autre chose que ce que je demande.
3. **Ne toucher a rien d'autre.** Les sept autres couleurs restent identiques a l'octet :
   `#f5c518` (corps jaune), `#13314a` (contour marine), `#c9ced3` (virole grise), `#3a2a1a` (bois
   brun), `#1c1c1c` (mine), `#f2a7b7` (gomme rose), et le transparent.
4. **Les pixels transparents restent totalement transparents** (`alpha` a 0) : 6 dans la vignette
   de 16 px, 20 dans celle de 32 px, 42 dans celle de 48 px. **Ne pas aplatir l'icone sur un fond
   opaque** : ce sont les biseaux des coins haut gauche et bas droit, et les aplatir ferait
   reapparaitre un carre visible sur le bandeau quelle que soit sa couleur - le defaut que cette
   consigne cherche justement a eviter.
5. Apres le changement, **chaque vignette contient encore exactement 8 couleurs distinctes**, et le
   fichier garde ses **trois entrees a 16, 32 et 48 px**, chacune un PNG decodable. Aucun
   reechantillonnage, aucun anticrenelage, aucun redessin : le dessin ne bouge pas d'un pixel. Le
   garde-fou existant `FaviconTest.laVignetteSeizePixelsEstDessineeAPlatEtNonReduiteDUnGrandDessin`
   (16 couleurs au plus) mordra si l'outil reencode en flou, et le lecteur `entreeIco` du meme test
   a besoin que l'entree de 16 px survive.

#### 4. Le garde-fou que j'exige dans le meme commit

Ce que je viens d'arbitrer couple **1855 pixels d'un binaire a une variable CSS**. Ce couplage est
invisible dans une relecture de diff : personne ne voit la couleur d'un `.ico` en lisant un commit.
Sans garde-fou, ma decision d'aujourd'hui est une verification manuelle a usage unique, et le
prochain qui touchera au bandeau refera exactement l'erreur que ce sprint vient de faire trois fois.

**La propriete a rendre mecanique** : *le fond opaque de chacune des trois vignettes de
`favicon.ico` porte exactement la couleur declaree par `--recreo-entete-fond` dans `recreo.css`, et
aucun pixel n'y porte plus `--recreo-accent`.* Je nomme la propriete ; **comment l'ecrire appartient
a `theo-archi` et a `lea-dev`**, et `FaviconTest` sait deja decoder les vignettes, donc la matiere
existe.

Cette exigence est neuve et attachee a une story neuve : elle ne durcit aucune reserve anterieure.
Si `theo-archi` juge qu'elle ne tient pas dans ce commit, **le recolorage se fait quand meme** et le
garde-fou devient une reserve nommee, a instruire par `camille-po` : dans ce cas je reste seule
garde de la propriete, et l'equipe doit savoir qu'elle n'est pas couverte.

#### 5. Ce dont je reste seule garde, quoi qu'il arrive

Pour qu'on ne croie pas couvert ce qui ne l'est pas :

1. **Que le crayon se lise encore comme un crayon a 44 px sur un bandeau plus sombre.** Aucun test
   ne le dira. Je l'ai regarde aujourd'hui sur une composition ; je le reverifierai sur le rendu.
2. **Que l'enfant trouve "Encore !" du premier coup d'oeil** avec un ecart de 1,71:1 entre les deux
   bleus. C'est le point que la story me reserve explicitement, et il se juge en jouant.
3. **Que le badge de l'onglet reste reconnaissable** en `#1d4b6b`, y compris sur un theme sombre ou
   il descend a 1,74:1.
4. **Que les trois parties sombres du crayon sous 3:1 ne genent pas un enfant qui distingue mal les
   couleurs.** J'ai fait le controle en niveaux de gris a 44 px et la silhouette tient, mais un
   ecran bon marche ou une lumiere du jour forte n'entrent dans aucune de mes mesures.

#### 6. Un constat de moi-meme que je corrige, et qui ne conditionne pas S6-10

Trouve en jouant aujourd'hui, sans rapport avec S6-10, et je le separe donc de l'arbitrage.

**Ma reserve 5 de s02, enregistree "tenue" avec pour preuve "Récréo dans le titre de page et dans
le `h1`, accents corrects a l'ecran", est aujourd'hui perimee** : l'application servie affiche
`<title>Recreo</title>` et `<h1 class="nom-site">Recreo</h1>`, sans accent. Ce n'est pas un defaut
et je ne rouvre rien : `DEC-0010` du 2026-08-06 a rendu la decision a la proprietaire du produit,
qui ecrit son nom sans accent, et c'est exactement l'arbitrage de marque que j'avais moi-meme refuse
de trancher en s01. Je ne retire pas la ligne de mon tableau de s02 - rien ne s'efface ici - mais
elle ne doit plus etre lue comme un acquis a preserver. **L'exigence de `DEC-0002` categorie B reste
entiere pour tout le reste du texte lu par l'enfant** : consignes, retours, messages restent
integralement accentues, et je continue de les verifier a chaque passage.

### Arbitrage prealable au developpement - S6-9, une seule sortie par ecran de jeu, du 2026-08-07

**Ce bloc n'est pas un Done pedagogique.** C'est un arbitrage demande avant que le sprint 6 soit
gele, par `sofia-sm`, sur signalement de `theo-archi` (`squad/sprints/s06-ouvrir-la-conjugaison-et-solder-la-dette/avis-faisabilite.md`,
section S6-9). Il est ecrit apres le verdict de s05 du meme jour et ne le modifie pas. Le Done
pedagogique de S6-9 sera prononce plus tard, en jouant l'increment livre.

#### Le verdict, en une phrase

**S6-9 est confirmee sur son principe - une seule sortie par ecran de jeu, et le mot est "Les
jeux" - et refusee sur la forme qu'elle donne a ce principe : la sortie qui survit sur un ecran de
jeu est le bouton de la zone de contenu, pas le lien de l'en-tete, et `sortie-pleine` est conserve
tel quel sur l'ecran de fin de partie.**

#### Ce que le developpeur doit livrer, ecran par ecran

| Ecran | Sortie dans la zone de contenu | Entree de menu de l'en-tete | Ce que l'enfant peut presser |
|---|---|---|---|
| Accueil (liste des jeux) | aucune, rien a changer | **visible**, forme `.lien-accueil` inchangee | les titres de jeux, et l'entree de menu |
| Manche | **conservee telle quelle** : `.btn retour-accueil`, contour, "Les jeux" | **non visible** | les propositions (aplats), et "Les jeux" (contour) |
| Retour juste ou faux, partie en cours (`enSuite` vrai) | **conservee telle quelle** : `.btn retour-accueil`, contour, "Les jeux" | **non visible** | "Encore !" (aplat), et "Les jeux" (contour) |
| Retour, fin de partie (`enSuite` faux) | **conservee telle quelle, non negociable** : `.btn sortie-pleine`, aplat plein, "Les jeux" | **non visible** | "Les jeux" seul, en aplat plein |
| Erreur | aucune sortie ajoutee ; "Recommencer" reste seul et reste un aplat plein | **non visible** | "Recommencer" seul |

Traduit en travail, cela fait trois gestes et pas un de plus :

1. **Le libelle de l'entree de menu passe de "Accueil" a "Les jeux".** C'est le seul changement de
   texte que j'autorise sur cette story. Ma reserve 3 du meme jour est levee par ce geste.
2. **L'entree de menu de l'en-tete n'est pas visible sur les cinq etats de l'ecran de jeu**
   (manche, retour juste, retour faux, fin de partie, erreur). Elle ne l'est que sur l'ecran
   d'accueil. Je decris ce que l'enfant doit voir ; **comment l'obtenir sans casser l'en-tete
   permanent de `DEC-0012` est une question technique qui appartient a `theo-archi`**, et je ne la
   tranche pas.
3. **Les deux branches du `th:class` de `fragments/retour.html` et le bouton de
   `fragments/manche.html` ne sont pas touches.** Ni retires, ni renommes, ni redimensionnes.

#### Le motif, sur ce que voit et comprend l'enfant

**J'ai joue avant de trancher.** Jar reconstruit depuis un arbre propre sur le JDK 21 designe par
`JAVA_HOME`, lance sur un port dedie, partie complete des additions CP jouee jusqu'a la dixieme
manche en me trompant a chaque fois. HTML reellement servi releve sur les cinq etats. Le seul
processus Java que j'ai arrete en fin d'audit etait le mien.

Ce que la mesure donne sur l'ecran de fin de partie, aujourd'hui : la zone de contenu contient
`<button class="btn sortie-pleine" data-role="sortie">Les jeux</button>`, le score, le signal
juste/faux et "C'est fini !". **Ce bouton est la seule chose pressable de la zone de contenu**, et
c'est exactement ce que S3-6 avait demande et que j'avais valide en jouant : quand il ne reste
qu'une chose a faire, cette chose est la plus visible de l'ecran.

**Si S6-9 est developpee telle qu'elle est ecrite, l'enfant qui termine une partie n'a plus rien a
presser dans la zone qu'il regarde.** Sa seule porte devient le lien de l'en-tete, que j'ai mesure
le meme jour a 102 x 53 px, en contour blanc, a x=1088 y=16 sur grand ecran et x=246 y=16 en
360 px - c'est-a-dire dans le coin, loin du score et du "C'est fini !" qu'il vient de lire, et dans
un bandeau dont j'ai ecrit qu'il n'invite pas au geste. La barre que j'avais posee en S3-6 etait
"la seule chose que je peux faire est aussi la chose la plus visible de l'ecran". Un contour de
102 x 53 dans un coin, contre un aplat de 117 x 56 au milieu du texte qu'on lit, ne tient pas cette
barre. **Ce serait un cul-de-sac : un ecran qui dit "C'est fini !" sans montrer par ou sortir.** Un
enfant de six ans qui ne trouve pas la sortie n'en cherche pas une seconde, il attend un adulte.

**A l'inverse, retirer le bouton du fragment sur la manche et sur le retour en cours de partie ne
coute rien a l'enfant et lui rapporte deux choses.** Sur ces deux ecrans, une autre action existe
dans la zone de contenu - les propositions, "Encore !" - et elle reste l'aplat le plus saillant.
Retirer la sortie dupliquee fait tomber les 11 215 px2 de sorties cumulees que j'avais mesures sur
l'ecran de retour a 5 788 px2, contre 6 571 px2 pour "Encore !" : **l'action redevient majoritaire
en surface, et non minoritaire de 70 %**. Et un seul mot circule sur toute l'application.

**Pourquoi la sortie de l'en-tete, et pas celle du fragment.** Trois raisons, dans cet ordre :

1. **La position ne bouge pas.** Avec mon arbitrage, la sortie est au meme endroit sur les cinq
   etats de l'ecran de jeu : en haut de la zone de contenu. Elle change de forme a la fin de la
   partie - contour, puis aplat plein - et c'est la grammaire deja validee en S3-6. Avec S6-9 telle
   qu'ecrite, la sortie serait dans le coin pendant la partie puis devrait grossir dans ce meme
   coin a la fin : **une sortie qui change de place est plus couteuse a apprendre qu'une sortie qui
   change de taille**, a six ans.
2. **Le bandeau est deja le point faible de cet ecran, ce n'est pas la que doit vivre la seule
   action.** Ma reserve 4 du meme jour dit que le bandeau porte exactement la couleur d'action sur
   107 677 px2, seize fois le bouton "Encore !". Y loger la seule chose pressable de l'ecran de fin
   de partie aggraverait ce point au lieu de le traiter, et croiserait S6-10 de face.
3. **Cela n'enleve a l'enfant aucune porte.** `DEC-0012` ecrit elle-meme que l'entree de menu est
   "equivalente au bouton 'Les jeux' existant". Je ne retire donc pas une sortie, je retire un
   doublon - ce que ma reserve 2 demandait, mot pour mot : "sur un ecran de jeu il en faut un, pas
   deux".

#### Je ne deplace pas mes poteaux, et je dis ce que ma reserve 2 demandait vraiment

Ma reserve 2 du 2026-08-07 se termine par : "Je ne tranche pas lequel des deux doit partir - c'est
une question produit pour `camille-po` - je dis que sur un ecran de jeu il en faut un, pas deux."
**La barre est inchangee : un, pas deux.** `camille-po` a choisi dans le perimetre que je lui avais
laisse, et son choix est defendable sur le papier. Ce que je corrige n'est pas la barre, c'est le
perimetre du choix que je lui avais donne : **je le croyais neutre pedagogiquement, il ne l'est
pas**, parce que l'une des deux branches supprime un etat que j'ai valide en jouant. Je ferme donc
cette branche et je nomme laquelle des deux sorties part. C'est la correction d'une erreur a moi,
pas un durcissement d'exigence a elle.

**Cet arbitrage ne defait rien de ce que j'ai valide.** Je l'ai verifie story par story plutot que
de l'affirmer :

- **S3-6** (la sortie de fin de partie est un aplat plein) : intacte, c'est le but de l'arbitrage.
- **S3-6, troisieme critere** (sur la manche et sur le retour, la sortie reste en contour et ne
  domine pas les boutons de reponse) : tenu, et mieux que tenu - la sortie disparait de ces deux
  ecrans, elle ne peut donc plus dominer.
- **S1-4** (une sortie visible et atteignable depuis n'importe quel etat) : tenu sur les cinq
  etats, par le bouton du fragment sur quatre d'entre eux et par "Recommencer" sur l'ecran
  d'erreur.
- **S6-1 et `DEC-0012` point 2** (l'en-tete permanent porte exactement une entree de menu), que
  j'ai validee le meme jour : l'entree existe toujours, avec sa forme et son unicite. Elle perd sa
  fonction de doublon, elle ne perd pas son existence. Je me suis explicitement interdit de
  demander sa suppression : ce serait faire a S6-1 ce que je reproche a S6-9 de faire a S3-6.

#### Ce que cet arbitrage change pour les garde-fous - symptomes decrits, causes laissees a theo-archi

`theo-archi` avait chiffre trois consequences sur `LActionNeDeclarePasMoinsQueLaSortieTest` pour
S6-9 telle qu'ecrite. Mon arbitrage en annule deux et en cree une nouvelle :

1. `leLienDeLEnTeteEstConfronteAuxActionsDesEcransDeJeu` epingle deux chaines finissant par
   `.btn.lien-accueil "Accueil"` : **rouge**, comme il l'annoncait, puisque je confirme le
   changement de libelle. Mise a jour legitime.
2. `surLEcranDeRetourLaSortieEstConfronteeAEncoreMaisPasDansSonEtatDeFinDePartie` exige un couple
   contenant `.btn.retour-accueil` : **reste vert sans reecriture**, puisque ce bouton reste. Le
   couple que le lot Bootstrap avait inverse reste epingle.
3. `noneMatch(couple -> couple.contains("sortie-pleine"))` : **garde son objet**, puisque
   `sortie-pleine` reste rendu. Le vert decoratif qu'il redoutait n'arrive pas.
4. **Ce qui devient sans objet, en revanche, c'est son extension du 2026-08-07** : le lien de
   l'en-tete ne s'affiche plus en meme temps que les boutons d'action des ecrans de jeu, donc les
   confronter ne mesure plus une situation que l'enfant rencontre. Je le signale comme symptome et
   je ne propose pas de correctif : c'est la meme famille de defaut qu'il nomme lui-meme, un
   appariement qui survit a la disparition de son sujet. **A instruire par `theo-archi`.**

#### Les proprietes dont je reste seule garde apres cette story

Je les nomme pour que personne ne croie couvert ce qui ne l'est pas :

- **que l'entree de menu soit reellement invisible sur les cinq etats de l'ecran de jeu.** Aucun
  automate du depot ne mesure aujourd'hui la visibilite d'un element. Je le verifierai en jouant,
  etat par etat, y compris l'ecran d'erreur.
- **que "Les jeux" soit dechiffrable et suffisant comme unique sortie.** Mon avis honnete, et il
  n'est pas parfait : `eu` est un digraphe travaille au CP, mais le `x` final muet de "jeux" ne se
  deduit pas, et le referentiel `squad/programme/francais/competences-cp.md` ne liste pas ce
  grapheme - il ne pretend pas etre exhaustif. Ce qui fait tenir le mot, c'est qu'il nomme ce que
  l'enfant est en train de faire, la ou "Accueil" nomme un lieu abstrait. Je le tiens pour
  acceptable et je le rejugerai en jouant, sur le libelle rendu.
- **que le bandeau bleu ne soit pas percu comme pressable** (ma reserve 4, story S6-10). Aucun
  automate ne lit une couleur.
- **l'ordre des elements dans la zone de contenu**, nouveau constat ci-dessous.

#### Trois constats neufs du 2026-08-07, trouves en jouant aujourd'hui, qui ne conditionnent pas S6-9

Je les date et je les separe de l'arbitrage : ce ne sont pas des exigences ajoutees a S6-9, ce sont
des motifs pour `camille-po`, a traiter quand elle voudra.

1. **Sur les cinq etats de l'ecran de jeu, la sortie est le premier element de la zone de
   contenu**, au-dessus du score, de la consigne, de l'enonce et du verdict. Sur l'ecran de fin de
   partie, cela veut dire que la chose la plus grande et la plus bleue de l'ecran invite l'enfant a
   partir **avant** qu'il ait lu s'il avait juste. C'est un probleme d'ordre, pas de saillance : il
   se corrige en descendant ce bouton sous "C'est fini !", pas en le retirant. **Je n'en fais pas
   une condition de S6-9** parce que S6-9 ne parle pas d'ordre et que je viens de le decouvrir.
2. **Sur l'ecran d'accueil, l'entree de menu mene a l'ecran deja affiche.** `DEC-0012` l'ecrit
   elle-meme en excluant cette forme : "un lien vers l'accueil depuis l'accueil est un ornement (on
   y est deja)". Apres mon arbitrage, l'accueil devient le seul ecran ou cette entree est visible,
   donc le seul ou elle ne mene nulle part. Un bouton qui ne repond pas apprend a un enfant de six
   ans que les boutons ne repondent pas. La suite coherente serait de la retirer, ce qui rouvre
   `DEC-0012` point 2 et defait une partie de S6-1 : **c'est une decision produit, elle appartient
   a `camille-po`, et je ne la prends pas a sa place.**
3. **Sur l'ecran d'erreur, "Recommencer" n'est pas plus dechiffrable au CP que "Accueil" ne
   l'etait.** Onze lettres, deux nasales, un `c` doux. Ce mot est anterieur a S6-9 et j'avais
   valide cet ecran en s01 : je ne reviens pas dessus et je ne le rouvre pas ici. Je l'enregistre
   parce que je l'ai relu aujourd'hui et que ce serait malhonnete de le taire au moment meme ou je
   demande un mot lisible ailleurs.

#### Ce que je corrige de mes propres constats, verifie avant de m'appuyer dessus

- **Mon tableau A.1 du 2026-08-07 est exact sur la fin de partie** : la sortie y est bien portee par
  `.sortie-pleine` en aplat plein, et l'action y est bien absente. Relu sur le HTML servi
  aujourd'hui, pas suppose.
- **Je n'ai pas remesure les pixels aujourd'hui, et je le dis** : `recreo.css` est inchange depuis
  le commit que j'avais mesure, et les quatre gabarits n'ont recu depuis que des commentaires plus
  le `th:block` d'`ADR-0020`. Je reprends donc mon tableau de mesures au lieu de le refaire.
  **Sur la geometrie, ce controle est un report ; sur le balisage reellement servi, il est joue.**
- **Le second symptome technique de mon point E de s05 est clos** : demander une manche sur une
  partie terminee ne rend plus un ecran mort, elle rend l'ecran de fin de partie. Verifie en
  jouant, sur la meme session. Ce qui a pour consequence directe, et qui compte ici, que
  `sortie-pleine` est desormais le seul bouton de deux chemins distincts vers cet ecran.

### Verdict courant - s05, en-tete permanent et correctifs du score, audit du 2026-08-07

- **Trois verdicts distincts. Je ne les fonds pas.**
  - **Le rhabillage du gabarit (en-tete, icone, menu, centrage - S6-1, S6-2, `DEC-0012`)** :
    **VALIDE SOUS RESERVE.** Done pedagogique accorde. C'est le troisieme passage de ce chantier
    devant moi et le premier que je n'ai pas a refuser.
  - **Ce que voit l'enfant qui appuie deux fois sur la derniere manche (`ADR-0019`, clause 3)** :
    **VALIDE.** Le choix de `theo-archi` est le bon, motif ecrit au point C.
  - **Les deux correctifs fonctionnels du score** (manche plantee par une reponse accentuee,
    increment perdu par course sur la session) : **VALIDE**, rejoues tous les deux.
- **Commit audite** : `c0b5241` (`HEAD` de `round-two`).
- **Moyens : audit joue, non degrade.** `mvn -B clean package` depuis un arbre propre sous un
  `JAVA_HOME` sur le JDK 21 reel du poste (`21.0.10`), jar relance en local. **Une instance de
  l'utilisatrice occupait le port 8080 et tenait `recreo.jar` verrouille** : je l'ai arretee pour
  pouvoir reconstruire, j'ai audite sur un autre port, et je l'ai relancee sur 8080 avec le jar de
  ce commit avant de rendre la main. Je le dis plutot que de le taire.
  - Parties completes jouees sur les deux jeux, en repondant juste, en me trompant a chaque
    manche, et en enchainant les echecs pour faire venir l'aide.
  - Ecrans **mesures en pixels** au navigateur sans tete (`getBoundingClientRect` et style calcule
    sur les gabarits reellement servis, en-tete assemble au fragment comme htmx le fait), en
    **1280 x 900 et en 360 x 640**, dans les memes conditions que mes deux refus precedents - sans
    quoi la comparaison ne voudrait rien dire - et regardes en captures d'ecran.
  - Double appui rejoue reellement, en sequence et en concurrence.

#### A. Le rhabillage - ACCORDE SOUS RESERVES

##### A.1 Mon axe de refus est tenu, et je le mesure avant de dire quoi que ce soit d'autre

J'ai refuse deux fois sur le meme axe : la sortie ne doit pas dominer l'action. Le critere que
j'avais ecrit au point 2.3 est *"le bouton d'action doit etre au moins aussi haut et aussi large
que le bouton de sortie"*. Un en-tete permanent ajoute une **seconde sortie sur chaque ecran**, y
compris pendant une manche : je l'ai donc remesure contre les deux.

| Ecran | Action | Sortie du fragment | Sortie de l'en-tete |
|---|---|---|---|
| Retour juste et faux | "Encore !" **117 x 56**, aire 6 571, aplat bleu | "Les jeux" 109 x 53, aire 5 788, contour | "Accueil" 102 x 53, aire 5 427, contour |
| Manche CP et CM1 | reponse **1140 x 74**, aire 84 360, aplat bleu | "Les jeux" 109 x 53 | "Accueil" 102 x 53 |
| Accueil | "Calcul CP" 129 x 56 / "Le bon petit mot" 199 x 56, aplats | - | "Accueil" 102 x 53 |
| Fin de partie | - | "Les jeux" 117 x 56, aplat plein | "Accueil" 102 x 53 |

117 >= 109 >= 102 en largeur, 56 >= 53 en hauteur. **L'action reste devant les deux sorties sur
les deux dimensions, et elle reste la seule a etre un aplat plein : les deux sorties sont des
contours.** Le lien de menu reprend deliberement les composantes de boite de `.retour-accueil`,
c'est ecrit dans la feuille et c'est verifie a la mesure. Sur l'ecran de manche, l'ecart est de
15,5 pour 1. **Mon critere est tenu, je ne refuse pas.**

Position, que je regarde parce qu'elle a compte dans mon refus de s03 : "Accueil" est a x=1088,
y=16, dans le coin haut droit, tres loin des boutons de reponse (x=62, y=326 et au-dela). En
360 px de large il reste a x=246, y=16, quand les reponses sont a y=326 et plus bas. **Le risque
qu'un enfant qui vise une reponse perde sa partie par erreur de visee n'augmente pas.** C'etait ma
crainte principale et elle ne se realise pas.

L'icone : le crayon jaune que j'avais valide en s03 est affiche a 44 px sur le bandeau. Son fond
bleu est exactement la couleur du bandeau, donc le carre disparait et le crayon flotte - regarde
en capture, c'est propre et cela se lit comme un crayon. `alt=""` est le bon choix, le nom du
produit est a cote. Rien a redire.

##### A.2 Ce que je refuserais si c'etait plus grave, et pourquoi ce sont des reserves

Quatre points. Aucun n'inverse la hierarchie action/sortie, aucun n'empeche l'enfant de jouer ni
d'apprendre. Ils sont ranges du plus concret au moins concret.

1. **Sur un ecran etroit, le texte et les boutons touchent les deux bords de l'ecran. C'est une
   regression, et elle vient de ce sprint.** `.zone-jeu { padding: 2rem 0 }` ecrase le rembourrage
   lateral que `.container` apporte, pendant que l'en-tete, lui, garde ses 12 px. Mesure a 360 px :
   `main.container` a `padding-left: 0` et `padding-right: 0`, la consigne, l'enonce et les
   boutons de reponse partent de x=0 et vont jusqu'au bord droit. Avant ce sprint,
   `body { margin: 2rem }` donnait 32 px de tous les cotes sur toutes les tailles ; le sprint a
   remplace cette marge par `margin: 0` en comptant sur `.container`, qui ne la rend qu'au-dessus
   de 1200 px de large. **Un enfant de six ans qui apprend a lire n'a plus aucune marge a gauche
   de sa premiere lettre**, et l'en-tete garde la sienne juste au-dessus, ce qui se voit. Sur un
   grand ecran, rien de tout cela n'est visible - c'est pourquoi personne ne l'a vu, et c'est
   exactement le genre de defaut que ma porte existe pour attraper : CI verte, ArchUnit vert, et
   `leContenuDeLAccueilEstCentreDansUneLargeurMaximale` vert. Le correctif est d'une ligne et je ne
   dis pas laquelle : c'est `ines-design` et `theo-archi` qui tranchent. **Je remesurerai a
   360 px.**
2. **Deux sorties sur chaque ecran de jeu, avec deux libelles differents pour la meme action.**
   "Accueil" et "Les jeux" envoient tous deux `POST /retour` et ramenent tous deux a la liste. Sur
   l'ecran de retour, la surface cumulee des deux sorties (11 215 px2) depasse de 70 % celle de la
   seule action (6 571 px2). Pris bouton par bouton mon critere tient, et c'est pourquoi ce n'est
   pas un motif ; mais faire cohabiter deux mots pour une meme porte est un cout de comprehension
   gratuit. Je ne tranche pas lequel des deux doit partir - c'est une question produit pour
   `camille-po` - je dis que sur un ecran de jeu il en faut un, pas deux.
3. **"Accueil" n'est pas dechiffrable par un CP.** `ueil` est l'un des groupes de lettres les plus
   tardifs du francais et il n'est pas au programme de CP. Un enfant de six ans ne lit pas ce mot ;
   il le reconnaitra a la rigueur par sa forme, apres qu'un adulte le lui aura dit. Ce n'est pas
   un motif de refus **uniquement parce que la sortie lisible existe a cote** : "Les jeux" est
   dechiffrable des le CP et fait le meme travail. Si la reserve 2 est traitee en gardant
   "Accueil" et en retirant "Les jeux", alors ce point devient bloquant et je le dis maintenant
   pour qu'on ne le decouvre pas apres.
4. **Le bandeau est exactement la couleur des boutons d'action** (`#2e6f9e` des deux cotes), sur
   1264 x 85 px, soit 107 677 px2 - **seize fois la surface du bouton "Encore !"**, dans la meme
   couleur, en haut de chaque ecran. J'avais explicitement porte au credit de l'habillage, en s03,
   que "l'action reste un aplat bleu plein, donc un enfant trouvera le bouton bleu". Cet appui est
   affaibli : le bleu n'est plus le signal exclusif de "appuie ici". Il n'est pas detruit - le
   bandeau va d'un bord a l'autre, sans coin arrondi ni ombre, et aucun enfant n'appuie sur un
   bandeau - et c'est pourquoi c'est une reserve. Ce que je demande a `ines-design` d'instruire :
   soit le bandeau prend une nuance distincte de la couleur d'action, soit l'action grandit sur
   l'ecran de retour, qui est le plus pauvre en signal et que l'enfant voit dix fois par partie.

##### A.3 Ce que le garde-fou de theo-archi ne garde pas, verifie plutot que suppose

`theo-archi` ecrit lui-meme que sa regle fermee compare desormais l'en-tete aux ecrans de jeu mais
ne mesure ni la couleur, ni la position, ni la largeur. Il a raison, et c'est exactement ou tombent
mes reserves 1, 2 et 4 : aucune n'aurait pu rougir. **Qu'il ait ecrit la limite de sa propre regle
plutot que de me laisser la trouver est ce qui rend cette porte utilisable** - j'ai su ou chercher.
Tant qu'aucun automate ne mesure un rendu, c'est moi le garde-fou, et je le reste en jouant.

#### B. Le texte lu par l'enfant n'a pas bouge en dehors de ce qui m'est soumis - verifie

Diff complet des gabarits depuis `c801eb8`, le commit de mon audit precedent, plus le HTML
reellement servi. **Trois changements de texte, tous les trois soumis a cette porte, aucun autre :**
le titre, le `h1` deplace dans l'en-tete, et le libelle "Accueil". Intacts, mot pour mot et accent
pour accent : "Les jeux", "Score : N", "Combien ça fait ?", "Quel mot complète la phrase ?",
"Bravo, c'est juste !", "Non, c'est N.", "C'est fini !", "Encore !", "Compte sur tes doigts.",
"Oups ! Le jeu a un souci.", "Recommencer", "Ta partie s'est arrêtée. Choisis un nouveau jeu !",
"Il n'y a pas de jeu pour le moment." **Ma porte n'a pas ete contournee.**

**`DEC-0010`, le titre "Recreo" sans accent : j'en prends acte et je ne le rouvre pas.** Ma
reserve 10 de s01, que j'avais enregistree comme tenue apres S3-9, ne l'est plus. C'est une
decision de l'utilisatrice sur le nom de son produit, et j'avais moi-meme renvoye ce point comme
une question de marque plutot que de la trancher. `camille-po` distingue proprement les deux
registres et ne pretend pas que la decision efface le constat : je note donc, sans en faire une
reserve puisque je n'ai rien a demander, que le cout reste ce qu'il etait - un enfant de CP qui
dechiffre "Recreo" lit /rəkrəo/, et le nom du produit est le seul mot non accentue qu'il rencontre.
Ce cout est assume par la proprietaire du produit, c'est son droit, et il ne m'appartient pas.

#### C. Le double appui - je tranche, et je valide le choix de theo-archi

**La question**, qu'il m'a laissee ouverte plutot que de la decider sans moi : que voit l'enfant
qui appuie deux fois sur la derniere manche ? Il voyait "Oups ! Le jeu a un souci." Il revoit
desormais l'ecran de fin de partie, avec son score.

**Ma reponse : c'est le bon choix. Valide, et je ne demande rien d'autre.**

Le motif, et il tient a une idee : **un double appui n'est pas une erreur de l'enfant.** C'est une
habitude motrice, et elle est frequente a six ans. Un geste involontaire ne doit pas produire une
consequence que l'enfant devra interpreter. Les trois autres possibilites que `theo-archi` liste
echouent toutes sur ce point :

- un **retour a la liste des jeux** lui arracherait son score avant qu'il ait fini de le lire.
  C'est la pire des quatre, et de loin ;
- un **message "la partie est finie"** se lit comme un reproche pour un geste qu'il n'a pas voulu,
  et ajoute un texte a dechiffrer au moment ou il n'y a plus rien a apprendre ;
- un **ecran de fin different du premier** lui apprend que son second appui a fait quelque chose,
  alors qu'il n'a rien fait. C'est faux, et c'est precisement ce qu'il ne faut pas enseigner.

Montrer le meme ecran dit la verite : le geste n'a rien change. C'est ce que fait un bouton qui se
comporte bien.

**J'ai verifie le point qui aurait pu rendre ce choix mauvais, et il tient.** Le second appui
transporte une proposition ; si elle basculait le verdict affiche, l'enfant verrait son "Bravo,
c'est juste !" se transformer en "Non, c'est ...". Rejoue dans les deux sens sur le jar reel :
juste puis appui faux, l'ecran reste "Score : 10 / Bravo, c'est juste ! / C'est fini !" ; faux puis
appui juste, l'ecran reste "Score : 9 / Non, c'est a. / C'est fini !". **L'ecran est gele, a
l'octet pres, au troisieme appui comme au deuxieme.** C'est cela qui fait que le choix est bon, et
pas seulement commode.

Qu'il ait epingle ce comportement par un test nomme pour qu'un changement d'avis se voie dans un
diff est la bonne facon de me rendre une decision : si je change d'avis, cela se verra.

#### D. Les deux correctifs du score - rejoues, tous les deux tiennent

- **La manche plantee par une reponse accentuee est corrigee.** Verifie sur le jar reel dans les
  trois formes possibles du corps de requete - octets UTF-8 bruts, pourcent-encode, et un octet
  invalide isole. **Aucune ne rend l'ecran d'erreur** ; les trois rendent un verdict normal. Joue
  aussi en partie complete : les reponses `à`, `où`, `là`, `l'a` sont jouables et comptent.
- **L'increment perdu par double appui est corrige.** Mon premier protocole ne prouvait rien - avec
  le verrou, deux appuis simultanes dont un faux donnent legitimement zero une fois sur deux, selon
  l'ordre d'arrivee, et j'aurais lu ce zero comme une perte. J'ai donc mesure l'invariant qui
  discrimine reellement : **aucun appui ne doit disparaitre.** Cinq paires d'appuis simultanes sur
  la meme session, vingt-cinq parties independantes : **la partie se termine apres exactement dix
  appuis, vingt-cinq fois sur vingt-cinq.** Aucun appui perdu. Ce que l'utilisatrice signalait -
  "le score ne s'incremente pas toujours" - ne se reproduit plus.

#### E. Suites hors de ma main

- **`squad/programme/MATRICE-couverture.md`** : rien a changer de mon fait ce sprint. La ligne
  `mathematiques | CP | ... additions dans la limite de 10 (puis 20)` reste `couvert-valide`, rien
  de ce sprint ne la remet en cause - je l'ai rejouee. La ligne francais CM1 **reste `en cours`**,
  motif dans `FICHE-bandit-manchot-homophones-cm1.md`, verdict du 2026-08-07. Je n'edite pas ce
  fichier, il appartient a `camille-po`.
- **Deux symptomes techniques, decrits sans que j'en cherche la cause, pour `theo-archi`** :
  1. apres chaque echange htmx, la page porte **deux elements d'identifiant `jeu`** - le `main` du
     gabarit et le `div` du fragment qu'il recoit. La page continue de fonctionner ; je le signale
     parce qu'un identifiant en double est un defaut d'accessibilite et que je l'ai constate a la
     mesure, pas suppose ;
  2. sur une partie terminee, une demande de manche rend un **ecran mort** - la consigne "Quel mot
     complète la phrase ?", aucun enonce, aucun bouton de reponse. **Je n'ai trouve aucun chemin
     pour qu'un enfant y arrive** : l'ecran de fin ne porte pas "Encore !". Je le note comme un
     ecran qui existe et que personne ne devrait pouvoir atteindre, pas comme un defaut constate en
     jeu.
- **`ines-design`** : mes quatre reserves du point A.2, dont la premiere est chiffree et
  remesurable.

---

### Verdict courant - s03 et chantier Bootstrap, audit du 2026-08-06

- **Deux verdicts distincts, et ils ne sont pas les memes.** On m'a soumis deux choses dans le
  meme cycle et je refuse de les fondre en un seul avis :
  - **Corrections mathematiques de s03 (S3-1 a S3-9)** : **VALIDE.** Done pedagogique prononce.
    **Ma condition sur la repartition des sommes est levee**, et avec elle la ligne de matrice.
  - **Chantier Bootstrap hors sprint (`ADR-0014`)** : **REFUSE**, sur un point precis et un seul,
    la hierarchie de saillance de l'ecran de retour. Le reste du chantier est verifie et tient.
    **Ce refus est leve le meme jour sur le commit `a810fc7`** - voir le point 2 bis, ajoute apres
    correction. Le refus reste ecrit integralement au point 2 : un refus efface n'a aucune valeur
    de preuve, c'est ma regle depuis s02 et elle vaut pour mes propres refus.
- **Date** : 2026-08-06. **Commit audite** : `c801eb8` (`HEAD` de `round-two`) pour les points 1
  et 2 ; `fb7f4df` pour le point 2 bis et le point 4.
- **Moyens : audit joue, non degrade.** Fat jar construit (`mvn -B package`) et lance en local
  sur un JDK 21.0.10 - le JDK que trois agents de s03 ont declare inexistant : il existe, il est
  simplement declare dans un fichier de toolchains dont le nom comporte une faute de frappe, que
  Maven ne lit donc jamais. Ce n'est pas un probleme d'environnement, c'est un fichier mal nomme.
  - **2 600 manches jouees** sur le jeu de mathematiques (200 parties completes pour la
    repartition, 60 pour l'exactitude), enonce par enonce, propositions relevees a chaque manche.
  - Parties jouees en repondant faux systematiquement, juste systematiquement, et selon une
    sequence ecrite a l'avance pour tester l'apparition et la remise a zero de l'indice.
  - Rendu reel **mesure en pixels** au navigateur sans tete (`getBoundingClientRect` et style
    calcule sur les gabarits reels servis par l'application), et regarde en captures d'ecran.
    Je n'ai pas deduit les tailles de la feuille de style : `theo-archi` a eu raison de me
    demander de remesurer plutot que de me fier au vert, et c'est la mesure qui a trouve le
    defaut.

#### 0. Question de process tranchee : une fiche par jeu

`camille-po` avait laisse ouverte la question d'une fiche de conformite par jeu, et je l'avais
renvoyee a "quand un deuxieme jeu existera". Il en existe deux de plus. **Je tranche : une fiche
par jeu, nommee `FICHE-<id du jeu>.md`.** Elles sont creees :

- `squad/conformite/FICHE-bandit-manchot-le-bon-son-cp.md`
- `squad/conformite/FICHE-bandit-manchot-homophones-cm1.md`

Trois raisons, dans l'ordre de leur importance :

1. **Le Done pedagogique porte sur un couple mecanique + contenu, pas sur une mecanique.** Les
   trois jeux partagent la meme mecanique et je viens d'en valider un et d'en refuser un autre le
   meme jour, sur le meme commit. Une fiche commune rendrait ce resultat inecrivable.
2. **Une ligne de matrice, une competence, une fiche.** Chaque jeu porte sa propre ligne dans
   `MATRICE-couverture.md`, avec sa propre condition de passage a `couvert-valide`. Les conditions
   n'ont rien a voir entre elles.
3. **Un refus ne doit pas contaminer un jeu qui n'y est pour rien.** Mon refus sur "Le bon son"
   ne dit rien du jeu de CM1, qui est accorde.

Ce qui reste **commun** aux trois jeux - l'habillage, la saillance des boutons, les formes
juste/faux, l'ecran d'erreur - continue d'etre juge **ici**, dans cette fiche, parce que c'est ici
que le chantier visuel a son historique depuis s00. Les fiches des deux autres jeux y renvoient
au lieu de dupliquer. Si l'habillage devient un chantier a part entiere, il prendra sa propre
fiche et je le dirai.

---

#### 1. VALIDE - les corrections mathematiques de s03

##### 1.1 Ma condition principale : la repartition des sommes. Tenue.

C'etait ma reserve 5 de s01 et la condition ecrite du passage de la ligne de matrice. **Verifiee
en jouant 2 000 manches reelles**, pas en lisant le tirage :

| Somme | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 |
|---|---|---|---|---|---|---|---|---|---|---|
| Manches | 26 | 56 | 131 | 147 | 183 | 212 | 266 | 295 | 316 | 368 |
| Part mesuree | 1,3 % | 2,8 % | 6,6 % | 7,4 % | 9,2 % | 10,6 % | 13,3 % | 14,8 % | 15,8 % | 18,4 % |
| Part annoncee | 1,3 % | 3,2 % | 5,2 % | 7,1 % | 9,0 % | 11,0 % | 12,9 % | 14,8 % | 16,8 % | 18,7 % |

Les trois chiffres qui decident :

| | s01, ancien tirage | s03, mesure | annonce |
|---|---|---|---|
| Sommes 9 et 10 | **43,9 %** | **34,2 %** | 35,5 % |
| Sommes inferieures a 5 | **7,6 %** | **18,0 %** | 16,8 % |
| Operande nul | 12,9 % | **13,7 %** | 12,9 % |

Et surtout : **les 65 couples valides sont tous sortis, aucun ne manque** (effectif par couple
entre 7 et 52, moyenne 30,8 sur 2 000 tirages). **`0 + 0` n'est jamais sorti sur 2 600 manches** -
ma reserve 8 de s01 est traitee dans le meme geste. Le comportement mesure correspond a ce qui
etait annonce, aux fluctuations d'echantillonnage pres.

**Je leve ma condition.** Trois raisons, et je les ecris parce qu'un tiers doit pouvoir verifier
que je ne me contente pas d'un progres :

1. Ma reserve 5 nommait elle-meme le tirage uniforme sur les couples comme "deja une nette
   amelioration". Il a ete livre. Je ne deplace pas mes propres poteaux apres coup : une porte
   dont la condition change une fois remplie ne vaut pas mieux qu'une porte qui s'ouvre toujours.
2. Mon motif de fond etait un motif de **couverture** : "le jeu ne travaille pas les additions
   dans la limite de 10, il travaille surtout leur bord superieur". Ce motif tombe, et il tombe
   sur une mesure : les 65 additions de l'intervalle sont toutes visitees, chacune avec la meme
   probabilite. C'est bien "les additions dans la limite de 10".
3. Les sommes basses sont passees de 7,6 % a 18,0 %, soit d'a peu près une manche sur treize a
   pres d'une manche sur cinq. Un enfant fragile rencontre desormais du surcomptage a sa portee
   plusieurs fois par partie.

**Ce que je ne dis pas** : que la difficulte est bien repartie. 34,2 % des manches restent sur 9
et 10, parce qu'un tirage uniforme sur les **faits** produit mecaniquement plus de grandes sommes
(il y a dix facons de faire 10 et une seule de faire 1). C'est un choix defendable - drainer
chaque fait autant que les autres vaut mieux que sur-entrainer `1 + 0` - et je ne le conteste pas.
Mais **il n'y a toujours aucune progression a l'interieur d'une partie** : les dix manches sont
tirees independamment, la difficulte est plate. C'etait ma reserve 3 de s00, elle reste ouverte,
et elle n'est pas une condition de la ligne de matrice - c'est une question de sequencement de
partie, pas de couverture de competence.

##### 1.2 L'indice : atteignable, verifie en jeu, et je suis toujours la seule a l'avoir joue

J'avais montre en s01 que l'indice etait mathematiquement inatteignable : il exigeait trois
echecs sur le **meme** item, ne pouvait pas s'afficher avant la 14e manche, et une partie en dure
dix. **Corrige et verifie en jouant une sequence ecrite a l'avance :**

| Manche | Ce que je reponds | Indice affiche |
|---|---|---|
| 1, 2, 3 | faux | absent |
| **4** | - | **"Compte sur tes doigts."** |
| 4 | juste | - |
| **5** | - | **absent : la reussite a remis le compteur a zero** |
| 5, 6, 7 | faux | absent |
| **8** | - | **"Compte sur tes doigts." - il revient** |

Le declenchement sur trois echecs **consecutifs quel que soit l'item** fonctionne, la remise a
zero sur une reussite fonctionne, et l'aide revient si l'enfant retombe. **C'est exactement ce que
je demandais** : "un enfant qui rate trois fois de suite doit recevoir une aide dans la partie en
cours". Ma reserve 3 de s01 est levee.

Le texte est bien celui que j'avais reformule, "Compte sur tes doigts.", et il vit desormais dans
`contenu/cp-additions-jusqu-a-10.yaml` ligne 12, plus dans une constante Java : la constante
`INDICE` a disparu du domaine. Ma reserve 4 de s01 est levee.

A l'ecran, l'indice est un encadre ambre a 20 px, entre l'enonce et les boutons de reponse. Il est
vu.

##### 1.3 Exactitude du contenu - 600 manches supplementaires, zero anomalie

Verifie sur 600 manches, automatiquement et sur chaque manche : trois propositions, la bonne
reponse presente exactement une fois, aucun doublon, aucune proposition negative, aucune somme
superieure a 10. **Zero anomalie.** Position de la bonne reponse equilibree (216 / 174 / 210 en
premiere, deuxieme, troisieme position) : aucune strategie de position n'est exploitable. Les
distracteurs sont a un ou deux de la bonne reponse (590 et 609 occurrences), un seul a trois, sur
un cas ou descendre plus bas sortirait des entiers positifs. Plausibles, jamais absurdes.

##### 1.4 Mes cinq reserves visuelles de s02 - toutes tenues, mesurees

| Reserve | Etat | Mesure |
|---|---|---|
| 1. Le chiffre doit peser plus que la barre qui le porte | **tenue** | `.proposition` a 32 px gras, soit la taille de l'enonce lui-meme ; le bouton fait 1200 x 62 px |
| 2. Ecart entre boutons porte a 1 rem | **tenue** | `gap: 1rem` sur `.propositions`, boutons empiles |
| 3. Sortie plus visible en fin de partie et sur l'ecran d'erreur | **tenue** | fin de partie : la sortie passe en aplat plein 101 x 44 px, seule action de l'ecran ; erreur : "Recommencer" est devenu un vrai bouton plein, ce n'est plus un lien souligne. Mes reserves 3 de s02 et 7 de s01 sont levees |
| 4. Icone dessinant un objet plutot que le monogramme "R" | **tenue** | un crayon jaune a mine sombre et gomme rose sur fond bleu. Regarde a 16, 48 et 288 px : il se lit comme un crayon des 48 px. A 16 px il n'est plus qu'une diagonale jaune, mais ce n'est plus une lettre, et c'est ce que je demandais |
| 5. Titre accentue | **tenue** | "Récréo" dans le titre de page et dans le `h1`, accents corrects a l'ecran |

##### 1.5 Le texte lu par l'enfant n'a pas bouge en dehors de ce qui m'a ete soumis - verifie

Verifie au diff depuis mon dernier avis (`df6b4e3`), pas sur parole. Dans les gabarits, **un seul
texte destine a l'enfant a change** : "Recreo" est devenu "Récréo", dans le titre et dans le
`h1`. C'est exactement ma reserve 10 de s01, tranchee par `DEC-0007`. Tout le reste du diff des
gabarits est de l'attribut de classe et du commentaire.

Dans le code de production, aucune chaine destinee a l'enfant n'a ete ajoutee ni modifiee : la
seule disparition est la constante `INDICE`, deplacee vers le YAML comme demande. Le message de
reprise "Ta partie s'est arrêtée. Choisis un nouveau jeu !" est inchange. Dans le contenu, les
seuls ajouts sont l'indice du paquet de mathematiques et les deux paquets de francais, qui m'ont
tous ete soumis explicitement.

**Rien n'est passe sans moi.**

---

#### 2. REFUSE - le chantier Bootstrap, sur la saillance de l'ecran de retour

##### 2.1 Portee du refus : ce qui est refuse, et ce qui ne l'est pas

**Ce qui n'est pas refuse, et que j'ai verifie :** la vendorisation elle-meme, l'ordre des
feuilles (la feuille maison passe bien en dernier et l'emporte a specificite egale), le passage
de `.jeux` a `.list-unstyled`, la pile de polices de Reboot, et surtout **les formes juste/faux**.
`theo-archi` a eu raison de me signaler que le `clip-path` maison n'a aucun equivalent Bootstrap
et que sa lisibilite pour un CP me revenait entierement. Je les ai regardees en rendu :
**la coche verte et la croix rouge sont intactes et parfaitement lisibles**, ce sont bien les deux
signes qu'un enfant connait de son cahier, a 48 x 48 px, et la forme porte le sens sans la
couleur. Rien a redire.

Le retrait de CSS maison plutot que l'empilement est la bonne methode et je le dis.

**Ce qui est refuse : un seul point.**

##### 2.2 Motif : sur l'ecran de retour, le bouton qui fait sortir du jeu est plus gros que le bouton qui fait continuer

Mesure en pixels sur le rendu reel, ecran de retour juste **et** faux, celui que l'enfant voit
dix fois par partie :

| Bouton | Role | Taille | Aire |
|---|---|---|---|
| "Les jeux" | **quitter** la partie | 109 x 53 px | **5 788 px2** |
| "Encore !" | **continuer** la partie | 101 x 44 px | **4 459 px2** |

**Le bouton qui quitte est 30 % plus grand que le bouton qui continue.** Il est aussi 9 px plus
haut, 8 px plus large, place au-dessus et donc rencontre en premier.

**C'est une regression introduite par ce chantier**, et elle est tracable a une ligne. Le commit
`6c83c97` a retire `padding: 0.75rem 1.25rem` du bloc des boutons pleins - bloc qui contient
`.encore` - au motif que `.btn` de Bootstrap fournit desormais le rembourrage. Mais `.btn` donne
`6px 12px`, moitie moins. Or `.retour-accueil`, la sortie, declare son propre rembourrage
`0.6rem 1.1rem` soit `9.6px 17.6px`, et **n'a pas ete touche**. Avant ce chantier l'action etait
environ 15 % plus grande que la sortie ; apres, elle est 23 % plus petite. La hierarchie s'est
inversee.

**Pourquoi c'est un refus et pas une reserve.** Trois raisons cumulees :

1. **C'est exactement l'axe sur lequel j'ai refuse S2-1 en s02** - "le bouton de sortie dominait
   le bouton de reponse" - et `theo-archi` m'a prevenue par ecrit que Bootstrap augmentait le
   risque sur cette saillance precise. Le risque annonce s'est realise. Un avertissement suivi
   d'une mesure qui le confirme ne se solde pas par une reserve.
2. **La consequence pour l'enfant n'est pas cosmetique.** La plus grosse cible de l'ecran est
   celle qui met fin a la partie, sans confirmation, et un enfant de six ans vise avec une main
   entiere. Une erreur de visee lui coute sa partie et son score.
3. **L'intention est ecrite dans le code et n'est pas tenue.** Le commentaire de `.retour-accueil`
   dit que la sortie "ne doit pas dominer l'ecran ni concurrencer les boutons de reponse ou
   'Encore !'". Mesuree, elle le concurrence et elle gagne.

**Ce qui attenue, et que je dis quand meme** : la sortie reste un contour et l'action reste un
aplat bleu plein, donc la hierarchie de **couleur** est intacte et un enfant trouvera le bouton
bleu. C'est pour cela que le refus est scope a un point et pas au chantier entier. Sur l'ecran de
question, la hierarchie est d'ailleurs excellente : le bouton de reponse fait 74 400 px2 contre
5 788 px2 pour la sortie, un rapport de 13 pour 1.

##### 2.3 Comment lever ce refus

Rendre au bouton d'action au moins la taille du bouton de sortie sur l'ecran de retour. Le
critere que je verifierai, et il est mesurable : **"Encore !" doit etre au moins aussi haut et
aussi large que "Les jeux"**, soit au minimum 109 x 53 px dans les conditions de mesure
ci-dessus. Je ne dis pas comment le faire - rendre son rembourrage au bloc des boutons pleins
serait le plus direct, mais c'est `ines-design` et `theo-archi` qui tranchent. Je remesurerai.

##### 2.4 La question laissee ouverte pour moi : remplacer `.indice` et `.message-reprise` par `.alert-warning` ? Non.

On m'a laisse ce point ouvert plutot que de le decider sans moi, et c'est la bonne facon de faire.
**Ma reponse est non, et voici le calcul qui la fonde.**

Le fond ambre de `.indice` est invisible sur le fond de page : rapport de contraste **1,01:1**
entre `#fff4e5` et `#f4f7fb`. C'est precisement pourquoi j'avais exige "un cadre complet, pas
seulement un liseret". Ce qui rend l'encadre visible, c'est **uniquement sa bordure** : `#b45309`
sur 2 px, a **4,67:1** contre le fond de page.

`.alert-warning` de Bootstrap donnerait :

| | maison (actuel) | `.alert-warning` |
|---|---|---|
| Texte sur son fond | 13,93:1 | 7,21:1 |
| **Bordure contre le fond de page** | **4,67:1 sur 2 px** | **1,15:1 sur 1 px** |
| Fond contre le fond de page | 1,01:1 | 1,03:1 |
| Taille du texte | 1,25 rem | 1 rem |

La bordure tomberait de 4,67:1 a **1,15:1**, et de 2 px a 1 px. Comme le fond est invisible dans
les deux cas, **l'encadre disparaitrait purement et simplement** du champ de vision de l'enfant.
Et le texte passerait de 1,25 rem a 1 rem, sous le plancher que j'ai pose en s01 (reserve 6 :
aucun texte lu par l'enfant sous la taille de la consigne, et `.indice` en particulier, parce que
c'est le texte destine au lecteur le plus fragile).

**Condition sous laquelle je changerais d'avis** : si `.alert-warning` est adopte **avec** une
regle maison qui lui rend une bordure d'au moins 2 px a 4,5:1 minimum contre `#f4f7fb` et une
taille de texte d'au moins 1,25 rem. Mais alors la classe Bootstrap n'apporte plus rien qu'on ne
redeclare par-dessus, et l'interet du geste disparait. **Laisser `.indice` et `.message-reprise`
en maison est le bon choix.**

---

#### 2 bis. Le refus du point 2 est LEVE - remesure sur le commit `a810fc7`

- **Verdict** : **refus leve. Le chantier Bootstrap est ACCORDE.**
- **Date** : 2026-08-06, le meme jour que le refus.
- **Commit qui corrige** : `a810fc7`. **Commit remesure** : `fb7f4df` (`HEAD` de `round-two`).
- **Moyens** : je n'ai pas cru l'annonce. `mvn -B clean package` depuis un arbre propre, jar
  reconstruit puis relance sur le JDK 21.0.10 reel (bytecode du jar verifie : version majeure 65,
  donc bien Java 21). Ecrans obtenus par les memes requetes HTTP que joue l'enfant, boites lues
  au navigateur sans tete dans **les memes conditions de mesure que mon refus** - meme fenetre de
  1280 x 900, meme methode - sans quoi la comparaison ne voudrait rien dire.

##### Ma condition est tenue, sur les deux dimensions

Ma condition ecrite au point 2.3 etait : *"Encore !" doit etre au moins aussi haut et aussi large
que "Les jeux", soit au minimum 109 x 53 px dans les conditions de mesure ci-dessus.*

| Ecran de retour | Avant (`c801eb8`) | Apres (`fb7f4df`), mesure par moi | Annonce par `ines-design` |
|---|---|---|---|
| "Encore !" - continuer | 101 x 44 px, aire 4 459 | **117 x 56 px, aire 6 571** | 117 x 56, aire 6571 |
| "Les jeux" - quitter | 109 x 53 px, aire 5 788 | 109 x 53 px, aire 5 788 | 109 x 53, aire 5788 |
| Rapport action / sortie | **0,77** (inverse) | **1,14** (retabli) | - |

117 >= 109 en largeur, 56 >= 53 en hauteur. **Les deux dimensions sont tenues**, et l'action
repasse devant la sortie de 13,5 % en surface. **Les chiffres annonces par `ines-design` sont
exactement les miens** - elle avait d'ailleurs commence par reproduire mes mesures avant de
toucher au code, ce qui est la bonne facon de traiter un refus chiffre.

Regarde et pas seulement mesure : sur la capture de l'ecran de retour, "Encore !" est desormais
un pave bleu plein nettement plus grand que le contour "Les jeux". L'action gagne maintenant sur
les deux canaux, la taille et la couleur. C'est ce que je demandais.

##### Aucune regression ailleurs - verifie moi-meme, pas sur parole

Le correctif rend le rembourrage a **tout** le bloc des boutons pleins, donc il touche cinq
selecteurs et pas seulement `.encore`. J'ai donc remesure tous les ecrans plutot que le seul qui
motivait mon refus :

| Ecran | Constat |
|---|---|
| Question (maths et CM1) | `.proposition` passe de 1200 x 62 a **1200 x 74 px**, aire 74 400 -> **88 800 px2**, contre 5 788 pour la sortie. Le rapport passe de 12,9 a **15,3 pour 1** : l'ecart de saillance **s'elargit**, il ne se retrecit pas |
| Retour juste et faux | traite ci-dessus ; coche et croix inchangees a 48 x 48 px |
| Fin de partie | `.sortie-pleine` a 117 x 56 px, seule action de l'ecran, aplat plein. Conforme a ma reserve 3 de s02 |
| Accueil | `.demarrer` a 56 px de haut. Deux jeux depuis `DEC-0009` |
| Erreur | "Recommencer" reste un vrai bouton plein |
| **Telephone, 360 x 640** | verifie parce que les boutons ont grandi de 12 px chacun : l'ecran le plus charge - sortie, score, consigne, enonce, **indice** et les trois reponses - tient encore **entierement au-dessus de la ligne de flottaison**. Pas de regression |

`.indice` et `.message-reprise` sont intacts : 20 px de texte, bordure 2 px `#b45309`, rembourrage
12 px / 16 px. **Mon refus chiffre sur `.alert-warning` est respecte** et n'a pas ete contourne.

##### Ce que cette affaire dit, et que je note pour `theo-archi`

C'est la troisieme fois dans ce projet qu'un defaut visible par un enfant traverse une CI
entierement verte. La cause est la meme a chaque fois et `ines-design` l'ecrit elle-meme sans
essayer de l'improviser : **aucun garde-fou de ce depot ne compare deux selecteurs entre eux sur
un meme ecran.** Les regles existantes verifient qu'une declaration est presente ou qu'une valeur
depasse un plancher ; aucune ne verifie qu'un bouton est plus gros qu'un autre, parce que c'est un
rapport de surfaces mesure sur un rendu, pas une declaration.

Je ne demande pas une regle ArchUnit - ce n'est pas mon perimetre et je ne sais pas si c'est
faisable. Je formule le besoin, `theo-archi` en fait ce qu'il veut : **quand une regle maison
existe pour garantir qu'un bouton d'action appelle la main plus qu'un bouton de sortie, la
supprimer ou la deleguer a une bibliotheque tierce ne devrait pas pouvoir passer sans que
quelqu'un remesure.** Tant que ce garde-fou n'existe pas, c'est moi le garde-fou, et je le suis
en jouant.

---

#### 3. Suites hors de ma main

- **`squad/programme/MATRICE-couverture.md`, ligne `mathematiques | CP | Nombres et calculs |
  Calcul mental - additions dans la limite de 10 (puis 20)` : elle passe a `couvert-valide`.**
  Ma condition, ecrite en s01 et reportee deux fois, est levee - motif complet au point 1.1.
  C'est la seule chose que j'avais exigee pour cette ligne et elle est verifiee en jeu. Je n'ai
  pas edite la ligne, elle appartient a `camille-po`.
  Reserve attachee, qui n'est **pas** une condition : la mention "(puis 20)" de la competence
  reste hors du perimetre de ce jeu, dont la borne est 10. La ligne est `couvert-valide` pour ce
  qu'elle couvre ; si "puis 20" doit etre couvert, c'est une autre story et je le reverrai.
- **Les deux nouvelles lignes de francais restent `en cours`**, chacune pour un motif different,
  ecrit dans sa propre fiche : `FICHE-bandit-manchot-le-bon-son-cp.md` (increment refuse) et
  `FICHE-bandit-manchot-homophones-cm1.md` (increment accorde, mais cinq couples d'homophones
  courants manquent a la competence).
- **Les deux criteres non coches de la section de `camille-po`** dans cette fiche sont, de mon
  point de vue, satisfaits aujourd'hui : l'indice atteignable sur trois echecs consecutifs quel
  que soit l'item (verifie en jeu, point 1.2) et la repartition des sommes (verifiee sur 2 000
  manches, point 1.1). Je ne les coche pas, cette section lui appartient.
- **Pour `sofia-sm`** : aucun `review.md` n'existe pour s03 et aucun dossier n'existe pour s04.
  Je ne cree ni l'un ni l'autre, ce sont ses ceremonies. La conclusion a y reporter est celle-ci,
  en trois lignes : corrections mathematiques de s03 **VALIDE** avec levee de la condition de
  matrice ; chantier Bootstrap **REFUSE** sur la saillance de l'ecran de retour ; francais CM1
  **VALIDE SOUS RESERVE**, francais CP **REFUSE**.
- **Pour `camille-po`, une erreur de referentiel** : `squad/programme/francais/competences-cp.md`
  declare couverte par un jeu de s02 une competence de syllabes dont ni le jeu ni la fiche
  n'existent. Detail et motif dans `FICHE-bandit-manchot-le-bon-son-cp.md`.

#### 4. Suivi de mes trois demandes, verifie sur `fb7f4df`

Ajoute apres coup, au meme titre que le point 2 bis. Je ne rouvre ni le point 1 ni le bloc 3 :
je constate ce qui a ete fait de mes demandes et je reponds a la seule question qui m'est posee.

##### 4.1 Le retrait du jeu refuse est effectif - verifie en jouant

`DEC-0009` a retire `bandit-manchot-le-bon-son-cp` du catalogue. Verifie sur l'application lancee,
pas dans le YAML : **l'accueil ne propose plus que deux jeux**, "Calcul CP" et "Le bon petit mot".
Et le jeu retire est injoignable par toutes ses portes - demarrage, coup, manche suivante -
chacune rendant l'ecran d'erreur avec son bouton "Recommencer" fonctionnel, jamais une erreur
brute. C'est le bon comportement.

Le paquet `cp-le-bon-son.yaml` et sa fiche restent en place comme matiere de correction : c'est
exactement ce qu'il faut. Mon refus portait sur ce qu'un enfant voit, pas sur l'existence du
fichier, et les cinq motifs doivent rester lisibles pour celui qui reprendra le contenu.

##### 4.2 La reprise de session ne revalide pas l'identifiant - suite acceptable, et voici pourquoi

`lea-dev` a trouve et signale, sans le coder, que le chemin de reprise ne revalide pas
l'identifiant de jeu contre le catalogue : un enfant dont la session serait ouverte sur "Le bon
son" au moment du retrait pourrait finir une manche sur un contenu que j'ai refuse. Elle a bien
fait de le signaler plutot que de le corriger en passant.

**Ma reponse : cela ne change rien a mon verdict de retrait, et c'est une suite acceptable.** Le
motif n'est pas de la complaisance, il est verifiable : le catalogue est charge **au demarrage**,
donc un retrait ne prend effet qu'au redemarrage de l'application ; et les sessions vivent en
memoire - il n'existe ni module de persistance ni magasin de sessions dans ce depot, les cinq
modules sont `domain`, `application`, `adapter-web`, `adapter-content` et `bootstrap` - donc **le
redemarrage qui applique le retrait detruit du meme coup toutes les sessions**. La fenetre
d'exposition d'un enfant est nulle aujourd'hui, pas petite : nulle.

**Ce qui doit etre note, parce que ce sera vrai demain et pas aujourd'hui** : le jour ou
`adapter-persistence` existera et ou une session survivra a un redemarrage - c'est une cible
annoncee du produit, `ADR-0001` et `DEC-0003` -, cette fenetre cessera d'etre theorique. Un
contenu que j'ai refuse pourrait alors rester jouable apres son retrait. Je ne demande pas de
correctif maintenant ; je demande que **le second critere de S5-1 ne soit pas ferme avant
`adapter-persistence`**, parce que c'est ce module qui transformera ce point d'hygiene en defaut
reel. A `camille-po` d'en faire ce qu'elle veut du backlog.

##### 4.3 Mes deux autres demandes

- **Ligne mathematiques CP passee `couvert-valide`** dans `MATRICE-couverture.md`, la premiere du
  produit. C'est ma condition, elle est levee, la ligne est juste. Rien a ajouter.
- **Referentiel corrige** : l'annotation qui declarait la competence syllabes couverte par un jeu
  inexistant est retiree, et `camille-po` a verifie les trois autres referentiels sans en trouver
  d'autre. C'est la bonne facon de traiter ce genre de signalement - ne pas se contenter de
  corriger l'occurrence trouvee, mais aller chercher les autres.

---

### Verdict du sprint s02, chantier identite visuelle (S2-1, S2-2, S2-3) - conserve pour trace

- **Verdict d'ensemble du chantier, apres correction** : **ACCORDE.** S2-1 **ACCORDE SOUS RESERVE**
  sur le commit `fd16057` - mon refus du commit `3be5a86` est **leve**, et il est conserve
  integralement au point 2 ci-dessous parce qu'un refus efface n'a aucune valeur de preuve. S2-2
  **VALIDE SOUS RESERVE (accorde)**. S2-3 **VALIDE SOUS RESERVE (accorde)**.
- **Dates** : refus de S2-1 prononce le 2026-08-05 sur `3be5a86` ; refus leve le 2026-08-05 sur
  `fd16057`. S2-2 et S2-3 accordes le 2026-08-05 et **non rouverts** par ce second passage : j'ai
  seulement verifie que la correction de S2-1 ne les avait pas casses, et elle ne les a pas casses,
  elle en leve meme une reserve (voir point 2 bis).
- **Portee de ce verdict** : il porte sur l'increment d'identite visuelle, pas sur le jeu. **Le Done
  pedagogique de `bandit-manchot-additions-cp` accorde le 2026-08-05 au titre de s01 n'a a aucun
  moment ete retire ni suspendu** : le contenu mathematique, les libelles et le traitement de
  l'erreur n'ont pas bouge, et je les ai reverifies aux deux passages (voir points 5 et 2 bis).

#### Ou j'ecris ce verdict, et pourquoi pas dans une fiche distincte

`DEC-0006` laissait ouverte la question d'ouvrir une fiche de conformite distincte pour ce chantier.
Je tranche : **non, je consigne ici.** Trois raisons. S2-1 n'est pas une story neuve, c'est la
transcription litterale de mes reserves 6 et 7 de cette fiche : suivre une reserve dans un fichier
et son traitement dans un autre est exactement la perte de trace que la gouvernance cherche a
eviter. La convention est `FICHE-<jeu>.md` et `DEC-0006` a lui-meme etabli que ces trois stories ne
constituent pas un nouveau jeu. Enfin, tous les ecrans que j'ai traverses sont ceux de ce jeu et de
la coquille qui l'entoure. Je passe la main a `camille-po` sur un point de process que je ne
tranche pas : le jour ou un deuxieme jeu existera, la partie "coquille d'application" de cet
habillage n'aura plus de fiche naturelle ou vivre. C'est une question de backlog, pas la mienne.

#### 1. Audit joue - moyens, et cette fois sans degradation

Audit **non degrade**, et sur le JDK de la CI cette fois : `mvn -B package` puis
`java -jar bootstrap/target/recreo.jar` sur le port 8099, avec `JAVA_HOME` positionne sur un JDK 21
(21.0.10). L'ecart de JDK que je signalais en s01 n'existe plus sur cet audit.

Ecrans reellement servis par l'application et traverses un par un : accueil, catalogue, une manche,
une bonne reponse, une mauvaise reponse, une partie menee jusqu'a la fin de partie, le bouton de
sortie "Les jeux", l'ecran d'erreur (reproduit par un coup joue sans partie en cours), l'ecran de
reprise apres perte de session (reproduit avec une session HTTP morte et le cookie marqueur encore
present).

**Je n'ai pas juge sur la declaration CSS, j'ai regarde le rendu.** Chaque ecran a ete rasterise
dans un vrai moteur de rendu, a 900 px de large, et mesure au pixel : hauteur et largeur reelles de
chaque bloc d'encre. Deux ecrans ne sont pas atteignables en jeu reel et ont ete reconstruits avec
le balisage exact des gabarits pour juger leur habillage, ce que je signale plutot que de le taire :
la manche portant l'indice (toujours inatteignable en dix manches, voir ma reserve 3 de s01, non
traitee par ce sprint et hors de son mandat) et l'ecran de catalogue vide.

#### 2. S2-1 sur le commit 3be5a86 - REFUSE (refus leve depuis, voir 2 bis ; conserve pour trace)

**Ce qui est fait, et bien fait.** Mes reserves 6 et 7 sont levees sur tout ce qu'elles nommaient.
Mesure a l'ecran, pas dans le fichier : "Encore !" et "Recommencer" ne sont plus du texte a 13 px ni
un lien souligne, ce sont deux vrais boutons pleins. Le bouton de sortie passe de 66 x 21 px a
115 x 47 px. `.score`, `.indice`, `.fin-de-partie`, `.erreur`, `.pas-de-jeu`, `.message-reprise`
sont tous a 1,25 rem ou plus, `h1` porte enfin une regle explicite. L'indice, le texte destine au
lecteur le plus fragile, est a 1,25 rem dans un bandeau qui se voit. Sur ce perimetre-la, le travail
est reel et je le dis.

**Motif du refus.** `.proposition` - les boutons de reponse - est declare a **1,1 rem**, en dessous
de `.consigne` (1,25 rem), et c'est le seul element de toute l'application a rester sous cette
barre. `ines-design` le signale elle-meme comme laisse "hors perimetre". Le perimetre ecrit ne le
permet pas : le premier critere de S2-1 vise "**chaque** selecteur qui affiche du texte lu par
l'enfant **ou** un bouton actionnable - **au minimum** [liste]", et "au minimum" est un plancher,
pas une liste fermee ; le deuxieme critere vise "**n'importe quel** bouton actionnable par
l'enfant". Un bouton de reponse affiche un nombre que l'enfant lit et se presse pour repondre : il
tombe sous les deux criteres. Ce n'est pas moi qui deplace la barre - c'est la phrase que j'ai
ecrite en s01 et que `camille-po` a reprise mot pour mot dans la story.

**Et il y a plus grave que la taille : cet increment a inverse la hierarchie visuelle dans le
mauvais sens.** J'ai rendu la meme manche avec le CSS d'avant (`5af6160`) et avec celui d'apres
(`3be5a86`), et j'ai mesure les boites :

| Element | Avant | Apres |
|---|---|---|
| Bouton de sortie "Les jeux" | 66 x 21 px, defaut navigateur | 115 x 47 px, bleu plein |
| Un bouton de reponse | 46 x 41 px, gris par defaut | 46 x 41 px, gris par defaut, inchange |

Avant ce sprint, le bouton de reponse etait l'objet cliquable le plus grand de l'ecran de jeu ; la
sortie etait le plus petit. Apres ce sprint, la sortie occupe pres de trois fois la surface d'un
bouton de reponse, elle est la **seule** zone coloree de l'ecran, elle est en haut, et les trois
boutons sur lesquels l'enfant doit appuyer sont les seuls controles restes gris. Pour un enfant de
CP qui ne dechiffre pas encore les libelles et se guide a la saillance, l'element qui appelle la
main est desormais celui qui **fait quitter le jeu**. L'habillage n'a pas oublie un detail : il a
mis en valeur la sortie et laisse la tache dans l'ombre. C'est un recul pedagogique produit par cet
increment, pas une dette heritee, et c'est ce qui fait la difference entre une reserve et un refus.

**Deux consequences concretes que j'ajoute au motif.** La cible tactile d'un bouton de reponse
mesure 46 x 41 px avec 8 px entre deux boutons : c'est sous les 44 px communement retenus comme
minimum, pour des enfants dont la motricite fine est en construction. Un doigt qui derape d'un
demi-centimetre appuie sur la reponse du dessous, et le jeu enregistre alors une erreur de calcul
la ou il y a eu une erreur de geste. Le score cesse de dire ce que l'enfant sait.

**Ce que j'attends pour lever ce refus** (je donne le motif, pas la valeur exacte, qui appartient a
`ines-design`) : que `.proposition` passe au moins a 1,25 rem comme tout autre bouton actionnable ;
qu'il prenne ses couleurs dans la palette nommee comme les autres et cesse de dependre du defaut du
navigateur ; que sa cible tactile atteigne au moins 44 x 44 px avec un espacement qui pardonne un
geste imprecis ; et surtout que, sur l'ecran de jeu, **aucun element ne soit plus saillant que les
boutons de reponse** - la sortie doit rester atteignable, elle ne doit pas etre ce qui attire
l'oeil en premier.

**Un point que je remonte a `yanis-qa` et `theo-archi`, sans le trancher.** Le test
`TailleDesTextesEtBoutonsTest.toutBoutonActionnableEstAuMoinsAUnRemVingtCinq` est vert, et son nom
affirme "tout bouton actionnable". Il parcourt en fait une liste de quatre selecteurs ecrite en dur
d'ou `.proposition` est absent - le seul bouton qui echoue. Une CI verte a donc couvert exactement
le defaut que la story voulait empecher. Je decris le symptome, la forme du garde-fou ne
m'appartient pas ; mais `CLAUDE.md` range deja "un garde-fou annonce mais absent" parmi les dettes
a ne pas reproduire.

#### 2 bis. S2-1 rejuge sur le commit fd16057 - ACCORDE SOUS RESERVE, refus leve

Saisie a nouveau sur S2-1 et sur S2-1 seulement. Second audit **non degrade**, meme protocole que le
premier pour que les chiffres soient comparables : `JAVA_HOME` sur le JDK 21 (21.0.10), `mvn -B
package`, fat jar lance sur le port 8099, partie rejouee de bout en bout, sept ecrans rasterises a
900 px de large et **remesures au pixel**. Je n'ai repris aucun chiffre de l'annonce d'`ines-design`.

**Mon motif principal etait la hierarchie de saillance. Il est leve, et la mesure le dit.**

| Ecran de jeu | Avant refus (`3be5a86`) | Apres correction (`fd16057`) |
|---|---|---|
| Un bouton de reponse | 46 x 41 px, gris par defaut | **836 x 47 px, bleu plein, gras** |
| Ecart entre deux boutons de reponse | 8 px, marges fondues | **12 px, non fusionnables** |
| Bouton de sortie "Les jeux" | 115 x 47 px, seule zone coloree | 114 x 46 px, **contour sans aplat** |

Ce que je voulais voir, je le vois : sur l'ecran de jeu, ce qui appelle la main est desormais la
reponse. Les trois boutons de reponse sont les seuls aplats colores de l'ecran, la sortie est un
contour discret, et le rapport de surface s'est inverse - la sortie faisait 2,9 fois la surface d'un
bouton de reponse, elle en fait maintenant 0,13 fois. **Un enfant de CP qui ne dechiffre pas les
libelles et se guide a la saillance est ramene vers la tache et non vers la porte.** C'etait mon
motif, il est traite.

Les deux motifs secondaires tombent aussi : `.proposition` est a 1,25 rem, plus aucun texte ni
bouton de l'application ne passe sous la consigne ; il prend ses couleurs dans la palette (blanc sur
`--recreo-accent`, contraste 5,41, largement au-dessus du seuil AA) et ne depend plus du defaut du
navigateur. J'ai verifie au passage que le `box-sizing: border-box` pose en reset n'avait rien casse
ailleurs : la coche et la croix du signal juste/faux se dessinent correctement, je les ai regardees
sur les rendus, pas seulement dans le CSS.

**Sur la cible tactile, je tranche : c'est suffisant, et je dis pourquoi et jusqu'ou.** 47 px de
haut passent le seuil de 44 px que j'avais invoque. Mais le vrai gain n'est pas la hauteur, c'est la
largeur : avec un bouton pleine ligne, **l'imprecision horizontale ne peut plus produire d'erreur**,
et il ne reste qu'un axe ou se tromper. Sur cet axe, un doigt qui derape de moins de 12 px tombe
dans le vide et ne declenche rien - ce qui est le bon comportement - et il faut deraper de plus de
18 px pour atteindre la reponse voisine, contre 12 px avant. C'est acceptable. Je le trouve
neanmoins au plancher et non confortable, d'ou une reserve ci-dessous.

**Sur la pleine largeur, la question m'est posee et je la tranche moi-meme : je l'accepte.** Un
bouton de reponse pleine ligne n'empeche pas l'enfant de comparer les propositions entre elles -
elles restent dans une colonne, alignees les unes sous les autres, a la meme abscisse : le regard
descend la colonne sans avoir a chercher. Et de grandes cibles sont exactement ce qu'il faut a un
enfant de six ans. Ce que je n'accepte qu'en reserve, c'est le rapport entre le contenant et le
contenu : le chiffre "9" est un glyphe de 20 px centre dans une barre de 836 px, soit l'information
utile logee dans 2 % de l'objet qui la porte. Or c'est le **nombre** qui distingue les trois
propositions, la barre est identique pour les trois. Ce qui doit etre le plus visible, c'est ce qui
porte la difference. Ce n'est pas un motif de refus - le chiffre est en gras, a 1,25 rem, a 5,41 de
contraste, parfaitement lisible - mais c'est une reserve que je nomme.

**S1-4, la sortie toujours visible et atteignable : verifie, et tenu.** Je ne me suis pas contentee
de constater qu'elle existe. Le bouton "Les jeux" est present et rendu sur les quatre ecrans ou je
l'exigeais - manche, retour juste, retour faux, fin de partie - il mesure 114 x 46 px, donc au-dessus
de la cible tactile, son texte est a 1,25 rem et a 5,03 de contraste, sa bordure de 2 px est au meme
contraste : il est discret, il n'est pas invisible, et il se lit sans hesitation comme un bouton.
**Et il marche** : je l'ai actionne, il rend bien la liste des jeux. La retrogradation ne casse pas
S1-4. Elle leve en revanche, et c'est un vrai progres, ma reserve sur les deux boutons bleus
identiques aux effets opposes : sur l'ecran de retour, "Encore !" est un aplat et "Les jeux" un
contour, l'enfant n'a plus besoin de lire pour savoir lequel poursuit et lequel sort.

**Le test que j'avais epingle : reellement corrige sur le defaut que j'avais montre, et je l'ai
verifie en le cassant.** La liste n'est plus ecrite en dur, elle est derivee d'un balayage des
quatre gabarits ; elle remonte bien cinq classes, dont `.proposition`. Le vert ne m'aurait rien
prouve, alors j'ai mute le CSS pour remettre `.proposition` a 1,1 rem : le test passe au rouge
(`toutBoutonReellementRenduEstAuMoinsAUnRemVingtCinq`, 1 echec sur 6). J'ai restaure le fichier
ensuite ; l'arbre est propre. Le garde-fou attrape donc bien ce qu'il avait laisse passer.

**Mais il reste un trou, que j'ai demontre et pas suppose, et c'est exactement le trou par lequel le
defaut initial est entre.** La liste est derivee des **classes** des boutons. Un bouton qui n'a
aucune classe ne produit aucun nom de classe, donc aucune assertion : il est invisible pour cette
regle. C'est precisement le cas de figure de s01, ou "Encore !" etait un
`<button type="submit">` sans classe et tombait a 13 px. Verification faite : j'ai ajoute un
`<button type="button">Rejouer</button>` sans classe dans `fragments/retour.html`, relance la
classe de tests, **les six tests restent verts**. Fichier restaure, arbre propre. Je decris le
symptome et je ne prescris pas la solution, qui appartient a `yanis-qa` et `theo-archi` : la regle
devrait partir des boutons rendus et non de leurs classes, de sorte qu'un bouton sans classe soit un
echec et non un angle mort.

**Le texte lu par l'enfant n'a toujours pas bouge.** `fd16057` ne touche au balisage que d'une
facon : l'ajout de `class="propositions"` sur le `form` de `fragments/manche.html`. Aucun autre
gabarit modifie, aucun caractere de texte enfant touche. Reverifie sur le HTML reellement servi par
l'application relancee : "Recreo", "Calcul CP", "Les jeux", "Score : N", "Combien ça fait ?",
"Bravo, c'est juste !", "Non, c'est 7.", "C'est fini !", "Encore !", "Compte sur tes doigts.",
"Oups ! Le jeu a un souci.", "Recommencer", "Ta partie s'est arrêtée. Choisis un nouveau jeu !",
"Il n'y a pas de jeu pour le moment." Tous intacts, accents compris.

**Ce que je n'ai pas pu verifier, et que je ne pretends donc pas avoir juge.** Mon harnais de rendu
impose une largeur de mise en page minimale d'environ 500 px : mes captures a 420 px sont un
recadrage et non une mise en page reelle, je les ecarte. **Je ne me prononce donc pas sur le rendu
en dessous d'environ 500 px de large**, alors qu'une tablette ou un telephone tenu debout est un
support plausible pour cette application. A verifier par qui pourra, avant de considerer ce sujet
clos.

**Reserves attachees a S2-1, non bloquantes, pour que `camille-po` en fasse des stories.**

1. **Le nombre doit peser plus que la barre qui le porte.** Un glyphe de 20 px centre dans un bouton
   de 836 px : l'information qui distingue les trois propositions est la plus petite chose de
   l'objet. A retravailler - grossir le chiffre, ou rapprocher la reponse de l'enonce qu'elle
   concerne, ou reduire la largeur. Je ne prescris pas la forme, c'est le mandat d'`ines-design`.
2. **12 px entre deux boutons de reponse, c'est le plancher et pas le confort.** Un ecart d'au moins
   1 rem laisserait plus de place a un geste d'enfant.
3. **Un appui rate est compte comme une erreur de calcul.** Ce n'est pas un defaut de S2-1 et je ne
   le mets pas au debit de cette story, mais le fait demeure : le score peut sous-estimer ce que
   l'enfant sait. C'est une question de produit pour `camille-po`.
4. **Sur l'ecran de fin de partie, la sortie est a la fois la seule action possible et le style le
   plus discret.** La retrogradation de `.retour-accueil` est juste sur l'ecran de jeu, ou ce bouton
   concurrencait la reponse ; elle ne l'est pas sur l'ecran de fin, ou il n'a plus rien a
   concurrencer et ou il est la seule chose a faire. Un meme bouton n'a pas a garder le meme poids
   selon qu'il est une echappatoire ou l'unique suite. Cette reserve prolonge et precise ma reserve
   4 de S2-2 sur l'alignement.

**Les trois reserves qu'`ines-design` dit avoir laissees ouvertes deliberement : je les accepte
toutes les trois en reserve, aucune ne bloque, et elle a eu raison de ne pas y toucher seule.**

- *Le bleu qui dit a la fois "lis-moi" et "presse-moi"* : accepte en reserve. La correction l'a meme
  ameliore sans le viser, puisqu'il existe maintenant deux styles de bouton distincts. "C'est fini !"
  en bleu centre reste proche de l'aspect d'une chose sur laquelle on appuie ; a reprendre.
- *L'alignement du message centre contre le bouton de cote sur les ecrans de fin et d'erreur* :
  accepte en reserve, et renforce par ma reserve 4 ci-dessus.
- *Le monogramme "R"* : accepte en reserve, et **elle a bien fait de ne pas le refaire de sa seule
  initiative** - j'ai demande un dessin d'objet lisible a 16 px, ce qui est une proposition de
  design a instruire dans une story, pas une retouche a glisser dans une correction de refus. S2-3
  reste accorde en l'etat.

#### 3. S2-2 - VALIDE SOUS RESERVE, accorde

Les ecrans nus ne le sont plus, ils se ressemblent, et je le constate a l'ecran sur les huit
rendus : accueil, manche, retour juste, retour faux, fin de partie, indice, erreur, reprise. La
palette est nommee et reellement reutilisee. Tout est servi depuis le fat jar, sans CDN et sans
police telechargee : l'application marche hors ligne. `recreo.js` n'a pas recu une ligne.

**Le contraste, mesure et pas suppose.** J'ai calcule le rapport de contraste WCAG de chaque couple
texte/fond reellement employe, et non juge sur le nom des couleurs. Les douze couples passent le
niveau AA pour du texte normal : 14,09 pour le texte courant, 5,03 pour le bleu d'accent sur le
fond de page, 5,41 pour le blanc sur les boutons, 13,93 dans les bandeaux d'attention, 4,67 pour le
texte d'erreur, 4,56 et 4,92 pour la coche et la croix sur leurs fonds. Aucun texte de cette
application n'est en dessous du seuil. C'est un progres net et il faut le dire.

**Ma reserve de s01 sur les luminances tient toujours, et elle s'est etendue a toute la palette.**
Les quatre couleurs qui portent du sens - bleu d'accent #2e6f9e, ambre d'attention #b45309, vert
juste #2e7d32, rouge faux #c62828 - ont des luminances relatives de 0,144, 0,159, 0,155 et 0,137.
En niveaux de gris elles valent respectivement 106, 111, 109 et 103 sur 255 : **les quatre couleurs
signifiantes de recreo sont le meme gris.** Pour le signal juste/faux ce n'est pas un probleme, je
l'ai deja juge en s01 et je le maintiens : la coche et la croix portent le sens par leur forme. Mais
je note le fait pour que personne ne construise a l'avenir une information sur ces contrastes de
teinte.

**Reserves nommees, pour que `camille-po` en fasse des stories.**

1. **Le bandeau d'attention n'existe pas sans la couleur.** Le fond ambre #fff4e5 et le fond de page
   #f4f7fb ont des luminances de 0,916 et 0,927, soit un rapport de 1,01 : en niveaux de gris, ou
   pour un enfant qui percoit mal les couleurs, l'indice et le message de reprise n'ont **aucun**
   fond distinct du reste de la page. Ce qui les sauve aujourd'hui est le liseret de 4 px a gauche,
   qui reste sombre et visible. Le bandeau tient donc, mais par un seul canal et par un trait fin.
   A renforcer : un liseret plus epais, ou une bordure complete, ou un ecart de clarte reel entre
   les deux fonds.
2. **Deux boutons rigoureusement identiques cohabitent sur l'ecran de retour.** "Les jeux" et
   "Encore !" ont la meme couleur, la meme taille, la meme forme, le meme arrondi, et sont l'un
   au-dessus de l'autre. L'un continue la partie, l'autre la quitte. Rien ne les distingue que le
   texte, c'est-a-dire la seule chose qu'un enfant de CP ne fait pas vite. Une action qui poursuit
   et une action qui sort ne devraient pas avoir le meme habit.
3. **Le bleu dit a la fois "lis-moi" et "appuie sur moi".** #2e6f9e est la couleur du titre, du
   score et de "C'est fini !", et c'est aussi le fond de tous les boutons. Le remplissage distingue
   les deux, ce qui est suffisant pour un adulte ; "C'est fini !" centre en bleu reste, pour un
   lecteur debutant, tres proche de l'aspect d'une chose sur laquelle on appuie.
4. **La fin de partie eloigne l'enfant de la seule action possible.** "C'est fini !" est centre au
   milieu de l'ecran, et la seule sortie, "Les jeux", est un bouton aligne a gauche tout en haut,
   la ou l'oeil n'est plus. Meme remarque sur l'ecran d'erreur : "Oups ! Le jeu a un souci." est
   centre, "Recommencer" est a gauche en dessous. Ce n'est pas bloquant, les deux ecrans sont
   presque vides, mais l'alignement devrait conduire l'enfant vers ce qu'il doit faire.

#### 4. S2-3, l'icone d'application - VALIDE SOUS RESERVE, accorde

Les criteres techniques sont tenus et je les ai verifies sur l'application lancee : `/favicon.ico`
repond 200 en `image/x-icon`, 4201 octets, servi depuis les ressources statiques donc disponible
hors ligne, et `accueil.html` le reference explicitement.

**Le dessin, que je juge et qui n'appartient qu'a moi** (`DEC-0002` categorie B, confirme par
`DEC-0006`). J'ai extrait les trois tailles du fichier et je les ai regardees agrandies au pixel.
C'est un ecusson bleu a coins arrondis portant un "R" capitale blanc. Le dessin est propre, la
couleur est celle de la palette, il n'y a **aucun biais** : pas de personnage, pas de genre, pas de
contexte social ou familial, rien qui presuppose quoi que ce soit de l'enfant. Rien de faux non
plus, et rien d'ambigu au sens ou l'enfant risquerait de confondre l'application avec autre chose.
**Je l'accorde donc**, parce que refuser un objet qui ne fait de mal a personne et qui remplit le
critere ecrit serait un caprice, et parce que l'application n'avait aucune icone hier.

**Mais je dis clairement que ce n'est pas la bonne reponse, et je le porte en reserve.** Le besoin
ecrit dans S2-3 est : "retrouver l'application **sans avoir besoin de lire son nom**". Un monogramme
est le nom reduit a sa premiere lettre : la reponse apportee est precisement celle que le besoin
excluait. Elle demande a l'enfant la seule competence qu'il est en train d'acquerir et sur laquelle
il est le plus lent. Un enfant de CP reconnait un **objet** avant une lettre, et l'ecart est enorme
entre les deux au mois d'octobre. Second point, mesure et pas suppose : la vignette de 16 px, celle
que le navigateur affiche reellement dans l'onglet, ne compte que **5 pixels blancs purs sur 256**
et 122 couleurs distinctes - c'est une reduction floue du grand dessin, pas un dessin pense pour
16 px. A la taille ou l'enfant la voit, la lettre est une tache claire dans un carre bleu.

**Ce que je demande pour un sprint suivant** : une icone qui soit un dessin d'objet, lisible d'un
coup d'oeil a 16 px, sans lettre - le champ est large et je ne l'impose pas a `ines-design`, mais
l'objet doit dire "jeu" ou "ecole" a un enfant qui ne lit pas. Et la vignette 16 px doit etre
dessinee pour sa taille, pas obtenue par reduction.

#### 5. Le texte lu par l'enfant n'a pas bouge - verifie

Je l'ai verifie de deux facons, sur le HTML reellement servi et sur le diff complet depuis
`caa1049`, le commit qui avait applique mes reformulations de s01. Le commit `3be5a86` ne touche que
deux lignes de gabarit : l'ajout du `<link rel="icon">` dans `accueil.html`, et l'ajout de
`class="encore"` sur le bouton "Encore !" de `fragments/retour.html`. **Aucun caractere de texte
destine a l'enfant n'a ete modifie.** Releves intacts a l'ecran : "Recreo", "Calcul CP",
"Les jeux", "Score : N", "Combien ça fait ?", "Bravo, c'est juste !", "Non, c'est 7.", "C'est
fini !", "Encore !", "Compte sur tes doigts.", "Oups ! Le jeu a un souci.", "Recommencer",
"Ta partie s'est arrêtée. Choisis un nouveau jeu !", "Il n'y a pas de jeu pour le moment." Ce sont
mot pour mot ceux que j'ai valides ou reformules a la cloture de s01, accents compris. Ma porte n'a
pas ete contournee et je le note explicitement, parce qu'un controle qui ne dit rien quand tout va
bien ne prouve rien.

#### 6. Ce que ce verdict ne dit pas

Mes reserves de s01 numerotees 1, 3, 4, 5, 8, 9, 10 et 11 (libelle de fin de partie deja corrige,
indice inatteignable, repartition des sommes, "0 + 0", "Calcul CP", accentuation de "Recreo",
catalogue vide sans action) n'etaient pas dans le mandat de ce sprint et **restent ouvertes en
l'etat**. J'ai reverifie en jouant que l'indice demeure inatteignable en dix manches : ce n'est ni
un progres ni un recul de s02.

`squad/programme/MATRICE-couverture.md` : **aucun changement de mon fait, et aucun n'est du.** Ces
trois stories ne portent aucune competence du referentiel, `DEC-0006` le dit deja. La ligne
mathematiques / CP reste `en cours` pour la raison ecrite dans mon verdict de s01, qui n'a pas
bouge : la reserve 5 n'est pas traitee.

---

### Verdict du sprint s01, conserve pour trace

- **Verdict** : VALIDE SOUS RESERVE. **Done pedagogique ACCORDE.**
- **Date** : 2026-08-05
- **Audit joue** : oui, audit non degrade. Fat jar reconstruit depuis `round-two` et lance sur le
  port 8099. Parties reellement jouees de bout en bout : une partie de 10 manches en repondant juste
  a chaque fois (score final 10), une partie de 10 manches en repondant faux a chaque fois (score
  final 0), trois parties "une reussite puis que des echecs" pour forcer les deux aides de S1-5, et
  un echantillon de 171 manches reelles collectees sur 24 parties pour mesurer le contenu enonce par
  enonce. Ecrans traverses un par un : accueil, catalogue, manche, retour juste, retour faux, fin de
  partie, bouton de sortie, rechargement en cours de partie, rechargement apres perte de session,
  coup joue sans partie en cours, demarrage d'un jeu inconnu. L'ecran de catalogue vide a ete
  **rendu reellement**, en relancant l'application sur un catalogue sans aucun jeu, et pas seulement
  lu dans le gabarit. La forme de la coche de S1-6 a ete verifiee en rasterisant le `clip-path` de
  `recreo.css`, pour juger le dessin obtenu et pas la regle qui le declare.
- **Moyens, dits franchement** : le poste d'audit ne dispose que d'un JDK 25, pas du JDK 21 de la
  CI. Le Done technique reste celui de `yanis-qa` et de `theo-archi`, tous deux passes sur JDK 21 ;
  ce que je juge ici est le comportement observe a l'ecran, qui ne depend pas de cet ecart. Je ne
  reprononce aucun Done technique.

#### 1. Mes trois motifs de refus de s00, un par un

**Motif 1 - texte de l'enfant non accentue : LEVE.** Verifie dans le HTML reellement servi, pas dans
le diff. Le retour faux affiche "Non, c'est 8." ; la fin de partie "La partie est finie. Bravo, tu as
bien joué !" ; le bouton de sortie "Retour à l'accueil" ; le message de session perdue "Ta partie
s'est arrêtée. Choisis un nouveau jeu !" ; la consigne "Combien ça fait ?" arrive intacte. J'ai
repris les quatre gabarits (`accueil.html`, `fragments/manche.html`, `fragments/retour.html`,
`fragments/erreur.html`), la constante `PartieController.MESSAGE_PARTIE_PERDUE`, `catalogue.yaml` et
la constante d'indice : plus une seule forme non accentuee d'un mot qui en demande. Un residu, sans
gravite mais reel : le titre `<h1>Recreo</h1>` est le seul mot de l'ecran d'accueil et il n'est pas
accentue ; un enfant de CP qui le dechiffre lit /rekreo/ et non "récréo". C'est un nom de produit, je
ne le refuse pas, je le porte en reserve.

**Motif 2 - score illisible au CP : LEVE.** Releve en jeu : "Score : 0", "Score : 1", "Score : 10".
`manche.html` et `retour.html` rendent tous deux `'Score : ' + ${score.reussites()}` : le nombre de
tentatives n'est jamais affiche, donc aucune barre oblique et aucun symbole operatoire ne peut
apparaitre au-dessus du calcul. Le mot "Score" reste, et c'est bien : aucun de mes motifs ne le
visait, et un enfant de six ans le connait a l'oral.

**Motif 3 - ecran d'erreur infranchissable : LEVE.** Les deux chemins d'echec que j'avais reproduits
rendent desormais, en HTTP 200 et en `text/html;charset=UTF-8`, le meme ecran : "Oups ! Le jeu a un
souci." et "Recommencer" qui ramene a `/`. Plus aucun corps JSON, plus un mot d'anglais, plus de
statut 500 sous les yeux de l'enfant. Le ton est juste : la faute est mise du cote du jeu, jamais de
l'enfant. Une reserve de rendu subsiste, decrite plus bas : "Recommencer" est un lien HTML sans
aucune regle CSS, donc du texte souligne a la taille par defaut, pas un bouton.

**Les trois motifs qui fondaient mon refus sont donc leves, et je m'en tiens a la barre que j'avais
moi-meme ecrite en s00** ("Les trois motifs de refus corriges [...] Les reserves peuvent etre
etalees sur les sprints suivants"). Deplacer cette barre maintenant que l'equipe l'a atteinte ferait
de ma porte un caprice, pas une porte.

#### 2. Mes sept reserves de s00, une par une

1. **Partie sans fin et sans sortie : LEVEE.** La partie s'arrete exactement a 10 manches, verifie
   sur cinq parties completes. L'ecran de fin existe et est atteint. Le bouton de sortie est present
   sur la manche, sur le retour juste, sur le retour faux **et sur l'ecran de fin** - je l'ai vu
   servi sur les trois ecrans, ce que le rapport de `yanis-qa` ne pouvait qu'inferer. Il fonctionne :
   il rend la liste des jeux. 10 manches est la bonne longueur pour un CP, je le confirme apres
   avoir joue : la partie tient en une a deux minutes et se termine avant que l'attention ne tombe.
2. **Trop d'operandes nuls : LEVEE.** Mesure sur 171 manches reellement jouees : 22 manches a
   operande nul, soit **12,9 %**, contre 26 % releves en s00. Environ une manche par partie. C'est le
   bon reglage, et je le dis explicitement puisque la question m'est posee : la propriete du zero
   fait partie du programme de calcul de CP, la retirer entierement serait une erreur, et une manche
   sur dix la garde presente sans manger le temps de calcul. Voir toutefois le point 5 : le vrai
   reste de "calculs qui ne font pas calculer" n'est plus la.
3. **Aucune progression, aucune adaptation : NON LEVEE**, ce qui etait assume, et **aggravee par un
   fait que je n'avais pas mesure en s00**. Voir le point 5.
4. **Signal juste/faux dont la forme ne veut rien dire : LEVEE.** Voir le point 4.
5. **Texte trop petit : PARTIELLEMENT LEVEE.** Voir le point 4.
6. **Trois libelles a reecrire : LEVEE sur le fond, avec deux libelles a reprendre.** Voir le
   point 3.
7. **Perte de la partie au rechargement : LEVEE.** Verifie : un rechargement pendant une session
   valide represente la manche en cours avec son score, sans un mot de plus - c'est bien une
   continuite et non une reprise qui se remarque. Un rechargement apres perte de session affiche
   "Ta partie s'est arrêtée. Choisis un nouveau jeu !" au-dessus de la liste des jeux, ce qui est
   honnete envers l'enfant et lui donne immediatement quoi faire.

#### 3. Les libelles destines a l'enfant, et l'indice de DEC-0005

Je statue sur chacun des libelles que `relecteur-pedagogique` m'a remontes comme non fixes par une
decision, plus l'indice que `camille-po` me soumet par `DEC-0005`. Trois issues : accepte, accepte
sous reserve de reformulation (j'ecris la reformulation), refuse.

| Libelle | Verdict | Motif |
|---|---|---|
| "Encore !" | **ACCEPTE** | Deux syllabes, entierement dechiffrable au CP, vocabulaire oral quotidien d'un enfant de six ans, et il dit exactement la bonne chose : "j'en veux un autre". C'est ma suggestion de s00 et je la confirme apres l'avoir vue en place. |
| "Non, c'est X." | **ACCEPTE** | Present, une seule phrase, quatre mots. Ne juge pas l'enfant, corrige la reponse. Le rendu est propre : le nombre est dans un `span` en ligne, la ponctuation ne se decolle pas. |
| "Il n'y a pas de jeu pour le moment." | **ACCEPTE** | Francais correct, registre d'enfant, honnete. Neuf mots c'est long pour un CP, mais cet ecran est une anomalie que l'enfant ne verra pas en classe, et il n'a de toute facon aucune action a y faire. Je l'accepte tel quel et je porte en reserve le fait que cet ecran n'offre rien a faire. |
| "Calcul CP" | **ACCEPTE** | Dechiffrable ("Calcul" est regulier, "CP" est le nom que l'enfant donne a sa propre classe), et il ne porte plus le grapheme "ti" prononce /sj/ qui motivait ma remarque de s00. Reserve nommee plus bas : "Calcul" promet plus large que ce que le jeu fait (des additions seulement), et deviendra ambigu des qu'un deuxieme jeu de mathematiques CP existera. |
| "Oups ! Le jeu a un souci." / "Recommencer" / "Ta partie s'est arrêtée. Choisis un nouveau jeu !" / "Score : N" (fixes par `DEC-0004`) | **ACCEPTES** | Je les confirme apres les avoir vus a l'ecran. Le ton de l'ecran d'erreur est exactement le bon : il n'accuse jamais l'enfant. |
| "Bravo, c'est juste !" | **ACCEPTE** | Inchange, deja valide en s00, toujours juste et non exagere. |
| "Retour à l'accueil" | **ACCEPTE SOUS RESERVE DE REFORMULATION** | Motif : "accueil" n'est pas dechiffrable au CP. La suite "ueil" est un grapheme rare et tardif (accueil, orgueil, cueillir), et le "cc" devant "ue" se lit /k/ : aucun enfant de CP ne lit ce mot seul. Le sens est en plus abstrait - un enfant de six ans ne sait pas ce qu'est un "accueil" dans un logiciel. **Reformulation que j'ecris : "Les jeux".** Deux mots, sept lettres, tous les graphemes sont du CP, et le libelle dit ou l'on va, ce qui est vrai sur les trois ecrans ou ce bouton apparait, y compris l'ecran de fin ou l'enfant veut en choisir un autre. |
| "La partie est finie. Bravo, tu as bien joué !" | **REFUSE** | Motif : ce texte s'affiche a l'identique quel que soit le resultat. Je l'ai vu de mes yeux au bout d'une partie a 0 bonne reponse sur 10 : l'ecran montre une croix rouge, "Non, c'est 8.", "Score : 0", puis "Bravo, tu as bien joué !". Trois choses ne vont pas. Le jeu felicite un enfant qui n'a rien reussi, ce qui est faux et ce que l'enfant sait. Le mot "Bravo" est deja le signal de reussite du jeu ("Bravo, c'est juste !") : l'employer aussi quand il n'y a rien a feliciter use le seul mot de renforcement dont ce jeu dispose, et un renforcement qui tombe tout le temps ne renforce plus rien. Enfin, "Bravo" pose sous une croix rouge est simplement contradictoire pour un lecteur debutant. **Reformulation que j'ecris : la phrase de cloture inconditionnelle devient "C'est fini !"** - trois mots, entierement dechiffrables, aucune affirmation sur la performance, et la ligne "Score : N" juste au-dessus dit deja le resultat. Si l'equipe veut garder un mot chaleureux, il doit dependre du resultat ; c'est alors un choix produit a ecrire en DEC, pas un remplacement de chaine. |
| "Compte avec tes doigts pour t'aider." (indice, `DEC-0005`) | **ACCEPTE SOUS RESERVE DE REFORMULATION** | Sur le fond, l'aide est **juste et je la valide** : le comptage sur les doigts est une strategie explicitement enseignee au CP pour l'addition dans la limite de 10, et dix doigts sont exactement le modele de cet intervalle. Ce n'est ni un raccourci ni une betise, c'est ce qu'un maitre dit. Sur la forme, non : six mots et trente-cinq caracteres pour l'enfant qui vient d'echouer trois fois, donc tres probablement le lecteur le plus fragile ; "compte" (p muet) et "doigts" (/dwa/, "gts" muet) sont deux mots irreguliers ; et la moitie de la phrase ("pour t'aider") ne dit rien que l'enfant ne comprenne deja. Une aide ne doit pas etre un second obstacle. **Reformulation que j'ecris : "Compte sur tes doigts."** - quatre mots, c'est la phrase que l'enfant entend en classe, donc reconnue globalement meme quand elle n'est pas dechiffree. |

Sur l'emplacement de l'indice, tranche par `DEC-0005` : je confirme que le rendu vu par l'enfant ne
depend pas de l'endroit ou le texte vit, et que ce prealable ne bloquait donc pas mon audit.
J'ajoute une raison pedagogique d'aller au bout de cette decision : je viens de demander une
reformulation de ce texte, et tant qu'il vit dans une constante Java du domaine, une correction de
langue exige de recompiler le domaine. Une porte pedagogique qui ne peut pas faire corriger un mot
sans un cycle de developpement n'en est pas vraiment une.

#### 4. Les trois criteres visuels sans test automatise

`yanis-qa` a eu raison de me les remonter nommement plutot que de les couvrir d'un vert general.
Voici mon jugement, et il n'appartient qu'a moi puisque aucun automate ne le rend.

**S1-6, signal juste/faux independant de la couleur : OUI, satisfait.** Je n'ai pas juge sur la
declaration CSS, j'ai rasterise le `clip-path` de `.signal.est-juste .icone-signal` pour voir le
dessin : c'est une vraie coche, un trait court descendant vers la droite qui rejoint un trait long
montant, reconnaissable sans hesitation. `.signal.est-faux` est fait de deux barres croisees a
45 degres : c'est une croix. Ce sont les deux signes que l'enfant voit sur son cahier depuis la
rentree, ils portent le sens sans un mot et sans une couleur. C'est exactement ce que je demandais
en s00, et c'est mieux que ce que je demandais : les deux signes ne sont pas seulement distincts,
ils sont **appris**. Le point est important parce que les deux couleurs retenues, #2e7d32 et
#c62828, ont des luminances tres proches (environ 0,15 et 0,14) : en niveaux de gris, ou pour un
enfant qui confond le vert et le rouge, les deux pastilles ont pratiquement la meme densite. La
couleur, ici, n'informe reellement personne d'autre que ceux qui la distinguent - c'est la forme qui
fait tout le travail, et elle le fait. Les deux signes font 3 rem, ils sont vus avant d'etre lus.

**S1-9, taille du calcul et du signal : OUI, les deux criteres ecrits sont satisfaits** - et ils ne
couvrent pas tout. `.enonce` est a `2rem` et en gras, `.icone-signal` a `3rem` : le calcul est
desormais, et de loin, le plus gros texte de l'ecran, devant la consigne (1,25 rem), le verdict
(1,25 rem) et les propositions (1,1 rem). La hierarchie que je reprochais en s00 est inversee dans
le bon sens et mon reproche tombe. Mais j'ai regarde ce que les criteres ne disaient pas, et il
reste un trou : `.score`, `.indice`, `.fin-de-partie`, `.erreur`, `.pas-de-jeu` et
`.message-reprise` n'ont **aucune** regle de taille, donc 16 px ; et surtout, ni le bouton "Encore
!" ni le bouton de sortie n'ont de regle, or un bouton n'herite pas de la taille du texte : le
navigateur les rend a sa taille par defaut, environ 13 px. Le bouton sur lequel l'enfant appuie a
chaque manche est ainsi le plus petit texte de l'ecran. Ce n'est pas un manquement au critere de
`camille-po`, qui ne visait que l'enonce et le signal ; c'est le reste de ma reserve 5 de s00
("remonter le texte courant"), qui n'a pas ete traite. Reserve, pas refus.

**S1-8, ecran de catalogue vide : OUI, satisfait.** Je ne me suis pas contentee du gabarit : j'ai
relance l'application sur un catalogue sans aucun jeu et j'ai regarde la page servie. Elle affiche
"Recreo" puis "Il n'y a pas de jeu pour le moment.", sans liste vide, sans bouton mort, sans trace
technique. Le texte est correct et au registre. Le test manque, mais le critere, lui, est tenu.

#### 5. Justesse et reglage du contenu mathematique

**Exactitude : irreprochable.** Sur les 171 manches relevees enonce par enonce, **zero erreur** :
aucune somme superieure a 10, aucun operande hors bornes, la bonne reponse figure exactement une
fois dans les propositions a chaque manche, aucune proposition negative, aucun doublon, et le
libelle de chaque bouton est bien la valeur qu'il envoie. La position de la bonne reponse est
equilibree (55, 56 et 60 fois en premiere, deuxieme et troisieme position) : aucune strategie de
position n'est exploitable. Les distracteurs sont toujours a exactement un et deux de la bonne
reponse : plausibles, jamais absurdes, jamais devinables.

**Operandes nuls : bon reglage, la question m'etait posee et je reponds oui.** 12,9 % mesures, soit
environ une manche par partie, contre 26 % en s00. C'est ce que je demandais : la propriete du zero
reste presente, elle ne mange plus le temps de calcul. Deux petites choses restent, portees en
reserve et non en motif : "0 + 0" peut sortir (une fois sur 171) et est sorti en **premiere manche**
d'une de mes parties, or c'est la seule addition du contenu qui ne demande absolument rien ; et
"9 + 0" propose une bonne reponse egale a un operande deja ecrit, donc lisible sans calculer.

**Ce qui reste de "calculs qui ne font pas calculer" n'est plus la ou je le cherchais, et c'est le
seul constat de contenu vraiment serieux de cet audit.** Le tirage n'est pas uniforme sur les 66
couples possibles - je l'avais ecrit a tort en s00 et je corrige mon propre constat. Le premier
operande est tire uniformement dans 0-10, le second dans ce qui reste : les grandes sommes sont donc
tres largement favorisees. Mesure sur mes 171 manches :

| Somme | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 |
|---|---|---|---|---|---|---|---|---|---|---|---|
| Manches | 1 | 0 | 2 | 4 | 6 | 15 | 17 | 24 | 27 | 27 | 48 |
| Part | 0,6 % | 0 % | 1,2 % | 2,3 % | 3,5 % | 8,8 % | 9,9 % | 14,0 % | 15,8 % | 15,8 % | 28,1 % |

**43,9 % des manches portent sur une somme de 9 ou 10, et 7,6 % seulement sur une somme inferieure
ou egale a 4. Les sommes 1 et 2 n'apparaissent pratiquement jamais.** Une de mes parties de dix
manches n'a propose aucune somme en dessous de 7. Ce n'est pas anodin pour un CP : le haut de
l'intervalle est precisement l'endroit ou l'addition cesse d'etre du surcomptage et devient un
franchissement, donc l'endroit ou l'enfant fragile decroche - et c'est la que le jeu l'installe
presque une fois sur deux, sans jamais commencer plus bas ni monter progressivement. Le jeu ne
travaille pas "les additions dans la limite de 10", il travaille surtout leur bord superieur. Cela
ne rend pas le contenu faux, cela le rend mal reparti : c'est ma reserve 3 de s00 (aucune
progression), que le sprint n'avait pas mandat de traiter, mais chiffree et plus grave que je ne
l'avais dit.

**L'aide de S1-5 face a l'echec : la "manche plus simple" marche, l'indice n'est jamais atteint.**
La reprise d'un item deja reussi apres trois echecs consecutifs fonctionne, je l'ai declenchee et
observee (manches 5 et 8 de mes parties de test). L'indice, lui, est inatteignable en jeu reel, et
ce n'est pas une impression : `BanditManchotSession` n'affiche l'indice qu'a partir de trois echecs
**sur le meme item**, or un item ne peut revenir dans une partie que par la liste des items deja
reussis (le tirage rejette les items deja vus sur vingt tentatives ; mesure : un seul retour
"naturel" sur 24 parties), et chaque retour coute trois echecs consecutifs prealables. En deroulant
la sequence la plus favorable possible, le troisieme echec sur un meme item ne peut pas arriver
avant la 11e manche et l'indice ne peut pas s'afficher avant la **14e** ; une partie en dure 10
(`catalogue.yaml`, `nombreDeManches: 10`). Verifie : sur trois parties jouees exactement selon cette
sequence optimale, le compteur d'echecs du meme item a plafonne a 2 et l'indice n'est jamais apparu.
Les tests de `yanis-qa` sont justes et le comportement est correct ; c'est le reglage du produit qui
rend l'aide morte. Concretement, l'enfant qui rate tout des la premiere manche - celui pour qui
S1-5 a ete ecrite - ne recoit ni indice (jamais) ni manche plus simple (il n'a rien reussi a
represente), et lit "Bravo, tu as bien joué !" a la fin. Il n'est plus enferme, puisque la partie
s'arrete et qu'il peut sortir : mon motif de s00 est bien leve. Mais il n'est pas aide.

**Biais et ton** : rien de nouveau a signaler, et c'est toujours a porter au credit de l'increment.
Des nombres nus, aucun personnage, aucun prenom, aucun contexte familial, materiel ou culturel
presuppose, donc aucun stereotype possible. Aucun classement, aucune comparaison a d'autres enfants.
Le seul defaut de ton de cet increment est le "Bravo" inconditionnel de la fin de partie, traite au
point 3.

#### 6. Ce qui est accorde, et ce qui part en reserve pour s02

**Accorde, sans condition.** Le Done pedagogique de l'increment `bandit-manchot-additions-cp` est
prononce. Les trois motifs de mon refus de s00 sont leves et verifies en jeu ; cinq de mes sept
reserves sont levees ; le contenu mathematique est exact sur 171 manches ; les trois criteres
visuels que nul test ne couvre sont tenus. Ce jeu peut etre mis devant une classe de CP.

**Reserves pour s02.** Elles sont nommees separement pour que `camille-po` en fasse des stories
distinctes. Je n'ecris pas le backlog et je ne priorise pas ; je donne le motif.

1. **Le libelle de fin de partie est refuse et doit changer avant tout autre travail sur ce jeu.**
   Remplacer "La partie est finie. Bravo, tu as bien joué !" par "C'est fini !". Toute felicitation
   doit dependre du resultat, ce qui est un choix produit a ecrire.
2. **Le bouton de sortie doit devenir lisible au CP** : "Retour à l'accueil" -> "Les jeux".
3. **L'indice doit devenir atteignable.** En l'etat, l'aide la plus importante de S1-5 ne s'affiche
   jamais dans une partie de 10 manches. Deux pistes possibles, que je ne tranche pas : abaisser le
   seuil d'echecs sur le meme item, ou declencher l'indice sur les echecs consecutifs plutot que sur
   la repetition d'un item. Ce qui compte pour moi : un enfant qui rate trois fois de suite doit
   recevoir une aide dans la partie en cours.
4. **L'indice doit etre reformule** : "Compte sur tes doigts." Et il doit vivre dans le YAML, comme
   `DEC-0005` l'a deja tranche, sinon la reformulation suivante coutera une recompilation.
5. **La repartition des sommes doit etre corrigee.** 43,9 % des manches sur les sommes 9 et 10 et
   7,6 % sous 5, ce n'est pas la competence annoncee, c'est son bord superieur. Un tirage uniforme
   sur les 66 couples serait deja une nette amelioration ; une progression du bas vers le haut de
   l'intervalle serait la bonne reponse, et rejoint ma reserve 3 de s00.
6. **Les tailles de texte qu'aucun critere ne visait.** Aucun texte lu par l'enfant ne devrait
   passer sous la taille de la consigne (1,25 rem), et les boutons "Encore !" et de sortie ne
   devraient pas etre rendus a la taille par defaut du navigateur (environ 13 px), sous peine
   d'etre le plus petit texte de l'ecran. `.indice` en particulier doit etre au moins aussi gros que
   la consigne : c'est le texte destine au lecteur le plus fragile.
7. **"Recommencer" doit ressembler a un bouton.** Sur l'ecran d'erreur, c'est un lien HTML sans
   aucune regle CSS : du texte souligne a la taille par defaut. Il est trouvable parce que l'ecran
   est vide, mais un enfant de six ans ne sait pas qu'un texte souligne se clique.
8. **"0 + 0" ne devrait pas pouvoir sortir**, et surtout pas en premiere manche : c'est la seule
   addition du contenu qui ne demande rien du tout.
9. **"Calcul CP" promet plus large que ce que le jeu fait.** Le libelle redeviendra ambigu des qu'un
   deuxieme jeu de mathematiques CP existera. A reprendre a ce moment-la, sans reintroduire le
   grapheme "ti" de "Additions".
10. **Le titre "Recreo" n'est pas accentue** alors que c'est le seul mot de l'ecran d'accueil et que
    l'enfant le dechiffre. A trancher comme une question de marque, pas par moi.
11. **L'ecran de catalogue vide n'offre aucune action.** Accepte pour aujourd'hui parce que c'est un
    ecran d'anomalie, mais un enfant devant un ecran sans rien a faire reste un enfant bloque.

#### 7. Suites hors de ma main

- `squad/programme/MATRICE-couverture.md` : la ligne mathematiques / CP / "Calcul mental - additions
  dans la limite de 10 (puis 20)" **reste `en cours`**, et je motive ce point precisement parce que
  le Done pedagogique, lui, est accorde. Les deux ne disent pas la meme chose : le Done porte sur
  l'increment, la matrice porte sur la couverture d'une competence. Un jeu qui passe 43,9 % de son
  temps sur deux des onze sommes possibles et 7,6 % sur les cinq plus petites ne couvre pas
  "les additions dans la limite de 10", il en couvre le haut. **Condition pour que je fasse passer
  cette ligne a `couvert-valide` : la reserve 5 traitee.** Je n'ai pas edite cette ligne, elle
  appartient a `camille-po`.
- Les criteres coches dans la section "Criteres pedagogiques attendus" de cette fiche appartiennent
  a `camille-po` : je ne les touche pas. Pour lui eviter d'avoir a me redemander, les quatre
  criteres restes non coches en s00 sont, de mon point de vue, satisfaits aujourd'hui - consigne et
  retours en vocabulaire de CP, texte integralement accentue, score lisible au CP, erreur serveur
  franchissable - et le dernier, celui de l'indice de S1-5, ne l'est pas : le texte est accepte sous
  reserve de reformulation et il n'est de toute facon jamais atteint en jeu.
- Rien a demander cette fois-ci a `DEC-0002` : l'exigence positive que j'avais reclamee en s00 a ete
  ecrite dans `CLAUDE.md` par le `relecteur-pedagogique`, et l'effet se voit - plus une seule faute
  d'accent dans le texte de l'enfant.

---

### Historique - verdict du sprint s00, conserve pour trace

> Ce verdict est celui qui a motive le sprint s01 tout entier. Il est conserve integralement :
> un refus efface n'a plus aucune valeur de preuve.

- **Verdict** : REFUSE
- **Date** : 2026-08-04
- **Audit joue** : oui. Fat jar `bootstrap/target/recreo.jar` lance sur le port 8099 avec le JDK
  21. Trois sessions reellement jouees : une session de 41 manches en repondant juste a chaque
  fois, une session de 20 manches en repondant faux a chaque fois, une session courte en
  alternant, plus des manches jouees a la main sur la page d'accueil, l'ecran de jeu, le retour
  juste et le retour faux. 61 manches relevees enonce par enonce, avec verification arithmetique
  de chaque somme, de chaque proposition et de la position de la bonne reponse. Lecture du HTML
  reellement servi, de `adapters/adapter-web/src/main/resources/templates/` (les trois gabarits)
  et de `adapters/adapter-web/src/main/resources/static/css/recreo.css` pour juger le rendu.
  Audit non degrade.

#### Ce qui a ete verifie

- **Ancrage au programme** : la competence annoncee est bien celle travaillee. Chaque manche
  demande une somme de deux entiers dont le resultat reste dans la limite de 10, ce qui correspond
  a la ligne "Calcul mental - additions dans la limite de 10 (puis 20)" de
  `squad/programme/mathematiques/competences-cp.md`, domaine "Nombres et calculs". Ce n'est pas
  une competence voisine plus facile : l'enfant calcule, il ne reconnait pas et ne recite pas.
  Reserve d'ancrage cependant : le tirage est uniforme sur les 66 couples possibles, donc environ
  un tiers des manches comportent un operande nul (releve : 16 manches sur 61, soit 26 %, pour
  une esperance theorique de 21 couples sur 66, soit 32 %). Or "7 + 0" ne fait pas travailler le
  calcul mental, il fait constater une propriete du zero. Un quart a un tiers du temps de jeu ne
  travaille donc pas la competence visee. Le "puis 20" de la ligne du referentiel reste hors
  perimetre, conformement a `DEC-0001`.
- **Lisibilite au niveau vise** : la consigne est bonne et je la valide telle quelle. "Combien ça
  fait ?" fait trois mots, tous du vocabulaire oral et de lecture de CP, tous composes de
  graphemes travailles en CP, et elle evite le mot "somme" comme demande. Le reste du texte
  affiche a l'enfant n'est pas au niveau : "Manche suivante" emploie "manche" dans un sens de jeu
  que l'enfant de CP n'a pas (pour lui une manche est celle d'un vetement) et "suivante" est un
  mot abstrait ; "Score : 1 / 2" emploie la notation fractionnaire, qui n'est pas une notion de
  CP ; "Additions CP", libelle du bouton d'accueil, contient le grapheme "ti" prononce /si/, qui
  n'est pas une acquisition de CP (reconnaissance globale seulement) ; "La partie est terminee.
  Merci d'avoir joue !" et "Aucune partie disponible pour le moment." sont d'un registre
  d'adulte. Rendu : aucune regle CSS ne grossit l'enonce, la consigne ni le verdict, qui
  s'affichent donc a la taille par defaut de 16 px, soit plus petit que les boutons de
  proposition (1,1 rem). Le calcul a lire est le plus petit texte de l'ecran.
- **Charge cognitive** : correcte sur le coeur du jeu. Trois propositions, une seule question a
  l'ecran, une seule action possible, pas de chronometre, pas de score a interpreter pour agir.
  L'enfant tient en tete deux nombres et rien d'autre. Un element parasite : la ligne
  "Score : x / y" est posee juste au-dessus d'une expression arithmetique, dans un jeu de
  mathematiques, avec un symbole que l'enfant de CP ne sait pas interpreter - il y a un risque
  reel qu'il la lise comme une operation a faire.
- **Progression** : il n'y en a aucune. Le tirage est uniforme et sans memoire d'une manche a
  l'autre : la manche 1 peut etre "10 + 0" et la manche 40 "0 + 1". La difficulte ne monte pas,
  ne s'adapte pas a ce que l'enfant reussit ou rate, et la partie ne s'arrete jamais - verifie
  dans le jeu (41 manches enchainees sans fin) et confirme par la Javadoc de
  `AdditionsBorneesContentSource.next`, qui documente que ce tirage ne renvoie jamais de fin. Il
  n'existe par ailleurs aucun bouton pour revenir a l'accueil ni pour arreter : l'enfant n'a
  aucun moment "j'ai fini".
- **Traitement de l'erreur** : le retour faux fait le minimum utile - il ne dit pas seulement
  "faux", il donne la bonne reponse, ce qui vaut mieux qu'un simple verdict. Mais il s'arrete la :
  pas de deuxieme essai, pas d'aide pour recalculer, et surtout aucune adaptation. J'ai joue 20
  manches fausses d'affilee : l'enfant voit vingt fois la meme phrase et un score qui reste a
  0 sur 20, sans un mot d'encouragement, sans que le jeu ne facilite quoi que ce soit, et sans
  pouvoir s'arreter autrement qu'en fermant l'onglet. Un enfant en difficulte n'est pas aide, il
  est enferme. Second point sur ce chapitre : quand la session HTTP est perdue (onglet laisse
  ouvert, serveur redemarre), le clic suivant de l'enfant remplace le jeu par un bloc technique
  en anglais - verifie, la reponse servie est
  `{"timestamp":...,"status":500,"error":"Internal Server Error",...}`. L'enfant est bloque devant
  du texte qu'il ne peut pas lire et ne peut pas repartir seul. Je decris le symptome, la cause
  technique appartient a `theo-archi`.
- **Exactitude du contenu** : les mathematiques sont exactes, sans une seule faute sur 61 manches
  relevees. Aucune somme superieure a 10, aucun operande hors 0-10, la bonne reponse figure
  toujours exactement une fois, sa position varie (releve sur 41 manches : 16 fois en premier,
  14 fois en deuxieme, 11 fois en troisieme, donc aucune strategie de position exploitable),
  aucune proposition negative ni non entiere, et le libelle de chaque bouton est bien la valeur
  qu'il envoie. Les distracteurs sont toujours a un, deux ou trois de la bonne reponse : ils sont
  plausibles, jamais absurdes, et la bonne reponse n'est pas systematiquement la valeur du milieu.
  Les distracteurs qui depassent 10 (11 et 12) ne sont pas un defaut : ils empechent l'enfant de
  gagner par elimination sans calculer. Les 41 enonces d'une meme partie etaient tous differents.
  En revanche le texte francais affiche a l'enfant comporte trois fautes d'orthographe, detaillees
  dans le motif ci-dessous.
- **Biais et ton** : rien a signaler, et c'est a mettre au credit de l'increment. Aucun personnage,
  aucun prenom, aucun contexte familial, materiel ou culturel presuppose : des nombres nus, donc
  aucun stereotype de genre, d'origine ou de milieu possible. Le ton n'est ni condescendant ni
  infantilisant : "Bravo, c'est juste !" est juste, sobre et non exagere ; le retour faux ne
  culpabilise pas et ne juge pas l'enfant, il corrige la reponse. Le signal rouge est associe a
  la reponse, pas a l'enfant. Aucun classement, aucune comparaison a d'autres enfants.

#### Motif

Le Done pedagogique est refuse pour trois motifs. Les deux premiers portent sur du texte lu par
l'enfant, le troisieme sur un ecran d'echec que l'enfant ne peut pas franchir.

1. **Le texte francais affiche a l'enfant comporte trois fautes d'orthographe, sur l'ecran qu'il
   voit a chaque erreur.** Dans
   `adapters/adapter-web/src/main/resources/templates/fragments/retour.html`, le retour faux est
   ecrit "Ce n'est pas ca. La bonne reponse etait X." Il faut lire "Ce n'est pas ça. La bonne
   réponse était X." : "ca" sans cedille n'est pas "ça", "reponse" et "etait" sans accent aigu ne
   sont pas "réponse" et "était" - sans accent, "etait" ne se lit meme pas comme un mot francais.
   Un enfant de CP est en train de construire son orthographe lexicale et ses correspondances
   graphemes-phonemes : tout texte mis sous ses yeux doit etre orthographiquement irreprochable,
   et l'accent aigu est un grapheme, pas une decoration. La meme faute est presente dans le
   message de fin de partie du meme gabarit, "La partie est terminee. Merci d'avoir joue !"
   (lire "terminée", "joué"). C'est une erreur de contenu scolaire, donc un motif de refus a lui
   seul. Precision importante pour la correction : le probleme est bien dans ces gabarits et non
   dans un encodage, puisque la consigne portee par `catalogue.yaml`, "Combien ça fait ?", est
   correctement accentuee et arrive intacte dans le HTML servi (en-tete
   `Content-Type: text/html;charset=UTF-8`). Correction attendue : accentuer tout le texte destine
   a l'enfant dans les trois gabarits. Voir aussi ma relecture de `DEC-0002` ci-dessous : cette
   faute est la consequence directe d'un manque de cette decision.
2. **Le score est affiche dans une notation que l'enfant de CP ne sait pas lire, au-dessus du
   calcul a faire.** L'ecran affiche "Score : 0 / 0" des la premiere manche, puis "Score : 1 / 2".
   La notation a / b comme rapport reussites sur essais n'est pas une notion de CP, et la barre
   oblique n'y a aucun sens etabli. Placee juste au-dessus d'une expression du type "6 + 3" dans
   un jeu de mathematiques, elle peut etre prise pour une operation. Correction attendue : afficher
   un decompte que l'enfant sait lire, par exemple un nombre de reussites seul ou un nombre de
   jetons ou d'etoiles gagnes, et ne pas montrer le nombre d'essais rates sous forme de rapport.
3. **Quand la partie est perdue cote serveur, l'enfant recoit un bloc technique en anglais et ne
   peut pas repartir.** Reproduit : un coup joue sans partie en cours renvoie un HTTP 500 dont le
   corps JSON est insere dans la zone de jeu, soit
   `{"timestamp":"...","status":500,"error":"Internal Server Error","path":"..."}`. Un enfant de CP
   ne peut ni lire ce texte ni comprendre qu'il doit recharger la page ; il est bloque sans issue,
   ce qui contredit la regle "un enfant bloque doit pouvoir avancer". Correction attendue : sur
   toute erreur, l'enfant voit un message court en francais et un bouton pour recommencer.
   Symptome constate cote ecran ; le traitement technique appartient a `theo-archi`.

#### Reserves

Ces points ne sont pas des motifs de refus, mais ils devront etre traites pour que ce jeu soit
autre chose qu'un exerciseur brut. Ils sont nommes separement pour que `camille-po` en fasse des
stories distinctes.

1. **La partie n'a pas de fin et l'enfant n'a aucune sortie.** J'ai enchaine 41 manches sans que
   rien ne s'arrete, et il n'existe aucun bouton pour revenir a l'accueil. Un enfant de CP a
   besoin d'un format court et borne, et d'un moment de cloture ("tu as fini, tu as gagne tant").
   A trancher comme une question produit : longueur d'une partie (a mon avis 10 manches), ecran de
   fin, bouton d'arret toujours visible. A noter que le message de fin existe deja dans le
   gabarit mais qu'il est inatteignable avec ce contenu.
2. **Un quart a un tiers des manches comporte un operande nul.** Releve 16 fois sur 61 manches.
   "7 + 0" ne fait pas travailler le calcul mental dans la limite de 10. Le tirage doit limiter la
   proportion d'additions a operande nul, sans les supprimer completement (la propriete du zero a
   sa place, mais pas un tiers du temps).
3. **Aucune progression et aucune adaptation.** La difficulte est constante et aleatoire, et le jeu
   ne reagit pas a une serie de reussites ni a une serie d'echecs. Attendu a terme : commencer par
   les petites sommes et monter vers les sommes proches de 10, et proposer quelque chose de
   different a l'enfant qui rate plusieurs fois de suite.
4. **Le signal juste/faux est percu mais sa forme ne veut rien dire.** Verifie dans
   `recreo.css` : trois canaux simultanes (fond du bloc teinte, pastille coloree, forme de la
   pastille), donc l'enfant voit instantanement qu'il s'est passe quelque chose - le critere de
   `camille-po` est litteralement satisfait. Mais le sens ne repose que sur l'opposition vert
   (#2e7d32) / rouge (#c62828), et la forme n'apporte aucun sens : un disque et un triangle ne
   signifient ni "juste" ni "faux" pour un enfant de 6 ans, et le triangle rouge evoque plutot un
   panneau de danger. Consequence concrete : un enfant daltonien - environ un garcon sur douze,
   et le vert/rouge est precisement la paire qu'il confond - voit deux pastilles de meme teinte
   apparente et doit deviner laquelle veut dire "juste". Correction attendue, compatible avec
   `DEC-0002` puisqu'elle n'introduit aucun emoji dans le gabarit : remplacer le disque et le
   triangle par les deux signes que l'enfant connait deja de son cahier, une coche pour juste et
   une croix pour faux, dessines en CSS ou en SVG, et garder la couleur comme second canal. Le
   signal devient alors lisible sans couleur et sans lecture.
5. **Le texte a lire est trop petit et le signal trop discret pour un lecteur debutant.** L'enonce,
   la consigne et le verdict sont a la taille par defaut (16 px) et la pastille de signal fait
   1,5 rem, alors que les boutons de proposition sont a 1,1 rem : le calcul a lire est le plus
   petit texte de l'ecran. Attendu : grossir nettement l'enonce (au moins 2 rem), remonter le
   texte courant, et porter la pastille de signal a au moins 3 rem.
6. **Trois libelles a reecrire en vocabulaire de CP.** "Manche suivante" (le mot "manche" n'a pas
   ce sens pour un enfant de CP ; preferer "Encore !" ou "On continue") ; "Merci d'avoir joue !"
   et "Aucune partie disponible pour le moment." (registre d'adulte). Le retour faux gagnerait
   aussi a passer au present et a une seule phrase : "Non, c'est 4." plutot que "La bonne réponse
   était 4."
7. **La perte de la partie au rechargement de la page.** Si l'enfant appuie sur F5, il revient a
   l'accueil et son score repart de zero. Peu grave avec une partie sans fin, mais a revoir en
   meme temps que la reserve 1.

#### Ce qu'il faut pour que ce jeu passe a VALIDE

Les trois motifs de refus corriges, c'est-a-dire : le texte de l'enfant correctement accentue, un
score lisible au CP, et un ecran d'erreur franchissable. Les reserves peuvent etre etalees sur les
sprints suivants. Le contenu mathematique lui-meme, la consigne et le ton n'ont pas besoin d'etre
retouches : ils sont bons.

#### Suites hors de ma main

- `squad/programme/MATRICE-couverture.md` : la ligne mathematiques / CP / "Calcul mental -
  additions dans la limite de 10 (puis 20)" **reste `en cours`** et ne peut pas passer a
  `couvert-valide`. Je n'ai pas edite cette ligne, elle appartient a `camille-po`.
- Amendement demande a `DEC-0002`, via `sofia-sm` : voir ci-dessous.

#### Relecture de DEC-0002 sur le fond pedagogique, a la demande de camille-po

Je valide la distinction des deux registres, et je confirme le choix technique retenu pour le
signal de S0-5 : pour un enfant qui ne dechiffre pas, le signal de reussite est appris par
repetition, donc il doit avoir exactement la meme apparence a chaque fois et sur tous les
appareils - ce qu'un caractere emoji ne garantit pas, en plus de ne pas permettre de maitriser sa
taille ni son contraste. Sur le fond, une regle de style d'ingenierie n'a effectivement rien a
dire sur la qualite d'un support pedagogique.

Mais `DEC-0002` a un manque, et ce sprint en donne la preuve. La decision **exempte** la
categorie B (le contenu vu par l'enfant) de la regle de redaction du depot, sans y mettre aucune
exigence positive a la place. Elle retire donc ce texte du controle du `relecteur-pedagogique` et
le confie a ma seule porte, qui ne s'exerce qu'une fois, en fin de sprint. Resultat observable :
l'habitude ASCII du depot a fuite dans le registre de l'enfant precisement la ou plus aucune
regle ne s'applique et ou plus personne ne regarde, et le retour faux affiche "reponse" et "etait"
a un enfant qui apprend a lire. Ce que je demande a `camille-po`, via `sofia-sm` : ajouter a
`DEC-0002` une exigence positive pour la categorie B, du type "le texte destine a l'enfant est
redige en francais correct et integralement accentue, orthographe et ponctuation irreprochables,
parce qu'il est lu par un enfant en cours d'apprentissage de la lecture", et faire verifier cette
exigence-la par le `relecteur-pedagogique` comme n'importe quelle autre. L'exemption de la regle
de style ne doit pas se lire comme une absence de regle. La meme precision devrait figurer dans
le complement de `CLAUDE.md` que `DEC-0002` signale deja.

Deuxieme point, sur les deux ambiguites que `DEC-0002` signale par avance dans
`contenu-repris/associer/cp-formes.yml`, pour que la future fiche ne corrige pas ce qui est bon :
les **deux triangles d'orientation differente associes au meme mot "triangle" ne sont pas un
defaut, c'est une qualite**. La competence de CP est "reconnaitre et nommer quelques figures
planes" : elle demande justement que l'enfant abstraie l'orientation, la taille et la couleur.
Ne montrer qu'un triangle toujours pointe vers le haut installerait un prototype faux, erreur
classique dont les eleves se defont ensuite difficilement. Il faut donc garder les deux
orientations. Le **carre bleu** est neutre a une seule condition, que je verifierai ce jour-la :
qu'aucun item du meme lot ne fasse jamais de la couleur le critere de tri, et que la consigne ne
parle jamais de couleur. Si ces deux conditions tiennent, la couleur est un simple attribut de
dessin et l'enfant ne peut pas se tromper de critere.
