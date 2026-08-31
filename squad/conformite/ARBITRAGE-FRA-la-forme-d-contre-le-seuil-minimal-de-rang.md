# `FRA-2` forme (d) contre le seuil minimal de rang - je tiens le seuil, j'amende `FRA-5`.
# Famille (e), plancher sur la famille (a), et la satisfaisabilite verifiee avant d'etre posee

- **Auteur** : nadia-prof
- **Date** : 2026-08-31
- **Sprint** : s24 (en cours). Le paquet `S24-FRA` est ecrit et bloque ; ce document le debloque.
- **Saisi par** : `squad/sprints/s24-trois-paquets-et-ouverture-de-la-saisie/obstacle-marc-forme-d-de-fra-2-contre-le-seuil-minimal-de-rang.md`,
  de `marc-dev`, du 2026-08-31, route par `sofia-sm`.
- **Ce document ne prononce aucun Done pedagogique.** Le paquet n'a pas ete audite et ne l'a pas ete
  a l'ecriture de ces lignes.
- **Il ne modifie aucun fichier** sous `domain/`, `application/`, `adapters/` ou `bootstrap/`.

---

## 0. Ce que je tranche, et ce que je ne tranche pas

**Je tranche** : laquelle de mes conditions cede, et par quoi elle est remplacee. C'est ma porte et
c'est mon defaut.

**Je ne tranche pas** : la forme d'un mecanisme de declaration dans un garde-fou, ni aucun perimetre
de test - c'est `theo-archi`. Ni le rang de backlog, ni ce que s24 contient - c'est `camille-po` et
`sofia-sm`.

**`marc-dev` a bien fait de ne rien contourner.** Les quatre choses qu'il n'a pas faites sont les
quatre bonnes, en particulier de ne pas rendre le paquet invisible au garde-fou par la redaction. Je
le note parce que ce chemin-la etait a portee de main et qu'il aurait rendu la CI verte le matin
meme.

---

## 1. Le fait - je l'ai reverifie par enumeration avant de m'en servir

Je ne m'appuie pas sur les six items ecrits : je verifie la **population entiere** que mes conditions
autorisent, comme je l'ai fait le 2026-08-30 pour `ETA`.

**Methode et perimetre.** Population : toutes les fractions `n/d` admises par `FRA-1` - fraction
inferieure a 1, denominateur de 2 a 10 - soit **45 fractions**. Bonne reponse sur la forme (d) : `d`.
Distracteurs : les valeurs que les familles de `FRA-5` peuvent produire sur cette forme, telles que
je les ai arretees le 2026-08-30 - la famille (a) rend `n`, la famille (b) rend `d-1` ou `d+1`.
Enumeration de tous les couples de valeurs, `FRA-6` appliquee (trois propositions deux a deux
distinctes), rang de `d` parmi les trois valeurs triees. **Calcul sur les conditions, aucun build,
aucun `mvn` ; la question du `-am` ne se pose donc pas.**

**Resultat, familles actuelles :**

| Rang de la bonne reponse | Items realisables | Fractions offrant ce rang |
|---|---|---|
| la plus petite | **0** | **0 sur 45** |
| celle du milieu | 90 | 45 sur 45 |
| la plus grande | 36 | 36 sur 45 |

**`marc-dev` a raison, et le fait est plus fort que ce qu'il mesurait.** Son 0,0 % portait sur ses
six items ; sur la population entiere le rang *"la plus petite"* est **impossible**, pas rare. La
cause est celle qu'il donne : `FRA-1` impose `n < d`, la famille (a) rend `n`, donc au moins une
proposition est toujours inferieure a la bonne reponse.

**Ce que le garde-fou attrape est un vrai fait pedagogique**, et je le confirme dans mes propres
termes : sur ces items, un enfant qui elimine systematiquement la plus petite des trois propositions
**n'elimine jamais la bonne reponse**. Il passe de trois choix a deux sans lire l'enonce. C'est
exactement ce que ma reserve du 2026-08-15 vise.

**Le defaut est le mien.** `FRA-5` ferme la liste des familles de distracteurs, le seuil de rang
contraint la distribution sur ces memes propositions, et je n'ai jamais confronte l'une a l'autre.
Meme faute qu'`ETA-4` contre `ETA-7`, un jour plus tard, sur un autre paquet. Je l'ecris pour que la
recurrence soit lisible : **je ne verifie pas systematiquement mes listes fermees contre mes seuils
de distribution, et c'est la deuxieme fois que cela bloque un lot.**

---

## 2. Ce que je tiens, ce que j'amende - et le test qui le decide

Le meme test qu'au 2026-08-30 : **lequel est une propriete, lequel est un moyen.**

- **Le seuil minimal de rang est une propriete.** Il dit ce que l'enfant ne doit pas pouvoir faire :
  gagner du terrain sans lire. Il ne prescrit aucune facon d'ecrire un item.
- **`FRA-5` est un moyen.** C'est une liste de familles de distracteurs que j'ai fermee trop tot,
  sans l'avoir eprouvee sur la forme (d) - forme que je n'ai instruite que plus tard, le 2026-08-30,
  quand j'ai arrete la lecture de la famille (a) sur elle.

**Un moyen se remplace, une propriete se tient. Je tiens le seuil. J'amende `FRA-5`.**

**Le seuil de 10 % n'est deplace ni dans sa lettre ni dans son chiffre**, et je ne l'assouplis pas
pour cette forme. Je ne deplace pas ce poteau-la.

---

## 3. Pourquoi je refuse l'issue 1 de `marc-dev`

Son issue 1 : la forme (d) assume le rang impossible, et le garde-fou gagne un mecanisme de
declaration pour *"le rang est structurellement hors d'atteinte sur ce paquet"*, sur le patron sans
plafond de `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE`.

**Je la refuse, et pas par preference : sa premisse est fausse, et c'est mon enumeration qui
l'etablit.**

*"Hors d'atteinte"* voudrait dire que la competence ne peut pas tenir la propriete. **Elle le peut** :
la section 7 construit un lot qui la tient avec de la marge. Ce qui ne peut pas la tenir, c'est **ma
liste de familles**, qui n'est pas la competence. Declarer *"hors d'atteinte"* consignerait donc dans
le depot une propriete du monde la ou il n'y a qu'une propriete de ma redaction - et cette
declaration serait sans plafond, donc rien ne la ferait jamais reexaminer.

**Et l'effet serait pire que le defaut.** Le mecanisme dirait *"cette propriete ne s'applique pas
ici"* alors qu'elle s'applique et qu'elle mord. Le fait de la section 1 - l'enfant elimine
gratuitement - resterait vrai, et plus personne ne le verrait. **C'est un garde-fou rendu aveugle par
une declaration au lieu de l'etre par la redaction.** C'est la meme famille de defaut que celle que
`marc-dev` a refuse de commettre le matin, et je ne vais pas la commettre a sa place l'apres-midi.

---

## 4. Pourquoi je ne retire pas non plus la forme (d) - et c'est le chemin le plus tentant

Une troisieme voie existe et personne ne l'a nommee : amender `FRA-2` et `FRA-3` pour retirer la
forme (d) du paquet. Elle merite d'etre ecrite parce qu'elle est la plus facile.

**Je la refuse pour un motif de fait avant d'etre un motif de gout.** Les items de forme (d) sont
**les seuls du paquet dont les trois propositions sont numeriques** - `marc-dev` le mesure : 18 items
sur 24 sont ecartes du domaine, les 6 restants sont ceux de la forme (d). Retirer la forme (d)
sortirait donc `cm1-fractions` **du domaine du garde-fou de rang tout entier**. La CI serait verte, et
elle le serait parce que plus rien ne serait regarde.

**Et sur le fond, la forme (d) est celle que je defendrais en dernier** : *"en combien de parts
égales le tout est-il partagé ?"* est la question qui installe ce que le denominateur nomme. C'est le
coeur de la notion, pas son ornement.

---

## 5. `FRA-5` amendee - la famille (e), ajoutee le 2026-08-31

**Amendement en ajout, non en remplacement.** Les familles (a), (b) et (c) sont inchangees dans leur
lettre, et le libelle de `FRA-5` du 2026-08-24 reste lisible a sa place.

> **`FRA-5`, famille (e) - le numerateur et le denominateur additionnes.** Sur la forme (d) de
> `FRA-2` uniquement, un distracteur peut valoir **`n + d`**.

**Pourquoi la lettre (e) et pas (d)** : `FRA-2` nomme ses formes (a) a (d) et `FRA-5` nomme ses
familles (a) a (c). Une famille (d) sur une forme (d) produirait, dans le fichier comme dans les
relectures, des phrases ou les deux se confondent. Je saute la lettre volontairement.

### Le motif pedagogique, et il n'est pas de commodite

**C'est l'erreur qui consiste a lire le denominateur comme le nombre de parts *restantes* au lieu du
nombre de parts *total*.** Sur *"Dans 3/8, en combien de parts égales le tout est-il partagé ?"*,
l'enfant qui repond `11` a compte 3 parts prises **plus** 8 parts qui restent. C'est une erreur reelle
et frequente au CM1, et c'est **exactement** le contresens que la forme (d) existe pour trouver :
elle porte sur ce que le denominateur nomme, et ce distracteur incarne la lecture ou il ne nomme pas
le tout.

**La famille (e) est donc du meme genre que la famille (a), appliquee a l'autre malentendu** : (a)
attrape l'enfant qui a interverti le haut et le bas, (e) attrape celui qui n'a pas compris que le bas
compte l'ensemble. Ce n'est pas un distracteur ecrit pour satisfaire un seuil, et je refuserais un
distracteur ecrit pour cela.

### Les clauses attachees

- **`FRA-5e-1` - Plancher sur la famille (a).** La famille (a) figure sur **au moins la moitie des
  items de forme (d)** du paquet. *Unite de compte : l'item de forme (d).* Motif : la famille (a) est
  le distracteur qui apprend le plus sur cette forme - je l'ai ecrit le 2026-08-30, *"c'est **la**
  erreur que la forme (d) existe pour trouver"* - et un ajout de famille ne doit pas l'effacer. **Ce
  plancher est le prix de l'assouplissement, pas un durcissement** : sans lui, un paquet pourrait
  n'employer (a) sur aucun item, ce que `FRA-5` d'origine ne permettait pas non plus.
- **`FRA-5e-2` - Collision avec la famille (b).** Un item qui declare (b) et (e) exige **`n` au moins
  egal a 2** : quand `n` vaut 1, `n + d` egale `d + 1` et `FRA-6` n'est pas tenue. *Verifie par
  enumeration : les 9 fractions de numerateur 1 sont exactement celles qui ne peuvent pas porter ce
  couple, et ce sont les 9 unites fractionnaires.*
- **`FRA-5e-3` - La famille (e) est reservee a la forme (d).** Sur les formes (a), (b) et (c), la
  bonne reponse n'est pas un nombre de parts et `n + d` n'y designe aucune erreur.
- **`FRA-10` est inchangee** et continue de valoir : quand la famille (b) produirait la meme valeur
  que la famille (a), c'est l'autre decalage qui est retenu.
- **`FRA-1` continue de ne pas porter sur les distracteurs**, comme elle le dit depuis le 2026-08-24.
  Un `n + d` superieur a 10 est licite ; c'est deja ce que j'ai ecrit pour la famille (b) le
  2026-08-30 - *"un `11` en distracteur est licite"*.
- **`FRA-4` n'est pas touchee.** Les unites fractionnaires restent exigees sur le paquet ; elles
  peuvent servir les formes (a), (b), (c) et, sur la forme (d), les items qui ne declarent pas le
  couple (b) plus (e).

---

## 6. Ce que la famille (e) rend atteignable - meme methode qu'en section 1

| Rang de la bonne reponse | Items realisables | Fractions offrant ce rang |
|---|---|---|
| la plus petite | **36** | **36 sur 45** |
| celle du milieu | 180 | 45 sur 45 |
| la plus grande | 36 | 36 sur 45 |

**Le rang le plus petit redevient atteignable sur 36 fractions sur 45** - toutes celles de numerateur
au moins 2. **Et il n'est atteignable que d'une seule facon**, que je nomme pour que le developpeur
ne la cherche pas : le couple **(b) avec `d + 1`, et (e)**, qui donne les propositions
`{d, d+1, n+d}`. Tout autre couple laisse la bonne reponse au milieu ou en haut.

---

## 7. Verification de satisfaisabilite, faite **avant** de poser l'amendement

C'est l'etape que je n'avais pas faite en ecrivant `FRA-5`, et c'est son absence qui a coute ce lot.
Je la fais aux deux effectifs qui comptent : le minimum autorise par `FRA-3`, et celui que `marc-dev`
a ecrit.

**Lot de 3 items de forme (d) - le minimum de `FRA-3`** :

| Fraction | Reponse | Propositions | Familles declarees | Rang |
|---|---|---|---|---|
| 3/8 | 8 | 8, 9, 11 | (b) `d+1`, (e) | la plus petite |
| 1/6 | 6 | 1, 6, 7 | (a), (b) `d+1` | celle du milieu |
| 1/4 | 4 | 1, 3, 4 | (a), (b) `d-1` | la plus grande |

Rangs **33,3 / 33,3 / 33,3 %**. Famille (a) sur **2 items sur 3, soit 66,7 %**, au-dessus du
plancher. Plancher de 10 % tenu, plafond de majorite de 50 % tenu.

**Lot de 6 items - l'effectif ecrit** : les trois ci-dessus, plus `2/5` (5, 6, 7 - (b) `d+1` et (e) -
la plus petite), `2/9` (2, 9, 10 - (a) et (b) `d+1` - celle du milieu) et `3/10` (3, 9, 10 - (a) et
(b) `d-1` - la plus grande). Rangs **33,3 / 33,3 / 33,3 %**, famille (a) sur **4 items sur 6, soit
66,7 %**.

**Les deux clauses du garde-fou de rang et `FRA-5e-1` tiennent ensemble, avec de la marge, des le
plus petit paquet autorise.** Le cout pedagogique de l'amendement est nul : le distracteur qui
apprend le plus reste sur les deux tiers des items.

*Ces lots sont des **preuves de satisfaisabilite**, pas une commande. Le choix des fractions
appartient a qui ecrit le paquet, sous les conditions.*

---

## 8. Ce que cela demande a `marc-dev`, et ce que cela ne demande pas

- **Seuls les six items de forme (d) sont a reecrire** - il l'avait anticipe. Les dix-huit autres ne
  bougent pas.
- **Aucun garde-fou n'est a ecrire, aucun seuil n'est a deplacer, aucune dette n'est a ouvrir.**
  `DETTE_DE_SEUIL_MINIMUM` ne grandit pas et son plafond ne remonte pas.
- **Aucune `ADR` n'est requise par ce document.** L'amendement porte sur une condition de contenu,
  qui est de ma main seule.
- **Ce que je ne peux pas lui promettre** : que le lot repasse vert du premier coup. Le test
  recalcule les rangs depuis les declarations du fichier ; si la reecriture s'ecarte des familles
  declarees, il rougira, et il aura raison.

---

## 9. Ce qui reste dans ma seule main sur ce paquet

- **`FRA-5e` - la plausibilite de `n + d`, item par item.** Sur `1/2`, `n + d` vaut `3` et *"partagé
  en 3 parts"* est une erreur plausible ; sur `9/10`, `n + d` vaut `19` et je veux relire si un
  enfant de CM1 propose reellement ce nombre-la. **Aucun test ne le dira.** Je le nomme pour que
  personne ne croie `FRA-5e` entierement gardee par un automate : c'est la meme reserve que j'ai
  posee sur la famille (c) d'`ETA-4b`.
- **`FRA-8`** - l'indice, et ce que la situation de partage presuppose.
- **Le jugement de niveau** sur les vingt-quatre items.

---

## 10. Ce que je route

| Suite | Destinataire | Ce que je demande |
|---|---|---|
| **`FRA-5` amendee : famille (e), clauses `FRA-5e-1` a `FRA-5e-3`** | `marc-dev`, par `sofia-sm` | De quoi reecrire les six items sans me redemander ce que je voulais dire. Le point est clos, aucun aller-retour n'est du |
| **L'issue 1 est refusee, avec son motif** | `marc-dev`, `theo-archi` | Qu'aucun mecanisme de declaration *"rang hors d'atteinte"* ne soit ecrit pour ce cas. La premisse est fausse, l'enumeration de la section 1 et la construction de la section 7 le montrent |
| **Le seuil de 10 % n'est pas deplace** | `theo-archi`, `yanis-qa` | Rien. Il est confirme tel quel |
| **Deuxieme occurrence en deux jours d'une liste fermee contre un seuil de distribution** (section 1) | `camille-po` | Un fait sur ma facon d'ecrire des conditions, pas une story. **Je m'engage a enumerer la population avant de fermer une liste de familles de distracteurs**, et cette phrase-ci est ce a quoi on pourra me rappeler |
| **La forme (d) n'est pas retiree**, et pourquoi ce chemin est le plus dangereux (section 4) | `camille-po`, `sofia-sm` | Que la voie *"retirer la forme"* ne soit pas reproposee comme un raccourci de perimetre |

---

## 11. Ce que ce document ne fait pas

- Il **ne prononce aucun Done pedagogique** sur `cm1-fractions` ni sur rien d'autre, et n'en anticipe
  aucun. Le paquet reste entierement a auditer.
- Il **ne fait bouger aucune ligne de la matrice**. La mention exacte exigee par `FRA-9` reste due le
  jour ou la ligne bougera.
- Il **n'efface aucun libelle**. `FRA-5` d'origine et `FRA-10` restent lisibles a leur place ;
  l'amendement est un ajout date.
- Il **ne deplace aucun seuil** et **n'assouplit aucune propriete**.
- Il **ne decide rien de technique** : le perimetre des garde-fous, leurs tables de dette et leurs
  mecanismes de declaration sont a `theo-archi`.
- Il **ne rouvre pas le perimetre de s24** et n'ecrit aucune story.
