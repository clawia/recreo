# Fiche de conformite - bandit-manchot-accords-groupe-nominal-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/
  cm1-accords-groupe-nominal.yaml` livre, `type: question`, 24 items (determinant/nom/adjectif,
  huit items chacun, un seul trou par item), story `S19-2` (`docs/backlog/backlog.md`). Matiere
  premiere partiellement reutilisable :
  `contenu-repris/textes-trous/cm1-accords.yml` (5 items) ne teste que l'accord de l'adjectif, le
  determinant et le nom etant deja fixes dans chaque phrase - verifie ligne par ligne. Le paquet
  doit etendre la couverture au determinant et au nom pour representer le groupe nominal complet
  que nomme la competence.
- **Statut global** : **increment livre, Done pedagogique non encore prononce.** Rien n'a encore
  ete joue par `nadia-prof` sur cet increment : le Done pedagogique reste entier.
- **Aucun arbitrage prealable de `nadia-prof` demande** : cette competence ne souleve aucune
  question de validite de proxy - elle est directement servable par le schema `type: question`
  existant (fill-in-the-blank), sur le meme patron que l'accord de l'adjectif deja identifie par
  `DEC-0008`. Les criteres ci-dessous sont ceux de `camille-po`, verifies a l'audit comme toute
  autre story de contenu de ce produit.
- **Arbitrage prealable de mecanique en vigueur pour tout le catalogue** (`squad/conformite/
  FICHE-bandit-manchot-additions-cp.md`, conditions M1 a M13).
- **Derniere mise a jour** : 2026-08-24 (`lea-dev`, depot du paquet, les trois composantes du
  groupe nominal tiennent toutes dans le paquet livre)

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CM1 (cycle 3) | Orthographe et grammaire | Accorder en genre et en nombre dans le groupe nominal (determinant/nom/adjectif) | `squad/programme/francais/competences-cm1.md` |

**Perimetre propose** : les trois composantes du groupe nominal (determinant, nom, adjectif) sont
chacune testees par le paquet, pas seulement l'adjectif comme le fait la matiere premiere. Si les
trois ne tiennent finalement pas dans ce paquet a cout raisonnable, la limite de couverture doit
etre nommee par ecrit dans cette fiche des l'ouverture du developpement, sur le modele de
`DEC-0013` (conjugaison, un temps sur trois nomme d'emblee) - jamais decouverte a l'audit.

## Criteres pedagogiques attendus - camille-po

- [ ] Le paquet classe ses items par composante testee (determinant, nom, adjectif) ; chacune des
  trois est representee par au moins un quart des items.
- [ ] Sur un item qui teste l'accord du nom, les distracteurs portent une erreur de nombre reelle
  sur le nom (par exemple un pluriel en `-s` manquant ou ajoute a tort), jamais une erreur d'un
  autre ordre.
- [ ] Sur un item qui teste l'accord du determinant, les distracteurs portent un determinant dont
  le genre ou le nombre ne correspond pas au nom qui suit, jamais un determinant inexistant en
  francais.
- [ ] Sur un item qui teste l'accord de l'adjectif, les distracteurs reprennent le patron deja
  valide sur `cm1-accords.yml` (les quatre formes possibles de l'adjectif, une seule accordee juste
  avec le reste du groupe).
- [ ] Le paquet compte au moins 20 items.
- [ ] Chaque item porte un indice non vide qui donne la methode d'accord, pas le resultat.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve.
- [ ] Si les trois composantes ne tiennent pas toutes dans ce paquet, la limite de couverture est
  nommee explicitement dans cette fiche avant le premier audit, jamais decouverte pendant.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. Il reste vide tant qu'aucun
> increment n'a ete livre et joue.

### Verdict du 2026-08-24 (s19) - `S19-2`, "Le bon accord"

- **Auteur** : nadia-prof. **Increment** : `round-two` a `51301d7`, jar reconstruit depuis cet arbre
  et servi, application **arretee par son PID**.
- **Audit joue** : oui. **6 parties, 114 lectures, 24/24 items vus**, au repos et apres un echec, a
  **744 x 551** (`DEC-0040`). **`scrollY = 0` a chaque lecture, 0 sur 114.**
- **Releve** : `squad/conformite/releves-s19/s19-audit-accords-gn-744x551.json`. Capture :
  `s19-agn-744x551-histoires-echec-indice-sous-le-pli.png`.
- **Aucun arbitrage prealable n'etait du de ma part sur cette ligne** - j'ai ecrit le 2026-08-24
  qu'elle etait ouvrable sans moi. **Je decouvre donc ce paquet entierement a l'audit**, et tout ce
  qui suit est un jugement de premier passage, pas la verification d'une condition posee d'avance.

## Verdict : **VALIDE SOUS RESERVE - aucune reserve bloquante.**

### 1. Le contenu - juste, et couvrant les trois parties de la competence

La ligne du referentiel dit *"Accorder en genre et en nombre dans le groupe nominal
(determinant/nom/adjectif)"*. **Les trois sont travailles, huit items chacun, et c'est exactement ce
que la ligne nomme.** J'avais signale a s17, sur la matiere premiere reprise, qu'un paquet fonde sur
`contenu-repris/textes-trous/cm1-accords.yml` ne couvrirait que l'adjectif ; ce paquet-ci ne fait pas
cette moitie de travail. **La ligne est couverte en entier.**

**Les 24 items sont justes**, verifies un par un : les accords attendus sont les bons, et **aucun
distracteur n'est une forme egalement acceptable** dans sa phrase. J'ai regarde de pres les deux
endroits ou une seconde reponse serait possible et il n'y en a pas : *"Tom a perdu ___ gomme
preferee"* n'accepte que *sa* (*gomme* commence par une consonne, donc pas de *son* euphonique), et
*"___ fleurs sentent tres bon"* n'accepte que *Les* parmi les trois proposees.

**Les distracteurs sont des erreurs reelles, jamais des mots inexistants** : *La*/*Les* contre *Le*,
*fleur* contre *fleurs*, *joli*/*jolis*/*jolie* contre *jolies*. Ce sont les fautes qu'on lit dans
les copies. C'est le point sur lequel j'ai refuse d'autres paquets, et il est tenu ici.

### 2. Le rendu, mesure

| Mesure a 744 x 551 | Resultat |
|---|---|
| Propositions par item | **2, 3 ou 4 selon le tiers** - 2 sur les huit items de nom, 3 sur les huit de determinant, 4 sur les huit d'adjectif |
| Items sur deux rangees | **8 sur 24** - les huit items a quatre propositions |
| Largeur necessaire, maximum, sur les items a une rangee | **au plus 400 px** contre 680 px offerts |
| Bas du bloc de verdict, pire cas | **510,19 px** pour un pli a 551 px - **au-dessus, 24/24** |
| Bas de la boite d'indice, pire cas | **619,19 px** sur huit items |

**Les huit items sur deux rangees ne sont pas un defaut de largeur, et je le dis pour que personne ne
les compte comme un `SYN-11`.** La feuille de style dispose deliberement quatre propositions en deux
colonnes de deux (`.propositions:has(.proposition:nth-child(4))`, base de 50 % moins l'ecart) : c'est
la mise en page voulue pour un paquet a quatre propositions, deja servie par la conjugaison depuis
s08. Mon balayage le confirme - ces huit items restent sur deux rangees **a toute largeur**, y compris
a 700 px, ce qu'aucun defaut de largeur ne produirait. **Le verdict reste au-dessus du pli sur les 24
items**, et c'est le critere qui compte.

### 3. Les reserves - trois, aucune bloquante

**`AGN-1` (2026-08-24, non bloquante).** **Huit items sur vingt-quatre perdent leur indice sous le
pli** - les huit items d'adjectif, bas de la boite a **619,19 px** pour un pli a **551 px**. Sur la
capture, l'enfant ne voit qu'un liseré orange au bord de l'ecran : ce n'est pas la fin de l'indice
qui manque, c'est l'indice entier.

**Je la classe non bloquante, et je dis pourquoi plutot que de le laisser deviner.** C'est le meme
defaut que `SYN-12`, par le meme mecanisme - le pli, jamais un rognage - et **je l'ai classe non
bloquant la-bas**. Le classer bloquant ici parce que la proportion est pire (un tiers contre un
cinquieme) reviendrait a durcir ma barre entre deux paquets du meme sprint. La cause n'est en outre
pas dans ce lot : c'est le budget vertical de l'ecran, que `camille-po` a explicitement renvoye a s20.
**Punir un lot de contenu pour une dette de mise en page differee ailleurs serait viser le mauvais
lot.** Ce que je note pour s20 : **le chantier vertical dont `theo-archi` a chiffre le manque a
43,19 px sur les synonymes ne doit pas oublier les items a quatre propositions, qui en demandent
davantage** - 68,19 px ici.

**`AGN-2` (2026-08-24, non bloquante).** **Les huit items de nom sont a deux propositions**
(*fleurs*/*fleur*). Le fait que j'ai etabli hier s'y applique : avec trois essais et deux
propositions, un enfant qui presse au hasard resout la manche au deuxieme essai et **elle compte
comme une reussite**. Un tiers de ce paquet ne mesure donc rien. Non bloquante pour la meme raison
que sur `S19-4` : je ne fais pas d'un fait de mecanique un motif contre un lot de contenu, et j'ai
laisse passer la meme chose sur les homophones. **Ce qui la reparerait ici, et qui n'existe pas
ailleurs** : contrairement a *nom / verbe*, l'accord d'un nom admet un **troisieme** candidat
plausible - la forme du meme nom avec une autre erreur frequente (*chevals* contre *chevaux* et
*cheval*, par exemple). Ce tiers-la pourrait passer a trois propositions sans changer la competence.

**`AGN-3` (2026-08-24, non bloquante).** **Les vingt-quatre indices citent un mot entre apostrophes
droites** - *"Le nom 'chat' est masculin singulier"*. En francais, on cite un mot avec des guillemets
ou en italique, pas avec l'apostrophe, qui est le signe de l'elision (*l'*, *d'*, *qu'*). Sur un
paquet **de grammaire**, lu par un enfant qui apprend justement la ponctuation, la maladresse compte
plus qu'ailleurs - et le produit a deja etabli les guillemets francais comme marque de citation d'un
mot (`SYN-6`, `SYN-9`). Aucun contenu faux n'en decoule, d'ou le classement, mais **les 24 indices
sont concernes** et la correction est mecanique.

### 4. Ce que ce verdict fait, et ce qu'il ne fait pas

**Le Done pedagogique est prononce, sans reserve bloquante.** Aucune des trois reserves n'empeche un
enfant d'apprendre l'accord dans le groupe nominal avec ce jeu.

**Je ne deplace aucune ligne de matrice** : c'est le geste de `camille-po`.

**Ce que ce Done ne couvre pas** : ce qu'un lecteur d'ecran annonce, ce qu'un enfant reel comprend, et
tout rendu sur une largeur, une hauteur ou une pile de polices que je n'ai pas mesurees - en
particulier la bande 576-719 px, ou les items a quatre propositions n'ont pas ete mesures.
