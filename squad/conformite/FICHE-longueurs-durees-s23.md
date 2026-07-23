# Fiche de conformité — Longueurs & durées (Grandeurs et mesures, s23)

- **Mécaniques** : Quiz-aventure (inchangée, id `quiz-aventure`) pour les longueurs et les durées ;
  Associer/Classer (inchangée, id `associer`) pour les équivalences. **Aucune nouvelle mécanique,
  aucun nouveau coup, aucun code.**
- **Contenus** : 3 jeux **externalisés en YAML** et **auto-enregistrés** par les loaders existants
  (`QuizPackLoader`, `AssocPackLoader` ; convention `niveau-jeu.yml`).
- **Statut global** : ✅ **VALIDÉ (2026-07-23)** — Done pédagogique prononcé par l'utilisatrice après
  démo (validation groupée s21 + s22 + s23 ; DEC-0020). **Étend le domaine Grandeurs et mesures**
  (amorcé en s20 par la monnaie) aux **longueurs** et **durées**.
- **Dernière mise à jour** : 2026-07-23, par camille-po
- **Ergonomie** : cf. DEC-0005 (carte-question, quiz) + DEC-0014 (plateau d'appariement) + DEC-0020.

## Jeux et compétences visées

| gameKey | Jeu | Matière | Niveau | Compétence | Statut |
|---|---|---|---|---|---|
| `quiz-aventure` | Les longueurs (`cm1-longueurs.yml`) | Mathématiques | CM1 | Grandeurs et mesures — conversions de longueurs (m / cm / km / mm) | ✅ validé (2026-07-23) |
| `quiz-aventure` | Les durées (`cm1-durees.yml`) | Mathématiques | CM1 | Grandeurs et mesures — conversions de durées (h / min / jour / semaine) | ✅ validé (2026-07-23) |
| `associer` | Les équivalences (`cm1-mesures.yml`) | Mathématiques | CM1 | Grandeurs et mesures — apparier des grandeurs équivalentes | ✅ validé (2026-07-23) |

## Critères pédagogiques
- [x] Étend le domaine **Grandeurs et mesures** (après la monnaie, s20) aux **longueurs** et **durées**.
- [x] Conversions **exactes** : longueurs m / cm / km / mm ; durées h / min / jour / semaine (contenu
      vérifié, relecture arithmétique croisée) ; équivalences appariées correctes.
- [x] Niveau adapté au CM1 (conversions usuelles, pas d'unités hors-programme).
- [x] Pur contenu sur mécaniques existantes (quiz-aventure, associer/classer) ; contenus
      **externalisés YAML auto-enregistrés** (aucun code) ; réponse / appariement non fuités dans le DOM.
- [x] **Jokers** cohérents (DEC-0013) sur les deux mécaniques.
- [ ] **Réserve** : contenus initiaux restreints — à relire/enrichir par l'utilisatrice directement
      dans les YAML (justesse des conversions / équivalences = point de vigilance principal).

## Bloc validation utilisateur (Done pédagogique)
- **Démo jouée le** : 2026-07-23 · **Validée** : Oui (VALIDÉ le 2026-07-23, groupée s21 + s22 + s23) ·
  **Commentaires** : extension du domaine Grandeurs et mesures validée — longueurs (m/cm/km/mm) et
  durées (h/min/jour/semaine) en quiz, équivalences en associer/classer ; conversions jugées justes,
  contenus à relire/enrichir par l'utilisatrice dans les YAML.

## Bloc playtest enfants
- **Session tenue le** : — (jalon) · **Observations** : —
