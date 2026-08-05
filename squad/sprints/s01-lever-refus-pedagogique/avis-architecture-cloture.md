# [Sprint s01] Avis d'architecture avant review - theo-archi

- **Date** : 2026-08-05
- **Auteur** : theo-archi
- **Arbre juge** : `round-two`, commit `d534451` (fusion des trois lots paralleles par `sofia-sm`),
  plus les trois corrections decrites au point 2.
- **Inventaire detaille** : `confirmation-archunit.md`, a cote de ce fichier. Ce document-ci porte
  le verdict, pas le tableau.

## Verdict

**Porte architecture OUVERTE pour la review.**

Motif, en une phrase : `mvn -B clean verify` est vert sur l'arbre fusionne (120 tests, 0 echec), les
**23 regles ArchUnit** sont executees et chacune a un contre-exemple qui la fait echouer, les deux
ADR du sprint ont desormais leurs regles ecrites **et mordantes**, et la fusion des trois worktrees
n'a introduit aucune dependance vers l'exterieur.

Cette porte est technique. Elle ne dit rien du Done pedagogique, qui appartient a `nadia-prof`
seule, et ne prejuge pas de son verdict.

## 1. ArchUnit sur l'arbre fusionne

```
mvn -B clean verify
```

**BUILD SUCCESS**, code de sortie 0, `Total time: 44.300 s`, sur un JDK 21.0.10 - la version de la
CI. 120 tests, 0 echec, 0 ignore (31 `domain`, 7 `application`, 26 `adapter-web`,
5 `adapter-content`, 49 + 2 `bootstrap`).

**23 regles ArchUnit executees**, contre 21 a la cloture de s00. Les deux nouvelles sont celles
d'ADR-0011. Repartition : 22 dans `bootstrap`, 1 dans `adapter-web` (dispense 2 d'ADR-0003 : sa
regle juge du code de test de son propre module).

Aucune regle n'a ete supprimee ni affaiblie pendant le sprint. Ce n'est pas une deduction a partir
du vert : le `git diff` du sprint sur les fichiers de regles ne contient que des ajouts.

**Reserve, la seule, et elle ne ferme pas la porte.** Le premier `clean verify` de ce passage a
rendu deux echecs dans `LesReglesMordentTest`, puis un `BUILD SUCCESS` a l'execution suivante sans
qu'une ligne ait change. Huit tentatives de reproduction, toutes vertes. Le rapport Surefire fautif
etait deja ecrase quand je l'ai lu : je ne sais pas quels cas ont echoue et je n'invente pas de
cause. Le contexte, lui, est nommable : d'autres agents ecrivaient dans le meme arbre pendant ce
passage - au point qu'une reinitialisation dure y a detruit tout le travail non commite, le mien
comme celui des autres, et qu'il a fallu le refaire. Une construction Maven concurrente dans le meme
`target/` expliquerait un import de classes a demi reecrites. Hypothese, pas conclusion. La CI, qui
construit un checkout neuf sans ecrivain concurrent, tranchera - et si l'echec y reapparait, il
devient un defaut a traiter et non un artefact de poste. Detail dans `confirmation-archunit.md`.

## 2. Les deux ADR du sprint, et ce qui leur manquait

### ADR-0011 - complet, et il mord sur du vrai code

Ses deux regles existent dans `bootstrap/src/test/java/com/recreo/archi/GestionDesErreursTest.java`,
telles que l'ADR les annonce, et chacune a son test de non-vacuite dans `LesReglesMordentTest`. Le
test d'integration que l'ADR exigeait la ou ArchUnit ne peut rien
(`adapters/adapter-web/src/test/java/com/recreo/adapter/web/ErreurDeSessionPerdueTest.java`) existe
aussi, reproduit bien le scenario de `nadia-prof` et lit le fragment a jsoup, jamais a l'expression
reguliere.

Je ne me suis pas contente du vert. Deux mutations du **code de production**, mesurees puis
annulees :

- faire retourner `ResponseEntity<String>` a `GestionnaireDesErreurs.partieIntrouvable` rend
  `uneErreurNeRendPasUnCorpsBrut` rouge, sur le bon motif ;
- retirer `@ControllerAdvice` de la classe rend `uneAdviceDErreurExiste` rouge sur
  "failed to check any classes", c'est-a-dire que `allowEmptyShould(false)` fait bien son travail.

C'est le point qui m'importait : ces deux regles refusent le contournement reel, pas seulement une
classe ecrite pour etre refusee.

### ADR-0012 - il lui manquait deux choses, ecrites maintenant

L'ADR ne cree pas de regle : il s'appuie sur `DifficulteTest.leContratDeContenuNePorteQueDuTexte`
(ADR-0006), qui refuse deja tout champ non textuel dans le contrat de contenu. Ce raisonnement tient
et je le maintiens. Mais l'ADR nommait deux artefacts qui n'ont pas ete livres, et l'un des deux
etait faux.

1. **Le contre-exemple etait range dans le mauvais paquet.** L'ADR le placait dans
   `domain.content.contreexemple`. La regle porte sur `resideInAPackage("com.recreo.domain.content")`
   - le paquet exact, pas ses sous-paquets. Un contre-exemple range dans le sous-paquet aurait ete
   invisible pour elle : son test de non-vacuite aurait echoue sur "failed to check any classes",
   donc rouge pour un motif etranger a la faute qu'il pretend prouver. Ecrit au bon endroit :
   `bootstrap/src/test/java/com/recreo/domain/content/ReglageDeLongueurDePartieContreExemple.java`,
   avec `LesReglesMordentTest.uneLongueurDePartieDansLeContratDeContenuEstRefusee`. Mutation de
   controle : passer le champ de `int` a `String` rend ce test, et lui seul, rouge (1 echec sur 21).

   Sa valeur exacte, sans la gonfler : il n'ajoute aucun pouvoir de refus, la regle mordait deja sur
   un `int`. Il ajoute un point d'accrochage nomme - si quelqu'un assouplit un jour cette regle au
   motif qu'un "parametre de partie" merite un entier dans le contrat de contenu, le test rouge
   citera ADR-0012 et pas seulement la borne arithmetique d'ADR-0006. Une recommandation ecrite et
   jamais appliquee, c'est la dette n°1 de `main` en miniature ; je ne la laisse pas s'installer.

2. **La derniere ligne de son tableau de gardes de comportement etait restee sur le papier.**
   `CatalogueChargeableIT` demarrait bien chaque jeu du vrai catalogue, mais n'assertait rien sur
   `nombreDeManches`. Le trou etait etroit - un champ absent du vrai `catalogue.yaml` faisait deja
   echouer ce test, le chargeur levant avant de rendre le moindre `GameDefinition` - mais une valeur
   de zero serait passee sans un mot, et aurait produit une partie qui se termine au premier coup.
   Assertion ajoutee : `isPositive()`, deliberement pas une egalite. **Combien** de manches est un
   choix pedagogique qui ne m'appartient pas ; **au moins une** est une propriete de forme.

Les trois autres gardes de comportement du tableau d'ADR-0012 sont bien la : la fin de partie apres
`nombreDeManches` coups sur un contenu combinatoire infini (`BanditManchotTest`), la transmission de
`GameDefinition.nombreDeManches()` a `GameMechanic.start` (`PartieServiceTest`, par une mecanique
espion), et l'echec de chargement nommant fichier et champ (`YamlContentPackLoaderTest`, avec sa
fixture `nombre-de-manches-manquant`).

ADR-0012 est amende sur ces deux points, avec l'erreur nommee, et `ADR-index.md` porte la ligne
d'amendement correspondante.

## 3. Clean Architecture apres la fusion des trois worktrees

Verifie, aucune violation.

- **Dependances vers l'interieur** : `lesDependancesVontVersLInterieur` verte, et sa morsure est
  prouvee par `DomaineDependantDeLApplicationContreExemple`.
- **`domain` sans framework** : `leDomaineNeDependQueDuJdk` verte. Controle independant a la
  lecture : aucune occurrence de `org.springframework` ni de `jakarta.` dans `domain/src/main` ni
  dans `application/src/main`.
- **`application` sans Spring** : `lApplicationNeDependQueDuJdkEtDuDomaine` verte.
- **Spring seulement dans les adapters et `bootstrap`** : consequence des deux precedentes, plus
  `lesAdaptateursNeSeConnaissentPas`, verte elle aussi. Aucune reference a
  `com.recreo.adapter.content` depuis `adapter-web`.

**Le lot `ines-design`** n'a touche que `static/css/recreo.css` et quatre gabarits Thymeleaf, tous
dans `adapters/adapter-web/src/main/resources/`. Aucun fichier `.js` modifie, donc le budget
JavaScript d'ADR-0008 est intact. Les gabarits neufs ou remanies, `fragments/erreur.html` compris,
sont couverts sans modification de regle par les trois tests de `FrontieresDuJavaScriptTest`, qui
balaient tout `.html` sous `templates/` au lieu d'une liste ecrite en dur. Le signal juste/faux
reste porte par du CSS (`clip-path`, pseudo-elements) et une classe posee cote serveur par
`th:classappend` : aucune decision de verdict n'a migre vers le client.

**Le lot `lea-dev`** est celui que je regardais de plus pres, parce que faire remonter la longueur
de partie jusqu'a `GameMechanic.start` est exactement le geste qui peut faire fuiter le catalogue
dans le domaine. Il ne fuit pas, et c'est verifiable a la signature :

- `GameMechanic.start(ContentSource, RandomSource, int)` prend un `int`, pas un `GameDefinition` ni
  quoi que ce soit de `com.recreo.application..`. Le domaine ne gagne aucun type nouveau ;
- `GameDefinition` (couche `application`) porte le champ et cite des types du domaine
  (`ContentSource`, `ContentDescriptor`, `MechanicId`) : c'est le bon sens de dependance ;
- `PartieService.demarrer` est le seul endroit ou les deux se rencontrent, et il est dans
  `application`.

Si quelqu'un avait fait passer `GameDefinition` a `start`, deux regles seraient devenues rouges
(`leDomaineNeDependQueDuJdk` et `lesDependancesVontVersLInterieur`), toutes deux avec un
contre-exemple qui prouve qu'elles mordent. Ce chemin est garde, il n'a pas simplement eu de la
chance.

Une remarque de forme, hors perimetre de la porte et sans effet sur elle :
`BanditManchotSession` a gagne quatre champs d'etat et son constructeur prive en compte quatorze.
C'est encore lisible, mais c'est la trajectoire exacte de la dette n°6 de `main` (les records tres
larges a methodes de copie manuscrites). Je ne demande rien pour ce sprint - une abstraction se
justifie sur un usage constate, pas sur une pente redoutee - et je le note pour la retro.

## 4. Les trois garde-fous de CI

Les trois sont verts, et surtout : chacun est teste avant d'etre applique, ce qui est la substance
d'ADR-0009 et d'ADR-0010.

| Garde-fou | Job CI | Script de test | Cas | Resultat |
|---|---|---|---|---|
| Prefixe de commit par agent | `prefixes` | `verifier-prefixes-test.sh` | 15 | verts, sortie 0 |
| Plage de commits atteignable | `prefixes` | `commit-atteignable-test.sh` | 5 | verts, sortie 0 |
| Chemins de poste de travail (ADR-0010) | `chemins` | `verifier-chemins-test.sh` | 20 | verts, sortie 0 |

Le troisieme que la question laisse ouvert est celui de la **plage de commits** : ne pas confondre
"base absente" et "base reecrite donc inatteignable apres un push force", faute de quoi la CI echoue
sur `bad object`, pour un motif etranger a un prefixe manquant. Il est ne de l'amendement d'ADR-0009
du 2026-08-05, il a son script juge (`commit-atteignable.sh`), son script de test, et son etape
dediee dans le workflow avant la verification elle-meme.

Les deux juges ont aussi ete passes sur le materiau reel de ce sprint, pas seulement sur leurs cas
de test : les commits `05647a1..HEAD` portent tous un prefixe connu, dont le `[INES]` ouvert au
debut du sprint, et aucun fichier suivi ne contient de chemin de poste de travail ni de nom de
compte. Le garde-fou de chemins a d'ailleurs mordu pendant ce passage, sur un fichier de
configuration locale non suivi qui porte un chemin de JDK en dur : il n'a pas ete commite.

## Ce que cet avis ne dit pas

- Il ne prononce aucun Done pedagogique, ni ne l'anticipe. C'est `nadia-prof`, apres avoir joue
  l'increment, avec un motif ecrit dans `squad/conformite/`.
- Il ne dit rien de la qualite fonctionnelle des trois lots : c'est le rapport de `yanis-qa`.
- Il ne garantit pas qu'aucun de ces tests ne soit instable. Voir la reserve du point 1 : un echec
  transitoire a ete vu une fois et n'a pas ete reproduit, et c'est la CI qui le dira.
