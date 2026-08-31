# Note - `D5` : la forme retenue au lot 4, et le seul point qui reste a `ines-design`

- **Auteur** : marc-dev. **Date** : 2026-08-31. **Sprint** : s24, lot 4.
- **Destinataire** : `sofia-sm`, qui route vers `ines-design`. **Ne tranche rien** : la forme
  appartient a `ines-design`, `nadia-prof` fixe le resultat, et le lot ne fait qu'implementer.
- **Etat** : le lot 4 est livre et vert avec la forme decrite ci-dessous. Si `ines-design` en
  prefere une autre, l'ecart est chiffre en section 3.

---

## 1. Ce que le lot a implemente, et pourquoi cette forme-la

`nadia-prof` retient la **variante A** avec une condition (`ARBITRAGE-D5-le-champ-de-saisie-apres-une-tentative-fausse.md`,
section 1) :

> Au moment ou l'enfant peut appuyer pour renvoyer, la chaine presente dans le champ doit lui etre
> montree comme deja refusee.

Elle nomme deux formes qui la servent et n'en designe aucune : **(a)** la meme chaine barree dans la
liste des tentatives au-dessus du champ, **(b)** une marque de refus sur le champ lui-meme, qui
s'efface a la premiere frappe.

**Le lot a implemente la forme (a)**, pour un motif qui n'est pas un choix de gout :

- **Elle tombe de la condition que `nadia-prof` rend directement par ailleurs.** Sa section 3.3
  ordonne que la liste porte **toutes** les tentatives de la manche, la derniere comprise, contre la
  description de la variante A qui la laissait vide au deuxieme essai. Une fois cette liste ecrite
  comme elle le demande, la chaine du champ **est** barree au-dessus du champ : la forme (a) est le
  resultat, pas une decision de plus. Elle valide elle-meme la combinaison en section 3.4 - *"l'une
  dit refusee, l'autre dit modifiable"*.
- **La forme (b) n'est pas implementable dans ce depot**, et c'est un fait technique que je verse
  plutot qu'un avis. *"Une marque qui s'efface a la premiere frappe"* est un comportement declenche
  par une frappe, donc du JavaScript maison qui decide de ce que l'ecran montre. C'est la dette 4 de
  la version precedente, que `CLAUDE.md` interdit de reproduire, et `FrontieresDuJavaScriptTest` la
  garde. Une variante serveur existerait - le serveur pose la marque et le premier envoi la retire -
  mais elle ne s'efface alors qu'apres un aller-retour, donc **apres** l'appui, c'est-a-dire trop
  tard pour ce que la condition demande.

## 2. Ce qui reste a `ines-design`, et qui n'est pas tranche par le lot

La forme (a) est **posee**, elle n'est pas **habillee**. Trois points sont de son ressort et le lot
n'en a decide aucun au-dela du minimum necessaire pour que l'ecran existe :

1. **La place de la liste.** Le lot l'a mise **au-dessus du champ** et non sous le verdict comme sur
   l'ecran a boutons, parce que la condition de `nadia-prof` porte sur *"le moment ou l'enfant peut
   appuyer"* : la marque doit etre dans son regard quand il regarde le champ. La proposition
   d'`ines-design` la placait sous le verdict, donc sous le champ. **Ce deplacement est de moi et il
   n'est pas mesure** : il change le budget vertical de l'ecran, que sa section 5.3 a chiffre sur dix
   largeurs. Le releve est a refaire sur cette disposition.
2. **Le trait lui-meme.** Le lot reprend a l'identique les valeurs de sa section 5.1
   (`text-decoration: line-through`, `0.1875rem`, `--recreo-texte`, `skip-ink: none`). Rien n'y est
   invente.
3. **Le fait que la derniere tentative figure aux deux endroits.** `nadia-prof` l'a explicitement
   valide (section 3.4), contre le critere *"rien n'est affiche deux fois"* d'`ines-design`. Si
   celle-ci trouve une forme qui porte les deux sens sans ecrire la chaine deux fois **et sans
   comportement client**, `nadia-prof` ecrit qu'elle sert sa condition aussi bien.

## 3. Ce que coute un changement d'avis, chiffre

- **Deplacer la liste sous le verdict** : deux lignes de gabarit deplacees, aucune regle de feuille
  touchee, aucun test de comportement touche. Un test d'`EcranDeSaisieTest` verifie l'ordre relatif
  et suivrait.
- **Retirer la derniere tentative de la liste** : une ligne de controleur - mais **cela rouvre
  l'ecart que `nadia-prof` a releve**, et il faudrait alors une autre forme pour sa condition. Ce
  n'est pas un changement de forme, c'est un changement d'arbitrage.
- **Le releve des dix largeurs sur la disposition reelle** : il n'est pas dans mon perimetre et je ne
  l'ai pas fait. Il vaut pour ce lot ce qu'`ines-design` ecrit deja du sien : *"le releve refait sur
  le jar, une fois le lot 4 livre, reste du - et il reste a `nadia-prof`."*

## 4. Ce que j'ai verifie moi-meme sur le jar, et ce que je n'ai pas pu verifier

**Verifie** : les cinq etats de l'ecran de saisie rendus par le jar servi - manche fraiche, premier
refus, deuxieme refus, revelation, manche suivante apres le geste de suite. La liste porte bien une
tentative au deuxieme essai, deux au troisieme, trois a la revelation ; le champ porte bien la
derniere ; a la revelation le champ disparait et les deux chaines sont montrees ensemble.

**Non verifie, et je le dis plutot que de le laisser supposer** : le rendu **visuel**. Aucun paquet
de saisie n'est servi - c'est le lot 9 de `sofia-sm` qui en depose un - donc l'ecran n'est atteignable
depuis le catalogue qu'avec une sonde posee a la main. J'en ai pose une, non commitee, pour lire le
document servi ; je n'ai mesure aucun pixel. **Ce que ces documents prouvent est ce que le serveur
rend, jamais ce que l'enfant voit.**
