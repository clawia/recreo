# [Sprint s57] Cloture du palier 1 - SQUELETTE

- **Statut : squelette, le palier 1 n'est pas clos.** Ce fichier ne vaut pas cloture : il n'a ni horodatage de fin, ni verdict, ni report constate par la cloture. Il fixe les sections que `squad/README.md` section 4 demande, pour que la cloture ne les invente pas. **A la cloture, `sofia-sm` le remplace en une seule ecriture** (pas d'ajout a la suite) ; le diff garde alors le squelette comme etat precedent.
- **Horodatage de fin du palier** : non ecrit.
- **Ecrit par** : `sofia-sm`, casquette Scrum Master, squelette du 2026-10-14, sur `round-two` a `6ca84317`.

## 1. Verdict ou report de chaque poste engage

Un poste engage a un verdict ecrit de `nadia-prof` ou un report ecrit (condition 2 de 11.3). Etat a remplir a la cloture, avec la source de chaque cellule.

| Poste | Instance | Verdict de `nadia-prof`, ou glissement ecrit | Fiche ou fichier du verdict |
|---|---|---|---|
| 3, `VSJ-CE1-R1` | A | a remplir | a remplir |
| 4, `ce1-phrases-types` | A' | a remplir | a remplir |
| 5, `ce1-accord-groupe-nominal` | A' | a remplir | a remplir |
| 6, `S54-OBS-SCI-CONSIGNE` | B | a remplir | a remplir |
| 7, `ce1-texte-narratif`, si engage | A | a remplir | a remplir |
| 8, correction et rejeu d'`ART-CP-10`, si engage | B | a remplir | a remplir |
| 9, branche B2, si engagee | B | a remplir | a remplir |

## 2. Glissements, reports, limite

Regle (`DEC-0120` A.72, confirmee par `planning.md`, amendement du 2026-10-14 18:20) : un poste glisse au plus une fois ; le second glissement est un report compte ; le troisieme report est interdit. A ecrire a la cloture : chaque glissement avec sa date et sa cause, dit a l'utilisatrice le jour meme.

- Reports comptes a l'ouverture du palier 1 (`planning.md`, amendement) : poste 7, premier report ; poste 8, premier report de la reprise ; ligne `ART-CP-10`, huit evenements sans fermeture. **A reprendre ici tels quels s'ils n'ont pas change.**
- `S54-OBS-SCI-CONSIGNE` : deux reports comptes ; glissement permis une fois ; acte d'abandon a la cloture du palier 2 si la story n'est pas livree et rejouee (A.73). A remplir.
- `RP-ACCUEIL-147` : echeance avant la cloture du palier 1 (A.74). Fait, ou glissement ecrit ici. A remplir.

## 3. Porte technique

CI verte avec les jobs nommes, sur l'arbre fusionne. A remplir : commit teste, jobs lances parmi `prefixes`, `chemins`, `outils`, `build` avec leur sortie, jobs non lances nommes comme tels. **Total de tests recompte par `sofia-sm` sur cet arbre**, avec sa methode (commande, perimetre, JDK rapporte par la machine virtuelle des tests) ; un chiffre annonce par un agent n'est pas relaye. Dernier total connu : 2498 sur `544159d9` (`planning.md`), non remesure sur l'arbre fusionne.

## 4. Fusions et comptage du catalogue

Une ligne par fusion (`planning.md`, section "Ordre de fusion") : `grep -c "^  - id:" adapters/adapter-content/src/main/resources/catalogue.yaml` apres la fusion vaut le compte d'avant plus le nombre d'entrees fusionnees ; `grep -c "paquet: <nom>$"` rend 1 pour chaque paquet fusionne. Compte de depart mesure sur `round-two` a `6ca84317` : 146. Un ecart arrete la fusion et va a `theo-archi`. A remplir.

| Fusion | SHA de fusion | Compte d'avant | Entrees fusionnees | Compte d'apres | Lignee verifiee (`verifier-lignee.sh`) |
|---|---|---|---|---|---|
| a remplir | a remplir | 146 | a remplir | a remplir | a remplir |

## 5. Ordre du critere 3 de `S57-ARB-PALIER-1`

Une ligne par paquet : le commit qui porte la ligne *"lecture complete"* de `nadia-prof` est ancetre du commit du premier verdict (`git merge-base --is-ancestor`). A remplir pour `ce1-phrases-types` (24 items), `ce1-accord-groupe-nominal` (24) et `ce1-texte-narratif` (20) s'il est engage.

## 6. Isolation

Lecture de `nadia-prof` des transcriptions du palier : repertoires et ports de la table de `planning.md` respectes ou ecart nomme. Ce fichier ne la remplace pas. A remplir.

## 7. Obstacles du palier

Tableau repris dans `review.md` a la cloture du sprint : obstacle, categorie, agent, artefact attendu, etat. A remplir.

## Decisions prises

**Squelette : aucune decision n'est prise par ce fichier.** La section est a remplir a la cloture avec chaque `ADR` et chaque `DEC` cree ou modifie pendant le palier, ou l'enonce explicite qu'aucune n'a ete prise.
