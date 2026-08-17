# Fiche de conformite - barre d'entree par niveau (en-tete)

- **Increment** : deux boutons d'entree par niveau (CP / CM1) dans l'en-tete de recreo, toujours
  visibles y compris sur un ecran de jeu, story `S14-1` (`docs/backlog/backlog.md`, section
  "Sprint 14").
- **Statut global** : **arbitrage prealable RENDU le 2026-08-17 par `nadia-prof`** - la story peut
  etre affectee au planning. **Variante 1 d'`ines-design` (bandeau empile) RETENUE, variante 2
  (defilement horizontal) REFUSEE**, sous **neuf conditions bloquantes BARRE-1 a BARRE-9** et avec
  **cinq reserves non bloquantes R-BARRE-1 a R-BARRE-5**, toutes au bloc de validation. Ce n'est pas
  un Done pedagogique : celui de S14-1 sera prononce en jouant l'increment livre, et il pourra etre
  refuse pour ce que seul le rendu montre, meme si les neuf conditions sont tenues
  (`DEC-0032-perimetre-sprint-14-barre-entree-niveau-arbitrage-prealable-matiere-differee.md`,
  section 1).
- **Ce que cette story rouvre, et pourquoi cette fiche existe avant tout code** : elle ajoute deux
  elements visibles et actionnables dans l'en-tete pendant un ecran de jeu, la ou l'arbitrage de
  S6-9 / `ADR-0022` (2026-08-07) n'en laisse survivre qu'un seul, masque justement pour eviter
  qu'un enfant hesite entre plusieurs elements qui ressemblent a une sortie. La demande vient de
  l'utilisatrice, qui l'assume en connaissance de cet arbitrage anterieur - ce n'est pas une
  reouverture que `camille-po` propose de son propre chef, voir `DEC-0032` pour le detail.
- **Releves de mesure** : `squad/conformite/releves-s14/`, sept captures a 360 px et au pli de 640 px,
  produites par `outils/mesurer-rendu.sh 360 <url> <png> 640` sur l'application reelle.
- **Derniere mise a jour** : 2026-08-17 (arbitrage prealable rendu par `nadia-prof` ; ouverture par
  `camille-po` le meme jour, bloc de validation alors vide).

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
