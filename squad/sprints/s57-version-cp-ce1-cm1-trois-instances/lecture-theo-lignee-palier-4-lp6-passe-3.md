# s57 palier 4 - lecture de `theo-archi` : lignee, test et ArchUnit de la troisieme passe de `LP6`

- **Date** : 2026-10-23, 10:13 a 10:35 (UTC+02:00), heure lue par `date`.
- **Auteur** : `theo-archi`, transverse. Suite de `lecture-theo-lignee-palier-4-lp6.md` (`630e15f7`) et de `lecture-theo-lignee-palier-4-lp6-reprise.md` (`816cfc68`), qui ne sont pas reecrites.
- **Arbre** : `round-two` a `a65341cb`. `LP6` : branche `s57-sofia-emc-ce1-04`, sommet `c975a7b8` (code `53f98a74`, rapport `c975a7b8`), `git status --short` du worktree vide. Rien n'est ecrit dans le worktree du lot.
- **Version** : JDK 21.0.10, propriete `java.version` des 25 rapports `TEST-*.xml` de la mesure 3.1, une seule valeur (`ADR-0071`). git 2.47.1.
- **Ce que ce fichier ne fait pas** : il ne fusionne rien, ne modifie pas `LP6`, ne juge aucun contenu, ne prononce aucun Done, ne cree ni n'amende aucune `ADR`. Les reperes `L`, `T`, `Y`, `A` reprennent ceux de la reprise ; `T5` est neuf.

## 0. Verdict

| Code | Point | Verdict |
|---|---|---|
| `L` | Lignee de `c975a7b8` | **saine** : `a65341cb` et `ebbb2136` ancetres (exit 0), `verifier-lignee.sh` exit 0, deux commits `[SOF]` lineaires sur `ebbb2136` |
| `T2` | Regle du lieu | **traite** : garde symetrique, il mord (mutation M1) ; marge nulle en portee *tous*, avis en 2.3 |
| `T3` | `chacun` dans `PARTAGES` | **traite** : retire, item 17 reecrit avec un possessif. Consequence en `T5` |
| `T4` | Marge du rapport | **traite** : rapport corrige (section 2 et 11.4), chiffres conformes a mon calcul |
| `Y` | Regex d'`INT-CE1-2` | **traite** : flexions et trait d'union couverts, sondes 46 a 49 ; l'exclusion de l'identifiant **n'ouvre pas de trou** (section 3) |
| `T5` | Mots de portee dans les scenes | **defaut du garde, neuf** : le paquet affirme *"aucune scene ne le porte"* pour *chacun, tous, toute* ; aucun test ne le tient (mutation M3 verte). Non bloquant pour le contenu actuel |
| `A` | ArchUnit et `ADR-0086` clause 1 | **vert** : 88 tests ArchUnit, 0 echec ; regles 41 / 38 / 16 inchangees ; clause 1 verte et **mord** le paquet (mutation M2) |

Aucune garde n'est affaiblie dans sa condition. Le retrait de `chacun` (`T3`) retire un refus incident, decrit en `T5`.

## 1. Lignee (`L`)

Lancees dans le worktree de la branche, 10:13 :

| Commande | Sortie | Code |
|---|---|---|
| `git merge-base --is-ancestor a65341cb c975a7b8` | (aucune) | **0** |
| `git merge-base --is-ancestor ebbb2136 c975a7b8` | (aucune) | **0** |
| `bash outils/verifier-lignee.sh` | `le worktree sur la branche 's57-sofia-emc-ce1-04' descend de la lignee de round-two (ancetre commun trouve dans son historique).` | **0** |
| `git merge-base round-two HEAD` | `a65341cb7fdc77ee6fbad40c7801ce6eff767318` | 0 |

`git log --format='%h %p %s' a65341cb..HEAD` : six commits `[SOF]`, chacun d'un seul parent ; les deux neufs sont `53f98a74` (parent `ebbb2136`) et `c975a7b8` (parent `53f98a74`). La troisieme passe s'ajoute au sommet lu en reprise, sans reecriture. `round-two` est toujours a `a65341cb`.

## 2. Le changement du test (`T`)

### 2.1 Diff

`git diff --numstat ebbb2136 c975a7b8` : 4 fichiers. Paquet (+25, -21), `PaquetCe1EmcInteretCommunTest.java` (+92, -6), jeu fautif (+70, -0), rapport (+107, -1). `git diff --name-only a65341cb c975a7b8 -- '*/src/main/java/*'` : vide. `git log --oneline a65341cb..c975a7b8 -- bootstrap/src/test/java/com/recreo/archi/` : vide.

Les 6 lignes retirees du test, toutes lues : l'ancien motif `MOTS_INTERDITS` (3 lignes, remplace par le motif elargi), le Javadoc et la ligne de `PARTAGES` (reprise sans `chacun`), l'appel du motif sur le fichier entier (remplace par le meme appel apres retrait de l'identifiant). Aucune autre condition ni aucun seuil ne bouge.

### 2.2 Les points de la reprise

`T2` **Traite.** `verifierQueLeLieuNeDecidePasDeLaPortee` applique le meme seuil (`3 * croisees >= taille`) a la regle *un mot de `PARTAGES` donne toute la classe*, calcule sur le texte de la scene. Mon calcul sur `c975a7b8` (script de scratchpad, meme liste, memes mots que `motsEntiers`) :

| Regle | Portee *un* (11) | Portee *tous* (13) | Total juste |
|---|---|---|---|
| possessif donne *une seule personne* | 6 croisees, seuil 4 | 7 croisees, seuil 5 | 11 / 24 |
| mot de `PARTAGES` donne *toute la classe* | 6 croisees, seuil 4 | **5 croisees, seuil 5** | 13 / 24 |

Ce sont les chiffres du rapport (11.4). Les deux indices croises : possessif et lieu (2 scenes, toutes deux *tous*), possessif seul (5 *un*, 5 *tous*), lieu seul (6 *un*, 6 *tous*), aucun des deux (0). Aucune regle sur ces deux indices ne depasse 13 / 24. L'item 7, que j'avais mute en reprise pour faire passer la regle du lieu a 13 / 13, porte desormais *"au mur de la salle"* : il n'est plus une scene croisee pour la regle du lieu, et le compte de 5 l'inclut deja.

Sondes 44 (trois scenes *un* sans lieu : 0 croisee) et 45 (trois scenes *tous* avec lieu : 0 croisee), chacune refusee avec son message de portee. Sur le paquet reel, mutation M1 (3.2) : rouge.

`T3` **Traite.** `PARTAGES` compte 12 mots, sans `chacun`. Aucune scene ne porte `chacun`, `tous` ni `toute` (`grep -i -E 'chacun|\btous\b|\btoute\b'` sur les lignes `enonce` du paquet : exit 1). L'item 17 devient *"Zoé distribue ses fiches pour la dictée."*, objet personnel par `ses`.

`T4` **Traite.** Le rapport, section 2, dit desormais marge nulle a `ebbb2136` dans les deux portees et nomme la correction ; la section 11.4 donne les marges du sommet, conformes au tableau ci-dessus.

`T5` **Defaut neuf : les mots de portee ne sont gardes par rien.** L'en-tete du paquet ecrit : *"Un mot qui dit la portee (chacun, tous, toute) n'est ni un lieu ni un objet : il n'entre pas dans la liste fermee et aucune scene ne le porte."* La seconde moitie est une regle sans test. Le garde d'echo ne prend que les racines de cinq lettres des propositions : il voit `toute`, pas `tous` ni `chacun`. Mutation M3 (3.2) : *"Samir apporte sa plante pour chacun."* et *"... à voix haute pour tous."*, deux scenes qui disent la reponse, **74 tests verts**.

C'est la consequence de `T3`, que j'ai demande : a `ebbb2136`, `chacun` comptait comme mot partage, si bien qu'une scene *un* a objet personnel qui le portait etait refusee par `verifierLaPortee`. Ce refus etait incident, pour un faux motif ; il disparait avec le retrait. Correction, a la main de l'autrice : une liste fermee de mots de portee interdits dans la scene (au moins `chacun`, `tous`, `tout`, `toute`, `toutes`), avec sa sonde dans le jeu fautif (`ADR-0013`). Le contenu actuel n'en porte aucun : non bloquant. Le moment (dans `LP6` avant fusion ou en suite) est a `sofia-sm`.

### 2.3 Avis sur la marge nulle (portee *tous*, 5 pour 5)

**Acceptable, sans changement.** Une marge nulle ne rend pas le garde plus faible : le seuil est celui de la fiche (un tiers), et le contenu le tient exactement. Elle a un cout, et un seul : toute retouche future qui ajoute un lieu de la liste a l'une des scenes 3, 11, 13, 17, 19 rougit, ce que M1 prouve. C'est le comportement attendu d'un garde-fou, pas un defaut. Relever le seuil ou demander une marge est une question de contenu : a `nadia-prof`, par `sofia-sm`, pas a moi.

## 3. Regex d'`INT-CE1-2` et exclusion de l'identifiant (`Y`)

Motif du sommet : `(?<!\p{L})(intérêts?[\s‐‑-]+(général|généraux)|biens?[\s‐‑-]+communs?)(?!\p{L})`, memes drapeaux. Sondes 46 (*biens communs*), 47 (*bien-commun*), 48 (*intérêts généraux*), 49 (*intérêt-général*), chacune refusee sur le chemin des champs lus. Le chemin du fichier entier est mordu par `leFichierQuiCiteLIdentifiantDuPaquetDesBiensNEstPasRefuseEtSonPlurielEcritEnCommentaireLEst`.

**L'exclusion n'ouvre pas de trou.** Raisons, puis preuve :

1. `champsLus()` (enonce, propositions, indice) est verifie **sans** retrait. Un identifiant ecrit dans un champ lu par l'enfant rougit toujours.
2. Le retrait ne porte que sur la chaine exacte `ce1-emc-biens-communs`, sensible a la casse. Ce qu'il efface ne contient qu'une expression interdite, la sienne : une autre expression ne peut s'y cacher que si elle chevauche l'identifiant, et le seul chevauchement possible est *biens-communs* lui-meme.
3. Le retrait peut **creer** un refus (deux morceaux recolles), jamais en supprimer un autre.

Rejoue tel quel sur JDK 21.0.10 (`java.util.regex`, programme de scratchpad, rien de commite), le paquet reel lu depuis la copie de `c975a7b8` :

| Texte | Resultat |
|---|---|
| paquet reel, avec retrait | accepte |
| paquet reel, motif brut sans retrait | **refuse** (l'exclusion est necessaire) |
| `# CE1-EMC-BIENS-COMMUNS` | refuse |
| `# emc-biens-communs`, `# ce1-emc-bien-commun`, `# les biens-communs` | refuse |
| `# ce1-emc-biens-communs biens communs` | refuse |
| `# bien ce1-emc-biens-communs commun` | refuse (recollement) |
| `# ce1-emc-biens-communs-bis`, `# xce1-emc-biens-communs` | accepte |
| `bien` + U+00A0 ou U+202F + `commun` | refuse |
| `bien` + U+2013 + `commun`, `bien_commun` | accepte |

Les deux lignes acceptees de l'avant-derniere ligne sont des identifiants dans un commentaire, que l'enfant ne lit pas : c'est le perimetre voulu. La derniere ligne n'est pas l'orthographe du terme ; je ne la demande pas.

## 4. ArchUnit et `ADR-0086` clause 1 (`A`)

### 4.1 Mesure

Copie propre de `c975a7b8` par `git archive`, dans mon repertoire de scratchpad (RF7). 10:19:23 a 10:24:35 :

```
bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content,adapters/adapter-web,bootstrap -am test \
  -Dtest=<perimetre> -Dsurefire.failIfNoSpecifiedTests=false
```

**Code 0, `BUILD SUCCESS`.** Comptes lus dans les `TEST-*.xml` (`tests`, `failures`, `errors`, `skipped`), pas dans la console.

| Perimetre | Tests | Echecs, erreurs, ignores |
|---|---|---|
| 15 classes ArchUnit (liste de `lecture-theo-lignee-palier-4.md` section 2) | **88** (2, 5, 2, 2, 2, 13, 3, 4, 4, 5, 1, 2, 39, 2, 2) | 0, 0, 0 |
| Sept gardes partages : `LeRang...` 28, `LesFormesAcceptees...` 4, `ChaquePaquetDeMathematiquesDeCe1...` 14, `TexteAccentue...` 10, `LaFormeDesPropositions...` (`ADR-0086`) 9, `LesGardeFousSontMisALEpreuveTest` 10, `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest` 3 | **78** | 0, 0, 0 |
| `ChaqueJeuDuCatalogueDureDixManchesSaufDerogationNommeeTest` | 7 | 0, 0, 0 |
| `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` | 10 | 0, 0, 0 |
| `PaquetCe1EmcInteretCommunTest` | **74** (69 a `ebbb2136`, +5 methodes `@Test`) | 0, 0, 0 |

Compte de regles (methode d'`ADR-0104` : `git grep -c`, motifs `static final ArchRule` et `@ArchTest`, fichiers par `git grep -l`) : **41, 38, 16** a `a65341cb` et a `c975a7b8`, ecart nul.

Ce que cette mesure n'est pas : le `verify` complet de la fusion (RF4).

### 4.2 Clause 1 et mutations

Regroupement par troisieme proposition et bonne reponse, lu sur le sommet : *quelques* 5 / 3, *deux* 4 / 4, *trois* 4 / 4 (*toute la classe* / *une seule personne*). Inchange depuis `ebbb2136` : la troisieme passe ne touche ni les bonnes reponses ni les distracteurs. Chaque moule porte les deux bonnes reponses.

Les trois mutations, sur la copie, par remplacement d'octets (fins de ligne CRLF conservees, `diff` ne rend que les lignes visees), fichier restaure et `cmp` identique apres chacune :

| | Mutation | Commande | Resultat |
|---|---|---|---|
| M1 | item 3 : *"... pour la leçon de sciences dans la salle."* | `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test -Dtest=PaquetCe1EmcInteretCommunTest -Dsurefire.failIfNoSpecifiedTests=false`, 10:28:14 a 10:28:33 | **exit 1**, 74 tests, 1 echec : `int1LeLieuNeDecidePasDeLaPorteeDansAucuneDesDeuxPortees`, *"dans la portée tous, 4 scènes sur 13 ... le lieu donne la réponse"* |
| M2 | items 6, 12, 18 : *"pour toute la classe"* et *"pour une seule personne"* echanges (12 lignes de `diff`) | `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest -Dsurefire.failIfNoSpecifiedTests=false`, 10:29:31 a 10:29:51 | **exit 1**, 9 tests, 1 echec : *"les items [1, 6, 7, 12, 13, 18, 19, 24] presentent le meme moule ... (ADR-0086, clause 1)"* |
| M3 | item 3 : *"... pour chacun."* ; item 11 : *"... à voix haute pour tous."* | meme commande que M1, 10:30:15 a 10:30:28 | **exit 0**, 74 tests, 0 echec (`T5`) |

## 5. Jobs de `ci.yml` lances pour ce fichier

- **`build`, mesures partielles** : sections 4.1 et 4.2, avec `-am` et leur perimetre nomme.
- **`chemins`** : sur ce fichier, avant commit. **`prefixes`** : sur ma branche, avant commit.
- **`build` complet, `package`, `outils`** : non lances. Le `verify` complet est la porte de la fusion (RF4).
- **Le Done technique n'est pas prononce par moi.**
