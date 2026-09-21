# Barre de niveaux a trois puces (F2) et sortie du libelle hors du bouton de zone (QLM-CP-11) - proposition, pas un Done

- **Auteure** : `ines-design`. **Date** : 2026-09-21. **Sprint** : s36.
- **Worktree** : `s36-ines`, ouvert depuis `s36-sofia` a `364ce0dd`, lignee verifiee
  (`outils/verifier-lignee.sh`).
- **Lots** : `F2` (planning.md, section 3, avis de `theo-archi` ; routee a `ines-design` et
  `nadia-prof`, section 4) ; `S36-QLM11-PLANCLASSE` (geometrie des six cadres, routee a
  `ines-design`).
- **Destinataires** : `nadia-prof` (jugement pedagogique des deux formes), `theo-archi` (dette
  technique nommee section 1.5 et donnee manquante section 2.6), `lea-dev` (implementation dans
  `fragments/designation.html` si la forme est retenue), `sofia-sm` (routage).
- **Ce que ce document est, et ce qu'il n'est pas** : une proposition de forme, mesuree mais pas
  jouee dans le jar reel - `mvn` n'a pas ete lance, aucun fichier de production n'est modifie par ce
  commit. Rien ici n'est acte tant que le depot ne porte pas le commit qui l'integre.

---

## Methode, commune aux deux sections

Chrome headless pilote par `outils/mesurer-rendu.sh` (`ADR-0025`), sur un **mockup HTML statique**
et non sur l'application servie - pour F2 parce qu'aucun paquet CE1 n'est charge ce sprint (la
troisieme puce n'existe dans aucune reponse du serveur a mesurer), pour QLM-CP-11 pour la meme
raison que `nadia-prof` a nommee dans son propre audit de s35 (*"mockup HTML statique... a la taille
servie"*, `FICHE-designation-plan-de-la-classe.md`, section "Audit degrade sur un point"). Le
mockup reprend **au caractere pres** les classes et la structure des gabarits reels
(`accueil.html` pour la barre, `fragments/designation.html` pour les zones), charge les **vraies**
`recreo.css` et `bootstrap.min.css` du depot (copiees telles quelles, jamais modifiees dans leur
contenu de base), et n'ajoute que le module CSS propose, en feuille separee. Chaque capture cite sa
commande dans cette section une fois pour toutes :

```
CHROME=<chemin d'un Chrome/Chromium installe, jamais ecrit en dur dans un fichier commite> \
outils/mesurer-rendu.sh <largeur> file:///<mockup>.html <png> <hauteur>
```

**Limite assumee** : un mockup statique n'est pas l'application reelle. Il reproduit le HTML/CSS
exact mais aucune donnee de controleur ; toute conclusion ci-dessous doit etre reverifiee sur
l'ecran reellement servi avant Done technique, comme `ADR-0092` l'exige deja pour toute mesure
dependant de la police de l'appareil.

---

## 1. F2 - geometrie de la barre a trois puces

### 1.1 Etat actuel (deux puces), controle

Avant de mesurer trois puces, un controle confirme que mon mockup rend l'etat deja accorde par
`nadia-prof` : a 470 px, les deux puces, le titre et "Les jeux" tiennent sur un seul rang
(`f2-temoin-2puces-470px.png`, largeur 470, hauteur 400) - conforme a `FICHE-barre-entree-niveau.md`
(remesure du 2026-08-19, "S17-A FERMEE... aucun contact"). Je ne remesure pas les quatre largeurs
pour ce controle : la question de ce lot est ce que change une troisieme puce, pas de rejuger un
etat deja ferme.

### 1.2 Etat a trois puces, sans aucun changement de gabarit ni de feuille

`recreo.css` et `accueil.html` inchanges, un troisieme `<a class="btn chip-niveau">` ("CE1") ajoute
dans le meme rang que "CP" et "CM1". Mesure aux quatre largeurs demandees, plus deux largeurs
supplementaires qui bornent le phenomene observe :

| Largeur | Fichier | Ce que je lis |
|---|---|---|
| 320 px | `f2-3puces-sans-changement-320px.png` | Rang 1 : icone, titre, **CP seul**. Rang 2 : CM1, CE1 groupes, "Les jeux" pousse a droite avec un grand vide devant lui. |
| 360 px | `f2-3puces-sans-changement-360px.png` | Rang 1 : icone, titre, CP, CM1. Rang 2 : **CE1 seul**, "Les jeux". |
| 400 px | `f2-3puces-sans-changement-400px.png` | Identique a 360 px. |
| 470 px | `f2-3puces-sans-changement-470px.png` | Rang 1 : icone, titre, **les trois puces ensemble**. Rang 2 : "Les jeux" **seul**, avec un grand vide a sa gauche. |
| 720 px | `f2-3puces-sans-changement-720px-rang-unique-retrouve.png` | **Rang unique retrouve** : tout tient sur une ligne (effet de la bande 720-767,98 px, `recreo.css` lignes 249-274, qui porte deja `.entete-contenu` a 720 px pour un autre motif, `SYN-11`). |

**Le fait qui motive cette section** : le rang unique que `nadia-prof` a valide a 470 px avec deux
puces (section 2.1) **n'existe plus a 470 px avec trois**. Il ne revient qu'a 720 px, par un effet
de bord d'une regle ecrite pour un motif sans rapport (`SYN-11`, largeur de rangee de
`cm1-synonymes`). Entre 470 px et 650 px (verifie aussi a 500, 520, 540, 576, 650 px, non joints ici
pour ne pas alourdir la preuve avec des images redondantes - le rang reste a deux du debut a la fin
de cette plage, `.container` etant fixe a 540 px sur toute la bande 576-719,98 px, `ADR-0054`), rien
ne change : le rang reste double.

**Un second fait, distinct du premier** : a 320 px, la puce **CP se separe de ses deux soeurs** -
elle reste sur le rang du titre, CM1 et CE1 partent sur le rang suivant. A 360 et 400 px, c'est
**CE1** qui se separe, cette fois du cote de "Les jeux". Dans les deux cas, une puce de niveau se
retrouve visuellement associee a un element qui n'est pas un niveau (le titre, ou la sortie) plutot
qu'a ses deux soeurs. Ce mode de repli existait deja avec deux puces a 320 px (CP se separait deja
de CM1, `FICHE-barre-entree-niveau.md` le decrit implicitement en s15) : il ne s'agit donc pas d'un
defaut neuf a cette largeur, mais son emprise s'etend avec une puce de plus.

### 1.3 Candidat propose : regrouper les puces de niveau dans un conteneur commun

**Le changement** : entourer les puces (et elles seules, pas "Les jeux") d'un `<div
class="groupe-niveaux">`, lui-meme `display: flex; flex-wrap: wrap` avec les memes `gap`/`row-gap`
que `.entete-contenu`. Aucune classe existante n'est retiree ; `.chip-niveau` garde toutes ses
regles.

```html
<!-- accueil.html, remplace les puces nues par un groupe qui replie comme un seul bloc -->
<div class="groupe-niveaux">
    <a class="btn chip-niveau" data-role="entree" href="/niveaux/CP"
       th:each="niveau : ${niveaux}"
       th:href="@{'/niveaux/' + ${niveau}}"
       th:classappend="${niveau == niveauActif} ? 'est-actif' : ''"
       th:text="${niveau}">CP</a>
</div>
```

```css
/* recreo.css, a cote de .entete-contenu */
.groupe-niveaux {
    display: flex;
    flex-wrap: wrap;
    gap: 1rem;
    row-gap: 0.5rem;
}
```

**Mesure aux quatre largeurs demandees** :

| Largeur | Fichier | Ce que je lis |
|---|---|---|
| 320 px | `f2-3puces-groupe-niveaux-320px.png` | **Trois rangs** : titre seul, les trois puces groupees, "Les jeux" seul. |
| 360 px | `f2-3puces-groupe-niveaux-360px.png` | Deux rangs : titre seul, les trois puces **et** "Les jeux" ensemble. |
| 400 px | `f2-3puces-groupe-niveaux-400px.png` | **Rang unique** : titre, les trois puces, "Les jeux". |
| 470 px | `f2-3puces-groupe-niveaux-470px.png` | Rang unique, confirme. |

**Ce que ce candidat corrige** : plus aucune puce isolee de ses deux soeurs a aucune des largeurs
mesurees - les trois avancent et reculent comme un seul bloc. **Ce qu'il coute a 320 px** : un
troisieme rang la ou l'etat actuel n'en a que deux, donc un budget de pli plus haut de la hauteur
d'un rang (le meme type de cout que `S15-7` a deja mesure et accepte pour un autre changement du
meme en-tete, ~16 a 24 px selon la police). **Ce qu'il gagne entre 360 et 470 px** : le rang unique,
perdu sans ce changement des 470 px, y est **conserve**.

**Ce que je ne peux pas expliquer avec certitude, et que je nomme plutot que de le taire.** Le
rang unique revient au candidat propose des 400 px, alors que sans changement il ne revenait qu'a
720 px - un ecart de 320 px pour un total de contenu et un budget de `gap` identiques (verifie a la
main : cinq espacements de 1rem au total dans les deux cas, groupes ou non). Mon hypothese, a
verifier par `theo-archi` plutot qu'affirmee par moi : un conteneur flex imbrique et lui-meme
`flex-wrap: wrap` contribue a l'algorithme de repli du conteneur parent par une taille de type
"fit-content" (bornee par son propre contenu minimal, la puce la plus large, plutot que par la
somme de ses trois enfants), ce qu'un CSS Flexbox permet mais que je n'ai pas verifie dans la
specification. **Cette mesure est reelle - c'est une vraie capture d'ecran, pas une deduction - mais
le mecanisme qui la produit reste a confirmer sur le gabarit reellement rendu par Thymeleaf avant
d'etre tenu pour acquis**, une feuille de plus s'ajoutant toujours a la liste des choses qui peuvent
differer entre mon mockup et l'application servie.

### 1.4 Ce que je ne tranche pas

- **Le choix entre accepter le repli a deux/trois rangs tel quel (aucun changement de gabarit) et le
  candidat groupe** reste a `nadia-prof` : c'est un jugement de forme, pas une mesure. Mon inclination
  est nommee (section 1.3, le regroupement visuel me semble le bon signal pour un enfant qui doit
  lire les trois puces comme une meme famille de boutons), mais ce n'est pas mon jugement a
  prononcer.
- **La confirmation du mecanisme de repli imbrique** sur le gabarit Thymeleaf reel, avant tout Done
  technique, reste a `theo-archi`/`lea-dev`.
- **Aucun fichier de production n'est modifie par ce commit** : `accueil.html` et `recreo.css`
  restent tels quels. Les deux extraits ci-dessus sont a copier par qui implemente, pas deja
  ecrits dans le depot.

---

## 2. `S36-QLM11-PLANCLASSE` - sortir le libelle de la boite du bouton

### 2.1 Rappel du probleme, sans le rederiver (`ADR-0093`, `ZON-15`)

`nadia-prof` accepte que le libelle sorte de la boite du bouton, sous deux conditions : que
l'enfant sache sans ambiguite quel bouton porte quel libelle, et que le bouton garde son nom
accessible. `ADR-0093` resout ce second point cote garde-fou (texte, sinon `aria-label`, sinon
`aria-labelledby`, contenant le libelle declare). Cette section propose **la forme**, qu'`ADR-0093`
nomme explicitement hors de son propre perimetre.

### 2.2 Forme retenue : un numero dans le bouton, une legende sous le dessin, le libelle complet dans les deux

**Le bouton** ne porte plus le texte du libelle. Il porte :

- un **numero de repere** (1 a 6, dans l'ordre du paquet), genere par un compteur CSS et non par du
  texte HTML - donc jamais lu par `bouton.text()` (`ADR-0093` clause 1), donc sans effet sur la
  resolution du nom accessible ;
- un attribut `aria-label` egal au libelle que le paquet declare pour cette zone - **le nom
  accessible resolu par `ADR-0093` est alors le libelle lui-meme, exactement**, pas seulement une
  chaine qui le contient.

**Une legende**, sous le dessin, liste les six libelles complets dans le meme ordre, numerotee de la
meme facon.

```html
<!-- fragments/designation.html, forme proposee - remplace le contenu du bouton de zone -->
<button type="submit" class="btn zone" data-role="action"
        th:each="zone : ${zones}"
        th:classappend="${zonesEssayees.contains(zone.id())} ? 'deja-essayee' : ''"
        th:attr="data-focus-cible=${focusCibles.contains(zone.id())},
                 aria-label=${zone.libelle()},
                 style=${'left:' + zone.cadre().x() + '%; top:' + zone.cadre().y() + '%; width:' + zone.cadre().largeur() + '%; height:' + zone.cadre().hauteur() + '%;'}"
        name="zoneDesignee" th:value="${zone.id()}"></button>
<!-- ... apres </form> : -->
<ol class="legende-zones">
    <li th:each="zone : ${zones}" th:text="${zone.libelle()}"></li>
</ol>
```

```css
/* recreo.css - modificateur du mecanisme, jamais applique par defaut : sans cette classe posee sur
   .illustration-designation, .zone garde son rendu actuel (texte dans la boite), et aucun ecran
   deja accorde par nadia-prof (frise-journee-ecole) n'est touche. */
.illustration-designation.legende-externe .zones-designables {
    counter-reset: numero-zone;
}
.illustration-designation.legende-externe .zone {
    counter-increment: numero-zone;
}
.illustration-designation.legende-externe .zone::before {
    content: counter(numero-zone);
}
.legende-zones {
    margin: 0.75rem 0 0;
    padding-left: 1.5rem;
    font-size: 1.25rem;
}
.legende-zones li {
    margin-bottom: 0.25rem;
}
```

**Pourquoi une classe modificatrice (`.legende-externe`) et non un changement du rendu par defaut**
de `.zone`. `fragments/designation.html` est le gabarit **de la mecanique**, partage par tous les
paquets de designation, dont `cp-designation-frise-de-la-journee` deja accorde par `nadia-prof` avec
un libelle **dans** la boite. Changer le rendu par defaut retoucherait un ecran deja valide sans
arbitrage prealable (`squad/README.md`, section 10) - exactement ce que mon propre mandat interdit
de faire de mon propre chef. La classe modificatrice garde la mecanique generique : elle s'applique
a n'importe quel paquet de designation dont les cadres sont trop etroits, pas seulement a
`cp-plan-de-la-classe`.

**Pourquoi le libelle complet vit a deux endroits (`aria-label` et legende) plutot qu'un seul,
relie par `aria-labelledby`.** Les deux sont deja rendus depuis la meme donnee de contenu
(`zone.libelle()`), donc aucune duplication redactionnelle ; et `aria-label` seul suffit a
`ADR-0093` sans dependre de la resolution multi-identifiants de la clause `aria-labelledby`, plus
fragile (un identifiant qui ne resout pas rend le bouton entierement muet).

**Taille du numero et de la legende** : le numero herite `font-size: 1.25rem` de `.zone`, inchange -
le plancher de `ADR-0050` reste tenu. La legende est fixee au meme plancher (`1,25rem`), pas en
dessous : c'est desormais elle qui porte la lecture principale (le libelle complet), donc elle ne
peut pas etre plus petite que le numero du bouton qui la designe (mandat "Tailles").

### 2.3 Ce que cette forme change a la geometrie des six cadres : rien, et voici pourquoi

Le motif du refus de s35 etait un deficit de **92 px** entre ce qu'un libelle replie sur deux
lignes demande (109 x 88 px) et ce que le plus etroit des six cadres offre (16,8 px de boite de
contenu, zone `fenetre`). **Un numero d'un seul chiffre ne replie jamais sur deux lignes** : sa
largeur necessaire est celle d'un seul glyphe a 1,25rem (de l'ordre de 12 a 15 px selon la police),
tres en-dessous des 16,8 px deja disponibles sur la zone la plus etroite. Le plancher de cible
tactile (`min-height`/`min-width` a 2,75rem, soit 44 px, garde-fou 3 d'`ADR-0080`) continue de
s'appliquer identiquement, independamment de cette proposition.

**Consequence directe : les deux cadres agrandis a s35 (`armoire`, `porte`) n'ont plus besoin de
l'etre pour cette raison.** Je ne propose pas de les retrecir : l'agrandissement n'est pas nuisible
(il n'a viole aucune regle et reste dans la borne de `ADR-0017`), et le remettre a sa geometrie de
s34 serait un changement non demande sur un dessin deja verifie par ailleurs (`ZON-12`, `ZON-13`).
Je le nomme pour que `theo-archi`/`lea-dev` sachent que **les six cadres actuels de
`plan-classe.svg` et de `cp-plan-de-la-classe.yaml` restent valables tels quels** avec cette forme -
aucune modification de cadre n'est requise dans ce lot.

### 2.4 Rendu mesure

Mockup reprenant la structure de `fragments/designation.html` (modificateur `.legende-externe`
pose), les six cadres actuels de `cp-plan-de-la-classe.yaml`, le fichier `plan-classe.svg` du
depot tel quel, a la taille servie (352 x 352 px, `.image-designation`) :

- `qlm11-libelle-externe-360px.png` (largeur de viewport 360 px, la largeur de reference du depot) :
  les six numeros tiennent chacun dans leur cadre, **aucun ne deborde, aucun ne touche un cadre
  voisin, aucun ne recouvre un trait du dessin au-dela de la boite translucide deja existante** -
  meme sur `tableau`, le cadre le plus contraint en hauteur (24,8 px de boite de contenu contre 120
  px demandes par le libelle complet a s35). La legende liste les six libelles complets juste sous
  le dessin.
- `qlm11-libelle-externe-992px.png` (largeur de viewport 992 px) : identique au pixel pres pour le
  dessin (`.image-designation` plafonne a 352 px quelle que soit la largeur du conteneur), la
  legende s'etale simplement sur une page plus large.

**Ce que je ne peux pas crediter comme mesure de `ZON-15` a elle seule** : `nadia-prof` reste la
seule a juger si, a l'ecran reel et avec la police de l'appareil de l'enfant, un enfant associe sans
ambiguite le numero 4 sur le dessin a la quatrieme ligne de la legende (la moitie de `ZON-15` que
`ADR-0093` nomme deja non encodable, section "Ce que cet `ADR` refuse d'affirmer"). Ce que ma
mesure etablit est la moitie geometrique : plus aucun debordement ni recouvrement nouveau, sur les
six zones, aux deux largeurs mesurees.

### 2.5 Donnee manquante a router - je ne l'ecris pas moi-meme

La classe `.legende-externe` et le bloc `<ol class="legende-zones">` ne doivent apparaitre que pour
les paquets dont les cadres sont trop etroits pour un libelle en boite (`cp-plan-de-la-classe`
aujourd'hui, un paquet futur potentiellement demain) - jamais pour `cp-designation-frise-de-la-
journee`, deja accorde avec un libelle dans la boite. Cette distinction ne peut pas se lire dans le
gabarit lui-meme sans connaitre le contenu (`illustration == 'plan-classe'` coderait une mecanique
generique pour un contenu particulier, exactement ce que mon mandat interdit). **Il manque donc une
donnee au modele que le controleur transmet a `fragments/designation.html`** - un booleen ou une
classe CSS calculee en amont, du meme ordre que ce qu'`ADR-0092` nomme deja
(`PAQUETS_DE_DESIGNATION_AUDITES_A_L_OEIL`, un ensemble de paquets nommes cote Java). Je ne l'ecris
pas : c'est une donnee de controleur, hors de mon mandat (CSS/gabarits), a router vers `lea-dev`/
`theo-archi`.

### 2.6 Ce que je ne tranche pas

- **Que cette forme (numero + legende + `aria-label`) soit la sortie retenue plutot que l'autre
  sortie acceptee par `nadia-prof`** (reduire le pave de `.zone`, deja borne a 4,8 px par `ADR-0093`
  section 2 du contexte, donc insuffisante seule) : `nadia-prof` juge.
- **La position de la legende** (sous le dessin, ici) contre une autre disposition (a cote) : j'ai
  choisi "sous" parce que c'est la seule qui reste correcte a toutes les largeurs mesurees par ce
  depot sans regle de repli neuve (`ADR-0028` interdit de garder un repli par un automate) ; une
  disposition "a cote" demanderait une largeur de conteneur que le depot ne sert qu'a partir de
  720 px et casserait sous ce plancher. Je le nomme comme un choix, pas comme la seule forme
  possible.
- **`ZON-15`, sa moitie non geometrique**, reste entierement a `nadia-prof`, a l'oeil, sur l'ecran
  servi (`ADR-0093`, section "Ce que cet `ADR` refuse d'affirmer").

---

## Fichiers de ce lot

- Ce document.
- `squad/conformite/releves-s36/f2-*.png` (dix captures, F2, methode section "Methode" ci-dessus).
- `squad/conformite/releves-s36/qlm11-libelle-externe-*.png` (deux captures, QLM-CP-11).
- **Aucun fichier de production n'est modifie** : `accueil.html`, `recreo.css` et
  `fragments/designation.html` restent inchanges par ce commit. Les extraits de code des sections
  1.3 et 2.2 sont a integrer par qui implemente la forme retenue.
