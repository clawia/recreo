# Audit de `nadia-prof`, s55, instance A - resume

`nadia-prof`, instance A, le 2026-10-08. Worktree `s55-nadia-audit-a` a `fa4ddc7e` (`round-two`, qui contient
`c9c80937`, `2aa1e03a`, `f53f5c47`). Les verdicts et leurs motifs sont dans les fiches ; ce fichier les
resume et ne les remplace pas.

## Verdicts

| Story | Ligne | Fiche | Verdict | Mouvement de matrice possible |
|---|---|---|---|---|
| `S55-BR-FRA-CE1-L1` | `FRA-CE1-14` | `squad/conformite/FICHE-bandit-manchot-accord-sujet-verbe-ce1.md` | **VALIDE SOUS RESERVE** : `ACV-CE1-R1`, `ACV-CE1-R2` | `couvert-valide`, sans mention |
| `S55-BR-FRA-CE1-L2` | `FRA-CE1-12` | `squad/conformite/FICHE-bandit-manchot-classes-de-mots-ce1.md` | **VALIDE SOUS RESERVE** : `CLM-CE1-R1` | `couvert-valide`, sans mention |
| `S54-ART-RES-R1` a `R3` | `ART-CP-10` | `squad/conformite/FICHE-bandit-manchot-familles-d-oeuvres-cp.md` | **VALIDE**, reserves 1 a 3 levees | aucun : `ART-CP-10` reste `non couvert` |

La matrice et le backlog sont a `camille-po`.

## Ce que j'ai fait

- `bash outils/mvn-jdk21.sh -B clean verify` sur la racine du worktree : BUILD SUCCESS, Java 21.0.10. **Job
  `build` seul lance par moi** ; `prefixes`, `chemins`, `outils` non lances par moi sur l'increment (les
  rapports de `marc-dev` et `lea-dev` les declarent verts sur leurs branches).
- Ressources du jar identiques a l'arbre (`cmp`). Jar servi par mon processus, port dedie, arrete par PID.
- Joue par les routes servies : 40 parties pour chacun des deux jeux de CE1, 30 pour le jeu de CP ; tous les items
  tires, chacun avec des erreurs volontaires et au moins une revelation ; aucune anomalie de service. Rendu non
  vu au navigateur (HTML lu).

## Points routes

| Point | Decision |
|---|---|
| `K1` a `K3` | appliques mot pour mot ; tenus, sauf l'item 6 |
| `E1` | **texte non accepte sur l'item 6**. Le defaut est dans mon texte, pas dans l'application. Remplacement exact dans la fiche, `ACV-CE1-R1` |
| `W1` | non pris ; `W2` non fait, comme demande ; aucune reserve |
| `K4`, `K5` | appliques mot pour mot ; tenus (prenoms 7 feminins, 6 masculins) |
| `W3` | rien n'a change : reserve `CLM-CE1-R1`, comme annonce |
| `W4` | non pris ; aucune reserve |
| `E3` | commentaire et en-tete de l'item 18 : adjectif attribut, pas apres le nom. Textes exacts dans la fiche, au developpeur ; pas une reserve |
| `S54-ART-RES-R1` a `R3` | trois textes appliques mot pour mot ; trois reserves levees |
| Mesure annoncee par mon arbitrage (section 4, *-ent*) | 1,9 manche sur 10 mesuree pour la forme muette comme bonne reponse : reserve neuve `ACV-CE1-R2`. Mon chiffre de *"4 manches sur 10"* est corrige dans la fiche |

## Ce dont je reste la seule garde

Qu'un indice ne contient pas une proposition de son item (`ACV-CE1-R1`) ; qu'un indice departage (`CLM-CE1-R1`,
item 11 du jeu des familles) ; la justesse des formes et des classes ; la place de l'adjectif ; la part des
pluriels muets ; les roles par genre ; le genre des metiers. Les tests de paquet ne les portent pas.

## Capacite de l'instance A

Consomme ici : `-14` 1,0, `-12` 1,0, `ART-RES` 0,25. Aucun rejeu a s55 : la marge de rejeu de 0,5 n'est pas
utilisee. A reste a 4,0 sur 4,0 (`PAR-9`) avec les postes deja comptes (`DEC-0115`, amendement A.2 ;
complement de A, section 4). Les rejeux des reserves se paient au sprint qui les sert : `ACV-CE1-R1` 0,25
(mot pour mot), `ACV-CE1-R2` 0,5, `CLM-CE1-R1` 1,0 (`PAR-14`, les 24 indices changent).

## Identifiants

Trois neufs, alloues dans le meme diff : `ACV-CE1-R1`, `ACV-CE1-R2`, `CLM-CE1-R1`. Compte du registre porte de
924 / 158 a 927 / 158, chiffre lu sur `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` sur mon diff,
puis vert apres mise a jour.

**Signe** : `nadia-prof`, s55, instance A, 2026-10-08.
