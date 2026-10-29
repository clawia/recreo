# Releve du relecteur pedagogique - palier 5 de s57, `ce1-qlm-generations` (QLM-CE1-12)

Date : 2026-10-29. Story : `S57-QLM-CE1-12-ACCORD`, critere 1 (`docs/backlog/backlog.md`). Demande :
`FICHE-ce1-qlm-12.md`, section 3 (signal `P5`, `nadia-prof`), `DEC-0120` A.283 D3. Perimetre :
l'accord de *du plus âgé au plus jeune* dans les trois enonces des lignes `:96`, `:131` et `:152` de
`adapters/adapter-content/src/main/resources/contenu/ce1-qlm-generations.yaml`, plus l'orthographe et
les accents de ces enonces et de leurs trois indices. Le niveau scolaire et le sens ne sont pas
juges (`nadia-prof`). Aucun fichier du paquet, des tests, du catalogue ni des fiches n'est modifie.

## 1. Verdict

| Ligne | Item | Enonce lu dans le fichier | Accord |
|---|---|---|---|
| `:96` | 3 | "Dans la famille de Lina, quelle suite range les générations du plus âgé au plus jeune ?" | **FAUTIF** |
| `:131` | 8 | "Dans la famille de Samir, quelle suite range les générations du plus âgé au plus jeune ?" | **FAUTIF** |
| `:152` | 11 | "Omar a une fille, Julie. Julie a un fils, Noé. Quelle suite va du plus âgé au plus jeune ?" | **JUSTE** |

Verdict d'ensemble : **FAUTIF** (items 3 et 8). Le branchement de l'entree de catalogue de `-12`
reste conditionne par la correction de ces deux enonces et par la garde `G12-3` (section 4).

## 2. La regle

Dans *ranger des X du plus [adjectif] au plus [adjectif]*, l'adjectif se rapporte aux X rangés et
s'accorde avec eux : *range les nombres du plus petit au plus grand*, *range les longueurs de la
plus courte à la plus longue*, *range les cartes de la plus ancienne à la plus récente*. C'est
l'accord de l'adjectif avec le nom qu'il qualifie, ici sous la forme d'un superlatif (*le plus
...*) precede de l'article qui prend le genre du nom (regle d'accord de l'adjectif qualificatif et du superlatif relatif, traitee par Grevisse, *Le bon usage*,
et par le Bescherelle de la grammaire ; je cite la regle, je n'ai pas verifie de numero de page ni de
paragraphe).

- Items 3 et 8 : ce qu'on range, c'est **les générations**, nom feminin pluriel, objet direct de
  *range*. *Génération* se dit *âgée* (*la génération la plus âgée*). L'enonce doit donc dire *de la
  plus âgée à la plus jeune*. *Du plus âgé* est un masculin singulier qui ne s'accorde avec aucun nom
  de la phrase : le lecteur le rattache a *générations* et lit une faute.
- L'objection : *le plus âgé* serait un adjectif employe comme nom, au masculin generique, pour
  *la personne la plus âgée* (adjectif employe comme nom, meme sources). Cet emploi est regulier **quand la
  phrase ne contient aucun nom feminin auquel l'adjectif puisse se rapporter**, ou quand les
  personnes sont ce qu'on range (*range les enfants du plus âgé au plus jeune*). Ici, ce qu'on range
  est *les générations*, pas leurs membres : la lecture par le masculin generique ne tient pas.
- Item 11 : ce qu'on range, ce sont trois prenoms, donc trois personnes (Omar, Julie, Noé). *Le plus
  âgé* est le masculin generique pour *la personne la plus âgée* de la suite, et Julie est une fille,
  donc aucun feminin ne convient a tous. *Suite* est feminin, mais une suite n'est pas *âgée* :
  l'adjectif ne peut se rapporter qu'aux personnes qu'elle contient. L'accord est juste. La formule
  reste la plus fragile des trois (un lecteur peut hesiter sur *suite* ... *plus âgé*) ; elle n'est
  pas fautive.

## 3. Textes corriges (items 3 et 8 seulement)

Seul le groupe *du plus âgé au plus jeune* change. Le reste de l'enonce, les propositions et les
bonnes reponses des 12 items sont inchanges. Les espaces avant `?` sont les `U+00A0` du paquet
(controle : `grep -c` de `U+00A0` rend 1 sur chacune des trois lignes).

| Item | Texte corrige, mot pour mot |
|---|---|
| 3 (`:96`) | "Dans la famille de Lina, quelle suite range les générations de la plus âgée à la plus jeune ?" |
| 8 (`:131`) | "Dans la famille de Samir, quelle suite range les générations de la plus âgée à la plus jeune ?" |
| 11 (`:152`) | inchange |

Lisibilite : *de la plus âgée à la plus jeune* ne change ni le nombre de mots difficiles ni la
longueur de la phrase de facon notable (+ 6 caracteres par enonce). Le jugement de lisibilite
appartient a `nadia-prof`.

## 4. Compatibilite avec `R12-2` et `G12-3` : a trancher par `theo-archi`

Lu dans `adapters/adapter-content/src/test/java/com/recreo/adapter/content/PaquetCe1QlmGenerationsTest.java`
(le test de `-12` ; aucun fichier de `bootstrap/src/test` ne cite `G12-3`) :

- `SENS_DU_PLUS_AGE = "du plus age au plus jeune"` (ligne 134) ; la garde `G12-3` teste
  `plat.contains(SENS_DU_PLUS_AGE)` (ligne 365) sur l'enonce normalise, et son message cite
  « du plus âgé au plus jeune » (lignes 368, 385, 778, 818).
- Les textes corriges des items 3 et 8 **ne contiennent pas** cette chaine : la garde les refuserait
  en l'etat (sonde 17, ligne 777).
- L'item 11 la contient et passe.

Consequences, que je ne tranche pas :

1. La formule de `R12-2` (B, generations) et la constante changent ensemble, comme l'exige le
   critere 3 de la story. Deux formes sont maintenant lues pour le groupe `gen` : *de la plus âgée à
   la plus jeune* (enonce qui range des générations) et *du plus âgé au plus jeune* (enonce qui range
   des prenoms, groupe `pre`). La garde ne doit pas pour autant accepter l'une ou l'autre
   indifferemment : *le sens est lie au groupe* (ligne 377 du test). Je ne peux
   pas dire si une lecture a la lettre de deux formes liees chacune a son groupe reste *sans assouplir
   la garde* : c'est a `theo-archi`.
2. Le commentaire d'en-tete du paquet (lignes 25 et 53 du YAML) cite *du plus age au plus jeune*
   pour les générations ; il est a mettre en accord avec la decision de `theo-archi`.
3. Le texte de `R12-2` B (`arbitrage-nadia-premiers-jets-ce1-qlm-10-12.md`, section 2 ; `arbitrage-nadia-contradictions-ce1-qlm-10-12.md`, `R12-2` B, ligne 180) cite la
   formule masculine : l'auteur de la regle (`nadia-prof`) et `camille-po` tranchent si la formule
   de la regle change ; ce n'est pas mon ecrit.

## 5. Orthographe et accents

Les trois enonces et les trois indices sont lus en entier : aucune faute d'orthographe, d'accent,
de ponctuation ni de typographie (guillemets droits, pas de tiret cadratin).

| Ligne | Texte | Verdict |
|---|---|---|
| `:96` | enonce item 3 | correct (hors accord) |
| `:99` | indice : "Compare deux générations à la fois, puis la troisième." | correct |
| `:131` | enonce item 8 | correct (hors accord) |
| `:134` | indice : "Cherche qui est né avant qui dans la famille." | correct |
| `:152` | enonce item 11 | correct |
| `:155` | indice : "Suis les liens de la famille, d'un prénom au suivant." | correct |

## 6. Points a trancher

| Point | Pour | Objet |
|---|---|---|
| T1 | `theo-archi` | forme de `R12-2` et de `G12-3` apres correction (section 4, points 1 et 2) |
| T2 | `camille-po`, `nadia-prof` | si la formule de la regle `R12-2` B change dans les arbitrages (section 4, point 3) |
| T3 | `nadia-prof` | relecture des deux enonces corriges avant `verify` (critere 3 de la story) |

Les corrections ne sont pas appliquees : ce releve ne modifie ni le paquet ni le test (consigne).
Les enonces corriges sont ecrits ci-dessus pour que `marc-dev` ou `lea-dev` les appliquent avec le
test dans le meme commit, `git diff` limite aux lignes `enonce:` des items 3 et 8 et au test.
