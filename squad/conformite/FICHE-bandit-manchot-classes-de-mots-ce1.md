# Fiche de conformite - bandit-manchot-classes-de-mots-ce1

- **Mecanique** : bandit manchot, mot cible designe (`partDesignee`, `ADR-0062`).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-classes-de-mots.yaml`
  (24 items, type `question`), ecrit en s53, corrige et branche par `marc-dev` (`2aa1e03a`).
- **Entree de catalogue** : `bandit-manchot-classes-de-mots-ce1`, titre servi *"Quelle sorte de mot ?"*,
  consigne servie *"Lis bien, puis dis quelle sorte de mot est entre guillemets."*,
  `nombreDeManches: 10`.
- **Ligne declaree** : `FRA-CE1-12`. **Story** : `S55-BR-FRA-CE1-L2`. **Decision** : `DEC-0115`.
- **Competence programme visee** : `squad/programme/francais/competences-ce1.md`, `FRA-CE1-12`,
  *"Identifier la classe d'un mot dans une phrase : nom commun, nom propre, verbe, déterminant,
  adjectif."*
- **Conditions opposables** : `CLM-CE1-1` a `CLM-CE1-3`, `TRV-CE1-1` a `TRV-CE1-7`, `LON-CE1-1`, `-2`,
  `-5` (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`, sections 1, 2 et 5.3) ; `K4`, `K5`
  (`ARBITRAGE-PREALABLE-s55-instance-a-francais-ce1.md`, section 5).
- **Statut global** : **VALIDE SOUS RESERVE**, prononce le 2026-10-08 par `nadia-prof` (s55,
  instance A), apres avoir joue le jar servi. Fiche ouverte par ce verdict.
- **Derniere mise a jour** : 2026-10-08.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

### Verdict du 2026-10-08 (`nadia-prof`, s55, instance A) : **VALIDE SOUS RESERVE**

**Done pedagogique prononce.** `camille-po` peut passer `FRA-CE1-12` a `couvert-valide`, sans mention
de couverture partielle, et faire une story de la reserve `CLM-CE1-R1` (section 4).

#### 1. Ce que j'ai fait

**Audit joue.** Meme arbre, meme jar, meme methode que
`FICHE-bandit-manchot-accord-sujet-verbe-ce1.md`, section 1 (`fa4ddc7e`, `clean verify` vert sur Java
21.0.10, job `build` seul lance par moi, ressources du jar identiques a l'arbre). **Quarante parties de
dix manches**, memes conduites. **Les 24 items tires**, de 12 a 21 fois chacun, chacun avec au moins 12
erreurs et une revelation. Sur les 400 manches : consigne, propositions et indices servis identiques au
fichier, aucun distracteur credite juste, aucune bonne reponse refusee, aucun indice avant la premiere
erreur. **Rendu non vu au navigateur** : dans le HTML servi, le mot cible est un `span` a part
(`mot-designe`, fond colore et corps plus grand), les guillemets et leurs espaces insecables restent
hors du `span`, y compris sur *un*, *la*, *des*. Je n'ai pas vu ce rendu a l'ecran.

#### 2. Ancrage au programme

Le paquet travaille **cette** ligne : les cinq classes nommees par la ligne, et elles seules, sur un mot
designe dans une phrase. Nom commun et verbe reunis font 10 items sur 24, sous la moitie : ce n'est pas
`FRA-CP-11` refait. Ce n'est pas `FRA-CE1-13` : l'enfant nomme la classe d'un mot montre, il ne cherche
pas un mot dans la phrase.

#### 3. `K4`, `K5` et les conditions

**Mot pour mot** : les deux enonces de `K4` et la consigne de `K5` sont dans le paquet et le catalogue
exactement comme mon arbitrage les ecrit, espaces insecables comprises.

| Point | Constat |
|---|---|
| `K4` | *"Fatou « range » ses crayons."*, *"Lina aime « cette » histoire."* ; prenoms en occurrences : feminins 7 (Mia 3, Lea, Ines, Fatou, Lina), masculins 6 (Tom 2, Karim 2, Noe 2), a un pres ; origines variees. **Tenu** |
| `K5` | consigne servie identique. *Sorte* et non *classe*. **Tenu** |
| `CLM-CE1-1` | mot cible designe sur les 24 ; nom commun 5, nom propre 4, verbe 5, determinant 5, adjectif 5. **Tenue** |
| `CLM-CE1-2` | aucun mot cible en tete de phrase ; aucun pronom, adverbe, preposition ni conjonction designe. **Tenue** |
| `CLM-CE1-3` | adjectif avant le nom 2 fois sur 5 (items 3, 8), apres le nom 2 fois (13, 23), attribut 1 fois (18) ; trois noms communs sur cinq suivent un adjectif. **Tenue** |
| `TRV-CE1-1` a `-7`, `LON-CE1-1`, `-2`, `-5` | **tenues** ; bonne reponse strictement la plus longue sur 6 items |

**`W4`** non pris (*"Inès porte un joli « sac »"* reste) : je ne l'exigeais pas, aucune reserve.

**Ecart `E3` (commentaire de l'item 18) : tranche.** Ce n'est pas un texte lu par l'enfant et ce n'est
pas une reserve. Le commentaire et l'en-tete comptent *froide* comme un adjectif *apres le nom* ; c'est
un adjectif **attribut**, apres le verbe *etre*. Le compte de `CLM-CE1-3` reste tenu sans lui. A
corriger par le developpeur au prochain passage du paquet, textes exacts :

```yaml
  # CLM-CE1-1: adjectif attribut, apres le verbe etre
```

(a la place du commentaire au-dessus de l'item 18) et, pour les lignes 9 et 10 de l'en-tete :

```yaml
# CLM-CE1-3 : trois noms communs sur cinq suivent un adjectif et non un determinant ; l'adjectif est place
# avant le nom dans deux items sur cinq, apres le nom dans deux, apres le verbe etre dans un.
```

#### 4. La reserve, datee du 2026-10-08

**`CLM-CE1-R1` - les indices disent ou regarder et ne departagent rien.** C'est la reserve que mon
arbitrage annoncait (`W3`) *"si rien ne change"* ; rien n'a change. Les quatre indices (*"Cherche à quoi
sert ce mot dans la phrase."*, *"Relis la phrase en pensant au mot entre guillemets."*, *"Compare ce mot
aux autres mots de la phrase."*, *"Demande-toi ce que ce mot fait dans la phrase."*) tournent sur les 24
items. Apres une erreur, l'enfant qui hesite entre *nom commun* et *adjectif* sur *"Le ballon « rond »
roule vite"* lit une invitation a reflechir, pas un moyen de trancher. Ce n'est pas un motif de refus
(barre de s54 : l'etage insuffisant, comme `ALP-CE1-R1`). **Propriete a tenir** : un indice par item qui
donne un test de classe (*"hier"* ou *"demain"* devant la phrase pour le verbe ; la majuscule au milieu
de la phrase pour le nom propre ; *un*, *une*, *des* ou *le* place devant pour le nom commun ; *"très"*
devant pour l'adjectif), **qu'aucun distracteur de son item ne passe**, douze mots au plus, jamais la
classe nommee. Je rejuge chaque indice contre ses deux distracteurs ; les 24 textes sont au
developpeur. Tous les items changent leur indice : la reprise se paie au tarif d'un lot (`PAR-14`), 1,0.

#### 5. Exactitude, niveau, ton

- **Les 24 classes sont justes**, relues a l'ecran. *Rond* (item 13) et *souffle* (item 22) ont un
  homonyme d'une autre classe (*un rond*, *le souffle*) ; la phrase departage, l'item est juste.
- **Distracteurs** : pris parmi les quatre autres classes, aucun absurde.
- **Niveau** : phrases de trois a sept mots, vocabulaire de CE1 ; les noms des classes sont admis
  par la ligne (`TRV-CE1-4`).
- **Roles** : aucun role ni activite distribue par genre apres `K4` (*Fatou range ses crayons*, *Lina
  aime cette histoire*).

#### 6. Ce dont je reste la seule garde sur cette ligne

`PaquetCe1ClassesDeMotsTest` tient les comptes de `CLM-CE1-1`, la designation, l'absence de mot cible en
tete, la consigne, l'equilibre des prenoms, la longueur. **Il ne verifie pas** la justesse d'une classe,
la place de l'adjectif (`CLM-CE1-3`), qu'un indice departage (`CLM-CE1-R1`), ni les roles. **Je les
tiens en jouant.**

**Identifiants** : un neuf, `CLM-CE1-R1`. Forme copiee de `PCO-CE1-R1` : trois segments, niveau au
milieu, marqueur `R` dans le dernier (`ADR-0100`). Alloue dans
`squad/decisions/REGISTRE-identifiants.md`, section 4, dans le meme diff. Aucun renomme.

**Tarif consomme** : audit 1,0 (premier passage). Aucun rejeu a s55.

**Signe** : `nadia-prof`, s55, instance A, 2026-10-08.
