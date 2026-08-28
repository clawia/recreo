# DEC-0052 - Arbitrage de l'utilisatrice : "cheminee" vaut trois syllabes, `cp-syllabes` passe
# `VALIDE SOUS RESERVE` (`SEG-1` amendee par `nadia-prof`) ; `bandit-manchot-mot-manquant-cp`
# retiree du catalogue pour un motif de contenu qui reste, lui, entier

- **Type** : arbitrage de perimetre (utilisatrice), execution de decision (retrait du catalogue)
- **Date** : 2026-08-28
- **Auteur** : camille-po
- **Sprint** : s21 (cloture)
- **Competence programme visee** : francais, CP (cycle 2), "Denombrer les syllabes orales d'un mot
  (segmentation syllabique)" (section 2, `squad/programme/francais/competences-cp.md`, ligne 9) et
  "Utiliser des mots precis pour designer des objets, actions, sensations du quotidien" (section 3,
  meme referentiel, ligne 34).
- **Statut** : Acceptee

## Contexte

`nadia-prof` a rendu son Done pedagogique de s21 le 2026-08-28 (branche `s21-nadia-2`, commit
`18d1220`) : `bandit-manchot-comparer-cp` **VALIDE SOUS RESERVE**, `bandit-manchot-syllabes-cp` et
`bandit-manchot-mot-manquant-cp` **REFUSE**. Le motif unique du refus de `cp-syllabes` portait sur
l'item `CHEMINEE` : *"che-mi-nee vaut trois syllabes en forme de citation et deux dans la
prononciation ordinaire"*, au titre de `SEG-1` clause (c) (*"les mots ou un `e` interieur peut
disparaitre a l'oral"*, motif : *"un item dont deux comptes sont defendables n'a pas une bonne
reponse, il en a deux"*).

**L'utilisatrice tranche, mot pour mot, le 2026-08-28** : *"pour les syllabes cheminée vaut bien 3
syllabes"*. Une decision de son rang - le meme que celui qui a fait sortir la bande de viewport
576-719 px de la cible du produit a s20 (`DEC-0040`, troisieme amendement, 2026-08-25) - **prime sur
le verdict pedagogique** rendu sur ce point precis.

**`nadia-prof` a repris son verdict le meme jour** (commit `4326f3b`, `s21-nadia-2`, non fusionne ;
`squad/conformite/FICHE-bandit-manchot-syllabes-cp.md`,
`squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md` section 10,
`squad/sprints/s21-fermer-le-pli-avant-le-volume/avis-nadia-done-pedagogique-s21.md`) : le refus
initial **n'est pas efface**, il reste ecrit et date, coiffe d'un bandeau qui dit qu'il ne fonde
plus rien - *"un refus efface n'a aucune valeur de preuve"*. **`bandit-manchot-syllabes-cp` passe a
`VALIDE SOUS RESERVE`.** Ce document tire les consequences produit de cette reprise ; il ne rejuge
pas le fond pedagogique, qui reste celui de `nadia-prof`.

## 1. Ce que `nadia-prof` a fait de l'arbitrage, verifie contre sa source primaire avant d'ecrire une ligne

**Elle n'a pas cherche a sauver son refus.** Elle a verifie, mecaniquement, que le motif ne se
deplace pas ailleurs sur le paquet : les seize items relus le 2026-08-28, `CHEMINEE` tient
desormais `SEG-1` (16 sur 16, contre 15 sur 16 au refus), `SEG-2` a `SEG-9` inchangees. **Aucun
motif de remplacement trouve.**

**Elle est allee plus loin qu'un cas d'espece, et je reprends son raisonnement sans le rejuger** :
son refus *"n'etait pas une erreur d'application, c'etait l'application exacte d'une condition
[qu'elle avait] posee avant que le paquet existe"* - donc c'est la condition, `SEG-1` clause (c),
qui etait trop large, pas l'item. **Mesure du 2026-08-28, sur les 650 mots francais distincts du
corpus CP du depot** (10 paquets CP servis et les fichiers CP de `contenu-repris/`, 29 fichiers,
**population entiere, verifiee, pas un echantillon**) : la clause (c) d'origine attrapait **39 cas
de schwa reels**, dont **30 a tort** (`petit`, `cheval`, `renard`, `semaine`, `regarde`, `premier`
et vingt-quatre autres - **le coeur du lexique de CP**) et **9 a bon droit** (`samedi`, son exemple
fondateur, en fait partie - la clause visait donc un phenomene reel, elle l'a manque en attrapant
**environ quatre fois trop large**). Elle amende la clause plutot que la supprimer, sur un critere
qu'elle montre general et non ad hoc (l'articulation lente, que la tache demande explicitement a
l'enfant, *resout* l'ambiguite sur les mots comme `cheminee` et *cree* l'ambiguite sur ceux vises
par les clauses (a) et (b), qui restent inchangees).

**Ce que je retiens de cette sequence, comme jugement produit et non pedagogique** : `SEG-1` est une
condition de `nadia-prof`, ecrite et amendee par elle, sur `squad/conformite/`, que je ne touche
pas. Je n'avais pas a lui poser de question : elle a instruit la question elle-meme, avec sa
methode et sa population, le jour meme.

**Ce qui ne bouge pas** : les quinze autres items de `cp-syllabes`, deja verifies exacts avant
l'arbitrage, ne sont pas rouverts ; `parapluie`, `dinosaure` et les autres mots ecartes du fichier
repris pour les clauses (a)/(b) restent ecartes, inchangees.

## 2. Consequence sur le catalogue - `bandit-manchot-syllabes-cp` : confirmee, aucun geste materiel

`nadia-prof` l'ecrit elle-meme : *"le retour de l'entree au catalogue n'a pas lieu d'etre : elle
n'en est jamais sortie"* (`FICHE-bandit-manchot-syllabes-cp.md`, verdict de reprise, section "Ce
qui appartient a d'autres"). **Verifie ce jour** : `catalogue.yaml` porte l'entree
`bandit-manchot-syllabes-cp` depuis le debut du sprint, jamais retiree. Avec un Done pedagogique
`VALIDE SOUS RESERVE` desormais prononce, cette exposition est en outre pleinement conforme a
`DEC-0045` section 3 sans qu'aucune lecture particuliere de ma part ne soit necessaire. **Aucune
edition de `catalogue.yaml` n'est requise.**

## 3. Decision de catalogue - `bandit-manchot-mot-manquant-cp` : retrait immediat, tel que `DEC-0045` le prescrit

**Ce cas reste seul, et distinct du precedent.** Les deux motifs du refus de `mot-manquant`
restent entiers, non touches par l'arbitrage de la section 1 : l'item 6 (*"La nuit, Tom allume sa
___."*, `radio` et `tele` aussi defendables que `lampe`) et les items 3 et 4 (*"Lea dessine avec un
___."*, *"Sous la pluie, Tom prend son ___."*, distracteurs qui ne portent aucun champ semantique
commun avec la bonne reponse). Aucune autorite - ni l'utilisatrice, ni moi - n'a dit que ces items
sont corrects. Un enfant qui joue aujourd'hui rencontrerait reellement l'ambiguite que `nadia-prof`
a mesuree en jouant.

**Je tranche : retrait immediat, sur `DEC-0045` section 3 telle qu'ecrite, sans amendement.**
L'entree `id: bandit-manchot-mot-manquant-cp` sort de `catalogue.yaml`, execution routee a
`sofia-sm` dans le meme geste que la fusion de ce document, sans attendre une story a planifier
(meme mecanisme que `DEC-0009`, `DEC-0014`, `DEC-0024`, `DEC-0031`, `DEC-0045`, `DEC-0049`). Le
paquet de contenu reste au depot inchange : c'est la matiere de travail de la correction en cours
par `lea-dev`, pas un contenu a reprendre de zero.

**Pourquoi je n'amende pas `DEC-0045` en un regime de maintien conditionnel, alors que la question
m'a ete explicitement posee, et qu'elle ne porte plus que sur un seul jeu.** Le precedent de s20
(`DEC-0049` puis `DEC-0050`, sur `cm1-reperes`) donne le prix exact d'un retrait puis d'un retour
dans le meme sprint : deux editions de `catalogue.yaml`, deux decisions, et une de ces editions a
casse un test (`TexteAccentueExposeParLePortEntrantTest`, corrige le meme jour par `sofia-sm`,
commit `77dd467`). C'est un cout reel, mesure, et je ne le minimise pas - mais il porte sur **un**
jeu, pas deux, et c'est exactement le cout que `DEC-0045` a ete ecrite pour rendre visible et
obligatoire, apres que ce depot a laisse deux fois un jeu refusable rester joue par un enfant faute
d'un geste materiel immediat (`DEC-0045`, notes du 2026-08-25 ; `DEC-0049`, section 3 : *"un jeu
reellement refusable est reste expose a des enfants le temps d'un sprint"*). Un regime de maintien
conditionnel, meme borne, recree precisement ce risque : un enfant qui joue `mot-manquant-cp` cette
semaine tomberait sur *"La nuit, Tom allume sa radio"* jugee fausse, ou sur un item ou deviner
suffit, pendant que la correction est en cours - le defaut meme que le refus vient de nommer. Le
prix de l'aller-retour (une edition de plus de `catalogue.yaml`, un test a surveiller) est petit et
connu ; le prix de l'exposition maintenue est porte par un enfant, pas par le depot. Je paie le
premier plutot que le second.

**Ce que cette decision ne fait pas** : elle ne prejuge pas de la correction que `lea-dev` livre en
ce moment. Le retour au catalogue suit le mecanisme deja etabli (`DEC-0045` section 2, `DEC-0050`
section 2) : des que `nadia-prof` reaudite le paquet corrige et ne nomme plus de motif bloquant sur
l'item 6 ni sur les items 3 et 4, l'entree revient, execution routee a `sofia-sm` dans le meme
geste que la fusion de ce verdict, sans DEC supplementaire.

## 4. Requalification du retour pedagogique de `cp-syllabes` (`SEG-10`), et `SEG-13` versee au backlog

Traitees dans `docs/backlog/backlog.md` : l'entree "explication n'est atteignable que par un
detour propre au francais" est remplacee par deux obstacles distincts - le verrou du champ
`explication` derriere `graphie` (inchange, deja route a `theo-archi`) et le defaut de mecanique
mesure par `nadia-prof` en jouant, `SEG-10` - le "Bravo !" d'un item s'affiche a cote du mot
suivant, jamais a cote du mot repondu. Ce second obstacle est plus grave que le premier : il touche
potentiellement les vingt et un jeux qui partagent la meme mecanique de re-rendu, pas seulement
`cp-syllabes`.

**`SEG-13` (neuve, trouvee par `nadia-prof` a la reprise du verdict)** : sur cinq items sur seize
(mots d'une syllabe), la **forme** de l'indice (indice de procede plutot que "Ce mot commence
par...") trahit `1` sans que l'enfant compte, une fois qu'il a vu le brassage des deux familles sur
plusieurs parties. Reserve non bloquante ; correction deja demontree dans le paquet lui-meme
(brassage deja fait entre 2 et 3 syllabes). Versee en story dans le backlog.

## 5. Matrice de couverture

`squad/programme/MATRICE-couverture.md` est mis a jour dans le meme geste que ce document :

- **Ligne 89** ("Comparer des longueurs, des masses, des contenances (sans instrument gradue)",
  mathematiques CP) passe `non couvert` -> `couvert-valide` : Done pedagogique `VALIDE SOUS
  RESERVE` sur `bandit-manchot-comparer-cp`, dix conditions sur dix tenues, aucune reserve
  bloquante.
- **Ligne 485** ("Denombrer les syllabes orales d'un mot...", francais CP) passe `non couvert` ->
  `couvert-valide` : Done pedagogique `VALIDE SOUS RESERVE` sur `bandit-manchot-syllabes-cp`,
  reserves `SEG-10` a `SEG-13` non bloquantes. **`nadia-prof` le nomme elle-meme** : cette ligne
  ouvre **une ligne sur trois** du domaine "Langage oral et conscience phonologique" ; les deux
  autres restent hors d'atteinte sans emission de son (`DEC-0042`, arbitrage 2). Je ne lis pas ce
  mouvement comme l'ouverture du domaine, et je l'ecris dans la matrice elle-meme pour qu'aucun
  lecteur ne le lise ainsi non plus.
- **Ligne 496** ("Utiliser des mots precis...", francais CP) : **je ne la fais pas bouger.** Le
  refus de `bandit-manchot-mot-manquant-cp` reste entier (section 3) ; aucun Done pedagogique n'est
  prononce. Note ajoutee sous la ligne, decrivant l'etat sans le deplacer.
- **Chiffre** : le compte de lignes `couvert-valide` passe de **15** a **17** (deux lignes
  bougent : 89 et 485). Le denominateur atteignable reste **43** sur **47**, inchange. **15/43
  (34,9 %) devient 17/43 (39,5 %)**.

## 6. Un cout que le planning de s21 n'avait pas compte, et qui doit l'etre au suivant

`nadia-prof` l'ecrit contre elle-meme, et je le retiens comme fait de gouvernance plutot que comme
jugement sur son travail : *"c'est la deuxieme fois en deux sprints qu'une de mes conditions
prealables, et non le travail qu'elle encadrait, est la cause racine d'un refus"* - a s20, des
libelles qu'elle avait dits courts et qui faisaient 34 caracteres (`REP-4`, `DEC-0049`) ; a s21,
`SEG-1` clause (c), quatre fois trop large. Sa conclusion, que je reprends : *"un arbitrage
prealable evite un cycle quand il est juste ; il en coute un quand il est faux. Ca doit se
compter."*

**Ce que cela change pour mon propre chiffrage.** `DEC-0051`, section 5, tenait *"deux a trois
paquets neufs"* comme le nombre auditable a s21 sans compter un aleas de refus supplementaire, sur
la base de deux sprints ou trois paquets sur six avaient exige un cycle de refus. **Cette methode
ne distinguait pas un cycle cause par le contenu livre d'un cycle cause par ma propre condition
prealable** (ou celle de `nadia-prof`, ecrite avant le developpement) : deux des trois cycles
observes sur ces deux sprints sont maintenant de cette seconde famille. Je ne rechiffre pas la
borne ici - une mesure sur deux occurrences ne fonde rien de plus precis - mais je nomme le trou
pour le planning suivant : **un arbitrage prealable est un risque de cycle a chiffrer au meme titre
qu'un risque de refus sur le contenu lui-meme**, pas une garantie qui le supprime. Route a
`sofia-sm` pour le prochain planning, sans le trancher ici.

## 7. Ce que la porte technique n'a pas vu, et ce que j'en dis

`nadia-prof` comptait, sur son premier passage, vingt-sept conditions sur les trois paquets, dont
trois seulement partiellement couvertes par un test (la regle 1 du contrat de forme, sur la clause
de nombre de `SEG-3`, `PRE-6` et `GRA-8` uniquement) - inchange par la reprise du verdict, qui
ajoute une condition de plus (`SEG-13`) sans garde-fou possible pour la meme raison que les autres.
La porte technique est verte a **722 tests, 0 echec**, et elle n'a vu aucun des motifs qui ont
produit deux refus, une reprise de verdict et huit reserves. Je ne demande la construction d'aucun
garde-fou pour combler cet ecart : `theo-archi` et `nadia-prof` ont etabli ensemble, sur ce meme
sprint, qu'un classifieur heuristique capable de juger la stabilite d'une coupe syllabique, la
plausibilite d'un distracteur ou l'unicite d'une reponse defendable se tromperait, et
qu'*"une regle qui passe pour une mauvaise raison est pire qu'une regle absente"*
(`FICHE-bandit-manchot-syllabes-cp.md`, section 7 ; meme phrase dans les deux autres fiches).

**Ce que cela vaut pour le produit, dit sans detour.** La CI verte ne garantit rien sur ce que ce
produit existe pour garantir - qu'un enfant qui a raison ne se fasse pas dire qu'il a tort. Sur ce
sprint, deux paquets sur trois ont porte ce defaut, et **aucune mesure technique ne l'aurait
signale avant qu'un humain ne joue chaque item** - y compris quand cet humain se trompe lui-meme
une fois, comme le montre `SEG-1`. Le double Done de ce produit (technique juge par la CI,
pedagogique juge par une seule personne qui joue) n'est donc pas une redondance : c'est la seule
protection qui existe sur exactement ce point, sans remplacant moins cher aujourd'hui, et son
propre cout (une condition prealable peut se tromper, section 6) fait partie du prix a payer, pas
d'un defaut a corriger par un garde-fou. Je ne route cela vers aucun garde-fou : je le nomme pour
que le chiffre reste lisible au moment ou quelqu'un serait tente de lire "722 tests, 0 echec" comme
une preuve de qualite pedagogique.

## Ce que je route

- **`sofia-sm`** : retirer l'entree `id: bandit-manchot-mot-manquant-cp` de `catalogue.yaml` dans
  le meme geste que la fusion de ce document, sans attendre une story ; ne rien faire sur
  `bandit-manchot-syllabes-cp`, deja et correctement expose (section 2) ; verser la section 6 au
  prochain planning.
- **`lea-dev`** : poursuivre la correction deja engagee sur les items 3, 4 et 6 de
  `cp-mot-manquant`, sur les motifs verbatim de `nadia-prof`
  (`FICHE-bandit-manchot-mot-manquant-cp.md`, section 3) - detail repris en story dans
  `docs/backlog/backlog.md`.
- **`nadia-prof`** : reauditer `bandit-manchot-mot-manquant-cp` quand `lea-dev` aura livre sa
  correction. Rien d'autre n'est route ici : `SEG-1` est deja instruite par elle-meme.
- **`theo-archi`** : `SEG-10`, defaut de mecanique route en section 4 et dans le backlog.

## Ce que je ne fais pas

- Je ne rejuge pas l'amendement de `SEG-1` : c'est une condition pedagogique, ecrite et amendee par
  `nadia-prof`, je la reprends telle quelle.
- Je ne prononce aucun Done pedagogique moi-meme.
- Je ne modifie pas `catalogue.yaml` moi-meme : hors de mon perimetre d'ecriture ; le geste sur
  `mot-manquant-cp` est routee a `sofia-sm`, celui sur `syllabes-cp` n'existe pas (section 2).
- Je ne touche a aucune fiche de `squad/conformite/` ni a l'arbitrage prealable.
- Je n'amende pas `DEC-0045` : sa regle de section 3 s'applique telle quelle sur `mot-manquant-cp`.
- Je ne generalise pas l'arbitrage de l'utilisatrice au-dela du seul mot `cheminee` - la
  generalisation, `SEG-1` clause (c) amendee, est celle de `nadia-prof`, pas la mienne.

## Impact

- `squad/decisions/produit/DEC-0052-...md` (ce fichier) : cree.
- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0052` ajoutee.
- `squad/programme/MATRICE-couverture.md` : lignes 89 et 485 `non couvert` -> `couvert-valide` ;
  note ajoutee sous la ligne 496, statut inchange ; synthese 17/43 (39,5 %).
- `adapters/adapter-content/src/main/resources/catalogue.yaml` : entree
  `bandit-manchot-mot-manquant-cp` a retirer, execution par `sofia-sm` ; entree
  `bandit-manchot-syllabes-cp` inchangee, aucune edition necessaire.
- `docs/backlog/backlog.md` : requalification de l'obstacle `explication`/`SEG-10` ; `SEG-13`
  versee en story ; story corrective pour `bandit-manchot-mot-manquant-cp` ; sept reserves `GRA-11`
  a `GRA-17` de `bandit-manchot-comparer-cp` versees en stories ; note sur
  `contenu-repris/mots/cp-premiere-lettre.yml` (accent manquant, matiere de reserve non servie).
- `squad/decisions/produit/DEC-0051-...md` : troisieme amendement ajoute (population reelle du
  catalogue, 478 items, distincte des chiffres precedents) - trace separement pour ne pas melanger
  deux sujets dans un seul document.
