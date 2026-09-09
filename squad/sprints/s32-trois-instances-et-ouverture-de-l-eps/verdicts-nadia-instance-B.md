# Verdicts de `nadia-prof` - instance B de s32 - la dette d'EMC CP

**Date : 2026-09-09.** Arbre audite : `round-two` a `fd19d18f`, worktree dedie `s32-nadia-b`.
Terrain de cette instance : **la dette d'EMC CP, a titre ponctuel** (`S32-CAT1`, `S32-EMCCP07`,
`S32-4`), plus `POS-R3` sans budget. **Mon terrain permanent reste sciences et technologie CM1**,
qu'aucun lot ne finance ce sprint : `PAR-2` n'est pas engagee et **EMC CP ne devient pas mon
terrain**.

**Je n'ai audite ni l'histoire-geographie, ni l'anglais, ni la portee D (instance A), ni
l'education physique et sportive (instance C), et je n'ai relu aucune des deux autres instances** -
c'est la regle que j'ai ecrite moi-meme.

---

## 1. Ce que je prononce

| Objet | Verdict | Ligne de matrice |
|---|---|---|
| **`EMC-CP-07`** (`bandit-manchot-regle-souhait-loi-cp`), **troisieme passage** | **REFUSE**, sur un motif **entierement neuf** en deux points. **`FAB-1c`, le motif unique du 2026-09-08, est paye.** | **reste `non couvert`** |
| **`CAT-1` sur `EMC-CP-06`** (`bandit-manchot-regles-de-vie-cp`) | **PAIEMENT REFUSE.** 2 items sur 11 tiennent la clause, 5 a moitie, 4 pas du tout. `CAT-1` **reste ouverte**, non bloquante. **Je ne rouvre pas la ligne.** | reste `couvert-valide` |
| **`CAT-1` sur `EMC-CP-02`** (`bandit-manchot-permis-interdit-cp`) | **PAIEMENT REFUSE.** 1 item sur 4 tient la clause, 2 a moitie, 1 pas du tout. `CAT-1` **reste ouverte**, non bloquante. **Je ne rouvre pas la ligne.** | reste `couvert-valide` |
| **`REG-R1`** | **LEVEE**, y compris sa clause la plus dure sur l'item 5 | - |
| **`PER-R1`** | **LEVEE** | - |
| **`EMO-R1`, `EMO-R2`** | **LEVEES** | reste `couvert-valide` |
| **`POL-R1`, `POL-R2`** | **LEVEES** | reste `couvert-valide` |
| **`EXC-R2`, `EXC-R4`** | **LEVEES** | reste `couvert-valide` |
| **`EXC-R3`** | **LEVEE SUR L'ITEM 1, MAINTENUE SUR LES ITEMS 5 ET 6** - le mot a change, la propriete non | reste `couvert-valide` |
| **`EXC-R1`, `EXC-R5`** | **NON PAYEES**, defauts revus a l'ecran. **L'obstacle technique de `EXC-R5` est leve** par `theo-archi` ; le contenu, lui, est inchange | reste `couvert-valide` |
| **Sept reserves neuves** : `EMO-R3`, `EXC-R6`, `PER-R2`, `POL-R3`, `POL-R4`, `REG-R2`, `REG-R3` | **ouvertes**, datees du 2026-09-09, **toutes non bloquantes** | aucune n'ouvre ni ne ferme de ligne |
| **`POS-R3`** (`division-posee-cm1`) | **non audite** : affecte a mon instance au planning, **non finance a s32**. Le paquet n'a pas bouge depuis s30 (`58169bcc`). Il reste au perimetre indicatif de s34 | reste `couvert-valide` |

**Aucune ligne de `squad/programme/MATRICE-couverture.md` ne bouge sur mon terrain, dans les deux
sens** : `EMC-CP-07` ne ferme pas, et les quatre lignes deja fermees ne sont pas rouvertes. **La
matrice appartient a `camille-po`** ; je ne l'ai pas touchee.

**Le sort des six paquets que j'ai joues, en une phrase** : cinq restent utilisables et servis sous
reserves, un reste refuse, et **le seul endroit des six ou le produit dit non a un enfant qui a
raison** est l'item de `EXC-R5`, ouverte depuis le 2026-09-07 et toujours impayee.

---

## 2. Corrections a mes propres constats, avant de m'en servir

**Je verifie ce que j'ai ecrit avant de m'appuyer dessus.** Onze constats de moi sont faux ou
incomplets ; **quatre m'ont ete rendus par le sprint**, et j'en ai trouve sept en recomptant.

### 2.1 Les quatre qui m'ont ete rendus, et je les prends

1. **Ma definition litterale de la phrase est fausse, et c'est mesure.** *"Un segment termine par un
   point, un point d'interrogation ou un point d'exclamation"* coupe `"0 + ? = 10"` en deux et rend
   **22 enonces servis rouges** dans deux paquets que mon propre releve ne compte pas parmi les
   enonces a deux phrases. **Le critere derive retenu par `ADR-0089` - un `.`, `?` ou `!` qui termine
   le texte ou qu'une majuscule suit - est meilleur que le mien : 0 fautif contre 22.** **Je l'adopte
   pour tous mes comptages**, et c'est sous ce critere que j'ai recompte les six paquets aujourd'hui.
2. **`EMO-R1` nomme deux enonces trop longs quand il y en avait trois** : l'item 6, a **16 mots**,
   etait compte parmi les neuf que je declarais conformes. -> `EMO-R3`.
3. **`EXC-R4` de meme** : l'item 2, a **16 mots**, est le plus long du paquet et il est dans la tache
   A, que ma reserve n'a pas mesuree. -> `EXC-R6`.
4. **Les cinq longueurs de `POL-R1` n'ont jamais ete recomptees sous ma propre convention**, et
   l'item 11 y vaut 25 mots la ou ma reserve en ecrit 26. **Cela ne change aucun verdict** - ces
   enonces etaient trop longs et ils l'etaient -, et je ne les recompte pas apres coup.

**Ces quatre constats me sont contraires et aucun n'a modifie une reserve** : `lea-dev` les a
confirmes par son propre script reproduisant ma convention plutot que de toucher a mes textes.
**C'est la bonne facon de me contredire.**

### 2.2 Les sept que j'ai trouves en recomptant

5. **`REG-R1` annoncait *"Mesure, pas impression - les trois, cites"*. Il y en avait cinq.** Les deux
   distracteurs absurdes des items 4 et 6 de `regles-de-vie` n'ont jamais ete comptes. -> `REG-R2`.
   **`REG-R1` reste levee** : elle nommait trois distracteurs, ils sont payes.
6. **`POL-R1` ne mesurait que la tache B.** Les trois plus longs enonces du paquet apres correction
   sont dans la tache A, a 16, 18 et 19 mots. -> `POL-R3`.
7. **Le defaut de `PER-R1` existait aussi sur `formules-de-politesse`, et je ne l'ai pas vu en
   fermant la ligne le 2026-09-07.** *"Que ne respecte pas Malo ?"* ne s'enchaine pas avec *"dire
   merci"*. -> `POL-R4`. **Traiter le meme defaut comme un refus sur un paquet et l'ignorer sur son
   voisin serait pire que de l'avoir manque.**
8. **J'ai credite `PLA-1` sur `regle-souhait-loi` le 2026-09-08 en condamnant nommement la chaine
   *"seulement les enfants"* sur l'item du klaxon - et la meme chaine survit sur l'item 11 du meme
   paquet, que j'ai lu le meme jour et porte au credit du correctif.** C'est le point 2 de mon
   refus. **Un poteau que j'avais plante et devant lequel je suis passee sans regarder.**
9. **Mes deux textes du 2026-09-08 se contredisent sur la tache A de `regle-souhait-loi`** : la fiche
   dit *"a conserver telle quelle"*, la section 16.5 de mon arbitrage prealable dit que `LON-1` mord
   sur ce paquet et prescrit de supprimer l'enumeration de la question. **`lea-dev` a suivi le
   second, ce qui etait le choix raisonnable.** La regression de l'item 4 vient de la ; elle est a
   mon debit.
10. **`EMC-5` sur l'item 4 de `regle-souhait-loi` : je l'ai creditee deux fois sans la voir.** Le
    casque a velo **est** une regle qui vaut pour tous, et le jeu marque cette proposition fausse.
11. **Deux de mes fiches annoncaient une consigne servie que le produit ne sert plus**
    (`regles-de-vie`, `permis-interdit`, toutes deux *"Lis bien la question, puis choisis la bonne
    reponse."*). **C'est la famille d'`EXC-R1` dans mes propres fichiers.** Corrigees dans ce diff,
    contre `catalogue.yaml`.

### 2.3 Une correction qui n'est pas de moi et que je porte quand meme : le compte des reserves de `S32-4`

**`S32-4` s'intitule *"dix reserves"* et en nomme neuf** : `EMO-R1`, `EMO-R2` (2), `POL-R1`,
`POL-R2` (2), `EXC-R1` a `EXC-R5` (5). **2 + 2 + 5 = 9.** Le planning et la charte de ce sprint
reprennent *"dix"*, et l'annonce du travail livre dit *"huit des dix payees"* en nommant sept
identifiants. **Le compte reel est sept payees sur neuf** (`EMO-R1`, `EMO-R2`, `POL-R1`, `POL-R2`,
`EXC-R2`, `EXC-R3`, `EXC-R4`), **deux impayees** (`EXC-R1`, `EXC-R5`) - et sur les sept, **`EXC-R3`
n'est payee que sur un item des trois qu'elle nomme**, donc **six et un tiers**, si l'on veut le
chiffre juste. Les deux erreurs vont dans le meme sens : elles gonflent d'une unite le denominateur
et le numerateur. **Je ne corrige ni le backlog ni le planning - ce ne sont pas mes fichiers - et je
donne le compte ici pour que la review ne recopie pas le mauvais.**

---

## 3. Partie jouee - `bandit-manchot-regle-souhait-loi-cp` (`EMC-CP-07`)

**Methode des six parties de ce releve** : jar reconstruit depuis mon worktree
(`mvn -B -DskipTests clean package` par l'enveloppe de toolchain, JDK **21.0.10** rapporte par la
machine virtuelle), lance sur un port local, une partie complete de **dix manches** par paquet,
**erreurs volontaires** jusqu'a epuisement des propositions sur chaque manche dont la premiere
reponse pressee n'etait pas la bonne. **Lecture du HTML servi, pas de la page rendue par un
navigateur : c'est le point degrade de cet audit, et aucun de mes verdicts ne repose sur un jugement
visuel.**

**Chemin d'erreur, verifie une fois pour toutes sur `permis-interdit` et retrouve identique
partout** : la **premiere** erreur affiche *"Ce n'est pas ca. Essaie encore !"* **suivi de
l'indice** ; a la **troisieme** tentative fausse, l'ecran revele la reponse - *"Ce n'est pas ca.
Appuie sur la bonne reponse : ..."* - et l'enfant continue. **Un enfant bloque peut avancer.** Fin
de partie : *"Bravo, c'est juste ! C'est fini !"* et le score.

| Manche | Item | Enonce servi | Propositions, dans l'ordre reel apres brassage | Ce que j'ai presse et ce que l'ecran a repondu |
|---|---|---|---|---|
| 1 | 9 | *Une loi interdit de fumer dans la cour d'une ecole. Qui peut y fumer ?* | *les adultes de l'ecole* / *personne, pas meme les adultes* / *les enseignants seulement* | faux, puis juste |
| 2 | 5 | *A la cantine, chaque eleve doit attendre son tour pour se servir. Est-ce une regle ?* | *une regle qui vaut pour tous* / *un simple souhait* / *une loi* | juste |
| 3 | 11 | *Une loi interdit de vendre de l'alcool aux mineurs. Qui peut en acheter ?* | *seulement les adultes* / *seulement les enfants* / *tout le monde, meme les enfants* | juste |
| 4 | 6 | *Aya aimerait que la maitresse donne moins de devoirs le week-end. Est-ce une regle ?* | *une loi* / *un simple souhait* / *une regle qui vaut pour tous* | faux, puis juste |
| 5 | 2 | *La maitresse aimerait que la classe soit plus silencieuse. Est-ce une regle ?* | *une regle qui vaut pour tous* / *un simple souhait* | faux, puis juste |
| 6 | 8 | *Une loi rend obligatoire le port de la ceinture en voiture. Qui doit l'attacher ?* | *les enfants et les adultes a l'arriere* / *seulement le conducteur* / *le conducteur et tous les passagers* | deux faux, puis juste |
| 7 | 4 | *En France, le casque a velo est obligatoire avant douze ans. Est-ce une regle ?* | *une loi* / *un simple souhait* / *une regle qui vaut pour tous* | **juste du premier coup - et c'est cette manche qui refuse la ligne** |
| 8 | 3 | *Dans la cour, il est interdit de pousser un camarade. Est-ce une regle ?* | *une regle qui vaut pour tous* / *un simple souhait* | juste |
| 9 | 1 | *Avant la recreation, chaque eleve doit ranger sa chaise, sans exception. Est-ce une regle ?* | *un simple souhait* / *une regle qui vaut pour tous* | faux, puis juste |
| 10 | 7 | *Le code de la route dit qu'il faut s'arreter au feu rouge. Qui doit s'arreter ?* | *seulement les pietons* / *tout le monde, y compris les adultes* / *les motards et les cyclistes* | faux, puis juste |

**Item non tire** : l'item 10 (klaxon), lu dans le fichier. **Onze items, dix manches** : c'est
`FER-2` qui veut cela, et le tirage m'a servi **les cinq items de la tache B**.

**Ce que la manche 7 montre** : la bonne reponse est *une loi*, et *une regle qui vaut pour tous* est
marquee fausse par une question qui demande *"Est-ce une regle ?"*. **Motif complet dans la fiche**,
`FICHE-bandit-manchot-regle-souhait-loi-cp.md`, bloc du 2026-09-09.

---

## 4. Partie jouee - `bandit-manchot-regles-de-vie-cp` (`EMC-CP-06`)

| Manche | Item | Enonce servi | Propositions, dans l'ordre reel | Presse |
|---|---|---|---|---|
| 1 | 3 | *Lea a fini de peindre. Que doit-elle faire de son materiel ?* | *le jeter a la poubelle* / *le ranger* / *le laisser sur la table* | faux, puis juste |
| 2 | 4 | *Pourquoi range-t-on le materiel apres l'avoir utilise ?* | *pour que chacun retrouve ses affaires* / *pour gagner un jeu* / *pour empecher les autres d'y toucher* | juste |
| 3 | 7 | *Hugo veut parler alors qu'un camarade parle deja. Que doit-il faire ?* | *parler plus fort que lui* / *attendre son tour* / *aller prevenir la maitresse tout de suite* | faux, puis juste |
| 4 | 11 | *Aya a emprunte des crayons de la classe. Que doit-elle faire en repartant ?* | *les garder pour elle* / *les donner a la maitresse pour qu'elle les range* / *les remettre a leur place* | deux faux, puis juste |
| 5 | 10 | *Pourquoi fait-on la queue a la cantine ?* | *pour que la maitresse compte les eleves* / *pour que tout le monde passe sans se bousculer* / *pour que le repas soit plus chaud* | faux, puis juste |
| 6 | 9 | *A la cantine, plusieurs eleves veulent se servir en meme temps. Que doivent-ils faire ?* | *faire la queue* / *demander a la maitresse de choisir qui passe en premier* / *se bousculer* | juste |
| 7 | 5 | *Dans les couloirs de l'ecole, comment doit-on se deplacer ?* | *en courant* / *en sautant a cloche-pied* / *en marchant* | deux faux, puis juste |
| 8 | 6 | *Pourquoi marche-t-on dans les couloirs au lieu de courir ?* | *pour arriver le premier en classe* / *pour arriver en retard* / *pour que personne ne se blesse* | deux faux, puis juste |
| 9 | 8 | *Pourquoi ecoute-t-on un camarade quand il parle ?* | *pour comprendre ce qu'il dit* / *pour gagner une recompense* / *pour savoir quand ce sera son tour de parler* | juste |
| 10 | 1 | *Avant de parler en classe, que doit faire un eleve ?* | *parler plus fort* / *regarder la maitresse sans rien dire* / *lever la main* | deux faux, puis juste |

**Item non tire** : l'item 2, lu dans le fichier.

**Les deux constats que cette partie donne, et qui ne se lisent pas dans le fichier aussi vite qu'a
l'ecran** :

- **Sur les manches 1, 2, 7 et 8** (items 3, 4, 5, 6), les deux distracteurs sonnent tous les deux
  comme la faute ou l'absurde : j'ai gagne ces quatre manches en pressant la seule proposition calme,
  soigneuse ou desinteressee. **`CAT-1` n'y est pas payee.**
- **Sur six des onze items, un distracteur nomme la maitresse ; aucune bonne reponse ne la nomme**,
  et **sur l'item 2 les deux distracteurs la nomment**. La strategie *"je ne prends jamais le bouton
  qui parle de la maitresse"* donne la bonne reponse d'emblee sur cet item-la. **Le repere de
  registre a ete remplace par un repere lexical.** Compte : **1** occurrence de *maitresse* dans les
  propositions avant le correctif, **7** apres.

**Motif complet et compte item par item dans la fiche**, bloc du 2026-09-09.

---

## 5. Partie jouee - `bandit-manchot-permis-interdit-cp` (`EMC-CP-02`)

| Manche | Item | Enonce servi | Propositions, dans l'ordre reel | Presse |
|---|---|---|---|---|
| 1 | 4 | *Pourquoi est-il interdit d'insulter un camarade ?* | *pour ne pas le faire souffrir* / *pour ne pas deranger la classe* / *pour parler plus fort que lui* | juste |
| 2 | 6 | *Pourquoi est-il interdit de pousser ou de frapper ?* | *pour ne pas se faire mal soi-meme* / *pour finir la recreation plus vite* / *pour ne pas lui faire mal* | deux faux, puis juste |
| 3 | 3 | *Malo dit des mots mechants a Hugo. Comment appelle-t-on ca ?* | *une insulte* / *un service* / *un cadeau* | juste |
| 4 | 1 | *Hugo rit de la voix d'Aya. Comment appelle-t-on ca ?* | *un compliment* / *une devinette* / *une moquerie* | deux faux, puis juste |
| 5 | 9 | *Malo imite Lea devant la classe pour rire d'elle. Comment appelle-t-on ca ?* | *une insulte* / *une moquerie* / *un compliment* | faux, puis juste |
| 6 | 8 | *Hugo explique calmement pourquoi il n'est pas d'accord. Comment appelle-t-on ca ?* | *une insulte* / *de la violence* / *une discussion* | deux faux, puis juste |
| 7 | 10 | *Pourquoi est-il interdit d'insulter un camarade, meme pour rire ?* | *parce que ca fait perdre du temps* / *parce que ca peut blesser malgre tout* / *parce que ca fait beaucoup de bruit* | faux, puis juste |
| 8 | 2 | *Pourquoi est-il interdit de se moquer d'un camarade ?* | *pour ne pas le blesser* / *pour faire plus de bruit* / *pour gagner un jeu* | juste |
| 9 | 7 | *Lea prete son crayon a Malo. Comment appelle-t-on ca ?* | *une moquerie* / *un service* / *de la violence* | faux, puis juste |
| 10 | 11 | *Aya tire les cheveux de Malo pendant la recreation. Comment appelle-t-on ca ?* | *de la violence* / *une blague sans consequence* / *un jeu de recreation* | juste |

**Item non tire** : l'item 5, lu dans le fichier.

**Ce que la manche 2 montre** : trois boutons, dont deux disent *"ne pas faire mal"*, et **la seule
chose qui les distingue est *lui* contre *soi-meme*** - alors que l'enonce ne nomme personne. -> 
`PER-R2`. **`PER-R1` est levee** : les six *"Comment appelle-t-on ca ?"* s'enchainent correctement
avec les cinq qualifications, entendu sur les cinq manches de qualification qui m'ont ete tirees.

---

## 6. Partie jouee - `bandit-manchot-emotions-autrui-cp` (`EMC-CP-01`)

| Manche | Item | Enonce servi | Presse |
|---|---|---|---|
| 1 | 9 | *Hugo sourit largement et saute partout dans le couloir. Qu'est-ce que cela montre ?* | faux, puis juste |
| 2 | 2 | *Hugo recoit le velo qu'il attendait depuis longtemps pour son anniversaire. Que ressent-il ?* | deux faux, puis juste |
| 3 | 1 | *Lea a perdu son doudou prefere a la sortie de l'ecole. Que ressent-elle ?* | juste |
| 4 | 3 | *Aya voit un gros chien aboyer tres fort juste devant elle. Que ressent-elle ?* | deux faux, puis juste |
| 5 | 8 | *Aya a les larmes aux yeux et la tete baissee. Qu'est-ce que cela montre ?* | juste |
| 6 | 7 | *Tom serre les poings et crie tres fort. Qu'est-ce que cela montre ?* | deux faux, puis juste |
| 7 | 6 | *Lea gagne la partie a laquelle elle joue avec ses amis dans la cour. Que ressent-elle ?* | deux faux, puis juste |
| 8 | 10 | *Malo se cache derriere sa maman et tremble un peu. Qu'est-ce que cela montre ?* | deux faux, puis juste |
| 9 | 11 | *Lea fronce les sourcils et tape du pied par terre. Qu'est-ce que cela montre ?* | faux, puis juste |
| 10 | 4 | *Malo voit sa tour de cubes renversee expres par son petit frere. Que ressent-il ?* | juste |

**Les quatre propositions sont les quatre emotions du libelle sur les onze items** ; je ne releve pas
l'ordre des boutons, il ne porte ici aucun repere.

**Les trois indices de `EMO-R2` lus a l'ecran apres erreur volontaire** (manches 6, 9 et - pour
l'item 4, non tire en erreur - dans le fichier) : plus aucun mot savant, et l'indice de l'item 8
cesse de demander une lecture figuree. **`EMO-R1` et `EMO-R2` sont levees.** **La manche 7 est celle
qui ouvre `EMO-R3`** : *"la partie a laquelle elle joue"*, 16 mots et le seul relatif compose des
onze enonces.

---

## 7. Partie jouee - `bandit-manchot-formules-de-politesse-cp` (`EMC-CP-05`)

| Manche | Item | Enonce servi | Presse |
|---|---|---|---|
| 1 | 2 | *Hugo recoit un livre offert par son oncle pour son anniversaire. Que doit-il dire ?* | juste |
| 2 | 5 | *Tom recoit l'aide d'un camarade pour ranger les livres tombes par terre. Que doit-il dire ?* | juste |
| 3 | 7 | *Malo coupe la parole a Hugo qui raconte son week-end. Que ne respecte pas Malo ?* | deux faux, puis juste |
| 4 | 8 | *Aya prend le feutre de Lea sans le lui demander. Que ne respecte pas Aya ?* | faux, puis juste |
| 5 | 6 | *Lea voudrait encore de l'eau, dans son verre presque vide. Que doit-elle dire ?* | faux, puis juste |
| 6 | 1 | *Lea arrive a l'ecole le matin et croise sa maitresse dans le couloir. Que doit-elle dire ?* | deux faux, puis juste |
| 7 | 3 | *Aya voudrait emprunter la gomme de Tom, qui ne s'en sert pas en ce moment. Que doit-elle dire ?* | juste |
| 8 | 11 | *Hugo parle plus fort que Tom, sans le laisser finir. Que ne respecte pas Hugo ?* | deux faux, puis juste |
| 9 | 9 | *Tom aide Malo, qui repart aussitot sans un mot. Que ne respecte pas Malo ?* | juste |
| 10 | 4 | *Malo croise le directeur de l'ecole dans la cour, pour la premiere fois de la journee. Que doit-il dire ?* | deux faux, puis juste |

**Item non tire** : l'item 10, lu dans le fichier. **Les cinq items de la tache B m'ont ete servis**,
et c'est ce que `POL-R1` visait.

**`POL-R1` et `POL-R2` sont levees** : les cinq enonces de la tache B tiennent en une proposition
principale, de 14 a 15 mots, et la scene du verre presque vide se tient (manche 5). **Les manches 3
et 9 ouvrent `POL-R4`** : *"Que ne respecte pas Malo ?"* -> *"dire merci"*. **Les manches 6, 7 et 10
ouvrent `POL-R3`** : 16, 18 et 19 mots, les trois plus longs enonces du paquet, tous dans la tache A
que ma reserve n'avait pas mesuree.

---

## 8. Partie jouee - `bandit-manchot-exclusion-cp` (`EMC-CP-03`)

| Manche | Item | Enonce servi | Propositions, dans l'ordre reel | Presse |
|---|---|---|---|---|
| 1 | 3 | *Depuis plusieurs jours, Malo ecarte Tom du jeu a chaque recreation.* | *une moquerie qui se repete* / *un accident isole* / *un jeu de recreation* | juste |
| 2 | 4 | *En courant, Tom bouscule Malo une seule fois et s'excuse aussitot.* | *une moquerie qui se repete* / *une bagarre* / *un accident isole* | deux faux, puis juste |
| 3 | 11 | *En classe, une moquerie se repete le vendredi. A qui Lea doit-elle en parler ?* | *a sa maitresse* / *a un surveillant de la cour* / *a l'adulte qui surveille la cantine* | juste |
| 4 | 6 | *Malo fait tomber le dessin de Lea une fois, sans faire expres.* | *un accident isole* / *une blague* / *une moquerie qui se repete* | juste |
| 5 | 1 | *Chaque midi cette semaine, Malo empeche Aya de s'asseoir avec le groupe.* | *une moquerie qui se repete* / *une bousculade* / *un accident isole* | juste |
| 6 | 5 | *Aya marche sur le pied de Hugo une seule fois, par accident.* | *un jeu de recreation* / *un accident isole* / *une moquerie qui se repete* | faux, puis juste |
| 7 | 8 | *En classe, une moquerie se repete chaque jour. A qui Aya doit-elle en parler ?* | *a l'adulte qui surveille la cantine* / *a sa maitresse* / *a un surveillant de la cour* | faux, puis juste |
| 8 | 10 | *Une moquerie se repete dans la cour, le matin. A qui Tom doit-il en parler ?* | *a un surveillant de la cour* / *a sa maitresse* / *a l'adulte qui surveille la cantine* | juste |
| 9 | 9 | *A la cantine, une moquerie se repete chaque jour. A qui Malo doit-il en parler ?* | *a un surveillant de la cour* / *a sa maitresse* / *a l'adulte qui surveille la cantine* | **deux faux, puis juste - et le premier faux est *a sa maitresse*** |
| 10 | 7 | *Dans la cour, une moquerie se repete chaque jour. A qui Hugo doit-il en parler ?* | *a un surveillant de la cour* / *a l'adulte qui surveille la cantine* / *a sa maitresse* | juste |

**Item non tire** : l'item 2, lu dans le fichier - et c'est celui qui ouvre `EXC-R6`.

**Trois choses vues a l'ecran et pas ailleurs** :

- **La consigne des dix manches est restee *"Lis bien, puis dis ce qu'il faut faire, ou a qui en
  parler."*** alors qu'aucune proposition de la tache A n'est une action. **`EXC-R1` n'est pas
  payee**, et je l'ai relu dix fois en dix manches.
- **Manche 9 : j'ai presse *"a sa maitresse"* pour une moquerie repetee a la cantine, et le jeu m'a
  repondu *"Ce n'est pas ca"*.** **`EXC-R5` n'est pas payee.** Un enfant qui parle a sa maitresse
  d'une moquerie de la cantine fait ce qu'il faut.
- **Les cinq indices de la tache B disent desormais le role de l'adulte** et non le lieu deja ecrit
  dans l'enonce (manches 7 et 9 lues apres erreur). **`EXC-R2` est levee, et c'est le meilleur
  paiement des cinq.**

---

## 9. La porte technique - les quatre jobs de la CI, lances sur mon diff et nommes un par un

**Ce que je ne fais plus depuis l'amendement du 2026-09-08 a `CLAUDE.md`** : deduire la porte
technique de `mvn verify` seul. **Le job `chemins` est reste rouge un sprint entier** pendant que
quatre agents et le Scrum Master annoncaient la porte verte sur cette foi. **Je nomme donc les quatre
jobs, avec ce que chacun a rendu sur mon diff**, et je dis lequel je n'ai pas pu lancer si c'est le
cas.

| Job de `.github/workflows/ci.yml` | Ce que j'ai lance | Resultat mesure |
|---|---|---|
| **`prefixes`** | `bash .github/scripts/verifier-prefixes-test.sh` (**15 cas, tous passent**), `bash .github/scripts/commit-atteignable-test.sh` (**5 cas, tous passent**), puis le sujet de mon commit pipe dans `verifier-prefixes.sh` comme le job le fait | **VERT.** *"Tous les commits portent un prefixe d'agent connu."*, code de sortie 0 |
| **`chemins`** | `bash .github/scripts/verifier-chemins-test.sh` (**22 cas, tous passent**), puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh'` pipe dans `verifier-chemins.sh`, **mon diff etant indexe** pour que mes trois fichiers neufs ou modifies y entrent | **VERT.** *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, code de sortie 0 |
| **`outils`** | les trois motifs du job (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`), chacun rendant au moins un fichier | **VERT. 9 juges purs executes**, aucun echec, aucun motif vide |
| **`build`** | `mvn -B clean verify` sur la **racine** du reacteur (reacteur complet, jamais un `-pl` sans `-am`), par l'enveloppe de toolchain, puis `mvn -B -pl bootstrap -am package` | **VERT. BUILD SUCCESS, 1439 tests, 0 echec, 0 erreur** - 105 `domain`, 13 `application`, 388 `adapter-web`, 689 `adapter-content`, 238 `bootstrap` unitaires, 6 tests d'integration Spring Boot. Fat jar reempaquete. **JDK 21.0.10, version rapportee par la machine virtuelle des tests**, pas par l'entree de toolchain (`ADR-0071`) |

**Les quatre sont verts.** **Aucun chiffre ne me manque** ; les six sous-totaux de tests
s'additionnent a 1439, ce qui est le compte que `sofia-sm` a mesure a la fusion de `fd19d18f`, et mon
diff n'ajoute ni test ni ligne de code.

**Deux choses que je dis parce qu'elles sont vraies et pas parce qu'elles m'arrangent :**

- **Mon diff ne touche aucun fichier de code ni de contenu.** Il ajoute des blocs de fiche, ce
  releve, et sept lignes au registre d'identifiants. **Un `build` vert sur mon diff ne prouve donc
  rien de pedagogique**, et c'est exactement le point du fait qui m'est rendu chaque sprint : les
  quatre jobs sont verts sur l'arbre ou l'item 4 de `regle-souhait-loi` marque fausse une proposition
  vraie, et ou `EXC-R5` fait d'un adulte reel une reponse fausse depuis trois jours.
- **Le seul job de ces quatre que mon diff peut faire rougir est `prefixes`**, par un message de
  commit, **et `chemins`**, par un chemin de poste de travail dans un fichier que j'ecris - c'est
  arrive le 2026-09-07 sur mon propre commit, pour un identifiant non alloue au registre. **C'est
  pour cela que je les lance moi-meme avant de commiter, plutot que de deduire la porte de
  `mvn verify` seul** (`CLAUDE.md`, amendement du 2026-09-08).

---

## 10. Le sort de chaque reserve, une par une

| Reserve | Ouverte le | Verdict du 2026-09-09 | Motif en une ligne |
|---|---|---|---|
| `REG-R1` | 2026-09-05 | **LEVEE** | Les trois distracteurs nommes sont remplaces par des raisons plausibles, et les trois propositions de l'item 5 sont des facons de se deplacer - ma clause la plus dure |
| `PER-R1` | 2026-09-06 | **LEVEE** | *"Comment appelle-t-on ca ?"* s'enchaine correctement avec les cinq qualifications |
| `EMO-R1` | 2026-09-07 | **LEVEE** | Items 4 et 5 a 14 et 11 mots, une proposition principale, sans futur |
| `EMO-R2` | 2026-09-07 | **LEVEE** | *posture*, *volontairement*, *brusques* partis, sans remplacant du meme rang, et la lecture figuree de l'item 8 aussi |
| `POL-R1` | 2026-09-07 | **LEVEE** | Les cinq enonces de la tache B de 14 a 15 mots, sans relative enchainee, l'item 7 ne comptant plus trois phrases |
| `POL-R2` | 2026-09-07 | **LEVEE** | *"encore de l'eau"* et *"presque vide"* : la scene se tient |
| `EXC-R2` | 2026-09-07 | **LEVEE** | Les cinq indices disent le role de l'adulte, information que l'enonce ne donne pas |
| `EXC-R4` | 2026-09-07 | **LEVEE** | Les deux enonces nommes a 15 et 14 mots, une seule proposition principale |
| `EXC-R3` | 2026-09-07 | **LEVEE SUR 1 ITEM SUR 3** | *une bousculade* tient sur l'item 1 ; *un jeu de recreation* et *une blague* sont exclus par les mots memes de l'enonce (*"par accident"*, *"sans faire expres"*), exactement comme *une dispute* et *une vengeance* |
| `EXC-R1` | 2026-09-07 | **NON PAYEE** | La consigne annonce toujours une tache que le paquet ne sert plus. Vue sur dix manches. **Ce qui manque est le texte, pas le deblocage** - et le texte est de mon ressort avant d'etre celui de `theo-archi` |
| `EXC-R5` | 2026-09-07 | **NON PAYEE** | *"a sa maitresse"* est toujours marque faux sur trois items. **L'obstacle technique est leve** et mesure par `theo-archi` ; le contenu est inchange |
| `CAT-1` | 2026-09-07 | **PAIEMENT REFUSE** sur les deux paquets | 2/11 sur `regles-de-vie`, 1/4 sur `permis-interdit`. Ma clause demande **les trois** propositions du meme registre ; un distracteur serieux par item n'y suffit pas, et le registre a ete remplace par un repere lexical |
| `POS-R3` | 2026-09-06 | **non audite** | Affectee a mon instance, **non financee**. Paquet inchange depuis s30 |
| `EMO-R3` | **2026-09-09** | **ouverte** | Item 6 a 16 mots, seul relatif compose du paquet. Mon comptage de `EMO-R1` en avait manque un |
| `EXC-R6` | **2026-09-09** | **ouverte** | Item 2 a 16 mots, le plus long du paquet, dans la tache A que `EXC-R4` n'avait pas mesuree |
| `POL-R3` | **2026-09-09** | **ouverte** | Items 1, 3, 4 a 16, 18, 19 mots. `POL-R1` ne mesurait que la tache B |
| `POL-R4` | **2026-09-09** | **ouverte** | *"Que ne respecte pas Malo ?"* -> *"dire merci"* : le meme defaut que `PER-R1`, sur le paquet voisin, que je n'avais pas vu |
| `PER-R2` | **2026-09-09** | **ouverte** | *"pour ne pas lui faire mal"* sans antecedent, et le nouveau distracteur en fait le critere de l'item |
| `REG-R2` | **2026-09-09** | **ouverte** | Deux distracteurs absurdes que `REG-R1` n'avait pas comptes (items 4 et 6) |
| `REG-R3` | **2026-09-09** | **ouverte** | L'adulte de l'ecole est une femme 7 fois sur 7 sur ce paquet, 17 contre 1 sur l'EMC CP entier. **J'avais pose ce constat le 2026-09-05 pour que le prochain lot ne l'aggrave pas ; il l'a aggrave** |

**Les sept identifiants neufs sont alloues dans `squad/decisions/REGISTRE-identifiants.md` dans ce
meme diff** (`PAR-5`), sur des prefixes deja existants, et le compte courant y passe de **547 / 94**
a **554 / 94**. **J'ai verifie plutot que suppose** que le motif du garde-fou n'ecarte plus les
identifiants a trois segments (`ADR-0088`, clause D) : aucun des sept n'en porte trois, mais le trou
ne me couvrirait plus si l'un en avait porte.

---

## 11. Ce dont je reste la seule garde, apres ce sprint

**Ce que je dis ici est destine a ce que l'equipe ne croie pas couvert ce qui ne l'est pas.**

| Propriete | Etat |
|---|---|
| **`LON-1`, `LON-2`** - longueur et forme de l'enonce au CP | **je cesse d'en etre la garde.** `ADR-0089` les execute, a egalite, sur 528 enonces de CP et 36 paquets, avec une dette declaree de 5 enonces qui sont exactement `EMO-R3`, `EXC-R6` et `POL-R3`. **C'est la premiere de mes barres de contenu qui devient mordable, et le sprint a mesure ce que son absence coutait** |
| **`CAT-1`** - la bonne reponse ne se reconnait pas a son ton | **moi seule**, et c'est le plus dangereux de la matiere. Ce sprint ajoute un fait : **le registre peut etre remplace par un repere lexical** sans qu'aucun test bouge |
| **Qu'aucune proposition vraie ne soit marquee fausse** (`EMC-5`) | **moi seule.** Les quatre jobs de la CI sont verts sur l'arbre ou l'item 4 de `regle-souhait-loi` dit quelque chose de faux a l'enfant, et `EXC-R5` traverse un sprint entier de plus |
| **Qu'un pronom de proposition ait un antecedent dans son enonce** | **moi seule** (`PER-R2`). Trois occurrences de cette famille dans ce seul sprint, dont deux trouvees par `lea-dev` et une par le `relecteur-pedagogique` |
| **Qu'une question et ses propositions forment une phrase correcte recollees** | **moi seule** (`PER-R1` payee, `POL-R4` ouverte). Deux paquets, aucun test |
| **Qu'un distracteur soit un candidat envisageable** (`PLA-1`, `EXC-R3`) | **moi seule**, et j'ai manque une occurrence a un jour d'intervalle sur le paquet ou je venais de la condamner |
| **Que la discrimination notee par un item soit dans le libelle de sa ligne** | **moi seule.** C'est la porte d'entree de `CON-1`, et la matrice ne la voit pas |
| **Le genre de l'adulte d'ecole dans la serie des paquets d'une matiere** | **moi seule** (`REG-R3`). Mesurable - un comptage de mots sur les paquets d'une matiere - mais **je n'ai compte cette population que sur l'EMC CP et je ne pose aucune barre chiffree en cours de sprint** |
| **La coherence entre la consigne d'une entree de catalogue et la tache servie** | **moi seule** (`EXC-R1`). Deux de mes propres fiches portaient le meme defaut, corrige dans ce diff |

---

## 12. Le cout, contre mon budget de 4,0

**Budget opposable** : **4,0**, dont **0,5** de marge `PAR-11` et **0,5** non allouee. Postes
budgetes : `S32-CAT1` 1,0, `S32-EMCCP07` 0,5, `S32-4` 1,5.

| Poste | Budget | Depense | Ce que j'ai fait |
|---|---|---|---|
| `S32-CAT1` (`regles-de-vie`, `permis-interdit`) | 1,0 | **1,0** | Deux parties completes de dix manches, `CAT-1` recomptee item par item sur 15 items des deux paquets, `REG-R1` et `PER-R1` verifiees, deux fiches, trois reserves neuves |
| `S32-EMCCP07` (`regle-souhait-loi`) | 0,5 | **0,75** | Partie complete, `FAB-1c` recomptee sur les cinq items de la tache B, les cinq faits de droit reverifies contre leur source, les onze longueurs recomptees, **et le motif neuf a instruire et a ecrire** - c'est le depassement, et il est la |
| `S32-4` (`emotions-autrui`, `formules-de-politesse`, `exclusion`) | 1,5 | **1,5** | Trois parties completes, neuf reserves instruites une par une, trois fiches, quatre reserves neuves |
| Marge `PAR-11` (lot neuf audite) | 0,5 | **0** | **Aucun lot neuf sur mon terrain** : les trois postes sont des corrections sur des paquets existants. **La marge n'etait pas due et je ne la prends pas** |
| Marge non allouee | 0,5 | **0,25** | Les onze corrections a mes propres constats, le recomptage du nombre de reserves de `S32-4`, et la correction des deux consignes perimees de mes fiches |
| **Total** | **4,0** | **3,5** | |

**Depense reelle : 3,5 sur 4,0.** **Je n'ai pas tire sur la marge `PAR-11`** - elle finance
l'audit d'un lot neuf, et je n'en ai audite aucun. **J'ai tire 0,25 sur la marge non allouee** et
**0,25 au-dela du chiffre de `S32-EMCCP07`**, pour le motif neuf du troisieme passage : instruire un
refus coute plus cher que constater un paiement. **Aucun transfert entre instances : je n'ai rien
cede ni recu** (`PAR-9`).

**Ce que ce chiffre dit du plafond de couverture de s32, de mon cote** : mon terrain visait **+1
ligne au plus**, et il en apporte **0**. La ligne visee etait `EMC-CP-07` et elle ne ferme pas.
**Cela n'est pas une surprise a lire dans mon budget** : j'avais chiffre 6 a 7 lignes pour le sprint
entier avec un plafond arithmetique de 8, et mon poste etait le plus incertain des trois.

---

## 13. Ce que je route, et ce que je ne fais pas

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **Les quatre lignes fermees restent `couvert-valide`, `EMC-CP-07` reste `non couvert`** | `camille-po` | **Aucun mouvement de matrice sur mon terrain.** Les mentions de couverture partielle deja exigees restent dues, mot pour mot |
| **Le motif du troisieme refus de `EMC-CP-07`, en deux points** | `camille-po`, via `sofia-sm` | Une story. **Le plus petit perimetre des trois passages** : un item de la tache A, un distracteur de l'item 11, et trois acquis a ne pas casser |
| **`CAT-1` non payee, avec ses deux comptes (2/11 et 1/4)** | `camille-po`, via `sofia-sm` | Une story par paquet, ou une pour les deux. **Regime inchange : rejeu borne, 0,5 par paquet** (`PAR-10`), `CAT-1` voyageant avec les reserves du meme paquet sans cout supplementaire |
| **Les sept reserves neuves** | `camille-po`, via `sofia-sm` | En faire des stories. **`EMO-R3`, `EXC-R6` et `POL-R3` sont le meme defaut sur trois paquets et sont deja portees par la dette declaree d'`ADR-0089`** |
| **`EXC-R1` et `EXC-R5`, impayees depuis le 2026-09-07** | `camille-po`, via `sofia-sm` | **`EXC-R5` d'abord** : c'est le seul endroit des six paquets ou le produit dit non a un enfant qui a raison, et son obstacle technique est leve |
| **Trois etiquettes de la dette de longueur d'`ADR-0089`** | `theo-archi` | **Constat, pas demande.** Les trois chiffres (1, 1, 3) restent justes ; ils nomment `EMO-R1`, `EXC-R4` et `POL-R1`, qui sont levees, la ou les enonces comptes sont ceux de `EMO-R3`, `EXC-R6` et `POL-R3` |
| **Qu'un test de paquet fige la formulation d'un enonce que ma porte demande de reecrire** | `theo-archi` | **Il l'a deja ouvert avec son chiffre** (25 fichiers sur 95). **Je le confirme du cote pedagogique** : c'est la premiere fois qu'un garde-fou de ce depot a rendu une de mes reserves impayable |
| **La contradiction entre deux de mes textes du 2026-09-08** | moi-meme | Ecrite dans la fiche de `regle-souhait-loi`, non effacee dans l'arbitrage. **Quand une de mes barres mord un paquet dont je viens d'ecrire *"a conserver telle quelle"*, c'est a moi de dire lequel gouverne, le jour ou je pose la barre** |

**Ce que je ne fais pas** : je ne mets pas a jour `squad/programme/MATRICE-couverture.md`, je n'ecris
aucune story, je ne fixe aucun perimetre, je ne fusionne pas ma branche, je ne modifie aucun contenu
et aucun fichier de `domain/`, `application/`, `adapters/` ou `bootstrap/`. Je n'ai touche ni au
backlog ni au planning, y compris la ou j'y ai trouve un compte faux.

---

## 14. A reprendre dans `review.md` - bloc pret a copier

**Je ne cree pas `review.md`** : c'est le fichier de ceremonie de `sofia-sm`, et trois instances qui
l'ecrivent en parallele produisent trois fois le meme conflit sur le meme fichier - le motif exact
pour lequel le planning a separe les trois worktrees. **Voici ma conclusion, dans la forme ou elle
doit y entrer.**

> **Terrain B - la dette d'EMC CP (instance B de `nadia-prof`, 2026-09-09).**
>
> **`EMC-CP-07` : REFUSE, troisieme passage, sur un motif entierement neuf.** `FAB-1c`, le motif
> unique du 2026-09-08, **est paye** (0 item sur 5 la ou la barre en autorise 1), les cinq faits de
> droit sont exacts, et les onze enonces passent de 13-30 mots a 12-15. **Le refus porte sur deux
> items** : l'item 4 de la tache A marque fausse une proposition vraie et note une distinction absente
> du libelle de la ligne ; l'item 11 porte un distracteur mort. **La regression de l'item 4 vient
> d'une contradiction entre deux de mes propres textes du 2026-09-08, et elle est a mon debit.**
> **`EMC-CP-07` reste `non couvert` : mon terrain apporte 0 ligne sur le +1 vise.**
>
> **`CAT-1` : paiement refuse sur `EMC-CP-02` et sur `EMC-CP-06`, sans rouvrir ni l'une ni l'autre.**
> Ma clause demande que **les trois** propositions soient du meme registre ; le correctif en a rendu
> **une** serieuse par item. Compte : **2 items sur 11** tiennent la clause sur `regles-de-vie`,
> **1 sur 4** sur `permis-interdit`. Et le repere de registre a ete remplace par un **repere
> lexical** : 6 items sur 11 portent *maitresse* dans un distracteur, **aucune bonne reponse ne la
> porte**, et sur un item cela donne la reponse d'emblee.
>
> **Reserves : 8 levees, 1 levee au tiers, 2 impayees, 7 ouvertes.** `REG-R1`, `PER-R1`, `EMO-R1`,
> `EMO-R2`, `POL-R1`, `POL-R2`, `EXC-R2`, `EXC-R4` **levees** ; `EXC-R3` levee sur 1 item des 3 ;
> `EXC-R1` et `EXC-R5` **non payees** - l'obstacle technique de `EXC-R5` est leve par `theo-archi`,
> le contenu est inchange. **Sept reserves neuves**, toutes non bloquantes, dont **cinq nees de mes
> propres comptes incomplets**.
>
> **Le compte de `S32-4` est de sept reserves payees sur neuf, pas huit sur dix** : la story
> s'intitule *"dix"* et en nomme neuf (2 + 2 + 5).
>
> **Six parties completes de dix manches jouees, erreurs volontaires, releves ecrits dans
> `verdicts-nadia-instance-B.md`, sections 3 a 8** - la trace dont `yanis-qa` a constate l'absence.
>
> **Cout : 3,5 sur un budget de 4,0.** Marge `PAR-11` **non prise** (aucun lot neuf sur ce terrain),
> 0,25 tiree sur la marge non allouee, 0,25 au-dela du chiffre de `S32-EMCCP07`. Aucun transfert
> entre instances.
