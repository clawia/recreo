# Confirmation de la porte technique - s32, lot `theo-archi` (exemption retiree, `LON-1`/`LON-2` encodees, deux reserves instruites)

- **Auteur** : `theo-archi`. **Date** : 2026-09-08.
- **Arbre mesure** : worktree `s32-theo2`, branche `s32-theo2` partie de `round-two` a `7ed471a7`
  (l'arbre fusionne de s32, celui que `sofia-sm` a laisse rouge). **JDK 21.0.10**, version que la
  machine virtuelle des tests rapporte d'elle-meme et non celle qu'une entree de toolchain declare
  (`ADR-0071`). Enveloppe : `bash outils/mvn-jdk21.sh`.
- **Ce n'est pas un Done.** La porte pedagogique appartient a `nadia-prof` seule.

---

## 1. Les quatre jobs de `.github/workflows/ci.yml`, un par un

`mvn verify` n'est que le contenu du job `build` (`CLAUDE.md`, amendement du 2026-09-08). Les quatre
jobs sont lances sur ce diff, avec les commandes du workflow.

| Job | Commande lancee | Verdict |
|---|---|---|
| `build` - Build et tests (Java 21) | `bash outils/mvn-jdk21.sh -B clean verify` (reacteur complet) | **vert.** `BUILD SUCCESS`, six modules `SUCCESS`, 02:05 min |
| `chemins` - Chemins de poste de travail | `bash .github/scripts/verifier-chemins-test.sh`, puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | **vert.** Tous les cas du garde-fou passent ; *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies"* |
| `outils` - Juges purs des outils de poste | la boucle du workflow sur `outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py` | **vert.** *"9 juge(s) pur(s) execute(s)"*, aucun motif vide |
| `prefixes` - Prefixes de commit par agent | `bash .github/scripts/verifier-prefixes-test.sh`, `bash .github/scripts/commit-atteignable-test.sh`, puis `git log --no-merges --format=%s round-two..HEAD \| bash .github/scripts/verifier-prefixes.sh` | **vert.** Tous les cas des deux garde-fous passent ; *"Tous les commits portent un prefixe d'agent connu"*. **La plage lancee est `round-two..HEAD`** et non le `-1` du repli du workflow : le seul commit de cette branche porte `[THEO]`, et la plage complete le verifie plutot que de le supposer |

### Le compte de tests, avec sa methode dans le meme paragraphe

**Methode** : somme de l'attribut `tests` des fichiers `TEST-*.xml` produits par le
`mvn clean verify` ci-dessus, dans les repertoires `surefire-reports` et `failsafe-reports` de chaque
module du reacteur. Un seul worktree est mesure, le mien ; aucun arbre de contre-exemple ne porte de
rapport.

| Perimetre | Fichiers | Tests |
|---|---|---|
| `recreo-domain` | | 105 |
| `recreo-application` | | 13 |
| `recreo-adapter-web` | | 388 |
| `recreo-adapter-content` | 93 | 689 |
| `recreo-bootstrap`, surefire | 39 | **238** |
| `recreo-bootstrap`, failsafe | 4 | 6 |
| **Total** | **193** | **1 439** |

**Les 238 de `recreo-bootstrap` en surefire se raccordent exactement a la mesure de `sofia-sm`** :
231 tests, 2 echecs sur l'arbre fusionne, plus les 7 tests du garde-fou neuf de `LON-1`/`LON-2`. Les
deux echecs ont disparu, et aucun test n'a ete supprime pour les faire disparaitre - le fichier
d'`ADR-0086` en porte toujours 9.

---

## 2. Le rouge herite est paye, et il n'a pas ete rendu vert par retrait

`LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` : 9 tests, 0 echec. Le detail de ce qui a
ete retire, de ce qui l'a remplace, et de la population qui reste sous la clause 3 est dans
`ADR-0086`, section *Amendement du 2026-09-08 (s32)*. Les trois faits qui comptent ici :

1. **L'exemption est retiree, pas deplacee** : la clause 3 n'exempte plus aucun paquet.
2. **La clause garde encore une population** : 65 paquets a propositions, 1 227 items, **1 111 a deux
   distracteurs - sa population -, 0 paquet fautif, 0 item marque**. Cliquet a plafond zero, pas
   regle a population vide (`ADR-0051`).
3. **La morsure a change de materiau, et c'etait le vrai cout du retrait** : elle s'executait sur le
   contenu vivant prive de son exemption, donc le paiement de `FAB-1c` l'a rendue rouge **sans
   qu'elle ait rien detecte**. L'etat refuse est fige octet pour octet sous `etat-refuse-emc-cp-07` ;
   la clause y nomme toujours les items 7, 8, 9 et 10.

**Une dette de meme forme reste ouverte, et son declencheur est ecrit dans le test** : la morsure de
la clause 2 s'execute encore sur le contenu vivant prive de son exemption, `FOP-R4` etant ouverte et
non payee. Le geste attendu dans le meme diff que son paiement est ecrit dans la Javadoc de
`lePaquetQuiPorteLaReserveFaitRougirLaClauseDeuxSansSonExemption`.

---

## 3. `LON-1` et `LON-2` sont executees

`ADR-0089`, et
`bootstrap/src/test/java/com/recreo/archi/AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.java`
(7 tests, dont 3 morsures). Ce qui doit etre lu par `nadia-prof` plutot que deduit :

- **La regle borne le CP seulement, et l'enonce seulement.** Les trois debordements qu'elle a
  refuses sont chiffres : CM1 **165** enonces au-dela de 15 mots et **35** au-dela de deux phrases ;
  indice de CP **48** ; propositions de CP **0**. Le troisieme est le cas dangereux, parce que
  l'extension y serait gratuite donc invisible.
- **`LON-1` porte un cliquet a egalite sur trois paquets et cinq enonces** - `emotions-autrui` 1,
  `exclusion` 1, `formules-de-politesse` 3 -, qui sont les trois paquets portant ses reserves de
  longueur ouvertes. La dette **enregistre** ces reserves, elle n'en cree aucune. Un paiement partiel
  est rouge.
- **`LON-2` est a plafond zero sur 528 enonces**, dont 82 a deux phrases.
- **Trois de ses comptes de la section 16.5 sont dementis par la mesure**, tous dans le meme sens
  (une reserve nomme un item de moins qu'il n'y en a). Le detail est dans `ADR-0089`, chapitre 4 ;
  **aucune de ses trois reserves n'est modifiee**, et le cliquet porte le compte reel, pas le compte
  annonce.

---

## 4. Les deux reserves rendues a la dette pour motif technique - instruction

### `EXC-R1` - la consigne du catalogue nomme encore l'ancienne tache

**Le blast radius annonce par `lea-dev` est exact, et je l'ai mesure.** Le changement de consigne de
`bandit-manchot-exclusion-cp` dans `catalogue.yaml` rend rouges **4 tests, dans un seul fichier** :
`bootstrap/src/test/java/com/recreo/bootstrap/catalogue/TexteAccentueExposeParLePortEntrantTest.java`.
Mutation non commitee, avec une consigne provisoire accentuee, revertie.

**Aucun autre garde-fou du reacteur ne bouge sur ce motif**, et la methode est due parce que
l'execution en portait un second : ce `mvn clean test` comptait **cinq** echecs, dont un de
`ToutesLesReferencesDeDecisionResolventTest` **imputable a mon propre diff** - mon garde-fou neuf
citait `ADR-0089` avant que le fichier existe. Le blast radius d'`EXC-R1` est donc de **4**, pas de
5, et le cinquieme a disparu quand `ADR-0089` a ete ecrit.

**Verdict : ce n'est pas un obstacle technique.** La mise a jour de cet inventaire est le geste que
`marc-dev` a fait a `S32-1` et que `sofia-sm` a fait a `S32-5`, chacun pour ses propres ajouts, dans
ce meme sprint. Le message d'echec du garde-fou prescrit lui-meme le geste : *"soit `nadia-prof` l'a
arbitree et l'inventaire s'etend dans le meme commit, soit cette chaine n'aurait pas du entrer."*

**Et ce n'est pas mon geste, pour un motif qui n'est pas technique** : l'inventaire ne peut pas etre
mis a jour avant que la consigne existe, et une consigne est un texte lu par l'enfant. **Ce qui
manque a `EXC-R1` est le texte, pas le deblocage.** Rendue a `camille-po` via `sofia-sm` comme story
de contenu ordinaire, avec le constat qu'elle ne coute aucun travail d'architecture.

### `EXC-R5` - aucun item ne fait d'un adulte reel une reponse fausse

**Ce n'est pas la tache qui doit changer, c'est le test qui classait mal.** `PaquetCpEmcExclusionTest`
lisait l'identite d'une tache dans une sous-chaine de l'enonce, `item.enonce().contains("À qui")` -
**c'est-a-dire dans la phrase exacte que la reserve demande de reecrire**. La reecriture faisait
basculer les cinq items de la tache B dans la tache A et faisait echouer `FER-3` sur `adulte > 0`.

**Paye ici** (`ADR-0089`, decision E) : la tache se lit dans la bonne reponse, et le partage des deux
taches est rendu **exhaustif et verifie**, pour qu'aucun item ne tombe dans un troisieme sac muet -
le faux vert par ecartement silencieux qu'`ADR-0088` vient de payer sur cinq garde-fous de rendu.

**Preuve que le deblocage debloque, mesuree** : les cinq enonces de la tache B reecrits selon la
formule que `nadia-prof` propose (`À qui X doit-il en parler` devient `Quel adulte est là`), les cinq
occurrences de `À qui` disparues, `PaquetCpEmcExclusionTest` **vert**. Mutation non commitee,
revertie.

**Rien de pedagogique n'est tranche.** Que la question doive nommer l'adulte present, et comment,
reste a `nadia-prof` ; ce qui est leve est le seul obstacle technique.

**Dette generale ouverte avec son chiffre** : **25 fichiers sur 95** tests de paquet lisent l'enonce
par une operation de chaine - majorant, plusieurs valident legitimement la *forme* de l'enonce -, et
la regle generale demanderait un champ de tache au schema d'item. Elle n'est pas prise ici ; son
declencheur est ecrit dans `ADR-0089`.

---

## 5. Ce que ce lot ne fait pas

Aucun item, aucun paquet de contenu, aucune condition pedagogique ecrite. Aucun Done prononce. Aucun
statut de `squad/programme/MATRICE-couverture.md` deplace. Le chemin de jeu du palier 2 (`L-a` a
`L-e`) reste au backlog de s33. **Cette branche n'est pas fusionnee : `sofia-sm` fusionne.**
