# s57 - palier 4, cloture : ce qui reste ouvert apres la fusion, clos ou reporte (`camille-po`)

- **Auteure** : `camille-po`, Product Owner, 2026-10-23. Arbre : `round-two` a `c08e49ab`, branche `s57-camille-p4-cloture`, `git status --short` vide avant ecriture.
- **Demande** (forme 2 de `DEC-0062` G, relayee par l'agent lanceur, non lue de la main de l'utilisatrice) : *"clos ou reporte ce qui reste ouvert apres la fusion, ecris la cloture et push round-two"*. Ce fichier est **la part produit** : pour chaque point ouvert, CLOS avec sa raison ou REPORTE avec ou, a qui, quand, quel declencheur. **Il ne leve aucune reserve** (`nadia-prof` seule), **ne tranche aucune forme** (`theo-archi`), **ne fixe aucun prix**, ne pousse rien. `cloture-palier-4.md`, les fusions et la poussee sont a `sofia-sm`.
- **Ou c'est ecrit** : `DEC-0120`, amendement du 2026-10-23, A.171 a A.179 ; `DEC-0125` ; `MATRICE-couverture.md` (note du 2026-10-23) ; `registre-prix-palier-4.txt` ; `docs/backlog/backlog.md` (annotation du 2026-10-23, quatre stories neuves).
- **Etat du depot lu avant d'ecrire** : `FICHE-bandit-manchot-interet-commun-ce1.md` sur `round-two` porte encore la reserve 3 *"maintenue, non mesure"*. La branche `s57-nadia-p4-lp6b` porte un commit de `nadia-prof`, `5d038278` (2026-10-23, 11:14), qui la leve et dit des reserves 6 et 7 : *"Elles peuvent etre reportees"*. Il n'etait pas la quand j'ai commence (`git log round-two..s57-nadia-p4-lp6b` vide) ; je l'ai lu avant de commiter. **Il n'est pas fusionne dans `round-two`.**

## 1. Les decisions

| Point | Decision | Raison | Destinataire | Declencheur |
|---|---|---|---|---|
| **P1a** `LP6`, reserve 3 (pli) | **CLOS**, aucune story | `ines-design`, `mesure-ines-palier-4-lp6-pli-744x551-c975a7b8.md`, sur `round-two` : 0 element sous le pli, 41 px de marge, 24 items, six ordres. **`nadia-prof` l'a levee** (`5d038278`) ; je constate, je ne leve pas | `sofia-sm` : fusionner `s57-nadia-p4-lp6b` | la fusion (RF11) |
| **P1b** `LP6`, reserve 6 (items 10 et 18, *"coin"* vers *"salle"*, regle des mots de 17 a 15 sur 24) | **REPORTE** : `S57-EMC-CE1-04-R1`, non engagee, report 1 | Modifier le paquet change ce qu'elle a joue : c'est un lot neuf. Elle ecrit des reserves 6 et 7 *"sans rejeu complet"* : relecture des items touches et du compte de la regle des mots, prix a elle. Reserve non bloquante, la barre de la fiche (un tiers par portee) est tenue. Criteres a ses mots, valeurs d'aujourd'hui : `grep -c "du coin dessin"` 1, `grep -c "du coin lecture"` 1, la commande de la regle des mots rend `17 24` | `sofia-sm` (code, developpeuse A') ; `nadia-prof` (rejeu, prix) | composition du palier 5 ; **apres** `S57-GARDE-PAQUET-INTERET-COMMUN` |
| **P1c** `LP6`, reserve 7 (item 13, fin de recreation) | **REPORTE** : meme story | Idem. `grep -c "récréation"` rend 1, `grep -c "pour tout le monde"` rend 0. Elle dit *"un but propre a la classe [...] ou la formule 'pour tout le monde'"* : le critere reprend les deux, a sa lecture | idem | idem |
| **P2** `T5` (`theo-archi`) : aucun test n'interdit *chacun*, *tous*, *toute* dans les scenes | **REPORTE** : `S57-GARDE-PAQUET-INTERET-COMMUN`, hors `PAR`, prix 0, report 1 | Test seul, aucun paquet touche : le Done de `LP6` tient. Critere rouge aujourd'hui : mutation M3, 74 tests verts. La forme (liste fermee, sonde, `ADR-0013`) est a `theo-archi`, le moment a `sofia-sm` (*"Le moment [...] est a `sofia-sm`"*) | `sofia-sm` (code), `theo-archi` (forme), `yanis-qa` (morsures) | avant `S57-EMC-CE1-04-R1` : cette story retouche des scenes gardees avec une marge nulle en portee *tous* (5 pour 5) |
| **P3a** `G5`, `LG5` (seuil `UN_TIERS` de 3 a 4 laisse tout vert) | **REPORTE** : meme story, critere 2 | Persistent depuis le rapport sur `ebbb2136`, nommes une seconde fois sur `c975a7b8`. Rouge aujourd'hui : 74 sur 74. Sa proposition : *"Une sonde a 1 croisee sur 3"* | `sofia-sm`, `yanis-qa` | idem |
| **P3b** `G11`, `LG11` (filtre par portee retire, tout vert) | **REPORTE** : meme story, critere 3 | Idem | idem | idem |
| **P3c** `.as(marqueur)` des sondes 46 a 49 : le message ne nomme pas la sonde | **REPORTE** : meme story, critere 4 | Defaut de diagnostic, le test mord (46 a 49 chacune rouge). Rouge aujourd'hui : *"Expecting code to raise a throwable."* | idem | idem |
| **P4a** `LP1`, pli a la revelation (17 sur 24, 13 px) | **REPORTE** : `S57-PLI-MOTS-INVARIABLES-REVELATION` (A.168), report 1 | La bonne reponse et *Continuer* restent au-dessus ; seule la correction (raccourcir des enonces) modifie le paquet. Le seuil est le sens de sa reserve | `nadia-prof` | elle lit la mesure `S3` et dit si elle maintient ; si elle leve, la story est fermee sans suite |
| **P4b** `LP1`, mots proposes par le clavier | **REPORTE** : `S57-SAISIE-MOTS-PROPOSES-PAR-LE-CLAVIER` (A.168), report 1 | `QP4` sans reponse de `theo-archi` ; l'effet sur l'appareil n'est pas testable | `theo-archi` ; l'utilisatrice (appareil) | reponse a `QP4` |
| **P4c** `LP3`, pli jamais mesure | **REPORTE** : `S57-PLI-LP3-LP5-MESURE`, neuve, report 1, hors `PAR`, prix 0 | Aucun releve n'existe (`grep -l -E "calendrier-dates\|droit-devoir" mesure-ines-*.md` : 0 fichier). C'est une mesure, pas un contenu | `ines-design` ; lecture `nadia-prof` | palier 5, ou plus tot si l'utilisatrice lance la mesure |
| **P4d** `LP4`, items 6 et 10 | **REPORTE** : `S57-EMC-CE1-02-R1` (A.168), report 1 | Modifier le paquet invalide le Done | `sofia-sm` ; `nadia-prof` (prix) | composition du palier 5 |
| **P4e** `LP4`, pli non remesure | **CLOS cote produit**, aucune story | 0 sur 24 a `f36d83bc`, 67 px de marge (section T2) | `nadia-prof` : lire et lever | sa lecture |
| **P4f** `LP5`, indice de l'item 19 | **REPORTE** : `S57-EMC-CE1-03-R1`, neuve, report 1 | Reserve non bloquante, le distracteur n'est pas defendable. `grep -c "font pour Hugo"` rend 1 | `sofia-sm` ; `nadia-prof` (prix, lecture de l'indice) | composition du palier 5 |
| **P4g** `LP5`, pli jamais mesure | **REPORTE** : `S57-PLI-LP3-LP5-MESURE` | Idem P4c | `ines-design` | idem P4c |
| **P4h** `LP2`, pli non remesure | **CLOS cote produit** | 0 sur 21 (section R4) ; seule autre reserve de la fiche : aucune | `nadia-prof` : lire et lever | sa lecture |
| **P5a** Prix du rejeu 1 de `LP6` | **Registre : 0,50, `facture`** | Son estimation du 2026-10-22, *"ce n'est pas une mesure"* ; reprise telle quelle | `nadia-prof` : confirmer ou corriger | - |
| **P5b** Prix du rejeu 2 de `LP6` | **Registre : `non-chiffre`, 0,00** ; non clos | Elle ecrit *"Je ne l'estime pas en prix : une phrase"* ; le commit joue change dix scenes et cinq indices. Je ne fixe pas le prix a sa place | `nadia-prof` : le chiffre | sa ligne |
| **P5c** `PAR-8` en A' | **Depassement ecrit, aucun relevement** | A' = 5,50 plus le prix du rejeu 2 (5,75 a 0,25). Les mots *"si besoin releve le plafond"* nomment `LP3`, `LP5`, `p4-10`, pas `LP6`. Palier 4 : 12,50 sur 20,0 | l'utilisatrice (`QU2`) | sa reponse |
| **P6a** Matrice | **FAIT** : 154 / 202 devient **156 / 202 = 77,23 %** | `EMC-CE1-04` et `EMC-CE1-03` `couvert-valide`, `MAT-CE1-11` `en cours` ; les trois paquets sont ceux que `nadia-prof` a joues (`git diff <commit joue> HEAD -- <paquet>` : 0 ligne) | - | - |
| **P6b** Autres lots du palier | **CONSTAT**, rien a faire | `LP1` `FRA-CE1-21` `couvert-valide`, `LP2` `MAT-CE1-10` `en cours`, `LP4` `EMC-CE1-02` `couvert-valide` : deja ecrits (A.167). `LP9` `MAT-CE1-14` `non couvert`. `p4-10` : hors matrice | - | - |
| **P7a** `DEC` d'amendement | **ECRITE** : `DEC-0120` A.171 a A.179 ; `DEC-index.md` a jour | - | - | - |
| **P7b** `DEC-0125` | **ECRITE**, `Acceptee`, reversible | Troisieme non-entree de `S57-TYP1-TEXTES-DE-GABARIT` et `S57-MESSAGE-PARTIE-PERDUE` (A.150 exige la `DEC`) | l'utilisatrice, pour confirmer | une phrase d'elle, ou une mesure |
| **P7c** `REGISTRE-identifiants.md` | **CLOS, non modifie** | Aucun identifiant de condition ou de reserve n'est alloue ; `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` lance sur ce diff (section 3) | - | - |
| **P8a** `LP9` `MAT-CE1-14` grille | **REPORTE**, deuxieme ecartement ; un troisieme demande une `DEC` | `OV5` : aucune des trois conditions d'A.149 ecrite, aucun worktree. `AP2` tranchee : deuxieme, pas premier (A.150) | `nadia-prof`, `ines-design`, `theo-archi`, `lea-dev` | composition du palier 5 : les conditions sont ecrites **avant**, le lot est engage, ou une `DEC` s'ecrit |
| **P8b** `LP7` `MAT-CE1-15` | **REPORTE**, deuxieme ecartement | Motif d'A.150 inchange | `nadia-prof`, `camille-po` | idem |
| **P8c** `LP8` `FRA-CE1-03` | **REPORTE**, deuxieme ecartement | **Aucune story** (`grep -c "^### .*FRA-CE1-03" docs/backlog/backlog.md` rend 0) ; hors de la demande, je ne l'ecris pas ici | `camille-po` : la story avant la composition | idem ; premier de la file |
| **P8d** `S57-GARDE-CE1-PROPOSITIONS-ET-SUPPORTS` | **REPORTE**, report 1 | Ecart non tranche : la story dit `CA4` absent, `theo-archi` le cite fourni (`Q-X1`) | `nadia-prof` : une ligne dit si `Q-X1` vaut `CA4` | sa ligne, puis la composition |
| **P8e** `S57-TYP1-TEXTES-DE-GABARIT`, `S57-MESSAGE-PARTIE-PERDUE` | **CLOS, retirees** (`DEC-0125`) | Trois non-entrees ; `Q-T12` sans reponse, mesure jamais faite ; avis de `nadia-prof` *"faible priorite"* | utilisatrice | phrase, mesure ou avis de `nadia-prof` |
| **P8f** `S57-GARDE-NOMBRE-DE-MANCHES` (`p4-10`) | **CLOS, solde par l'entree** | Fusionne (`f9f7f0fc`) | - | - |
| **P8g** Commentaires de `src/` (ecarts 33, 40 a 44, part code de 37 et 38) | **REPORTE**, premier report a ma charge | 0 titre au backlog a l'ouverture et a la cloture ; non lus ici | `camille-po` (story), puis `sofia-sm` | avant la composition du palier 5 |
| **P9** Condition de composition de `LP6` | **Tenue a la lettre, sans avoir protege ce qu'elle visait** | Les verdicts de `LP4` (`70470aae`, 2026-10-22 15:18) et de `LP5` (`d5298c19`, 20:26) precedent le premier commit de `LP6` (`e85a93fe`, 23:08). Trois paquets ecrits en A', trois refus au premier passage, `LP6` refuse deux fois : la recommandation de `nadia-prof` 3.3 s'est verifiee | `nadia-prof`, utilisatrice : aucune regle neuve sans eux | composition du palier 5 |

## 2. Ce qui a besoin de l'utilisatrice

1. **`QU2`** : A' est a 5,50 sur 5,0 plus le prix du rejeu 2 de `LP6`, que `nadia-prof` n'a pas chiffre. Le depassement est ecrit comme depassement. Faut-il le laisser tel quel (palier clos) ou le consigner comme un relevement a 5,75 une fois le rejeu chiffre ?
2. **`DEC-0125`** : deux stories de typographie retirees apres trois non-entrees. Une phrase suffit pour en garder une.
3. **Le palier 5** (`QP7`, *"apres un palier 5 voir 6 je ne sais pas encore"*) : `LP9`, `LP7`, `LP8` n'ont plus de report a leur disposition. Leur composition au palier 5 est conditionnee a des ecrits qui n'existent pas.

## 3. Verification

Lancees le 2026-10-23 sur le diff de ce commit, dans le worktree `../recreo-s57-camille-p4-cloture`.

| Quoi | Commande | Sortie |
|---|---|---|
| Matrice avant / apres | la commande de la "Methode du compte" de `MATRICE-couverture.md`, une fois par etat | avant `cv 154`, `ec 6`, `nc 42`, `ncb 75`, total 277 ; apres `cv 156`, `ec 7`, `nc 39`, `ncb 75`, total 277 ; denominateur 202 ; par niveau CP 59 / 52, CE1 64 / 38, CM1 79 / 66 |
| Paquets joues = paquets fusionnes | `git diff <commit joue> HEAD -- <paquet>`, puis `wc -l` | 0 ligne pour `f863fa7a`, `5b9bb648`, `c975a7b8` |
| Entrees au catalogue | `grep -c "paquet: <nom>$"` sur `catalogue.yaml` | 1 pour chacun des trois paquets |
| Registre de prix | `awk -F'\|'` par instance et par etat | `A 2.25`, `Ap 5.5`, `B 4.75`, `total 12.5` ; `facture 9.5`, `consomme 1.5`, `non-engage 1.5`, `non-chiffre 0` |
| Valeurs d'aujourd'hui des criteres | `grep -c` sur les paquets ; la commande de la regle des mots | `du coin dessin` 1, `du coin lecture` 1, `enonce:.*salle` 5, `récréation` 1, `pour tout le monde` 0, regle des mots `17 24`, `font pour Hugo` 1 |
| Identifiants de condition | `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest -Dsurefire.failIfNoSpecifiedTests=false` (mesure partielle, `-am`) | exit 0, 10 tests, 0 echec, `BUILD SUCCESS` |
| Job `chemins` | `bash .github/scripts/verifier-chemins-test.sh`, puis `git ls-files` et les deux fichiers neufs vers `verifier-chemins.sh` | exit 0, exit 0, exit 0 |
| Job `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh`, puis `verifier-prefixes.sh` sur le message de ce commit | exit 0 ; le second est lance apres le commit |

**Le job `build` complet n'est pas lance** : ce commit ne touche ni code, ni paquet, ni catalogue ; `mvn verify` est la porte de la fusion et appartient a `sofia-sm`. Je n'annonce donc pas de porte technique verte.

## 4. Ce que ce fichier ne fait pas

Ne prononce aucun Done, ne juge aucun contenu, ne leve aucune reserve, ne fixe aucun prix, n'engage aucun poste, ne touche ni `planning.md`, ni `domain/`, `application/`, `adapters/`, `bootstrap/`, ni paquet, ni catalogue, ni fiche de conformite. Ne pousse pas, ne fusionne pas, ne rebase pas.
