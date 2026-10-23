# DEC-0125 - Troisieme non-entree de `S57-TYP1-TEXTES-DE-GABARIT` et de `S57-MESSAGE-PARTIE-PERDUE` : les deux stories sont retirees du backlog actif

- **Type** : decision de produit (retrait de deux stories apres trois non-entrees)
- **Date** : 2026-10-23
- **Auteur** : camille-po
- **Sprint** : s57, cloture du palier 4
- **Competences programme visees** : transverse - pas de contenu pedagogique (typographie de textes que l'eleve lit a l'ecran). **Aucune ligne de `squad/programme/MATRICE-couverture.md` n'est concernee.**
- **Statut** : **Acceptee** (motif de forme 1, `DEC-0062` : des faits du depot, references ci-dessous). **Reversible par une phrase de l'utilisatrice** (section 4). Ce retrait **n'implique aucun changement de code** : aucun proprietaire n'est a designer.
- **Lien** : `DEC-0120` A.118, A.150 et l'amendement A.171 a A.179 ; `preparation-palier-4-camille.md` section 6 ; `composition-palier-4.md` sections 3 et 7 ; `docs/backlog/backlog.md`, `S57-TYP1-TEXTES-DE-GABARIT` et `S57-MESSAGE-PARTIE-PERDUE` (amendement de `Q-T11` et `Q-T12`).

## 1. Les faits, comptes

`DEC-0120` A.150 ecrit : *"une troisieme non-entree au palier 4 est la direction que personne n'a choisie [...] Si elles n'entrent pas a l'ouverture, une `DEC` avec le motif s'ecrit a ce moment-la"*. `composition-palier-4.md` section 7 les compte a leur troisieme non-entree et renvoie la `DEC` a moi.

| Story | Premiere non-entree | Deuxieme | Troisieme | Ce qui manquait a chaque fois |
|---|---|---|---|---|
| `S57-TYP1-TEXTES-DE-GABARIT` | palier 2 (composition du 2026-10-15, `DEC-0120` A.115 : *"premiere inscription au palier 2, non retenue"*) | palier 3 (`DEC-0120` A.118, *"deuxieme ecartement"*) | palier 4 (`composition-palier-4.md` section 3) | `Q-T12` de `theo-archi` (le perimetre technique), ouverte depuis le 2026-10-15, **sans reponse dans le depot** |
| `S57-MESSAGE-PARTIE-PERDUE` | palier 2 (meme source) | palier 3 (meme source) | palier 4 | la mesure du nombre de lignes de `.message-reprise` a 744 et a 1200 de large, *"a affecter par `sofia-sm`"*, **jamais affectee ni faite** |

Les deux portent le meme avis de `nadia-prof`, ses mots : *"Ni refus ni reserve. Recommandation non bloquante"* (`TYP1`), *"Une story, de faible priorite"* (`MESSAGE-PARTIE-PERDUE`). Aucun enfant, aucune relecture, aucune mesure du depot n'a montre une coupure reelle d'un de ces textes a l'ecran.

## 2. Decision

**Les deux stories sont retirees du backlog actif** : statut `RETIREE`, hors de toute composition, hors du compte des reports. Leur texte reste ecrit dans le backlog, sans etre efface. Le palier 5 ne les compte pas.

## 3. Motif

1. **Trois non-entrees successives sont une direction.** Je la choisis au lieu de la laisser se faire : elles n'entrent pas.
2. **Ce qui les bloquait n'a avance dans aucun des trois paliers** (section 1), et ni l'un ni l'autre des deux manques n'est un travail de produit.
3. **Le cout n'est pas nul** : `nadia-prof` a repondu pour `TYP1` *"un regard au premier rejeu d'un jeu de saisie qui suit la livraison"* ; un lot de plus dans un palier dont le stock de lots prets est la limite (`arbitrage-nadia-palier-4.md`) passe devant des lots qui font avancer la matrice.
4. **Le retrait ne ferme pas une competence** : aucune ligne de matrice ne depend de ces deux stories.

## 4. Ce qui les rouvre

Une de ces trois choses, ecrite dans le depot :

1. une phrase de l'utilisatrice, citee mot pour mot avec sa date (forme 2) ;
2. une mesure d'`ines-design` ou de `sofia-sm` qui montre un de ces textes coupe a 744 ou a 1200 de large (la mesure que `nadia-prof` appelait *"la premiere chose a mesurer"*) ;
3. un avis de `nadia-prof` qui releve la priorite.

La story est alors reprise par une annotation datee et compte comme une premiere inscription.

## 5. Ce que cette DEC laisse

- `Q-T12` n'a plus d'objet pour `TYP1` tant que la story est retiree : `theo-archi` n'a pas a y repondre, et je ne le lui demande plus. Si elle est rouverte, la question revient avec elle.
- `S57-FORME-C-VERDICT` et le garde-fou des verdicts, fusionnes au palier 1, ne sont pas touches.

## 6. Ce que cette DEC ne decide pas

Ne modifie ni code, ni gabarit, ni paquet, ni catalogue. Ne prononce aucun Done, ne juge aucun contenu, ne fixe aucun prix. Ne dit rien d'autre sur la typographie des textes de l'ecran : `ADR-0067` et son garde-fou restent tels quels. **A confirmer par l'utilisatrice** : si elle veut garder l'une de ces deux stories, une phrase suffit (section 4).

## Amendement du 2026-10-23, premier (`camille-po`, confirmation par l'utilisatrice)

Statut de `DEC-0125` : `Acceptee`, inchange. L'utilisatrice a repondu *"on retire"* (forme 2, `DEC-0062` G, relayee par l'agent lanceur, 2026-10-23) a la demande de confirmation de la section 6. **Les deux stories restent retirees ; aucune regle de cette `DEC` ne change** ; la section 4 (ce qui les rouvre) tient. Consigne par `DEC-0120` A.187.
