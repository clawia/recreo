# s57 - relecture de l'orthographe du titre et de la consigne de l'entrée `QLM-CE1-12` (`S57-QLM-CE1-ENTREES-CATALOGUE`, critère 4)

- **Auteur** : `relecteur-pedagogique`.
- **Date** : 2026-10-29.
- **Arbre** : branche `s57-p5-releco-12-titre`, base `round-two` à `1d8df272`.
- **Texte relu** : le titre et la consigne retenus par `nadia-prof` à la section 3 de
  `lecture-nadia-diff-accord-et-titre-ce1-qlm-generations.md` (fait foi). Paquet `ce1-qlm-generations`, 10 manches.
- **Norme du catalogue** : `relecture-orthographe-entrees-de-catalogue-ce1-qlm-et-horloge.md`, section 1 (apostrophe droite,
  espace ordinaire avant `?` `!` `:` `;`, pas de ponctuation finale sur un titre, `.` final sur une consigne à l'impératif), et
  les neuf titres voisins lus dans `adapters/adapter-content/src/main/resources/catalogue.yaml` de cet arbre.
- **Périmètre** : orthographe, accents, majuscules, apostrophes, espaces, accords, ponctuation. Le vocabulaire, le ton, le fond
  et le niveau scolaire restent à `nadia-prof`. Aucun catalogue, aucun test, aucune lecture de `nadia-prof` n'est modifié.

## 1. Verdict

| Texte | Chaîne relue | Verdict |
|---|---|---|
| Titre | "Les générations et les moments de la vie" | juste |
| Consigne | "Lis bien, puis choisis la bonne réponse." | juste |

**Correction proposée : aucune.** Rien n'est à valider par `nadia-prof`.

## 2. Règles vérifiées

1. **Accents** : "générations" porte deux "é" (octets `C3 A9`, relus) ; "réponse" porte un "é".
2. **Accord** : "générations" et "moments" sont au pluriel après "les" ; "la vie" est au singulier, complément de "moments" par
   "de" (une seule vie, celle d'un personnage) ; "les générations" et "les moments" se coordonnent par "et" sans virgule.
   Consigne : "Lis" et "choisis" à l'impératif, 2e personne du singulier, `-s` final.
3. **Majuscules** : initiale du titre seulement ; "générations", "moments", "vie" en minuscules. Initiale de la consigne seulement.
4. **Apostrophes** : aucune dans les deux textes, donc rien à unifier avec la norme (apostrophe droite).
5. **Espaces** : espaces ordinaires entre les mots, aucun espace avant ou après le texte, aucun espace insécable (relu octet par
   octet : aucun caractère hors ASCII sauf les "é").
6. **Ponctuation du titre** : aucune ponctuation finale, comme les neuf titres voisins (aucun ne finit par un point ; seuls les
   titres en question portent `?`). Le titre n'est pas une question.
7. **Ponctuation de la consigne** : virgule avant "puis" et point final, identiques mot pour mot aux huit consignes de
   `QLM-CE1-01` à `QLM-CE1-10` déjà au catalogue.
8. **Signes interdits** : aucun tiret cadratin, aucun guillemet typographique, aucun emoji.

## 3. Ce que je n'ai pas pu vérifier

1. **La chaîne écrite dans `catalogue.yaml`.** L'entrée de `QLM-CE1-12` n'est pas dans ce worktree (`marc-dev` l'écrit en
   parallèle) : aucun `generations` n'y figure. Les deux textes sont relus dans l'écrit de `nadia-prof`, pas dans le fichier
   que le jar sert. **L'orthographe de la chaîne effectivement écrite, et sa liste dans
   `TexteAccentueExposeParLePortEntrantTest` si l'entrée y figure, sont à relire après le commit de `marc-dev`**, ou à
   vérifier par comparaison avec les deux chaînes de la section 1.
2. **Le rendu à l'écran** (retour à la ligne dans une tuile, huit mots) : non vu, hors porte d'un relecteur de texte.
