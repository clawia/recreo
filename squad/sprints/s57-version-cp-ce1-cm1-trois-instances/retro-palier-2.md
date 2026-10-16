# [Sprint s57] Retro du palier 2 : lecons du palier

- **Ce que ce fichier est.** Les lecons du palier 2, ecrites par `sofia-sm` a la cloture du palier (2026-10-16). **Ce n'est pas la `retro.md` du sprint** : `squad/README.md` (section 4) veut une seule `retro.md` par sprint, ecrite a la cloture du sprint, et le sprint s57 n'est pas clos. Ce fichier en est une source, nommee a part pour qu'aucune ceremonie ne soit ecrite avant d'avoir eu lieu. Sort de ces lecons dans la retro du sprint : question (d) de `cloture-palier-2.md`, section 13, a l'utilisatrice.
- **Forme.** Chaque lecon est un geste : ce qu'on fait maintenant. Le fait qui la motive est dans le depot, avec son fichier.
- **Amendements a `squad/README.md`** : aucun n'est applique ici. Les lecons 2, 3 et 4 en appelleraient un ; il s'ecrit a la retro du sprint, pas avant.

## Ce qui a marche

- **Un amendement avant un worktree, tenu.** L'amendement `PN8` de `camille-po` (`341129f8`, 16:41) etait fusionne (`5a6e94b5`, 16:54) quand le worktree de correction de `-05` s'est ouvert ; la clause *"avant l'ouverture du worktree"* a ete verifiee par l'horodatage (`planning.md`, amendement de 16:59, section 3).
- **39 fusions, un conflit.** Lignee jugee avant chacune, reacteur complet a chaque fusion de code, le seul conflit (`2764310d`, double ajout en fin de backlog) resolu en gardant les deux cotes.
- **Un agent qui verifie l'annonce qu'on lui fait.** `nadia-prof` a refuse de relayer *"code identique a `7c9ac220`"* et a constate que c'etait inexact a la lettre ; ses verdicts portent sur le jar qu'elle a joue.
- **Le controle croise.** `yanis-qa` a trouve 7 mutations vertes sur 14 lancements (proprietes que personne ne garde) et `theo-archi` a trouve que la decision 1 d'`ADR-0031` ne mordait plus sur son exemple depuis s10. Aucun des deux n'etait vu par le `build`.
- **Un releve de cloture qui lit les renvois.** Le `relecteur-pedagogique` a trouve 19 ecarts, dont neuf dans mes textes, en resolvant les SHA, les sections et les citations.

## Ce qui a coince

- **Mon sommet de rejeu a ete faux pres d'une heure** (de la fusion de code de 19:30 a la correction de 20:15) : phrase non relue apres cette fusion.
- **Les mots de `S2` ont ete declares absents alors qu'ils etaient au depot**, par `cloture-palier-1.md` et par l'arbitrage du palier 2 (`cloture-palier-2.md`, section 11).
- **Un commit engage n'a pas ete livre** : `RL5` a `RL7`, engage a 19:40, absent de l'amendement de 20:15, report ecrit seulement a la cloture.
- **Des chiffres sans perimetre** : `501 tests` et `496 tests` etaient des comptes d'`adapter-web`, ecrits comme des totaux.
- **Trois recomptes `awk` sur un registre non commite** : la commande ne se rejouait pas.
- **Le refus de `-05` a rouvert le calcul de prix** : le rejeu (0,5) n'avait pas de ligne dans la composition, deux lectures du financement ont coexiste de 16:59 a 18:14 (A' a 2,75 ou a 3,00), et la reponse de `nadia-prof` sur l'attribution du quart hors instance n'est pas au depot (`git grep -l "attribution du quart"` ne rend que `DEC-0120` et `planning.md`).
- **Des declarations d'isolation renvoyees a un message qui n'est pas dans le depot** (`rapport-lea-dev-palier-2.md` ligne 70, `cote-de-reference-forme-commune-2026-10-16.md` ligne 209).

## Lecons, ecrites comme des gestes

1. **L'amendement d'une story s'ecrit et se fusionne avant l'ouverture du worktree qui l'implemente.** Avant d'ouvrir un worktree, je lis le dernier amendement de la story dans `docs/backlog/backlog.md`, je verifie qu'il est sur `round-two` (`git merge-base --is-ancestor <commit de l'amendement> round-two`), et j'inscris les deux heures dans l'amendement de `planning.md` : celle de l'amendement, puis celle du worktree. Un amendement qui arrive apres l'ouverture ne se rattrape pas par une reecriture du critere.
2. **Une affirmation de code identique entre deux SHA se verifie par `git diff` avant d'etre ecrite.** J'ecris la commande et son code de sortie a cote des deux SHA : `git diff --quiet <A> <B> -- adapters domain application bootstrap pom.xml outils .github`, sortie 0 ou 1. Je ne le deduis jamais d'un autre SHA ni d'un message de commit. **A chaque fusion de code, je relis toute phrase de `planning.md` qui nomme un sommet de rejeu**, qu'elle me semble concernee ou non, et je l'annote le jour meme.
3. **"Mots absents du depot" se verifie par `git grep` avant d'etre ecrit.** Je cherche une chaine prise a la source de la demande (`git grep -n "<quelques mots de la demande>"` sur `HEAD`, `squad` et `docs`), et j'ecris la commande avec sa sortie. Si elle rend une ligne, les mots existent ; si elle ne rend rien, je l'ecris comme tel, avec la chaine cherchee. Je ne demande a l'agent lanceur des mots qu'apres cette commande.
4. **Un total de tests est cite avec son perimetre.** *"N tests, reacteur complet, racine, sans `-pl`"* ou *"N tests d'`adapter-web` (`-pl adapters/adapter-web -am`)"* : jamais un nombre nu. Le total d'un arbre fusionne est celui que je mesure moi-meme ; le chiffre d'un agent est sa base plus son lot, et je dis d'ou vient l'ecart.
5. **Le rejeu d'un refus rouvre le calcul de prix, le jour du refus.** Quand `nadia-prof` refuse un lot, j'ecris le registre complet avec le rejeu comme ligne neuve, je le commite, et je demande a `camille-po` le financement avant que le worktree de correction ne s'ouvre. Le registre se commite a chaque recompte : un `awk` sur un fichier qui n'est pas dans le depot ne se rejoue pas (`registre-prix-palier-2.txt`).
6. **Un engagement hors tableau entre dans le tableau d'etat des postes, avec son titulaire.** `RL5` a `RL7` n'avait pas de ligne dans le tableau de 20:15 et a disparu de l'amendement. A chaque amendement, je relis les engagements du precedent, un par un, et j'ecris pour chacun : livre, ou non livre avec la condition qui bloque.
7. **Une declaration d'isolation est dans le rapport, pas dans un message de rendu.** Je refuse un rapport qui renvoie au message de rendu pour l'etat de la racine : la ligne *"racine : propre"* ou la liste des noms est ecrite dans le fichier commite, sinon elle n'existe pas pour le depot.
