# DEC-0021 - La regularite de magnitude des additions de "resoudre un probleme" est reportee, pas un geste de ce sprint

- **Type** : perimetre produit, arbitrage de calendrier sur une reserve non bloquante
- **Date** : 2026-08-12
- **Auteur** : camille-po
- **Sprint** : s09
- **Competence programme visee** : mathematiques, CP (cycle 2), domaine "Nombres et calculs",
  "Resoudre des problemes simples d'addition/soustraction en situation (1 etape)"
  (`squad/programme/mathematiques/competences-cp.md`)
- **Statut** : Acceptee

## Contexte

`nadia-prof` refuse `bandit-manchot-problemes-additifs-cp` sur un motif borne (deux enonces dont le
texte contredit la reponse attendue, `squad/conformite/FICHE-bandit-manchot-problemes-additifs-cp.md`,
verdict du 2026-08-12, section 1). Dans la meme fiche, elle nomme une reserve distincte, non
bloquante et ne conditionnant pas la levee du refus (reserve 1) : sur les 11 items d'addition du
paquet, la bonne reponse est 11 fois sur 11 le plus grand des trois nombres proposes. Un enfant qui
remarque cette regularite reussit la moitie du paquet sans calculer.

`sofia-sm` a instruit son correctif (le lot qui corrige les deux enonces "ballons"/"fleurs") pour
qu'il ne touche pas cette reserve : changer les distracteurs modifierait un contenu deja juge par
`nadia-prof`, ce qui est un perimetre, pas une coquille a corriger au passage. Il m'appartient de
trancher si ce perimetre s'ouvre comme un geste separe de ce sprint, ou comme une story pour un
sprint futur.

## Decision

**Je ne l'ouvre pas comme un geste de ce sprint. J'inscris une story au backlog, non affectee, pour
un sprint futur** (`docs/backlog/backlog.md`, section "Backlog - sprints suivants").

### Pourquoi je ne l'ouvre pas maintenant

1. **Le fichier est deja modifie ce sprint par un correctif borne en cours.**
   `cp-problemes-additifs.yaml` est le fichier que corrige le lot dispatche par `sofia-sm` pour lever
   le refus. Ouvrir en parallele un second changement, plus large et non borne par `nadia-prof`, sur
   le meme fichier, cree un risque de conflit qu'aucune urgence ne force a prendre.
2. **Rien ne l'exige pour ce sprint.** `nadia-prof` a explicitement separe cette reserve du motif de
   refus : elle ne conditionne pas la levee, qui se fait par la seule correction des deux enonces.
   Le sprint n'a donc aucune obligation de la traiter maintenant.
3. **`DEC-0019` a deliberement borne ce sprint a un contenu pur, sous gel de rendu, sur la demande de
   l'utilisatrice.** Elargir ce perimetre en cours de sprint pour un defaut non bloquant, sans
   capacite nouvelle engagee pour l'instruire, reviendrait a le faire glisser en silence - exactement
   le mode de defaillance deja nomme sur ce produit (une decision ecrite et jamais executee, ou ici
   l'inverse : une execution non decidee).
4. **La correction n'est pas une substitution mecanique.** Rendre plausible un distracteur superieur
   a la bonne reponse pour plusieurs items d'addition (par exemple un `6` obtenu par surcomptage sur
   `3 + 2`) est un travail de redaction de contenu qui merite sa propre instruction et sa propre
   relecture, pas un ajout de derniere minute a un correctif deja cadre sur autre chose.

## Ce que cette decision ne fait pas

- Elle ne retire ni ne modifie la reserve 1 de `nadia-prof` : elle la reporte, elle ne la classe pas
  sans suite.
- Elle ne bloque pas le correctif en cours sur les deux enonces "ballons"/"fleurs", qui reste
  strictement borne au motif du refus.
- Elle ne prejuge d'aucun Done pedagogique : la reserve reste un constat de `nadia-prof`, pas un
  critere que je viens d'inventer.
- Elle n'affecte aucun developpeur ni aucun sprint a cette story future : c'est une decision de
  capacite, qui reste a `sofia-sm`.
- Elle ne touche aucune ligne de `squad/programme/MATRICE-couverture.md` : cette reserve n'a jamais
  conditionne le statut de la ligne "resoudre un probleme".

## Impact

- `docs/backlog/backlog.md`, section "Backlog - sprints suivants" : story de correction ajoutee, non
  affectee, criteres repris au mot pres de la reserve 1 de `nadia-prof`.
- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0021` ajoutee.

## Alternatives considerees

**Ouvrir un geste separe ce sprint, avec un developpeur affecte par `sofia-sm`.** Ecartee : le
fichier est deja en cours de modification par un correctif distinct, rien ne force ce calendrier, et
le sprint est deliberement borne par `DEC-0019`. Rouvrir ce perimetre maintenant reviendrait a le
faire sans l'avoir decide comme tel.

**Ne rien inscrire, laisser la reserve dans la seule fiche de conformite.** Ecartee : une reserve qui
n'est pas versee au backlog n'existe pas au sens de la gouvernance de ce depot ; elle serait relue au
hasard d'une future consultation de la fiche plutot que suivie comme un travail a faire.
