# [Sprint s25] Rapport de sprint - le premier palier visuel

- **Sprint** : s25, `s25-le-premier-palier-visuel`.
- **Debut** : 2026-08-31 18:24 +0200. **Fin** : 2026-09-01 09:45 +0200. **Duree effective** :
  **15 h 21 min**.
- **Redige par** : `sofia-sm`, casquette Scrum Master. **Ce rapport ne prononce aucun Done.**

---

## 1. L'increment livre, en une phrase

> **`recreo` sait desormais montrer une image a l'enfant : quatre jeux neufs sont servis, dont deux
> illustres, et la couverture du programme passe de 27 a 31 lignes sur 41.**

**Ce que le palier 1 est** : une **image fixe**, des **propositions en texte**, et la mecanique de
choix qui existait deja. **Ce qu'il n'est pas** - et c'est ce qui a rendu le sprint tenable :
*"ni une forme d'item de plus, ni une mecanique de plus"* (`theo-archi`, `ADR-0073`). La facon dont
l'enfant repond n'a pas change.

---

## 2. Les deux Done

### Done technique - acquis

```
bash outils/mvn-jdk21.sh -B clean verify
```

> **BUILD SUCCESS**, cinq modules, **1 018 tests, 0 echec, 0 erreur, 0 ignore.**

*Methode : somme des lignes de resultat **de module** (`surefire` et `failsafe`), jamais des lignes
par classe. Perimetre : le reacteur complet a la racine, donc **aucun `-pl` et aucune question de
`-am`**. JDK d'execution : **21.0.10**, rendu par l'enveloppe et confirme par le journal du build.*

**Mesure par moi sur l'arbre de cloture, puis reproduite independamment par le
`relecteur-pedagogique` et par `yanis-qa` sur le meme commit.** Depart a `130ba64` : 926 tests.

`yanis-qa` ajoute, et cela fait partie du Done : **aucun test ignore ou desactive** (`skipped` vaut 0
sur 140 rapports, sur trois executions), **aucun test instable**, **aucune execution relancee pour
obtenir un vert**.

### Done pedagogique - prononce par `nadia-prof`, et par elle seule

> **Quatre `VALIDE`.** Le refus de `bandit-manchot-substituts-cm1` est **leve**, les deux reserves
> visuelles sont **levees**.

Cinq fiches de conformite : `figures-planes-cp`, `monnaie-cp`, `semaine-cp`, `substituts-cm1`, plus
`texte-narratif-cp` sur un paquet de s24 que `marc-dev` avait signale de lui-meme.

**Je rapporte ce Done, je ne le prononce pas, et rien de ce rapport ne le fonde.**

---

## 3. La couverture du programme

| | Avant s25 (`130ba64`) | Apres `DEC-0066` | **A la cloture** |
|---|---|---|---|
| `couvert-valide` | 27 | 27 | **31** |
| Denominateur | 43 | 41 | **41** |
| Taux | 62,8 % | 65,9 % | **75,6 %** |

*Methode : comptage de la colonne `Statut` des quatre tableaux de matiere de
`squad/programme/MATRICE-couverture.md`. Aucun module compile, donc aucune question de `-am`.
Ecrite par `camille-po`, **recomptee par moi sur l'arbre de cloture** : les deux comptes concordent.*

**Les deux mouvements sont de nature differente et ne s'additionnent pas** : `DEC-0066` retire deux
lignes du denominateur sans qu'un enfant apprenne quoi que ce soit ; les quatre lignes suivantes sont
un apprentissage. **`DEC-0066` demande que toute lecture de la serie de couverture soit coupee au
2026-08-31, et je le repete ici plutot que de le laisser dans la decision.**

**Quatre lignes distinctes** : figures planes CP, monnaie CP, la ligne de l'heure par sa moitie
*se reperer*, les substituts CM1.

> **C'est exactement le plafond que `DEC-0065` avait pose a l'ouverture**, en ecrivant qu'il
> *"suppose que quatre paquets passent la porte du premier coup, ce qui n'est jamais arrive sur ce
> produit"*. **Il a ete atteint** - avec un refus leve en cours de sprint, ce qui n'est pas
> *"du premier coup"*.

---

## 4. Les decisions du sprint - liste complete

| Decision | Titre | Statut a la cloture |
|---|---|---|
| **`DEC-0065`** | Perimetre de s25 : couverture maximale, la reserve arbitree ne vaut qu'une ligne | **Acceptee**, **amendee deux fois** le 2026-08-31 - le palier 1 ouvert, puis la monnaie rouverte en illustre |
| **`DEC-0066`** | Reclassement de deux lignes en `non couvrable en l'etat` sur arbitrage de l'utilisatrice | **Acceptee** |
| **`DEC-0067`** | Etat du produit a la cloture de s25 : la reserve retombe a une ligne, rang propose pour s26 | **Acceptee** |
| **`ADR-0073`** | Un item declare l'illustration qu'il montre par un identifiant, et la preuve change de source | **Accepte**, **amende** le 2026-08-31 - un chiffre trace en geometrie n'est pas du texte |
| **`ADR-0071`** | La version de JDK citee par une mesure est celle que la machine virtuelle rapporte | **Accepte**, **amende** le 2026-08-31 - le verdict de l'enveloppe changeait de sujet |

**Deux fichiers de process amendes** : `.claude/agents/relecteur-pedagogique.md` (la convention
`MASTER`, dette n. 7) et `squad/README.md` section 2 bis (la forme 2 retablie).

---

## 5. Les obstacles

**Resolus - onze fermes par un fichier qui existe et que j'ai lu :**

- **la hauteur declaree d'un dessin, et ce n'etait pas une contradiction** : `DES-3` corrigeait
  `DV-1`, et un garde-fou avait fige la lecture d'avant. Je l'avais routee a deux agents ; son
  autrice l'a trouvee chez elle ;
- `MON-1`, `ADR-0041` contre `MON-3`, le trait vertical de la bourse, `DV-3` - tous tranches par
  `nadia-prof` dans ses fiches ;
- le juge de s22 que personne ne lancait, **troisieme occurrence de cette famille** ;
- l'horodatage de mon planning, que l'outil de tokens ne lisait pas ;
- `cp-texte-narratif` ; `SUB`, refuse puis rejoue apres correctif ;
- le rang des trois lots de dette et le second report du score, poses par `DEC-0067` ;
- **l'obstacle 12**, ouvert depuis s23 : `yanis-qa` en etablit la cause - quatre classes portent deux
  annotations de test, et une agregation par nom de classe perd exactement 7. **Artefact de methode,
  pas defaut de build** ;
- **la forme 2 de `squad/README.md`**, qui chassait l'attribution a l'utilisatrice.

**Un ferme parce qu'il etait faux, et l'erreur est de moi** : j'avais rapporte que la toolchain du
poste declarait 21 en designant un JDK 25. **C'etait un faux rouge** - le poste porte deux fichiers
de toolchains et l'enveloppe jugeait celui que Maven ne lit pas. Les mesures anterieures du sprint
ont bien tourne sur un JDK 21.

**Ouverts, avec leur proprietaire.** Tableau complet : `review.md` section 13.9.

| Obstacle | Proprietaire | Severite |
|---|---|---|
| **`ADR-0008` ne voit ni un attribut d'evenement ni un element de script** dans un gabarit servi - 0 classe rouge sur 1 018 tests, **deuxieme sprint** | `theo-archi` | **haute** |
| **`CON-6` (b) est rouge sur `cp-monnaie`, pas vide** - une clause non retroactive rendue opposable a un increment deja valide | `nadia-prof`, `theo-archi` | **haute** |
| **`CON-6` (b') est insatisfaisable telle qu'ecrite** ; restreinte au domaine juste, elle est verte, non vide, et rouge sur l'etat fautif | `nadia-prof`, `theo-archi` | **haute** |
| Tout encodage de (b) ou (b') doit declarer la taille de son domaine et echouer si elle est nulle | `theo-archi` | moyenne |
| Le garde-fou *"un rang suffit"* et sa clause de non-vacuite | `theo-archi`, rang `camille-po` | moyenne |
| `Question` : six composantes optionnelles sur dix - **dette n. 6** | `theo-archi` | moyenne |
| L'URL d'illustration porte l'identifiant de session, ce qui defait la mise en cache | `theo-archi` | moyenne |
| **Une ligne du programme est injouable sans la vue** - demontre par le paquet servi, plus suppose | `camille-po`, puis l'utilisatrice | **remonte** |
| `DES-6`, le second plancher de `DES-3`, `MON-6`, `SUB-8` : ecrits, executes par rien | `ines-design`, `nadia-prof`, `theo-archi`, `marc-dev` | basse |
| Quatre dossiers de sprint invisibles a l'outil de tokens | `sofia-sm` | basse |
| La formule de JDK disqualifiee, 31 occurrences chez six auteurs | six auteurs | basse |

---

## 6. Le fait de sprint, et il n'est pas de moi

`yanis-qa` a nomme la propriete commune aux **sept** defauts du sprint :

> **Ils naissent tous de l'intervalle entre l'ecriture d'une clause et sa mesure sur l'objet
> motivant. Juste en intention, faux en extension.**

`CON-6`, ecrite pour attraper un paquet **que sa clause laissait passer a exactement 50 %** ; le
garde-fou de geometrie, qui verifiait **dans le mauvais sens** ; l'enveloppe, **faux rouge et faux
vert** ; `CPL-3`, troisieme et quatrieme recurrence. **Trois ont ete trouves par leurs propres
auteurs. Aucun par un outil.**

**Ce qui a fonctionne contre cela est une chaine et non un controle** : `relecteur-pedagogique`
trouve en relisant ce qu'aucun test ne voit ; `nadia-prof` ecrit la clause ; `sofia-sm` mesure sa
conformite avant d'ecrire, obtient **100 %**, **s'en mefie au lieu de s'en prevaloir** ; `nadia-prof`
verifie et trouve pire ; `lea-dev` propose une propriete que `nadia-prof` **prefere a ses propres
bornes** ; `yanis-qa` corrige les deux dernieres clauses. **Quatre agents, aucun n'ayant eu raison
seul.**

**Ma faute du sprint** : *"j'ai lu un rouge comme une preuve parce qu'il confirmait ce que
j'attendais."* La retro la porte, section 3.1.

---

## 7. Consommation de tokens

`squad/sprints/s25-le-premier-palier-visuel/RAPPORT-tokens.md`, produit par
`python outils/rapport-tokens.py --sprint s25`, **fenetre 2026-08-31 16:24 a 2026-09-01 07:45 UTC**
(les deux horodatages de ce rapport, convertis). `squad/rapports/JOURNAL-tokens.md` est a jour.

**Les dix lignes sont mesurees** - les neuf agents et le fil principal. **Aucune ligne "aucune
invocation mesuree" sur cette fenetre**, ce qui n'etait pas le cas au planning, ou trois agents
n'avaient pas encore ete invoques.

**Je ne recopie aucun chiffre a la main et je n'en commente aucun** : les quatre compteurs sont
separes par construction, et les additionner donnerait un classement faux.

---

## 8. Ce que je route pour s26, sans l'arbitrer

`camille-po` pose le rang dans **`DEC-0067`** : `DV-4` en tete - la borne de longueur d'une
proposition, qui a ferme `S25-LIS-1` -, puis le score parfait par elimination, **le trou du
JavaScript elargi** par la mesure de `yanis-qa`, le garde-fou *"un rang suffit"* avec sa clause de
non-vacuite, et trois identifiants executes par rien.

**Et le fait de perimetre le plus lourd, qui reste routee a l'utilisatrice** : **la source de la voix
- cinq lignes, le plus gros gisement restant.**

---

## 9. La fusion sur `round-two` - je ne la fais pas

**Je laisse la branche `s25-sofia-review` a l'utilisatrice, et le motif est celui de s24, inchange.**

Ce sprint a produit **trois documents de ma main qui jugent le sprint** - `review.md`, `retro.md`, ce
rapport - **et deux fautes de ma main que ces memes documents rapportent** : un faux rouge lu comme
une preuve, et une convention dont ma transcription avait chasse une forme. **Fusionner moi-meme
serait etre juge et porte, dans le sprint ou j'ai le plus de raisons de ne pas l'etre.**

**Ce qui est pret** : la branche est poussee, la porte technique est verte et mesuree trois fois sur
le meme commit, le garde-fou de chemins est vert sur l'index complet, les quatre ceremonies sont
ecrites, et **aucun de mes documents n'attend quoi que ce soit.**
