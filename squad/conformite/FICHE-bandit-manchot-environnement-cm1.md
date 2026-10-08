# Fiche de conformite - bandit-manchot-environnement-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-environnement-activite-humaine.yaml`
  (12 items, type `question`), ecrit par `lea-dev` (commit `7854d8d3`, session de contenu de s53), non
  modifie au branchement (commit `fb42d5f1`).
- **Entree de catalogue** : `bandit-manchot-environnement-cm1`, titre servi *"L'être humain et
  l'environnement"*, consigne servie *"Lis bien, puis dis quel est l'effet, ou quel geste préserve."*,
  `nombreDeManches: 10`.
- **Ligne declaree** : `SCI-CM1-18`. **Story** : `S54-BR-SCI-CM1-18` (`DEC-0114`).
- **Reprise de** : `cm1-sciences-activite-humaine`, jeu `bandit-manchot-activite-humaine-cm1`, **REFUSE
  deux fois le 2026-10-06** (`squad/conformite/FICHE-bandit-manchot-activite-humaine-cm1.md`, motif de
  l'item 11). Cette fiche juge le paquet neuf ; les deux refus restent ecrits dans l'ancienne.
- **Conditions opposables** : `FER-1` a `FER-3`, `SCI-1` a `SCI-4`, `HIS-3`, `CON-1`, `CYC-1`
  (`squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md`,
  sections 2.2 ligne 18, 2.3, 2.5 et 2.6), `DEC-0064`, et la barre du motif de l'ancienne fiche (S2-3).
- **Statut global** : **VALIDE**, prononce le 2026-10-08 par `nadia-prof` (s54, instance B).
- **Derniere mise a jour** : 2026-10-08.

---

## Bloc de validation pedagogique

### Verdict du 2026-10-08 (`nadia-prof`, s54, instance B) : **VALIDE**

**Done pedagogique prononce le 2026-10-08.** `camille-po` peut passer `SCI-CM1-18` a `couvert-valide`,
**sans mention de couverture** (aucune n'est due pour cette ligne). Le motif maintenu deux fois a s51 est
leve, a la barre ecrite alors et reprise sans changement. Aucune reserve.

#### 1. Ce que j'ai fait

**Audit complet, pas degrade.** Worktree `s54-nadia-b`, a `95214218`. `bash outils/mvn-jdk21.sh -B clean
verify` : `BUILD SUCCESS` (job `build` seul ; `prefixes`, `chemins`, `outils` non lances). Jar de ce
build, paquet et catalogue embarques identiques octet pour octet aux fichiers commites (`cmp`), lance
sur un port dedie a l'instance B, arrete par son PID. Joue par les routes servies.

**Parties jouees** : quatre parties de dix manches, chaque manche jouee avec les deux distracteurs
presses puis la revelation. **Les douze items ont ete tires, et chacun de leurs 24 distracteurs presse au
moins une fois.** Item 11 lu d'abord, avec l'erreur *"plus de gaz naturel"*.

#### 2. Ancrage au programme

`SCI-CM1-18` : *"Identifier un effet de l'activité humaine sur l'environnement et un geste de
préservation des ressources."* Items 1 a 6 : un effet ; items 7 a 12 : un geste. **C'est bien cette
competence**, sur les deux taches ecrites d'avance. Le titre neuf ne reprend pas celui du jeu retire.

#### 3. Le motif de s51, a sa barre

> Barre (S2-3, inchangee) : *"que la question pose le critere de l'indice, de sorte qu'une seule
> proposition y reponde"*.
>
> Item servi : *"Le pétrole, le charbon et le gaz naturel sont des énergies fossiles qui ne se
> renouvellent pas à l'échelle humaine. Quel geste préserve ces énergies ?"* - bonne reponse *"produire
> de l'électricité avec le vent et le soleil"*, distracteurs *"produire de l'électricité avec plus de gaz
> naturel"* et *"extraire plus vite ce qu'il en reste dans le sous-sol"*, indice *"Une énergie qui ne se
> renouvelle pas s'épuise quand on l'utilise."*
>
> L'enonce nomme le gaz naturel parmi *"ces énergies"* : brûler plus de gaz ne les preserve pas. A
> l'ecran, *"avec plus de gaz naturel"* se lit *davantage* (la preposition *"avec"* ecarte la lecture *ne
> ... plus*). L'indice juge sur le critere que l'enonce pose. **Une seule proposition repond. Leve.**

#### 4. Les autres items

- **Item 5** : l'indice *"Ce gaz n'est pas de l'oxygène et ne rend pas l'air plus pur."* remplace celui
  que j'avais observe ecrit pour l'ancienne question ; il est exact et repond au distracteur de l'oxygene.
- **Les dix autres items** sont identiques a ceux du second passage de s51, dont les deux points non
  bloquants etaient pris ; rejoues ici, aucun constat neuf.
- **Exactitude** : douze bonnes reponses et douze indices exacts ; aucun distracteur vrai.
- **`SCI-2`** : aucune adresse a l'enfant, aucun foyer, aucun geste domestique ; gestes collectifs a
  l'infinitif. Verifie item par item a l'ecran, au-dela des jetons d'adresse que le test voit.
- **`CYC-1`** : aucun paquet de cycle 2 n'a pour objet l'effet de l'activite humaine sur
  l'environnement (recherche dans `contenu/cp-*.yaml` et `contenu/ce1-*.yaml`). Confirme.
- **Niveau** : *"énergies fossiles"* est nomme et defini dans l'enonce meme de l'item 11 ; lisible au CM1.

#### 5. Ce dont je reste la seule garde

`PaquetCm1SciencesActiviteHumaineTest` (recible sur ce paquet par `fb42d5f1`) tient la forme. **Aucun
automate ne verifie** qu'une seule proposition reponde a la question ecrite (le defaut de s51 a traverse
deux `clean verify` verts), l'exactitude d'un fait d'environnement, qu'un indice ne donne pas la reponse
par elimination, la part de `SCI-2` que les jetons d'adresse ne voient pas, `CYC-1`. Verifie ici en jouant.

#### 6. Tarif et identifiants

Tarif reel : **0,5** (rejeu, 2 items sur 12 changes, `PAR-10`) ; ce n'est pas un lot. Aucun identifiant
neuf.
