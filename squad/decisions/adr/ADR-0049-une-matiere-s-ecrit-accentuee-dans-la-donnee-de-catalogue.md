# ADR-0049 - Porter la matiere accentuee dans la donnee de contenu elle-meme, et garder l'unicite de sa graphie

- **Statut** : Accepte, avec sa regle ecrite dans le meme commit
- **Date** : 2026-08-19
- **Auteur** : theo-archi
- **Sprint** : s17 (avis de faisabilite, avant tout commit de code)
- **Repond a** : `DEC-0032` point 2, question routee le 2026-08-17 et **sans reponse depuis**, differee
  trois fois - la troisieme sur instruction de l'utilisatrice
- **Complete** : `ADR-0006` (ajouter une matiere est une donnee, jamais un enum), `ADR-0015` (deriver
  le perimetre de la source qui gouverne le rendu), `DEC-0002` amende (tout texte lu par un enfant est
  integralement accentue)
- **Regle executable** :
  `adapters/adapter-content/src/test/java/com/recreo/adapter/content/UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest.java`

## Contexte

`camille-po` a differe le filtre par matiere trois fois, et sa raison n'a pas bouge : les paquets
servis portent `matiere: "Francais"` et `matiere: "Mathematiques"` **en ASCII**, valeurs internes
jamais montrees a un enfant jusqu'ici. Le jour ou un bouton de matiere affiche ce mot, il devient du
texte lu par un enfant, et `DEC-0002` amende exige qu'il soit integralement accentue. Elle m'a route
deux questions techniques : la valeur interne peut-elle devenir accentuee sans rompre une comparaison
ailleurs, et si un libelle distinct est introduit, ou vit la correspondance.

**Ce qui a change et rend la question non differable, mesure plutot que suppose.** Le sprint 17 ouvre
du francais CM1 depuis `contenu-repris/`, et le garde-fou que `S16-5` a fait naitre - l'inventaire des
chaines exposees par le port entrant - **inventorie la matiere de chaque entree**. Toute entree neuve
oblige donc a inscrire sa matiere dans cet inventaire, dans le meme commit, avec son motif. La
question ne peut plus etre reportee par un choix de perimetre : elle est posee par un garde-fou.

**Les trois mesures qui tranchent, methode dans le meme paragraphe.**

1. **Les valeurs servies** : balayage des 12 paquets de
   `adapters/adapter-content/src/main/resources/contenu/`, lecture de la cle `matiere` -
   `Francais` **3 fois**, `Mathematiques` **9 fois**, une seule graphie par matiere.
2. **Les valeurs de la reserve** : balayage de `contenu-repris/`, meme cle - **tout est accentue**, et
   la reserve porte **neuf matieres distinctes** la ou le contenu servi en porte deux :
   `Francais` 19, `Mathematiques` 21, `Geometrie` 8, `Sciences et technologie` 3, `Anglais` 3, `EMC` 2,
   `Histoire` 2, `Questionner le monde` 2, `Geographie` 1 - les graphies reelles portant leurs accents.
3. **Aucune comparaison de production ne lit cette valeur** : `ContentDescriptor.matiere()` n'est
   appele nulle part dans les sources de production des cinq modules. Le seul comparateur du depot est
   une assertion de test, `YamlContentPackLoaderTest`.

**La question de `camille-po` a donc une reponse chiffree et non une preference.** Aligner sur la forme
accentuee coute **12 fichiers YAML et une assertion**, une fois. Aligner sur l'ASCII coute une
reecriture de la valeur **a chaque conversion**, sur une reserve de 61 fichiers qui la porte accentuee,
donc indefiniment, et chaque conversion est une occasion de l'oublier. Le second cout n'est pas
seulement plus grand : il est **recurrent et silencieux**, ce qui est la forme que ce depot sanctionne.

## Decision

**1. La matiere est portee accentuee par la donnee de contenu elle-meme.** `matiere: "Francais"`
devient la forme accentuee dans les 12 paquets servis, et toute conversion de la reserve garde la
valeur telle qu'elle y est ecrite. **Il n'y a pas de second champ, pas de libelle d'affichage distinct,
pas de table de correspondance.**

**2. Pourquoi pas un libelle distinct de l'identifiant.** Deux champs la ou un seul suffit, avec la
certitude qu'ils divergeront - c'est la famille exacte de la dette 2 de la version precedente, un champ
que chaque paquet doit remplir sans que son contenu en ait besoin. **Ce qui me ferait changer d'avis,
ecrit d'avance** : le jour ou un libelle affichable doit differer de la valeur pour une raison qui n'est
pas l'accent - un bouton trop etroit pour "Questionner le monde", par exemple, matiere que la reserve
porte deja. Le besoin serait alors constate et non anticipe, et le second champ se justifierait seul.

**3. Pourquoi pas une correspondance dans le gabarit.** `ADR-0006` le decide et non moi : ajouter une
matiere est **une donnee**. Une table de libelles dans une vue ferait d'un ajout de matiere une
modification de gabarit, exactement le defaut que `ADR-0044` a ferme pour les niveaux.

**4. Un identifiant sans accent reste possible, et c'est une donnee de plus, jamais un calcul.** Le jour
ou une route de matiere existera, elle aura besoin d'un segment d'URL sans accent. Ce segment sera
**une cle du paquet**, ecrite a cote de la matiere, et jamais une transformation calculee de celle-ci :
un depliage d'accents cale dans le code redeviendrait une regle de francais dans un adaptateur. Ce
point n'est pas ouvert par cette decision et n'est pas du ce sprint ; il est ecrit pour que la question
ne soit pas rouverte de zero.

**5. Ce que cette decision ne tranche pas.** La graphie exacte, la majuscule, et si le mot qu'un enfant
de CM1 doit lire est celui-la : `DEC-0002` amende et `nadia-prof`. Cette decision dit **ou** la valeur
vit et **qu'elle est unique** ; elle ne dit pas ce qu'elle doit etre.

## Regle executable

Fichier :
`adapters/adapter-content/src/test/java/com/recreo/adapter/content/UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest.java`

> **Une matiere s'ecrit d'une seule facon dans tout le contenu servi.**

**Ce que la regle garde, et ce qu'elle ne garde pas - dit dans cet ordre.** Elle ne dit **pas** laquelle
des deux graphies est la bonne : aucun dictionnaire n'entre dans ce depot, et aucun automate n'y decide
de l'orthographe du francais. Le point 1 tranche que c'est la forme accentuee, et c'est une decision
ecrite, opposable en relecture par `relecteur-pedagogique` et `nadia-prof`. Ce que la regle garde est
l'**unicite**, le seul invariant de cette decision qui se derive.

**Ce qu'elle interdit reellement, et c'est le geste que ce sprint est sur le point de faire** : deposer
un paquet converti qui porte `Francais` accentue a cote des paquets servis en ASCII. Avant cette clause,
rien ne le voyait - les deux valeurs se chargent, aucun test ne les compare, et le contenu servi
porterait deux orthographes d'une meme matiere dont une seule peut etre montree a un enfant.

**Obligations d'`ADR-0013`, les trois.**

1. **perimetre derive** : les paquets viennent du balayage du repertoire de contenu, jamais d'une liste,
   et leur matiere vient du **chargeur reel** - la chaine que l'enfant traverse. C'est deliberement le
   repertoire et non le catalogue : un paquet retire du catalogue (`DEC-0009`, `DEC-0024`) est la
   matiere de travail d'une story de retour, et un garde-fou derive du catalogue ne le verrait plus -
   ce qui compte precisement pour `S17-4` ;
2. **derivation totale** : aucun paquet releve, et un groupe portant plus d'une graphie, sont des
   **echecs**. Le repliage ne connait **aucune liste de mots** : il retire les diacritiques par
   `java.text.Normalizer` et compare sans casse, donc une matiere neuve entre dans le controle sans que
   ce fichier change - `ADR-0039`, une propriete calculee et non une enumeration ;
3. **mise a l'epreuve** : deux paquets fautifs qui portent les deux graphies de la meme matiere, avec
   les **valeurs reelles des deux repertoires** plutot qu'une fixture imaginee.

**Non-vacuite (`ADR-0003`)** : une clause exige que le contenu servi porte **plus d'une** matiere, sans
quoi l'unicite ne comparerait rien et passerait verte a vide.

**Mesure, methode comprise** : `bash outils/mvn-jdk21.sh -B -o -pl adapters/adapter-content -am test
-Dtest=UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest`, JDK 21.0.10 de la toolchain, apres un
`clean` du reacteur complet plus tot dans ce lot - **3 tests, 0 echec** : la clause sur la production,
la non-vacuite, et la morsure. Le `-am` est porte parce que la mesure est partielle (`CLAUDE.md`,
prerequis de build).

**Ce n'est pas une regle ArchUnit**, et le compte reste a **29** : l'objet juge est la valeur d'une cle
d'un fichier de ressources, qu'ArchUnit ne voit pas. Le mecanisme automatique est un test du module
`adapter-content`, execute par `mvn verify`.

## Consequences

- **La question de `DEC-0032` point 2 est fermee**, apres trois reports. Le filtre par matiere n'a plus
  de prealable d'architecture.
- **Douze fichiers de contenu servis doivent changer de graphie**, plus l'assertion de
  `YamlContentPackLoaderTest` et l'inventaire du garde-fou de `S16-5`. C'est un cout reel, borne, et
  il ne se represente jamais.
- **Le contenu servi et la reserve cessent de diverger sur ce point**, donc une conversion devient une
  copie plutot qu'une reecriture. C'est le gain principal, et il est proportionnel au nombre de
  conversions restantes - la reserve en porte 61.
- **Mauvaise consequence assumee** : une valeur accentuee est plus fragile a manipuler dans un outil qui
  n'est pas en UTF-8. Le depot est deja UTF-8 de bout en bout et le prouve - les enonces servis portent
  des accents depuis s01, et `CorpsFormulaireEnUtf8ToleranteFilter` existe pour le seul cas ou un
  navigateur renvoie autre chose. Le risque est donc nomme et deja couvert ailleurs.
- **La regle ne dira jamais qu'une graphie unique est la bonne graphie.** Un contenu entierement en
  ASCII serait vert. C'est la limite structurelle de cette clause, et c'est `relecteur-pedagogique` qui
  tient l'autre moitie.

## Alternatives considerees

1. **Un libelle d'affichage distinct de la valeur interne.** Ecartee au point 2, avec son declencheur de
   reouverture ecrit.
2. **Garder l'ASCII dans les paquets et accentuer a l'affichage.** Ecartee : la transformation serait une
   regle de francais dans un adaptateur, et elle serait fausse des la premiere matiere dont l'accent
   n'est pas derivable de sa forme ASCII.
3. **Une table de correspondance dans le gabarit ou dans le controleur.** Ecartee par `ADR-0006` et par
   `ADR-0044` : ajouter une matiere cesserait d'etre une donnee.
4. **Un enum de matieres.** Ecartee sans hesitation : `ADR-0006` l'a tranche en s00, et c'est la dette 2
   de la version precedente.
5. **Continuer a differer.** Ecartee, et c'est la seule alternative que je dois justifier plutot que
   mesurer : le garde-fou de `S16-5` force l'inscription de la matiere de toute entree neuve dans un
   inventaire. Differer une quatrieme fois obligerait a inscrire une graphie dont on sait qu'elle est
   fausse pour un enfant, ce qui est pire que de trancher.
