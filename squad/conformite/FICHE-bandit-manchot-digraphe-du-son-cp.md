# Fiche de conformite - bandit-manchot-digraphe-du-son-cp

- **Mecanique** : bandit manchot ; champ vocal (champ d'item, tranche par `theo-archi` -
  `ADR-0075`, qui amende `ADR-0032` sur son critere ; la branche mecanique de `DEC-0067`,
  amendement, sections B.5 et B.7, ne s'est pas realisee).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-digraphe-du-son.yaml`
  (a creer), story `S26-L4` (`docs/backlog/backlog.md`). Matiere premiere de forme uniquement,
  refusee comme contenu : `contenu-repris/mots/cp-le-bon-son.yml` (version d'origine, 6 sons
  melanges, refusee par `nadia-prof` le 2026-08-06 - le son `ch` y est bonne reponse 4 fois et
  distracteur 0 fois, `P(bonne / presente) = 100 %`, addendum section 11.2).
- **Entree de catalogue** : `bandit-manchot-digraphe-du-son-cp` (identifiant propose, a confirmer
  par le developpeur).
- **Statut global** : **REFUSE** (Done pedagogique **non prononce**, 2026-09-01, sur `round-two` a `bc59642`). **Motif unique et bloquant : `VOI-12` NON TENUE.** **Hors ce motif, ce paquet serait VALIDE sans reserve** : 15 items sur 15 justes, 33,3 % par digraphe, aucune reponse ambigue, aucune signature. **C'est le plus propre des cinq.** Le refus est ecrit ci-dessous et n'est pas efface.
- **Ce que cette fiche est, et ce qu'elle n'est pas seule a fermer** : cette story couvre **la
  clause 2** (`ou`, `oi`, `ch`) de la ligne "Associer les graphemes aux phonemes". **La ligne ne
  passe `couvert-valide` que si cette fiche ET `FICHE-bandit-manchot-lettre-du-son-cp.md`
  (clause 1, `S26-L3`) recoivent toutes deux un verdict `VALIDE`** (`nadia-prof`, addendum,
  section 11.6) - livrer l'une sans l'autre ne fait bouger aucune ligne de matrice.
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
(clause 3) et `bandit-manchot-lettre-du-son-cp` (clause 1).**

## Criteres pedagogiques attendus - conditions de `nadia-prof`, reprises telles quelles

- [ ] Espace de reponse : **exactement trois digraphes stables** - `ou`, `oi`, `ch` - jamais un
  autre.
- [ ] **Condition de distribution (sans numero attribue par `nadia-prof`, citee mot pour mot,
  addendum section 11.4)** - "chacun des trois est reponse sur un tiers des items".
- [ ] **`VOI-7`** - L'adulte dit le mot entier, jamais le grapheme isole.
- [ ] **`VOI-1`** - L'adulte prononce le stimulus (le mot ou le son) ; il ne prononce jamais une
  proposition.
- [ ] **`VOI-10`** - Le texte que l'adulte prononce est un champ distinct, et il ne contient jamais
  une proposition ni la reponse.
- [ ] **`VOI-12` (exigence bloquante)** - L'ecran dit a l'adulte ce qu'il ne doit pas faire, a
  l'endroit ou il lit son texte. Phrase candidate rendue par `nadia-prof` (interdit rattache a
  `VOI-7`, identique a celle de `bandit-manchot-lettre-du-son-cp`) : *"Dites le mot entier, jamais
  le son tout seul."*
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
> ligne de matrice tant que `FICHE-bandit-manchot-lettre-du-son-cp.md` n'a pas elle aussi recu
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

**`VOI-12` est mon seul motif sur ce paquet.** Tout le reste est verifie et tenu.

#### Ce que j'ai verifie sur ce paquet, et qui est TENU

**Les 15 items, dits a voix haute.**

| Verification | Mesure | Etat |
|---|---|---|
| **Exactitude** - le mot fait-il le son annonce ? | **15 sur 15.** `ou` : loup, bijou, genou, hibou, toupie. `oi` : roi, toit, noix, poisson, armoire. `ch` : chat, chien, cheval, vache, chemise | **TENU** |
| **Une seule reponse defendable par item** | **15 sur 15 : aucun mot ne contient deux des trois digraphes.** Mesure faite item par item - c'est exactement le controle qui echoue sur le paquet voisin `cp-lettre-du-son`, et ici il passe sans exception | **TENU** |
| **Distribution** (ma condition : *"chacun des trois est reponse sur un tiers des items"*) | **5 / 5 / 5, soit 33,3 % chacun.** C'est la repartition exacte qui a assaini `cp-le-bon-son` a s06 apres mon refus du 2026-08-06 | **TENU** |
| **Aucune signature** - `P(bonne / presente)` | chacun des trois est reponse 5 fois et distracteur 10 fois. **Aucun n'est jamais-distracteur**, le defaut que j'ai mesure a 100 % sur `ch` dans le paquet d'origine (addendum, 11.2) | **TENU** |
| **`VOI-7`** - le mot entier, jamais le digraphe seul | verifie sur toutes les parties | **TENU** |
| **`VOI-13`** (corrigee) | **56 ecrans inspectes, 0 ecart** | **TENU** |
| **L'enfant bloque peut avancer** | apres six erreurs sur `genou`, l'ecran affiche *"C'est ou. Appuie sur ou."* et l'enfant peut presser | **TENU** |

#### Un point que j'avais route et que je ferme moi-meme

En section 12.4 de mon addendum, j'avais nomme un chemin jamais audite : *"l'ecran de revelation de
`M5` affiche la bonne reponse, et un adulte qui le lit la dit a voix haute."*

**Je l'ai provoque et je l'ai regarde.** La revelation - *"C'est ou. Appuie sur ou."* - est **dans la
zone de l'enfant**, et le bloc adulte reste inchange : *"Le mot est genou."* + *"Dites le mot entier,
jamais le son tout seul."* **On ne donne pas la reponse a lire a l'adulte.** Le chemin que je
craignais n'existe pas sur cet increment, et je le ferme.

#### Ce qui gagnerait sans la phrase manquante

**Un enfant qui lit *"Le mot est chat."* voit `ch` et presse `ch`.** C'est le motif exact pour lequel
j'avais ferme les digraphes transparents a l'ecrit le 2026-08-06 : *"sans son, un digraphe
transparent ne peut donner lieu qu'a une recherche de lettres."*

#### Consequence sur la ligne de matrice

**La ligne ne bouge pas**, et pas a cause de ce paquet : sa moitie 1 (`cp-lettre-du-son`) porte un
motif propre. **Les deux moities ferment la ligne ensemble ou pas du tout** - mon fait de
composition, addendum section 11.6.

#### Ce dont je reste seule garde sur ce paquet

- **`VOI-7` a l'oral - NON GARDEE.** Qu'un adulte ne dise pas *"che"* devant trois graphies dont
  l'une est `ch`. Le test garde le texte ecrit, pas la bouche. Sa phrase est bonne.

#### Verdict s'il n'y avait pas `VOI-12`

**VALIDE, sans reserve.** C'est le paquet le plus propre des cinq.
