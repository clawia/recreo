# s57 - relecture des corrections de `ce1-qlm-ce-que-mangent-les-animaux` (`S57-BR-QLM-CE1-02`, `P5`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : debut 2026-10-26 16:18:38 (UTC+01:00), lu par `date` ; fin en section 7.
- **Arbre** : branche `s57-p5-nadia-corr02`, base `round-two` a `1706a40b`, lue par `git`.
- **Lu** : `git diff ebc274b1 HEAD` sur `adapters/adapter-content/src/main/resources/contenu/ce1-qlm-ce-que-mangent-les-animaux.yaml`
  et `git show f7c6bc7d` sur `PaquetCe1QlmCeQueMangentLesAnimauxTest.java` et `animaux-fautifs.yaml` (correction de `marc-dev`),
  compares a la section 5 de `lecture-nadia-premier-jet-ce1-qlm-ce-que-mangent-les-animaux.md`. Pour l'unicite, les douze lignes
  `indice:` du paquet ; pour les distracteurs, les douze lignes `distracteurs:` ; pour la source du sanglier, les enonces du lapin et
  du sanglier de `cm1-sciences-regimes-alimentaires.yaml`. Pour la morsure des sondes, `verifierLesPropositionsDeChaqueTache`.
  **Je n'ai pas relu le paquet.**
- **Type** : relecture des lignes corrigees, avant `verify`. **Aucun Done, aucun verdict de lot**, aucun identifiant neuf, aucune
  condition neuve. Je n'ai modifie ni le paquet, ni les tests.
- **Audit degrade, dit** : rien n'est joue a l'ecran (paquet hors catalogue). Chaque indice et chaque distracteur neuf est joue sur le
  papier.
- **Mesures lancees** :
  - comparaison caractere par caractere par script (Python, hors du depot, non commite) : chaque *texte a ecrire* de la section 5
    cherche tel quel, les deux blocs de commentaire et la `Map` `CATEGORIES` cherches a l'identique, chaque *texte actuel* cherche
    absent ; compte de mots par decoupe sur les blancs ; unicite des douze indices ;
  - `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test -Dtest=PaquetCe1QlmCeQueMangentLesAnimauxTest -Dsurefire.failIfNoSpecifiedTests=false` :
    *"Tests run: 24, Failures: 0, Errors: 0, Skipped: 0"*, `BUILD SUCCESS`. Perimetre : ce test, module `adapter-content` et ses
    dependances. Ce n'est ni un `verify`, ni la porte technique.

## Resume

| Point | Reponse |
|---|---|
| Conformite a la section 5 | **les dix points tiennent, a l'octet** (section 1) |
| Perimetre du diff | **seules les lignes prescrites changent** : 2 distracteurs, 4 indices, 1 ligne de regle, le bloc des sources, 12 lignes d'animal (section 2) |
| `TRV-CE1-5`, unicite, distracteurs neufs, ligne `cm1-regimes` | **tiennent** (section 3) |
| Sonde 14 (la chouette, *des mulots*) | **je la garde** (section 4) |
| `I1`, *"meme type d'article"* | **je demande qu'elle soit encodee** ; la forme est a `theo-archi` (section 5) |
| Ouverture | **aucune** : pas d'item ni de formulation rouverts, pas de deuxieme tour a classer (section 6) |
| Duree | section 7 |

## 1. Comparaison a la section 5, element par element

| Point | Element | Verdict | Motif |
|---|---|---|---|
| 1 | item 2, escargot : `["des mouches", "des vers de terre"]` | **tient** | Texte present a l'identique, ancien absent |
| 2 | item 10, lapin : `["des vers de terre", "des souris"]` | **tient** | Idem. *Des poissons* a disparu |
| 3 | indice chouette : *"Pense aux griffes et au bec de la chouette."* | **tient** | Idem. 9 mots |
| 4 | indice araignee : *"Imagine l'araignée qui attend, immobile, au milieu de sa toile."* | **tient** | Idem, virgules comprises. 10 mots |
| 5 | indice grenouille : *"Pense à la longue langue collante de la grenouille."* | **tient** | Idem. 9 mots |
| 6 | indice hirondelle : *"Imagine l'hirondelle qui vole le bec grand ouvert."* | **tient** | Idem. 8 mots |
| 7 | commentaire `:29`, *"au meme type d'article (du, de la, de l' ou des)"* | **tient** | Ligne a l'identique ; *"meme article (des)"* absent |
| 8 | bloc des sources `:38-43` et douze lignes d'animal `:47-58` | **tient** | Bloc a l'identique ; les douze lignes portent `source : lecture-nadia`, le reste de chaque ligne inchange. La seule occurrence restante d'`avis-nadia` est `:17`, le nom du fichier de mon avis dans l'en-tete, hors de ma prescription et juste |
| 9 | `CATEGORIES`, neuf cles | **tient** | Texte a l'identique. Une sonde par famille de paraphrase est ecrite (section 4) |
| 10 | rang et comptes | **tient** | Aucune ligne de compte ni d'item hors des points 1 a 6 ne change ; 12 items |

Aucun texte neuf ne porte d'espace avant `?` ou `:`.

## 2. Perimetre du diff

`git diff --stat ebc274b1 HEAD` sur le YAML : *"1 file changed, 25 insertions(+), 23 deletions(-)"*. Les 23 lignes retirees sont :
1 ligne de regle, 4 lignes de sources, 12 lignes d'animal, 2 lignes de distracteurs, 4 lignes d'indice. Les 25 lignes ecrites sont les
memes plus les 2 lignes que mon bloc de sources ajoute (6 lignes pour 4). **Rien d'autre ne change** : ni enonce, ni bonne reponse, ni
declaration d'item, ni ligne de compte.

Le test change sur deux points, tous deux prescrits ou laisses a `marc-dev` par mon point 9 : `CATEGORIES` et une methode de sonde
`s6UnItemAlimentQuiProposeUneParaphraseDUneCategorieEstRefuse`. Le jeu fautif gagne une ligne de commentaire et deux items (sondes 13
et 14).

## 3. Ce que les corrections doivent tenir

**`TRV-CE1-5`, aucun indice ne donne la reponse.** Les quatre indices neufs nomment une partie du corps ou une attitude : griffes et
bec, l'araignee immobile au milieu de sa toile, la langue collante, le vol bec ouvert. Aucun ne nomme une categorie ni un aliment, et
aucun verbe n'a une proie pour objet. Joues sur le papier : l'enfant doit savoir a quoi servent des griffes, une langue collante, un
bec ouvert en vol, et en tirer ce que l'animal mange ; c'est la competence. L'hirondelle est le plus direct des quatre : l'enfant
doit encore savoir que ce qui vole dans le ciel, ce sont des insectes, et ni *des graines* ni *des feuilles* ne s'ecartent par
l'indice seul sans ce savoir. **Tient.** C'est le critere de ma section 2.1, que je ne durcis pas.

**Les douze indices sont distincts** : 12 lignes, 12 textes differents (script). **Tient.**

**Les distracteurs neufs.** Mon defaut (2.2) : un distracteur ne doit ni etre mange par l'animal, ni s'ecarter sans la competence.

| Item | Distracteur | Mange par l'animal ? | Ecartable sans la competence ? | Verdict |
|---|---|---|---|---|
| 2, escargot, jardin | *des mouches* | non : l'escargot du jardin mange des vegetaux | non : la mouche se pose sur la salade, elle est petite | **tient** |
| 2, escargot, jardin | *des vers de terre* | non | non : le ver vit dans la meme terre, il est mou et lent ; ni la taille ni le lieu ne l'ecartent | **tient** |
| 10, lapin, potager | *des vers de terre* | non : le lapin est herbivore (`cm1-regimes`) | non : le ver est dans la terre du potager | **tient** |
| 10, lapin, potager | *des souris* | non | non : la souris court dans le potager, elle est plus petite que le lapin | **tient** |

Je suis toujours la seule garde de la colonne *"mange par l'animal"* (`QC9`). Le constat de ma section 4 (la bonne reponse de B est
l'intrus) demeure tel quel : les deux distracteurs de chaque item restent de la meme categorie, c'est ma regle, je le regarderai a
l'audit.

**La ligne `cm1-regimes`.** Mon premier jet de ce bloc portait la faute que le point 8 corrige : la parenthese du sanglier melait le
regime du lapin (*herbes, feuilles et racines*) au sien. Confrontee a `cm1-sciences-regimes-alimentaires.yaml` : le lapin *"se
nourrit d'herbe, de feuilles et de racines"*, le sanglier *"mange aussi bien des racines et des glands que des vers de terre et de
petits animaux"*. La ligne ecrite dit *lapin herbivore (herbe, feuilles et racines ; la carotte est une racine)* et *sanglier omnivore
(racines, glands, vers de terre et petits animaux)*. **Tient**, au mot pres de la source.

**Morsure des sondes 13 et 14, par lecture.** `verifierLesPropositionsDeChaqueTache` ne leve son message *FER-3* en tache aliment que
si `categorie(proposition)` est non vide, et le message porte la proposition. Chaque sonde est un item seul (`hasSize(1)`) ; sans les
cles *plantes* et *viande*, aucune assertion ne leverait et le test echouerait. Je n'ai pas lance de mutation : c'est une lecture,
pas une mesure.

## 4. Sonde 14 : la chouette avec *des mulots*

**Je la garde.** Trois motifs :

1. **Le fait est exact et source** : *"la chouette mange le mulot"* est la ligne `cm1-chaine` du paquet. Une sonde fautive ne doit
   etre fautive que par ce qu'elle met a l'epreuve ; sa bonne reponse et son autre distracteur (*des graines*) sont justes.
2. **Elle montre pourquoi la paraphrase est une faute** : *de la viande* est vrai de la chouette, donc une seconde reponse defendable
   a cote de *des mulots*. C'est exactement le motif de ma lecture de `-03`, section 4.
3. **Elle eprouve l'article `de la `**, que la sonde 13 (*des plantes*) n'eprouve pas : les deux sondes couvrent deux articles de
   tete differents.

Un autre animal ne gagnerait rien. La chouette est en tache A dans le paquet ; dans le jeu fautif, elle est en B, ce qui est permis
par ma regle (une seule categorie, *autres animaux*).

## 5. `I1` : *"meme type d'article"*, encodee ou lue

**Je demande qu'elle soit encodee.** La regle est entierement ecrite et fermee, je l'ai posee en `I1` de ma lecture : chaque
proposition d'un item de B commence par *du*, *de la*, *de l'* ou *des* ; elles peuvent les meler ; aucune ne commence par *le*,
*la*, *les*, *un*, *une*, un nombre, ni n'est sans article. Elle ne demande aucun jugement : c'est un debut de chaine sur une liste
fermee. Ce que je lis moi-meme, ce sont les proprietes qu'aucune liste ne ferme (ce qu'un indice designe, ce qu'un animal mange). Une
regle fermee laissee a ma seule lecture est une regle que je peux manquer en relisant douze items au palier suivant.

Ce que la garde doit tenir, pour ne pas refuser a tort : *de l'* avec l'apostrophe droite et l'apostrophe typographique ; le melange
des quatre articles sur un meme item ; une garde qui ne porte que sur la tache B (les categories de A, *les deux* compris, n'y
entrent pas). La forme, le lieu dans le test et le nombre de sondes sont a `theo-archi`. Le classement (ce lot avant `verify`, ou un
lot suivant) est a `camille-po`.

**Ce n'est pas une condition neuve ni un durcissement** : les douze items la tiennent aujourd'hui (les six propositions de B
commencent toutes par *des*), et ce paquet n'attend pas cette garde pour ma porte. Tant qu'elle n'est pas ecrite, `I1` reste a ma
seule garde, comme le dit ma section 5.

## 6. Ouvertures

**Aucune.** Aucun item, aucune formulation n'est rouvert : les dix points tiennent tels que prescrits, et rien de ce que j'ai lu ne
demande un deuxieme tour d'arbitrage. La demande de la section 5 porte sur le test, pas sur le contenu ; elle se classe comme une
story ou une tache de `theo-archi`, pas comme un deuxieme tour.

**Ce dont je reste la seule garde sur ce paquet, apres cette relecture** : l'exactitude de chaque categorie et de chaque aliment
contre sa source ; qu'aucun distracteur de B ne soit mange par l'animal ; qu'un indice soit une methode et pas une periphrase ;
*"meme type d'article"* tant que la garde n'est pas ecrite ; *"un animal classe les deux n'entre pas en B"* sauf si `theo-archi`
l'encode ; les paraphrases hors des six ; le lieu de l'enonce ; le constat de ma section 4. **Aucune n'est couverte par un test.**

## 7. Duree et faits pour le classement

Mesuree par `date` : debut **2026-10-26 16:18:38**, ecriture de ce fichier 16:24:34, fin **16:28:46**, avant le commit.
**Duree : 10 min 08 s**, dont la mesure du test du paquet, celle du garde-fou des identifiants
(`bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest -Dsurefire.failIfNoSpecifiedTests=false` :
*"Tests run: 10, Failures: 0, Errors: 0, Skipped: 0"*, `BUILD SUCCESS`, ce fichier present dans l'arbre ; il n'ecrit aucun
identifiant neuf) et `.github/scripts/verifier-chemins.sh` : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers
verifies."* Au bareme, **0,5 hors marge** (`DEC-0120` A.272, seconde lecture, A.270 point 4) : je n'en convertis aucune duree.
