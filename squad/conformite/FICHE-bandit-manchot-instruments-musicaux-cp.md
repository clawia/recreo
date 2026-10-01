# Fiche de conformité - bandit-manchot-instruments-musicaux-cp

- **Mécanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-musique-instruments.yaml`
  (11 items, type `question`), écrit par `sofia-sm`, casquette dev (commit `0d7fb883`, fusion
  `b070d82b`).
- **Entrée de catalogue** : `bandit-manchot-instruments-musicaux-cp`, titre servi *"Les instruments
  de musique"*, consigne servie *"Lis bien, puis dis l'instrument, ou sa famille."*,
  `nombreDeManches: 10`.
- **Ligne déclarée** : `MUS-CP-05`. **Story** : `S45-MUS-INSTRUMENTS` (`docs/backlog/backlog.md`,
  section "Sprint 45").
- **Conditions opposables** : `MUS-1`, `MUS-2` (`squad/programme/education-musicale/competences-cp.md`),
  `MUS-CP-R1` sous sa forme générale et ma réponse à Q3
  (`squad/conformite/ARBITRAGE-PREALABLE-s45-cinq-lots.md`, section 3), `FER-1` à `FER-3`.
- **Statut global** : **VALIDE SOUS RÉSERVE**, prononcé le 2026-10-01 par `nadia-prof` (s45,
  instance A). Fiche ouverte par ce verdict : aucune fiche n'existait pour ce jeu.
- **Dernière mise à jour** : 2026-10-01.

---

## Bloc de validation pédagogique

### Verdict du 2026-10-01 (`nadia-prof`, s45, instance A) : **VALIDE SOUS RÉSERVE**

**Done pédagogique prononcé.** `camille-po` peut passer `MUS-CP-05` à `couvert-valide`, **avec la
mention de la section 6, qui est une condition de ce Done**. Les réserves `MUS-CP-R2` et `MUS-CP-R3`
ne bloquent pas le mouvement de ligne.

#### 1. Ce que j'ai fait

**Audit complet, pas dégradé.** Jar construit sur `round-two` à `9fcae7dc`, dans un worktree dédié
(`bash outils/mvn-jdk21.sh -B clean package -DskipTests`), lancé sur le JDK 21 de la toolchain. **Six
parties complètes** de dix manches, même protocole que sur les deux autres jeux de l'instance A :
un tiers des manches juste, un tiers après une erreur, un tiers après trois erreurs. **Les onze items
ont été tirés et joués**, les propositions affichées conformes au paquet.

Retour après erreur, révélation après la troisième et écran de fin conformes aux autres jeux.

**Vu en jouant** : les items 7 et 10 ont le même énoncé (*"Lequel de ces instruments est une
percussion ?"*), comme les items 8 et 11 (*"... est à cordes ?"*), et ils sont sortis l'un après
l'autre sur la première partie. Chaque item n'a qu'une bonne réponse parmi ses trois propositions, et
la seconde fois l'enfant cherche un autre instrument de la même famille : je ne le compte pas en
réserve.

**Vérification complémentaire des garde-fous**, parce que la story les donnait pour la garde de
`MUS-1` et de `MUS-CP-R1` : section 5.

#### 2. Ancrage au programme

`MUS-CP-05` : *"Identifier quelques instruments de musique et les familles auxquelles ils
appartiennent (cordes, vents, percussions)."* **C'est bien la moitié connaissance de cette
compétence** : six items demandent la famille d'un instrument nommé, cinq l'instrument d'une famille
nommée. Aucun item ne glisse vers `MUS-CP-11` (vocabulaire musical) ni vers `MUS-CP-04` (caractère
d'un son).

#### 3. Conditions, vérifiées une par une

| Condition | Tenue | Ce que j'ai vérifié |
|---|---|---|
| `MUS-1` | **oui** | Aucun item ne suppose une écoute. Aucun texte ne contient `écout` ni `entend`, cherché sur les onze énoncés, les trente-trois propositions et les onze indices. *"fait le son"* (item 4) et *"pour le faire sonner"* (item 7) disent comment l'instrument produit un son, pas à quoi il ressemble : en effaçant ces mots, la réponse se trouve encore |
| `MUS-2` | **oui** | Aucun `texteAVoixHaute`, aucun bloc adulte à l'écran sur les six parties. Ni `adulte` ni `fredonn`. **`frapp` est employé et doit l'être** : quatre indices de percussions (items 5, 6, 7, 10) disent qu'on frappe l'instrument, et le garde-fou du paquet ne rougit pas, `frapp` et `taper` ayant été retirés de son lexique interdit. Aucun texte ne demande à l'adulte ni à l'enfant de frapper un rythme |
| `MUS-CP-R1` et Q3 | **oui** | Aucun énoncé ne nomme une de ses propositions. La forme *"cordes ou vent ?"* n'est employée nulle part. Sur les items 7 à 11, la famille est dans l'énoncé et n'est pas une proposition, ce que ma réponse à Q3 admet |
| `FER-1` | **oui** | Les trois familles sont bonnes réponses de deux items chacune sur la tâche "instrument -> famille" : cordes (1, 2), vents (3, 4), percussions (5, 6), avec deux instruments distincts par famille |
| `FER-2` | **oui** | 11 items, au-dessus du plancher de 10 |
| `FER-3` | **oui** | Deux tâches ; la moins servie, "famille -> instrument", porte 5 items. Elle ne compte pas pour `FER-1`, comme le paquet le déclare |

#### 4. Niveau réel, exactitude, biais

- **Lisibilité** : énoncés de 7 ou 8 mots, une phrase, sous le plafond d'`ADR-0089`. *Archet*,
  *lames* et *maillets* n'apparaissent que dans les indices ; ce sont les mots de l'instrument.
- **Exactitude** : guitare et violon à cordes, trompette et flûte à vent, tambour et xylophone à
  percussion. La trompette est un cuivre, et les cuivres sont des vents dans le classement en trois
  familles du libellé : exact au niveau du CP. Le xylophone a des lames de bois : exact.
- **Biais et ton** : aucun.

#### 5. Ce que les garde-fous de ce paquet ne gardent pas

J'ai appliqué au paquet, dans le worktree et sans commit, deux mutations : l'indice de l'item 1
réécrit *"Écoute bien, elle a des cordes."*, et l'énoncé de l'item 3 réécrit *"La trompette est-elle
un instrument à cordes ou à vent ?"*, la forme exacte que Q3 refuse. Puis
`bash outils/mvn-jdk21.sh -B -pl bootstrap -am test` (périmètre : le réacteur jusqu'à `bootstrap`,
`-am` compris) : **1844 tests, 0 échec, `BUILD SUCCESS`**. Le paquet a été restauré ensuite, arbre
propre. Deux causes, lues dans `PaquetCpMusiqueInstrumentsTest` :

- le lexique interdit cherche `ecout` sans accent dans un texte intégralement accentué : *écoute* ne
  le contient pas. Le même défaut est dans `PaquetCpMusiqueVocabulaireTest` (s44). Mon verdict de s44
  sur `MUS-1` ne reposait pas sur ce test (j'avais cherché moi-même, sa fiche le dit), il reste juste ;
- l'assertion de `MUS-CP-R1` cherche la proposition entière (`les cordes`) dans l'énoncé, alors que la
  forme refusée nomme *cordes* et *vent* sans l'article ni le pluriel. Elle laisse passer exactement
  l'exemple que sa Javadoc dit refuser.

Le contenu servi est sain : c'est la garde annoncée qui ne garde pas. Réserve `MUS-CP-R3`.

#### 6. Mention de couverture, condition de ce Done

La story me demandait de confirmer que la mention est due et qu'elle intègre la fermeture conjointe
avec `MUS-CP-11`. **Elle est due.** La matrice porte, sur la ligne `MUS-CP-05`, cette mention **mot
pour mot** :

> *"familles d'instruments (cordes, vents, percussions) sur le nom écrit seul ; la reconnaissance
> auditive d'un instrument ou d'une famille reste non couverte"*

**Sur la ligne `MUS-CP-11`**, la dernière proposition de la mention écrite en s44 (*"et les familles
d'instruments (ligne 5) restent non couvertes"*) devient fausse à la fermeture de cette ligne.
`camille-po` la remplace, par un bloc daté qui n'efface rien, par : *"vocabulaire musical seul
(refrain, couplet, pulsation, silence), sur le mot écrit ; aucune compétence auditive, vocale ni
corporelle n'est servie"*. **Mention commune aux deux lignes**, celle que mon référentiel annonçait
pour le jour où elles fermeraient ensemble, à porter en tête de section ou sur les deux lignes au
choix de `camille-po` : *"vocabulaire musical et familles d'instruments seuls, sur le nom écrit ;
aucune compétence auditive ni vocale n'est servie"*.

#### 7. Les réserves

> **`MUS-CP-R2` - Items 1 et 2 : l'indice contient le mot de la bonne réponse.** Item 1 : *"Elle a
> des cordes que l'on pince ou que l'on gratte."* Item 2 : *"On fait vibrer ses cordes avec un
> archet."* Bonne réponse : `les cordes`. Après une erreur, il reste deux boutons et l'indice en
> nomme un : **l'enfant trouve sans rien savoir**. C'est la famille de `M2` et `M21`.
>
> **Correction à faire** : un indice qui dit comment on joue l'instrument sans écrire *cordes*
> (par exemple *"On la pince ou on la gratte avec les doigts."*, *"On le frotte avec un archet."*).
> Les indices des vents (*souffle*) et des percussions (*frappe*) demandent déjà de relier le geste à
> la famille : ils restent.
>
> **Pourquoi elle n'empêche pas `couvert-valide`** : le premier essai de ces deux items se fait sans
> indice, et il exige de savoir que la guitare et le violon ont des cordes. Le défaut ne joue qu'après
> une erreur, sur deux items sur onze.

> **`MUS-CP-R3` - Deux garde-fous de `PaquetCpMusiqueInstrumentsTest` ne mordent pas sur ce qu'ils
> annoncent** (section 5) : le lexique `MUS-1` est aveugle aux accents, l'assertion `MUS-CP-R1` est
> aveugle à la forme *"cordes ou à vent ?"*. Même défaut de lexique dans
> `PaquetCpMusiqueVocabulaireTest`. **Je décris le symptôme, la forme de la correction appartient à
> `theo-archi` et à l'auteure du test.** Ce que la correction doit faire rougir : les deux mutations
> de la section 5, rejouées.
>
> **Pourquoi elle n'empêche pas `couvert-valide`** : le contenu servi tient `MUS-1` et `MUS-CP-R1`,
> vérifié en jouant et en lisant. C'est le prochain paquet d'éducation musicale qui serait exposé.

Routage : les deux réserves à `sofia-sm`, auteure du paquet et du test, pour que `camille-po` en
fasse des stories.

#### 8. Ce dont je reste la seule garde

Tant que `MUS-CP-R3` n'est pas corrigée : **`MUS-1` sur un mot accentué et `MUS-CP-R1` sur toute
forme qui nomme une famille sans son article**. Et dans tous les cas : l'exactitude de la famille de
chaque instrument, et qu'un indice ne nomme pas la bonne réponse (`MUS-CP-R2`). Aucun automate ne
vérifie ces propriétés aujourd'hui.

---

**Signé** : `nadia-prof`, 2026-10-01.
