# [Sprint s00] Avis de faisabilite - theo-archi

- **Date** : 2026-08-04
- **Auteur** : theo-archi
- **Backlog lu** : `docs/backlog/backlog.md`, section "Sprint 0", stories S0-1 a S0-5
- **Decision produit de reference** : `DEC-0001-perimetre-sprint-0.md`

## Synthese

| Story | Avis | ADR requis | Point a trancher hors architecture |
|---|---|---|---|
| S0-1 | Faisable moyennant ADR, et un critere a reformuler | ADR-0001, ADR-0002, ADR-0003 | oui - mention de H2 |
| S0-2 | Faisable en l'etat | ADR-0004, ADR-0008 | non |
| S0-3 | Faisable en l'etat, c'est ma story | ADR-0003, ADR-0004 | non |
| S0-4 | Faisable en l'etat | ADR-0006, ADR-0007 | non |
| S0-5 | Faisable techniquement ; le fond est pedagogique | ADR-0007 | oui - emoji et vocabulaire |

Les huit ADR necessaires au sprint sont ecrits et acceptes (`squad/decisions/adr/ADR-index.md`).
Aucune story ne demande de revoir le decoupage.

## S0-1 - Un socle applicatif qui se verifie tout seul

**Avis : faisable moyennant ADR-0001, ADR-0002 et ADR-0003, avec un critere a reformuler.**

Ce que le socle livre : cinq modules Maven (`domain`, `application`, `adapters/adapter-web`,
`adapters/adapter-content`, `bootstrap`), Spring Boot 4.1.0, Java 21, ArchUnit 1.5.0, fat jar
`bootstrap/target/recreo.jar` via `<finalName>recreo</finalName>`. Le garde-fou de prefixe de
commit existe deja dans `.github/workflows/ci.yml` : je l'ai relu, il compare bien la plage de
commits et sort en erreur, ce n'est pas une annonce sans mise en oeuvre cette fois.

**Point a trancher, route a Sofia** : le troisieme critere d'acceptation demande que `mvn verify`
couvre des "tests d'integration H2". Or ADR-0001 ne cree pas `adapter-persistence` : aucune story
du sprint ne demande de conserver quoi que ce soit entre deux parties, et un module sans usage est
un module sans test - c'est la dette n°5 de `main`. Deux issues possibles, la premiere est celle
que je recommande :

1. reformuler le critere en "tests d'integration Spring Boot (contexte demarre, parcours HTTP
   verifie)", ce que le sprint livre reellement ;
2. maintenir H2, ce qui oblige a introduire une persistence sans besoin. Je m'y oppose sur le plan
   technique, mais le perimetre appartient a Camille.

La formulation "H2" vient de la section Stack de `CLAUDE.md`, qui decrit la cible ; le meme fichier
renvoie le decoupage exact des modules a l'ADR. Il n'y a donc pas de contradiction, juste un
critere ecrit sur la cible plutot que sur l'increment.

**Rappel de porte technique** : le Done technique de cette story, c'est `mvn verify` vert, et rien
d'autre. La suite ArchUnit du sprint compte huit classes de test, dont une (`LesReglesMordentTest`)
qui verifie que les autres echouent bien sur du code fautif.

## S0-2 - Jouer une manche du bandit manchot du debut a la fin

**Avis : faisable en l'etat.**

Trois precisions d'architecture, qui ne changent rien aux criteres :

- **L'etat de partie vit dans la session HTTP du serveur**, pas en base et pas dans le navigateur.
  Le score cumule reste donc visible d'une manche a l'autre (quatrieme critere) sans introduire de
  persistence. C'est coherent avec ADR-0008 : le serveur detient l'etat et le verdict.
- **Le retour "incorrect" qui designe la bonne reponse** n'arrive dans le HTML **qu'apres** le coup
  de l'enfant. Le fragment envoye avant la reponse ne porte aucun marqueur de correction ;
  `FrontieresDuJavaScriptTest` fait echouer le build si un gabarit contient `data-correct`,
  `data-bonne`, `data-solution` ou `data-reponse`. Un enfant qui inspecte la page ne doit pas
  pouvoir gagner.
- **Le comportement (score, enchainement, fin de manche) est ecrit en Java** dans
  `com.recreo.domain.mechanics.banditmanchot`, pas dans le JavaScript. Le budget de 4 096 octets
  d'ADR-0008 rend physiquement impossible d'y loger une machine a etats, et
  `ToutesLesMecaniquesSontTesteesTest` exige un test de comportement pour cette mecanique.

Attention a un piege venu de `main` : la mecanique ne doit pas etre une coquille qui delegue a un
moteur partage. ADR-0004 ferme le paquet `domain.mechanic` aux classes concretes, donc il n'y a pas
d'endroit ou hisser un `SessionEngine`. Le comportement va dans le paquet de la mecanique, meme si
cela parait "trop peu factorise" avec une seule mecanique. C'est exactement le bon moment pour ne
pas factoriser.

## S0-3 - Rendre impossible de meler mecanique et contenu

**Avis : faisable en l'etat. Livree par ADR-0004, adossee a ADR-0003.**

Correspondance critere par critere :

- *"la regle echoue si la mecanique reference une classe propre a ce contenu"* : c'est la regle
  `uneMecaniqueNeVoitQueLesContrats` d'ADR-0004, une **liste blanche** (JDK + les deux contrats).
  Le generateur d'additions vit dans `com.recreo.domain.content.source` : il est hors liste
  blanche, ou qu'on le range. La version precedente interdisait un nom de sous-paquet
  (`content.impl`) et se contournait donc en renommant un repertoire.
- *"... ou une valeur propre a ce contenu (par exemple la borne 10, le mot addition)"* : ArchUnit
  lit le bytecode, il voit les types et pas les litteraux. Le complement est
  `AucunVocabulaireDeMatiereTest`, un test de lecture des sources de `domain/mechanics` qui refuse
  un vocabulaire de matiere (addition, syllabe, fraction...). Quant a la borne numerique, elle est
  deja sans objet : la mecanique ne manipule que du texte (`Question` ne porte que des `String`),
  une constante `10` n'y aurait rien a comparer.
- *"quand le contenu est injecte via une abstraction generique, la regle passe"* : c'est la suite
  ArchUnit verte sur le code livre.

**Ce que j'ajoute au-dela du critere, et pourquoi.** La story ne demande qu'un sens
(mecanique vers contenu). J'encode aussi le sens inverse (`leContenuIgnoreLesMecaniques`) et
l'ignorance de la matiere (`uneMecaniqueIgnoreLaMatiere`). Motif : sur `main`, la mecanique n'a
jamais reference un generateur, la regle est restee verte 25 sprints, et le couplage s'est
installe par les deux autres chemins. Ce n'est pas de la sur-ingenierie, c'est la fermeture des
chemins reellement empruntes.

## S0-4 - S'entrainer aux petites additions en jouant

**Avis : faisable en l'etat.**

Le contenu est **combinatoire** (le troisieme critere demande de la variete sur 20 manches), donc
pas une liste YAML figee. Il est livre par `AdditionsBorneesContentSource` dans
`com.recreo.domain.content.source`, avec ses bornes declarees sous `parametres` dans son paquet
YAML (`sommeMaximale: 10`, `nombreDePropositions: 3`), lues par sa seule fabrique (ADR-0006,
ADR-0007).

Deux consequences utiles pour Lea :

- **Le tirage passe par `RandomSource`, injectable.** Un test avec une graine fixe verifie les
  quatre criteres de facon deterministe : somme toujours inferieure ou egale a 10, bonne reponse
  presente exactement une fois, au moins un distracteur plausible et jamais negatif ni non entier,
  variete sur 20 tirages. Sans injection du hasard, ces criteres ne sont pas testables ; avec, ils
  le sont tous.
- **Aucun `DifficultyConfig` a remplir.** Ajouter "les additions jusqu'a 20" au sprint suivant sera
  un fichier YAML et une ligne de catalogue, zero ligne de Java. C'est la mesure concrete
  d'ADR-0006 : sur `main`, chaque contenu de francais devait declarer des operandes.

La consigne affichee ("combien ca fait ?") est portee par l'entree de catalogue, pas par la
mecanique et pas par le code du generateur. Sa **redaction** appartient a Camille et sa validation
a Nadia ; je ne me prononce que sur son emplacement.

## S0-5 - Un retour comprehensible pour un enfant qui lit encore peu

**Avis : faisable techniquement. Le fond de la story est pedagogique et ne m'appartient pas.**

Ma part :

- La consigne courte est une donnee du catalogue, modifiable sans recompiler la mecanique. La
  contrainte "moins de dix mots, vocabulaire de CP" se verifie sur cette donnee ; c'est Nadia qui
  juge, pas la CI.
- Le retour juste/faux distinguable sans lecture fine est du rendu serveur : classe CSS et couleur
  sur le fragment renvoye, pas de logique cote client.

**Point a trancher, route a Sofia, avec une composante produit.** Le critere parle d'un "icone".
`CLAUDE.md` interdit les emoji et les symboles Unicode decoratifs dans tout le depot, code et
documentation compris - un gabarit Thymeleaf est un fichier du depot. Techniquement, la reponse
propre est un SVG ou une forme CSS, pas un caractere emoji dans le gabarit ; le rendu pour l'enfant
est identique, voire meilleur (taille et contraste maitrises). Il faut trancher explicitement,
parce que la question reviendra : les paquets de `contenu-repris/` utilisent massivement des
emoji dans leurs enonces (par exemple `contenu-repris/associer/cp-formes.yml`). Savoir si un emoji
est du contenu pedagogique legitime ou un symbole decoratif interdit est une decision produit, pas
une decision d'architecture. Je recommande une `DEC` de Camille, relue par Nadia, avant que le
sujet arrive avec la premiere mecanique d'association.

## Risques que je signale des maintenant

1. **La tentation de factoriser trop tot.** Avec une seule mecanique, il n'y a rien a factoriser.
   Si un developpeur bute sur "ou mettre ce code commun", la reponse au sprint 0 est : dans la
   mecanique. La factorisation se decidera a l'arrivee de la deuxieme, par ADR, et elle ne rouvrira
   pas le contrat aux classes concretes.
2. **Le schema de paquet a deux natures des le sprint 0** (enumeree et combinatoire). C'est
   assume, et c'est aussi la pente qui a produit neuf familles de chargeurs sur `main` : la
   difference tient a ce que le chargeur reste unique et que seule la fabrique change.
   `ilNYAQuUnSeulChargeurDeContenu` casse le build au deuxieme chargeur.
3. **Les contre-exemples d'ADR-0003 sont du code de test dans des paquets de production.** C'est
   deroutant a la premiere lecture ; ils sont tous suffixes `ContreExemple` et une regle interdit
   ce suffixe dans `src/main`. A expliquer une fois a Marc et Lea plutot que de les laisser le
   decouvrir dans un diff.

## Verification ArchUnit

Le depot ne contient encore aucun code Java : `mvn -pl bootstrap test` n'a rien a executer a la
date de cet avis. Le statut ArchUnit sera confirme, commande et resultat a l'appui, avant la
review, conformement a `squad/README.md` section 9 etape 4. Les regles ecrites dans les ADR ont ete
verifiees API par API contre `archunit-1.5.0.jar` (existence et signature de
`beRecords`, `beEnums`, `beInterfaces`, `haveFullyQualifiedName`, `implement(String)`,
`allowEmptyShould`, `resideOutsideOfPackages`, `haveRawType(DescribedPredicate)`,
`doNotHaveModifier`, `OnlyIncludeTests`), ainsi que deux comportements dont dependent les listes
blanches : les primitifs sont exclus des dependances, et un type tableau porte le paquet de son
type composant. Elles doivent compiler telles quelles.
