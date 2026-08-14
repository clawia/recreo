# [Sprint s10] Rapport de sprint - la forme du bandit manchot, livree avec un defaut assume et une porte pedagogique refusee

- **Fin** : 2026-08-14 10:24 (`review.md`)
- **Debut** : aucun horodatage officiel - pas de `planning.md` pour ce sprint (voir `retro.md`,
  point 3). Le premier commit `s10` fusionne sur `round-two` est horodate 2026-08-12 15:22:34,
  cite pour reperage, pas comme un debut de sprint.

## Increment livre

Le bandit manchot recoit sa forme visuelle definitive (cabinet, colonnes, levier, animation de
rouleau) sur les sept jeux du catalogue, avec une conjugaison etendue au futur et a l'imparfait ;
l'increment est livre malgre un Done pedagogique **refuse**, sur un seul motif restant (perte de
partie sans confirmation au clavier, sur deux ecrans), leve par decision expresse de l'utilisatrice
et non par une correction du defaut.

**Ce sprint n'est ni un succes ni un echec : c'est un increment techniquement solide, livre sous
un defaut assume et ecrit, apres deux tours de refus pedagogique honnetes.**

## Decisions du sprint

| Reference | Titre | Type | Statut |
|---|---|---|---|
| ADR-0033 | Un habillage ne change pas d'etat sous le doigt de l'enfant | Architecture | Accepte |
| ADR-0034 | Un corpus d'ecrans exerce les deux verdicts | Architecture | Accepte |
| ADR-0035 | Aucun texte rendu ne vit dans un sous-arbre masque | Architecture | Accepte |
| ADR-0036 | Une image maison entre par son chemin, jamais par son empreinte | Architecture | Accepte |
| ADR-0037 | Distinguer une dependance de test d'une ressource servie | Architecture | Accepte |
| ADR-0038 | Interdire tout arret de tabulation destructeur a une tabulation d'un point de depart | Architecture | Accepte |
| ADR-0014 | Ressources tierces : webjar refuse, vendorisation exigee | Architecture | Accepte a l'origine ; **modifie ce sprint** - deux amendements de renvoi ajoutes par `ADR-0037`, rien retire |
| DEC-0022 | Perimetre du sprint 10 : le manche est dessine et immobile, trois demandes ecartees | Produit | Acceptee ; **partiellement remplacee** par DEC-0023 le meme jour |
| DEC-0023 | Le perimetre du sprint 10 est repris sur les references de l'utilisatrice | Produit | Acceptee sur les points 1 a 5 ; point 6 ouvert, non tranche |
| DEC-0024 | "Le bon petit mot" quitte le catalogue jouable | Produit, retrait | Acceptee |
| DEC-0025 | Levee de la condition M4 : le sprint 10 est livre avec un defaut de pli assume | Produit, levee | Acceptee ; **amendee le 2026-08-14** - chiffre des jeux fermes corrige (trois jeux, pas deux) |
| DEC-0026 | Levee de la clause 1 de M13, perte de partie sans confirmation | Produit, levee | Acceptee ; **amendee le 2026-08-14** - levee et declencheur etendus a l'ecran de reprise en plus de la revelation |

**Onze decisions actees ou modifiees ce sprint** (six `ADR`, cinq `DEC`), plus un `ADR` anterieur
(`ADR-0014`) modifie par renvoi. Aucune section n'est laissee vide : la liste ci-dessus est
complete au moment de cette cloture.

## Etat des deux Done

| Done | Juge par | Etat |
|---|---|---|
| Technique | CI (`mvn -B clean verify`) | **Vert.** Rejoue independamment sur l'arbre fusionne (`round-two` a `6d1ffba`) apres un `clean` complet : BUILD SUCCESS, six modules, **406 tests**, **28 regles ArchUnit** inchangees. Le test qu'un jar perime aurait rendu faussement vert (`LeLivrableNeSertQueLesRessourcesDeSesModulesIT`, `ADR-0037`) a ete rejoue sur le livrable reconstruit, pas sur un jar deja present. |
| Pedagogique | nadia-prof | **REFUSE.** Second verdict, non retire par cette cloture : un seul motif bloquant, M13 clause 1 sur l'ecran de revelation (la partie est detruite si et seulement si la reponse revelee occupe la derniere position de l'ordre de tabulation, mesure sans exception sur 25 revelations). Vingt-sept de ses trente-huit conditions initiales, plus onze conditions neuves emises pendant le sprint, sont tenues ou sans objet. L'increment est **livre malgre ce refus**, sur decision expresse de l'utilisatrice (`DEC-0026`), qui ne prononce ni ne simule aucun Done pedagogique. |

## Obstacles

**Resolus** :

| Description | Agent route | Decision | Clos le |
|---|---|---|---|
| `nashorn-core` engage sans arbitrage ecrit (levee de la reserve L de `nadia-prof` sans `ADR`) | theo-archi | `ADR-0037` | 2026-08-14 |
| Le defaut de tabulation mesure par `nadia-prof` sur la revelation est derivable sans navigateur (doctrine, pas encore le code) | theo-archi | `ADR-0038` | 2026-08-14 |
| Le meme defaut de tabulation touche aussi l'ecran de reprise, non couvert par `DEC-0026` a l'origine | camille-po (verification et amendement), theo-archi (mesure) | amendement du 2026-08-14 a `DEC-0026` | 2026-08-14 |
| Ligne de competences perimee dans `FICHE-bandit-manchot-conjugaison-present-cm1.md` | camille-po (correction), nadia-prof et une relecture (signalement independant) | correction fusionnee dans `s10-camille-fiche-conjugaison` | 2026-08-14 |
| Chiffre faux de `DEC-0025` (jeux fermes par le resserrement de marges) | camille-po (correction), yanis-qa (trouvaille) | amendement du 2026-08-14 a `DEC-0025` | 2026-08-14 |
| Proposition de `yanis-qa` : mutation-sur-materiau-reel systematique dans ses rapports de cloture | sofia-sm (arbitrage) | **ACCEPTEE**, `squad/README.md` amende | 2026-08-14 |
| Cinq worktrees de sprint laisses sur le disque (trois du premier lot, deux de cette fusion) | sofia-sm (casquette dev) | worktrees supprimes apres verification de lignee | 2026-08-14 |

**Encore ouverts** :

| Description | Agent route | Depuis | Ce qui manque |
|---|---|---|---|
| Regle executable et remede du defaut de tabulation (M13 clause 1, revelation et reprise) : la doctrine est ecrite (`ADR-0038`), le code est du au lot de s11 | theo-archi, un developpeur | 2026-08-14 | affectation a s11 (`DEC-0026` exige qu'au moins une des deux stories du backlog le soit) |
| Outil de cycle de vie du serveur de mesure, conforme a `ADR-0009`, avec cinq garanties nommees (fraicheur, isolement, exclusivite, arret garanti, tracabilite) | theo-archi | 2026-08-14 | avis de faisabilite puis affectation |
| Convention d'`ADR` : nommer explicitement ce qu'une regle ne prouve pas - pratique apparue trois fois ce sprint sans etre formalisee | theo-archi | 2026-08-14 | decision d'en faire ou non une doctrine ecrite |
| Trois stories de la dette de `DEC-0025` (raccourcir six jeux, reecrire les 25 indices de "Le bon verbe", l'ampoule M16 a M21) | camille-po (priorite), sofia-sm (planning) | 2026-08-13 | affectation a un sprint - aucune n'est planifiee a ce jour, l'ampoule est deja reportee vers s11 par le backlog |
| Reserve F11 de `nadia-prof` (emplacement accessible de l'enseigne) et sa reserve P (budget vertical d'un ecran a 360 x 640) | nadia-prof, ines-design | anterieur a s10 | une ecoute reelle au lecteur d'ecran (F11) ; un arbitrage de repartition qui n'existe pas encore (P) |
| Aucun fichier de relecture dedie pour la deuxieme fois consecutive (s09, s10) | relecteur-pedagogique | 2026-08-14 | a surveiller une troisieme fois avant de conclure qu'un gabarit de fichier obligatoire est necessaire |

## Couverture programme

Inchangee par cette cloture : ni `ADR-0037`, ni `ADR-0038`, ni les deux amendements de `DEC-0025`
et `DEC-0026` ne font bouger de ligne. `squad/programme/MATRICE-couverture.md`, section "Synthese
apres le retrait du sprint 10" (mouvement du a `DEC-0024`, anterieur a cette cloture) : **5
lignes `couvert-valide`, 3 `en cours`, 38 `non couvert`, sur 46**.

## Consommation de tokens

**Aucun `RAPPORT-tokens.md` n'existe pour ce sprint, et ce n'est pas un chiffre a zero : c'est une
mesure impossible a produire, faute d'un `planning.md`.**

```
python outils/rapport-tokens.py --sprint s10
```

rend :

```
ignore s10-la-forme-du-bandit-manchot : pas d'horodatage de debut dans planning.md
Sprint inconnu : s10
```

Le script construit la fenetre temporelle d'un sprint exclusivement a partir de l'horodatage de
debut lu dans `planning.md` ; ce sprint n'en a pas (`retro.md`, point 3). Je n'ai pas fabrique de
`planning.md` pour satisfaire l'outil - meme raisonnement que pour les sprints 2 et 4, qui portent
la meme absence dans leurs propres `RAPPORT.md`.
