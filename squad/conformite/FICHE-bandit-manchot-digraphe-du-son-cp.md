# Fiche de conformite - bandit-manchot-digraphe-du-son-cp

- **Mecanique** : bandit manchot ; champ vocal (nature technique en attente de `theo-archi` -
  champ d'item selon `ADR-0073`, ou mecanique neuve ; `DEC-0067`, amendement, sections B.5 et
  B.7).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-digraphe-du-son.yaml`
  (a creer), story `S26-VOI-4` (`docs/backlog/backlog.md`). Matiere premiere de forme uniquement,
  refusee comme contenu : `contenu-repris/mots/cp-le-bon-son.yml` (version d'origine, 6 sons
  melanges, refusee par `nadia-prof` le 2026-08-06 - le son `ch` y est bonne reponse 4 fois et
  distracteur 0 fois, `P(bonne / presente) = 100 %`, addendum section 11.2).
- **Entree de catalogue** : `bandit-manchot-digraphe-du-son-cp` (identifiant propose, a confirmer
  par le developpeur).
- **Statut global** : **ouverte, aucun increment livre a ce jour.** Rien n'a encore ete joue.
- **Ce que cette fiche est, et ce qu'elle n'est pas seule a fermer** : cette story couvre **la
  clause 2** (`ou`, `oi`, `ch`) de la ligne "Associer les graphemes aux phonemes". **La ligne ne
  passe `couvert-valide` que si cette fiche ET `FICHE-bandit-manchot-lettre-du-son-cp.md`
  (clause 1, `S26-VOI-3`) recoivent toutes deux un verdict `VALIDE`** (`nadia-prof`, addendum,
  section 11.6) - livrer l'une sans l'autre ne fait bouger aucune ligne de matrice.
- **Arbitrage prealable de `nadia-prof` rendu** : `squad/conformite/ARBITRAGE-PREALABLE-s26-la-
  voix-est-un-adulte.md`, section 4.3 (acceptation, "la ligne peut se fermer"), sa barre du
  2026-08-10 (`FICHE-bandit-manchot-le-bon-son-cp.md`, verdict du 2026-08-10, section 6), et
  l'addendum, sections 11 a 13 (deux paquets, pas un ; motif mesure).
- **Non-regression sur le jeu existant** : `git diff 96a80b4 -- adapters/adapter-content/src/main/
  resources/contenu/cp-le-bon-son.yaml` doit rester **vide**. Ce paquet ne touche pas ce fichier
  (clause 3, trois nasales, deja acquise).
- **Derniere mise a jour** : 2026-09-01 (ouverture par `camille-po`, `DEC-0068`, s26).

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
