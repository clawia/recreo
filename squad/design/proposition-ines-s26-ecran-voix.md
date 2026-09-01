# Proposition de forme - l'ecran de la voix adulte (s26, prealable)

- Auteure : ines-design
- Date : 2026-09-01
- Demande : sofia-sm, apres l'arbitrage prealable de nadia-prof
  (squad/conformite/ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md, branche s26-nadia, commit
  451ff2d). VOI-12 y est nommee comme sa seule exigence bloquante.
- Increment de reference : round-two a 153df5a, travail mene sur la branche s26-ines.

## 0. Ce que ce document est, et ce qu'il n'est pas

C'est une proposition de forme, prealable au meme titre que l'arbitrage de nadia-prof qu'elle
sert : aucune des quatre lignes acceptees n'est servie, aucune mecanique a voix n'existe, aucun
controleur ne fournit aujourd'hui les donnees qu'un tel ecran demanderait. Rien ici n'est du code
servi. Le vehicule est un prototype statique commite,
squad/design/prototype-ines-s26-ecran-voix.html, qui charge les deux feuilles reelles de
l'application (vendor puis maison, ADR-0014) et recopie la coquille de templates/accueil.html -
meme methode que squad/sprints/s24-trois-paquets-et-ouverture-de-la-saisie/prototype-ines-ecran-
de-saisie.html en s24.

Je ne tranche rien : ce que je propose est un candidat de forme pour nadia-prof, jamais un Done
pedagogique, et un candidat de contenu (les mots, les consignes, les phrases adressees a
l'adulte) que camille-po et nadia-prof restent seules a arreter.

Je ne tranche pas non plus la mecanique. Le cabinet du bandit-manchot (le meuble anime, ADR-0030)
n'est deliberement pas repose ici : ADR-0030 le scope au seul bandit-manchot, et nadia-prof le dit
elle-meme dans son arbitrage (section 8) - une mecanique a voix est neuve, elle n'a pas encore de
forme arretee. J'ai donc choisi la forme la plus nue possible pour la famille "choix" (boutons
.proposition sans meuble autour), pour ne rien presumer d'une decision qui revient a theo-archi et
camille-po.

## 1. Reponse a VOI-12

> VOI-12 - L'ecran dit a l'adulte ce qu'il ne doit pas faire, en une phrase, a l'endroit ou il lit
> son texte.

La reponse est un bloc unique, `.bloc-adulte`, qui porte a la fois le stimulus (ce que l'adulte
dit) et la mise en garde (ce qu'il ne doit pas faire) - jamais deux blocs separes, jamais une aide
a cote, jamais un texte derriere un clic. La mise en garde est la derniere ligne du meme cadre,
separee du stimulus par un filet, mais dans le meme objet visuel : un adulte qui lit le mot lit la
mise en garde juste apres, sans bouger les yeux d'un cadre a un autre.

```html
<div class="bloc-adulte" tabindex="-1">
    <p class="etiquette-adulte">Adulte, à voix haute :</p>
    <p class="stimulus-a-lire">vélo</p>
    <p class="consigne-adulte">Redites le mot autant de fois qu'il le demande. Ne l'épelez pas.</p>
</div>
```

Le bloc reste affiche a l'identique apres une reponse, juste comme fausse (etats "ecrire-echec" et
"identifier-reussite" du prototype) : VOI-8 exige que l'adulte puisse redire le stimulus autant de
fois que l'enfant le demande, donc il doit rester lisible tant que la manche n'est pas resolue. Il
ne disparait jamais au premier coup d'oeil.

## 2. Les trois registres qui se distinguent a l'ecran

Le prototype rend trois textes, chacun sur un objet visuel distinct - jamais la meme boite, jamais
la meme classe :

1. **Ce que l'adulte prononce** - `.stimulus-a-lire`, dans `.bloc-adulte`. 2rem, gras, sur fond
   distinct (voir section 3). C'est le plus gros texte de l'ecran avec `.consigne`, a egalite avec
   `.enonce` ailleurs dans l'application.
2. **Ce que l'enfant lit ou voit** - `.consigne` et `.propositions`/`.champ-reponse`, inchanges.
   Rien de nouveau ici : ces classes sont deja arretees par les sprints precedents (ADR-0050,
   proposition-ines-ecran-de-saisie.md) et je ne les touche pas. La distinction avec le stimulus
   vient entierement du fait que ces elements restent HORS de `.bloc-adulte`, sur fond de page nu.
3. **Ce que l'adulte ne doit pas faire** - `.consigne-adulte`, dans le meme bloc que le stimulus
   mais separee de lui par un filet (`border-top`), 1,25rem, graisse normale - donc jamais confondue
   avec le stimulus (2rem, gras) malgre le meme fond.

Un quatrieme texte existe dans l'arbitrage de nadia-prof (VOI-11, "ce que l'enfant ne doit pas
voir") mais n'a pas de forme visuelle : c'est une absence, pas un registre a dessiner. Aucun bloc
vide ne le represente (ADR-0020) - le mot travaille n'apparait simplement jamais dans `.consigne`,
`.propositions` ni `.champ-reponse`.

### 2.1 Les canaux non colores

Aucune des trois distinctions ci-dessus ne repose sur la seule couleur (mandat permanent
d'ines-design, jamais specifique a cette proposition) :

- **position** : `.bloc-adulte` est le premier bloc de la manche, avant `.consigne` - un ordre de
  lecture fixe, pas un hasard de mise en page ;
- **cadre complet** : `.bloc-adulte` a un cadre ferme sur ses quatre cotes, comme `.indice` et
  `.explication` deja dans l'application - jamais un simple liseret, pour rester lisible sans
  distinguer les couleurs (meme motif que ces deux classes, recreo.css) ;
- **etiquette en toutes lettres** : "Adulte, a voix haute :" nomme le bloc pour ce qu'il est avant
  qu'aucun signe visuel n'entre en jeu ;
- **taille et graisse** : a l'interieur du bloc, le stimulus (2rem, gras) et la mise en garde
  (1,25rem, normal) different sur deux composantes non colorees a la fois, jamais colorimetrie
  seule.

## 3. La couleur du bloc, et pourquoi une cinquieme

L'application a deja quatre couleurs de sens : bleu de l'action (`--recreo-accent`), ambre de
l'indice/attention, bleu-gris de l'explication, vert/rouge du verdict. Aucune n'etait libre pour un
"cette zone est pour l'adulte, pas pour l'enfant" sans creer une ambiguite avec un sens deja pris -
reutiliser l'ambre de `.indice` aurait fait lire le bloc comme une aide destinee a l'enfant.

```css
:root {
    --recreo-adulte-fond: #f0eaf7;
    --recreo-adulte-bordure: #5a3d8a;
}
```

Contraste mesure (methode : luminance relative WCAG, calcul reproductible) :

| Paire | Ratio |
|---|---|
| texte (#1c2733) sur fond du bloc (#f0eaf7) | 12,84:1 |
| bordure (#5a3d8a) sur fond de page (#f4f7fb) | 7,92:1 |
| bordure (#5a3d8a) sur fond du bloc (#f0eaf7) | 7,22:1 |

Tous largement au-dessus des planchers appliques partout ailleurs dans l'application (4,5:1 pour un
texte, 3:1 pour un composant non textuel).

## 4. Ce que le rendu reel donne, mesure a dix largeurs

`squad/outils/ines-design/mesurer-ecran-voix.py` (meme forme que l'outil de s24) pilote un Chrome
reel sur le prototype, aux dix largeurs deja utilisees par cette squad (360 a 1440 px, reference
produit 744 x 551, DEC-0040), sur les six etats du document. Releve commite dans
squad/design/releves-ines-s26-ecran-voix-dix-largeurs.json.

Deux verifications faites sur ce releve :

- **Aucun ecart lateral** entre `.zone-jeu` et `.entete-contenu` sur aucune des dix largeurs, sur
  aucun des six etats - c'est exactement le defaut de s05 (le rembourrage vertical d'un bloc qui
  ecrase le rembourrage lateral du conteneur sous 1200 px) et il ne se reproduit pas ici.
- **Aucun debordement lateral** de `.bloc-adulte` hors de `.zone-jeu` sur aucune largeur.

A 360 px, l'etat "localiser" (le seul a porter deux stimulus - le mot et l'unite cherchee, plus
trois boutons de choix) descend a 733 px de bas d'ecran contre 551 px de reference : la manche
demande un defilement a cette largeur. Ce n'est pas nouveau ni cache : le budget du pli est hors de
la porte pedagogique depuis DEC-0051/DEC-0053 (reconfirme le 2026-08-31), et je le signale ici pour
que le chiffre soit sur la table plutot que decouvert plus tard.

## 5. Ce qui reste hors de ma main

- **Le contenu** de chaque phrase (le mot, la consigne, la mise en garde) est un exemple, jamais
  un acquis - a camille-po et nadia-prof.
- **Le point de focus initial** d'une manche fraiche (`data-focus`, deja un contrat serveur ailleurs
  dans l'application, M13) n'est pas cable ici : je pose `tabindex="-1"` sur `.bloc-adulte` comme le
  point d'ancrage possible, mais la decision de savoir si le focus initial doit aller au bloc adulte
  ou a `.consigne` revient a theo-archi/marc-dev quand la mecanique existera.
- **La mecanique elle-meme** (boutons nus, ou un futur meuble propre a la voix) n'est pas tranchee :
  voir section 0.

## 6. Ce sur quoi j'ai un doute, et que seule nadia-prof peut lever

1. **Le risque du meme ecran partage.** VOI-11 dit que le mot travaille n'apparait "nulle part dans
   ce que l'enfant voit". Mais `.bloc-adulte` est sur le meme ecran, le meme appareil, sous les yeux
   de l'enfant assis a cote - rien dans une regle de forme ne peut empecher un enfant qui lit un peu
   de regarder cette zone plutot que d'ecouter. Je ne peux pas resoudre cela par une taille, une
   position ou une couleur : c'est une limite du medium (un seul ecran, aucune voix embarquee,
   aucune separation d'appareil), pas un defaut de ma proposition. Je le nomme parce que nadia-prof
   est seule a juger si ce risque est acceptable, au meme titre que le risque des trois conditions
   qu'elle a deja ecrit ne garder personne (section 7.1 de son arbitrage).
2. **Le poids relatif du stimulus et de la mise en garde.** J'ai fait le stimulus plus grand (2rem,
   gras) que la mise en garde (1,25rem, normale), parce que c'est ce que l'adulte cherche en premier
   a chaque manche. Mais VOI-12 est sa seule exigence bloquante, et une mise en garde visuellement
   plus discrete que le stimulus est peut-etre le mauvais choix si le risque qu'elle redoute est
   l'habitude qui s'installe (section 7.1 de son arbitrage : "trois conditions que personne ne
   garde"). Je n'ai pas de moyen de trancher ce compromis moi-meme.
3. **Le second stimulus, sur la ligne "localiser une syllabe".** J'ai rendu l'unite cherchee comme
   une seconde ligne dans `.stimulus-a-lire`, avec une etiquette en ligne ("Cherchez :"). C'est un
   candidat non joue : je n'ai aucune certitude qu'un adulte presse au petit-dejeuner distingue bien
   "le mot a dire normalement" de "l'unite a chercher, jamais isolee de sa propre voix" a la seule
   lecture de cette forme.
4. **La consigne de l'enfant sur la ligne "identifier un son" ecrit le grapheme cible** ("Entends-tu
   le son « ch » dans ce mot ?"). Sa propre section 4.2 loue cette ligne parce que la forme parlee
   "detache le son de la lettre" - et ma consigne d'exemple reintroduit une lettre ecrite, meme si ce
   n'est pas celle du mot. Je ne sais pas si cela defait le motif qu'elle donne a cette ligne ou si
   nommer le son par sa graphie dans la consigne (constante sur tout le paquet, jamais l'item) est
   sans rapport avec ce qu'elle protegeait. C'est une question de contenu, mais elle est nee d'un
   choix de forme que j'ai fait pour rendre l'exemple lisible, donc je la remonte plutot que de la
   trancher.

## 7. Fichiers de cette proposition

- `squad/design/prototype-ines-s26-ecran-voix.html` - le prototype, six etats.
- `squad/outils/ines-design/mesurer-ecran-voix.py` - l'outil de mesure, meme forme que celui de s24.
- `squad/design/releves-ines-s26-ecran-voix-dix-largeurs.json` - le releve mesure, dix largeurs, six
  etats.
