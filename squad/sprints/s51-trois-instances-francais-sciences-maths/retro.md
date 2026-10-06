# Retro - s51 - trois lignes de francais CE1 et une ligne de sciences CM1 fermees sur six engagees, deux refus de sciences CM1, instance C non engagee

- **Date** : 2026-10-06.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Nature du sprint** : deux instances de `nadia-prof` en parallele (A francais CE1, B sciences et
  technologie CM1), ouvert a trois instances puis ramene a deux par le refus de la borne (`DEC-0110`
  section 14). Premier sprint parallele depuis la collision de scratchpad de s50.
- **Sources** : `review.md` de ce dossier (obstacles, mesures), `DEC-0110` sections 14 a 16, les sept fiches de
  conformite. Je cite ces fichiers ; je n'ai pas relu les transcriptions de sous-agent.

---

## 1. Ce qui a marche

**La condition suspensive de `FRA-CE1-22` a tenu son role.** Le planning refusait d'engager la ligne tant que
A1 (`ADR-0065`) et l'avis de `nadia-prof` n'existaient pas au disque. Ordre des commits, lu par
`git log --format='%h %ad' --date=format:%H:%M` : A1 a 13:47 (`c2802a32`, `02ba9e80`), l'arbitrage de
`nadia-prof` a 13:52 (`0ce82262`), le paquet a 14:13 (`271058ec`). La ligne de remplacement que `camille-po`
devait fournir (R3) n'a pas ete necessaire. `S51-FRA-TEMPS` et `S51-FRA-ALPHABETIQUE` ont demarre sans attendre,
comme ecrit. Resultat : la ligne s'est fermee au second passage.

**L'isolation des deux instances a tenu, sur ce que je peux lire.** Chaque instance a eu un worktree
(`s51-nadia-instance-a`, `s51-nadia-instance-b`), une branche, un sous-repertoire propre dans le repertoire
temporaire de la session (`ls` : `instance-a` et `instance-b`, aucun `jouer.py` a la racine). Fait mesure : les
deux `jouer.py` y sont ecrits a 33 secondes d'ecart (14:26:38 et 14:27:11, heure locale), c'est-a-dire dans la
situation qui a produit la collision de s50 (13 secondes), et ils different (4 101 et 1 879 octets) parce qu'ils
sont dans deux repertoires. Les quatre branches de s51 descendent de `e4d8ba3b` (`git merge-base
--is-ancestor`).

**La fusion n'a pas affaibli une assertion.** Un seul conflit, sur `REGISTRE-identifiants.md` : les deux
instances ajoutent au meme tableau, chacune depuis son worktree comme la regle le demande. Resolu en gardant
les deux ajouts : 918 lignes d'identifiant au planning, +2 cote A, +3 cote B, **923** apres fusion, et le
garde-fou `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` rend 10 tests verts sur l'arbre fusionne.

**Les chiffres annonces ont ete refaits, et ils tenaient.** 2076 tests : `yanis-qa` l'a mesure sur
`ca4c934a`, je l'ai remesure sur `047b91c4` (reacteur complet, somme des rapports XML) et retrouve le meme
total. L'ecart avec la base de 2004 est explique (+2 d'`ADR-0104` avant le planning, +70 de s51). La couverture,
123 a 127, est refaite par la commande de la matrice.

**Le budget a ete tenu en lisant la regle, pas la preference.** `camille-po` a choisi le rejeu de `SCI-CM1-18`
sur `PAR-8`, `PAR-10` et `PAR-14` comptes ligne par ligne (`DEC-0110` 15.1 a 15.3), et a ecrit apres coup que
ce choix etait un pari perdu (16.1). Aucune instance n'a depasse 5,0.

## 2. Ce qui a coince

**Trois premiers passages refuses sur sept, et le meme defaut deux fois en sciences.** `FRA-CE1-22`,
`SCI-CM1-15`, `SCI-CM1-18` : refus au premier passage. Le refus de `FRA-CE1-22` a ete rejoue et leve pour
0,5. En sciences, `SCI-CM1-15` (items 4 et 11) et `SCI-CM1-18` (item 11) portent la meme famille de defaut,
celle que `nadia-prof` nomme *"une cle de reponse fausse"* : un distracteur est une reponse juste a la
question posee. `SCI-CM1-18` a ete refusee une seconde fois sur le meme item. Cout mesure : B a consomme 5,0
pour une ligne fermee sur trois, et deux paquets refuses restent servis (decision de l'utilisatrice, `review.md`
section 8). Les tests de paquet ne voient pas ce defaut : il se lit en jouant, par la lecture masquee de
`nadia-prof`.

**Le rejeu de `SCI-CM1-18` a coute la marge de B sans fermer la ligne.** `SCI-CM1-15` n'a eu aucun rejeu. La
marge de A (1,0) est restee non consommee, sans pouvoir servir B : une instance par couple matiere/niveau
(`PAR-2`), un plafond par instance (`PAR-8`). Je constate l'ecart, je ne le juge pas.

**La cedille a fait rougir un garde-fou a s51, apres avoir fait refuser une ligne a s50.** A s50,
`FRA-CE1-02` a ete refusee par `nadia-prof` au premier passage parce que `ç` n'etait servi par aucun item
(`FER-1`, `VLE-CE1-1`). A s51, l'avis de `theo-archi` (V1) a trouve que `AucunMotServiNeSEcarteDeSonAccentTest`
refusait par construction le paquet que `GRL-CE1-2` decrivait : `LexiqueFrancais.squelette` retire la
cedille, *garcon* n'est pas au lexique. Les deux mecanismes different (un avis pedagogique, puis un garde-fou
de contenu), la lettre est la meme. Le cout a ete celui d'un amendement (A1) ecrit avant la ligne, et
`ADR-0065` dit que toute autre marque appellera un nouvel amendement.

**A1 a ete commite deux fois.** `c2802a32` (sur `round-two`) et `02ba9e80` (sur `s51-nadia-instance-a`) ont le
meme `git patch-id --stable` et la meme date d'auteur 13:47:16 ; la fusion n'a produit aucun conflit.
`confirmation-archunit.md` place `02ba9e80` sur l'instance B, ce que `git branch --contains` ne confirme pas.

**`HOM-CE1-R1` a ete livree par un autre developpeur que celui du planning, sans trace ecrite.** Le planning
l'affectait a `marc-dev` ; le commit `cc5686e0` est `[LEA]`. Rien dans le depot ne dit pourquoi
(`review.md`, obstacle 7). Le changement d'affectation est de ma categorie (capacite) : il aurait du
entrer dans le tableau d'obstacles au moment ou il a eu lieu, pas etre retrouve a la cloture.

**Le planning est devenu faux en un jour sur cinq points.** Objectif, tarif de B, statut de
`SCI-CM1-15`, condition suspensive de `S51-FRA-GRAPHIE`, numeros de ligne du backlog. Le `relecteur-pedagogique`
les a signales, l'amendement de cloture du planning les met a jour. Fait mesure : les neuf numeros de ligne
cites au planning se sont tous decales de +14 (`grep -n "^### S51-" docs/backlog/backlog.md`) parce que 14
lignes ont ete inserees dans le backlog avant ces stories.

**La verification des formes (a), (b), (c) n'est pas au depot a la cloture.** `nadia-prof` l'a annoncee pour
la cloture de s51 (`ARBITRAGE-PREALABLE-s51-borne-a-trois-instances.md` section 8) ; `DEC-0110` 16.10 constate
qu'aucun fichier de derivation ni de s52 n'existe. Sans elle, la borne a trois instances pour s52 reste
fermee. Ce que j'ai : le `ls` ci-dessus, qui ne remplace ni la lecture des transcriptions ni son verdict.

**Huit commits portent le trailer "Claude Opus 5.5" (`[THEO]` quatre, `[NAD]` quatre)**, un `[NAD]`
(`0ce82262`) porte "Sonnet 5.5". Aucune regle ecrite ne fixe cette valeur (`grep -rn Co-Authored-By` sur
`CLAUDE.md`, `squad/README.md`, `.github/`). Le rapport de tokens de s51 place `theo-archi` et `nadia-prof` sur
`claude-opus-5-5` et les autres agents sur `claude-sonnet-5-5` : les huit trailers nomment le modele qui a tourne,
et c'est `0ce82262` qui est l'ecart. Sans consequence sur la CI.

**Le journal de tokens perd des mesures a la regeneration.** `outils/rapport-tokens.py` regenere
`squad/rapports/JOURNAL-tokens.md` depuis les transcriptions encore presentes. A s51, la regeneration remplace par
"Aucune consommation mesuree" les sections de s23 a s27 (et presque tout s22), qui portaient des chiffres a
`047b91c4` (`git diff` : 36 lignes ajoutees, 90 retirees). Je n'ai pas commite ce journal (`review.md`, obstacle 16).
Je n'ai pas verifie la cause : les transcriptions de ces sprints ont disparu du poste, c'est l'hypothese la
plus simple, pas un fait lu.

## 3. Actions, routees et non tranchees

| # | Action | Route a | Artefact attendu |
|---|---|---|---|
| A1 | Verifier les formes (a), (b), (c) de s51 contre ses transcriptions et ecrire la conclusion ; dire en meme temps si un sous-repertoire de scratchpad de session suffit a la regle *"scratchpad aussi dedie que son worktree"* (le planning demandait un repertoire temporaire propre a l'instance, les instances ont pris un sous-repertoire) | `nadia-prof` | document neuf, ou conclusion dans l'arbitrage de s52 |
| A2 | Corriger la ligne 89 de `ARBITRAGE-PREALABLE-s51-fra-ce1-22-graphie-cedille.md` (162 caracteres, defaut residuel du relecteur) | `nadia-prof` | correction de son fichier |
| A3 | Corriger dans `confirmation-archunit.md` le rattachement de `02ba9e80` (instance A, pas B) | `theo-archi` | correction de son fichier |
| A4 | Reprendre les trois stories de reprise (`S51-SCI-ACTIVITE-HUMAINE-CM1-REPRISE`, `S51-SCI-TERRE-CM1-REPRISE`, `S51-SCI-CORPS-CM1-RESERVES`) et les deux reserves de A (`TPS-CE1-R1`, `ALP-CE1-R1`) ; trancher, avant tout autre poste sur les deux paquets servis, entre retrait et reprise (exigence de `DEC-0110` 16.10) | `camille-po` | `DEC` d'ouverture de s52 |
| A5 | Dire si l'instance C est rouverte a s52 sous la condition de `nadia-prof`, et si les deux points ouverts (`PAR-4` a deux instances, ecart `PAR-9`/pratique) sont tranches | `camille-po`, `nadia-prof` | `DEC` de s52 |
| A6 | Les distracteurs vrais de sciences : les criteres d'acceptation des stories de sciences demandent-ils de verifier chaque distracteur contre la question posee, et non contre la conception fausse visee ? Question, pas conclusion : je ne sais pas si l'ecriture des criteres aurait evite les trois items | `camille-po` (criteres), `nadia-prof` (barre) | reponse ecrite |
| A7 | Dire si une valeur du trailer `Co-Authored-By` est exigee et laquelle pour un agent qui tourne sur Opus | `camille-po` (conventions de `CLAUDE.md`) | regle ecrite ou absence de regle ecrite |
| A8 | Reaffectation de `HOM-CE1-R1` : aucune action de reecriture ; a s52, toute reaffectation apres le planning entre dans le tableau d'obstacles de la review le jour ou elle a lieu | `sofia-sm` | pratique, sans fichier de regle |
| A9 | Decider du journal de tokens : commiter le journal regenere (s23 a s27 perdus dans l'arbre de travail, encore dans l'historique Git), le laisser tel quel sans la section de s51, ou faire preserver par l'outil les sections deja ecrites ; l'en-tete du journal interdit l'edition a la main | utilisatrice, puis `theo-archi` pour l'outil | decision de l'utilisatrice, `ADR` ou correction de `outils/rapport-tokens.py` |

## 4. Amendements au process

**Aucun amendement n'est ecrit par moi dans `squad/README.md`.** Deux propositions sont justifiees par un fait
mesure de s51 ; je les demande, je ne les ecris pas.

| Section de `squad/README.md` | Proposition | Fait mesure | Demandee a |
|---|---|---|---|
| 4 (planning) et `squad/TEMPLATES.md` | Le tableau *"Verification poste/story"* cite la story par son titre de section ; le numero de ligne du backlog, s'il est cite, est ecrit comme un temoin date et non comme une reference | Neuf numeros de ligne cites au planning, neuf decales de +14 en moins de 24 h ; le relecteur a du les signaler et l'amendement de cloture les corriger | `camille-po` (le backlog est le sien) |
| 9 (scratchpad dedie) | Preciser ce que *"dedie"* veut dire (sous-repertoire propre a l'instance dans le scratchpad de session, ou repertoire distinct), et faire consigner par la review un `ls` des scratchpads dedies a la cloture d'un sprint parallele | A s51 les deux instances ont pris un sous-repertoire ; le planning demandait un repertoire propre ; aucune des deux lectures n'est ecrite. Cette proposition **depend de la reponse de `nadia-prof` a l'action A1** : si elle juge le sous-repertoire insuffisant, la proposition change | `nadia-prof`, puis `theo-archi` pour l'ecriture |

## Decisions prises

**Aucune `DEC` ni `ADR` n'est creee ou amendee par cette retro.**
