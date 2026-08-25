# NAT-5, geste 2 - validation de .mot-designe et budget vertical avant/apres

- **Auteure** : ines-design. **Date** : 2026-08-25, s20.
- **Contexte** : `marc-dev` a livre `S20-1` geste 1 (`ADR-0062`, commit `6b8eebe`) et repris mon
  candidat mesure (`proposition-ines-nat5-mot-designe.md`) pour `.mot-designe`, avec un seul ecart
  impose par `TailleDesTextesEtBoutonsTest` : `font-size` en `2,3rem` plutot qu'en `1,15em`.
- **Statut** : validation. Aucun Done pedagogique n'est prononce ici ; `nadia-prof` reaudite apres
  ce geste.

## 1. Validation de `2,3rem` a la place de `1,15em`

**J'accepte la valeur.** `2,3rem` est la conversion algebrique exacte de mon rapport a la taille
actuelle de `.enonce` (`2rem`, `recreo.css`) : `1,15 x 2rem = 2,3rem`, au caractere pres. Rendu
verifie : le mot marque passe de `48px`/`96px` de hauteur de ligne a `55,19px`/`103,19px`,
identique au pixel a ce que j'avais mesure par injection avec `1,15em`.

**Ce que je note plutot que de taire** : une valeur en `rem` ne suit plus `.enonce` si sa taille
change un jour, la ou `em` l'aurait fait automatiquement. `marc-dev` documente deja cette
dependance dans le commentaire CSS ("2,3rem reprend au caractere pres le rapport 1,15... jamais
une valeur differente"). Je ne demande pas de garde-fou supplementaire ici - c'est une question de
maintenance de code, pas de forme visuelle - mais je le nomme pour que la prochaine personne qui
change `.enonce` sache qu'elle doit revisiter `.mot-designe` dans le meme geste, elle ne le
decouvrira pas seule.

**Ce que je ne change pas** : fond, rayon, rembourrage sont a l'identique de ma proposition
mesuree. Les guillemets francais restent hors de la boite, comme prevu.

## 2. Budget vertical, deux passages, deux bases

`ADR-0059` (second amendement) exige `campagne-budget-vertical.sh` avant et apres. **La bonne
base "avant" n'est pas `f4e0808`** (deja apres le lot) mais **`a7ca85b`**, le dernier commit avant
le lot de `marc-dev` - sans quoi l'apres serait mesure contre lui-meme. Deux jars reconstruits,
deux serveurs locaux, meme script, meme largeur de reference (`744 x 551`, `DEC-0040`).

### 2.1 Les quinze jeux catalogues - aucun ne bouge

| Metrique | Constat |
|---|---|
| `pireDeficit` (px, pire deficit sous le pli du jeu) | **identique au centieme sur les 15 jeux**, `avant` et `apres` |
| Items vus / sous le pli par jeu | varient de +-1 a +-3 entre les deux passages (echantillonnage aleatoire du tirage, `PARTIES_MAX=4` par defaut - ni l'un ni l'autre passage ne voit tous les items du plus gros paquet) |
| HTML de `.enonce`, verifie directement (jeu "Les synonymes") | `<p class="enonce" tabindex="-1">PHRASE</p>` sur les deux serveurs, sans balise ni branche - identique a l'octet sur la structure |

Tableau complet (un jeu par ligne, `itemsVus`, `itemsSousLePli`, `pireDeficit` avant/apres) :
`squad/conformite/releves-s20/budget-vertical-avant-apres-marquage.json`.

**Lecture** : `cp-nature-mot` est le seul paquet a declarer `partDesignee` (`ADR-0062`) ; aucun des
quinze jeux catalogues n'en declare. Le budget vertical de ces quinze jeux est donc, comme prevu
par la forme du contrat, rigoureusement inchange - je le mesure plutot que de le deduire de la
lecture du code, et le chiffre confirme la lecture.

### 2.2 `cp-nature-mot` lui-meme - hors catalogue, mesure sur le contrat reel

`cp-nature-mot` reste hors de `catalogue.yaml` (`DEC-0045`, non leve ici). Pour le mesurer avec le
vrai contrat (segments et `.mot-designe` reels, plus d'injection simulee), j'ai restaure son
entree **dans mon worktree seulement, jamais commitee** (`git checkout` immediat apres mesure),
reconstruit le jar et servi.

| Mesure a 744 x 551 | Resultat |
|---|---|
| Items vus | 16 / 16 |
| Items a deux lignes | **0 / 16** |
| Items sous le pli | **0 / 16** |
| Pire deficit | **-38,62 px** (38,62 px de marge au-dessus du pli, pire item : *"Léa « arrose » les fleurs."*) |

Ce chiffre satisfait exactement le critere pose au planning (`planning.md`, ligne `S20-1` geste 2) :
*"zero item sur 16 qui bascule a deux lignes a 744 x 551"*. Releve complet :
`squad/conformite/releves-s20/budget-vertical-cp-nature-mot-744-mesure-hors-catalogue.json`.

## 3. Ce que je ne fais pas

- Je ne leve pas `NAT-5` : `nadia-prof` seule tranche.
- Je ne remets pas `cp-nature-mot` dans `catalogue.yaml` : mon retablissement etait local au
  worktree, pour la duree de la mesure, et deja annule avant ce commit.
- Je ne demande pas de garde-fou neuf sur la relation `2rem`/`2,3rem` : nomme, route a qui touchera
  `.enonce` un jour, pas tranche ici.

## 4. Ce que j'ai trouve de faux dans ce brief

Rien d'inexact dans les faits qu'il pose. Une precision que je nomme pour la trace : le brief cite
"95 items sur 296" comme population de reference (`theo-archi`, sa propre campagne complete) ; mes
deux passages, avec le `PARTIES_MAX` par defaut du script (4 parties), en voient environ 300 avec
un echantillonnage legerement different a chaque lancement - la population totale vue varie donc
d'un lancement a l'autre par construction (tirage aleatoire), ce qui n'a pas de consequence sur ma
conclusion puisque c'est `pireDeficit` par jeu, invariant entre mes deux passages, qui repond a la
question posee (le marquage ajoute-t-il un deficit), et non le compte d'items vus.

## Impact

- `squad/conformite/releves-s20/budget-vertical-avant-apres-marquage.json` : cree.
- `squad/conformite/releves-s20/budget-vertical-cp-nature-mot-744-mesure-hors-catalogue.json` :
  cree.
- `squad/sprints/s20-mot-cible-designe-et-conception-de-la-saisie/validation-ines-mot-designe-2rem.md`
  (ce fichier) : cree.
- Aucun fichier sous `adapters/`, `domain/` ni `application/` n'a change ;
  `adapters/adapter-content/src/main/resources/catalogue.yaml` a ete modifie puis restaure dans le
  worktree, jamais commite.
