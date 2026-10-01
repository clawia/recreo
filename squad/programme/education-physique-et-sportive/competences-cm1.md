# Référentiel de compétences - Éducation physique et sportive - CM1 (cycle 3)

> Écrit par `nadia-prof` le 2026-09-03, sur arbitrage de l'utilisatrice du même jour (`DEC-0074`),
> au même grain et dans la même forme que
> `squad/programme/histoire-geographie/competences-cm1.md`, écrit le 2026-09-02. **Socle réaliste,
> non exhaustif et non officiel** : à compléter et à faire valider par l'utilisatrice au fil des
> sprints, en lien avec `squad/programme/MATRICE-couverture.md`. Ne remplace pas une lecture des
> programmes officiels de l'Éducation nationale.
>
> **Audit dégradé, et je le déclare** : je n'ai joué aucun incrément, parce qu'il n'en existe aucun
> sur cette matière. Ce document **ne prononce aucun Done pédagogique**, **n'écrit pas la matrice**
> (`camille-po`) et **ne priorise pas** (l'utilisatrice).
>
> Le programme d'EPS de cycle 3 s'organise en **quatre champs d'apprentissage** - produire une
> performance mesurable ; adapter ses déplacements à des environnements variés ; s'exprimer devant
> les autres par une prestation artistique ou acrobatique ; conduire et maîtriser un affrontement -
> doublés de compétences générales (développer sa motricité, s'approprier des méthodes et des
> outils, partager des règles et assumer des rôles, apprendre à entretenir sa santé).
>
> **Le résultat de ce fichier est dur et il est écrit sans être adouci : huit lignes sur dix sont
> `non couvrable en l'etat`, toutes de nature irréversible.** Les dix lignes sont écrites quand
> même, entièrement : une matrice qui n'écrirait que les deux lignes jouables ferait passer deux
> connaissances pour un enseignement.
>
> **Codes de ligne ajoutés le 2026-09-07 (`camille-po`, `DEC-0081`), conformément à `ADR-0081`.** Ce
> référentiel n'était codé par aucun préfixe : aucun paquet ne le citait avant ce jour. La
> numérotation suit l'ordre de lecture des quatre domaines - `EPS-CM1-01` à `EPS-CM1-10`. Aucun
> intitulé n'est modifié.

## Produire une performance optimale, mesurable à une échéance donnée

- [EPS-CM1-01] Réaliser une performance mesurée en course, en saut ou en lancer, et la comparer à ses résultats précédents.
- [EPS-CM1-02] Se déplacer dans l'eau sur une distance donnée en assurant sa propre sécurité.

## Adapter ses déplacements à des environnements variés

- [EPS-CM1-03] Adapter ses déplacements à un milieu inhabituel (grimper, s'équilibrer, se déplacer en milieu naturel) en respectant les règles de sécurité.
- [EPS-CM1-04] Suivre un itinéraire à l'aide d'un plan ou d'une carte et y retrouver des repères.

## S'exprimer devant les autres par une prestation artistique ou acrobatique

- [EPS-CM1-05] Composer et présenter devant les autres une courte chorégraphie ou un enchaînement acrobatique.
- [EPS-CM1-06] Observer la prestation d'un camarade et lui formuler un retour simple et respectueux.

## Conduire et maîtriser un affrontement collectif ou interindividuel

- [EPS-CM1-07] Conduire un affrontement collectif : se placer, coopérer avec ses partenaires et atteindre un but commun.
- [EPS-CM1-08] Conduire un affrontement interindividuel : adapter ses actions à celles de l'adversaire.
- [EPS-CM1-09] Tenir les rôles d'arbitre, d'observateur ou de marqueur dans un jeu, et en appliquer les règles.

## Apprendre à entretenir sa santé

- [EPS-CM1-10] Connaître les effets de l'activité physique sur le corps (échauffement, essoufflement, récupération, hydratation) et les règles de sécurité qui l'entourent.

---

## Jugement de faisabilité (`nadia-prof`, 2026-09-03)

**Les moyens du produit** : la mécanique de **choix** (`bandit-manchot`), la mécanique de
**saisie**, le **palier 1 visuel** (`ADR-0073`). **Aucun son, aucun geste de tracé, aucune
persistance** (`DEC-0028`, `DEC-0046`, `DEC-0066`). **Et, pour cette matière-ci, la contrainte
décisive n'est même pas dans cette liste : le produit ne reçoit aucun mouvement du corps de
l'enfant, et rien dans son architecture ne s'y prête.** Ce n'est pas une lacune d'outillage, c'est
ce que le produit est. C'est le même constat que celui que j'ai posé le même jour sur l'EPS de CP,
et il vaut à l'identique ici.

`EPS-1` (la règle d'un jeu n'est interrogée que si elle est rappelée dans l'énoncé ou
universellement scolaire), `EPS-2` (aucun item ne compare des corps, des performances ou des
capacités entre enfants, et aucune performance chiffrée n'est présentée comme une norme) et
`EPS-3` (aucun item ne fait porter une attitude ou une conduite morale par une bonne réponse),
posés le 2026-09-03 dans `squad/programme/education-physique-et-sportive/competences-cp.md`,
**s'appliquent ici à l'identique**. `BIA-4` aussi : aucun item n'interroge l'enfant sur sa propre
pratique ni sur son propre corps.

La numérotation ci-dessous suit l'ordre de lecture de la liste de compétences.

| # | Compétence | Situation | Motif |
|---|---|---|---|
| 1 | Performance mesurée et comparée à la précédente | **`non couvrable en l'etat`**, nature **irréversible** | Geste moteur mesuré. Aucun capteur, aucune mécanique de ce produit n'observe un corps ; et la performance elle-même se juge par un adulte qui regarde et chronomètre (`ADR-0008`). Le produit ne conserve rien d'une session à l'autre (`DEC-0028`), mais **ce motif-là est superflu** : lever la persistance ne rouvrirait rien tant qu'il n'y a rien à mesurer |
| 2 | Se déplacer dans l'eau | **`non couvrable en l'etat`**, nature **irréversible** | Geste moteur dans un milieu physique |
| 3 | Adapter ses déplacements à un milieu inhabituel | **`non couvrable en l'etat`**, nature **irréversible** | Geste moteur. **Une simulation à l'écran d'un parcours d'équilibre ne fait pas tenir en équilibre** : ce serait une contrefaçon (`CON-1`), pas une couverture |
| 4 | Suivre un itinéraire avec un plan ou une carte | **`non couvrable en l'etat`**, nature **irréversible** | Le geste de la ligne est **le déplacement**, pas la lecture. **Recouvrement à ne pas double-compter** : lire un plan et y situer un repère est une compétence de **géographie**, déjà portée par histoire-géographie CM1 (ligne 9, en attente du palier 2). La compter ici ferait entrer deux fois la même chose au dénominateur, dans le sens qui arrange |
| 5 | Composer et présenter une chorégraphie ou un enchaînement | **`non couvrable en l'etat`**, nature **irréversible** | Production corporelle libre devant des spectateurs, jugement humain (`ADR-0008`) |
| 6 | Observer un camarade et lui formuler un retour | **`non couvrable en l'etat`**, nature **irréversible** | Production orale libre, aucune bonne réponse unique, et il n'y a **pas d'autre enfant devant l'écran**. `EPS-3` |
| 7 | Conduire un affrontement collectif | **`non couvrable en l'etat`**, nature **irréversible** | Se placer et coopérer se font en situation, dans une équipe qui n'existe pas ici. **Et je refuse d'avance la contrefaçon tactique** : un item *"où te places-tu quand ton partenaire a le ballon ?"* mesure une réponse d'école, pas un placement en jeu |
| 8 | Conduire un affrontement interindividuel | **`non couvrable en l'etat`**, nature **irréversible** | Même motif : adapter ses actions à l'adversaire suppose un adversaire |
| 9 | Tenir les rôles d'arbitre, d'observateur, de marqueur | **`non couvert`** - jouable sur la seule moitié **connaissance**, **sous `EPS-1`** | La règle appliquée à une situation décrite est une connaissance vérifiable, et c'est bien le travail de l'arbitre : *"Le ballon sort sur la ligne de côté, à qui la remise en jeu ?"*. **Tenir le rôle en situation ne l'est pas.** Mention exigée d'avance : *"connaissance et application de la règle seules ; la tenue du rôle en situation reste non couverte"* |
| 10 | Effets de l'activité physique et règles de sécurité | **`non couvert`** - jouable au choix textuel, **sous `EPS-2` et `BIA-4`**, et sous `EPS-9` depuis le 2026-09-25 | *"Comment un échauffement doit-il se dérouler ?"*, bonne réponse *"des mouvements doux d'abord, puis de plus en plus vifs"*, est une connaissance exacte, utile, du niveau du CM1 : la progressivité de l'échauffement, que le CP ne sert pas. **Frontière à tenir avec les sciences** (ci-dessous). Exemple remplacé le 2026-09-25 (note ci-dessous) |

**Correction du 2026-09-25 (`nadia-prof`), ligne 10.** L'exemple que j'avais écrit le 2026-09-03 était
*"'Pourquoi s'échauffe-t-on avant de courir ?' est une connaissance exacte, utile, du niveau du
CM1."* Il n'était pas jouable au CM1 : cette question est déjà l'item 18 de `cp-eps-corps-effort`
(*"Pourquoi échauffe-t-on ses muscles avant un effort intense ?"*), et la clause 2 d'`EPS-9`, que
j'ai écrite le 2026-09-25, interdit au CM1 de demander *"pourquoi l'une de ces trois règles
existe"*. J'avais donc mis en avant, comme exemple de la ligne, la seule formulation que j'ai ensuite
interdite, et le paquet de s43 ne sert aucun item d'échauffement (réserve `EPS-CM1-R3`,
`squad/conformite/FICHE-bandit-manchot-effets-activite-eps-cm1.md`, section 4, point b). Le nouvel
exemple porte sur la progressivité de l'échauffement, précision que la clause 1 d'`EPS-9` admet et
que le CP ne connaît pas (il ne sert que *"des mouvements légers"*) ; il ne demande ni le moment, ni
la raison, ni le risque, et aucune de ses propositions n'est une bonne réponse du CP. Cette
correction ne lève pas `EPS-CM1-R3` : la réserve se lève quand un item d'échauffement est servi.

**Correction du 2026-10-01 (`nadia-prof`, s45), ligne 9.** L'exemple *"Le ballon sort sur la ligne
de côté, à qui la remise en jeu ?"* n'a pas de réponse tant que l'énoncé ne dit pas qui a touché le
ballon en dernier, et il a la forme de la tâche B de `cp-eps-regles-jeu-collectif`, déjà servie au
CP. Exemple de remplacement : *"Un joueur bleu envoie le ballon hors du terrain, sur le côté. Qui
remet le ballon en jeu ?"*, bonne réponse *"l'équipe rouge, sur le côté"*. La ligne est désormais
aussi sous `EPS-10`, le discriminant CM1/CP posé dans
`squad/conformite/ARBITRAGE-PREALABLE-s45-cinq-lots.md`, section 4.

### Recouvrement à ne pas double-compter

Sciences et technologie CM1 porte déjà : *"Relier l'alimentation, l'activité physique, le sommeil
et l'hygiène au bon fonctionnement du corps"*. La ligne 10 ci-dessus porte sur les **effets de
l'effort** et sur ce qui l'**encadre** - échauffement, hydratation pendant l'effort, récupération,
sécurité. Ce sont deux compétences distinctes et elles coexistent, **à condition que les items
d'EPS ne dérivent pas vers le sommeil, les repas ou l'hygiène du corps** : la même compétence
serait alors comptée deux fois sous deux matières, sans que rien ne le signale. C'est la même
frontière que celle posée le même jour entre l'EPS de CP et *questionner le monde*.

### Mention exigée d'avance si une ligne bouge

Si les lignes 9 ou 10 passent un jour à `couvert-valide`, la matrice porte cette mention :
*"connaissances qui entourent la pratique physique seules ; aucune activité physique, aucun geste
et aucune performance ne sont servis par ce produit"*. **Deux lignes vertes sur dix ne doivent en
aucun cas se lire comme une couverture partielle de l'EPS** : ce qui serait couvert, c'est ce qu'on
sait à propos de l'EPS, pas l'EPS.

### Bilan chiffré - éducation physique et sportive CM1

| | Nombre |
|---|---|
| Lignes du référentiel | **10** |
| dont `non couvert` | **2** (lignes 9 et 10) |
| dont `non couvrable en l'etat` | **8** (lignes 1 à 8), toutes de nature **irréversible** |
| **Entrent au dénominateur atteignable** | **2** |
| dont jouables **dès aujourd'hui** par une mécanique existante | **2** |
| dont en attente du **palier 2** | **0** |

**Huit lignes sur dix sont hors de portée définitive de ce produit, et aucune décision produit ne
les rouvrira.** Ce n'est pas un échec : l'EPS s'exerce avec le corps, et un écran qui pose des
questions n'est pas un gymnase. Le dire clairement vaut mieux que d'inventer deux lignes de plus.

**Ce dont je reste la seule garde** : `EPS-1` à `EPS-3`, `BIA-4`, la frontière avec les sciences
ci-dessus, et les deux contrefaçons refusées en lignes 3 et 7. Aucune CI ne verra jamais qu'un
paquet d'EPS ne fait bouger personne.
