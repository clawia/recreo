# Review du sprint s11 - la tabulation ne detruit plus la partie

> A la difference des sprints precedents, aucune section n'est ouverte ici par `nadia-prof` :
> aucun commit ni branche portant un verdict de Done pedagogique sur l'increment corrige et livre
> de ce sprint n'existe sur le depot au moment de cette cloture. Ce que j'ecris ci-dessous vient
> de ce qui est reellement sur le disque - l'arbitrage prealable qu'elle a rendu avant la
> correction finale du texte et de la forme - et je le dis comme tel plutot que de fabriquer une
> conclusion qu'elle n'a pas ecrite. Toutes les sections qui suivent sont ecrites par `sofia-sm`.

## Cloture - sofia-sm, du 2026-08-14

- **Horodatage de debut** : 2026-08-14 11:14 (`planning.md`).
- **Horodatage de fin** : 2026-08-14 16:28.
- **Duree effective** : 5 h 14.
- **Commit de tete de `round-two` apres cette cloture** : `14cd800`.

### Fusion des deux derniers lots

`round-two` etait a `1421d46` a l'ouverture de cette cloture (planning, developpement de
`marc-dev`, arbitrages de `nadia-prof` et `ines-design` sur la forme et le texte de l'ecran de
confirmation, correctifs de `lea-dev`, et retrait de la clause de concordance d'`ADR-0038` par
`theo-archi`, tous deja fusionnes). Deux lots documentaires restaient a fusionner, aucun des deux
ne touchant un fichier de production :

| Ordre | Lot | Branche | Tete | Contenu verifie par `git diff --stat` avant fusion |
|---|---|---|---|---|
| 1 | `yanis-qa` | `s11-yanis-qualite` | `8429f09` | un seul fichier neuf, `rapport-qualite.md` |
| 2 | `ines-design` | `s11-ines-chiffres` | `86cf1f6` | un commentaire corrige dans `recreo.css` (chiffre de pixels vides, 5 -> 4, aucune ligne de style changee) et deux documents de sprints anterieurs (`proposition-ines-largeur-du-cabinet.md` de s08, `proposition-ines-manche-et-relief.md` de s10) ou elle referme, sans qu'on le lui demande, une divergence de contraste qu'elle avait laissee ouverte : la cause n'etait pas une linearisation sRGB mais une paire de couleurs fausse du cote de `nadia-prof` (cadre compare au blanc pur au lieu du fond de page) |

**Lignee verifiee au prealable** : les deux branches descendent de `1421d46`, tete de `round-two`
au moment de leur ouverture (`git merge-base round-two <branche>` rend `1421d46` pour les deux).
Fusionnees par deux `git merge --no-ff` successifs, dans l'ordre du tableau. **Aucun conflit sur
aucune des deux fusions.**

### Porte technique - etablie par moi, pas relayee

`JAVA_HOME` designe sur un JDK 21.0.10 le temps de la commande (`java` du `PATH` en 1.8.0_202,
constat rendu par `bash outils/verifier-jdk.sh`). `mvn -B clean verify` sur l'arbre fusionne final
(`14cd800`) : **BUILD SUCCESS**, six modules en SUCCESS, aucun test ignore.

| Module | Tests (bloc `Results:` du reacteur Maven, apres `clean`) |
|---|---|
| domain | 46 |
| application | 7 |
| adapter-web | 216 |
| adapter-content | 64 |
| bootstrap (surefire) | 77 |
| bootstrap (failsafe) | 6 |
| **Total** | **416** |

**416, confirme par une seconde methode** : recherche litterale de la forme `ArchRule <nom> =`
sous `src/test/java` des cinq modules de code (31 occurrences), moins les 3 du faux module de
contre-exemple de `LesGardeFousSontMisALEpreuveTest` (vit sous `src/test/resources`, jamais
compile comme regle du depot) = **28 regles ArchUnit**, methode et resultat inchanges. Ces deux
chiffres sont ceux que rend independamment `yanis-qa` dans son rapport de qualite (voir plus bas) ;
je ne les recopie pas, je les ai reconstruits sur l'arbre fusionne.

**Garde-fou de chemins, rejoue par moi avant cette cloture** :

```
git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' | bash .github/scripts/verifier-chemins.sh
```

rend `Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies.`, **code de
sortie 0**.

### Pourquoi 416, jamais 409 - le mecanisme, pas seulement le chiffre

J'avais avance, avant cette cloture, que les rapports `.txt` de surefire sous-comptaient d'un test
sur une classe parametree. **C'etait faux, et `yanis-qa` a etabli le mecanisme reel** : quatre
classes de ce depot, dans deux modules, melangent un champ statique `@ArchTest ArchRule` (execute
par le moteur propre a `archunit-junit5`) et au moins une methode `@Test` ordinaire (executee par
Jupiter). Le rapport XML de chacune agrege correctement les deux moteurs (`tests="N"` juste) ; le
rapport `.txt`, lui, n'ecrit que le compte du moteur Jupiter pour cette classe - `AssertionsWebTest`
(1 au lieu de 2), `AbstractionsCiteesTest` (1 au lieu de 5), `ChargementDuContenuTest` (1 au lieu de
2), `LesReglesMordentTest` (25 au lieu de 26), soit un ecart total de **7**, exactement l'ecart
entre 416 et 409 que le `relecteur-pedagogique` avait releve. Ce depot compte ses tests a chaque
cloture depuis onze sprints ; **416 est le chiffre autoritaire** (somme des blocs `Results:` du
reacteur Maven, identique a la somme de l'attribut `tests=` de chaque rapport XML), et les fichiers
`.txt` de surefire/failsafe ne doivent plus servir de source de compte des qu'une classe combine un
champ `@ArchTest` et une methode `@Test`. Amendement porte dans `squad/README.md`, section 9, point
4.

### Rapport qualite - yanis-qa

Fichier : `squad/sprints/s11-la-tabulation-ne-detruit-plus-la-partie/rapport-qualite.md`, ecrit sur
`round-two` a `1421d46`, avant les deux lots fusionnes ci-dessus (aucun des deux ne touche du code
ni des tests).

- **416 tests, 28 regles, garde-fou de chemins en code 0**, confirmes par moi ci-dessus sur l'arbre
  fusionne final, memes methodes, memes resultats.
- **Cinq mutations sur du materiau de production reel, toutes restaurees**, `git status --porcelain`
  vide verifie a chaque etape : le retrait de la clause de concordance d'`ADR-0038` ne laisse pas de
  trou au-dela de celui que `theo-archi` nomme lui-meme (verifie par mutation d'attribut, pas par
  lecture) ; `AucunArretDeTabulationNEstDestructeurTest` rougit bien si le corpus s'evide (5 echecs
  sur 6) ; le test de texte de confirmation garde la phrase entiere (mutation d'un mot -> rouge) mais
  pas sa position (deplacement de la phrase -> reste vert, limite nommee) ; **`RessourcesTiercesTest`
  reste vert sur ses 16 tests apres l'ajout reel d'un fichier JavaScript tiers de bibliotheque
  d'interface (`alpine.min.js`), correctement trace - un garde-fou de ressources tierces qui ne
  garde pas.**
- **Deux instances neuves de la classe de defaut "perimetre derive, critere enumere sur des
  incidents clos"** trouvees par un grep systematique de l'auto-declaration qu'`ADR-0013` exige
  (quatre fichiers la portent) : `RessourcesTiercesTest` confirmee par mutation (ci-dessus) ;
  `AucunVocabulaireDeMatiereTest` cherchee et **distinguee** - sa liste nomme des categories de
  vocabulaire qui generalisent, pas des incidents clos, donc pas la meme faute. Reponse a la
  question de la detection systematique, portee telle quelle : le grep de l'auto-declaration
  enumere lui-meme tous les candidats du depot, mais juger si les membres d'une liste generalisent
  ou nomment chacun un incident clos reste une lecture, jamais un motif syntaxique. Amendement porte
  dans `squad/README.md`, section 9, point 4.
- **Les quatre non-preuves d'`ADR-0038` que `theo-archi` declare "encore vraies" (1, 2, 6, 7)
  tiennent contre l'implementation reelle**, verifiees par lecture du code livre et non supposees.
- **Aucun etat partage entre les rejeux de `AucunArretDeTabulationNEstDestructeurTest`**, verifie
  par lecture (`EtatDePartieEnSession` porte tout l'etat dans `HttpSession`, jamais un champ
  statique ni un registre partage).

### Confirmation ArchUnit - theo-archi

`ADR-0038`, amende le 2026-08-14 apres la fusion du lot de `marc-dev`, retire lui-meme sa propre
clause de concordance et corrige son propre releve, sur trois mutations qu'il a mesurees :

1. **La clause de concordance (point 3) est retiree, pas reparee.** Elle etait un moyen de deriver
   "destructeur" sans se laisser desamorcer par un renommage d'attribut ; le rejeu reel de la
   requete la remplace, et elle interdisait le remede que le point 5 de la meme decision benissait.
   Mutation verifiee : `data-role="sortie"` renomme en `"action"` sur le bouton reel qui detruit
   dans `fragments/confirmation.html` - les deux clauses restent vertes, la concordance n'attrapait
   plus rien.
2. **"Deux ecrans rouges" etait faux : ils etaient cinq en clause 1 et un en clause 2.** Mesure en
   revenant a l'`abandonner()` inconditionnel d'avant le lot (mutation reelle) : cinq ecrans rouges
   sur la clause 1 (manche 1, reprise, apres reponse juste, apres reponse fausse, revelation), un
   seul sur la clause 2 (revelation, seul ecran dont le point de focus n'a aucun arret apres lui).
3. **Quatre des huit non-preuves ont change (3, 4, 5, 8) ; les quatre autres (1, 2, 6, 7) restent
   vraies.** La non-preuve 4 etait **fausse** : elle disait que la souris sur "Les jeux" detruit
   encore la partie sans confirmation ; ce n'est plus le cas, la destruction ne vit plus dans le
   geste mais dans une requete que seul l'ecran de confirmation declare. `yanis-qa` a verifie les
   quatre non-preuves restantes (1, 2, 6, 7) contre l'implementation livree ; elles tiennent.

Le compte des regles ArchUnit reste a **28**, inchange : cet amendement n'ajoute et ne retire
aucune `ArchRule`, la propriete portant sur un document rendu et non sur du bytecode. Confirme par
moi ci-dessus, memes methode et resultat.

`ADR-0022` est amende le meme jour sur un chiffre perime : "102 x 53 px" (mesure du 2026-08-07 sur
le libelle "Accueil") devient **"109 x 53 px"**, le libelle ayant change en "Les jeux" depuis
S6-9. La decision elle-meme n'est pas rouverte.

### Corrections - relecteur-pedagogique

**Aucun commit `[REL]` ni fichier de relecture dedie n'existe pour s11 dans ce depot.** Il m'est
rapporte que le `relecteur-pedagogique` a relu 18 fichiers de ce sprint et n'y a rien corrige. Je
ne peux pas l'etablir moi-meme depuis le depot - il n'existe aucun artefact (fichier, commit,
liste des fichiers relus) qui permette de reverifier ce chiffre, contrairement a toute mesure
technique de cette review. Je le rapporte donc comme une annonce, pas comme un fait constate.
C'est la troisieme fois en quatre sprints (s09, s10, s11) qu'aucune trace de relecture ecrite
n'existe sur le depot ; voir `retro.md`. `RAPPORT-tokens.md` mesure 113 tours pour cet agent sur
la fenetre du sprint, ce qui est compatible avec une activite reelle de relecture - mais un compte
de tours ne prouve ni le nombre de fichiers relus ni l'absence de correction : ce n'est pas un
substitut a un fichier ou un commit.

### Avis de nadia-prof et Done pedagogique

**Aucun Done pedagogique n'est prononce sur l'increment de confirmation livre par ce sprint.** Ce
n'est pas un refus : c'est l'absence, sur le depot, d'un verdict quelconque sur l'incrément
finalement livre (forme a 44 px, texte "Si tu pars, ta partie s'arrête.").

Ce qui existe reellement sur le disque, dans
`squad/conformite/FICHE-bandit-manchot-additions-cp.md` : un **arbitrage prealable** du
2026-08-14, rendu par `nadia-prof` en jouant un jar reconstruit sur la forme et le texte proposes
avant leur correction. Ce bloc dit explicitement ne pas etre un Done pedagogique.

- **Forme (`ines-design`) : ACCEPTEE sous une condition chiffree** - l'ecart vertical entre les
  deux boutons doit passer de 32 a **44 px** de pixels reellement vides (le plancher que
  l'application s'impose deja sur toute cible, `min-height: 2.75rem`). `lea-dev` livre cette
  correction (CONF-1, CONF-2).
- **Texte (`marc-dev`) : REFUSE, quatre motifs**, dont deux graves : *"tu perds ta partie"* est un
  piege d'homonymie dans un jeu (perdre veut dire etre battu, pas voir son travail efface) ; la
  question posee ("Veux-tu vraiment quitter ?") n'est pas celle que les deux boutons repondent.
  Texte de remplacement nomme et mesure par elle : **"Si tu pars, ta partie s'arrête."** - repris
  du mot que l'application emploie deja pour le meme evenement ailleurs. `lea-dev` livre cette
  correction (CONF-3).
- **Trois chiffres corriges avant de s'appuyer dessus, dont un des siens** : l'ecart entre boutons
  livre est de 4 px de pixels vides (pas 5, mesure par soustraction de coordonnees) ; le rapport de
  poids "identique" tel que livre est faux, 2,01:1 en largeur (pas 1:1) ; et sa propre divergence de
  contraste du 2026-08-12 (8,61 contre 9,25) ne venait pas d'une linearisation sRGB, mais d'une
  paire de couleurs fausse de son cote (cadre compare au blanc pur au lieu du fond de page).
- **Reserve O fermee sur sa premiere moitie** ("le seul chemin est Les jeux, qui detruit sans
  confirmation") ; **sa seconde moitie reste ouverte** ("aucun chemin vers le catalogue ne
  preserve la partie" - `GET /` pendant une partie continue de rendre 0 bouton de demarrage) ; et
  une **reserve neuve** : le meme libelle "Les jeux" porte desormais deux consequences selon
  l'ecran (ne detruit rien sur la manche, detruit sur la confirmation), motif pour `camille-po`,
  pas condition sur ce lot.

**Ce que je n'ai pas trouve sur le depot, et qui manque pour clore la dette de `DEC-0026`** : un
verdict rendu **apres** la correction de forme et de texte par `lea-dev` (commit `4eaddf0`), et une
reverification **au clavier reel**, sur les 25 revelations de son protocole et sur l'ecran de
reprise, telle que `DEC-0026` l'exige explicitement comme condition necessaire de plus que la CI
verte. Voir la section suivante.

### Declencheur de reouverture de `DEC-0026` - verifie par moi, pas prononce satisfait

`DEC-0026` (amendee le 2026-08-14 a la cloture de s10) pose quatre conditions cumulatives. Je les
verifie une par une contre ce qui est reellement sur le disque a cette cloture, et non contre ce
que ce sprint visait a livrer :

| # | Condition | Etat | Preuve |
|---|---|---|---|
| 1 | Au moins une des deux stories est planifiee pour s11, pas laissee non affectee | **satisfaite** | `planning.md` planifie la story de confirmation, qui couvre la story de tabulation dans le meme geste ; `marc-dev` et `lea-dev` la livrent (`687b4be`, `4eaddf0`), fusionnee sur `round-two` |
| 2 | La reverification se fait au protocole exact de `nadia-prof` (clavier reel, Tabulation puis Entree, sur les 25 revelations - sept jeux, tirages repetes sur "Calcul CP" et "Le bon verbe") | **non satisfaite, faute de preuve sur le depot** | aucun fichier ni commit ne porte une reverification au clavier posterieure a `4eaddf0` (la correction de forme et de texte). L'arbitrage prealable de `nadia-prof` du 2026-08-14 mesure l'assemblage des documents rendus (section 5 de son bloc), pas une sequence au clavier reel, et il porte sur le texte qu'elle a elle-meme refuse - pas sur le texte finalement livre |
| 3 | Si aucune des deux stories n'est livree a la cloture, la levee revient devant l'utilisatrice comme un report a nommer | **sans objet** - la story est livree, donc cette consequence ne se declenche pas | voir condition 1 |
| 4 (amendement) | La reverification couvre aussi l'ecran de reprise (`GET /` pendant une partie, rien focalise au depart) | **non satisfaite, meme manque que la condition 2** | meme raison : aucune reverification posterieure a la livraison finale n'est tracee sur le depot |

**Conclusion, et je la prononce parce que c'est mon role, pas celui de `nadia-prof` : le
declencheur de `DEC-0026` n'est pas integralement satisfait a cette cloture.** Deux de ses quatre
conditions cumulatives (2 et 4) manquent leur preuve sur le depot - non pas parce que le remede
technique serait absent (les tests de `theo-archi` et `yanis-qa` etablissent que l'invariante
tient sur la CI), mais parce que la reverification **au clavier reel**, sur l'increment corrige et
livre, que `DEC-0026` exige nommement comme une condition necessaire de plus que la CI, n'a pas
ete faite ou n'a pas ete tracee. Ce n'est pas un Done pedagogique que je refuse a la place de
`nadia-prof` - je n'ai pas cette autorite - c'est un fait de gouvernance que je constate : la
dette de `DEC-0026` reste ouverte, routee a `nadia-prof` pour la reverification manquante, et a moi
pour le suivi. Voir le tableau des obstacles ci-dessous.

### Tableau des obstacles du sprint

| Description | Categorie | Destinataire | Statut |
|---|---|---|---|
| Reverification au clavier reel de `DEC-0026` (25 revelations, sept jeux, tirages repetes, plus l'ecran de reprise), sur l'increment corrige et livre - conditions 2 et 4 du declencheur | pedagogie, produit | `nadia-prof` (reverification), `sofia-sm` (suivi) | **ouvert** - aucune preuve sur le depot a cette cloture ; la dette de `DEC-0026` n'est donc pas fermee |
| `RessourcesTiercesTest` reste vert alors qu'un JavaScript tiers d'interface, correctement trace, entre sous un nom absent de sa liste fermee | technique, architecture, garde-fou de ressources tierces | `theo-archi` | **route**, non clos - demande de deriver le perimetre plutot que de l'enumerer |
| `TexteAccentueDesGabaritsTest.aucunGabaritNeReintroduitUneFauteConnue` : critere enumere sur des incidents clos, ne peut rien exiger d'un texte qui n'existait pas au moment ou la liste a ete ecrite - `nadia-prof` a remplace la phrase entiere sans faire bouger un test | technique, architecture, garde-fou de redaction | `theo-archi` (deja signale, connu depuis avant ce sprint, reconfirme) | **route**, non clos - meme famille que le point precedent |
| Garde-fou de chemins rouge sur ce sprint, deux occurrences dans `planning.md` | process, gouvernance | `sofia-sm` | **clos** - cause nommee ci-dessous (retro.md), corrige a la source par `2fa2df3` avant la fusion de ce jour, garde-fou verifie vert par moi a cette cloture |
| Deux propositions de methode de `yanis-qa` routees a `theo-archi` a la cloture de s10 (outil de cycle de vie du serveur de mesure ; convention d'`ADR` nommant ce qu'une regle ne prouve pas) | technique, outillage | `theo-archi` | **toujours reportees a s12**, decision deja actee au planning de s11 (capacite de `theo-archi` engagee sur la supervision d'`ADR-0013`) ; confirmee inchangee a cette cloture |
| Aucun fichier de relecture dedie pour la troisieme fois en quatre sprints (s09, s10, s11) | redaction, gouvernance de ceremonie | `relecteur-pedagogique` | **note**, pas un obstacle a router - constat repete, voir `retro.md` |
| Neuf worktrees de sprint sur le disque a l'ouverture de cette cloture | capacite | `sofia-sm` (casquette dev) | **clos** - les neuf branches verifiees ancetres de `HEAD` (`git branch --merged round-two`), arbre propre sur chacune, worktrees supprimes |

Rappel du gabarit : un obstacle n'est `clos` que si le fichier qui le tranche existe physiquement.
Le premier obstacle du tableau reste `ouvert` tant qu'aucun fichier ne porte la reverification au
clavier reel qu'il exige.
