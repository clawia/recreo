# DEC-0004 - Textes, forme du score et seuils chiffres pour lever le refus pedagogique du sprint 1

- **Type** : contenu, comportement de mecanique, seuils
- **Date** : 2026-08-05
- **Auteur** : camille-po
- **Sprint** : s01
- **Competence programme visee** : transverse pour les points 1 a 4 (comportement generique de la
  mecanique bandit manchot et de l'interface qui l'entoure) ; mathematiques, CP (cycle 2), domaine
  "Nombres et calculs" - "Calcul mental - additions dans la limite de 10" pour le point 5, meme
  ligne que `DEC-0001` (`squad/programme/mathematiques/competences-cp.md`).
- **Statut** : Acceptee

## Contexte

`theo-archi` a rendu son avis de faisabilite sur le sprint 1
(`squad/sprints/s01-lever-refus-pedagogique/avis-faisabilite.md`) et y route cinq points qui ne
sont pas techniques : ils portent sur des valeurs et des formulations que seule cette decision
peut fixer, faute de quoi cinq stories (S1-2, S1-3, S1-4, S1-5, S1-7) restent non testables en
l'etat par `yanis-qa`. Chaque point s'appuie sur les constats precis de `nadia-prof` dans
`squad/conformite/FICHE-bandit-manchot-additions-cp.md` (verdict REFUSE, 2026-08-04), plus precis
que les stories elles-memes.

Je regroupe les cinq points dans une seule decision parce qu'ils partagent le meme fait
generateur (lever le refus pedagogique d'un seul et meme increment, route en un seul avis) et
qu'aucun ne depend d'un arbitrage distinct des autres.

## Decision

### 1. Forme du score (S1-2, motif de refus 2)

Le score affiche a l'enfant est **un nombre entier seul : le nombre de bonnes reponses depuis le
debut de la partie**, jamais mis en rapport avec le nombre d'essais rates, sous la forme
`Score : N` (par exemple `Score : 3`). Aucune barre oblique, aucune notation `x / y`, aucun
symbole `+`, `-`, `x` ou `/`. Le mot `Score` est conserve : aucun motif de refus ni aucune reserve
ne le vise, ce n'est pas un mot du perimetre de S1-8.

Je retiens un nombre en texte plutot qu'une forme iconographique (etoiles, jetons), pourtant
suggeree par `nadia-prof`, pour une seule raison produit : une forme dessinee demande un jugement
de perception (est-ce qu'un jeton ressemble a un signe operatoire ?) que personne n'a mandat de
rendre aujourd'hui - obstacle deja signale par `theo-archi` sur S1-6 et S1-9, non resolu ce
sprint. Un nombre en texte se specifie et se teste completement sans ce jugement : sa forme exacte
est ecrite ici, noir sur blanc, et sa conformite se verifie par simple lecture du texte rendu.
Le jour ou un agent design est mandate (voir avis de `theo-archi`, risque 2), cette decision
pourra etre remplacee par une DEC ulterieure sans que rien de cote domaine ne change : `Score` est
un modele de vue, pas un type du domaine.

### 2. Message d'erreur et bouton (S1-3, motif de refus 3)

Le fragment `fragments/erreur.html` (`ADR-0011`) affiche exactement :

- Message : **"Oups ! Le jeu a un souci."**
- Bouton : **"Recommencer"**, qui ramene a `/`.

Ces deux textes sont volontairement courts (respectivement six mots et un mot), ne comportent
aucune lettre accentuee a defaut d'en requerir dans ces mots precis, et ne blament jamais
l'enfant : "le jeu a un souci" met le probleme du cote du jeu, jamais de l'enfant qui n'a rien
fait de mal. "Recommencer" est un mot que l'enfant retrouve deja dans son quotidien de jeu (un
jeu de societe, une partie ratee) et ne presuppose aucune comprehension de ce qui a echoue.

### 3. Nombre de manches et message de reprise (S1-4, reserves 1 et 7)

**Une partie dure exactement 10 manches.** Je retiens la valeur suggeree par `nadia-prof`
(`FICHE-bandit-manchot-additions-cp.md`, reserve 1) pour trois raisons :

- un enfant de CP tient son attention sur une tache repetitive quelques minutes, pas des dizaines
  de manches : a quelques secondes par calcul simple, 10 manches font une session d'une a deux
  minutes, assez courte pour se terminer avant que l'attention decroche, assez longue pour ne pas
  se terminer avant que l'enfant ait eu le temps de s'installer dans le jeu ;
- 10 est un nombre que l'enfant sait deja denombrer et sur lequel il peut se representer une
  progression ("il en reste combien") sans manipuler un ordre de grandeur qu'il ne maitrise pas
  encore en dehors du jeu (le referentiel CP le fait denombrer et comparer des nombres jusqu'a
  100, donc 10 n'est pas un obstacle de numeration) ;
- c'est aussi la borne du contenu lui-meme (additions dont la somme reste dans la limite de 10) :
  garder le meme nombre pour la longueur de partie et pour la borne arithmetique n'est pas un
  hasard recherche, mais evite d'introduire un deuxieme nombre de reference sans aucun besoin.

**Message affiche quand la partie precedente n'est plus retrouvee** (session perdue au
rechargement, cas que `ADR-0011` ne couvre pas puisqu'aucune exception n'est levee - voir l'avis
de `theo-archi`, section S1-4, "quatrieme critere") :

- Message : **"Ta partie s'est arrêtée. Choisis un nouveau jeu !"**, affiche au-dessus de la
  liste des jeux plutot qu'un retour silencieux a l'accueil qui laisserait croire que rien ne
  s'est passe.

Quand la partie precedente **est** retrouvable (cas normal d'un rechargement pendant une session
HTTP encore valide), aucun message n'est affiche : l'enfant retrouve directement l'etat de sa
manche en cours, ce n'est pas une reprise qui se remarque, c'est une continuite.

### 4. Ce qu'est une "manche plus simple" et ses deux seuils (S1-5, reserve 3)

Je retiens le chemin 2 recommande par `theo-archi` : **une "manche plus simple" est un item que
l'enfant a deja resolu avec succes au moins une fois dans la session en cours**, represente a la
place du prochain tirage normal - pas un item mathematiquement plus facile (une somme plus
petite). Cette interpretation ne rouvre aucun contrat de contenu (`ADR-0006` reste inchange) et
reste generique a toute matiere future, ce que la seconde option (exposer une notion de
difficulte sur `ContentSource`) ne garantit pas.

Sur le fond pedagogique, cette interpretation me convient **comme premiere reponse au constat de
Nadia** ("proposer quelque chose de different a l'enfant qui rate plusieurs fois de suite") : un
item deja reussi n'est pas garanti plus facile au sens arithmetique, mais il change ce que
l'enfant voit, casse la repetition identique a l'identique qu'elle a decrite (vingt fois la meme
phrase), et lui redonne une experience de reussite recente avant de reprendre la difficulte
normale. Je n'affirme pas que c'est la meilleure reponse pedagogique possible - ce jugement
appartient a `nadia-prof` et sera rendu quand elle rejouera cet increment. Si son audit conclut
que cette acception ne repond pas au besoin qu'elle a constate (un enfant qui echoue sur des
sommes proches de 10 a besoin de sommes plus petites, pas de revoir une question deja vue), le
chemin 1 de `theo-archi` (exposer une notion de difficulte dans le contrat de contenu) devient la
solution a instruire, avec son propre ADR, et cette decision sera remplacee en consequence.

**Deux seuils, deux compteurs independants, la meme valeur numerique par souci de coherence** :

- **Indice** : un meme item auquel l'enfant repond faux a **trois reprises** (deja ecrit dans le
  critere d'acceptation existant de S1-5, non modifie ici) declenche l'affichage d'un indice la
  prochaine fois que cet item est represente.
- **Manche plus simple** : **trois echecs consecutifs, quel que soit l'item** (pas necessairement
  le meme d'une manche a l'autre), declenchent la presentation d'un item deja reussi dans la
  session a la manche suivante. Je retiens le meme chiffre que l'indice pour la meme raison qui
  guide le nombre de manches au point 3 : ne pas laisser une serie d'echecs s'installer plus
  longtemps pour un enfant de six ans, sous peine de laisser advenir exactement ce que Nadia a
  observe (vingt echecs d'affilee sans aucune reaction du jeu). Attendre plus (cinq, dix) risque
  de decourager l'enfant avant que le jeu ne reagisse ; un seuil plus bas (deux, un seul echec)
  risque a l'inverse de traiter une erreur isolee et normale dans un apprentissage comme un signal
  d'alerte.
- **Cas ou aucun item n'a encore ete reussi dans la session** (le seuil de trois echecs
  consecutifs est atteint des le debut de la partie, avant toute reussite) : la mecanique presente
  le prochain tirage normal, sans manche plus simple ni erreur technique. Ce n'est pas un echec du
  mecanisme, c'est un cas ou il n'a rien a proposer de plus simple pour l'instant.

### 5. Seuil chiffre pour la proportion d'additions a operande nul (S1-7, reserve 2)

**Sur 200 tirages effectues avec une graine aleatoire fixee, la proportion de tirages comportant
au moins un operande nul reste comprise entre 5 % et 15 % inclus (soit entre 10 et 30 tirages sur
200).**

Justification des deux bornes, a partir des chiffres constates par Nadia (16 manches sur 61, soit
26 %, pour une esperance theorique de 32 % avec un tirage uniforme sur les 66 couples
possibles) :

- **Borne haute a 15 %** : c'est "nettement inferieur au tiers" au sens strict du mot -
  c'est-a-dire moins de la moitie de la proportion theorique constatee (32 %) et moins de la
  moitie de la proportion effectivement observee (26 %), pas une reduction marginale qui laisserait
  planer un doute sur l'intention.
- **Borne basse a 5 %** : garantit que "sans jamais tomber a zero" est verifie sur un echantillon
  fini et non par hasard - a 5 %, un tirage de 200 essais produit en moyenne 10 occurrences, assez
  pour qu'un test avec une graine fixee les observe de facon fiable et reproductible, tout en
  restant clairement une exception plutot que la norme. La propriete du zero garde ainsi sa place
  dans le jeu (elle fait partie du programme de calcul), sans dominer le temps de jeu.
- **200 tirages** : suffisamment grand pour qu'un ecart de quelques tirages ne fasse pas basculer
  le test d'un cote ou de l'autre de la fourchette (contrairement a l'echantillon de 61 manches de
  l'audit, trop petit pour distinguer une proportion de 20 % d'une proportion de 30 % avec
  certitude), suffisamment petit pour qu'un test avec une graine fixee reste rapide.

## Impact

- `docs/backlog/backlog.md`, sprint 1 : criteres d'acceptation de S1-2, S1-3, S1-4, S1-5 et S1-7
  reecrits pour porter les valeurs decidees ici, avec un lien vers cette DEC. Le detail est dans
  le backlog lui-meme, pas repete ici.
- `squad/programme/MATRICE-couverture.md` : aucune ligne affectee. La ligne mathematiques CP
  "Calcul mental - additions dans la limite de 10 (puis 20)" reste `en cours` : cette decision
  fixe des valeurs de story, elle ne constitue pas un verdict pedagogique, qui reste la seule main
  de `nadia-prof`.
- `squad/conformite/FICHE-bandit-manchot-additions-cp.md` : bloc de validation pedagogique non
  touche par cette decision, conformement au mandat de `camille-po`.
- Aucun ADR n'est modifie : les trois changements de comportement de mecanique decrits au point 4
  (compteur de succes par item, seuil de trois echecs consecutifs, repli sur le tirage normal en
  l'absence d'item deja reussi) restent a l'interieur du perimetre deja ouvert par
  `theo-archi` dans son avis de faisabilite (chemin 2, aucun ADR requis) ; si le chemin 1 devait un
  jour etre retenu a la suite d'un refus de Nadia, ce serait alors `theo-archi` qui ouvrirait
  l'ADR correspondant, pas cette decision.

## Amendement du 2026-08-05 (s03)

**Contexte.** `theo-archi`, dans son avis de faisabilite du sprint 3
(`squad/sprints/s03-completer-mathematiques-cp/avis-faisabilite.md`, section S3-1), a mesure sur
200 000 tirages que le tirage retenu par `DEC-0007` point 2 (uniforme sur les 65 couples valides)
viole, seul, le point 5 ci-dessus : 30,7 % de tirages a operande nul, contre 5 % a 15 % exiges.
Garder le filtre existant sur les operandes nuls tel quel (accepter un tirage a operande nul une
fois sur cinq, `ACCEPTATION_OPERANDE_NUL_SUR = 5`) tient le point 5 (8,09 % mesure) mais fait
sortir deux sommes de la table de `DEC-0007` point 2, parce que cette table a ete calculee sur le
tirage nu et non sur le tirage reellement compose avec ce filtre.

**Ce que je tranche.** La proportion de tirages a operande nul que je retiens comme cible
pedagogique n'est pas seulement "quelque part entre 5 % et 15 %" : c'est **12,9 %**, la valeur
que `nadia-prof` a mesuree en s01 et qu'elle a explicitement qualifiee de bon reglage ("c'est le
bon reglage, et je le dis explicitement puisque la question m'est posee [...] la propriete du
zero fait partie du programme de calcul de CP, la retirer entierement serait une erreur, et une
manche sur dix la garde presente sans manger le temps de calcul"). Le point 5 ci-dessus (bande
5 % - 15 % sur 200 tirages) **n'est pas modifie** : 12,9 % s'y trouve deja, cet amendement precise
le point de la bande que je retiens, il n'en deplace pas les bornes.

Porte a la connaissance de l'equipe, la mesure de `theo-archi` : porter
`ACCEPTATION_OPERANDE_NUL_SUR` de 5 a 3 (accepter un tirage a operande nul une fois sur trois)
produit 12,92 % sur 200 000 tirages, satisfait le point 5 tel qu'ecrit et ne fait sortir aucune
somme de la table de `DEC-0007` point 2 une fois cette table recalculee sur le tirage compose (voir
l'amendement du meme jour a `DEC-0007-perimetre-s03-completer-avant-etendre.md`, point 2). Le
choix de la valeur exacte du filtre (implementation de
`AdditionsBorneesContentSource`) reste a `theo-archi` et aux developpeurs ; ce que je fixe ici est
la cible pedagogique qu'elle doit atteindre.

**Impact** : `docs/backlog/backlog.md`, story S3-1, la table de fourchettes (premier critere) est
recalculee en consequence dans le meme geste (voir l'amendement de `DEC-0007` point 2). Le
quatrieme critere de S3-1, qui verifie que le point 5 ci-dessus reste vert, n'est pas modifie par
cet amendement.

## Ce qui reste non testable par construction

- Que le message d'erreur et le message de reprise "rassurent" reellement un enfant plutot que de
  l'inquieter est une question de perception et de ton, jugee par `nadia-prof` a l'audit, pas par
  un automate. Ce qui est testable : la presence exacte du texte decide ici dans le fragment
  rendu, verifiable par un test d'integration jsoup (comparaison de sous-chaine litterale, jamais
  une expression reguliere, comme l'exige `ADR-0011`).
- Que 10 manches soient effectivement la bonne duree pour tenir l'attention d'un enfant de CP
  reste un jugement d'usage que seule `nadia-prof` peut confirmer en jouant l'increment. Ce qui
  est testable : qu'une partie s'arrete exactement au bout de 10 manches, jamais avant, jamais
  apres.
- Que remontrer un item deja reussi aide reellement l'enfant en difficulte, plutot que de le
  laisser sur son incomprehension initiale, est un jugement pedagogique de fond que je ne
  tranche pas ici (voir point 4) : je fixe un comportement observable, `nadia-prof` en jugera
  l'effet.
