# Retro - s35 - fermer ce qui n'a pas recu le go pedagogique

- **Date** : 2026-09-11.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Nature du sprint** : entierement pilote par agents, sans ceremonie de daily - conforme a
  `squad/README.md`, section sur le daily ("le daily n'est pas journalise ; il reste une
  synchronisation de travail"). Plusieurs instances ont ete reprises en cours de route
  (`s35-theo` -> `s35-theo-confirm`, `s35-relecture` -> `s35-relecture-2`, une instance de cloture
  dediee `s35-camille-cloture`). Ce document nomme ce qui a fonctionne dans cette orchestration et
  ce qui a coince, sur le patron des amendements deja portes dans `CLAUDE.md` et `squad/README.md`.

---

## 1. Ce qui a marche

**Le gel d'objectif a tenu jusqu'au bout, a l'unite pres.** Le planning fixait explicitement
"aucune ligne neuve" ; le sprint ferme exactement une ligne (`QLM-CP-14`), et le seul lot qui en
aurait ouvert une seconde (`QLM-CP-11`) est refuse plutot que ferme de justesse pour tenir un
compte. Aucune pression de cloture n'a fait bouger le verdict pedagogique.

**Une confirmation ArchUnit qui corrige ses propres phrases avant de les faire foi.**
`theo-archi` a trouve, dans son propre `ADR-0092`, une affirmation fausse ("levier gratuit") et un
nom de constante qui promettait un audit non fait - et les a corriges lui-meme, dans le meme
commit qui accepte l'`ADR`, avant de s'appuyer dessus. C'est exactement la discipline que
`ADR-0015` demande ("un nom qui promet plus que son perimetre") appliquee a son propre auteur, pas
seulement au code d'un autre agent.

**`nadia-prof` corrige sa propre regle en la citant, sans la durcir apres coup.** `ZON-16`, ecrite
a s34, refusait son propre exemple d'acceptable ("en haut, au centre" compte quatre mots et deux
reperes, alors que la regle en autorisait trois et un). La reformulation du 2026-09-11 est
explicitement datee, la version precedente reste lisible dans le verdict de s34, et le motif ecrit
("c'est l'exemple qui dit ce que je voulais dire") est verifiable par quiconque relit les deux
textes. C'est le meme geste que la lecon retenue en s01 sur une assertion a rendre plus stricte,
applique ici dans l'autre sens - assouplir sans affaiblir un jugement deja rendu.

**Un refus qui chiffre sa propre limite plutot que de se contenter d'un verdict.** Le refus de
`S35-QLM11-PLANCLASSE` porte, zone par zone, le debordement exact en pixels (17 a 95 px sur les
six zones) et la propriete generale qui en decoule ("un libelle positionnel replie sur deux lignes
demande un cadre d'au moins 109 x 88 px, et trois des six elements d'un plan de classe sont des
bandes minces par nature"). Un refus de cette forme est directement actionnable par
`ines-design` et `theo-archi` sans qu'un troisieme sprint ait besoin de re-mesurer ce qui l'a
motive.

**La reprise d'instance a fonctionne sans perte de traçabilite.** `s35-theo` a ete repris en
`s35-theo-confirm` pour la confirmation de porte technique, `s35-relecture` en `s35-relecture-2`
pour un residu de relecture ; les deux reprises sont nommees dans leurs propres documents
(`confirmation-archunit.md`, en-tete ; `relecture.md`, en-tete), avec l'arbre exact et le commit de
base. Aucune des deux reprises n'a laisse une ceremonie sans document ni un chiffre sans methode.

## 2. Ce qui a coince

**Un ecart de deux tests entre les mesures intermediaires (1479) et la mesure de cloture (1481),
non explique par le diff du dernier commit.** `theo-archi` et `yanis-qa` ont chacun mesure 1479 de
maniere independante et concordante sur leurs propres arbres ; la mesure de cloture sur l'arbre
fusionne final en trouve 1481, sans qu'un commit de documentation pur (`5e0f3e87`) explique la
difference. Aucune des deux mesures n'est fausse en elle-meme - chacune decrit l'arbre qu'elle a
effectivement mesure -, mais **aucun des deux rapports intermediaires ne nomme le commit exact sur
lequel il se tient** avec la meme precision que `theo-archi` l'exige de lui-meme pour un `ADR`
("l'etat mesure sur `f705d62a`"). C'est la lecon deja ecrite dans `CLAUDE.md` - "un chiffre annonce
par un agent se reverifie, il ne se relaie pas" - qui s'est appliquee une fois de plus, et le
correctif est le meme que d'habitude : mesurer soi-meme sur l'arbre final avant de cloturer, ce que
cette review fait, plutot que de chercher une cause qui n'apparait pas dans le diff.

**Le calcul du registre de dette a la cloture demande une reconciliation manuelle a chaque fois,
et personne n'a encore ecrit la regle qui l'automatiserait.** Pour la deuxieme fois consecutive
(apres `DEC-0087` a s34), la question "quels paquets repassent PAYE" ne se lit pas directement
dans les documents de sprint : il faut croiser la table des postes de `DEC-0086`/`DEC-0087`, l'etat
code par code de chaque fiche de conformite, et le regime de prix (mot pour mot, rejeu borne, tarif
de lot) que `nadia-prof` attribue a chaque reserve individuellement. `camille-po` a nomme la
question sans y repondre (`REGISTRE-reserves-neuves-s35.md`), et cette review y repond a son tour a
la main. Le mecanisme qui fait grossir la dette meme quand un sprint solde un paquet - une reserve
neuve nait sur le paquet qui vient d'etre remis a zero - est identifie depuis `DEC-0086`, mais
aucune methode ecrite ne dit encore comment le distinguer, sans reconstruction manuelle, d'un
paquet reellement solde sans reste. Ce n'est pas un obstacle bloquant pour ce sprint, mais c'est le
troisieme sprint consecutif ou cette reconciliation coute un travail d'enquete a la cloture plutot
qu'une lecture directe.

**Un garde-fou de forme continue de passer a cote de son objet apres deux sprints de constat.**
`PaquetCpEmcExclusionTest` a laisse passer, a s35 comme signale par `yanis-qa`, un second "bouton
au rabais" ecrit sous une formulation neuve ("ne rien dire du tout" a la place de "attendre que
cela s'arrete tout seul") : le garde-fou verifie une chaine de caracteres exacte, pas la categorie
pedagogique qu'elle incarne. Le symptome est nomme depuis au moins un sprint anterieur et reste
route a `theo-archi` sans qu'une regle generale (une categorie verifiable sans liste ecrite a la
main) n'ait ete posee. Un garde-fou de regression sur un incident precis n'est pas fautif en soi -
mais rien dans son nom ni son commentaire ne dit qu'il ne couvre qu'un incident, et c'est cette
absence-la qui cree le faux vert.

## 3. Amendements a `squad/README.md`

Aucun amendement propose a `squad/README.md` par cette retro. Les deux points de la section 2
("ce qui a coince") ne demandent pas un changement de process de ce depot au sens de la section 4
("le sprint est un increment livrable") ni de la section 9 ("porte technique") : le premier est
une discipline de citation d'arbre deja ecrite ailleurs (`CLAUDE.md`, "un chiffre se reverifie") et
appliquee une fois de plus a la lettre dans cette review ; le second est un obstacle de conception
de garde-fou deja route a `theo-archi`, sur le meme circuit que celui utilise pour tout obstacle
technique de ce depot. Aucun des deux ne revele un trou dans la gouvernance des ceremonies
elles-memes.

## Decisions prises

**Aucune decision de sprint neuve n'est prise dans cette retro.** Elle ne fait que constater ce qui
a fonctionne et ce qui a coince dans l'orchestration de s35 ; les decisions produit et
d'architecture du sprint (`DEC-0088`, `ADR-0091`, `ADR-0092`, `ADR-0080`) sont deja enregistrees
dans `review.md`, section "Decisions prises", et ne sont pas reprises ici une seconde fois.
