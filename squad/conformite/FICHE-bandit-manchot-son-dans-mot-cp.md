# Fiche de conformite - bandit-manchot-son-dans-mot-cp

- **Mecanique** : bandit manchot ; champ vocal (champ d'item, tranche par `theo-archi` -
  `ADR-0075`, qui amende `ADR-0032` sur son critere ; la branche mecanique de `DEC-0067`,
  amendement, sections B.5 et B.7, ne s'est pas realisee).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-son-dans-mot.yaml`
  (a creer), story `S26-L2` (`docs/backlog/backlog.md`). Aucun fichier de `contenu-repris/` ne
  porte cette forme (voix, sans mot ecrit) ; ecriture neuve.
- **Entree de catalogue** : `bandit-manchot-son-dans-mot-cp` (identifiant propose, a confirmer par
  le developpeur).
- **Statut global** : **VALIDE** (Done pedagogique prononce le 2026-09-01 sur `round-two` a `a5147d8`). **Le REFUSE du meme jour est leve** : `VOI-12` est tenue. **Le refus n'est pas efface.** Ligne de matrice : peut passer a `couvert-valide`.
  **Historique** : **REFUSE** (Done pedagogique **non prononce**, 2026-09-01, sur `round-two` a `bc59642`). **Motif unique et bloquant : `VOI-12` NON TENUE.** **Hors ce motif, ce paquet serait VALIDE sans reserve** : 12 items sur 12 justes a l'oreille, 6 oui / 6 non, aucune graphie sous les yeux de l'enfant. Le refus est ecrit ci-dessous et n'est pas efface.
- **Arbitrage prealable de `nadia-prof` rendu** : `squad/conformite/ARBITRAGE-PREALABLE-s26-la-
  voix-est-un-adulte.md`, section 4.2 (acceptation - *"celle ou la voix humaine est le mieux
  justifiee"*) et sections 5, 6, 7, 13 (conditions transverses aux cinq paquets). `DEC-0068` et
  son amendement portent l'arbitrage de l'utilisatrice qui rend ce moyen possible.
- **Point ouvert, non tranche par cette fiche** : `sofia-sm` a route a `nadia-prof` un doute
  d'`ines-design` sur sa proposition prealable d'ecran (`squad/design/proposition-ines-s26-ecran-
  voix.md`, section 6, point 4) : sa consigne d'exemple pour cette ligne ecrit le grapheme cible
  (`« ch »`), ce qui pourrait defaire le motif meme de la ligne - detacher le son de la lettre.
  **Tant que `nadia-prof` n'a pas repondu, aucun item de ce paquet n'affiche a l'enfant de lettre
  correspondant au son cherche**, dans la consigne comme ailleurs - lecture prudente par defaut,
  pas un arbitrage de `camille-po`.
- **Derniere mise a jour** : 2026-09-01 (**verdict de `nadia-prof` apres avoir joue**, s26).

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CP (cycle 2) | Langage oral et conscience phonologique | Identifier un son (phoneme) dans un mot | `squad/programme/francais/competences-cp.md` |

**Une seule ligne de matrice visee.**

## Criteres pedagogiques attendus - conditions de `nadia-prof`, reprises telles quelles

- [ ] **`VOI-5` - NON GARDEE.** L'adulte n'appuie pas le son cherche. Ni accentuation, ni
  allongement, ni pause autour de lui. *Aucun test ne peut le verifier. La seule contrepartie est
  la phrase de `VOI-12` sur ce paquet : "Dites le mot normalement, sans appuyer sur aucun son."*
- [ ] **`VOI-6`** - Sur l'ensemble du paquet, le son cherche est present dans le mot sur une part
  des items et absent sur une autre, aucune des deux n'etant majoritaire, et sa position (quand il
  est present) varie.
- [ ] **Point ouvert** - la consigne de l'enfant nomme-t-elle le grapheme cible par sa lettre, ou
  designe-t-elle le son uniquement par la voix de l'adulte ? Reponse due de `nadia-prof` avant
  l'ecriture du premier item (voir ci-dessus).
- [ ] **`VOI-1`** - L'adulte prononce le stimulus (le mot) ; il ne prononce jamais une proposition.
- [ ] **`VOI-10`** - Le texte que l'adulte prononce est un champ distinct, et il ne contient jamais
  une proposition ni la reponse.
- [ ] **`VOI-12` (exigence bloquante)** - L'ecran dit a l'adulte ce qu'il ne doit pas faire, en une
  phrase, a l'endroit ou il lit son texte, jamais derriere un clic. Cinq points verifiables
  (arbitrage, section 13.1). Phrase candidate rendue par `nadia-prof` pour ce paquet : *"Dites le
  mot normalement, sans appuyer sur aucun son."*
- [ ] **`VOI-13`** - Le mot travaille est ecrit une seule fois a l'ecran, dans le texte de l'adulte,
  et nulle part ailleurs. *Calculable pour cette moitie ; comptee comme la quatrieme condition non
  gardee pour le reste.*
- [ ] Le paquet compte au plus **20 items** (borne de `nadia-prof`, addendum section 12.2).

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. Il reste vide tant qu'aucun
> increment n'a ete livre et joue. La reponse au point ouvert ci-dessus est due avant tout item
> ecrit, pas seulement avant ce bloc.

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

**`VOI-12` est mon seul motif sur ce paquet.** Tout le reste est verifie et tenu.

#### Ce que j'ai verifie sur ce paquet, et qui est TENU

**Les 12 items, dits a voix haute.** Un exercice de conscience phonologique ne se juge pas en
lisant.

| Verification | Mesure | Etat |
|---|---|---|
| **Exactitude** - le son cherche est-il reellement present, ou reellement absent ? | **12 sur 12 justes**, verifie a l'oreille. `manteau`/`an` oui ; `velo`/`an` non ; `ballon`/`on` oui ; `tapis`/`on` non ; `singe`/`in` oui ; `chocolat`/`in` non ; `renard`/`a` oui ; `souris`/`a` non ; `souris`/`i` oui ; `chapeau`/`i` non ; `toupie`/`ou` oui ; `sapin`/`ou` non | **TENU** |
| **`VOI-6`** - present et absent, aucune part majoritaire, position variable | **6 oui / 6 non**, exactement la moitie. Position du son quand il est present : debut 3, milieu 1, fin 2. **Repondre toujours "oui" rend 50 %**, donc rien | **TENU** |
| **`VOI-13`** (corrigee) | **56 ecrans inspectes, 0 ecart** | **TENU** |
| **L'enfant bloque peut avancer** | indice apres erreur, revelation ensuite | **TENU** |

#### La question de `camille-po` sur la graphie affichee - **je confirme la lecture prudente**

`camille-po` a pose par defaut qu'**aucune lettre du son cherche n'apparait dans ce que l'enfant
lit**, et me demande de confirmer ou de corriger. **Je confirme, et le motif est celui de mon
acceptation de cette ligne** (arbitrage, section 4.2) : *"`eau`, `au`, `o` sont un son et trois
graphies. Un exercice ecrit enseigne le contraire de ce que la ligne nomme."*

**Ecrire `an` sous les yeux de l'enfant lui donnerait une graphie pour un son qui en a plusieurs**,
le jour meme ou on lui demande de detacher le son de la lettre. La lecture prudente n'est pas une
precaution : **c'est la condition d'existence de cette ligne.** Je verifie sur l'increment que
l'enonce, la consigne, les propositions et l'indice n'en portent aucune : **c'est le cas.**

*Consequence que je nomme plutot que de la laisser decouvrir : le son cherche est ecrit dans le
texte de l'adulte (`"Entends-tu le son an ?"`). Un enfant qui lit par-dessus l'epaule y voit la
graphie. **C'est la meme exposition non gardee que `VOI-13`, appliquee au son au lieu du mot**, et
c'est un motif de plus pour la phrase manquante - pas une condition nouvelle.*

#### Ce qui gagnerait sans la phrase manquante

**Un enfant qui lit *"Le mot est manteau. Entends-tu le son an ?"* voit `an` dans `manteau` et
repond oui sans ecouter** - et il apprend au passage exactement le contraire de la ligne : que le
son est la lettre.

#### Ce dont je reste seule garde sur ce paquet

- **`VOI-5` - NON GARDEE.** Que l'adulte n'appuie pas le son cherche, ne l'allonge pas, ne marque
  pas de pause autour de lui. Sa phrase est bonne : *"Dites le mot normalement, sans appuyer sur
  aucun son."*
- **Que le mot fasse ou ne fasse pas le son annonce.** Aucun test ne sait prononcer ; je viens de
  le faire douze fois.

#### Verdict s'il n'y avait pas `VOI-12`

**VALIDE, sans reserve.**

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

#### Ce que je n'ai pas eu a rejouer, et pourquoi

Le delta ne touche ni ce paquet ni sa mecanique : **seule `consigneAdulte` a change**. Les 12 items,
leur exactitude a l'oreille et le 6 oui / 6 non restent verifies par mon audit du matin, plus haut.
**Je l'ai neanmoins rejoue** pour le point 1 : 40 ecrans jouables, **0 ecart** sur `VOI-13`, et
aucune graphie du son cherche dans ce que l'enfant lit.

**Verdict : VALIDE.** C'est le verdict conditionnel que j'avais ecrit, et la condition est remplie.

**Ligne de matrice** : *"Identifier un son (phoneme) dans un mot"* peut passer a `couvert-valide`.

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
