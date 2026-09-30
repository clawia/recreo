# Fiche de conformite - bandit-manchot-vocabulaire-musical-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-musique-vocabulaire.yaml`
  (11 items, type `question`), ecrit par `marc-dev` (commit `5720c07a`, fusion `f0fbebd0`).
- **Entree de catalogue** : `bandit-manchot-vocabulaire-musical-cp`, titre servi *"Les mots de la
  musique"*, consigne servie *"Lis bien, puis dis le mot juste, ou ce qu'il veut dire."*,
  `nombreDeManches: 10`.
- **Ligne declaree** : `MUS-CP-11`. **Story** : `S44-MUS-VOCABULAIRE` (`docs/backlog/backlog.md`,
  section "Sprint 44").
- **Conditions opposables** : `MUS-1`, `MUS-2` (`squad/programme/education-musicale/competences-cp.md`),
  `MUS-3` (`squad/conformite/ARBITRAGE-PREALABLE-s44-musique-et-sciences-cm1.md`, section 2.2),
  `FER-1` a `FER-3`.
- **Statut global** : **VALIDE SOUS RESERVE**, prononce le 2026-09-30 par `nadia-prof` (s44,
  instance unique). Fiche ouverte par ce verdict : aucune fiche n'existait pour ce jeu.
- **Derniere mise a jour** : 2026-09-30.

---

## Bloc de validation pedagogique

### Verdict du 2026-09-30 (`nadia-prof`, s44, instance unique) : **VALIDE SOUS RESERVE**

**Done pedagogique prononce.** `camille-po` peut passer `MUS-CP-11` a `couvert-valide`, **avec la
mention de la section 6, qui est une condition de ce Done et non une note**. La reserve
`MUS-CP-R1` ne bloque pas le mouvement de ligne.

#### 1. Ce que j'ai fait

**Audit complet, pas degrade.** Jar construit sur `round-two` a `bc1cf4b0`
(`bash outils/mvn-jdk21.sh -B clean package -DskipTests`, le Done technique etant deja acquis par
`yanis-qa`), lance sur le JDK 21 de la toolchain. **Six parties completes** de dix manches, jouees
contre le jar servi, par les routes de l'ecran : sur chaque partie, un tiers des manches en
repondant juste, un tiers apres une erreur, un tiers apres trois erreurs jusqu'a la revelation.
**Les onze items ont ete tires et joues.**

Apres une erreur : *"Ce n'est pas ça. Essaie encore !"*, la proposition barree, l'indice affiche.
Apres la troisieme : *"Appuie sur la bonne réponse : ..."*, l'indice reste. Ecran de fin correct,
avec l'item manque rappele et sa bonne reponse. Aucun enfant n'est laisse sans issue.

#### 2. Ancrage au programme

`MUS-CP-11` : *"Nommer quelques repères du vocabulaire musical rencontré (refrain, couplet,
pulsation, silence)."* **C'est bien cette competence que le paquet travaille** : huit items
demandent le mot a partir de sa definition, trois la definition a partir du mot. Aucun item ne
glisse vers `MUS-CP-04` (caracteristique d'un son), `MUS-CP-03` (geste) ou `MUS-CP-10` (silence de
conduite).

#### 3. Conditions, verifiees une par une

| Condition | Tenue | Ce que j'ai verifie |
|---|---|---|
| `MUS-1` | **oui** | Aucun item ne suppose une ecoute. En effacant l'ecoute, chaque reponse se trouve par la seule connaissance du mot. Aucun texte ne contient `écout` ni `entend` (cherche sur les onze items, pas lu dans le commentaire du paquet) |
| `MUS-2` | **oui** | Aucun `texteAVoixHaute`, aucun bloc adulte a l'ecran sur les six parties. Aucun texte ne contient `adulte`, `fredonn`, `frapp` ni le verbe *taper* en mot entier |
| `MUS-3`, exigence 1 | **oui** | Cinq items de pulsation et de silence (5 a 8, 11), plus les distracteurs de 9 et 10 : aucune chanson, aucun morceau, aucun instrument, aucune situation d'ecoute nommee |
| `MUS-3`, exigence 2 | **oui** | La pulsation est definie par *"battement régulier"*, jamais par ce qu'on en fait avec le corps |
| `MUS-3`, exigence 3 | **oui** | Aucun comptage, aucun *combien* |
| `MUS-3`, exigence 4 | **oui** | Le silence est toujours situe : *"au milieu d'une chanson"*, *"à l'intérieur d'une musique"*, et dans l'item 11 le distracteur *"le moment où il n'y a aucun son"* est sous l'enonce *"la pulsation d'une musique"*. Aucun sens de conduite, ni en enonce ni en distracteur |
| `MUS-3`, exigence 5 | **oui** | Distracteurs pris dans les quatre mots du paquet. Ni `le rythme`, ni `le tempo`, ni `la mesure`, ni `la pause`, ni `le soupir`, nulle part |
| `MUS-3`, exigence 6 | **oui** | Meme recherche que `MUS-2`, sur enonce, propositions et indices |
| `FER-1` | **oui** | Chacun des quatre termes est bonne reponse de deux items de la tache "definition -> mot" : refrain (1, 2), couplet (3, 4), pulsation (5, 6), silence (7, 8) |
| `FER-2` | **oui** | 11 items, au-dessus du plancher de 10 |
| `FER-3` | **oui** | Deux taches ; la moins servie, "mot -> definition", porte 3 items. Elle ne compte pas pour `FER-1`, comme je l'avais ecrit (arbitrage, section 2.4) |

**`MUS-3` est tenue telle que `marc-dev` l'a ecrite, et pas seulement telle que je l'avais
envisagee.** Les items 5 et 7 reprennent mes deux exemples mot pour mot ; les items 6 et 8 sont
neufs, et je les ai juges contre les six exigences, pas contre mes exemples. L'item 8 (*"le moment
sans aucun son à l'intérieur d'une musique"*) est plus sur que mon propre exemple : il ne depend pas
de la place du silence dans le morceau.

#### 4. Niveau reel

- **Lisibilite** : enonces de 6 a 14 mots, une phrase chacun, sous le plafond de `ADR-0089`.
  *Battement* et *régulier*, que je m'etais engagee a verifier en jouant, se lisent dans une phrase
  courte ou ils sont les seuls mots difficiles ; ce sont les mots de la definition elle-meme, je les
  accepte.
- **Charge** : la tache "mot -> definition" (items 9 a 11) fait lire trois boutons de 7 a 11 mots.
  C'est lourd pour un CP, et je l'accepte pour trois items sur onze : ces definitions reprennent les
  mots deja lus dans les items 1 a 8, et c'est la seule forme qui fait reconnaitre le sens d'un mot
  plutot que son etiquette.
- **Traitement de l'erreur** : chaque indice ajoute un trait que l'enonce ne donnait pas (sauf
  l'item 6, dont l'indice reformule l'enonce ; la revelation debloque l'enfant, je ne le compte pas
  en reserve).

#### 5. Exactitude, biais et ton

**Les onze definitions sont exactes au niveau du CP** : le refrain revient avec les memes mots, le
couplet change de texte, la pulsation est le battement regulier qui continue du debut a la fin, y
compris pendant un silence, le silence est un moment sans son a l'interieur de la musique. L'item 2
(*"la partie d'une chanson qui se répète plusieurs fois"*) est la formule de classe du refrain ; la
melodie du couplet revient aussi, mais un enfant de CP ne l'oppose pas a cette formule, et je ne la
compte pas comme une seconde reponse defendable. Aucun biais, aucun ton condescendant.

#### 6. Mention de couverture, que la story me confiait

La mention exigee d'avance par mon referentiel (`competences-cp.md`, section "Mention exigee
d'avance si une ligne bouge") portait sur les lignes 5 **et** 11 ensemble. Seule la ligne 11 ferme
dans ce sprint. **La matrice porte, sur la ligne `MUS-CP-11`, cette mention exactement** :

> *"vocabulaire musical seul (refrain, couplet, pulsation, silence), sur le mot écrit ; aucune
> compétence auditive, vocale ni corporelle n'est servie, et les familles d'instruments (ligne 5)
> restent non couvertes"*

*Corporelle* est ajoute parce que la pulsation est nommee sans etre frappee : `MUS-CP-03` reste
`non couvrable en l'etat`, et une ligne verte qui porte le mot *pulsation* ne doit pas le laisser
croire. La mention d'origine reste valable pour le jour ou la ligne 5 fermera aussi ; je l'ai
signale dans le referentiel par un amendement date.

#### 7. La reserve

> **`MUS-CP-R1` - Items 1 et 4 : l'enonce nomme un distracteur et l'exclut.** Item 1 : *"Comment
> s'appelle la partie d'une chanson qui revient après chaque couplet ?"*, distracteurs `le couplet`
> et `la pulsation`. Item 4 : *"... qui vient juste avant le refrain ?"*, distracteurs `le refrain`
> et `le silence`. Le premier distracteur est ecarte par l'enonce lui-meme, le second n'est pas une
> *partie* de chanson : **un enfant qui ne connait aucun des quatre mots trouve la reponse**.
>
> **Correction a faire** : un enonce qui ne nomme aucun des mots proposes (par exemple, pour
> l'item 1, *"Comment s'appelle la partie d'une chanson qui revient toujours avec les mêmes
> mots ?"*, en verifiant qu'il ne double pas l'item 2), ou un distracteur qui n'est pas nomme
> dans l'enonce.
>
> **Je corrige d'abord mon propre texte** : l'exemple que j'ai ecrit le 2026-09-03 dans le
> referentiel pour cette ligne (*"Quelle partie d'une chanson revient après chaque couplet ?"*)
> porte deja ce defaut. L'item 1 le suit. C'est une reserve neuve et datee, trouvee en jouant, pas
> un durcissement de `MUS-3`, que le paquet tient.
>
> **Pourquoi elle n'empeche pas `couvert-valide`** : deux items sur onze, les neuf autres exigent
> de connaitre le mot, et les quatre termes restent servis en bonne reponse sur un item sans ce
> defaut (2, 3, 5 a 8).

#### 8. Ce dont je reste la seule garde

`PaquetCpMusiqueVocabulaireTest` tient le lexique (`MUS-3` exigence 6, `MUS-2`), `FER-1` a
`FER-3` et la longueur. **Aucun automate ne verifie** : qu'une definition soit generale et non un
cas particulier (`MUS-3`, exigence 1), que le silence soit musical (exigence 4), qu'un distracteur
ne soit pas defendable (exigence 5), qu'un enonce ne nomme pas son distracteur (`MUS-CP-R1`,
proche de la propriete 1 d'`ADR-0086`, refusee a l'encodage), ni l'exactitude des definitions.
Ces cinq proprietes n'ont ete verifiees qu'ici.
