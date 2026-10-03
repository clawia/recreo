# DEC-0105 - Aucune candidate CM1 n'est ouverte pour l'instance B d'instruction de s48 ; correction
du statut, perime dans la matrice, de `DEC-0075`

- **Type** : arbitrage de perimetre (instance B d'instruction, pas de Done pedagogique) ; correction
  factuelle de `squad/programme/MATRICE-couverture.md`.
- **Date** : 2026-10-03
- **Auteur** : `camille-po`
- **Sprint** : s48 (proposition de perimetre, planning non gele)
- **Competence programme visee** : transverse - ce document porte sur l'ouverture d'un terrain
  d'instruction et sur l'exactitude de la matrice, pas sur le contenu d'une ligne.
- **Statut** : Acceptee
- **Lien** : `squad/sprints/s48-problemes-ce1-et-corrections-differees-de-s47/planning.md`, section
  3.6 (recherche de `sofia-sm`, aucune candidate trouvee) et section 4 (proposition d'instance B) ;
  `DEC-0074` (ecriture et integration des dix referentiels CM1, Jugement de faisabilite ligne par
  ligne des s28) ; `DEC-0075` (question des droits d'usage d'une image d'oeuvre) ; `DEC-0077`,
  section 4 (`DEC-0075` tranchee le 2026-09-05, option B - corpus du domaine public) ;
  `squad/programme/MATRICE-couverture.md` (cinq mentions corrigees dans le meme geste que ce
  document, voir section 2).

---

## 1. Pourquoi aucune candidate CM1 n'est ouverte a l'instance B

`sofia-sm` a cherche, dans le planning de s48, une matiere ou un niveau CM1 integre a la matrice
mais jamais instruit - le pendant du francais CE1 pour l'instance B. Je reverifie son constat plutot
que de le reprendre tel quel : les dix referentiels CM1 de `squad/programme/MATRICE-couverture.md`
(mathematiques, francais, histoire-geographie, langues vivantes, arts plastiques, education
musicale, EPS, EMC, histoire des arts, sciences et technologie) couvrent l'integralite des
enseignements du cycle 3 tels que comptes par `nadia-prof` a leur creation (un enseignement par
chapitre de programme portant ses propres attendus de fin de cycle, `DEC-0074` section 5). Aucun
onzieme enseignement de cycle 3 n'existe dans le programme de l'Education nationale qui serait
absent de cette liste. **Je n'invente donc pas de candidate par la voie 1** (integrer un referentiel
neuf) : fixer une competence ou un referentiel qui ne correspond a rien dans le programme reel
serait une fabrication de travail, pas une instruction.

Les dix referentiels CM1 ont chacun recu leur Jugement de faisabilite ligne par ligne des leur
creation (`DEC-0074`, 2026-09-03) - a la difference du francais CE1, entre dans la matrice le
2026-10-01 sans ce travail (`DEC-0104` section 1.2). **Les lignes CM1 encore `non couvert` ne sont
pas en attente d'un premier passage de `nadia-prof` : elles sont deja instruites**, chacune sous un
motif de blocage ecrit (palier 2 requis ; prerequis d'un second paquet lexical pour `LVE-CM1-05` ;
question de droits d'usage d'une image d'oeuvre). Aucune de ces lignes n'est un travail pour
l'instance B, dont le produit est un Jugement de faisabilite - un jugement deja rendu n'est pas a
refaire.

**Conclusion (voie 3) : aucune candidate CM1 n'est ouverte a l'instance B pour s48.** L'instance B
instruit le referentiel francais CE1 seul, comme le planning le propose en section 4 a defaut d'une
autre nomination.

## 2. Correction : `DEC-0075` est tranchee depuis le 2026-09-05, la matrice disait encore "non
tranchee"

En verifiant la piste la plus proche signalee par `sofia-sm` - les lignes bloquees par `DEC-0075`
(histoire des arts CM1, arts plastiques CP et CM1) -, je constate que `DEC-0075` **n'est pas une
question ouverte** : `DEC-0077`, section 4 (2026-09-05), l'a deja tranchee, option B retenue (corpus
de reproductions d'oeuvres du **domaine public**), et le fichier `DEC-0075` lui-meme porte depuis
cette date la note et le champ `Statut: Acceptee`. **Ce n'est donc pas, pour moi, une decision a
prendre** (la voie 2 proposee ne s'applique pas telle quelle : il n'y a rien a trancher).

Ce qui reste reel, et que je ne corrige pas par cette decision parce que ce n'est pas mon autorite :
**le chantier de sourcing et de verification des oeuvres, que `DEC-0077` routait a `theo-archi` via
`sofia-sm` il y a quatre sprints (s29 a s47), n'a laisse aucune trace d'engagement dans le depot** -
aucun `ADR`, aucune story, aucun paquet. Neuf lignes restent bloquees pour ce seul motif depuis un
mois. Je ne le transforme pas en nouvelle `DEC` : la decision existe deja (`DEC-0077`), c'est son
execution qui manque, et c'est exactement le risque que `CLAUDE.md` nomme pour `DEC-0009` - une
decision non appliquee fait croire que le probleme est traite.

**Ce que je corrige dans ce geste** : `squad/programme/MATRICE-couverture.md` portait, a cinq
endroits (arts plastiques CP lignes 9/10, arts plastiques CM1 ligne 7, ses conditions `ART-3`, et le
bilan d'histoire des arts CM1), la mention perimee "question de droits d'usage ouverte en
`DEC-0075`, non tranchee". Corrigee en "tranchee en `DEC-0077` (corpus du domaine public), chantier
de sourcing non engage a ce jour" - la matrice reflete desormais que le blocage actuel est un
chantier technique en souffrance, pas une question produit en attente d'arbitrage.

**Ce que je route, sans le faire moi-meme** : `sofia-sm`/`theo-archi` engagent, ou refusent
explicitement par ecrit, le chantier de sourcing du corpus d'oeuvres du domaine public route par
`DEC-0077` section 4 - sourcing, preuve de domaine public par oeuvre (`ART-3`), format
d'integration. Ce n'est ni une instruction pedagogique (l'instance B n'a rien a y faire, les lignes
sont deja jugees) ni un arbitrage produit neuf (deja fait).

## 2 bis. Amendement du 2026-10-03 (meme jour) : correction factuelle sur l'ADR du chantier de
sourcing

La section 2 ci-dessus affirme que le chantier "n'a laisse aucune trace d'engagement dans le
depot - aucun ADR, aucune story, aucun paquet". **C'est faux sur le premier point.** `theo-archi`
a ecrit `ADR-0082` (Accepte) le jour meme de `DEC-0077`, le 2026-09-05, commit `4f594d64` : il
repond aux quatre questions de `DEC-0077` section 4 (la source, la preuve de domaine public par
oeuvre, le format d'integration, qui verifie) et porte sa regle executable en garde-fou de test
(`ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest`, 18 tests verts).
`theo-archi` l'avait lui-meme omis de son avis de s48 (section 11) et corrige dans son avis de s49
(`squad/rapports/avis-faisabilite-s49-quatre-stories.md`, section 7).

Ce qui reste vrai de la section 2 : aucune story et aucun paquet n'existent, et la recherche de
sources elle-meme n'a pas commence. C'est l'execution de l'`ADR` qui a manque quatre sprints
durant, pas sa redaction. Consequences sur le perimetre de s49 : `DEC-0107`, section 9.

## 3. Ce que ce document ne fait pas

- Il n'ouvre aucune ligne CM1 a l'instance B de `nadia-prof` pour s48.
- Il n'invente aucune competence ni aucun referentiel : aucun onzieme enseignement de cycle 3 n'est
  cree.
- Il ne tranche pas a nouveau `DEC-0075` : elle l'est deja depuis `DEC-0077`.
- Il ne construit pas le chantier de sourcing du corpus d'oeuvres : routage a `sofia-sm` et
  `theo-archi`, section 2.
- Il ne modifie pas le planning de s48 : `sofia-sm` en tire les consequences (l'instance B instruit
  le francais CE1 seul).
- Il ne prononce aucun Done, technique ou pedagogique.

## 4. Impact

- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0105` ajoutee.
- `squad/programme/MATRICE-couverture.md` : cinq mentions perimees sur le statut de `DEC-0075`
  corrigees (section 2) ; aucune ligne de statut (`non couvert` / `en cours` / `couvert-valide`) ne
  bouge.
- Signale a `sofia-sm`, pour routage a `theo-archi` : le chantier de sourcing du corpus d'oeuvres du
  domaine public (`DEC-0077` section 4) reste a engager, en souffrance depuis s29.
