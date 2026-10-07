# Retro - s52 - cinq lignes CE1 fermees, deux instances a 4,5 sur 5,0, un planning devenu faux en quarante-sept minutes

- **Date** : 2026-10-07.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Nature du sprint** : deux instances de `nadia-prof` en parallele sur le CE1 (A francais, C mathematiques), plus un
  couloir de developpement sans instance (retrait des deux paquets de sciences CM1 refuses). Premier sprint avec un
  scratchpad, un worktree, une branche et un port par agent (`DEC-0111` section 8).
- **Sources** : `review.md` de ce dossier (obstacles, mesures), `DEC-0111` et ses quatre amendements, les huit fiches de
  conformite, les trois arbitrages de `nadia-prof`, `ls` du repertoire temporaire de la session. Je cite ces fichiers ;
  je n'ai pas relu les transcriptions de sous-agent.

---

## 1. Ce qui a marche

**La fusion dans l'ordre retrait, A, C a tenu, avec un reacteur complet sur chaque commit de fusion.** Retrait
(`789af8a9`) **2084** tests, instance A (`d06ea52f`) **2125**, instance C (`40daa419`) **2172** apres correction,
cloture **2174**. Chaque marche est la somme de la precedente et du lot de la branche (+2, +41, +47, +2 pour le test Q6) ;
les bases 2084 et 2125 que `yanis-qa` n'avait que reconstruites par arithmetique sont retrouvees par un build. Les
journaux de ces mesures sont dans le scratchpad, hors depot : je les ai relus ce jour pour les sommes, pas pour le detail.

**La condition suspensive de `MAT-CE1-17` a fait son travail en quarante-sept minutes.** Le planning (20:51) refusait
d'engager la ligne tant que le message du cliquet, le quadrillage et les reponses de `nadia-prof` n'existaient pas
sur le disque. Ordre des commits, lu par `git log --format='%h %ad' --date=format:%H:%M` : U1 `ceffde66` 21:11, U3
`48716e62` 21:26, U2 `44ac6c02` 21:28, U4 `ee3ff6cf` 21:38. La ligne a ete engagee a 21:38, fermee au second passage
(`3c97a87a`, 23:18), au dixieme sprint depuis son instruction. C n'a ete ouvert qu'apres U1 : A1 n'a pas ete commite
deux fois (obstacle 4).

**Aucune reaffectation apres le planning.** Les commits de contenu portent le tag de l'agent nomme : `[MARC]` pour le
retrait, `TPS-CE1-R1`, `ALP-CE1-R1` et les trois paquets de A, `[LEA]` pour les trois paquets de C, `[THEO]` pour A1
et A2bis. Le cas de `HOM-CE1-R1` a s51 (action A8) ne s'est pas reproduit ; il n'y a rien a ecrire au tableau.

**Un refus a ete rejoue et leve dans le sprint, avec un motif unique.** `FRA-CE1-17` : refus au premier passage
(`TRV-CE1-5`, deux indices), rejeu `40989ba9`, second passage VALIDE. Le budget du rejeu etait nomme (0,5 par instance)
et a servi.

**Les chiffres annonces ont ete refaits, et deux d'entre eux ne tenaient pas.** 2174 tests : `yanis-qa` l'a mesure sur
`40daa419` plus son test, je l'ai remesure sur `509552f0`, meme total. Le compte du lexique de l'avis (266) a ete
corrige par `theo-archi` apres relecture. Le chiffre de s51 sur le registre (918, puis 923) etait faux : obstacle 3.

**Le scratchpad par agent a tenu pour les agents.** `ls` du repertoire temporaire de la session : un sous-repertoire
par agent dans chaque couloir (`retrait/marc-dev`, `instance-a/marc-dev`, `instance-a/nadia`, `instance-c/lea-dev`,
`instance-c/nadia`, `theo/theo-archi`, `ines/ines-design`, `camille/camille-po`, et `cloture/theo-archi` et
`cloture/yanis-qa`, que le planning ne prevoyait pas) ; `find ... -newermt` sur les couloirs : **aucun fichier direct
sous `instance-a`, `instance-c` ni `retrait` depuis le planning**. Les fichiers de cookies sont par agent (`cj` et
`cj2` dans `instance-c/lea-dev` et dans `instance-c/nadia`, `cookies.txt` dans `instance-a/marc-dev`) : le geste de
s51 (un fichier de cookies partage dans `instance-b`) ne s'est pas reproduit. Je n'ai pas lu les transcriptions pour
verifier qu'aucun agent n'a ecrit dans le sous-repertoire d'un autre.

## 2. Ce qui a coince

**Le planning est devenu faux en quarante-sept minutes sur six points.** `MAT-CE1-17` non engagee (re-engagee a 21:38),
C a 2,5 (4,0), engage 6,5 (8,0), `lea-dev` "si re-engagee" (affectee aux trois stories), nom du worktree du retrait,
compte des trailers. C'est le second sprint de suite (s51 : cinq points) ou le planning se perime en moins d'un jour.
L'amendement de cloture du planning les met a jour ; il n'a pas ete ecrit au moment du re-engagement, mais a la
cloture.

**Le planning a compte 568 trailers "Opus 5.5" ; c'etait la somme de quatre variantes.** La commande du planning
(`--grep='^Co-Authored-By: Claude Opus'`) est un prefixe : elle additionne ce que le paragraphe appelait "Opus 5.5" et
trois autres libelles. A `93aade92` : 63 "Opus 5.5", 369 "Opus 5", 132 "Opus 5 (1M context)", 4 "Opus 5.5 (1M context)" ;
le comparable du paragraphe est 67. Le chiffre de 18 transmis par la consigne de l'epoque reste sans source au depot.

**Le test de la symetrie n'a rougi qu'a la fusion.** `core.autocrlf=true` sur ce poste, SVG commites en LF et extraits
en CRLF, un `replaceFirst` sur `\n` sans effet, une mutation qui ne mutait rien. Premiere fusion de C : 2166 tests,
1 echec, `BUILD FAILURE`. Correctif `173b48f7`. **Je ne sais pas pourquoi il etait vert dans le worktree de `lea-dev`**
(hypothese non verifiee : dessins ecrits en LF par son script et jamais re-extraits). Cout : une fusion rejouee, et un risque de faux vert si la
correction avait affaibli l'assertion (elle l'a rendue plus stricte : la mutation asserte desormais qu'elle a change
le dessin).

**Une mesure de fusion de cloture n'a pas termine.** Le premier reacteur complet de la fusion de cloture est reste
8 h 46 min en `bootstrap` avant que son fork soit termine (code 143), dans
`AucunTexteServiNEnchasseLaReponseDansUnePhraseTest` ; trois reacteurs complets verts depuis. Cause non etablie
(obstacle 12). Cout : la mesure de la fusion de cloture n'existe qu'au matin (journal de la relance : 09:12).

**Deux instances ont chacune depense leur seul rejeu et finissent a 4,5.** A : rejeu de `FRA-CE1-17`. C : second passage
de `MAT-CE1-17`. 8,0 engages, 9,0 consommes. `SYC-CE1-R1` et `MUL-CE1-R1` (0,5 chacune) tiennent arithmetiquement
dans 5,0 mais n'ont pas ete engagees : `camille-po` a refuse de les engager sans marge de rejeu (`DEC-0111` A.13).
Le risque que `DEC-0111` section 2 acceptait (0,5 de marge au lieu des 1,5 de `PAR-11`) ne s'est pas realise parce
que chaque instance n'a eu qu'un evenement a payer.

**Trois lectures du meme plafond circulent, et une quatrieme a propos du rejeu.** (1) Pratique depuis s41 : 8,0 de
lots, rejeu en plus (decision de l'utilisatrice, appliquee a s52). (2) `PAR-11` amendee : 0,5 de marge par lot neuf.
(3) `PAR-9` maintenue par `nadia-prof` le 2026-10-06 : a partir de s53, 4,0 par instance **marge de rejeu comprise**.
(4) Le rejeu de la symetrie : 0,5 par `PAR-10` (`camille-po`) ou 5,5 par la lettre de `PAR-14` (obstacle 9). Aucun de ces
quatre points n'est tranche par moi ; s53 se chiffre sous (3).

**Des fichiers sont restes a la racine du scratchpad de session, et ce sont les miens.** `ls` : 41 fichiers a la racine.
Huit sont posterieurs au planning, tous des journaux de mesure de fusion (`verify-fusion-retrait.log`,
`verify-fusion-u1.log`, `verify-fusion-a.log`, `verify-fusion-c.log`, `verify-fusion-c2.log`,
`verify-fusion-cloture.log`, `verify-fusion-cloture2.log`, `verify-fusion-final.log`) : mes mesures de fusion, qui
auraient du aller dans `sofia/`, alors que le planning n'en prevoyait pas pour moi et que le bloc commun disait "rien
a la racine". Trois sont posterieurs a la cloture de s51 et anterieurs au planning (`a2-amendement.md`,
`JOURNAL-regenere-s51.md`, `row16.txt`). Trente datent de s51 ou d'avant. Les repertoires `camille/`, `ines/`, `theo/`
et `nadia-s52-borne/` portent a leur racine des fichiers anterieurs au planning (19:56 a 20:34 et 18:47 a 19:06) :
l'ouverture a precede la regle. Un fichier hors du scratchpad : `c3` (obstacle 5).

**Le chiffre du registre que j'avais ecrit a s51 etait faux de 4.** 918 puis 923 ; la commande de la CI rend 914 puis
919. J'ai cite "ma mesure" sans nommer la commande. **Cout** : un chiffre faux dans trois fichiers de s51 (review,
retro, rapport), non edites ici.

**L'entete `Statut global` des six fiches de s52 n'a pas suivi les verdicts** (obstacle 13).

## 3. Actions, routees et non tranchees

| # | Action | Route a | Artefact attendu |
|---|---|---|---|
| A1 | Verifier la condition (ii) de la borne a trois instances (formes (a), (b), (c)) pour s52, dernier sprint parallele ; faits remis : obstacles 2, 3 et 5 de `review.md` | `nadia-prof` | fichier de verification, ou conclusion dans l'arbitrage de s53 |
| A2 | Confirmer ou corriger le prix de 0,5 du rejeu de `SYM-CE1-R1` et dire si elle lit `PAR-14` a la lettre (C a 5,5) | `nadia-prof` | prix ecrit ; amendement de `DEC-0111` si l'ecart est confirme |
| A3 | Remplacer l'item 16 de `FRA-CE1-25` (*"Il arrive a l'ecole."*) et le re-auditer avec `SYC-CE1-R1` (items 19 et 23) ; la story est au backlog, non engagee | `camille-po` (engagement s53), `nadia-prof` (rejeu) | `DEC` de s53, fiche |
| A4 | Mettre l'entete `Statut global` des six fiches de s52 en accord avec leur verdict | `camille-po`, `nadia-prof` | fiches |
| A5 | Dire qui a ecrit `c3` a la racine du worktree de C et le supprimer, ou le declarer | `lea-dev`, `nadia-prof` | message de retour |
| A6 | Epingler le niveau `ERROR` dans `UnePartieOuverteSurUnJeuRetireNeServeAucunResultatTest` (`theo-archi` le range chez lui), et chercher la cause de la mesure interrompue (obstacle 12) | `yanis-qa` | test, ou motif ecrit de l'absence ; rapport |
| A7 | Un test qui lit un fichier du disque et suppose ses fins de ligne n'a aucun garde-fou : une regle ou un motif ecrit de son absence ? | `theo-archi` | `ADR`, ou ligne de motif |
| A8 | Statut d'`ADR-0104` apres la reponse de `nadia-prof` a `C5` clause 1 | `theo-archi` | statut de l'`ADR` |
| A9 | Decider du journal de tokens (regeneration qui degrade s22 a s27 ; section de s52 absente du journal commite, comme celle de s51) ; story `S51-OUTIL-JOURNAL-TOKENS`, troisieme report | utilisatrice, puis `theo-archi` | decision, `ADR` ou correction de `outils/rapport-tokens.py` |
| A10 | Corriger ou supprimer le fichier de toolchains du shell, perime (hors depot) | utilisatrice | poste |
| A11 | Ouvrir s53 : engager ou abandonner, avec motif, les cinq postes au troisieme report et `ART-CP-10` (`DEC-0111` A.14) | utilisatrice, `camille-po` | `DEC` d'ouverture de s53 |

## 4. Amendements au process

**Aucun amendement n'est ecrit par moi dans `squad/README.md`.** Deux propositions sont justifiees par un fait mesure
de s52 ; je les demande, je ne les ecris pas.

| Section de `squad/README.md` | Proposition | Fait mesure | Demandee a |
|---|---|---|---|
| 9 (isolation, worktrees) | Ecrire que le nom d'un worktree se choisit de sorte que *prefixe du dossier de worktrees + chemin suivi le plus long du depot* reste sous 260 caracteres sur un poste Windows, et donner la commande (`git ls-files \| awk '{ print length($0) }'`) | Le nom du planning donnait 260 ; le nom retenu 247 ; le worktree de C est a 256, quatre caracteres de marge ; le chemin le plus long est un fichier de contre-exemple de test | `theo-archi` |
| 9 (scratchpad dedie) | Donner un couloir au Scrum Master (`<scratchpad>/sofia`) pour ses journaux de mesure de fusion | Huit journaux de fusion a la racine, tous de moi | `theo-archi` |

## Decisions prises

**Aucune `DEC` ni `ADR` n'est creee ou amendee par cette retro.**
