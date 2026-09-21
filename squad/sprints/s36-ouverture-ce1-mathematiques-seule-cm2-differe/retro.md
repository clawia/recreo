# Retro - s36 - ouverture du perimetre CE1, mathematiques seule (CM2 differe)

- **Date** : 2026-09-21.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Nature du sprint** : entierement pilote par agents, sans ceremonie de daily - conforme a
  `squad/README.md`. Douze worktrees provisionnes en tout, dont deux dedies a la seule confirmation
  de porte technique (`s36-yanis`, `s36-theo-porte`) et deux dedies a des correctifs trouves par
  cette meme porte (`s36-lea-fix`, `s36-theo-regle`). Ce document nomme ce qui a fonctionne dans
  cette orchestration et ce qui a coince.

---

## 1. Ce qui a marche

**Six lots disjoints, fusionnes sans aucun conflit reel malgre une verification systematique.** Les
six premiers worktrees (`s36-camille`, `s36-nadia`, `s36-ines`, `s36-theo`, `s36-lea`, puis les
lots de cloture) ont chacun ete verifies fichier par fichier avant fusion plutot que sur la seule
foi des rapports d'agents - et une fois, cette verification a paye : `s36-theo` et `s36-lea`
touchaient tous deux `ADR-0093`, `ADR-0094` et `ADR-index.md`, un recoupement qu'aucun des deux
rapports ne nommait. Les trois fichiers etaient byte-identiques d'un worktree a l'autre (`lea-dev`
avait copie le texte de `theo-archi` avant que sa branche ne soit fusionnee), et la fusion s'est
faite sans conflit - mais elle aurait pu ne pas l'etre, et la seule facon de le savoir etait de
diffuser les deux versions avant de fusionner, pas de faire confiance a l'enumeration "aucun fichier
commun" du message de routage.

**Deux dettes trouvees a la confirmation de porte technique, payees le jour meme par leur propre
auteur.** `theo-archi` a trouve, dans sa propre confirmation d'`ADR-0094`, une regle executable
absente et un ecart entre sa decision 5 et le gabarit reellement servi - et les a fermees dans un
second worktree, le meme jour, avant que le sprint ne se cloture avec un `ADR` durablement
`Propose`. `yanis-qa` a trouve un relais d'`ADR-0094` sans test dedie ; `lea-dev` l'a ferme dans un
correctif nomme comme tel. Aucune des trois dettes n'a attendu un sprint suivant.

**Un refus qui se leve en nommant precisement ce qui a change et ce qui n'a pas change.**
`nadia-prof` a rejoue `S36-QLM11-PLANCLASSE` dans un navigateur reel plutot que de relire un
diff, et son verdict distingue explicitement ce que la reprise corrige (0,00 px de debordement, la
barre qui bloquait le refus de s35) de ce qu'elle assouplit par elle-meme et le dit comme tel
(le croisement de trait, reclasse de defaut a silhouette identifiable) et de ce qu'elle refuse de
reclasser (`PCL-R1`, `PCL-R2`, deja gradees non bloquantes le 2026-09-11, non requalifiees en motif
de refus a la faveur d'un sprint de plus). C'est la meme discipline que la review de s35 avait
notee sur `ZON-16` : assouplir sans affaiblir un jugement deja rendu.

**Une reserve trouvee en jouant devient une ligne de referentiel dans la meme relecture, sans
attendre un audit separe.** `nadia-prof` a trouve, en relisant son propre `competences-ce1.md`,
qu'il manquait la ligne sur le sens de la division - et plutot que de l'ajouter dans le meme geste
que son verdict de sprint ("une ligne de referentiel s'ecrit posement, pas en marge d'un audit"),
elle l'a routee a elle-meme via `camille-po`, qui l'a explicitement note comme "pas une dette" dans
`DEC-0089` amendee. La ligne existe a la cloture, ecrite avec le meme soin que les dix-sept
premieres.

## 2. Ce qui a coince

**Deux allers-retours ont ete necessaires pour fermer `ADR-0094`, sur un chiffre que la premiere
mesure de porte technique aurait pu prevenir.** `yanis-qa` a trouve, avant meme la confirmation de
`theo-archi`, que le relais 1 d'`ADR-0094` n'avait aucun test dedie - un signal que la regle
executable de la decision 6 elle-meme manquait probablement aussi, et qui n'a ete verifie comme tel
qu'a la confirmation ArchUnit, dans un second passage. Les deux dettes ont ete payees le jour meme,
mais cela a demande d'ouvrir un huitieme puis un neuvieme worktree (`s36-lea-fix`, `s36-theo-regle`)
la ou une lecture plus large du signal de `yanis-qa` a la premiere confirmation aurait pu router les
deux corrections dans le meme geste.

**Une allocation d'identifiants ecrite sans son enregistrement, trouvee seulement a l'assemblage.**
`s36-nadia-audit` a ecrit quatre reserves neuves (`PCL-R3` a `PCL-R6`) dans sa fiche de conformite
sans ajouter leurs quatre lignes a `squad/decisions/REGISTRE-identifiants.md`, faisant rougir
`mvn -B clean verify` sur `PAR-5` clause 2 a la fusion. Ce n'est pas une premiere : le registre
porte lui-meme, a sa propre note du jour, l'annonce explicite de ce risque ("d'autres lots de s36
peuvent allouer en parallele sur des branches soeurs que je ne lis pas... la reparation sera
arithmetique... c'est `sofia-sm` qui la fait, a l'endroit prevu") et un precedent identique a s34.
La reparation est bien arithmetique, comme prevu, mais le fait qu'elle se reproduise a chaque sprint
ou plusieurs instances allouent en parallele confirme que c'est une limite structurelle de la
verification par worktree isole, nommee par `theo-archi` lui-meme dans ce meme registre, et non un
defaut d'une instance en particulier.

**La proposition de design de `F2` a ete infirmee par la mesure, et rien ne l'a corrigee dans le
sprint qui l'a mesuree.** `ines-design` a propose une forme pour le repli de la barre a trois puces
sous 360 px ; `theo-archi` l'a mesuree sur le rendu reellement servi et a trouve un ecart de 12 px
non corrige par la proposition. L'obstacle reste ouvert, route au sprint suivant, sans qu'une
troisieme instance n'ait pu fermer la boucle proposition-mesure-correction dans le meme sprint - le
meme motif deja observe a s35 sur `F2` avant sa premiere mesure.

## 3. Amendements a `squad/README.md`

Aucun amendement propose a `squad/README.md` par cette retro. Le premier point de la section 2 (deux
allers-retours pour `ADR-0094`) est un obstacle de sequencement d'un sprint donne, pas un trou dans
le process de ce depot ; le second (allocation d'identifiants non enregistree a l'assemblage) est
deja documente comme limite structurelle connue, avec sa reparation deja prevue et executee a
l'endroit que le registre lui-meme designe - rien n'indique qu'un changement de process ferait mieux
qu'une discipline deja ecrite et deja suivie. Le troisieme (`F2` non ferme dans le sprint qui l'a
mesure) est un obstacle de conception routable au sprint suivant par les canaux existants.

## Decisions prises

**Aucune decision de sprint neuve n'est prise dans cette retro.** Elle ne fait que constater ce qui
a fonctionne et ce qui a coince dans l'orchestration de s36 ; les decisions produit et
d'architecture du sprint (`DEC-0089`, `DEC-0090`, `ADR-0093`, `ADR-0094`) sont deja enregistrees
dans `review.md`, section "Decisions prises", et ne sont pas reprises ici une seconde fois.
