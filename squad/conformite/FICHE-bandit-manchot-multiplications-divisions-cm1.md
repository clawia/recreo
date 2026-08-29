# Fiche de conformite - bandit-manchot-multiplications-divisions-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du catalogue).
- **Contenu(s)** : contenu neuf a creer (`type: question`, au moins 24 items), story `S13-2`
  (`docs/backlog/backlog.md`). `contenu-repris/cm1-calcul-mental.yml` est une matiere premiere
  inspirante seulement : schema incompatible (`difficulty`/`bounds`, configuration de difficulte
  taillee pour l'arithmetique qu'aucune fabrique de `round-two` ne lit) et perimetre trop large
  (melange addition, soustraction et multiplication, sans borner aux multiples de 10 et 100) - voir
  `DEC-0029`, section "Ce que le schema existant peut porter sans code neuf".
- **Statut global** : **ouverte, aucun increment livre a ce jour.** Rien n'a encore ete joue.
- **Arbitrage prealable de `nadia-prof` : rendu le 2026-08-15** (voir section dediee plus bas,
  `MD-1` a `MD-10`) - la ligne ci-dessus disait encore "demande et non encore rendu" jusqu'au
  2026-08-28, perimee depuis huit sprints ; corrigee ici plutot que laissee en l'etat. Les criteres
  ci-dessous, notamment les bornes, restent ceux de `camille-po` a l'ouverture, **completes et
  confirmes** par l'arbitrage rendu.
- **Arbitrage prealable de mecanique en vigueur pour tout le catalogue** (`squad/conformite/
  FICHE-bandit-manchot-additions-cp.md`, branche `s10-nadia-mecanique` et audits suivants) :
  conditions M1 a M13. Rien n'est mesure ici a l'ouverture de la fiche, faute d'increment a mesurer.
  **Mise a jour du 2026-08-29 (`camille-po`)** : `M4` est sortie de la porte pedagogique le
  2026-08-28 (`DEC-0053`) et sa reference `360 x 640` etait deja morte depuis `DEC-0040`
  (2026-08-22) ; voir `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, en-tete, et
  `squad/conformite/ARBITRAGE-M4-ET-BALAYAGE-DES-CONDITIONS-DE-MECANIQUE-2026-08-29.md`
  (`nadia-prof`) avant de citer ce bloc.
- **Priorite s22** (`DEC-0053`, section 4) : premiere candidate du perimetre du sprint, verrou
  d'arbitrage leve, aucun obstacle nomme sur ce paquet en dehors de l'avis de faisabilite de
  `theo-archi`, encore du.
- **Derniere mise a jour** : 2026-08-28 (`camille-po`, correction de statut et priorisation s22,
  `DEC-0053`)

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| mathematiques | CM1 (cycle 3) | Nombres et calculs | Calcul mental - multiplications et divisions simples (multiples de 10, 100) | `squad/programme/mathematiques/competences-cm1.md` |

**Perimetre propose, sous reserve de l'arbitrage prealable de `nadia-prof`** : multiplier ou diviser
un nombre entier par 10 ou par 100, sans unite de grandeur (calcul mental pur, a distinguer de la
ligne "convertir des unites" deja `couvert-valide` au sprint 12), sans ecriture decimale.

## Criteres pedagogiques attendus - camille-po

- [ ] Chaque item est une multiplication ou une division d'un nombre entier par 10 ou par 100,
  sans unite de grandeur ; la bonne reponse est le resultat exact.
- [ ] Le nombre de depart et le resultat sont des entiers positifs ; aucune division qui ne tombe
  pas juste, aucune ecriture decimale nulle part dans le paquet.
- [ ] La multiplication et la division sont representees a une unite d'ecart pres l'une de l'autre.
- [ ] Les items sur le facteur 10 et sur le facteur 100 sont chacun representes par au moins un
  quart du paquet.
- [ ] Chaque item porte exactement trois propositions, dont deux distracteurs entiers correspondant
  a une erreur de facteur identifiable, jamais deux propositions egales dans le meme item.
- [ ] Le paquet compte au moins 24 items.
- [ ] Chaque item porte un indice non vide qui donne la methode, pas le resultat.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. Il reste vide tant qu'aucun
> increment n'a ete livre et joue. L'arbitrage prealable demande par `DEC-0030` (bornes, unites,
> profondeur sur 100) sera rendu ici ou dans un fichier d'arbitrage dedie, avant tout code.

### Arbitrage prealable rendu le 2026-08-15 (s13) - ce n'est pas un Done pedagogique

- **Ou** : `squad/conformite/ARBITRAGE-PREALABLE-s13-multiplications-divisions-et-durees-cm1.md`,
  section 2. **Dix conditions, MD-1 a MD-10**, plus la liste de ce que je refuse d'avance
  (section 3.5).
- **Ce qu'il tranche** : la ligne ouvre **en entier**, sous la lecture stricte du referentiel -
  l'operateur est 10 ou 100, jamais 1000 ni un multiple compose. Les bornes de `camille-po` sont
  **confirmees** (entiers seulement, aucune ecriture decimale) et **completees** : quatre familles
  d'operation chacune a un cinquieme du paquet au moins, resultat sous le million, signes multiplie
  et divise reels a la place de la lettre `x` et de la barre oblique, et une **condition
  d'aboutissement sur le rang de la bonne reponse** (entre 20 % et 40 % par rang) plus stricte que
  les seuils de la CI, parce qu'elle est atteignable sur ce contenu.
- **Ce qu'il ne fait pas** : il ne vaut aucun Done pedagogique par avance. Un lot qui tient les dix
  conditions peut encore etre refuse pour ce que seul le jeu montre.
- **Statut** : le verrou d'arbitrage prealable de `DEC-0030` sur cette story est **leve**. Le code
  peut commencer des que `theo-archi` a rendu son avis de faisabilite.
