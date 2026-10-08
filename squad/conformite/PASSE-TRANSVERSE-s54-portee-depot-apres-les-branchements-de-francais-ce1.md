# Passe transverse de s54 (`S53-PASSE-TRANSVERSE`, `PAR-4`) - les proprietes de portee depot

**Auteur** : `nadia-prof`, instance A (celle qui porte l'accumulation, `PAR-7`).
**Date** : 2026-10-08.
**Arbre** : branche `s54-nadia-a`, issue de `round-two` a `5cad6650`, qui contient les fusions des branchements
de `marc-dev` (`f6bcb4e3`) et de `lea-dev` (`b91532aa`). C'est le depot que l'enfant aura apres ces deux fusions.
**Prix contre ma borne** : **0,5**, le poste nomme d'avance (`ARBITRAGE-PREALABLE-s54-instance-a-francais-ce1.md`,
section 8).
**Statut** : ce document ne prononce aucun Done pedagogique. Il mesure les proprietes de portee **D**
(`ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md`, section 13.2,
completee par `PASSE-TRANSVERSE-s30-les-trois-familles-de-portee-depot.md`) et route ce qu'il trouve. Passes
precedentes : s29, s30, s35 ; aucune entre s35 et celle-ci.

## 0. Conditions

Jar construit depuis mon worktree a `5cad6650` (`-DskipTests package`), copie hors du worktree, lance sur le JDK
21.0.10, port 18854. Parties jouees par requetes HTTP reelles : **HTML servi lu, rendu non vu au navigateur**.
Rien de ce document ne repose sur la couleur, la taille ou la position. Porte technique non mesuree par moi.

## 1. Une ligne par propriete

| Repere | Propriete (portee **D**) | Methode | Resultat |
|---|---|---|---|
| `D1` | la dette des paquets a voix | `git log --since=2026-09-11` sur chaque paquet qui porte un champ `texteAVoixHaute:` ; `git blame` des `consigneAdulte` du catalogue | **population vide.** Six paquets portent une voix (`cp-digraphe-du-son`, `cp-lettre-du-son`, `cp-mot-dicte`, `cp-position-syllabe`, `cp-son-dans-mot`, `cp-voyelle-du-son`) : aucun modifie depuis la passe de s35. Les six `consigneAdulte` datent des 2026-09-01 et 2026-09-02. Les deux paquets de musique ajoutes depuis (`cp-musique-vocabulaire`, `cp-musique-instruments`) ne nomment `texteAVoixHaute` qu'en commentaire, pour dire qu'ils n'en portent pas. Rien a relire |
| `D2` | ce qu'un indice ou une consigne demande a l'adulte, contre `VOI-3`, `VOI-5`, `VOI-8` | recherche de *adulte* dans tous les indices et consignes servis | **population inchangee.** Les seuls indices qui demandent quelque chose a l'adulte sont ceux des six paquets a voix (un indice par paquet, inchange). Les autres occurrences (`cp-emc-alerte`, `cp-eps-regles-de-securite`, `cm1-emc-*`, `cm1-sciences-*`) parlent d'un adulte comme contenu (*prevenir un adulte*), et ne demandent rien a l'adulte qui accompagne |
| `D3` | l'unicite de sens d'un identifiant de condition | garde-fou `PAR-5` lance sur l'arbre ; lecture a la main des 16 fichiers de `squad/` touches par s54 contre la section 4 du registre | **tenue.** Garde-fou sur `5cad6650` avant mon diff : 10 tests, 0 echec. Lecture a la main : aucun identifiant de condition ou de reserve non alloue ; les seuls motifs hors table sont des codes de ligne (`HG-CM1-nn`). Mon diff ajoute `PCO-CE1-R1` ; voir section 3 |
| `D4` | la coherence de mes conditions entre elles | lecture deux a deux, sur les populations partagees, de `LON-CE1-1` a `-5`, `TRV-CE1-1` a `-7`, des conditions de ligne de s48 servies (`PRS`, `FUT`, `PCO`, `TPS`, `SYC`) et de `PRO-CE1-6`, `-7`, `DUR-CE1-6`, `CAL-CE1-5` de s43 | **deux ecarts, tous deux de moi, aucun ne demande d'amender une condition** ; section 2 |
| `D5` | l'homogeneite de registre entre jeux qu'un meme enfant enchaine | les 27 entrees CE1 du catalogue : titre, consigne, presence d'une question dans les enonces | **tenue sur la famille de `K1`.** Les cinq jeux de francais CE1 dont aucun enonce ne pose de question (`homophones`, `temps`, `conjugaison-present`, `futur`, `passe-compose`) ont une consigne qui la pose. Les trois jeux de conjugaison ont des titres paralleles (*Les verbes au present / au futur / au passe compose*) ; seule la consigne du futur nomme le temps, ce que `K1` exigeait et que rien n'exige ailleurs. **Deux observations**, sans reserve : *"Memoriser ses tables de multiplication"* est le seul titre CE1 a l'infinitif avec un possessif, il se lit comme la ligne du programme et non comme un nom de jeu ; *"Lis bien, puis trouve la reponse !"* est la seule consigne CE1 qui finit par un point d'exclamation |
| `D6` | un gabarit qui compose une phrase autour d'un champ de contenu (famille de `REV-1`) | relecture des gabarits modifies depuis le 2026-09-11 ; toutes les expressions `th:text` qui concatenent ; regles `content:` de `recreo.css` ; jeu des ecrans touches | **tenue, aucune composition neuve.** Quatre commits, et non trois comme l'ecrivait mon arbitrage : `a6a0b9f0` est l'ecran de fin de s35, date du jour meme de cette passe-la et jugee par elle. Les trois autres : `776cb642` (tableau support), `8d29e726` (libelle de zone hors de la boite), `90c36ab0` (reproduction d'oeuvre). Chacun rend une valeur seule et entiere dans son element. Les compositions du depot sont les cinq deja relevees a s30 ; *"Pour le "* + niveau a desormais trois valeurs (CP, CE1, CM1), toutes compatibles avec *le* (vu : *"Pour le CE1"*). Les trois ecrans touches joues : `bandit-manchot-sciences-extraire-information-cm1` (tableau), `designation-symetrie-ce1` (legende hors de la boite, erreur et indice), `bandit-manchot-decrire-une-oeuvre-cp` (oeuvre). `saisie.html` n'a pas change depuis s30 : `SAI-R1` reste ouverte, non remesuree |
| `D7` | la contrefacon par tirage (un item resolu par un autre item du meme paquet) | - | **non cherchee.** Population du depot entier, hors de 0,5, comme annonce. Personne ne doit la croire tenue |

## 2. `D4` - ce que la lecture deux a deux trouve

**2.1 `FUT-CE1-3` (b) contre `TRV-CE1-2` : contradiction, deja trouvee et reparee.** Le present du meme verbe,
admis en distracteur du futur, est une phrase juste avec un mot de temps (*"demain, je chante"*). Trouvee a mon
arbitrage de s54 (section 1.1), reparee par `K1` sans toucher aucune des deux conditions : la consigne demande
le futur. **Verifiee a l'ecran** sur les 400 manches jouees (`FICHE-bandit-manchot-futur-ce1.md`).

**2.2 Ma barre de la section 1.2 de mon arbitrage contre mon propre indice `N1` : ecart de redaction.** Ecrite
*"un critere ne doit pas etre satisfait par un distracteur de son item"*, la barre condamnerait a la lettre
`N1`, que j'ai ecrit le meme jour. Le motif de s52, que la barre disait reprendre *"sans rien y ajouter"*,
vise un critere presente comme ce qui fait la bonne reponse. Lecture fixee dans
`FICHE-bandit-manchot-passe-compose-ce1.md`, section 4 ; aucune condition n'est amendee, aucun verdict ne
change.

**2.3 Paires lues, coherentes** :

- `LON-CE1-2` (*"la derniere est la question"*) contre les enonces a trou de `PRS`, `FUT`, `PCO`, `HOM`, `TPS` :
  coherente par la precision de s48, section 1.1 (la question peut etre portee par la consigne), verifiee en
  `D5`.
- `TPS-CE1-2` (*"le verbe decide"*, la moitie des items sans mot de temps) contre les indices du futur et du
  passe compose qui renvoient au mot de temps : ces indices ne sont servis que sur des items qui en portent
  un, et sont vrais la. Aucune condition ne se contredit.
- `TRV-CE1-2` (bonne reponse au plus la moitie du temps la plus longue) contre `PCO-CE1-3` (a) : l'infinitif
  depasse le participe d'une lettre, la bonne reponse n'est donc jamais la plus longue quand (a) est servie.
  Ce n'est pas un indice de surface independant de la competence : la lettre en plus est le *-r* de
  l'infinitif, que l'enfant doit justement voir.
- `TRV-CE1-5` (douze mots, pas identique sur tout le paquet) contre `PRO-CE1-7`, `CAL-CE1-5` : meme
  formulation, populations disjointes.

**2.4 Observation, sans ecart** : ce qu'une situation ne doit pas supposer chez l'enfant est ecrit trois
fois au CE1, avec trois listes (`TRV-CE1-6` : configuration familiale, logement, jardin, animal, vacances,
objet possede, et pour ce qui s'adresse a l'enfant seul ; `PRO-CE1-6` : jardin, chambre, animal, argent de
poche, voiture, vacances, pour toute situation ; `DUR-CE1-6` : avion, vacances, un adulte qui le conduit). Les
populations sont disjointes (francais, problemes, durees) : rien ne se contredit. Une modification de l'une ne
se propagera pas aux deux autres ; je le sais et je le garde.

## 3. L'identifiant que mon diff ecrit

`PCO-CE1-R1`, reserve de `FICHE-bandit-manchot-passe-compose-ce1.md`. Garde-fou `PAR-5` lance sur mon diff,
ligne de la section 4 ajoutee et ligne d'etat non encore portee : *"declare 922 identifiants alloues et 158
prefixes distincts, sa table en porte 923 et 158"*. Ligne d'etat portee a **923 / 158**, chiffre recopie du
garde-fou. L'instance B de s54 n'a rien commite a cette heure ; si elle alloue aussi, la reparation a la
fusion est arithmetique.

## 4. Observations hors de la portee D, vues en jouant `D6`

Elles ne sont pas des reserves et ne sont pas de mon lot (`PAR-2`) : je les ecris pour ne pas les avoir vues
sans les dire.

1. `bandit-manchot-sciences-extraire-information-cm1` : la consigne *"Lis le texte ou le tableau, puis reponds a
   la question."* et l'enonce *"Lis ce tableau, puis reponds a la question."* disent deux fois la meme chose a
   l'ecran. Sciences CM1, instance B.
2. `bandit-manchot-decrire-une-oeuvre-cp` : le texte alternatif de l'oeuvre servie est *"Une reproduction
   d'oeuvre a regarder attentivement avant de repondre."*, qui ne decrit pas l'oeuvre (`CON-5`). Arts
   plastiques CP.

## 5. Ce que cette passe ne couvre pas

- `D7`, en entier.
- Le rendu : couleur, contraste, taille, position, defilement (`MNC-1`).
- Les branches de `s54-nadia-b`, `s54-nadia-c`, `s54-lea-art` non fusionnees a `5cad6650` : le depot qu'elles
  ajouteront n'est pas lu ici.
- La continuite cycle 2 / cycle 3 (`CYC-1`) : portee **C**, pas **D**.

## 6. Ce que je route

| A qui | Quoi |
|---|---|
| `camille-po` | `PCO-CE1-R1` en story (mot pour mot, 0,25) ; les deux observations de la section 4, pour les instances qui portent ces lignes |
| `sofia-sm` | `D7` reste non cherchee : a engager comme poste distinct si l'equipe veut la voir tenue |
| `theo-archi` | rien de neuf : `D6` ne trouve aucune composition neuve |

**Signe** : `nadia-prof`, s54, instance A, 2026-10-08.
