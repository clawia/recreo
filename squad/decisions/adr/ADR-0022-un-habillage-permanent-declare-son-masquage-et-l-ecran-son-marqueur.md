# ADR-0022 - Rendre la visibilite d'un habillage permanent dependante d'un marqueur d'etat pose par le serveur, et retirer d'un appariement les boutons qui ne se montrent plus ensemble

- **Statut** : Accepte, son contexte amende le 2026-08-14 (un chiffre perime et la comparaison qu'il
  portait ; la decision et ses garde-fous sont inchanges)
- **Date** : 2026-08-07
- **Auteur** : theo-archi
- **Sprint** : s06
- **Amende** : ADR-0017 (l'extension du 2026-08-07 ; l'amendement est ecrit dans le corps d'ADR-0017)
- **Complete** : ADR-0008 (le comportement de jeu vit sur le serveur), ADR-0014 point 7 (le
  JavaScript de la bibliotheque tierce n'entre pas), ADR-0015 (deriver le perimetre de la source qui
  gouverne le rendu), ADR-0020 (l'unite de verification d'un ecran est le document assemble)
- **Story** : S6-9, apres l'arbitrage prealable de `nadia-prof` du 2026-08-07
  (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section "Arbitrage prealable au
  developpement - S6-9")

## Contexte

`nadia-prof` a tranche l'arbitrage que je lui avais route au planning. Elle confirme le principe de
S6-9 - une seule sortie par ecran de jeu, et le mot est "Les jeux" - et **refuse la forme** : la
sortie qui survit sur un ecran de jeu est le bouton de la zone de contenu, pas le lien de l'en-tete,
et `sortie-pleine` est conserve tel quel sur l'ecran de fin de partie. Elle a joue avant de trancher
et chiffre son motif : le lien de l'en-tete mesure 102 x 53 px en contour dans un coin, contre un
aplat de 117 x 56 px au milieu du texte que l'enfant vient de lire ; garder le premier comme seule
porte ferait de l'ecran de fin de partie un cul-de-sac.

> **Amendement du 2026-08-14 (s11) : le chiffre "102 x 53 px" est perime, et la comparaison qu'il
> portait ne compare pas ce que je lui faisais dire. La decision ci-dessous ne bouge pas, et je dis
> pourquoi plutot que de le supposer.**
>
> **1. Le chiffre. `109 x 53 px`, pas `102 x 53 px`.** `nadia-prof` l'avait mesure le 2026-08-07 sur le
> libelle "Accueil" ; le **point 3 de la decision ci-dessous** remplace ce libelle par "Les jeux", plus
> large. Le chiffre etait donc perime par cet `ADR` lui-meme, avant meme son acceptation, et il a
> survecu un sprint et demi. Elle l'a remesure et corrige de sa main
> (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section "0 bis", point 1), `camille-po` en a
> tire l'amendement de `DEC-0027` ; ce document-ci est le troisieme et dernier porteur du chiffre faux.
>
> **Ce que j'ai verifie moi-meme, et par quoi** - lecture des composantes de boite de `recreo.css`, pas
> une capture :
>
> - **La hauteur est derivable, et elle vaut bien 53 px.** `.lien-accueil` declare `font-size: 1.25rem`
>   (20 px), `padding: 0.6rem 1.1rem` (9,6 et 17,6 px), `border: 0.125rem` (2 px), et herite le
>   `line-height: 1.5` de `.btn` : 30 + 2 x 9,6 + 2 x 2 = **53,2 px**. Le meme calcul sur
>   `.sortie-pleine` (`padding: 0.75rem 1.25rem`, bordure de `.btn` a 1 px) donne **56 px**, exactement
>   le second chiffre cite ci-dessus, qu'elle a remesure inchange.
> - **La largeur absolue n'est pas derivable de ce depot, et je ne fais donc pas semblant de la
>   verifier.** Aucun fichier maison ne declare de `font-family` ; la famille vient de
>   `--bs-font-sans-serif` de la bibliotheque vendorisee, c'est-a-dire de la police systeme du poste. Les
>   109 px sont sa mesure sur son rendu, et je la reprends comme telle. Ce qui se verifie de mon cote est
>   la **coherence** des deux chiffres entre eux : leur ecart declare vaut 2 x (20 - 17,6) de rembourrage
>   moins 2 x (2 - 1) de bordure, soit **+2,8 px** pour l'aplat, et l'ecart mesure est de 8 px ; les
>   5,2 px restants sont l'avance du meme mot en gras a 20 px, ce qui est l'ordre de grandeur attendu.
>   Rien ne detonne, et rien de plus n'est demontre par la.
> - **Et surtout : les deux boites sont egales par construction, quelle que soit la police.**
>   `.lien-accueil` et `.retour-accueil` declarent les **memes** composantes - `display`, `min-height`,
>   `font-size`, largeur de bordure, `border-radius`, `padding` - ne se distinguent que par la couleur du
>   texte et de la bordure, et portent le **meme libelle** depuis le point 3 ci-dessous. `recreo.css` le
>   dit en clair depuis S6-1 : le lien *"reprend deliberement les memes composantes de boite que
>   `.retour-accueil`"*. Cette egalite-la ne depend d'aucune mesure, et c'est elle qui compte pour la
>   suite.
>
> **2. Le raisonnement qui tombe avec le chiffre.** La phrase ci-dessus oppose `.lien-accueil` a un
> "aplat de 117 x 56 px", qui est `.sortie-pleine`. **Ces deux elements ne s'affichent jamais sur le meme
> ecran** : le premier ne se montre que sur l'accueil (c'est l'objet de cette decision), le second n'est
> rendu que par l'ecran de fin de partie. Une comparaison de tailles entre deux ecrans ne dit rien de ce
> qu'un enfant voit - c'est la doctrine meme d'`ADR-0017` ("deux boutons d'un meme ecran") et
> d'`ADR-0031`. Sur un ecran de manche, les deux candidats a la sortie sont `.lien-accueil` et
> `.retour-accueil`, et le point 1 ci-dessus dit qu'ils ont **la meme boite**. **Il n'y avait donc aucun
> ecart de taille a opposer sur une manche.** `nadia-prof` en tire la meme conclusion contre son propre
> argument, et le perimetre de son motif - un ecran, pas cinq - est sa correction, dans sa fiche, pas la
> mienne a ecrire.
>
> **3. Ce que cet amendement ne change pas, verifie clause par clause et non affirme.** Aucun des cinq
> points de la decision, aucune consequence et aucun garde-fou ne raisonne sur une taille : le marqueur
> d'etat, la forme contractuelle du masquage, le libelle, la conservation de `sortie-pleine` et la
> derivation de l'appariement depuis les masquages sont tous des proprietes declaratives. Le garde-fou
> amende, `LActionNeDeclarePasMoinsQueLaSortieTest`, compare des **composantes declarees** (`font-size`,
> `min-height`, les deux axes de `padding`) et jamais une boite rendue - `ADR-0017` note lui-meme que la
> largeur, venant du libelle, n'est pas comparable. Aucun chiffre de ce depot n'est a recalculer, et la
> suite reste verte a 415 tests. Ce qui aurait bouge, si je l'avais ecrit : une justification du masquage
> par "le lien est plus petit, il ne peut pas etre la seule porte". Elle n'est pas dans cet `ADR`, et
> c'est le seul motif pour lequel la correction se limite au contexte.
>
> **4. Ma part dans la survie du chiffre, parce qu'elle est nommable.** Je l'ai recopie **sans sa
> methode** - ni le libelle sur lequel il avait ete pris, ni l'ecran, ni le fait qu'il datait d'avant le
> renommage que le meme `ADR` decidait. Un chiffre cite avec sa methode aurait rendu sa peremption
> visible le jour meme. C'est ma propre regle, appliquee ici a mes frais.

Elle me laisse deux questions techniques, et c'est tout ce que cet ADR traite.

### Question 1 - un element permanent qui doit disparaitre sur certains ecrans

`DEC-0012` et le critere d'acceptation de S6-1, livre en s05, font de l'en-tete un element
**permanent** : frere ou ancetre de la zone `#jeu`, jamais efface ni recree par un echange htmx.
`nadia-prof` demande maintenant que son unique entree de menu ne soit **pas visible** sur les cinq
etats de l'ecran de jeu (manche, retour juste, retour faux, fin de partie, erreur), tout en restant
visible sur l'accueil. Elle nomme ce qu'elle veut voir et me rend le comment, explicitement.

Le probleme n'est pas cosmetique, et il n'a pas de solution evidente :

- **Le serveur ne rend que la zone `#jeu`.** Tous les declencheurs du depot posent
  `hx-target="#jeu" hx-swap="innerHTML"`. L'en-tete est le frere de cette zone : aucune reponse du
  serveur ne le traverse. Un `th:if` sur l'entree de menu ne vaudrait donc que pour un chargement de
  page complet, et laisserait l'en-tete **perime** apres chaque echange htmx - c'est-a-dire faux
  exactement dans le cas que `nadia-prof` decrit.
- **Aucun JavaScript n'entre ici.** Le comportement de jeu vit sur le serveur (ADR-0008), le
  JavaScript de Bootstrap n'entre pas sans un ADR qui l'ouvre nommement (ADR-0014 point 7), et
  `FrontieresDuJavaScriptTest` garde le budget.
- **La solution ne doit pas rouvrir ADR-0020.** Je viens de fermer, dans le commit precedent, un
  identifiant duplique dans le document assemble. Toute solution qui ajoute un identifiant ou un
  second mecanisme d'echange doit etre pesee contre ce risque.

### Question 2 - un appariement qui survivrait a la disparition de son sujet

`nadia-prof` nomme elle-meme le symptome, et elle a raison. Mon extension d'ADR-0017 du 2026-08-07
attribue a **tous** les ecrans un bouton ecrit hors de la zone echangee, et confronte donc le lien de
l'en-tete a `.btn.proposition` et a `.btn.encore`. Si l'entree de menu cesse d'etre visible sur les
ecrans de jeu, ces boutons ne s'afficheront plus jamais ensemble : les confronter ne mesure plus une
situation que l'enfant rencontre. **C'est la famille de defaut que la retro de s05 a sanctionnee deux
fois** - un garde-fou vert qui ne verifie plus rien - et cette fois elle serait de mon fait.

## Decision

**Un habillage permanent dont la visibilite depend de l'ecran declare son masquage dans la feuille
maison ; l'ecran, lui, declare son etat par un marqueur que le serveur pose dans la zone echangee. Un
appariement de saillance ne confronte que des boutons que ce couple laisse visibles ensemble.**

1. **Le serveur pose un marqueur d'etat sur la zone de contenu, jamais sur l'en-tete.** Chaque
   fragment insere dans `#jeu` porte la classe `ecran-de-jeu` sur sa racine. Cette classe ne porte
   aucun style propre : elle dit un etat, et c'est tout ce qu'elle fait.

2. **La feuille maison declare le masquage, sous une forme contractuelle** :

   ```css
   body:has(.ecran-de-jeu) .lien-accueil {
       display: none;
   }
   ```

   L'entree de menu **reste presente dans le document** - `DEC-0012` et S6-1 sont tenus a la lettre,
   l'en-tete n'est ni efface ni recree. C'est sa **visibilite** qui depend de l'etat, jamais son
   existence. `nadia-prof` a d'ailleurs pris soin d'ecrire qu'elle s'interdisait de demander sa
   suppression.

3. **Le libelle de l'entree de menu devient "Les jeux".** Un seul mot de sortie circule sur toute
   l'application. C'est le geste par lequel `nadia-prof` leve sa reserve 3, et le seul changement de
   texte que l'arbitrage autorise.

4. **Les deux branches du `th:class` de `fragments/retour.html` et le bouton de
   `fragments/manche.html` ne sont pas touches.** Ni retires, ni renommes, ni redimensionnes.
   `sortie-pleine` est conserve tel quel.

5. **Un appariement de saillance derive des masquages la liste des ecrans ou un habillage hors zone
   reste visible.** Le lien de l'en-tete n'est plus confronte aux actions des ecrans de jeu, parce
   qu'il ne s'y montre plus ; il reste confronte aux actions de l'accueil, ou il se montre. Le doute
   joue dans le sens de la comparaison : un habillage qu'aucun masquage ne nomme reste confronte a
   tous les ecrans, comme avant.

### Pourquoi un selecteur relationnel, et pas les deux autres voies

**`hx-swap-oob` (echange hors bande).** Chaque fragment de jeu embarquerait une version vide de
l'en-tete, que htmx substituerait a l'originale. C'est la voie la plus tentante parce qu'elle garde
tout cote serveur. Je l'ecarte pour un motif que j'ai ecrit moi-meme il y a un commit : ADR-0020 nomme
comme **dette explicite et mode de defaillance le plus dangereux** le fait que son garde-fou
reimplemente `hx-swap="innerHTML"` et **assemblerait le mauvais document en restant vert** devant tout
autre `hx-swap`. Introduire `hx-swap-oob` declencherait cette dette immediatement. Elle exigerait de
plus un identifiant sur l'en-tete et la duplication de son balisage dans trois fragments - deux
choses qu'ADR-0020 vient de retirer du depot.

**Un `th:if` cote serveur sur l'entree de menu.** Ecarte parce qu'il est simplement faux : l'en-tete
n'est pas re-rendu par un echange htmx, donc la condition ne serait evaluee qu'au chargement complet.
L'entree resterait affichee pendant toute une partie commencee depuis l'accueil.

**Le selecteur relationnel a un avantage que je n'avais pas anticipe et que je verifie** : il traite
gratuitement le cas du **chargement complet pendant une partie en cours**. `GET /` avec reprise rend
`fragments/manche` a l'interieur de `#jeu` ; le document assemble porte donc le marqueur, et l'entree
est masquee sans qu'aucune condition serveur n'ait a le savoir. Une solution `th:if` aurait demande de
traiter ce cas a part.

## Regle executable

### Pourquoi ce n'est pas une regle ArchUnit

Meme motif qu'ADR-0020, et il n'a pas change : ArchUnit raisonne sur des classes compilees, des
dependances et des annotations. Rien ici n'est une dependance - ce sont une declaration CSS et un
attribut de gabarit.

### Le garde-fou neuf

Fichier : `adapters/adapter-web/src/test/java/com/recreo/adapter/web/LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.java`

**Ce qu'il ne fait pas, dit avant ce qu'il fait** : il ne mesure aucune visibilite. Une visibilite est
une propriete du rendu, et aucun automate du depot ne rend une page. `nadia-prof` a ecrit elle-meme
que "que l'entree de menu soit reellement invisible sur les cinq etats" reste une propriete dont elle
est seule garde, et je ne pretends pas la lui prendre.

**Ce qu'il interdit reellement** : que l'un des deux termes du couple declaratif existe sans l'autre.
C'est la seule facon dont cette propriete se perdra, et il y en a exactement quatre formes, toutes
fermees :

| Faute possible | Ce qui la refuse |
|---|---|
| Un fragment neuf oublie le marqueur | les ecrans sont derives du repertoire de gabarits ; tout gabarit qui n'est pas la coquille est un ecran insere, donc doit le porter |
| La regle de masquage disparait de la feuille | le garde-fou echoue faute de masquage, avant meme de juger un ecran |
| Le marqueur est renomme d'un cote seulement | le masquage nomme le marqueur ; l'ecart devient un defaut nomme |
| La coquille porte elle-meme le marqueur | l'entree serait masquee partout, y compris sur l'accueil : refuse explicitement |

**Perimetre derive, et la coquille n'est pas nommee.** La coquille est identifiee par le fait que son
document **contient la zone `hx-target`** - jamais par son nom de fichier ni par son repertoire. Tout
autre gabarit est un ecran insere dans cette zone. Un fragment neuf entre donc dans le controle sans
qu'on l'y inscrive, et c'est le seul point qui compte : la regression viendra du quatrieme fragment,
pas des trois qui existent.

**Derivation totale (ADR-0013 obligation 2).** Sont des **echecs**, jamais des cas ignores : aucune
zone `hx-target`, plusieurs zones distinctes, aucune coquille, plusieurs coquilles, aucun fragment,
aucun gabarit, aucun masquage, et un masquage dont la classe n'est portee par aucun gabarit - ce
dernier fermant le cas de la regle de masquage qui ne masque rien, donc decorative.

**Un second epinglage, sur le texte** : `leLienDeMenuEtLaSortieDeLaZoneDeContenuPortentLeMemeMot`
releve le libelle de **tous** les elements `data-role="sortie"` du depot et exige qu'ils portent tous
le meme mot, "Les jeux". Il n'enumere pas les sorties, il les derive de l'attribut de role. Si
quelqu'un remet "Accueil" sur le lien de menu, la reserve 3 de `nadia-prof` se rouvre - elle l'a levee
par ce geste precis et pas par un autre.

### Le garde-fou amende

Fichier : `adapters/adapter-web/src/test/java/com/recreo/adapter/web/LActionNeDeclarePasMoinsQueLaSortieTest.java`

L'attribution des ecrans a un bouton hors zone passe de "tous les ecrans" a "tous les ecrans ou aucun
masquage ne le cache" :

```java
    private static List<String> ecransOuIlResteVisible(Map<String, Document> documents,
                                                      List<String> classes,
                                                      List<Masquage> masquages) {
        List<String> visibles = new ArrayList<>();
        for (Map.Entry<String, Document> ecran : documents.entrySet()) {
            boolean masque = masquages.stream()
                    .anyMatch(masquage -> classes.contains(masquage.habillage())
                            && masqueSur(ecran.getValue(), masquage));
            if (!masque) {
                visibles.add(ecran.getKey());
            }
        }
        return visibles;
    }
```

La liste des masquages vient de la feuille servie, pas d'une liste ecrite dans le test : c'est la
feuille qui gouverne ce rendu, donc c'est d'elle que le perimetre descend (ADR-0015).

### L'epinglage supprime, et son motif ecrit

`leLienDeLEnTeteEstConfronteAuxActionsDesEcransDeJeu`, ecrit le 2026-08-07, est **supprime** le meme
jour. Motif, exige par ADR-0013 et par la consigne de `sofia-sm` : il affirmait que le lien de
l'en-tete est confronte a `.proposition` et a `.encore`. Apres l'arbitrage, ces boutons ne s'affichent
plus jamais ensemble. Le maintenir aurait exige de mesurer un ecran que l'enfant ne rencontre jamais,
c'est-a-dire de garder vert un appariement sans objet.

Il est **remplace, pas retire**, par `leLienDeLEnTeteEstConfronteAuxSeulesActionsDeLAccueil`, qui tient
la propriete par ses deux bouts :

- la premiere assertion exige que le couple restant existe - l'accueil, seul ecran ou le lien se
  montre, ou il accompagne les boutons de titre de jeu. La propriete d'ADR-0017 y vaut pleinement :
  un enfant qui regarde la liste des jeux ne doit pas voir la sortie plus saillante qu'un jeu a
  ouvrir ;
- la seconde exige que les couples retires **ne reviennent pas**. Elle rougit si quelqu'un retire la
  regle de masquage en gardant le lien dans l'en-tete, c'est-a-dire si l'arbitrage est defait en
  silence.

**Ce que je ne cache pas** : le mecanisme de traversee de frontiere de l'extension du 2026-08-07 - un
bouton hors zone confronte aux boutons d'un autre fichier - **n'a plus aucun objet en production**,
puisque le seul bouton hors zone est desormais confronte a un bouton du meme fichier. Je le garde
malgre cela, et je dis pourquoi plutot que de le presenter comme actif : il reste **correct**, il
reste **eprouve** par son jeu fautif `entete-permanente-plus-grosse` qui le fait rougir, et il
redeviendra portant le jour ou un element permanent portera un bouton visible sur un ecran de jeu. Un
mecanisme juste, prouve mordant sur un objet neuf, dont la population de production est
momentanement vide, n'est pas un garde-fou decoratif - c'est exactement ce que la mise a l'epreuve sur
objet neuf d'ADR-0013 sert a rendre acceptable. Ce qui serait decoratif, et que je supprime, c'est
l'**epinglage** qui pretendait que la production l'exerce.

### La preuve que ces garde-fous mordent

Methode : une mutation a la fois appliquee au depot, puis `mvn -B -o -pl adapters/adapter-web test`
restreint aux trois classes qui gardent cette propriete
(`LActionNeDeclarePasMoinsQueLaSortieTest`, `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest`,
`EnTeteEtMenuTest`), depot restaure ensuite. La colonne de droite liste les tests reellement rouges,
releves dans la sortie de Maven - pas les tests que j'attendais rouges. Le perimetre de la mesure est
donc ces trois classes : un quatrieme garde-fou ailleurs dans le depot ne serait pas visible ici, et
la derniere ligne de cette section donne la mesure sur le depot entier.

| Mutation appliquee | Tests rouges mesures |
|---|---|
| La regle `body:has(.ecran-de-jeu) .lien-accueil { display: none }` retiree de la feuille maison | `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.chaqueEcranInsereDansLaZonePorteLeMarqueurQueLaFeuilleExige` **et** `LActionNeDeclarePasMoinsQueLaSortieTest.leLienDeLEnTeteEstConfronteAuxSeulesActionsDeLAccueil` |
| `class="ecran-de-jeu"` retire de `fragments/retour.html` seulement | les **memes deux** tests |
| Le libelle du lien de menu revenu a "Accueil" | `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.leLienDeMenuEtLaSortieDeLaZoneDeContenuPortentLeMemeMot`, `leLienDeLEnTeteEstConfronteAuxSeulesActionsDeLAccueil` **et** `EnTeteEtMenuTest.leMenuDeLEnTeteContientExactementUneEntreeVersLAccueil` |

Les deux premieres mutations ne sont couvertes par **rien d'autre** que les deux garde-fous nommes :
ce sont des mutations de mecanismes qui n'existaient pas avant ce commit, il n'y a donc pas d'etat
anterieur auquel les comparer. La troisieme etait deja couverte a `HEAD` par `EnTeteEtMenuTest` et par
l'ancien epinglage ; sa couverture a donc **change de porteur sans laisser de trou**, ce que la
troisieme ligne mesure.

Deux jeux fautifs neufs, permanents dans la suite, sous
`adapters/adapter-web/src/test/resources/contreexemple/LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest/` :

- `fragment-sans-marqueur` : deux ecrans inseres dans la meme zone, l'un declare le marqueur, l'autre
  l'oublie. La feuille de ce jeu declare bien le masquage, donc c'est l'oubli du gabarit qui est juge
  et rien d'autre. C'est l'objet neuf d'ADR-0013 : un jeu de gabarits que personne n'a inscrit nulle
  part, portant la faute qu'un fragment neuf commettra ;
- `feuille-sans-masquage` : la feuille perd sa regle, et elle est jugee **avec les gabarits de la
  production**. C'est la forme de la regression qui ne se voit pas dans un diff de gabarit.

**Ce que la premiere ligne du tableau prouve accessoirement, et qui repond a la question 2 de
`nadia-prof`** : retirer la regle de masquage fait revenir les appariements avec `.proposition` et
`.encore`. L'appariement depend donc reellement de la feuille, par la seule voie de `masquages()`.
Sans cet amendement, il n'aurait pas pu en dependre du tout - la logique d'appariement ne lisait que
la position dans le DOM et les conditions Thymeleaf, toutes deux **aveugles a une regle CSS**. C'est
la mesure de ce que l'appariement serait devenu : vert, et vide.

## Consequences

- **Le depot depend desormais du selecteur relationnel `:has()`.** C'est la consequence la plus
  serieuse et je ne l'habille pas : `:has()` est du CSS recent, et un navigateur qui ne le connait
  pas ignore la regle entiere - l'entree de menu redeviendrait visible sur les ecrans de jeu, sans
  aucun signal. **Le mode de defaillance est silencieux cote enfant**, meme si aucun automate n'en
  est complice. Ce qui rend ce risque acceptable ici : la degradation ramene exactement l'etat que
  `nadia-prof` vient d'auditer et de juger "valide sous reserve", pas un ecran casse ; et le depot
  vendorise deja Bootstrap 5.3.8, dont la ligne de base navigateurs est de la meme generation. Ce qui
  me ferait changer d'avis : une cible de deploiement nommee ou `:has()` manque.
- **Une classe de gabarit qui ne porte aucun style.** `ecran-de-jeu` dit un etat, pas une apparence.
  `HabillageDesEcransTest.aucuneClassePoseeSurUnElementNEstOrpheline` l'accepte parce que la feuille
  la nomme dans le selecteur de masquage - verifie, pas suppose. Je note l'inconfort : un lecteur
  peut chercher un bloc `.ecran-de-jeu { }` et n'en pas trouver.
- **Un couple declaratif a deux termes, dans deux fichiers de nature differente**, un gabarit et une
  feuille. C'est plus fragile qu'une condition unique, et c'est precisement pourquoi le garde-fou
  neuf existe : sans lui, la desynchronisation des deux termes serait muette.
- **Le mecanisme de traversee de frontiere d'ADR-0017 perd sa population de production.** Nomme
  ci-dessus, garde avec son motif.
- **Trois etats de l'ecran de jeu perdent une porte visible sur cinq**, du point de vue du nombre de
  boutons : c'est le but, et c'est `nadia-prof` qui l'a mesure. Ce n'est pas ma decision et je ne la
  reinstruis pas.

## Alternatives considerees

1. **`hx-swap-oob`.** Rejetee : declencherait immediatement la dette explicite d'ADR-0020, et
   demanderait un identifiant plus la duplication du balisage de l'en-tete dans trois fragments.
2. **Un `th:if` cote serveur sur l'entree de menu.** Rejetee : faux par construction, l'en-tete n'est
   pas re-rendu par un echange htmx.
3. **Deplacer l'entree de menu dans la zone echangee**, ou le serveur la controle entierement.
   Rejetee, et c'est la seule dont le rejet ne m'appartient pas entierement : elle detruirait le
   critere d'acceptation de S6-1 et le point 2 de `DEC-0012`, que `nadia-prof` a valides le meme jour,
   et elle ferait a S6-1 exactement ce qu'elle reproche a S6-9 de faire a S3-6. Je ne prends pas cette
   liberte.
4. **Supprimer l'entree de menu.** Rejetee pour la meme raison, et `nadia-prof` s'est explicitement
   interdit de la demander.
5. **Supprimer l'extension d'ADR-0017 du 2026-08-07 plutot que l'amender.** Rejetee : le mecanisme est
   correct et il est le seul a fermer le point 5 d'ADR-0017. Ce qui etait faux n'est pas le mecanisme,
   c'est l'hypothese implicite qu'un bouton hors zone est visible partout.
6. **Ne rien changer a `LActionNeDeclarePasMoinsQueLaSortieTest`, et livrer seulement le masquage.**
   Rejetee, et c'est le coeur de la question 2 : la suite serait restee **entierement verte** en
   confrontant des boutons que l'enfant ne voit plus ensemble. C'est le defaut que la retro de s05 a
   sanctionne deux fois, et il aurait ete de mon fait.

## Ce qui reste non gardable, et le dit

- **Que l'entree de menu soit effectivement invisible sur les cinq etats.** Propriete du rendu.
  `nadia-prof` l'a nommee comme sienne et annonce qu'elle la verifiera en jouant, etat par etat, y
  compris l'ecran d'erreur.
- **Que `:has()` soit honore par le navigateur de l'enfant.** Aucun automate du depot ne rend une
  page ; le garde-fou verifie que la regle est ecrite, pas qu'elle s'applique. C'est la meme limite
  qu'ADR-0017 nomme pour lui-meme depuis s05, et elle vaut ici avec une consequence plus large,
  puisqu'il s'agit d'une regle dont l'echec entier est possible plutot que d'une valeur mal lue.
- **Que "Les jeux" soit dechiffrable au CP.** `nadia-prof` a ecrit son avis - le `x` final muet ne se
  deduit pas, mais le mot nomme ce que l'enfant fait - et annonce qu'elle le rejugera sur le libelle
  rendu.
