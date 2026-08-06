# Fiche de conformite - bandit-manchot-le-bon-son-cp

- **Mecanique** : bandit manchot (mecanique generique, partagee avec
  `bandit-manchot-additions-cp` et `bandit-manchot-homophones-cm1`).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-le-bon-son.yaml`,
  21 items depuis le lot `lea-dev` de s05 (24 a l'audit du premier refus), `type: question`.
  Matiere premiere issue de `contenu-repris/`.
- **Statut global** : **REFUSE** (Done pedagogique refuse le 2026-08-06, premier audit). Le refus
  reste en vigueur : l'avis de conception ajoute en fin de fiche ne le leve pas et n'est pas un
  audit.
- **Derniere mise a jour** : 2026-08-06

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CP (cycle 2) | Lecture et comprehension de l'ecrit | Associer les graphemes aux phonemes (voyelles, consonnes simples, puis digraphes ch, ou, on...) | `squad/programme/francais/competences-cp.md` |

## Criteres pedagogiques attendus - camille-po

> Section vide a l'ouverture de cette fiche : elle appartient a `camille-po` et je ne la
> pre-remplis pas. Les motifs de mon refus, ci-dessous, sont ecrits pour pouvoir etre convertis
> en criteres et en stories sans avoir a me redemander ce que je voulais dire.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Verdict - premier audit, contenu livre en s04

- **Verdict** : **REFUSE.**
- **Date** : 2026-08-06.
- **Commit audite** : `c801eb8` (`HEAD` de `round-two`).
- **Moyens** : audit **joue**, non degrade. Fat jar construit et lance en local (JDK 21.0.10),
  partie complete de 10 manches jouee en repondant faux a chaque manche, plus 20 demarrages de
  partie pour relever les enonces tires. Rendu reel mesure au navigateur sans tete
  (boites en pixels et captures d'ecran), pas deduit de la feuille de style.

#### Ce que j'ai verifie et qui tient

- **Orthographe et accentuation** : irreprochables sur les 24 items, y compris `ÉTOILE`,
  `CHÂTEAU` et `BALANÇOIRE`. J'ai verifie les accents **sur la reponse HTTP**, pas seulement dans
  le fichier YAML : ils arrivent intacts a l'ecran. La relecture de `relecteur-pedagogique` est
  confirmee.
- **Aucun distracteur piege par construction** : sur les 24 items, aucun distracteur propose ne
  correspond a un son reellement present dans le mot affiche. C'est verifie item par item, et
  c'est un point de qualite reel : quelqu'un y a pense.
- **Biais et ton** : rien a signaler. Des noms d'objets et d'animaux, aucun prenom, aucune
  situation familiale, sociale ou culturelle presupposee. Aucun stereotype possible.
- **Habillage** : les boutons de reponse portent la meme taille et le meme poids que dans le jeu
  de mathematiques (mesure : 1200 x 62 px, texte 32 px gras). Le mot est affiche a 32 px gras.
  Rien a redire sur la lisibilite des caracteres eux-memes.

#### Motif 1 - la consigne annonce un son qui n'existe pas

La consigne affichee est **"Quel son entends-tu ?"**. **Il n'y a aucun son.** L'application ne
joue rien, ne contient aucun fichier audio et n'offre aucun bouton pour ecouter. L'enfant ne
peut rien entendre.

Un enfant de CP prend une consigne au pied de la lettre. Celui-ci va attendre un son, chercher
un bouton pour l'ecouter, ou conclure que quelque chose ne marche pas chez lui. C'est le premier
mot que l'increment lui adresse, et il est faux.

Ce n'est pas une maladresse de formulation isolee : c'est le symptome du motif 3.

#### Motif 2 - les crochets sont montres a l'enfant

Les trois boutons de reponse affichent litteralement `[an]`, `[on]`, `[in]`, `[ou]`, `[oi]`,
`[ch]`. Les crochets droits sont une convention de transcription d'adulte. Ils ne sont enseignes
nulle part au CP, et un enfant qui est precisement en train d'apprendre que chaque signe ecrit
correspond a un son va essayer de les dechiffrer.

Sur un bouton de reponse de quatre caracteres, **deux ne se prononcent pas**. Pour un enfant qui
construit sa table de correspondance graphemes-phonemes, ce n'est pas neutre : c'est du bruit
place exactement dans le canal qu'on lui demande de decoder.

Actionnable : afficher `an`, `on`, `in`, `ou`, `oi`, `ch` sans crochets. Si une marque de "ceci
est un son et non un mot" est jugee necessaire, elle doit etre visuelle (couleur, encadre,
pictogramme) et non typographique, et elle repasse devant moi.

#### Motif 3 - le contenu ne travaille pas la competence annoncee, et c'est la reponse a la question de camille-po

`camille-po` a rattache ce contenu a **"Associer les graphemes aux phonemes"** (lecture) plutot
qu'a **"Identifier un son dans un mot"** (langage oral et conscience phonologique), au motif que
le contenu montre un mot ecrit. **Ce rattachement est le bon, et c'est le seul defendable** : des
lors qu'aucun son n'est emis et que le mot est donne a lire, on est en lecture, pas en oral. Sur
la question de programme qui m'etait posee, je confirme le choix de `camille-po`.

**Mais le contenu, lui, ne travaille ni l'une ni l'autre.** Le mot est ecrit et les reponses
sont des **graphemes ecrits**. L'enfant n'a donc besoin d'aucun son pour reussir : il lui suffit
de retrouver les lettres `oi` dans `BALANÇOIRE` et d'appuyer sur `[oi]`. C'est de la mise en
correspondance visuelle de chaines de caracteres. Sur 23 des 24 items, la reussite est atteignable
sans passer par le moindre phoneme.

Le 24e item est **`DENT`, bonne reponse `[an]`**. C'est le seul item du paquet ou l'appariement
visuel echoue, parce que le mot ne contient ni `a` ni `an` : le grapheme est `en`. C'est donc le
seul item qui travaille reellement la competence annoncee - **et c'est aussi le seul qui va
contredire frontalement ce que l'enfant vient d'apprendre**, a savoir que le son [an] s'ecrit
`a-n`. Presente sans explication ni retour, cet item n'enseigne pas la variation graphemique : il
la fait passer pour une erreur.

Un exercice ou 23 items sur 24 se reussissent sans la competence visee, et ou le seul item qui
l'exige risque de desapprendre, ne couvre pas "associer les graphemes aux phonemes".

#### Motif 4 - trois items posent une question qui a deux reponses vraies

La consigne demande quel son on entend dans le mot. Sur trois items, **deux des sons du jeu sont
reellement presents dans le mot**, et un seul est accepte :

| Item | Reponse attendue | Autre son du jeu present dans le mot |
|---|---|---|
| `MOUCHE` | `[ou]` | `[ch]` |
| `MOUTON` | `[on]` | `[ou]` |
| `BALANÇOIRE` | `[oi]` | `[an]` |

Dans les trois cas le second son vrai est absent de la liste des propositions, donc l'enfant ne
peut pas etre compte faux pour lui. **Ce n'est pas une consolation, c'est le probleme.** L'enfant
qui entend correctement `[ou]` dans `MOUTON`, qui ne le trouve pas dans la liste et qui finit par
appuyer sur `[on]`, apprend que sa perception juste n'etait pas la bonne reponse. On lui enseigne
a se mefier de son oreille au moment precis ou on lui demande de s'en servir.

Actionnable : soit retirer ces trois items, soit reformuler la consigne pour qu'elle designe une
position ("Quel son entends-tu **au debut** du mot ?"), ce qui suppose de reordonner le paquet.

#### Motif 5 - aucune aide n'existe dans ce jeu, et ce n'est pas un oubli d'auteur

**J'ai joue une partie complete de 10 manches en repondant faux a chaque manche. Aucun indice
n'est jamais apparu.** L'enfant recoit dix fois de suite "Non, c'est [ou]." puis "C'est fini !"
avec un score de 0, et rien d'autre.

Ce n'est pas un champ oublie dans le YAML. C'est structurel :
`adapters/adapter-content/src/main/java/com/recreo/adapter/content/QuestionContentSourceFactory.java`
construit chaque item avec le constructeur a quatre arguments -

```java
return new Question(new ItemId("item-" + position), enonce, bonneReponse, distracteursTexte);
```

- et ne lit jamais de champ `indice`. **Un paquet `type: question` ne peut pas porter d'indice**,
meme si un auteur en ecrit un. L'aide que S3-2 et S3-3 viennent de rendre atteignable
(verifiee par moi en jeu sur le jeu de mathematiques, voir
`FICHE-bandit-manchot-additions-cp.md`) n'est atteignable que pour `type: additions-bornees`.

Autrement dit : le premier jeu de francais du produit est livre a des enfants de CP **sans le
dispositif d'aide dont l'absence avait motive mon refus de s00**. L'enfant pour qui ce dispositif
a ete ecrit - celui qui echoue en serie - est exactement celui qui n'y a pas droit ici.

Je decris le symptome ; la cause est technique et appartient a `theo-archi` et aux developpeurs.
Ce qui compte pour moi : **un enfant de CP qui rate trois fois de suite dans ce jeu doit recevoir
une aide**, comme dans le jeu de mathematiques.

#### Reserves, a traiter mais qui ne fondent pas a elles seules le refus

1. **Les mots sont trop longs pour le moment de l'annee ou cette competence se travaille.**
   `BALANÇOIRE` (10 lettres, une cedille), `PINCEAU`, `MANTEAU`, `CHÂTEAU` supposent un decodage
   deja solide. Or l'enfant doit decoder le mot **avant** de pouvoir repondre : la difficulte de
   lecture s'ajoute a la difficulte de l'exercice au lieu d'etre l'exercice.
2. **Aucune progression.** Les 24 items sont tires sans ordre de difficulte. Meme constat que ma
   reserve 3 de s00 sur le jeu de mathematiques.
3. **`[ch]` n'est pas de la meme nature que les cinq autres reponses.** `an`, `on`, `in`, `ou`,
   `oi` sont des voyelles ; `ch` est une consonne, digraphe. Les melanger dans le meme tirage sans
   que l'enfant sache jamais s'il cherche une voyelle ou une consonne augmente la charge sans
   rien apprendre.
4. **L'accueil ne dit pas a quel niveau s'adresse le jeu.** "Le bon son" et "Le bon petit mot"
   ne portent aucune mention de niveau, alors que "Calcul CP" en porte une. Un enfant de CP peut
   ouvrir le jeu de CM1 et l'inverse. C'est une question produit pour `camille-po`, pas un motif.

#### Ce qu'il faut pour que ce jeu passe a VALIDE

Les cinq motifs traites. Les motifs 1, 2 et 4 sont du contenu et de la formulation ; le motif 3
demande de revoir la conception de l'exercice, pas seulement ses mots ; le motif 5 demande
qu'un paquet `type: question` puisse porter un indice.

#### Suites hors de ma main

- `squad/programme/MATRICE-couverture.md`, ligne
  `francais | CP | Lecture et comprehension de l'ecrit | Associer les graphemes aux phonemes` :
  **reste `en cours`, ne passe pas a `couvert-valide`.** Motif : voir le motif 3. Je n'ai pas
  edite cette ligne, elle appartient a `camille-po`.
- **Erreur dans le referentiel, a corriger par `camille-po`.**
  `squad/programme/francais/competences-cp.md` affirme, sous "Langage oral et conscience
  phonologique", que "Denombrer les syllabes orales d'un mot" est couvert par un jeu de bandit
  manchot consacre aux syllabes, en renvoyant a une fiche `FICHE-francais-cp-syllabes.md` et au
  sprint s02. **Ce jeu n'existe pas** : il n'est ni dans `catalogue.yaml` (trois jeux, aucun de
  syllabes),
  ni dans `squad/conformite/` (la fiche citee n'existe pas). C'est une annotation heritee de
  `main` restee dans le referentiel. `MATRICE-couverture.md` est juste sur ce point et classe bien
  la ligne en `non couvert` ; c'est le referentiel qui ment. Un referentiel qui declare couverte
  une competence qui ne l'est pas est le pire defaut qu'un referentiel puisse avoir : il fait
  disparaitre un trou. A supprimer.

---

### Avis de conception - le motif 3 ne peut pas etre leve par un remplacement de mots

- **Nature** : **avis de conception, pas un audit et pas un verdict.** Le REFUSE ci-dessus reste
  en vigueur, entier. Rien ici ne remet `bandit-manchot-le-bon-son-cp` au catalogue.
- **Date** : 2026-08-06.
- **Saisine** : `sofia-sm`, sur le blocage de S5-2 constate par `lea-dev` (commit `df963b7`).
- **Moyens** : **audit degrade, et je le signale comme tel.** Je n'ai pas rejoue l'increment : le
  jeu est hors catalogue depuis `DEC-0009` et l'audit joue m'est annonce pour plus tard. J'ai lu
  le paquet corrige (21 items), `QuestionContentSourceFactory`, le referentiel CP, les stories
  S5-2 a S5-5 et S5-9, et le planning de s05. Tout ce qui, ci-dessous, porte sur le rendu a
  l'ecran reste a confirmer en jeu.

#### 1. Le constat de lea-dev est juste, et il est plus fort qu'elle ne le dit

`lea-dev` a raison sur les faits : sur les six sons du paquet, seules les trois voyelles nasales
`an`, `on`, `in` admettent une graphie alternative courante en vocabulaire CP (`en`, `em`, `am`
pour `an` ; `om` pour `on` ; `im`, `ain`, `ein`, `un` pour `in`). `ou`, `oi` et `ch` n'en ont pas :
un mot de CP qui fait le son [u] s'ecrit avec `ou`, et il n'y a pas d'exception a lui montrer.

Mais le probleme n'est pas une penurie de mots. **Il est dans la forme meme de la tache.** Ce jeu
donne un mot ecrit et demande de choisir un grapheme ecrit. Tant qu'aucun son n'est emis, cette
tache ne peut exiger le phoneme que la ou le grapheme ne suffit pas a le donner, c'est-a-dire la
ou le son admet plusieurs graphies. **Pour un son a graphie unique, aucune reformulation de
consigne, aucun choix de mot et aucune inversion du sens de la question ne rendra l'exercice
autre chose qu'une recherche de lettres.** J'ai passe en revue les variantes envisageables -
demander la position du son dans le mot, demander quel mot contient le son, demander quel mot
fait le meme son qu'un autre - toutes restent resolubles par comparaison de chaines. La direction
"garder `ou`, `oi` et `ch` en changeant ce qu'on demande a l'enfant" est une impasse, et je la
ferme plutot que de la laisser ouverte : elle couterait un sprint pour revenir ici.

Ce que cela veut dire pour le produit : **les digraphes transparents `ou`, `oi`, `ch` ne sont pas
travaillables sans son.** Ils appartiennent a un jeu qui joue un fichier audio, pas a celui-ci. Le
referentiel les cite ("digraphes `ch`, `ou`, `on`...") et ils resteront donc partiellement
decouverts par ce jeu ; c'est un trou reel, il vaut mieux qu'il soit visible dans la matrice
plutot que comble par des items qui ne verifient rien.

#### 2. Non, mon motif ne se lit pas en "majorite des items"

`camille-po` m'a traduite en "plus de la moitie, donc au moins 13 sur 24". **Ce n'est pas ce que
j'ai ecrit, et cette traduction est doublement fausse.**

Ma phrase d'origine est : "Un exercice ou 23 items sur 24 se reussissent sans la competence visee,
**et** ou le seul item qui l'exige risque de desapprendre, ne couvre pas 'associer les graphemes
aux phonemes'." C'est une conjonction, et je reprochais une **quasi-absence** (1 item sur 24), pas
un defaut de majorite. Entre "presque jamais" et "plus d'une fois sur deux" il y a tout l'espace
d'un exercice correct, et c'est la qu'il faut viser.

Pire : **la majorite est une cible pedagogiquement mauvaise, pas seulement inatteignable.** Un
enfant de CP est en train de construire la regle "ces lettres font ce son". Un paquet dont la
majorite des items contredit cette regle ne lui enseigne pas la variation, il lui enseigne que la
regle ne tient pas. Le cas regulier n'est pas du remplissage : c'est l'ancrage sans lequel
l'exception n'a rien a quoi s'opposer. Si S5-2 avait ete satisfaite telle qu'ecrite, j'aurais
refuse le resultat.

**Le critere a tester, a la place de la majorite** - trois proprietes, la troisieme etant la
seule reellement bloquante :

1. **Par son, pas globalement.** Chaque son travaille par le paquet admet au moins deux graphies
   usuelles au niveau CP, et **figure dans le paquet sous au moins deux graphies differentes**.
   Un son present sous une seule graphie n'a rien a faire dans cet exercice.
2. **Une part, pas une majorite.** Les items ou la graphie differe de la notation du son
   representent **entre un tiers et la moitie** du paquet, jamais plus. Plancher, parce qu'en
   dessous l'exercice retombe dans le defaut que j'ai refuse ; plafond, pour la raison ci-dessus.
   La meme fourchette vaut a l'interieur de chaque son, pas seulement sur le total.
3. **Par item, et c'est la propriete bloquante.** **Aucun item a variation n'est livre sans un
   retour qui nomme la variation a l'enfant.** C'est la seconde moitie de mon motif 3, celle que
   la traduction en majorite a laissee tomber. Voir le point 5.

Sur la mesure : compter les lettres de la reponse dans le mot est un substitut qui se trompe dans
les deux sens. Il compte `DENT` comme variation, ce qui est juste, mais il compterait `MAIN`
comme regulier alors que la graphie y est `ain` et non `in`. **Le besoin est que chaque item
declare la graphie qui porte le son** (mot `DENT`, son `an`, graphie `en`) : la propriete 2
devient alors exactement verifiable, et c'est le meme champ qui sert au retour du point 5. Je
decris le besoin ; le modele de contenu appartient a `theo-archi` et a `camille-po`.

#### 3. Ce que ce jeu doit travailler - je tranche

**Ce paquet ne travaille plus que les trois voyelles nasales : `an`, `on`, `in`.** `ou`, `oi` et
`ch` en sortent.

Composition demandee : **15 items, cinq par son, dont trois a graphie reguliere et deux a graphie
alternative.** Soit 6 items sur 15 a variation, 40 pour cent, dans la fourchette du point 2.

- `an` : reguliers `MAMAN`, `MANTEAU`, `BANC` ; variations `DENT` (`en`), `VENT` (`en`), `JAMBE`
  ou `LAMPE` (`am`).
- `on` : reguliers `PONT`, `BALLON`, `MONTRE` ; variations `OMBRE`, `POMPIER` ou `NOMBRE` (`om`).
- `in` : reguliers `LAPIN`, `SAPIN`, `JARDIN` ; variations `TIMBRE` (`im`), `MAIN` ou `PAIN`
  (`ain`), `LUNDI` (`un`).

Ces mots sont des exemples de travail, pas une liste arretee : le paquet final repasse devant moi
item par item, comme tout contenu neuf.

Trois consequences favorables, que je note pour qu'elles ne soient pas re-decouvertes :

- **Les trois propositions deviennent toujours les trois memes** (`an`, `on`, `in`). L'enfant
  choisit reellement entre trois sons voisins, ce qui est une difficulte CP authentique, au lieu
  de piocher dans six categories heterogenes. **Ma reserve 3 du refus - `ch` melange a des
  voyelles - tombe d'elle-meme.**
- **Les mots nasaux utiles sont courts** (`DENT`, `VENT`, `PONT`, `MAIN`, `OMBRE`, `TIMBRE`),
  contre `BALANCOIRE` et `CHATEAU`. **Ma reserve 1 - mots trop longs pour le moment de l'annee -
  s'attenue nettement**, sans effort particulier.
- Le risque du double son (motif 4) diminue : deux nasales dans un meme mot court sont rares. Il
  ne disparait pas et reste a verifier item par item.

**Sur la sortie "ce jeu ne peut pas travailler la competence, il faut autre chose"** : je ne la
prends pas. Reconnaitre que `en` fait le son [an] dans `DENT`, c'est associer un grapheme a un
phoneme au sens le plus strict du terme - c'est meme le seul endroit ou cette association est
autre chose qu'une evidence. Le jeu tient, a condition qu'il ne travaille que la ou il y a
quelque chose a travailler.

#### 4. La consigne (motif 1) - la source du son, c'est la voix de l'enfant

Mon motif 1 disait qu'une consigne ne doit pas promettre un son que l'application ne joue pas.
`camille-po` l'a traduit en interdiction du verbe "entendre". **C'est trop etroit et cela va
produire une consigne contournee.**

La consigne juste est : **"Dis le mot. Quel son entends-tu ?"**

Cinq mots, deux phrases courtes, lisibles au CP. Elle est vraie : l'enfant dit le mot a voix
haute, donc il l'entend. Et surtout **elle decrit la bonne operation pedagogique** - passer de
l'ecrit a l'oral par sa propre voix est exactement le geste que la competence demande, et c'est
lui qui rend l'exercice possible sans fichier audio. Une consigne qui evite "entendre" evacuerait
ce geste au lieu de le prescrire.

A rectifier dans le troisieme critere de S5-2, qui interdit aujourd'hui le verbe "entendre" et
interdirait donc cette consigne. La regle est : **la consigne ne promet aucun son emis par
l'application, et nomme ce que l'enfant doit faire pour produire le son.**

Meme rectification sur les indices, qui disent tous "Le son s'entend aussi dans...". Ils
deviennent vrais des lors que la consigne etablit que l'enfant dit les mots.

#### 5. S5-9 n'est pas detachable de S5-2 : c'est sa condition

`camille-po` a detache en S5-9 le retour qui explique la variation, et `DEC-0011` l'a mise hors
perimetre de s05. Sur le plan technique le detachement est fonde, `theo-archi` a raison. **Sur le
plan pedagogique, l'ordre qui en resulte est dangereux.**

Aujourd'hui le paquet contient un seul item a variation, `DENT`, et j'ai ecrit qu'il "fait passer
la variation pour une erreur". La recomposition du point 3 en produit **six**. Livrer six `DENT`
sans le retour de S5-9, c'est multiplier par six le defaut exact que mon motif 3 nomme. **Un
paquet recompose sans S5-9 est un increment que je refuserais plus durement que celui de s04**,
et ce serait justifie : l'increment de s04 n'enseignait rien, celui-la enseignerait faux six fois
par partie.

L'indice ne remplace pas ce retour. Il arrive avant la reponse, seulement apres des echecs
repetes, et il designe un autre mot ; le retour, lui, arrive apres la reponse et nomme les
lettres du mot que l'enfant a sous les yeux. Ce sont deux dispositifs differents.

**Donc : S5-2 recomposee et S5-9 sont un seul lot, et se livrent ensemble ou pas du tout.**

**Prealable de S5-9 que je tranche ici, pour qu'elle ne me revienne pas** (son premier critere le
demande explicitement) :

- **Sur quels items** : uniquement les items a variation, ceux dont la graphie differe de la
  notation du son. Sur un item regulier il n'y a rien a expliquer, et un texte partout dilue
  celui qui compte.
- **Quand** : **systematiquement, que l'enfant ait repondu juste ou faux.** Pas seulement sur
  reponse fausse : l'enfant qui a trouve `DENT` par elimination a autant besoin de l'explication
  que celui qui s'est trompe, et celui qui avait raison pour la bonne raison a besoin d'etre
  conforte. Pas "une seule fois" non plus : une variation graphemique s'installe par repetition.
- **Ce que le texte doit dire** : nommer les lettres du mot affiche et le son qu'elles font.
  Forme attendue, a valider texte par texte : "Dans DENT, ce sont les lettres e et n qui font le
  son an." Une phrase, le mot que l'enfant vient de lire, pas de terme de metalangage.

#### 6. Ce qui est acquis dans le lot livre par lea-dev

Pour ne pas le rejouer a l'audit. **Acquis sur le contenu, sous reserve de confirmation a l'ecran
le jour de l'audit joue** - c'est la limite de l'audit degrade, pas une reticence de ma part.

| Motif | Etat | Ce qui reste |
|---|---|---|
| **Motif 2** - crochets | **Acquis.** Les six sons s'ecrivent `an`, `on`, `in`, `ou`, `oi`, `ch` dans le paquet, sans crochet. | Confirmer le rendu a l'ecran. |
| **Motif 4** - double reponse | **Acquis sur les 21 items actuels.** `MOUCHE`, `MOUTON`, `BALANCOIRE` retires ; j'ai repasse les 21 items restants un par un, aucun ne fait entendre deux des sons du jeu. Le retrait etait l'une des deux sorties que mon motif nommait comme actionnables. | A re-verifier sur les mots neufs de la recomposition, sur ceux-la seulement. |
| **Motif 5** - aucune aide | **Acquis sur les deux plans.** `QuestionContentSourceFactory` lit desormais `indice` : la cause structurelle que je decrivais est traitee, un paquet `type: question` peut porter une aide. Les 21 items en portent une. | Verifier en jeu que l'indice apparait reellement apres echecs repetes, comme dans le jeu de calcul. Reserves de redaction ci-dessous. |
| **Motif 1** - consigne | Non traite, et c'est conforme au planning : la consigne vit dans l'entree de catalogue, absente de `round-two`. Texte tranche au point 4. | Livrable avec la recomposition. |
| **Motif 3** - appariement visuel | **Non traite, a juste titre.** Voir points 1 a 3. | Recomposition complete du paquet. |

**Point hors du refus mais dans mes suites** : `squad/programme/francais/competences-cp.md` ne
contient plus l'annotation heritee qui declarait couverte "Denombrer les syllabes orales d'un mot"
par un jeu inexistant. **Corrige, cette suite est close.**

#### 7. Reserves nouvelles sur les indices, non bloquantes

Je les ecris maintenant pour qu'elles soient traitees avec la recomposition plutot que decouvertes
a l'audit. **Aucune erreur factuelle** : les 21 mots de reference font bien le son annonce, je les
ai verifies un par un. Trois defauts de redaction :

1. **Trois indices induisent en erreur sur la position.** `PINCEAU` : "Le son s'entend au debut du
   mot, comme dans 'lapin'." Dans `lapin` le son est a la fin. Meme defaut sur `SOURIS` (renvoi a
   `loup`, ou le son est au milieu) et sur `ETOILE` (renvoi a `roi`, ou il est a la fin). Un enfant
   de CP lit "au debut, comme dans" comme une seule affirmation. Soit le mot de renvoi partage la
   position, soit on ne mentionne pas la position.
2. **L'indice de `DENT` est le plus faible du paquet alors que c'est l'item qui compte.** Il
   renvoie a `temps`, ou la graphie est `em` - encore une autre - et qui est difficile a lire (`p`
   et `s` muets). Un enfant bloque sur `DENT` a besoin qu'on lui montre les lettres `e-n`, pas
   qu'on l'envoie vers un mot plus dur. Cet indice, et ceux des items a variation en general,
   doivent nommer les lettres.
3. **"Le son s'entend aussi dans le mot 'X'." est lourd pour du CP.** Neuf mots, une tournure
   pronominale passive, et des guillemets. "On entend ce son dans grand." fait le meme travail en
   cinq mots. Je ne bloque pas la-dessus, mais la recomposition est le bon moment.

#### 8. Deux points ouverts que je ferme au passage

- **S5-3, marque visuelle "ceci est un son"** : mon motif 2 la laissait ouverte. **Je tranche :
  aucune marque.** Avec les trois seules nasales du point 3, les propositions sont toujours des
  sons et jamais des mots, la confusion que la marque devait prevenir n'a plus d'objet, et
  ajouter un encadre ou une couleur serait de la charge visuelle sans contrepartie. Ce point ne
  revient pas devant moi.
- **S5-9, declencheur et perimetre d'affichage** : tranches au point 5.

#### 9. Ce jeu ne peut pas etre releve pendant s05

**Non, et je le dis franchement parce que `sofia-sm` en a besoin pour la cloture.**

Ce que je demande n'est pas un remplacement de mots : c'est une recomposition du paquet - quels
sons, en quelle proportion, quels mots, quinze items neufs, quinze indices neufs, six textes
d'explication neufs, une consigne neuve. Chacun de ces textes est du contenu neuf qui passe par
mon audit item par item. Et l'ensemble est conditionne a S5-9, que `DEC-0011` a explicitement
sortie du perimetre de s05.

**Il n'y a rien a fusionner sur ce jeu d'ici la fin de s05.** Le lot de `lea-dev` deja fusionne
n'a pas besoin d'etre defait : les motifs 2, 4 et 5 sont acquis dessus et servent de base a la
recomposition.

**`DEC-0009` reste tenue et le jeu reste hors catalogue.** L'audit joue que `sofia-sm` m'annonce
sur branche jetable porterait, aujourd'hui, sur un paquet dont le motif 3 est intact : je
prononcerais REFUSE une seconde fois, sur le meme motif, ce qui ne renseignerait personne. **Je
demande que cet audit soit reporte a la livraison du lot S5-2 recompose plus S5-9, et je
prononcerai a ce moment-la.** Si l'equipe souhaite tout de meme me faire jouer le paquet a 21
items pour verifier les motifs 2, 4 et 5 en conditions reelles, je le ferai volontiers et j'en
ferai un constat de verification, pas un verdict.

#### 10. Suites hors de ma main

- La ligne `francais | CP | Lecture et comprehension de l'ecrit | Associer les graphemes aux
  phonemes` de `squad/programme/MATRICE-couverture.md` **reste `en cours`**. Je n'ai pas edite ce
  fichier, il appartient a `camille-po`.
- Pour `camille-po`, sans que j'ecrive de story : reecrire le premier critere de S5-2 selon le
  point 2 (par son, fourchette d'un tiers a la moitie, propriete par item) ; rectifier son
  troisieme critere selon le point 4 ; rattacher S5-9 au meme lot que S5-2 selon le point 5 ;
  acter que `ou`, `oi` et `ch` sortent de ce paquet et que leur couverture demande un jeu sonore.
- Pour `theo-archi`, comme besoin et non comme conception : un item doit pouvoir declarer la
  graphie qui porte le son, et un item a variation doit pouvoir porter un texte de retour affiche
  apres la reponse.
