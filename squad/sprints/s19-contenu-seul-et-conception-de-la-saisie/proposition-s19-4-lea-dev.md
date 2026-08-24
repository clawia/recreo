# S19-4 - Proposition d'items pour arbitrage prealable de nadia-prof (nom/verbe, francais CP)

- **Auteur** : lea-dev. **Date** : 2026-08-24.
- **Ce que ce fichier est** : une proposition de phrases pour la story `S19-4`
  (`docs/backlog/backlog.md`), soumise a l'arbitrage prealable que `planning.md` (sequencement,
  point 3) exige avant tout code de contenu. **Aucun paquet YAML n'est ecrit, aucune entree de
  catalogue n'est ajoutee** : c'est la limite exacte que la story m'impose tant que cet arbitrage
  n'est pas rendu.
- **Ce que ce fichier n'est pas** : ni un Done pedagogique, ni une decision arretee sur le
  vocabulaire ou la forme - c'est une proposition, a corriger par `nadia-prof` autant que
  necessaire avant que le code ne commence.

## Rappel du besoin et de ce qui n'est pas tranchable par un automate

Competence visee : francais, CP (cycle 2), "Orthographe et grammaire", "Distinguer les principaux
types de mots (nom, verbe) dans une phrase tres simple". Matiere premiere
(`contenu-repris/mots/cm1-nature-mot.yml`) classe des mots isoles de CM1 en quatre natures ; la
competence CP en vise deux (nom, verbe), toujours dans une phrase, jamais un mot isole. Aucune
partie de la matiere premiere n'est directement reutilisable, seul son principe (choisir entre deux
natures) l'est.

Trois questions pedagogiques, nommees dans la fiche de conformite
(`squad/conformite/FICHE-bandit-manchot-nature-mot-cp.md`) et dans `docs/backlog/backlog.md`, ne
sont pas resolues par ce document et attendent le jugement de `nadia-prof` :

1. Ces seize phrases sont-elles reellement lisibles par un enfant de CP en cours d'apprentissage de
   la lecture (longueur, vocabulaire, absence de mot irregulier non encore vu) ?
2. Le choix nom/verbe se resout-il uniquement par le sens du mot, jamais par un indice de
   position dans la phrase ?
3. Comment l'enfant designe-t-il le mot dont on parle, sachant qu'il lit encore peu ?

## Comment l'enfant sait sur quel mot porte la question

**Proposition : reprendre telle quelle la convention deja validee pour "Les synonymes"** (SYN-1,
SYN-6, SYN-9 : mot cible entre guillemets francais, espaces insecables a l'interieur) - la
consigne du jeu resterait fixe et generique ("Quel est le mot entre guillemets : un nom ou un
verbe ?"), lue une fois par l'adulte ou l'enseignant qui presente le jeu, comme pour tout autre jeu
du catalogue. Aucun code de rendu neuf : le mecanisme d'affichage est deja etabli par `ADR-0052` et
deja mesure par `nadia-prof` sur un paquet CM1.

**Ce que je n'affirme pas, et qui merite le jugement de `nadia-prof` specifiquement pour le CP** :
un enfant de CM1 qui a deja vu des guillemets a l'ecrit s'appuie sur une convention qu'il connait
un peu ; un enfant de CP en debut de lecture peut ne pas encore associer ce signe a "le mot dont on
parle". Si les guillemets seuls ne suffisent pas a ce niveau, le renfort visuel (couleur, gras)
serait une question de rendu pour `ines-design`, hors du perimetre de cette story de contenu -
je la nomme plutot que de la trancher.

## Les seize phrases proposees

Toutes distinctes (avis-faisabilite.md s19, section 4 : "le paquet doit porter 16 phrases
distinctes, pas 8 phrases vues deux fois sous deux mots cibles" - aucune phrase de la liste "nom"
ne reapparait, meme modifiee, dans la liste "verbe"). Vocabulaire choisi parmi des mots courants de
CP (animaux, famille, objets de la classe et de la maison, actions quotidiennes). Chaque indice
aide a reconnaitre la nature sans jamais nommer la reponse, sur le patron donne par la story
("un verbe dit ce qu'on fait").

### Le mot cible est un nom (8 phrases, reponse "nom")

| # | Phrase | Mot cible | Indice propose |
|---|---|---|---|
| 1 | Le « chat » dort sur le tapis. | chat | Ce mot désigne un animal : on peut dire "le" ou "un" devant lui. |
| 2 | Léa porte un « sac » bleu. | sac | Ce mot désigne un objet : on peut dire "le" ou "un" devant lui. |
| 3 | Le « soleil » brille dans le ciel. | soleil | Ce mot désigne une chose : on peut dire "le" devant lui. |
| 4 | Tom mange un « gâteau » au chocolat. | gâteau | Ce mot désigne une chose qu'on peut manger. |
| 5 | Mon « vélo » est rouge. | vélo | Ce mot désigne un objet : on peut dire "mon" devant lui. |
| 6 | Les « oiseaux » chantent dans l'arbre. | oiseaux | Ce mot désigne des animaux : on peut dire "les" devant lui. |
| 7 | Papa lave la « voiture ». | voiture | Ce mot désigne un objet : on peut dire "la" devant lui. |
| 8 | La « maîtresse » écrit au tableau. | maîtresse | Ce mot désigne une personne : on peut dire "la" devant lui. |

### Le mot cible est un verbe (8 phrases, reponse "verbe")

| # | Phrase | Mot cible | Indice propose |
|---|---|---|---|
| 9 | Léa « joue » avec sa poupée. | joue | Ce mot dit ce que fait Léa. |
| 10 | Le chien « court » dans le jardin. | court | Ce mot dit ce que fait le chien. |
| 11 | Tom « saute » par-dessus la barrière. | saute | Ce mot dit ce que fait Tom. |
| 12 | Maman « range » la cuisine. | range | Ce mot dit ce que fait maman. |
| 13 | Les enfants « chantent » une chanson. | chantent | Ce mot dit ce que font les enfants. |
| 14 | Le poisson « nage » dans l'eau. | nage | Ce mot dit ce que fait le poisson. |
| 15 | Léa « arrose » les fleurs. | arrose | Ce mot dit ce que fait Léa. |
| 16 | Papa « lit » le journal. | lit | Ce mot dit ce que fait papa. |

Repartition : 8 noms, 8 verbes - equilibree, aucune nature dominante.

## Un point que je nomme sans le trancher : les mots ambigus entre nom et verbe

Aucun des seize mots cibles ci-dessus n'est un homographe nom/verbe (par exemple "ferme" - une
ferme / il ferme -, "porte" - une porte / il porte -, ou "marche" - la marche / il marche -). Je les
ai ecartes par prudence, mais la story ne dit pas si c'est le bon choix : un enfant de CP ne
rencontrera peut-etre jamais ce genre d'ambiguite avant le CM1, auquel cas les exclure est juste ;
ou au contraire l'exercice pourrait vouloir les assumer plus tard pour montrer qu'un meme mot peut
changer de nature selon la phrase. Je route cette question a `nadia-prof` plutot que de decider
seule, comme le demande la story.

## Ce que je n'ai pas fait, volontairement

- Pas de fichier YAML sous `adapters/adapter-content/src/main/resources/contenu/`.
- Pas d'entree ajoutee a `catalogue.yaml`.
- Pas de test `PaquetCpNatureMotTest`.

Ces trois manques sont le respect de la contrainte de sequencement : le code de contenu de
`S19-4` n'ecrit qu'apres cet arbitrage rendu.
