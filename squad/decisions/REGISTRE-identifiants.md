# Registre des identifiants de condition

Une ligne par identifiant. **Un identifiant designe une condition et une seule, dans tout le depot,
et son allocation est visible avant d'ecrire** - c'est la propriete `PAR-5`, posee par `nadia-prof`
le 2026-09-05 et routee a `theo-archi` trois sprints de suite. La forme du registre est decidee par
`ADR-0085`, qui porte aussi la regle executable qui le tient.

**Avant d'ecrire un identifiant neuf, on cherche son prefixe dans la table de la section 4.** S'il y
est, on prend le numero suivant et on ajoute sa ligne dans le meme commit que le document qui le
definit. S'il n'y est pas, le prefixe est libre. **Un identifiant ecrit sans sa ligne fait rougir la
CI** ; deux instances qui allouent le meme identifiant produisent deux lignes de meme identifiant, et
la CI rougit aussi. C'est tout ce que ce fichier fait, et c'est ce qu'on lui demande.

**Note de `theo-archi`, s49, le 2026-10-03 (dette d'`ADR-0082` section 7).** Deux allocations
retirees : les deux seules du prefixe **`REF`**, numeros un et deux. Elles ne sont pas ecrites ici
sous leur forme complete, parce que ce garde-fou exigerait alors leur allocation. C'etaient les
identifiants d'objet du materiau de reference
ecrit a la main dans
`bootstrap/src/test/java/com/recreo/archi/ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest.java`,
que ce lot remplace par le registre reel des oeuvres : le fichier ne les ecrit plus, et le prefixe
`REF` n'a plus aucune ligne. Aucun sens touche, aucune allocation ajoutee.

Ligne d'etat courant portee de **915 / 158 a 913 / 157**. **Methode : le chiffre est celui que le
garde-fou affiche sur mon diff.** `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test
-Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest
-Dsurefire.failIfNoSpecifiedTests=false`, apres retrait des deux lignes et avant la mise a jour de la
ligne d'etat, rend : *"declare 915 identifiants alloues et 158 prefixes distincts, sa table en porte
913 et 157"*. C'est ce **913 / 157** que je recopie. Aucun autre defaut rendu.

**Note de `nadia-prof`, s49, instance A, le 2026-10-03 (Done pedagogique de `FRA-CE1-01` en second
passage, VALIDE SOUS RESERVE).** Un identifiant ajoute, une reserve : **`GRC-CE1-R1`**, ecrit dans
`squad/conformite/FICHE-bandit-manchot-graphemes-complexes-ce1.md`. **Forme copiee** de `PRO-CE1-R1` :
trois segments, niveau au milieu, marqueur `R` dans le dernier (`ADR-0100`). **Aucun prefixe neuf** :
`GRC-CE1-1` a `GRC-CE1-3` etaient alloues ; `GRC-CE1-R1` etait absent de la section 4 et des branches
locales avant d'ecrire. La ligne de la section 4 est dans le meme diff que la fiche.

Ligne d'etat courant portee de **914 / 158 a 915 / 158**. **Methode : le chiffre est celui que le
garde-fou affiche sur mon diff.** `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test
-Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest
-Dsurefire.failIfNoSpecifiedTests=false`, avant la mise a jour de la ligne d'etat, rend :
*"declare 914 identifiants alloues et 158 prefixes distincts, sa table en porte 915 et 158"*. C'est
ce **915 / 158** que je recopie. Aucun autre defaut rendu.

**Note de `nadia-prof`, s48, instance A, le 2026-10-03 (Done pedagogique de `MAT-CE1-08` et de
`LVE-CP-06` en second passage, VALIDES SOUS RESERVE).** Trois identifiants ajoutes, des reserves :
**`PRO-CE1-R1`**, **`PRO-CE1-R2`**, ecrits dans
`squad/conformite/FICHE-bandit-manchot-problemes-etapes-ce1.md`, et **`ANG-CP-R3`**, ecrit dans
`squad/conformite/FICHE-bandit-manchot-culture-anglophone-cp.md`. **Forme copiee** : `PRO-CE1-R1` et
`PRO-CE1-R2` de `TAB-CE1-R1` et `DUR-CE1-R1`, `ANG-CP-R3` de `ANG-CP-R2` - trois segments, niveau au
milieu, marqueur `R` dans le dernier (`ADR-0100`). **Aucun prefixe neuf** : `PRO-CE1-1` a `PRO-CE1-7`
et `ANG-CP-R1`, `ANG-CP-R2` etaient alloues ; les trois identifiants etaient absents de la section 4
et de toutes les branches locales avant d'ecrire. Les trois lignes de la section 4 sont dans le meme
diff que les fiches.

Ligne d'etat courant portee de **911 / 158 a 914 / 158**. **Methode : le chiffre est celui que le
garde-fou affiche sur mon diff.** `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test
-Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest
-Dsurefire.failIfNoSpecifiedTests=false`, avant la mise a jour de la ligne d'etat, rend :
*"declare 911 identifiants alloues et 158 prefixes distincts, sa table en porte 914 et 158"*. C'est
ce **914 / 158** que je recopie. Aucun autre defaut rendu.

**Note de `nadia-prof`, s48, instance B, le 2026-10-03 (arbitrage prealable du referentiel francais
CE1, `DEC-0106` section 4).** Quatre-vingt-six identifiants ajoutes, des conditions, tous ecrits dans
`squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` : `LON-CE1-5`, `TRV-CE1-1` a `TRV-CE1-7`,
et trois conditions pour chacune des vingt-six lignes (`GRC`, `VLE`, `MFR`, `RCT`, `PRN`, `INF`,
`DOC`, `OMP`, `RLP`, `TPH`, `NEG`, `CLM`, `VSJ`, `ACV`, `GNA`, `HOM`, `PRS`, `FUT`, `PCO`, `TPS`,
`MIV`, `GRL`, `ALP`, `FAM`, `SYC`, `CTG`, suffixe `-CE1-1` a `-CE1-3`). **Forme copiee** de
`LON-CE1-4` : trois segments, niveau au milieu, aucun marqueur de reserve. **Vingt-sept prefixes
neufs**, cherches avant d'ecrire dans la section 4 et sur les 79 branches locales, absents ; la
methode de recherche a ete verifiee sur un prefixe connu (`LON`) avant de croire ses zeros. `LON`
n'est pas neuf. Les lignes de la section 4 sont dans le meme diff que l'arbitrage.

Ligne d'etat courant portee de **825 / 131 a 911 / 158**. **Methode : le chiffre est celui que le
garde-fou affiche sur mon diff.** `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test
-Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest
-Dsurefire.failIfNoSpecifiedTests=false`, avant la mise a jour de la ligne d'etat, rend :
*"declare 825 identifiants alloues et 131 prefixes distincts, sa table en porte 911 et 158"*. C'est
ce **911 / 158** que je recopie. Aucun autre defaut rendu.

**Note de `nadia-prof`, s47, instance A, troisieme passage, le 2026-10-02 (Done pedagogique de
`QLM-CP-15`, VALIDE SOUS RESERVE).** Un identifiant ajoute, une reserve : **`AUT-CP-R1`**, ecrit dans
`squad/conformite/FICHE-bandit-manchot-autrefois-aujourdhui-cp.md`. **Forme copiee** de `ANG-CP-R1`
- trois segments, niveau au milieu, marqueur `R` dans le dernier (`ADR-0100`). **Prefixe neuf** :
`AUT` etait absent de la section 4 et de toutes les branches locales avant d'ecrire. La ligne de la
section 4 est dans le meme diff que la fiche.

Ligne d'etat courant portee de **824 / 130 a 825 / 131**. **Methode : le chiffre est celui que le
garde-fou affiche sur mon diff.** `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test
-Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest
-Dsurefire.failIfNoSpecifiedTests=false`, avant la mise a jour de la ligne d'etat, rend :
*"declare 824 identifiants alloues et 130 prefixes distincts, sa table en porte 825 et 131"*. C'est
ce **825 / 131** que je recopie.

**Note de `nadia-prof`, s47, instance A, le 2026-10-02 (Done pedagogique de `MAT-CE1-07` et de
`LVE-CP-06`, VALIDES SOUS RESERVE ; `QLM-CP-15` REFUSE).** Trois identifiants ajoutes, des
reserves : **`TAB-CE1-R1`**, ecrit dans `squad/conformite/FICHE-bandit-manchot-tables-multiplication-ce1.md`,
et **`ANG-CP-R1`**, **`ANG-CP-R2`**, ecrits dans
`squad/conformite/FICHE-bandit-manchot-culture-anglophone-cp.md`. **Forme copiee** : `TAB-CE1-R1`
de `SOP-CE1-R1`, `ANG-CP-R1` et `ANG-CP-R2` de `MUS-CP-R1` - trois segments, niveau au milieu,
marqueur `R` dans le dernier (`ADR-0100`). Les trois ont ete cherches sur l'arbre et sur toutes les
branches locales avant d'ecrire, absents. Les trois lignes de la section 4 sont dans le meme diff que
les fiches. Aucun identifiant n'est ecrit dans la fiche de `bandit-manchot-autrefois-aujourdhui-cp`,
refusee.

Ligne d'etat courant portee de **821 / 130 a 824 / 130**. **Methode : le chiffre est celui que le
garde-fou affiche sur mon diff.** `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test
-Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest
-Dsurefire.failIfNoSpecifiedTests=false`, avant la mise a jour de la ligne d'etat, rend :
*"declare 821 identifiants alloues et 130 prefixes distincts, sa table en porte 824 et 130"*. C'est
ce **824 / 130** que je recopie.

**Note de `nadia-prof`, s47, le 2026-10-02 (borne du texte support au CE1, point B2 de
`DEC-0104`).** Deux identifiants ajoutes, des conditions : **`LON-CE1-3`** et **`LON-CE1-4`**, ecrits
dans `squad/conformite/ARBITRAGE-s47-longueur-du-texte-support-au-ce1.md`. **Aucun prefixe neuf** :
`LON-CE1-1` et `LON-CE1-2` etaient alloues. Les deux identifiants ont ete cherches sur toutes les
branches locales avant d'ecrire, absents. **Forme copiee** de `LON-CE1-2` : trois segments, niveau au
milieu. Les deux lignes de la section 4 sont dans le meme diff que l'arbitrage. Les codes
`FRA-CE1-27` a `FRA-CE1-31`, ecrits dans le meme commit, sont des codes de ligne alloues par leur
referentiel (section 2), pas par ce registre. L'arbitrage de `SOP-CE1-R1` du meme jour n'ecrit aucun
identifiant neuf.

Ligne d'etat courant portee de **819 / 130 a 821 / 130**. **Methode : le chiffre est celui que le
garde-fou affiche sur mon diff.** `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test
-Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest
-Dsurefire.failIfNoSpecifiedTests=false`, avant la mise a jour de la ligne d'etat, rend :
*"declare 819 identifiants alloues et 130 prefixes distincts, sa table en porte 821 et 130"*. C'est
ce **821 / 130** que je recopie.

**Note de `nadia-prof`, s46, instance A, troisieme passage, le 2026-10-02 (Done pedagogique de
`EMC-CM1-09`, VALIDE SOUS RESERVE).** Un identifiant ajoute, une reserve : **`EMC-CM1-R7`**, ecrit
dans `squad/conformite/FICHE-bandit-manchot-institutions-cm1.md`. **Aucun prefixe neuf** :
`EMC-CM1-R1` a `EMC-CM1-R6` etaient alloues. L'identifiant a ete cherche sur l'arbre et sur toutes
les branches locales avant d'ecrire, absent. **Forme copiee** d'`EMC-CM1-R6` : trois segments,
marqueur `R` dans le dernier (`ADR-0100`). La ligne de la section 4 est dans le meme diff que la
fiche.

Ligne d'etat courant portee de **818 / 130 a 819 / 130**. **Methode : le chiffre est celui que le
garde-fou affiche sur mon diff.** `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test
-Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest
-Dsurefire.failIfNoSpecifiedTests=false`, avant la mise a jour de la ligne d'etat, rend :
*"declare 818 identifiants alloues et 130 prefixes distincts, sa table en porte 819 et 130"*. C'est
ce **819 / 130** que je recopie.

**Note de `nadia-prof`, s46, instance A, second passage, le 2026-10-02 (Done pedagogique de
`MAT-CE1-05`, VALIDE SOUS RESERVE).** Un identifiant ajoute, une reserve : **`SOP-CE1-R1`**, ecrit
dans `squad/conformite/FICHE-bandit-manchot-soustractions-posees-ce1.md`. **Aucun prefixe neuf** :
`SOP-CE1-1` a `SOP-CE1-6` etaient alloues. L'identifiant a ete cherche sur l'arbre et sur toutes les
branches locales avant d'ecrire, absent. **Forme copiee** de `SOU-CE1-R1` et `ADD-CE1-R1` : trois
segments, marqueur `R` dans le dernier (`ADR-0100`). La ligne de la section 4 est dans le meme diff
que la fiche. Aucun identifiant n'est ecrit dans la fiche de `bandit-manchot-institutions-cm1`,
refusee de nouveau.

Ligne d'etat courant portee de **817 / 130 a 818 / 130**. **Methode : le chiffre est celui que le
garde-fou affiche sur mon diff.** `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test
-Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest
-Dsurefire.failIfNoSpecifiedTests=false`, avant la mise a jour de la ligne d'etat, rend :
*"declare 817 identifiants alloues et 130 prefixes distincts, sa table en porte 818 et 130"*. C'est
ce **818 / 130** que je recopie.

**Note de fusion, s46, le 2026-10-02.** Les instances A et B ont allouees en parallele depuis le
meme socle (807 / 128), chacune sur son propre diff, exactement le cas que `PAR-5` et la clause 5
d'`ADR-0085` anticipent : le compte de chacune des deux notes ci-dessous n'est juste que sur son
propre diff, et la reparation a la fusion est arithmetique, aucune allocation ajoutee ni retiree.
Compte combine : **807 + 2 (instance A) + 8 (instance B) = 817** identifiants, **128 + 2 prefixes
neufs (`MVT`, `PHN`) = 130** prefixes distincts. Voir la ligne d'etat courant, section 1.

**Note de `nadia-prof`, s46, instance A, le 2026-10-02 (Done pedagogique de `EMC-CM1-01`, VALIDE
SOUS RESERVE).** Deux identifiants ajoutes, tous des reserves : **`EMC-CM1-R5`** et
**`EMC-CM1-R6`**, ecrits dans `squad/conformite/FICHE-bandit-manchot-emotion-ressentir-agir-cm1.md`.
**Aucun prefixe neuf** : `EMC-CM1-R1` a `EMC-CM1-R4` etaient alloues, et les deux identifiants ont
ete cherches sur l'arbre et sur toutes les branches locales avant d'ecrire, absents. **Forme copiee**
d'`EMC-CM1-R4` : trois segments, marqueur `R` dans le dernier (`ADR-0100`). Les deux lignes de la
section 4 sont dans le meme diff que la fiche. Aucun identifiant n'est ecrit dans les fiches de
`bandit-manchot-soustractions-posees-ce1` ni de `bandit-manchot-institutions-cm1`, refusees.

Ligne d'etat courant portee de **807 / 128 a 809 / 128** (sur son seul diff). **Methode : le chiffre
est celui que le garde-fou affiche sur mon diff.** `bash outils/mvn-jdk21.sh -B -pl bootstrap -am
test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest
-Dsurefire.failIfNoSpecifiedTests=false`, avant la mise a jour de la ligne ci-dessous, rend :
*"declare 807 identifiants alloues et 128 prefixes distincts, sa table en porte 809 et 128"*. C'est
ce **809 / 128** que je recopie. L'instance B travaille en parallele sur le meme fichier : si elle
alloue aussi, le compte se reverifie a la fusion (`ADR-0085`, clause 5) - voir la note de fusion
ci-dessus.

**Note de `nadia-prof`, s46, instance B, le 2026-10-02 (`SCI-CM1-16`, REFUSE).** Quatre
identifiants ajoutes, tous des reserves : **`PHN-CM1-R1` a `PHN-CM1-R4`**, ecrits dans
`squad/conformite/FICHE-bandit-manchot-phenomenes-naturels-cm1.md`. Le motif du refus ne porte
aucun identifiant. **Un prefixe neuf, `PHN`** : absent de la table de la section 4, et cherche sur
l'arbre et sur toutes les branches locales avant d'ecrire, absent. **Forme copiee** d'`USN-CM1-R9` :
trois segments, marqueur `R` dans le dernier (`ADR-0100`). Les quatre lignes de la section 4 sont
dans le meme diff que la fiche.

Ligne d'etat courant portee de **811 / 129 a 815 / 130** (sur son seul diff, qui inclut deja son
propre commit `MVT` ci-dessous). **Methode : le chiffre est celui que le garde-fou affiche sur mon
diff.** `bash outils/mvn-jdk21.sh -pl bootstrap -am test
-Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest
-Dsurefire.failIfNoSpecifiedTests=false`, avant la mise a jour de la ligne ci-dessous, rend :
*"declare 811 identifiants alloues et 129 prefixes distincts, sa table en porte 815 et 130"*. C'est
ce **815 / 130** que je recopie - voir la note de fusion ci-dessus pour le compte combine avec
l'instance A.

**Note de `nadia-prof`, s46, instance B, le 2026-10-02 (Done pedagogique de `SCI-CM1-03`, VALIDE
SOUS RESERVE).** Quatre identifiants ajoutes, tous des reserves : **`MVT-CM1-R1` a `MVT-CM1-R4`**,
ecrits dans `squad/conformite/FICHE-bandit-manchot-mouvement-cm1.md`. **Un prefixe neuf, `MVT`** :
absent de la table de la section 4, et cherche sur l'arbre et sur toutes les branches locales avant
d'ecrire, absent. **Forme copiee** d'`USN-CM1-R9` : trois segments, marqueur `R` dans le dernier
(`ADR-0100`). Les quatre lignes de la section 4 sont dans le meme diff que la fiche.

Ligne d'etat courant portee de **807 / 128 a 811 / 129** (sur son seul diff). **Methode : le chiffre
est celui que le garde-fou affiche sur mon diff.** `bash outils/mvn-jdk21.sh -pl bootstrap -am test
-Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest
-Dsurefire.failIfNoSpecifiedTests=false`, avant la mise a jour de la ligne ci-dessous, rend :
*"declare 807 identifiants alloues et 128 prefixes distincts, sa table en porte 811 et 129"*. C'est
ce **811 / 129** que je recopie.

**Note de `nadia-prof`, s45, instance B, second passage, le 2026-10-01 (Done pedagogique de
`SCI-CM1-14`, VALIDE SOUS RESERVE).** Neuf identifiants ajoutes, tous des reserves :
**`USN-CM1-R1` a `USN-CM1-R9`**, ecrits dans
`squad/conformite/FICHE-bandit-manchot-usages-numeriques-cm1.md`. **Un prefixe neuf, `USN`** :
absent de la table de la section 4, et cherche sur l'arbre et sur toutes les branches locales avant
d'ecrire, absent. **Forme copiee** d'`EVO-CM1-R4` : trois segments, marqueur `R` dans le dernier
(`ADR-0100`). Les neuf lignes de la section 4 sont dans le meme diff que la fiche.

Ligne d'etat courant portee de **798 / 127 a 807 / 128**. **Methode : le chiffre est celui que le
garde-fou affiche sur mon diff.** `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test
-Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest
-Dsurefire.failIfNoSpecifiedTests=false`, avant la mise a jour de la ligne ci-dessous, rend :
*"declare 798 identifiants alloues et 127 prefixes distincts, sa table en porte 807 et 128"*. C'est
ce **807 / 128** que je recopie.

**Note de `nadia-prof`, s45, instance A, le 2026-10-01 (Done pedagogiques de `MAT-CE1-04`,
`MUS-CP-05` et `EPS-CM1-09`).** Quatre identifiants ajoutes, tous des reserves : **`MUS-CP-R2`** et
**`MUS-CP-R3`**, ecrits dans `squad/conformite/FICHE-bandit-manchot-instruments-musicaux-cp.md` ;
**`EPS-CM1-R5`** et **`EPS-CM1-R6`**, ecrits dans
`squad/conformite/FICHE-bandit-manchot-roles-eps-cm1.md`. **Aucun prefixe neuf** : `MUS-CP-R1` et
`EPS-CM1-R1` a `EPS-CM1-R4` etaient alloues, et les quatre identifiants ont ete cherches sur l'arbre
et sur toutes les branches locales avant d'ecrire, absents. **Formes copiees** de `MUS-CP-R1` et
`EPS-CM1-R4` : trois segments, marqueur `R` dans le dernier (`ADR-0100`). Les quatre lignes de la
section 4 sont dans le meme diff que les deux fiches. Aucun identifiant n'est ecrit dans la fiche
de `bandit-manchot-additions-posees-ce1`, validee sans reserve.

Ligne d'etat courant portee de **794 / 127 a 798 / 127**. **Methode : le chiffre est celui que le
garde-fou affiche sur mon diff.** `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test
-Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest
-Dsurefire.failIfNoSpecifiedTests=false`, avant la mise a jour de la ligne ci-dessous, rend :
*"declare 794 identifiants alloues et 127 prefixes distincts, sa table en porte 798 et 127"*. C'est
ce **798 / 127** que je recopie. L'instance B travaille en parallele sur le meme fichier : si elle
alloue aussi, le compte se reverifie a la fusion (`ADR-0085`, clause 5).

**Note de `nadia-prof`, s45, le 2026-10-01 (Q1, Q3, Q4 et Q5 de l'avis de faisabilite
`avis-faisabilite-s45-cinq-lots.md`).** Un identifiant ajoute, une condition : **`EPS-10`**
(discriminant CM1/CP de `EPS-CM1-09` face a `cp-eps-regles-jeu-collectif`), ecrit dans
`squad/conformite/ARBITRAGE-PREALABLE-s45-cinq-lots.md`. **Aucun prefixe neuf** : `EPS-1` a `EPS-9`
etaient alloues, et `EPS-10` a ete cherche sur l'arbre entier avant d'ecrire, absent. **Forme
copiee** de `EPS-9` (deux segments, aucun marqueur de reserve, `ADR-0100`), rang a deux chiffres
comme `EMC-10`. La ligne de la section 4 est dans le meme diff que le document.

Ligne d'etat courant portee de **793 / 127 a 794 / 127**. **Methode : le chiffre est celui que le
garde-fou affiche sur mon diff.** `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test
-Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest
-Dsurefire.failIfNoSpecifiedTests=false`, avant la mise a jour de la ligne ci-dessous, rend :
*"declare 793 identifiants alloues et 127 prefixes distincts, sa table en porte 794 et 127"*. C'est
ce **794 / 127** que je recopie.

**Note de `nadia-prof`, s44, le 2026-09-30 (les trois Done pedagogiques de s44).** Six
identifiants ajoutes, tous des reserves de mes trois verdicts du jour : **`MUS-CP-R1`**, ecrit dans
`squad/conformite/FICHE-bandit-manchot-vocabulaire-musical-cp.md` ; **`EVO-CM1-R1` a
`EVO-CM1-R4`**, ecrits dans `squad/conformite/FICHE-bandit-manchot-evolution-objet-technique-cm1.md` ;
**`DIV-CE1-R1`**, ecrit dans `squad/conformite/FICHE-bandit-manchot-division-ce1.md`. **Aucun
prefixe neuf** : `MUS`, `EVO` et `DIV` etaient deja alloues. Les six identifiants ont ete cherches
sur le depot entier et sur les branches locales avant d'ecrire : aucun n'y figurait. **Formes
copiees** : `MUS-CP-R1` et `EVO-CM1-R1` sur `NUQ-CP-R1` et `EPS-CM1-R1`, `DIV-CE1-R1` sur
`DUR-CE1-R1` ; trois segments, marqueur `R` dans le dernier (`ADR-0100`). Un identifiant a trois
segments ne se collisionne pas avec `MUS-3`, `EVO-2` ou `DIV-CE1-6`, comme `EPS-CM1-R1` avec
`EPS-9`. Les six lignes de la section 4 sont dans le meme diff que les trois fiches.

Ligne d'etat courant portee de **787 / 127 a 793 / 127**. **Methode : le chiffre est celui que le
garde-fou affiche sur mon diff.** `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test
-Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest
-Dsurefire.failIfNoSpecifiedTests=false`, avant la mise a jour de la ligne ci-dessous, rend :
*"declare 787 identifiants alloues et 127 prefixes distincts, sa table en porte 793 et 127"*. C'est
ce **793 / 127** que je recopie.

**Note de `nadia-prof`, s44, le 2026-09-30 (Q1 et Q3 de l'avis de faisabilite
`avis-faisabilite-s44-mus-sci.md`).** Trois identifiants ajoutes, tous des conditions, ecrits dans
`squad/conformite/ARBITRAGE-PREALABLE-s44-musique-et-sciences-cm1.md` : **`MUS-3`** (forme d'enonce
de `pulsation` et `silence` sur `MUS-CP-11`), **`EVO-1`** et **`EVO-2`** (discriminant entre
`SCI-CM1-13` et les lignes servies `SCI-CM1-11` et `SCI-CM1-12`). **Forme copiee** : `MUS-3` suit
`MUS-1` et `MUS-2`, deja alloues ; `EVO` est un prefixe neuf, verifie absent de la table de la
section 4, de l'arbre entier et des branches locales avant d'ecrire, et sa forme `PREFIXE-<n>` est
copiee de `HDA-1`, condition posee sur une seule ligne de referentiel. Aucun marqueur de reserve
(`ADR-0100`) : ce sont des conditions. Les lignes de la section 4 sont dans le meme diff que le
document qui les ecrit.

Ligne d'etat courant portee de **784 / 126 a 787 / 127**. **Methode : le chiffre est celui que le
garde-fou affiche sur mon diff.** `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test
-Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest
-Dsurefire.failIfNoSpecifiedTests=false`, avant la mise a jour de la ligne ci-dessous, rend :
*"declare 784 identifiants alloues et 126 prefixes distincts, sa table en porte 787 et 127"*. C'est
ce **787 / 127** que je recopie.

**Note de `nadia-prof`, hors sprint avant s44, le 2026-09-25 (frontiere `MAT-CE1-06` /
`MAT-CE1-18`).** Un identifiant ajoute, **`DIV-CE1-6`**, ecrit dans
`squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md`, section 11 : la clause qui
range le reseau dessine, l'addition reiteree a nombre de termes inconnu et le cas nu *"combien de
fois 3 dans 12"* entre les deux lignes. **Aucun prefixe neuf** : `DIV-CE1-1` a `DIV-CE1-5` etaient
alloues ; le rang 6 a ete verifie absent de la table de la section 4, de l'arbre entier et des trois
branches `hs44-*` en vol avant d'ecrire. **Forme copiee de `DIV-CE1-5`** : trois segments, aucun
marqueur de reserve (`ADR-0100`), parce que c'est une condition. La ligne de la section 4 est dans
le meme diff que l'addendum qui ecrit l'identifiant.

Ligne d'etat courant portee de **783 / 126 a 784 / 126**. **Methode : le chiffre est celui que le
garde-fou affiche sur mon diff, jamais un compte que je refais de tete.**
`bash outils/mvn-jdk21.sh -pl bootstrap -am test
-Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest
-Dsurefire.failIfNoSpecifiedTests=false`, avant la mise a jour de la ligne ci-dessous, rend :
*"declare 783 identifiants alloues et 126 prefixes distincts, sa table en porte 784 et 126"*. C'est
ce **784 / 126** que je recopie.

**Note de `nadia-prof`, s43, le 2026-09-25 (les trois Done pedagogiques de s43).** Sept identifiants
ajoutes, tous des reserves de mes trois verdicts du jour :

- **`NUQ-CP-R1`, `NUQ-CP-R2`**, ecrits dans
  `squad/conformite/FICHE-bandit-manchot-objets-numeriques-cp.md` (reserves du
  `VALIDE SOUS RESERVE` sur `S43-QLM-NUMERIQUE` : un distracteur vrai et un vocabulaire hors du CP
  sur l'item 11, deux distracteurs de poids sans rapport sur les items 8 et 10) ;
- **`EPS-CM1-R1` a `EPS-CM1-R4`**, ecrits dans
  `squad/conformite/FICHE-bandit-manchot-effets-activite-eps-cm1.md` (reserves du
  `VALIDE SOUS RESERVE` sur `S43-EPS-EFFETS-ACTIVITE` : un item qui refait le CP, une bonne reponse
  circulaire et reconnaissable a sa longueur, l'echauffement absent du paquet, et une bonne reponse
  designee par le seul mot de duree sur trois items) ;
- **`DUR-CE1-R1`**, ecrit dans `squad/conformite/FICHE-bandit-manchot-problemes-durees-ce1.md`
  (reserve du `VALIDE SOUS RESERVE` sur `S43-MAT-DUREE` : l'indice de l'item 3 donne le resultat).

**Un seul prefixe neuf, `NUQ`**, verifie libre **sur le depot entier** avant d'ecrire, et pas
seulement dans la table de la section 4. Je n'ai repris ni `NUM`, deja alloue a la numeration CE1
(`NUM-CE1-1` a `-7`), ni `OBJ`, deja alloue aux sciences (`OBJ-R1`, `OBJ-R2`) : un prefixe ne porte
qu'un sens. **Aucun prefixe neuf du cote `EPS` ni `DUR`** - `EPS-1` a `EPS-9` et `DUR-CE1-1` a
`DUR-CE1-7` etaient deja alloues, et un identifiant a deux segments ne se collisionne pas avec un
identifiant a trois segments de meme prefixe.

**Les formes sont copiees, jamais inventees.** `NUQ-CP-R1` et `EPS-CM1-R1` sur `DEP-CP-R1` et
`EMC-CM1-R1`, alloues le 2026-09-24 ; `DUR-CE1-R1` sur `ADD-CE1-R1` et `SOU-CE1-R1`. **Trois
segments, marqueur `R` dans le dernier segment** (`ADR-0100`), jamais en tete ni au milieu. **Les
sept lignes de la section 4 sont dans le meme diff que les trois fiches qui ecrivent ces
identifiants**, jamais dans un commit de reparation qui suit.

Ligne d'etat courant portee de **776 / 125 a 783 / 126**. **Methode : le chiffre est celui que le
garde-fou affiche sur mon diff, jamais un compte que je refais de tete.**
`bash outils/mvn-jdk21.sh -pl bootstrap -am test
-Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest
-Dsurefire.failIfNoSpecifiedTests=false`, avant la mise a jour de la ligne ci-dessous, rend :
*"declare 776 identifiants alloues et 125 prefixes distincts, sa table en porte 783 et 126"*. C'est
ce **783 / 126** que je recopie, et rien d'autre. Je n'ai ni recalcule 776 + 7, ni deduit le nombre
de prefixes de la nouveaute de `NUQ` : les deux chiffres sortent de la commande.

**Note de `nadia-prof`, s43, le 2026-09-25 (les deux discriminants poses avant l'ecriture des deux
paquets).** Deux identifiants ajoutes, tous deux ecrits dans `docs/backlog/backlog.md`, section
"Sprint 43" : **`EPS-9`** (discriminant CM1/CP de `S43-EPS-EFFETS-ACTIVITE`, `EPS-CM1-10`) et
**`DUR-CE1-7`** (discriminant avec `cm1-problemes-durees` de `S43-MAT-DUREE`, `MAT-CE1-12`).
**Aucun prefixe neuf** : `EPS-1` a `EPS-8` et `DUR-CE1-1` a `DUR-CE1-6` etaient deja alloues,
verifies dans la table de la section 4 **et** sur le depot entier avant d'ecrire - `EPS-9` et
`DUR-CE1-7` n'y apparaissaient nulle part. **Les deux formes sont copiees, jamais inventees** :
`EPS-9` sur `EPS-8` (deux segments), `DUR-CE1-7` sur `DUR-CE1-6` (trois segments). Aucun des deux
ne porte de marqueur de reserve (`ADR-0100`) : ce sont des conditions, pas des reserves.

**Ce que ces deux allocations couvrent, et pourquoi elles sont dans le backlog et non dans une
fiche.** Les deux conditions sont posees **avant** que le contenu ne soit ecrit, en reponse aux deux
conditions que le planning de s43 (sections 5 et 6) m'a transmises. Leur destinataire est le
developpeur affecte, qui lit la story ; les ecrire ailleurs les aurait separees des criteres
d'acceptation qu'elles qualifient. Aucune fiche de conformite n'est ouverte : aucun increment
n'existe, et je ne prononce aucun Done pedagogique dans ce commit.

Ligne d'etat courant portee de **774 / 125 a 776 / 125**. **Methode : le chiffre est celui que le
garde-fou affiche sur mon diff, jamais un compte que je refais de tete.**
`bash outils/mvn-jdk21.sh -pl bootstrap -am test
-Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest
-Dsurefire.failIfNoSpecifiedTests=false`, avant la mise a jour de la ligne ci-dessus, rend :
*"declare 774 identifiants alloues et 125 prefixes distincts, sa table en porte 776 et 125"*. C'est
ce 776 / 125 que je recopie, et la reexecution apres mise a jour est verte.

**Note de `nadia-prof`, s43, le 2026-09-25 (reparation - les trente-deux identifiants du lot 1,
ecrits sans atteindre ce fichier).** Trente-deux identifiants ajoutes, tous ecrits dans
`squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` : `NUM-CE1-1` a `NUM-CE1-7`
(numeration jusqu'a 1000, `MAT-CE1-01`), `ORD-CE1-1` a `ORD-CE1-6` (comparer, ranger, encadrer,
ligne graduee, `MAT-CE1-02`), `ADP-CE1-1` a `ADP-CE1-6` (addition posee, `MAT-CE1-04`), `SOP-CE1-1`
a `SOP-CE1-6` (soustraction posee, `MAT-CE1-05`), `MUL-CE1-1` a `MUL-CE1-7` (sens de la
multiplication, `MAT-CE1-06`). **Cinq prefixes neufs - `NUM`, `ORD`, `ADP`, `SOP`, `MUL`** -
verifies absents de la table de la section 4 avant allocation, et verifies absents des codes de
ligne de referentiel d'`ADR-0081` (les onze prefixes de code de ligne du depot sont `ART`, `EMC`,
`EPS`, `FRA`, `HDA`, `HG`, `LVE`, `MAT`, `MUS`, `QLM`, `SCI` - aucune collision, clause 6). **La
forme a trois segments est copiee sur `ADD-CE1-1` et `MOI-CE1-2`**, deja allouees et de la meme
famille, jamais inventee ; aucun de ces trente-deux jetons ne porte de marqueur de reserve
(`ADR-0100`). **`ADP` et `SOP` sont volontairement distincts de `ADD` et `SOU`**, qui gouvernent le
calcul mental de `MAT-CE1-03` : le calcul pose est une autre ligne, et deux familles de conditions
qui partageraient un prefixe se liraient comme une seule. Les trente-deux sont places a
l'emplacement alphabetique de leur prefixe dans la table, et non en tete comme le lot 2.

**Le defaut que cette note repare, et il est de moi.** Mon commit `ad6507fc` annonce ces
trente-deux allocations dans son message et **le diff reellement commite dans ce fichier etait
celui du lot 2**, pas le mien : trois instances de `nadia-prof` ecrivaient ce fichier sur **le meme
arbre de travail**, et ma mise a jour a ete perdue entre mon edition et mon commit. `ad6507fc` a
donc laisse `round-two` **rouge** sur `PAR-5` clause 2 - dix echecs, tous de la forme *"ton document
ecrit l'identifiant X, que le registre n'alloue pas"*. J'avais nomme ce risque dans le commit perdu,
en l'appelant un ecart arithmetique reparable a l'assemblage ; **c'etait une mesure trop douce du
risque que je decrivais** - une perte de mise a jour n'est pas un ecart de comptage, elle efface
l'allocation entiere. Ce que cet incident ajoute a la liste de mes trois precedents de s40 et s42 :
lancer le garde-fou **avant** le commit ne suffit pas quand l'arbre est partage, parce que le
fichier mesure n'est pas celui qui sera commite. **Le compte se lit sur l'outil, et il se relit sur
le commit.**

**Note de `nadia-prof`, s43, le 2026-09-25 (arbitrage prealable, lot 2 de mathematiques CE1).**
Trente identifiants ajoutes, tous ecrits dans
`squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` : `TAB-CE1-1` a `TAB-CE1-6`
(tables de multiplication, `MAT-CE1-07`), `PRO-CE1-1` a `PRO-CE1-7` (problemes a une ou deux etapes,
`MAT-CE1-08`), `MAS-CE1-1` a `MAS-CE1-6` (masses et contenances, `MAT-CE1-10`), `CAL-CE1-1` a
`CAL-CE1-5` (calendrier, `MAT-CE1-11`), `DUR-CE1-1` a `DUR-CE1-6` (durees, `MAT-CE1-12`).
**Cinq prefixes neufs - `TAB`, `PRO`, `MAS`, `CAL`, `DUR`** - verifies absents de la table de la
section 4 avant allocation. **`CAL` plutot que `HOR`** pour le calendrier : `HOR-1` a `HOR-8` sont
deja alloues au palier 1 visuel, et deux familles qui partageraient un prefixe se liraient comme une
seule. **La forme a trois segments est copiee sur `ADD-CE1-1` et `MOI-CE1-2`**, deja allouees et de
la meme famille, jamais inventee ; aucun de ces trente jetons ne porte de marqueur de reserve
(`ADR-0100`).

**Ce que ce recompte est, et ce qu'il n'est pas.** Ligne d'etat courant portee de **712 / 115 a
742 / 120**, chiffre rendu par l'execution reelle de
`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` sur l'arbre de ce commit, jamais un chiffre
recalcule depuis la ligne precedente. **Deux autres instances de `nadia-prof` arbitrent en parallele
les deux autres lots des quatorze lignes de mathematiques CE1, et je constate qu'elles ecrivent dans
ce fichier sur le meme arbre de travail que moi** - ce n'est pas une precaution abstraite, j'ai vu
leurs lignes sur l'arbre avant de commiter. **Mon commit ne porte que mes trente allocations et mon
seul document** : je n'emporte pas des lignes qui pointent vers des documents que je ne commite pas,
ce qui ferait rougir la clause 3 sur un clone de mon commit. Quand les deux autres instances
commiteront les leurs, la ligne d'etat courant montera d'autant ; si un ecart subsiste a
l'assemblage, la CI rougira comme `ADR-0085` clause 5 le prevoit, et **la reparation sera
arithmetique** - aucune allocation ajoutee, aucune retiree, aucun sens touche.

**Note de `nadia-prof`, s38, le 2026-09-22 (audit du paquet de soustractions).** Un identifiant
ajoute - `SOU-CE1-R1`, la reserve non bloquante ouverte sur le second distracteur des quatre items
`b < 10` du paquet de soustractions de CE1, ecrite dans
`squad/conformite/FICHE-bandit-manchot-soustractions-ce1.md`. **Aucun prefixe neuf** : `SOU` etait
deja alloue par mes deux clauses de la veille (le paragraphe qui suit), verifie avant allocation.
Meme forme a trois segments que `ADD-CE1-R1`/`ADD-CE1-R2`, pour la meme raison : nommer une reserve
de CE1 sans taire le niveau. Ligne d'etat courant portee de **665 / 108 a 666 / 108**. Methode :
comptage des lignes de la table de la section 4 sous le motif du garde-fou lui-meme (`ALLOCATION`,
identifiant et document entre accents graves, `ADR-0085` clause 5), prefixe pris comme **premier
segment** de l'identifiant. Sur mon worktree dedie, avant mon diff : 665 identifiants distincts,
108 prefixes distincts, 0 doublon, ce qui confirme la ligne d'etat courant que le fichier
declarait.

**Note de `nadia-prof`, s39, le 2026-09-22 (arbitrage prealable sur quatre lots).** Onze
identifiants ajoutes, tous ecrits dans
`squad/conformite/ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md` : `MES-CE1-1` a
`MES-CE1-3` (bornes de tirage pour la mesure textuelle de longueurs, `S39-LONGUEUR`), `MON-CE1-1` a
`MON-CE1-4` (bornes de tirage pour la monnaie CE1, `S39-MONNAIE`), `SOL-CE1-1` a `SOL-CE1-4`
(conditions de rendu visuel des solides, `S39-SOLIDES`). **Un seul prefixe neuf : `SOL`** ; `MES`
et `MON` etaient deja alloues (`MES-1`/`MES-2`, `MON-1` a `MON-6`), verifie avant allocation.
`S39-DEN` ne recoit aucun identifiant neuf : `DEN-2` est confirmee telle quelle, pas amendee.
Ligne d'etat courant portee de **666 / 108 a 677 / 109**. Methode : comptage des lignes de la
table de la section 4 sous le motif du garde-fou lui-meme (`ALLOCATION`, identifiant et document
entre accents graves, `ADR-0085` clause 5), prefixe pris comme **premier segment** de
l'identifiant. Sur mon worktree dedie, avant mon diff : 666 identifiants distincts, 108 prefixes
distincts, 0 doublon, ce qui confirme la ligne d'etat courant que le fichier declarait ; apres mon
diff : 677 et 109.

**Note de `nadia-prof`, s40, le 2026-09-23 (reparation - identifiants ecrits sans allocation).**
Quatre identifiants ajoutes, tous ecrits dans
`squad/conformite/ARBITRAGE-PREALABLE-s40-relier-une-oeuvre-a-son-contexte-hda-cm1-05.md` :
`HDA-1` a `HDA-4` (criteres qui font tenir `HIS-2` sur `HDA-CM1-05` - forme de l'item, faits de
contexte admissibles, distracteurs, diversite du corpus). J'avais ecrit ce document dans un commit
anterieur sans allouer ses identifiants dans ce registre : `PAR-5` (`ADR-0085`, clause 2) a rougi
a la fusion, signale par `sofia-sm` et retrouve independamment par `theo-archi`. **Un prefixe
neuf : `HDA`**, verifie absent de la table de la section 4 avant allocation - je le distingue des
codes de ligne a trois segments du referentiel d'histoire des arts (`HDA-CM1-01` a `HDA-CM1-10`,
`ADR-0081`), qui portent trois segments et non deux : aucune collision de nom, verifiee sur les
deux chaines exactes plutot que sur le seul prefixe partage. Ligne d'etat courant portee de
**677 / 109 a 681 / 110**. Methode : comptage des lignes de la table de la section 4 sous le motif
du garde-fou lui-meme (`ALLOCATION`, identifiant et document entre accents graves, `ADR-0085`
clause 5), prefixe pris comme **premier segment** de l'identifiant. Sur mon worktree dedie, avant
mon diff : 677 identifiants distincts, 109 prefixes distincts, ce qui confirme la ligne d'etat
courant que le fichier declarait ; apres mon diff : 681 et 110.

**Note de `camille-po`, s40, le 2026-09-23 (reserve non bloquante sur la devise CP).** Un
identifiant ajoute - `DVS-CP-R1`, la reserve non bloquante ouverte par `nadia-prof` sur le verbe
*illustre* (dix des onze enonces du paquet, registre trop soutenu pour un enfant de CP), decrite en
prose dans `squad/conformite/FICHE-bandit-manchot-devise-cp.md` sans y recevoir de code - c'est moi
qui le mine, pour qu'un sprint futur puisse la retrouver et l'appliquer sans redemander a
`nadia-prof` ce qu'elle a deja ecrit. **J'ecris `DVS-CP-R1` dans
`squad/programme/MATRICE-couverture.md`, jamais dans la fiche de `nadia-prof`** : je ne touche pas
un document qu'elle signe. **Un prefixe neuf : `DVS`**, verifie absent de la table de la section 4
avant allocation - distinct du prefixe voisin `DV` (validation design, s25, sans rapport). Ligne
d'etat courant portee de **681 / 110 a 682 / 111**. Methode : comptage des lignes de la table de la
section 4 sous le motif du garde-fou lui-meme (`ALLOCATION`, identifiant et document entre accents
graves, `ADR-0085` clause 5), prefixe pris comme **premier segment** de l'identifiant. Sur mon
worktree dedie, avant mon diff : 681 identifiants distincts, 110 prefixes distincts, 0 doublon, ce
qui confirme la ligne d'etat courant que le fichier declarait.

**Note de `nadia-prof`, s42, le 2026-09-24 (reparation - sept identifiants ecrits sans allocation,
instance A).** Sept identifiants ajoutes, issus de mes deux verdicts du jour sur le terrain 1 de
`DEC-0098` :

- `HG-R23`, `HG-R24`, `HG-R25`, ecrits dans
  `squad/conformite/FICHE-bandit-manchot-histoire-geographie-nommer-en-histoire-cm1.md` (reserves
  neuves sur les cinq items de `S42-HG-ROIS` : un enonce qui nomme ses propres distracteurs, trois
  couples bonne reponse / distracteurs deja servis par `HG-CM1-06`, une annonce de *quatre*
  propositions devant trois). **`HG-R25` est aussi citee dans
  `FICHE-bandit-manchot-histoire-geographie-situer-dans-le-temps-cm1.md`**, qui y renvoie plutot que
  de la recopier ; le registre nomme le document ou elle est **ecrite**, une seule fois ;
- `MOB-R1` a `MOB-R4`, ecrits dans `squad/conformite/FICHE-bandit-manchot-matiere-objets-cp.md`
  (reserves neuves sur `S42-QLM-MATIERE` : vocabulaire de la consigne, deux enonces illisibles au CP,
  un distracteur defendable).

**Aucun prefixe neuf du cote `HG`** (`HG-R1` a `HG-R22` etaient deja alloues, verifie avant
allocation, le suivant libre etait `HG-R23`). **Un prefixe neuf : `MOB`**, verifie absent de la
table de la section 4 avant allocation - je ne reprends pas `MAT`, deja alloue a une autre famille
(`MAT-R1`/`MAT-R2`, familles de materiaux CM1), pour ne pas faire porter deux sens au meme prefixe ;
je ne reprends pas non plus `MOI`, `MON` ni `MNC`, alloues et sans rapport.

**J'ai ecrit ces sept identifiants dans un commit anterieur du jour (`1d5c00de`) sans les allouer
ici** : `PAR-5` (`ADR-0085`, clause 2) a rougi sur `round-two`, trouve par `lea-dev` en lancant
`mvn -B clean verify` apres sa propre correction. **C'est la deuxieme fois que je cause ce rouge
exactement de la meme facon** - la premiere est la note de s40, quatre lignes plus haut, datee
d'hier. Je le laisse ecrit a cote d'elle plutot que de le presenter comme un incident isole : deux
fois le meme oubli en deux jours n'est pas un accident, c'est que **j'ecris mes reserves dans la
fiche et que j'appelle le verdict fini avant d'avoir ouvert ce fichier**.

Ligne d'etat courant portee de **682 / 111 a 689 / 112**. Methode : comptage des lignes de la table
de la section 4 sous le motif du garde-fou lui-meme (`ALLOCATION`, identifiant et document entre
accents graves, `ADR-0085` clause 5), prefixe pris comme **premier segment** de l'identifiant. Sur
mon arbre, avant mon diff : 682 identifiants distincts, 111 prefixes distincts, 0 doublon, ce qui
confirme la ligne d'etat courant que le fichier declarait ; apres mon diff : 689 et 112.

**Ce chiffre est juste sur ma moitie du sprint et je ne peux pas le garantir sur l'arbre entier.**
`DEC-0098` fait tourner deux instances de `nadia-prof` en parallele ; si l'instance B alloue sur son
terrain, la clause 5 rougira a la fusion et la reparation sera arithmetique. C'est la limite
structurelle de `PAR-5` que `theo-archi` a choisi de laisser nommee plutot que d'encoder, et elle
s'est deja produite a s31 (ligne 543 / 93 de la note d'assemblage). **Je l'ecris d'avance plutot que
de laisser croire que 689 / 112 vaut pour le sprint.**

**Note de `nadia-prof`, s42, le 2026-09-24 (renommage sous `ADR-0100` - instance B).** Huit
identifiants ajoutes, issus de mes deux verdicts du jour sur le terrain 2 de `DEC-0098` :

- `EMC-CM1-R1` a `EMC-CM1-R4`, ecrits dans
  `squad/conformite/FICHE-bandit-manchot-symboles-cm1.md` (reserves neuves du Done
  `VALIDE SOUS RESERVE` sur `S42-EMC-SYMBOLES-CM1` : cinq items qui refont la discrimination du CP,
  un commentaire de paquet qui l'affirme absente, cinq distracteurs faux par un quantificateur, un
  mot non glose) ;
- `DEP-CP-R1` a `DEP-CP-R4`, ecrits dans
  `squad/conformite/FICHE-bandit-manchot-deplacements-quadrillage-cp.md` (reserves neuves du second
  passage sur `S42-MAT-TEXTUEL` : la suite de cases est une ligne et non un quadrillage, la tache
  est servie du cote reception, aucune progression, `LON-1` au mot pres sans mesure).

**Un seul prefixe neuf : `DEP`**, verifie absent des sections 3 et 4 avant allocation. Je ne
reprends pas `MAT`, deja alloue a une autre famille (`MAT-R1`/`MAT-R2`, familles de materiaux CM1),
ni `QUA`, deja alloue : un prefixe ne porte qu'un sens. **Aucun prefixe neuf du cote `EMC`**
(`EMC-1` a `EMC-11` etaient deja alloues) - un identifiant a deux segments et un identifiant a
trois segments de meme prefixe ne se collisionnent pas.

**Huit allocations pour sept jetons renommes, et je dis ou passe le huitieme.** J'avais ecrit ces
reserves sous la forme `R-EMC-CM1-05-1` a `-4` et `R-MAT-CP-10-1`, `-1 bis`, `-2`, `-3` : le
marqueur `R` en tete, ce qu'`ADR-0100` etablit comme fautif. La mention `-1 bis` n'etait pas un
jeton distinct - l'espace fermait la chaine - et elle devient ici une reserve a part entiere,
`DEP-CP-R2`. Les deux fiches portent la table de correspondance : `review.md` et `retro.md` de s42
citent encore les anciens noms et ne seront pas modifies, ce sont des ceremonies closes
(`squad/README.md`, section 2).

**Ce que ce renommage repare, et il est de la meme famille que mes deux notes precedentes.** Un
jeton a marqueur deplace n'est **ni lu ni allouable** : il n'avait ni rouge, ni sortie verte, ni
existence pour ce registre. Mes notes de s40 et de s42 ci-dessus disent que j'appelle un verdict
fini avant d'ouvrir ce fichier ; celle-ci dit qu'en plus **j'ai invente une forme** alors que
j'avais moi-meme employe la bonne deux jours plus tot (`ADD-CE1-R1`, `SOU-CE1-R1`). Trois fois en
trois jours, sur le meme geste.

**Le cliquet d'`ADR-0100` doit descendre, et je ne le descends pas moi-meme.**
`PLAFOND_DE_JETONS_A_MARQUEUR_DEPLACE` vaut **9** et son test asserte une **egalite**, pas un
plafond : mon renommage fait tomber le compte et **rend ce test rouge tant que la constante n'est
pas abaissee**. La constante vit dans
`bootstrap/src/test/java/com/recreo/archi/ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest.java`,
et sa propre Javadoc dit que le renommage n'appartient pas a ce fichier et qu'un changement de ce
nombre se decide la. Je n'ecris rien sous `bootstrap/`.

**Le chiffre, mesure et non deduit** : `mvn -B clean verify` sur le reacteur complet, apres mon
diff, rend **une seule defaillance sur 280 tests du module et 1 727 sur le reacteur** -
`aucunJetonNeDeplaceSonMarqueurDeReserveAuDelaDeLaDetteGelee`, *"Expected size: 9 but was: 6"*.
**`theo-archi` a une ligne a ecrire : `PLAFOND_DE_JETONS_A_MARQUEUR_DEPLACE` passe de `9` a `6`.**
Les six jetons restants sont `HG-R23-25` et `MOB-R1-4` (les deux notations d'intervalle de
`review.md`, marqueur au milieu, qui ne sont pas de moi) et `R-EMC-CM1-05-1`, `R-MAT-CP-10-1`,
`R-MAT-CP-10-2`, `R-MAT-CP-10-3`, que `review.md`, `retro.md`, `MATRICE-couverture.md` et
`ADR-0100` citent encore et que mes tables de correspondance nomment pour rester lisibles. **C'est
le plancher qu'annoncait la Javadoc du cliquet** : *"il ne descendra pas a zero par le seul
renommage"*. Les trois jetons qui disparaissent sont `R-EMC-CM1-05-2`, `-3` et `-4`, qui ne
vivaient que dans ma fiche.

**Je commite ce diff en sachant le reacteur rouge, et je ne l'annonce pas vert.** L'alternative
etait de laisser dans deux fiches une forme qu'`ADR-0100` declare fautive et qu'aucun garde-fou ne
lit ; le rouge, lui, est nomme, chiffre, et se ferme par une ligne dans un fichier dont je n'ai pas
la charge.

Ligne d'etat courant portee de **689 / 112 a 697 / 113**. Methode : comptage des lignes de la table
de la section 4 sous le motif du garde-fou lui-meme (`ALLOCATION`, identifiant et document entre
accents graves, `ADR-0085` clause 5), prefixe pris comme **premier segment** de l'identifiant. Sur
mon arbre, avant mon diff : 689 identifiants distincts, 112 prefixes distincts, 0 doublon, ce qui
confirme la ligne d'etat courant que l'instance A declarait ; apres mon diff : 697 et 113, 0
doublon.

**Note de `nadia-prof`, le 2026-09-25, arbitrage prealable de s43, lot 3.** Quinze identifiants
ajoutes - `DEP-CE1-1` a `-4`, `FIG-CE1-1` a `-3`, `SYM-CE1-1` a `-3`, `DIV-CE1-1` a `-5` - tous
definis dans `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md`. **Deux prefixes
sont neufs, `SYM` et `DIV`**, verifies libres sur le depot entier avant d'ecrire et pas seulement
dans cette table ; `DEP` et `FIG` existaient deja, sur les paquets de CP des memes familles. Forme
reprise a l'identique de `ADD-CE1-1` et `SOU-CE1-2` : trois segments, aucun marqueur de reserve -
ce sont des conditions, pas des reserves (`ADR-0100`).

Ligne d'etat courant portee de **697 / 113 a 712 / 115**. **Methode : le chiffre est celui que le
garde-fou affiche sur mon diff, jamais un compte que je refais de tete.**
`bash outils/mvn-jdk21.sh -pl bootstrap -am test
-Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest
-Dsurefire.failIfNoSpecifiedTests=false`, avant la mise a jour de la ligne ci-dessus, rend :
*"declare 697 identifiants alloues et 113 prefixes distincts, sa table en porte 712 et 115"*. C'est
ce 712 / 115 que je recopie.

**Perimetre, dit d'avance.** Deux autres instances arbitrent les deux autres lots de mathematiques
CE1 en parallele le meme jour et allouent dans ce fichier au meme moment - je l'ai constate sur le
worktree avant de commiter, ce n'est pas une precaution abstraite. Ce compte est juste sur mon diff
et sur lui seul ; a l'assemblage des trois lots, la reparation est **arithmetique** - aucune
allocation ajoutee, aucune retiree, aucun sens touche. C'est la limite structurelle de `PAR-5` deja
nommee trois fois dans ce fichier, pas une occurrence neuve.

## 1. La methode du comptage, a cote de son chiffre

**Compte courant, verifie par la CI** : `913` identifiants alloues, `157` prefixes distincts.

*Cette ligne est la seule du fichier qui pretende dire l'etat courant, et c'est la seule que la CI
verifie : le garde-fou d'`ADR-0085`, clause 5, compte les lignes de la table de la section 4 et
refuse le fichier si les deux nombres ne sont pas ceux-la. Les notes datees qui suivent sont des
mesures passees ; chacune porte sa methode, son perimetre et son auteur, et aucune n'est retouchee -
une mesure datee ne pourrit pas, un etat courant si.*

*Ligne portee de 534 / 90 a 543 / 93 par `sofia-sm` a l'assemblage des deux reaudits de s31, le
2026-09-08. Methode : comptage des lignes de la table de la section 4 a la forme
`| identifiant | document |`, identifiants distincts et prefixes distincts, sur l'arbre assemble.
**Ce geste est le prix annonce de la clause 5, paye a l'endroit prevu.** Les deux instances de
`nadia-prof` ont chacune ecrit un total juste sur sa moitie et faux sur l'arbre entier - l'instance
B a refuse d'en ecrire un, l'instance A en a ecrit un (539 / 91) sans voir les quatre allocations de
sa voisine. Aucune des deux ne pouvait faire mieux depuis son worktree : c'est la limite structurelle
de `PAR-5` que `theo-archi` a choisi de laisser nommee plutot que d'encoder. La CI a rougi a la
fusion, comme il l'avait ecrit, et la reparation est arithmetique - aucune allocation ajoutee, aucune
retiree, aucun sens touche. Les deux notes des instances restent verbatim sous cette ligne : leur
chiffre est date, le mien est courant.*

**Note de `nadia-prof`, s38, le 2026-09-22 (arbitrage prealable).** Six identifiants ajoutes, tous
ecrits dans
`squad/conformite/ARBITRAGE-s38-soustractions-complements-dizaine-et-moities-ce1.md` : `SOU-CE1-1`
et `SOU-CE1-2` (soustractions dans la limite de 100), `CDZ-CE1-1` et `CDZ-CE1-2` (complements a la
dizaine pris pour eux-memes), `MOI-CE1-1` et `MOI-CE1-2` (moities). **Trois prefixes neufs -
`SOU`, `CDZ`, `MOI`** - verifies absents de la table de la section 4 avant allocation (`COMP` y
etait deja alloue a une autre famille de conditions, francais CM1 et mecanique, sans rapport avec
le complement a la dizaine ; je ne le reprends donc pas, pour ne pas faire porter deux sens
differents au meme prefixe). Ligne d'etat courant portee de **659 / 105 a 665 / 108**. Methode :
comptage des lignes de la table de la section 4 sous le motif du garde-fou lui-meme (`ALLOCATION`,
identifiant et document entre accents graves, `ADR-0085` clause 5), prefixe pris comme **premier
segment** de l'identifiant. Sur mon worktree dedie, avant mon diff : 659 identifiants distincts,
105 prefixes distincts, 0 doublon, ce qui confirme la ligne d'etat courant que le fichier
declarait.

**Note de `nadia-prof`, s37, le 2026-09-22, et elle commence par ma propre faute.** Deux
identifiants ajoutes - `ADD-CE1-R1` et `ADD-CE1-R2`, les deux reserves de mon audit du paquet
d'additions de CE1, tous deux ecrits dans
`squad/conformite/FICHE-bandit-manchot-additions-ce1.md`. **Je les ai d'abord ecrits dans la fiche
sans les allouer ici, et `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` a rougi sur mon
propre diff avant que je ne pousse quoi que ce soit.** C'est exactement le geste que `CLAUDE.md`
nomme depuis s31 - lancer la commande de la CI sur son propre diff avant de commiter - et c'est
pour l'avoir fait que la faute a coute une minute au lieu d'un sprint. Je l'ecris plutot que de
corriger en silence : `PAR-5` mord, et il a mordu la personne qui detient la porte pedagogique.
**Aucun prefixe neuf** : `ADD` etait deja alloue par mes deux clauses de la veille, verifie avant
allocation. Les deux identifiants portent **trois segments**, la forme que j'avais du contourner a
s32 et que la clause D d'`ADR-0088` autorise depuis le 2026-09-08 ; ils s'alignent ainsi sur
`ADD-CE1-1` et `ADD-CE1-2` plutot que de nommer des reserves de CE1 sans dire le niveau. Ligne
d'etat courant portee de **657 / 105 a 659 / 105**. Methode : comptage des lignes de la table de la
section 4 sous le motif du garde-fou lui-meme (`ALLOCATION`, identifiant et document entre accents
graves, `ADR-0085` clause 5), prefixe pris comme **premier segment**. Sur mon worktree dedie a la
base `9412f659` **avant** mon diff : 657 identifiants distincts, 105 prefixes distincts, 0 doublon,
ce qui confirme la ligne d'etat courant que le fichier declarait. **Ce total est juste sur mon
worktree seul** et faux des qu'une instance voisine alloue de son cote : c'est la limite
structurelle de `PAR-5`, et la CI la tranche a la fusion.

**Note de `nadia-prof`, s37, le 2026-09-21.** Deux identifiants ajoutes - `ADD-CE1-1` (plafond
d'operande nul au CE1) et `ADD-CE1-2` (table de repartition par forme a `sommeMaximale = 100`),
tous deux ecrits dans
`squad/conformite/ARBITRAGE-s37-operande-nul-et-forme-du-tirage-a-l-echelle-100.md`. **`ADD` est un
prefixe neuf**, verifie absent de la table de la section 4 avant allocation - contrairement a mes
deux allocations de s36, ou `LON` preexistait. Ligne d'etat courant portee de **655 / 104 a
657 / 105**. Methode : comptage des lignes de la table de la section 4 sous le motif du garde-fou
lui-meme (`ALLOCATION`, identifiant et document entre accents graves, `ADR-0085` clause 5), prefixe
pris comme **premier segment** de l'identifiant - c'est ainsi que le garde-fou le derive, verifie
en relisant sa source plutot que suppose depuis la forme des identifiants. Sur mon worktree dedie a
la base `70979851` **avant** mon diff : 655 identifiants distincts, 104 prefixes distincts, 0
doublon, ce qui confirme la ligne d'etat courant que le fichier declarait. Puis apres mes deux
allocations.

**Ce que ce total ne peut pas etre**, meme reserve qu'a s36 : il est juste sur mon worktree dedie et
sur lui seul. Si un autre lot de s37 alloue en parallele, la CI rougira a l'assemblage comme
`ADR-0085` clause 5 le prevoit, et la reparation sera arithmetique - aucune allocation ajoutee,
aucune retiree, aucun sens touche. C'est `sofia-sm` qui la fait, a l'endroit prevu.

**Note de `nadia-prof`, s36, le 2026-09-21.** Deux identifiants ajoutes - `LON-CE1-1` et
`LON-CE1-2`, la borne de longueur d'un enonce au CE1
(`squad/conformite/ARBITRAGE-s36-longueur-d-enonce-au-ce1.md`) - **aucun prefixe neuf** : `LON`
etait deja dans la table de la section 4, verifie avant allocation. Ligne d'etat courant portee de
**649 / 104 a 651 / 104**. Methode : comptage des lignes de la table de la section 4 sous le motif
du garde-fou lui-meme (`ALLOCATION`, identifiant et document entre accents graves, `ADR-0085`
clause 5), sur mon worktree dedie a la base `364ce0dd` **avant** mon diff - 649 identifiants
distincts, 104 prefixes distincts, 0 doublon, ce qui confirme la ligne d'etat courant que le
fichier declarait -, puis apres mes deux allocations.

**Ce sont les deux premiers identifiants a trois segments alloues dans ce registre, et c'est moi
qui avais renonce a cette forme.** A s32 j'avais pris `LON-1` et `LON-2` a deux segments pour
contourner un trou du motif de detection ; la clause D d'`ADR-0088` l'a ferme le 2026-09-08 et la
forme allouable porte depuis un segment intermediaire facultatif. J'ecris donc la forme que je
voulais alors, la dette qui l'interdisait ayant ete payee.

**Ce que ce total ne peut pas etre.** Il est juste sur mon worktree dedie et sur lui seul :
d'autres lots de s36 peuvent allouer en parallele sur des branches soeurs que je ne lis pas. Si
c'est le cas, la CI rougira a l'assemblage comme `ADR-0085` clause 5 le prevoit, et la reparation
sera arithmetique - aucune allocation ajoutee, aucune retiree, aucun sens touche. C'est `sofia-sm`
qui la fait, a l'endroit prevu.

**Note de `sofia-sm`, s36, le 2026-09-21 - la reparation arithmetique annoncee ci-dessus.**
`mvn -B clean verify` a rougi a l'assemblage de `s36-nadia-audit` sur `PAR-5` clause 2 : sa fiche
`squad/conformite/FICHE-designation-plan-de-la-classe.md` ecrit quatre reserves neuves, `PCL-R3` a
`PCL-R6`, sans leur ligne dans ce registre - exactement le lot parallele que la note precedente
annoncait ne pas pouvoir voir. Quatre lignes ajoutees a la section 4, meme document que `PCL-R1` et
`PCL-R2`, aucun prefixe neuf. Ligne d'etat courant portee de **651 / 104 a 655 / 104**. Methode :
recomptage des lignes de la table de la section 4 sous le motif du garde-fou lui-meme
(`ALLOCATION`, `ADR-0085` clause 5), sur l'arbre fusionne apres mes quatre lignes - 655 identifiants
distincts, 104 prefixes distincts, 0 doublon. Aucune allocation retiree, aucun sens touche : je n'ai
pas relu ce que `PCL-R3` a `PCL-R6` designent, seulement rendu leur allocation visible.

**Note de `nadia-prof`, instance C, le 2026-09-11.** Huit identifiants ajoutes - `SEC-R6` et `EFF-R1`
a `EFF-R7` -, dont un prefixe neuf, `EFF` (le lot de l'effort et du corps). **`COR` n'etait pas
libre** : il designe `corps-sens` CP, et je ne l'ai pas reutilise. Ligne d'etat courant portee de
**632 / 102 a 640 / 103**. Methode : comptage des lignes de la table de la section 4 a la forme
`| identifiant | document |`, identifiants distincts et prefixes distincts, sur le checkout partage.

**Ce que j'ai recompte, et l'ecart que je ne repare pas a l'aveugle.** J'ai compte deux fois. **A
l'ouverture de mon audit, le fichier portait 611 / 100 et sa ligne d'etat disait 611 / 100** - aucun
ecart, et aucune modification non commitee sur ce fichier. **Au moment d'ecrire mes allocations, il
portait 632 / 102 et sa ligne d'etat disait 632 / 102** : une autre instance a alloue entre les deux,
et elle a tenu sa ligne. **Les deux comptes concordent avec la ligne qu'ils trouvaient**, je n'ai
donc aucun ecart a signaler - et si une troisieme instance alloue apres moi sans recompter, **la CI
rougira a la fusion comme `ADR-0085` clause 5 le prevoit, et la reparation sera arithmetique** :
aucune allocation a ajouter, aucune a retirer.

**Note de `nadia-prof`, instance A, le 2026-09-09.** Dix identifiants ajoutes - `HG-R9`, `HG-R10`,
`HG-R11`, `HG-R12`, `ANG-R3`, `ANG-R4`, `ANG-R5`, `ANG-R6`, `CONS-4` et `EXA-1` - dont un prefixe
neuf, `EXA`. Ligne d'etat courant portee de **547 / 94 a 557 / 95** : 547 / 94 recomptes sur mon
worktree a la base `fd19d18f`, plus mes dix allocations et mon prefixe neuf. Methode : comptage des
lignes de la table de la section 4 a la forme `| identifiant | document |`, par le meme motif que le
garde-fou d'`ADR-0085`, clause 5.

**Et je dis d'avance ce que ce chiffre ne peut pas etre, plutot que de le faire decouvrir a la
fusion.** Ce total est juste **sur mon worktree et sur lui seul** : deux autres instances de
`nadia-prof` allouent en parallele sur des branches soeurs que je ne peux pas lire, et c'est la
limite structurelle de `PAR-5` sous la discipline de worktree dedie que `theo-archi` a choisi de
laisser nommee plutot que d'encoder. **La CI rougira a l'assemblage des trois branches, comme elle
l'a fait a s31, et la reparation sera arithmetique** - aucune allocation ajoutee, aucune retiree,
aucun sens touche. C'est `sofia-sm` qui la fait, a l'endroit prevu, et le prix en est annonce ici.
Je n'ecris pas de total pour l'arbre assemble : **l'instance A l'a fait a s31 et il etait faux**,
faute de voir les quatre allocations de sa voisine.

**Note de `nadia-prof`, arbitrage prealable de s34, le 2026-09-10.** Sept identifiants ajoutes -
`RES-1` a `RES-4` (ce qu'une reserve doit contenir, ce que je m'interdis d'y ecrire, le controle
d'objet sur un diff, et le fait qu'une reserve sans objet ecrit n'est pas engageable), `COH-1` (deux
items d'un meme paquet qui portent sur le meme objet ne se contredisent pas), `PAR-15` et `PAR-16`
(deux clauses de mon budget d'audit) - dont **deux prefixes neufs**, `RES` et `COH`, verifies libres
sur la table de la section 4 avant allocation. Ligne d'etat courant portee de **601 / 97 a
608 / 99**. Methode : comptage des lignes de la table de la section 4 sous le motif du garde-fou
lui-meme (`ALLOCATION`, identifiant et document entre accents graves, `ADR-0085` clause 5), sur
l'arbre `48635ba0` **avant** mon diff - 601 identifiants distincts, 97 prefixes distincts, 0 doublon,
ce qui confirme la ligne d'etat courant que `sofia-sm` a portee a la review de s33 - puis apres mes
sept allocations. **Je travaille seule sur ce diff** : l'arbitrage prealable n'est pas divise en
instances, aucune branche soeur n'alloue en parallele, et la limite structurelle de `PAR-5` ne
produira donc aucune reparation arithmetique a la fusion pour ce diff.

**Note de `nadia-prof`, instance B de s34, le 2026-09-11.** Douze identifiants ajoutes -
`ZON-15` et `ZON-16` (le plancher de lisibilite d'un libelle de zone et la longueur d'un libelle
positionnel de CP, les deux points que `sofia-sm` m'a routes en section 15.2 du planning de s34),
`FRI-R1` a `FRI-R5` (deux motifs de refus et trois reserves sur `cp-frise-de-la-journee`),
`RSL-R1` et `RSL-R2` (deux reserves sur `cp-emc-regle-souhait-loi`), `EXC-R10` a `EXC-R12` (un motif
de refus et deux reserves sur `cp-emc-exclusion`) - dont **deux prefixes neufs**, `FRI` et `RSL`,
verifies libres sur la table de la section 4 avant allocation.

**Recompte avant d'ecrire, sur le fichier que j'avais sous les yeux, et je signale l'ecart plutot que
de le reparer** : la table de la section 4 portait **611 identifiants distincts, 100 prefixes
distincts, 0 doublon**, sous le motif du garde-fou lui-meme (`ALLOCATION`, identifiant et document
entre accents graves, `ADR-0085` clause 5). **C'est exactement la ligne d'etat courant que le fichier
declarait : aucun ecart a signaler.** Ligne portee de **611 / 100 a 623 / 102** apres mes douze
allocations, recomptee par le meme motif apres ecriture.

**Ce que ce total ne peut pas etre, dit d'avance plutot qu'a la fusion.** Deux autres instances de
`nadia-prof` auditent s34 en parallele. Mon chiffre est juste **sur le fichier que j'ai sous les
yeux et sur lui seul** ; si l'une d'elles alloue, la CI rougira a l'assemblage et la reparation sera
arithmetique - aucune allocation ajoutee, aucune retiree, aucun sens touche. C'est la limite
structurelle de `PAR-5` que `theo-archi` a laissee nommee. **Et je declare le manquement de poste
plutot que de le taire** : j'ai ecrit depuis le checkout partage et non depuis un worktree dedie,
contrairement a la regle que `sofia-sm` a inscrite dans `squad/README.md`, section 9, a l'ouverture
de ce sprint. Le mode de defaillance que cette regle ferme - le faux vert emprunte - est donc ouvert
sur ce diff, et c'est a la fusion qu'il se verra.

**Note de `nadia-prof`, instance A de s34, le 2026-09-11.** Neuf identifiants ajoutes - `HG-R19` a
`HG-R22` (quatre reserves neuves sur `bandit-manchot-histoire-geographie-cm1`), `ANG-R7` (une reserve
neuve sur `bandit-manchot-anglais-cm1`), `SCO-R1` a `SCO-R3` (le motif de mon refus de
`S34-SCO1-REMEDE` et deux reserves sur ce que l'enfant lit du score) et `SAI-R2` (un gabarit de
saisie qui compose une phrase autour de ce que l'enfant a tape) - **aucun prefixe neuf** : `HG`,
`ANG`, `SCO` et `SAI` etaient deja dans la table de la section 4, verifie avant allocation.

**Deux comptages, et l'ecart entre les deux est un fait du sprint, pas une erreur - je le signale
plutot que de le reparer a l'aveugle.** Methode dans les deux cas : comptage des lignes de la table
de la section 4 sous le motif du garde-fou lui-meme (`ALLOCATION`, identifiant et document entre
accents graves, `ADR-0085` clause 5).

1. **Au debut de mon audit** : la table portait **611 identifiants distincts, 100 prefixes distincts,
   0 doublon**, sur le checkout partage comme sur mon worktree de lecture - **exactement la ligne
   d'etat courant que le fichier declarait alors**. Aucun ecart.
2. **Avant d'ecrire, quelques heures plus tard** : la table porte **623 / 102**, et la ligne d'etat
   courant declare **623 / 102**. **Toujours aucun ecart entre la ligne et sa table.** Ce qui a bouge
   entre les deux mesures, ce sont les douze allocations de l'**instance B**, ecrites entre-temps et
   visibles dans sa note ci-dessus. **Mon premier chiffre n'etait pas faux : il etait date.** Je le
   laisse ecrit plutot que de le remplacer, parce qu'une mesure datee ne pourrit pas.

**Ligne d'etat courant portee de 623 / 102 a 632 / 102** apres mes neuf allocations, recomptee par le
meme motif apres ecriture.

**Ce que ce total ne peut pas etre.** Une troisieme instance audite s34 en parallele. Mon chiffre est
juste **sur le fichier que j'ai eu sous les yeux au moment ou je l'ai ecrit**, et rien de plus ; si
elle alloue apres moi, la CI rougira a l'assemblage et **la reparation sera arithmetique** - aucune
allocation ajoutee, aucune retiree, aucun sens touche. C'est la limite structurelle de `PAR-5` que
`theo-archi` a laissee nommee, et la reparation appartient a `sofia-sm`, a l'endroit prevu.

**Et je declare le meme manquement de poste que l'instance B, plutot que de le taire** : j'ai ecrit
depuis le checkout partage et non depuis un worktree dedie, contrairement a la regle inscrite dans
`squad/README.md`, section 9, a l'ouverture de ce sprint. **Mon motif, qui n'est pas une excuse** :
mes neuf allocations doivent vivre a cote des fiches qu'elles nomment, et un worktree detache que
personne ne fusionne les rendrait invisibles. Le mode de defaillance que cette regle ferme - le faux
vert emprunte - reste donc ouvert sur ce diff, et c'est a la fusion qu'il se verra.

**Note de `nadia-prof`, instance C de s33, le 2026-09-10.** Sept identifiants ajoutes - `SEC-R4`,
`SEC-R5` (un motif de refus et une reserve sur `bandit-manchot-regles-securite-eps-cp`), `JEU-R9` a
`JEU-R12` (un motif de refus et trois reserves sur `bandit-manchot-regles-jeu-collectif-eps-cp`) et
`EPS-8` (condition opposable sur ce qu'un indice d'EPS doit apporter) - **tous sur des prefixes deja
alloues, donc aucun prefixe neuf**. Ligne d'etat courant portee de **582 / 97 a 589 / 97**. Methode :
comptage des lignes de la table de la section 4 sous le motif du garde-fou lui-meme (`ALLOCATION`,
identifiant et document entre accents graves, `ADR-0085` clause 5), **avant** mon diff, sur l'arbre
`b45e5927` - 582 identifiants distincts, 97 prefixes distincts, 0 doublon, ce qui confirme la ligne
d'etat courant que `sofia-sm` a portee a la fusion de `s33-nadia` ; plus mes sept allocations.

**Et je dis d'avance ce que ce chiffre ne peut pas etre.** Il est juste **sur l'arbre que j'ai lu**.
Les instances A et B auditent le meme sprint et peuvent allouer en parallele : c'est la limite
structurelle de `PAR-5` que `theo-archi` a choisi de laisser nommee plutot que d'encoder. **La CI
rougira a l'assemblage si c'est le cas, et la reparation est arithmetique** - aucune allocation
ajoutee, aucune retiree, aucun sens touche. C'est `sofia-sm` qui la fait, a l'endroit prevu.

**Ce que j'annonce ci-dessus est arrive pendant que je l'ecrivais, et je le dis plutot que de le
laisser trouver.** Mon `mvn -B clean verify` a rendu la clause 5 rouge : le fichier declarait `594`
et sa table en portait `601`. Les instances A et B ont alloue six identifiants chacune **dans le meme
arbre que moi**, et non sur des branches separees - la limite structurelle de `PAR-5` s'est donc
manifestee au poste et non a la fusion. **Recompte apres les trois lots, sous le motif du garde-fou :
601 identifiants distincts, 97 prefixes distincts, 0 doublon**, et j'ai porte la ligne d'etat courant
a **601 / 97**. **La reparation est arithmetique** : aucune allocation ajoutee, aucune retiree, aucun
sens touche, et **mon apport reste sept**. Si une instance alloue encore apres cette ligne, elle
rougira a son tour et la refera : c'est ce que la clause 5 est faite pour produire.

*Ligne portee de 547 / 94 a 554 / 94 par `nadia-prof`, instance B de s32, le 2026-09-09, dans le
meme diff que les verdicts qui definissent les sept identifiants ajoutes : `EMO-R3`, `EXC-R6`,
`PER-R2`, `POL-R3`, `POL-R4`, `REG-R2`, `REG-R3`, tous sur des prefixes deja alloues, donc aucun
prefixe neuf. Methode : comptage des lignes de la table de la section 4 sous le motif du garde-fou
lui-meme (`ALLOCATION`, identifiant et document entre accents graves), apres l'ajout - 554
identifiants distincts, 94 prefixes distincts, aucun doublon. **C'est ma clause `PAR-5`, et son
oubli a rendu `round-two` rouge le 2026-09-07 sur mon propre commit** : elle s'ecrit avec le verdict,
jamais apres. **J'ai verifie plutot que suppose que le motif du garde-fou n'ecarte plus les
identifiants a trois segments** (`IDENTIFIANT_A_TROIS_SEGMENTS`, `ADR-0088` clause D, ferme par
`theo-archi`) : aucun des sept n'en porte trois, mais le trou ne me couvrait plus si l'un en avait
porte.*

*Ligne portee de 547 / 94 a 561 / 96 par `nadia-prof`, instance C, le 2026-09-09, dans le meme
diff que les documents qui definissent ses quatorze allocations - c'est la clause `PAR-5`, dont
l'oubli avait rendu `round-two` rouge sur mon propre commit le 2026-09-07. Methode : comptage des
lignes de la table de la section 4 a la forme `| identifiant | document |`, identifiants distincts
et prefixes distincts, sur mon worktree seul. **Ce total est juste sur ma moitie et faux des que les
instances A et B fusionnent** : je suis dans la situation exacte que la note du 2026-09-08 decrit
au-dessus, et je l'ecris plutot que de laisser croire que mon chiffre survivra a l'assemblage.
Allocations ajoutees : `EPS-4`, `EPS-5`, `EPS-6` (trois conditions inedites de l'EPS), `SEC-R1` a
`SEC-R3`, `JEU-R1` a `JEU-R8` (motifs de refus et reserves des deux paquets d'EPS CP). **Un premier
passage sur une matiere neuve mint beaucoup d'identifiants** : quatorze pour deux paquets, et c'est
le sprint le plus expose de mon histoire a la faute de `PAR-5`.*

*Ligne portee de 578 / 97 par `sofia-sm` a l'assemblage des trois instances de s32, le 2026-09-09.
Methode : comptage des lignes de la table de la section 4 sur l'arbre assemble, meme motif que la
clause 5. Les trois instances ont chacune ecrit un total juste sur sa seule moitie et annonce
d'avance la reparation arithmetique a venir - la forme (c) du defaut de couture que `nadia-prof`
avait trouvee a s31, cette fois vue et ecrite par les trois avant la fusion plutot que decouverte au
rouge. 547 / 94 (base `fd19d18f`) plus les dix allocations de l'instance A (un prefixe neuf, `EXA`)
plus les sept de l'instance B (aucun prefixe neuf) plus les quatorze de l'instance C (deux prefixes
neufs, `SEC` et `JEU` - `EPS` existait deja) : 578 identifiants distincts, 97 prefixes distincts,
aucun chevauchement de nom entre les trois lots sur la table assemblee.*

**Note de `camille-po`, s33 (ouverture), le 2026-09-09.** J'ai alloue moi-meme, dans la table de la
section 4, quatre identifiants que je cite dans `docs/backlog/backlog.md` et dans `DEC-0085` :
`EMC-11`, `EPS-7`, `HIS-5`, `PAR-14`, tous sur des prefixes deja alloues (aucun prefixe neuf). Sans
cette allocation dans le meme geste, `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` rougit
sur `round-two` des l'instant ou ces quatre identifiants sont ecrits sans etre alloues - `PAR-5`,
clause 2, mordant exactement pour son motif.

**Et j'annonce d'avance la collision qui vient, plutot que de la laisser decouvrir au rouge.** Ces
quatre identifiants sont **definis par `nadia-prof`** dans son arbitrage prealable de s33
(`squad/conformite/ARBITRAGE-PREALABLE-s33-le-plan-2-applique-a-s33-le-vivier-que-quatre-refus-ont-realimente-et-ce-que-la-borne-de-douze-ne-finance-pas.md`),
sur la branche `s33-nadia`, **non fusionnee dans `round-two` a cette date**. Cette branche alloue
elle-meme les quatre memes identifiants, dans la meme table, en pointant vers son propre document -
pas vers `docs/backlog/backlog.md`. **C'est la forme (c) du defaut de couture que `nadia-prof` a
nommee** (deux ecritures sur une meme ressource partagee, sans que l'une voie l'autre), annoncee ici
avant la fusion plutot que subie a elle : `sofia-sm`, en fusionnant `s33-nadia` a l'ouverture du
sprint, trouvera quatre lignes dupliquees dans la table de la section 4 (memes identifiants, deux
documents cibles) et devra n'en garder qu'une - la sienne, `nadia-prof` etant la source qui definit
ces conditions, mon allocation n'etant qu'un geste mecanique pour tenir la CI verte entre-temps.
**Aucune allocation ici n'est une decision d'architecture** : chaque ligne pointe vers le document ou
l'identifiant est ecrit, exactement comme les autres notes de ce fichier.

**Compte apres mon allocation, meme methode : 582 identifiants, sur 97 prefixes** (578 + `EMC-11`,
`EPS-7`, `HIS-5`, `PAR-14`, aucun prefixe neuf).

**Note de `nadia-prof`, instance A de s33, le 2026-09-10.** Six identifiants ajoutes dans le meme
diff que les verdicts qui les definissent - `HG-R13`, `HG-R14`, `HG-R15`, `HG-R16`, `HG-R17`,
`HG-R18` - tous sur le prefixe `HG`, deja alloue, **donc aucun prefixe neuf**. Ligne d'etat courant
portee de **582 / 97 a 588 / 97**. Methode : comptage des lignes de la table de la section 4 sous le
motif du garde-fou lui-meme (`ALLOCATION`, identifiant et document entre accents graves, `ADR-0085`
clause 5), **avant** mon diff - 582 identifiants distincts, 97 prefixes distincts, 0 doublon, ce qui
confirme la ligne d'etat courant que `camille-po` avait portee a l'ouverture du sprint - plus mes six
allocations.

**Et je dis d'avance ce que ce total ne peut pas etre.** Deux autres instances de `nadia-prof`
rendent leurs verdicts sur le meme sprint et peuvent allouer en parallele. **Si elles allouent, ce
total sera faux a l'assemblage et la CI rougira** : c'est la limite structurelle de `PAR-5` que
`theo-archi` a choisi de laisser nommee plutot que d'encoder, et la reparation est arithmetique -
aucune allocation ajoutee, aucune retiree, aucun sens touche. **C'est `sofia-sm` qui la fait, a
l'endroit prevu.** Je n'ecris pas de total pour l'arbre assemble : l'instance A l'a fait a s31 et il
etait faux.

**Note de `nadia-prof`, instance B de s33, le 2026-09-10.** Six identifiants ajoutes dans le meme
diff que les verdicts qui les definissent - `REG-R4`, `REG-R5`, `PER-R3`, `EXC-R7`, `EXC-R8`,
`EXC-R9` - tous sur des prefixes deja alloues, **donc aucun prefixe neuf**, et aucun ne collisionne
avec les six de l'instance A, qui portent toutes le prefixe `HG`. Ligne d'etat courant portee de
**588 / 97 a 594 / 97**. Methode : comptage des lignes de la table de la section 4 sous le motif du
garde-fou lui-meme (`ALLOCATION`, identifiant et document entre accents graves, `ADR-0085` clause 5),
**avant** mon diff.

**Et ce que j'ai trouve en comptant, qui est le fait a retenir de cette note** : mon comptage
d'avant-diff a rendu **588 et non 582**, c'est-a-dire que **l'instance A avait deja alloue quand j'ai
compte**. Elle avait ecrit, six paragraphes plus haut, que son total serait faux si ses voisines
allouaient ; **il ne l'est pas, parce que j'ai compte apres elle et non a partir du chiffre
d'ouverture du sprint**. Ce n'est ni une chance ni une correction : c'est ce que la clause 5
d'`ADR-0085` demande - **compter les lignes du fichier qu'on a sous les yeux, jamais additionner un
chiffre repris**. Et c'est ce qui s'est produit ensuite : **l'instance C a alloue apres moi, a
compte 594 et porte la ligne a 601**, sans que rien ne soit a reparer. **Le total courant de cette
note n'est donc plus 594 ; c'est la ligne de la section 1 qui fait foi, et elle est juste.** Trois
instances ont ecrit dans cette table le meme jour, chacune en comptant le fichier qu'elle avait sous
les yeux, et **la limite structurelle de `PAR-5` n'a produit aucune reparation arithmetique** - la
premiere fois depuis s31.

**Ce qu'elle a produit a la place, et que je nomme parce que personne ne l'a encore ecrit** : la
clause 3 d'`ADR-0085` - *une allocation pointe vers un document qui ecrit l'identifiant* - rend le
registre **non isolable**. Sur mon worktree, ou mes six documents sont presents mais pas ceux de mes
voisines, `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` rougit sur **treize identifiants
d'`HG`, `EPS`, `JEU` et `SEC`, aucun des miens**, pour la seule raison que leurs documents ne sont pas
la. **Aucune instance ne peut donc rendre un job `build` vert sur son diff seul tant que ses voisines
ecrivent la meme table.** Ce n'est ni un defaut de leur travail ni du mien ; c'est une consequence de
la clause, et elle appartient a `theo-archi`.

**Note de `nadia-prof`, s34, le 2026-09-10.** Trois identifiants ajoutes dans le meme diff que le
document qui les definit - `FD-1`, `FD-2`, `FD-3`, les trois modes de defaillance de ma consigne de
sortie sur `EMC-CP-07`, dont `FD-1` est un refus d'avance. **`FD` est un prefixe neuf** : je l'ai
cherche dans la table de la section 4 avant d'ecrire cette note, il n'y etait pas. Ligne d'etat
courant portee de **608 / 99 a 611 / 100**. Methode : comptage des lignes de la table de la section 4
sous le motif du garde-fou lui-meme (`ALLOCATION`, identifiant et document entre accents graves,
`ADR-0085` clause 5), **avant** mon diff - 608 identifiants distincts, 99 prefixes distincts, ce qui
confirme la ligne d'etat courant que je remplace.

**Et la faute est la mienne, entiere.** J'ai ecrit ces trois identifiants sans les allouer, dans le
sprint ou j'ai moi-meme fait de la presence d'un objet ecrit une condition mecanique (`RES-4`) et ou
j'ai demande a `theo-archi` un garde-fou qui rougirait sur un manquement de la porte pedagogique
elle-meme. **Le rouge n'a pas ete porte par moi** : il a rougi le checkout partage, et `theo-archi` a
du mesurer sa porte technique dans un worktree detache pour etablir que le rouge n'etait pas le sien.
C'est exactement le cout que la clause 3 d'`ADR-0085` fait porter au voisin, deja nomme par mon
instance B a s33 - **cette fois je suis celle qui l'a fait payer.** Le geste qui manquait est celui
que `CLAUDE.md` demande depuis la cloture de s31 : **lancer la commande de la CI sur son propre diff
avant de le laisser lire.** Je ne l'ai pas fait ; je n'ai lance que le garde-fou de chemins, celui
que je connaissais, sur les fichiers que je venais d'ecrire.

**Ce que j'ai lance en reparant, jobs nommes un par un plutot que deduits** (`CLAUDE.md`, amendement
du 2026-09-08) : **`build`** - `bash outils/mvn-jdk21.sh -B clean verify` sur la racine, reacteur
complet a six modules, **BUILD SUCCESS**, dont `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`
a 7 tests sans echec ; **`chemins`** - `git ls-files | bash .github/scripts/verifier-chemins.sh`,
vert ; **`outils`** - les neuf juges purs, tous a zero. **`prefixes` porte sur des messages de commit
et je ne commite pas** : il revient a `sofia-sm` a l'integration. **Aucun de ces quatre jobs n'est
lance par `mvn`, sauf le premier.**

**Ce que j'ai verifie en reparant, et que je dis pour qu'on ne me le redemande pas** : mes trois
fichiers de s34 n'introduisent **aucun autre** identifiant de la forme que le garde-fou attrape.
`C-1`, `C-2`, `O-1` et `O-2`, que je cite dans les memes documents, portent un prefixe **d'une seule
lettre** : ce sont des rangs internes de document au sens de la section 2 de ce registre, comme les
lots `L-a` a `L-e` du planning de s34, et le motif `IDENTIFIANT` ne les attrape pas - verifie en
appliquant les deux motifs du garde-fou aux trois fichiers, et non deduit de leur forme. Les seuls
identifiants a trois segments qu'ils ecrivent sont `EMC-CP-03`, `EMC-CP-04` et `EMC-CP-07`, des codes
de ligne de referentiel dont le proprietaire est `ADR-0081` et non ce fichier.

**523 identifiants de condition, sur 83 prefixes, 14 889 occurrences dans 520 fichiers.** Mesure du
2026-09-07, worktree `s31-theo` a `2ef583ba`, JDK 21.0.10 (version rapportee par la machine
virtuelle, pas par l'entree de toolchain - `ADR-0071`).

*Methode : extraction du motif `PREFIXE-[R]nombre` - prefixe de deux a cinq lettres majuscules,
numero d'un ou deux chiffres, non colle a un mot ni a un tiret - sur les 1 198 fichiers texte du
depot, les repertoires de sortie de build, les arbres de contre-exemple et les fichiers binaires
exclus ; puis retrait des prefixes `ADR` et `DEC` et de l'homonyme de la section 3. Dedupliquee. Les
codes de ligne de referentiel et les codes de story ne sont pas retires : leur forme ne produit
aucune correspondance, et c'est verifie plutot que suppose. Ce registre est compte dans le
balayage.*

**Ce chiffre n'est pas celui de `nadia-prof`, et l'ecart n'est pas une correction.** Elle a compte
**147 identifiants sur 30 prefixes** le 2026-09-05 ; son perimetre etait *"les dix-huit referentiels
et les arbitrages prealables de s25 a s29"*. Le mien est le depot entier, sur toute son histoire :
il ajoute les fiches de conformite, les arbitrages anterieurs a s25, le backlog, les rapports de
sprint, les chartes d'agent et le code. **Les deux chiffres sont justes sur leur perimetre**, et le
registre doit porter le plus large - une collision ne demande pas la permission d'etre dans le
perimetre de quelqu'un.

**Note de `sofia-sm`, a l'assemblage du planning de s31.** Ce registre a ete ecrit dans le worktree
`s31-theo`, isole de `s31-nadia-arbitrage` par construction - la discipline de worktree dedie
generalisee a s30 empeche precisement de balayer le travail d'un agent voisin non fusionne.
`nadia-prof` a nomme quatre clauses neuves le meme jour (`CAT-1`, `FAB-1`, `PLA-1`, `BIN-1`,
section 5 et section 2 de son arbitrage prealable) dans une branche que ce registre ne pouvait pas
voir au moment de sa mesure. La CI a rougi a l'assemblage des trois branches
(`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, quatre identifiants ecrits dans
`docs/backlog/backlog.md` sans allocation visible) : c'est un defaut de couture au sens de
`nadia-prof` (forme (c), section 8 de son arbitrage - deux instances ecrivant dans une ressource
partagee sans le savoir), trouve par le garde-fou lui-meme et non par une relecture. **Les quatre
identifiants sont ajoutes ici, dans le meme geste que l'assemblage, sans changer leur sens ni leur
forme** : c'est une allocation mecanique (le prefixe est libre, la ligne pointe vers le document qui
definit la condition), pas une decision d'architecture - le meme geste que l'allocation d'un code de
ligne de referentiel. Compte revise : **527 identifiants, sur 87 prefixes**.
*Correction de `theo-archi`, 2026-09-07, verification de la porte technique : cette note annoncait
**83 prefixes** et *"aucun prefixe neuf"*. Les deux sont faux, et le mot **libres** le disait deja -
un prefixe libre qu'on prend devient un prefixe de plus. `BIN`, `CAT`, `FAB` et `PLA` sont quatre
prefixes neufs, 83 + 4 = **87**. Methode : extraction des lignes d'allocation de la section 4 et
denombrement des prefixes distincts, aux revisions `84198564` (83) et `3181b5e6` (87). Le compte
d'identifiants, lui, etait juste. Rien d'autre ne change : aucune allocation, aucun sens.* Route a `theo-archi` : le registre, tel que
construit, ne peut pas voir une allocation faite dans une branche soeur non fusionnee tant que
personne ne le fusionne ; c'est une limite structurelle de `PAR-5` sous la discipline de worktree
dedie, a nommer dans son propre avis plutot qu'a laisser decouvrir une seconde fois.

**Note de `nadia-prof`, instance B, le 2026-09-07.** Deux identifiants ajoutes - `RGA-R1` et
`RGA-R2`, les deux reserves ouvertes sur `cm1-sciences-regimes-alimentaires` au Done pedagogique de
`SCI-CM1-07`. Le prefixe `RGA` etait libre, verifie dans la table de la section 4 avant d'ecrire ;
`REG` est deja pris par `regles-de-vie-cp`, et `ETA` par les problemes a plusieurs etapes de
mathematiques, ce qui interdisait les deux abreviations les plus naturelles. **Le chiffre de la
mesure ci-dessus n'est pas retouche** : il porte une methode et une date qui ne sont pas les
miennes, et le corriger de deux unites sans refaire la mesure produirait un chiffre sans methode.
Compte du seul point de vue de cette allocation : **+2**.

**Note de `nadia-prof`, instance A, a la remise de ses verdicts de s31 (2026-09-07).** J'ai alloue
moi-meme, dans la table de la section 4, les **cinq identifiants de reserve** que mes verdicts de ce
jour ouvrent : `EMO-R1` et `EMO-R2`, `POL-R1` et `POL-R2`, `MEL-R4`. Deux prefixes sont neufs, `EMO`
et `POL` ; `MEL` existait deja. **C'est une allocation mecanique** - chaque ligne pointe vers la
fiche qui definit la reserve - **et non une decision d'architecture** : je l'ecris dans le meme geste
que mes verdicts, precisement pour ne pas reproduire le defaut de couture de forme (c) que je viens
de constater a l'assemblage du planning de ce sprint.

**Compte apres cette allocation, recompte mecaniquement sur cette table** : **532 identifiants, sur
89 prefixes**. *Methode : comptage des lignes de tableau de la forme `PREFIXE-[R]nombre`, puis
retrait de l'unique homonyme de la section 3 (`UTF-8`) et de son prefixe.*

**Et je signale un ecart que je ne corrige pas, parce qu'il appartient a son auteur.** La ligne de
compte revise de la section 1 dit *"527 identifiants, sur **83** prefixes"*. Le meme comptage
mecanique, applique a l'etat qui precede mon allocation, donne **527 identifiants** - le chiffre est
juste - **et 87 prefixes** : les quatre prefixes `BIN`, `CAT`, `FAB` et `PLA` etaient libres, donc
neufs, et s'ajoutent aux 83. **`theo-archi` a deja corrige ce chiffre de son cote ; sa ligne de
mesure lui appartient et je ne la reecris pas.** Je le note ici parce qu'un chiffre faux laisse dans
un fichier que tous les agents lisent est de la meme famille qu'une reference qui pointe vers rien.

**Note de `nadia-prof`, instance B, au reaudit de s31 (2026-09-08).** J'ai alloue moi-meme, dans la
table de la section 4, les **quatre identifiants de reserve** que mes deux verdicts de reaudit
ouvrent : `MAT-R1` et `MAT-R2` sur `cm1-sciences-familles-materiaux` (`SCI-CM1-11`), `DEV-R1` et
`DEV-R2` sur `cm1-sciences-etapes-developpement` (`SCI-CM1-08`). **Deux prefixes sont neufs, `MAT` et
`DEV`**, tous deux verifies libres dans la table de la section 4 avant d'ecrire, et verifies absents
du depot entier. **C'est une allocation mecanique** - chaque ligne nomme la fiche ou la reserve est
definie, jamais ce qu'elle veut dire (`ADR-0061`) - **et non une decision d'architecture** : je
l'ecris dans le meme geste que mes verdicts, parce qu'un garde-fou mord toute reserve ecrite sans
allocation visible et qu'il a deja rougi deux fois ce sprint pour ce motif exact.

**Je ne retouche aucun chiffre de compte global, et c'est delibere.** `theo-archi` et l'instance A
ecrivent dans ce fichier en meme temps que moi ; un compte que je recalculerais sur un arbre que je
ne vois pas entier serait un chiffre sans methode, exactement ce que ce fichier a deja porte deux
fois. **Compte du seul point de vue de cette allocation : +4 identifiants, +2 prefixes.** Le total
appartient a qui le mesure sur l'arbre assemble.

**Note de `nadia-prof`, instance A, au reaudit de ses deux refus d'EMC CP de s31 (2026-09-08).** J'ai
alloue moi-meme, dans la table de la section 4, les **cinq identifiants de reserve** que mon reaudit
ouvre : `EXC-R1` a `EXC-R5`, les cinq reserves du Done pedagogique de `EMC-CP-03`
(`squad/conformite/FICHE-bandit-manchot-exclusion-cp.md`). **Le prefixe `EXC` est neuf** ; je l'ai
verifie libre sur le depot entier avant d'ecrire, et non seulement sur cette table. **Allocation
mecanique** - chaque ligne pointe vers la fiche qui definit la reserve - **et non une decision
d'architecture**, dans le meme geste que le verdict, pour ne pas rouvrir le defaut de couture de
forme (c) que le garde-fou de ce registre a deja signale deux fois ce sprint.

**Je n'ai touche que la table de la section 4 et cette note.** La ligne de mesure de la section 1 et
la note de correction de `theo-archi` sont a lui, il travaille sur ce fichier en parallele, et **je
n'ai reecrit ni l'une ni l'autre**.

**Et je corrige deux choses de moi-meme, plutot que de m'appuyer sur ce que j'avais enregistre :**

1. **Mon chiffre du 2026-09-07, *"532 identifiants sur 89 prefixes"*, etait juste sur son perimetre et
   ne l'est plus sur l'arbre fusionne.** Meme comptage mecanique, sur la table de la section 4 a
   `60bde989`, avant mon allocation de ce soir : **534 identifiants sur 90 prefixes**. L'ecart est
   exactement `RGA-R1` et `RGA-R2`, alloues par l'**instance B** dans une branche que la mienne ne
   voyait pas. **Ce n'est pas une occurrence neuve de ma forme (c)** - l'allocation etait faite et
   correcte, elle m'etait invisible - mais c'est **une seconde mesure de la meme limite** que je
   route a `theo-archi` depuis hier, et cette fois elle porte sur mon propre chiffre.
2. **La methode que j'avais ecrite sous ce chiffre annoncait un retrait qui n'a pas eu lieu** :
   *"puis retrait de l'unique homonyme de la section 3 (`UTF-8`) et de son prefixe"*. **`UTF-8`
   n'est pas alloue dans la table de la section 4** : il n'y avait rien a en retirer, et l'en
   retirer aurait donne un chiffre faux. **Mon nombre etait bon ; ma phrase de methode decrivait une
   etape de trop.** La methode qui fait foi pour cette table est le comptage de ses lignes de la
   forme `PREFIXE-[R]nombre`, **sans retrait**. Je le corrige ici plutot que de laisser une methode
   inexacte servir de modele au prochain comptage.

**Compte apres mon allocation de ce soir, meme methode** : **539 identifiants, sur 91 prefixes**.


**Note de `nadia-prof`, le 2026-09-09, arbitrage prealable de s33.** Quatre identifiants ajoutes -
`PAR-14`, `EMC-11`, `EPS-7`, `HIS-5` - tous sur des prefixes deja alloues, **donc aucun prefixe
neuf**. Ligne d'etat courant portee de **578 / 97 a 582 / 97**. Methode : comptage des lignes de la
table de la section 4 sous le motif du garde-fou lui-meme (`ALLOCATION`, identifiant et document
entre accents graves), **avant** mon diff - 578 identifiants distincts, 97 prefixes distincts, 0
doublon, ce qui confirme la ligne portee par `sofia-sm` a l'assemblage de s32 - puis apres mes
quatre allocations. **Ce total est juste sur l'arbre entier et non seulement sur mon worktree** :
l'arbitrage prealable n'est pas divise en instances, je suis seule a allouer aujourd'hui, et la
limite structurelle de `PAR-5` sous la discipline de worktree dedie ne s'applique donc pas a ce
diff. **Aucune reparation arithmetique n'est a attendre a la fusion.**

**Note de `sofia-sm`, a l'ouverture de s33, resolution de la collision annoncee des deux cotes.**
`camille-po` avait alloue les quatre memes identifiants sur `round-two`, en pointant vers
`docs/backlog/backlog.md`, pour tenir la CI verte le temps que `s33-nadia` fusionne - et l'avait
ecrit dans sa propre note, ci-dessus dans ce fichier avant fusion. `nadia-prof` les avait allouee
sur `s33-nadia`, en pointant vers son arbitrage prealable. **Une seule ligne gardee par
identifiant** : celle de `nadia-prof`, qui definit les quatre conditions - l'allocation de
`camille-po` n'etait qu'un geste mecanique, comme sa note le dit elle-meme. Verifie que les
definitions ne divergent pas entre les deux cotes : `docs/backlog/backlog.md` et
`DEC-0085` citent les quatre identifiants en les attribuant a `nadia-prof` et a la meme date
(2026-09-09), sans en ecrire un sens different ; aucune des quatre n'est donc dans le cas que
`PAR-5` vise vraiment. **Compte recompte sur le fichier assemble, et non additionne** : 582
identifiants distincts, 97 prefixes distincts, zero doublon de definition dans la table de la
section 4 - la ligne d'etat courant ci-dessus etait deja juste et n'est pas modifiee par cette
resolution.


*Ligne portee de 640 / 103 a 649 / 104 par `nadia-prof` a l'ecriture de ses six verdicts de s35,
le 2026-09-11. Methode : recomptage des lignes de la table de la section 4 a la forme `|
identifiant | document |`, identifiants distincts et prefixes distincts, sur l'arbre de mon
worktree apres l'ajout de mes neuf allocations - **et non 640 + 9 calcule de tete**. Perimetre :
mon seul diff, aucune autre branche n'etait en vol. Les neuf sont `SCO-R4`, `EXC-R13`, `EXC-R14`,
`FRI-R6`, `FRI-R7`, `PCL-R1`, `PCL-R2`, `ANG-R8` et `CONS-5` ; le prefixe neuf est `PCL` (plan de
la classe), choisi a deux segments plutot que `PLA-CL-` a trois, pour ne pas ecrire un identifiant
de la forme d'un code de ligne de referentiel quand aucun niveau n'y est en jeu. **Ce geste est le
prix annonce de la clause 5, et j'ai decouvert le devoir en lancant `mvn -B clean verify` sur mon
propre diff avant de commiter, ce que `CLAUDE.md` demande depuis le 2026-09-08 : la premiere
version de mon commit a fait rougir ce garde-fou sur mes neuf identifiants.***

## 2. Ce que ce registre alloue, et ce qu'il n'alloue pas

Il alloue les identifiants de **condition** et de **reserve**, les deux genres que la mesure de s30
a fait apparaitre (`PASSE-TRANSVERSE-s30-les-trois-familles-de-portee-depot.md`, section 3.2) :

| Forme | Ce que l'identifiant designe | Exemple |
|---|---|---|
| `PREFIXE-<n>` | une **condition** posee sur un lot, un paquet ou le produit | `SCI-1`, `PAR-5`, `VOI-14` |
| `PREFIXE-R<n>` | une **reserve** ouverte sur un paquet servi | `HG-R1`, `FOP-R4`, `SAI-R1` |

**Quatre familles d'identifiants ne sont pas allouees ici, et aucune ne l'est par enumeration** -
chacune est ecartee par sa forme ou par la decision qui la gouverne deja :

| Famille | Forme | Ce qui l'alloue |
|---|---|---|
| Les codes de ligne de referentiel | trois segments, `PREFIXE-NIVEAU-nn` | le referentiel lui-meme (`ADR-0081`) |
| Les decisions | prefixe `ADR` ou `DEC` | le nom de fichier sous `squad/decisions/` (`ADR-0066`) |
| Les codes de story | prefixe portant un chiffre, `S<sprint>-<rang>` | le planning du sprint |
| Les numeros de rang interne d'un document | un seul caractere de prefixe, `M5`, `C4`, `V3`, `R1` | le document qui les porte, et eux seuls |

## 3. Les homonymes ecartes

**Deux formes du depot ressemblent a un identifiant de condition sans en etre une.** Elles sont
nommees ici plutot que cachees dans le garde-fou, et le garde-fou verifie que chacune designe encore
quelque chose de reel : le jour ou l'une disparait du depot, sa ligne doit disparaitre aussi. **Cette
table est un cliquet a plafond exact** : son plafond vaut deux, il est ecrit dans le garde-fou, et
une troisieme ligne fait rougir la CI au lieu d'entrer en silence. Ajouter un ecart coute donc un
diff dans le Java, pas une ligne discrete dans ce fichier - c'est ce qui empeche d'eteindre la
clause 2 par une exemption.

| Forme | Ce que c'est |
|---|---|
| `UTF-8` | le nom d'un encodage de caracteres, dans les `pom.xml`, les gabarits et les scripts |
| `ZZZ-9` | un identifiant fictif du depot fautif d'`ADR-0085`, cite verbatim hors de son propre test comme preuve de morsure dans un rapport de qualite |

## 4. Les identifiants alloues

| Identifiant | Document ou il est ecrit |
|---|---|
| `ACC-1` | `squad/conformite/FICHE-bandit-manchot-reperes-cm1.md` |
| `ACC-2` | `squad/conformite/FICHE-bandit-manchot-reperes-cm1.md` |
| `ADD-CE1-1` | `squad/conformite/ARBITRAGE-s37-operande-nul-et-forme-du-tirage-a-l-echelle-100.md` |
| `ADD-CE1-2` | `squad/conformite/ARBITRAGE-s37-operande-nul-et-forme-du-tirage-a-l-echelle-100.md` |
| `ADD-CE1-R1` | `squad/conformite/FICHE-bandit-manchot-additions-ce1.md` |
| `ADD-CE1-R2` | `squad/conformite/FICHE-bandit-manchot-additions-ce1.md` |
| `SOU-CE1-1` | `squad/conformite/ARBITRAGE-s38-soustractions-complements-dizaine-et-moities-ce1.md` |
| `SOU-CE1-2` | `squad/conformite/ARBITRAGE-s38-soustractions-complements-dizaine-et-moities-ce1.md` |
| `SOU-CE1-R1` | `squad/conformite/FICHE-bandit-manchot-soustractions-ce1.md` |
| `CDZ-CE1-1` | `squad/conformite/ARBITRAGE-s38-soustractions-complements-dizaine-et-moities-ce1.md` |
| `CDZ-CE1-2` | `squad/conformite/ARBITRAGE-s38-soustractions-complements-dizaine-et-moities-ce1.md` |
| `MOI-CE1-1` | `squad/conformite/ARBITRAGE-s38-soustractions-complements-dizaine-et-moities-ce1.md` |
| `MOI-CE1-2` | `squad/conformite/ARBITRAGE-s38-soustractions-complements-dizaine-et-moities-ce1.md` |
| `TAB-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` |
| `TAB-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` |
| `TAB-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` |
| `TAB-CE1-4` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` |
| `TAB-CE1-5` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` |
| `TAB-CE1-6` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` |
| `TAB-CE1-R1` | `squad/conformite/FICHE-bandit-manchot-tables-multiplication-ce1.md` |
| `PRO-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` |
| `PRO-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` |
| `PRO-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` |
| `PRO-CE1-4` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` |
| `PRO-CE1-5` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` |
| `PRO-CE1-6` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` |
| `PRO-CE1-7` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` |
| `PRO-CE1-R1` | `squad/conformite/FICHE-bandit-manchot-problemes-etapes-ce1.md` |
| `PRO-CE1-R2` | `squad/conformite/FICHE-bandit-manchot-problemes-etapes-ce1.md` |
| `MAS-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` |
| `MAS-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` |
| `MAS-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` |
| `MAS-CE1-4` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` |
| `MAS-CE1-5` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` |
| `MAS-CE1-6` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` |
| `CAL-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` |
| `CAL-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` |
| `CAL-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` |
| `CAL-CE1-4` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` |
| `CAL-CE1-5` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` |
| `DUR-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` |
| `DUR-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` |
| `DUR-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` |
| `DUR-CE1-4` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` |
| `DUR-CE1-5` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` |
| `DUR-CE1-6` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` |
| `DUR-CE1-7` | `docs/backlog/backlog.md` |
| `ADP-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `ADP-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `ADP-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `ADP-CE1-4` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `ADP-CE1-5` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `ADP-CE1-6` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `AGN-1` | `squad/conformite/FICHE-bandit-manchot-accord-sujet-verbe-cm1.md` |
| `AGN-2` | `squad/conformite/FICHE-bandit-manchot-accords-groupe-nominal-cm1.md` |
| `AGN-3` | `squad/conformite/FICHE-bandit-manchot-accords-groupe-nominal-cm1.md` |
| `ANG-4` | `squad/conformite/ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md` |
| `ANG-5` | `squad/conformite/ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md` |
| `ANG-R1` | `squad/conformite/ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md` |
| `ANG-R2` | `squad/conformite/FICHE-bandit-manchot-anglais-cm1.md` |
| `ANG-R3` | `squad/conformite/FICHE-bandit-manchot-anglais-cm1.md` |
| `ANG-R4` | `squad/conformite/FICHE-bandit-manchot-anglais-cm1.md` |
| `ANG-R5` | `squad/conformite/FICHE-bandit-manchot-anglais-cm1.md` |
| `ANG-R6` | `squad/conformite/FICHE-bandit-manchot-anglais-cm1.md` |
| `ANG-R7` | `squad/conformite/FICHE-bandit-manchot-anglais-cm1.md` |
| `ANG-CP-R1` | `squad/conformite/FICHE-bandit-manchot-culture-anglophone-cp.md` |
| `ANG-CP-R2` | `squad/conformite/FICHE-bandit-manchot-culture-anglophone-cp.md` |
| `ANG-CP-R3` | `squad/conformite/FICHE-bandit-manchot-culture-anglophone-cp.md` |
| `ANG-R8` | `squad/conformite/FICHE-bandit-manchot-anglais-cm1.md` |
| `ANT-1` | `squad/conformite/ARBITRAGE-PREALABLE-s19-antonymes-cm1-et-nature-du-mot-cp.md` |
| `ANT-2` | `squad/conformite/ARBITRAGE-PREALABLE-s19-antonymes-cm1-et-nature-du-mot-cp.md` |
| `ANT-3` | `squad/conformite/ARBITRAGE-PREALABLE-s19-antonymes-cm1-et-nature-du-mot-cp.md` |
| `ANT-4` | `squad/conformite/ARBITRAGE-PREALABLE-s19-antonymes-cm1-et-nature-du-mot-cp.md` |
| `ANT-5` | `squad/conformite/ARBITRAGE-PREALABLE-s19-antonymes-cm1-et-nature-du-mot-cp.md` |
| `ANT-6` | `squad/conformite/ARBITRAGE-PREALABLE-s19-antonymes-cm1-et-nature-du-mot-cp.md` |
| `ANT-7` | `squad/conformite/ARBITRAGE-PREALABLE-s19-antonymes-cm1-et-nature-du-mot-cp.md` |
| `ANT-8` | `squad/conformite/FICHE-bandit-manchot-antonymes-cm1.md` |
| `ANT-9` | `squad/conformite/FICHE-bandit-manchot-antonymes-cm1.md` |
| `ART-1` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` |
| `ART-2` | `squad/programme/arts-plastiques/competences-cp.md` |
| `ART-3` | `squad/programme/arts-plastiques/competences-cm1.md` |
| `ART-4` | `squad/programme/arts-plastiques/competences-cm1.md` |
| `ART-5` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` |
| `ASV-1` | `squad/conformite/FICHE-bandit-manchot-accord-sujet-verbe-cm1.md` |
| `ASV-2` | `squad/conformite/FICHE-bandit-manchot-accord-sujet-verbe-cm1.md` |
| `ASV-3` | `squad/conformite/FICHE-bandit-manchot-accord-sujet-verbe-cm1.md` |
| `AUD-1` | `squad/conformite/ARBITRAGE-PREALABLE-s19-antonymes-cm1-et-nature-du-mot-cp.md` |
| `AUD-2` | `squad/conformite/ARBITRAGE-PREALABLE-s19-antonymes-cm1-et-nature-du-mot-cp.md` |
| `AUD-3` | `squad/conformite/ARBITRAGE-PREALABLE-s19-antonymes-cm1-et-nature-du-mot-cp.md` |
| `AUT-CP-R1` | `squad/conformite/FICHE-bandit-manchot-autrefois-aujourdhui-cp.md` |
| `BARRE-1` | `squad/conformite/FICHE-barre-entree-niveau.md` |
| `BARRE-2` | `squad/conformite/FICHE-barre-entree-niveau.md` |
| `BARRE-3` | `squad/conformite/FICHE-barre-entree-niveau.md` |
| `BARRE-4` | `squad/conformite/FICHE-barre-entree-niveau.md` |
| `BARRE-5` | `squad/conformite/FICHE-barre-entree-niveau.md` |
| `BARRE-6` | `squad/conformite/FICHE-barre-entree-niveau.md` |
| `BARRE-7` | `squad/conformite/FICHE-barre-entree-niveau.md` |
| `BARRE-8` | `squad/conformite/FICHE-barre-entree-niveau.md` |
| `BARRE-9` | `squad/conformite/FICHE-barre-entree-niveau.md` |
| `BARRE-10` | `squad/sprints/s16-deux-defauts-et-dettes-de-len-tete/relecture-du-retard-de-s15.md` |
| `BARRE-11` | `squad/sprints/s16-deux-defauts-et-dettes-de-len-tete/relecture-du-retard-de-s15.md` |
| `BIA-1` | `squad/conformite/ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md` |
| `BIA-2` | `squad/conformite/ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md` |
| `BIA-3` | `squad/conformite/ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md` |
| `BIA-4` | `squad/conformite/ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md` |
| `BIA-5` | `squad/conformite/ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md` |
| `BIN-1` | `squad/conformite/ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-borne.md` |
| `BPM-1` | `squad/conformite/ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md` |
| `BPM-2` | `squad/conformite/ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md` |
| `BPM-3` | `squad/conformite/ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md` |
| `BPM-4` | `squad/conformite/ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md` |
| `BPM-5` | `squad/conformite/FICHE-bandit-manchot-homophones-cm1.md` |
| `BPM-6` | `squad/conformite/FICHE-barre-entree-niveau.md` |
| `BPM-7` | `squad/conformite/FICHE-bandit-manchot-homophones-cm1.md` |
| `BPM-8` | `squad/conformite/FICHE-bandit-manchot-homophones-cm1.md` |
| `CAT-1` | `squad/conformite/ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-borne.md` |
| `CHA-R1` | `squad/conformite/FICHE-bandit-manchot-chaine-alimentaire-cm1.md` |
| `COMP-1` | `squad/conformite/ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md` |
| `COMP-2` | `squad/conformite/ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md` |
| `COMP-3` | `squad/conformite/ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md` |
| `COMP-4` | `squad/conformite/ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md` |
| `COMP-5` | `squad/conformite/ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md` |
| `COMP-6` | `squad/conformite/ARBITRAGE-M4-ET-BALAYAGE-DES-CONDITIONS-DE-MECANIQUE-2026-08-29.md` |
| `COMP-7` | `squad/conformite/ARBITRAGE-M4-ET-BALAYAGE-DES-CONDITIONS-DE-MECANIQUE-2026-08-29.md` |
| `COH-1` | `squad/conformite/ARBITRAGE-PREALABLE-s34-le-motif-transverse-l-objet-d-une-reserve-et-le-vivier-de-quatre-refus.md` |
| `CON-1` | `squad/conformite/ARBITRAGE-PREALABLE-s25-palier-1-visuel-mon-ordre-du-15-aout-et-la-mesure-de-pli.md` |
| `CON-2` | `squad/conformite/ARBITRAGE-PREALABLE-s25-palier-1-visuel-mon-ordre-du-15-aout-et-la-mesure-de-pli.md` |
| `CON-3` | `squad/conformite/ARBITRAGE-PREALABLE-s25-palier-1-visuel-mon-ordre-du-15-aout-et-la-mesure-de-pli.md` |
| `CON-4` | `squad/conformite/ARBITRAGE-PREALABLE-s25-palier-1-visuel-mon-ordre-du-15-aout-et-la-mesure-de-pli.md` |
| `CON-5` | `squad/conformite/ARBITRAGE-PREALABLE-s25-palier-1-visuel-mon-ordre-du-15-aout-et-la-mesure-de-pli.md` |
| `CON-6` | `squad/conformite/ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md` |
| `CONF-1` | `squad/conformite/FICHE-bandit-manchot-additions-cp.md` |
| `CONF-2` | `squad/conformite/FICHE-bandit-manchot-additions-cp.md` |
| `CONF-3` | `squad/conformite/FICHE-bandit-manchot-additions-cp.md` |
| `CONF-4` | `squad/conformite/FICHE-bandit-manchot-additions-cp.md` |
| `CONF-5` | `squad/conformite/FICHE-bandit-manchot-additions-cp.md` |
| `CONS-1` | `squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md` |
| `CONS-2` | `squad/conformite/FICHE-bandit-manchot-etats-eau-cp.md` |
| `CONS-3` | `squad/programme/MATRICE-couverture.md` |
| `CONS-4` | `squad/conformite/FICHE-bandit-manchot-histoire-geographie-cm1.md` |
| `CONS-5` | `squad/conformite/FICHE-bandit-manchot-histoire-geographie-cm1.md` |
| `COR-R1` | `squad/conformite/ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md` |
| `COR-R2` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` |
| `COR-R3` | `squad/conformite/FICHE-bandit-manchot-corps-sens-cp.md` |
| `COR-R4` | `squad/conformite/FICHE-bandit-manchot-corps-sens-cp.md` |
| `COU-1` | `squad/conformite/ARBITRAGE-s27-titre-du-jeu-d-additions-et-portee-de-ma-clause-sur-la-couleur.md` |
| `COU-2` | `squad/conformite/ARBITRAGE-s27-titre-du-jeu-d-additions-et-portee-de-ma-clause-sur-la-couleur.md` |
| `COU-3` | `squad/conformite/ARBITRAGE-s27-titre-du-jeu-d-additions-et-portee-de-ma-clause-sur-la-couleur.md` |
| `COU-4` | `squad/conformite/ARBITRAGE-s27-titre-du-jeu-d-additions-et-portee-de-ma-clause-sur-la-couleur.md` |
| `CPL-1` | `squad/conformite/ARBITRAGE-PREALABLE-s24-borne-den-premier-paquet-de-saisie-et-deux-points-de-satisfaisabilite.md` |
| `CPL-2` | `squad/conformite/ARBITRAGE-PREALABLE-s24-borne-den-premier-paquet-de-saisie-et-deux-points-de-satisfaisabilite.md` |
| `CPL-3` | `squad/conformite/ARBITRAGE-PREALABLE-s24-borne-den-premier-paquet-de-saisie-et-deux-points-de-satisfaisabilite.md` |
| `CPL-4` | `squad/conformite/ARBITRAGE-PREALABLE-s24-borne-den-premier-paquet-de-saisie-et-deux-points-de-satisfaisabilite.md` |
| `CPL-5` | `squad/conformite/ARBITRAGE-PREALABLE-s24-borne-den-premier-paquet-de-saisie-et-deux-points-de-satisfaisabilite.md` |
| `CPL-6` | `squad/conformite/ARBITRAGE-PREALABLE-s24-borne-den-premier-paquet-de-saisie-et-deux-points-de-satisfaisabilite.md` |
| `CPL-7` | `squad/conformite/ARBITRAGE-PREALABLE-s24-borne-den-premier-paquet-de-saisie-et-deux-points-de-satisfaisabilite.md` |
| `CPL-8` | `squad/conformite/ARBITRAGE-PREALABLE-s24-borne-den-premier-paquet-de-saisie-et-deux-points-de-satisfaisabilite.md` |
| `CPL-9` | `squad/conformite/FICHE-saisie-complements-10-cp.md` |
| `CYC-1` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` |
| `CYV-R1` | `squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md` |
| `DEN-1` | `squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md` |
| `DEN-2` | `squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md` |
| `DEN-3` | `squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md` |
| `DEN-4` | `squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md` |
| `DEN-5` | `squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md` |
| `DEN-6` | `squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md` |
| `DEN-7` | `squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md` |
| `DEN-8` | `squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md` |
| `DEP-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md` |
| `DEP-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md` |
| `DEP-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md` |
| `DEP-CE1-4` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md` |
| `DEP-CP-R1` | `squad/conformite/FICHE-bandit-manchot-deplacements-quadrillage-cp.md` |
| `DEP-CP-R2` | `squad/conformite/FICHE-bandit-manchot-deplacements-quadrillage-cp.md` |
| `DEP-CP-R3` | `squad/conformite/FICHE-bandit-manchot-deplacements-quadrillage-cp.md` |
| `DEP-CP-R4` | `squad/conformite/FICHE-bandit-manchot-deplacements-quadrillage-cp.md` |
| `DES-1` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-palier-2-ce-qu-un-paquet-de-designation-doit-apporter.md` |
| `DES-2` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-palier-2-ce-qu-un-paquet-de-designation-doit-apporter.md` |
| `DES-3` | `squad/sprints/s25-le-premier-palier-visuel/contrainte-ines-ce-qu-un-dessin-servi-doit-declarer.md` |
| `DES-4` | `squad/sprints/s25-le-premier-palier-visuel/contrainte-ines-ce-qu-un-dessin-servi-doit-declarer.md` |
| `DES-5` | `squad/sprints/s25-le-premier-palier-visuel/contrainte-ines-ce-qu-un-dessin-servi-doit-declarer.md` |
| `DES-6` | `squad/conformite/PASSE-TRANSVERSE-s29-les-proprietes-de-portee-depot.md` |
| `DES-7` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-palier-2-ce-qu-un-paquet-de-designation-doit-apporter.md` |
| `DEV-R1` | `squad/conformite/FICHE-bandit-manchot-etapes-developpement-cm1.md` |
| `DEV-R2` | `squad/conformite/FICHE-bandit-manchot-etapes-developpement-cm1.md` |
| `DIV-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md` |
| `DIV-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md` |
| `DIV-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md` |
| `DIV-CE1-4` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md` |
| `DIV-CE1-5` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md` |
| `DIV-CE1-6` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md` |
| `DRO-R1` | `squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md` |
| `DRO-R2` | `squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md` |
| `DRO-R3` | `squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md` |
| `DRO-R4` | `squad/conformite/PASSE-TRANSVERSE-s30-les-trois-familles-de-portee-depot.md` |
| `DV-1` | `squad/sprints/s25-le-premier-palier-visuel/review.md` |
| `DV-2` | `squad/sprints/s25-le-premier-palier-visuel/planning.md` |
| `DV-3` | `squad/sprints/s25-le-premier-palier-visuel/review.md` |
| `DV-4` | `squad/decisions/produit/DEC-0067-etat-du-produit-a-la-cloture-de-s25-la-reserve-retombe-a-une-ligne-et-rang-propose-pour-s26.md` |
| `DV-5` | `squad/sprints/s25-le-premier-palier-visuel/planning.md` |
| `DV-6` | `squad/sprints/s25-le-premier-palier-visuel/review.md` |
| `DV-7` | `squad/sprints/s25-le-premier-palier-visuel/planning.md` |
| `DV-8` | `squad/sprints/s25-le-premier-palier-visuel/planning.md` |
| `DV-9` | `squad/sprints/s25-le-premier-palier-visuel/planning.md` |
| `DV-10` | `squad/sprints/s25-le-premier-palier-visuel/planning.md` |
| `DVS-CP-R1` | `squad/programme/MATRICE-couverture.md` |
| `EAU-R1` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` |
| `EAU-R2` | `squad/conformite/FICHE-bandit-manchot-etats-eau-cp.md` |
| `EFF-R1` | `squad/conformite/FICHE-bandit-manchot-corps-effort-eps-cp.md` |
| `EFF-R2` | `squad/conformite/FICHE-bandit-manchot-corps-effort-eps-cp.md` |
| `EFF-R3` | `squad/conformite/FICHE-bandit-manchot-corps-effort-eps-cp.md` |
| `EFF-R4` | `squad/conformite/FICHE-bandit-manchot-corps-effort-eps-cp.md` |
| `EFF-R5` | `squad/conformite/FICHE-bandit-manchot-corps-effort-eps-cp.md` |
| `EFF-R6` | `squad/conformite/FICHE-bandit-manchot-corps-effort-eps-cp.md` |
| `EFF-R7` | `squad/conformite/FICHE-bandit-manchot-corps-effort-eps-cp.md` |
| `EMC-1` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` |
| `EMC-2` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` |
| `EMC-3` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` |
| `EMC-4` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` |
| `EMC-5` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` |
| `EMC-6` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` |
| `EMC-7` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` |
| `EMC-8` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` |
| `EMC-9` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` |
| `EMC-10` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` |
| `EMC-11` | `squad/conformite/ARBITRAGE-PREALABLE-s33-le-plan-2-applique-a-s33-le-vivier-que-quatre-refus-ont-realimente-et-ce-que-la-borne-de-douze-ne-finance-pas.md` |
| `EMC-CM1-R1` | `squad/conformite/FICHE-bandit-manchot-symboles-cm1.md` |
| `EMC-CM1-R2` | `squad/conformite/FICHE-bandit-manchot-symboles-cm1.md` |
| `EMC-CM1-R3` | `squad/conformite/FICHE-bandit-manchot-symboles-cm1.md` |
| `EMC-CM1-R4` | `squad/conformite/FICHE-bandit-manchot-symboles-cm1.md` |
| `EMC-CM1-R5` | `squad/conformite/FICHE-bandit-manchot-emotion-ressentir-agir-cm1.md` |
| `EMC-CM1-R6` | `squad/conformite/FICHE-bandit-manchot-emotion-ressentir-agir-cm1.md` |
| `EMC-CM1-R7` | `squad/conformite/FICHE-bandit-manchot-institutions-cm1.md` |
| `EMO-R1` | `squad/conformite/FICHE-bandit-manchot-emotions-autrui-cp.md` |
| `EMO-R2` | `squad/conformite/FICHE-bandit-manchot-emotions-autrui-cp.md` |
| `EMO-R3` | `squad/conformite/FICHE-bandit-manchot-emotions-autrui-cp.md` |
| `EPS-1` | `squad/programme/education-physique-et-sportive/competences-cp.md` |
| `EPS-2` | `squad/programme/education-physique-et-sportive/competences-cp.md` |
| `EPS-3` | `squad/programme/education-physique-et-sportive/competences-cp.md` |
| `EPS-4` | `squad/sprints/s32-trois-instances-et-ouverture-de-l-eps/verdicts-nadia-instance-C.md` |
| `EPS-5` | `squad/sprints/s32-trois-instances-et-ouverture-de-l-eps/verdicts-nadia-instance-C.md` |
| `EPS-6` | `squad/sprints/s32-trois-instances-et-ouverture-de-l-eps/verdicts-nadia-instance-C.md` |
| `EPS-7` | `squad/conformite/ARBITRAGE-PREALABLE-s33-le-plan-2-applique-a-s33-le-vivier-que-quatre-refus-ont-realimente-et-ce-que-la-borne-de-douze-ne-finance-pas.md` |
| `EPS-8` | `squad/sprints/s33-plan-2-tenu-le-palier-2-differe-a-s34/verdicts-nadia-instance-C.md` |
| `EPS-9` | `docs/backlog/backlog.md` |
| `EPS-10` | `squad/conformite/ARBITRAGE-PREALABLE-s45-cinq-lots.md` |
| `ERR-R1` | `squad/conformite/PASSE-TRANSVERSE-s30-les-trois-familles-de-portee-depot.md` |
| `ETA-1` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `ETA-2` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `ETA-3` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `ETA-4` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `ETA-5` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `ETA-6` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `ETA-7` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `ETA-8` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `ETA-9` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `ETA-10` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `EXA-1` | `squad/sprints/s32-trois-instances-et-ouverture-de-l-eps/verdicts-nadia-instance-A.md` |
| `EXC-R1` | `squad/conformite/FICHE-bandit-manchot-exclusion-cp.md` |
| `EXC-R2` | `squad/conformite/FICHE-bandit-manchot-exclusion-cp.md` |
| `EXC-R3` | `squad/conformite/FICHE-bandit-manchot-exclusion-cp.md` |
| `EXC-R4` | `squad/conformite/FICHE-bandit-manchot-exclusion-cp.md` |
| `EXC-R5` | `squad/conformite/FICHE-bandit-manchot-exclusion-cp.md` |
| `EXC-R6` | `squad/conformite/FICHE-bandit-manchot-exclusion-cp.md` |
| `EXC-R7` | `squad/conformite/FICHE-bandit-manchot-exclusion-cp.md` |
| `EXC-R8` | `squad/conformite/FICHE-bandit-manchot-exclusion-cp.md` |
| `EXC-R9` | `squad/conformite/FICHE-bandit-manchot-exclusion-cp.md` |
| `EXC-R10` | `squad/conformite/FICHE-bandit-manchot-exclusion-cp.md` |
| `EXC-R11` | `squad/conformite/FICHE-bandit-manchot-exclusion-cp.md` |
| `EXC-R12` | `squad/conformite/FICHE-bandit-manchot-exclusion-cp.md` |
| `EXC-R13` | `squad/conformite/FICHE-bandit-manchot-exclusion-cp.md` |
| `EXC-R14` | `squad/conformite/FICHE-bandit-manchot-exclusion-cp.md` |
| `EVO-1` | `squad/conformite/ARBITRAGE-PREALABLE-s44-musique-et-sciences-cm1.md` |
| `EVO-2` | `squad/conformite/ARBITRAGE-PREALABLE-s44-musique-et-sciences-cm1.md` |
| `FAB-1` | `squad/conformite/ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-borne.md` |
| `FD-1` | `squad/sprints/s34-dernier-sprint-du-plan-2-palier-2-engage/arbitrage-emccp07-nadia.md` |
| `FD-2` | `squad/sprints/s34-dernier-sprint-du-plan-2-palier-2-engage/arbitrage-emccp07-nadia.md` |
| `FD-3` | `squad/sprints/s34-dernier-sprint-du-plan-2-palier-2-engage/arbitrage-emccp07-nadia.md` |
| `FER-1` | `squad/conformite/ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md` |
| `FER-2` | `squad/conformite/ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md` |
| `FER-3` | `squad/conformite/ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md` |
| `FIG-1` | `squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md` |
| `FIG-2` | `squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md` |
| `FIG-3` | `squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md` |
| `FIG-4` | `squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md` |
| `FIG-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md` |
| `FIG-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md` |
| `FIG-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md` |
| `FOC-1` | `squad/conformite/ARBITRAGE-PREALABLE-s17-indicateur-de-focus-clavier.md` |
| `FOC-2` | `squad/conformite/ARBITRAGE-PREALABLE-s17-indicateur-de-focus-clavier.md` |
| `FOC-3` | `squad/conformite/ARBITRAGE-PREALABLE-s17-indicateur-de-focus-clavier.md` |
| `FOC-4` | `squad/conformite/ARBITRAGE-PREALABLE-s17-indicateur-de-focus-clavier.md` |
| `FOC-5` | `squad/conformite/ARBITRAGE-PREALABLE-s17-indicateur-de-focus-clavier.md` |
| `FOC-6` | `squad/conformite/ARBITRAGE-PREALABLE-s17-indicateur-de-focus-clavier.md` |
| `FOP-R1` | `squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md` |
| `FOP-R2` | `squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md` |
| `FOP-R3` | `squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md` |
| `FOP-R4` | `squad/conformite/PASSE-TRANSVERSE-s30-les-trois-familles-de-portee-depot.md` |
| `FRA-1` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `FRA-2` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `FRA-3` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `FRA-4` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `FRA-5` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `FRA-6` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `FRA-7` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `FRA-8` | `squad/conformite/ARBITRAGE-FRA-la-forme-d-contre-le-seuil-minimal-de-rang.md` |
| `FRA-9` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `FRA-10` | `squad/conformite/ARBITRAGE-FRA-la-forme-d-contre-le-seuil-minimal-de-rang.md` |
| `FRA-11` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `FRA-12` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `FRA-13` | `docs/backlog/backlog.md` |
| `FRA-14` | `docs/backlog/backlog.md` |
| `FRA-15` | `docs/backlog/backlog.md` |
| `FRI-R1` | `squad/conformite/FICHE-designation-frise-de-la-journee.md` |
| `FRI-R2` | `squad/conformite/FICHE-designation-frise-de-la-journee.md` |
| `FRI-R3` | `squad/conformite/FICHE-designation-frise-de-la-journee.md` |
| `FRI-R4` | `squad/conformite/FICHE-designation-frise-de-la-journee.md` |
| `FRI-R5` | `squad/conformite/FICHE-designation-frise-de-la-journee.md` |
| `FRI-R6` | `squad/conformite/FICHE-designation-frise-de-la-journee.md` |
| `FRI-R7` | `squad/conformite/FICHE-designation-frise-de-la-journee.md` |
| `GN-1` | `squad/conformite/ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md` |
| `GN-2` | `squad/conformite/ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md` |
| `GRA-1` | `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md` |
| `GRA-2` | `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md` |
| `GRA-3` | `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md` |
| `GRA-4` | `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md` |
| `GRA-5` | `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md` |
| `GRA-6` | `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md` |
| `GRA-7` | `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md` |
| `GRA-8` | `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md` |
| `GRA-9` | `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md` |
| `GRA-10` | `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md` |
| `GRA-11` | `squad/conformite/FICHE-bandit-manchot-comparer-cp.md` |
| `GRA-12` | `squad/conformite/FICHE-bandit-manchot-comparer-cp.md` |
| `GRA-13` | `squad/conformite/FICHE-bandit-manchot-comparer-cp.md` |
| `GRA-14` | `squad/conformite/FICHE-bandit-manchot-comparer-cp.md` |
| `GRA-15` | `squad/conformite/FICHE-bandit-manchot-comparer-cp.md` |
| `GRA-16` | `squad/conformite/FICHE-bandit-manchot-comparer-cp.md` |
| `GRA-17` | `squad/conformite/FICHE-bandit-manchot-comparer-cp.md` |
| `GRN-1` | `squad/conformite/FICHE-bandit-manchot-reperes-cm1.md` |
| `GRN-2` | `squad/conformite/FICHE-bandit-manchot-grands-nombres-cm1.md` |
| `GRN-3` | `squad/conformite/FICHE-bandit-manchot-reperes-cm1.md` |
| `HDA-1` | `squad/conformite/ARBITRAGE-PREALABLE-s40-relier-une-oeuvre-a-son-contexte-hda-cm1-05.md` |
| `HDA-2` | `squad/conformite/ARBITRAGE-PREALABLE-s40-relier-une-oeuvre-a-son-contexte-hda-cm1-05.md` |
| `HDA-3` | `squad/conformite/ARBITRAGE-PREALABLE-s40-relier-une-oeuvre-a-son-contexte-hda-cm1-05.md` |
| `HDA-4` | `squad/conformite/ARBITRAGE-PREALABLE-s40-relier-une-oeuvre-a-son-contexte-hda-cm1-05.md` |
| `HG-R1` | `squad/conformite/PASSE-TRANSVERSE-s29-les-proprietes-de-portee-depot.md` |
| `HG-R2` | `squad/conformite/ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md` |
| `HG-R3` | `squad/conformite/ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md` |
| `HG-R4` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` |
| `HG-R5` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` |
| `HG-R6` | `squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md` |
| `HG-R7` | `squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md` |
| `HG-R8` | `squad/conformite/FICHE-bandit-manchot-histoire-geographie-cm1.md` |
| `HG-R9` | `squad/conformite/FICHE-bandit-manchot-histoire-geographie-cm1.md` |
| `HG-R10` | `squad/conformite/FICHE-bandit-manchot-histoire-geographie-cm1.md` |
| `HG-R11` | `squad/conformite/FICHE-bandit-manchot-histoire-geographie-cm1.md` |
| `HG-R12` | `squad/conformite/FICHE-bandit-manchot-histoire-geographie-cm1.md` |
| `HG-R13` | `squad/conformite/FICHE-bandit-manchot-histoire-geographie-cm1.md` |
| `HG-R14` | `squad/conformite/FICHE-bandit-manchot-histoire-geographie-cm1.md` |
| `HG-R15` | `squad/conformite/FICHE-bandit-manchot-histoire-geographie-cm1.md` |
| `HG-R16` | `squad/conformite/FICHE-bandit-manchot-histoire-geographie-cm1.md` |
| `HG-R17` | `squad/conformite/FICHE-bandit-manchot-histoire-geographie-cm1.md` |
| `HG-R18` | `squad/conformite/FICHE-bandit-manchot-histoire-geographie-cm1.md` |
| `HG-R19` | `squad/conformite/FICHE-bandit-manchot-histoire-geographie-cm1.md` |
| `HG-R20` | `squad/conformite/FICHE-bandit-manchot-histoire-geographie-cm1.md` |
| `HG-R21` | `squad/conformite/FICHE-bandit-manchot-histoire-geographie-cm1.md` |
| `HG-R22` | `squad/conformite/FICHE-bandit-manchot-histoire-geographie-cm1.md` |
| `HG-R23` | `squad/conformite/FICHE-bandit-manchot-histoire-geographie-nommer-en-histoire-cm1.md` |
| `HG-R24` | `squad/conformite/FICHE-bandit-manchot-histoire-geographie-nommer-en-histoire-cm1.md` |
| `HG-R25` | `squad/conformite/FICHE-bandit-manchot-histoire-geographie-nommer-en-histoire-cm1.md` |
| `HIS-1` | `squad/conformite/ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md` |
| `HIS-2` | `squad/conformite/ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md` |
| `HIS-3` | `squad/conformite/ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md` |
| `HIS-4` | `squad/conformite/ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md` |
| `HIS-5` | `squad/conformite/ARBITRAGE-PREALABLE-s33-le-plan-2-applique-a-s33-le-vivier-que-quatre-refus-ont-realimente-et-ce-que-la-borne-de-douze-ne-finance-pas.md` |
| `HOR-1` | `squad/conformite/ARBITRAGE-PREALABLE-s25-palier-1-visuel-mon-ordre-du-15-aout-et-la-mesure-de-pli.md` |
| `HOR-2` | `squad/conformite/ARBITRAGE-PREALABLE-s25-palier-1-visuel-mon-ordre-du-15-aout-et-la-mesure-de-pli.md` |
| `HOR-3` | `squad/conformite/ARBITRAGE-PREALABLE-s25-palier-1-visuel-mon-ordre-du-15-aout-et-la-mesure-de-pli.md` |
| `HOR-4` | `squad/conformite/ARBITRAGE-PREALABLE-s25-palier-1-visuel-mon-ordre-du-15-aout-et-la-mesure-de-pli.md` |
| `HOR-5` | `squad/conformite/ARBITRAGE-PREALABLE-s25-palier-1-visuel-mon-ordre-du-15-aout-et-la-mesure-de-pli.md` |
| `HOR-6` | `squad/conformite/ARBITRAGE-PREALABLE-s25-palier-1-visuel-mon-ordre-du-15-aout-et-la-mesure-de-pli.md` |
| `HOR-7` | `squad/conformite/ARBITRAGE-PREALABLE-s25-palier-1-visuel-mon-ordre-du-15-aout-et-la-mesure-de-pli.md` |
| `HOR-8` | `squad/conformite/ARBITRAGE-PREALABLE-s25-palier-1-visuel-mon-ordre-du-15-aout-et-la-mesure-de-pli.md` |
| `JEU-R1` | `squad/conformite/FICHE-bandit-manchot-regles-jeu-collectif-eps-cp.md` |
| `JEU-R2` | `squad/conformite/FICHE-bandit-manchot-regles-jeu-collectif-eps-cp.md` |
| `JEU-R3` | `squad/conformite/FICHE-bandit-manchot-regles-jeu-collectif-eps-cp.md` |
| `JEU-R4` | `squad/conformite/FICHE-bandit-manchot-regles-jeu-collectif-eps-cp.md` |
| `JEU-R5` | `squad/conformite/FICHE-bandit-manchot-regles-jeu-collectif-eps-cp.md` |
| `JEU-R6` | `squad/conformite/FICHE-bandit-manchot-regles-jeu-collectif-eps-cp.md` |
| `JEU-R7` | `squad/conformite/FICHE-bandit-manchot-regles-jeu-collectif-eps-cp.md` |
| `JEU-R8` | `squad/conformite/FICHE-bandit-manchot-regles-jeu-collectif-eps-cp.md` |
| `JEU-R9` | `squad/conformite/FICHE-bandit-manchot-regles-jeu-collectif-eps-cp.md` |
| `JEU-R10` | `squad/conformite/FICHE-bandit-manchot-regles-jeu-collectif-eps-cp.md` |
| `JEU-R11` | `squad/conformite/FICHE-bandit-manchot-regles-jeu-collectif-eps-cp.md` |
| `JEU-R12` | `squad/conformite/FICHE-bandit-manchot-regles-jeu-collectif-eps-cp.md` |
| `LIA-1` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `LIA-2` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `LIA-3` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `LIA-4` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `LIA-5` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `LIA-6` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `LIA-7` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `LIA-8` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `LIA-9` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `LIA-10` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `LIS-1` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `LIS-2` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `LIS-3` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `LIS-4` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `LIS-5` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `LIS-6` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `LIS-7` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `LIS-8` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `LIS-9` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `LIS-10` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `LON-1` | `squad/conformite/ARBITRAGE-PREALABLE-s32-trois-instances-ma-borne-a-douze-le-terrain-sans-image-et-le-prix-d-une-ligne-marginale.md` |
| `LON-2` | `squad/conformite/ARBITRAGE-PREALABLE-s32-trois-instances-ma-borne-a-douze-le-terrain-sans-image-et-le-prix-d-une-ligne-marginale.md` |
| `LON-CE1-1` | `squad/conformite/ARBITRAGE-s36-longueur-d-enonce-au-ce1.md` |
| `LON-CE1-2` | `squad/conformite/ARBITRAGE-s36-longueur-d-enonce-au-ce1.md` |
| `LON-CE1-3` | `squad/conformite/ARBITRAGE-s47-longueur-du-texte-support-au-ce1.md` |
| `LON-CE1-4` | `squad/conformite/ARBITRAGE-s47-longueur-du-texte-support-au-ce1.md` |
| `MAT-R1` | `squad/conformite/FICHE-bandit-manchot-familles-materiaux-cm1.md` |
| `MAT-R2` | `squad/conformite/FICHE-bandit-manchot-familles-materiaux-cm1.md` |
| `MD-1` | `squad/conformite/ARBITRAGE-PREALABLE-s13-multiplications-divisions-et-durees-cm1.md` |
| `MD-2` | `squad/conformite/ARBITRAGE-PREALABLE-s13-multiplications-divisions-et-durees-cm1.md` |
| `MD-3` | `squad/conformite/ARBITRAGE-PREALABLE-s13-multiplications-divisions-et-durees-cm1.md` |
| `MD-4` | `squad/conformite/ARBITRAGE-PREALABLE-s13-multiplications-divisions-et-durees-cm1.md` |
| `MD-5` | `squad/conformite/ARBITRAGE-PREALABLE-s13-multiplications-divisions-et-durees-cm1.md` |
| `MD-6` | `squad/conformite/ARBITRAGE-PREALABLE-s13-multiplications-divisions-et-durees-cm1.md` |
| `MD-7` | `squad/conformite/ARBITRAGE-PREALABLE-s13-multiplications-divisions-et-durees-cm1.md` |
| `MD-8` | `squad/conformite/ARBITRAGE-PREALABLE-s13-multiplications-divisions-et-durees-cm1.md` |
| `MD-9` | `squad/conformite/ARBITRAGE-PREALABLE-s13-multiplications-divisions-et-durees-cm1.md` |
| `MD-10` | `squad/conformite/ARBITRAGE-PREALABLE-s13-multiplications-divisions-et-durees-cm1.md` |
| `MEL-R1` | `squad/conformite/FICHE-bandit-manchot-melanges-cm1.md` |
| `MEL-R2` | `squad/conformite/FICHE-bandit-manchot-melanges-cm1.md` |
| `MEL-R3` | `squad/conformite/FICHE-bandit-manchot-melanges-cm1.md` |
| `MEL-R4` | `squad/conformite/FICHE-bandit-manchot-melanges-cm1.md` |
| `MES-1` | `squad/conformite/FICHE-bandit-manchot-reperes-cm1.md` |
| `MES-2` | `squad/conformite/FICHE-bandit-manchot-reperes-cm1.md` |
| `MNC-1` | `squad/conformite/ARBITRAGE-M4-ET-BALAYAGE-DES-CONDITIONS-DE-MECANIQUE-2026-08-29.md` |
| `MOB-R1` | `squad/conformite/FICHE-bandit-manchot-matiere-objets-cp.md` |
| `MOB-R2` | `squad/conformite/FICHE-bandit-manchot-matiere-objets-cp.md` |
| `MOB-R3` | `squad/conformite/FICHE-bandit-manchot-matiere-objets-cp.md` |
| `MOB-R4` | `squad/conformite/FICHE-bandit-manchot-matiere-objets-cp.md` |
| `MON-1` | `squad/conformite/ARBITRAGE-PREALABLE-s25-palier-1-visuel-mon-ordre-du-15-aout-et-la-mesure-de-pli.md` |
| `MON-2` | `squad/conformite/ARBITRAGE-PREALABLE-s25-palier-1-visuel-mon-ordre-du-15-aout-et-la-mesure-de-pli.md` |
| `MON-3` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `MON-4` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `MON-5` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `MON-6` | `squad/conformite/FICHE-bandit-manchot-monnaie-cp.md` |
| `MUL-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `MUL-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `MUL-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `MUL-CE1-4` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `MUL-CE1-5` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `MUL-CE1-6` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `MUL-CE1-7` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `MUS-1` | `squad/programme/education-musicale/competences-cp.md` |
| `MUS-2` | `squad/programme/education-musicale/competences-cp.md` |
| `MUS-3` | `squad/conformite/ARBITRAGE-PREALABLE-s44-musique-et-sciences-cm1.md` |
| `NAR-1` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `NAR-2` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `NAR-3` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `NAR-4` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `NAR-5` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `NAR-6` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `NAR-7` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `NAR-8` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `NAR-9` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `NAR-10` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `NAR-11` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `NARR-1` | `docs/backlog/backlog.md` |
| `NARR-2` | `docs/backlog/backlog.md` |
| `NARR-3` | `docs/backlog/backlog.md` |
| `NARR-4` | `docs/backlog/backlog.md` |
| `NARR-5` | `docs/backlog/backlog.md` |
| `NAT-1` | `squad/conformite/ARBITRAGE-PREALABLE-s19-antonymes-cm1-et-nature-du-mot-cp.md` |
| `NAT-2` | `squad/conformite/ARBITRAGE-PREALABLE-s19-antonymes-cm1-et-nature-du-mot-cp.md` |
| `NAT-3` | `squad/conformite/ARBITRAGE-PREALABLE-s19-antonymes-cm1-et-nature-du-mot-cp.md` |
| `NAT-4` | `squad/conformite/ARBITRAGE-PREALABLE-s19-antonymes-cm1-et-nature-du-mot-cp.md` |
| `NAT-5` | `squad/conformite/ARBITRAGE-PREALABLE-s19-antonymes-cm1-et-nature-du-mot-cp.md` |
| `NAT-6` | `squad/conformite/ARBITRAGE-PREALABLE-s19-antonymes-cm1-et-nature-du-mot-cp.md` |
| `NAT-7` | `squad/conformite/ARBITRAGE-PREALABLE-s19-antonymes-cm1-et-nature-du-mot-cp.md` |
| `NAT-8` | `squad/conformite/ARBITRAGE-PREALABLE-s19-antonymes-cm1-et-nature-du-mot-cp.md` |
| `NAT-9` | `squad/conformite/FICHE-bandit-manchot-nature-mot-cp.md` |
| `NAT-10` | `squad/conformite/FICHE-bandit-manchot-nature-mot-cp.md` |
| `NAT-11` | `squad/conformite/FICHE-bandit-manchot-nature-mot-cp.md` |
| `NAT-12` | `squad/conformite/FICHE-bandit-manchot-nature-mot-cp.md` |
| `NUM-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `NUM-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `NUM-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `NUM-CE1-4` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `NUM-CE1-5` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `NUM-CE1-6` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `NUM-CE1-7` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `OBJ-R1` | `squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md` |
| `OBJ-R2` | `squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md` |
| `OBT-R1` | `squad/conformite/FICHE-bandit-manchot-objet-technique-cm1.md` |
| `OBT-R2` | `squad/conformite/FICHE-bandit-manchot-objet-technique-cm1.md` |
| `ORD-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `ORD-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `ORD-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `ORD-CE1-4` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `ORD-CE1-5` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `ORD-CE1-6` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `PAR-0` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` |
| `PAR-1` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` |
| `PAR-2` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` |
| `PAR-3` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` |
| `PAR-4` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` |
| `PAR-5` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` |
| `PAR-6` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` |
| `PAR-7` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` |
| `PAR-8` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` |
| `PAR-9` | `squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md` |
| `PAR-10` | `squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md` |
| `PAR-11` | `squad/programme/MATRICE-couverture.md` |
| `PAR-12` | `squad/conformite/ARBITRAGE-PREALABLE-s32-trois-instances-ma-borne-a-douze-le-terrain-sans-image-et-le-prix-d-une-ligne-marginale.md` |
| `PAR-13` | `squad/conformite/ARBITRAGE-PREALABLE-s32-trois-instances-ma-borne-a-douze-le-terrain-sans-image-et-le-prix-d-une-ligne-marginale.md` |
| `PAR-14` | `squad/conformite/ARBITRAGE-PREALABLE-s33-le-plan-2-applique-a-s33-le-vivier-que-quatre-refus-ont-realimente-et-ce-que-la-borne-de-douze-ne-finance-pas.md` |
| `PAR-15` | `squad/conformite/ARBITRAGE-PREALABLE-s34-le-motif-transverse-l-objet-d-une-reserve-et-le-vivier-de-quatre-refus.md` |
| `PAR-16` | `squad/conformite/ARBITRAGE-PREALABLE-s34-le-motif-transverse-l-objet-d-une-reserve-et-le-vivier-de-quatre-refus.md` |
| `PCL-R1` | `squad/conformite/FICHE-designation-plan-de-la-classe.md` |
| `PCL-R2` | `squad/conformite/FICHE-designation-plan-de-la-classe.md` |
| `PCL-R3` | `squad/conformite/FICHE-designation-plan-de-la-classe.md` |
| `PCL-R4` | `squad/conformite/FICHE-designation-plan-de-la-classe.md` |
| `PCL-R5` | `squad/conformite/FICHE-designation-plan-de-la-classe.md` |
| `PCL-R6` | `squad/conformite/FICHE-designation-plan-de-la-classe.md` |
| `PER-R1` | `squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md` |
| `PER-R2` | `squad/conformite/FICHE-bandit-manchot-permis-interdit-cp.md` |
| `PER-R3` | `squad/conformite/FICHE-bandit-manchot-permis-interdit-cp.md` |
| `PLA-1` | `squad/conformite/ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-borne.md` |
| `PLU-1` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `PLU-2` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `PLU-3` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `PLU-4` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `PLU-5` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `PLU-6` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `PLU-7` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `PLU-8` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `PLU-9` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `PORTE-1` | `squad/conformite/FICHE-barre-entree-niveau.md` |
| `PORTE-2` | `squad/conformite/FICHE-barre-entree-niveau.md` |
| `PORTE-3` | `squad/conformite/FICHE-barre-entree-niveau.md` |
| `PORTE-4` | `squad/conformite/FICHE-barre-entree-niveau.md` |
| `PORTE-5` | `squad/conformite/FICHE-barre-entree-niveau.md` |
| `PORTE-6` | `squad/conformite/FICHE-barre-entree-niveau.md` |
| `POL-R1` | `squad/conformite/FICHE-bandit-manchot-formules-de-politesse-cp.md` |
| `POL-R2` | `squad/conformite/FICHE-bandit-manchot-formules-de-politesse-cp.md` |
| `POL-R3` | `squad/conformite/FICHE-bandit-manchot-formules-de-politesse-cp.md` |
| `POL-R4` | `squad/conformite/FICHE-bandit-manchot-formules-de-politesse-cp.md` |
| `POS-1` | `squad/conformite/ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md` |
| `POS-2` | `squad/conformite/ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md` |
| `POS-3` | `squad/conformite/ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md` |
| `POS-4` | `squad/conformite/ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md` |
| `POS-5` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `POS-6` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `POS-R1` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` |
| `POS-R2` | `squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md` |
| `POS-R3` | `squad/conformite/FICHE-bandit-manchot-division-posee-cm1.md` |
| `PRE-1` | `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md` |
| `PRE-2` | `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md` |
| `PRE-3` | `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md` |
| `PRE-4` | `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md` |
| `PRE-5` | `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md` |
| `PRE-6` | `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md` |
| `PRE-7` | `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md` |
| `PRE-8` | `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md` |
| `PRE-9` | `squad/conformite/FICHE-bandit-manchot-mot-manquant-cp.md` |
| `PRE-10` | `squad/conformite/FICHE-bandit-manchot-mot-manquant-cp.md` |
| `PRE-11` | `squad/conformite/FICHE-bandit-manchot-mot-manquant-cp.md` |
| `PRE-12` | `squad/conformite/FICHE-bandit-manchot-mot-manquant-cp.md` |
| `PRE-13` | `squad/conformite/FICHE-bandit-manchot-mot-manquant-cp.md` |
| `PRE-14` | `squad/conformite/FICHE-bandit-manchot-mot-manquant-cp.md` |
| `PRE-15` | `squad/conformite/FICHE-bandit-manchot-mot-manquant-cp.md` |
| `PRE-16` | `squad/conformite/FICHE-bandit-manchot-mot-manquant-cp.md` |
| `QUA-1` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `QUA-2` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `QUA-3` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `QUA-4` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `QUA-5` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `RAN-1` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `RAN-2` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `RAN-3` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `RAN-4` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `RAN-5` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `RAN-6` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `RAN-7` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `RAN-8` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `REC-1` | `squad/conformite/ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md` |
| `REC-2` | `squad/conformite/ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md` |
| `REC-3` | `squad/conformite/ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md` |
| `REC-4` | `squad/conformite/ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md` |
| `REC-5` | `squad/conformite/ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md` |
| `REC-6` | `squad/conformite/ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md` |
| `REG-R1` | `squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md` |
| `REG-R2` | `squad/conformite/FICHE-bandit-manchot-regles-de-vie-cp.md` |
| `REG-R3` | `squad/conformite/FICHE-bandit-manchot-regles-de-vie-cp.md` |
| `REG-R4` | `squad/conformite/FICHE-bandit-manchot-regles-de-vie-cp.md` |
| `REG-R5` | `squad/conformite/FICHE-bandit-manchot-regles-de-vie-cp.md` |
| `REP-1` | `squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md` |
| `REP-2` | `squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md` |
| `REP-3` | `squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md` |
| `REP-4` | `squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md` |
| `REP-5` | `squad/conformite/FICHE-bandit-manchot-reperes-cm1.md` |
| `REP-6` | `squad/conformite/ARBITRAGE-typographie-espace-avant-signe-double.md` |
| `REP-7` | `squad/conformite/FICHE-bandit-manchot-reperes-cm1.md` |
| `REP-8` | `squad/conformite/FICHE-bandit-manchot-comprehension-cm1.md` |
| `REP-9` | `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md` |
| `REP-10` | `squad/conformite/FICHE-bandit-manchot-reperes-cm1.md` |
| `REP-11` | `squad/conformite/FICHE-bandit-manchot-reperes-cm1.md` |
| `REP-12` | `squad/conformite/FICHE-bandit-manchot-reperes-cm1.md` |
| `REP-13` | `squad/conformite/FICHE-bandit-manchot-reperes-cm1.md` |
| `REP-14` | `squad/conformite/FICHE-bandit-manchot-reperes-cm1.md` |
| `RGA-R1` | `squad/conformite/FICHE-bandit-manchot-regimes-alimentaires-cm1.md` |
| `RGA-R2` | `squad/conformite/FICHE-bandit-manchot-regimes-alimentaires-cm1.md` |
| `RSL-R1` | `squad/conformite/FICHE-bandit-manchot-regle-souhait-loi-cp.md` |
| `RSL-R2` | `squad/conformite/FICHE-bandit-manchot-regle-souhait-loi-cp.md` |
| `RES-1` | `squad/conformite/ARBITRAGE-PREALABLE-s34-le-motif-transverse-l-objet-d-une-reserve-et-le-vivier-de-quatre-refus.md` |
| `RES-2` | `squad/conformite/ARBITRAGE-PREALABLE-s34-le-motif-transverse-l-objet-d-une-reserve-et-le-vivier-de-quatre-refus.md` |
| `RES-3` | `squad/conformite/ARBITRAGE-PREALABLE-s34-le-motif-transverse-l-objet-d-une-reserve-et-le-vivier-de-quatre-refus.md` |
| `RES-4` | `squad/conformite/ARBITRAGE-PREALABLE-s34-le-motif-transverse-l-objet-d-une-reserve-et-le-vivier-de-quatre-refus.md` |
| `REV-1` | `squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md` |
| `REV-2` | `squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md` |
| `REV-3` | `squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md` |
| `SAI-1` | `squad/conformite/ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md` |
| `SAI-2` | `squad/conformite/ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md` |
| `SAI-3` | `squad/conformite/ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md` |
| `SAI-4` | `squad/conformite/ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md` |
| `SAI-5` | `squad/conformite/ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md` |
| `SAI-6` | `squad/conformite/ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md` |
| `SAI-7` | `squad/conformite/ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md` |
| `SAI-8` | `squad/conformite/ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md` |
| `SAI-9` | `squad/conformite/ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md` |
| `SAI-10` | `squad/conformite/ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md` |
| `SAI-11` | `squad/conformite/ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md` |
| `SAI-12` | `squad/conformite/ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md` |
| `SAI-R1` | `squad/conformite/PASSE-TRANSVERSE-s30-les-trois-familles-de-portee-depot.md` |
| `SAI-R2` | `squad/conformite/FICHE-saisie-mot-dicte-cp.md` |
| `SCI-1` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` |
| `SCI-2` | `squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md` |
| `SCI-3` | `squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md` |
| `SCI-4` | `squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md` |
| `SCO-1` | `docs/backlog/backlog.md` |
| `SCO-2` | `docs/backlog/backlog.md` |
| `SCO-3` | `docs/backlog/backlog.md` |
| `SCO-4` | `docs/backlog/backlog.md` |
| `SCO-5` | `docs/backlog/backlog.md` |
| `SCO-6` | `docs/backlog/backlog.md` |
| `SCO-7` | `docs/backlog/backlog.md` |
| `SCO-R1` | `squad/conformite/FICHE-bandit-manchot-additions-cp.md` |
| `SCO-R2` | `squad/conformite/FICHE-bandit-manchot-additions-cp.md` |
| `SCO-R3` | `squad/conformite/FICHE-bandit-manchot-additions-cp.md` |
| `SCO-R4` | `squad/conformite/PASSE-TRANSVERSE-s35-ecran-de-fin-de-partie.md` |
| `SEC-R1` | `squad/conformite/FICHE-bandit-manchot-regles-securite-eps-cp.md` |
| `SEC-R2` | `squad/conformite/FICHE-bandit-manchot-regles-securite-eps-cp.md` |
| `SEC-R3` | `squad/conformite/FICHE-bandit-manchot-regles-securite-eps-cp.md` |
| `SEC-R4` | `squad/conformite/FICHE-bandit-manchot-regles-securite-eps-cp.md` |
| `SEC-R5` | `squad/conformite/FICHE-bandit-manchot-regles-securite-eps-cp.md` |
| `SEC-R6` | `squad/conformite/FICHE-bandit-manchot-regles-securite-eps-cp.md` |
| `SEG-1` | `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md` |
| `SEG-2` | `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md` |
| `SEG-3` | `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md` |
| `SEG-4` | `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md` |
| `SEG-5` | `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md` |
| `SEG-6` | `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md` |
| `SEG-7` | `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md` |
| `SEG-8` | `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md` |
| `SEG-9` | `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md` |
| `SEG-10` | `squad/conformite/FICHE-bandit-manchot-syllabes-cp.md` |
| `SEG-11` | `squad/conformite/FICHE-bandit-manchot-syllabes-cp.md` |
| `SEG-12` | `squad/conformite/FICHE-bandit-manchot-syllabes-cp.md` |
| `SEG-13` | `squad/conformite/FICHE-bandit-manchot-syllabes-cp.md` |
| `SEM-1` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `SEM-2` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `SEM-3` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `SEM-4` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `SEM-5` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `SEM-6` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `SEM-7` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `SEM-8` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `SEM-9` | `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md` |
| `SEUIL-1` | `squad/conformite/ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md` |
| `SEUIL-2` | `squad/conformite/ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md` |
| `SEUIL-3` | `squad/conformite/ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md` |
| `SEUIL-4` | `squad/conformite/ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md` |
| `SEUIL-5` | `squad/conformite/ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md` |
| `SIG-R1` | `squad/conformite/FICHE-bandit-manchot-signal-information-cm1.md` |
| `SIG-R2` | `squad/conformite/FICHE-bandit-manchot-signal-information-cm1.md` |
| `SOP-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `SOP-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `SOP-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `SOP-CE1-4` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `SOP-CE1-5` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `SOP-CE1-6` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` |
| `SOP-CE1-R1` | `squad/conformite/FICHE-bandit-manchot-soustractions-posees-ce1.md` |
| `SOR-1` | `squad/conformite/FICHE-bandit-manchot-comparer-cp.md` |
| `SOR-2` | `squad/conformite/FICHE-bandit-manchot-reperes-cm1.md` |
| `SOR-3` | `squad/conformite/FICHE-bandit-manchot-comparer-cp.md` |
| `SOR-4` | `squad/conformite/FICHE-bandit-manchot-comparer-cp.md` |
| `SUB-1` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `SUB-2` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `SUB-3` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `SUB-4` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `SUB-5` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `SUB-6` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `SUB-7` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `SUB-8` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `SUB-9` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `SUB-10` | `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` |
| `SUB-11` | `squad/conformite/ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md` |
| `SUB-12` | `squad/conformite/FICHE-bandit-manchot-substituts-cm1.md` |
| `SYL-1` | `squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md` |
| `SYL-2` | `squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md` |
| `SYL-3` | `squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md` |
| `SYL-4` | `squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md` |
| `SYM-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md` |
| `SYM-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md` |
| `SYM-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md` |
| `SYN-1` | `squad/conformite/ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md` |
| `SYN-2` | `squad/conformite/ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md` |
| `SYN-3` | `squad/conformite/ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md` |
| `SYN-4` | `squad/conformite/ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md` |
| `SYN-5` | `squad/conformite/FICHE-bandit-manchot-synonymes-cm1.md` |
| `SYN-6` | `squad/conformite/ARBITRAGE-PREALABLE-s19-antonymes-cm1-et-nature-du-mot-cp.md` |
| `SYN-7` | `squad/conformite/FICHE-bandit-manchot-synonymes-cm1.md` |
| `SYN-8` | `squad/conformite/FICHE-bandit-manchot-synonymes-cm1.md` |
| `SYN-9` | `squad/conformite/ARBITRAGE-PREALABLE-s19-antonymes-cm1-et-nature-du-mot-cp.md` |
| `SYN-10` | `squad/conformite/FICHE-bandit-manchot-synonymes-cm1.md` |
| `SYN-11` | `squad/conformite/FICHE-bandit-manchot-synonymes-cm1.md` |
| `SYN-12` | `squad/conformite/ARBITRAGE-PREALABLE-s19-antonymes-cm1-et-nature-du-mot-cp.md` |
| `SYN-13` | `squad/conformite/FICHE-bandit-manchot-synonymes-cm1.md` |
| `SYN-14` | `squad/conformite/FICHE-bandit-manchot-synonymes-cm1.md` |
| `SYN-15` | `squad/conformite/FICHE-bandit-manchot-synonymes-cm1.md` |
| `SYN-16` | `squad/conformite/ARBITRAGE-PREALABLE-s19-antonymes-cm1-et-nature-du-mot-cp.md` |
| `TYP-1` | `squad/conformite/ARBITRAGE-typographie-espace-avant-signe-double.md` |
| `VIV-R1` | `squad/conformite/FICHE-bandit-manchot-vivant-non-vivant-cp.md` |
| `VIV-R2` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` |
| `VOI-1` | `squad/conformite/ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md` |
| `VOI-2` | `squad/conformite/ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md` |
| `VOI-3` | `squad/conformite/ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md` |
| `VOI-4` | `squad/conformite/ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md` |
| `VOI-5` | `squad/conformite/ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md` |
| `VOI-6` | `squad/conformite/ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md` |
| `VOI-7` | `squad/conformite/ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md` |
| `VOI-8` | `squad/conformite/ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md` |
| `VOI-9` | `squad/conformite/ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md` |
| `VOI-10` | `squad/conformite/ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md` |
| `VOI-11` | `squad/conformite/ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md` |
| `VOI-12` | `squad/conformite/ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md` |
| `VOI-13` | `squad/conformite/ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md` |
| `VOI-14` | `squad/conformite/ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md` |
| `VOY-R1` | `squad/conformite/FICHE-bandit-manchot-voyelle-du-son-cp.md` |
| `VOY-R2` | `squad/conformite/FICHE-bandit-manchot-voyelle-du-son-cp.md` |
| `VOY-R3` | `squad/conformite/FICHE-bandit-manchot-voyelle-du-son-cp.md` |
| `VOY-R4` | `squad/conformite/FICHE-bandit-manchot-voyelle-du-son-cp.md` |
| `VOY-R5` | `squad/conformite/FICHE-bandit-manchot-voyelle-du-son-cp.md` |
| `VR-1` | `squad/conformite/ARBITRAGE-PREALABLE-s13-voie-de-rendu-des-propositions.md` |
| `VR-2` | `squad/conformite/ARBITRAGE-PREALABLE-s13-voie-de-rendu-des-propositions.md` |
| `VR-3` | `squad/conformite/ARBITRAGE-PREALABLE-s13-voie-de-rendu-des-propositions.md` |
| `VR-4` | `squad/conformite/ARBITRAGE-PREALABLE-s13-voie-de-rendu-des-propositions.md` |
| `VR-5` | `squad/conformite/ARBITRAGE-PREALABLE-s13-voie-de-rendu-des-propositions.md` |
| `VR-6` | `squad/conformite/ARBITRAGE-PREALABLE-s13-voie-de-rendu-des-propositions.md` |
| `VR-7` | `squad/conformite/ARBITRAGE-PREALABLE-s13-voie-de-rendu-des-propositions.md` |
| `ZON-1` | `squad/conformite/ARBITRAGE-s28-le-palier-2-la-vue-et-les-six-lignes.md` |
| `ZON-2` | `squad/conformite/ARBITRAGE-s28-le-palier-2-la-vue-et-les-six-lignes.md` |
| `ZON-3` | `squad/conformite/ARBITRAGE-s28-le-palier-2-la-vue-et-les-six-lignes.md` |
| `ZON-4` | `squad/conformite/ARBITRAGE-s28-le-palier-2-la-vue-et-les-six-lignes.md` |
| `ZON-5` | `squad/conformite/ARBITRAGE-s28-le-palier-2-la-vue-et-les-six-lignes.md` |
| `ZON-6` | `squad/conformite/ARBITRAGE-s28-le-palier-2-la-vue-et-les-six-lignes.md` |
| `ZON-7` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-palier-2-ce-qu-un-paquet-de-designation-doit-apporter.md` |
| `ZON-8` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-palier-2-ce-qu-un-paquet-de-designation-doit-apporter.md` |
| `ZON-9` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-palier-2-ce-qu-un-paquet-de-designation-doit-apporter.md` |
| `ZON-10` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-palier-2-ce-qu-un-paquet-de-designation-doit-apporter.md` |
| `ZON-11` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-palier-2-ce-qu-un-paquet-de-designation-doit-apporter.md` |
| `ZON-12` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-palier-2-ce-qu-un-paquet-de-designation-doit-apporter.md` |
| `ZON-13` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-palier-2-ce-qu-un-paquet-de-designation-doit-apporter.md` |
| `ZON-14` | `squad/conformite/ARBITRAGE-PREALABLE-s29-le-palier-2-ce-qu-un-paquet-de-designation-doit-apporter.md` |
| `ZON-15` | `squad/conformite/FICHE-designation-frise-de-la-journee.md` |
| `ZON-16` | `squad/conformite/FICHE-designation-frise-de-la-journee.md` |
| `MES-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md` |
| `MES-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md` |
| `MES-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md` |
| `MON-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md` |
| `MON-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md` |
| `MON-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md` |
| `MON-CE1-4` | `squad/conformite/ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md` |
| `SOL-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md` |
| `SOL-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md` |
| `SOL-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md` |
| `SOL-CE1-4` | `squad/conformite/ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md` |
| `NUQ-CP-R1` | `squad/conformite/FICHE-bandit-manchot-objets-numeriques-cp.md` |
| `NUQ-CP-R2` | `squad/conformite/FICHE-bandit-manchot-objets-numeriques-cp.md` |
| `EPS-CM1-R1` | `squad/conformite/FICHE-bandit-manchot-effets-activite-eps-cm1.md` |
| `EPS-CM1-R2` | `squad/conformite/FICHE-bandit-manchot-effets-activite-eps-cm1.md` |
| `EPS-CM1-R3` | `squad/conformite/FICHE-bandit-manchot-effets-activite-eps-cm1.md` |
| `EPS-CM1-R4` | `squad/conformite/FICHE-bandit-manchot-effets-activite-eps-cm1.md` |
| `DUR-CE1-R1` | `squad/conformite/FICHE-bandit-manchot-problemes-durees-ce1.md` |
| `MUS-CP-R1` | `squad/conformite/FICHE-bandit-manchot-vocabulaire-musical-cp.md` |
| `EVO-CM1-R1` | `squad/conformite/FICHE-bandit-manchot-evolution-objet-technique-cm1.md` |
| `EVO-CM1-R2` | `squad/conformite/FICHE-bandit-manchot-evolution-objet-technique-cm1.md` |
| `EVO-CM1-R3` | `squad/conformite/FICHE-bandit-manchot-evolution-objet-technique-cm1.md` |
| `EVO-CM1-R4` | `squad/conformite/FICHE-bandit-manchot-evolution-objet-technique-cm1.md` |
| `DIV-CE1-R1` | `squad/conformite/FICHE-bandit-manchot-division-ce1.md` |
| `MUS-CP-R2` | `squad/conformite/FICHE-bandit-manchot-instruments-musicaux-cp.md` |
| `MUS-CP-R3` | `squad/conformite/FICHE-bandit-manchot-instruments-musicaux-cp.md` |
| `EPS-CM1-R5` | `squad/conformite/FICHE-bandit-manchot-roles-eps-cm1.md` |
| `EPS-CM1-R6` | `squad/conformite/FICHE-bandit-manchot-roles-eps-cm1.md` |
| `USN-CM1-R1` | `squad/conformite/FICHE-bandit-manchot-usages-numeriques-cm1.md` |
| `USN-CM1-R2` | `squad/conformite/FICHE-bandit-manchot-usages-numeriques-cm1.md` |
| `USN-CM1-R3` | `squad/conformite/FICHE-bandit-manchot-usages-numeriques-cm1.md` |
| `USN-CM1-R4` | `squad/conformite/FICHE-bandit-manchot-usages-numeriques-cm1.md` |
| `USN-CM1-R5` | `squad/conformite/FICHE-bandit-manchot-usages-numeriques-cm1.md` |
| `USN-CM1-R6` | `squad/conformite/FICHE-bandit-manchot-usages-numeriques-cm1.md` |
| `USN-CM1-R7` | `squad/conformite/FICHE-bandit-manchot-usages-numeriques-cm1.md` |
| `USN-CM1-R8` | `squad/conformite/FICHE-bandit-manchot-usages-numeriques-cm1.md` |
| `USN-CM1-R9` | `squad/conformite/FICHE-bandit-manchot-usages-numeriques-cm1.md` |
| `MVT-CM1-R1` | `squad/conformite/FICHE-bandit-manchot-mouvement-cm1.md` |
| `MVT-CM1-R2` | `squad/conformite/FICHE-bandit-manchot-mouvement-cm1.md` |
| `MVT-CM1-R3` | `squad/conformite/FICHE-bandit-manchot-mouvement-cm1.md` |
| `MVT-CM1-R4` | `squad/conformite/FICHE-bandit-manchot-mouvement-cm1.md` |
| `PHN-CM1-R1` | `squad/conformite/FICHE-bandit-manchot-phenomenes-naturels-cm1.md` |
| `PHN-CM1-R2` | `squad/conformite/FICHE-bandit-manchot-phenomenes-naturels-cm1.md` |
| `PHN-CM1-R3` | `squad/conformite/FICHE-bandit-manchot-phenomenes-naturels-cm1.md` |
| `PHN-CM1-R4` | `squad/conformite/FICHE-bandit-manchot-phenomenes-naturels-cm1.md` |
| `LON-CE1-5` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `TRV-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `TRV-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `TRV-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `TRV-CE1-4` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `TRV-CE1-5` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `TRV-CE1-6` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `TRV-CE1-7` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `GRC-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `GRC-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `GRC-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `GRC-CE1-R1` | `squad/conformite/FICHE-bandit-manchot-graphemes-complexes-ce1.md` |
| `VLE-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `VLE-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `VLE-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `MFR-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `MFR-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `MFR-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `RCT-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `RCT-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `RCT-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `PRN-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `PRN-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `PRN-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `INF-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `INF-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `INF-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `DOC-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `DOC-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `DOC-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `OMP-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `OMP-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `OMP-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `RLP-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `RLP-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `RLP-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `TPH-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `TPH-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `TPH-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `NEG-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `NEG-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `NEG-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `CLM-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `CLM-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `CLM-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `VSJ-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `VSJ-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `VSJ-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `ACV-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `ACV-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `ACV-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `GNA-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `GNA-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `GNA-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `HOM-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `HOM-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `HOM-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `PRS-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `PRS-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `PRS-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `FUT-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `FUT-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `FUT-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `PCO-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `PCO-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `PCO-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `TPS-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `TPS-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `TPS-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `MIV-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `MIV-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `MIV-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `GRL-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `GRL-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `GRL-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `ALP-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `ALP-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `ALP-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `FAM-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `FAM-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `FAM-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `SYC-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `SYC-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `SYC-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `CTG-CE1-1` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `CTG-CE1-2` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `CTG-CE1-3` | `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
