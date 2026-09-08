# Relecture pedagogique - Cloture de s31

**Auteur** : `relecteur-pedagogique`. **Depot mesure** : worktree dedie (`recreo-s31-relecteur-
cloture`, branche `s31-relecteur-cloture`, creee sur `472df4fc` - `round-two` - et verifiee
descendante de lui avant toute ecriture, `outils/verifier-lignee.sh`).

**Reconvocation** : `relecture.md` (bornee a `3181b5e6`) notait elle-meme, section 4, que
`rapport-qualite.md` de `yanis-qa` n'etait pas encore fusionne et restait a relire. Il l'est
desormais, avec neuf autres documents arrives depuis. Ce fichier couvre l'ecart entre les deux
bornes ; il ne reecrit pas `relecture.md`.

**Perimetre lu** : `rapport-qualite.md`, `verdicts-nadia-instance-A.md`,
`verdicts-nadia-instance-B.md`, `reaudit-nadia-instance-A.md`, `reaudit-nadia-instance-B.md`, les
dix fiches de conformite touchees ce sprint (`emotions-autrui-cp`, `formules-de-politesse-cp`,
`exclusion-cp`, `regle-souhait-loi-cp`, `melanges-cm1`, `objet-technique-cm1`, `etats-matiere-cm1`,
`regimes-alimentaires-cm1`, `familles-materiaux-cm1`, `etapes-developpement-cm1`), `ADR-0085`,
`ADR-0086`, `ADR-index.md`, `squad/decisions/REGISTRE-identifiants.md` (integral, y compris son
compte courant et les cinq notes datees qui le suivent), `DEC-0082`, `DEC-index.md`, la section
"Synthese apres les verdicts du sprint 31" de `MATRICE-couverture.md`, et la section "Cloture de
s31" plus les deux blocs "Backlog ouvert pour s32" de `docs/backlog/backlog.md`.

---

## 0. Commande 1 - le chemin de poste de travail dans ma propre relecture de s30

Traite separement, sur un commit distinct, avant le present fichier :
`squad/sprints/s30-sciences-cm1-et-le-prix-des-reserves/relecture.md`, commit
`cf6738ab6d590f89d02b96395c26a08869a71c8` sur cette meme branche. Detail complet en fin de reponse
(sortie de la commande CI, ce qui a ete corrige, les deux points de methode demandes). Ce fichier-ci
ne le reprend pas pour ne pas dupliquer la preuve.

## 1. Ce que j'ai corrige dans le perimetre de cloture

**Rien.** Verification mecanique sur l'ensemble du perimetre liste ci-dessus (tirets cadratins,
guillemets typographiques, emoji, doubles espaces, `TODO`/`FIXME`/ponctuation degradee) : aucune
occurrence. Verification du garde-fou de chemins sur l'ensemble des fichiers suivis du depot,
apres la reparation de la section 0 :

```
git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' | bash .github/scripts/verifier-chemins.sh
Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies.
```

Aucune faute d'orthographe, d'accentuation ou de ponctuation trouvee dans les dix fiches ni dans
les cinq documents de sprint. C'est un corpus tres discipline : chaque affirmation de fait est
verifiee a l'ecran ou par comptage avant d'etre ecrite, chaque chiffre porte sa methode, chaque
identifiant cite resout.

## 2. Les chiffres verifies independamment

**Le taux de sortie, nommement demande.** `MATRICE-couverture.md` annonce **63 / 138 = 45,7 %**
apres les sept verdicts de s31 (56/138 = 40,6 % avant). Recompte independant, colonne `Statut` des
dix-huit tableaux de competences, en prenant **le septieme champ de chaque ligne de tableau** (le
statut, jamais la prose qui peut le suivre dans une huitieme cellule ajoutee par un reaudit) :

| | Avant | Apres |
|---|---|---|
| `couvert-valide` | 56 | 63 |
| `en cours` | 7 | 7 |
| `non couvert` | 75 | 68 |
| `non couvrable en l'etat` | 71 | 71 |
| Total | 209 | 209 |

63/138 = 45,652...% = 45,7 % arrondi. **Les deux comptes de camille-po sont exacts au chiffre pres.**
Piege verifie et evite : une premiere passe qui prend la derniere cellule non vide d'une ligne
plutot que la septieme se laisse abuser par les quatre lignes ou un reaudit ajoute une neuvieme
colonne de texte apres le statut (`SCI-CM1-02`, `SCI-CM1-12`, deux fois chacune) - c'est exactement
l'ecart que la commande decrit.

**Le compte du registre d'identifiants.** `squad/decisions/REGISTRE-identifiants.md`, section 1,
clause 5 : `543` identifiants, `93` prefixes. Recompte mecanique sur la table de la section 4 :
**543 lignes, 543 identifiants distincts, zero doublon, 93 prefixes distincts.** Exact. Chacun des
six identifiants neufs de ce sprint (`EMO-R1/R2`, `POL-R1/R2`, `EXC-R1` a `R5`, `MAT-R1/R2`,
`DEV-R1/R2`, `RGA-R1/R2`) est bien ecrit dans le document que sa ligne nomme (clause 3
d'`ADR-0085`) : verifie par grep sur les dix fiches. Les cinq notes datees qui suivent la ligne de
compte courant (`theo-archi` a l'ouverture, `nadia-prof` instance A a ses deux passages, instance B
a ses deux passages) restent chacune juste sur son perimetre declare, aucune ne pretend dire l'etat
courant a la place de la ligne gardee - c'etait le point a verifier nommement, il tient.

**Le compte de 33 regles `ArchRule`** (`confirmation-archunit.md`, `ADR-0085`, `ADR-0086`,
`ADR-index.md`) : confirme par grep sur les cinq arbres de test hors contre-exemples. **1385 tests**
apres le sprint (avant les deux reaudits ; `d0c3c494` porte le chiffre a 1394 apres) : coherent avec
les deltas nommes (+11 `bootstrap`, +53 `adapter-content`) dans `confirmation-archunit.md`. **6
tests** dans `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` et **9 tests** dans
`LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` : confirmes par comptage direct des
`@Test` dans les deux fichiers source. **86 entrees d'`ADR-index.md` pour 86 fichiers `ADR-*.md`,
82 entrees de `DEC-index.md` pour 82 fichiers `DEC-*.md`** : les deux comptes resolvent.

**Toutes les references verifiees resolvent** : les 18 chemins `squad/*.md` cites explicitement
dans les cinq documents de sprint et les dix fiches, les treize `ADR-XXXX` cites, tous les
`DEC-XXXX` cites.

## 3. Ce que je signale sans corriger

### 3.1 `DEC-0082` - le titre attribue a `CAT-1` un compte qui n'est pas le sien

**A `camille-po`.** Le titre et le nom de fichier de `DEC-0082` disent *"le quatrieme report de
`CAT-1`"*. Le corps du meme document dit le contraire, en toutes lettres, a la section 1 : `REG-R1`
et `PER-R1` sont nees a s29 et en sont a leur quatrieme sprint sans paiement (s29, s30, s31, s32) ;
`CAT-1`, elle, **"trouvee a s31, s'y ajoute des sa naissance"** - c'est son premier routage vers
s32, pas son quatrieme. `DEC-0081`, qui ouvre s31, le confirme : *"`CAT-1` (...) est neuf et date
d'aujourd'hui"*, et elle **"entre au backlog comme story pour s32, et non pour s31"** - une premiere
entree, pas une quatrieme.

`MATRICE-couverture.md` et `DEC-index.md` evitent l'ecart : les deux disent *"le report [de
`CAT-1`, `REG-R1` et `PER-R1`] atteint son quatrieme sprint consecutif"*, groupant les trois sans
attribuer nommement le compte a `CAT-1` seule. C'est le titre de `DEC-0082` qui isole `CAT-1` du
groupe et lui attache un chiffre qui appartient a ses deux compagnes de voyage.

Je ne corrige ni le titre ni le nom de fichier : c'est une decision de `camille-po`, pas une faute
de langue sans ambiguite - la lecture la plus charitable est que le titre emploie `CAT-1` comme
raccourci du trio qui "voyage" ensemble (`DEC-0081` : *"`CAT-1`, qui voyageait gratuitement avec
`REG-R1` et `PER-R1`"*), mais un titre ne devrait pas laisser un lecteur presse croire que `CAT-1`
elle-meme a deja ete reportee trois fois. **Route a `camille-po`** : soit le titre est corrige pour
distinguer les deux historiques, soit elle confirme que le raccourci est assume et je n'y reviens
pas.

### 3.2 Aucun autre point a trancher

Le reste du perimetre lu est conforme : mecaniques de redaction propres, chiffres verifies,
references resolues, aucune donnee de fait sans provenance verifiable (voir section 4).

## 4. Convention MASTER (`DEC-0062`)

Aucune occurrence de "MASTER" dans les documents de cloture de s31. Tous les chiffres et
affirmations de fait portent leur provenance sous la forme 1 (agent de la squad, fichier et
section) : les verdicts et reaudits de `nadia-prof` citent systematiquement le commit audite, le
worktree, le port et la methode de comptage ; les corrections de `theo-archi` et `sofia-sm` dans le
registre portent chacune sa date, son auteur et sa methode. Rien a signaler.

## 5. Ce que je n'ai pas touche

Le contenu servi a l'enfant (les dix paquets YAML touches ce sprint) reste au perimetre de
`nadia-prof` : ses dix verdicts sont deja rendus et cette relecture ne les rejuge pas. Je n'ai
modifie ni `MATRICE-couverture.md`, ni `docs/backlog/backlog.md`, ni aucun `ADR` ou `DEC`, ni
`review.md`/`retro.md`/`RAPPORT.md` (proprietes de `sofia-sm`, inexistants a ce jour).
