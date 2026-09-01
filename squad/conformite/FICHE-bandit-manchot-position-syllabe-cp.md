# Fiche de conformite - bandit-manchot-position-syllabe-cp

- **Mecanique** : bandit manchot ; champ vocal (nature technique en attente de `theo-archi` -
  champ d'item selon `ADR-0073`, ou mecanique neuve ; `DEC-0067`, amendement, sections B.5 et
  B.7).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-position-syllabe.yaml`
  (a creer), story `S26-VOI-1` (`docs/backlog/backlog.md`). Aucun fichier de `contenu-repris/` ne
  porte cette forme (voix, sans mot ecrit) ; ecriture neuve.
- **Entree de catalogue** : `bandit-manchot-position-syllabe-cp` (identifiant propose, a confirmer
  par le developpeur).
- **Statut global** : **ouverte, aucun increment livre a ce jour.** Rien n'a encore ete joue.
- **Arbitrage prealable de `nadia-prof` rendu** : `squad/conformite/ARBITRAGE-PREALABLE-s26-la-
  voix-est-un-adulte.md`, section 4.1 (acceptation) et sections 5, 6, 7, 13 (conditions
  transverses aux cinq paquets). `DEC-0068` et son amendement portent l'arbitrage de l'utilisatrice
  qui rend ce moyen possible.
- **Derniere mise a jour** : 2026-09-01 (ouverture par `camille-po`, `DEC-0068`, s26).

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CP (cycle 2) | Langage oral et conscience phonologique | Localiser une syllabe dans un mot (debut / milieu / fin) | `squad/programme/francais/competences-cp.md` |

**Une seule ligne de matrice visee.**

## Criteres pedagogiques attendus - conditions de `nadia-prof`, reprises telles quelles

- [ ] **`VOI-3` - NON GARDEE.** L'adulte dit le mot d'un seul tenant, a vitesse normale. Aucune
  scansion syllabique, aucune pause a l'interieur du mot. *Aucun test, aucune capture, aucune
  relecture ne peut le verifier - c'est un humain dans la piece qui la tient, ou personne. La seule
  contrepartie est la phrase de `VOI-12` sur ce paquet : "Dites le mot normalement, sans le
  couper."*
- [ ] **`VOI-4`** - Les mots comptent deux ou trois syllabes, et la syllabe cherchee occupe les
  trois positions (debut, milieu, fin) sur l'ensemble du paquet, aucune n'etant majoritaire. Unite
  de compte : l'item.
- [ ] **`VOI-1`** - L'adulte prononce le stimulus (le mot, et la syllabe cherchee) ; il ne
  prononce jamais une proposition.
- [ ] **`VOI-10`** - Le texte que l'adulte prononce est un champ distinct, et il ne contient jamais
  une proposition ni la reponse.
- [ ] **`VOI-12` (exigence bloquante)** - L'ecran dit a l'adulte ce qu'il ne doit pas faire, en une
  phrase, a l'endroit ou il lit son texte, jamais derriere un clic. Cinq points verifiables
  (arbitrage, section 13.1). Phrase candidate rendue par `nadia-prof` pour ce paquet : *"Dites le
  mot normalement, sans le couper."*
- [ ] **`VOI-13`** - Le mot travaille est ecrit une seule fois a l'ecran, dans le texte de l'adulte,
  et nulle part ailleurs (ni consigne, ni enonce, ni proposition, ni indice, ni texte alternatif,
  ni ecran d'erreur, ni ecran de revelation). *Calculable pour cette moitie ; comptee comme la
  quatrieme condition non gardee pour le reste - rien n'empeche un enfant assis a cote de lire le
  texte de l'adulte par-dessus son epaule.*
- [ ] Le paquet compte au plus **20 items** (borne de `nadia-prof`, addendum section 12.2).

**Ce que cette fiche ne fait pas** : elle ne prejuge d'aucune forme de mecanique ni d'aucun rendu
d'ecran. Le materiel prealable d'`ines-design` (`squad/design/proposition-ines-s26-ecran-voix.
md`) est un candidat de forme, pas un acquis.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. Il reste vide tant qu'aucun
> increment n'a ete livre et joue.
