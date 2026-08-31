# Fiche de conformite - `bandit-manchot-substituts-cm1`

- **Jeu** : `bandit-manchot-substituts-cm1`, paquet `cm1-substituts`, 20 items a texte support.
- **Competence visee** : Francais CM1, "Lecture et comprehension de l'ecrit" - *"Reperer les
  substituts d'un personnage (pronoms, groupes nominaux) dans un texte"*.
- **Conditions opposables** : `SUB-1` a `SUB-10` (2026-08-30), `NAR-11` (2026-08-31).
- **Auteure du verdict** : `nadia-prof`. **Un verdict n'est jamais efface.**

---

## Verdict du 2026-08-31 (s25)

# Verdict : **REFUSE**

**Motif, en une phrase** : **199 manches sur 210 reellement jouees se gagnent du premier coup en
appliquant une seule regle de surface - choisir la proposition qui contient le premier prenom du
texte - sans jamais lire la phrase ou vit le substitut**, et ce raccourci est exactement la
representation fausse que cette competence existe pour remplacer.

**La ligne de matrice reste `non couvert`.** `camille-po` en fait du backlog ; **ce refus ne bloque
pas le sprint suivant.**

---

### 1. Ce que j'ai fait, avec sa methode et son perimetre

**J'ai joue.** 25 parties ouvertes sur le jar construit depuis `s25-sofia-review`, **210 manches**,
en n'appliquant qu'une seule regle : *choisir la proposition qui contient le premier prenom du texte
support*. Jamais la phrase du substitut, jamais un accord, jamais un raisonnement de reference.
**Les 20 textes supports du paquet ont ete rencontres**, donc la population est couverte en entier
et non echantillonnee.

> **Resultat : 199 reussites du premier coup sur 210 manches, soit 94,8 pour cent.**
> **Le hasard a trois propositions vaut 33,3 pour cent.**
> **Les onze echecs sont onze fois le meme item** - le seul du paquet ou le referent n'est pas le
> premier personnage nomme.

**Verification independante sur le fichier**, faite avant de jouer et qui donne le meme fait :
**19 items sur 20** se gagnent par cette regle. *Methode : pour chaque item, le premier prenom du
`texteSupport` dans l'ordre du texte, puis la proposition qui le contient ; l'item est compte gagne
si cette proposition est exactement la bonne reponse.*

**Et j'ai relu les vingt items un par un** - enonce, texte support, bonne reponse, deux distracteurs,
indice. **Aucun echantillon**, comme `SUB-6` l'exige et comme `C6` m'y oblige.

### 2. Ce que je dis d'abord, parce que ce serait injuste de le dire apres : le paquet est bien fait

**`SUB-1` a `SUB-10` sont tenues, toutes les dix, et je les ai verifiees une par une.**

| Condition | Verdict | Ce que j'ai verifie |
|---|---|---|
| **`SUB-1`** forme | **TENUE, 20/20** | `texteSupport` porte le texte, l'enonce cite le substitut entre guillemets, les trois propositions sont presentes dans le texte de leur propre item |
| **`SUB-2`** longueur | **TENUE, 20/20** | Textes de **19 a 29 mots** contre un plafond de 45, **jamais plus de trois phrases**, enonces a 10 mots contre un plafond de 12 |
| **`SUB-3`** les deux familles servies | **TENUE** | **11 pronoms et 9 groupes nominaux**, plancher a 6 pour chacun |
| **`SUB-4`** le genre et le nombre ne decident jamais seuls | **TENUE, 20/20**, verifiee a la main | Sur chaque item, au moins deux des trois propositions s'accordent avec le substitut. Les quatre items ou une seule proposition discorde restent au-dessus du plancher |
| **`SUB-5`** la proximite ne decide pas toujours | **TENUE tres largement : 18 items sur 20**, plancher a 6 | Verifiee a la main, item par item : le referent n'est presque jamais le groupe compatible le plus proche a gauche |
| **`SUB-6`** texte masque, la question ne se repond pas | **TENUE, 20/20**, verifiee a la main | Texte masque, aucun item ne se repond : il reste au moins deux candidats indiscernables. C'est la propriete de type `COMP-2` du sprint et elle est honoree |
| **`SUB-7`** un seul substitut, une seule occurrence | **TENUE, 20/20** | Verifie sans tenir compte de la casse |
| **`SUB-8`** personnages | **TENUE** | **10 prenoms feminins distincts et 9 masculins**, ecart de 1, plafond a 1. Aucun role, metier ou activite distribue par genre : les gardiens de but, les cuisiniers, les scientifiques et les libraires sont des deux genres |
| **`SUB-9`** indice | **TENUE, 20/20** | *"Relis la premiere phrase du texte."*, *"Relis la phrase du milieu."* Cinq a six mots, jamais le referent |
| **`SUB-10`** au moins vingt items, textes tous distincts | **TENUE** | 20 items, 20 textes distincts |

**Et `SUB-5` merite d'etre saluee plutot que subie : 18 items sur 20 defont le raccourci de
proximite, alors que j'en demandais 6.** L'auteur a travaille bien au-dela de ma condition.

**Je le dis parce que c'est la clef de ce refus** : ce n'est pas un paquet baclé. **C'est un paquet
ou la fermeture tres soignee d'un raccourci en a ouvert un autre**, et c'est un enchainement que
personne ne pouvait voir sans compter.

### 3. Le defaut, et pourquoi il est de fond

**Le mecanisme.** `SUB-5` demande que le referent ne soit pas le groupe compatible le plus proche a
gauche. La facon la plus simple de la tenir est de placer le referent **loin**, donc **au debut**.
Dix-neuf items sur vingt, le referent est le **premier personnage nomme**. **Fermer le raccourci de
proximite a ouvert le raccourci d'anteriorite.**

**Pourquoi c'est grave, et ce n'est pas une question de pourcentage.** Le raccourci qui gagne n'est
pas quelconque : *"l'histoire parle de celui qu'on nomme en premier"* est **precisement la strategie
du lecteur qui ne sait pas encore suivre une chaine de reference**. C'est la representation que cette
ligne du programme existe pour remplacer. **Ce paquet la recompense dix-neuf fois sur vingt.**

Un enfant peut donc terminer ce jeu avec un score parfait **sans avoir jamais fait ce que la
competence nomme**, et en sortir conforte dans l'erreur qu'il fallait corriger. **Je prefere une case
vide a une case fausse**, et une case verte qui recompense la mauvaise strategie est pire qu'une case
vide.

**Une seconde marque de surface, plus petite et de la meme famille.** Sur les **5 items dont la
reponse est un couple** - *"Theo et Adam"*, *"Jade et Ines"*, *"Rachid et Ilan"*, *"Manon et Lila"*,
*"Chloe et Jade"* - les deux distracteurs sont des groupes en *"les ..."*. **La regle "prendre celle
qui contient et" gagne 5 fois sur 5**, et elle n'a pas besoin du texte non plus.

### 4. Ce qui joue **contre** mon refus, et que je verse moi-meme

**Je dois l'ecrire : un refus qui cache ce qui l'affaiblit n'est pas un jugement, c'est une
plaidoirie.**

**`NAR-11`, prise a la lettre, ne mord pas sur ce paquet.** Elle dit : *"je lis a l'audit les items
qu'une strategie **a phrase fixe** gagne"*. J'ai mesure les trois strategies a phrase fixe, avec la
metrique de gains decisifs que j'avais employee sur `NAR` a s24 :

| Strategie a phrase fixe | Gains decisifs |
|---|---|
| la reponse est dans la **premiere** phrase | **7 / 20 = 35,0 %** |
| la reponse est dans la **deuxieme** phrase | 5 / 20 = 25,0 % |
| la reponse est dans la **troisieme** phrase | 0 / 20 = 0 % |

**35,0 pour cent contre 33,3 pour cent de hasard : `NAR-11` passe.** Si je m'en tenais a la seule
condition chiffree que j'ai ecrite hier, **ce paquet serait valide**.

**Et aucune des dix conditions `SUB-n` ne porte cette propriete.** L'auteur a satisfait tout ce que je
lui avais demande, et deux conditions bien au-dela.

### 5. Pourquoi je refuse quand meme, et ou est la barre que j'invoque

**La barre n'est pas neuve et elle n'est pas posee apres coup. Elle est datee du 2026-08-31, avant que
ce paquet ne soit ecrit**, dans la liste des proprietes dont je me declare seule garde
(`squad/conformite/SUITES-s24-apres-la-livraison-des-quatre-lots-2026-08-31.md`, section 7) :

> *"La propriete generale que `ADR-0072` etablit comme non formulable : **une bonne reponse ne doit
> pas etre reconnaissable a une marque de surface que ses distracteurs ne portent pas**. Je suis la
> seule garde de la famille entiere ; le garde-fou neuf n'en tient qu'un membre, celui du decoupage
> en phrases."*

**Etre le premier prenom du texte est une marque de surface que la bonne reponse porte et que ses
distracteurs ne portent pas, sur 19 items sur 20.** C'est la famille entiere, pas le membre que
`NAR-11` chiffre. **J'avais ecrit que le garde-fou n'en tenait qu'un membre et que je gardais le
reste ; c'est ce reste-la qui se presente aujourd'hui.**

**Le test que je m'applique pour savoir si je deplace un poteau** : la propriete etait-elle ecrite,
datee et lisible avant que le paquet ne soit ecrit ? **Oui.** Etait-elle formulee en des termes qu'un
tiers pouvait lire ? **Oui, et je l'avais nommee comme irreductible a un automate.** Ce qui manquait,
c'est un **chiffre**, et `NAR-11` disait explicitement pourquoi je n'en posais pas : *"je n'ai pas de
mesure qui en fonde un, et j'ai assez paye deux listes fermees cette semaine pour ne pas inventer un
troisieme nombre."*

**Ce refus est donc un jugement, pas un depassement de seuil, et je l'assume comme tel.** Je ne
compare pas 94,8 a une barre : je constate qu'un enfant gagne ce jeu sans faire ce que la ligne
demande.

**Il est contestable, et je dis comment.** Si `camille-po` ou `sofia-sm` jugent que j'oppose ici une
exigence que le lot ne pouvait pas connaitre, **qu'elles me l'ecrivent et je repondrai par ecrit**. Ce
que je ne ferai pas, c'est valider en sachant ce que je viens de mesurer.

*Et pour qu'on puisse verifier que je m'applique la meme regle dans les deux sens : le meme jour, sur
`cp-figures-planes`, j'ai trouve une marque de surface comparable - l'indice designe la reponse sur 8
items sur 12 - et **je n'ai pas refuse**, precisement parce qu'aucune de mes conditions ne gouvernait
l'indice d'un paquet illustre. La difference entre les deux cas est ecrite en section 11 de la fiche
de ce jeu-la.*

### 6. Ce qui corrigerait, et c'est borne

**Le paquet contient deja son propre modele : l'item 6** - *"Lila et Jade apportent un roman policier
au club de lecture. Manon s'installe pres de la fenetre. Elle ouvre le livre a la premiere page."* Le
referent y est le **deuxieme** personnage nomme, et c'est le seul item que le raccourci perd - les
onze echecs de mes 210 manches sont onze fois celui-la.

> **`SUB-11`, neuve, datee du 2026-08-31** - Sur un paquet de substituts, **le referent n'est pas le
> premier personnage nomme du texte sur au moins la moitie des items**. *Unite de compte : l'item ;
> "premier personnage nomme" se lit dans l'ordre du texte.*
>
> **`SUB-12`, neuve, meme date** - **Aucune propriete de forme ne distingue la bonne reponse de ses
> distracteurs** : si la bonne reponse est un couple *"X et Y"*, au moins un distracteur en est un
> aussi.

**Les deux sont calculables** - c'est `theo-archi` et `yanis-qa` qui diront si elles valent un
garde-fou, et je recommande que **`SUB-11`** en devienne un : c'est elle qui compte, et elle vient de
couter un sprint.

**Ce que je n'exige pas** : ni de reecrire les vingt items, ni de toucher a `SUB-5`, qui est bien
tenue. **Deplacer le referent sur une dizaine d'items suffit**, et les textes existants s'y pretent -
il suffit le plus souvent d'echanger deux phrases.

### 7. Ce que le paquet garde d'acquis

**`SUB-6` a ete portee jusqu'au bout sur les vingt items et elle est tenue.** C'est la propriete de
type `COMP-2` du sprint, celle qui coute le plus cher. Elle **ne depend pas** du rang du referent dans
le texte : un lot correctif devra la refaire verifier, mais il partira d'un paquet qui l'a deja tenue
une fois, et **le travail de fond n'est pas perdu**.

**De meme pour `SUB-2`, `SUB-3`, `SUB-7`, `SUB-8`, `SUB-9`, `SUB-10`** : rien de ce que je refuse ne
les touche.

**Et je note ce que le `relecteur-pedagogique` a fait sur ce paquet** : il a relu les vingt textes
support un par un sur les homographes que l'automate laisse a la main. Ce travail-la ne sera pas a
refaire non plus sur les textes qui ne bougeront pas.

### 8. Sur quelles proprietes je reste la seule garde sur ce jeu

- **La famille entiere de la marque de surface** - dont ce refus est le premier cas reel. Aucun
  automate ne la tient, et `ADR-0072` a etabli qu'elle n'est pas formulable en general.
- **`SUB-4`, `SUB-5`, `SUB-6`** - portees a la main, item par item, comme annonce a s23.
- **Qu'un raccourci mesure soit un raccourci qu'un enfant du niveau vise prendrait reellement**
  (`NAR-11`). **Ici il le prendrait** : c'est la strategie du lecteur qui ne sait pas encore suivre
  une reference, et c'est la plus repandue au CM1.

### 9. Ce que ce verdict ne fait pas

- Il **ne bloque pas le sprint suivant** : il renvoie l'increment a `camille-po`, qui en fait du
  backlog.
- Il **ne retire rien** aux trois autres paquets de s25, qui ont leurs propres fiches et leurs propres
  verdicts, dont deux Done accordes et un troisieme.
- Il **ne reproche rien a l'auteur** : les dix conditions que je lui avais donnees sont tenues, et deux
  d'entre elles largement au-dela de ce que je demandais.
- Il **n'est pas fonde sur la CI**, qui est verte a 1 006 tests, et **c'est precisement le genre de
  defaut qu'une CI verte ne voit pas**.
- Il **ne modifie aucun fichier** de code ni de contenu.
