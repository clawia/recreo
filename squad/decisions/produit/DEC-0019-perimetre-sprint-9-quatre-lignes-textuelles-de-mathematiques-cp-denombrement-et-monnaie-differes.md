# DEC-0019 - Perimetre du sprint 9 : quatre lignes textuelles de mathematiques CP ouvertes, denombrement et monnaie differes sous le gel de rendu

- **Type** : portee
- **Date** : 2026-08-12
- **Auteur** : camille-po
- **Sprint** : s09
- **Competence programme visee** : mathematiques, CP (cycle 2), domaine "Nombres et calculs" - quatre
  lignes nommees ci-dessous (`squad/programme/mathematiques/competences-cp.md`)
- **Statut** : Acceptee

## Contexte

L'utilisatrice a joue l'increment du sprint 8 et tranche deux choses : le sprint 9 attaque la
couverture de mathematiques CP, domaine "Nombres et calculs" ; et elle n'est pas satisfaite du
rendu CSS actuel, donc aucun travail de rendu ce sprint - ni mesure, ni garde-fou CSS neuf, ni
arbitrage visuel d'`ines-design` ou de `nadia-prof` sur des pixels. Consequence directe : ce
sprint ne peut porter que du contenu pur, servi par la mecanique bandit manchot et le schema de
paquet YAML existants, sans aucune ligne de gabarit Thymeleaf ni de `recreo.css` neuve.

Sur les sept lignes de ce domaine, une seule est `couvert-valide` (calcul mental additions,
`bandit-manchot-additions-cp`). Les six restantes, toutes `non couvert` :

1. Denombrer, lire, ecrire et decomposer les nombres jusqu'a 100.
2. Comparer et ranger des nombres jusqu'a 100 (`<`, `>`, `=`).
3. Calcul mental - soustractions dans la limite de 10.
4. Complements a 10.
5. Resoudre des problemes simples d'addition/soustraction en situation (1 etape).
6. Reconnaitre et utiliser la monnaie (pieces/billets simples) dans des problemes concrets.

## Ce que le schema existant peut porter sans code neuf

`ADR-0007` documente deux natures de paquet : combinatoire (`type: additions-bornees`, specifique a
l'addition) et enumere (`type: question`, une enveloppe generique - `enonce`, `bonneReponse`,
`distracteurs`, `indice` optionnel - deja utilisee par trois jeux du catalogue sur quatre). Sa
propre table ("Comment on ajoute quelque chose") est explicite : un paquet neuf d'un type deja
connu, quelle que soit la matiere, demande "un fichier YAML (...) plus une ligne de catalogue. Zero
ligne de Java." Aucune des lignes 2 a 5 ci-dessus n'exige un champ que `type: question` ne porte
pas deja : un enonce texte, une bonne reponse texte, une liste de distracteurs texte. C'est exact
pour un signe de comparaison, un nombre ou une phrase de situation, au meme titre que pour un mot
ou une lettre.

Ce constat porte sur la forme du schema, pas sur le rendu reel (largeur de colonne, hauteur de
viewport) : **il reste sous reserve de l'avis de faisabilite de `theo-archi`, rendu en parallele de
cette decision**. Si son avis contredit ce que j'ecris ici sur la faisabilite sans code neuf, c'est
le sien qui gouverne le perimetre retenu, pas le mien.

## Decision

### Quatre lignes entrent au sprint 9, sur du pur contenu textuel

Choisies parce qu'aucun verbe de leur intitule n'exige de montrer un objet, une piece ou un billet
a l'enfant - tout s'exprime en texte et en chiffres dans l'enveloppe `type: question` :

- **Story S9-1** - Calcul mental, soustractions dans la limite de 10.
- **Story S9-2** - Complements a 10.
- **Story S9-3** - Comparer deux nombres jusqu'a 100 avec les signes `<`, `>`, `=`. **Limitee
  explicitement a la comparaison de deux nombres** : voir plus bas, "ranger" n'entre pas dans son
  perimetre.
- **Story S9-4** - Resoudre un probleme simple d'addition ou de soustraction en une etape.

Les quatre stories sont ecrites dans `docs/backlog/backlog.md`, section "Sprint 9", avec leurs
criteres d'acceptation. Chacune ouvre une fiche de conformite sous `squad/conformite/`, remplie a
l'exception du bloc de validation, reserve a `nadia-prof` comme pour tout contenu neuf - rien dans
ce sprint ne reduit son autorite sur le Done pedagogique.

### "Ranger" (ligne 2) est differe separement de "comparer"

La competence 2 nomme deux verbes, "comparer" et "ranger". Comparer deux nombres par un signe est
strictement l'objet de S9-3. Ranger une suite de trois nombres ou plus (par exemple "range du plus
petit au plus grand : 45, 12, 78") serait exprimable dans la meme enveloppe `type: question` - la
bonne reponse et les distracteurs seraient des suites de nombres ecrites en texte plutot qu'un seul
nombre ou un seul signe - mais ces suites sont plus larges que les mots les plus larges deja mesures
sur "Le bon verbe" (`mangeons`, 156 px de glyphes contre une colonne de 152 px, `DEC-0018`). Verifier
qu'une telle proposition tient dans la colonne existante est une mesure sur le rendu, donc du travail
de rendu au sens strict de la consigne de l'utilisatrice, ce que ce sprint exclut. "Ranger" est donc
differe, pas ferme : une story ulterieure pourra l'ouvrir dès qu'une mesure de largeur (reutilisant
`outils/mesurer-rendu.sh`, deja ecrit, pas un outil neuf) aura confirme que des suites de trois
nombres a deux chiffres tiennent dans la colonne actuelle, ou dès que le gel de rendu sera leve.

### Deux lignes restent differees, pour un motif nomme

**Ligne 1 - Denombrer, lire, ecrire et decomposer les nombres jusqu'a 100.** Le premier verbe nomme,
"denombrer", designe compter une quantite presentee - une competence qui, par definition, presente
quelque chose a compter (des objets, des marques). Aucune ecriture textuelle ne teste ce verbe :
dire a l'enfant "il y a sept pommes" ne le fait pas denombrer, ca le fait lire un nombre deja
denombre pour lui. Ouvrir seulement "lire, ecrire et decomposer" - les trois verbes restants, eux
entierement textuels - reviendrait a couvrir la ligne sans jamais travailler le verbe qui la nomme
en premier. C'est exactement le motif que `nadia-prof` a deja sanctionne a plusieurs reprises sur ce
produit sous des formes voisines (repartition faussee sur "additions... jusqu'a 10 (puis 20)",
`squad/programme/MATRICE-couverture.md`, sprint 1 ; "les principaux" homophones grammaticaux
couverts a moitie, sprint 4 ; un temps sur trois de la conjugaison, `DEC-0013`) : une couverture
partielle presentee sans le dire comme la ligne entiere. Je ne reproduis pas ce motif. Cette ligne
reste `non couvert`.

**Ligne 6 - Reconnaitre et utiliser la monnaie (pieces/billets simples) dans des problemes
concrets.** Meme raisonnement, sur le verbe "reconnaitre" : reconnaitre une piece ou un billet, c'est
reconnaitre son apparence (couleur, taille, motif), une propriete visuelle qu'aucun texte ne porte.
J'ai verifie `contenu-repris/quiz/cp-monnaie.yml` avant d'ecrire cette decision (reflexe de critere
correctif applique par prudence a un contenu de reserve, meme si ce n'est pas un critere correctif au
sens strict) : ses huit questions sont des problemes arithmetiques ou la valeur de chaque piece ou
billet est donnee en toutes lettres dans l'enonce ("Tu as une piece de 2 euros et une piece de
1 euro. Combien as-tu en tout ?") - exactement le format textuel de S9-4, jamais une reconnaissance
visuelle. Ouvrir cette ligne sur ce seul contenu reviendrait a livrer un cinquieme jeu de calcul
addition/soustraction habille en euros, sans jamais travailler ce que "reconnaitre" nomme. Cette
ligne reste `non couvert`. Le contenu de `cp-monnaie.yml` n'est pas perdu : nomme au backlog comme
matiere premiere pour une story future qui travaillerait "utiliser" isolement de "reconnaitre",
explicitement scoped comme telle, le jour ou le gel de rendu est leve ou qu'un arbitrage confirme
qu'un jugement isole du volet textuel a un sens pedagogique - decision qui reste a `nadia-prof` et
non a moi de trancher par avance.

## Justification pedagogique

Aucune - cette decision ne juge la qualite pedagogique d'aucun contenu, elle trace un perimetre de
stories et le motif qui exclut deux lignes. Le jugement sur le contenu qui sera ecrit pour S9-1 a
S9-4 reste entierement celui de `nadia-prof`, exerce sur les fiches de conformite ouvertes dans le
meme geste que cette decision, comme pour tout contenu neuf.

## Impact

- `docs/backlog/backlog.md`, nouvelle section "Sprint 9" : quatre stories (S9-1 a S9-4).
- Quatre fiches ouvertes sous `squad/conformite/` : `FICHE-bandit-manchot-soustractions-cp.md`,
  `FICHE-bandit-manchot-complements-10-cp.md`, `FICHE-bandit-manchot-comparer-nombres-cp.md`,
  `FICHE-bandit-manchot-problemes-additifs-cp.md`.
- `squad/programme/MATRICE-couverture.md` : les quatre lignes visees passent `en cours` (jeu et
  mecanique renseignes), aucune ne passe `couvert-valide` par ce planning. Les lignes 1 et 6
  restent `non couvert`, avec une note renvoyant a cette decision.
- `docs/backlog/backlog.md`, section "Backlog - sprints suivants" : l'intention perimee qui
  conflait additions, soustractions et complements a 10 comme une seule ligne a clore est corrigee
  pour renvoyer a cette decision plutot que de rester en contradiction avec elle.
- Intention inscrite au backlog, sans story : le rendu CSS actuel est juge insatisfaisant par
  l'utilisatrice ; aucun cadrage de solution n'est ouvert ici, ce sera une decision separee quand
  elle le decidera.

## Ce que cette decision ne fait pas

- Elle ne tranche aucune question technique : la faisabilite sans code neuf qu'elle constate sur
  `ADR-0007` reste sous reserve de l'avis de `theo-archi`, rendu en parallele et prioritaire sur le
  mien en cas de contradiction.
- Elle ne prejuge d'aucun Done pedagogique sur S9-1 a S9-4 : ces jugements restent entierement ceux
  de `nadia-prof`, sur les fiches ouvertes.
- Elle ne fait passer aucune ligne de `squad/programme/MATRICE-couverture.md` a `couvert-valide`.
- Elle ne ferme pas les lignes 1 et 6 : elles restent au programme, differees avec un motif ecrit,
  pas abandonnees.
- Elle ne rouvre ni ne cadre le chantier de rendu CSS que l'utilisatrice a nomme insatisfaisant.
