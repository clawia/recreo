# Relecture pedagogique et QA de contenu - Sprint 7

- **Auteure** : relecteur-pedagogique
- **Date** : 2026-08-10
- **Provisionnement** : worktree dedie `s07-relecteur-pedagogique`, provisionne hors de l'arbre du
  depot depuis `round-two` a `eff5767`. Lignee verifiee : `outils/verifier-lignee.sh` rend
  "le worktree sur la branche 's07-relecteur-pedagogique' descend de la lignee de round-two
  (ancetre commun trouve dans son historique)." Rien a signaler sur le comportement du script.
- **Perimetre lu** : `DEC-0015` (quatre amendements) et `DEC-0016`, `DEC-index.md`, `ADR-0026` et
  `ADR-0027`, `ADR-index.md`, `docs/backlog/backlog.md` section "Sprint 7" (S7-1 a S7-4) et la note
  de la story des cinq indices fautifs, `squad/sprints/s07-machine-a-sous/planning.md` et
  `proposition-ines-machine-a-sous.md`, les quatre fiches de `squad/conformite/` (blocs neufs
  dates du 2026-08-10 : arbitrages prealables, arbitrage de forme, extinction de decision), les
  messages des quinze commits du sprint (`f29281a^..eff5767`, lots et fusions compris).
- **Non touche** : `confirmation-archunit.md` (`theo-archi` y travaille en parallele, hors de mon
  perimetre).

## Corrections appliquees (7)

1. `squad/decisions/produit/DEC-0015-sprint-7-la-vraie-machine-a-sous-ferme-la-dette-1.md`, titre et
   deux occurrences dans le corps (lignes 1, 24, 58) : "dette n. 1" corrige en "dette n°1", pour
   rejoindre la convention "n°X" utilisee sans exception dans plus de vingt `ADR` et `DEC` du depot
   depuis s00 (`n°1` a `n°7` de `main`) pour designer les memes dettes. Coherence terminologique :
   meme concept, meme nom.
2. `squad/decisions/produit/DEC-index.md`, ligne 21 : meme correction "n. 1" -> "n°1" dans le titre
   de `DEC-0015` recopie en table.
3. `squad/sprints/s07-machine-a-sous/planning.md`, ligne 84 : meme correction "n. 1" -> "n°1".
4. `docs/backlog/backlog.md`, ligne 1830 (section "Sprint 7") : meme correction "n. 1" -> "n°1".
5. `squad/sprints/s07-machine-a-sous/proposition-ines-machine-a-sous.md`, ligne 42 : coquille "de la
   geser au cas par cas" corrigee en "de la gerer au cas par cas" (le mot "geser" n'existe pas en
   francais ; le sens - gerer un probleme au cas par cas plutot que le fermer a la racine - est
   certain).
6. Meme fichier, ligne 43 : "Nadia-prof formule elle-meme sa preference" corrige en "`nadia-prof`
   formule elle-meme sa preference" - l'identifiant d'agent est ecrit en minuscules et entre
   guillemets simples inverses partout ailleurs dans ce document (plus de vingt occurrences) et dans
   tout le depot ; la majuscule et l'absence de balisage venaient d'une phrase commencant par ce mot.
7. Meme fichier, ligne 139 : meme correction, seconde occurrence isolee ("Nadia-prof interdit
   nommement" -> "`nadia-prof` interdit nommement").

Les sept corrections sont des coquilles ou des ecarts de coherence terminologique sans ambiguite :
aucune ne change le sens d'une phrase, aucune ne touche a une decision, un chiffre ou un critere
d'acceptation.

## Signale sans corriger

Rien. Aucun defaut ambigu, aucun chemin de poste de travail, aucun point de terminologie ou de
clarte necessitant l'arbitrage d'un autre agent n'a ete trouve ce sprint.

## Verifie sans rien trouver

- **Chemins de poste de travail et noms de compte**, le point d'exposition nomme pour ce sprint
  (huit worktrees, invocations de navigateur a repetition dans les rapports de mesure). Recherche
  manuelle des formes `C:\...`, `C:/...`, `/c/...`, `/home/...`, `/Users/...`, du nom du worktree
  (`recreo-wt-...`) et du compte de l'utilisatrice dans les treize fichiers du perimetre et dans les
  quinze messages de commit du sprint : aucune occurrence reelle, seulement des faux positifs
  ("declaration" contient la sous-chaine "clara"). `git ls-files -- . ':!.github/scripts/verifier-
  chemins-test.sh' | bash .github/scripts/verifier-chemins.sh` rend vert sur l'arbre apres mes
  corrections, comme avant.
- **Tirets cadratins et guillemets typographiques**, sur les treize fichiers du perimetre : aucune
  occurrence (`[\x{2014}\x{2013}\x{201C}\x{201D}\x{2018}\x{2019}]`). Les six occurrences trouvees par
  la meme recherche dans `squad/` entier sont toutes hors perimetre (programme, s01, s03).
- **Emoji et symboles Unicode decoratifs** : aucune occurrence dans `squad/` entier.
- **ASCII des messages de commit** : les quinze sujets et corps de commit du sprint (`f29281a^..
  eff5767`) sont entierement ASCII. Un ecart mineur et non corrigeable existe dans le corps du
  commit `4fac2d9` ("Dette n1 de CLAUDE.md", sans le point ni le "n°" des autres occurrences) : un
  message de commit deja fusionne sur `round-two` n'est pas un texte que je peux corriger sans
  reecrire l'historique, hors de mon mandat. Il reste conforme a la regle qui compte (ASCII).
- **Lisibilite pour un developpeur qui arrive apres coup sur `DEC-0015` (quatre amendements) et
  `ADR-0026` (plusieurs corrections integrees)** : chaque amendement de `DEC-0015` se numerote et se
  date, le quatrieme et dernier enonce explicitement "ma condition, dans sa forme desormais
  definitive" en deux points verifiables - un lecteur peut s'arreter au dernier amendement sans
  reconstituer les trois precedents. `ADR-0026` integre ses deux chiffres corriges directement dans
  le tableau de mesure ("le tableau ci-dessous est corrige") plutot que de laisser une correction en
  append-only en contradiction avec le corps du texte.
- **Le cas des douze triplets eteints** de `FICHE-bandit-manchot-conjugaison-present-cm1.md` (la
  reduction de "Le bon verbe" a trois propositions, acceptee puis eteinte par `nadia-prof` le meme
  jour) : le bloc "Second arbitrage" nomme le verdict "ETEINTE" en toutes lettres, precise
  explicitement "Ils ne sont plus applicables. Ils restent ecrits" et "Aucun developpeur ne les
  applique", et le paquet YAML garde ses quatre propositions. Aucun lecteur ne peut prendre ce
  tableau pour une consigne active.
- **Coherence des identifiants d'agent** (`nadia-prof`, `camille-po`, `theo-archi`, `ines-design`,
  `lea-dev`, `marc-dev`, `sofia-sm`, `yanis-qa`) sur le reste du perimetre, apres correction des deux
  occurrences signalees ci-dessus : aucune autre variante de casse.
- **Paraphrase de code dans les commentaires** : aucun commentaire de code n'entre dans mon
  perimetre ce sprint (aucun fichier `.java`, `.css` ni `.html` n'y figure).
