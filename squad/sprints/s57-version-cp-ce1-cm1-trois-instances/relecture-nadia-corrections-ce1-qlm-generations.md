# s57 - relecture des corrections de `ce1-qlm-generations` (`S57-BR-QLM-CE1-12`, `P5`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : debut 2026-10-27 14:49:50 (UTC+01:00), lu par `date` ; fin en section 6.
- **Arbre** : branche `s57-p5-nadia-corr12`, base `round-two` a `0286fde1`, lue par `git`.
- **Lu** : `git diff de814b09 HEAD` sur `adapters/adapter-content/src/main/resources/contenu/ce1-qlm-generations.yaml` et
  `git show 249aaf71` sur `PaquetCe1QlmGenerationsTest.java` et `generations-fautives.yaml` (correction de `marc-dev`), compares a
  la section 4 (`K1` a `K6`) et a la section 6 (`X1`) de `lecture-nadia-premier-jet-ce1-qlm-generations.md`. Dans le test, la garde
  `G12-3` (`verifierLesSuites`) et les aides `refuse` et `laisse`. **Je n'ai pas relu le paquet.**
- **Type** : relecture des lignes corrigees, avant `verify`. **Aucun Done, aucun verdict de lot**, aucun identifiant neuf, aucune
  condition neuve. Je n'ai modifie ni le paquet, ni les tests.
- **Audit degrade, dit** : rien n'est joue a l'ecran (paquet hors catalogue). Les items 6, 9 et 12 sont rejoues sur le papier avec leur
  indice neuf.
- **Mesures lancees** :
  - comparaison par script (Python, hors du depot, non commite) : paragraphe *Tache voisin.* et ligne *Evenements d'une vie* de
    `de814b09` et de `HEAD`, mis bout a bout sans le retour a la ligne, puis compares apres ma seule substitution ; colonnes des lignes ;
    longueur stricte de `TRV-CE1-2` ;
  - octets des quatre champs corriges (`od`) : espace insecable `c2 a0` avant *?* a l'enonce de l'item 12, aucun autre caractere hors
    ASCII que les lettres accentuees, apostrophes droites ;
  - `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test -Dtest=PaquetCe1QlmGenerationsTest -Dsurefire.failIfNoSpecifiedTests=false` :
    *"Tests run: 40, Failures: 0, Errors: 0, Skipped: 0"*, `BUILD SUCCESS` (39 au premier jet, plus le test de `X1`). Perimetre : ce
    test, module `adapter-content` et ses dependances. Ce n'est ni un `verify`, ni la porte technique.

## Resume

| Point | Reponse |
|---|---|
| Conformite a ma section 4 | **`K1` a `K6` tiennent**, texte a la lettre ; `K5` et `K6` different par le seul retour a la ligne (section 1) |
| Deux faits du rapport de `marc-dev` sont inexacts | `K5` n'est pas *sous 100 colonnes* : sa premiere ligne fait **121 colonnes** ; le paragraphe de `K6` va jusqu'a **100**, pas 98 (section 1). Ce n'est pas une propriete pedagogique ; aucune correction demandee |
| Garde `X1` | **tient** : refus de la permutation (1, 2, 0) sur une suite `evt` seulement, sondes 68 a 70 refusees avec le motif, 71 a 73 passent (section 3) |
| Perimetre du diff | **seules les lignes prescrites changent** (section 2) |
| Indices, enonce, `TRV-CE1-2` | **tiennent** ; 2 items sur 12, limite 6 (section 4) |
| Ouverture | **aucune** (section 5) |
| Duree | section 6 |

## 1. Comparaison a ma section 4, element par element

| Code | Element | Verdict | Motif |
|---|---|---|---|
| `K1` | item 6, `indice` : *"Pars de Mia et suis les liens jusqu'à Tom."* | **tient** | Caractere pour caractere ; apostrophe droite, *à* accentue ; ancien texte absent |
| `K2` | item 9, `indice` : *"Pars d'Inès et remonte d'une génération à la fois."* | **tient** | Idem ; deux apostrophes droites, *Inès*, *génération*, *à* accentues |
| `K3` | item 12, `enonce` : *"Dans la vie de Jade, quelle suite range les moments du premier au dernier ?"* | **tient** | Idem ; l'espace avant *?* est l'insecable `U+00A0` (octets `c2 a0`, seule occurrence de la ligne), comme ma section 4 l'exige |
| `K4` | item 12, `indice` : *"Compare deux moments à la fois, puis le troisième."* | **tient** | Idem ; une seule occurrence dans le paquet, distinct de l'indice de l'item 3 (*générations*, *la troisième*) |
| `K5` | ligne *Evenements d'une vie* | **tient, retour a la ligne seul different** | Mise bout a bout, la ligne de `HEAD` est celle de `de814b09` ou *"(admise, pas demandee)"* devient *"(admise ; dans chaque item voisin d'evenements, dans aucune suite)"*, rien d'autre [script]. La coupure tombe apres *d'evenements,* comme `marc-dev` le dit. **Sa raison est inexacte** : la premiere ligne fait 121 colonnes, la seconde 23, pas *sous 100*. Le fichier porte deja une ligne de 151 colonnes (ligne 8, champ `competence`) et aucune garde ne lit la largeur : je le dis comme un fait, sans correction, ce n'est pas de mon ressort |
| `K6` | phrase *celle des enfants* du paragraphe *Tache voisin.* | **tient, retour a la ligne seul different** | Mis bout a bout, le paragraphe de `HEAD` est celui de `de814b09` ou mon ancienne phrase est remplacee par ma phrase neuve, a la lettre, et rien d'autre [script]. Le reste du paragraphe est recoupe sans un mot change, en lignes de 89 a 100 colonnes (lignes 43 et 46 a 100) : pas *a 98* comme le dit le rapport, meme remarque que pour `K5` |
| `K6` | ligne de la famille a | **tient** | *"famille a : tache voisin, une autre generation que la bonne reponse"* ; *"jamais celle des enfants"* retire, rien d'autre |

**Je le confirme : pour `K5` et `K6`, seul le retour a la ligne s'ecarte de ma lettre, pas le texte.** Les largeurs donnees (98 pour
`K6`, sous 100 pour `K5`) ne decrivent pas le fichier ; elles ne changent rien au texte.

**`M4` et `M8` laisses tels quels : je le tiens ainsi.** Ma section 5 ne demandait rien sur *celle des enfants* en distracteur ni sur
*celle des arrière-grands-parents* (distracteur des items 1, 4, 9) ; le choix etait au developpeur, a charge de le dire dans son
rapport. Il l'a dit dans le rapport qui m'est relaye ; il n'est ni dans le message de `249aaf71` ni dans un fichier du depot, il est
desormais ici. Garder les deux en l'etat ne touche aucune propriete que je garde : la competence reste le choix entre parents et
grands-parents.

## 2. Perimetre du diff

`git diff --stat de814b09 HEAD` sur le YAML : *"1 file changed, 18 insertions(+), 16 deletions(-)"*, un seul commit (`249aaf71`). Les
lignes : `K5` (1 ligne devenue 2), le paragraphe *Tache voisin.* (10 lignes recoupees en 11, `K6`), la famille a (1), les indices des
items 6, 9 et 12 (3), l'enonce de l'item 12 (1). **Rien d'autre ne change** : aucune bonne reponse, aucun distracteur, aucune
declaration d'item, aucun compte, aucun autre enonce ni indice. Le test gagne la constante `NAISSANCE_EN_DERNIER`, la ligne de `X1` dans
`G12-3` et un test de sondes ; le jeu fautif gagne les sondes 68 a 73 et leur mention dans son en-tete, et rien d'autre.

## 3. La garde `X1`

| Point | Verdict | Motif |
|---|---|---|
| Place | **tient** | Dans `verifierLesSuites` (`G12-3`), sous `groupe.equals(EVT)` : les suites de generations et de prenoms ne sont pas touchees |
| Ce qui est refuse | **tient** | La permutation est lue sur les rangs de la bonne suite, qui pour `evt` est forcee a `SUITE_ADMISE` (*naissance, entrée à l'école, premier emploi*) par la ligne precedente. (1, 2, 0) est donc exactement *"entrée à l'école, premier emploi, naissance"*, la rotation de ma section 6 |
| Ce qui passe | **tient** | La rotation (2, 0, 1), *"premier emploi, naissance, entrée à l'école"*, que j'admettais, n'est pas refusee |
| Sondes 68, 69, 70 refusees | **tient** | 68 : echange (1, 0, 2) puis la rotation fautive ; 69 : la rotation fautive en premier ; 70 : echange (0, 2, 1) puis la rotation fautive. `refuse` exige le fragment *"met naissance en dernier"* : une sonde refusee pour un autre motif ferait echouer le test. Le test passe |
| Sondes 71, 72, 73 passent | **tient** | 72 est l'item 12 du paquet corrige (ma sonde qui passe) ; 73 porte l'autre rotation admise ; 71 est l'item 12 du premier jet, ancien enonce et ancien indice, qui ne porte pas la rotation fautive |

**Ce que la sonde 71 montre et que je lis** : l'enonce *"... du premier au dernier moment ?"* passe `G12-3`, parce qu'il ecrit *du
premier au dernier*. Aucune garde ne refuse un enonce qui evoque la mort : c'est toujours moi (section 5).

## 4. Ce que les corrections doivent tenir

Rejoues sur le papier, comme un enfant de CE1, y compris en me trompant.

- **`K1`, item 6, pas de reponse par la parente** : *suis les liens* ne dit ni *enfant*, ni *fils*, ni *fille*, ni un sens. L'enfant
  part de Mia, monte a Nora, redescend a Tom : il doit encore voir que monter puis redescendre ramene a la meme generation, ce qui est la
  competence. L'enfant qui prend *celle des parents* (Nora est sur le chemin) se trompe sur la competence, pas sur l'indice. **Tient.**
- **`K2`, item 9, une methode vraie** : *remonte d'une génération à la fois* fait nommer chaque marche (Hugo, les parents ; Rose, les
  grands-parents) au lieu de compter des liens. La regle fausse de ma 2.2 a disparu : sur l'item 6, la meme methode, appliquee, fait
  monter a Nora puis constater que Tom n'est pas au-dessus, ce qui reste vrai. *Remonte* ne trie rien : les trois propositions sont
  au-dessus d'Inès. Ni *grand*, ni *parents*. **Tient.**
- **`K4`, item 12, pas de reponse par la vie** : *Compare deux moments à la fois, puis le troisième.* ne nomme aucun moment, ni
  premier ni dernier ; *le début* et *la fin* ont disparu. **Tient.**
- **`K3`, item 12, plus de mort** : *dernier* qualifie le dernier moment de la suite rangee, plus *le dernier moment* d'une vie. La
  formule *du premier au dernier* de `R12-2` est gardee (`G12-3` passe). 14 mots, sous `LON-CE1-1`. **Tient.**
- **`TRV-CE1-2`** : bonne reponse strictement la plus longue en caracteres dans **2 items sur 12** (7 et 10), limite 6 [script] ;
  inchange, aucune proposition n'ayant change. **Tient.**

## 5. Ouvertures

**Aucune.** Aucun item, aucune formulation n'est rouvert : les six corrections sont ma lettre, `X1` est ma ligne, et la phrase ajoutee
a `R12-2` (ma lecture, section 7) n'est pas retouchee. Les seuls ecarts releves sont deux largeurs du rapport de `marc-dev` (section 1),
qui ne portent sur aucun texte que l'enfant lit. Rien a reporter par `camille-po`.

**Ce dont je reste la seule garde sur `-12`, apres cette relecture** : qu'un indice ne dise pas la reponse par la parente ou par la
vie ; qu'un indice-methode reste vrai sur tous les items du paquet ; qu'un enonce n'evoque pas la mort (la sonde 71 le montre) ; qu'un
indice de B ne nomme pas deux elements de la suite dans leur ordre. **Aucune n'est couverte par un test.** `X1` est desormais couvert
par `G12-3`.

## 6. Duree et faits pour le classement

Mesuree par `date` : debut **2026-10-27 14:49:50**, ecriture de ce fichier 14:53:51, fin en section 7. Il n'ecrit ni ne renomme aucun identifiant de condition : `K1`, `M4`, `X1` sont des reperes de
lecture de mes fichiers.

- `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest -Dsurefire.failIfNoSpecifiedTests=false` :
  verdict en section 7.
- `.github/scripts/verifier-chemins.sh < /dev/null` : verdict en section 7.
- Aucun autre job de `.github/workflows/ci.yml` lance.

**Faits pour le classement par `camille-po`** : cette relecture ne porte que sur les lignes que j'ai prescrites (section 2) et sur la
garde que j'ai demandee (`X1`) ; elle ne relit aucun item hors des items 6, 9 et 12, ne pose aucune correction, ne rouvre aucun verdict
hors de ces lignes et n'ouvre aucun tour. C'est la forme d'une seconde lecture au sens de `DEC-0120` A.272 (0,5 hors marge). Le
classement est a `camille-po` ; je n'en convertis aucune duree.

## 7. Verdicts des verifications

Lancees a 14:56:51, ce fichier present dans l'arbre, puis relancees apres les dernieres retouches de ce fichier (section 1) :

- garde-fou des identifiants : *"Tests run: 10, Failures: 0, Errors: 0, Skipped: 0"*, `BUILD SUCCESS`. Perimetre : ce seul test,
  module `bootstrap` et ses dependances ; ni un `verify`, ni la porte technique ;
- `verifier-chemins.sh` : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, code de sortie 0.

Fin mesuree par `date` : **14:57:31**, avant le commit. **Duree : 7 min 41 s.**

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-27.
