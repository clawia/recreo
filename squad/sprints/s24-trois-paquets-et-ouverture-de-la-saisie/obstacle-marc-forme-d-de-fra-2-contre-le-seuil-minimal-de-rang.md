# Obstacle - la forme (d) de `FRA-2` ne peut pas satisfaire le seuil minimal de rang

- **Auteur** : marc-dev. **Date** : 2026-08-31. **Sprint** : s24, item 7 (`S24-FRA`).
- **Destinataire** : `sofia-sm`, qui route. **Ce document ne tranche rien** : ni une condition de
  `nadia-prof`, ni le perimetre d'un garde-fou, qui est a `theo-archi`.
- **Etat de la livraison** : le paquet est **ecrit et complet** - vingt-quatre items, son test, ses
  declarations - sur la branche `s24-marc-fra`. Elle est **rouge sur cette assertion et sur elle
  seule**, et elle ne doit pas etre fusionnee avant que ce point soit tranche.

---

## 1. Le fait, avec sa mesure

`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test`, perimetre **le module
`adapter-content` et ses amonts, pas le depot** - **299 tests, 1 echec**, message integral :

```
paquet cm1-fractions : le rang la plus petite de la bonne reponse n'apparait que sur 0,0 % des 6
items a trois propositions numeriques, en dessous du seuil minimal (10,0 %)
```

Garde-fou : `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, clause
`aucunRangDeLaBonneReponseNeTombeSousLeSeuilMinimalSurLesPaquetsDeProduction`. Le seuil de 10 % est
une **reserve de `nadia-prof` du 2026-08-15**, pas un choix d'ingenieur, et le fichier le dit.

Les cinq autres clauses de ce garde-fou passent, la clause de majorite comprise : les six items se
repartissent 3 / 3 entre le rang intermediaire et le rang le plus grand, soit 50 % chacun, sous le
seuil de majorite.

## 2. Pourquoi aucune ecriture d'item ne le leve

Sur la forme **(d)** de `FRA-2` - *"la lecture du denominateur seul"* - les trois propositions d'un
item sont, par les conditions elles-memes :

| Proposition | D'ou elle vient | Sa valeur |
|---|---|---|
| la bonne reponse | `FRA-2` forme (d) | le denominateur `d` |
| distracteur de famille (a) | `FRA-5`, lecture arretee par `nadia-prof` le 2026-08-30 | le numerateur `n` |
| distracteur de famille (b) | `FRA-5` | `d - 1` ou `d + 1` |

`FRA-1` impose que la fraction enoncee soit inferieure a 1, donc **`n < d`**. La bonne reponse est
donc toujours superieure a au moins une des deux autres propositions, sur **tous** les items de
cette forme, quelle que soit la fraction choisie et quel que soit le decalage retenu. Le rang *"la
plus petite"* vaut 0 % par construction, et non par une maladresse de redaction.

La famille (c) ne fournit pas d'issue : `FRA-5` la reserve aux formes (a) et (b), et `nadia-prof`
la redit le 2026-08-30 - *"la famille (c) reste exclue de la forme (d)"*.

Les trois autres formes de `FRA-2` ne compensent pas : leurs propositions sont soit une
denomination en toutes lettres, soit une fraction ecrite en chiffres, et la lecture de valeur les
ecarte du domaine depuis `ADR-0068`. Elles sont declarees comme telles dans
`PAQUETS_A_ITEMS_ECARTES` (18 items sur 24). Le taux de 0 % porte donc sur les 6 items de la forme
(d), et il porterait 0 % sur n'importe quel effectif.

`FRA-3` exige au moins trois items par forme : **la forme (d) ne peut pas etre retiree du paquet.**

## 3. Ce que le garde-fou attrape reellement, et qui est un vrai fait pedagogique

Ce n'est pas un faux positif, et je ne le presente pas comme tel. Ce que la reserve de `nadia-prof`
vise - *"un rang quasi vide elimine une proposition gratuitement a presque chaque manche"* - est
exactement vrai ici : sur un item de forme (d), un enfant qui elimine systematiquement la plus
petite des trois propositions **n'elimine jamais la bonne reponse**. Il passe de trois propositions
a deux sans lire l'enonce.

C'est un fait sur les conditions, pas sur mon ecriture. Il appartient a `nadia-prof` de dire s'il
est acceptable sur cette forme-la.

## 4. Ce que je n'ai pas fait, et pourquoi

- **Je n'ai pas ecrit les propositions de la forme (d) sous une forme que la lecture de valeur
  ecarte** - par exemple *"En 8 parts"* au lieu de *"8"*. Cela rendrait le paquet entier hors du
  domaine numerique, donc declarable dans `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE`, qui n'a pas de
  plafond, et la CI serait verte. **Le defaut de la section 3 serait toujours la, et plus personne
  ne le verrait.** C'est rendre un garde-fou aveugle par la redaction, la famille de defaut que ce
  depot sanctionne depuis s05.
- **Je n'ai pas ajoute `cm1-fractions` a `DETTE_DE_SEUIL_MINIMUM`.** Cette table porte trois
  paquets et son plafond vaut trois, avec la mention *"ce nombre ne remonte jamais"*. Le faire
  remonter est une decision d'architecture ; elle n'est pas de moi.
- **Je n'ai amende aucune condition de `FRA-1` a `FRA-10`.** Elles ne sont pas de moi non plus.
- **Je n'ai pas livre un paquet ampute de sa forme (d)** : `FRA-3` l'interdit, et un paquet qui ne
  tient pas ses propres conditions serait refuse a l'audit, a juste titre.

## 5. Les deux issues que je vois, sans en preferer aucune

1. **`nadia-prof`** : la forme (d) assume ce rang impossible, et le garde-fou gagne un mecanisme de
   declaration pour *"le rang est structurellement hors d'atteinte sur ce paquet"* - distinct d'une
   dette, sur le patron sans plafond de `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE`, qui existe deja pour
   dire *"cette propriete ne s'applique pas ici"* plutot que *"elle est violee ici"*. Le mecanisme
   serait alors a `theo-archi`, en `ADR`.
2. **`nadia-prof`** : `FRA-5` est amendee sur la forme (d) pour ouvrir une famille de distracteur
   qui puisse depasser le denominateur des deux cotes. Je ne propose aucune formulation : le
   contenu des familles de distracteurs est sa porte, et elle a ecrit avoir *"assez paye `D-4` et
   `D-5`"* pour ne pas laisser un developpeur choisir une lecture.

**Ce qui est pret des que l'une des deux est rendue** : la branche `s24-marc-fra`, ou seule cette
assertion est rouge. Si l'issue 2 est retenue, seuls les six items de la forme (d) sont a reecrire ;
le test les recalcule depuis les declarations du fichier et rougira de lui-meme si la reecriture
s'ecarte de la famille declaree.

---

## 6. Resolution - `nadia-prof` a tranche le 2026-08-31, la branche est verte

*Bloc ajoute par `marc-dev` le 2026-08-31, apres coup. Rien de ce qui precede n'est efface :
les sections 1 a 5 decrivent l'etat du matin et restent lisibles a leur place.*

**Ce qu'elle a rendu** :
`squad/conformite/ARBITRAGE-FRA-la-forme-d-contre-le-seuil-minimal-de-rang.md`. Elle **tient le
seuil de rang sans le deplacer d'un point** et **amende sa propre `FRA-5`**, sur le test qui separe
une propriete d'un moyen : le seuil dit ce que l'enfant ne doit pas pouvoir faire, la liste des
familles de distracteurs est une facon d'ecrire un item. Un moyen se remplace.

**Elle a reverifie le fait par enumeration, et il est plus fort que ma mesure.** Mon 0,0 % portait
sur six items ; sur les 45 fractions qu'`FRA-1` admet, le rang *"la plus petite"* est realisable
**0 fois sur 45** - impossible, et non rare.

**Mes deux issues sont refusees toutes les deux, et la seconde par un motif que je n'avais pas
vu.** L'issue 1 - declarer le rang hors d'atteinte - a une premisse fausse : ce qui ne peut pas
tenir la propriete n'est pas la competence, c'est sa liste. Une declaration l'aurait consignee comme
une propriete du monde, sans plafond, donc sans reexamen. Et la voie que ni elle ni moi n'avions
nommee - retirer la forme (d) - est la plus dangereuse : ses six items sont **les seuls du paquet a
propositions numeriques**, donc leur retrait sortirait `cm1-fractions` du domaine du garde-fou de
rang tout entier, et la CI serait verte parce que plus rien ne serait regarde.

**Ce qui remplace** : une **famille (e)**, `n + d`, sur la forme (d) seule - l'enfant qui lit le
denominateur comme les parts *restantes* au lieu du *tout* -, un plancher `FRA-5e-1` sur la famille
(a), et deux clauses de forme (`FRA-5e-2`, `FRA-5e-3`).

**Ce que le lot a fait** : **deux items reecrits, et deux seulement.** Les quatre autres portaient
deja la famille (a) et couvraient deja deux des trois rangs. Comptes recalcules sur le fichier
servi - lecture directe du YAML, tri des trois propositions, rang de la bonne reponse :

| | Avant | Apres |
|---|---|---|
| rang *la plus petite* | 0 / 6 - **0,0 %** | 2 / 6 - **33,3 %** |
| rang *celle du milieu* | 3 / 6 - 50,0 % | 2 / 6 - 33,3 % |
| rang *la plus grande* | 3 / 6 - 50,0 % | 2 / 6 - 33,3 % |
| famille (a) sur la forme (d) | 6 / 6 | 4 / 6 - **66,7 %**, plancher `FRA-5e-1` tenu |

**Etat de `s24-marc-fra`** : verte. `mvn -B clean verify` a la racine du reacteur, reacteur complet
donc aucun `-am` a citer - **BUILD SUCCESS, 825 tests, 0 echec**, +14 sur la base de la fusion
d'ouverture mesuree a 811. Elle est fusionnable, sous reserve de l'ordre de fusion que `sofia-sm`
tient.

**Un defaut de ma mesure du matin, que je verse plutot que de le taire** : l'entree de catalogue
neuve manquait a l'inventaire de `TexteAccentueExposeParLePortEntrantTest`, et **ce rouge-la
n'apparaissait pas** sur la mesure partielle d'`adapter-content` qui m'avait servi a instruire cet
obstacle. C'est le `verify` a la racine qui l'a rendu. Le perimetre etait nomme a cote du chiffre,
comme `CLAUDE.md` l'exige, et c'est ce qui rend l'ecart lisible - mais il illustre exactement
pourquoi la porte technique est la commande de la racine et rien d'autre.

**Ce qui reste ouvert sur ce paquet, et qui n'est pas de moi** : le Done pedagogique. `nadia-prof`
n'a rien audite, elle l'ecrit, et elle nomme ce qu'aucun automate ne gardera - la plausibilite de
`n + d` item par item, `FRA-8`, et le jugement de niveau sur les vingt-quatre items.
