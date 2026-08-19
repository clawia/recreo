# Avis de mecanisme - le style du texte-support de `S17-2` (`COMP-5`)

- **Auteur** : theo-archi. **Date** : 2026-08-19.
- **Branche** : `s17-theo-comp5`, worktree dedie, forkee de `round-two` a `26c7d8b` ; lignee verifiee
  par `outils/verifier-lignee.sh`.
- **Repond a** : `DEC-0039`, amendement du 2026-08-19, "Ce que je route" - *"theo-archi : mecanisme de
  ce style neuf (une regle CSS pour un champ, pas une mecanique de jeu neuve)"* ; et au point que
  `ines-design` me route nommement (`proposition-ines-texte-support-comp5.md`, section 6, branche
  `s17-ines-comp5`, lue sans quitter ce worktree).
- **Ce que je ne fais pas** : je ne juge pas la lisibilite (`nadia-prof`, en parallele), je ne choisis
  pas la forme (`ines-design`), je n'ecris pas le contenu. Aucun chiffre de rendu n'est de moi : je
  n'ai pas de navigateur, aucune instance ne tourne.
- **Porte technique** : `bash outils/mvn-jdk21.sh -B clean verify` - **BUILD SUCCESS**, reacteur
  complet (6 modules), sur le JDK de la toolchain declaree, **Java 21.0.10**. Detail en section 5.

## Verdict en une page

| Point route | Verdict |
|---|---|
| 1. Champ de contenu ou mecanique neuve ? | **Champ de contenu.** `S17-2` reste une story de contenu. Mais *"aucun code de jeu ne change"* est faux a la lettre : cinq fichiers, quatre modules, dont une methode de la mecanique |
| 2. Ce que le chargeur exige | **Aucune evolution de schema, aucun `schema: 2`, aucun type de contenu neuf.** Une cle d'item lue par la fabrique `question`. Le schema "l'accepte tel quel" dans le sens dangereux du terme : une cle inconnue est **ignoree sans un mot** |
| 3. Le budget vertical d'`ines-design` | **Le chiffre de 240 px ne se transporte pas** sur une disposition qui n'est pas celle qui l'a produit, ni comme feu vert ni comme feu rouge. Gardable : le plancher de 1,25rem, desormais garde ; le bloc vide sur les onze jeux existants, deja garde. Non gardable : la hauteur, le pli, "est-ce que ca tient" |
| 4. `ADR` du ? | **Oui.** `ADR-0046` et `ADR-0049` ne couvrent rien de ce cas. `ADR-0050`, ecrit dans ce lot avec ses deux regles |

## 1. Le mecanisme : un champ de contenu, et le critere n'est pas une appreciation

**La question se lit, elle ne se tranche pas.** `ADR-0004` en a fait un critere executable des le
sprint 0, et il vit dans `DecouplageMecaniqueContenuTest` : une mecanique est une classe qui
implemente `GameMechanic` et qui vit dans son propre paquet sous
`com.recreo.domain.mechanics.<mecanique>` (clause 6), et deux mecaniques ne dependent pas l'une de
l'autre (clause 7). Applique au texte-support : aucune classe n'implemente `GameMechanic`, aucun
paquet n'apparait sous `domain/mechanics`, aucune permission ne s'ajoute au `sealed interface Item`
(`ADR-0004` : une deuxieme forme d'item n'est ajoutee que par la mecanique qui en a besoin).
**Ce n'est pas une mecanique neuve.** La premiere lecture de `camille-po` est confirmee, et
`S17-2` reste dans le perimetre de contenu.

**Ce que je corrige dans sa formulation, parce qu'un chiffre de cout sert au planning.** *"Aucun code
de jeu ne change, une seule regle de style s'ajoute pour un champ"* est faux a la lettre. Mesure sur
l'arbre `26c7d8b`, par lecture de la chaine complete :

| Fichier | Module | Ce qui change |
|---|---|---|
| `QuestionContentSourceFactory` | adapter-content | lit la cle de l'item |
| `Question` | domain/content | porte la composante, optionnelle |
| `BanditManchotSession` | domain/mechanics | l'expose, comme elle expose deja `enonceManche()` |
| `PartieController` | adapter-web | la met au modele |
| `fragments/manche.html` + `recreo.css` | adapter-web | la rendent |

**Cinq fichiers, quatre modules, dont la mecanique.** Ce n'est pas une contradiction avec le verdict :
c'est exactement la forme qu'ont deja prise `indice` (`ADR-0016`) et `explication` (`ADR-0024`) - un
texte que le contenu ecrit, que la mecanique **transporte sans jamais le lire**, et que le gabarit
rend. Le texte-support est le troisieme de cette famille. La difference avec les deux autres, et elle
compte pour la suite : les deux premiers s'affichent **apres** un geste de l'enfant, celui-ci
s'affiche **avec la question**, au moment ou l'ecran se presente. C'est de la que vient le seul effet
de bord reel de ce champ - section 3 et `ADR-0050`, clause 2.

**Le detour qui aurait fait basculer la story, et qu'il faut ecarter avant que quelqu'un le prenne** :
declarer un **type de contenu neuf** (`type: question-sur-texte`) et l'enregistrer dans le registre de
fabriques de `YamlContentPackLoader`. Cela produirait deux facons d'ecrire la meme question a choix
multiple, dupliquerait la lecture des cles communes, et ferait de `S17-2` une story d'architecture
pour un gain nul. `ADR-0007` a decide un chargeur unique pilote par un schema ; un second type qui ne
differe que par une cle optionnelle est la premiere marche vers les neuf familles de chargeurs de la
version precedente (dette 3 de `CLAUDE.md`).

## 2. Ce que le chargeur exige, et ce qu'il laisse passer en silence

**L'enveloppe de paquet ne bouge pas.** Precision sur les quatre noms que la question me donne, parce
que l'un d'eux ne designe pas ce qu'on croit (lecture de `YamlContentPackLoader.chargerPaquet`) :

- `schema` : obligatoire, **doit valoir 1**, sinon *"schema inconnu"*. Il ne change pas ici. Une cle
  d'item **optionnelle** ne casse aucun paquet existant : il n'y a **pas de `schema: 2`** a introduire,
  et en introduire un obligerait les douze paquets servis a se declarer autrement pour rien ;
- `type` : obligatoire, reste `question` - voir la fin de la section 1 ;
- `descripteur` : obligatoire, correspondance imbriquee a **quatre** cles (`matiere`, `niveau`,
  `domaine`, `competence`), toutes obligatoires. Inchange ;
- `id` : **le fichier de paquet n'en porte pas.** L'`id` est celui de l'**entree de catalogue**
  (`catalogue.yaml`, avec `titre`, `consigne`, `mecanique`, `paquet`, `nombreDeManches`), et
  l'identifiant d'un item est **derive de sa position** dans le YAML (`QuestionContentSourceFactory`).
  Rien a faire de ce cote.

**Ce que la fabrique `question` exige d'un item** : `enonce`, `bonneReponse`, `distracteurs`
obligatoires ; `indice` et `explication` optionnels ; `indicesNommentLaReponse` au niveau du paquet.
Le texte-support s'ajoute a la seconde liste. Ce que `contenu-repris/` ne fournit pas reste ce que j'ai
etabli en section 1 de l'avis de faisabilite, et ce lot n'y change rien.

**Le piege, et c'est le point de cette section.** `QuestionContentSourceFactory` **ignore sans un mot
toute cle qu'elle ne connait pas** - dette nommee et non fermee par `ADR-0024`, ecrite dans sa propre
Javadoc. Donc, litteralement : **le schema actuel accepte deja un champ `texteSupport:` dans un
paquet.** Il l'accepte comme un puits l'accepte. Un paquet qui le porterait avant que la fabrique ne
le lise chargerait sans erreur, passerait les garde-fous de contenu du depot sans en emouvoir un
seul, laisserait la CI **entierement verte**, et l'enfant aurait devant lui une question sans son
texte. C'est pourquoi
`ADR-0050` decision 6 exige, **dans le commit qui fait entrer le champ**, un test qui prouve qu'il
atteint l'ecran sur un item reel en traversant la frontiere HTTP, sur le modele exact de
`LExplicationDUnItemReelAtteintLEcranDeRetourTest` - le meme geste qu'`ADR-0024` avait deja impose a
l'explication, pour la meme raison.

**Reponse en une ligne a la question posee** : le schema ne demande **aucune evolution** ; la fabrique
demande **une cle lue** ; et la formule *"le schema l'accepte tel quel"* est vraie d'une facon qui doit
etre dite, sans quoi elle vaut permission de ne rien verifier.

## 3. Le point qu'`ines-design` me route : ce qui est gardable dans son budget

Elle a raison de ne pas taire ce point, et il ne se resout pas par le verdict que j'ai deja ecrit en
section 3 de l'avis de faisabilite. Je le rejoue sur ce cas.

**Ce que la mesure de `nadia-prof` couvre exactement** : une manche reelle avec une consigne, **un
seul** bloc de texte substitue a l'enonce, et le cabinet. **Deux blocs de texte au-dessus du cabinet.**
La disposition proposee en porte **trois** : consigne, texte-support, question. Le budget de 240 px et
la marge de 27 px decrivent donc une page qui n'est pas celle que `S17-2` produira.

**Ce que je refuse de faire avec ce chiffre, dans les deux sens.** Le transporter tel quel serait un
chiffre sans sa methode - la faute que ce depot a deja sanctionnee. Mais en tirer le verdict inverse
par soustraction en serait une autre : les chiffres publies par `ines-design` et `nadia-prof` donnent
bien 27 px de marge au point le plus charge et 48 px pour une ligne de `.enonce`, et cette
arithmetique **suggere** que le point a 8 lignes ne tient plus une fois la question ajoutee. Elle ne
le **mesure** pas : elle ignore la fusion des marges, le repli reel de la question sur la largeur
disponible, et la police reellement installee - aucun fichier maison ne declare de `font-family`
(`ADR-0025`). **Ni feu vert, ni feu rouge : une mesure a refaire sur la disposition reelle**, avant
les vingt items et non apres. C'est ce que recommande `ines-design`, c'est ce que dit deja la section
3 de mon avis de faisabilite comme un **ordre de gestes**, et depuis `DEC-0039` section 3
l'instrument qui produit ce chiffre appartient a l'utilisatrice, plus a la squad.

**Ce qui n'est pas gardable, avec le motif propre a ce cas.** La hauteur d'un bloc de texte est une
propriete de pixels : elle depend du repli, donc de la largeur reelle, de la police installee et du
texte lui-meme. Ici s'ajoute une raison que les cas precedents n'avaient pas : la hauteur du
texte-support et celle de la question **varient ensemble** au gre de deux contenus independants, si
bien qu'il n'existe meme pas un pire cas calculable a partir de declarations. Un plafond en nombre de
mots ou de caracteres reste interdit (`DEC-0016`, `ADR-0028`, qui a deja refuse cette forme au motif
qu'elle serait rouge sur un contenu valide). **Cela appartient a la porte de `nadia-prof`, entierement.**

**Ce qui est gardable, et ce n'est pas rien.**

1. **Le plancher de lisibilite.** C'est le point que je n'attendais pas en ouvrant l'instruction, et
   c'est le plus utile : quand un budget vertical est etroit, la sortie la moins couteuse a ecrire est
   **de reduire la police du texte** - une ligne de feuille, un gain immediat, aucun rouge. Or le
   plancher de 1,25rem que `nadia-prof` tient pour non negociable n'etait garde que sur les **boutons
   rendus** et sur **sept selecteurs nommes a la main**. Methode du releve : lecture des **20**
   declarations `font-size` de `static/css/recreo.css` (seule feuille maison sur cet arbre ; 20 debuts
   de declaration reels, aucune occurrence parasite dans une variable), confrontees a cette liste et a
   la population de boutons. **Cinq regles n'etaient lues par aucun garde-fou** : `.signal .verdict`
   (le juste/faux que l'enfant lit apres chaque reponse), `.filtre-niveau`, `.question-confirmation`,
   `.cabinet-enseigne`, `.chip-niveau`. `.texte-support` aurait ete la sixieme, et le seul chiffre
   gardable de la proposition d'`ines-design` - 1,25rem - n'aurait ete garde par rien. Ce lot ferme le
   trou : `ADR-0050` clause 1, perimetre derive de la feuille, morsure prouvee sur la feuille servie
   **reelle mutee**. Consequence concrete pour `S17-2` : la valeur proposee par `ines-design` devient
   un **plancher tenu par la CI**, et gagner des pixels en rapetissant le texte demande desormais un
   arbitrage, pas une ligne de feuille.
2. **Le seul degat que ce champ pourrait faire aux onze jeux existants est deja rouge.** Un
   `<p class="texte-support" th:text="...">` sans condition rendrait, sur tous les jeux qui n'en
   portent pas, un paragraphe vide qui coute quand meme sa marge - au moment precis ou `DEC-0024` a
   deja retire un jeu pour un depassement de pli.
   `ToutEcranEchangeEstUnDocumentJouableTest.aucunEcranNeRendUnParagrapheVide` (perimetre derive des
   routes du controleur) le refuse deja. Aucune regle neuve n'est due.
3. **La classe sans habillage** : `HabillageDesEcransTest.aucuneClassePoseeSurUnElementNEstOrpheline`,
   derive des gabarits, rougit si `.texte-support` est posee sans qu'aucune feuille ne la declare.
4. **L'identite d'ecran**, section 4 - c'est le seul effet de bord structurel du champ.

**Ce qui serait gardable et que je n'ecris pas, dit plutot que taire (dette ouverte).** `ADR-0028`
demande qu'un habillage neuf **declare son repli** plutot que de le laisser au defaut.
`ines-design` declare explicitement ne poser ni `white-space: nowrap`, ni `overflow: hidden`, ni
`text-overflow: ellipsis` - donc, aujourd'hui, il n'y a rien a garder : l'absence est le defaut du
navigateur. Le risque est **posterieur** : le jour ou le pli serrera, tronquer le texte est la
correction la plus tentante, et **aucun garde-fou du depot ne l'interdirait**
(`LHabillageNeRogneJamaisUnElementPressableTest` ne juge que les **ancetres d'un pressable**, et
`.cabinet-enseigne` declare deja les trois proprietes de troncature, sur arbitrage). Une regle
derivee est possible - aucune regle maison applicable a un element dont le texte vient du modele ne
declare de propriete de troncature - et elle serait verte aujourd'hui sans aucune exemption, puisque
`.cabinet-enseigne` n'est cablee dans aucun gabarit (`F11`, `ADR-0035`). **Je ne l'ecris pas dans ce
lot** : elle repondrait a un usage que j'imagine, pas a un usage constate. **Elle devient due** le jour
ou un lot declare une troncature sur un texte lu par l'enfant, et cette phrase-ci est ce qui
l'empechera d'entrer en silence.

## 4. `ADR` du : oui, et ni `ADR-0046` ni `ADR-0049` ne couvrent le cas

- **`ADR-0046`** oblige la feuille maison a declarer tout **etat interactif** (survol, focus, appui)
  qu'une feuille tierce declare sur un **pressable**. Le texte-support n'est pas pressable et ne porte
  aucun etat interactif. Sans objet.
- **`ADR-0049`** porte sur la **graphie accentuee de la matiere** dans la donnee de catalogue. Sans
  objet - il vaudra pour l'entree de catalogue de `S17-2`, comme pour toute entree neuve, mais il ne
  dit rien d'un champ de texte.
- Aucun autre `ADR` ne couvre les deux trous mesures. `ADR-0032` **est** l'un des deux : il a raison
  pour aujourd'hui et rendra un verdict faux demain.

**`ADR-0050` est ecrit dans ce lot, avec ses deux regles dans le meme commit.** Son objet, sa decision
et ses alternatives sont dans le fichier ; ce qu'il faut en retenir ici :

- **clause 1** - aucune taille de texte declaree par la feuille maison ne passe sous 1,25rem, perimetre
  derive de la feuille et non d'une liste de selecteurs (section 3, point 1) ;
- **clause 2** - toute composante de `Question` est classee : dans l'identite d'ecran d'`ADR-0032`, ou
  hors d'elle avec son motif. **Ce que cela interdit reellement** : que le champ entre sans que
  personne dise si l'ecran le montre. Aujourd'hui, l'identite d'ecran est le couple enonce + jeu de
  propositions ; le jour ou un troisieme texte est rendu, deux items poses sur **deux textes
  differents** dont la question et les propositions coincident - *"Que ressent le personnage ?"* avec
  les memes trois choix, forme ordinaire sur vingt items de comprehension - seraient refuses comme une
  copie, avec un message qui nomme une cause fausse. La clause rougit au moment exact ou la composante
  apparait. Elle ne verifie pas que le classement est **juste** : elle transforme un oubli silencieux
  en affirmation signee, et c'est tout ce qu'elle achete, ce qui est ecrit dans l'`ADR` comme dans la
  Javadoc du garde-fou.

**Ce que `ADR-0050` decide en plus, et qui engage `marc-dev` avant le premier item** : le champ est
porte par **l'item**, jamais par le paquet ni par un niveau de regroupement neuf - le tirage est sans
remise et melange (`ListContentSource.next`), deux items poses sur le meme texte ne sont donc jamais
montres l'un apres l'autre de facon garantie, et un regroupement promettrait un ordre que la mecanique
ne tient pas. Le texte est **recopie** dans chaque item qui le porte. Et dans le commit qui fait entrer
le champ : un test qui prouve qu'il atteint l'ecran sur un item reel (section 2).

## 5. Ce que ce lot mesure, avec sa methode

- **Porte technique** : `bash outils/mvn-jdk21.sh -B clean verify` a la racine du worktree
  `s17-theo-comp5`. **BUILD SUCCESS**, reacteur complet - `recreo`, `recreo-domain`,
  `recreo-application`, `recreo-adapter-web`, `recreo-adapter-content`, `recreo-bootstrap` - le
  2026-08-19, **Java 21.0.10** (version rendue par les tests d'integration Spring Boot du meme
  journal), toolchain declaree. Aucune mesure partielle n'est citee dans ce document, donc aucun
  `-pl` et aucune question de `-am`.
- **Les deux garde-fous touches, avant et apres** : `TailleDesTextesEtBoutonsTest` passe de **26** a
  **28** tests verts (deux methodes neuves : la clause derivee et sa morsure sur la feuille servie
  reelle mutee ; la clause de non vacuite est portee par la clause elle-meme, pas par une methode a
  part). `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` passe de **6** a **9** (trois methodes
  neuves : le classement, et ses deux morsures). Comptes avant : `grep -c "@Test"` sur la version
  `HEAD` de chaque fichier. Comptes apres : ligne `Tests run` de chaque classe dans le journal de ce
  `verify`.
- **Les 20 declarations `font-size`** : comptees sur `adapters/adapter-web/src/main/resources/static/
  css/recreo.css`, commentaires retires, en ne retenant que les **debuts de declaration** (precedes de
  `{` ou de `;`, espaces ignores) - la meme lecture que celle du garde-fou, et la parade au defaut
  d'`ADR-0015` ou `--bs-btn-font-size:` passait pour une declaration. 20 debuts pour 20 occurrences
  brutes : aucune variable ne porte ce nom dans la feuille maison. Toutes en `rem`, toutes superieures
  ou egales a 1,25rem - c'est pourquoi la clause part **verte sur du materiau reel**, jamais sur une
  fixture.
- **Aucune de ces deux clauses n'est une regle ArchUnit** : l'objet juge est une declaration CSS pour
  l'une, la forme d'un record pour l'autre. **Le compte des regles ArchUnit du depot ne bouge pas**, et
  je ne le recopie pas ici : il vit dans `ADR-index.md` avec sa methode.

## 6. Ce que cet avis ne dit pas

- Il ne dit pas si la variante nue ou la variante encadree est la bonne : c'est `ines-design` qui
  propose et `nadia-prof` qui juge. Les deux variantes passent identiquement mes deux clauses.
- Il ne dit pas si le texte-support est **lisible** par un enfant de CM1, ni si 8 lignes sont trop.
- Il ne dit pas si `S17-2` tiendra au-dessus du pli : personne dans la squad ne peut le dire, et
  depuis `DEC-0039` section 3 ce n'est plus une dette de la squad a reconduire.
- Il ne prononce aucun Done, ni technique ni pedagogique.
