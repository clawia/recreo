# Rapport qualite - sprint 13 (grands nombres et chemin de catalogue)

- **Auteur** : yanis-qa
- **Date** : 2026-08-17
- **Worktree** : dedie, provisionne depuis `round-two` sur une branche neuve (`s13-yanis-qualite`),
  hors du depot principal ou `relecteur-pedagogique` travaille en parallele. Aucun push.
- **Commit audite** : `a948785`, tete de `round-two` au moment de la saisine.
- **Constat JDK** : `outils/verifier-jdk.sh` donne le meme constat que les sprints precedents sur ce
  poste - le `java` du `PATH` ne compile pas, le fichier de toolchains local est mal orthographie et
  n'est donc pas lu par Maven. Toutes les mesures ci-dessous sont prises avec `JAVA_HOME` pointe sur
  le JDK 21 nomme par ce script pour chaque commande, jamais ecrit dans un fichier commite.

**Etat de ce document** : rapport ecrit par sections au fil des verifications, pour ne rien perdre
d'une execution interrompue. Ce qui suit est etabli et rejoue par moi ; les sections marquees
"en cours" seront completees dans un commit suivant, jamais reecrites en silence.

## 1. Porte technique

```
mvn -B clean verify
```

**Resultat : BUILD SUCCESS**, six modules en SUCCESS, `git status --porcelain` vide avant la
commande.

| Module | Tests (agregat Maven, "Results:") |
|---|---|
| domain | 47 |
| application | 7 |
| adapter-web | 229 |
| adapter-content | 120 |
| bootstrap (surefire) | 80 |
| bootstrap (failsafe) | 6 |
| **total** | **489** |

**489 confirme le chiffre annonce dans la saisine**, sur un `mvn -B clean verify` complet - jamais
une mesure incrementale (`-pl` seul sans `-am`).

**28 regles ArchUnit, confirme.** Methode : `git ls-files '*.java'` filtre sur `src/test/java` des
cinq modules du pom agregateur, recherche du motif litteral `ArchRule <nom> =`. Brut : **30**
occurrences. Deux vivent dans le faux depot de contre-exemple de
`LesGardeFousSontMisALEpreuveTest` (`bootstrap/src/test/resources/.../RegleContreExemple.java`),
chemin qui contient la sous-chaine `src/test/java` sans etre un module source compile : **30 - 2 =
28**, meme methode que `theo-archi` en confirmation-archunit.md et que les sprints precedents.

**Garde-fou de chemins**, rejoue avant d'ecrire cette section :
```
git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' | bash .github/scripts/verifier-chemins.sh
```
resultat a completer dans la section technique finale (execution en cours au moment de la coupure -
voir section 6, "ce qui reste a faire").

**Tests instables** : aucun constate sur cette premiere execution complete.

**Un ecart de publication, releve et non corrige par moi** : `confirmation-archunit.md` de ce
sprint est date du 2026-08-15, a l'ouverture (`9430cfd`), et `theo-archi` y ecrivait explicitement
qu'il l'amenderait "a la review, sur l'arbre fusionne, section ajoutee et non reecrite". A la tete
`a948785`, ce fichier compte toujours 143 lignes et un seul commit (`f9c5b57`) : aucune section de
cloture n'y a ete ajoutee. Je ne le corrige pas a sa place - ce n'est pas mon role - mais je le
nomme : le planning de `sofia-sm` faisait de cette republication un critere de cloture explicite.

## 2. Mutation sur un garde-fou neuf : la regle de la voie 4 de `lea-dev`

Regle mise a l'epreuve, appliquee au garde-fou cite par la saisine comme fermant les 29
debordements : `TailleDesTextesEtBoutonsTest.leConteneurSeReplieOuLaColonneGardeUneBaseNonNulle`
(`adapters/adapter-web`). Deux mutations, sur le fichier de production reel
(`static/css/recreo.css`), jamais une fixture ecrite pour l'occasion ; `git status --porcelain`
verifie vide avant la premiere et apres chaque restauration.

| # | Mutation | Resultat | Restaure |
|---|---|---|---|
| 1 | `.propositions` : `flex-wrap: wrap` retire ; `.proposition` : `flex: 1 1 auto` remplace par `flex: 1 1 0` - l'etat exact de la production a `34abbb7`, celui que `nadia-prof` a refuse le 2026-08-16 | **rouge**, 1 echec sur 26 : `[.propositions ne declare pas flex-wrap: wrap et .proposition fixe sa base de flexibilite a une longueur nulle (0) : c'est exactement la forme du defaut refuse par nadia-prof le 2026-08-16 ...]` | oui |
| 2 | couple garde intact (`flex-wrap: wrap` present, base `auto`), `white-space: nowrap` ajoute a la regle `.proposition { font-size: 2rem; }` | **vert a tort** - 26/26, aucun echec | oui |

`git diff --stat` vide et `mvn -B -pl adapters/adapter-web -am test -Dtest=TailleDesTextesEtBoutonsTest`
de nouveau a 26/26 apres chaque restauration.

**Ce que la mutation 1 etablit** : le garde-fou mord bien sur l'etat reel qui a motive son
ecriture, pas seulement sur la fixture de contre-exemple deja presente dans le depot
(`conteneur-rigide-et-colonne-a-base-nulle`, qui porte deja mot pour mot ce meme etat en
commentaire). Rejoue sur le fichier de production lui-meme, la mutation confirme la revendication de
la saisine ("theo-archi a applique le correctif ... pas un test n'a bouge") dans l'autre sens : le
defaut retire pour de vrai fait rougir pour de vrai.

**Ce que la mutation 2 etablit, et qui repond directement a la verification 4** : la regle ne garde
qu'un couple precis (conteneur non replie + base de colonne nulle), exactement comme son propre
commentaire Javadoc le dit ("elle garde une cause, pas un effet"). Un debordement reintroduit par un
tout autre chemin - ici `white-space: nowrap`, qui empeche le texte d'un bouton de passer a la ligne
et le fait deborder de sa colonne dans un navigateur reel - laisse cette regle entierement verte.
Aucun test de ce depot ne peut le voir : `ADR-0025` interdit un moteur de rendu, donc rien
n'automatise la mesure visuelle qui verrait l'effet. Seule `nadia-prof`, en jouant, le verrait.

## 3. Ce qui reste a faire (sections a completer dans le commit suivant)

- Le quatrieme axe de rang (recherche dans le depot en cours).
- Verification 1 : les quatre tests au motif fragile. **Deja identifie** : quatre classes de test
  d'`adapter-content` (`PaquetCm1MesuresTest`, `PaquetCm1PerimetreTest`,
  `PaquetCpComplements10S9Test`, `PaquetCpSoustractionsS9Test`) appellent
  `new YamlContentPackLoader().chargerPaquet("<nom-litteral>")` directement, sans passer par le
  catalogue. Reste a verifier si ce motif rougit reellement au retrait du paquet ou seulement au
  retrait du fichier YAML, et a le rapprocher du cas reel de `cm1-grands-nombres` (section 4).
- Verification 2 : le couple retrait-de-catalogue / methode-de-test. **Fait etabli, a detailler** :
  commit `a9a41aa` (`marc-dev`) retire `leJeuDuCatalogueChargeBienLePaquetCm1GrandsNombres` avec
  l'entree de catalogue (`DEC-0031`) ; commit `a948785` (`sofia-sm`) restitue l'entree et la
  methode dans le meme geste, apres que seule la reinstallation manuelle de `nadia-prof` avait
  revele l'absence (rien dans la suite ne le disait : 488 tests avant et apres son depart, sans
  ecart).
- Verification 3 : chiffre de VR-4 (huit items sous le pli).
- Verification 5 : deux chiffres verifies au hasard parmi ceux non corriges.
- Rejeu final du garde-fou de chemins et cloture de la section 1.
