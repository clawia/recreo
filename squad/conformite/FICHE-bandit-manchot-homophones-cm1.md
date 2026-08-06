# Fiche de conformite - bandit-manchot-homophones-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec
  `bandit-manchot-additions-cp` et `bandit-manchot-le-bon-son-cp`).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-homophones.yaml`,
  10 items, `type: question`. Matiere premiere issue de `contenu-repris/`.
- **Statut global** : **VALIDE SOUS RESERVE** (Done pedagogique accorde le 2026-08-06, premier
  audit). C'est le premier contenu de CM1 du produit.
- **Derniere mise a jour** : 2026-08-06

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CM1 (cycle 3) | Orthographe et grammaire | Distinguer et utiliser correctement les principaux homophones grammaticaux (et/est, son/sont, a/a...) | `squad/programme/francais/competences-cm1.md` |

## Criteres pedagogiques attendus - camille-po

> Section vide a l'ouverture de cette fiche : elle appartient a `camille-po` et je ne la
> pre-remplis pas. Mes reserves ci-dessous sont ecrites pour pouvoir etre converties en criteres
> et en stories sans avoir a me redemander ce que je voulais dire.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Verdict - premier audit, contenu livre en s04

- **Verdict** : **VALIDE SOUS RESERVE.** Done pedagogique accorde. Ce jeu peut etre mis devant
  une classe de CM1.
- **Date** : 2026-08-06.
- **Commit audite** : `c801eb8` (`HEAD` de `round-two`).
- **Moyens** : audit **joue**, non degrade. Fat jar construit et lance en local (JDK 21.0.10),
  parties completes jouees en repondant juste et en me trompant volontairement, y compris jusqu'a
  l'ecran de fin. Rendu reel mesure au navigateur sans tete.

#### 1. Exactitude du contenu - irreprochable, verifiee item par item

Les dix phrases, leur bonne reponse et leur distracteur, un par un :

| Enonce | Attendu | Distracteur | Verdict |
|---|---|---|---|
| "Il ___ mangé une pomme." | `a` | `à` | juste, auxiliaire avoir |
| "Je vais ___ l'école." | `à` | `a` | juste, preposition |
| "Papa ___ maman font la cuisine." | `et` | `est` | juste, "font" au pluriel interdit "est" |
| "Le chat ___ noir." | `est` | `et` | juste |
| "Les enfants ___ partis." | `sont` | `son` | juste |
| "Il prend ___ cartable." | `son` | `sont` | juste |
| "Ils ___ un chien." | `ont` | `on` | juste |
| "___ joue dans la cour." | `On` | `Ont` | juste, majuscule correcte en tete de phrase |
| "___ vas-tu ?" | `Où` | `Ou` | juste |
| "Tu veux du thé ___ du café ?" | `ou` | `où` | juste |

**Zero erreur factuelle.** Chaque phrase n'admet qu'une seule lecture grammaticalement correcte :
il n'y a pas d'item ou les deux propositions passeraient. Les cinq couples annonces (a/à, et/est,
on/ont, son/sont, ou/où) sont tous presents, chacun deux fois, une fois dans chaque sens - c'est
la bonne facon de construire ce type d'exercice, parce qu'elle interdit a l'enfant de retenir
"c'est toujours celui avec l'accent".

**Les distracteurs sont plausibles par construction** : le distracteur est l'autre membre du
couple d'homophones. Il n'y a donc aucun distracteur absurde possible, et aucune devinette. C'est
le point fort de ce paquet.

#### 2. Rattachement au referentiel - exact

La ligne du referentiel CM1 est "Distinguer et utiliser correctement les principaux homophones
grammaticaux (`et/est`, `son/sont`, `a/à`...)". C'est bien cette competence-la que l'increment
travaille, pas une competence voisine plus facile. Rien a redire.

#### 3. Niveau et lisibilite - conformes au CM1

Phrases de cinq a sept mots, vocabulaire entierement courant, aucune tournure obscure. Un enfant
de CM1 lit cela sans effort, ce qui est ce qu'on veut : l'effort doit porter sur le choix du mot,
pas sur le dechiffrage de la phrase. La consigne "Quel mot complète la phrase ?" fait cinq mots
et est exacte. Le trou est marque par trois traits soulignes, bien visibles a 32 px gras.

#### 4. Biais et ton

Pas de condescendance, pas d'infantilisation, aucun classement ni comparaison a d'autres enfants.
Deux points mineurs, portes en reserve et non en motif : voir reserves 3 et 4.

#### 5. Ce que ce verdict ne dit pas

Il ne porte que sur le contenu `cm1-homophones` et sur les libelles de ce jeu. **La saillance des
boutons sur l'ecran de retour est refusee separement**, au titre du chantier Bootstrap, dans
`FICHE-bandit-manchot-additions-cp.md`. Ce refus concerne l'habillage commun aux trois jeux, donc
celui-ci aussi ; il ne remet pas en cause le Done pedagogique accorde ici sur le contenu.

#### Reserves - a convertir en stories par camille-po

1. **Dix items pour une partie de dix manches : chaque partie est identique.** L'enfant voit
   l'integralite du paquet a chaque fois, seul l'ordre change. Des la deuxieme partie il ne
   reste plus rien a apprendre, il ne reste qu'a se souvenir. Il faut plus d'items que de manches.
2. **Les phrases sont trop courtes pour travailler la competence a la profondeur du CM1.** Dans
   neuf items sur dix, le sujet est colle au trou ("Le chat ___ noir.", "Ils ___ un chien."), ce
   qui rend le choix quasi automatique. Le seul item qui demande vraiment de raisonner est
   "Papa ___ maman font la cuisine.", parce qu'il faut aller chercher "font" apres le trou. C'est
   le modele a suivre : la competence ne se travaille que quand la phrase oblige a regarder
   ailleurs que juste avant le trou. En l'etat, l'exercice est juste, mais il est de niveau CE1 ou
   CE2 plutot que CM1 - consolidation, pas apprentissage.
3. **Aucun sujet feminin dans tout le paquet.** "Il" apparait deux fois, "Ils" une fois ; "elle"
   et "elles" n'apparaissent jamais. Sur dix phrases dans un paquet de grammaire, c'est un
   desequilibre evitable, d'autant que le remplacement est gratuit ("Elle ___ mangé une pomme.").
4. **"Papa ___ maman font la cuisine." presuppose une configuration familiale.** Une occurrence
   sur dix, et par ailleurs non stereotypee sur les roles puisque les deux parents cuisinent
   ensemble - ce qui est a porter au credit de l'item. Je le signale sans en faire un motif :
   tous les enfants n'ont pas un papa et une maman. "Léa ___ Tom font la cuisine." dirait la
   meme chose grammaticalement.
5. **Deux propositions donnent une chance sur deux au hasard.** C'est inherent a l'exercice - on
   ne peut pas proposer "et" comme distracteur de "a/à" - et je ne le compte donc pas comme un
   defaut de conception. Mais il faut en tirer la consequence : **le score de ce jeu ne mesure
   pas grand-chose**, un enfant qui repond au hasard finit a 5 sur 10. A garder en tete le jour ou
   le score servira a autre chose qu'a s'encourager.
6. **Aucune aide n'est disponible dans ce jeu.** Meme constat structurel que pour
   `bandit-manchot-le-bon-son-cp`, motif 5 de sa fiche : un paquet `type: question` ne peut pas
   porter d'indice, parce que `QuestionContentSourceFactory` construit chaque `Question` sans
   champ `indice`. C'est moins grave ici qu'au CP - un enfant de CM1 est plus autonome, et
   l'increment reste utilisable sans - donc c'est une reserve et non un motif. Mais quand le
   dispositif sera ouvert aux paquets `question`, ce jeu doit en beneficier : "Remplace par
   *avait* pour t'aider." est exactement le genre d'indice qui apprend quelque chose.
7. **L'accueil ne dit pas a quel niveau s'adresse le jeu.** "Le bon petit mot" ne porte aucune
   mention de CM1 alors que "Calcul CP" porte la sienne. Question produit pour `camille-po`.
8. **"Tu veux du thé ___ du café ?"** propose a un enfant de choisir entre deux boissons
   d'adulte. Ce n'est pas grave et ce n'est pas faux, mais dans un paquet de dix phrases on peut
   trouver un choix qui parle a un enfant de neuf ans.

#### Suites hors de ma main

- `squad/programme/MATRICE-couverture.md`, ligne
  `francais | CM1 | Orthographe et grammaire | Distinguer et utiliser correctement les principaux
  homophones grammaticaux` : **reste `en cours`, ne passe pas encore a `couvert-valide`.**
  Motif, et il est franc : le Done pedagogique est accorde, mais le Done et la matrice ne disent
  pas la meme chose - le Done porte sur l'increment, la matrice porte sur la couverture d'une
  competence. La competence dit "**les principaux** homophones grammaticaux" et le referentiel
  laisse la liste ouverte par des points de suspension. Le paquet en couvre cinq : a/à, et/est,
  on/ont, son/sont, ou/où. **Manquent, parmi ceux qu'un CM1 rencontre couramment : ce/se, ces/ses,
  la/là/l'a, mais/mes, peu/peut.** Cinq couples sur une dizaine, ce n'est pas "les principaux".
  **Condition pour que je fasse passer cette ligne a `couvert-valide` : ces cinq couples ajoutes
  au paquet, avec la reserve 2 traitee dans le meme geste** (des phrases qui obligent a regarder
  ailleurs que juste avant le trou), parce qu'ajouter cinq couples faciles ne ferait qu'allonger
  un exercice de CE2. Je n'ai pas edite cette ligne, elle appartient a `camille-po`.
