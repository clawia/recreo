# Fiche de conformite - bandit-manchot-evolution-objet-technique-cm1

- **Mecanique** : bandit manchot.
- **Contenu** :
  `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-evolution-objet-technique.yaml`
  (11 items, type `question`), ecrit par `sofia-sm`, casquette dev (commit `b5cd63dd`, fusion
  `00c9139b`).
- **Entree de catalogue** : `bandit-manchot-evolution-objet-technique-cm1`, titre servi *"Comment
  ça a changé ?"*, consigne servie *"Lis bien, puis dis ce qui a changé, ou ce qui n'a pas
  changé."*, `nombreDeManches: 10`.
- **Ligne declaree** : `SCI-CM1-13`. **Story** : `S44-SCI-OBJET-TECHNIQUE` (`docs/backlog/backlog.md`,
  section "Sprint 44", corrigee par `camille-po` en `ead6038d`).
- **Conditions opposables** : `SCI-1`, `SCI-3`, `SCI-4`, `BIA-3`, `HIS-3`, `CON-1`, `FER-1` a
  `FER-3`, et `EVO-1`, `EVO-2` (`squad/conformite/ARBITRAGE-PREALABLE-s44-musique-et-sciences-cm1.md`,
  section 3).
- **Statut global** : **VALIDE SOUS RESERVE**, prononce le 2026-09-30 par `nadia-prof` (s44,
  instance unique). Fiche ouverte par ce verdict : aucune fiche n'existait pour ce jeu.
- **Derniere mise a jour** : 2026-09-30.

---

## Bloc de validation pedagogique

### Verdict du 2026-09-30 (`nadia-prof`, s44, instance unique) : **VALIDE SOUS RESERVE**

**Done pedagogique prononce.** `camille-po` peut passer `SCI-CM1-13` a `couvert-valide`. Les quatre
reserves `EVO-CM1-R1` a `EVO-CM1-R4` ne bloquent pas le mouvement de ligne ; `EVO-CM1-R1` est la
plus lourde et je dis en section 7 pourquoi elle reste une reserve.

#### 1. Ce que j'ai fait

**Audit complet, pas degrade.** Meme jar que pour les deux autres lots (`round-two` a `bc1cf4b0`,
JDK 21 de la toolchain). **Cinq parties completes** de dix manches, un tiers en repondant juste, un
tiers apres une erreur, un tiers apres trois erreurs jusqu'a la revelation. **Les onze items ont
ete tires et joues.** L'indice s'affiche des la premiere erreur et reste a la revelation ; l'ecran
de fin rappelle l'item manque et sa bonne reponse.

#### 2. Ancrage au programme

`SCI-CM1-13` : *"Repérer l'évolution d'un objet technique au cours du temps et ce qui a changé dans
sa fabrication ou dans son usage."* Tache A (items 1 a 5) : dire ce qui a change, par un passage
d'un etat ancien a un etat recent. Tache B (items 6 a 11) : dire ce qui n'a pas change. **C'est bien
cette competence** : aucun item ne demande une famille de materiau (`SCI-CM1-11`) ni la fonction
d'un objet seul (`SCI-CM1-12`).

#### 3. Conditions, verifiees une par une

| Condition | Tenue | Ce que j'ai verifie |
|---|---|---|
| `EVO-1` | **oui** | Onze bonnes reponses sur onze enoncent une relation entre deux etats : *"X a laissé la place à Y"* (tache A), *"on l'utilise pour ..."* entre deux versions (tache B). Aucune n'est un terme nu ; aucune n'egale une bonne reponse de `cm1-sciences-objet-technique.yaml` ni de `cm1-sciences-familles-materiaux.yaml`, relus cote a cote. Les cinq items de la tache A portent le passage inverse en distracteur |
| `EVO-2` | **oui** | Douze distracteurs de la tache B verifies un par un : chacun est vrai de la version ancienne et faux de la version recente, aucun n'est une fonction fausse. Aucun des quatre objets de `SCI-CM1-12` ne porte la tache B |
| `SCI-1` | **oui** | Aucun distracteur ne porte une conception fausse courante ; `SCI-1` n'est donc engagee sur aucun item, comme je l'avais prevu (arbitrage, section 3.4). Chaque indice fait une phrase, s'affiche des la premiere erreur, ne dit jamais *"ta réponse"* et ne nomme pas la bonne reponse. Un indice installe en revanche une idee fausse : voir `EVO-CM1-R2` |
| `SCI-3` | **oui** | Aucune date, aucune valeur numerique a restituer |
| `SCI-4` | **oui** | Trois propositions sur chaque item |
| `BIA-3` | **oui, a la lettre** | Aucun texte ne qualifie l'ancien de difficile, dur, pauvre ou pas pratique, ni de moins bien. Un distracteur s'en approche : voir `EVO-CM1-R4` |
| `HIS-3` | **oui** | Chaque ligne d'item porte son marqueur, sous la forme *"fait consensuel"* deja admise sur les paquets d'histoire valides. J'ai verifie chaque fait moi-meme (section 5) |
| `CON-1` | **oui, item par item** | Aucun item ne se repond sans son enonce a la premiere rencontre. Sur la suite de la partie, non : voir `EVO-CM1-R1` |
| `FER-1` a `FER-3` | **oui** | Changement et permanence en bonne reponse ; fabrication (items 1, 5) et usage (items 2 a 4) servis ; deux taches, 5 et 6 items ; 11 items |

#### 4. Niveau reel

Enonces de deux phrases, vocabulaire du CM1. *"X a laissé la place à Y"* est une tournure qu'un
enfant de 9 ans lit sans aide et qui porte exactement l'ordre dans le temps. Les indices donnent un
angle de comparaison (*"Compare à quoi sert cet objet, pas ..."*), pas la reponse.

#### 5. Exactitude, fait par fait

Jouets du bois au plastique, eclairage de la flamme a l'electricite, deplacement du cheval au
moteur, plume d'oie et stylo a bille, telephone a cadran et portable, lavoir et machine a laver,
voile et moteur, sablier et montre, feu de bois et cuisiniere : **exacts**. L'item 4 (*"le bloc de
glace a laissé la place à un moteur électrique"*) simplifie - le froid d'un refrigerateur est
produit par un circuit que fait tourner un moteur electrique - mais ne dit rien de faux au niveau
du CM1 ; je l'accepte. Deux points ne tiennent pas, items 2 et 5 : voir `EVO-CM1-R2` et
`EVO-CM1-R3`.

#### 6. Correction de mon propre texte, avant d'arbitrer

`EVO-1` dit que *"les distracteurs ont la même forme (ADR-0086)"*. Mon exemple d'`EVO-2`, ecrit le
meme jour, oppose *"on s'en sert pour écrire"* a *"on trempe sa pointe dans l'encre"* et *"sa pointe
vient d'une plume d'oiseau"* : **il porte deja l'amorce que `EVO-1` interdit**. Le paquet l'a suivi
sur six items. Le defaut vient de mon exemple, pas de l'auteure du paquet ; je ne le retourne pas
contre elle en refus, je l'ecris en reserve neuve et datee.

#### 7. Les reserves

> **`EVO-CM1-R1` - Tache B : l'amorce de la bonne reponse la designe.** Sur les six items 6 a 11,
> cinq bonnes reponses commencent par *"on l'utilise pour"*, la sixieme par *"on s'en sert pour"* ;
> **aucun des douze distracteurs ne commence ainsi**. Des le deuxieme item de la tache, un enfant
> presse le bouton qui commence par *"on l'utilise pour"* sans lire l'enonce ni comparer les deux
> objets. C'est la famille de `FOP-R4`.
>
> **Correction a faire** : sur la tache B, qu'aucune amorce ne soit propre aux bonnes reponses.
> Donner aux distracteurs la meme amorce (*"on s'en sert en trempant sa pointe dans l'encre"*,
> *"on l'utilise en restant à la maison"*) ou varier celle des bonnes reponses, sans jamais faire
> d'un distracteur une fonction fausse (`EVO-2`).
>
> **Pourquoi c'est une reserve et pas un refus** : la barre ecrite, `EVO-1` et `EVO-2`, est
> atteinte, et le patron vient de mon propre exemple ; la tache A (cinq items) exige reellement de
> savoir dans quel sens l'objet a change ; et ce que l'enfant retient de la tache B, *la fonction
> demeure*, est la bonne lecon, meme s'il peut la presser sans la penser.
>
> **`EVO-CM1-R2` - Item 5 (le moulin) : l'objet change en cours d'enonce, et l'item oppose l'eau a
> l'electricite.** L'enonce part d'*"un moulin"* et demande ce qui a change *"dans le fonctionnement
> de nombreuses machines"* : ce n'est plus le meme objet (`EVO-1`). La bonne reponse *"la force de
> l'eau a laissé la place à l'électricité"* et l'indice *"sans roue tournée par une rivière"* font
> de l'eau et de l'electricite deux choses qui se remplacent, alors que
> `cm1-sciences-energie.yaml` enseigne au meme niveau qu'un barrage produit de l'electricite avec
> l'eau. **Un enfant qui a joue les deux paquets recoit deux messages contraires.**
>
> **Correction a faire** : garder le moulin d'un bout a l'autre de l'enonce, et nommer ce qui fait
> tourner la meule (*"la roue à eau a laissé la place à un moteur électrique"*, passage inverse en
> distracteur), avec un indice qui ne dit pas d'ou vient l'electricite.
>
> **`EVO-CM1-R3` - Item 2 (l'eclairage) : un distracteur est historiquement defendable.** *"La
> bougie a laissé la place à la lampe à huile"* : au XIXe siecle, la lampe a huile puis la lampe a
> petrole ont reellement remplace la bougie dans beaucoup de maisons, avant l'electricite. Un enfant
> qui le sait est sanctionne pour une reponse vraie. **Correction a faire** : un passage que
> l'histoire des techniques n'a pas connu.
>
> **`EVO-CM1-R4` - Item 8 (le lavoir) : *"pendant longtemps"*.** Le distracteur *"il faut frotter
> le linge à la main pendant longtemps"* est vrai du lavoir. *"Pendant longtemps"* n'ajoute rien a
> la comparaison et presente l'ancien comme penible : c'est l'esprit que `BIA-3` ecarte, meme si le
> mot n'est pas dans sa liste. **Correction a faire** : *"il faut frotter le linge à la main"*.
>
> **Pourquoi ces quatre reserves n'empechent pas `couvert-valide`** : les onze items servent la
> ligne et non une voisine, aucun fait n'est faux, et les trois reserves d'item (R2 a R4) touchent
> un item chacune sur onze.

#### 8. Ce dont je reste la seule garde

`PaquetCm1SciencesEvolutionObjetTechniqueTest` tient `EVO-1` sur les chaines, `EVO-2` sur les noms
d'objets, `SCI-1`, `SCI-3`, `SCI-4`, le lexique de `BIA-3` et la presence du marqueur `HIS-3`.
**Aucun automate ne verifie** : que chaque distracteur d'`EVO-2` soit vrai d'un etat et faux de
l'autre ; qu'une amorce soit propre aux bonnes reponses (`EVO-CM1-R1` - la clause 2 d'`ADR-0086`
ne mord que dans l'autre sens, quand ce sont les distracteurs qui partagent une amorce) ; qu'un
item contredise un autre paquet (`EVO-CM1-R2` - aucun garde-fou ne compare deux paquets) ;
l'exactitude de chaque fait d'histoire des techniques. Ces proprietes n'ont ete verifiees qu'ici.
