# Rapport qualite du palier 4, lot LP4 au sommet 41203188, et LP1 au sommet f8d5d875

Auteur : `yanis-qa`, transverse. Date : 2026-10-22. Branche du lot : `s57-sofia-emc-ce1-02`, sommet `41203188` (tache 1 en forme (a) de `nadia-prof`), base `eb267ba7`. Ce rapport remplace `rapport-qualite-palier-4-lp4-sommet-1152fdc9.md` pour LP4. Mesures faites dans mon worktree `s57-yanis-p4`, mis sur chaque sommet nomme, propre avant chaque mesure. Les worktrees des lots n'ont pas ete touches.

**Ce rapport ne prononce pas le Done pedagogique** (`nadia-prof`, seule).

## 1. Resultat

1. LP4 `41203188` : `bash outils/mvn-jdk21.sh -B clean verify`, **exit 0, 2933 tests, 0 echec, 0 erreur, 0 ignore** (+11 contre 2922). Les 78 tests de `PaquetCe1EmcRegleSanctionTest` passent.
2. Les quatre trous du rapport precedent : **G1 `poser` ferme** (G1a, G1c rouges) mais `repos` passe (G1b) ; **G2 : les trois sondes sont fermees**, trois sondes voisines passent (G2d a G2f) ; **indices du CP : "chaque enfant" et "Chacune" fermes**, "n'importe qui" passe (I4) ; **question de la cible faute : gardee, mais sur trois lettres** (C8a rouge, C8b et C8c verts). Les gardes neufs de la tache 1 (chevrons, forme, regle citee, melange, pli de 112) mordent sur chaque mutation vise, limites comprises.
3. **Un garde de la tache 1 est affaibli** : le garde de la question de cible sanction. Il exigeait le geste de l'adulte en entier ; il exige trois lettres (section 4).
4. Porte technique de LP4 : `build` (Verify et "Package du fat jar"), `prefixes`, `chemins` et `outils` lances, verts (section 5).
5. LP1 `f8d5d875` : `verify` complet, exit 0, **2895** (section 6).

## 2. Job `build` au sommet `41203188`

Commande : `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl`, sous le jeton de `verify` (RF1, acquis par `mkdir` au premier essai, supprime a la fin ; racine du scratchpad relue sans jeton). Exit 0, `BUILD SUCCESS`, 545 s.

| Module | Tests |
|---|---|
| domain | 120 |
| application | 31 |
| adapter-web | 523 |
| adapter-content | 1872 |
| bootstrap (381) et integration Failsafe (6) | 387 |
| **Total** | **2933** |

Methode : attribut `tests` des 320 `TEST-*.xml`, par module, JDK 21.0.10 (propriete `java.version` des 320 rapports), 0 echec, erreur ou ignore. Delta : **+11 contre `1152fdc9`, tout dans adapter-content** (1861 a 1872), perimetre : reacteur complet. Il s'accorde avec l'annonce de `sofia-sm` (2933, +11).

## 3. Mutations des gardes de `PaquetCe1EmcRegleSanctionTest` (sommet `41203188`)

**Methode.** Une mutation du paquet `ce1-emc-regle-sanction.yaml` (donnees reelles), puis
`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am -Dtest=PaquetCe1EmcRegleSanctionTest -Dsurefire.failIfNoSpecifiedTests=false test`
(perimetre : classe seule, 78 tests, `-am` present). Verdict lu dans le XML Surefire, mutation annulee par `git checkout -- <fichier>`, `git status --short` vide apres chacune. 31 executions. Quand une mutation allonge un enonce au-dela de 112 caracteres, le garde de pli rougit en plus : je ne lis alors que le garde vise.

| Id | Garde | Mutation | Resultat |
|---|---|---|---|
| C1 | chevrons dans les 12 enonces de la tache 1 | chevrons retires de l'item 1 | rouge, motif : "(tache 1) cite la regle entre chevrons" (et la forme de la question) |
| C2 | aucun chevron en tache 2 | chevrons ajoutes a un enonce de tache 2 | rouge, meme garde (et la longueur) |
| C3 | forme `<element>, c'est quoi ?` | "C'est quoi, respecter les murs ?" | rouge, motif : "n'a pas la forme" |
| C4 | idem | chevrons dans la question | rouge, meme motif |
| C5 | cible regle | la question ne reprend pas la regle citee | rouge, motif : "ne reprend pas la regle citee" |
| C6 | cible sanction | la question reprend la regle citee | rouge, motif : "reprend la regle citee alors que la cible est sanction" |
| C7 | cible sanction | question "Lire le livre" (geste absent) | rouge, motif : "ne reprend pas le geste de l'adulte" |
| C9 | cible sanction | question sur l'acte ("Dechirer le livre") | rouge, meme motif |
| C8a | cible faute | question "Lire sur Zoe" | rouge, motif : "ne reprend pas l'acte" |
| C10a | melange affirmatif/negatif | 3 regles negatives sur 12 (25 %, limite) | **vert, comme attendu** |
| C10b | idem | 2 sur 12 | rouge, motif : "ne melent pas affirmatif et negatif (2 negatives sur 12)" |
| C10c | idem | 9 sur 12 (75 %, limite) | **vert, comme attendu** |
| C10d | idem | 10 sur 12 | rouge, meme motif |
| C11a | pli, enonce de tache 1 a 112 caracteres | item 1 porte de 109 a 112 | **vert, comme attendu** |
| C11b | idem | 113 | rouge, motif : "plus de 112 caracteres, trois lignes au plus" |
| G1a | G1 ancre, `poser` | item 1 : "poser une question" a la place de "laver le mur" | rouge, motif : "n'est pas une reparation" (ferme, etait vert en N8f) |
| G1c | G1 ancre, `poser` | item 2 (sanction) : "poser une question" | rouge, motif : "n'est pas une reparation" |
| **G1b** | G1, `repos` | item 1 : "le maitre lui fait prendre du repos" | `rsa1LesSanctionsDesSituationsSontDesReparations` **vert** (le pli rougit seul) : "repos" ancre en debut de mot, accepte "repos" comme reparation |
| G2a, G2b, G2c | G2 | "devant les autres", "le maitre le tape", "sans diner" (phrase de l'adulte de l'item 13) | rouge, motif : "mot refuse d'avance" (verts a `1152fdc9`, ferme) |
| **G2d, G2e, G2f** | G2, sondes voisines | "devant ses amis", "le maitre le pousse", "sans souper" | **vertes, 78 sur 78. Le garde ne mord pas.** |
| I1, I2, I3 | indices du CP | "chaque enfant", "Chacune doit le savoir", "tous les eleves" | rouge, motif : "donne le critere du CP" (I1 et I2 etaient verts a `1152fdc9`, fermes) |
| **I4** | indices du CP, sonde voisine | "Cela vaut pour n'importe qui" | **vert, 78 sur 78** |
| **C7b** | cible sanction | question "Recopier le livre" (le geste est "recoller") | **vert** : les trois premieres lettres, `rec`, sont dans "recoller" |
| **C8b** | cible faute | question "Copier le dessin" (acte faux, meme racine `cop`) | `rsa1LaQuestionDeLaTache1ADeLaMemeFormePourLesTroisCibles` **vert** (le pli rougit seul) |
| **C8c** | cible faute | question "Refaire l'exercice" (elle designe la **sanction**, dont la bonne reponse serait "la sanction", pas "la faute") | **vert** (le pli rougit seul), racine `ref` presente dans "refaire" |

## 4. Un garde de la tache 1 a-t-il ete affaibli ?

Oui, un, et un autre a change de nature.

1. **Le garde de la question de cible sanction est affaibli.** A `1152fdc9` il exigeait que **le premier mot de la question** soit dans la phrase de l'adulte (`contains(geste)`), et mordait sur "Cela, c'est quoi" (N9a). A `41203188`, il exige les **trois premieres lettres** du premier mot de l'element (`racine`). "Recopier le livre" passe, alors que le geste est "recoller" (C7b). Le garde mord encore sur un geste dont les trois lettres sont absentes (C7, C9).
2. **Le garde de la question de cible faute est neuf, et du meme calibre.** A `1152fdc9` rien ne le gardait (N9b, vert). A `41203188` il exige trois lettres presentes dans le reste de l'enonce, ce qui ferme "Lire sur Zoe" (C8a) et laisse passer un autre acte (C8b) ou la sanction (C8c). Une question de faute qui designe la sanction est un item dont la bonne reponse est fausse : le garde ne le voit pas.
3. **"Chevrons seulement en cible regle" est remplace par le garde inverse** (chevrons dans les 12 enonces de la tache 1, aucun en tache 2) : il mord dans les deux sens (C1, C2). Ce qui distinguait la cible regle par la presence de chevrons repose maintenant sur l'**egalite de la question avec la regle citee** (C5, C6 mordent). Il n'y a pas d'affaiblissement de ce cote : le critere a ete deplace, pas retire.
4. Le melange affirmatif et negatif et le pli de 112 caracteres mordent a leur limite exacte (C10a a C11b) ; la marge du pli est de **1 caractere** (plus long enonce de tache 1 : 111).

## 5. Jobs de `.github/workflows/ci.yml` lances, sommet `41203188`

| Job | Etapes | Resultat |
|---|---|---|
| `build` | "Verify" : `bash outils/mvn-jdk21.sh -B clean verify` | exit 0 (section 2) |
| `build` | "Package du fat jar" : `bash outils/mvn-jdk21.sh -B clean -pl bootstrap -am package`, sous le jeton | exit 0, 421 s, `bootstrap/target/recreo.jar` present ; 2927 tests rejoues, 0 echec (perimetre : bootstrap et ses dependances, sans les 6 d'integration) |
| `build` | "Verifier la presence du build Maven" | non lancee (teste `pom.xml`) |
| `prefixes` | tester prefixes, tester plage, verifier prefixes sur `eb267ba7..41203188` (sans fusions), tester le controle de l'accent, verifier le proprietaire de l'accent sur la meme plage | **les 5 lancees, exit 0** |
| `chemins` | tester le garde-fou, verifier l'absence de chemins sur `git ls-files` | **les 2 lancees, exit 0** |
| `outils` | balayage des trois motifs de juges purs | **lance, exit 0, 24 juges, 0 echec, aucun motif vide** (interpreteur Python du poste) |

Ecarts a la CI : enveloppe et interpreteur du poste, `clean` ajoute, plage de `prefixes` celle du lot. **Porte technique de LP4 au sommet `41203188` : verte sur tout ce que j'ai lance**, soit tous les jobs sauf l'etape triviale "Verifier la presence du build Maven". Elle vaut pour ce sommet, pas pour la fusion.

## 6. LP1 au sommet `f8d5d875`

Commande : `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl`, sous le jeton, apres le `package` de LP4 (un seul a la fois). **Exit 0, 417 s, 2895 tests** : domain 120, application 31, adapter-web 523, adapter-content 1834, bootstrap 381 et integration 6 (387), 0 echec, erreur ou ignore, JDK 21.0.10, 320 `TEST-*.xml`. C'est le total annonce (2895, +40 contre 2855) : **egal a ma mesure de `24a9315b`**, l'item 12 modifie ne change aucun nombre de tests. Je n'ai pas mesure `eb267ba7` ; le rapprochement de la base tient sur `git diff --stat 03de0f6d eb267ba7 -- adapters domain application bootstrap pom.xml outils .github`, vide. **Mesure d'un lot isole, pas de l'arbre fusionne.** Je n'ai pas rejoue de mutations sur LP1 ni lance ses jobs `prefixes`, `chemins`, `outils` et "Package du fat jar" : la porte technique de LP1 n'est pas annoncee ici.

## 7. Limites

- Une execution de chaque `verify`, sous Windows, sans rejeu pour juger la stabilite. Aucun test instable observe. Couverture chiffree non mesuree.
- Les mutations ne couvrent que les gardes neufs et les quatre trous demandes ; elles ne prouvent pas l'absence d'autres angles morts. Les listes lexicales (G2, indices du CP) ont toujours des voisines non couvertes : D'autres sondes que les miennes peuvent passer.
- Les durees (545, 421, 417 s) sont celles de ce poste a cet instant.
- Done pedagogique : non prononce ici, a `nadia-prof`.
