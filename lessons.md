# lessons.md - regles tirees des corrections

Fichier cree le 2026-10-11 par `sofia-sm` a la retro de s56 : il n'existait pas (`find . -iname lessons.md`
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
