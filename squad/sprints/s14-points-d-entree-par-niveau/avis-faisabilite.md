# Avis de faisabilite - theo-archi, sprint 14

- **Auteur** : theo-archi
- **Date** : 2026-08-17
- **Branche** : `s14-theo`, worktree dedie, forkee de `round-two` a `e30566b` ; lignee verifiee par
  `outils/verifier-lignee.sh`.
- **Story instruite** : `S14-1 - Une barre d'entree par niveau, toujours visible dans l'en-tete`
  (`docs/backlog/backlog.md`, section "Sprint 14"), ouverte par `DEC-0032`.
- **Etape 1 du planning** (`squad/README.md` section 9) : cet avis precede tout commit de code de la
  story et l'affectation n'est ferme qu'apres lui. **Il ne remplace pas l'arbitrage prealable de
  `nadia-prof`**, qui reste bloquant et qui porte sur autre chose que la faisabilite.
- **Ce que je ne fais pas ici** : aucune decision produit ni pedagogique, aucun Done pedagogique ni
  son anticipation, aucun choix entre les deux formes chiffrees par `ines-design`, aucune
  repriorisation.
- **Prerequis de mesure** : le `java` du `PATH` de ce poste n'est pas un JDK 21. Toutes les mesures
  citees sont prises sur le **JDK 21.0.10** designe par la toolchain de l'utilisateur, via
  `bash outils/mvn-jdk21.sh`. Chaque mesure porte sa commande a cote d'elle.

## 0. Verdict

**`S14-1` est faisable en l'etat du decoupage, moyennant deux `ADR` - qui sont ecrits, avec leurs
regles, dans le meme commit que cet avis - mais elle n'est pas faisable sous la forme de ses criteres
d'acceptation : trois des huit sont a reecrire, dont un qui invite a contredire `ADR-0006`, et un qui
epingle un nombre destine a pourrir.**

Deux `ADR` neufs, un amendement, et une clause de garde-fou a remplacer :

| Document | Ce qu'il tranche | Sa regle executable |
|---|---|---|
| **`ADR-0043`** (neuf) | un point d'entree permanent porte un troisieme role, `entree`, admis du cote domine de la saillance ; le mot unique reste aux seules sorties | `LActionNeDeclarePasMoinsQueLaSortieTest`, amende, **deux jeux fautifs neufs** - ecrite dans ce commit |
| **`ADR-0044`** (neuf) | le choix des jeux montres vit derriere le port entrant ; le point d'entree est une route, jamais un parametre ; l'en-tete permanent ne porte aucun etat de filtre | **la 29e regle ArchUnit du depot** (`LeChoixDesJeuxResteDerriereLePortTest`) et sa moitie non-bytecode (`LeChoixDesJeuxNeVitPasDansLaVueTest`) - ecrites dans ce commit |
| `ADR-0022`, amende | ce que `S14-1` fait tomber, clause par clause : **rien de cet `ADR`** | aucune regle nouvelle, aucune retiree ; l'amendement est un constat mesure |
| `ADR-0042`, amende | sa regle n'a jamais ete ecrite et son lot porteur a ete annule : dette explicite ouverte, avec declencheur | aucune - c'est le sujet de l'amendement |
| `EnTeteEtMenuTest`, une clause | le comptage fixe a un seul element actionnable dans l'en-tete, a remplacer par une propriete derivee **dans le commit de la barre** | due par le lot, forme specifiee en section 7.1 |

## 1. La contradiction avec `ADR-0022` : ni un amendement de fond, ni un `ADR` neuf sur ce point

`nadia-prof` a raison sur le fait : `recreo.css` porte bien
`body:has(.ecran-de-jeu) .lien-accueil { display: none }`, et la visibilite permanente contredit
l'esprit de l'arbitrage de S6-9. Mais **la question posee - amendement, `ADR` neuf, ou les deux - se
tranche en lisant les clauses, et j'ai relu les cinq une par une plutot que d'affirmer que la decision
"reste valable".**

### 1.1 Ce qui survit, clause par clause

| Clause d'`ADR-0022` | Verdict | Motif, mesure |
|---|---|---|
| **1. Le serveur pose `ecran-de-jeu` sur la zone de contenu** | **survit, et `S14-1` en depend** | c'est le seul levier qui permette a un habillage permanent de dependre de l'etat sans qu'un echange htmx le traverse |
| **2. La forme contractuelle `body:has(.<marqueur>) .<habillage>{display:none}`** | **survit mot pour mot** | la barre **n'y est pas ajoutee** : la visibilite permanente ne coute aucune ligne, c'est l'absence de regle. Mesure : deux puces posees dans l'en-tete, `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest` reste a **4 tests sur 4** |
| **3. Le libelle "Les jeux", un seul mot de sortie** | **survit, et gouverne `S14-1`** | c'est cette clause qui **interdit** de declarer une puce `data-role="sortie"`. Mesure : deux puces `sortie` libellees "CP"/"CM1" font echouer `leLienDeMenuEtLaSortieDeLaZoneDeContenuPortentLeMemeMot`, **et lui seul** |
| **4. `sortie-pleine` conserve tel quel** | survit | aucun rapport avec cette story |
| **5. Un appariement ne confronte que des boutons qu'un couple masquage/marqueur laisse visibles ensemble** | **survit, et retrouve sa population** | `ADR-0022` ecrivait que le mecanisme de traversee de frontiere d'`ADR-0017` "redeviendra portant le jour ou un element permanent portera un bouton visible sur un ecran de jeu". **`S14-1` est ce jour-la**, sans qu'une ligne change |

### 1.2 Ce qui tombe, et ce n'est pas dans `ADR-0022`

La propriete que `DEC-0032` et la fiche attribuent a cet `ADR` - "un ecran de jeu ne porte qu'une
seule sortie visible" - **n'est aucune de ses cinq clauses**. C'est l'arbitrage de `nadia-prof`,
rapporte dans le contexte de l'`ADR`, encode seulement de biais en masquant un element.

La clause **executable** qui tombe est ailleurs, et personne ne l'avait nommee :

```java
Elements liensDeNavigation = page.select("header button, header a[href]");
assertThat(liensDeNavigation)
        .as("DEC-0012 : le menu de l'en-tete contient exactement une entree")
        .hasSize(1);
```

`EnTeteEtMenuTest.leMenuDeLEnTeteContientExactementUneEntreeVersLAccueil`, qui releve de `DEC-0012` et
non d'`ADR-0022`. Mesure du 2026-08-17, deux puces posees dans l'en-tete avec la boite chiffree par
`ines-design`, `bash outils/mvn-jdk21.sh -B -o -pl adapters/adapter-web test` : cette assertion est
**l'un des trois seuls echecs** des 233 tests du module.

**Un comptage fixe n'est pas une propriete : c'est la taille du depot au jour ou il a ete ecrit.** Il
doit etre remplace, dans le commit de la barre, par la propriete derivee de la section 7.1 - un
element de role `sortie` dont le libelle est "Les jeux", plus un point d'entree par niveau distinct du
catalogue.

### 1.3 Reponse a la question posee

- **Amendement d'`ADR-0022`** : oui, mais **de constat** - aucune clause retiree, aucune affaiblie. Il
  est ecrit, avec ses mesures, dans le corps de l'`ADR` et dans `ADR-index.md`.
- **`ADR` neuf** : oui, deux, mais **pas sur la visibilite** - sur les deux questions que la
  visibilite ouvre et qu'aucun `ADR` ne couvrait : le vocabulaire de role (`ADR-0043`) et l'endroit ou
  vit la selection (`ADR-0044`).
- **Une clause de garde-fou a remplacer**, hors `ADR` : celle d'`EnTeteEtMenuTest`.

## 2. `ADR-0038` et `ADR-0042` : ce que les garde-fous voient de ces boutons, et ce qu'ils ne voient pas

### 2.1 Ce qu'ils voient **automatiquement**, sans qu'on inscrive rien

- **Les puces entrent dans l'ordre de tabulation par derivation.** `arretsDeTabulation` selectionne
  `a[href], button, input, select, textarea, [tabindex]` sur le **document assemble**, qui contient
  l'en-tete. Une puce neuve est donc jugee par les clauses 1, 2 et 3 sans qu'aucune liste ne la nomme.
- **Elles deviennent `A[0]` sur tous les ecrans a partie en cours**, l'en-tete precedant la zone dans
  l'ordre du document. La clause 1 exige donc que la requete d'une puce **ne detruise pas** la partie -
  ce que le passage par la confirmation garantit, et c'est exactement le remede que `DEC-0026` et
  `ADR-0038` point 5 benissent. Meme chose pour le rebouclage de la clause 2 depuis la derniere
  proposition en revelation, qui atteindra desormais une puce et non plus le bouton de sortie.
- **Une route neuve rend la suite rouge d'elle-meme.**
  `chaqueRouteDuControleurEstExerceeParCeGardeFou` lit les routes dans `RequestMappingHandlerMapping`
  et exige, par une comparaison **exacte**, que chacune produise au moins un ecran du corpus. C'est la
  reponse a la question sur `ADR-0039` : **le declenchement se derive, l'extension du corpus reste
  manuelle**, et cette asymetrie est voulue - `ADR-0038` l'inscrit dans ses consequences ("un ecran
  qu'aucune requete du corpus ne produit fera rougir la regle, y compris un ecran neuf parfaitement
  correct"). Le lot ne peut pas oublier d'ajouter ses scenarios : la CI le lui dira.

### 2.2 Ce qu'ils ne voient pas, et deux de ces trous sont des pieges concrets pour ce lot

1. **Un parametre de requete achete le silence.** `GET /?niveau=cp` n'ajoute aucune route, donc
   n'ajoute aucune obligation de scenario, donc l'ecran "accueil filtre avec une partie en cours"
   n'entrerait dans aucun corpus et ne serait juge par rien (non-preuve 8 d'`ADR-0038`). **C'est le
   seul motif du point 2 d'`ADR-0044`** : le point d'entree est une route parce que la route est ce
   que le perimetre derive sait voir.
2. **Un masquage qui nomme un conteneur ne masque rien aux yeux du garde-fou.** `ADR-0038`
   non-preuve 3, precision du 2026-08-14 : le masquage est evalue **element par element**
   (`candidat.hasClass(habillage)`). **Consequence directe pour le repli que `ines-design` decrit en
   section 5 de sa proposition** : la regle qu'elle propose,
   `body:has(.ecran-de-jeu) .bandeau-niveaux { display: none }`, nomme le `<nav>` conteneur, pas les
   puces focalisables. `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest` l'accepterait (la classe est bien
   posee par un gabarit), et **`AucunArretDeTabulationNEstDestructeurTest` continuerait de compter les
   puces dans `A`**, donc calculerait un `A[0]` faux, en restant vert. Si le repli est retenu par
   `nadia-prof`, la regle doit nommer **la classe de la puce** (`.chip-niveau`), jamais celle du
   bandeau. Le meme piege vaut pour `ADR-0017` : `ecransOuIlResteVisible` lit lui aussi les classes du
   bouton.
3. **Aucun garde-fou ne verifie que la confirmation s'affiche.** Le predicat mesure un effet sur la
   session ("la partie survit"), pas ce que le serveur a rendu. Une route de niveau qui, pendant une
   partie, ne ferait **rien du tout** serait verte sur les quatre clauses. Le critere 4 de
   `camille-po` a donc besoin de son propre test, et sa forme est en section 7.4.
4. **`ADR-0042` n'existe pas en code.** Mesure sur `e30566b` : `git grep estRemplacant` rend **zero
   occurrence** ; les clauses 1 a 3 appellent `estDestructeur(...)` seul. Son lot porteur, S13-6, a ete
   annule par `DEC-0032`. **`S14-1` ne fait apparaitre aucun arret remplacant** - la confirmation ne
   porte pas de bouton de demarrage, l'accueil filtre ne se rend que sans partie en cours - donc le
   predicat n'y trouverait pas son objet. La dette est ouverte, nommee et bornee par un declencheur
   observable dans un diff, dans l'amendement d'`ADR-0042` ; elle n'est pas de ce lot-ci.

## 3. `ADR-0017` : le vocabulaire de `data-role`, tranche

`ines-design` route la question nommement et propose `sortie`. **Les deux valeurs disponibles ont ete
essayees sur le depot reel** - deux puces dans l'en-tete, la boite qu'elle a chiffree ecrite en regle
maison, `bash outils/mvn-jdk21.sh -B -o -pl adapters/adapter-web -am test`, arbre restaure ensuite :

| Valeur | Mesure |
|---|---|
| `sortie` | **1 echec** : `leLienDeMenuEtLaSortieDeLaZoneDeContenuPortentLeMemeMot`. La clause releve le libelle de **tous** les `[data-role=sortie]` et exige `containsOnly("Les jeux")`. C'est le geste exact par lequel `nadia-prof` a leve sa reserve 3 le 2026-08-07 |
| `action` | **2 echecs sur 11**, dont `aucuneSortieNeDeclarePlusQuUneActionDuMemeEcran` avec **16 ecarts** : quatre ecrans x deux axes de rembourrage x deux puces. Une puce volontairement plus petite que les sorties fait, declaree `action`, de **chaque sortie de chaque ecran** un element qui domine une action |
| `entree` (tranche) | `LActionNeDeclarePasMoinsQueLaSortieTest` **11 sur 11**, `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest` **4 sur 4** |

**Les deux echecs disent chacun quelque chose de vrai, et c'est ce qui interdit de les contourner** :
`sortie` est faux sur le texte, `action` est faux sur la saillance. Une puce de niveau n'est ni ce qui
fait avancer le jeu affiche, ni la sortie de ce jeu. **Verdict : une troisieme valeur, `entree`**,
decidee par `ADR-0043`, dont le garde-fou est etendu dans le meme commit - comme `ines-design`
l'exigeait elle-meme.

**Ce que l'appariement devient** (`LActionNeDeclarePasMoinsQueLaSortieTest`) :

- le cote domine n'est plus le seul `sortie` : `sortie` **et** `entree`. Sans cela, rebaptiser
  n'importe quel bouton `entree` suffirait a le sortir d'`ADR-0017` - le desamorcage a un attribut
  pres qu'`ADR-0038` a deja nomme comme le moyen le moins cher ;
- la puce est donc confrontee, sur chaque ecran ou aucun masquage ne la cache, a **toutes** les
  familles d'action : `.proposition` (`font-size: 2rem`, `padding: 0.75rem 1.25rem`), `.demarrer` et
  `.continuer-partie` (`1.25rem`, `0.75rem 1.25rem`). Avec la boite de `ines-design` (`1.25rem`,
  `min-height: 2.75rem`, `padding: 0.5rem 0.9rem`), les quatre composantes comparees passent sur
  toutes les paires - verifie composante par composante contre `recreo.css`, et confirme par la mesure
  a 11 tests sur 11 ;
- une clause de plus : **tout role domine reellement porte par un bouton du depot est confronte au
  moins une fois**. Un role porte et confronte a rien est un echec.

## 4. Le contrat de controleur : ou vit le filtre

Tranche par `ADR-0044`. En resume, et avec les faits qui le fondent.

**Le port entrant, module `application`**, implemente par `PartieService` :

```java
List<String> niveauxDuCatalogue();

List<GameDefinition> jeuxDuNiveau(String niveau);
```

`jeuxDisponibles()` ne change pas et reste la vue non filtree. **Aucun port sortant neuf** :
`ContentCatalogPort.games()` rend deja tout, selectionner est une regle de catalogue. **Aucun
changement dans `domain`** : `ContentDescriptor(matiere, niveau, domaine, competence)` porte deja la
donnee - et personne ne la lit encore, mesure a l'appui : `.niveau()` et `.matiere()` **ne sont
appeles nulle part** dans les sources de production des cinq modules. Le filtre sera le premier
lecteur ; il n'y a rien a casser.

**Pourquoi `niveauxDuCatalogue()` et pas deux litteraux dans un gabarit.** `ADR-0006` : ajouter un
niveau est **une donnee, jamais un enum**. Deux boutons ecrits en clair transforment "ajouter un
niveau" en "modifier un gabarit". Etat mesure du catalogue : **11 entrees**, 6 sur `"CP"`, 5 sur
`"CM1"` (methode : valeurs de `niveau` des paquets references par une entree `- id:` de
`adapters/adapter-content/src/main/resources/catalogue.yaml`, comptees sur `e30566b` ; le douzieme
paquet du repertoire, `cm1-homophones`, n'a pas d'entree - retrait deja acte). **Deux, aujourd'hui.
Pas deux par nature.**

**Pourquoi pas trois lignes de `.filter` dans le controleur.** Ce n'est pas la quantite de code - le
filtre fait une ligne des deux cotes - c'est **ce que l'adaptateur saurait** : ou un jeu range son
niveau, et quels niveaux existent. La seconde est celle qui coute. La 29e regle ArchUnit du depot
encode cette frontiere, et elle mord sur la production mutee : le filtre ecrit dans `PartieController`
donne **1 echec**, message *"Method `PartieController.jeuxDuNiveau(java.lang.String)` calls method
`ContentDescriptor.niveau()`"*.

**Ou vit l'etat du filtre : dans l'URL, nulle part ailleurs.** `DEC-0028` ne conserve rien ; un filtre
de navigation n'est pas un etat de partie. Consequence verifiee : `EtatDePartieEnSession` **n'est pas
touche**, donc `ADR-0019` et `AccesALEtatDeSessionTest` non plus, et le lot n'a aucun cycle
lire-calculer-ecrire neuf a rendre indivisible. Le domaine, lui, continue de ne connaitre aucun
vocabulaire de matiere : `AucunVocabulaireDeMatiereTest` balaye `domain/mechanics` et le paquet racine
de `domain/content`, que rien de ce lot ne touche - je ne le credite d'aucune protection sur cette
story.

**Le parametre de niveau courant qu'`ines-design` demande : il n'existera pas, et ce n'est pas un
oubli de contrat.** Aucun echange htmx n'atteint l'en-tete (`hx-target="#jeu"`, l'en-tete est le frere
de la zone) : c'est le contexte d'`ADR-0022` mot pour mot, *"un `th:if` sur l'entree de menu ne
vaudrait que pour un chargement de page complet, et laisserait l'en-tete perime apres chaque echange
htmx"*. **Un marqueur de niveau courant dans l'en-tete serait faux des le premier echange.** Deux
formes possibles, aucune gratuite, et le choix est pedagogique :

- **l'etat filtre est annonce dans la zone echangee** (la ou le serveur gouverne le rendu a chaque
  reponse) et l'en-tete ne porte que les points d'entree. Cout : zero. C'est ce qu'`ADR-0044` retient
  par defaut ;
- **l'etat actif est porte par l'en-tete**, et alors **chaque appui devient un rendu de document
  complet**, sans echange htmx. Cout : un chargement de page la ou tout le reste echange un fragment.

Les deux autres voies sont ecartees pour des motifs techniques et non de gout : `hx-swap-oob` reste
refuse par `ADR-0022` (il declenche la dette explicite d'`ADR-0020`, dont le garde-fou
*"assemblerait le mauvais document en restant vert"*), et un selecteur relationnel par niveau
demanderait **une regle CSS par niveau**, ce qui contredit `ADR-0006` frontalement.

**Une bonne nouvelle a signaler au lot** : `accueil.html` porte deja
`<p class="pas-de-jeu" th:if="${jeux.isEmpty()}">Il n'y a pas de jeu pour le moment.</p>`. Un niveau
filtre a vide a donc deja un ecran, sans rien ajouter. Que ce texte convienne dans ce contexte est une
question pour `nadia-prof`, pas pour moi.

## 5. `ADR-0035` et `ADR-0031` : ce que la barre heurte, et ce qu'elle ne heurte pas

**`ADR-0035` (aucun texte rendu dans un sous-arbre masque) : la barre l'engage, et le garde-fou se
derive tout seul.** `ines-design` propose un petit disque decoratif `aria-hidden="true"` a cote du
texte de chaque puce. `AucunTexteNeVitDansUnSousArbreMasqueTest` balaye `templates/`, selectionne
**tous** les `[aria-hidden=true]` et refuse tout texte en clair comme tout `th:text`/`th:utext` dans
leur sous-arbre. Consequences concretes, dans l'ordre ou le lot les rencontrera :

- un `<span aria-hidden="true">` **vide** passe, et c'est le cas propose. Rien a faire ;
- si un libelle entre un jour sous ce masque, il faut soit le sortir du sous-arbre, soit poser
  `data-decor="<motif en clair>"` **sur l'element qui porte le texte**, jamais sur un ancetre - et
  l'amendement de la reserve H exige que ce motif ne soit jamais vide ;
- et le versant symetrique mord aussi : un `data-decor` pose **hors** d'un masque est un echec. On ne
  peut donc pas s'en servir comme d'une porte.

**`ADR-0031` (un ecran ne gouverne jamais l'apparence d'un autre) : la barre ne le heurte pas, et je
ne le credite d'aucune protection ici.** Sa propriete est la **co-occurrence** de jetons : un
selecteur maison qui reunit des jetons que le serveur ne peut jamais rendre ensemble. Or les jetons de
la coquille permanente coexistent **avec ceux de chaque ecran**, par construction. Aucun selecteur
portant sur la barre - masquage, etat actif, teinte - ne peut donc etre attrape par cette clause. Sa
limite 4.a n'est pas declenchee non plus : elle se rouvre le jour ou une regle maison conditionne un
element permanent a un marqueur d'ecran **conditionnel** (du genre `est-juste`), et `ecran-de-jeu` est
pose par tous les fragments, pas par une branche de ternaire. **Dit autrement : `ADR-0031` sera vert
quoi que la barre fasse, et ce vert ne doit etre lu par personne comme une garantie sur cette story.**

## 6. Le cout au pli : ce qui est gardable dans `ADR-0028`, et ce qui ne l'est pas

**Ce qui est gardable, et qui l'est deja** : l'outil. `outils/mesurer-rendu.sh <largeur> <url> <png>
[hauteur]` rend le pli visible sur l'image, et son juge d'invocation
(`outils/mesurer-rendu-test.sh`, 23 cas) refuse une hauteur qui n'est pas une hauteur, une hauteur de
fenetre illisible, et une hauteur d'iframe superieure a sa fenetre. C'est ce qui rend la mesure de
`nadia-prof` **rejouable sans main**, et c'est tout ce qu'un automate de ce depot peut apporter ici.

**Ce qui n'est pas gardable, et que je refuse de crediter a `ADR-0028`** (amendement s12 de
`squad/README.md` section 9) : **aucun test de ce depot ne rougira quand l'en-tete grandira de 66 px
et poussera une proposition sous le pli.** La position d'un element rendu est une propriete de pixels,
pas de declarations : elle depend de la police installee et du repli du texte, et `DEC-0016`
decision 2 interdit nommement tout critere en nombre de caracteres. Le garde-fou d'`ADR-0028` juge des
**invocations du script de mesure**, jamais un rendu. Le fait que `nadia-prof` mesure qu'a 360 x 640
l'accueil ne montre que 8 des 11 jeux, le neuvieme coupe au pli, **avant** les 66 px, restera un fait
qu'elle seule constate.

**Ce qui est en revanche encodable, et qui depend de la variante retenue - donc que je verse comme
matiere a l'arbitrage plutot que comme decision** :

- **variante 2 (defilement horizontal, cout fixe a +66 px)** : la promesse "la hauteur de l'en-tete ne
  depend pas du nombre de puces" est **declarative**, donc gardable en une clause - la regle maison du
  bandeau declare `flex-wrap: nowrap`, et l'ecart devient un defaut nomme. Ce n'est pas une mesure de
  rendu, c'est la cause de la propriete, et c'est exactement la forme qu'`ADR-0028` juge acceptable
  (garder une cause, jamais un effet) ;
- **variante 1 (empilee, cout `8 + 58n` px)** : **rien n'est encodable**. Le nombre de lignes que les
  libelles reels produisent a 360 px n'est pas derivable sans rendu - `ines-design` l'ecrit elle-meme -
  donc le cout devra etre remesure a chaque libelle neuf, a la main, par la commande.

Autrement dit : le choix de forme decide s'il restera une propriete gardable sur ce sujet ou aucune.
**Ce n'est pas un argument pour l'une ou pour l'autre** - la decouvrabilite qu'un enfant percoit pese
plus lourd qu'une clause de CSS, et c'est `nadia-prof` qui pese.

## 7. Les huit criteres d'acceptation, un par un

Trois refuses en l'etat et reecrits, cinq encodables tels quels, sous reserve de la forme de test
indiquee. **Aucune reecriture ne rend un critere plus facile** : les trois nouvelles formes sont plus
fortes que les anciennes et ne coutent rien de plus.

### 7.1 "l'en-tete affiche deux boutons de niveau, CP et CM1" - **refuse, reecrit**

Non encodable sans contredire `ADR-0006` : ecrit tel quel, il invite a poser deux litteraux et deux
`href` fixes dans un gabarit, et un troisieme niveau deviendrait une modification de code. Il
contredirait aussi, en l'etat, l'intention deja versee par `DEC-0032` section 3 pour les matieres
("derives du catalogue : une matiere sans jeu n'affiche pas de bouton").

> **Forme reecrite** : l'en-tete rend **exactement un point d'entree par niveau distinct du
> catalogue**, dans l'ordre du catalogue, chacun portant `data-role="entree"`, et **exactement un**
> element de role `sortie` dont le libelle est "Les jeux". Le nombre attendu n'est pas ecrit dans le
> test : il est celui que `niveauxDuCatalogue()` rend. **Deux au 2026-08-17**, methode de ce chiffre en
> section 4.

Cette forme **remplace** la clause `hasSize(1)` d'`EnTeteEtMenuTest` (section 1.2) : elle en garde la
propriete - un seul mot de sortie, une seule sortie - et elle cesse d'epingler un effectif. Elle
interdit reellement trois choses : les libelles en dur, un niveau au catalogue sans bouton, et un
bouton sans niveau au catalogue.

### 7.2 "sur un ecran de jeu, les deux boutons restent visibles" - **encodable sous sa forme declarative seule**

Une visibilite est une propriete du rendu ; aucun automate du depot ne rend une page, et `ADR-0022`
l'ecrit de lui-meme. Ce qui est encodable est le **couple declaratif** : aucune regle de masquage
servie ne nomme la classe des puces. C'est peu, c'est exact, et c'est ce qui rougira si quelqu'un
ajoute le repli sans decision. La visibilite rendue, etat par etat, reste la propriete de
`nadia-prof`, qui l'a nommee comme sienne en s06 pour l'entree de menu.

**Piege a signaler au lot** : si le repli est un jour retenu, la regle doit nommer la classe de la
**puce**, pas celle du bandeau - section 2.2, point 2.

### 7.3 "accueil sans partie en cours, appui -> la liste se filtre immediatement, sans passer par la confirmation" - **encodable, un mot a retirer**

"Immediatement" n'est pas mesurable par un test. Le reste l'est entierement : `GET /niveaux/{niveau}`
sans partie en cours rend la liste filtree **dans la meme reponse**, et cette reponse ne contient pas
le fragment de confirmation. Deux assertions, aucune ambiguite.

### 7.4 "partie en cours, appui -> la confirmation existante s'affiche avant tout changement, la partie n'est pas encore modifiee" - **encodable, la seconde moitie a renforcer**

La premiere moitie s'asserte sur le fragment rendu. **La seconde ne doit pas s'asserter sur le
balisage** : c'est la doctrine d'`ADR-0038` point 3 - un effet mesure, jamais un attribut lu. Et
"portePartieEnCours" ne suffit pas, c'est ce qu'`ADR-0042` a etabli : une requete peut laisser une
partie en cours tout en ayant remplace celle de l'enfant.

> **Forme reecrite** : apres la requete d'une puce sur une session qui porte une partie, l'etat lu par
> `EtatDePartieEnSession` est **identique** avant et apres, `jeuId` **et** compte de manches resolues
> compris.

C'est gratuit (deux lectures au lieu d'une) et strictement plus fort. Rappel de la section 2.2 : sans
ce test, une route qui ne ferait **rien** serait verte sur les quatre clauses d'`ADR-0038`.

### 7.5 "continuer -> la partie reprend exactement sur la manche affichee, et aucun filtre n'est applique" - **encodable, et sa seconde moitie devient gratuite**

"Exactement" a besoin d'un critere : l'enonce, l'ensemble des propositions et le score de la manche
rendue apres l'annulation sont ceux d'avant l'ouverture de la confirmation. `POST /retour/annuler`
existe et fait cela ; le test est un scenario de plus dans le corpus.

"Aucun filtre de niveau n'est applique" est **garanti par construction** par le point 4 d'`ADR-0044` :
le niveau demande ne vit que dans l'URL, aucun attribut de session ne le porte, donc il n'y a rien qui
puisse survivre a l'annulation. Un critere qu'une decision de conception rend gratuit vaut mieux qu'un
critere teste, et ce qui le garde est `AccesALEtatDeSessionTest` - aucune classe hors
`EtatDePartieEnSession` ne touche la session HTTP.

### 7.6 "confirmer -> la partie est abandonnee (meme comportement qu'un abandon ordinaire) et l'accueil affiche la liste filtree" - **encodable, "meme comportement" a expliciter**

"Meme comportement qu'un abandon ordinaire" ne se teste pas comme tel ; ce qu'il designe se teste :
apres la requete, la session ne porte plus de partie en cours **et** le marqueur de partie est efface
cote navigateur - les deux gestes que `POST /retour/confirmer` fait aujourd'hui. Plus l'assertion sur
la liste filtree rendue.

### 7.7 "la liste filtree contient exactement les jeux dont le niveau du `ContentDescriptor` correspond" - **encodable tel quel, et c'est le seul des huit qui l'est sans retouche**

Une seule precision de **placement** : ce test vit dans `application` (sur `PartieService`), pas dans
`adapter-web` - c'est la consequence directe d'`ADR-0044`, et un test place du mauvais cote serait le
premier signe que le filtre a glisse dans l'adaptateur.

Une forme gratuite et plus forte est disponible, et je la verse sans l'imposer : **la reunion des
listes filtrees sur tous les niveaux de `niveauxDuCatalogue()` est exactement `jeuxDisponibles()`**.
C'est une partition, elle attrape en plus le jeu dont le niveau ne correspond a aucun bouton - un cas
que "exactement les jeux du niveau" ne voit pas.

### 7.8 "au premier chargement, la liste reste le catalogue complet des onze jeux" - **refuse, reecrit**

**"Onze" est un chiffre destine a pourrir**, et le depot en a la mesure exacte : `ADR-0042` a annonce
"sept jeux" a trois endroits pendant que le catalogue en portait onze, parce que le chiffre de s10
avait ete reporte de sprint en sprint sans etre remesure. Un critere d'acceptation qui epingle un
effectif de catalogue sera faux au douzieme jeu, et il sera faux **en restant vert** si personne ne le
relit.

> **Forme reecrite** : au premier chargement, la liste affichee est exactement ce que
> `jeuxDisponibles()` rend, sans filtre - aucun effectif ecrit dans le test.

### 7.9 Trois garde-fous que les huit criteres ne mentionnent pas et qui rougiront quand meme

Mesure du 2026-08-17, les deux puces posees avec la boite de `ines-design`, `data-role="entree"`, sa
regle maison `.chip-niveau` ajoutee, **et aucun controleur derriere** ;
`bash outils/mvn-jdk21.sh -B -o -pl adapters/adapter-web test` : **3 echecs sur 233 tests**, et aucun
n'est un garde-fou de saillance.

1. **`TexteAccentueDesGabaritsTest.aucunGabaritDeProductionNEcritUnTexteHorsDeLInventaire`** - message
   du garde-fou : *"accueil.html ecrit \"CP\", un texte que l'inventaire de ce garde-fou ne porte pas.
   [...] soit `nadia-prof` l'a arbitre et l'inventaire s'etend dans le meme commit, avec la reference
   de l'arbitrage, soit ce texte n'aurait pas du entrer"*. **La porte pedagogique est donc deja
   executable sur les libelles**, et ce n'est pas moi qui l'ai mise la. Le lot ne peut pas poser "CP"
   sans citer l'arbitrage.
2. **`HabillageDesEcransTest.aucuneClassePoseeSurUnElementNEstOrpheline`** - toute classe posee doit
   etre nommee par une feuille servie. Une classe de structure comme `bandeau-niveaux` doit donc
   recevoir au moins une declaration, ou disparaitre.
3. **`EnTeteEtMenuTest.leMenuDeLEnTeteContientExactementUneEntreeVersLAccueil`** - le comptage fixe de
   la section 1.2, a remplacer par la forme de la section 7.1.

## 8. Les deux points routes par `camille-po` via `sofia-sm`

### 8.1 Le couple retrait-du-catalogue / methode-de-test, cinq classes

`yanis-qa` a mesure le symptome et refuse de choisir le remede ; `ADR-0039` avait refuse l'invariant
evident ("chaque paquet du repertoire a une entree de catalogue"), a bon droit, parce qu'il romprait a
chaque retrait volontaire.

**Mon avis : un mecanisme, pas un `ADR`, et il tient dans un changement de sens de derivation.** Les
cinq methodes `leJeuDuCatalogue...ChargeBienLePaquet...` sont **une population enumeree a la main** -
une methode par entree, resolue par `filter().findFirst().orElseThrow()`. Il faut deriver la
population **du catalogue vers les paquets**, et non l'inverse :

> une seule clause qui, **pour chaque entree de `catalogue.yaml`**, verifie que le chargeur reel rend
> le paquet que cette entree designe.

Le sens de derivation est tout : `ADR-0039` refusait paquets -> entrees, qui rougit sur un retrait
volontaire. Entrees -> paquets ne rougit jamais sur un retrait : l'entree disparait, le cas disparait
avec elle, **et la restitution de l'entree restitue le cas dans le meme geste** - ce qui est exactement
la propriete que le depot a perdue une fois ce sprint et qu'aucun mecanisme ne tenait. Cinq methodes
sont supprimees, aucune ligne de production ne change, `ADR-0013` obligation 1 est honoree au lieu
d'etre contournee.

**Ce que cela ne resout pas, dit avant qu'on le decouvre** : qu'un paquet **doive** avoir une entree
reste une decision produit, non encodee - et c'est bien ainsi. Le paquet `cm1-homophones`, present dans
le repertoire et absent du catalogue, doit rester vert.

**Recommandation : une story, cout de l'ordre d'un petit lot de developpeur** - une classe de test
derivee, cinq methodes retirees, la preuve de morsure etant une entree de catalogue mutee (deja
mesuree par `yanis-qa`, qui en donne le message). Pas d'`ADR` : la doctrine qui la justifie est
`ADR-0013` obligation 1, deja acceptee, et je n'ecris pas un `ADR` pour appliquer un `ADR`.

### 8.2 Le quatrieme axe de rang - la position a l'ecran, enumerable par lecture, mesuree par rien

`yanis-qa` etablit que la position d'une proposition vient d'un Fisher-Yates
(`BanditManchotSession.melanger`) rejoue a chaque manche, qu'aucun test n'en mesure la distribution, et
que la conclusion "la position ne trahit rien" repose donc sur une lecture de code juste - pas sur un
comptage. Il a raison de faire la distinction : c'est celle d'`ADR-0039` et d'`ADR-0041`.

**Mon avis : un mecanisme, pas un `ADR`, et une story petite - avec une reserve de conception a
respecter, sans quoi le remede serait pire que le mal.** Un garde-fou statistique qui depend du hasard
du jour est **instable**, et un garde-fou instable finit desactive : c'est la pire issue possible pour
ce depot. La forme qui evite cela :

> une distribution de positions mesuree sur un **grand nombre de tirages** avec une `RandomSource` de
> **graine declaree dans le test**, comparee aux memes bornes que le garde-fou de rang de valeur
> emploie deja. Deterministe, donc jamais instable ; et il mord sur ce qui peut reellement se casser -
> un `melanger` qui ne deplacerait jamais le premier element, une borne de `nextInt` fausse.

**Ce qu'il ne mesurera pas, et qui doit etre ecrit dans le meme geste** : la qualite de la
`RandomSource` **de production**. Une graine fixe mesure l'algorithme, pas le generateur. Le trou est
donc nomme d'avance plutot que decouvert, ce qui est la seule facon acceptable de livrer un garde-fou
avec un trou.

**Recommandation : une story, cout de l'ordre d'un petit lot**, dans `domain` la ou `melanger` vit.
Priorite basse a mon sens - la lecture de code est juste, le risque est faible - mais ce jugement de
priorite appartient a `camille-po` et `sofia-sm`, pas a moi.

## 9. Le point route par `nadia-prof` : deux phrases perimees, corrigees

`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` portait deux affirmations fausses dans sa
javadoc. **Elle a raison sur les deux, le garde-fou est intact, et rien de son code n'a change.**

1. *"L'indice ne s'affiche qu'apres trois echecs consecutifs"* - **faux**. `BanditManchotSession.indice()`
   documente et implemente l'affichage **des le premier echec** sur la manche bloquante (M1, `DEC-0004`
   point 4), ce que `nadia-prof` a constate en jouant. **Corrige**, avec le motif juste, qui a la meme
   conclusion : un indice n'apparait que sur une manche non resolue, il ne distingue donc pas deux
   items au moment ou l'ecran se presente.
2. *"Meme le critere plus strict de l'enonce seul est tenu partout"* - **plus vrai du contenu de
   production**, et le contenu qui l'a rendu faux est **valide**. Mesure du 2026-08-17, methode :
   lecture ligne a ligne des cles `enonce`/`question` des paquets servis et de `contenu-repris/`,
   comptage des valeurs apparaissant plus d'une fois dans un meme fichier.
   **`cm1-tables-multiplication` (S13-4/S13-5) porte 99 items dont 16 enonces apparaissent deux fois**,
   avec le meme `bonneReponse` et des distracteurs differents - par exemple `"2 x 4 = ?"` avec
   `["12", "10"]` puis avec `["4", "6"]`. C'est **exactement la forme legitime** que le critere retenu
   autorise a dessein. C'est le **seul** paquet du depot dans ce cas. La phrase est retiree ; elle
   n'etait pas une clause - aucune assertion n'a jamais porte sur l'enonce seul - mais une observation
   de circonstance, et une observation de circonstance qui survit a son contenu devient un mensonge sur
   ce que le garde-fou garde.
3. **Troisieme correction, non signalee, trouvee en verifiant les deux premieres** : la mesure
   "397 items des 65 paquets" ne portait **aucune date** et le depot sert desormais 12 paquets et non 4.
   Elle est datee du 2026-08-11 (s10) et mise au passe, plutot que remesuree sous une methode qui n'est
   pas la mienne. Un chiffre sans date se compare a un depot qui a change.

## 10. Question de nomenclature routee pour s15 : ou vit le libelle affichable d'une matiere

**Avis technique pour instruire s15, pas une decision produit ni de redaction.**

Trois faits mesures d'abord. **(a)** Les paquets portent `matiere: "Francais"` (3 paquets servis) et
`matiere: "Mathematiques"` (9), en ASCII. **(b)** `ContentDescriptor.matiere()` **n'est appele nulle
part** dans les sources de production des cinq modules : la valeur est chargee, transportee, et jamais
lue. Le seul endroit du depot qui la compare est une assertion de test,
`YamlContentPackLoaderTest:33` (`isEqualTo("Mathematiques")`). **(c)** Les fichiers de contenu et le
catalogue portent deja des accents lus par un enfant (`consigne: "Combien ça fait ?"`), donc la chaine
UTF-8 de bout en bout est **deja prouvee** par l'application en service.

**Ou le libelle doit vivre : dans la donnee de catalogue, pas dans le gabarit.** C'est `ADR-0006` qui
le decide et non moi : *"ajouter un niveau ou une matiere est une donnee, jamais un enum"*. Une
correspondance ecrite dans un gabarit (un `th:switch`, une table de libelles) ferait d'un ajout de
matiere une modification de vue - le meme defaut que deux boutons de niveau en dur, une couche plus
haut. Et le domaine est hors sujet : il ne connait aucun vocabulaire de matiere et ne doit pas en
acquerir un.

**Entre les deux options que `camille-po` pose, ma recommandation est la premiere, et son cout est
mesure : la valeur interne devient elle-meme la valeur accentuee.**

- **Cout total : 12 fichiers YAML et une assertion de test.** Aucun code de production a changer,
  puisque rien ne compare cette valeur (fait b). Aucun risque de rupture d'egalite de chaine, pour la
  meme raison - et le jour ou s15 comparera un parametre de route a cette valeur, la comparaison
  portera sur la valeur accentuee des deux cotes. Un accent dans une URL est en revanche une gene
  reelle : si un chemin de matiere est retenu, il devra porter un identifiant sans accent
  (`/matieres/francais`), qui est une **donnee de plus du paquet**, pas une transformation calculee de
  l'autre.
- **Pourquoi pas un second champ "libelle affichable" tout de suite** : deux champs ou un seul suffit,
  avec la certitude qu'ils divergeront - c'est la famille exacte de la dette 2 de la version
  precedente, un champ que chaque paquet doit remplir sans que son contenu en ait besoin. **Ce qui me
  ferait changer d'avis, ecrit d'avance** : le jour ou un libelle affichable doit differer de
  l'identifiant pour une raison qui n'est pas l'accent - un bouton trop etroit pour "Questionner le
  monde", par exemple. Le besoin serait alors constate et non anticipe, et le second champ se
  justifierait seul.
- **Ce que cette recommandation ne tranche pas** : la graphie exacte, la majuscule, et si le mot que
  lit un enfant de CP doit etre celui-la. `DEC-0002` amende et `nadia-prof`.

**Garde-fou deja en place a signaler a qui instruira s15** : `TexteAccentueDesGabaritsTest` refusera
tout libelle de matiere rendu qui n'est pas dans son inventaire, tant que l'arbitrage de `nadia-prof`
n'est pas cite dans le meme commit. La question de la nomenclature ne peut donc pas etre reglee en
silence, quelle que soit l'option retenue.

## 11. Ce que cet avis ne dit pas

- **Si un enfant de CP distingue une puce de niveau d'une sortie de jeu.** Arbitrage prealable de
  `nadia-prof`, bloquant, et rien de vert ici ne l'anticipe.
- **Laquelle des deux formes chiffrees par `ines-design` retenir.** Je dis seulement, en section 6,
  laquelle des deux laisse une propriete gardable derriere elle - c'est une donnee de l'arbitrage, pas
  un vote.
- **A qui affecter la story, ni dans quel ordre.** `sofia-sm`.
- **Si les criteres reecrits en section 7 conviennent au produit.** Je dis ce qu'un test peut tenir ;
  ce que la story doit promettre reste a `camille-po`.
