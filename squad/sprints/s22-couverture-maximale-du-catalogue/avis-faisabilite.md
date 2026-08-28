# Avis de faisabilite - s22 (ouverture)

- **Auteur** : `theo-archi`. **Date** : 2026-08-28. **Branche** : `s22-theo`, worktree dedie,
  ouverte depuis `round-two` a `f0d5d5a`.
- **Demande** : `sofia-sm`, a l'ouverture de s22. `camille-po` ecrit `DEC-0053` en parallele sur
  `s22-camille` ; cet avis ne presuppose pas son perimetre et le dit chaque fois qu'il s'appuie sur
  une hypothese de story.
- **Ce que ce document est** : un avis technique. Il ne redige aucune story, n'en repriorise
  aucune, et ne prononce aucun Done, ni technique ni pedagogique.
- **Nom du repertoire de sprint** : choisi par moi faute de perimetre ecrit a l'heure ou j'ecris.
  `sofia-sm` s'aligne dessus ou le renomme, sans que rien de ce fichier n'en depende.

---

## 0. La porte technique, mesuree aujourd'hui

Mesure faite avant tout jugement, sur le worktree `s22-theo` a `f0d5d5a`, reacteur complet :

```
bash outils/mvn-jdk21.sh -B clean verify
```

**`BUILD SUCCESS`, 722 tests, 0 echec.** Repartition lue sur les six lignes `Results:` du journal
de build, dans l'ordre du reacteur : **49** `domain`, **13** `application`, **332** `adapter-web`,
**224** `adapter-content`, **98** `bootstrap` surefire, **6** `bootstrap` failsafe. Total identique
a celui de la cloture de s21 : ce worktree n'ajoute aucun code.

**Sur quelle machine virtuelle.** Les tests et la compilation tournent sur le JDK de la toolchain,
que `maven-toolchains-plugin` resout et nomme en clair a chaque module (`Found matching toolchain
for type jdk`, version **21.0.10**). La machine virtuelle de Maven, elle, est un **25.0.2** - et
c'est exactement l'obstacle 23, reproduit sans le chercher : la premiere ligne du journal est
`Maven lance sur le JDK de la toolchain jdk 21 : 25.0.2`, une phrase qui se contredit elle-meme.
Voir section 4.2.

**Les deux juges purs de l'outillage**, avec les globs de l'etape CI, le meme jour sur le meme
arbre :

```
bash outils/mesurer-rendu-test.sh
bash outils/verifier-jdk-test.sh
```

Verts tous les deux, message final `Tous les cas passent` dans chacun.

**Compte de regles ArchUnit : 30, inchange.** Methode, dans le meme paragraphe que le chiffre :
**29** annotations `@ArchTest` comptees sur les deux seuls repertoires qui portent des regles
(`bootstrap/src/test/java/com/recreo/archi/` et
`adapters/adapter-web/src/test/java/com/recreo/adapter/web/archi/`), **plus 1** `ArchRule` executee
par un `.check()` explicite sans `@ArchTest` (`ScanDeComposantsTest`) - le piege que
`squad/README.md` section 4 nomme depuis s06. **Explicitement exclues** du compte : les deux
occurrences de la chaine litterale `"ArchRule "` dans `LesGardeFousSontMisALEpreuveTest`, qui sont
le motif de recherche du meta-garde-fou et non des regles ; et les deux classes de contre-exemple
sous `src/test/resources/`, qui ne sont pas du code du depot mais du materiau fautif.

---

## 1. L'arbitrage du pli : ce qu'il touche dans le depot, et ce qu'il ne touche pas

L'utilisatrice tranche le 2026-08-28 que **le pli sort de la porte pedagogique**, pour tout le
catalogue, paquets neufs compris. `nadia-prof` ne refuse plus sur le motif "indice sous le pli"
(`REP-8`).

**Ma reponse tient en une phrase, et le reste de cette section la prouve :** cet arbitrage porte sur
un **critere de validation pedagogique** et il ne rend **aucune** regle executable du depot sans
objet, parce qu'**aucune regle executable du depot ne garde le pli** - c'est `ADR-0028` qui a
decide qu'il n'y en aurait pas, a s08, le 2026-08-11, et ce refus n'a jamais ete rouvert depuis.

### 1.1 Methode de l'inventaire, avant son resultat

Je n'ai pas repris la liste que `sofia-sm` me propose : elle m'annonce elle-meme qu'elle n'est ni
limitative ni verifiee. J'ai derive la mienne, et je donne les commandes parce qu'une verification
annoncee sans sa methode est sanctionnee comme un chiffre sans sa methode (`squad/README.md`,
section 4, amendement s16).

1. **Recherche dans tout le code** de ce qui pourrait parler du pli ou du budget vertical :

   ```
   grep -rlE "\bpli\b|budget vertical|\b551\b|\b640\b" --include=*.java --include=*.sh \
        --include=*.py --include=*.css --include=*.html .
   ```

   **31 fichiers**, dont **4** dans `target/` (copies de build) et **3** arbres de contre-exemple :
   restent **24 fichiers**, ouverts un a un - **9** de Java, **1** feuille de style maison, **14**
   d'outillage de mesure.

   **Aucun des 9 fichiers Java ne porte une assertion dont le pli soit l'objet.** Le mot y apparait
   dans des Javadoc (motif historique, ou limite declaree renvoyant a `ADR-0025` et `ADR-0028`),
   dans **deux messages d'echec dont l'assertion porte sur autre chose** - le nombre de propositions
   d'un item pour `PaquetCm1ReperesTest`, une valeur de plafond de largeur pour
   `UnPlafondMaisonNAffamePasLaRangeeTest` - et dans **un motif d'exemption** ou il est entre
   parentheses (`LeRangDeLaBonneReponse...`, entree `cm1-reperes`). La table 1.2 les classe.

2. **Recherche dans les seules regles ArchUnit**, avec bornes de mot :

   ```
   grep -rniE "\bpli\b|\bplis\b|budget vertical|\bhauteur\b|\b551\b|\b640\b|viewport" \
        --include=*.java bootstrap/src/test/java/com/recreo/archi/ \
        adapters/adapter-web/src/test/java/com/recreo/adapter/web/archi/
   ```

   **Zero ligne.** Aucune des 30 regles ArchUnit ne parle du pli, ni de pres ni de loin.

   **Pourquoi les bornes de mot sont dans la commande et pas seulement dans ma tete.** `grep -rni
   "pli"` sur les deux memes repertoires rend **26 lignes**, **toutes fausses** : `Application`,
   `lApplication`, `multiplication` contiennent la sous-chaine `pli`. C'est litteralement le defaut
   de s05 - une detection de `!important` qui trouvait un nom de variable de la bibliotheque - et il
   se serait rejoue ici, sur ma propre verification, si je l'avais ecrite en prose au lieu de
   l'ecrire en commande. Vingt-six lignes auraient meme pu se lire comme une confirmation de la
   these inverse.

3. **Lecture des `ADR` candidats**, en entier et pas par leur titre : `ADR-0028` (le seul dont le
   pli est l'objet), `ADR-0025`, `ADR-0063`, `ADR-0017`, `ADR-0029`, `ADR-0050`.

4. **Inventaire de l'outillage** : les 4 repertoires de `squad/outils/` et `outils/`, listes
   integralement.

### 1.2 Ce que le depot porte reellement, et ce que l'arbitrage en fait

| Element | Ce qu'il juge reellement | Motif du pli ? | Statut apres l'arbitrage |
|---|---|---|---|
| Les **30 regles ArchUnit** | bytecode : couches, purete, contrats, mecaniques | **non**, aucune | **Intactes.** Aucune n'a jamais eu le pli pour objet |
| `ADR-0028` geste 2 | rien : c'est un **refus** ecrit d'automatiser le pli | oui, c'est son objet | **Intact.** On ne retire pas un refus quand la propriete refusee cesse d'etre exigee. Son declencheur de reprise (un conteneur de build a polices epinglees) est inchange |
| `ADR-0028` geste 1 : `outils/mesurer-rendu.sh`, 4e argument | l'invocation d'une capture, pas un rendu | oui | **Intact.** C'est un instrument de mesure, pas un critere. Un instrument ne devient pas faux parce qu'une porte cesse de lui demander un chiffre |
| `outils/mesurer-rendu-test.sh` | le juge pur de l'invocation ; **7** de ses appels portent sur la hauteur (`grep -c "^verifier_hauteur " outils/mesurer-rendu-test.sh`, le 2026-08-28) | oui | **Intact et necessaire pour un autre motif** : il garde aussi la largeur et le plancher de fenetre. Sa mutation la plus mordante (`-gt` en `-ge`) casse **l'invocation par defaut**, celle qui ne demande aucun pli (`ADR-0028`, table des mutations) |
| `AuPlusTroisPropositionsParItemTest` (`ADR-0063` regle 1) | le **nombre de propositions** rendu par le chargeur reel | motif d'ouverture seulement | **Conserve, motif independant.** `ADR-0063` decision 1 ecrit deja *"Cette regle ne ferme pas `REP-8` et ne pretend pas le faire"*. Elle interdit le repli a quatre colonnes ; le cliquet exact de 2 paquets / 33 items n'a aucun rapport avec la porte pedagogique |
| `PaquetCm1ReperesTest`, `PROPOSITIONS_PAR_ITEM = 3` | trois propositions sur un paquet servi | **1 motif sur 3** | **Conserve.** Les deux autres motifs de `nadia-prof` survivent tels quels : a quatre, le pool de cinq libelles rend l'item soluble par elimination (`REP-4`) ; a deux, l'enfant a une chance sur deux sans connaitre la distinction |
| `LActionNeDeclarePasMoinsQueLaSortieTest` (`ADR-0017`) | la **hierarchie de deux boutons d'un meme ecran** | **non** | **Intact, et le lien va dans l'autre sens.** Il lit `padding`, `min-height`, `font-size` - les leviers que `S21-2` a bouges pour fermer le pli. Ce n'est pas un garde-fou du pli, c'est le garde-fou qui **borne les correctifs de pli**. Il mord davantage, pas moins, quand la contrainte se relache |
| `ChaqueColonneQueLeContenuPeutProduirePorteSonDecalageTest` (`ADR-0029`) | un `animation-delay` declare par colonne | **non** | **Intact.** Aucun rapport avec le pli. Il est cite dans la question parce qu'il porte le **meme angle mort de lecture** qu'`ADR-0017` avait (obstacle 14), pas la meme matiere. Voir 4.3 |
| `TailleDesTextesEtBoutonsTest`, plancher 1,25rem (`ADR-0050` clause 1) | toute taille de texte declaree par la feuille maison | **non, l'inverse** | **Intact, et c'est le point a ne pas manquer.** Un plancher de taille de texte est ce qui **coute** des pixels verticaux. Il s'oppose au pli, il ne le sert pas. Rien dans l'arbitrage ne l'affaiblit |
| `LaLargeurOffreeAUneRangee...`, `LaLargeurDemandeeParUneRangee...`, `UnPlafondMaisonNAffamePasLaRangee`, `ViewportEtLargeurTelephoneTest` | des **largeurs** | non | **Intacts.** Le pli est une hauteur. La largeur de reference `744` continue de gouverner ces quatre regles, arbitrage ou pas (`DEC-0040`) |
| `PaquetCm1SynonymesTest`, `ToutEcranEchangeEstUnDocumentJouableTest`, `LaConsigneDUnJeuServiAtteintLEcranDeMancheTest` | leur objet propre ; le pli n'apparait que dans une Javadoc, comme motif historique ou comme **limite declaree** | non | **Intacts.** Aucun n'assertionne quoi que ce soit sur une hauteur |
| `squad/outils/nadia-prof/` : `lire-le-pli-a-l-ecran.py`, `compter-le-pli-du-catalogue-entier.py`, `mesurer-un-contenu-candidat.py`, `budget-de-forme.py` | des mesures de rendu | oui | **Intacts, propriete de `nadia-prof`.** Un outil de mesure qui perd un appelant reste juste. Je ne propose de supprimer aucun outil d'un autre agent, et ce serait de toute facon hors de mon mandat |
| `squad/outils/ines-design/mesurer-candidats-rep-8.py` | idem | oui | **Intact, propriete d'`ines-design`** |
| `squad/outils/theo-archi/mesurer-budget-vertical.py` et `campagne-budget-vertical.sh` | le deficit vertical sur l'application servie | oui | **Intacts, et ils sont a moi.** Je les garde : ils sont le seul moyen dont je dispose pour **chiffrer** la dette croissante que l'utilisatrice accepte explicitement d'ouvrir. Un chiffre reste utile quand il cesse d'etre bloquant |

### 1.3 Les trois categories demandees, sans arrondir

**Sans objet.** *Aucun garde-fou executable.* Deux elements **non executables** perdent leur
declencheur, et ni l'un ni l'autre n'est un test :

1. **La regle 4 du contrat de forme** (`ADR-0063` decision 3) : *"aucun item ne laisse l'indice ni
   le verdict sous le pli a 744 x 551"*, **etape de revue avant fusion, jamais un test**. C'est le
   seul enonce du depot formule directement en resultat de pli. Il ne se supprime pas - il n'est
   deja execute par rien - il cesse simplement d'avoir un demandeur. Je ne le retire pas de
   `ADR-0063` : un `ADR` accepte ne se reecrit pas, il s'amende avec sa date, et l'amendement
   revient au sprint qui constate le changement, pas a cet avis.
2. **`COMP-4`, `COMP-7` et le premier des deux motifs de `COMP-6`** de
   `FICHE-bandit-manchot-comprehension-cm1.md`. Ce sont des **conditions pedagogiques de
   `nadia-prof`**. Je les nomme parce qu'elles bloquent `S17-2` et que le planning doit le savoir ;
   **je ne les leve pas**, et personne d'autre que `nadia-prof` ne le peut. Voir 3.3.

**Conserves pour un autre motif que le pli.** `AuPlusTroisPropositionsParItemTest` (le repli a
quatre colonnes, le cliquet exact) ; `PaquetCm1ReperesTest` (deux des trois motifs) ;
`LActionNeDeclarePasMoinsQueLaSortieTest` et `ADR-0017` (hierarchie de boutons, jamais le pli) ;
`ChaqueColonneQue...Test` et `ADR-0029` (animation) ; le plancher de lisibilite d'`ADR-0050` ; les
quatre garde-fous de largeur ; les deux gestes d'`ADR-0028` ; tout l'outillage de mesure.

**Garde-fous morts, verts par construction.** **Cet arbitrage n'en cree aucun.** Il ne peut pas en
creer, pour une raison structurelle : un garde-fou meurt quand la population qu'il examine se vide
ou quand la condition qu'il teste devient impossible ; retirer un **critere de jugement humain** ne
fait ni l'un ni l'autre. Aucun test du depot n'a le pli pour condition.

**Le depot dit deja cela de lui-meme, et je le cite plutot que de le reformuler.**
`UnPlafondMaisonNAffamePasLaRangeeTest`, Javadoc de classe, lignes 76-77 : *"Il ne dit rien du
budget vertical, dont depend le fait que le verdict passe au-dessus du pli, et **aucun garde-fou de
ce depot n'en dit rien non plus**."* C'est une affirmation ecrite avant l'arbitrage, dans un fichier
commite, par un lot qui n'avait aucune raison de la flatter - et elle concorde avec mes deux greps.

**Mais le depot en porte deja cinq a moitie morts, aujourd'hui, pour une cause qui n'a rien a voir
avec l'arbitrage - et c'est le sprint de volume qui les rend dangereux.** Cinq garde-fous de contenu
bornent leurs tirages a **60 items par paquet**, valeur ecrite a la main : ils sont verts sur les
60 premiers items et **muets sur le reste**. L'obstacle 16 n'en nomme qu'un ; j'ai mesure les
quatre autres aujourd'hui. Section 4.1.

### 1.4 Ma reponse a la position de `sofia-sm`

> *"Retirer un critere de validation pedagogique n'autorise personne a supprimer une regle
> technique, et un garde-fou qui ne mord plus est plus dangereux qu'un garde-fou absent."*

**Je ne la contredis pas, je la confirme et je la precise sur un point ou elle est trop courte.**

Sur le fond : d'accord, et la separation des mandats suffit a le fonder sans argument de prudence.
Un critere pedagogique est prononce par `nadia-prof` sous l'autorite de l'utilisatrice ; une regle
technique est actee par un `ADR` et executee par un test. Les deux ne sont pas dans la meme chaine
de decision. **Le seul geste qui retire une regle technique est un `ADR` qui l'acte** - et je n'en
propose aucun ici, parce qu'aucune regle technique n'est concernee.

**La precision.** Le risque de ce sprint n'est pas qu'on supprime une regle : ce serait visible dans
un diff, donc c'est le risque le moins grave. Le risque est qu'on **reinterprete le motif** d'une
regle qu'on garde, et qu'on la relache par le contenu au lieu de la supprimer par le code. Le depot
en porte deux instances mesurables aujourd'hui :

- `PaquetCm1ReperesTest` : **trois** motifs pour `PROPOSITIONS_PAR_ITEM = 3`, dont un seul est le
  pli. Lire "le motif est tombe" au singulier laisserait passer quatre propositions sur un paquet
  dont le pool de libelles n'en compte que cinq - un item soluble par elimination, ce que
  `nadia-prof` a nomme `REP-4` et qui n'a rien a voir avec une hauteur.
- `COMP-6` : **deux** motifs, dont un seul est le budget vertical. Le second - `.consigne` et
  `.texte-support` sont rendues a l'identique, donc un CM1 ne sait pas ou l'histoire commence -
  survit entierement a l'arbitrage. C'est de la lisibilite, pas de la hauteur.

C'est exactement ma propre lecon, celle des `@ExceptionHandler` : **un perimetre enumere par types
echoue par inclusion autant que par omission.** Ici la meme faute prend la forme d'un motif enumere
en un seul exemplaire pour une regle qui en porte trois.

**Et le symetrique, que la position de `sofia-sm` ne couvre pas.** Garder un enonce en prose dont le
demandeur a disparu est aussi une facon de mentir : la regle 4 du contrat de forme est ecrite dans
`ADR-0063` et dans `docs/backlog/backlog.md` comme une porte de revue. Personne ne l'execute. Si
elle reste citee sans etre datee de sa perte de declencheur, le prochain lecteur la lira comme une
contrainte en vigueur. **Un enonce non executable qui a perdu son demandeur porte sa date, il ne
disparait pas et il ne se tait pas non plus.** Je route l'amendement au sprint, pas a cet avis.

### 1.5 Ce que je propose de retirer

**Rien.** Aucun `ADR` de retrait n'est necessaire, et je n'en ouvre aucun.

---

## 2. Ce que l'arbitrage laisse ouvert, et qui n'est pas de moi

Trois consequences techniques, chacune routee, aucune tranchee ici.

1. **Plus rien ne borne la longueur d'un libelle ni celle d'un enonce**, et ce n'etait deja pas le
   cas avant l'arbitrage : `ADR-0063` decision 3 refuse d'encoder les regles 2 et 3, avec les
   chiffres de `nadia-prof` (la regle 2 ecarterait 35 items dont `cm1-reperes` 16 sur 16 ; la
   regle 3 en ecarterait 80 dont `cp-problemes-additifs` 22 sur 22). Ce que l'arbitrage change,
   c'est que la **mesure humaine** qui tenait lieu de garde disparait aussi. Il ne reste donc, sur
   la forme verticale d'un ecran, **ni test ni regard**. Ce n'est pas une objection - l'utilisatrice
   l'a explicitement accepte, "meme si ca peut ouvrir une dette croissante" - c'est un fait a
   ecrire pour qu'il ne soit pas decouvert plus tard comme une surprise.
2. **La dette devient non chiffree si personne ne la mesure.** Elle reste **chiffrable a la
   demande** : `squad/outils/theo-archi/campagne-budget-vertical.sh` la produit sur le catalogue
   servi sans rien injecter. Je propose, sans le decider, qu'une mesure de ce genre soit relancee a
   une cloture de sprint et son releve commite - c'est le seul moyen de savoir de combien la dette
   a grandi le jour ou quelqu'un voudra le savoir. **A trancher par `camille-po` et `sofia-sm`** :
   c'est un cout de ceremonie, pas une regle d'architecture.
3. **`DEC-0016` est la decision que cet arbitrage amende, et c'est une `DEC`, pas un `ADR`.** Elle
   fixe le viewport de reference et la propriete "visible sans defilement", et elle appartient a
   `camille-po`. Je ne la touche pas. Je signale seulement qu'elle est aujourd'hui la seule source
   ecrite de la propriete, et qu'un arbitrage qui la vide sans l'amender laisserait le depot avec
   une decision produit en vigueur que plus personne n'applique. **Route a `camille-po`, dans
   `DEC-0053` ou ailleurs.**

---

## 3. Faisabilite des trois paquets candidats

Hypothese explicite : `S13-2`, `S13-3`, `S17-2` sont les candidats de tete. **A confirmer contre
`DEC-0053`.** Si `camille-po` en retient d'autres, seule la section 3.3 est specifique.

### 3.1 `S13-2` - maths CM1, multiplier et diviser par 10 et 100

**Faisable en l'etat, sans une ligne de Java de production.** Verifie, pas suppose :

- **Le schema porte les items.** `QuestionContentSourceFactory` lit `enonce`, `bonneReponse`,
  `distracteurs`, et trois optionnels (`indice`, `explication`, `partDesignee`). Les dix conditions
  `MD-1` a `MD-10` de l'arbitrage de `nadia-prof` du 2026-08-15 ne demandent aucun champ qui
  n'existe pas.
- **L'arbitrage prealable est rendu et le verrou de `DEC-0030` est leve** (fiche
  `FICHE-bandit-manchot-multiplications-divisions-cm1.md`, bloc de validation). Le code peut
  commencer.
- **Les valeurs sont numeriques**, donc le paquet entre dans le domaine du garde-fou de rang et n'a
  pas a etre declare dans `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE`. La lecture d'`ADR-0041` couvre les
  entiers et les separateurs de milliers ; la condition `MD` de `nadia-prof` sur le rang (20 % a
  40 % par rang) est **plus stricte** que le seuil de la CI, donc un paquet qui la tient passe la
  CI par construction.
- **Livrable** : un fichier YAML sous `contenu/`, une entree dans `catalogue.yaml`, et une classe
  `PaquetCm1MultiplicationsDivisionsTest` sous `adapter-content` - c'est la convention de tous les
  paquets du depot, pas une exigence neuve.

**Avis : faisable en l'etat.** Aucun `ADR` requis.

### 3.2 `S13-3` - maths CM1, problemes de durees

**Faisable en l'etat, sans code neuf - et l'obstacle technique que `nadia-prof` avait signale avant
le code est ferme depuis s13.**

L'arbitrage du 2026-08-15, section 3.6, signalait que les deux garde-fous de rang lisaient
**l'entier de tete** d'une proposition, donc classaient `1 h 30` sous `45 min`. **C'est corrige** :
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` porte depuis `ADR-0041` deux motifs
dedies, une duree ou un instant en heures et minutes (`1 h 30`, `1 h 30 min`, `2 h`) et une duree en
minutes seules (`45 min`), le premier evalue **avant** le motif numerique generique precisement pour
que `1 h 30` ne soit pas lu `1`. La mise a l'epreuve `...Durees...` existe et cite nommement
l'arbitrage de `nadia-prof`. Le fichier declare aussi le motif qui rend la comparaison legitime :
`D-3` interdit de franchir minuit, donc un instant du jour s'ordonne en minutes depuis minuit comme
une duree.

**Avis : faisable en l'etat.** Aucun `ADR` requis. **Une reserve a porter au planning, qui n'est pas
un blocage** : `D-2` (un instant et une duree ne s'ecrivent pas de la meme facon) reste une regle de
redaction du contenu, gardee par personne d'autre que `nadia-prof`. Je ne propose pas de l'encoder :
un garde-fou qui deciderait, en lisant `2 h`, s'il s'agit d'un instant ou d'une duree, devrait
comprendre l'enonce.

### 3.3 `S17-2` - francais CM1, comprehension de texte

**Infaisable en l'etat sans code neuf, et le pli n'y est pour rien.** C'est le point ou une lecture
trop large de l'arbitrage ferait le plus de degats, et je le prends dans l'ordre : ce que le pli
debloque, puis ce qu'il ne debloque pas.

#### Ce que l'arbitrage debloque reellement

`nadia-prof` avait mesure le 2026-08-19, sur le rendu servi, que la mise en page composee de
`S17-2` ne laissait au texte-support **que 4 lignes / environ 20 mots** dans l'assemblage le plus
favorable (marge de 4,81 px), et **aucune ligne** dans l'assemblage a consigne de 2 lignes,
question de 2 lignes et 3 propositions de deux mots. Elle en tirait, sans se dedire : a 20 mots on
peut mettre un reperage litteral, pas une inference - c'est-a-dire le paquet qu'elle venait de
refuser. `COMP-3` (un tiers d'items reliant deux informations) n'etait pas atteignable.

Ce plafond etait **entierement derive du pli**. Il tombe avec lui. `COMP-4` (tout visible a
360 x 640 sans defiler), `COMP-7` (le seul chiffre qui gouverne est `y <= 640` pour le bas de la
derniere proposition) et le premier motif de `COMP-6` (gagner 30 px) perdent leur objet. **Mais
c'est `nadia-prof` qui prononce cette levee, pas moi et pas `sofia-sm` :** ce sont ses conditions,
elles sont bloquantes, et elles sont ecrites dans une fiche de conformite. Sans son geste ecrit,
`S17-2` reste bloquee quoi que dise le planning. **Je le route comme prealable de sprint.**

#### Ce que l'arbitrage ne debloque pas - les contraintes techniques reelles qui restent

Cinq, verifiees sur l'arbre d'aujourd'hui. Aucune n'est une contrainte de hauteur.

1. **Le champ `texteSupport` n'existe pas.** Verifie :
   `grep -rn "texteSupport" --include=*.java --include=*.html --include=*.css --include=*.yaml .`
   rend **trois** lignes, toutes des commentaires Javadoc qui annoncent le champ au futur ; aucune
   n'est du code. `Question` porte sept composantes et pas celle-la. **C'est la dette n°1 de
   `CLAUDE.md` en train de s'ecrire** - une abstraction citee partout, implementee nulle part - et
   `ADR-0050` l'avait deja anticipee.
2. **La chaine coute cinq fichiers dans quatre modules**, `ADR-0050` l'a chiffree et je la
   reverifie : `Question` (`domain/content`), `BanditManchotSession` (`domain/mechanics`),
   `QuestionContentSourceFactory` (`adapter-content`), `PartieController` (`adapter-web`),
   `fragments/manche.html` plus `recreo.css` (`adapter-web`). **Le precedent exact existe** :
   `partDesignee` (`ADR-0062`, s20) a traverse la meme chaine, et les cinq points de passage sont
   lisibles aujourd'hui. Ce n'est donc pas une conception a faire, c'est un patron a suivre.
3. **`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` deviendra rouge des l'ajout du champ, et
   c'est voulu.** Son perimetre est derive de `Question.class.getRecordComponents()` : toute
   composante portee doit etre classee, dans l'identite d'ecran ou hors d'elle avec son motif. Un
   `texteSupport` ajoute sans classement fait echouer le build. **C'est un garde-fou qui mord
   vraiment**, et il impose la bonne sequence : le champ et son classement dans le meme commit.
   Sans lui, deux items poses sur deux textes differents mais partageant question et propositions
   seraient refuses comme une copie - le faux rouge qu'`ADR-0050` avait nomme.
4. **`ADR-0050` clause 6 impose un test qui traverse la frontiere HTTP**, dans `bootstrap`, sur le
   modele de `LExplicationDUnItemReelAtteintLEcranDeRetourTest`. Motif non decoratif :
   `QuestionContentSourceFactory` **ignore sans un mot toute cle qu'elle ne connait pas** (la
   Javadoc de la classe le dit elle-meme). Une cle `texteSupport:` ecrite dans un YAML avant que la
   fabrique ne la lise laisse le build **entierement vert** et l'enfant devant un ecran sans texte.
   C'est un ecart muet, la famille de defaut que ce depot sanctionne depuis s05.
5. **`COMP-5` survit a l'arbitrage, et c'est la contrainte qu'il ne faut pas lire comme tombee.**
   `nadia-prof` l'a satisfaite le 2026-08-19 par deux signaux **mesurables** - la taille et la
   graisse - et non par un budget. Elle a **retire elle-meme** la parenthese "8 lignes / 45 mots" de
   `COMP-5` et l'a remplacee par `COMP-7`. Ce qui reste de `COMP-5` est donc une regle de
   **lisibilite et de distinction visuelle**, pas de hauteur : elle exige une classe
   `.texte-support` distincte de `.enonce`. **Consequence technique immediate** : cette classe
   declarera une `font-size`, donc elle entre dans le perimetre derive du plancher de lisibilite de
   1,25rem (`ADR-0050` clause 1, `TailleDesTextesEtBoutonsTest`), qui la refusera en dessous. Le
   seul chiffre gardable de la proposition d'`ines-design` est donc **garde**, et il l'est pour un
   motif de lisibilite qui ne doit rien au pli. Le second motif de `COMP-6` (distinguer
   `.texte-support` de `.consigne`, rendues a l'identique aujourd'hui) survit pour la meme raison.

**Une contrainte de largeur, pas de hauteur, qui reste entiere.** `DEC-0051` signale que l'enonce de
`S17-2` integre un texte de deux phrases, plus long que tout enonce du catalogue actuel. Une fois le
pli sorti du jugement pedagogique, **ce qui reste de cette contrainte est une affaire de largeur, et
elle est gardee** : la largeur de reference est `744` (`DEC-0040`), et les quatre garde-fous de
largeur - `LaLargeurOffreeAUneRangee...`,
`LaLargeurDemandeeParUneRangee...`, `UnPlafondMaisonNAffamePasLaRangee`,
`ViewportEtLargeurTelephoneTest` - continuent de mordre exactement comme avant. Et le
**debordement** de mise en page reste declare **non gardable** par `ADR-0025`, avec son declencheur
de reprise inchange. Autrement dit : le pli sort du jugement, la largeur n'en sort pas, et rien de
ce qui garde la largeur n'a bouge.

**Ce qu'il ne faut surtout pas lire dans la phrase precedente**, et c'est la moitie qui compte :
ces quatre garde-fous gouvernent **la rangee de propositions**, pas un paragraphe de texte. Un
texte-support de deux phrases se replie sur plusieurs lignes ; **ce repli n'est garde par rien**, et
son debordement eventuel non plus (`ADR-0025`). Bilan honnete du contrat de forme qui reste sur
`S17-2`, une fois le pli hors du jugement : **une** contrainte executable - la classe
`.texte-support` ne descend pas sous 1,25rem, par un balayage derive de la feuille - et **zero**
contrainte, executable ou humaine, sur la longueur du texte et sur ce qu'il devient a l'ecran. Ce
n'est pas un manque a combler dans ce sprint : `ADR-0063` decision 3 a deja mesure, chiffres de
`nadia-prof` a l'appui, qu'un plafond en caracteres serait un garde-fou dont le verdict n'est pas
celui de la propriete qu'il nomme. **C'est un fait a ecrire, pas un trou a boucher.**

**Avis : `S17-2` est faisable moyennant du code, sans `ADR` neuf.** `ADR-0050` a deja acte la
decision et ecrit ses deux regles ; ce qui manque est son implementation, qui est une story de
developpement, pas une decision d'architecture. **Elle n'est pas une story de contenu seul**, et
elle ne doit pas etre planifiee comme telle : la fiche de conformite dit *"sans code Java neuf autre
que le support de style de `COMP-5`"*, formule qu'`ADR-0050` a deja qualifiee de *"fausse a la
lettre"* le 2026-08-19.

**Ordre impose par le depot, que le planning ne peut pas inverser** : la levee ecrite de `COMP-4`,
`COMP-6` motif 1 et `COMP-7` par `nadia-prof` ; puis le champ et son classement d'ecran dans un
commit ; puis le test HTTP ; puis le contenu. Ecrire les items avant le champ, c'est les ecrire
contre un plafond qui n'existera plus, ou sans savoir s'ils atteignent l'ecran.

### 3.4 Ce qu'un paquet neuf coute en garde-fous, quel qu'il soit

Utile au chiffrage de `sofia-sm`, et vrai pour tout paquet de s22. Un paquet neuf entre
**automatiquement** dans le perimetre derive de tous les garde-fous de contenu - personne n'a a
l'inscrire nulle part - et il doit donc tenir, sans exception negociable : un indice sur chaque
item (`ChaquePaquetPorteUnIndiceTest`) ; plus d'items que de manches (`ADR-0023`) ; aucune
proposition egale a une autre dans un item ; aucun ecran repete dans le paquet (`ADR-0032`) ; au
plus trois propositions par item (`ADR-0063`, et un paquet neuf n'a **aucun** droit d'exemption) ;
une matiere accentuee dans le catalogue (`ADR-0049`) ; le rang de la bonne reponse non predictible,
**ou** une declaration hors domaine avec son motif (`ADR-0051`) si les propositions n'ont pas de
valeur comparable - ce qui sera le cas de `S17-2`.

---

## 4. Les dettes ouvertes a la cloture de s21

### 4.1 Obstacle 16 - **je le prends**, et il est plus large que son enonce

**Ce que l'obstacle dit** : `AucunePropositionNEstEgaleAUneAutreDansUnItemTest` porte la meme borne
d'echantillonnage fixe de 60 que le cliquet corrige a s21.

**Ce que j'ai mesure aujourd'hui, et qui n'etait pas ecrit.** Methode, une seule commande sur
`f0d5d5a` le 2026-08-28, puis lecture de chaque site d'appel :

```
grep -rn "int TIRAGES" --include=*Test.java adapters/ bootstrap/
```

**Neuf lignes. Cinq portent la valeur 60**, et les cinq sont la meme boucle sur le meme patron
(`for (int tirage = 0; tirage < TIRAGES; tirage++)`, tirage sans remise par `dejaVus`, arret sur
`Optional.empty()`) :

| Garde-fou | Ligne |
|---|---|
| `AucunePropositionNEstEgaleAUneAutreDansUnItemTest` | 70 |
| `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` | 96 |
| `ChaquePaquetATrousPorteSonTrouSurChaqueItemTest` | 54 |
| `ChaquePaquetPorteUnIndiceTest` | 56 |
| `LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest` | 81 |

**Les quatre autres lignes, pour que le perimetre soit complet et pas seulement accablant** :
`AuPlusTroisPropositionsParItemTest` (60, mais **reserve aux seuls paquets combinatoires** depuis le
correctif de s21, et le code le declare a cet endroit) ; `ChaqueEntreeDuCatalogueChargeBienSonPaquet`
(**150**, ecrit a la main lui aussi mais superieur au plus grand paquet, donc aveugle a partir de
151 items et pas avant) ; `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManches` (20 par item vise, autre
forme) ; `LeRangDeLaBonneReponse...` (2000, avec une regle d'arret sur absence de nouveaute).

**Portee exacte, mesuree.** Comptage independant des 21 fichiers de
`adapters/adapter-content/src/main/resources/contenu/` le 2026-08-28, une occurrence de
`bonneReponse:` par item : **478 items**, 20 paquets enumeres plus un paquet combinatoire qui
n'enumere rien. **Un seul paquet depasse 60** : `cm1-tables-multiplication`, **99 items**. Les cinq
garde-fous du tableau en examinent donc **60**, et **39 items ne sont examines par personne**, cinq
fois, en silence. Ce total de 478 concorde avec celui que `nadia-prof` a etabli le 2026-08-27 en
jouant les 20 jeux enumeres, par une methode entierement differente - elle joue, je lis les
fichiers.

**Ce que ces 39 items ne sont pas.** Je n'affirme aucun defaut cache : `cm1-tables-multiplication` a
son Done pedagogique et le recomptage exact de s21 sur `ADR-0063` n'y a rien trouve. Ce qui est en
cause est **le silence**, pas un contenu fautif - exactement la distinction que `ADR-0051` a
etablie.

**Pourquoi c'est le sprint de volume qui doit le prendre, et pas le suivant.** Le mode de
defaillance n'est pas l'erreur, c'est le silence - le fil rouge de s21, sept fois. Un sprint dont
la consigne est *"couverture maximale du catalogue"* est precisement celui qui multiplie les
paquets, donc les occasions qu'un paquet depasse 60 items, donc la surface non regardee. Et un
garde-fou de contenu qui lit 60 items sur 99 sans le dire est, mot pour mot, ce que j'ai corrige
dans `AuPlusTroisPropositionsParItemTest` a la cloture de s21. **Laisser quatre freres du meme
defaut ouverts pendant un sprint de volume serait la meme faute, sue et repetee.**

**Ce que je propose d'ecrire, et pourquoi ca demande un `ADR`.** La correction de s21 a ete faite
sans `ADR`, comme un amendement a `ADR-0063` - c'etait juste pour un garde-fou. Cinq sites font une
regle, et une regle non ecrite n'est pas une regle :

> **`ADR-0064` (a ecrire en s22, numero reserve)** - *Un garde-fou qui tire sans remise borne ses
> tirages par ce que le paquet declare, jamais par un nombre ecrit a la main, et un paquet enumere
> lu en partie est un echec nomme.*

- **Ce qu'elle interdirait reellement** a quelqu'un qui essaierait de mal faire : ecrire un
  garde-fou de contenu neuf avec `for (int tirage = 0; tirage < 60; ...)`, ou reprendre par copie le
  patron d'un voisin. Ce n'est pas hypothetique : **six garde-fous du depot bornent encore leurs
  tirages a la main** - cinq a 60, un a 150 - et un septieme le faisait jusqu'a ce que je le corrige
  a la cloture de s21.
- **Encodable, et par un mecanisme qui existe deja dans le depot** : un meta-garde-fou qui balaie
  les sources de test, exactement comme `LesGardeFousSontMisALEpreuveTest` balaie `src/test/` a la
  recherche de la chaine `"ArchRule "` et de `assertThatThrownBy`. Ce n'est pas une `ArchRule` -
  ArchUnit lit du bytecode, et une borne litterale dans une boucle de test n'y est pas une
  propriete lisible - et l'`ADR` devra ecrire ce motif plutot que de laisser croire le contraire,
  comme `ADR-0063` l'a fait.
- **Rapport a `ADR-0051`** : `ADR-0051` traite le cas ou un garde-fou n'examine **aucun** de ses
  objets. Celui-ci traite le cas ou il en examine **une partie sans le dire**. Le second est plus
  dangereux que le premier, parce qu'un compte a zero finit par se remarquer.
- **Cout** : cinq bornes derivees sur le patron deja ecrit et deja eprouve par mutation dans
  `AuPlusTroisPropositionsParItemTest`, plus un meta-garde-fou et sa mise a l'epreuve. Un lot de
  `theo-archi`, pas un lot de developpeur.

**Ce que je ne fais pas** : je n'introduis pas d'abstraction partagee entre les cinq classes tant
que le meta-garde-fou n'existe pas. Cinq boucles derivees se lisent ; un lecteur partage de cinq
classes est exactement ce qui a produit l'obstacle 13.

### 4.2 Obstacle 23 - **je le prends**, il est petit et il s'est reproduit sous mes yeux

`outils/mvn-jdk21.sh` lit `${HOME}/.m2/toolchains.xml`, ou `HOME` est la variable du shell POSIX ;
Maven resout sa toolchain dans `${user.home}/.m2/toolchains.xml`, ou `user.home` est la propriete
de la machine virtuelle Java, donc le profil utilisateur de la machine. Sur un poste ou les deux
ne coincident pas, l'enveloppe pose un `JAVA_HOME` qui n'est pas celui que Maven emploiera, **et
elle l'annonce sans le verifier**.

**Reproduit aujourd'hui sans le chercher**, section 0 : la premiere ligne de mon journal de build
dit `Maven lance sur le JDK de la toolchain jdk 21 : 25.0.2`. Une phrase qui nomme la version 21 et
affiche la 25 est une phrase qui se contredit dans sa propre ligne. Le build, lui, est juste : les
lignes `Found matching toolchain for type jdk` de `maven-toolchains-plugin` nomment un JDK 21.0.10
a chaque module, et c'est bien lui qui compile et qui execute les tests. **Aucune consequence sur
la CI ni sur le bytecode livre** - la mesure de s21 le disait deja et je la reverifie.

**Ce qui doit changer** : l'enveloppe verifie ce qu'elle affirme, ou elle n'affirme rien. La
transformation est pure, donc testable par le juge existant `outils/verifier-jdk-test.sh`, dans la
famille qu'`ADR-0009` a etablie. **Cout : un lot court.** Pas d'`ADR` : `ADR-0009` et `ADR-0010`
couvrent deja la doctrine, seule l'implementation ment.

**Motif de le prendre maintenant plutot que de le reporter** : c'est la commande que tous les
agents lancent pour prononcer un Done technique, et elle affiche un chiffre faux dans sa premiere
ligne. Un chiffre faux dans l'outil qui sert a mesurer est le pire endroit ou en laisser un.

### 4.3 Obstacle 14 - **reporte**, declencheur inchange, condition reverifiee

`ChaqueColonneQueLeContenuPeutProduirePorteSonDecalageTest` (`bootstrap`) porte son propre lecteur
prive de feuille, avec la meme forme de trou qu'`ADR-0017` avait : il lit la **premiere** occurrence
d'une propriete dans un corps de regle, pas la declaration qui gagne la cascade.

**Je reverifie sa condition plutot que de la reprendre sur parole** :

```
grep -n "animation-delay" adapters/adapter-web/src/main/resources/static/css/recreo.css
grep -c "animation-delay" adapters/adapter-web/src/main/resources/static/vendor/bootstrap.min.css
```

**Trois declarations dans la feuille maison** (lignes 625, 626, 627), chacune dans une **regle
distincte** avec un selecteur distinct (`:nth-child(2)`, `(3)`, `(4)`), **zero** dans la feuille
vendorisee. **Aucune regle du depot ne declare `animation-delay` deux fois**, donc le trou de
lecture n'a **aucun objet aujourd'hui**, et le corriger ne changerait aucun verdict.

**Reporte, avec le declencheur de s21 inchange et verifiable** : une regle maison qui declare
`animation-delay` deux fois dans le meme corps, **ou** un troisieme module qui se met a lire des
feuilles avec son propre lecteur prive. **J'ajoute un troisieme declencheur, propre a s22** : si un
lot de ce sprint touche `recreo.css` dans la bande des colonnes de propositions, la condition est
reverifiee par la commande ci-dessus avant fusion - c'est un `grep`, pas une story.

### 4.4 Obstacle 25, dette residuelle - **reportee**, declencheur deja ecrit

**Les cinq scripts, nommes**, methode : recherche de la garde `__name__ == "__main__"` sur les
quatre repertoires d'outillage, le 2026-08-28 sur `f0d5d5a` ; cinq fichiers ne la portent pas, tous
dans `squad/outils/nadia-prof/` :

`auditer-un-paquet-entier.py`, `auditer-un-paquet-par-triplet.py`, `chercher-un-signe-orphelin.py`,
`compter-le-pli-du-catalogue-entier.py`, `presser-la-sortie.py`.

**Reportee, et le declencheur d'`ADR-0025` est le bon** : le jour ou l'un d'eux recoit un juge pur
execute par l'etape CI, son corps passe sous la garde comme les autres. Motif de ne pas les
corriger maintenant : deplacer cinq corps de script sans ecrire le juge qui l'exige serait un
changement sans garde, et le depot vient de payer un sprint entier pour l'inverse. Aucun des cinq
n'est charge par un test aujourd'hui, donc la clause d'`ADR-0025` ne les vise pas.

**Une observation que l'arbitrage rend utile, sans changer le verdict** : deux des cinq
(`compter-le-pli-du-catalogue-entier.py`, et `auditer-un-paquet-entier.py` par ce qu'il mesure) sont
des outils de pli. Leur demandeur s'eloigne. Cela ne les rend pas faux et ne justifie ni de les
supprimer ni de les corriger plus vite - ils appartiennent a `nadia-prof`, pas a moi.

### 4.5 Table de synthese des dettes

| Dette | Verdict | Ce qui la ferme, ou son declencheur |
|---|---|---|
| **Obstacle 16** (borne fixe de 60) | **PRISE en s22**, elargie de 1 a **5 garde-fous** | Bornes derivees du paquet + meta-garde-fou de source + `ADR-0064` |
| **Obstacle 23** (`mvn-jdk21.sh`) | **PRISE en s22** | L'enveloppe verifie ce qu'elle affiche ; juge pur existant |
| **Obstacle 14** (lecteur prive de `ADR-0029`) | **REPORTEE** | Une regle declarant `animation-delay` deux fois ; un troisieme module lecteur de feuilles ; **ou** tout lot de s22 touchant la bande des colonnes - reverification par `grep` avant fusion |
| **Obstacle 25 residuel** (5 scripts) | **REPORTEE** | Le premier de ces scripts qui recoit un juge pur execute par l'etape CI (`ADR-0025`) |

**Ce que je ne prends pas et qui n'est pas a moi** : les obstacles 21, 22 et 24 de s21
(4 items sans levier, `REP-8` sur CM1, divergence de deux releves) sont routes a `camille-po` et
`nadia-prof`. L'arbitrage d'aujourd'hui change leur nature - `REP-8` n'est plus un motif de refus -
mais leur destinataire ne change pas et ce n'est pas moi.

---

## 5. L'amendement de la retro de s21 qui m'est adresse

> *"Quand un lot durcit un lecteur partage a plusieurs modules ou plusieurs feuilles, il est mesure
> sur l'union des arbres du sprint avant sa fusion, jamais sur son seul arbre."*

**Je confirme la formulation, elle est la mienne, et je propose son ajout a `squad/README.md`
section 4** - a cote de la methode de comptage et de la methode de mesure de rendu, qui sont de la
meme famille : *sur quoi la mesure a-t-elle porte*.

**Trois precisions, pour qu'elle soit applicable plutot que belle.**

1. **Ce qu'est un "lecteur partage", de facon derivable et non au jugement** : un point de passage
   employe par plus d'une classe de test ou par plus d'un module - une methode `static` importee
   ailleurs, un lecteur de feuille de style, un port de deverminage fixe. Le critere se lit dans un
   diff : *le lot modifie-t-il quelque chose dont un autre fichier depend ?* Sans ce critere, la
   regle se contourne sans mauvaise foi, par simple desaccord sur le mot "partage".
2. **Ce qu'elle achete exactement, et ce qu'elle n'achete pas.** L'obstacle 13 aurait fini par etre
   trouve : la CI sur `round-two` apres fusion l'aurait rougi. **Cette regle ne gagne donc pas une
   couverture, elle gagne du temps et un contexte** - le defaut se trouve pendant que son auteur y
   est encore, pas a la cloture quand sept lots sont deja empiles et que le coupable n'est plus
   identifiable en un diff. Je l'ecris parce qu'une regle dont on surestime la valeur finit par
   etre abandonnee quand elle coute.
3. **Ce n'est pas une regle encodable en `ArchUnit`, et je le dis plutot que de laisser un blanc.**
   C'est une regle de conduite de fusion, pas une propriete du code : elle porte sur **quand** on
   mesure, pas sur ce que le code contient. Son equivalent automatique existe deja et c'est la CI
   sur l'arbre fusionne ; ce que la regle ajoute est de l'anticiper. Elle vit donc dans
   `squad/README.md` comme les autres regles de methode de mesure, pas dans un `ADR`.

**Le geste appartient a `sofia-sm`** : c'est son `README` de process, et l'amendement 1 de sa retro.
Ma confirmation est ce qu'elle attendait ; je n'ecris pas le fichier a sa place.

---

## 6. Synthese pour le planning

| Objet | Avis |
|---|---|
| **`S13-2`** multiplier/diviser par 10 et 100 | **Faisable en l'etat.** Aucun code de production, aucun `ADR`. Schema `question` suffisant, arbitrage rendu, verrou leve |
| **`S13-3`** problemes de durees | **Faisable en l'etat.** Aucun code, aucun `ADR`. L'obstacle de rang signale avant le code est **ferme depuis `ADR-0041`**, verifie |
| **`S17-2`** comprehension de texte | **Faisable moyennant du code**, pas d'`ADR` neuf (`ADR-0050` a deja decide). Le champ `texteSupport` n'existe dans aucun fichier Java. **Prealable non technique** : levee ecrite de `COMP-4`, `COMP-6` motif 1 et `COMP-7` par `nadia-prof`, seule a pouvoir la prononcer |
| **Garde-fous du pli** | **Aucun n'existe.** Aucune des 30 regles ArchUnit, aucun test JUnit. `ADR-0028` avait deja refuse d'en ecrire un. **Rien a retirer, aucun `ADR` de retrait** |
| **Garde-fous devenus morts par cet arbitrage** | **Aucun**, et aucun ne peut le devenir : retirer un critere de jugement humain ne vide la population d'aucun test. Mais **cinq sont deja a moitie muets aujourd'hui**, pour une cause qui n'a rien a voir avec le pli - 39 items sur 99 non examines, section 4.1 |
| **Obstacles 16 et 23** | **Pris en s22**, l'un elargi de 1 a 5 garde-fous et adosse a un `ADR-0064` a ecrire |
| **Obstacles 14 et 25 residuel** | **Reportes**, declencheurs ecrits et conditions reverifiees ce jour |
| **Amendement `README` section 4** | **Confirme**, avec trois precisions d'applicabilite. Geste a `sofia-sm` |

---

## Ce que je route

- **A `camille-po`** : `DEC-0016` reste la source ecrite de la propriete "visible sans defilement"
  et n'est amendee par rien a ce jour (section 2, point 3). Et le choix de remesurer ou non la
  dette verticale a une cloture (section 2, point 2).
- **A `nadia-prof`** : la levee ecrite de `COMP-4`, `COMP-6` motif 1 et `COMP-7` est un prealable
  bloquant de `S17-2`, et elle n'appartient qu'a elle (section 3.3). Je signale, sans rien en
  deduire, que `COMP-5` et le second motif de `COMP-6` ne sont **pas** des conditions de pli et
  survivent tels quels.
- **A `sofia-sm`** : le nom du repertoire de sprint ; l'amendement du `README` section 4 avec mes
  trois precisions ; et le fait que `S17-2` n'est pas une story de contenu seul et ne doit pas etre
  chiffree comme telle.
- **A `ines-design`** : la reserve non bloquante de `nadia-prof` du 2026-08-19 sur le signal visuel
  distinguant `.texte-support` de `.consigne` lui etait deja routee **avec une contrainte de
  4,81 px de budget vertical**. Cette contrainte-la tombe ; la question de forme, elle, reste
  posee.

## Ce que je ne fais pas

- Je ne prononce aucun statut de couverture, aucun Done technique de sprint, aucun Done
  pedagogique.
- Je ne leve aucune condition pedagogique et je n'en interprete aucune a la place de
  `nadia-prof` : je dis seulement laquelle repose sur le pli et laquelle n'y repose pas.
- Je ne redige, ne priorise ni ne chiffre aucune story.
- Je ne retire aucune regle, aucun `ADR`, aucun outil - y compris ceux qui perdent leur demandeur.
- Je n'ecris pas `ADR-0064` dans cet avis : un `ADR` s'ecrit avec sa regle dans le meme geste, et
  ce geste appartient a la story, pas au planning. Le numero est reserve, l'index sera tenu a jour
  a ce moment-la et pas avant.
