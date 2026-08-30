# [Sprint s23] Planning - 2026-08-30

- **Horodatage de debut** : 2026-08-30 14:15
- **Anime par** : sofia-sm
- **Participants** : camille-po, theo-archi, nadia-prof, marc-dev, lea-dev, sofia-sm

## Etat de la fusion d'ouverture

Trois lots ecrits avant ce planning, dans un worktree dedie, depuis `round-two` a `890732f`
(tete de cloture de s22) :

| Branche | Tete au moment de la fusion | Contenu |
|---|---|---|
| `s23-camille` | `dad76fc` | `DEC-0057` - perimetre d'ouverture, `DEC-index`, note de matrice, backlog |
| `s23-nadia` | `8caaa8a` | Huit arbitrages prealables (11 commits) |
| `s23-theo` | `f384846` | `avis-faisabilite.md`, `ADR-0065`, `ADR-0066`, `ADR-0067`, leurs regles, trois correctifs |

**Lignee verifiee avant de fusionner, pas supposee** : `git merge-base --is-ancestor round-two
<branche>` vrai pour les trois. Fusionnees dans l'ordre camille, nadia, theo (`git merge --no-ff`) :
aucun conflit sur les trois fusions, aucun fichier touche par deux branches a la fois (verifie par
`git show --stat` sur chacune avant de fusionner).

**Nom du repertoire de sprint : renomme.** `theo-archi` avait cree
`s23-accentuation-gardee-par-un-automate` faute de perimetre ecrit au moment de son avis de
faisabilite. L'accentuation n'est qu'un des trois `ADR` livres ; le perimetre reel est la
composition d'un lot de trois paquets de contenu parmi sept ouvrables, sous une borne de capacite.
Renomme en `s23-trois-paquets-parmi-sept` dans le meme commit que la fusion, aucun contenu deplace.

**Porte technique, mesuree apres la fusion, pas avant** :

```
bash outils/mvn-jdk21.sh -B clean verify
```

sur le reacteur complet (racine, aucun `-pl`). **BUILD SUCCESS**, six modules en `SUCCESS`.

*Note d'environnement, sans consequence sur le depot* : ce poste porte, sur le `PATH`, un
executable `mvnd` d'un projet sans rapport avec `recreo`, dont le `settings.xml` global redirige
la resolution Maven Central vers un miroir d'entreprise injoignable depuis ce reseau. La premiere
execution a echoue sur la resolution d'une dependance de test neuve (`org.languagetool:
french-pos-dict:0.7`, ajoutee par `theo-archi` pour `LexiqueFrancais`, portee `test`) avec ce
miroir. Verifie ensuite que l'artefact existe reellement sur Maven Central (requete directe,
200 OK) : ce n'est pas un defaut du depot ni de la dependance ajoutee, c'est une redirection propre
a ce poste. Contourne en pointant Maven vers un fichier de configuration global sans miroir pour
cette seule execution ; aucun fichier du depot n'est modifie pour cela, et la CI (environnement
propre, sans cette redirection) n'est pas concernee.

**Compte de tests, methode XML** (somme des attributs `tests`/`failures`/`errors`/`skipped` de
l'element `<testsuite>` de chaque `TEST-*.xml` de `target/surefire-reports/` et
`target/failsafe-reports/`, sur les rapports produits par ce build, apres un `clean`) : **119
rapports, 772 tests, 0 echec, 0 erreur, 0 ignore.** Conforme au chiffre annonce par `theo-archi`
(761 a la cloture de s22, plus 3 + 3 + 5 pour ses trois `ADR`) : **verifie sur l'arbre fusionne,
pas recopie.**

## Objectif du sprint (gele)

**Couverture croissante** (consigne de l'utilisatrice, 2026-08-30), en ouvrant trois paquets de
contenu neufs sous la borne de capacite de `nadia-prof`, et en absorbant le perimetre garanti de
s22 avec la capacite de developpement que trois paquets seuls n'epuisent pas.

`nadia-prof` a rendu huit arbitrages prealables (`squad/conformite/ARBITRAGE-PREALABLE-s23-...md`) :
**sept paquets sont ouvrables** (six candidats de contenu seul acceptes sous conditions, plus une
part servable degagee d'un refus) contre une borne tenue a **trois** par `camille-po` (`DEC-0057`).
**Le declencheur du mur de la mecanique unique ne se produit pas** (il exigeait moins de trois
survivants sur six ; il y en a six) : l'arbitrage de l'utilisatrice sur l'ouverture de la saisie
reste acquis pour le jour ou ce declencheur se produira, il n'est pas caduc, il ne s'applique
simplement pas a s23.

Si les trois paquets retenus aboutissent sans cycle de refus supplementaire, la matrice passe de
**21/43 (48,8 %)** a **24/43 (55,8 %)** - **un objectif de sprint, jamais un Done** : aucune ligne
ne passe `couvert-valide` par ce document, ce statut n'est prononce que par `nadia-prof` apres
audit reel de chaque increment. **Gele** : il ne change plus pendant le sprint.

## Composition du lot de trois paquets

Nadia est explicite sur ce point (section 9.2 de son arbitrage) : elle n'a **aucune preference
pedagogique** entre les sept paquets ouvrables et confie leur composition **conjointement a
`camille-po` et `sofia-sm`**, sous deux contraintes qu'elle seule pose et que je ne rouvre pas :
- **trois paquets au plus**, la borne de `DEC-0057` restant tenue tant que sa premiere condition de
  capacite n'est pas confirmee au sens ou elle l'entend (voir section suivante) ;
- **au plus un** des trois porte une propriete du type `COMP-2` (`NAR-6`, `SUB-6`, `LIA-3` -
  verifiable uniquement item par item, par aucun automate), parmi `NAR`, `SUB`, `LIA`.

**Composition retenue pour s23** : `RAN` (ranger des nombres jusqu'a 100, CP), `ETA` (problemes a
plusieurs etapes aux 4 operations, CM1), `LIA` (mots de liaison simples, CM1, le paquet du type
`COMP-2`). Motif :
- `RAN` acheve une ligne `en cours` depuis s09 (`mathematiques | CP | Nombres et calculs |
  Comparer et ranger des nombres jusqu'a 100 (<, >, =)`) ; nadia note que ce paquet n'est "presque"
  dans aucun jugement qui reste a sa seule main - le risque d'audit est le plus bas des sept.
- `ETA` cloture une dette de planification nommee deux fois (`DEC-0029` a s12, `DEC-0030` a s13,
  toutes deux pour capacite, jamais pour motif pedagogique) ; comme `RAN`, ce n'est pas un paquet
  du type `COMP-2`.
- `LIA` est le paquet du type `COMP-2` retenu parmi les trois candidats (`NAR`, `SUB`, `LIA`) :
  nadia le decrit comme le plus risque des six sur le fond (deux reponses correctes possibles),
  mais aussi celui dont la garde (`LIA-3`, derivation declaree par le fichier) est, dans ses
  propres mots, "le gain le plus rentable de s22" - une propriete qu'elle **recompte** au lieu de
  l'**etablir**. `NAR` et `SUB` restent dans la reserve de quatre paquets deja arbitres pour s24
  que nadia elle-meme signale (avec `PLU` et `FRA`), la premiere depuis s19.

**Ce que ce document ne fait pas** : il ne cree pas les entrees formelles de `docs/backlog/
backlog.md` pour ces trois paquets - `DEC-0057` a ete ecrite avant les huit arbitrages et ne les
contient donc pas encore sous forme de story. C'est un obstacle nomme, pas un blocage : les
conditions de `nadia-prof` (`RAN-1` a `RAN-8`, `ETA-1` a `ETA-10`, `LIA-1` a `LIA-10`) sont deja
ecrites pour etre "reprises telles quelles dans les criteres de story" (ses mots) et suffisent a
demarrer le developpement des ce planning. **Routage** : `camille-po` formalise ces trois entrees
dans `docs/backlog/backlog.md` en debut de sprint, sans que cela retarde le premier commit de
contenu.

## Stories retenues, ordre et affectations

| Ordre | Story | Titre | Competence programme visee | Criteres d'acceptation | Affectee a |
|---|---|---|---|---|---|
| 1 | `S23-RAN` | Ranger des nombres jusqu'a 100 | mathematiques, CP, nombres et calculs | `RAN-1` a `RAN-8`, arbitrage `nadia-prof` s23 | `marc-dev` |
| 2 | `S23-ETA` | Resoudre des problemes a plusieurs etapes (4 operations) | mathematiques, CM1, nombres et calculs | `ETA-1` a `ETA-10`, arbitrage `nadia-prof` s23 | `marc-dev` |
| 3 | `S23-LIA` | Mots de liaison simples pour structurer un texte court | francais, CM1, vocabulaire (couverture partielle nommee, `LIA-10`) | `LIA-1` a `LIA-10`, arbitrage `nadia-prof` s23 | `sofia-sm` |
| 4 | Reserve 1 | En-tete de `cm1-comprehension` sous-declare son marquage `COMP-3` | transverse (correction de contenu deja livre) | Backlog s22, section `cm1-comprehension` | `marc-dev` |
| 5 | Reserves A-E | Cinq reserves non bloquantes de `cm1-problemes-durees` | transverse (correction de contenu deja livre) | Backlog s22, section `cm1-problemes-durees` | `lea-dev` |
| 6 | Audit `.text()` | Verifier si les 65 autres occurrences de `.text()` dans les tests de rendu partagent le meme angle mort a l'espace insecable que celui corrige par `theo-archi` | transverse (qualite de test) | Rapport nomme dans `rapport-qualite.md` : combien d'occurrences sont aveugles, lesquelles corrigees | `yanis-qa` |

**Motif de l'affectation `marc-dev` a deux paquets neufs plus une reserve, `lea-dev` a cinq
reserves sans paquet neuf, `sofia-sm` a un paquet neuf seul** : la consigne de l'utilisatrice
("on peut envisager de prendre plusieurs story par dev") rencontre une capacite de developpement
que trois paquets de contenu seuls n'epuisent pas (la borne de trois est une borne d'**audit**
pedagogique, pas une borne de code). Les cinq reserves de `cm1-problemes-durees` appartiennent a
`lea-dev`, auteure du paquet - nadia les a explicitement adressees a elle ; la reserve de
`cm1-comprehension` appartient de la meme facon a `marc-dev`. Affecter `RAN` et `ETA` (les deux
paquets non `COMP-2`, au moindre jugement pedagogique restant) a `marc-dev` equilibre sa charge
avec les cinq reserves deja fixees sur `lea-dev` ; `LIA`, le paquet `COMP-2` le plus couteux a
auditer, reste seul sur `sofia-sm`, dont la charge de sprint porte deja les ceremonies.

**Reserve 2 de `cm1-comprehension`** (reecriture de la condition `COMP-2` elle-meme) n'est **pas**
une story de developpement : `nadia-prof` la porte seule, aucun agent n'y est affecte ici.

## Ce que ce document route, sans le trancher

### La borne de capacite - a `camille-po` et `nadia-prof` conjointement

`nadia-prof` a demande une capacite de **quatre a cinq paquets par sprint** sous trois conditions
cumulatives, dont la premiere - un garde-fou d'accentuation - n'etait pas remplie quand `camille-po`
a tenu la borne a trois dans `DEC-0057`. **Elle l'est desormais** : `ADR-0065` livre le garde-fou,
avec sa morsure prouvee sur le materiau reel du defaut de s22, et repond a la reserve que
`nadia-prof` avait posee avant de compter dessus ("je veux savoir ce que le garde-fou ne verrait
pas") - les homographes, **232 mots sur 1 331 servis (17 %)**, un trou qui contient le quatrieme
mot du defaut de s22 (`ADR-0065` : le garde-fou aurait nomme "ecrire", "zero", "zeros", jamais
"a droite"). **Ce document ne tranche pas** si la condition 1 desormais remplie, avec son angle
mort connu et mesure, suffit a faire passer la borne de trois a quatre ou cinq : c'est une question
produit (charge que la matrice peut absorber) et pedagogique (ce que `nadia-prof` est prete a
auditer) a la fois. **Route a `camille-po` et `nadia-prof` conjointement.** Le planning de ce
sprint est construit sur trois paquets ; si la borne monte pendant le sprint, un quatrieme ou un
cinquieme paquet serait pris parmi `NAR`, `SUB`, `PLU`, `FRA` (jamais un second paquet `COMP-2`,
la condition 3 de `nadia-prof` ne bougeant pas avec la borne), et le planning serait amende, pas
reecrit.

### `TYP-1`, le moyen - a `theo-archi`, `ines-design` et `sofia-sm`

`ADR-0067` arrete la croissance des signes orphelins par un cliquet exact a 540 occurrences sur
17 fichiers, agnostique au caractere retenu, mais **ne corrige aucune des 540** (18 orphelins
visibles a l'ecran aujourd'hui). Le choix du caractere (espace insecable ordinaire U+00A0, deja en
service par `ADR-0060` clause 2 ; espace insecable etroite U+202F, forme typographique francaise
correcte mais dont le rendu depend des polices servies ; ou retrait de l'espace) est confie a
`theo-archi` et `sofia-sm` conjointement, mais `theo-archi` nomme lui-meme que ce choix depend
d'une mesure de rendu qui n'est pas encore faite - si U+202F casse sur les polices servies, le
choix se ferme sur U+00A0 par elimination. **Ce document ne tranche pas le caractere** : il route
la mesure de rendu prealable a `ines-design`, avant que `theo-archi` et moi ne fermions le choix.
Aucune story de ce sprint n'ecrit ni ne corrige les 540 occurrences existantes ; un paquet neuf
(`S23-RAN`, `S23-ETA`, `S23-LIA`) applique `TYP-1` des sa premiere ecriture, sans attendre ce choix,
puisque le cliquet est deja vert pour zero occurrence.

### Deux corrections que `nadia-prof` route - a `camille-po`

- `DEC-0057` cite `ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md`, **section 2** pour le refus du
  proxy de fraction (ligne 61 du fichier) : la section exacte est la **section 1, "Proxy 2"**. Une
  ligne a corriger dans `DEC-0057`.
- Question de referentiel, posee par `nadia-prof` avant tout arbitrage d'une part non sonore de
  "Identifier un son (phoneme) dans un mot" : cette ligne et "Associer les graphemes aux phonemes"
  designent-elles deux competences distinctes du socle, ou la premiere est-elle la version orale de
  la seconde ? Sans reponse, `nadia-prof` ne rend aucun arbitrage sur cette ligne.

### Ce qui reste connu mais non traite ce sprint

- **`ADR-0066` porte le declencheur de sa propre suppression** si sa clause 2 (aujourd'hui deux
  paires examinees) n'en examine pas plus dans trois sprints. C'est son premier sprint depuis sa
  creation : rien a faire, a surveiller a s24 et s25.
- **Une phrase d'`ADR-0058`** declare trois conditions non gardables faute de dictionnaire ;
  `ADR-0065` en a fait entrer un. A amender le jour ou la saisie ouvre, pas avant - le mur de la
  mecanique unique ne s'est pas produit ce sprint.
- **Onze garde-fous de contenu** ne rougiraient pas mais planteraient a l'arrivee d'un paquet de
  saisie, chacun demandant une decision de perimetre ecrite le jour venu. Chiffre connu, aucune
  action ce sprint.

## Perimetre garanti (sans attendre d'arbitrage neuf)

Les sept reserves non bloquantes de s22 (stories 4 et 5 ci-dessus) et l'audit `.text()` de
`yanis-qa` (story 6) : aucune des trois n'attend de decision produit ou pedagogique, toutes
peuvent demarrer des l'ouverture.

## Avis de faisabilite de `theo-archi`

`squad/sprints/s23-trois-paquets-parmi-sept/avis-faisabilite.md` (358 lignes, fusionne avec
`s23-theo`). Aucune reserve technique nommee contre l'ouverture des trois paquets retenus : les
trois sont des paquets `question`/`texteSupport` sans architecture neuve (`ADR-0050`), la meme
convention que tout paquet du depot.

## Decisions prises

- **Renommage du dossier de sprint** de `s23-accentuation-gardee-par-un-automate` vers
  `s23-trois-paquets-parmi-sept`, dans le commit de fusion, sans deplacement de contenu.
- **Composition du lot de trois paquets** : `RAN`, `ETA`, `LIA`, decidee conjointement avec
  `camille-po` par delegation explicite de `nadia-prof` (section 9.2 de son arbitrage), motif
  ci-dessus. Ce n'est pas un `DEC` : c'est une composition de planning dans l'espace que `DEC-0057`
  et l'arbitrage de `nadia-prof` autorisent deja, pas une decision de perimetre nouvelle.
- **Affectations nominatives** : `marc-dev` (`S23-RAN`, `S23-ETA`, reserve 1), `lea-dev` (reserves
  A a E), `sofia-sm` (`S23-LIA`), `yanis-qa` (audit `.text()`).
- **La borne de capacite reste a trois paquets pour ce sprint**, sa reouverture eventuelle etant
  routee et non tranchee ici (voir ci-dessus).
