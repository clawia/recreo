# Retro du sprint 22 - couverture maximale du catalogue

## Ce qui a marche

- **Le premier cycle complet du sprint** : trois paquets refuses le 2026-08-29, trois corrections
  livrees sur la tete de chaque branche d'origine, trois Done pedagogique prononces le 2026-08-30.
  Aucun des trois n'entre au catalogue sans que son motif de refus ne soit ferme, et aucun ne
  reste bloque non plus - le premier sprint ou la totalite des paquets livres passe par un refus
  puis aboutit dans le meme sprint.
- **`camille-po` a instruit `DEC-0045` section 3 au troisieme cas, comme elle l'avait annonce a
  s21** plutot que de le repousser une nouvelle fois ou de l'inferer sur deux cas insuffisants.
  Le motif qu'elle avait ecrit pour refuser a s21 - *"cette conception a besoin de plus de deux
  cas, ou d'un arbitrage delibere"* - est exactement le critere qu'elle applique aujourd'hui pour
  trancher : une regle de gouvernance tenue au meme niveau d'exigence que les proprietes de
  contenu qu'elle-meme reproche a `nadia-prof` de sous-verifier.
- **La reponse de `nadia-prof` sur sa capacite pour s23 nomme une limite avant qu'elle ne soit
  demandee** : quatre a cinq paquets, sous trois conditions dont une qui engage directement le
  planning (au plus un paquet par sprint portant une propriete non automatisable du type de
  `COMP-2`), et un refus explicite d'echantillonner pour tenir un chiffre - *"deux de mes trois
  refus du 2026-08-29 seraient passes au travers d'un echantillon."* Une porte qui refuse de
  s'assouplir pour un chiffre de capacite est exactement ce que `CLAUDE.md` demande d'elle.
- **Fusion des trois derniers lots sans aucun conflit**, lignee et fichiers touches verifies avant
  fusion plutot que crus sur la description du travail a merger.

## Ce qui a coince

### Le fil rouge tient une seconde fois

Sept defauts a s21, un nombre comparable a s22, tous de la meme famille deja nommee a la retro
precedente : **un vert ou un chiffre s'affiche sans que ce qui compte ait ete regarde.** Le dernier
exemple de ce sprint, trouve non par un balayage mais par la relecture de fin de cycle : un
commentaire d'en-tete de `cm1-comprehension.yaml` affirmait *"17 items sur 20"* marques `COMP-3`,
alors que la correction meme qui repondait au refus de `nadia-prof` avait porte ce chiffre a 20 sur
20 - le commentaire a survecu, faux, a la correction qui l'a rendu faux. `relecteur-pedagogique`
l'a corrige a la fusion de cloture, dans le meme geste que `camille-po` versait la meme observation
en reserve de backlog depuis une autre branche, a 82 secondes d'ecart : les deux ignoraient le
travail de l'autre, et le depot porte aujourd'hui une story de backlog pour un defaut deja corrige.
Aucune consequence de fond - le motif de refus original de `nadia-prof` (`COMP-2`, trois items
resolubles sans lire le texte) est distinct de ce compte-la - mais c'est un signe de plus que ce
fil rouge n'est pas referme : deux agents ont trouve, corrige et documente independamment le meme
petit fait sans jamais se rejoindre.

### Deux chartes d'agent contredisant le depot, en deux sprints consecutifs

A s21, les fiches de `marc-dev` et `lea-dev` interdisaient de rediger le contenu d'un paquet, la
ou la pratique reelle depuis s21 le faisait deja sans incident. A s22, `.claude/agents/relecteur-
pedagogique.md` rendait le controle de l'accentuation du texte enfant **facultatif** ("si tu
reperes"), la ou `DEC-0002` (amendement du 2026-08-04) le rend **systematique** depuis dix-huit
sprints ; `.claude/agents/nadia-prof.md` portait une formule assez large pour laisser croire que
cette meme porte lui appartenait. Dans les deux cas, la contradiction n'a ete decouverte que parce
qu'un agent a bute dessus en situation reelle - `nadia-prof` refusant `S13-2` sur ce motif precis -
jamais par un balayage systematique des fiches contre les decisions en vigueur. **Aucun garde-fou
du depot ne verifie qu'une charte d'agent reste coherente avec les `DEC` et `ADR` qui la
concernent.** C'est un obstacle neuf, pas encore route : la faisabilite d'un tel controle (une
charte cite-t-elle une regle qu'un `DEC` ou un `ADR` posterieur a corrigee ?) revient a
`theo-archi` ; la priorite de le construire, a `camille-po`, pour la prochaine planning.

### `nadia-prof` en tort deux fois sur son propre reaudit

En levant ses trois refus, `nadia-prof` trouve elle-meme deux failles dans sa propre position du
2026-08-29 :

- Sa reserve sur `S13-3` (l'erreur de base 100 produit mecaniquement plus de 59 minutes sur la
  forme "duree") est en tension avec sa propre condition `D-6`, qui n'admet ce type d'anomalie que
  si elle n'est pas la seule du paquet - et elle l'est.
- La correction qu'elle avait exigee sur `D-6` cree elle-meme une regularite structurelle neuve :
  sur les douze items "horaire d'arrivee", la bonne reponse n'est jamais la plus petite des trois.

Elle nomme les deux comme des constats neufs et dates, pas des durcissements retroactifs de ses
propres conditions - exactement la discipline que sa charte lui demande ("tu ne deplaces pas tes
poteaux"). Le fait notable pour la squad n'est pas la faute, c'est la methode : une porte
pedagogique qui trouve ses propres angles morts en se relisant vaut mieux qu'une porte qui ne se
relit jamais.

## Amendements a `squad/README.md`

### Decide (domaine propre : le format des ceremonies)

**Un fichier de ceremonie encore ouverte peut recevoir un ajout date, sous quatre conditions.** La
regle "un fichier par ceremonie, jamais d'ajout" (section 2) visait a interdire de fabriquer une
ceremonie retrospective une fois close - elle n'a jamais eu pour objet d'interdire de completer une
ceremonie qui n'a pas fini d'avoir lieu. `review.md` de ce sprint a recu cinq ajouts dates pendant
qu'un cycle `REFUSE` -> correction -> reaudit restait ouvert : chacun exact a sa date, aucun
n'efface le precedent, et l'ensemble a produit un historique complet et lisible d'un cycle qui a
dure trois jours. J'ai ecrit cette exception directement dans `squad/README.md` section 2, avec ses
quatre conditions (ceremonie non close, ajout date et signe, aucune modification du texte anterieur,
la cloture le dit explicitement) : c'est mon domaine de proprietaire des ceremonies, je ne le
propose pas, je le tranche.

### Proposes, avec qui decide

1. **Construire un garde-fou qui verifie qu'une charte d'agent (`.claude/agents/*.md`) ne contredit
   aucun `DEC` ou `ADR` en vigueur qui la concerne.** Deux sprints consecutifs ont trouve une telle
   contradiction par accident, jamais par balayage. Faisabilite technique **a `theo-archi`**,
   priorite de mise en oeuvre **a `camille-po`** pour la planning de s23.
2. **La capacite de `nadia-prof` pour s23** (quatre a cinq paquets, sous les trois conditions de son
   avis section 12.3, dont la limite d'un paquet par sprint portant une propriete non automatisable)
   n'est pas tranchee par ce document : c'est une donnee d'entree pour la prochaine planning, **a
   `camille-po`** pour le perimetre qu'elle en tire et **a moi-meme** pour l'affectation entre Marc,
   Lea et moi qui en decoule.

## Decisions prises

Une seule, dans mon propre domaine : l'amendement a `squad/README.md` section 2 sur les ajouts
dates a une ceremonie encore ouverte, ecrit ci-dessus et applique dans le meme geste. Aucune autre
decision de perimetre, d'architecture ou de pedagogie n'est prise par ce document : le garde-fou de
coherence des chartes et la capacite de `nadia-prof` pour s23 sont proposes, chacun avec le nom de
qui doit trancher, et aucun des deux n'est acte ici.
