# ADR-0100 - Un identifiant de condition porte son marqueur de reserve dans son dernier segment, et les deux formes deviantes sont gelees par un cliquet exact

- **Statut** : Accepte
- **Date** : 2026-09-24
- **Sprint** : s42 (apres cloture)
- **Auteur** : theo-archi
- **Etend** : `ADR-0085` (clauses 7 et 8), sur le patron de la clause D d'`ADR-0088`
- **Route a** : `nadia-prof` pour le renommage des sept jetons, qui ne se fait pas ici

## Contexte

**Une seule convention violee, deux symptomes opposes, deux auteurs.** Le marqueur de reserve `R`
vit dans le **dernier** segment d'un identifiant de condition. Le 2026-09-24, il a ete ecrit ailleurs
deux fois, par deux agents differents : **en tete**, ou il a produit un silence, et **au milieu**, ou
il a produit un faux rouge. Les deux se reparent dans le meme fichier de garde-fou, et cet ADR refuse
d'en faire deux dettes.

### Premier fait - le marqueur en tete, et le silence

A la cloture de s42, sept jetons de reserve ecrits par l'instance B de `nadia-prof` **n'etaient lus
par aucun des deux motifs** du garde-fou de `PAR-5` :

- quatre dans `squad/conformite/FICHE-bandit-manchot-symboles-cm1.md` ;
- trois dans `squad/conformite/FICHE-bandit-manchot-deplacements-quadrillage-cp.md`.

Ils sont ecrits sous la forme `R-<MATIERE>-<NIVEAU>-<ligne>-<n>` : **cinq segments, et le marqueur
de reserve `R` en premier segment**. Le meme jour, l'instance A a ecrit sept jetons sous la forme
usuelle (`HG-R23` a `HG-R25`, `MOB-R1` a `MOB-R4`) : ceux-la **ont fait rougir la CI**, ont ete
trouves par `lea-dev` en lancant `mvn -B clean verify`, et ont ete alloues dans le registre le jour
meme. Les sept autres n'ont produit aucun rouge, et ont ete trouves a la main a la cloture,
independamment par `theo-archi` et `sofia-sm`.

### La cause exacte, mesuree et non supposee

`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` lit deux motifs, et **deux raisons
independantes** font que ni l'un ni l'autre ne voit un tel jeton :

1. le premier segment vaut `R`, **une lettre**, en dessous du plancher `[A-Z]{2,5}` des deux
   motifs ;
2. les lookarounds des deux motifs refusent un tiret de part et d'autre - c'est ce qui ecarte un
   code de ligne de referentiel sans avoir a le nommer. **Aucun sous-segment n'est donc lu non
   plus** : ni `EMC-CM1-05` au milieu de `R-EMC-CM1-05-1`, ni `05-1` a sa fin.

Consequence : ces jetons n'etaient pas *non alloues*, ils n'etaient **pas examines du tout**.
C'est le meme mode de defaillance que le trou ferme par la clause D d'`ADR-0088` le 2026-09-08,
a un segment pres, et c'est le faux vert exact que `PAR-5` existe pour tuer.

### Second fait - le marqueur au milieu, et le faux rouge que personne n'a vu

**Le garde-fou de `PAR-5` est rouge sur `round-two` depuis le commit de la review de s42.** Constat
signale par `nadia-prof`, **reproduit ici avant d'etre repris** : arbre de travail remise, `HEAD`
sans aucune modification locale, execution du seul test du garde-fou, deux echecs de clause 2.

La cause est distincte de la precedente et elle est de `sofia-sm`. La section des points ouverts de
`squad/sprints/s42-fusion-six-lignes-deux-instances/review.md` designe les deux plages de reserves
de l'instance A par une **notation d'intervalle collee** de la forme `<PREFIXE>-R<premier>-<dernier>`
- une facon d'ecrire *"de la premiere a la derniere"* en une chaine. Ce n'est pas un identifiant.
Mais c'est ecrit **exactement** comme un identifiant a trois segments, dont le motif lit le segment
intermediaire comme un niveau : `IDENTIFIANT_A_TROIS_SEGMENTS` y voit un identifiant, ne le trouve
pas dans le registre, et rougit. Pire, `FORME_ALLOUABLE` **acceptait cette forme** : la reponse la
moins couteuse au rouge aurait ete d'ajouter une ligne de registre, c'est-a-dire de faire entrer une
notation de prose dans l'espace des identifiants.

**Comment il a survecu a la cloture.** Le commit qui introduit ces deux notations est **celui de la
review elle-meme**, qui annonce la porte technique verte. La mesure precedait son propre diff. C'est
le geste que `CLAUDE.md` nomme depuis s31 - *lancer la commande de la CI sur son propre diff avant de
commiter* - et c'est la seconde fois que son absence coute un rouge non vu a la cloture d'un sprint.

### La convention, verifiee dans le registre et non de memoire

La forme d'une reserve n'est pas un choix ouvert : elle est **constatee dans les 689 lignes de la
section 4 du registre**, ou le marqueur `R` vit toujours dans le **dernier segment**, jamais en tete
ni au milieu, sous deux formes seulement :

| Forme | Exemples reels | Segments |
|---|---|---|
| `<PREFIXE>-R<n>` | `HG-R23`, `MOB-R1`, `MAT-R1` | deux |
| `<PREFIXE>-<NIVEAU>-R<n>` | `ADD-CE1-R1`, `SOU-CE1-R1`, `DVS-CP-R1` | trois |

La seconde existe pour la raison meme que l'instance B invoque - *nommer une reserve sans taire son
niveau* - et elle est ecrite noir sur blanc dans la note de `nadia-prof` du 2026-09-22 au registre.
**La forme a cinq segments ne resout donc aucun besoin que la convention ne couvrait pas.**

L'amendement s16 de `squad/README.md` exige en outre que *toute famille de jetons neuve introduite
par `nadia-prof` soit reprise dans une table de correspondance unique, portee par la fiche de
conformite du jeu concerne*. **Aucune des deux fiches ne porte de table de correspondance**
(verifie sur leurs sommaires). La forme a cinq segments est donc une **deviation non declaree**, pas
une famille neuve.

Un trait aggravant, et il n'est pas de style : ces jetons **enchassent un code de ligne de
referentiel dans leur propre nom** (`EMC-CM1-05`, `MAT-CP-10`), que `ADR-0081` alloue dans un autre
espace de noms. La clause 6 d'`ADR-0085` existe pour refuser qu'un nom ait deux proprietaires ;
cette forme met le nom de l'un a l'interieur du nom de l'autre, la ou aucune des deux clauses ne
sait plus le voir.

## Decision

### 1. Le marqueur de reserve vit dans le dernier segment, et la forme allouable le dit

`FORME_ALLOUABLE` n'accueille **ni** la forme a marqueur en tete - il ne l'accueillait deja pas -
**ni** la forme a marqueur au milieu, qu'il acceptait par accident. Le segment intermediaire d'un
identifiant a trois segments est un **niveau** (`CP`, `CE1`, `CM1`), et un niveau ne porte jamais le
marqueur. `IDENTIFIANT_A_TROIS_SEGMENTS` porte la meme borne, ecrite **une fois** et lue par les
trois motifs : la place du marqueur est la propriete, et trois copies d'une meme propriete
divergeraient.

**Ce que cette borne retranche a la clause 2, mesure avant de l'ecrire** : aucun des 689
identifiants alloues n'a un segment intermediaire de cette forme, et les 689 lignes restent lues
apres le changement, sur 112 prefixes - le compte courant que le registre declare est inchange, donc
la clause 5 n'est pas touchee. La borne ne retire donc **aucun objet reel** au balayage ; elle en
retire une notation de prose.

### 2. Clauses 7 et 8 - un jeton dont le marqueur est deplace est compte, et sa dette est gelee

Deux motifs lisent desormais ces formes, **non pour les allouer mais pour les compter** : le marqueur
en tete (clause 7) et le marqueur au milieu (clause 8). Le nombre de jetons distincts est fige a sa
valeur exacte du 2026-09-24. Un dixieme, sous l'une ou l'autre place, ecrit n'importe ou, fait rougir
la CI.

**Ce point est ce qui distingue cette decision d'une exemption.** La notation d'intervalle cesse
d'etre lue par la clause 2 : si elle s'arretait la, elle deviendrait invisible, c'est-a-dire
exactement le defaut de l'instance B a l'autre bout. Elle **change de juge**, elle ne disparait pas.

**Methode du chiffre, dans le meme paragraphe.** Perimetre : les fichiers texte du depot enumeres
par le balayage du garde-fou - repertoires de sortie de build, arbres de contre-exemple et binaires
exclus, ces derniers reconnus a l'octet nul (`ADR-0036`). Critere : les deux motifs
`MARQUEUR_DE_RESERVE_EN_TETE` et `MARQUEUR_DE_RESERVE_AU_MILIEU`. Resultat, arbre du 2026-09-24
**avant ce diff** : **7 jetons en tete** (23 occurrences, 5 fichiers) et **2 jetons au milieu**
(2 occurrences, 1 fichier), soit **9**. La mention `R-MAT-CP-10-1 bis` n'est pas un jeton de plus -
l'espace ferme la chaine, et `bis` qualifie le jeton qui precede.

**Ecart avec le compte annonce a la cloture de s42, et il porte sur les membres, pas sur le
nombre.** `review.md` et `retro.md` enumerent *`R-EMC-CM1-05-1` a `-4`, `R-MAT-CP-10-1`, `-1 bis`,
`-2`* : sept elements, dont un (`-1 bis`) n'est pas une chaine distincte et un
(`R-MAT-CP-10-3`) manque. Le total tombe juste par compensation. **Les deux enumerations ont ete
faites a la main, sur les fiches ; celle-ci est faite par le motif qui sera execute.** C'est
exactement la raison d'etre de la clause : un compte a la main d'identifiants qu'aucun automate ne
lit est un compte qui se trompe sans bruit.

### 3. Le renommage appartient a `nadia-prof`, et il n'est pas fait ici

Les sept jetons vivent dans des fiches de conformite qu'elle signe. `camille-po` a deja pose le
precedent en s40 : *"j'ecris `DVS-CP-R1` dans `MATRICE-couverture.md`, jamais dans la fiche de
`nadia-prof` : je ne touche pas un document qu'elle signe"*. **Cet ADR ne renomme rien.** Il rend la
deviation visible et il en gele la taille ; la forme conforme et l'allocation sont son geste.

Noms cibles proposes, prefixes verifies libres dans la table de la section 4 du registre au
2026-09-24 - elle tranche, je ne fais que lui epargner la verification.

**Les noms cibles sont decrits, jamais ecrits.** Les ecrire ici en toutes lettres les minterait dans
un fichier commite avant qu'ils soient alloues, et ferait rougir la clause 2 sur cet ADR meme - le
garde-fou mord son auteur en cours d'ecriture, ce qui est une bonne nouvelle et pas un contretemps.

| Fiche | Jetons actuels | Forme conforme proposee | Verification faite au 2026-09-24 |
|---|---|---|---|
| symboles CM1 | quatre reserves, prefixe `R-EMC-CM1-05-` | forme a trois segments : prefixe `EMC`, segment de niveau `CM1`, marqueur `R` suivi des numeros 1 a 4 | `EMC` est deja alloue a deux segments (`EMC-1` a `EMC-11`) ; aucune allocation existante ne combine ce prefixe, ce niveau et un marqueur de reserve ; aucune collision avec un code de ligne, qui se termine toujours par un chiffre et jamais par un marqueur |
| deplacements CP | trois reserves plus une mention *bis*, prefixe `R-MAT-CP-10-` | forme a trois segments : **prefixe neuf** (par exemple celui des deplacements, trois lettres, absent de la table), segment de niveau `CP`, marqueur `R` suivi des numeros 1 a 4 | **ne pas reprendre `MAT`**, deja porte a un autre sens (`MAT-R1`, `MAT-R2`, familles de materiaux CM1) - c'est le motif qu'elle a elle-meme ecrit le 2026-09-24 en choisissant un prefixe neuf plutot que `MAT` |

Quatre numeros du cote CP et non trois : la mention *bis* est une reserve a part entiere, avec son
texte propre, et un numero est moins cher qu'un suffixe qu'aucun motif ne lit.

### 4. Les deux notations d'intervalle ne sont pas reecrites non plus, et pour un autre motif

Elles vivent dans `review.md` de s42, **fichier de ceremonie close, que rien ne modifie plus**
(`squad/README.md`, section 2). Elles n'ont donc pas de correctif de texte disponible, et c'est
precisement pourquoi le correctif est du cote du garde-fou : un faux rouge cause par une notation
qu'aucun agent n'a plus le droit de reecrire ne peut pas se reparer dans le document.

**Ce qu'une future review ecrit a la place**, et c'est la seule prescription de cet ADR sur la
prose : une plage de reserves se designe par ses deux bornes separees, jamais par une chaine qui
colle les deux numeros autour d'un tiret. Cette phrase-ci n'est pas gardee par un test - elle n'en a
pas besoin : le cliquet rougit sur la notation elle-meme.

### 5. Le cliquet ne descendra pas a zero par le seul renommage, et c'est ecrit d'avance

Six des neuf jetons sont cites par `review.md` et `retro.md` de s42, **fichiers de ceremonie close,
que rien ne modifie plus**. Apres renommage des fiches et de `MATRICE-couverture.md`, le plancher
atteignable est celui que portent ces deux fichiers-la. Le plafond se mesure a ce moment et se
descend par un diff dans le fichier de test, jamais ailleurs.

### Amendement du 2026-09-24, meme jour - le plafond descend de 9 a 7

`nadia-prof` a renomme et alloue ses huit reserves (`7004286e`), sous la forme a trois segments que
la decision 3 decrivait. **Le cliquet a rougi**, comme sa Javadoc l'annoncait : une egalite exacte
rougit quand la dette baisse, exactement comme quand elle monte. C'est le comportement voulu, et
c'est ce qui rend le plafond couteux a bouger dans les deux sens.

**Le plafond passe a 7, et non a 6.** Perimetre et critere inchanges, mesure faite **deux fois et
par deux chemins** - un balayage independant des sources, puis l'execution du test lui-meme :
**7 jetons distincts, 38 occurrences, 7 fichiers**, arbre d'apres le renommage et **avant le present
diff** - qui n'ajoute que des occurrences de jetons deja comptes, jamais un jeton de plus, verifie en
recomptant apres ecriture. Cinq portent le marqueur en tete
(`R-EMC-CM1-05-1`, `R-EMC-CM1-05-2`, `R-MAT-CP-10-1` a `-3`), deux au milieu. Aucun n'est plus un
identifiant courant : ils sont cites par les tables de correspondance des fiches, par `review.md` et
`retro.md` de s42, par `MATRICE-couverture.md`, par le registre et par cet ADR.

**L'ecart avec le 6 annonce porte sa cause, et elle est connue de ce depot.** La note de renommage
du registre predit `6` et range `R-EMC-CM1-05-2` parmi les jetons *"qui disparaissent"* - **dans la
phrase meme qui l'ecrit**, donc qui le maintient vivant. Le compte etait juste sur l'arbre d'avant
son propre diff, et faux sur l'arbre qu'il produit. **Troisieme occurrence en deux jours du meme
mode de defaillance** : la porte technique annoncee verte par la review de s42 avant son propre
commit, l'enumeration des sept jetons a la cloture, et ce compte-ci. Le plafond vaut ce que le
balayage trouve, jamais ce qu'un auteur prevoit - **je n'ai pas repris son chiffre, je l'ai
mesure**, et c'est la seule raison pour laquelle l'ecart est visible.

## Regle executable

**Fichier** :
`bootstrap/src/test/java/com/recreo/archi/ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest.java`

La place du marqueur, ecrite une fois ; les deux motifs qui la lisent hors de sa place ; le plafond
qui les rend mordants :

```java
private static final String SEGMENT_DE_MARQUEUR = "R[0-9]{1,2}-";

private static final Pattern IDENTIFIANT_A_TROIS_SEGMENTS = Pattern.compile(
        "(?<![A-Za-z0-9_/-])([A-Z]{2,5})-(?!" + SEGMENT_DE_MARQUEUR + ")([A-Z]{1,3}[0-9]{0,2})"
                + "-(R?[0-9]{1,2})(?![A-Za-z0-9_-])");

private static final String FORME_ALLOUABLE =
        "[A-Z]{2,5}(?:-(?!" + SEGMENT_DE_MARQUEUR + ")[A-Z]{1,3}[0-9]{0,2})?-R?[0-9]{1,2}";

private static final Pattern MARQUEUR_DE_RESERVE_EN_TETE = Pattern.compile(
        "(?<![A-Za-z0-9_/-])R(?:-[A-Z0-9]{1,4}){2,}(?![A-Za-z0-9_-])");

private static final Pattern MARQUEUR_DE_RESERVE_AU_MILIEU = Pattern.compile(
        "(?<![A-Za-z0-9_/-])[A-Z]{2,5}-" + SEGMENT_DE_MARQUEUR
                + "(?:[A-Z0-9]{1,4}-)*[A-Z0-9]{1,4}(?![A-Za-z0-9_-])");

private static final int PLAFOND_DE_JETONS_A_MARQUEUR_DEPLACE = 9;

@Test
void aucunJetonNeDeplaceSonMarqueurDeReserveAuDelaDeLaDetteGelee() {
    Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
    assertThat(jetonsAMarqueurDeReserveDeplace(racine))
            .hasSize(PLAFOND_DE_JETONS_A_MARQUEUR_DEPLACE);
}
```

**Ce n'est pas une `ArchRule`, et le compte de regles ArchUnit du depot est inchange** : ArchUnit lit
du bytecode, et un jeton ecrit dans un texte de gouvernance n'y est pas une propriete lisible. Le
mecanisme est le balayage de sources deja porte par ce fichier, dont la lecture de perimetre et
l'autorisation de contre-exemple sont **reutilisees plutot que redupliquees** - un second avis sur ce
qu'est un fichier du depot divergerait du premier.

**Ce que la regle interdirait reellement a quelqu'un qui essaierait de mal faire**, ecrit avant qu'on
le demande : ouvrir une dixieme reserve sous une forme que le registre ne sait pas ecrire, donc sans
allocation possible, donc sans collision detectable. C'est le geste exact du 2026-09-24, et il est
desormais rouge - des deux cotes, celui qui se taisait comme celui qui criait a tort.

**Trois tests de morsure, et ils ne prouvent pas la meme chose** :

- `unMarqueurDeplaceNEstNiLuNiAllouableAlorsQueLaFormeConformeLEst` constate les trois proprietes
  sur du **materiau reel**, pour les **trois** jetons deviants reels du 2026-09-24 et pour trois
  jetons reellement alloues (`HG-R23`, `MOB-R1`, `ADD-CE1-R1`) : la forme deviante n'est lue par
  aucun motif de la clause 2, n'est pas allouable, et est comptee par le cliquet ; la forme conforme
  est lue et allouable, et n'est pas comptee. Le jour ou quelqu'un elargit `FORME_ALLOUABLE`, ce
  test tombe ;
- `lesFormesVoisinesNeSontPasDesIdentifiantsDeCondition` recoit un cas de plus : la notation
  d'intervalle reelle de `review.md`, qui ne doit plus etre lue comme un identifiant a trois
  segments. C'est l'assertion qui aurait mordu sur le faux rouge, ecrite sur la chaine exacte qui
  l'a cause ;
- `lesDeuxFormesAMarqueurDeplaceSontVuesDansUnDepotFautif` (obligation 3 d'`ADR-0013`) constate sur
  un arbre de contre-exemple que le compteur **lit reellement** les deux places. Sans lui, un
  plafond de neuf pourrait etre tenu par un compteur qui ne lit rien.

## Consequences

- **Bonnes.** La famille de jetons qui a traverse un sprint entier sans rouge en produit un des le
  dixieme. Le faux rouge qui bloquait `round-two` depuis le commit de la review de s42 est leve, et
  il l'est **sans qu'une notation entre dans l'espace des identifiants**. La forme allouable reste a
  deux entrees, et le registre n'apprend pas un troisieme dialecte. La verification de la convention
  cesse d'etre une lecture a la main - qui vient de se tromper deux fois de suite sur l'enumeration,
  sans se tromper sur le total.
- **Mauvaises, et elles sont reelles.** Les sept jetons de reserve restent **non alloues** apres cet
  ADR : geler n'est pas corriger, et la garantie d'unicite de `PAR-5` ne les couvre toujours pas. Le
  cliquet **gele aussi les formes deviantes logees dans des fichiers de ceremonie close**, donc il
  ne retombera jamais a zero. Un plafond exact produit un rouge le jour du renommage, tant que
  personne ne le descend - c'est le prix d'un cliquet exact, et c'est celui qu'`ADR-0067` a deja
  accepte. Enfin, la borne de niveau **retire a la clause 2** une forme qu'elle lisait : si un
  identifiant de condition devait un jour porter un niveau ecrit comme un marqueur, il echapperait -
  aucun niveau servi ne s'ecrit ainsi, et c'est verifie sur les 689 allocations, pas suppose.
- **Ce qui me ferait changer d'avis.** Une **troisieme** forme de reserve legitime, argumentee par
  un besoin que les deux formes actuelles ne servent pas. Ce jour-la, la bonne reponse est
  d'elargir `FORME_ALLOUABLE`, de declarer la famille dans la table de correspondance qu'exige
  l'amendement s16, et de supprimer ce cliquet - pas de l'agrandir.

## Alternatives considerees

1. **Elargir le garde-fou pour lire et allouer la forme a cinq segments.** Ecartee : elle
   institutionnaliserait une deviation non declaree, ajouterait une troisieme forme au registre pour
   sept jetons, et laisserait un code de ligne de referentiel enchasse dans un identifiant de
   condition - exactement ce que la clause 6 refuse entre deux espaces de noms.
2. **Renommer moi-meme les sept jetons dans les deux fiches.** Ecartee : je n'ecris pas dans un
   document que `nadia-prof` signe, et le precedent est pose par `camille-po` en s40. J'ecris les
   noms cibles et les verifications de prefixe pour qu'il ne lui reste que l'arbitrage.
3. **Ne rien encoder et router le renommage seul.** Ecartee : c'est la reponse qui laisse la squad
   exactement ou elle etait. Rien n'empecherait la prochaine instance de recommencer, et c'est ma
   propre regle qui l'interdit - une convention qui n'est pas encodee en test n'est pas une
   convention.
4. **Interdire la forme sans cliquet, en la faisant rougir immediatement.** Ecartee : la porte
   technique serait rouge tant que `nadia-prof` n'a pas renomme, donc l'arbre serait livre rouge
   par un diff d'architecte sur des documents qui ne lui appartiennent pas. `ADR-0067` a deja tranche
   cette question : on arrete une dette avant de la corriger, et on la corrige chez son auteur.
5. **Allouer les deux notations d'intervalle dans le registre.** C'etait la reponse la moins
   couteuse au faux rouge, et `FORME_ALLOUABLE` l'acceptait. Ecartee : elle ferait entrer dans
   l'espace des identifiants deux chaines qui ne designent aucune condition, et le registre
   nommerait comme document de definition un rapport de sprint qui ne definit rien.
6. **Exempter la notation d'intervalle sans la compter.** Ecartee : elle deviendrait invisible,
   c'est-a-dire ramenee au defaut de l'autre bout. Une forme retiree a un juge est donnee a un
   autre, jamais rendue muette.
7. **Amender `review.md` pour ecrire la plage en deux bornes.** Ecartee : c'est un fichier de
   ceremonie close, et le corriger ferait payer a la regle de gouvernance ce qui est un defaut de
   garde-fou. La prescription pour les futures reviews est ecrite en decision 4 ; le passe est gele,
   pas reecrit.
