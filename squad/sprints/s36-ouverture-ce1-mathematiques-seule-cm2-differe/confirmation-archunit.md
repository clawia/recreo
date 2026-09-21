# Confirmation de la porte technique - s36 (`theo-archi`)

- **Auteur** : `theo-archi`. **Date** : 2026-09-21.
- **Perimetre mesure** : branche `s36-sofia` a `1d361786`, worktree dedie `s36-theo-porte` branche
  depuis elle (amendement s30 : toute mesure du reacteur depuis son propre worktree, premiere
  ecriture ou non). Lignee verifiee avant toute ecriture :
  `git merge-base --is-ancestor round-two HEAD`, code de retour 0.
- **Version** : **JDK 21.0.10**, la version que le JDK resolu par l'enveloppe rapporte, pas celle
  qu'une entree de toolchain declare (`ADR-0071`). Premiere ligne de l'enveloppe :
  *"Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10"*.

---

## 0. Verdict, en une table

| Objet | Verdict |
|---|---|
| `mvn -B clean verify` sur la racine | **vert**, 6 modules sur 6, `BUILD SUCCESS` |
| Compte de tests | **1487**, 0 echec, 0 erreur, 0 ignore |
| `ArchRule` du depot | **33** par la methode de s35 (`@ArchTest`), inchange par ce sprint - **et 34** par la methode que mes deux `ADR` de s36 ont ecrite a cote du chiffre (section 2) |
| Preuves de morsure | **trois**, toutes par mutation de source de production (sections 3.1, 3.2, 4.2) |
| `ADR-0093` | **`Propose` -> `Accepte`** : deux clauses dans l'arbre, cinq morsures sur cinq (section 4) |
| `ADR-0094` | **reste `Propose`** : quatre relais sur quatre dans l'arbre, **regle executable absente** (section 5) |

**Ce que cette confirmation ne fait pas** : elle ne prononce aucun Done pedagogique, ne ferme
aucune ligne de matrice, ne juge aucun contenu. La porte pedagogique est a `nadia-prof`.

**Ce que cette confirmation ne couvre pas, et je le nomme plutot que de le deduire** (amendement du
2026-09-08 a `CLAUDE.md`) : `mvn verify` **n'est que le job `build`** de
`.github/workflows/ci.yml`. J'ai lance en plus **le job `chemins` sur mon propre diff** (section
6). Les jobs de prefixes de commit et de juges purs des outils **ne sont pas lances par moi et je
ne les deduis pas**.

---

## 1. La porte technique, avec sa commande et sa methode

```
bash outils/mvn-jdk21.sh -B clean verify
```

lancee a la racine du worktree `s36-theo-porte`.

```
[INFO] recreo ............................................. SUCCESS [  0.829 s]
[INFO] recreo-domain ...................................... SUCCESS [ 15.273 s]
[INFO] recreo-application ................................. SUCCESS [  5.043 s]
[INFO] recreo-adapter-web ................................. SUCCESS [01:00 min]
[INFO] recreo-adapter-content ............................. SUCCESS [ 27.283 s]
[INFO] recreo-bootstrap ................................... SUCCESS [01:55 min]
[INFO] BUILD SUCCESS
```

**Compte de tests, avec sa methode dans le meme paragraphe** : somme de l'attribut `tests="..."`
des **201** rapports XML de surefire et de failsafe apres le `clean`, jamais la somme des lignes
`Tests run:` des rapports `.txt` (amendement s11). **1487 tests, 0 echec, 0 erreur, 0 ignore.** Le
chiffre concorde avec celui annonce par `sofia-sm` sur le meme arbre, obtenu par un autre passage.

---

## 2. Le compte des `ArchRule`, et la divergence de methode que j'ai moi-meme introduite

**Le chiffre : 33, inchange par s36.** Methode, dans le meme paragraphe : occurrences de
l'annotation `@ArchTest` dans `**/src/test/java/**/*.java` du seul worktree mesure - `target/`
exclu par construction (le motif ne vise que les arbres de sources), les worktrees des autres
agents exclus parce qu'ils sont freres de cet arbre et non dedans, le faux module de
contre-exemple exclu parce qu'il vit sous `src/test/resources`. C'est **exactement** la methode de
`s35/confirmation-archunit.md`, refaite et non reprise.

**La divergence, qui est de ma main.** `ADR-0093` et `ADR-0094` citent tous deux *"33 au
2026-09-11"* en ecrivant a cote, comme methode, *"motif `static final ArchRule` sur les arbres de
sources"*. **Ce n'est pas la methode qui produit 33.** Mesure aujourd'hui :

| Methode | Compte |
|---|---|
| `@ArchTest` dans `src/test/java` (methode de s35, celle qui produit le 33 cite) | **33** |
| `static final ArchRule`, `/target/` exclu | 36 |
| idem, moins les 2 declarations du faux module sous `src/test/resources` | **34** |

L'ecart de 1 est nomme : `ScanDeComposantsTest.aucuneClasseDeTestSurLeClasspathDeLApplicationNePorteDeStereotype`
declare bien une `ArchRule`, mais elle est evaluee par **deux methodes `@Test` ordinaires** qui
appellent `.check(...)` explicitement, et non par `@ArchTest`. Elle est dans l'arbre depuis s06
(`a5ea324f`, 2026-08-07) : l'ecart preexistait au 2026-09-11, il n'a pas ete introduit par s36.

**Le chiffre etait juste, la methode ecrite a cote de lui ne l'etait pas** - c'est precisement la
faute que ce depot m'a deja sanctionnee (*"tout chiffre que tu cites porte sa methode dans le meme
paragraphe"*), commise ici dans sa variante la plus discrete : une methode plausible recopiee de
memoire a cote d'un chiffre exact. Les deux `ADR` sont corriges dans le meme commit que cette
confirmation.

**s36 n'ajoute aucune `ArchRule`** : les objets que ses deux `ADR` jugent sont des documents HTML
rendus, des paquets de contenu charges, un gabarit et une feuille de style - aucun n'est un type.

---

## 3. Les deux preuves de morsure sur le plancher ArchUnit

Le vert de la section 1 ne prouve rien par lui-meme (amendement s06 : *"il est vert" ne suffit
jamais comme preuve de morsure*). Les deux mutations ci-dessous portent sur du **code de
production**, jamais sur une fixture ; les deux materiaux ont ete remis en etat et `git status`
etait propre avant les ecritures de ce commit.

### 3.1 Le contrat de contenu ne porte que du texte - et la mutation est celle qu'`ADR-0094` affirme

`ADR-0094` decision 2 affirme qu'un `boolean` aurait ete **refuse par un garde-fou deja dans
l'arbre**, et que c'est pour cela que le fait est porte par un enum. L'affirmation est verifiee
plutot que reprise.

Mutation : `domain/src/main/java/com/recreo/domain/content/Zone.java` recoit une composante
`boolean libelleHorsDeLaBoite`, avec un constructeur de commodite conservant l'arite - donc
**aucun site d'appel n'est retouche**, et rien d'autre que la regle ne peut rougir.

```
bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest='DifficulteTest' \
  -Dsurefire.failIfNoSpecifiedTests=false
```

Le `-am` est porte (`CLAUDE.md`, troisieme prerequis) : sans lui, `domain` serait resolu contre la
derniere version **installee** et la mesure decrirait un autre arbre.

**`Tests run: 3, Failures: 1`** - les deux autres regles de la classe restent vertes, la regle
discrimine donc au lieu de tout refuser :

```
Architecture Violation - Rule 'ADR-0006 : le contrat de contenu porte du texte, jamais des bornes
de matiere' was violated (1 times):
com.recreo.domain.content.Zone.libelleHorsDeLaBoite cite le type boolean, qui n'est ni du texte,
ni une collection du JDK, ni un type du contrat de contenu
```

### 3.2 Deux mecaniques ne se connaissent pas - et ce qu'elle interdit reellement

C'est la regle du plancher dont je me mefie le plus : elle serait **decorative** si chaque
mecanique etait une coquille vide deleguant a un moteur partage. Verification prealable sur
l'arbre : les trois sessions de mecanique portent chacune leur propre boucle de partie -
`DesignationSession` 268 lignes, `SaisieSession` 310, `BanditManchotSession` 326. La regle interdit
donc quelque chose de reel : la premiere reutilisation qu'un developpeur presse ecrirait.

Mutation, sur le fichier que ce sprint vient de toucher :
`DesignationSession.emplacementDuLibelleDeZoneManche()` reference
`com.recreo.domain.mechanics.saisie.SaisieSession`.

```
bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest='DecouplageMecaniqueContenuTest' \
  -Dsurefire.failIfNoSpecifiedTests=false
```

**`Tests run: 12, Failures: 1`** - les onze autres regles de la classe restent vertes :

```
Architecture Violation - Rule 'ADR-0004 : deux mecaniques ne dependent pas l'une de l'autre'
was violated (1 times):
Slice designation depends on Slice saisie:
Method <com.recreo.domain.mechanics.designation.DesignationSession
  .emplacementDuLibelleDeZoneManche()> references class object
  <com.recreo.domain.mechanics.saisie.SaisieSession>
```

**Une premiere tentative a echoue a la compilation** et le fait merite d'etre ecrit : la classe
visee d'abord (`NormalisationDeSaisie`) est de visibilite paquet, donc la frontiere etait deja
tenue par le langage a cet endroit-la. La regle ArchUnit n'est pas redondante pour autant - elle
tient aussi les types **publics** des mecaniques voisines, que le langage n'empeche pas de citer,
et c'est le cas que la seconde mutation a mesure.

---

## 4. `ADR-0093` passe `Accepte`

### 4.1 Les deux clauses sont dans l'arbre, dans les deux fichiers nommes

Verification faite sur `1d361786`, par lecture du diff `6a6eb12d..1d361786` et des fichiers.

| Condition ecrite dans l'`ADR` | Etat dans l'arbre |
|---|---|
| Clause 1 dans `ChaqueZoneDesignableEstUnBoutonDuFormulaireDeCoupTest` | **presente**, methode `nomAccessible(Element, Document)`, **ordre amende** : `aria-labelledby`, puis `aria-label`, puis texte |
| Clause 1 dans `LEcranDeMancheDuJeuDeDesignationReelEstServiTest` | **presente**, meme methode, meme ordre, dupliquee pour le motif ecrit (aucun `test-jar` ne publie l'une hors de son module) |
| Le grief nomme ce qu'il juge | **oui** : *"ne porte aucun nom accessible : ni texte, ni aria-label, ni aria-labelledby resolvant vers un texte non vide"* |
| Clause 2 dans `LEcranDeMancheDuJeuDeDesignationReelEstServiTest` **seulement** | **oui** ; le libelle est lu par la meme chaine de chargement que la production (`GameDefinition.contenu()`), pas par un second recensement |
| Comparaison par inclusion, espaces reduits et casse repliee | **oui**, `normalise(...)` puis `contains` |

**L'item juge est retrouve par son enonce et non par l'ordre de tirage** - une prudence que
l'implementation a ajoutee et que mon `ADR` n'avait pas demandee : le hasard fixe de la
configuration de test ne traverse pas la frontiere de module. C'est un durcissement, pas un
contournement.

### 4.2 Les cinq morsures dues sont la, et j'en refais une sur la source

| Morsure due | Ou elle est | Verdict |
|---|---|---|
| 1 - aucun des trois porteurs, rouge | `ChaqueZoneDesignable...Test.unBoutonSansAucunPorteurDeNomAccessibleFaitEchouerLeGardeFou` | presente |
| 2 - `aria-label` seul, vert sous le juge neuf, rouge sous l'ancien | les **deux** classes, avec l'assertion `bouton.text()` vide portant le verdict de l'ancien juge dans la meme methode | presente |
| 3 - `aria-labelledby` vers un identifiant absent, rouge | `ChaqueZoneDesignable...Test.unAriaLabelledbyVersUnIdentifiantAbsentFaitEchouerLeGardeFou` | presente |
| 4 - nom accessible qui ne porte pas le libelle declare, sur materiau reel | `LEcranDeManche...Test.unLibelleDeclareMuteEnMemoireFaitEchouerLeGardeFou` | presente |
| 5 - texte et `aria-label` differents, juge sur l'`aria-label` | `LEcranDeManche...Test.unAriaLabelRemplaceParUneAutreChaineFaitEchouerLeGardeFouAlorsQueLeTexteNAPasBouge`, qui asserte que le texte n'a pas bouge | presente |

**Troisieme preuve de morsure de cette confirmation, et elle repond a l'amendement s10** : les
morsures 4 et 5 mutent le document **en memoire** apres rendu. J'ai mute la **source de
production** a la place.

Mutation : dans `templates/fragments/designation.html`, l'attribut
`aria-label=... ? ${zone.libelle()} : null` devient `... ? ${zone.id()} : null`.

```
bash outils/mvn-jdk21.sh -B -pl bootstrap -am test \
  -Dtest='LEcranDeMancheDuJeuDeDesignationReelEstServiTest' \
  -Dsurefire.failIfNoSpecifiedTests=false
```

**`Tests run: 8, Failures: 3`**, sur le catalogue reel :

```
le bouton de zone <button type="submit" class="btn zone" data-role="action" name="zoneDesignee"
 value="tableau" ... aria-label="tableau" ...>1</button> a pour nom accessible "tableau", qui ne
 porte pas le libelle "en haut, au centre" que le paquet declare pour la zone "tableau"
 (ADR-0093 clause 2)
```

**Ce que cette mutation etablit en plus des morsures livrees** : la clause 2 est couplee au
**gabarit servi**, pas seulement a une mutation en memoire de sa propre production. Et le document
cite montre la forme reellement servie - un bouton dont le texte est `1` et dont le nom vient de
son `aria-label`.

**Une limite structurelle de la clause 2, que je nomme** : elle compare le document servi au
paquet charge. Muter le libelle **dans le YAML** ne peut donc pas la faire rougir - les deux cotes
bougent ensemble. Elle garde la coherence entre le rendu et le contenu, jamais la justesse du
contenu. C'est bien la propriete voulue, mais il faut savoir ce qu'elle ne voit pas.

### 4.3 Verdict

**`Accepte`.** Les deux clauses sont dans les deux fichiers nommes, les cinq morsures dues sont
ecrites, et la clause 2 mord sur une mutation de source de production.

**Ce que cet `Accepte` ne dit pas** : rien de la moitie de `ZON-15` qui compte pour l'enfant -
savoir quel bouton porte quel libelle. Un ecran ou les six libelles sont corrects, resolus, et
places de telle sorte que personne ne sache lequel va avec quel bouton passe cette regle.
`nadia-prof` reste le seul juge de cette moitie, et aucun Done pedagogique n'est prononce ici.

---

## 5. `ADR-0094` reste `Propose` - sa regle executable n'est pas dans l'arbre

### 5.1 Ce qui y est : les quatre relais, tous les quatre

| Relais (decision 4) | Etat dans l'arbre |
|---|---|
| 1. `DesignationContentSourceFactory` lit la cle, applique le defaut, refuse une valeur inconnue | **present** ; le message d'erreur nomme la cle et la valeur ; la valeur du paquet est posee sur chaque item |
| 2. `ZoneADesigner` recoit la composante, constructeur de commodite conservant l'arite (`ADR-0016`) | **present** ; aucun site de construction existant n'est retouche |
| 3. `DesignationSession.emplacementDuLibelleDeZoneManche()`, transport strict | **present** ; la mecanique ne lit pas la valeur et ne s'en sert pour aucun verdict |
| 4. `PartieController` convertit l'enum en un seul attribut booleen de modele | **present** ; l'enum n'entre pas dans le modele |

`EmplacementDuLibelleDeZone` est dans `domain/content` avec ses deux valeurs et sa Javadoc ;
`cp-plan-de-la-classe.yaml` declare `emplacementDuLibelleDeZone: hors-de-la-boite` ; `application`
n'est pas touche ; aucun port n'est ajoute. Sur les quatre relais, rien a redire.

### 5.2 Ce qui n'y est pas : le garde-fou de la decision 6

`bootstrap/src/test/java/com/recreo/archi/AucunGabaritNiFeuilleMaisonNeNommeUnContenuParticulierTest.java`
**n'existe pas dans l'arbre.** Methode : `git diff --stat 6a6eb12d 1d361786` porte 11 fichiers et
aucun test d'architecture ; la recherche du nom dans tout l'arbre ne le trouve que dans
`ADR-0094` et dans `ADR-index.md`, c'est-a-dire **uniquement la ou il est promis**. Ses quatre
morsures dues n'existent donc pas non plus.

C'est la seule chose qu'`ADR-0094` interdise a quelqu'un qui essaierait de mal faire. Les quatre
relais, eux, n'interdisent rien - ils transportent une valeur. **Un `ADR` dont seule la partie qui
n'interdit rien est implementee n'est pas un `ADR` accepte** : c'est la dette 1 de `CLAUDE.md`
- une abstraction documentee partout et absente du code - en train de se reformer, et mon propre
mandat me l'interdit explicitement.

**La propriete tient aujourd'hui, et ce n'est pas un motif d'accepter.** Balayage a la main sur cet
arbre, avec sa methode : identifiants derives du contenu servi (`id:` et `paquet:` de
`catalogue.yaml`, `id:` de chaque paquet, valeur `illustration:` de chaque item ; seuls les
identifiants de plus de trois caracteres retenus) = **101** ; fichiers du perimetre
(`templates/**/*.html`, `static/css/recreo.css`, `adapter-web/src/main/java/**/*.java`) = **17** ;
occurrences litterales hors commentaire = **0**. Mon extraction est plus grossiere que celle que
l'`ADR` prescrit (101 contre les 175 que la mesure du 2026-09-21 avait derives), donc ce balayage
est un **minorant** : il ne peut pas prouver l'absence, il peut seulement constater qu'il n'a rien
trouve. Une propriete constatee a la main un jour donne n'est pas une propriete gardee - c'est la
definition meme du souhait que ce depot refuse d'appeler une regle.

### 5.3 Un second ecart, plus petit, entre la decision et l'arbre

La decision 5 ecrit : *"`aria-label` porte **toujours** `zone.libelle()`, que le libelle soit dans
la boite ou non"*, et la consequence associee nomme un repli conditionnel **sous une condition
precise** - *"si `nadia-prof` refuse"* le fait que `frise-de-la-journee`, deja accorde, recoive un
attribut qu'il n'avait pas.

Le gabarit de l'arbre porte le repli :
`aria-label=${libelleDeZoneHorsDeLaBoite} ? ${zone.libelle()} : null`. **Aucun verdict de
`nadia-prof` sur ce point n'existe dans `squad/` pour s36** : le declencheur ecrit du repli n'est
pas survenu.

**Consequence technique : aucune.** Sur un paquet `dans-la-boite`, le nom accessible resolu retombe
sur le texte du bouton, qui est le libelle - la clause 2 d'`ADR-0093` reste verte, et je l'ai
verifiee sur le catalogue reel en section 4.2. **Consequence de gouvernance : reelle.** Le gabarit
et l'`ADR` disent deux choses differentes, et c'est l'`ADR` qui est cense faire foi. Je ne reecris
pas la decision 5 pour la faire coincider avec le code apres coup - ce serait le geste que ce
depot sanctionne. Je l'ecris comme un ecart a trancher quand `ADR-0094` reviendra en acceptation,
et le choix entre les deux formes reste ouvert.

### 5.4 Verdict

**`ADR-0094` reste `Propose`**, et son critere d'acceptation est inchange : il passera `Accepte`
quand le garde-fou de sa decision 6 sera dans l'arbre avec ses quatre morsures dues, et quand
l'ecart de la section 5.3 sera tranche. Ce n'est pas un rouge de porte technique - le reacteur est
vert et le lot de `lea-dev` fonctionne - c'est une decision d'architecture qui n'est pas encore
tenue par une regle.

---

## 6. Le job `chemins`, lance sur mon propre diff

```
bash .github/scripts/verifier-chemins.sh
```

alimente par les quatre fichiers de ce commit a la place de `git ls-files`. Sortie : *"Aucun chemin
de poste de travail ni nom de compte dans les fichiers verifies."*, **code de retour 0**. C'est le
geste qui manquait a s30 et qui a laisse le job `chemins` rouge un sprint entier derriere un
`mvn verify` vert.

---

## 7. Ce que cette confirmation laisse ouvert

1. **La regle executable d'`ADR-0094`** (section 5.2) - absente, l'`ADR` reste `Propose`. C'est la
   seule dette que ce sprint ouvre de mon cote.
2. **L'ecart de la decision 5 d'`ADR-0094`** (section 5.3) - `aria-label` conditionnel dans l'arbre
   contre `aria-label` inconditionnel dans l'`ADR`, sans le declencheur ecrit du repli.
3. **L'audit pedagogique de `cp-plan-de-la-classe` sous sa forme neuve** - numero dans le bouton,
   legende sous le dessin. Non fait ici, et un vert de CI n'en dit rien. A `nadia-prof`.
4. **Les jobs de CI autres que `build` et `chemins`** (section 0) - non lances par moi, non
   deduits.
5. **La divergence de methode sur le compte des `ArchRule`** (section 2) - corrigee dans les deux
   `ADR`, mais aucun automate ne verifie qu'un chiffre cite dans un `ADR` porte la methode qui le
   produit. Je ne propose pas de garde-fou pour cela aujourd'hui : je ne sais pas l'encoder sans
   ecrire une regle decorative.
