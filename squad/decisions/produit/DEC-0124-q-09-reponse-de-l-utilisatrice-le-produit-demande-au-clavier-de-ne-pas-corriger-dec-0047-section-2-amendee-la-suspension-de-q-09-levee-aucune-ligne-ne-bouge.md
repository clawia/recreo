# DEC-0124 - `Q-09` : l'utilisatrice revient sur l'acceptation de la correction automatique du clavier ; `DEC-0047` section 2 est amendee, la suspension de `Q-09` (`DEC-0123`, section 7) est levee, aucune ligne ne bouge

- **Type** : decision de produit (changement de direction) et consignation d'une reponse de l'utilisatrice, mots relayes
- **Date** : 2026-10-21
- **Auteur** : camille-po
- **Sprint** : s57 (hors palier : la story associee est non engagee)
- **Competences programme visees** : `FRA-CE1-21`, `FRA-CE1-29`, `LVE-CM1-06` (lignes que le motif `R6` de `nadia-prof` retenait) ; `FRA-CP-09` et `MAT-CP-05` (lignes servies par saisie). **Aucune ligne de `squad/programme/MATRICE-couverture.md` ne bouge** : 152 / 200 sur la matrice a la date de cette DEC, inchange.
- **Statut** : **Acceptee** pour le sens (le produit veut que le clavier ne corrige pas). **Non appliquee** : le changement de code n'a pas de proprietaire avant que `theo-archi` ait rendu son avis et que `sofia-sm` ait engage la story (section 6). Tant que ce n'est pas fait, `saisie.html` ne porte aucun attribut de neutralisation et un enfant sur tablette est toujours corrige.
- **Remplace en partie** : `DEC-0047` section 2 (amendement date ajoute au bas de ce fichier-la). **Amende** : `DEC-0123` section 7 (amendement date au bas de ce fichier-la).
- **Lien** : `DEC-0047` (section 2) ; `DEC-0123` (section 7) ; `DEC-0040` (les deux appareils) ; `DEC-0046`, `DEC-0068` (premisse de l'adulte) ; `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/arbitrage-nadia-q05-q10.md` (section 4) ; `squad/conformite/ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md` (`R6`, `SAI-2`) ; `ADR-0058` (section 5) ; `adapters/adapter-web/src/main/resources/templates/fragments/saisie.html` (lignes 75 a 78) ; story `S57-SAISIE-CLAVIER-SANS-CORRECTION` (`docs/backlog/backlog.md`, non engagee).

## 0. Ce que sont ces mots, et ma lecture

Reponse relayee par l'agent lanceur le 2026-10-21. **Je ne l'ai pas lue de la main de l'utilisatrice.**

| Code | Ses mots (relayes) | Ma lecture |
|---|---|---|
| `Q-09` | *"Q9 il n'y aura pas de correction automatique du clavier"* | decision de produit : le champ de saisie demande au clavier de ne pas corriger le mot que l'enfant tape. **A confirmer par elle.** |

**Ce que ces mots departagent.** `DEC-0123` section 7.3 laissait deux lectures de sa phrase precedente (*"la saisie clavier ne corrige pas le mot donc plus de refus"*) : (a) le produit ne reecrit pas le mot, vrai et sans effet sur `R6` ; (b) le clavier de l'appareil ne corrige pas, ce qui contredit `DEC-0047` section 2. Ces mots-ci sont la lecture (b), et ils la disent comme une decision : *"il n'y aura pas"*.

**Ce que ces mots ne disent pas, et que je n'invente pas** : si elle veut dire *"on le demande au clavier"* (une demande, sans garantie) ou *"on le garantit"*. Le produit ne peut tenir que la premiere (section 4). Si elle voulait la seconde, c'est une autre question, et elle est a elle. Ils ne disent pas non plus que la saisie se limite au clavier physique : cette option, ecartee le 2026-08-25, ne revient pas par cette DEC.

## 1. Ce que `DEC-0047` section 2 devient

`DEC-0047` section 2 (2026-08-25) actait deux choses. Elle est lue ligne a ligne :

| Ce que la section actait | Etat apres cette DEC |
|---|---|
| La saisie vaut sur les deux appareils de `DEC-0040` | **Maintenu.** Rien dans la reponse ne le touche |
| *"Un clavier virtuel qui corrige et complete est accepte"* | **Remplace.** La correction automatique n'est plus acceptee |
| *"Les deux options qui l'auraient evitee (neutraliser la correction, limiter la saisie au clavier physique) sont ecartees"* | **Remplace pour la premiere** (neutraliser est maintenant voulu). **Maintenu pour la seconde** : la saisie ne se limite pas au clavier physique |
| La question *"clavier physique ou virtuel"* est fermee | **Maintenu**, avec une reponse differente de celle de 2026-08-25 |
| La consequence de la correction sur `SAI-2` (mode strict) est a `nadia-prof`, sans reponse presumee | **Inchange.** Que la demande de neutralisation ne soit pas une garantie garde `SAI-2` et `SAI-2c` utiles (section 4) ; la lecture de `SAI-2` reste a elle |

`DEC-0047` reste `Acceptee`. Elle porte un amendement date qui renvoie a cette DEC. Le commentaire de `saisie.html` (lignes 75 a 78, *"... que l'utilisatrice a acceptee le 2026-08-25"*) devient **faux** a partir de cette reponse ; sa correction est dans la story (section 6), pas ici (`adapters/` n'est pas a moi).

## 2. La suspension de `Q-09` est levee

`DEC-0123` section 7 suspendait `Q-09` a deux avis : celui de `nadia-prof` sur `R6`, et celui de `theo-archi` *"seulement si une neutralisation est voulue"*. **La condition de `theo-archi` est remplie : une neutralisation est voulue.** La suspension est levee, par amendement date de `DEC-0123` (statut de `Q-09`, section 7, section 8 ligne `R6`). Les deux avis ne sont pas rendus pour autant : ils deviennent des demandes en cours (section 5).

## 3. Ce que cela debloque, ligne par ligne, et ce que cela laisse

**Ce que `R6` etait.** *"Sur un clavier qui corrige, c'est l'appareil qui produit les lettres du mot"*, refus pose par `nadia-prof` le 2026-08-25, levable si *"la correction automatique cesse d'etre acceptee"* (`arbitrage-nadia-q05-q10.md`, 4.2). Sa condition, ecrite en termes de produit, est remplie. **Elle a ajoute** (4.2, point 3) qu'une neutralisation *"ne vaut que si elle est mesuree"*, sur les deux appareils, par `theo-archi`. **Ce que l'utilisatrice a dit n'est pas cette mesure**, et je ne la tiens pas pour faite.

**Ce que je debloque, pour toutes les lignes** : l'obstacle *"le produit accepte la correction"* disparait. **Ce que je ne debloque pas** : le verdict sur `R6`, qui reste a `nadia-prof` apres la mesure.

| Ligne | Etat aujourd'hui | Ce que cela debloque | Ce que cela laisse |
|---|---|---|---|
| `FRA-CE1-21` (mots invariables) | plafond `en cours` ; paquet `ce1-mots-invariables` non servi, en-tete `DEGRADE` | la condition de produit de `R6` est remplie, ce qui ouvre ses conditions de Done en saisie (`arbitrage-nadia-q05-q10.md`, 4.3), plafond `couvert-valide` avec sa mention, prix **1,0 qu'elle a elle-meme ecrit** | **a `nadia-prof`** : lever `R6` apres la mesure ; le prix (je ne le reecris pas) ; le verdict. **A `sofia-sm`/composition** : l'instance et le palier, hors palier 3. **A `camille-po`** : une story de paquet quand `R6` sera levee, pas avant |
| `FRA-CE1-29` (dictee) | `non couvrable en l'etat`, nature reversible | les deux obstacles que je portais sont leves : la premisse de l'adulte (`DEC-0123` section 5) et la condition de produit de `R6` | **a `nadia-prof`** : lever `R6`, relire sa ligne, ecrire ses conditions (aucun paquet n'existe, aucune condition de dictee n'est ecrite) ; amender `TRV-CE1-1`. **A `sofia-sm`** : l'amendement de `CLAUDE.md` si elle juge que le CE1 doit y figurer. La cellule de matrice **ne change pas** |
| `LVE-CM1-06` (ecrire un mot anglais) | `non couvert`, refusee a la saisie (`R6`) ; paquet `cm1-anglais-ecrire-mots` non servi, en-tete `DEGRADE` | la condition de produit de `R6` est remplie | **a `nadia-prof`** : si un champ neutralise suffit pour un mot anglais, et la forme du paquet. La premisse de l'adulte **ne s'applique pas** (la voix n'est pas la competence). Cellule **inchangee** |
| `FRA-CP-09` (`saisie-mot-dicte-cp`) | `couvert-valide` (2026-09-01) | la raison de l'omission de `R6` a s45 est reglee du cote produit | **a `nadia-prof`** : fermer elle-meme l'omission qu'elle a ecrite (s45, 5.3) et dire si son verdict tient sur un champ neutralise ou demande un rejeu. **Je ne dis ni l'un ni l'autre** |
| `MAT-CP-05` (`saisie-complements-10-cp`) | `couvert-valide` | le champ porte les memes attributs, le contenu est numerique | **a `theo-archi`** : si les attributs ont un effet sur un champ de chiffres et ce qu'il mesure ; **a `nadia-prof`** : si un rejeu est du |

**Ce que `R6` ne decide pas** : les refus `R1` a `R5` de `ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md` sont des motifs distincts, **inchanges**. Une ligne refusee a la saisie pour un autre motif que `R6` reste refusee.

### 3.1 Jeux a saisie ecrite : ce que j'ai trouve

Verifie le 2026-10-21 : `grep -c "mecanique: saisie" adapters/adapter-content/src/main/resources/catalogue.yaml` rend **2** ; `grep -rl "^type: saisie" adapters/adapter-content/src/main/resources/contenu` rend **2** fichiers, les memes.

| Niveau | Jeux servis a saisie | Paquets prets a saisie, non servis |
|---|---|---|
| CP | `saisie-mot-dicte-cp` (`FRA-CP-09`), `saisie-complements-10-cp` (`MAT-CP-05`) : **2, touches par le changement de champ** | aucun |
| CE1 | **aucun** | `ce1-mots-invariables` (`FRA-CE1-21`) ; la dictee (`FRA-CE1-29`) n'a pas de paquet |
| CM1 | **aucun** | `cm1-anglais-ecrire-mots` (`LVE-CM1-06`) |

Les deux jeux servis partagent **un seul champ** (`fragments/saisie.html`, ligne 87) : une demande de neutralisation, si elle est posee, vaut pour tous les jeux a saisie, presents et futurs. **Je n'ai pas lu les 61 paquets de `contenu-repris/`** ; `grep -rl "type: saisie" contenu-repris` rend 0.

## 4. La limite, ecrite avant la mise en oeuvre

Citation relayee par l'agent lanceur, **non verifiee par moi** (je n'ai pas lu MDN) : `autocorrect="off"` est supporte, *"Baseline 2026, newly available since September 2026, might not work in older devices or browsers"* ; la correction est fournie par l'appareil, et `spellcheck` n'est qu'un indice. `theo-archi` la verifie avant de s'y appuyer.

Consequences pour le produit :

1. **Un attribut est une demande faite au clavier, pas une garantie.** Un appareil ancien, un navigateur ancien ou un clavier installe a part peut l'ignorer (`arbitrage-nadia-q05-q10.md` 4.2, point 3 ; `ADR-0058` section 5). **Le produit ne peut pas annoncer que le clavier ne corrige pas ; il peut annoncer qu'il le demande.**
2. **La phrase de l'utilisatrice est donc une cible de produit, non une propriete verifiable par le serveur** : le serveur ne connait que la chaine recue (`ADR-0058`, section 5).
3. **Ce qui protege quand la demande est ignoree existe deja** : `SAI-2c` montre la chaine jugee a la reussite d'un item en forme exacte (`saisie.html`, lignes 108 a 110). Cette DEC n'y touche pas.
4. **Ce que je n'ecris pas** : un message a l'adulte sur les reglages du clavier de l'appareil. L'adulte ne recoit d'instruction du produit qu'aux lignes ou la voix est la competence (`DEC-0047` section 3, `DEC-0123` section 5). Si la mesure montre un appareil qui ignore la demande, c'est une question a elle, pas une consigne ajoutee par moi.

## 5. Ce que cette DEC laisse

| A qui | Quoi | Condition posee par qui |
|---|---|---|
| `theo-archi` | **Avis sur la mise en oeuvre dans `saisie.html`** : quels attributs (`autocorrect`, `spellcheck`, `autocapitalize`, `autocomplete` deja present) ; ce que chacun obtient sur **les deux appareils de `DEC-0040`, mesure sur ces appareils** et non lu dans la specification, avec la version du navigateur et de l'appareil que la mesure rapporte ; ce qu'il ne garantit pas ; l'amendement de la premiere puce de `ADR-0058` section 5 (*"Je ne reintroduis aucune neutralisation"*), qui reposait sur la reponse du 2026-08-25 | `nadia-prof`, `arbitrage-nadia-q05-q10.md` 4.2 point 3 |
| `nadia-prof` | Lire `R6` apres la mesure : levee, levee sous condition, ou tient ; fermer l'omission de s45 5.3 ; `SAI-2` ; prix et mentions de `FRA-CE1-21`, `FRA-CE1-29`, `LVE-CM1-06` ; dire si `FRA-CP-09` et `MAT-CP-05` demandent un rejeu | elle seule |
| `sofia-sm` | Demander les deux avis ; engager la story le jour ou `theo-archi` a dit la taille ; l'amendement de `CLAUDE.md` si elle le juge utile (`DEC-0123` section 5.4) | n/a |
| `camille-po` | La story (ecrite) ; la matrice **apres** un verdict, jamais avant | n/a |

## 6. Routage, avec proprietaire

| Geste | Proprietaire | Etat |
|---|---|---|
| Story `S57-SAISIE-CLAVIER-SANS-CORRECTION` (non engagee, prix 0 hors `PAR`) | `camille-po` : ecrite en fin de `docs/backlog/backlog.md` | fait |
| Avis et mesure sur les deux appareils | `theo-archi` | a demander par `sofia-sm` |
| `R6`, `SAI-2`, omission de s45 | `nadia-prof` | a demander par `sofia-sm`, apres la mesure |
| Engagement de la story, agent de code | `sofia-sm` | a son jugement |

**Tant que la story n'a pas de proprietaire de code, cette decision est prise et non appliquee.** Je le dis ici pour que la ligne `FRA-CE1-21` ne passe pas pour debloquee : seule sa condition de produit l'est.

## 7. Report compte

`Q-09` a ete posee a l'utilisatrice et suspendue le 2026-10-21 (`DEC-0123`), reponse le meme jour : **aucun report**. Les lignes `FRA-CE1-21`, `FRA-CE1-29`, `LVE-CM1-06` attendaient `R6` depuis le 2026-08-25 pour la regle, et depuis le 2026-10-01 pour `LVE-CM1-06` (s45). Cette DEC ne les compte pas comme report de ma part : la condition etait a l'utilisatrice.

## 8. Ce que cette DEC ne decide pas, et ne change pas

- **Aucune ligne ne devient `couvert-valide`**, aucun prix n'est ecrit, aucune instance affectee, aucun Done pedagogique prononce.
- La matrice (152 / 200), `planning.md`, `composition-palier-3.md`, `DEC-0120` : non touches.
- `saisie.html` et tout fichier sous `domain/`, `application/`, `adapters/`, `bootstrap/` : non touches. `CLAUDE.md` : non touche.
- `ADR-0058` n'est pas amendee par moi : c'est `theo-archi`.
- Les refus `R1` a `R5` et la regle de l'adulte (`DEC-0123` section 5.2, borne aux deux lignes de CE1 et aux lignes de CP ou la voix est la competence) : inchanges.
- **Aucun identifiant de condition ou de critere du type `XX-n` n'est alloue** : tous ceux qui sont cites existent deja. Les deux identifiants neufs sont ce numero de DEC (`DEC-0124`, derniere en date `DEC-0123`) et le nom de story `S57-SAISIE-CLAVIER-SANS-CORRECTION`, sans suffixe numerique.

## Amendement du 2026-10-21 (`camille-po`, s57) - `R6` est leve par l'arbitrage de l'utilisatrice

**Mots de l'utilisatrice**, relayes par l'agent lanceur, non lus de sa main : *"a toujours a oui et b a non sur tablette uniquement, pour moi c'est bon R6 peut etre leve"*.

**Faits** (`squad/sprints/s57-version-cp-ce1-cm1-trois-instances/arbitrage-nadia-q05-q10.md`, sections 7.5 et 8.1) : la verification porte sur la tablette seule ; l'ordinateur n'est pas mesure ; le jar joue n'est pas identifie ; (a) oui, le champ n'est pas corrige ; (b) non, des mots sont proposes au-dessus du clavier.

**Ce que `nadia-prof` en a fait** (section 8, commit `b5dd1d4c`) : `R6` est leve, sur la decision de l'utilisatrice, qu'elle lit telle quelle. Elle nomme une reserve, **non bloquante**, datee du 2026-10-21, mot pour mot : *"sur la tablette, des mots sont proposes au-dessus du clavier ; un enfant qui en touche un n'a pas ecrit le mot"*. Elle exige que la cellule de matrice la porte pour `FRA-CE1-21`, `FRA-CE1-29` et `LVE-CM1-06`, et pour `FRA-CP-09` (8.4) ; l'omission de s45 5.3 est fermee et le Done de s26 tient sans rejeu de contenu.

**Effet** : les sections 3 a 6 qui attendaient l'avis de `nadia-prof` sur `R6` sont rendues. `Q-09` n'est plus *"repondue, avis en cours"* : elle est close. La reserve est reprise dans les quatre cellules de `squad/programme/MATRICE-couverture.md`. **Aucun statut ne change** : `FRA-CP-09` reste `couvert-valide`, les trois autres lignes gardent leur statut et leur plafond. Compteurs inchanges, 152 / 200.

**Ce que cet amendement ne fait pas** : il ne leve pas la limite ecrite par `nadia-prof` (l'ordinateur n'est pas mesure) ; il n'ouvre aucune story ; il ne demande pas la suppression des mots proposes, question qui releve de `theo-archi`. `DEC-0124` reste `Acceptee`.
