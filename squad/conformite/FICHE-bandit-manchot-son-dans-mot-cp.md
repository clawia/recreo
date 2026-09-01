# Fiche de conformite - bandit-manchot-son-dans-mot-cp

- **Mecanique** : bandit manchot ; champ vocal (nature technique en attente de `theo-archi` -
  champ d'item selon `ADR-0073`, ou mecanique neuve ; `DEC-0067`, amendement, sections B.5 et
  B.7).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-son-dans-mot.yaml`
  (a creer), story `S26-VOI-2` (`docs/backlog/backlog.md`). Aucun fichier de `contenu-repris/` ne
  porte cette forme (voix, sans mot ecrit) ; ecriture neuve.
- **Entree de catalogue** : `bandit-manchot-son-dans-mot-cp` (identifiant propose, a confirmer par
  le developpeur).
- **Statut global** : **ouverte, aucun increment livre a ce jour.** Rien n'a encore ete joue.
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
- **Derniere mise a jour** : 2026-09-01 (ouverture par `camille-po`, `DEC-0068`, s26).

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
