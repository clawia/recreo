# Fiche de conformité — « Le bon son » (Français CP, s08)

- **Jeu** : Le bon son (`gameKey` `le-bon-son`, entrée `le-bon-son`).
- **Mécanique** : Bandit manchot (inchangée) — générateur `LeBonSonGenerator` (`content/impl`).
- **Matière / niveau** : Français / CP. **Domaine** : décodage — association graphème/phonème.
- **Statut global** : En cours — Done technique ✅ (CI) ; Done pédagogique en attente (démo).
- **Dernière mise à jour** : 2026-07-20, par camille-po · Ergonomie : cf. DEC-0007.

## Compétence visée
Conscience phonologique / décodage : reconnaître un son entendu dans un mot (nasales [an]/[on]/[in],
+ [ou]/[oi]/[ch]). Comble un trou de la matrice (`Français CP — décodage`, jusque-là non couvert).

## Critères pédagogiques
- [x] Un mot s'affiche sur le rouleau ; consigne fixe « Quel son entends-tu ? » ; l'enfant clique
      le bon son parmi trois (grosses cibles tactiles, format court sans charge de lecture).
- [x] Chaque mot contient **exactement** le son attendu ; les distracteurs sont des sons **absents**
      du mot, définis mot par mot (jamais un son réellement présent). Testé en déterministe.
- [x] Anti-doublon (mode session) + épuisement quand le vivier est parcouru.
- [ ] **Réserve** : liste vérifiée d'environ 12 mots, volontairement restreinte ; à relire/enrichir
      par l'utilisatrice (justesse mot ↔ son = point de vigilance principal de la démo). Enrichissement
      futur via externalisation (DEC-0006).

## Bloc validation utilisateur (Done pédagogique)
- **Démo jouée le** : — (à venir) · **Validée** : Non (en attente) · **Commentaires** : —

## Bloc playtest enfants
- **Session tenue le** : — (jalon) · **Observations** : —
