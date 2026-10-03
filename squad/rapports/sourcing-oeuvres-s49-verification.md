# Verification et sourcing reel des oeuvres candidates (T5 cote developpeur, ADR-0082)

- **Date** : 2026-10-03.
- **Auteur** : session principale (acces web reel, hors du mandat des sous-agents de la squad qui
  n'en disposent pas).
- **Ce que ce document fait** : verifie, oeuvre par oeuvre, la proposition de
  `squad/corpus/CHOIX-oeuvres-candidates-T5.md` (`nadia-prof`, 28 candidates, toutes marquees "a
  verifier") directement contre la fiche publiee par l'institution. Vingt-cinq entrees sont
  retenues et ecrites dans `squad/corpus/oeuvres-domaine-public.yaml`, avec le fichier JPEG
  correspondant redimensionne a 352 px de plus grand cote sous
  `adapters/adapter-web/src/main/resources/static/oeuvres/`.
- **Ce que ce document ne fait pas** : il ne prononce aucun Done pedagogique, ne juge pas `ART-5`
  (diversite du corpus), ne confirme pas que l'attribution est exacte au-dela de ce que la fiche de
  l'institution affirme, et ne choisit pas a la place de `nadia-prof` quand une candidate est
  ecartee - ces remplacements lui sont routes en section 3, et ses decisions sont en section 4.

## 1. Methode

Pour chaque candidate du tableau de section 4 de `CHOIX-oeuvres-candidates-T5.md` : recherche de
l'objet par son numero d'inventaire propose aupres de l'API ou du jeu de donnees ouvert de
l'institution citee (Metropolitan Museum of Art : `collectionapi.metmuseum.org` ; Art Institute of
Chicago : `api.artic.edu` ; National Gallery of Art, Washington : jeu de donnees ouvert
`github.com/NationalGalleryOfArt/opendata`, CSV `objects.csv` et `published_images.csv` complets,
82 Mo et 89 Mo) ; lecture du champ qui porte le domaine public et la licence (`isPublicDomain` au
Met et a l'AIC, `openaccess` a la NGA) ; verification de l'auteur, de sa date de mort, et de la
date de l'oeuvre contre le tableau de `nadia-prof` ; tout ecart est signale au lieu d'etre tu.

Le fichier image est le fichier source de l'institution (`primaryImage` au Met, IIIF a l'AIC et a
la NGA), telecharge puis redimensionne en une seule etape (Pillow, filtre Lanczos, JPEG qualite 85)
a 352 px de plus grand cote exactement, comme `ADR-0082` section "Le poids" le vise. Poids total des
vingt-cinq fichiers : **668 Ko**, dans la fourchette basse de l'estimation de l'`ADR` (1,1 a 2,2 Mo
pour 30 a 45 oeuvres).

## 2. Resultat, oeuvre par oeuvre (premier passage)

| # | Identifiant | Verdict | Ecart trouve avec la proposition de `nadia-prof` |
|---|---|---|---|
| 1 | `oeuvre-hippopotame` | **retenue** | aucun |
| 2 | `oeuvre-moissonneurs` | **retenue** | aucun |
| 3 | `oeuvre-chambre` | **bloquee**, section 3.1 | aucun sur les faits ; le fichier n'est pas recupere |
| 4 | `oeuvre-quatre-saisons` | **retenue** | numero d'inventaire reel `2010.77.1` (nadia-prof l'avait note "a trouver") |
| 5 | `oeuvre-grande-vague` | **retenue** | aucun |
| 6 | `oeuvre-marche-aux-chevaux` | **retenue** | aucun |
| 7 | `oeuvre-rue-sous-la-pluie` | **bloquee**, section 3.1 | aucun sur les faits ; le fichier n'est pas recupere |
| 8 | `oeuvre-laitiere` | **ecartee**, section 3.2, remplacee section 4 | licence reelle : Public Domain Mark 1.0, pas CC0-1.0 |
| 9 | `oeuvre-champ-de-ble` | **retenue** | aucun |
| 10 | `oeuvre-averse-sur-le-pont` | **retenue** | institution et numero trouves (Met, `JP2522`), nadia-prof avait note "a trouver" |
| 11 | `oeuvre-jeune-femme-aiguiere` | **retenue** | aucun |
| 12 | `oeuvre-licorne-captive` | **retenue** | titre exact de la fiche : *"The Unicorn Rests in a Garden"*, pas *"La Licorne captive"* - meme tapisserie (verifiee a l'image, licorne enchainee dans l'enclos fleuri), nom usuel different de l'intitule de catalogue |
| 13 | `oeuvre-grande-jatte` | **bloquee**, section 3.1 | aucun sur les faits ; le fichier n'est pas recupere |
| 14 | `oeuvre-autoportrait-leyster` | **retenue** | aucun |
| 15 | `oeuvre-autoportrait-deux-eleves` | **retenue** | aucun |
| 16 | `oeuvre-rhinoceros` | **retenue** | institution et numero trouves (Met, `19.73.159`), nadia-prof avait note "a trouver" |
| 17 | `oeuvre-belles-heures` | **retenue** | numero d'inventaire reel `54.1.1a, b` (suffixe absent de la proposition) |
| 18 | `oeuvre-vitrail` | **retenue** | objet choisi sur image parmi plusieurs candidats Met XIIIe siecle : panneau `29.156.49`, 1270, culture francaise |
| 19 | `oeuvre-fresque-romaine` | **retenue** | numero d'inventaire reel `03.14.13a-g` (suffixe absent) ; date confirmee 50-40 av. J.-C. |
| 20 | `oeuvre-vase-ming` | **retenue** | objet choisi sur image : *"Vase with rabbits"*, porcelaine bleu et blanc, fin XVIe siecle, Jingdezhen |
| 21 | `oeuvre-vase-shang` | **retenue** | objet choisi sur image : chaudron rituel *Ding*, periode confirmee *"Shang dynasty (ca. 1600-1046 BCE)"* au champ `period` du Met |
| 22 | `oeuvre-carreau-iznik` | **retenue** | objet choisi sur image : panneau de carreaux, debut XVIIe siecle |
| 23 | `oeuvre-bateau-egyptien` | **retenue** | objet choisi sur image : maquette de bateau, Moyen Empire |
| 24 | `oeuvre-amphore-grecque` | **retenue** | objet choisi sur image : amphore attique, vers 510 av. J.-C. ; la technique a figures noires n'est pas confirmee dans les champs lus, a verifier sur l'image par `nadia-prof` |
| 25 | `oeuvre-amerique-ancienne` | **retenue** | objet choisi sur image : figure de la deesse Chicomecoatl, culture mexica (azteque) |
| 26 | `oeuvre-photo-pyramides` | **retenue** | aucun |
| 27 | `oeuvre-photo-notre-dame` | **ecartee**, section 3.3, remplacee section 4 | institution reelle : National Gallery of Art (pas Paris Musees) ; vue trop eloignee pour etre lisible a 352 px |
| 28 | `oeuvre-photo-acropole` | **non verifiee**, section 3.4, remplacee section 4 | licence non confirmee programmatiquement |

**Vingt-deux retenues au premier passage, six non retenues** (trois bloquees par une protection
technique, une ecartee pour licence, une ecartee pour lisibilite, une non verifiee).

## 3. Les six non retenues au premier passage, en detail

### 3.1 Trois oeuvres de l'Art Institute of Chicago bloquees par Cloudflare

`oeuvre-chambre` (*The Bedroom*, Van Gogh, 1926.417), `oeuvre-rue-sous-la-pluie` (*Paris Street;
Rainy Day*, Caillebotte, 1964.336) et `oeuvre-grande-jatte` (*A Sunday on La Grande Jatte*, Seurat,
1926.224) sont **verifiees CC0-1.0** (API `api.artic.edu`, champ `is_public_domain: true`, licence
du corps de reponse *"Creative Commons Zero (CC0) 1.0"*) et les trois faits (auteur, date, mort)
correspondent exactement a la proposition de `nadia-prof`.

**Mais leur fichier image n'a pas pu etre telecharge.** Le serveur IIIF de l'Art Institute
(`artic.edu`) renvoie un defi Cloudflare (page de controle JavaScript, HTTP 403) a toute requete
automatisee, `curl` compris. Ce n'est pas un refus de licence, c'est une protection technique du
fournisseur qui echappe aux outils de ce depot. **Aucune entree de registre n'est ecrite pour ces
trois oeuvres** : ecrire une preuve sans image violerait la clause 2 de la regle executable
d'`ADR-0082`. Une personne munie d'un navigateur peut telecharger les trois fichiers directement
depuis les pages d'oeuvre citees et completer le registre a la main, sur le modele des entrees
existantes. `nadia-prof` a confirme (section 4) n'avoir aucune remplacante a proposer : le lot
d'arts plastiques CP (`ART-CP-09`) reste a 5 oeuvres sur 8 tant qu'elles n'entrent pas a la main.

### 3.2 `oeuvre-laitiere` (Vermeer, *La Laitiere*) - ecartee, licence refusee, remplacee

Nadia-prof avait signale elle-meme ce point de vigilance (`CHOIX-oeuvres-candidates-T5.md` section
2). Verifie : la fiche du Rijksmuseum (`SK-A-2344`) porte **"Public domain"**, avec un lien vers le
*Creative Commons Public Domain Mark 1.0*, pas CC0 1.0. Le registre n'admet que `CC0-1.0`
exactement (`ADR-0082` decision 1, et la regle executable, motif 8). **Ecartee sans discussion**,
conformement a l'instruction qu'elle avait donnee a l'avance. Remplacee en section 4.

### 3.3 `oeuvre-photo-notre-dame` - ecartee, lisibilite, remplacee

Deux ecarts trouves. **L'institution** : la piste de Paris Musees n'a pas donne de photographie de
Notre-Dame par Charles Marville ; l'objet retenu etait a la National Gallery of Art, Washington
(*The Seine from the Pont du Carrousel Looking towards Notre Dame*, 1853, `1995.36.102`), verifie
CC0-1.0 (`openaccess: 1` dans le jeu de donnees ouvert). **La composition** : a 352 px, c'est une
vue panoramique de la Seine ou la cathedrale n'occupe qu'une petite silhouette a l'arriere-plan -
image inspectee a cette taille, le sujet n'est pas reconnaissable. C'est exactement le critere que
nadia-prof posait elle-meme en section 3 de son document ("lisible a 352 pixels [...] un sujet
principal reconnaissable en vignette"). **Ecartee sur son propre critere.** Remplacee en section 4.

### 3.4 `oeuvre-photo-acropole` - non verifiee, remplacee

La piste la plus proche trouvee etait le J. Paul Getty Museum, dont le programme Open Content est
passe integralement sous licence CC0 en mars 2024 ; une photographie de l'Acropole par Felix
Bonfils y est repertoriee (*"Grece : Vue generale des monuments d'Athenes"*). **Mais la page de
collection ne rend pas son contenu sans JavaScript**, et aucune API publique du Getty n'a ete
trouvee pour confirmer programmatiquement la licence et le numero d'objet de cette image precise.
Une alternative verifiee existe au Met : trois photographies *"[Acropolis, Athens, Greece]"* de
James Robertson, annees 1850, `isPublicDomain: true` (`2005.100.1352` a `.1354`) - un photographe
different de celui que `nadia-prof` avait choisi. **Aucune des deux n'a ete ecrite au registre au
premier passage** : substituer l'auteur sans son accord aurait ete exactement le geste qu'elle a
interdit a l'avance ("Ne pas la remplacer soi-meme"). Elle a tranche en section 4.

## 4. Decisions de `nadia-prof` et remplacantes verifiees

`nadia-prof` a statue sur les quatre situations de la section 3
(`squad/corpus/CHOIX-oeuvres-candidates-T5.md` section 8). Trois remplacantes ont ete proposees
puis verifiees ici selon la meme methode qu'en section 1 ; elles sont ecrites au registre.

| Remplace | Identifiant final | Oeuvre | Verification |
|---|---|---|---|
| `oeuvre-laitiere` | `oeuvre-femme-au-luth` | *Young Woman with a Lute*, Vermeer, Metropolitan Museum of Art, `25.110.24` | CC0-1.0 confirme (`isPublicDomain: true`), mort 1675, 1662-63. Luth lisible a 352 px, verifie a l'image ; meme composition a fenetre a gauche que `oeuvre-jeune-femme-aiguiere`, la paire que `nadia-prof` voulait preserver pour `ART-CP-10` |
| `oeuvre-photo-notre-dame` | `oeuvre-photo-notre-dame` (meme identifiant, contenu remplace) | *Notre-Dame (facade)*, Edouard Baldus, Metropolitan Museum of Art, `1978.671.9.3` | CC0-1.0 confirme, mort 1889 (annee retenue au registre : 1869, la plus tardive de la fourchette "1860s"). Les deux tours et la rosace sont lisibles a 352 px, verifie a l'image - critere de lisibilite de `nadia-prof` tenu cette fois |
| `oeuvre-photo-acropole` | `oeuvre-photo-acropole` (auteur remplace) | *[Acropolis, Athens, Greece]*, James Robertson (pas Felix Bonfils), Metropolitan Museum of Art, `2005.100.1353` | CC0-1.0 confirme, mort **1881** (le Met corrige la date 1888 transmise de memoire a l'origine). Vue frontale du Parthenon choisie parmi les trois photographies du meme fonds (`.1352` a `.1354`) : c'est la plus lisible en vignette, les deux autres (vue d'ensemble eloignee, detail des Propylees) le sont moins |

**Registre final : 25 oeuvres, 668 Ko.**

## 5. Ce qui reste du, nomme precisement

1. **Le cliquet de poids et le compte de reproductions servies**
   (`REPRODUCTIONS_SERVIES`, `POIDS_TOTAL_MAXIMUM` dans
   `bootstrap/src/test/java/com/recreo/archi/ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest.java`)
   : route a `theo-archi` des le premier passage (22 fichiers), a reverifier sur l'etat final a 25
   fichiers / 668 Ko avant tout commit.
2. **Trois fichiers a recuperer a la main** (`oeuvre-chambre`, `oeuvre-rue-sous-la-pluie`,
   `oeuvre-grande-jatte`) depuis les pages de l'Art Institute of Chicago citees en section 3.1, par
   une personne munie d'un navigateur - les trois sont deja verifiees CC0 1.0 et pretes a entrer au
   registre des que le fichier existe. `nadia-prof` n'a pas de remplacante a proposer en attendant.
3. **La technique a figures noires de `oeuvre-amphore-grecque`** n'est pas confirmee dans les
   champs lus (section 2, ligne 24) : a verifier sur l'image par `nadia-prof`.
4. **Les cinq questions que `nadia-prof` routait a `theo-archi` et `camille-po`**
   (`CHOIX-oeuvres-candidates-T5.md` section 6) restent entierement ouvertes : annee avant notre ere
   (cinq entrees de ce registre en portent une, ecrite en entier negatif faute de convention
   actee), photographie d'un monument a deux auteurs, detail d'une oeuvre, nom de convention pour un
   artisan inconnu, provenance coloniale contestee.
5. **`ART-5`** (diversite du corpus retenu) et l'exactitude de chaque attribution restent
   entierement a `nadia-prof` : ce document ne les evalue pas.
6. **Aucun paquet de contenu ne sert encore ces images** (T6 a T8 d'`ADR-0082`) : le registre et le
   mecanisme existent, mais la ligne `ART-1` a `ART-5` qu'un paquet reel doit tenir n'est pas
   engagee ici. C'est un chantier de plusieurs sprints, hors du perimetre de ce document.
