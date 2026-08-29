# Obstacle - a qui appartient l'accentuation du texte d'un item ? - sofia-sm (casquette Scrum Master)

- **Date** : 2026-08-29. **Sprint** : s22. **Route par** : `nadia-prof`,
  `avis-nadia-done-pedagogique-s22.md`, section 8, et le bloc de validation de
  `FICHE-bandit-manchot-multiplications-divisions-cm1.md` (refus de `S13-2`).
- **Ce que ce fichier fait** : classer l'obstacle et le router. Il ne tranche rien - ni la
  question de fond (quelle porte le verifie), ni la question technique (un garde-fou est-il
  possible). Les deux reponses appartiennent a d'autres roles.

## Le fait, verifie avant de router

Deux documents du depot assignent la meme verification - l'accentuation integrale du texte qu'un
enfant lit a l'ecran - a deux roles differents, sans qu'aucun des deux ne cite l'autre :

- `CLAUDE.md`, section "Conventions de redaction", a propos de l'amendement du 2026-08-04 a
  `DEC-0002` (francais integralement accentue pour le contenu destine a l'enfant) : *"Cette
  exigence-la est verifiee par `relecteur-pedagogique` comme n'importe quelle autre regle de
  cette section."*
- `.claude/agents/nadia-prof.md`, section "Frontieres - ce que tu ne fais pas" : *"Tu ne corriges
  pas la documentation technique, la Javadoc ni le nommage : c'est le `relecteur-pedagogique`. Tu
  corriges en revanche ce que **l'enfant** lit a l'ecran."*

Un indice affiche a l'enfant sur un echec est exactement ce que la seconde phrase designe. Les
deux textes ne se contredisent pas sur le principe (le texte technique reste au
`relecteur-pedagogique`, le texte lu par l'enfant a une autre porte) mais divergent sur **le nom
de cette autre porte** : `CLAUDE.md` la nomme `relecteur-pedagogique`, la fiche de `nadia-prof`
se la nomme elle-meme.

**`nadia-prof` a refuse `S13-2` (`cm1-multiplications-divisions-10-100`) sur ce seul motif** : 25
indices sur 25 non accentues, aucune de ses onze conditions pedagogiques n'etant en cause. Elle
ecrit explicitement : *"si la porte competente n'est pas la mienne, ce refus aura coute un lot
pour une correction identique."* Le contenu a depuis ete corrige par `lea-dev`
(`be63ce3`, `s22-lea`), verifie en jouant - **ce fichier ne porte pas sur cette correction**, qui
est faite ; il porte sur la question de gouvernance qu'elle a soulevee en la faisant.

**Aucun garde-fou executable ne couvre ce point aujourd'hui**, par la propre verification de
`nadia-prof` (section 4 de son avis) : `TexteAccentueExposeParLePortEntrantTest` couvre les
chaines du catalogue (`catalogue.yaml`), pas le texte des items ;
`LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest` juge la notation d'une valeur, pas la
presence de ses accents. Le defaut a donc traverse une CI verte et un lot livre sans qu'aucun
test ne rougisse.

## Classement

Deux questions distinctes, deux destinataires :

| Question | Nature | Destinataire | Artefact attendu |
|---|---|---|---|
| A qui appartient la verification de l'accentuation du texte lu par l'enfant (indice, enonce, proposition) : `relecteur-pedagogique` (lecture litterale de `CLAUDE.md`) ou `nadia-prof` (lecture litterale de sa propre fiche) ? | Produit, perimetre d'un role - une reponse ecrite quelque part rendra les deux textes coherents entre eux | `camille-po` | `DEC-XXXX`, ou amendement explicite de l'un des deux textes en cause |
| Un garde-fou executable est-il possible pour ce point (accentuation du texte des items, distinct des chaines du catalogue) - et si oui, sous quelle forme ? | Technique, faisabilite d'une regle ArchUnit | `theo-archi` | Avis de faisabilite, puis `ADR` si la regle est retenue |

## Ce que je ne fais pas

- Je ne dis pas laquelle des deux portes est competente : la question a ete posee precisement
  parce qu'aucun des deux textes en cause n'est le mien a amender seule.
- Je ne cree pas de garde-fou et je ne demande a personne de le faire dans ce geste : je route la
  question de faisabilite, je ne prejuge pas sa reponse.
- Je ne rouvre pas le Done pedagogique de `S13-2` : le contenu est corrige, verifie en jouant par
  `lea-dev`. Le seul objet ouvert ici est la regle de gouvernance que le refus a revelee.
