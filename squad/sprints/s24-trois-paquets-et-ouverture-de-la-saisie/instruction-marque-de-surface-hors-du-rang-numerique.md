# Instruction - une bonne reponse reconnaissable hors du rang numerique, et ce qu'un garde-fou peut en tenir

- **Auteur** : theo-archi. **Date** : 2026-08-31. **Branche** : `s24-theo`.
- **Demande** : `sofia-sm`, apres le trou de garde-fou remonte par `lea-dev` sur `S24-NAR`.
- **Arbre mesure** : `s24-theo` apres fusion d'`origin/s24-sofia-dev` (`866b4ef`). JDK **21.0.10**,
  version rapportee par la machine virtuelle des tests elle-meme (`ADR-0071`).
- **Decision et regle** : `ADR-0072`. Ce document porte l'instruction, les mesures et le verdict de
  faisabilite ; il ne les repete pas tous.

## 1. L'etendue du trou, mesuree

**Perimetre** : les 27 paquets du repertoire de contenu du worktree. **Methode** : chaque paquet
charge par le chargeur reel, ses items tires par la meme chaine qu'un enfant traverse, bornes par ce
que le paquet declare ; un item est dans le domaine du garde-fou de rang si ses trois propositions se
lisent comme des nombres au sens de `valeurDe` (`ADR-0068`).

- **15 paquets sur 27 n'ont aucun item dans le domaine** : `cm1-accord-sujet-verbe`,
  `cm1-accords-groupe-nominal`, `cm1-antonymes`, `cm1-comprehension`, `cm1-conjugaison-present`,
  `cm1-homophones`, `cm1-mots-de-liaison`, `cm1-reperes`, `cm1-synonymes`, `cp-comparer`,
  `cp-comparer-nombres`, `cp-le-bon-son`, `cp-mot-manquant`, `cp-nature-mot`, `cp-ranger-nombres`.
- **2 y sont partiellement** : `cm1-grands-nombres` (14 items sur 28), `cm1-perimetre` (20 sur 22).
- **10 y sont entierement.**
- **Les deux paquets de `lea-dev` sont entierement hors du domaine** : zero item de
  `cp-texte-narratif` et zero de `cp-pluriel` n'a ses trois propositions numeriques. **Apres leur
  fusion : 17 paquets sur 29.**

**Limite de methode, nommee** : le compte de **paquets** ne depend d'aucun budget de tirage. Un compte
d'**items** ne serait pas comparable d'un paquet a l'autre - `cp-additions-jusqu-a-10` est
combinatoire, ce qu'on en tire est un budget et non une population. Je ne cite donc pas de
pourcentage d'items.

## 2. Le fait, verifie plutot que repris

Le defaut m'a ete relaye ainsi : *"sur les sept items Ou, les sept bonnes reponses commencaient par
sous, devant ou derriere, aucun distracteur n'employait ces prepositions"*. **Verifie sur le paquet
que `lea-dev` a pousse (`s24-lea`, `b6772c0`) :**

| Ce qui a ete relaye | Ce que le materiau commite montre |
|---|---|
| les **sept** bonnes reponses portent la marque | **quatre** sur sept |
| **aucun distracteur** n'emploie ces prepositions | **deux** les emploient : `sous le preau`, `sous le tilleul` |
| l'enfant gagne les sept sans lire | la strategie lexicale **gagne 4, perd 2**, est indefinie sur 1 |

**La regularite decisive est ailleurs, et elle est mesurable** : sur ce meme paquet, un enfant qui ne
lit que la **premiere phrase** du texte support et presse la seule proposition qu'il y trouve gagne
**65 %** des items, contre 33,3 % par hasard. La correction de `lea-dev` la fait tomber a **50,0 %**.

**Ce que cela ne retire pas.** `lea-dev` a trouve en jouant un defaut reel qu'aucun automate ne
voyait, et sa correction le corrige. C'est la **description** que je corrige, pas son constat - et je
le fais parce qu'une regle ecrite sur la description relayee aurait garde la mauvaise propriete, et
serait passee verte sur le paquet qui l'a motivee.

## 3. La propriete generale est-elle formulable ? Non - et je l'ai mesure avant de le dire

*"Une bonne reponse ne doit pas etre reconnaissable par une marque de surface que ses distracteurs ne
portent pas"* **n'est pas encodable**. `ADR-0072` section 1 porte le motif : *marque de surface*
quantifie sur toutes les fonctions calculables des chaines, et la fonction indicatrice des bonnes
reponses en est toujours une ; restreinte aux marques qu'un enfant percoit, la propriete devient
pedagogique et non calculable.

**Je n'ai pas conclu par raisonnement seul. Trois familles essayees, mesurees, ecartees :**

| Famille | Attrape le paquet fautif ? | Cout sur le catalogue actuel |
|---|---|---|
| Premier mot d'une proposition | **non** - `sous` est aussi en tete de deux distracteurs | 7 paquets marques a partir de 2 occurrences, 2 a partir de 3 |
| Dernier mot | **non**, meme motif | meme ordre |
| Rang par longueur | **non** - la bonne reponse y est la plus longue sur 25 % des items | rouge des le premier jour sur `cm1-mesures`, 54,1 % |

**Les trois echouent du meme cote** : elles ne voient pas le defaut et refuseraient du contenu sain.
Une regle qui n'aurait connu que les prepositions aurait ete une liste d'exemples deguisee en regle,
et la mesure montre en plus qu'elle n'aurait rien attrape.

## 4. Ce qui est formulable, et qui est ecrit - `ADR-0072`

**Ce n'est pas la marque qui se formule, c'est la strategie.** Ce que le garde-fou de s12 tient n'est
pas une propriete du rang, c'est *"une strategie qui ignore le contenu ne doit jamais etre
majoritaire"* - le rang n'en est qu'une instance. La seconde instance est celle du defaut de
`lea-dev` :

> Sur un paquet dont les items portent un texte support, aucune strategie qui ne lit qu'une seule
> phrase de ce texte ne gagne la majorite des items.

`adapters/adapter-content/src/test/java/com/recreo/adapter/content/AucunePhraseDuTexteSupportNeSuffitAGagnerLaMajoriteTest.java`,
**5 tests, 0 echec**. Seuil de s12 repris sans renegociation. Mis a l'epreuve **dans les deux sens sur
le meme paquet a un jour d'intervalle** : l'etat fautif (65 %, rouge) et l'etat corrige (50,0 %,
vert). Sans cette seconde moitie, la morsure prouverait que le garde-fou rougit, pas qu'il distingue
la correction que `lea-dev` a faite.

## 5. Verdict de faisabilite

**La regle est ecrivable a cout faible, et je l'ai livree sans toucher au perimetre de personne.**

- **Un fichier de test** dans `adapter-content`, plus deux paquets de jeu fautif sous
  `contreexemple/`, tous deux extraits de l'historique de `s24-lea`.
- **Aucun fichier de production touche**, aucun paquet servi touche, aucune story d'un developpeur
  touchee, aucune `ArchRule` ajoutee - compte inchange a 30.
- **Aucune exemption, aucun plafond de dette** : le catalogue actuel passe.
- **Aucun engagement de lot n'est demande** a `sofia-sm` : c'est fait.

## 6. Ce que je route, et qui n'est pas a moi

- **A `nadia-prof`, un fait et non une decision** : le paquet corrige de `lea-dev` est **exactement a
  la borne, 50,0 %, marge nulle**. Il passe par comparaison stricte, comme `cp-soustractions` passait
  a 50,0 % pile le garde-fou de s12. Un enfant qui ne lit que la premiere phrase y gagne encore une
  manche sur deux, contre une sur trois par hasard. **Le seuil est le sien, la valeur est un fait, et
  c'est a elle de dire si une marge nulle lui convient sur une ligne de comprehension de lecture.**
  Je ne deplace pas un seuil que je n'ai pas fixe et je ne prononce rien sur le paquet.
- **A `sofia-sm`** : **15 paquets sur 27 - 17 sur 29 apres la fusion - restent hors de toute
  propriete de cette famille.** `ADR-0072` dit pourquoi aucune regle generale ne les couvrira. Ce
  n'est pas une dette a solder, c'est une limite a connaitre : sur ces paquets-la, **la seule garde
  reste le fait de jouer**, et `lea-dev` vient d'en administrer la demonstration.
- **A `lea-dev`** : son refus de combler seule le trou etait le bon geste. La regle qu'elle aurait
  ecrite de bonne foi - celle qui suit la description du defaut - aurait ete verte sur son propre
  paquet fautif.
