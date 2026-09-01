# ADR-0075 - La famille a voix est un champ d'item, et l'identite d'un tour cesse d'etre ce que l'ecran montre

- **Statut** : Accepte. **Sa regle executable est ecrite ci-dessous et mesuree, rouge puis verte, sur
  une sonde de quatre paquets construits d'apres les quatre lignes que `nadia-prof` a ouvertes.**
  Elle est **due dans le commit qui livre le champ**, et la section *Regle executable* dit pourquoi
  la committer avant serait une clause verte sur une population vide (`ADR-0051`), qui verifie qui la
  pose et quand.
- **Sprint** : s26 (planning). **Date** : 2026-09-01. **Auteur** : `theo-archi`.
- **Arbre mesure** : worktree `s26-theo`, parti de `round-two` a `153df5a`, fusionne avec
  `round-two` a `7934d37`. **JDK 21.0.10**, celui que la machine virtuelle des tests rapporte
  d'elle-meme (`ADR-0071`). Enveloppe : `bash outils/mvn-jdk21.sh`.
- **Repond a la question routee par `camille-po`** (`DEC-0068`, section A.5 et section 7) : *"la
  famille a voix est-elle un champ d'item ou une mecanique ?"*, avec `ADR-0073` pour precedent.
- **Amende `ADR-0032`** (l'identite d'ecran d'un item) et **`ADR-0050` clause 2** (le classement des
  composantes). **Prolonge** : `ADR-0004`, `ADR-0006`, `ADR-0013`, `ADR-0051`, `ADR-0058`,
  `ADR-0070`, `ADR-0073`.
- **Ne decide aucune pedagogie** (`nadia-prof`), **aucun perimetre de sprint** (`camille-po`),
  **aucune apparence** (`ines-design`). Ce qu'un adulte doit dire, ce qu'une phrase d'ecran porte et
  quelles lignes s26 prend ne sont pas ici.

---

## Contexte

`DEC-0068`, sur arbitrage de l'utilisatrice du 2026-09-01, acte que la voix d'un exercice est celle
d'un adulte present, et que **le seul livrable du produit est la distinction entre ce que l'adulte
prononce et ce que l'enfant lit**. `nadia-prof` ouvre **quatre lignes sur cinq**
(`ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md`, section 4), pose douze conditions `VOI-1` a
`VOI-12`, puis, dans son addendum du meme jour, en corrige une contre elle-meme (`VOI-13` remplace
`VOI-2`) et etablit que la ligne des graphemes demande **deux paquets**, donc **cinq paquets** au
total.

`camille-po` route la question qui decide le calibrage : **champ d'item, ou mecanique ?** Elle a
raison de la router plutot que de la presumer - `ADR-0073` a etabli a s25 qu'une famille d'items
neuve n'est **ni une forme d'item neuve, ni une mecanique neuve**, et *"ce precedent n'a jamais ete
applique a ce cas"*.

`nadia-prof` a chiffre les deux branches **avant de connaitre ma reponse**, ce qui rend cet `ADR`
directement convertible en perimetre (son addendum, section 12.4) : champ d'item, **5 paquets, 4
lignes, 85,4 %** ; mecanique, **4 paquets, 3 lignes, 82,9 %**.

### Le piege que ce depot a sanctionne sept fois en deux sprints

`yanis-qa` le nomme : *"le defaut nait dans l'intervalle entre l'ecriture d'une clause et sa mesure
sur l'objet motivant. Juste en intention, faux en extension."*

**Appliquer `ADR-0073` par ressemblance aurait ete exactement cela.** Le raisonnement tient en une
phrase - *"l'enfant choisit toujours parmi des propositions en texte, donc c'est une composante
optionnelle de plus, donc c'est gratuit"* - et sa premiere moitie est vraie. **Sa seconde moitie est
fausse, et aucune lecture ne le dit : il a fallu construire les paquets et les charger.**

---

## La mesure - une sonde de quatre paquets, batie sur les quatre lignes ouvertes

**Methode.** Sonde non commitee, sur le patron des sondes A et B d'`ADR-0073`. Une composante
`Optional<String> texteAVoixHaute` posee sur **les deux** formes d'item (`Question` et
`ReponseAEcrire`), lue par **les deux** fabriques, et **quatre paquets servis depuis
`contenu/`** - un par ligne ouverte, ecrits d'apres les conditions de `nadia-prof` et non d'apres
l'idee que je m'en fais : le mot n'est jamais dans ce que l'enfant lit (`VOI-13`), le texte de
l'adulte porte le stimulus et jamais une proposition (`VOI-1`, `VOI-10`).

`bash outils/mvn-jdk21.sh -B -o clean test` sur la **racine** - reacteur complet, la question du
`-am` ne se pose pas. Reference sans sonde, meme commande, meme arbre : **domain 80, application 13,
adapter-web 364, adapter-content 410, bootstrap 145**, soit **1 012 tests**.

| | Arrets du compilateur | Classes rouges | Echecs |
|---|---|---|---|
| Reference | 0 | 0 | 0 (hors l'echec `ADR-0066` anterieur, repare depuis a `7934d37`) |
| **Sonde V** - la composante et les quatre paquets | **0** | **5**, toutes dans `adapter-content` | **8** |

**`adapter-web` reste a 364 tests et 0 echec, `domain` a 80 et 0, `bootstrap` a 145 et son seul
echec anterieur.** Aucune mecanique n'est touchee, et c'est deja la moitie de la reponse.

### Ce que la sonde a trouve, et que je n'aurais pas trouve en lisant

**`ADR-0032` rougit sur trois des quatre paquets. Six collisions.**

| Paquet de sonde | Ligne de `nadia-prof` | Collisions d'ecran |
|---|---|---|
| `sonde-voix-localiser-syllabe` | localiser une syllabe | **2** |
| `sonde-voix-identifier-son` | identifier un son | **2** |
| `sonde-voix-ecrire-mot` | ecrire un mot en autonomie | **2** |
| `sonde-voix-graphemes` | associer graphemes et phonemes | **0** |

Message rendu, mot pour mot : *"paquet `sonde-voix-ecrire-mot`, item `item-0` : il presente le meme
ecran que l'item `item-1` - meme enonce `Ecris le mot.` et meme jeu de propositions."*

**La cause est structurelle et elle est le sujet meme de cette famille.** `ADR-0032` fixe l'identite
d'un item au couple **enonce + jeu de propositions** - *ce que l'ecran montre*. Sur un item a voix,
`VOI-13` exige que le mot travaille **ne soit pas dans ce que l'enfant lit**. La matiere qui
distingue deux tours quitte donc l'ecran de l'enfant : l'enonce devient une consigne invariante, les
propositions sont stables (les trois positions, oui / non, une forme de saisie), et **tous les items
du paquet deviennent le meme ecran**. Le paquet des graphemes echappe seul, parce que ses
propositions changent d'un item a l'autre.

**Le contraste avec `ADR-0073` est exactement la reponse a `camille-po`.** L'illustration entrait
dans l'identite d'ecran **parce que l'enfant la voit**. Le texte de l'adulte, l'enfant ne le voit
pas - **il l'entend**. La propriete qu'`ADR-0032` protege n'a jamais ete *"deux items ne montrent pas
la meme image"* : elle est *"une partie ne pose jamais deux fois la meme manche"*, verifiee a la main
en jouant par `nadia-prof` sur vingt-quatre parties. **C'est le critere qui etait plus etroit que la
propriete, et la famille a voix est le premier materiau qui l'expose.**

**Deuxieme mesure, l'autre moitie.** La composante classee **dans** l'identite, meme sonde, meme
commande : `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` passe a **16 tests, 0 echec**. Les
six collisions disparaissent. Les six echecs qui restent dans `adapter-content` sont le **cout
ordinaire d'ajouter des paquets** - deux clauses de population declaree (`ADR-0051`) et le cliquet
typographique de `TYP-1` (`ADR-0067`), qui a d'ailleurs correctement mordu mes espaces avant les
points d'interrogation. **Aucun d'eux n'est propre a la voix.**

### Une troisieme mesure, contre une supposition confortable : `ADR-0070` ne protege pas ce champ

`ADR-0070` exige qu'aucune cle de schema d'un paquet servi ne soit ignoree de tout le depot. On
pourrait croire qu'elle couvre une cle neuve. **Elle la voit, et elle ne la protege pas comme on
l'espere.** Trois etats mesures sur la sonde, le 2026-09-01,
`bash outils/mvn-jdk21.sh -B -o -pl bootstrap -am test -Dtest=AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest`
(*perimetre : `bootstrap` et ses amonts, `-am` porte*) :

| Etat | Verdict |
|---|---|
| Les deux fabriques nomment `"texteAVoixHaute"` | **vert** |
| Aucune fabrique ne le nomme, mais le classement d'`ADR-0032` le nomme | **vert** |
| Personne ne le nomme | **rouge** : *"ces cles sont ecrites dans un paquet servi et ne sont nommees par aucune source du depot"* |

**C'est exactement ce qu'`ADR-0070` decide et ecrit** : la propriete faible - *personne ne nomme
cette cle* - et non *cette cle est lue au bon endroit*. **Consequence operationnelle a ne pas se
cacher : sur ce champ-ci, `ADR-0070` sera satisfaite par le classement d'identite d'ecran seul.**
Elle n'attrapera pas un champ ecrit dans les paquets et jamais rendu a l'ecran. Ce qui l'attrape est
un test de traversee, sur le patron de `LeTexteSupportDUnItemReelAtteintLEcranDeMancheTest` - la
clause 4 ci-dessous.

---

## Decision

### 1. La famille a voix est un **champ d'item**. Ce n'est ni une forme d'item neuve, ni une mecanique neuve

**Le critere n'est pas invente ici, il vient d'`ADR-0058`** : une forme d'item de plus se justifie
quand **la facon dont l'enfant repond** change. `ADR-0073` l'a applique au visuel ; je l'applique
ici, et je le mesure au lieu de le deduire.

| Ligne ouverte par `nadia-prof` | Comment l'enfant repond | Mecanique |
|---|---|---|
| Localiser une syllabe | il choisit parmi trois propositions en texte | bandit manchot, **inchange** |
| Identifier un son | il choisit parmi des propositions en texte | bandit manchot, **inchange** |
| Associer graphemes et phonemes (deux paquets) | il choisit parmi trois propositions en texte | bandit manchot, **inchange** |
| Ecrire un mot en autonomie | il ecrit | saisie, **inchangee** |

**Ce qui change est ce que l'enfant entend avant de repondre**, exactement comme le palier 1 visuel
changeait ce qu'il regarde avant de choisir. **Mesure a l'appui** : la sonde produit **0 arret du
compilateur** - la scellure d'`Item` (`ADR-0004`) n'arrete personne parce qu'aucune forme n'est
ajoutee - et **`domain` reste a 80 tests, 0 echec, `adapter-web` a 364, 0 echec**. Une mecanique
neuve qui redistribuerait des propositions, tiendrait un score et rendrait un verdict serait une
**copie** de `BanditManchot` : la dette n. 4 de `CLAUDE.md` par un autre chemin.

**L'objection d'`ADR-0058` ne s'applique pas** : une composante `Optional` n'oblige aucun paquet a
porter une cle qui ne le concerne pas - c'est ce qu'`ADR-0024` a tranche pour l'explication et ce que
les 27 paquets servis demontrent.

**Le prix, en une ligne, avec sa source** : la borne de `nadia-prof` est **5 paquets, 4 lignes,
plafond 35 / 41 = 85,4 %** (son addendum, section 12.4). Sa branche *"mecanique"* - 4 paquets, 3
lignes, 82,9 % - ne se realise pas.

### 2. Le motif de sa branche *"mecanique"* est reel, et il ne fait pas une mecanique

`nadia-prof` price un paquet de plus pour un chemin qu'elle n'a jamais audite : *"l'ecran de
revelation affiche la bonne reponse, et un adulte qui le lit la dit a voix haute. Ce n'est pas
`VOI-1`, qui gouverne le stimulus : c'est le moment de la correction."*

**Le fait est juste, et j'y reponds par une mesure plutot que par un argument.** Sur `round-two` a
`7934d37`, `fragments/retour.html` porte en tete : *"ce fragment ne rend plus qu'un seul etat - la
fin de partie"*. **La revelation du troisieme echec vit sur `fragments/manche.html`**, le meme ecran
que l'item - `manche.html` nomme lui-meme *"celui qui porte la reponse revelee au troisieme
echec"*.

Trois consequences, et aucune n'appelle une mecanique :

- **Le moment de la correction n'est pas un ecran de plus a concevoir** : c'est le meme fragment,
  dans un autre etat.
- **La reponse revelee est une proposition.** `VOI-10` ferme le champ de l'adulte a toute
  proposition, **par construction et non par vigilance** : si le champ 1 ne peut pas contenir de
  proposition, l'adulte qui lit son champ ne peut pas la dire. La fuite que `nadia-prof` decrit
  suppose qu'il lise **l'ecran de l'enfant** faute d'avoir un texte a lui - c'est le point 1 de sa
  clause `VOI-12`, *"chaque ecran ou un texte destine a l'adulte est affiche"*, et c'est une
  exigence de **rendu et de contenu**, pas une mecanique.
- **`VOI-13` est deja ecrite comme portant sur tous les ecrans du tour**, et c'est calculable : la
  clause 3 ci-dessous le fait.

**Ce que je ne fais pas** : je ne dis pas que son audit est inutile. `VOI-12` point 1 la lui impose
sur cet etat comme sur les autres, et **elle a ecrit elle-meme que ce cout est deja dans son
assiette** dans la branche *"champ d'item"*. Je dis que ce chemin ne remplit pas le critere
d'`ADR-0058` et n'ajoute pas de mecanique a auditer.

### 3. L'identite d'un tour est ce qui le distingue **pour l'enfant** - vue ou entendue

**C'est l'amendement a `ADR-0032`, et c'est la decision structurante de cet `ADR`.**

> **Deux items d'un meme paquet fini ne presentent jamais le meme tour a l'enfant.** L'identite
> retenue est ce qui distingue deux tours **de son point de vue** : ce que l'ecran lui montre, **et
> ce qu'un adulte lui dit**. `texteAVoixHaute` entre dans cette identite.

**Ce que le critere d'origine disait, et pourquoi il n'a pas failli** : `ADR-0032` ecrit *"le meme
ecran"* parce que, le jour ou il a ete ecrit, tout ce qui distinguait deux tours etait a l'ecran.
La propriete qu'il protege a toujours ete la variete d'une partie a l'autre - *"le tirage reste sans
remise sur les identifiants et redevient avec remise sur ce que l'enfant lit"*. **Ce n'est pas le
critere qui derive, c'est le monde qui s'est elargi** : un tour a desormais une part que l'enfant
entend. **C'est la meme famille que l'amendement de s24, ou le classement reflechissait sur
`Question` seule et tenait sa promesse pour une population plus etroite que la phrase qui la
decrivait.**

**Ce que cet amendement coute s'il n'est pas fait** : trois paquets sur cinq rouges au premier
commit, avec un message qui nomme une cause fausse - *"faire varier l'item"* - alors que l'item
varie deja, dans le seul endroit ou la ligne pedagogique permet qu'il varie. **Un auteur suivrait le
message et remettrait le mot a l'ecran, ce qui defait exactement la ligne.**

### 4. `VOI-10` et `VOI-13` sont calculables, et ce sont deux calculs distincts

`nadia-prof` demande que `VOI-10` soit *"la condition a ecrire en test avant toutes les autres"*,
parce qu'elle referme le canal de fuite **par construction** au lieu d'une liste d'interdits.

> **`VOI-10`, clause de fermeture** : sur un item a voix, `texteAVoixHaute` ne contient aucune
> proposition de son item - ni la bonne reponse, ni un distracteur, ni la forme de reference d'un
> item de saisie.
>
> **`VOI-13`, clause de non-visibilite, et elle porte sur le tour entier** : la matiere que l'adulte
> prononce n'apparait dans aucun texte que l'enfant lit sur **aucun ecran du tour** - consigne du
> catalogue, enonce, propositions, indice, explication, texte-support, texte alternatif
> d'illustration.

**Elles ne sont pas symetriques et il ne faut pas les fusionner.** `VOI-10` compare un champ aux
propositions **du meme item** ; `VOI-13` compare un champ a **tout ce que l'enfant lit**, y compris
la consigne, qui vit dans le catalogue et non dans le paquet (`ADR-0052`). Fusionner les deux aurait
donne une clause qui lit deux populations differentes sous un seul nom.

**Ce que la comparaison compare, et ce point n'est pas cosmetique** : une comparaison de chaines nue
declarerait *chapeau* absent d'un enonce qui porte *Chapeau*. La comparaison se fait donc sur une
forme repliee - casse et signes diacritiques -, sur le patron de `NormalisationDeSaisie` qui existe
deja dans le domaine. **Elle reste une comparaison de sous-chaines, et ce qu'elle ne voit pas est
nomme** : un mot ecrit en syllabes separees, ou une periphrase, lui echappent. Elle attrape la faute
reelle - le mot recopie dans l'enonce - et pas la faute imaginative.

### 5. Le champ vit sur les **deux** formes d'item, et le motif est mesure

**C'est la clause que la lecture seule aurait manquee**, et c'est le piege de `yanis-qa` en acte.
Trois lignes sur quatre sont des items de choix (`Question`) ; **la quatrieme, ecrire un mot en
autonomie, est un item de saisie** (`ReponseAEcrire`, `ADR-0058`). Un champ pose sur `Question`
seule aurait ete juste pour quatre paquets sur cinq et **faux pour le cinquieme**, celui-la meme
dont la sonde montre qu'il collisionne le plus mecaniquement - ses items n'ont aucune proposition
pour les distinguer.

**Ce que cela oblige, et je le dis parce que c'est un cout reel** : les deux fabriques le lisent, et
le classement d'identite d'ecran couvre les deux branches du `switch` scelle.

### 6. Ce que cet `ADR` refuse d'avance, et il le reprend d'elle sans le discuter

**Aucun dispositif qui verifierait ce que l'adulte a dit.** Ni ecoute, ni enregistrement, ni
captation, ni controle, quel que soit le gain de couverture. `nadia-prof` l'a refuse d'avance
(section 7.4) et **je le refuse aussi du cote technique, pour que la question ne se repose pas comme
une question d'architecture** : `VOI-3`, `VOI-5`, `VOI-8` et la seconde moitie de `VOI-13` sont des
conditions **sans garde**, et elles le restent. Le seul paiement que le produit puisse faire est de
les dire a qui peut les tenir - c'est `VOI-12`.

**Corollaire de conception, pour qu'il soit opposable** : aucune capacite d'entree audio, aucune
dependance de reconnaissance vocale, aucune permission de micro n'entre dans ce produit par cette
famille. Elle n'en a pas besoin et elle ne sert pas de motif.

### 7. Ce que le gabarit et le modele de vue doivent porter pour que `VOI-12` soit possible

`VOI-12` est l'exigence **bloquante** de `nadia-prof` : *"sans elle je refuserai les paquets a
voix"*. `ines-design` traite la forme ; ce qui suit est ce que la structure doit rendre possible, et
rien de plus.

- **Deux textes distincts dans le modele de vue, jamais un seul enrichi.** Le texte de l'adulte est
  un attribut de modele a lui, comme `texteSupport` et `illustration` avant lui. **Un texte unique
  ou le gabarit devinerait la part destinee a l'adulte porterait la distinction dans le gabarit**,
  ce que `DEC-0068` section 4 s'interdit explicitement - *"un contenu qui declare la distinction,
  plutot qu'un rendu qui la devine"*.
- **La phrase d'interdit est du contenu, pas un libelle de gabarit.** `nadia-prof` : *"deux paquets
  dont les interdits different ne portent pas la meme phrase"* (`VOI-12` point 3). Une phrase ecrite
  dans le gabarit serait **la meme pour tous les paquets**, donc fausse par construction sur le point
  3. Elle est **invariante a l'interieur d'un paquet** et varie d'un paquet a l'autre : c'est
  exactement le patron de la consigne, qui vit dans l'entree de catalogue (`ADR-0052`), et non celui
  d'un champ d'item repete a l'identique sur vingt items.
- **Les deux textes de l'adulte sont contigus dans le document assemble**, sans qu'aucun element
  destine a l'enfant ne s'intercale : c'est le point 1 de `VOI-12`, *"presente en meme temps,
  immediatement contigue, sans qu'aucune interaction ne soit necessaire"*. **Verifiable sur le
  document assemble** (`ADR-0020`), par la position relative des deux elements - pas par une capture.
- **Le bloc destine a l'adulte porte un marqueur d'etat qui dit son destinataire**, sur le patron
  d'`ADR-0022` : c'est ce qui rend le point 2 mesurable **en tant que declaration**. `nadia-prof`
  garde le jugement - *"qu'un tiers dise sans hesiter a qui s'adresse un texte"* n'est calculable par
  personne, et elle l'ecrit elle-meme.
- **Sur l'etat de revelation, le bloc de l'adulte est present avec sa phrase d'interdit**, faute de
  quoi le point 1 est faux sur l'etat que `nadia-prof` nomme comme non audite. Meme fragment, meme
  bloc, aucune branche de gabarit neuve.

**Ce que je ne decide pas** : ou ce bloc se place, sa taille, sa couleur, comment on evite que
l'enfant le lise. `nadia-prof` route ce dernier point comme un symptome dont *"la cause est technique
et n'est pas de moi"* ; c'est `ines-design` qui propose, elle qui juge, et **rien dans cet `ADR` ne
prejuge de la solution retenue** - un placement, un geste, une orientation d'appareil, autre chose.

### 8. Nommage : `texteAVoixHaute`, et pas un nom qui parle de l'adulte

Le champ nomme **ce que le texte est** - un texte destine a etre prononce - jamais **qui le
prononce**. Motif : `ADR-0006` et la dette n. 2 de `CLAUDE.md` tiennent le contrat de contenu libre
de tout ce qui n'est pas la matiere. *L'adulte* est une premisse de produit qui vit dans `CLAUDE.md`
et dans `DEC-0046` ; elle peut changer de forme, le champ non. C'est le meme geste que `partDesignee`
(`ADR-0062`), qui ne nomme pas *le mot* et ne privilegie aucune matiere.

---

## Regle executable

Trois clauses, dans le module qui possede deja la population - `adapters/adapter-content` - plus une
quatrieme dans `bootstrap`, la ou vivent deja les traversees de champ jusqu'a l'ecran.

### Clause 1 - `texteAVoixHaute` entre dans l'identite d'un tour

Fichier : `adapters/adapter-content/src/test/java/com/recreo/adapter/content/AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest.java`.

```java
private record Ecran(String enonce, Set<String> propositions, Optional<String> partDesignee,
                      Optional<String> texteSupport, Optional<String> illustration,
                      Optional<String> texteAVoixHaute) {
}

private static Ecran ecranDe(Item item) {
    return switch (item) {
        case Question question -> new Ecran(question.enonce(), propositionsDe(question),
                question.partDesignee(), question.texteSupport(), question.illustration(),
                question.texteAVoixHaute());
        case ReponseAEcrire reponse -> new Ecran(reponse.enonce(), Set.of(), Optional.empty(),
                Optional.empty(), Optional.empty(), reponse.texteAVoixHaute());
    };
}

private static final Set<String> COMPOSANTES_DANS_L_IDENTITE_D_ECRAN =
        Set.of("enonce", "bonneReponse", "distracteurs", "partDesignee", "texteSupport",
                "illustration", "texteAVoixHaute");
```

**Mesure de morsure, faite** : sans la derniere ligne, la sonde rend **6 collisions sur 3 paquets de
4** ; avec elle, **16 tests, 0 echec**. *Perimetre : `adapters/adapter-content` et ses amonts, `-am`
porte pour la seconde mesure, reacteur complet pour la premiere.*

**Et le silence est deja refuse sans que j'aie rien a ecrire** : la clause
`chaqueComposanteDUnItemEstClasseeDansLIdentiteDEcranOuHorsDElle` (`ADR-0050` clause 2) est **rouge**
des que la composante existe et n'est pas classee - mesure sur la sonde, message *"expected ... but
was ..."* nommant `texteAVoixHaute`. **Ce garde-fou-la a fait son travail sur mon propre lot avant
que je n'aie ecrit une ligne de regle.**

### Clause 2 - `VOI-10` : le champ de l'adulte ne porte aucune proposition de son item

```java
@Test
void aucunTexteAVoixHauteNePorteUnePropositionDeSonItem() {
    // perimetre derive : les paquets viennent du balayage de contenu/, les items du chargeur reel
    for (Item item : itemsDesPaquetsServis()) {
        Optional<String> voix = texteAVoixHauteDe(item);
        if (voix.isEmpty()) {
            continue;
        }
        String replie = replier(voix.orElseThrow());
        assertThat(propositionsDe(item).stream().map(this::replier).filter(replie::contains))
                .as("VOI-10 (nadia-prof, 2026-09-01, section 5.1) : le texte que l'adulte prononce"
                        + " ne contient jamais une proposition ni la reponse. Si le champ ne peut"
                        + " pas contenir de proposition, il n'y a rien a interdire - une liste"
                        + " d'interdits serait une liste qu'on oublie de tenir a jour")
                .isEmpty();
    }
}
```

### Clause 3 - `VOI-13` : la matiere prononcee n'est dans aucun texte que l'enfant lit, sur aucun ecran du tour

Meme forme, populations differentes : la comparaison balaie **la consigne de l'entree de catalogue**
qui sert le paquet, puis, pour chaque item, l'enonce, les propositions, l'indice, l'explication, le
texte-support et le texte alternatif d'illustration. **La consigne vient du catalogue et non du
paquet** : une clause qui ne lirait que le paquet serait verte sur la fuite la plus facile a ecrire.

### Clause 4 - la traversee, parce qu'`ADR-0070` ne la garde pas

Fichier : `bootstrap`, sur le patron exact de
`LeTexteSupportDUnItemReelAtteintLEcranDeMancheTest`. **Motif mesure** : la section *Une troisieme
mesure* ci-dessus montre que `ADR-0070` est satisfaite par le seul classement d'identite d'ecran. Un
champ ecrit dans cinq paquets, relu par `nadia-prof`, et jamais rendu a l'ecran serait **vert
partout**. C'est le mode de defaillance qu'`ADR-0070` decrit - *"l'ecart muet"* - et il n'est ferme
ici que par une traversee qui part d'un paquet servi et arrive au document assemble.

### Pourquoi ces quatre clauses ne sont pas dans ce commit, qui les pose, et quand

**Elles seraient vertes et vides.** Il n'existe aujourd'hui **aucun** item portant
`texteAVoixHaute` : la population des clauses 2, 3 et 4 est l'ensemble vide, et `ADR-0051` etablit
qu'un garde-fou qui n'examine aucun de ses objets doit le declarer plutot que d'occuper la place
d'une preuve qu'il ne fournit pas. **Les committer maintenant serait ecrire quatre regles vertes qui
ne gardent rien**, dans le sprint meme ou j'ecris qu'une regle decorative n'est pas une regle.

**Elles sont dues dans le commit qui livre le champ** - le premier lot de developpement de s26,
celui qui pose `texteAVoixHaute` sur `Question` et `ReponseAEcrire` et le fait lire par les deux
fabriques. La clause 1 y est **obligatoire et non negociable** : sans elle, le lot est rouge, et le
message nomme une cause fausse.

**Qui verifie, et quand** (`squad/README.md`, section 9, amendement s17 - un engagement conditionnel
porte qui le verifie et quand) : **`sofia-sm`, a la fusion du lot qui ajoute le champ**, verifie que
les quatre clauses sont dans le meme commit que lui. **Si le champ arrive sans elles, c'est un
defaut de lot, pas une dette a ouvrir.** Et le depot le dira de lui-meme sur la clause 1 :
`chaqueComposanteDUnItemEstClasseeDansLIdentiteDEcranOuHorsDElle` est rouge tant que la composante
n'est pas classee.

---

## Consequences

- **Le perimetre de s26 est celui de la branche haute de `nadia-prof`** : 5 paquets, 4 lignes,
  plafond **35 / 41 = 85,4 %**. Le rang et le choix restent a `camille-po`.
- **Aucun paquet servi ne change, aucun Done n'est rouvert.** Une composante `Optional` n'oblige
  aucun des 27 paquets a porter quoi que ce soit - mesure : `adapter-content` reste vert sur ses 410
  tests hors les paquets de sonde.
- **`ADR-0032` change de critere, pas de propriete.** Les 27 paquets servis sont juges par le meme
  calcul augmente d'une composante qu'aucun ne porte : leur verdict est inchange par construction.
- **Le cout de la famille a voix se paie entierement dans le contrat de contenu et dans le rendu**,
  jamais dans `domain/mechanic` ni dans `domain/mechanics` - mesure : 0 arret du compilateur,
  `domain` a 80 tests et 0 echec sous la sonde.
- **Trois conditions et demie restent sans garde** - `VOI-3`, `VOI-5`, `VOI-8`, et la seconde moitie
  de `VOI-13` (rien n'empeche l'enfant de lire par-dessus l'epaule). **Elles sont ecrites comme non
  gardees ici aussi**, parce qu'un `ADR` qui les enumererait parmi ses clauses sans le dire ferait
  croire qu'un test les tient.
- **Ce que je paie et que je nomme** : les clauses 2 et 3 comparent des sous-chaines sur une forme
  repliee. Un mot decoupe en syllabes dans un enonce, ou designe par une periphrase, leur echappe.
  Elles attrapent la faute reelle - le mot recopie - pas la faute imaginative.

## Alternatives considerees

1. **Une mecanique a voix.** Ecartee sur le critere d'`ADR-0058`, mesure : la facon dont l'enfant
   repond ne change sur aucune des quatre lignes, et la sonde ne produit **aucun** arret du
   compilateur ni aucun rouge dans `domain` ou `adapter-web`. Elle aurait coute une ligne de
   couverture (82,9 % contre 85,4 %) pour une copie de `BanditManchot`.
2. **Une troisieme forme d'item.** Ecartee pour le meme motif, et le prix en est connu : `ADR-0073`
   a mesure **6 arrets du compilateur et 12 classes rouges** pour une forme de plus. Rien ne
   l'appelle ici - la reponse de l'enfant est deja couverte par les deux formes existantes.
3. **Poser le champ sur `Question` seule.** Ecartee par la mesure : la ligne *ecrire un mot en
   autonomie* est un item de saisie, et ce paquet de sonde est precisement l'un des trois qui
   collisionnent. Cette alternative aurait ete **juste en intention et fausse en extension** sur un
   paquet sur cinq - le piege nomme par `yanis-qa`, evite ici par la sonde et non par la relecture.
4. **Un champ *"ce que l'adulte ne doit pas dire"*.** Ecartee, et le motif est de `nadia-prof` :
   *"une liste d'interdits est une liste qu'on oublie de tenir a jour"*. Le champ clos de `VOI-10`
   ferme le canal **par construction**. C'est le meme geste qu'`ADR-0073` sur l'illustration :
   fermer par construction plutot que par vigilance.
5. **Laisser `ADR-0032` intact et faire varier l'enonce de chaque item.** Ecartee : faire varier ce
   que l'enfant lit est exactement ce que `VOI-13` interdit. Cette alternative rend le garde-fou vert
   **en defaisant la ligne pedagogique** - la definition d'une regle qui passe sans rien prouver.
6. **Compter sur `ADR-0070` pour garder le champ neuf.** Ecartee sur mesure : trois etats mesures,
   verte dans deux d'entre eux dont celui ou aucune fabrique ne lit la cle. C'est la propriete faible
   qu'`ADR-0070` decide d'avoir, et elle est bonne ; elle n'est simplement pas ce qu'on croirait.

## Ce que cet `ADR` ne decide pas

- **Ce que chaque paquet dit, et a qui.** Le texte de l'adulte, la phrase d'interdit, leur registre
  et leur justesse pedagogique appartiennent a `nadia-prof` et aux developpeurs qui redigent les
  items.
- **Quelles lignes s26 prend, dans quel ordre, et si les cinq paquets y tiennent.** C'est
  `camille-po`, et l'avis de faisabilite dit ce que j'en pense sans en decider.
- **La forme a l'ecran.** `ines-design` propose, `nadia-prof` juge. La section 7 dit ce que la
  structure doit rendre possible, jamais a quoi cela ressemble.
- **Le sort de la ligne refusee** - decoder un mot regulier par assemblage syllabique. `nadia-prof`
  constate une impasse et nomme deux voies ; aucune n'est technique de mon fait.
