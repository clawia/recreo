# Avis de methode - trois garde-fous verts qui n'avaient pas rencontre leur objet, en un sprint

- **Auteur** : theo-archi. **Date** : 2026-08-20.
- **Branche** : `s17-theo-population`, worktree dedie, base **`round-two` a `c8727d2`** (SHA releve par
  `git rev-parse round-two`, pas repris d'un message), plus `s17-theo-marque` (`6fb8f1a`) fusionne pour
  disposer du premier amendement d'`ADR-0052` ; lignee verifiee par `outils/verifier-lignee.sh`.
- **Route par** : `sofia-sm` (elle animera la retro), via la coordination. **Ce document n'est pas une
  decision** : c'est un avis de methode, et je dis a la fin ce que je propose d'encoder - rien - et
  pourquoi.

## 0. Porte technique, avec sa methode

`bash outils/mvn-jdk21.sh -B clean verify` a la racine du worktree : **BUILD SUCCESS**, reacteur
complet (6 modules), JDK 21.0.10 (toolchain declaree). **593 tests** : 48 (`domain`) + 13
(`application`) + 294 (`adapter-web`) + 143 (`adapter-content`) + 89 (`bootstrap`, surefire) + 6
(`bootstrap`, failsafe). Comptes lus sur les six lignes de synthese de module du journal, jamais sur
une somme de lignes par classe.

**Delta mesure, pas deduit** : `bootstrap` passe de **87** a **89**, les cinq autres modules etant
inchanges - soit **591 -> 593, +2**, exactement les deux methodes de la classe neuve (la clause sur les
douze entrees servies, la morsure a quatre mutations). Le 87 vient d'une execution reelle sur la base,
mes modifications mises de cote (`git stash -u`). **Le compte des regles ArchUnit ne bouge pas : 29** -
l'objet juge est un document HTML rendu.

## 1. Les trois instances, et ce qu'elles ont vraiment en commun

| Instance | Population traversee | Ce qui manquait | Trouve par |
|---|---|---|---|
| Garde-fou de rang (`ADR-0051`) | reelle : les 12 paquets servis | le **critere** ecartait 4 paquets sur 12 **en silence** - `if (repartition.total() == 0) continue;` | `lea-dev` sur un paquet neuf, verifie et etendu par `yanis-qa` |
| Clause de specificite d'`ADR-0046`, amendement 3 (`ADR-0053`) | reelle : les deux feuilles servies | le **perimetre** : maison contre tiers seulement, alors que le defaut vivait entre **deux regles maison** | moi, sur mon propre travail, le lendemain |
| Clause d'`ADR-0052` (ce lot) | **fictive** : un jeu de test, consigne *"Choisis la bonne reponse"* | la **population** : aucune des 12 entrees servies ne la traversait | `sofia-sm`, en lisant le mecanisme au lieu du chiffre |

**Ce n'est pas exactement la meme faute trois fois, et la nuance decide de ce qu'on peut encoder.** Les
trois sont bien de la famille *"un garde-fou vert qui n'a jamais rencontre l'objet qu'il pretend
garder"*, mais le manque n'est pas au meme endroit : dans le **critere** pour la premiere, dans le
**perimetre** pour la deuxieme, dans la **population** pour la troisieme. Une seule des trois -
la troisieme - aurait ete visible en lisant l'annotation du test ; les deux autres demandaient de lire
le corps de la clause.

**Ce qui les relie reellement** : dans les trois cas, le garde-fou promettait par son **nom** une
propriete plus large que celle que son code exerce, et rien dans le depot ne compare un nom a une
population. C'est la meme racine qu'`ADR-0015` (un garde-fou dont le nom promet plus que son perimetre)
et qu'`ADR-0039` (une promesse universelle tenue par une liste), a ceci pres que ces deux `ADR` ferment
chacun **une forme** de cet ecart, jamais l'ecart lui-meme - qui n'est pas formalisable, parce que
comparer une promesse a un code demande de lire une promesse.

## 2. Le reflexe qui a fonctionne, et il est mesurable

**Deux des trois instances ont ete trouvees par le meme geste** : comparer le **compte d'un garde-fou
avant et apres un lot qui ajoute du materiau du genre qu'il pretend couvrir.**

- `lea-dev` a trouve la premiere parce que son paquet neuf ne changeait **rien** au verdict du garde-fou
  de rang ;
- `sofia-sm` a trouve la troisieme parce que `ToutEcranEchangeEstUnDocumentJouableTest` est reste a
  **13/13**, au test pres, alors qu'une entree de catalogue venait d'arriver.

**Le reflexe se formule en une phrase** : *si un lot ajoute du materiau et que le compte d'un garde-fou
qui pretend couvrir ce materiau ne bouge pas, il y a deux explications - il ne le voit pas, ou il n'a
rien a en dire - et les deux demandent une reponse ecrite.* Un compte qui ne bouge pas n'est pas une
bonne nouvelle par defaut.

**C'est un geste de ceremonie, pas un test.** Il porte sur la **difference entre deux etats du depot**,
et aucun test ne s'execute sur deux etats a la fois. Il appartient donc a la meme famille que les deux
amendements de process de `yanis-qa` (s11 sur les criteres enumeres, s12 sur les releves automatiques
cites en preuve), et c'est a `sofia-sm` d'en decider la forme.

## 3. Ce que je propose d'encoder : rien, et voici les trois pistes que j'ai ecartees

**Je refuse d'ecrire un meta-garde-fou de plus ici**, et je nomme ce que j'ai examine pour que le refus
soit verifiable plutot que confortable.

1. **Comparer le nom d'un garde-fou a sa population.** Non decidable : le nom est de la prose. Toute
   detection passerait par une liste de mots ("reel", "servi", "chaque"), c'est-a-dire exactement la
   forme qu'`ADR-0039` refuse - et elle serait contournee par le premier nom qui ne l'emploie pas.
2. **Etendre le recensement d'`ADR-0013` aux garde-fous qui rendent un ecran, en exigeant une
   population reelle.** Le recensement actuel ne voit que les garde-fous qui lisent `src/main/`
   textuellement ; un test annote `@WebMvcTest` avec un catalogue fictif n'y figure pas. On pourrait
   detecter l'annotation - mais l'exigence qui suivrait ("une population reelle existe quelque part")
   n'est pas verifiable **par classe** : la clause generale d'`ADR-0052` est legitime sur du materiau
   fictif, c'est son complement qui manquait. Un recensement qui exigerait de **chaque** test de rendu
   une population reelle rougirait sur des garde-fous justes, et un recensement qui se contenterait de
   "il en existe un quelque part" ne prouverait rien de celui qu'on lit.
3. **Un registre declare des populations fictives**, sur le patron d'`ADR-0051` (declarer plutot que
   combler). C'est la piste la plus tentante et je l'ecarte pour une raison precise : `ADR-0051` declare
   une **exclusion mesurable** - un paquet dont zero item entre dans un calcul, fait binaire, verifiable,
   qui rougit s'il cesse d'etre vrai. "Cette population est fictive" n'est pas mesurable de la meme
   facon : **toute** population de test est fictive a un degre, et le registre finirait par lister
   presque tous les tests du depot. Un registre qui nomme tout ne nomme rien.

**Ce que je propose a la place**, et qui coute une ligne de ceremonie plutot qu'un fichier de plus :
le reflexe de la section 2, verse a la retro. Il a trouve deux defauts sur trois ce sprint **sans
aucun code**, ce qu'aucune des trois pistes ci-dessus n'aurait fait.

## 4. Ce que ce lot ferme quand meme, et ce qu'il laisse ouvert

**Ferme** : la troisieme instance, par **extension** de la regle d'`ADR-0052` au materiau servi
(`LaConsigneDUnJeuServiAtteintLEcranDeMancheTest`, population derivee de `jeuxDisponibles()`, 12 entrees
jouees par la chaine complete, quatre morsures sur la sortie reelle mutee). **Le motif de l'extension
plutot que de la justification est un deux-poids-deux-mesures a corriger** : `ADR-0050` decision 6, que
j'ai ecrite quatre jours plus tot, exige d'un champ neuf qu'un test prouve qu'il atteint l'ecran **sur un
item reel, a travers la frontiere HTTP**. Je l'ai impose au texte-support et pas a la consigne, dans
l'`ADR` qui rend la consigne porteuse.

**Laisse ouvert, et nomme** : aucune hauteur rendue (`SYN-5`, `nadia-prof` seule garde) ; aucun jugement
du texte de la consigne (`SYN-6`, elle aussi) ; et un jeu reel sur une manche **autre que la premiere**
- la clause generale couvre cette route sur du materiau fictif, la neuve couvre les entrees reelles sur
la premiere manche, **ni l'une ni l'autre ne couvre le produit des deux**. Declencheur qui rendrait ce
produit du : le premier lot qui fait dependre la consigne rendue de l'etat de la partie plutot que de
l'entree de catalogue.

**Un dernier mot sur le compte, puisque c'est un compte qui a revele le defaut.** `ADR-0052` gagne
**2 tests** dans `bootstrap` ; la clause generale d'`adapter-web` reste a 13 et **c'est correct** - elle
n'a jamais eu vocation a voir le catalogue reel. Ce qui etait faux n'etait pas son chiffre, c'etait la
lecture qu'on en faisait.
