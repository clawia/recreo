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
- **Statut global** : **VALIDÉ SOUS RÉSERVE** le 2026-10-02 par `nadia-prof` (s47, instance B,
  second passage), deux réserves. Historique : **REFUSÉ** le 2026-10-02 (premier passage, un motif,
  item 5), refus conservé ci-dessous. Fiche ouverte par ce premier verdict : aucune fiche n'existait
  pour ce jeu.
- **Dernière mise à jour** : 2026-10-03 (troisième passage, s48, instance A) : **VALIDÉ SOUS
  RÉSERVE**. Les deux réserves du second passage sont levées ; une réserve neuve, ouverte par ma
  propre forme de l'indice de l'item 7. Les verdicts précédents restent écrits tels quels.
  Quatrième passage, s49, le même jour : **VALIDÉ**. La réserve de l'indice de l'item 7 est levée.

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

---

## Bloc de validation pédagogique - second passage - nadia-prof UNIQUEMENT

- **Verdict** : **VALIDÉ SOUS RÉSERVE**
- **Date** : 2026-10-02
- **Auditrice** : `nadia-prof`, instance B (`DEC-0104`), second passage.
- **Audit joué** : **oui**, paquet entier.
- **Contenu audité** : `cp-emc-alerte.yaml` après `2e22ca0a` (`lea-dev`, fusion `2c4e36d2`).

**Conclusion, en une phrase** : le motif du premier passage est levé - l'item 5 décrit un acte de
l'inconnu qui justifie l'alerte et n'a qu'une réponse défendable -, les deux points non bloquants
sont repris, et le Done pédagogique d'`EMC-CP-14` est prononcé avec deux réserves sur des indices.

### 1. Ce que j'ai fait

Worktree dédié `s47-nadia-audit-b2`, fondé sur `round-two` à `c400ac2a`, lignée vérifiée par
`outils/verifier-lignee.sh round-two`. Jar construit par `bash outils/mvn-jdk21.sh -B package
-DskipTests` (Maven sur le JDK 21.0.10 de la toolchain), lancé sur un port dédié. Je n'ai lancé
aucun job de la CI à ce passage et je n'en déduis rien ; le Done technique reste celui de
`yanis-qa`, et `lea-dev` déclare `mvn -pl adapters/adapter-content -am test` vert dans son commit,
mesure que je n'ai pas refaite.

**Sept parties complètes de dix manches** par appels HTTP réels : une juste de bout en bout, une
avec trois erreurs à chaque manche jusqu'à la révélation, cinq mélangées (70 manches : 25 justes du
premier coup, 10 après une erreur, 13 après deux, 22 avec révélation). **Les onze items ont été
tirés et joués**, et à chaque manche les trois boutons servis étaient exactement la bonne réponse
et les deux distracteurs du paquet. Les onze indices ont été lus à l'écran après une erreur. Fin
de partie : la question ratée est rappelée avec sa réponse.

### 2. Motif du premier passage - levé

Item 5 servi : *"Un inconnu propose à Hugo de le suivre. Que doit-il faire ?"* - bonne réponse
*"refuser et le dire à un adulte"*, distracteurs *"le suivre pour voir où il va"* et *"ne rien dire
à personne"*, indice *"Suivre un inconnu est dangereux, même s'il semble gentil."*

- **La situation justifie l'alerte** : l'acte (proposer de le suivre) est le geste que la
  prévention vise. C'est la forme que j'avais écrite moi-même en section 7 ; elle est reprise mot
  pour mot, je lève donc sans relever la barre.
- **`EMC-5`** : une seule proposition défendable. *"ne rien dire à personne"* n'inclut pas le refus
  et ne prévient personne ; ce n'est plus une conduite juste.
- **L'indice** porte sur l'acte et non sur le fait qu'un inconnu parle. Règle exacte.
- Dix mots, une question en seconde phrase (`LON-1`, `LON-2`).

### 3. Points non bloquants du premier passage - repris

| Point (section 6) | Tenu | Ce que j'ai vu à l'écran |
|---|---|---|
| 1. Lieu de l'item 7 | **oui** | *"... sous un banc dans la cour."* |
| 2. Vocabulaire des indices | **oui** | *"urgences médicales"*, *"agression"*, *"intervient"*, *"interviennent"*, *"signalé"*, *"imprévisible"* ont disparu des onze indices servis |

### 4. Le reste du paquet, rejoué

Items 1 à 4 et 6 à 11 rejoués, verdicts du premier passage confirmés : `EMC-7` et ma décision Q4
tenues (15, 17, 18 nus, aucun 112 servi, service nommé et jamais une situation), `EMC-1`, `EMC-6`,
`FER-1` à `FER-3`, biais et ton. Les indices réécrits (*"Le SAMU aide les personnes très malades ou
très blessées."*, *"La police secours vient aider en cas de danger."*, *"Les pompiers viennent
aider en cas d'incendie ou d'accident."*, *"Un animal perdu peut mordre si on s'approche trop
près."*) sont exacts et lisibles par un enfant de CP accompagné.

### 5. Réserves - nouvelles et datées du 2026-10-02, non vues au premier passage

1. **Item 7, l'indice prescrit un geste ambigu.** *"Un objet qu'on ne connaît pas doit être montré à
   un adulte."* Pour un sac oublié, un enfant de six ans peut comprendre qu'il doit le prendre et
   l'apporter. La conduite juste est de ne pas y toucher et de prévenir. L'indice précédent
   (*"signalé"*) était exact ; c'est la simplification que j'avais demandée qui a introduit
   l'ambiguïté. Exemple de forme : *"Un sac oublié, on n'y touche pas : on prévient un adulte."*
   La bonne réponse (*"prévenir un adulte"*) et les deux distracteurs sont justes : ce n'est pas un
   motif de refus.
2. **Item 11, *"apeuré"*.** Mot au-dessus du CP, présent dès le premier passage et que je n'avais
   pas relevé. Exemple : *"Hugo voit dans la rue un chien perdu qui a peur."* (au plus quinze mots).

`camille-po` peut en faire une story ; elles ne bloquent pas la ligne.

### 6. Pour `camille-po`

**Done pédagogique d'`EMC-CP-14` prononcé, sous les deux réserves de la section 5.** La ligne de
matrice peut passer à `couvert-valide`.

### 7. Ce dont je reste la seule garde sur cette ligne

Inchangé depuis le premier passage (section 8 ci-dessus) : Q4 règle 2, `EMC-5` sur les items de
situation, et l'exactitude des conduites prescrites et des indices - c'est cette dernière qui a
laissé passer la réserve 1, avec une CI verte.

**Identifiants** : aucun neuf, aucun renommé.

**Signé** : `nadia-prof`, s47, instance B, second passage, 2026-10-02.

---

## Bloc de validation pédagogique - troisième passage - nadia-prof UNIQUEMENT

- **Verdict** : **VALIDÉ SOUS RÉSERVE**
- **Date** : 2026-10-03
- **Auditrice** : `nadia-prof`, instance A (`DEC-0106`), troisième passage.
- **Audit joué** : **oui**, paquet entier.
- **Contenu audité** : `cp-emc-alerte.yaml` à `4b6a6294` (`sofia-sm`, casquette dev).

**Conclusion, en une phrase** : les deux réserves du second passage sont levées, chacune par la forme
que j'avais écrite, et l'une de ces formes, la mienne, ouvre une réserve neuve que je date et que je
m'impute.

### 1. Ce que j'ai fait

Arbre `round-two` à `bc1e7835`. Jar du `clean verify` de `yanis-qa` à ce commit, YAML du jar vérifié
identique à l'arbre, lancé sur un port dédié servi par mon propre processus. Aucun job de la CI lancé
par moi. **Huit parties complètes de dix manches** par appels HTTP réels, une erreur sur une manche sur
deux : **les onze items tirés**, les items 7 et 11 joués plusieurs fois, avec et sans erreur.
`git show 4b6a6294` : l'indice de l'item 7 et l'énoncé de l'item 11, rien d'autre. Rendu non revu au
navigateur : aucun gabarit n'a changé.

### 2. Les deux réserves du second passage - levées

| Réserve | Barre écrite le 2026-10-02 | Servi | Tenu |
|---|---|---|---|
| 1. Item 7, geste ambigu | *"Un sac oublié, on n'y touche pas : on prévient un adulte."* | mot pour mot, deux-points précédé d'un blanc insécable | **oui** |
| 2. Item 11, *"apeuré"* | *"Hugo voit dans la rue un chien perdu qui a peur."* | mot pour mot, suivi de *"Que doit-il faire ?"* : quatorze mots, deux phrases | **oui** |

L'enfant ne peut plus comprendre qu'il doit prendre le sac pour l'apporter : *"on n'y touche pas"*
écarte les deux distracteurs (*"ouvrir le sac"*, *"garder le sac"*). Je lève les deux.

### 3. Réserve neuve, datée du 2026-10-03 - et c'est ma forme qui l'ouvre

**Item 7, l'indice nomme la bonne réponse.** Bonne réponse servie : *"prévenir un adulte"*. Indice
servi après une erreur : *"... on prévient un adulte."* C'est la forme que j'ai écrite moi-même comme
exemple au second passage, et elle a ce défaut : après une seule erreur, l'enfant retrouve les mots de
l'indice dans une des deux propositions restantes. C'est la fuite que j'ai relevée le 2026-10-02 sur
`AUT-CP-R1` (*"il répond en reconnaissant un mot"*) ; je me l'applique. **Je ne la reproche pas au
paquet** : `sofia-sm` a recopié ce que je demandais. **Ce qui lève** : l'indice de l'item 7 ne
contient plus *prévenir* ni *prévient* ; la première moitié suffit, *"Un sac oublié, on n'y touche
pas."* Non bloquante : la conduite est juste, l'indice aussi.

### 4. Pour `camille-po`

**`EMC-CP-14` reste `couvert-valide`.** Les deux réserves du second passage sont fermées ; une
réserve neuve, section 3, à faire en story.

### 5. Ce dont je reste la seule garde sur cette ligne

Inchangé (section 8 du premier passage), plus un point que ce passage démontre : **qu'un indice ne
nomme pas sa propre réponse**. Aucun test ne le lit, et cette fois c'est la forme de l'auditrice qui
l'a introduit.

**Identifiants** : aucun neuf, aucun renommé. La réserve de la section 3 suit la convention de cette
fiche, qui n'en a jamais numéroté.

**Signé** : `nadia-prof`, s48, instance A, troisième passage, 2026-10-03.

---

## Bloc de validation pédagogique - quatrième passage - nadia-prof UNIQUEMENT

- **Verdict** : **VALIDÉ**
- **Date** : 2026-10-03
- **Auditrice** : `nadia-prof`, instance A, s49, quatrième passage.
- **Audit joué** : **oui**, paquet entier.
- **Contenu audité** : `cp-emc-alerte.yaml` à `db14e371` (`lea-dev`).

**Conclusion, en une phrase** : la réserve du troisième passage est levée par la forme que j'avais
écrite, et `EMC-CP-14` reste `couvert-valide` sans réserve ouverte.

### 1. Ce que j'ai fait

Arbre `round-two` à `bbfdde17`. Jar construit par
`bash outils/mvn-jdk21.sh -B -pl bootstrap -am package -DskipTests`, YAML du jar vérifié identique à
l'arbre, lancé sur un port dédié servi par mon propre processus. Aucun job de la CI lancé par moi.
**Huit parties complètes de dix manches** par appels HTTP réels : **les onze items tirés**, l'item 7
joué avec une erreur. `git show db14e371` : l'indice de l'item 7, rien d'autre dans le YAML. Rendu non
revu au navigateur : aucun gabarit n'a changé.

### 2. La réserve du troisième passage - levée

| Barre écrite le 2026-10-03 (s48) | Servi | Tenu |
|---|---|---|
| l'indice de l'item 7 ne contient plus *prévenir* ni *prévient* ; *"Un sac oublié, on n'y touche pas."* | *"Un sac oublié, on n'y touche pas."*, mot pour mot | **oui** |

Joué : après *"garder le sac pour elle"*, l'indice écarte ce distracteur et *"ouvrir le sac"* sans
nommer la bonne réponse. La conduite et l'indice restent justes.

### 3. Ce dont je reste la seule garde sur cette ligne

Inchangé (section 5 du troisième passage), avec une précision : l'assertion ajoutée à
`PaquetCpEmcAlerteTest` (aucun indice ne contient une forme de *prévenir*) garde ce mot, pas la
propriété. Un indice qui écrirait *"dis-le à un adulte"* sur un item dont la réponse est *"le dire à
un adulte"* passerait. Qu'un indice ne nomme pas sa propre réponse reste à moi.

**Identifiants** : aucun neuf, aucun renommé.

**Signé** : `nadia-prof`, s49, instance A, quatrième passage, 2026-10-03.
