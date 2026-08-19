# Relecture du retard de s15 - relecteur-pedagogique, s16

- **Auteur** : relecteur-pedagogique. **Date** : 2026-08-19.
- **Motif** : `sofia-sm` a declare le rapport qualite de s15 perime (son objet a disparu) mais a
  ecrit que la relecture, elle, ne perime pas ("une convention de redaction n'expire pas") -
  `squad/sprints/s16-deux-defauts-et-dettes-de-len-tete/planning.md`, section "Ce que je decide sur
  les deux documents encore dus de la cloture de s15". Ce fichier est cette relecture, jamais faite.
- **Perimetre relu** : tout ce que s15 a produit - `squad/sprints/s15-cinq-arbitrages-de-l-en-tete/`
  (douze fichiers), `squad/decisions/produit/DEC-0034` a `DEC-0037` et les notes ajoutees a
  `DEC-0004`, `DEC-0027`, `DEC-0033`, `DEC-index.md`, `squad/decisions/adr/ADR-0044`, `ADR-0046`,
  `ADR-0047`, `ADR-0048`, les trois amendements d'`ADR-0022`, l'amendement d'`ADR-0014`,
  `ADR-index.md`, `squad/conformite/FICHE-barre-entree-niveau.md` (les quatre verdicts de s15),
  `squad/sprints/s16-deux-defauts-et-dettes-de-len-tete/planning.md` et `avis-faisabilite.md`,
  `squad/README.md` (deux amendements), `CLAUDE.md`, et le code livre a s15
  (`PartieController.java`, `accueil.html`, les quatre fragments, `recreo.css`,
  `LApparenceActiveNExisteQueQuandLEcranMontreLeFiltreTest`,
  `AucunEtatDePressableNEstAbandonneAuTiersTest`, `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest`,
  `EnTeteEtMenuTest`).

## Corrections directes appliquees

**Aucune.** Verifie explicitement pour chaque categorie de la grille :

| Categorie | Resultat |
|---|---|
| Orthographe et grammaire | Aucune faute trouvee dans le perimetre relu |
| Tiret cadratin, guillemet typographique | Aucun (verifie par balayage automatique des caracteres hors ASCII et hors plage accentuee francaise, sur tous les fichiers `.md`, `.java`, `.html`, `.css` du perimetre) |
| Emoji, symbole Unicode decoratif | Aucun (meme balayage) |
| Titre casse, bloc duplique, section vide | Aucun trouve |
| Chemin absolu de poste, nom d'utilisateur, credential | Aucun |
| Reference visiblement fausse (fichier ou section inexistante) | Aucune trouvee dans le perimetre de s15/s16 - la seule reference fausse connue de ce sprint (`CLAUDE.md` vers "`ADR-0045`, section Prerequis de mesure") a deja ete trouvee par `camille-po` et corrigee par `theo-archi` avant cette relecture (`CLAUDE.md`, note du 2026-08-19) |
| Chemin de sprint perime (le repertoire renomme deux fois) | Aucun renvoi restant vers `s15-la-sortie-vit-dans-l-en-tete` ou `s16-deux-defauts-de-l-increment-de-s15` en dehors des deux mentions narratives qui racontent le renommage lui-meme (`planning.md` de s15 et de s16) |
| Doublon de mot accidentel | Deux candidats trouves par balayage automatique, les deux verifies faux positifs : "n'a a s'en souvenir" (`exigences-theo-etat-actif.md`, l. 126) est "n'a **a**" au sens de "n'a **à**", ecrit sans accent selon la convention du depot, pas un doublon ; "le reste reste" (`exigences-theo-sortie-en-tete.md`, l. 186) est grammaticalement correct (nom puis verbe) |

Ce sprint est redactionnellement le plus propre que j'aie relu a ce jour sur ce depot, au sens strict
de la grille ci-dessus. Ce que j'ai trouve releve d'un autre registre - l'exactitude d'un chiffre ou
d'une affirmation contre sa source - et j'ai choisi de signaler plutot que de trancher, conformement
a mon mandat.

## Points a trancher

### 1. Le chiffre du pli des grands nombres CM1 (R-BARRE-1) est cite comme "+22 px" apres le lot qui l'a ramene a +14 px - destinataires : sofia-sm, camille-po

**Le fait, verifie dans la source primaire.** `squad/conformite/FICHE-barre-entree-niveau.md`, section
"Done pedagogique du 2026-08-18 (s15), second lot" (verdict sur l'increment `65da40e`, l. 391-399),
mesure elle-meme, avec sa methode : *"avant s14 : y = 397 ; s14 : y = 410 ; s15, avant ce lot : y =
419 ; s15, ce lot : y = 411 [...] Les 8 px reviennent, exactement. Le solde depuis avant s14 est de
**+14 px**, contre +22 px avant ce lot."* Autrement dit : le lot 3 (`b109f98`, "troisieme arbitrage")
porte le pli a +22 px, et le lot 4 (`65da40e`, "rang unique et etat actif") le **ramene** a +14 px, en
recuperant exactement les 8 px que la variante 1 d'`ines-design` avait annonces.

**Ce que trois documents de cloture et un document de perimetre affirment, a l'oppose :**

- `squad/sprints/s15-cinq-arbitrages-de-l-en-tete/planning.md`, l. 162 : *"R-BARRE-1 [...] : **+13 px**
  mesures sur `b109f98`, une aggravation de 9 px supplementaires (**+22 px** depuis s14) mesuree
  seulement dans le lot 6 non fusionne."*
- `squad/sprints/s15-cinq-arbitrages-de-l-en-tete/review.md`, l. 156 : *"R-BARRE-1 [...] : **+22 px**
  depuis s14 (etait **+13 px** au lot 3, aggrave au lot 4)"*.
- `squad/sprints/s15-cinq-arbitrages-de-l-en-tete/RAPPORT.md`, l. 65 : *"R-BARRE-1 [...] : **+22 px**
  depuis s14 [...] **chiffre definitif**"*.
- `squad/decisions/produit/DEC-0037-...md`, l. 73 : *"**R-BARRE-1** (grands nombres CM1, **+22 px** de
  pli depuis s14)."*

Ces quatre citations disent, dans cet ordre, que le lot 3 vaut +13 et que le lot 4 l'aggrave a +22 -
l'exact inverse de ce que `nadia-prof` a mesure et ecrit elle-meme sur ces deux memes lots, avec sa
methode citee dans le meme paragraphe (colonne x = 180, item "Decompose 305 421 en valeurs de
position"). Le chiffre "+22 px" repris comme **etat courant/definitif** dans `RAPPORT.md` et dans
`DEC-0037` decrit donc l'etat du lot 3, pas celui que le lot 4 - deja fusionne sur `round-two` au
moment ou ces quatre documents sont ecrits - a produit.

**Pourquoi je ne corrige pas moi-meme.** Determiner lequel des deux recits est la source de verite
demande de retracer quelle mesure `sofia-sm` a reellement prise a chaque etape (une possibilite est
qu'elle ait mesure le lot 6 non fusionne, `726e0c4`, avant sa fusion, sur un etat different de ce que
`nadia-prof` documente ensuite sur `65da40e` une fois fusionne) - c'est un travail de reconciliation
de mesures, pas une correction de redaction.

**Ce qui en depend concretement** : `DEC-0037` (s16) classe R-BARRE-1 comme *"hors chantier de
l'en-tete, non ouverte ce sprint"* avec pour seul chiffre cite le "+22 px" - si l'etat reel du depot
est +14 px, la priorite et le motif de la story candidate changent.

### 2. Un commentaire de `accueil.html` decrit un masquage retire depuis l'arbitrage C de s15 - destinataire : theo-archi

**Le fait.** `adapters/adapter-web/src/main/resources/templates/accueil.html`, l. 32-35 (commentaire
au-dessus de l'en-tete) :

> *"ADR-0022 / S6-9 : son libelle est "Les jeux", et il n'est pas visible sur un ecran de jeu - la
> feuille maison le masque des que la zone de contenu porte le marqueur "ecran-de-jeu". L'element
> reste permanent dans le document [...]"*

Ce paragraphe decrit le comportement d'avant l'arbitrage C de s15 (`DEC-0035`). Depuis ce lot, la
regle `body:has(.ecran-de-jeu) .lien-accueil { display: none }` **est retiree** et le lien "Les jeux"
**reste visible pendant une manche** - c'est l'objet meme de l'arbitrage C. `recreo.css` documente ce
changement correctement et deux fois (l. 111-120 : *"sa regle de masquage d'alors [...] est retiree
plus bas [...] la sortie d'en-tete restant desormais visible pendant une manche"* ; l. 145-156 : deux
regles neuves, `ecran-exclusif` et `ecran-porte-sa-sortie`, ni l'une ni l'autre n'etant `ecran-de-jeu`).
Les quatre fragments (`manche.html`, `retour.html`, `confirmation.html`, `erreur.html`) sont eux aussi
a jour. Seul ce paragraphe d'`accueil.html`, ecrit a une epoque anterieure (S6-9) et jamais retouche
lors de la reecriture de s15, affirme encore le contraire de ce que le code fait.

**Effet concret** : un developpeur qui lit ce commentaire pour comprendre pourquoi "Les jeux" est
visible sur une manche lira l'affirmation opposee, exactement sur l'ecran ou le comportement a
change.

**Accessoirement, meme famille** : `fragments/manche.html`, l. 13-14, dit que *"sa regle de masquage
vers .lien-accueil est **en cours de revision** par theo-archi, hors du perimetre de ce commit"* - la
revision est achevee (le meme fichier `recreo.css` le montre) ; la formulation "en cours" est perimee,
sans etre fausse sur le fond.

**Pourquoi je ne corrige pas moi-meme** : reecrire ce paragraphe suppose de decrire avec exactitude
le mecanisme actuel (deux marqueurs, deux regles, quel ecran porte lequel) - c'est un contenu
technique qui appartient a `theo-archi`, qui l'a deja redige correctement ailleurs dans le meme
commit (`recreo.css`) sans le reporter ici.

### 3. Une affirmation de `avis-faisabilite.md` (s16) sur "aucun critere n'epingle un effectif de catalogue" est contredite par le texte actuel de `backlog.md` - destinataire : theo-archi

**Le fait.** `squad/sprints/s16-deux-defauts-et-dettes-de-len-tete/avis-faisabilite.md`, section 7,
dernier paragraphe : *"Aucun critere n'epingle un effectif de catalogue cette fois - je l'ai cherche,
apres l'avoir refuse deux fois. Les onze jeux sont cites dans S16-2 comme un repere de lecture dans
la phrase du besoin, **jamais dans un critere d'acceptation**."*

Or `docs/backlog/backlog.md`, story S16-2, cite "onze jeux" **deux fois dans les criteres
d'acceptation eux-memes**, pas seulement dans le "Besoin" :

- critere 1 : *"l'adresse affichee [...] devient celle du catalogue complet, **coherente avec les
  onze jeux desormais affiches**"* ;
- critere 2 : *"le **catalogue complet des onze jeux** s'affiche a l'identique"*.

**Ce qui rend le point ambigu plutot que simplement faux** : la table de la meme section 7 note bien
que le critere 1 de S16-2 est a reecrire ("l'adresse affichee par le navigateur devient...") et sa
propre reecriture, donnee section 2, ne porte plus "onze jeux". Il est donc possible que la phrase de
la section 7 decrive l'etat **apres** sa propre reecriture plutot que le texte actuel de
`backlog.md` - mais elle est formulee comme un constat sur la story telle qu'elle existe ("je l'ai
cherche"), sans cette precision, et elle est donc lisible comme une affirmation fausse sur un fichier
que je viens de verifier.

**Pourquoi je signale plutot que de trancher** : cela touche a la lecture qu'un futur lecteur fera de
`avis-faisabilite.md` contre `backlog.md`, deux fichiers de deux agents differents ; c'est a
`theo-archi`, qui a ecrit la phrase, de la preciser ou de la confirmer.

### 4. `ADR-index.md`, section "Lecture rapide", ne couvre pas ADR-0046, ADR-0047 ni ADR-0048 - destinataire : theo-archi

**Le fait.** Le tableau principal et le tableau "Amendements" d'`ADR-index.md` portent bien
`ADR-0044`, `ADR-0046`, `ADR-0047` et `ADR-0048` (verifie ligne par ligne). La section narrative
"Lecture rapide", qui commente chaque `ADR` un par un (parfois en groupant plusieurs numeros dans une
meme entree), s'arrete a l'entree 42, qui couvre `ADR-0045`. `ADR-0044` est deja couverte par l'entree
41 ("`ADR-0043` et `ADR-0044` [...] se lisent ensemble"), mais aucune entree 43, 44 ou 45 ne
couvre `ADR-0046`, `ADR-0047` ou `ADR-0048`.

**Ce que je ne sais pas trancher** : si cette section est mise a jour a la cloture de chaque sprint
(auquel cas s16, pas encore clos, n'a pas encore recu la sienne, et `ADR-0047`/`ADR-0048` restant
`Propose` renforcent cette lecture) ou si c'est un oubli sur `ADR-0046`, deja `Accepte` depuis s15.
Je le signale plutot que de rediger moi-meme l'entree, qui suppose une synthese motivee que seul
`theo-archi` peut porter dans son propre registre.

## Reponse sur la nomenclature des conditions (BARRE, S15, R-BARRE, PORTE)

**Non, cette nomenclature n'est pas comprehensible par un developpeur junior qui arrive sans
contexte**, et je le signale a `nadia-prof` (qui nomme les conditions), `sofia-sm` et `theo-archi`
(qui la reprennent dans le process et les `ADR`), sans trancher laquelle des conventions doit
survivre - c'est un choix de process, pas une faute de redaction isolee.

**Ce que j'observe, fichier a l'appui :**

1. **Les conditions bloquantes changent de prefixe sans que le changement soit explique.**
   `squad/conformite/FICHE-barre-entree-niveau.md` numerote les conditions bloquantes du verdict de
   s14 `BARRE-1` a `BARRE-9` (prefixe = le sujet, "la barre d'entree"), puis celles du verdict de s15
   `S15-1` a `S15-9` (prefixe = le sprint). Rien dans la fiche ne dit pourquoi la suite ne continue
   pas en `BARRE-10`, `BARRE-11`... Un lecteur doit inferer la regle en comparant les dates des deux
   series.
2. **Une meme condition change de nom en cours de vie, sans alias systematique.** `BARRE-3` est
   *"annulee par arbitrage"* et sa substance transferee dans une condition neuve, `S15-6`, qui n'est
   jamais explicitement dite "successeur de BARRE-3" ailleurs que dans une phrase de commentaire
   ("Ce n'est pas une barre neuve : c'est le meme texte, deja ecrit, deja date" - section 4 du
   verdict sur `b109f98`). Un lecteur qui ne lit que `S15-6` ne sait pas qu'elle remplace `BARRE-3`
   sans lire tout le verdict.
3. **Deux conventions distinctes coexistent pour les reserves non bloquantes, sans regle ecrite qui
   dise laquelle s'applique quand.** `S14-A`, `S14-B`, `S15-C`, `S15-D` suivent un schema
   sprint+lettre ; `R-BARRE-1` a `R-BARRE-4` suivent un schema `R-`+sujet+numero (comme "reserve J"
   dans `FICHE-bandit-manchot-grands-nombres-cm1.md`, une troisieme convention, lettre seule, pour
   une autre fiche). Les deux premieres conventions coexistent pour le **meme role** (reserve non
   bloquante), au point que la meme reserve porte **les deux noms a la fois** dans plusieurs
   documents : `"S14-B / R-BARRE-3"` (`FICHE-barre-entree-niveau.md`, section 5 du verdict sur
   `b109f98` ; `DEC-0037`, section 2). Un developpeur qui cherche "R-BARRE-3" dans un document et
   "S14-B" dans un autre doit deviner que ce sont la meme chose - ce que le double-nommage rend
   possible, mais seulement pour qui a deja remarque le motif.
4. **`PORTE-1` a `PORTE-6`** (conditions de `DEC-0027`, s14) suivent une quatrieme convention
   (sujet du geste refuse, sans numero de sprint), coherente en elle-meme mais non reliee aux trois
   precedentes par aucune table de correspondance.

**Ce qui fonctionne, et que je ne veux pas passer sous silence** : chaque reference individuelle que
j'ai verifiee designe reellement quelque chose (aucune "S15-A"/"S15-B" fantome trouvee, contrairement
a ce que deux documents anterieurs a `planning.md` citaient avant que `sofia-sm` ne le corrige) et
chaque changement de nom est motive dans le paragraphe qui le fait. Le probleme n'est pas
l'exactitude point par point, que j'ai verifiee ; c'est qu'aucun document du perimetre ne porte une
table unique "quelle convention, pour quel role, depuis quand" - un futur lecteur reconstruit cette
regle en recoupant des dates, exactement le travail que cette relecture vient de faire a sa place.

## Ce qui n'entre dans aucune des cases ci-dessus

- **La fiche de conformite des grands nombres CM1** (`FICHE-bandit-manchot-grands-nombres-cm1.md`,
  qui porte formellement la reserve J = R-BARRE-1) ne contient **aucune** des mesures de pli de s14
  et de s15 (397/410/419/411 px) : ces chiffres ne vivent que dans `FICHE-barre-entree-niveau.md`,
  qui n'est pas la fiche de ce jeu. Ce n'est pas une reference fausse - personne ne pretend le
  contraire - mais un lecteur qui ouvre la fiche des grands nombres pour suivre R-BARRE-1, comme
  plusieurs documents le renvoient a faire ("story candidate, sur la fiche des grands nombres"), n'y
  trouve rien de ce sprint. Je le mentionne ici plutot que d'en faire un point separe, parce qu'il
  est de la meme famille que le point 1 (des chiffres qui ne sont pas ou on les cherche) sans porter
  en lui-meme une contradiction.
- **Aucun emoji, tiret cadratin, guillemet typographique, chemin de poste ou identifiant non-ASCII
  n'a ete trouve dans le code Java, HTML ou CSS du perimetre** (balayage automatique identique a
  celui applique aux fichiers `squad/`).
- **Le francais accentue du contenu destine a l'enfant est correct et integral** dans
  `PartieController.java` (`MESSAGE_PARTIE_PERDUE`, `MESSAGE_BRAVO`, `MESSAGE_ECHEC`) et dans les
  quatre fragments HTML - verifie caractere par caractere. Ce que les documents `squad/` (`DEC`,
  `ADR`, fiches) citent de ces memes messages **sans** leurs accents (convention constante de ce
  registre redactionnel, non celui de l'enfant) est coherent avec cette meme convention partout ou je
  l'ai verifiee.

## Recapitulatif

| Categorie | Compte |
|---|---|
| Corrections directes (orthographe/ponctuation) | 0 |
| Corrections directes (tiret cadratin/guillemet typographique) | 0 |
| Corrections directes (emoji/symbole decoratif) | 0 |
| Corrections directes (titre casse/bloc duplique/section vide) | 0 |
| Corrections directes (chemin perime/reference fausse) | 0 |
| Points signales sans correction | 4 |
| Point de lisibilite signale (nomenclature) | 1 |
