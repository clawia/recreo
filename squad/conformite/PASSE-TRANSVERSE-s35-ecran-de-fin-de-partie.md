# Passe transverse de s35 - l'ecran de fin de partie, commun aux trois mecaniques

**Auteur** : `nadia-prof`.
**Date** : 2026-09-11 (s35).
**Arbre** : `s35-integration` a `c1b326d4`, worktree `s35-nadia`.
**Objet** : le Done pedagogique de `S35-SCO1-ECRAN-FIN`, qui ferme le refus de `S34-SCO1-REMEDE`.

**Pourquoi ce fichier et pas une fiche de jeu.** L'ecran de fin de partie n'appartient a aucun
paquet : c'est un seul fragment (`retour.html`) servi par les trois mecaniques. Le porter dans
`FICHE-bandit-manchot-additions-cp.md` le rendrait invisible depuis la saisie et la designation.
Meme forme que `PASSE-TRANSVERSE-s29` et `PASSE-TRANSVERSE-s30`, pour la meme raison : une propriete
de portee depot se trace hors des fiches de paquet. **Ce document porte en revanche un vrai verdict**,
contrairement aux deux passes precedentes, parce qu'une story lui est attachee.

---

## 0. Conditions - j'ai joue, et je dis la forme exacte

Jar reconstruit depuis mon propre worktree (`mvn -pl bootstrap -am clean package`), lance sur le JDK
`21.0.10` de la toolchain, port 8080. Parties jouees par requetes HTTP reelles, donc sur le HTML
reellement servi.

| Mecanique | Jeu | Ce que j'ai joue | Ce que l'ecran de fin a affiche |
|---|---|---|---|
| `bandit-manchot` | `bandit-manchot-additions-cp` | 10 manches, 5 trouvees du premier appui, 4 apres une erreur, 1 poussee jusqu'a la revelation | *"Tu as trouve, tout seul : 5"* / *"Tu as joue toutes les questions : 10"* |
| `saisie` | `saisie-complements-10-cp` | 7 manches, 3 trouvees du premier coup, **la derniere epuisee jusqu'a la revelation** | *"Tu as trouve, tout seul : 3"* / *"Tu as joue toutes les questions : 7"* |
| `designation` | `designation-frise-de-la-journee` | 10 manches, toutes trouvees au deuxieme appui | *"Tu as trouve, tout seul : 0"* / *"Tu as joue toutes les questions : 10"* |
| `bandit-manchot` | les quatre paquets d'histoire-geographie de `S35-CONS-4` | 4 parties completes | meme texte, comptes justes (4/10, 3/10, 2/10, 7/7) |

**Les deux nombres ont ete recomptes a la main contre ma propre trace de jeu sur les quatre parties
de la premiere colonne : les quatre sont justes.**

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

- **Verdict** : **VALIDE SOUS RESERVE**
- **Date** : 2026-09-11 (s35)
- **Auteur** : `nadia-prof`
- **Ce que ce verdict autorise** : `SCO-R1` est **levee**. Aucune ligne de
  `squad/programme/MATRICE-couverture.md` ne bouge - cette story ne porte aucune competence.

### 1. `SCO-R1` est levee, et je dis exactement contre quoi je la mesure

Mon refus du 2026-09-11 (s34, `verdicts-nadia-instance-A.md`, section 5.2) demandait ceci, mot pour
mot : *"a la fin d'une partie, l'enfant doit lire deux choses - ce qu'il a trouve seul, et qu'il a
fait toutes les questions."*

**Ce que l'enfant lit aujourd'hui, dans cet ordre, sur les trois mecaniques :**

> *"Tu as trouve, tout seul :"* - ***"5"*** - *"Tu as joue toutes les questions :"* - ***"10"***

- **Deux informations distinctes, lisibles separement** : oui. Chaque nombre porte **sa propre
  phrase**, posee juste au-dessus de lui.
- **Le nombre reduit ne remplace plus les deux** : oui. *"Score : 6"* seul a disparu de cet ecran.
- **Aucun terme de note scolaire** : verifie mot par mot. Ni *score*, ni *sur 10*, ni *resultat*, ni
  *points*. Aucune formule ne presente le nombre bas comme un resultat.
- **Le premier mode de defaillance que j'avais nomme d'avance** - montrer a l'enfant ce que ses
  essais lui ont coute - **n'est pas realise** : rien sur cet ecran ne dit que le nombre est plus bas
  qu'il aurait pu l'etre.
- **Le second mode de defaillance** - *"6"* et *"10"* poses l'un pres de l'autre se lisant comme une
  note - **n'est pas realise non plus** : les deux nombres sont separes par une phrase entiere qui
  nomme le second. Un enfant ne peut pas les lire comme un quotient.
- **Un ecran commun, pas trois variantes** : oui, et je l'ai verifie en jouant, pas en lisant le
  gabarit. Le meme texte, au mot pres, sur `bandit-manchot`, `saisie` et `designation`.
- **Le remede des trois essais n'a pas ete touche** : les trois sessions comptent comme avant, le
  nombre de reussites monte sur les seules manches trouvees du premier appui. Verifie en comptant a
  la main sur quatre parties.
- **Ce que je n'avais pas compte a s34, et que je compte maintenant** : *"l'ecran de fin est aussi
  celui d'une manche revelee ; je n'ai pas verifie ce que le texte neuf y donnerait."* Je l'ai
  verifie, en finissant exprès une partie de saisie sur une manche epuisee. L'ecran donne :

  > *"Tu as trouve, tout seul : 3"* / *"Tu as joue toutes les questions : 7"* / *"Tu n'as pas
  > trouve."* / *"5 + ? = 10"* / *"La bonne reponse : 5"* / *"C'est fini !"*

  **C'est honnete et c'est complet** : les deux nombres tiennent, le verdict de la derniere manche ne
  ment pas, et l'enfant repart en sachant la reponse qu'il n'a pas trouvee. **Ce point est donc
  paye, et il n'etait pas dans le perimetre que j'avais chiffre.**

**Une phrase de plus, parce qu'elle est due.** *"Tu as joue toutes les questions"* est vrai par
construction : le nombre affiche est le nombre de manches resolues, et une partie ne se termine
qu'une fois le nombre de manches du catalogue atteint. J'ai verifie ce point dans le code plutot que
de le supposer, sur les trois sessions (`BanditManchotSession`, `SaisieSession`,
`DesignationSession`). **L'ecran ne peut donc pas annoncer *toutes* sur une partie incomplete.**

### 2. `SCO-R4` - **reserve neuve, datee du 2026-09-11 (s35), non bloquante** : les deux phrases qui portent l'information sont le plus petit texte de l'ecran

**Mesure, sur la feuille maison servie** (`recreo.css`) :

| Element | Ce qu'il porte | Taille declaree |
|---|---|---|
| `.score-trouve-nombre`, `.score-joue-nombre` | les deux nombres | **1,25rem**, gras, couleur d'accent |
| `.score-trouve-libelle`, `.score-joue-libelle` | **les deux phrases qui disent lequel est lequel** | **aucune taille declaree** |
| `.signal .verdict` | *"Bravo, c'est juste !"* | 1,25rem |
| `.reponse-revelee`, `.question-rappelee` | *"La bonne reponse :"*, la question | 1,25rem |
| `.reponse-attendue` | la reponse | 2rem |
| `.fin-de-partie` | *"C'est fini !"* | 1,5rem |
| `.sortie-pleine` | *"Les jeux"* | 1,25rem |

**Sans taille declaree, ces deux phrases heritent de `body`, que la feuille tierce fixe a `1rem`**
(`bootstrap.min.css` : `body { font-size: var(--bs-body-font-size) }`, `--bs-body-font-size: 1rem`).
**Elles sont donc a 16 px, et ce sont les deux seuls textes de cet ecran sous 1,25rem.** Tout le
reste de ce que l'enfant lit ici est a 1,25rem ou au-dessus.

- **Objet** : *les deux phrases qui disent a l'enfant lequel des deux nombres est lequel se lisent
  au moins aussi bien que les nombres eux-memes.* Aujourd'hui, les nombres crient et le sens
  chuchote : la hierarchie visuelle est l'inverse de la hierarchie de l'information.
- **Le plancher n'est pas neuf et il n'est pas de circonstance** : 1,25rem est celui que j'ai pose
  et qu'`ADR-0050` (decision 4) tient deja. Ma condition d'arrivee sur `SCO-R1` disait *"les mots
  sont lisibles par un enfant de CP"* : les mots choisis le sont, leur taille ne l'est pas.
- **Mode de defaillance a eviter** : baisser la taille des nombres pour egaliser. Le geste est de
  declarer 1,25rem sur les deux libelles, comme sur tout le reste de l'ecran.
- **Perimetre** : **deux selecteurs**, une seule feuille. Aucun texte ne change.
- **Regime et prix** : **mot pour mot, 0,25.**

### 3. Ce dont je reste la seule garde sur cet ecran, et je le dis pour que personne ne croie couvert ce qui ne l'est pas

`LEcranDeFinDePartiePorteLesDeuxNombresTest` existe, il joue une partie complete sur les trois
mecaniques du catalogue reel, et `yanis-qa` l'a mis a l'epreuve en mutant `retour.html` lui-meme
(rapport qualite de s35, section 3.2 : rouge 3/3 sur le fichier mute, vert 3/3 restaure). **C'est un
bon garde-fou et je le porte au credit du lot** : il mord sur le fragment servi, pas sur une copie en
memoire.

**Ce qu'il ne garde pas, et ce que je viens de trouver en est la preuve** :

1. **La taille des deux libelles.** `TailleDesTextesEtBoutonsTest` verifie *"aucun texte ne passe
   sous la taille de la consigne"* sur une **liste de sept selecteurs ecrits a la main**
   (`.score`, `.indice`, `.fin-de-partie`, `.erreur`, `.pas-de-jeu`, `.message-reprise`,
   `.explication`), et son autre methode ne juge que **les tailles declarees**. Un texte qui ne
   declare aucune taille n'est vu par aucune des deux. **Les deux libelles neufs de cet ecran sont
   passes par ce trou, avec une CI entierement verte.** C'est la meme famille de defaut que
   `yanis-qa` mesure sur `laPropositionAuRabaisAPersonneNApparaitDansAucuneTache` (section 4 de son
   rapport) : un garde-fou dont la population est une liste ecrite a la main ne garde que cette
   liste. **Je remonte le symptome ; la forme du garde-fou est a `theo-archi`.**
2. **Le sens des deux phrases.** Qu'*"tu as trouve, tout seul"* soit ce qu'un enfant de six ans
   comprend, et non un jargon, aucun automate ne le dit. C'est moi, en jouant.
3. **La hierarchie visuelle** - qu'un nombre ne l'emporte pas sur la phrase qui l'explique. Aucune
   arithmetique ne la juge.

### 4. Ce que ce verdict ne fait pas

- Il ne traite pas `SCO-R2` (rien ne dit a l'enfant que seul le premier essai compte) ni `SCO-R3`
  (sur la saisie, une validation vide coute un essai). Les deux restent ouvertes, non bloquantes,
  et n'etaient pas dans le perimetre de cette story.
- Il ne rouvre ni `ADR-0090` ni le calcul de score.
- Il ne touche pas l'ecran de manche, qui affiche toujours *"Score : 0"* a 1,25rem. Je ne l'ai pas
  juge ici et je n'en tire aucune demande : ce n'est pas l'ecran que `SCO-R1` nommait.
