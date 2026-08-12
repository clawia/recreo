# Mesure ines-design - budget vertical sous le meuble (M4), les huit jeux

Repond a la demande de `sofia-sm` suite a l'arbitrage de mecanique de `nadia-prof`
(`s10-nadia-mecanique`, tete `cc56d20`, `FICHE-bandit-manchot-additions-cp.md`). Condition **M4,
bloquante** : message, indice (M1) et revelation (M5) entierement au-dessus du pli, sur les huit
jeux. Ce document mesure, ne tranche pas : `nadia-prof` reste seule a juger la forme et le ton une
fois le lot livre.

## Lignee et methode

Worktree `s10-ines-meuble`, provisionne depuis `round-two` a `b15bda1`, verifie par
`bash outils/verifier-lignee.sh`. Aucune ligne de `recreo.css` ni de gabarit modifiee : tout ce qui
suit est mesure sur une maquette jetable, non commitee, chargeant les deux feuilles reellement
servies dans le meme ordre que l'application (`vendor/bootstrap.min.css` puis `css/recreo.css`),
avec mes regles candidates ajoutees apres, jamais dans le depot.

**Le defaut de `outils/mesurer-rendu.sh` que `theo-archi` corrige sur `s10-theo-outillage` (non
fusionnee) touche un chemin de PNG relatif** ; je contourne en fournissant systematiquement un
chemin de PNG absolu et une URL `file:///C:/...` deja convertie (le meme defaut, non corrige,
touche aussi une URL passee sous forme POSIX a ce script - je le nomme une seconde fois plutot que
de le laisser decouvrir ailleurs).

**Contenu** : les huit paquets YAML servis relus le jour meme pour en tirer l'enonce, l'indice et
la bonne reponse **les plus longs de chaque jeu** - c'est l'etat le plus charge de chaque jeu,
jamais un exemple choisi au hasard. Textes exacts de `nadia-prof` (M12) repris mot pour mot :
`"Ce n'est pas ça. Essaie encore !"`, `"C'est <reponse>. Appuie sur <reponse>."`, `"Bravo !"`.

**Ecran mesure** : consigne, enonce, le meuble complet de ma proposition precedente (corps en deux
tons, relief, visage, enseigne, boutons colores, etoiles du socle, levier de flanc - voir
`proposition-ines-meuble.md`), puis le bloc sous le meuble dans l'etat teste. Capture a 360 px de
large, a une hauteur de fenetre de 1200 px (jamais 640) pour lire une position reelle non coupee
par le pli quand un etat le depasse - c'est la seule facon de savoir de **combien** un etat manque,
pas seulement qu'il manque.

**Ce qui est degrade, et je le nomme.** Je n'ai pas joue une partie reelle jusqu'a provoquer chaque
etat en jeu (M1 a trois echecs, M5 au troisieme) : la mecanique elle-meme n'existe pas encore,
seule sa fiche existe. Ce que je mesure est la geometrie du texte le plus long de chaque paquet,
place dans le montage exact que le nouveau flux decrit. C'est une mesure de forme sur un contenu
reel, pas un audit d'increment - `nadia-prof` le reverifiera en jouant, comme elle le dit dans sa
propre section 9.

## 1. Ce que le depart de l'indice libere au-dessus du cabinet, aujourd'hui

Mesure : `.indice` tel qu'il existe aujourd'hui (fond `#fff4e5`), texte le plus long de chaque
paquet, boite isolee par sa couleur exacte ; puis difference entre le bas du cabinet **avec** cette
boite au-dessus (etat d'aujourd'hui) et **sans** elle (le cabinet remonte d'autant).

| Jeu | Hauteur de la boite `.indice` | Bas du cabinet avec indice | Bas du cabinet sans indice | Liberte gagnee |
|---|---|---|---|---|
| Calcul CP | 54 px | 467 | 402 | 65 px |
| Le bon petit mot | 144 px | 797 | 642 | 155 px |
| Le bon verbe | 144 px | 737 | 582 | 155 px |
| Le bon son | 84 px | 497 | 402 | 95 px |
| Les petites soustractions | 114 px | 527 | 402 | 125 px |
| Petits problemes | 114 px | 623 | 498 | 125 px |
| Compare les nombres | 114 px | 527 | 402 | 125 px |
| Les complements a 10 | 114 px | 527 | 402 | 125 px |

**La liberte gagnee n'est jamais egale a la seule hauteur de la boite** : elle inclut environ 11 px
de marges qui disparaissent avec elle (marge du paragraphe precedent et du paragraphe qui suit,
partiellement fondues). C'est la colonne "bas du cabinet" qui compte, pas la hauteur isolee de la
boite - je donne les deux pour que le calcul soit verifiable.

## 2. Ce qu'il faut sous le meuble, dans les quatre etats, jeux par jeu

Bloc unique (`.dessous-meuble`, meme famille visuelle que `.indice`/`.explication` : fond
`--recreo-attention-fond`, bordure `--recreo-attention-bordure`, `border-radius: 0.25rem`) portant
selon l'etat : le message seul (CM1, M2), le message et l'indice (CP), la revelation seule, la
revelation et l'explication (le-bon-son seulement, ses six items), ou la coche + "Bravo !" (reprise
de `.signal.est-juste`, 48 x 48 px, deja valide par `nadia-prof` le 2026-08-11).

**Rembourrage tel qu'il existe aujourd'hui sur `.indice`/`.explication`** (`padding: 0.75rem 1rem`,
`margin: 0.5rem 0 0`), sans aucun resserrement : ce que le bloc coute en plus du bas de cabinet
"sans indice" de la section 1.

| Jeu | Bas cabinet (sans indice) | Cout : 1er/2e echec | Cout : revelation | Cout : reussite | Marge dispo (640 - bas) | Bilan |
|---|---|---|---|---|---|---|
| Calcul CP | 402 | 101 px | 63 px | 81 px | 238 px | **TIENT** |
| Le bon petit mot | 642 | 63 px | 63 px | 81 px | **-2 px** | **NE TIENT PAS (deja negatif avant tout ajout)** |
| Le bon verbe | 582 | 63 px | 93 px | 81 px | 58 px | **NE TIENT PAS** (revelation depasse de 35 px) |
| Le bon son | 402 | 131 px | 131 px | 81 px | 238 px | TIENT |
| Les petites soustractions | 402 | 161 px | 63 px | 81 px | 238 px | TIENT |
| Petits problemes | 498 | 161 px | 63 px | 81 px | 142 px | **NE TIENT PAS** (1er/2e echec depasse de 19 px) |
| Compare les nombres | 402 | 161 px | 63 px | 81 px | 238 px | TIENT |
| Les complements a 10 | 402 | 161 px | 63 px | 81 px | 238 px | TIENT |

**Cinq jeux tiennent sans rien toucher de plus** : Calcul CP, Le bon son, Les petites soustractions,
Compare les nombres, Les complements a 10. **Trois ne tiennent pas** en l'etat : Le bon petit mot
(le plus grave), Le bon verbe (sur son seul etat de revelation), Petits problemes (sur son seul
etat de premier/deuxieme echec).

## 3. Ce que la liberte de C2 peut racheter, mesure et non suppose

`nadia-prof` laisse la consigne et l'enonce dedans ou dehors du cadre - "c'est de la forme". Je ne
les deplace pas a l'interieur du cadre (aucun gain mesure a le faire : ce serait de la meme
epaisseur de texte, seulement encadree autrement), je resserre a la place le rembourrage vertical
qui les entoure, qui est aujourd'hui la marge par defaut de Bootstrap sur un `<p>` (`1rem` par
paragraphe, jamais reduite depuis).

```css
.consigne,
.enonce {
    margin-bottom: 0.25rem;
}

.dessous-meuble {
    margin-top: 0.25rem;
    padding: 0.5rem 0.75rem;
}
```

**Remesure sur les trois jeux qui manquaient, meme methode, meme contenu le plus long :**

| Jeu | Bas cabinet (resserre) | 1er/2e echec | Revelation | Reussite | Bilan resserre |
|---|---|---|---|---|---|
| Le bon petit mot | 618 | **669** | **669** | **691** | **NE TIENT TOUJOURS PAS** (depasse de 29 a 51 px) |
| Le bon verbe | 558 | 609 | **639** | 631 | **TIENT, a 1 px pres** |
| Petits problemes | 474 | 593 | 525 | 547 | **TIENT** |

**Capture reelle a 360 x 640** (fichier joint dans la meme mesure, "Le bon verbe" en revelation,
enonce et reponse les plus longs du paquet) : le bas de la boite de revelation touche `y = 639`,
soit **un seul pixel de marge avant le pli**. Je le dis pour ce que c'est - un passage, pas un
confort - et je ne le presente pas comme une marge de securite. Un item futur dont l'enonce ou la
reponse serait ne serait-ce qu'un mot plus long ferait basculer cet etat sous le pli, sans qu'aucun
test du depot ne le voie (`ADR-0025`, aucun automate ne lit un rendu).

**Petits problemes tient desormais avec une vraie marge** (47 px sur son etat le plus juste), et
c'est le resserrement seul qui l'a rachete - aucune autre regle touchee.

**Le bon petit mot ne peut pas etre rachete par ce geste, et voici pourquoi, mesure et non
suppose.** Capture de l'enonce le plus long de ce paquet (97 caracteres) : il occupe a lui seul
**six lignes** a `2rem` de police dans la largeur du cabinet, avant meme que la consigne, le score
ou le meuble n'entrent en jeu. Au quantum de `48 px` par ligne d'enonce a `2rem` que `theo-archi`
cite deja dans `ADR-0033` (Contexte), six lignes valent **environ 288 px** a elles seules. Le
resserrement ne fait gagner que les marges entre blocs (24 a 28 px, mesure section 3) ; il ne peut
rien contre une hauteur de texte qui, a elle seule, occupe deja plus de 280 px sur un budget total
de 640. **Le cabinet "sans indice" de ce jeu passe sous les
618 px meme resserre, contre une marge disponible de 22 px avant le pli - et le moindre bloc
ajoute dessous le fait basculer.** Ce n'est pas un probleme de rembourrage ni de cadre : c'est la
longueur de la phrase elle-meme, la cause deja nommee sous `DEC-0016` depuis s07 et que ce chantier
rend bloquante plutot que de la creer.

## 4. Bilan final

| Jeu | Sans rien toucher | Avec le resserrement de C2 |
|---|---|---|
| Calcul CP | TIENT | TIENT |
| Le bon son | TIENT | TIENT |
| Les petites soustractions | TIENT | TIENT |
| Compare les nombres | TIENT | TIENT |
| Les complements a 10 | TIENT | TIENT |
| Petits problemes | ne tient pas (19 px) | **TIENT** (47 px de marge) |
| Le bon verbe | ne tient pas (35 px) | **TIENT, a 1 px pres** (fragile) |
| Le bon petit mot | ne tient pas (65 a 83 px) | **ne tient toujours pas** (29 a 51 px) |

**Sept jeux sur huit peuvent tenir** avec un seul geste de forme (le resserrement de marges
ci-dessus), applique une fois, de la meme facon sur les huit. **Un seul, "Le bon petit mot", ne
tient pas et ne peut pas etre rachete par un geste qui est de mon ressort** : sa cause est la
longueur de ses enonces les plus longs, pas la mise en page autour du meuble. C'est une information
a faire remonter a l'utilisatrice, comme demande, pas un echec de conception de ma part sur ce
jeu-la.

**Ce que je ne fais pas ici** : je ne propose pas de reduire `2rem` a une taille plus petite pour
`.enonce` sur ce paquet - ce serait toucher l'acquis 3 de `nadia-prof` ("la proposition doit peser
au moins aussi lourd que l'enonce"), une regle qui gouverne les huit jeux et huit paquets, pas un
correctif local a un seul. Je ne propose pas non plus de raccourcir les phrases : c'est un contenu,
`camille-po` et `nadia-prof` le tranchent, pas moi.

## Ce que je remonte

- **A `sofia-sm` et a l'utilisatrice** : "Le bon petit mot" ne peut pas satisfaire M4 avec la seule
  mise en page. Trois pistes existent en dehors de mon domaine, nommees sans etre choisies :
  raccourcir les enonces les plus longs de `cm1-homophones.yaml` (contenu, `nadia-prof`/`camille-po`) ;
  reduire `.enonce` a moins de `2rem` **pour ce paquet seul**, avec la reserve d'acquis que
  `nadia-prof` devra lever elle-meme ; ou accepter un defilement residuel sur les etats les plus
  longs de ce seul jeu, ce que M4 refuse aujourd'hui sans exception.
- **A `nadia-prof`** : ma proposition de resserrement (section 3) est une regle de forme, pas de
  contenu - elle reste seule a juger si "Ce n'est pas ça. Essaie encore !" a `0,5rem` de rembourrage
  au lieu de `0,75rem` reste assez respirant pour un enfant de six ans. Et le passage a 1 px de
  marge sur "Le bon verbe" (section 3) merite sa vigilance a chaque paquet ajoute a ce jeu.
- **A `theo-archi`** : rien de neuf sur le fond technique - ce document ne mesure que la forme.
  Le defaut d'URL de `mesurer-rendu.sh` (en-tete de ce document) s'ajoute a celui du PNG deja
  remonte.

Je ne fusionne pas, je ne pousse pas.
