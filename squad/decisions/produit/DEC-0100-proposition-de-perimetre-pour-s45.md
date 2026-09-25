# DEC-0100 - Proposition de perimetre pour s45

**Statut : Proposee (2026-09-25). Ce document propose, il n'engage rien** - ni un dossier de
sprint, ni une story, ni la matrice. Seule une validation explicite de l'utilisatrice, dans un
geste distinct de celui-ci, engagerait ce perimetre.

- **Date** : 2026-09-25
- **Auteur** : `camille-po`
- **Sprint** : s45 (candidat, non engage)
- **Prealable** : `DEC-0099` (methode de chiffrage et de verification, reprise ici sur le meme
  protocole) ; `DEC-0074`/`DEC-0077` section 8.2 (tarif : 1,0 pour une matiere dont `nadia-prof`
  est la source factuelle - mathematiques, francais -, 1,5 pour les autres) ; `DEC-0098` section 6
  (logique de marge) ; `squad/programme/MATRICE-couverture.md`, etat de sortie de s43
  (101/156, 64,7 %) ; `DEC-0099` section 10 (engagement du perimetre de s44 le 2026-09-25, qui
  retire `MUS-CP-11`, `SCI-CM1-13` et `MAT-CE1-18` du vivier disponible pour ce document)
- **Competence programme visee** : transverse - ce document porte sur l'objectif, le perimetre et
  la borne d'un sprint candidat, pas sur le contenu d'une ligne

---

## 0. Commande et methode

Aucune `DEC` n'existe pour s45. Ce document en propose une, sur le meme protocole que
`DEC-0097`/`DEC-0099` :

1. Verification independante de l'etat de la matrice apres la cloture de s43 et l'engagement de
   s44 (section 1).
2. Reprise de la borne d'audit, verifiee non revisee (section 1).
3. Reverification ligne par ligne, sans reprise de confiance, du vivier tel qu'il tenait a
   l'ecriture de `DEC-0099` : le groupe de mathematiques CE1 deja arbitre (section 2) et le vivier
   hors mathematiques (section 3) - **une correction de fait y est trouvee et signalee, pas
   corrigee en silence** (section 3.1).
4. Exclusion confirmee des quatre lignes qui restent hors de portee sans avis de faisabilite de
   `theo-archi` (section 4).
5. Chiffrage d'un perimetre propose, une ligne de mathematiques CE1 (tarif 1,0) et deux lignes du
   vivier hors mathematiques (tarif 1,5 chacune), cible 4,0/5,0, marge 1,0 (section 5).

## 1. Etat de la matrice et borne d'audit - verifies ce jour, pas repris de `DEC-0099`

**Etat de sortie de s43** : 101/156 (64,7 %), verifie contre
`squad/sprints/s43-trois-lignes-qlm-eps-et-duree-ce1/RAPPORT.md` et contre
`squad/programme/MATRICE-couverture.md` (trois lignes fermees `couvert-valide` a s43 :
`QLM-CP-08`, `EPS-CM1-10`, `MAT-CE1-12` ; aucune ouverture partielle, aucun refus a lever). Le
perimetre de s44 (`MUS-CP-11`, `SCI-CM1-13`, `MAT-CE1-18`), engage le 2026-09-25 (`DEC-0099`
section 10), n'a pas encore de Done pedagogique au moment ou j'ecris ce document : les trois
lignes restent `non couvert` dans la matrice et ne sont donc pas encore comptees au numerateur -
mais elles sont retirees du vivier disponible pour s45, puisqu'elles sont deja engagees ailleurs.

**Borne d'audit** : instance unique, 5,0 par sprint, inchangee. Meme verification que `DEC-0099`
section 1 : `ARBITRAGE-PREALABLE-s28`, section 4.1, la pose ; aucun commit sur ce fichier depuis
son ecriture ; aucune `DEC` entre `DEC-0095` et ce document ne la touche. Aucune demande de fusion
ni d'instances paralleles n'a ete recue pour s45.

## 2. Groupe de mathematiques CE1 deja arbitre - verifie ligne par ligne, pas repris de confiance

`DEC-0099` section 9.7 nomme `MAT-CE1-04` et `MAT-CE1-05` **"premiere candidate du prochain
sprint"**, a egalite, aux cotes de `MAT-CE1-07` et `MAT-CE1-08` (memes verdicts, memes plafonds).
Verification faite ce jour, pas reprise de la note de `DEC-0099` :

| Ligne | Verdict | Etat matrice | Motif de non-retenue a s43/s44 |
|---|---|---|---|
| `MAT-CE1-04` Poser et effectuer une addition avec retenue | **ACCEPTE SOUS CONDITIONS**, `ADP-CE1-1` a `ADP-CE1-6` (`ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md`, section 4) | toujours `non couvert` (`MATRICE-couverture.md`, ligne 684) | ecartee par la marge, pas par le fond (`DEC-0099` section 9.4) |
| `MAT-CE1-05` Poser et effectuer une soustraction | **ACCEPTE SOUS CONDITIONS**, `SOP-CE1-1` a `SOP-CE1-6` (meme source, section 5) | toujours `non couvert` (ligne 685) | idem |
| `MAT-CE1-07` Tables de 2, 5, 10, puis 3 et 4 | **ACCEPTE SOUS CONDITIONS** (lot 2) | toujours `non couvert` | idem |
| `MAT-CE1-08` Problemes a une ou deux etapes | **ACCEPTE SOUS CONDITIONS**, la plus riche en conditions (sept) des six lignes du groupe A (lot 2) | toujours `non couvert` | idem |

Aucun refus, aucune reserve nouvelle, aucun changement d'arbitrage depuis `DEC-0099`.

**Une seule ligne de mathematiques CE1 par sprint, comme pour s43 et s44** (`DEC-0099` section
9.4) : une marge de 0,0 sur deux lignes CE1 dans le meme sprint reproduirait exactement le risque
que `DEC-0098` attribue aux deux refus de s41. Entre `MAT-CE1-04` et `MAT-CE1-05`, `DEC-0099` ne
donne aucun motif de priorite de l'une sur l'autre - elles sont citees a egalite comme premieres
candidates. Je retiens `MAT-CE1-04` (section 5), sans motif de fond sur ce choix : `MAT-CE1-05`
reste la candidate suivante, disponible au meme tarif et aux memes conditions
(`SOP-CE1-1` a `SOP-CE1-6`) si l'utilisatrice preferait inverser l'ordre.

## 3. Vivier hors mathematiques - reverifie ligne par ligne

### 3.1 Correction de fait, trouvee en reverifiant plutot qu'en reprenant

Le point de depart transmis pour ce document associe `SCI-CM1-10` a une frontiere avec
**"`EPS-CM1-10`, deja engagee a s44"**. **C'est inexact, verifie contre le disque** :
`EPS-CM1-10` est engagee **et deja fermee** a **s43** (`couvert-valide`,
`squad/programme/MATRICE-couverture.md` ligne 1956 ; `RAPPORT.md` de s43), pas a s44. Le perimetre
de s44, engage par `DEC-0099` section 10, porte `MUS-CP-11`, `SCI-CM1-13` et `MAT-CE1-18` -
`EPS-CM1-10` n'y figure pas. Je corrige ici plutot que de reproduire l'erreur : la frontiere entre
`SCI-CM1-10` et `EPS-CM1-10` reste due si `SCI-CM1-10` est un jour engagee, mais elle porte sur une
ligne **fermee a s43**, dont la fiche de conformite est deja verifiable
(`squad/conformite/FICHE-bandit-manchot-effets-activite-eps-cm1.md`) - un avantage pour toute
instruction future de `SCI-CM1-10`, pas un obstacle : la frontiere n'est plus a fixer en meme
temps que les deux lignes s'ecrivent, elle est deja posee d'un cote.

### 3.2 Le reste du vivier, reverifie ligne par ligne contre la matrice de ce jour

| Ligne | Matiere / niveau | Etat matrice, verifie ce jour | Note reprise ou corrigee |
|---|---|---|---|
| `QLM-CP-15` Comparer des modes de vie d'autrefois et d'aujourd'hui | questionner le monde CP | `non couvert` (ligne 1608) | jouable, `BIA-3` - inchangee |
| `QLM-CP-16` Comparer des modes de vie ici et ailleurs | questionner le monde CP | `non couvert` (ligne 1609) | jouable mais **la ligne la plus a risque de biais du referentiel** (`BIA-1`, `BIA-2`) - a instruire avec un soin superieur, inchangee. Non retenue ce tour-ci pour ce motif |
| `MUS-CP-05` Identifier des instruments et leurs familles (connaissance seule) | education musicale CP | `non couvert` (ligne 1846) | jouable, mention de couverture partielle exigee d'avance - inchangee. **Complete le denominateur atteignable de la matiere** si `MUS-CP-11` (engagee a s44) ferme aussi : la mention exigee d'avance par `nadia-prof` (*"vocabulaire musical et familles d'instruments seuls [...]"*) anticipe explicitement cette paire de lignes (5 et 11) fermant ensemble |
| `EPS-CM1-09` Tenir les roles d'arbitre, d'observateur, de marqueur (connaissance seule) | EPS CM1 | `non couvert` (ligne 1955) | jouable, `EPS-1`, mention de couverture partielle exigee d'avance - inchangee. Note historique verifiee et sans effet : `DEC-0083` (Plan 2, s32-s34) projetait sa fermeture a s35, projection non tenue et non reprise ici - c'est le jugement de faisabilite du 2026-09-03, plus recent, qui gouverne |
| `EMC-CP-04` Reconnaitre que les personnes sont differentes | EMC CP | `non couvert` (ligne 1979) | jouable sous `EMC-4` **mais non retenue a s31** (`DEC-0081`) : `EMC-4` epuise par `FER-3` avant qu'un item ne soit ecrit - inchangee. Non retenue ici pour le meme motif |
| `EMC-CP-14` Connaitre les gestes d'alerte | EMC CP | `non couvert` (ligne 1989) | jouable, `EMC-7`, piege d'exactitude connu (18 vs 112 pour le numero d'urgence incendie) - inchangee |
| `EMC-CM1-01` Identifier une emotion, distinguer ressentir et agir | EMC CM1 | `non couvert` (ligne 2033) | jouable, risque de reponses doublement defendables - inchangee |
| `EMC-CM1-02` Reconnaitre une atteinte a autrui | EMC CM1 | `non couvert` | jouable mais **la plus delicate du lot** avec `EMC-CM1-06` (exclue, `DEC-0093` section 6) - inchangee |
| `EMC-CM1-03` Ce qu'interdit le respect des differences | EMC CM1 | `non couvert` (ligne 2035) | meme risque `EMC-4` que `EMC-CP-04` - inchangee |
| `EMC-CM1-09` Role des institutions proches | EMC CM1 | `non couvert` (ligne 2041) | jouable, `EMC-6` - inchangee |
| `EMC-CM1-10` Sens du vote et de la representation | EMC CM1 | `non couvert` (ligne 2042) | jouable, connaissance seule - inchangee |
| `LVE-CP-06` Decouvrir quelques aspects culturels des pays de la langue etudiee | langues vivantes CP | `non couvert` (ligne 1723) | jouable en francais seul, mention exigee d'avance, conditions `BIA-1`/`BIA-2` opposables - inchangee |
| `LVE-CM1-06` Ecrire des mots et expressions memorises | langues vivantes CM1 | `non couvert` (ligne 1742) | jouable par saisie, `ANG-S1`/`ANG-S2` - inchangee |
| `SCI-CM1-03` Decrire le mouvement d'un objet par sa trajectoire et sa vitesse | sciences CM1 | `non couvert` (ligne 2126) | jouable, description seule ; recouvrement a ne pas double-compter avec mathematiques CM1 (calcul de vitesse) - inchangee |
| `SCI-CM1-10` Relier alimentation, activite physique, sommeil et hygiene au corps | sciences CM1 | `non couvert` (ligne 2133) | jouable, `BIA-4`. Frontiere avec `EPS-CM1-10` - voir la correction de la section 3.1 : la ligne en face est fermee a s43, pas engagee a s44 |
| `SCI-CM1-14` Regles d'usage d'un environnement numerique | sciences CM1 | `non couvert` (ligne 2137) | jouable, connaissances seules - inchangee |
| `SCI-CM1-16` Phenomenes geologiques et meteorologiques, risques | sciences CM1 | `non couvert` (ligne 2139) | jouable, `BIA-2` - inchangee |

Aucun refus, aucune reserve nouvelle, aucun changement d'arbitrage trouve sur ces dix-sept lignes
depuis `DEC-0099`, hormis la correction de la section 3.1 (qui ne change ni le statut ni le tarif
de `SCI-CM1-10`, seulement le sprint auquel sa ligne miroir se rattache).

## 4. Hors de portee sans avis de faisabilite de `theo-archi` - non chiffrees

Confirme inchange (`DEC-0099` section 4 ter) : `QLM-CP-12`, `HG-CM1-09`, `HG-CM1-10`, `HDA-CM1-09`
requierent une illustration neuve non instruite. Aucun avis de faisabilite de `theo-archi` n'existe
sur le disque a ce jour pour ces quatre lignes. Elles ne sont pas chiffrees ici.

## 5. Perimetre propose pour s45

**Cible : 4,0/5,0, marge 1,0.**

| Ligne | Matiere / niveau | Tarif | Motif du choix |
|---|---|---|---|
| `MAT-CE1-04` Poser et effectuer une addition avec retenue | mathematiques CE1 | 1,0 | **ACCEPTE SOUS CONDITIONS**, `ADP-CE1-1` a `ADP-CE1-6`, `couvert-valide` atteignable sur texte seul (section 2). Choisie a egalite avec `MAT-CE1-05`, sans motif de fond sur l'ordre - `MAT-CE1-05` reste la candidate suivante |
| `MUS-CP-05` Identifier des instruments et leurs familles | education musicale CP | 1,5 | jouable, mention de couverture partielle exigee d'avance. Si elle ferme aux cotes de `MUS-CP-11` (engagee a s44), les deux seules lignes atteignables du referentiel d'education musicale CP ferment ensemble, exactement la paire que `nadia-prof` anticipe dans sa mention exigee d'avance |
| `EPS-CM1-09` Tenir les roles d'arbitre, d'observateur, de marqueur | education physique et sportive CM1 | 1,5 | jouable, `EPS-1`, mention de couverture partielle exigee d'avance. Continuite avec `EPS-CM1-10`, fermee a s43 sur le meme referentiel |

**Total : 4,0/5,0.** Trois matieres (mathematiques, education musicale, education physique et
sportive), deux niveaux (CE1, CP, CM1). Si les trois lignes ferment integralement et que le
perimetre de s44 ferme aussi, la matrice passerait de 104/156 (66,7 %, projection de s44) a
107/156 (68,6 %).

**Marge de 1,0**, meme raisonnement que `DEC-0099` section 9.4 : elle egale le tarif de la seule
ligne de mathematiques CE1 ajoutee aux deux lignes hors mathematiques. Si elle est retiree en
cours de sprint, le sprint retombe sur les deux lignes de 1,5, jamais a zero contenu ferme. Une
composition a trois lignes de 1,5 chacune (4,5/5,0, marge 0,5) ou a deux lignes de mathematiques
CE1 (5,0/5,0, marge 0,0) sont ecartees pour le meme motif que `DEC-0099` section 9.4 : `DEC-0098`
attribue explicitement une marge de 0,5 aux deux refus de s41, et une marge de 0,0 serait
strictement pire.

## 6. Ce que ce document ne fait pas

Il ne modifie ni `docs/backlog/backlog.md`, ni `squad/sprints/`, ni
`squad/programme/MATRICE-couverture.md`. **Aucun perimetre n'est engage.** Il ne tranche aucune
question technique et ne juge la qualite pedagogique d'aucun contenu - les trois lignes proposees
restent `non couvert` jusqu'au Done pedagogique de `nadia-prof`. Il ne rouvre ni les quatre lignes
de la section 4, ni `QLM-CP-16`, ni les lignes d'EMC deja ecartees pour un motif de fond
(`EMC-CP-04`, `EMC-CM1-02`, `EMC-CM1-03`) - elles restent au vivier pour un sprint ulterieur. Si
l'utilisatrice valide tout ou partie de ce qui precede, l'ecriture des stories et l'ouverture du
dossier de sprint reviennent a `sofia-sm`, comme pour s43 et s44 (`DEC-0099` section 9.8).
