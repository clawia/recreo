# Retro - s47 - tables de multiplication CE1, modes de vie autrefois et culture anglophone au CP
(instance A), gestes d'alerte et sens du vote en EMC CP/CM1 (instance B)

- **Date** : 2026-10-02.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Nature du sprint** : entierement pilote par agents, sans ceremonie de daily - conforme a
  `squad/README.md`. Cinq lots de developpement affectes nominativement des l'ouverture du
  planning (`marc-dev` seul sur le lot de complexite moderee, `lea-dev` et `sofia-sm` casquette
  dev sur deux lots chacun), deux instances de `nadia-prof` auditant chacune un sous-ensemble
  disjoint des cinq lots.

---

## 1. Ce qui a coute - l'episode "oeufs" : un test qui refuse n'a pas toujours raison, et une
correction qui fait passer un test peut degrader un contenu juste

**Un distracteur juste ("oeufs", avec sa ligature) a fait rougir un test ; la premiere reaction a
ete de corriger le contenu, pas de verifier qui avait tort.** `PropositionsAccentueesJouablesIT`
a rougi a la fusion des cinq lots sur la ligature U+0153 dans `cp-anglais-culture.yaml`. Le
premier geste a ete de retirer la ligature du contenu (`5015d96c`) pour faire reverdir le test,
sur la foi d'un diagnostic jamais verifie : que le produit refuserait ce caractere. `theo-archi`,
en confirmant ArchUnit, a lu le code du test plutot que de supposer le diagnostic exact, et a
trouve que le refus venait du **harnais** de l'IT (encodage Windows-1252 qui ne sait pas
reproduire "oe"/"œ" avant meme d'envoyer la requete), pas du produit. Le chemin reel (decodage
UTF-8 cote serveur) ne leve jamais sur cette sequence. `nadia-prof`, saisie de la question, a
tranche sans ambiguite : *"Le contenu destine a l'enfant ne se plie pas a la limite d'un test.
Quand un test refuse un texte juste, c'est le test qu'on corrige."* Elle a aussi corrige un fait
que l'auteur de la correction avait enregistre a tort - "oeufs" n'etait pas le premier exemple de
ce defaut dans le depot, "coeur" l'etait depuis s34, jamais tranche sur le fond jusqu'ici.

**Le reflexe qui a manque, et qui a fini par s'appliquer** : avant de corriger un cote d'un
desaccord entre un test et un contenu, verifier lequel des deux dit une chose fausse. Un test qui
rougit n'est pas automatiquement la verite ; un contenu qui fait rougir un test n'est pas
automatiquement en faute. Ici, c'etait le harnais qui simulait mal le monde reel (un encodage plus
etroit que celui que le produit sait traiter), pas le produit ni le contenu.

**Ce que cet incident n'a pas coute.** La sequence a corrige elle-meme avant la cloture : la
degradation du contenu a dure moins de 35 minutes sur l'arbre (`5015d96c` a 19:04, harnais corrige
et ligature retablie a 20:06), et aucune des deux portes de cloture (`yanis-qa`, `theo-archi`) n'a
jamais mesure l'etat degrade. Le cout reel est celui d'un diagnostic refait une fois, plutot qu'une
degradation silencieuse du contenu livre a l'enfant.

**Ce que je retiens pour la suite, sans l'ecrire en regle faute d'un second cas pour la confirmer** :
quand un garde-fou de test mute un texte qu'il n'a jamais rencontre avant (un caractere, une forme
neuve), la premiere question a poser est "qu'est-ce que ce test simule, et le simule-t-il bien ?"
avant de modifier le contenu qui l'a fait rougir. `theo-archi` et `nadia-prof` l'ont fait ici ;
je ne l'ecris pas en amendement de process parce qu'une seule occurrence d'une methode qui finit
par bien fonctionner ne distingue pas encore une regle generale d'un cas particulier - a la
difference du constat de la section 2, qui s'est deja produit deux sprints de suite.

## 2. Ce qui a coute - trois refus sur cinq lots, et une partie d'entre eux introduits par la
correction elle-meme

**Taux de refus eleve : 3 lots sur 5 ont subi au moins un refus pedagogique reel ce sprint
(`QLM-CP-15` deux fois, `EMC-CP-14` une fois, `EMC-CM1-10` deux fois), et ce n'est pas une
premiere** : s46 portait exactement le meme taux (`MAT-CE1-05`, `EMC-CM1-09`, `SCI-CM1-16`, 3 lots
sur 5). Deux sprints de suite au meme taux rend le motif plus interessant qu'un accident isole.

**Le motif commun, sur les deux refus en seconde instance de ce sprint, n'est pas une erreur de
conception initiale mais un defaut introduit par la correction precedente elle-meme** :

- **`EMC-CM1-10`, 2e passage.** `nadia-prof` avait ecrit au 1er passage deux formulations
  acceptees mot pour mot (*"tous les citoyens majeurs"* ou *"toute personne majeure de nationalite
  francaise"*). La correction soumise n'a repris aucune des deux : elle a paraphrase en ajoutant
  une exclusivite ("reserve aux ..."), qui s'est averee fausse pour une raison que la formulation
  d'origine de `nadia-prof` evitait justement. Elle l'ecrit elle-meme : *"Si la correction avait
  repris l'une des deux formes que j'avais ecrites, je l'aurais acceptee."*
- **`QLM-CP-15`, 3e passage (reserve `AUT-CP-R1`, non bloquante mais de la meme famille).** La
  correction des items 4 et 7, demandee au 2e passage pour qu'ils situent l'objet dans le temps,
  a choisi pour l'item 7 une formulation qui reutilise le mot *robinet* - exactement le mot que
  l'item 13 porte en bonne reponse. La paire 7/13 avait deja ete fermee au 1er passage ; la
  correction du 2e passage l'a rouverte sans que personne ne le voie avant le passage suivant.

**Pourquoi ces deux defauts ont ete vus, et pas au prix d'une fuite en production** : `nadia-prof`
rejoue **le paquet entier** a chaque passage, jamais seulement l'item corrige - pratique deja en
place depuis plusieurs sprints et qu'elle nomme elle-meme a chaque fiche (*"Je rejouerai le paquet
entier a son retour, pas seulement l'item corrige"*). C'est ce rejeu complet qui a trouve la
collision de `AUT-CP-R1` et l'exclusivite fausse d'`EMC-CM1-10`, pas une relecture cible du seul
diff.

**Ce qui pourrait aider, cote developpeur, avant que le paquet ne revienne devant `nadia-prof`** :
deux gestes distincts, pour deux causes distinctes.

1. **Quand `nadia-prof` ecrit une formulation exacte comme forme acceptee ("exemple de forme"),
   la correction la reprend verbatim**, sauf motif ecrit explicite de s'en ecarter. Paraphraser une
   formulation deja validee pour "dire la meme chose autrement" a introduit l'erreur sur
   `EMC-CM1-10` : la paraphrase n'etait pas equivalente, et personne n'a verifie qu'elle l'etait
   avant de la soumettre.
2. **Quand une correction touche un indice ou un enonce, le developpeur cherche le mot de la bonne
   reponse qu'il vient d'y placer dans le reste du fichier du paquet** (recherche simple du mot,
   pas un outil neuf) **avant de soumettre**, pour verifier qu'aucun autre item du meme paquet n'a
   ce mot pour bonne reponse. C'est exactement le geste qui aurait trouve que "robinet" apparaissait
   deja comme reponse de l'item 13 avant de l'ecrire dans l'indice de l'item 7.

Je propose ces deux gestes comme amendement de process (section ci-dessous) : aucun des deux
n'est un garde-fou automatisable a ce jour (aucun test ne lit la correspondance d'un indice a une
reponse d'un autre item), donc la seule garde reste la discipline du developpeur avant soumission,
en plus du rejeu complet deja fait par `nadia-prof`.

## 3. Ce qui a marche - deux lots fermes sans aucun refus, et un blocage de faisabilite leve avant
l'engagement

**`MAT-CE1-07` et `LVE-CP-06` ferment des le premier passage**, sans aucun refus. Le blocage de
faisabilite qui pesait sur `MAT-CE1-07` avant meme l'ouverture du planning - le fait `10 × 1`
n'admettait qu'un seul distracteur dans les quatre familles existantes, rendant la bonne reponse
predictible - a ete leve par un amendement pedagogique de `nadia-prof` (cinquieme famille de
distracteur) avant l'horodatage de debut du planning, et cet amendement a tenu sans aucune reprise
tout le reste du sprint : la seule reserve sur ce lot (`TAB-CE1-R1`) porte sur un indice, pas sur
la condition amendee elle-meme.

## 4. Actions pour la suite

**Je route, je ne tranche pas, conformement a mon role.**

- Les cinq stories de correction differee (huit reserves : `TAB-CE1-R1`, `AUT-CP-R1`,
  `ANG-CP-R1`/`ANG-CP-R2`, deux reserves de "Donner l'alerte", deux reserves de "Le vote et la
  representation") sont deja ecrites dans le backlog par `camille-po` ; je ne leur fixe pas de
  sprint.
- `SOP-CE1-R1` reste routee a `nadia-prof` elle-meme depuis s46, instruite hors du chiffrage de
  s47 (section 6 de `planning.md`) ; la reprise du paquet qu'elle conditionne n'est pas engagee.
- L'amendement de `squad/README.md` section 9, etape 2 (les deux gestes de la section 2
  ci-dessus) est ecrit dans ce meme geste de retro ; aucune action supplementaire n'est due de ma
  part sur ce point.
- Le motif de l'episode "oeufs" (section 1) n'appelle pas d'amendement de process a lui seul,
  faute d'un second cas pour distinguer une regle generale d'un incident isole ; je le signale
  explicitement plutot que de le laisser silencieux, au cas ou un second cas survenait.

## Decisions prises

**Un amendement a `squad/README.md`** (section 9, etape 2 "Developpement") : deux gestes neufs a
la soumission d'une correction de contenu en reponse a un refus pedagogique - reprendre verbatim
toute formulation que `nadia-prof` a ecrite comme forme acceptee, sauf motif ecrit explicite de
s'en ecarter ; et rechercher, dans le reste du fichier du paquet, le mot de la bonne reponse qu'une
correction d'indice ou d'enonce vient d'y placer, avant de soumettre. Motif et preuve : section 2
ci-dessus.

Aucune `DEC` ni aucun `ADR` n'est cree ou amende par cette retro : elle constate ce qui a
fonctionne et ce qui a coute dans l'orchestration de s47, et amende la seule regle de process qui
s'en degage.
