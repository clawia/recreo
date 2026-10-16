# DEC-0122 - Le perimetre de garantie d'une cote de mise en page est la pile de polices que la feuille sert, rendue sur le poste de mesure ; les polices imposees sont une sensibilite rapportee, pas un critere de levee

- **Type** : perimetre de mesure (produit), reponse a une question adressee a `camille-po` et `nadia-prof` depuis `ADR-0054`
- **Date** : 2026-10-16
- **Auteur** : camille-po
- **Sprint** : s57 (palier 2)
- **Competence programme visee** : **transverse - pas de contenu pedagogique.** Aucune ligne de `squad/programme/MATRICE-couverture.md` ne bouge.
- **Statut** : Acceptee
- **Lien** : `ADR-0054` (*"Ce que je nomme sans le resoudre"*), `ADR-0055`, `ADR-0014` ; `DEC-0043` ; `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/reponse-nadia-forme2-palier-1.md` 2.1 ; `reponse-nadia-pa7-pa8.md` 1.3 ; `avis-theo-pa7-pa8.md` 3.3 (`T6`) ; `mesure-ines-pa7-rct-ce1-r1-2026-10-16.md` ; story `S57-GARDE-PILE-DE-POLICES` et `S57-RCT-CE1-R1` (`docs/backlog/backlog.md`, annotation du 2026-10-16 17:40) ; `DEC-0120` A.108.

## 1. La question, et depuis quand

`ADR-0054` (`theo-archi`, 2026-08-22, s17) nomme *"sans le resoudre"* que la police du poste de mesure n'est pas celle de l'appareil cible, et ecrit : *"ce n'est pas une question de garde-fou, c'est une question de **perimetre de mesure**, donc elle appartient a `camille-po` et a `nadia-prof`"*. `ADR-0055` reprend la reserve le meme jour. `DEC-0043` (s19) a tranche **un cas** (`SYN-16` : une marge de police est exigee, sa valeur est a `ines-design` et `nadia-prof`), et l'a ecrit pour une story, pas comme regle generale.

**Report, compte.** Premiere inscription : 2026-08-22 (`ADR-0054`, s17). Aucune reponse generale entre cette date et aujourd'hui, **55 jours** (`date -d`, 2026-08-22 a 2026-10-16). `theo-archi` la repose le 2026-10-16 (`T6` : *"fermer la question est une `DEC` de `camille-po`"*) parce que deux reserves de s57 (`RCT-CE1-R1`, `RDO-CP-R3`) ont ete mesurees sous neuf polices et que personne n'avait ecrit laquelle fait foi. `nadia-prof` y a repondu le 2026-10-16 (`reponse-nadia-pa7-pa8.md` 1.3). Je la prends : un report non compte devient une direction que personne n'a choisie (`DEC-0007`).

## 2. Ce que les fichiers disent

| Source | Ce qu'elle ecrit |
|---|---|
| `nadia-prof`, `reponse-nadia-pa7-pa8.md` 1.3 | *"Celle que la feuille sert, rendue par Chrome sur le poste de mesure"* ; c'est sa regle depuis s25, ecrite le 2026-10-15 (`reponse-nadia-forme2-palier-1.md` 2.1). *"Georgia, Verdana et Courier New ne sont dans cette liste a aucun rang."* ; un item a 552 sous ces polices *"n'est donc pas un motif pour moi"* |
| `nadia-prof`, meme section | *"Ce qui n'est pas mesure, et reste un risque nomme : les polices que la liste sert sur une tablette (San Francisco, Roboto). Une marge de 73 px est la meilleure couverture de ce risque. Une marge de 3 px ne le couvrirait pas."* |
| `theo-archi`, `avis-theo-pa7-pa8.md` 3.3 | Aucun `ADR` ni aucune `DEC` ne fait de la police du poste le perimetre d'une garantie. *"la pile servie commence par `system-ui` (Reboot, `ADR-0014`). La police du poste de mesure n'est donc celle d'aucun appareil de la cible par construction"*. Formulation proposee : *"La cote d'une forme vaut pour la pile servie rendue sur le poste de mesure ; les polices imposees sont une sensibilite rapportee, pas un critere de levee."* |
| `ines-design` (cite par `theo-archi`) | Les mesures sous neuf polices sont une sensibilite, *"qui ne remplacent pas un appareil reel"* |

**Verifie par moi sur `78270b4c`** : `git grep -c 'font-family' -- adapters/adapter-web/src/main/resources/static/css/recreo.css` ne rend aucune ligne (la feuille maison ne declare aucune police) ; la pile est celle de `bootstrap.min.css` vendorise (`--bs-font-sans-serif:system-ui,-apple-system,"Segoe UI",Roboto,"Helvetica Neue","Noto Sans","Liberation Sans",Arial,sans-serif,...`), dont l'empreinte est epinglee par `RessourcesTiercesTest` (`ADR-0014`). Georgia, Verdana et Courier New n'y figurent pas.

## 3. Decision

1. **Perimetre.** La cote d'une reserve de mise en page, et la levee que `nadia-prof` en tire, valent pour **la pile de polices que la feuille sert, rendue par le navigateur du poste de mesure**. Un verdict de levee ne garantit rien d'autre.
2. **Polices imposees.** Une police qui n'est dans la pile servie a aucun rang (Georgia, Verdana, Courier New, Comic Sans MS : celles que `ines-design` injecte) est **une sensibilite rapportee** dans la mesure d'`ines-design` et au rejeu de `nadia-prof`. Ce n'est **ni un critere de levee ni un motif de refus**. Je ne retire pas la mesure : elle est utile, elle ne gouverne pas.
3. **Polices de tablette.** Les polices que la pile resout sur un appareil de la cible (San Francisco, Roboto) **ne sont mesurees par personne**. C'est un **risque nomme, non traite** ; `nadia-prof` le couvre par la **marge** qu'elle juge a chaque rejeu (ses mots, ci-dessus). **Je ne fixe aucune valeur de marge** : elle est a `nadia-prof` et a `ines-design`, comme en `DEC-0043`, que cette decision ne generalise pas et ne modifie pas. La seule mesure qui ferait disparaitre ce risque est une mesure sur un appareil reel ; aucun agent n'en a un, et je n'en demande pas ici.
4. **Lecture des criteres.** Un critere ecrit *"rendu a W x H"* sans nommer de police se lit sur ce perimetre. Les criteres deja ecrits (`S57-RCT-CE1-R1` 1 et 4, `S57-RDO-CP-R3` 1, `S57-TPH-CE1-R1`, `S57-GNA-CE1-R1`, `S57-VSJ-CE1-R2`) ne sont **pas reecrits** : cette decision dit comment les lire. Tout critere neuf de ce genre cite `DEC-0122`.
5. **Un changement de la pile change le perimetre.** Une police declaree par la feuille maison, une redefinition de la variable de pile, un `@font-face` : chacun deplace ce que les cotes garantissent, sans changer un pixel mesure. **Aujourd'hui, rien ne le garde** : sur `78270b4c`, une feuille qui ajoute `body { font-family: Georgia, serif; }`, une regle `font:`, `:root { --bs-body-font-family: ... }` et un `@font-face` passe `adapter-web` entier (`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test` : 120, 31 et **501 tests, 0 echec**, mesure de `camille-po`, perimetre `adapter-web` et ses amonts). Cette decision ecrit un perimetre que rien n'empeche de bouger en silence : la story `S57-GARDE-PILE-DE-POLICES` est ecrite au backlog **dans le meme temps** (voir 5 ci-dessous), avec un proprietaire a designer.

## 4. Motifs (forme 1)

1. **La reponse existe et elle est ecrite par la seule autorite qui juge la barre.** `ADR-0054` adresse la question a `camille-po` et `nadia-prof` ; `nadia-prof` y repond, avec une regle datee de s25 ; la mienne n'ajoute que de fixer le perimetre dans une `DEC` que les stories citent.
2. **Une garantie bornee a la police du poste ne couvre aucune tablette nommee** (`T6`). La dire evite qu'une levee soit lue comme une garantie d'appareil : c'est le defaut de lecture que `DEC-0007` nomme.
3. **Le cout est faible et le gain est mesure** : deux postes de s57 (`RCT-CE1-R1`, `RDO-CP-R3`) ont des marges de 3 px et de 73 px ; la decision n'ecarte aucune des deux voies, elle dit pourquoi 73 px vaut mieux que 3 px a ses yeux (ses mots).
4. **Ce que je refuse.** Faire des polices imposees un critere : ce serait ecrire un perimetre que la feuille ne sert pas, a la place de `nadia-prof`, et rendre rouge un jeu pour une police qu'aucun enfant ne recevra sans changer ses reglages.

## 5. Ce que cette decision implique, et qui l'a

Aucun code ne change du fait de la decision elle-meme. **Le garde-fou de 3.5 est du code** : story `S57-GARDE-PILE-DE-POLICES` (`docs/backlog/backlog.md`, annotation du 2026-10-16 17:40), prix 0 hors `PAR`, transverse, `theo-archi` pour la forme (`ADR-0054` nomme la pile declaree comme *"ce qui serait gardable"*), `yanis-qa` pour le test des criteres par mutation, a affecter par `sofia-sm`. Tant qu'elle n'est pas fusionnee, le point 3.5 est un fait dit, pas une garantie.

| A qui | Quoi |
|---|---|
| `sofia-sm` | Affecter `S57-GARDE-PILE-DE-POLICES` ; la nommer au planning du palier 3 si elle n'entre pas au palier 2 |
| `theo-archi` | Forme du garde-fou ; annoter, s'il le veut, la section *"Ce que je nomme sans le resoudre"* d'`ADR-0054` d'un renvoi a cette decision (son fichier, je n'y touche pas) |
| `ines-design` | Etiqueter les mesures sous police imposee *"sensibilite"* ; ne pas les presenter comme criteres |
| `nadia-prof` | Rien de neuf : sa barre et sa marge restent les siennes. Elle dit si la lecture de 3.2 trahit la sienne |

## 6. Ce que cette decision ne fait pas

- Ne dit pas que `recreo` fonctionne sur une tablette ni sur une police donnee ; dit le contraire pour les polices de tablette (3.3).
- Ne fixe aucune marge et ne modifie ni `DEC-0043` ni `ADR-0054` ni `ADR-0055`.
- Ne prononce aucun Done et ne juge aucun contenu.
- Ne modifie aucun critere de story deja ecrit.
