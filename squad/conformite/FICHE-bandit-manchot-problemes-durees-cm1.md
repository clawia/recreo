# Fiche de conformite - bandit-manchot-problemes-durees-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du catalogue).
- **Contenu(s)** : contenu neuf a creer (`type: question`, au moins 20 items), story `S13-3`
  (`docs/backlog/backlog.md`). Matiere premiere partielle identifiee : `contenu-repris/quiz/
  cm1-heure.yml` porte deux items reellement conformes ("de 14h a 16h, combien de temps s'est
  ecoule ?", le film de 8h30 a 10h) sur ses dix questions ; les autres relevent de la lecture de
  l'heure (competence CP) ou de la conversion d'unite de temps seule (hors de cette ligne), et aucun
  ne porte sur un "horaire d'arrivee". Reprise = reprise des deux items conformes, pas de la
  structure du fichier (`contenu-repris/README.md`) : ils repassent devant `nadia-prof` comme un
  contenu neuf.
- **Statut global** : **ouverte, aucun increment livre a ce jour.** Rien n'a encore ete joue.
- **Arbitrage prealable de `nadia-prof` demande et non encore rendu** (`DEC-0030`, section 5) : ce
  contenu n'a jamais ete instruit au niveau de detail des trois lignes du sprint 12. Les criteres
  ci-dessous, notamment les bornes (une seule journee, minutes rondes de 5 en 5), sont ceux de
  `camille-po` et n'ont pas valeur de jugement pedagogique tant que `nadia-prof` ne les a pas
  confirmes ou corriges.
- **Arbitrage prealable de mecanique en vigueur pour tout le catalogue** (`squad/conformite/
  FICHE-bandit-manchot-additions-cp.md`, branche `s10-nadia-mecanique` et audits suivants) :
  conditions M1 a M13. Rien n'est mesure ici a l'ouverture de la fiche, faute d'increment a mesurer.
- **Derniere mise a jour** : 2026-08-15 (ouverture par `camille-po`, `DEC-0030`)

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| mathematiques | CM1 (cycle 3) | Grandeurs et mesures | Resoudre des problemes de durees (calcul d'une duree, d'un horaire d'arrivee) | `squad/programme/mathematiques/competences-cm1.md` |

**Perimetre propose, sous reserve de l'arbitrage prealable de `nadia-prof`** : deux formes seulement,
"calcul d'une duree" (deux heures donnees, duree ecoulee a trouver) et "horaire d'arrivee" (heure de
depart et duree donnees, heure d'arrivee a trouver) - jamais une simple lecture de l'heure ni une
conversion d'unite de temps seule.

## Criteres pedagogiques attendus - camille-po

- [ ] Chaque item releve de l'une des deux formes exactes du referentiel (calcul d'une duree, ou
  horaire d'arrivee), jamais d'une lecture de l'heure ou d'une conversion d'unite de temps seule.
- [ ] Les deux formes sont chacune representees par au moins un tiers du paquet.
- [ ] Toutes les heures d'un meme item restent dans une seule journee (aucun item ne franchit
  minuit), exprimees en heures et minutes rondes de 5 en 5 minutes, sans seconde.
- [ ] Chaque item porte exactement trois propositions, dont deux distracteurs plausibles (erreur de
  report d'heure ou de minute, inversion du sens de l'operation, oubli de la retenue de 60 minutes),
  jamais deux propositions egales dans le meme item.
- [ ] Le paquet compte au moins 20 items.
- [ ] Chaque item porte un indice non vide qui donne la methode, pas le resultat.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. Il reste vide tant qu'aucun
> increment n'a ete livre et joue. L'arbitrage prealable demande par `DEC-0030` (formes admises,
> bornes horaires) sera rendu ici ou dans un fichier d'arbitrage dedie, avant tout code.

### Arbitrage prealable rendu le 2026-08-15 (s13) - ce n'est pas un Done pedagogique

- **Ou** : `squad/conformite/ARBITRAGE-PREALABLE-s13-multiplications-divisions-et-durees-cm1.md`,
  section 3. **Onze conditions, D-1 a D-11**, plus la liste de ce que je refuse d'avance
  (section 3.5).
- **Ce qu'il tranche** : la ligne ouvre **en entier** sur les deux formes que le referentiel nomme.
  La troisieme forme - retrouver l'heure de depart - est **refusee d'avance dans ce paquet** et son
  absence ne pourra pas etre comptee comme un trou de couverture. Les trois bornes de `camille-po`
  (une journee, minutes de 5 en 5, aucune seconde) sont **confirmees** et **completees** : au plus la
  moitie du paquet en quarts d'heure, entre un tiers et deux tiers des items franchissant une heure
  ronde, duree maximale de cinq heures, enonce de vingt-cinq mots au plus. **La condition la plus
  importante est D-2** : un instant et une duree ne s'ecrivent pas de la meme facon, et le contenu
  repris les ecrit a l'identique.
- **Un obstacle technique signale avant le code** (section 3.6) : les deux garde-fous de rang lisent
  **l'entier de tete** d'une proposition, donc ils classent `1 h 30` sous `45 min` et rangent trois
  propositions de meme heure comme si la bonne reponse etait la plus petite. Sur ce paquet, leur
  verdict sera faux dans un sens ou dans l'autre. **Personne ne modifie le contenu pour les faire
  verdir** ; le symptome est route a `theo-archi` et `yanis-qa`, et le rang reste a ma garde.
- **Ce qu'il ne fait pas** : il ne vaut aucun Done pedagogique par avance.
- **Statut** : le verrou d'arbitrage prealable de `DEC-0030` sur cette story est **leve**. Le code
  peut commencer des que `theo-archi` a rendu son avis de faisabilite - l'obstacle de la section 3.6
  ne bloque pas l'ecriture du contenu, il conditionne la lecture de la CI a la review.
