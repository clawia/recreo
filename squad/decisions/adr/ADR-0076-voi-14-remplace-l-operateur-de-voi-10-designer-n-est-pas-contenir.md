# ADR-0076 - `VOI-14` remplace l'operateur de `VOI-10` : designer n'est pas contenir, et la branche saisie disparait

- **Statut** : Accepte. **La regle est ecrite, verte et mise a l'epreuve** -
  `adapters/adapter-content/src/test/java/com/recreo/adapter/content/AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest.java`,
  **14 tests** : onze sur les jeux fautifs commites de `marc-dev`, qui reproduisent les douze
  contre-tests de `nadia-prof`, et trois mutations en memoire d'items **reellement servis**, ecrites
  par `theo-archi` pour la section 3.
- **Sprint** : s26. **Date** : 2026-09-01. **Auteur** : `theo-archi`.
- **Ce que cet `ADR` fait** : il **acte** dans l'architecture l'arbitrage pedagogique de
  `nadia-prof` (`VOI-14`, section 16 de son arbitrage prealable, `3216f92`), **complete sa regle sur
  un materiau que sa mesure ne contenait pas** (section 3), et **verifie sa mesure par un troisieme
  chemin**. **Il ne tranche aucune pedagogie** : la frontiere *designer / contenir* est d'elle, et
  elle est citee, jamais reformulee.
- **L'encodage est de `marc-dev`** (`s26-marc`, `5a57161`) : le tokeniseur, les dix jeux fautifs et
  la clause 4 de traversee. `theo-archi` y apporte la correction de la section 3 et ses trois
  morsures.
- **Arbre mesure** : worktree `s26-theo-reco`, parti de `s26-marc` a `5a57161` - lequel porte deja la
  fusion de `s26-contenu`, donc les cinq paquets de `lea-dev`. **JDK 21.0.10**, celui que la machine
  virtuelle des tests rapporte d'elle-meme (`ADR-0071`). Enveloppe : `bash outils/mvn-jdk21.sh`.
- **Amende `ADR-0075` clause 2**, ecrite par moi dans ce meme sprint. **Ne remplace pas `ADR-0075`.**
- **Prolonge** : `ADR-0013`, `ADR-0051`, `ADR-0058`, `ADR-0070`.

---

## Contexte

### L'enchainement, avec qui a trouve quoi

1. **`lea-dev`** livre les cinq paquets a voix (`s26-contenu`, `ab574e2`) et **mesure** que le crochet
   `VOI-10` d'`ADR-0075`, encode par `marc-dev`, rougirait sur trois paquets sur cinq. Elle ne branche
   pas le crochet et ne touche pas au test : *"c'est une decision d'architecture qui ne
   m'appartient pas."* **Elle a raison sur les deux points**, et son constat est le point de depart
   de tout ce qui suit.
2. **`theo-archi`** remesure par l'execution du garde-fou lui-meme et instruit la question.
3. **`nadia-prof`** tranche le versant pedagogique - section 16 de son arbitrage prealable, commit
   `3216f92` - et **ecrit `VOI-14`**, operateur compris, mesure sur les cinq paquets et sur douze
   contre-tests.

**Cet `ADR` encode `VOI-14`. Il n'en propose pas une variante.**

### L'attribution, et elle est une regle de ce depot

**Le defaut est dans la clause, et la clause est de `nadia-prof`.** Elle est allee le verifier
elle-meme avant de commenter le travail d'un autre, et elle l'ecrit (section 16.1) :

> *"**"Contient une chaine".** C'est l'operateur de sous-chaine, ecrit de ma main, dans ma propre
> condition. `marc-dev` a encode **exactement** ce que j'avais demande, et son test est fidele a la
> lettre que je lui ai donnee. **Le defaut n'est pas dans le garde-fou : il est dans la clause, et la
> clause est a moi.**"*

**Et ma part y est aussi.** `ADR-0075` clause 2 a recopie sa formulation calculable sans la mesurer
sur les paquets que le sprint allait servir. J'y ai ecrit que la comparaison *"reste une comparaison
de sous-chaines"* et nomme ce qu'elle **ne voit pas** ; **je n'ai pas nomme ce qu'elle voit de
trop.** C'est le geste que je reproche aux autres depuis deux sprints, commis dans l'`ADR` qui le
reproche.

Elle note ce que ses deux fautes du jour ont en commun, et c'est utilisable par tout le monde :

> *"les deux fois, j'ai ecrit la partie "calculable" au fil de la plume, **apres** la partie qui porte
> le motif, et sans la mesurer sur un item reel. Le motif, je le pese ; l'operateur, je l'ai lache."*

### Ce que le defaut etait vraiment - plus large que trois paquets

`sofia-sm` me l'a transmis comme une clause qui echoue sur trois paquets. **`nadia-prof` etablit
qu'il est d'une autre taille** (section 16.2, raison 1) :

> *"ce n'est pas une clause qui echoue sur trois paquets - **elle echoue sur une categorie entiere de
> competences**, et y echouera sur tout paquet futur de la meme famille."*

Les lignes concernees s'appellent *identifier un son dans un mot*, *associer les graphemes aux
phonemes*, *ecrire un mot dicte*. **Leur enonce meme dit que la reponse est dans le stimulus** :
l'enfant a pour tache d'**extraire** `ch` de `chat`. Une clause qui interdit au stimulus de contenir
la reponse **interdit la competence**.

Ses deux autres motifs, que je reprends parce qu'ils gouvernent l'operateur que j'encode : `ch` **ne
se prononce pas** - *"une consonne francaise n'a pas de prononciation autonome"*, et *"l'operateur de
sous-chaine compare une chose dite a une chose ecrite, et conclut a leur identite parce qu'elles
partagent deux caracteres"* ; et la fuite que `VOI-1` vise est une **comparaison**, qui exige deux
enonces - *"un mot dit seul n'a rien a quoi se comparer"*.

> **La frontiere, dans ses mots : "Designer n'est pas contenir."**

---

## Decision

### 1. `VOI-14` point 1 est l'operateur de `VOI-10`, et `VOI-10` n'est pas effacee

> **Le texte de l'adulte est decoupe en jetons - toute suite de lettres, les separateurs etant tout
> ce qui n'est pas une lettre -, sur la forme repliee (casse et diacritiques). Une proposition fuit
> si et seulement si la suite de ses jetons apparait comme suite CONTIGUE de jetons du texte de
> l'adulte.**

`VOI-10` reste lisible a sa place avec sa date : **son motif est juste, son operateur est faux**, et
c'est le geste qu'elle a deja fait le matin meme avec `VOI-13` remplacant `VOI-2`. `VOI-1` n'est pas
en cause et n'est pas touchee.

**Conformite d'encodage** : les jetons sont des **suites de lettres** et non de lettres et de
chiffres. J'avais derive l'operateur de mon cote avant de lire sa section 16 et j'y avais inclus les
chiffres ; **c'est sa definition qui fait foi, et c'est la sienne qui est encodee.** La difference
n'est pas cosmetique - voir la section 3.

### 2. La branche `saisie` disparait, et elle est remplacee, pas supprimee

C'est une **suppression**, pas un assouplissement, et le motif est d'elle (section 16.3) :

> *"sur un paquet de saisie, `VOI-1` n'a pas d'objet, et elle n'en a jamais eu. Sa phrase entiere est :
> ce parmi quoi l'enfant doit **choisir** ne l'est jamais. Dans une dictee, l'enfant ne choisit pas :
> il **produit**. [...] Une dictee ou l'adulte ne dirait pas le mot n'est pas une dictee protegee,
> c'est une dictee vide."*

**Consequence encodee** : le jeu de propositions d'un item de saisie est **vide**. C'est aussi la
definition que le depot portait deja avant ce sprint - `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest.ecranDe`
rend `Set.of()` pour un `ReponseAEcrire` depuis le lot 5a de s24, avec son motif ecrit. **Deux
definitions du meme mot coexistaient dans le meme module ; celle qui reste est celle qui precede ce
sprint.**

**La fixture `saisie-fuite` de `marc-dev` decrit une dictee correcte.** Son test n'est pas supprime :
**son sens est inverse**, il epingle desormais qu'une dictee ou l'adulte dit le mot reste licite.

> **`VOI-14` point 2, ce qui prend la place** : *"les lettres de la forme de reference ne doivent pas
> apparaitre comme suite contigue de jetons du texte de l'adulte. C'est l'epellation, c'est-a-dire la
> moitie calculable de `VOI-8`."*

**Elle ne retire pas un garde-fou sans en nommer un**, et le garde-fou nomme couvre une condition
qu'elle avait declaree **sans garde** le matin meme. `VOI-8` gagne donc sa moitie calculable, et la
liste des conditions sans garde **ne diminue pas pour autant** - elle l'ecrit elle-meme en 16.7, et je
ne l'arrange pas ici.

### 3. Le cas qu'aucune des trois mesures ne contenait : une proposition sans jeton

**C'est la seule part de cet `ADR` qui ne transcrit pas `VOI-14` mais la complete**, et elle porte
sur un materiau que ni `nadia-prof`, ni `lea-dev`, ni ma propre mesure n'avaient sous les yeux : les
cinq paquets a voix sont en francais, et leurs propositions sont des mots ou des lettres. **Le
catalogue servi, lui, ne l'est pas.**

`VOI-14` ecrit *"toute suite de **lettres**"*. Une proposition qui n'en porte aucune a une liste de
jetons **vide**, et une suite vide est contigue partout : la comparaison n'y decide rien, dans un
sens comme dans l'autre.

**Mesure du 2026-09-01, sur les 2 544 propositions des paquets servis** - *methode : lecture ligne a
ligne des cles `bonneReponse`, `distracteurs`, `formeDeReference` et `formesAcceptees` des paquets de
`contenu/`, arbre `s26-theo-reco`* :

| Tokenisation | Propositions sans aucun jeton | Part | Paquets |
|---|---|---|---|
| **lettres seules** (la lettre de `VOI-14`) | **1 057** sur 2 544 | **41,5 %** | 13 |
| lettres **et chiffres** | **93** sur 2 544 | 3,7 % | 2 |

> **Et 313 de ces 1 057 sont des bonnes reponses.**

**Ce que l'encodage d'origine en faisait, et pourquoi c'etait le mauvais cote.** Il rendait `false`
sur une liste vide, avec ce motif ecrit : *"`Question.bonneReponse()` n'est jamais vide en
pratique"*. **C'est vrai de la chaine et faux de sa liste de jetons** - 313 bonnes reponses servies
n'en portent aucun. Consequence : sur 41,5 pour cent des propositions du catalogue, **aucune fuite
n'aurait jamais pu etre signalee**, et un adulte lisant *"Est-ce 7, 8 ou 9 ?"* serait passe vert.
C'est le mode de defaillance silencieux, celui qui ne se remarque pas.

**Ce que le repli sur la sous-chaine en aurait fait, et pourquoi ce n'etait pas mieux.** J'avais
d'abord tranche ainsi, du cote strict. **Mesure contre moi-meme** : sur un item reel de
`cp-soustractions` dont la bonne reponse est `7`, un texte d'adulte disant *"Le nombre est 17."*
devient **rouge**, `"17"` contenant `"7"`. C'est **le defaut exact que `VOI-14` corrige pour les
lettres, reintroduit pour les nombres.** Contenir n'est pas enoncer, pour un nombre comme pour un
mot.

**La decision, en deux couches, et la premiere n'est pas un repli mais une generalisation :**

> **1. Un chiffre est un jeton, exactement comme une lettre.** Un chiffre est une unite autonome de
> parole : l'adulte qui dit *"Est-ce 7, 8 ou 9 ?"* enonce ses trois propositions, celui qui dit
> *"dix-sept"* n'en enonce aucune. **C'est la generalisation fidele du critere de `VOI-14`, pas un
> autre critere** - la comparaison reste une suite contigue de jetons entiers. Elle ramene le cas
> indefini de **1 057 propositions a 93**.
>
> **2. Pour le residu - 93 propositions, deux paquets, les `<`, `>` et `=` de `cm1-grands-nombres`
> et de `cp-comparer-nombres` -, repli sur l'inclusion de sous-chaine**, qui est le cote strict. Ce
> repli ne peut laisser passer aucune fuite ; il rend seulement le cas defini. **Il ne vaut que pour
> le point 1** : sur le point 2, une forme de reference sans lettre ni chiffre ne serait pas un mot a
> ecrire, et le `false` d'origine y est juste.

**Aucun de ces paquets ne declare de voix aujourd'hui** : le cas ne mord pas, et c'est precisement
pourquoi il fallait le mesurer plutot que l'attendre. **Le premier paquet a voix en mathematiques
rencontrera ce chemin.**

**Route a `nadia-prof`**, et la forme du routage compte : ce choix ne lui force la main **que dans le
sens du refus**. Si elle veut une autre regle pour un `10` prononce *"dix"*, elle la posera sur un
garde-fou qui, en attendant, ne laisse rien passer.

### 4. Ce que cet `ADR` refuse de faire, et ce qu'il abandonne

**Je n'invente pas d'operateur.** J'en avais derive un de mon cote avant que son arbitrage n'arrive,
et il coincidait sur le point 1 - **c'est une concordance, pas un titre a le preferer.**

**Et l'encodage n'est pas le mien.** Deux lots ont ecrit le meme fichier, par un defaut de routage
assume par l'orchestration. **Celui de `marc-dev` est retenu** : il porte l'arbre complet - les cinq
paquets, les dix jeux fautifs qui reproduisent les douze contre-tests de `nadia-prof`, et la clause 4
de traversee d'`ADR-0075`, que le mien n'avait pas. **Le mien est abandonne**, y compris la ou il
etait defendable : mon exemption plafonnee de `cp-mot-dicte` - une dette d'un paquet, avec son motif,
son terme et sa clause residuelle - est **sans objet** depuis `VOI-14` point 2. *Une dette qu'un
arbitrage supprime vaut mieux qu'une dette bien tenue.*

**Ce qui survit du mien est ce que le sien n'avait pas** : la section 3, sa correction et ses trois
morsures ; la resolution de l'ecart de mesure, plus bas ; et cette decision elle-meme.

**Une chose que je note sans la defaire.** La fixture `saisie-fuite` a ete **supprimee** et remplacee
par `dictee-normale`, qui epingle la meme propriete par l'autre bout - une dictee ou l'adulte dit le
mot reste licite. J'avais choisi d'**inverser le sens** de l'ancienne plutot que de la retirer, pour
que le depot garde la trace qu'une fixture a un jour affirme l'inverse. **La propriete est gardee
dans les deux formes** ; seule la trace historique differe, et elle est ecrite ici.

## Regle executable

Fichier : `adapters/adapter-content/src/test/java/com/recreo/adapter/content/AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest.java`.
**Le tokeniseur, la comparaison et les dix jeux fautifs sont de `marc-dev`** ; la couche de la
section 3 et ses trois morsures sont de `theo-archi`.

```java
/** VOI-14 : suites maximales de lettres et de chiffres, tout le reste etant separateur. */
private static List<String> jetons(String texte) { /* sur la forme repliee */ }

/** VOI-14 : jetonsCibles apparait comme suite contigue de jetonsTexte, jamais en sous-chaine. */
private static boolean contientCommeJetonsContigus(List<String> jetonsCibles,
                                                     List<String> jetonsTexte) {
    if (jetonsCibles.isEmpty()) {
        return false;                     // juste pour le point 2, traite en amont pour le point 1
    }
    /* ... balayage des sous-listes ... */
}

/** VOI-14 point 1 + ADR-0076 section 3 : le repli strict des 93 propositions sans jeton. */
private static boolean enoncee(String proposition, List<String> jetonsDuTexte, String texteReplie) {
    List<String> jetonsDeLaProposition = jetons(proposition);
    if (jetonsDeLaProposition.isEmpty()) {
        return texteReplie.contains(replier(proposition));
    }
    return contientCommeJetonsContigus(jetonsDeLaProposition, jetonsDuTexte);
}

// point 1 : les propositions d'un item de choix ; point 2 : les lettres de la forme de reference
case Question question -> { for (String p : propositionsDe(question))
        if (enoncee(p, jetonsDuTexte, texteReplie)) { /* rouge */ } }
case ReponseAEcrire reponse -> {
        if (contientCommeJetonsContigus(lettresDe(reponse.formeDeReference()), jetonsDuTexte))
            { /* rouge : epellation */ } }
```

**Le perimetre est derive** : les paquets viennent du balayage de `contenu/`, les items du **chargeur
reel**. **Le garde-fou juge le contenu servi** - c'est le `@Test` qu'`ADR-0075` annoncait comme du au
premier paquet a voix, et les **cinq paquets y passent**.

**La clause 4 de traversee d'`ADR-0075` est ecrite dans le meme lot** :
`bootstrap/src/test/java/com/recreo/bootstrap/rendu/LeBlocAdulteDUnItemReelAtteintLEcranDeMancheTest.java`,
sur le patron de `LeTexteSupportDUnItemReelAtteintLEcranDeMancheTest`. C'est elle qui ferme le trou
mesure en `ADR-0075` : `ADR-0070` etant satisfaite par le seul classement d'identite d'ecran, un
champ ecrit dans cinq paquets et jamais rendu serait vert partout.

### Les morsures

**Dix jeux fautifs commites, de `marc-dev`**, qui reproduisent les douze contre-tests de
`nadia-prof` (section 16.6) : `question-fuite` conservee telle quelle, `grapheme-seul`,
`trois-propositions-lues`, `reponse-designee`, `epellation-virgules`, `epellation-tirets`, et les
cas qui doivent rester verts - `sain`, `dictee-normale`, `epelle-un-autre-mot`, plus `aucune-voix`
pour la clause de non-vacuite.

**L'epellation est mordue sous ses deux ecritures et elle est bornee** : `epelle-un-autre-mot`
verifie qu'un adulte epelant un mot **autre** que la forme de reference ne declenche rien. Sans ce
cas, le point 2 serait une interdiction d'epeler quoi que ce soit, ce que `nadia-prof` n'a pas ecrit.

**Trois morsures de plus, de `theo-archi`, pour la section 3**, mutees **en memoire sur des items
reellement servis** - le chargeur lit le classpath, donc une copie sur disque ne serait pas
chargeable :

| Morsure | Item reel mute | Ce qu'elle prouve |
|---|---|---|
| `unAdulteQuiLitLesTroisPropositionsNumeriquesFaitEchouerLeGardeFou` | `cp-soustractions`, voix ajoutee *"Est-ce 7 ?"* | une proposition **numerique** enoncee est une fuite - elle ne pouvait pas l'etre |
| `unChiffreALInterieurDUnNombrePlusLongResteVert` | meme item, voix *"Le nombre est 17."* | **contenir n'est pas enoncer, pour un nombre aussi** : c'est ce cas qui interdit le repli par sous-chaine |
| `unAdulteQuiLitLesTroisSignesDeComparaisonFaitEchouerLeGardeFou` | `cp-comparer-nombres`, propositions `<`, `>`, `=` | le **repli strict** du residu, avec son vert de bord - un texte sans aucun signe ne rougit pas |

**Chaque morsure verifie d'abord la propriete qui la rend probante** : que la proposition porte bien
un jeton pour la premiere, qu'elle n'en porte aucun pour la troisieme, que le nombre long contient
bien le chiffre pour la seconde. **Une morsure qui ne verifie pas son hypothese ne prouve rien.**

**Et les deux couches de la section 3 sont prouvees par leur retrait**, sur l'arbre reel :

| Couche retiree | Resultat |
|---|---|
| les chiffres cessent d'etre des jetons | **2 rouges** - la fuite numerique n'est plus vue, **et** le nombre long devient un faux rouge |
| le repli strict est remplace par `false` | **1 rouge** - les trois signes de comparaison redeviennent invisibles |

*Les deux mesures sont faites par mutation du fichier reel puis restauration ; le detail des deux
modes de defaillance est en section 3.*

## Ma verification independante de sa mesure

`sofia-sm` demande une troisieme mesure **par mon propre chemin**, et non une relecture : *"elle a
mesure, `lea-dev` a mesure, les deux concordent ; une troisieme mesure par ton propre chemin est ce
qui distingue une concordance d'une repetition."*

**Mon chemin differe du leur sur le point qui compte** : elles ont l'une et l'autre mesure par un
script lisant le YAML ; **j'ai instrumente le garde-fou lui-meme**, donc les items tels que le
**chargeur reel** les construit, records du domaine compris, et j'ai fait tourner les deux operateurs
sur la meme population dans la meme execution. *Commande :
`bash outils/mvn-jdk21.sh -B -o -pl adapters/adapter-content -am test`, perimetre : le module
`adapter-content` et ses amonts, `-am` porte, jamais le depot.*

| Paquet | Items | Sous-chaine (`VOI-10`) | Jetons (`VOI-14`) | Table de `nadia-prof` |
|---|---|---|---|---|
| `cp-position-syllabe` | 18 | 0 | **0** | 0 / 0 |
| `cp-son-dans-mot` | 12 | 0 | **0** | 0 / 0 |
| `cp-lettre-du-son` | 18 | 18 | **0** | 18 / 0 |
| `cp-digraphe-du-son` | 15 | 15 | **0** | 15 / 0 |
| `cp-mot-dicte` | 12 | 12 | **0** | 12 / 0 |

> **Les cinq lignes concordent cellule pour cellule, et par un instrument different.** 45 items rouges
> sur 45 sous l'ancien operateur, **0 sur 45 sous `VOI-14`**, sans qu'une ligne de contenu ait bouge.

**Un ecart apparent, que je resous plutot que de le laisser** : ma premiere mesure, faite avant son
arbitrage, annoncait *"71 ecarts sur 43 stimulus distincts"* la ou elle compte **45 items**. Les deux
sont justes et ne comptent pas la meme chose - 71 est un nombre de couples *(item, proposition)*, un
item de `cp-lettre-du-son` en produisant jusqu'a trois ; **43 est un nombre de chaines distinctes**,
et il est inferieur a 45 parce que **deux stimulus sont partages par deux paquets** : *"Le mot est
moto."* et *"Le mot est lune."* vivent a la fois dans `cp-lettre-du-son` et dans `cp-mot-dicte`. C'est
licite - l'identite d'`ADR-0032` est **par paquet**. *Mon premier chiffre etait juste et son unite
etait mal nommee : un nombre de couples annonce comme un nombre d'items.*

**Porte technique**, arbre de reconciliation `s26-theo-reco` - `s26-marc` a `5a57161`, qui porte
deja `s26-contenu`, plus ce lot -, `bash outils/mvn-jdk21.sh -B -o clean verify` sur la racine :
**BUILD SUCCESS**, **1 079 tests, 0 echec, 0 erreur**. Par module : domain 82, application 13,
`adapter-web` 377, `adapter-content` 451, `bootstrap` 150, plus 6 en failsafe. *Methode : somme de
l'attribut `tests` de chaque rapport XML de surefire et de failsafe apres `clean`, les deux moteurs
comptes, jamais les lignes `Tests run:` des rapports `.txt`.*

---

## Consequences

- **Deux lots ont ecrit le meme fichier, et un seul survit.** Celui de `marc-dev`. Ce qui est repris
  du mien est nomme en section 4 : la section 3 et ses trois morsures, la resolution de l'ecart de
  mesure, et cette decision. **Le reste est abandonne, y compris ce qui etait defendable.**
- **Cinq references qui ne resolvaient plus sont corrigees dans le meme geste** : les cinq
  `PaquetCpXxxTest` de `lea-dev` citaient l'ancien nom de la classe. **Elles portaient deux erreurs et
  non une** - le nom, et l'affirmation *"reste volontairement non branche sur le contenu de
  production"*, qui n'est plus vraie. Un renommage seul aurait laisse cinq enonces faux dans l'arbre.
  C'est la famille exacte qui a rendu `round-two` rouge le matin meme.

- **Les cinq paquets de `lea-dev` passent le garde-fou sans qu'une ligne de contenu bouge**, et les
  quatre lignes restent fermables. **Le sprint ne passe pas de quatre lignes a une.**
- **Le garde-fou juge desormais le contenu servi**, ce qu'il ne faisait pas : c'est le `@Test` qu'
  `ADR-0075` annoncait comme du au premier paquet a voix.
- **`VOI-8` gagne sa moitie calculable.** Une condition declaree sans garde le matin en a une le soir,
  pour la seule part qui s'ecrit - ce que le produit **donne a lire**, jamais ce qui sort d'une bouche.
- **Ce que la regle ne dit toujours pas**, et `nadia-prof` l'ecrit mieux que moi : *"`VOI-14` verte ne
  dit pas que l'adulte n'a pas prononce la reponse. Elle dit qu'**on ne lui a pas donne a la lire**."*
  Les quatre conditions sans garde le restent, et la contrepartie reste `VOI-12`.
- **Un cas defini de plus, et une question ouverte** : la proposition sans lettre, section 3, tranchee
  du cote strict et routee.
- **Le troisieme `ADR` de ce sprint a etre mordu par `ADR-0066` avant d'exister.** Le garde-fou de
  references a rougi sur `DEC-0068`, puis sur `ADR-0074`, puis ici sur `ADR-0076`. Il fait son travail
  a chaque fois, et deux fois sur trois contre moi.

## Alternatives considerees

1. **Garder l'operateur et declarer les trois paquets hors norme.** Ecartee, et c'est le motif de
   `nadia-prof` qui l'ecarte : la clause echoue sur **une categorie entiere de competences**, pas sur
   trois paquets. Un garde-fou qui refuse la ligne qu'il sert n'est pas strict, il est faux.
2. **Mon propre operateur**, derive avant son arbitrage, sur deux points ou il divergeait du sien.
   **Le second est ecarte : il conservait sur la dictee un jeu de propositions dont elle etablit
   qu'il n'a jamais eu d'objet.** *Le premier - les chiffres comptes comme jetons - **n'est pas
   ecarte, il est adopte en section 3**, et c'est le seul endroit de cet `ADR` ou je maintiens une
   divergence avec la lettre de `VOI-14`. Je ne le maintiens pas parce qu'il etait de moi : je l'ai
   d'abord abandonne pour suivre sa formulation, puis la mesure des 1 057 propositions m'a fait
   revenir dessus. **L'ordre compte : la mesure d'abord, la conclusion ensuite** - c'est le contraire
   qui a produit les deux clauses fausses de ce sprint.*
3. **Ma dette plafonnee** ecartant `cp-mot-dicte` de `VOI-10` avec une clause residuelle. Ecartee :
   `VOI-14` point 2 la rend sans objet. Elle etait defendable tant que la question pedagogique etait
   ouverte ; elle ne l'est plus une fois tranchee.
4. **Une transcription phonetique.** Ecartee : le depot ne vendorise aucun lexique phonetique
   (`ADR-0065`), et le probleme n'est pas phonetique - il est de savoir si l'adulte **enonce** une
   unite.

## Ce que cet `ADR` ne decide pas

- **Aucune pedagogie.** La frontiere *designer / contenir*, le sort de la dictee et les douze
  contre-tests sont de `nadia-prof`, section 16 de son arbitrage prealable. Je les encode.
- **Le contenu des cinq paquets** : `lea-dev` les redige, `nadia-prof` les juge. Les cinq signaux
  qu'elle releve sur `cp-position-syllabe` (section 16.8) ne sont pas un verdict et ne sont pas ici.
- **Le perimetre de s26** : `camille-po`.
