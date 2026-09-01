# Review de s26 - la voix et la dette

> Ce fichier est ouvert par `nadia-prof` pour y deposer sa **conclusion pedagogique**, comme son
> prompt l'exige. **Le reste de la review appartient a `sofia-sm`** : demonstration, Done technique,
> etat des stories, suites. Rien de ce qui suit ne prejuge de ce qu'elle y ajoutera.

---

## Porte pedagogique - `nadia-prof`, 2026-09-01

**Increment juge** : `round-two` a `bc59642`. **J'ai joue**, jar reconstruit et lance par moi, sur le
JDK 21.0.10 rapporte par la machine virtuelle. **Cet audit n'est pas degrade.**

### Verdict : **REFUSE sur les cinq paquets. Aucun Done pedagogique n'est prononce.**

| Paquet | Items | Verdict | Motif |
|---|---|---|---|
| `bandit-manchot-position-syllabe-cp` | 18 | **REFUSE** | `VOI-12` seule - **sinon VALIDE sans reserve** |
| `bandit-manchot-son-dans-mot-cp` | 12 | **REFUSE** | `VOI-12` seule - **sinon VALIDE sans reserve** |
| `bandit-manchot-lettre-du-son-cp` | 18 | **REFUSE** | `VOI-12` **plus un second motif qui ne se leve pas avec elle** |
| `bandit-manchot-digraphe-du-son-cp` | 15 | **REFUSE** | `VOI-12` seule - **sinon VALIDE sans reserve** |
| `saisie-mot-dicte-cp` | 12 | **REFUSE** | `VOI-12` plus **une reserve nommee** qui ne bloque pas |

**Aucune ligne de `squad/programme/MATRICE-couverture.md` ne bouge. 31 / 41 reste 31 / 41.**

### Le motif bloquant

**Il manque la cinquieme phrase d'adulte**, celle que ma section 14.3 exige sur les cinq paquets et
que j'ai ecrite **avant que ce contenu n'existe** : *"Ne montrez pas cette ligne a l'enfant."* ou une
phrase qui dise la meme chose. Cherchee sur l'accueil, la page de niveau, l'ecran de jeu et les cinq
`consigneAdulte` : **elle n'existe nulle part.**

**Mesure sur les cinq, sans exception : un enfant qui lit la ligne de l'adulte gagne sans la
competence.** Il voit `pin` a la fin de `sapin`, `an` dans `manteau`, le `p` de `pomme`, le `ch` de
`chat` - et sur la dictee, **il recopie**. Un produit qui confie cette condition a l'adulte sans la
lui dire ne l'a pas confiee.

**Ce refus se leve avec une phrase par paquet.** Aucun contenu a reecrire, aucune mecanique a
toucher, **aucun audit a refaire** : tout ce que j'ai verifie est ecrit dans les cinq fiches
precisement pour ne pas avoir a l'etre deux fois.

### Ce que je dis a la decharge de l'increment, parce que c'est vrai

- **Les cinq points de 13.1 sont TENUS** sur les quatre phrases livrees. **Le point 3 n'echoue pas** :
  ce qui manque est la phrase supplementaire.
- **Mes cinq signaux du matin sont corriges**, et je les ai reverifies **a voix haute** : les cinq
  remplacants sont justes et aucun n'introduit le defaut qu'il repare.
- **`VOI-14` tient sur l'objet**, la branche `saisie` a bien disparu, `SAI-1`, `SAI-3` et `SAI-4` sont
  verifiees en jeu, et l'enfant bloque peut toujours avancer.
- **`cp-digraphe-du-son` est le plus propre des cinq** : 15 items sur 15 justes, 33,3 % par digraphe,
  aucune reponse ambigue, aucune signature.
- **Le chemin que j'avais route en 12.4 n'existe pas** : la revelation est dans la zone de l'enfant,
  pas dans celle de l'adulte. Je le ferme.

### Ce que je corrige contre moi

**`VOI-13` etait trop large.** Sa lettre interdisait le mot *"sur l'ecran de revelation"* ; or la
dictee ne l'y montre **qu'au moment ou l'item cesse d'etre jouable**, et une dictee qui ne montre
jamais l'orthographe apres l'echec ne corrige rien. **J'aurais refuse a tort sur ma propre lettre.**
Corrigee et datee ; l'ancienne reste lisible.

*Troisieme de mes clauses fausse en extension aujourd'hui, apres `VOI-2` et `VOI-10`.*

### Ce que le vert ne prouvait pas

**1 084 tests verts, et deux defauts qu'aucun n'attrape** : les 5 items sur 18 de `cp-lettre-du-son`
ou un enfant qui sait ses lettres est corrige d'un savoir juste, et `gomme` / `bulle` sur la dictee,
dont l'ecriture ne se deduit pas du son.

**Je reste seule garde de `VOI-3`, `VOI-5`, `VOI-8`, de la moitie non calculable de `VOI-13`, du
point 2 et de la moitie du point 3 de `VOI-12`.** Mes tables de "TENU" disent ce que j'ai verifie en
jouant - **pas ce qu'un test garde.**

### Arbitrages rendus dans le meme geste

- **`.etiquette-adulte` a 1,25rem** : confirme sans reserve. Et **une etiquette plus petite n'aurait
  rien achete sur `VOI-13`** - ce que l'enfant lit par-dessus l'epaule est le stimulus, pas
  l'etiquette.
- **Le texte *"Adulte, a voix haute :"*** : valide. Il tient mon point 2, et il est bien une
  etiquette et non une phrase.
- **La suite vide contigue partout** : **cote strict confirme**. Une regle qui ne peut produire qu'un
  refus ne peut pas produire un faux Done. **Et aucun paquet a voix en mathematiques ne passera ma
  porte tant que la comparaison sur la forme ecrite en lettres n'existe pas** - dit d'avance.
- **La lecture prudente sur la graphie affichee** : confirmee. C'est la condition d'existence de la
  ligne, pas une precaution.

### Ma borne

**75 items pour un plafond de 115.** `cp-le-bon-son.yaml` intact - `git diff` vide sur ce fichier -,
donc la clause 3 de ma barre du 2026-08-10 est tenue par constat et je n'ai pas rejoue ses 15 items,
exactement comme je l'avais ecrit d'avance. **Ma borne n'a pas ete le facteur limitant. Je n'ai rien
echantillonne et je n'ai pas eu a decliner.**

### Detail

`squad/conformite/ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md`, section 17, et le bloc de
validation des cinq `squad/conformite/FICHE-*.md`.

---

## Porte pedagogique - `nadia-prof`, 2026-09-01, **second passage sur le correctif**

**Ma conclusion precedente n'est pas effacee.** Le refus reste lisible ci-dessus avec son motif : un
refus efface n'a aucune valeur de preuve.

**Increment juge** : `round-two` a `a5147d8`. **Jar reconstruit et relance par moi**, JDK 21.0.10
rapporte par la machine virtuelle. **J'ai rejoue.**

### Verdict : **REFUS LEVE. VALIDE sur les cinq. Done pedagogique prononce.**

| Paquet | Verdict | Ce qui a ete leve |
|---|---|---|
| `bandit-manchot-position-syllabe-cp` | **VALIDE** | `VOI-12` |
| `bandit-manchot-son-dans-mot-cp` | **VALIDE** | `VOI-12` |
| `bandit-manchot-lettre-du-son-cp` | **VALIDE** | `VOI-12` **et son second motif** |
| `bandit-manchot-digraphe-du-son-cp` | **VALIDE** | `VOI-12` |
| `saisie-mot-dicte-cp` | **VALIDE, sans reserve** | `VOI-12` **et ma reserve non bloquante** |

**Je leve parce que c'est verifie.** Mesure : la cinquieme phrase presente sur **15 etats d'ecran**
(cinq jeux x item, erreur, revelation) ; `VOI-13` sur **188 ecrans jouables, 0 ecart** ; une partie
complete de chacun des cinq jusqu'a l'ecran de fin.

**Et le fait que je dis en premier** : le texte livre est **celui que j'avais moi-meme rendu candidat
et mesure TENU** en section 13.2. Je ne peux pas trouver insuffisant ce que j'ai propose - ce serait
deplacer mes poteaux, et je m'en suis interdit la possibilite par ecrit avant l'objet.

- ***"le tout premier son"*** : **18 items sur 18**, dits a voix haute. Aucun mot ne commence par une
  lettre muette, un groupe consonantique ni un digraphe : **aucun item ne devient ambigu**, et c'est
  present **avant toute reponse**. La formulation est de `lea-dev` et **elle est meilleure que ce que
  j'aurais ecrit** - *"tout"* ferme la porte a l'enfant qui comprendrait *"le premier que je
  remarque"*.
- **`brosse` / `trousse`** : *"brose"* dirait `[broz]`, *"trouse"* dirait `[trouz]`. **Le doublement
  se deduit du son**, donc un enfant qui se trompe **peut trouver son erreur en redisant le mot** -
  ce que `gome` ne permettait jamais. **Le remede va la ou etait le defaut.** Joue dans les deux
  sens.

### Ce que je dois dire contre le confort du chiffre : **trois lignes se ferment, pas quatre**

**Ma barre du 2026-08-10, clause 1** : *"les **voyelles simples** et les consonnes simples sont
couvertes."* **`cp-lettre-du-son` sert six consonnes et aucune voyelle** - verifie sur tout le contenu
servi, aucun paquet de CP n'a une voyelle simple pour bonne reponse.

**Les deux moities sont VALIDE ; la ligne n'est pas couverte.** Le Done porte sur l'increment, la
matrice porte sur la couverture d'une competence. **Me taire ferait passer la ligne a
`couvert-valide` contre une barre qui n'est pas atteinte**, et ma barre existe pour empecher
exactement cela.

| Ligne | Etat |
|---|---|
| Localiser une syllabe dans un mot | **`couvert-valide`** |
| Identifier un son (phoneme) dans un mot | **`couvert-valide`** |
| Ecrire de maniere autonome un mot simple | **`couvert-valide`** |
| Associer les graphemes aux phonemes | **reste `en cours`** - il manque les voyelles simples |

**31 / 41 devient 34 / 41 (82,9 pour cent), et non 35 / 41.** Le compte final appartient a
`camille-po`. **Un paquet de voyelles simples ferme la derniere ligne** : ses clauses 2, 3 et 4 sont
deja remplies, et je le dis d'avance.

**Et ce qui est vrai aussi** : le domaine *"Langage oral et conscience phonologique"* passe de **1 sur
3 a 3 sur 3** et se ferme entierement.

### Quatrieme correction contre moi dans la journee

*"a l'enfant"* produit un jeton `l` par elision, et `l` est une proposition de `cp-lettre-du-son` :
**3 tirages sur 8** declares fuites par mon propre point 5. **Faux positif** - un article elide n'est
pas la designation d'une lettre, exactement la famille que j'avais denoncee sur *"chat"* et *"ch"*.
Corrige et date.

**`VOI-2`, `VOI-10`, `VOI-13`, l'operateur du point 5 : quatre clauses a moi fausses en extension en
un jour, toutes trouvees par la mesure, aucune par la relecture.** Je pese mes motifs et je lache mes
operateurs ; c'est la lecon et je la rends telle quelle.

**Aucun test n'a rougi** : le point 5 de `VOI-12` n'est encode nulle part. **Que nul ne le croie
couvert.**

### Ce que le vert ne prouve toujours pas

**1 090 tests verts, et la liste des conditions que personne ne tient reste a quatre** : `VOI-3`,
`VOI-5`, `VOI-8`, et la moitie non calculable de `VOI-13`.

> **La cinquieme phrase ne fait pas respecter `VOI-13` : elle la dit.** C'etait la contrepartie que je
> demandais, et il ne faut pas la lire comme une garantie. **Un adulte qui laisse l'enfant lire la
> ligne fait echouer les cinq jeux, et rien ne le saura.**

Plus le point 2, la moitie du point 3 et desormais le point 5 de `VOI-12`.

### Detail

`squad/conformite/ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md`, section 18, et le **second
verdict** dans le bloc de validation des cinq `squad/conformite/FICHE-*.md`.

---

## Addendum du 2026-09-01 (sofia-sm) - cloture de la ceremonie : horodatage, rapport qualite, confirmation, obstacles, decisions

- **Auteure de cet addendum** : `sofia-sm`. Rien du texte precedent (les deux passages de
  `nadia-prof`) n'est modifie ni retire.
- **Horodatage de debut** : 2026-09-01 14:32, repris de `planning.md`.
- **Horodatage de fin** : 2026-09-01 21:49
- **Duree effective** : environ 7h17.
- **Increment livre, en une phrase** : les quatre lignes du domaine *Langage oral et conscience
  phonologique* sont servies par cinq paquets a voix (un adulte present lit le texte que l'enfant
  n'a pas a lire), et la dette du JavaScript porteur non file (`ADR-0008`) est fermee.

### Rapport qualite - `yanis-qa`

Repris depuis `squad/sprints/s26-la-voix-et-la-dette/rapport-qualite.md`, extrait de `s26-yanis`
(`0fbf7b0`) sans fusionner cette branche - voir la note en tete de ce fichier pour le motif.

- **Commande** : `bash outils/mvn-jdk21.sh -B clean verify`, sur `s26-theo-reco` a `87963a2`.
- **Resultat a cette date** : vert, **1079 tests**, 0 echec, JDK 21.0.10 confirme par la JVM des
  tests.
- **Couvert** : `VOI-14` (ses deux points), l'appariement `texteAVoixHaute`/`consigneAdulte` cote
  contenu YAML, l'amendement d'identite d'`ADR-0032`.
- **Non couvert a cette date, trouve par mutation sur materiau reel** : la clause 4 de traversee
  d'`ADR-0075` ne tenait qu'au niveau du contenu, pas du gabarit ; la clause 3 d'`ADR-0074` ne
  normalisait pas les tabulations et retours-ligne comme un navigateur. **Les deux trous sont
  fermes depuis** par `theo-archi` (`ADR-0074` amende) et `marc-dev` (type `BlocAdulte`), fusionnes
  sur `round-two` avant ce passage de cloture (voir `planning.md`, addendum "les deux trous
  fermes").
- **Tests instables** : aucun.

### Confirmation ArchUnit - `sofia-sm`

Aucun fichier `confirmation-archunit.md` dedie n'a ete produit par `theo-archi` pour ce sprint :
cette section est donc la mienne, sur ma propre execution, et non une reprise de la sienne.

- **Commande** : `bash outils/mvn-jdk21.sh -B clean verify`, sur `round-two` a `05dcc60`.
- **Resultat** : BUILD SUCCESS, **1090 tests** (82 domain + 13 application + 382 adapter-web + 457
  adapter-content + 150 bootstrap unitaires + 6 bootstrap integration), 0 echec, 0 erreur.
- **Regles ArchUnit (paquet `com.recreo.archi`) ajoutees ce sprint** : **aucune**. Verifie par
  liste du repertoire `bootstrap/src/test/java/com/recreo/archi/` : 18 fichiers, la meme liste
  qu'a l'ouverture du sprint. Le sprint a modifie des tests de garde-fou de contenu et de rendu
  (`FrontieresDuJavaScriptTest`, `LeBlocAdulteEstUnSeulObjetContiguATousLesEtatsTest`,
  `AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest`,
  `LeTexteAVoixHauteEtLaConsigneAdulteSeDeclarentEnsembleTest`,
  `LaConsigneAdulteInterditDeMontrerLaLigneALEnfantTest`), pas la suite ArchUnit du paquet `archi`.
- **`ToutesLesReferencesDeDecisionResolventTest`** : vert a chaque fusion de ce sprint, verifie
  individuellement a trois reprises - c'est le garde-fou qui a trouve les deux premiers des trois
  incidents de "fichier reste sur une branche" de ce sprint.

### Relecture - `relecteur-pedagogique`

- **Corrections appliquees** (`s26-relecteur`, `61e13ca`) : la moitie manquante de la correction
  d'un des cinq `PaquetCpXxxTest` ; la contradiction entre le champ Statut des quatre stories et
  leur preambule ; ma consigne fautive citant `VOI-7` au lieu de `VOI-13`.
- **Points a trancher** : aucun restant a sa charge a la cloture.

### Obstacles du sprint

| Description | Categorie | Agent route | Decision liee | Statut |
|---|---|---|---|---|
| `round-two` rouge : `CLAUDE.md` citait `DEC-0068` avant sa fusion | technique, gouvernance | `sofia-sm` (auteur : l'orchestrateur) | `DEC-0068` | clos |
| `P(bonne / presente)` non garde par `adapter-content` | qualite, technique | `yanis-qa`, `theo-archi` | - | route, hors perimetre de s26 |
| Doute d'`ines-design` sur la consigne de `bandit-manchot-son-dans-mot-cp` | pedagogie | `nadia-prof` | - | la fiche le marque encore "point ouvert", incoherence a confirmer par `nadia-prof` |
| Ecart d'affectation : les cinq paquets a `lea-dev` sans relire la table du planning | capacite | `sofia-sm` (auteur : l'orchestrateur) | - | clos, table corrigee avec sa date |
| `VOI-10` rougirait par construction sur 3 paquets sur 5 | technique puis pedagogie | `theo-archi`, `nadia-prof` | `ADR-0076` | clos |
| `ADR-0076` reposait sur la section 16 de l'arbitrage, restee sur `s26-nadia` | technique, gouvernance | `sofia-sm` | `ADR-0076` | clos |
| `s26-theo-reco` en retard sur `round-two` | gouvernance | `sofia-sm` | - | clos |
| Bloc adulte atteignable a moitie sur l'ecran | technique | `marc-dev` | - | clos (type `BlocAdulte`) |
| Troisieme porteur JavaScript, mal reconnu comme quatrieme, echappant a `ADR-0074` | technique, architecture | `theo-archi` | `ADR-0074` | clos |
| `PaquetCpXxxTest` a moitie corrige ; stories contradictoires ; ma consigne fautive | redaction | `relecteur-pedagogique` | - | clos |
| Refus pedagogique initial : cinquieme phrase manquante, second motif sur `cp-lettre-du-son` | pedagogie | `lea-dev` | - | clos (leve) |
| Trois lignes fermees et non quatre : `cp-lettre-du-son` ne sert aucune voyelle simple | produit, matrice | `camille-po` | - | ouvert - rang de s27 non rendu a cette cloture |
| `s26-camille` stale : conflit sur six fichiers deja corriges autrement | gouvernance | `camille-po` | - | non fusionne, signale plutot que force |
| Trois incidents "fichier reste sur une branche" en un sprint | technique, architecture | `theo-archi` | - | ouvert - dette a instruire |
| Quatrieme ecart de l'orchestrateur : correction de contenu lancee en parallele d'une fusion qui allait la doubler | gouvernance | `sofia-sm` | - | clos |

Rappel de `squad/README.md` section 4 : un obstacle n'est `clos` que si le fichier de decision
existe physiquement, ou si sa resolution est verifiee sur le disque.

### Porte pedagogique - `nadia-prof`

- **Verdict** : **VALIDE** (leve depuis REFUSE, ci-dessus, sans effacer le motif du refus).
- **Fiches** : les cinq `squad/conformite/FICHE-*.md` des paquets a voix.
- **Motif** : voir les deux passages ci-dessus, in extenso.
- **Reserves** : aucune ligne de matrice `couvert-valide` sur "Associer les graphemes aux
  phonemes" - trois lignes se ferment, pas quatre.

### `s26-camille` - non fusionne, signale

Tentative de fusion faite, en conflit sur six fichiers. Ce commit de `camille-po`, date d'avant le
renommage `S26-VOI` vers `S26-L` et avant la fermeture de la branche "mecanique", refait le meme
travail avec une redaction differente sur les memes fichiers. Je ne force pas la fusion : le
contenu est deja couvert, sous une autre plume, par ce qui est fusionne sur `round-two`. Fusion
abandonnee proprement, branche laissee intacte.

### `s26-yanis` - non fusionne, garde

Ses deux commits restent rouges par construction : ce n'est pas leur version qui entre, leurs
correctifs sont entres par `s26-theo-reco` et `s26-marc`. Je garde la branche plutot que de la
supprimer en silence : son rapport qualite est extrait dans `rapport-qualite.md`, mais ses commits
de test restent la source primaire de ce que `yanis-qa` a mesure.

## Decisions prises (creees ou modifiees ce sprint)

| Reference | Titre | Type | Statut |
|---|---|---|---|
| `DEC-0068` | La voix est celle d'un adulte present | Produit | Acceptee, amendee |
| `DEC-0067` | Etat du produit a la cloture de s25, rang de s26 | Produit | Acceptee, amendee |
| `ADR-0032` | Identite d'un tour (item) | Architecture | Accepte, amende |
| `ADR-0074` | Tout JavaScript servi vit dans un fichier | Architecture | Accepte, amende |
| `ADR-0075` | La famille a voix est un champ d'item | Architecture | Accepte |
| `ADR-0076` | `VOI-14` remplace l'operateur de `VOI-10` | Architecture | Accepte |
