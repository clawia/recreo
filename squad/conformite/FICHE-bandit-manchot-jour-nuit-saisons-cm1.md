# Fiche de conformite - bandit-manchot-jour-nuit-saisons-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-terre-jour-nuit-saisons.yaml`
  (12 items, type `question`), ecrit par `lea-dev` (commit `7854d8d3`, session de contenu de s53), item 4
  remplace au branchement (commit `269ce5ce`, texte de mon arbitrage prealable de s54, section 3.2).
- **Entree de catalogue** : `bandit-manchot-jour-nuit-saisons-cm1`, titre servi *"Le jour, la nuit et
  les saisons"*, consigne servie *"Lis bien, puis dis pourquoi il y a le jour et la nuit, ou les
  saisons."*, `nombreDeManches: 10`.
- **Ligne declaree** : `SCI-CM1-15`. **Story** : `S54-BR-SCI-CM1-15` (`DEC-0114`).
- **Reprise de** : `cm1-sciences-terre`, jeu `bandit-manchot-terre-cm1`, **REFUSE le 2026-10-06**
  (`squad/conformite/FICHE-bandit-manchot-terre-cm1.md`, motifs 1 et 2, quatre points non bloquants).
  Cette fiche juge le paquet neuf ; le refus reste ecrit dans l'ancienne.
- **Conditions opposables** : `FER-1` a `FER-3`, `SCI-1`, `SCI-3`, `SCI-4`, `HIS-3`, `CON-1`, `CYC-1`
  (`squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md`,
  sections 2.2 ligne 15, 2.3, 2.5 et 2.6), `DEC-0064`, et la barre des deux motifs de l'ancienne fiche.
- **Statut global** : **VALIDE**, prononce le 2026-10-08 par `nadia-prof` (s54, instance B).
- **Derniere mise a jour** : 2026-10-08.

---

## Bloc de validation pedagogique

### Verdict du 2026-10-08 (`nadia-prof`, s54, instance B) : **VALIDE**

**Done pedagogique prononce le 2026-10-08.** `camille-po` peut passer `SCI-CM1-15` a `couvert-valide`,
**sans mention de couverture** (aucune n'est due pour cette ligne). Les deux motifs du refus de s51 sont
leves a la barre ecrite le 2026-10-06, et les quatre points non bloquants sont pris. Aucune reserve.

#### 1. Ce que j'ai fait

**Audit complet, pas degrade.** Worktree `s54-nadia-b`, a `95214218`. `bash outils/mvn-jdk21.sh -B clean
verify` : `BUILD SUCCESS` (job `build` seul ; `prefixes`, `chemins`, `outils` non lances). Jar de ce
build, paquet et catalogue embarques identiques octet pour octet aux fichiers commites (`cmp`), lance
sur un port dedie a l'instance B, arrete par son PID. Joue par les routes servies.

**Parties jouees** : quatre parties de dix manches, chaque manche jouee avec les deux distracteurs
presses puis la revelation, puis la bonne reponse. **Les douze items ont ete tires, et chacun de leurs
24 distracteurs presse au moins une fois** ; les indices cites sont ceux lus a l'ecran. Items 4 et 11
lus d'abord, comme annonce.

#### 2. Ancrage au programme

`SCI-CM1-15` : *"Situer la Terre dans le système solaire et relier les mouvements de la Terre au jour,
à la nuit et aux saisons."* Tache C (items 1, 2), tache A (items 3 a 6 et 12), tache B (items 7 a 11) :
inchange par rapport au paquet audite a s51. **C'est bien cette competence.** Le titre neuf dit le
contenu a un enfant de 9 ans et ne reprend pas celui du jeu retire.

#### 3. Les deux motifs du refus de s51, a leur barre

| Motif | Barre (fiche de s51, section 7) | Constate a l'ecran |
|---|---|---|
| 1, item 4 | un second distracteur faux, qui porte la conception de la distance sans etre vrai a la lettre ; un indice qui dit pourquoi elle est fausse sans decrire la position attendue | Distracteur *"le Soleil s'est beaucoup rapproché de cette région"* : faux dans tous les reperes. Indice *"Entre la nuit et le jour, la distance au Soleil ne change presque pas."*, lu apres chacun des deux distracteurs : vrai, il ne decrit pas la position. **Leve** |
| 2, item 11 | une seule saison a les jours les plus longs parmi les trois proposees | Distracteurs *"l'hiver"*, *"l'automne"* ; enonce en *"France métropolitaine"*. En ete, les jours y durent douze heures ou plus, en automne et en hiver douze heures ou moins. **Leve** |

#### 4. Les quatre points non bloquants de s51

| Point | Constate |
|---|---|
| 1, item 10 | Indice *"Quand une moitié de la Terre penche vers le Soleil, l'autre penche à l'opposé."* : vrai, et il parle d'inclinaison, plus de jour et de nuit. *"France métropolitaine"* dans les items 9, 10 et 11. **Pris** |
| 2, item 7 | *"la Lune cache le Soleil en hiver"* : faux dans toutes les lectures. **Pris** |
| 3, items 3 et 9 | Item 9 : *"En juillet, la Terre est même un peu plus loin du Soleil qu'en janvier."*, vrai (aphelie debut juillet), repond a la conception de la distance. Item 3 : *"Dans le système solaire, ce sont les planètes qui tournent autour du Soleil."* repond a *"le Soleil tourne autour de la Terre"*. **Pris** |
| 4, item 2 | *"La Lune tourne autour de la Terre, pas l'inverse."* : ne s'appuie plus sur la taille. **Pris** |

#### 5. Exactitude, niveau, `CYC-1`, ton

- **Exactitude** : les douze bonnes reponses et les douze indices sont exacts ; aucun distracteur n'est
  vrai dans une lecture qu'un enfant de 9 ans peut faire. Items 4, 8 et 9 : *"beaucoup"*, *"un peu plus
  loin"* et *"change très peu"* disent la meme chose sur la distance, sans se contredire.
- **Niveau** : questions d'une phrase, vocabulaire du CM1 (*astre*, *axe*, *inclinaison*, *hémisphère
  sud*, *horizon*), inchange depuis s51.
- **`CYC-1`** : `cp-qlm-saisons` relu (hiver le plus froid, ete le plus chaud) ; aucune contradiction.
- **Ton** : neutre ; le presuppose geographique releve a s51 est leve.

#### 6. Ce que j'ai regarde sans en faire une condition (arbitrage de s54, section 3.3)

- **Item 3, rotation et revolution** : l'indice parle du trajet des planetes autour du Soleil dans un item
  sur la rotation. A l'ecran, apres l'indice, la proposition qui reste juste est *"la Terre tourne sur
  elle-même"*, et la revelation la nomme. Le risque de confusion existe ; il est tenu par les items 2 et
  6 du meme paquet, qui posent les deux mouvements et leurs durees (*"en une année"*, *"environ
  vingt-quatre heures"*). Je n'en fais pas une reserve.
- **Item 1** : l'indice decrit la bonne reponse, comme a s51 ; je ne releve pas ma barre.
- **Item 7** : *"la Lune cache le Soleil en hiver"* est moins plausible que *"la Terre est plus près du
  Soleil en été"*, mais l'item garde une conception fausse courante en distracteur (`SCI-1`).

#### 7. Ce dont je reste la seule garde

`PaquetCm1SciencesTerreTest` (recible sur ce paquet par `269ce5ce`) tient les marqueurs `FER-1` et de
tache, le nombre d'items, la forme de `SCI-1`, `SCI-3`, `SCI-4` et le marqueur `HIS-3`. **Aucun automate
ne verifie** qu'un distracteur n'est pas vrai (les deux motifs de s51 avaient traverse ce test vert),
qu'un indice ne decrit pas la bonne reponse, l'exactitude d'un fait d'astronomie, la portee
geographique d'un enonce, `CYC-1`. Verifie ici en jouant.

#### 8. Tarif et identifiants

Tarif reel : **1,0**, tarif de lot (`PAR-14`, 9 items sur 12 changes). Aucun identifiant neuf.
