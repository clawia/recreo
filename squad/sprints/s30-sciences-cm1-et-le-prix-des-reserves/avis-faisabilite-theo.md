# Avis de faisabilite technique - s30 (`theo-archi`)

- **Date** : 2026-09-06. **Auteur** : `theo-archi`.
- **Etat du sprint au moment ou j'ecris** : **non ouvert**. `nadia-prof` rend son arbitrage prealable
  en parallele, `camille-po` n'a pas encore ecrit sa `DEC` de strategie, `sofia-sm` n'a pas gele
  l'objectif. Cet avis ne priorise rien, n'ecrit aucune story, n'affecte personne, ne fixe aucun
  rang, ne prononce aucun Done et ne touche pas a `squad/programme/MATRICE-couverture.md`.
- **Repertoire** : `squad/sprints/s30/`, sans slug. Le slug du sprint appartient a `sofia-sm`, qui
  le fixe en gelant l'objectif ; si elle renomme ce repertoire, ce fichier la suit.
  **Note de `sofia-sm`, au planning** : repertoire renomme
  `squad/sprints/s30-sciences-cm1-et-le-prix-des-reserves/`, ce fichier deplace dans le meme geste,
  comme annonce ci-dessus.
- **Worktree** : branche dediee `s30-theo-faisabilite`, lignee verifiee par
  `bash outils/verifier-lignee.sh` avant la premiere ecriture. Aucune ecriture dans le depot
  principal, aucune dans l'arbre de `nadia-prof`.

## 0. Ce sur quoi tout ce qui suit a ete mesure

Toute mesure de cet avis vient du meme arbre - ce worktree, provisionne sur `a5f2812` - et de
`bash outils/mvn-jdk21.sh -B clean verify` **a la racine du reacteur**, jamais d'un `-pl` sans
`-am`. La machine virtuelle des tests **rapporte JDK 21.0.10** (`ADR-0071` : c'est le JDK resolu qui
dit sa version, jamais l'entree de toolchain qui le designe).

**Resultat, apres le lot `REV-3` livre plus bas** : `BUILD SUCCESS`, 2 min 24 s, **1289 tests**,
0 echec, 0 erreur. Methode du 1289 : somme des six totaux `Tests run` du meme journal, module par
module - `domain` 99, `application` 13, `adapter-web` 388, `adapter-content` 585, `bootstrap`
(surefire) 198, `bootstrap` (failsafe, tests d'integration) 6. **Ce n'est pas un compte de regles
ArchUnit** : c'est un compte de tests rapportes par les deux moteurs, sur ce reacteur, a cette date.

---

## 1. `REV-3` - tranche, livre, garde-fou compris

**Statut : traite.** `ADR-0084` est ecrit, sa regle executable aussi, les deux sont dans le commit
`c2c82ac` de cette branche, et `mvn -B clean verify` est vert avec.

### La decision, en une phrase

**Le verdict d'une mecanique servie porte l'enonce de la manche qu'il ferme, obligatoirement, et
l'ecran de fin de partie rend cet enonce entier dans son propre element, a cote de la reponse et
jamais dedans.**

### Pourquoi cette forme-la, et pas les deux autres que la story laissait ouvertes

La story proposait trois voies : recomposer l'enonce complet, n'en montrer qu'un extrait, changer le
modele expose a la vue.

- **Recomposer n'a pas d'objet.** A l'instant ou cet ecran est rendu, `resoudre` a deja pose
  `mancheCourante = null` : le domaine n'a plus la manche. Le verdict est la **seule trace** de
  l'item joue - c'est exactement ce qu'`ADR-0024` avait etabli en y logeant l'explication et
  `ADR-0058` en y logeant la chaine jugee. Il n'y a rien a recomposer, il y a quelque chose a
  transporter.
- **Un extrait demande une politique de troncature** : une longueur, une regle de coupe. C'est du
  code qui juge un texte lu par un enfant, donc hors de ma frontiere, et son mode de defaillance est
  silencieux - une question coupee au mauvais endroit reste affichee et cesse d'etre une question.
- **Changer le modele expose a la vue** est ce qui se passe, mais ce n'est pas une alternative : le
  modele ne peut exposer que ce que le controleur possede, et le controleur ne possede que le
  verdict.

### Le cout reel, mesure

| Poste | Cout |
|---|---|
| Code de production | **7 fichiers**, **+57 / -4 lignes**, dont **15 lignes de code** - le reste est du commentaire qui dit pourquoi. Deux composantes de record, deux appels de construction, deux attributs de modele, un `<p>` de gabarit, une regle CSS. Un huitieme fichier, un test de domaine, voit trois assertions de verdict completees |
| Contenu | **zero octet**. Aucun paquet, aucun catalogue, aucun referentiel |
| Texte neuf lu par un enfant | **zero mot**. L'element ne porte que la valeur du champ `enonce` |
| Garde-fou | **419 lignes**, 3 tests, 2 morsures, un contexte Spring, **+9 s** environ sur `verify` |
| Ligne de matrice deplacee | **aucune**, conformement au quatrieme critere d'acceptation |

### La regle executable, et ce qu'elle interdit reellement

`bootstrap/src/test/java/com/recreo/bootstrap/rendu/AucuneReponseDeFinDePartieNEstOrphelineDeSaQuestionTest.java`.

**Population, avec sa methode** : **54 ecrans de fin de partie, un par paquet servi**, soit 54
parties de **deux manches** jouees mal jusqu'au bout - trois erreurs puis le geste de sortie de
revelation sur chacune des deux -, **108 items reellement joues**. Les jeux viennent du chargeur de
production (`new YamlContentPackLoader().games()`, l'appel meme que `ContenuConfiguration` fait).
Aucun paquet, aucun item, aucun texte et **aucune classe CSS** n'est nomme dans le critere : il
cherche des valeurs, pas des selecteurs, pour ne pas epingler le correctif du jour.

**Ce qu'il interdit, et ce n'est pas une assertion de presence** : trois clauses - la reponse portee
seule et entiere hors bouton ; la question de **cette** manche portee seule et entiere, **dans le
meme bloc** (parent commun) ; et **aucun** element ne portant la question d'une **autre** manche de
la meme partie. C'est la troisieme qui fait le travail : un ecran qui afficherait systematiquement
la question de la premiere manche serait complet, bien mis en page, et faux. C'est aussi pour elle
que la population est faite de parties de **deux** manches : sur une partie d'une seule manche cette
clause est indemontrable, et le garde-fou serait vert sur un produit fautif.

**La morsure est mesuree, pas affirmee** : gabarit remis dans son etat d'avant ce lot (la seule
ligne ajoutee supprimee, rien d'autre), le garde-fou seul relance sur `-pl bootstrap -am` rend
**54 griefs sur 54 ecrans**, un par paquet servi, chacun nommant son jeu -
`bandit-manchot-additions-cp--deux-manches : l'ecran de fin de partie nomme la reponse "1" sans
porter la question de la manche qu'elle commente ("0 + 1")`.

### Ce qu'un garde-fou existant a change a ce correctif, et je l'ecris plutot que de le taire

Le premier jet donnait a l'element la classe `.enonce`, celle que la question porte deja sur l'ecran
de manche - le choix qui n'ajoute aucune regle CSS. **`ADR-0052` clause 1 l'a arrete** :
`ToutEcranEchangeEstUnDocumentJouableTest.aucunEcranNeMontreUnEnonceSansLaConsigneDeSonJeu` exige la
consigne du jeu partout ou un `.enonce` est rendu, et il a rougi sur un item reel
(`<p class="enonce">0 + 1</p>`).

Cette clause a raison sur l'ecran de manche et n'a pas d'objet sur un ecran terminal, ou l'enfant
n'a plus rien a faire de la phrase. La classe est donc `.question-rappelee` et non `.enonce`.
**Le geste ressemble a une contrefacon** - changer de classe pour sortir du perimetre d'une regle -
et c'est pourquoi `ADR-0084` en ecrit le declencheur inverse : le jour ou un ecran **jouable**
porterait une question sous un autre nom que `.enonce`, c'est `ADR-0052` qu'il faut rouvrir. La
seule autre issue etait d'ajouter la consigne du jeu a l'ecran de fin de partie, c'est-a-dire de
prendre une decision de produit pour satisfaire un garde-fou.

### Ce que je route, et qui doit le savoir avant le planning

1. **La question s'affiche sans intitule.** Personne n'a juge que c'est lisible pour un enfant de
   six ans. `nadia-prof`, au titre de `M12`. Si elle veut un intitule, cela coute une ligne de
   gabarit et une entree d'inventaire dans `TexteAccentueDesGabaritsTest` - **pas de code**.
2. **Le texte-support n'est pas rappele.** Sur un item de comprehension (`ADR-0050`), la question
   peut renvoyer a un paragraphe que cet ecran ne porte pas. Cout de l'ajouter si elle le demande :
   une composante de plus sur les deux verdicts, un bloc de plus dans le gabarit. Je ne l'ajoute pas
   sans usage constate.
3. **Le poids visuel** de la question rappelee reprend a l'identique les valeurs de
   `.reponse-revelee`. C'est le choix le moins engageant, pas le choix juste : `ines-design`.
4. **`DesignationVerdict` n'est pas modifie**, parce que la designation n'a aucun ecran servi. Ce
   n'est pas un oubli : la population du garde-fou **echoue** sur une forme d'item qu'aucun ecran de
   fin de partie ne sait jouer, donc le jour ou un paquet de designation entre au catalogue, la
   question se pose en rouge plutot que de passer vert par omission.

---

## 2. Faisabilite du perimetre de contenu

### 2.1 Sciences et technologie CM1 en bloc - **faisable sans une ligne de code, sauf sur une ligne**

**Verdict : 18 des 19 lignes `non couvert` sont du contenu pur.** La dix-neuvieme demande une
decision avant d'etre servie en entier.

**Methode de ma verification, dans le meme paragraphe que l'affirmation.** J'ai (a) relu les vingt
lignes de la table *Sciences et technologie - CM1* de la matrice et la colonne `Statut` de chacune ;
(b) relu le schema d'item reellement lu par le chargeur de production
(`QuestionContentSourceFactory`, les onze cles : `enonce`, `bonneReponse`, `distracteurs`, `indice`,
`explication`, `partDesignee`, `texteSupport`, `illustration`, `illustrationTexteAlternatif`,
`texteAVoixHaute`, plus `indicesNommentLaReponse` au niveau du paquet) et confronte chaque ligne a
ce schema ; (c) verifie `SCI-1` **sur l'application qui tourne**, pas sur une lecture de code.

**`SCI-1` est bien servi par le champ `indice` existant - verifie a l'ecran.** Appels HTTP reels sur
l'instance locale, paquet `bandit-manchot-cycle-de-vie-cp`, une reponse fausse : la reponse rend
`<p class="verdict">Ce n'est pas ça. Essaie encore !</p>` (texte invariant de la mecanique) et
`<p class="indice">Il change de taille peu à peu.</p>` (texte de l'item). Une phrase par item,
ecrite par le paquet, affichee **des le premier echec** et jusqu'a la resolution de la manche. Rien
a coder.

**Un piege a nommer avant que quelqu'un le rencontre** : `BanditManchotSession.indice()` rend vide,
quel que soit le nombre d'echecs, quand le paquet declare `indicesNommentLaReponse: true` (clause
`M21`). Un paquet de sciences qui poserait ce drapeau **n'afficherait jamais son indice** et
`SCI-1` serait silencieusement non tenue, sans qu'aucun garde-fou ne le dise - le drapeau est
legitime, c'est sa combinaison avec `SCI-1` qui ne l'est pas. Ce n'est pas encodable en l'etat (un
garde-fou devrait savoir quelles lignes sont sous `SCI-1`, ce que le contenu ne declare pas) : je le
signale comme une consigne d'ecriture, pas comme une regle.

**La ligne qui n'est pas du contenu pur** : *"Extraire une information d'un document scientifique
(texte, tableau, schema, graphique) et la restituer."* La mention exigee d'avance par `nadia-prof`
est *"document textuel **et tableau** seuls"*. Or :

- `texteSupport` est **une chaine**, rendue par `<p class="texte-support" th:text="...">`, et la
  feuille de style ne porte **aucun `white-space: pre-line`** (verifie : la seule occurrence de
  `white-space` du fichier est un `nowrap`, ailleurs). Un tableau ecrit dans ce champ se rend en
  **une seule ligne collabee**, sans colonnes, et sans structure pour un lecteur d'ecran.
- Aucune cle du schema ne porte de donnees tabulaires.

**Deux issues, et je chiffre les deux plutot que d'en imposer une.**

| Issue | Cout | Ce qu'elle coute vraiment |
|---|---|---|
| **Servir cette ligne sur le document textuel seul** en s30, et router le tableau | **zero code** | La mention de la matrice se retrecit de *"texte et tableau"* a *"texte seul"* - c'est un mot de `nadia-prof`, pas de moi |
| **Servir le tableau** | une cle de schema, une composante de `Question`, un accesseur de session, un attribut de modele, un bloc de gabarit `<table>` avec ses en-tetes, une regle CSS, l'extension de `AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest` (`ADR-0070`), et un arbitrage d'accessibilite qui n'existe pas | Ce n'est pas une ligne de contenu, c'est une **capacite de rendu** de plus, du meme genre que le texte-support (`ADR-0050`) ou l'illustration (`ADR-0073`) - chacun a coute un `ADR` |

**Ma recommandation** : la premiere. Servir dix-huit lignes plus la moitie textuelle de la
dix-neuvieme sans code, et instruire le tableau comme sa propre decision quand il aura un usage
constate. Le choix est a `camille-po` et `nadia-prof`.

### 2.2 Un prerequis du lot que personne n'a encore nomme, et sans lequel la CI le refuse

**Les vingt lignes du referentiel de sciences et technologie CM1 n'ont aucun code alloue.**

Methode : `grep -c "^- \["` sur les dix-huit fichiers `squad/programme/*/competences-*.md`.
Resultat - sept referentiels portent des codes (questionner le monde CP 16, histoire-geographie CM1
14, EMC CP 14, EMC CM1 12, francais CP 12, francais CM1 12, mathematiques CM1 12, mathematiques CP
11, langues vivantes CM1 7), **onze n'en portent aucun**, dont
`squad/programme/sciences-et-technologie/competences-cm1.md`.

**Consequence executable** : `ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest`
(`ADR-0081`) refuse un paquet servi qui ne declare aucune ligne, et refuse un code qu'aucun
referentiel ne declare. **Un paquet de sciences ne peut donc pas entrer en production tant que les
codes ne sont pas alloues.** Ce n'est ni un obstacle ni un cout de conception : c'est un geste de
referentiel, fait par le developpeur du lot dans le meme commit que le paquet - precedent exact,
`lea-dev` a alloue les 14 codes d'EMC CP dans le commit `5526932` du lot `S29-4`.

Forme du code : `[SCI-CM1-01]` a `[SCI-CM1-20]`, conforme au motif du garde-fou
(`[A-Z]{2,4}-[A-Z0-9]{2,4}-[0-9]{2}`) et sans collision avec les conditions opposables `SCI-1`,
`SCI-2`, `SCI-3`, qui n'ont pas cette forme.

### 2.3 `CONS-1` - cout technique, et le garde-fou que `DEC-0078` me route

**Cout technique : nul.** Dix chaines de caractere dans un seul fichier.

Methode : sur `adapters/adapter-content/src/main/resources/catalogue.yaml`, **54 entrees**
(`grep -c "^  - id:"`), **54 consignes**, et **10 occurrences** exactes de *"Lis bien la question,
puis choisis la bonne réponse."*. Les chiffres de `DEC-0078` sont confirmes sur le fichier servi.
Aucun code, aucun schema, aucune migration : la consigne est une valeur d'entree de catalogue, lue
au demarrage de la partie, et `LaConsigneDUnJeuServiAtteintLEcranDeMancheTest` garantit deja qu'elle
atteint l'ecran. Le cout du lot est **entierement** dans le rejeu de `nadia-prof` (0,5 par jeu,
chiffre par elle), pas dans l'ecriture.

**Faut-il un garde-fou ? Oui pour le comptage, non pour la propriete - et je maintiens la
distinction qu'`ADR-0083` avait deja posee.**

- **Ce qu'un garde-fou peut faire, si `camille-po` ou `nadia-prof` le demande** : un cliquet au sens
  d'`ADR-0067`, derivant du catalogue reel la population des consignes et refusant qu'une meme
  consigne serve plus de `N` entrees, **`N` valant exactement la dette du jour**. Cout : une
  trentaine de lignes plus sa morsure. Effet reel : la tendance de `+5 par sprint` devient rouge au
  onzieme jeu.
- **Un point que je dois corriger dans mon propre avis d'`ADR-0083`, et le motif est celui du
  cliquet lui-meme** : j'y ecrivais *"le plafond vaut exactement la dette du jour (10)"*. **Il ne
  vaudra 10 que si le cliquet est ecrit avant le correctif.** Si `CONS-1` est corrige d'abord, le
  plafond doit valoir le compte **d'apres** correctif - et si les dix consignes deviennent toutes
  distinctes, ce compte est **1**, ce qui est un cliquet bien plus mordant. *Un cliquet dont le
  plafond depasse la dette n'est pas un cliquet* : ecrire le cliquet a 10 apres avoir paye la dette
  laisserait neuf places libres.
- **Ce qu'aucun garde-fou ne peut faire** : verifier que *"la consigne nomme la tache de son jeu"*.
  C'est la propriete que `nadia-prof` demande, et elle porte sur le rapport entre un texte et ce que
  le jeu demande de faire. Toute tentative retombe sur une liste de tournures interdites -
  un critere enumere sous une promesse universelle, vert par defaut sur la onzieme formulation.

**Ma recommandation** : si le cliquet est voulu, l'ecrire **apres** le correctif et lui donner le
compte mesure a ce moment-la. Je ne l'ecris pas de moi-meme : `DEC-0078` section 3 laisse
explicitement la decision a l'equipe, le plafond depend d'un correctif qui n'est pas ecrit, et un
cliquet pose au mauvais moment est pire que pas de cliquet.

### 2.4 Les quinze reserves de s29 - avis en bloc

**Toutes faisables sans une ligne de code, toutes du contenu.** Les quatre corrections mot pour mot
(`CYV-R1`, `OBJ-R1`, `HG-R7`, `VIV-R2`), les trois rejeux bornes (`OBJ-R2`, `HG-R6`, `POS-R2`), les
sept proprietes sans texte impose (`REG-R1`, `PER-R1`, `FOP-R1`, `FOP-R2`, `FOP-R3`, `DRO-R1`,
`DRO-R2`) et le cout nul (`DRO-R3`) touchent des fichiers YAML et rien d'autre. Trois remarques
techniques, et aucune n'est bloquante :

- **`VIV-R2`** demande explicitement que `FER-1`, `FER-2`, `FER-3` restent intactes et que la
  fermeture de `QLM-CP-01` ne soit pas remise en cause. `FER-2` (*"au moins dix items servent la
  ligne"*) est **calculable** depuis `ADR-0081` : les items declarent leur ligne. Remplacer trois
  enonces sans toucher aux declarations ne change aucun compte ; c'est verifiable dans le diff, pas
  seulement a la relecture.
- **`FOP-R2`** demande que *"la declaration du paquet soit corrigee dans le meme geste"*. Le paquet
  declare aujourd'hui que ses dix distracteurs sont d'une nature qu'ils n'ont pas.
  `AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest` garantit qu'une cle est **lue**, jamais
  qu'elle **dit vrai** : la coherence entre une declaration de paquet et ses items n'est pas gardee,
  et elle ne l'est pas davantage apres correction. C'est un fait, pas une objection.
- **`DRO-R2`** etend une propriete a **tout item binaire du depot** (*"l'indice porte le critere et
  jamais la categorie"*). Une extension de portee de cette famille demande de savoir quels items
  sont binaires ; c'est derivable (deux propositions), mais juger qu'un indice *"porte la
  categorie"* ne l'est pas. Non encodable, tenue par `nadia-prof` comme elle l'ecrit.

---

## 3. Le garde-fou de coherence entre un bilan de matiere et le compte reel de ses lignes

**Question posee par `DEC-0077` section 7 et jamais tranchee. Ma reponse : oui, c'est encodable ; le
prix est modere ; mais il y a une condition, et sans elle le garde-fou fabriquerait le defaut qu'il
pretend fermer.**

### 3.1 Le fait, remesure

Methode : parcours programmatique de `squad/programme/MATRICE-couverture.md`, decoupage sur les
titres de niveau 2 de la forme `## <Matiere> - <CP|CM1> (cycle N)`, puis comptage des lignes
commencant par `**Bilan` dans chaque tranche.

| | Nombre |
|---|---|
| Tables de matiere | **18** |
| Sans aucune phrase de bilan (les quatre plus anciennes) | **4** |
| Avec exactement une phrase de bilan | **13** |
| Avec **trois** phrases de bilan datees (histoire-geographie CM1) | **1** |
| **Tables portant au moins un bilan** | **14** |

**Ecart avec `DEC-0077` section 7, signale sans en tirer de reproche** : cette section annonce
*"treize tables portent une phrase Bilan"* et *"quatre n'en portent aucune"*, soit dix-sept pour
dix-huit tables ; et son enumeration des tables exactes en nomme douze la ou le texte dit onze. Le
compte que je mesure est **quatorze**. Je ne corrige pas la `DEC` - ce n'est pas mon fichier - et je
le signale parce que **c'est precisement l'argument** : un comptage a la main sur ce fichier a
derive une quatrieme fois, dans le document meme qui documentait les trois premieres derives.

### 3.2 Ce qui est encodable, et ou

**Encodable, et la maison existe deja.** Deux tests de `bootstrap` lisent deja `squad/` sur
l'arborescence du depot : `ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest` lit
`squad/programme`, `ToutesLesReferencesDeDecisionResolventTest` lit `squad/decisions`. Aucun
outillage neuf, aucun script, aucune etape de CI supplementaire : un test JUnit de plus, qui echoue
dans `mvn verify` comme les autres.

**Le critere** : pour chaque table de matiere, le compte des statuts de la colonne `Statut` egale le
compte que la phrase de bilan de cette table declare.

### 3.3 La condition sans laquelle je ne le recommande pas

**La phrase de bilan doit devenir une declaration lisible par une machine, au lieu d'etre une phrase
que le garde-fou devinerait.** Les quatorze bilans existants ne partagent aucune forme : *"**Bilan**
: 20 lignes, 19 `non couvert`, 1 `non couvrable en l'etat`."*, *"**Bilan, corrige le 2026-09-06
(`camille-po`, s29) - deux lignes de plus ferment** : ..."*, *"**Bilan du 2026-09-04 (s28, second
passage, composition B)** : ..."*, et deux phrases *"Bilan de la matiere apres sXX"* au fil du
texte.

Un analyseur qui accepterait ces quatre formes serait **un critere enumere sous une promesse
universelle** : il passerait vert sur la quinzieme formulation, c'est-a-dire sur le prochain bilan
ecrit. Et son mode de defaillance le plus probable n'est pas le faux vert mais le **faux rouge sur
une reformulation legitime**, qui pousserait a reecrire un bilan juste pour plaire au parseur -
la famille de defaut que ce depot sanctionne, et que le faux rouge d'`ADR-0083` a deja illustree.

**Ce qui leve la condition** : que chaque table porte, en plus de sa prose, **une ligne de bilan de
forme fixe** que le garde-fou lit - la prose reste libre, le chiffre devient declare. La forme
exacte est de la redaction du depot, donc a proposer par moi et a valider par `sofia-sm` et le
`relecteur-pedagogique`, pas a imposer ici.

### 3.4 Le prix, chiffre

| Poste | Estimation |
|---|---|
| Le test lui-meme (decoupage des tables, lecture de la colonne `Statut`, comparaison) | ~130 lignes |
| Sa mise a l'epreuve (`ADR-0013` obligation 3) : une table mutee en memoire dont un statut change sans que le bilan bouge, et une table dont le bilan change sans que les statuts bougent | ~40 lignes, **sur le fichier reel mute**, jamais sur une fixture de ma main |
| La derivation des deux exemptions - les quatre tables sans bilan, la table a bilans dates | **c'est le poste risque** : elles doivent etre **derivees** (une table sans ligne de bilan n'est pas jugee ; la derniere ligne de bilan d'une table fait foi), jamais enumerees par leur nom, sans quoi le garde-fou est decoratif des la premiere table neuve |
| Normalisation prealable des quatorze bilans | 14 lignes de matrice a reecrire - **fichier de `camille-po`**, pas le mien |
| Cout d'execution | negligeable : lecture d'un fichier, aucun contexte Spring |

**Total : environ 170 lignes de test, une demi-journee d'agent, plus la normalisation des quatorze
bilans qui ne m'appartient pas.**

### 3.5 Mon avis

**Faisable et utile ; pas urgent ; a ne pas ecrire sans la normalisation prealable.** Trois derives
en deux sprints, toutes rattrapees a la main par `camille-po`, sont un signal reel : le mode de
defaillance est l'ecart muet, pas l'echec. Mais un garde-fou qui devine une phrase est pire que la
revue humaine qu'il remplacerait. **Ma recommandation : normaliser d'abord les quatorze bilans
(geste de `camille-po`, aucun de mes fichiers), ecrire le garde-fou ensuite.** Si `sofia-sm` veut
l'inverse - un controle humain formalise a chaque fusion de matrice - c'est defendable et cela ne
coute rien ; ce qui n'est pas defendable est de laisser la question ouverte une quatrieme fois.

---

## 4. Les quatre questions du palier 2 qui me sont routees

Le palier 2 n'est pas ouvert ce sprint. **Trois des quatre peuvent attendre sans cout. Une seule
gagne a etre tranchee maintenant, et une cinquieme chose - que personne n'a demandee - devrait
l'etre avec elle.**

### 4.1 Les six dessins sont-ils une quatrieme population d'images ? - **peut attendre, sous un declencheur precis**

**Ma reponse de fond, pour qu'elle ne se reinvente pas** : non, ce ne sont pas une quatrieme
population. `ADR-0082` a cree une troisieme population parce qu'une reproduction d'oeuvre pose un
probleme de **provenance** - des droits, une renonciation, un pivot de 1900 - qui n'a rien a voir
avec le dessin lui-meme. Un plan, une carte, une frise, un quadrillage sont **dessines par le
projet** : leur provenance est triviale, et leur seul probleme est l'**exactitude**. L'exactitude
n'est pas un critere de population, c'est une **declaration par fichier**. La forme economique est
donc : meme population que les dessins maison, `DES-1` a `DES-7` inchanges, plus une declaration
obligatoire de source sur tout dessin qui represente une chose reelle, et une declaration explicite
quand il ne represente rien de reel (le point 2 de `nadia-prof`, sans lequel elle *"verifierait dans
le vide"*).

**Cela repond du meme geste a la deuxieme question - ou vit la preuve de l'exactitude d'un trace
geographique** : dans le registre des dessins, a cote du dessin, comme `ADR-0082` fait porter au
registre l'identification d'une oeuvre. Les deux questions sont **une seule decision** et coutent un
seul `ADR`.

**Pourquoi cela peut attendre, et jusqu'a quand exactement.** Zero dessin du palier 2 existe dans le
depot (`nadia-prof` l'a mesure : 24 fichiers, tous figures planes ou pieces de monnaie). Tant que
personne ne dessine, la question ne coute rien. **Le declencheur n'est pas l'ouverture du palier 2,
c'est le premier coup de crayon** : la decision gouverne ce que le dessinateur doit consigner, et
la prendre apres coup obligerait a retrouver, apres coup, d'apres quoi chaque trace a ete fait.
**Si un seul dessin du palier 2 est produit en s30, la decision est due avant lui.**

*Note de comptage* : la section 8.1 de l'arbitrage de `nadia-prof` titre *"les cinq dessins"* et en
enumere six (plan de classe, plan de musee, carte de France, planisphere, frise, quadrillage). Six
est le compte que je retiens, comme le fait la demande qui m'est adressee. Signale au
`relecteur-pedagogique`, pas corrige par moi.

### 4.2 L'extension du garde-fou 4 d'`ADR-0080` au rang de declaration - **la seule qui gagne a etre tranchee maintenant**

**Encodable, derivable, et bon marche.** Le garde-fou 4
(`LaBonneZoneNEstPasPredictibleSurUnPaquetTest`, 173 lignes) interdit deja deux formes de la meme
contrefacon : la bonne zone portant le meme identifiant sur une majorite d'items, et la bonne zone
etant la plus grande de son item sur une majorite d'items. **Le rang de declaration est la
troisieme forme, et il se derive exactement comme les deux autres** - la position de la bonne zone
dans la liste declaree, comparee a la premiere et a la derniere, sur une majorite d'items.

**Cout mesure sur le fichier existant** : ~25 lignes de critere, un paquet fautif de plus dans les
fixtures, un test de morsure - **environ 50 lignes au total**, dans un fichier qui a deja sa
structure, ses fixtures et ses trois tests.

**Pourquoi maintenant plutot qu'avec `T6`, et c'est un argument de cout, pas de zele.** Les deux
clauses existantes mordent aujourd'hui sur des paquets fabriques - c'est la dette datee d'`ADR-0080`
- donc la troisieme peut etre ecrite et prouvee **exactement comme elles**, sans attendre un paquet
reel. Ecrite maintenant, elle coute la relecture d'un fichier deja ouvert. Ecrite dans six semaines,
elle coute de retrouver l'arbitrage, de re-deriver la propriete et de rouvrir les fixtures. Et
surtout : **le cliquet ne la nommera pas.**

### 4.3 Ce que personne n'a demande et qui devrait accompagner 4.2

`AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest` porte la dette
d'`ADR-0080` avec un plafond de zero paquet servi. Son message d'echec nomme
`BALAYAGES_A_ETENDRE`, trois tests, dont le garde-fou 4. **Mais il ne dit rien de la troisieme
clause** : il a ete ecrit le 2026-09-04, et `nadia-prof` a route le rang de declaration **le
2026-09-06**. Le jour ou il rougira, il enverra le lecteur etendre trois balayages sans lui dire
qu'une propriete de plus est due.

Ce n'est pas un defaut du cliquet, c'est du retard. **Cout de le combler : une entree de plus dans
le message et son assertion, moins de dix lignes.** Je ne le fais pas dans cette passe - le palier 2
n'est pas ouvert et je ne m'auto-affecte pas de travail - mais je le nomme ici pour qu'il ne
devienne pas la cinquieme dette documentee sans etre payee.

### 4.4 `ADR-0080` est encore `Propose` - **doit le rester, et c'est sans cout**

**Aucune raison de le passer `Accepte` en s30.** Son statut dit une verite verifiable : la plupart
de ses regles ne sont pas ecrites, et l'`ADR` dit pour chacune quand elle est due et si elle serait
verte ou rouge. Un statut `Accepte` porterait une promesse que le code ne tient pas - exactement la
famille de la dette 1 de `main`, une abstraction documentee partout et implementee nulle part.

**Ce qui le fait passer `Accepte` est `T6`**, le premier paquet de designation servi : ce jour-la
les garde-fous 4, 5 et 6 derivent leur perimetre de la population reelle, le cliquet monte son
plafond dans le meme diff, et le statut cesse d'anticiper. La dette est deja tenue par un mecanisme
executable ; **attendre ne coute rien, et c'est le seul des quatre points ou l'inaction est
strictement neutre.**

---

## 5. Recapitulatif des avis

| Objet | Avis | Ce qu'il faut savoir avant de le planifier |
|---|---|---|
| `REV-3` | **Fait.** `ADR-0084`, regle executable verte et mordue, `verify` vert | Reste le rejeu de `nadia-prof` (chiffre par elle) et trois points routes : l'intitule, le texte-support, le poids visuel |
| Sciences et technologie CM1, 18 lignes | **Faisable en l'etat, zero code** | Prerequis : allouer les vingt codes `[SCI-CM1-nn]` dans le referentiel, sinon la CI refuse le paquet |
| Sciences CM1, la ligne "extraire d'un document" | **Faisable moyennant un arbitrage**, pas un `ADR` | Le document textuel : zero code. Le **tableau** : une capacite de rendu de plus, du meme prix qu'`ADR-0050` |
| `CONS-1` | **Faisable en l'etat, zero code** | Le cliquet possible s'ecrit **apres** le correctif, avec le compte mesure a ce moment-la, jamais avec 10 |
| Les quinze reserves | **Faisables en l'etat, zero code** | Trois notes techniques en 2.4, aucune bloquante |
| Bilans de matiere (`DEC-0077` s7) | **Encodable, ~170 lignes** | A ne pas ecrire avant que les quatorze bilans aient une forme fixe - sinon le garde-fou devine une phrase |
| Palier 2, population des dessins + preuve d'exactitude | **Peut attendre** | Declencheur : le **premier dessin produit**, pas l'ouverture du palier |
| Palier 2, rang de declaration | **Gagne a etre tranche maintenant**, ~50 lignes | Le cliquet de dette ne le nomme pas encore : ~10 lignes de plus |
| `ADR-0080` statut `Propose` | **Doit le rester** | Passe `Accepte` avec `T6`, pas avant. Inaction strictement neutre |
