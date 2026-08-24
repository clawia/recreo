# Fiche de conformite - bandit-manchot-antonymes-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/cm1-antonymes.yaml`
  livre, `type: question`, 17 items (dix-huit proposes, un retire - detail dans le bullet
  "Arbitrage prealable de nadia-prof RENDU" ci-dessous), story `S19-1` (`docs/backlog/backlog.md`). Entree de catalogue
  `bandit-manchot-antonymes-cm1`, titre "Les antonymes", consigne "Quel mot veut dire le contraire
  du mot entre guillemets ?", 10 manches. Tests : `PaquetCm1AntonymesTest.java`.
  **Aucune matiere premiere reutilisable** : `contenu-repris/associer/cm1-synonymes.yml` (12 paires
  mot/synonyme) ne contient aucun antonyme, verifie ligne par ligne et confirme par `DEC-0039`
  ("verifie par recherche du mot 'antonyme' et de ses derives dans `contenu-repris/` : aucun
  fichier"). Le paquet est integralement neuf.
- **Decision de forme - jeu distinct de "Les synonymes", pas une extension** : l'entree de
  catalogue `bandit-manchot-synonymes-cm1` porte, depuis `ADR-0052`, une consigne generique et
  fixe ("Quel mot peut remplacer le mot entre guillemets sans changer le sens ?") qui serait fausse
  pour un item d'antonyme. `nadia-prof` qualifie elle-meme les antonymes d'"autre operation", pas
  d'un prolongement du meme geste (`DEC-0039`, section 3). Detail complet du raisonnement :
  `docs/backlog/backlog.md`, story `S19-1`.
- **Statut global** : **increment livre, Done pedagogique non encore prononce.** L'arbitrage
  prealable de `nadia-prof` (voir ci-dessous) a accepte la proposition sous conditions ; les
  corrections ANT-2 ont ete appliquees dans le paquet livre. `mvn verify` vert sur le reacteur
  complet (methode et compte dans le compte rendu de `marc-dev`, non recopies ici). Rien n'a encore
  ete joue par `nadia-prof` sur cet increment : le Done pedagogique reste entier.
- **Arbitrage prealable de `nadia-prof` RENDU** : `ARBITRAGE-PREALABLE-s19-antonymes-cm1-et-
  nature-du-mot-cp.md`, partie A, 2026-08-24 - **ACCEPTE SOUS CONDITIONS** (`ANT-1` a `ANT-7`).
  Cinq des dix-huit items proposes ont ete repris ou retires (`ANT-2`) :
  - item "silencieux"/"bruyant" (defaut bloquant, deux reponses vraies : le distracteur "anime"
    etait lui-meme un antonyme acceptable) - distracteur remplace par "ancien" ;
  - item "plein"/"vide" (la substitution ne produisait pas du francais avec "d'eau") - "d'eau"
    retire de l'enonce ;
  - item "commencer"/"finir" (distracteurs "durer"/"plaire" ne tenaient pas dans la phrase) -
    remplaces par "reprendre"/"passer" ;
  - item "gagner"/"perdre" (meme motif) - distracteurs "arbitrer"/"applaudir" remplaces par
    "jouer"/"disputer" ;
  - item "jour"/"nuit" (le determinant ne suivait pas le genre de la bonne reponse) - **retire**,
    dix-sept items restants, plancher de seize tenu (`ANT-4`).
  Les treize autres items sont livres sans changement, deja juges "justes" par `nadia-prof` dans son
  arbitrage. Les conditions `ANT-1` (test de substitution) et `ANT-3` (aucune erreur de fait)
  restent un jugement de sens que seul `nadia-prof` porte, a l'audit de l'increment livre.
- **Arbitrage prealable de mecanique en vigueur pour tout le catalogue** (`squad/conformite/
  FICHE-bandit-manchot-additions-cp.md`, conditions M1 a M13) et **forme deja validee pour un jeu
  voisin** (`FICHE-bandit-manchot-synonymes-cm1.md`, conditions SYN-1 a SYN-9, reprises par
  analogie ci-dessous).
- **Derniere mise a jour** : 2026-08-24 (ouverture par `camille-po`, `DEC-0042`)

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CM1 (cycle 3) | Vocabulaire | Utiliser des synonymes et antonymes pour preciser ou nuancer un propos | `squad/programme/francais/competences-cm1.md` |

**Perimetre** : seconde moitie de cette ligne, deja ouverte pour sa moitie synonymes par
`bandit-manchot-synonymes-cm1` (`en cours`). Cette ligne ne passe a `couvert-valide` que le jour ou
les deux jeux sont chacun `VALIDE` par `nadia-prof` (`DEC-0039`, section 3).

## Criteres pedagogiques attendus - camille-po

- [ ] Chaque item presente le mot cible entre guillemets francais a l'interieur d'une phrase
  complete, jamais isole (repris de `SYN-1`).
- [ ] La bonne reponse est un antonyme reellement oppose au mot cible **dans cette phrase precise**,
  pas un antonyme du mot pris hors contexte.
- [ ] Aucun distracteur n'est lui-meme un antonyme acceptable du mot cible dans cette phrase - un
  seul contraire juste par item.
- [ ] Les espaces a l'interieur des guillemets francais sont des espaces insecables (U+00A0),
  jamais ordinaires (repris de `SYN-9`).
- [ ] Le paquet compte au moins 16 items, aucun ne produisant trois rangees de propositions a la
  reference 744 x 551 (`DEC-0040`).
- [ ] Chaque item porte un indice qui aide a trouver l'antonyme sans le nommer.
- [ ] La profondeur des phrases est de niveau CM1, pas CE1/CE2 (meme exigence que `SYN-2`/`SYN-3`).
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve - reutilise le mecanisme de rendu deja etabli par `ADR-0052` pour "Les
  synonymes".

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. Il reste vide tant qu'aucun
> increment n'a ete livre et joue. L'arbitrage prealable demande ci-dessus (validite du proxy pour
> l'operation inverse, profondeur des phrases) sera rendu ici ou dans un fichier d'arbitrage dedie,
> avant tout code.

### Verdict du 2026-08-24 (s19) - `S19-1`, "Les antonymes"

- **Auteur** : nadia-prof. **Increment** : `round-two` a `51301d7`, jar reconstruit depuis cet arbre
  et servi, application **arretee par son PID**.
- **Audit joue** : oui. **5 parties, 89 lectures, 17/17 items vus**, chacun au repos et apres un
  echec, au viewport de reference **744 x 551** (`DEC-0040`). **`scrollY = 0` verifie a chaque
  lecture : 0 lecture a defilement non nul sur 89.** Ce n'est pas un audit degrade, a la reserve de
  police pres.
- **Releve complet** : `squad/conformite/releves-s19/s19-audit-antonymes-744x551.json`. Capture :
  `s19-ant-744x551-claire-echec-rangee-unique.png`.
- **Outil** : `squad/outils/nadia-prof/auditer-paquet.py`, commite avec cette fiche.

## Verdict : **VALIDE SOUS RESERVE - aucune reserve bloquante. `SYN-4` est fermee.**

### 1. Mes sept conditions, une par une

| Condition | Etat | Ce que j'ai verifie |
|---|---|---|
| **ANT-1** substitution | **tenue, 17/17** | pour chacun des 17 items, la bonne reponse mise a la place du mot cible produit une phrase francaise correcte qui dit le contraire, et **aucun des 34 distracteurs n'est un contraire acceptable dans sa phrase** |
| **ANT-2** cinq items repris | **tenue** | item 5 (*"Ce verre est plein."*, le complement supprime), item 8 (*reprendre*/*passer*), item 9 (*jouer*/*disputer*), item 13 retire, **item 15 : *anime* remplace par *ancien*** |
| **ANT-3** aucune erreur de fait | **tenue** | les 17 couples reverifies un par un |
| **ANT-4** au moins 16 items | **tenue** | 17 items apres le retrait de l'item 13 |
| **ANT-5** guillemets et insecables | **tenue** | 17 guillemets ouvrants, **17 suivis d'une espace insecable, 17 fermants precedes d'une insecable** - aucun manquant |
| **ANT-6** au plus un antonyme par prefixe | **tenue** | un seul, *poli/impoli* |
| **ANT-7** aucun accord ne trahit la reponse | **tenue** | les trois propositions s'accordent identiquement avec le mot cible sur les 17 items |

**`ANT-2` sur l'item 15 est la condition dont j'avais ecrit qu'elle n'etait pas negociable.** Elle
est tenue : *"Ce quartier est tres 'silencieux' la nuit"* propose desormais *bruyant* / *eclaire* /
*ancien*, et **aucun des deux distracteurs n'est un contraire de *silencieux***. Je le verifie parce
que c'etait mon seul motif de refus possible sur ce paquet.

**Le critere de `camille-po` que je verifie aussi** : *"chaque item porte un indice qui aide a trouver
l'antonyme sans le nommer"*. **Tenu 17 fois sur 17** - les indices decrivent (*"comme un glacon"*,
*"tout pres du sol"*) et ne prononcent jamais le mot attendu. Cela n'a rien d'automatique : un autre
paquet de ce meme sprint fait l'inverse.

### 2. Le rendu, mesure

| Mesure a 744 x 551 | Resultat |
|---|---|
| Propositions par item | **3 sur les 17**, jamais 2 - la question du score a deux propositions ne se pose pas ici |
| Items sur deux rangees | **0 sur 17** |
| Largeur de rangee offerte | 680,00 px |
| **Largeur necessaire, maximum du paquet** | **547 px** (item *"Cette piece est claire le matin"*, propositions *sombre* / *humide* / *silencieuse*) |
| Bas du bloc de verdict, pire cas | **474,19 px** pour un pli a 551 px - **au-dessus, 17/17** |
| Bas de la boite d'indice, pire cas | 553,19 px sur **un** item |

**J'avais annonce ou regarder et j'y ai regarde** : *silencieuse*, onze lettres, est bien le mot le
plus long du paquet et il porte le maximum. **547 px contre 680 px offerts : la marge est de 133 px
a la reference, le paquet ne se replie nulle part.**

### 3. Reserves - deux, aucune bloquante

- **`ANT-8` (2026-08-24, non bloquante).** **Un item sur dix-sept perd la fin de son indice** :
  *"Le clown est toujours 'joyeux' avec les enfants."*, bas de la boite a **553,19 px** pour un pli a
  **551 px**, soit **2,19 px sous le pli**. C'est le meme defaut que `SYN-12` sur les synonymes, par
  le meme mecanisme - le pli, jamais un rognage - et je le traite **exactement comme je l'ai traite
  la** : reserve ouverte, non bloquante. Je ne durcis pas ma barre parce que le paquet est neuf.
  2,19 px est en outre sous l'incertitude de police que j'ai moi-meme nommee (15 a 35 px) : sur une
  autre pile de polices, cet item peut passer ou empirer, et je ne sais pas dans quel sens.
- **`ANT-9` (2026-08-24, non bloquante, et elle vaut surtout pour s20).** **Ce paquet demande 547 px
  la ou les synonymes en demandent 539.** Cela ne se voit pas a la reference, mais cela compte pour
  le plafond de la bande 576-719 px que `camille-po` a renvoye a s20 : **la reserve de la proposition
  d'`ines-design` a 620 px, que j'ai a trancher, n'est plus de 41 px mais de 33 px** une fois ce
  paquet compte. Elle reste largement au-dessus du risque de derive de police, et **la proposition de
  620 px couvre ce paquet-ci**. Je le pose ici pour que le chiffre soit a jour le jour ou je
  trancherai, et non redecouvert apres.

### 4. Ce que ce verdict fait, et ce qu'il ne fait pas

**`SYN-4` est fermee.** C'est la condition - la moitie "antonymes" manquante - dont j'ai ecrit a s17
puis a s18 qu'elle etait *"elle, et elle seule, ce qui empeche la ligne de matrice de passer
`couvert-valide`"*. Le paquet d'antonymes existe, il est joue, il est valide. **La ligne francais CM1
"Utiliser des synonymes et antonymes pour preciser ou nuancer un propos" n'a plus d'obstacle
pedagogique de mon fait.**

**Je ne deplace aucune ligne de matrice** : ce geste appartient a `camille-po`. Ce que je lui donne,
c'est le verdict qui le lui permet.

**Ce que ce Done ne couvre pas** : ce qu'un lecteur d'ecran annonce (jamais couvert par aucun de mes
Done), ce qu'un enfant reel comprend, et tout rendu sur une largeur, une hauteur ou une pile de
polices que je n'ai pas mesurees - notamment la bande 576-719 px, ou ce paquet se repliera comme les
synonymes tant que `S19-6a` n'est pas traitee.

**Ce que je dois a `marc-dev`, et je l'ecris parce que c'est rare** : les cinq reprises que j'avais
demandees sont faites exactement, l'item que j'avais autorise a retirer plutot qu'a forcer a ete
retire, et rien n'a ete ajoute en douce. **Un paquet livre conforme du premier coup apres un
arbitrage prealable, c'est ce que cette porte existe pour obtenir.**
