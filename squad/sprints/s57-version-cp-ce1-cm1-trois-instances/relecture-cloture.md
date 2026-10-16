# [Sprint s57] Relecture de cloture du palier 2 - relecteur-pedagogique

- **Auteur** : `relecteur-pedagogique`. **Date** : 2026-10-16. **Action** : `DEC-0116` section 9, sans budget.
- **Arbre** : branche `s57-relecteur-p2c`, issue de `round-two` a `bb322938` (`git rev-parse origin/round-two` rend `bb322938` : poussee).
- **Perimetre** : les livrables non executables ajoutes ou modifies entre `6dbb855d` (fusion de la cloture du palier 1, 2026-10-15 17:44) et `bb322938` : 159 fichiers (`git diff --name-only --diff-filter=AM 6dbb855d bb322938`). Le texte de l'enfant n'est relu que pour l'orthographe et la ponctuation (section 6) ; le niveau scolaire est a `nadia-prof`, les decisions techniques a `theo-archi`.
- **Ce que ce fichier ne fait pas** : il ne modifie aucun autre fichier, ne prononce aucun Done, ne tranche ni le produit ni l'architecture. Chaque ecart porte un verdict : **corriger** (texte seul, sans ambiguite, par l'auteur nomme) ou **router** (a trancher par l'agent nomme). Aucune correction n'est appliquee ici.
- **Mesures** : scripts jetables hors depot, lances sur l'arbre de `bb322938`. Je n'ai pas relance `mvn` : les comptes de tests sont verifies par arithmetique entre documents, pas remesures.

## Resume

| Categorie | Ecarts | Dont a router |
|---|---|---|
| 1. Conventions de redaction (CLAUDE.md) | **0** | 0 |
| 2. Renvois qui pointent vers rien, ambigus ou perimes | **10** (`R1` a `R10`) | 1 |
| 3. Chiffres sans methode ou sans perimetre | **2** (`N1`, `N2`) | 0 |
| 4. Provenance des mots de l'utilisatrice (`DEC-0062`) | **3** (`P1` a `P3`) | 3 |
| 5. Planning contre rapports | **4** (`C1` a `C4`) | 0 |
| **Total** | **19** | 4 |

**Les plus graves, dans l'ordre** : `P2` (une reponse de l'utilisatrice sans guillemets pilote deux decisions, dont une contredit une `DEC` en vigueur), `R5` (trois commentaires faux commites, un commit engage et jamais livre, absent du dernier amendement), `C1` (une phrase du planning prete a l'utilisatrice un propos que le planning attribue a `sofia-sm` ailleurs), `C4` (la fiche des pronoms dit `refuse` en tete et `VALIDE` en pied), `R4` (collision de `Q-T11`, point `P8` route a moi : reponse en section 2), puis `P3`, `P1`.

## 1. Conventions de redaction : 0 ecart

Commande (liste des fichiers ajoutes ou modifies du palier, passee au garde-fou de la CI), sortie telle quelle :

```
git diff --name-only --diff-filter=AM 6dbb855d bb322938 | bash .github/scripts/verifier-chemins.sh
Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies.
exit=0
```

Controle a la main, sur les lignes ajoutees (`git diff -U0 6dbb855d bb322938`, hors images) : nom de compte et adresse (`clara`, `wiatrowski`, `neoxam.com`), `AppData`, lettre de lecteur suivie d'un segment, `/c/<segment>`, `/Users/`, `/home/<segment>`, `/tmp/`, `Program Files`, `Python314` : **0 occurrence reelle**. Les rapports d'agents (`rapport-marc-dev-*`, `rapport-lea-dev-*`, `rapport-sofia-dev-palier-2.md`, `rapport-qualite-*`) ne citent aucun chemin de poste. Formes portables rencontrees, acceptees : 18 lignes de `../recreo-s57-...` (nom de worktree voisin, relatif) ; `C:/.../tmp.XXXXXXXXXX` dans `rapport-qualite-lot-24.md:79` (la forme est citee sans segment reel, ce que le script accepte).

Caracteres des lignes ajoutees autres que des lettres : `«` et `»` (5254 et 5240, contenu des paquets de l'enfant), `U+00A0` (28, memes paquets), `U+00D7` (177, donnees JSON de `releves-s57/`). Tiret cadratin ou demi-cadratin, guillemet typographique, emoji, symbole decoratif, espace de fin de ligne : **0**.

## 2. Renvois : 10 ecarts

| Code | Ou | Ecart | Verdict |
|---|---|---|---|
| `R1` | `planning.md:1286`, `:1369`, `:1370`, `:1739` ; `rapport-qualite-garde-c1.md:100` | Noms tronques par `...` qui ne resolvent pas en chemin : `mesure-ines-pa7-...`, `mesure-ines-pa8-...`, `...-rejeu.md`, `ADR-0031-...md`. Le releve du palier 1 (`C5`, `C7`, `C8`) avait traite la meme forme comme un defaut. Les noms existent : `mesure-ines-pa7-rct-ce1-r1-2026-10-16.md`, `mesure-ines-pa8-rdo-cp-r3-2026-10-16.md`, `rejeu-nadia-palier-2-a-lot05-rejeu.md`, `ADR-0031-un-ecran-ne-gouverne-jamais-l-apparence-d-un-autre.md` | **corriger** : `sofia-sm` (planning), `yanis-qa` (rapport) |
| `R2` | `rapport-qualite-lot-24.md:79` ; `reponse-nadia-q-palier-2.md:115` | Renvoi a `planning.md` par un numero de section sans amendement : `section 4` (13 sections 4 dans le fichier, la bonne est celle de l'amendement 12:01, ligne 915), `section 9` (7 sections 9, la bonne est ligne 597, amendement 20:56) | **corriger** : `yanis-qa`, `nadia-prof` (nommer l'amendement par son horodatage, comme les autres renvois du palier) |
| `R3` | `planning.md:1754` | Les rejeux de `-05` et de `RCT-CE1-R1` sont cites pour *"Tarif consomme"* ; ce champ n'existe que dans `rejeu-nadia-palier-2-ap.md:185`. Dans `rejeu-nadia-palier-2-a-lot05-rejeu.md:46` et `rejeu-nadia-palier-2-a-rct.md:47`, le chiffre est dans la ligne de routage (*"0,5 consomme en A"*). Le chiffre est juste, le nom du champ ne resout pas | **corriger** : `sofia-sm` |
| `R4` | `arbitrage-nadia-palier-2.md:179`, `:191` ; `backlog.md:22192`, `:22207` ; `avis-theo-poste-8-art-cp-10.md:816`, `:839`, `:860` ; `rapport-ines-r8-forme-commune.md:142` ; `FICHE-bandit-manchot-rapprocher-deux-oeuvres-cp.md:308` | `Q-T11` nomme deux questions : les libelles de gabarit (`camille-po`) et la reduction des cases a 720 (`theo-archi`). Point `P8` de `cloture-palier-1.md:149`, route au `relecteur-pedagogique`. La proposition `Q-T13` (`backlog.md:22265`, `CR10`) n'est reprise dans aucun fichier signe par un autre agent (`git grep -n "Q-T13" -- squad docs` : `backlog.md` seul) | **router**, reponse en section 3 |
| `R5` | `manche.html:207-211` (ligne 210) ; `TexteAccentueDesGabaritsTest.java:158-160` ; `LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest.java:107` | Trois commentaires disent que la phrase de revelation est *"de nadia-prof (M4)"*, *"copie tel quel"* de `ARBITRAGE-PREALABLE-s55-instance-a-francais-ce1.md` 3.2, *"M4 du meme arbitrage, mot pour mot"*. La phrase servie est `Voici la bonne œuvre. Appuie dessus.` de `reponse-nadia-pa7-pa8.md` 2.4 (ligne 122) ; la `M4` de l'arbitrage de s55 porte l'ancienne. Dette `RL5` a `RL7` ecrite par `theo-archi` (`lecture-theo-p2-12-rdo-cp-r3.md`), engagee par `planning.md:1658-1670` (*"le commit n'existe pas"*), jamais livree (aucun commit depuis `23222465`), et absente de l'amendement de 20:15 (`git grep -n "RL5\|RL6\|RL7" planning.md` : lignes 1610 et 1658 a 1666 seulement). Mon releve des constantes (`releve-relecteur-palier-2-corrections.md`, section 3.3) ne jugeait que la typographie et ne les a pas vus | **corriger** : `sofia-sm`, casquette developpeuse (un commit, trois commentaires, `RD3-9` lu par la forme a parents) ; a defaut, la nommer au planning du palier 3 avec son etat |
| `R6` | `ce1-pronoms.yaml:14` ; `ce1-texte-narratif.yaml:14` ; `ce1-familles-de-mots.yaml:14` | (a) *"ce paquet attend l'audit de nadia-prof"* : les deux premiers sont **VALIDE** (fiches, 2026-10-16) ; c'est la famille de defaut que `D5` a fermee sur dix paquets (*"brouillon soumis"*), restee sur ces deux-la. (b) *"La question est portee par la consigne du jeu quand l'enonce ne la porte pas"* : les 24 enonces de `ce1-familles-de-mots` se terminent par `?` (`grep -c '?"$'` : 24 sur 24) et portent leur question ; meme phrase que celle que le releve du palier 1 a corrigee dans trois paquets (`C1` a `C3`, commit `f4e9190c`) | **corriger** : `marc-dev` ou `sofia-sm` ; (a) renvoyer a la fiche comme `D5`, (b) *"Chaque enonce porte sa question ; la consigne du jeu (catalogue.yaml) est generique."* |
| `R7` | `rapport-lea-dev-palier-2-rdo-cp-r3.md:10`, `:57` | La phrase de `nadia-prof` est citee en ASCII (*"oeuvre"*) et donnee comme egale au texte servi, alors que la source (`reponse-nadia-pa7-pa8.md:122`), le gabarit et les tests portent `œuvre`. Citation non mot pour mot | **corriger** : `lea-dev` |
| `R8` | `rapport-marc-dev-palier-2-corrections.md:52-54` | `RC1-1` a `RC1-3` numerotent les items *14, 18, 19* sans dire la base ; les mesures d'`ines-design`, la fiche et l'avis de `theo-archi` disent *13, 17, 18* (comptes depuis 0, dit dans `backlog.md:22865`). Meme item, deux numeros, la base n'est ecrite que dans le backlog | **corriger** : `marc-dev` (ecrire *"comptes depuis 1"*) |
| `R9` | `composition-palier-2.md:148` | `Q-P8` (a `sofia-sm`) n'est refermee par son code nulle part (`git grep -n "Q-P8"` : une ligne). Le planning la traite par ses affectations, sans la nommer | **corriger** : `sofia-sm` (une ligne dans le prochain amendement) |
| `R10` | `DEC-0120` A.107 (ligne 1712) | Citation de `nadia-prof` : *"reste `couvert-valide`, aucune ligne ne bouge"*. La source (`rejeu-nadia-palier-2-b.md:17`) dit *"reste `couvert-valide`, non rouverte ; aucune ligne ne bouge"* : une proposition est retiree sans marque | **corriger** : `camille-po` (restituer la phrase ou marquer la coupe) |

## 3. Reponse a `P8` (collision de `Q-T11`)

Un code qui designe deux questions est un defaut de coherence terminologique : chacun des fichiers cites en `R4` est lu sans que le lecteur sache de laquelle il s'agit, sauf ceux qui nomment l'auteur (`rejeu-nadia-b2-palier-1.md` sections 4, 5 et 7, `cloture-palier-1.md:149`). Il faut un code distinct. La convention de `backlog.md:22265` est acceptable : `Q-T11` = les libelles de gabarit (`camille-po`), `Q-T13` = la reduction a 720 (`theo-archi`). `Q-T13` est libre (verifie ci-dessus). Je ne reecris pas les fichiers signes par d'autres : **router** a `theo-archi` (`avis-theo-poste-8-art-cp-10.md:816, 839, 860`, par une annotation datee), `ines-design` (`rapport-ines-r8-forme-commune.md:142`), `nadia-prof` (`FICHE-bandit-manchot-rapprocher-deux-oeuvres-cp.md:308`). Pour tout texte neuf, la convention vaut des maintenant.

## 4. Chiffres sans methode ou sans perimetre : 2 ecarts

| Code | Ou | Ecart | Verdict |
|---|---|---|---|
| `N1` | `planning.md:1078`, `:1079`, `:1135` ; `DEC-index.md:128` | *"501 tests, 0 echec"* et *"496 tests"* sont des comptes du seul module `adapter-web` (mesures par `-pl adapters/adapter-web -am`, `backlog.md:22564`). Ni le module ni la commande ne sont a cote du chiffre, alors que `CLAUDE.md` (section Prerequis de build) l'exige de toute mesure partielle citee dans un fichier commite. Le reacteur complet est 2669 | **corriger** : `sofia-sm` (planning), `camille-po` (index) : *"501 tests d'adapter-web (`-pl adapters/adapter-web -am`)"* |
| `N2` | `planning.md:1331` (dix-huit lignes), `:1434` (dix-sept), `:1756` (seize) | Trois recomptes `awk` sur un `registre` que `sofia-sm` ecrit *"moi-meme"* et ne commite pas : la commande ne se rejoue pas. `DEC-0120` A.105 (lignes 1657-1673) donne les dix-sept lignes en entier et ses totaux se recalculent (A 3,25 ; A' 3,00 ; B 1,75 ; 8,00) | **corriger** : `sofia-sm` (reproduire le registre dans l'amendement, ou citer celui d'A.105) |

## 5. Provenance des mots de l'utilisatrice : 3 ecarts

`S2` : **conforme**. `arbitrage-nadia-palier-2.md:285`, `planning.md:431`, `:621` et `:679` disent *"sans mots"* et n'en ecrivent aucun. Aucun enonce `recu de MASTER` n'est employe dans les fichiers du palier ; aucun n'est donc motif.

| Code | Ou | Ecart | Verdict |
|---|---|---|---|
| `P1` | `rejeu-nadia-palier-1.md:239-250` ; `FICHE-bandit-manchot-phrases-types-ce1.md:154-165` ; `reponse-nadia-q-palier-2.md:77` ; `FICHE-bandit-manchot-texte-narratif-ce1.md:128` | `Q-N4` : les mots *"le pli n'est pas bloquant"* sont inscrits, avec la mention `de l'utilisatrice`. Restent : (a) la date est celle du relais (2026-10-16), pas celle de la reponse, que les fichiers datent du 2026-10-14 (dit par `nadia-prof`, `planning.md:679`) ; (b) les trois annotations sont du meme commit (`5d2f34fb`) et disent chacune figer la citation (*"Cette annotation et celle de la fiche figent"* contre *"Cette occurrence fige"*, `DEC-0062` 3.5, qui vise la forme 3) : l'anteriorite ne se determine pas par `git log`, les textes sont identiques, donc aucun ecart de contenu, mais la source citee par les fichiers suivants (`FICHE-...-familles-de-mots-ce1.md:94`, `...-pronoms-ce1.md:102`) est `reponse-nadia-q-palier-2.md` section 2 ; (c) `FICHE-...-texte-narratif-ce1.md:128` ecrit encore "la reponse de l'utilisatrice a `Q-N4` (2026-10-14, non)", la notation que `nadia-prof` dit ne pas etre ses mots. Le qualificatif *"relayee"* n'est pas l'une des trois formes (`E5` de `cloture-palier-1.md:161`, ouvert) | **router** : `camille-po` (la regle pour une reponse relayee, `E5`) ; **corriger** (c) et nommer une seule source en (b) : `nadia-prof` |
| `P2` | `planning.md:1567`, `:1777` ; `rapport-marc-dev-palier-2-corrections.md:84` ; `DEC-0120` A.106 (ligne 1690) | `Q-U11` : *fenetre bornee* est ecrit en gras, sans guillemets, par `sofia-sm`, sans les mots de l'utilisatrice ni la date de sa reponse (l'agent principal la relaie le 2026-10-16). `marc-dev` met *"fenetre bornee"* entre guillemets, sans fichier ni date, dans un rapport commite a 18:56, avant le planning (19:28) : c'est la premiere inscription, et elle ne porte pas la provenance. `camille-po` ne l'a pas inscrite (`git grep -n "fenetre bornee" -- squad/decisions` : rien). Cette reponse **pilote deux choses** : `S57-RETRAIT-PRONOMS-CE1` non engagee (`planning.md:1567`) et le paquet `bandit-manchot-pronoms-ce1` laisse servi, alors que `DEC-0120` A.106 (`Acceptee`) decide son retrait et n'est pas amendee ; `planning.md:1777` le dit (*"aucun `DEC` ne la tranche"*) | **router** : `camille-po` (inscrire les mots entre guillemets avec la date, amender A.106 ou la fermer) ; `marc-dev` : renvoyer a cette inscription une fois ecrite |
| `P3` | `DEC-0120` A.94 (lignes 1505-1511) ; `DEC-index.md:494` ; `avis-theo-palier-2.md:6` | A.94 (a) et (b) : reponses *"oui"* de l'utilisatrice, *"mots non cites"* ; A.94 (c) cite *"oui"* entre guillemets. Meme nature, deux traitements : `DEC-0062` 3.1 exige les mots cites et la date. `avis-theo-palier-2.md:6` ecrit *"decision de l'utilisatrice du 2026-10-16, relayee ... non lue de sa main"* sans mots et sans renvoi a A.94 : un renvoi de forme 1 (`DEC-0120` A.94 (a)) suffirait | **router** : `camille-po` (citer *"oui"* en (a) et (b)) ; **corriger** : `theo-archi` (renvoi a A.94) |

## 6. Planning contre rapports : 4 ecarts

| Code | Ou | Ecart | Verdict |
|---|---|---|---|
| `C1` | `planning.md:1777` contre `:1567` | La section 5 de 19:20 ecrit : *"S'il n'a pas lieu avant la cloture du palier 2, la question du retrait revient au palier 3 : je le dis, je ne la decide pas"* (propos de `sofia-sm`). La section 7 de 20:15 ecrit que *"la reponse relayee le 2026-10-16 (fenetre bornee, 19:20, section 5) disait que"* cela. Une condition de l'agent est attribuee a l'utilisatrice | **corriger** : `sofia-sm` (annotation datee) |
| `C2` | `planning.md:1750` contre `:1738` | *"Tous les postes sont audites"* : le tableau juste au-dessus dit `p2-2` *"non entamees"* et `p2-1`, `p2-6` n'ont pas d'audit (livre, fusionne) | **corriger** : `sofia-sm` (*"tous les postes soumis a l'audit"*) |
| `C3` | `planning.md:1771` contre `:458` | *"Les cinq premiers etaient non engages a l'ouverture ; p2-7 etait engage le 2026-10-16"* : les six l'etaient (`:458`, *"non engage, report compte"*) ; p2-7 est engage le 2026-10-16 (09:03). La phrase laisse lire que p2-7 a l'ouverture etait engage | **corriger** : `sofia-sm` |
| `C4` | `FICHE-bandit-manchot-pronoms-ce1.md:11`, `:19-24` ; `FICHE-bandit-manchot-familles-de-mots-ce1.md:12` | Statut de tete de la fiche des pronoms : **refuse**, *"entree encore au catalogue servi : son retrait est la story `S57-RETRAIT-PRONOMS-CE1`"*, *"La ligne `FRA-CE1-05` reste `non couvert`"* ; le pied de la meme fiche (ligne 312) porte **VALIDE**, et les autres fiches rejouees (phrases-types, accord-groupe-nominal, verbe-sujet, texte-narratif, rapprocher-deux-oeuvres-cp, familles-de-mots) ont leur tete a jour. `planning.md` route le statut de tete a `camille-po` (*"reste a faire"*), sans date. Les deux fiches ecrivent aussi *"Non pousse"* : `edcd3de9` et `9f110983` sont sur `origin/round-two` (`git branch -r --contains`) | **corriger** : `nadia-prof` (tete de fiche) ; la matrice (`FRA-CE1-05`, 147 / 200) reste a `camille-po`, comme le planning le dit |

### Verifie, et qui tient

- **Le correctif de sommet de rejeu (`planning.md:1695-1710`)** : `git diff --name-only 7c9ac220 3eef868d -- adapters domain application bootstrap pom.xml` rend cinq fichiers (`manche.html` et quatre tests, noms identiques a ceux du planning), `git diff --quiet` sort 1 ; `git diff --quiet 19c8a7b2 3eef868d -- adapters domain application bootstrap pom.xml outils .github` sort 0 ; meme commande de `3eef868d` a `bb322938` : sort 0. Les rejeux de `nadia-prof` disent le meme fait (`rejeu-nadia-palier-2-a-lot05-rejeu.md:24`, `FICHE-...-pronoms-ce1.md:322`). La phrase de 19:40 (*"code de `7c9ac220`"*) reste en place, corrigee par l'annotation de 20:15, comme la convention le veut.
- **Comptes** : 2669 = 120 + 31 + 501 + 1630 + 381 + 6 ; la chaine 2597, 2599, 2603, 2640, 2664, 2669 se recalcule avec les deltas de chaque fusion (+2, +4, +37, +24, +5) ; ArchUnit recompte au disque : 41 declarations, 38 `@ArchTest`, 16 fichiers ; matrice par la commande de sa methode : 147 / 4 / 49 / 77, total 277, soit 147 / 200 ; `git diff --shortstat` des cinq fusions du soir (`5d07c1bd`, `19c8a7b2`, `ae116186`, `a5a486ad`, `7c9ac220`) egal aux nombres du planning ; prix recomptes (A 3,25, A' 3,00, B 1,75 ; factures 2,75, 2,25, 1,25, total 6,25 ; marges 1,5 ; non affecte 0,25).
- **Renvois** : 147 SHA de 8 caracteres hexadecimaux, 7 SHA complets : tous resolvent, sauf huit qui sont des prefixes de SHA-256 de jar ou de feuille, nommes comme tels ; 237 renvois *fichier + section* : 0 section absente ; 105 renvois `DEC-0115`, `DEC-0116`, `DEC-0120` + `A.n` : 0 sans titre ; codes `CR1` a `CR39` continus, sans doublon ; 71 citations *"..."* a cote d'un fichier source relues par recherche de chaine, 29 non retrouvees par l'automate, **les 29 relues a la main : 28 faux positifs** (gras, retour a la ligne, accents, source citee plus loin dans la phrase) **et un ecart, `R10`**.
- **Orthographe et ponctuation de l'enfant** : `ce1-pronoms.yaml` (101 champs lus), `ce1-familles-de-mots.yaml` (97), `ce1-texte-narratif.yaml` (101), `cm1-sciences-extraire-information.yaml` (55), et les titres et consignes des deux entrees neuves du catalogue : 0 signe double sans `U+00A0`, 0 chevron sans `U+00A0`, 0 double espace, 0 caractere typographique, 0 `oe` sans ligature ; lecture complete des 24 et 20 enonces, indices et propositions des deux paquets neufs : aucune faute d'orthographe ni d'accent. Le point ouvert du releve (`2377` contre `224` espaces avant `?`) est route (`planning.md:1577`) et inchange.

## 7. Routage

| A qui | Quoi |
|---|---|
| `sofia-sm` | `R1`, `R3`, `R5` (commit de commentaires ou report ecrit), `R9`, `N1`, `N2`, `C1`, `C2`, `C3` ; reprendre cette liste dans `review.md` |
| `camille-po` | `P1` (regle pour une reponse relayee, `E5`), `P2` (inscrire *fenetre bornee*, amender `DEC-0120` A.106), `P3` (A.94), `R10` ; `N1` pour `DEC-index.md:128` ; matrice (`FRA-CE1-05`) |
| `theo-archi` | `R4` (annotation de l'annexe E.6), `P3` (renvoi a A.94) |
| `nadia-prof` | `P1` (une seule source, ligne 128 de la fiche du texte narratif), `C4` (tete de fiche, `Non pousse`), `R2` |
| `marc-dev` | `R6`, `R8`, `P2` |
| `lea-dev` | `R7` |
| `yanis-qa` | `R1` (`rapport-qualite-garde-c1.md:100`), `R2` |
| `ines-design` | `R4` (`rapport-ines-r8-forme-commune.md:142`) |

## 8. Ce que ce releve ne couvre pas

- Le planning, le backlog et `DEC-0120` n'ont pas ete relus ligne a ligne : les 19 ecarts viennent de lectures ciblees (rapports, amendements du soir, `DEC-0120` A.94 a A.110, `DEC-0122`, `ADR-0031`, `ADR-0065` A3) et des controles scriptes de la section 6.
- La CI distante n'est pas lue ; aucun Done n'est prononce. Les jobs `chemins` et `prefixes` : `verifier-chemins.sh` lance sur le diff du palier (section 1), `verifier-prefixes.sh` lance sur le sujet du commit de ce releve.
- Les quatre classes de `bootstrap` qui lisent `squad/` (`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest`, `ToutesLesReferencesDeDecisionResolventTest`, `ConcordanceDesTagsDAgentTest`) ont tourne avec ce fichier dans l'index : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=<les quatre> -Dsurefire.failIfNoSpecifiedTests=false`, perimetre `bootstrap` et ses amonts (mesure partielle, `-am` present), `BUILD SUCCESS`, 19 tests, 0 echec. Ce n'est pas `mvn verify`.
