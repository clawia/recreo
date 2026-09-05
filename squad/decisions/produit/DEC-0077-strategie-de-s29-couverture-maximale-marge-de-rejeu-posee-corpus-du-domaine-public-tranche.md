# DEC-0077 - Strategie de s29 : couverture maximale sur trois matieres bon marche ; la marge de rejeu est posee comme question ouverte ; DEC-0075 est tranchee (corpus du domaine public)

- **Type** : deux arbitrages de l'utilisatrice, plus une question ouverte posee a l'ouverture de s29
  sans etre tranchee ici.
- **Date** : 2026-09-05
- **Auteur** : `camille-po`
- **Sprint** : s29 (ouverture)
- **Competence programme visee** : transverse - pas de contenu pedagogique. Cette decision arbitre
  un perimetre et une question de gouvernance de sprint ; elle ne fixe aucune competence ligne a
  ligne - ce chiffrage est celui de l'arbitrage prealable de `nadia-prof`, en cours d'ecriture en
  parallele de ce document, et son chiffre remplace celui donne ici a titre d'estimation.
- **Statut** : Acceptee.
- **Lien** : `DEC-0074` (perimetre de s28, tarif "suis-je la source ?"), `DEC-0075` (tranchee par la
  presente decision), `DEC-0076` (arbitrage de cloture de s28), `ADR-0079` (l'unite de la borne de
  `nadia-prof` est le lot de matiere), `squad/conformite/ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-
  ligne-et-le-prix-des-quatre-lots.md` (regles `FER-1` a `FER-3`, tarif 1,0/1,5),
  `squad/sprints/s28-second-passage-et-chantier-palier-2/retro.md`,
  `squad/programme/MATRICE-couverture.md`, `squad/programme/sciences-et-technologie/competences-
  cm1.md`, `squad/programme/enseignement-moral-et-civique/competences-cp.md` et `competences-
  cm1.md`, `squad/programme/questionner-le-monde/competences-cp.md`.

---

## 1. Etat de depart, recompte avant de m'en servir

Recompte ligne a ligne des dix-huit tableaux de `squad/programme/MATRICE-couverture.md`, colonne
`Statut`, sur l'etat du fichier apres la cloture de s28 (`DEC-0076`, commit `b37405d`) : **43
`couvert-valide`, 10 `en cours`, 85 `non couvert`, 71 `non couvrable en l'etat`, 209 lignes au
total, 138 lignes au denominateur atteignable (209 - 71). 43 / 138 = 0,3116... = 31,2 %.** Ce
compte converge exactement avec celui deja ecrit dans la matrice ("Synthese apres les verdicts du
sprint 28") : **aucun ecart trouve.**

Verification ciblee sur les trois matieres du premier arbitrage, ligne a ligne dans leurs tableaux
respectifs : sciences et technologie CM1 compte 19 lignes `non couvert` sur 20 (une seule `non
couvrable en l'etat`, la demarche d'investigation) ; enseignement moral et civique compte 12 lignes
`non couvert` au CP et 11 au CM1, soit 23 ; questionner le monde CP compte **12** lignes `non
couvert` - et non 15 comme l'affiche encore le "Bilan" ecrit sous ce tableau, qui date d'avant les
trois fermetures de s28 (`QLM-CP-01`, `QLM-CP-03`, `QLM-CP-05`) et n'a pas ete corrige depuis. Cette
phrase de bilan est perimee ; elle n'affecte aucun chiffre cite dans la presente decision, qui est
recompte directement sur les lignes du tableau et non sur cette phrase - je la signale plutot que de
la laisser trainer sans mention, sans la corriger ici pour ne pas elargir le perimetre de ce
document a une retouche de redaction qui n'est pas son objet.

## 2. Premier arbitrage : la strategie de s29 est la couverture maximale

**L'utilisatrice retient cinq lots de vingt items, sur les seules matieres ou `nadia-prof` est la
source du referentiel** : sciences et technologie CM1 (19 lignes `non couvert`), enseignement moral
et civique aux deux cycles (23 lignes), questionner le monde CP (12 lignes). **Total annonce : 5,0
sur une borne de 5.**

**Le raisonnement presente a l'utilisatrice, qui gouverne ce choix et doit rester lisible ici** :
depuis `FER-2` (`ARBITRAGE-PREALABLE-s28...`, section 2), **une ligne ferme a partir de dix items
qui la servent** - un lot de vingt items ferme donc environ deux lignes, quelle que soit la
matiere. Ce qui change d'une matiere a l'autre n'est pas le rendement en lignes, c'est le **prix a
l'item** : `nadia-prof` a ecrit que son tarif ne suit pas la distinction neuve/connue mais la
question **"suis-je la source de cette matiere ?"** - 1,0 quand elle l'est (elle a ecrit elle-meme
le referentiel, comme pour ces trois matieres, `DEC-0074`), 1,5 quand elle ne l'est pas et doit
verifier chaque fait contre une source externe (histoire-geographie, anglais). **Le levier de
couverture disponible pour s29 est donc le choix des matieres bon marche, pas le choix des lignes
a l'interieur d'une matiere.**

**Cible annoncee : environ dix lignes fermees, soit environ 53/138 = 38 %.** Cette cible est
**une estimation de l'orchestrateur, pas un engagement** : c'est `nadia-prof` qui chiffre le
plafond honnete de s29 dans son arbitrage prealable, en cours d'ecriture en parallele de la
presente decision, et **son chiffre remplace celui-ci** des qu'il est ecrit.

**Ce que ce choix ecarte, ecrit sans l'adoucir** :

- **Les neuf lignes d'histoire-geographie CM1 ouvertes depuis s27 ne bougent pas** dans s29. Elles
  restent `en cours`, exactement au tarif de 1,5 qui les a exclues de ce perimetre.
- **Aucune reserve de s28 n'est payee.** `POS-R1` (structure repetee des distracteurs de la
  division posee, `MAT-CM1-05`) n'est pas reprise, alors que `nadia-prof` l'a signalee prioritaire
  sur une ligne **deja verte** - une reserve sur du `couvert-valide` reste donc ouverte un sprint
  de plus. La dette des paquets a voix (six jeux, cinq indices), due depuis s26, reste due depuis
  trois sprints (`DEC-0074`, section amendee ; `DEC-0076`).

## 3. Question posee a l'ouverture de s29, non tranchee ici : que fait-on d'un refus tardif ?

La retro de s28 (`squad/sprints/s28-second-passage-et-chantier-palier-2/retro.md`) nomme un point
que ce depot n'a jamais ecrit comme un choix assume : **la reponse par defaut a un refus de
`nadia-prof` survenant pres de la fin d'un sprint deja a sa borne.** s28 s'est trouve a 5,25 sur une
borne de 5 (`DEC-0076`) parce que cette question ne s'est posee qu'a la cloture, avec trois items
faux deja en production le temps du debat.

**s29 repart a 5,0 sans marge, sur la meme construction que s28.** Si un lot est refuse en cours de
sprint, trois issues existent, et je ne tranche a la place de personne :

- **Le lot part a s30.** C'est la reponse par defaut de `ADR-0079` : la borne de `nadia-prof` est
  concue sans marge, un refus tardif se corrige au sprint suivant. Cout : le contenu refuse reste
  hors catalogue le temps du report (rien de faux ne reste servi, contrairement a s28, puisqu'un
  refus retient l'increment hors catalogue tant qu'il n'est pas leve).
- **Le sprint depasse sa borne**, comme en s28 (5,25 sur 5), sur arbitrage explicite de
  l'utilisatrice au moment du refus. Cout : `nadia-prof` audite un cinquieme lot dans le sprint qui
  l'a deja portee a sa borne, au tarif normal du lot concerne (1,0 pour les trois matieres de
  la section 2).
- **Un lot est retire d'avance du perimetre**, pour garder 0,25 de marge de rejeu des l'ouverture.
  Cout chiffre : a raison d'environ deux lignes par lot de vingt items (`FER-2`), retirer un
  cinquieme du perimetre pour se menager une marge coute **environ deux lignes** sur la cible de
  dix annoncee en section 2 - soit une cible revisee a environ huit lignes, 51/138 = 37 %.

**Cette question est ouverte.** Elle doit etre tranchee avec `nadia-prof` avant que s29 ferme son
perimetre, pas redecouverte au premier refus comme en s28.

## 4. Second arbitrage : `DEC-0075` est tranchee - corpus de reproductions d'oeuvres du domaine public

**L'utilisatrice retient l'option B de `DEC-0075`** : un corpus de reproductions d'oeuvres du
**domaine public**. `theo-archi` instruit la source et la forme technique ; `nadia-prof` valide le
choix des oeuvres retenues, comme pour tout contenu.

`DEC-0075` passe du statut `Proposee` au statut **`Acceptee`** (note et champ `Statut` mis a jour
dans le fichier lui-meme). Ce que cette decision implique :

- **Elle debloque jusqu'a neuf lignes atteignables** pour les sprints suivants : 2 en arts
  plastiques CP (lignes 9, 10), 2 en arts plastiques CM1 (lignes 7, 8, sous `ART-3`), 5 en
  histoire des arts CM1 (lignes 1, 2, 3, 4, 6). Ces neuf lignes restent `non couvert` aujourd'hui -
  cette decision ouvre un chantier d'acquisition, elle ne sert aucun contenu.
- **Elle ne ferme aucune ligne dans s29.** Constituer et verifier un corpus de reproductions n'est
  pas un lot de contenu pilotable comme les cinq lots de la section 2 : c'est un chantier de
  sourcing et de verification de droits, et s29 est deja plein a 5,0 sans lui.
- **Les questions qui restent ouvertes ne sont pas tranchees ici** : quelle source de reproductions
  (musee en licence ouverte, corpus dedie au domaine public, autre), quelle preuve de domaine
  public exiger par oeuvre (`ART-3` : identification, auteur, date, lieu de conservation), quel
  format d'integration au contenu, qui verifie chaque preuve avant qu'une oeuvre entre au depot.
  **Je les route a `theo-archi`**, via `sofia-sm` : ce sont des choix de sourcing technique et de
  forme, pas des choix pedagogiques ni des choix qui m'appartiennent.

## 5. Ce que cette decision ne fait pas

- Elle ne fixe pas la composition ligne a ligne de s29 : c'est l'arbitrage prealable de
  `nadia-prof`, qui chiffre le plafond honnete et remplace l'estimation de la section 2.
- Elle ne tranche pas la question de la section 3 : elle est ecrite comme ouverte et le reste tant
  que `camille-po` et `nadia-prof` ne l'ont pas arbitree ensemble.
- Elle ne tranche aucune des questions de sourcing ou de forme du corpus d'oeuvres (section 4) :
  routees a `theo-archi`.
- Elle ne fait bouger aucune ligne de `squad/programme/MATRICE-couverture.md` : ni les cinq lots de
  s29 (pas encore livres), ni les neuf lignes debloquees par l'option B (pas encore servies).
- Elle ne prononce aucun Done pedagogique.

## 6. Impact

- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0077` ajoutee ; ligne `DEC-0075` mise a jour
  (statut `Acceptee`, option B, renvoi a `DEC-0077`).
- `squad/decisions/produit/DEC-0075-corpus-de-reproductions-d-oeuvres-et-droits-d-usage-question-
  ouverte.md` : note ajoutee en tete, champ `Statut` mis a jour ; le corps du document (constat,
  perimetre des neuf lignes, trois options) reste tel qu'ecrit le 2026-09-03, non efface.
- Signale a `sofia-sm`, hors de ce fichier : routage vers `theo-archi` des questions de source, de
  preuve de domaine public, de format et de verification du corpus d'oeuvres (section 4) ; routage
  vers `nadia-prof` de la question de la marge de rejeu (section 3), a trancher avant la fermeture
  du perimetre de s29.
- `squad/programme/MATRICE-couverture.md` : inchangee par cette decision quant a ses lignes ;
  deux bilans de matiere perimes y sont corriges par l'amendement de la section 7 ci-dessous.

## 7. Amendement du 2026-09-05 (meme jour) - deux bilans de matiere perimes corriges, question de garde-fou routee

**Ce que j'ai verifie, a la demande de `sofia-sm`.** Le "Bilan" de la table *Questionner le monde -
CP* affichait encore "15 `non couvert`" apres les trois fermetures de s28 (`QLM-CP-01`, `QLM-CP-03`,
`QLM-CP-05`) : la fermeture avait mis a jour les trois lignes concernees sans mettre a jour le total
qui les resume. J'ai verifie les dix-sept autres bilans de matiere du meme fichier, table par table,
en recomptant chaque tableau ligne a ligne plutot qu'en reprenant un total ecrit :

- **Quatre tables (mathematiques CP, mathematiques CM1, francais CP, francais CM1) ne portent aucune
  phrase de bilan** : ce sont les quatre tables les plus anciennes, dont l'histoire est ecrite ligne
  par ligne au fil des sprints plutot que resumee par une phrase unique. Rien a corriger, rien a
  verifier de cette famille sur elles.
- **Treize tables portent une phrase "Bilan"** (les huit matieres integrees le 2026-09-02 et les dix
  referentiels integres le 2026-09-03). **Deux sont perimees** :
  - *Questionner le monde - CP* : "16 lignes, 15 `non couvert`, 1 `non couvrable en l'etat`"
    -> corrige en "16 lignes, 12 `non couvert`, 3 `couvert-valide`, 1 `non couvrable en l'etat`"
    (denominateur atteignable inchange, 15).
  - *Langues vivantes (anglais) - CM1* : "7 lignes, 4 `non couvert`, 3 `non couvrable en l'etat`"
    -> corrige en "7 lignes, 2 `non couvert`, 1 `en cours`, 1 `couvert-valide`, 3 `non couvrable en
    l'etat`" (denominateur atteignable inchange, 4).
  - **Les onze autres** (histoire-geographie CM1, langues vivantes CP, arts plastiques CP et CM1,
    education musicale CP et CM1, EPS CP et CM1, EMC CP et CM1, histoire des arts CM1, sciences et
    technologie CM1) **sont exacts** : soit la matiere n'a pas ete touchee par s28, soit son bilan
    avait deja ete mis a jour dans le meme geste que la fermeture (histoire-geographie CM1 porte
    d'ailleurs deux bilans dates, l'un a la cloture de s27, l'autre a celle de s28, tous deux
    exacts).
- **Le total du fichier n'etait pas affecte** : le recompte du 43/138 (section 1) porte sur les
  lignes elles-memes, pas sur les phrases de bilan qui les resument ; les deux corrections
  ci-dessus ne changent donc aucun chiffre cite ailleurs dans cette decision ou dans la synthese de
  cloture de s28.

**Ce que je route, sans le construire moi-meme.** Le motif de cette derive est mecanique et se
reproduira tant que rien n'y change : un verdict qui deplace une ligne (fermeture ou ouverture) met
a jour la ligne, mais rien n'oblige a mettre a jour, dans le meme geste, le total ecrit en dessous
du tableau - deux gestes distincts, dans le meme fichier, et rien qui les relie. C'est la meme
famille de defaut qu'une reference qui pointe vers rien ou qu'un chiffre sans methode : un total
qui peut se lire juste alors qu'il decrit un autre etat que celui du tableau qu'il resume, et le
mode de defaillance n'est pas l'echec mais l'ecart muet - personne n'est averti, le fichier reste
lisible, le total est simplement faux. **Je pose la question, je ne la tranche pas** : faut-il un
garde-fou qui verifie mecaniquement qu'un bilan de matiere correspond au compte reel des lignes du
tableau qu'il resume (calcul, script, ou autre forme que `theo-archi` jugera), ou bien la revue de
`sofia-sm` a chaque fusion de matrice suffit-elle comme controle humain ? Je n'ai pas d'avis a
imposer sur le moyen : c'est une question d'outillage, pas de produit. **Route a `sofia-sm`, pour
instruction par `theo-archi` si elle le juge necessaire**, a poser maintenant plutot qu'apres une
troisieme derive.
