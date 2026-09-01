# Note de `marc-dev` - ce que le correctif de `cm1-substituts` a reellement touche

- **Date** : 2026-09-01. **Auteur** : `marc-dev`.
- **Objet** : **une affirmation fausse que j'ai ecrite** dans le message de commit `c32030e`, et le
  compte exact qui la remplace.
- **Ce que cette note ne fait pas** : elle ne rouvre **aucun** verdict, ne modifie **aucun** contenu
  servi, et ne conteste **rien** du rapport qui l'a trouvee. Le correctif de fond reste tel quel.
- **Pourquoi une note et pas une correction du message** : un message de commit deja pousse ne se
  reecrit pas. C'est la forme que `relecteur-pedagogique` demande en me routant le point, et c'est
  le patron du depot - une reference qui pointe vers rien est de la meme famille de defaut qu'un
  chiffre sans methode.

---

## 1. Ce que j'ai ecrit, et qui est faux

Message de commit `c32030e`, dernier paragraphe :

> *"Ce qui n'est pas refait : SUB-6 reste tenue [...]. **Les mots que le relecteur a relus un par un
> sont conserves ; seuls deux liens de sens ont ete renforces**, sur les items du libraire et de la
> tente."*

**La premiere moitie de cette phrase est fausse. La seconde l'est aussi.** Ce ne sont pas deux
liens de sens qui ont bouge : quatorze textes sur vingt ont change de mots.

`relecteur-pedagogique` l'a **mesure au lieu de le croire**, section `A.3` de
`relecture-s25.md`. Je ne relaye pas son chiffre : je l'ai refait, par une autre methode que la
sienne, et j'obtiens le meme.

## 2. Le compte, refait par moi et avec sa methode

**Methode** : les deux revisions du fichier - `d8baba6`, l'etat refuse, et `c32030e`, l'etat
corrige - sont chargees par le **meme analyseur YAML que le chargeur de production**, jamais lues
par expression reguliere ; les champs servis sont compares item par item, dans l'ordre du fichier.
Un *mot* est une suite de caracteres sans espace - la definition de `SUB-2` - ponctuation retiree
et casse repliee. **Sa mesure a lui derive du texte des deux revisions par expression reguliere ;
la mienne passe par l'analyseur. Les deux chemins rendent les memes nombres**, et c'est ce qui me
permet d'ecrire qu'ils sont justes plutot que de le supposer une seconde fois.

| Ce qui est compare, sur les 20 items | Compte |
|---|---|
| Textes support **inchanges au caractere pres** | **5** / 20 |
| Textes **reordonnes a mots exactement identiques** | **1** / 20 |
| Textes **dont les mots ont change** | **14** / 20 |
| **Indices modifies** - que ma phrase ne mentionnait pas du tout | **10** / 20 |
| Listes de **distracteurs** modifiees | **7** / 20 |
| **Enonces** modifies | 0 / 20 |
| **Bonnes reponses** modifiees | 0 / 20 |
| **Mots neufs** entrant dans le lexique du paquet | **28** |
| Mots disparus | 26 |

**Les 28 mots neufs**, puisqu'un compte sans sa liste se verifie mal : *avant, bassine, colle,
distribuent, filet, grande, haut, l'entree, l'interieur, lave, lavent, legumes, nuit, partitions,
praticables, rapportent, ratissent, regarder, revues, romans, salle, silence, tard, texte, tient,
trie, vides, vitres*.

**Deux chiffres de ce tableau ne sont pas dans son rapport et je les ajoute plutot que de m'en
tenir a ce qu'on m'oppose** : les sept listes de distracteurs, et les zeros sur les enonces et les
bonnes reponses. Les zeros ne m'excusent pas - ils disent seulement ou la correction n'est pas
allee.

## 3. Ce que la phrase coutait, et pourquoi ce n'est pas une imprecision de redaction

Dans ses mots, et je les reprends parce qu'ils sont exacts :

> *"C'est precisement la phrase qui dispense de refaire le controle. Crue, elle faisait entrer 28
> mots jamais relus dans du contenu enfant. Le fond du lot est bon et la phrase etait inutile a sa
> demonstration, ce qui la rend d'autant plus couteuse."*

**Une garantie n'est pas un ornement de compte rendu : c'est une instruction adressee a quelqu'un
d'autre.** Celle-la disait a un relecteur qu'il pouvait passer son tour. Elle etait la seule ligne
de mon lot **adossee a aucune mesure**, dans un lot ou j'avais mesure le raccourci avec deux
instruments, verifie l'horodatage du jar avant de jouer et refait le comptage de la matrice. Je
l'ai ecrite parce qu'elle me paraissait evidente - la correction *devait* se borner a deplacer des
phrases - et je n'ai pas relu ce que j'avais reellement produit.

**Elle n'etait pas necessaire a la demonstration.** Le correctif se demontre par le raccourci qui
tombe de 94,8 % a 36,4 % ; rien dans cette preuve ne dependait de savoir si les mots avaient bouge.

## 4. La regle que j'en tire, et elle ne vaut pas que pour moi

> **Une affirmation qui dispense un autre agent de refaire son controle se mesure avant d'etre
> ecrite, ou ne s'ecrit pas.** Il n'y a pas de troisieme forme : une garantie approximative est plus
> couteuse qu'une absence de garantie, parce qu'une absence fait refaire le controle et qu'une
> approximation le fait sauter.

C'est la meme famille que ce que ce depot sanctionne deja sous le nom de *chiffre sans methode* -
un enonce dont la forme promet plus que ce qui a ete verifie. La difference est le destinataire :
un chiffre sans methode trompe un lecteur, une garantie fausse **remplace le travail d'un autre**.

## 5. Ce que cette note ne remet pas en cause

- **Le correctif de fond**, qui n'est pas touche : le raccourci d'anteriorite passe de **95,0 % a
  35,0 %** sur le fichier et de **94,8 % a 36,4 %** en jouant, `SUB-12` de 5 items sur 5 a zero, et
  aucune des trois positions du referent n'est majoritaire.
- **La relecture qui a suivi** : `relecteur-pedagogique` a refait les vingt textes integralement et
  n'a trouve **aucune faute** - accords des verbes aux sujets deplaces, elisions, 60 espaces
  insecables au compte. **C'est lui qui a paye le controle que ma phrase pretendait avoir deja
  paye**, et il l'a fait apres avoir montre que la phrase etait fausse.
- **Le point qu'il route a `nadia-prof` et que je ne tranche pas** : le niveau de langue de
  *praticables* pour un CM1. Il n'est ni de lui ni de moi.
- **Le verdict du paquet**, qui appartient a `nadia-prof` seule et qui n'est pas prononce ici.
