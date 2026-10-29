# Relecture de la correction 29c11e3f (ce1-qlm-besoins-de-la-plante, item 11)

Relecteur : `relecteur-pedagogique`. Perimetre : orthographe, accents, ponctuation, conformite DEC-0002. Le niveau scolaire et le sens ne sont pas juges (`nadia-prof`).

## Verdict

Aucun defaut.

## Verifie

Fichier : `adapters/adapter-content/src/main/resources/contenu/ce1-qlm-besoins-de-la-plante.yaml`, ligne 143.

Texte : `Une graine a déjà de l'eau. Pour germer, elle a aussi besoin de quoi ?`

- Octets lus en binaire (`python -I`) : le fichier est de l'UTF-8 valide. Avant le point d'interrogation, `c2 a0` (U+00A0), pas `20`.
- Accents : `déjà` en `c3 a9` / `c3 a0`, complet. Aucun autre mot de l'enonce n'en exige.
- Apostrophe de `l'eau` : `27` (droite). Guillemets : `22`. Aucune apostrophe typographique (U+2019) ni tiret cadratin (U+2014) dans le fichier.
- Ponctuation : point apres `eau`, virgule apres `germer`, un seul `?`. Orthographe et accord de `elle a aussi besoin de quoi` corrects.
- Cohérence du fichier : 14 enonces interrogatifs, 14 occurrences de `U+00A0` + `?`, 0 occurrence de `U+0020` + `?`. Le 15e `?` est dans le commentaire ligne 56 (regle TYP-1, ADR-0067).
- Diff : une seule ligne modifiee, ligne 143. Les commentaires voisins (lignes 140-142) sont inchanges.
