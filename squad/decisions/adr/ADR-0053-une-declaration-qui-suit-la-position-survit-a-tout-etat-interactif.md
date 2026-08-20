# ADR-0053 - Une declaration qui suit la position survit a tout etat interactif, et la relation de cascade entre deux regles maison cesse de vivre en commentaire

- **Statut** : Accepte, sa regle ecrite dans le meme commit
  (`UneCouleurDePositionSurvitATousLesEtatsTest`, une clause, deux morsures - un chemin de cascade chacune - et un cas vert).
- **Sprint** : s17. **Date** : 2026-08-19 (decision et mesures) ; **finalise le 2026-08-20**,
  apres fusion du correctif du proprietaire (`marc-dev`, `f66504c`) et retrait de la correction
  provisoire que j'avais prise en son absence. **Auteur** : theo-archi.
- **Arbre mesure** : `round-two` a `246d149` (SHA releve par `git rev-parse round-two`, pas repris
  d'un message).
- **Route par** : `nadia-prof`, qui a mesure sur l'increment livre que `S17-D` avait defait `S15-8`.
- **Complete** : `ADR-0046` (amendement 3, meme invariant sur une autre population), `ADR-0014`
  (point 5, l'ordre de chargement ne joue qu'a specificite egale), `ADR-0013`.

## Contexte

`S15-8` est une condition pedagogique de `nadia-prof`, validee en s16 : **la couleur de fond qui suit
la position d'une proposition survit sous le doigt**. Elle sert un principe deja arbitre - *"la
couleur suit la position, jamais la reponse attendue"* - donc un decor, pas un second indice.

**Ce qui s'est passe.** Le correctif `S17-D` a ajoute l'ancetre `#jeu` devant `.proposition:active`
pour battre une regle tierce plus specifique. Sous un appui reel, `#bdd2ef`, `#a9dcd8` et `#dcc8ed`
deviennent toutes `#2e6f9e` et le texte passe au blanc : la regle d'etat est passee **au-dessus** des
quatre regles de position. `nadia-prof` l'a trouve en jouant ; **aucun test du depot ne l'a vu**.

**Ce qui tenait la propriete, et c'est le fond de cet `ADR`** : une relation de cascade entre deux
blocs d'un meme fichier, **ecrite en commentaire et executee par personne**. Le fichier portait bien
l'avertissement, a deux lignes de la modification - *"ne jamais deplacer ce bloc apres les quatre
regles de position"* - mais **relever la specificite produit le meme effet, et l'avertissement ne
couvrait pas ce geste**. Ni `nadia-prof` ni moi ne comptons cela comme une negligence de `marc-dev` :
une consigne de redaction qui depend de la vigilance est un souhait, et `CLAUDE.md` a un mot pour ca.

**Et je nomme ma part.** Mon propre amendement 3 d'`ADR-0046`, ecrit le meme jour, a verifie que
`S17-D` **tient au sens de la specificite** - 57 exigences comparees, toutes satisfaites. Ce vert
n'etait pas faux : il etait **etroit**. Il compare la feuille maison a la feuille **tierce**, et ce
defaut-ci vit entre **deux regles maison**. J'ai ferme une porte et laisse la fenetre voisine ouverte,
le meme jour, avec la meme phrase - *declarer ne suffit pas, il faut l'emporter* - appliquee a une
seule des deux populations.

**Les deux formes que prenait la protection, mesurees sur la feuille servie**, et c'est pourquoi la
regle ne peut pas se contenter de comparer des specificites :

| Pressable | Regle d'etat | Regle de position | Ce qui protegeait |
|---|---|---|---|
| `.proposition` | `.proposition:active` **(0,2,0)** | `.propositions .proposition:nth-child(4n+1)` **(0,3,0)** | une **marge** de specificite |
| `.proposition.deja-essayee` | `.proposition.deja-essayee:active` **(0,3,0)** | la meme, **(0,3,0)** | une **egalite**, plus l'ordre du fichier |

`S17-D` a fait passer la premiere a **(1,2,0)** - l'identifiant domine la colonne des classes, quel
que soit le compte de celles-ci. *(`nadia-prof` lit (0,3,0) -> (1,3,0) la ou je lis (0,2,0) ->
(1,2,0) : nous comptons `:active` dans la meme colonne, l'ecart d'une unite vient du selecteur exact
qu'elle a releve. **La conclusion est identique et ne depend pas de ce chiffre** : des qu'un
identifiant entre, la colonne des classes ne decide plus.)*

## Decision

1. **Une regle maison dont le sujet designe une position ne peut pas etre recouverte, sur une
   propriete qu'elle declare, par une regle maison a etat interactif qui s'applique au meme
   element.** Ce que la clause compare n'est pas une specificite : c'est **l'issue de la cascade** -
   la plus specifique gagne, et a specificite egale la derniere ecrite. Les deux formes du tableau
   ci-dessus sont donc couvertes par une seule regle.
2. **Le perimetre protege se derive, il ne s'enumere pas.** Une regle est "de position" quand son
   **sujet** porte une pseudo-classe positionnelle (`:nth-child()`, `:first-child`, ...). Aucun
   selecteur n'est ecrit dans le test. Une cinquieme teinte, un habillage positionnel neuf ou un etat
   neuf entrent dans le controle sans que le garde-fou change.
3. **La regle est generale, mais pas plus generale que ce qu'elle peut promettre.** Elle ne dit pas
   "aucun etat ne recouvre une declaration de repos" - un etat qui recouvre le repos est **la
   definition** d'un etat, et `.demarrer:hover` doit continuer de peindre par-dessus `.demarrer`. Ce
   qui est protege est ce dont la valeur **depend de la position** : la position n'est pas une
   apparence que l'interaction a le droit de renegocier, c'est un reperage.
4. **Elle vit dans son propre garde-fou, pas dans celui d'`ADR-0046`.** Meme invariant, autre
   population : `ADR-0046` compare la feuille maison a la feuille **tierce**, avec un nom, un
   perimetre et un cliquet de dette qui parlent de la bibliotheque. Y loger une clause maison contre
   maison ferait promettre a ce garde-fou plus que son nom - la faute qu'`ADR-0015` a deja
   sanctionnee.

## Regle executable

`adapters/adapter-web/src/test/java/com/recreo/adapter/web/UneCouleurDePositionSurvitATousLesEtatsTest.java`

```java
private static boolean lEmporteSur(RegleClassee gagnante, RegleClassee perdante) {
    int forceGagnante = AucunEtatDePressableNEstAbandonneAuTiersTest.specificite(gagnante.selecteur());
    int forcePerdante = AucunEtatDePressableNEstAbandonneAuTiersTest.specificite(perdante.selecteur());
    if (forceGagnante != forcePerdante) {
        return forceGagnante > forcePerdante;
    }
    return gagnante.rang() > perdante.rang();
}
```

Pour chaque pressable rendu, pour chaque regle de position qui s'y applique, pour chaque regle a etat
interactif qui s'y applique, pour chaque propriete que les deux declarent (relation `touche`, qui lit
aussi le couple raccourci/longhand) : la regle de position doit **l'emporter**.

- **Perimetre derive** (`ADR-0013` obligation 1) : les regles viennent du balayage des feuilles maison
  **dans l'ordre du fichier** (`reglesEcritesMaison`, la meme lecture que les autres clauses de ce
  paquet - deux lectures libres de diverger finiraient par juger deux feuilles differentes), les
  pressables du balayage des gabarits (`etatsRendus`), les etats interactifs de la meme source que la
  clause d'`ADR-0046` (`etatsInteractifsDe`).
- **Derivation totale** (obligation 2) : une specificite illisible fait echouer la lecture
  (`ADR-0046`, amendement 3) plutot que d'etre ignoree.
- **Non-vacuite** (`ADR-0003`) : si aucun couple (position, etat) sur une meme propriete n'est
  compare, la clause echoue au lieu de passer verte a vide.
- **Deux morsures, une par chemin de defaillance de la cascade.** *(1)* Sur **la feuille servie
  reelle remise dans la forme exacte du defaut** : le sujet presse rendu a son ancetre identifiant,
  donc (1,2,0) contre (0,3,0). Aucune fixture de ma main - **cette forme-la a reellement ete
  servie**, et c'est celle que `nadia-prof` a mesuree sous appui. Elle prouve le chemin de la
  **specificite**. *(2)* Sur un **jeu fautif neuf, jamais livre** : deux regles a la **meme**
  specificite (0,3,0), la regle d'etat ecrite **apres** - aucun identifiant, aucune marge, le defaut
  tenant au seul **ordre de redaction**, c'est-a-dire exactement ce que le commentaire de la feuille
  demandait de ne jamais faire. La premiere morsure ne couvre pas ce chemin-la.
- **Un cas qui doit rester vert** : le meme jeu fautif, les deux blocs remis dans l'ordre. Sans lui,
  la clause serait une interdiction de declarer un etat sur un pressable colore par sa position, alors
  qu'elle interdit seulement qu'il la **recouvre** - la lecon de la seconde mutation d'`ADR-0025`.
- **Etat mesure du defaut, par la clause elle-meme sur l'arbre `246d149`** : **8 motifs** - les quatre
  regles de position x `background-color` et `color` - avec `#jeu .proposition:active` a **(1,2,0)**
  contre **(0,3,0)**. Le garde-fou reproduit le defaut a partir des seules declarations, sans
  navigateur.

**La clause est verte sur les deux formes de correction, et c'est ce qui prouve qu'elle garde une
propriete et non une redaction.** Le correctif du proprietaire (`marc-dev`, commit `f66504c`, fusionne
dans ce lot) **abaisse le sujet presse** a `.propositions .proposition:active`, soit (0,3,0), a
egalite avec les regles cycliques, l'ordre du fichier redevenant decisif. La forme inverse - relever
les quatre regles de position a (1,3,0) - que j'avais prise a titre provisoire pendant que son lot
n'existait pas, la satisfaisait aussi. **Elle est retiree** : deux corrections du meme defaut n'ont
pas a coexister, et la sienne evite l'identifiant que la mienne repandait. Mesure sur l'arbre
fusionne : les quatre methodes de cette classe, les 2 de son garde-fou de lot et les 4 de celui de
`S17-D` passent ensemble.

**Ce n'est pas une regle ArchUnit** : l'objet juge est une feuille de style. Le compte de 29 ne bouge
pas.

## Ce que cette regle ne voit pas, nomme

- **Aucun etat rendu.** Elle lit des **declarations**, jamais une couleur sous le doigt. Aucun
  garde-fou de ce depot ne mesure un etat rendu (`ADR-0025`, `ADR-0028`), et c'est exactement pour
  cela qu'un correctif vert de bout en bout a pu defaire `S15-8` sans que rien ne le dise.
  `nadia-prof` reste **seule garde** de l'effet ; ce qui est garde ici est la **cause** declarative.
- **Le prefixe d'ancetre n'est verifie ni d'un cote ni de l'autre** - c'est la dette que j'ai nommee
  la veille sur `ADR-0046`, et **cette regle-ci ne la ferme pas** (voir la section suivante). Une
  regle d'etat portee par un ancetre que le pressable n'a pas serait comptee comme recouvrante alors
  qu'elle ne s'applique pas : ici la defaillance va vers le **faux rouge**, la ou dans `ADR-0046`
  elle va vers le faux vert. Les deux gardes ont le meme angle mort et le paient dans des sens
  opposes.
- **Les regles nichees sous une regle-`@`** : `reglesEcritesMaison` les lit avec leur prelude en
  origine, mais une regle de position ou d'etat qui n'existerait que sous une `@media` serait comparee
  a des regles qui, elles, ne s'appliquent pas dans la meme condition. Aucune n'existe aujourd'hui
  dans la feuille maison sur ces sujets ; le jour ou l'une apparait, cette clause pourra rougir a
  tort, et c'est ce paragraphe qui le dira.
- **Le sens de la couleur.** Que `#bdd2ef` soit la bonne teinte, qu'elle contraste assez, qu'elle ne
  porte pas un second indice : tout cela est de `nadia-prof` et d'`ines-design`, et rien ici n'y
  touche.

## Le lien avec la dette d'ancetre que j'ai nommee la veille

J'ai ecrit, dans l'amendement 3 d'`ADR-0046` : *"le prefixe d'ancetre d'une regle maison n'est pas
verifie non plus"*, avec pour declencheur *"le premier lot qui ajoute un ancetre a une regle d'etat
dont le pressable peut etre rendu hors de cet ancetre"*. **Le lot qui a declenche le defaut du jour
est bien celui qui ajoute un ancetre a une regle d'etat - mais ce n'est pas cette dette-la qui s'est
realisee**, et confondre les deux ferait croire qu'elle est payee.

- **La dette** porte sur un ancetre qui **ne s'applique pas** : la clause d'`ADR-0046` crediterait
  alors une specificite que l'enfant n'obtient jamais - un **faux vert**. Verifie le jour meme :
  `.demarrer` vit bien sous `<main id="jeu">`, donc l'ancetre s'applique et la dette **ne s'est pas
  realisee**.
- **Le defaut du jour** porte sur un ancetre qui **s'applique parfaitement** et qui fait gagner la
  regle d'etat **trop haut**, contre une autre regle maison qu'aucune clause n'examinait.

**Meme geste, deux trous voisins, deux directions opposees.** Cet `ADR` ferme le second. Le premier
reste ouvert, avec son declencheur inchange, et il est desormais partage par **deux** garde-fous - ce
qui augmente son cout sans changer sa nature.

## Consequences

- **Toute regle d'etat neuve sur une proposition devra rester sous les regles de position**, ou faire
  monter les regles de position dans le meme geste. C'est une contrainte reelle sur les lots de style
  a venir, et elle est desormais dite par un rouge plutot que par un commentaire.
- **Le commentaire de la feuille reste utile mais cesse d'etre la garantie.** Je ne le retire pas : il
  explique le *pourquoi*, que le test ne dit pas. Ce qui change est qu'il n'est plus le seul.
- **Un faux rouge est possible** (ancetre non applicable, regle sous `@media`), et sa resolution est
  d'ecrire la regle autrement ou d'amender cette clause - jamais de la neutraliser.
- **Le cout de lecture du garde-fou** : il croise chaque pressable rendu avec chaque couple de regles
  maison. Sur la feuille servie c'est instantane ; sur une feuille dix fois plus grande, ce serait a
  revoir. Constate, pas anticipe.

**Ce qui me ferait changer d'avis.** Si une deuxieme famille de declarations devait etre protegee de
la meme facon (par exemple une couleur qui suivrait le niveau plutot que la position), la question
deviendrait *"quelles declarations sont protegees ?"* et la reponse ne serait plus derivable d'une
pseudo-classe : il faudrait un marqueur explicite dans la feuille, decide avec `nadia-prof`. Je ne
l'invente pas aujourd'hui pour une seule famille.

## Alternatives considerees

1. **Etendre la clause d'`ADR-0046` a la population maison contre maison.** Ecartee : ce garde-fou
   promet, par son nom et par sa Javadoc, quelque chose sur la bibliotheque **tierce** ; son cliquet
   de dette compte des exigences nees de la feuille vendorisee. Une clause maison contre maison y
   serait invisible a la relecture et fausserait le sens de sa dette.
2. **Un registre de toutes les egalites de specificite dont depend une propriete rendue.** Tentant -
   c'est la forme d'`ADR-0051` - et **il n'aurait pas attrape ce defaut** : le couple qui a casse
   n'etait pas une egalite mais une **marge** (0,2,0 contre 0,3,0). Un registre d'egalites aurait
   declare la protection de `.deja-essayee` et laisse passer celle qui a reellement cede. C'est la
   mesure de pourquoi la regle doit porter sur l'issue de la cascade, pas sur un cas particulier de
   cette issue.
3. **Interdire les identifiants dans les selecteurs maison.** Ecartee : `#jeu` est la sortie que
   `S17-D` a retenue pour battre une regle tierce, et l'interdire rouvrirait le defaut qu'il ferme.
   Ce n'est pas l'identifiant qui est fautif, c'est la relation non gardee entre deux regles.
4. **Declarer la couleur de position a chaque etat** (douze regles de plus, une par teinte et par
   etat). Ecartee : elle repare le cas du jour et n'empeche rien ensuite - la treizieme regle d'etat
   ecrite dans six mois recouvrira de nouveau, et personne ne le saura.
5. **Ne rien encoder et durcir le commentaire.** Ecartee : le commentaire existait, il etait juste, il
   etait a deux lignes de la modification, et il n'a rien retenu. C'est la definition meme d'un
   souhait.
