# Fiche de conformité — Déclinaisons du bandit manchot (s04)

- **Mécanique** : bandit manchot (`BanditManchotMechanic`) — **réutilisée sans modification**.
- **Contenus** : 6 nouveaux `ContentGenerator` (listes vérifiées) sur plusieurs matières.
- **Statut global** : ✅ Validé pédago (2026-07-19) — les 6 jeux (dont « La première lettre »
  reformulée) sont acceptés par l'utilisatrice ; playtest enfants à venir (jalon).
- **Dernière mise à jour** : 2026-07-19, par camille-po

## Jeux et compétences visées

| gameKey | Jeu | Matière | Niveau | Compétence | Statut |
|---|---|---|---|---|---|
| `complement-dix` | Compléments à 10 | Maths | CP | compléments à 10 | en cours |
| `comparaison` | Le plus grand nombre | Maths | CP | comparer des nombres | en cours |
| `premiere-lettre` | La première lettre | Français | CP | principe alphabétique (attaque) | en cours |
| `homophones` | Le bon petit mot | Français | CM1 | homophones grammaticaux | en cours |
| `nature-mot` | La nature du mot | Français | CM1 | classes de mots | en cours |
| `anglais-lexique` | Le bon mot en anglais | Anglais | CM1 | lexique LV | en cours |

## Critères pédagogiques (transverses)
- [ ] Chaque item a une réponse correcte unique et des distracteurs plausibles, issus de la liste
      vérifiée du générateur (testé en déterministe).
- [ ] Contenu adapté au niveau (CP : nombres ≤ 20, mots courants ; CM1 : homophones/natures/lexique
      usuels).
- [ ] Réutilisation stricte de la mécanique (aucune modification de `BanditManchotMechanic`).
- [ ] Jeux sans réglage : pas de bouton « Paramètres » affiché.

## Réserve produit à valider en démo
- [ ] **« La première lettre »** est une **reformulation** de « le bon son/graphème » : sans audio,
      on cible l'attaque écrite du mot (principe alphabétique), pas la discrimination auditive.
      À confirmer par l'utilisatrice (une vraie phonologie sonore = mécanique audio ultérieure).

## Bloc validation utilisateur (Done pédagogique)
- **Démo jouée le** : 2026-07-19
- **Validée par l'utilisatrice** : ✅ Oui
- **Commentaires** : Les 6 jeux validés. Reformulation « La première lettre » acceptée (à défaut
  d'audio pour une vraie phonologie sonore).

## Bloc playtest enfants
- **Session tenue le** : — (jalon)
- **Observations** : —
