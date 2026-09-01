# Fiche de conformite - saisie-mot-dicte-cp

- **Mecanique** : saisie ; champ vocal (champ d'item, tranche par `theo-archi` - `ADR-0075`,
  qui amende `ADR-0032` sur son critere ; la branche mecanique de `DEC-0067`, amendement,
  sections B.5 et B.7, ne s'est pas realisee). **Premier paquet a reponse alphabetique sur la
  mecanique de saisie** - jusqu'ici seule la reponse numerique a ete jouee
  (`saisie-complements-10-cp`).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-mot-dicte.yaml`
  (a creer), story `S26-L5` (`docs/backlog/backlog.md`). Ecriture neuve ; aucun fichier de
  `contenu-repris/` ne porte cette forme.
- **Entree de catalogue** : `saisie-mot-dicte-cp` (identifiant propose, a confirmer par le
  developpeur).
- **Statut global** : **VALIDE** (Done pedagogique prononce le 2026-09-01 sur `round-two` a `a5147d8`). **Le REFUSE du meme jour est leve, et ma reserve non bloquante aussi** : `VOI-12` est tenue, et `gomme` / `bulle` sont remplaces par `brosse` / `trousse`, dont le doublement se deduit du son. **Aucune reserve ne subsiste.** **Le refus n'est pas efface.** Ligne de matrice : peut passer a `couvert-valide`.
  **Historique** : **REFUSE** (Done pedagogique **non prononce**, 2026-09-01, sur `round-two` a `bc59642`). **Motif bloquant : `VOI-12` NON TENUE**, plus **une reserve nommee qui ne bloque pas** - `gomme` et `bulle` ne se deduisent pas du son. **Hors `VOI-12`, ce paquet serait VALIDE SOUS RESERVE.** `SAI-1`, `SAI-3` et `SAI-4` sont verifiees en jeu. Le refus est ecrit ci-dessous et n'est pas efface.
- **Arbitrage prealable de `nadia-prof` rendu** : `squad/conformite/ARBITRAGE-PREALABLE-s26-la-
  voix-est-un-adulte.md`, section 4.5 (acceptation - *"c'est ma propre condition qui se leve"*,
  `SAI-11` etant desormais levee sur sa condition remplie) et sections 5, 6, 7, 13 (conditions
  transverses aux cinq paquets). `DEC-0068` et son amendement portent l'arbitrage de l'utilisatrice
  qui rend ce moyen possible.
- **Ce qui reste tranche par une condition anterieure, non a reconfirmer** : la lettre accentuee
  sur tablette reste sans reponse de l'utilisatrice, mais `SAI-1` (2026-08-24) compte deja juste
  par defaut une reponse ecrite sans accent.
- **A trancher avant developpement, par `theo-archi` et `sofia-sm` (casquette dev), pas par
  `camille-po`** : lesquelles des conditions `SAI-1` a `SAI-12` et `SEUIL-1` a `SEUIL-5`, ecrites
  pour une reponse numerique, s'appliquent telles quelles a une reponse alphabetique, et lesquelles
  en demandent une nouvelle.
- **Derniere mise a jour** : 2026-09-01 (**verdict de `nadia-prof` apres avoir joue**, s26).

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CP (cycle 2) | Ecriture | Ecrire de maniere autonome un mot simple en respectant les correspondances grapheme/phoneme connues | `squad/programme/francais/competences-cp.md` |

**Une seule ligne de matrice visee.**

## Criteres pedagogiques attendus - conditions de `nadia-prof`, reprises telles quelles

- [ ] **`VOI-8` - NON GARDEE.** L'adulte dit le mot, puis autant de fois que l'enfant le demande.
  Il ne l'epelle jamais, ne le decoupe jamais en syllabes, et n'ecrit rien. *"Epeler, c'est faire
  l'exercice." Aucun test ne peut le verifier. La seule contrepartie est la phrase de `VOI-12` sur
  ce paquet : "Dites le mot autant de fois qu'il le demande, mais ne l'epelez jamais."*
- [ ] **`VOI-9` (partiellement testable ; la regularite reelle reste jugee par `nadia-prof`)** -
  Les mots du paquet sont reguliers et de deux syllabes au plus, et aucun ne porte de lettre muette
  finale autre que celles que le CP a apprises.
- [ ] **`VOI-1`** - L'adulte prononce le stimulus (le mot a ecrire) ; il ne prononce jamais une
  proposition (sans objet ici, mecanique de saisie, mais la regle reste tenue par construction :
  aucune proposition n'existe).
- [ ] **`VOI-10`** - Le texte que l'adulte prononce est un champ distinct, et il ne contient jamais
  la reponse ecrite attendue en toutes lettres au-dela du mot lui-meme.
- [ ] **`VOI-12` (exigence bloquante)** - L'ecran dit a l'adulte ce qu'il ne doit pas faire, a
  l'endroit ou il lit son texte. Phrase candidate rendue par `nadia-prof` : *"Dites le mot autant
  de fois qu'il le demande, mais ne l'epelez jamais."*
- [ ] **`VOI-13`** - Le mot travaille est ecrit une seule fois a l'ecran, dans le texte de l'adulte,
  et nulle part ailleurs (le champ de saisie de l'enfant reste vide jusqu'a sa reponse). *Comptee
  comme la quatrieme condition non gardee pour sa part non calculable.*
- [ ] Le paquet compte au plus **20 items** (borne de `nadia-prof`, addendum section 12.2).

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. Il reste vide tant qu'aucun
> increment n'a ete livre et joue.

### Verdict du 2026-09-01 sur l'increment de s26 (`round-two`, `bc59642`) - **REFUSE**

- **Increment juge** : `round-two` a `bc59642`. Jar reconstruit et lance par moi, `mvn -B clean
  package`, JDK 21.0.10 rapporte par la machine virtuelle. **J'ai joue**, je n'ai pas lu.
- **Ce que j'ai fait** : plusieurs parties completes sur ce jeu, avec des erreurs volontaires, des
  echecs repetes sur un meme item jusqu'a la revelation, et une partie menee jusqu'a l'ecran de
  fin. Puis l'audit d'exactitude de **tous** les items du paquet, **dits a voix haute par moi**,
  parce qu'aucun test ne sait prononcer.
- **Ma borne** : 75 items pour les cinq paquets, sous mon plafond de 115. `cp-le-bon-son.yaml` est
  **inchange** - `git diff 96a80b4 --` sur ce fichier est vide -, donc la clause 3 de ma barre du
  2026-08-10 est tenue par constat et je n'ai pas rejoue ses 15 items, exactement comme je l'avais
  ecrit d'avance (addendum, section 12.2). **Je n'ai rien echantillonne et je n'ai pas eu a
  decliner.**

#### Le motif bloquant, commun aux cinq paquets : **`VOI-12` est NON TENUE**

**Il manque la cinquieme phrase d'adulte**, celle que j'ai attachee a `VOI-12` en section 14.3 de
mon addendum, **avant que ce contenu n'existe** : *"`VOI-12` gagne donc un cinquieme interdit, qui
vaut sur les cinq paquets - "Ne montrez pas cette ligne a l'enfant." ou une phrase qui dise la meme
chose."*

**Je l'ai cherchee et elle n'est nulle part.** Verifie sur l'accueil, sur la page de niveau, sur
l'ecran de jeu, et sur les cinq `consigneAdulte` du catalogue : aucune formulation ne dit a l'adulte
que l'enfant ne doit pas lire la ligne qu'il lui lit.

**Pourquoi ce n'est pas une formalite, et je le mesure sur ce jeu-ci** : un enfant assis a cote lit
le bloc adulte. Ce que je mesure ci-dessous, paquet par paquet, c'est que **lire cette ligne suffit
a gagner sans la competence** - sur les cinq, sans exception. C'est le constat que j'avais ecrit en
section 14.2 et que l'increment confirme.

> **Ce que j'avais ecrit d'avance et que je tiens** : *"Sans `VOI-12`, je refuserai les paquets a
> voix. Non pas parce que le contenu serait mauvais, mais parce qu'un produit qui confie une
> condition a quelqu'un sans la lui dire ne l'a pas confiee : il l'a abandonnee."*

**Ce refus se leve avec une phrase par paquet.** Il ne demande aucune reecriture de contenu, aucun
changement de mecanique et aucune reprise d'audit : tout ce que j'ai verifie est ecrit ci-dessous
pour n'avoir pas a etre reverifie.

#### `VOI-12`, point par point, sur l'objet reel

| Point (arbitrage, 13.1) | Mesure sur cet increment | Etat |
|---|---|---|
| 1 - presence sans geste, sur **chaque** ecran ou l'adulte a un texte | le bloc adulte porte ses trois parties ensemble, sans clic ni survol, sur l'ecran d'item, apres une bonne reponse, apres une erreur **et** sur l'ecran de revelation | **TENU** |
| 2 - destinataire nomme | *"Adulte, a voix haute :"* - je le lis et je sais sans hesiter a qui il parle | **TENU** |
| 3 - l'interdit de CE paquet, rattache a une et une seule condition | les quatre phrases livrees se rattachent chacune a exactement une condition | **TENU pour les quatre phrases livrees** |
| 4 - lisible sans le depot | une phrase par paquet, aucun des mots que j'avais interdits | **TENU** |
| 5 - la phrase ne fuit pas | aucune `consigneAdulte` ne porte une proposition de son item | **TENU** |
| **la cinquieme phrase de la section 14.3** | **absente des cinq paquets et de tous les ecrans** | **NON TENU** |

**Je nomme exactement ce qui echoue, pour qu'un tiers puisse le verifier sans me le redemander** :
les cinq points de 13.1 sont tenus sur les quatre phrases livrees. **Ce qui manque est la phrase
elle-meme, exigee par la section 14.3.** Je ne pretends pas que le point 3 echoue : il ne echoue
pas.

#### Ce que je corrige de ma propre main avant de m'en servir - `VOI-13` etait trop large

**`VOI-13`, telle que je l'ai ecrite le 2026-09-01, interdit le mot *"ni sur l'ecran d'erreur, ni
sur l'ecran de revelation"*. Cette enumeration est trop large et je la corrige, datee du meme
jour.**

**Ce que j'ai mesure** : sur `saisie-mot-dicte-cp`, le mot sort du bloc adulte - *"On ecrit :
jupe"* - **exactement au moment ou l'item cesse d'etre jouable**, jamais avant. Sur les quatre jeux
a propositions, il n'en sort jamais. **Le motif de `VOI-13` est donc entierement satisfait** : ce
que je crains est que l'enfant lise au lieu d'entendre, et il ne peut pas lire tant qu'il peut
repondre.

**Une dictee qui ne montrerait jamais l'orthographe apres l'echec ne corrigerait rien.** J'aurais
refuse, sur la lettre de ma propre clause, le seul geste qui apprend quelque chose a l'enfant qui
s'est trompe.

> **`VOI-13` corrigee (2026-09-01)** - le mot travaille n'apparait hors du texte de l'adulte
> **sur aucun ecran ou l'item peut encore etre repondu**. Apres resolution de l'item, l'afficher est
> non seulement permis mais **attendu** sur un paquet de saisie. *Troisieme de mes clauses fausse en
> extension aujourd'hui, apres `VOI-2` et `VOI-10`, et la seule dont la lettre m'aurait fait refuser
> a tort.*

**Etat mesure sur ce jeu** : `VOI-13` corrigee est **TENUE**.

**`VOI-12` bloque, et j'ajoute une reserve nommee qui ne bloque pas.**

#### Ce que j'ai verifie sur ce paquet, et qui est TENU

**Les 12 mots, dictes a voix haute par moi et tapes.**

| Verification | Mesure | Etat |
|---|---|---|
| **`SAI-1`** - une reponse sans accent compte juste | j'ai tape `velo` pour `velo` accentue : **compte juste**, score incremente, aucun indice affiche | **TENU** |
| **`SAI-3`** - la casse ne penalise pas | j'ai tape `DUNE` pour `dune` : **compte juste** | **TENU** |
| **`SAI-4`** - les espaces de bord ne penalisent pas | j'ai tape `" moto "` : **compte juste** | **TENU** |
| **`VOI-9`** - mots reguliers, deux syllabes au plus | **12 sur 12** : velo, lune, jupe, robe, moto, pile, gomme, tasse, cape, dune, mule, bulle. Aucune consonne finale muette ; le seul `e` final est celui que le CP a appris | **TENU** |
| **`VOI-13`** (corrigee) | le mot ne quitte le bloc adulte **qu'au moment ou l'item cesse d'etre jouable** - mesure a l'essai pres | **TENU** |
| **L'enfant bloque peut avancer** | apres trois echecs : *"Ce n'est pas ca. Tu as ecrit : zzz. On ecrit : jupe."* **L'orthographe est donnee et l'enfant repart** | **TENU** |
| **`VOI-1` n'a pas d'objet ici** | aucune proposition, l'enfant produit. C'est ce que j'ai tranche en section 16.3, et l'increment le respecte : la branche `saisie` du garde-fou a disparu | **TENU** |

#### Reserve nommee - **deux items sur douze ne se deduisent pas du son**

**Elle ne bloque pas le Done ; elle sera a corriger dans un sprint suivant.**

**`gomme` et `bulle`.** Un enfant qui entend [gom] et ecrit `gome`, ou qui entend [byl] et ecrit
`bule`, **a applique correctement les correspondances grapheme/phoneme qu'il connait**. Les deux
ecritures se lisent exactement pareil. Le doublement de la consonne n'est pas deductible du son :
**c'est une memoire d'orthographe.**

**C'est le motif de ma propre `VOI-9`, que je cite** : *"la ligne dit "en respectant les
correspondances grapheme/phoneme **connues**" - un mot dont l'ecriture ne se deduit pas du son
n'evalue pas cette ligne, il evalue une memoire d'orthographe."*

**Je ne durcis pas ma clause : je constate que sa lettre laisse passer ce que son motif ecarte**, et
c'est une reserve, pas un motif. **Les dix autres sont irreprochables** - et `tasse` en particulier
est bien deductible, parce que `tase` se lirait [taz] : la regle du `s` entre deux voyelles est au
programme du CP et elle force le doublement. `gomme` et `bulle` n'ont pas cette excuse.

**Ce que l'increment fait bien malgre tout** : l'enfant qui echoue finit par voir *"On ecrit :
gomme"*. **Il n'est pas laisse sans reponse.** Ce qui manque est l'explication du doublement - et ce
paquet n'a pas de champ pour la porter. C'est ce que je nomme, pas ce que je concois.

**Ce qui leve la reserve** : deux mots remplaces par deux mots deductibles, ou un moyen de dire
pourquoi on double. Le choix est a `camille-po`.

#### Ce qui gagnerait sans la phrase manquante

**C'est le paquet ou l'exposition est la pire des cinq** : un enfant qui lit *"Le mot est gomme."*
**recopie**. Il ne gagne pas *"un peu"* sans la competence - il ne fait strictement rien de
l'exercice.

#### Ce dont je reste seule garde sur ce paquet

- **`VOI-8` - NON GARDEE.** Qu'un adulte n'epelle pas. Le garde-fou attrape une epellation **ecrite**
  dans le texte ; il n'attrape pas celle qui sort de la bouche. Sa phrase est bonne : *"Dites le mot
  autant de fois qu'il le demande, mais ne l'epelez jamais."*
- **La regularite reelle de chaque mot pour un CP.** C'est ce que je viens de faire, et c'est ce qui
  a trouve `gomme` et `bulle`.

#### Verdict s'il n'y avait pas `VOI-12`

**VALIDE SOUS RESERVE**, la reserve etant `gomme` et `bulle`.

### Second verdict du 2026-09-01 sur le correctif (`round-two`, `a5147d8`) - **REFUS LEVE, VALIDE**

**Le refus du meme jour n'est pas efface. Il reste lisible ci-dessus avec son motif, et l'histoire de
ce jeu se lit dans l'ordre.**

- **Increment juge** : `round-two` a `a5147d8`. **Jar reconstruit et relance par moi**, JDK 21.0.10
  rapporte par la machine virtuelle. **J'ai rejoue.**
- **Perimetre de cet audit : le delta, et rien de plus large.** J'avais borne moi-meme ce que la
  levee demandait - *"une phrase par paquet, sans reecrire un item ni refaire un audit"* - et tout
  ce que j'avais verifie le matin est consigne plus haut. **Je ne rejoue pas les 75 items.**

#### Le motif bloquant est leve - `VOI-12` est TENUE

**La cinquieme phrase est la** : *"Ne montrez pas cette ligne a l'enfant."*, ajoutee a
`consigneAdulte`.

**C'est le texte que j'avais moi-meme rendu candidat et mesure TENU en section 13.2**, repris tel
quel plutot que reinvente. **Je ne peux pas trouver insuffisant le texte que j'ai propose** : ce
serait deplacer mes poteaux apres coup, et je m'en suis interdit la possibilite par ecrit.

| Point de 13.1 | Ce que j'ai mesure sur cet increment | Etat |
|---|---|---|
| 1 - presence sans geste, sur **chaque** ecran ou l'adulte a un texte | **verifie sur l'ecran d'item, sur l'ecran d'erreur et sur l'ecran de revelation** pour ce jeu. La phrase vit dans `consigneAdulte`, donc dans le meme paragraphe que le premier interdit, **immediatement sous le stimulus, sans rien d'intercale** : trois paragraphes, un bloc | **TENU** |
| 2 - destinataire nomme | *"Adulte, a voix haute :"* coiffe le bloc entier, la cinquieme phrase comprise | **TENU** |
| 3 - l'interdit de CE paquet, une et une seule condition | la cinquieme phrase se rattache a `VOI-13` et a elle seule. **Son identite d'un paquet a l'autre est correcte** : ma clause ne l'interdit que *"sur deux paquets dont les interdits different"*, et `VOI-13` est le meme interdit sur les cinq. **Je lis ma clause comme je l'ai ecrite** | **TENU** |
| 4 - lisible sans le depot | une phrase, un verbe d'action (*"montrez"*), des mots qu'un parent emploie, **aucun des mots que j'avais interdits** | **TENU** |
| 5 - la phrase ne fuit pas | aucune proposition dans le texte de l'adulte - **sous reserve de la correction d'operateur que je fais contre moi ci-dessous** | **TENU** |

**Le choix de porter la phrase par le contenu et non par le gabarit ne m'appartient pas** - c'est
`ADR-0075` section 7, anterieur a mon refus. **Je constate seulement qu'il rend la phrase presente
la ou je l'exigeais**, et c'est tout ce que ma porte demande.

#### Ma reserve non bloquante est levee elle aussi, et le motif rendu est le mien

**`gomme` et `bulle` sont remplaces par `brosse` et `trousse`.**

**Je verifie l'argument plutot que de l'accepter** : le `s` simple entre deux voyelles se lit `[z]`,
donc *"brose"* dirait `[broz]` et *"trouse"* dirait `[trouz]`. **Le doublement se deduit du son
entendu.** C'est la regle du programme de CP, la meme qui forcait deja `tasse` dans ce paquet, et
c'est exactement le motif de ma `VOI-9` : *"un mot dont l'ecriture ne se deduit pas du son n'evalue
pas cette ligne, il evalue une memoire d'orthographe."*

**La difference avec ce que j'avais refuse est nette** : un enfant qui ecrit `brose` et qui redit le
mot **peut trouver son erreur**. Un enfant qui ecrivait `gome` ne le pouvait pas - `gome` et `gomme`
se lisent identiquement, et l'indice offert (*"demande a l'adulte de redire le mot"*) ne pouvait
jamais l'aider. **Le remede va precisement la ou etait le defaut.**

**Verifie en jouant** : j'ai tape `brose`, refuse ; puis `brosse`, accepte. `trouse`, refuse ;
`trousse`, accepte.

#### Ce que j'ai regarde parce qu'on m'a demande si le remede rouvrait autre chose - **il ne rouvre rien**

| Point regarde | Constat |
|---|---|
| **Nombre de syllabes** | `brosse` et `trousse` en comptent **une**. `VOI-9` en autorise deux au plus. **TENU** |
| **Lettre muette finale** | le seul `e` final, celui que le CP a appris. Aucune consonne finale muette. **TENU** |
| **Groupe consonantique initial** (`br`, `tr`) | **aucun autre mot du paquet n'en porte** : c'est une marche de difficulte reelle. **Mais elle se deduit du son** - l'enfant entend `[br]`, il ecrit `b` puis `r` -, donc elle reste dans la ligne, qui porte sur les correspondances grapheme/phoneme. **Je le nomme et je ne le compte pas contre** : fabriquer une reserve pour avoir l'air rigoureuse serait sa propre faute |
| **Le `ou` de `trousse`** | deductible, et c'est le digraphe que `cp-digraphe-du-son` travaille au meme sprint. **Le paquet voisin le sert plutot qu'il ne le contrarie** |
| **Le paquet est-il toujours entier ?** | 12 items ; `SAI-1`, `SAI-3` et `SAI-4` toujours verifiees en jeu ; 7 manches jusqu'a l'ecran de fin. **TENU** |

**Verdict : VALIDE, sans reserve.** J'avais ecrit *"VALIDE SOUS RESERVE"* comme verdict conditionnel
ce matin ; **la reserve ayant ete payee dans le meme sprint, il n'en reste aucune.**

**Ligne de matrice** : *"Ecrire de maniere autonome un mot simple en respectant les correspondances
grapheme/phoneme connues"* peut passer a `couvert-valide`.

#### Ce que je corrige contre moi dans le meme geste - l'operateur de mon point 5

**En mesurant le point 5 sur cet increment, j'ai trouve mon propre operateur faux, une quatrieme
fois aujourd'hui.**

Le texte de l'adulte contient desormais *"a l'enfant"*. **Decoupe en jetons, l'apostrophe produit un
jeton `l`** - et `l` est une proposition de `cp-lettre-du-son`. Mesure : **3 tirages sur 8** de ce
jeu-la sont declares fuites par mon propre point 5.

**C'est un faux positif, et c'est exactement la famille que j'ai denoncee ce matin sur "chat" et
"ch"** : *"l'"* est un **article elide**, pas la designation d'une lettre. Aucun adulte lisant *"Ne
montrez pas cette ligne a l'enfant"* n'enonce la proposition `l`.

> **Correction datee du 2026-09-01** - dans le decoupage en jetons de mon point 5 comme de `VOI-14`,
> **l'article elide n'est pas un jeton de l'espace de reponse**. Un jeton d'une seule lettre produit
> par une apostrophe ne compte pas.

**Ce que cela dit d'utile a l'equipe, et je le nomme parce qu'aucun test n'a rougi** : le point 5 de
`VOI-12` n'est encode nulle part - le garde-fou d'`ADR-0075` lit `texteAVoixHaute`, pas
`consigneAdulte`. **C'est une propriete dont je suis seule garde, et c'est moi seule qui pouvais
trouver ce faux positif, comme c'est moi seule qui aurais pu manquer un vrai.**

#### Ce dont je reste seule garde - **la liste ne diminue pas d'une unite**

`VOI-3`, `VOI-5`, `VOI-8` et la moitie non calculable de `VOI-13` restent **quatre conditions que
personne ne tient**. **La cinquieme phrase ne fait pas respecter `VOI-13` : elle la dit.** C'etait
exactement la contrepartie que je demandais - le produit n'a que ce levier-la - **et il ne faut pas
la lire comme une garantie.** J'y ajoute le point 2 et la moitie du point 3 de `VOI-12`, et
desormais son point 5, qu'aucun automate ne lit.

**Done pedagogique prononce le 2026-09-01.**
