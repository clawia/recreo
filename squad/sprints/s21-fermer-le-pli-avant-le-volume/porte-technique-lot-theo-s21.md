# Porte technique du lot de `theo-archi` - s21

- **Auteur** : `theo-archi`. **Date** : 2026-08-27.
- **Ce que ce fichier est** : la mesure de la porte technique **sur mon lot**, dans **mon**
  worktree. Ce n'est pas le `confirmation-archunit.md` du sprint : celui-la juge l'arbre fusionne et
  s'ecrit apres les fusions de `sofia-sm`. Un fichier qui pretendrait le contraire porterait une
  mesure vraie de mon lot et fausse du sprint - la faute que j'ai commise a s20 et que j'ai datee.
- **Arbre mesure** : branche `s21-theo`, worktree dedie hors de l'arbre du depot, forkee de
  `round-two` a `caad2f0`. **Trois lots** : `S21-1`, `S21-6`, `S21-5`.
- **Ce que ce fichier ne fait pas** : aucun Done, ni technique ni pedagogique - le technique est
  juge par la CI sur l'arbre fusionne, le pedagogique appartient a `nadia-prof` seule. Aucun statut
  de couverture.
- **Ce que je n'ai pas mesure** : l'arbre d'un autre agent. D'autres worktrees lancent Maven en
  parallele sur ce poste ; toutes les commandes ci-dessous sont lancees a la racine du mien, sans
  `-pl`, et un jar servi par un autre agent (port 8099, machine virtuelle 25.0.2) a ete laisse
  intact pendant tout le lot.

## 1. La commande, et son resultat

```
bash outils/mvn-jdk21.sh -B clean verify
```

Lancee a la racine du reacteur (six modules, **aucun `-pl`, donc aucune question de `-am`**), le
2026-08-27, sur `s21-theo` avec les trois lots. **`BUILD SUCCESS`, code de sortie 0,
`Total time: 01:58 min`.**

Blocs `Results:` releves **module par module dans le journal de cette commande**, jamais deduits
d'un total :

| Module | Bloc `Results:` |
|---|---|
| `recreo-domain` | `Tests run: 49, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-application` | `Tests run: 13, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-adapter-web` | `Tests run: 330, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-adapter-content` | `Tests run: 205, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-bootstrap` (surefire) | `Tests run: 98, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-bootstrap` (failsafe) | `Tests run: 6, Failures: 0, Errors: 0, Skipped: 0` |
| **total, somme des six blocs** | **701** |

**Delta avec l'etat de depart : +7, et il s'explique en entier.** Le meme `clean verify` sur
`caad2f0` sans mon lot, lance dans ce meme worktree le 2026-08-27, rend **694** avec la meme
repartition a une exception pres : `recreo-adapter-content` y vaut **198**. Les 7 tests d'ecart sont
ceux de `AuPlusTroisPropositionsParItemTest` (`ADR-0063`). Aucun autre module ne bouge, ce qui est
attendu : les lots `S21-1` et `S21-6` ne touchent aucun fichier Java.

## 2. Sur quelle machine virtuelle ces tests ont tourne - avec ce qu'il faut lire, et ce qu'il ne
faut pas

C'est la question exacte que la story `S21-6` corrige dans douze rapports anterieurs. Je l'applique
d'abord a moi-meme.

| Ce qui est mesure | Source primaire | Resultat |
|---|---|---|
| Machine virtuelle **de Maven** | premiere ligne du journal, ecrite par `outils/mvn-jdk21.sh` | `Maven lance sur le JDK de la toolchain jdk 21 : 25.0.2` |
| Toolchain **retenue par Maven** | `Found matching toolchain for type jdk`, puis `Toolchain in maven-compiler-plugin` et `Toolchain in maven-surefire-plugin`, une fois par module | une racine de **JDK 21.0.10** |
| Machine virtuelle **des tests** | proprietes du rapport surefire de `AuPlusTroisPropositionsParItemTest`, produit par cette execution | `java.version = 21.0.10`, `java.vm.version = 21.0.10+8-LTS-217` |

**La premiere ligne est celle qu'il ne faut pas lire comme une preuve** : `outils/mvn-jdk21.sh` lit
le fichier de toolchains de `${HOME}` du shell POSIX, Maven resout la sienne sur `${user.home}` de
la machine virtuelle Java, et sur ce poste ce sont deux fichiers declarant deux JDK. **Les tests de
ce lot ont tourne sur un JDK 21.0.10**, et ce sont les lignes du journal et les rapports surefire
qui l'etablissent, jamais la ligne de l'enveloppe. Le correctif de l'enveloppe reste hors perimetre
de s21 (decision 4 du planning) et il est route.

## 3. Les regles ArchUnit, avec leur methode - parce qu'un compte sans methode m'a deja fait mentir

**30 regles, inchange par ce lot.** Methode :
`grep -rn -E "ArchRule [A-Za-z_]+ *=" <module>/src/test` sur les cinq modules du reacteur, dans le
**seul worktree `s21-theo`** (`git worktree list` en compte d'autres, tous des lots d'agents, tous
exclus), le 2026-08-27. Le comptage brut rend **32** declarations ; **deux appartiennent au faux
depot de contre-exemple** de `LesGardeFousSontMisALEpreuveTest`
(`bootstrap/src/test/resources/contreexemple/.../faux-module/`), qui n'est pas du code de garde-fou
mais le materiau qu'un garde-fou juge. Reste **30**, dont **29** dans
`bootstrap/src/test/java/com/recreo/archi/` et **1** dans
`adapters/adapter-web/src/test/java/com/recreo/adapter/web/archi/`.

**`ADR-0063` n'ajoute aucune `ArchRule` et n'en revendique aucune** : ArchUnit lit du bytecode, et le
nombre de propositions d'un item est une propriete de la donnee servie, lue par le chargeur reel.
C'est un garde-fou de balayage, de la meme famille que `AucunePropositionNEstEgaleAUneAutreDansUnItemTest`.

## 4. Les juges de scripts, que `mvn verify` ne voit pas

| Juge | Resultat, 2026-08-27, worktree `s21-theo` |
|---|---|
| `bash squad/outils/nadia-prof/balayage-largeur-rangee-test.sh` | **29 cas, tous verts**, code 0 (fichier neuf, `S21-1`) |
| `bash squad/outils/sofia-sm/verifier-provenance-lots-test.sh` | 7 cas, tous verts, code 0 |
| `git ls-files ... \| bash .github/scripts/verifier-chemins.sh` | `Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies`, code 0 |
| essai a blanc du motif de balayage de l'etape `outils` de la CI | **6 juges purs** trouves sur les deux repertoires, contre 4 avant ce lot |

Les quatre juges de `outils/` sont executes par l'etape `outils` de la CI et ne sont pas relances a
la main ici ; ce lot n'y touche pas.

## 5. Ce que la porte technique ne dit pas

- Elle ne dit rien de la qualite pedagogique d'un increment. Aucun de mes trois lots ne livre du
  contenu, mais la phrase vaut quand meme : le Done pedagogique appartient a `nadia-prof` seule.
- Elle ne dit rien de l'arbre fusionne. Deux lots verts separement peuvent etre rouges ensemble ;
  c'est `sofia-sm` qui fusionne et la CI qui juge.
- Elle ne dit rien du pli. `ADR-0028` le declare non gardable, et ce lot corrige l'instrument qui le
  constate, pas la propriete.
