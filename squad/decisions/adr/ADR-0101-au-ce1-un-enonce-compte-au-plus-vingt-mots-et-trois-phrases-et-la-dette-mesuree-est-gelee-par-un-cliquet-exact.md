# ADR-0101 - Au CE1, un enonce compte au plus vingt mots et au plus trois phrases, et la dette mesuree est gelee par un cliquet exact

- **Statut** : Accepte
- **Date** : 2026-09-25
- **Sprint** : s43
- **Auteur** : `theo-archi`
- **Route par** : `nadia-prof`, `squad/conformite/ARBITRAGE-s36-longueur-d-enonce-au-ce1.md`,
  section 2, qui pose `LON-CE1-1` et `LON-CE1-2` le 2026-09-21 et ecrit que la convention de
  comptage est celle d'`ADR-0089` *"reprise sans un mot de changement"*. Et par `DUR-CE1-6`
  (`squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md`, section 5.3), qui les
  reclame pour le paquet de `MAT-CE1-12` : *"`LON-CE1-1` et `LON-CE1-2` s'appliquent - et non les
  25 mots de `D-8`, qui valent pour le CM1 et pour lui seul"*
- **Arbre mesure** : worktree `s43-theo-adr`, branche partie de `round-two` a `5bd99d74`. JDK
  **21.0.10**, version que la machine virtuelle des tests rapporte d'elle-meme et non celle qu'une
  entree de toolchain declare (`ADR-0071`), Maven 3.9.9. Toute mesure partielle du reacteur porte
  `-am` et nomme son module
- **Prolonge** : `ADR-0089` (la barre du CP et sa convention de comptage), `ADR-0013` (perimetre
  derive, garde-fou mis a l'epreuve), `ADR-0051` (un garde-fou dit combien d'objets il examine),
  `ADR-0061` (une convention vit en un seul endroit), `ADR-0067` et `ADR-0087` (cliquet a plafond
  exact), `ADR-0078` (le niveau est lu dans le paquet)
- **N'ecrit aucun contenu, ne prononce aucun Done, ne rouvre aucun Done, ne deplace aucun statut de
  couverture.**

---

## Contexte

### 1. Une barre arbitree, tenue par personne

`nadia-prof` a pose le 2026-09-21 :

> **`LON-CE1-1`** - au CE1, un enonce compte **au plus 20 mots**.
>
> **`LON-CE1-2`** - au CE1, un enonce compte **au plus trois phrases**, et **la derniere est la
> question**.

**Aucun automate ne la tenait.** Mesure du 2026-09-25 : zero occurrence de la chaine `LON-CE1` dans
le Java du depot, tous modules, `target/` exclu. La cause n'est pas un oubli de perimetre, c'est un
perimetre ferme par une constante : `AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest` porte
`NIVEAU_BORNE = "CP"` et sa population est *les enonces des paquets dont le descripteur porte le
niveau borne*. Un paquet de CE1 n'y est pas exempte : **il n'y est pas vu**. `nadia-prof` l'avait
elle-meme verifie dans la source du garde-fou plutot que dans son titre (section 1 de son
arbitrage), et elle avait ecrit ce que cela coutait : *"tant qu'il n'existe pas, elles sont tenues
par moi, en comptant"*.

### 2. Le prix de cette absence, mesure sur du materiau reel et non suppose

`ce1-longueurs.yaml` porte **quatre** enonces au-dela de 20 mots - ses items 11 (22 mots), 12 (27),
13 (26) et 14 (26). Son Done pedagogique a ete prononce a **s39**, soit **apres** l'arbitrage du
2026-09-21 qui pose la barre. **Quatre enonces ont franchi une barre arbitree sans qu'aucun rouge ne
le dise**, dans un depot dont l'axiome est qu'une regle non encodee n'est pas une regle.

C'est la preuve que je prefere a une fixture : elle n'est pas de ma main, elle est ce que le depot a
reellement servi.

### 3. Ce que la barre du CP aurait fait si on l'avait transposee

Mesure du 2026-09-25 sur les **144** enonces de CE1 servis par sept paquets, meme convention de
comptage dans les deux colonnes :

| Barre appliquee | Enonces trop longs | Defauts de forme |
|---|---|---|
| Celle du CP (15 mots, 2 phrases) | **18** | **6** |
| Celle du CE1 (20 mots, 3 phrases) | **4** | **0** |

Les deux barres ne mesurent pas la meme chose. Etendre celle du CP au CE1 aurait pose un poteau que
`nadia-prof` n'a pas pose, sur une population qu'elle a comptee autrement - la faute qu'`ADR-0089`
s'interdisait deja sur les propositions de CP.

## Decision

1. **Encoder `LON-CE1-1` et `LON-CE1-2`** dans un garde-fou dont la population est derivee :
   les enonces des paquets servis dont le **descripteur** porte `CE1` (`ADR-0078`), champ `enonce`
   seul - ni proposition, ni indice, ni consigne, que l'arbitrage ne compte pas.
2. **Ne pas recopier la convention de comptage.** Le garde-fou du CE1 appelle les methodes `mots` et
   `phrases` de celui du CP, qui portent deja la convention arbitree. Un second exemplaire rancit
   (`ADR-0061`), et deux barres qui divergeraient sur ce qu'est un mot seraient pires que pas de
   barre.
3. **Geler la dette mesuree par un cliquet exact**, ne pas l'interdire seche.
   `DETTE_DE_LONGUEUR = { ce1-longueurs.yaml : 4 }`, `PLAFOND_DE_DETTE = 4`, et le plafond vaut
   **exactement** la somme de la table. Un enonce trop long de plus est rouge ; un de moins l'est
   aussi, ce qui force a baisser le chiffre dans le diff qui paie. `nadia-prof` a refuse d'avance de
   rouvrir le Done de `ce1-longueurs` - *"une barre qu'on releve apres qu'une equipe l'a atteinte
   fait de ma porte un caprice"* - et la dette **enregistre** ces quatre enonces sans les absoudre.
4. **`LON-CE1-2` n'a aucune exemption** : `PLAFOND_DE_DETTE_DE_FORME = 0` sur les 144 enonces
   servis, et ce n'est pas une clause sans objet.
5. **Ne pas parametrer une classe par niveau.** Deux barres valent deux fichiers ; une abstraction
   (niveau, mots, phrases) se justifiera sur la troisieme, pas sur la seconde.

## Regle executable ArchUnit

**Ce n'est pas une `ArchRule`** : ArchUnit lit du bytecode, et la longueur d'un enonce de paquet
YAML n'y est pas une propriete lisible. Le compte de regles ArchUnit du depot est inchange. Le
mecanisme automatique qui garde cette decision est un garde-fou de balayage, lance par `mvn verify`
comme n'importe quel test.

**Fichier** :
`bootstrap/src/test/java/com/recreo/archi/AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest.java`

Les constantes qui decident tout :

```java
private static final String NIVEAU_BORNE = "CE1";

private static final int MOTS_MAXIMUM = 20;

private static final int PHRASES_MAXIMUM = 3;

private static final Map<String, Integer> DETTE_DE_LONGUEUR = Map.of("ce1-longueurs.yaml", 4);

private static final int PLAFOND_DE_DETTE = 4;

private static final int PLAFOND_DE_DETTE_DE_FORME = 0;
```

Les huit tests, et ce que chacun interdit reellement :

| Test | Ce qu'il rend rouge |
|---|---|
| `leContenuServiDeCe1EstConforme` | un enonce de CE1 de 21 mots, ou de quatre phrases, ou de plusieurs phrases dont la derniere n'interroge pas |
| `leBalayageExamineTousLesEnoncesDeCe1` | une population qui se viderait en silence - plus de 100 enonces, plus de 5 paquets (`ADR-0051`) |
| `laDetteNePeutNiGrandirNiPourrir` | un plafond qui ne vaut plus la somme de la table ; un paquet nomme qui n'est plus servi ; un paquet nomme dont le compte reel a change |
| `sansSaDetteLeContenuServiDeCe1FaitRougirLaBarre` | **la mutation qui re-verifie le chiffre 4** : la dette videe, le garde-fou doit nommer les quatre items avec leur compte de mots |
| `uneDetteQuiNeVautPasLeCompteReelEstRouge` | une dette a 3 **et** une dette a 5 - l'autre sens du cliquet, sans lequel un plafond confortable serait un quota |
| `unEnonceDeQuatrePhrasesOuDontLaDerniereNInterrogePasEstRouge` | `LON-CE1-2`, sur un jeu fautif **de ma main et declare comme tel**, faute de materiau reel |
| `lesDeuxBarresNeMesurentPasLaMemeChose` | la transposition silencieuse de la barre du CP : les deux comptes sont compares sur le meme corpus |
| `lesPopulationsDuCpEtDuCe1SontDisjointes` | un paquet qui serait juge par les deux barres, donc vert d'un cote et rouge de l'autre |

**Mise a l'epreuve** (`ADR-0013` obligation 3) : le jeu fautif principal **n'est pas une fixture**.
C'est le contenu de CE1 reellement servi, juge **sans sa dette** : l'objet mute est la
**declaration**, pas le corpus. La lecon de s05 est qu'une fixture ecrite par l'auteur d'une regle
ressemble a ce qu'il imagine ; ici, seul `LON-CE1-2` en demande une, et elle est declaree comme
telle.

**Re-verification du chiffre 4, par mutation** : le plafond a ete porte a 5 dans un
`mvn -B -pl bootstrap -am test` puis remis a 4. Sous 5, **deux assertions rougissent**
(`laDetteNePeutNiGrandirNiPourrir`, sur la somme puis sur le compte reel du paquet) ; sous 4, les
huit tests passent. Le chiffre n'est donc pas un report de comptage a la main : il est rejoue par le
garde-fou a chaque execution.

## Consequences

**Bonnes.**

- La barre du CE1 cesse d'etre tenue a la main par `nadia-prof`. C'est exactement ce qu'elle
  demandait le 2026-09-21.
- Le paquet de `S43-MAT-DUREE` entre dans la population **des son premier commit**, par son
  descripteur. Le precedent le plus proche de sa forme, `cm1-problemes-durees`, porte 17 de ses 24
  enonces au-dela de 20 mots (il est au CM1, ou aucune barre ne s'applique) : sans ce garde-fou, un
  paquet de CE1 ecrit sur ce modele serait trop long et rien ne le dirait.
- Les quatre enonces de `ce1-longueurs` deviennent **comptes** au lieu d'etre invisibles.

**Mauvaises, et je les ecris.**

- **Geler n'est pas corriger.** Les quatre enonces restent trop longs, servis, et le cliquet ne
  retombera a zero que si quelqu'un les reecrit - ce que `nadia-prof` a refuse pour ce paquet-ci.
- **Le garde-fou du CE1 depend de celui du CP** : il appelle ses methodes et son record `Enonce`,
  qui passe de prive a visible dans le paquet. Renommer une methode du garde-fou du CP casse la
  compilation du garde-fou du CE1 - c'est le prix de l'exemplaire unique de la convention, et je le
  prefere a deux copies qui divergeraient en silence.
- **Deux fichiers portent maintenant une barre de longueur**, avec des constantes differentes. Une
  troisieme barre serait le signe qu'il faut les parametrer ; deux ne le sont pas.
- **La barre ne juge que l'enonce.** Un indice de 40 mots au CE1 reste vert, comme au CP, et pour la
  meme raison : cette population n'a pas ete comptee par qui pose les barres.

## Alternatives considerees

1. **Etendre le garde-fou du CP au CE1 avec ses constantes** (15 mots, 2 phrases). Ecartee : la
   mesure dit ce que cela couterait - 18 enonces trop longs et 6 defauts de forme sur du contenu
   valide - et cela poserait une barre que personne n'a arbitree.
2. **Une seule classe parametree par (niveau, mots, phrases).** Ecartee pour l'instant : deux
   niveaux ne justifient pas l'abstraction, et trois lignes semblables valent mieux qu'une
   abstraction prematuree. **Ce qui me ferait changer d'avis** : une troisieme barre de niveau.
3. **Interdire seche, sans dette, en corrigeant les quatre enonces.** Ecartee : cela rouvrirait un
   Done pedagogique que `nadia-prof` a explicitement refuse de rouvrir, et la decision de reecrire du
   contenu valide ne m'appartient pas.
4. **Un plafond a 5, pour laisser de la marge.** Ecartee : un cliquet dont le plafond depasse la
   dette n'est pas un cliquet, c'est un quota. La mutation ci-dessus le verifie dans les deux sens.
5. **Ne rien ecrire et attendre le premier paquet de `MAT-CE1-12`.** Ecartee : la barre existe depuis
   quatre sprints et elle a deja ete franchie quatre fois sans bruit.

## Ce que je route, et a qui

| Fait | A qui | Pourquoi ce n'est pas a moi |
|---|---|---|
| Les quatre enonces de `ce1-longueurs` restent au-dela de la barre, desormais comptes | `nadia-prof`, via `sofia-sm` | Reecrire du contenu valide est une decision pedagogique ; je le rends visible, je ne le corrige pas |
| Aucune barre de longueur ne borne le CM1 ni le CM2 | `nadia-prof` | Poser une barre sur une population non comptee est exactement ce qu'`ADR-0089` s'interdit |
