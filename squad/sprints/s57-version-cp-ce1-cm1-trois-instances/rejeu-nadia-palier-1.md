# s57, palier 1 - audits et rejeux de `nadia-prof` : postes 3, 4 et 6 ; reponses a `Q-C1` et `Q-N2`

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-14, 19:27 (UTC+02:00), lu par `date`.
- **Arbre** : branche `s57-nadia-rejeu`, worktree dedie, partie de `round-two` a `ea6d6f92`. `round-two` est passe a
  `6a45399d` pendant le travail (amendement du planning, `9d4c98ec`) ; aucun fichier commun avec ce commit.
- **Instances** : A (poste 3), A' (poste 4), B (poste 6, `Q-N2`) ; `Q-C1` porte sur le poste 8 (B).
- **Audit joue** : les trois jars construits et joues, un par branche. Rien n'est fusionne ; chaque verdict porte sur un
  commit nomme.

## Resume

| Poste | Branche, commit | Verdict | Fiche |
|---|---|---|---|
| 3, `VSJ-CE1-R1` (A) | `s57-marc-vsj-ce1-r1`, `7dc40fb0` | **`VSJ-CE1-R1` levee.** `FRA-CE1-13` reste **VALIDE SOUS RESERVE**, reserve neuve non bloquante `VSJ-CE1-R2` | `squad/conformite/FICHE-bandit-manchot-verbe-sujet-ce1.md`, rejeu du 2026-10-14 |
| 4, `ce1-phrases-types` (A') | `s57-fra-ce1-10`, `1f6642ab` | **VALIDE SOUS RESERVE** : Done pedagogique de `FRA-CE1-10`, reserve non bloquante `TPH-CE1-R1` | `squad/conformite/FICHE-bandit-manchot-phrases-types-ce1.md` (ouverte par ce verdict) |
| 6, `S54-OBS-SCI-CONSIGNE` (B) | `s57-sci-consigne`, `c3875936` | **VALIDE**, sans reserve. `SCI-CM1-20` non rouverte | `squad/conformite/FICHE-bandit-manchot-sciences-extraire-information-cm1.md`, rejeu du 2026-10-14 |

Correspondance des mots : *ACCEPTE* = **VALIDE**, *ACCEPTE SOUS RESERVE* = **VALIDE SOUS RESERVE**. **Aucun refus** : aucune
marge de rejeu n'est entamee.

**Les verdicts valent pour les commits joues.** Une fusion qui change le contenu servi (paquet, catalogue, gabarits) se
nomme a moi avant d'etre faite.

## 1. Ce que j'ai lance, par lot

| | Poste 3 (A) | Poste 4 (A') | Poste 6 (B) |
|---|---|---|---|
| `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl` | `BUILD SUCCESS`, 2504 tests, 0 echec | `BUILD SUCCESS`, 2520 tests, 0 echec | `BUILD SUCCESS`, 2499 tests, 0 echec |
| JDK (`java.version`, rapport Surefire de `bootstrap`) | 21.0.10 | 21.0.10 | 21.0.10 |
| `bash outils/verifier-lignee.sh` | sort 0 | sort 0 | sort 0 |
| Dossier de poste (`I1`) | `s57-p1-nadia-prof-a` | `s57-p1-nadia-prof-ap` | `s57-p1-nadia-prof-b` |
| Port (`I2`), libre avant (`I3`) | 18511 ; `netstat -ano` filtre ne rendait rien | 18512 ; idem | 18513 ; idem |
| Journal de demarrage | *"Tomcat started on port 18511"*, PID 55476 | *"... 18512"*, PID 47420 | *"... 18513"*, PID 42788 |
| Arret | `taskkill` sur ce PID seul | idem | idem |
| Parties jouees par les routes servies | 27 ; 24 items sur 24 ; 555 erreurs ; 0 ecart | 15 ; 24 sur 24 ; 223 erreurs ; 0 ecart | 6 ; 12 sur 12 ; 0 ecart |

Total des tests de chaque reacteur : celui de **cette branche seule** (sa base plus son lot), pas celui de l'arbre fusionne
(`planning.md`, "Ordre de fusion"). Les jobs `prefixes`, `chemins` et `outils` de ces trois commits ne sont pas les miens ;
je ne les deduis pas.

**Le pli** : rendu a 744 x 551 apres une erreur, Chrome sans tete, `getBoundingClientRect()`, item par item. Je n'ai pas
lance `pilote-q7.mjs` ni le port 19222 (`planning.md`, "Un port que la table ne controle pas") ; methode et etalonnage dans
la fiche de `-13`, rejeu du 2026-10-14, section 1. Le profil de navigateur est un repertoire neuf de mon dossier de poste ;
aucun processus de navigateur n'a ete arrete par nom.

## 2. Poste 3 : les points demandes

- **Propriete 8 amendee** : trois items de sujet sur quinze ont un distracteur avant le verbe, au plus un par item, au
  moins un apres sur chacun ; tenue au jeu (fiche, section 2).
- **`E1` et `E2`, tels que la demande me les nomme** (*"distracteur de temps ou de lieu, jamais une personne"*) : *le soir*,
  *le matin* (temps), *le toit* (lieu) ; aucun n'est une personne ni un animal, aucun ne fait l'action. **Tenus.** Les
  reperes `E1` et `E2` ne sont ecrits dans aucun fichier de `round-two` ni des trois branches (`git grep`), je les lis
  comme le point 1 de l'amendement de la propriete 8. *(Inexact, annotation du 2026-10-15 en fin de fichier.)*
- **Liste d'ouvertures de `TRV-CE1-6`** : **inchangee**. *Le* et *Sur* y etaient avant le lot ; aucun mot d'ouverture neuf
  n'etait necessaire.
- **Ce que le jeu a montre en plus** : `VSJ-CE1-R2`, l'indice coupe par le pli sur les items 16 et 20 a 744 x 551 (20 :
  introduit par le lot ; 16 : anterieur, ce qui corrige mon constat de s56, fiche section 3).

## 3. Poste 4 : les points demandes

- **Aucune phrase imperative** (`TPH-CE1-3`), **sens d'un groupe de mots** (`TPH-CE1-2`), **regle fausse dans un indice**
  (`TRV-CE1-5`) : **tenus tous les trois**, phrase par phrase et indice par indice dans la fiche, section 3.
- **Le titre *"Les types de phrases"*** : **tenu**, motif dans la fiche, section 4.
- **`TPH-CE1-R1`** : a 744 x 551, apres une erreur, l'indice n'est au-dessus du pli sur **aucun** des 24 items, et le
  verdict est coupe sur 6. Non bloquante par le precedent de `RDO-CP-R1`, que je ne releve pas apres coup ; c'est la
  reserve la plus lourde du palier, et elle pose `Q-N4` a l'utilisatrice (section 8).

## 4. Poste 6 : les points demandes

- **Critere 5** : une partie de dix manches jouee, chaque enonce lu une fois, puis les 12 vus a l'ecran. **Tenu.**
- **`E3`, accents des 12 enonces** : relus un par un a l'ecran, recopies dans la fiche ; aucun manquant a ma lecture. Le
  repere `E3` n'est ecrit nulle part dans le depot *(inexact, annotation du 2026-10-15 en fin de fichier)* ; je le lis comme la demande de relecture des 12 enonces. **Ce controle
  reste celui du `relecteur-pedagogique`** (`DEC-0002`, amendement du 2026-08-04 ; `DEC-0056`) ; ma lecture ne le remplace pas.
- **Critere 2 amende** (`DEC-0120` A.70) : la commande de paires rend `12 0 0` sur `3a3ea97e..c3875936`. Le contenu tient le
  critere amende ; la porte P-4 (lignee) est nommee par `sofia-sm` et reste a elle.

## 5. `Q-C1` : `D7` couvre-t-elle `alt`, `title`, `aria-label` ?

**Oui.** `D7` dit ce que l'ecran de fin donne a lire ; un enfant qui entend l'ecran par un lecteur d'ecran recoit ces
attributs comme du texte, et `title` s'affiche au survol. Un `alt="celle-ci"` designerait une oeuvre absente exactement comme
le noeud de texte que `RDO-CP-R2` refuse. La propriete, completee :

> Sur l'ecran de fin d'un jeu dont la bonne reponse est une reproduction, juste ou revele, **tout texte rendu par le fragment
> `retour`, noeud de texte ou valeur d'un attribut `alt`, `title` ou `aria-label`**, appartient a la liste fermee de `D7`, ou
> est vide. Un element sous `aria-hidden="true"` n'en rend aucun (l'icone du signal).

**Aujourd'hui** : `fragments/retour.html` n'en porte aucun (lu) ; les ecrans de fin que j'ai joues sur les trois jars n'en
rendent aucun (mon script releve `alt`, `title`, `aria-label` sur chaque ecran : liste vide). **Mutation qui doit rougir** :
un `alt` ou un `aria-label` portant un texte d'item sur cet ecran. La forme du test est a `yanis-qa` et au developpeur, comme
pour `D7`. Je n'alloue rien : `D7` est un repere de `lecture-nadia-palier-1.md`, pas un identifiant de condition.

## 6. `Q-N2` : prix de l'audit d'`HS-ACCUEIL-APM-R2`

**0,25.** Motif : c'est le rejeu d'une reserve que j'ai moi-meme ecrite (`APM-R2`, `FICHE-accueil-par-matiere.md`), sans
contenu pedagogique, dont la propriete est tenue par un test de route. Mon audit avant fusion (`DEC-0117` section 11) joue
le jar : le parcours de la reserve depuis V1 (*"Tout voir"* pendant une partie, *"Continuer la partie"*, sortie, choix de la
classe : l'etage 1), le meme depuis V2 avec *"Par matière"* (V2 reste), le cas d'abandon deja vert (V2 retenue), et une bascule
non annulee hors partie, pour voir qu'elle marche encore. Precedents du meme prix : le rejeu mot pour mot de `PCO-CE1-R1` et
celui de `S54-OBS-SCI-CONSIGNE` (ce palier). **Le pli de l'accueil n'y est pas un motif** (`FICHE-accueil-par-matiere.md`).
**D'ou il se prend** : ni des marges ni des 0,75 de B (`DEC-0120` A.74, que je ne contredis pas) ; des 8,0 du palier qui
l'engage, sur l'instance que sa composition designe. Apres un refus, un second rejeu coute 0,25 de plus.

## 7. Lecture complete des items (`S57-ARB-PALIER-1`, critere 2)

La forme est la mienne ; une ligne par paquet. Les lectures ont ete faites le 2026-10-14 (`lecture-nadia-palier-1.md`,
section 2, commit `7161ab69`), sur les fichiers de `3a3ea97e`, aucun n'a change depuis sur les branches jouees.

- **lecture complete** : `ce1-phrases-types`, 24 items lus (2026-10-14, section 2.2), puis joues au jar (`1f6642ab`).
- **lecture complete** : `ce1-accord-groupe-nominal`, 24 items lus (2026-10-14, section 2.3) ; lot non joue ici.
- **lecture complete** : `ce1-texte-narratif`, 20 items lus (2026-10-14, section 2.1), version d'avant la correction de `D1`
  et `D2` ; je relirai les deux items corriges a l'audit du lot.

Le commit qui porte ces lignes est celui du verdict de `ce1-phrases-types` : il est son propre ancetre (critere 3).

## 8. Questions pour l'utilisatrice

- **`Q-N4` - Le pli doit-il redevenir bloquant pour un jeu neuf ?** `DEC-0051` (s21, votre arbitrage) disait : *"les jeux
  neufs naissent sans le defaut"* de pli. Ma pratique depuis le traite en reserve non bloquante : `RDO-CP-R1` (s56) et
  aujourd'hui `TPH-CE1-R1`, ou l'indice n'est au-dessus du pli sur aucun des 24 items a 744 x 551. Si vous repondez *oui*,
  `FRA-CE1-10` passe au refus sur ce seul motif, `ce1-phrases-types` attend une forme de `theo-archi` et `ines-design` pour
  les propositions longues, et je l'ecris comme une barre neuve et datee, pas comme une correction de ce verdict. Si vous
  repondez *non*, rien ne change. **Ma recommandation : non pour ce lot, oui pour la suite** : la cause est commune a tout
  jeu a propositions longues, et une forme qui la regle servirait les lots suivants avant leur branchement.

## 9. Routage

| A qui | Quoi |
|---|---|
| `camille-po` | `FRA-CE1-10` a `couvert-valide` apres fusion de `1f6642ab` ; stories de `VSJ-CE1-R2` et `TPH-CE1-R1` avec leurs criteres de levee, sans reformulation ; `FRA-CE1-13` et `SCI-CM1-20` ne bougent pas ; `Q-C1` repondue (section 5), a reporter au critere de `S56-RDO-CP-R2` ; `Q-N2` repondue (section 6) |
| `sofia-sm` | les trois verdicts pour `cloture-palier-1.md` ; les lignes de lecture complete (section 7) ; les commits joues, a comparer au contenu fusionne |
| `theo-archi`, `ines-design` | la cause de `TPH-CE1-R1` (propositions longues sur deux ou trois rangees a 744 px) ; aucune forme demandee avant la reponse a `Q-N4` |
| `yanis-qa` | `Q-C1` : le test de vue lit aussi `alt`, `title`, `aria-label` ; mutation de la section 5 ; la mutation du test neuf de `c3875936`, que je n'ai pas rejouee |
| `relecteur-pedagogique` | l'accentuation et la ponctuation des 12 enonces de `c3875936` (section 4) |
| `marc-dev`, `lea-dev` | rien a reprendre sur leurs commits avant fusion |
| Utilisatrice | `Q-N4` |

## 10. Ce dont je reste la seule garde, ajoute par ce palier

| Propriete | Pourquoi aucun test ne la tient |
|---|---|
| Que le complement en tete de `-13` soit de temps ou de lieu et ne fasse pas l'action | le test voit la virgule et le rang |
| Absence de phrase imperative, sens des groupes, verite des indices de `-10` | le test lit des signes, pas le sens |
| La position de l'indice par rapport au pli (`VSJ-CE1-R2`, `TPH-CE1-R1`), sur tout jeu | aucun automate du reacteur ne rend une page |
| Que la question rappelee de l'ecran de fin se lise sans son document (sciences CM1, observation) | lecture d'enfant |

## 11. Tarifs consommes

A : 0,5 (rejeu `VSJ-CE1-R1`). A' : 1,0 (audit de `ce1-phrases-types`). B : 0,25 (rejeu de `S54-OBS-SCI-CONSIGNE`). Les
reponses a `Q-C1` et `Q-N2` sont prises sur l'arbitrage de chaque instance, sans prix neuf. **Marges de rejeu de A, A' et B
intactes**, 0,75 non affectes de B intacts.

## 12. Ce que ce document ne fait pas

Il ne fusionne rien, ne touche ni la matrice, ni le catalogue, ni un paquet, ni du code. Il n'audite ni
`ce1-accord-groupe-nominal` (poste 5, non livre a cette tache) ni `ART-CP-10`.

**Signe** : `nadia-prof`, s57, palier 1, instances A, A' et B, 2026-10-14.

## 13. Jobs de `ci.yml` lances pour ce commit

Sur ce worktree, avec les cinq fichiers du commit, avant commit. Je ne deduis aucun job que je n'ai pas lance.

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl` : `BUILD SUCCESS`, 2498 tests (120,
  31, 486, 1484, 371 et 6 d'integration), 0 echec, 0 erreur, 0 ignore ; JDK 21.0.10 (`java.version` d'un rapport de
  `bootstrap`). Le build a lu ce fichier avant l'ajout de cette section, qui ne contient aucun tableau. Etape *"Package du
  fat jar"* non lancee sur ce commit. Avant lui, `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test
  -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest -Dsurefire.failIfNoSpecifiedTests=false` : 1 echec sur 10,
  *"declare 981 identifiants alloues et 178 prefixes distincts, sa table en porte 983 et 178"* ; ligne d'etat portee a
  983 / 178 ; relance : 10 tests, 0 echec, `BUILD SUCCESS`.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; les fichiers suivis et non suivis passes a `verifier-chemins.sh` :
  sort 0.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` (21
  cas) sortent 0 ; le sujet du commit passe dans `verifier-prefixes.sh` : sort 0.
- **`outils`** : non lance ; aucun outil n'est modifie.

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue.

## Amendement du 2026-10-14 : reponse de l'utilisatrice a `Q-N4`

Forme 2, relayee par l'agent principal, non lue de sa main : **`Q-N4` = non.** *(Ses mots ne sont pas cites : forme 2
incomplete, annotation du 2026-10-15 en fin de fichier.)* Le pli a 744 x 551 ne redevient pas
bloquant pour un jeu neuf. **Consequences** : `FRA-CE1-10` reste **VALIDE SOUS RESERVE** avec `TPH-CE1-R1` (fiche, amendement
du meme jour) ; aucun refus n'est ecrit. **Ma recommandation de la section 8, *"oui pour la suite"*, n'est pas retenue par
l'utilisatrice** : je traite le pli des lots suivants comme une reserve datee, sur le regime de `RDO-CP-R1` et `TPH-CE1-R1`.
La section 8 reste ecrite telle quelle. Pour `theo-archi` et `ines-design` : la forme des propositions longues n'attend plus
`Q-N4` (section 9) ; elle suit la story que `camille-po` tirera de `TPH-CE1-R1`.

## Amendement du 2026-10-14, 20:40 : poste 5 audite, reponses a `ines-design` (poste 8)

Branche `s57-nadia-rejeu` avancee par `git merge --ff-only` a `faf7e285` (`round-two`, qui contient deja `13d6285f`) ;
`bash outils/verifier-lignee.sh` sort 0. Rien au-dessus n'est reecrit.

- **Poste 5, `ce1-accord-groupe-nominal` (A')** : branche `s57-sofia-fra-ce1-15`, commit `72931c94` (le sommet `3459ddb8`
  n'ajoute que le rapport de la developpeuse). **VALIDE SOUS RESERVE** : Done pedagogique de `FRA-CE1-15`, reserve neuve non
  bloquante `GNA-CE1-R1` (pli a 744 x 551 sur 23 items sur 24). Titre *"Des mots bien accordés"* et consigne reprise de
  `-13` et `-10` : tenus. Fiche : `squad/conformite/FICHE-bandit-manchot-accord-groupe-nominal-ce1.md`. Build de la branche :
  2519 tests, 0 echec, JDK 21.0.10 ; jar sur 18512, PID 53496, arrete par ce PID ; 15 parties, 24 items sur 24, 0 ecart.
  Audit 1,0 sur A' ; marge de A' intacte.
- **Poste 8, six questions d'`ines-design`** : `reponse-nadia-forme-poste-8.md` (ce dossier). `L1` et `L2` acceptes ; veto
  sur le bandeau a croix sous la reference, deux colonnes acceptees sous le meuble ; coupes hors barres confirmees sans effet
  sur le refus ; verdict sur deux lignes accepte si la coupure tombe entre les deux phrases ; repli `L1 + L3` sans objet.
- **Precision** de `TPH-CE1-R1` (fiche de `FRA-CE1-10`, bloc date) : a 518 - 568 le texte de l'indice reste lisible, le cadre
  est coupe.
- **Routage ajoute** : `camille-po`, `FRA-CE1-15` a `couvert-valide` apres fusion de `72931c94` et story de `GNA-CE1-R1` ;
  `sofia-sm`, ce verdict pour `cloture-palier-1.md` et la resolution de `catalogue.yaml` entre les postes 4 et 5 sans toucher
  les six lignes de chaque entree ; `ines-design`, la section 2 de la reponse.

Jobs de `ci.yml` pour le commit de cet amendement : section suivante.

### Jobs de `ci.yml` lances pour le commit de l'amendement de 20:40

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl` : `BUILD SUCCESS`, 2498 tests (120, 31, 486,
  1484, 371 et 6 d'integration), 0 echec ; JDK 21.0.10. Le build a lu ce fichier avant l'ajout de cette section, sans
  tableau. Avant lui, le garde-fou du registre : *"declare 983 identifiants alloues et 178 prefixes distincts, sa table en
  porte 984 et 178"*, ligne d'etat portee a 984 / 178, relance verte (10 tests, 0 echec).
- **`chemins`**, **`prefixes`** : voir le compte rendu de la tache ; **`outils`** : non lance, aucun outil modifie.

## Annotation du 2026-10-15 (`nadia-prof`, s57, palier 1) : `D1` et `D3` du `relecteur-pedagogique`

Source : `releve-relecteur-palier-1.md`, section 3.2. Rien au-dessus n'est efface ; les phrases visees portent un renvoi ici.

**`D1`, correction de mon propre constat.** J'ai ecrit, section 2, que `E1` et `E2` ne sont ecrits *"dans aucun fichier de
`round-two`"*, et section 4 que `E3` *"n'est ecrit nulle part dans le depot"*. **C'est faux, et c'etait faux sur ma propre
base** : `git grep -c -w -e E1 -e E2 ea6d6f92 -- squad/conformite/FICHE-bandit-manchot-verbe-sujet-ce1.md` rend 2 (lignes 64
et 92 : `E1` et `E2` de `marc-dev`, dans une fiche que j'ai ecrite) ; `git grep -c -w E3 ea6d6f92` rend 4 sur
`docs/backlog/backlog.md` et 1 sur `ARBITRAGE-PREALABLE-s56-instance-a-francais-ce1.md`. Ces codes y designent d'autres objets.
**Lecture juste** : `E1`, `E2` et `E3` de cette section 2 et de cette section 4 sont les reperes de la demande de l'agent
lanceur, propres a cette demande ; je les ai lus par leur contenu, cite a chaque fois. Aucun constat ni verdict de ce fichier
ne change.

**`D3`, forme 2 incomplete.** L'amendement du 2026-10-14 ecrit *"`Q-N4` = non"* : c'est ma notation, pas les mots de
l'utilisatrice, et `DEC-0062` 3.1 exige ses mots entre guillemets. **Je ne peux pas les citer** : aucun fichier du depot ne
les porte (`DEC-0120`, ligne 1387, ecrit la meme notation), et je ne les ai pas recus. Je les demande a l'agent lanceur par
`sofia-sm` ; ils s'ajouteront ici par une annotation datee. **Ce qui depend de ce point** : aucun verdict. Le caractere non
bloquant de `TPH-CE1-R1` repose sur le precedent de `RDO-CP-R1` (section 3), pas sur `Q-N4` ; `Q-N4` portait sur ma
recommandation de section 8, plus stricte, qui reste non retenue tant que l'utilisatrice ne l'a pas acceptee.
