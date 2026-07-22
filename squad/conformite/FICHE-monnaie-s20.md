# Fiche de conformité — La monnaie (Grandeurs et mesures, s20)

- **Mécaniques** : Quiz-aventure (inchangée, id `quiz-aventure`) pour CP et CM1 ; Associer/Classer
  (inchangée, id `associer`) pour euros ↔ centimes. **Aucune nouvelle mécanique, aucun nouveau coup,
  aucun code.**
- **Contenus** : 3 jeux **externalisés en YAML** et **auto-enregistrés** par les loaders existants
  (`QuizPackLoader`, `AssocPackLoader` ; convention `niveau-jeu.yml`).
- **Statut global** : ✅ **VALIDÉ (2026-07-22)** — Done pédagogique prononcé par l'utilisatrice
  (validation groupée s19 + s20). Une seule correction de contenu appliquée : le **5 € est un billet,
  pas une pièce** (monnaie CP) ; le reste validé tel quel.
- **Dernière mise à jour** : 2026-07-22, par camille-po
- **Ergonomie** : cf. DEC-0005 (carte-question, quiz) + DEC-0014 (plateau d'appariement) + DEC-0016.

## Jeux et compétences visées

| gameKey | Jeu | Matière | Niveau | Compétence | Statut |
|---|---|---|---|---|---|
| `monnaie` | La monnaie CP (`cp-monnaie.yml`) | Mathématiques | CP | compter des euros, petites sommes, rendre la monnaie | couvert-validé (2026-07-22) |
| `monnaie` | La monnaie CM1 (`cm1-monnaie.yml`) | Mathématiques | CM1 | rendre la monnaie, conversions euros/centimes | couvert-validé (2026-07-22) |
| `associer` | Euros ↔ centimes (`cm1-monnaie-centimes.yml`) | Mathématiques | CM1 | associer une somme en euros à sa valeur en centimes | couvert-validé (2026-07-22) |

## Critères pédagogiques
- [x] Ouvre le domaine **Grandeurs et mesures — monnaie**, jusque-là absent de la matrice.
- [x] Montants, rendus de monnaie et conversions euros/centimes **exacts** (contenu vérifié,
      relecture arithmétique croisée).
- [x] Niveau adapté : CP (petites sommes) ; CM1 (conversions et rendus plus larges).
- [x] Pur contenu sur mécaniques existantes (quiz-aventure, associer/classer) ; contenus
      **externalisés YAML auto-enregistrés** (aucun code) ; réponse non fuitée dans le DOM.
- [x] **Jokers** cohérents (DEC-0013) sur les deux mécaniques.
- [ ] **Réserve** : contenus initiaux restreints — à relire/enrichir par l'utilisatrice directement
      dans les YAML (justesse des montants/rendus/conversions = point de vigilance principal).

## Bloc validation utilisateur (Done pédagogique)
- **Démo jouée le** : 2026-07-22 · **Validée** : Oui (VALIDÉ le 2026-07-22, groupée s19 + s20) ·
  **Commentaires** : ouverture du domaine monnaie (CP quiz, CM1 quiz, euros ↔ centimes CM1) validée ;
  montants, rendus et conversions jugés justes. **Une correction de contenu appliquée** : le **5 €
  est un billet, pas une pièce** (jeu monnaie CP) — YAML corrigé ; le reste validé tel quel.

## Bloc playtest enfants
- **Session tenue le** : — (jalon) · **Observations** : —
