# Index des decisions produit

Une ligne par decision produit. Numeros jamais reutilises.

| Reference | Titre | Sprint | Date | Statut |
|---|---|---|---|---|
| DEC-0001 | Perimetre du sprint 0 et choix de la premiere competence couverte | s0 | 2026-08-04 | Acceptee |
| DEC-0002 | Statut des emoji et pictogrammes dans le contenu et l'interface destines a l'enfant | s0 | 2026-08-04 | Acceptee |
| DEC-0003 | Le sprint 0 ne cree pas de story de persistence, le critere d'integration est reformule | s0 | 2026-08-04 | Acceptee |
| DEC-0004 | Textes, forme du score et seuils chiffres pour lever le refus pedagogique du sprint 1 | s01 | 2026-08-05 | Acceptee |
| DEC-0005 | L'indice de S1-5 est un contenu YAML, jamais une constante du domaine | s01 | 2026-08-05 | Acceptee |
| DEC-0006 | Perimetre du chantier identite visuelle de s02 et statut de l'icone d'application au regard de DEC-0002 | s02 | 2026-08-05 | Acceptee |
| DEC-0007 | Le sprint 3 complete la ligne mathematiques CP existante et solde la dette de gouvernance, il n'ouvre pas de nouvelle matiere ni de nouvelle competence | s03 | 2026-08-05 | Acceptee (point 1 remplace par DEC-0008, points 2 a 8 inchanges) |
| DEC-0008 | La couverture du programme reprend sur demande de l'utilisatrice : francais CP et CM1 entrent au backlog | s04 | 2026-08-06 | Acceptee |

Amendement (pas une nouvelle reference) : `DEC-0002` a ete amendee le 2026-08-04, a la suite de la
relecture pedagogique de `nadia-prof` sur le refus de `bandit-manchot-additions-cp` - ajout d'une
exigence positive de francais correct et integralement accentue pour la categorie B, et
correction du jugement sur les deux orientations du triangle de `contenu-repris/associer/
cp-formes.yml` (qualite pedagogique et non ambiguite, selon l'avis de Nadia). Voir la section
"Amendement du 2026-08-04" dans `DEC-0002-emoji-et-pictogrammes-contenu-enfant.md`.

Amendements (pas une nouvelle reference) : `DEC-0004` et `DEC-0007` ont chacune ete amendees le
2026-08-05, sur mesure de `theo-archi` dans son avis de faisabilite du sprint 3
(`squad/sprints/s03-completer-mathematiques-cp/avis-faisabilite.md`) : la table de repartition des
sommes de `DEC-0007` point 2 etait calculee sur le tirage nu, pas sur le tirage reellement compose
avec le filtre d'operandes nuls de `DEC-0004` point 5, ce qui faisait sortir deux sommes de leurs
fourchettes. `DEC-0004` precise desormais que la cible pedagogique retenue est 12,9 %
d'operandes nuls (bande du point 5 inchangee) ; `DEC-0007` porte la table de cibles recalculee sur
le tirage compose. Voir les sections "Amendement du 2026-08-05 (s03)" dans
`DEC-0004-textes-score-et-seuils-lever-refus-s01.md` et
`DEC-0007-perimetre-s03-completer-avant-etendre.md`.

Amendement (pas une nouvelle reference) : `DEC-0007` a ete amendee le 2026-08-06, sur demande
explicite de l'utilisatrice qui renverse la priorisation "completer avant etendre". Seul le
point 1 de `DEC-0007` (aucune matiere ni competence nouvelle avant `couvert-valide` sur
mathematiques CP) est remplace, par `DEC-0008-la-couverture-reprend-francais-cp-et-cm1.md`. Les
points 2 a 8 de `DEC-0007` (repartition des sommes, declenchement de l'indice, appui rate, largeur
d'ecran, titre accentue, question de process, ecran de catalogue vide) restent en vigueur,
inchanges : ils ne sont ni retires ni redebats par cet amendement. La condition ecrite par
`nadia-prof` pour que la ligne mathematiques CP passe a `couvert-valide` n'est ni levee ni
abandonnee par `DEC-0008` : elle reste en attente de son audit de cloture de s03, en parallele de
l'ouverture du francais.
