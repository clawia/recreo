# Proposition ines-design - S6-8 et S6-10

- **Auteure** : ines-design
- **Date** : 2026-08-07
- **Pieces lues** : `docs/backlog/backlog.md` (S6-8, S6-9, S6-10) ; `squad/conformite/FICHE-bandit-manchot-additions-cp.md`
  (verdict du 2026-08-07, section A.2, points 1 et 4, et A.1 pour les mesures deja acquises) ;
  `ADR-0014`, `ADR-0017` (et ses deux amendements du 2026-08-07), `ADR-0022` ;
  `adapters/adapter-web/src/main/resources/static/css/recreo.css` et les quatre gabarits.
- **Mandat** : je propose des valeurs et une forme. Je ne tranche rien. `nadia-prof` garde seule
  le veto pedagogique. `lea-dev` met en oeuvre.
- **Ce que je ne touche pas** : `.btn.retour-accueil` et `.btn.sortie-pleine` restent tels quels
  (gel de l'arbitrage S6-9) - aucune de mes deux propositions ne modifie leur classe, leur texte
  ni leur taille.

## Methode de verification, commune aux deux stories

J'ai provisionne un worktree dedie hors de l'arbre du depot, verifie sa lignee
(`git merge-base --is-ancestor round-two HEAD`), puis construit et lance le fat jar reel
(`java -jar bootstrap/target/recreo.jar`) sur un JDK 21 designe par `JAVA_HOME` le temps de la
commande. Je n'ai pas de navigateur sans tete a ma disposition (pas de Playwright/Selenium
installe dans mon environnement) : je n'ai donc pas mesure de `getBoundingClientRect` comme
`nadia-prof` le fait. Ce que j'ai fait a la place, et qui reste une verification sur l'artefact
reellement servi et non sur une lecture de source seule :

1. construit et lance le jar, puis recupere par HTTP le HTML et le CSS reellement servis
   (`/`, `/css/recreo.css`, `/vendor/bootstrap.min.css`, une manche jouee et son retour) ;
2. verifie sur ces octets servis, et non sur ma seule intention, la regle CSS qui gouverne
   chaque propriete en cause, y compris la regle de `bootstrap.min.css` que ma feuille maison
   doit laisser passer ou completer (`ADR-0014` point 5 : la feuille maison charge en dernier) ;
3. pour S6-8, confirme qu'aucune `@media` de `bootstrap.min.css` ni de `recreo.css` ne fait
   varier la propriete en cause selon la largeur - **recreo.css ne declare aucune `@media`** et
   la regle `.container` de Bootstrap qui porte le rembourrage lateral (`padding-right`/
   `padding-left: calc(var(--bs-gutter-x) * .5)`, `--bs-gutter-x: 1.5rem`) n'est reprise dans
   aucune de ses propres media queries. La valeur est donc la meme a 360 px qu'a 1280 px : verifier
   une largeur suffit a verifier toutes les largeurs pour cette propriete precise, et je le dis
   explicitement plutot que de laisser croire a une mesure repetee sur plusieurs largeurs ;
4. j'ai ecrit du CSS exploratoire dans mon worktree pour verifier chaque proposition (les deux
   changements decrits plus bas), rejoue une partie complete dessus, verifie que les 114 tests
   d'`adapter-web` restent verts (`mvn -B test`, JDK 21, `Tests run: 114, Failures: 0, Errors: 0`),
   puis **annule ce CSS avant de committer** : seul ce fichier de proposition est commite dans mon
   worktree, le diff de mise en oeuvre appartient a `lea-dev`.

**Ce que cette methode ne prouve pas** : le rendu pixel exact que verra un enfant (anti-crenelage,
rendu de police, zoom du navigateur). C'est le rendu de la propriete CSS elle-meme que je verifie,
pas une capture d'ecran. `nadia-prof` reste seule a mesurer en pixels sur un rendu reel, et elle
l'annonce elle-meme pour S6-8 ("je remesurerai a 360 px").

---

## S6-8 - la marge laterale qui disparait sous 1200 px

### Diagnostic verifie, pas suppose

`<main id="jeu" class="container zone-jeu">` porte deux classes sur le meme element. `.container`
(Bootstrap, chargee en premier) declare `padding-right`/`padding-left: calc(1.5rem * .5)` soit
**0,75rem (12 px)**, sans aucune condition de largeur - verifie en lisant `bootstrap.min.css` tel
que servi par le jar, aucune `@media` ne redeclare cette propriete pour `.container`. `.zone-jeu`
(maison, chargee apres, meme specificite de classe unique -> elle gagne, `ADR-0014` point 5)
declare aujourd'hui `padding: 2rem 0`. Ce raccourci fixe **aussi** `padding-left` et
`padding-right` a `0`, et c'est cette valeur qui l'emporte : le rembourrage lateral de `.container`
est ecrase, a toute largeur d'ecran, pas seulement sous 1200 px (1200 px est le seuil auquel
`.container` cesse de se centrer avec une largeur maximale ; ce n'est pas un seuil qui concerne son
rembourrage, qui est inconditionnel).

### Valeur proposee

| Selecteur | Avant | Propose |
|---|---|---|
| `.zone-jeu` | `padding: 2rem 0;` | `padding-top: 2rem; padding-bottom: 2rem;` |

Je ne redeclare pas de valeur laterale : en ne parlant plus du tout de `padding-left`/
`padding-right`, `.zone-jeu` laisse la declaration de `.container` s'appliquer telle quelle,
au lieu de la recouvrir. Je n'ai pas retenu l'alternative "`padding: 2rem 0.75rem`" (dupliquer la
valeur de Bootstrap dans ma propre regle) : elle marche aussi, mais elle fige dans `recreo.css` un
nombre qui appartient a la feuille tierce et qui devrait continuer a en venir (`ADR-0014` point 6
distingue deja "porter une garantie" de "porter le rendu de base" - ici il n'y a pas de garantie
pedagogique en jeu, juste le rendu de base, donc autant le laisser a Bootstrap).

### Ce que j'ai mesure, et a quelle largeur

Sur le jar reconstruit avec ce changement : `padding-left` et `padding-right` calcules sur
`main#jeu` valent **0,75rem, soit 12 px**, identiques a ceux de `.entete-contenu` (le bandeau, qui
n'a jamais eu ce defaut). Verifie sur la declaration servie, a une largeur de fenetre nommee de
**360 px** conformement au critere du backlog - et, comme explique plus haut, cette valeur ne varie
avec aucune largeur puisqu'aucune media query ne la conditionne, donc la meme lecture vaut a
1280 px. Le plancher demande (12 px, au moins celui de l'en-tete) est atteint, pas seulement
approche.

### Garde-fous verifies

`HabillageDesEcransTest.aucuneClassePoseeSurUnElementNEstOrpheline`, `ViewportEtLargeurTelephoneTest`
et `TailleDesTextesEtBoutonsTest` : verts sur mon CSS exploratoire (je n'ajoute aucune classe, je ne
touche aucun bouton). Aucune classe neuve, donc rien a ajouter au balisage.

### Ce que je laisse au jugement de `nadia-prof`

Que 12 px suffisent reellement au confort de lecture qu'elle demande, ou qu'elle prefere davantage
(par exemple aligner sur `1rem`/16 px plutot que sur le plancher exact de l'en-tete). Je n'ai
propose que le montant qui leve le defaut chiffre et rejoint le plancher qu'elle a elle-meme nomme
("au moins ce meme plancher de 12 px") ; si son gout en jouant demande plus, la meme methode
(ne rien ecraser, ou ecraser volontairement avec une valeur assumee) s'applique a un autre montant.

---

## S6-10 - le bouton d'action reste seul de sa couleur

### Choix retenu entre les deux options nommees par `nadia-prof`

Je retiens **la premiere option** : le bandeau prend une nuance distincte de la couleur d'action.
Je ne retiens pas "l'action grandit sur l'ecran de retour", pour une raison de coherence avec le
premier critere de la story tel qu'il est ecrit au backlog : ce critere se lit sur une **inegalite
de couleur** ("elles ne sont plus identiques"), pas sur une taille. Faire grandir "Encore !" sans
changer sa couleur laisserait cette premiere assertion fausse - les deux couleurs de fond
resteraient `#2e6f9e` des deux cotes. Choisir la nuance distincte satisfait directement ce qui est
ecrit, sans attendre une reformulation.

### Valeur proposee

| Selecteur | Avant | Propose |
|---|---|---|
| `:root` (nouvelle variable) | (absente) | `--recreo-entete-fond: #1d4b6b;` |
| `.entete` | `background-color: var(--recreo-accent);` | `background-color: var(--recreo-entete-fond);` |

Aucun bouton d'action n'est touche : `--recreo-accent` (`#2e6f9e`) reste la couleur de fond de
`.demarrer`, `.proposition`, `.recommencer`, `.encore` et `.sortie-pleine`, inchangee. Seul le
bandeau change, et seulement lui.

**Pourquoi cette nuance precise et pas une autre couleur.** `#1d4b6b` reste dans la meme famille de
bleu que `--recreo-accent` (coherence d'identite : le bandeau reste "le meme bleu", en plus fonce,
pas une couleur differente qui romprait la charte de S2-2). J'ai calcule le contraste du texte
blanc (`--recreo-accent-texte`) sur ce fond selon la formule de luminance relative WCAG : **environ
9,3:1**, contre environ 5,4:1 aujourd'hui avec `--recreo-accent` - la lisibilite du nom du site et
de "Les jeux" (quand il redevient visible sur l'accueil) s'ameliore, elle ne se degrade pas.

### Ce que j'ai mesure, et sur quel ecran

Sur le jar reconstruit avec ce changement, une partie jouee jusqu'au retour (juste et faux) :
le bandeau sert `background-color: #1d4b6b` et le bouton "Encore !" sert toujours
`background-color: #2e6f9e` - deux valeurs different declarees, verifiees sur le CSS et sur le HTML
assemble reellement servis, pas seulement lues dans la source. Les mesures deja acquises par
`nadia-prof` au point A.1 de la fiche (117 >= 109 >= 102 en largeur, 56 >= 53 en hauteur, action
devant les deux sorties) restent verifiees telles quelles : je n'ai touche aucune dimension de
bouton, `LActionNeDeclarePasMoinsQueLaSortieTest` reste vert par construction sur mon CSS
exploratoire (114/114 tests d'`adapter-web`, y compris ce garde-fou et
`LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest`).

### Un point que je remonte plutot que de le trancher : l'icone du bandeau

En mesurant les pixels de `favicon.ico` (histogramme de couleurs sur les 48 x 48 px de l'icone),
**1218 des 2304 pixels sont exactement `rgb(46, 111, 158)` avec alpha 255, soit `--recreo-accent`
a l'octet pres** : c'est le carre de fond du crayon, colore pour disparaitre dans le bandeau -
c'est exactement ce que `nadia-prof` a valide en s05 ("son fond bleu est exactement la couleur du
bandeau, donc le carre disparait et le crayon flotte [...] c'est propre").

**Si le bandeau passe a `#1d4b6b` sans que l'icone change, ce carre redevient visible** : il
resterait a l'ancienne couleur `#2e6f9e` sur un fond desormais plus fonce, et l'effet de fondu que
`nadia-prof` a explicitement salue serait defait par ma propre proposition. Je n'ai pas retouche
`favicon.ico` : c'est un fichier binaire deja valide pedagogiquement (S2-3), et le retoucher engage
une nouvelle passe de `nadia-prof` sur un objet qui n'est pas dans mon mandat pour cette story. Je
laisse donc deux issues possibles, sans trancher laquelle :

- recolorer le fond du crayon dans `favicon.ico` a `#1d4b6b` a la place de `#2e6f9e` (j'ai verifie
  que les 1218 pixels concernes sont un bloc de couleur uniforme et separe du crayon lui-meme -
  jaune, marron, gris, noir - donc le remplacement est mecanique et sans risque de toucher le
  dessin), dans le meme commit que le changement de `.entete` ;
- ou garder l'icone telle quelle et accepter qu'un petit carre bleu clair redevienne visible dans
  le coin du bandeau, ce que je ne recommande pas puisque cela reintroduit un defaut deja corrige.

Je remonte ce point a `sofia-sm` et `nadia-prof` plutot que de le trancher moi-meme : recolorer un
fichier binaire deja valide sort de "proposer une valeur CSS", et seule `nadia-prof` peut juger si
le nouveau fondu reste aussi propre qu'avant.

### Ce que je laisse au jugement de `nadia-prof`

Que `#1d4b6b` soit percu comme une nuance nettement distincte du bleu d'action (pas seulement une
inegalite de valeur hexadecimale) et que le bleu d'action redevienne le signal exclusif "appuie
ici" - c'est exactement le point qu'elle nomme comme sien dans le backlog. Si elle juge l'ecart de
teinte insuffisant a l'oeil, la meme mecanique (une variable dediee au bandeau) accepte n'importe
quelle autre valeur sans toucher aux boutons.
