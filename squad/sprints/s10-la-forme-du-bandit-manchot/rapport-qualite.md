# Rapport qualite - sprint 10 (la forme du bandit manchot)

- **Auteur** : yanis-qa
- **Date** : 2026-08-14
- **Worktree** : dedie, provisionne depuis `round-two` sur une branche neuve (`s10-yanis-qualite`),
  hors de l'arbre du depot principal. Lignee verifiee avant toute ecriture :
  `bash outils/verifier-lignee.sh` -> "le worktree sur la branche 's10-yanis-qualite' descend de la
  lignee de round-two (ancetre commun trouve dans son historique)".
- **Commit audite** : `643c6f4`, tete de `round-two` au moment de la saisine.
- **Constat de JDK** (`bash outils/verifier-jdk.sh`) : `java du PATH : 1.8.0_202 | JDK de
  compilation : 21.0.10 | JAVA_HOME : vide | toolchains lu par Maven : non (fichier present sous un
  nom non lu)`. Meme anomalie de poste que les sprints precedents. `JAVA_HOME` a ete pointe sur ce
  JDK 21 pour la duree de chaque commande citee ci-dessous, jamais ecrit dans un fichier commite.
- **Branche non fusionnee, hors de mon compte technique** : `s10-nadia-verdict-2` (tete `a88a3d4`).
  Verifie par moi : `git merge-base 643c6f4 a88a3d4` rend `643c6f4` - cette branche part bien de la
  tete actuelle de `round-two`, mais n'y est pas fusionnee. `git diff --stat 643c6f4 a88a3d4 -- '*.java' '*pom.xml'`
  ne rend rien : aucun code, aucun test, uniquement des fiches de conformite et `review.md`. Elle
  porte le **second verdict** de `nadia-prof` (REFUSE, M13 clause 1 a la revelation) et la
  correction du chiffre faux de `DEC-0025` traitee au point 4. Je la lis pour repondre aux points 3
  et 4 de ma saisine, mais **tant qu'elle n'est pas fusionnee, son contenu n'est pas l'etat de
  `round-two`** - je le dis a chaque fois que je m'appuie dessus.

Ce rapport ne recopie aucune mesure sans l'avoir soit rejouee, soit refutee - y compris les
garde-fous cites comme fermant un trou, que j'ai casses puis remis en etat, pas relus.

## 1. Porte technique - `mvn -B clean verify`

```
mvn -B clean verify
```

**Resultat : BUILD SUCCESS**, six modules en SUCCESS, aucun test ignore.

| Module | Tests |
|---|---|
| domain | 46 |
| application | 7 |
| adapter-web | 206 |
| adapter-content | 64 |
| bootstrap (surefire) | 77 |
| bootstrap (failsafe) | 3 |
| **total** | **403** |

**Confirme le chiffre annonce par `sofia-sm`.** Methode : somme de l'attribut `Tests run` du bloc
`Results:` de chaque module dans la sortie du reacteur Maven, apres un `clean` - c'est l'agregat
que Maven calcule lui-meme a partir des rapports XML, jamais une addition manuelle de fichiers, et
jamais un balayage depuis la racine du poste (qui verrait les rapports d'autres worktrees actifs a
cote du mien).

**28 regles ArchUnit, confirme par la meme methode que celle documentee dans `ADR-0033` et
`ADR-0034`** : recherche de la forme litterale `ArchRule <nom> =` dans `src/test/java` des cinq
modules porteurs de tests declares au pom agregateur (`domain`, `application`, `adapter-web`,
`adapter-content`, `bootstrap`), fixture de contre-exemple exclue (elle vit sous
`src/test/resources`, hors du perimetre de la methode). Resultat : 1 dans `adapter-web`
(`AssertionsWebTest`), 27 dans `bootstrap` (dix fichiers de `com.recreo.archi`) = **28**.

### Le piege de comptage du sprint, reconstitue sur le graphe des commits

Trois chiffres ont circule (390, 399, 403) parce que deux lots ont diverge du **meme point**,
`2d34a15` (390 tests), sans se voir l'un l'autre avant fusion :

- le lot de cloture de `marc-dev` (focus differe, test `recreo.js`, M21, M14, resserrement de
  marges), fusionne en premier (`03b7880`) ;
- le lot d'images de `theo-archi` (`ADR-0036`), qui ajoute exactement quatre tests (trois jeux
  fautifs de reference d'image plus une clause anti-vacuite), fusionne **apres** (`c2ac04a`).

Verifie sur le graphe : `git merge-base 2d34a15 03b7880` et `git merge-base 2d34a15 c2ac04a`
rendent tous deux `2d34a15`, et aucun des deux lots n'est ancetre de l'autre - ce sont des branches
soeurs. Quiconque mesurait sur le seul lot de `marc-dev` avant la fusion du lot d'images obtenait un
total qui ne pouvait pas compter ces quatre tests, **pas parce qu'ils manquaient, mais parce que
son arbre ne les contenait pas encore**. Le chiffre final, 403, n'est mesurable qu'apres que les
deux lots soeurs sont fusionnes sur la meme branche - ce qui est le cas de `round-two` a `643c6f4`,
et c'est sur cet arbre-la, en un seul `mvn -B clean verify`, que j'ai obtenu 403 moi-meme. La lecon
que je retiens et que je documenterai dans `lessons.md` de mon propre reflexe : un compte de tests
etabli sur une branche qui n'a pas encore vu toutes les branches soeurs qui la rejoindront n'est pas
un chiffre faux, c'est un chiffre pris trop tot.

## 2. Garde-fous casses puis remis en etat - la partie la plus utile de ce rapport

Chaque ligne : mutation appliquee sur le fichier reel de production (jamais une fixture), commande
executee, resultat constate, puis restauration verifiee par `git status` (arbre propre avant et
apres chaque mutation).

| Garde-fou | Mutation | Resultat | Restaure |
|---|---|---|---|
| `LeScriptDAgrementSExecuteApresLeCorpsTest` | retrait de `defer` sur la balise de `recreo.js` dans `accueil.html` - la faute historique exacte que ce test garde | **rouge**, 1 echec, message nommant exactement le motif ("sans defer, ... document.body.addEventListener leve une TypeError") | oui, `git status` propre |
| `RecreoJsDeplaceLeFocusTest` | selecteur `[data-focus-cible="true"]` change en `[data-focus-cible="false"]` dans `recreo.js` reel | **rouge**, `surUneMancheDesignantUnBoutonLeFocusVaSurCeBouton` en erreur (le faux DOM refuse le selecteur non modelise) - le test mord, meme si le motif rapporte est "selecteur non modelise" plutot qu'un echec de focus | oui |
| `LHabillageResteImmobileTest` (`ADR-0033`) | `transition: background-color 0.3s` ajoutee a `.cabinet` dans `recreo.css` reel | **rouge**, 2 echecs sur 5, message nommant `.cabinet` et citant C3 de `nadia-prof` | oui |
| `RessourcesTiercesTest` (`ADR-0036`/`ADR-0014`) | icone d'en-tete d'`accueil.html` pointee vers `https://cdn.example.org/icone.png` en attribut simple (jamais `th:src`), exactement la mutation documentee par `ADR-0036` | **rouge**, 1 echec sur 16, message nommant le fichier, la cible et "sur le reseau" | oui |

Apres chaque restauration : `mvn -B clean verify` complet rejoue en fin de sequence, **BUILD
SUCCESS, 403 tests**, `git status` propre. Aucune des quatre mutations ne subsiste dans ce
worktree.

## 3. Reponse aux cinq points de ma saisine

### 3.1 - La propriete commune aux quatre garde-fous verts sans rien examiner

Les quatre partagent la meme forme, et elle n'est pas "une regle trop faible" : **dans chacun des
quatre cas, le vert ne venait pas d'une absence de defaut, mais d'une absence de rencontre entre le
garde-fou et l'objet ou l'etat qu'il pretendait garder**, et personne ne l'avait verifie par
mutation du materiau reel avant que la mesure ne le revele.

- `ADR-0033` : aucune regle n'existait sur les transitions et pseudo-classes interactives - `ADR-0030`
  l'avait explicitement refusee comme "sans objet", en confondant la population d'une interdiction
  (les sujets qu'elle gouverne) avec l'ensemble de ses violations constatees.
- `ADR-0034` : le corpus d'ecrans ne jouait que des bonnes reponses ; la moitie du jeu ou l'enfant se
  trompe n'avait jamais ete rendue devant les clauses existantes.
- Le couple "reponse fausse en fin de partie" : jamais exerce, y compris par la clause elargie
  qu'`ADR-0034` venait d'ecrire le matin meme pour corriger le premier trou - la meme faute reproduite
  dans l'outil qui la corrige.
- `ADR-0036` : le selecteur jsoup `script[th\:src]` ne comprend pas l'echappement du deux-points dans
  la version de bibliotheque reellement resolue ; il selectionnait zero element depuis que toutes les
  references de production sont ecrites en Thymeleaf, soit depuis le sprint 6.

**Est-ce gardable ?** Partiellement, et le mecanisme existe deja en partie : `ADR-0013` porte une
regle meta (`LesGardeFousSontMisALEpreuveTest`) qui verifie que tout garde-fou classe "de balayage"
possede un jeu fautif sur lequel il mord - et cette regle a reellement attrape son propre auteur
pendant ce sprint (`ADR-0034`, section "le garde-fou qui m'a corrige"). Ce que cette regle meta ne
peut pas verifier, en revanche, c'est la **ressemblance entre un jeu fautif et la forme que la
production ecrit reellement** - c'est precisement ce qui a manque aux quatre cas ci-dessus, et
c'est un fait sur le monde (une version de bibliotheque, un corpus incomplet), pas un fait sur le
code du test. Cette ressemblance ne se prouve que par une mutation du fichier reel, jamais par la
lecture du test. Je recommande de faire de cette mutation-sur-materiau-reel une etape systematique
de mon propre rapport a chaque sprint (et non une decouverte occasionnelle) : je propose d'ajouter
a mon reflexe de cloture un tirage d'un ou deux garde-fous recemment ecrits ou modifies, mutes sur
le fichier de production reel plutot que sur leur fixture, avant chaque review.

### 3.2 - `nashorn-core`, dependance de test non arbitree

Le geste est bon : `nashorn-core` (scope `test`, jamais dans le fat jar - `spring-boot-maven-plugin`
n'embarque aucune dependance de portee `test`) ferme reellement la reserve de `nadia-prof` ("aucun
test n'execute `recreo.js`"), et c'est la premiere fois que ce fichier est reellement joue plutot
que lu par regex (`FrontieresDuJavaScriptTest`) ou par position de balise
(`LeScriptDAgrementSExecuteApresLeCorpsTest`).

**La doctrine d'`ADR-0014` ne couvre pas ce cas, et ce n'est pas la bonne doctrine a lui appliquer.**
`ADR-0014` gouverne les ressources **servies** au navigateur - ce qui entre dans le fat jar, ce qui
doit fonctionner hors ligne, ce qui doit etre trace par empreinte. `nashorn-core` n'est jamais servi,
jamais empaquete, jamais vu par un enfant : c'est un outil de test JVM, de la meme famille que
`junit`, `assertj`, `archunit-junit5` ou `jsoup`, deja presents sans qu'aucun ADR n'ait jamais eu a
les arbitrer.

**Mon avis de QA sur l'outillage de test, pas sur l'architecture** : le risque reel n'est pas que ce
geste soit interdit - il ne l'est par aucune regle ecrite - mais que **l'arbitrage n'existe nulle
part dans le depot au titre d'une decision tracable**. Il vit aujourd'hui dans un commentaire de
`pom.xml` ("Test seulement : moteur JavaScript pur JVM...") et dans un message de commit, tous deux
utiles mais ni l'un ni l'autre n'est un `ADR` ni une `DEC` que quelqu'un peut retrouver en cherchant
"pourquoi cette dependance". Par le principe de gouvernance du depot lui-meme ("une decision non
redigee en `ADR` ou `DEC` n'a pas ete prise"), la levee de la reserve de `nadia-prof` est un fait de
code mais pas encore une decision ecrite. Je recommande a `theo-archi` d'ecrire, au minimum, une
ligne de doctrine generale ("une dependance de test JVM qui n'entre jamais dans le fat jar et dont
la necessite est justifiee dans le commit n'a pas besoin d'un `ADR` dedie ; une dependance qui touche
`main` ou qui est servie en est une autre categorie, couverte par `ADR-0014`") - pas necessairement
un `ADR` complet pour ce cas precis, mais une regle qui dit ce qui a ete tranche silencieusement ce
sprint, pour que le prochain agent qui ajoute une dependance de test sache s'il doit demander ou
non.

### 3.3 - Ce que `RecreoJsDeplaceLeFocusTest` ne couvre pas, et le defaut qui a coute le second refus

Le fichier le dit lui-meme, dans sa propre Javadoc, ecrite par `marc-dev` : il ne couvre "ni le vrai
focus clavier d'un navigateur... ni le vrai evenement `htmx:afterSwap`... La clause 1 de M13 reste
verifiee au clavier, dans un vrai navigateur, hors de la CI." C'est exactement la ou `nadia-prof` a
trouve le defaut de son second verdict (branche non fusionnee `s10-nadia-verdict-2`) : sur l'ecran
de revelation, la partie est detruite si et seulement si la proposition revelee occupe la derniere
position de l'ordre des propositions - 10 fois sur 25 revelations mesurees.

**Ce que j'ai verifie moi-meme sur le gabarit reel**
(`adapters/adapter-web/src/main/resources/templates/fragments/manche.html`) : le bouton de sortie
("Les jeux", `data-role="sortie"`) est declare **avant** le formulaire des propositions dans le
document. La condition que `nadia-prof` a isolee - "la proposition revelee est la derniere de
l'ordre de tabulation" - est donc entierement derivable de la structure statique du document rendu :
c'est une question de position dans l'ordre DOM des elements focalisables (moins `tabindex="-1"`),
exactement le genre de propriete que ce depot verifie deja par assemblage de document plutot que par
navigateur (`ADR-0020`, `ADR-0027`, `ADR-0034`). Une clause structurelle - "le bouton de sortie
n'est jamais le prochain arret de tabulation apres le bouton porteur de la reponse revelee, dans le
document assemble" - me parait donc **gardable a un cout raisonnable**, sans navigateur ni moteur
JavaScript, sur le meme modele que les tests de document deja ecrits.

**Ce que je ne peux pas affirmer, faute de navigateur reel sur ce poste pour le verifier** : que le
comportement observe par `nadia-prof` (la premiere tabulation depuis le dernier element focalisable
du document atteint le bouton declare en premier) se reproduit a l'identique dans un navigateur de
bureau ordinaire avec sa propre interface (barre d'adresse, etc.) et pas seulement dans l'outillage
pilote qu'elle utilise pour mesurer. C'est une question d'environnement d'execution, pas de document
statique, et je ne la tranche pas sans l'avoir observee moi-meme sur les deux. Ce point-la - la
fidelite de l'instrument de mesure au comportement d'un vrai navigateur - rejoint la famille de ce
qui appartient a `nadia-prof`, au meme titre que l'ecoute au lecteur d'ecran (point 5 ci-dessous).

### 3.4 - Le chiffre faux de `DEC-0025`, et ce qui l'a laisse entrer

**Confirme par moi, sur le materiau reel, independamment du second verdict de `nadia-prof`.**
`DEC-0025` (commit `42b379b`, ecrit **2026-08-13 20:09:43**) affirme que le resserrement de marges
"ferme deux jeux sur sept ('Calcul CP', 'Le bon son')". Le commit qui **implemente** ce resserrement
(`3a2f9ce`, ecrit **20:43:54**, soit 34 minutes plus tard) dit tout autre chose dans son propre
message : il "rend 'Petits problemes' et 'Le bon verbe' mesures par `ines-design`" - deux jeux
differents des deux nommes par `DEC-0025`.

Le second verdict de `nadia-prof` (branche non fusionnee `s10-nadia-verdict-2`, que je cite comme
source mais dont je ne fais pas mon propre chiffre technique) mesure apres coup, sur le rendu reel :
le resserrement ferme "Comparer les nombres", "Les complements a 10" et "Soustractions CP" - **trois**
jeux, aucun des deux nommes par `DEC-0025`. "Calcul CP" tenait deja avant tout resserrement (18,81 px
de marge des le matin) ; "Le bon son" ne tient toujours pas, son ecran de revelation debordant de
23,19 px.

**Ce qui, dans le process, laisse un chiffre non mesure entrer dans un `DEC`** : `DEC-0025` a ete
redigee **avant** que le commit qu'elle decrit n'existe dans l'arbre. Le chiffre qu'elle porte ne
peut donc venir que d'une mesure anterieure et distincte - la maquette d'`ines-design`
(`mesure-ines-budget-vertical-m4.md`), batie sur un meuble candidat different de celui livre, et non
rejouee sur le rendu reel avant que la decision ne soit close. Aucune etape du process ne demande de
revérifier le chiffre d'un `DEC` contre le commit qui le realise une fois que ce commit existe, y
compris quand les deux sont ecrits a trente-quatre minutes d'intervalle dans le meme lot. C'est la
meme famille de defaut que le point 1 : un chiffre pris pour vrai sans avoir jamais ete confronte au
materiau reel. Je recommande a `sofia-sm` d'ajouter une etape de cloture de lot : tout chiffre porte
par une decision produit doit citer le commit qui le realise, et ce commit doit exister au moment ou
la decision est ecrite - pas l'inverse.

### 3.5 - Cinq incidents en deux jours, et l'absence d'outil de cycle de vie du serveur de mesure

Les cinq incidents partagent une seule cause : **ce depot n'a aucun outil qui lance, verifie et
arrete un serveur de mesure**, et chaque agent reconstruit ce protocole a la main, a chaque mesure -
j'ai retrouve la trace de deux d'entre eux moi-meme dans les fiches de ce sprint : un jar perime
mesure par `marc-dev` (cite par `nadia-prof` elle-meme, "j'ai donc reconstruit... plutot que de me
fier a un binaire deja present"), et une instance de navigateur laissee derriere par une erreur qui
gardait le port de debogage, la suivante s'y rattachant et mesurant la session HTTP de la precedente
- `nadia-prof` a failli ecrire deux defauts de M11 qui n'existent pas avant de s'en apercevoir.

**Ma reponse, selon la doctrine d'`ADR-0009`** : oui, cela merite un outil, et le meme decoupage que
cet `ADR` impose deja a `verifier-jdk.sh` et `mesurer-rendu.sh` s'applique directement - une fonction
pure testee (verification de fraicheur, allocation de port, ecriture d'un fichier de verrou) et une
enveloppe qui touche reellement le poste (lancer/arreter un processus, se lier a un port), prouvee
une fois a la main et documentee comme degradee. Ce que cet outil devrait garantir, un par un contre
les cinq incidents :

1. **Fraicheur** : ne jamais servir un jar plus vieux que l'arbre courant - comparer une empreinte ou
   un horodatage du jar a l'etat du worktree, refuser de mesurer sinon plutot que de laisser un agent
   s'y fier sur la foi d'un nom de fichier.
2. **Isolement** : un port et un profil de navigateur neufs a chaque mesure, jamais reutilises -
   c'est deja une discipline manuelle chez `ines-design` et `nadia-prof` ("un port et un profil
   neufs par mesure"), qui devrait etre le comportement par defaut de l'outil et non un reflexe a se
   rappeler.
3. **Exclusivite** : refuser de demarrer si un port ou un verrou precedent est deja tenu, plutot que
   de laisser une seconde instance s'attacher silencieusement a la premiere.
4. **Arret garanti** : une enveloppe qui arrete le serveur qu'elle a lance meme quand la tache qui
   l'utilisait echoue - pas seulement sur le chemin de succes - pour qu'un serveur ne survive jamais
   a l'echec de sa propre tache et ne garde pas un port.
5. **Traçabilite** : le port et le PID utilises sont ecrits quelque part de lisible (pas seulement
   dans la sortie console d'une session qui peut se fermer), pour qu'un agent puisse nettoyer ce
   qu'une session precedente a laisse, y compris la sienne propre.

Ce n'est pas un outil que je prescris dans le detail - `theo-archi` choisit sa forme et son
emplacement (probablement `outils/`, sur le patron de `verifier-jdk.sh` et `mesurer-rendu.sh`) - mais
je le nomme comme obstacle de qualite reel, pas comme une malchance repetee cinq fois par hasard.

## 4. Ce dont `nadia-prof` reste la seule garde, et ce qui s'en approche

`nadia-prof` ecrit, pour la deuxieme fois du sprint, qu'elle n'a pas ecoute cette application avec un
lecteur d'ecran, et que sa condition F11 reste bloquee pour cette raison. **Je ne remplace pas cette
ecoute et je ne pretends pas l'approcher.** Ce que ce depot peut automatiser aujourd'hui - `role`,
`aria-hidden`, la lecture d'un arbre d'accessibilite statique - est deja fait par `nadia-prof`
elle-meme dans sa reponse sur F11 ("le bloc de message porte `role="status"`, calcule `live:
polite`"), et elle le dit elle-meme : "je n'ai pas fait passer une lecture d'arbre d'accessibilite
pour une ecoute". Un test automatise pourrait au mieux verifier la presence statique de ces
attributs (et rien de ce depot ne le fait aujourd'hui, a ma connaissance, pour la region live du
bloc de message) ; il ne peut rien dire sur l'annonce reelle d'une region detruite-et-recreee a
chaque echange htmx, qui est precisement la question ouverte qu'elle nomme. Je ne promets donc rien
au-dela d'un garde-fou statique possible et limite, et je ne le fais pas passer pour une reponse a sa
question.

## 5. Ce que ce rapport ne couvre pas

- Aucun Done pedagogique : il n'est prononce que par `nadia-prof`, et son verdict `REFUSE` du
  2026-08-13 (fusionne, `f440d7a`) reste entier. Le second `REFUSE` (branche non fusionnee) n'est pas
  compte comme verdict officiel tant qu'il n'est pas sur `round-two`.
- Aucune tracabilite critere par critere des stories de ce sprint : ma saisine porte sur le rapport
  qualite de cloture, pas sur une revue de story par story ; les fiches de conformite et `DEC-0022` a
  `DEC-0025` portent deja cette tracabilite du cote produit.
- Aucun jugement sur la forme du meuble, l'ampoule ou le contenu : `ines-design` et `nadia-prof`.

## Conclusion

**Vert, constate par moi-meme, sur `round-two` a `643c6f4`** : `mvn -B clean verify`, BUILD SUCCESS,
403 tests, 0 echec, 28 regles ArchUnit. Quatre garde-fous casses sur du materiau reel puis remis en
etat, arbre propre verifie a chaque etape. Un chiffre faux trouve et sa cause nommee (`DEC-0025`,
point 4). Une dependance non arbitree signalee sans blocage (point 2). Un outil de cycle de vie de
serveur de mesure recommande (point 5). Rien de tout cela ne change le Done pedagogique, qui reste
`REFUSE` et qui n'appartient qu'a `nadia-prof`.
