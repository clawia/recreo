# DEC-0076 - Arbitrage de cloture de s28 : corriger et reauditer le paquet refuse dans le sprint plutot que laisser trois items faux servis en production jusqu'a s29

- **Type** : arbitrage de gestion de sprint, prix de la borne d'audit de `nadia-prof`.
- **Date** : 2026-09-04
- **Auteur** : `camille-po`
- **Sprint** : s28 (cloture)
- **Competence programme visee** : transverse - pas de contenu pedagogique. Cette decision ne
  touche aucune ligne de referentiel ; elle porte sur la gestion du sprint qui a produit les
  fermetures de `QLM-CP-01`, `QLM-CP-03` et `QLM-CP-05`.
- **Statut** : Acceptee et executee.
- **Lien** : `DEC-0074` (perimetre de s28, borne de 5,0 sur 5, zero marge) ;
  `squad/conformite/ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md`
  (section 7.3, la dette des paquets a voix chiffree a 0,25 et declinee au-dela de 5,0) ;
  `squad/conformite/FICHE-bandit-manchot-vivant-non-vivant-cp.md` (les trois blocs de verdict,
  refus puis reaudit) ; `squad/programme/MATRICE-couverture.md`, section "Synthese apres les
  verdicts du sprint 28".

---

## 1. Le fait

Le second paquet de questionner le monde sur les etres vivants (`bandit-manchot-vivant-non-vivant-
cp`) a ete livre a s28 avec six items neufs, dont trois de matiere inerte (caillou, sable, neige)
qui donnaient `un objet` en bonne reponse. `nadia-prof` a **REFUSE** cet increment le 2026-09-04 :
cette reponse est fausse au regard du libelle de la ligne, qui distingue explicitement le vivant,
les objets **et** la matiere inerte comme trois categories, pas deux.

`nadia-prof` a ecrit, en meme temps que son refus, qu'elle ne le rejouerait pas dans s28 : le
sprint etait deja a sa borne de 5,0 sur 5, sans marge, et son arbitrage prealable avait explicitement
decline toute composition qui depasserait ce plafond, y compris une composition a 5,25 (le
perimetre du sprint plus la dette des paquets a voix, section 7.3 de son arbitrage). Sur cette
seule regle, la correction du paquet refuse partait donc a s29, et le produit aurait continue de
servir en production trois items faux jusque-la.

## 2. L'arbitrage de l'utilisatrice

**L'utilisatrice a arbitre le contraire le 2026-09-04** : elle a demande que le paquet refuse soit
corrige et reaudite dans s28 plutot que de laisser trois items faux servis en production jusqu'a
s29. `nadia-prof` a execute cet arbitrage sans le rediscuter - elle l'a recu avec son propre refus
ecrit sous les yeux, et elle le dit dans le bloc du troisieme passage de sa fiche de conformite.

`lea-dev` a corrige le paquet (`0c383b9`, texte fourni mot pour mot par `nadia-prof`) et
`nadia-prof` a reaudite le meme jour : **VALIDE SOUS RESERVE** (`VIV-R2`, non bloquante).

## 3. Ce que ce fait coute, dit sans l'adoucir

**Le sprint vaut 5,25 sur une borne de 5, et non 5,0.** Ce n'est pas la composition que
`nadia-prof` avait declinee par ecrit avant le sprint (perimetre plus dette des paquets a voix,
egalement a 5,25) : c'est une composition differente, arrivee par un chemin different - un refus,
une correction et un reaudit dans le meme sprint, chiffres au regime du mot pour mot (0,25,
`ARBITRAGE-PREALABLE-s28...`, section 4.3). **Le total numerique coincide avec ce qu'elle avait
decline ; la composition qui le produit n'est pas la meme.** Les deux faits doivent rester
distincts pour que personne ne lise plus tard "elle avait decline 5,25 et pourtant le sprint vaut
5,25" comme une contradiction de sa part : elle a decline une chose et execute une autre, sur
arbitrage explicite.

**Ce que ce depassement ne fait pas** : il ne revoque ni le refus du 2026-09-04 ni le motif qui le
porte, tous deux lisibles dans `squad/conformite/FICHE-bandit-manchot-vivant-non-vivant-cp.md`
sans etre effaces. Il ne change pas la regle de `DEC-0074` : la borne de `nadia-prof` reste 5,0 par
sprint, sauf arbitrage explicite de l'utilisatrice, comme ici.

**Ce que ce depassement ne dit pas** : il ne fixe aucun precedent silencieux. Un futur sprint qui
depasserait sa borne sans un arbitrage aussi explicite que celui-ci n'aurait pas le meme fondement.

## 4. Ce que je ratifie

> **Le cout reel de s28 est 5,25 sur la borne de 5,0 de `nadia-prof`, par arbitrage explicite de
> l'utilisatrice du 2026-09-04**, rendu apres que `nadia-prof` a ecrit son refus et le prix de sa
> non-reprise dans le sprint. Ce depassement est distinct de la composition a 5,25 que
> `nadia-prof` avait declinee avant le sprint (perimetre plus dette des paquets a voix) : cette
> derniere reste hors de s28 et reportee a s29, sans changement.

## 5. Ce que cette decision ne fait pas

- Elle ne prononce aucun Done pedagogique : les verdicts de `nadia-prof` sur les trois passages du
  paquet sont deja rendus, independamment de cette decision.
- Elle ne fait bouger aucune ligne de matrice : le mouvement de `QLM-CP-01` vers `couvert-valide`
  est trace dans `squad/programme/MATRICE-couverture.md` sur le fond pedagogique, pas ici.
- Elle ne cree aucune story : `VIV-R2` est tracee dans `docs/backlog/backlog.md`, section "Backlog
  ouvert pour s29".
- Elle ne modifie pas la borne de `nadia-prof` pour les sprints suivants : `ADR-0079` (5,0 par
  sprint, unite = le lot de matiere) reste la regle par defaut.
