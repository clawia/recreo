# lessons.md - regles tirees des corrections

Fichier cree a la racine par `sofia-sm` le 2026-10-11, a la retro de s56, et deplace sous `squad/` le 2026-10-12 : il n'existait pas (`find . -iname lessons.md`
ne rendait rien, `git log --all` non plus ; le planning de s56 le disait deja). Une ligne par regle :
la regle, puis la correction qui la fonde, avec sa source. Le detail et les mesures sont dans
`squad/sprints/s56-paquet-art-cp-10-et-francais-ce1-13/retro.md`. Une regle qui devient une regle de
process s'ecrit dans `squad/README.md` ; ce fichier ne remplace ni les `ADR`, ni les `DEC`.

## s56

- **Un "non ecrit" cite la commande qui l'a verifie et le commit sur lequel elle a tourne.**
  `lea-dev` a ecrit deux fois que le critere de la marque n'etait pas ecrit (`rapport-lea-dev.md`
  sections 8 et 9.6) ; `git merge-base --is-ancestor f3de5e58 8d40c4a5` sort 0 : le critere etait dans
  sa propre base.
- **Une cote lue a l'oeil n'est pas recopiee comme un chiffre dans un paragraphe qui conclut ; elle est
  routee vers qui mesure au `getBoundingClientRect()`.** L'indice a l'echec : y = 740 environ a l'oeil
  (`rapport-lea-dev.md` 11.3), 626 a 676 mesure (`FICHE-bandit-manchot-rapprocher-deux-oeuvres-cp.md`
  section 3, point 2 ; `Q7-releve-gabarit-livre.md`).
- **Une condition ecrite "tenue" dans un arbitrage prealable cite la cote mesuree sur l'objet qui sera
  servi, pas sur son vehicule.** *"Mesure : tenu avec O1 et le meuble"* lisait le bas du contenu d'un
  vehicule statique ; l'entree de catalogue a ete ecrite contre cette barre ; `nadia-prof` l'a corrigee
  elle-meme (fiche, motif du refus).
- **Le test de non-exclusion d'un garde-fou a un oracle ecrit a part, plus etroit que le predicat
  d'exclusion.** Juge par son propre predicat, il ne peut pas rougir (`ADR-0104`, amendement du
  2026-10-11, point 1 : 0 echec sur 4 quand le predicat est elargi a toute `Question`).
- **Une mutation ne se lit qu'apres deux controles : le motif a ete trouve, et le nombre de tests lances
  est superieur a zero.** Trois faux verts dans s56, chacun vu par son auteur : `bash` de WSL qui
  n'executait rien (`rapport-qualite.md` section 3), espace insecable non reconnue par le script de
  mutation chez `marc-dev` deux fois (`rapport-marc-dev.md` sections 3 et 16 : 22 verts, puis 18).
- **Un chiffre de capacite "consomme" nomme les postes qu'il additionne.** `cloture-nadia.md` section 6
  donne 5,75 ; les arbitrages (1,0), la forme (0,25) et le choix des oeuvres (0,25), livres eux aussi,
  n'y sont pas : 7,25 en les comptant.
- **Deux perimetres de mesure declares identiques se demontrent par la liste des modules, pas par la
  phrase "meme perimetre".** `planning.md` de s56 ecrivait les deux (relecture-cloture.md P3) ; la
  demonstration est dans `pom.xml` (cinq modules) et `bootstrap/pom.xml` (les quatre autres en
  dependance).
- **Une porte de sortie ne depend pas d'un artefact que l'etape qu'elle precede produit.** Le planning
  de s56 demandait `Q7` avant l'entree de catalogue ; `Q7` se mesure sur le jar qui sert cette entree
  (`Q7-releve-gabarit-livre.md` sections 0 et 1). Porte de ma main, insatisfiable telle qu'ecrite.
- **Un agent arrete par l'utilisatrice laisse une trace ecrite de celui qui relance : qui, quand, ce que
  sa branche portait.** Un seul fichier du depot le dit pour s56 (`cloture-nadia.md`, "l'instance B
  arretee") ; le reste est dans un message de lancement.

## s57 (hors sprint)

- **Un classement "seul manque : X" se verifie contre chaque obstacle que la cellule de matrice nomme, pas contre le premier.**
  `HG-CM1-10` etait rangee parmi les lignes dont le seul manque est un dessin (`DEC-0120` A.13, A.31) ; sa cellule
  nommait aussi le lieu de vie de l'enfant (`DEC-0028`), que `nadia-prof` (s32) et `theo-archi` (`avis-faisabilite-sept-dessins.md`, 2.1)
  donnent pour l'obstacle. Corrige par `DEC-0120` A.41.
- **Un vivier se compte avec le plafond de celui qui le porte.** La condition (i) de la derivation au-dela de 8,0 demande
  *"au moins 4,0 de lots instruits"* prenables par une troisieme instance sous `PAR-1` et `PAR-2` ; mesuree, elle donne 18,0
  sous ces deux regles et 3,0 sous `PAR-8` (trois lots a 1,0 par instance). L'exemple de `nadia-prof` (quatre lots de `G4`,
  4,0) ne tient pas sur une instance. Ecrit par `DEC-0120` A.50 ; la lecture est a `nadia-prof`.
- **Une mesure routee a soi-meme se fait dans le geste qui la rend due.** La mesure de la condition (i) a ete laissee deux
  fois (`DEC-0120` A.36, A.45) pour *"hors du travail demande"* ; elle etait due depuis A.5 point 2 et s'est faite avec
  des commandes sur des fichiers deja presents (`DEC-0120` A.50). Un report de sa propre mesure se compte comme un autre.
- **Une seconde lecture d'un compte de matrice lit huit champs, pas neuf.** Trois lignes de donnees n'ont pas de barre
  finale (`DEC-0120` A.40) : une analyse qui l'exige rend 274 au lieu de 277. C'est le desaccord entre les deux methodes
  qui l'a montre.
- **Les corrections demandees aux trois premiers lots de questionner le monde CE1 reviennent sur trois defauts, pas sur trois lots.** Un indice qui donne la reponse par sa forme ou son verbe (`-03` : six items ; `-02` : quatre), un distracteur que l'enfant ecarte sans la competence (`-01` : trois items ; `-03` : deux ; `-02` : deux), une source citee qui est le test ou une demande de source (`-03`, `-02`). Chacun a ete corrige lot par lot apres la lecture de `nadia-prof`, a 0,50 de seconde lecture hors marge. Consigne en `DEC-0120` A.273 sous forme d'autocontrole du premier jet par `marc-dev`, sans seuil ni porte ; les verdicts sont dans `lecture-nadia-premier-jet-ce1-qlm-etapes-de-developpement.md`, `lecture-nadia-premier-jet-ce1-qlm-besoins-de-la-plante.md` et `lecture-nadia-premier-jet-ce1-qlm-ce-que-mangent-les-animaux.md`.
- **Une projection qui tombe pile sur le plafond n'est pas une reserve.** Apres `-03`, le cas haut etait 18,70 et sept secondes lectures de plus donnaient 22,20, soit 2,00 de plus que le plafond de 20,20 : le calcul etait faisable des A.272, qui comptait trois secondes lectures de reserve pour sept lots restants. Le plafond a ete releve a 22,20 par l'utilisatrice (`DEC-0120` A.273), a zero de reserve ; les rejeux au-dela des trois et la seconde lecture de l'horloge restent hors de cette projection (`Q-273-1`).
