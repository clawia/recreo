# [Sprint s57] Cloture du palier 1 - SQUELETTE

- **Statut : squelette, le palier 1 n'est pas clos ; la section 4 est remplie au fil des fusions (2026-10-14), les sections 1, 2 et 5 portent des constats du 2026-10-14 avant cloture, le reste ne l'est pas.** Ce fichier ne vaut pas cloture : il n'a ni horodatage de fin, ni verdict, ni report constate par la cloture. Il fixe les sections que `squad/README.md` section 4 demande, pour que la cloture ne les invente pas. **A la cloture, `sofia-sm` le remplace en une seule ecriture** (pas d'ajout a la suite) ; le diff garde alors le squelette comme etat precedent.
- **Horodatage de fin du palier** : non ecrit.
- **Ecrit par** : `sofia-sm`, casquette Scrum Master, squelette du 2026-10-14, sur `round-two` a `6ca84317`.

## 1. Verdict ou report de chaque poste engage

Un poste engage a un verdict ecrit de `nadia-prof` ou un report ecrit (condition 2 de 11.3). Etat a remplir a la cloture, avec la source de chaque cellule.

| Poste | Instance | Verdict de `nadia-prof`, ou glissement ecrit | Fiche ou fichier du verdict |
|---|---|---|---|
| 3, `VSJ-CE1-R1` | A | constate avant cloture, 2026-10-14 : `VSJ-CE1-R1` levee ; `FRA-CE1-13` VALIDE SOUS RESERVE, reserve neuve non bloquante `VSJ-CE1-R2` | `rejeu-nadia-palier-1.md` ; `FICHE-bandit-manchot-verbe-sujet-ce1.md` |
| 4, `ce1-phrases-types` | A' | constate avant cloture : VALIDE SOUS RESERVE, `TPH-CE1-R1` non bloquante ; `Q-N4` = non | `FICHE-bandit-manchot-phrases-types-ce1.md` |
| 5, `ce1-accord-groupe-nominal` | A' | constate avant cloture : VALIDE SOUS RESERVE, `GNA-CE1-R1` non bloquante | `FICHE-bandit-manchot-accord-groupe-nominal-ce1.md` |
| 6, `S54-OBS-SCI-CONSIGNE` | B | constate avant cloture : VALIDE, sans reserve ; `SCI-CM1-20` non rouverte | `rejeu-nadia-palier-1.md` ; `FICHE-bandit-manchot-sciences-extraire-information-cm1.md` |
| 7, `ce1-texte-narratif` | A | **engage le 2026-10-14** par l'amendement de `planning.md` (`marc-dev`, 1,0) ; aucun verdict : a remplir | a remplir |
| 8, correction et rejeu d'`ART-CP-10`, si engage | B | a remplir | a remplir |
| 9, branche B2, si engagee | B | a remplir | a remplir |

## 2. Glissements, reports, limite

Regle (`DEC-0120` A.72, confirmee par `planning.md`, amendement du 2026-10-14 18:20) : un poste glisse au plus une fois ; le second glissement est un report compte ; le troisieme report est interdit. A ecrire a la cloture : chaque glissement avec sa date et sa cause, dit a l'utilisatrice le jour meme.

- Poste 7 : engage apres l'ouverture ; **son premier report reste ecrit** (A.83). S'il n'est pas audite a la cloture, il glisse une seule fois (A.72) : a ecrire ici avec date et cause. Postes 8 et 9 : non engages a cet instant (`theo-archi`, `C1` a `C4`).
- Reports comptes a l'ouverture du palier 1 (`planning.md`, amendement) : poste 7, premier report ; poste 8, premier report de la reprise ; ligne `ART-CP-10`, huit evenements sans fermeture. **A reprendre ici tels quels s'ils n'ont pas change.**
- `S54-OBS-SCI-CONSIGNE` : deux reports comptes ; glissement permis une fois ; acte d'abandon a la cloture du palier 2 si la story n'est pas livree et rejouee (A.73). A remplir.
- `RP-ACCUEIL-147` : echeance avant la cloture du palier 1 (A.74). **Non fait au 2026-10-14** : `grep -n "147" squad/conformite/ARBITRAGE-PREALABLE-accueil-par-matiere.md` rend encore les lignes 41 et 236 (la ligne 398 est l'ecart constate). Fait, ou glissement ecrit ici. A remplir a la cloture.

## 3. Porte technique

CI verte avec les jobs nommes, sur l'arbre fusionne. A remplir : commit teste, jobs lances parmi `prefixes`, `chemins`, `outils`, `build` avec leur sortie, jobs non lances nommes comme tels. **Total de tests recompte par `sofia-sm` sur cet arbre**, avec sa methode (commande, perimetre, JDK rapporte par la machine virtuelle des tests) ; un chiffre annonce par un agent n'est pas relaye. Dernier total connu : 2498 sur `544159d9` (`planning.md`), non remesure sur l'arbre fusionne.

## 4. Fusions et comptage du catalogue

Une ligne par fusion (`planning.md`, section "Ordre de fusion") : `grep -c "^  - id:" adapters/adapter-content/src/main/resources/catalogue.yaml` apres la fusion vaut le compte d'avant plus le nombre d'entrees fusionnees ; `grep -c "paquet: <nom>$"` rend 1 pour chaque paquet fusionne. Compte de depart mesure sur `round-two` a `6ca84317` : 146. Un ecart arrete la fusion et va a `theo-archi`. A remplir.

| Fusion | SHA de fusion | Compte d'avant | Entrees fusionnees | Compte d'apres | `grep -c "paquet: <nom>$"` | Tests du reacteur sur l'arbre fusionne | Base de la branche, ancetre de `round-two` |
|---|---|---|---|---|---|---|---|
| Poste 4, `s57-fra-ce1-10` (`1f6642ab`, `marc-dev`) | `c935d764` | 146 | 1 | 147 | `ce1-phrases-types` : 1 | 2520 | `3a3ea97e`, oui |
| Poste 5, `s57-sofia-fra-ce1-15` (`3459ddb8`). **Branche de `sofia-sm` en casquette developpeuse, fusionnee par `sofia-sm` en casquette Scrum Master : aucun autre juge que `yanis-qa` a la cloture** | `d67204de` | 147 | 1 | 148 | `ce1-accord-groupe-nominal` : 1 | 2541 | `ea6d6f92`, oui |
| Poste 6, `s57-sci-consigne` (`ac32f976`, `lea-dev`) | `14d6cf47` | 148 | 0 | 148 | sans objet, aucune entree | 2542 | `faf7e285`, oui |
| Poste 3, `s57-marc-vsj-ce1-r1` (`7dc40fb0`, `marc-dev`) | `65d00d8d` | 148 | 0 | 148 | sans objet, aucune entree | 2548 | `6ca84317`, oui |

**Methode des tests** : `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl`, apres chaque fusion, somme des attributs `Tests run` des cinq modules Surefire et du Failsafe lus dans la sortie Maven ; JDK 21.0.10 (`java.version` d'un rapport Surefire de `bootstrap`). Modules apres la derniere fusion : domain 120, application 31, adapter-web 486, adapter-content 1534, bootstrap 371, integration 6. Ecarts : +22 (poste 4), +21 (poste 5), +1 (poste 6), +6 (poste 3), chacun egal au lot que la branche annonce. Aucun total annonce par un agent n'est relaye. Base : 2498 sur `544159d9`, **non remesuree** sur `round-two` avant la premiere fusion.

**Conflits.** Seule la fusion du poste 5 en a eu, sur `catalogue.yaml` (une entree) et sur `TexteAccentueExposeParLePortEntrantTest.java` (trois blocs d'inventaire, aux memes ancres), celui prevu par `rapport-sofia-dev.md`. **Resolution** : les ajouts des deux cotes sont gardes dans l'ordre des postes (4 puis 5), `nombreDeManches: 10` redonne a l'entree du poste 4 dont les deux entrees se partageaient la ligne. Aucune assertion n'est modifiee ni retiree : `git diff c935d764 d67204de` sur ces deux fichiers rend 6 et 9 lignes ajoutees, 0 supprimee. Le poste 5 est ma propre branche : ce point est dit ici pour que `yanis-qa` le relise a la cloture.

**Ce qui est fusionne est ce qui a ete joue** : `git diff <branche> <fusion> -- <paquet>` rend 0 ligne pour les quatre paquets (`ce1-phrases-types`, `ce1-accord-groupe-nominal`, `cm1-sciences-extraire-information`, `ce1-verbe-sujet`) ; les entrees de catalogue des postes 4 et 5 sont identiques a celles de leur branche (empreinte des six lignes). Le poste 6 est fusionne a `ac32f976`, le commit que `nadia-prof` a joue.

## 5. Ordre du critere 3 de `S57-ARB-PALIER-1`

Une ligne par paquet : le commit qui porte la ligne *"lecture complete"* de `nadia-prof` est ancetre du commit du premier verdict (`git merge-base --is-ancestor`). Constate le 2026-10-14, a confirmer a la cloture : les trois lignes *"lecture complete"* sont dans `rejeu-nadia-palier-1.md`, section 7, commit `13d6285f` (`ce1-phrases-types` 24 items, `ce1-accord-groupe-nominal` 24, `ce1-texte-narratif` 20). `ce1-accord-groupe-nominal` : verdict `0688b581`, `git merge-base --is-ancestor 13d6285f 0688b581` sort 0. `ce1-phrases-types` : la ligne et le premier verdict sont dans le **meme commit** `13d6285f` ; un commit est son propre ancetre, l'ordre est donc tenu a la lettre et non demontre entre deux commits. `ce1-texte-narratif` : la ligne dit *"version d'avant la correction de `D1`"* ; si `nadia-prof` veut une lecture de la version corrigee, c'est a elle de l'ecrire, aucun verdict n'existe encore.

## 6. Isolation

Lecture de `nadia-prof` des transcriptions du palier : repertoires et ports de la table de `planning.md` respectes ou ecart nomme. Ce fichier ne la remplace pas. A remplir.

## 7. Obstacles du palier

Tableau repris dans `review.md` a la cloture du sprint : obstacle, categorie, agent, artefact attendu, etat. A remplir.

## Decisions prises

**Squelette : aucune decision n'est prise par ce fichier.** La section est a remplir a la cloture avec chaque `ADR` et chaque `DEC` cree ou modifie pendant le palier, ou l'enonce explicite qu'aucune n'a ete prise.
