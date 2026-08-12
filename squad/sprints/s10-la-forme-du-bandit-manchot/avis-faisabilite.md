# [Sprint s10] Avis de faisabilite - theo-archi

- **Date** : 2026-08-12
- **Branche** : `s10-theo-archi`, worktree dedie, descend de `round-two` (`bash outils/verifier-lignee.sh` : ancetre commun trouve)
- **Aucune story ne m'a encore ete routee** : ce document n'est donc pas un avis story par story mais
  l'eprouve des trois constats qui m'ont ete donnes a verifier, plus les contraintes de mise en oeuvre
  qui en decoulent pour le lot du manche.
- **Note de nommage** : ce repertoire de sprint est le premier ecrit pour s10. Si `sofia-sm` ou
  `camille-po` nomme le sprint autrement, ce fichier suit le repertoire ; le nom n'est pas de mon
  ressort.

## 1. Les trois constats, eprouves plutot que crus

Methode commune : mutation reelle de `adapters/adapter-web/src/main/resources/static/css/recreo.css`,
`mvn -B -pl adapters/adapter-web test` sur un JDK 21.0.10, mutation annulee et `git diff` verifie apres
chaque cas.

### Constat 1 - `.cabinet-bandeau::after` avec `clip-path` est vert : **exact**

Mutation `.cabinet-bandeau::after { content: ""; position: absolute; clip-path: polygon(...) }` :
`LHabillageNeRogneJamaisUnElementPressableTest` reste **vert**, et tout le module avec lui.
`classesDuSelecteur(".cabinet-bandeau::after")` rend `{cabinet-bandeau}`, absent de la population des
ancetres de pressables - laquelle contient bien `cabinet`, `propositions`, `ecran-de-jeu`,
`container`, `zone-jeu`, `entete`, `entete-contenu`, `jeux`, `menu`, `list-unstyled`. Le manche est
faisable sur ce pseudo-element sans `ADR` supplementaire du cote du rognage.

**Corollaire : exact aussi.** Mutation `.cabinet .cabinet-bandeau::after { ... clip-path ... }` :
**rouge**. Le garde-fou lit les classes du **selecteur entier** et non celles de son sujet, donc
nommer `.cabinet` devant suffit a le declencher. **Contrainte pour le lot** : ecrire le selecteur
court, `.cabinet-bandeau::after`. Ce n'est pas une preference de style, c'est la difference entre vert
et rouge.

### Constat 2 - `.cabinet::after` avec `clip-path` serait rouge : **exact sur le premier motif, non verifiable sur le second**

Mutation `.cabinet::after { ... clip-path ... }` : **rouge** sur
`LHabillageNeRogneJamaisUnElementPressableTest`. La classe du selecteur est `cabinet`, ancetre reel des
colonnes depuis S8-1. Premier motif confirme.

**Le second motif n'est pas un fait du depot mais une consequence de mise en page**, et je le dis
plutot que de le relayer : "un debordement du flanc en ferait le payeur unique d'un flanc sur les 12 px
de `.container`" est un raisonnement sur le rendu, et le rendu n'est garde par aucun automate ici
(`ADR-0025`). Il ne change rien au verdict - le premier motif suffit, et il est mesure - mais il ne doit
pas etre cite comme s'il l'etait.

### Constat 3 - les trois clauses de non-vacuite tiennent : **exact, avec une precision de porteur**

- `content` declare par au moins une regle maison : oui - `.cabinet-bandeau::before`,
  `.proposition::after`, et les deux barres de `.signal.est-faux .icone-signal::before/::after` ;
- `clip-path` sur une classe d'action : oui - `.proposition::after`, seule regle de la feuille dans ce
  cas, l'etoile du bandeau n'y entrant pas (`cabinet-bandeau` n'est pas une classe d'action) ;
- au moins une animation : oui - `[data-arrivee=par-un-geste] .proposition::after`.

**Precision** : ce n'est pas `.proposition::after` qui porte les trois. Il porte `content` et
`clip-path` ; l'animation est portee par une **autre** regle, de selecteur different
(`[data-arrivee=par-un-geste] .proposition::after`). La distinction compte parce que la clause 10
compare les deux par leur sujet : c'est ce qui la fait fonctionner, et non un hasard d'ecriture. Le lot
du manche ne touche aucune des trois.

## 2. `CabinetDeLaMachineTest` : entrer un selecteur dans le perimetre epingle

**Corrige apres mesure, et la version precedente de cette section etait le piege qu'elle pretendait
nommer.** Elle demandait que le jeu fautif recoive le bloc du selecteur neuf avec sa dimension en
pixels, et s'arretait la. Applique a la lettre, selecteur ajoute apres `.cabinet-bandeau::before` :
`Tests run: 8, Failures: 0`. Vert, et le selecteur neuf jamais eprouve - la boucle echouait au premier
fautif rencontre et `hasMessageContaining` epinglait ce nom-la en clair. Une contrainte dont le
resultat depend d'un ordre est une contrainte qu'un developpeur applique de travers sans le savoir.

Le garde-fou a donc change de forme plutot que la consigne de formulation : `CabinetDeLaMachineTest`
rassemble tous les selecteurs fautifs avant d'echouer, et `chaqueSelecteurEpingleEstEprouveParLeJeuFautif`
derive son attente de `SELECTEURS_DU_CABINET`. Detail, obligations et mesures de morsure : `ADR-0033`
point 7.a.

**Contrainte pour le lot, quels que soient les selecteurs livres et leur nombre** : tout selecteur
ajoute a `SELECTEURS_DU_CABINET` existe aussi dans
`contreexemple/CabinetDeLaMachineTest/dimension-en-pixels/`, ou il porte une dimension en pixels. Rien
d'autre a ecrire, et la position dans la liste n'a aucun effet. L'oubli est rouge et nomme le
selecteur oublie ; ce n'est plus une consigne a suivre, c'est un garde-fou a satisfaire.

Je n'ajoute moi-meme aucun nom de selecteur : la regle n'a d'objet que le jour ou la regle de
production existe, et je n'ecris pas un nom de selecteur dans un test avant que le code ne le porte
(dette n°1 de la version precedente). Ce que j'ai livre est la forme qui rend l'ajout sur.

## 3. La clause d'immobilite : `ADR-0033`, et non un amendement d'`ADR-0030`

Ecrite et executable dans le meme commit :
`adapters/adapter-web/src/test/java/com/recreo/adapter/web/LHabillageResteImmobileTest.java`, cinq cas,
trois jeux d'artefacts neufs. Le detail - perimetre derive par complementaire, prefixe plutot
qu'enumeration, exclusion mesuree de `transform`, limite nommee de la surcollecte Thymeleaf, trou de
`prefers-reduced-motion` - est dans `ADR-0033`.

**Forme retenue et pourquoi** : un `ADR` numerote plutot qu'une clause 11 d'`ADR-0030`, parce qu'il
**renverse un refus explicitement ecrit dans `ADR-0030`** et etablit que son motif etait faux. Un
renversement loge dans le fichier qui portait le refus n'apparait pas dans l'index : un lecteur verrait
`ADR-0030` inchange. `ADR-0030` reste `Accepte`, son entree d'index porte desormais la mention du
renversement.

## 4. Faisabilite d'un habillage dessine

**Le perimetre a change depuis la redaction de cette section, et je le note plutot que de laisser lire
une liste perimee.** `nadia-prof` a refuse le manche du fronton apres l'avoir vu rendu, et demande un
levier a boule sur le flanc du meuble, un visage souriant et une enseigne nommee sur le fronton. C'est
son arbitrage, pas le mien. Ce qui suit reste vrai en tant que **faisabilite d'un habillage dessine et
immobile** ; ce qui demande un avis neuf, le jour ou les stories seront ecrites, est le decoupage de
ces trois decors et leur cout en pixels sous le pli - la contrainte de cout, elle, n'a pas bouge d'un
pixel.

**Faisable en l'etat, sans `ADR` supplementaire**, sous trois contraintes, toutes tenues par un
garde-fou existant apres ce commit :

1. selecteur court, et qui ne nomme aucune classe ancetre d'un pressable : un decor decoupe ne pend
   pas de `.cabinet` (constat 1, corollaire, et constat 2) - cela vaut pour le levier de flanc autant
   que pour le fronton ;
2. `position: relative` sur le bloc qui porte le pseudo-element est le seul ancrage a ajouter, et il ne
   change aucune dimension ;
3. tout selecteur qui entre dans `SELECTEURS_DU_CABINET` arrive **avec** son bloc dans le jeu fautif
   (section 2), quel que soit leur nombre.

Et rien d'autre : ni `overflow` (`ADR-0030` point 2), ni `transition` ni pseudo-classe interactive
(`ADR-0033`), ni `content` autre que la chaine vide (`ADR-0027` clause 7), ni image chargee
(`ADR-0030` point 3).

Ce que je ne dis pas : que le manche ressemble a un manche, qu'il soit lisible a 360 px, ou qu'un
enfant comprenne qu'on n'y touche pas. C'est a `nadia-prof`, et sa condition C3 reste plus large que
la moitie que je viens d'en prendre.

## 5. L'obstacle de la retro de s09 : **corrige dans ce sprint**, pas reporte

`AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` traversait la production sans examiner un seul
indice, et sa clause de non-vacuite gardait la mauvaise population - les **paquets**, jamais les
**indices**. Le constat de la retro est exact, je l'ai relu dans le code avant de le corriger.

**Ce que je ne pouvais pas faire, et il faut le dire** : rendre la clause mordante sur le contenu
livre. Zero indice de production porte la forme generale non temperee, la correction de s07 les ayant
tous remplaces. Exiger "au moins un indice examine" aurait donc rougi la CI sans qu'aucun contenu soit
en cause. **Ce que j'ai fait a la place**, en trois gestes :

1. la clause **rend** le nombre d'indices examines : c'est sa vraie population, et c'est elle que les
   appelants confrontent desormais a un chiffre ;
2. le cliquet de l'etat reel vit **dans le meme test** que la clause, et non plus dans un second
   parcours qui relisait le meme motif a cote. Deux lectures pouvaient s'ecarter ; il n'y en a plus
   qu'une. Le plafond vaut exactement la taille de la dette, zero ;
3. un troisieme jeu d'artefacts (`general-tenu`) etablit que ce compteur **n'est pas coince a zero pour
   une raison de code** : sur un paquet portant la forme generale sans la dementir, il vaut 1 et la
   clause passe.

**Preuve de morsure, deux mutations reelles, annulees.** Compteur force a zero : seul
`unPaquetDontLaRegleGeneraleEstTenueEstExamineEtPasse` rougit - **la clause de production et son
cliquet restent verts**, ce qui mesure precisement pourquoi le troisieme jeu etait necessaire. Bonne
reponse du second item de `general-tenu` passee de "fermes" a "fermons" : rouge, message "l'indice de
[...] enonce une regle generale (terminaison '-es') que la bonne reponse "fermons" [...] dement" - la
comparaison tourne bien sur du contenu.

**Ce que cette correction ne fait pas** : elle ne rend pas la clause mordante sur le contenu livre. Elle
rend **visible et chiffre** le fait qu'elle ne l'est pas, la ou on le lit. Le garde-fou reste ecrit pour
la regression, comme la clause de rognage d'`ADR-0030` l'a ete avant le cabinet. Ce que la retro de s09
lui reprochait - se lire comme une garantie sans en etre une - cesse ici ; ce qu'elle ne pouvait pas lui
reprocher - ne pas avoir d'objet en production - reste vrai et est desormais ecrit dans le fichier.

Aucun `ADR` n'est ecrit pour cette correction : `ADR-0003` porte deja la doctrine, et un `ADR` qui
documente apres coup une correction sans regle neuve est precisement ce que je m'interdis.

## 6. Porte technique

`mvn -B clean verify` sur un JDK **21.0.10** designe par `JAVA_HOME` le temps de la commande (le `java`
du `PATH` de ce poste est un JRE 8, et le fichier de toolchains y est enregistre sous un nom que Maven
ne lit pas ; constat rendu par `bash outils/verifier-jdk.sh`) : **BUILD SUCCESS**.

**Chiffres et methode dans le meme paragraphe.** Apres un `clean`, lignes `Results:` du journal, module
par module : `domain` 43, `application` 7, `adapter-web` 183, `adapter-content` 63, `bootstrap` 77 en
surefire et 3 en failsafe. Total **376 tests**, contre 371 sur `round-two` a `b15bda1` : les 5 de plus
sont les 5 cas de `LHabillageResteImmobileTest`, et rien d'autre - `adapter-content` ne bouge pas parce
que la correction de la section 5 supprime un test et en ajoute un.

**Regles ArchUnit : 28, inchange**, mesure rejouee et non relayee - recherche de la forme
`ArchRule <nom> =` dans les `src/test/java` des cinq modules porteurs de tests declares au pom
agregateur, executee dans ce worktree seul : 28. Le meme balayage rend 27 `@ArchTest` ; la 28e est celle
d'`AssertionsWebTest`, executee par un `.check()` explicite. Aucune clause de ce commit n'est une
`ArchRule` : ArchUnit lit du bytecode, ni une feuille de style ni un gabarit n'y sont visibles.

**Je ne fusionne pas et je ne pousse pas.** Aucun `Done` pedagogique n'est prononce ici, et le manche
n'a pas ete joue.
