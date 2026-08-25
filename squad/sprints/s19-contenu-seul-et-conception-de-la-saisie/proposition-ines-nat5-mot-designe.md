# NAT-5 - proposition de renfort visuel du mot cible, et son cout mesure

- **Auteure** : ines-design. **Date** : 2026-08-25, hors sprint.
- **Sujet** : `NAT-5` (`squad/conformite/FICHE-bandit-manchot-nature-mot-cp.md`), routee par `DEC-0044`.
- **Statut** : proposition. Aucun Done pedagogique n'est prononce ici ; `nadia-prof` seule tranche.

## 1. Verification de ma lecture, au rendu servi plutot qu'a la source

Le jar reconstruit depuis ce worktree (`c48c0e6` + ce commit) a ete servi localement et interroge
par le protocole de deverminage, sur "Les synonymes" (`bandit-manchot-synonymes-cm1`), qui partage
le meme gabarit et la meme regle `.enonce` que "Nom ou verbe ?" :

```
outerHTML : <p class="enonce" tabindex="-1">Le village est très «&nbsp;calme&nbsp;» le dimanche
            matin.</p>
nbNoeuds  : 1 (un seul noeud, type 3 = noeud texte)
fontSize  : 32px   fontWeight : 700
.mot-designe existe : false
```

Ma lecture tient : `.enonce` est un unique noeud texte, sans aucune balise autour du mot cible.
Aucune regle CSS ne peut viser une sous-chaine d'un noeud texte - ce n'est pas une limite
d'aptitude, c'est ce que le rendu reel montre. Le renfort exige donc une balise autour du mot, donc
une donnee distincte du controleur, exactement ce que j'avais conclu depuis la source. Rien de ma
premiere lecture n'est infirme.

## 2. Proposition, rappelee

Une fois le mot cible expose separement (donnee routee a `sofia-sm`), le gabarit poserait un
`<span class="mot-designe">` autour du seul mot, guillemets et espaces conserves de part et
d'autre. Regle CSS proposee, generique (mecanique de marquage d'un mot dans un enonce, jamais une
competence) :

```css
.mot-designe {
    background-color: #fff59d;
    border-radius: 0.25rem;
    padding: 0 0.25rem;
    font-size: 1.15em;
}
```

Trois canaux non colores : une boite de fond (forme), un rembourrage, une police superieure au
reste de la phrase deja en gras a 2 rem - les guillemets francais restent en place, inchanges.

## 3. Methode de mesure, par injection, sans toucher a la production

`squad/outils/ines-design/mesurer-cout-mot-designe.py` (nouveau, meme protocole que
`squad/outils/nadia-prof/`) : navigateur reel pilote par CDP, viewport de reference `DEC-0040`
(744 x 551) et trois largeurs voisines deja mesurees par `nadia-prof` (768/767/744/720/640/576).
Pour chaque item d'un paquet servi : releve de `.enonce` et du bloc de verdict/indice APRES un
premier echec (etat le plus haut, comme `confirmer-largeur-dans-la-bande.py`), puis mutation DOM
seule (jamais `recreo.css` ni un gabarit) posant `.mot-designe`, puis nouveau releve sur le meme
DOM. Le delta est mesure, jamais suppose. Un second script, non verse ici (usage ponctuel),
injecte directement les seize phrases de `cp-nature-mot.yaml` dans le meme noeud, pour mesurer le
cout sur le propre contenu de ce jeu sans rouvrir `catalogue.yaml`.

Aucune ligne de `adapters/adapter-web` ni de `recreo.css` n'a change pour produire ces chiffres :
la regle et le marquage sont injectes par le navigateur, jamais ecrits au depot.

## 4. Chiffres

### 4.1 "Les synonymes" (`cm1-synonymes`, 16 items, couvert-valide)

| Largeur | Items a un nouveau repli sous le pli | Delta de hauteur d'enonce | Pire bas de verdict avec renfort |
|---|---|---|---|
| 744 px (reference) | 0 | +7,19 px (uniforme, 16/16) | 481,38 px (marge 69,62 px sous le pli) |
| 720 px | 0 | +7,19 px (uniforme) | 481,38 px |
| 640 px | 0 nouveau (5 deja sous le pli avant renfort, non bloquant, `S19-6a`/`SYN-16`) | +7,19 px (uniforme) | 601,38 px (contre 594,19 px sans renfort - le residu existant s'aggrave de 7,19 px, il n'en nait pas de nouveau) |
| 576 px | identique a 640 px | +7,19 px | 601,38 px |

### 4.2 "Les antonymes" (`cm1-antonymes`, 17 items, couvert-valide)

| Largeur | Items a un nouveau repli sous le pli | Pire delta de hauteur | Pire bas de verdict avec renfort |
|---|---|---|---|
| 744 px (reference) | 0 | +55,19 px sur *"Le film va « commencer » dans cinq minutes."* (bascule d'une a deux lignes) | 481,38 px (marge 69,62 px, l'item qui bascule ne bascule pas sous le pli) |
| 767 px | 0 | +55,19 px (meme item) | 481,38 px |
| 640 px | **1 nouveau** : *"Cette pièce est « claire » le matin."* passe de 516,19 px (au-dessus du pli) a 571,38 px (**sous le pli**) | +55,19 px | 571,38 px |

**C'est le seul cas ou ma proposition casse quelque chose de mesure, et je le nomme sans le
minimiser** : a 640 px, un item de "Les antonymes" - jeu deja `couvert-valide` - passe d'un
verdict visible a un verdict sous le pli, uniquement a cause de mon renfort. Cette largeur est
dans la bande 576-719 px deja connue fragile (`DEC-0043`, `S19-6a`/`SYN-16`, non bloquante et non
rouverte ici) ; mon renfort n'y invente pas la fragilite, mais il y ajoute un item concret qui
n'y etait pas avant.

### 4.3 Le propre contenu de "Nom ou verbe ?" (`cp-nature-mot`, injecte sur le meme vehicule)

| Largeur | Items qui basculent a deux lignes |
|---|---|
| 744 px (la reference que `nadia-prof` a utilisee pour son propre audit de ce jeu) | **0 sur 16** |
| 640 px et 576 px | **1 sur 16** : *"La « maîtresse » écrit au tableau."* (48 px -> 103,19 px) |

**A la reference exacte que `nadia-prof` a deja utilisee pour auditer ce jeu (744 x 551), le
renfort ne fait basculer aucune des seize phrases.** Le seul repli neuf, sur le contenu propre de
ce jeu, se produit a une largeur qu'elle n'a pas mesuree pour ce paquet.

## 5. General ou porte par le contenu - reponse mesuree

La regle `.mot-designe` reste **generique** : elle ne nomme aucune competence, elle s'applique a
la convention "un mot marque dans un enonce", deja partagee par trois paquets. Ce que je mesure ici
change ma reponse sur un point precis : **generique ne veut pas dire gratuit**. Applique tel quel
aux jeux deja valides, le renfort :

- ne coute rien de mesurable a la reference du produit (744 x 551, `DEC-0040`) ni a la bande
  corrigee (720 px et au-dela) - marge de 69,62 px partout ;
- coute un residu deja connu et non bloquant qui s'aggrave de 7,19 px sur "Les synonymes" dans la
  bande 576-719 px ;
- **cree un residu neuf, mesurable, sur "Les antonymes" a 640 px** - un item qui n'etait pas sous
  le pli le devient.

Je ne recommande donc pas d'appliquer ce renfort a "Les synonymes" et "Les antonymes" sans
passage devant `nadia-prof` au titre de leur propre Done pedagogique deja acquis - ce n'est pas
mon jugement a rendre, mais je ne peux plus ecrire que le cout est nul partout, je l'ai mesure
positif a un endroit precis. Pour "Nom ou verbe ?" lui-meme, actuellement hors catalogue et dont
le seul audit connu se tient a 744 x 551, le renfort ne casse rien a cette reference.

## 6. Ce qui me ferait changer d'avis

- Une regle plus legere (par exemple `font-size: 1.08em` sans agrandissement de rembourrage, ou un
  soulignement/encadre sans changement de taille) ferait probablement disparaitre le cas de bascule
  a deux lignes - a remesurer avec le meme script avant de le proposer comme remplacement, pas a
  supposer.
- Si `sofia-sm`/`theo-archi` decident que la bande 576-719 px reste hors du perimetre de tout
  Done tant que `S19-6a` n'est pas rouverte, le cout sur "Les antonymes" a 640 px devient une
  aggravation d'un residu deja assume plutot qu'une regression neuve a bloquer - c'est un
  arbitrage de perimetre, pas le mien.

## 7. Ce qui reste route, hors de ma main

- **`sofia-sm` / `theo-archi` / un developpeur** : la donnee manquante - le mot cible distinct de
  l'avant et de l'apres de l'enonce, portee par le controleur - est un prerequis technique avant
  toute mise en oeuvre reelle de `.mot-designe`. Je ne l'ecris pas moi-meme (aucune ligne de
  `PartieController` ni de domaine n'a change pour produire cette proposition).
- **`nadia-prof`** : juge si la forme (boite jaune, taille, guillemets conserves) satisfait
  `NAT-5` pour un enfant de CP, et si le cout mesure sur "Les synonymes"/"Les antonymes" est
  acceptable pour des jeux qu'elle a deja valides.

## Impact

- `squad/outils/ines-design/mesurer-cout-mot-designe.py` : cree.
- `squad/sprints/s19-contenu-seul-et-conception-de-la-saisie/proposition-ines-nat5-mot-designe.md`
  (ce fichier) : cree.
- Aucun fichier sous `adapters/`, `domain/` ni `application/` n'a change.
