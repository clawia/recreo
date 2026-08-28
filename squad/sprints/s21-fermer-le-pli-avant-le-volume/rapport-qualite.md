# Rapport qualite - s21 (fermer le pli avant le volume), yanis-qa

Mesure sur le worktree principal, branche `round-two`, a l'etat commite le plus recent au moment de
la review : `8ecf44d` (`fusion finale (nadia-2, lea-3, camille-2)`, `git status` rendait `clean`
avant toute intervention et apres). Perimetre du sprint tel que route par `sofia-sm` : les onze
stories de `squad/sprints/s21-fermer-le-pli-avant-le-volume/planning.md`, l'arbitrage de
l'utilisatrice "fermer le pli d'abord, puis le volume" (`DEC-0051`), les trois paquets neufs
(`cp-syllabes`, `cp-mot-manquant`, `cp-comparer`) et les correctifs d'outillage et de garde-fous
livres en cours de sprint. Machine virtuelle des tests : **JDK 21.0.10**, la toolchain retenue par
Maven (`Found matching toolchain for type jdk` et `Toolchain in maven-compiler-plugin` du journal
de ma propre execution) - jamais la ligne de diagnostic de l'enveloppe `outils/mvn-jdk21.sh`, dont
`S21-6` (`theo-archi`) a etabli qu'elle nomme un fichier de toolchains different de celui que Maven
lit reellement. Date de toutes les mesures de ce rapport : **2026-08-28**.

Ce rapport ne porte pas de Done pedagogique : ce n'est pas mon role. Les trois Done du sprint sont
prononces par `nadia-prof` (`squad/sprints/s21-fermer-le-pli-avant-le-volume/avis-nadia-done-
pedagogique-s21.md`, verdicts du 2026-08-28) : `cp-syllabes`, `cp-mot-manquant` et `cp-comparer`
sont **VALIDE SOUS RESERVE**. Je ne recopie pas ses chiffres sans les verifier : ceux que je cite
ci-dessous ont ete confrontes a leur source primaire (fiches de conformite, `MATRICE-
couverture.md`, `catalogue.yaml`).

## Verdict sur la porte technique

**`bash outils/mvn-jdk21.sh -B clean verify` a la racine, sur `8ecf44d`, reacteur complet, le
2026-08-28 : `BUILD SUCCESS`, code de sortie 0, `Total time: 01:22 min`.** Six modules, aucun `-pl`
et donc aucune question de `-am`. Blocs `[INFO] Results:` releves module par module dans le journal
de cette commande, jamais deduits d'un total :

| Module | Bloc `Results:` |
|---|---|
| `recreo-domain` | `Tests run: 49, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-application` | `Tests run: 13, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-adapter-web` | `Tests run: 332, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-adapter-content` | `Tests run: 224, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-bootstrap` (surefire) | `Tests run: 98, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-bootstrap` (failsafe) | `Tests run: 6, Failures: 0, Errors: 0, Skipped: 0` |
| **Total, somme des six blocs** | **722** |

**722 confirme, par ma propre execution du 2026-08-28, le chiffre annonce dans le message du commit
`8ecf44d`** ("722 tests (49+13+332+224+98+6), zero echec") et celui deja mesure par `theo-archi` le
2026-08-27 sur l'arbre fusionne a `9ace409`
(`correctif-porte-rouge-troisieme-appelant.md`, section 6, meme repartition module par module) :
je ne relaie ni l'un ni l'autre, je les reconstruis independamment, un jour plus tard, sur l'etat
final.

**Delta avec la base confirmee de s20** : `694` (`squad/sprints/s20-mot-cible-designe-et-
conception-de-la-saisie/rapport-qualite.md`, ma propre mesure du 2026-08-27 sur `77dd467`) ->
`722` le 2026-08-28, soit **+28**. Je ne recompose pas ce delta module par module moi-meme : il
traverse trois lots merges successivement (`s21-theo` : 694 -> 701 par `ADR-0063`
(`AuPlusTroisPropositionsParItemTest`, +7) ; `S21-12` : 701 -> 703 par les deux tests neufs de
`LActionNeDeclarePasMoinsQueLaSortieTest` ; fusion complete : 703 -> 722 par les trois paquets de
contenu neufs, `recreo-adapter-content` passant de 205 a 224). Chaque etape porte sa propre mesure,
sa methode et sa date dans les fichiers cites ; je cite la chaine plutot que de la recomposer sur un
arbre que je n'ai pas mesure a chacune de ses etapes.

**Regles ArchUnit** : `grep -rn "static final ArchRule\|static ArchRule" --include=*.java .` sur le
worktree, `target/` exclu et tout chemin `contreexemple` exclu, le 2026-08-28 : **30 champs
`ArchRule` reels**, inchange depuis s20. Aucune story de s21 n'en revendique une nouvelle
(`ADR-0063` et le correctif de `S21-12` sont tous deux des garde-fous de lecture de feuille ou de
balayage de donnee chargee, pas des regles ArchUnit - les deux le disent eux-memes).

Aucun test instable rencontre sur l'ensemble des executions de ce rapport (une seule execution par
chiffre final cite), et aucun test instable n'est signale dans aucun document commite de ce sprint.

## 1. Les garde-fous de depot, sur du materiau reel du sprint

```
bash .github/scripts/verifier-chemins-test.sh      -> tous les cas passent
bash .github/scripts/verifier-prefixes-test.sh     -> tous les cas passent
bash .github/scripts/commit-atteignable-test.sh    -> tous les cas passent
```

Mis a l'epreuve sur le materiau reel du sprint, pas seulement sur leur propre suite. Plage
`77dd467..8ecf44d` (`77dd467` etant le dernier commit mesure par le rapport de s20) : **54 commits
au total, 41 hors fusion, 13 de fusion** (`git rev-list --count`, `--no-merges` et `--merges`, le
2026-08-28 - la forme `git log --oneline | wc -l` de ce poste rend un chiffre different sur ce
depot pour une raison que je n'ai pas a instruire ici, `git rev-list --count` est la mesure brute
que je retiens).

- `git log --no-merges --format=%s 77dd467..8ecf44d | bash .github/scripts/verifier-prefixes.sh` ->
  "Tous les commits portent un prefixe d'agent connu."
- `git diff --name-only 77dd467..8ecf44d | bash .github/scripts/verifier-chemins.sh` -> "Aucun
  chemin de poste de travail ni nom de compte dans les fichiers verifies."

## 2. Tracabilite des criteres d'acceptation, sur les trois paquets neufs

Chaque paquet a son test dedie, et chaque assertion porte le label de condition qu'elle garde
(`PaquetCpSyllabesTest`, `PaquetCpMotManquantTest`, `PaquetCpComparerTest`, tous dans
`adapters/adapter-content/src/test/java/com/recreo/adapter/content/`) - exemples verifies par
lecture directe du code, le 2026-08-28 : `chaqueItemProposeExactementLesValeursUnDeuxTrois` et
`laRepartitionDesTroisValeursRespecteLeSeuilDUnQuartAUneMoitie` nomment `SEG-3` et `SEG-6` dans
leurs propres messages `.as(...)`. `AuPlusTroisPropositionsParItemTest` (`ADR-0063`, +7 tests dans
le delta ci-dessus) garde la clause de nombre commune aux trois paquets.

**Ce que cette couverture technique ne garde pas, et ce n'est pas un trou** : `nadia-prof` compte
**vingt-sept conditions sur les trois paquets** (citation directe, `avis-nadia-done-pedagogique-
s21.md`, section "Sur quelles proprietes je reste la seule garde") **dont trois seulement sont
partiellement tenues par un test** - la clause de nombre de `SEG-3`, `PRE-6` et `GRA-8`, celle que
`AuPlusTroisPropositionsParItemTest` verifie. Les vingt-quatre autres (exactitude d'un compte de
syllabes, decidabilite d'une comparaison, plausibilite d'un distracteur, unicite de la reponse
defendable, biais de representation) **n'ont aucun garde-fou et ne doivent pas en avoir** : un
classifieur qui deciderait a la place de `nadia-prof` se tromperait pour une mauvaise raison, ce
qu'`ADR-0028` et le refus de `lea-dev` d'ecrire un classifieur fragile a s20 (approuve par
`theo-archi`) ont deja etabli. Je ne reclame donc pas de garde-fou technique la ou ces deux avis
convergent deja pour dire qu'il serait pire que rien.

**Catalogue et matrice, verifies contre leur fichier source, pas repris d'un rapport** :
`catalogue.yaml` porte **21 entrees** (`grep -c "^  - id:"
adapters/adapter-content/src/main/resources/catalogue.yaml`, le 2026-08-28). `MATRICE-
couverture.md` porte **18/43 (41,9 %)** dans son tableau final (`squad/programme/MATRICE-
couverture.md`, section "Amendement du 2026-08-28 (second, s21 cloture)", ligne "Total" : 47
competences, 43 atteignables, 18 `couvert-valide`).

## 3. La recolte de ce sprint : six instruments qui ne regardaient pas ce qu'ils jugeaient

C'est le coeur de ce rapport, parce que c'est le fait saillant du sprint : plus de defauts
d'outillage et de garde-fous trouves en un sprint que de coutume, et **aucun n'a ete trouve en
lisant un rapport vert**. Chacun a ete trouve en cassant quelque chose ou en confrontant un chiffre
a sa source primaire - exactement la regle que ce role s'impose. Ce qu'ils ont en commun : dans les
six cas, l'instrument ne s'est pas trompe sur une valeur, **il n'a pas lu celle qui comptait**, et
il est reste vert ou silencieux plutot que de le dire. C'est la famille que `CLAUDE.md` nomme pour
un `-pl` sans `-am` : le mode de defaillance n'est pas l'echec, c'est l'ecart muet.

1. **`LActionNeDeclarePasMoinsQueLaSortieTest` restait vert 11 sur 11 en lisant la premiere
   declaration de `padding` au lieu de celle qui gagne la cascade.** `ADR-0017` (l'action ne
   declare pas moins que la sortie) a ete viole en rendu reel a travers trois CI vertes avant que
   `marc-dev`, en livrant son propre lot, ne le remarque. `theo-archi` (`S21-12`,
   `garde-fou-aveugle-et-acc-1-s21-12.md`) a corrige le lecteur (lire la derniere declaration, pas
   la premiere) et rendu l'omission bruyante au point de passage commun (`valeurDeclaree` refuse
   desormais une lecture qui n'est pas la derniere position). Preuve sur materiau reel : la feuille
   de `s21-marc` a `0e0bc4e`, reprise sans y changer un caractere, fait rougir le garde-fou corrige
   et le laisse vert quand le correctif est neutralise (mutation reproduite, arbre restaure et
   verifie identique).

2. **Le correctif du point 1 a lui-meme casse la porte a la fusion complete.** `theo-archi`
   avait durci `valeurDeclaree` en ne convertissant que deux de ses trois appelants. Le troisieme
   (`LeConteneurTierGardeSonRembourrageLateralTest`) ne casse que lorsque `.proposition` porte deux
   declarations de rembourrage - un materiau qui n'existait sur aucun des deux arbres separes
   (`s21-theo`, qui portait le lecteur strict sans le materiau ; `round-two` d'avant fusion, qui
   portait le materiau sans le lecteur strict), seulement sur l'etat fusionne
   (`correctif-porte-rouge-troisieme-appelant.md`). `theo-archi` le rapproche lui-meme du `-pl` sans
   `-am` de `CLAUDE.md` : un chiffre juste, mesure sur un autre arbre que celui qu'on croit mesurer.
   Le correctif retire la piece qui permettait l'erreur (la position devient calculee par la
   fonction elle-meme, plus passee par l'appelant) plutot que de convertir le seul appelant fautif -
   26 appels sur 12 classes verifies par le compilateur, pas par un recensement.

3. **`campagne-budget-vertical.sh` ecrivait "campagne terminee" en ayant perdu 8 jeux sur 17.** Le
   script ignorait le code de sortie de chaque mesure individuelle. `theo-archi` (`S21-1`,
   `correctif-outillage-de-mesure-s21-1.md`, section 2.4) le nomme lui-meme comme la cause reelle -
   "cette ligne-la, et non les trois juges" qu'il avait par ailleurs corriges. La campagne compte
   desormais ses mesures, nomme les jeux perdus un par un et sort en erreur.

4. **Deux outils de mesure du pli perdaient silencieusement leur condition de mesure.**
   `mesurer-candidats-rep-8.py` (`ines-design`) posait sa regle CSS candidate avant la premiere
   partie puis la perdait des la deuxieme, parce qu'une navigation complete recree le `<head>` du
   document et jette tout ce qui y a ete injecte - corrige en s21 (`S21-7`, commit `3daf481`,
   fusionne dans `455875e`) : l'outil repose la regle a chaque debut de partie et refuse en erreur
   s'il ne peut pas prouver sa presence exacte avant d'enregistrer une mesure. `mesurer-un-contenu-
   candidat.py` (`nadia-prof`) retombait silencieusement sur `10px sans-serif` quand la police
   venait d'un element detache du document par htmx entre-temps, rendant des largeurs **3,2 fois
   trop petites, sans un mot d'erreur** (`ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md`,
   section 1) - `nadia-prof` l'a trouve elle-meme en comparant a une valeur deja commitee le matin
   meme, avant de publier aucun chiffre issu de l'execution fautive, et a ajoute une verification de
   calibrage a chaque execution. Meme famille exacte que le point 1 : une regle ou une propriete
   injectee, jamais relue au moment ou elle compte.

5. **Une population d'echantillon a ete lue comme un inventaire pendant l'essentiel du sprint.**
   Les "56 items sous le pli" de `REP-8` portaient sur un echantillon de **167 items vus sur 428
   items reellement servis par 17 paquets, soit 39 %** (`avis-faisabilite.md`, amendement du
   2026-08-27 en fin de journee, methode : somme des `itemsVus` du releve `s21-pli-du-catalogue-
   744x551.json`). Un second chiffre de la meme journee, "122 sur 348 (35 %)", portait sur une
   population differente (81 % de la matiere, 13 paquets sur 17 vus en entier) et n'etait, de l'aveu
   de son propre auteur, "jamais un inventaire" mais un plancher. `nadia-prof` a cloture la question
   le 2026-08-28 en mesurant le **catalogue entier servi, 478 items sur 478, 20 jeux sur 21**
   (`bandit-manchot-additions-cp`, paquet genere non enumere, reste hors de cette mesure) : **64
   items sous le pli, tous CM1** ; le pli est ferme sur les 162 items des 9 jeux CP enumeres, pas
   sur CM1. Aucun des deux echantillons anterieurs n'est repris comme total dans ce rapport.

6. **Un `ADR` cite a tort dans une Javadoc de test commitee.** `PaquetCm1ReperesTest` invoquait
   `ADR-0029` pour justifier "trois propositions par item, jamais quatre" ; `ADR-0029` ne porte pas
   sur le nombre de propositions, il porte sur le decalage d'animation par colonne. Trouve par
   `theo-archi` en balayant les 62 `ADR` du depot et le code (`avis-faisabilite.md`, section
   citee "5. `ADR-0029` mal cite"), corrige dans `S21-4` par `lea-dev` : verifie par moi le
   2026-08-28, `PaquetCm1ReperesTest.java` ne cite plus `ADR-0029` que pour ce qu'il edicte
   reellement (le decalage de colonne), et la clause de nombre porte desormais les trois motifs
   pedagogiques dates de `nadia-prof`.

**Une septieme observation, trouvee par moi en verifiant un chiffre de `nadia-prof` contre sa
propre source primaire, et que je route sans la trancher.** Sa conclusion (`avis-nadia-done-
pedagogique-s21.md`) affirme "les quatre refus prononces ce sprint" ; les fiches de conformite ne
portent que **trois** verdicts `REFUSE` (`grep -n "Verdict : \*\*REFUSE\*\*"` sur les trois fiches,
le 2026-08-28 : un sur `FICHE-bandit-manchot-syllabes-cp.md`, deux sur `FICHE-bandit-manchot-mot-
manquant-cp.md`), et elle ecrit elle-meme, six lignes plus haut dans le meme fichier, "deux refus
sur `cp-mot-manquant`, un sur `cp-syllabes`" - soit trois, pas quatre. Je ne corrige pas son fichier
(hors de mon perimetre, et le contenu qu'il porte n'est pas en cause), je signale l'ecart parce
qu'un chiffre non reconcilie dans un document deja revise deux fois merite d'etre nomme plutot que
repris tel quel.

## 4. Le fait dur du sprint

**La porte technique est verte a 722 tests, 0 echec (section "Verdict" ci-dessus, ma propre mesure
du 2026-08-28), et elle n'a vu aucun des trois refus pedagogiques prononces ce sprint** (section 3,
point 7 : trois `REFUSE`, pas quatre). Les trois motifs etaient tous des motifs de contenu - un
compte de syllabes errone (`SEG-1` trop large), une reponse defendable refusee a tort puis un
distracteur hors champ sur `cp-mot-manquant` - et `mvn verify` etait vert a chacun d'eux, avant
comme apres correction. C'est le meme fait que la section 2 etablit autrement : **vingt-sept
conditions pedagogiques sur les trois paquets, trois seulement partiellement couvertes par un
test.** La porte technique garde la structure (nombre de propositions, absence de doublon, decalage
d'animation, cascade CSS qui ne mord pas moins que la sortie) ; elle ne garde et ne peut garder ni
l'exactitude d'un fait linguistique, ni la decidabilite d'une comparaison, ni un biais de
representation, ni le pli a 744 x 551 (hors de `mvn verify` par construction, mesure par script).
Je ne reclame aucun garde-fou technique sur ces points : `ADR-0028` et le refus deja arbitre de
`lea-dev`/`theo-archi` a s20 etablissent qu'un classifieur heuristique y serait pire qu'une absence
de garde.

## 5. Ce qui reste ouvert, hors de mon perimetre de correction

- **`outils/mvn-jdk21.sh` et `outils/verifier-jdk.sh`** affirment un raccordement entre le fichier
  de toolchains lu par le shell (`${HOME}`) et celui que Maven lit reellement (`${user.home}` de la
  JVM) sans jamais le verifier ; les deux fichiers declarent des JDK differents sur au moins un
  poste de mesure. Route par `theo-archi` (`correctif-outillage-de-mesure-s21-1.md`, section 6),
  hors perimetre de s21 par la decision 4 du planning. Cela n'a change aucun chiffre de ce
  rapport : je mesure la toolchain retenue par Maven dans le journal, jamais la ligne de
  l'enveloppe.
- **La sonde de port de deverminage reste une mitigation, pas une garantie**, de l'aveu meme de
  `theo-archi` (`correctif-outillage-de-mesure-s21-1.md`, section 3) : elle interdit de s'attacher a
  un port deja tenu, elle n'interdit pas la course entre deux forcages simultanes du meme port.
- **Divergence non reconciliee entre deux releves de `nadia-prof` sur `cm1-reperes`** (14/16 items
  au-dessus du pli dans un releve, 16/16 dans un autre, outils differents). `nadia-prof` le signale
  elle-meme et precise ne s'en servir pour bouger aucun verdict. Je le releve pour memoire, sans le
  rouvrir.
- **`REP-8` reste ouverte sur CM1** : 64 items sous le pli sur le catalogue entier, concentres sur
  quatre jeux (`accord-sujet-verbe-cm1`, `reperes-cm1`, `grands-nombres-cm1`, `accords-groupe-
  nominal-cm1`). Ce n'est pas un garde-fou technique manquant, c'est un volume de contenu a
  retravailler ; route a `camille-po` par `nadia-prof`.

## Decisions prises

Aucune. Ce rapport mesure et instruit ; il ne tranche aucune question de produit, d'architecture ou
de pedagogie. La seule chose que je verse a la gouvernance est l'ecart de comptage de la section 3,
point 7 - un signalement, pas une decision, et il ne cree, n'amende et ne clot aucun `ADR` ni `DEC`.

## Compte rendu court

**Porte technique : verte.** `bash outils/mvn-jdk21.sh -B clean verify` sur le reacteur complet,
`8ecf44d`, `2026-08-28` : `BUILD SUCCESS`, **722 tests, 0 echec** (methode : somme des blocs
`Results:`, verifiee module par module, confirmee independamment du commit de fusion et du rapport
de `theo-archi`). **30 regles ArchUnit reelles**, inchange par le sprint. Catalogue a **21
entrees**, matrice a **18/43 (41,9 %)**, verifies contre leurs fichiers sources.

**Six instruments de mesure ou garde-fous ont ete trouves defaillants ce sprint, aucun par lecture
d'un rapport vert** : un garde-fou CSS qui lisait la mauvaise declaration d'une propriete cascadee,
son propre correctif qui a casse un troisieme appelant a la fusion complete (materiau qui
n'existait que sur l'arbre fusionne), un script de campagne qui ignorait des codes de sortie, deux
outils de mesure qui perdaient silencieusement leur condition d'injection entre deux parties, et un
`ADR` mal cite dans une Javadoc de test commitee. Tous corriges dans ce sprint sauf l'enveloppe
`outils/mvn-jdk21.sh`, explicitement hors perimetre et routee.

**Fait dur** : la porte technique verte a 722 tests n'a vu aucun des trois refus pedagogiques du
sprint (et non quatre - ecart de comptage trouve dans la conclusion de `nadia-prof` en confrontant
son chiffre a ses propres fiches, signale sans etre corrige par moi). Vingt-sept conditions
pedagogiques sur les trois paquets neufs, trois seulement partiellement couvertes par un test - le
reste n'a et ne doit avoir aucun garde-fou automatique, `ADR-0028` et le precedent de s20 l'ayant
deja etabli.
