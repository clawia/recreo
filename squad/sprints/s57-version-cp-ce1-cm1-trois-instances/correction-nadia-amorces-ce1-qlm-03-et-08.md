# Correction des amorces de `-03` et `-08` (ADR-0086, clause 2, FOP-R4)

`nadia-prof`, 2026-10-27, s57 palier 5. Textes a appliquer par `marc-dev` tels qu'ecrits. Je ne modifie aucun YAML.

## 1. Ce que le garde-fou mesure

`LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest`, `defautsDAmorce` : pour chaque proposition d'au moins
4 mots (texte sans accent, sans casse, ponctuation et apostrophe comptees comme separateur), l'amorce est la suite
de ses 4 premiers mots. Deux amorces sont dans le meme moule si elles sont identiques ou ne different qu'en une
position. Un paquet est rouge quand une amorce de distracteur est partagee, moule compris, par 4 distracteurs ou
plus du paquet et par aucune bonne reponse. Le compte est fait sur tout le paquet, pas item par item.

Consequence pour `-03` : *elle grandit*, *elle germe*, *elle pousse plus vite* sont un seul moule, `elle _ plus
vite`, et ce moule compte 4 distracteurs (items 2, 9, 12, 14). Le message du test en affiche 4 pour chacune des
trois amorces, parce qu'il compte le moule autour de chacune.

## 2. Ma part dans ce rouge

Les deux rouges viennent de mes propres corrections, que j'ai relues et tenues :

- `-03` : ma correction 7a (`lecture-nadia-premier-jet-ce1-qlm-besoins-de-la-plante.md`, section 5) a remplace
  *elle donne des fruits* par *elle germe plus vite* a l'item 9. Avant elle, le moule comptait 3 distracteurs.
- `-08` : ma correction 4 (`lecture-nadia-premier-jet-ce1-qlm-dangers-electricite.md`, section 2.3) a remplace
  *ne pas déranger un adulte* par *ne rien dire à personne* chez Hugo. Avant elle, `ne rien dire a` comptait 3
  distracteurs.

Je n'ai compte les amorces ni a la lecture ni a la relecture. La propriete etait gardee par la CI, et c'est la CI
qui l'a vue.

## 3. Remplacements

| # | Paquet, item, ligne | Texte actuel | Remplacement | Raison |
|---|---|---|---|---|
| C1 | `-03`, item 2 (plante privee d'eau), `:73`, 1er distracteur | `elle grandit plus vite` | `elle reste bien droite` | Famille b inchangee : la privation n'aurait aucun mauvais effet. Le sens passe de *mieux qu'avant* a *rien ne change*, que l'en-tete de la famille b admet deja (*"ce qui arriverait s'il ne manquait rien, ou mieux encore"*) ; l'autre distracteur de l'item, *elle devient plus verte*, garde le *mieux*. C'est le contraire exact de *elle se fane* : un enfant qui ne sait pas que la plante a besoin d'eau peut le choisir, un enfant qui le sait l'ecarte. Faux : sans eau, la plante se fane. *Reste* est aussi le verbe de la bonne reponse de l'item 14 : le verbe ne trie plus |
| C2 | `-03`, item 12 (plante privee de chaleur), `:153`, 1er distracteur | `elle pousse plus vite` | `elle continue de pousser` | Famille b inchangee : la privation n'aurait aucun effet. Meme moule que la bonne reponse *elle arrête de pousser* : la seule difference est le verbe, et choisir entre *arrête* et *continue* est la competence (le froid arrete la croissance). Faux, par la consequence que j'ai tenue pour exacte (`I3`). Aucun verbe de la bonne reponse ne devient un signe : *pousser* est dans les deux |
| C3 | `-08`, item 2 (Tom, fil abime), `:71`, 2e distracteur | `ne rien dire à personne` | `ne pas en parler` | Famille c inchangee, une des quatre formes de refus admises (celle de Noe). Tom voit un danger present : ne pas en parler est un refus de signaler, qu'un adulte n'attend pas de lui. Ce n'est pas une abstention (aucune des cinq formes ancrees de la regle 3), *adulte* n'y est pas, aucune precaution n'y est niee |
| C4 | `-08`, item 5 (Lea, grille-pain au bord de l'evier), `:92`, 2e distracteur | `ne rien dire à un adulte` | `ne pas déranger un adulte` | Famille c inchangee, forme admise (celle de Sami). Le danger est deja la, aucun adulte n'est occupe dans l'enonce : ne pas deranger se lit *ne pas signaler*, pas *laisser quelqu'un travailler*, ce qui n'etait pas le cas chez Hugo (section 2.3 de ma lecture). Commence par *ne*, nomme l'adulte : passe `T2` |

Je ne change ni Hugo (`:120`, *ne rien dire à personne*) ni Ines (`:113`) : Hugo n'a encore rien fait, et *ne rien
dire a personne* est la seule forme admise qui s'y lise (*le faire en cachette*) ; Ines garde l'une des deux
occurrences restantes. Je ne change ni l'item 9 ni l'item 14 de `-03` : le moule tombe a 2 sans eux.

### Pourquoi deux par paquet et non un seul

Un seul remplacement par paquet suffit au garde-fou (4 devient 3). J'en ecris deux pour une raison que le
garde-fou ne mesure pas, et seulement pour `-03`, ou elle se constate :

- **`-03`, le mot *plus*.** Avant correction, 8 des 10 distracteurs longs portent *plus* et aucune bonne reponse
  ne le porte. Dans 3 items sur 5 de la tache privation (2, 6, 12), *plus* est sur les deux distracteurs et pas
  sur la bonne reponse : l'enfant trouve la reponse en cherchant le seul bouton sans *plus*. C'est la propriete
  de la clause 3, que le garde-fou ne voit pas parce que *plus* n'est pas dans sa liste de marqueurs (critere
  enumere assume, ADR-0086). C1 et C2 la retirent des items 2 et 12 ; il reste l'item 6 (section 5).
- **`-08`.** Avec C3 seule, le moule `ne rien dire a` resterait a 3 sur 4. Avec C3 et C4, la repartition des refus
  devient *ne pas déranger un adulte* 2 (Sami, Lea), *ne pas en parler* 2 (Tom, Noe), *ne rien dire à un adulte* 1
  (Ines), *ne rien dire à personne* 1 (Hugo) : aucune forme ne revient assez pour devenir le signe d'un item.

## 4. Contraintes verifiees

- **Mesure de la clause 2**, par une replique en Python de `mots`, `memeMoule` et `defautsDAmorce`, pas par le
  test lui-meme. La replique reproduit les comptes du rouge actuel (`-03` : 4 sur 10 pour chacune des trois
  amorces, 4 bonnes reponses longues ; `-08` : 4 sur 22, 11 bonnes reponses longues). Apres C1 a C4 : `-03`, moule
  le plus partage 3 (`elle devient plus verte`, items 2 et 6, et `elle devient plus solide`, item 6), `elle _ plus
  vite` 2 ; `-08`, aucun moule au-dela de 2. Le moule `elle devient plus _` est a 3, un en dessous du seuil : un
  distracteur de plus de cette forme rend `-03` rouge. Le verdict de la vraie clause est celui de `mvn verify`
  apres application.
- **A.259** : aucun item ajoute ni retire, 14 items et 42 propositions pour `-03`, 12 et 36 pour `-08`, aucune
  proposition ne commence par un chiffre. L'entree de `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`
  reste juste.
- **A.273, defaut b** : chaque remplacement ne s'ecarte qu'avec la competence (le besoin d'eau, le froid qui
  arrete la croissance, signaler un danger), aucun n'est impossible pour son sujet.
- **TRV-CE1-2** : familles declarees inchangees (`b, b` pour les items 2 et 12 de `-03` ; `a, c` pour Tom et Lea).
  Trois propositions distinctes. Bonne reponse strictement la plus longue : aucun item ajoute a ce compte.
- **LON-CE1-5** : 4 mots chacun, une seule phrase.
- **BPL-CE1-1 et -2, DEL-CE1-1 et -2, QMT-CE1-1** : aucun mot exclu, aucune pile, aucun mot d'accident, aucun
  besoin nomme en distracteur.
- **`-08`, raccourci du *ne*** (ma lecture, section 3.2) : inchange. C3 et C4 remplacent un refus en *ne* par un
  refus en *ne*.

**Ce que `marc-dev` doit ajuster hors du YAML, que je n'ecris pas** : la table `EFFETS_CONTRAIRES` de
`PaquetCe1QlmBesoinsDeLaPlanteTest` doit admettre *elle reste bien droite* et *elle continue de pousser* ; garder
ou retirer *elle grandit plus vite* de la table lui revient, avec `theo-archi`. Aucune garde de
`PaquetCe1QlmDangersElectriciteTest` ne nomme les formes de refus : rien a y changer a ma connaissance.

## 5. Une bonne reponse doit-elle changer ?

Non, dans aucun des deux paquets. Faire entrer une bonne reponse dans le moule (par exemple *elle pousse moins
vite* a l'item 12) rendrait la clause verte, mais au prix de l'exactitude : par grand froid la plante arrete de
pousser, et c'est la consequence que j'ai tenue pour exacte (`I3`). Les bonnes reponses de `-08` sont des formes
admises de la regle 2 ; aucune ne se lit dans `ne rien dire a`.

**Hors du rouge, une recommandation dont le perimetre revient a `camille-po`.** Apres C1 et C2, l'item 6 de
`-03` reste le seul item dont la bonne reponse est le seul bouton sans *plus* (*elle devient pâle et pousse mal* ;
*elle devient plus verte*, *elle devient plus solide*). Un seul item ne suffit pas a apprendre le signe, et la
clause 3 elle-meme s'arreterait sous son seuil de 2 items. Si le lot est rouvert pour C1 a C4, je recommande, a
`:105`, 2e distracteur : `elle devient plus solide` devient `elle pousse très bien`. Famille b inchangee (mieux
encore), contraire de *pousse mal*, faux dans le noir ; *pousse* passe dans un distracteur et ne trie plus ;
`elle devient plus _` tombe a 2, et aucun moule de `-03` ne depasse 2 (meme replique). Sans ce remplacement, je suis seule a garder cette propriete sur l'item 6, et je
la regarderai a l'ecran a l'audit.

## 6. Ces changements exigent-ils une relecture de ma part, et a quel cout

Je ne classe pas ; c'est a `camille-po`. Ce que je constate :

- **Ce fichier juge les quatre propositions** contre leur liste fermee, avant application. Ce que je relirais
  ensuite serait la transcription de quatre lignes (six avec l'item 6), et le verdict de `mvn verify`.
- **Les textes les plus proches** : A.272 (condition C2 d'A.268) dit que remplacer une proposition n'est pas un
  arbitrage de formulation, et la sort du 0,00 ; A.279 point 5 dit qu'une correction *"que `marc-dev` reprend
  apres la relecture, reste une seconde correction suivie d'une relecture, troisieme lecture (A.272 fait 3)"*.
  `-03` (A.272) et `-08` (A.278) ont chacun deja leur seconde lecture comptee a 0,50.
- **Ce que les textes disent du cout** : une troisieme lecture vaut 0,50 hors marge par lot. A.278 point 4 :
  reserve 0,00, plafond de 22,20 atteint ; une lecture de plus porte le cas haut a 22,70, deux a 23,20. A.273
  point 1 (b) : au-dela de 22,20, le cout va au registre et ne bloque le Done d'aucun lot.
- **Ce qu'aucun texte ne nomme** : une correction de propositions ecrite par moi apres la seconde lecture, parce
  qu'un garde-fou est rouge au `verify`. Ce n'est ni un arbitrage avant premier jet (A.276), ni une reouverture a
  la premiere lecture (A.277), ni un rejeu apres refus (`P3`). Si ce fichier compte lui-meme comme la troisieme
  lecture, ou si la relecture de la transcription en est une, c'est a classer.
- Je n'ecris pas que j'ai relu les paquets entiers (A.277, condition d) : j'ai lu les items 2, 6, 9, 12, 14 de
  `-03` et les items 2, 5, 8, 9 de `-08`. Les comptes de moules et de *plus* portent sur tout le paquet, par
  script.

## 7. Routage

| A qui | Quoi |
|---|---|
| `marc-dev` | appliquer C1 a C4 telles qu'ecrites ; ajuster `EFFETS_CONTRAIRES` ; lancer `mvn -B clean verify` |
| `camille-po` | classer le cout de section 6 ; decider si l'item 6 (section 5) entre dans la correction |
| `theo-archi` | savoir que la clause 3 laissait passer *plus* sur 3 items de `-03` ; je ne demande pas d'ajouter *plus* au lexique, ce mot sert aussi des propositions justes ailleurs |
