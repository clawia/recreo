# DEC-0057 - Perimetre du sprint 23 : la reserve de contenu seul tombe a zero candidat pret,
# huit arbitrages prealables poses des l'ouverture, sept reserves non bloquantes de s22 et un
# garde-fou de gouvernance entrent au perimetre garanti, le mur de la mecanique unique est pose a
# l'utilisatrice avec son cout et son moment plutot que tranche ici

- **Type** : decision de perimetre de sprint (`camille-po`) et question posee a l'utilisatrice
  (mur de la mecanique unique, non tranchee par ce document)
- **Date** : 2026-08-30
- **Auteur** : camille-po
- **Sprint** : s23 (ouverture)
- **Competence programme visee** : transverse - pas de contenu pedagogique pour le classement de
  perimetre lui-meme (section 1) et pour le mur de la mecanique unique (section 4). Les huit
  candidats poses en section 2 portent chacun leur ligne de referentiel, citee dans leur propre
  paragraphe.
- **Statut** : Acceptee

## Contexte

s22 se cloture a **21/43 (48,8 %)**, catalogue a 24 jeux (`RAPPORT.md` de s22,
`squad/programme/MATRICE-couverture.md`, amendement du 2026-08-30). L'utilisatrice ouvre s23 avec
une consigne unique : **couverture toujours croissante**, en autorisant explicitement plusieurs
stories par developpeur si cela permet de tenir un debit plus eleve. Elle me demande aussi de
porter, dans le perimetre de ce sprint et non a s24, la question du mur de la mecanique unique -
je la pose en section 4, je ne la tranche pas.

`nadia-prof` a rendu, le 2026-08-30, sa reponse a la question de capacite posee a la cloture de
s22 (`avis-nadia-done-pedagogique-s22.md`, section 12) : **quatre a cinq paquets par sprint, sous
trois conditions**, et une correction de diagnostic que je reprends telle quelle : *"ni le nombre
de paquets ni la livraison groupee n'est la contrainte. Ce qui coute est proportionnel au nombre
d'items, pas de paquets."*

## 1. Verification personnelle de l'etat avant d'ecrire un perimetre

### Matrice de couverture

`squad/programme/MATRICE-couverture.md`, amendement du 2026-08-30 : **21/43 (48,8 %)** sur le
denominateur atteignable (43 sur 47 lignes, 4 lignes `non couvrable en l'etat` retirees). Repartition :
mathematiques CP 5/11 `couvert-valide` (1 `en cours`) ; mathematiques CM1 6/12 (0 `en cours`,
2 `non couvrable`) ; francais CP 3/12 (1 `en cours`, 1 `non couvrable`) ; francais CM1 7/12
(1 `non couvrable`). Total : 21 `couvert-valide`, 2 `en cours`, 20 `non couvert`, 4 `non couvrable`.

### Le fait qui domine ce perimetre, verifie ligne par ligne avant d'ecrire quoi que ce soit

`DEC-0053`, section 7, avait classe les 25 lignes alors `non couvert`/`en cours` en deux groupes :
18 lignes atteignables sans mecanique neuve (contenu seul, ou item visuel/audio servi par
`BanditManchot` sans changement de mecanique), et 7 lignes exigeant une mecanique ou un jugement
que ce produit n'a pas. Sur ces 18, **six etaient deja arbitrees et pretes** : `S13-2`, `S13-3`,
`S17-2`, figures planes, fraction-reperage, decoder par assemblage syllabique.

**Les trois premieres sont consommees par s22** (`couvert-valide` au 2026-08-30). **Je verifie,
avant d'ecrire le perimetre de s23, ce qu'il reste des trois autres** : aucune n'est developpable
aujourd'hui, pour deux raisons distinctes, verifiees contre leur source, pas supposees.

- **Figures planes (`FIG-1` a `FIG-4`)** est arbitree pedagogiquement depuis s19
  (`squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md`, section 2),
  mais bloquee sur l'absence de decision d'architecture pour un item visuel (une forme rendue) -
  `DEC-0053` section 7 le dit en toutes lettres : *"le visuel n'a encore aucune decision
  d'architecture"*. Meme constat verifie dans le fichier d'arbitrage lui-meme (ligne 444) :
  *"`FIG-1` lui reviendra le jour ou la famille visuelle [...]"*, jamais arrivee depuis.
- **Fraction - moitie "reperage sur une droite numerique"** a son proxy texte deja refuse, motif
  ecrit (`squad/conformite/ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md`, section 2) : une ligne
  graduee rendue resterait a arbitrer une fois qu'elle existerait techniquement, ce qui n'est pas
  le cas.
- **Decoder un mot par assemblage syllabique (`SYL-1` a `SYL-4`)** est arbitree pedagogiquement,
  mais bloquee sur une question qui n'est pas technique : la source d'un enregistrement de voix
  humaine, qu'aucun agent de cette squad ne peut fournir (`DEC-0053`, section 7.3).

**Consequence directe et verifiee, pas supposee : la reserve de contenu seul reellement pret a
developper aujourd'hui est de zero ligne.** C'est la premiere fois depuis s19 que ce backlog est
vide a l'ouverture d'un sprint - a s19, s20, s21 et s22, au moins une ligne arbitree attendait deja
un developpeur. A s23, les six candidats de contenu seul restants (voir section 2) n'ont **encore
recu aucun arbitrage individuel** : ils ont ete poses par `DEC-0053` section 6 et 7 le 2026-08-28,
jamais repondus depuis (verifie par recherche de leurs noms de fichier et de competence dans
`squad/conformite/` : aucune reponse trouvee, hors les fichiers d'origine qui les posent).

## 2. Perimetre du sprint - huit arbitrages prealables poses des l'ouverture, priorite absolue

Pour ne pas reproduire le defaut deja nomme sur `NAT-5` et sur `DEN-1` a `DEN-8` (perdre un sprint a
instruire un arbitrage une fois le developpement pret) - et parce que la reserve de contenu seul est
a zero ligne pretes, la priorite du sprint n'est pas un lot de developpement mais **huit reponses de
`nadia-prof`**, posees dans le meme geste que ce document, pour qu'elle puisse les rendre pendant
que le reste de la squad travaille sur le perimetre garanti (section 3).

### Six candidats de contenu seul (`DEC-0053`, section 7.1), aucun encore arbitre individuellement

| Ligne | Matiere/niveau | Reference |
|---|---|---|
| Comparer et ranger des nombres jusqu'a 100 - moitie "ranger" | mathematiques CP | `squad/programme/mathematiques/competences-cp.md` |
| Resoudre des problemes a plusieurs etapes mobilisant les 4 operations | mathematiques CM1 | `squad/programme/mathematiques/competences-cm1.md`. Risque nomme d'avance par `DEC-0021` |
| Comprendre un texte narratif tres court (questions explicites : qui, ou, quoi) | francais CP | `squad/programme/francais/competences-cp.md` |
| Reconnaitre et utiliser les marques du pluriel les plus frequentes (`-s`) | francais CP | `squad/programme/francais/competences-cp.md` |
| Reperer les substituts d'un personnage (pronoms, groupes nominaux) dans un texte | francais CM1 | `squad/programme/francais/competences-cm1.md` |
| Utiliser a bon escient des mots de liaison simples pour structurer un texte court | francais CM1 | `squad/programme/francais/competences-cm1.md` |

### Deux candidats de niveau 2 (`DEC-0051`), poses le 2026-08-28 par `DEC-0053` section 6, jamais repondus

- `contenu-repris/mots/cp-premiere-lettre.yml` sur "Identifier un son (phoneme) dans un mot"
  (francais CP) : un choix de la premiere lettre **ecrite** d'un mot satisfait-il un phoneme
  **entendu**, ce que ce fichier ne fournit pas ?
- `contenu-repris/fractions/cm1-compare.yml` sur "Notion de fraction simple... et reperage sur une
  droite numerique" (mathematiques CM1) : peut-il couvrir la moitie "notion" seule, en couverture
  partielle explicitement nommee, sans laisser croire que "reperage" est traite ?

### Ce que je ne fais pas ici

Je ne propose aucune de ces huit lignes comme story de developpement de s23 : je pose les huit
questions pour que la reponse existe le plus tot possible dans le sprint, pas pour prejuger d'aucun
verdict. Aucun agent n'est affecte au developpement d'un candidat tant que `nadia-prof` ne l'a pas
arbitre.

### Le risque a nommer, verifie et non suppose

`DEC-0053` section 7.2 l'a deja mesure : sur les quatre lignes que `DEC-0030` avait pensees
"contenu seul" (denombrer, figures planes, decoder par assemblage syllabique, relire son texte),
**les quatre ont ete redirigees hors du contenu seul une fois l'arbitrage rendu** - taux de
redirection 4 sur 4. Je ne peux pas planifier sur l'hypothese que les six candidats d'aujourd'hui
survivront mieux : trois d'entre eux (comprendre un texte tres court, substituts d'un personnage,
mots de liaison) portent un risque d'appariement de la meme famille que `cm1-comprehension`,
refusee une fois avant d'etre acceptee sous cinq conditions (`S17-2`). **Le gain de couverture de
s23 depend donc entierement de ce que `nadia-prof` arbitre, pas d'un chiffre que je peux fixer a
l'avance** - voir section 4 pour ce que ce risque signifie pour le mur de la mecanique unique.

## 3. Perimetre garanti - ce qui se developpe sans attendre aucun arbitrage neuf

Trois lots ne dependent d'aucune des huit reponses ci-dessus et peuvent commencer des l'ouverture
du sprint, pour que la squad ne reste pas inactive pendant que `nadia-prof` arbitre :

### 3.1 Les sept reserves non bloquantes de s22

Deja versees en stories testables dans `docs/backlog/backlog.md`
(section "Sprint 22 (cloture, 2026-08-30)") : cinq sur `cm1-problemes-durees` (reserves A a E,
`lea-dev`, auteure du paquet), deux sur `cm1-comprehension` (reserves 1 et 2, `marc-dev`, auteur du
paquet). Elles ne font bouger aucune ligne de la matrice (les deux jeux sont deja `couvert-valide`
sous reserve ou valide), mais elles ferment une dette de qualite deja nommee et chiffree par
`nadia-prof` elle-meme (couts donnes reserve par reserve dans le backlog). J'entre les sept au
perimetre de s23.

### 3.2 Le garde-fou de coherence charte d'agent / `DEC` / `ADR`

Propose par `sofia-sm` a la retro de s22 (`squad/sprints/s22-couverture-maximale-du-catalogue/
retro.md`, section "Amendements a squad/README.md", point 1), sur un constat que je verifie et que
je ne minimise pas : **deux sprints consecutifs** ont trouve une charte d'agent contredisant une
decision du depot en vigueur - `marc-dev`/`lea-dev` sur la redaction de contenu a s21,
`relecteur-pedagogique`/`nadia-prof` sur l'accentuation a s22, cette derniere contradiction ayant
coute un lot entier (refus de `S13-2` sur l'accentuation). **Dans les deux cas, la contradiction a
ete decouverte par un agent qui butait dessus en situation reelle, jamais par un balayage.** Un
troisieme cas de la meme famille est possible a tout moment sur une charte que personne n'a
encore relue.

**Je prends cette priorite au perimetre de s23**, pour trois raisons : c'est un cout borne (un
outil de coherence, pas une conception neuve), il ne consomme aucune capacite d'audit de
`nadia-prof`, et le moment ou dev a le moins de contenu deja arbitre a assigner (l'ouverture de ce
sprint precisement, tant que les huit arbitrages de la section 2 ne sont pas rendus) est le moment
ou l'affecter coute le moins cher en couverture non produite ailleurs. Faisabilite technique reste
a `theo-archi` (deja route par `sofia-sm`) ; je n'en tranche pas la forme.

### 3.3 Le garde-fou d'accentuation, si et seulement si `theo-archi` le confirme faisable

`nadia-prof` le demande explicitement (`avis-nadia-done-pedagogique-s22.md`, section 12.2, point 1
et section 13) : *"la propriete se teste sur un lexique... un garde-fou executable aurait evite ce
refus et ce lot"*. C'est aussi la premiere des trois conditions qu'elle pose pour monter sa
capacite (section 5 ci-dessous). Je n'en tranche pas la faisabilite - route a `theo-archi`, qui
travaille en parallele sur ce point (branche `s23-theo`, aucun commit au moment ou j'ecris ce
document). **S'il le confirme faisable et le livre pendant s23**, il entre au perimetre du sprint
sans amendement de ce document ; s'il ne le confirme pas, cette porte reste manuelle comme
aujourd'hui et la condition 1 de la section 5 n'est pas remplie.

### 3.4 Ce qui reste route sans que je le priorise moi-meme

`TYP-1` "le moyen" (choix du caractere d'espace insecable, application a `catalogue.yaml` - zero
insecable aujourd'hui sur 21 signes orphelins mesures) et l'obstacle 25 residuel (cinq scripts de
`nadia-prof` executes a l'import, non testes par un juge pur) restent routes a `theo-archi` et
`sofia-sm` : ce sont des choix techniques, pas les miens. Je recommande qu'ils entrent au perimetre
de s23 pour la meme raison que 3.2 (capacite dev disponible en attendant les arbitrages), mais la
priorisation finale entre eux, le garde-fou de coherence et le garde-fou d'accentuation appartient
a `sofia-sm`.

## 4. Obstacle 21 ferme - troisieme fois qu'il aurait ete reporte, je le clos plutot que de le reporter

Obstacle 21 (s21) - "4 items sans levier connu" (`bandit-manchot-grands-nombres-cm1` x2,
`bandit-manchot-reperes-cm1` x2) - a ete route a moi, reste `OUVERT, hors perimetre de s21` puis
`OUVERT, hors perimetre de s22`. Le reporter une troisieme fois sans decision serait exactement le
defaut que `DEC-0007` a deja sanctionne sur ce produit. **Je le clos ici, avec motif ecrit, plutot
que de le reporter.**

Verification faite avant de clore : ces quatre items sont des items **sous le pli** dans des jeux
deja `couvert-valide`, sans levier technique connu pour les faire remonter au-dessus. `DEC-0053`,
arbitrage 1 (2026-08-28), a deja retire le pli de la porte pedagogique sur tout le catalogue, et la
clause `MNC-1` (versee au gabarit de fiche de conformite, `squad/TEMPLATES.md` section 7) rend
cette dette **explicitement croissante et assumee** plutot qu'un defaut a corriger au cas par cas.
Il n'y a donc plus rien a instruire sur ces quatre items precis : ils suivent deja le regime que
`DEC-0053` a fixe pour tout le catalogue. **Statut final : reserve non bloquante permanente,
absorbee par l'arbitrage 1 et `MNC-1`, aucune story de correction n'est ouverte.**

## 5. Rechiffrage de la borne de capacite, avec sa methode et ce qu'elle suppose

### Ce que `nadia-prof` avance, et ce que je verifie avant de le reprendre

`avis-nadia-done-pedagogique-s22.md`, section 12.3 : **quatre a cinq paquets par sprint**, contre
trois a s22, sous trois conditions cumulatives qu'elle ecrit elle-meme :

1. l'accentuation est gardee par un automate - sinon elle la mesure a la main sur chaque
   population, comme trois fois a s22 ;
2. chaque paquet arrive avec ses comptes declares et leur methode - pratique deja acquise depuis
   s22, verifiee exacte sur les trois lots (section 10.4 de son avis) ;
3. au plus un paquet par sprint porte une propriete du type de `COMP-2` (verifiable seulement item
   par item, aucun automate ne la voit).

Elle nomme aussi une limite que je reprends sans l'affaiblir : *"ce que je ne ferai pas pour tenir
un chiffre : echantillonner"* - deux de ses trois refus de s22 seraient passes au travers d'un
echantillon.

### Pourquoi je ne tiens pas 4-5 comme borne acquise de s23

**La condition 1 est un prerequis de sprint, pas un voeu.** Au moment ou j'ecris ce document, le
garde-fou d'accentuation n'existe pas et sa faisabilite n'est pas encore confirmee (section 3.3) -
`theo-archi` travaille en parallele, sans commit a ce jour sur `s23-theo`. **Sans lui, la borne
retombe** : `nadia-prof` l'ecrit elle-meme, la condition n'est pas une preference, c'est un plancher
en dessous duquel le chiffre de 4-5 ne tient pas.

**Je tiens donc trois paquets comme borne haute sure pour s23**, la meme qu'a s22, avec un
avertissement que la methode de `DEC-0053` n'avait pas a porter : **contrairement a s22, cette
borne n'est adossee a aucun candidat deja pret** (section 1). Si `theo-archi` confirme et livre le
garde-fou d'accentuation pendant le sprint, et que la condition 3 est respectee au moment
d'assigner un paquet (les candidats de comprehension/lecture parmi les six de la section 2 sont les
plus susceptibles de porter une propriete du type `COMP-2` - un seul d'entre eux par sprint), la
borne peut monter a 4 ou 5 en cours de route sans amendement de ce document, exactement comme la
consigne de l'utilisatrice le permet ("plusieurs stories par dev"). **Je ne l'ecris pas comme
acquise a l'ouverture, parce qu'aucune de ses trois conditions n'est aujourd'hui verifiee remplie.**

### Ce que la consigne "plusieurs stories par dev" change reellement, verifie et non suppose

Elle ne change rien au goulot mesure par `nadia-prof` (section 12.1 de son avis : *"ni le nombre de
paquets, ni la livraison groupee"* n'est la contrainte - le cout est proportionnel au nombre
d'items lus, pas au nombre de developpeurs qui les produisent). Trois developpeurs qui se
partagent plusieurs stories chacun peuvent produire plus de paquets en parallele, mais cela ne
fait pas auditer `nadia-prof` plus vite : **la borne de s23 reste celle de sa capacite d'audit, pas
celle de la capacite de developpement**, et je ne la monte pas sur la foi de la consigne seule.

## 6. Le gain de couverture vise - un objectif conditionnel, pas un chiffre ferme

Contrairement a `DEC-0053` (ou trois candidats deja arbitres donnaient un objectif net,
18/43 -> 21/43), **je ne peux fixer aucun objectif ferme a l'ouverture de s23** : la reserve pretes
est a zero ligne (section 1), et le gain reel depend entierement des huit arbitrages de la
section 2, dont je ne peux pas prejuger le sens.

**Ce que je peux dire, avec sa methode** : si `nadia-prof` arbitre rapidement (dans les premiers
jours du sprint, comme elle l'a fait pour les questions de niveau 2 a s19-s21) et que le taux de
survie du contenu seul se maintient a son niveau historique (0 sur 4 lignes "pensees contenu seul"
ont survecu telles quelles depuis `DEC-0030`), **l'objectif prudent est 21/43 inchange a 24/43
(48,8 % a 55,8 %)** - zero a trois lignes nouvelles, borne haute alignee sur la capacite de
section 5. Si aucun arbitrage n'aboutit a temps pour permettre un developpement puis un audit
complet dans la fenetre du sprint, s23 peut cloturer sans mouvement de couverture - **ce que je
signale maintenant, explicitement, plutot que de laisser croire a un engagement que je ne peux pas
tenir** : la consigne de couverture croissante rencontre ici une dependance reelle (l'arbitrage
pedagogique), pas un manque de volonte de perimetre.

**Ce document ne prononce aucun Done pedagogique et ne fait bouger aucune ligne de
`squad/programme/MATRICE-couverture.md`.**

## 7. Le mur de la mecanique unique - etat exact, question posee a l'utilisatrice, non tranchee ici

### Ou en est la reserve de contenu seul, exactement, apres s22

**Six lignes**, toutes non arbitrees individuellement a ce jour (section 2). C'est la totalite de
ce qui reste, sur la population complete des 25 lignes que `DEC-0053` avait classees a l'ouverture
de s22 (18 atteignables sans mecanique neuve, dont 6 deja arbitrees et desormais consommees ou
bloquees sur architecture/production - section 1 - et 6 encore a l'etat de candidat). Les autres
familles (item visuel : 5 lignes ; item audio : 4 lignes) restent, elles aussi, dans la population
des 25, mais bloquees sur un prealable distinct de l'arbitrage pedagogique (architecture visuelle
non ecrite, source de voix humaine non tranchee) - je ne les compte pas dans la reserve de
"contenu seul" au sens strict, parce qu'aucune des deux ne peut aboutir par le seul travail de
redaction de contenu, meme une fois arbitree.

### A quel moment precis l'ouverture d'une seconde mecanique devient inevitable

`DEC-0053`, section 7.4, avait deja nomme le declencheur : **le moment ou `nadia-prof` a arbitre
les six candidats et que moins de trois d'entre eux survivent comme contenu seul** - c'est-a-dire
le moment ou la reserve confirmee retombe sous la borne d'un sprint (trois, section 5).

**Ce que je verifie et ajoute ici, parce que le fait a change depuis que ce declencheur a ete
ecrit** : les huit arbitrages sont poses des l'ouverture de s23 (section 2), pas a la cloture. Si
`nadia-prof` les rend tot dans le sprint - ce qu'elle a fait sur des questions comparables a
s19-s21 -, **le declencheur peut se produire pendant s23 lui-meme, pas seulement a s24**. Sur le
taux historique (0 sur 4 lignes "pensees contenu seul" ont survecu telles quelles), c'est le
scenario le plus probable, pas un cas limite.

### Ce que cela coute, dans les termes deja ecrits par `theo-archi`, sans que j'en ajoute un chiffre que je n'ai pas

La **saisie** est, aujourd'hui, la seule des trois familles nommees par `theo-archi`
(`avis-ordre-de-grandeur-seconde-mecanique.md`, s13 ; `ADR-0058`, s19) instruite jusqu'au bout sans
qu'aucune capacite de developpement n'y ait ete affectee : **onze conditions pedagogiques rendues**
(`SAI-1` a `SAI-11`, 2026-08-24), **une decision d'architecture ecrite et mesuree** (`ADR-0058`),
**zero ligne de code**. `ADR-0058` le dit lui-meme dans ses consequences : *"un sprint ulterieur
peut ouvrir des stories de saisie sans rouvrir la conception... ce qui reste a decider par un lot
est de l'implementation, pas de l'architecture"*. Je n'ai, et `theo-archi` n'a pas non plus donne,
de chiffre d'effort de developpement au-dela de cette phrase - je ne l'invente pas.

**Une question deja posee et jamais tranchee accompagne ce choix, je la signale sans la reposer
moi-meme** : `ADR-0058` route a l'utilisatrice une question sur l'acces a une lettre accentuee sur
tablette, qui conditionne la valeur pedagogique de la saisie alphabetique (pas son architecture,
que `ADR-0058` ecrit valable dans les deux cas). Aucune reponse trouvee depuis sa formulation
(s19). Si l'utilisatrice ouvre la saisie, cette question devra etre tranchee avant que le premier
lot ne soit audite par `nadia-prof`, pas apres.

### La question, exacte, posee a l'utilisatrice - je ne la tranche pas

Compte tenu de ce qui precede - reserve de contenu seul a six lignes non arbitrees, taux de survie
historique de 0 sur 4 sur des candidats de la meme nature, declencheur susceptible de se produire
pendant s23 lui-meme, et une seule famille (la saisie) instruite jusqu'au bout sans capacite
affectee -, **je pose la question suivante, sans y repondre** :

**Si le declencheur se confirme pendant s23 (moins de trois des six candidats de section 2
survivent comme contenu seul une fois arbitres), autorisez-vous l'ouverture de la mecanique de
saisie des ce moment - en affectant un developpeur au premier lot des que `nadia-prof` a rendu ses
huit arbitrages, sans attendre la cloture du sprint -, ou preferez-vous attendre la cloture
complete de s23 pour statuer, en connaissance du cout deja nomme par `DEC-0007` (un perimetre qui
se ferme sprint apres sprint sur un motif chaque fois defendable finit par couter un sprint entier
d'attente) ?** Le cout d'ouvrir est connu dans ses termes (onze conditions pedagogiques et une
architecture deja ecrites, zero ligne de code, une question d'accessibilite encore ouverte) mais
non chiffre en effort de developpement ; le cout d'attendre est un sprint de capacite de
developpement possiblement inactive si la reserve de contenu seul s'epuise avant que la decision
ne soit rendue.

Je ne recommande ni le moment ni la mecanique a ouvrir en premier : ce jugement reste a
`nadia-prof` pour la priorite pedagogique (deja rendu, `AVIS-PRIORITE-PEDAGOGIQUE-s13-seconde-
mecanique.md` : audio, puis visuel, puis saisie) et a l'utilisatrice pour l'arbitrage final.

## Ce que je route

- **`nadia-prof`** : les huit arbitrages prealables de la section 2, en priorite absolue sur ce
  sprint - c'est le seul geste qui peut faire bouger la couverture de s23.
- **`sofia-sm`** : affectation du perimetre garanti (section 3) au planning ; priorisation finale
  entre le garde-fou de coherence charte (3.2), `TYP-1` "le moyen" et l'obstacle 25 residuel (3.4) ;
  portage de la question de la section 7 a l'utilisatrice, dans ses termes exacts.
- **`theo-archi`** : faisabilite et, si possible, livraison du garde-fou d'accentuation (3.3),
  condition 1 de la capacite rechiffree (section 5) ; faisabilite du garde-fou de coherence charte
  (3.2) ; confirmation ou correction de ma lecture de son propre avis sur le mur de la mecanique
  unique (section 7).
- **`marc-dev`/`lea-dev`** (via `sofia-sm`) : les sept reserves de perimetre garanti (3.1), des
  l'ouverture du sprint, sans attendre aucun arbitrage.
- **`l'utilisatrice`** (via `sofia-sm`) : la question exacte de la section 7 - le moment d'ouvrir la
  mecanique de saisie, avec son cout connu et sa dependance nommee.

## Ce que je ne fais pas

- Je ne tranche aucune question technique : la faisabilite du garde-fou d'accentuation, du
  garde-fou de coherence charte, et le choix du caractere d'espace insecable (`TYP-1`) restent a
  `theo-archi` et `sofia-sm`.
- Je ne juge aucune qualite pedagogique de contenu : les huit arbitrages de la section 2 sont
  poses, pas repondus.
- Je ne prononce aucun Done pedagogique et ne fais bouger aucune ligne de
  `squad/programme/MATRICE-couverture.md`.
- Je ne recommande aucune mecanique a ouvrir ni aucun moment de l'ouvrir (section 7) : je nomme un
  declencheur objectif deja ecrit par `DEC-0053` et je signale qu'il peut se produire plus tot que
  prevu, je ne tranche pas a la place de `nadia-prof` ni de l'utilisatrice.
- Je ne cree ni le repertoire de sprint ni `planning.md` : `sofia-sm` les ecrit.
- Je ne fixe aucun objectif de couverture ferme (section 6) : la dependance a l'arbitrage
  pedagogique est nommee, pas contournee par un chiffre que je ne peux pas garantir.

## Impact

- `squad/decisions/produit/DEC-0057-...md` (ce fichier) : cree.
- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0057` ajoutee.
- `docs/backlog/backlog.md` : section "Backlog - perimetre s23" ajoutee, portant les huit
  arbitrages de la section 2 et le perimetre garanti de la section 3.
- `squad/programme/MATRICE-couverture.md` : note d'ouverture de s23 ajoutee (reserve de contenu
  seul a six lignes non arbitrees, aucune ligne ne change de statut).
- Aucun fichier sous `domain/`, `application/`, `adapters/` ou `bootstrap/` n'est modifie par cette
  decision.
