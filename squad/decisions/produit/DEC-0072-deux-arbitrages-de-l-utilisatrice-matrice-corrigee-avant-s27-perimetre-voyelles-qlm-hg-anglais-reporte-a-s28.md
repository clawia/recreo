# DEC-0072 - Deux arbitrages de l'utilisatrice sur les matieres absentes : la matrice est corrigee avant s27, le perimetre du sprint est voyelles simples + questionner le monde + histoire-geographie, l'anglais CM1 est reporte a s28

- **Statut** : Acceptee (les deux arbitrages sont pris et appliques par ce document et par
  `squad/programme/MATRICE-couverture.md`). Ce qui reste ouvert (nommage de colonne, sens de
  traduction de l'anglais, ecart de comptage du cycle 3) est route en section 6, non tranche ici.
- **Date** : 2026-09-02
- **Auteur** : `camille-po`
- **Sprint** : hors-sprint (branche `matieres-absentes`, avant ouverture de s27)
- **Referentiel qui fonde ce document** : `nadia-prof`,
  `squad/conformite/ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md`, branche
  `matieres-absentes`, commit `4f4e11c`, 2026-09-02.
- **Arbitrages qui fondent ce document** : l'utilisatrice, 2026-09-02, deux arbitrages, cites et
  distingues en sections 1 et 3.

---

## 0. Ce que ce document fait

1. Trace l'arbitrage 1 (integration immediate des 43 lignes dans la matrice) et son application,
   deja faite dans `squad/programme/MATRICE-couverture.md`.
2. Recompte, independamment de `nadia-prof` et de quiconque, le denominateur et le taux qui en
   resultent, et donne la methode.
3. Trace l'arbitrage 2 (perimetre de s27) avec le mot pour mot de l'utilisatrice, et le motif du
   report de l'anglais CM1 a s28.
4. Porte, sans l'adoucir, le plafond honnete de s27 tel que `nadia-prof` l'a ecrit.
5. Signale ce que j'ai trouve en verifiant le referentiel de `nadia-prof`, qui contredit une partie
   de ce qui m'a ete transmis comme deja etabli.

---

## 1. Arbitrage 1 - la matrice est corrigee avant s27

**Ce qui m'est rapporte** : l'utilisatrice a suivi la recommandation que `nadia-prof` ecrit en
section 8.4 de son arbitrage - *"je recommande que la matrice integre les lignes des matieres
neuves AVANT que s27 ne livre, pour que la baisse soit lue comme ce qu'elle est - une correction
de mesure - et non comme la consequence d'un sprint"*.

**Precision d'attribution que je dois, par la convention de `DEC-0062`** : je n'ai pas recu de mot
pour mot de l'utilisatrice pour ce point precis, seulement la confirmation qu'elle a suivi cette
recommandation. Je ne mets donc aucune parole entre guillemets a son nom sur cet arbitrage : je
dis ce qui m'a ete transmis, sans l'habiller d'une citation que je ne peux pas verifier. Ce n'est
pas la meme prudence que la forme 3 de `DEC-0062` (aucune source dans le depot) : la decision est
bien la sienne et datee, seule sa formulation exacte ne m'a pas ete donnee.

**Application, deja faite** : les 43 lignes des trois matieres (`questionner le monde` CP,
`histoire-geographie` CM1, `langues vivantes (anglais)` CP et CM1) sont entrees dans
`squad/programme/MATRICE-couverture.md` a cette date, dans quatre nouvelles sections placees a la
suite des quatre tableaux existants. Les quatre referentiels source ont ete crees a l'identique de
`nadia-prof`, un mot pres :

- `squad/programme/questionner-le-monde/competences-cp.md`
- `squad/programme/histoire-geographie/competences-cm1.md`
- `squad/programme/langues-vivantes/competences-cp.md`
- `squad/programme/langues-vivantes/competences-cm1.md`

Aucune des 43 lignes n'a de jeu : toutes demarrent a `non couvert` ou `non couvrable en l'etat`,
jamais a `en cours` ou `couvert-valide` - ces deux derniers statuts supposent un increment qui
n'existe pas (`MATRICE-couverture.md`, section "Comment la lire").

---

## 2. Le recompte, independant, et sa methode

**Je ne recopie aucun chiffre annonce ailleurs.** J'ai compte, ligne par ligne, les huit tableaux
desormais presents dans `squad/programme/MATRICE-couverture.md`, colonne `Statut`, une fois les 43
lignes ecrites avec le statut que le critere de cette matrice leur donne (feasibilite de
`nadia-prof` traduite en `non couvert` ou `non couvrable en l'etat`, jamais en un statut qui
supposerait un jeu existant). Detail complet, table matiere par matiere et methode, dans
`squad/programme/MATRICE-couverture.md`, section "Integration des matieres absentes du
2026-09-02".

**Resultat** :

| | Avant (4 tableaux) | Apres (8 tableaux) |
|---|---|---|
| Total referentiel | 47 | 90 |
| `non couvrable en l'etat` | 6 | 15 |
| Denominateur atteignable | 41 | **75** |
| `couvert-valide` | 34 | **34, inchange** |
| Taux | 82,9 % | **45,3 %** |

**Neuf des 43 lignes neuves sont `non couvrable en l'etat`** : une en questionner le monde
(demarche d'investigation, jugement humain sur une production libre), zero en histoire-geographie,
cinq en anglais CP (le son sur trois lignes, le refus specifique de la ligne 4, la copie au titre
de `DEC-0066`), trois en anglais CM1 (le son sur trois lignes). **34 lignes des 43 entrent au
denominateur atteignable.**

**Ce recompte rejoint celui de `nadia-prof`** (denominateur 75, taux 45,3 %, section 3 de son
arbitrage), par une methode differente et independante - la sienne comptait ses propres lignes
avant qu'elles soient ecrites dans ce fichier, celle-ci compte les lignes telles qu'elles y sont
effectivement. **Les deux methodes convergent au chiffre pres. Rien n'est du a une copie de son
compte : c'est le mien, verifie separement.**

**Aucune regression du produit.** Les 34 lignes `couvert-valide` d'hier le restent toutes ; aucun
jeu n'a ete touche. Le taux baisse parce que le denominateur devient honnete, exactement comme au
sprint 19 de ce depot (`squad/programme/MATRICE-couverture.md`, section "Synthese a l'ouverture de
s19"), a une echelle vingt fois plus grande.

---

## 3. Arbitrage 2 - le perimetre de s27

**L'utilisatrice, 2026-09-02, verbatim** :

> Voyelles simples + Questionner le monde + Histoire-geographie. L'anglais CM1 est reporte a s28.

**Verification de l'arithmetique qui sous-tend ce choix, contre le tarif ecrit d'avance par
`nadia-prof`** (section 8.2 de son arbitrage) : matiere connue, mecanique existante = 1 ; matiere
neuve textuelle (choix ou saisie) = 1,5 ; matiere neuve illustree = 2. Borne inchangee a 5, meme
sur trois matieres d'un coup.

| Composition | Compte |
|---|---|
| voyelles simples (matiere connue, 1) + questionner le monde (matiere neuve textuelle, 1,5) + histoire-geographie (matiere neuve textuelle, 1,5) | **4,0** |
| meme composition + anglais CM1 (matiere neuve textuelle, 1,5) | **5,5** |

**Verifie : 4,0 tient dans la borne de 5, avec une marge de 1. 5,5 la depasse.** Le perimetre
retenu par l'utilisatrice est exactement celui que l'arithmetique de `nadia-prof` designe comme
tenable ; le troisieme paquet de matiere neuve ne tient pas dans le meme sprint, quelle que soit
la matiere qu'on choisirait de sacrifier a sa place. **Ce n'est pas un choix arbitraire d'exclure
l'anglais : c'est la seule composition a trois qui depasse la borne, et l'utilisatrice a garde les
deux qui restent dans la marge.**

**Motif du report, dans les mots de `nadia-prof`, porte ici parce que le sprint 27 en depend** :
son cout de verification factuelle est le plus faible sur questionner le monde (elle est la
source, comme en francais) et le plus eleve sur l'anglais illustre (a lui seul, l'equivalent de
deux paquets textuels si le sens retenu est image -> anglais). Reporter l'anglais laisse aussi le
temps de trancher ses deux points non arbitres (section 5) avant d'en ecrire le contenu, plutot que
de le faire dans l'urgence d'un sprint qui le porterait.

---

## 4. Le plafond honnete de s27, porte sans l'adoucir

**Fait que `nadia-prof` pose et que je ne reformule pas** : *"un premier paquet de matiere neuve
ouvre une ligne, il ne la ferme pas - saisie, illustre et voix ont tous demande un second
passage."* Les paquets de questionner le monde et d'histoire-geographie, s'ils sont livres et
obtiennent un Done pedagogique, feront passer les lignes qu'ils touchent a `en cours` au mieux -
pas a `couvert-valide` du premier coup, sur la seule foi de l'historique de ce depot que
`nadia-prof` cite (saisie, illustre, a voix : trois premiers paquets, trois seconds passages).

**Le plafond honnete de s27, tel qu'ecrit par `nadia-prof`, section 8.4** : **une seule fermeture
de ligne**, celle des graphemes par le paquet de voyelles simples - ses clauses 2, 3 et 4 sont
deja remplies, seule la clause 1 (voyelles simples en bonne reponse) manque. **Aucun gain de
couverture chiffre n'est annonce au-dela de cette ligne.** C'est la lecon explicitement tiree de
la cloture de s26 (`DEC-0069`), ou un plafond de 35/41 annonce a l'ouverture n'a pas ete tenu pour
un motif similaire - une barre pedagogique non integree au compte.

**Ce que cela signifie pour la matrice a la cloture de s27, dans le meilleur des cas** : la ligne
des graphemes passe a `couvert-valide` (35 lignes vertes sur 75, 46,7 %) ; les lignes de
questionner le monde et d'histoire-geographie touchees par les deux premiers paquets passent au
mieux a `en cours`, jamais a `couvert-valide` par ce seul geste.

---

## 5. Ce que j'ai trouve, et qui contredit une partie de ce qui m'a ete transmis comme etabli

**Le referentiel de `nadia-prof` est lui-meme incoherent sur le compte des enseignements de cycle
3, et je le signale plutot que de le reproduire.**

Sa section 2.2 titre *"Cycle 3 - neuf enseignements"*, puis conclut, sous un tableau : *"Neuf
enseignements annonces, dix lignes de tableau : c'est que l'histoire et la geographie comptent
pour un seul enseignement, meme si l'usage les nomme separement."*

**Cette reconciliation ne fonctionne pas, et je l'ai verifiee ligne par ligne.** Le tableau qui
precede cette phrase liste deja *"Histoire et geographie"* comme **une seule ligne** (la ligne 4,
explicitement annotee *"un seul enseignement, horaire et programme communs"*). Il n'y a, dans ce
tableau, aucune paire de lignes distinctes "Histoire" et "Geographie" qui attendrait d'etre
fusionnee : la fusion est deja faite avant que le tableau ne soit ecrit. Le tableau contient dix
lignes numerotees (Francais, Mathematiques, Langues vivantes, Histoire et geographie, Sciences et
technologie, Arts plastiques, Education musicale, Histoire des arts, Education physique et
sportive, Enseignement moral et civique), soit **dix enseignements distincts nommes**, pas neuf.

**L'explication la plus probable, que je pose comme hypothese et non comme un fait** : au cycle 2,
`nadia-prof` fusionne arts plastiques et education musicale en une seule ligne *"Enseignements
artistiques"* (section 2.1, ligne 5). Au cycle 3, elle les liste separement (lignes 6 et 7) tout en
ajoutant *histoire des arts* comme onzieme... dixieme entree distincte (ligne 8). Si le meme
principe de fusion des deux arts s'appliquait au cycle 3, le compte tomberait a neuf sans toucher
a l'histoire-geographie, deja comptee une fois. **Le tableau tel qu'il est ecrit dit dix ; le
titre de section dit neuf ; la phrase de reconciliation nomme le mauvais coupable.**

**Consequence pour cette matrice, aujourd'hui : aucune.** Aucune ligne d'arts, d'EPS, d'EMC, de
sciences et technologie ou d'histoire des arts n'entre dans la matrice a cette date - seules
questionner le monde, histoire-geographie et langues vivantes y entrent, et leur decompte n'est
pas affecte par ce point. **Mais le jour ou ces matieres entreront, le nombre exact
d'enseignements de cycle 3 (neuf ou dix) devra etre tranche par `nadia-prof`, pas suppose depuis
ce document.** Je le route en section 6.

**Un second point, plus mineur, de la meme famille de vigilance** : l'arbitrage de `nadia-prof`
annonce en section 8.3 un plafond de "35/75 = 46,7 %" pour la cloture de s27. Verifie : 35/75 =
0,4666... = 46,7 % arrondi. **Exact.**

---

## 6. Ce que ce document route, et a qui

| Fait ou question | A qui | Etat |
|---|---|---|
| Nombre exact d'enseignements de cycle 3 (neuf annonces, dix nommes dans le tableau source) | `nadia-prof` | **Route, section 5. Sans consequence sur la matrice tant qu'aucune ligne d'arts, d'EPS, d'EMC, de sciences et technologie ou d'histoire des arts n'y entre** |
| Nommage de la colonne `Matiere` pour l'anglais (`anglais` ou `langues vivantes (anglais)`) | l'utilisatrice | **Route par `nadia-prof`, section 6 de son arbitrage ; retenu a titre provisoire dans la matrice** |
| Sens de traduction exact de l'exercice d'anglais (image -> anglais recommande en priorite, mot francais -> anglais en second, anglais -> francais jamais seul) | l'utilisatrice, apres l'avis de `nadia-prof` | **Route, non tranche - concerne le paquet de s28, pas s27** |
| Verification que la forme d'anglais choisie ne demande reellement ni mecanique ni forme d'item neuves | `nadia-prof`, au moment ou le paquet d'anglais CM1 sera compose | **Route, section 7.3 bis de son arbitrage** |
| Cout de developpement d'un paquet illustre de vingt items (double le stock d'illustrations du produit) | `theo-archi`, `camille-po` | **Route, sans consequence sur s27 (aucun paquet illustre n'y est prevu)** |
| `HIS-1` a `HIS-3`, `BIA-1` a `BIA-5`, conditions opposables aux paquets de questionner le monde et d'histoire-geographie | `camille-po` (stories), developpeurs | **Portees dans les referentiels crees et dans les stories de s27** |
| Stories de developpement pour les trois paquets de s27 | `camille-po` | **A ecrire, voir `docs/backlog/backlog.md`** |

---

## 7. Ce que ce document ne fait pas

- Il ne prononce aucun Done pedagogique.
- Il ne tranche ni le nombre exact d'enseignements de cycle 3, ni le nommage de colonne pour
  l'anglais, ni le sens de traduction : ce sont des points routes, pas des choix faits ici.
- Il n'ecrit aucune competence de sa propre main : les 43 lignes sont recopiees telles quelles
  depuis le referentiel de `nadia-prof`.
- Il ne fixe le perimetre d'aucun sprint au-dela de ce que l'utilisatrice a arbitre en section 3.
