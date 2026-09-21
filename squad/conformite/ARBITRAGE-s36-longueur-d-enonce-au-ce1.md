# Arbitrage du 2026-09-21 - la borne de longueur d'un enonce au CE1

- **Auteur** : `nadia-prof`. **Date** : 2026-09-21. **Sprint** : s36, hors lot de contenu.
- **Ce qui m'est pose** : `F3` du planning de s36 -
  `squad/sprints/s36-ouverture-ce1-mathematiques-seule-cm2-differe/planning.md`, section 3 (avis de
  faisabilite de `theo-archi`, integral) et section 4 (affectation nominative). Enonce du constat :
  *"aucune borne de longueur d'enonce ne couvre CE1 : `ADR-0089` (au CP un enonce compte au plus
  quinze mots) est explicitement borne au CP par son propre titre"*.
- **Ce document ne prononce aucun Done**, n'ecrit ni backlog ni garde-fou, et ne cree aucune ligne
  de matrice.
- **Je n'ai rien joue, et ce n'est pas un audit degrade : il n'y a rien a jouer.** Aucun paquet de
  CE1 n'existe (`DEC-0089`, section 1). Ce document est une **specification prealable**, du meme
  genre que celles que j'ecris avant un lot de contenu, et il sera mis a l'epreuve par le premier
  paquet de CE1 comme n'importe quelle autre.

---

## 1. Le constat de `theo-archi` est exact, et je l'ai verifie autrement que par le titre

Sa formulation s'appuie sur le titre d'`ADR-0089`. Le titre ne suffirait pas : un titre peut mentir
sur ce que son garde-fou fait. **Methode de ma verification**, dans le meme paragraphe que
l'affirmation : j'ai lu la clause D d'`ADR-0089` (*"la barre ne vaut ni pour le CM1, ni pour les
propositions, ni pour l'indice, ni pour la consigne"*) puis la source du garde-fou qui l'execute,
`bootstrap/src/test/java/com/recreo/archi/AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.java`.
Elle porte trois constantes qui decident tout : `NIVEAU_BORNE = "CP"`, `MOTS_MAXIMUM = 15`,
`PHRASES_MAXIMUM = 2`, et sa population est **les enonces des paquets servis dont le descripteur
porte le niveau borne**. Un paquet dont le descripteur porte `CE1` n'entre donc dans aucune de ses
assertions - il n'est pas exempte, il n'est pas vu.

C'est la bonne conception, et je ne la reproche a personne : cette barre a ete posee par moi sur une
population que j'avais comptee, et un garde-fou qui aurait deborde sur un niveau non compte aurait
pose un poteau que je n'avais pas pose (`ADR-0089`, alternatives 3 et 4).

## 2. Ma decision : oui, une borne s'applique au CE1, et elle n'est pas celle du CP

> **`LON-CE1-1`** - au CE1, un enonce compte **au plus 20 mots**.
>
> **`LON-CE1-2`** - au CE1, un enonce compte **au plus trois phrases**, et **la derniere est la
> question**.

**La convention de comptage est celle d'`ADR-0089`, reprise sans un mot de changement**, et je ne
la reecris pas ici : un second exemplaire d'une convention rancit (`ADR-0061`). Elle vit en deux
endroits qui font foi - la section 16.1 de
`squad/conformite/ARBITRAGE-PREALABLE-s32-trois-instances-ma-borne-a-douze-le-terrain-sans-image-et-le-prix-d-une-ligne-marginale.md`,
et les clauses A a C d'`ADR-0089` pour sa forme operatoire. Trois points de cette convention
comptent assez au CE1 pour que je les nomme plutot que de les laisser decouvrir :

1. **Une forme elidee compte pour un mot** (`qu'il`, `l'ami`, `d'entre`). Je m'etais trompee
   dessus a s32 en comptant pour deux, je l'avais corrige, et la correction tient ici.
2. **Une fin de phrase est un `.`, un `?` ou un `!` qui termine le texte ou qu'une majuscule
   suit** - la derivation de la clause C, jamais le critere litteral. **Ce point est plus important
   au CE1 qu'au CP** : la numeration va jusqu'a 1000 et la multiplication entre, donc les enonces
   de la forme `12 + ? = 20` ou `4 x 5 = ?` seront plus nombreux, pas moins. Sous le critere
   litteral, chacun de ces enonces compterait deux phrases dont la seconde n'interroge pas, et
   `LON-CE1-2` serait rouge sur du contenu parfaitement lisible - exactement les 22 faux rouges
   mesures au CP.
3. **Un symbole seul n'est pas un mot** : il ne contient ni lettre ni chiffre. Dans `3 €`, le `3`
   compte et le `€` ne compte pas. Je le dis parce qu'un enonce de monnaie (`MAT-CE1-13`) en porte
   plusieurs, et que quelqu'un qui compte a la main les comptera.

## 3. Pourquoi 20 et pas 15 - et pourquoi trois phrases et pas deux

**Le CE1 n'est pas un CP qui lirait mieux, c'est un niveau ou la lecture cesse d'etre le travail
principal.** Au CP, chaque mot de l'enonce est encore un dechiffrage, et 15 mots etait deja un
plafond genereux : la mediane mesuree sur 506 enonces de CP etait de **6 mots**, le 95e centile de
15 (`ADR-0089`, section 3, mesure du 2026-09-08). Au CE1, l'enfant lit une phrase courte sans la
dechiffrer mot a mot, mais il la relit encore, et **ce qu'il relit est du temps qu'il ne passe pas
sur les mathematiques**.

Les deux clauses que je pose ne sont pas un reglage arbitraire : elles sont la taille exacte de ce
que les lignes du referentiel demandent. Trois enonces, comptes sous la convention :

| Enonce | Mots | Phrases | Ligne servie |
|---|---|---|---|
| **Léa a 24 billes. Elle en donne 7 à Tom. Combien lui en reste-t-il ?** | 14 | 3 | `MAT-CE1-08` |
| **Tu achètes un cahier à 3 € et une gomme à 2 €. Tu donnes 10 €. Combien te rend-on ?** | 17 | 3 | `MAT-CE1-13` |
| **Range ces nombres du plus petit au plus grand : 308, 83, 380, 38.** | 12 | 1 | `MAT-CE1-02` |

**Les deux premiers sont la forme normale d'un probleme a une ou deux etapes, et aucun des deux ne
passerait la barre du CP** - non pas sur ses mots (14 et 17, donc le second seul depasse 15), mais
sur ses **phrases** : trois, la ou `LON-2` en autorise deux. Le CP evite ce probleme en n'en posant
pas ; le CE1 ne le peut pas, `MAT-CE1-08` **est** la competence de tenir une situation en deux
temps avant de repondre. Une barre a deux phrases au CE1 forcerait a comprimer la situation en une
seule phrase a subordonnees - **plus courte a compter, plus dure a lire**, ce qui est exactement
l'effet que la barre existe pour empecher.

**Et ce que 20 refuse, parce qu'une borne qui ne refuse rien n'est pas une borne.** Voici un
enonce que je refuserai, et je l'ecris pour qu'on ne l'ecrive pas :

> *Dans la classe de Madame Roux, il y a 24 élèves. Douze d'entre eux partent à la piscine avec
> leur maître-nageur. Combien d'élèves restent en classe ?*

**26 mots, 3 phrases.** Il tient `LON-CE1-2` et il echoue sur `LON-CE1-1`. Le decor - le nom de la
maitresse, le maitre-nageur - ne sert pas le calcul et coute sept mots. C'est la forme d'enonce que
le CM1 accueille (165 enonces de CM1 au-dela de 15 mots, maximum 36, mesure d'`ADR-0089`) et que le
CE1 ne doit pas accueillir par glissement.

**20 est un plafond, pas une cible.** Les trois exemples du tableau tiennent a 12, 14 et 17 : la
marge n'est pas un budget a depenser.

## 4. La population que cette borne gouverne, et je dis qu'elle est vide

**Population** : le champ `enonce` de tout paquet servi dont le descripteur porte `CE1`, dans le
contenu servi de chaque module declare au pom agregateur - meme derivation que celle d'`ADR-0089`,
jamais une liste de noms de paquets.

**Cette population est vide aujourd'hui**, et je le declare plutot que de laisser croire que j'ai
mesure quelque chose. `squad/README.md`, section 4, m'impose de mesurer une condition qui filtre du
contenu **contre la population entiere qu'elle gouverne au moment ou je l'ecris** - c'est la regle
que ma clause `SEG-1` a payee. Cette obligation est explicitement **bornee aux populations
enumerables et deja existantes**, et son remplacement pour le cas inverse est que la condition soit
ecrite comme une **propriete decidable item par item**, avec sa population nommee et sa
non-enumerabilite dite. C'est ce que fait la section 2 : compter les mots d'un enonce et compter
ses phrases se decide sur l'item seul, sans connaitre les autres, sans exemple et sans liste
fermee.

**Ce que je n'ai donc pas fait, et qu'il ne faut pas me crediter** : je n'ai verifie sur aucun
corpus de CE1 reel que 20 mots est le bon chiffre. Il n'y a pas de corpus. Le chiffre est un
jugement de niveau, appuye sur la distribution mesuree du CP et sur la forme des lignes du
referentiel, pas sur une mesure du CE1.

**Consequence que j'accepte d'avance, et qui est le prix de ce jugement.** Quand le premier paquet
de CE1 existera, je mesurerai sa distribution comme j'ai mesure celle du CP. Si elle me montre que
20 etait trop haut, **j'ouvrirai une reserve neuve et datee, je ne durcirai pas `LON-CE1-1`
retroactivement** : une barre qu'on releve apres qu'une equipe l'a atteinte fait de ma porte un
caprice. `LON-CE1-1` et `LON-CE1-2` sont les poteaux, et ils restent ou ils sont.

## 5. La dette est nulle, et c'est la raison de trancher maintenant plutot que plus tard

Au CP, la barre est arrivee **apres** 528 enonces servis : elle porte une dette de **5 enonces sur
3 paquets** (`DETTE_DE_LONGUEUR` d'`ADR-0089`), qui vit encore, et la promesse *"au CP aucun enonce
ne depasse 15 mots"* y est fausse de cinq enonces - `theo-archi` l'ecrit lui-meme dans ses
consequences. Elle porte aussi une clause de date : *"elle ne vaut que pour les enonces ecrits ou
reecrits a partir du 2026-09-08"*.

**Au CE1, aucune des deux n'est necessaire.** La borne entre avant le premier item :

- **dette exacte zero**, des le premier paquet, sans clause de date et sans paquet nomme ;
- **aucune reecriture a financer** : personne n'a encore ecrit un enonce de CE1.

C'est la seule fenetre ou cette borne est gratuite. Posee dans six mois, elle coutera ce que la
barre du CP a coute.

## 6. Ce que cette borne ne couvre pas - ma frontiere, ecrite comme au CP

- **Elle ne vaut que pour le champ `enonce`.** Ni les propositions, ni l'indice, ni la consigne du
  jeu. Motif identique a celui du CP : je n'ai pas compte ces populations, et au CE1 elles
  n'existent pas encore. Une borne gratuite aujourd'hui parce que sa population est vide serait une
  regle que personne n'a decidee (`ADR-0089`, alternative 3, *"le cas dangereux des trois"*).
- **Elle ne vaut pas pour le CP.** `LON-1` et `LON-2` restent a 15 mots et deux phrases, inchangees.
  Ce document ne les touche pas, ne les amende pas et n'en deplace pas le plafond de dette.
- **Elle ne vaut pas pour le CM1, et elle ne repond pas a la question du CM1.** `ADR-0089` me route
  explicitement *"une barre de longueur pour le CM1, et le sort de `HG-R4`"* dans son chapitre *Ce
  que je route*. **Cette question reste ouverte et elle reste a moi** : je ne la ferme pas ici par
  ricochet, et personne ne doit lire ce document comme l'ayant fermee. Le CM1 porte 165 enonces
  au-dela de 15 mots ; il a une population reelle, donc il merite une mesure et pas une deduction
  depuis le CE1.
- **Elle ne vaut pas pour le CM2**, qui n'est pas ouvert (`DEC-0089`, section 2).

## 7. Ce dont je reste la seule garde, et qu'aucun automate ne verra

Un paquet de CE1 conforme a `LON-CE1-1` et a `LON-CE1-2` **peut rester illisible, et je le
refuserai.** Le compteur ne compte que des mots : il ne voit ni une subordonnee, ni un referent
elide (*"il"* sans savoir qui), ni deux circonstances posees avant la question, ni un mot de
vocabulaire hors de portee. C'est la meme limite que `theo-archi` a ecrite pour le CP dans les
consequences d'`ADR-0089`, en me citant, et elle vaut mot pour mot au CE1.

Restent donc dans ma seule garde, sur tout paquet de CE1, et je les nomme parce que sinon l'equipe
croira couvert ce qui ne l'est pas :

1. **la lisibilite reelle** d'un enonce conforme aux deux clauses ;
2. **l'accessibilite du vocabulaire** au niveau vise ;
3. **la charge cognitive** d'une situation a deux temps - trois phrases autorisees n'obligent
   personne a en ecrire trois ;
4. **l'exactitude mathematique** de l'enonce, de la bonne reponse et des distracteurs, et la
   plausibilite des distracteurs ;
5. **le fait que l'item travaille bien la ligne `MAT-CE1-nn` qu'il declare**, et pas une ligne
   voisine plus facile : le garde-fou d'`ADR-0081` verifie qu'un code resout et qu'un item ne sert
   qu'une ligne, jamais que la declaration est vraie.

## 8. Ce que je route, et a qui

| Fait | A qui | Pourquoi ce n'est pas a moi |
|---|---|---|
| `LON-CE1-1` et `LON-CE1-2` sont entierement automatisables, sur le patron exact d'`ADR-0089` : meme convention, meme derivation de fin de phrase, seul le niveau borne et les deux nombres changent. **Tant qu'aucun garde-fou ne les execute, elles sont tenues par moi, en comptant** | `theo-archi`, via `sofia-sm` | Je dis la propriete ; la forme du test lui appartient. Je note, sans en tirer de conclusion technique, que la population de CE1 etant vide, un garde-fou ecrit aujourd'hui n'examinerait aucun objet - `ADR-0051` a une doctrine sur ce cas, et c'est la sienne, pas la mienne |
| Les 17 lignes de `squad/programme/mathematiques/competences-ce1.md` sont a copier dans `squad/programme/MATRICE-couverture.md`, a `non couvert`, avec l'effet de perimetre ecrit d'avance (ancien denominateur, nouveau denominateur, ancien taux, nouveau taux) | `camille-po` | `DEC-0089`, sections 3 et 5, le lui attribue nommement, et le referentiel existe desormais |
| Le premier paquet de contenu mathematiques CE1 devra declarer `niveau: "CE1"` et une ligne `MAT-CE1-nn` par item | `camille-po` puis les developpeurs | Conditions `C2` et `C3` de `theo-archi`, planning de s36, section 3 |

## 9. Une correction sur mon propre dossier, avant qu'on s'appuie dessus

**Les identifiants `LON-CE1-1` et `LON-CE1-2` portent trois segments, et j'ai deja renonce une fois
a cette forme.** A s32, je voulais glisser le niveau au milieu de l'identifiant de ma barre de
longueur ; j'ai pris `LON-1` et `LON-2`, a deux segments, pour passer a cote d'un trou du registre
d'identifiants - le motif ne lisait **aucune** forme a trois segments, donc un tel identifiant
aurait ete silencieusement non alloue. **Ce trou est ferme depuis le 2026-09-08** par la clause D
d'`ADR-0088`, et la source qui le dit est la Javadoc de
`bootstrap/src/test/java/com/recreo/archi/ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest.java`
(constante `IDENTIFIANT_A_TROIS_SEGMENTS`), ou mon contournement est ecrit noir sur blanc.

J'ecris donc aujourd'hui la forme que je voulais alors, parce que la dette qui l'interdisait a ete
payee - et non parce que je l'avais oubliee. Les deux identifiants sont alloues dans
`squad/decisions/REGISTRE-identifiants.md`, section 4, dans le meme commit que ce document, avec
leur ligne d'etat courant recomptee.

**Et je dis d'avance ce que ce recompte ne peut pas etre** : il est juste sur mon worktree dedie et
sur lui seul. D'autres lots de s36 peuvent allouer en parallele sur des branches soeurs que je ne
peux pas lire ; si c'est le cas, la CI rougira a l'assemblage, comme `ADR-0085` clause 5 le prevoit,
et **la reparation sera arithmetique** - aucune allocation ajoutee, aucune retiree, aucun sens
touche.
