# Avis de faisabilite - s26

- **Auteur** : `theo-archi`.
- **Date** : 2026-09-01.
- **Perimetre arbitre par l'utilisatrice** : les quatre lignes a voix, **plus** la dette.
- **Arbre** : worktree `s26-theo`, parti de `round-two` a `153df5a`, **fusionne avec `round-two` a
  `7934d37`** avant la mesure de cloture de cet avis. Toutes les mesures ci-dessous nomment celui
  des deux arbres sur lequel elles ont ete faites.
- **JDK** : **21.0.10**, celui que la machine virtuelle des tests rapporte d'elle-meme, jamais celui
  qu'une entree de toolchain declare (`ADR-0071`). Enveloppe : `bash outils/mvn-jdk21.sh`.
- **Ce que cet avis est** : un avis de faisabilite. Il ne redige aucune story, n'en priorise
  aucune, ne decide ni produit ni pedagogie.

---

## 0. Ce que j'ai livre, et ce que j'ai seulement instruit

| Objet | Etat |
|---|---|
| **`ADR-0074`** - tout JavaScript servi vit dans un fichier ; un gabarit n'en porte que par reference | **Accepte, regle ecrite, verte et mordue dans le meme commit.** La dette du rang 3 de `DEC-0067` est fermee |
| **`ADR-0075`** - la famille a voix est un champ d'item ; l'identite d'un tour cesse d'etre ce que l'ecran montre | **Accepte.** Ses quatre clauses sont ecrites et mesurees rouge puis verte sur une sonde ; elles sont **dues dans le commit qui livre le champ**, et sa section *Regle executable* dit pourquoi les committer aujourd'hui serait ecrire quatre regles vertes sur une population vide |
| `ADR-index.md` | a jour : deux lignes neuves, **quatre amendements** (`ADR-0032`, `ADR-0050`, `ADR-0070`, `ADR-0008`) |
| Le garde-fou de `P(bonne / presente)` que `nadia-prof` route | **chiffre en section 4, pas ecrit.** Ce n'est pas mon lot, et son seuil est un jugement pedagogique |

---

## 1. La reponse a `camille-po` : **un champ d'item**, et elle n'est pas gratuite

> **La famille a voix est un champ d'item. Ce n'est ni une forme d'item neuve, ni une mecanique
> neuve.** `ADR-0075`, decision 1.

**Le critere n'est pas de moi** : `ADR-0058` etablit qu'une forme d'item de plus se justifie quand
**la facon dont l'enfant repond** change. `ADR-0073` l'a applique au palier visuel a s25 ; c'est le
precedent que `camille-po` a raison de nommer, et je l'applique au meme critere plutot qu'a la
ressemblance.

| Ligne ouverte par `nadia-prof` | Comment l'enfant repond | Mecanique |
|---|---|---|
| Localiser une syllabe | il choisit parmi trois propositions en texte | bandit manchot, inchange |
| Identifier un son | il choisit parmi des propositions en texte | bandit manchot, inchange |
| Associer graphemes et phonemes (**deux paquets**) | il choisit parmi trois propositions en texte | bandit manchot, inchange |
| Ecrire un mot en autonomie | il ecrit | saisie, inchangee |

**Ce qui change est ce que l'enfant entend avant de repondre**, exactement comme le palier 1 visuel
changeait ce qu'il regarde avant de choisir.

### Ce que sa reponse vaut, avec la source du chiffre

`nadia-prof` a chiffre les deux branches **avant de connaitre ma reponse** - son addendum du
2026-09-01, section 12.4 :

| Ma conclusion | Sa borne | Lignes fermables | Plafond |
|---|---|---|---|
| **Champ d'item** | **5 paquets** | **4 sur 4** | **35 / 41 = 85,4 %** |
| Mecanique | 4 paquets | 3 sur 4 | 34 / 41 = 82,9 % |

**Sa branche haute est celle qui se realise.** Le rang et le choix des lignes restent a
`camille-po` ; je ne les touche pas.

### Et le piege, en acte : ce n'est **pas** le cas d'`ADR-0073`

`yanis-qa` : *"le defaut nait dans l'intervalle entre l'ecriture d'une clause et sa mesure sur
l'objet motivant. Juste en intention, faux en extension."*

**Appliquer `ADR-0073` par ressemblance aurait ete exactement cela.** Le raisonnement - *"l'enfant
choisit toujours en texte, donc c'est une composante optionnelle de plus, donc c'est gratuit"* - a
une premiere moitie vraie et une seconde fausse. **Il a fallu batir les quatre paquets et les
charger pour le voir.**

**Sonde V**, non commitee, sur le patron des sondes d'`ADR-0073` : la composante
`Optional<String> texteAVoixHaute` posee sur **les deux** formes d'item, lue par **les deux**
fabriques, et **quatre paquets servis depuis `contenu/`** - un par ligne ouverte, ecrits d'apres ses
conditions et non d'apres l'idee que je m'en fais.

*Methode : `bash outils/mvn-jdk21.sh -B -o clean test` sur la **racine** - reacteur complet, donc la
question du `-am` ne se pose pas -, arbre `s26-theo` a `153df5a`, JDK 21.0.10 rapporte par la machine
virtuelle des tests. Totaux par somme de l'attribut `tests` des rapports XML de surefire apres
`clean`, jamais par les lignes `Tests run:` des rapports `.txt` (`squad/README.md`, section 4,
amendement s11). Reference sans sonde : domain 80, application 13, adapter-web 364, adapter-content
410, bootstrap 145 = **1 012 tests**.*

| | Arrets du compilateur | Classes rouges | Echecs |
|---|---|---|---|
| Reference | 0 | 0 | 0 (hors l'echec `ADR-0066` anterieur, repare depuis a `7934d37`) |
| **Sonde V** | **0** | **5**, toutes dans `adapter-content` | **8** |

**`domain` reste a 80 / 0 et `adapter-web` a 364 / 0.** Aucune mecanique n'est touchee : c'est la
moitie mesuree de la reponse.

**L'autre moitie, celle qu'aucune lecture ne donne** : `ADR-0032` rougit sur **trois des quatre
paquets, six collisions**.

| Paquet de sonde | Collisions |
|---|---|
| `sonde-voix-localiser-syllabe` | 2 |
| `sonde-voix-identifier-son` | 2 |
| `sonde-voix-ecrire-mot` | 2 |
| `sonde-voix-graphemes` | 0 |

**La cause est le sujet meme de la famille.** `VOI-13` exige que le mot travaille ne soit pas dans
ce que l'enfant lit ; la matiere qui distingue deux tours quitte donc son ecran, l'enonce devient une
consigne invariante, les propositions sont stables, et **tous les items deviennent le meme ecran**.
Le paquet des graphemes echappe seul, parce que ses propositions varient d'un item a l'autre.

**Le contraste avec `ADR-0073` est la reponse complete a `camille-po`** : l'illustration entrait dans
l'identite d'ecran **parce que l'enfant la voit** ; le texte de l'adulte, il l'**entend**.
`ADR-0075` amende donc `ADR-0032` - l'identite d'un tour est ce qui le distingue **pour l'enfant, vu
ou entendu**. Mesure de l'autre cote : la composante classee **dans** l'identite,
`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` passe a **16 tests, 0 echec** (*perimetre :
`adapters/adapter-content` et ses amonts, `-am` porte*).

### Le motif technique de sa branche *"mecanique"* - repondu par une mesure, pas par un argument

`nadia-prof` : *"l'ecran de revelation affiche la bonne reponse, et un adulte qui le lit la dit a
voix haute. Ce n'est pas `VOI-1`, qui gouverne le stimulus : c'est le moment de la correction."*
**Le fait est juste.**

**Mesure sur `round-two` a `7934d37`** : `fragments/retour.html` porte en tete *"ce fragment ne rend
plus qu'un seul etat - la fin de partie"*, et `fragments/manche.html` nomme lui-meme *"celui qui
porte la reponse revelee au troisieme echec"*. **La revelation vit sur l'ecran de la manche.** Meme
fragment, autre etat.

- Ce n'est **pas** un ecran de plus a concevoir.
- La reponse revelee est une **proposition**, et `VOI-10` ferme le champ de l'adulte a toute
  proposition **par construction**. La fuite qu'elle decrit suppose que l'adulte lise l'ecran de
  l'enfant faute d'avoir un texte a lui : c'est le **point 1 de sa propre clause `VOI-12`**, une
  exigence de rendu et de contenu.
- `VOI-13` porte deja sur tous les ecrans du tour, et c'est calculable.

**Ce que je ne dis pas** : que son audit soit inutile. `VOI-12` point 1 le lui impose sur cet etat
comme sur les autres, et **elle a ecrit elle-meme que ce cout est deja dans son assiette** dans la
branche champ d'item. Je dis que ce chemin ne remplit pas le critere d'`ADR-0058`.

---

## 2. Ce que la distinction coute techniquement

### 2.1 Le champ, et sa place dans le schema

- **Nom** : `texteAVoixHaute`. Il nomme **ce que le texte est**, jamais qui le prononce - `ADR-0006`
  et la dette n. 2 de `CLAUDE.md` tiennent le contrat de contenu libre de tout ce qui n'est pas la
  matiere. *L'adulte* est une premisse de produit qui peut changer de forme ; le champ non.
- **Forme** : une composante `Optional<String>`, cinquieme du patron ouvert par `explication`
  (`ADR-0024`), `partDesignee` (`ADR-0062`), `texteSupport` (`ADR-0050`) et `illustration`
  (`ADR-0073`). Un paquet qui n'en porte pas ne remplit **aucune valeur factice**.
- **Sur les deux formes d'item, et c'est la clause que la lecture seule aurait manquee.** Trois
  lignes sur quatre sont des items de choix ; **la quatrieme, ecrire un mot, est un item de saisie**.
  Un champ pose sur `Question` seule aurait ete juste pour quatre paquets sur cinq et **faux pour le
  cinquieme** - celui-la meme qui collisionne le plus mecaniquement, ses items n'ayant aucune
  proposition pour les distinguer.
- **Cle de schema** : `texteAVoixHaute`, lue par les deux fabriques.

### 2.2 La regle qui empeche qu'il soit rendu a l'enfant

Deux clauses, **et il ne faut pas les fusionner** : elles lisent deux populations differentes.

- **`VOI-10`** - le champ ne contient aucune proposition **de son item** : ni la bonne reponse, ni un
  distracteur, ni la forme de reference d'un item de saisie.
- **`VOI-13`** - la matiere prononcee n'apparait dans **aucun texte que l'enfant lit, sur aucun ecran
  du tour** : consigne du catalogue, enonce, propositions, indice, explication, texte-support, texte
  alternatif d'illustration.

**La consigne vient du catalogue et non du paquet** (`ADR-0052`). Une clause qui ne lirait que le
paquet serait verte sur la fuite la plus facile a ecrire.

**Ce que la comparaison compare, et ce n'est pas cosmetique** : sur une forme repliee - casse et
signes diacritiques - sur le patron de `NormalisationDeSaisie`, qui existe deja dans le domaine. Une
comparaison nue declarerait *chapeau* absent d'un enonce portant *Chapeau*. **Ce qu'elle ne voit pas
est nomme** : un mot decoupe en syllabes, une periphrase. Elle attrape la faute reelle - le mot
recopie - pas la faute imaginative.

### 2.3 Ce qu'`ADR-0070` impose a ce champ, et ce qu'il n'impose pas

**Mesure, trois etats, sur la sonde** - `bash outils/mvn-jdk21.sh -B -o -pl bootstrap -am test
-Dtest=AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest`, *perimetre `bootstrap` et ses amonts,
`-am` porte* :

| Etat | Verdict |
|---|---|
| Les deux fabriques nomment `"texteAVoixHaute"` | **vert** |
| Aucune fabrique ne le nomme, le classement d'identite d'ecran le nomme | **vert** |
| Personne ne le nomme | **rouge** : *"ces cles sont ecrites dans un paquet servi et ne sont nommees par aucune source du depot"* |

**Ce qu'`ADR-0070` impose** : la cle ne peut pas etre ecrite dans un paquet avant que quelqu'un ne
la nomme. **Consequence d'ordonnancement pour le sprint : les paquets ne partent jamais avant les
fabriques.** Un lot de contenu fusionne en premier rend le build rouge, et c'est le comportement
correct.

**Ce qu'`ADR-0070` n'impose pas, et qu'il ne faut pas croire** : que le champ soit **lu au bon
endroit**. C'est la propriete faible qu'il decide d'avoir, et elle est bonne ; mais sur ce champ-ci
elle sera satisfaite **par le seul classement d'identite d'ecran**. Un champ ecrit dans cinq
paquets, relu par `nadia-prof`, et jamais rendu a l'ecran serait **vert partout**. Ce qui l'attrape
est une traversee, sur le patron de `LeTexteSupportDUnItemReelAtteintLEcranDeMancheTest` -
`ADR-0075` clause 4.

### 2.4 `VOI-12` : ce que le gabarit et le modele de vue doivent porter

`nadia-prof` : *"sans `VOI-12`, je refuserai les paquets a voix"*. `ines-design` traite la forme ;
ce qui suit est ce que la structure doit rendre possible, et rien de plus.

- **Deux textes distincts dans le modele de vue, jamais un seul enrichi.** Le texte de l'adulte est
  un attribut de modele a lui. **Un texte unique dont le gabarit devinerait la part destinee a
  l'adulte porterait la distinction dans le gabarit**, ce que `DEC-0068` section 4 s'interdit
  explicitement.
- **La phrase d'interdit est du contenu, et elle vit dans l'entree de catalogue, pas dans l'item.**
  Motif : `nadia-prof` exige que *"deux paquets dont les interdits different ne portent pas la meme
  phrase"* (point 3). Une phrase ecrite dans le gabarit serait la meme pour tous les paquets, donc
  **fausse par construction sur le point 3**. Elle est invariante a l'interieur d'un paquet : c'est
  le patron de la consigne (`ADR-0052`), jamais celui d'un champ d'item recopie a l'identique sur
  vingt items.
- **Les deux textes de l'adulte sont contigus dans le document assemble**, sans qu'aucun element
  destine a l'enfant ne s'intercale - point 1. **Verifiable sur le document assemble** (`ADR-0020`)
  par la position relative des deux elements, pas par une capture.
- **Le bloc destine a l'adulte porte un marqueur d'etat qui dit son destinataire**, sur le patron
  d'`ADR-0022` : c'est ce qui rend le point 2 mesurable **en tant que declaration**. Le jugement
  reste a `nadia-prof`, qui l'ecrit elle-meme.
- **Sur l'etat de revelation, le bloc de l'adulte est present avec sa phrase d'interdit**, faute de
  quoi le point 1 est faux sur l'etat qu'elle nomme comme non audite. Meme fragment, aucune branche
  de gabarit neuve.

**Ce que je ne decide pas** : ou ce bloc se place, sa taille, comment on evite que l'enfant le lise.
Elle route ce dernier point comme un symptome dont *"la cause est technique et n'est pas de moi"* ;
`ines-design` propose, elle juge, et **rien ici ne prejuge de la solution**.

### 2.5 Refuse d'avance, du cote technique aussi

**Aucun dispositif qui verifierait ce que l'adulte a dit** - ni ecoute, ni enregistrement, ni
captation. `nadia-prof` l'a refuse d'avance ; **je le refuse aussi comme option d'architecture, pour
que la question ne se repose pas sous un autre nom.** Corollaire opposable : aucune capacite
d'entree audio, aucune dependance de reconnaissance vocale, aucune permission de micro n'entre dans
ce produit par cette famille.

**Et je nomme les conditions sans garde plutot que de les enumerer parmi mes clauses** : `VOI-3`,
`VOI-5`, `VOI-8`, et **la seconde moitie de `VOI-13`** - rien n'empeche l'enfant de lire par-dessus
l'epaule. Trois et demie, pas trois.

---

## 3. La dette : ce qu'elle coutait, ce qu'elle coute, ce qu'elle laisse ouvert

### 3.1 Le fait, remesure par moi

**Je ne l'ai pas recopie** (`squad/README.md`, section 4, amendement s17). Arbre `s26-theo` a
`153df5a`, `bash outils/mvn-jdk21.sh -B -o clean test` sur la **racine**, JDK 21.0.10 rapporte par
la machine virtuelle des tests :

| Etat | domain | application | adapter-web | adapter-content | bootstrap | Total | Echecs |
|---|---|---|---|---|---|---|---|
| Reference | 80 | 13 | 364 | 410 | 145 | **1 012** | **1** |
| **`M7` dans `fragments/manche.html`** | 80 | 13 | 364 | 410 | 145 | **1 012** | **1** |

*L'unique echec est le meme dans les deux colonnes et anterieur a la mutation :
`ToutesLesReferencesDeDecisionResolventTest` (`ADR-0066`), `CLAUDE.md` a `153df5a` citant `DEC-0068`
qui ne vivait pas encore sur cette branche.* **Ecart imputable a `M7` : zero, sur les cinq
modules.** Le chiffre de `yanis-qa` est confirme sur un autre arbre, a une autre date, par un autre
instrument ; mon total est plus bas de six unites parce que le reacteur a change entre les deux -
deux arbres, pas un desaccord.

### 3.2 Le diagnostic, et il corrige une lecture naturelle

`sessionStorage` et `score` sont **deux des treize mots** d'`INTERDITS_JS`. **La liste n'a pas
failli : son perimetre ne la porte pas jusque-la.** Trois des quatre clauses d'`ADR-0008` comptent
des octets, des lignes et des sous-chaines dans `static/**.js` ; un gabarit n'y entrait que par la
quatrieme, qui cherche quatre marqueurs de correction.

### 3.3 La regle, executable par quoi, mordant sur quoi

> **Tout JavaScript servi a l'enfant vit dans un fichier sous `static/`. Un gabarit n'en porte que
> par reference.** `ADR-0074`.

**Le remede n'est pas d'etendre la liste de vocabulaire**, et le motif se mesure : `var n = 0;
b.addEventListener('click', function () { n++; });` tient un etat de jeu sans ecrire **un seul** des
treize mots. Ce qui est ferme est le **porteur**, pas le vocabulaire - donc rien qui puisse pourrir.

**Executable par** : `FrontieresDuJavaScriptTest`, dans `adapter-web`, perimetre derive du balayage
des gabarits, document analyse par jsoup et jamais par expression reguliere. Trois porteurs derives
de ce que le navigateur execute ; le seul critere de nom est une **propriete** (`on` + lettres, ou
`hx-on`), jamais une enumeration de gestionnaires.

**La moitie `hx-on` est mesuree sur la bibliotheque telle qu'elle est vendorisee, pas imaginee** :
`grep -o 'hx-on[a-z:*-]*' .../vendor/htmx.min.js | sort -u` rend `hx-on`, `hx-on-` et `hx-on:` sur
htmx 2.0.10 du depot. C'est la lecon de s05 appliquee a moi-meme.

**Mordant sur quoi - et la morsure qui compte est faite sur l'arbre de production, pas sur une
copie** : `M7` pose dans le gabarit servi, puis `bash outils/mvn-jdk21.sh -B -o -pl
adapters/adapter-web -am test -Dtest=FrontieresDuJavaScriptTest` : **9 tests, 1 echec**, message
*"`fragments/manche.html` : un element `<script>` porte du JavaScript en ligne"*. Mutant retire,
meme commande : **9 tests, 0 echec**. *Perimetre : `adapter-web` et ses amonts, `-am` porte.*

*La premiere tentative sans `-am` a echoue a la resolution de `recreo-application` depuis le depot
local - la demonstration, sur mon propre lot, de ce que `CLAUDE.md` decrit.*

Trois autres morsures mutent une copie des gabarits reels : le bloc en ligne, les trois ecritures
d'attribut gestionnaire (`onclick`, `th:onclick`, `hx-on:click`), l'URL `javascript:`.

### 3.4 Ce que la fermeture laisse ouvert, et je le nomme

- **Un porteur injecte a l'execution** par `th:utext`. Mesure du 2026-09-01,
  `grep -rn "utext\|th:inline" adapters/adapter-web/src/main/resources/templates/` : **aucune
  occurrence**. Le canal n'existe pas aujourd'hui ; il n'est pas garde.
- **Ce qu'un fichier `.js` maison fait sous ses 4 096 octets.** Inchange, et cette regle ne pretend
  pas le fermer.
- **Le plafond de `DETTE_DE_BALAYAGE` ne bouge pas**, bien que mon lot me le permettrait : la dette
  y nomme un jeu fautif **commite**, et **un plafond qu'on baisse en changeant ce qu'il compte n'est
  pas un plafond baisse**.

---

## 4. `P(bonne / presente)` - encodable, et voici son prix avec sa methode

`nadia-prof` route un fait verifie test par test : **aucun garde-fou d'`adapter-content` ne mesure
la probabilite qu'une proposition soit la bonne sachant qu'elle est presente.**

**Je l'ai verifie par ma propre methode plutot que de la croire** : les 37 classes de test
d'`adapter-content` qui mentionnent `distracteur`, dont **33** appellent `distracteurs()` et
`bonneReponse()` dans le meme fichier, lues une par une sur ce qu'elles calculent. Les deux plus
proches sont `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, qui mesure la **position** de
la bonne reponse, et `LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest` (`ADR-0041`), qui derive
une **valeur d'ordre**. **Ni l'une ni l'autre ne compte, par valeur de proposition, ses apparitions
comme reponse contre ses apparitions comme distracteur.** Son constat est exact.

**Encodable : oui. Et la mesure prealable change sa forme.** Balayage des paquets servis, comptage
par valeur de proposition, sur les 33 paquets a propositions ecrites en ligne (2 paquets
combinatoires en sont hors, ils ne listent aucune proposition dans le YAML). *Methode : lecture
ligne a ligne des cles `bonneReponse` et `distracteurs`, sur le patron du comptage textuel deja
employe par le depot ; arbre `s26-theo` fusionne a `7934d37` ; support minimal de 2 apparitions.*

| Forme du garde-fou | Ce qu'il rend aujourd'hui |
|---|---|
| Seuil 100 %, support >= 2, **sur tous les paquets** | **8 paquets sur 33 rouges**, tous deja `VALIDE` |
| Seuil 70 %, support >= 2, **sur tous les paquets** | **9 paquets sur 33 rouges** |
| **Restreint aux paquets a jeu de propositions ferme** (aucune proposition vue une seule fois) | **9 paquets dans la population, 1 signale** : `cm1-mots-de-liaison`, *alors* a 2 / 2 |

**Le fait de conception que cette mesure donne, et qu'aucune lecture ne donnait** : dans la plupart
des paquets, **l'immense majorite des propositions n'apparait qu'une seule fois** -
`cm1-comprehension` 60 sur 60, `cp-pluriel` 63 sur 63. Sur ces paquets-la, `P(bonne / presente)` ne
mesure rien : chaque proposition est a 0 % ou a 100 % par construction. **Un garde-fou non restreint
serait rouge sur huit paquets valides le jour de sa naissance, et sa population utile serait vide la
ou il compte.** C'est la forme exacte de `CPL-3`, quatrieme instance, celle que `yanis-qa` a trouvee
sur le garde-fou de rang.

**La forme qui tient** : population restreinte aux paquets a jeu de propositions ferme - un critere
**calcule**, jamais une liste de noms -, avec une **clause de non-vacuite par paquet** (`ADR-0051`)
qui declare tout paquet ecarte. Sur les 9 paquets de cette population, l'instrument est dense et
significatif : `cp-le-bon-son` a 3 propositions, `cp-syllabes` a 3 - exactement les paquets sur
lesquels `nadia-prof` a mesure ses 33,3 % et ses 100 %.

**Le chiffrage.** Une classe de garde-fou, de l'ordre de **150 a 200 lignes** avec ses jeux fautifs,
sur le patron de `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` qui porte deja tout ce
qu'il faut : balayage derive, declaration de population, morsures. **Un lot de developpeur d'une
demi-journee**, pas un chantier.

**Ce qui n'est pas de moi et qui bloque son ecriture** : **le seuil et le support minimal sont un
jugement pedagogique**. Un seuil est une reponse a *"a partir de quand une signature devient une
strategie gagnante"*, et c'est `nadia-prof`. Je donne les trois colonnes ci-dessus pour qu'elle
choisisse sur des nombres ; **je ne choisis pas a sa place, et je n'ecris pas ce garde-fou dans ce
sprint.**

---

## 5. Verdict de faisabilite du perimetre complet

### 5.1 Ce qui est deja fait, et sort du compte

**La dette est fermee.** `ADR-0074` est accepte, sa regle est ecrite, verte et mordue sur l'arbre de
production, dans ce lot-ci. **Elle ne consomme plus rien du sprint** : le rang 3 de `DEC-0067`, a son
troisieme sprint de routage, est paye au planning plutot que reporte une quatrieme fois.

*Mesure de cloture, arbre `s26-theo` fusionne avec `round-two` a `7934d37`,
`bash outils/mvn-jdk21.sh -B -o clean verify` sur la racine : **BUILD SUCCESS**, **1 022 tests, 0
echec, 0 erreur** - 1 016 en surefire (domain 80, application 13, adapter-web **368**,
adapter-content 410, bootstrap 145) et 6 en failsafe. Methode : somme de l'attribut `tests` de
chaque rapport XML de surefire et de failsafe apres `clean`, les deux moteurs comptes, jamais les
lignes `Tests run:` des rapports `.txt`. **Les quatre tests de plus en `adapter-web` sont les
miens.** L'echec `ADR-0066` mesure a `153df5a` a disparu : il tenait a `DEC-0068`, que `sofia-sm` a
fait entrer, et mon propre lot en avait ouvert un second en citant `ADR-0074` avant que le fichier
n'existe - ce garde-fou a donc mordu deux fois ce sprint, dont une fois sur moi.*

### 5.2 Ce qui reste, et ma reponse franche

**Le perimetre restant tient, et il ne tient que parce que la dette en est sortie.** Il ne tient pas
avec de la marge.

| Ce qui reste a faire | Qui | Ce que j'en dis |
|---|---|---|
| Le champ sur les deux formes d'item, ses deux fabriques, les **quatre clauses d'`ADR-0075`** | un developpeur | **Faisable en l'etat.** Mesure : 0 arret du compilateur, 0 rouge hors `adapter-content`. C'est un lot d'une journee, clauses comprises |
| Le rendu : deux textes, la phrase d'interdit au catalogue, la contiguite, le marqueur de destinataire, l'etat de revelation | un developpeur, sur proposition d'`ines-design` | **Faisable moyennant `ADR-0075` section 7**, qui est ecrite. Aucune branche de gabarit neuve, aucune mecanique |
| **Cinq paquets de contenu** | les developpeurs, puis `nadia-prof` | **C'est ici que le sprint se joue, et ce n'est pas technique.** Sa borne est a **5, sans marge**, de son propre aveu - *"il n'y a aucune marge"* |
| `P(bonne / presente)` | `nadia-prof` d'abord, un developpeur ensuite | **Faisable, une demi-journee, et je recommande de ne pas le prendre dans s26.** Section 5.3 |

### 5.3 Ce que je couperais, et pourquoi

**Je ne couperais aucune des quatre lignes.** Elles sont le perimetre arbitre, elles sont
techniquement homogenes, et `nadia-prof` a etabli qu'une moitie de la ligne des graphemes **depense
un paquet pour zero ligne**. Couper la-dedans coute plus que cela ne rend.

**Je couperais `P(bonne / presente)` de s26, et je le dis en sachant que c'est moi qui l'ai
chiffre.** Trois motifs, dans l'ordre de leur poids :

1. **Son seuil n'est pas rendu, et il n'est pas de moi.** Ecrire le garde-fou avant que
   `nadia-prof` n'ait choisi un seuil sur les trois colonnes de la section 4, ce serait choisir a sa
   place - et un seuil choisi par son auteur est exactement ce qui rend une regle decorative.
2. **Il rougirait sur des paquets valides.** Huit sur trente-trois dans sa forme naive. La forme qui
   tient demande la restriction mesuree ci-dessus, donc une conception, donc plus qu'une
   demi-journee si on la decouvre en la codant.
3. **Il ne garde aucun des cinq paquets de s26 tel qu'ils sont decrits.** Trois d'entre eux ont un
   jeu de propositions ferme et minuscule - trois positions, oui / non, trois digraphes - ou la
   condition de distribution que `nadia-prof` ecrit deja (`VOI-4`, `VOI-6`, et le tiers d'items par
   digraphe) est **plus forte** que `P(bonne / presente)`. **Le garde-fou paierait sur le sprint
   d'apres, pas sur celui-ci.**

**Ce que je ne couperais pas et qui pourrait tenter** : la clause 4 d'`ADR-0075`, la traversee. Elle
a l'air redondante avec `ADR-0070` et la section 2.3 montre, mesure a l'appui, qu'elle ne l'est pas.
**C'est la seule chose qui empeche cinq paquets de contenu, relus par `nadia-prof`, de n'arriver
sur aucun ecran sans qu'un test ne rougisse.**

### 5.4 Le risque que je nomme, et qui n'est pas technique

**La borne de `nadia-prof` tombe au plafond, sans marge, et elle l'ecrit.** Cinq paquets, 20 items au
plus par paquet, 100 items neufs, 115 avec `cp-le-bon-son` si son fichier est touche - et *"un
sixieme paquet, je decline par ecrit"*.

**Ce que cela implique techniquement, et c'est de mon perimetre** : `cp-le-bon-son.yaml` est le
paquet servi de la clause 3 de sa barre, et **son fichier ne doit pas etre touche par le lot des
graphemes**. Sa condition de verification est ecrite comme un `git diff`, donc mecanique. **Les deux
paquets de graphemes sont des fichiers neufs, et rien dans `ADR-0075` n'oblige a rouvrir
`cp-le-bon-son`.** Si un lot le modifie pour une raison de forme - une typographie, un renommage -
**il coute 15 items sur les 115, pour rien.** Je le signale parce que c'est le genre de geste qu'on
fait sans y penser.

---

## 6. Ce que cet avis ne fait pas

- Il **n'ecrit aucune story**, n'en priorise aucune et ne choisit aucune ligne : c'est `camille-po`.
- Il **ne prononce aucun Done**, ni technique ni pedagogique, et n'en anticipe aucun.
- Il **ne revise la borne de personne** et ne conteste aucun chiffre de `nadia-prof` : je les reprends
  avec leur source.
- Il **ne decide aucune apparence** : la section 2.4 dit ce que la structure doit rendre possible,
  jamais a quoi cela ressemble.
- Il **n'ecrit pas le garde-fou de `P(bonne / presente)`** : il le chiffre et dit ce qui manque avant
  qu'on puisse l'ecrire.
