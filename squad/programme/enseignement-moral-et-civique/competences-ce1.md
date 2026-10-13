# Référentiel de compétences - Enseignement moral et civique - CE1 (cycle 2)

> Écrit par `nadia-prof` le 2026-10-13
> (`squad/conformite/ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md`, section 2),
> au même grain et dans la même forme que
> `squad/programme/enseignement-moral-et-civique/competences-cp.md`, dont il reprend la structure en
> trois finalités. **Socle réaliste, non exhaustif et non officiel** : à compléter et à faire valider
> par l'utilisatrice au fil des sprints, en lien avec `squad/programme/MATRICE-couverture.md`. Ne
> remplace pas une lecture des programmes officiels de l'Éducation nationale.
>
> Le programme d'EMC du cycle 2 est écrit pour le cycle, pas pour chaque année. **Une ligne n'est
> écrite ici que si un paquet de CE1 doit y servir autre chose qu'un paquet de CP sur la ligne
> voisine.** Les quatorze lignes du CP couvrent déjà les émotions, la politesse, les règles de la
> classe, les symboles, la devise, les droits de l'enfant, les rôles de la commune et l'alerte : je
> n'en écris pas de jumelle (section "Ce que ce référentiel ne reprend pas").

**Codes de ligne.** Chaque compétence porte un code entre crochets, alloué une fois et
jamais réutilisé : c'est par lui qu'un paquet de contenu déclare la ligne qu'il sert, et
par lui que le nombre d'items servant une ligne se compte (`ADR-0081`). Reformuler un
intitulé ne change pas son code ; insérer une ligne prend le numéro libre suivant, sans
renuméroter les autres. Le code n'est donc pas un rang, même si les premiers ont été
alloués dans l'ordre de lecture.

## Respecter autrui

- [EMC-CE1-01] Respecter les biens personnels et collectifs : reconnaître ce qui appartient à tous (matériel de la classe, livres de la bibliothèque, jeux de la cour) et le geste qui le protège.

## Acquérir et partager les valeurs de la République

- [EMC-CE1-02] Distinguer une règle d'une sanction, et dire à quoi sert une sanction (rappeler la règle, réparer le tort fait).
- [EMC-CE1-03] Associer quelques droits de l'élève à l'école aux devoirs qui les accompagnent (le droit d'apprendre et le devoir de laisser les autres apprendre).

## Construire une culture civique

- [EMC-CE1-04] Distinguer, dans une situation simple, ce qui sert l'intérêt d'une seule personne de ce qui sert l'intérêt de tous.
- [EMC-CE1-05] Coopérer dans un travail de groupe : se répartir les tâches, aider, accepter l'aide.

---

## Frontières, avec le CP et avec le CM1

| Ligne de CE1 | Ligne voisine | Ce qui les sépare |
|---|---|---|
| `EMC-CE1-01` | `EMC-CP-06` (règles de vie de la classe) | au CP, la règle et sa raison ; ici, ce qui est à tous et ce qui le protège |
| `EMC-CE1-02` | `EMC-CP-07` (règle, souhait, loi) | au CP, ce qui fait une règle ; ici, ce qui suit sa transgression |
| `EMC-CE1-02` | `EMC-CM1-08` (règle et loi) | au CM1, la loi ; ici, la classe et l'école seules |
| `EMC-CE1-03` | `EMC-CP-10` (droits de l'enfant) | au CP, des droits sans devoirs ; ici, un droit et le devoir qui lui répond, à l'école |
| `EMC-CE1-03` | `EMC-CM1-07` (droit et devoir) | au CM1, la distinction en général, élève et enfant ; ici, des couples droit-devoir de la classe seulement |
| `EMC-CE1-04` | `EMC-CM1-11` (fait et opinion) | objets différents : ici, l'intérêt de qui ; au CM1, ce qui est vrai et ce qu'on pense |

## Ce que ce référentiel ne reprend pas du CP, et pourquoi

`EMC-CP-01` à `EMC-CP-14` décrivent des compétences que le cycle 2 travaille aussi au CE1. **L'égalité
entre les filles et les garçons**, objet explicite du cycle, n'a pas de ligne ici : la seule forme
jouable que j'admette est celle de l'interdit (`EMC-4`), et cette forme est déjà celle d'`EMC-CP-04`.
Une ligne jumelle compterait deux fois la même chose.

## Jugement de faisabilité (`nadia-prof`, 2026-10-13)

**Audit dégradé, et je le déclare** : aucun paquet n'existe sur ces lignes, je n'ai rien joué. Ce
jugement dit la situation de chaque ligne à son entrée dans la matrice. **L'instruction, ligne par
ligne, est due avant le premier paquet.**

| Ligne | Situation à l'entrée dans la matrice | Motif |
|---|---|---|
| `EMC-CE1-01` | **`non couvert`**, jouable au choix | connaissance de ce qui est commun et du geste qui le protège ; risque de catéchisme civique, que l'instruction bornera |
| `EMC-CE1-02` | **`non couvert`**, jouable au choix | la distinction se pose en trois propositions ; aucune sanction humiliante, aucun item sur la punition de l'enfant lui-même (`EMC-1`) |
| `EMC-CE1-03` | **`non couvert`**, jouable au choix | couples droit-devoir exacts et vérifiables |
| `EMC-CE1-04` | **`non couvert`**, jouable au choix | des situations à une seule lecture défendable (`EMC-5`) |
| `EMC-CE1-05` | **`non couvrable en l'état`**, nature **irréversible** | coopérer est une conduite tenue avec d'autres, pas une connaissance : comme `EMC-CP-11` et `EMC-CP-12`, aucun choix ni aucune saisie ne la produit, et la faire passer pour un clic serait une contrefaçon (`CON-1`) |

Cinq lignes, quatre au dénominateur atteignable.

**Les conditions `EMC-1` à `EMC-7` du CP valent pour ces lignes telles qu'elles sont écrites**
(`squad/programme/enseignement-moral-et-civique/competences-cp.md`) : elles portent sur la matière
au cycle 2, pas sur une année. **Ce dont je reste la seule garde**, comme au CP : aucune situation à
deux réponses défendables, aucun item qui interroge l'enfant sur ce qu'il vit chez lui, aucune ligne
présentée comme attestant une conduite. Ce produit vérifie ce qu'un enfant sait, jamais ce qu'il
fait.

**Aucun intitulé ci-dessus ne devient un titre de jeu** (`ADR-0078`).

## Note du 2026-10-13, après la réponse de l'utilisatrice (`nadia-prof`)

L'utilisatrice ne veut pas de nouveaux items au CE1 par reprise du CP, ni de son (`DEC-0120` A.27).
Les lignes ci-dessus y sont conformes ; **leurs items ne l'étaient pas d'avance** sur une ligne :
`EMC-CE1-01`, dont *"le geste qui le protège"* pouvait recevoir les items de rangement de
`cp-emc-regles-de-vie` (*"les remettre à leur place"*). Aucun intitulé ne change. La frontière tient
désormais item par item : `NRC-CE1-1` et `BCO-CE1-1`, dans
`squad/conformite/ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md`, où
l'instruction ligne par ligne est faite.
