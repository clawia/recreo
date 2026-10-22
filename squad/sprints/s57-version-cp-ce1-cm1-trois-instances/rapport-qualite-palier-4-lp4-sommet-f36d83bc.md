# Rapport qualite du palier 4, lot LP4 au sommet f36d83bc

Auteur : `yanis-qa`, transverse. Date : 2026-10-22. Branche du lot : `s57-sofia-emc-ce1-02`, sommet `f36d83bc`, base `eb267ba7`. Ce rapport remplace `rapport-qualite-palier-4-lp4-sommet-41203188.md` pour LP4. Mesures faites dans mon worktree `s57-yanis-p4` mis sur le sommet, propre avant chaque mesure. Le worktree du lot n'a pas ete touche.

**Ce rapport ne prononce pas le Done pedagogique** (`nadia-prof`, seule).

## 1. Resultat

1. `bash outils/mvn-jdk21.sh -B clean verify` : **exit 0, 2940 tests, 0 echec, 0 erreur, 0 ignore** (+7 contre 2933). Les 85 tests de `PaquetCe1EmcRegleSanctionTest` passent.
2. **Le garde de sanction est restaure** : "Recopier le livre" rougit (S1, C7b). **Le garde de la cible faute est ancre en debut de mot** : "Opier sur Zoe" rougit (F5). Mais "Copier le dessin" (C8b) et "Refaire l'exercice" (C8c) **passent encore** (section 3).
3. Gardes neufs : negation (3 mutations), "expres" (4 sur 5), effacer/gommer (2 sur 4) mordent. Trois trous lexicaux et un trou de forme restent (section 4).
4. Les trois voisines relevees ("devant ses amis", "le maitre le pousse", "sans souper") **passent encore**.
5. **Aucune garantie perdue depuis `41203188`** sur ce que j'ai rejoue (section 5).
6. Porte technique de LP4 au sommet `f36d83bc` : verte sur tout ce que j'ai lance (section 6).

## 2. Job `build`

Commande : `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl`, sous le jeton de `verify` (RF1, `mkdir` au premier essai, supprime a la fin). Exit 0, `BUILD SUCCESS`, 456 s.

| Module | Tests |
|---|---|
| domain | 120 |
| application | 31 |
| adapter-web | 523 |
| adapter-content | 1879 |
| bootstrap (381) et integration Failsafe (6) | 387 |
| **Total** | **2940** |

Methode : attribut `tests` des 320 `TEST-*.xml`, par module, JDK 21.0.10 (propriete `java.version` des 320 rapports), 0 echec, erreur ou ignore. Delta : **+7 contre `41203188`, tout dans adapter-content** (1872 a 1879), perimetre : reacteur complet. Il s'accorde avec l'annonce de `sofia-sm` (2940, +7).

## 3. Mutations (sommet `f36d83bc`)

**Methode.** Une mutation du paquet `ce1-emc-regle-sanction.yaml` (donnees reelles), puis
`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am -Dtest=PaquetCe1EmcRegleSanctionTest -Dsurefire.failIfNoSpecifiedTests=false test`
(perimetre : classe seule, 85 tests, `-am` present). Verdict lu dans le XML Surefire, mutation annulee par `git checkout -- <fichier>`, `git status --short` vide apres chacune (verifie sur les 52 executions). Quand une mutation allonge un enonce au-dela de 112 caracteres, le garde de pli rougit en plus : je ne lis alors que le garde vise, et je le note "(pli seul rouge)" quand le garde vise reste vert.

| Id | Garde | Mutation | Resultat |
|---|---|---|---|
| S1 | sanction, mot entier | question "Recopier le livre" (geste "recoller") | **rouge**, motif : "le geste de l'adulte, mot entier" (vert a `41203188`, C7b) |
| S4 | sanction, debut de mot | question "Asser le papier" (fragment d'un mot du geste) | rouge, meme motif |
| S3 | sanction | question "Ramasse le papier" (forme conjuguee, geste "ramasser") | vert, comme voulu |
| **S2** | sanction | question "La maitresse, c'est quoi ?" (premier mot "la" present dans la phrase de l'adulte) | **le garde de la question reste vert** (pli seul rouge) |
| F4 | faute, racine | question "Recopier sur Zoe" (racine `rec` absente) | rouge, motif : "ne reprend pas l'acte" |
| F5 | faute, ancrage | question "Opier sur Zoe" (`opi` au milieu de "copie") | **rouge**, motif : "ne reprend pas l'acte" (ancrage effectif) |
| **C8b** | faute | question "Copier le dessin" (meme verbe, autre objet) | **le garde reste vert** (pli seul rouge) |
| **C8c** | faute | question "Refaire l'exercice" (designe la **sanction** : la bonne reponse serait "la sanction") | **le garde reste vert** (pli seul rouge) : "refaire" est dans la phrase de l'adulte, que le garde lit aussi |
| **F7** | faute | question "Refus sur Zoe" (premiere racine `ref`, sans rapport) | **vert, 85 sur 85** |
| F6, NG2 | negation | "Ne pas rendre" devient "Rendre" : les questions negatives ne portent plus que la cible regle | rouge, motif : "la negation designe la regle" |
| NG1 | negation | items 4 et 7 en question affirmative : seule la cible faute reste negative | rouge, meme motif |
| X1 | "expres" | "Jade a tache expres" devient "Jade a tache" | rouge, motif : "peut se lire comme un accident" |
| X2 | "expres" | "Enzo a mouille expres" devient "Enzo a mouille" | rouge, meme motif |
| X3 | "expres" | "Hugo a renverse la tour d'Aya" sans "expres" | rouge, meme motif |
| X4 | "expres" | "renverse sans demander" | garde vert (pli seul rouge), comme voulu : "sans demander" est accepte |
| X6 | "expres" | "renverse expres" | garde vert (pli seul rouge), comme voulu |
| **X5** | "expres" | "Hugo a fait tomber la tour d'Aya" (accident lisible, verbe hors liste) | **vert, 85 sur 85** |
| E1 | effacer/gommer | "Zoe a efface son calcul" | rouge, motif : "geste ordinaire d'ecolier" |
| E2 | effacer/gommer | "Zoe a gomme son trait" | rouge, meme motif |
| **E3** | effacer/gommer | "Zoe gommait son trait" (imparfait) | **vert, 85 sur 85** |
| **E4** | effacer/gommer | "Zoe effacait son trait" (cedille) | **vert, 85 sur 85** |
| E5 | effacer/gommer | "le maitre lui fait effacer le livre" (sanction, pas faute) | rouge : le garde lit **tout l'enonce**, il refuse aussi "effacer" dans la phrase de l'adulte (un item de sanction legitime, "effacer", serait refuse) |
| G2d, G2e, G2f | refus d'avance, les trois voisines | "devant ses amis", "le maitre le pousse", "sans souper" | **vertes, 85 sur 85. Le garde ne mord pas.** |
| I4 | indices du CP | "n'importe qui" | vert, 85 sur 85 |

Rejeu des mutations des gardes de `41203188` : C1 a C9, C8a, C10a a C10d, C11a, C11b, G1a a G1c, G2a a G2c, I1 a I3. **Resultats identiques** a ceux du rapport precedent (colonne rouge ou vert inchangee), sauf C7b (maintenant rouge, voir S1). Les ancres de C10 ont ete adaptees au nouveau paquet : 25 % et 75 % verts, 2 et 10 negatives sur 12 rouges.

## 4. Ce qui ne mord toujours pas

1. **Question de cible faute, deux formes** (C8b, C8c, F7) : le garde cherche trois lettres, en debut de mot, dans tout l'enonce hors la question, **phrase de l'adulte comprise**. Une question qui designe la sanction (C8c) ou un autre acte du meme verbe (C8b) passe, ainsi qu'une racine sans rapport qui se trouve ailleurs dans l'enonce (F7).
2. **Question de cible sanction, mot de liaison** (S2) : le premier mot de la question est cherche en entier dans la phrase de l'adulte ; "La maitresse, c'est quoi ?" passe parce que "la" y figure. Le garde est entier, pas exact.
3. **"Expres"** (X5) : la liste des verbes lisibles en accident est de trois (renverser, mouiller, tacher) ; "faire tomber" passe.
4. **Effacer/gommer** (E3, E4) : le motif est lexical ; l'imparfait de "gommer" et "effacait" (cedille) passent. Le garde est en outre **trop large** (E5) : il rejette "effacer" dans la phrase de l'adulte d'un item de sanction.
5. **Refus d'avance, voisines** (G2d a G2f) et **indices du CP, "n'importe qui"** (I4) : les listes lexicales ne ferment pas les voisines d'un mot.

## 5. Une garantie a-t-elle ete perdue depuis `41203188` ?

**Non, sur ce que j'ai rejoue.** Le garde de sanction est plus fort qu'a `41203188` (mot entier, ancre : S1, S4) et egal a celui de `1152fdc9` (mot entier) en ajoutant l'ancrage. Le garde de faute est plus fort (ancre : F5). Les gardes de la tache 1 (chevrons, forme de la question, regle citee, melange, pli) rougissent aux memes mutations (C1 a C11b). Les trois gardes neufs mordent sur leur cas nominal (F6/NG1/NG2, X1 a X3, E1, E2). Ce que je n'ai **pas** rejoue : les mutations M01 a M40 du premier rapport et les gardes de la tache 2 hors G2, I et pli.

Reste ouvert, comme a `41203188` : la question de faute n'est pas verifiee comme designant la faute (C8c), et le premier mot d'une question de sanction peut etre un mot de liaison (S2).

## 6. Jobs de `.github/workflows/ci.yml` lances, sommet `f36d83bc`

| Job | Etapes | Resultat |
|---|---|---|
| `build` | "Verify" : `bash outils/mvn-jdk21.sh -B clean verify` | exit 0 (section 2) |
| `build` | "Package du fat jar" : `bash outils/mvn-jdk21.sh -B clean -pl bootstrap -am package`, sous le jeton | exit 0, 255 s, `bootstrap/target/recreo.jar` present, 2934 tests rejoues, 0 echec (perimetre : bootstrap et ses dependances, sans les 6 d'integration) |
| `build` | "Verifier la presence du build Maven" | non lancee (teste `pom.xml`) |
| `prefixes` | tester prefixes, tester plage, verifier prefixes sur `eb267ba7..f36d83bc` (sans fusions), tester le controle de l'accent, verifier le proprietaire de l'accent | les 5 lancees, exit 0 |
| `chemins` | tester le garde-fou, verifier l'absence de chemins sur `git ls-files` | les 2 lancees, exit 0 |
| `outils` | balayage des trois motifs de juges purs | lance, exit 0, 24 juges, 0 echec, aucun motif vide (interpreteur Python du poste) |

Ecarts a la CI : enveloppe et interpreteur du poste, `clean` ajoute, plage de `prefixes` celle du lot. **Porte technique de LP4 au sommet `f36d83bc` : verte sur tout ce que j'ai lance**, soit tous les jobs sauf l'etape triviale "Verifier la presence du build Maven". Elle vaut pour ce sommet, pas pour la fusion.

## 7. Limites

- Une execution de chaque `verify`, sous Windows, sans rejeu pour juger la stabilite. Aucun test instable observe. Couverture chiffree non mesuree.
- Les mutations couvrent les gardes nommes ; elles ne prouvent pas l'absence d'autres angles morts. Les listes lexicales ont des voisines non couvertes.
- Done pedagogique : non prononce ici, a `nadia-prof`.
