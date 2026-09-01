# [Sprint s26] Rapport de sprint - la-voix-et-la-dette

- **Debut** : 2026-09-01 14:32
- **Fin** : 2026-09-01 21:49
- **Duree effective** : environ 7h17

## Increment livre

**Un adulte present peut desormais lire a voix haute cinq jeux de conscience phonologique et de
graphie a un enfant de CP, et le produit lui dit ce qu'il ne doit pas dire ou montrer ; la dette
technique du JavaScript porteur non file, ouverte depuis trois sprints, est fermee.**

- Cinq paquets neufs (`bandit-manchot-position-syllabe-cp`, `bandit-manchot-son-dans-mot-cp`,
  `bandit-manchot-lettre-du-son-cp`, `bandit-manchot-digraphe-du-son-cp`, `saisie-mot-dicte-cp`),
  75 items dits a voix haute, valides par `nadia-prof` apres un premier refus leve.
- Le champ d'item `texteAVoixHaute`, l'ecran d'`ines-design` cable sous `VOI-12` (bloc adulte
  unique, jamais a moitie rempli), et l'amendement d'identite d'`ADR-0032` que la famille a voix a
  rendu necessaire.
- `ADR-0074` ferme : un troisieme porteur de JavaScript, mal reconnu comme un quatrieme, est
  corrige par le parseur d'URL du standard.
- Perimetre arbitre par l'utilisatrice, tenu mot pour mot : *"demarre s26 avec en plus la dette."*

## Decisions du sprint

| Reference | Titre | Type | Statut |
|---|---|---|---|
| `DEC-0068` | La voix est celle d'un adulte present | Produit | Acceptee, amendee |
| `DEC-0067` | Etat du produit a la cloture de s25, rang de s26 | Produit | Acceptee, amendee |
| `ADR-0032` | Identite d'un tour (item) | Architecture | Accepte, amende |
| `ADR-0074` | Tout JavaScript servi vit dans un fichier | Architecture | Accepte, amende |
| `ADR-0075` | La famille a voix est un champ d'item | Architecture | Accepte |
| `ADR-0076` | `VOI-14` remplace l'operateur de `VOI-10` | Architecture | Accepte |

## Etat des deux Done

- **Done technique** : vert. `mvn -B clean verify` sur le reacteur complet : **1090 tests**
  (82 domain + 13 application + 382 adapter-web + 457 adapter-content + 150 bootstrap unitaires +
  6 bootstrap integration), 0 echec, 0 erreur, BUILD SUCCESS, mesure sur `round-two` a `05dcc60`
  (dernier commit de code du sprint ; les commits suivants sont les ceremonies de cloture, sans
  effet sur ce compte).
- **Done pedagogique** : **VALIDE sur les cinq paquets**, prononce par `nadia-prof`, apres un
  premier **REFUSE** leve dans le meme sprint (motif : la cinquieme phrase d'adulte manquante,
  ecrite d'avance et non executee - voir `retro.md`). Le refus n'a pas ete efface ; il reste
  lisible avec sa date dans les cinq `squad/conformite/FICHE-*.md` et dans `review.md`.

## Ce qui reste ouvert - a ne pas annoncer ferme

- **La ligne "Associer les graphemes aux phonemes" ne passe pas a `couvert-valide`** : trois
  lignes se ferment sur quatre. `cp-lettre-du-son` sert six consonnes et aucune voyelle simple ;
  la barre de `nadia-prof` exige les deux. Un paquet de voyelles simples la fermerait a s27 - rang
  non encore instruit par `camille-po` a cette cloture.
- **`P(bonne / presente)` reste non garde par `adapter-content`**, coupe du perimetre de s26 par
  `theo-archi` lui-meme (forme naive rougirait 8 paquets deja valides sur 33, population utile
  vide) : a instruire, pas un abandon.
- **La liste des conditions que personne ne tient ne diminue pas** : `VOI-3`, `VOI-5`, `VOI-8`, la
  moitie non calculable de `VOI-13`, le point 2 et la moitie du point 3 de `VOI-12`. Aucun test ne
  sait si un adulte a reellement respecte ces consignes.
- **Une dette neuve, instruite et non tranchee** : trois incidents du sprint partagent la meme
  cause - une decision citait un fichier ou une section restee sur une branche. `ADR-0066` n'en
  attrape qu'une partie (les identifiants `ADR-*`/`DEC-*`) ; l'extension necessaire est routee a
  `theo-archi`, pas actee.
- **Un doute d'`ines-design` sur `bandit-manchot-son-dans-mot-cp`** reste marque "point ouvert"
  dans sa fiche malgre une reponse vraisemblable ailleurs dans l'arbitrage de `nadia-prof`
  (section 17.5) : incoherence non tranchee par moi, a confirmer par elle.
- **`s26-camille` (`5c95976`) n'est pas fusionnee** : conflit avec du travail deja fait autrement,
  abandonnee proprement plutot que forcee, branche laissee intacte pour verification.
- **`s26-yanis` n'est pas fusionnee**, deliberement : ses deux commits restent la trace primaire
  de ce qu'il a mesure, gardee plutot que supprimee.

## Obstacles resolus et ouverts

Voir le tableau complet dans `review.md`, section "Obstacles du sprint" - quinze obstacles
distincts, treize clos, deux ouverts (le rang de s27 sur la ligne des graphemes, et la dette
"fichier reste sur une branche" a instruire par `theo-archi`).

**Quatre ecarts de l'orchestrateur** journalises ce sprint (voir `retro.md` pour le detail et les
citations) : `CLAUDE.md` citant une `DEC` hors arbre et pousse sans `verify` ; l'affectation des
cinq paquets doublant la table du planning ; une consigne au `relecteur-pedagogique` citant
`VOI-7` au lieu de `VOI-13` ; une correction de contenu lancee en parallele d'une fusion qui
allait la doubler.

## Consommation de tokens

Voir `RAPPORT-tokens.md` pour le detail (quatre compteurs separes, jamais additionnes). Total de
la fenetre du sprint (2026-09-01 12:32 a 19:49 UTC) :

| Compteur | Total |
|---|---:|
| Tours | 2 931 |
| Input | 5 862 |
| Output | 1 659 242 |
| Cache cree | 22 488 068 |
| Cache lu | 825 890 820 |

Aucune invocation mesuree sur cette fenetre pour `ines-design` : son travail sur ce sprint est
anterieur a l'horodatage de debut (14:32), pas absent du sprint.
