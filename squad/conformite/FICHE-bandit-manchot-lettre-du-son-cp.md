# Fiche de conformite - bandit-manchot-lettre-du-son-cp

- **Mecanique** : bandit manchot ; champ vocal (champ d'item, tranche par `theo-archi` -
  `ADR-0075`, qui amende `ADR-0032` sur son critere ; la branche mecanique de `DEC-0067`,
  amendement, sections B.5 et B.7, ne s'est pas realisee).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-lettre-du-son.yaml`
  (a creer), story `S26-L3` (`docs/backlog/backlog.md`). Matiere premiere de forme uniquement,
  refusee comme contenu : `contenu-repris/mots/cp-premiere-lettre.yml` (la bonne reponse y est la
  premiere lettre du mot affiche sur 24 items sur 24 - inutilisable tel quel, ecriture neuve
  requise).
- **Entree de catalogue** : `bandit-manchot-lettre-du-son-cp` (identifiant propose, a confirmer
  par le developpeur).
- **Statut global** : **VALIDE** (Done pedagogique prononce le 2026-09-01 sur `round-two` a `a5147d8`). **Le REFUSE du meme jour est leve sur ses DEUX motifs** : `VOI-12` est tenue, et les dix-huit enonces disent desormais *"le tout premier son"* avant toute reponse - verifie mot par mot, 18 sur 18, aucun item ambigu. **Le refus n'est pas efface.** **Ligne de matrice : reste `en cours`** - la clause 1 de ma barre du 2026-08-10 exige les **voyelles** simples, et ce paquet ne sert que six consonnes. Ce n'est pas un reproche au paquet, c'est un constat de couverture (second verdict, derniere section).
  **Historique** : **REFUSE** (Done pedagogique **non prononce**, 2026-09-01, sur `round-two` a `bc59642`). **Deux motifs. Le second ne se leve pas avec `VOI-12`** : cinq items sur dix-huit ont deux reponses defendables, parce que rien ne dit a l'enfant qu'il cherche le **premier** son avant qu'il ait echoue. **Ce paquet resterait REFUSE meme `VOI-12` tenue** - le seul des cinq dans ce cas. Le refus est ecrit ci-dessous et n'est pas efface.
- **Ce que cette fiche est, et ce qu'elle n'est pas seule a fermer** : cette story couvre **la
  clause 1** (voyelles et consonnes simples) de la ligne "Associer les graphemes aux phonemes".
  **La ligne ne passe `couvert-valide` que si cette fiche ET `FICHE-bandit-manchot-digraphe-du-
  son-cp.md` (clause 2, `S26-L4`) recoivent toutes deux un verdict `VALIDE`** (`nadia-prof`,
  addendum, section 11.6) - livrer l'une sans l'autre ne fait bouger aucune ligne de matrice.
- **Arbitrage prealable de `nadia-prof` rendu** : `squad/conformite/ARBITRAGE-PREALABLE-s26-la-
  voix-est-un-adulte.md`, section 4.3 (acceptation, "la ligne peut se fermer"), sa barre du
  2026-08-10 (`FICHE-bandit-manchot-le-bon-son-cp.md`, verdict du 2026-08-10, section 6), et
  l'addendum, sections 11 a 13 (deux paquets, pas un ; motif mesure).
- **Non-regression sur le jeu existant** : `git diff 96a80b4 -- adapters/adapter-content/src/main/
  resources/contenu/cp-le-bon-son.yaml` doit rester **vide**. Ce paquet ne touche pas ce fichier
  (clause 3, trois nasales, deja acquise).
- **Derniere mise a jour** : 2026-09-01 (**verdict de `nadia-prof` apres avoir joue**, s26).

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CP (cycle 2) | Lecture et comprehension de l'ecrit | Associer les graphemes aux phonemes (voyelles, consonnes simples, puis digraphes ch, ou, on...) | `squad/programme/francais/competences-cp.md` |

**Une seule ligne de matrice visee, en couverture partagee avec `bandit-manchot-le-bon-son-cp`
(clause 3) et `bandit-manchot-digraphe-du-son-cp` (clause 2).**

## Criteres pedagogiques attendus - conditions de `nadia-prof`, reprises telles quelles

- [ ] Espace de reponse : **une lettre simple**, parmi une vingtaine (voyelles et consonnes
  simples). Jamais un digraphe.
- [ ] **Condition de distribution (sans numero attribue par `nadia-prof`, citee mot pour mot,
  addendum section 11.4)** - "chaque lettre servie est distracteur au moins aussi souvent qu'elle
  est reponse".
- [ ] **`VOI-7`** - L'adulte dit le mot entier, jamais le grapheme isole (le son de la lettre
  seule).
- [ ] **`VOI-1`** - L'adulte prononce le stimulus (le mot ou le son) ; il ne prononce jamais une
  proposition.
- [ ] **`VOI-10`** - Le texte que l'adulte prononce est un champ distinct, et il ne contient jamais
  une proposition ni la reponse.
- [ ] **`VOI-12` (exigence bloquante)** - L'ecran dit a l'adulte ce qu'il ne doit pas faire, a
  l'endroit ou il lit son texte. Phrase candidate rendue par `nadia-prof` (interdit rattache a
  `VOI-7`) : *"Dites le mot entier, jamais le son tout seul."*
- [ ] **`VOI-13`** - Le mot travaille est ecrit une seule fois a l'ecran, dans le texte de l'adulte,
  et nulle part ailleurs. *Comptee comme la quatrieme condition non gardee pour sa part non
  calculable.*
- [ ] Le paquet compte au plus **20 items** (borne de `nadia-prof`, addendum section 12.2).

**Aucune condition de cette fiche n'est comptee parmi les quatre non gardees** : `VOI-3`, `VOI-5`
et `VOI-8` sont specifiques a d'autres paquets ; seule `VOI-13` (partiellement) s'applique ici,
comme sur les quatre autres paquets.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. Il reste vide tant qu'aucun
> increment n'a ete livre et joue. **Le Done pedagogique de cette fiche ne fait pas bouger la
> ligne de matrice tant que `FICHE-bandit-manchot-digraphe-du-son-cp.md` n'a pas elle aussi recu
> un verdict `VALIDE`.**

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

**Deux motifs, et le second est propre a ce paquet.** Il tiendrait meme si `VOI-12` etait levee.

#### Second motif, propre a ce paquet : **cinq items sur dix-huit ont deux reponses defendables**

**Ce motif est independant de `VOI-12` et il ne se leve pas avec elle.**

**Ce que l'enfant voit avant sa premiere reponse** : la consigne *"Ecoute l'adulte lire le mot, puis
choisis la lettre qui fait ce son."*, l'enonce *"Ecoute l'adulte, puis choisis la lettre qui fait ce
son."*, et trois lettres. **Rien, nulle part, ne dit de quel son il s'agit.** *"Ce son"* n'a aucun
antecedent : l'adulte n'a dit qu'un mot.

**L'information manquante existe - mais dans l'indice**, qui ne s'affiche **qu'apres un echec** :
*"[...] puis ecoute le tout premier son."*

**Ce que cela produit, mesure sur les 18 items** : sur **5 items**, un distracteur est lui aussi une
lettre dont le son est dans le mot entendu.

| Item | Bonne reponse | Distracteur egalement present dans le mot |
|---|---|---|
| `pomme` | `p` | **`m`** |
| `moto` | `m` | **`t`** |
| `table` | `t` | **`l`** |
| `tortue` | `t` | **`r`** |
| `lapin` | `l` | **`p`** |

**5 sur 18, soit 27,8 %.**

**Pourquoi c'est un motif de refus et pas une reserve.** Un enfant de CP qui entend *"pomme"*, qui
sait que `m` fait le son [m], et qui presse `m`, **a raison sur la competence que la ligne nomme** -
associer un grapheme a un phoneme. Le jeu lui repond qu'il a tort. **On le corrige d'un savoir
juste, au nom d'une convention qu'on ne lui a jamais dite.** C'est la meme famille que le refus que
j'ai prononce le 2026-08-31 sur `cm1-substituts` : un jeu qui enseigne a l'enfant que ce qu'il sait
est faux.

**Et sur les treize autres items, l'exercice se gagne par une strategie qui n'est pas la ligne** :
*"laquelle de ces trois lettres est dans le mot que j'ai entendu"*. Elle rend juste 13 fois sur 18
et elle est renforcee a chaque fois.

**Ce qui leve ce motif, et c'est actionnable sans rien recrire d'autre** : dire dans la consigne de
l'enfant **de quel son il s'agit**, avant sa premiere reponse - par exemple *"choisis la lettre du
premier son"*. **Cela n'ecrit aucune lettre a l'ecran** et ne casse donc rien : la position d'un son
n'est pas sa graphie. C'est un mot dans la consigne, pas une reecriture du paquet.

#### Ce que j'ai verifie par ailleurs, et qui est TENU

| Verification | Mesure | Etat |
|---|---|---|
| **Distribution** (ma condition, addendum 11.4 : *"chaque lettre servie est distracteur au moins aussi souvent qu'elle est reponse"*) | six lettres servies (`p`, `m`, `t`, `r`, `l`, `s`), **chacune reponse 3 fois et distracteur 6 fois**. Deux fois plus souvent distracteur que reponse | **TENU** |
| **`VOI-7`** - l'adulte dit le mot entier, jamais le grapheme isole | verifie sur toutes les parties : le texte de l'adulte est toujours *"Le mot est <mot>."* | **TENU** |
| **`VOI-13`** (corrigee) | **56 ecrans inspectes, 0 ecart** | **TENU** |
| **L'enfant bloque peut avancer** | indice, puis revelation | **TENU** |

#### Ce qui gagnerait sans la phrase manquante

**Un enfant qui lit *"Le mot est pomme."* voit le `p` initial et presse `p`.** C'est mot pour mot
`cp-premiere-lettre.yml`, le paquet de reserve dont j'ai mesure qu'il est resoluble par la vue sur
**24 items sur 24** et qui n'a jamais ete servi pour ce motif.

#### Consequence sur la ligne de matrice, et elle est de mon fait de composition

**La ligne "Associer les graphemes aux phonemes" ne bouge pas.** Elle exige ses deux moities
(addendum, section 11.6). Ce paquet est la moitie 1 et il est refuse ; **la moitie 2
(`cp-digraphe-du-son`) n'a aucun motif contre elle hors `VOI-12`, et cela ne suffit pas.** La ligne
reste `en cours`, comme depuis s03.

#### Verdict s'il n'y avait pas `VOI-12`

**REFUSE quand meme**, sur le second motif. C'est le seul des cinq paquets dans ce cas.

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

#### Mon second motif est leve, et le correctif est celui que j'avais nomme

**Ce que j'exigeais** : *"dire dans la consigne de l'enfant de quel son il s'agit, avant sa premiere
reponse [...] Cela n'ecrit aucune lettre a l'ecran."*

**Ce qui est livre** : la consigne et **les dix-huit enonces** disent *"le tout premier son"* au lieu
de *"ce son"*. **Un mot ajoute, aucune lettre supplementaire a l'ecran.** C'est mon correctif,
applique a la lettre.

**Ce que j'ai verifie moi-meme, parce que la formulation est de `lea-dev` et pas de moi :**

| Verification | Mesure | Etat |
|---|---|---|
| **Le mot est-il juste pour un CP ?** | *"le tout premier son"* est la formule de classe pour la conscience phonologique au CP. **Et elle est meilleure que "le premier son"** : *"tout"* ferme la porte a l'enfant qui comprendrait *"le premier que je remarque"* | **TENU** |
| **Est-ce dit AVANT toute reponse ?** | oui - sur l'ecran d'item, dans la consigne **et** dans l'enonce, sans qu'aucune erreur soit necessaire. **C'etait tout le defaut** | **TENU** |
| **Un item devient-il ambigu ?** | **non, 18 sur 18** : la reponse est le premier phoneme de chaque mot, aucun mot ne commence par une lettre muette, un groupe consonantique ni un digraphe. Verifie mot par mot, dit a voix haute | **TENU** |
| **Les cinq items que j'avais nommes** - `pomme`, `moto`, `table`, `tortue`, `lapin` | **leur ambiguite tombe entierement.** Un distracteur peut encore etre une lettre du mot, mais **il ne peut pas en etre le premier son**. L'enfant qui presse `m` sur `pomme` n'est plus corrige d'un savoir juste : **la question lui est enfin posee** | **TENU** |
| **Le correctif n'a rien casse ailleurs** | 18 items, **enonce unique et invariant** - donc `VOI-13` intacte, l'enonce ne distinguant pas les tours - et distribution inchangee : chaque lettre reponse 3 fois, distracteur 6 fois | **TENU** |

**Verdict : VALIDE. Mes deux motifs sont leves.** C'etait le seul des cinq paquets a en porter deux,
et le seul dont la levee demandait autre chose qu'une phrase.

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

#### Ligne de matrice - **ce qui manque encore, et ce n'est pas un motif contre ce paquet**

**Ce paquet est valide. La ligne, elle, ne se ferme pas - et je dois le dire ici parce que c'est
maintenant qu'elle bougerait.**

**Ma barre du 2026-08-10, clause 1, mot pour mot** : *"les **voyelles simples** et les consonnes
simples sont couvertes, par ce jeu ou par un autre."*

**Ce paquet sert `p`, `m`, `t`, `r`, `l`, `s` : six consonnes, et aucune voyelle.** Verifie sur tout
le contenu servi - aucun paquet de CP n'a une voyelle simple pour bonne reponse. **La moitie
"voyelles" de ma clause 1 n'est servie par rien.**

**Ce n'est pas un reproche a ce paquet** : le Done porte sur l'increment, la matrice porte sur la
couverture d'une competence, et je maintiens cette distinction comme je l'ai maintenue le 2026-08-10
sur `cp-le-bon-son`. **C'est un constat de couverture, et il est de mon perimetre parce que la barre
est de moi.**

**Consequence : la ligne "Associer les graphemes aux phonemes" reste `en cours`.** Ses clauses 2
(digraphes `ou`, `oi`, `ch` par un jeu qui emet un son), 3 (les trois nasales intactes - `git diff`
vide sur `cp-le-bon-son.yaml` a `a5147d8`) et 4 (j'ai joue) **sont desormais toutes remplies. Il ne
manque que les voyelles simples.** Un paquet de plus ferme cette ligne, et je le dis d'avance pour
que ce ne soit pas decouvert au sprint suivant.

---

## Reverification de s29 (`S29-8`, `6b2edf1`) - **la dette des paquets a voix est payee**

- **Verdict** : **la dette est payee sur ce jeu**, constate le **2026-09-06** par `nadia-prof`,
  **Instance A**, sur le contenu servi. **Aucun Done n'est rouvert, aucune ligne de matrice ne
  bouge** : la reserve etait non bloquante des son ouverture et le reste.
- **Origine** : reserve ouverte a s26, **elargie par ma propre mesure a s27** (six jeux et non trois),
  **chiffree a s28** (0,25, un passage), **non payee a s28**. Elle aurait eu quatre sprints si s29 ne
  l'avait pas payee. **Elle est payee.**
- **Regime et prix** : mot pour mot, **0,25 pour les six jeux ensemble** - une reverification
  d'identite, pas une pedagogie.

### Ce que j'ai verifie sur le contenu servi

| Cle | Servi le 2026-09-06 | Conforme au texte que j'ai fourni ? |
|---|---|---|
| `enonce` (18 items) | *"Quelle lettre fait le premier son ?"* | **oui, mot pour mot** |
| `indice` (18 items) | *"Demande a l'adulte de redire le mot en entier, puis ecoute le tout premier son."* | **oui, mot pour mot** |
| consigne de catalogue | inchangee | **oui** |

**`VOI-3` cesse d'etre contredite par ecrit** : *"doucement"* a disparu de l'indice. Et la redondance
entre la consigne du catalogue et l'enonce est levee **du cote de l'enonce**, comme je l'avais
demande - la consigne porte la tache, l'enonce porte ce qui est propre a l'item.

### Ce que ce constat ne prouve pas

**Je n'ai verifie que le texte ecrit.** `VOI-3`, `VOI-5` et `VOI-8` portent sur ce que l'adulte **fait
de sa voix**, et **personne ne le garde, pas meme moi** : je ne suis pas dans la piece quand l'enfant
joue. Ce que ce lot repare, c'est que **le produit cessait de demander par ecrit le contraire de ce
qu'il demande par ailleurs a l'adulte**. Un adulte qui ralentit quand meme n'est arrete par rien, et
ne le sera jamais. **`VOI-3` n'a aucun garde-fou et n'en aura pas.**

**Un ecart de forme que je constate et que je ne compte pas comme un ecart** : les enonces servis
portent une espace insecable devant le point d'interrogation, la ou mes textes portaient une espace
ordinaire. C'est la typographie du canal enfant ; elle appartient au `relecteur-pedagogique`
(`DEC-0002`, amendement du 2026-08-04 ; `DEC-0056`), et j'avais moi-meme ecrit que l'orthographe,
l'accentuation et la ponctuation de ces textes ne m'appartiennent pas. **Le mot pour mot est tenu.**

**Un jeu voisin que j'ai verifie et que j'ecarte, pour qu'on ne le rouvre pas** :
`bandit-manchot-syllabes-cp` sert trois indices qui disent *"tout doucement"*, *"tres lentement"*,
*"Chante le mot tres lentement"*. **Ce n'est pas un defaut `VOI-3`** : ce jeu **ecrit le mot a
l'ecran** et c'est **l'enfant** qui le prononce, pas l'adulte ; ralentir pour frapper les syllabes est
le geste de classe correct. `VOI-3` gouverne la voix de l'adulte, et ce jeu n'en a pas. **Verifie sur
le catalogue et sur le contenu, pas suppose.**
