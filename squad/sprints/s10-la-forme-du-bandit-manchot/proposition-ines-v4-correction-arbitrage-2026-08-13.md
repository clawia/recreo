# Proposition ines-design v4 - correction bornee sur l'arbitrage du 2026-08-13

Repond au troisieme arbitrage de forme de `nadia-prof` (branche `s10-nadia-forme-2`, tete
`ca7d386`, `FICHE-bandit-manchot-additions-cp.md`, section du 2026-08-13). Sa forme du levier
(variante E) et ma methode sont acceptees ; **un seul defaut est refuse, arithmetique et non
plastique** - la platine deborde le budget de 20 px du flanc de 4 px. Deux conditions neuves,
**F16 (bloquante)** et **F18**, s'ajoutent, sur des points que je n'avais pas encore dessines. Je
corrige les trois et rien d'autre : je ne retouche ni le visage, ni le corps en deux tons, ni les
etoiles du socle, tous acquis.

## Lignee et methode

Worktree dedie `s10-ines-levier-final`, provisionne depuis `round-two`, lignee verifiee par
`bash outils/verifier-lignee.sh`. Aucune ligne de `recreo.css` ni de gabarit modifiee dans ce
depot - tout ce qui suit est mesure sur un candidat jetable, non commite.

**J'ai rendu, pas suppose** - la discipline que je tiens depuis la v2 et que `nadia-prof` s'est
faite a elle-meme le 2026-08-13, apres avoir mesure qu'une de ses propres lectures de silhouette
etait fausse. Fat jar reconstruit depuis ce
worktree, `JAVA_HOME` pose sur un JDK 21 le temps des commandes Maven (le `java` du `PATH` de ce
poste est un JRE 8), application lancee sur un port dedie. Les fragments mesures sont **reellement
servis** par les vraies routes HTTP :

- un ecran CM1 a **quatre colonnes** ("Le bon verbe", `jouait` / `jouions` / `jouais` /
  `jouaient`) - meme famille de mots courts que l'item que `nadia-prof` cite, memes quatre
  colonnes ;
- un ecran CP a **trois colonnes** ("Calcul CP", "2 + 1", propositions `1` / `4` / `3`).

Candidat construit en pointant les deux feuilles reellement servies par leur URL sur
l'application qui tourne (`vendor/bootstrap.min.css` puis `css/recreo.css`, meme ordre que la
production, `ADR-0014`), avec mon bloc candidat ajoute apres, jamais recopie d'une feuille
jetable - la meme precaution que `nadia-prof` a prise le 2026-08-13 contre le defaut de
fabrication qu'on lui avait signale. Le candidat inclut aussi le corps en deux tons
(`--recreo-cabinet-fond: #081c2c`, deja accepte) et le visage/enseigne acceptes, pour mesurer
l'etat complet plutot qu'un montage partiel qui aurait laisse passer un chevauchement invisible
sur mon propre poste.

**Geometrie relevee par difference de deux rendus** du meme ecran, avec et sans le levier
(equivalent direct de `outils/mesurer-rendu.sh`, ecrit en Python natif avec Pillow pour le
balayage pixel par pixel) - la methode que `nadia-prof` decrit elle-meme comme celle qui a trouve
les deux defauts de la v3, et qui est donc celle que j'applique pour verifier leur correction.
Chaque bbox de proposition est mesuree par sa couleur exacte sur le rendu **sans** levier, puis
chaque pixel change par l'ajout du levier est teste contre cette bbox - pas un rapport de
diametres, une comparaison de rendus.

**Une source de bruit nommee avant qu'elle ne soit prise pour un defaut du levier.** Une poignee
de pixels (4 sur l'ecran CM1, 8 sur l'ecran CP) changent aussi loin a gauche du levier (`x < 320`),
d'un ou deux niveaux de gris sur le contour anticrenele d'une lettre. Cause : `[data-arrivee]`
anime le rouleau sur 0,6 s, et les deux captures sont deux lancements de Chrome distincts, donc
deux instants de rendu legerement differents pendant que l'animation se termine. Rien a voir avec
le levier - je le montre en isolant les pixels changes du flanc droit (`x >= 320`) de ceux
d'ailleurs, et seuls les premiers sont attribues au levier ci-dessous.

## 1. Le levier - platine ramenee dans les 20 px (F13, F14, F15)

### Ce qui change, deux valeurs seulement

```css
.cabinet-levier {
    position: absolute;
    right: -1.25rem;   /* etait -1.125rem */
    width: 1.25rem;    /* etait 1.5rem */
    bottom: 0;
    height: 2.25rem;
}

.cabinet-levier-embase {
    position: absolute;
    left: 50%;
    bottom: 0;
    width: 1.25rem;    /* etait 1.5rem */
    height: 0.75rem;
    transform: translateX(-50%);
    background-color: var(--recreo-levier-teinte);
    border-radius: 0.125rem;
    box-shadow:
        inset 0 0.0625rem 0 rgba(255, 255, 255, 0.25),
        inset 0 -0.0625rem 0 rgba(0, 0, 0, 0.25);
}
```

`.cabinet-levier::before` (le fut, 0,4375rem) et `.cabinet-levier::after` (la boule, 1,125rem) ne
changent pas un seul chiffre - boule, fut, teinte et ancrage `bottom: 0` sont exactement ceux de la
v3, comme demande. Seules les deux valeurs ci-dessus, qui gouvernent la largeur totale de la
platine, passent de 24 a 20 px.

### Mesure, avant/apres, sur les deux ecrans

| | ecran CM1, 4 colonnes | ecran CP, 3 colonnes |
|---|---|---|
| pixels du levier qui tombent dans la bbox d'une proposition | **0** | **0** |
| pixels du levier qui tombent dans la bbox du corps du cabinet | **0** | **0** |
| bbox des pixels changes par le levier | `x = 340..359`, `y = 573..626` | `x = 340..359`, `y = 393..446` |
| bbox de la proposition la plus proche (rose / lavande) | `x = 188..339`, `y = 540..610` | `x = 244..339`, `y = 357..430` |
| ecart entre le levier et la proposition la plus proche | **1 px** (`339` -> `340`) | **1 px** (`339` -> `340`) |

Le levier commence exactement au pixel qui suit la derniere colonne, sur les deux largeurs de
cabinet, et n'entre jamais dans sa boite - **zero recouvrement, verifie et non suppose, sur les
deux ecrans**. F13 (budget de 20 px) et F14 (zero pixel sur le corps) sont tenues. F15 (aucun
appui intercepte) en decoule directement : un pixel qui ne fait jamais partie de la boite d'un
bouton ne peut pas absorber son appui.

**Regarde a x6** (captures jointes a ce document, non commitees) : la meme lecture qu'en v3 - une
boule sur un moignon, fixee sur une plaque a rebord visible, contre la plinthe. Retirer 4 px a la
plaque ne lui fait pas perdre son rebord ; elle reste nettement plus large que la boule. Confirme
sur les deux largeurs de cabinet, quatre colonnes et trois colonnes.

## 2. La marque "deja essayee" et ses fonds, dans le meme geste (F16, bloquante)

**Ce que je propose : une forme, pas seulement une couleur.** Une proposition deja essayee est
barree - le geste qu'un enfant de CP comme de CM1 connait deja de son cahier pour "ce n'est pas
la bonne", exactement le principe qui a fait choisir la coche et la croix du signal juste/faux
plutot qu'un disque et un triangle.

```css
.proposition.deja-essayee {
    text-decoration: line-through;
    text-decoration-color: var(--recreo-texte);
    text-decoration-thickness: 0.1875rem;
    text-decoration-skip-ink: none;
}
```

**Le fond n'est pas une cinquieme decision** : la couleur de la marque est `--recreo-texte`, celle
deja acceptee pour le texte des quatre boutons. Je ne choisis pas une nouvelle teinte a
verifier contre quatre fonds - je reprends celle deja mesuree contre eux dans l'arbitrage du
2026-08-13, avec la meme marge.

| Fond | Marque `--recreo-texte` (`#1c2733`) | Marque blanche (a eviter) |
|---|---|---|
| bleu `#bdd2ef` | **9,82:1** | 1,54:1 |
| sarcelle `#a9dcd8` | **10,03:1** | 1,51:1 |
| lavande `#dcc8ed` | **9,75:1** | 1,55:1 |
| rose `#eec1d8` | **9,55:1** | 1,58:1 |

Remesure par moi (methode WCAG explicite, luminances relatives) avant de m'appuyer dessus : les
quatre valeurs de la colonne `--recreo-texte` correspondent exactement a celles de la fiche du
2026-08-13. **Plancher F16 : 3:1. Marge la plus juste : 6,55, sur le rose.** La marque n'est
jamais blanche, elle ne peut donc jamais retomber au fantome mesure a 1,54:1.

**Rendu et regarde**, sur les quatre teintes en meme temps (capture jointe) : le mot barre se lit
sans ambiguite sur chacune des quatre, a taille reelle comme agrandi. Aucune couleur n'entre dans
la lecture du signe : une ligne sombre traverse le mot, visible independamment de la teinte de
fond, donc du daltonisme de l'enfant - le second canal qu'exige mon propre mandat est ici le
canal unique, puisque le signe n'est jamais porte par une teinte.

**Ce que je ne fais pas** : je ne dessine pas l'etat qui declenche cette classe (quel coup, quelle
proposition, combien de temps la marque reste). C'est un contenu de mecanique (M9), pas une forme
- `sofia-sm`/`marc-dev`/`lea-dev` cablent la classe `deja-essayee` sur le bouton concerne quand le
lot de mecanique de M9 est route ; la classe et son fond sont livres dans le meme geste que ce
document, comme la reserve K de `nadia-prof` le demandait.

## 3. La quatrieme teinte sort de la famille de l'ambre (F18)

**Je choisis le sarcelle pale `#a9dcd8` parmi les quatre candidats de `nadia-prof`**, a la place
de l'ambre `#e7cb7f` a la position `nth-child(4n+2)`.

```css
.propositions .proposition:nth-child(4n+1) { background-color: #bdd2ef; color: var(--recreo-texte); }
.propositions .proposition:nth-child(4n+2) { background-color: #a9dcd8; color: var(--recreo-texte); }
.propositions .proposition:nth-child(4n+3) { background-color: #dcc8ed; color: var(--recreo-texte); }
.propositions .proposition:nth-child(4n+4) { background-color: #eec1d8; color: var(--recreo-texte); }
```

Seule la deuxieme ligne change - une teinte remplace une autre, rien d'autre ne bouge.

**Pourquoi celle-la et pas les trois autres candidats, tous valides pour F9/F10.** Remesure par
moi avant de choisir (memes formules, memes references) :

| Candidat | vs `#2e7d32` | vs `#c62828` | vs corps | vs texte sombre |
|---|---|---|---|---|
| **sarcelle `#a9dcd8`** | 3,40:1 | 3,73:1 | 11,47:1 | 10,03:1 |
| pervenche `#c5cdf0` | 3,26:1 | 3,58:1 | 11,02:1 | 9,63:1 |
| turquoise `#aedde6` | 3,49:1 | 3,82:1 | 11,78:1 | 10,30:1 |
| gris-bleu `#c3cdd6` | 3,18:1 | 3,49:1 | 10,74:1 | 9,39:1 |

Les quatre satisfont F9/F10 ; ce n'est donc pas un critere de tri. Je retiens le sarcelle pour une
raison de forme, mon domaine : **le pervenche et le turquoise restent trop proches du bleu deja
present a la premiere position** (les trois sont dans la meme famille de bleus clairs et se
distinguent mal cote a cote sur un petit ecran), et **le gris-bleu est le plus desature des
quatre**, ce qui l'affaiblit visuellement a cote des trois autres teintes franches. Le sarcelle
occupe une position distincte sur la roue chromatique (bleu-vert), entre le bleu et le lavande, ce
qui donne aux quatre boutons un ecart de teinte perceptible un a un - une aide au reperage visuel,
jamais une information : la position reste le seul canal qui porte du sens (C5 clause 2, inchangee
par ce choix).

**Verifie a l'ecran, a cote de la boite d'aide**, sur l'etat que `nadia-prof` a rendu en premier
(capture jointe) : le sarcelle ne lit a aucun moment comme une nuance d'ambre, et la boite d'aide
(fond `#fff4e5`, bordure `#b45309`) reste le seul objet orange de l'ecran. F18 est tenue.

## Ce que je remonte

- **A `nadia-prof`** : la platine replacee (section 1), la marque et ses fonds ensemble
  (section 2, F16), et le choix du sarcelle (section 3, F18) - le jugement sur la lecture de
  chacune reste le sien, sur son propre rendu.
- **A `sofia-sm` / `marc-dev` / `lea-dev`** : `.cabinet-levier` et `.cabinet-levier-embase`
  gardent leurs deux seules valeurs modifiees (voir section 1), aucune autre regle de
  `SELECTEURS_DU_CABINET` n'a de raison de changer. La classe `.proposition.deja-essayee` est a
  cabler sur le bouton concerne des que le lot de mecanique M9 est route - forme et fond livres
  ici, comportement a coder ailleurs.
- **Reserve I de `nadia-prof`, rappelee et non levee** : le levier tient par sa forme (platine
  large, ancrage sur la plinthe), pas par sa teinte. Si une geometrie future le simplifie, la
  tension entre F1 (silhouette) et F2 (teinte discrete) revient intacte - je ne la resous pas ici,
  je la retrouve identique.

## Ce que je laisse ouvert

- **F11 et l'emplacement accessible de l'enseigne** restent hors de mon geste, bloques par une
  ecoute reelle de lecteur d'ecran que je n'ai pas faite - comme `nadia-prof` l'a ecrit trois fois
  de suite, je ne le redemontre pas ici.
- **Reserve J** (combien de sens l'application accroche-t-elle deja a ses couleurs, et lesquelles
  sont prises) : je retire l'ambre de sa position de decor, je ne tiens pas la liste complete des
  teintes deja chargees de sens que `nadia-prof` demande. Ce n'est pas mon geste de ce tour.
  `camille-po` en fait une story si elle le juge utile.
- **F17** (le mot d'une proposition reste >= 4,5:1 pendant le voile du rouleau) : je la remesure
  plutot que de la supposer tenue par ressemblance avec l'ancien ambre. Sous le voile noir a 20 %,
  le sarcelle tombe a **6,38:1** (contre 10,03:1 au repos, perte de 3,65) - une perte legerement
  **plus grande** que celle de l'ambre qu'il remplace (3,46, de 9,55:1 a 6,09:1), pas plus petite
  comme je l'avais d'abord ecrit sans calculer. Le resultat reste tres au-dessus du plancher de
  4,5:1, dans le meme ordre de grandeur que les trois autres teintes (6,09 a 6,38:1) : **F17 est
  tenue**, mais par la marge de la famille, pas par un gain que le sarcelle apporterait.
- **Le comportement de la marque M9** (quand elle apparait, sur quelles propositions, combien de
  temps) : forme et fond proposes ici, mecanique a coder par `marc-dev`/`lea-dev`/`sofia-sm`,
  Done pedagogique a `nadia-prof` une fois l'increment livre.

Je ne fusionne pas, je ne pousse pas.
