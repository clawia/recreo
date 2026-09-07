# s31 - confirmation de la suite, `theo-archi`

**Un "je pense que c'est bon" ne vaut rien.** Ce fichier porte le resultat des commandes, leur
perimetre et leur date.

## 0. Ou et sur quoi

| | |
|---|---|
| Worktree de travail | `s31-theo`, branche `s31-theo`, cree sur `2ef583ba` |
| Worktree de reference | worktree detache que **j'ai cree moi-meme** sur `2ef583ba`, pour mesurer l'avant par la meme methode que l'apres. Aucun worktree d'un autre agent n'est lu ni balaye |
| Commande | `bash outils/mvn-jdk21.sh -B clean verify` sur la racine, les deux fois |
| JDK | **21.0.10**, version rapportee par la machine virtuelle qui execute les tests, pas par l'entree de toolchain qui la designe (`ADR-0071`) |
| Date | 2026-09-07 |

**Aucune mesure de ce document n'est partielle** : le reacteur est complet par construction sur la
racine, donc la clause du `-am` de `CLAUDE.md` ne s'applique a aucune ligne ci-dessous.

## 1. Porte technique - reacteur complet, les deux etats

**`BUILD SUCCESS` dans les deux cas.**

| Module | Moteur | `2ef583ba` (avant) | `2ef583ba` + ce lot |
|---|---|---|---|
| `recreo-domain` | surefire | 99 | 99 |
| `recreo-application` | surefire | 13 | 13 |
| `recreo-adapter-web` | surefire | 388 | 388 |
| `recreo-adapter-content` | surefire | 617 | 617 |
| `recreo-bootstrap` | surefire | 198 | **209** |
| `recreo-bootstrap` | failsafe | 6 | 6 |
| **Total** | | **1321** | **1332** |

*Methode du total : somme des six blocs `Results:` du journal de `mvn -B clean verify`, deux moteurs
comptes separement parce qu'un rapport texte n'en garde qu'un.*

**Le chiffre d'avant, 1321, est celui annonce a la cloture de s30, et je l'ai remesure plutot que de
le reprendre.** Il tombe juste au test pres.

### Le delta se ferme exactement, et je l'ai verifie parce qu'il ne se fermait pas de tete

**+11 tests.** Ma premiere redaction annoncait +14, de memoire et non de mesure ; le compte reel des
methodes `@Test` de mes deux fichiers est **4 + 7**, pas 7 + 7. **1321 + 4 + 7 = 1332.**

| Fichier neuf | Tests |
|---|---|
| `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` (`ADR-0085`) | **4** |
| `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` (`ADR-0086`) | **7** |

*L'erreur avait ete ecrite dans trois fichiers avant d'etre trouvee par cette confirmation. Elle est
corrigee partout. C'est exactement la raison d'etre de cette etape.*

## 2. Compte des regles ArchUnit - **33, inchange**

*Methode : `grep` des declarations `ArchRule` dans les sources de test des cinq modules, arbres de
contre-exemple exclus, sur les deux etats.*

| Etat | Declarations `ArchRule` | Tests de `LesReglesMordentTest` |
|---|---|---|
| `2ef583ba` | **33** | **33** |
| `2ef583ba` + ce lot | **33** | **33** |

**Les deux garde-fous de ce lot ne sont pas des regles ArchUnit, et ce n'est pas un manque.**
ArchUnit lit du bytecode : un identifiant ecrit dans un texte de gouvernance et la fabrication des
propositions d'un paquet YAML n'y sont pas des proprietes lisibles. Ce sont des balayages de
sources, sur les precedents de `ToutesLesReferencesDeDecisionResolventTest` et de
`ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest`. **Chaque `ADR` de ce lot le dit dans sa
section `Regle executable`**, plutot que de laisser croire au lecteur que le compte a bouge.

**Les deux methodes concordent** - 33 declarations, 33 morsures - ce qui n'etait pas acquis :
`LesReglesMordentTest` apparait **deux fois** dans le journal, a 1 test puis a 33, sur les deux
etats. Un releve qui n'aurait garde que la premiere occurrence aurait annonce **1**.

## 3. Ce que les deux garde-fous neufs ont trouve

| Garde-fou | Ce qu'il a trouve | Etat |
|---|---|---|
| `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` | **un defaut dans sa propre source**, a sa premiere execution : trois identifiants de son depot fautif ecrits dans ses assertions et alloues nulle part | corrige par le trou derive d'`ADR-0066`, repris tel quel |
| Le meme | **`ADR-0085` lui-meme**, dont la premiere redaction epelait quatre identifiants d'exemple inexistants | prose corrigee ; la contrainte est ecrite dans les consequences de l'`ADR` |
| `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` | rien sur le contenu servi, hors le paquet deja sous reserve `FOP-R4` et exempte sous cliquet a plafond exact de 1 | conforme |

## 4. Un defaut trouve hors suite, dans un fichier dont je suis le gardien

`ADR-index.md` portait **`ADR-0064` et `ADR-0065` sur la meme ligne physique** - un saut de ligne
manquant depuis le 2026-08-30. `ADR-0065` **ne rendait donc pas comme une ligne de la table** depuis
huit sprints. Corrige dans ce commit.

*Verification apres correction, methode : extraction des identifiants en tete de ligne de la table
et comparaison avec les noms de fichiers de `squad/decisions/adr/`.* **86 lignes d'index, 86
fichiers, aucun doublon, aucun absent.**

**Aucun automate ne voyait ce defaut**, et aucun ne le verrait demain :
`ToutesLesReferencesDeDecisionResolventTest` verifie qu'une reference **resout**, pas qu'un `ADR`
**figure** a l'index. **Dette ouverte a mon nom, datee du 2026-09-07** (avis de faisabilite,
section 5).

## 5. Verdict

**Porte technique verte.** `mvn -B clean verify` sur le reacteur complet, **1332 tests, 0 echec, 0
erreur**, `BUILD SUCCESS`, JDK 21.0.10, worktree `s31-theo` a `2ef583ba` plus ce lot.

**Le Done pedagogique n'est pas concerne par ce lot** : il ne touche **aucun octet de contenu, aucun
gabarit, aucun texte lu par un enfant**. Les fichiers ajoutes sont un registre de gouvernance, deux
fichiers de test, leurs arbres de contre-exemple, deux `ADR` et l'index. **Et je ne le prononcerais
pas de toute facon : il appartient a `nadia-prof`, et a elle seule.**
