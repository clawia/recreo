# Fiche de conformite - bandit-manchot-le-bon-son-cp

- **Mecanique** : bandit manchot (mecanique generique, partagee avec
  `bandit-manchot-additions-cp` et `bandit-manchot-homophones-cm1`).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-le-bon-son.yaml`,
  24 items, `type: question`. Matiere premiere issue de `contenu-repris/`.
- **Statut global** : **REFUSE** (Done pedagogique refuse le 2026-08-06, premier audit).
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
