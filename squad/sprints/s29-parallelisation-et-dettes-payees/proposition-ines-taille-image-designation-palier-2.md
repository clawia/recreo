# Taille d'image du palier 2 - `.image-designation`, un plafond double et separe de `.image-item`

- **Auteure** : `ines-design`. **Date** : 2026-09-05. **Sprint** : s29.
- **Arbitrage demande par** : `ADR-0080` (section 8, quatrieme puce ; tache `T4`, dependance
  *"l'arbitrage d'`ines-design` sur la taille d'image du palier 2"*) et l'arbitrage prealable de
  `nadia-prof` du meme jour
  (`squad/conformite/ARBITRAGE-PREALABLE-s29-le-palier-2-ce-qu-un-paquet-de-designation-doit-apporter.md`,
  sections 4.5, 7.3, 8.2, 12).
- **Ce que ce document tranche** : la taille d'affichage des illustrations de la mecanique de
  designation, et la regle qui la gouverne. **Ce qu'il ne tranche pas** : si un plan, une carte,
  un planisphere, une frise ou un musee donnes sont exacts, lisibles ou pedagogiquement recevables
  - `nadia-prof` garde seule ce veto, item par item, une fois l'ecran servi (`ZON-9`, `ZON-12`).

---

## 1. Le blocage tel que je le lis, et pourquoi il n'est pas mal pose

Deux chemins independants arrivent au meme constat : a `max-height: 11rem` (176 px), une zone
carree ne peut pas descendre sous le quart de la hauteur de l'image (44 / 176 = 25 %). Un
quadrillage plafonne a 4x4, et sur un planisphere l'Europe (un continent, pas la plus petite cible
imaginable) n'atteint pas ce quart.

**Je ne conclus pas que la question est mal posee.** Les cinq cas d'usage restent une seule
mecanique (`ADR-0080` decision 6) et n'ont pas a etre traites par cinq regles de taille
differentes - ce serait recoller une geometrie par contenu la ou la mecanique refuse deja de la
connaitre (clause 10, `uneMecaniqueIgnoreLaGeometrie`). Ce qui est reellement en cause n'est pas le
contenu, c'est **ce que cette mecanique demande a une image que les deux autres ne demandent
jamais** : que l'enfant y atteigne, au doigt ou a la tabulation, une zone que le contenu place
lui-meme (`CadreDeZone`). Ni le bandit manchot ni la saisie ne posent jamais un bouton sur un
dessin. C'est une propriete de la **mecanique de designation**, pas d'une matiere : la regle que je
pose ci-dessous s'applique a elle en bloc, jamais a "une carte" ou "un quadrillage" en particulier.

## 2. Ce que je propose

Une classe neuve, **`.image-designation`**, **modificateur** de `.image-item` (posee en plus sur
le meme `<img>`, jamais a sa place) :

```css
.image-designation {
    max-height: 22rem;
}
```

`.image-item` reste seule a fixer `display`, `max-width`, `height`, `width` et `object-fit` ; ces
quatre declarations ne different jamais entre les mecaniques et n'ont donc pas a etre recopiees.
Seul le plafond de hauteur differe, et une mecanique de plus qui afficherait une image n'aurait
jamais que ce plafond a choisir.

**Pourquoi une classe separee, et pas une modification de `11rem` en place.** `.image-item` reste
le plafond du palier 1 (bandit manchot, saisie), mesure et arbitre par moi-meme au sprint 25
(`contrainte-ines-ce-qu-un-dessin-servi-doit-declarer.md`, section 1) contre les dessins qui
existent deja - un cadran a douze nombres, des pieces. Toucher `11rem` deplacerait ce plafond sous
des ecrans qui ne demandent rien de neuf et que personne n'a redemande de mesurer. La regle vaut
pour un gabarit ou une mecanique, jamais pour un contenu - ici, le gabarit qui change est celui de
la designation, pas celui du bandit manchot ou de la saisie, et la classe le dit par son nom.

**Pourquoi doubler, et pas une autre valeur.** Meme methode qu'au sprint 25 : mesurer le prix d'une
valeur candidate contre le cas le plus exigeant connu, avec marge, sans depasser ce que l'ecran
peut encore offrir. Le detail est en section 3.

## 3. La mesure

**Ce que je n'ai pas pu faire, et pourquoi** : `nadia-prof` l'a ecrit avant moi (`ZON-7`) - aucun
paquet de designation n'existe, et l'ecran de manche de designation n'est pas servi (`T4` n'est pas
livree). Je n'ai donc mesure aucun rendu reel de contenu.

**Ce que j'ai fait a la place** : un montage HTML statique, charge dans le meme moteur de rendu que
`outils/mesurer-rendu.sh` (Chrome headless, meme protocole de capture), portant des rectangles
places en pourcentage - la meme unite que `CadreDeZone` (`ADR-0080` decision 2) - pour verifier
l'arithmetique et le comportement de mise en page, jamais pour juger un dessin. Aucun de ces
rectangles n'est une donnee d'un paquet reel ; ils sont marques comme tels dans le montage.

| Cas verifie | Rapport | A 176 px (`.image-item` seul) | A 352 px (`.image-designation`) |
|---|---|---|---|
| Zone occupant 19 % de la hauteur (grandeur indicative d'un continent sur un planisphere 2:1) | 2:1 | 33 px - sous le plancher de 44 px | 67 px - au-dessus |
| Quadrillage carre, cellule au quart de la hauteur (4x4) | 1:1 | 44 px - au plancher, sans marge | 88 px - large marge |
| Quadrillage carre, cellule au huitieme de la hauteur (8x8) | 1:1 | 22 px - tres sous le plancher | 44 px - au plancher, sans marge |

**Ce que ces chiffres disent** : doubler le plafond fait passer un continent-type de sous le
plancher a au-dessus, avec une marge comparable a celle que le quadrillage avait deja a 176 px.
Il ne rend pas tout jouable pour autant - une grande ville sur un planisphere reste plus petite
qu'un continent, et ni ce document ni aucun automate ne peut trancher si son cadre, une fois
dessine, restera au-dessus du plancher : c'est exactement ce que `ZON-9` verifiera, item par item,
en jouant.

**Verification de mise en page**, aux deux largeurs de container reellement servies par ce depot
(`ADR-0054`, `ADR-0056`) - 720 px (bande 720-767,98 px) et 992 px (bande lg, 960 px de container) -
capture par `outils/mesurer-rendu.sh` sur le montage statique :

- a 992 px de viewport (container ~936 px de contenu utile), une image au rapport 2:1 plafonnee a
  352 px de haut (704 px de large) tient entierement dans la largeur offerte, sans depassement ;
- a 720 px de viewport (container ~696 px de contenu utile), la meme image serait large de 704 px
  si rien ne la contraignait : `max-width: 100%` la reduit alors a ~696 px de large, donc a
  ~348 px de haut plutot que 352 - un ecart de moins de 2 %, invisible a l'oeil et sans effet sur
  l'arithmetique de la section precedente ;
- aucune des deux captures ne montre de defilement horizontal ni de contenu tronque.

*Les deux captures et le montage source ne sont pas commites : ce sont des outils de verification,
comme le prescrit mon mandat ("un CSS qui compile n'est pas un CSS qui se voit"), pas un livrable.
Rejouables avec `outils/mesurer-rendu.sh` des qu'un montage equivalent existe.*

## 4. Ce que ce plafond deplace sur l'ecran

**Verticalement, et c'est le seul axe qui bouge** : l'illustration passe de 176 px a jusqu'a
352 px de haut, donc pousse l'enonce, les zones (boutons de designation) et le bloc `.message-jeu`
d'environ 176 px de plus vers le bas de la page. **Ce n'est pas une contrainte technique** : le pli
est sorti de la porte pedagogique depuis s21-s22 (`DEC-0051`, `DEC-0053`, reconfirme le 2026-08-31)
et reste hors de la mienne (je l'ai deja ecrit au sprint 25, section 4 de mon document cite plus
haut). Je le mesure ici seulement pour dire qu'aucun ecran ne se trouve casse par ce deplacement -
pas de defilement horizontal, pas de chevauchement - et je ne l'oppose a personne.

**Horizontalement**, la largeur suit le rapport du fichier comme aujourd'hui (`.image-item` n'est
pas touchee) : `max-width: 100%` continue de border sur la largeur du container, et le point de la
section 3 (moins de 2 % d'ecart a la bande de container la plus etroite) est le seul effet mesure.

## 5. Ce que je route

**A `theo-archi` / `sofia-sm`**, pour `T4` :

- **Le garde-fou 3 d'`ADR-0080`** (`AucuneZoneDesignableNeDescendSousLaCibleTactileTest`) doit lire
  le `max-height` de **`.image-designation`**, jamais celui de `.image-item` : la designation ne
  partage plus le plafond des deux autres mecaniques a compter de cette regle, et le garde-fou
  serait faux s'il lisait l'ancienne classe.
- **Le gabarit qui portera l'illustration de designation** doit poser les deux classes sur le meme
  `<img>` (`class="image-item image-designation"`), et non une seule : c'est ce qui permet a
  `.image-designation` de ne redeclarer que le plafond, sans dupliquer `display`, `max-width`,
  `height`, `width`, `object-fit`.
- Je n'ai pas ecrit ce gabarit moi-meme : il suppose des donnees de modele (zones, `CadreDeZone`
  positionne, verdict) que le controleur ne fournit pas encore, et c'est le perimetre de `T4`, pas
  du mien.

**A `nadia-prof`** : ce plafond est un maximum, pas une garantie. Il rend une classe de defauts
moins probable (une cible mecaniquement trop petite pour le doigt) ; il ne dit rien de l'exactitude
d'un trace, ni de savoir si une ville precise, une fois dessinee, restera au-dessus du plancher une
fois son cadre pose - ce que seul `ZON-9`, joue, peut trancher. Sur la ligne 5 (`HG-CM1-09`) en
particulier, ce plafond rend la moitie planisphere **tentable a nouveau**, il ne la rend pas
**close**.

## 6. Ce que je ne tranche pas

- L'exactitude d'un plan, d'une carte, d'un planisphere, d'une frise, d'un musee : `nadia-prof`
  (`ZON-12`).
- Si une zone donnee, une fois le dessin reel trace, reste au-dessus du plancher de 44 px :
  `nadia-prof`, en jouant (`ZON-9`).
- Le contenu de `T4` (route, gabarit reel, controleur) : `sofia-sm` / `theo-archi` / le lot qui
  livrera `T4`.
- Si le palier 2 doit s'ouvrir ce sprint, et dans quel ordre : `camille-po`, `sofia-sm`.
