# Glyphes 7, 8 et 9 (ines-design, proposition soumise a nadia-prof)

Dette `Q7` d'`avis-theo-palier-5-dessins.md` (section Questions) et condition du palier 6 de
`arbitrage-nadia-palier-5-lp7-d5.md` : `MAT-CE1-02` demande les chiffres 7, 8 et 9. Cette
proposition n'est pas un Done ; le veto pedagogique est a `nadia-prof`.

## Definition

Meme famille que `g0`, `g1`, `g2` et `g5` de `monnaie-en-tout-a.svg` : boite de 40 x 60 unites
(traits centres de x 4 a 36, de y 2 a 58), trait de 8, bouts et jonctions arrondis, instanciation
par `<use>` en `translate(cx cy) scale(s) translate(-20 -30)`. Un chiffre sans barre ni
fioriture, comme sur un cahier de CE1.

```
<path id="g7" d="M4 2 L36 2 L14 58"/>
<path id="g8" d="M20 30 A13 14 0 1 1 20 2 A13 14 0 1 1 20 30 A16 14 0 1 0 20 58 A16 14 0 1 0 20 30"/>
<path id="g9" d="M20 2 A15 15 0 1 0 20 32 A15 15 0 1 0 20 2 Z M35 17 C35 40 28 54 12 58"/>
```

Poids : 31, 96 et 81 octets de definition (une ligne `<path id=` mesure 42 a 68 octets pour
les glyphes existants : `g8` et `g9` la depassent).

## Mesure

Rendu Edge headless, une police (celle du poste), un navigateur, pas d'appareil reel. Les sept
chiffres 0, 1, 2, 5, 7, 8 et 9 sont rendus cote a cote a trois tailles (hauteur d'encre 84, 25
et 14 px environ). A 84 et 25 px, 7, 8 et 9 se distinguent entre eux et de 0, 2 et 5. A 14 px
(plancher de chiffre trace pose par `ines-design`), 8 et 9 restent distincts de 0 par leurs
boucles et leur queue ; la boucle haute du 8 se ferme a peine. Je n'ai pas mesure a 744 x 551
ni a 320 px, ni dans un cadran ou une regle : ces rendus restent a faire sur un dessin reel.

## Ce qui reste ouvert

- Les glyphes ne sont recopies dans aucun SVG : un fichier ajoute a `static/illustrations/`
  sans item qui le reference fait echouer `LIllustrationDeContenuResoutEtAucuneNeDortTest`.
  La recopie se fait avec le premier dessin de `MAT-CE1-02`.
- 3, 4, 6, A a F et la marque de zone (`LP9`, `Q7`) ne sont pas dessines ici.
