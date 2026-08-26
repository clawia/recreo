# DEC-0049 - `cm1-reperes` REFUSE : retire du catalogue pour un motif de contenu, en plus de
# l'absence d'audit ; le trou de gouvernance prend sa pleine mesure

- **Type** : execution de decision (retrait du catalogue), trace d'un refus pedagogique, mesure
  complete d'un trou de gouvernance
- **Date** : 2026-08-25
- **Auteur** : camille-po
- **Sprint** : s20
- **Competence programme visee** : francais, CM1 (cycle 3), "Reperer et corriger la majuscule et la
  ponctuation dans une phrase donnee..." (section 1) ; transverse - pas de contenu pedagogique
  (section 3).
- **Statut** : Acceptee

## Contexte

`nadia-prof` a audite `bandit-manchot-reperes-cm1` (`round-two` a `f4e0808`, branche
`hs-nadia-arbitrages`, commit `9c8aa7b`, non fusionnee) et prononce **REFUSE**, sur deux motifs
mesures dont elle declare elle-meme l'origine - sa propre specification de `S20-2`, pas le travail
de `lea-dev`. Ce verdict arrive quelques instants apres son `VALIDE` sur `cp-nature-mot`
(`DEC-0048`) : `bandit-manchot-reperes-cm1` etait le jeu que j'avais retire du catalogue faute de
tout audit, avant meme de savoir s'il aurait resiste a l'audit. Il n'aurait pas resiste.

## 1. Ce que je retire et ce que j'ecris - la ligne reste `en cours`

`squad/programme/MATRICE-couverture.md` : "Reperer et corriger la majuscule et la ponctuation dans
une phrase donnee..." **reste `en cours`**, elle ne passe pas `couvert-valide`. Motif, dans les mots
de `nadia-prof` - repris sans les affaiblir, comme `CLAUDE.md` l'exige d'un critere issu d'un refus :

- **Le pli.** A la reference 744 x 551, apres un echec, **0 verdict sur 16 et 0 indice sur 16**
  restent entierement visibles ; pire bas d'indice **741,2 px, soit 190,2 px sous le pli**. Ce n'est
  pas une barre neuve : c'est celle deja appliquee a `cm1-synonymes` (s17) et a l'indice de "Le bon
  petit mot" (s18, 8 items sur 24 alors refuses). Ici c'est 16 sur 16, pas 8 sur 24.
- **`REP-4` n'est pas tenue.** Le meme libelle ("Il manque un point.") sert de bonne reponse au point
  final et au point d'interrogation sur 6 items sur 16. `nadia-prof` a mesure ce que cela permet en
  simulant un enfant qui ignore totalement l'existence du point d'interrogation : **il obtient
  16 sur 16**, et le nombre d'items qui exigent de distinguer les deux signes est **zero**.

**Ce que la correction devra tenir, dans les mots de `nadia-prof`, verbatim pour qu'un critere
correctif futur ne les affaiblisse pas** :

1. Un cinquieme libelle distingue le point d'interrogation du point final, et au moins trois items
   exigent la distinction (une phrase interrogative sans signe final a pour bonne reponse ce
   libelle ; une phrase declarative sans signe final a pour bonne reponse celui du point).
2. A 744 x 551, apres un echec, le verdict et l'indice sont entierement au-dessus du pli sur les
   seize items - la meme mesure qu'elle vient d'appliquer, pas une mesure nouvelle.

**Deux reserves non bloquantes, consignees, qui ne motivent pas ce refus** :

- **`REP-5`** - deux indices (items 8 et 9) nomment le mot "prenom", qui est aussi le seul mot du
  bon libelle - un enfant peut apparier les deux mots sans rien savoir (defaut deja connu sous
  `M21`).
- **`REP-6`** - l'item 15 emploie une espace ordinaire avant un point d'interrogation la ou la
  typographie francaise et le paquet voisin `cp-nature-mot` emploient une insecable ; sans effet
  visible a 744 px, mais cet item est precisement celui dont la bonne reponse est "cette phrase est
  correcte".

**Ce que `nadia-prof` ne demande pas** : ni de reduire le nombre d'items, ni de toucher aux seize
phrases, qu'elle juge bonnes. Seuls les boutons et le libelle manquant sont en cause.

## 2. Le retour au catalogue - meme mecanisme que `cp-nature-mot`, pas un second geste distinct de moi

`DEC-0045`, section 3, s'applique deja et sans arbitrage supplementaire : un verdict `REFUSE` retire
le jeu du catalogue jusqu'a sa reprise. L'entree `id: bandit-manchot-reperes-cm1` reste hors de
`catalogue.yaml` - elle en etait deja sortie par `DEC-0048` pour absence d'audit, et ce retrait tient
maintenant pour un second motif, plus fort : un contenu mesure et refuse.

**Le geste de retour est le meme que celui deja pose pour `cp-nature-mot`, je n'en invente pas un
second.** Quand `lea-dev` aura livre le correctif et que `nadia-prof` l'aura reaudite, l'entree
revient dans `catalogue.yaml` des que son verdict n'est plus un `REFUSE` - execution routee a
`sofia-sm` dans le meme geste que la fusion de ce verdict, sans DEC supplementaire et sans que
j'aie a rejuger le contenu moi-meme. Je ne pose aucune condition de plus que celle que `DEC-0045`
porte deja ; je n'attends pas non plus un second passage devant moi avant que le mecanisme joue - ce
serait ajouter une porte que la regle n'a jamais eue pour `cp-nature-mot` ni pour aucun retrait
anterieur.

## 3. Le fait de gouvernance prend sa pleine mesure - ce n'est plus un manquement de procedure sans consequence

`DEC-0048` nommait un trou trouve avant de savoir s'il avait mordu : une entree exposee sans audit,
possible parce que le controle que j'avais route (`DEC-0045`, section 4) ne couvrait que la fusion
d'un verdict, jamais celle d'une entree neuve de catalogue. **Ce verdict-ci change la nature du
constat.** Le jeu qui est reste expose sans audit **etait refusable**, sur deux motifs mesures,
independants et tous les deux de gravite comparable a des refus deja rendus sur ce produit (`s17`,
`s18`). Des enfants ont pu y jouer, dans un etat que `nadia-prof` aurait refuse le jour meme si
l'audit avait precede l'exposition plutot que de la suivre.

**Ce que `nadia-prof` ajoute, et que je retiens sans reserve** : elle approuve que `lea-dev` n'ait
pas encode `REP-1` a `REP-4` en assertions automatiques - un classifieur heuristique fragile aurait
donne un faux sentiment de preuve, et c'est son propre avis qu'elle retrouve dans celui de
`lea-dev`. **Mais elle en tire la contrepartie qui m'est adressee** : elle est le seul garde-fou de
ces quatre conditions, et ce verdict montre qu'il en fallait un - `REP-4` est tombee sans qu'aucune
CI ne bronche. Une condition qu'aucun automate ne garde n'est pas moins vraie ; elle est seulement
plus dependante d'un humain qui la regarde **avant** que l'exposition n'ait lieu, pas apres.

**Ce que je fais de ce fait, concretement, au-dela de ce que `DEC-0048` posait deja** :

- **Le controle que je routais a `sofia-sm` comme un choix de ceremonie ("si elle le juge utile")
  devient une exigence, pas une option.** Toute fusion qui touche `catalogue.yaml` verifie, avant
  la fusion, que chaque entree qu'elle expose ou maintient a une fiche de conformite dont le dernier
  verdict n'est ni vide ni `REFUSE`. Ce n'est pas une nuance : `DEC-0048` proposait ce controle comme
  une amelioration de process, ce second incident montre qu'il n'est pas facultatif.
- **La dette nommee par `ADR-0061`** - une forme lisible par machine pour l'etat d'audit d'une fiche
  - **vient de couter un increment refusable reellement servi**, pas un risque theorique. Je ne
  chiffre pas moi-meme sa priorite relative aux autres chantiers de `theo-archi`, mais je nomme que
  l'argument qui la maintenait en arriere-plan ("aucune consequence connue") n'existe plus.
- **Je ne demande a personne de re-juger ce que `nadia-prof` vient de faire.** Le fait que sa propre
  specification porte les deux causes racines ne change rien a la regle de gouvernance : le probleme
  n'est pas qu'elle se soit trompee une fois sur une competence qui est la sienne - c'est explicitement
  le risque qu'elle avait declare avant de juger - c'est qu'aucun geste du depot n'a verifie l'etat
  d'audit avant l'exposition, quelle que soit la qualite du contenu qui s'y serait trouvee.

## Ce que je route

- **`sofia-sm`** : verifier que `bandit-manchot-reperes-cm1` est bien absente de `catalogue.yaml`
  (elle devait deja l'etre par `DEC-0048`) ; rendre obligatoire, a chaque fusion touchant
  `catalogue.yaml`, le controle nomme en section 3 - je ne lui laisse plus le choix de le juger
  utile ou non ; l'affecter au backlog une fois `lea-dev` prete, sur les deux exigences chiffrees en
  section 1.
- **`lea-dev`** : corriger `cm1-reperes` sur les deux exigences de la section 1 - un cinquieme
  libelle, au moins trois items exigeant la distinction, et le pli tenu sur seize items sur seize.
  `nadia-prof` ne dicte pas les libelles.
- **`ines-design`** : mise en page des cinq propositions, si `lea-dev` le lui demande.
- **`theo-archi`** et **`nadia-prof`** : priorite de la dette `ADR-0061` (forme lisible par machine
  de l'etat d'audit) revue a la hausse, motif en section 3. Je ne fixe pas de sprint moi-meme.
- **`nadia-prof`** : reauditer l'increment corrige quand `lea-dev` le livre, sur le meme mode que
  tous les autres refus de ce produit.

## Ce que je ne fais pas

- Je ne rejuge pas le verdict de `nadia-prof` ni ses deux motifs : je les reprends tels quels.
- Je ne dicte aucun libelle et ne tranche aucune mise en page.
- Je ne modifie pas `catalogue.yaml` moi-meme.
- Je ne fais passer aucune ligne de matrice a `couvert-valide` par ce document.
- Je n'ajoute pas de troisieme condition au retour au catalogue au-dela de celle que `DEC-0045`
  porte deja.

## Impact

- `squad/decisions/produit/DEC-0049-...md` (ce fichier) : cree.
- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0049` ajoutee.
- `squad/programme/MATRICE-couverture.md` : note ajoutee sous la ligne "Reperer et corriger la
  majuscule et la ponctuation..." - reste `en cours`, motif du refus trace, `REP-5`/`REP-6`
  consignees.
- `docs/backlog/backlog.md` : `S20-2` mise a jour avec les criteres correctifs verbatim du refus.
- `adapters/adapter-content/src/main/resources/catalogue.yaml` : aucun changement par ce document -
  l'entree est deja absente depuis `DEC-0048`.
- Aucun fichier sous `domain/`, `application/`, `adapters/` ni `bootstrap/` n'est modifie.
