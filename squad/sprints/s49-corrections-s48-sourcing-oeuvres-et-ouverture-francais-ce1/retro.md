# Retro - s49 - trois corrections differees de s48, `FRA-CE1-01` (premiere ligne construite du vivier
francais CE1), chantier de sourcing du corpus d'oeuvres du domaine public inscrit

- **Date** : 2026-10-03.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Nature du sprint** : entierement pilote par agents, sans ceremonie de daily - conforme a
  `squad/README.md`. Une instance unique de `nadia-prof` (Done pedagogique sur quatre stories), une
  story de developpement affectee a `marc-dev`, trois a `lea-dev`, le mecanisme du chantier de sourcing
  a `sofia-sm` (casquette dev), le choix des oeuvres a `nadia-prof`.

---

## 1. Ce qui a marche - le second passage sur `FRA-CE1-01` sans friction excessive

**Un refus reel, une barre ecrite dans le meme document, une correction qui la reprend au mot pres, un
second passage le jour meme.** `nadia-prof` refuse `FRA-CE1-01` au premier passage pour deux motifs
d'exactitude pedagogique (cinq items a deux reponses justes, huit indices sans methode ni lieu a
regarder) et ecrit, dans la meme fiche, la forme exacte qui leve chacun. `marc-dev` la reprend, et le
second passage valide sous reserve le jour meme. **Aucune boucle supplementaire** : contrairement a
l'episode `ANG-CP-R1`/`EMC-CM1-10` de s48 (retro, section 2), ou l'absence de forme exacte avait laisse
une reserve ouverte sur un item sur cinq, ici la forme exacte etait ecrite des le refus et elle a tenu
integralement au second passage. **Ce que je retiens** : un refus qui s'accompagne de sa propre barre
ecrite, au meme moment, se resout en un aller-retour, pas en une reserve differee. C'est la meme
regle que la retro de s48 amendait deja pour un contenu accepte sous reserve ; cet episode-ci montre
qu'elle vaut aussi pour un refus.

**Deux nuances que je ne confonds pas avec cette reussite.** D'une part, l'episode n'est pas gratuit :
une reserve neuve s'ouvre quand meme (`GRC-CE1-R1`), **annoncee d'avance par `nadia-prof` elle-meme** au
premier passage si le point restait - ce n'est donc pas un signal nouveau, c'est une promesse tenue.
D'autre part, cet episode n'est pas de la meme famille que l'episode "oeufs" de s47, meme s'il partage
sa forme (un aller-retour documente le meme jour dans la meme fiche) : "oeufs" etait un test qui
refusait a tort un contenu juste, celui-ci est un refus pedagogique reel sur un contenu reellement
fautif. Je ne les confonds pas, meme si tous deux illustrent qu'un aller-retour document dans le depot,
le meme jour, n'est pas en soi un cout.

## 2. Ce qui a coute - mon propre avis de faisabilite de s48 avait ignore qu'`ADR-0082` existait deja,
## erreur propagee dans deux `DEC` avant d'etre trouvee

**Precision avant le constat : ce n'est pas mon avis.** Les avis de faisabilite sont rendus par
`theo-archi`, pas par `sofia-sm` ; je ne porte la casquette dev que pour implementer, jamais pour juger
de la faisabilite technique d'un sprint. L'avis fautif (`avis-faisabilite-s48-sept-stories.md`, section
11) est signe `theo-archi`, et c'est lui qui le corrige et l'impute a lui-meme dans son propre avis de
s49 (section 7) : *"`DEC-0105` et `DEC-0107` section 5 reprennent mon erreur."* Je le constate avec la
meme exigence que je lui demande : verifier plutot que relayer.

**Le fait, tel qu'il est ecrit dans le depot.** `ADR-0082` (Accepte, 2026-09-05, commit `4f594d64`)
repond deja aux quatre questions de `DEC-0077` section 4, avec sa regle executable deja verte. L'avis de
`theo-archi` a s48 affirmait pourtant qu'aucun `ADR` ne couvrait le chantier de sourcing et en demandait
un. **Cette affirmation, une fois ecrite, a ete reprise sans reverification dans deux documents
ulterieurs** : `DEC-0105` (`camille-po`, s48, section 2 : *"aucun `ADR`, aucune story, aucun paquet"*)
et `DEC-0107` (`camille-po`, ouverture de s49, section 5 : *"je l'inscris [...] `theo-archi` [...] rend
l'`ADR` et sa regle ArchUnit avant la cloture"*). C'est `theo-archi` lui-meme, en fondant son avis de s49
sur l'arbre reel plutot que sur son propre avis anterieur, qui trouve l'ecart et le corrige - pas une
relecture de `camille-po` ni la mienne.

**Pourquoi cela n'a pas coute plus cher.** L'erreur portait sur l'existence d'un document, pas sur son
contenu : une fois trouvee, la correction n'a demande aucune reouverture de travail - `ADR-0082` etait
deja correct, seule l'affirmation de son absence etait fausse. `camille-po` corrige les deux `DEC` dans
un seul commit (`fcd7c3ee`), le jour meme, par amendement date plutot que par reecriture silencieuse. Si
l'erreur avait porte sur le **contenu** d'une decision d'architecture deja appliquee en code, le cout
aurait ete d'un autre ordre.

**Ce que je retiens, en lecon de process et pas en reproche a un agent nommement.** Une affirmation
d'un avis de faisabilite ("tel artefact n'existe pas") est une affirmation verifiable par une recherche
dans le depot, au meme titre qu'un chiffre de test ou une reference de decision. Elle merite la meme
discipline que celle deja ecrite pour les chiffres de test ("un chiffre annonce par un agent se
reverifie, il ne se relaie pas") : **une absence annoncee se reverifie aussi**, par la meme recherche
qu'on ferait pour confirmer une presence. Je ne fais pas de ce seul cas une regle neuve a `squad/README.md`
- un cas unique ne suffit pas a distinguer un motif recurrent d'un incident isole, la meme reserve que
j'avais posee a s48 pour l'episode des distracteurs sans forme exacte - mais je le nomme ici pour qu'un
second cas comparable, s'il survient, trouve une trace.

## 3. Ce qui a coute - le chantier de sourcing reste bloque par l'absence d'acces web de la squad, et
## c'est une limite structurelle, pas un defaut de process

**Le fait, nomme sans l'habiller.** `ADR-0082` exige, pour chaque oeuvre entree au corpus, une preuve
verifiable : une renonciation `CC0-1.0` publiee par une institution identifiee, avec de quoi
reverifier l'adresse de la fiche publiee. Ni T2-T4 (le mecanisme technique, livre par `sofia-sm`) ni T5
(le choix des oeuvres, engage par `nadia-prof`) n'ont pu produire une seule preuve de ce type a s49,
parce qu'**aucun agent de la squad ne dispose d'un acces web** pour consulter la fiche d'un musee, d'une
bibliotheque numerique ou d'un registre de licence. Le commit de T2-T4 le dit explicitement
(*"aucun acces web disponible, aucune preuve de domaine public fabriquee"*), et celui de T5 aussi
(*"ecrites de memoire, sans acces web"*, *"proposition a verifier, pas preuve de domaine public"*).
`nadia-prof` ecrit vingt-huit candidates de memoire, avec la precision requise par `ART-3` quand elle en
dispose, mais aucune ne peut passer le garde-fou d'`ADR-0082` en l'etat : une date, une institution ou
une renonciation ecrite de memoire n'est pas une preuve refaisable par un tiers, c'est exactement ce que
l'`ADR` interdit de confondre.

**Pourquoi je nomme ceci comme une limite structurelle et pas comme un obstacle a router.** Le tableau
de routage des obstacles (`CLAUDE.md`, section routage) suppose qu'un obstacle a une competence et un
agent qui peut le trancher - produit, architecture, qualite, pedagogie, redaction, capacite. **Aucune de
ces categories ne resout celui-ci** : ce n'est pas une question de perimetre (`camille-po` l'a deja
inscrit), ni d'architecture (`ADR-0082` est deja ecrit et correct), ni de pedagogie (`nadia-prof` fait
deja ce qu'elle peut sans l'outil qui lui manque). **La squad ne peut pas verifier une adresse
publique sans un moyen de la consulter**, et aucun agent de la squad ne peut se donner cet acces lui-meme.
Je ne le route donc a personne a l'interieur de ce sprint : je le signale explicitement pour les sprints
futurs, et pour l'utilisatrice, comme une contrainte materielle du dispositif - au meme titre que
l'absence de synthese vocale au CP (`CLAUDE.md`, "L'enfant de CP n'est jamais seul") est une contrainte
que le produit contourne par un dispositif different, pas par une insistance renouvelee.

**Ce que cela change pour T6 a T8.** Tant que cette limite n'est pas levee - par un acces donne a un
agent, ou par un autre mecanisme de verification que l'utilisatrice choisirait d'introduire -, **aucun
des trois paquets de contenu ne peut etre engage de bonne foi** : engager un paquet sur un corpus non
verifie reviendrait a demander a `nadia-prof` de juger un contenu dont la preuve de licence ne peut pas
etre refaite, exactement le defaut qu'`ADR-0082` existe pour empecher. Je ne propose pas de contournement
ici : router la question a l'utilisatrice, via `camille-po`, est le geste qui revient au sprint qui
rouvrira ce chantier.

## 4. Actions pour la suite

**Je route, je ne tranche pas, conformement a mon role.**

- L'absence d'acces web de la squad, qui bloque T5 a T8 du chantier de sourcing, est a signaler a
  l'utilisatrice via `camille-po` avant tout nouveau sprint sur ce chantier : poursuivre sans le
  signaler reproduirait quatre sprints de sourcing non verifiable.
- La story de `GRC-CE1-R1`, deja ecrite au backlog, reste a affecter a un developpeur ; je ne lui fixe
  pas de sprint.
- `FRA-CE1-02` reste au vivier ouvert, candidate naturelle d'un sprint futur, non engagee par choix
  explicite de `DEC-0107`.

## Decisions prises

**Aucune `DEC` ni aucun `ADR` n'est cree ou amende par cette retro.** Aucun amendement a
`squad/README.md` n'est propose : les deux constats de cette retro (sections 2 et 3) portent sur un
incident d'exactitude d'un avis et sur une limite materielle de la squad, ni l'un ni l'autre n'appelant
une regle de ceremonie neuve a ce jour.
