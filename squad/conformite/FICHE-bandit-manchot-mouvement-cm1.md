# Fiche de conformite - bandit-manchot-mouvement-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-mouvement.yaml`
  (12 items, type `question`), ecrit par `lea-dev` (commit `39a65ed3`, fusion `8ab9ebf1`).
- **Entree de catalogue** : `bandit-manchot-mouvement-cm1`, titre servi *"Trajectoire et vitesse"*,
  consigne servie *"Lis bien, puis dis la trajectoire, le repère, ou qui va le plus vite."*,
  `nombreDeManches: 10`.
- **Ligne declaree** : `SCI-CM1-03`. **Story** : `S46-SCI-MOUVEMENT-CM1` (`docs/backlog/backlog.md`,
  section "Sprint 46").
- **Conditions opposables** : `SCI-1`, `SCI-3`, `SCI-4`, `HIS-3`, `CON-1`, `FER-1` a `FER-3`, et le
  refus d'avance 11 (aucun item ne calcule une vitesse, une duree ou une longueur)
  (`squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md`,
  sections 2.2 ligne 3, 2.5 et 6). Piege de fabrication `ADR-0102` nomme par `theo-archi`
  (`squad/rapports/avis-faisabilite-s46-cinq-lots.md`, section 6). `CYC-1` non due (planning de s46,
  section 3).
- **Statut global** : **VALIDE SOUS RESERVE**, prononce le 2026-10-02 par `nadia-prof` (s46,
  instance B). Fiche ouverte par ce verdict : aucune fiche n'existait pour ce jeu.
- **Derniere mise a jour** : 2026-10-02.

---

## Bloc de validation pedagogique

### Verdict du 2026-10-02 (`nadia-prof`, s46, instance B) : **VALIDE SOUS RESERVE**

**Done pedagogique prononce.** `camille-po` peut passer `SCI-CM1-03` a `couvert-valide`. Les quatre
reserves `MVT-CM1-R1` a `MVT-CM1-R4` ne bloquent pas le mouvement de ligne ; elles sont a faire
dans un sprint suivant.

#### 1. Ce que j'ai fait

**Audit complet, pas degrade.** Jar construit depuis mon worktree (`round-two` a `7a507b2a`,
`bash outils/mvn-jdk21.sh -pl bootstrap -am package -DskipTests`, `BUILD SUCCESS`), lance sur le
JDK 21 de la toolchain. **Trois parties completes** de dix manches : une en tirant au hasard (sept
justes, trois manches avec erreurs), deux en me trompant trois fois a chaque manche jusqu'a la
revelation. **Les douze items ont ete tires et joues**, chacun au moins une fois avec une erreur.
L'indice s'affiche des la premiere erreur et reste affiche ; a la troisieme erreur l'ecran dit
*"Appuie sur la bonne réponse :"* ; l'ecran de fin rappelle l'item manque et sa bonne reponse.

Porte technique : je ne l'ai pas mesuree. Je reprends le rapport de `yanis-qa`
(`squad/sprints/s46-soustraction-ce1-emc-et-sciences-cm1/rapport-qualite.md`), qui la declare verte
sur les cinq lots.

#### 2. Ancrage au programme

`SCI-CM1-03` : *"Décrire le mouvement d'un objet par sa trajectoire et par sa vitesse, par rapport à
un point de repère donné."* Tache A (items 1 a 6) : decrire une trajectoire (circulaire, rectiligne,
en zigzag) et dire par rapport a quel repere un mobile est en mouvement. Tache B (items 7 a 12) :
comparer deux mouvements par leur vitesse (plus vite, moins vite, meme vitesse). **C'est bien cette
competence**, et elle reste qualitative : aucun item ne glisse vers le calcul de vitesse que
mathematiques CM1 porte.

#### 3. Conditions, verifiees une par une

| Condition | Tenue | Ce que j'ai verifie |
|---|---|---|
| Refus d'avance 11 | **oui** | Zero chiffre dans les 60 champs que l'enfant lit (enonces, bonnes reponses, distracteurs, indices), compte sur le YAML analyse. Aucun calcul, aucune duree, aucune longueur |
| Piege `ADR-0102` | **oui** | Aucune proposition ne commence par un nombre ; rien ne peut etre lu comme une duree |
| `SCI-1` | **oui, avec une reserve** | Un seul item porte une conception fausse courante en distracteur (item 11, *le plus grand va plus vite*) : son indice dit pourquoi elle est fausse sans nommer la reponse. Les items 5 et 6 opposent un repere interieur au vehicule a un repere exterieur : l'indice de l'item 6 est le bon modele, celui de l'item 5 revele la reponse, voir `MVT-CM1-R1` |
| `SCI-3` | **oui** | Aucune valeur numerique a restituer |
| `SCI-4` | **oui** | Trois propositions sur les douze items |
| `HIS-3` | **oui** | Chaque ligne d'item porte son marqueur ; j'ai verifie chaque fait moi-meme (section 5) |
| `CON-1` | **oui, avec une reserve** | Les items de la tache A ne se repondent pas sans l'enonce. Sur la tache B, quatre items se repondent sans le lire, voir `MVT-CM1-R2` |
| `FER-1` | **oui** | Trajectoire en bonne reponse (items 1 a 4), repere (items 5 et 6), vitesse (items 7 a 12) |
| `FER-2` | **oui** | 12 items. Le compte tient meme en comptant les items 10 et 11 comme un seul (`MVT-CM1-R2`) : 11 items, strictement plus que 10 manches |
| `FER-3` | **oui** | Deux taches, 6 et 6 items |

#### 4. Niveau reel

Enonces de deux phrases, vocabulaire du CM1 : *trajectoire*, *rectiligne*, *circulaire*, *repère*
sont les mots du programme et ils sont sous les yeux de l'enfant dans les propositions. L'enonce de
l'item 6 est le plus long du paquet (trois propositions grammaticales) ; il reste lisible seul par
un enfant de 9 ans. Deux formulations et la consigne genent la lecture : `MVT-CM1-R3` et
`MVT-CM1-R4`.

#### 5. Exactitude, fait par fait

Cheval de manege a distance constante du centre : circulaire. Ballon frappe une fois sur terrain
plat : rectiligne. Skieur qui enchaine les virages : zigzag. Fusee qui monte sans devier :
rectiligne. Passager de train : le quai est un repere par rapport auquel le train avance, le siege
et la fenetre n'en sont pas. Enfant en voiture : les arbres, pas le petit frere ni le siege.
Cycliste plus rapide que le pieton qu'il devance, tortue plus rapide que l'escargot qu'elle double,
coureurs cote a cote a la meme vitesse, voiture qui double le camion, voilier qui distance le
nageur : **tous exacts**. L'indice de l'item 5 simplifie la notion de repere, voir `MVT-CM1-R1`.

#### 6. Biais et ton

Personnages sans genre marque ou avec des roles ordinaires (passager, pieton, cycliste, coureurs,
skieur), un enfant et son petit frere en voiture : rien qui presuppose un milieu, une configuration
familiale exclusive ou un stereotype. Le skieur et le voilier supposent une culture que tous les
enfants n'ont pas vecue, mais l'enonce decrit tout ce qu'il faut savoir. Ton neutre.

#### 7. Les reserves

> **`MVT-CM1-R1` - Item 5 (le train) : l'indice ecarte les deux distracteurs a la fois.** Indice
> servi apres la premiere erreur : *"Ce qui compte, c'est ce qui reste immobile dehors, pas ce qui
> est fixé à l'intérieur du train."* Le siege et la fenetre sont tous deux *fixes a l'interieur* :
> l'indice ne laisse que *le quai de la gare*. **Constate en jouant** : apres une erreur, l'enfant
> n'a plus qu'a presser le seul bouton qui designe quelque chose de dehors. C'est la famille
> d'`OBT-R2` (`M21`). L'indice dit en outre qu'un repere *doit rester immobile*, ce qui n'est pas la
> notion : un repere est ce par rapport a quoi on decrit le mouvement, et le siege en est un, par
> rapport auquel le passager est immobile.
>
> **Correction a faire** : un indice qui dit pourquoi le siege ou la fenetre ne conviennent pas
> **sans les ecarter tous deux par une seule categorie**, sur le modele de l'item 6 (*"Ce qui ne
> bouge pas par rapport à l'enfant peut quand même bouger par rapport au sol."*), qui explique sans
> designer. Les formulations sont a l'equipe.
>
> **`MVT-CM1-R2` - Tache B : quatre items sur six se repondent sans lire l'observation, et deux
> sont la meme scene.** La competence demande de comparer deux vitesses **a partir de ce qu'on
> observe** (qui arrive le premier sur le meme trajet, qui double l'autre). Or sur les items 7
> (cycliste et pieton), 8 (tortue et escargot), 10 (voiture et camion) et 12 (voilier et nageur),
> **la bonne reponse est celle que l'enfant donne d'avance**, sans lire la phrase qui la justifie.
> Seuls l'item 9 (meme vitesse) et l'item 11 (la taille ne fait pas la vitesse) obligent a utiliser
> l'observation. Les items 10 et 11 sont de plus la meme scene : une voiture double un camion sur
> l'autoroute, meme question, meme bonne reponse.
>
> **Correction a faire** : au moins deux items de la tache B dont l'observation decrite **va contre
> le classement habituel** (le mobile qu'on croit le plus rapide est celui qui arrive le second, sur
> le meme trajet), et une scene neuve a la place de l'item 10. *Reserve nouvelle, datee du
> 2026-10-02* : ma barre ecrite (`FER-3`, `CON-1` tel que je l'ai transpose aux sciences) est
> atteinte, je ne la releve pas, j'ecris ce que je vois en plus.
>
> **`MVT-CM1-R3` - La consigne dit *"qui va le plus vite"*, deux items demandent *"le moins
> vite"*.** Consigne servie : *"Lis bien, puis dis la trajectoire, le repère, ou qui va le plus
> vite."* Les items 8 et 12 demandent *"Qui va le moins vite ?"*, l'item 9 demande une egalite. Un
> enfant qui suit la consigne a la lettre presse le mobile le plus rapide et se trompe pour avoir
> obei. **Correction a faire** : une consigne qui nomme la tache sans la restreindre au plus rapide
> (par exemple *comparer les vitesses*).
>
> **`MVT-CM1-R4` - Deux formulations.** Item 9 : la question *"Comment peut-on comparer leur
> vitesse ?"* attend une maniere de comparer, et les trois propositions sont des resultats de
> comparaison ; ses distracteurs *"le premier coureur"* et *"le second coureur"* renvoient a un
> ordre que l'enonce n'etablit jamais. Item 3 : *"pour freiner sa vitesse"* - on freine un mobile,
> on reduit sa vitesse. **Correction a faire** : item 9, une question qui demande ce qu'on peut dire
> de leur vitesse et deux coureurs que l'enonce distingue ; item 3, *"pour ralentir"*.
>
> **Pourquoi ces reserves n'empechent pas `couvert-valide`** : les douze items servent la ligne et
> non une voisine, aucun fait n'est faux, la tache A exige reellement de connaitre les trajectoires
> et la notion de repere, et ce que l'enfant retient quand il repond juste est exact.

#### 8. Ce dont je reste la seule garde

`PaquetCm1SciencesMouvementTest` tient les marqueurs `FER-1` et de tache, le nombre d'items, la
forme de `SCI-1` (une phrase, sans la bonne reponse ni *"ta réponse"*), `SCI-3` sur les chiffres,
`SCI-4`, la presence du marqueur `HIS-3` et le refus d'avance sur deux nombres suivis d'une unite.
**Aucun automate ne verifie** : qu'un indice n'ecarte pas tous les distracteurs par une categorie
(`MVT-CM1-R1` - le test cherche la chaine de la bonne reponse, pas l'elimination) ; qu'un item ne
se reponde pas par la culture generale seule (`MVT-CM1-R2`) ; que la consigne couvre chaque
question du paquet (`MVT-CM1-R3`) ; l'exactitude de chaque fait de mouvement. Ces proprietes n'ont
ete verifiees qu'ici, en jouant.
