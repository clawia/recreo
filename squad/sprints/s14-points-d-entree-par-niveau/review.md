# Review du sprint 14 - points d'entree par niveau

- **Horodatage de debut** : 2026-08-17 13:24 (`planning.md`)
- **Horodatage de fin** : 2026-08-17 19:34
- **Duree effective** : 6 h 10
- **Increment audite** : `round-two`, tete `a7c7401` - fusion du lot `nadia-prof` (`s14-nadia-porte`,
  `0a55b3c`), verifie par `outils/verifier-lignee.sh` avant fusion ("le worktree sur la branche
  's14-nadia-porte' descend de la lignee de round-two"), `git merge --no-ff`, aucun conflit, aucun
  push.

## L'increment en une phrase

**Deux boutons d'entree par niveau (CP, CM1) sont desormais dans l'en-tete de recreo, visibles en
permanence sur l'accueil et sur les trois etats de manche, et filtrent la liste des jeux d'un
appui ; sur l'ecran de confirmation d'abandon et sur l'ecran d'erreur, ils sont absents par choix
pedagogique, pour ne pas ajouter de reponses a un endroit ou l'enfant n'en attend qu'une ou deux.**

## Un ecart entre la demande et ce qui est livre - a lire sans avoir suivi le sprint

L'utilisatrice avait demande que les boutons de niveau restent **"toujours visibles, y compris
pendant une partie"**. Ce que le sprint livre : ils sont visibles sur l'accueil et sur les trois
ecrans de manche (manche, echec, revelation), mais **absents** de l'ecran de confirmation
d'abandon et de l'ecran d'erreur. Ce n'est pas un oubli : `nadia-prof` a refuse de les y poser
(deux ecrans ou ajouter deux puces de niveau creerait quatre reponses a une question qui n'en pose
qu'une ou deux). Ce choix figurait deja au planning avant tout developpement ; il est confirme,
inchange, par son verdict final. Changer de niveau depuis l'un de ces deux ecrans demande donc
d'abord d'en sortir, exactement comme avant ce sprint.

## Porte technique - rejouee par moi sur l'arbre fusionne, jamais recopiee

```
bash outils/mvn-jdk21.sh -B clean verify
```

**BUILD SUCCESS**, six modules en SUCCESS.

| Module | Tests (rapports XML surefire/failsafe, agreges apres `clean`) |
|---|---|
| domain | 48 |
| application | 11 |
| adapter-web | 242 |
| adapter-content | 121 |
| bootstrap (surefire) | 82 |
| bootstrap (failsafe) | 6 |
| **total** | **510** |

**510 confirme**, identique au chiffre que `nadia-prof` annonce dans son commit de fusion
(`0a55b3c`, remesure independante sur le meme arbre). 0 echec, 0 erreur.

**29 regles ArchUnit, deux methodes qui concordent.** (1) Tous les `.java` sous `src/test/java`
du depot, hors `target/` : **31** occurrences brutes du motif `ArchRule <nom> =`, dont **2** dans
`RegleContreExemple.java` (le faux module de contre-exemple de
`LesGardeFousSontMisALEpreuveTest`, dont le chemin contient la sous-chaine `src/test/java` sans
etre un module compile par le pom agregateur) : **31 - 2 = 29**. (2) Balayage des seuls
`src/test/java` des cinq modules declares au pom : `bootstrap` 28, `adapter-web` 1
(`AssertionsWebTest`), `domain`/`application`/`adapter-content` zero : **29**. Les deux methodes
se recoupent. Le compte est inchange depuis le planning (29) malgre le troisieme amendement
d'`ADR-0022` : `theo-archi` a reforme une clause existante plutot que d'en ajouter une (voir
"Ce qui a coince" en synthese ci-dessous).

**Garde-fou de chemins**, rejoue comme la CI :
```
git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' | bash .github/scripts/verifier-chemins.sh
  -> "Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies.", EXIT=0
bash .github/scripts/verifier-chemins-test.sh
  -> "Tous les cas passent : le garde-fou de chemins mord, et sur le bon motif."
```

## Rapport qualite - `yanis-qa`, et ce que sa date ne couvre pas

`squad/sprints/s14-points-d-entree-par-niveau/rapport-qualite.md` (2026-08-17, arbre audite
`c1abb47`) : BUILD SUCCESS, **502 tests**, **29 regles ArchUnit**, garde-fou de chemins a 0. Sept
mutations restaurees sur du materiau reel (`ADR-0043`, deux clauses d'`ADR-0044`, comptage derive
de l'en-tete, corpus de routes, les deux dettes techniques), chacune rouge puis restauree verte -
preuve de morsure au sens de l'amendement s10. Deux pieges nommes par `theo-archi` fermes sur le
code livre. Aucun defaut technique ouvert a cette date.

**Ce que cette date ne couvre pas, et qui doit etre dit sans detour** : `c1abb47` precede le
developpement de S14-2 (retrait du lien "Les jeux"), le refus pedagogique de `nadia-prof`, et la
correction du masquage (clauses A/B/C d'`ADR-0022`). `yanis-qa` n'a pas republie son rapport sur
l'arbre final. C'est moi qui ferme cette boucle ci-dessus : ma propre execution de
`mvn -B clean verify` sur `a7c7401`, la tete reellement fusionnee, est ce qui atteste le Done
technique de l'increment livre - je ne le releve pas d'un rapport qui s'arrete plus tot.

## Confirmation ArchUnit - `theo-archi`, et ce que sa derniere section ne fait pas

`squad/sprints/s14-points-d-entree-par-niveau/confirmation-archunit.md` porte sept sections : la
porte technique a l'ouverture de son propre lot, la derivation du catalogue (`ADR-0045`),
l'accord geste par geste sur S14-2 (section 6), et l'arbitrage du masquage apres le refus
pedagogique (section 7, arbre lu `911af6a`). Cette derniere section **decide de la forme du
correctif** (le marqueur `ecran-exclusif`, les trois clauses A/B/C, la reforme de la clause 2
d'`ADR-0022`) ; elle ne re-confirme pas la porte technique **apres** que `marc-dev` a livre ce
correctif (`10909e8`, fusionne en `23267ea`). Aucune section posterieure ne le fait non plus.

Je referme cette boucle de la meme facon que pour le rapport qualite : mon `mvn -B clean verify`
sur `a7c7401` est vert sur les 29 regles, sans exception, ce qui inclut necessairement les trois
clauses A/B/C et la clause reformee d'`ADR-0022` puisqu'elles font partie du meme reacteur execute
d'un seul tenant. C'est cette execution-la, pas une section de `confirmation-archunit.md`, qui
atteste l'ArchUnit du code final.

## Corrections du relecteur-pedagogique

`relecture.md` (arbre lu `81a43ac`) releve deux corrections appliquees dans le sprint (un
commentaire de test perime sur l'effectif du plus gros paquet, une affirmation historique fausse
dans `ADR-index.md`), et un signalement ouvert deja route a `nadia-prof` (chiffre perime dans
`FICHE-bandit-manchot-additions-cp.md`, herite de s13, sans lien avec ce sprint). Aucun tiret
cadratin, guillemet typographique, emoji ou chemin de poste trouve sur son perimetre.

Sa lecture s'arrete a `81a43ac`, avant le refus pedagogique, la correction du masquage et le
verdict final de `nadia-prof` (243 lignes ajoutees a `FICHE-barre-entree-niveau.md`). J'ai
complete ce point moi-meme plutot que de le laisser non couvert : recherche des memes motifs
interdits (tirets cadratins, guillemets typographiques, emoji) sur l'integralite du delta
documentaire produit depuis `81a43ac` (`CLAUDE.md`, `ADR-0022`, `ADR-index.md`,
`avis-faisabilite.md`, `confirmation-archunit.md`, `FICHE-barre-entree-niveau.md`) - **aucune
occurrence**. Ce n'est pas une relecture formelle au sens de la ceremonie (je n'ai pas verifie la
coherence terminologique ni la paraphrase de code sur ce delta), seulement un filet de securite
sur les regles de forme les plus mecaniques.

## Avis de `nadia-prof` et Done pedagogique

Deux verdicts le meme jour, sur `squad/conformite/FICHE-barre-entree-niveau.md`.

**Premier verdict, 2026-08-17 - REFUSE.** `marc-dev` avait declare BARRE-1 et BARRE-2 "tenues par
construction" en raisonnant sur le fragment servi ; `nadia-prof`, en mesurant l'ecran assemble
reellement rendu, compte **quatre** boites pressables sur l'ecran de confirmation (deux
attendues) et **trois** sur l'ecran d'erreur (une attendue) - l'en-tete permanent survit a ces
deux ecrans, et les puces de niveau avec lui. C'est la quatrieme instance nommee ce sprint d'un
garde-fou ou d'une mesure creditee d'une propriete qu'elle ne peut pas voir depuis le fragment
seul.

**Second verdict, 2026-08-17 (soir) - VALIDE SOUS RESERVE, sur S14-1 et S14-2.** Apres la
correction de `marc-dev` (marqueur `ecran-exclusif`, forme d'`ADR-0022` retenue par `theo-archi`),
`nadia-prof` rejoue les memes gestes et la meme commande, sans les prendre sur parole : la
confirmation porte exactement deux boites pressables, l'erreur exactement une. **Neuf conditions
sur neuf tenues** (BARRE-1 a BARRE-9), remesurees une par une. Rien des sept autres conditions
n'est entame par la correction - verifie, geometrie de la manche identique au pixel avant/apres.
S14-2 (retrait du lien "Les jeux") valide sans reserve propre.

Deux reserves neuves, non bloquantes : **S14-A**, une bande sombre vide de 16 px sur les deux
ecrans exclusifs (la regle masque la puce et non le bandeau, bon choix technique, effet
secondaire visuel accepte). **S14-B**, aucune puce ne se distingue jamais comme selectionnee et
rien ne nomme le filtre en cours.

**R-BARRE-3 maintenue non bloquante, corrigee par sa propre autrice.** Le cri d'alarme initial
("l'enfant perd la vue melangee sans retour") reste exact dans ce qu'il nomme, mais trois faits
remesures le degonflent : le retour n'est pas absent, il est immediat (la liste change sous le
doigt) ; l'enfant n'est jamais coince (sa puce reste a l'ecran) ; la vue melangee perdue est moins
bonne que celle qui la remplace. `nadia-prof` date sa condition de bascule : R-BARRE-3 devient
bloquante le jour ou un second axe de filtre s'ajoute (le filtre par matiere, differe a s15).

**Le motif du refus, etabli par `theo-archi`** : une seule assertion mal placee dans le garde-fou
d'`ADR-0022` (`masquages(...).isNotEmpty()`, qui exigeait que son propre mecanisme soit employe) a
produit toute la cascade - son retrait force par S14-2 a emporte la clause du marqueur, laissant
une clause orpheline. Reforme dans le troisieme amendement plutot qu'amendee une troisieme fois
par constat.

## Trois agents ont corrige leur propre chiffre contre eux-memes

- `theo-archi`, 506 contre le 507 de `marc-dev` (`ADR-0013` obligation 3 exige que le jeu fautif
  soit execute ; il avait compte un fixture gratuit et pas la methode qui le fait tourner).
- `nadia-prof`, sur sa prevision de cout pessimiste de 53 px (l'ecart mesure est de 13 px) et sur
  BARRE-3 qu'elle avait ecrite inapplicable.
- `theo-archi`, sur sa derivation d'impact ("j'ai derive le perimetre du geste du jour, pas celui
  de son inverse").

## Tableau des obstacles du sprint

| Description | Categorie | Destinataire | Statut |
|---|---|---|---|
| Ecart entre "toujours visible" et les conditions bloquantes BARRE-1/2/3 (confirmation et erreur exclues) | pedagogie / produit | l'utilisatrice | **remonte au planning et confirme par le verdict final**, restitue en tete de cette review |
| BARRE-1/BARRE-2 declarees tenues par construction sur le fragment, fausses sur le document assemble | technique / methode | `theo-archi`, `marc-dev` | **clos** - correctif livre (marqueur `ecran-exclusif`), remesure par `nadia-prof`, 9/9 conditions tenues |
| Une assertion du garde-fou d'`ADR-0022` (`masquages(...).isNotEmpty()`) a produit toute la cascade du sprint | technique | `theo-archi` | **clos** - clause reformee dans le troisieme amendement, test de methode ajoute ("si l'humain change d'avis, combien de fichiers de garde-fou bougent ? zero.") |
| `rapport-qualite.md` et `confirmation-archunit.md` s'arretent avant le correctif de masquage et le verdict final | technique / methode | moi-meme, cette review | **clos pour ce sprint par ma propre reverification sur `a7c7401`** ; voir "Ce que ce sprint suggere pour le process" en `retro.md` |
| `relecture.md` s'arrete avant le correctif de masquage et le verdict final (243 lignes neuves) | redaction / methode | moi-meme, cette review | **attenue** par mon filet de securite mecanique ci-dessus, pas remplace par une relecture formelle - **reste ouvert pour une relecture complete a s15** si un futur travail retouche ces fichiers |
| S14-A : bande sombre vide de 16 px sur les deux ecrans exclusifs | pedagogie | `camille-po` (story candidate) | **route, non bloquant** |
| S14-B : aucune puce ne se distingue jamais, aucun filtre nomme | pedagogie | `camille-po` (story candidate) | **route, non bloquant** - condition de bascule bloquante datee au jour du filtre par matiere (s15) |
| R-BARRE-1 (reserve J, grands nombres CM1) : +13 px mesures contre 66 px annonces, ouverte | pedagogie / produit | `camille-po` | **ouvert**, chiffre corrige par `nadia-prof` elle-meme |
| R-BARRE-4 : F11 (ecoute lecteur d'ecran) s'enrichit de deux puces sur deux ecrans sur cinq | pedagogie / accessibilite | aucun agent - hors de la portee de la squad | **ouvert, inchange** |
| Question de nomenclature de la matiere accentuee, avis consultatif de `theo-archi` non tranche | produit + technique | `camille-po`, `nadia-prof` | **route pour instruction de s15**, `nadia-prof` demande sa fermeture avant s15 |
| Residu de 11 mutations sur 121 qu'`ADR-0045` laisse sans automate | technique / pedagogie | `nadia-prof` | **pris sous deux clauses verifiables N-1, N-2**, ecrites dans la fiche |
| Garde-fou de provenance des classes amont (`-am`) | technique | `theo-archi` | **ouvert, non affecte** |
| Migration du marqueur d'ecran de jeu (`ecran-de-jeu`) vers un attribut | technique | `theo-archi` (mecanisme), `sofia-sm` (capacite) | **ouvert, non affecte**, differe depuis le declencheur de resolution de rendu |
| Resolution de navigateur d'`outils/mesurer-rendu.sh` (facteur de bus sur la mesure) | technique | `sofia-sm` (capacite) | **ouvert, non affecte** |
| Nom du marqueur `ecran-exclusif` a soumettre a `ines-design` | design | `ines-design` | **ouvert, non affecte** |
| Predicat `estRemplacant` d'`ADR-0042` toujours sans objet en code | technique | `theo-archi` (dette actee) | **ouvert, capacite non affectee ce sprint** (decision de `sofia-sm`, planning) |
| Reserve F11 (ecoute reelle au lecteur d'ecran) | pedagogie / accessibilite | aucun agent - hors de la portee de la squad | **ouvert, inchange depuis s13** |

## Couverture programme

**Aucune ligne de `squad/programme/MATRICE-couverture.md` n'est ouverte ni fermee par ce sprint.**
C'est un choix acte au planning (S14-1 et S14-2 sont une story de navigation transverse, aucune
ouverture de contenu pedagogique) et non une omission : `MATRICE-couverture.md` n'est pas touche.

## Decisions prises

1. **Le Done technique de l'increment fusionne (`a7c7401`) est atteste par ma propre execution de
   `mvn -B clean verify`**, et non par `rapport-qualite.md` ni par `confirmation-archunit.md`
   seuls : ces deux fichiers s'arretent avant le correctif de masquage et le verdict final de
   `nadia-prof`, et je ne fabrique pas de section de cloture a leur place ni au nom de leurs
   auteurs.
2. **Le filet de securite de redaction que j'ai passe sur le delta documentaire posterieur a
   `relecture.md` n'est pas une relecture formelle** : il ne couvre que les motifs mecaniques
   (tirets cadratins, guillemets typographiques, emoji, chemins de poste), pas la coherence
   terminologique ni la paraphrase de code. Je ne prononce donc aucune clôture de la relecture sur
   ce delta ; l'obstacle correspondant reste route, non bloquant pour cette review, a rouvrir si un
   futur sprint retouche ces memes fichiers.
3. **Aucune ligne de `MATRICE-couverture.md` n'est mise a jour par moi**, conformement au choix de
   perimetre du planning : ce sprint n'a ouvert aucun contenu pedagogique neuf.
4. **Le tableau des obstacles ci-dessus reprend et cloture les obstacles routes au planning qui
   sont tenus**, et ajoute les obstacles neufs apparus pendant le developpement (l'ecart de
   couverture des rapports de cloture, l'assertion mal placee d'`ADR-0022`). Rien n'est reouvert au
   perimetre gele du planning.
5. **Je ne prononce aucun Done pedagogique** : le verdict "VALIDE SOUS RESERVE" ci-dessus est celui
   de `nadia-prof`, restitue sans reformulation.
