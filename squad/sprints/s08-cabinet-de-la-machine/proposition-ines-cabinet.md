# Proposition ines-design - cabinet de la machine, fin du mouvement, distinction de l'explication

Chantier sprint 8. Je propose, je ne tranche pas : `nadia-prof` garde seule le veto pedagogique
sur ce document, au titre de l'arbitrage prealable qu'elle a rendu le 2026-08-11
(`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, bloc "Arbitrage prealable au
developpement - cabinet visible, sort du disque decoratif, fin du mouvement, distinction de
l'explication, du 2026-08-11"). Je lis ce bloc comme la source qui justifie chaque choix ci-dessous
et je le cite par ses proprietes C1 a C6 et ses sections.

## Lignee et methode de mesure

Worktree dedie provisionne depuis `round-two` a `f29a2b0` (meme base que `s08-camille-po` et
`s08-theo-archi`), verifie par `outils/verifier-lignee.sh`. Je n'ai pas fusionne `s08-camille-po`
ni `s08-nadia` dans ma branche : j'ai lu leurs commits (`e9ac1e6`, `cb4f40e`) par `git show`, sans
les amener dans mon arbre.

**Comment j'ai mesure, et sa limite exacte.** Comme `nadia-prof` le fait elle-meme (section 0 de
son arbitrage), j'ai construit une maquette jetable, non commitee, hors de toute story : une page
HTML statique chargeant les deux feuilles reellement servies (`vendor/bootstrap.min.css` puis
`css/recreo.css`, dans cet ordre) et reproduisant la structure reelle de `fragments/manche.html`
et de `fragments/retour.html`. J'ai ajoute mon CSS de proposition directement dans `recreo.css`
le temps de la mesure, capture aux largeurs 360 et 375 px avec `outils/mesurer-rendu.sh` (Chrome
designe par la variable `CHROME`), puis j'ai tout reverti avant de commiter ce document -
`git status` et `git diff --stat` sont vides sur le code a l'heure ou j'ecris ceci. Ce que cette
methode ne couvre pas : l'echange htmx reel et l'animation jouee dans un vrai navigateur pilote.
Pour la fin du mouvement (S8-2), je n'ai donc pas rejoue l'animation a l'oeil ; je la derive du
calcul exact de la regle CSS existante, verifie par simulation numerique (section correspondante).

## 1. Le cabinet : forme retenue et motif

**Forme.** Un coffret rectangulaire a coins arrondis, en trois bandes empilees, qui n'entoure que
`.propositions` (jamais la consigne, l'enonce ni l'indice, qui restent au-dessus, hors du cadre) :

1. un **bandeau haut** (2 rem / 32 px) qui porte l'unique image decorative, une etoile blanche
   centree, dessinee en CSS par `clip-path` (meme technique que la coche et la croix du signal
   juste/faux, `ADR-0027` clause 7 : `content: ""` est la seule valeur declaree) ;
2. la **fenetre** : les colonnes de reponses elles-memes, inchangees dans leur police, leur
   couleur et leur rembourrage declares ;
3. un **socle bas** (1 rem / 16 px), plein, sans image - le pied du coffret.

Le tout est cerne d'une **bordure de 0,5 rem (8 px) sur les quatre cotes**, plancher exact que
`nadia-prof` chiffre en C1 et dans son tableau de payeurs (section 3). Le corps entier (bordure,
bandeau, socle, et le fond qui se revele dans les interstices entre les colonnes) porte une seule
couleur : `var(--recreo-entete-fond)` (`#1d4b6b`), deja acquise et deja mesuree pour son contraste
(commentaire S6-10 dans `recreo.css`). Je ne cree aucune nouvelle couleur pour le cabinet lui-meme.

**Motif de la forme.** Trois choix, chacun motive par une clause de l'arbitrage :

- **Trois bandes plutot qu'une simple bordure fine**, parce que C1 exige qu'un enfant de 6 ans
  reconnaisse un objet a son pourtour et que nadia-prof ecrit elle-meme qu'"une caisse avec une
  fenetre et un bouton suffit" (section 1) : une bordure seule aurait tenu C1 au sens de la
  mesure, mais un bandeau et un socle donnent au coffret un haut et un bas, ce qu'un simple trait
  ne donne pas a un enfant qui doit "en montrer le tour du doigt".
- **Une seule couleur pour tout le corps**, y compris le fond qui apparait entre les colonnes
  (`.cabinet` porte `background-color` en plus de `border-color`), pour que le coffret se lise
  comme un seul objet plein et non comme un cadre ajoure a travers lequel on voit la page. Premiere
  version testee sans ce fond : les interstices entre colonnes laissaient voir `--recreo-fond`
  (la page), ce qui cassait la lecture d'objet unique - corrige avant la mesure finale.
- **Aucune nuance nouvelle** : reprendre `--recreo-entete-fond` evite d'introduire une couleur que
  personne n'a encore confrontee aux planchers de contraste, et rattache visuellement le cabinet a
  l'en-tete permanent de l'application plutot qu'a une identite de machine a sous (chrome, or,
  rouge) - c'est un choix deliberement pauvre en couleur, au service de C6.

**Ce que je n'ai pas fait, et pourquoi je le nomme.** Je n'ai donne aucune epaisseur au bandeau ou
au socle au-dela de ce qu'il faut pour loger une image lisible (2 rem) et un pied visible (1 rem) :
le budget vertical est serre, et grossir ces bandes sans motif nouveau consommerait de la marge
sans rien ajouter que nadia-prof m'ait demande.

### Gabarit propose (`fragments/manche.html`)

Seul changement : le `<form class="propositions">` est desormais entoure d'un `<div class="cabinet">`
portant deux freres decoratifs. Rien d'autre dans le fragment ne bouge - la consigne, l'enonce et
l'indice restent hors du cadre.

```html
<div class="cabinet">
    <div class="cabinet-bandeau" aria-hidden="true"></div>
    <form class="propositions" th:attr="hx-post=@{'/parties/' + ${jeuId} + '/coups'}" hx-target="#jeu" hx-swap="innerHTML">
        <button type="submit" class="btn proposition" data-role="action"
                th:each="proposition : ${propositions}"
                name="propositionChoisie" th:value="${proposition}" th:text="${proposition}"></button>
    </form>
    <div class="cabinet-socle" aria-hidden="true"></div>
</div>
```

`aria-hidden="true"` sur les deux bandes decoratives : elles ne portent aucune information que le
texte de l'ecran ne porte deja. Aucun autre gabarit ne change : le cabinet n'existe que dans
`fragments/manche.html`, jamais dans `fragments/retour.html` ni dans `accueil.html` - c'est cette
absence de balisage, et non une regle de masquage, qui garantit qu'il n'atteint ni l'ecran de
retour ni l'ecran de fin de partie (section 6 de l'arbitrage, acquis 6).

### Feuille de style proposee (`css/recreo.css`)

```css
/* Cabinet visible entourant les colonnes de reponses (nadia-prof, arbitrage prealable du
   2026-08-11). Un seul objet ferme sur ses quatre cotes (C1), qui ne contient que .propositions
   (C2), jamais anime (C3), jamais couple au verdict puisqu'il n'existe pas sur l'ecran de retour
   (C4), dont l'unique image est fixe (C5), et qui ne porte aucun code de jeu d'argent (C6). Le
   cadre se paye sur la largeur des colonnes : aucune marge ni rembourrage n'est ajoute ici sur
   .propositions, qui remplit toute la largeur interieure du cabinet. */
.cabinet {
    display: flex;
    flex-direction: column;
    background-color: var(--recreo-entete-fond);
    border: 0.5rem solid var(--recreo-entete-fond);
    border-radius: 0.75rem;
    overflow: hidden;
}

/* Le rembourrage vertical de .propositions (0.75rem, S3-5) reste utile hors du cabinet ; ici
   c'est le bandeau et le socle qui separent les colonnes du reste de l'ecran. */
.cabinet > .propositions {
    margin: 0;
}

/* Bandeau haut : 2 rem (32 px), porte l'unique image fixe du cabinet (C5 clause 2). */
.cabinet-bandeau {
    height: 2rem;
    display: flex;
    align-items: center;
    justify-content: center;
}

/* Etoile a cinq branches, dessinee comme la coche et la croix (ADR-0027 clause 7). */
.cabinet-bandeau::before {
    content: "";
    width: 1.25rem;
    height: 1.25rem;
    background-color: var(--recreo-accent-texte);
    clip-path: polygon(50% 0%, 61% 35%, 98% 35%, 68% 57%, 79% 91%, 50% 70%, 21% 91%, 32% 57%, 2% 35%, 39% 35%);
}

/* Socle bas : 1 rem (16 px), plein, sans image - le pied du coffret. */
.cabinet-socle {
    height: 1rem;
}
```

Aucune nouvelle variable de couleur n'est necessaire pour le cabinet : `--recreo-entete-fond` et
`--recreo-accent-texte` existent deja et sont deja mesurees.

## 2. Combien d'images, et lesquelles

**Une seule image, une etoile blanche, centree dans le bandeau, identique en toute circonstance.**
Je le tranche a une plutot qu'a plusieurs pour une raison precise, et non par pauvrete d'ambition :
C5 clause 2 est, dans les mots de `nadia-prof`, "son point de garde le plus important de ce
chantier". Une image unique, positionnee au centre du bandeau independamment du nombre de colonnes
(2, 3 ou 4), ne peut structurellement pas se lire comme "une image par colonne" ou comme un indice
associe a un item : mes captures a 2, 3 et 4 propositions montrent la meme etoile a la meme place
dans les trois cas (section 4). Un jeu de plusieurs images alignees sur le nombre de colonnes
aurait constamment expose ce risque, meme fixe dans son contenu.

Ce choix laisse une extension possible que je nomme sans la faire : un second element purement
decoratif sur le socle (par exemple deux petits demi-cercles aux coins, evoquant des pieds) ne
romprait aucune des six proprietes tant qu'il reste invariant lui aussi. Je ne l'ajoute pas parce
que la demande de l'utilisatrice est satisfaite par un seul signe reconnaissable, et qu'un
element de moins est un element de moins a auditer contre C5 et C6.

**Sur C6, verification nommee.** L'etoile n'est ni une cerise, ni une cloche, ni une couronne, ni
une barre, ni un chiffre ; le mot `BAR` et le nombre `777` n'apparaissent nulle part, ni en texte
ni en image. Aucune fente, aucune coupelle, aucun afficheur de credit, aucune manette laterale
n'est dessine.

## 3. Mesures a 360 px et 375 px, sur les trois cas

Mesures par balayage de pixels (comme `nadia-prof` le fait elle-meme), sur une image rendue par
Chrome headless a l'echelle 1:1, avec les couleurs exactes `--recreo-entete-fond` (`rgb(29,75,107)`)
et `--recreo-accent` (`rgb(46,111,158)`) comme reperes. Les trois cas sont les trois jeux reels du
catalogue (`catalogue.yaml`) ; j'ajoute un quatrieme cas synthetique (`mangeons`/`mangez`/
`mangent`/`mange`, quatre colonnes) pour verifier que le cadre reste ferme sous le repli en deux
rangees (`ADR-0026` section 4), meme si aucun jeu du catalogue n'atteint aujourd'hui quatre
propositions.

### Ce que payent les colonnes (horizontal)

| Jeu | Colonnes | Largeur de colonne a 360 px | Largeur de colonne a 375 px | Plancher de `nadia-prof` |
|---|---|---|---|---|
| Calcul CP (`10`, `7`, `9`) | 3 | **96 px** mesures | **101 px** mesures | 77 px |
| Le bon son (`an`, `on`, `in`) | 3 | **96 px** mesures | 101 px (memes colonnes que ci-dessus) | 79 px |
| Le bon petit mot (`sont`, `son`) | 2 | **152 px** mesures | **160 / 159 px** mesures | 106 px |
| Cas synthetique a 4 (repli) | 2 x 2 | **152 px** par colonne (identique au cas a 2) | non mesure | hors catalogue, hors tableau de `nadia-prof` |

Dans tous les cas, la **bordure du cabinet mesure exactement 8 px de chaque cote** et le **`gap`
entre colonnes reste exactement 16 px**, intact - je n'ai touche ni `.propositions { gap }` ni le
rembourrage de `.proposition`, conformement au tableau de payeurs (section 3 de l'arbitrage) : seul
`.cabinet > .propositions { margin: 0 }` et la largeur disponible pour les colonnes changent. A
360 px, 96 px et 152 px sont exactement les valeurs que `nadia-prof` calcule elle-meme dans son
arbitrage (`(336 - 16 - 32) / 3 = 96`, `(336 - 16 - 16) / 2 = 152`) : ma mesure confirme son calcul
au pixel pres, pas seulement en theorie.

**Aucune troncature, aucune reduction de police, aucun mot coupe** dans les trois cas reels, y
compris `sont` (le mot le plus large du catalogue par la mesure, pas par le compte de lettres -
voir la mise en garde de `nadia-prof` sur mon propre defaut de s07). Le cas synthetique a 4
colonnes reste **illisible** (`mangeons` deborde deja sa colonne) - c'est le defaut deja connu et
nomme hors de portee de ce chantier ("Le bon verbe" est hors du tableau de planchers de
`nadia-prof") ; le cabinet ne l'aggrave pas et ne le corrige pas, il n'y touche pas.

### Ce que payent les colonnes (vertical) - surcharge du cabinet

Mesure sur le cas le plus charge du catalogue, "Le bon son" avec trois echecs consecutifs et
l'indice affiche (item `JARDIN`) :

| Zone | Hauteur mesuree |
|---|---|
| Bordure haute | 8 px |
| Bandeau (image) | 31-32 px |
| Rangee de colonnes | 74 px (inchangee) |
| Socle | 16 px |
| Bordure basse | 8 px |
| **Surcharge totale ajoutee par le cabinet** (hors rangee de colonnes, deja existante) | **63 px** |

`nadia-prof` fixe une limite dure a 150 px et conseille de rester sous 100 px. **63 px** est bien
en dessous des deux : il reste **87 px de marge sous la limite dure**, et **37 px sous sa cible**.
Cette marge n'est pas consommee par un decor supplementaire : elle reste disponible si un paquet
futur allonge son enonce ou son indice, ce qui n'est pas de mon ressort mais que je note pour que
personne ne croie le budget entierement epuise par ce chantier.

### Fermeture du contour (C1) sur les trois cas et le cas synthetique

Sur les cinq captures (2, 3, 4 colonnes a 360 px ; 2 et 3 colonnes a 375 px), le contour est ferme
et continu sur les quatre cotes, d'une epaisseur de 8 px partout, sans qu'aucun cote touche le bord
de l'ecran (l'espace de 12 px du rembourrage de `.container` reste visible de part et d'autre, page
blanche) ni ne soit rogne par le pli dans le cas le plus charge mesure ci-dessus.

## 4. La fin du mouvement (S8-2) : cause exacte et correction

**Cause, en une formule.** La regle actuelle interpole `top` de 0 % a 80 % et `bottom` de 80 % a
0 % **au meme rythme** : `hauteur visible = 100% - top - bottom` reste **constante a 20 %** du
debut a la fin de l'animation - le voile ne retrecit jamais, il se contente de glisser du haut vers
le bas en gardant toujours 20 % de hauteur (14,8 px sur un bouton de 74 px). C'est exactement le
chiffre que `nadia-prof` mesure et declare en section 0 bis point B : l'animation "rend la main"
alors qu'un cinquieme de la boite est encore sombre, et l'etat de repos (0 % de hauteur visible)
survient alors en un seul saut au lieu d'une transition.

**Correction proposee : une seule valeur a changer.** Remplacer la valeur d'arrivee de `top`
(portee par `inset(80% 0 0 0)`) par `100%`, sans toucher a `bottom` :

```css
@keyframes rouleau-glisse {
    from { clip-path: inset(0 0 80% 0); }
    to   { clip-path: inset(100% 0 0 0); }
}
```

Avec ce changement, `hauteur visible(t) = 20% x (1 - p(t))`, ou `p(t)` est la progression apres
easing (`ease-out`, inchangee) : elle vaut 20 % au debut et **0 % exactement a la fin**, de facon
continue entre les deux. Je ne touche a aucune autre valeur : duree (0,6 s), courbe (`ease-out`),
decalage cumulatif (0,08 s par colonne), un seul passage - tout reste dans les bornes du
2026-08-10, comme `nadia-prof` le demande en section 5 et en section 8 (sa latitude me laisse "la
facon de rendre la fin du mouvement continue", pas la duree ni la courbe).

**Verification des trois mesures qu'elle exige, par simulation numerique de la courbe `ease-out`
reelle (`cubic-bezier(0, 0, 0.58, 1)`) sur 600 ms, pas par capture d'un navigateur reel :**

1. **Hauteur de voile sur la derniere image : 0 px.** `p(600ms) = 1`, donc hauteur visible = 0 -
   exact, non arrondi.
2. **Derniere image et etat de repos identiques.** L'etat de repos est `inset(0 0 100% 0)`
   (hauteur visible 0 %, rectangle degenere en haut de la boite) ; la derniere image de l'animation
   est `inset(100% 0 0 0)` (hauteur visible 0 %, rectangle degenere en bas de la boite). Les deux
   sont des rectangles de hauteur nulle : ils ne peignent aucun pixel, donc rendent la meme image a
   l'ecran quelle que soit leur position declaree.
3. **Decroissance progressive sur les 150 dernieres ms.** J'ai simule `hauteur_visible(t)` pour
   `t` de 450 a 600 ms avec un pas de 40 ms (la granularite que `nadia-prof` cite pour sa propre
   reverification) : la plus grande baisse entre deux echantillons consecutifs est de **0,60 px**,
   tres en dessous de son plafond de 3 px. A des pas plus fins (20, 10, 5, 1 ms), la baisse
   maximale ne fait que diminuer (0,32 px, 0,16 px, 0,08 px, 0,02 px) : la propriete tient quelle
   que soit la finesse de l'echantillonnage utilise pour la reverifier.

**Ce que je n'ai pas verifie, et que je nomme.** Cette derivation est analytique : elle repose sur
la formule d'interpolation lineaire de `clip-path: inset()` que le navigateur applique par
composante avant d'appliquer la fonction de temporisation, et sur la fonction `ease-out` standard.
Je n'ai pas rejoue l'animation reelle dans un navigateur pilote pour la chronometrer image par
image - c'est ce que `nadia-prof` fera elle-meme (section 10, point 9, "image par image a 40 ms ou
mieux") avant tout Done pedagogique, et je recommande a `lea-dev` ou `theo-archi` de le confirmer
une fois la regle implementee, plutot que de se fier a mon seul calcul.

## 5. La distinction de l'explication (S8-3)

**Solution : le meme motif que `.indice`**, que `nadia-prof` cite elle-meme comme reference
("meme motif que pour `.indice`, ou j'ai deja refuse le liseret au profit du cadre complet") - un
aplat de fond et un cadre complet, jamais un simple liseret lateral. Je ne reutilise pas les
couleurs de `.indice` (ambre, deja chargee du sens "tu as besoin d'aide"), pour ne pas superposer
deux significations sur la meme couleur : j'introduis une paire neutre, distincte du vert et du
rouge du verdict, et distincte de l'ambre de l'indice.

```css
:root {
    /* ... variables existantes inchangees ... */
    --recreo-explication-fond: #eaf1f7;
    --recreo-explication-bordure: #5b7c94;
}

.explication {
    font-size: 1.25rem;
    color: var(--recreo-texte);
    background-color: var(--recreo-explication-fond);
    border: 0.125rem solid var(--recreo-explication-bordure);
    border-radius: 0.25rem;
    padding: 0.75rem 1rem;
    margin: 0.5rem 0;
}
```

`--recreo-explication-bordure` (`#5b7c94`) est une teinte assourdie de `--recreo-accent`
(`#2e6f9e`) : elle reste dans la famille de couleurs de l'application sans reprendre exactement le
bleu des boutons d'action, pour ne pas laisser croire que l'explication est elle-meme pressable.

**Verification des cinq proprietes, sur mesure reelle :**

1. **Signal non textuel, cadre complet.** Aplat + bordure de 0,125 rem sur les quatre cotes,
   meme epaisseur et meme rayon que `.indice` - jamais un liseret d'un seul cote.
2. **Neutre, identique apres juste et apres faux.** Aucune regle ne conditionne
   `.explication` a `.signal.est-juste` ou `.signal.est-faux` : la couleur est la meme par
   construction, pas par coincidence. Verifie par capture : sur l'ecran de retour apres un coup
   juste et apres un coup faux (meme item, meme explication), la difference de pixels entre les
   deux zones d'explication est **nulle** (`bbox` de la difference vide).
3. **L'explication reste apres le verdict.** Inchange dans `fragments/retour.html` : le bloc
   `.signal` est rendu avant le paragraphe `.explication` dans le document.
4. **Le verdict ne perd rien.** Je n'ai touche ni `.signal`, ni `.icone-signal` (toujours 3 rem),
   ni leurs couleurs de fond `est-juste` / `est-faux`.
5. **1,25 rem tenu, tout au-dessus du pli.** `.explication` garde `font-size: 1.25rem`, egal a
   `.consigne` comme avant. Mesure sur le cas le plus charge (explication sur deux lignes, "Dans
   NOMBRE, ce sont les lettres o et m qui font le son on.") : le nouvel aplat ajoute exactement
   **28 px** de hauteur (24 px de rembourrage vertical + 4 px de bordure) par rapport a la version
   sans cadre ; le bas du bouton "Encore !" passe de `y = 336` a `y = 364` px sur ma capture a
   360 px de large - **276 px de marge restent sous le pli** (640 px), tres au-dessus de zero.

## 6. Points que je remonte plutot que de trancher

- **Anneau de focus clavier sur les boutons de reponse.** `.cabinet` porte `overflow: hidden` pour
  que ses coins arrondis decoupent proprement le bandeau et le socle. Si un bouton de reponse se
  trouve exactement au bord du cabinet (colonne la plus a gauche ou la plus a droite), l'anneau de
  focus par defaut du navigateur pourrait etre partiellement rogne par ce `overflow: hidden`. Je
  n'ai pas verifie ce point au clavier dans un navigateur reel - ma methode de mesure (captures
  statiques) ne le montre pas. Je le nomme pour que `lea-dev` ou `yanis-qa` le verifie a
  l'implementation, plutot que de le laisser decouvrir plus tard.
- **Le sort du disque decoratif** (section 4 de l'arbitrage) n'est pas dans ce document : c'est
  tranche, il n'est pas a reinstaller, et sa mise en oeuvre revient a `theo-archi` / `lea-dev`. Je
  n'ai pas touche `.demarrer::before` ni `.encore::before`.
- **Tests structurels a mettre a jour dans le meme commit que le code**, si `lea-dev` reprend cette
  proposition telle quelle : `TailleDesTextesEtBoutonsTest`, `LActionNeDeclarePasMoinsQueLaSortieTest`
  et le test du repli a quatre colonnes lisent des selecteurs (`.propositions`, `.proposition`,
  boutons par role) et non l'ancetre DOM, donc mon ajout de `.cabinet` ne devrait rien casser parmi
  ceux que j'ai lus - mais je ne les ai pas fait tourner (pas de `mvn verify` execute depuis ce
  worktree, JDK 21 non invoque), donc je ne le declare pas comme une certitude.
- **Si `nadia-prof` souhaite davantage de decor** que la seule etoile (un second motif sur le
  socle, par exemple), je nomme cette latitude en section 2 mais je ne la prends pas par defaut :
  c'est un choix de gout qui reste le sien a demander, pas un manque de ma part.

## Ce que je n'ai pas touche

`docs/backlog/backlog.md`, `squad/conformite/`, tout `ADR`, et aucun fichier sous `domain/`,
`application/` ou `bootstrap/`. Le CSS et le gabarit ci-dessus sont des propositions ecrites dans
ce document, pas des modifications commitees dans `adapters/adapter-web/` : `git status` sur ce
worktree ne montre que ce fichier.
