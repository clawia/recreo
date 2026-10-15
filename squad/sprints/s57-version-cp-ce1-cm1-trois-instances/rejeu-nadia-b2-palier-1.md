# s57, palier 1 - `nadia-prof`, instance B : rejeu d'`ART-CP-10` sur B2, `Q-T11`, la reduction a 720 et `Q-T12`

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-15, 17:00 (UTC+02:00), lu par `date`.
- **Arbre** : branche `s57-nadia-rejeu-b2`, worktree dedie, tete `ac9ae080` (issue de `s57-yanis-j5` : B1 dans la lignee de
  `round-two`, B2 `06c7804f`, releves `ebceb838`, juge pur corrige `ac9ae080`, annexe E de `theo-archi` `be27be58`).
- **Lu** : `engagement-poste-8.md` ; `avis-theo-poste-8-art-cp-10.md` (5, annexes B, D et E) ; `avis-faisabilite.md` 3.3 (`R1`
  a `R3`) ; `reponse-nadia-forme2-palier-1.md` ; `reponse-nadia-q-palier-1.md` ; la fiche du jeu ; `releve-b2-jar.json`,
  `coupure-b2-jar.json` et leurs scripts ; `docs/backlog/backlog.md` (`S57-TYP1-TEXTES-DE-GABARIT`) ; `squad/lessons.md`.
- **Joue** : le jar de `ac9ae080`, port 18513, dossier de poste `s57-p1-nadia-prof-b`. Methode, volume et limites : fiche,
  rejeu du 2026-10-15, section 1.

## Resume

| Code | Point | Decision | Section |
|---|---|---|---|
| R1 | Done pedagogique d'`ART-CP-10` | **VALIDE SOUS RESERVE** : refus du 2026-10-11 leve, `RDO-CP-R1` et `RDO-CP-R2` levees, reserve neuve non bloquante `RDO-CP-R3` | 1 |
| R2 | Juge pur sur `releve-b2-jar.json` | `J1` a `J5` OK, sortie 0, recopiee | 2 |
| R3 | `coupure-b2-jar.json` | lu : 0 coupure dans une phrase sur 45 mesures du texte servi ; 5 depassements, tous en Courier New, non servie | 3 |
| R4 | `Q-T11` de `theo-archi` (annexe E.6), la reduction a 720 | **Ni refus par `N6`, ni hors cible : observation sans reserve**, comme le decalage de 7,5 px | 4 |
| R5 | `Q-T11` de `camille-po` (backlog), *"Score : "* et *"Tu as écrit : "* | **Oui, la propriete vaut pour ces libelles. Aucun rejeu, prix 0** | 5 |
| R6 | `Q-T12` (a `theo-archi`) | avis pedagogique : le perimetre est ce que l'enfant lit, `th:text` compris ; le moyen n'est pas a moi | 6 |
| R7 | Deux questions portent le code `Q-T11` | signale a `sofia-sm` | 7 |

---

## 1. `ART-CP-10` : VALIDE SOUS RESERVE

Verdict, motifs, cotes et conditions : `squad/conformite/FICHE-bandit-manchot-rapprocher-deux-oeuvres-cp.md`, rejeu du
2026-10-15. En bref :

- **Motif.** A 744 x 551, a la revelation, la phrase de `M4` finit a y = 528 sur les six items, avec et sans barre de
  defilement ; la reference, les cases, les croix et l'anneau sont au-dessus du pli. La barre que j'avais ecrite est atteinte :
  le refus est leve.
- **`RDO-CP-R1` levee** : indice a 548 a 744 apres une erreur, croix a 490 a 992. **`RDO-CP-R2` levee** : 126 ecrans de fin,
  aucun texte ne designe une oeuvre absente.
- **`RDO-CP-R3`, neuve, non bloquante** : *"C'est cette œuvre qui va avec."* (262 px, insecable) deborde de sa colonne de
  18,2 px de 720 a 768 ; a 720 elle sort de l'ecran (21,2 px avec barre, ou on lit *"qui va ave"*), et une barre de
  defilement horizontale apparait a 720 et a 744 avec barre. Je tranche la question laissee ouverte en 2.3 de
  `reponse-nadia-forme2-palier-1.md` : ne pas deborder l'emporte sur l'absence de coupure dans une phrase.
- **Commit joue** : `ac9ae080`, dont le contenu servi est celui de `06c7804f` (`git diff --name-only 06c7804f ac9ae080 --
  adapters domain application bootstrap` : aucune ligne).

**`R1` a `R3` d'`avis-faisabilite.md` 3.3, ce qu'ils demandent de moi.** `R1` : ce qui sera fusionne doit etre ce que j'ai joue ;
le `git diff` de `R1` entre `ac9ae080` et le resultat de la fusion est a `sofia-sm`, et ce qu'il montrerait m'est nomme avant
la fusion. `R2` : la fiche cite `ac9ae080`. `R3` : l'identifiant de l'entree est celui d'avant le retrait,
`bandit-manchot-rapprocher-deux-oeuvres-cp` (`DEC-0120` A.65) ; ses six lignes sont identiques a celles de `13d00bfe^`.

## 2. Le juge pur, sortie recopiee

`python outils/juger-pli-deux-oeuvres.py squad/conformite/releves-s57/releve-b2-jar.json`, sur `ac9ae080` :

```text
J1 OK - completude : largeurs 720, 744, 768, 992, 1200, 6 items par largeur (paquet : 6), hauteur 551 et clientW sur 168 mesures
J2 OK - barre du refus (744, revelation, bas de m4 <= 551) : 6 items, bas de m4 max 528
J3 OK - RDO-CP-R1 (a part de J2 : reserve, pas refus) (744 indice, 992 marques, bas <= 551) : 744 bas de indice max 548 (12 mesures), 992 bas de marque max 490 (17 mesures)
J4 OK - veto de nadia-prof (messageJeu sous cabinet et dans ses bords, a 1 px pres, des 720) : 138 mesures, marge min : sous cabinet 0, a gauche 0, a droite 0
J5 OK - C1' et N6 (hauts partout ; largeur, hauteur, gauche a clientW egal ; hauteur de cabinet par groupe) : 168 mesures comparees pour les hauts, 108 comparaisons horizontales entre etats de meme clientW
    . 720, groupes d'etats de meme clientW : clientW 720 (fraiche, juste_apres_echec, juste1) ; clientW 705 (echec1, echec2, revelation)
    . 744, groupes d'etats de meme clientW : clientW 744 (fraiche, juste_apres_echec, juste1) ; clientW 729 (echec1, echec2, revelation)
    . 768, groupes d'etats de meme clientW : clientW 768 (fraiche, juste_apres_echec, juste1) ; clientW 753 (echec1, echec2, revelation)
    . 992, groupes d'etats de meme clientW : clientW 992 (fraiche) ; clientW 977 (echec1, juste_apres_echec, juste1, echec2, revelation)
    . 1200, groupes d'etats de meme clientW : clientW 1200 (fraiche, juste_apres_echec, juste1) ; clientW 1185 (echec1, echec2, revelation)
    - hors verdict (barre de defilement) : 720 cabinet.largeur : 496 puis 481 (clientW 720 puis 705, 18 mesures, 6 items)
    - hors verdict (barre de defilement) : 720 cases.hauteur : 144 puis 139 (clientW 720 puis 705, 54 mesures, 6 items)
    - hors verdict (barre de defilement) : 720 cases.largeur : 144 puis 139 (clientW 720 puis 705, 54 mesures, 6 items)
    - hors verdict (barre de defilement) : 744 cabinet.gauche : 224 puis 216.5 (clientW 744 puis 729, 18 mesures, 6 items)
    - hors verdict (barre de defilement) : 768 cabinet.gauche : 236 puis 228.5 (clientW 768 puis 753, 18 mesures, 6 items)
    - hors verdict (barre de defilement) : 992 cabinet.gauche : 228 puis 220.5 (clientW 992 puis 977, 24 mesures, 6 items)
    - hors verdict (barre de defilement) : 1200 cabinet.gauche : 242 puis 234.5 (clientW 1200 puis 1185, 18 mesures, 6 items)
VERDICT DES COTES : tout passe
```

Code de sortie : 0. **Ce que le juge ne voit pas** et que j'ai trouve en jouant : le texte de `M4` qui deborde de sa boite. `J2`
lit le bas de la boite, `J4` les bords du message ; aucun ne compare un mot a sa boite ni `scrollWidth` a `clientWidth`. Mes
cotes rendent les siennes a la meme largeur et au meme mode (fiche, section 2).

## 3. `coupure-b2-jar.json`, lu par moi

Le juge pur ne le lit pas. Le releve porte 135 mesures : cinq largeurs (720 a 1200), neuf polices, trois variantes, sur le
verdict de l'echec. **La variante `A` est le texte servi** : le script fait `v.textContent = o`, ou `o` est le `textContent`
d'origine, qui garde U+00A0 (lignes 27, 28 et 32 de `coupure-poste-8.mjs`, lues). Sur ses 45 mesures :

- texte : *"Ce n'est pas ça. / Essaie encore !"*, deux lignes, partout ;
- **coupure dans une phrase : 0** ;
- depassement du bandeau : 5, **tous en Courier New**, une par largeur. La feuille ne sert pas cette police
  (`reponse-nadia-forme2-palier-1.md` 2.1) : ce n'est pas un motif.

Le releve ne mesure ni la phrase de `M4`, ni le verdict de la revelation, ni *"Bravo !"* : je les ai mesures sur le jar
(fiche, section 5). Il est de `06c7804f` ; le texte servi n'a pas change depuis (section 1).

## 4. `Q-T11` de `theo-archi` (annexe E.6) : la reduction a 720

**Ni un refus par `N6`, ni hors cible : une observation sans reserve, du meme ordre que le decalage de 7,5 px.**

**Le fait, mesure.** A 720 x 551 avec une barre classique, apres la premiere erreur, la largeur utile passe de 720 a 705 ; les
cases passent de 144 a 139 px, le meuble de 496 a 481, la plus grande reproduction de 134 a 129 px, et la troisieme case recule
de 10 px (544 puis 534). Le haut des cases (260) ne bouge pas. **Sans barre peinte**, les cases restent a 144 a 720 apres
l'erreur : rien ne bouge. Mes cotes et celles de `lea-dev` sont les memes.

**Pourquoi pas un refus par `N6`.** `N6` dit : *"la case et l'image ne changent ni de taille ni de place quand la marque
arrive"*, et son critere 7 le lit sur les regles qui visent `deja-essayee`. Ce n'est pas la marque qui reduit les cases ; c'est
la barre verticale, qui apparait parce que 24 px de marge vide passent le pli. J'ai qualifie la meme cause le 2026-10-11 pour le
decalage de 7,5 px (fiche, section 3, point 2) : *"Ce n'est pas la marque qui les deplace"*. **En faire un refus aujourd'hui, ce
serait relever ma barre apres coup.** Un mouvement de 5 a 10 px est du meme ordre que celui que j'ai accepte : l'enfant retrouve
les trois oeuvres a leur place, la croix sous celle qu'il a pressee.

**Pourquoi pas hors cible.** Un ordinateur sous Windows a des barres classiques, et une fenetre de 720 a 734 px de large y est
possible ; `DEC-0040` le met dans la cible. Une tablette a des barres superposees : la mesure sans barre dit que rien n'y bouge.

**Ce que je ne demande pas** : le remede d'`ines-design` (`scrollbar-gutter`) touche tous les jeux ; s'il vient, c'est sa
proposition. Si un jour la reduction depasse le decalage que j'ai accepte, ou si l'image passe sous la taille ou la propriete
se voit (regle 6), j'en ferai une reserve datee.

## 5. `Q-T11` de `camille-po` (backlog, `S57-TYP1-TEXTES-DE-GABARIT`) : *"Score : "* et *"Tu as écrit : "*

**Oui, la propriete vaut pour ces libelles. Aucun rejeu, prix 0.**

**Motif.** Ce que j'ai demande pour le verdict tient a ce que l'enfant lit, pas a la source du texte : un signe double ne
commence jamais une ligne, et une ligne garde ses mots ensemble. *"Score : 3"* est lu sur chaque manche ; *"Tu as écrit : "*
est lu sur l'ecran de saisie, au moment ou l'enfant compare ce qu'il a ecrit a la bonne forme. Un *":"* seul en debut de
ligne y gene la lecture comme le *"!"* du verdict. C'est la meme demande que pour `Q-T6` (`reponse-nadia-forme2-palier-1.md`,
section 7) : un perimetre derive de la source laisse echapper la suivante.

**Ce que la propriete couvre, et ce qu'elle ne couvre pas.** Les mots que le gabarit ecrit (*"Score :"*, *"Tu as écrit :"*)
ne se separent pas de leur signe double. **Elle ne lie pas le libelle a la valeur qui le suit** (le score, ce que l'enfant a
ecrit) : la reponse de l'enfant peut etre longue, et une ligne qui deborde est pire qu'un retour a la ligne apres les
deux-points (meme arbitrage que `RDO-CP-R3`).

**Pourquoi aucun rejeu.** C'est le regime de `S57-FORME-C-VERDICT` et de `Q-T7` (`reponse-nadia-q-palier-1.md`, section 2) : le
texte lu ne change ni d'un mot ni d'un signe, seules les espaces changent, et le garde-fou de la story le tient. Je regarde ces
libelles une fois, sans prix, au premier rejeu d'un jeu de saisie qui suit la livraison. **Aucun Done pedagogique propre.**

**Ce qui n'est pas a moi** : l'orthotypographie de ces espaces (`relecteur-pedagogique`, `DEC-0002`, `DEC-0056`) ; le moyen et
la forme du test (`theo-archi`) ; la story et son palier (`camille-po`).

## 6. `Q-T12` : avis pedagogique

`Q-T12` est posee a `theo-archi` ; je ne tranche ni le perimetre technique ni la suffisance du bloc D.4. **Mon avis, pour qu'il
tranche avec** : le perimetre que je demande est **tout texte que l'enfant lit a l'ecran et que le gabarit ecrit**, qu'il soit
un noeud de texte ou un litteral de `th:text`. Un enfant ne voit pas la difference entre les deux ; un perimetre qui saute
`th:text` laisse passer *"Score :"*, lu sur chaque manche de tous les jeux.

## 7. Deux questions portent le code `Q-T11`

`avis-theo-poste-8-art-cp-10.md`, annexe E.6 (`theo-archi`, la reduction a 720), et `docs/backlog/backlog.md`,
`S57-TYP1-TEXTES-DE-GABARIT` (`camille-po`, les libelles), ecrites le meme jour. Ce sont des reperes locaux, pas des
identifiants du registre : `PAR-5` ne les garde pas. J'ai repondu aux deux (sections 4 et 5) en nommant l'auteur. A
`sofia-sm`, de dire si l'un des deux change de code.

## 8. Routage

| A qui | Quoi |
|---|---|
| `camille-po` | `ART-CP-10` a `couvert-valide` apres la fusion de B2 ; `RDO-CP-R3` en story (fiche, sections 2 et 6) ; `Q-T11` repondue (section 5) |
| `sofia-sm` | fusion de B2 sous `R1` et `R2`, commit joue `ac9ae080` ; la collision de `Q-T11` (section 7) ; tarif : rejeu 0,5, instance B |
| `theo-archi` | `Q-T11` de l'annexe E.6 repondue (section 4) ; `Q-T12`, mon avis (section 6) ; `RDO-CP-R3` touche le garde-fou de D.4 si la coupure est retenue |
| `ines-design` | la forme de `RDO-CP-R3` ; la largeur de la colonne du message a 720 - 768 |
| `yanis-qa` | ce que le juge ne voit pas (section 2) : un texte qui deborde de sa boite, et `scrollWidth` contre `clientWidth`. Je ne demande rien : c'est une donnee pour lui |

## 9. Ce que ce fichier ne fait pas

Il ne fusionne rien et ne touche ni la matrice, ni le catalogue, ni un paquet, ni du code. **Identifiant ecrit et alloue** :
`RDO-CP-R3` (fiche et registre, meme commit). Les codes `R1` a `R7` sont des reperes de ce fichier.

**Signe** : `nadia-prof`, s57, palier 1, instance B, 2026-10-15.

## 10. Jobs de `ci.yml` lances pour ce commit

Sur ce worktree, les trois fichiers du commit dans l'index, avant commit. Je ne deduis aucun job que je n'ai pas lance.

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl` : `BUILD SUCCESS`, 2597 tests (120, 31, 492, 1567,
  381 et 6 d'integration), 0 echec, 0 erreur, 0 ignore ; JDK 21.0.10 (`java.version` des 60 rapports Surefire de `bootstrap`).
  Le build a lu ces fichiers avant l'ajout de cette section, qui ne porte aucun tableau. Avant lui, le garde-fou du registre sur
  mon diff : *"declare 985 identifiants alloues et 178 prefixes distincts, sa table en porte 986 et 178"*, ligne d'etat portee a
  986 / 178, puis 10 tests, 0 echec. Etape *"Package du fat jar"* non lancee comme etape a part ; le jar joue vient du `verify`
  du meme arbre avant mes fichiers.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh` :
  sort 0.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 ; le
  sujet du commit passe dans `verifier-prefixes.sh` : sort 0. Le controle du proprietaire du garde-fou de l'accent n'est pas
  lance : aucun de ses chemins n'est touche.
- **`outils`** : les 12 juges de `outils/*-test.sh`, `squad/outils/*/*-test.sh` et `outils/*-test.py`, lances avec `python` (le
  `python3` de ce poste n'est pas un interpreteur) : 12 sortis 0.

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue.
