# Choix des oeuvres candidates du corpus du domaine public - T5 d'`ADR-0082`

- **Auteur** : `nadia-prof`. **Date** : 2026-10-03. **Sprint** : s49.
- **Ce qui m'est pose** : T5 du chantier de sourcing (`ADR-0082`, section "Les taches"), entre au
  perimetre de s49 par `DEC-0107` section 6 : le choix des oeuvres, prealable a toute recherche de
  sources par un developpeur.
- **Ce que ce document n'est pas** : ni un Done pedagogique, ni une entree du registre
  `squad/corpus/oeuvres-domaine-public.yaml`, ni une preuve de domaine public.
- **Lien** : `ADR-0082` ; `DEC-0077` ; `DEC-0107` ; `squad/programme/MATRICE-couverture.md`
  (arts plastiques CP et CM1, histoire des arts CM1) ;
  `squad/programme/arts-plastiques/competences-cp.md`, `squad/programme/arts-plastiques/competences-cm1.md`,
  `squad/programme/histoire-des-arts/competences-cm1.md` (`ART-1` a `ART-5`, `HIS-2`, `HIS-3`).

---

## 1. La limite de ce document, a lire avant toute ligne du tableau

**Aucune des oeuvres ci-dessous n'est verifiee.** Je n'ai aucun acces au web. J'ai choisi des
oeuvres notoirement anciennes, d'auteurs morts avant 1900 ou anonymes et anterieures a 1900,
conservees, **a ma connaissance**, par des institutions qu'`ADR-0082` decision 1 cite comme pistes
d'acces ouvert. Tout ce qui suit est donc une **proposition a verifier**, jamais un fait etabli :

- **l'annee de mort de l'auteur et la date de l'oeuvre** sont ecrites de memoire ;
- **le lieu de conservation et le numero d'inventaire** sont ecrits de memoire, et un numero de
  memoire est exactement le genre de donnee qui a l'air juste et ne l'est pas ;
- **la renonciation CC0 1.0** n'est verifiee pour **aucune** oeuvre. Qu'une institution ait un
  programme d'acces ouvert ne prouve pas que **cette** image en fasse partie, ni qu'elle soit
  publiee sous CC0 1.0 plutot que sous une autre marque.

Une entree du registre ne s'ecrit qu'apres que le developpeur de T2-T4, ou une personne, a ouvert la
fiche publiee par l'institution et y a lu, pour cette image : CC0 1.0, l'identifiant d'objet,
l'auteur, la date. **Si un seul de ces points differe de ce tableau, c'est la fiche qui a raison,
et l'ecart m'est signale** : une date ou une attribution fausse dans ce document est une erreur de
ma part, pas un detail.

## 2. Une correction sur la commande, avant de choisir

La commande qui m'a ete transmise cite *"Wikimedia Commons, Gallica"* parmi les institutions
susceptibles d'avoir publie une renonciation CC0. **`ADR-0082` les ecarte toutes les deux comme
preuve** :

- **Wikimedia Commons** : la licence y est la revendication d'un contributeur, pas la renonciation
  d'un titulaire identifiable (`ADR-0082`, "Alternatives considerees"). Bon endroit pour trouver
  une oeuvre, jamais pour la prouver ;
- **Gallica (Bibliotheque nationale de France)** : l'institution attache ses propres conditions a la
  reutilisation de ses reproductions (`ADR-0082`, contexte). Ce n'est pas une renonciation CC0.

J'ajoute trois sources que je n'ai pas retenues pour le meme motif, a verifier plutot qu'a croire :
le **Louvre** et le **musee d'Orsay** (conditions propres de reutilisation, a ma connaissance), le
**British Museum** (licence avec clause non commerciale, a ma connaissance). C'est ce qui ecarte du
tableau plusieurs oeuvres plus connues d'un enfant francais, et c'est voulu.

**Rijksmuseum, point de vigilance propre** : je ne sais pas si ses images sont publiees sous CC0 1.0
ou sous une marque de domaine public. Le registre n'admet que `CC0-1.0`. Si la fiche porte une autre
marque, l'oeuvre sort du tableau, sans discussion.

## 3. Les criteres de mon choix

Ce sont des criteres de choix, pas des conditions nouvelles : ils ne portent aucun identifiant.

1. **Lisible a 352 pixels de plus grand cote** (`ADR-0082`, section sur le poids) : un sujet
   principal reconnaissable en vignette. Une foule de cent personnages ne se decrit pas a cette taille.
2. **Pas de texte en alphabet latin lisible dans l'image pour le CP.** C'est une prudence de choix
   en attendant la reponse a la question 4 que m'a routee `ADR-0082`, pas cette reponse.
3. **`ART-1`** : aucune oeuvre n'est choisie pour une propriete qui ne se voit qu'a la couleur.
4. **`ART-4`** : aucune oeuvre n'est choisie parce que son titre porte la reponse. Le registre n'est
   pas affiche a l'enfant (`ADR-0082` decision 8), mais l'enonce d'un item ne doit pas le recopier.
5. **`ART-5`** sur chaque ligne : plusieurs epoques, plusieurs aires culturelles, et des artistes
   femmes. Le pivot de 1900 rend ce dernier axe difficile ; je nomme les trois retenues en section 5.
6. **Une seule annee de mort par oeuvre** : le registre porte un seul champ `auteur` et un seul champ
   `anneeDeMortDeLAuteur`. Seule exception du vivier, l'oeuvre 17 : les trois freres de Limbourg
   sont morts tous trois en 1416 (a verifier), donc une seule annee suffit.

## 4. Le vivier : 28 oeuvres candidates

Identifiant propose au format d'`ADR-0082` decision 2 (`oeuvre-[a-z0-9-]+`). Colonnes "Mort",
"Date", "Institution" et "Piste d'inventaire" : **de memoire, toutes a verifier.** Une annee avant
notre ere est notee "av.".

| # | Identifiant propose | Oeuvre | Auteur | Mort | Date | Institution (piste) | Piste d'inventaire |
|---|---|---|---|---|---|---|---|
| 1 | `oeuvre-hippopotame` | Hippopotame dit "William", faience | anonyme, Egypte, Moyen Empire | - | vers 1961-1878 av. | Metropolitan Museum of Art, New York | 17.9.1 |
| 2 | `oeuvre-moissonneurs` | Les Moissonneurs | Pieter Bruegel l'Ancien | 1569 | 1565 | Metropolitan Museum of Art | 19.164 |
| 3 | `oeuvre-chambre` | La Chambre (version de septembre 1889) | Vincent van Gogh | 1890 | 1889 | Art Institute of Chicago | 1926.417 |
| 4 | `oeuvre-quatre-saisons` | Les Quatre Saisons en une tete | Giuseppe Arcimboldo | 1593 | vers 1590 | National Gallery of Art, Washington | a trouver |
| 5 | `oeuvre-grande-vague` | Sous la vague au large de Kanagawa, estampe | Katsushika Hokusai | 1849 | vers 1830-1832 | Metropolitan Museum of Art | JP1847 |
| 6 | `oeuvre-marche-aux-chevaux` | Le Marche aux chevaux | Rosa Bonheur | 1899 | 1852-1855 | Metropolitan Museum of Art | 87.25 |
| 7 | `oeuvre-rue-sous-la-pluie` | Rue de Paris, temps de pluie | Gustave Caillebotte | 1894 | 1877 | Art Institute of Chicago | 1964.336 |
| 8 | `oeuvre-laitiere` | La Laitiere | Johannes Vermeer | 1675 | vers 1660 | Rijksmuseum, Amsterdam (voir section 2) | SK-A-2344 |
| 9 | `oeuvre-champ-de-ble` | Champ de ble avec cypres | Vincent van Gogh | 1890 | 1889 | Metropolitan Museum of Art | 1993.132 |
| 10 | `oeuvre-averse-sur-le-pont` | Averse soudaine sur le pont Shin-Ohashi, estampe | Utagawa Hiroshige | 1858 | 1857 | a trouver (exemplaire dans une collection CC0) | a trouver |
| 11 | `oeuvre-jeune-femme-aiguiere` | Jeune femme a l'aiguiere | Johannes Vermeer | 1675 | vers 1662 | Metropolitan Museum of Art | 89.15.21 |
| 12 | `oeuvre-licorne-captive` | La Licorne captive, tapisserie | anonyme, Pays-Bas du Sud | - | 1495-1505 | Metropolitan Museum of Art, The Cloisters | 37.80.6 |
| 13 | `oeuvre-grande-jatte` | Un dimanche apres-midi a l'ile de la Grande Jatte | Georges Seurat | 1891 | 1884-1886 | Art Institute of Chicago | 1926.224 |
| 14 | `oeuvre-autoportrait-leyster` | Autoportrait | Judith Leyster | 1660 | vers 1630 | National Gallery of Art, Washington | 1949.6.1 |
| 15 | `oeuvre-autoportrait-deux-eleves` | Autoportrait avec deux eleves | Adelaide Labille-Guiard | 1803 | 1785 | Metropolitan Museum of Art | 53.225.5 |
| 16 | `oeuvre-rhinoceros` | Le Rhinoceros, gravure sur bois | Albrecht Durer | 1528 | 1515 | a trouver (exemplaire dans une collection CC0) | a trouver |
| 17 | `oeuvre-belles-heures` | une page des Belles Heures de Jean de Berry | freres de Limbourg | 1416 | 1405-1409 | Metropolitan Museum of Art, The Cloisters | 54.1.1 |
| 18 | `oeuvre-vitrail` | un panneau de vitrail, France, XIIIe siecle | anonyme | - | a fixer | Metropolitan Museum of Art, The Cloisters | a choisir |
| 19 | `oeuvre-fresque-romaine` | fresques d'une chambre de la villa de Boscoreale | anonyme, Rome antique | - | vers 50-40 av. | Metropolitan Museum of Art | 03.14.13 |
| 20 | `oeuvre-vase-ming` | un vase en porcelaine bleu et blanc, dynastie Ming | anonyme, Chine | - | XVe siecle, a fixer | Metropolitan Museum of Art | a choisir |
| 21 | `oeuvre-vase-shang` | un vase rituel en bronze, dynastie Shang | anonyme, Chine | - | a fixer | Metropolitan Museum of Art | a choisir |
| 22 | `oeuvre-carreau-iznik` | un carreau ou panneau de carreaux d'Iznik | anonyme, empire ottoman | - | XVIe siecle, a fixer | Metropolitan Museum of Art | a choisir |
| 23 | `oeuvre-bateau-egyptien` | une maquette de bateau en bois peint, tombe de Meketre | anonyme, Egypte, Moyen Empire | - | vers 1981-1975 av. | Metropolitan Museum of Art | a choisir |
| 24 | `oeuvre-amphore-grecque` | une amphore attique a figures noires | anonyme, Grece antique | - | VIe siecle av., a fixer | Metropolitan Museum of Art | a choisir |
| 25 | `oeuvre-amerique-ancienne` | un objet azteque ou moche en pierre ou en ceramique | anonyme, Amerique precolombienne | - | a fixer | Metropolitan Museum of Art | a choisir |
| 26 | `oeuvre-photo-pyramides` | photographie des pyramides de Gizeh | Francis Frith | 1898 | 1858 | Metropolitan Museum of Art | a trouver |
| 27 | `oeuvre-photo-notre-dame` | photographie de Notre-Dame de Paris | Charles Marville | 1879 | a fixer | Paris Musees (musee Carnavalet) | a trouver |
| 28 | `oeuvre-photo-acropole` | photographie de l'Acropole d'Athenes | Felix Bonfils | 1885 | a fixer | a trouver | a trouver |

"a choisir" : je designe un type d'objet, pas un objet. Le developpeur propose deux ou trois objets
de ce type trouves avec une fiche CC0, et **je choisis sur image** avant toute entree au registre.

## 5. Repartition par ligne bloquee

Les neuf lignes sont celles de `ADR-0082` (contexte) et de la matrice. Une oeuvre peut servir
plusieurs lignes ; le nombre de la derniere colonne est le nombre d'oeuvres proposees pour la ligne.

| Ligne | Oeuvres (numeros de la section 4) | Pourquoi ces oeuvres | Nombre |
|---|---|---|---|
| `ART-CP-09` Decrire une oeuvre (sujet, couleurs, formes, materiaux) | 1, 2, 3, 4, 5, 6, 7, 8 | Un sujet principal que l'enfant de six ans nomme avec ses mots : un hippopotame, une chambre, un visage fait de fruits et de legumes, une vague, des chevaux, des parapluies, une femme qui verse du lait, des gens qui coupent le ble. Le materiau se voit sur 1 (objet en volume) contre les peintures. Trois continents, du Moyen Empire egyptien a 1889, une artiste femme (6) | **8** |
| `ART-CP-10` Reconnaitre des oeuvres deja rencontrees et les rapprocher | les 8 de `ART-CP-09`, plus 9, 10, 11 | "Deja rencontrees" impose de reutiliser le vivier de la ligne 9. Les trois ajouts font chacun une paire qu'un enfant de CP peut rapprocher en regardant : 3 et 9 (meme peintre, meme touche), 5 et 10 (estampes japonaises, meme maniere de dessiner l'eau et la pluie), 8 et 11 (meme peintre, meme lumiere de fenetre a gauche) | **11** |
| `ART-CM1-07` Reperer les effets de la matiere, de la couleur, du geste et du support | 1, 5, 9, 12, 13, 14, 16 | Le geste visible : la touche epaisse (9), les points (13, voir section 6), le trait grave (5, 16). La matiere : faience (1), fils tisses (12). Le support : toile d'une artiste qui se montre au travail (14) | **7** |
| `ART-CM1-08` Decrire une oeuvre avec un lexique elementaire (support, format, matiere, couleur, sujet) | 1, 2, 5, 8, 12, 15, 17 | Des formats differents (horizontal 2 et 5, vertical 8 et 15, petit format de livre 17), des supports differents (bois, papier, toile, parchemin, laine), un objet en volume (1). La composition reste non couverte, comme la matrice l'exige deja | **7** |
| `HDA-CM1-01` Decrire une oeuvre avec un lexique simple (support, format, matiere, couleur, sujet) | 1, 2, 5, 6, 12, 15, 17, 20 | Meme lexique que `ART-CM1-08`, sur un vivier plus large en epoques et en aires culturelles, puisque la matiere est l'histoire des arts | **8** |
| `HDA-CM1-02` Degager les caracteristiques techniques et formelles | 5, 9, 16, 17, 18, 19 | Une technique par oeuvre, chacune reconnaissable a l'image : estampe, peinture a la touche, gravure sur bois, enluminure, vitrail, fresque | **6** |
| `HDA-CM1-03` Identifier les materiaux et leur mise en forme | 1, 12, 18, 20, 21, 22, 23 | Un materiau par oeuvre : faience, laine et soie tissees, verre, porcelaine, bronze, ceramique peinte, bois peint. `ART-4` mord ici : l'enonce ne nomme jamais le materiau, et le titre du registre ne s'affiche pas | **7** |
| `HDA-CM1-04` Associer une oeuvre a une epoque et a une civilisation | 1, 2, 5, 18, 19, 21, 22, 24, 25 | Neuf civilisations ou epoques reconnaissables a des elements observes : Egypte antique, Grece antique, Rome antique, Chine des Shang, Moyen Age europeen, Renaissance flamande, empire ottoman, Japon d'Edo, Amerique precolombienne. Les distracteurs naturels que la ligne demande existent : Grece et Rome, Moyen Age et Renaissance. La tapisserie 12 (1495-1505) est ecartee de cette ligne : elle se situe a la charniere du Moyen Age et de la Renaissance, deux reponses defendables (`HIS-2`) ; le vitrail du XIIIe siecle (18) la remplace | **9** |
| `HDA-CM1-06` Identifier des reperes du patrimoine (identification et ordre entre deux oeuvres) | 1, 2, 5, 19, 26, 27, 28 | Des reperes connus a l'ecole, ordonnables deux a deux sans hesitation : pyramides, Acropole, fresque romaine, cathedrale gothique, Renaissance, estampe japonaise. Le placement sur une frise reste non couvert, comme la matrice l'exige deja | **7** |

**`ART-5`, vivier entier** : artistes femmes 3 sur 16 auteurs nommes distincts (6, 14, 15) ; aires
culturelles : Europe, Egypte, Grece, Rome, Chine, Japon, empire ottoman, Amerique precolombienne.
**Lacune nommee** : aucune oeuvre d'Afrique subsaharienne, d'Oceanie ni d'Asie du Sud. Des objets
anterieurs a 1900 existent dans des collections CC0, mais une partie d'entre eux pose une question
de provenance coloniale que je ne tranche pas ici ; elle est routee en section 6.

## 6. Ce que ce choix a revele, et a qui je le route (via `sofia-sm`)

| Code | Point | A qui | Bloque T2-T4 ? |
|---|---|---|---|
| Q1 | **Annee avant notre ere.** Le garde-fou lit `anneeDeLOeuvre` comme un entier ; cinq oeuvres du vivier sont anterieures a notre ere (1, 19, 21, 23, 24). Aucune convention n'est ecrite (entier negatif ? quelle annee pour une fourchette ?) | `theo-archi` | non, mais avant la premiere entree antique |
| Q2 | **Photographie d'un monument : deux auteurs.** Pour 26, 27 et 28, il y a deux oeuvres l'une dans l'autre : la photographie et le monument. Le registre n'a qu'un champ `auteur`. Les trois monuments retenus sont anonymes et anterieurs a 1900, donc le cas est sain ; le registre ne le montre pas | `theo-archi` | non |
| Q3 | **Detail d'une oeuvre.** A 352 pixels, les points de Seurat (13) et une partie des touches de Van Gogh (9) ne se voient pas : la ligne `ART-CM1-07` demande le geste, qui se lit sur un detail. `ADR-0082` decision 2 prevoit un fichier source **redimensionne**, en une seule etape. Un recadrage est-il admis ? Sinon, 13 sort du vivier de `ART-CM1-07` | `theo-archi` | non |
| Q4 | **Nom de convention pour un artisan inconnu** ("Peintre de ..." sur un vase grec). Le registre admet un auteur nomme avec une annee de mort, ou `anonyme`. Ma proposition : inscrire `anonyme`, parce que l'auteur n'est pas connu. A confirmer pour la forme du registre | `theo-archi` | non |
| Q5 | **Objets a provenance coloniale contestee** (lacune de la section 5) : le produit peut-il servir a un enfant un objet dont la restitution est en debat ? Ce n'est ni une question de droits d'usage ni une question de niveau scolaire | `camille-po`, puis l'utilisatrice | non |

**Les cinq questions qu'`ADR-0082` m'a routees le 2026-09-05 restent ouvertes.** Ce document n'y
repond pas, et le critere 2 de la section 3 n'est pas une reponse a la quatrieme. Une observation
pour la deuxieme (le pivot de 1900) : le vivier de `HDA-CM1-04` n'y perd rien, il va de l'Egypte
antique a 1889 ; le vivier de `HDA-CM1-06` perd tous les reperes du vingtieme siecle (par exemple la
tour Eiffel, dont les auteurs sont morts apres 1900). Elles bornent T6 a T8, pas T2 a T4.

## 7. Ordre de travail propose au developpeur

1. Commencer par les huit oeuvres de `ART-CP-09` : c'est le premier increment qu'`ADR-0082`
   recommande (lot d'arts plastiques CP, lignes 9 et 10).
2. Pour chaque oeuvre : ouvrir la fiche de l'institution, verifier les quatre points de la section 1,
   noter tout ecart a ce tableau.
3. Une oeuvre sans CC0 1.0 sur sa fiche sort du vivier ; me la signaler pour que je choisisse une
   remplacante qui garde `ART-5` sur la ligne. **Ne pas la remplacer soi-meme**, ni la garder sous
   une autre licence : le registre ne l'admet pas.
4. Pour les lignes "a choisir" : proposer deux ou trois objets avec leur fiche, je choisis sur image.

**Ce dont je reste la seule garde sur ce vivier** : l'exactitude de chaque attribution et de chaque
date une fois la fiche lue, la fidelite de la reproduction retenue, et `ART-5` paquet par paquet.
Aucun garde-fou ne le verifie ; le registre en tient la forme, pas la verite.

## 8. Ajout du 2026-10-03 : decisions sur les six candidates non retenues

Ajout date, apres lecture de `squad/rapports/sourcing-oeuvres-s49-verification.md`. Les sections 4
et 5 ci-dessus restent telles que je les ai ecrites, pour que l'ecart entre ma proposition et la
verification reste lisible ; **ce sont les tableaux de cette section-ci qui font foi** pour les
oeuvres 8, 27 et 28 et pour les lignes qu'elles touchent.

### 8.1 Une correction, avant de decider

Le rapport de verification (section 3.2) et la commande qui m'a ete transmise placent
`oeuvre-laitiere` dans `ART-CM1-07`, `ART-CM1-08` et `HDA-CM1-01`. **Ma section 5 la place dans
`ART-CP-09`, `ART-CP-10` et `ART-CM1-08`** : elle n'est ni dans `ART-CM1-07` (1, 5, 9, 12, 13, 14,
16) ni dans `HDA-CM1-01` (1, 2, 5, 6, 12, 15, 17, 20). L'ecart compte : la perte touche le CP, et
surtout `ART-CP-10`, dont la paire 8 et 11 (meme peintre, meme lumiere de fenetre a gauche) est
cassee. C'est ce qui decide de la remplacer.

### 8.2 Decisions

| # | Candidate | Decision |
|---|---|---|
| 3, 7, 13 | `oeuvre-chambre`, `oeuvre-rue-sous-la-pluie`, `oeuvre-grande-jatte` | **Gardees, pas de remplacante.** Le blocage est un telechargement manuel, pas un probleme de licence ni de choix. Consequence a connaitre : tant que les trois fichiers manquent, `ART-CP-09` n'a que 5 oeuvres sur 8 au registre, et `ART-CP-10` perd la paire 3 et 9. Le lot d'arts plastiques CP ne se joue pas avant leur entree |
| 8 | `oeuvre-laitiere` | **Remplacee par 29**, un autre Vermeer, pour garder la paire de `ART-CP-10` |
| 27 | `oeuvre-photo-notre-dame` | **Gardee comme repere, objet a choisir sur image** (8.3). Repli si aucune vue nette n'est trouvee : une autre cathedrale gothique |
| 28 | `oeuvre-photo-acropole` | **James Robertson accepte a la place de Felix Bonfils.** Rien dans mon choix ne tenait a Bonfils : je cherchais une vue de l'Acropole par un photographe mort avant 1900, ecrite de memoire. La fiche du Met est verifiee, celle du Getty ne l'est pas. Condition : je choisis sur image, parmi les trois objets `2005.100.1352` a `.1354`, celui ou le Parthenon est le sujet principal reconnaissable a 352 px. Si aucun ne l'est, on revient a la verification du Getty |

### 8.3 Candidates nouvelles ou modifiees

Comme en section 4 : **de memoire, a verifier sur la fiche**. Je ne garantis aucune licence.

| # | Identifiant propose | Oeuvre | Auteur | Mort | Date | Institution (piste) | Piste d'inventaire |
|---|---|---|---|---|---|---|---|
| 29 | `oeuvre-femme-au-luth` | Femme au luth | Johannes Vermeer | 1675 | vers 1662-1663 | Metropolitan Museum of Art | 25.110.24 |
| 29 bis | (repli de 29) | Jeune femme endormie | Johannes Vermeer | 1675 | vers 1656-1657 | Metropolitan Museum of Art | 14.40.611 |
| 27 | `oeuvre-photo-notre-dame` | photographie de la facade de Notre-Dame de Paris | a choisir : Charles Marville (1879), Edouard Baldus (1889) ou Henri Le Secq (1882) | voir auteur | avant 1900 | Metropolitan Museum of Art ou National Gallery of Art | a choisir |
| 28 | `oeuvre-photo-acropole` | [Acropolis, Athens, Greece] | James Robertson | 1888 | annees 1850 | Metropolitan Museum of Art | 2005.100.1352 a .1354, un des trois |

- **29** : sujet nommable par un enfant de six ans ("une dame qui joue de la musique"), lumiere
  d'une fenetre a gauche comme 11, pas de texte lisible a 352 px (la carte murale ne se lit pas a
  cette taille, a confirmer sur l'image). Le tableau est sombre et use : si le luth ne se reconnait
  pas en vignette, 29 bis le remplace, au prix d'une paire moins nette avec 11 (lumiere differente).
- **27** : un **type d'objet**, comme les lignes "a choisir" de la section 4. Critere : la facade
  vue de face, les deux tours visibles, la cathedrale occupant plus de la moitie de l'image. Le
  developpeur propose deux ou trois objets de ce type avec leur fiche, je choisis sur image.
  **Ecarter les freres Bisson** : Auguste-Rosalie Bisson est mort en 1900, ce qui ne satisfait pas
  "mort avant 1900". **Repli** si aucune vue nette n'existe : la facade d'une autre cathedrale
  gothique (Reims, Chartres, Amiens), meme critere ; le repere vise par la ligne est "une
  cathedrale gothique", Notre-Dame n'etait que la plus connue a l'ecole.

### 8.4 Repartition des lignes touchees

Seules les lignes qui changent. Les autres restent celles de la section 5.

| Ligne | Oeuvres | Nombre |
|---|---|---|
| `ART-CP-09` | 1, 2, 3, 4, 5, 6, 7, 29 ("une dame qui joue de la musique" remplace "une femme qui verse du lait") | **8** |
| `ART-CP-10` | les 8 de `ART-CP-09`, plus 9, 10, 11 ; la paire 29 et 11 remplace la paire 8 et 11 | **11** |
| `ART-CM1-08` | 1, 2, 5, 12, 15, 17, 29 ; format vertical : 15, et 29 a confirmer sur l'image (proche du carre) | **7** |
| `HDA-CM1-06` | 1, 2, 5, 19, 26, 27, 28 ; 27 a choisir sur image, 28 par James Robertson | **7** |

**`ART-5`** : les comptes de la section 5 ne changent pas (Vermeer remplace Vermeer, un photographe
europeen du XIXe siecle en remplace un autre). Une perte a nommer : la laitiere etait la seule
figure de travail domestique du vivier de CP ; le travail reste represente par 2 (moissonneurs).
