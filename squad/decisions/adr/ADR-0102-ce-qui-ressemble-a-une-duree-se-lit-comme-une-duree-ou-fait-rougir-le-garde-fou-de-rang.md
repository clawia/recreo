# ADR-0102 - Ce qui ressemble a une duree se lit comme une duree, ou fait rougir le garde-fou de rang

- **Statut** : Accepte
- **Date** : 2026-09-25
- **Sprint** : s43
- **Auteur** : `theo-archi`
- **Route par** : mon propre avis de faisabilite de s43
  (`squad/sprints/s43-trois-lignes-qlm-eps-et-duree-ce1/avis-faisabilite.md`, section 5.2), et par
  `DUR-CE1-3` et `DUR-CE1-5` (`nadia-prof`,
  `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md`, section 5.3), qui nomment
  les deux ecritures de duree d'un paquet de `MAT-CE1-12` et la repartition des trois rangs
- **Arbre mesure** : worktree `s43-theo-adr`, branche partie de `round-two` a `5bd99d74`. JDK
  **21.0.10**, version que la machine virtuelle des tests rapporte d'elle-meme (`ADR-0071`), Maven
  3.9.9. Toute mesure partielle du reacteur porte `-am` et nomme son module
- **Prolonge** : `ADR-0041` (la lecture d'une valeur et sa clause du reste), `ADR-0068` (une lecture
  qui laisse un chiffre derriere elle rend `null`), `ADR-0039` (polarite : exiger plutot que
  dispenser), `ADR-0051` (un garde-fou dit combien d'objets il examine), `ADR-0013` (mise a
  l'epreuve)
- **N'ecrit aucun contenu, ne prononce aucun Done, ne touche a aucun paquet servi.**

---

## Contexte

### 1. Le defaut, mesure avant d'etre ecrit

`DUR-CE1-3` nomme **deux** ecritures de duree pour un paquet de `MAT-CE1-12` : *"2 heures et
demie"* ou *"2 h 30 min"*. Le lecteur de valeurs de
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` essaie dans l'ordre un motif de duree en
heures, un motif de duree en minutes, une somme de termes, puis un nombre nu. Execute sur les deux
ecritures, il rendait :

| Ecriture | Lecture | Valeur |
|---|---|---|
| `2 h 30 min` | motif de duree | **150** |
| `2 heures 30 minutes` | motif de duree | **150** |
| `14 h 30` (un instant) | motif de duree, en minutes depuis minuit | **870** |
| **`2 heures et demie`** | le motif echoue ; le nombre nu est lu, le reste *"heures et demie"* ne porte aucun chiffre et passe | **2** |
| **`1 heure et demie`** | idem | **1** |

**La forme `2 h 30 min` n'a jamais ete en defaut** ; le defaut porte sur la forme en toutes lettres,
et il est silencieux.

### 2. Pourquoi ce silence compte, et ce qu'il rendrait faux

Un item dont les trois propositions sont *"1 heure"* (60), *"1 heure et demie"* (lue **1**) et
*"2 heures"* (120) a une bonne reponse que l'enfant voit **au milieu** et que le garde-fou classe
**la plus petite**. Les valeurs ne coincident pas, donc ni
`aucuneValeurDeProductionNeCoincideAvecUneAutreDansLeMemeItem` ni
`AucunePropositionNEstEgaleAUneAutreDansUnItemTest` ne mordent : l'item reste dans le domaine avec un
rang faux.

**Ce qui en depend directement** : `SEUIL_MINIMUM = 0.10` et `SEUIL_MAJORITE = 0.5`, qui sont les
nombres memes de `DUR-CE1-5` - *"chacun des trois rangs de la bonne reponse porte au moins 10 % et au
plus 50 % des items"*. La clause pedagogique est donc **deja encodee**, mais sur un paquet ecrit *"et
demie"* elle aurait ete **verte sur une repartition qui n'est pas celle qui est servie**. Un
garde-fou qui passe sans rien prouver est le defaut que ce depot sanctionne le plus souvent, et il
etait ici de la main de l'architecte.

### 3. Pourquoi allonger le motif ne suffit pas

Ajouter *"et demie"* au motif corrige **ce** cas et laisse le **mode de defaillance** intact : la
prochaine ecriture non prevue - *"et quart"*, *"une heure et demie"* en toutes lettres, *"90 min"*
mal orthographie - retombera en silence sur le nombre nu. La correction seule serait une reparation
sans garde-fou.

## Decision

1. **Lire la demi-heure ecrite en toutes lettres.** `DUREE_ET_DEMIE` lit
   `<n> heure(s) et demi(e)` en minutes. **Et rien d'autre** : le quart d'heure n'est pas ajoute -
   `DUR-CE1-2` borne les minutes a `00` et `30`, aucun contenu servi ne l'ecrit, et un motif ajoute
   d'avance garderait une population vide.
2. **Inverser la polarite du silence** (`ADR-0039`). Toute proposition servie qui **ressemble** a une
   duree ou a un instant - un nombre suivi d'une unite de temps - et que la lecture de duree ne
   reconnait pas fait **rougir** le garde-fou, avec la valeur qu'elle aurait prise. Le critere de
   ressemblance est deliberement **plus large** que les motifs de lecture : c'est l'ecart entre les
   deux qui est l'objet garde.
3. **Ne pas exempter, ne pas ouvrir de dette** : la population est verte aujourd'hui, une exemption
   n'aurait rien a nommer.

## Regle executable ArchUnit

**Ce n'est pas une `ArchRule`** : la valeur que porte le texte d'une proposition YAML n'est pas une
propriete de bytecode. Le compte de regles ArchUnit du depot est inchange. Le mecanisme automatique
est une clause du garde-fou de rang, lancee par `mvn verify`.

**Fichier** :
`adapters/adapter-content/src/test/java/com/recreo/adapter/content/LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java`

```java
private static final Pattern DUREE_ET_DEMIE =
        Pattern.compile("^(\\d+)\\s*h(?:eures?)?\\s+et\\s+demie?\\.?$");

private static final Pattern RESSEMBLE_A_UNE_DUREE =
        Pattern.compile("^\\d+\\s*(?:h|heures?|min|minutes?)\\b.*", Pattern.CASE_INSENSITIVE);
```

et la clause :

```java
static void verifierQuAucuneEcritureDeDureeNEchappeALaLecture(Path racine, String racineClasspath)
```

Les cinq tests, et ce que chacun interdit reellement :

| Test | Ce qu'il rend rouge |
|---|---|
| `aucuneEcritureDeDureeDeProductionNEchappeALaLecture` | une proposition servie qui ecrit une duree ou un instant que la lecture ne reconnait pas - donc lue comme un nombre nu et comparee a des minutes |
| `lesDeuxEcrituresDeDureeSeLisentEnMinutes` | une regression sur l'une des sept ecritures servies ou arbitrees, dont les deux de `DUR-CE1-3` |
| `laDemiHeureEstOrdonneeEntreLesDeuxHeuresQuiLEncadrent` | l'**ordre** lu, qui est le seul objet dont le taux de rang depend : une heure et demie tombe entre une heure et deux heures, et non sous les deux |
| `uneEcritureDeDureeInconnueDeLaLectureEstRouge` | la mise a l'epreuve (`ADR-0013` obligation 3), sur une ecriture **deliberement** non ajoutee aux motifs - le quart d'heure : elle ressemble a une duree, la lecture ne la connait pas, elle vaudrait 2 |
| `laClauseDeLectureDesDureesExamineUnePopulationReelle` | une population vide, qui rendrait la clause verte sans avoir rien lu (`ADR-0051`) |

**Population, mesuree le 2026-09-25 sur le contenu servi** : **4 859** propositions, dont **72**
entrent dans le critere de ressemblance - les instants et les durees de `cm1-problemes-durees` - et
**zero** echappe a la lecture. La clause s'execute donc sur du materiau reel des son premier commit,
et elle y est verte. Le compte de 72 est rejoue par le test, qui refuse une population inferieure a
50.

**Verification de la correction** : `mvn -B -pl adapters/adapter-content -am test` sur ce garde-fou,
28 tests, 0 echec. `valeurDe("2 heures et demie")` rend **150** la ou il rendait 2 ;
`valeurDe("1 heure et demie")` rend **90** la ou il rendait 1 ; `valeurDe("2 h 30 min")` rend
**150**, comme avant ce commit.

## Consequences

**Bonnes.**

- Les deux ecritures que `DUR-CE1-3` autorise sont desormais lisibles, donc le paquet de
  `S43-MAT-DUREE` peut choisir l'une ou l'autre sans que la clause de rang de `DUR-CE1-5` mesure
  autre chose que ce qui est servi.
- La prochaine ecriture de duree non prevue arrivera comme un **echec nomme**, avec la valeur fautive
  dans le message, au lieu d'un chiffre faux dans un taux.

**Mauvaises, et je les ecris.**

- **Le critere de ressemblance peut produire un faux rouge** sur une proposition qui commencerait par
  un nombre suivi de `h` ou de `min` sans etre une duree. Aucune n'existe dans le contenu servi, et
  le prix d'un tel faux rouge est une ligne de motif ou une reecriture de la proposition - pas un
  chiffre faux.
- **La lecture reste une enumeration de motifs.** Elle en compte desormais quatre au lieu de trois,
  et la clause ne la rend pas complete : elle rend son incompletude **bruyante**.
- **Le quart d'heure est refuse** tant que personne n'en a besoin. Le jour ou une condition
  pedagogique l'autorise, c'est une ligne de motif et une ligne de test, dans le diff qui le demande.

## Alternatives considerees

1. **Ne rien changer et recommander l'ecriture `2 h 30 min`** dans l'avis de faisabilite. Ecartee :
   une recommandation n'est pas une regle, `DUR-CE1-3` autorise l'autre ecriture, et le mode de
   defaillance serait reste silencieux.
2. **Ajouter le motif *"et demie"* seul, sans la clause.** Ecartee : c'est la reparation d'un cas
   sans garde-fou sur la famille - la prochaine ecriture retomberait en silence sur le nombre nu.
3. **Rendre `null` sur toute proposition qui ressemble a une duree sans etre lue**, pour la sortir du
   domaine au lieu de rougir. Ecartee : c'est exactement l'exclusion silencieuse qu'`ADR-0051`
   combat ; l'item sortirait du calcul de rang sans qu'aucune ligne ne le dise.
4. **Ecrire un lecteur de durees generique** (analyse lexicale du francais des durees). Ecartee :
   beaucoup de pieces mobiles pour deux ecritures reellement servies. **Ce qui me ferait changer
   d'avis** : une quatrieme ou cinquieme ecriture arbitree, qui ferait de l'enumeration de motifs le
   mauvais outil.
