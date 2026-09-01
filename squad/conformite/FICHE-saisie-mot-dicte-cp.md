# Fiche de conformite - saisie-mot-dicte-cp

- **Mecanique** : saisie ; champ vocal (nature technique en attente de `theo-archi` - champ d'item
  selon `ADR-0073`, ou mecanique neuve ; `DEC-0067`, amendement, sections B.5 et B.7). **Premier
  paquet a reponse alphabetique sur la mecanique de saisie** - jusqu'ici seule la reponse numerique
  a ete jouee (`saisie-complements-10-cp`).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-mot-dicte.yaml`
  (a creer), story `S26-VOI-5` (`docs/backlog/backlog.md`). Ecriture neuve ; aucun fichier de
  `contenu-repris/` ne porte cette forme.
- **Entree de catalogue** : `saisie-mot-dicte-cp` (identifiant propose, a confirmer par le
  developpeur).
- **Statut global** : **ouverte, aucun increment livre a ce jour.** Rien n'a encore ete joue.
- **Arbitrage prealable de `nadia-prof` rendu** : `squad/conformite/ARBITRAGE-PREALABLE-s26-la-
  voix-est-un-adulte.md`, section 4.5 (acceptation - *"c'est ma propre condition qui se leve"*,
  `SAI-11` etant desormais levee sur sa condition remplie) et sections 5, 6, 7, 13 (conditions
  transverses aux cinq paquets). `DEC-0068` et son amendement portent l'arbitrage de l'utilisatrice
  qui rend ce moyen possible.
- **Ce qui reste tranche par une condition anterieure, non a reconfirmer** : la lettre accentuee
  sur tablette reste sans reponse de l'utilisatrice, mais `SAI-1` (2026-08-24) compte deja juste
  par defaut une reponse ecrite sans accent.
- **A trancher avant developpement, par `theo-archi` et `sofia-sm` (casquette dev), pas par
  `camille-po`** : lesquelles des conditions `SAI-1` a `SAI-12` et `SEUIL-1` a `SEUIL-5`, ecrites
  pour une reponse numerique, s'appliquent telles quelles a une reponse alphabetique, et lesquelles
  en demandent une nouvelle.
- **Derniere mise a jour** : 2026-09-01 (ouverture par `camille-po`, `DEC-0068`, s26).

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CP (cycle 2) | Ecriture | Ecrire de maniere autonome un mot simple en respectant les correspondances grapheme/phoneme connues | `squad/programme/francais/competences-cp.md` |

**Une seule ligne de matrice visee.**

## Criteres pedagogiques attendus - conditions de `nadia-prof`, reprises telles quelles

- [ ] **`VOI-8` - NON GARDEE.** L'adulte dit le mot, puis autant de fois que l'enfant le demande.
  Il ne l'epelle jamais, ne le decoupe jamais en syllabes, et n'ecrit rien. *"Epeler, c'est faire
  l'exercice." Aucun test ne peut le verifier. La seule contrepartie est la phrase de `VOI-12` sur
  ce paquet : "Dites le mot autant de fois qu'il le demande, mais ne l'epelez jamais."*
- [ ] **`VOI-9` (partiellement testable ; la regularite reelle reste jugee par `nadia-prof`)** -
  Les mots du paquet sont reguliers et de deux syllabes au plus, et aucun ne porte de lettre muette
  finale autre que celles que le CP a apprises.
- [ ] **`VOI-1`** - L'adulte prononce le stimulus (le mot a ecrire) ; il ne prononce jamais une
  proposition (sans objet ici, mecanique de saisie, mais la regle reste tenue par construction :
  aucune proposition n'existe).
- [ ] **`VOI-10`** - Le texte que l'adulte prononce est un champ distinct, et il ne contient jamais
  la reponse ecrite attendue en toutes lettres au-dela du mot lui-meme.
- [ ] **`VOI-12` (exigence bloquante)** - L'ecran dit a l'adulte ce qu'il ne doit pas faire, a
  l'endroit ou il lit son texte. Phrase candidate rendue par `nadia-prof` : *"Dites le mot autant
  de fois qu'il le demande, mais ne l'epelez jamais."*
- [ ] **`VOI-13`** - Le mot travaille est ecrit une seule fois a l'ecran, dans le texte de l'adulte,
  et nulle part ailleurs (le champ de saisie de l'enfant reste vide jusqu'a sa reponse). *Comptee
  comme la quatrieme condition non gardee pour sa part non calculable.*
- [ ] Le paquet compte au plus **20 items** (borne de `nadia-prof`, addendum section 12.2).

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. Il reste vide tant qu'aucun
> increment n'a ete livre et joue.
