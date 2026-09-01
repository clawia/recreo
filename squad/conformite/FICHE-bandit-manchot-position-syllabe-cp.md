# Fiche de conformite - bandit-manchot-position-syllabe-cp

- **Mecanique** : bandit manchot ; champ vocal (champ d'item, tranche par `theo-archi` -
  `ADR-0075`, qui amende `ADR-0032` sur son critere ; la branche mecanique de `DEC-0067`,
  amendement, sections B.5 et B.7, ne s'est pas realisee).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-position-syllabe.yaml`
  (a creer), story `S26-L1` (`docs/backlog/backlog.md`). Aucun fichier de `contenu-repris/` ne
  porte cette forme (voix, sans mot ecrit) ; ecriture neuve.
- **Entree de catalogue** : `bandit-manchot-position-syllabe-cp` (identifiant propose, a confirmer
  par le developpeur).
- **Statut global** : **REFUSE** (Done pedagogique **non prononce**, 2026-09-01, sur `round-two` a `bc59642`). **Motif unique et bloquant : `VOI-12` NON TENUE** - la cinquieme phrase d'adulte exigee par la section 14.3 de l'arbitrage manque. **Hors ce motif, ce paquet serait VALIDE sans reserve** : 18 items sur 18 justes a l'oral, 6/6/6, cinq signaux corriges et reverifies a voix haute. Le refus est ecrit ci-dessous et n'est pas efface.
- **Arbitrage prealable de `nadia-prof` rendu** : `squad/conformite/ARBITRAGE-PREALABLE-s26-la-
  voix-est-un-adulte.md`, section 4.1 (acceptation) et sections 5, 6, 7, 13 (conditions
  transverses aux cinq paquets). `DEC-0068` et son amendement portent l'arbitrage de l'utilisatrice
  qui rend ce moyen possible.
- **Derniere mise a jour** : 2026-09-01 (**verdict de `nadia-prof` apres avoir joue**, s26).

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CP (cycle 2) | Langage oral et conscience phonologique | Localiser une syllabe dans un mot (debut / milieu / fin) | `squad/programme/francais/competences-cp.md` |

**Une seule ligne de matrice visee.**

## Criteres pedagogiques attendus - conditions de `nadia-prof`, reprises telles quelles

- [ ] **`VOI-3` - NON GARDEE.** L'adulte dit le mot d'un seul tenant, a vitesse normale. Aucune
  scansion syllabique, aucune pause a l'interieur du mot. *Aucun test, aucune capture, aucune
  relecture ne peut le verifier - c'est un humain dans la piece qui la tient, ou personne. La seule
  contrepartie est la phrase de `VOI-12` sur ce paquet : "Dites le mot normalement, sans le
  couper."*
- [ ] **`VOI-4`** - Les mots comptent deux ou trois syllabes, et la syllabe cherchee occupe les
  trois positions (debut, milieu, fin) sur l'ensemble du paquet, aucune n'etant majoritaire. Unite
  de compte : l'item.
- [ ] **`VOI-1`** - L'adulte prononce le stimulus (le mot, et la syllabe cherchee) ; il ne
  prononce jamais une proposition.
- [ ] **`VOI-10`** - Le texte que l'adulte prononce est un champ distinct, et il ne contient jamais
  une proposition ni la reponse.
- [ ] **`VOI-12` (exigence bloquante)** - L'ecran dit a l'adulte ce qu'il ne doit pas faire, en une
  phrase, a l'endroit ou il lit son texte, jamais derriere un clic. Cinq points verifiables
  (arbitrage, section 13.1). Phrase candidate rendue par `nadia-prof` pour ce paquet : *"Dites le
  mot normalement, sans le couper."*
- [ ] **`VOI-13`** - Le mot travaille est ecrit une seule fois a l'ecran, dans le texte de l'adulte,
  et nulle part ailleurs (ni consigne, ni enonce, ni proposition, ni indice, ni texte alternatif,
  ni ecran d'erreur, ni ecran de revelation). *Calculable pour cette moitie ; comptee comme la
  quatrieme condition non gardee pour le reste - rien n'empeche un enfant assis a cote de lire le
  texte de l'adulte par-dessus son epaule.*
- [ ] Le paquet compte au plus **20 items** (borne de `nadia-prof`, addendum section 12.2).

**Ce que cette fiche ne fait pas** : elle ne prejuge d'aucune forme de mecanique ni d'aucun rendu
d'ecran. Le materiel prealable d'`ines-design` (`squad/design/proposition-ines-s26-ecran-voix.
md`) est un candidat de forme, pas un acquis.

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

**`VOI-12` est mon seul motif sur ce paquet.** Tout le reste est verifie et tenu.

#### Ce que j'ai verifie sur ce paquet, et qui est TENU

**Les 18 items, dits a voix haute par moi, un par un.** C'est le seul moyen de juger un decoupage
syllabique, et aucun test ne sait le faire.

| Verification | Mesure | Etat |
|---|---|---|
| **Exactitude du decoupage oral** - la syllabe cherchee occupe bien la position annoncee **a l'oral** | **18 items sur 18 justes.** 3 syllabes : chocolat, domino, escargot, kangourou, cheminee, parasol, crocodile, telephone, canape, macaron. 2 syllabes : tapis, lapin, bateau, cadeau, coussin, sapin, velo, jardin | **TENU** |
| **Une seule reponse defendable par item** - la syllabe cherchee n'apparait qu'une fois dans le mot | **18 sur 18.** Verifie a l'oreille, y compris les pieges d'apparence : `cho` n'est pas `co` dans chocolat, `cro` n'est pas `co` dans crocodile, `te` n'est pas `le` dans telephone | **TENU** |
| **`VOI-4`** - deux ou trois syllabes, trois positions, aucune majoritaire | **6 debut / 6 milieu / 6 fin**, recompte par moi. 8 mots de deux syllabes, 10 de trois, **et les six items "milieu" portent tous sur un mot de trois syllabes** | **TENU** |
| **`VOI-13`** (corrigee) - le mot n'est lisible que dans le bloc adulte tant que l'item est jouable | **56 ecrans inspectes, 0 ecart** | **TENU** |
| **L'enfant bloque peut avancer** | apres une erreur, l'indice ; apres des erreurs repetees, la reponse est revelee et l'enfant peut presser. **Il n'est jamais coince** | **TENU** |
| **Enonce et propositions invariants** - ils ne distinguent pas les tours, donc ne portent rien | verifie sur toutes les parties jouees | **TENU** |

#### Mes cinq signaux du 2026-09-01 : **les cinq sont corriges, et je le verifie a voix haute**

J'avais releve `papa`, `banane`, `tomate`, `ananas` et la phrase *"Cherche le son son"* de `maison`.
**Je ne me contente pas de la parole de qui les a corriges : j'ai dit les cinq remplacants.**

| Ancien | Nouveau | Ce que je verifie moi-meme |
|---|---|---|
| `papa` / debut | `tapis` / debut | `ta-pis` : `ta` n'est pas `pi`. **La double reponse a disparu** |
| `banane` / milieu | `parasol` / milieu | `pa-ra-sol` : trois syllabes orales reelles, `ra` au milieu |
| `tomate` / fin | `canape` / fin | `ca-na-pe` : la finale orale est bien `pe`, aucun `e` muet |
| `ananas` / fin | `macaron` / fin | `ma-ca-ron` : aucune lettre finale muette, `ron` s'entend |
| `maison` / *"le son son"* | `coussin` / `sin` | l'adulte lit *"Cherche le son sin"* : **plus de bafouillage** |

**Aucun des cinq remplacants n'introduit le defaut qu'il repare**, et j'ai reverifie les treize
autres items dans la meme passe.

#### Ce qui gagnerait sans la phrase manquante, et c'est pourquoi elle bloque

**Un enfant qui lit *"Le mot est sapin. Cherche le son pin."* voit `pin` a la fin de `sapin` et
repond "fin" sans rien entendre.** C'est mot pour mot le motif de mon refus du 2026-08-31 sur la
forme ecrite, revenu par un texte que j'ai moi-meme fait ajouter.

#### Ce dont je reste seule garde sur ce paquet

- **`VOI-3` - NON GARDEE.** Que l'adulte ne scande pas les syllabes. Aucun test, aucune capture. La
  seule contrepartie est sa phrase, et elle est bonne : *"Dites le mot normalement, sans le
  couper."*
- **Que chaque decoupage soit celui du francais parle**, et non celui de l'ecrit. Rien ne le
  verifiera a ma place : c'est ce que je viens de faire item par item.

#### Verdict s'il n'y avait pas `VOI-12`

**VALIDE, sans reserve.** Je l'ecris pour que la levee du blocage n'oblige personne a refaire cet
audit : **hors `VOI-12`, ce paquet n'a aucun motif contre lui.**
