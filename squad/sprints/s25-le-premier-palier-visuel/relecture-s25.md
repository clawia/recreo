# Relecture s25 - le premier palier visuel

- **Auteur** : `relecteur-pedagogique`
- **Date** : 2026-08-31
- **Arbre relu** : `s25-sofia-review`, pointe `16b80f6`, lignee verifiee par `git merge-base --is-ancestor`
- **Worktree** : dedie, branche `s25-relecteur`. Le checkout principal n'a pas ete touche.
- **Place dans la ceremonie** : appele au tour du planning, avant la review. C'est la correction de
  l'ecart de conduite de s24, ou `yanis-qa` et moi avions ete lances apres.

---

## 1. La mesure, refaite par moi

> **BUILD SUCCESS**, cinq modules, **1 001 tests, 0 echec, 0 erreur, 0 ignore**, sur un JDK qui
> **rapporte 21.0.10**.

**Perimetre** : `mvn -B clean verify` a la racine, reacteur complet, donc aucun `-pl` et aucune
question de `-am`. **Methode du comptage** : somme des lignes de resultat de module (`surefire` et
`failsafe`), jamais des lignes par classe - 80 + 13 + 359 + 398 + 145 + 6.

**La version est celle que la machine virtuelle des tests rapporte d'elle-meme**, lue dans le journal
de Maven (*"Found matching toolchain for type jdk"*) puis confirmee par un `java -version` sur le JDK
ainsi retenu. C'est la forme qu'`ADR-0071` exige, et non une declaration reprise sur parole. Le
chiffre de `sofia-sm` est reproduit a l'identique, sur un autre worktree et une autre execution.

**Ma correction de la section 2 ne deplace pas ce chiffre** : mesure refaite apres, meme resultat.

### 1.1 La commande du depot a refuse de me laisser mesurer, et il faut regarder pourquoi

`bash outils/mvn-jdk21.sh -B clean verify` **s'est arretee sur moi aussi**, avec le meme constat que
sur `sofia-sm` : *"la toolchain jdk 21 designe un JDK qui rapporte 25.0.2"*. Le lot F fonctionne.

Mais `bash outils/verifier-jdk.sh` imprime, dans son propre diagnostic, **que le fichier de
toolchains qu'il lit n'est pas celui que Maven lit** - il l'ecrit mot pour mot : *"toolchains lu par
Maven : oui (un autre que celui lu par ce script)"*. Sur ce poste, `$HOME` et le repertoire
utilisateur que la machine virtuelle rapporte (`user.home`) ne designent pas le meme repertoire, donc
`~/.m2/toolchains.xml` n'est pas le meme fichier pour l'enveloppe et pour Maven. **Les deux fichiers
divergent** : celui que l'enveloppe lit declare une toolchain `21` sur un JDK qui rapporte 25.0.2 ;
celui que Maven lit en declare une sur un JDK qui rapporte 21.0.10, et c'est celui-la qui a execute
les 1 001 tests.

**C'est la cause n° 2 que le message du script enumere lui-meme**, et elle produit ici un **faux
rouge** : l'enveloppe refuse une mesure que Maven aurait executee sur le bon JDK. Ce n'est pas la
famille dangereuse - un faux rouge se voit - mais il bloque la commande du depot sur un poste dont la
toolchain effective est correcte, et son verdict ne distingue pas laquelle des deux causes est
active alors que son diagnostic, lui, le sait.

**Je ne tranche pas** : c'est technique. **Route a `theo-archi`**, avec la remarque que le fait est
deja dans le journal du script et qu'il ne manque que de remonter dans le verdict.

---

## 2. Ce que j'ai corrige

### 2.1 Coherence terminologique : "lecture d'ecran" alignee sur "lecteur d'ecran"

**11 occurrences dans 9 fichiers**, toutes introduites par le lot d'`ADR-0073`.

Le depot porte **127 occurrences de "lecteur d'ecran"**, dont `ADR-0035`, `F11` et la section 9.1 du
document d'`ines-design` de ce sprint meme, qui ecrit *"un canal reserve aux lecteurs d'ecran"* trois
lignes avant d'employer l'autre forme. Le terme etabli est aussi le terme juste : c'est le
**dispositif** qui annonce, et *"une image qui ne parvient pas a une lecture d'ecran"* n'est pas du
francais tenable.

| Fichier | Portee |
|---|---|
| `domain/.../content/Question.java` | Javadoc de la clause 5 - **code de production** |
| `domain/.../banditmanchot/BanditManchotSession.java` | Javadoc de l'accesseur - **code de production** |
| `domain/.../banditmanchot/BanditManchotTest.java` | Javadoc de test |
| `adapters/adapter-content/.../AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest.java` | libelle de motif |
| `adapters/adapter-content/.../PaquetCpFiguresPlanesTest.java` | message `.as(...)` |
| `bootstrap/.../archi/LIllustrationDeContenuResoutEtAucuneNeDortTest.java` | 3 occurrences - Javadoc et messages de diagnostic |
| `bootstrap/.../rendu/LIllustrationDUnItemServiAtteintLEcranDeMancheTest.java` | message de diagnostic |
| `ADR-0073` | corps de la decision |
| `review.md` de s25 | section sur le regime des textes alternatifs |

**Verifie avant de toucher** : aucune de ces chaines n'est comparee par une assertion - ce sont des
Javadoc, des libelles `.as(...)` et des messages de faute. **Verifie apres** : `mvn -B clean verify`
rend 1 001 tests, 0 echec. Le diff fait 11 lignes pour 11 occurrences, et rien d'autre.

**Pourquoi je l'ai corrige dans `ADR-0073` et dans `review.md`, qui ne sont pas de moi** : le geste
est le remplacement d'un mot par le terme que le depot emploie deja 127 fois, pas une reecriture
d'argument. Laisser les deux formes coexister aurait fabrique l'incoherence que je signale. Les deux
auteurs sont nommes ici pour qu'ils le voient plutot que de le decouvrir.

---

## 3. Ce que je route a son auteur

### 3.1 A `ines-design` - "texte de remplacement" contre "texte alternatif"

**15 occurrences contre 48**, sur le meme concept, l'attribut `alt`.

Le depot a tranche de fait, et trois fois : la cle de contenu s'appelle `illustrationTexteAlternatif`,
la clause 5 d'`ADR-0073` dit *"texte alternatif"*, et `CON-5` de `nadia-prof` aussi. Le document
`proposition-ines-palier-1-du-visuel.md` emploie **les deux dans le meme paragraphe** : son addendum
cite `CON-5` avec *"le texte alternatif"*, et son point 9.1.2 ecrit *"un texte de remplacement qui
decrit le dessin"*. Il n'y a pas de distinction conceptuelle entre les deux emplois - c'est bien un
seul concept sous deux noms.

**Je ne l'ai pas corrige, et le motif est de forme** : le terme porte le **titre du document** et le
**titre de la section 9**, et cette section est citee par renvoi depuis au moins deux autres fichiers
(*"voir la section 9 de..."*). Renommer un titre cite ailleurs n'est plus une correction de mot.
**Route a `ines-design`**, a qui appartient le document et la decision de renommer ou de motiver
l'ecart.

### 3.2 A `nadia-prof` - sur `cp-figures-planes`, l'indice est le seul texte qui varie, et il partitionne les reponses

**C'est un fait mesure, pas un verdict**, et je ne le tranche pas : la porte est la sienne.

Les douze items de `cp-figures-planes` ont un enonce parmi trois formulations equivalentes et **un
texte alternatif identique**, rendu identique par `CON-5` au motif ecrit que *"toute difference entre
eux porterait la reponse"*. **L'indice, lui, prend trois valeurs, et elles se distribuent ainsi :**

| Indice | Sur quels items | Exclusif ? |
|---|---|---|
| *"Regarde si la figure a des coins."* | les **3** items dont la reponse est *un cercle* | oui, et sur aucun autre |
| *"Compte les cotes de la figure."* | les **3** items dont la reponse est *un triangle* | oui, et sur aucun autre |
| *"Compare la longueur des cotes."* | les **6** items *carre* ou *rectangle* | oui, et sur aucun autre |

**Consequence, verifiee item par item contre les propositions offertes** : sur les six items cercle
et triangle, l'indice designe la bonne reponse parmi les trois propositions **sans qu'il soit besoin
de regarder le dessin** - *un cercle* est la seule figure sans coins proposee a chaque fois.

**Ce qui rend le point digne d'etre pose plutot que tu, c'est la dissymetrie de traitement dans le
meme paquet** : le raisonnement qui a rendu les douze textes alternatifs identiques s'applique mot
pour mot a l'indice, et l'indice n'en a pas beneficie. **Et la condition existe ailleurs** : `SEM-8`
gouverne l'indice de `cp-semaine` (*"il dit quoi se rappeler, jamais la reponse"*, huit mots au
plus) ; `FIG-1` a `FIG-4` portent sur le rendu, la couleur et la confusion cercle/disque, **et sur
l'indice, rien**. Le paquet ou l'indice est le seul texte variable est celui ou aucune condition ne
le gouverne.

*(Verifie et sans objet ailleurs : les deux indices de `cp-monnaie` sont correles au type d'enonce,
deja visible ; les quatre de `cp-semaine` a la famille de question, deja dans l'enonce, et tous
tiennent `SEM-8`.)*

### 3.3 A `nadia-prof` - deux enonces de `cp-semaine` sans inversion interrogative

> *"Pendant quelle saison les feuilles tombent ?"* et *"Pendant quelle saison les fleurs poussent ?"*

La forme normee est *"les feuilles tombent-elles ?"*. Sans inversion ni *est-ce que*, l'enonce releve
de l'oral familier.

**Je ne l'ai pas corrige** parce que c'est de la **syntaxe et du registre**, pas de l'orthographe, de
l'accentuation ni de la ponctuation : ma porte s'arrete la, et le registre de ce que lit un enfant
est le sien. **Je le nomme parce que les 21 autres enonces du paquet sont bien formes** - c'est un
ecart interne au paquet, pas un parti pris de registre.

---

## 4. La dette n° 7, redite pour la seconde fois

**`DEC-0062`, amendement C : la charte de `relecteur-pedagogique` n'a toujours pas la convention
`MASTER`.** Verifie sur l'arbre, des deux cotes :

| Cote | Etat |
|---|---|
| `squad/README.md`, **section 2 bis** | **Ferme.** La convention y est, les trois formes, le nom `MASTER`, les deux bornes, la regle de capitales. `sofia-sm` l'a ecrite a la cloture de s24 |
| `.claude/agents/relecteur-pedagogique.md` | **Ouvert. Zero occurrence de `MASTER`**, aucun critere d'attribution dans la section `Criteres`, rien dans `Reflexe a chaque tache` |

`DEC-0062` section 4 affirme au present *"elle est verifiee par `relecteur-pedagogique`"*. Son
amendement C dit deja qu'il faut la lire au conditionnel et route la fermeture ; **la fermeture n'a
pas eu lieu**. C'est exactement la dette n° 7 de `CLAUDE.md` - *"un garde-fou annonce mais absent"* -
et c'est le **second sprint consecutif** ou le meme trou est route sans etre comble.

**Je ne modifie pas ma propre charte** : `.claude/agents/` est de la configuration d'agent, hors de
mon perimetre, et une charte qui se donne elle-meme ses criteres ne verifie plus rien. **Route a
`sofia-sm`**, et a l'utilisatrice si `sofia-sm` maintient qu'une charte d'agent ne se modifie pas
sans elle - c'est la position qu'elle a tenue a s24 et rien ne l'a levee depuis.

**Ce que ce trou coute concretement, et il est mesurable** : j'ai verifie la convention sur s25 ci-
dessous **parce que la consigne de ma tache me l'a demande**, pas parce que ma charte l'exige. Un
appel qui ne le demanderait pas ne la ferait pas verifier.

---

## 5. Ce que j'ai verifie et qui est conforme

Liste donnee pour que la prochaine relecture sache ce qui a ete regarde, et comment.

### 5.1 La langue du contenu enfant - controle systematique, 67 items

**Les quatre paquets, champ par champ** : enonces, textes support, indices, bonnes reponses,
distracteurs, textes alternatifs.

- **Accentuation integrale** : aucune lettre manquant son accent, sur les quatre paquets.
- **Ponctuation** : majuscule initiale et point final sur toutes les phrases ; **espace insecable**
  (`U+00A0`) devant chaque `?` et de part et d'autre de chaque paire de guillemets francais - 107
  occurrences, exactement le compte attendu (60 + 12 + 12 + 23). Aucune apostrophe typographique,
  aucun tiret cadratin.
- **`cm1-substituts`, la classe laissee a la main par `ADR-0065`** : les 20 textes support lus un par
  un, en visant les homographes que l'automate ne juge pas - *arbitre*, *garde*, *court*, *tire*,
  *range*, *note*, *plonge*, *monte(nt)*, *cote* contre *cote*, *goute*. **Aucune faute.** Les
  accords des groupes nominaux referents sont justes, y compris sur les items ou le referent est
  eloigne.
- **Les textes alternatifs des 24 dessins** : les 12 de `cp-figures-planes` sont identiques ; les 12
  de `cp-monnaie` prennent 7 valeurs distinctes, dont les accords sont corrects (*"Des billets et des
  pieces poses cote a cote"* au masculin pluriel, *"Des pieces posees"* au feminin). **Verifie item
  par item qu'aucune des 7 valeurs n'elimine un distracteur** : les distracteurs sont du meme ordre
  de grandeur que la bonne reponse dans les douze cas, donc savoir qu'il y a des billets plutot que
  des pieces ne retire aucune proposition.
- **Commentaires YAML** : en ASCII sur les quatre paquets, conforme a la convention du depot, alors
  que les champs servis a l'enfant sont accentues. La frontiere est tenue.

### 5.2 Les conventions de redaction, sur les 94 fichiers touches par s25

- **Aucun emoji, aucun tiret cadratin ou demi-cadratin, aucun guillemet typographique, aucune
  apostrophe courbe** hors contenu enfant. Balayage sur tout caractere au-dela de `U+2100` compris.
- Les **5 occurrences de `x` multiplicatif** (`U+00D7`) sont le libelle *"tables de multiplication
  (jusqu'a x10)"* du programme de l'Education nationale, cite verbatim, y compris dans
  `TexteAccentueExposeParLePortEntrantTest`. Signe mathematique et non decoratif, citation figee :
  **non touchees**, et le dire evite qu'on les corrige par reflexe au prochain passage.
- **`verifier-chemins.sh` en code 0** sur l'arbre suivi : aucun chemin de poste, aucun nom de compte.
- **Prefixes de commit** : 39 commits, tous prefixes ; verifie avec `--no-merges`, comme la CI
  l'appelle, le seul message sans tag etant un commit de fusion.
- **Toutes les references resolvent** : chaque `ADR-XXXX` et `DEC-XXXX` cite dans les 94 fichiers
  correspond a un fichier existant (74 `ADR`, 67 `DEC`). Zero renvoi mort - c'est le controle qu'un
  ecart de `CLAUDE.md` avait rendu necessaire le 2026-08-19.

### 5.3 La forme de mesure d'`ADR-0071`

**Respectee dans les documents de s25.** `avis-faisabilite.md`, `proposition-ines-palier-1-du-visuel.md`
et `reponse-ines-douze-chiffres-traces.md` citent tous la version en la rapportant a la machine
virtuelle des tests. C'est l'ecart que j'avais route a `ines-design` a s24 : **il est ferme dans ses
documents de s25.**

### 5.4 La convention `MASTER` appliquee a s25

**Correctement employee**, dans la section 3.3 du planning : formule fixe *"recu de MASTER, sans
source dans le depot"*, capitales, deux enonces cites mot pour mot, dates, et la borne de `DEC-0062`
section 3.2 explicitement portee (*"ils ne fondent rien"*).

**La regle 3.5 - le gel a la reception - est tenue**, et de deux facons :

- la section 3.2 fige la citation et ecrit *"tout agent ulterieur cite cette section-ci (forme 1)"* ;
- un troisieme enonce arrive par le meme canal **n'est pas ecrit sous la forme 3** parce que son
  auteur a verifie qu'il etait dans le depot, et il est cite de forme 1 avec sa reference. C'est
  exactement le reflexe de la section 3.4.

**Verifie qu'aucun enonce gele n'est recite divergemment ailleurs** : les deux enonces de forme 3
n'apparaissent que dans le planning, la seconde occurrence de l'un des deux etant un renvoi et non
une reception. L'arbitrage *"Instruire dans s25"* est cite a l'identique dans les trois fichiers qui
le portent. **Aucun ecart de la famille `fera`/`ferait`.**

### 5.5 Le code et sa documentation

- **La Javadoc d'`ADR-0073`** (`Question.java`, `QuestionContentSourceFactory.java`,
  `BanditManchotSession.java`) decrit ce que le code fait et **pourquoi**, sans paraphraser une seule
  ligne. Elle nomme la frontiere - *"le contenu declare ce qu'il montre, l'adapter web decide ou il
  le prend"* - ce qu'un developpeur junior ne pourrait pas deduire du code seul. Rien a corriger.
- **Le message de la clause 5** est en ASCII, nomme sa regle et l'item fautif :
  `"ADR-0073 clause 5 : une illustration et son texte alternatif se declarent ensemble ou pas du
  tout - item <id>"`. Conforme.
- **Le nommage des 24 identifiants d'illustration** est regulier et descriptif
  (`figure-<forme>-<variante>`, `monnaie-<type>-<lettre>`), en ASCII, sans extension ni chemin -
  ce que la clause d'`ADR-0073` exige.
- **Les marqueurs `SUB-3` de `cm1-substituts` disent vrai** : le commentaire annonce un marqueur sur
  chaque item pour que *"le compte se refasse en lisant le fichier"*. Refait : **20 items sur 20
  marques, 11 pronoms et 9 groupes nominaux**, les deux familles de la ligne du programme servies.

---

## 6. Ce que je juge systemique plutot qu'isole

**Un lot qui ouvre une matiere neuve introduit son vocabulaire sans le raccorder a celui du depot.**

Les points 2.1 et 3.1 sont le meme defaut vu deux fois. En un sprint, un concept neuf - l'attribut
`alt` d'une illustration - a recu **trois designations concurrentes** : *texte alternatif* (retenu de
fait par la cle de contenu et par deux clauses), *texte de remplacement*, et le dispositif qui le lit
appele tantot *lecteur d'ecran*, tantot *lecture d'ecran*. La seconde s'etait deja propagee dans
**neuf fichiers, dont deux de production**, avant d'etre relue.

**Ce n'est pas une faute d'auteur** : chacun a nomme une chose qui n'avait pas de nom dans ce depot,
et aucun n'avait de raison de savoir ce que l'autre ecrivait le meme jour. **C'est un trou de
process** : le depot fixe ses conventions de forme (tirets, guillemets, chemins) et les fait executer
par des garde-fous, mais **il ne fixe son lexique nulle part**, et rien ne fait le raccord avant que
le vocabulaire ait essaime. La relecture le rattrape apres coup - ce sprint-ci, elle l'a rattrape ;
rien ne garantit qu'elle sera appelee au bon moment le sprint prochain, ce que s24 a demontre.

**Je ne propose pas la solution, ce n'est pas ma porte** : qu'un `ADR` qui ouvre une matiere neuve
fixe le nom de ce qu'il introduit est une question de process pour `sofia-sm` et de forme d'`ADR`
pour `theo-archi`. **Je pose le fait avec son cout mesure** : 11 occurrences dans 9 fichiers, dont la
Javadoc de deux classes de production, pour un concept ne il y a un sprint.

**Le point 4 est systemique par sa recurrence** : deuxieme sprint que le meme trou de charte est
route sans etre comble, sur la dette que `CLAUDE.md` nomme en tete.

---

## 7. Ce que cette relecture ne fait pas

- **Elle ne prononce aucun Done**, ni technique ni pedagogique. La mesure de la section 1 est un
  constat, pas un verdict : la porte technique est jugee par la CI, la porte pedagogique par
  `nadia-prof` seule.
- **Elle ne juge le niveau scolaire d'aucun contenu**, ni le vocabulaire, ni le ton, ni le fond de ce
  que lit un enfant. Les points 3.2 et 3.3 sont poses comme des **faits**, avec leur methode, et
  routes a qui les tranche.
- **Elle ne tranche rien de produit ni d'architecture**, et n'a modifie aucune logique de code : le
  seul diff de code est le remplacement de deux mots dans des commentaires et des messages.
- **Elle ne modifie la charte d'aucun agent**, y compris la mienne.
