# Fiche de conformite - saisie-mots-invariables-ce1

- **Mecanique** : saisie.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-mots-invariables.yaml` (24 items, type `saisie`,
  aucun texte support).
- **Entree de catalogue** : `saisie-mots-invariables-ce1`, titre servi *"Écris le petit mot"*, consigne servie *"Lis la
  phrase, puis écris le mot qui manque."*, `nombreDeManches: 10`.
- **Ligne declaree** : `FRA-CE1-21`. **Story** : `S57-BR-FRA-CE1-21` et son amendement du 2026-10-21 (criteres 9 a 14).
- **Competence programme visee** : `squad/programme/francais/competences-ce1.md`, `FRA-CE1-21`, *"Orthographier les mots
  invariables et les mots fréquents étudiés."*
- **Conditions opposables** : `arbitrage-nadia-q05-q10.md` 4.3, points 1 a 5 ; `MIV-CE1-1` et `MIV-CE1-3`
  (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`, 5.12) ; `TRV-CE1-1` a `-7` ; `arbitrage-nadia-preparation-palier-3-ce1.md` 4.1 ;
  `arbitrage-nadia-palier-4.md` 3.1 et 4 (refus ecrit d'avance : *"un item ou un autre mot, bien ecrit, complete la phrase"*).
- **Statut global** : en audit (s57, palier 4, instance A). Fiche ouverte le 2026-10-22 par `nadia-prof`.
- **Derniere mise a jour** : 2026-10-22.

---

## Lecture complete

**lecture complete : ce1-mots-invariables, 24 items lus** - 2026-10-22, 11:26 (UTC+02:00), lu par `date`, sur la branche
`s57-marc-fra-ce1-21` a `56e9fd1b` (`grep -c "formeDeReference:"` rend 24). Lus : les 24 enonces, les 24 formes de
reference, la seule forme acceptee non vide (item 3, `aujourd’hui`, octets `342 200 231` lus par `od -c` : U+2019), les 24
indices, l'en-tete, l'entree du catalogue ; `rapport-marc-dev-palier-4-lp1.md` (sections 1 a 5).

**`OV9` : l'amendement de `S57-BR-FRA-CE1-21` tient ma condition 5.2**, avec un ecart que le lot a ferme. Ma condition
demandait trois choses : (a) les 24 enonces reecrits pour que le sens n'admette qu'un mot : **tenu** (critere 9, refus ecrit
d'avance repris mot pour mot) ; (b) le YAML livrable du lot : **tenu** (*"ses 24 enonces sont reecrits dans le lot"*) ;
(c) *"le remede de `QP1` choisi"* : **non tenu par l'amendement**, qui laisse le choix au developpeur et a `theo-archi` et
porte la recommandation de `theo-archi` (`P1`) sans la trancher. Le lot l'a tranche : `P1`, deux apostrophes. Je n'en fais pas
un motif : ce que (c) protegeait (un lot engage sans remede) n'est pas arrive.
