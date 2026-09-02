# Obstacle a sequencer - le remplissage de couleur sur les illustrations de monnaie

- **Date** : 2026-09-02.
- **Auteure** : `sofia-sm` (casquette Scrum Master), sur routage d'`ines-design`, qui a refuse de
  trancher seule - a raison : sa question 1 (section "Sequencement") appartient a `nadia-prof`
  seule.
- **Depot au moment ou cette note est ecrite** : `round-two` a `bf18ee0`, sprint 26 clos.

## Le retour qui declenche l'obstacle

De l'utilisatrice, 2026-09-02 : *"pour la monnaie, pourquoi des images n'ont pas ete utilisees ?"*

Les images sont bien la et servies. Le probleme qu'`ines-design` a trouve : elles **ne se lisent
pas comme de la monnaie** - des cercles au trait sans remplissage, avec le chiffre trace en
geometrie.

## Le correctif d'`ines-design`, verifie en le faisant rendre dans un navigateur

`fill="#e8a33d"` sur le cercle exterieur des pieces, `fill="#cfe3ef"` sur le rectangle exterieur
des billets. Le lisere interieur, le cadre et **tous les chiffres restent `fill="none"`**, en
contour pur. Aucun element de texte ajoute. Douze fichiers revises, **non commites** par elle -
voici pourquoi.

## Ce que le correctif casse, mesure et non suppose

```
Tests run: 13, Failures: 2
- 44x "la surface est remplie de '#e8a33d'/'#cfe3ef' (FIG-3, FIG-4)"
- leCliquetVautExactementCeQueCeLotInstalle: expected 30070, but was 30730
```

## Son analyse - douzieme occurrence du defaut dominant de s26

Le critere de `nadia-prof` est *"la couleur ne decide jamais"*. Un remplissage **uniforme et
invariant selon la valeur** ne decide rien - le chiffre porte seul la valeur. Mais le garde-fou
interdit **tout** remplissage n'importe ou sous `static/illustrations/`, qu'il varie ou non. **La
regle est juste, son encodage est plus large qu'elle** - meme famille que les clauses de
`nadia-prof` trouvees fausses en extension le jour de la cloture de s26 (`retro.md` de s26).

## Un verdict a ne pas eroder au passage

Sur `cp-figures-planes`, le trait pur est **le bon choix, pas un oubli** : *"Remplir un cercle en
fait un disque, un autre objet geometrique - exactement la confusion pour laquelle un paquet a
deja ete refuse."* Verifie par elle en faisant rendre un carre et un cercle. **Le correctif ne
s'etend pas a ce paquet.**

## Sequencement - dans cet ordre, et pas avant

1. **`nadia-prof` confirme ou infirme la lecture du critere** : est-ce bien *"la couleur ne
   decide jamais"* et non *"aucun remplissage"* ? C'est sa clause, elle seule la lit. **Rien
   n'est fait avant sa reponse** - si elle dit que sa clause interdit bien tout remplissage, le
   correctif tombe et il faut chercher autre chose. **Elle travaille en ce moment sur les
   matieres absentes de la matrice ; ce sequencement l'attend et ne l'interrompt pas.**
2. **Si elle confirme** : le retrecissement du garde-fou a la population pour laquelle il a ete
   ecrit revient a ses proprietaires, avec `theo-archi` sur la facon dont une population par
   paquet se derive plutot qu'elle ne s'enumere - le principe *perimetre derive, jamais enumere*
   qu'`ADR-0073` applique deja ailleurs dans cette famille de tests. `ines-design` le nomme
   explicitement.
3. **Le cliquet de poids** (`ADR-0036` clause 5) est releve **dans le meme commit** que le point
   2, avec son motif et sa valeur mesuree : +90 octets par fichier, **660 octets au total**,
   entierement dus aux attributs `fill`. Un cliquet releve sans dire pourquoi est un cliquet mort.
4. **Le paquet monnaie porte deja un Done pedagogique** (`FICHE-bandit-manchot-monnaie-cp.md`,
   VALIDE SOUS RESERVE). `ines-design` : *"je ne rouvre pas son verdict, je signale qu'un
   changement de dessin sur des fichiers deja valides demande son oeil."* `nadia-prof` rejoue
   l'increment avant que ce paquet soit a nouveau tenu pour Done.

## Ce qui ne doit pas se telescoper

`camille-po` et `nadia-prof` travaillent en parallele sur une instruction plus lourde et distincte
de cet obstacle : l'utilisatrice a constate que la matrice ne couvre que deux matieres sur huit et
demande anglais, histoire-geographie et questionner le monde pour s27. Ce sequencement ne les
interrompt pas et ne s'y mele pas ; il attend une fenetre dans le travail de `nadia-prof` sur la
question 1 ci-dessus.

## Statut

**Ouvert, en attente de la reponse de `nadia-prof` sur la question 1.** Aucune action de
`theo-archi`, `ines-design` ou tout autre agent n'est engagee sur les points 2 a 4 avant cette
reponse.
