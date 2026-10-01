# Fiche de conformite - bandit-manchot-usages-numeriques-cm1

- **Mecanique** : bandit manchot.
- **Contenu** :
  `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-usages-numeriques.yaml`
  (12 items, type `question`), ecrit par `sofia-sm`, casquette dev (commit `c5b96350`, fusion
  `16b4d799`).
- **Entree de catalogue** : `bandit-manchot-usages-numeriques-cm1`, titre servi *"Se protéger en
  ligne"*, consigne servie *"Lis bien, puis dis la bonne pratique, ou pourquoi c'est risqué."*,
  `nombreDeManches: 10`.
- **Ligne declaree** : `SCI-CM1-14`. **Story** : `S45-SCI-NUMERIQUE` (`docs/backlog/backlog.md`,
  section "Sprint 45", corrigee par `camille-po` le 2026-10-01).
- **Conditions opposables** : `SCI-1` (version du 2026-09-06), `SCI-2` (par extension), `SCI-3`,
  `SCI-4`, `HIS-3`, `CON-1`, `FER-1` a `FER-3`
  (`squad/rapports/avis-faisabilite-s45-cinq-lots.md`, section 8.1).
- **Statut global** : **REFUSE**, prononce le 2026-10-01 par `nadia-prof` (s45, instance B,
  `DEC-0101`). Fiche ouverte par ce verdict : aucune fiche n'existait pour ce jeu.
- **Derniere mise a jour** : 2026-10-01.

---

## Bloc de validation pedagogique

### Verdict du 2026-10-01 (`nadia-prof`, s45, instance B) : **REFUSE**

**Done pedagogique non prononce.** `SCI-CM1-14` reste `non couvert`. Le refus tient a deux faits
inexacts enseignes comme des regles (section 5). Toutes les conditions ecrites d'avance sont
tenues a la lettre (section 3) : ce n'est pas une condition manquee qui refuse, c'est le contenu.

#### 1. Ce que j'ai fait

**Audit complet, pas degrade.** Jar construit depuis `round-two` a `9fcae7dc` (JDK 21 de la
toolchain), lance en local, joue par requetes successives dans une meme session HTTP. **Cinq
parties completes** de dix manches : une en repondant faux une fois puis juste, une en epuisant les
trois essais jusqu'a la revelation, trois en repondant juste du premier coup. **Les douze items ont
ete tires et joues.** L'indice s'affiche des la premiere erreur, sous *"Ce n'est pas ça. Essaie
encore !"*, et reste affiche a la revelation (*"Appuie sur la bonne réponse : ..."*). L'ecran de fin
rappelle l'item manque et sa bonne reponse. Les propositions changent d'ordre entre deux essais.

#### 2. Ancrage au programme

`SCI-CM1-14` : *"Connaître quelques règles d'usage d'un environnement numérique (identification,
courriel, recherche d'une information, protection de ses données)."* Tache A (items 1 a 6) :
reconnaitre la bonne pratique. Tache B (items 7 a 12) : dire pourquoi une conduite est risquee.
**C'est bien cette competence**, sur les connaissances seules, comme mon jugement de faisabilite
l'exigeait : aucun item ne demande un geste dans un environnement reel.

#### 3. Conditions, verifiees une par une

Comptes faits par script sur le YAML servi, et non repris du commentaire d'en-tete.

| Condition | Tenue | Ce que j'ai verifie |
|---|---|---|
| `SCI-1` | **oui, a la lettre** | Douze indices d'une phrase, au present, aucun ne contient sa bonne reponse ni *"ta réponse"*. Sur les items dont un distracteur porte une conception fausse courante (1, 4, 5, 6 : accepter sans lire, croire le premier resultat, un seul mot de passe, un mot de passe court), l'indice dit pourquoi elle est fausse. Trois defauts d'indice restent : points b et c de la section 7 |
| `SCI-4` | **oui** | Deux distracteurs sur chacun des douze items |
| `SCI-3` | **oui, a la borne** | **2 items sur 12** dont la bonne reponse porte un chiffre (*"à partir de 13 ans"*, *"au moins 8 caractères"*), `floor(12/6) = 2`. Lot : 14 paquets `cm1-sciences-*`, 157 items, 2 numeriques. Le chiffre du developpeur est exact. **Ce sont precisement les deux items faux** (section 5) |
| `SCI-2` | **oui, extension confirmee** | Voir section 4 |
| `FER-1` | **oui** | Quatre elements sur quatre en bonne reponse : identification (1, 2, 6, 7), courriel (3, 8), recherche (4, 9), donnees (5, 10, 11, 12) |
| `FER-2`, `FER-3` | **oui** | 12 items pour 11 exiges ; deux taches de 6 items chacune |
| `HIS-3` | **oui sur la forme, non sur deux faits** | Douze marqueurs `# HIS-3:` non vides, aucun `HIS-1`. Forme *"regle de prudence numerique courante"* admise comme *"fait consensuel"* sur les paquets valides. Mais deux marqueurs declarent consensuelle une valeur qui ne l'est pas, et l'un contredit son propre enonce : section 5 |
| `CON-1` | **oui, item par item** | Propositions masquees, aucun item ne se repond a sa premiere rencontre sans l'enonce. Deux indices de surface existent sur la suite de la partie : points d et e de la section 7 |
| `CYC-1` (adjacence `QLM-CP-08`) | **aucune collision** | `cp-qlm-objets-numeriques.yaml` relu cote a cote : aucune chaine identique entre les deux paquets ; seul recouvrement lexical, *"demande"* et *"pourquoi"* entre deux enonces. Le CP porte sur le materiel (recharge, chute, eau, son, ecran dans le noir) et l'aide d'un adulte pour un reglage ; le CM1 sur l'inscription, le courriel, la recherche et les donnees. Les items 1 et 3 retiennent aussi un adulte, mais pour un autre objet (le site, le destinataire) |

#### 4. `SCI-2` sur cette ligne : l'extension est defendable, sous une lecture que je fixe

Le libelle source, *"aucun item ne fait porter une responsabilite a l'enfant ni a son foyer"*, ne
se transpose pas a la lettre : une regle d'usage est par definition une conduite de l'enfant. La
lecture que le paquet applique, **aucun item n'impute une faute a l'enfant ni a son foyer**, est
celle que je retiens pour `SCI-CM1-14`. Elle est tenue : les douze enonces sont impersonnels
(*"quelle est la bonne pratique"*, *"pourquoi est-il risqué de"*), aucun ne dit *"tu"*, aucun ne
suppose une faute deja commise. Extension confirmee pour cette ligne, sous cette lecture, et pour
elle seule.

#### 5. Motif du refus : deux regles fausses ou perimees, enseignees comme vraies

Une erreur factuelle dans un contenu scolaire refuse a elle seule. Il y en a deux, et ce sont les
deux items qui portent une valeur a retenir.

**Motif 1 - Item 2, l'age : la question est a l'envers, et le paquet se contredit lui-meme.**
L'enonce demande *"À partir de quel âge un service numérique demande-t-il en général l'accord d'un
parent ?"* et la bonne reponse est *"à partir de 13 ans"*. Un service demande l'accord d'un parent
**en dessous** d'un age, jamais a partir de lui : l'enfant apprend qu'a 13 ans on commence a
demander a ses parents. Le marqueur `HIS-3` de la meme ligne dit l'inverse de l'enonce (*"seuil
d'age courant pour la creation d'un compte **sans** accord parental"*) : le defaut se lit dans le
fichier, sans source exterieure. **Et la valeur n'est pas celle de la France** : 13 ans est l'age
minimal fixe par les conditions de nombreux services etrangers ; en France, l'accord conjoint d'un
parent est requis pour un mineur de moins de 15 ans (loi Informatique et Libertes, article 45, dans
sa redaction de la loi du 20 juin 2018). Le distracteur *"à partir de 18 ans seulement, jamais
avant"* herite de la meme forme a l'envers.

**Motif 2 - Item 6, le mot de passe : la longueur enseignee comme solide ne l'est plus.** La bonne
reponse *"un mot de passe d'au moins 8 caractères, avec des lettres et des chiffres"* est donnee
comme la regle d'un mot de passe solide. La recommandation de la CNIL du 21 juillet 2022, reprise
par cybermalveillance.gouv.fr, fixe **12 caracteres au moins, de quatre types** (majuscules,
minuscules, chiffres, caracteres speciaux) pour un mot de passe seul ; 8 caracteres n'y suffisent
qu'avec des mesures du service que l'enfant ne voit pas. Le marqueur `HIS-3` dit *"longueur
minimale recommandee"* sans nommer qui la recommande : le fait n'est pas verifiable contre sa
source, et verifie contre la source francaise, il est faux.

**Methode, dite pour qu'on puisse la contester** : les deux references francaises ci-dessus sont
citees de ma connaissance, sans avoir ete rouvertes pendant cet audit (aucun acces reseau depuis la
session). L'auteure de la reprise les ouvre et les cite dans le marqueur `HIS-3` de chaque item.
Si elles disent autre chose que ce que j'ecris, c'est ma fiche qui se corrige, et je le dirai.

#### 6. Ce qu'il faut corriger pour que je reprononce - propriete a tenir

1. **Item 2** : soit l'enonce porte le seuil dans son vrai sens et sa vraie valeur pour la France
   (en dessous de 15 ans, l'accord d'un parent est demande), avec des distracteurs de meme forme ;
   soit l'item ne porte plus de valeur et demande la regle seule (ce qu'il faut avant de creer un
   compte). Dans les deux cas, enonce, bonne reponse, distracteurs et marqueur `HIS-3` disent le
   meme fait.
2. **Item 6** : la bonne reponse est conforme a la recommandation francaise en vigueur, ou ne porte
   plus de longueur chiffree (*long et varie* plutot qu'un nombre). Le marqueur `HIS-3` nomme
   l'autorite et la date de la recommandation.
3. **Sur les deux items ou une valeur reste** : le marqueur `HIS-3` nomme sa source. *"Regle
   courante"* suffit pour une regle de conduite sans chiffre, pas pour un seuil ou une longueur.
4. `SCI-3` reste tenue apres la reprise : retirer un chiffre n'est jamais un defaut, en ajouter un
   l'est des 13 items.

**Seuls ces deux motifs bloquent la reprononciation.** Les points de la section 7 se corrigent
dans la meme reprise s'il est commode de le faire ; s'ils restent, ils deviendront des reserves
datees au second audit, et ne seront pas changes en motifs de refus.

#### 7. Points a reprendre, non bloquants

- **a. Items 4 et 9 enseignent la meme regle.** *"Comparer plusieurs sources"* (4) et *"une
  information peut être fausse ou dépassée, même bien classée"* (9) ; le distracteur 1 de l'item 4
  est la conduite que l'item 9 interroge, et les deux indices disent la meme chose (*"Le classement
  ... ne garantit pas ..."*). Joues dans la meme partie dans les deux parties dont j'ai garde le
  detail manche par manche : le second se repond par le premier. Une autre regle de recherche (qui a ecrit le texte, sa date) donnerait
  deux connaissances au lieu d'une.
- **b. Items 3 et 5 : l'indice parle d'un seul distracteur.** Item 3, l'enonce porte sur l'envoi
  (*"Avant d'envoyer un courriel"*) ; un distracteur et l'indice portent sur la reponse a un
  courriel recu (*"Répondre à un inconnu confirme que l'adresse est utilisée"*). Joue : apres le
  distracteur *"envoyer le message à toute une liste de contacts inconnus"*, l'indice affiche ne
  parle de rien de ce que l'enfant a fait ni de ce qu'on lui demande. Item 5, l'indice porte sur le
  mot de passe reutilise ; il ne dit rien a l'enfant qui a choisi *"partager sa photo et son
  adresse"*, et *"percé"* n'est pas un mot du CM1 dans ce sens. L'indice doit etre vrai et utile
  quel que soit le bouton presse (`SCI-1`, *"ecrit pour etre vrai quel que soit le bouton
  pressé"*).
- **c. Tache B : trois indices redisent la bonne reponse.** Item 8 (*"Un programme caché dans une
  pièce jointe ..."* face a *"elle peut contenir un programme ..."*), item 10 (*"révèle un endroit
  précis à des personnes inconnues"*), item 7 (*"facilite la devinette"* face a *"servir à
  deviner"*). Aucun ne contient la chaine de la bonne reponse, donc le test passe ; tous la
  designent. Famille de `M21`, au meme regime que `HG-R9` : non bloquant, l'indice n'est lu
  qu'apres une erreur.
- **d. Tache B : le mot "peut" designe la bonne reponse.** Six bonnes reponses sur six portent
  *"peut"* (*"peut servir"*, *"peut contenir"*, *"peut être"*, ...), un distracteur sur douze. Plus
  largement, 11 distracteurs sur 24 portent un absolu (*"toujours"*, *"jamais"*,
  *"automatiquement"*, *"aucun"*, *"n'importe quel"*), une bonne reponse sur 12. Un enfant de CM1
  qui l'a remarque au deuxieme item presse sans lire. Meme famille qu'`EVO-CM1-R1` : qu'aucune
  amorce ni aucun mot ne soit propre aux bonnes reponses ou aux distracteurs.
- **e. Deux distracteurs sont vrais.** Item 10, *"la batterie de l'appareil se vide plus vite"* :
  c'est exact pour une localisation en continu, meme si ce n'est pas un risque. Item 7, *"son écran
  affiche une publicité différente"* : l'age declare sert reellement a choisir les publicites. Un
  enfant qui le sait est sanctionne pour une reponse vraie (famille d'`EVO-CM1-R3`). Un distracteur
  doit etre faux, pas seulement hors sujet.
- **f. Item 7 suppose qu'on donne une fausse date de naissance.** *"Pourquoi est-il risqué de
  donner sa **vraie** date de naissance"* laisse entendre que l'alternative est d'en donner une
  fausse, dans le paquet meme ou l'item 2 enseigne que l'age conditionne l'acces. Formuler sur le
  fait de la donner ou non, pas sur sa verite.

#### 8. Ce dont je reste la seule garde

`PaquetCm1SciencesUsagesNumeriquesTest` tient la presence des marqueurs `FER-1` et `HIS-3`, le
nombre d'items et de distracteurs, la phrase unique de l'indice, l'absence de la chaine de la bonne
reponse et de *"ta réponse"* dans l'indice, la borne `SCI-3` et un lexique de `SCI-2`. **Aucun
automate ne verifie** : qu'un fait soit exact ou que le marqueur `HIS-3` dise le meme fait que son
enonce (les deux motifs de refus sont passes avec une CI verte) ; qu'un indice paraphrase la bonne
reponse ou parle d'un autre distracteur ; qu'un mot soit propre aux bonnes reponses ; que deux
items enseignent la meme regle ; qu'un distracteur soit vrai. Ces proprietes n'ont ete verifiees
qu'ici, en jouant.

#### 9. Ce que je route

A `sofia-sm` : la reprise du paquet sur les deux motifs de la section 5, selon les proprietes de la
section 6 ; les points de la section 7 dans la meme reprise si possible. A `camille-po`, par
`sofia-sm` : `SCI-CM1-14` reste `non couvert` ; le refus est a reprendre en backlog, il ne bloque
pas le sprint suivant. La conclusion est a reprendre dans `review.md` de s45.
