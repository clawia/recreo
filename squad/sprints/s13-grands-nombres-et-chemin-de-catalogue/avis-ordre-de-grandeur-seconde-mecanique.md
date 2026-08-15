# [Sprint s13] Avis d'ordre de grandeur - une seconde mecanique (visuel, saisie, audio)

- **Date** : 2026-08-15
- **Auteur** : theo-archi
- **Demande** : `DEC-0030` section 6 point 3, routee par `planning.md` - un avis de faisabilite et
  d'ordre de grandeur, **pas un chiffrage detaille, pas une architecture**, sans capacite de
  developpement affectee.
- **Arbre juge** : `round-two` a `9430cfd`. Mesures sur JDK 21.0.10 (voir `avis-faisabilite.md` pour
  le constat de poste).
- **Ce que je ne fais pas ici** : je ne priorise pas, je ne recommande aucune des trois familles, je
  ne fixe aucun sprint d'ouverture. `nadia-prof` rend l'ordre pedagogique en parallele ;
  `camille-po` reste seule a sequencer. Je dis le cout, rien d'autre.

## Le fait qui reordonne la question, et il est architectural

`camille-po` classe les 35 lignes non couvertes par **ce dont l'enfant a besoin** - un dessin, une
saisie, un son. C'est le bon classement pour elle. Ce n'est pas celui du cout, et l'ecart n'est pas
un detail de vocabulaire :

**Deux des trois familles ne demandent aucune seconde mecanique.**

Une mecanique est definie par le **geste de l'enfant** et par ce que le serveur en fait
(`GameMechanic.apply(session, coup)`). Or :

| famille | le geste de l'enfant | la mecanique |
|---|---|---|
| **visuel** | presser l'une des trois ou quatre propositions | **inchangee** - seule la facon de dessiner une proposition change |
| **audio (emission)** | presser l'une des trois ou quatre propositions | **inchangee** - seule la facon de presenter l'enonce change |
| **saisie** | **produire** une reponse au lieu d'en choisir une | **neuve** - c'est la seule des trois |

Le visuel et l'audio sont des **formes d'item**, pas des mecaniques. `BanditManchot` les sert sans
une ligne de changement : il tire un item, melange ses propositions, compare la reponse pressee a la
bonne. Que la proposition soit le mot "carre", le glyphe d'un carre ou le dessin d'un carre ne le
concerne pas ; que l'enonce soit lu ou entendu non plus.

**Consequence pour l'arbitrage de `camille-po`, et c'est le seul endroit ou mon avis touche le
sien** : son motif 1 est que *"le principe directeur du produit n'a jamais ete mis a l'epreuve"*,
faute d'une seconde mecanique a comparer a la premiere. Ni le visuel ni l'audio ne le mettront a
l'epreuve - ils ouvrent des lignes sans jamais construire de seconde mecanique. **Seule la saisie
repond a ce motif-la.** Si le motif 1 pese dans sa decision, il ne pointe pas vers la famille la plus
large ni vers celle qui ferme un domaine ; il pointe vers la saisie. Je ne dis pas qu'il doit peser -
c'est le sien.

## Le decouplage tient-il ? Oui, et c'est mesure

Le point d'extension existe deja et il est nomme dans le code depuis le sprint 0 : `Item` est une
interface **scellee**, `permits Question`, avec en Javadoc *"une deuxieme permission ne sera ajoutee
que par la mecanique qui en aura besoin"* (`ADR-0004`). Ce n'est pas une abstraction documentee et
jamais implementee - c'est le cas inverse : elle existe dans le code et attend son second membre.

**Mesure** - j'ai ajoute un second membre a `permits` et compile :

1. **le compilateur, pas un test, refuse d'abord** : `BanditManchotSession.questionDe` puis
   `PropositionsAccentueesJouablesIT` ne compilent plus, *"the switch expression does not cover all
   possible input values"*. **Il y a exactement deux `switch` exhaustifs sur `Item` dans tout le
   depot**, un en production, un en test. Le cout d'une forme d'item de plus est donc borne et rendu
   visible par javac, pas par la vigilance ;
2. **la regle `leContratDeContenuNePorteQueDuTexte` (`ADR-0006`) tranche la forme des champs**, et
   elle mord dans les trois sens qui comptent. Champs de sonde `int heures`, `byte[] son`,
   `java.nio.file.Path image` :

```
com.recreo.domain.content.SondeItem.heures cite le type int, qui n'est ni du texte, ni une collection du JDK, ni un type du contrat de contenu
com.recreo.domain.content.SondeItem.image cite le type java.nio.file.Path, qui n'est ni du texte, ni une collection du JDK, ni un type du contrat de contenu
com.recreo.domain.content.SondeItem.son cite le type [B, qui n'est ni du texte, ni une collection du JDK, ni un type du contrat de contenu
```

C'est la meilleure nouvelle de cet avis, et elle n'etait pas acquise : **la regle ecrite contre la
dette 2 de la version precedente** (une configuration taillee pour l'arithmetique que le francais
remplissait de valeurs factices) **force par avance la bonne forme pour les trois familles**. Un item
d'horloge ne portera pas `int heures, int minutes` - il portera du texte. Un item sonore ne portera
pas d'octets - il nommera une ressource par une chaine, et c'est l'adaptateur qui la sert. Aucun
amendement d'`ADR-0006` n'est requis par aucune des trois familles.

**Quels ports changent, en tout et pour tout** :

| port | visuel | saisie | audio |
|---|---|---|---|
| `Item` (`permits`) | +1 forme | +1 forme (peut-etre) | +1 forme |
| `ContentSource.next` | inchange | inchange | inchange |
| `GameMechanic.start` / `apply` | inchange | inchange | inchange |
| `Move` (interface marqueur) | inchange | +1 implementation dans le paquet de la mecanique neuve | inchange |
| `GameSession` | inchange | +1 implementation | inchange |
| chargeur `adapter-content` | +1 `type:` de schema | +1 `type:` | +1 `type:` |
| `adapter-web` | rendu | rendu + un `<input>` | rendu + un `<audio>` |

Le chargeur unique pilote par schema (`ADR-0007`) porte deja **deux** types (`question`,
`additions-bornees`) : ajouter le troisieme est le geste pour lequel il a ete concu, et c'est
precisement la dette 3 de la version precedente qui ne se reproduira pas.

## Ce qu'`ADR-0008` autorise et ce qu'il interdit, famille par famille

La regle est courte : le verdict reste serveur, la bonne reponse ne quitte jamais le serveur avant
que l'enfant ait repondu, et le JavaScript maison tient dans 4 096 octets et 100 lignes d'agrement -
basculer une classe, animer, deplacer le focus. Une mecanique a forte animation peut demander un
**ilot JavaScript** par un `ADR` nomme, qui leve le budget mais jamais l'autorite.

- **Visuel : `ADR-0008` n'est pas sollicite.** Un SVG en ligne ou une image servie depuis le fat jar
  se rend sans une ligne de JavaScript. Aucun ilot a ouvrir. Ce que le visuel touche est ailleurs, et
  ce n'est pas anodin : chaque image porte un `alt`, c'est-a-dire **du texte que le serveur envoie et
  qu'un enfant peut recevoir sans le voir** - exactement le critere que `nadia-prof` vient de poser
  sur S12-5. Ouvrir le visuel sans avoir ferme ce canal, c'est ouvrir une famille entiere de textes
  non gardes. Les deux sujets sont lies, et le lien va dans ce sens-la.
- **Saisie : `ADR-0008` n'est pas sollicite non plus, et il est meme mieux servi.** Un `<input>` dans
  un formulaire htmx est du HTML ; le serveur recoit une chaine et rend un verdict. La bonne reponse
  **ne part meme plus** dans la page, alors qu'aujourd'hui elle y est, melangee aux distracteurs. Un
  `<input>` entre naturellement dans l'ordre de tabulation que
  `AucunArretDeTabulationNEstDestructeurTest` derive deja.
- **Audio : `ADR-0008` n'est pas sollicite pour l'emission** - `<audio controls>` joue un fichier
  sans JavaScript. **Il l'est pour la synthese vocale du navigateur** (`speechSynthesis`), et je la
  refuserais : ce que l'enfant entend deviendrait un produit du systeme d'exploitation, invisible du
  serveur, donc intestable - c'est la dette 4 de la version precedente sous un autre nom, et le
  contenu pedagogique s'y logerait. **Il l'est absolument pour la reconnaissance vocale** ; voir
  ci-dessous.

## Ordre de grandeur

Trois barreaux, pas des jours-homme. Le barreau porte le **risque de conception**, pas la quantite de
frappe.

### Visuel - le moins cher des trois par sa mecanique, une seule vraie question de conception

Aucune mecanique, aucun port de domaine autre qu'une forme d'item, aucun ilot JavaScript. La
question, une seule, est **qui calcule le dessin**. Deux chemins, et je nomme celui que je prendrais
et ce qui me ferait changer d'avis :

- le paquet porte le dessin (un chemin de SVG, une geometrie) : le contenu devient un fichier de
  presentation, et le jour ou l'habillage change il faut rouvrir 24 items. Refuse ;
- le paquet porte la **donnee** en texte ("10 h 25", "un quart", "3 sur 8") et l'adaptateur en tire
  le dessin. C'est ce que la regle `ADR-0006` impose deja de fait, et c'est le chemin avec le moins
  de pieces mobiles. **Ce qui me ferait changer d'avis** : le jour ou deux matieres demanderaient
  deux dessins radicalement differents a partir de la meme donnee textuelle, la traduction
  donnee-vers-dessin cesserait d'etre derivable et il faudrait la nommer dans le paquet.

Ce que le visuel coute reellement, et qui n'est pas de l'architecture : **un garde-fou de rendu
n'existe pas dans ce depot**. `ADR-0025` l'a etabli, `nadia-prof` juge le rendu a l'oeil. Six lignes
de contenu visuel, ce sont six increments dont la moitie de la validation ne peut pas etre
automatisee. C'est le vrai prix, et il se paie a chaque paquet, pas une fois.

### Saisie - la plus chere en conception, la seule qui teste la promesse du produit

C'est une vraie mecanique : `Move` gagne une implementation qui porte une chaine, `GameSession` une
implementation, et surtout le **verdict cesse d'etre une egalite**. Un enfant qui ecrit "chevaux"
avec une majuscule, un accent oublie, un espace en trop a-t-il juste ? La reponse est
pedagogique ; la **place** de la reponse est architecturale, et c'est la que la promesse du produit
se joue vraiment :

- si la mecanique connait les regles d'orthographe acceptables, elle connait le francais, et
  `uneMecaniqueIgnoreLaMatiere` la refuse - c'est une des 28 regles, elle est deja ecrite ;
- si le paquet porte l'ensemble des formes acceptees et la mecanique se borne a une normalisation
  qu'elle possede (casse, espaces), le decouplage tient. C'est le chemin, et c'est aussi celui qui a
  le moins de pieces mobiles.

**Les deux lignes d'operation posee ne sont pas de la saisie**, et c'est la distinction que
`camille-po` m'a explicitement routee (`DEC-0030` section 4). Une multiplication posee ou une division
euclidienne, ce n'est pas un champ : c'est une grille de champs, des retenues, un ordre de
remplissage, et un verdict partiel case par case. **Oui, cela change le cout**, et cela split la
famille : trois lignes de saisie simple d'un cote, deux lignes d'operation posee de l'autre, qui sont
une **troisieme mecanique** et non une variante de la seconde. Je recommande de ne jamais les
compter ensemble dans un arbitrage de sequencement.

### Audio - pas cher a emettre, et le cout n'est pas la ou on le cherche

L'emission ne coute presque rien en architecture : une forme d'item qui nomme une ressource par une
chaine, un `<audio>` dans le gabarit, un fichier dans le fat jar. Aucun ilot JavaScript, aucun port
de domaine touche, `ADR-0006` deja satisfaite.

**Le cout est la production des sons, et ce n'est pas de l'architecture** : trois lignes de
conscience phonologique du CP demandent un corpus de syllabes et de phonemes francais, dit par une
voix humaine, embarque dans le jar. Ce n'est ni du code ni du contenu YAML : c'est un enregistrement.
**Aucun agent de cette squad ne peut le produire**, exactement comme aucun ne peut faire l'ecoute au
lecteur d'ecran de la reserve F11. C'est le meme genre d'obstacle, et il doit etre route de la meme
facon - a l'utilisatrice, avant et non apres l'ouverture de la ligne.

L'alternative d'une synthese vocale **cote serveur**, en Java, embarquee dans le fat jar, existe
comme famille de solutions ; je ne nomme aucune bibliotheque que je n'ai pas verifiee contre cette
pile. L'ordre de grandeur que je peux donner sans mentir : les voix de qualite se comptent en
dizaines a centaines de megaoctets, ce qui change la nature du livrable - un fat jar de quelques
mega devient un fat jar de plusieurs centaines. C'est une decision de produit et d'exploitation, pas
une decision d'architecture, et elle merite d'etre posee avant qu'un lot ne commence.

## Ce que le depot ne pourra pas faire, et ce n'est pas une question de sprints

**La reconnaissance vocale - "lire a voix haute un texte court avec fluidite" - est hors d'atteinte
de ce depot tel qu'il est defini.** Pas "chere". Hors d'atteinte, et je le dis maintenant plutot que
de laisser le compte de `camille-po` porter cette ligne comme un cout parmi d'autres.

Elle contredit **trois** contraintes fondatrices en meme temps, et aucune des trois ne se contourne
sans que `recreo` cesse d'etre `recreo` :

1. **le fat jar autonome, zero build Node** (`CLAUDE.md`). Une reconnaissance dans le navigateur
   passe par une interface que les navigateurs implementent en deleguant a un service distant du
   fournisseur - donc une dependance reseau et un service tiers, sur une application qui se lance par
   `java -jar` et n'appelle personne. Une reconnaissance hors ligne embarquee demande un modele
   acoustique de plusieurs centaines de megaoctets dans le jar ;
2. **le verdict reste serveur et testable** (`ADR-0008`). Le verdict de fluidite serait rendu par un
   moteur probabiliste, sur un signal audio, avec un taux d'erreur - et sur la voix d'un enfant de CP
   qui apprend a lire, c'est-a-dire le cas le plus difficile qui soit pour ce genre de moteur. Il n'y
   a pas de test de non-regression a ecrire sur un verdict de cette nature ;
3. **ce qui peut etre teste cote serveur doit l'etre** (dette 4). Le pilotage du micro vit
   necessairement dans le navigateur ; c'est le retour exact du comportement de jeu loge dans le
   JavaScript client, sur la ligne qui s'y prete le plus mal.

S'y ajoute une question qui n'est pas la mienne et que je remonte a `sofia-sm` plutot que de la
trancher : la voix d'un enfant est une donnee personnelle. La poser, c'est deja repondre.

**Ce qui me ferait changer d'avis** : rien de technique a court terme. Si l'utilisatrice acceptait un
jour que `recreo` cesse d'etre autonome hors ligne, la premiere contrainte tomberait - les deux
autres resteraient. Cette ligne ne devrait donc pas etre comptee dans une reserve de contenu ni dans
un horizon de sprints : elle devrait etre **nommee non couvrable en l'etat** dans
`MATRICE-couverture.md`, ce qui est une ecriture de `camille-po` et non de moi.

## Ce que je ne dis pas

- Je ne dis pas quelle famille ouvrir en premier. Le cout que je donne ne suffit pas a decider : il
  faut l'ordre pedagogique de `nadia-prof`, et c'est `camille-po` qui tranche.
- Je ne dis pas que la reserve de contenu seul tient quatre a six sprints. C'est l'estimation de
  `camille-po`, elle repose sur un rythme d'ouverture de lignes que je ne mesure pas.
- Je n'ai construit aucune de ces trois familles, meme en brouillon. Tout ce qui precede est mesure
  sur le depot tel qu'il est, par des sondes retirees et un arbre laisse propre.
