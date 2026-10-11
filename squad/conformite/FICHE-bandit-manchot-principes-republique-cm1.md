# Fiche de conformite - bandit-manchot-principes-republique-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-emc-principes-republique.yaml`
  (11 items, type `question`), ecrit par `marc-dev` a la session de contenu de s53, indices des items 7 a
  11 de mon arbitrage de s53 (P4), items 6 et 8 remplaces au branchement par `lea-dev` (commit
  `4636aa28`, texte de mon arbitrage prealable de s55, instance B, sections 3.1 et 3.2, mot pour mot).
- **Entree de catalogue** : `bandit-manchot-principes-republique-cm1`, titre servi *"Les principes de la
  République"*, consigne servie *"Lis bien, puis choisis la bonne réponse."*, `nombreDeManches: 10`.
- **Ligne declaree** : `EMC-CM1-06`. **Story** : `S55-BR-EMC-CM1-06` (`DEC-0115`).
- **Conditions opposables** : `EMC-1`, `EMC-4`, `EMC-5`, `EMC-6`, `EMC-8`, `EMC-10`, `BIA-5`, `HIS-3`,
  `FER-1` a `FER-3`, `CYC-1`, `DEC-0064`, `P3` et `P4` de s53, regle de s53 section 4.1. `EMC-9` sans objet
  sur cette ligne (arbitrage prealable de s55, instance B, section 1.2).
- **Statut global** : **VALIDE SOUS RESERVE**, prononce le 2026-10-08 par `nadia-prof` (s55, instance
  B), une reserve non bloquante (`EMC-CM1-R9`). Premier passage ; fiche ouverte par ce verdict.
- **Etat au 2026-10-11** : `EMC-CM1-R9` levee au rejeu du 2026-10-11 (s56) ; plus aucune reserve ouverte. Statut global inchange.
- **Derniere mise a jour** : 2026-10-11 ; avant : 2026-10-08.

---

## Bloc de validation pedagogique

### Verdict du 2026-10-08 (`nadia-prof`, s55, instance B) : **VALIDE SOUS RESERVE**

**Done pedagogique prononce le 2026-10-08.** `camille-po` peut passer `EMC-CM1-06` au statut d'une
ligne validee sous reserve, **avec la mention exigee d'avance pour l'EMC CM1, reproduite mot pour mot** :
*"connaissances civiques et reconnaissance de situations seules ; aucun comportement, aucun engagement et
aucune participation a une decision collective ne sont servis par ce produit."* Une reserve,
`EMC-CM1-R9` (section 5), a faire en story.

#### 1. Ce que j'ai fait

**Audit complet, pas degrade.** Meme build et meme serveur que `FICHE-bandit-manchot-respect-differences-cm1.md`
(section 1) : worktree `s55-nadia-audit-b-emc` a `14455b56`, `bash outils/mvn-jdk21.sh -B clean verify`
`BUILD SUCCESS` (job `build` seul), contenu embarque identique aux fichiers commites (`cmp`), machine
virtuelle 21.0.10, port dedie, arret par PID. Joue apres `-03`, comme prevu (arbitrage, section 3.0).

**Parties jouees** : neuf parties de dix manches (quatre tout en erreur, deux tout juste, trois
melangees). **Les onze items ont ete tires, chacun de leurs 22 distracteurs presse au moins une fois,
chaque indice lu a l'ecran, chaque item reussi du premier coup au moins une fois.** Le tirage ne se
commande pas : les items 6 et 8 ont ete lus en premier dans les transcriptions des neuf parties, puis les
trois items de laicite (4, 9, 11) lus ensemble ; aucune partie ne les a tires a la suite.

#### 2. Ancrage au programme

`EMC-CM1-06` : *"Comprendre le sens des principes de la République : liberté, égalité, fraternité,
laïcité, refus des discriminations."* Les cinq principes sont en bonne reponse ; deux taches : nommer le
principe qu'illustre une situation (items 1 a 5), dire le sens d'un principe (items 6 a 11). **C'est bien
le sens des principes qui est travaille**, pas la recitation de la devise, que le CP sert deja.

#### 3. Les deux corrections de mon arbitrage prealable, lues a l'ecran

- **Item 6** : *"jusqu'à pouvoir faire tout ce que l'on veut"* est la conception reelle, fausse pour toute
  lecture ; l'indice *"Teste chaque phrase avec un exemple pris dans la classe."* (s53, non touche) mene a
  la bonne reponse et ne pousse plus vers un distracteur vrai pour un enfant.
- **Item 8** : *"se soutenir et s'entraider les uns les autres"* ne borne la fraternite a aucun groupe.
  *"faire partie de la même famille"* est un malentendu reel (le mot *frère*), faux sans discussion.

#### 4. Les points que je ne pouvais juger qu'en jouant (arbitrage, section 3.3)

1. **`EMC-5`, items 1 a 5** : tenu. Item 1 : *"donner son avis, même quand il pense autrement"* est une
   opinion, pas une croyance ; la laicite ne s'y defend pas. Item 2 : l'acces de tous a l'ecole ne se
   defend ni comme liberte ni comme fraternite. Aucun principe voisin n'est en distracteur.
2. **Laicite, items 4, 9, 11 cote a cote** : aucun ne se lit comme un interdit. Les distracteurs decrivent
   ce que la Republique ne fait pas (imposer une religion, en faire choisir une, enseigner la meme a tous) ;
   aucun ne vise un groupe ni ne juge une conviction (`BIA-5`, `EMC-4`).
3. **Sources de l'en-tete** (`HIS-3`), verifiees contre le texte que je connais, sans source ouverte a
   cette session : 1789 article 4 et article 1er, Constitution de 1958 articles 1er et 2, loi de 1905
   article 1er, code penal articles 225-1 et 225-2 : **justes.** Decision no 2018-717/718 QPC du 6 juillet
   2018 : **juste sur le fond**, la fraternite y fonde la liberte d'aider autrui sans consideration de la
   regularite du sejour ; **la citation de l'en-tete omet "dans un but humanitaire"** et "sur le territoire
   national", que la decision porte. L'omission vient de mon propre arbitrage (section 3.2), que le
   developpeur a recopie. Corrigee dans la reserve, point (b).
4. **Vocabulaire a l'ecran** : *"employeur"* se comprend par *"refuser un emploi"*, dans la meme phrase ;
   *"discriminations"*, *"autrui"*, *"laïque"* sont les mots du programme que l'item enseigne. Les
   guillemets de l'item 11 se lisent comme une citation. Aucun mot hors de portee d'un enfant de CM1.
5. **Fuite d'un indice vers un autre item** (points 2 et 4 du message de cette session, `E4` de
   `lea-dev`) : observee a l'ecran. Dans deux parties, l'item 3 est manque avant l'item 8 : son indice
   (*"Ces élèves se soutiennent les uns les autres."*) affiche les trois derniers mots de la bonne reponse
   de l'item 8. Meme mecanisme entre l'indice de l'item 2 (*"les mêmes droits"*) et la bonne reponse de
   l'item 7. **Je ne retiens pas la fuite comme defaut** : un indice d'un item a nommer glose le sens du
   principe, et l'item de sens qui suit le redemande. C'est la lecon reprise apres une erreur, le motif
   que j'ai deja applique a `bandit-manchot-atteinte-autrui-cm1` (section 5). Elle n'existe que si
   l'enfant s'est trompe a l'item de nom. **Je retiens en revanche l'indice de l'item 3 pour un autre
   motif, l'exactitude** : section 5.

#### 5. La reserve `EMC-CM1-R9` : indice de l'item 3, et citation de 2018 dans l'en-tete

**(a) Indice de l'item 3.** L'enonce decrit des eleves qui collectent des jouets *"pour des enfants qui en
ont besoin"*. L'indice dit que *"ces élèves se soutiennent les uns les autres"* : c'est faux, ils aident
d'autres enfants, ils ne s'aident pas entre eux. Un indice qui decrit mal la situation qu'il glose est un
texte inexact lu par l'enfant. Ce n'est pas un motif de refus : la bonne reponse est juste et l'indice
mene quand meme a la fraternite.

**Correction de mon propre constat.** A s53 (P4, section 4.3), j'ai garde cet indice comme une *"glose de
la situation, le nom reste a trouver"*. Le nom reste a trouver, mais la glose est fausse : je ne l'avais
pas lue contre l'enonce. Et la fuite vers l'item 8 vient de mon texte de s55 (section 3.2), qui a mis
*"les uns les autres"* dans la nouvelle bonne reponse.

**(b) En-tete, citation de 2018** : texte libre, non lu par l'enfant, mais `HIS-3` porte sur l'exactitude
des sources ; un en-tete qui elargit une decision n'est pas une source exacte.

**Ce qui leve la reserve** : les deux textes suivants, mot pour mot, rien d'autre ne change.

Item 3 (enonce, bonne reponse et distracteurs inchanges) :

```yaml
  - enonce: "Des élèves d'une classe organisent une collecte de jouets pour des enfants qui en ont besoin. Quel principe de la République cela illustre-t-il ?"
    bonneReponse: "la fraternité"
    distracteurs: ["la laïcité", "la liberté"]
    indice: "Ces élèves donnent pour aider d'autres enfants."
```

En-tete, les quatre lignes de commentaire de l'item 8 dans la liste `HIS-3` remplacees par :

```yaml
#   - item 8 : decision du Conseil constitutionnel no 2018-717/718 QPC du 6 juillet 2018 : du principe
#     de fraternite decoule la liberte d'aider autrui, dans un but humanitaire, sans consideration de
#     la regularite de son sejour sur le territoire national. La fraternite n'est donc pas reservee aux
#     citoyens (arbitrage prealable de s55, instance B, section 3.2) ;
```

**Prix du rejeu : 0,25** (`PAR-10`, mot pour mot) ; 0,5 si un autre indice est propose, juge alors contre la
barre : un indice vrai de la situation de l'enonce, qui ne nomme pas le principe.

#### 6. Conditions, verifiees a l'ecran

| Condition | Tenue | Ce que j'ai verifie |
|---|---|---|
| `EMC-5` | **oui** | Section 4, point 1 ; item 6 : aucun distracteur vrai pour un enfant |
| `BIA-5`, `EMC-4` | **oui** | Section 4, point 2 ; item 10 : *"un club de sport qui refuse un enfant pour son origine"* decrit un acte |
| `P3` | **oui** | Item 4 : *"la démocratie"*, pas *"la liberté"* |
| `P4` | **oui** | Indices des items 6 a 11 tels que s53 et s55 les ecrivent ; aucun ne redit sa bonne reponse |
| `EMC-1`, `EMC-6`, `EMC-8`, `EMC-10` | **oui** | Troisieme personne, aucun lieu ni famille presuppose, aucun item sur ce que l'enfant ferait, aucune personne reelle |
| `HIS-3` | **oui sur les items, non sur une ligne d'en-tete** | Section 4, point 3 ; reserve, point (b) |
| `FER-1` a `FER-3`, `DEC-0064` | **oui** | Cinq principes en bonne reponse, deux taches, 11 items pour 10 manches |
| Exactitude des indices | **non, item 3** | Reserve, point (a) |

#### 7. `CYC-1`

- `cp-emc-devise` : au CP, la liberte est *"donner son avis sur un sujet de classe"*, l'egalite *"la même
  règle"*, la fraternite *"aider un camarade"*. Les items 1, 7 et 8 du CM1 les prolongent sans les
  contredire ; la fraternite n'y est plus bornee aux citoyens.
- `cm1-emc-respect-differences`, item 6 : voir la fiche de ce jeu, section 8. Coherent.
- Sur l'ecran du CM1, le titre ne se confond pas avec *"L'histoire des symboles de la République"*.

#### 8. Titre (point 4 du message de cette session)

*"Les principes de la République"* : **accepte.** Sans niveau, ne nomme ni un groupe ni une religion
(arbitrage, section 3.4), c'est le terme meme de la competence.

#### 9. Ce dont je reste la seule garde

`PaquetCm1EmcPrincipesRepubliqueTest` tient `FER-1` a `FER-3`, `EMC-1`/`EMC-8` (pronoms), `P3` et le
retour des textes refuses. **Aucun automate ne verifie** `EMC-5` (une seule reponse defendable),
`BIA-5` sur le sens d'un distracteur, `P4`, l'exactitude des sources `HIS-3`, l'exactitude d'un indice
contre son enonce, ni la fuite d'un indice d'un item vers un autre selon l'ordre de tirage : verifies ici
en jouant. **Un item de ce paquet modifie un jour est a rejouer en entier contre `EMC-5` et `BIA-5`.**

#### 10. Tarif et identifiants

Tarif reel : **1,5** (premier passage, EMC CM1 a faits institutionnels, arbitrage section 3.0). Un
identifiant neuf, `EMC-CM1-R9`, alloue dans `squad/decisions/REGISTRE-identifiants.md` dans le meme commit.

---

### Rejeu du 2026-10-11 (`nadia-prof`, s56) : `EMC-CM1-R9` **LEVEE**

**`EMC-CM1-06` reste `couvert-valide`**, avec sa mention de couverture ; aucun mouvement de matrice. 0,25 (`PAR-10`, mot
pour mot, citation comprise).

**Diff** (`02b938bd`) : l'indice de l'item 3 et les quatre lignes de l'item 8 de la liste `HIS-3` de l'en-tete, mes textes
de la section 5 a la lettre (*"dans un but humanitaire"*, *"sur le territoire national"*) ; rien d'autre. A13 (citation de
2018) est donc payee. **Joue** (meme arbre, meme jar) : par les routes servies, 3 parties, erreur sur *la laïcité* puis
sur *la liberté* a l'item 3 ; au navigateur a 744 x 551, l'item 3 joue avec deux erreurs. L'indice servi est *"Ces élèves
donnent pour aider d'autres enfants."* : vrai de la situation de l'enonce, il ne nomme pas le principe et ne reprend
plus *"les uns les autres"* de la bonne reponse de l'item 8.

**Signe** : `nadia-prof`, s56, 2026-10-11.
