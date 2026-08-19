# Fiche de conformite - bandit-manchot-comprehension-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du
  catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/cm1-comprehension.yaml`
  (a creer, `type: question`, au moins 20 items), story `S17-2` (`docs/backlog/backlog.md`). Matiere
  premiere identifiee : `contenu-repris/quiz/cm1-comprehension.yml` (8 items, structure
  `question`/`bonne`/`distracteurs` deja compatible avec `type: question`, aucune transposition de
  schema requise). Reprise = reprise de la matiere, pas de la structure
  (`contenu-repris/README.md`) : chaque item repasse devant `nadia-prof` comme un contenu neuf, et
  le paquet doit doubler l'effectif repris (voir criteres).
- **Statut global** : **en construction** - aucun code, aucun contenu neuf ecrit a l'ouverture de
  cette fiche. Arbitrage prealable bloquant pose ci-dessous, avant tout premier commit.
- **Derniere mise a jour** : 2026-08-19 (ouverture, `camille-po`, s17).

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CM1 (cycle 3) | Lecture et comprehension de l'ecrit | Comprendre un texte narratif ou documentaire (questions explicites et implicites, inferences simples) | `squad/programme/francais/competences-cm1.md` |

**Perimetre exact, assume a l'ouverture.** Le referentiel porte deux competences voisines dans le
meme domaine : celle-ci ("comprendre", questions sur un texte) et "lire silencieusement un texte de
longueur croissante et en restituer l'essentiel". Cette fiche ne couvre que la premiere. La seconde
demande a l'enfant de produire un resume dans ses propres mots ; le mecanisme du bandit manchot ne
sait que faire choisir une proposition parmi plusieurs, jamais recueillir une production libre. Ce
n'est pas un oubli : c'est un report explicite, nomme dans `DEC-0039` et dans
`docs/backlog/backlog.md`, sur le meme motif structurel que "reperage sur une droite numerique" en
mathematiques CM1 (`squad/programme/MATRICE-couverture.md`, section mathematiques CM1).

## Criteres pedagogiques attendus - camille-po

- [ ] Le paquet compte au moins 20 items (le double de la matiere premiere reprise), pour que deux
  parties de dix manches ne se ressemblent pas - meme motif que la reserve 7 de "Le bon petit mot"
  (s05, `squad/programme/MATRICE-couverture.md`, francais CM1).
- [ ] Au moins un tiers des items ont une reponse qui ne recopie aucune phrase du texte mot pour
  mot : elle demande de relier deux informations distinctes du texte (inference simple), pas
  seulement de reperer une phrase explicite. Le referentiel nomme les deux ("explicites et
  implicites, inferences simples") ; un paquet entierement explicite ne le couvrirait qu'a moitie.
- [ ] Pour chaque item, une seule des propositions est vraie au regard du texte fourni : aucune
  autre proposition ne peut etre defendue comme correcte par une lecture attentive du texte.
- [ ] Pour chaque item, chaque distracteur est en rapport avec le texte (un element, un personnage
  ou un lieu qui y figure), jamais une proposition sans rapport que le sens commun ecarterait sans
  avoir lu le texte.
- [ ] Le texte-support de chaque item est visible a l'ecran en meme temps que la question et les
  propositions (aucune memorisation demandee), et sa longueur reste lisible sur un ecran de
  360 x 640 sans defiler - meme exigence M4 que les huit autres jeux du catalogue.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : <a rendre>
- **Date** : <a rendre>
- **Audit joue** : <a rendre>

### Ce qui a ete verifie

- **Ancrage au programme** :
- **Lisibilite au niveau vise** :
- **Charge cognitive** :
- **Progression** :
- **Traitement de l'erreur** :
- **Exactitude du contenu** :
- **Biais et ton** :

### Motif

### Reserves

## Arbitrage prealable bloquant, avant tout premier commit de code

Pose par `camille-po` a l'ouverture de cette fiche, sur le modele impose depuis l'incident du "Bon
verbe" en s07 (`squad/README.md`, section 10) : toute story qui ouvre un contenu neuf attend
l'arbitrage prealable de `nadia-prof` avant que la premiere ligne de code ne soit ecrite.

**Question posee** : un texte-support affiche a l'ecran en meme temps que la question et les
propositions constitue-t-il un exercice valide de la competence "comprendre un texte narratif ou
documentaire", ou seulement un exercice de recherche d'information localisee qu'un enfant peut
resoudre par appariement de mots entre la question et le texte, sans avoir vraiment compris
l'ensemble ? C'est exactement le risque que `nadia-prof` a nomme pour "Le bon son" en s02 (23 items
sur 24 resolubles par simple appariement visuel des lettres, sans passer par le phoneme vise) :
je ne veux pas ecrire le meme defaut une seconde fois sous un autre habillage. Si `nadia-prof` juge
que la forme MCQ ne peut jamais verifier une comprehension reelle sans un garde-fou supplementaire
(par exemple, un texte assez long ou construit pour qu'aucune reponse ne soit trouvable par simple
correspondance de mots), elle le pose comme condition avant tout code, pas comme une reserve
decouverte a l'audit.
