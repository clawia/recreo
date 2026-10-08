# Fiche de conformite - bandit-manchot-atteinte-autrui-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-emc-atteinte-autrui.yaml`
  (11 items, type `question`), ecrit par `marc-dev` (commit `d27ba381`, session de contenu de s53), items
  2, 3, 9 et 10 remplaces au branchement par `lea-dev` (commit `ce6614db`, texte de mon arbitrage
  prealable de s54, section 5).
- **Entree de catalogue** : `bandit-manchot-atteinte-autrui-cm1`, titre servi *"Ce qui blesse les
  autres"*, consigne servie *"Lis bien, puis choisis la bonne réponse."*, `nombreDeManches: 10`.
- **Ligne declaree** : `EMC-CM1-02`. **Story** : `S54-BR-EMC-CM1-02` (`DEC-0114`).
- **Conditions opposables** : `EMC-1` a `EMC-9` (dont `EMC-3` et `EMC-9`, propres a cette ligne),
  `BIA-1`, `BIA-2`, `BIA-4`, `BIA-5`, `FER-1` a `FER-3`, `CON-1`, `CYC-1`, `DEC-0064`
  (`squad/programme/enseignement-moral-et-civique/competences-cm1.md`, jugement de faisabilite, ligne 2).
- **Statut global** : **VALIDE**, prononce le 2026-10-08 par `nadia-prof` (s54, instance B). Premier
  passage ; fiche ouverte par ce verdict.
- **Derniere mise a jour** : 2026-10-08.

---

## Bloc de validation pedagogique

### Verdict du 2026-10-08 (`nadia-prof`, s54, instance B) : **VALIDE**

**Done pedagogique prononce le 2026-10-08.** `camille-po` peut passer `EMC-CM1-02` a `couvert-valide`,
**avec la mention exigee d'avance pour l'EMC CM1, reproduite mot pour mot** : *"connaissances civiques et
reconnaissance de situations seules ; aucun comportement, aucun engagement et aucune participation a une
decision collective ne sont servis par ce produit."* Aucune reserve.

#### 1. Ce que j'ai fait

**Audit complet, pas degrade.** Worktree `s54-nadia-b`, a `5cad6650`. `bash outils/mvn-jdk21.sh -B clean
verify` : `BUILD SUCCESS` (job `build` seul ; `prefixes`, `chemins`, `outils` non lances). Jar de ce
build, paquet et catalogue embarques identiques octet pour octet aux fichiers commites (`cmp`), lance
sur un port dedie a l'instance B, arrete par son PID. Joue par les routes servies.

**Parties jouees** : onze parties de dix manches. Quatre avec les deux distracteurs presses a chaque
manche puis la revelation ; trois avec une erreur, une revelation ou une bonne reponse du premier coup
selon la manche ; quatre avec la bonne reponse du premier coup a chaque manche, pour lire le retour
d'une reussite et l'ecran de fin d'une partie sans erreur. **Les onze items ont ete tires, chacun de leurs 22
distracteurs presse au moins une fois, et chaque item a ete reussi du premier coup au moins une fois.**
Items 2, 3, 9 et 10 lus d'abord.

#### 2. Ancrage au programme

`EMC-CM1-02` : *"Reconnaître ce qui porte atteinte à la personne d'autrui (moquerie, insulte, mise à
l'écart, harcèlement) et savoir à qui s'adresser."* Les quatre termes de la parenthese sont en bonne
reponse (items 1 a 4), deux items de contraste (5, 6 : un accident, un desaccord), cinq items *"à qui
est-il important d'en parler"* (7 a 11). **C'est bien cette competence, ses deux moities.** La
distinction moquerie / harcelement porte sur la duree et la pluralite des actes (item 4 : *"depuis
plusieurs semaines"*, trois actes) : c'est le critere juste, et le distracteur *"une moquerie isolée"*
s'ecarte par l'enonce.

#### 3. Les quatre corrections de mon arbitrage prealable

Appliquees mot pour mot (verifie champ par champ) et lues a l'ecran :

- **Items 2 et 3** : *"Lina lance à Malo un mot grossier"*, *"trois élèves tournent le dos à Jade"*. Sur
  le paquet, les auteurs nommes sont Hugo, Lina et des groupes ; les cibles Aya, Malo, Jade, Tom. Plus
  aucun partage de genre, plus aucune cible visee deux fois.
- **Item 9** : *"Un adulte peut agir pour faire cesser ces messages."* Ne promet plus.
- **Item 10** : *"à personne, en attendant que cela passe"* : idee fausse reelle chez un enfant ecarte ;
  l'indice *"Une situation qui dure demande l'aide d'un adulte."* y repond.

#### 4. Conditions propres a la ligne, verifiees a l'ecran

| Condition | Tenue | Ce que j'ai verifie |
|---|---|---|
| `EMC-3` | **oui** | Bonne reponse des items 7 a 11 toujours un adulte de l'ecole ; les solutions que l'enfant appliquerait seul sont en distracteur. Aucun item ne met le joueur en victime ou en auteur |
| `EMC-9` | **oui, sur l'ecran entier** | Retour d'une reussite : *"Bravo !"* ou *"Bravo, c'est juste !"*. Revelation : *"Appuie sur la bonne réponse :"* puis la reponse. Ecran de fin : *"Tu n'as pas trouvé."* puis la question et la bonne reponse, ou *"Bravo, c'est juste !"*. **Aucun ecran ne dit ni ne laisse croire que l'enfant vient de prevenir quelqu'un.** Les indices des items 7 a 11 sont au present de verite generale |
| `EMC-8` | **oui** | Aucun item ne demande ce que l'enfant ferait ou ressent |
| `EMC-7` | **oui** | Aucun numero, aucun service d'urgence |
| `EMC-5` | **oui** | Une seule reponse defendable par item ; *"à l'élève qui se moque, pour lui demander d'arrêter"* (items 7 et 11) est un geste que des enfants envisagent sincerement, et le programme renvoie a un adulte |
| `EMC-6`, `BIA-4` | **oui** | Aucune famille presupposee ; l'adulte est un adulte de l'ecole. *"sur un téléphone"* (item 9) ne suppose pas que le joueur en a un |
| `BIA-1`, `BIA-2` | **oui** | Prenoms varies, sans repartition de genre ni d'origine entre auteurs et cibles (section 3) |
| `FER-1` a `FER-3`, `DEC-0064`, `CON-1` | **oui** | Quatre termes en bonne reponse, deux taches, 11 items pour 10 manches ; aucun item ne se repond sans l'enonce |

#### 5. Exactitude, niveau, ton

- **Exactitude** : aucun fait institutionnel n'est affirme ; les onze bonnes reponses sont celles du
  programme. Aucun distracteur n'est une bonne conduite.
- **Niveau** : enonces de une ou deux phrases. *"Aux dépens de"* (indice de l'item 1) est le mot le plus
  exigeant ; un enfant qui ne le connait pas comprend l'indice par *"cherche à faire rire"*, qui le
  precede. *"La cible"* (item 11) est lisible au CM1.
- **Fuite d'un indice vers un autre item** : les indices des items 7 a 11 disent tous qu'un adulte peut
  agir. Lu dans n'importe quel ordre de tirage, c'est la lecon reprise, pas une reponse soufflee : c'est
  ce que la ligne doit apprendre.
- **Ton** : sobre, aucune violence detaillee, aucune morale adressee a l'enfant.

#### 6. `CYC-1`

Lus a cote du paquet : `cp-emc-exclusion`, `cp-emc-permis-interdit`, `cm1-emc-institutions`.

- `cp-emc-permis-interdit` : *"Hugo rit de la voix d'Aya"* est une moquerie, comme l'item 1 ici.
  Coherent.
- `cm1-emc-institutions` : un incident entre eleves releve de l'ecole ; ici, *"un adulte de l'école"*.
  Coherent.
- `cp-emc-exclusion` : au CP, *"Malo écarte Tom du jeu"* depuis plusieurs jours se nomme *"une moquerie
  qui se répète"*, faute d'un terme propre. Au CM1, l'exclusion se nomme *"une mise à l'écart"* et
  *"une moquerie"* est distracteur de l'item 3. **Ce n'est pas une contradiction du CM1** : l'item 3
  decrit un acte unique, et le CM1 donne le terme du programme la ou le CP regroupait. Je note pour
  `camille-po` que la categorie du CP est plus large que le fait ; ce constat porte sur le paquet de CP,
  il n'ouvre ni reserve ni identifiant ici.

#### 7. Ce dont je reste la seule garde

`PaquetCm1EmcAtteinteAutruiTest` (ecrit au branchement, `ce6614db`) tient la forme de `FER-1` a `FER-3`,
`EMC-1`, `EMC-8`, `EMC-3`, `EMC-7`, et `EMC-9` sur la forme du texte du paquet. **Aucun automate ne
verifie** `EMC-9` sur les ecrans que la mecanique ecrit (retour, revelation, fin de partie), `EMC-5` (une
seule reponse defendable), `BIA-1` et `BIA-2` sur la repartition des roles entre prenoms, la promesse
qu'un indice ferait a un enfant concerne, `CYC-1`. Verifie ici en jouant. **Si la mecanique change un
jour le texte d'un retour, `EMC-9` est a rejuger sur ce jeu.**

#### 8. Tarif et identifiants

Tarif reel : **1,0** (premier passage, lot). Aucun identifiant neuf.
