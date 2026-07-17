# Fiche de conformité — Bandit manchot — calcul mental (CP + CM1)

- **Mécanique** : bandit manchot (`domain/mechanics/banditmanchot`, `BanditManchotMechanic`)
- **Contenu(s) pédagogique(s)** : calcul mental CP (additions/soustractions ≤10), calcul mental
  CM1 (tables de multiplication / multiplications)
- **Statut global** : En cours
- **Dernière mise à jour** : 2026-07-17, par camille-po

## Compétences programme couvertes

| Matière | Niveau | Domaine | Compétence (réf. programme) | Statut |
|---|---|---|---|---|
| Mathématiques | CP | Nombres et calculs | Calcul mental — additions dans la limite de 10 | en cours |
| Mathématiques | CP | Nombres et calculs | Calcul mental — soustractions dans la limite de 10 | en cours |
| Mathématiques | CM1 | Nombres et calculs | Calcul mental — tables de multiplication (jusqu'à ×10) | en cours |
| Mathématiques | CM1 | Nombres et calculs | Multiplications posées de nombres à 2-3 chiffres | en cours |

Détail des référentiels : `squad/programme/mathematiques/competences-cp.md` et
`squad/programme/mathematiques/competences-cm1.md`.

## Critères pédagogiques

- [ ] Progressivité : les items générés restent strictement dans les bornes de la
      `DifficultyConfig` du niveau (CP ≤10 ; CM1 facteurs 1-10), vérifié par test unitaire
      déterministe.
- [ ] Feedback immédiat : chaque réponse (bonne ou mauvaise) produit un retour visible avant le
      round suivant.
- [ ] Absence de piège hors-programme : les distracteurs proposés sont plausibles mais jamais
      construits sur une notion non enseignée au niveau visé.
- [ ] Adaptation au niveau : aucune ligne de code de la mécanique ne diffère entre CP et CM1 —
      seule la configuration de contenu change (preuve du découplage mécanique/contenu).
- [ ] Accessibilité enfant : cibles tactiles larges, pas de texte long à lire pour comprendre la
      consigne, animation courte et non bloquante.

## Bloc validation utilisateur (Done pédagogique)

- **Démo jouée le** : — (à venir, fin de sprint s01)
- **Validée par l'utilisatrice** : Non
- **Date de validation** : —
- **Commentaires** : —

## Bloc playtest enfants

- **Session tenue le** : — (à venir, aux jalons — pas prévu avant la fin de s01/s04)
- **Niveau(x) testé(s)** : —
- **Nombre d'enfants** : —
- **Observations** : —
- **Suites données** : —
