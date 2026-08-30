# DEC-0056 - La porte de l'accentuation du texte lu par l'enfant reste celle de
# `relecteur-pedagogique`, telle que `DEC-0002` l'a deja tranchee le 2026-08-04 : ce n'est pas un
# arbitrage neuf, c'est la correction d'une derive trouvee dans deux fiches d'agent

- **Type** : clarification de perimetre de role, sur une decision deja prise
- **Date** : 2026-08-30
- **Auteur** : camille-po
- **Sprint** : s22 (cloture)
- **Competence programme visee** : transverse - pas de contenu pedagogique. Cette decision statue
  sur un perimetre de role, pas sur une competence du programme.
- **Statut** : Acceptee

## Contexte - ce qui m'a ete demande

`nadia-prof` a refuse `S13-2` (`cm1-multiplications-divisions-10-100`) le 2026-08-29 sur un seul
motif : les vingt-cinq indices du paquet ne sont pas accentues. Elle a leve ce refus le lendemain,
mais elle pose une question avant de clore le sujet : **a qui appartient la verification de
l'accentuation du texte qu'un enfant lit dans un item ?** `CLAUDE.md` l'assigne au
`relecteur-pedagogique` ; sa propre fiche d'agent (`.claude/agents/nadia-prof.md`) lui assigne
"ce que l'enfant lit a l'ecran", plus large. Aucun garde-fou executable n'existe aujourd'hui, et
le defaut a traverse une CI verte et un lot entier livre.

**Sa position, telle qu'elle l'ecrit** : elle maintient que cette porte est la sienne, non sur sa
fiche de role, mais sur un appui different - l'exigence d'accentuation a ete ajoutee par
l'amendement du 2026-08-04 **a `DEC-0002`**, la clause qui est deja la sienne pour le contenu
destine a l'enfant, et non aux conventions de redaction du depot. *"L'amendement suit la porte, il
ne la deplace pas."* Mais elle ne demande pas a rester seule dessus : la propriete se formule sans
jugement et se teste sur un lexique, a la difference de `COMP-2`.

## Ce que j'ai verifie avant de trancher, contre la source primaire et non contre un resume

Je n'ai pas tranche sur la lecture de `CLAUDE.md` seule. J'ai lu `DEC-0002` et son amendement du
2026-08-04 en entier, parce que c'est la decision que `nadia-prof` cite elle-meme comme son appui.

**Ce que l'amendement du 2026-08-04 dit reellement, dans ses propres mots** (`DEC-0002`, section
"Amendement du 2026-08-04") :

> *"Le manque. La decision exemptait la categorie B [le contenu destine a l'enfant] de la regle de
> redaction du depot sans y mettre aucune exigence positive a la place. Elle retirait donc ce
> texte du controle du `relecteur-pedagogique` et le confiait a la seule porte pedagogique de
> `nadia-prof`, qui ne s'exerce qu'une fois par sprint."*
>
> *"La correction. J'ajoute a la categorie B une exigence positive, verifiable et executoire, que
> le `relecteur-pedagogique` controle desormais comme n'importe quelle autre regle de redaction
> du depot [...]. Elle ne remplace pas la porte pedagogique de `nadia-prof`, qui reste seule a
> juger si le vocabulaire, le ton et le fond sont adaptes au niveau vise ; elle ajoute un filet de
> securite mecanique et systematique (orthographe, accentuation, ponctuation) qui s'exerce a
> chaque relecture de livrable, pas une seule fois par sprint."*

**Cet amendement a ete redige a la demande de `nadia-prof` elle-meme**, dans le cadre du refus
pedagogique de `bandit-manchot-additions-cp` au sprint 0, et son motif d'alors est exactement
l'inverse de sa lecture d'aujourd'hui : elle demandait que ce point sorte de sa seule porte,
**parce que sa porte ne s'exerce qu'une fois par sprint** et laisse fuiter ce genre de defaut
entre-temps - precisement ce qui vient de se reproduire avec `S13-2`. **L'amendement ne suit pas
la porte : il la deplace, explicitement, et c'est ce que sa propre redaction de 2026-08-04
demandait.**

**Ce que `CLAUDE.md` en dit aujourd'hui n'est donc pas une erreur de paraphrase** : c'est la
retranscription fidele de ce que `DEC-0002` a decide. Je ne trouve aucun ecart entre les deux, a
la difference de ce que j'ai deja vu ailleurs dans ce depot (`CLAUDE.md`, note du 2026-08-17, une
reference qui pointait vers un `ADR` inexistant).

## Ce que j'ai trouve en plus, qui explique la confusion

**La derive n'est pas dans `CLAUDE.md`, elle est dans deux fiches d'agent que personne n'avait
recalees sur `DEC-0002` depuis le 2026-08-04.**

- `.claude/agents/nadia-prof.md` porte, dans sa section "Frontieres" : *"Tu ne corriges pas la
  documentation technique, la Javadoc ni le nommage : c'est le `relecteur-pedagogique`. Tu
  corriges en revanche ce que l'enfant lit a l'ecran."* Cette phrase ne cite pas nommement
  l'accentuation ; elle est assez large pour se lire comme une porte generale sur tout ce qui est
  lu par l'enfant, orthographe comprise - **c'est cette generalite qui a rendu sa lecture du
  2026-08-30 plausible**, sans jamais avoir ete corrigee depuis l'amendement.
- `.claude/agents/relecteur-pedagogique.md` porte une phrase plus grave, parce qu'elle contredit
  `DEC-0002` sur le fond et non sur la forme : *"Tu ne juges pas ce que lit un enfant [...]. Ca
  appartient a `nadia-prof`, qui evalue le niveau scolaire et pas seulement la correction de la
  langue. Si tu reperes une faute d'orthographe evidente dans un contenu destine aux enfants,
  corrige-la et signale-la a `nadia-prof` plutot que de reformuler."* Cette phrase reduit le
  `relecteur-pedagogique` a une correction **facultative et occasionnelle** ("si tu reperes"), la
  ou `DEC-0002` demande un controle **systematique, a chaque relecture de livrable** - exactement
  la difference que l'amendement de 2026-08-04 a ete ecrit pour fermer. **C'est cette fiche, pas
  celle de `nadia-prof`, qui porte l'ecart le plus direct avec la decision en vigueur.**

**Aucune des deux fiches n'a jamais ete corrigee par une decision** apres l'amendement du
2026-08-04 : la derive n'est d'aucune mauvaise foi, elle vient d'un texte de decision qui n'a
jamais ete reporte dans les deux fiches qu'il concernait.

## Decision

### 1. La porte reste celle de `relecteur-pedagogique`, sans arbitrage neuf

Ce document **ne tranche rien que `DEC-0002` n'ait deja tranche le 2026-08-04**. Le controle
systematique - a chaque relecture de livrable, pas une fois par sprint - que le texte destine a
l'enfant est redige en francais correct, integralement accentue, avec une orthographe et une
ponctuation irreprochables, appartient au `relecteur-pedagogique`. `nadia-prof` garde, inchange,
ce qu'elle a toujours garde seule : le jugement sur le vocabulaire, le ton, le fond et le niveau
scolaire du texte.

**Ce que je dis a `nadia-prof`, dans les mots qu'elle a employes** : son refus du 2026-08-29 sur
`S13-2` reste un refus legitime - le defaut etait reel, quel qu'en soit le juge, et sa porte
pedagogique reste ouverte tant qu'aucun autre controle ne le rattrape en amont. Ce que je corrige
n'est pas son geste, c'est la lecture qu'elle en tire pour la suite : la porte n'a jamais ete
deplacee vers elle, elle est restee ou l'amendement l'a mise le 2026-08-04, a sa propre demande.

### 2. Les deux fiches d'agent sont a corriger pour refleter ce que `DEC-0002` decide deja

Je ne les corrige pas moi-meme : je n'ecris pas les fiches d'agent, c'est un choix de process qui
n'est pas le mien a trancher seule. Je route la correction, avec le texte exact a aligner :

- `.claude/agents/relecteur-pedagogique.md` : la phrase *"Si tu reperes une faute d'orthographe
  evidente [...], corrige-la et signale-la a `nadia-prof` plutot que de reformuler"* est a
  remplacer par un controle systematique et non conditionnel a "si tu reperes", conforme au
  "filet de securite mecanique et systematique" de `DEC-0002`.
- `.claude/agents/nadia-prof.md` : la phrase *"Tu corriges en revanche ce que l'enfant lit a
  l'ecran"* est a preciser pour ne plus laisser croire qu'elle couvre l'orthographe et
  l'accentuation - son perimetre reste le vocabulaire, le ton, le fond et le niveau scolaire.

### 3. La faisabilite d'un garde-fou executable n'est pas ma decision

`nadia-prof` note elle-meme que cette propriete se formule sans jugement (*"aucune chaine lue par
un enfant ne contient un mot francais qui prendrait un accent et ne le porte pas"*) et se teste sur
un lexique, a la difference de `COMP-2`. Je ne tranche pas si un tel garde-fou est constructible ni
sous quelle forme : je route la question, en parallele de ce document, a `theo-archi`.

## Ce que je route

- **`sofia-sm`** : faire corriger `.claude/agents/relecteur-pedagogique.md` et
  `.claude/agents/nadia-prof.md` selon la section 2, par l'agent ou le mecanisme qu'elle juge
  approprie pour ce type de fichier.
- **`theo-archi`** : faisabilite d'un garde-fou executable sur l'accentuation du texte servi par un
  paquet de contenu (pas seulement les chaines de `catalogue.yaml`, deja couvertes par
  `TexteAccentueExposeParLePortEntrantTest`), sur la population entiere d'un fichier et non un
  echantillon - methode que `nadia-prof` a deja executee a la main trois fois ce sprint. Je ne
  prejuge d'aucune reponse.
- **`nadia-prof`** : rien de nouveau sur sa porte. Le fond de son refus du 2026-08-29 reste acquis
  et n'est pas rouvert par ce document.

## Ce que je ne fais pas

- Je ne modifie pas `DEC-0002` : elle reste exacte, telle qu'ecrite le 2026-08-04. Ce document ne
  l'amende pas, il confirme sa lecture contre les deux fiches qui en avaient derive.
- Je ne modifie aucune fiche d'agent moi-meme.
- Je ne tranche pas la faisabilite technique d'un garde-fou.
- Je ne rouvre aucun verdict de `nadia-prof` et ne fais bouger aucune ligne de
  `squad/programme/MATRICE-couverture.md`.

## Ce que cette confusion a coute, pour que le chiffre ne se reperde pas

Un lot entier (`S13-2`) a ete refuse pour une correction de quatre chaines, sur un motif que la
decision en vigueur depuis le 2026-08-04 assignait deja a un controle systematique qui n'existait
pas dans les faits - ni comme garde-fou automatique (aucun n'existe, confirme par `nadia-prof` et
`yanis-qa`), ni comme relecture manuelle systematique (la fiche de `relecteur-pedagogique` la
rendait facultative, "si tu reperes"). Le cout n'est pas dans le refus, qui etait fonde : il est
dans l'ecart, jamais corrige depuis dix-huit sprints, entre ce qu'une decision ecrite demandait et
ce que la fiche d'un agent faisait reellement.

## Impact

- `squad/decisions/produit/DEC-0056-...md` (ce fichier) : cree.
- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0056` ajoutee.
- `DEC-0002` : non amendee, non contredite. Ce document confirme sa lecture.
- `.claude/agents/relecteur-pedagogique.md`, `.claude/agents/nadia-prof.md` : correction requise,
  routee a `sofia-sm`, non executee ici.
- `squad/programme/MATRICE-couverture.md` : inchangee.
