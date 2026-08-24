# DEC-0042 - Perimetre du sprint 19 : contenu seul en francais CM1/CP, conception de la saisie sans
# code, lecture a voix haute classee non couvrable en l'etat

- **Type** : portee, arbitrage de trajectoire
- **Date** : 2026-08-24
- **Auteur** : camille-po
- **Sprint** : s19
- **Competence programme visee** : francais, CP et CM1 (cycle 2 et cycle 3) pour les sections 2 et
  5 ; transverse - pas de contenu pedagogique pour les sections 3, 4 et 6.
- **Statut** : Acceptee (les trois arbitrages qu'elle trace sont deja rendus par l'utilisatrice ;
  cette decision les fixe par ecrit avec leur motif et instruit ce qui en decoule)

## Contexte

s18 ferme sur **10 lignes `couvert-valide` sur 46 (21,7 %)**
(`squad/programme/MATRICE-couverture.md`, "Synthese de cloture de s18"), premier mouvement reel de
couverture depuis quatre sprints. L'utilisatrice rend trois arbitrages pour l'ouverture de s19 et
valide un perimetre : contenu seul sur quatre lignes de francais, conception de la saisie sans
capacite de developpement, audio confirme hors perimetre, et une ligne classee hors d'atteinte de
ce produit tel qu'il est defini.

## 1. Arbitrage 1 - perimetre de s19 : contenu seul + conception de la saisie

**Decision de l'utilisatrice** : un lot de couverture servi par la mecanique `bandit-manchot`
existante (Lot A, section 2 ci-dessous), et en parallele, sans capacite de developpement, la
conception de la mecanique de saisie (Lot C, section 6).

**Ce que cela confirme** : le principe directeur du produit (`CLAUDE.md`) - une mecanique
reutilisable, decouplee du contenu - continue de tenir sur quatre lignes de plus, sans aucun code
nouveau dans `domain/mechanic`. La conception de la saisie, elle, avance sans consommer de
capacite de developpement, exactement dans l'esprit de ma demande de s13 (`DEC-0030`, section 6) :
instruire une seconde mecanique avant que la reserve de contenu seul ne soit epuisee, pas au moment
ou elle le devient.

## 2. Arbitrage 2 - audio reporte, hors perimetre de s19

**Decision de l'utilisatrice** : les trois lignes de conscience phonologique du CP (denombrer les
syllabes, les localiser, identifier un son) restent a zero ce sprint. Motif ecrit pour qu'il ne se
redecouvre pas dans six sprints :

- **Aucun agent de cette squad ne peut produire un corpus de syllabes et de phonemes francais dit
  par une voix humaine**, embarque dans le fat jar (`theo-archi`, avis d'ordre de grandeur,
  `squad/sprints/s13-grands-nombres-et-chemin-de-catalogue/avis-ordre-de-grandeur-seconde-
  mecanique.md`, section "Audio"). Ce n'est ni du code ni du contenu YAML : c'est un
  enregistrement, et c'est un obstacle de production, pas de conception.
- **L'alternative de synthese vocale embarquee change la nature du livrable** : les voix de qualite
  se comptent en dizaines a centaines de megaoctets, ce qui ferait passer le fat jar de quelques
  megaoctets a plusieurs centaines (meme avis, meme section). C'est une decision de produit et
  d'exploitation que je ne prends pas a la place de l'utilisatrice, et elle ne la prend pas ce
  sprint.
- **Le seuil pose par `nadia-prof` reste la condition d'ouverture, pas une preference** :
  "*si la capacite audio ne peut pas rendre un son net, identique a chaque ecoute, et reecoutable a
  la demande par l'enfant, alors je prefere que ces trois lignes restent vides*"
  (`squad/conformite/AVIS-PRIORITE-PEDAGOGIQUE-s13-seconde-mecanique.md`, section "Ce que je ne
  dis pas et la condition qui ferait bouger mon ordre"). Aucune des deux voies disponibles
  (corpus enregistre, synthese embarquee) ne satisfait ce seuil aujourd'hui sans le cout nomme
  ci-dessus.

**Ce que je n'ecris pas ici** : un horizon ou une priorite entre audio et les autres besoins
mecaniques non couverts. `nadia-prof` a deja rendu son ordre (audio, puis visuel, puis saisie,
memes references) ; cette decision ne le rejuge pas, elle documente pourquoi il ne se traduit pas
en story ce sprint precis - un ordre pedagogique et une capacite de production ne sont pas la
meme chose.

## 3. Arbitrage 3 - "Lire a voix haute un texte court et simple avec fluidite croissante" : statut
   `non couvrable en l'etat`

**Decision de l'utilisatrice** : cette ligne (francais CP, domaine "Lecture et comprehension de
l'ecrit") recoit un quatrieme statut dans `squad/programme/MATRICE-couverture.md`, distinct des
trois statuts existants et de la mecanique de note en prose deja utilisee ailleurs (comparer/ranger
en s09, synonymes/antonymes en s17) : **`non couvrable en l'etat`**.

### Pourquoi une reponse ecrite maintenant, et pourquoi ce statut plutot qu'une note

`theo-archi` a lui-meme ecrit, a s13, que cette ligne "*ne devrait pas etre comptee dans une
reserve de contenu ni dans un horizon de sprints : elle devrait etre nommee non couvrable en
l'etat dans MATRICE-couverture.md, ce qui est une ecriture de camille-po et non de moi*"
(`squad/sprints/s13-grands-nombres-et-chemin-de-catalogue/avis-ordre-de-grandeur-seconde-
mecanique.md`, section "Ce que le depot ne pourra pas faire"). Cette ecriture etait due depuis s13
et n'avait jamais ete faite ; l'arbitrage de l'utilisatrice la declenche, je l'execute ici.

### Le motif, au mot pres de ce qui est deja etabli

La reconnaissance vocale que cette ligne exige contredit **trois contraintes fondatrices** du
produit a la fois, et une quatrieme question distincte se pose en plus :

1. **Le fat jar autonome, sans dependance reseau** (`CLAUDE.md`). Une reconnaissance dans le
   navigateur delegue a un service distant du fournisseur - une dependance reseau sur une
   application qui se lance par `java -jar` et n'appelle personne. Une reconnaissance hors ligne
   embarquee demande un modele acoustique de plusieurs centaines de megaoctets dans le jar.
2. **Le verdict reste serveur et testable** (`ADR-0008`). Le verdict de fluidite serait rendu par
   un moteur probabiliste, sur un signal audio, avec un taux d'erreur, sur la voix d'un enfant de
   CP qui apprend a lire - le cas le plus difficile pour ce genre de moteur. Il n'y a pas de test
   de non-regression a ecrire sur un verdict de cette nature.
3. **Ce qui peut etre teste cote serveur doit l'etre, jamais dans le JavaScript client** (dette 4
   de `CLAUDE.md`). Le pilotage du micro vivrait necessairement dans le navigateur : exactement le
   retour du comportement de jeu loge cote client que cette squad s'est explicitement interdit de
   reproduire.
4. **Une question de donnee personnelle sur la voix d'un enfant**, distincte des trois contraintes
   d'architecture ci-dessus et qui ne se resout pas techniquement : capter la voix d'un enfant pour
   la faire analyser, meme localement, pose une question que je ne tranche pas seule (voir section
   "Ce que je route").

(Source complete des quatre points : `squad/sprints/s13-grands-nombres-et-chemin-de-catalogue/
avis-ordre-de-grandeur-seconde-mecanique.md`, section "Ce que le depot ne pourra pas faire, et ce
n'est pas une question de sprints".) Un jugement pedagogique s'ajoute a ces quatre points, deja
rendu par `nadia-prof` sans qu'on le lui demande : "*la fluidite de lecture s'evalue par un adulte
qui ecoute, avec un jugement sur le ton, les liaisons, le respect de la ponctuation, qu'aucune
reconnaissance vocale ne rend*" (`AVIS-PRIORITE-PEDAGOGIQUE-s13-seconde-mecanique.md`, section
"Audio"). Elle-meme ne reclamera pas cette ligne.

### Ce que `non couvrable en l'etat` veut dire, et ce qu'il ne veut pas dire

- **Ce n'est pas `non couvert`** : `non couvert` dit qu'aucun jeu ne travaille la competence
  aujourd'hui, mais laisse ouverte la question de savoir si un jeu le pourrait avec du contenu, une
  mecanique visuelle ou de l'audio. Laisser cette ligne `non couvert` la range dans la meme
  categorie que "reconnaitre des figures planes" ou "denombrer jusqu'a 100", qui n'attendent qu'une
  mecanique ou un arbitrage pedagogique - ce n'est pas comparable, et cette confusion est
  exactement ce que `theo-archi` demandait de ne plus laisser filer.
- **Ce n'est pas un abandon du referentiel** : la ligne reste ecrite dans
  `squad/programme/francais/competences-cp.md` et dans la matrice, avec son motif. Rien n'est
  supprime ; un jugement est rendu visible.
- **Ce n'est pas definitif au sens ou aucune decision ne pourrait jamais le rouvrir** :
  `theo-archi` le nomme lui-meme - "*si l'utilisatrice acceptait un jour que recreo cesse d'etre
  autonome hors ligne, la premiere contrainte tomberait*" - mais les deux autres (verdict serveur
  testable, rien de metier cote client) resteraient, et la question de donnee personnelle resterait
  entiere. Rouvrir cette ligne suppose de revenir sur au moins une des contraintes fondatrices du
  produit, ce qui est une decision de l'utilisatrice, pas un geste de planning.
- **Elle cesse d'etre comptee comme une dette qu'un sprint pourrait fermer.** C'est la consequence
  operationnelle : elle ne figure plus dans aucun horizon de "lignes atteignables par du contenu
  seul", aucun compte de report ne s'applique a elle (elle n'est reportee par aucune capacite, elle
  est hors d'atteinte par construction), et aucun rapport de sprint n'a plus a se justifier de ne
  pas l'avoir ouverte.

### Le denominateur, avec sa methode

**Le denominateur honnete de la couverture devient 45 lignes atteignables sur 46.** Je garde les
deux chiffres cote a cote plutot que de n'en montrer qu'un :

- **46** reste le compte structurel du referentiel : quatre fichiers de competences, une ligne par
  competence, inchange par cette decision - le referentiel n'est pas modifie, aucune competence
  n'est retiree du programme.
- **45** est le denominateur que j'utilise desormais pour tout taux de couverture cite dans un
  rapport de sprint ou une decision : les lignes que ce produit peut, en principe, atteindre un
  jour par du contenu, une mecanique visuelle, une mecanique de saisie ou de l'audio recevable au
  sens du seuil de `nadia-prof`.
- **Le compte de lignes `couvert-valide` ne change pas** : 10 aujourd'hui, avant tout mouvement de
  s19. Le taux passe de 10/46 (21,7 %) a **10/45 (22,2 %)** par le seul effet du denominateur, sans
  qu'aucune ligne supplementaire n'ait ete validee. Je l'ecris explicitement pour qu'aucun rapport
  futur ne presente ce gain de 0,5 point comme un mouvement de couverture : ce n'en est pas un.

Detail d'application dans `squad/programme/MATRICE-couverture.md` : voir section 5 de cette
decision et le fichier lui-meme.

## 4. Quatre questions de validite de proxy dues depuis s13, jamais repondues - reposees a
   `nadia-prof`

`DEC-0030` (section 5) posait a `nadia-prof` quatre questions de validite de proxy, chacune
bloquant une ligne du referentiel, avant tout code sur ces lignes :

1. **Denombrer, lire, ecrire et decomposer les nombres jusqu'a 100 (CP)** - un pictogramme-
   caractere (une face de de, un glyphe repete) suffit-il a "denombrer" une quantite ?
2. **Reconnaitre et nommer des figures planes (CP)** - un glyphe geometrique Unicode (carre,
   triangle, cercle) suffit-il a "reconnaitre" une figure, ou faut-il un rendu reel ?
3. **Decoder un mot regulier inconnu par assemblage syllabique (CP)** - un choix a trois
   propositions entre des decoupages syllabiques ecrits satisfait-il le verbe "decoder", ou cette
   competence exige-t-elle que l'enfant assemble lui-meme les syllabes ?
4. **Relire et corriger son texte a l'aide de reperes simples (CM1)** - un choix entre plusieurs
   versions d'une phrase satisfait-il "relire et corriger **son** texte", ou cette competence
   suppose-t-elle un texte que l'enfant a lui-meme produit, ce qui la deplace vers la saisie ?

**J'ai verifie avant d'ecrire cette section, plutot que de le supposer** : `nadia-prof` a rendu un
avis a s13 (`squad/conformite/AVIS-PRIORITE-PEDAGOGIQUE-s13-seconde-mecanique.md`) sur une demande
distincte de la meme decision (l'ordre pedagogique entre visuel, saisie et audio - sa sixieme
demande), et elle nomme elle-meme, dans sa section "Suites hors de ma main", que "*les quatre
questions de validite de proxy de DEC-0030 section 4 ne sont pas traitees par ce fichier et
restent dues*". Une recherche sur les quatre formulations exactes ("pictogramme-caractere",
"glyphe geometrique", "assemblage syllabique", "relire et corriger") dans l'integralite de
`squad/conformite/` et `squad/decisions/produit/` ne fait remonter que `DEC-0030` elle-meme et cet
avis - **aucun fichier d'arbitrage dedie n'existe pour ces quatre questions**, six sprints apres
qu'elles ont ete posees.

**Ce que je repose, sans rien y ajouter ni retirer** : les quatre questions ci-dessus, telles
qu'ecrites par `DEC-0030`, section 4. Je ne les reformule pas et je ne prejuge d'aucune reponse.

**Ce que je change pour que ce manque ne redevienne pas invisible six sprints de plus** : une
question posee et non repondue restait, jusqu'ici, lisible seulement dans le corps d'un `DEC`
date - un document que personne ne relit systematiquement a chaque cloture. Je rends la dette
visible directement dans le document que toute la squad lit a chaque planning et chaque cloture :
`squad/programme/MATRICE-couverture.md` porte desormais, sous chacune des quatre lignes
concernees, une note nommant la question due et son age en sprints, avec un renvoi a cette
decision. `sofia-sm` peut ainsi verifier a chaque cloture de sprint, par une simple lecture de la
matrice et non par une recherche dans l'historique des `DEC`, si une question de ce genre est
restee sans reponse au-dela d'un sprint ou deux - le meme reflexe que "un report se compte"
(`CLAUDE.md`), applique ici a une question plutot qu'a un report de perimetre.

## 5. Lot A - quatre lignes de francais en contenu seul, mecanique `bandit-manchot` inchangee

Quatre stories, detaillees dans `docs/backlog/backlog.md`, section "Sprint 19" : `S19-1`
(antonymes, francais CM1, ferme la moitie manquante de `S17-3`), `S19-2` (accorder en genre et en
nombre dans le groupe nominal, francais CM1), `S19-3` (accorder le verbe avec son sujet, francais
CM1), `S19-4` (distinguer nom/verbe dans une phrase tres simple, francais CP). Le detail des
criteres, de la matiere premiere verifiee et du choix paquet neuf/extension pour chacune vit dans
le backlog, pas ici.

**Ce que cette section fixe, transversal aux quatre stories** : chacune ouvre une fiche de
conformite sous `squad/conformite/`, remplie a l'exception du bloc de validation, reserve a
`nadia-prof`. Chacune fait passer sa ligne de `squad/programme/MATRICE-couverture.md` de
`non couvert` a `en cours`, jeu et mecanique renseignes. **Aucune ne passe a `couvert-valide` par
ce planning** : ce statut n'est prononce que par `nadia-prof`, apres audit reel de l'increment
livre.

**Un point de sequencement que je nomme sans le trancher, route a `sofia-sm`** : les quatre
nouveaux paquets de contenu devront choisir la graphie ASCII ou accentuee de leur champ
`descripteur.matiere`, sous la contrainte symetrique du garde-fou d'`ADR-0049`
(`UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest`) - un paquet accentue rougit tant que la
majorite des paquets servis est en ASCII, et l'inverse une fois la bascule faite. La reponse depend
donc de l'ordre dans lequel `S19-5` (section 6) et ces quatre stories sont fusionnees, pas d'un
choix de redaction : c'est une question de capacite et d'ordonnancement, pas de perimetre.

## 6. Lot B - dette deja chiffree

- **`S19-5` - migration `ADR-0049`** (accentuation des descripteurs). La story existe deja au
  complet dans `docs/backlog/backlog.md` ("Story de migration - Les paquets de contenu servis
  portent leur matiere accentuee"), avec son perimetre etabli et son cout mesure par `theo-archi` a
  s17 (12 paquets servis, plus `cm1-synonymes`, plus 2 entrees de l'inventaire de
  `TexteAccentueExposeParLePortEntrantTest`, plus 3 chaines de descripteur, zero ligne de code de
  production, un seul commit obligatoire sous peine d'etat intermediaire rouge). **J'ai reverifie
  ce perimetre avant de l'affecter** : les 13 paquets servis aujourd'hui portent toujours
  `matiere: "Francais"` ou `matiere: "Mathematiques"` en ASCII, sans exception - le perimetre
  mesure a s17 est encore exact. Je l'affecte au sprint 19 sans en reecrire un mot.
- **`S19-6` - `SYN-16` / `BPM-8`**, le correctif qui ne mord pas entre 576 et 719 px de viewport.
  `nadia-prof` a mesure que le correctif de `SYN-11`/`BPM-1` ne mord qu'a partir de 720 px : entre
  576 et 719 px, cinq items de synonymes sur seize gardent leur verdict sous le pli (594,19 px) et
  six a sept items d'homophones gardent leur indice coupe
  (`DEC-0041-perimetre-sprint-18-fermer-trois-dettes-avant-toute-couverture-neuve-bpm-1-reclamee-a-
  nadia-prof.md`, amendement du 2026-08-23). J'ai tranche a la cloture
  de s18 que cette bande designe un appareil reel de la cible "ordinateur" (une fenetre de
  navigateur non maximisee ou partagee, pas un appareil fictif) et non un vide de reference. La
  story est ecrite dans `docs/backlog/backlog.md`, section "Sprint 19", avec ses criteres ; le
  mecanisme de correction (corriger le pli sans effondrer les marges laterales entre 576 et 719 px,
  ou l'inverse - contradiction deja nommee par `lea-dev`) reste entierement a instruire par
  `theo-archi`, non tranche ici.

## 7. Lot C - conception de la mecanique de saisie, sans capacite de developpement

Aucune story de code ce sprint. `theo-archi` ecrit l'`ADR` de la seconde mecanique et `nadia-prof`
rend l'arbitrage prealable qui va avec, sur le meme modele que chaque mecanique et chaque contenu
neuf de ce produit jusqu'ici.

### Ce que cette conception doit trancher pour que s20 puisse ouvrir des stories

Je nomme ici ce qui manque pour qu'une story de saisie soit ecrivable au prochain sprint, sans
trancher aucun de ces points moi-meme :

1. **Le verdict d'egalite textuelle.** `theo-archi` l'a deja pose comme la vraie question
   d'architecture de la saisie (`avis-ordre-de-grandeur-seconde-mecanique.md`, section "Saisie") :
   si le paquet porte l'ensemble des formes acceptees et que la mecanique se borne a une
   normalisation qu'elle possede deja (casse, espaces), le decouplage mecanique/contenu tient ; si
   la mecanique doit connaitre des regles d'orthographe pour juger une reponse proche mais fautive,
   elle connaitrait la matiere, ce que `uneMecaniqueIgnoreLaMatiere` interdit deja. C'est a
   `theo-archi` de trancher le mecanisme, mais c'est a moi de savoir, avant d'ecrire une story, quel
   niveau de tolerance orthographique un paquet de saisie devra assumer par sa propre donnee - je ne
   peux pas l'ecrire sans cette reponse.
2. **La liste des lignes candidates et leur ordre.** Cinq lignes ont ete identifiees comme
   "saisie" au sprint 13 (`DEC-0030`, section 4) ; je dois savoir, avant d'ouvrir la premiere story,
   si cette liste tient encore telle quelle ou si l'arbitrage prealable de `nadia-prof` en retire ou
   en deplace une (la quatrieme question de la section 4 ci-dessus, "relire et corriger son texte",
   pourrait deplacer cette ligne du bucket "contenu seul" vers "saisie" si sa reponse va dans ce
   sens).
3. **Le budget d'affichage d'un champ de saisie a la reference en vigueur** (744 x 551,
   `DEC-0040`). Chaque mecanique deja construite sur ce produit a du composer avec le pli
   (`DEC-0016`, `DEC-0025`, `M4`, `SYN-5`, `BPM-1`...) ; je ne veux pas decouvrir ce cout sur le
   premier increment de saisie livre, alors qu'il peut etre nomme des l'`ADR`.

### Rappel explicite : l'operation posee n'est pas une variante de la saisie

`theo-archi` a deja tranche cette distinction et je la reprends au mot pres pour qu'elle ne se
brouille pas au moment de sequencer : "*les deux lignes d'operation posee ne sont pas de la
saisie [...] une multiplication posee ou une division euclidienne, ce n'est pas un champ : c'est
une grille de champs, des retenues, un ordre de remplissage, et un verdict partiel case par case
[...] cela split la famille : trois lignes de saisie simple d'un cote, deux lignes d'operation
posee de l'autre, qui sont une troisieme mecanique et non une variante de la seconde. Je recommande
de ne jamais les compter ensemble dans un arbitrage de sequencement*"
(`avis-ordre-de-grandeur-seconde-mecanique.md`, section "Saisie"). `nadia-prof` va dans le meme
sens sur le fond pedagogique, independamment du cout : ces deux operations "se font sur du papier,
en colonnes, avec les retenues ecrites [...] une saisie a l'ecran, quelle qu'en soit la forme, ne
reproduira jamais ce geste" (`AVIS-PRIORITE-PEDAGOGIQUE-s13-seconde-mecanique.md`, section
"Saisie"). Je ne compte donc ni "effectuer une multiplication posee" ni "effectuer une division
euclidienne" dans la reserve de lignes que la conception de s19 doit debloquer : ce sont deux
lignes d'une troisieme mecanique, a instruire separement le jour ou elle est priorisee.

## Ce que je route

- **`theo-archi`** : l'`ADR` de la mecanique de saisie (section 7) ; le mecanisme de `S19-6`
  (`SYN-16`/`BPM-8`, section 6) ; l'ordre de fusion entre `S19-5` et les stories du Lot A quand la
  capacite sera affectee (section 5, note de sequencement).
- **`nadia-prof`** : les quatre questions de validite de proxy de `DEC-0030` section 4, reposees
  section 4 de cette decision ; l'arbitrage prealable de la mecanique de saisie une fois l'`ADR`
  de `theo-archi` rendu (section 7).
- **`sofia-sm`** : l'affectation et le sequencement des six stories du backlog (`S19-1` a `S19-6`) ;
  la verification, a chaque cloture de sprint, que les quatre questions notees dans la matrice
  (section 4) ne restent pas sans reponse au-dela d'un ou deux sprints.

## Justification pedagogique

Aucune sur les criteres des quatre stories du Lot A eux-memes - ils seront verifies par
`nadia-prof` sur les increments livres. Les arbitrages 2 et 3 (sections 2 et 3) reprennent des
jugements pedagogiques deja rendus par `nadia-prof` (seuil audio, verdict sur la reconnaissance
vocale) sans les rejuger.

## Impact

- `docs/backlog/backlog.md`, nouvelle section "Sprint 19" : six stories (`S19-1` a `S19-6`).
- Six fiches de conformite ouvertes ou reutilisees sous `squad/conformite/`, a l'exception du bloc
  de validation reserve a `nadia-prof`.
- `squad/programme/MATRICE-couverture.md` : quatrieme statut `non couvrable en l'etat` introduit
  dans "Comment la lire" ; ligne francais CP "Lire a voix haute..." reclassee avec son motif ;
  quatre lignes du Lot A passent `non couvert` -> `en cours` ; quatre notes ajoutees sous les
  tables concernees pour les questions de proxy dues (section 4) ; synthese chiffree ajoutee en bas
  de fichier avec les deux denominateurs (46 et 45).
- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0042` ajoutee.

## Ce que cette decision ne fait pas

- Elle ne tranche aucune question technique : ni le mecanisme de `S19-6`, ni celui de la mecanique
  de saisie, ni l'ordre de fusion entre `S19-5` et le Lot A - toutes routees a `theo-archi`.
- Elle ne prejuge d'aucun Done pedagogique sur `S19-1` a `S19-6` : ces jugements restent
  entierement ceux de `nadia-prof`, sur les fiches ouvertes.
- Elle ne fait passer aucune ligne de `squad/programme/MATRICE-couverture.md` a `couvert-valide`.
- Elle ne rouvre pas la ligne "lire a voix haute" : seule une decision future de l'utilisatrice, sur
  au moins une des trois contraintes fondatrices nommees en section 3, pourrait le faire.
- Elle ne repond a aucune des quatre questions de proxy qu'elle repose : ces reponses restent
  entierement celles de `nadia-prof`.

## Amendement du 2026-08-24 (meme jour, second passage) - nadia-prof a repondu aux quatre questions
   de proxy pendant que cette decision s'ecrivait

`nadia-prof` travaillait en parallele sur la branche `s19-nadia`, worktree dedie, lignee verifiee.
Son arbitrage (`squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md`)
repond aux quatre questions de la section 4 ci-dessus. Cette section les amende a leur date plutot
que de les reecrire : les notes de `squad/programme/MATRICE-couverture.md` qui disaient une reponse
due restent lisibles telles qu'ecrites au moment ou elles etaient vraies, et portent desormais, a
cote, l'amendement du meme jour.

### Verification demandee par le coordinateur avant de reprendre

**Aucune graphie fautive n'est entree dans mon lot.** J'ai relu `docs/backlog/backlog.md`,
`squad/programme/MATRICE-couverture.md` et les quatre fiches ouvertes ce sprint : aucune ne cite la
ligne de relecture CM1 avec le mot "accords" ajoute. La seule occurrence de cette ligne dans mon lot
(`squad/programme/MATRICE-couverture.md`, tableau francais CM1) porte deja l'intitule exact du
referentiel, *"Relire et corriger son texte a l'aide de reperes simples (majuscules,
ponctuation)"*, verifie caractere pres caractere contre
`squad/programme/francais/competences-cm1.md`. Rien a corriger de ce cote.

### Les quatre verdicts

| Question (`DEC-0030` section 4) | Verdict de `nadia-prof` | Famille apres arbitrage |
|---|---|---|
| 1. Denombrer, lire, ecrire, decomposer jusqu'a 100 (math CP) | **partiel** : REFUSE sur "denombrer", ACCEPTE SOUS CONDITIONS (`DEN-1` a `DEN-8`) sur "lire, ecrire, decomposer" | contenu seul (moitie) + visuel (moitie, "denombrer") |
| 2. Reconnaitre et nommer des figures planes (math CP) | **REFUSE** | visuel (`FIG-1` a `FIG-4`) |
| 3. Decoder un mot regulier par assemblage syllabique (francais CP) | **REFUSE** comme proxy ecrit ; le format qui la sert reellement existe et ne demande pas de reconnaissance vocale | audio, emission seule (`SYL-1` a `SYL-4`) |
| 4. Relire et corriger son texte (francais CM1) | **REFUSE**, et la saisie ne la debloque pas non plus | hors de portee de ce produit tel que le referentiel l'ecrit |

Motifs complets dans le fichier source, non recopies ici : groupement par dix non controlable dans
une chaine qui retourne a la ligne selon l'ecran et plafond reel de dix objets par ligne pour la
question 1 ; proportions d'un glyphe qui sont celles de la police et non du contenu, demontre par
`contenu-repris/associer/cp-formes.yml` qui associe deja un carre a la cible "rectangle", pour la
question 2 ; le proxy ecrit teste la segmentation et non l'assemblage et affiche toujours le mot qui
doit rester inconnu, pour la question 3 ; dependance a une autre ligne non couverte (rediger un
texte) et jugement de correction non automatisable, pour la question 4.

### Le defaut trouve dans la matiere premiere des figures planes, verse au dossier

`contenu-repris/associer/cp-formes.yml` associe le glyphe carre a la cible "rectangle" - le seul
materiau du depot sur cette competence, et il enseigne l'inverse exact de ce qu'elle existe pour
faire distinguer. Ce n'est pas du contenu servi et cela n'ouvre aucune story ce sprint. Je le nomme
ici pour qu'il ne soit pas redecouvert le jour ou la famille visuelle ouvrira - ce que le refus 2
vient de rendre probable plutot qu'hypothetique.

### Le recompte des familles, avec la methode de `nadia-prof`

Elle part du tableau de `DEC-0030` section 4 (35 lignes `non couvert` a s13) et n'y applique que ses
quatre verdicts, sans reouvrir le reste :

| Besoin mecanique | `DEC-0030` (s13) | Apres cet arbitrage (s19) |
|---|---|---|
| Contenu seul | 19 entieres + 1 partielle | **15 entieres + 2 partielles** |
| Visuel | 6 entieres + 1 partielle | **7 entieres + 2 partielles** |
| Saisie | 5 | **5, inchange** |
| Audio (emission) | 3 | **4** |
| Hors de portee | 1 | **2** |

Le total reste 35, deux lignes etant comptees en deux moities (fractions, deja partagee depuis s13 ;
denombrement, nouvellement partagee). Je reprends ce tableau tel quel : je n'ai aucun motif de le
compter autrement, et refaire le calcul a sa place reviendrait a mettre en doute un travail que je
n'ai aucune raison de mettre en doute.

**Consequence pour l'horizon de `DEC-0030` section 6, que cette decision citait en section 1 :**
l'estimation "quatre a six sprints" de contenu seul restant reposait sur une reserve de 19 lignes
entieres plus une moitie (19,5 en equivalent-ligne). Elle est desormais de 15 plus deux moities (16
en equivalent-ligne). Au rythme observe sur ce produit (4 lignes ouvertes a s9, 3 a s12, 3 proposees
a s13, 4 dans le Lot A de ce sprint - une moyenne de 3,5 lignes par sprint sur ces quatre points de
mesure), 16 equivalent-lignes representent environ **4,6 sprints**. **Je revise l'horizon a quatre a
cinq sprints**, plutot que quatre a six - une reduction modeste, dans le sens attendu, et je l'ecris
avec sa methode plutot que de laisser circuler un chiffre qui n'a plus sa base.

### Ma decision sur le denominateur : la ligne "Relire et corriger son texte" rejoint "Lire a voix
   haute" au statut `non couvrable en l'etat`

`nadia-prof` ecrit elle-meme, dans son verdict sur la question 4 : *"Elle rejoint la meme categorie
que 'Lire a voix haute un texte court avec fluidite' : hors de portee, et pas pour une question de
sprints."* Je retiens ce rapprochement plutot que d'en inventer un autre : les deux lignes partagent
la meme nature de blocage - un jugement humain sur une production de l'enfant (le ton et les
liaisons pour l'une, la correction d'un texte qu'il a lui-meme ecrit pour l'autre) qu'aucun verdict
serveur testable ne peut rendre (`ADR-0008`), et qui ne se resout par aucune des mecaniques que ce
produit peut construire - ni le choix, ni la saisie.

**Ce que je note comme distinct, pour ne pas confondre les deux motifs malgre le meme statut** :
"Lire a voix haute" bute sur une contrainte technique du produit (la reconnaissance vocale, qui
contredirait trois choix fondateurs a la fois) - une contrainte qui pourrait, en theorie, tomber si
l'utilisatrice revenait sur l'un de ces choix. "Relire et corriger son texte" bute sur autre chose :
elle est **structurellement dependante d'une autre ligne non couverte** ("Rediger un texte narratif
court et coherent", elle-meme hors de portee de la saisie par refus `R3` du second arbitrage de
`nadia-prof`, voir plus bas) et sur un jugement de correction qu'aucune comparaison de chaines ne
peut rendre, meme si le texte de l'enfant existait. Le premier motif est un choix de produit qui
pourrait changer ; le second est une propriete de la competence elle-meme, telle que le referentiel
la formule aujourd'hui. Je les classe au meme statut parce que le resultat operationnel est
identique - aucune mecanique de ce produit ne la debloquera - mais je garde les deux motifs
distincts dans la matrice pour qu'un futur arbitrage ne les confonde pas.

**Ce que je retiens de la piste que `nadia-prof` ouvre, sans la trancher moi-meme** : elle recommande
qu'un amendement du referentiel, pas un arbitrage de mecanique, pourrait un jour rendre utile ce que
cette ligne visait - les reperes de relecture eux-memes (majuscule en debut de phrase, point final,
majuscule aux noms propres), servables des aujourd'hui par la mecanique actuelle et sans aucun
arbitrage de sa part, mais qui ne sont pas ce que la ligne actuelle nomme ("relire et corriger **son**
texte"). Les referentiels de `squad/programme/` sont un socle non exhaustif et non officiel, a faire
valider par l'utilisatrice (`squad/programme/francais/competences-cm1.md`, en-tete) : je ne
l'amende pas moi-meme et je route cette proposition a l'utilisatrice, via `sofia-sm`, comme une
question de perimetre du referentiel et non de mecanique.

**Le denominateur devient donc 44 lignes atteignables sur 46**, deux lignes ("Lire a voix haute",
"Relire et corriger son texte") portant desormais le statut `non couvrable en l'etat`. Le compte de
lignes `couvert-valide` ne change pas : **10**. Le taux devient **10/44 (22,7 %)**, remplacant le
10/45 (22,2 %) que j'avais ecrit avant de recevoir cet arbitrage - lui-meme deja different du 10/46
(21,7 %) de la cloture de s18. J'ecris les trois chiffres successifs plutot que le seul dernier,
pour la meme raison que plus haut : aucun des deux mouvements n'est un mouvement de couverture,
seulement un denominateur qui se precise au fil des reponses recues.

### Ce que je ne fais pas dans cet amendement

- Je n'ouvre aucune story sur les quatre lignes tranchees : le perimetre de developpement de ce
  sprint (`S19-1` a `S19-6`) est intact, aucune capacite n'y est ajoutee.
- Je ne fais passer aucune des deux lignes reclassees "visuel" ou "audio" a `en cours` : aucun jeu
  ne les cible encore, seule leur famille de mecanique future change dans la note qui les
  accompagne.
- Je n'amende pas le referentiel `squad/programme/francais/competences-cm1.md` : la proposition de
  `nadia-prof` est routee, pas tranchee.

## Amendement du 2026-08-24 (meme jour, troisieme passage) - nadia-prof instruit la mecanique de
   saisie (Lot C)

Second fichier recu sur la meme branche : `squad/conformite/ARBITRAGE-PREALABLE-s19-mecanique-de-
saisie.md`. Il precede l'`ADR` de `theo-archi` plutot que de le remplacer, sur le meme principe que
tout arbitrage prealable de ce produit. Je n'en recopie pas le detail (onze conditions `SAI-1` a
`SAI-11`, cinq seuils `SEUIL-1` a `SEUIL-5`, cinq refus `R1` a `R5`) : il reste la source, et
`theo-archi` le lit lui-meme pour son `ADR`. Ce que je retiens ici, pour que le sequencement de s20
n'ait pas a le redecouvrir :

- **Cinq lignes du referentiel sur lesquelles `nadia-prof` refuse par avance qu'une story de saisie
  soit un jour validee** : "Copier un mot, une phrase courte..." (CP, `R1` - competence
  graphomotrice, un clavier n'en travaille aucune facette) ; "Comprendre un texte narratif ou
  documentaire" (CP et CM1) et "Lire silencieusement... et en restituer l'essentiel" (CM1) (`R2` -
  une reponse libre a des dizaines de formulations correctes, le choix deja retenu a s17 est
  superieur) ; "Rediger un texte narratif court et coherent" (CM1, `R3` - la coherence d'un recit ne
  se compare pas a des chaines) ; "Relire et corriger son texte" (CM1, `R4` - deja traitee ci-dessus,
  meme verdict) ; "Utiliser des synonymes et antonymes..." (CM1, `R5` - un mot a plusieurs synonymes
  justes, le choix ferme l'espace des reponses et c'est une qualite ici, ligne deja servie par
  `S19-1` et `bandit-manchot-synonymes-cm1` sans aucun besoin de saisie).
- **Une ligne qui a besoin de deux mecaniques a la fois, pas d'une seule** : "Ecrire de maniere
  autonome un mot simple en respectant les correspondances grapheme/phoneme connues" (CP) exige que
  l'enfant produise des lettres (saisie) a partir d'un mot **entendu**, jamais affiche (audio) - sans
  quoi l'exercice devient de la copie (`R1`). Cette ligne ne peut donc ouvrir qu'apres que les deux
  familles existent ensemble, jamais l'une sans l'autre. Je le note pour qu'aucun arbitrage de
  sequencement futur ne la range dans une seule des deux colonnes.
- **Une amelioration d'existant, pas une couverture neuve** : `nadia-prof` recommande la saisie
  numerique courte (au plus deux chiffres) sur les paquets de calcul deja `couvert-valide` ou
  `en cours` (additions, complements a 10...), au motif qu'elle supprime la chance qu'un choix parmi
  quatre laisse aujourd'hui. Je ne l'ouvre pas comme story ce sprint - `sofia-sm` en decidera la
  valeur le jour ou la mecanique existera, hors du perimetre de developpement gele de s19.
- **Deux questions routees a l'utilisatrice, via `sofia-sm`, qui conditionnent la portee de tout
  l'arbitrage de saisie et que je transmets sans les trancher** : une lettre accentuee est-elle
  accessible d'un geste raisonnable sur le clavier virtuel de la tablette visee (si la reponse est
  non, la tolerance d'accent `SAI-1` cesse d'etre un confort et devient une condition d'existence de
  toute saisie alphabetique) ; et dans quel contexte un enfant de CP utilise `recreo` - seul ou
  accompagne, sur quel appareil (la reponse change son verdict sur la viabilite de la saisie
  alphabetique au CP).
- **Ce qui ne change pas** : la distinction operation posee / saisie que cette decision posait deja
  en section 7 est confirmee sans amendement par `theo-archi` et par `nadia-prof`, au mot pres.

### Ce que je route, mis a jour

- **`theo-archi`** : en plus de ce que section 7 routait deja, les deux conditions `SAI-2` (ou vit
  l'interrupteur de tolerance stricte declare par le contenu) et `SAI-9` (montrer a l'enfant ce qu'il
  a ecrit au moment du verdict) comme entrees les plus structurantes pour son `ADR`.
- **`sofia-sm`** : transmettre les deux questions ci-dessus a l'utilisatrice avant que l'`ADR` de la
  saisie ne soit ecrit, comme `nadia-prof` le demande elle-meme ; verifier que les cinq refus
  (`R1` a `R5`) sont lus par quiconque instruira une story de saisie sur l'une des lignes qu'ils
  citent.
- **`nadia-prof`** : rien de plus ici - son arbitrage reste la source, je ne lui redemande rien.

### Note du 2026-08-24 (meme jour, camille-po) - `SAI-12` entre au perimetre de la conception, chiffrage route

`nadia-prof` a pose, le meme jour, une condition neuve non couverte par les deux paragraphes
ci-dessus : `SAI-12`, l'enfant voit aux deuxieme et troisieme essais la liste de ce qu'il a deja
ecrit sur la manche, pas seulement sa derniere tentative. Elle demande qu'elle soit chiffree a
part de `SAI-9`. Je tranche que `SAI-12` entre au perimetre de la conception de s19 plutot que
d'attendre l'ouverture de la mecanique, avec son motif complet dans `DEC-0043`. Cette section
n'est pas reecrite : `SAI-2` et `SAI-9` restent les deux entrees qu'elle nommait ce jour-la,
`SAI-12` s'y ajoute par cette note.

