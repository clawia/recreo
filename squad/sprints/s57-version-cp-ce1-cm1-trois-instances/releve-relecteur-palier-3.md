# s57 - releve orthotypographique du palier 3 (`relecteur-pedagogique`)

- **Auteur** : `relecteur-pedagogique`.
- **Date** : 2026-10-20.
- **Arbre** : checkout principal de `round-two`, fichiers non modifies par ce releve. Ce fichier n'est pas commite.
- **Demande** : `nadia-prof`, `lecture-nadia-palier-3.md` section 5 ("Hors de mon controle") et section 8 (routage).
- **Ce que je n'ai pas fait** : aucune correction appliquee (consigne : un texte destine a l'enfant n'est pas modifie par moi),
  aucun jeu lance, aucun rendu a l'ecran, aucun correcteur orthographique automatique. J'ai lu les cinq YAML en entier, ligne
  par ligne, puis extrait par script tous les mots des champs lus par l'enfant et relu cette liste a la recherche d'une forme
  sans accent la ou il en faut un. Ce releve ne juge ni vocabulaire, ni ton, ni fond, ni niveau scolaire.
- **Aucun identifiant de condition ou de reserve n'est cree ici.** Les points sont reperes par paquet, numero d'item (ordre du
  fichier, a partir de 1) et numero de ligne du fichier.

## Resume

| Paquet | Fautes certaines (texte lu par l'enfant) | Reperage typographique dans un champ de descripteur | A trancher (langue de l'enfant) | Commentaires de depot a corriger |
|---|---|---|---|---|
| `ce1-inference` | 0 | 0 | 1 | 1 |
| `ce1-texte-documentaire` | 0 | 0 | 1 | 0 |
| `ce1-negation` | 0 | 0 | 0 | 2 |
| `ce1-nombres-jusqu-a-1000` | 0 | 0 | 1 | 1 |
| `ce1-emc-biens-communs` | 1 | 1 | 3 | 1 |

Total : 1 faute certaine dans un texte lu par l'enfant (ponctuation, `ce1-emc-biens-communs` ligne 132) et 1 ecart de
typographie certain dans un champ de descripteur que, d'apres `lecture-nadia-palier-3.md` section 4, l'enfant ne lit pas
(`ce1-emc-biens-communs` ligne 8). Aucune faute d'orthographe ni d'accent trouvee dans les cinq paquets.

## Controles faits sur les cinq fichiers, avec resultat

| Controle | Methode | Resultat |
|---|---|---|
| Tiret cadratin ou demi-cadratin, guillemet ou apostrophe typographique, points de suspension Unicode | `grep -P` sur U+2010 a U+2015, U+2018 a U+201F, U+2026 | 0 occurrence dans les cinq fichiers |
| Espace avant `?`, `:`, `;`, `!` dans un champ lu par l'enfant | `grep -P` : espace simple devant ces signes, lignes de commentaire exclues | 0, sauf `ce1-emc-biens-communs` ligne 8 (voir plus bas) ; toutes les autres espaces sont U+00A0 |
| Espace a l'interieur des chevrons `«` et `»` | `grep -P` sur `«` suivi d'une espace simple et sur une espace simple suivie de `»` | 0 : les 14 chevrons de `ce1-texte-documentaire` et les 24 de `ce1-negation` portent une espace insecable |
| Signe de ponctuation haute sans espace avant | `grep -P` | 0 |
| Caractere non ASCII dans une ligne de commentaire | `grep -P '^\s*#.*[^\x00-\x7F]'` | 0 |
| Apostrophe courbe | `grep -c` | 0 dans les cinq fichiers |
| Mot sans accent la ou il en faut un (`a` pour `a`/`à`, `ou` pour `ou`/`où`, `sur`/`sûr`, `du`/`dû`, `la`/`là`) | liste de tous les mots des champs lus par l'enfant, relue en entier ; chaque `a`, `ou`, `sur`, `du` relu dans sa phrase | 0 faute |
| `où` accentue en tete d'enonce et en milieu de phrase | relecture | correct partout (`Où`, `où`) |
| Ligatures `œ` (`œufs`, `cœur`) | relecture | correctes |
| Accord des participes et pluriels des textes support et des propositions | relecture | 0 faute |
| Orthographe des nombres en lettres du paquet `ce1-nombres-jusqu-a-1000` (traits d'union sous cent, `et` de 71, `cents` seulement en fin de nombre, `quatre-vingt-dix`) | relecture des 7 designations litterales et des 14 distracteurs lisibles en lettres | 0 faute, conforme a la convention que l'en-tete annonce |
| Entrees de `catalogue.yaml` des cinq jeux (titre et consigne) | lues, hors perimetre demande | 0 faute |

## 1. `ce1-inference` (20 items)

**Fautes certaines** : aucune.

**A trancher par `nadia-prof` (langue de l'enfant)**

| Ligne | Item | Texte actuel | Pourquoi je le signale |
|---|---|---|---|
| 108 | 14 (Ines) | texte : "Ce matin, Inès part à l'école avec son frère. Il se met à pleuvoir. Inès ouvre son parapluie au-dessus de lui. Ils arrivent tout secs." ; enonce : "Pourquoi Inès ouvre-t-elle son parapluie au-dessus de lui ?" | Orthographe correcte (`tout secs` est juste). Le `Il` de la deuxieme phrase suit immediatement `son frère` et peut se lire comme le frere (relation de lecture, pas une faute) ; l'enonce emploie `lui` sans que la question le nomme. Je ne propose pas de reecriture : c'est la clarte du texte pour un enfant, donc a elle |

**Commentaire de depot a corriger** (`ce1-inference.yaml`, lignes 17-18)

- Texte actuel : "Les sentiments sont pris parmi content, triste, en colere, avoir peur."
- Constat : verifie contre le code, les sept items de la sorte sentiment (items 1 a 7) n'emploient que ces quatre sentiments, mais
  l'item 20 (cause, ligne 145) a pour bonne reponse "parce qu'elle est fière", un cinquieme sentiment. La phrase, placee dans le
  commentaire d'`INF-CE1-1` qui decrit les trois sortes, laisse croire que le paquet n'en porte que quatre. `nadia-prof` le
  constate aussi (`lecture-nadia-palier-3.md` section 1).
- Correction proposee, non appliquee : "Les sept items de la sorte sentiment sont pris parmi content, triste, en colere, avoir
  peur ; l'item 20, de la sorte cause, a pour reponse fiere."

**Verifie sans ecart** : en-tete (7 sentiments, 6 lieux, 7 causes ; onze prenoms de chaque genre en comptant Louna, personnage
secondaire de l'item 4, soit 11 masculins et 10 feminins plus Louna ; propositions de 6 mots au plus ; indices de 11 mots au
plus ; 20 textes). Renvois a `ARBITRAGE-PREALABLE-s48-francais-ce1.md` (sections 2 et 3.6) et a
`ARBITRAGE-s47-longueur-du-texte-support-au-ce1.md` : les deux fichiers existent, les sections existent.

## 2. `ce1-texte-documentaire` (20 items)

**Fautes certaines** : aucune. Les 14 mots cites des indices sont entre chevrons avec espace insecable, en minuscules, a la forme
ou ils apparaissent dans le texte (`« arrête-toi »` pour `Arrête-toi`, `« arrose »` pour `Arrose` : minuscule voulue, mot cite hors
debut de phrase).

**A trancher par `nadia-prof`**

| Lignes | Items | Texte actuel | Pourquoi je le signale |
|---|---|---|---|
| 48 et 72 | 4 et 8 | item 4 : "La Lune tourne autour de la Terre. La Terre tourne autour du Soleil. Le Soleil est une étoile." ; item 8 : "Le soleil se lève à l'est. À midi, il est très haut dans le ciel. Il se couche à l'ouest." | `Soleil` prend une majuscule en astronomie (astre), `soleil` une minuscule dans l'usage courant : les deux graphies sont defendables et chaque item en suit une. Il n'y a pas de faute. Je le note parce qu'un enfant lit les deux dans la meme partie |

**Verifie sans ecart** : les majuscules initiales des bonnes reponses et distracteurs de l'item 17 (`Mélange bien`, `Casse deux
œufs`, `Ajoute du sucre`) sont homogenes (les trois reprennent une instruction du texte), sans point final, comme les autres
propositions du paquet. Renvois de l'en-tete a `ARBITRAGE-PREALABLE-s48-francais-ce1.md` (sections 2 et 3.7) : existent.

**Commentaires de depot** : aucun a corriger.

## 3. `ce1-negation` (24 items)

**Fautes certaines** : aucune.

**Ecarts voulus, a ne pas corriger** : les distracteurs contiennent des fautes qui sont leur raison d'etre (`Léo aime pas les
épinards.`, `Léo ne aime pas les épinards.`, `Inès n'écoute la radio pas.`, `Yanis ne range ses pas crayons.`, `Les enfants ne
pas chantent.`, `Le chat n'aime la pluie.`, etc.). Je les ai lus un par un contre la famille que le commentaire de chaque item
declare (a a d) : les 24 correspondent. Seule la ponctuation et l'orthographe de ce qui n'est pas la faute voulue est de mon
ressort, et elle est correcte (`maîtresse`, `boîte`, `fenêtre`, `Noé`, `Léa`, `Zoé`).

**A trancher par `nadia-prof`** : rien.

**Commentaires de depot a corriger ou a signaler**

| Lignes | Texte actuel | Constat | Correction proposee |
|---|---|---|---|
| 1 | "... l'etat de validation est dans la fiche de conformite, squad/conformite/)." | Verifie : `ls squad/conformite` ne contient aucune fiche pour `ce1-negation` (ni `bandit-manchot-negation-ce1`). Les stories de `camille-po` disent que la fiche est ouverte apres le commit de branchement. Le commentaire decrit un fichier qui n'existe pas encore. Pas une faute de langue : un renvoi qui ne resout pas | A router a `camille-po` : soit reformuler en "sera dans la fiche ...", soit retirer la phrase ; je ne tranche pas |
| 1, 2, 12, 13 | Bloc d'en-tete place avant `schema: 1`, avec des lignes de plus de 150 caracteres, la reference `NEG-CE1-1 a` coupee en fin de ligne 1, | Mise en forme seulement : `ce1-nombres-jusqu-a-1000` place aussi ses commentaires avant `schema: 1`, les trois autres apres `lignesDeReferentiel`, a 100 caracteres environ par ligne. Aucun effet sur le contenu | Au choix de l'auteur du paquet ; non bloquant |

**Verifie sans ecart** : 14 items vers la negative, 10 vers l'affirmative, 14 elisions (les 14 commentaires `# NEG-CE1-2: elision`
portent chacun un `n'`), 24 commentaires `# NEG-CE1-3`, propositions de 6 mots au plus, indices de 11 mots au plus, 3 indices
distincts. Aucun `texteSupport` hors commentaire.

## 4. `ce1-nombres-jusqu-a-1000` (22 items)

**Fautes certaines** : aucune. Les espaces avant `:` et `?` sont insecables partout, y compris dans `358 = 300 + 50 + ?` et
`724 = 700 + ? + 4`.

**A trancher par `nadia-prof`**

| Ligne | Item | Texte actuel | Pourquoi je le signale |
|---|---|---|---|
| 105 | 18 | "Dans 745, combien de centaines ?" | Phrase elliptique (sans verbe), grammaticalement acceptable dans un enonce de mathematiques. "Dans 745, combien y a-t-il de centaines ?" est la forme complete. La difference tient a la lecture d'un enfant de CE1 : a elle |

**Commentaire de depot a signaler** (`ce1-nombres-jusqu-a-1000.yaml`, ligne 30)

- Le champ `competence` du descripteur n'a pas de point final ("... unités, dizaines, centaines)"), alors que les quatre autres
  paquets en portent un. Sans effet de lecture (`lecture-nadia-palier-3.md` section 4 : le champ n'est lu par aucun code ni
  gabarit, a verifier a l'ecran) ; harmonisation facultative.

**Verifie sans ecart** : l'en-tete annonce 7 items `lire`, 7 `ecrire`, 8 de decomposition dont 5 avec centaines, dizaines, unites
et 3 en ecriture additive : les comptes sont justes. La liste "zero intercalaire ou final sur 305, 407, 620, 800, 604, 806, 500,
605, 430, 20" correspond a des bonnes reponses ou des nombres de l'enonce du fichier (le `20` est la bonne reponse de l'item 21).
Le mot "decomposer" n'est dans aucun champ lu par l'enfant (il est dans `competence`, ligne 30, sous la forme `décomposer`, voir
la reserve de `nadia-prof`).

## 5. `ce1-emc-biens-communs` (24 items)

**Fautes certaines**

| Ligne | Item | Texte actuel | Faute | Correction proposee |
|---|---|---|---|---|
| 132 | 14 (Omar, page dechiree), champ `indice` | "Demande-toi qui doit savoir que le livre est abîmé ?" | Ponctuation : `qui doit savoir que le livre est abîmé` est une interrogation indirecte, qui se termine par un point, pas par un point d'interrogation. Dans le meme paquet, la ligne 108 ("Demande-toi quel objet ne sert qu'à une personne.") est correctement terminee par un point, et les lignes 54 et 90 sont des interrogations directes introduites par `:` | "Demande-toi : qui doit savoir que le livre est abîmé ?" (meme forme que les lignes 54 et 90 ; espace insecable avant `:` et avant `?`) |
| 8 | descripteur, champ `competence` | "Respecter les biens personnels et collectifs : reconnaître ce qui ..." | Typographie : l'espace avant `:` est une espace simple (U+0020). Le paquet declare lui-meme, ligne 46, que "l'espace qui precede ':' et '?' est insecable" (`TYP-1`, `ADR-0067`), et le seul autre paquet dont le descripteur contient un `:` (`ce1-inference`, ligne 8) a une espace insecable a cet endroit. Texte non lu par l'enfant d'apres `lecture-nadia-palier-3.md` section 4, a verifier a l'ecran : **non verifie** | Remplacer l'espace par U+00A0 |

Les deux autres occurrences d'une espace simple avant `:` ou `?` dans ce fichier sont dans des commentaires (lignes 18 et 22, en
ASCII) : hors regle.

**A trancher par `nadia-prof` (langue de l'enfant)**

| Ligne | Item | Texte actuel | Pourquoi je le signale |
|---|---|---|---|
| 99 | 9 | "Parmi ces objets, lequel n'est qu'à un seul élève ?" | `ne ... que` et `seul` disent deux fois la meme restriction ; la tournure est correcte mais lourde pour un enfant de CE1. Elle est symetrique de la ligne 87 ("Quel objet est à une seule personne ?"), plus simple |
| 93 | 8 | "Un seul de ces objets est à un seul élève. Lequel ?" | Deux `seul` a quelques mots d'intervalle, avec deux sens differents (un objet parmi trois, un eleve parmi tous). Correct ; la repetition peut gener la lecture |
| 75 et 51 | 5 et 1 | "Quel objet est à tout le monde dans l'école ?" et "Quel objet est à tous les élèves de l'école ?" | `dans l'école` et `de l'école` : les deux sont corrects. Je le signale seulement parce que les deux enonces ne se distinguent que par cette formule |

**Commentaire de depot a corriger** (`ce1-emc-biens-communs.yaml`, lignes 17-21)

- Texte actuel : "(six "quel objet est a tous" dont la bonne reponse nomme les trois exemples du libelle : materiel de la classe,
  livres de la bibliotheque, jeux de la cour ; ...)".
- Constat, verifie contre le code : une bonne reponse ne nomme qu'un exemple. Les six bonnes reponses de la tache 1 (`le ballon
  de la cour`, `le livre de la bibliothèque`, `la règle de la classe`, `le jeu de la cour`, `le crayon de la classe`, `le jeu de
  la classe`) nomment ensemble les trois. La phrase se lit comme si chaque bonne reponse les nommait tous.
- Correction proposee, non appliquee : "(six "quel objet est a tous", dont les bonnes reponses nomment, a elles six, les trois
  exemples du libelle : ...)".

**Verifie sans ecart** : 24 items, 12 par tache ; 7 prenoms feminins (Jade, Mila, Lina, Zoé, Inès, Aya, Maya) et 8 masculins
(Tom, Omar, Samir, Léo, Noé, Adam, Hugo, Enzo) comme annonce ; bonne reponse strictement la plus longue en caracteres dans 9
items (items 1, 2, 3, 4, 5, 6, 13, 18, 21) : borne de 12 tenue ; propositions de 6 mots au plus ; indices de 12 mots au plus et 24
distincts ; `ranger`, `remettre` et `à sa place` : 0 occurrence hors commentaire, comme l'en-tete l'annonce (lignes 23-24) ;
aucun possessif de deuxieme personne (`ton`, `ta`, `tes`, `votre`) hors descripteur ; aucun `chez` hors commentaire. Renvois de
l'en-tete a `ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md` section 4.1, a
`reponses-nadia-questions-stories-palier-3.md` section 4 et a `squad/programme/enseignement-moral-et-civique/competences-ce1.md` :
les fichiers existent et la section 4.1 existe.

## Convention d'attribution (`DEC-0062`)

Les commentaires d'en-tete des cinq paquets attribuent leurs conditions a `nadia-prof` par nom de fichier et de section (forme 1
de `DEC-0062`). Les renvois que j'ai pu resoudre le sont (listes ci-dessus). Renvois `DEC-0062` et `ADR-0067` cites par le
paquet : non verifies par moi, hors du perimetre de ce releve. Aucun enonce recu de MASTER dans les cinq fichiers.

## Points a ne pas oublier

- Pas de verification a l'ecran : l'affichage des espaces insecables, des chevrons et du descripteur est **non verifie**.
- Les fins de ligne des cinq fichiers sont CRLF dans l'arbre de travail (147, 147, 159, 125, 192 lignes sur autant de lignes).
  **Non verifie** dans les objets Git (reglage `autocrlf` possible) ; aucun lien avec le contenu.
- Le fichier est destine a `sofia-sm` pour `review.md`. Routage des points a trancher : langue de l'enfant a `nadia-prof` ; le
  renvoi du commentaire de `ce1-negation.yaml` ligne 1 a `camille-po` ; la faute certaine ligne 132 et l'espace de la ligne 8
  d'`ce1-emc-biens-communs.yaml` a `lea-dev` (auteur du paquet) ou a moi, apres accord de `nadia-prof`, en un commit `[REL]`
  limite aux deux champs.
