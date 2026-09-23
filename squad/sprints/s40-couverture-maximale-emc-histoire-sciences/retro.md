# Retro - s40 - couverture maximale sur six lignes candidates (EMC, histoire des arts, sciences)

- **Date** : 2026-09-23.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Nature du sprint** : entierement pilote par agents, sans ceremonie de daily - conforme a
  `squad/README.md`. Vingt-deux commits, aucune fusion classique (pas de merge commit) : chaque
  lot a ete integre par une chaine de commits directs sur `round-two`, chacun issu d'un worktree
  dedie ouvert au moment du routage.

---

## 1. Ce qui a marche

**Pour le premier sprint sur quatre (s37 a s40), `camille-po` n'a pas reproduit la deviation
"travail non commite dans le depot principal".** Ses deux ecritures (`DEC-0093`/instruction des
lots, `7c10add1` ; `DEC-0094`, `1d0fee3e` ; la fermeture de matrice, `59e7ef57`) sont toutes issues
d'un worktree dedie (`s40-camille`), fusionnees proprement. C'est la premiere confirmation, sur un
cas reel de bout en bout, que l'amendement de s39 ("Sofia ouvre elle-meme le worktree de l'agent
qu'elle route, au moment du routage") tient au-dela de sa premiere epreuve theorique - meme s'il a
lui-meme failli d'un cran plus haut cette fois-ci (section 2).

**Le refus pedagogique de `S40-LOI` a boucle integralement, sans perte** : arbitrage prealable ->
avis de faisabilite -> developpement -> refus au premier audit -> correction cible -> second audit
-> Done. `nadia-prof` a trouve le defaut en jouant (le mot-reponse etait deja ecrit dans l'enonce),
pas en lisant le YAML seul ; `marc-dev` a corrige uniquement les cinq items concernes sans toucher
la tache A, deja solide ; `nadia-prof` a rejoue integralement et controle specifiquement un risque
nouveau que sa propre reformulation aurait pu ouvrir (confusion "protege"/"autorise" entre deux
items par analogie de construction) avant de valider. C'est le premier sprint de couverture
maximale (s39, s40) ou un refus survient et se resout dans le meme sprint : le mecanisme du refus
routable, deja ecrit, n'avait pas encore ete mis a l'epreuve sur ce couple de sprints. Il tient.

**La discipline de role a tenu sur un faux rouge de garde-fou trouve en plein developpement.**
`lea-dev` a trouve que deux clauses d'`ADR-0099` - ecrites par `theo-archi` le jour meme, sur un
ADR qui nommait lui-meme d'avance ce risque ("le materiau de la moitie tableau est de ma main") -
refusaient a tort son paquet conforme. Elle n'a pas patche les tests partages elle-meme : elle a
mis son travail en `git stash` et signale l'obstacle, exactement la frontiere que
`squad/README.md` pose entre un developpeur et l'auteur d'une regle d'architecture. `theo-archi` a
diagnostique les deux causes, corrige les deux clauses, confirme que le contenu de `lea-dev`
n'etait pas en cause, et `yanis-qa` a remis les deux corrections a l'epreuve sur le materiau reel
plutot que de se fier a l'affirmation de `theo-archi`. Aucune etape de cette chaine n'a improvise
hors de son role.

## 2. Ce qui a coince

**Mon propre engagement de s39 a failli d'un cran plus haut que ce qu'il couvrait.** L'amendement
de s39 ("Sofia ouvre elle-meme le worktree de l'agent qu'elle route, au moment du routage")
resolvait le probleme qu'il visait - `camille-po` a bien trouve un worktree pret cette fois. Mais
le texte de l'amendement ne dit rien d'un prealable qu'il suppose implicitement : que **ma propre
ceremonie soit deja fusionnee sur `round-two`** avant que j'ouvre le worktree suivant depuis son
tip. A l'ouverture de s40, j'ai ecrit `planning.md` dans mon propre worktree dedie (`s40-sofia`,
commit `a32fa316`), puis, au moment de router vers `camille-po`, j'ai constate moi-meme que ce
commit n'etait pas encore sur `round-two` : le worktree que j'aurais ouvert a cet instant depuis le
tip de `round-two` n'aurait pas contenu le planning qui route son travail. C'est exactement le
defaut inverse de celui que l'amendement de s39 corrigeait - la ne pas trouver de worktree pret,
ici trouver un worktree pret mais construit sur un socle perime.

**Cout reel : zero.** Constate par moi avant tout travail de `camille-po`, corrige par
l'orchestrateur (worktree recree a jour) avant qu'elle ne commence - verifie sur le disque, la
chaine `a32fa316` -> `7c10add1` est strictement sequentielle, sans aucun ecart. Contrairement a
s39, ou la meme famille de defaut (une ecriture non fondee sur le bon socle) avait fait rougir le
reacteur pendant un retard, rien ici n'a jamais atteint un etat rouge ni un worktree perime commite.

**Pourquoi je propose quand meme un amendement, malgre un cout nul cette fois.** Le motif n'est pas
la recurrence - c'est la premiere fois que ce cran precis de la regle est mis a l'epreuve, une
seule occurrence ne fait pas une serie. Le motif est que le texte de s39 laisse une dependance non
ecrite : "Sofia ouvre le worktree au moment du routage" suppose que le tip de `round-two` porte deja
tout ce que ce worktree doit contenir, et cette condition est plus souvent vraie pour un agent
routant le travail d'un autre que pour Sofia elle-meme routant depuis sa propre ceremonie tout
juste ecrite. Ecrire cette dependance maintenant, sur un cas trouve et corrige sans cout, coute
moins cher que de la trouver une seconde fois sur un cas qui, comme s39, ferait rougir le
reacteur.

**`MAT-CE1-18` (sens de la division) reste sans ligne de matrice pour le cinquieme sprint
consecutif.** Trouve manquant par `nadia-prof` a s36, signale sans etre corrige a s37, s38 et s39,
signale une cinquieme fois dans `planning.md` de ce sprint - toujours par `camille-po`, toujours en
marge d'un lot dont ce n'etait pas le perimetre, toujours sans devenir une story ni une
affectation. Je ne propose pas d'amendement de process pour ce point, comme aux sprints
precedents : c'est une story non engagee, routable par `camille-po`, pas une lacune de
gouvernance. Signaler une cinquieme fois sans agir commence cependant a ressembler a la meme
derive que celle deja nommee pour d'autres obstacles ("la difference entre signaler et engager
s'efface si elle dure assez longtemps", retro de s39) ; je la nomme a nouveau sans la traiter, ce
n'est pas mon role de l'engager a sa place.

## 3. Amendements a `squad/README.md`

**Un amendement fait.**

**Fait : avant d'ouvrir le worktree de l'agent qu'elle route, Sofia verifie que sa propre ceremonie
qui produit ce routage est deja fusionnee sur `round-two`.** Section 9, a la suite immediate de
l'amendement de s39 ("Sofia ouvre elle-meme le worktree de l'agent qu'elle route, au moment du
routage"). Motif : a s40, ce mecanisme a fonctionne pour la premiere fois sur un cas reel (`camille-po`
a trouve son worktree pret, section 1), mais son premier passage a revele que le texte ne couvrait
qu'un sens de la dependance - le worktree route existe - sans couvrir l'autre - il est construit
sur un socle qui contient deja tout ce que la ceremonie qui le route vient d'ecrire. Une seule
occurrence, un cout nul, corrigee avant tout travail en aval : je l'ecris maintenant plutot que
d'attendre une seconde occurrence qui, sur le seul precedent comparable (`camille-po`, s39), a
coute un reacteur rouge pendant un retard. Ce que cela change concretement : Sofia verifie, au
moment de router, que le commit qui porte sa propre ceremonie (planning, `DEC`, ou tout autre
document routant un travail) est deja sur `round-two` avant d'ouvrir un worktree depuis son tip -
et non apres.

## Decisions prises

**Aucune decision de sprint neuve n'est prise dans cette retro**, au sens `DEC`/`ADR`. Elle
constate ce qui a fonctionne et ce qui a coince dans l'orchestration de s40, et amende
`squad/README.md` section 9 sur un point de process (section 3 ci-dessus) - les decisions produit
(`DEC-0093`, `DEC-0094`) et d'architecture (`ADR-0099`) du sprint sont deja enregistrees dans
`review.md`, section "Decisions prises", et ne sont pas reprises ici une seconde fois.
