# s57 - palier 5 : questionner le monde CE1, mon constat sur A.229 et ma reponse sur le nombre d'items et les mots d'`AIM-CE1-2`

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-25. Heure non lue : le shell etait indisponible a l'ecriture.
- **Arbre** : branche `s57-p5-nadia-items`, base `cb5ea29f` annoncee par l'agent lanceur, **non verifiee par `git`** (shell
  indisponible).
- **Type** : deux reponses dues avant les neuf stories (`arbitrage-nadia-palier-5-qlm-9-lots.md` 5.4, points 2 et 4 ; `DEC-0120`
  A.246, A.249 points 5 et 6). **Aucun Done**, aucune condition de Done neuve, **aucun identifiant neuf** ; ni code, ni paquet, ni
  catalogue, ni `DEC`, ni matrice, ni registre.
- **Je n'ai rien joue** : aucun paquet `ce1-qlm-*` n'existe (Glob sur le repertoire de contenu : 0 fichier).
- **Lu** : `DEC-0120` A.220 a A.249 ; `arbitrage-nadia-palier-5-qlm-v3.md` (0, 2) ; `arbitrage-nadia-palier-5-qlm-9-lots.md` ;
  `ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md` (2, 3) ;
  `ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md` (2, 4.1, 5.5) ;
  `arbitrage-nadia-preparation-palier-3-ce1.md` (4.4, 4.5) ; paquets `cp-qlm-cycle-de-vie` et `ce1-emc-interet-commun`, lus en
  entier.

## Resume

| # | Question | Reponse |
|---|---|---|
| N1 | Condition d'A.229 | **Remplie sous reserve** (section 1) |
| N2 | Nombre d'items par paquet | **Au moins 10, par `FER-2`**, deja ecrite et applicable. Pas de plancher de 20. Au-dela de 20, le prix du lot depasse 1,0 (section 2) |
| N3 | Mots de quantite d'`AIM-CE1-2` | **Je n'adopte pas la liste proposee** : la phrase interdite n'en contient aucun, et deux de ces mots sont justes et deja servis. La sonde porte sur la phrase, pas sur des mots de quantite (section 3) |

---

## 1. N1 : la condition d'A.229

**Ce que j'avais ecrit** (`arbitrage-nadia-palier-5-qlm-v3.md` 2) : consigner *"le texte de V3 tel qu'il lui a ete presente"*,
parce que *"une reference qui ne pointe vers rien ne fonde pas une entree"* et que ce texte dit quelles lignes et quelle regle elle
depasse ; et, dans le meme paragraphe, *"j'admets le depassement des aujourd'hui sur le texte que l'agent lanceur m'a transmis"*.

**Constat : remplie sous reserve.**

1. **A la lettre, elle n'est pas remplie, et je ne le declare pas autrement.** A.229 ecrit que le libelle affiche n'a pas ete
   conserve ; ce qui est consigne est une reconstitution depuis le message de l'agent lanceur. Dire le contraire serait deplacer mon
   poteau.
2. **Son motif est rempli.** (a) `V3` pointe desormais vers un texte du depot, avec sa provenance et ce qu'on ignore. (b) Ce texte
   est celui sur lequel j'ai admis le depassement (`arbitrage-nadia-palier-5-qlm-v3.md` 0) ; A.229 en porte une forme plus longue
   qui ne le contredit pas. (c) **Ce que je voulais apprendre de ce texte (quelles lignes, quelles regles) ne depend plus de lui** :
   ses mots consignes ensuite le disent eux-memes, les neuf lignes et les trois refus (A.237), les regles levees en les nommant
   (A.242, A.247).
3. **Reserve** : si l'utilisatrice corrige la reconstitution (A.229 le lui ouvre), je relis mon constat contre sa correction, par
   annotation datee. La portee du depassement est celle de ses mots d'A.237 a A.247, jamais une lecture plus large de la
   reconstitution.

**Effet** : ce constat ne bloque ni les stories ni l'ouverture. **`PO2` (A.222)** : ma reponse est ecrite depuis le matin, section 2
de `arbitrage-nadia-palier-5-qlm-v3.md` (lecture (b), depassement de ma regle pedagogique, admis). A.249 point 5 la dit *"non
ecrite"* : c'est a corriger par `camille-po`.

---

## 2. N2 : le nombre d'items par paquet

**Correction de mon propre constat, avant de repondre.** J'ai ecrit (`arbitrage-nadia-palier-5-qlm-9-lots.md` 2.2) que le nombre
d'items *"reste ouvert"*, et A.227 point 4 ecrit qu'*"aucune condition de questionner le monde CE1 ne le donne"*. **C'est inexact
pour le plancher** : l'instruction (section 2) reprend `FER-1` a `FER-3` sur les dix-sept lignes, et `FER-2` (s28, 2) dit *"Au moins
dix items servent la ligne"*. Une ligne par lot (`M1`), donc un paquet par ligne : **au moins 10 items par paquet.**

**Ce que le depot donne, et rien de plus.**

| Borne | Valeur | Source |
|---|---|---|
| Plancher | **10 items** | `FER-2`, condition existante, necessaire et non suffisante |
| Prix | **1,0 par lot jusqu'a 20 items**, prorata au-dela | s28 4.1 (unite du lot) ; les 10,00 au bareme des neuf lots supposent donc au plus 20 items par paquet |
| Parties | 10 manches par defaut (`DEC-0064`) | a 10 items, une partie epuise le paquet sans derogation (s28 5.4) |

**Les conditions de ligne tiennent dans 10 items** [calcul sur l'instruction, section 3] : le cas le plus serre est `-04`, six
familles en bonne reponse (`FAL-CE1-1`, `FER-1`) et au moins trois items de menus (`FAL-CE1-2`, seconde tache de `FER-3`), soit 9 ;
`-10` demande 4 + 3 = 7 ; `-05` au moins 4 items a la vapeur d'eau sur 10 (`ETE-CE1-1`, un tiers) ; `-06` au moins 3 sur 10
(`AIM-CE1-2`, un quart).

**Pourquoi pas 20, par analogie avec l'EMC CE1.** Les *"au moins 20 items"* d'EMC CE1 sont ma condition de
`arbitrage-nadia-preparation-palier-3-ce1.md` 4.4, ecrite pour l'EMC ; la section 4.5 du meme document dit, pour questionner le
monde CE1 : *"Je n'en ajoute aucune."* L'etendre aujourd'hui serait une condition neuve ecrite apres coup, ce que je ne fais pas ici.

**Ce que je ne fixe pas** : un chiffre entre 10 et 20. Aucune condition ne le donne ; la story peut en demander plus que 10, c'est a
`camille-po`. **Ce qu'il change pour moi** : `M2` se paie a l'item, contre sa source. Neuf paquets font **90 items a lire au
plancher, 180 a 20 items** [calcul], dans un seul palier.

---

## 3. N3 : les mots de quantite d'`AIM-CE1-2`

**Ma condition, inchangee** : *"Aucun enonce, aucun indice, aucune explication ne dit que l'aimant attire les metaux."*

**Je n'adopte pas la liste proposee en A.227 point 2** (`tous`, `toutes`, `toujours`, `jamais`, `seulement`) :

1. **La phrase que j'interdis n'en contient aucun.** *"L'aimant attire les metaux."* passerait une garde sur ces cinq mots : elle
   serait verte sur l'exemple meme qu'elle doit refuser.
2. **Deux de ces mots sont justes et deja servis** sur la matiere : `cp-qlm-cycle-de-vie` porte les indices *"Elle garde toujours la
   meme taille."* et *"Elle n'a jamais ete vivante."* Les interdire refuserait des items justes.

**Ce que la sonde doit porter** : une phrase qui attribue l'attraction **au metal en general** (le metal, les metaux, ce qui est en
metal, les objets metalliques), quel que soit le mot de quantite.

| Doit etre refuse | Doit passer |
|---|---|
| *"L'aimant attire les metaux."* | *"L'aimant attire-t-il le papier d'aluminium ?"* |
| *"Un aimant attire tout ce qui est en metal."* | *"Le clou est en fer. L'aimant l'attire-t-il ?"* |
| *"Les objets en metal sont attires par l'aimant."* | *"Quel objet l'aimant attire-t-il ?"* |

Les exemples sont ecrits ici sans accents ; dans le test, la sonde lit les formes accentuees (*metal*, *metaux*, *metallique* et
leurs formes avec accent). **Aucun identifiant neuf, aucune barre neuve** : c'est la sonde d'`AIM-CE1-2` telle qu'ecrite, que A.227
demande pour chaque critere negatif. **La forme de la garde est a `theo-archi`.** Une detection de mots qui apparaissent ensemble
dans une phrase rougirait aussi sur une question juste (*"Ce trombone est en metal : l'aimant l'attire-t-il ?"*) : c'est a lui de
regler la forme. **Je reste la seule garde des paraphrases** qu'aucune liste ne voit.

---

## 4. Ce qui reste avant l'ouverture (5.4 de mon arbitrage des neuf lots)

Points 2 et 4 : ecrits ici. Restent : les neuf stories, chacune avec mes conditions mot pour mot et, pour `-06`, la sonde de la
section 3 ; un developpeur distinct de `lea-dev` ; le premier jet de `-01` commite seul ; mon arbitrage prealable de l'instance. `K1`
a `K5` et `M1` a `M6` sont inchanges.

| A qui | Quoi |
|---|---|
| `camille-po` | les neuf stories, avec `FER-2` (au moins 10 items) cite comme condition existante ; corriger A.227 point 4 et A.249 point 5 par annotation datee |
| `theo-archi` | la forme de la garde d'`AIM-CE1-2` sur les sondes de la section 3 |
| l'utilisatrice, par `sofia-sm` | aucune question ; elle peut corriger la reconstitution d'A.229 |

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-25.
