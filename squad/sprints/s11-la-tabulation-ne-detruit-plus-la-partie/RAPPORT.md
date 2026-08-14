# [Sprint s11] Rapport de sprint - la tabulation ne detruit plus la partie, remede technique livre, dette pedagogique pas encore fermee

- **Debut** : 2026-08-14 11:14 (`planning.md`)
- **Fin** : 2026-08-14 16:28 (`review.md`)
- **Duree effective** : 5 h 14

## Increment livre

Une confirmation server-side s'interpose desormais avant tout abandon de partie en cours (bouton
"Les jeux", rechargement de page), quel que soit le dispositif d'entree, et un garde-fou de
`adapter-web` verrouille l'invariante qu'aucun premier arret de tabulation ne detruit plus une
partie ; **le remede technique et son garde-fou sont livres, mais la reverification au clavier
reel que `DEC-0026` exige comme condition de fermeture de sa dette n'est pas tracee sur le depot a
cette cloture**, et la dette reste donc ouverte.

## Decisions du sprint

| Reference | Titre | Type | Statut |
|---|---|---|---|
| DEC-0027 | Le retrait du bouton de sortie du fragment de manche reouvre l'arbitrage de S6-9 / ADR-0022 : arbitrage prealable de nadia-prof requis avant developpement | Produit | Acceptee, amendee le 2026-08-14 (arbitrage rendu : principe accepte sous six conditions PORTE-1 a PORTE-6, forme instruite refusee ; perimetre corrige a trois ecrans, chiffre du 2026-08-07 corrige a 109 x 53 px) |
| ADR-0038 | Interdire tout arret de tabulation destructeur a une tabulation d'un point de depart | Architecture | Accepte a s10 ; **amende le 2026-08-14 (s11)** - clause de concordance retiree, "deux ecrans" corrige en "cinq en clause 1, un en clause 2", quatre non-preuves relues (une etait fausse), regle executable livree par `marc-dev` |
| ADR-0022 | Un habillage permanent declare son masquage et l'ecran son marqueur | Architecture | Accepte a s06 ; **contexte amende le 2026-08-14** - chiffre perime corrige (109 x 53 px, pas 102 x 53 px), decision inchangee |

**Une decision creee et amendee (`DEC-0027`), deux `ADR` anterieurs amendes (`ADR-0038`,
`ADR-0022`) ce sprint.** Aucune section n'est laissee vide.

## Etat des deux Done

| Done | Juge par | Etat |
|---|---|---|
| Technique | CI (`mvn -B clean verify`) | **Vert.** Rejoue independamment sur l'arbre fusionne (`round-two` a `14cd800`) apres un `clean` complet : BUILD SUCCESS, six modules, **416 tests** (methode : somme des blocs `Results:` du reacteur Maven, identique a la somme de l'attribut `tests=` des rapports XML - les fichiers `.txt` de surefire/failsafe sous-comptent de 7 sur quatre classes qui melangent un champ `@ArchTest` et une methode `@Test`), **28 regles ArchUnit** inchangees, garde-fou de chemins en code 0. |
| Pedagogique | nadia-prof | **Non prononce sur l'increment livre.** Le seul verdict ecrit sur le disque est un arbitrage prealable du 2026-08-14, rendu avant la correction finale de forme (44 px) et de texte ("Si tu pars, ta partie s'arrête.") livree par `lea-dev` : forme acceptee sous condition, texte refuse sur quatre motifs. Aucun verdict posterieur a cette correction n'est trace. **Le declencheur de reouverture de `DEC-0026` n'est donc pas integralement satisfait** : deux de ses quatre conditions cumulatives (reverification au clavier reel, sur les 25 revelations et sur l'ecran de reprise) manquent leur preuve sur le depot, alors que la story qui la porte est livree et que le remede technique tient sur la CI. |

## Obstacles

**Resolus** :

| Description | Agent route | Decision | Clos le |
|---|---|---|---|
| Garde-fou de chemins rouge sur `planning.md` (deux occurrences, issues du brief de sofia-sm a elle-meme) | sofia-sm | corrige a la source par `2fa2df3`, garde-fou verifie vert avant toute fusion | 2026-08-14 |
| Retrait de la clause de concordance d'`ADR-0038`, apres trois mutations mesurees par theo-archi | theo-archi | amendement du 2026-08-14 a `ADR-0038` | 2026-08-14 |
| Chiffre perime "102 x 53 px" dans `ADR-0022` | theo-archi | amendement du 2026-08-14 a `ADR-0022` | 2026-08-14 |
| Arbitrage prealable de forme et de texte de l'ecran de confirmation, et de la sortie unique de l'en-tete (`DEC-0027`) | nadia-prof | arbitrages du 2026-08-14, fusionnes ; `DEC-0027` amendee | 2026-08-14 |
| Neuf worktrees de sprint laisses sur le disque | sofia-sm (casquette dev) | worktrees supprimes apres verification de lignee | 2026-08-14 |

**Encore ouverts** :

| Description | Agent route | Depuis | Ce qui manque |
|---|---|---|---|
| Reverification au clavier reel de `DEC-0026` sur l'increment corrige et livre (25 revelations, sept jeux, tirages repetes, plus l'ecran de reprise) | nadia-prof (reverification), sofia-sm (suivi) | 2026-08-14 | un fichier ou un commit qui la trace ; sans lui, la dette de `DEC-0026` n'est pas fermee |
| `RessourcesTiercesTest` reste vert alors qu'un JavaScript tiers d'interface, correctement trace, entre sous un nom absent de sa liste fermee | theo-archi | 2026-08-14 | derivation du perimetre au lieu d'une enumeration |
| `TexteAccentueDesGabaritsTest` : critere enumere sur des incidents clos, ne peut rien exiger d'un texte neuf | theo-archi | 2026-08-14 | meme remede que le point precedent, meme famille de defaut |
| Deux propositions de methode de `yanis-qa` routees a `theo-archi` a la cloture de s10 (outil de cycle de vie du serveur de mesure ; convention d'`ADR` nommant ce qu'une regle ne prouve pas) | theo-archi | 2026-08-13 | affectation a un sprint - reportees a s12 |
| Reserve O, seconde moitie, de `nadia-prof` (aucun chemin vers le catalogue ne preserve une partie en cours) | nadia-prof, camille-po | 2026-08-13 | question produit, non instruite |
| Reserve neuve de `nadia-prof` : le meme libelle "Les jeux" porte deux consequences selon l'ecran | camille-po | 2026-08-14 | non instruite |
| Aucun fichier de relecture dedie pour la troisieme fois consecutive (s09, s10, s11) | relecteur-pedagogique | 2026-08-14 | a surveiller, aucune regle nouvelle tiree de cette seule occurrence |

## Couverture programme

Inchangee par cette cloture : ni `DEC-0027`, ni les amendements d'`ADR-0038` et d'`ADR-0022` ne
font bouger de ligne. Aucun Done pedagogique n'est prononce ce sprint.

## Consommation de tokens

Voir `squad/sprints/s11-la-tabulation-ne-detruit-plus-la-partie/RAPPORT-tokens.md`, produit par
`python outils/rapport-tokens.py --sprint s11`.
