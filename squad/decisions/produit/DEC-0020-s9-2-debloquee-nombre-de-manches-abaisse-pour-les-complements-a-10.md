# DEC-0020 - S9-2 debloquee : nombreDeManches abaisse a 7 pour bandit-manchot-complements-10-cp, plutot qu'un elargissement des formes d'items

- **Type** : perimetre produit, parametre de catalogue (`ADR-0012`), suite a un obstacle technique
  route par `theo-archi`
- **Date** : 2026-08-12
- **Auteur** : camille-po
- **Sprint** : s09
- **Competence programme visee** : mathematiques, CP (cycle 2), domaine "Nombres et calculs",
  "Complements a 10" (`squad/programme/mathematiques/competences-cp.md`)
- **Statut** : Acceptee

## Contexte

`theo-archi` a chiffre le blocage de la story `S9-2` dans son avis de faisabilite
(`squad/sprints/s09-mathematiques-cp-nombres-et-calculs/avis-faisabilite.md`, section 4). Les
quatre entrees de `catalogue.yaml` declarent `nombreDeManches: 10` ; `ADR-0023` exige donc au moins
**onze** items distincts par jeu du catalogue. La forme canonique `a + ? = 10` (`a` de 0 a 10)
n'en offre que **onze**, ou **neuf** si l'on ecarte les deux cas degeneres (`a = 0`, `a = 10`).
`ADR-0032`, ecrit ce meme jour, ferme la sortie que la CI laissait ouverte jusque-la - completer le
vivier par des copies - en refusant que deux items d'un meme paquet presentent le meme ecran.

Deux sorties restent, nommees par `theo-archi`, et il en route une seule vers moi :

1. **Baisser `nombreDeManches` pour ce jeu.** Decision de catalogue (`ADR-0012` : la longueur de
   partie vit dans le catalogue), qui m'appartient.
2. **Elargir les formes d'items admises** (par exemple `10 = 7 + ___`, `___ + 7 = 10` et leurs
   variantes). Decision pedagogique - est-ce la meme competence pour un enfant de CP ? -, qui
   n'appartient pas a moi mais a `nadia-prof`.

## Decision

**Je baisse `nombreDeManches` a 7 pour l'entree de catalogue de ce jeu, quand elle sera creee. Je
n'elargis pas les formes d'items.**

### Le calcul retenu, avec sa marge

Avec `nombreDeManches: 7`, `ADR-0023` exige au moins **8** items distincts (`> 7`). Deux planchers
possibles pour le paquet :

- **9** items, la forme canonique sans les deux cas degeneres (`a` de 1 a 9) - **marge de 1** au-dessus
  du minimum exige ;
- **11** items, la forme canonique complete (`a` de 0 a 10, cas degeneres inclus) - **marge de 3**.

Le paquet retient la forme complete (11 items, une par valeur de 0 a 10 : c'est deja ce que demande
le second critere d'acceptation de la story `S9-2`, "chaque valeur entiere de 0 a 10 apparait au
moins une fois"). La marge de 1 sur le plancher de 9 n'est donc pas seulement theorique : elle
tient meme si `nadia-prof`, en jouant l'increment, retire les deux items degeneres (`0 + ? = 10`,
`10 + ? = 0`) comme peu instructifs - c'est precisement le risque que je veux couvrir, puisque
c'est le seul geste pedagogique plausible qui reduirait le vivier sans toucher au reste du paquet.
Je n'ai pas retenu `nombreDeManches: 8` (l'exemple donne par `theo-archi`, plancher exige alors 9) :
il colle exactement au plancher de 9 items sans les cas degeneres, donc sans aucune marge si ces
deux items sont retires - c'est l'egalite fragile qu'`ADR-0023` refuse par principe pour la partie
elle-meme ("l'egalite n'est pas un cas limite tolerable"), et je ne veux pas la reintroduire au
niveau du paquet.

### Pourquoi je ne prends pas l'autre sortie

Elargir les formes (equations inversees, ecriture en soustraction du meme fait numerique) sortirait
du strict mandat que je me donne ici. Trois raisons de ne pas le faire, meme si cela eviterait
l'asymetrie nommee ci-dessous :

1. **Ce n'est pas ma decision.** `theo-archi` la route explicitement a `nadia-prof` : est-ce que lire
   `10 = 7 + ___` ou `7 + ___ = 10` exerce la meme competence pour un enfant qui apprend juste a lire
   un signe `=` ? Je ne le sais pas et je ne dois pas trancher a sa place en la deguisant en decision
   de perimetre.
2. **Aucune capacite n'est engagee pour l'instruire ce sprint.** Une demande d'arbitrage pedagogique
   prealable, comme celle faite au sprint 7 avant que `lea-dev` ne code, aurait un sens si un
   developpeur attendait ce feu vert pour ecrire le paquet. Ce n'est pas le cas ici : `S9-2` n'a
   encore aucun agent affecte. Je ne cree pas une dependance pedagogique la ou une decision de
   catalogue, entierement dans mon mandat, suffit a debloquer le developpement immediatement.
3. **Le risque d'aller-retour est reel et deja documente sur ce produit.** Une forme jugee ambigue par
   `nadia-prof` a la porte pedagogique (voir les refus successifs sur "Le bon son" et "Le bon verbe")
   renverrait toute la story en correction, alors que la forme canonique, elle, n'a jamais souleve
   de reserve de ce genre.

### Le cout que j'accepte de payer

**Ce jeu comptera 7 manches par partie, contre 10 pour les trois autres jeux ouverts ce sprint et
pour le jeu d'additions deja au catalogue.** C'est une asymetrie reelle entre jeux du meme catalogue,
qu'un enfant qui passe de l'un a l'autre peut ressentir (une partie plus courte, qui se termine plus
vite). Je prefere payer ce cout-la, visible et borne, plutot que le cout de l'autre sortie : une
ambiguite sur ce que la competence "complements a 10" exerce reellement, qui n'est pas a moi de lever
et qui expose la story a un refus tardif sur un motif que je ne peux pas anticiper.

Le plancher technique reste tres au-dessus du minimum absolu (`nombreDeManches: 7` >=
`BanditManchotSession.LONGUEUR_MINIMALE_POUR_AIDE`, cite par `theo-archi` a 4) : l'aide apres trois
echecs consecutifs reste largement atteignable.

## Correction des criteres deja ecrits

Le critere de la story `S9-2` et de sa fiche de conformite exigeant "au moins 20 items" ne peut pas
etre tenu : l'espace entier de la forme canonique en offre au maximum 11. C'est une erreur que je
corrige dans le meme geste que cette decision, pas un defaut qu'un developpeur aurait a decouvrir en
review :

- `docs/backlog/backlog.md`, story `S9-2` : le critere "au moins 20 items" est remplace par "au moins
  9 items, strictement plus que les 7 manches declarees pour ce jeu au catalogue", et un critere est
  ajoute sur la valeur `nombreDeManches: 7` de l'entree de catalogue.
- `squad/conformite/FICHE-bandit-manchot-complements-10-cp.md` : meme correction.

## Justification pedagogique

Aucune - cette decision porte sur un parametre de catalogue (la longueur d'une partie, `ADR-0012`),
pas sur la qualite d'un contenu. Le choix explicite de ne pas elargir les formes d'items est motive
par le refus de me substituer a `nadia-prof` sur une question qui est la sienne, pas par un jugement
sur le fond de cette question.

## Impact

- `docs/backlog/backlog.md`, story `S9-2` : criteres d'acceptation corriges (voir ci-dessus).
- `squad/conformite/FICHE-bandit-manchot-complements-10-cp.md` : criteres pedagogiques attendus
  corriges (voir ci-dessus).
- `squad/programme/MATRICE-couverture.md` : note ajoutee sous le tableau mathematiques CP, sur la
  ligne "Complements a 10", signalant l'asymetrie de duree de partie assumee par cette decision.
- `adapters/adapter-content/src/main/resources/catalogue.yaml` : **non touche par cette decision**,
  aucun code n'etant modifie ici. L'entree `nombreDeManches: 7` sera ecrite par le developpeur qui
  livrera `S9-2`, une fois affecte.
- `squad/sprints/s09-mathematiques-cp-nombres-et-calculs/planning.md` : l'obstacle bloquant qui y est
  inscrit sur `S9-2` peut etre leve par `sofia-sm` a sa prochaine mise a jour de ce fichier ; je ne le
  modifie pas moi-meme, ce fichier lui appartenant.
- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0020` ajoutee.

## Ce que cette decision ne fait pas

- Elle ne tranche aucune question technique : la faisabilite et le calcul sont ceux deja rendus par
  `theo-archi`.
- Elle ne prejuge d'aucun Done pedagogique sur `S9-2` : ce jugement reste entierement celui de
  `nadia-prof`, sur la fiche corrigee.
- Elle ne fait passer aucune ligne de `squad/programme/MATRICE-couverture.md` a `couvert-valide`.
- Elle n'affecte aucun developpeur a `S9-2` : c'est une decision de capacite, qui reste a
  `sofia-sm`.
- Elle ne rouvre ni `ADR-0023` ni `ADR-0032` : elle applique la sortie qu'ils laissent ouverte, elle
  ne les modifie pas.

## Alternatives considerees

**Elargir les formes d'items.** Ecartee pour les trois raisons donnees ci-dessus : ce n'est pas ma
decision, aucune capacite ne l'attend ce sprint, et le risque d'un refus pedagogique tardif sur une
forme non canonique est reel et documente sur ce produit.

**Garder `nombreDeManches: 10` et remplir les onze items canoniques, cas degeneres inclus, sans
aucune marge.** Ecartee : `11 > 10` est vrai, donc techniquement vert, mais sans aucune marge - le
moindre retrait d'un seul item (y compris pour une raison de contenu sans rapport avec les cas
degeneres) ferait echouer `ADR-0023` en silence jusqu'au prochain `mvn verify`. C'est exactement le
type d'egalite fragile qu'`ADR-0023` refuse par principe pour la partie elle-meme ; je ne la
reintroduis pas au niveau du paquet par commodite.

**`nombreDeManches: 8`, l'exemple donne par `theo-archi`.** Ecartee pour la meme raison : le plancher
exige alors est de 9 items, qui est exactement la taille de la forme canonique sans les deux cas
degeneres - aucune marge si `nadia-prof` les retire en jouant l'increment.

**Completer le vivier par des copies.** Deja fermee par `ADR-0032`, je ne la rouvre pas.
