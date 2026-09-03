# Rapport qualite - s27, yanis-qa

- **Mesure sur** : `round-two` a `fe14ea2` (l'arbre a bouge une fois pendant ma revue, de `d7fab95`
  a `fe14ea2` ; je me suis recale et toute mesure de ce rapport est faite sur `fe14ea2`, pas sur
  l'etat precedent).
- **Worktree dedie** : `../recreo-s27-yanis`, branche `s27-yanis`, lignee verifiee par
  `bash outils/verifier-lignee.sh` ("le worktree sur la branche 's27-yanis' descend de la lignee
  de round-two"). Je n'ai touche aucun autre worktree de la squad.
- **JDK** : celui que la machine virtuelle des tests rapporte d'elle-meme (bannieres de demarrage
  Spring Boot des tests d'integration), pas celui d'une entree de toolchain recopiee (`ADR-0071`) -
  **21.0.10**. Enveloppe : `bash outils/mvn-jdk21.sh`.

## Porte technique - commande, resultat, methode de comptage

```
bash outils/mvn-jdk21.sh -B clean verify
```

**Reacteur complet, aucun `-pl`, executee par moi-meme sur `fe14ea2`.** Resultat : **BUILD
SUCCESS**, les six modules (`recreo`, `recreo-domain`, `recreo-application`, `recreo-adapter-web`,
`recreo-adapter-content`, `recreo-bootstrap`) en `SUCCESS`.

**Compte de tests, methode de l'amendement s11** : somme de l'attribut `tests="..."` de chaque
rapport XML sous `target/surefire-reports/` et `target/failsafe-reports/` des cinq modules qui en
produisent, apres `clean` (158 fichiers XML lus) : **1125 tests, 0 echec, 0 erreur**. Je confirme
le chiffre annonce par `sofia-sm`/`theo-archi`, par ma propre execution, sur mon propre `clean`,
sur l'arbre final du sprint.

**Tests instables constates** : aucun. Une seule execution de `mvn -B clean verify` a produit ce
resultat ; je n'ai relance aucun test pour l'obtenir.

**Verdict** : porte technique verte. Ceci ne dit rien du Done pedagogique, qui reste a
`nadia-prof` seule - le rejeu de `cp-monnaie` et le Done des paquets de `questionner le monde` et
`histoire-geographie` (lignes ouvertes, pas fermees, `nadia-prof` section 8.4 du planning) ne sont
pas de mon ressort.

---

## Ce qui m'etait demande : chercher le quatorzieme defaut par mutation sur materiel reel

Methode constante sur tout ce rapport : je ne crois aucun vert sans l'avoir casse. Chaque mutation
ci-dessous est appliquee au code de production ou au contenu servi reel, en memoire ou sur disque
dans mon propre worktree, jamais sur une fixture imaginee ; chaque probe est retiree avant de
conclure (`git status --short` verifie vide apres coup, a chaque fois).

### 1. `ADR-0079` (lot de matiere) - defaut confirme, encore ouvert sur `fe14ea2`

**Question posee** : le lot est la cle `matiere + " / " + niveau`, lue telle quelle dans le
descripteur YAML de chaque paquet (`Paquet.lot()`,
`UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest.java`). Aucune normalisation
(trim, casse, accent) n'est appliquee avant le regroupement. Que se passe-t-il si un second
fichier du meme lot reel ecrit ce couple avec une variation minime ?

**Mutation** : ajout en memoire d'un `Paquet` synthetique portant la meme matiere reelle
(`Histoire-géographie`) et le meme niveau avec un espace de fin (`"CM1 "` au lieu de `"CM1"`),
10 items, **zero marqueur `HIS-1`** - le geste exact que la morsure `unFichierQuiRejointUnLotSoumis
SansMarqueurFaitEchouerLeGardeFou` prouve deja pour un descripteur identique.

**Resultat, execution reelle (`bash outils/mvn-jdk21.sh -pl adapters/adapter-content -am test`,
probe ajoutee puis retiree, aucune trace commitee)** :

```
SONDE-YAN nombre de lots distincts : [..., Histoire-géographie / CM1, ..., Histoire-géographie / CM1 ]
java.lang.AssertionError: Expecting code to raise a throwable.
```

**Le lot se scinde en silence.** Le fichier synthetique forme son propre lot d'un seul membre,
`marques == 0`, la boucle de `verifier()` fait `continue` avant tout controle : le fichier entier
qui rejoint le lot sans marqueur **n'est refuse par rien**, exactement le "vert a tort" que
`ADR-0079` a ete ecrit pour fermer - reintroduit par la fragilite de sa propre cle de
regroupement. Un auteur humain qui tape le descripteur a la main (espace de fin, accent oublie,
casse differente) declenche ce mode de defaillance sans qu'aucun message ne l'avertisse.

**Ce que je remonte** : `ADR-0079` clause 2 devrait comparer le couple (matiere, niveau) apres une
normalisation minimale (au moins `strip()`), ou faire echouer explicitement un lot dont deux
descripteurs different seulement par un caractere blanc/casse/accent plutot que de les traiter
comme deux matieres distinctes. Decision d'architecture, hors de mon mandat : je route a
`sofia-sm` pour `theo-archi`.

**Sur le contenu reel livre ce sprint** : les trois fichiers `cp-qlm-*.yaml` et le fichier
`cm1-histoire-geographie.yaml` declarent aujourd'hui un descripteur identique caractere pres (verifie
par lecture directe des quatre fichiers) - le defaut est **latent**, pas actif sur le catalogue
d'aujourd'hui. Il devient actif au premier descripteur mal recopie, ce que rien dans le schema
n'empeche.

### 2. `ADR-0078` (aucun titre ne porte son niveau) - population confirmee derivee, pas figee

**Question posee** : la population de quarante titres citee dans l'ADR est-elle un chiffre en dur
ou un calcul sur le catalogue servi ?

**Lecture du code** (`AucunTitreDeJeuNePorteSonNiveauTest.java`) : aucun literal `40` n'apparait
dans `entreesServies` ni dans `verifier` - la recherche `grep -n "40"` sur ce fichier ne trouve
aucune occurrence dans le code executable, seulement dans la prose Javadoc qui date le constat.
`entreesServies` parcourt `corps.get("jeux")` du `catalogue.yaml` servi, sans aucune limite de
taille.

**Preuve par le materiel reel, pas seulement par lecture** : ce sprint a lui-meme fait grandir le
catalogue de 40 a **45** entrees (`grep -c "^\s*- id:" catalogue.yaml` -> 45 sur `fe14ea2`), en
ajoutant les cinq entrees des paquets neufs (`bandit-manchot-voyelle-du-son-cp` et les quatre
entrees de `questionner le monde`/`histoire-geographie`). Le `mvn -B clean verify` que j'ai
lance ci-dessus a fait tourner `aucunTitreDuCatalogueServiNePorteLeNiveauDeSonPaquet` contre ces
**45** entrees, en vert, sans qu'une ligne du test ait bouge depuis l'ADR. **Le
quarante-et-unieme jeu, et les quatre suivants, sont deja entres sous la garde** - ce n'est pas
une deduction, c'est ce que ce sprint vient de faire tourner.

**Verdict** : pas de defaut ici, question fermee.

### 3. Le test de "prediction" d'`ADR-0077` - deja sorti et corrige avant que je conclue

Sur `d7fab95`, j'avais reproduit exactement le defaut signale par `ines-design` : la methode
`revisionDInesDesign` (avant correctif) **ajoutait** un attribut `fill` au lieu de le **poser**,
donc appliquee a un dessin deja rempli (le cas reel une fois `ines-design` fusionnee) elle
produisait un attribut `fill="..."` duplique - markup invalide, jamais detecte parce que les trois
morsures qui s'en servaient ne regardaient que le verdict du juge, jamais le texte produit. Je
l'avais confirme par une execution reelle et instrumentee (probe ajoutee puis retiree) :

```
SONDE-YAN occurrences fill= dans APRES : 5   (pour un dessin a deux objets, donc 4 attendues)
Tests run: 1, Failures: 0
```

**Ce point est tombe pendant ma revue** : `theo-archi` a retire le test devenu sans objet
(`65d7d80`), remplace le mecanisme par une fonction **idempotente** (`sceneRemplieAvec`, qui pose
la teinte au lieu de l'ajouter), et ajoute une morsure dediee au mecanisme lui-meme
(`laMutationDeRemplissagePoseLaTeinteAuLieuDeLAjouter`) qui compare le **texte produit**, pas
seulement un verdict. J'ai relu cette classe sur `fe14ea2` (ligne 1013-1027) : le test verifie
l'idempotence (`sceneRemplieAvec` applique deux fois rend le meme texte qu'applique une fois) et
la totalite (une seconde teinte remplace la premiere plutot que de s'y ajouter). C'est exactement
la mise a l'epreuve qui manquait. Rien a rouvrir ici.

### 4. Recherche elargie : d'autres morsures de la meme famille (mecanisme de mutation jamais mordu)

Suite au signal de `theo-archi` ("les trois morsures qui l'employaient regardaient le verdict du
juge et jamais le texte produit"), j'ai cherche d'autres endroits du depot ou un **mecanisme de
mutation partage, au code non trivial**, sert plusieurs morsures sans que son propre resultat soit
jamais verifie independamment.

**Perimetre couvert** :
- Les dix fichiers de test nouveaux ou modifies par ce sprint (diff `efa5c95..fe14ea2` sur
  `*Test.java`) : aucun n'emploie de fonction de mutation bespoke comparable a
  `revisionDInesDesign` - les nouveaux paquets de contenu (`PaquetCm1HistoireGeographieTest`,
  `PaquetCpQlm*Test`, `PaquetCpVoyelleDuSonTest`) verifient du contenu reel non mute ; `ADR-0078`
  et `ADR-0079` mutent par simple concatenation de chaine ou par ajout d'enregistrement, sans
  logique de construction caractere par caractere a faire echouer.
- Les fonctions de mutation partagees d'`adapter-web` (`muter`/`muterUneSeuleFois`, `ADR-0056`/
  `ADR-0057`, utilisees par trois fichiers de garde-fou de largeur/rembourrage) : toutes delegent
  a la **meme** implementation, qui verifie **avant** de muter que l'ancre cherchee apparait
  **exactement une fois** (`assertThat(occurrences(source, motif)).isEqualTo(1)`), avec message
  nommant explicitement le risque ("a zero la morsure ne mute rien, au-dela de un elle porte sur
  autre chose que ce qu'elle prouve, sans que rien ne le dise"). Ce garde-fou-la est deja construit
  contre la famille de defaut trouvee : un mutant inerte (ancre absente) ou ambigu (ancre multiple)
  fait echouer le test immediatement, avant meme d'atteindre le garde-fou cible.
- Recherche systematique des `assertThatThrownBy(...)` sans `.hasMessageContaining(...)` dans les
  trois modules testes (204 occurrences, 20 candidats apres filtrage automatique). Sur les quatre
  qui n'attendent qu'`isInstanceOf(AssertionError.class)` sans controle de message : un
  (`CabinetDeLaMachineTest`) verifie en realite le message dans une instruction separee (variable
  `echec`, boucle de controle par selecteur) ; les trois autres (`ViewportEtLargeurTelephoneTest`
  x2, `LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest`) mutent un **fichier fixture committe**
  (contre-exemple statique relu a chaque execution), pas une construction en memoire au moment du
  test - le risque de mutant inerte y est d'une autre nature (curation de fixture, pas logique de
  mutation) et hors du perimetre precis signale par `theo-archi`.

**Conclusion de cette recherche** : je n'ai pas trouve d'autre occurrence vivante de la famille
"mecanisme de mutation partage jamais mis a l'epreuve sur son propre resultat" au-dela de celle
deja trouvee et corrigee sur `ADR-0077`. Je ne ferme pas la question pour autant - le perimetre que
j'ai couvert est le sprint courant et les mecanismes de mutation partages d'`adapter-web` ; je n'ai
pas rejoue les mecanismes de mutation plus anciens des sprints s10 a s21 un par un, ce qui
depasserait le mandat de ce rapport.

---

## Verdict sur le quatorzieme defaut

**Un defaut confirme et encore ouvert** : la cle de regroupement d'`ADR-0079` (couple matiere/
niveau non normalise) reintroduit, par fragilite de sa propre derivation, le "vert a tort" que la
regle a ete ecrite pour fermer - demontre par mutation sur le contenu reel (section 1 ci-dessus).
Latent sur le catalogue d'aujourd'hui, actif des le premier descripteur mal recopie.

**Deux points verifies sans defaut** : la population d'`ADR-0078` est bien derivee et a deja
absorbe cinq entrees neuves en vert sans changement de code (section 2). Le test de prediction
d'`ADR-0077` et son defaut de mecanisme de mutation etaient deja trouves et corriges par
`theo-archi` avant que je conclue (section 3) ; la recherche elargie a la meme famille sur le reste
du sprint et sur les mecanismes de mutation partages d'`adapter-web` n'a rien trouve de plus dans
le perimetre couvert (section 4).

## Ce que je route

- **A `sofia-sm`, pour `theo-archi`** : le defaut de la section 1 (`ADR-0079`, cle de lot non
  normalisee). Decision d'architecture (comment comparer/normaliser un descripteur declare a la
  main), hors de mon mandat.
- **Rien d'autre.** Aucun obstacle technique nouveau de mon fait sur la porte elle-meme : elle est
  verte, mesuree par moi sur `fe14ea2`, reacteur complet.

Je ne tranche ni le fond de la normalisation du descripteur (architecture), ni si le paquet
`histoire-geographie` ou `questionner le monde` est pret pedagogiquement - ces deux points restent
respectivement a `theo-archi` et a `nadia-prof`.
