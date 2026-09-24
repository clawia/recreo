# Fiche de conformite - bandit-manchot-roles-ecole-commune-cp

- **Mecanique** : bandit manchot.
- **Contenu** :
  `adapters/adapter-content/src/main/resources/contenu/cp-emc-roles-ecole-commune.yaml`
  (11 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-roles-ecole-commune-cp`, titre servi *"Les roles de
  l'ecole et de la commune"*, consigne servie *"Lis bien, puis dis a qui s'adresser, ou ce que
  fait cette personne."*, `nombreDeManches: 10`.
- **Ligne declaree** : `EMC-CP-13`.
- **Statut global** : **VALIDE** (second audit du 2026-09-24, apres correction ; le premier audit
  du meme jour avait refuse, sa trace est conservee plus bas). Fiche ouverte
  par `camille-po` a l'ouverture du bloc de construction du sprint s41 (paquet et catalogue livres
  et fusionnes dans `round-two`, commit `b5b07966`).
- **Story** : `S41-EMC-ROLES` - Identifier quelques roles et services de l'ecole et de la commune
  (`docs/backlog/backlog.md`).
- **Competence programme visee** : enseignement moral et civique, CP (cycle 2), domaine
  "Construire une culture civique" -
  `squad/programme/enseignement-moral-et-civique/competences-cp.md`, `EMC-CP-13`. Ligne
  `non couvert` dans `squad/programme/MATRICE-couverture.md` au moment d'ouverture de cette fiche.
- **Arbitrage pedagogique prealable deja rendu, cite en entier dans la story, aucun arbitrage neuf
  a router** (`ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-
  borne.md`, ligne 237) : *"oui (lot a 1,5) [...] Connaissance verifiable. Deux taches : a qui
  s'adresse-t-on pour telle chose ; que fait telle personne ou tel service. `EMC-6` : aucun item ne
  suppose la commune, l'ecole ou le foyer de l'enfant. Le produit ne les connait pas et ne peut
  pas les connaitre (`DEC-0028`)."*
- **Lien decision** : `DEC-0095`.
- **Derniere mise a jour** : 2026-09-24 (second audit apres correction, `nadia-prof` instance A).

---

## Criteres d'acceptation de la story (`docs/backlog/backlog.md`, `S41-EMC-ROLES`)

- Etant donne le paquet livre, quand on le relit dans son entier, alors les roles et services du
  libelle (directeur, mairie, pompiers, medecin) sont chacun en bonne reponse au moins une fois
  (`FER-1`).
- Etant donne un item, quand il s'affiche, alors il porte l'une des deux taches distinctes (a qui
  s'adresse-t-on pour telle situation ; que fait telle personne ou tel service), jamais uniquement
  la premiere sur tout le paquet (`FER-3`).
- Etant donne un item, quand la situation s'affiche, alors elle ne suppose jamais une commune, une
  ecole ou une configuration familiale particuliere de l'enfant (`EMC-6`).
- Etant donne cette manche, quand les propositions s'affichent, alors la bonne reponse y figure
  exactement une fois, avec des distracteurs qui sont d'autres roles ou services reels, jamais des
  elements sans rapport.
- Etant donne chaque item du paquet, quand il declare sa ligne de referentiel, alors il porte
  `EMC-CP-13` et uniquement elle.
- Etant donne le Done pedagogique de ce paquet, quand `nadia-prof` (instance A) le joue, alors
  elle confirme que les roles et services du libelle sont chacun servis et qu'aucun item ne
  suppose la commune ou l'ecole de l'enfant.

## Ce que cette story ne fait pas

Elle ne suppose jamais la commune, l'ecole ou le foyer de l'enfant (`EMC-6`) ; elle ne fixe aucun
contenu factuel precis - reste a l'auteur du paquet puis a l'audit de `nadia-prof`.

---

## Bloc de validation pedagogique

# Audit du 2026-09-24 (`nadia-prof`, instance A de s41) - REFUSE

## 1. Ce que j'ai joue

Reacteur reconstruit depuis `round-two` au commit `3f6b379f`
(`bash outils/mvn-jdk21.sh -B clean package -DskipTests`), `bootstrap/target/recreo.jar` relance
sur le JDK 21 designe par la toolchain. **Audit joue, pas lu** : cinq parties completes de dix
manches, plus une partie ou je me trompe volontairement. **Les onze items sont remontes a
l'ecran**, les six de la tache A et les cinq de la tache B.

## 2. Ce qui est tenu, et que je constate avant de refuser

- **`EMC-6`, la condition que j'avais posee d'avance et que `theo-archi` a declaree non encodable**
  (avis de faisabilite, section 6.3 : jugement semantique, ma garde) : **aucun des onze items ne
  suppose la commune, l'ecole ou le foyer de l'enfant.** Les situations disent *"un batiment"*,
  *"un enfant"*, *"une classe"*, *"une rue du quartier"*, *"les habitants"*, *"une personne"*.
  Jamais *"ta commune"*, jamais *"ton ecole"*, jamais *"ta mairie"*. **Condition tenue sans
  exception, verifiee item par item a l'ecran.**
- **`FER-1`** : les quatre roles et services du libelle sont chacun en bonne reponse - les pompiers
  (items 1, 6), le medecin (2), le directeur ou la directrice (3, 5), la mairie (4).
- **`FER-3`** : deux taches distinctes, jouees toutes les deux - a qui s'adresser (items 1 a 6),
  que fait tel role ou tel service (items 7 a 11).
- **Lisibilite** : aucun enonce ne depasse quinze mots ni deux phrases, la seconde etant la
  question.
- **Traitement de l'erreur** : premiere erreur, *"Ce n'est pas ca. Essaie encore !"* suivi de
  l'indice ; troisieme erreur, la bonne reponse est revelee. Un enfant bloque avance.
- **Ecriture non genree des roles** : *"le directeur ou la directrice"*, *"il ou elle"*,
  *"une maitresse ou un maitre"*. C'est juste, et sur une ligne qui nomme des metiers c'est ce
  qu'il fallait faire.
- **Point que j'ai regarde specifiquement** : l'indice de l'item 9 mentionne *"un camion rouge"*.
  Une couleur percue y figure, mais elle n'y porte aucune reponse - l'indice nomme aussi
  *"une grande echelle"* et *"un camion"*, et l'enonce porte deja *"en cas d'incendie"*. Un enfant
  daltonien n'est desavantage sur aucun item de ce paquet.
- **Exactitude des dix autres items** : verifiee un par un. Les pompiers interviennent bien
  au-dela de l'incendie (item 6, ascenseur ; item 11, secours aux personnes) ; la commune a bien la
  charge des batiments et du materiel d'une ecole elementaire publique (item 8) ; le directeur ou
  la directrice autorise bien une sortie scolaire sans nuitee (item 3) ; le medecin soigne et
  vaccine (item 10). **Rien a reprendre sur ces dix items, et je demande qu'ils ne soient pas
  reecrits.**

## 3. Le motif du refus : l'item 7 enseigne un fait inexact sur l'ecole

**Item 7, joue** :

- enonce : *"Que fait le directeur ou la directrice de l'ecole ?"*
- bonne reponse servie a l'enfant : *"il ou elle organise l'ecole et **remplace les enseignants
  absents**"*
- distracteurs : *"il ou elle soigne les blessures"* / *"il ou elle eteint les incendies"*

**Ce n'est pas ce que fait un directeur d'ecole.** Le remplacement d'un enseignant absent est
assure par un **enseignant remplacant**, affecte par les services departementaux de l'education
nationale ; c'est un poste distinct, tenu par un autre professeur des ecoles. Le directeur ou la
directrice **veille a la bonne marche de l'ecole et repartit les eleves entre les classes** quand
aucun remplacant n'est disponible - ce qui n'est pas la meme chose que remplacer, et ce que le
paquet dit d'ailleurs correctement ailleurs, en **item 5** : *"Un enseignant est absent. Qui
organise la classe ce jour-la ?"* -> *"le directeur de l'ecole"*. **Le paquet connait la bonne
formulation et la sert deja ; l'item 7 la deborde.**

**Pourquoi je refuse pour un seul membre de phrase.** Sur cette ligne, la seule chose que le jeu
apprend a un enfant de six ans, c'est **qui fait quoi**. L'item 7 est precisement l'item qui
**definit** le role du directeur, et la definition qu'il sert est fausse sur sa seconde moitie. Les
deux distracteurs etant tres eloignes (*soigne les blessures*, *eteint les incendies*), l'enfant
choisira la bonne proposition sans difficulte - **et repartira avec l'enonce faux en tete, sans
jamais avoir eu l'occasion de le contredire**. Un item qu'on ne peut pas rater est exactement celui
dont le texte doit etre exact. C'est aussi ce que l'arbitrage prealable de cette ligne demandait en
un mot : *"connaissance verifiable"*.

**Correction demandee, et elle tient en un membre de phrase** : remplacer *"et remplace les
enseignants absents"* par ce que le role recouvre reellement, par exemple *"il ou elle organise
l'ecole et repartit les eleves quand un enseignant est absent"*, ou plus simplement *"il ou elle
organise l'ecole et veille a ce que tout s'y passe bien"*. **Les deux distracteurs de l'item 7 et
les dix autres items ne sont pas concernes.**

## 4. Second point a corriger dans le meme passage (n'est pas, a lui seul, le motif du refus)

**Item 2** : *"Un enfant a une forte fievre depuis plusieurs jours. Qui **consulte-t-on** ?"* Le
verbe *consulter*, au sens medical, n'est pas du vocabulaire de CP, et sa forme interrogative avec
le pronom *on* inverse ajoute une difficulte de lecture a une question par ailleurs simple. *"Qui
va-t-on voir ?"* dit la meme chose et se lit en CP. Meme famille que la reserve que j'ai ouverte le
2026-09-23 sur le verbe *illustre* (`FICHE-bandit-manchot-devise-cp.md`, section 3) : **c'est une
reserve de registre, non bloquante en elle-meme**, et elle ne serait pas devenue un refus si l'item
7 n'avait pas rouvert le paquet. Je la note ici pour qu'elle soit payee dans le meme passage.

## 5. Ce dont je reste la seule garde sur ce paquet

Dit explicitement pour que l'equipe ne croie pas couvert ce qui ne l'est pas. La CI est verte sur
cet increment ; **elle n'a vu ni l'inexactitude de l'item 7, ni le registre de l'item 2**, et aucune
des deux n'est de nature a etre vue par un automate.

| Propriete | Automatisable ? | Qui la tient |
|---|---|---|
| `EMC-6` - aucun item ne suppose la commune, l'ecole ou le foyer de l'enfant | non (`theo-archi`, avis de faisabilite section 6.3) | **moi seule** ; **tenue ici** |
| Exactitude de ce que fait chaque role et chaque service | non | **moi, contre source** |
| `EMC-5` - aucun item n'a deux reponses defendables | non | **moi seule** |
| Registre de vocabulaire adapte au CP (item 2) | non | **moi seule** |

---

> **Done pedagogique : REFUSE.** 2026-09-24, `nadia-prof`, instance A de s41.
>
> **Motif** : l'item 7, qui est l'item de definition du role, sert a l'enfant un fait inexact - *"le
> directeur ou la directrice organise l'ecole et **remplace les enseignants absents**"*. Le
> remplacement d'un enseignant absent est assure par un enseignant remplacant affecte par les
> services departementaux, pas par le directeur, qui veille a la bonne marche de l'ecole et
> repartit les eleves entre les classes quand aucun remplacant n'est disponible. Le paquet sert
> deja la formulation exacte en item 5 (*"Qui organise la classe ce jour-la ?"*). Les deux
> distracteurs de l'item 7 etant tres eloignes, l'enfant ne peut pas rater l'item et repart donc
> avec l'enonce faux : un item qu'on ne peut pas rater est celui dont le texte doit etre exact. La
> ligne demandait une *"connaissance verifiable"*.
>
> **Correction demandee** : remplacer *"et remplace les enseignants absents"* par ce que le role
> recouvre reellement (par exemple *"et repartit les eleves quand un enseignant est absent"*).
> Corriger au passage *"Qui consulte-t-on ?"* en *"Qui va-t-on voir ?"* (item 2, registre de CP).
> **Les dix autres items sont exacts, `EMC-6` est tenue sans exception sur les onze, et rien
> d'autre ne doit etre reecrit.**
>
> **Ce refus renvoie a `camille-po`, qui en fait du backlog.** Il ne bloque pas le sprint suivant.
> `EMC-CP-13` reste `non couvert` dans `MATRICE-couverture.md` ; je ne touche pas la matrice.

---

# Second audit (2026-09-24, apres correction de `lea-dev` au commit `bddd8233`) - VALIDE

**Le refus ci-dessus n'est pas efface et ne le sera pas.** Il est leve par ce second audit, sa
trace reste lisible.

## 6. Ce que j'ai rejoue

Reacteur reconstruit sur `round-two` a jour (`bcbe3b5f`,
`bash outils/mvn-jdk21.sh -B clean package -DskipTests`), jar relance sur le JDK 21 designe par la
toolchain. Quatre parties completes de dix manches plus deux parties ciblees sur les items 2 et 7.
**Les onze items sont remontes a l'ecran**, y compris les neuf que je n'avais pas demande de
rejouer : je verifie a chaque fois que la correction n'a rien casse ailleurs.

## 7. Le motif du refus est paye

**Item 7, rejoue, capture de la partie** :

- enonce : *"Que fait le directeur ou la directrice de l'ecole ?"*
- bonne reponse servie : *"il ou elle organise l'ecole et **veille au bon deroulement de la
  journee**"*
- distracteurs inchanges : *"il ou elle soigne les blessures"* / *"il ou elle eteint les
  incendies"*
- indice inchange, et toujours coherent avec le nouvel enonce : *"Il ou elle est responsable de
  toute l'ecole, du matin au soir."*

La clause fautive a disparu. **Ce que l'item enseigne desormais est exact** : organiser l'ecole et
veiller au bon deroulement de la journee est ce que recouvre la fonction, et la formulation ne
revendique plus une mission qui appartient a un enseignant remplacant. L'item 5 (*"Un enseignant
est absent. Qui organise la classe ce jour-la ?"* -> *"le directeur de l'ecole"*) reste inchange et
reste exact : le paquet dit maintenant la meme chose aux deux endroits.

**Point que j'ai controle de pres, parce qu'une reecriture peut deplacer un probleme plutot que le
regler** : la nouvelle formulation ne recouvre aucune des deux autres propositions de l'item, ni
aucune bonne reponse d'un autre item de la tache B. *"veille au bon deroulement de la journee"* ne
se confond ni avec *"elle s'occupe des batiments et du materiel de l'ecole"* (item 8, la mairie),
ni avec *"il ou elle decide des regles de l'ecole"* ou *"il ou elle organise les sorties
scolaires"*, qui restent des distracteurs de l'item 10. Aucun item n'a deux reponses defendables
(`EMC-5`).

## 8. Le second point est paye aussi

**Item 2, rejoue** : *"Un enfant a une forte fievre depuis plusieurs jours. Qui doit-on aller
voir ?"* Le verbe *consulter* a disparu, la tournure est celle d'un enfant de CP, la bonne reponse
et l'indice sont inchanges. Treize mots, deux phrases, la seconde etant la question : `LON-1` et
`LON-2` tenues.

## 9. Rien n'a casse ailleurs

Verifie a l'ecran sur les onze items : `EMC-6` reste tenue sans exception (aucune situation ne
suppose la commune, l'ecole ou le foyer de l'enfant - les deux enonces reecrits disent toujours
*"un enfant"* et *"l'ecole"* de facon generique) ; `FER-1` et `FER-3` sont inchangees, les quatre
roles et services du libelle restent chacun en bonne reponse ; les dix items que j'avais juges
exacts au premier audit sont inchanges au caractere pres ; le traitement de l'erreur est intact
(*"Ce n'est pas ca. Essaie encore !"* puis l'indice, puis revelation de la bonne reponse au
troisieme echec), verifie en me trompant volontairement sur les deux items corriges ; l'ecran de
fin de partie s'affiche normalement.

## 10. Ce dont je reste la seule garde sur ce paquet

Inchange depuis la section 5 du premier audit, et je le redis parce qu'un verdict vert ne le
supprime pas : `EMC-6`, `EMC-5` et l'exactitude de ce que fait chaque role sont des jugements
semantiques qu'aucun automate de ce depot ne rend. La CI etait verte sur la version que j'ai
refusee.

---

> **Done pedagogique : VALIDE.** 2026-09-24, `nadia-prof`, instance A de s41. Le refus du meme jour
> est **leve**, sa trace est conservee ci-dessus.
>
> **Ce que j'ai verifie** : les onze items rejoues a l'ecran sur un jar reconstruit depuis
> `round-two` a jour. L'item 7 n'affirme plus que le directeur remplace les enseignants absents et
> ce qu'il enseigne est exact ; l'item 2 est passe dans le registre du CP ; les neuf autres items
> sont inchanges et toujours exacts ; `EMC-6` est tenue sans exception sur les onze, `FER-1` et
> `FER-3` sont tenues, aucun item n'a deux reponses defendables, aucun distracteur n'est absurde,
> l'erreur est traitee (indice puis revelation) et un enfant bloque avance. Aucune des deux
> corrections n'a introduit de defaut ailleurs dans le paquet.
>
> **`camille-po` peut passer `EMC-CP-13` a `couvert-valide`** dans `MATRICE-couverture.md`. Je ne
> touche pas la matrice.
>
> **Aucune reserve ouverte sur ce paquet.**
