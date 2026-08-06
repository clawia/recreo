# [Sprint s04] Review - 2026-08-06

- **Horodatage de debut** : aucun. **Ce sprint n'a pas eu de planning**, ni de dossier avant cette
  cloture. Voir l'anomalie ci-dessous ; elle n'est pas fabriquee retrospectivement.
- **Horodatage de fin** : 2026-08-06 12:02 (commit `399fa5f`, meme instant que la cloture de s03 -
  voir la note de fenetre partagee dans `squad/sprints/s03-completer-mathematiques-cp/review.md`,
  qui s'applique identiquement ici et que je ne duplique pas).
- **Duree effective** : non mesurable de la maniere habituelle (aucun `planning.md`). Repere de
  repli, a titre indicatif seulement : 2h55 entre le commit qui ouvre reellement ce sprint
  (`0e07fd3`, `DEC-0008`, 09:07:22 - son propre en-tete porte "Sprint : s04 (ouverture)") et le
  dernier commit de la fenetre partagee (`399fa5f`, 12:02:07).
- **Anime par** : sofia-sm

## Anomalie de methode - ce qui a tenu lieu de perimetre en l'absence de planning

**C'est la deuxieme fois apres s02 qu'un sprint entier se deroule sans ceremonie de planning, et
cette fois il n'a meme pas eu de dossier avant que je le cree a cette cloture.** Je le dis sans
l'adoucir, comme le veut la regle que j'applique a moi-meme.

Ce qui s'est reellement passe, dans l'ordre : l'utilisatrice a dit ne pas comprendre pourquoi le
produit n'avait toujours qu'un seul jeu apres quatre sprints. `camille-po` a repondu en ecrivant
`DEC-0008` (`0e07fd3`, 2026-08-06 09:07:22) : deux stories concretes (S4-1, S4-2), leur competence
programme, leurs criteres d'acceptation, et une clause explicite anticipant la suite normale du
process - *"elle ne tranche aucune question technique [...] l'avis de faisabilite de `theo-archi`
au planning le confirmera ou l'infirmera"*. **Cet avis de faisabilite n'a jamais ete ecrit.**
`lea-dev` a livre le code vingt-neuf minutes plus tard (`a6c416c`, 09:36:00), sans qu'aucun avis
n'ait ete recueilli entre les deux commits. `DEC-0008` a donc tenu lieu d'objectif gele et de
stories retenues - les deux premieres colonnes d'un planning - mais ni d'avis de faisabilite
recueilli avant le code, ni d'affectation nominative formelle, ni d'horodatage de debut de
ceremonie. La verification de faisabilite a bien eu lieu, mais **a la cloture et non avant** :
c'est `theo-archi` qui, dans `confirmation-archunit.md` section 5, constate a posteriori que le
`domain` n'a pas bouge d'une ligne. Le mecanisme qui aurait pu arreter un contenu incompatible
avec la mecanique **avant** qu'il soit code n'a pas tourne ; il se trouve que le resultat est bon
(voir "La demonstration du decouplage" ci-dessous), mais ce n'est pas parce que le garde-fou a
fonctionne.

**Consequence concrete signalee par `camille-po` a cette cloture, et je la porte telle qu'elle** :
l'absence de dossier de sprint pendant que le travail se faisait l'a empechee de relier une story
ouverte (S4-1, S4-2 dans `docs/backlog/backlog.md`) a l'increment reellement livre autrement qu'a
posteriori, en reconstituant la chronologie par les commits. Un planning ecrit avant le code aurait
donne cette tracabilite en temps reel plutot qu'apres coup.

**Le seul artefact ecrit specifiquement pour s04 avant cette cloture**, `rapport-qualite.md` de
`yanis-qa`, portait explicitement en tete : *"Je le classe provisoirement dans le dossier de s03,
faute d'un dossier s04 a ce jour [...] `sofia-sm` creera le dossier s04 a la cloture ; ce fichier
est ecrit pour etre deplace ou reference a ce moment-la, pas pour rester ici."* `theo-archi`
(`confirmation-archunit.md`, section "Ou va la part s04") et `relecteur-pedagogique`
(`relecture.md`) ont fait le meme choix, chacun pour sa propre part. **Je fais maintenant ce
qu'ils anticipaient tous les trois** :

- `squad/sprints/s04-ouvrir-le-francais/rapport-qualite.md` **existe desormais ici** : deplace
  depuis `squad/sprints/s03-completer-mathematiques-cp/rapport-qualite-s04.md`, contenu inchange
  (je ne reecris pas le rapport d'un autre agent), renomme pour suivre la convention du dossier qui
  l'accueille.
- La part s04 de `theo-archi` (`confirmation-archunit.md`, section "Le point le plus important de
  s04 : le decouplage est demontre") **reste dans le dossier de s03**, parce que le reste de ce
  document juge s03 dans son entier et que le deplacer casserait sa propre lecture ; elle est
  **citee** ici, dans la section dediee ci-dessous, plutot que dupliquee.
- `relecture.md` **reste dans le dossier de s03** pour la meme raison (il couvre s02, s03 et s04
  dans un seul document) ; ses constats sur le contenu francais neuf sont cites plus bas.

## Story par story

| Story | Livre | Verdict de nadia-prof |
|---|---|---|
| S4-1 - homophones grammaticaux CM1 | `contenu/cm1-homophones.yaml`, 10 items, cinq couples (a/a, et/est, on/ont, son/sont, ou/ou), chacun dans les deux sens ; entree de catalogue "Le bon petit mot" | **VALIDE SOUS RESERVE** - Done pedagogique accorde. Exactitude irreprochable item par item, rattachement au referentiel exact, niveau et lisibilite conformes au CM1. Huit reserves nommees (dont : dix items pour dix manches, aucune variete entre deux parties ; les phrases sont majoritairement trop faciles pour la profondeur CM1 ; aucun sujet feminin ; aucune aide disponible - meme constat structurel que S4-2) |
| S4-2 - graphemes/phonemes CP ("Le bon son") | `contenu/cp-le-bon-son.yaml`, 24 items, six sons (an, on, in, ou, oi, ch) ; entree de catalogue "Le bon son" | **REFUSE**, cinq motifs cumules : consigne annoncant un son jamais joue ; crochets phonetiques montres a l'enfant ; 23 items sur 24 resolubles par appariement visuel des lettres, sans passer par le phoneme ; trois items a deux reponses vraies mais une seule acceptee ; aucune aide disponible, motif structurel (`QuestionContentSourceFactory` ne porte pas de champ `indice`) |

## La demonstration du decouplage - le fait le plus important de ce sprint, plus que le compte de stories

`theo-archi` et `yanis-qa` l'ont verifie **independamment**, chacun dans son propre arbre, sur le
meme materiau : `git diff --stat` sur `domain/` entre la fin du lot `marc-dev` de s03 (`9af191c`)
et la fusion du lot francais (`4c62f60`, qui inclut aussi le lot Bootstrap hors sprint) est **vide**.
Pas seulement `domain/mechanic` : **le module `domain` entier n'a pas bouge d'une ligne.** Le lot
francais de `lea-dev` (`a6c416c`) touche exactement quatre fichiers, tous sous
`adapters/adapter-content` : `catalogue.yaml`, deux paquets YAML neufs, et un test. Les deux
paquets declarent `type: question`, un schema deja connu d'`ADR-0007` - aucun schema neuf non
plus.

**C'est la premiere preuve reelle, en quatre sprints, du principe directeur inscrit dans
`CLAUDE.md` et dans `ADR-0004` : une mecanique de jeu sert n'importe quelle matiere sans
modification de code.** Deux jeux de francais - un CP, un CM1 - sont entres au catalogue par trois
fichiers YAML et un test. `DEC-0008` avait explicitement choisi le francais parce que c'est "la
matiere la plus eloignee de l'arithmetique parmi celles qui ont deja un referentiel" : le pari
tient, et il est mesure au diff, pas affirme.

**`theo-archi` refuse d'en ecrire une regle ArchUnit, et il donne son motif par ecrit plutot que de
la reporter sans raison** (`confirmation-archunit.md`, section 5) : une telle regle comparerait
deux etats du depot entre deux commits, ce qu'`ADR-0009` a deja montre couteux et fragile pour un
garde-fou de CI ; et surtout, **un seul lot de contenu constitue une observation, pas encore un
usage constate** - une abstraction (et une regle en est une) se justifie sur un usage constate.
Ce qui le ferait changer d'avis, il le dit lui-meme : un second lot de matiere qui, lui, toucherait
`domain`. Je porte ce fait dans `RAPPORT.md` avec le poids qu'il merite : c'est le veritable
increment de ce sprint, plus que le compte de deux stories.

## Rapport qualite - yanis-qa

- **Commande** : `mvn -B clean verify`, sur le meme commit fusionne (`4c62f60`) que celui de
  `rapport-qualite.md` de s03.
- **Resultat** : vert, deux executions independantes sur JDK 21.0.10 reel, **BUILD SUCCESS, 180
  tests, 0 echec**, dont **4 tests neufs pour s04** (`PaquetsFrancaisS04Test`).
- **Couvert** : chaque critere testable de S4-1 et S4-2 a un test qui le nomme, plus la boucle
  derivee de `CatalogueChargeableIT` qui demarre une partie sur chaque jeu du catalogue reel, les
  deux jeux francais inclus sans etre cites nommement ; preuve de decouplage au diff, section
  precedente.
- **Non couvert** : la forme du contenu est verifiee (couples autorises, distracteurs, variete,
  couverture des sons et couples cites par le referentiel), pas son fond pedagogique - c'est
  explicitement le mandat de `nadia-prof`, pas une lacune.
- **Tests instables** : aucun specifique a s04 (voir la section correspondante du rapport de s03,
  qui couvre l'unique instabilite non reproduite de ce cycle, etrangere au code de s04).

## Confirmation ArchUnit - theo-archi

- **Commande** : `mvn -B clean verify`
- **Resultat** : vert. Aucune confirmation ArchUnit dediee a s04 n'a ete ecrite, pour un motif que
  `theo-archi` donne lui-meme (`rapport-qualite-s04.md` original, section "Ce que ce rapport ne
  couvre pas") : la preuve de decouplage porte sur un diff temporel, pas sur un etat statique du
  code, donc aucune regle ArchUnit ne peut la porter a elle seule - elle est faite au diff, dans
  `confirmation-archunit.md` section 5, citee ci-dessus.
- **Regles ajoutees ce sprint** : aucune, sur le code de s04 lui-meme.

## Relecture - relecteur-pedagogique

- **Corrections appliquees** : aucune, y compris sur les deux paquets francais neufs. `relecture.md`
  releve la relecture caractere par caractere des 24 items de `cp-le-bon-son.yaml` et des 10 items
  de `cm1-homophones.yaml` : aucune faute d'accentuation, d'apostrophe ou de ponctuation.
- **Points a trancher** : les libelles destines a l'enfant introduits par ces deux paquets (titres
  de jeu, consignes, 34 enonces/reponses/distracteurs) sont listes et remontes a `nadia-prof` sans
  jugement de fond, conformement au registre de categorie B (`DEC-0002`). Deja traite par les deux
  verdicts ci-dessous.

## Obstacles du sprint

| Description | Categorie | Agent route | Decision liee | Statut |
|---|---|---|---|---|
| Aucun planning pour s04, aucun dossier avant la cloture, rapport `yanis-qa` egare dans le dossier de s03 - camille-po signale que cela l'a empechee de relier une story ouverte a l'increment livre autrement qu'a posteriori | process | sofia-sm | Dossier cree et fichier deplace a cette cloture ; amendement propose en `retro.md` | clos pour l'incident, ouvert pour l'amendement de process |
| DEC-0008 anticipait un avis de faisabilite de theo-archi "au planning" ; aucun n'a ete recueilli avant le code (29 minutes entre DEC-0008 et le premier commit de code) | process | sofia-sm | Constat ecrit ici ; le decouplage a tenu, mais par constat a posteriori et non par garde-fou prealable | ouvert, amendement en `retro.md` |
| S4-2 refuse ("Le bon son") : consigne fausse, crochets phonetiques, appariement visuel, items a double reponse vraie, aucune aide disponible | pedagogie | nadia-prof, camille-po | `DEC-0009` (retrait du catalogue), stories S5-2 a S5-5 | clos pour le retrait ; corrections encore a livrer, hors mandat de cette cloture |
| `DEC-0009` a ete redigee (`7e739e5`, 11:45:18) et le catalogue a continue de contenir le jeu refuse pendant environ neuf minutes, jusqu'a son execution effective (`fb7f4df`, 11:54:41) | produit / gouvernance | camille-po, sofia-sm (retro) | Execute et verifie en jeu par `nadia-prof` (fiche, point 4.1) ; le trou de gouvernance est nomme en `retro.md`, pas seulement l'incident corrige | clos pour l'incident ; amendement en `retro.md` |
| Referentiel `squad/programme/francais/competences-cp.md` declarait une competence de syllabes couverte par un jeu (`FICHE-francais-cp-syllabes.md`, sprint s02) qui n'existe pas sur `round-two` | produit / gouvernance | camille-po | Annotation retiree le 2026-08-06 (`MATRICE-couverture.md`, "Etat de reconstruction") ; les trois autres referentiels relus sans trouver d'autre occurrence | clos |
| S5-1, second critere : la reprise de session ne revalide pas l'identifiant de jeu contre le catalogue | produit | camille-po | `nadia-prof` demande explicitement de ne pas fermer ce critere avant que `adapter-persistence` existe - fenetre d'exposition nulle aujourd'hui, elle ne le sera plus le jour ou une session survit a un redemarrage | **ouvert, garde volontairement ouvert** |
| S5-5 : un paquet `type: question` ne peut porter d'indice (motif structurel du refus de "Le bon son", touche aussi `bandit-manchot-homophones-cm1` en reserve non bloquante) | technique | theo-archi | Deja route dans `docs/backlog/backlog.md` ("note de routage" de la story elle-meme) ; confirme par cette review | **ouvert, route** |
| Besoin formule par nadia-prof : aucun garde-fou ne compare deux selecteurs entre eux sur un meme ecran (troisieme defaut visible qui traverse une CI verte, cette fois sur le chantier Bootstrap hors sprint mais le meme constat vaut pour tout futur contenu francais) | technique | theo-archi | Route dans `squad/sprints/s03-completer-mathematiques-cp/review.md` ; je ne le route pas une seconde fois ici, je le cite | ouvert (voir s03) |

Rappel : un obstacle n'est `clos` que si le fichier qui le tranche existe physiquement. Verifie pour
chaque ligne `clos` ci-dessus.

## Porte pedagogique - nadia-prof

- **Verdict S4-1 (homophones CM1)** : **VALIDE SOUS RESERVE**. Done pedagogique accorde - premier
  contenu du produit pour le CM1, tous jeux confondus.
- **Fiche** : `squad/conformite/FICHE-bandit-manchot-homophones-cm1.md`.
- **Motif** : exactitude irreprochable, rattachement exact au referentiel, niveau et lisibilite
  conformes.
- **Reserves** : huit, nommees dans la fiche (dix items pour dix manches sans variete ; phrases
  majoritairement trop simples pour la profondeur CM1 ; aucun sujet feminin ; absence de mention
  du niveau a l'accueil ; entre autres). `squad/programme/MATRICE-couverture.md`, ligne francais
  CM1, **reste `en cours`** : la condition ecrite pour `couvert-valide` est explicite (cinq couples
  supplementaires - ce/se, ces/ses, la/la/l'a, mais/mes, peu/peut - et la reserve de profondeur
  traitees dans le meme geste, story S5-6).

- **Verdict S4-2 (graphemes/phonemes CP, "Le bon son")** : **REFUSE**.
- **Fiche** : `squad/conformite/FICHE-bandit-manchot-le-bon-son-cp.md`.
- **Motif** : cinq motifs cumules, dont un structurel (voir tableau des obstacles). 23 items sur 24
  reussissables par appariement visuel des lettres sans passer par le phoneme que la competence
  vise.
- **Consequence produit, execution verifiee** : `DEC-0009` retire le jeu du catalogue jouable
  (`fb7f4df`) ; `nadia-prof` a verifie en jouant l'application relancee que l'accueil ne propose
  plus que deux jeux et que le jeu retire est injoignable par toute porte, chacune rendant l'ecran
  d'erreur plutot qu'une erreur brute (`FICHE-bandit-manchot-le-bon-son-cp.md`, point 4.1 de la
  fiche des additions - le suivi croise vit dans les deux fiches). `squad/programme/
  MATRICE-couverture.md`, ligne francais CP, **reste `en cours`** : ce statut suit le Done
  pedagogique, pas la presence au catalogue jouable.

## Decisions prises

Aucune `DEC` ni aucun `ADR` n'est cree pendant cette review : elle constate et cite les decisions
deja ecrites pendant le sprint (`DEC-0008`, `DEC-0009`) et route les obstacles encore ouverts vers
leurs proprietaires. Je n'anticipe aucune decision qui n'existe pas encore sur le disque.
