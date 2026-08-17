# [Sprint s13] Rapport de sprint - les grands nombres entrent au catalogue sous condition de rendu, la voie 4 ferme 29 debordements

- **Debut** : 2026-08-15 16:18 (`planning.md`)
- **Fin** : 2026-08-17 09:16 (`review.md`)

## Increment livre

Trois jeux de mathematiques CM1 tiennent aujourd'hui dans leur colonne a 360 px : "Les grands
nombres" (lire, ecrire, decomposer et comparer les entiers jusqu'au million, ligne qui ne comptait
aucun jeu a l'ouverture du sprint 12), "Le perimetre" et "Les mesures", dont un debordement de
rendu abimait respectivement une bonne reponse et un distracteur sur vingt-neuf items au total. La
voie retenue par `nadia-prof` (la colonne recule devant son contenu, "voie 4") est implementee par
`lea-dev` et mise a l'epreuve par mutation par `yanis-qa` : elle mord sur l'etat reel refuse, et
reste vraie a tort sur un contournement different (`white-space: nowrap`), fait explicitement
consigne plutot que tu. Les deux reserves ouvertes sur les tables de multiplication (un enonce sur
cinq qui revient, deux distracteurs hors de l'espace des tables) sont corrigees en code, **mais pas
encore reverifiees par `nadia-prof`** - voir "Etat des deux Done".

L'`aria-label="Menu principal"`, refuse par `nadia-prof` ("une zone a un seul lien n'est pas un
menu"), est retire plutot que reformule, et le garde-fou de texte accentue s'etend desormais aux
attributs HTML/ARIA qui exposent un texte a un lecteur d'ecran (`ADR-0039`) - **le jugement
pedagogique de ce retrait n'est pas non plus encore rendu**.

## Decisions du sprint

| Reference | Titre | Type | Statut |
|---|---|---|---|
| DEC-0030 | Perimetre du sprint 13 : trois lignes de mathematiques CM1 et le plafond de la mecanique unique mesure | Produit | Acceptee |
| DEC-0031 | "Les grands nombres" quitte le catalogue jouable : contenu juge bon, debordement de rendu a 360 px, condition de retour executable | Produit | Acceptee ; **amendee le 2026-08-16** - la voie de rendu est tranchee, le jeu revient sous verification dediee, "Le perimetre" refuse une seconde fois sur le meme motif |
| ADR-0039 | Un critere se derive des que la promesse est universelle | Architecture | Accepte a l'origine (s12) ; **applique ce sprint** au retrait de l'`aria-label` (extension aux attributs HTML/ARIA) |
| ADR-0040 | Un point de focus declare n'est jamais destructeur | Architecture | Accepte a l'origine (s12) ; non modifie ce sprint |
| ADR-0041 | La valeur d'une proposition est derivee de ce qu'elle ecrit | Architecture | Accepte ; **note du 2026-08-16, meme auteur** - autocorrection de sa propre table de distribution (5/4/5 etait le contenu d'avant la reprise de `marc-dev`, 3/6/5 est l'etat reel) |
| ADR-0042 | Un arret remplacant est un second predicat orthogonal du destructeur | Architecture | Accepte |
| ADR-0013 | Un garde-fou derive son perimetre et se prouve mordant | Architecture | Accepte a l'origine ; **non amende ce sprint** - la section de cloture de `confirmation-archunit.md` que `theo-archi` avait annoncee n'a pas ete ecrite (voir "Obstacles") |

**Six decisions actees, amendees ou notees ce sprint** (deux `ADR` neufs, une `DEC` neuve amendee le
jour meme de son ouverture, deux `ADR` anterieurs appliques ou corriges par leur propre auteur). Le
tableau ci-dessus ne laisse aucune section vide.

## Etat des deux Done

| Done | Juge par | Etat |
|---|---|---|
| Technique | CI (`mvn -B clean verify`) | **Vert, rejoue independamment par moi sur `round-two` a `8d846c0`**, JDK 21.0.10 designe par `JAVA_HOME` (JDK par defaut du poste en JRE 8, fichier de toolchains present sous un nom que Maven ne lit pas - dette de poste nommee depuis s03, hors depot). BUILD SUCCESS, six modules, **489 tests**, **28 regles ArchUnit** (recompte par une methode stricte, `adapter-web` 1, `bootstrap` 27), garde-fou de chemins en code de sortie 0. Identique aux chiffres de `yanis-qa` (`rapport-qualite.md`). |
| Pedagogique | nadia-prof | **Trois verdicts rendus, deux exit de porte de sortie non franchis.** "Les grands nombres" : `VALIDE SOUS RESERVE` (troisieme passage, apres deux refus, la coupure de valeur est bonne mais VR-4 - huit items sous le pli du navigateur - reste ouverte). "Le perimetre" : `VALIDE SOUS RESERVE`, refus du 2026-08-16 leve. "Les mesures" : Done maintenu, reserve de rendu levee. **"Les tables de multiplication" : Done pedagogique inchange depuis s12** - la porte de sortie du planning ("reverifie par `nadia-prof`") n'a pas ete franchie, la fiche n'a plus ete touchee depuis `b50d4c5` (s12), alors que le code de `lea-dev` est fusionne et vert. **S12-5 (retrait de l'`aria-label`) : aucun jugement pedagogique rendu**, alors que la porte de sortie du planning l'exigeait explicitement. Je ne prononce aucun de ces deux Done a la place de `nadia-prof` ; je constate que les portes que le planning nommait ne sont pas franchies. |

## Obstacles

**Resolus** :

| Description | Agent route | Decision | Clos le |
|---|---|---|---|
| Blocage circulaire : `nadia-prof` ne peut pas juger un jeu retire du catalogue, le catalogue ne peut pas le reprendre sans son jugement | camille-po | `DEC-0031`, amendement - verification dediee hors catalogue jouable | 2026-08-16 |
| 29 debordements de rendu a 360 px sur trois jeux (grands nombres, perimetre, mesures) | nadia-prof (arbitrage de la voie), lea-dev (implementation) | voie 4 retenue et implementee, mesuree a 0 debordement sur 357 items | 2026-08-16 |
| Garde-fou de rang rendu muet par la correction du motif "decomposer" - predit par `nadia-prof`, realise, puis reconcilie | theo-archi, nadia-prof | reconciliation independante au meme chiffre (3/6/5, 42,9 %) | 2026-08-16 |
| `aria-label="Menu principal"` refuse par `nadia-prof` | lea-dev | attribut retire, garde-fou de texte etendu aux attributs (`ADR-0039`) | 2026-08-15 |
| Premier critere d'acceptation de S13-4 non encodable ("se rapproche des 99 items") | theo-archi | forme plus forte et gratuite substituee, mesuree rouge sur le seul paquet fautif | 2026-08-15 |

**Encore ouverts** :

| Description | Agent route | Depuis | Ce qui manque |
|---|---|---|---|
| `confirmation-archunit.md` publie a l'ouverture avec une section de cloture promise, jamais ecrite | theo-archi | s13 (annonce s09-s12) | la section elle-meme, ou une note explicite disant pourquoi elle n'est plus jugee utile |
| Done pedagogique de S13-4/S13-5 (tables de multiplication) jamais rejoue par `nadia-prof` sur le code fusionne | nadia-prof | s13 | reverification sur `FICHE-bandit-manchot-tables-multiplication-cm1.md` |
| Jugement pedagogique de S12-5 (retrait de l'`aria-label`) jamais rendu | nadia-prof | s13 | un verdict, meme informel, sur l'increment livre |
| Reserve F11 : "les huit jeux partagent le meme gabarit" est un chiffre perime (onze aujourd'hui) | nadia-prof | 2026-08-15 (releve ce matin par le relecteur) | correction par note datee, le raisonnement reste valable |
| `MATRICE-couverture.md` et `FILE-ATTENTE.md` non mis a jour depuis les verdicts du 2026-08-16 (soir) | camille-po | 2026-08-16 | passage des lignes grands nombres et perimetre selon leurs verdicts respectifs |
| Un commit de `nadia-prof` (`653b915`, guidance sur la reserve B des tables) n'est l'ancetre d'aucun commit de `round-two` | aucun agent - fait a connaitre | s13 | rien a corriger sur le fond (la correction de `lea-dev` suit deja cette guidance) ; lecon versee a `squad/README.md` |
| Le quatrieme axe de rang (position a l'ecran) est enumerable par lecture mais mesure par aucun test ; couple retrait-catalogue/methode-de-test non garde par un mecanisme, sur cinq paquets | theo-archi, camille-po | s13 (`rapport-qualite.md`) | mecanisme ou decision d'architecture pour s14 |
| Reserves H et I (grands nombres) et reserve non bloquante sur la longueur/largeur comme signal de reponse (mesures, perimetre, calcul CP) | nadia-prof, camille-po | s13 | non bloquantes, tracees dans les fiches, candidates a une story s14 |
| JDK 21 declare absent par un troisieme agent ce sprint sur le meme poste (fichier de toolchains nomme `toochains.xml`) | aucun - hors depot, deja signale a l'utilisatrice | s03 | rien cote depot : `outils/verifier-jdk.sh` deja la seule reponse possible depuis le code source ; renommer le fichier est une action hors depot |

## Couverture programme

**Aucune synthese s13 n'existe encore dans `squad/programme/MATRICE-couverture.md`** : sa derniere
section date du 2026-08-15 ("Synthese apres les seconds verdicts du sprint 12", 8 lignes
`couvert-valide` sur 46, 3 `en cours`) et n'inclut ni l'ouverture des trois lignes CM1 de `DEC-0030`
ni les verdicts du 2026-08-16 (soir) sur les grands nombres et le perimetre. Cette mise a jour
revient a `camille-po` (`squad/README.md`, section 9, etape 6) ; je ne la fais pas a sa place. Etat
lu directement dans le tableau CM1 a ce jour : "Les grands nombres" et "Le perimetre" `en cours`,
"Les mesures" et "Les tables de multiplication" `couvert-valide`.

## Consommation de tokens

```
python outils/rapport-tokens.py --sprint s13
```

produit `squad/sprints/s13-grands-nombres-et-chemin-de-catalogue/RAPPORT-tokens.md` et met a jour
`squad/rapports/JOURNAL-tokens.md`. Fenetre mesuree : 2026-08-15 14:18 a 2026-08-17 07:16 UTC.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 170 | 94 476 | 72 449 | 611 452 | 14 793 990 |
| `sofia-sm` | claude-sonnet-5 | 455 | 3 516 | 142 927 | 1 276 619 | 32 125 102 |
| `theo-archi` | claude-opus-5 | 910 | 65 978 | 414 454 | 3 188 696 | 120 301 279 |
| `marc-dev` | claude-sonnet-5 | 638 | 22 014 | 246 256 | 2 174 723 | 67 933 616 |
| `lea-dev` | claude-sonnet-5 | 675 | 42 420 | 378 785 | 2 531 553 | 80 448 731 |
| `yanis-qa` | claude-sonnet-5 | 166 | 37 616 | 50 640 | 952 131 | 22 842 540 |
| `relecteur-pedagogique` | claude-sonnet-5 | 167 | 334 | 46 145 | 689 201 | 21 982 241 |
| `nadia-prof` | claude-opus-5 | 872 | 10 372 | 399 186 | 4 940 631 | 89 648 306 |
| `ines-design` | claude-sonnet-5 | 149 | 5 614 | 77 799 | 397 803 | 16 818 205 |
| orchestrateur (fil principal) | claude-opus-5 | 170 | 318 | 275 812 | 10 368 608 | 91 960 381 |
| **Total** | | **4372** | **282 658** | **2 104 453** | **27 131 417** | **558 854 391** |

Detail complet, y compris la fenetre "hors sprint", dans `RAPPORT-tokens.md` et
`squad/rapports/JOURNAL-tokens.md`.
