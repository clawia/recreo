# s57 palier 4 - lecture de `theo-archi` : lignee, test et ArchUnit de `LP6` repris apres le REFUSE

- **Date** : 2026-10-23, 09:01 a 09:20 (UTC+02:00), heure lue par `date`.
- **Auteur** : `theo-archi`, transverse. Suite de `lecture-theo-lignee-palier-4-lp6.md` (sommet lu `cbfb152e`), qui n'est pas reecrite.
- **Arbre** : `round-two` a `a65341cb`. `LP6` : branche `s57-sofia-emc-ce1-04`, sommet `ebbb2136` (code `ec2d572a`, rapport `ebbb2136`), `git status --short` du worktree vide. Rien n'est ecrit dans le worktree du lot.
- **Version** : JDK 21.0.10, propriete `java.version` des 25 rapports `TEST-*.xml` de la mesure 3.1, une seule valeur (`ADR-0071`). git 2.47.1.
- **Fiche lue** : `FICHE-bandit-manchot-interet-commun-ce1.md` au commit `705eb2b7` (branche `s57-nadia-p4-lp6`, non fusionnee), motif M1 et reserve 1.
- **Ce que ce fichier ne fait pas** : il ne fusionne rien, ne modifie pas `LP6`, ne juge aucun contenu, ne prononce aucun Done, ne cree ni n'amende aucune `ADR`. Les reperes `L`, `T`, `A`, `F`, `Y` n'appartiennent qu'a ce fichier.

## 0. Verdict

| Code | Point | Verdict |
|---|---|---|
| `L` | Lignee de `ebbb2136` | **saine** : `a65341cb` et `cbfb152e` ancetres (exit 0), `verifier-lignee.sh` exit 0, quatre commits `[SOF]` lineaires |
| `T` | Changement de `PaquetCe1EmcInteretCommunTest` | **accepte, aucune garde affaiblie** ; le garde neuf mord. **Deux defauts de perimetre** (`T2`, `T3`) et un chiffre faux dans le rapport (`T4`), section 2 |
| `A` | ArchUnit et `ADR-0086` clause 1 | **vert** : 88 tests ArchUnit, 0 echec ; compte de regles 41 / 38 / 16 inchange ; la clause 1 est verte et **mord** le paquet repris (section 3) |
| `F` | *pour tout le monde* | **ne pas l'ajouter** a l'ensemble ferme (section 4) |
| `Y` | Regex d'`INT-CE1-2` | **defaut du garde, a corriger** ; aucun defaut de contenu (section 5) |

## 1. Lignee (`L`)

Lancees dans le worktree de la branche, 09:01 :

| Commande | Sortie | Code |
|---|---|---|
| `git merge-base --is-ancestor a65341cb ebbb2136` | (aucune) | **0** |
| `git merge-base --is-ancestor cbfb152e ebbb2136` | (aucune) | **0** |
| `bash outils/verifier-lignee.sh` | `le worktree sur la branche 's57-sofia-emc-ce1-04' descend de la lignee de round-two (ancetre commun trouve dans son historique).` | **0** |
| `git merge-base round-two HEAD` | `a65341cb7fdc77ee6fbad40c7801ce6eff767318` | 0 |

`git log --format='%h %p %s' a65341cb..ebbb2136` : `e85a93fe` (parent `a65341cb`), `cbfb152e` (parent `e85a93fe`), `ec2d572a` (parent `cbfb152e`), `ebbb2136` (parent `ec2d572a`), tous `[SOF]`, chacun d'un seul parent. La reprise s'ajoute au sommet que j'avais lu, sans reecriture. La base de merge est le sommet courant de `round-two`.

## 2. Le changement du test (`T`)

### 2.1 Diff

`git diff --numstat cbfb152e ebbb2136` : 4 fichiers. Paquet (+82, -51), `PaquetCe1EmcInteretCommunTest.java` (+106, -22), jeu fautif (+91, -0), rapport (+98, -94). `git diff --name-only a65341cb ebbb2136 -- '*/src/main/java/*'` : vide. `git log --oneline a65341cb..ebbb2136 -- bootstrap/src/test/java/com/recreo/archi/` : vide. Lignes ajoutees portant `@Disabled`, `@Ignore`, `ArchIgnore`, `allowEmptyShould` ou `assume` : aucune (`grep` exit 1). Les fichiers partages lus en `lecture-theo-lignee-palier-4-lp6.md` 2.1 ne bougent pas depuis `cbfb152e`.

Lignes retirees du test contre `cbfb152e` (`git diff ... -- '*Test.java' | grep -E '^-[^-]'`), toutes lues : le Javadoc de classe et celui de `verifierLaPortee`, la liste `PARTAGES` (reprise avec deux mots de plus), les deux conditions de portee, et le message attendu de la sonde 06. Contre `a65341cb`, le test est neuf : rien n'est retire.

### 2.2 Lecture, point par point

`T1` **Aucune garde affaiblie.** Avant : portee *un* exige possessif et aucun mot partage ; portee *tous* exige un mot partage et aucun possessif. C'est exactement l'equivalence *possessif si et seulement si une seule personne* que M1 refuse ; la fiche le dit (*"la liste fermee du test qui lie possessif et portee un ... enferme le paquet dans la forme refusee"*). Apres : la portee reste liee a la bonne reponse ; l'appartenance est declaree a part et relue sur le texte (personnel : un possessif ; partage : un mot de `PARTAGES`, aucun possessif) ; portee *un* et objet personnel interdit toujours un mot partage (sonde 07, verte en 3.1). Ce qui est retire est la forme refusee, pas une protection. `PARTAGES` gagne `bibliothèque` et `robinet`, n'en perd aucun. La declaration `objet` absente fait rougir (sonde 41).

Le garde neuf `verifierQueLAppartenanceNeDecidePasDeLaPortee` calcule sur le texte, pas sur les declarations : une declaration mensongere ne le trompe pas. Il mord : sondes 42 et 43 (vertes en 3.1, chacune refusee avec son message), et les mutations A et B du rapport (une scene croisee de moins, rouge).

`T2` **Le garde neuf mesure une regle de surface, le paquet en porte une seconde, plus forte.** M1 nomme la propriete *"la forme donne la reponse"* ; le garde n'en calcule qu'une instance, la regle du possessif. Une regle qui repond *toute la classe* quand la scene nomme un mot de `PARTAGES` et *une seule personne* sinon, calculee sur les 24 scenes de `ebbb2136` (script de scratchpad, memes mots que `motsEntiers`, meme liste que le test) :

| Regle | Portee *un* (11) | Portee *tous* (13) | Total |
|---|---|---|---|
| possessif donne *une seule personne* (celle du garde) | 7 justes, 4 fausses | 8 justes, 5 fausses | 15 / 24 |
| mot de `PARTAGES` donne *toute la classe* | 7 justes, 4 fausses | **12 justes, 1 fausse** | **19 / 24** |

Le seuil du garde, applique a cette seconde regle, rougit en portee *tous* (1 sur 13, pour 5 exiges). La cause est dans le test lui-meme : la condition conservee *portee un et objet personnel : aucun mot partage* n'a pas de symetrique, si bien que 4 des 5 scenes *tous* a objet personnel nomment `salle` ou `tableau` (items 3, 11, 15, 19) et aucune des 7 scenes *un* a objet personnel ne le peut.

**Ce que le garde laisse passer, prouve sur le paquet reel** : copie de `ebbb2136`, item 7 *"Lina colle son affiche des horaires au mur."* devient *"... dans la salle."*, seule ligne du `diff`. La regle du mot partage passe a 13 / 13 en portee *tous*. `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test -Dtest=PaquetCe1EmcInteretCommunTest -Dsurefire.failIfNoSpecifiedTests=false`, 09:16:20 a 09:16:36 : **exit 0, 69 tests, 0 echec**. Fichier restaure, `cmp` identique.

Ce que je tranche : le garde tient ce qu'il dit, il ne tient pas tout M1. Correction possible, de la main de l'autrice : calculer le meme seuil sur la seconde regle. Elle rougirait sur le contenu actuel ; c'est donc une question de contenu, et si 19 / 24 est trop pour un enfant, c'est a `nadia-prof` de le dire, pas a moi. **Je le remonte a `sofia-sm`**, avec ce tableau, pour la lecture de `nadia-prof`.

`T3` **`chacun` est dans `PARTAGES` et n'est ni un lieu ni un objet.** Il dit la portee. L'item 17, *"Zoé distribue les feuilles à chacun."*, declare l'objet partage et n'est valide que par `chacun` (seul mot de la liste dans sa scene). `chacun` figure dans 4 scenes *tous* (13, 17, 21, 24) et dans aucune scene *un*. La liste etait deja ainsi a `cbfb152e` : ce n'est pas un affaiblissement, c'est une entree qui fait verifier l'appartenance par un mot de portee. A retirer de `PARTAGES` (l'item 17 doit alors nommer un objet partage), a la main de l'autrice.

`T4` **La marge annoncee par le rapport est fausse.** `rapport-sofia-dev-palier-4-lp6.md` section 2 : *"marge d'une scene en portee un ... et de deux en portee tous"*. Le seuil est `3 * croisees >= taille`. Portee *un* : 4 croisees, 3 rougit (12 >= 11, 9 < 11). Portee *tous* : 5 croisees, 4 rougit (15 >= 13, 12 < 13). La marge est **nulle dans les deux portees** ; les mutations A et B du meme rapport, rouges avec une scene de moins, le prouvent.

## 3. ArchUnit et `ADR-0086` clause 1 (`A`)

### 3.1 Mesure

Copie propre de `ebbb2136` par `git archive`, dans mon repertoire de scratchpad (RF7). 09:07:38 a 09:12:59 :

```
bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content,adapters/adapter-web,bootstrap -am test \
  -Dtest=<perimetre> -Dsurefire.failIfNoSpecifiedTests=false
```

**Code 0, `BUILD SUCCESS`.** Comptes lus dans les `TEST-*.xml` (`tests`, `failures`, `errors`, `skipped`).

| Perimetre | Tests | Echecs, erreurs, ignores |
|---|---|---|
| 15 classes ArchUnit (meme liste que `lecture-theo-lignee-palier-4.md` section 2) | **88** (2, 5, 2, 2, 2, 13, 3, 4, 4, 5, 1, 2, 39, 2, 2) | 0, 0, 0 |
| Sept gardes partages : `LeRang...` 28, `LesFormesAcceptees...` 4, `ChaquePaquetDeMathematiquesDeCe1...` 14, `TexteAccentue...` 10, `LaFormeDesPropositions...` (`ADR-0086`) 9, `LesGardeFousSontMisALEpreuveTest` 10, `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest` 3 | **78** | 0, 0, 0 |
| `ChaqueJeuDuCatalogueDureDixManchesSaufDerogationNommeeTest` | 7 | 0, 0, 0 |
| `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` | 10 | 0, 0, 0 |
| `PaquetCe1EmcInteretCommunTest` | **69** (65 a `cbfb152e`) | 0, 0, 0 |

Compte de regles (methode d'`ADR-0104` : `git grep -c`, motifs `static final ArchRule` et `@ArchTest`, fichiers par `git grep -l`) : **41, 38, 16** a `a65341cb` et a `ebbb2136`, ecart nul.

Ce que cette mesure n'est pas : le `verify` complet de la fusion (RF4).

### 3.2 Clause 1 sur le paquet repris

Regroupement des 24 items par troisieme proposition et bonne reponse, lu sur `ebbb2136` :

| Troisieme proposition | *pour toute la classe* | *pour une seule personne* |
|---|---|---|
| *pour quelques élèves* | 5 | 3 |
| *pour deux élèves* | 4 | 4 |
| *pour trois élèves* | 4 | 4 |

Chaque moule porte les deux bonnes reponses : la clause est tenue (9, 0, en 3.1).

**Elle mord ce paquet.** Sur la copie, les trois items *"pour quelques élèves"* a reponse *"pour une seule personne"* (6, 12, 18) recoivent *"pour toute la classe"*, la formule echangee en distracteur ; `diff` ne rend que ces six lignes, fins de ligne conservees. `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest -Dsurefire.failIfNoSpecifiedTests=false`, 09:15:32 a 09:15:57 : **exit 1, 9 tests, 1 echec**, message *"ce1-emc-interet-commun.yaml : les items [1, 6, 7, 12, 13, 18, 19, 24] presentent le meme moule de propositions et portent tous la meme bonne reponse "pour toute la classe" ... (ADR-0086, clause 1)"*. Fichier restaure, `cmp` identique.

## 4. *Pour tout le monde* (`F`)

**Avis : ne pas l'ajouter.**

1. **L'usage qui la motivait n'existe plus.** La reserve 1 de la fiche la demande *"sur les scenes de la cour"* de portee *tous*. A `ebbb2136`, la cour n'apparait que dans l'item 14 (*"Enzo boit au robinet de la cour."*), de portee *un*. Ajouter une formule sans scene qui la demande, c'est une abstraction pour un usage imagine.
2. **Ajoutee a cote de *pour toute la classe*, elle cree une forme que la clause 1 ne voit pas.** *pour tout le monde* et *pour toute la classe* comptent quatre mots et different en trois positions : ils ne coulent pas dans le meme moule. Les items qui la porteraient formeraient des moules a part, et la clause 1 ne mord qu'a partir de trois items par moule (`ADR-0086`, Javadoc de la clause). Une formule servie seulement la ou elle est juste, sur deux scenes par moule, serait une bonne reponse reconnaissable que ni la clause 1 ni le test ne refusent. Il faudrait alors au test une regle neuve : chaque formule de la famille *tous* est aussi servie en distracteur, et `FER-1` (chaque formule bonne reponse de cinq items au moins) reecrit pour trois formules.
3. **Si `nadia-prof` la prefere**, la forme sans piece neuve est la **substitution** sur les 24 items : l'ensemble ferme reste de deux formules, les trois moules restent les memes, la clause 1 garde la meme population. Le test change une constante (`FORMULES`) ; son garde d'echo calcule les mots pleins sur les propositions et interdirait `monde` dans les scenes et les indices sans modification. Ni `ADR-0086` ni la clause ne changent.

Choisir entre *toute la classe* et *tout le monde* est pedagogique : a `nadia-prof`, par `sofia-sm`.

## 5. Regex d'`INT-CE1-2` (`Y`)

Le motif du test est `(?<!\p{L})(intérêt\s+général|bien\s+commun)(?!\p{L})`, drapeaux `CASE_INSENSITIVE | UNICODE_CASE | UNICODE_CHARACTER_CLASS`. Rejoue tel quel sur JDK 21.0.10 (`java.util.regex`, programme de scratchpad, rien de commite) :

| Texte | Trouve |
|---|---|
| `bien commun`, `Bien commun`, `intérêt général`, `intérêt` + espace insecable + `général` | oui |
| `biens communs`, `bien communs`, `bien-commun`, `intérêts généraux`, `interet general` | **non** |

**Verdict : defaut du garde, a corriger.** L'arbitrage dit que ces termes *"ne sont pas lus par l'enfant"* : le pluriel et la forme a trait d'union sont le meme terme. Un perimetre enumere par formes exactes echoue par omission. Aucun defaut de contenu : `grep -i -E 'biens?[- ]+communs?|int[ée]r[êe]ts?[- ]+g[ée]n[ée]r(al|aux)'` sur le paquet ne trouve que la ligne 59 de l'en-tete, en commentaire, et le titre et la consigne du catalogue n'en portent aucun.

**Le piege, sur le materiau reel** : le garde lit aussi le fichier entier, commentaires compris. Elargi naivement (`biens?[\s-]+communs?`), il rougirait sur le paquet lui-meme, ligne 59, qui cite l'identifiant `ce1-emc-biens-communs` du paquet voisin. La correction doit couvrir les flexions et le trait d'union dans les champs lus par l'enfant, et soit exclure les identifiants de paquet de la lecture du fichier entier, soit faire citer le voisin autrement dans l'en-tete. Chaque forme ajoutee recoit sa sonde dans le jeu fautif (`ADR-0013`). La forme sans accent n'est pas a ajouter au garde : un texte d'enfant sans accents viole deja `DEC-0002`.

Je ne modifie pas le code de `LP6` : la correction est a l'autrice. Elle n'est pas bloquante pour le contenu actuel. Le moment de la faire (dans `LP6` avant fusion ou en suite) est a `sofia-sm`.

## 6. Jobs de `ci.yml` lances pour ce fichier

- **`build`, mesures partielles** : sections 2.2, 3.1 et 3.2, avec `-am` et leur perimetre nomme.
- **`chemins`** : sur ce fichier, avant commit. **`prefixes`** : sur ma branche, avant commit.
- **`build` complet, `package`, `outils`** : non lances. Le `verify` complet est la porte de la fusion (RF4).
- **Le Done technique n'est pas prononce par moi.**
