# ADR-0063 - Un item ne presente jamais plus de trois propositions, et les deux autres regles de forme restent des conseils

- **Statut** : Accepte
- **Date** : 2026-08-27
- **Auteur** : theo-archi
- **Sprint** : s21, story `S21-5`
- **Regle ecrite dans le meme commit** : `adapters/adapter-content/src/test/java/com/recreo/adapter/content/AuPlusTroisPropositionsParItemTest.java`, sept tests dont quatre morsures, plus une morsure sur materiau reel prouvee par mutation d'un paquet servi.

## Contexte

`ines-design` propose, le 2026-08-27, un **contrat de forme** en quatre regles a imposer aux paquets
neufs, apres avoir mesure que 56 items du catalogue laissent l'indice sous le pli apres un echec
(`REP-8`). Les quatre regles : au plus trois propositions par item ; un libelle de proposition de
dix caracteres au plus ; un enonce qui tient sur une seule ligne a 744 px ; une porte de rendu avant
fusion.

**Le probleme reel n'est pas "faut-il un contrat", c'est "laquelle de ces quatre regles peut devenir
un test sans rendre rouge du contenu que la porte pedagogique a valide".** C'est la question que
`ADR-0028` a deja tranchee une fois, dans l'autre sens, et dont le motif de refus etait exactement
celui-la : *"il serait de plus rouge sur du contenu que `nadia-prof` a valide"*.

### La mesure de la dette, faite avant d'ecrire quoi que ce soit

**Methode, perimetre et date** : les 18 fichiers de
`adapters/adapter-content/src/main/resources/contenu/` lus le 2026-08-27, sur `9e2e31a`. Pour chaque
item, le nombre de propositions est la taille de l'ensemble `{bonneReponse} union {distracteurs}`.
Le paquet combinatoire `cp-additions-jusqu-a-10.yaml` n'a pas d'items a lire et est declare tel.

| Regle proposee | Items ecartes sur les 428 servis | Ce que cela represente |
|---|---|---|
| **1 - au plus 3 propositions** | **33**, sur **2 paquets** (`cm1-conjugaison-present` 25, `cm1-accords-groupe-nominal` 8) | une dette chiffrable au paquet pres |
| 2 - libelle de 10 caracteres au plus | **35**, sur **6 paquets sur 18**, dont `cm1-reperes` **16 sur 16** | deux competences de referentiel supprimees |
| 3 - enonce sur une seule ligne (47 caracteres a 744 px) | **80**, dont `cp-problemes-additifs` **22 sur 22** | la resolution de problemes en CP, en entier |
| 4 - porte de rendu avant fusion | sans objet - porte de revue, pas de critere de contenu | - |

Les chiffres des regles 2 et 3 sont ceux de `nadia-prof`, mesures en pixels de police reellement
rendue le 2026-08-27 (`budget-de-forme.py`) ; ceux de la regle 1 sont les miens, par une lecture
independante du meme repertoire le meme jour. **Les deux methodes rendent le meme total de 428 items
et, pour la regle 1, la meme repartition item pour item.** C'est cette concordance qui me fait tenir
la table sans reserve.

### Ce que la porte pedagogique a tranche, et que cet ADR n'a pas a rouvrir

`nadia-prof`, le 2026-08-27 (commit `749244a`, section 6 de son arbitrage) : elle **autorise** la
regle 1 et la regle 4, et **refuse comme veto** les regles 2 et 3. Son verdict sur la regle 2, au
mot : *"un contrat de 10 caracteres ne raccourcit pas ces libelles : il supprime les competences."*
`Point d'interrogation` mesure 317,69 px, 1,8 fois la colonne, **et c'est le mot du referentiel**.

**J'allais ecrire la regle 2 avec un cliquet de six paquets, et j'avais tort.** Mon objection
d'architecte etait de la bonne famille mais de la mauvaise force - un critere en caracteres pour une
propriete de pixels, ce que `DEC-0016` decision 2 interdit deja nommement - et j'en concluais
"encodable avec des precautions". Sa mesure montre qu'aucune precaution ne repare le fond : un
cliquet dont l'exemption doit contenir un paquet **entier** deja valide (`cm1-reperes`, 16 sur 16)
n'exempte pas une dette, il exempte la competence elle-meme.

## Decision

### 1. Un item ne presente jamais plus de trois propositions

La proposition compte la bonne reponse et ses distracteurs. La regle porte sur ce que **le chargeur
reel** rend, pas sur ce que le YAML ecrit : un type de contenu dont la fabrique ajouterait une
proposition en route est juge comme les autres.

**Ce qu'elle interdit reellement** : qu'un paquet neuf entre au catalogue avec quatre propositions,
donc qu'il tombe dans le repli a quatre colonnes qui, a lui seul, a pousse le bas du cabinet a
492 px sur 551 sur `cm1-reperes` (`FICHE-bandit-manchot-reperes-cm1.md`, correction du 2026-08-26).
Le cas rouge est reel et deja constate.

**Ce qu'elle n'interdit pas, et je l'ecris ici plutot que de laisser croire l'inverse** : qu'un item
passe sous le pli pour une autre cause. Les pires items du catalogue la respectent deja -
`cm1-grands-nombres`, le plus enfonce du releve de `REP-8`, n'a jamais plus de trois propositions et
doit ses trois rangees a la longueur de ses libelles. **Cette regle ne ferme pas `REP-8` et ne
pretend pas le faire.**

### 2. Le cliquet vaut exactement deux paquets et 33 items, et il mord dans les deux sens

`cm1-conjugaison-present` a 25 items concernes, `cm1-accords-groupe-nominal` en a 8. Le plafond vaut
**exactement** ces nombres, jamais un plancher : ma propre regle est qu'un cliquet dont le plafond
depasse la dette n'est pas un cliquet. Quatre clauses en decoulent :

1. un item a plus de trois propositions **hors** des deux paquets exemptes est refuse et nomme ;
2. un paquet exempte dont le compte **monte** est refuse : la dette ne grandit pas ;
3. un paquet exempte dont le compte **descend** est refuse aussi, et le message donne la valeur a
   inscrire - sans quoi l'exemption vaudrait plus que ce qu'elle exempte et laisserait rentrer un
   item fautif sans que rien ne le dise ;
4. un paquet exempte qui **n'existe plus** sous `contenu/` est refuse : une exemption qui ne nomme
   plus rien de reel autorise un nom, et ce nom peut revenir porte par un contenu neuf.

**Ces 33 items ne sont pas un defaut pedagogique constate.** `nadia-prof` n'a prononce aucun refus
dessus, et leur correction eventuelle est une question de contenu qui ne m'appartient pas. Le
cliquet dit seulement qu'ils existent, combien ils sont, et qu'ils ne se multiplieront pas en
silence.

### 3. Les regles 2 et 3 ne sont pas encodees, et ce refus est la partie la plus importante de cet ADR

- **Regle 2 (libelle de 10 caracteres au plus)** : *encodable, et il ne faut pas l'encoder.*
  Techniquement, un garde-fou sur la longueur d'une chaine s'ecrit en dix lignes. Il serait rouge
  sur 35 items que la porte pedagogique a valides, dont un paquet entier, et il supprimerait deux
  competences des referentiels. C'est le motif de refus d'`ADR-0028`, mot pour mot.
- **Regle 3 (enonce sur une seule ligne a 744 px)** : *non encodable.* C'est la meme propriete que le
  pli, un cran plus haut dans la pile : une largeur de texte rendu. Les trois motifs d'`ADR-0028`
  s'appliquent tels quels. La tentation a nommer pour qu'elle soit refusee explicitement est de
  l'encoder par un nombre maximal de caracteres d'enonce : ce serait vert sur un enonce court en
  glyphes larges qui passe sur deux lignes, et rouge sur un enonce long en glyphes etroits qui tient
  sur une. Un garde-fou dont le verdict n'est pas celui de la propriete qu'il nomme apprend a
  l'equipe a le desactiver.
- **Regle 4 (porte de rendu avant fusion)** : *une etape de revue, jamais un test.* Elle parle du
  **resultat** ("aucun item ne laisse l'indice ni le verdict sous le pli a 744 x 551") au lieu de
  prescrire un moyen, donc elle ne peut pas fermer une competence - c'est sa qualite. Elle n'entre
  pas en CI pour la raison d'`ADR-0028` : elle demande un navigateur et des polices que `mvn verify`
  ne peut pas garantir, et un test qui se met en veille quand le binaire manque est un vert qui ne
  prouve rien.

**Les regles 2 et 3 restent des conseils de premier recours**, ce que `nadia-prof` autorise
explicitement, ecrits dans `docs/backlog/backlog.md` et dans
`FICHE-bandit-manchot-reperes-cm1.md`, **jamais en critere bloquant**.

## Regle executable

**Ce n'est pas une regle ArchUnit, et le compte de regles ArchUnit du depot est inchange.** ArchUnit
lit du bytecode ; le nombre de propositions d'un item est une propriete de la donnee servie, lue par
le chargeur reel. Le mecanisme automatique qui garde cette decision est un test JUnit du module
`adapter-content`, execute par `mvn verify` comme n'importe quel autre, dans la meme famille que
`AucunePropositionNEstEgaleAUneAutreDansUnItemTest` et `ChaquePaquetPorteUnIndiceTest`.

**Fichier** :
`adapters/adapter-content/src/test/java/com/recreo/adapter/content/AuPlusTroisPropositionsParItemTest.java`

```java
private static final Map<String, Integer> DETTE_AU_2026_08_27 = Map.of(
        "cm1-conjugaison-present", 25,
        "cm1-accords-groupe-nominal", 8);

private static int nombreDePropositions(Question item) {
    return 1 + item.distracteurs().size();
}
```

**Les exemptions sont un parametre de la fonction de verdict, pas une constante lue en son sein.**
C'est ce qui rend chacune des quatre clauses eprouvable separement, sur du **contenu reel**, sans
avoir a fabriquer un faux catalogue par clause - et c'est aussi ce qui permet au test
`sansAucuneExemptionLeGardeFouNommeLesDeuxPaquetsDeLaDetteEtLeursComptes` d'exiger que le garde-fou
**nomme lui-meme** les 25 et les 8. La dette est donc un chiffre relu sur le contenu servi a chaque
build, jamais une valeur recopiee dans une table que plus personne ne verifie.

### ADR-0013, les trois obligations

- **Perimetre derive** : les paquets viennent du balayage de `contenu/`, jamais d'une liste ecrite
  dans le test - un paquet retire du catalogue mais laisse sur disque reste vu, le motif nomme par
  `ChaquePaquetPorteUnIndiceTest`. Les items viennent du chargeur reel.
- **Derivation totale** : chaque item tire est compte, sans exception. Le test refuse aussi un
  repertoire qui ne rend aucun paquet et un paquet qui ne rend aucun item : un garde-fou qui
  n'examine rien serait vert et ne prouverait rien.
- **Mise a l'epreuve** : un paquet fautif neuf, jamais livre, sous
  `src/test/resources/contreexemple/AuPlusTroisPropositionsParItemTest/`, dont le second item porte
  quatre propositions ; et sa version corrigee, un distracteur de moins et rien d'autre, qui doit
  redevenir verte.

### Les sept tests, et ce que chacun met a l'epreuve

| Test | Ce qu'il prouve |
|---|---|
| `aucunItemDeProductionNePresentePlusDeTroisPropositionsHorsLaDetteExacte` | le verdict sur le contenu servi |
| `sansAucuneExemptionLeGardeFouNommeLesDeuxPaquetsDeLaDetteEtLeursComptes` | la dette existe vraiment et vaut 25 et 8, relus a chaque build |
| `unPaquetNeufAQuatrePropositionsFaitEchouerLeGardeFou` | clause 1, sur un objet neuf |
| `leMemePaquetRameneATroisPropositionsPasseLeGardeFou` | le garde-fou ne refuse pas ce qui est correct |
| `unPaquetExempteDontLeCompteMonteFaitEchouerLeGardeFou` | clause 2 |
| `unPaquetExempteDontLeCompteDescendFaitEchouerLeGardeFouEtDonneLaValeurAInscrire` | clause 3 |
| `uneExemptionQuiNeNommePlusUnPaquetReelFaitEchouerLeGardeFou` | clause 4 |

### Morsure prouvee sur materiau reel, et pas seulement sur ma fixture

Une fixture ecrite par l'auteur d'une regle ressemble a ce qu'il imagine. **Mutation d'un paquet
servi**, `cm1-synonymes.yaml`, un distracteur ajoute a son premier item, le 2026-08-27, arbre
restaure et verifie propre ensuite :

```
paquet cm1-synonymes : 1 item(s) presentent plus de 3 propositions, alors que ce paquet n'est
pas dans la dette du 2026-08-27. Un item a quatre propositions tombe dans le repli a quatre
colonnes (ADR-0063). Detail : item item-0 ("Lea est "contente" de son cadeau.") : 4 propositions
```

`Tests run: 7, Failures: 1` sur `-pl adapters/adapter-content -am test`, le 2026-08-27. Le paquet et
l'item sont nommes, pas seulement comptes.

### Ce que ce garde-fou ne couvre pas

- **Il ne voit pas un paquet qui n'est pas sous `contenu/`.** Un contenu servi depuis un autre
  emplacement lui echappe ; aucun n'existe aujourd'hui, et `ProvenanceDesClassesAmontEstLeDepotTest`
  garde une propriete voisine sur les classes.
- **Il ne voit d'un paquet combinatoire que ce que 60 tirages en rendent.** La borne est celle de
  `AucunePropositionNEstEgaleAUneAutreDansUnItemTest`, pour la meme raison : un paquet enumere
  s'epuise bien avant, un paquet combinatoire ne s'epuise jamais.
- **Il ne dit rien de la largeur rendue.** Trois propositions a libelles longs se replient sur trois
  rangees, et c'est le cas des pires items du catalogue.

## Consequences

- **Un paquet neuf a quatre propositions est refuse par la CI**, avec le nom du paquet et de l'item.
  C'est le seul des quatre points du contrat de forme qui devient opposable sans arbitrage.
- **Deux paquets servis portent une exemption nommee et chiffree.** Elle n'est pas un pardon : elle
  est une dette dont la taille est verifiee a chaque build, dans les deux sens.
- **Un lot qui corrigerait `cm1-conjugaison-present` rendra ce test rouge**, avec le message qui dit
  exactement quelle valeur inscrire. C'est voulu, et c'est le prix de l'egalite : un plancher aurait
  laisse passer la correction sans rien dire, et le plafond aurait cesse de valoir la dette.
- **Deux regles proposees ne deviennent pas des tests, et le depot porte le motif chiffre.** Le cout
  est qu'un paquet neuf peut entrer avec un libelle de 40 caracteres ; le benefice est qu'aucune
  competence de referentiel n'est fermee par un garde-fou.
- **La regle 4 reste une promesse tenue par une revue humaine.** C'est la septieme dette de
  `CLAUDE.md` si personne ne l'ecrit dans les criteres d'acceptation : elle doit y etre nommee comme
  **etape de revue**, jamais comme quelque chose que la CI tient.
- **Le compte de regles ArchUnit du depot est inchange** : cet ADR n'en revendique aucune.

## Ce qui me ferait changer d'avis

- **Sur le plafond de trois** : un arbitrage de `nadia-prof` etablissant qu'une competence exige
  quatre propositions. Le plafond serait alors une valeur portee par le paquet et non une constante,
  et la regle deviendrait "un item ne presente jamais plus de propositions que son paquet n'en
  declare" - meme forme qu'`ADR-0012` pour la longueur de partie.
- **Sur la regle 2** : rien de ce que je peux mesurer. Seule une remesure de `nadia-prof` etablissant
  que les libelles longs ne portent plus de competence rouvrirait la question, et ce n'est pas un
  geste d'architecte.
- **Sur la regle 3 et sur la regle 4** : le jour ou le depot porte un conteneur de build avec ses
  polices epinglees - le declencheur qu'`ADR-0028` a lui-meme ecrit, verifie non survenu le
  2026-08-27 (aucun `Dockerfile`, aucun `devcontainer` a la racine ni au premier niveau). Ce jour-la,
  une etape de CI qui joue le catalogue au navigateur devient defendable, et il faudra encore
  epingler la population des tirages avant d'en faire un cliquet.

## Alternatives considerees

- **Ecrire les quatre regles.** Ecartee : deux d'entre elles seraient rouges sur du contenu valide,
  et une n'est pas encodable. Un contrat de forme ecrit sans passer par le referentiel n'est pas un
  garde-fou, c'est une amputation qui a l'air d'un garde-fou.
- **N'ecrire aucune regle et faire des quatre points une porte de revue.** Ecartee : la regle 1 a une
  dette de deux paquets et un cas rouge reel deja constate ; la laisser a une revue humaine, c'est la
  septieme dette de `CLAUDE.md` - une promesse que la documentation affirme et qu'aucun automate ne
  tient.
- **Un cliquet en plancher** ("pas plus que N"). Ecartee pour le motif que j'ai moi-meme etabli :
  un plafond qui depasse sa dette n'est pas un cliquet. Ici, la correction des 33 items est un
  evenement attendu, pas un accident : le message du refus donne la valeur a inscrire, ce qui coute
  une ligne a celui qui corrige et lui evite de deviner.
- **Exempter par item plutot que par paquet.** Ecartee : 33 identifiants d'items dans une constante
  seraient une liste enumeree que le depot refuse depuis `ADR-0039`, et qui deviendrait fausse au
  premier item renumerote. Le compte par paquet est derive, verifie et suffisant.
