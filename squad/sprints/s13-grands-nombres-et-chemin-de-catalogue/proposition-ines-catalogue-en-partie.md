# Proposition ines-design - la forme de l'ecran de S13-6, deux variantes chiffrees

- **Auteur** : ines-design
- **Date** : 2026-08-15
- **Branche** : `round-two-ines-catalogue`, worktree dedie `s13-ines-catalogue`, cree depuis la tete
  de `round-two` a `c8766cd` (`git merge-base --is-ancestor` confirme).
- **Ne modifie aucun fichier de production.** Ce document propose une forme pour S13-6 ; il ne
  tranche ni le contrat du controleur, ni le texte de l'ecran (`nadia-prof`), ni le point ouvert
  d'`ADR-0042` sur la presence du jeu en cours dans la liste (`camille-po`/`nadia-prof`).
- **Contrainte recue avant de dessiner** : `ADR-0042` (theo-archi, 2026-08-15) - un point de focus
  sans requete, place avant le retour dans l'ordre du document ; le retour en premier arret de
  tabulation ; les boutons de demarrage apres lui. Aucune autre famille n'est possible.

## 0. Une mesure corrige un chiffre du brief avant tout le reste

Le brief et `ADR-0042` citent tous deux "sept jeux" ou "sept dans le catalogue reel". Mesure sur
l'arbre fusionne d'aujourd'hui (`c8766cd`), JDK 21.0.10, jar reconstruit par
`mvn -B clean verify` puis lance (`java -jar bootstrap/target/recreo.jar`), `GET /` hors partie :

**`catalogue.yaml` sert onze jeux, pas sept** (compte des boutons `class="btn demarrer"` dans le
document rendu : 11). Mesure a 360 x 640 px (`outils/mesurer-rendu.sh 360 http://.../ <png> 640`,
puis lecture colonne par colonne du PNG, x = 30, transitions de couleur) : le bandeau d'en-tete
occupe y = 0 a 84 (85 px), le premier bouton "Calcul CP" commence a y = 135, chaque bouton occupe
un pas vertical constant de **68 px** (mesure sur les onze intervalles, ecart-type nul). A cette
cadence, **exactement sept boutons tiennent entierement au-dessus du pli** (le septieme, "Les
compléments à 10", finit a y = 594 ; le huitieme, "Les tables de multiplication", commence a
y = 611 et deborde a y = 663) - **les quatre derniers jeux du catalogue (tables de multiplication,
perimetre, mesures, grands nombres) demandent deja un defilement aujourd'hui, avant tout
changement.**

**Ce que je conclus, sans trancher pour un autre agent** : "sept" decrit ce qui tient au-dessus du
pli sur l'ecran hors-partie d'aujourd'hui, pas le nombre total de jeux du catalogue reel (onze).
Je le signale parce que ma propre contrainte de budget vertical en depend directement - je chiffre
donc mes deux variantes sur **onze jeux**, le vrai contenu servi, et je donne le chiffre pour sept
en aparte quand il eclaire un ecart. Je route la reconciliation du chiffre lui-meme a `sofia-sm`/
`theo-archi` : ce n'est pas a moi de decider ce que "catalogue reel" designe dans leur redaction.

## 1. Ce qui est fixe dans les deux variantes

- Fragment racine `class="ecran-de-jeu"` **conserve** : c'est ce marqueur, deja pose, qui masque le
  lien "Les jeux" de l'en-tete (`body:has(.ecran-de-jeu) .lien-accueil { display:none }`). Sans
  lui, le lien d'en-tete redeviendrait visible et focalisable sur cet ecran - un second chemin vers
  `/retour` destructeur, sans confirmation, exactement le risque que S13-6 doit fermer. Zero regle
  CSS neuve requise pour cela : la regle existe deja et s'applique a tout element qui porte cette
  classe.
- **Point de focus** : un `<h2 data-focus-cible="true">Les jeux</h2>`, `data-focus="titre"` sur la
  racine du fragment (valeur libre, seule "enonce" est speciale pour `recreo.js` - verifie dans
  `adapters/adapter-web/src/main/resources/static/js/recreo.js` ligne 6). Ce titre n'est pas un
  texte de remplissage pose pour satisfaire la contrainte : c'est l'orientation ("tu es sur l'ecran
  des jeux") qu'un enfant qui vient d'un ecran de manche a besoin de lire.
- **Retour** : reutilise `<button class="btn continuer-partie" data-role="action">Continuer la
  partie</button>`, **le meme libelle et la meme classe** que le bouton de retour de
  `fragments/confirmation.html`. Zero CSS neuve pour le bouton lui-meme (police, couleur,
  rembourrage, cible tactile deja fixes). **Question pour `nadia-prof`** ci-dessous : ce texte a
  ete valide dans le contexte de la confirmation, pas dans celui-ci.
- **Liste des jeux** : reutilise integralement `<ul class="jeux list-unstyled"><li><button
  class="btn demarrer" data-role="action">...</button></li></ul>`, le meme balisage
  qu'`accueil.html` aujourd'hui. Zero CSS neuve pour les boutons de demarrage.
- **La requete du retour doit laisser la session intacte** (ni destructrice ni remplacante au sens
  d'`ADR-0042`) - c'est une exigence que je pose au controleur, pas une ligne que j'ecris ; le
  mecanisme le plus proche deja dans le depot est `/retour/annuler`, qui rend la meme manche sans
  y toucher.

## 2. Variante A - le retour en bandeau, en tete, ordre visuel = ordre du document

### Forme

```
[en-tete]
Les jeux                          <- point de focus, sans requete
[Continuer la partie]             <- bandeau pleine largeur, coins non-pilule
Choisis un autre jeu
(Calcul CP)  (Le bon verbe)  ...   <- liste actuelle, pilules, inchangee
```

### Mesure, a 360 x 640 px

Mockup statique servi par le meme `recreo.css`/`bootstrap.min.css` que l'application (charges
depuis le jar reconstruit), aucune ligne de production modifiee.
`outils/mesurer-rendu.sh 360 file://<mockup-variante-a> <png> 640`, lecture colonne par colonne
(x = 15 et x = 340) :

| Bloc | y de debut | y de fin | Hauteur |
|---|---|---|---|
| En-tete | 0 | 84 | 85 px (mesure sur l'application reelle ; le mockup local rend l'icone 9 px plus bas a cause du chargement de favicon.ico en inter-origine - ecart note, non corrige, sans effet sur les hauteurs relatives ci-dessous) |
| "Les jeux" (titre, point de focus) | 124 | 140 | - |
| "Continuer la partie" (bandeau) | 158 | 213 | 55 px |
| "Choisis un autre jeu" (sous-titre) | 248 | 260 | - |
| Premier bouton de demarrage | 299 | - | pas de 68 px, identique a l'existant |

**Au-dessus du pli (640 px) : cinq jeux entiers** (Calcul CP, Le bon verbe, Le bon son, Les petites
soustractions, Petits problèmes). Le sixieme ("Compare les nombres") deborde du pli. **Six jeux
restent sous le pli, dont le sixieme partiellement.**

### Cout

**-2 jeux entierement visibles par rapport a l'ecran hors-partie d'aujourd'hui (7 -> 5)**, du fait
des ~155 px que le titre, le bandeau de retour et le sous-titre ajoutent avant la liste. Ce n'est
pas un cout nouveau au sens ou le defilement existe deja aujourd'hui pour les jeux au-dela du
septieme (`ADR-0028`/`DEC-0025` : le contenu sous le pli reste non gardable, jamais garanti par la
CI) - c'est un deplacement de la frontiere de defilement, chiffre et non suppose.

### Ce qui distingue le retour des sept - onze - boutons de demarrage (point 4 du brief)

Trois canaux redondants, aucun ne repose sur la couleur seule (les deux familles restent bleu
accent, coherent avec le reste de l'application ou le bleu dit "faire avancer, rester dans le
jeu") :

1. **Forme** : `.continuer-partie` garde les coins de `.btn` (Bootstrap, rectangle arrondi) ;
   `.demarrer` est une pilule (`border-radius: 999px`, regle deja existante scopee a `.demarrer,
   .encore`). Les deux formes ne se sont jamais ressemblees, sans qu'aucune ligne de CSS n'ait
   besoin de changer.
2. **Largeur** : le bandeau de retour s'etend sur toute la largeur du conteneur (flex-column par
   defaut stretch) ; chaque bouton de demarrage garde sa largeur de contenu, compacte, alignee a
   gauche - visible sur la capture, jamais confondable au premier regard.
3. **Position et isolement** : le retour est seul, en tete, separe de la liste par le sous-titre
   "Choisis un autre jeu" - jamais adjacent a un bouton de demarrage de la meme apparence, ce qui
   est exactement la faille qui avait coute 4 px sur l'ecran de confirmation (deux boutons de meme
   forme, cote a cote).

## 3. Variante B - le retour visuellement en dernier, la liste en tete

### Forme

```
[en-tete]
Les jeux                          <- point de focus, sans requete
(Calcul CP)  (Le bon verbe)  ...   <- liste, remontee visuellement
[Continuer la partie]             <- retour, premier dans le document, dernier a l'ecran
```

L'ordre du DOCUMENT reste retour avant liste (conformite `ADR-0042` inchangee) ; seul l'ordre
VISUEL change, par `order` CSS sur un parent `display:flex; flex-direction:column`
(`.catalogue-en-partie > .jeux { order: 1 }`, `.reprise-catalogue { order: 2 }`).

### Mesure, a 360 x 640 px

Meme methode, meme mockup, ordre `<div reprise>` avant `<ul class="jeux">` dans le HTML source,
`order` CSS pour l'affichage :

- Liste : premier bouton a y = 179 (mockup), pas de 68 px inchange -> **sept jeux entierement
  visibles au-dessus du pli**, exactement comme l'ecran hors-partie d'aujourd'hui.
- Bandeau de retour : deplace en bas du document assemble, mesure a **y = 941-996** sur la capture
  pleine page (`outils/mesurer-rendu.sh 360 file://<mockup-variante-b> <png>`, sans hauteur, page
  entiere) - **301 px sous le pli**. Un enfant ne voit le bouton de retour qu'apres avoir defile
  au-dela des onze jeux.

### Cout

1. **Le retour, l'unique chemin sans risque pour rejoindre sa partie, est invisible au premier
   affichage.** C'est le cout le plus serieux : la variante gagne deux jeux visibles (5 -> 7) au
   prix de rendre invisible, sans defiler, la seule action que cet ecran devait rendre trouvable.
2. **`order` CSS decouple l'ordre visuel de l'ordre de tabulation reel.** Aucun garde-fou du depot
   ne verifie qu'un navigateur reel respecte l'ordre du document plutot que l'ordre visuel pour la
   navigation sequentielle au clavier - c'est une non-preuve nommee, du meme type que celles
   qu'`ADR-0038` et `ADR-0042` genent deja ("l'ordre de tabulation calcule est celui d'un vrai
   navigateur"). Un enfant qui voit la liste en premier et appuie sur Tabulation depuis le titre
   focalise atterrit sur un bouton "Continuer la partie" invisible a l'ecran (en bas de page),
   avant tout bouton de demarrage visible - un decalage reel entre ce que l'oeil suit et ce que le
   clavier fait, jamais mesure au clavier sur un vrai navigateur (meme limite que le point 7 des
   non-preuves d'`ADR-0042`).

## 4. Recommandation

**Variante A.** Le cout de la variante B (le retour invisible sans defiler, plus un decouplage
visuel/clavier non garde) porte sur exactement ce que S13-6 existe pour resoudre - un chemin
*trouvable* vers la partie en cours. Gagner deux jeux visibles en echange n'est pas un echange que
je recommande : les jeux au-dela du pli restent a une portee de defilement dans les deux variantes,
alors que rendre le retour invisible ne l'est pas de la meme facon (il n'y a aucun signal a l'ecran
qu'il existe plus bas). La variante A garde le retour visible, immediatement identifiable, sans
recours a une propriete CSS qui n'a jamais ete mise a l'epreuve d'un vrai clavier dans ce depot.

**Ajustement a cout nul que je signale sans le compter comme une troisieme variante** : le
sous-titre "Choisis un autre jeu" de la variante A peut etre retire si `nadia-prof` juge la
separation visuelle (bandeau plein/pilules, largeur, isolement) suffisante sans lui - cela
recupere une partie des ~155 px de cout et ferait probablement remonter le nombre de jeux
entierement visibles a six. Je ne le mesure pas plus finement ici : c'est une question de texte,
donc a elle de la trancher avant que je remesure.

## 5. Ce que ces variantes coutent a un garde-fou existant, verifie et non suppose

- **`body:has(.ecran-de-jeu) .lien-accueil { display:none }`** (`LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest`
  probablement, verifie sur le selecteur dans `recreo.css`) : **cout nul** dans les deux variantes,
  a condition que le lot qui cable l'ecran pose bien `class="ecran-de-jeu"` sur la racine du
  fragment - une obligation que je nomme pour le developpeur, pas une ligne que j'ecris.
- **`AucunArretDeTabulationNEstDestructeurTest`, clauses 1 a 3 augmentees du predicat "remplacant"**
  (`ADR-0042`) : les deux variantes satisfont l'unique famille que l'ADR laisse ouverte (focus sans
  requete avant le retour, retour premier arret). **Cout reel, deja nomme par `ADR-0042` lui-meme,
  pas par moi** : le corpus de ce garde-fou devra porter un ecran a partie en cours ou au moins une
  manche est resolue, sans quoi le trou de la non-preuve 2 (redemarrage du jeu en cours a
  progression nulle, non vu par le predicat) rend la clause verte sans avoir ete mise a l'epreuve.
  Cela ne depend d'aucune des deux variantes que je propose ici, uniquement du contenu du corpus de
  test que le developpeur ecrira.
- **Aucun garde-fou de taille de police ou de cible tactile n'est mis en cause** : les trois classes
  nouvelles que je propose (`.titre-catalogue`, `.reprise-catalogue`, `.sous-titre-catalogue` -
  variante A seulement pour la derniere) ne touchent a aucun selecteur existant, et je ne reutilise
  que des classes de boutons deja conformes (`.continuer-partie`, `.demarrer`) sans leur ajouter ni
  leur retirer de regle. `lesSeulesTaillesDePropositionSontLesDeuxReglesNommees` reste scope a
  `.proposition`, que je ne touche pas.
- **Aucune nouvelle paire de couleurs a faire verifier** : `.titre-catalogue` reprend
  `var(--recreo-accent)` sur `var(--recreo-fond)`, la meme paire deja utilisee par `.score` ;
  `.sous-titre-catalogue` reprend `var(--recreo-texte)` sur le meme fond, la meme paire que
  `.pas-de-jeu` et `.message-reprise`. Je ne propose aucune teinte neuve. Rappel du standing
  caveat : ma methode de calcul de contraste a deja diverge de celle de `nadia-prof` sur une paire
  differente, cause etablie de son cote (une paire de couleurs fausse dans ma feuille de calcul,
  pas une difference de linearisation) - sans objet ici puisque je ne calcule aucune valeur
  absolue neuve, je ne fais que reutiliser des paires deja jugees.

## 6. Ce qui ne tient pas sous le pli, dans les deux variantes

Onze jeux ne tiennent jamais entierement au-dessus de 640 px une fois le retour ajoute, quelle que
soit la variante - **ce n'est pas un echec de forme, c'est une contrainte de contenu** : le
catalogue avait deja depasse ce budget avant que S13-6 n'existe (quatre jeux sous le pli sur
l'ecran hors-partie d'aujourd'hui). Le defilement est la reponse deja acceptee par ce depot
(`ADR-0028`, `DEC-0025`) pour ce cas, pas quelque chose que j'introduis.

## 7. Questions pour `nadia-prof`

1. **"Continuer la partie" est le texte deja valide pour ce geste exact (revenir a la manche en
   cours sans la modifier), mais dans le contexte de l'ecran de confirmation.** Le reprendre ici,
   dans un contexte different (un catalogue affiche pendant la partie, pas une question de sortie),
   est-il acceptable tel quel, ou ce nouveau contexte demande-t-il un texte different ? C'est la
   meme regle qu'elle a deja posee elle-meme : "un texte valide dans un contexte ne l'est pas
   automatiquement dans un autre."
2. **Le titre "Les jeux" en tete d'ecran, et le sous-titre "Choisis un autre jeu" (variante A
   seulement) : ces deux textes sont-ils lisibles et non ambigus pour un CP ?** Je n'ai invente que
   leur taille et leur position, jamais leur formulation finale.
3. **La distinction retour/demarrage (forme, largeur, isolement, section 2 ci-dessus) est-elle
   suffisante pour qu'un enfant ne demarre pas un autre jeu en croyant reprendre le sien ?** C'est
   la question la plus proche de son mandat dans ce document : je propose la forme, elle seule
   juge si elle protege l'enfant.
4. **Point ouvert deja nomme par `ADR-0042`, pas par moi, mais que ma mesure rend concret** : si le
   jeu en cours reste dans la liste des onze boutons de demarrage, un enfant peut appuyer dessus et
   redemarrer sa propre partie - un cas que le garde-fou ne detectera pas tant qu'aucune manche
   n'est resolue (non-preuve 2 d'`ADR-0042`). Faut-il l'exclure de la liste, le neutraliser
   autrement, ou l'assumer ? Question de contenu et de pedagogie, pas de forme.
5. **La valeur absolue de tout contraste que je cite reste a reverifier par elle** - standing
   caveat, sans objet nouveau ici puisque aucune teinte n'est neuve (section 5).

## 8. Methode - commandes citees

- Reconstruction : `mvn -B clean verify` (JDK 21.0.10 pointe via `JAVA_HOME`, `java` du `PATH` en
  JRE 8 sur ce poste - voir `outils/verifier-jdk.sh`). 483 tests, 28 regles ArchUnit, inchange par
  ce document (aucun fichier de production touche).
- Lancement : `java -jar bootstrap/target/recreo.jar --server.port=8099`, arrete par sa PID de JVM
  reelle (verifiee par la liste des processus, jamais par nom d'image) une fois les mesures prises.
- Mesures : `outils/mesurer-rendu.sh 360 <url> <png> [640]`, largeur et pli conformes a `ADR-0025`/
  `ADR-0028`. Les deux mockups de variantes chargent `recreo.css` et `bootstrap.min.css` depuis le
  jar reconstruit, servis par l'application reelle en cours d'execution - aucune feuille de style
  n'a ete dupliquee ni modifiee pour la mesure.
- Lecture pixel par pixel des captures (transitions de couleur colonne par colonne) pour situer les
  bordures de bloc avec exactitude plutot qu'a l'oeil - technique volontairement simple, pas de
  script ajoute au depot : aucun outil de mesure de rectangle n'existe aujourd'hui dans `outils/`
  au-dela de la capture d'ecran elle-meme.

## 9. Tete de branche

`round-two-ines-catalogue` a ce commit, worktree `s13-ines-catalogue`, descendant direct verifie de
la tete de `round-two` (`c8766cd`) sans aucun autre commit intercale.
