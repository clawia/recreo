# Review du sprint 15 - cinq arbitrages successifs sur la barre d'entree par niveau

- **Horodatage de debut** : 2026-08-17, 20:33:26 +0200 (`planning.md`, premier commit du sprint,
  `7bf9692`)
- **Horodatage de fin** : 2026-08-18 17:20 +0200
- **Duree effective** : 20 h 47
- **Increment audite** : `round-two`, tete `74e6e20` - fusion des trois derniers lots restants
  (`camille-po` : `f371eef` ; `ines-design` : `334acbd` ; `nadia-prof` : `74e6e20`), verifiees par
  `git merge-base` avant fusion (aucun des trois worktrees ne descendait d'une branche etrangere),
  `git merge --no-ff`, **aucun conflit sur les trois**, aucun push.

## Ce planning n'a pas suivi son propre ordre - a lire sans avoir suivi le sprint

Ce sprint a ete pilote par **cinq arbitrages successifs de l'utilisatrice**, rendus en jouant
l'application livree, chacun declenchant un lot de developpement sans story instruite au sens
normal, sans objectif gele avant le premier commit et sans avis de faisabilite prealable de
`theo-archi`. `planning.md` le dit en detail : c'est une regularisation retroactive ecrite apres
six lots, pas un planning ordinaire. Cette review ne refait pas ce constat, elle en tire le cout et
le benefice, factuellement.

**Ce que ce mode a coute** : trois des cinq arbitrages (C, D, E) sont restes sans aucune trace `DEC`
pendant toute leur duree de vie en production - `DEC-0035` ne les trace que le jour de la cloture,
apres qu'ils sont deja fusionnes et deja juges en partie par `nadia-prof`. `docs/backlog/backlog.md`
ne porte que `S15-1` et `S15-2` : les arbitrages C, D et E n'ont, a ce jour, **aucune story**, et
`camille-po` nomme elle-meme cet ecart dans `DEC-0035` plutot que de le combler en fabricant une
story retroactive hors mandat. Un sixieme lot (le Done pedagogique VALIDE de `nadia-prof` sur D et
E, `726e0c4`) est reste fusionne nulle part pendant plusieurs heures de developpement ulterieur,
decouvert seulement en regularisant. Aucun des cinq rapports de cloture habituels
(`rapport-qualite.md`, `confirmation-archunit.md`, `relecture.md`) n'a ete ecrit pour ce sprint - ni
par `yanis-qa`, ni par `theo-archi`, ni par le `relecteur-pedagogique`.

**Ce que ce mode a rapporte** : chaque arbitrage a ete instruit, code et rejoue par `nadia-prof` en
moins d'une journee (C le 2026-08-17 soir, D et E puis leur correctif le 2026-08-18), sans qu'un
cycle de planning classique n'ait ralenti la boucle "l'utilisatrice joue, dit ce qui ne va pas,
l'equipe corrige, l'utilisatrice rejoue". Le defaut de survol qui effacait tous les boutons du
depot (`ADR-0046`) et la regression de couleur apres retour au catalogue ont tous deux ete trouves
par l'utilisatrice en jouant, pas par un garde-fou instruit a l'avance - ce que ce mode rend
possible en fermant la boucle le jour meme, il ne le rend pas gardable pour la suite : voir
`retro.md`.

## L'increment en une phrase

**L'en-tete de recreo tient desormais un seul rang au-dessus de 470 px (deux en dessous, accepte
d'avance), porte "Les jeux" en permanence y compris pendant une manche en cours, et la puce de
niveau pressee reste pleine jusqu'au demarrage du jeu ou jusqu'a un retour au catalogue complet,
sur les trois routes qui y ramenent.**

## Porte technique - rejouee par moi sur l'arbre fusionne final, jamais recopiee

```
bash outils/mvn-jdk21.sh -B clean verify
```

**BUILD SUCCESS**, six modules en SUCCESS. Aucune instance de l'application ne tenait plus le jar
ouvert : les six tests d'integration de `bootstrap` (`failsafe`) ont pu s'executer dans cette
mesure, ce qui n'avait pas ete possible plus tot dans le sprint.

| Module | Tests (somme des blocs `Results:` du reacteur apres `clean`) |
|---|---:|
| domain | 48 |
| application | 11 |
| adapter-web | 260 |
| adapter-content | 121 |
| bootstrap (surefire) | 82 |
| bootstrap (failsafe) | 6 |
| **total** | **528** |

**528 confirme par une seconde methode, independante** : somme de l'attribut `tests="..."` sur les
83 rapports XML de surefire et de failsafe du reacteur (`find ... -name "TEST-*.xml"`) - **528**,
identique. 0 echec, 0 erreur sur les deux methodes.

**29 regles ArchUnit, deux methodes qui concordent.** (1) `grep -rn "@ArchTest"` sur tout le depot
hors `target/` : **28** champs annotes. (2) `grep -rn "ArchRule [a-zA-Z]"` (declaration de champ,
quelle que soit son annotation) sur les memes sources : **29** occurrences reelles - l'ecart d'une
unite est `ScanDeComposantsTest.aucuneClasseDeTestSurLeClasspathDeLApplicationNePorteDeStereotype`,
une regle deliberement **non** annotee `@ArchTest` (`ADR-0021`) et executee par deux methodes
`@Test` ordinaires via un appel `.check()` explicite - exactement le piege que
`squad/README.md`, section 4, nomme depuis s06 ("compter les annotations sous-estime le total"). Je
recompte moi-meme ce chiffre plutot que de reprendre celui, constant depuis s14, annonce par
`theo-archi` dans chaque lot de s15 : les deux methodes rendent **29**, inchange.

**Garde-fou de chemins**, rejoue comme la CI :
```
bash .github/scripts/verifier-chemins.sh
  -> "Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies.", EXIT=0
bash .github/scripts/verifier-chemins-test.sh
  -> "Tous les cas passent : le garde-fou de chemins mord, et sur le bon motif.", EXIT=0
```

## Rapport qualite, confirmation ArchUnit, relecture - absents, et je le nomme plutot que de les fabriquer

**Aucun des trois n'existe pour ce sprint.** `squad/sprints/s15-cinq-arbitrages-de-l-en-tete/` ne
contient ni `rapport-qualite.md` (`yanis-qa`), ni `confirmation-archunit.md` (`theo-archi`), ni
`relecture.md` (`relecteur-pedagogique`) - verifie sur le disque, pas suppose. Aucun des trois
agents n'a ete sollicite dans ce sprint pilote par arbitrages successifs.

Ce que j'ai fait a leur place, et ce que cela ne remplace pas : ma propre execution de
`mvn -B clean verify` ci-dessus atteste le Done technique sur l'arbre reellement fusionne, avec sa
methode et son chiffre. Elle ne remplace pas un rapport qualite (aucune mutation de garde-fou
recent n'a ete rejouee par moi ce sprint, contrairement a l'exigence systematique de l'etape 4),
ni une confirmation ArchUnit motivee clause par clause, ni une relecture des livrables non
executables (`planning.md`, `DEC-0035`, les fiches de `theo-archi`, la fiche de conformite) contre
les conventions de redaction de `CLAUDE.md`. **Je route les trois** : `yanis-qa` pour un rapport
qualite et une mutation de garde-fou recent sur le mecanisme de `ADR-0046` ou de la clause du
selecteur compose ; `theo-archi` pour une confirmation ArchUnit motivee sur les regles neuves du
sprint (marqueur `ecran-porte-sa-sortie`, etat actif) ; le `relecteur-pedagogique` pour une
relecture complete des livrables textuels du sprint, aucun n'ayant ete relu a ce jour.

## Avis de `nadia-prof` et Done pedagogique

**Quatre verdicts distincts sur ce sprint**, tous lisibles dans
`squad/conformite/FICHE-barre-entree-niveau.md`, dans l'ordre reverse-chronologique du fichier,
aucun n'ecrasant les autres (verifie par moi apres chacune des quatre fusions du sprint) :

1. **2026-08-18, cloture - VALIDE SOUS RESERVE** sur le correctif de regression et l'arbitrage sur
   `.proposition.deja-essayee`. La couleur de selection est verifiee **eteinte au pixel sur le
   rendu, pas sur la classe**, sur les trois routes de la regression (`/retour`,
   `/retour/confirmer`, `/niveaux/{niveau}/confirmer`) et sur une manche apres filtre - cinq etats
   mesures, quatre eteints, un allume et c'est le bon. `nadia-prof` arbitre elle-meme la question
   d'`ines-design` sur `.proposition.deja-essayee` en retenant la **variante 1 (forme neutre)**,
   contre la recommandation d'`ines-design`, pour deux motifs dont un decisif : la variante 2
   detruirait la premisse globale ("rien ne change jamais sous le pointeur nulle part dans
   l'application") sur laquelle elle avait accorde l'etat actif plein quelques heures plus tot.
   Deux conditions neuves posees pour le lot qui corrigera la derivation : **S15-8** (l'etat neutre
   conserve la couleur de fond cyclique de sa position) et **S15-9** (le trait de M9 survit).
   **Reserve S15-D, neuve, non bloquante** : sur `/niveaux/{niveau}/confirmer`, l'enfant atterrit
   sur une liste filtree a six jeux sans aucune phrase pour le dire - S15-2 tenue sur une route, pas
   sur l'autre.
2. **2026-08-18, second lot - VALIDE** sur le rang unique et l'etat actif (arbitrages D et E). Les
   sept conditions S15-1 a S15-7 sont tenues au chiffre : zero pixel de debordement de document aux
   six largeurs demandees, en-tete a 143 px a 360 px, contraste de 9,25:1 sur l'etat actif mesure
   sur les pixels rendus. Reserve S15-C reduite d'un tiers, non fermee (le bouton "Les jeux" reste
   visuellement identique a une puce inactive).
3. **2026-08-18 - VALIDE SOUS RESERVE** sur S15-1, S15-2 et l'arbitrage C (la sortie visible
   pendant une manche). Sept conditions bloquantes posees pour la suite, reserve S15-C ouverte pour
   la premiere fois.
4. **2026-08-17 (s14) - VALIDE SOUS RESERVE**, verdict herite de la cloture du sprint precedent,
   inchange par ce sprint et lisible sous les trois verdicts ci-dessus.

**Aucun Done pedagogique n'est prononce par moi** : les quatre verdicts restitues ci-dessus sont
ceux de `nadia-prof`, sans reformulation ni synthese qui en attenuerait les reserves.

## Tableau des obstacles du sprint

| Description | Categorie | Destinataire | Statut |
|---|---|---|---|
| Le sixieme lot (`726e0c4`, Done VALIDE de `nadia-prof` sur D/E) est reste fusionne nulle part pendant plusieurs heures | technique / methode | sofia-sm | **clos** - fusionne (`6b3641e`), verifie sans conflit, contenu verifie dans la fiche |
| Trois arbitrages (C, D, E) sans aucun fichier `DEC` | produit / gouvernance | camille-po | **clos** - `DEC-0035` cree, amendements dates de `DEC-0027` et `DEC-0034` dans le meme geste |
| Aucun Done pedagogique sur le correctif de regression | pedagogie | nadia-prof | **clos** - VALIDE SOUS RESERVE prononce, cinq etats mesures au pixel |
| Trou du selecteur compose (`.proposition.deja-essayee` sans etat interactif declare) | technique | theo-archi, ines-design, nadia-prof | **clos pour l'arbitrage** - variante 1 (neutre) retenue par `nadia-prof`, S15-8/S15-9 posees ; **ouvert pour l'implementation**, non livree ce sprint |
| Noms de marqueurs `ecran-exclusif` et `ecran-porte-sa-sortie` a confirmer | design | ines-design | **clos** - les deux noms sont gardes tels que proposes par `theo-archi`, motif ecrit |
| Aucune story de backlog pour les arbitrages C, D, E | produit / gouvernance | camille-po | **ouvert**, nomme explicitement par `camille-po` elle-meme dans `DEC-0035` comme un ecart avec sa propre regle de gouvernance, hors du mandat de cette regularisation |
| Aucun rapport qualite (`yanis-qa`), aucune confirmation ArchUnit motivee (`theo-archi`), aucune relecture (`relecteur-pedagogique`) pour ce sprint | technique / methode / redaction | yanis-qa, theo-archi, relecteur-pedagogique | **ouvert** - route ci-dessus, ferme partiellement par ma propre reverification technique, qui ne couvre ni la mutation de garde-fou recent ni la relecture redactionnelle |
| Reserve S15-C : le bouton "Les jeux" reste visuellement identique a une puce inactive | pedagogie | camille-po (story candidate) | **ouvert, reduit d'un tiers** |
| Reserve S15-D : aucune phrase n'annonce le filtre sur `/niveaux/{niveau}/confirmer` | pedagogie | camille-po (story candidate) | **ouvert, neuf** |
| R-BARRE-1 (reserve J, grands nombres CM1) : +22 px depuis s14 (etait +13 px au lot 3, aggrave au lot 4) | pedagogie / produit | camille-po | **ouvert**, chiffre definitif etabli ce sprint |
| R-BARRE-4 : F11 (ecoute lecteur d'ecran) s'enrichit de deux ecrans supplementaires et de deux premiers arrets de tabulation | pedagogie / accessibilite | hors de la portee de la squad | **ouvert, inchange depuis s13** |
| Question de nomenclature de la matiere accentuee | produit + technique | camille-po (choix), nadia-prof (graphie) | **ouvert, non affecte** - aucun filtre par matiere developpe ce sprint |
| Garde-fou de provenance des classes amont (mesure partielle sans `-am`) | technique | theo-archi | **ouvert depuis s14, non affecte** |
| Migration du marqueur `ecran-de-jeu` vers un attribut | technique | theo-archi (mecanisme), sofia-sm (capacite) | **ouvert depuis s14, non affecte** |
| Resolution de navigateur d'`outils/mesurer-rendu.sh` (facteur de bus sur la mesure) | technique | sofia-sm (capacite) | **ouvert depuis s14, non affecte** |
| Dependance a une seule personne pour toute mesure de rendu | technique / methode | sofia-sm (capacite), camille-po | **ouvert, nomme quatre fois par `nadia-prof` en deux jours ce sprint**, dont trois avec sa cause (outillage, jamais d'observation d'enfant, jamais de survol reel) |
| Predicat `estRemplacant` d'`ADR-0042` toujours sans objet en code | technique | theo-archi (dette actee) | **ouvert depuis s13, non affecte** |
| Reserve F11 (ecoute reelle au lecteur d'ecran) | pedagogie / accessibilite | hors de la portee de la squad | **ouvert depuis s13, inchange** |

## Le compte de retraits et de retournements de tests sur `theo-archi`

Cinq fois ce sprint et le sprint precedent immediatement lie a lui, la clause 2 d'`ADR-0022` ou son
voisinage direct a ete retiree, retournee ou reformee par `theo-archi` : le retrait force par
`S14-2` (clause du marqueur emportee), la reforme de forme du troisieme amendement (l'assertion
`isNotEmpty()` supprimee), la verification du 2026-08-17 (aucun fichier ne bouge), la note du
2026-08-18 (population de masquage changee une cinquieme fois, toujours zero fichier de garde-fou
touche), et le retournement de l'epinglage du lien d'en-tete (`accord-theo-epinglage-du-lien.md`,
l'assertion inversee plutot que retiree). **Deux de ces cinq evenements portent une decouverte
reelle et non une simple adaptation** : l'assertion mal placee qui causait toute la cascade de s14
(troisieme amendement), et l'hypothese de specificite CSS fausse d'`ADR-0014` qui a laisse le
defaut de survol traverser quinze sprints et une CI verte. Les trois autres sont des consequences
mecaniques, mesurees et non supposees, d'un mecanisme qui ne bouge pas.

## Trois trous de garde-fou trouves par mutation pendant le sprint

1. **`ADR-0014`** : l'hypothese "une regle maison de meme specificite l'emporte sur la regle
   tierce" ne tenait pas des qu'une regle tierce porte une pseudo-classe - mesure : 139 selecteurs a
   pseudo-classe interactive sur 2959 dans la feuille vendorisee, huit boutons de l'application
   affectes. Ferme par `ADR-0046`.
2. **Le selecteur compose** `.chip-niveau.est-actif:hover` : si la regle du compose disparaissait,
   `.chip-niveau:hover` prendrait le dessus et la puce active perdrait son apparence active sous le
   doigt - defaut plus discret que celui vu par l'utilisatrice, donc plus probable de passer
   inapercu. Trouve par moi en cherchant ce que le garde-fou d'etats interactifs ne pouvait pas
   voir ; chiffre par `nadia-prof` (deux feuilles reelles, ordre du document, spécificite mesuree) ;
   arbitrage rendu ce sprint (variante neutre), correction non livree.
3. **Le filtre naif de la clause E** (neutralisation par marqueur d'ecran de jeu seul) : ne couvrait
   pas les routes de retour au catalogue complet, laissant la couleur active survivre a un retour.
   Corrige par `marc-dev` au passage du correctif de regression. **Aucun faux vert produit** : le
   garde-fou d'etat actif ne portait, avant ce sprint, aucune clause sur ces trois routes - son
   risque etait un faux rouge (rougir a tort si une future clause generalisait mal), pas un faux
   vert deja vecu.

## `theo-archi` a refuse un geste sur cinq puis change de forme trois fois sur le meme mecanisme

Sur l'ensemble du mecanisme de masquage d'en-tete (`ADR-0022`) depuis s14, `theo-archi` a refuse un
geste propose par `marc-dev` sur cinq comme portant une regression reelle, et a change la **forme**
de la clause 2 trois fois (retrait force, reforme d'assertion, retournement de l'epinglage) sans
qu'aucune de ces trois formes n'ait jamais ouvert de fichier de garde-fou neuf sur ce mecanisme :
la reforme du troisieme amendement tient ses deux tests reels (`LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest`,
`LActionNeDeclarePasMoinsQueLaSortieTest`) a **zero fichier de garde-fou** ajoute malgre cinq
changements de population de masquage documentes.

## Couverture programme

**Aucune ligne de `squad/programme/MATRICE-couverture.md` n'est ouverte ni fermee par ce sprint.**
Les cinq arbitrages portent sur la navigation et l'apparence de l'en-tete, aucun sur un contenu
pedagogique ; `MATRICE-couverture.md` n'est pas touche, ce n'est pas une omission.

## Decisions prises

1. **Le Done technique de l'increment fusionne (`74e6e20`) est atteste par ma propre execution de
   `mvn -B clean verify`**, avec deux methodes de comptage concordantes (528 tests, 29 regles
   ArchUnit) : aucun rapport qualite ni confirmation ArchUnit n'existant pour ce sprint, je ne
   fabrique aucune section de cloture au nom de leurs auteurs absents.
2. **Aucun rapport qualite, aucune confirmation ArchUnit motivee, aucune relecture ne sont
   fabriques a la place de `yanis-qa`, `theo-archi` et du `relecteur-pedagogique`.** Leur absence
   est nommee comme un fait et routee, pas comblee.
3. **Les quatre verdicts de `nadia-prof` sur `FICHE-barre-entree-niveau.md` sont restitues sans
   reformulation ni synthese qui en attenuerait la portee.** Aucun Done pedagogique n'est prononce
   par moi.
4. **L'absence de story de backlog pour les arbitrages C, D et E reste ouverte**, `camille-po`
   ayant elle-meme nomme cet ecart hors du mandat de sa regularisation ; je ne la comble pas non
   plus ici, faute de mandat produit pour le faire.
5. **Aucune ligne de `MATRICE-couverture.md` n'est mise a jour**, conformement au perimetre reel du
   sprint (navigation et apparence, aucun contenu neuf).
6. **Aucun worktree n'est supprime, aucun push n'est effectue dans ce geste.**
