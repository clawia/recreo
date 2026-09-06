# [Sprint 29] Verdicts de `nadia-prof` - **Instance A** - 2026-09-06

- **Auteur** : `nadia-prof`, **Instance A** (`PAR-7` : instance qui porte l'accumulation).
- **Arbre audite** : `round-two` a **`6b2edf1`**.
- **Perimetre** : `S29-2`, `S29-1`, `S29-7`, `S29-8`, `S29-3`. **Je n'ai pas relu les lots de
  l'Instance B** - EMC aux deux cycles, division posee. C'est ma propre specification : *"aucune des
  deux instances ne relit l'autre."*
- **A reprendre par `sofia-sm` dans `review.md` a la cloture.** Je n'ecris pas la review : c'est son
  artefact, et l'Instance B ne l'a pas ecrit non plus ce sprint. Les verdicts font foi dans les
  fiches ; ceci en est le releve.

## Done technique - acquis, mesure par moi et non reconduit

`bash outils/mvn-jdk21.sh -B clean verify` sur le **reacteur complet** (racine, **pas de `-pl`**) :
**BUILD SUCCESS**. Comptage independant des rapports XML de tous les modules du reacteur (methode de
l'amendement s11) : **169 rapports, 1259 tests, 0 echec, 0 erreur, 0 ignore**. Machine virtuelle des
tests : **21.0.10**, demandee au JDK resolu.

*Un incident de mesure, ecrit pour que personne ne le redecouvre : ma premiere execution a rendu
`BUILD FAILURE`, et la cause etait mon propre jar en cours d'execution, qui empechait
`maven-clean-plugin` d'effacer `recreo.jar`. Un auditeur qui joue et qui mesure dans le meme arbre
arrete l'application avant de mesurer, sinon il impute au produit un echec qui est le sien.*

## Verdict par lot

| Story | Objet | Verdict | Lignes |
|---|---|---|---|
| **`S29-2`** | histoire-geographie CM1, composition A | **VALIDE SOUS RESERVE** | **+3** |
| **`S29-1`** | questionner le monde CP, deux paquets | **VALIDE SOUS RESERVE** (les deux) | **+2** |
| **`S29-7`** | cinq reserves de s28 | **quatre levees, une tranchee** | 0 |
| **`S29-8`** | dette des paquets a voix | **payee**, six jeux verifies | 0 |
| **`S29-3`** | passe transverse, portee depot | rendue, deux defauts trouves | 0 |

**Total Instance A : cinq lignes ferment.** Avec les trois de l'Instance B, **huit pour le sprint** -
**le haut de mon attente raisonnable** (7 a 8), sous le plafond arithmetique de 9. Taux de sortie
attendu : **51 / 138 = 37,0 %**, exactement le chiffre que mon arbitrage annoncait pour cette
composition. **Le compte de la matrice appartient a `camille-po` ; je ne la touche pas.**

## Les cinq lignes qui ferment, avec le motif de chacune

| Ligne | Jeu | Motif |
|---|---|---|
| **`QLM-CP-02`** | `bandit-manchot-cycle-de-vie-cp` | `FER-1` : les cinq caracteres du libelle chacun en bonne reponse, verifies un par un. `FER-2` : 11 items. `FER-3` : deux operations mentales distinctes, 5 et 6. Exactitude : 11 sur 11, y compris l'item de la plante, que j'ai instruit et confirme. Lisibilite : mediane 7 mots |
| **`QLM-CP-07`** | `bandit-manchot-objet-technique-cp` | `FER-1` : les deux moities du libelle servies (fonction 5, parties 6). `FER-2` : 11 items. `FER-3` : deux taches par construction. Exactitude : 11 sur 11. Lisibilite : mediane 7 mots, tout le lexique du quotidien |
| **`HG-CM1-06`** | `bandit-manchot-histoire-geographie-cm1` | `FER-1` : les **quatre realisations** de la parenthese enfin servies en bonne reponse - elles ne l'etaient pas avant ce lot. `FER-2` : 10. `FER-3` : deux taches a 4 items. Aucune tradition parmi les faits, refus pose d'avance respecte |
| **`HG-CM1-07`** | idem | `FER-1` : les quatre evenements de la parenthese. `FER-2` : 10. `FER-3` : 7 contre 3, **exactement au plancher**, et je le dis tel quel. Chronologie revolutionnaire juste sur les dix items |
| **`HG-CM1-14`** | idem | `FER-1` : les quatre composantes du libelle, dont **nature et auteur**, que j'avais nommees comme manquantes a s28. `FER-2` : 10. `FER-3` : 6 contre 4. **Ferme sur sa part servie, avec la mention exigee d'avance** |

## Ce qui ne ferme pas, et le motif

- **Les six autres lignes d'histoire-geographie CM1** (`-01`, `-02`, `-03`, `-04`, `-08`, `-12`)
  restent `en cours` : ce lot ne les a pas touchees, et cinq d'entre elles portent **exactement un
  item**. *En cours* y reste le mot le plus optimiste qui soit exact.
- **`HG-CM1-05`, `-09`, `-10`** ne sont pas `en cours` du tout : jamais servies, et deux d'entre
  elles sont hors de portee du palier 1. **Le chiffre de "neuf lignes en cours" que j'ai ecrit deux
  fois a vieilli** : il en reste six. A ne pas recopier.
- **Aucune ligne de `S29-7` ni de `S29-8`** : ces deux stories corrigent des lignes deja
  `couvert-valide`. **Aucune ligne de matrice ne bouge de leur fait**, ni dans un sens ni dans
  l'autre.

## Mentions exigees d'avance, a reprendre **mot pour mot** par `camille-po` dans la matrice

> Sur **`HG-CM1-14`** : *"document textuel seul ; carte et image restent non couvertes"*

**Sans elle, je n'aurais pas laisse cette ligne fermer.** Elle est deja ecrite mot pour mot dans le
commentaire du paquet par `marc-dev`, ce dont je le credite. Motif : le libelle de la ligne dit
*"un document (texte, carte, image)"* ; les dix items portent tous un `texteSupport` ecrit. Une ligne
verte sans cette mention laisserait croire que le produit apprend a lire une carte.

**Aucune mention n'est due sur `QLM-CP-02` ni sur `QLM-CP-07`** - je le dis explicitement, parce que
j'en exige sur d'autres et qu'il faut savoir dire quand il n'y en a pas. Les deux libelles sont
servis en entier.

*Rappel, hors de mon perimetre de ce jour* : la mention de `LVE-CM1-07` (*"decouverte culturelle
seule, en francais ; aucune competence linguistique n'est servie"*) reste due et inchangee.

## `S29-7` - les cinq reserves, une par une

| Reserve | Verdict | Motif |
|---|---|---|
| `COR-R2` | **levee** | `la langue` et son indice servis mot pour mot ; l'erreur de fait est payee. Verifie en plus que rien n'est casse sur la ligne verte : 14 items relus, `le nez` reste plausible, aucune contradiction creee |
| `EAU-R1` | **levee** | Indice servi mot pour mot. Verifie en plus que le perimetre du paquet ne s'est pas relache - **aucune vapeur, aucun etat gazeux** - et que le mot *couler* de mon indice n'entre pas en conflit avec le distracteur `couler` des deux items de nommage |
| `HG-R5` | **levee** | Indice du village de pecheurs servi mot pour mot |
| `HG-R4` | **levee** | Les vingt enonces du lot vont de 6 a 21 mots, la mediane du fichier reste a 16, les deux simplifications sont faites comme dictees. **Les trois exigences que j'avais ecrites sont tenues.** `marc-dev` a declare un ecart contre une lecture **plus severe que la mienne** ; cet ecart n'existe pas contre mon texte, et je ne le lui compte pas |
| `ANG-R1` | **levee** | `hill`/`colline` tient `REC-5` et `REC-6`, mieux que `mountain`. **Mais je n'avais fourni aucun texte** : ce choix est de `marc-dev`, je viens de le juger. Voir la correction de tarif ci-dessous |
| `VIV-R2` | **tranchee, toujours ouverte** | Section suivante |

## `VIV-R2` - ma decision

**J'ai rejoue, et je tranche.** Detail complet dans `FICHE-bandit-manchot-vivant-non-vivant-cp.md`,
dernier bloc.

1. **`marc-dev` a eu raison de ne pas l'implementer.** J'avais qualifie mon remplacement de *"piste
   que je nomme sans la prescrire"* : l'executer aurait ete trancher a ma place. **Le defaut est de
   moi.**
2. **J'abandonne ma piste.** Remplacer `ce n'est pas un etre vivant` par `de la matiere` echoue :
   *matiere*, au sens de categorie du monde physique, est une abstraction de cycle 3. **Ma piste
   aurait remplace un probleme de lisibilite par un probleme de niveau.**
3. **Je corrige une phrase de moi.** J'avais ecrit *"la formule actuelle reste servie et elle est
   juste"*. Elle est juste **dans le fichier** ; elle ne l'est pas **a l'ecran** : apres trois
   erreurs, l'enfant lit *"C'est ce n'est pas un etre vivant. Appuie sur ce n'est pas un etre
   vivant."* **Je l'ai lu en jouant. C'est un second motif, et il renforce la reserve.**
4. **Je prescris**, mot pour mot, les trois enonces de remplacement (forme *"Un caillou est-il un
   etre vivant ?"* -> `non` / `oui`), avec la verification que `FER-1`, `FER-2` et `FER-3` restent
   intactes et que **la fermeture de `QLM-CP-01` n'est pas remise en cause**. **Regime : mot pour
   mot, 0,25.**
5. **La reserve reste ouverte et non payee.** Elle est desormais **executable**.

## `S29-8` - la dette des paquets a voix

**Payee, apres trois sprints d'attente ; elle aurait eu quatre sprints si s29 ne l'avait pas payee.**
Les six jeux verifies un par un sur le contenu servi : cinq enonces et cinq indices repris mot pour
mot, `cp-mot-dicte` inchange comme demande. **Aucune occurrence de *doucement* ni de *lentement* ne
subsiste dans un indice de paquet a voix.** Le cas grave - `cp-son-dans-mot`, ou un mot ralenti donne
la reponse - est paye et je l'ai verifie **a l'ecran**, bloc adulte et indice cote a cote.

**Un jeu voisin ecarte avec son motif** : `bandit-manchot-syllabes-cp` porte des indices qui disent
*"tres lentement"*, mais il **ecrit le mot a l'ecran** et c'est l'enfant qui le prononce - il n'a ni
`texteAVoixHaute` ni `consigneAdulte`. **`VOI-3` gouverne la voix de l'adulte ; ce jeu n'en a pas.**

## `S29-3` - resultat de la passe transverse, separement des verdicts de lot

**Document complet** : `squad/conformite/PASSE-TRANSVERSE-s29-les-proprietes-de-portee-depot.md`.
**Deux defauts que rien d'autre dans ce sprint n'aurait trouves**, plus quatre constats.

| Trouvaille | Nature |
|---|---|
| **`REV-1`** - la phrase composee par la mecanique autour de la bonne reponse est **fautive sur 12 items de 4 paquets** (*"C'est il se nourrit."*), **maladroite sur 29 items de 5 autres**, sur 1056 items enumeres. Elle n'existe dans **aucun fichier de contenu** et n'apparait **qu'a la troisieme erreur** | defaut, symptome pedagogique, **cause technique** - route a `theo-archi` |
| **`CONS-1`** - la consigne generique *"Lis bien la question, puis choisis la bonne reponse."* est passee de **5 jeux a 10 en un sprint**, mesuree sur trois etats du catalogue. Elle ne porte aucune tache, contre ma propre regle de s28 | defaut, non bloquant, route a `camille-po` |
| La dette a voix est **payee** sur les six jeux, un septieme ecarte avec motif | constat |
| **Aucune collision d'identifiant creee par s29** ; quatre prefixes (`REV`, `CONS`, `CYV`, `OBJ`) alloues et ecrits | constat |
| **Deux de mes conditions etaient incoherentes entre elles** (`ANG-R1` et `VIV-R2` prices a tort au regime verbatim) ; corrige sans supplement facture | correction de moi |
| **`CYC-1` est vacante partout, pas tenue** - aucun paquet de cycle 3 heritier n'est servi | constat |

**Le fait de methode** : la parallelisation **n'a pas cause** ces deux defauts, qui sont anterieurs a
s29 - **elle les aurait laisses dormir**, parce qu'en serie je les aurais rencontres en accumulant.
**C'est ce que `PAR-0` predisait, et c'est maintenant verifie plutot que suppose.**

## Ce que mon vert ne prouve pas

**Dit explicitement, comme a chaque fois, pour que l'equipe ne croie pas couvert ce qui ne l'est
pas.**

1. **Aucun test du depot ne sait qu'un enonce scolaire est vrai.** Les tests de paquet gardent des
   chaines et des comptes **que j'ai dictes** : ils auraient garde `la bouche` pour l'organe du gout
   aussi fidelement que `la langue`, **et ils l'ont fait pendant deux sprints**. Les 57 faits
   d'histoire-geographie et les 22 items de questionner le monde ne sont tenus que par ma lecture, et
   **cette lecture ne se rejoue pas toute seule au sprint suivant**.
2. **`FER-1` et `FER-3` ne sont pas automatisables.** Le **compte** de `FER-2` est derive depuis
   `ADR-0081` ; *"quel element du libelle est en position de bonne reponse"* et *"est-ce la meme
   operation mentale"* restent des jugements, et ils sont a moi seule.
3. **La plausibilite d'un distracteur n'a aucun garde-fou.** C'est la propriete d'`OBJ-R2` et de
   `HG-R6`, deux des trois reserves que j'ouvre aujourd'hui - et c'est deja ce qui m'avait echappe a
   s27 sur l'item rural.
4. **`VOI-3`, `VOI-5` et `VOI-8` ne sont gardees par personne, pas meme par moi.** Ce que s29 repare,
   c'est que le produit cessait de demander **par ecrit** le contraire de ce qu'il demande a
   l'adulte. Un adulte qui ralentit quand meme n'est arrete par rien.
5. **`REV-1` demontre qu'un audit qui joue proprement ne suffit pas.** Ce defaut n'apparait qu'a la
   troisieme erreur volontaire sur une manche. **Il a traverse tous les sprints de contenu du
   depot**, sur des lignes que j'avais fermees moi-meme.
6. **Un item rare peut n'etre jamais tire.** Le paquet d'histoire-geographie porte **57 items** et
   une partie en montre **dix** : quarante-sept ne sont jamais vus. J'ai lu les cinquante-sept ; **si
   je ne l'avais pas fait, mon vert ne vaudrait que pour un sixieme du paquet.**
7. **Je n'ai pas relu les lots de l'Instance B**, et rien dans ce document ne vaut confirmation ni
   infirmation de ses verdicts. `cm1-emc-droit-devoir` apparait dans ma mesure de `REV-1` **comme
   donnee de portee depot**, jamais comme reexamen.

## Mon prix

| Poste | Prix |
|---|---|
| `S29-2` - histoire-geographie CM1, composition A (57 items lus, trois lignes) | **1,5** |
| `S29-1` - questionner le monde CP, deux paquets de onze | **1,0** |
| `S29-7` - cinq reserves, passage verbatim | **0,25** |
| `S29-8` - dette des paquets a voix, un passage | **0,25** |
| `S29-3` - passe transverse, portee depot (`PAR-4`) | **0,5** |
| **Sous-total planifie** | **3,5** |
| `VIV-R2` tranchee - rejeu d'un paquet servi sur une propriete nommee, puis prescription | **0,25** |
| Jugement du texte neuf `hill`/`colline`, que je n'avais pas fourni (correction de mon tarif) | **0,25** |
| **Total** | **4,0 / 5,0** |

**Aucun depassement.** Les 0,5 non planifies tiennent dans ma **marge de rejeu individuelle de 0,5**,
nommee et non allouee a l'ouverture (`PAR-8`). **Je ne tire rien sur la marge de rejeu du sprint**,
qui garde ses **0,75** apres le tirage de 0,25 de l'Instance B.
