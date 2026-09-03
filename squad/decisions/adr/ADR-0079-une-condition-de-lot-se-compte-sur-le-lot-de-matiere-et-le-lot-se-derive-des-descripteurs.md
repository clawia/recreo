# ADR-0079 - Une condition de lot se compte sur le lot de matiere, et le lot se derive des descripteurs servis

- **Statut** : Accepte. **Amende le 2026-09-03, meme jour, sur un defaut vivant trouve par
  `yanis-qa`** : la cle de regroupement etait une chaine brute et **reintroduisait par elle-meme le
  vert a tort que cet `ADR` ferme**. Section *Amendement du 2026-09-03*.
- **Sa regle est ecrite, verte et mise a l'epreuve dans le meme commit** -
  `adapters/adapter-content/src/test/java/com/recreo/adapter/content/UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest.java`,
  **9 tests** : la clause sur les lots servis, un rapprochement de comptes avec `nadia-prof`, deux
  morsures mutees en memoire sur du materiau reel, **une borne qui exige un vert**, la non-vacuite,
  et une morsure sur le jeu fautif commite.
- **Sprint** : s27 (planning). **Date** : 2026-09-03. **Auteur** : `theo-archi`.
- **Arbre mesure** : worktree `s27-theo`, sur l'arbre fusionne de `sofia-sm` a `e3cef81` - les
  paquets de `lea-dev` et de `marc-dev` y sont, et cette regle n'a aucun sens sans eux. **JDK
  21.0.10**, celui que la machine virtuelle des tests rapporte d'elle-meme (`ADR-0071`). Enveloppe :
  `bash outils/mvn-jdk21.sh`.
- **Encode une decision de `nadia-prof` du 2026-09-03 ; il n'en propose pas une variante.** Elle
  fixe son unite d'audit et route la consequence technique dans la meme phrase.
- **Prolonge** : `ADR-0013` (perimetre derive, garde-fou mis a l'epreuve), `ADR-0051` (un garde-fou
  qui n'examine aucun objet le declare), `ADR-0077` (un garde-fou execute la clause qu'il cite, sur
  la population que sa lettre designe).
- **Ne decide aucun contenu, aucune pedagogie.** Il ne dit pas si un item est de la restitution ; il
  dit sur quelle population le compte se fait.

---

## Contexte

### Le fait, et il est de `nadia-prof`

Le 2026-09-03 elle a mesure l'extension de sa propre borne d'audit sur l'objet, ce qu'elle n'avait
jamais fait :

> *"Un paquet ne dit pas s'il s'agit d'un fichier ou d'une charge d'audit. A s26 les cinq paquets
> etaient cinq fichiers, la question ne se posait pas, et j'ai pris pour une definition ce qui
> n'etait qu'une coincidence. C'est la meme famille de defaut que `S1-8` et que `FIG-3` hier.
> Troisieme fois en deux jours, et cette fois c'est une clause qui gouverne mon propre travail."*

Elle reprend ses trois couts de matiere neuve et constate qu'**aucune de ses conditions ne se compte
par fichier** : elles sont soit par item, soit **par lot de matiere**. Sa borne devient donc *"le lot
de matiere - jusqu'a 20 items d'une meme matiere et d'un meme niveau"*, et elle route la consequence
technique :

> *"Si un garde-fou verifie `HIS-1` ou `BIA-2`, il doit le faire sur le lot de matiere, pas sur un
> fichier. Le test de `marc-dev` est juste aujourd'hui parce que l'histoire tient en un seul
> fichier ; `BIA-2` sur questionner le monde porte sur les trois fichiers ensemble, et un test qui
> l'evaluerait fichier par fichier serait **vert a tort**."*

### Ou etait exactement le vert a tort

`PaquetCm1HistoireGeographieTest` designait sa population par **un chemin de ressource ecrit en dur**
(`/contenu/cm1-histoire-geographie.yaml`) et son plafond par **une constante egale au tiers de
vingt** (`HIS_1_PLAFOND_RESTITUTION = 6`).

Un second fichier d'histoire-geographie CM1 n'aurait ete juge par **rien** : pas refuse, pas compte,
**simplement invisible**. Le tiers du lot aurait pu passer a la moitie sans qu'une seule assertion
bouge, et le plafond ecrit en dur aurait continue d'afficher *"six sur vingt"* sur un lot de trente.

**Ce n'est pas un defaut du travail de `marc-dev`.** Sa clause etait exacte sur l'arbre du jour, son
marqueur est bon et il est conserve sans un caractere de changement. C'est **un perimetre enumere** -
juste tant que le monde ressemble a l'enumeration - c'est-a-dire la meme famille que `FIG-4` etendue
hors de son paquet la veille (`ADR-0077`), a l'envers : la, une clause debordait sa population ; ici,
une population deborde sa clause.

---

## Decision

1. **Une condition qui parle d'un lot se compte sur le lot**, jamais sur le fichier qui en porte une
   part.
2. **Le lot est le couple (matiere, niveau) que chaque paquet declare dans son descripteur.** Aucune
   matiere n'est ecrite dans la regle ; les paquets viennent du repertoire de contenu servi, jamais
   d'une liste.
3. **Le fait qu'un lot soit soumis a une condition de lot est lui aussi derive** : un lot y est
   soumis des qu'**un** de ses items porte le marqueur de cette condition. Personne n'a a declarer
   quelque part que l'histoire est soumise a `HIS-1` - c'est le contenu qui le dit.
4. **Dans un lot soumis, chaque item de chaque fichier porte un marqueur.** C'est la clause qui ferme
   le vert a tort : un fichier entier qui rejoint le lot sans marqueur est **rouge**, et nomme.
5. **Le plafond n'est pas une constante, c'est un calcul sur la taille du lot** - le tiers, arrondi
   inferieur. Une constante egale au tiers d'une taille donnee pourrit des que le lot grandit.
6. **`HIS-1` cesse d'etre gardee par fichier** dans `PaquetCm1HistoireGeographieTest`, et le motif du
   deplacement est ecrit a l'endroit qu'elle occupait. Deux garde-fous qui calculent la meme chose
   sur deux populations divergent un jour ; il n'y en a qu'un.

### Ce que la derivation vaut, mesure et non suppose

Sur l'arbre fusionne du 2026-09-03, la derivation retrouve les comptes de `nadia-prof` **par un autre
chemin que le sien** - elle a lu les fichiers annonces, ce recensement lit les descripteurs servis :

| Lot derive | Fichiers | Items |
|---|---|---|
| Questionner le monde / CP | **3** | **24** |
| Histoire-geographie / CM1 | **1** | **20** |

Ce sont les nombres de sa section 2.3, obtenus sans qu'aucun fichier ne soit nomme dans le test.
**Un compte qui concorde par deux chemins vaut mieux qu'un compte recopie**, et c'est le seul moyen
que j'aie de verifier que ma notion de *lot* est bien la sienne plutot qu'une notion voisine qui lui
ressemblerait sur l'arbre du jour.

---

## Regle executable

**Ce n'est pas une `ArchRule`** : la propriete porte sur des ressources servies, pas sur des types.
C'est le regime de garde-fou de balayage d'`ADR-0013`.

```java
for (Map.Entry<String, List<Paquet>> lot : lotsDeMatiere(paquets).entrySet()) {
    long marques = lot.getValue().stream().mapToLong(Paquet::marques).sum();
    if (marques == 0) {
        continue;
    }
    lotsSoumis++;
    int itemsDuLot = lot.getValue().stream().mapToInt(Paquet::items).sum();
    long restitutions = lot.getValue().stream().mapToLong(Paquet::restitutions).sum();

    for (Paquet paquet : lot.getValue()) {
        if (paquet.marques() < paquet.items()) {
            fautes.add(paquet.fichier() + " : ... ne portent pas de marqueur ... (ADR-0079)");
        }
    }

    long plafond = itemsDuLot / PART_DE_RESTITUTION;
    if (restitutions > plafond) {
        fautes.add(lot.getKey() + " : ... au-dessus du tiers du LOT ... (ADR-0079)");
    }
}
```

| Test | Ce qu'il mute | Verdict |
|---|---|---|
| `unFichierQuiRejointUnLotSoumisSansMarqueurFaitEchouerLeGardeFou` | **le vert a tort, reproduit** : un second fichier rejoint le lot sans un seul marqueur | rouge, et il **nomme le fichier** |
| `unLotQuiDepasseLeTiersDeRestitutionFaitEchouerLeGardeFou` | le lot reel depasse sa part d'un item | rouge, *au-dessus du tiers du LOT* |
| `leMemeLotRepartiSurDeuxFichiersResteVertQuandLeLotTientSonTiers` | le lot reel reparti sur deux fichiers, **toutes ses restitutions dans le plus petit** | **vert** |
| `lesLotsDeMatiereServisSontCeuxQueNadiaProfACompte` | rien - il rapproche deux comptes | vert, 3/24 et 1/20 |
| `unContenuSansAucunLotSoumisFaitEchouerLeGardeFou` | population vide | rouge, `ADR-0051` |
| `leJeuFautifCommiteEstRefuseSurLeFichierQuiRejointLeLotSansMarqueur` | deux fichiers d'un meme lot, l'un marque et l'autre pas | rouge sur le fautif, et il **ne nomme pas** le sain |

**La troisieme ligne est celle qui prouve la moitie que les morsures ne prouvent pas.** Une lecture
par fichier y serait **rouge a tort** - le petit fichier depasse largement son propre tiers - alors
que le lot tient le sien. Sans elle, la regle pourrait n'etre qu'une regle plus severe ; avec elle,
elle est bien une regle qui a **change de population**.

---

## Consequences

### Ce que la regle interdit reellement

- Ajouter un fichier a un lot soumis sans classer ses items : **rouge**, et le fichier est nomme.
  C'est le geste que la version par fichier ne voyait pas.
- Faire passer un lot au-dessus du tiers en repartissant ses items sur plusieurs fichiers : **rouge**,
  parce que le compte est sur le lot.
- Laisser un plafond ecrit en dur pourrir quand le lot grandit : impossible, il n'y a plus de plafond
  ecrit, seulement un diviseur.

### `BIA-2` n'est pas gardee ici, et c'est delibere

`nadia-prof` route `BIA-2` avec `HIS-1`, et *"un pays n'est jamais represente par un seul trait"* est
exactement une condition de lot. **Je ne l'encode pas aujourd'hui, et le motif est le contraire d'une
economie** : `BIA-2` ne porte que sur les lignes *ici et ailleurs* (QLM 16) et *aspects culturels*
(anglais CP 6, CM1 7), qui ne sont **servies par aucun paquet** - la matrice les donne non couvertes,
et aucun item servi ne nomme un pays.

Une clause verte sur une population vide est ce qu'`ADR-0051` refuse. Ici elle serait **pire que
decorative** : elle donnerait a lire, dans une suite verte, que le biais culturel est garde. **Le
mecanisme de lot est ecrit pour qu'elle s'y branche** le jour ou sa ligne entre - le marqueur se
declarera de la meme facon, et la clause 3 la fera prendre effet sans qu'on ait a l'inscrire nulle
part. **C'est ce jour-la qu'elle sera ecrite, avec la population que le contenu lui donnera.**

### Ce que la regle ne juge pas

**Si un item *est* de la restitution.** Le marqueur est pose a la main par l'auteur du paquet, et une
marque menteuse passe : un item de date marque *comprehension* est vert. Ce qui est tenu est que
**chaque item du lot en porte une** - donc qu'aucun item, et surtout aucun fichier entier, n'echappe
au classement - et que **le compte se fasse sur le lot**. Le sens de chaque marque reste a
`nadia-prof`, en jouant, et aucun automate ne le prendra.

### Ce qui me ferait changer d'avis

Si deux lots distincts devaient un jour partager un couple (matiere, niveau) - deux themes
d'histoire-geographie CM1 audites separement - le groupement serait trop grossier et il faudrait que
le paquet declare son lot. **Je ne l'ecris pas aujourd'hui** : `nadia-prof` definit son unite par la
matiere et le niveau, pas par le theme, et une cle de plus dans le schema pour un decoupage qu'elle
n'a pas demande serait une abstraction pour un usage imagine.

---

## Alternatives considerees

**Laisser la clause par fichier et en ajouter une par nouveau fichier.** Ecartee : c'est
l'enumeration qui a produit le defaut, et elle demande qu'on pense a l'etendre - le seul filet etant
qu'un relecteur y pense, ce qui n'a jamais suffi sur ce depot.

**Deriver le lot du prefixe du nom de fichier** (`cp-qlm-*`). Ecartee : une convention de nommage que
rien ne tient, et qui aurait fait de `cp-voyelle-du-son` et de `cp-qlm-corps-sens` deux lots par
accident de prefixe plutot que par matiere declaree. Le descripteur dit deja la matiere.

**Deriver le lot du catalogue** (les entrees qui servent chaque paquet). Ecartee : un paquet retire du
catalogue mais laisse sur disque cesserait d'etre juge, ce qu'`ADR-0073` refuse deja dans cette
famille de tests.

**Faire declarer par chaque paquet les conditions de lot auxquelles il est soumis.** Ecartee : c'est
une cle de plus dans le schema, donc une chose de plus a oublier, et elle rendrait possible qu'un
paquet d'histoire se declare non soumis a `HIS-1`. Le marqueur porte deja l'information, et un lot
soumis se reconnait a ce que ses items sont classes.

---

## Amendement du 2026-09-03 - la cle qui calcule la population rouvrait la porte a cote

**`yanis-qa` a trouve un defaut vivant dans cette regle, reproduit sur une machine virtuelle puis
entierement defait**, et il l'a route hors de son mandat parce que c'est une decision
d'architecture. Il a raison sur les deux moities, et la seconde est la plus grave.

> *"La cle de regroupement est `matiere + " / " + niveau`, chaine brute, sans normalisation de casse,
> d'accent ni d'espace. Elle scinde un lot reel en deux groupes des qu'un descripteur differe d'un
> espace final."*

Sa preuve : un paquet en memoire avec `niveau = "CM1 "` produit **deux** cles distinctes, et
`verifier(paquets)` **ne leve rien**.

> *"Le lot du fichier synthetique a `marques == 0`, donc la boucle `continue` **avant de l'avoir
> jamais verifie**."*

**C'est mot pour mot le defaut que cet `ADR` pretend fermer** - *"un second fichier du meme lot
n'aurait ete juge par rien"* - **reintroduit par la cle qui calcule la population**. J'avais change
la population et laisse la porte a cote. Le mode de defaillance n'est pas l'echec, c'est le silence :
un lot scinde dont la moitie ne porte aucun marqueur n'est ni refuse, ni compte, invisible.

**Latent aujourd'hui, vivant demain** : les quatre fichiers reels declarent des descripteurs
identiques, donc rien ne rougissait. Ces descripteurs sont du YAML ecrit a la main.

### Ce qui change

1. **La cle est normalisee** : accents deposes, blancs ramenes a un seul et rognes, casse repliee.
   **La famille est traitee, pas le cas mesure** - l'espace final est celui qu'il a reproduit,
   l'espace initial, l'espace double, la casse et l'accent cassaient la cle exactement pareil, et
   *une famille mesuree sur un seul cas est un incident, pas un perimetre* est ma propre phrase
   d'`ADR-0077`.
2. **Une clause de plus : deux ecritures d'un meme lot sont une faute nommee.** La normalisation
   reunit les fichiers, mais elle ne doit pas **absorber en silence** un defaut de donnee. Le message
   nomme les deux ecritures et les fichiers.
3. **La forme declaree est ce qui s'affiche dans les messages**, la forme normalisee ne sert qu'a
   grouper : personne n'a a lire `histoire-geographie / cm1` dans un rapport d'echec.

### La question posee, et la reponse : oui, le saut est voulu, et voici ce qui le rend sur

`ADR-0079` clause 3 pose que la soumission a une condition de lot est **derivee du marqueur**. Un lot
dont **aucun** item ne porte de marqueur n'est donc pas soumis. **C'est correct, et
`Questionner le monde / CP` en est la preuve** : le compter contre un tiers de restitution n'aurait
aucun sens, et l'exiger reviendrait a **nommer les matieres soumises**, c'est-a-dire a enumerer ce
que la regle derive.

**Ce qui rendait cette branche dangereuse n'etait pas sa definition, c'etait ce qui pouvait y
tomber.** Le cas redoutable n'est pas *un lot sans marqueur*, c'est *un fichier qui aurait du
rejoindre un lot soumis et qui ne l'a pas fait*. La cle normalisee ferme **toutes** les versions ou
le descripteur ecrit le meme lot autrement. Et le saut cesse d'etre un `continue` muet : il est
epingle par `unLotDontAucunItemNePorteDeMarqueurNEstPasSoumisEtCEstVoulu`, un test vert qui **enonce
la decision** au lieu de la laisser vivre dans une ligne de code.

**Reste une version qu'aucune comparaison de chaines ne peut attraper, et je la nomme plutot que de
la laisser trouver** : un paquet qui declare une matiere franchement differente - `"Histoire"` la ou
les autres disent `"Histoire-géographie"` - forme son propre lot. Aucun automate ne sait que la
premiere aurait du s'ecrire comme la seconde sans un vocabulaire ecrit a la main, ce qui serait un
critere enumere sous une promesse universelle. **Celui-la est vu par `nadia-prof` et par la matrice**,
qui comptent des lignes de referentiel et non des chaines.

### Les morsures, et la preuve par retrait

- `aucuneDivergenceDeCasseDAccentOuDEspaceNeScindeUnLot` construit **toutes** les ecritures fautives
  d'un descripteur reel, **ecarte celles qui ne different pas reellement** de la forme servie, et
  **refuse de prouver quoi que ce soit s'il en reste moins de quatre** - une precaution qui vient de
  s'averer utile : ma premiere ecriture comparait `toUpperCase("CM1")` a `"CM1"`, c'est-a-dire une
  iteration qui ne mutait rien. Chaque ecriture doit tenir **deux** choses : le lot **reste un seul
  lot**, et la divergence **est nommee**.
- *Preuve par retrait, mesuree et non affirmee : la cle remise en concatenation brute, la suite rend
  **2 echecs sur 9** ; normalisee, **9 / 0**.*

### La meme famille, balayee chez le voisin plutot qu'attendue

`AucunTitreDeJeuNePorteSonNiveauTest` (`ADR-0078`) lisait le meme champ et le comparait au titre
**sans le rogner**. Le meme `"CM1 "` y aurait fait chercher `"cm1 "` dans un titre, ne l'aurait pas
trouve, et **un titre fautif serait passe** - le meme silence, dans l'autre garde-fou, non mesure par
personne. Le niveau y est desormais rogne, avec sa morsure
(`unEspaceFinalDansLeNiveauDeclareNEmpechePasLaDetection`) et *sa preuve par retrait : sans le
rognage, **1 echec sur 7** ; avec, **7 / 0**.* **Une normalisation posee a un seul endroit laisse le
meme YAML casser le second garde-fou** ; c'est pour cela qu'elle est posee aux deux.

### Ce que je retiens contre moi

Ce defaut n'est pas le meme que celui d'`ADR-0077` - la je predisais un chiffre sans le mesurer, ici
j'ai ecrit une regle correcte sur une cle qui ne l'etait pas. **Le point commun est ailleurs** : dans
les deux cas j'ai verifie **le verdict** de la regle sur le materiau du jour, et jamais **l'objet
intermediaire** par lequel elle passe - le texte produit par une mutation la, la cle de regroupement
ici. Les quatre descripteurs reels etant identiques, aucune de mes six morsures ne pouvait voir la
cle. **Une regle qui derive sa population derive aussi ses erreurs de la fonction qui la calcule, et
cette fonction merite le meme traitement que la regle elle-meme.**
