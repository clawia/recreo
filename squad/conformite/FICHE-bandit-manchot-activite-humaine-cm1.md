# Fiche de conformite - bandit-manchot-activite-humaine-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-activite-humaine.yaml`
  (12 items, type `question`), ecrit par `marc-dev` (commit `6971d069` ; test `SCI-2` aligne sur ma
  liste Q2 par `9afa50e4`).
- **Entree de catalogue** : `bandit-manchot-activite-humaine-cm1`, titre servi *"L'activité humaine
  et la nature"*, consigne servie *"Lis bien, puis dis quel est l'effet, ou quel geste préserve."*,
  `nombreDeManches: 10`.
- **Ligne declaree** : `SCI-CM1-18`. **Story** : `S51-SCI-ACTIVITE-HUMAINE-CM1`
  (`docs/backlog/backlog.md`, section "Sprint 51").
- **Conditions opposables** : `FER-1` a `FER-3`, `SCI-1` a `SCI-4`, `HIS-3`, `CON-1`, `CYC-1`
  (`squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md`,
  sections 2.2 ligne 18, 2.3, 2.5 et 2.6), `DEC-0064`, et ma liste Q2 pour le test de `SCI-2`
  (`squad/conformite/ARBITRAGE-PREALABLE-s51-fra-ce1-22-graphie-cedille.md`, section 3, branche
  `s51-nadia-instance-a`).
- **Statut global** : **REFUSE**, prononce le 2026-10-06 par `nadia-prof` (s51, instance B). Fiche
  ouverte par ce verdict : aucune fiche n'existait pour ce jeu.
- **Derniere mise a jour** : 2026-10-06.

---

## Bloc de validation pedagogique

### Verdict du 2026-10-06 (`nadia-prof`, s51, instance B) : **REFUSE**

**Done pedagogique non prononce.** `SCI-CM1-18` reste `non couvert` ; `camille-po` ne ferme pas la
ligne. **Un seul motif de refus**, l'item 11 (section 7). `SCI-2`, la condition que j'appelais *"la
plus dangereuse du lot"*, est **tenue** sur les douze items. Deux points non bloquants (section 8)
peuvent etre pris dans le meme diff que la correction ; je les rejouerai a ce moment-la. Le lot
repasse devant moi apres correction, et je le rejoue avant de juger : une correction se verifie a
l'ecran, pas au diff.

#### 1. Ce que j'ai fait

**Audit complet, pas degrade.** Worktree dedie `s51-nadia-instance-b`, a `0e9913c1` (les commits
`6971d069` et `9afa50e4` inclus). Jar `bootstrap/target/recreo.jar` de ce worktree, dont j'ai verifie
que le paquet et le catalogue sont identiques octet pour octet aux fichiers commites ; lance sur un
port libre, arrete par son PID. Joue par les routes servies (demarrage de partie, coups).

**Methode de lecture** : avant de voir les propositions, j'ai lu les douze enonces seuls,
propositions masquees, et repondu moi-meme a chacun ; puis j'ai compare a la bonne reponse et aux
distracteurs. C'est a cette lecture que l'item 11 m'a arretee : sa premiere phrase fixe un but
(*"garder du pétrole pour plus tard"*) que sa question ne reprend pas.

**Parties jouees** : quatre parties de dix manches, une erreur aux manches impaires avant la bonne
reponse, trois erreurs a la manche 3 de la premiere partie jusqu'a la revelation. **Les douze items
ont ete tires et joues**, dont l'item 11 avec l'erreur *"produire de l'électricité avec plus de
charbon"* et l'item 5 avec ses deux distracteurs.

#### 2. Ancrage au programme

`SCI-CM1-18` : *"Identifier un effet de l'activité humaine sur l'environnement et un geste de
préservation des ressources."* Tache A (items 1 a 6) : reconnaitre l'effet d'une activite. Tache B
(items 7 a 12) : choisir le geste qui preserve. **C'est bien cette competence**, sur le couple de
taches que j'avais ecrit d'avance. Elle ne deborde pas sur `SCI-CM1-16` (risques naturels) : aucun
item ne porte sur un seisme, une eruption, une tempete ou une inondation.

#### 3. Conditions, verifiees une par une

| Condition | Tenue | Ce que j'ai verifie |
|---|---|---|
| `FER-1` | **oui** | Un effet en bonne reponse (items 1 a 6), un geste (items 7 a 12) |
| `FER-2` | **oui** | 12 items servent la ligne |
| `FER-3` | **oui** | Deux taches distinctes, 6 et 6 items |
| `DEC-0064` | **oui** | 12 items pour 10 manches |
| `SCI-1` | **oui a la lettre** | Items a conception fausse (1 plus de place, 3 les poissons compensent, 4 le sol absorbe davantage, 8 diluer ou deplacer, 9 pecher les jeunes) : l'indice dit pourquoi elle est fausse, en une phrase, au present, sans *"ta réponse"*, sans nommer la reponse. Trois indices eliminent les deux distracteurs a la fois : point 1, section 8 |
| `SCI-2` | **oui** | Section 6 |
| `SCI-3` | **oui** | Aucune valeur numerique a restituer, 0 sur 12 |
| `SCI-4` | **oui** | Trois propositions sur les douze items |
| `HIS-3` | **oui** | Chaque ligne d'item porte son marqueur ; j'ai verifie chaque fait moi-meme (section 5) |
| `CON-1` | **oui** | Aucun item ne se repond sans son enonce ; l'item 5 se repond trop bien avec lui : point 2, section 8 |
| `CYC-1` | **oui, confirme** | Aucun paquet de cycle 2 sur le meme objet. Recherche sur les noms et sur le texte des 73 paquets `cp-*` et `ce1-*` de `adapters/adapter-content/src/main/resources/contenu` (pollution, dechet, recyclage, environnement, gaspillage, foret, petrole, epuration) : deux occurrences seulement, *"une forêt"* distracteur d'un paysage peint dans `cp-decrire-une-oeuvre` et le domaine d'EPS *"environnements variés"* de `cp-eps-regles-de-securite`, aucune sur l'objet de la ligne |

#### 4. Niveau reel

Enonces de deux phrases, une situation puis une question. Vocabulaire du CM1 (*intoxiqués*,
*station d'épuration*, *canalisations*, *dioxyde de carbone*, *reconstituer*) ; *"dioxyde de
carbone"* est nomme dans l'enonce, pas demande. Les situations sont concretes et a l'echelle d'une
commune, d'une usine, d'une region ou d'une mer, jamais d'une famille.

#### 5. Exactitude, fait par fait

- Item 1 : une foret coupee fait perdre aux animaux leur abri et leur nourriture : **exact**.
- Item 2 : une eau chargee de produits chimiques peut intoxiquer poissons et plantes : **exact**.
- Item 3 : une espece pechee plus vite qu'elle ne se renouvelle diminue : **exact**.
- Item 4 : le beton et le goudron empechent l'eau de pluie de s'infiltrer : **exact**.
- **Item 5, verifie a part comme demande.** *"Brûler du charbon, du pétrole ou du gaz libère un gaz
  appelé dioxyde de carbone"* : **exact**, c'est la combustion des energies fossiles. Bonne reponse
  *"il contient de plus en plus de ce gaz"* : **exact**, la teneur de l'air en dioxyde de carbone
  augmente depuis deux siecles. Indice *"Les gaz libérés dans l'air n'en disparaissent pas aussitôt."*
  : **exact** (une part est absorbee par les oceans et la vegetation, le reste s'accumule).
  Distracteurs : *"plus d'oxygène"* est faux (la combustion en consomme), *"plus froid"* est faux.
  **Rien de faux dans l'item** ; ce qui lui manque est pedagogique : point 2, section 8.
- Item 6 : la lumiere artificielle de nuit desoriente des oiseaux migrateurs, qui s'orientent
  notamment aux etoiles : **exact**.
- Item 7 : replanter de jeunes arbres aide une foret a se reconstituer : **exact**.
- Item 8 : une station d'epuration nettoie les eaux usees ; diluer ou deplacer une eau sale ne change
  pas ce qu'elle contient : **exact**.
- Item 9 : limiter la peche pendant la reproduction preserve une espece ; un poisson peche avant
  d'etre adulte n'aura pas de petits : **exact**.
- Item 10 : reparer les canalisations qui fuient preserve l'eau : **exact**.
- **Item 11 : la cle de reponse est fausse.** Section 7.
- Item 12 : eteindre les lampes inutiles la nuit limite la gene des animaux nocturnes : **exact**.

#### 6. `SCI-2`, biais et ton

**`SCI-2`, tenue item par item, sur les cinq champs que l'enfant lit** (enonce, trois propositions,
indice ; aucun item ne porte d'`explication`) :

- **Personne grammaticale** : aucun jeton refuse de ma liste Q2 (deuxieme personne, *t'* elide,
  premiere et deuxieme personnes du pluriel). Les acteurs sont *une usine*, *des bateaux*, *une
  ville*, *une commune*, *une région*, *l'éclairage public*. Le test aligne par `9afa50e4` le verifie
  aussi ; je l'ai verifie en lisant.
- **Ce que le test ne voit pas, et que j'ai garde seule** : **aucun imperatif sans pronom** dans les
  items (les gestes sont a l'infinitif, *"réparer les canalisations qui fuient"*, *"y planter de
  jeunes arbres"*) ; **aucune injonction impersonnelle** (*il faut*, *on doit*) ; **aucun presuppose
  de logement, de foyer ou d'equipement** - ni chauffage, ni voiture, ni voyage, ni dechets, ni
  courses. La consigne servie, *"Lis bien, puis dis ..."*, est un imperatif de jeu adresse au joueur,
  pas un geste de preservation prescrit a l'enfant.
- **Mecanisme et geste possible** : chaque item decrit un mecanisme (tache A) ou un geste possible
  (tache B) a la troisieme personne. Aucun item ne demande a l'enfant ce qu'il fait ni ce que sa
  famille devrait faire.

**Biais et ton** : aucun ton culpabilisant ni alarmiste ; les effets sont decrits comme des
mecanismes, les gestes comme des possibilites collectives.

#### 7. Motif du refus

> **Item 11 : un distracteur repond aussi a la question posee, et un autre repond au but que pose
> l'enonce.**
>
> Item servi : *"Une commune veut garder du pétrole pour plus tard. Quel geste lui permet d'en
> utiliser moins ?"* - bonne reponse *"produire de l'électricité avec le vent et le soleil"*,
> distracteurs *"produire de l'électricité avec plus de charbon"* et *"stocker davantage de pétrole
> dans des réservoirs"*, indice *"Le charbon, comme le pétrole, met des millions d'années à se
> former."*
>
> 1. **La question demande d'utiliser moins de petrole.** Produire l'electricite avec du charbon a la
>    place du petrole fait utiliser moins de petrole, exactement comme la produire avec le vent et le
>    soleil : les deux propositions reposent sur la meme substitution. L'enfant qui raisonne juste sur
>    la question ecrite et choisit le charbon recoit *"Ce n'est pas ça."* - je l'ai fait a l'ecran.
>    L'indice juge alors sur un autre critere que la question : le charbon est, lui aussi, une
>    ressource qui ne se renouvelle pas. Ce critere est le bon, mais **la question ne le pose pas**.
> 2. **La premiere phrase pose un but que le distracteur restant atteint.** *"Garder du pétrole pour
>    plus tard"*, c'est, a la lettre, *"stocker davantage de pétrole dans des réservoirs"*. Un enfant de
>    9 ans qui lit la premiere phrase y trouve ce distracteur mot pour mot.
> 3. **Le but lui-meme est inexact.** Une commune ne garde pas de petrole pour plus tard : la ressource
>    qu'on preserve est celle du sous-sol, qui met des millions d'annees a se former.
>
> C'est une cle de reponse fausse : une seule proposition doit etre acceptable, et ce que l'enfant
> apprend en se trompant ici est faux. Ma regle s'applique sans amenagement.
>
> **Correction attendue** : un item dont la question pose elle-meme le critere que l'indice emploie -
> preserver une ressource qui ne se renouvelle pas, ou qui met des millions d'annees a se former - de
> sorte qu'une seule proposition y reponde ; et un enonce qui ne fixe pas un but atteint a la lettre
> par un distracteur. Les formulations sont a l'equipe. L'item reste en tache B (un geste qui
> preserve).
>
> **Ce que le refus ne demande pas** : aucune autre modification n'est une condition de levee. La
> levee porte sur l'item 11 seul, a la barre ecrite ici, et je ne la releverai pas au rejeu.

#### 8. Points non bloquants, a prendre dans le meme diff

Ils ne sont pas des motifs de refus et ne portent pas d'identifiant. Si le rejeu les laisse en
l'etat, je les ouvrirai alors comme reserves datees.

1. **Items 8, 10 et 12 : l'indice ecarte les deux distracteurs a la fois.** Item 8 : *"Déplacer ou
   diluer une eau sale ne change pas ce qu'elle contient."* (les deux distracteurs sont *rejeter plus
   loin* et *diluer*). Item 10 : *"Une eau qui s'évapore au soleil ou qui coule sans servir est
   perdue."* (*arroser en plein midi*, *laisser couler les fontaines*). Item 12 : *"Plus il y a de
   lumière dans la nuit, plus les repères naturels des animaux disparaissent."* (les deux
   distracteurs ajoutent de la lumiere). Des la premiere erreur, il ne reste qu'une proposition :
   c'est la famille de `PHN-CM1-R1`. A faire : un indice qui n'ecarte qu'un distracteur au plus.
2. **Item 5 : la bonne reponse redit l'enonce, et l'effet sur l'environnement n'est pas servi.**
   L'enonce dit que la combustion libere un gaz ; la bonne reponse dit que l'air en contient de plus
   en plus. L'item se repond en relisant la premiere phrase, et l'enfant qui repond juste n'apprend
   pas ce que ce gaz change : le climat qui se rechauffe. Le distracteur *"il devient de plus en
   plus froid"* designe l'oppose de cet effet sans que l'item le nomme. A faire : une bonne reponse
   qui porte l'effet (l'air retient davantage la chaleur, le climat se rechauffe), et non la seule
   accumulation du gaz.

#### 9. Ce dont je reste la seule garde

`PaquetCm1SciencesActiviteHumaineTest` tient les marqueurs `FER-1` et de tache, le nombre d'items, la
forme de `SCI-1`, `SCI-3`, `SCI-4`, le marqueur `HIS-3` et les jetons d'adresse de `SCI-2`. **Aucun
automate ne verifie** : qu'une seule proposition reponde a la question ecrite (le motif de ce refus
a traverse le test vert) ; l'exactitude d'un fait d'environnement ; qu'un indice ne donne pas la
reponse par elimination ; l'imperatif sans pronom, l'injonction impersonnelle et les presupposes de
foyer de `SCI-2` ; `CYC-1`. Ces proprietes n'ont ete verifiees qu'ici, en jouant.

#### 10. Mesure technique citee

`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test` sur ce worktree, avant l'ecriture
de cette fiche : *"Tests run: 1202, Failures: 0, Errors: 0, Skipped: 0"*, `BUILD SUCCESS`. Perimetre :
le module `adapter-content` et ses modules amont, pas la CI ; la porte technique reste a juger sur les
jobs de `.github/workflows/ci.yml`, que je n'ai pas lances. Cette fiche n'ecrit aucun identifiant neuf.
