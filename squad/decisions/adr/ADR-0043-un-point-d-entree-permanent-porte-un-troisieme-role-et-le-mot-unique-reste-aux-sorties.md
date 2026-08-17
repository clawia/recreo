# ADR-0043 - Ouvrir un troisieme role de bouton pour un point d'entree permanent, l'admettre du cote domine de la saillance, et laisser le mot unique aux seules sorties

- **Statut** : Accepte
- **Date** : 2026-08-17
- **Auteur** : theo-archi
- **Sprint** : s14 (avis de faisabilite S14-1, question routee par `ines-design`, section 8 de
  `proposition-ines-barre-de-niveaux.md`)
- **Amende** : `ADR-0017` (le vocabulaire de `data-role` et le cote domine de l'appariement)
- **Complete** : `ADR-0022` (la clause de libelle unique, dont le perimetre est confirme et non
  elargi)
- **Regle executable** :
  `adapters/adapter-web/src/test/java/com/recreo/adapter/web/LActionNeDeclarePasMoinsQueLaSortieTest.java`
  - ecrite dans le commit de cet ADR, avec ses deux jeux fautifs.

## Contexte

`ines-design` route explicitement une question plutot que de la trancher depuis un gabarit : un
bouton de niveau permanent dans l'en-tete est-il une `action`, une `sortie`, ou une troisieme
valeur ? Elle propose `sortie`, parce que la consequence structurelle est identique a celle de
"Les jeux" - passer par l'ecran de confirmation, interrompre l'activite - et elle ajoute qu'une
troisieme valeur exigerait d'etendre le garde-fou dans le meme commit.

`ADR-0017` point 1 ne connait que deux valeurs et refuse toute autre : un role inconnu est un
**echec**, jamais un membre ignore. Le choix n'est donc pas cosmetique - il decide quelles
comparaisons la CI fera, et lesquelles elle ne fera plus.

**Les deux valeurs existantes ont ete essayees avant d'en ouvrir une troisieme, sur le depot reel et
non en raisonnement.** Methode, identique pour les deux mesures : les deux puces de niveau posees
dans `<header>` de `accueil.html` avec la boite exacte que `ines-design` a chiffree
(`font-size: 1.25rem`, `min-height: 2.75rem`, `padding: 0.5rem 0.9rem`, ecrite en regle maison
`.chip-niveau` dans `recreo.css`), libelles "CP" et "CM1" ; `bash outils/mvn-jdk21.sh -B -o -pl
adapters/adapter-web -am test`, JDK 21.0.10 designe par la toolchain ; arbre restaure apres chaque
mutation et verifie propre par `git status --porcelain`.

| Valeur essayee | Mesure |
|---|---|
| `data-role="sortie"` | **1 echec**, `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.leLienDeMenuEtLaSortieDeLaZoneDeContenuPortentLeMemeMot` : la clause releve le libelle de **tous** les `[data-role=sortie]` du depot et exige `containsOnly("Les jeux")`. `LActionNeDeclarePasMoinsQueLaSortieTest` reste vert, 11 tests sur 11 |
| `data-role="action"` | **2 echecs sur 11** dans `LActionNeDeclarePasMoinsQueLaSortieTest` : `aucuneSortieNeDeclarePlusQuUneActionDuMemeEcran` avec **16 ecarts** nommes - quatre ecrans (`accueil.html`, `fragments/confirmation.html`, `fragments/manche.html`, `fragments/retour.html`) x deux axes de rembourrage x deux puces - et `surLEcranDeRetourAucuneActionNEstPlusConfronteeALaSortieDepuisLaDisparitionDEncore`, parce qu'un couple reapparait sur l'ecran de fin de partie ou l'epinglage exige qu'il n'y en ait plus |
| `data-role="entree"` (cette decision) | `LActionNeDeclarePasMoinsQueLaSortieTest` **11 sur 11** et `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest` **4 sur 4**, avec l'appariement etendu de cet ADR |

**Les deux echecs disent deux choses vraies, et c'est ce qui interdit de les contourner.**

- **`sortie` est faux sur le texte.** La clause de libelle n'est pas un detail de style : c'est le
  geste precis par lequel `nadia-prof` a leve sa reserve 3 le 2026-08-07. "Un seul mot de sortie
  circule sur toute l'application" cesse d'etre vrai le jour ou une sortie porte "CP". Rendre la
  clause verte en elargissant son perimetre reviendrait a defaire l'arbitrage en silence, ce qui est
  exactement ce que cette clause existe pour empecher.
- **`action` est faux sur la saillance, et il est faux dans le bon sens.** Une puce de niveau
  volontairement plus petite que les boutons de sortie de l'application fait, si on la declare
  `action`, de **chaque sortie de chaque ecran** un element qui domine une action. Les 16 ecarts ne
  sont pas un artefact du garde-fou : ils disent que ce qui appelle le plus la main sur ces ecrans
  serait ce qui en sort. La bonne conception est bien la petite puce ; c'est la declaration qui
  mentait sur ce qu'elle est.

Autrement dit : la puce n'est ni ce qui fait avancer le jeu affiche, ni la sortie de ce jeu. Elle
mene ailleurs dans l'application depuis un habillage qui ne disparait pas. **Le vocabulaire a deux
valeurs n'a pas de place pour cet objet, et les deux places qu'il offre sont chacune fausse d'une
facon mesuree.**

## Decision

**1. Un troisieme role, `entree`.** Un bouton d'un habillage permanent qui ne fait pas avancer
l'ecran affiche et qui n'est pas la sortie de cet ecran declare `data-role="entree"`. Les trois
valeurs restent une liste fermee : un role inconnu demeure un echec.

**2. Le cote domine de l'appariement de saillance n'est plus le seul `sortie`.** La propriete
d'`ADR-0017` est reecrite dans les termes qui etaient deja les siens et qui n'avaient jamais eu
besoin du mot "sortie" :

> Sur un meme ecran, aucun bouton qui ne fait pas avancer le jeu ne declare de composante de boite
> superieure a celle d'un bouton d'action du meme ecran.

`sortie` et `entree` sont donc tous deux du cote domine. Ce n'est pas une generosite envers le
troisieme role : c'est ce qui l'empeche d'etre une porte de sortie d'`ADR-0017`. Sans cette clause,
la facon la moins chere de faire taire les 16 ecarts mesures ci-dessus serait de rebaptiser
n'importe quel bouton en `entree` - le desamorcage a un attribut pres qu'`ADR-0038` a deja nomme
comme le moyen le moins cher.

**3. La clause de libelle unique garde exactement son perimetre : les `sortie`.** Elle n'est ni
elargie, ni affaiblie, ni deplacee. C'est elle qui interdit desormais de declarer une puce de niveau
`sortie`, et c'est le point 2 qui interdit de la declarer `action` sans en payer la saillance. **Les
deux clauses tirent en sens contraire, et c'est ce qui rend le troisieme role obligatoire plutot que
commode.**

**4. Tout role domine reellement porte par un bouton du depot est confronte au moins une fois.** Un
role qu'aucun bouton ne porte n'est pas juge - il n'y a rien a juger. Un role porte et confronte a
rien est un bouton sorti d'`ADR-0017` par un attribut, et c'est un echec. La population est derivee
du balisage : un quatrieme role ajoute demain au cote domine entre dans cette clause sans que
personne y pense.

**5. Ce que cet ADR ne decide pas.** Que "CP" et "CM1" soient les bons mots, qu'un enfant de CP
distingue une puce de niveau d'une sortie, et laquelle des deux formes chiffrees par `ines-design`
retenir : `nadia-prof`. Le nom de la valeur - `entree` - est un identifiant de code ASCII, il n'est
jamais lu par un enfant, et il ne prejuge d'aucun libelle.

## Regle executable

### Pourquoi ce n'est pas une regle ArchUnit

Meme motif qu'`ADR-0017`, `ADR-0020` et `ADR-0022`, inchange : ArchUnit lit du bytecode, des
dependances et des annotations. Ce qui est juge ici est un attribut de gabarit et quatre
declarations d'une feuille de style, dont ni l'un ni l'autre n'existent dans le bytecode. **Le
compte des regles ArchUnit ne bouge pas de cet ADR-ci** ; `ADR-0044`, ecrit dans le meme lot, en
ajoute une. Le mecanisme automatique est un test du module `adapter-web`, execute par `mvn verify`
comme tout le reste.

### Ce qui change dans le garde-fou

Fichier : `adapters/adapter-web/src/test/java/com/recreo/adapter/web/LActionNeDeclarePasMoinsQueLaSortieTest.java`

- `ROLES` passe a trois valeurs, `ROLES_DOMINES` en nomme deux, et les deux messages d'echec
  enumerent desormais les valeurs lues depuis la constante plutot qu'en clair - une valeur de plus
  ne peut plus laisser un message perime derriere elle ;
- `appariements` confronte une `action` a tout role domine du meme ecran, plus au seul `sortie` ;
- le message d'un ecart nomme le role en cause ("la sortie" / "le point d'entree"), pour que le
  defaut se lise sans ouvrir le gabarit ;
- `rolesDominesJamaisConfrontes` porte le point 4.

**Ce qui ne change pas, et qui compte autant** : le perimetre reste derive du repertoire de gabarits
et du balisage ; les quatre composantes comparees sont les memes ; l'appariement continue de ne
confronter que des etats capables de s'afficher ensemble, masquages servis compris (`ADR-0022`
point 5). Aucune assertion n'a ete affaiblie pour faire entrer le troisieme role.

### La preuve que ces clauses mordent

Deux jeux fautifs neufs, permanents dans la suite, sous
`adapters/adapter-web/src/test/resources/contreexemple/LActionNeDeclarePasMoinsQueLaSortieTest/` :

- **`entree-permanente-plus-grosse`** - la boite que `ines-design` a chiffree, a son rembourrage
  pres, sur une puce `entree` d'un habillage permanent ; la **sortie du meme habillage y reste plus
  petite que l'action**, donc seul le point d'entree est en cause. C'est ce qui prouve que le point 2
  est encode et pas seulement ecrit : un lot qui aurait etendu le seul vocabulaire, sans etendre
  l'appariement, resterait vert sur ce jeu. Rouge mesure, message :
  *"le point d'entree .puce-niveau \"CP\" declare rembourrage haut et bas a 1,00rem quand l'action
  .continuer \"Continuer\" se contente de 0,75rem"* ;
- **`entree-jamais-confrontee`** - le point 4. La puce existe, la feuille la masque sur le seul ecran
  qui porte une action, et l'ecran ou elle reste visible n'en porte aucune. Le couple action/sortie
  de l'ecran insere, lui, existe et passe : **la clause generale "aucun couple confronte" reste donc
  verte**, et c'est ce qui rend ce jeu fautif utile plutot que redondant. Rouge mesure, message :
  *"le role \"entree\" est porte par un bouton du depot mais n'est confronte a aucune action"*.

Le materiau de la boite n'est pas une fixture de ma main : c'est le balisage et les valeurs que
`ines-design` a ecrits et mesures, repris tels quels - le precedent d'`ADR-0035`, et la lecon de
s05 appliquee dans le seul sens ou elle protege.

**Etat de la classe apres ce lot** : 11 tests, 0 echec (9 avant), `bash outils/mvn-jdk21.sh -B -o
-pl adapters/adapter-web -am test -Dtest=LActionNeDeclarePasMoinsQueLaSortieTest`.

## Consequences

- **Un vocabulaire de trois valeurs au lieu de deux.** C'est une piece mobile de plus, et je ne
  l'habille pas : il faudra desormais choisir entre trois valeurs a chaque bouton neuf, et le choix
  n'est pas toujours evident. Ce qui rend le cout acceptable : les deux autres choix sont
  **mesurement faux** sur l'objet de s14, et la liste reste fermee - un quatrieme role ne peut pas
  entrer en silence.
- **La production ne porte aucun `entree` au jour de cet ADR.** Le point 4 est donc vert parce que sa
  population est vide, et c'est dit plutot que caché : ce n'est pas un vert a vide au sens
  d'`ADR-0013`, puisque les deux jeux fautifs exercent les deux clauses sur du materiau qui n'est pas
  la production. Le premier lot qui pose une puce fait entrer la population reelle.
- **Un lot qui poserait la barre avec `data-role="entree"` sans rien d'autre n'est pas vert pour
  autant.** Mesure du 2026-08-17 sur `adapter-web` (233 tests) avec les deux puces posees, la boite
  d'`ines-design` en regle maison et aucun controleur derriere : **3 echecs**, et aucun n'est celui
  d'`ADR-0017`. Ils sont enumeres dans l'avis de faisabilite de s14, section sur les criteres
  d'acceptation ; le plus important est
  `TexteAccentueDesGabaritsTest.aucunGabaritDeProductionNEcritUnTexteHorsDeLInventaire`, qui refuse
  "CP" tant que l'inventaire ne cite pas l'arbitrage de `nadia-prof`. **La porte pedagogique est donc
  deja executable sur ce point**, et ce n'est pas moi qui l'ai mise la.
- **`ADR-0017` cesse de parler de "sortie" dans sa propriete generale**, tout en gardant son titre et
  son fichier. Le nom du garde-fou (`LActionNeDeclarePasMoinsQueLaSortie...`) devient legerement
  etroit par rapport a ce qu'il verifie. Je ne le renomme pas : trois `ADR` et une dizaine de
  documents le citent par ce nom, et un renommage couterait plus que l'inexactitude qu'il corrige.
  C'est ecrit ici pour que personne ne le decouvre en lisant le nom.

## Alternatives considerees

1. **`data-role="sortie"`, la proposition d'`ines-design`.** Ecartee sur mesure, pas sur principe :
   1 echec, et l'echec porte sur le geste exact par lequel `nadia-prof` a leve une reserve. Elle
   avait raison sur la consequence structurelle - la puce passe bien par la confirmation - et c'est
   precisement ce qu'`ADR-0038` a deja etabli : `data-role` dit une **destination du point de vue de
   l'enfant**, pas l'effet d'une requete. "CP" et "Les jeux" ne sont pas la meme destination.
2. **`data-role="action"`.** Ecartee sur mesure : 2 echecs, 16 ecarts, quatre ecrans. Et elle serait
   fausse meme si le CSS la rendait verte, parce qu'une puce de niveau ne fait pas avancer le jeu
   affiche.
3. **Elargir la clause de libelle unique aux seules sorties "de la zone echangee".** Ecartee : elle
   enumererait une situation par sa position dans le document au lieu de nommer une propriete, et
   `ADR-0038` a paye ce genre d'enonce. Surtout, elle laisserait un enfant lire deux mots de sortie
   differents, ce que l'arbitrage refuse.
4. **Grossir la puce jusqu'a ce que `action` passe.** Ecartee, et c'est la pire : elle rendrait le
   garde-fou vert en donnant a un point d'entree la saillance d'un bouton de reponse, sur tous les
   ecrans de jeu a la fois. Un garde-fou qu'on satisfait en aggravant la propriete qu'il garde est un
   garde-fou qu'on a retourne.
5. **Ne rien decider et laisser le lot choisir.** Ecartee : `ines-design` a route la question
   nommement, les deux valeurs disponibles sont rouges, et le lot decouvrirait en revue que la CI
   refuse son gabarit. C'est exactement ce que l'etape 1 du planning existe pour eviter.
6. **Ecrire la regle plus tard, avec le lot.** Ecartee, et pour un motif que ce sprint a sous les
   yeux : `ADR-0042` a ete accepte en s13 avec sa regle "due par le lot de S13-6", le lot a ete
   annule par `DEC-0032`, et la regle n'existe toujours pas. Une regle dont l'ecriture depend d'un lot
   futur est une regle qui peut ne jamais exister.

## Ce qui reste non gardable, et le dit

- **Qu'un enfant de CP distingue une puce de niveau d'une sortie de jeu.** Aucun automate de ce depot
  ne mesure la comprehension d'un enfant. C'est l'arbitrage prealable que `DEC-0032` demande a
  `nadia-prof`, et rien de ce qui est vert ici ne l'anticipe.
- **Que la puce soit reellement moins saillante au rendu.** Les composantes comparees sont
  **declarees**, jamais mesurees sur des pixels : la largeur rendue depend du libelle et de la police
  du poste, et `ADR-0017` le dit de lui-meme depuis s05.
- **Que le troisieme role soit le bon decoupage du monde.** Trois valeurs decrivent ce que ce depot
  contient aujourd'hui. Ce qui me ferait changer d'avis : un quatrieme objet qui n'entre dans aucune
  des trois. Il faudrait alors se demander si `data-role` ne melange pas deux questions - ou va le
  bouton, et qui doit appeler la main - et les separer en deux attributs plutot que d'allonger une
  liste.
