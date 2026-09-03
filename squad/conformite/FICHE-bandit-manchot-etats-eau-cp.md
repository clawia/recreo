# Fiche de conformite - bandit-manchot-etats-eau-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-qlm-etats-eau.yaml`.
- **Entree de catalogue** : `bandit-manchot-etats-eau-cp`, titre servi *"L'eau solide ou liquide"*, `nombreDeManches: 7`.
- **Statut global** : **VALIDE** (Done pedagogique prononce le 2026-09-03). Ligne de matrice `non couvert` -> **`en cours`** - premier paquet d'une matiere neuve, la ligne ne se ferme pas.
- **Arbitrage prealable de `nadia-prof`** : `ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md`, sections 4 (ligne 5) et 7.1.
- **Derniere mise a jour** : 2026-09-03 (**verdict de `nadia-prof` apres avoir joue**, s27).

## Competence visee

`Reconnaître les états de l'eau (solide, liquide) et les changements d'état observables.`
(Questionner le monde, CP, Le vivant, la matière et les objets).

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `d259b5f`, jar reconstruit et lance, parties reelles par appels
HTTP contre l'application, **y compris en me trompant volontairement**. Ce n'est pas un audit
degrade.

### 1. Exactitude - huit items sur huit, et la ligne est servie en entier

glace, glacon, neige -> **solide** ; jus, eau du robinet, pluie -> **liquide** ; *"quand la glace
fond"* -> **liquide** ; *"quand l'eau liquide gele"* -> **solide**. **Quatre et quatre.**

**Et la ligne a deux verbes, tous les deux servis** : *reconnaitre les etats* (les six premiers
items) **et** *les changements d'etat observables* (les deux derniers, fondre et geler).
**Aucune mention de couverture partielle n'est due sur cette ligne** - je le dis explicitement,
parce que j'en ai exige sur d'autres et qu'il faut savoir dire quand il n'y en a pas.

### 2. Le troisieme etat absent - **je confirme le choix de `lea-dev`**

Le paquet ne parle ni de vapeur ni d'etat gazeux. **C'est correct** : la ligne du referentiel que
j'ai ecrite dit *"(solide, liquide)"* et n'en nomme que deux. Un paquet de CP qui ajouterait la
vapeur travaillerait une competence de CE2, pas celle-ci. **Servir exactement la ligne, ni plus ni
moins, est ce que je demande.**

### 3. Deux propositions - meme motif qu'a `cp-qlm-vivant-non-vivant`

*Solide* contre *liquide* est une opposition binaire par construction de la ligne. Precedent
mesure et valide par moi trois fois (`cp-nature-mot`, `cm1-homophones`, `cp-son-dans-mot`).
**Accepte.**

### 4. Lisibilite - mesuree

Les huit enonces font **6, 6, 6, 6, 7, 9, 9 et 9 mots**, mediane 6,5. **Aucun outlier**, aucun mot
hors niveau. Le vocabulaire (*glace*, *glacon*, *neige*, *pluie*, *robinet*, *fondre*, *geler*)
est entierement du quotidien d'un enfant de six ans.

### 5. Joue jusqu'au bout

Partie complete, **7 manches**, ecran de fin atteint. Les indices donnent le **critere physique**
et non la reponse : *"on peut la tenir dans la main sans qu'elle coule"*, *"il coule et prend la
forme du verre"*. **C'est la meilleure serie d'indices des trois paquets de cette matiere** : elle
enseigne la propriete qui definit l'etat, ce qui est exactement la competence.

### 6. Reserves non bloquantes

- **`EAU-R1` - l'indice de l'item *"quand la glace fond"* est abstrait.** *"Fondre, c'est passer du
  solide a ce deuxieme etat"* demande a l'enfant de tenir *"ce deuxieme etat"* comme un renvoi, ce
  qui est une charge de langage inutile ici. Piste : *"Quand la glace fond, elle coule."* - meme
  information, meme critere que les autres indices du paquet, et coherent avec eux.
- **`EAU-R2`** - meme reserve que `VIV-R1` sur `nombreDeManches: 7` sans decision ecrite.

