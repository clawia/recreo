# DEC-0069 - Etat du produit a la cloture de s26 : trois lignes se ferment, pas quatre, et rang propose pour s27

- **Statut** : Proposee
- **Date** : 2026-09-01
- **Auteur** : `camille-po`
- **Sprint** : s26 (cloture)
- **Arbre de reference** : `squad/conformite/ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md`,
  section 18 (`nadia-prof`, verdict sur `round-two` a `a5147d8` augmente du delta)
- **Ce que ce document fait** : il constate ce que le Done pedagogique de s26 laisse, corrige
  publiquement le plafond que j'avais annonce a l'ouverture du sprint, et propose un rang pour s27.
  **Il ne fixe aucun perimetre** : le perimetre de s27 s'ecrira a son ouverture.

---

## 1. Le verdict, et ce qu'il ferme reellement

`nadia-prof` a rejoue les cinq paquets et prononce le Done pedagogique : **VALIDE sur les cinq**
(`bandit-manchot-position-syllabe-cp`, `bandit-manchot-son-dans-mot-cp`,
`bandit-manchot-lettre-du-son-cp`, `bandit-manchot-digraphe-du-son-cp`, `saisie-mot-dicte-cp`).

**Trois lignes passent `couvert-valide`**, pas quatre :

| Ligne | Etat |
|---|---|
| Localiser une syllabe dans un mot | **`couvert-valide`** |
| Identifier un son (phoneme) dans un mot | **`couvert-valide`** |
| Ecrire de maniere autonome un mot simple | **`couvert-valide`** |
| Associer les graphemes aux phonemes | **reste `en cours`** |

La quatrieme reste ouverte malgre deux paquets VALIDE, sur un motif qui n'est ni technique ni un
refus : sa barre du 2026-08-10, clause 1, exige les voyelles simples **et** les consonnes simples ;
`bandit-manchot-lettre-du-son-cp` sert six consonnes (`p, m, t, r, l, s`) et aucun paquet servi
n'a de voyelle simple en bonne reponse. Ses mots : *"Les deux moities sont VALIDE ; la ligne n'est
pas couverte. Le Done porte sur l'increment, la matrice sur la couverture d'une competence. Me
taire ferait passer la ligne a `couvert-valide` contre une barre non atteinte."*

**Ratio, methode nommee** : comptage automatise des quatre tableaux de matiere de
`squad/programme/MATRICE-couverture.md`, colonne `Statut`, apres report des trois verdicts qui
bougent un statut. Aucun module compile, aucune question de `-am`. **34 `couvert-valide`, 6
`non couvert`, 1 `en cours`, 6 `non couvrable en l'etat`, total 47, denominateur 41 : 34/41 =
82,9 %** (contre 75,6 % a la cloture de s25, +7,3 points, integralement de trois lignes livrees et
validees).

**Le domaine "Langage oral et conscience phonologique" (francais CP) passe de 1/3 a 3/3 et se
ferme entierement** - premier domaine ferme du produit.

**Le francais CP passe de 5/10 (50,0 %) a 8/10 (80,0 %)**, et cesse d'etre le domaine le plus
faible du produit (mathematiques CP 81,8 %, mathematiques CM1 80,0 %, francais CM1 90,0 %).

Detail complet, table de composition et compte matiere par matiere :
`squad/programme/MATRICE-couverture.md`, section "Synthese apres les verdicts du sprint 26".

## 2. Mon ecart : le plafond que j'avais annonce n'est pas atteint

**A l'ouverture de s26, apres la reponse de `theo-archi` sur `ADR-0075` (champ d'item, pas
mecanique), j'avais ecrit : "les quatre lignes ferment entierement, plafond 35/41 = 85,4 %."**
Ce n'est pas ce qui s'est produit : **34/41 = 82,9 %.**

Le motif n'est ni un echec technique (1 090 tests verts, non-regression verifiee sur 188 ecrans
jouables) ni un refus pedagogique (les cinq paquets sont VALIDE) : c'est une **barre pedagogique
que mon compte n'integrait pas**. J'avais compte des paquets livres contre des lignes de
referentiel, en traitant la reponse de `theo-archi` sur la question technique comme si elle
epuisait aussi la question pedagogique - qui restait a rendre et qui portait sur un point que je
n'avais pas nomme comme une condition ouverte (le contenu reellement servi par
`bandit-manchot-lettre-du-son-cp`, evalue contre la barre du 2026-08-10, pas seulement contre le
Done de l'increment). C'est le meme defaut que j'ai verse contre moi a la cloture de s25 :
**une enumeration qui ne se declare pas partielle se lit comme complete.** Mon "35/41" ne se
declarait pas conditionnel a un jugement de contenu qui restait a rendre.

**Precision de `nadia-prof` a porter explicitement, pour qu'aucune lecture future ne les
confonde** : ce 82,9 % n'a **aucun rapport** avec le 82,9 % qu'elle avait price pour la branche
"mecanique" de `theo-archi` (4 paquets, 3 lignes) avant sa reponse "champ d'item, pas mecanique"
(5 paquets, 4 lignes, 85,4 %). Cette branche-la decrivait un choix d'architecture qui n'a pas ete
pris. Celle-ci decrit une ligne de matrice qui reste `en cours` faute de voyelles simples servies
dans le contenu livre. **Meme nombre, autre cause.**

## 3. Ce que le vert ne couvre pas

**La dette du JavaScript est fermee ce sprint** (`ADR-0074`, trois porteurs, plus l'amendement
apres que `yanis-qa` a trouve le troisieme mal reconnu) - c'est le lot que l'utilisatrice a fait
entrer en disant *"demarre s26 avec en plus la dette"*. Elle ne touche aucune ligne de cette
matrice : c'est un chantier de qualite technique, pas de couverture pedagogique, et je ne la compte
dans aucun chiffre de couverture ci-dessus.

**La liste des conditions pedagogiques sans garde executable ne diminue pas d'une unite** :
`VOI-3`, `VOI-5`, `VOI-8`, et la moitie non calculable de `VOI-13`. `nadia-prof` : *"la cinquieme
phrase ne fait pas respecter `VOI-13` : elle la dit. Un adulte qui laisse l'enfant lire la ligne
fait echouer les cinq jeux, et rien ne le saura."* Aucun des cinq jeux VALIDE de ce sprint n'en est
dispense, et rien dans cette decision ne doit se lire comme une garantie sur ce point.

## 4. Le rang que je propose pour s27

### Rang 1 - un paquet de voyelles simples ferme la ligne des graphemes. Fait arithmetique, pas priorisation.

`nadia-prof` l'ecrit d'avance : ses clauses 2, 3 et 4 de la barre du 2026-08-10 (digraphes `ou`,
`oi`, `ch` ; nasales inchangees ; increment joue) sont **desormais toutes remplies**. Il manque un
seul paquet, servant au moins une voyelle simple en bonne reponse, sur le meme champ d'item que
les quatre paquets deja livres (`ADR-0075`), sans toucher `cp-le-bon-son.yaml` (cout deja chiffre
par `theo-archi` a 15 items pour rien, motif acquis ce sprint). C'est le cout de composition le
plus bas et le motif de fermeture le plus certain du backlog : je recommande de l'ouvrir en tete
de s27. Le fermer clot un second domaine consecutif.

### Ce que je ne tranche pas, et qui reste a l'utilisatrice

- **Combien de sprints les deux lignes `non couvert` restantes du domaine phonologique et de
  lecture** (decoder un mot par assemblage syllabique, refusee ce sprint pour ce moyen ; les
  autres lignes non couvert de francais CP) doivent attendre derriere ce sixieme paquet : c'est une
  priorisation, pas un calcul de couverture.
- **Si la dette residuelle - `VOI-3`, `VOI-5`, `VOI-8`, la moitie non calculable de `VOI-13`,
  aucune garde executable - appelle un chantier propre plutot que de rester une reserve
  permanente.** C'est un arbitrage d'acceptation de risque, pas une question de contenu ou de
  couverture.
- **Le rang relatif de ce paquet face au conflit couverture contre dette technique** que `DEC-0067`
  et `DEC-0068` gardent lisible depuis deux sprints (trou d'`ADR-0008`, garde-fou "un rang suffit",
  score parfait par elimination) : ces chantiers restent a `sofia-sm` et `theo-archi`, je ne les
  reordonne pas ici.

## 5. Ce que je route

| Suite | Destinataire | Ce que je demande |
|---|---|---|
| Le rang 1 (paquet de voyelles simples) | `sofia-sm` | L'inscrire au planning de s27, sur le meme champ d'item, sans toucher `cp-le-bon-son.yaml` |
| L'arbitrage de priorisation (section 4, "ce que je ne tranche pas") | l'utilisatrice, par `sofia-sm` | Trancher le rang relatif face au conflit couverture / dette |
| La matrice a jour | - | Deja faite dans le meme geste (`squad/programme/MATRICE-couverture.md`) |

## 6. Ce que cette decision ne fait pas

- Elle **ne prononce aucun Done** : les cinq verdicts sont de `nadia-prof`.
- Elle **ne fixe aucun perimetre de s27** : seul un rang est propose.
- Elle **n'efface pas** le "35/41" que j'avais ecrit a l'ouverture de s26 : il reste lisible a sa
  place, avec sa date, et il est corrige ici plutot que reecrit sur place.
