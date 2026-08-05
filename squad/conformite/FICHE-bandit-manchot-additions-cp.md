# Fiche de conformite - bandit-manchot-additions-cp

- **Mecanique** : bandit manchot (mecanique generique - tirage d'une question, choix parmi
  plusieurs propositions dont une seule correcte, retour immediat juste/faux, enchainement de
  plusieurs manches avec score cumule).
- **Contenu(s)** : additions CP dont les deux operandes et la somme sont compris entre 0 et 10.
  Matiere premiere de reference (a repasser devant `nadia-prof` comme un contenu neuf, pas
  reprise telle quelle) : `contenu-repris/cp-calcul-mental.yml`.
- **Statut global** : VALIDE SOUS RESERVE (Done pedagogique accorde le 2026-08-05, reserves nommees
  pour s02)
- **Derniere mise a jour** : 2026-08-05

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| mathematiques | CP (cycle 2) | Nombres et calculs | Calcul mental - additions dans la limite de 10 (sous-partie de la competence referentiel, qui couvre aussi "puis 20", hors perimetre de ce jeu pour l'instant) | `squad/programme/mathematiques/competences-cp.md` |

## Criteres pedagogiques attendus - camille-po

- [x] Chaque manche presente une addition dont les deux operandes et la somme sont des entiers
      compris entre 0 et 10 (jamais de somme superieure a 10).
- [x] Chaque manche presente une unique bonne reponse parmi l'ensemble des propositions
      affichees.
- [x] Les propositions fausses (distracteurs) sont des nombres entiers plausibles, proches de la
      bonne reponse, jamais negatifs et jamais non entiers.
- [ ] La consigne et les retours (juste/faux) sont formules en une phrase courte, en vocabulaire
      courant de CP (pas de terme comme "somme" ; une formulation du type "combien ca fait ?").
- [x] Le retour juste/faux est accompagne d'un signal visuel (forme et/ou couleur) distinct du
      texte, sans emoji dans le gabarit (voir `DEC-0002`), pour un enfant qui ne dechiffre pas
      encore vite.
- [x] Les additions proposees varient d'une manche a l'autre sur une session de jeu (pas de
      question figee qui se repete en boucle).
- [x] Rien dans le code de la mecanique ne fait reference a "addition", "CP" ou aux bornes 0-10 :
      tout provient du contenu injecte (verifie par la regle ArchUnit de `theo-archi`, story
      S0-3 de `docs/backlog/backlog.md`).
- [ ] Tout texte destine a l'enfant (consigne, retours juste et faux, libelles de bouton,
      messages d'etat comme la fin de partie ou l'absence de partie) est redige en francais
      correct et integralement accentue, orthographe et ponctuation irreprochables (exigence de
      categorie B ajoutee par l'amendement du 2026-08-04 a `DEC-0002`, en reaction directe au
      premier motif de refus de ce sprint).
- [ ] Le decompte de score affiche a l'enfant utilise une forme qu'un enfant de CP sait lire
      (par exemple un nombre de reussites seul, ou un nombre d'etoiles/jetons), jamais une
      notation "x / y" ni de barre oblique (deuxieme motif de refus de ce sprint).
- [ ] Toute erreur cote serveur (session perdue, redemarrage) affiche a l'enfant un message
      court en francais avec un moyen visible de repartir, jamais le corps brut d'une erreur
      HTTP (troisieme motif de refus de ce sprint).
- [ ] Quand un meme item est rate a trois reprises (S1-5, `DEC-0004` point 4), l'indice affiche a
      la representation suivante de cet item est un contenu neuf, candidat a l'audit de
      `nadia-prof` au meme titre que la consigne ou le retour faux : sa formulation actuelle,
      "Compte avec tes doigts pour t'aider.", n'a encore ete jugee ni juste ni fausse
      pedagogiquement par personne. Prealable produit trouve en review et acte par `DEC-0005` :
      ce texte doit venir du paquet de contenu YAML (comme la consigne), jamais d'une constante du
      code du domaine ; correction de perimetre non bloquante pour le verdict de ce sprint,
      portee au backlog de s02.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Verdict courant - sprint s01

- **Verdict** : VALIDE SOUS RESERVE. **Done pedagogique ACCORDE.**
- **Date** : 2026-08-05
- **Audit joue** : oui, audit non degrade. Fat jar reconstruit depuis `round-two` et lance sur le
  port 8099. Parties reellement jouees de bout en bout : une partie de 10 manches en repondant juste
  a chaque fois (score final 10), une partie de 10 manches en repondant faux a chaque fois (score
  final 0), trois parties "une reussite puis que des echecs" pour forcer les deux aides de S1-5, et
  un echantillon de 171 manches reelles collectees sur 24 parties pour mesurer le contenu enonce par
  enonce. Ecrans traverses un par un : accueil, catalogue, manche, retour juste, retour faux, fin de
  partie, bouton de sortie, rechargement en cours de partie, rechargement apres perte de session,
  coup joue sans partie en cours, demarrage d'un jeu inconnu. L'ecran de catalogue vide a ete
  **rendu reellement**, en relancant l'application sur un catalogue sans aucun jeu, et pas seulement
  lu dans le gabarit. La forme de la coche de S1-6 a ete verifiee en rasterisant le `clip-path` de
  `recreo.css`, pour juger le dessin obtenu et pas la regle qui le declare.
- **Moyens, dits franchement** : le poste d'audit ne dispose que d'un JDK 25, pas du JDK 21 de la
  CI. Le Done technique reste celui de `yanis-qa` et de `theo-archi`, tous deux passes sur JDK 21 ;
  ce que je juge ici est le comportement observe a l'ecran, qui ne depend pas de cet ecart. Je ne
  reprononce aucun Done technique.

#### 1. Mes trois motifs de refus de s00, un par un

**Motif 1 - texte de l'enfant non accentue : LEVE.** Verifie dans le HTML reellement servi, pas dans
le diff. Le retour faux affiche "Non, c'est 8." ; la fin de partie "La partie est finie. Bravo, tu as
bien joué !" ; le bouton de sortie "Retour à l'accueil" ; le message de session perdue "Ta partie
s'est arrêtée. Choisis un nouveau jeu !" ; la consigne "Combien ça fait ?" arrive intacte. J'ai
repris les quatre gabarits (`accueil.html`, `fragments/manche.html`, `fragments/retour.html`,
`fragments/erreur.html`), la constante `PartieController.MESSAGE_PARTIE_PERDUE`, `catalogue.yaml` et
la constante d'indice : plus une seule forme non accentuee d'un mot qui en demande. Un residu, sans
gravite mais reel : le titre `<h1>Recreo</h1>` est le seul mot de l'ecran d'accueil et il n'est pas
accentue ; un enfant de CP qui le dechiffre lit /rekreo/ et non "récréo". C'est un nom de produit, je
ne le refuse pas, je le porte en reserve.

**Motif 2 - score illisible au CP : LEVE.** Releve en jeu : "Score : 0", "Score : 1", "Score : 10".
`manche.html` et `retour.html` rendent tous deux `'Score : ' + ${score.reussites()}` : le nombre de
tentatives n'est jamais affiche, donc aucune barre oblique et aucun symbole operatoire ne peut
apparaitre au-dessus du calcul. Le mot "Score" reste, et c'est bien : aucun de mes motifs ne le
visait, et un enfant de six ans le connait a l'oral.

**Motif 3 - ecran d'erreur infranchissable : LEVE.** Les deux chemins d'echec que j'avais reproduits
rendent desormais, en HTTP 200 et en `text/html;charset=UTF-8`, le meme ecran : "Oups ! Le jeu a un
souci." et "Recommencer" qui ramene a `/`. Plus aucun corps JSON, plus un mot d'anglais, plus de
statut 500 sous les yeux de l'enfant. Le ton est juste : la faute est mise du cote du jeu, jamais de
l'enfant. Une reserve de rendu subsiste, decrite plus bas : "Recommencer" est un lien HTML sans
aucune regle CSS, donc du texte souligne a la taille par defaut, pas un bouton.

**Les trois motifs qui fondaient mon refus sont donc leves, et je m'en tiens a la barre que j'avais
moi-meme ecrite en s00** ("Les trois motifs de refus corriges [...] Les reserves peuvent etre
etalees sur les sprints suivants"). Deplacer cette barre maintenant que l'equipe l'a atteinte ferait
de ma porte un caprice, pas une porte.

#### 2. Mes sept reserves de s00, une par une

1. **Partie sans fin et sans sortie : LEVEE.** La partie s'arrete exactement a 10 manches, verifie
   sur cinq parties completes. L'ecran de fin existe et est atteint. Le bouton de sortie est present
   sur la manche, sur le retour juste, sur le retour faux **et sur l'ecran de fin** - je l'ai vu
   servi sur les trois ecrans, ce que le rapport de `yanis-qa` ne pouvait qu'inferer. Il fonctionne :
   il rend la liste des jeux. 10 manches est la bonne longueur pour un CP, je le confirme apres
   avoir joue : la partie tient en une a deux minutes et se termine avant que l'attention ne tombe.
2. **Trop d'operandes nuls : LEVEE.** Mesure sur 171 manches reellement jouees : 22 manches a
   operande nul, soit **12,9 %**, contre 26 % releves en s00. Environ une manche par partie. C'est le
   bon reglage, et je le dis explicitement puisque la question m'est posee : la propriete du zero
   fait partie du programme de calcul de CP, la retirer entierement serait une erreur, et une manche
   sur dix la garde presente sans manger le temps de calcul. Voir toutefois le point 5 : le vrai
   reste de "calculs qui ne font pas calculer" n'est plus la.
3. **Aucune progression, aucune adaptation : NON LEVEE**, ce qui etait assume, et **aggravee par un
   fait que je n'avais pas mesure en s00**. Voir le point 5.
4. **Signal juste/faux dont la forme ne veut rien dire : LEVEE.** Voir le point 4.
5. **Texte trop petit : PARTIELLEMENT LEVEE.** Voir le point 4.
6. **Trois libelles a reecrire : LEVEE sur le fond, avec deux libelles a reprendre.** Voir le
   point 3.
7. **Perte de la partie au rechargement : LEVEE.** Verifie : un rechargement pendant une session
   valide represente la manche en cours avec son score, sans un mot de plus - c'est bien une
   continuite et non une reprise qui se remarque. Un rechargement apres perte de session affiche
   "Ta partie s'est arrêtée. Choisis un nouveau jeu !" au-dessus de la liste des jeux, ce qui est
   honnete envers l'enfant et lui donne immediatement quoi faire.

#### 3. Les libelles destines a l'enfant, et l'indice de DEC-0005

Je statue sur chacun des libelles que `relecteur-pedagogique` m'a remontes comme non fixes par une
decision, plus l'indice que `camille-po` me soumet par `DEC-0005`. Trois issues : accepte, accepte
sous reserve de reformulation (j'ecris la reformulation), refuse.

| Libelle | Verdict | Motif |
|---|---|---|
| "Encore !" | **ACCEPTE** | Deux syllabes, entierement dechiffrable au CP, vocabulaire oral quotidien d'un enfant de six ans, et il dit exactement la bonne chose : "j'en veux un autre". C'est ma suggestion de s00 et je la confirme apres l'avoir vue en place. |
| "Non, c'est X." | **ACCEPTE** | Present, une seule phrase, quatre mots. Ne juge pas l'enfant, corrige la reponse. Le rendu est propre : le nombre est dans un `span` en ligne, la ponctuation ne se decolle pas. |
| "Il n'y a pas de jeu pour le moment." | **ACCEPTE** | Francais correct, registre d'enfant, honnete. Neuf mots c'est long pour un CP, mais cet ecran est une anomalie que l'enfant ne verra pas en classe, et il n'a de toute facon aucune action a y faire. Je l'accepte tel quel et je porte en reserve le fait que cet ecran n'offre rien a faire. |
| "Calcul CP" | **ACCEPTE** | Dechiffrable ("Calcul" est regulier, "CP" est le nom que l'enfant donne a sa propre classe), et il ne porte plus le grapheme "ti" prononce /sj/ qui motivait ma remarque de s00. Reserve nommee plus bas : "Calcul" promet plus large que ce que le jeu fait (des additions seulement), et deviendra ambigu des qu'un deuxieme jeu de mathematiques CP existera. |
| "Oups ! Le jeu a un souci." / "Recommencer" / "Ta partie s'est arrêtée. Choisis un nouveau jeu !" / "Score : N" (fixes par `DEC-0004`) | **ACCEPTES** | Je les confirme apres les avoir vus a l'ecran. Le ton de l'ecran d'erreur est exactement le bon : il n'accuse jamais l'enfant. |
| "Bravo, c'est juste !" | **ACCEPTE** | Inchange, deja valide en s00, toujours juste et non exagere. |
| "Retour à l'accueil" | **ACCEPTE SOUS RESERVE DE REFORMULATION** | Motif : "accueil" n'est pas dechiffrable au CP. La suite "ueil" est un grapheme rare et tardif (accueil, orgueil, cueillir), et le "cc" devant "ue" se lit /k/ : aucun enfant de CP ne lit ce mot seul. Le sens est en plus abstrait - un enfant de six ans ne sait pas ce qu'est un "accueil" dans un logiciel. **Reformulation que j'ecris : "Les jeux".** Deux mots, sept lettres, tous les graphemes sont du CP, et le libelle dit ou l'on va, ce qui est vrai sur les trois ecrans ou ce bouton apparait, y compris l'ecran de fin ou l'enfant veut en choisir un autre. |
| "La partie est finie. Bravo, tu as bien joué !" | **REFUSE** | Motif : ce texte s'affiche a l'identique quel que soit le resultat. Je l'ai vu de mes yeux au bout d'une partie a 0 bonne reponse sur 10 : l'ecran montre une croix rouge, "Non, c'est 8.", "Score : 0", puis "Bravo, tu as bien joué !". Trois choses ne vont pas. Le jeu felicite un enfant qui n'a rien reussi, ce qui est faux et ce que l'enfant sait. Le mot "Bravo" est deja le signal de reussite du jeu ("Bravo, c'est juste !") : l'employer aussi quand il n'y a rien a feliciter use le seul mot de renforcement dont ce jeu dispose, et un renforcement qui tombe tout le temps ne renforce plus rien. Enfin, "Bravo" pose sous une croix rouge est simplement contradictoire pour un lecteur debutant. **Reformulation que j'ecris : la phrase de cloture inconditionnelle devient "C'est fini !"** - trois mots, entierement dechiffrables, aucune affirmation sur la performance, et la ligne "Score : N" juste au-dessus dit deja le resultat. Si l'equipe veut garder un mot chaleureux, il doit dependre du resultat ; c'est alors un choix produit a ecrire en DEC, pas un remplacement de chaine. |
| "Compte avec tes doigts pour t'aider." (indice, `DEC-0005`) | **ACCEPTE SOUS RESERVE DE REFORMULATION** | Sur le fond, l'aide est **juste et je la valide** : le comptage sur les doigts est une strategie explicitement enseignee au CP pour l'addition dans la limite de 10, et dix doigts sont exactement le modele de cet intervalle. Ce n'est ni un raccourci ni une betise, c'est ce qu'un maitre dit. Sur la forme, non : six mots et trente-cinq caracteres pour l'enfant qui vient d'echouer trois fois, donc tres probablement le lecteur le plus fragile ; "compte" (p muet) et "doigts" (/dwa/, "gts" muet) sont deux mots irreguliers ; et la moitie de la phrase ("pour t'aider") ne dit rien que l'enfant ne comprenne deja. Une aide ne doit pas etre un second obstacle. **Reformulation que j'ecris : "Compte sur tes doigts."** - quatre mots, c'est la phrase que l'enfant entend en classe, donc reconnue globalement meme quand elle n'est pas dechiffree. |

Sur l'emplacement de l'indice, tranche par `DEC-0005` : je confirme que le rendu vu par l'enfant ne
depend pas de l'endroit ou le texte vit, et que ce prealable ne bloquait donc pas mon audit.
J'ajoute une raison pedagogique d'aller au bout de cette decision : je viens de demander une
reformulation de ce texte, et tant qu'il vit dans une constante Java du domaine, une correction de
langue exige de recompiler le domaine. Une porte pedagogique qui ne peut pas faire corriger un mot
sans un cycle de developpement n'en est pas vraiment une.

#### 4. Les trois criteres visuels sans test automatise

`yanis-qa` a eu raison de me les remonter nommement plutot que de les couvrir d'un vert general.
Voici mon jugement, et il n'appartient qu'a moi puisque aucun automate ne le rend.

**S1-6, signal juste/faux independant de la couleur : OUI, satisfait.** Je n'ai pas juge sur la
declaration CSS, j'ai rasterise le `clip-path` de `.signal.est-juste .icone-signal` pour voir le
dessin : c'est une vraie coche, un trait court descendant vers la droite qui rejoint un trait long
montant, reconnaissable sans hesitation. `.signal.est-faux` est fait de deux barres croisees a
45 degres : c'est une croix. Ce sont les deux signes que l'enfant voit sur son cahier depuis la
rentree, ils portent le sens sans un mot et sans une couleur. C'est exactement ce que je demandais
en s00, et c'est mieux que ce que je demandais : les deux signes ne sont pas seulement distincts,
ils sont **appris**. Le point est important parce que les deux couleurs retenues, #2e7d32 et
#c62828, ont des luminances tres proches (environ 0,15 et 0,14) : en niveaux de gris, ou pour un
enfant qui confond le vert et le rouge, les deux pastilles ont pratiquement la meme densite. La
couleur, ici, n'informe reellement personne d'autre que ceux qui la distinguent - c'est la forme qui
fait tout le travail, et elle le fait. Les deux signes font 3 rem, ils sont vus avant d'etre lus.

**S1-9, taille du calcul et du signal : OUI, les deux criteres ecrits sont satisfaits** - et ils ne
couvrent pas tout. `.enonce` est a `2rem` et en gras, `.icone-signal` a `3rem` : le calcul est
desormais, et de loin, le plus gros texte de l'ecran, devant la consigne (1,25 rem), le verdict
(1,25 rem) et les propositions (1,1 rem). La hierarchie que je reprochais en s00 est inversee dans
le bon sens et mon reproche tombe. Mais j'ai regarde ce que les criteres ne disaient pas, et il
reste un trou : `.score`, `.indice`, `.fin-de-partie`, `.erreur`, `.pas-de-jeu` et
`.message-reprise` n'ont **aucune** regle de taille, donc 16 px ; et surtout, ni le bouton "Encore
!" ni le bouton de sortie n'ont de regle, or un bouton n'herite pas de la taille du texte : le
navigateur les rend a sa taille par defaut, environ 13 px. Le bouton sur lequel l'enfant appuie a
chaque manche est ainsi le plus petit texte de l'ecran. Ce n'est pas un manquement au critere de
`camille-po`, qui ne visait que l'enonce et le signal ; c'est le reste de ma reserve 5 de s00
("remonter le texte courant"), qui n'a pas ete traite. Reserve, pas refus.

**S1-8, ecran de catalogue vide : OUI, satisfait.** Je ne me suis pas contentee du gabarit : j'ai
relance l'application sur un catalogue sans aucun jeu et j'ai regarde la page servie. Elle affiche
"Recreo" puis "Il n'y a pas de jeu pour le moment.", sans liste vide, sans bouton mort, sans trace
technique. Le texte est correct et au registre. Le test manque, mais le critere, lui, est tenu.

#### 5. Justesse et reglage du contenu mathematique

**Exactitude : irreprochable.** Sur les 171 manches relevees enonce par enonce, **zero erreur** :
aucune somme superieure a 10, aucun operande hors bornes, la bonne reponse figure exactement une
fois dans les propositions a chaque manche, aucune proposition negative, aucun doublon, et le
libelle de chaque bouton est bien la valeur qu'il envoie. La position de la bonne reponse est
equilibree (55, 56 et 60 fois en premiere, deuxieme et troisieme position) : aucune strategie de
position n'est exploitable. Les distracteurs sont toujours a exactement un et deux de la bonne
reponse : plausibles, jamais absurdes, jamais devinables.

**Operandes nuls : bon reglage, la question m'etait posee et je reponds oui.** 12,9 % mesures, soit
environ une manche par partie, contre 26 % en s00. C'est ce que je demandais : la propriete du zero
reste presente, elle ne mange plus le temps de calcul. Deux petites choses restent, portees en
reserve et non en motif : "0 + 0" peut sortir (une fois sur 171) et est sorti en **premiere manche**
d'une de mes parties, or c'est la seule addition du contenu qui ne demande absolument rien ; et
"9 + 0" propose une bonne reponse egale a un operande deja ecrit, donc lisible sans calculer.

**Ce qui reste de "calculs qui ne font pas calculer" n'est plus la ou je le cherchais, et c'est le
seul constat de contenu vraiment serieux de cet audit.** Le tirage n'est pas uniforme sur les 66
couples possibles - je l'avais ecrit a tort en s00 et je corrige mon propre constat. Le premier
operande est tire uniformement dans 0-10, le second dans ce qui reste : les grandes sommes sont donc
tres largement favorisees. Mesure sur mes 171 manches :

| Somme | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 |
|---|---|---|---|---|---|---|---|---|---|---|---|
| Manches | 1 | 0 | 2 | 4 | 6 | 15 | 17 | 24 | 27 | 27 | 48 |
| Part | 0,6 % | 0 % | 1,2 % | 2,3 % | 3,5 % | 8,8 % | 9,9 % | 14,0 % | 15,8 % | 15,8 % | 28,1 % |

**43,9 % des manches portent sur une somme de 9 ou 10, et 7,6 % seulement sur une somme inferieure
ou egale a 4. Les sommes 1 et 2 n'apparaissent pratiquement jamais.** Une de mes parties de dix
manches n'a propose aucune somme en dessous de 7. Ce n'est pas anodin pour un CP : le haut de
l'intervalle est precisement l'endroit ou l'addition cesse d'etre du surcomptage et devient un
franchissement, donc l'endroit ou l'enfant fragile decroche - et c'est la que le jeu l'installe
presque une fois sur deux, sans jamais commencer plus bas ni monter progressivement. Le jeu ne
travaille pas "les additions dans la limite de 10", il travaille surtout leur bord superieur. Cela
ne rend pas le contenu faux, cela le rend mal reparti : c'est ma reserve 3 de s00 (aucune
progression), que le sprint n'avait pas mandat de traiter, mais chiffree et plus grave que je ne
l'avais dit.

**L'aide de S1-5 face a l'echec : la "manche plus simple" marche, l'indice n'est jamais atteint.**
La reprise d'un item deja reussi apres trois echecs consecutifs fonctionne, je l'ai declenchee et
observee (manches 5 et 8 de mes parties de test). L'indice, lui, est inatteignable en jeu reel, et
ce n'est pas une impression : `BanditManchotSession` n'affiche l'indice qu'a partir de trois echecs
**sur le meme item**, or un item ne peut revenir dans une partie que par la liste des items deja
reussis (le tirage rejette les items deja vus sur vingt tentatives ; mesure : un seul retour
"naturel" sur 24 parties), et chaque retour coute trois echecs consecutifs prealables. En deroulant
la sequence la plus favorable possible, le troisieme echec sur un meme item ne peut pas arriver
avant la 11e manche et l'indice ne peut pas s'afficher avant la **14e** ; une partie en dure 10
(`catalogue.yaml`, `nombreDeManches: 10`). Verifie : sur trois parties jouees exactement selon cette
sequence optimale, le compteur d'echecs du meme item a plafonne a 2 et l'indice n'est jamais apparu.
Les tests de `yanis-qa` sont justes et le comportement est correct ; c'est le reglage du produit qui
rend l'aide morte. Concretement, l'enfant qui rate tout des la premiere manche - celui pour qui
S1-5 a ete ecrite - ne recoit ni indice (jamais) ni manche plus simple (il n'a rien reussi a
represente), et lit "Bravo, tu as bien joué !" a la fin. Il n'est plus enferme, puisque la partie
s'arrete et qu'il peut sortir : mon motif de s00 est bien leve. Mais il n'est pas aide.

**Biais et ton** : rien de nouveau a signaler, et c'est toujours a porter au credit de l'increment.
Des nombres nus, aucun personnage, aucun prenom, aucun contexte familial, materiel ou culturel
presuppose, donc aucun stereotype possible. Aucun classement, aucune comparaison a d'autres enfants.
Le seul defaut de ton de cet increment est le "Bravo" inconditionnel de la fin de partie, traite au
point 3.

#### 6. Ce qui est accorde, et ce qui part en reserve pour s02

**Accorde, sans condition.** Le Done pedagogique de l'increment `bandit-manchot-additions-cp` est
prononce. Les trois motifs de mon refus de s00 sont leves et verifies en jeu ; cinq de mes sept
reserves sont levees ; le contenu mathematique est exact sur 171 manches ; les trois criteres
visuels que nul test ne couvre sont tenus. Ce jeu peut etre mis devant une classe de CP.

**Reserves pour s02.** Elles sont nommees separement pour que `camille-po` en fasse des stories
distinctes. Je n'ecris pas le backlog et je ne priorise pas ; je donne le motif.

1. **Le libelle de fin de partie est refuse et doit changer avant tout autre travail sur ce jeu.**
   Remplacer "La partie est finie. Bravo, tu as bien joué !" par "C'est fini !". Toute felicitation
   doit dependre du resultat, ce qui est un choix produit a ecrire.
2. **Le bouton de sortie doit devenir lisible au CP** : "Retour à l'accueil" -> "Les jeux".
3. **L'indice doit devenir atteignable.** En l'etat, l'aide la plus importante de S1-5 ne s'affiche
   jamais dans une partie de 10 manches. Deux pistes possibles, que je ne tranche pas : abaisser le
   seuil d'echecs sur le meme item, ou declencher l'indice sur les echecs consecutifs plutot que sur
   la repetition d'un item. Ce qui compte pour moi : un enfant qui rate trois fois de suite doit
   recevoir une aide dans la partie en cours.
4. **L'indice doit etre reformule** : "Compte sur tes doigts." Et il doit vivre dans le YAML, comme
   `DEC-0005` l'a deja tranche, sinon la reformulation suivante coutera une recompilation.
5. **La repartition des sommes doit etre corrigee.** 43,9 % des manches sur les sommes 9 et 10 et
   7,6 % sous 5, ce n'est pas la competence annoncee, c'est son bord superieur. Un tirage uniforme
   sur les 66 couples serait deja une nette amelioration ; une progression du bas vers le haut de
   l'intervalle serait la bonne reponse, et rejoint ma reserve 3 de s00.
6. **Les tailles de texte qu'aucun critere ne visait.** Aucun texte lu par l'enfant ne devrait
   passer sous la taille de la consigne (1,25 rem), et les boutons "Encore !" et de sortie ne
   devraient pas etre rendus a la taille par defaut du navigateur (environ 13 px), sous peine
   d'etre le plus petit texte de l'ecran. `.indice` en particulier doit etre au moins aussi gros que
   la consigne : c'est le texte destine au lecteur le plus fragile.
7. **"Recommencer" doit ressembler a un bouton.** Sur l'ecran d'erreur, c'est un lien HTML sans
   aucune regle CSS : du texte souligne a la taille par defaut. Il est trouvable parce que l'ecran
   est vide, mais un enfant de six ans ne sait pas qu'un texte souligne se clique.
8. **"0 + 0" ne devrait pas pouvoir sortir**, et surtout pas en premiere manche : c'est la seule
   addition du contenu qui ne demande rien du tout.
9. **"Calcul CP" promet plus large que ce que le jeu fait.** Le libelle redeviendra ambigu des qu'un
   deuxieme jeu de mathematiques CP existera. A reprendre a ce moment-la, sans reintroduire le
   grapheme "ti" de "Additions".
10. **Le titre "Recreo" n'est pas accentue** alors que c'est le seul mot de l'ecran d'accueil et que
    l'enfant le dechiffre. A trancher comme une question de marque, pas par moi.
11. **L'ecran de catalogue vide n'offre aucune action.** Accepte pour aujourd'hui parce que c'est un
    ecran d'anomalie, mais un enfant devant un ecran sans rien a faire reste un enfant bloque.

#### 7. Suites hors de ma main

- `squad/programme/MATRICE-couverture.md` : la ligne mathematiques / CP / "Calcul mental - additions
  dans la limite de 10 (puis 20)" **reste `en cours`**, et je motive ce point precisement parce que
  le Done pedagogique, lui, est accorde. Les deux ne disent pas la meme chose : le Done porte sur
  l'increment, la matrice porte sur la couverture d'une competence. Un jeu qui passe 43,9 % de son
  temps sur deux des onze sommes possibles et 7,6 % sur les cinq plus petites ne couvre pas
  "les additions dans la limite de 10", il en couvre le haut. **Condition pour que je fasse passer
  cette ligne a `couvert-valide` : la reserve 5 traitee.** Je n'ai pas edite cette ligne, elle
  appartient a `camille-po`.
- Les criteres coches dans la section "Criteres pedagogiques attendus" de cette fiche appartiennent
  a `camille-po` : je ne les touche pas. Pour lui eviter d'avoir a me redemander, les quatre
  criteres restes non coches en s00 sont, de mon point de vue, satisfaits aujourd'hui - consigne et
  retours en vocabulaire de CP, texte integralement accentue, score lisible au CP, erreur serveur
  franchissable - et le dernier, celui de l'indice de S1-5, ne l'est pas : le texte est accepte sous
  reserve de reformulation et il n'est de toute facon jamais atteint en jeu.
- Rien a demander cette fois-ci a `DEC-0002` : l'exigence positive que j'avais reclamee en s00 a ete
  ecrite dans `CLAUDE.md` par le `relecteur-pedagogique`, et l'effet se voit - plus une seule faute
  d'accent dans le texte de l'enfant.

---

### Historique - verdict du sprint s00, conserve pour trace

> Ce verdict est celui qui a motive le sprint s01 tout entier. Il est conserve integralement :
> un refus efface n'a plus aucune valeur de preuve.

- **Verdict** : REFUSE
- **Date** : 2026-08-04
- **Audit joue** : oui. Fat jar `bootstrap/target/recreo.jar` lance sur le port 8099 avec le JDK
  21. Trois sessions reellement jouees : une session de 41 manches en repondant juste a chaque
  fois, une session de 20 manches en repondant faux a chaque fois, une session courte en
  alternant, plus des manches jouees a la main sur la page d'accueil, l'ecran de jeu, le retour
  juste et le retour faux. 61 manches relevees enonce par enonce, avec verification arithmetique
  de chaque somme, de chaque proposition et de la position de la bonne reponse. Lecture du HTML
  reellement servi, de `adapters/adapter-web/src/main/resources/templates/` (les trois gabarits)
  et de `adapters/adapter-web/src/main/resources/static/css/recreo.css` pour juger le rendu.
  Audit non degrade.

#### Ce qui a ete verifie

- **Ancrage au programme** : la competence annoncee est bien celle travaillee. Chaque manche
  demande une somme de deux entiers dont le resultat reste dans la limite de 10, ce qui correspond
  a la ligne "Calcul mental - additions dans la limite de 10 (puis 20)" de
  `squad/programme/mathematiques/competences-cp.md`, domaine "Nombres et calculs". Ce n'est pas
  une competence voisine plus facile : l'enfant calcule, il ne reconnait pas et ne recite pas.
  Reserve d'ancrage cependant : le tirage est uniforme sur les 66 couples possibles, donc environ
  un tiers des manches comportent un operande nul (releve : 16 manches sur 61, soit 26 %, pour
  une esperance theorique de 21 couples sur 66, soit 32 %). Or "7 + 0" ne fait pas travailler le
  calcul mental, il fait constater une propriete du zero. Un quart a un tiers du temps de jeu ne
  travaille donc pas la competence visee. Le "puis 20" de la ligne du referentiel reste hors
  perimetre, conformement a `DEC-0001`.
- **Lisibilite au niveau vise** : la consigne est bonne et je la valide telle quelle. "Combien ça
  fait ?" fait trois mots, tous du vocabulaire oral et de lecture de CP, tous composes de
  graphemes travailles en CP, et elle evite le mot "somme" comme demande. Le reste du texte
  affiche a l'enfant n'est pas au niveau : "Manche suivante" emploie "manche" dans un sens de jeu
  que l'enfant de CP n'a pas (pour lui une manche est celle d'un vetement) et "suivante" est un
  mot abstrait ; "Score : 1 / 2" emploie la notation fractionnaire, qui n'est pas une notion de
  CP ; "Additions CP", libelle du bouton d'accueil, contient le grapheme "ti" prononce /si/, qui
  n'est pas une acquisition de CP (reconnaissance globale seulement) ; "La partie est terminee.
  Merci d'avoir joue !" et "Aucune partie disponible pour le moment." sont d'un registre
  d'adulte. Rendu : aucune regle CSS ne grossit l'enonce, la consigne ni le verdict, qui
  s'affichent donc a la taille par defaut de 16 px, soit plus petit que les boutons de
  proposition (1,1 rem). Le calcul a lire est le plus petit texte de l'ecran.
- **Charge cognitive** : correcte sur le coeur du jeu. Trois propositions, une seule question a
  l'ecran, une seule action possible, pas de chronometre, pas de score a interpreter pour agir.
  L'enfant tient en tete deux nombres et rien d'autre. Un element parasite : la ligne
  "Score : x / y" est posee juste au-dessus d'une expression arithmetique, dans un jeu de
  mathematiques, avec un symbole que l'enfant de CP ne sait pas interpreter - il y a un risque
  reel qu'il la lise comme une operation a faire.
- **Progression** : il n'y en a aucune. Le tirage est uniforme et sans memoire d'une manche a
  l'autre : la manche 1 peut etre "10 + 0" et la manche 40 "0 + 1". La difficulte ne monte pas,
  ne s'adapte pas a ce que l'enfant reussit ou rate, et la partie ne s'arrete jamais - verifie
  dans le jeu (41 manches enchainees sans fin) et confirme par la Javadoc de
  `AdditionsBorneesContentSource.next`, qui documente que ce tirage ne renvoie jamais de fin. Il
  n'existe par ailleurs aucun bouton pour revenir a l'accueil ni pour arreter : l'enfant n'a
  aucun moment "j'ai fini".
- **Traitement de l'erreur** : le retour faux fait le minimum utile - il ne dit pas seulement
  "faux", il donne la bonne reponse, ce qui vaut mieux qu'un simple verdict. Mais il s'arrete la :
  pas de deuxieme essai, pas d'aide pour recalculer, et surtout aucune adaptation. J'ai joue 20
  manches fausses d'affilee : l'enfant voit vingt fois la meme phrase et un score qui reste a
  0 sur 20, sans un mot d'encouragement, sans que le jeu ne facilite quoi que ce soit, et sans
  pouvoir s'arreter autrement qu'en fermant l'onglet. Un enfant en difficulte n'est pas aide, il
  est enferme. Second point sur ce chapitre : quand la session HTTP est perdue (onglet laisse
  ouvert, serveur redemarre), le clic suivant de l'enfant remplace le jeu par un bloc technique
  en anglais - verifie, la reponse servie est
  `{"timestamp":...,"status":500,"error":"Internal Server Error",...}`. L'enfant est bloque devant
  du texte qu'il ne peut pas lire et ne peut pas repartir seul. Je decris le symptome, la cause
  technique appartient a `theo-archi`.
- **Exactitude du contenu** : les mathematiques sont exactes, sans une seule faute sur 61 manches
  relevees. Aucune somme superieure a 10, aucun operande hors 0-10, la bonne reponse figure
  toujours exactement une fois, sa position varie (releve sur 41 manches : 16 fois en premier,
  14 fois en deuxieme, 11 fois en troisieme, donc aucune strategie de position exploitable),
  aucune proposition negative ni non entiere, et le libelle de chaque bouton est bien la valeur
  qu'il envoie. Les distracteurs sont toujours a un, deux ou trois de la bonne reponse : ils sont
  plausibles, jamais absurdes, et la bonne reponse n'est pas systematiquement la valeur du milieu.
  Les distracteurs qui depassent 10 (11 et 12) ne sont pas un defaut : ils empechent l'enfant de
  gagner par elimination sans calculer. Les 41 enonces d'une meme partie etaient tous differents.
  En revanche le texte francais affiche a l'enfant comporte trois fautes d'orthographe, detaillees
  dans le motif ci-dessous.
- **Biais et ton** : rien a signaler, et c'est a mettre au credit de l'increment. Aucun personnage,
  aucun prenom, aucun contexte familial, materiel ou culturel presuppose : des nombres nus, donc
  aucun stereotype de genre, d'origine ou de milieu possible. Le ton n'est ni condescendant ni
  infantilisant : "Bravo, c'est juste !" est juste, sobre et non exagere ; le retour faux ne
  culpabilise pas et ne juge pas l'enfant, il corrige la reponse. Le signal rouge est associe a
  la reponse, pas a l'enfant. Aucun classement, aucune comparaison a d'autres enfants.

#### Motif

Le Done pedagogique est refuse pour trois motifs. Les deux premiers portent sur du texte lu par
l'enfant, le troisieme sur un ecran d'echec que l'enfant ne peut pas franchir.

1. **Le texte francais affiche a l'enfant comporte trois fautes d'orthographe, sur l'ecran qu'il
   voit a chaque erreur.** Dans
   `adapters/adapter-web/src/main/resources/templates/fragments/retour.html`, le retour faux est
   ecrit "Ce n'est pas ca. La bonne reponse etait X." Il faut lire "Ce n'est pas ça. La bonne
   réponse était X." : "ca" sans cedille n'est pas "ça", "reponse" et "etait" sans accent aigu ne
   sont pas "réponse" et "était" - sans accent, "etait" ne se lit meme pas comme un mot francais.
   Un enfant de CP est en train de construire son orthographe lexicale et ses correspondances
   graphemes-phonemes : tout texte mis sous ses yeux doit etre orthographiquement irreprochable,
   et l'accent aigu est un grapheme, pas une decoration. La meme faute est presente dans le
   message de fin de partie du meme gabarit, "La partie est terminee. Merci d'avoir joue !"
   (lire "terminée", "joué"). C'est une erreur de contenu scolaire, donc un motif de refus a lui
   seul. Precision importante pour la correction : le probleme est bien dans ces gabarits et non
   dans un encodage, puisque la consigne portee par `catalogue.yaml`, "Combien ça fait ?", est
   correctement accentuee et arrive intacte dans le HTML servi (en-tete
   `Content-Type: text/html;charset=UTF-8`). Correction attendue : accentuer tout le texte destine
   a l'enfant dans les trois gabarits. Voir aussi ma relecture de `DEC-0002` ci-dessous : cette
   faute est la consequence directe d'un manque de cette decision.
2. **Le score est affiche dans une notation que l'enfant de CP ne sait pas lire, au-dessus du
   calcul a faire.** L'ecran affiche "Score : 0 / 0" des la premiere manche, puis "Score : 1 / 2".
   La notation a / b comme rapport reussites sur essais n'est pas une notion de CP, et la barre
   oblique n'y a aucun sens etabli. Placee juste au-dessus d'une expression du type "6 + 3" dans
   un jeu de mathematiques, elle peut etre prise pour une operation. Correction attendue : afficher
   un decompte que l'enfant sait lire, par exemple un nombre de reussites seul ou un nombre de
   jetons ou d'etoiles gagnes, et ne pas montrer le nombre d'essais rates sous forme de rapport.
3. **Quand la partie est perdue cote serveur, l'enfant recoit un bloc technique en anglais et ne
   peut pas repartir.** Reproduit : un coup joue sans partie en cours renvoie un HTTP 500 dont le
   corps JSON est insere dans la zone de jeu, soit
   `{"timestamp":"...","status":500,"error":"Internal Server Error","path":"..."}`. Un enfant de CP
   ne peut ni lire ce texte ni comprendre qu'il doit recharger la page ; il est bloque sans issue,
   ce qui contredit la regle "un enfant bloque doit pouvoir avancer". Correction attendue : sur
   toute erreur, l'enfant voit un message court en francais et un bouton pour recommencer.
   Symptome constate cote ecran ; le traitement technique appartient a `theo-archi`.

#### Reserves

Ces points ne sont pas des motifs de refus, mais ils devront etre traites pour que ce jeu soit
autre chose qu'un exerciseur brut. Ils sont nommes separement pour que `camille-po` en fasse des
stories distinctes.

1. **La partie n'a pas de fin et l'enfant n'a aucune sortie.** J'ai enchaine 41 manches sans que
   rien ne s'arrete, et il n'existe aucun bouton pour revenir a l'accueil. Un enfant de CP a
   besoin d'un format court et borne, et d'un moment de cloture ("tu as fini, tu as gagne tant").
   A trancher comme une question produit : longueur d'une partie (a mon avis 10 manches), ecran de
   fin, bouton d'arret toujours visible. A noter que le message de fin existe deja dans le
   gabarit mais qu'il est inatteignable avec ce contenu.
2. **Un quart a un tiers des manches comporte un operande nul.** Releve 16 fois sur 61 manches.
   "7 + 0" ne fait pas travailler le calcul mental dans la limite de 10. Le tirage doit limiter la
   proportion d'additions a operande nul, sans les supprimer completement (la propriete du zero a
   sa place, mais pas un tiers du temps).
3. **Aucune progression et aucune adaptation.** La difficulte est constante et aleatoire, et le jeu
   ne reagit pas a une serie de reussites ni a une serie d'echecs. Attendu a terme : commencer par
   les petites sommes et monter vers les sommes proches de 10, et proposer quelque chose de
   different a l'enfant qui rate plusieurs fois de suite.
4. **Le signal juste/faux est percu mais sa forme ne veut rien dire.** Verifie dans
   `recreo.css` : trois canaux simultanes (fond du bloc teinte, pastille coloree, forme de la
   pastille), donc l'enfant voit instantanement qu'il s'est passe quelque chose - le critere de
   `camille-po` est litteralement satisfait. Mais le sens ne repose que sur l'opposition vert
   (#2e7d32) / rouge (#c62828), et la forme n'apporte aucun sens : un disque et un triangle ne
   signifient ni "juste" ni "faux" pour un enfant de 6 ans, et le triangle rouge evoque plutot un
   panneau de danger. Consequence concrete : un enfant daltonien - environ un garcon sur douze,
   et le vert/rouge est precisement la paire qu'il confond - voit deux pastilles de meme teinte
   apparente et doit deviner laquelle veut dire "juste". Correction attendue, compatible avec
   `DEC-0002` puisqu'elle n'introduit aucun emoji dans le gabarit : remplacer le disque et le
   triangle par les deux signes que l'enfant connait deja de son cahier, une coche pour juste et
   une croix pour faux, dessines en CSS ou en SVG, et garder la couleur comme second canal. Le
   signal devient alors lisible sans couleur et sans lecture.
5. **Le texte a lire est trop petit et le signal trop discret pour un lecteur debutant.** L'enonce,
   la consigne et le verdict sont a la taille par defaut (16 px) et la pastille de signal fait
   1,5 rem, alors que les boutons de proposition sont a 1,1 rem : le calcul a lire est le plus
   petit texte de l'ecran. Attendu : grossir nettement l'enonce (au moins 2 rem), remonter le
   texte courant, et porter la pastille de signal a au moins 3 rem.
6. **Trois libelles a reecrire en vocabulaire de CP.** "Manche suivante" (le mot "manche" n'a pas
   ce sens pour un enfant de CP ; preferer "Encore !" ou "On continue") ; "Merci d'avoir joue !"
   et "Aucune partie disponible pour le moment." (registre d'adulte). Le retour faux gagnerait
   aussi a passer au present et a une seule phrase : "Non, c'est 4." plutot que "La bonne réponse
   était 4."
7. **La perte de la partie au rechargement de la page.** Si l'enfant appuie sur F5, il revient a
   l'accueil et son score repart de zero. Peu grave avec une partie sans fin, mais a revoir en
   meme temps que la reserve 1.

#### Ce qu'il faut pour que ce jeu passe a VALIDE

Les trois motifs de refus corriges, c'est-a-dire : le texte de l'enfant correctement accentue, un
score lisible au CP, et un ecran d'erreur franchissable. Les reserves peuvent etre etalees sur les
sprints suivants. Le contenu mathematique lui-meme, la consigne et le ton n'ont pas besoin d'etre
retouches : ils sont bons.

#### Suites hors de ma main

- `squad/programme/MATRICE-couverture.md` : la ligne mathematiques / CP / "Calcul mental -
  additions dans la limite de 10 (puis 20)" **reste `en cours`** et ne peut pas passer a
  `couvert-valide`. Je n'ai pas edite cette ligne, elle appartient a `camille-po`.
- Amendement demande a `DEC-0002`, via `sofia-sm` : voir ci-dessous.

#### Relecture de DEC-0002 sur le fond pedagogique, a la demande de camille-po

Je valide la distinction des deux registres, et je confirme le choix technique retenu pour le
signal de S0-5 : pour un enfant qui ne dechiffre pas, le signal de reussite est appris par
repetition, donc il doit avoir exactement la meme apparence a chaque fois et sur tous les
appareils - ce qu'un caractere emoji ne garantit pas, en plus de ne pas permettre de maitriser sa
taille ni son contraste. Sur le fond, une regle de style d'ingenierie n'a effectivement rien a
dire sur la qualite d'un support pedagogique.

Mais `DEC-0002` a un manque, et ce sprint en donne la preuve. La decision **exempte** la
categorie B (le contenu vu par l'enfant) de la regle de redaction du depot, sans y mettre aucune
exigence positive a la place. Elle retire donc ce texte du controle du `relecteur-pedagogique` et
le confie a ma seule porte, qui ne s'exerce qu'une fois, en fin de sprint. Resultat observable :
l'habitude ASCII du depot a fuite dans le registre de l'enfant precisement la ou plus aucune
regle ne s'applique et ou plus personne ne regarde, et le retour faux affiche "reponse" et "etait"
a un enfant qui apprend a lire. Ce que je demande a `camille-po`, via `sofia-sm` : ajouter a
`DEC-0002` une exigence positive pour la categorie B, du type "le texte destine a l'enfant est
redige en francais correct et integralement accentue, orthographe et ponctuation irreprochables,
parce qu'il est lu par un enfant en cours d'apprentissage de la lecture", et faire verifier cette
exigence-la par le `relecteur-pedagogique` comme n'importe quelle autre. L'exemption de la regle
de style ne doit pas se lire comme une absence de regle. La meme precision devrait figurer dans
le complement de `CLAUDE.md` que `DEC-0002` signale deja.

Deuxieme point, sur les deux ambiguites que `DEC-0002` signale par avance dans
`contenu-repris/associer/cp-formes.yml`, pour que la future fiche ne corrige pas ce qui est bon :
les **deux triangles d'orientation differente associes au meme mot "triangle" ne sont pas un
defaut, c'est une qualite**. La competence de CP est "reconnaitre et nommer quelques figures
planes" : elle demande justement que l'enfant abstraie l'orientation, la taille et la couleur.
Ne montrer qu'un triangle toujours pointe vers le haut installerait un prototype faux, erreur
classique dont les eleves se defont ensuite difficilement. Il faut donc garder les deux
orientations. Le **carre bleu** est neutre a une seule condition, que je verifierai ce jour-la :
qu'aucun item du meme lot ne fasse jamais de la couleur le critere de tri, et que la consigne ne
parle jamais de couleur. Si ces deux conditions tiennent, la couleur est un simple attribut de
dessin et l'enfant ne peut pas se tromper de critere.
