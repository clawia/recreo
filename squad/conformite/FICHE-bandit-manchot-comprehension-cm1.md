# Fiche de conformite - bandit-manchot-comprehension-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du
  catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/cm1-comprehension.yaml`
  (a creer, `type: question`, au moins 20 items), story `S17-2` (`docs/backlog/backlog.md`). Matiere
  premiere identifiee a l'ouverture : `contenu-repris/quiz/cm1-comprehension.yml` (8 items) -
  **REFUSEE comme matiere premiere, voir mise a jour ci-dessous.**
- **Statut global** : **en construction** - aucun code, aucun contenu neuf ecrit a l'ouverture de
  cette fiche. Arbitrage prealable rendu le 2026-08-19 (voir section dediee) : principe **ACCEPTE**
  sous cinq conditions bloquantes (COMP-1 a COMP-5) ; matiere premiere **REFUSEE**. Aucun code ecrit
  a ce jour.
- **Derniere mise a jour** : 2026-08-19 (arbitrage prealable de `nadia-prof`, `camille-po` en tire
  les consequences sur cette fiche).

**Mise a jour du 2026-08-19, apres l'arbitrage prealable de `nadia-prof`**
(`squad/conformite/ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md`, section 1, branche
`s17-nadia`, tete `c5d2bce`, worktree dedie non fusionne). Elle a mesure les huit items de
`contenu-repris/quiz/cm1-comprehension.yml`
un par un : **8 sur 8** ont une bonne reponse recopiee mot pour mot du texte ; **4 sur 8** ont des
distracteurs absents du texte ; **au moins 4 sur 8** se repondent par la seule connaissance du monde,
texte masque ; chaque support est **une seule phrase**, donc **zero item sur huit** ne demande
l'inference que le referentiel nomme. Sa conclusion, au mot pres : *"ce n'est pas une reprise, c'est
un paquet neuf"*. **Je retire donc l'economie de reprise de mon estimation** : ce paquet est un
contenu integralement neuf, au meme rang de cout qu'une ligne sans aucune matiere premiere
identifiee (voir `DEC-0039`, amendement du 2026-08-19, section 1, pour ce que cela change a mon
plafond d'ouvertures). Les huit items repris ne sont pas retouches ; ils ne sont pas repris du tout.

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

**Mise a jour du 2026-08-19** : cette checklist reprend au mot pres les cinq conditions bloquantes
de l'arbitrage prealable de `nadia-prof` (COMP-1 a COMP-5), pour que `yanis-qa` en tire un test sans
avoir a lire deux documents. Le critere "sans gabarit ni feuille de style neuve" de l'ouverture de
cette fiche est **retire** : `nadia-prof` a mesure qu'il est incompatible avec la competence visee
(voir "Arbitrage prealable" ci-dessous) ; je le leve plutot que de sortir la story du perimetre.

- [ ] **COMP-1.** Texte visible, question et propositions masquees : aucun item ne se resout en
  cherchant laquelle des propositions figure dans le texte. Consequence executable : toutes les
  propositions d'un item emploient des mots presents dans le texte, ou aucune ne le fait - jamais
  une seule.
- [ ] **COMP-2.** Question et propositions visibles, texte masque : un enfant qui n'a pas lu le
  texte ne fait pas mieux que le hasard - ni fait de culture generale, ni distracteur absurde qui
  s'elimine seul.
- [ ] **COMP-3.** Au moins un tiers des items exigent de relier deux informations distinctes du
  texte, et pour ceux-la la bonne reponse n'est pas un fragment recopie du support.
- [ ] **COMP-4.** Le support, la question et toutes les propositions sont entierement visibles a
  360 x 640 sans defiler, mesure par `outils/mesurer-rendu.sh` sur la totalite des items du paquet.
- [ ] **COMP-5.** Le texte-support recoit un style distinct de celui de l'enonce, plus petit
  (budget mesure par `nadia-prof` : 5 lignes avec le style d'enonce actuel, insuffisant pour un
  texte a inference ; 8 lignes avec un style a la taille de la consigne, suffisant). Forme exacte
  (taille, police, couleur) proposee par `ines-design`, mecanisme par `theo-archi`, jugee par
  `nadia-prof` sur sa lisibilite au niveau vise avant tout item ecrit sur sa base.
- [ ] Le paquet compte au moins 20 items.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf autre que le
  support de style de COMP-5.

**Limite du proxy, a porter sur la matrice le jour ou cette ligne bouge** (`nadia-prof`, meme
arbitrage, section 1.5) : un item est un texte de 45 mots maximum : "textes courts, un texte par
question, sans lecture longue". Un enfant ne lira jamais, dans ce jeu, un texte assez long pour que
la comprehension d'un texte long soit en jeu.

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
l'ensemble ? C'est exactement le risque que `nadia-prof` a nomme pour "Le bon son" en s02.

**Reponse rendue le 2026-08-19**
(`ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md`, section 1) : **le principe est ACCEPTE**
- "une question a choix multiple sur un texte affiche peut verifier une comprehension reelle... ce
n'est pas la forme qui decide, c'est la construction des items" - sous les cinq conditions COMP-1 a
COMP-5 reprises dans la checklist ci-dessus. **La matiere premiere identifiee a l'ouverture est
REFUSEE** : les huit items de `contenu-repris/quiz/cm1-comprehension.yml` sont exactement le defaut
qu'elle refuse ("l'appariement visuel de s02, transpose du grapheme au mot"), et ne sont pas
retouchables - le paquet a ecrire est integralement neuf. `nadia-prof` a egalement mesure que mon
critere d'ouverture "sans gabarit ni feuille de style neuve" ne tenait pas avec la competence visee
(budget de 5 lignes au style d'enonce actuel, insuffisant pour un texte a inference) ; je le leve en
faveur de COMP-5 plutot que de laisser sa reponse au principe basculer a NON, ce qu'elle a ecrit se
produirait si ce critere restait tel quel.
