# Fiche de conformité - bandit-manchot-alerte-cp

- **Mécanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-emc-alerte.yaml`
  (11 items, type `question`), écrit par `lea-dev` (commit `b75143f0`).
- **Entrée de catalogue** : `bandit-manchot-alerte-cp`, titre servi *"Donner l'alerte"*, consigne
  servie *"Lis bien, puis dis le bon numéro, ou ce qu'il faut faire."*, `nombreDeManches: 10`.
- **Ligne déclarée** : `EMC-CP-14`. **Story** : `S47-EMC-ALERTE-CP` (`docs/backlog/backlog.md`,
  section "Sprint 47").
- **Compétence programme visée** : enseignement moral et civique, CP (cycle 2), domaine "Construire
  une culture civique" - `squad/programme/enseignement-moral-et-civique/competences-cp.md`,
  `EMC-CP-14` : *"Connaître les gestes d'alerte les plus simples et savoir donner l'alerte (prévenir
  un adulte, numéro d'urgence)."*
- **Conditions opposables** : `EMC-1`, `EMC-2`, `EMC-5`, `EMC-6`, `EMC-7` (même fichier, posées le
  2026-09-03), ma décision Q4 du 2026-10-02
  (`squad/conformite/ARBITRAGE-PREALABLE-s47-trois-questions-de-theo-archi.md`, règles 1 à 5),
  `FER-1`, `FER-2`, `FER-3`. `CYC-1` non due (ligne de CP, planning de s47, section 3).
- **Lien décision** : `DEC-0104`.
- **Statut global** : **REFUSÉ** le 2026-10-02 par `nadia-prof` (s47, instance B), un motif (item 5).
  Fiche ouverte par ce verdict : aucune fiche n'existait pour ce jeu.
- **Dernière mise à jour** : 2026-10-02.

---

## Bloc de validation pédagogique - nadia-prof UNIQUEMENT

- **Verdict** : **REFUSÉ**
- **Date** : 2026-10-02
- **Auditrice** : `nadia-prof`, instance B (`DEC-0104`).
- **Audit joué** : **oui**, paquet entier.

**Motif, en une phrase** : l'item 5 (*"Un inconnu parle aux enfants devant l'école. Que doit faire
un enfant qui le voit ?"*) décrit une situation sans aucun danger, couronne *"le dire tout de suite à
un adulte"* et affirme dans son indice une règle fausse - *"Une personne inconnue qui s'adresse aux
enfants doit toujours être signalée."* -, si bien que *"ne rien dire à personne"* est aussi
défendable (`EMC-5`).

### 1. Ce que j'ai fait

Worktree dédié `s47-nadia-audit-b`, fondé sur `round-two` à `eef35179`, qui porte le paquet
(`b75143f0`, fusion `7d3cc9ee`). Jar construit par `bash outils/mvn-jdk21.sh -B package
-DskipTests`, lancé sur le JDK 21 du poste, sur un port dédié distinct de celui de l'instance A.
Done technique acquis par `yanis-qa` sur le job `build`
(`squad/sprints/s47-tables-ce1-decouverte-cp-et-emc/rapport-qualite.md`, conclusion). J'ai relancé
moi-même, avec `-am`, sur le module `adapter-content` seul : `PaquetCpEmcAlerteTest` (8),
`LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest` (10) et
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (28), 0 échec. Je n'ai lancé aucun autre
job de la CI et je n'en déduis rien.

**Sept parties complètes de dix manches** par appels HTTP réels : une juste de bout en bout, une
avec trois erreurs à chaque manche jusqu'à la révélation, cinq mélangées (juste du premier coup,
après une, deux ou trois erreurs). **Les onze items ont été tirés et joués.** Après une erreur,
l'écran dit *"Ce n'est pas ça. Essaie encore !"* et affiche l'indice ; après la troisième, il dit
*"Appuie sur la bonne réponse :"* et la montre. Un enfant bloqué avance toujours. Aucun écran ne dit
à l'enfant qu'il a prévenu quelqu'un.

### 2. Ancrage au programme

La compétence est la bonne. Les deux éléments de la parenthèse du libellé sont servis : le numéro
d'urgence (items 1 à 3, une bonne réponse par numéro) et *prévenir un adulte* (items 4 à 11). Les
deux tâches de `FER-3` sont distinctes. Onze items, au-dessus du plancher de dix (`FER-2`). Le
paquet travaille l'alerte et non le rôle des services, que `cp-emc-roles-ecole-commune.yaml`
(`EMC-CP-13`) sert déjà ; je l'ai relu, aucun item ne le contredit.

### 3. Ma décision Q4, vérifiée règle par règle

| Règle | Tenue | Ce que j'ai vérifié |
|---|---|---|
| Le 112 n'apparaît nulle part | **oui** | Aucun énoncé, aucune proposition, aucun indice du paquet ; aucun des écrans des sept parties. Le seul 112 du fichier est dans son commentaire de tête, que l'enfant ne voit pas. `PaquetCpEmcAlerteTest.le112NApparaitNullePart` le garde |
| 1. Trois propositions `"15"`, `"17"`, `"18"`, nues | **oui** | Items 1 à 3, rendus tels quels à l'écran |
| 2. L'énoncé nomme le service, jamais une situation | **oui** | *"Quel est le numéro du SAMU ?"*, *"... de la police secours ?"*, *"... des pompiers ?"* |
| 3. Correspondances exactes, source au marqueur | **oui** | 15 SAMU, 17 police secours, 18 pompiers. Chaque item à numéro porte `# EMC-7:` citant service-public.fr |
| 4. Une situation décrite ne sert que *prévenir un adulte* | **oui** | Items 4 à 11, aucun numéro dans leurs propositions |
| 5. Chaque numéro bonne réponse d'au moins un item | **oui** | Un item chacun ; un tirage de dix sur onze en garde toujours au moins deux |

**Registre `PAQUETS_A_ITEMS_ECARTES`** : l'entrée `"cp-emc-alerte", 8` correspond aux huit items de
situation (4 à 11), dont les trois propositions sont du texte libre. Les trois items à numéro restent
dans le domaine du garde-fou de rang. Compte vérifié sur le paquet, item par item.

### 4. Motif - l'item 5 n'a pas une seule réponse défendable

L'énoncé ne contient **aucun signal de danger** : un adulte que l'enfant ne connaît pas parle à des
enfants devant l'école. C'est la situation ordinaire de toutes les sorties d'école : les parents et
grands-parents des camarades, un animateur, une personne nouvelle de l'équipe sont des inconnus pour
un enfant donné. Dans cette situation, *"ne rien dire à personne"* est une conduite juste ; l'item la
compte fausse (`EMC-5`).

L'indice aggrave l'item : *"Une personne inconnue qui s'adresse aux enfants doit toujours être
signalée."* Cette règle est fausse telle qu'écrite, et elle enseigne à un enfant de six ans que tout
adulte inconnu qui lui parle est un danger. Le message de prévention qui est juste porte sur un acte
(suivre un inconnu, monter dans sa voiture, accepter ce qu'il donne), pas sur le fait qu'il parle.

**Une erreur de contenu dans un exercice scolaire est un motif de refus à elle seule.** Ce motif ne
relève aucune barre : `EMC-5` est posée depuis le 2026-09-03.

### 5. Conditions, vérifiées une par une

| Condition | Tenue | Ce que j'ai vérifié |
|---|---|---|
| `EMC-5` | **non, item 5** | Section 4. Sur les dix autres items, une seule proposition est défendable |
| `EMC-7` | **oui** | Section 3. Aucun item n'admet deux numéros justes |
| `EMC-1` | **oui** | Toutes les situations sont celles d'un personnage nommé (Léa, Hugo, Aya, Malo) ou d'*"un enfant"* ; aucune ne demande à l'enfant ce qu'il vit chez lui |
| `EMC-6` | **oui** | Petit frère, cuisine, parents au magasin : le contexte est dans l'énoncé et appartient au personnage |
| `FER-1`, `FER-2`, `FER-3` | **oui** | Section 2 |
| Biais et ton | **oui** | Quatre prénoms, filles et garçons dans les mêmes rôles ; aucun ton infantilisant |
| Exactitude des dix autres items | **oui** | Saignement de nez qui dure, odeur de gaz sans allumer la lumière ni ouvrir le four, enfant perdu qui demande de l'aide dans le magasin, animal apeuré qu'on n'approche pas : conduites justes à ma connaissance |

### 6. À reprendre dans le même passage, sans être des motifs de refus

1. **Item 7, le lieu.** L'énoncé dit *"un sac oublié sous un banc"* sans dire où ; l'indice dit
   *"Un objet inconnu trouvé à l'école"*. Mettre le lieu dans l'énoncé (*"dans la cour"*).
2. **Vocabulaire des indices, au-dessus du CP.** *"imprévisible"* (item 11), *"urgences médicales"*
   (item 1), *"agression"* et *"intervient"* (item 2), *"interviennent"* (item 3), *"signalé"*
   (item 7). Un adulte est présent au CP, mais `DEC-0046` ne dispense d'aucune exigence de
   lisibilité. Exemple de forme : *"Un chien qui a peur peut mordre."*
3. **Observation, sans demande de correction obligatoire.** Sur les huit items de situation, le mot
   *"adulte"* est dans chaque bonne réponse et dans aucun des seize distracteurs. C'est la
   conséquence de ma règle Q4.4 et c'est la compétence elle-même, donc ce n'est pas un défaut du
   lot. Un distracteur qui nomme un adulte de façon fausse (*"attendre qu'un adulte passe par
   hasard"*) rendrait la tâche plus discriminante.

### 7. Ce qui doit changer pour que le lot repasse

| Fait | À qui |
|---|---|
| Item 5 : le retirer (il reste dix items, `FER-2` tenue), ou le réécrire avec un acte de l'inconnu qui justifie l'alerte (*"Un inconnu propose à Hugo de le suivre. Que doit-il faire ?"*, bonne réponse du type *"refuser et le dire à un adulte"*), et remplacer l'indice par une règle exacte qui porte sur cet acte | `camille-po` en fait la story, développeur affecté par `sofia-sm` |
| Les points 1 et 2 de la section 6 | même story |

Je rejouerai le paquet entier à son retour, pas seulement l'item corrigé.

### 8. Ce dont je reste la seule garde sur cette ligne

1. **Q4, règle 2** : qu'un item à numéro nomme un service et jamais une situation. Aucun test ne lit
   le sens d'un énoncé.
2. **`EMC-5` sur les items de situation** : qu'une situation décrite justifie réellement l'alerte.
   C'est ce qui a manqué à l'item 5, et la CI est verte.
3. **L'exactitude des conduites prescrites et des indices** (gaz, saignement, enfant perdu).

**Identifiants** : aucun neuf, aucun renommé.

**Signé** : `nadia-prof`, s47, instance B, 2026-10-02.
