# Arbitrage prealable - s53 : les points de relecture P1 a P11 des paquets de la session de contenu

`nadia-prof`, le 2026-10-07. Passage unique de decision de s53 (`DEC-0112`, section 2 ; planning de s53, U2).

**Audit sans jeu.** Aucun des paquets cites n'est branche au catalogue : aucun n'est jouable, je n'en ai joue
aucun. Je decide sur la lecture des fichiers de paquet eux-memes (`adapters/adapter-content/src/main/resources/contenu/`,
arbre `d13d28f4`), des propositions de `marc-dev` et de `lea-dev`, de la relecture de P11, de l'avis de `theo-archi`, de
la lecture de `yanis-qa` et de `DEC-0112`. **Ce document ne prononce aucun Done pedagogique, aucun verdict, aucun statut
de matrice.** Chaque paquet cite passera devant moi en premier passage complet, au tarif d'un lot, le jour ou il sera
branche. **Aucun identifiant de condition neuf n'est ecrit ici** : je ne cite que des identifiants deja alloues.

Convention des blocs YAML : ils portent l'espace insecable (U+00A0) devant `?` et `:` et a l'interieur des guillemets
francais, comme les paquets (`TYP-1`). Un bloc est un item complet, a coller a la place de l'item de meme numero.

## Resume

| Point | Decision | Texte a appliquer |
|---|---|---|
| P1 | corriger : "les beaux chapeaux" | proposition de `marc-dev`, P1, tel quel |
| P2 | texte confirme ; **reprise demandee** de l'indice des items 10 et 13 | section 2 |
| P3 | corriger : "la liberte" remplacee par "la democratie" | section 3 |
| P4 | indices 6 a 11 reecrits ; 1 a 5 gardes. `TRV-CE1-5` ne s'applique pas a l'EMC CM1 | section 4 |
| P5 | corriger : la proposition de `lea-dev` est refusee, item reecrit par negation | section 5 |
| P6 | corriger les deux items | proposition de `marc-dev`, P6, les deux items tels quels |
| P7 | confirme | rien a appliquer (deja applique, `f43e5f96`) |
| P8 | item 16 corrige ; item 15 et item 3 acceptes | proposition de `lea-dev`, item 16, tel quel |
| P9 | je n'exige pas de ligne `DEGRADE` | rien |
| P10 | item 7 : un distracteur admis ; item 12 : deux distracteurs exiges | sections 5 et 10 |
| P11 | accepter l'espace ordinaire dans `competence`, aucun YAML change | rien |

---

## 1. P1 - `ce1-accord-groupe-nominal`, item 1 : corriger, forme "les beaux chapeaux"

**La norme.** Devant un adjectif pluriel place avant le nom, l'ecrit soutenu emploie "de" et non "des" : *de beaux
chapeaux*. "des" reste admis quand l'adjectif et le nom forment un nom compose (*des petits pois*, *des jeunes gens*),
ce qui n'est pas le cas ici. *Des beaux chapeaux* est courant a l'oral, mais un item qui demande "Quel groupe de mots est
bien ecrit ?" ne peut pas proposer en bonne reponse une forme qu'une grammaire scolaire corrige. Le point est fonde.

**Pourquoi "les" et non "de".** Je retiens la proposition de `marc-dev`. La forme normee *de beaux chapeaux* ferait
entrer dans l'item une regle que `FRA-CE1-15` ne nomme pas (`GNA-CE1-1` : l'adjectif, le `-x`, le feminin), et un
enfant de CE1 qui vient d'apprendre "des" comme pluriel de "un" verrait un "de" qu'aucun item ne lui explique. "les"
garde ce que l'item travaille, le `-x` de *beaux* et de *chapeaux*, sans rien y ajouter. J'ai verifie dans le fichier
que l'item 1 est le seul "des" suivi d'un adjectif place avant le nom.

**Texte** : proposition de `marc-dev`, P1, tel quel (`propositions-marc-dev.md`, bloc "Texte propose, item complet").
Familles `GNA-CE1-3` (a) et (c) inchangees ; les comptes de l'en-tete ne bougent pas.

## 2. P2 - `ce1-pronoms` : texte confirme, reprise de deux indices

**Les trois textes reecrits par `lea-dev` (`9bd0ad21`) sont confirmes.** J'ai relu les vingt items. Aux items 10
("Ilyes donne un gateau a Sofia. Elle dit merci."), 13 ("Leo lit un livre a Rose. Elle ecoute bien.") et 14 ("Enzo et
Maya rangent leurs affaires. Ils les posent sur l'etagere."), un seul candidat a le genre et le nombre du pronom :
l'accord decide, et le compte de l'en-tete (dix par le sens, dix par l'accord) redevient exact.

**Reprise demandee : l'indice des items 10 et 13 conduit a la mauvaise reponse.** Item 10 : *"Relis la phrase d'avant
pour trouver qui agit."* Dans la phrase d'avant, celui qui agit est Ilyes, qui donne : c'est un distracteur. Item 13 :
*"Cherche qui fait l'action dans la phrase d'avant."* Celui qui fait l'action est Leo, qui lit : distracteur aussi.
L'enfant qui a deja fait une erreur et suit l'indice choisit le second distracteur. C'est un indice qui enseigne une
regle fausse, ce que `TRV-CE1-5` exclut. Le defaut n'est pas ne de la correction (il existait avec "une pomme" et "une
histoire"), mais la correction a touche ces items et l'a laisse. Sur ces deux items classes "l'accord", l'indice doit
nommer l'accord. Texte a appliquer, identique sur les deux items, enonce, texte, propositions inchanges :

```yaml
    indice: " Elle  désigne un mot féminin : cherche-le dans la phrase d'avant."
```

Dix mots sous la convention de comptage (section 4.2). Il dit une methode, l'accord, que l'en-tete du paquet declare
legitime au CE1 ; il ne nomme pas la reponse.

**Observation pour l'audit, non exigee a s53** : aux items 3, 6, 9 et 14, le meme indice "qui agit" tombe juste par
coincidence. Je jugerai a l'audit si un indice d'agent sur un item d'accord est acceptable quand il ne trompe pas.

## 3. P3 - `cm1-emc-principes-republique`, item 4 : corriger, "la democratie"

**Le point est fonde, et c'est l'item qui est faux, pas l'en-tete.** L'en-tete applique `EMC-5` : aucun distracteur
n'est le principe voisin que l'enonce permettrait aussi de nommer. "Ne favorise aucune religion" se lit sans erreur
comme une liberte, et le paquet lui-meme definit la laicite par "la liberte de croire ou de ne pas croire" (item 9).
"la liberte" sort.

**Le remplacant.** Ni "l'egalite" (la Constitution, article 1er, assure l'egalite "sans distinction de religion" :
meme piege), ni "le refus des discriminations" (meme motif). Des deux propositions de `marc-dev`, je retiens **"la
democratie"** et j'ecarte "la securite". "La securite" n'est pas un principe de la Republique : un enfant qui connait
les cinq principes l'elimine sans lire l'enonce, et l'item ne garde qu'un distracteur reel. "La democratie" est un
caractere de la Republique (Constitution, article 1er, deja cite par l'en-tete), donc un distracteur plausible, et rien
dans l'enonce ne parle de vote ou de decision collective.

```yaml
  - enonce: "Quel principe de la République garantit que l'école publique ne favorise aucune religion ?"
    bonneReponse: "la laïcité"
    distracteurs: ["la fraternité", "la démocratie"]
    indice: "Ce principe dit comment l'école et l'État se comportent envers les croyances."
```

Trois propositions, le motif de `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` ("11 items", "33
propositions") ne change pas.

## 4. P4 - `cm1-emc-principes-republique`, indices

### 4.1 `TRV-CE1-5` ne s'applique pas a l'EMC CM1

**Non, ni `TRV-CE1-5` ni sa borne de douze mots ne s'appliquent a ce paquet.** `TRV-CE1-5` est l'une des sept
conditions transverses de l'arbitrage de francais CE1, ecrites "valables pour chacune des vingt-six lignes" de francais
CE1 (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`, section 2). Je ne l'etends pas par ce document a une autre matiere ni a
un autre niveau. Aucune borne de longueur d'indice n'existe au CM1, et je n'en pose pas.

**Ce qui s'applique a ce paquet, par la presente decision et sans identifiant** : un indice ne redit pas le contenu de
la bonne reponse de son propre item. L'indice s'affiche apres la premiere erreur (`BanditManchotSession.indice()` :
vide tant qu'aucun essai n'a echoue) : il reste alors deux propositions, et un indice qui reformule la reponse transforme
le second essai en recopie.

### 4.2 Regle de comptage des mots

Celle d'`ADR-0089`, posee par moi a s32 (section 16.1 de l'arbitrage de s32) et reprise sans changement au CE1
(`ARBITRAGE-s36-longueur-d-enonce-au-ce1.md`, section 2) : un mot est une suite de caracteres separee par des espaces et
contenant au moins une lettre ou un chiffre. **Une forme elidee compte pour un mot** (`qu'il`, `d'une`, `s'arrete`,
`Qu'est-ce`) ; l'apostrophe et le trait d'union ne separent rien ; un guillemet ou un signe isole n'est pas un mot. C'est
la regle qu'execute `AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest`. Sous elle, le comptage de `yanis-qa` est le
bon : la ligne 92 (item 6) compte douze mots. Elle vaut partout ou une borne de mots existe ; elle ne cree pas de borne
la ou il n'y en a pas.

### 4.3 Item par item

Critere : l'indice redit-il le contenu de la bonne reponse au point qu'on choisit sans connaitre la notion ? Aux items
1 a 5, la reponse est le **nom** d'un principe et l'indice glose la situation : l'enfant doit encore relier le sens au
nom, ce qui est la competence lue a l'envers. Aux items 6 a 11, la reponse est le **sens** d'un principe, et l'indice
le redonne.

| Item | Decision | Motif |
|---|---|---|
| 1, 2, 3, 4 | garder | glose de la situation, le nom reste a trouver |
| 5 | garder | meme nature que 1 a 3 ; seize mots, sans borne au CM1 |
| 6 | reecrire | "la ou commencent les droits des autres" redit "nuise a autrui" |
| 7 | reecrire | "leurs droits sont les memes" reprend la reponse mot pour mot |
| 8 | reecrire | "solidarite" redit "s'entraider", "ne se connaissent pas" ecarte un distracteur |
| 9 | reecrire | "decide pour lui-meme" redit "croire ou ne pas croire", "l'ecole ne choisit pas" ecarte un distracteur (ajoute par `marc-dev`, je le retiens) |
| 10 | reecrire | "en faire une raison de traiter mal" reprend la reponse ; la phrase finit par un "si" qu'un enfant de CM1 ne lit pas sans aide |
| 11 | reecrire | "ne prend parti pour aucune" redit "sans en favoriser aucune" |

**Textes.** Je retiens la proposition de `marc-dev` pour l'item 6. Pour les items 7 a 11, je refuse ses textes : ce sont
des consignes de lecture qui conviendraient a n'importe quel item ("Lis les trois phrases et elimine celles qui te
semblent fausses"), et un indice qui n'aide personne vaut une absence d'indice, que `ChaquePaquetPorteUnIndiceTest`
interdit. Je donne a la place un exemple concret que l'enfant doit relier lui-meme au sens du principe, sans reprendre
les mots de la reponse.

Item 6 : proposition de `marc-dev`, P4, item 6, tel quel.

```yaml
  - enonce: "Que veut dire l'égalité dans la devise de la République ?"
    bonneReponse: "tous ont les mêmes droits, sans distinction"
    distracteurs: ["tous doivent être pareils", "tous doivent gagner la même somme"]
    indice: "Pense à une règle de la classe qui vaut pour chaque élève."
  - enonce: "Que veut dire la fraternité dans la devise de la République ?"
    bonneReponse: "se soutenir et s'entraider entre citoyens"
    distracteurs: ["avoir les mêmes idées que les autres", "faire partie de la même famille"]
    indice: "Pense à des personnes qui apportent leur aide après une tempête."
  - enonce: "Que garantit la laïcité à chaque élève de l'école publique ?"
    bonneReponse: "la liberté de croire ou de ne pas croire"
    distracteurs: ["l'enseignement de la même religion à tous les élèves", "le droit de choisir les règles de l'école"]
    indice: "Pense à des élèves d'une même classe aux croyances différentes."
  - enonce: "Que veut dire refuser les discriminations ?"
    bonneReponse: "ne pas traiter quelqu'un moins bien à cause de ce qu'il est ou de ce qu'il croit"
    distracteurs: ["ne jamais remarquer les différences entre les personnes", "éviter de parler aux personnes différentes de soi"]
    indice: "Pense à un club de sport qui refuse un enfant pour son origine."
  - enonce: "Que veut dire « la République est laïque » ?"
    bonneReponse: "elle respecte toutes les croyances sans en favoriser aucune"
    distracteurs: ["elle impose à tous la religion de la majorité", "elle demande à chaque citoyen de choisir une religion"]
    indice: "Pense à un arbitre de match, qui ne joue pour aucune équipe."
```

Les enonces, bonnes reponses et distracteurs sont ceux du fichier, inchanges. Indices : 12, 11, 10, 13 et 12 mots.
Aucun n'enonce un interdit de croire ni ne juge une conviction (`BIA-5`, `EMC-4`) ; l'exemple de l'item 10 decrit un
acte, il n'attribue rien a un groupe.

### 4.4 Q7 - `PAR-14` sur P4

**`PAR-14` ne s'applique pas.** Elle chiffre une **reprise** apres un audit (arbitrage de s33, section 5.5 : "une reprise
dont les corrections touchent plus de la moitie des items d'un paquet se paie au tarif d'un lot"). Ce paquet n'a jamais
ete audite : il n'y a ni audit precedent a couvrir, ni rejeu a tarifer. Six indices sur onze (54,5 %) changent avant le
premier passage, qui sera de toute facon un passage complet au tarif d'un lot. La lecture du planning est juste.

## 5. P5 et P10 - `cp-qlm-vocabulaire-de-position`, item 7 : corriger par negation

**La proposition de `lea-dev` est refusee, pour deux motifs.** (1) Elle compte 25 mots et trois phrases : elle viole
`LON-1` et `LON-2`, deux fois la borne du CP. (2) Elle change la competence : ordonner trois animaux par deux
comparatifs ("plus loin que") est un raisonnement de transitivite, plus difficile que l'emploi du vocabulaire de
position que la ligne `QLM-CP-10` demande, et la tache "extremite d'un alignement" existe deja aux items 12 a 14.

**J'ai ecarte aussi la relation inverse** ("Le chien est loin de la porte. Ou est la porte par rapport au chien ?") :
"loin" est symetrique, la reponse reprendrait le mot de l'enonce, et l'en-tete du paquet dit que l'enfant "ne recopie
pas" le mot juste. Ce serait corriger P5 en reproduisant le defaut de P5.

**Decision : l'item passe a la tache "contraire d'un mot de position"**, en contexte : l'enonce nie un mot, l'enfant
nomme la position par le contraire. Recopier le mot de l'enonce mene au distracteur.

```yaml
  - enonce: "Le chien n'est pas près de la porte. Où est le chien ?"
    bonneReponse: "loin de la porte"
    distracteurs: ["près de la porte"]
    indice: "Pense à la distance entre le chien et la porte."
```

Douze mots, deux phrases, la seconde est la question (`LON-1`, `LON-2`). Consequence d'ecriture, dans le meme commit :
l'en-tete `FER-3` dit "relation inverse (items 1 a 6)" et "contraire d'un mot de position (items 7 a 11)". `FER-1` ne
change pas.

**P10 sur cet item (Q5) : un seul distracteur est admis.** Pres et loin sont la paire que le libelle de la ligne nomme
lui-meme ("pres/loin") : sur une distance dite de deux mots, l'item est une dichotomie fermee, de la meme nature que les
dichotomies que j'ai tenues (`FICHE-bandit-manchot-homophones-cm1.md`). Un troisieme bouton serait une position d'un autre
axe ("devant la porte"), que l'enonce n'exclut pas : il y aurait deux reponses acceptables. Le motif du garde-fou de rang
("41 propositions", "certains items portent un seul distracteur") reste exact.

## 6. P6 - `ce1-masses-contenances` : corriger les deux items

"Le mot kilo veut dire mille." donne le fait que les deux items demandent : a "1 kg, combien de g ?", "mille" est
"1000" ; a "1000 g, combien de kg ?", c'est la meme egalite lue dans l'autre sens. Apres une premiere erreur, il reste
deux boutons et l'indice designe le bon. Je corrige les deux, pour le motif de `marc-dev` : garder l'un et pas l'autre
ferait croire a une difference de formulation la ou il n'y a que le sens de la conversion.

**Texte** : proposition de `marc-dev`, P6, les deux items tels quels. Les deux indices disent ou regarder (le mot
*kilogramme*), sans nombre ni relation ; ils n'enseignent pas que la taille donne la masse (`MAS-CE1-6`).

**Observation de `marc-dev` sur les items de choix d'unite** ("Un kilogramme, c'est mille grammes.") : hors de P6, et
je ne l'exige pas a s53. Sur son propre item, la phrase est une methode (une pomme pese moins de mille grammes) ; qu'elle
donne d'avance la reponse des items de conversion releve de la question ouverte en section 13.

## 7. P7 - `cm1-hda-epoque-civilisation`, item 4 : confirme

Le texte applique par `lea-dev` (`f43e5f96`) est confirme. L'enonce decrit un type d'ouvrage (aqueduc en pierre a
plusieurs etages d'arches) sans nommer d'oeuvre ; il reste exact et suffisant : l'aqueduc a arches superposees est un
marqueur de l'architecture romaine, et l'indice sur la Gaule departage la Grece. Sa recherche d'autres noms dans les
enonces (Zeus, Louis XIV, Paris, 1889 : un dieu, un roi, une ville, une date) est juste. **La mention du pont du Gard au
commentaire d'en-tete (ligne 15) n'est pas un ecart** : c'est la source d'un fait, que l'enfant ne lit pas ; la phrase
de la ligne 6 parle de ce que le paquet sert.

## 8. P8 - `ce1-texte-documentaire` : item 16 corrige, items 15 et 3 acceptes

**Balayage.** Commande de `lea-dev`, relancee par moi sur `d13d28f4`, sur les lignes non commentees :
`grep -nwiE 'ton|ta|tes|mon|ma|mes|notre|nos|votre|vos|son|sa|ses|leur|leurs' ce1-texte-documentaire.yaml`. Occurrences
de possessif :

| Lignes | Item | Occurrence | Decision |
|---|---|---|---|
| 41, 42 | 3 | "ses oeufs" (le crocodile) | accepter : troisieme personne, un animal ; rien ne s'adresse a l'enfant |
| 114 | 15 | "Ecris ton prenom en haut." | accepter |
| 120, 121, 122 | 16 | "ton cahier", "ta trousse", "ta chaise" (texte et propositions) | corriger |

**Item 16 : corriger.** `TRV-CE1-6` exclut que ce qui s'adresse a l'enfant suppose un objet possede ; une trousse et un
cahier a soi sont exactement cela. **Texte** : proposition de `lea-dev`, P8, item 16, tel quel ("Range le cahier. Ferme
la trousse. Mets la chaise sous la table.", bonne reponse "la chaise"). Le texte support et les propositions changent
ensemble, ce qui garde le verdict de `AucunePhraseDuTexteSupportNeSuffitAGagnerLaMajoriteTest` (a lancer, pas a deduire).

**Item 15 : accepter, aucun YAML change.** Un prenom n'est pas un objet possede : tout enfant en a un, et "ecris ton
prenom" est la consigne la plus commune d'une fiche de classe. La variante "Ecris un prenom en haut" ferait ecrire a
l'enfant le prenom de quelqu'un d'autre, ce qui n'a pas de sens.

**Ce que la recherche doit rendre apres correction** : la commande de `lea-dev` rend les lignes de "ses oeufs" (deux) et
celle de "ton prenom" (une), comparees par contenu et non par numero ; la commande du planning (sans `son`, `sa`, `ses`,
`leur`, `leurs`) rend la seule ligne de "ton prenom".

## 9. P9 - aucune ligne `DEGRADE` exigee

Je n'exige pas de ligne `DEGRADE` sur les trois paquets. J'ai relu les enonces : `cm1-emc-atteinte-autrui` sert
"reconnaitre" (items 1 a 6) et "savoir a qui s'adresser" (items 7 a 11) ; `cm1-emc-respect-differences` sert
"identifier ce qu'interdit" ; `cm1-emc-principes-republique` sert "comprendre le sens". Aucun verbe du libelle n'est
laisse sans item. Je fais mien le critere de `DEC-0112`. La mention de la matrice reste due a la fermeture de chacune
des trois lignes, et je la juge a l'audit.

## 10. P10 - `cp-qlm-situer-la-france`, item 12 : deux distracteurs exiges (Q5)

**Ce n'est pas une dichotomie fermee.** L'emboitement des espaces a trois niveaux naturels (une ville, un pays, un
continent) ; seul l'enonce le reduit a deux. Une comparaison a deux boutons donne une chance sur deux a un enfant qui
n'a rien lu, et l'enonce ("fait partie de") porte deja l'essentiel du raisonnement.

**La proposition de `lea-dev` est refusee** : elle ajoute une troisieme phrase ("Lyon est une ville de France.") et viole
`LON-2`. **Decision** : l'enonce est garde tel quel, et le troisieme bouton est **Paris**, que l'item 11 du meme paquet
fait connaitre comme ville de France. L'enfant doit placer une ville sous un pays, ce que l'enonce ne lui dit pas.

```yaml
  - enonce: "La France fait partie de l'Europe. Qu'est-ce qui est le plus grand ?"
    bonneReponse: "l'Europe"
    distracteurs: ["la France", "Paris"]
    indice: "Un pays est à l'intérieur d'un continent."
```

Consequence d'ecriture, dans le meme commit (avis de `theo-archi`, section 3, issue B) : le motif de
`cp-qlm-situer-la-france` dans `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` passe a "36 propositions" et perd la parenthese "(certains
items portent un seul distracteur, donc deux propositions)". Le motif de `cp-qlm-vocabulaire-de-position` ne change pas
(section 5). L'ecart de l'en-tete signale par `lea-dev` (l'item 12 classe parmi les "points cardinaux") est a corriger
dans le meme commit, sans autre effet.

## 11. P11 - espace avant ":" dans `competence` : accepter, aucun YAML change

**Decision : l'espace ordinaire est accepte dans `competence`, sur les treize fichiers. Aucun fichier ne change.** Le
chiffre que je retiens est celui que j'ai refait : `grep -l ' competence: ".* :'` rend 13 fichiers sur `d13d28f4`, les
quatre du point et neuf plus anciens. Je n'explique pas le "onze" du backlog ; il ne pese pas sur la decision.

Motifs :

1. **Aucun enfant ne lit ce champ.** Fait etabli par le relecteur et par `theo-archi` : aucun gabarit ni aucune vue ne
   l'affiche ; la seule lecture du descripteur en production est le niveau. `DEC-0002` gouverne le texte que l'enfant
   lit ; ce champ n'en est pas.
2. **Le champ est une copie conforme du referentiel.** La source, `squad/programme/enseignement-moral-et-civique/competences-cm1.md`
   ligne 43, et la matrice ecrivent l'espace ordinaire (verifie par les octets : aucun fichier de `squad/programme/` ne
   porte d'espace insecable avant ":"). Les treize fichiers sont fideles a leur source ; ce sont les quatre a l'espace
   insecable qui s'en ecartent. Je n'exige pas non plus de les changer : la forme d'un champ que personne ne lit ne vaut
   pas un diff.
3. Un champ compare a la lettre existe deja (`SousPartieDeMathematiquesCe1`) : imposer une forme a `competence` sans
   motif d'enfant, c'est ouvrir des diffs sur un champ qui sert de cle.

**Ce qui rouvre la decision** : le jour ou un ecran affiche `competence`, le champ devient du texte d'enfant et `TYP-1`
s'y applique sur les dix-sept fichiers ; c'est a `theo-archi` de faire entrer le champ dans la population du garde-fou
dans le meme geste.

**Hors de P11, route sans decision** : six fichiers portent une espace ordinaire avant ":" dans un `enonce`, ce qui est du
texte d'enfant (`cm1-anglais`, `cm1-comprehension`, `cm1-fractions`, `cm1-grands-nombres`,
`cm1-histoire-geographie-nommer-en-geographie`, `cm1-histoire-geographie-nommer-en-histoire`, releve par script sur les
lignes `enonce`). La ponctuation du texte d'enfant est au `relecteur-pedagogique` (`DEC-0002`, amendement du 2026-08-04 ;
`DEC-0056`) et au cliquet de `TYP-1` ; je ne la tranche pas.

## 12. Base rouge de s53 : ce qui doit rester vrai quand l'auteur raccourcit

Cinq echecs de `bootstrap` (lecture de `yanis-qa`, section 1 ; avis de `theo-archi`, section 1), sur des paquets qui
ne sont dans aucun des onze points sauf l'item 7 de `cp-qlm-vocabulaire-de-position` (section 5). Je n'ecris pas les
items. **Aucun garde-fou n'est a assouplir** : la reecriture doit faire passer `LON-1`, `LON-2`, `LON-CE1-2` et la clause 2
d'`ADR-0086` sans exemption. Pour chaque paquet, ce qui doit rester vrai :

### 12.1 `cp-emc-personnes-differentes`

Le paquet reste sous le refus que j'ai ecrit trois fois sur `EMC-CP-04` (arbitrage de s31, section 3.1) ; le raccourcir
ne le leve pas. Il sera juge item par item, comme je l'ai dit.

**Tache A (items 1 a 4), longueur.**

- La difference reste celle de **deux enfants nommes**, jamais celle d'une categorie (`EMC-4`). C'est la seule porte que
  j'ai laissee ouverte.
- **L'enonce garde ce que les deux enfants ont en commun.** Les distracteurs ("leur classe", "leur age") ne sont faux
  que parce que l'enonce dit qu'ils sont les memes. Un raccourci qui retire le point commun rend deux reponses
  acceptables : c'est pire qu'un enonce trop long.
- La difference reste neutre : rien qui classe un enfant au-dessus de l'autre.
- Si la tache A ne tient pas en quinze mots et deux phrases avec le point commun **et** la difference, l'auteur le dit
  et la tache sort ; il ne coupe pas le point commun pour passer.

**Tache B (items 5 a 11), amorces et longueur.** Le defaut de `FOP-R4` est un vrai defaut d'enfant, pas un artefact du
garde-fou : les bonnes reponses ouvrent sur un trait de l'enfant ("parce qu'il porte", "parce qu'elle ecrit"), les
distracteurs sur le jeu ou l'heure ("parce que c'est l'heure", "parce que le jeu"). Un enfant qui n'a rien compris
choisit le bouton qui ne commence pas par "c'est". Doit rester vrai :

- les trois propositions d'un item ont la meme forme ("Prenom, parce que ...") ;
- **le debut de la raison ne departage plus** : des raisons de jeu ouvrent sur l'enfant ("parce qu'il a deja eu son
  tour", "parce qu'elle est deja dans l'autre equipe") et des raisons tirees d'une difference ouvrent autrement ; c'est
  le sens qui departage, jamais un mot de tete. Ajouter des mots pour casser le moule sans changer cela n'est pas une
  correction ;
- les indices suivent : "la raison qui parle de l'enfant, pas du jeu" devient faux des qu'une raison de jeu parle de
  l'enfant. Le critere a nommer est "une raison tiree de ce qu'est l'enfant" contre "une raison tiree de ce qui se passe" ;
- les raisons de jeu restent de vraies raisons, sans ambiguite (`EMC-5` de l'en-tete) ;
- items 6 et 11 : seize mots ; la reecriture ne retire ni le jeu nomme ni la question.

### 12.2 `cp-qlm-modes-de-vie`, item 12

C'est le seul item qui sert le verbe "comparer" ; la ligne `DEGRADE` de l'en-tete le dit. Doit rester vrai :

- deux manieres de vivre juxtaposees, chacune rattachee a un **type de lieu** (la ville, la campagne), aucune posee en
  norme (`BIA-1`) ;
- la question demande pourquoi elles different, et la bonne reponse explique par le lieu, jamais par ceux qui y vivent ;
- deux phrases au plus, la seconde est la question : les deux manieres de vivre tiennent dans la premiere.

Observation pour l'audit, non exigee : la bonne reponse "parce que les lieux sont eloignes" ne dit pas ou ; "a la
campagne" la rendrait exacte sans ambiguite. Les deux distracteurs ("il n'y a pas de maisons en ville", "aucune route a
la campagne") sont absurdes ; je les jugerai a l'audit.

### 12.3 `cp-qlm-vocabulaire-de-position`, items 12, 13 et 14

- Trois elements, **deux relations dites avec le meme mot**, la question porte le mot contraire ("devant" puis "tout
  derriere") : l'enfant ne peut pas recopier le mot de l'enonce. A garder.
- La reponse n'est jamais dite dans l'enonce.
- Deux phrases au plus : les deux relations tiennent dans la premiere ("Léa est devant Tom et Tom est devant Sam."),
  la question est la seconde ; quinze mots au plus.
- **Defaut releve en relisant, a corriger dans la meme reecriture** : aux trois items, la bonne reponse est le dernier
  element nomme (Sam, la boite, la maison). Un enfant qui repond "le dernier nomme" gagne les trois sans lire une
  relation. Au moins un des trois items a pour reponse un element qui n'est pas le dernier nomme.

### 12.4 `ce1-mots-invariables`, item 7

- "dedans" reste employe comme adverbe, **sans complement** (la difference avec "dans" est ce que l'item montre) ;
- le contexte nomme le contenant **avant** le trou, pour que "dedans" ait un sens ;
- une seule phrase, ou la derniere est la question (`LON-CE1-2`) : par exemple deux propositions reliees par "et" dans
  une phrase ; vingt mots au plus ;
- les graphies fautives restent dans les propositions seules (`MIV-CE1-3`), et l'enonce ne s'adresse pas a l'enfant
  avec un objet a lui ("ta boite" est exclu, `TRV-CE1-6`).

## 13. Ce que je ne peux pas decider sans le jeu a l'ecran

Ces points restent ouverts jusqu'au premier passage de chaque paquet. **Je reste la seule garde de chacun** : aucun
garde-fou ne les mesure.

1. **Fuite d'un indice vers la reponse d'un autre item.** Dans `cm1-emc-principes-republique`, l'indice de l'item 2
   ("les memes droits") annonce la reponse de l'item 7, celui de l'item 3 ("se soutiennent") celle de l'item 8, celui
   de l'item 5 celle de l'item 10 presque mot pour mot ; dans `ce1-masses-contenances`, l'indice des items de choix
   d'unite donne l'egalite des items de conversion. Que ce soit une lecon reprise ou une reponse soufflee depend de
   l'ordre de tirage et de ce que l'enfant a vu avant : je le juge en jouant.
2. **L'item a deux boutons** (item 7 de `cp-qlm-vocabulaire-de-position`) : comment la mecanique traite un item dont la
   premiere erreur laisse un seul bouton, et si l'ecran de revelation dit a l'enfant pourquoi.
3. **La negation au CP** (meme item) : si "n'est pas pres" se lit, avec l'adulte, sans faire basculer l'enfant sur le
   mot "pres".
4. **Les indices reecrits du paquet de principes (dix a treize mots)** : s'ils se lisent au CM1 sur l'ecran reel, et si
   l'exemple (l'arbitre, le club de sport) est compris comme un exemple et non comme une question nouvelle.
5. **La longueur des boutons de la tache B** de `cp-emc-personnes-differentes`, une fois reecrite : une raison de dix
   mots sur un bouton de CP.
6. **Quel mecanisme servira chaque paquet** : le moment ou l'indice s'affiche est une regle du mecanisme
   (`BanditManchotSession` l'affiche apres la premiere erreur). Mes motifs des sections 4 et 6 supposent ce mecanisme ; si
   un paquet est branche sur un autre, je relis ces deux sections avant de jouer.

## 14. Q3 - prix de ce passage : 0,5 sur 5,0

**Je ne confirme pas 0,0.** Ce passage a lu douze fichiers de paquet, et il a ecrit neuf blocs de texte (indices et
items) que je rejugerai a l'audit. C'est le volume d'un rejeu (`PAR-10`), sans le jeu. Le precedent de
s52 (section 4, "sans budget") portait sur trois questions, pas sur l'ecriture de contenu. **Prix : 0,5.** Avec les 1,0
de `DEC-0112` (`CRP-CM1-R1` a `R3` et `PAR-4`), l'instance porte **1,5 sur 5,0**. Une seule instance, `PAR-9` non
declenchee.

**Ce que ce prix ne paie pas** : le premier passage de chacun de ces paquets, au tarif d'un lot le jour du branchement.
Les corrections decidees ici le rendent moins long, pas moins cher.

## 15. Routage

| Point | A qui | Effet |
|---|---|---|
| P1 | `marc-dev` | `S53-REL-1` ouverte |
| P2, reprise | `lea-dev` | indice des items 10 et 13, section 2 |
| P3, P4 | `marc-dev` | `S53-REL-3` ouverte, dans l'ordre du planning (P3, puis P4) |
| P5 | `lea-dev` | `S53-REL-4` (P5), avec l'en-tete `FER-3` |
| P6 | `marc-dev` | `S53-REL-5` ouverte |
| P8 | `lea-dev` | `S53-REL-7` ouverte, item 16 seul |
| P10 | `lea-dev` | item 12 de `cp-qlm-situer-la-france` et son motif de test |
| P11 | `sofia-sm` | `S53-REL-9` soldee sans YAML (critere C2) |
| Section 12 | `sofia-sm`, `camille-po` | perimetre et story de la base rouge ; les auteurs reecrivent sous la section 12 |
| Q3, Q7 | `sofia-sm`, `camille-po` | sections 14 et 4.4 |
