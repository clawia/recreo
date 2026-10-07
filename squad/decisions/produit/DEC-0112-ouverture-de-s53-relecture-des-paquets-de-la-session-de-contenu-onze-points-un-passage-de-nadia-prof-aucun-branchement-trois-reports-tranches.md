# DEC-0112 - Ouverture de s53 : onze points de relecture des paquets de la session de contenu, un seul passage de
# `nadia-prof`, aucun branchement, trois postes engages et deux retires au troisieme report

- **Type** : decision d'ouverture de sprint (perimetre, instance, reports, routage) et decision de P9.
- **Date** : 2026-10-07
- **Auteur** : `camille-po`
- **Sprint** : s53
- **Competences programme visees** : transverse - pas de contenu pedagogique. Aucune ligne de matrice ne
  change de statut a s53. Les matieres des paquets relus (francais CE1, mathematiques CE1, EMC CM1, histoire
  CM1, questionner le monde CP, arts CM1, histoire des arts CM1) sont donnees pour information.
  Le seul poste qui touche un jeu servi est `CRP-CM1-R1` a `R3` (`SCI-CM1-10`, ligne deja `couvert-valide`).
- **Statut** : Acceptee (decisions de `camille-po` ; voir section 0 pour ce que l'utilisatrice n'a pas encore ecrit)
- **Lien** : `DEC-0111` (amendements A.13, A.14, A.16 ; non editee ici, citee) ; `DEC-0110` (sections 14.7, 17.2) ;
  `DEC-0002` ;
  `squad/sprints/s53-relecture-paquets-session-contenu/planning.md` ;
  `docs/backlog/backlog.md`, section "Session hors sprint s53 special" ;
  `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md`
  (sections 13.2, 13.3 : `PAR-4`, `PAR-8`, `PAR-10`) ;
  `squad/conformite/ARBITRAGE-PREALABLE-s52-fra-ce1-questions-de-l-avis.md` (section 4.3 : `PAR-9`) ;
  `squad/conformite/ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-borne.md`
  (mention de couverture partielle de `EMC-CP-05`) ;
  `squad/programme/enseignement-moral-et-civique/competences-cm1.md` (section "Mention exigee d'avance si une
  ligne bouge") ; `squad/README.md` (section 2 bis, section 4 `PAR-16`).

---

## 0. Provenance et methode

**Perimetre.** Il vient de la story `S53SPE-RELECTURE` (forme 1, `camille-po`, backlog) et du planning de
`sofia-sm` (forme 1, `planning.md`). Le message qui a lance cette tache est un message d'agent : il ne fonde rien
(`squad/README.md` section 2 bis).

**Ce que l'utilisatrice n'a pas ecrit dans le depot.** `DEC-0111` A.14 voulait porter l'ouverture de s53 a
l'utilisatrice. Le message de l'agent qui m'a lance dit qu'elle a arbitre le 2026-10-07 deux points (decideur de
P11, lecture de P1, P3, P5). **Je n'ai pas ses mots** ; je les cite comme relayes, sans guillemets, et aucune
section ci-dessous n'en depend : chacune porte mon motif. Si ses mots divergent de ce qui est ecrit en section 3,
cette decision est amendee.

**Mesures lancees pour cette decision** (arbre `round-two`, worktree `s53-camille-p9`, 2026-10-07) :

1. `git diff --name-status 9d44d510 76a50e8b -- adapters/adapter-content/src/main/resources/contenu` : 45 lignes,
   toutes `A` (45 fichiers ajoutes, aucun modifie, aucun supprime).
2. `grep -c` sur les points constates, rendus par commande en section 2.
3. `grep -l ' competence: ".* :'` sur `contenu/*.yaml` : 13 fichiers ; la meme recherche avec l'espace insecable
   (`\xC2\xA0:`) : 0.
4. Matrice : les lignes `sciences et technologie | CM1` sont 20 ; **deux** sont `non couvert`, `SCI-CM1-15` et
   `SCI-CM1-18`, une est `non couvrable en l'etat` (`SCI-CM1-19`, demarche d'investigation).

**Ce que je n'ai pas lu** : aucun avis de `theo-archi` pour s53 (il n'existe pas), aucune transcription de
`nadia-prof`, aucun rapport de CI. Le jugement pedagogique d'aucun paquet n'est porte ici.

---

## 1. Objectif gele

Celui de la story `S53SPE-RELECTURE`, repris sans modification : **chacun des onze points P1 a P11 a une
decision ecrite et appliquee** (corriger, accepter avec motif, ou retirer le paquet), **aucun paquet de la session
n'est branche au catalogue**, et les paquets corriges repassent la suite de `adapter-content` et de `bootstrap`.
Ajouts de cette decision, qui ne le modifient pas : les trois postes engages en section 5.

**Aucune ligne de matrice ne change de statut a s53.** `MATRICE-couverture.md` n'est pas editee. **Aucun Done
pedagogique n'est prononce.**

---

## 2. Perimetre : onze points, neuf stories

Decoupage de `sofia-sm` (`planning.md`), repris. Un point sans decision n'est pas edite (critere C1 du planning).

| Point | Story | Constat rendu par commande aujourd'hui | Decideur |
|---|---|---|---|
| P1 | `S53-REL-1` | `grep -c 'bonneReponse: "des beaux chapeaux"' ce1-accord-groupe-nominal.yaml` rend 1 | `nadia-prof` ; l'auteur propose |
| P2 | `S53-REL-2` | non automatisable (relecture item par item des items 10, 13, 14) | l'auteur (`lea-dev`) |
| P3 | `S53-REL-3` | l'en-tete de `cm1-emc-principes-republique.yaml` dit "la liberte" non proposee contre "la laicite" ; l'item 4 la porte en distracteur (ligne 83) | `nadia-prof` ; l'auteur propose |
| P4 | `S53-REL-3` | six indices, jugement non automatisable | `nadia-prof` |
| P5 | `S53-REL-4` | `grep -c "le chien est loin" cp-qlm-vocabulaire-de-position.yaml` rend 1 | `nadia-prof` ; l'auteur propose |
| P6 | `S53-REL-5` | `grep -c "Le mot kilo veut dire mille" ce1-masses-contenances.yaml` rend 2 (le backlog n'en decrit qu'un) | `nadia-prof` |
| P7 | `S53-REL-6` | l'enonce de l'item 4 de `cm1-hda-epoque-civilisation.yaml` contient "le pont du Gard" | l'auteur (`lea-dev`) |
| P8 | `S53-REL-7` | `texteSupport: "Range ton cahier. ..."`, distracteurs "ta trousse" et "ton cahier" | `nadia-prof` |
| P9 | `S53-REL-8` | `grep -c '^# DEGRADE'` rend 0 sur les trois paquets EMC | `camille-po` : **tranche en section 4** |
| P10 | `S53-REL-4` | `awk '/distracteurs: \["[^"]*"\]$/'` sur les deux paquets rend 2 lignes | `theo-archi` |
| P11 | `S53-REL-9` | 13 fichiers a espace ordinaire avant ":" dans `competence` | `nadia-prof` (section 3, Q4) |

**Instance** : une, celle de `nadia-prof`, en un seul passage pour les points qui lui reviennent. **Aucune
ouverture d'instance : `PAR-9` n'est pas declenchee** (section 7).

---

## 3. Reponses aux questions routees a `camille-po`

**Q1 - qui ouvre s53, et que deviennent les cinq postes du troisieme report ?** Cette decision ouvre s53 sur la
section 2. Les cinq postes sont tranches en section 5.

**Q2 - les "51 paquets".** **Le chiffre exact est 45 fichiers de paquet** (mesure 1 de la section 0 : 45 `A`, rien
d'autre, sur les six commits `34fdd167`, `8561498c`, `d27ba381`, `8de2927f`, `10bad629`, `7854d8d3`). Le chiffre 51 est,
selon le message qui m'a lance, une erreur de l'orchestrateur, reprise dans le sujet du commit `76a50e8b` et dans la section "Session hors sprint
s53 special" du backlog. Le commit est pousse et n'est pas reecrit : **le sujet de `76a50e8b` dit 51 a tort**, cette
decision est son erratum. Le backlog est corrige en place. Je n'ai pas verifie que les 45 fichiers correspondent un
a un aux 45 lignes de matrice citees par le meme sujet ; le planning de `sofia-sm` fait le meme constat (45 contre 51)
sans l'elucider, et la mesure ci-dessus le tranche pour les fichiers seulement. Aucun point P1 a P11 ne porte sur un
fichier hors de ces 45.

**Q4 - qui decide P11 (espace avant ":" dans `competence`).** **`nadia-prof` decide ; `relecteur-pedagogique`
fournit les faits** (liste des fichiers, nature de l'espace, commande). Relaye de l'utilisatrice, 2026-10-07 ;
mon motif : une espace ordinaire avant ":" est une faute de typographie francaise, et les textes que lit l'enfant sont
gouvernes par la porte pedagogique, pas par le registre de redaction (`CLAUDE.md`, `DEC-0002`). Reste un fait que ni
elle ni moi n'avons : **`competence` est-il lu par un enfant ?** `theo-archi` n'a trouve aucun gabarit de `adapter-web`
qui l'affiche et n'a pas cherche plus loin (`planning.md`). Si personne ne l'affiche, c'est un champ de metadonnee et
`nadia-prof` peut accepter l'espace ordinaire avec un motif d'une ligne. **Perimetre** : ma mesure rend **13
fichiers, dont les 4 du point et 9 plus anciens ; le backlog dit onze plus anciens.** Neuf mesures, onze ecrits, ecart
non explique ; l'espace insecable n'est pas la cause (0 fichier). Le decideur refait la mesure avec la commande de la
section 0 avant de decider, et le chiffre qu'il retient est ecrit dans sa decision.

**Q6 - P9.** Section 4.

**Q8 - P1, P3, P5 : qui l'emporte ?** **L'auteur propose, `nadia-prof` decide.** Relaye de l'utilisatrice,
2026-10-07 ; mon motif : la table de routage de ce depot donne la pedagogie a `nadia-prof`, et un point a deux
decideurs nommes sans ordre est un point sans decideur le jour ou ils different. Meme regle pour P1, P3, P5 et
elle corrige la lecture "auteur / `nadia-prof`" du backlog.

**Questions qui ne sont pas a moi** : Q3 (tarif du passage de `nadia-prof`, qui est le sien), Q5 (admissibilite d'un
distracteur unique : `theo-archi` pour le chargeur, `nadia-prof` pour la part pedagogique), Q7 (`PAR-14` sur P4).
Je ne les tranche pas.

---

## 4. P9 - la mention exigee d'avance vaut-elle une ligne `DEGRADE` ?

**Decision : non, les deux ne sont pas equivalentes, et aucun des trois paquets n'a besoin d'une ligne `DEGRADE`.**
`S53-REL-8` est soldee par cette decision, **aucun fichier de paquet ne change** (critere C2 du planning).

**Ce que les trois en-tetes disent.** `cm1-emc-atteinte-autrui.yaml` (`EMC-CM1-02`), `cm1-emc-respect-differences.yaml`
(`EMC-CM1-03`) et `cm1-emc-principes-republique.yaml` (`EMC-CM1-06`) portent la meme phrase, en commentaire :
*"connaissances civiques et reconnaissance de situations seules ; aucun comportement, aucun engagement et aucune
participation a une decision collective ne sont servis par ce produit"* (dans l'en-tete de chacun). Aucun des trois ne porte de ligne `# DEGRADE` (`grep -c '^# DEGRADE'` rend 0 pour chacun).

**Ce que chaque mecanisme dit, et a quelle echelle.**

- **Une ligne `DEGRADE`** (premiere ligne du fichier, par exemple `cm1-anglais-ecrire-mots.yaml`,
  `ce1-masses-contenances.yaml`, `cm1-lire-et-restituer.yaml`) dit qu'**un verbe ou un element du libelle de la ligne
  n'est pas servi par ce paquet** : "mesurer" sans balance, "tracer" sans trace, "ecrire" sans saisie. Son objet est
  **le libelle d'une ligne**.
- **La mention d'EMC CM1** (`competences-cm1.md`, section "Mention exigee d'avance si une ligne bouge") dit ce que
  **la matiere entiere** ne fait pas : *"onze lignes vertes sur douze diraient sinon que le produit fait l'education
  morale et civique d'un enfant, ce qui serait faux et le resterait quelle que soit la qualite des paquets"*. Son objet
  est **la lecture de la matrice**, et elle s'ecrit sur la ligne a sa fermeture.

**Le critere qui decide, et qui est de la lecture de libelles, pas de pedagogie.** Une ligne `DEGRADE` est due quand un
verbe du libelle n'est pas servi. Les trois libelles (`competence` des paquets, copie conforme du referentiel) :

- `EMC-CM1-02` : *"Reconnaitre ce qui porte atteinte a la personne d'autrui (...) et savoir a qui s'adresser"* ;
- `EMC-CM1-03` : *"Identifier ce qu'interdit le respect d'autrui dans ses differences (...)"* ;
- `EMC-CM1-06` : *"Comprendre le sens des principes de la Republique (...)"*.

Les verbes sont reconnaitre, identifier, comprendre et savoir a qui s'adresser : **des verbes de connaissance, que
le choix de reponse sert**. Aucun verbe de comportement ni d'engagement n'y figure. La ligne ou le libelle demande
l'engagement, `EMC-CM1-12` (*"S'engager dans un projet collectif"*), est `non couvrable en l'etat` : ce depot ne la
sert pas, il ne la sert pas a demi.

**Precedent.** Les six paquets d'EMC CM1 deja `couvert-valide` (`cm1-emc-emotion-ressentir-agir`,
`-institutions`, `-regle-loi`, `-regles-vie-collective`, `-symboles`, `-vote-election`) portent la meme phrase en
commentaire et **aucun ne porte de ligne `DEGRADE`** (`grep -l DEGRADE contenu/*emc*` ne rend rien). La phrase est aussi
dans la cellule de matrice de plusieurs d'entre eux (`EMC-CM1-07`, `EMC-CM1-11`). La ligne `EMC-CP-05` est le cas
inverse et il confirme le critere : son libelle dit *employer*, le produit sert la connaissance seule, `nadia-prof` a
exige une **mention de couverture partielle de ligne** (arbitrage de s31, ligne 14 du tableau des refus).

**Ce que cette decision ne decide pas.** Que le paquet **serve effectivement** le verbe du libelle est un jugement de
contenu, a `nadia-prof`, a l'audit futur. Si elle constate qu'un verbe n'est pas servi dans l'un des trois paquets,
elle exige la ligne `DEGRADE` et une story corrective l'ecrit : cette decision ne l'en empeche pas. Elle dit
seulement que **l'existence de la mention d'avance n'est pas, par elle-meme, un `DEGRADE`**, et qu'on n'ajoute
pas trois lignes a des paquets qui n'en ont pas la raison. Le marqueur est un commentaire qu'aucun test ne lit
(`grep -rn DEGRADE adapters/adapter-content/src/test/java` ne rend rien) : ajouter trois lignes fausses aurait ete
une affirmation sans garde-fou. **La mention de la matrice est due a la fermeture de chacune des trois lignes**, par
`nadia-prof`, mot pour mot ; elle n'est pas due a s53.

---

## 5. Les postes au troisieme report (`DEC-0111` A.14)

A.14 : *"les cinq postes marques troisieme ne se reportent plus sans decision"*. Chacun est engage ou retire ici,
avec son motif. **Le cadre de s53 exclut deux issues** : tout poste qui ferme une ligne ou qui remet un jeu au
catalogue (aucune ligne ne change de statut, aucun branchement).

| Poste | Reports comptes | Issue | Proprietaire |
|---|---|---|---|
| `S52-SCI-TERRE-CM1-REPRISE-APRES-RETRAIT` (1,0) et `S52-SCI-ACTIVITE-HUMAINE-CM1-REPRISE-APRES-RETRAIT` (0,5) | s51, s52, s53 : trois | **Retires comme postes de sprint** ; competences conservees | voir 5.1 |
| `CRP-CM1-R1` a `R3` (`S51-SCI-CORPS-CM1-RESERVES`, 0,5) | s51, s52, s53 : trois | **Engage a s53** | voir 5.2 |
| `MAT-CE1-02`, moitie designation | s51, s52, s53 : trois | **Retire comme poste de sprint** ; ligne laissee `en cours` | voir 5.3 |
| Passe transverse `PAR-4` (0,5) | aucun sprint parallele depuis s41 ne l'a engagee (`nadia-prof`, arbitrage de s51 6.1) ; **troisieme fois** portee a une `DEC` d'ouverture sans engagement (s51, s52, s53) | **Engagee a s53** | voir 5.4 |
| `S51-OUTIL-JOURNAL-TOKENS` | s51, s52, s53 : trois | **Engagee a s53** | voir 5.5 |

### 5.1 Reprises de sciences CM1 : retirees comme postes, pas comme competences

**Motifs.** (1) **Le cadre de s53** : une reprise s'acheve par la remise au catalogue apres un Done, c'est-a-dire un
branchement et un changement de statut de `SCI-CM1-15` et `SCI-CM1-18`, que s53 exclut. (2) **Aucune instance de
sciences CM1 ne peut atteindre la barre de `nadia-prof`.** Les lignes `non couvert` de sciences CM1 sont deux
(mesure 4 de la section 0), les deux reprises, pour 1,5 ; avec `CRP` (0,5) cela fait 2,0, contre les 4,0 qu'elle a
exiges d'une instance (condition (i), arbitrage de s52 section 6, cite par `DEC-0111` section 6). `PAR-2` interdit de
porter ce travail par une instance d'une autre matiere. **Le vivier de sciences CM1 ne peut donc pas former une
instance seul, quel que soit le sprint** ; le reporter une quatrieme fois n'aurait aucune issue.

**Ce que cela ne fait pas.** Les lignes restent `non couvert`, la matrice n'est pas editee. Les deux paquets
refuses restent retires du catalogue (`DEC-0111` section 3) et leurs fichiers, tests et fiches restent. Le texte
des deux stories reste au backlog, marque *retiree des postes de sprint par `DEC-0112`*.

**Ce qui les rouvre** : une `DEC` d'ouverture qui nomme le porteur, soit une instance de sciences CM1 qui atteint la
barre, soit une reponse ecrite de `nadia-prof` qui amende la condition (i) ou `PAR-2` pour ces deux lignes.
**Question pour l'utilisatrice** (route par `sofia-sm`) : veut-elle que ces deux lignes de sciences soient
couvertes, et accepte-t-elle de ce fait qu'un sprint porte une instance sous la barre de `nadia-prof` ?

### 5.2 `CRP-CM1-R1` a `R3` : engagee a s53, parce qu'un enfant lit aujourd'hui une phrase fausse

**Motif.** C'est la seule des cinq a viser un jeu **servi** : `grep -c "corps-cm1"` sur `catalogue.yaml` rend 1.
`FICHE-bandit-manchot-corps-cm1.md` (section 8, `CRP-CM1-R1`) : l'indice de l'item 3 *"Reparer et grandir demande du
temps pendant lequel le corps ne fait rien d'autre"* est **faux** (le coeur bat, la digestion continue) et la
section 7 dit que `nadia-prof` a hesite a refuser. C'est la classe de defaut de `DEC-0009` : un contenu qu'on sait
fautif et que l'enfant peut encore jouer. Un indice faux n'est pas un motif de refus pour elle (section 7 de la
fiche) ; ce n'est pas non plus un motif pour moi de le laisser servi un quatrieme sprint.

**Perimetre.** La story `S51-SCI-CORPS-CM1-RESERVES` telle qu'ecrite, **sans modification** : cinq items sur douze
(3, 5, 6, 7, 11), tarif 0,5 (`PAR-10`). **5 sur 12 est 41,7 %, sous la moitie** : `PAR-14` ne s'applique pas, et
c'est un fait que `nadia-prof` confirme ou corrige. Elle ne change aucun statut de matrice (`SCI-CM1-10` est
`couvert-valide`) et ne touche pas le catalogue.

**Proprietaire** : paquet ecrit par `marc-dev` (`[MARC] s51: paquet cm1-sciences-corps`) ; affectation a `sofia-sm`,
qui connait la charge des deux developpeurs. Rejeu par `nadia-prof`, meme instance : une seule instance est ouverte, donc `PAR-2` (une matiere n'est pas partagee entre deux instances) n'est pas en jeu.

### 5.3 `MAT-CE1-02`, moitie designation : retiree comme poste, ligne laissee `en cours`

**Motifs.** (1) Elle depend de **A3 d'`ADR-0080`**, qui est a `theo-archi` et que l'arbitrage de s52 (section 8)
dit non due tant que cette moitie et `MAT-CE1-14` restent dehors ; **aucun avis de faisabilite de s53 ne la chiffre**.
(2) Elle appartient au second paquet de designation de la mathematique CE1, pour lequel `nadia-prof` demande une
instance qui ne porte aucun autre terrain neuf (`PAR-13`, arbitrage de s51 section 6.2) : ce n'est pas un poste
qu'un sprint de relecture peut porter. (3) Elle ne sert qu'a **un** jeu manquant sur une ligne dont la moitie
textuelle est `couvert-valide` (mention d'`ORD-CE1-5` dans la matrice) : la matrice dit deja honnetement l'etat.

**Ce que cela ne fait pas.** La ligne reste `en cours`, avec sa mention. Je ne la declare pas `non couvrable en
l'etat` : c'est un jugement de faisabilite de `theo-archi` et de `nadia-prof`, pas le mien. **Ce qui la rouvre** : la
`DEC` d'ouverture du sprint qui engage A3 ; elle engage alors la moitie designation et `MAT-CE1-14` ensemble.

### 5.4 Passe transverse `PAR-4` : engagee a s53

**Motif.** `PAR-4` est due, 0,5, dans la borne (`nadia-prof`, arbitrage de s51 section 6.1, texte de `PAR-4` :
arbitrage de s29 section 13.3). Les passes ecrites sont `PASSE-TRANSVERSE-s29-...`, `PASSE-TRANSVERSE-s30-...` (2026-09-06) et
`PASSE-TRANSVERSE-s35-ecran-de-fin-de-partie.md` (une seule famille de proprietes, d'apres son titre ; je ne l'ai pas
relue). Je n'en trouve aucune depuis (`find squad -iname 'PASSE-TRANSVERSE*'`), alors que la matrice compte 132 lignes
`couvert-valide` au 2026-10-06 (`DEC-0111` A.9) ; je n'ai pas la valeur de ce compte a s30. Aucune de ces passes n'a regarde, depuis,
le depot servi entier. La portee **D** (arbitrage de s29, section 13.2)
couvre l'unicite de sens d'un identifiant de condition, la coherence des conditions de `nadia-prof` entre elles et
**l'homogeneite de registre entre jeux qu'un meme enfant enchaine** : trois proprietes qu'aucun audit de lot ne voit,
et qu'un enfant rencontre. Retirer `PAR-4` obligerait `nadia-prof` a amender sa propre regle, que je ne lui demande
pas ; l'engager coute 0,5 sur une instance dont la charge connue, passage de decision a part, est de 0,5 (5.2).

**Story** : `S53-PASSE-TRANSVERSE` (backlog, section "Session hors sprint s53 special"). Proprietaire : `nadia-prof`,
meme instance, qui porte l'accumulation (`PAR-7`). **Ce que ce poste ne fait pas** : aucun Done, aucun statut de
matrice, aucun branchement ; ce qu'elle trouve est route par elle en stories, non payees a s53.

### 5.5 `S51-OUTIL-JOURNAL-TOKENS` : engagee a s53

**Motif.** Troisieme report d'une story **sans prix** : le backlog la dit *non chiffree*, son prix est a `theo-archi`. L'outil `outils/rapport-tokens.py` regenere un journal qui remplace des chiffres mesures par *"Aucune
consommation mesuree"* ; le depot a donc, depuis s51, un journal commite qu'on ne peut pas regenerer sans perte
(`DEC-0110` section 17.2). Le defaut n'est pas pour l'enfant ; il est pour la squad et pour l'utilisatrice qui lit
le cumul de tokens. Un poste non chiffre se reporte indefiniment : **je l'engage donc en exigeant le chiffre** dans
l'avis de faisabilite que `theo-archi` doit de toute facon rendre a s53 (`planning.md`, section "Avis de
faisabilite").

**Ce que je ne decide pas.** La forme du correctif, son developpeur et son prix : `theo-archi`, `sofia-sm`. Le
planning donne `sofia-sm` (casquette developpeuse) sans story de developpement a s53 : c'est une proposition que je
lui laisse. **Si l'avis rend un prix qui depasse ce que s53 peut porter, je ne le descends pas : je le dis a
l'utilisatrice**, et le poste n'est alors ni retire en silence ni reporte une quatrieme fois.

---

## 6. Autres reports, comptes

| Poste | Reports comptes | Issue a s53 |
|---|---|---|
| `SYC-CE1-R1` (0,5), `MUL-CE1-R1` (0,5) et item 16 de `FRA-CE1-25` (observation de `nadia-prof`, a re-auditer avec `SYC-CE1-R1`) | non engages a s52 (`DEC-0111` A.13) ; **s53 : second report** | Non engages. Rien n'est faux pour l'enfant : un partage de genre sur deux items, une phrase qui gene sans fausser l'exercice, un enonce qui peut faire douter. **Un troisieme report n'est plus permis** : la `DEC` de s54 les engage ou les retire |
| `ART-CP-10` | s50, s51, s52, s53 : **quatrieme sprint hors perimetre** | **Aucune des deux issues de `DEC-0111` section 9 n'est ecrite par moi** : le budget n'entre pas dans le cadre de s53 (une ligne fermerait), et declarer la ligne *non couvrable en l'etat* est un jugement de `nadia-prof`. Le statut d'`ADR-0104` (`Propose`) est a `theo-archi` (action A8 de la retro de s52). Je ne presente pas cela comme un choix : c'est la direction que personne n'a choisie. **Question pour l'utilisatrice** : alloue-t-elle un sprint a `ART-CP-10` ou demande-t-elle a `nadia-prof` de la declarer ? |
| `MAT-CE1-14` | jamais engagee | inchange (liee a A3, voir 5.3) |
| Lignes de francais CE1 atteignables (15), vivier | aucune story | inchange, non un report |

---

## 7. Capacite, `PAR-9`, tarif

- **Une instance de `nadia-prof`**, plafond de 5,0 (`PAR-8`). **`PAR-9` n'est pas declenchee** : elle vise une `DEC`
  qui ouvre deux instances, celle-ci n'en ouvre aucune.
- **Charge** : le passage de decision (P1, P3, P4, P5, P6, P8) est propose a 0,0 par `sofia-sm` et attend le prix
  de `nadia-prof` (Q3), que je ne fixe pas. S'y ajoutent **0,5 (`CRP-CM1-R1` a `R3`, `PAR-10`) et 0,5 (`PAR-4`)**,
  soit **1,0 sur 5,0 hors le passage de decision**, marge de rejeu comprise : 4,0 restent. Ce chiffre est le mien et
  son prix est a elle.
- **Developpement** : le depot n'a pas de tarif. Tailles ajoutees par cette decision : cinq items d'un paquet (5.2),
  un outil et ses tests (5.5), aucun autre.

---

## 8. Branchement des paquets au catalogue : non engage

La proposition **P-BR** du planning (brancher les 45 paquets, ordre de grandeur 45,0 au tarif de 1,0 par paquet, au
moins six sprints a 8,0 par sprint, **borne basse non verifiee**) **n'est pas engagee** et **n'est pas decidee ici**.
Elle est une proposition a arbitrer par l'utilisatrice. Le motif de ne pas brancher a s53 est celui du planning :
un paquet corrige apres son branchement se paie au tarif de rejeu (`PAR-10`, ou lot si plus de la moitie des items
change, `PAR-14`), alors que corrige avant il se paie au premier passage. **Question pour l'utilisatrice** : brancher,
et dans quel ordre de matieres ? Je n'ecris pas cet ordre sans son arbitrage.

---

## 9. Routage (changement de code et de fichiers)

**Une decision qui implique un changement n'est pas prise sans proprietaire** : les changements ci-dessous sont routes
dans la meme passe que cette decision, via `sofia-sm`.

| A qui | Quoi |
|---|---|
| `sofia-sm` | Fusionner cette `DEC` avant d'ouvrir les worktrees des developpeurs ; reintegrer au planning les trois postes engages (5.2, 5.4, 5.5) et le solde de `S53-REL-8` (aucun fichier a editer, `marc-dev` n'a pas de tache pour cette story) ; affecter `CRP` et l'outil de journal ; porter a l'utilisatrice les trois questions de 5.1, 6 (`ART-CP-10`) et 8 |
| `nadia-prof` | Passage de decision (P1, P3, P4, P5, P6, P8) ; decision de P11 sur les faits du relecteur ; passe `PAR-4` (`S53-PASSE-TRANSVERSE`) ; rejeu de `CRP-CM1-R1` a `R3` ; prix des postes ajoutes ; Q3 et Q7 |
| `theo-archi` | Avis de faisabilite des stories, **avec le prix de l'outil de journal** (5.5) ; decision de P10 ; statut d'`ADR-0104` |
| `marc-dev`, `lea-dev` | Selon l'affectation de `sofia-sm` ; le corps de `S53-REL-8` n'est plus a faire |
| `relecteur-pedagogique` | Faits de P11 avec la commande de la section 0 et la reconciliation 9 / 11 |
| `yanis-qa` | Criteres testables de `S53-PASSE-TRANSVERSE`, de `S51-SCI-CORPS-CM1-RESERVES` et de `S51-OUTIL-JOURNAL-TOKENS` a s53 |
| `camille-po` | Backlog et `DEC-index.md` (ce commit) ; la matrice n'est pas touchee |

---

## 10. Ce que cette decision ne fait pas

- Elle ne prononce aucun Done pedagogique et ne juge la pedagogie d'aucun paquet.
- Elle n'edite ni `MATRICE-couverture.md`, ni une fiche de conformite, ni `REGISTRE-identifiants.md`, ni aucun
  fichier de paquet, ni `catalogue.yaml`.
- Elle n'ecrit aucun code et ne tranche aucune question technique (P10, A3, forme de l'outil de journal).
- Elle n'amende ni `PAR-2`, ni `PAR-4`, ni `PAR-9`, ni `PAR-14`.
- Elle ne reecrit pas le commit `76a50e8b` (pousse) ; l'erratum de son sujet est en section 3, Q2.

---

## Decisions prises

1. s53 s'ouvre sur les onze points P1 a P11 en neuf stories, **une instance de `nadia-prof`**, aucune ouverture
   d'instance (`PAR-9` non declenchee), **aucun branchement**, **aucune ligne de matrice ne change de statut**.
2. **Q2 : 45 fichiers de paquet**, pas 51 ; erratum du sujet de `76a50e8b`, backlog corrige.
3. **Q4 : `nadia-prof` decide P11**, le relecteur fournit les faits ; neuf fichiers plus anciens mesures contre onze
   ecrits, ecart non explique, a refaire par le decideur.
4. **Q8 : sur P1, P3, P5, l'auteur propose et `nadia-prof` decide.**
5. **P9 : la mention exigee d'avance n'est pas equivalente a une ligne `DEGRADE`**, et aucun des trois paquets EMC n'a
   de verbe de libelle non servi ; `S53-REL-8` est soldee, aucun YAML ne change.
6. **Reprises de sciences CM1 : retirees comme postes de sprint**, lignes `SCI-CM1-15` et `SCI-CM1-18` conservees
   `non couvert` ; rouverture par une `DEC` qui nomme le porteur.
7. **`CRP-CM1-R1` a `R3` : engagee a s53** (indice faux sur un jeu servi), 0,5, meme instance de `nadia-prof`.
8. **`MAT-CE1-02` moitie designation : retiree comme poste de sprint**, ligne laissee `en cours`, rouverte avec A3.
9. **`PAR-4` : engagee a s53** (`S53-PASSE-TRANSVERSE`, 0,5) ; **journal de tokens : engagee a s53**, prix a rendre
   par `theo-archi` dans son avis.
10. **Seconds reports comptes** (`SYC-CE1-R1`, `MUL-CE1-R1`, item 16 de `FRA-CE1-25`) et **quatrieme sprint hors
    perimetre d'`ART-CP-10`**, avec la question posee a l'utilisatrice.
