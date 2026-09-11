# [Sprint s34] Consommation de tokens

- **Genere par** : `python outils/rapport-tokens.py --sprint s34`
- **Resultat** : **l'outil echoue**, aucun chiffre n'est produit.

## Ce que l'outil rend, mot pour mot

```
ignore s34-dernier-sprint-du-plan-2-palier-2-engage : pas d'horodatage de debut dans planning.md
Sprint inconnu : s34
```

(sortie complete ; cinq autres sprints anterieurs sont ignores par le meme motif dans la meme
execution, ce qui n'est pas propre a s34.)

## Pourquoi, sans inventer de chiffre a la place

`lire_horodatage` (`outils/rapport-tokens.py`) exige un horodatage a la minute pres,
`AAAA-MM-JJ HH:MM`, sur la ligne "Horodatage de debut" du planning et sur la ligne "Horodatage de
fin" de la review, pour construire la fenetre temporelle qui attribue chaque tour de conversation
a ce sprint plutot qu'a un autre.

- `planning.md` porte : *"Horodatage de debut : 2026-09-10"* - une date, aucune heure.
- `review.md` porte : *"Horodatage de fin : 2026-09-11"* - une date, aucune heure, et **ce n'est
  pas un oubli** : la meme ligne dit explicitement *"je n'invente pas d'heure de debut que le
  planning n'a pas ecrite, contrairement a s33 qui en portait une"*.

L'absence d'heure est donc une decision prise dans la ceremonie elle-meme, pas un defaut de
redaction a corriger apres coup. Ecrire une heure a cette place maintenant, pour satisfaire
l'outil, reviendrait a fabriquer une donnee que la review a explicitement refuse d'inventer -
exactement ce que `CLAUDE.md` interdit ("ne jamais fabriquer une ceremonie qui n'a pas eu lieu").

**Consequence** : aucune table par agent n'est produite pour s34, ni ici ni dans
`squad/rapports/JOURNAL-tokens.md`, qui n'est pas touche par cette execution. Ce n'est pas un
"aucune invocation mesuree" agent par agent - c'est une fenetre qui ne peut pas etre calculee du
tout, faute d'horodatage exploitable aux deux bornes.

## Ce qui n'est pas fait ici

- Aucun chiffre n'est recopie a la main depuis un autre document (planning, review ou verdicts
  d'instance) : ces documents ne portent aucune mesure de consommation de tokens, seulement des
  horodatages de ceremonie.
- Aucune heure n'est ajoutee a `planning.md` ni a `review.md` pour permettre un second essai : les
  deux fichiers sont deja commites, et une modification retroactive de leur horodatage serait la
  meme fabrication que celle que la review refuse.
- Le remede, si l'equipe le veut, est un choix de process pour s35 (porter l'heure dans
  `planning.md` et dans `review.md` des leur redaction), pas un geste a faire ici sur des
  documents de s34 deja clos.
