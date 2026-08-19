# Proposition - style du texte-support de S17-2 (COMP-5)

- **Auteur** : ines-design.
- **Repond a** : `COMP-5` (`squad/conformite/ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md`,
  section 1.4), `S17-2` du backlog, section 2 de `DEC-0039-perimetre-sprint-17-francais-cm1-et-dette-de-len-tete.md`.
- **Ce que ce document fait** : il propose une regle de style pour un champ, sur le meme mecanisme
  qu'un gabarit generique de question a choix multiple - jamais pour "les questions de comprehension"
  au sens du contenu qu'elles porteront. Aucun code de production n'est modifie par ce lot. `theo-archi`
  garde seul l'avis de faisabilite et le mecanisme (l'ajout d'un champ au gabarit) ; `nadia-prof` garde
  seule le jugement de lisibilite, avant le premier des vingt items de `S17-2`.
- **Ce que je ne propose pas** : un contenu, une longueur de texte en caracteres (`DEC-0016` l'interdit
  nommement), ni le mecanisme qui fait porter ce champ par le controleur - je nomme ce dernier point
  comme une question ouverte a `theo-archi` en section 6.

## 0. Le budget dont je pars, et ce qu'il mesure exactement

`nadia-prof` a mesure deux points, jamais un intervalle continu (section 0 de son arbitrage) :

| Style du bloc substitue | Lignes | Hauteur de texte | Tient au-dessus du pli |
|---|---|---|---|
| style de `.enonce` actuel (2rem, gras, interligne 48 px) | 5 | 240 px | oui, 27 px de marge |
| taille de `.consigne` (1,25rem, interligne 30 px) | 8 (extrapole a la meme hauteur, 8x30=240 px) | 240 px | oui, meme marge : 27 px |

**Le protocole qu'elle decrit** : une manche reelle de "Calcul CP" (consigne courte reelle, cabinet
reel a trois colonnes), avec un **seul** bloc de texte substitue a la place de l'enonce. Le chiffre de
240 px est donc un budget pour **un seul bloc**, dans une page qui porte deja une consigne et un
cabinet, mais **aucune ligne de question supplementaire** en dessous de ce bloc et au-dessus du
cabinet. Je le garde tel quel comme le seul chiffre dont je dispose ; je reviens sur ce que cela ne
couvre pas en section 6, parce que ce n'est pas un point de style et que je ne veux pas le taire.

## 1. Le style propose - une classe, `.texte-support`

Je reprends le nom deja utilise par l'arbitrage de `nadia-prof` et par la story elle-meme
("texte-support") plutot que d'en inventer un, pour ne pas faire coexister deux noms pour la meme
chose dans le depot.

```css
.texte-support {
    font-size: 1.25rem;
    font-weight: normal;
    margin-bottom: 0.25rem;
}
```

- **Taille : 1,25rem**, la valeur exacte de `.consigne`, celle que `nadia-prof` a mesuree comme
  suffisante (8 lignes/240 px). Je ne propose aucune taille intermediaire entre celle-ci et celle de
  `.enonce` (2rem) : aucune n'a ete mesuree, et j'ecris en section 6 pourquoi je ne peux pas en
  mesurer une moi-meme sur ce poste.
- **Graisse : normale**, jamais grasse. C'est la premiere des deux marques qui distingue ce champ de
  `.enonce` (qui reste gras, 2rem) - la seconde est la taille elle-meme. Un texte a lire sur plusieurs
  lignes se lit plus mal en gras qu'un texte court a reperer d'un coup d'oeil ; aucune classe de ce
  depot ne met du texte long en gras aujourd'hui (`.enonce`, seul texte gras de cette famille, ne
  depasse jamais une ou deux lignes courtes dans les jeux existants).
- **Couleur : aucune declaration**, donc heritee de `body` (`var(--recreo-texte)`, `#1c2733`) - la
  meme couleur que `.consigne`, `.enonce`, `.indice`, `.explication` et tout le texte courant de
  l'application. Aucune paire neuve a mesurer : c'est la paire deja en usage partout ailleurs sur fond
  de page (section 3).
- **Interligne : aucune declaration.** Je le dis explicitement parce que c'est le point le plus
  fragile de cette proposition : ce depot ne declare `line-height` nulle part, la valeur qui s'applique
  est celle de Reboot (1,5), et c'est exactement cette valeur-la que la mesure de `nadia-prof` a
  utilisee pour son second point (1,25rem x 1,5 = 30 px, le chiffre qu'elle cite au mot pres). Ecrire
  une valeur d'interligne ici, meme identique, romprait le lien entre "ce que je propose" et "ce
  qu'elle a mesure" - une regle explicite ecraserait silencieusement une future montee de version de
  Reboot que la mesure actuelle ne peut pas anticiper, la ou l'absence de regle continue de suivre
  la meme valeur que le reste du depot.
- **Aucune largeur, aucun alignement, aucun style italique ni souligne.** Bloc de niveau `p`, pleine
  largeur du conteneur, comme `.consigne` et `.enonce`. Une police italique est une piste que j'ai
  ecartee (section 4) : elle ne coute rien au budget vertical mais elle introduit un signe qu'aucune
  autre classe de ce depot ne porte, sur un texte que l'enfant doit lire integralement plutot que
  reperer - je ne la retiens pas dans le vocabulaire que je propose.
- **Repli (mode de defaillance) : le passage a la ligne par defaut du navigateur, jamais une
  troncature.** Je ne declare ni `white-space: nowrap`, ni `overflow: hidden`, ni `text-overflow:
  ellipsis` - c'est l'absence de ces trois proprietes qui est la regle gardable ici (`ADR-0028`,
  meme forme que `.consigne` et `.enonce`, qui ne declarent rien de plus sur ce point et se replient
  deja sur plusieurs lignes dans les paquets existants).

**Disposition dans le gabarit** (`fragments/manche.html`) : entre `.consigne` et `.enonce`, dans cet
ordre - `.consigne` (la phrase fixe du jeu, par exemple "Lis le texte, puis reponds a la question"),
`.texte-support` (le texte narratif, par item), `.enonce` (la question, par item, style inchange),
puis le cabinet des propositions. C'est l'ordre de lecture naturel : consigne, texte, question,
reponses. Je ne modifie pas le gabarit moi-meme - l'ajout d'un champ dans `fragments/manche.html` et
sa provenance cote controleur sont du mecanisme, route a `theo-archi` en section 6.

## 2. Contraste - formule et valeurs, pas seulement le verdict

Formule WCAG (luminance relative sur canaux lineaires, contraste = `(L1 + 0,05) / (L2 + 0,05)`, `L1`
la plus claire des deux) :

```
pour chaque canal c (0-255) : c_srgb = c/255
si c_srgb <= 0,03928 : c_lin = c_srgb / 12,92
sinon : c_lin = ((c_srgb + 0,055) / 1,055) ^ 2,4
L = 0,2126*R_lin + 0,7152*G_lin + 0,0722*B_lin
```

**Variante recommandee (texte nu, section 3) : `#1c2733` sur `#f4f7fb`.**

- `#1c2733` -> R=28, G=39, B=51 -> R_lin=0,011614, G_lin=0,020285, B_lin=0,033096 -> L=0,019368
- `#f4f7fb` -> R=244, G=247, B=251 -> R_lin=0,903620, G_lin=0,929770, B_lin=0,964860 -> L=0,926760
- Contraste = (0,926760 + 0,05) / (0,019368 + 0,05) = 0,976760 / 0,069368 = **14,08:1**

Cette meme paire est deja mesuree ailleurs dans le depot a **14,09:1**
(`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, ligne 2049, "texte de la question `#1c2733`
contre la page") : mon calcul independant la reproduit au centieme pres, l'ecart tenant a
l'arrondi des etapes intermediaires. Seuil pertinent : WCAG 2.1, critere 1.4.3, texte normal (le
texte-support n'est ni gras ni a 24px ou plus, donc pas "texte large" au sens du critere), plancher
**4,5:1**. **14,08:1 est plus de trois fois le plancher.**

**Variante alternative (encadree, section 4) : `#1c2733` sur `#eaf1f7`** (le fond de
`.explication`/`.indice`-like deja nomme `--recreo-explication-fond`) :

- `#eaf1f7` -> R=234, G=241, B=247 -> R_lin=0,822610, G_lin=0,879950, B_lin=0,929770 -> L=0,871320
- Contraste = (0,871320 + 0,05) / (0,019368 + 0,05) = 0,921320 / 0,069368 = **13,28:1**

Les deux variantes tiennent tres largement le plancher de texte ; le contraste n'est **pas** le
critere qui les depuis distingue - c'est le cout en pixels verticaux, section 4.

## 3. Ce que j'ai deja sous la main, et pourquoi je ne le reprends pas telle quelle

Le style de `.explication`/`.indice` (fond teinte, bordure, rembourrage 0,5rem 0,75rem) est le seul
"encadrement" deja dans le vocabulaire de ce depot pour un bloc de texte secondaire. Je le cite comme
piste (variante alternative ci-dessous) plutot que de l'ecarter sans le nommer, parce que la consigne
de `sofia-sm` demande de proposer dans le vocabulaire deja en place.

## 4. Deux variantes, chiffrees, et ce qui les separe

### Variante recommandee - texte nu, sans cadre

`.texte-support` telle qu'ecrite en section 1 : aucun fond, aucune bordure, aucun rembourrage propre
au-dela de `margin-bottom: 0,25rem` (la meme valeur que `.consigne` et `.enonce`). **Cout vertical :
nul au-dela du texte lui-meme** - c'est exactement le bloc que `nadia-prof` a mesure (240 px pour 8
lignes, 27 px de marge avant le pli).

### Variante alternative - encadree, motif `.explication`/`.indice`

```css
.texte-support {
    font-size: 1.25rem;
    font-weight: normal;
    background-color: var(--recreo-explication-fond);
    border: 0.125rem solid var(--recreo-explication-bordure);
    border-radius: 0.25rem;
    padding: 0.5rem 0.75rem;
    margin-bottom: 0.25rem;
}
```

Meme taille, meme graisse, meme contraste de texte (13,28:1, section 3). **Cout vertical mesurable :
rembourrage 0,5rem en haut et en bas (2 x 8 px = 16 px) plus bordure 0,125rem en haut et en bas
(2 x 2 px = 4 px), soit 20 px de plus que la variante nue, pour la meme hauteur de texte.** Sur le
seul point de donnees mesure (8 lignes, 240 px, marge de 27 px), cela laisse 7 px de marge avant le
pli - une marge reelle, mais neuf fois plus etroite, et qui ne couvre a elle seule aucun autre cout
(section 6).

**Ce que l'encadrement apporterait** : une frontiere visuelle nette entre "le texte a lire" et "la
question qui suit", sur un motif deja connu du depot (le meme traitement que `.indice`/`.explication`
marque deja "ceci est un bloc a part"). **Ce qu'il coute** : la quasi-totalite de la marge mesuree, au
moment precis ou la section 6 montre qu'un autre cout, non mesure, reste probablement du.

## 5. Ma recommandation

**La variante nue.** Elle est la seule dont le cout vertical est nul au-dela du texte lui-meme, donc
la seule qui garde intacte la marge de securite que `nadia-prof` a mesuree (27 px) au moment ou une
question distincte doit encore s'ajouter en dessous (section 6) - un cout que je ne peux pas encore
chiffrer. Sa distinction avec `.enonce` repose sur deux signaux deja mesurables (taille, graisse) et
un troisieme qui ne coute rien (l'ordre de lecture, texte-support avant la question) : je n'ai pas
besoin d'un quatrieme signal (cadre) pour satisfaire `COMP-5`, qui demande "un style distinct, plus
petit", pas un encadrement.

## 6. Ce qui reste ouvert, et ce n'est pas un point de style

**Le budget de 240 px que je reprends a ete mesure sur un seul bloc de texte, dans une mise en page
qui ne portait aucune ligne de question separee en dessous de lui** (section 0). La story `S17-2`
telle que je la lis (`COMP-4` : "on mesure le texte-support, la question et les propositions")
demande **trois blocs distincts** - `.consigne`, `.texte-support`, `.enonce` (la question) -
au-dessus du cabinet, la ou la mesure de `nadia-prof` n'en portait que deux (`.consigne` et le bloc
substitue). **Je ne sais pas dire, sans navigateur, si l'ajout de la question (`.enonce`, 2rem, gras,
une ou deux lignes a 48 px chacune) tient encore dans les 27 px de marge mesures au point le plus
charge (8 lignes de texte-support).** Ce n'est pas une question de style - la taille et la graisse de
la question ne changent pas dans cette proposition - c'est une question de **mecanisme** (un champ de
plus dans le gabarit) et de **mesure reelle apres assemblage**, que je route :

- a `theo-archi`, pour confirmer que `fragments/manche.html` peut recevoir ce champ supplementaire
  sans casser une regle existante (`ADR-0028`, `ADR-0030`) ;
- a `nadia-prof`, pour la mesure reelle exigee par `COMP-4` sur les vingt items assembles - je
  recommande, sans le trancher, que le premier item ecrit sur cette base soit mesure avant les dix-neuf
  autres, precisement parce que ce cout composé n'a jamais ete mesure.

Je ne fixe pas de plafond de mots par prudence a moi (`DEC-0016` l'interdit), mais je nomme le risque :
si le premier item mesure a 8 lignes de texte-support echoue une fois la question ajoutee, la marge
disponible pour les textes futurs est plus etroite que les 45 mots cites comme indicatifs.

## 7. Ce que je ne peux pas verifier moi-meme

Je n'ai pas de navigateur sur ce poste (comme `theo-archi` sur ce meme sprint) : les deux contrastes
de la section 3 sont calcules a la formule sur des valeurs hexadecimales deja declarees dans
`recreo.css`, jamais mesures sur un rendu. Je n'ai mesure aucun pixel de rendu compose
(texte-support + question reelle + cabinet) : c'est precisement le point ouvert de la section 6.

## Mes questions a `nadia-prof`

1. La variante nue (1,25rem, graisse normale, sans cadre) satisfait-elle "un style distinct, plus
   petit" au sens de `COMP-5`, ou attendez-vous un signal visuel supplementaire au-dela de la taille
   et de la graisse ?
2. Souhaitez-vous mesurer le premier item assemble (texte-support + question + propositions) avant
   les dix-neuf autres, comme je le recommande en section 6, plutot que d'attendre le paquet complet ?
3. Si le premier item mesure montre que la marge restante apres l'ajout de la question est plus
   etroite que les 27 px mesures sur le bloc seul, quel est le chiffre de lignes que vous retenez
   comme plafond reel pour le texte-support - une question que je ne peux pas trancher sans rendu ?
