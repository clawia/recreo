# Releve du relecteur pedagogique - palier 2 de s57

Date : 2026-10-16. Perimetre : l'ecriture des espaces (U+00A0), des chevrons et de la ponctuation
dans trois textes destines a l'enfant, relue sur les commits, pas sur le poste. Le niveau scolaire
n'est pas juge (`nadia-prof`). Aucun fichier de contenu ni de code n'est modifie.

## 1. Verdict

| Texte | Commit | Verdict |
|---|---|---|
| N1 et N2, `cm1-sciences-extraire-information.yaml` | `69dd7631` | correct |
| indices des items 10 et 13, item 15 de `ce1-pronoms.yaml` | `b9f1c62f` | correct |
| item *chat*/*chaton* et 14 indices de `ce1-familles-de-mots.yaml` | `1febd011` | correct |

Aucune correction a appliquer. Un point a trancher hors perimetre (section 5).

## 2. La regle et la convention

- `DEC-0002` et son amendement du 2026-08-04 exigent un francais correct, accentue, a la
  ponctuation irreprochable. L'amendement ne dit rien de l'espace insecable.
- `ADR-0060`, clause 2 : un guillemet francais ouvrant est suivi de `U+00A0`, un fermant en est
  precede. La clause ne couvre pas l'espace avant `?` ou `:` (l'ADR l'ecrit lui-meme, "mauvaise
  consequence assumee 2"). La convention des paquets le fait.
- Les textes de `nadia-prof` dans `reponse-nadia-q-palier-1.md` et `reponse-nadia-q-palier-2.md`
  sont en espaces ordinaires : `grep -c` de `U+00A0` rend 0 dans les deux. Pour l'item 15 et
  l'item *chat*, elle ecrit elle-meme que les espaces sont "celles du paquet (U+00A0 dans les
  chevrons et devant `?`)" (`reponse-nadia-q-palier-2.md`, 3.5) et "celles des 13 autres enonces de
  famille" (3.6). Passer en `U+00A0` applique sa consigne, ne la modifie pas.

Verification du chiffre de `marc-dev`, `git grep` sur le sommet de `round-two`, dossier
`adapters/adapter-content/src/main/resources/contenu/*.yaml` :

```
git grep -h "«<U+00A0>" | wc -l   -> 562
git grep -h "« "        | wc -l   -> 3
git grep -h "«"         | wc -l   -> 565
```

Les 3 lignes a espace ordinaire sont des commentaires : `ce1-graphemes-complexes.yaml:46`,
`cm1-synonymes.yaml:23`, et la fin de la ligne 141 de
`cm1-histoire-geographie-nommer-en-geographie.yaml` (commentaire apres l'enonce). Aucun texte servi
a l'enfant n'a un chevron a espace ordinaire. Le chiffre est confirme : 562 contre 3.

## 3. Commandes et sorties par texte

Convention de lecture : `<NBSP>` marque un `U+00A0`, remplace par `sed` pour la lisibilite ; les
octets bruts sont donnes par `od -c` (`302 240` = `U+00A0`, `302 253` = `«`, `302 273` = `»`).

### 3.1 N1 et N2 (`69dd7631`)

```
git show 69dd7631:<contenu>/cm1-sciences-extraire-information.yaml | grep -n -E "«|»|que permet"
108:  - enonce: "D'après ce texte, que permet la chaleur retenue par cette couche d'air<NBSP>?"
112:    indice: "Relis la dernière phrase du texte, après les mots «<NBSP>ce qui permet<NBSP>»."
122:    indice: "Relis la deuxième phrase du texte, après le mot «<NBSP>aussi<NBSP>»."
123:  - enonce: "D'après ce texte, que permet ce transport de pollen à de nombreuses plantes<NBSP>?"
127:    indice: "Relis la dernière phrase du texte, après le mot «<NBSP>permet<NBSP>»."
```

Octets des trois chevrons (`grep -o` puis `od -c`) :
`302 253 302 240 c e ... t 302 240 302 273`, `302 253 302 240 a u s s i 302 240 302 273`,
`302 253 302 240 p e r m e t 302 240 302 273`.

Espace ordinaire restante dans un chevron ou avant `? : ; !` :
`grep -n -E '« |[^ ] »|[a-zé] [?:;!]'` rend 3 lignes (15, 16 et 23), toutes des commentaires de
tete en prose ASCII, aucune dans un texte servi.

Orthotypographie des chevrons de `S57-OBS-SCI-N1-N2` (action demandee par le planning avant rejeu) :
- chevrons francais `«` `»` (U+00AB, U+00BB), pas de guillemets droits dans le texte servi ;
- `U+00A0` apres `«` et avant `»` : 3 indices sur 3 ;
- point final apres le chevron fermant, hors des chevrons : correct (le point ne fait pas partie du
  mot cite) ;
- le mot cite est en minuscules et tel qu'il figure dans le texte (`ce qui permet`, `aussi`,
  `permet`) ;
- N1 : "que permet ce transport de pollen à de nombreuses plantes<NBSP>?" reproduit mot pour mot la
  forme recommandee par `nadia-prof` (`reponse-nadia-q-palier-1.md`, 4.1) ; accents et accord
  corrects ; `U+00A0` avant `?`.

Verdict : correct, rejeu possible sur ce point.

### 3.2 `ce1-pronoms.yaml`, items 10, 13 et 15 (`b9f1c62f`)

```
git show b9f1c62f:<contenu>/ce1-pronoms.yaml | grep -n -E "désigne un mot|Julie|Que désigne"
89:  indice: "«<NBSP>Elle<NBSP>» désigne un mot féminin<NBSP>: cherche-le dans la phrase d'avant."
107: indice: "«<NBSP>Elle<NBSP>» désigne un mot féminin<NBSP>: cherche-le dans la phrase d'avant."
115: (item 15) enonce: "Que désigne «<NBSP>il<NBSP>» dans le texte<NBSP>?"
116: texteSupport: "Le chat de Julie voit Elias. Il lui donne un biscuit."
118: distracteurs: ["le chat", "Julie"]
```

Octets de la ligne 89 : `" 302 253 302 240 E l l e 302 240 302 273 d 303 251 s i g n e ... f 303 251 m i n i n 302 240 : c h e r c h e - l e`.
Soit chevrons a `U+00A0` des deux cotes, `U+00A0` avant les deux-points, `é` en UTF-8 precompose.
`grep -v '^\s*#' | grep -c -E '« |[^ ] »|[a-zé] [?:;!]'` : 0 sur tout le fichier, hors commentaires.

- Texte de `nadia-prof` (`reponse-nadia-q-palier-2.md`, ligne 62) :
  `« Elle » désigne un mot féminin : cherche-le dans la phrase d'avant.` Meme texte, seules les
  espaces different, comme sa consigne le prevoit.
- Majuscule de `Elle` en tete d'indice : correcte (debut de phrase).
- Item 15 : enonce inchange et deja a `U+00A0` ; texte support, majuscules de `Julie`, `Elias`, `Il`
  et ponctuation corrects. Identique a l'encadre de 3.5.

Verdict : correct.

### 3.3 `ce1-familles-de-mots.yaml`, item livre et 14 indices (`1febd011`)

```
git show 1febd011:<contenu>/ce1-familles-de-mots.yaml | grep -n -E "chat|chaton|Le bon mot"
81:  - enonce: "Quel mot est de la même famille que «<NBSP>chat<NBSP>»<NBSP>?"
82:    bonneReponse: "chaton"
84:    indice: "Le bon mot garde un morceau et parle de la même chose."
```

Octets de l'enonce : `q u e 302 253 302 240 c h a t 302 240 302 273 302 240 ? "`.
Compteurs : `grep -c 'indice: "Le bon mot garde un morceau et parle de la même chose."'` rend 14 ;
`grep -c "même famille que «<NBSP>"` rend 14. Aucune espace ordinaire dans un chevron ou avant
`? : ; !` hors commentaires (sortie vide).

- Les 14 indices ne contiennent ni chevron, ni `?`, ni `:` : aucune espace insecable n'est attendue,
  il n'y en a pas. Phrase complete, majuscule et point, accent de `même` correct.
- L'enonce du nouvel item a la meme ecriture que les 13 autres (14 sur 14).
- Texte de `nadia-prof` (`reponse-nadia-q-palier-2.md`, 3.6) respecte au mot pres.

Verdict : correct.

## 4. Reponse a la question posee

Les trois ecritures sont correctes. `marc-dev` et `sofia-sm` n'ont pas altere les textes de
`nadia-prof` : ils ont suivi la convention du depot, que sa consigne ecrite designe elle-meme.
Un `U+00A0` est indiscernable d'une espace ordinaire a l'oeil, d'ou les commandes ci-dessus.

## 5. Point a trancher (hors perimetre de ces trois textes)

L'espace avant `?` n'est pas uniforme dans le depot. Sur le sommet de `round-two`, dans les lignes
`enonce:`, `indice:`, `consigne:` et `texteSupport:` de `contenu/*.yaml` :

```
git grep -h -E '^\s*(- )?(enonce|indice|consigne|texteSupport):.*<U+00A0>\?' | wc -l   -> 2377
git grep -h -E '^\s*(- )?(enonce|indice|consigne|texteSupport):.*[^ ] \?' | wc -l      -> 224
```

Une partie des 224 est de la notation mathematique (`a + ? = d`), le reste est une vraie phrase
interrogative a espace ordinaire (exemples : `cm1-anglais.yaml`, `cm1-homophones.yaml`,
`cp-comparer.yaml`). `ADR-0060`, "mauvaise consequence assumee 2", a deja signale cette
incoherence sans la trancher. Elle releve d'une decision de typographie pour un enfant : a
`theo-archi` pour l'ADR, a `camille-po` pour le produit. Le relecteur ne la tranche pas, et ne
corrige pas les fichiers de contenu dans ce releve.

## 6. Attributions (`DEC-0062`)

Les chiffres 562, 3, 565, 2377 et 224 sont mesures par le relecteur le 2026-10-16 avec les commandes
ci-dessus, sur le sommet de `round-two`. Elles confirment le chiffre "562 contre 3" de `marc-dev`. Les citations de `nadia-prof` renvoient a `reponse-nadia-q-palier-1.md` (4.1, 4.2) et
`reponse-nadia-q-palier-2.md` (3.5, 3.6) ; ces renvois resolvent. Aucun enonce recu de MASTER.
