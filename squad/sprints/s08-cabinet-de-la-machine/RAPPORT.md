# [Sprint s08] Rapport de sprint - un cabinet visible autour des colonnes, la conjugaison etendue au futur et a l'imparfait

- **Debut** : 2026-08-11 11:44 (`planning.md`)
- **Fin** : 2026-08-11 20:15 (`review.md`)
- **Duree effective** : 8 heures 31 minutes

## Increment livre

L'ecran de manche montre desormais une machine reconnaissable - un cabinet visible entoure les
colonnes, le decor de levier a quitte les boutons de choix de jeu de l'accueil, et la fin du
mouvement des colonnes ne disparait plus d'un coup - et l'explication d'une variation graphemique
se distingue visuellement de la consigne. "Le bon verbe" a rejoint le catalogue jouable et son
paquet s'etend au futur et a l'imparfait de l'indicatif, apres un refus pedagogique corrige dans le
meme sprint ; la ligne de matrice francais CM1 conjugaison passe a `couvert-valide`.

## Decisions du sprint

| Reference | Titre | Type | Statut |
|---|---|---|---|
| DEC-0017 | Perimetre du sprint 8 : la machine retrouve son contexte, trois verites du depot corrigees, l'ouverture du futur et de l'imparfait de la conjugaison | Produit | Acceptee |
| ADR-0028 | Outiller la mesure du pli, et declarer non gardable le contenu au-dessus du pli | Architecture | Accepte |
| ADR-0029 | Faire porter un decalage declare a toute colonne que le contenu peut produire | Architecture | Accepte |
| ADR-0030 | Un habillage entoure sans rogner, n'anime rien, et toute animation finit sur son etat de repos | Architecture | Accepte |
| ADR-0031 | Un ecran ne gouverne jamais l'apparence d'un autre | Architecture | Accepte, amende par une clause d'unicite sur la taille de proposition (S8-5), sans nouvel `ADR` |
| DEC-0018 | L'activation de "Le bon verbe" est corrigee dans le sprint plutot que retiree, denouement d'un second refus, et sort de la ligne de matrice | Produit | Acceptee |
| DEC-0016 | Une hauteur de viewport de reference (360 x 640), et ce qui doit rester au-dessus du pli sur un ecran de manche | Produit | Acceptee, **amendee** ce sprint - le meme defaut de pli mesure sur deux jeux a des severites tres differentes ; une story ouverte au backlog, aucun seuil chiffre pose |

Aucun `ADR` ni `DEC` n'a ete cree par cette cloture elle-meme : les six references ci-dessus sont
toutes anterieures au fusionnage final, actees pendant le sprint.

## Etat des deux Done

| Done | Juge par | Etat | Reference |
|---|---|---|---|
| Technique | CI (`mvn verify`) | **Vert.** `mvn -B clean verify` sur `round-two` (`3fca08a`), rejoue independamment apres `clean` : BUILD SUCCESS, six modules, **347 tests**, **28 `ArchRule`** inchange (27 executees par `@ArchTest`, 1 par `.check()` explicite). Aucun plafond de dette approche. | `review.md`, sections "Rapport qualite" et "Confirmation ArchUnit" |
| Pedagogique | nadia-prof | **Deux increments distincts, tous deux `VALIDE SOUS RESERVE`, apres un refus intermediaire leve dans le sprint.** Chantier cabinet (S8-1 a S8-3) : Done accorde, quatorze points de garde audites sur les quatre jeux du catalogue. Conjugaison etendue : **troisieme audit REFUSE** sur l'activation au catalogue (deux propositions plus larges que leur colonne), corrige par S8-5, **quatrieme audit VALIDE SOUS RESERVE** - le refus est leve, la reserve restante (defaut de pli) n'est pas retenue comme motif. | `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, `FICHE-bandit-manchot-conjugaison-present-cm1.md` |

## Obstacles

**Resolus** :

| Description | Agent route | Decision | Clos le |
|---|---|---|---|
| Refus pedagogique de l'activation de "Le bon verbe" au catalogue (deux propositions plus larges que leur colonne a 360 px) | camille-po (perimetre), ines-design (chiffrage), nadia-prof (arbitrage), lea-dev (S8-5) | `DEC-0018` : correction dans le sprint plutot que retrait, les deux issues decidees avant le second verdict | quatrieme audit de `nadia-prof`, 2026-08-11 |
| Dette de `theo-archi` : la condition C4 (co-occurrence d'ecrans) n'etait gardee par aucun test ArchUnit ou structurel | theo-archi | `ADR-0031`, clause structurelle ecrite une fois `.cabinet` livre dans le depot | 2026-08-11, canal principal ferme (voir obstacles ouverts pour ce qui reste) |
| `AGENTS_SQUAD` de `outils/rapport-tokens.py` omettait `ines-design` (huit agents listes, l'equipe en compte neuf) - obstacle route a s07 | sofia-sm | Corrige : liste neuf agents, test dans le meme commit, mis a l'epreuve par `yanis-qa` (retrait du nom, echec constate, restauration, succes) | 2026-08-11, `26b2f51` |

**Encore ouverts** :

| Description | Agent route | Depuis | Ce qui manque |
|---|---|---|---|
| Troisieme violation de la regle du worktree obligatoire en deux sprints d'affilee (`camille-po` s06, `theo-archi` s07, `nadia-prof` s08) | theo-archi (cadrage de faisabilite d'un garde-fou executable) | 2026-08-11, `retro.md` | avis de faisabilite au planning du prochain sprint - garde-fou technique ou constat ecrit que ce n'est pas gardable a un cout raisonnable |
| Dette C4 : le canal de feuille reunissant deux ecrans exclusifs est ferme, mais le verdict peut encore colorer la coquille permanente ou passer par le gabarit via `th:classappend` | theo-archi | 2026-08-11 | le canal du gabarit n'est pas testable aujourd'hui, faute de toute variable de modele portant un verdict sur l'ecran de manche |
| Trois points gardes par `nadia-prof` seule : taille de police du repli (fermee ce sprint), air des glyphes (non gardable a cout raisonnable, 2,86 px de marge restante), rapport proposition/enonce (gardable a faible cout) | sofia-sm | 2026-08-11 | le rapport proposition/enonce est ouvert au backlog comme prochain geste le moins cher ; les deux autres restent geres a la main, motifs deja ecrits |
| Scan du lexique de jeu d'argent sur les textes et attributs de contenu (C6) | sofia-sm | 2026-08-11 | route a un sprint futur au cadrage - perimetre doit couvrir les 61 paquets de `contenu-repris/`, pas seulement les gabarits |
| Defaut documentaire de `DEC-0017` : "quatre chantiers" annonces, seuls les chantiers 1, 2 et 4 sont numerotes ; "quatre stories" annoncees pour le chantier 1, trois listees | camille-po | 2026-08-11 | correction du texte de `DEC-0017` ; **verifie par `sofia-sm` : aucune story perdue**, le backlog du sprint porte exactement S8-1 a S8-5, toutes livrees et jugees |
| Les trois indices qui qualifient d'irregulier ce qui l'est au niveau du verbe mais pas de la terminaison a l'imparfait | nadia-prof (deja verse au backlog) | anterieur, confirme ce sprint par `relecteur-pedagogique` | decision pedagogique prealable avant toute reformulation |
| Une technique de mesure de geometrie (`getBoundingClientRect` texte contre bouton) reconstruite cinq fois par trois agents dans ce seul sprint | sofia-sm | 2026-08-11 | outillage a la prochaine planification (fonction pure + enveloppe non testee, doctrine `ADR-0009`) |

## Couverture programme

| Ligne de matrice | Avant s08 | Apres s08 |
|---|---|---|
| francais / CM1 / Orthographe et grammaire / Conjuguer les verbes frequents au present, futur et imparfait | `en cours`, Done pedagogique refuse (troisieme audit) puis accorde sous reserve (quatrieme audit) | **`couvert-valide`** - condition de `DEC-0018` remplie au premier verdict non-refuse sur l'increment corrige |

Synthese globale (`squad/programme/MATRICE-couverture.md`, 46 lignes) : **3 `couvert-valide`**
(mathematiques CP, homophones grammaticaux CM1, conjugaison CM1), **1 `en cours`** (francais CP,
"Le bon son"), **42 `non couvert`**. Ce sprint fait passer une seule ligne, celle deja gelee par
`DEC-0017` comme objectif possible ; aucune autre ligne ne bouge.

## Consommation de tokens

`python outils/rapport-tokens.py --sprint s08`, lance apres l'ecriture de `review.md` pour que la
fenetre se ferme sur son horodatage de fin (`squad/README.md` section 6) plutot que sur "jusqu'a
maintenant". A produit `squad/sprints/s08-cabinet-de-la-machine/RAPPORT-tokens.md` et mis a jour
`squad/rapports/JOURNAL-tokens.md`.

**Premier sprint ou `ines-design` est comptee** : sa ligne porte un compte reel (171 tours), pas un
zero silencieux - c'est la correction de `AGENTS_SQUAD` livree ce sprint qui le rend possible.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 271 | 593 | 76 054 | 805 013 | 35 128 641 |
| `sofia-sm` | claude-sonnet-5 | 477 | 75 102 | 117 984 | 1 501 133 | 26 573 690 |
| `theo-archi` | claude-opus-5 | 243 | 486 | 121 901 | 694 920 | 24 056 726 |
| `marc-dev` | claude-sonnet-5 | 220 | 39 296 | 129 373 | 698 465 | 44 004 654 |
| `lea-dev` | claude-sonnet-5 | 288 | 19 503 | 83 425 | 936 040 | 41 593 376 |
| `yanis-qa` | claude-sonnet-5 | 225 | 26 774 | 49 318 | 430 890 | 28 734 377 |
| `relecteur-pedagogique` | claude-sonnet-5 | 154 | 308 | 54 332 | 1 066 790 | 21 883 521 |
| `nadia-prof` | claude-opus-5 | 483 | 1 101 | 230 745 | 1 653 259 | 55 636 623 |
| `ines-design` | claude-sonnet-5 | 171 | 7 750 | 62 163 | 583 516 | 30 025 205 |
| orchestrateur (fil principal) | claude-opus-5 | 132 | 251 | 272 804 | 550 949 | 19 227 280 |
| **Total** | | **2 664** | **171 164** | **1 198 099** | **8 920 975** | **326 864 093** |

Les quatre compteurs restent separes (`squad/README.md` section 6) : le cache lu depasse
couramment l'output de deux ordres de grandeur, les additionner donnerait un classement faux.

Seize worktrees ont tourne ce sprint (huit agents, plusieurs en ayant ouvert deux), et une
troisieme violation de la regle du worktree obligatoire s'y est glissee (voir `retro.md`) ; hors ce
point, deja route, aucune autre anomalie trouvee dans ce tableau.
