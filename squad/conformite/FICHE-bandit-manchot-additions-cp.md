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
  2026-08-07, rejoues.
- **Derniere mise a jour** : 2026-08-07

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
