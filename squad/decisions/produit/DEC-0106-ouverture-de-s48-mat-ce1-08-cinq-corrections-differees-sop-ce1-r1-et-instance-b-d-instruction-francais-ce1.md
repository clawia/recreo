# DEC-0106 - Ouverture de s48 : `MAT-CE1-08`, les cinq corrections differees de s47, `SOP-CE1-R1`,
et l'instance B d'instruction du referentiel francais CE1

- **Type** : decision d'ouverture de sprint (perimetre, chiffrage, routage).
- **Date** : 2026-10-03
- **Auteur** : `camille-po`
- **Sprint** : s48
- **Competence programme visee** : transverse - ce document gele un perimetre de sprint, il ne
  tranche aucune question pedagogique de contenu.
- **Statut** : Acceptee
- **Lien** : `squad/sprints/s48-problemes-ce1-et-corrections-differees-de-s47/planning.md` (section 2,
  section 4, section 5) ; `DEC-0074`/`DEC-0077` (tarif 1,0/1,5 selon la source du referentiel) ;
  `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` (conditions `PRO-CE1-1` a
  `PRO-CE1-7` sur `MAT-CE1-08`) ; `DEC-0085`, section 3 et 8 (clause `PAR-10`, precedent de tarif
  d'une reserve) ; `DEC-0105` (aucune candidate CM1 pour l'instance B) ; `docs/backlog/backlog.md`,
  section "Backlog - sprints suivants" (les cinq stories de correction et `SOP-CE1-R1`).

---

## 1. Perimetre gele

Instance A de `nadia-prof` (Done pedagogique) :

| Story | Tarif | Motif |
|---|---|---|
| `MAT-CE1-08` (resoudre des problemes a une ou deux etapes) | 1,0 | tarif deja fixe (`DEC-0074`/`DEC-0077`), conditions `PRO-CE1-1` a `PRO-CE1-7` deja ecrites, seule ligne neuve atteignable `couvert-valide` |
| `TAB-CE1-R1` (indice du facteur 1, `bandit-manchot-tables-multiplication-ce1`) | 0,5 | correction d'un seul paquet, section 2 |
| `AUT-CP-R1` (deux indices, `bandit-manchot-autrefois-aujourdhui-cp`) | 0,5 | idem |
| `ANG-CP-R1` + `ANG-CP-R2`, groupees (`bandit-manchot-culture-anglophone-cp`) | 0,5 | un seul paquet touche, deux reserves |
| Deux reserves groupees de `bandit-manchot-alerte-cp` (`EMC-CP-14`) | 0,5 | idem |
| Deux reserves groupees de `bandit-manchot-vote-election-cm1` (`EMC-CM1-10`) | 0,5 | idem |
| `SOP-CE1-R1` (reprise de `bandit-manchot-soustractions-posees-ce1`, arbitrage deja rendu) | 0,5 | section 3 |
| **Total** | **4,0 / 5,0** | marge 1,0 |

Instance B de `nadia-prof` (instruction, aucun Done pedagogique) : instruit le referentiel francais
CE1 seul (26 lignes), hors chiffrage de la borne ci-dessus (section 4).

## 2. Tarif des cinq corrections differees : 0,5 par story, sur le precedent `PAR-10`

Aucun tarif publie n'existe pour une story de correction en tant que telle. Le planning le signale
comme chiffrage manquant (section 2) et je ne l'invente pas sans verifier d'abord s'il existe deja
un precedent.

**Precedent trouve** : `DEC-0085`, section 8, clause `PAR-10` - "une reserve se paie 0,5 par paquet
touche, et non 0,5 par code". Cette clause a deja servi a chiffrer des groupes de plusieurs reserves
sur un meme paquet (treize reserves de s32 payees en six paquets, section 8 de `DEC-0085`) et une
reprise isolee sur un seul paquet (`EPS-CP-04`, 0,5, section 3, meme decision). Le meme document
distingue `PAR-14` : une reprise qui touche **plus de la moitie des items** d'un paquet se paie au
tarif d'un lot entier (1,0 ou 1,5), parce que l'audit precedent ne couvre plus rien une fois la
majorite du paquet changee.

**Verification que `PAR-14` ne s'applique a aucune des cinq corrections differees de s47**, ligne
par ligne, d'apres le volume que chaque story ecrit elle-meme dans ses criteres d'acceptation :

- `TAB-CE1-R1` : cinq items touches sur l'indice seul, ni produit ni distracteurs.
- `AUT-CP-R1` : deux indices sur un paquet de quatorze items.
- `ANG-CP-R1`/`ANG-CP-R2` : "quatre items sur douze" (la story le chiffre elle-meme).
- `EMC-CP-14`, deux reserves groupees : "deux items sur onze" (idem).
- `EMC-CM1-10`, deux reserves groupees : deux distracteurs/une question sur un paquet de douze
  items, aucune des deux ne touchant la majorite.

Aucune de ces cinq corrections ne touche la majorite des items de son paquet : `PAR-14` ne
s'applique pas, `PAR-10` s'applique. **Chaque story vaut 0,5, qu'elle groupe une ou deux reserves du
meme paquet** - une story qui groupe deux reserves sur un paquet deja groupe par `camille-po`
(`ANG-CP-R1`/`R2`, les deux reserves d'`EMC-CP-14`, les deux reserves d'`EMC-CM1-10`) n'est pas
payee deux fois sous pretexte qu'elle porte deux identifiants, exactement comme `PAR-10` l'ecrit.

**Risque assume, nomme plutot que cache** : si l'une de ces corrections s'avere a l'audit toucher en
realite plus de la moitie de son paquet une fois le diff vu (par exemple si `EMC-CM1-10` oblige a
retoucher plus d'items que ses deux reserves le laissent supposer), `PAR-14` reprend la main et la
story vaut le tarif d'un lot, pas 0,5 - ce cas n'est pas chiffre d'avance par cette decision, il
sera signale par `nadia-prof` au moment de l'audit s'il se presente.

Total des cinq corrections : **2,5 / 5,0**.

## 3. `SOP-CE1-R1` entre au perimetre de s48

Le planning (section 4) la propose "si `camille-po` juge que le volume du sprint le permet une fois
les quatre points chiffres". Une fois `MAT-CE1-08` (1,0) et les cinq corrections (2,5) comptees, le
total s'etablit a 3,5/5,0 : une marge de 1,5 reste disponible, plus que la marge de 1,0 que les
sprints a borne comparable ont retenue depuis s42 (`DEC-0098`, `DEC-0099`, `DEC-0101`, `DEC-0102`,
`DEC-0104`).

**Correction du 2026-10-03, avant cloture de cette section : l'arbitrage est deja rendu.**
`theo-archi` le signale (avis de faisabilite de s48, Q3, section 9) : `nadia-prof` a tranche
`SOP-CE1-R1` le 2026-10-02 (s47), dans la meme fiche
(`squad/conformite/FICHE-bandit-manchot-soustractions-posees-ce1.md`, section "Arbitrage de
`SOP-CE1-R1`") - cinquieme forme admise, le decalage des colonnes, obligatoire sur tout item sans
emprunt. Cette section decrivait encore l'arbitrage comme a venir et un travail "selon son issue" :
l'issue est connue, et il n'y a qu'une seule sortie a chiffrer, pas deux. Le travail de s48 est **la
reprise seule**, bornee aux deux items sans emprunt du paquet (`47 - 20`, `65 - 15`), ecrite en story
dans `docs/backlog/backlog.md`, section "Backlog - sprints suivants" (story `SOP-CE1-R1`, qui suit
immediatement la story d'arbitrage close). C'est le meme format qu'une correction differee au sens
de la section 2 : un seul paquet, une minorite d'items (deux sur dix-huit). Je lui applique le meme
tarif, 0,5, sous `PAR-10` - confirme malgre les pieges de fabrication que `theo-archi` releve pour
cette reprise (marqueur neuf, regle d'admission, jeu fautif commite, test de paquet a adapter,
section 9 de son avis) : ce volume est celui qu'une correction differee porte deja ailleurs dans ce
sprint (`TAB-CE1-R1` demande aussi une assertion de test neuve, section 4 du meme avis), et le
compte d'items touches (deux sur dix-huit, 11 %) reste tres en-dessous de la majorite qui ferait
basculer `PAR-14`.

Total gele pour l'instance A : **4,0 / 5,0**, marge 1,0 - meme discipline de marge que les sprints
precedents, sans la reduire a zero sur un sprint qui porte deja six stories distinctes.

La reprise peut etre affectee directement : elle n'attend plus aucun arbitrage.

## 4. L'instance B d'instruction entre au perimetre de s48, hors chiffrage de la borne de l'instance A

L'instance B instruit le referentiel francais CE1 seul (26 lignes) - `DEC-0105` ferme deja la
question d'une candidate CM1 symetrique, aucune n'est ouverte. Son produit est un Jugement de
faisabilite / arbitrage prealable, pas un paquet joue et pas un Done pedagogique : elle ne consomme
donc pas la borne d'audit de 5,0 qui gouverne l'instance A, exactement comme le planning le propose
(section 4) et sur le meme modele que le traitement deja reserve a `SOP-CE1-R1` avant ce document
(un arbitrage de `nadia-prof` route "hors chiffrage" plutot que compte comme un sixieme lot,
`DEC-0104` section 2.1 avant cette decision).

Je ne lui fixe aucun chiffre en lots ou en points, parce qu'aucune unite de ce type n'a jamais
mesure un travail d'instruction dans ce depot (la seule mesure ecrite pour une instruction
comparable, celle de mathematiques CE1 entre s36 et s37, n'est pas chiffree en points non plus). Sa
seule borne est de forme, posee ici explicitement pour qu'elle ne devienne pas un chantier sans fin
parallele au sprint : elle rend un Jugement de faisabilite ligne par ligne sur les 26 lignes avant la
cloture de s48, en reprenant les bornes de lisibilite deja posees pour le CE1
(`ARBITRAGE-s36-longueur-d-enonce-au-ce1.md`, a reverifier pour ce niveau) et en statuant sur la
borne de longueur du texte support que `DEC-0104` section 1.1 signale non posee.

## 5. Ce que je decide

1. Le perimetre de s48 est gele : `MAT-CE1-08`, `TAB-CE1-R1`, `AUT-CP-R1`,
   `ANG-CP-R1`/`ANG-CP-R2`, les deux reserves groupees d'`EMC-CP-14`, les deux reserves groupees
   d'`EMC-CM1-10`, `SOP-CE1-R1` - total 4,0/5,0 pour l'instance A de `nadia-prof` (Done pedagogique).
2. Le tarif d'une story de correction differee est fixe a 0,5, sur le precedent `PAR-10`
   (`DEC-0085`), tant qu'elle ne touche pas la majorite des items de son paquet (section 2) ; au-dela
   de cette majorite, `PAR-14` reprend la main et la story vaut le tarif d'un lot entier.
3. L'instance B de `nadia-prof` entre au perimetre de s48 pour instruire le referentiel francais CE1
   seul, hors chiffrage de la borne de l'instance A, sans unite de mesure chiffree, avec pour seule
   borne de rendre son Jugement de faisabilite complet avant la cloture de s48.
4. Les developpeurs restent a affecter nominativement par `sofia-sm` au planning, une fois cette
   decision lue.

## 6. Ce que cette decision ne fait pas

- Elle ne deplace aucun statut de `squad/programme/MATRICE-couverture.md` et ne ferme aucune ligne :
  les statuts ne bougent qu'au verdict de `nadia-prof`.
- Elle ne prononce aucun Done, technique ou pedagogique.
- Elle ne rend pas l'avis de faisabilite technique dedie a s48 que `theo-archi` doit encore ecrire
  sur `MAT-CE1-08` et sur les cinq corrections (planning, section 6, point 3) : cette decision gele
  le perimetre produit, elle ne confirme pas la faisabilite technique.
- Elle n'engage pas le chantier de sourcing du corpus d'oeuvres du domaine public (`DEC-0077`,
  `DEC-0105`), sans rapport avec le perimetre de s48.
- Elle ne fixe l'affectation nominative d'aucun developpeur ni d'aucune instance : travail du
  planning de s48, par `sofia-sm`.
