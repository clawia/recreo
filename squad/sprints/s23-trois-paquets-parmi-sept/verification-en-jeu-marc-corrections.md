# Verification en jouant - correctifs RAN-7 et reserves ETA, marc-dev, 2026-08-30

Base : `round-two` a `86eea1d` (six lots fusionnes, 800 tests), worktree dedie `s23-marc-2`,
branche `s23-marc-2`. Methode identique a la premiere verification du sprint : fat jar construit
par `bash outils/mvn-jdk21.sh -B clean verify` (reacteur complet, BUILD SUCCESS), lance en local
sur le JDK 21 de la toolchain declaree, joue par requetes HTTP directes (`curl`, cookie de
session) sur les routes reelles de `PartieController` - pas de navigateur graphique disponible
dans cet environnement.

## `cp-ranger-nombres` - correctif `RAN-7`

Six parties completes, en se trompant volontairement sur chaque manche pour lire l'indice servi,
jusqu'a couvrir les 24 items. Verifie a l'ecran, pas seulement dans le fichier :

- L'item `37, 100, 16` (le second des deux defauts graves de `nadia-prof`) rend desormais
  l'indice *"Compare le nombre de chiffres de chaque nombre."* - jamais plus *"Compare les
  dizaines, puis les unites si besoin."*, qui designait le distracteur `100, 16, 37` a l'ecran.
- L'item `48, 2, 100` (le premier des deux) rend le meme indice corrige.
- Les items `66, 54, 59` et `94, 11, 56` (deux des trois defauts "ne separe rien"), tous deux a
  triplet purement a deux chiffres, rendent desormais *"Compare les dizaines, puis les unites si
  besoin."* - une methode qui, appliquee a leurs nombres, isole reellement le plus petit.

Aucune autre valeur affichee (enonce, propositions, score) n'a change entre les deux sessions de
jeu de ce sprint.

## `cm1-problemes-etapes` - reserves A, B, C fermees

Six parties supplementaires jouees en se trompant volontairement, ciblees pour couvrir les items
concernes par les trois reserves non bloquantes de `nadia-prof`.

- **Reserve A** : l'item du timbres rend *"La classe recolte 40 timbres, puis en recoit 15 de
  plus pour une collection commune. Elle en donne ensuite 10 a une ecole. Combien de timbres la
  classe garde-t-elle ?"* - "autre" retire, les trois propositions (`15`, `55`, `45`) et la bonne
  reponse (`45`) inchangees.
- **Reserve B** : les items alors 22, 23 et 24 rendent desormais *"en perd 15"* (reserve), *"en
  perd 22"* (ecole/chaises) et *"en perd 13"* (classe/crayons) au lieu de *"distribue"*,
  *"retire"*, *"vend"* - memes trois nombres, memes trois propositions, meme famille de
  distracteur (a)+(c) sur les trois.
- **Reserve C** : l'item de l'atelier de poterie rend *"3 ateliers, celui-ci compris, fabriquent
  chacun ce meme nombre de bols reussis."* L'item de l'atelier de couture (meme construction, non
  rencontre dans cette session de jeu) a recu la meme correction dans le fichier - verifie par
  lecture directe du YAML et par `PaquetCm1ProblemesEtapesTest`, qui recalcule les 48
  distracteurs sans ecart apres la modification.

## Ce que cette verification ne re-couvre pas

Elle ne rejoue pas l'integralite des 24 items de chaque paquet une seconde fois (deja fait a la
premiere verification du sprint, non invalidee par ces correctifs cibles) : elle cible les items
et les reserves que ce message de `sofia-sm` demandait de corriger, et confirme que rien d'autre
n'a bouge a l'ecran.
