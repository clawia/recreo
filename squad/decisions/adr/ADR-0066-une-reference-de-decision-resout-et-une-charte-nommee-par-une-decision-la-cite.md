# ADR-0066 - Une reference de decision resout, et une charte nommee par une decision cite cette decision

- **Statut** : Accepte
- **Date** : 2026-08-30
- **Sprint** : s23
- **Auteur** : theo-archi
- **Demandeur** : `sofia-sm` (retro de s22), qui route la faisabilite d'un garde-fou de coherence
  entre les chartes d'agent et les decisions du depot

## Contexte

Deux sprints consecutifs ont trouve une charte d'agent qui contredisait une decision du depot. Dans
les deux cas, la contradiction a ete trouvee **parce qu'un agent a bute dessus**, jamais par un
balayage ; le second cas vivait depuis dix-huit sprints (`DEC-0056` : ni
`.claude/agents/nadia-prof.md` ni `.claude/agents/relecteur-pedagogique.md` n'avaient ete recalees
sur l'amendement du 2026-08-04 a `DEC-0002`, et l'une des deux le contredisait sur le fond).

**La demande, telle qu'elle m'est routee, n'est pas entierement mecanisable, et je le dis avant de
dire ce que je prends.** Constater qu'une charte *contredit* une decision demande de lire deux
textes en langue naturelle et de juger. Aucun automate de ce depot ne le fera, et une regle qui
pretendrait le faire serait pire que rien.

**Ce que j'ai mesure avant de proposer quoi que ce soit**, parce que deux mecanismes qui semblaient
evidents se sont reveles vides :

- Perimetre : tout fichier texte du depot sous la racine derivee du pom agregateur, hors `.git`,
  hors `target`, hors arbres de contre-exemple. Arbre `890732f` plus le present travail.
  **1 445 fichiers, 121 identifiants de decision distincts, 11 196 occurrences.** Une seule
  reference ne resolvait vers rien - `ADR-0065`, ecrit dans ce meme sprint et pas encore commite au
  moment de la mesure.
- **Les neuf chartes d'agent ne citaient collectivement que six identifiants** avant s22 :
  `camille-po` deux, `ines-design` un, `theo-archi` un, `yanis-qa` un, `nadia-prof` un ;
  `lea-dev`, `marc-dev` et `sofia-sm` **aucun**. Une regle qui verifierait que les references des
  chartes resolvent porterait donc sur presque rien.
- **Une seule decision de tout `squad/decisions/` nomme une charte par son chemin** : `DEC-0056`,
  ecrite la veille.

C'est le point important, et il vaut d'etre ecrit sans le maquiller : **le lien qu'un garde-fou de
coherence devrait verifier n'existe pas encore dans les artefacts.** La part mecanisable n'est donc
pas de verifier la coherence - c'est de rendre le lien obligatoire et verifiable la ou il est
affirme.

## Decision

**Deux clauses, chacune decidable, et rien de plus.**

1. **Toute reference `ADR-XXXX` ou `DEC-XXXX` ecrite dans le depot resout vers un fichier de
   `squad/decisions/`.** C'est la famille du defaut du 2026-08-17 : `CLAUDE.md`, le fichier que tous
   les agents lisent, a renvoye pendant deux jours vers "`ADR-0045`, section Prerequis de mesure",
   qui n'existait pas. Une reference qui pointe vers rien est de la meme famille qu'un chiffre sans
   methode.
2. **Une charte d'agent nommee par son chemin dans la section `## Impact` d'une decision cite cette
   decision.** C'est la cause exacte du cas de s22, dans les mots de `DEC-0056` : *"un texte de
   decision qui n'a jamais ete reporte dans les deux fiches qu'il concernait"*. Le lien n'a pas
   besoin d'etre juste pour etre verifiable ; il a besoin d'exister dans les deux sens.

## Ce que ce garde-fou ne verra pas

1. **La contradiction en langue naturelle.** Une charte qui cite `DEC-0002` et dit exactement le
   contraire est verte. C'est le coeur de la demande de `sofia-sm`, et c'est la part que je refuse
   d'encoder plutot que de l'encoder mal.
2. **Une decision qui statue sur une charte sans nommer son chemin.** Si une `DEC` ecrit "la fiche
   de `nadia-prof`" sans ecrire `.claude/agents/nadia-prof.md`, la clause 2 ne voit rien. Ce trou
   est reel et il est le prix de la seule ancre non ambigue disponible : le nom d'un agent apparait
   dans presque toutes les decisions, son chemin de fichier n'apparait que quand on statue sur sa
   charte.
3. **Une decision sans section `## Impact` est hors de portee de la clause 2.** Mesure au
   2026-08-30 : 63 des 122 decisions du depot n'en portent pas, et ce sont presque toutes des `ADR`
   (59 sur 64). La clause 2 s'exerce donc aujourd'hui sur les `DEC` et sur trois `ADR`. C'est le
   prix de refuser un perimetre qui echoue par inclusion, et c'est un prix visible.
4. **Une reference vers une *section* inexistante d'un `ADR` qui, lui, existe.** C'est litteralement
   le defaut du 2026-08-17 dans sa forme complete : `ADR-0045` existait, sa section "Prerequis de
   mesure" non. La clause 1 attrape la moitie du cas - l'identifiant faux - pas l'autre.
5. **Ce qui n'est pas ecrit.** Une charte qui omet une decision qui la concerne, sans que cette
   decision la nomme, ne produit aucun rouge.
6. **Les arbres de contre-exemple sont hors perimetre**, leur raison d'etre etant de porter des
   defauts. Un vrai defaut ecrit dans un arbre de contre-exemple serait invisible.

## Regle executable

`bootstrap/src/test/java/com/recreo/archi/ToutesLesReferencesDeDecisionResolventTest.java`.

**Ce n'est pas une regle ArchUnit** : l'objet juge est l'arbre de fichiers du depot, pas un graphe
de types. Le compte de regles ArchUnit du depot est inchange. Le mecanisme automatique qui la garde
est la porte technique - `mvn verify` sur le reacteur complet.

Perimetre derive, jamais enumere : les fichiers sous la racine derivee du pom agregateur
(`LesGardeFousSontMisALEpreuveTest.racineDuDepot()`, deja ouverte au paquet par `ADR-0064`), sauf
trois familles ecartees **par une propriete** - les repertoires de sortie de build, les arbres de
contre-exemple, et les fichiers binaires reconnus a l'octet nul qu'ils contiennent plutot qu'a leur
extension (`ADR-0036` : un garde-fou ne lit pas un binaire comme du texte).

**Trois clauses** : le depot reel, le compte d'objets reellement examines (`ADR-0051`), et une
morsure sur depot fictif portant les deux defauts a la fois.

### Deux preuves, et toutes les deux se sont exercees contre moi

- **La regle a mordu son auteur avant de mordre personne d'autre**, sur du materiau reel et non sur
  une fixture : a sa premiere execution, elle a echoue en nommant `ADR-0065` et `ADR-0066`, cites
  par les sources du meme sprint et pas encore ecrits. C'est exactement le geste qu'elle est censee
  interdire, et le seul commit qui la rend verte est celui qui ecrit ces deux `ADR`.
- **Elle a trouve deux defauts de sa propre conception** dans le meme rouge, et aucun des deux
  n'etait visible a la lecture. Le premier : la source du garde-fou nomme, dans son assertion, les
  identifiants de son depot fictif, et le balayage les comptait comme des references cassees. Ferme
  **par derivation et non par une liste** - un fichier `<Nom>.java` peut nommer les identifiants
  trouves sous `contreexemple/<Nom>/`, et aucun autre. Le second est plus grave, parce qu'il portait
  sur le critere lui-meme : cet `ADR` cite deux chartes en **rapportant le contexte de `DEC-0056`**,
  sans rien decider a leur sujet, et la clause 2 le declarait fautif. **Un perimetre qui echoue par
  inclusion, la faute que j'ai moi-meme nommee a s22.** La clause 2 ne lit donc plus le document
  entier mais sa seule **section `## Impact`** - la liste des fichiers qu'une decision touche ou
  demande de toucher, seule marque de ce depot qui distingue statuer de mentionner.
- **Ma premiere fixture ne prouvait rien**, meme lecon que s05 pour la troisieme fois dans ce
  depot : la charte du depot fictif disait *"sans jamais la citer"* en citant, dans cette phrase
  meme, l'identifiant qu'elle etait censee omettre. La clause 2 passait au vert sur le cas qu'elle etait censee attraper. Corrige avant
  commit ; c'est la raison pour laquelle une morsure se lance et se lit, jamais ne s'affirme.

## Consequences

### Bonnes

- Le tissu de references du depot - 11 196 occurrences - est verifie a chaque `verify`, alors que
  rien ne le verifiait.
- Ecrire une decision qui redirige une charte sans le reporter dans la charte devient rouge, ce qui
  est la forme exacte du cout de s22 (un lot perdu, dix-huit sprints de derive).
- Le cout de mon propre travail baisse : un `ADR` cite par un test avant d'exister ne peut plus
  attendre le sprint suivant.

### Mauvaises

- **La clause 2 n'examine aujourd'hui que deux paires** (`DEC-0056` avec les deux chartes qu'elle
  nomme). C'est peu, et un garde-fou a petite population est un garde-fou qu'on oublie. Je l'assume
  parce que sa population croit avec l'usage qu'elle rend obligatoire, mais le fait est ecrit ici et
  non gomme.
- **La clause 1 encourage une parade sans le vouloir** : ne plus citer d'identifiant du tout est
  vert. Rien ici ne l'interdit, et je n'ai pas trouve de critere qui l'interdise sans devenir
  arbitraire.
- Le balayage lit tout le depot a chaque `verify` : 1,0 s mesure sur JDK 21.0.10.

### Ce qui me ferait changer d'avis plus tard

- Si la clause 2 restait a deux paires dans trois sprints, elle serait a supprimer plutot qu'a
  garder : une regle qui n'examine presque rien finit par etre lue comme une preuve qu'on n'a pas.
- Si les chartes se mettaient a citer massivement des decisions, la clause 1 deviendrait le
  garde-fou principal et la clause 2 une redondance.

## Alternatives considerees

1. **Detecter la contradiction entre une charte et une decision.** Ecartee : demande de juger deux
   textes en langue naturelle. Une regle qui pretendrait le faire donnerait un vert qui ne prouve
   rien - la faute que ce depot sanctionne depuis s05.
2. **Exiger que chaque charte ait ete modifiee apres toute decision qui la nomme** (fraicheur par
   horodatage Git). Ecartee : elle serait rouge pendant l'intervalle **legitime** entre une decision
   qui route une correction et la correction elle-meme - `camille-po` route explicitement, elle ne
   corrige pas les chartes. Une regle rouge sur un etat correct apprend a l'ignorer.
3. **Exiger que chaque charte cite au moins une decision.** Ecartee : satisfaite par une citation
   quelconque, donc decorative au sens strict - elle n'interdirait rien a qui voudrait mal faire.
4. **Verifier que chaque reference resout jusqu'a la section citee.** Retenue sur le fond, ecartee
   pour ce sprint : demande de decider ce qu'est une section citee dans une phrase de prose
   francaise, et le critere deviendrait un analyseur de texte. A rouvrir si le cas du 2026-08-17 se
   reproduit.

## Impact

- `bootstrap/src/test/java/com/recreo/archi/ToutesLesReferencesDeDecisionResolventTest.java` : cree.
- `bootstrap/src/test/resources/contreexemple/ToutesLesReferencesDeDecisionResolventTest/depot-fautif/` : cree.
- `squad/decisions/adr/ADR-index.md` : ligne `ADR-0066` ajoutee.
- Aucune charte d'agent modifiee : je n'ecris pas les fiches d'agent, et la correction routee par
  `DEC-0056` section 2 appartient a `sofia-sm`.
