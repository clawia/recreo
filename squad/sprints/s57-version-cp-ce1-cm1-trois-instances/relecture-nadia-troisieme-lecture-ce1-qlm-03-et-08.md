# s57 - troisieme lecture de `-03` et `-08` : C1 a C4 et l'item 6 appliques (`S57-BR-QLM-CE1-03`, `S57-BR-QLM-CE1-08`, `P5`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-27, horloge du poste lue par `date` (UTC+01:00) ; durees en section 5.
- **Arbre** : branche `s57-p5-nadia-lecture3`, base `round-two` a `90c421a7`, lue par `git`.
- **Forme** : troisieme lecture classee par l'utilisatrice (A.281 point 2), item 6 de `-03` compris (A.282 point 3), lue
  apres la transcription de l'item 6 (commit `e02dcd86`, 21:00:33), donc dans la forme d'A.282 point 3 et non une
  quatrieme lecture. Je dis, par lot, les lignes relues, si un verdict d'item est rouvert, si une transcription est
  rejetee ou reformulee, et si j'ai relu le paquet entier (A.272, section *Le fichier de relecture* ; A.277 b et d ;
  A.279 point 5 ; A.281 point 4).
- **Lu** : `DEC-0120` A.272, A.273 point 2, A.277, A.279, A.281, A.282 ; mon fichier
  `correction-nadia-amorces-ce1-qlm-03-et-08.md` en entier ; les deux YAML en entier ;
  `git diff -U0 09344f1e HEAD` sur les deux YAML (09344f1e : commit de ma correction) ; les tableaux de verdict de mes
  deux secondes lectures (`relecture-nadia-corrections-ce1-qlm-besoins-de-la-plante.md`,
  `relecture-nadia-corrections-ce1-qlm-dangers-electricite.md`).
- **Ne fait pas** : aucun Done, aucun verdict de lot, aucune correction de YAML, aucun identifiant neuf. Rien n'est joue a
  l'ecran : c'est l'audit a l'ecran, plus tard. Les items sont joues sur le papier.

## Resume

| Point | Reponse |
|---|---|
| Transcription | **les cinq textes sont identiques octet par octet** a mon fichier de correction, a la bonne place (section 1) |
| Perimetre du diff | **cinq lignes changent, les cinq prescrites**, et rien d'autre dans les deux YAML (section 1) |
| Verdict de ma seconde lecture et de ma correction sur ces items | **tient, pour les cinq items** (section 2) |
| Transcription rejetee ou reformulee | **aucune** |
| Verdict d'item rouvert | **aucun** |
| Autocontrole A.273 a, b, c | **tient** sur les cinq items (section 2) |
| Correction de mon propre constat | une : *4 mots chacun* est faux pour C4 (section 3) |
| Reserve nouvelle, datee | une, sur *plus* dans `-03`, gardee par moi a l'audit a l'ecran, sans correction demandee (section 3) |
| Paquet entier relu | **non** au sens d'A.277 d (section 6) |

## 1. Transcription, ligne par ligne

Comparaison par script (Python, hors du depot, non commite) : le texte entre accents graves de mon fichier de correction,
encode en UTF-8, est cherche entre guillemets dans la ligne du YAML. Chaque texte a une seule occurrence dans mon
fichier. Les deux YAML sont en CRLF ; aucun caractere combinant ; les accents sont precomposes (`c3 a8`, `c3 a9`).

| Lot | Item | Ligne | Position | Texte de mon fichier | Texte lu dans le YAML | Octets | Verdict |
|---|---|---|---|---|---|---|---|
| `-03` | 2, plante privee d'eau | `:73` | 1er distracteur | `elle reste bien droite` (C1) | `"elle reste bien droite"` | identiques, ASCII | **tient** |
| `-03` | 6, plante dans le noir | `:105` | 2e distracteur | `elle pousse très bien` (section 5) | `"elle pousse très bien"` | identiques, `è` = `c3 a8` | **tient** |
| `-03` | 12, plante privee de chaleur | `:153` | 1er distracteur | `elle continue de pousser` (C2) | `"elle continue de pousser"` | identiques, ASCII | **tient** |
| `-08` | 2, Tom | `:71` | 2e distracteur | `ne pas en parler` (C3) | `"ne pas en parler"` | identiques, ASCII | **tient** |
| `-08` | 5, Lea | `:92` | 2e distracteur | `ne pas déranger un adulte` (C4) | `"ne pas déranger un adulte"` | identiques, `é` = `c3 a9` | **tient** |

`git diff -U0 09344f1e HEAD` sur les deux YAML : cinq hunks d'une ligne, `:73`, `:105`, `:153` (`-03`) et `:71`, `:92`
(`-08`), `+5 -5`, aucun autre changement. Commits : `3166dd40` (C1 a C4), `e02dcd86` (item 6). L'autre distracteur de
chaque ligne est inchange.

## 2. Chaque item relu en entier

Enonce, bonne reponse, deux distracteurs, indice et declaration de familles. Les longueurs sont en caracteres
(`String.length`) et en mots, par script.

| Lot | Item | Ce que je relis | Verdict |
|---|---|---|---|
| `-03` | 2 | *Une plante n'a plus d'eau.* Bonne reponse *elle se fane* (12) ; *elle reste bien droite* (22), *elle devient plus verte* (23). Famille `b, b` inchangee. Un seul des deux distracteurs porte *plus* : le seul bouton sans *plus* n'est plus la bonne reponse. *Reste bien droite* est le contraire de *se fane* : il ne s'ecarte qu'en sachant que la plante a besoin d'eau. La bonne reponse n'est pas la plus longue. L'indice (*pendant longtemps*) n'appuie que la bonne reponse | **tient** |
| `-03` | 6 | *Une plante a des feuilles et reste dans le noir.* Bonne reponse *elle devient pâle et pousse mal* (31) ; *elle devient plus verte* (23), *elle pousse très bien* (21). Famille `b, b` inchangee. *Devient* et *pousse* sont chacun dans un distracteur : aucun verbe ne trie. Un seul distracteur porte *plus*. La bonne reponse etait deja strictement la plus longue avant (contre 24) : le compte de `TRV-CE1-2` ne change pas. *Pousse très bien* ne s'ecarte qu'en sachant que la plante a besoin de lumiere | **tient** |
| `-03` | 12 | *Une plante manque de chaleur, car il fait très froid.* Bonne reponse *elle arrête de pousser* (22) ; *elle continue de pousser* (24), *elle fait plus de fleurs* (24). Famille `b, b` inchangee. Meme moule que la bonne reponse, le verbe seul differe : choisir entre *arrête* et *continue* est la competence. *Pousser* est dans les deux. La bonne reponse n'est pas la plus longue | **tient** |
| `-08` | 2, Tom | *fil électrique abîmé dans la cuisine.* Bonne reponse *ne pas toucher le fil* (21) ; *réparer le fil avec du scotch* (29), *ne pas en parler* (16). Famille `a, c` inchangee. *Ne pas* est maintenant partage par la bonne reponse et le refus : le raccourci du *ne* (ma lecture du premier jet, section 3.2) ne s'aggrave pas, il ne trie pas plus qu'avant. *Adulte* n'y est pas | **tient** |
| `-08` | 5, Lea | *grille-pain branché au bord de l'évier.* Bonne reponse *ne pas toucher l'appareil* (25) ; *le débrancher en tirant le fil* (30), *ne pas déranger un adulte* (25). Famille `a, c` inchangee. Aucun adulte n'est occupe dans l'enonce : *ne pas deranger* se lit *ne pas signaler*. Commence par *ne*, nomme l'adulte : passe `T2`. Bonne reponse pas strictement la plus longue | **tient** |

**Autocontrole d'A.273 point 2, sur les cinq lignes.**

- **a, indice qui donne la reponse** : aucun indice n'a change ; aucun texte neuf ne reprend un mot de l'indice de son
  item. **Tient.**
- **b, distracteur ecartable sans la competence** : aucun des cinq textes neufs ne s'ecarte par la taille, le lieu ou
  parce qu'il est impossible pour son sujet ; chacun est le contraire de la consequence (`-03`) ou un refus de signaler
  (`-08`), dans sa famille declaree. **Tient**, avec la reserve nouvelle de la section 3, qui ne porte pas sur un de ces
  trois motifs.
- **c, source circulaire** : aucune ligne de commentaire ni de source n'est dans le diff. **Sans objet, tient.**

**Repartition des refus de `-08` apres C3 et C4**, par script : *ne pas déranger un adulte* 2 (Sami, Lea), *ne pas en
parler* 2 (Tom, Noe), *ne rien dire à un adulte* 1 (Ines), *ne rien dire à personne* 1 (Hugo). C'est celle de ma
section 3. Les propositions qui contiennent *adulte* restent 3, toutes en distracteur, comme avant C4.

## 3. Ce que je corrige et ce que je vois de plus

**Correction de mon propre constat.** Mon fichier de correction, section 4, ligne `LON-CE1-5`, ecrit *"4 mots chacun"*.
C'est faux pour C4 : *ne pas déranger un adulte* fait 5 mots. `LON-CE1-5` borne a six mots au plus
(`ARBITRAGE-PREALABLE-s48-francais-ce1.md`), C4 la tient. Le constat est inexact, la conclusion ne change pas. Je laisse
mon fichier tel qu'il est et je corrige ici.

**Reserve nouvelle, datee du 2026-10-27, `-03`, mot *plus*.** Ce n'est pas un durcissement de ma barre : la barre que
j'avais ecrite (section 5 de ma correction) etait *"le seul bouton sans plus"*, et elle est atteinte, aucune bonne
reponse de `-03` n'est plus le seul bouton sans *plus*. Ce que je vois apres application, par script : dans chacun des
cinq items de la tache privation (2, 6, 9, 12, 14), **exactement un distracteur porte *plus*** (*plus verte* deux fois,
*germe plus vite*, *fait plus de fleurs*, *pousse plus vite*), et aucune bonne reponse du paquet ne le porte. Un enfant
qui a remarque, partie apres partie, que *plus* n'est jamais juste peut ecarter ce distracteur sans la competence ; il
reste un choix entre la bonne reponse et son contraire, qui, lui, demande la competence. C'est l'effet que le defaut b
d'A.273 decrit (*"fait de l'item un choix entre deux"*), par un signe de forme appris d'un item a l'autre et non par la
taille, le lieu ou l'impossible. Il existait avant la correction sous une forme plus forte (8 distracteurs longs sur 10
portaient *plus*). **Je ne demande aucune correction** : elle toucherait cinq lignes, dont deux d'items que je n'ai pas
rouverts (9, 14), et serait une quatrieme lecture, non chiffree, a decider par l'utilisatrice. **Je reste seule a garder
cette propriete** : le garde-fou de la clause 3 ne compte pas *plus* (ma correction, section 3), et je la regarderai a
l'ecran a l'audit, en jouant plusieurs parties de suite. C'est la qu'elle devient un motif ou non.

**Ecart *cinq* ou *six* lignes (A.282 point 3), non tranche.** Ce que j'ai relu : cinq lignes transcrites, `:73`, `:105`,
`:153`, `:71`, `:92`, chacune dans son item entier, et le diff en montre cinq. Ma phrase *"quatre lignes (six avec
l'item 6)"* est dans mon fichier de correction, section 6 ; je ne la reclasse pas ici.

## 4. Mesures lancees

- Comparaison octet par octet, longueurs, comptes de *plus* et de refus : script Python hors du depot, non commite.
- `git diff -U0 09344f1e HEAD` sur les deux YAML, lu en section 1.
- Garde-fou des tableaux, sur cet arbre et ce fichier, verdict en section 7.
- Je n'ai lance ni `mvn verify` ni la porte technique. Le `verify` complet vert apres C1 a C4 (3618 tests, A.281 point 1)
  est un fait relaye, que je n'ai pas relance ; celui qui suit l'item 6 n'est pas mesure par moi.

## 5. Duree de chaque lecture, mesuree a l'horloge

Donnees brutes, horloge du poste lue par `date` au debut et a la fin de chaque fenetre. Aucune conversion en bareme
(A.276).

| Fenetre | Debut | Fin | Duree |
|---|---|---|---|
| `-03` : lecture du YAML entier et des items 2, 6, 12 | 21:33:56 | 21:35:10 | 1 min 14 s |
| `-08` : lecture du YAML entier et des items 2 et 5, puis diff et comparaison octet par octet des deux lots | 21:35:10 | 21:36:34 | 1 min 24 s |
| verifications transverses des deux lots (comptes de *plus*, longueurs, `LON-CE1-5`) | 21:36:34 | 21:38:59 | 2 min 25 s |

Hors fenetres : la lecture de `DEC-0120` et de mon fichier de correction, avant 21:33:56, et la redaction de ce fichier,
apres 21:38:59, ne sont pas mesurees.

## 6. Ce que je n'ai pas relu

- **Le paquet entier, au sens d'A.277 condition d : non, pour les deux lots.** Les deux fichiers sont passes en entier
  sous mes yeux (170 et 142 lignes), mais je n'ai juge que les cinq items de la section 2. Aucun autre item n'est
  rejuge, aucun verdict n'est rendu sur eux. Les comptes transverses (*plus*, refus, longueurs) portent sur tout le
  paquet, par script, et ne sont pas un jugement d'item.
- Les tests du paquet (`PaquetCe1QlmBesoinsDeLaPlanteTest`, table `EFFETS_CONTRAIRES`) et le diff de
  `PaquetCe1QlmEauTroisEtatsTest`, qui figure dans `git diff --stat 09344f1e HEAD` : non relus, hors de mes lignes.
- Les commentaires des deux YAML : non relus ligne a ligne, aucun n'est dans le diff.

## 7. Garde-fou des tableaux (`ADR-0087`)

`bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest,ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest -Dsurefire.failIfNoSpecifiedTests=false`,
perimetre : ces deux tests, module `bootstrap` et ses dependances, sur cet arbre.

Sortie lue : `Tests run: 5, Failures: 0, Errors: 0, Skipped: 0` pour `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest`, `BUILD SUCCESS`. Lance dans la meme commande, avec ce fichier ecrit : `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, `Tests run: 10, Failures: 0, Errors: 0, Skipped: 0` ; ce fichier n'ecrit aucun identifiant neuf. Ce n'est ni un `verify`, ni la porte technique.

## 8. Routage

| A qui | Quoi |
|---|---|
| `camille-po` | classer cette lecture ; savoir que la reserve nouvelle sur *plus* ne demande aucune correction et ne rouvre aucun item |
| `sofia-sm` | rien de neuf pour le registre de ma part ; le `verify` apres l'item 6 n'est pas mesure par moi |
| `theo-archi` | rien : la clause 3 qui ne compte pas *plus* est dite dans ma correction, section 7, et je ne demande toujours pas de l'etendre |
