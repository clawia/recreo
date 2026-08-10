# Fiche de conformite - bandit-manchot-le-bon-son-cp

- **Mecanique** : bandit manchot (mecanique generique, partagee avec
  `bandit-manchot-additions-cp` et `bandit-manchot-homophones-cm1`).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-le-bon-son.yaml`,
  **15 items depuis la recomposition de s06** (21 apres le lot `lea-dev` de s05, 24 a l'audit du
  premier refus), `type: question`, chacun porteur d'un indice, et les six items a variation
  porteur d'une explication. Matiere premiere issue de `contenu-repris/`.
- **Statut global** : **VALIDE SOUS RESERVE** (Done pedagogique accorde le 2026-08-10). **Le REFUSE
  du 2026-08-06 est leve** : ses cinq motifs sont traites et verifies en jeu. Le refus n'est pas
  efface - il reste lisible ci-dessous avec sa date, et l'histoire de ce jeu se lit dans l'ordre.
- **Ligne de matrice** : **reste `en cours`.** Le Done pedagogique est accorde, mais la competence
  du referentiel nomme plus que ce que ce paquet travaille - barre ecrite au verdict du 2026-08-10,
  section 6.
  **Arbitrage prealable au developpement du chantier machine a sous du 2026-08-10** (bloc en fin de
  fiche, arbitrage complet dans `FICHE-bandit-manchot-additions-cp.md`) : **trois colonnes de 101 px
  validees sur mesure**, `an`, `on`, `in` confortables sur l'ecran de manche complet a 360 px ; a
  reverifier sur le rendu livre que l'indice et l'explication ne passent pas sous le pli. Ce n'est pas
  un Done pedagogique et le VALIDE SOUS RESERVE du 2026-08-10 n'est pas touche.
- **Derniere mise a jour** : 2026-08-10

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

---

### Note de cloture de s05 - 2026-08-07 : ce jeu n'a pas ete audite, et ce n'est pas un oubli

Je l'ecris pour qu'on ne lise pas mon silence comme un accord tacite.

`cp-le-bon-son.yaml` a change pendant s05 : les crochets de notation d'adulte ont ete retires, un
indice a ete ajoute sur chacun des items, et trois items a deux sons ont disparu. **Je n'ai juge
aucun de ces changements**, conformement a ce que j'ai tranche moi-meme le 2026-08-06 : ce paquet
n'est pas relevable ce sprint, son audit est reporte a la livraison du lot recompose, et `DEC-0009`
tient - le jeu reste hors catalogue. Verifie a l'application lancee : l'accueil ne propose que
"Calcul CP" et "Le bon petit mot", ce jeu n'est atteignable par aucun enfant.

**Le refus du 2026-08-06 reste donc en vigueur, entier, y compris sur les parties du contenu
modifiees depuis.** Le texte neuf que `relecteur-pedagogique` me remonte - vingt et un indices -
est du texte non audite, et il ne devient pas valide parce qu'il est propre de forme. La ligne
`francais | CP | Lecture et comprehension de l'ecrit` de `MATRICE-couverture.md` reste ou elle est ;
je ne l'ai pas editee, elle appartient a `camille-po`.

---

### Verdict du sprint s06 - audit du 2026-08-10 : le REFUSE est leve

- **Verdict** : **VALIDE SOUS RESERVE.** Done pedagogique accorde. Le refus du 2026-08-06 est leve
  sur ses cinq motifs. Ce jeu peut etre mis devant une classe de CP.
- **Verdict sur la ligne de matrice** : **reste `en cours`.** Barre ecrite en section 6.
- **Date** : 2026-08-10.
- **Commit audite** : `12990eb`, branche jetable `s06-lea-son-cp-jouable-jetable`, qui n'ajoute au
  contenu deja fusionne sur `round-two` (`be60b10`) que l'entree de catalogue. J'ai verifie que le
  paquet `cp-le-bon-son.yaml` de cette branche est **identique a celui de `round-two`** : ce que
  j'ai joue est bien le contenu fusionne, la branche ne le modifie pas.
- **`DEC-0009` est tenue, je l'ai constate moi-meme** : sur `round-two`, l'accueil servi ne propose
  que "Calcul CP", "Le bon petit mot" et "Le bon verbe". Ce jeu n'est atteignable par aucun enfant
  aujourd'hui. La confirmation de `sofia-sm` est exacte.
- **Moyens** : audit **joue**, non degrade. Worktree dedie hors de l'arbre du depot. JDK constate
  par `outils/verifier-jdk.sh` : `java du PATH : 1.8.0_202 | JDK de compilation : 21.0.10 |
  JAVA_HOME : vide | toolchains lu par Maven : non (fichier present sous un nom non lu)` ;
  `JAVA_HOME` pointe sur ce JDK 21 le temps de la commande, jamais ecrit dans un fichier commite.
  Fat jar construit depuis la branche jetable et lance sur un port dedie. **Huit parties completes
  jouees** - une juste de bout en bout (score 10 sur 10), sept fausses a chaque manche, dont six
  pour faire sortir du tirage les quinze items. **Les quinze items et les six explications ont ete
  vus a l'ecran.** Rendu reel mesure au navigateur sans tete a 1280 px et a une largeur et une
  hauteur de viewport reellement de 360 x 640 (methode du rapport qualite de `yanis-qa`).

#### 1. Les cinq motifs de mon refus, leves un par un, en jeu

| Motif du 2026-08-06 | Etat | Ce que j'ai vu a l'ecran |
|---|---|---|
| **1** - la consigne annonce un son qui n'existe pas | **leve** | La consigne servie est **"Dis le mot. Quel son entends-tu ?"**, le texte que j'avais dicte au mot pres. Elle est vraie : c'est l'enfant qui produit le son, et elle prescrit le geste au lieu de l'evacuer. |
| **2** - les crochets sont montres a l'enfant | **leve** | Les trois boutons affichent `an`, `on`, `in`. Aucun crochet, verifie sur le rendu au navigateur et pas seulement dans le YAML. |
| **3** - le contenu ne travaille pas la competence annoncee | **leve** | Detail en section 2 : c'etait le motif de fond, et c'est celui qui demandait une recomposition. |
| **4** - trois items posent une question a deux reponses vraies | **leve** | Les quinze mots repasses un par un : **aucun ne fait entendre deux des trois nasales du jeu**. Les mots courts choisis rendent le cas presque impossible, comme je l'avais anticipe. |
| **5** - aucune aide n'existe dans ce jeu | **leve** | Les quinze items portent un indice, et **l'aide apparait a la quatrieme manche apres trois echecs consecutifs**, verifie en jouant, comme dans le jeu de calcul. |

#### 2. Le motif 3, le seul qui demandait une conception : leve, et je le detaille

Mon critere du 2026-08-06 avait trois proprietes. Les trois sont tenues.

**Propriete 1 - par son, chaque son figure sous au moins deux graphies.** Tenu :

| Son | Items reguliers | Items a variation | Graphies presentes |
|---|---|---|---|
| `an` | MAMAN, MANTEAU, BANC | DENT (`en`), VENT (`en`) | `an`, `en` |
| `on` | PONT, BALLON, MONTRE | OMBRE (`om`), NOMBRE (`om`) | `on`, `om` |
| `in` | LAPIN, SAPIN, JARDIN | TIMBRE (`im`), LUNDI (`un`) | `in`, `im`, `un` |

**Propriete 2 - une part, entre un tiers et la moitie, par son et au total.** Tenu exactement :
**6 items a variation sur 15, soit 40 pour cent au total, et 2 sur 5 dans chacun des trois sons.**

**Propriete 3, la seule bloquante - aucun item a variation n'est livre sans un retour qui nomme la
variation a l'enfant.** Tenu, et verifie sur les deux branches que j'avais exigees le 2026-08-06 :

- **le retour existe et il est affiche apres la reponse.** Sur l'ecran de retour, sous le signal
  juste/faux : *"Dans DENT, ce sont les lettres e et n qui font le son an."* C'est la forme que
  j'avais dictee, une phrase, les lettres nommees, le mot que l'enfant vient de lire, aucun terme de
  metalangage. Les six textes sont sur ce modele et je les ai tous vus a l'ecran.
- **il s'affiche que l'enfant ait repondu juste ou faux.** C'etait le point precis que j'avais
  tranche et il aurait ete facile de ne le faire que sur l'erreur. J'ai joue les deux cas : sur
  bonne reponse ("Bravo, c'est juste !") l'explication est la ; sur mauvaise reponse ("Non, c'est
  an.") elle est la aussi.
- **il ne s'affiche que sur les items a variation.** Aucun des neuf items reguliers n'en porte, ce
  qui est ce que j'avais demande : sur un item regulier il n'y a rien a expliquer, et un texte
  partout dilue celui qui compte.

**Et je verifie ce qui fondait vraiment mon motif : plus aucun item a variation n'est resoluble par
appariement visuel.** Repasse lettre par lettre sur les six : aucune des chaines `an`, `on`, `in`
n'apparait dans DENT, VENT, OMBRE, NOMBRE, TIMBRE ni LUNDI. L'enfant ne peut pas retrouver les
lettres de la reponse dans le mot ; il faut passer par le son. Symetriquement, aucun des quinze mots
ne contient les lettres d'un **mauvais** son du jeu, donc il n'y a pas de piege visuel a l'envers.
C'est le contraire de l'increment de s04, ou 23 items sur 24 se reussissaient sans phoneme.

**Consequence favorable que je note** : les propositions sont desormais toujours les trois memes,
`an`, `on`, `in`, verifie sur huit parties. L'enfant choisit entre trois sons voisins, ce qui est une
difficulte de CP authentique. **Ma reserve 3 du refus - `ch`, consonne, melange a des voyelles - tombe
d'elle-meme**, comme je l'avais annonce.

#### 3. MAIN et PAIN ecartes : je valide l'ecart a mes propres exemples

`lea-dev` a ecarte **MAIN** et **PAIN**, que je citais moi-meme en exemple pour le son `in`, et mis
**TIMBRE** (`im`) et **LUNDI** (`un`) a leur place. Son motif : ces deux mots contiennent
litteralement la sous-chaine "in" et restent donc resolubles par appariement visuel.

**Je valide, et son motif est le bon. Elle a corrige une contradiction interne a mon propre avis, et
elle l'a tranchee du bon cote.**

Le detail, parce qu'il compte pour la suite. Dans le meme avis du 2026-08-06, j'ecrivais deja, au
point 2, que compter les lettres de la reponse dans le mot "compterait MAIN comme regulier alors que
la graphie y est `ain` et non `in`". Je l'avais donc vu comme un defaut de **mesure**, et je n'en ai
pas tire la consequence sur ma **liste d'exemples**, ou j'ai laisse MAIN et PAIN. Les deux passages
de mon avis se contredisaient. Ce qui lie l'equipe est la propriete - mon motif 3 - et non ma liste,
que j'avais moi-meme qualifiee d'"exemples de travail, pas une liste arretee". `lea-dev` a garde la
propriete et jete l'exemple : c'est l'ordre juste.

**J'ajoute un second motif, qui est de moi et qui va dans le meme sens** : la forme d'explication que
j'ai imposee ne fonctionne pas sur `ain`. Elle dirait *"ce sont les lettres a, i et n qui font le son
in"* - trois lettres, dont un `a` place juste avant le "in" que l'enfant voit. On lui montrerait un
appariement a deux lettres tout en lui affirmant que le son en demande trois. `im` et `un` prennent
la forme a deux lettres proprement.

**Ce que cela coute et que j'assume** : le son `in` n'est plus illustre par ses graphies les plus
frequentes en vocabulaire de CP (`ain` de "main", "pain") mais par `im` et `un`, plus rares. C'est le
prix de la propriete, et je le prefere a deux items qui ne verifient rien. `ain` reste travaillable
le jour ou un jeu jouera un son.

**Une limite que je nomme sur LUNDI, non bloquante, parce que c'est moi qui l'ai mis dans la liste** :
la graphie `un` note un timbre qui, pour une partie des locuteurs, n'est pas celui de `in` - la fusion
est majoritaire en France aujourd'hui et les manuels de CP rangent bien "un, lundi, brun" avec le son
`in`, mais dans certaines classes un enfant entendra une autre voyelle et l'explication lui paraitra
fausse. Je ne le retire pas et je ne le compte pas contre l'increment : je le signale pour que
`camille-po` sache qu'un remplacement par `im` ou `am` est disponible si le cas se presente.

#### 4. Ce qui est bon et que je dis

- **Les quinze indices sont justes, et les trois defauts de redaction que j'avais nommes le
  2026-08-06 sont corriges.** **Aucun indice ne parle plus de position** : le defaut de PINCEAU,
  SOURIS et ETOILE ("le son s'entend au debut, comme dans lapin", ou il est a la fin) a disparu avec
  les mots. **L'indice des items a variation nomme les lettres** - *"Regarde : les lettres e et n
  font ce son."* - au lieu d'envoyer l'enfant vers un mot plus dur, ce qui etait mon reproche le plus
  precis sur l'ancien indice de DENT et son renvoi a "temps". Et la tournure lourde *"Le son s'entend
  aussi dans le mot 'X'."* est devenue **"On entend aussi ce son dans le mot grand."** - sept mots,
  pas de guillemets, tournure active. Les neuf mots de renvoi (grand, maman, manteau, bonbon, pont,
  ballon, jardin, lapin, sapin) font tous le son annonce, verifies un par un, et sont tous de graphie
  reguliere, donc ils ancrent au lieu de brouiller.
- **L'indice ne donne pas la reponse.** Sur les items a variation, "les lettres e et n font ce son"
  montre ou regarder sans dire quel son c'est : l'enfant garde le travail a faire. C'est mieux que ce
  que fait le jeu des homophones, ou l'indice conclut a la place de l'enfant, et c'est a porter au
  credit de ce paquet.
- **Les mots sont nettement plus courts.** MAMAN, BANC, DENT, VENT, PONT, OMBRE remplacent
  BALANÇOIRE (10 lettres, une cedille), PINCEAU et CHÂTEAU. **Ma reserve 1 s'attenue fortement**,
  comme je l'avais anticipe, sans avoir ete traitee en propre.
- **Accents et orthographe irreprochables** sur les quinze mots, les quinze indices et les six
  explications, verifies sur la reponse HTTP et sur le rendu, pas seulement dans le fichier.
- **Biais et ton** : rien a signaler. Des noms d'objets, d'animaux et un jour de la semaine ; aucun
  prenom, aucune situation familiale, sociale ou culturelle presupposee.
- **Rendu reel a 360 x 640** - le format le plus probable pour un enfant de CP sur un telephone :
  consigne, mot et les trois propositions tiennent **entierement au-dessus du pli**, sans
  defilement. Verifie a une largeur et une hauteur de viewport reellement de 360 x 640.

#### 5. Reserves - a convertir en stories par camille-po, non bloquantes

Les trois que j'avais nommees comme non bloquantes en 2026-08-06 restent ouvertes et hors perimetre
de ce sprint. Je ne les transforme pas en motifs : elles etaient non bloquantes quand je les ai
ecrites, elles le restent.

1. **Mots trop longs pour le moment de l'annee** : fortement attenuee (section 4), non fermee.
   TIMBRE, MONTRE, NOMBRE et MANTEAU restent au-dessus de six lettres.
2. **Aucune progression de difficulte dans le tirage** - et je peux maintenant en nommer la
   consequence exacte, observee en jeu : **a ma premiere partie, la premiere manche a tire VENT**,
   c'est-a-dire que l'enfant rencontre l'exception `en` avant d'avoir jamais vu la regle `an`. Le
   tirage peut aussi presenter les deux variations d'un son avant aucun de ses reguliers. Mon avis du
   2026-08-06 disait que le cas regulier est "l'ancrage sans lequel l'exception n'a rien a quoi
   s'opposer" ; le tirage aleatoire peut donc inverser l'ordre d'apprentissage. **Ce n'est pas
   bloquant, et pour une raison que je verifie plutot que je suppose** : l'explication apparait
   immediatement et nomme les lettres, donc l'enfant n'est pas laisse a conclure que la regle ne
   tient pas. Mais c'est desormais la premiere story a instruire sur ce jeu.
3. **Le son `[ch]`** : disparait de fait avec la recomposition, comme je l'avais ecrit. Reserve
   close, sans travail.
4. **L'accueil ne dit pas a quel niveau s'adresse le jeu.** Toujours ouverte, et elle empire : quand
   ce jeu rejoindra le catalogue, l'accueil proposera deux jeux de CM1 et deux jeux de CP, dont un
   seul porte une mention de niveau. Question produit pour `camille-po`.

Reserves nouvelles, datees du 2026-08-10, non bloquantes :

5. **Quatre des six items a variation sont deux paires de mots rimant, sur une seule graphie et une
   seule position.** DENT et VENT sont tous deux en `-ENT` final ; OMBRE et NOMBRE tous deux en
   `-OMBRE`. L'enfant risque d'apprendre deux formes de mots ("ENT fait an", "OMBRE fait on") plutot
   que les graphemes `en` et `om` la ou ils se presentent. Ma propriete 1 n'exigeait qu'au moins deux
   graphies par son, elle est tenue, et je n'en fais donc pas un motif - ce serait poser une barre
   apres coup. La correction est deja dans ma liste d'exemples du 2026-08-06 et ne demande aucun
   arbitrage neuf : remplacer VENT par **LAMPE** ou **JAMBE** (`am`, autre graphie, autre position) et
   NOMBRE par **POMPIER** (`om` en position non finale). Le son `in`, avec `im` et `un`, est deja le
   mieux servi des trois.
6. **L'explication n'est pas distinguee visuellement de la consigne.** A l'ecran, *"Dans DENT, ce
   sont les lettres e et n qui font le son an."* est du texte courant place sous le bandeau rouge
   "Non, c'est an." Un enfant de CP lit peu et lentement, et ce qui l'arrete est le bloc colore ;
   c'est justement la phrase la plus importante de l'ecran qui est la moins signalee. Question de
   mise en forme pour `ines-design`, pas de contenu : je decris ce que l'enfant ne verra pas, je ne
   propose pas de dessin.
7. **L'explication reprend presque mot pour mot l'indice du meme item** ("les lettres e et n font ce
   son" / "ce sont les lettres e et n qui font le son an"). Ce n'est pas un defaut - une variation
   graphemique s'installe par repetition, je l'ai ecrit moi-meme - mais l'enfant qui vient de lire
   l'indice puis l'explication lit deux fois la meme phrase, et la seconde n'ajoute rien. Le jour ou
   ce jeu sera retouche, l'explication pourrait pointer un second mot de la meme graphie ("comme dans
   VENT") plutot que se repeter.

#### 6. Ou est la barre pour `couvert-valide`, et pourquoi elle n'est pas atteinte aujourd'hui

Le Done pedagogique et la matrice ne disent pas la meme chose : le Done porte sur l'increment, la
matrice porte sur la couverture d'une competence. La ligne du referentiel est **"Associer les
graphemes aux phonemes (voyelles, consonnes simples, puis digraphes ch, ou, on...)"**. Ce paquet
travaille **trois voyelles nasales et leurs variantes**, et rien d'autre : ni les voyelles simples,
ni les consonnes simples, ni les digraphes `ch`, `ou`, `oi`. C'est une part de la competence, pas la
competence.

**Ma condition pour que je fasse passer cette ligne a `couvert-valide`** - et elle est ecrite pour ne
pas etre rejouee a chaque sprint :

1. **les voyelles simples et les consonnes simples sont couvertes**, par ce jeu ou par un autre. Une
   correspondance grapheme-phoneme simple ne se travaille pas par un choix entre trois notations de
   son : c'est probablement un autre jeu, et ce n'est pas a moi de le concevoir ;
2. **les digraphes `ou`, `oi` et `ch` sont couverts par un jeu qui emet un son.** J'ai tranche le
   2026-08-06 qu'ils ne sont pas travaillables sans audio, et je ne rouvre pas ce point : sans son,
   un digraphe transparent ne peut donner lieu qu'a une recherche de lettres. Tant qu'aucun jeu
   sonore n'existe, cette part de la ligne reste un trou reel, et il vaut mieux qu'il soit visible
   dans la matrice que comble par des items qui ne verifient rien ;
3. **les trois nasales gardent les proprietes acquises aujourd'hui** : au moins deux graphies par
   son, entre un tiers et la moitie d'items a variation, une explication sur chaque item a variation,
   aucun item resoluble par appariement visuel ;
4. et **j'aurai joue l'increment** avant de le dire.

Rien d'autre. Ce qui n'est pas dans cette liste - dont les sept reserves de la section 5 - ne bloque
pas `couvert-valide` et ne pourra pas m'etre oppose comme l'ayant bloque. Je dis franchement que
cette barre est loin : elle demande au moins un jeu qui n'existe pas. C'est la mesure honnete de
l'ecart, et elle est preferable a un `couvert-valide` qui ferait disparaitre le trou.

#### 7. Ce dont je reste seule garde sur ce jeu

`ChaquePaquetExpliqueSesItemsAVariationTest` epingle desormais qu'un item dont la graphie differe de
la reponse porte une explication, `ChaquePaquetPorteUnIndiceTest` qu'aucun item n'est livre sans
indice, et `PaquetsFrancaisS04Test` que le paquet ne presente que les trois nasales, sans crochet, et
qu'il varie sur vingt manches. C'est beaucoup, et c'est neuf depuis mon refus. **Ce qu'aucun test ne
dit, et que je porte seule :**

1. **Que le mot fasse reellement le son annonce, et un seul des trois.** Mon motif 4. Aucun test ne
   sait prononcer.
2. **Que la graphie declaree soit la bonne** - qu'on ecrive bien `en` pour DENT et non `an`. Le champ
   `graphie` est la donnee sur laquelle les garde-fous s'appuient ; s'il mentait, ils seraient verts
   sur un paquet faux. C'est moi qui le lis.
3. **Que le texte de l'explication soit vrai et lisible par un enfant de six ans.** Un test peut
   verifier qu'il existe, pas qu'il dit vrai ni qu'il est comprehensible.
4. **Que le mot de renvoi de l'indice fasse le meme son que l'item.** Verifie a la main sur les
   neuf, et rien ne le surveille.

#### 8. Suites hors de ma main

- **L'entree de catalogue de la branche jetable `s06-lea-son-cp-jouable-jetable` peut rejoindre
  `round-two`** : mon verdict est VALIDE SOUS RESERVE, donc la condition posee par `sofia-sm` est
  remplie. Je ne fusionne pas et je n'ai pas touche a `catalogue.yaml` : ce fichier n'est pas le
  mien.
- `squad/programme/MATRICE-couverture.md`, ligne `francais | CP | Lecture et comprehension de
  l'ecrit | Associer les graphemes aux phonemes` : **reste `en cours`**, avec la barre de la
  section 6. Je n'ai pas edite cette ligne, elle appartient a `camille-po`.
- Pour `camille-po`, sans que j'ecrive de story : la reserve 2 (progression du tirage) est la
  premiere a instruire sur ce jeu ; la reserve 5 (deux paires de mots rimant) se corrige avec des
  mots deja listes dans mon avis du 2026-08-06 ; la reserve 4 (niveau annonce sur l'accueil) est
  commune aux quatre jeux et n'est toujours pas traitee.
- Pour `ines-design`, comme symptome et non comme conception : l'explication qui suit la reponse est
  aujourd'hui moins signalee que le bandeau juste/faux qui la precede (reserve 6).

---

### Arbitrage prealable au developpement - chantier machine a sous (S7-1 a S7-4), du 2026-08-10

**Ce bloc n'est pas un Done pedagogique et il ne touche ni au VALIDE SOUS RESERVE du 2026-08-10 ni au
refus du 2026-08-06, qui restent lisibles ci-dessus.** L'arbitrage complet est ecrit une seule fois,
dans `FICHE-bandit-manchot-additions-cp.md`, arbitrage du 2026-08-10 : la mecanique est commune aux
quatre jeux. Ce qui concerne **ce jeu-ci** en particulier :

1. **Trois colonnes de 101 px, et c'est valide.** Mesure sur le rendu a 360 px, sur l'ecran de manche
   complet de l'item `MANTEAU` avec son score, sa consigne, son enonce et son indice : `an`, `on`, `in`
   se lisent de facon confortable, centres, en gras, a 2 rem, avec beaucoup d'air autour. C'est avec
   "Calcul CP" le cas le plus favorable du catalogue, et c'est un jeu de CP, donc le plus exigeant en
   lisibilite. Aucune reserve sur ce point.
2. **La condition qui vise ce jeu : l'explication et l'indice ne doivent pas passer sous le pli.** Ce
   paquet est le seul a porter une `explication` en plus de l'indice, et l'ecran de manche gagne
   pourtant 180 px en passant en colonnes - le pli devrait devenir plus confortable, pas moins. Je le
   verifierai sur le rendu livre, sur un item a variation (`DENT`, `OMBRE`, `TIMBRE`, `LUNDI`) et apres
   trois echecs consecutifs pour faire venir l'indice.
3. **Aucune colonne ne fait defiler un son autre que le sien.** Meme motif que sur les homophones : un
   enfant de CP qui apprend a associer un grapheme a un phoneme n'a rien a gagner a voir `on` passer
   dans la colonne de `an`.
