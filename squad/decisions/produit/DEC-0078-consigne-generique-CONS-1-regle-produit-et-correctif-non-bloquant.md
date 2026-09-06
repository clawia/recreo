# DEC-0078 - `CONS-1` : la consigne generique cesse d'etre reprise par une entree neuve de catalogue, correctif non bloquant pour les dix entrees existantes

- **Type** : arbitrage produit sur un defaut transverse route par `nadia-prof`.
- **Date** : 2026-09-06
- **Auteur** : `camille-po`
- **Sprint** : s29 (cloture), effet sur s30
- **Competence programme visee** : transverse - pas de contenu pedagogique.
- **Statut** : Acceptee.
- **Lien** : `squad/conformite/PASSE-TRANSVERSE-s29-les-proprietes-de-portee-depot.md`, section 2
  (`CONS-1`) et section 8 (routage) ; `docs/backlog/backlog.md`, section "Story a ecrire pour s30 -
  `CONS-1`" ; `squad/sprints/s29-parallelisation-et-dettes-payees/verdicts-nadia-instance-A.md`.

## 1. Le fait, tel que `nadia-prof` l'a mesure

La consigne de catalogue *"Lis bien la question, puis choisis la bonne reponse."*, qui ne nomme
aucune tache propre a un jeu, est passee de **5 jeux a 10** en un seul sprint (mesure sur trois
etats successifs du catalogue : 5 a la cloture de s28, 5 a l'ouverture de s29, 10 aujourd'hui).
Elle couvre desormais toutes les matieres ouvertes depuis s27 : questionner le monde CP,
histoire-geographie CM1, anglais CM1, EMC aux deux cycles.

`nadia-prof` la qualifie de defaut contre sa propre regle posee a s28 en payant la dette des
paquets a voix : *"la consigne (entree de catalogue) porte la tache ; l'enonce (item) porte ce qui
est propre a l'item."* Sur ces dix jeux, la consigne ne porte aucune tache : un enfant qui enchaine
plusieurs jeux d'une meme matiere lit trois fois la meme phrase qui ne lui apprend rien avant de
commencer.

**Non bloquant** : aucune ligne de matrice n'est en cause, aucun enfant n'apprend rien de faux, et
les jeux concernes s'apprennent malgre cette consigne.

## 2. Ce que je decide

**Je tranche, plutot que d'ouvrir une reserve de plus sans destinataire.**

1. **Regle produit, effective a partir de s30** : toute entree neuve de `catalogue.yaml` porte une
   consigne qui **nomme la tache** de son jeu, comme le font deja 44 des 54 entrees actuelles. La
   consigne generique *"Lis bien la question, puis choisis la bonne reponse."* **ne doit plus etre
   reprise** par une entree neuve, quelle que soit la matiere.
2. **Correctif non bloquant sur les dix entrees existantes**, planifie pour s30 : chacune des dix
   consignes est reecrite pour nommer la tache reelle de son jeu. `nadia-prof` ne fournit pas les
   dix textes - une consigne se juge contre le paquet qu'elle introduit, elle les rejouera une par
   une. **Regime : rejeu borne, 0,5**, chiffre par elle-meme.
3. **Ce correctif ne rouvre aucune ligne de `squad/programme/MATRICE-couverture.md`** : les dix
   lignes servies par ces jeux restent dans l'etat ou leurs verdicts pedagogiques les ont laissees.

## 3. Ce que cette decision ne fait pas

- Elle ne dicte aucun des dix textes de consigne : `lea-dev` ou `marc-dev` les ecrivent, `nadia-
  prof` les rejoue.
- Elle ne prononce aucun Done pedagogique.
- Elle ne cree aucun garde-fou ArchUnit ou automatise sur la forme d'une consigne : la question de
  savoir si une telle regle merite un dispositif technique (par exemple un test qui interdirait la
  reutilisation de la meme chaine sur une entree neuve) n'est pas tranchee ici. Si l'equipe le juge
  utile, elle le route a `theo-archi` ; ce n'est pas une question pedagogique.
- Elle ne remplace aucun arbitrage de `nadia-prof` sur le contenu des paquets concernes : seule la
  phrase de consigne est en cause.

## 4. Impact

- `docs/backlog/backlog.md` : story "`CONS-1`, la consigne generique n'a plus a etre reprise par
  une entree neuve de catalogue" ajoutee, a instruire au planning de s30.
- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0078` ajoutee.
- Aucune ligne de `squad/programme/MATRICE-couverture.md` ne change du fait de cette decision.
