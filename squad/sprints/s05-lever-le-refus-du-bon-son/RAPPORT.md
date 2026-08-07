# [Sprint s05] Rapport de sprint - lever le refus du "bon son"

- **Debut** : 2026-08-06 13:55 (`planning.md`)
- **Fin** : 2026-08-07 10:41 (commit `8c6d41e`)
- **Duree effective** : 20 h 46

## Increment livre

Le paquet d'homophones grammaticaux CM1 gagne cinq familles et une vraie variete d'une partie a
l'autre (accorde sous reserve, ligne de matrice pas encore levee) ; le rhabillage du gabarit
(en-tete, icone, menu, contenu centre), initialement prevu pour le sprint suivant, est livre et
audite dans le meme cycle (accorde sous reserve) ; et trois defauts de jeu trouves par
l'utilisatrice en jouant - une reponse accentuee qui plantait la manche, un score parfois perdu par
un double appui, un coup rejoue sur une partie terminee - sont corriges, alors qu'aucun des trois
n'avait ete signale par une CI verte. **"Le bon son" (CP) reste hors catalogue** : sa recomposition,
tranchee par `nadia-prof` comme indivisible d'un retour explicatif encore a ecrire, n'a pas ete
codee ce sprint.

## Decisions du sprint

| Reference | Titre | Type | Statut |
|---|---|---|---|
| ADR-0016 | Faire porter par le contenu l'aide que la mecanique promet, et deriver le perimetre du garde-fou du repertoire de contenu | Architecture | Accepte, amende le 2026-08-07 (dette de contenu fermee, plafond corrige de 2 a 0) |
| ADR-0017 | Comparer deux boutons d'un meme ecran, et dire ce que cette comparaison ne voit pas | Architecture | Accepte, amende le 2026-08-07 (frontiere `accueil.html` / fragments fermee) |
| ADR-0018 | Empecher qu'un defaut traverse le serveur en silence, et qu'il emprunte le canal reserve a l'attendu | Architecture | Accepte |
| ADR-0019 | Rendre indivisible tout cycle lire-calculer-ecrire sur l'etat de partie, et cesser de demander un coup a une partie terminee | Architecture | Accepte |
| ADR-0011 | (amendement) Rendre toute erreur imprevue en HTML francais | Architecture | Amende dans le meme commit qu'ADR-0018 (son point 3 etait faux, une exception du conteneur empruntait le canal de la session perdue) |
| ADR-0013 | (amendement) Un garde-fou derive son perimetre et se prouve mordant | Architecture | Amende dans le meme commit qu'ADR-0018 (cinquieme occurrence : les donnees d'entree d'un test font partie de son perimetre) |
| DEC-0010 | Le titre du produit redevient "Recreo", sans accent | Produit | Acceptee |
| DEC-0011 | Le sprint 5 reste entierement correctif, l'ouverture suivante est nommee au sprint 6 | Produit | Acceptee |
| DEC-0012 | Le rhabillage du gabarit ouvre le sprint 6 ; le menu reste a une seule entree | Produit | Acceptee, **non tenue dans les faits** - le rhabillage a ete livre et audite dans s05 sans amendement ecrit de cette DEC ; obstacle route en retro |

`DEC-0009` (retrait de "Le bon son") a ete creee a la charniere s04/s05 et deja rapportee dans
`squad/sprints/s04-ouvrir-le-francais/RAPPORT.md` ; elle n'est pas recomptee ici, mais son execution
reste tenue a la lettre tout au long de s05 (verifiee en relancant l'application).

## Etat des deux Done

| Done | Juge par | Etat | Reference |
|---|---|---|---|
| Technique | CI (`mvn verify`) | **Vert.** `yanis-qa` et `theo-archi` ont chacun mesure independamment au fil du sprint (226, puis 228, puis 238 tests selon l'etat de fusion). Reverifie par moi-meme a la cloture, sur JDK 21.0.10 : **BUILD SUCCESS, 238 tests, 0 echec, 27 `ArchRule` reellement executees** (methode de `theo-archi`, coherente avec sa propre progression 25 -> 27 apres la fusion d'`ADR-0019`). | `review.md`, sections "Rapport qualite" et "Confirmation ArchUnit" |
| Pedagogique | nadia-prof | **Trois verdicts distincts sur trois blocs.** Homophones CM1 : **VALIDE SOUS RESERVE**, ligne de matrice **non levee** (deux points precis manquent). Rhabillage du gabarit : **VALIDE SOUS RESERVE**, quatre reserves dont une regression chiffree. Double appui (`ADR-0019`) : **VALIDE**. "Le bon son" CP : **non audite**, refus du 2026-08-06 en vigueur, entier. | `squad/conformite/FICHE-bandit-manchot-homophones-cm1.md`, `FICHE-bandit-manchot-additions-cp.md`, `FICHE-bandit-manchot-le-bon-son-cp.md` |

## Obstacles

**Resolus** :

| Description | Agent route | Decision | Clos le |
|---|---|---|---|
| `PLAFOND_DE_DETTE` d'`ADR-0016` restait a 2 pour une dette de contenu vide | theo-archi | Plafond corrige a 0, motif ecrit sur le champ, `ADR-0016` amende | `6c02ed7` |
| `PropositionsAccentueesJouablesIT` restait vert quand le correctif d'encodage etait desactive | lea-dev | Corps de requete reconstruit en Latin-1 non pourcent-encode, prouve mordant dans les deux sens | `94a1a8f` |
| Trou de frontiere d'`ADR-0017` entre `accueil.html` et les fragments de jeu (nomme par l'ADR lui-meme comme non couvert) | theo-archi | Cible lue dans les gabarits (`hx-target`) plutot qu'un fichier de gabarit, `ADR-0017` amende, prouve mordant en revenant a l'ancienne semantique | `6c02ed7` |
| Course sur le cycle lire-calculer-ecrire de la session HTTP (score perdu par double appui) et coup rejoue sur une partie terminee (ecran d'erreur) | theo-archi | Tranches en un seul geste, deux clauses, `ADR-0019` | `af6f115` |
| Reponse accentuee qui plantait la manche, silencieuse dans les journaux | lea-dev, theo-archi | Decodage tolerant du corps de formulaire (`b3e1f22`) ; journalisation qui distingue les deux causes plutot que de les confondre (`ADR-0018`) | `b3e1f22`, `46eaa74` |
| Worktree de `yanis-qa` provisionne sur `main` plutot que `round-two` | yanis-qa | Reinitialise sur `round-two` avant tout travail, conformement a la consigne de verification du planning | debut de son rapport qualite |

**Encore ouverts** :

| Description | Agent route | Depuis | Ce qui manque |
|---|---|---|---|
| S5-2 recomposee et S5-9 (retour explicatif) doivent se livrer comme un seul lot ; "Le bon son" reste hors catalogue | nadia-prof (a tranche), camille-po, theo-archi, lea-dev | 2026-08-06 | recomposition a trois sons (an/on/in), 15 items, le champ de retour de S5-9 et son affichage |
| Ligne francais CM1 non levee : deux items neufs de plus, mot decisif non voisin du trou (la/l'a, ces/ses ou peu/peut) | camille-po, marc-dev | 2026-08-07 | deux items ecrits, rejoues par `nadia-prof` |
| Indice de "J'ai range ___ affaires" instruit une substitution qui n'est pas du francais | camille-po, marc-dev | 2026-08-07 | correction d'une ligne |
| Quatre reserves de forme sur le rhabillage, dont la regression chiffree de rembourrage lateral sous 1200 px | ines-design, theo-archi | 2026-08-07 | correctif CSS, remesure par `nadia-prof` |
| Identifiant `id="jeu"` en double apres chaque echange htmx | theo-archi | 2026-08-07 | diagnostic puis correction |
| `GET /parties/{id}/manche` sur une partie terminee rend un ecran mort | theo-archi | 2026-08-07 | diagnostic puis decision (aucun chemin atteignable par un enfant trouve a ce jour) |
| `GestionnaireErreurBruteContreExemple` enregistre comme un vrai bean Spring dans tout `@SpringBootTest`, intercepte avant `GestionnaireDesErreurs`, rend un 500 brut | theo-archi | 2026-08-07 | decision de cadrage du scan de composants ou d'isolation des contre-exemples |
| `DEC-0012` place le rhabillage au sprint 6 ; il a ete livre et audite dans s05 sans amendement ecrit | sofia-sm | 2026-08-07 | amendement de process propose en `retro.md` |
| Fichier de toolchains mal nomme, quatre agents ce sprint ont conclu a tort a l'absence d'un JDK 21 | sofia-sm | 2026-08-07 | script `outils/verifier-jdk.sh`, propose en `retro.md` |
| Verification de lignee de worktree recopiee en prose plutot qu'en script permanent | sofia-sm | 2026-08-07 | `outils/verifier-lignee.sh`, propose en `retro.md` |

## Couverture programme

| Ligne de matrice | Avant s05 | Apres s05 |
|---|---|---|
| francais / CP / Lecture et comprehension de l'ecrit / Associer les graphemes aux phonemes | `en cours` | **inchangee, `en cours`** - "Le bon son" non audite ce sprint |
| francais / CM1 / Orthographe et grammaire / Distinguer et utiliser correctement les principaux homophones grammaticaux | `en cours` | **inchangee, `en cours`** - condition retrecie a deux points precis par `nadia-prof` |

Aucune ligne de matrice n'est entree `en cours` ni n'est passee a `couvert-valide` ce sprint,
conformement au perimetre gele par `DEC-0011`.

## Consommation de tokens

`python outils/rapport-tokens.py --sprint s05` a produit
`squad/sprints/s05-lever-le-refus-du-bon-son/RAPPORT-tokens.md` et mis a jour
`squad/rapports/JOURNAL-tokens.md`, sans erreur - `planning.md` porte l'horodatage de debut requis,
contrairement a s02 et s04.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 51 | 25 296 | 9 517 | 555 337 | 4 148 323 |
| `sofia-sm` | claude-sonnet-5 | 168 | 336 | 27 766 | 347 281 | 7 827 352 |
| `theo-archi` | claude-opus-5 | 825 | 71 296 | 327 221 | 2 205 226 | 92 616 854 |
| `marc-dev` | claude-sonnet-5 | 226 | 4 490 | 35 116 | 1 277 823 | 26 185 786 |
| `lea-dev` | claude-sonnet-5 | 723 | 32 676 | 181 291 | 1 703 853 | 99 303 620 |
| `yanis-qa` | claude-sonnet-5 | 352 | 21 626 | 108 394 | 1 386 689 | 41 238 057 |
| `relecteur-pedagogique` | claude-sonnet-5 | 141 | 11 001 | 55 323 | 739 608 | 21 506 935 |
| `nadia-prof` | claude-opus-5 | 224 | 3 414 | 103 948 | 684 219 | 22 267 996 |
| `ines-design` | claude-sonnet-5 | 223 | 7 700 | 50 211 | 722 011 | 23 122 934 |
| orchestrateur (fil principal) | claude-opus-5 | 91 | 163 | 231 482 | 1 178 203 | 28 699 509 |
| **Total** | | **3024** | **177 998** | **1 130 269** | **10 800 250** | **366 917 366** |

Les quatre compteurs restent separes (`squad/README.md`, section 6) : le cache lu depasse
couramment l'output de deux ordres de grandeur, les additionner donnerait un classement faux.
Detail complet, y compris la fenetre exacte (2026-08-06 11:55 a 2026-08-07 08:41 UTC), dans
`RAPPORT-tokens.md` de ce dossier.
