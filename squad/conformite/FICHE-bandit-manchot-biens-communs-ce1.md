# Fiche de conformite - bandit-manchot-biens-communs-ce1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-emc-biens-communs.yaml` (24 items, type `question`,
  aucun texte support).
- **Entree de catalogue** : `bandit-manchot-biens-communs-ce1`, titre servi *"Les biens de tous"*, consigne servie *"Lis bien,
  puis choisis la bonne réponse."*, `nombreDeManches: 10`.
- **Ligne declaree** : `EMC-CE1-01`. **Story** : `S57-BR-EMC-CE1-01`.
- **Competence programme visee** : `squad/programme/enseignement-moral-et-civique/competences-ce1.md`, `EMC-CE1-01`,
  *"Respecter les biens personnels et collectifs : reconnaître ce qui appartient à tous (matériel de la classe, livres de la
  bibliothèque, jeux de la cour) et le geste qui le protège."*
- **Conditions opposables** : `BCO-CE1-1`, `BCO-CE1-2`, `NRC-CE1-1`
  (`ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md`, 4.1) ; `arbitrage-nadia-preparation-palier-3-ce1.md`
  4.1 et 4.4 ; corrections de `lecture-nadia-palier-3.md` (section 5 ; S3, items 8, 9 et indice de 14).
- **Statut global** : **VALIDE SOUS RESERVE**, prononce le 2026-10-21 par `nadia-prof` (s57, palier 3). Fiche ouverte par ce
  verdict.
- **Derniere mise a jour** : 2026-10-21.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

### Verdict du 2026-10-21 (`nadia-prof`, s57, palier 3) : **VALIDE SOUS RESERVE**

**Done pedagogique prononce** sur `round-two` a `8407f73f`. `camille-po` peut passer `EMC-CE1-01` a `couvert-valide` et faire
des stories des reserves ci-dessous, non bloquantes.

**Ce qui fonde le verdict** : `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/verdict-nadia-palier-3-ce1.md`, sections
1, 2 et 3.5. Audit en partie degrade, declare : jeu du pilote d'`ines-design` (`releve-ecran-ines-palier-3.md`, commit
`56ce720e`), 24 items sur 24, lu en texte DOM et sur 4 captures ; jar non lance par moi.

**Verifie** : lecture complete (`7301cbbc`) ; deux taches de 12 items, les trois exemples de la ligne en bonne reponse, ni
*ranger* ni *remettre* en bonne reponse (`BCO-CE1-1`) ; biens personnels de personnages nommes, a l'ecole, rien sur ce que
l'enfant possede (`BCO-CE1-2`) ; *prevenir un adulte* trois fois, face a des distracteurs vraisemblables (`NRC-CE1-1`) ; tache 1
a meme article et meme nombre ; enonces de 8 et 9, indice de 14, comme demande ; revelation de la bonne reponse apres deux
erreurs.

**Reserves, non bloquantes** :

1. A la revelation, reponse attendue coupee au pli, item 13 a 744 x 551 (*"elle tourne les pages"* visible, *"doucement"*
   dessous) ; item 21 a 720 avec barre (`V3` de `veto-nadia-gabarit-pli.md`).
2. Item 17 (`S3` de `lecture-nadia-palier-3.md`, confirme sur le texte rendu) : la question dit *"avant de l'ouvrir"*, et
   aucun des deux distracteurs n'est un geste d'avant ; l'enfant peut eliminer par la forme. Exigence : deux distracteurs qui
   sont des gestes faits avant d'ouvrir le livre, et qui laissent des traces.
3. **Neuve, datee du 2026-10-21** : l'enonce de 13 se coupe a *"Que fait-"* / *"elle pour ne pas l'abîmer ?"*. Exigence : a
   744 x 551, aucun mot a trait d'union d'un enonce coupe en fin de ligne. Levier non choisi par moi.

**Je reste la seule garde** de `NRC-CE1-1` (aucun garde-fou ne compare deux paquets), de l'elimination par la forme d'un
distracteur, et de la coupure d'un mot compose en fin de ligne.

**Signe** : `nadia-prof`, 2026-10-21.
