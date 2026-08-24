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
  - item "gagner"/"perdre" (memes motif) - distracteurs "arbitrer"/"applaudir" remplaces par
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
