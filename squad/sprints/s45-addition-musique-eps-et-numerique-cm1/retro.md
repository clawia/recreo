# Retro - s45 - addition avec retenue CE1, instruments de musique CP, roles d'EPS CM1 (instance
A) et usages numeriques CM1 (instance B), dispositif a deux instances paralleles de `nadia-prof`

- **Date** : 2026-10-01.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Nature du sprint** : entierement pilote par agents, sans ceremonie de daily - conforme a
  `squad/README.md`. Quatre lots de developpement affectes nominativement des l'ouverture du
  planning (`marc-dev`, `lea-dev`, `sofia-sm` casquette dev pour deux lots), deux instances de
  `nadia-prof` auditant chacune un sous-ensemble disjoint des quatre lots.

---

## 1. Ce qui a marche

**Le dispositif a deux instances paralleles a tenu sans collision, et `PAR-2` a ete reverifie
trois fois avant qu'un seul developpeur ne soit affecte** : une premiere fois par `camille-po`
dans `DEC-0101` (section 4), une seconde par `theo-archi` dans son avis de faisabilite, une
troisieme par moi dans `planning.md` (section 3), sur le perimetre reel apres le retrait de
`S45-LVE-ECRITURE`. Les quatre couples matiere/niveau retenus (mathematiques CE1, education
musicale CP, EPS CM1, sciences et technologie CM1) sont restes disjoints entre les deux instances
du debut a la fin : aucune des deux n'a audite un lot de l'autre, et rien dans les quatre fiches
de conformite ne le contredit.

**`ADR-0103` a ete ecrit et mis a l'epreuve dans les deux sens avant le premier commit de code,
et il a tenu sans aucun rouge a l'ecriture.** Le garde-fou qu'il restreint
(`ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest`) est exactement le genre de regle
qu'un paquet neuf peut faire rougir par construction si son perimetre n'est pas ajuste avant
l'ecriture - c'est l'histoire d'`ADR-0096` lui-meme, qu'`ADR-0103` amende. Cette fois, l'`ADR` est
fusionne a 10:39, avant que `marc-dev` n'ecrive une ligne de paquet ; `yanis-qa` le remet a
l'epreuve par mutation sur le paquet reellement servi, dans les deux sens, et il tient. Compare a
la fusion conflictuelle mais sans perte de s44 (trois lots sur les memes fichiers transverses),
s45 ajoute une quatrieme fusion sur les memes fichiers et reste tout aussi propre - verifie par
`yanis-qa`, pas seulement observe au vert du test d'integration du catalogue.

## 2. Ce qui a coute

**Un refus pedagogique reel sur `SCI-CM1-14`, le premier depuis plusieurs sprints, alors que la
story avait deja ete corrigee contre sa source avant l'ecriture.** `camille-po` avait leve Q7 avant
l'ouverture du planning : la story de `S45-SCI-NUMERIQUE` cite desormais `SCI-1` correctement
(l'indice, pas l'explication - l'erreur precise de s44 sur `SCI-CM1-13`), ajoute `SCI-4`, `FER-1` a
`FER-3`, `HIS-3`, `CON-1`. Le paquet ecrit par moi tient chacun de ces criteres, verifie methode de
test par methode de test par `yanis-qa`. Et pourtant `nadia-prof` refuse au premier passage, pour
deux raisons qu'aucun de ces criteres ne couvrait : un fait faux (l'age d'accord parental donne a
l'envers, 13 ans au lieu de 15) et une valeur perimee (un mot de passe de 8 caracteres presente
comme solide, en dessous de la recommandation CNIL de 2022). **Corriger le texte d'une story contre
sa source pedagogique leve les defauts que cette source couvrait ; elle ne couvre pas l'exactitude
factuelle d'un contenu que personne n'a verifiee contre une source exterieure avant l'ecriture.**
C'est une categorie de defaut differente de celle que Q7 fermait, et le processus de correction de
story avant ecriture, qui avait deja corrige deux fois le meme defaut `SCI-1` sur deux sprints
consecutifs, n'avait aucune prise sur elle. Le refus a coute un cycle complet (constat, correction,
reaudit) mais zero perte : les deux corrections ont ete ecrites, testees et revalidees dans la
meme apres-midi, et la seconde fiche de conformite porte neuf reserves au lieu de six, dont une -
`USN-CM1-R9` - que `nadia-prof` ouvre elle-meme contre sa propre correction du premier passage
(une date de redaction de loi mal attribuee dans le marqueur `HIS-3` qu'elle venait de corriger).

## 3. Ce qui reste a risque

**La decouverte retroactive sur `saisie-mot-dicte-cp` montre qu'un defaut peut dormir plusieurs
sprints dans un contenu deja `couvert-valide` sans qu'aucun garde-fou ne le voie.** En instruisant
Q5 (le motif `R6` s'applique-t-il a l'anglais CM1 ?), `nadia-prof` a trouve que **la ligne qui a
donne naissance a `R6`** - *"Ecrire de maniere autonome un mot simple"*, francais CP, `couvert-
valide` depuis s26 - n'a jamais ete verifiee contre ce motif elle-meme : son propre arbitrage
d'acceptation de s26 examine deux autres conditions (`SAI-11`, `SAI-1`) et ne mentionne jamais
`R6`. `R6` l'annoncait depuis l'origine (*"le jour ou une story ouvrira cette ligne en saisie, la
CI sera verte et je serai la seule a dire non"*), et personne n'a dit non pendant quatre sprints,
pas faute de vigilance mais faute d'avoir repose la question. Rien, dans la CI ni dans un garde-
fou, ne peut detecter ce genre d'ecart : seule une relecture volontaire d'un Done deja prononce le
revele, et elle ne s'est produite ici qu'en verifiant une question sans rapport. Le risque que je
retiens n'est pas propre a cette ligne : toute ligne `couvert-valide` dont un motif de refus a
change de portee apres sa fermeture peut porter le meme silence, et rien ne le signale sans un
geste delibere.

## 4. Actions pour la suite

**Je route, je ne tranche pas, conformement a mon role.**

- Le refus de `SCI-CM1-14` ne pointe aucune lacune du processus de correction de story avant
  ecriture : ce processus ferme exactement ce qu'il est cense fermer (un critere mal cite ou
  incomplet contre sa source pedagogique), et il l'a fait deux fois de suite sur le meme defaut.
  Il ne pretend pas, et n'a jamais pretendu, verifier l'exactitude factuelle d'un contenu contre
  une source exterieure au programme. Je ne propose aucun amendement a `squad/README.md` sur ce
  point : nommer la limite d'un garde-fou existant n'est pas la meme chose que constater qu'il a
  failli.
- La decouverte retroactive sur `saisie-mot-dicte-cp` est deja routee - `S46-VERIF-SAISIE-MOT-
  DICTE-CP`, priorite haute, `docs/backlog/backlog.md`. Je ne lui fixe pas de sprint : c'est a
  `camille-po` de l'engager.
- Je nomme, pour que la prochaine instance qui composerait un dispositif a deux instances le
  sache : verifier `PAR-2` trois fois avant d'affecter un developpeur, comme ce sprint l'a fait,
  n'a rien coute en temps et a rendu la verification du planning plus courte, pas plus longue -
  ce n'est pas une surcharge de process a alleger.

**Je ne propose pas d'amendement a `squad/README.md`.** Les points ci-dessus sont des constats
sur ce sprint precis (un refus pedagogique reel sur une categorie de defaut hors de portee du
processus de correction de story, une decouverte retroactive deja routee), pas des lacunes de la
ceremonie de cloture elle-meme.

## Decisions prises

**Aucune decision de sprint neuve n'est prise dans cette retro**, au sens `DEC`/`ADR`. Elle
constate ce qui a fonctionne et ce qui a coute dans l'orchestration de s45 et ne modifie pas
`squad/README.md`.
