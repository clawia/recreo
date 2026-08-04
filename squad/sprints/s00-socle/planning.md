# [Sprint s00] Planning - 2026-08-04

- **Horodatage de debut** : 2026-08-04 15:39
- **Anime par** : sofia-sm
- **Participants** : camille-po (stories du backlog), theo-archi (avis de faisabilite), sofia-sm

## Objectif du sprint

Livrer, dans le meme increment, un socle technique verifiable (CI verte, Clean Architecture a
cinq modules, garde-fou de commit) et une premiere mecanique de jeu generique, le bandit manchot,
jouable de bout en bout avec un contenu pedagogique reel - les additions dans la limite de 10
(mathematiques, CP).

## Stories retenues

| Story | Titre | Competence programme visee | Decision liee | Affectee a |
|---|---|---|---|---|
| S0-1 | Un socle applicatif qui se verifie tout seul | transverse | DEC-0003 | theo-archi (squelette Maven, garde-fou CI), puis marc-dev (cablage bootstrap et adapter-web qui rendent le socle verifiable de bout en bout) |
| S0-2 | Jouer une manche du bandit manchot du debut a la fin | transverse (la mecanique ne connait aucune matiere) | - | marc-dev |
| S0-3 | Rendre impossible de meler mecanique et contenu | transverse | - | theo-archi |
| S0-4 | S'entrainer aux petites additions en jouant | mathematiques, CP (cycle 2), "Calcul mental - additions dans la limite de 10" | DEC-0001 | lea-dev |
| S0-5 | Un retour comprehensible pour un enfant qui lit encore peu | transverse (accessibilite CP) | DEC-0002 | marc-dev (rendu du signal visuel juste/faux, generique) puis lea-dev (redaction de la consigne courte dans le paquet YAML des additions) |

**Affectation de Sofia (casquette dev) ce sprint** : aucune tache de code. Sous cette casquette,
je tiens les ceremonies et le routage des obstacles ; je ne me sur-affecte pas une tache de
developpement en plus.

**Sequencement, impose par le partage du meme arbre de travail** : le code n'est pas ecrit en
parallele. Un `mvn verify` sur un module a moitie ecrit ne prouve rien pour personne. L'ordre
retenu est strictement serie :

1. `theo-archi` monte le squelette Maven a cinq modules et les huit classes ArchUnit avec leurs
   contre-exemples (S0-1 partie socle, S0-3).
2. Puis `marc-dev` livre la mecanique du bandit manchot, l'adapter-web et le cablage bootstrap
   (S0-2, achevement de S0-1, moitie generique de S0-5), sur les contrats de domaine et
   d'application deja livres par `theo-archi` avec le squelette (voir correction du 2026-08-04
   en fin de fichier).
3. Puis `lea-dev` livre `adapter-content` (chargeur YAML unique), `AdditionsBorneesContentSource`,
   l'entree de catalogue et le paquet YAML des additions CP (S0-4, moitie contenu de S0-5).

Chaque etape ne demarre qu'une fois la precedente committee et compilable.

## Avis de faisabilite - theo-archi

| Story | Avis | ADR requis |
|---|---|---|
| S0-1 | Faisable moyennant ADR, et un critere a reformuler | ADR-0001, ADR-0002, ADR-0003 |
| S0-2 | Faisable en l'etat | ADR-0004, ADR-0008 |
| S0-3 | Faisable en l'etat, c'est la story de theo-archi | ADR-0003, ADR-0004 |
| S0-4 | Faisable en l'etat | ADR-0006, ADR-0007 |
| S0-5 | Faisable techniquement ; le fond est pedagogique | ADR-0007 |

Repris integralement de `squad/sprints/s00-socle/avis-faisabilite.md`. Les huit ADR necessaires
au sprint sont ecrits et acceptes (`squad/decisions/adr/ADR-index.md`). Le critere de S0-1 cite
dans l'avis a deja ete reformule par `DEC-0003` ; le point d'"icone" de S0-5 a deja ete tranche
par `DEC-0002`. Aucun des deux ne reste ouvert a ce planning.

## Decisions prises

**1. Obstacle de capacite / technique, deja resolu - trace ici pour que chaque developpeur en
tienne compte.** La machine expose `java` 1.8.0_202 sur le `PATH`, et Maven 3.9.9 tourne par
defaut sur ce JRE 8. `ADR-0001` cible Java 21 et Spring Boot 4.1.0 : rien ne compilerait avec le
Java du `PATH`. J'ai verifie l'environnement pendant ce planning : un JDK 21 est installe a
`<JDK 21>` (`java version "21.0.10" 2026-01-20 LTS`), et
`spring-boot-starter-parent` 4.1.0 existe sur Maven Central - c'est meme la derniere version
publiee, ce qui confirme l'avis de faisabilite de `theo-archi` sur ce point. Un
`~/.m2/toochains.xml` declare bien un JDK 21, mais son nom de fichier est mal orthographie
(`toochains` au lieu de `toolchains`) : Maven ne le lit pas, il ne faut pas compter sur les
toolchains ce sprint.

Consequence operationnelle, valable pour toute commande Maven du sprint (theo-archi, marc-dev,
lea-dev, yanis-qa, moi) : positionner explicitement `JAVA_HOME` sur
`<JDK 21>` avant chaque invocation de `mvn`, jamais se fier au `java` du
`PATH` ni au toolchains cassees. Ceci ne cree ni ne modifie aucun `ADR` : `ADR-0001` reste tel
qu'ecrit, c'est une verification d'environnement qui confirme sa faisabilite, pas une decision
d'architecture nouvelle.

**2. Obstacle de redaction, deja identifie, route au `relecteur-pedagogique` - non tranche ici.**
`squad/sprints/s00-socle/ETAT-REPRISE.md` documente que `CLAUDE.md` decrit une stack a cinq
modules incluant `adapters/adapter-persistence` et fait figurer "integration H2" dans la
definition du Done technique, alors que `ADR-0001` ne cree pas ce module (il lui substitue
`adapter-content`) et que `DEC-0003` acte qu'aucune durabilite n'est necessaire au sprint 0.
L'ecart est deja trace par `DEC-0003` elle-meme, volontairement, comme un defaut de formulation
de `CLAUDE.md` a corriger plus tard, pas une contradiction de fond. Je ne le tranche pas
maintenant : il est route au `relecteur-pedagogique`, qui le traitera a l'etape de relecture des
livrables non executables (etape 5 de `squad/README.md` section 9), pas au planning.

## Prochaines etapes

1. **theo-archi** : monte le squelette Maven a cinq modules (`domain`, `application`,
   `adapters/adapter-web`, `adapters/adapter-content`, `bootstrap`) selon `ADR-0001`, y compris
   les contrats de domaine et d'application, pour que son etape compile seule ; ecrit les huit
   classes ArchUnit avec leurs contre-exemples exiges par `ADR-0003` (couvrant `ADR-0002`,
   `ADR-0004` a `ADR-0008`), livre S0-1 (partie socle) et S0-3. Toute commande Maven avec
   `JAVA_HOME` sur `<JDK 21>`.
2. **marc-dev**, une fois le squelette de theo-archi committe et compilable : mecanique du
   bandit manchot (`com.recreo.domain.mechanics.banditmanchot`), adapter-web (templates
   htmx/Thymeleaf, fragment du retour juste/faux sans marqueur de correction avant le coup de
   l'enfant, rendu generique du signal visuel de S0-5), cablage bootstrap (fat jar `recreo.jar`,
   page accessible en HTTP). Livre S0-2, acheve S0-1.
3. **lea-dev**, une fois le commit de marc-dev committe et compilable : `adapter-content`
   (chargeur YAML unique), `AdditionsBorneesContentSource`, entree de catalogue, paquet YAML des
   additions CP avec la consigne courte de S0-5 ("combien ca fait ?"). Livre S0-4, acheve S0-5.
4. **sofia-sm** : tient la synchronisation de travail entre chaque etape (pas de fichier de
   daily), collecte l'avancement, route tout nouvel obstacle vers l'agent proprietaire, prepare la
   review une fois les trois etapes de code terminees.
5. **yanis-qa**, une fois le code livre : traduit les criteres d'acceptation en tests, lance
   `mvn verify`, produit le rapport qualite.
6. **theo-archi** : confirme le resultat ArchUnit avec la commande et son resultat, pour la
   review.
7. **relecteur-pedagogique** : passe sur les livrables non executables (planning, ADR, DEC,
   backlog, fiche de conformite), et traite l'ecart `CLAUDE.md` / `ADR-0001` / `DEC-0003` signale
   ci-dessus.
8. **nadia-prof** : joue l'increment et prononce le Done pedagogique dans
   `squad/conformite/FICHE-bandit-manchot-additions-cp.md`.

## Correction du 2026-08-04, a la cloture du sprint

Ce planning affectait a l'origine les contrats de domaine et d'application a `marc-dev`
(sequencement, etape 2 des prochaines etapes ci-dessus). Ce n'est pas ce qui s'est passe : le
commit `a3e530b` montre que `theo-archi` les a livres lui-meme avec le squelette Maven, pour que
son etape compile seule avant que `marc-dev` ne demarre la mecanique. Corrige par `sofia-sm` a la
review, les deux occurrences ci-dessus reformulees en consequence : le depot etant la seule
source de verite, un planning qui decrit une affectation qui n'a pas eu lieu est faux.
