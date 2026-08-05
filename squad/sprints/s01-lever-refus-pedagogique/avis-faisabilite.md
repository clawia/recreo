# [Sprint s01] Avis de faisabilite - theo-archi

- **Date** : 2026-08-05
- **Auteur** : theo-archi
- **Backlog lu** : `docs/backlog/backlog.md`, section "Sprint 1 - Lever le refus pedagogique de
  bandit-manchot-additions-cp", stories S1-1 a S1-9
- **Piece de reference** : `squad/conformite/FICHE-bandit-manchot-additions-cp.md` (REFUSE,
  `nadia-prof`, 2026-08-04) - ses constats sont plus precis que les stories, je m'y refere
  directement quand le backlog resume.
- **Code relu** : `com.recreo.domain.mechanics.banditmanchot` (`BanditManchot`,
  `BanditManchotSession`, `BanditManchotMove`, `Verdict`), `AdditionsBorneesContentSource`,
  `PartieController`, `PartieService`, `GameDefinition`, les trois gabarits d'`adapter-web` et
  `recreo.css`.

## Synthese

| Story | Avis | ADR requis | Point a trancher hors architecture |
|---|---|---|---|
| S1-1 | Faisable en l'etat | aucun | perimetre exact du controle automatique de redaction (voir "Intestable par construction") - route relecteur-pedagogique / yanis-qa |
| S1-2 | Faisable en l'etat | aucun | forme exacte du score (etoiles, jetons, nombre seul) - route camille-po / nadia-prof |
| S1-3 | Faisable moyennant ADR | **ADR-0011** (nouveau) | libelle exact du message d'erreur et du bouton - route camille-po / nadia-prof |
| S1-4 | Faisable moyennant ADR, un critere nuance | **ADR-0012** (nouveau) | nombre exact de manches (Nadia suggere 10) ; formulation du message de reprise - route camille-po |
| S1-5 | Faisable en l'etat pour l'indice ; pour la "manche plus simple", faisable sans toucher aux contrats selon l'interpretation retenue - voir corps | aucun ADR si l'interpretation la plus simple est retenue | ce qu'on entend par "plus simple" est une decision pedagogique, pas technique - route camille-po / nadia-prof |
| S1-6 | Faisable techniquement ; le fond est perceptif, pas seulement pedagogique | aucun | absence d'agent design (obstacle ouvert du sprint 0) - route sofia-sm |
| S1-7 | Faisable en l'etat | aucun | le critere ne fixe aucun seuil chiffre ("nettement inferieure au tiers") - route camille-po pour le rendre testable |
| S1-8 | Faisable en l'etat (redaction et donnees de catalogue) | aucun | idem S1-1, controle de redaction - route relecteur-pedagogique |
| S1-9 | Faisable techniquement ; le fond est perceptif | aucun | absence d'agent design - route sofia-sm |

Deux ADR nouveaux ce sprint : `ADR-0011` (S1-3) et `ADR-0012` (S1-4). Les sept autres stories ne
demandent aucune decision d'architecture nouvelle - elles s'executent a l'interieur des dix ADR du
sprint 0, deja acceptes.

## S1-1 - Un texte que l'enfant peut lire sans apprendre une faute

**Avis : faisable en l'etat. Aucun ADR.**

Les trois fautes que Nadia cite (`ca`, `reponse`, `etait` sans accents dans
`fragments/retour.html`) sont un defaut de redaction dans un gabarit, pas un defaut
d'architecture : ADR-0008 dit deja que le texte affiche a l'enfant est du rendu serveur, rien de
plus n'est requis pour que l'accentuation soit corrigee. `DEC-0002` amendee porte deja l'exigence
positive ("le texte destine a l'enfant est redige en francais correct et integralement accentue")
et son controle est confie au `relecteur-pedagogique`, pas a moi.

**Intestable par construction, et ce qui l'est quand meme.** "Aucune faute d'orthographe ou de
ponctuation" en toute generalite n'est pas verifiable par un automate sans un correcteur
linguistique complet, hors de portee et hors de propos ici (un tel outil produirait des faux
positifs sur des tournures correctes et volontairement simples de CP). Ce qui **est** testable
sans ambiguite : les trois formes fautives precises que Nadia a nommees ("ca" pour "ça", "reponse"
pour "réponse", "etait" pour "était") sont des sous-chaines connues, absentes une fois corrigees ;
un test de comparaison de sous-chaine litterale (pas une expression reguliere, deja interdite dans
`adapter-web` par `AssertionsWebTest`) sur les trois gabarits peut les detecter en regression. Je
recommande a `yanis-qa` ou au `relecteur-pedagogique` d'ajouter ce filet, mais je le nomme comme un
complement de defense, pas comme une preuve d'exactitude orthographique generale : il attrape la
regression connue, pas une faute nouvelle. Le jugement de fond reste celui du `relecteur-pedagogique`
a chaque livrable, comme l'exige l'amendement de process du sprint 0.

## S1-2 - Un score que l'enfant de CP sait lire

**Avis : faisable en l'etat. Aucun ADR.**

`Score(int reussites, int tentatives)` (`com.recreo.domain.mechanic.Score`) ne change pas : les
deux nombres existent deja dans le domaine, seule leur **mise en forme** dans
`fragments/manche.html` et `fragments/retour.html` porte la notation "x / y" incriminee. C'est un
changement de gabarit et de modele de vue dans `adapter-web`, rien de plus - aucune regle
d'ADR-0006 n'est en jeu, `Score` n'est pas le contrat de contenu.

**Testable par automate** : la notation demandee ("nombre de reussites seul", "jetons", "etoiles")
est verifiable par jsoup sur le fragment rendu - absence du caractere `/` dans l'element de score,
absence d'un rendu du type `reussites sur tentatives`. **Intestable par construction** : "aucun
symbole du score ne peut etre lu comme un signe operatoire" est en partie testable (absence des
caracteres `+`, `-`, `x`, `/` dans le texte du score) et en partie une question de perception si la
forme retenue est iconographique (un jeton dessine peut-il, par sa forme, evoquer un operateur ?) -
cette derniere moitie n'est jugeable qu'a l'oeil, par `nadia-prof` ou par le futur agent design
(voir S1-6).

## S1-3 - Un ecran franchissable quand quelque chose tourne mal

**Avis : faisable moyennant `ADR-0011`.**

Lu dans le code : `PartieController.sessionEnCours` leve `IllegalStateException` quand la session
HTTP ne porte pas l'attribut de jeu attendu, et `jeuOuEchec` leve `NoSuchElementException` sur un
identifiant de jeu inconnu. Aucune des deux n'est interceptee aujourd'hui : Spring Boot les laisse
remonter jusqu'a sa page d'erreur par defaut, qui rend le JSON que Nadia a reproduit. C'est
exactement le mecanisme du motif de refus 3.

**Ou vit la correction : `adapter-web` seul, pas `bootstrap`.** Le detail est dans `ADR-0011` :
une classe `@ControllerAdvice` avec un `@ExceptionHandler` sur les deux exceptions citees, qui
rend `fragments/erreur :: erreur` avec un statut HTTP 200 (necessaire pour que htmx substitue
effectivement le fragment - un statut d'erreur ne serait pas substitue par htmx dans sa
configuration par defaut, laissant l'ecran fige ou pire). `bootstrap` n'a besoin d'aucun
changement : il n'y a pas de route hors du controleur de jeu a corriger, et la page d'erreur par
defaut de Spring Boot reste en place pour tout le reste (aucune story ne demande de la remplacer
globalement, voir "Consequences" d'`ADR-0011`).

**`ADR-0008` est-il a reecrire ? Non, seulement precise.** Ses trois gardes (budget JavaScript,
absence de marqueur de correction, jsoup plutot que regex) balaient deja tout gabarit `.html` sous
`templates/` et tout fichier JavaScript sous `static/js/` : le nouveau gabarit `fragments/erreur.html`
en herite sans qu'aucune regle existante ne change de texte. Ce qu'`ADR-0008` ne disait pas, et que
ce sprint montre absent, c'est ce qui se passe **avant** qu'un fragment soit choisi - le chemin
d'exception. J'ai ajoute un amendement de renvoi dans `ADR-0008` et j'ouvre `ADR-0011` pour cette
partie-la, avec sa propre regle ArchUnit (existence d'une porte `@ControllerAdvice`, interdiction
structurelle d'un gestionnaire d'erreur qui renvoie un `ResponseEntity` ou un corps `@ResponseBody`)
et son test d'integration nomme, qui reproduit le scenario exact de Nadia (jsoup, jamais regex,
meme convention qu'ADR-0008).

**Intestable par automate en totalite, testable pour l'essentiel** : "message court" se compte
(nombre de mots), "francais correctement accentue" retombe sur la meme limite que S1-1. Ce qui est
integralement testable : l'absence des marqueurs techniques constates (`"status"`, `"timestamp"`,
`Internal Server Error`) et la presence d'un bouton vers l'accueil, verifiees dans le test
d'integration nomme par `ADR-0011`.

## S1-4 - Une partie qui a une fin et une sortie

**Avis : faisable moyennant `ADR-0012` pour le premier critere ; le quatrieme critere est
satisfiable sans persistence, avec un critere a nuancer.**

### Le nombre de manches (premier et deuxieme critere)

`ADR-0006` avait deja nomme ce cas exact et l'avait laisse ouvert : "ce nombre est un parametre de
partie, il appartient a l'entree de catalogue ou a la mecanique... si cette contrainte devient
penible sur un cas reel, elle sera revue par un ADR". C'est ce cas reel. Je tranche pour le
**catalogue** plutot que pour une constante fixee dans la mecanique : une longueur de partie est un
attribut de *ce jeu-la* (cette association mecanique + paquet), pas de la mecanique bandit manchot
en general, et le placer en donnee respecte la promesse d'ADR-0007 ("ajouter quelque chose = un
fichier YAML, zero ligne de Java pour changer une longueur"). Le detail complet, y compris pourquoi
ce n'est **pas** une reouverture d'ADR-0006 (le nombre ne va toujours pas dans
`ContentDescriptor` ni dans `Question`, deja refuse par la regle existante), est dans `ADR-0012` :
`GameDefinition` gagne `int nombreDeManches`, le schema de catalogue gagne le champ correspondant
(obligatoire, echec au demarrage s'il manque, comme tout champ du schema), et
`GameMechanic.start(ContentSource, RandomSource, int nombreDeManches)` gagne un parametre.
`BanditManchotSession` compte les manches jouees et passe en `TERMINEE` au seuil, independamment de
ce que rend `AdditionsBorneesContentSource` (qui, seul, ne s'arrete jamais - c'est exactement la
Javadoc que Nadia cite).

Je n'ajoute **aucune nouvelle regle ArchUnit de garde** pour l'interdiction de placer ce nombre
dans le contenu : `DifficulteTest.leContratDeContenuNePorteQueDuTexte` (ADR-0006) la refuse deja,
pour le meme motif que `ReglageArithmetiqueContreExemple`. J'en fais un point explicite d'`ADR-0012`
plutot que de laisser croire qu'une regle neuve etait necessaire. Ce que j'ajoute reellement, ce
sont des cas a des tests de comportement deja obligatoires par ADR-0007 et ADR-0008 (voir le
tableau d'`ADR-0012`) : c'est le mecanisme qui garde la partie "positive" de la decision, l'autre
etant deja gardee.

### Le rechargement de page (quatrieme critere)

**Ce critere est satisfiable sans `adapter-persistence`.** L'etat de partie vit dans la session
HTTP du serveur (ADR-0008), et une session HTTP **survit** a un rechargement de page (F5) tant que
le cookie `JSESSIONID` et le processus serveur restent valides - elle n'est perdue qu'au
redemarrage du serveur ou a l'expiration de la session, c'est-a-dire exactement le cas que S1-3
couvre deja. Le constat de la reserve 7 de la fiche ("F5 ramene a l'accueil, le score repart de
zero") n'est donc pas une limite de la session HTTP : c'est que `PartieController.accueil()`
affiche toujours la liste des jeux sur `GET /`, sans jamais regarder si une partie est deja en
cours dans la session. C'est un defaut de routage dans `adapter-web`, corrigeable sans nouveau
module : `accueil()` doit lire l'attribut de session existant et, s'il porte une partie en cours,
rendre son etat plutot que la liste des jeux.

**Point d'implementation, pas d'architecture, a signaler a qui prend cette story** : les fragments
actuels (`manche.html`, `retour.html`) sont ecrits comme des documents `<html>` autonomes, extraits
par leur `th:fragment`, et servis uniquement en reponse a une requete htmx qui remplace `#jeu`.
Une reprise sur `GET /` est une **navigation complete** : la reponse doit porter `<head>`, la
feuille de style et les scripts, comme `accueil.html`. Il faudra donc soit une page dediee de
reprise incluant le fragment de manche via composition Thymeleaf, soit une condition dans
`accueil.html` lui-meme. Aucun des deux ne touche a une regle d'architecture.

**Rappel du renvoi d'ADR-0001** : si une story future demandait de retrouver une partie **au-dela**
d'une session HTTP (un autre appareil, un onglet ferme puis rouvert plus tard, un redemarrage
serveur qui ne doit pas faire tout perdre), ce serait le moment ou `adapter-persistence` se cree,
avec sa couche ajoutee a `ArchitectureEnCouchesTest` dans le meme commit, comme ADR-0001 le prevoit
explicitement depuis le sprint 0. Ce n'est pas ce que demande S1-4 : je ne cree rien.

**Testable par automate** : la terminaison au bon compte de manches (test de comportement de
mecanique, seed fixe), la reprise apres un second appel `GET /` dans la meme session (test
d'integration), la visibilite du bouton d'accueil sur chaque fragment (jsoup). **Intestable par
construction** : que le nombre soit "annonce" de facon comprehensible pour un enfant de CP (un
compteur "3 sur 10" est-il lisible a cet age ?) reste un jugement de `nadia-prof`.

## S1-5 - Un enfant qui echoue plusieurs fois est aide, pas enferme

**Avis : faisable en l'etat pour l'indice. Pour la "manche plus simple", j'ai regarde les deux
chemins et je recommande celui qui ne touche a aucun contrat - mais le choix a une composante
pedagogique que je ne tranche pas.**

### L'indice

Un indice doit venir du **contenu** : la mecanique ignore la matiere (ADR-0004, regle 3), elle ne
peut donc pas inventer un indice pertinent pour une addition ou pour un futur contenu de francais.
La solution la plus simple est un champ optionnel de texte sur `Question`
(`Optional<String> indice()` ou equivalent) : un `String` reste permis par
`leContratDeContenuNePorteQueDuTexte` (ADR-0006) sans aucune modification de regle - je l'ai
verifie sur la logique de la regle actuelle, qui n'exclut que ce qui n'est ni `String`, ni
collection du JDK, ni type du contrat. Chaque fabrique de contenu (`AdditionsBorneesContentSource`
pour ce paquet-ci, une fabrique de paquet enumere pour un autre) reste seule a savoir produire un
indice pertinent pour sa propre matiere ; la mecanique se contente de l'afficher si present, apres
le seuil d'echecs. **Aucun ADR requis.**

### La "manche plus simple"

Deux chemins, tels que demandes :

1. **Etendre les contrats** pour qu'un contenu expose une notion de difficulte ou une capacite
   "item plus facile" (par exemple une methode supplementaire sur `ContentSource`). Cela
   fonctionnerait, mais cela rouvre exactement le risque qu'ADR-0006 a ferme : introduire une
   notion de difficulte partagee entre matieres, meme sous forme d'une methode plutot que d'un
   champ, redemande a chaque contenu futur de repondre a une question qui ne le concerne pas
   forcement ("qu'est-ce qu'un item plus facile, pour un contenu de vocabulaire anglais ?").
2. **Rester dans la mecanique, sans toucher a aucun contrat.** `BanditManchotSession` garde deja un
   ensemble d'identifiants deja tires ; il suffit d'y associer le verdict (juste/faux) obtenu la
   premiere fois. Apres le seuil d'echecs consecutifs, au lieu de demander un nouvel item a
   `ContentSource`, la mecanique re-presente un item que l'enfant a deja reussi dans la session -
   "plus simple" au sens le plus strict et le plus verifiable : un item deja resolu une fois. Aucun
   contrat ne change, tout reste dans `com.recreo.domain.mechanics.banditmanchot`, testable par un
   test de comportement de mecanique ordinaire (seed fixe, sequence d'echecs puis verification de
   l'enonce represente).

**Je recommande le chemin 2**, par simplicite (moins de pieces mobiles, ADR-0004 reste inchange) et
parce qu'il ne demande aucun ADR. **Mais le choix entre les deux n'est pas qu'une question
d'architecture** : "plus simple" au sens 2 signifie "deja reussi", pas "mathematiquement plus
facile" (une somme plus petite). Si `nadia-prof` juge que la premiere acception ne repond pas au
besoin pedagogique constate (un enfant qui echoue sur des additions proches de 10 a besoin de
sommes plus petites, pas de retrouver une question qu'il a deja vue), alors le chemin 1 devient
necessaire malgre son cout, et je le referai avec un ADR nomme le jour ou cette decision produit
sera prise. Je route ce choix a `camille-po` et `nadia-prof`, pas a moi.

**Intestable par construction** : que l'aide "aide" reellement l'enfant plutot que de le frustrer
davantage est un jugement pedagogique, jamais un test. **Testable** : la presence effective d'un
indice au bon moment, le declenchement de la manche de reprise au bon seuil, la persistance du
bouton de sortie pendant une serie d'echecs.

## S1-6 - Un signal juste/faux qui ne depend pas de distinguer les couleurs

**Avis : faisable techniquement. Aucun ADR - c'est un changement de `recreo.css` et
eventuellement du gabarit, deja couvert par les regles existantes.**

`recreo.css` distingue aujourd'hui `.signal.est-juste`/`.signal.est-faux` par couleur de fond et
deux formes (cercle, triangle) qui n'ont pas de sens conventionnel pour un enfant. Remplacer ces
formes par une coche et une croix (dessinees en CSS ou SVG, ADR-0008 et `DEC-0002` l'autorisent
tant qu'aucun caractere emoji n'entre dans un gabarit) ne touche a aucune regle d'architecture :
`FrontieresDuJavaScriptTest` ne regarde que le JavaScript et les marqueurs de correction, pas la
CSS.

**Intestable par construction, et c'est le coeur du critere.** "Leur forme seule permet de savoir
lequel dit juste et lequel dit faux" est un jugement de perception, pas une propriete de code : un
automate peut verifier que les deux formes sont **structurellement differentes** (deux
`clip-path`, deux jeux de regles CSS distincts), pas qu'un enfant de six ans, daltonien ou non, les
reconnait comme "juste" et "faux". C'est exactement le trou nomme dans la retrospective du sprint 0
: aucun agent n'a mandat sur l'accessibilite visuelle depuis que `marc-dev` a ecrit `recreo.css`
sans que ce soit son mandat et que `nadia-prof` en ait juge sans que ce soit le sien non plus. Je
signale cet obstacle a nouveau ici parce que S1-6 et S1-9 en dependent directement, et parce qu'il
reste ouvert depuis le sprint 0 sans qu'aucun ADR ni aucune DEC ne l'aient encore referme - **c'est
a `sofia-sm` de trancher au planning**, pas a moi de creer un agent.

## S1-7 - Des additions qui font vraiment calculer

**Avis : faisable en l'etat. Aucun ADR.**

Changement localise dans `AdditionsBorneesContentSource.tirer()` : reduire la probabilite qu'un
operande tire soit nul (par exemple en retirant et re-tirant une fraction des cas nuls), avec un
nouveau parametre propre a cette fabrique si necessaire, declare sous `parametres` dans son propre
paquet YAML - exactement le mecanisme qu'ADR-0006 reserve a ce contenu-la et a lui seul. Aucune
autre matiere n'a a en tenir compte.

**Testable par automate, mais le critere ne fixe aucun seuil chiffre.** "Nettement inferieure au
tiers... sans jamais tomber a zero" se verifie parfaitement avec `RandomSource` injectable (meme
methode que S0-4 : graine fixe, comptage sur N tirages), **a condition qu'un nombre precis
remplace "nettement inferieure"**. Sans ce chiffre, deux implementations aussi legitimes l'une que
l'autre (10 % ou 25 %) satisferaient ou non le meme test selon qui l'ecrit. Je recommande a
`camille-po` de fixer une borne haute explicite avant que la story parte en developpement -
c'est exactement le type de precision que l'amendement de process du sprint 0 demande de faire
remonter des le planning plutot qu'a la relecture qualite.

## S1-8 - Des mots d'interface que l'enfant de CP comprend

**Avis : faisable en l'etat. Aucun ADR.**

Tous les textes cites (`"Manche suivante"`, `"Merci d'avoir joue !"`, `"Aucune partie disponible
pour le moment."`, le retour faux, `"Additions CP"`) sont soit des libelles de gabarit
(`adapter-web`), soit des donnees de catalogue (`titre`, `consigne` dans `catalogue.yaml`, ADR-0007).
Aucun n'est un type ou une regle du domaine. Le renommage du bouton de jeu ne touche a aucune
mecanique : la mecanique ignore deja les libelles, qui sont portes par la vue et par le catalogue
(ADR-0004, ADR-0007).

**Testable pour les remplacements nommes, intestable pour la generalite.** Les cinq remplacements
concrets cites par la story (absence du mot "manche", absence de "Merci d'avoir joue !", etc.) sont
verifiables par comparaison de sous-chaine litterale sur les gabarits et sur `catalogue.yaml` - le
meme mecanisme que S1-1. "Vocabulaire courant de CP" en general reste un jugement de
`nadia-prof`/`relecteur-pedagogique`, pas un automate.

## S1-9 - Un calcul et un signal plus grands que les boutons

**Avis : faisable techniquement. Aucun ADR - changement de `recreo.css` uniquement.**

Les seuils demandes (au moins 2 rem pour l'enonce, au moins 3 rem pour le signal) sont des valeurs
declarees dans une feuille de style, lisibles et comparables par un test qui lit le fichier texte
`recreo.css` (comparaison de valeur, pas une expression reguliere : le fichier n'est pas du Java,
`AssertionsWebTest` ne le concerne donc pas, mais je recommande la meme discipline de lisibilite -
une assertion directe sur la valeur declaree, pas un motif approximatif).

**Testable au niveau declare, intestable au niveau rendu.** Qu'une regle CSS declare `font-size:
2rem` est verifiable. Que le texte apparaisse **effectivement** plus grand a l'ecran, compte tenu
des reglages d'accessibilite du navigateur de l'enfant, ne l'est pas sans un navigateur reel - hors
de portee de `mvn verify`. Meme obstacle que S1-6 : aucun agent n'a mandat sur ce jugement visuel
aujourd'hui.

## Deux obstacles herites du sprint 0

**`leContratDeContenuNePorteQueDuTexte` admet encore un enum arithmetique place dans le paquet de
contrat lui-meme.** Je ne le corrige pas ce sprint : aucune des neuf stories S1 n'ajoute d'enum au
paquet `com.recreo.domain.content`, et corriger une regle hors de toute pression d'un besoin reel
serait exactement le type d'anticipation que je m'interdis. Ce que je fais a la place : si S1-4 ou
S1-5 finit par introduire un enum dans `domain.content` (par exemple pour typer une nature
d'indice), le trou devra etre ferme dans le meme commit, pas laisse pour un sprint de plus - je le
verifierai a la review. Le trou reste documente ici et dans `ADR-0006` tant qu'il n'est pas ferme.

**ADR-0009 et ADR-0010 n'ont aucun point ouvert qui concerne ce sprint.** Les deux garde-fous de CI
(prefixes de commit, chemins de poste de travail) sont stables depuis leurs amendements du
2026-08-05 ; aucune story de ce sprint ne les sollicite d'une facon nouvelle. Rappel valable pour
tout le monde ce sprint : ne jamais ecrire un chemin de poste de travail dans un fichier suivi,
`ADR-0010` et la CI le refusent desormais tous les deux.

## Risques que je signale des maintenant

1. **`GameMechanic.start` change de signature (ADR-0012).** Un seul appelant existe
   (`PartieService.demarrer`) et une seule implementation (`BanditManchot`) : le cout est reel mais
   contenu a ce sprint. Le risque grandirait avec une deuxieme mecanique deja en cours d'ecriture
   au moment du changement - ce n'est pas le cas.
2. **Deux stories (S1-6, S1-9) et une reserve de S1-2 retombent sur un jugement visuel qu'aucun
   agent n'a mandat de rendre**, obstacle deja identifie a la retrospective du sprint 0 et toujours
   sans decision. Je ne le tranche pas ; je le signale une deuxieme fois pour qu'il ne se reperde
   pas.
3. **La tentation, sur S1-5, de resoudre "manche plus simple" en rouvrant les contrats de contenu
   par reflexe** plutot que de chercher d'abord une solution interne a la mecanique. J'ai fait ce
   travail dans cet avis precisement pour qu'il ne soit pas refait sous pression de sprint avec
   moins de recul.
4. **Le controle automatique d'orthographe que je recommande pour S1-1 et S1-8 (comparaison de
   sous-chaine) est un filet de regression, pas une preuve d'exactitude.** Il ne doit pas etre lu
   comme dispensant le `relecteur-pedagogique` de sa relecture de fond : c'est explicitement la
   lecon du manque signale par `nadia-prof` dans sa relecture de `DEC-0002`.

## Verification ArchUnit

Base de reference confirmee avant d'ecrire cet avis, commande et resultat a l'appui :
`mvn -B verify` depuis la racine, JDK 21 (`java version "21.0.10"`) - `BUILD SUCCESS`, 92 tests
(26 `domain`, 6 `application`, 10 `adapter-web`, 4 `adapter-content`, 44 + 2 `bootstrap` - les deux
derniers sont le surefire et le failsafe du meme module, l'un unitaire, l'autre d'integration), 0
echec. Ce chiffre est identique a celui du rapport de sprint 0
(`squad/sprints/s00-socle/RAPPORT.md`) : aucune derive n'est survenue entre les deux sprints.

Les regles nouvelles de ce sprint (`ADR-0011`, `ADR-0012`) ne sont pas encore ecrites en code -
aucune ligne de production n'est modifiee par cet avis, conformement au mandat de ce lot. Leurs API
ArchUnit ont ete verifiees directement contre les classes de `archunit-1.5.0.jar` (decompilation
`javap` de `MembersThat`, `MembersShould`, `CodeUnitsShould`, `MethodsShouldConjunction`,
`ClassesThat`) plutot que supposees : `areAnnotatedWith(String)`, `notBeAnnotatedWith(String)`,
`notHaveRawReturnType(String)` et `andShould()` existent tous avec la signature citee dans
`ADR-0011`. Le statut complet (regle par regle, contre-exemple, test de non-vacuite) sera confirme
avant la review, conformement a `squad/README.md` section 9 etape 4, une fois le code de ces deux
ADR livre.
