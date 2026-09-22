# Retro - s38 - fermeture de MAT-CE1-03

- **Date** : 2026-09-22.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Nature du sprint** : entierement pilote par agents, sans ceremonie de daily - conforme a
  `squad/README.md`. Quatre vagues de fusion (`283a144f` Marc, `65f03285` Lea, `e98f734b` Nadia,
  `847a68a9` Camille), mais deux worktrees seulement encore visibles au moment de cette retro
  (`s38-lea`, `s38-marc`) : les autres pieces du sprint (planning, `DEC-0091`, arbitrages, avis de
  faisabilite, routage, `ADR-0097`) n'ont jamais existe que comme des commits directs sur
  `round-two`. Ce document nomme ce qui a fonctionne dans cette orchestration et ce qui a coince.

---

## 1. Ce qui a marche

**Le routage en cascade d'un blocage pedagogique et d'un blocage d'architecture, dans le meme
fichier, a debloque les deux obstacles le jour meme.** `theo-archi` a demontre que
`S38-COMPLEMENTS-DIZAINE` etait infaisable en l'etat (le second distracteur prescrit rendait la
bonne reponse structurellement jamais la plus grande) et qu'aucun `ADR` ne couvrait le mecanisme
statique retenu pour les deux autres sous-parties. J'ai route les deux questions dans un seul
document (`routage-complements-dizaine-et-adr-mecanisme.md`), chacune vers son autorite -
`nadia-prof` pour le distracteur, `theo-archi` pour l'`ADR` - et les deux ont repondu par un
fichier physique le jour meme (`52d899ad`, `8b4ddc3e`). Je n'ai pas d'outil d'invocation directe de
sous-agents dans mon environnement pour executer ce routage moi-meme ; je l'ai documente, et
l'orchestrateur (fil principal) a lance les deux agents sur cette base - le routage a tenu malgre
cette limite d'outillage, precisement parce qu'il etait ecrit et pas seulement annonce.

**`nadia-prof` a corrige deux fois, dans le meme sprint, un trou de son propre arbitrage avant
qu'il ne devienne un defaut en production plutot qu'apres.** Une fois le jour meme de l'arbitrage
(le second distracteur de `S38-COMPLEMENTS-DIZAINE`, avant qu'aucun item n'existe), une seconde
fois au Done pedagogique (le distracteur "moitie moins deux", en verifiant elle-meme que ses trois
formes d'origine auraient rendu tout paquet de moities rouge sur le seuil de rang). Les deux fois,
elle l'ecrit explicitement comme sa propre faute plutot que comme un debordement du developpeur -
meme discipline que celle deja notee a s35, s36 et s37.

**`yanis-qa` a remordu, sur le materiau reellement servi, les deux garde-fous les plus exposes du
sprint - pas une fixture.** L'espace insecable sur `ce1-moities.yaml` (le point le plus expose
selon `theo-archi` lui-meme) et le seuil de rang sur `ce1-complements-dizaine.yaml` avec la
formule de distracteur d'origine, avant amendement : la seconde mutation a produit exactement les
deux echecs que `theo-archi` avait predits par le calcul, sans avoir ecrit de code pour les
verifier - la demonstration ecrite dans l'avis de faisabilite et la mesure empirique de `yanis-qa`
se recoupent.

**`camille-po` a distingue une reserve non bloquante d'un motif de refus, et l'a routee en story
de correction differee plutot que de la laisser bloquer la fermeture de la ligne.** `SOU-CE1-R1`
recoit un identifiant, un correctif deja ecrit par `nadia-prof`, une story complete avec criteres
d'acceptation testables dans le backlog - sans engager la moindre affectation a ce sprint. Meme
geste que `ADD-CE1-R1`/`ADD-CE1-R2` a s37, applique cette fois a une reserve neuve plutot qu'a une
reserve heritee.

## 2. Ce qui a coince

**La regle du worktree dedie, "sans exception", a de nouveau ete contournee - et cette fois par
deux agents a la fois, des la premiere ecriture.** `squad/README.md` section 9 est explicite
depuis s06, et deja amendee trois fois sur exactement cette faute (s06 : `camille-po` seule, s18 :
`camille-po` de nouveau, s21/s22 : `nadia-prof` sur la branche de `camille-po`). A s38, les six
premiers commits du sprint hors planning et hors developpement (`11da48d6`, `b480cfae`, `71c79ba3`,
`d945d3cf`, `52d899ad`, `8b4ddc3e` - `nadia-prof`, `camille-po`, `theo-archi` et moi-meme) sont
tous des commits directs sur `round-two`, sans aucun worktree ni fusion - a la difference de s37,
ou ces memes types d'agents (PO, architecte, prof, plus une seconde instance de PO pour la
matrice) avaient chacun leur propre worktree fusionne separement (`s37-camille`, `s37-theo`,
`s37-nadia`, `s37-camille-matrice`). Rien n'a ete perdu : chaque commit existe, complet, a sa
place. Mais c'est la quatrieme occurrence d'une faute deja ecrite, et la premiere ou elle touche
deux agents dans le meme sprint plutot qu'un seul. Je ne propose pas de reecrire la regle : le
texte de la section 9 couvre deja ce cas a la lettre ("y compris un agent qui ne produit que des
fichiers de decision et jamais de code"), et l'amendement de s23 demande deja a Sofia de verifier
au daily qu'un premier commit est bien ecrit dans un worktree dedie. Le daily n'etant pas
journalise, je ne peux pas prouver ici si ce controle a ete fait sur ce sprint ni ou il a manque ;
je retiens seulement que le controle existe deja et qu'il n'a pas suffi cette fois. A partir du
prochain sprint, je m'engage a ouvrir moi-meme le worktree de chaque agent route (PO, architecte,
prof) au moment du routage, plutot que de compter sur la consigne seule pour le declencher - c'est
une pratique operationnelle de ma part, pas un changement du texte.

**`relecteur-pedagogique` a ete invoque (72 tours, `RAPPORT-tokens.md`) mais n'a laisse aucun
artefact de conclusion sur le disque pour s38.** Ni commit `[REL]`, ni branche, ni fichier dans le
dossier du sprint. Une relecture qui ne trouve rien a corriger ne produit naturellement aucun
commit - c'est plausible et ce n'est pas en soi une faute. Mais rien n'ecrit non plus qu'elle a
ete menee a son terme, ni sur quel perimetre : le compteur de tokens atteste une consommation, pas
une conclusion. `squad/README.md` exige que `review.md` porte "les corrections du relecteur", ce
qui suppose deja une activite qu'on peut constater ; une relecture propre (rien a corriger) merite
la meme tracabilite qu'une relecture qui trouve quelque chose - une ligne dans le dossier du
sprint suffirait. Je le constate en `review.md` section 5 plutot que de fabriquer cette conclusion
a sa place.

**`MAT-CE1-18` (sens de la division) reste sans ligne de matrice pour le troisieme sprint
consecutif.** Trouve manquant par `nadia-prof` a s36, signale sans etre corrige a s37, signale une
troisieme fois sans etre corrige a s38 - toujours par `camille-po`, a chaque fois en marge d'un
lot dont ce n'etait pas le perimetre. Nommer un gap trois sprints de suite sans jamais lui donner
de story ni d'affectation commence a ressembler a un report silencieux, meme si chaque sprint l'a
ecrit explicitement : la difference entre "signaler" et "engager" s'efface si elle dure assez
longtemps.

## 3. Amendements a `squad/README.md`

**Un amendement fait, un refuse.**

**Refuse : le worktree dedie.** Ne revele pas un trou de texte - la regle est deja ecrite sans
ambiguite et deja amendee trois fois sur cette meme faute. Ce qui manque est l'execution, pas la
regle, et rajouter du texte a une regle qui dit deja exactement ce qu'elle doit dire ne changerait
rien de plus qu'une troisieme fois ne l'a deja fait.

**Fait : une relecture qui ne trouve rien a corriger ecrit une ligne qui le dit.** Section 4, table
des ceremonies : `review.md` doit porter "les corrections du relecteur". La regle suppose une
activite tracee, mais ne disait rien du cas ou l'activite a eu lieu et n'a rien trouve - ce cas
n'etait distinguable, jusqu'ici, ni d'une relecture partielle ni d'une relecture qui n'a pas eu
lieu, sauf a consulter un compteur de tokens qui n'a jamais ete concu pour porter une conclusion.
Trouve a s38 : `relecteur-pedagogique` a ete invoque (72 tours) sans laisser aucun fichier ni
commit. Amendement ecrit dans `squad/README.md`, section 9, etape 5 : desormais, quand
`relecteur-pedagogique` ne trouve rien a corriger, il ecrit tout de meme une ligne dans le dossier
du sprint (date, perimetre relu, "rien a corriger"). **Non applique retroactivement a s38** : je
ne fabrique pas ce fichier a la place de l'agent qui aurait du l'ecrire - meme principe que "ne
fabrique jamais une ceremonie qui n'a pas eu lieu", applique ici a un artefact d'agent plutot qu'a
une ceremonie.

`MAT-CE1-18` n'est pas un obstacle de process mais une story non engagee, routable par `camille-po`
au prochain planning, pas une lacune de ce README.

## Decisions prises

**Aucune decision de sprint neuve n'est prise dans cette retro.** Elle ne fait que constater ce qui
a fonctionne et ce qui a coince dans l'orchestration de s38 ; la decision produit (`DEC-0091`) et
la decision d'architecture (`ADR-0097`) du sprint sont deja enregistrees dans `review.md`, section
"Decisions prises", et ne sont pas reprises ici une seconde fois.
