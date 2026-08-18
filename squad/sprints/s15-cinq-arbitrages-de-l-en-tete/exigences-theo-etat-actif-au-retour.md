# Exigences - l'etat actif s'eteint au retour au catalogue complet

- **Auteur** : theo-archi. **Date** : 2026-08-18.
- **Branche** : `s15-theo-retour`, worktree dedie, forkee de `round-two` a `65da40e` ; lignee verifiee
  par `outils/verifier-lignee.sh`.
- **Destinataire** : `marc-dev`. La section 1 est ce qu'il attend ; le reste peut se lire apres.
- **Aucun serveur lance, aucun port touche** : tout ce qui suit est lu dans la feuille servie et dans
  les gabarits. Aucun chiffre de rendu revendique.
- **Le defaut** : l'utilisatrice presse CM1 puis "Les jeux" ; la liste montre les onze jeux et CM1 garde
  sa couleur. Elle a raison, et le diagnostic de `sofia-sm` sur l'instance servie est exact.

## 1. La regle : j'inverse la polarite, mais **pas** sous la forme d'une neutralisation

**La piste est juste sur le fond : l'etat actif ne doit dependre que du fait que l'ecran montre ce
filtre.** Je la retiens, et je change sa forme - non pas "neutraliser quand la marque du filtre est
absente", mais **ne declarer l'apparence active que sous la condition que cette marque soit presente**.

**Regle a ecrire** - une seule, positive, dans la forme que la feuille emploie deja partout :

```css
body:has(.filtre-niveau) .chip-niveau.est-actif,
body:has(.filtre-niveau) .chip-niveau.est-actif:hover,
body:has(.filtre-niveau) .chip-niveau.est-actif:active {
    /* l'apparence active d'aujourd'hui, inchangee */
}
```

**Et la regle `body:has(.ecran-de-jeu) .chip-niveau.est-actif { ... }` est supprimee**, ainsi que la
regle inconditionnelle `.chip-niveau.est-actif` : l'apparence active n'existe plus qu'a l'interieur du
selecteur relationnel. **Ma moitie (ii) est donc remplacee, pas conservee.**

**Pourquoi la forme positive plutot que la neutralisation, et ce n'est pas une preference.** Une
neutralisation doit **enumerer ce qu'elle defait** : la regle actuelle repete les trois proprietes de
l'apparence active pour les annuler une par une. Le jour ou l'apparence active gagne une quatrieme
propriete, la neutralisation ne la defait pas, et l'etat survit a moitie - un defaut plus discret que
celui que l'utilisatrice vient de voir. Une application conditionnelle **n'a rien a enumerer** : ce qui
n'est pas applique n'a pas a etre defait. C'est une piece mobile de moins et un mode de defaillance de
moins.

**Un gain que je n'avais pas cherche et que je verifie plutot que de l'esperer** : la regle actuelle
depend de son **ordre de declaration** contre `.chip-niveau:hover` - le commentaire de la feuille le
dit lui-meme, *"meme specificite (0,2,0), l'ordre de declaration tranche"*. La forme positive vaut
`body` + `:has(.filtre-niveau)` + `.chip-niveau` + `.est-actif`, soit **(0,3,1) contre (0,2,0)** : elle
gagne par specificite, quel que soit l'ordre. Une propriete qui dependait d'une position dans un
fichier cesse d'en dependre. C'est une lecture de selecteurs, pas une mesure de rendu.

**Ce que la regle traite d'un seul geste** : les **trois** routes qui rendent `accueil :: zoneAccueil`
sans filtre - aucune ne porte `.filtre-niveau`, donc aucune n'allume la puce, et il n'y a rien a ecrire
par route ; **et** les quatre ecrans de jeu, qui ne portent pas cette marque non plus. Un lot qui
ajouterait une quatrieme route rendant ce fragment serait couvert sans que personne y pense.

### La subsomption est un gain, et l'objection merite une reponse precise

`sofia-sm` demande si couvrir deux motifs d'une regle peut masquer lequel a casse. **Ma reponse est que
les deux motifs n'en ont jamais fait deux.** "L'etat cesse quand le jeu demarre" et "l'etat cesse au
catalogue complet" sont deux instances d'une seule propriete : *l'apparence active s'affiche exactement
quand l'ecran affiche ce filtre*. Ma formulation de la veille - neutraliser sur un ecran de jeu -
**enumerait une instance de cette propriete**, et le defaut que l'utilisatrice a trouve est la seconde
instance, celle que l'enumeration manquait. C'est mot pour mot le defaut que ce depot a paye plusieurs
fois : un perimetre enumere echoue par omission. Remplacer l'enumeration par la propriete n'est pas
fusionner deux sujets, c'est cesser d'en decouper un.

**Le cout reel, et il existe** : le message d'un echec nommera desormais la marque du filtre, pas le
genre d'ecran. Si quelqu'un retire `.filtre-niveau` du catalogue filtre, le symptome sera "aucune
couleur nulle part" et non "couleur sur les ecrans de jeu". Diagnostic plus grossier, propriete plus
sure ; j'assume l'echange et je le note plutot que de le taire.

### Ce que `marc-dev` doit livrer

1. La regle positive ci-dessus ; suppression de la regle de neutralisation **et** de la regle
   inconditionnelle.
2. **`.filtre-niveau` reste porte par l'element de la phrase lui-meme**, jamais par un ancetre. C'est la
   condition qui rend le couplage de la section 2 sain plutot que fortuit, et c'est la logique de la
   reserve H d'`ADR-0035` : une declaration posee sur un conteneur devient fausse des que son contenu
   change sans que personne la relise.
3. **Clause E reformee** - elle change de sujet avec la regle, sinon elle rougirait a tort :
   > toute classe qu'un point d'entree peut porter conditionnellement (litteraux du `th:classappend`
   > du gabarit, jamais ecrits dans le test) n'est declaree par **aucune** regle maison en dehors d'un
   > selecteur conditionne par la marque du filtre.
   C'est **plus fort** que la version d'hier : hier j'exigeais qu'une regle la neutralise quelque part,
   ce qui laissait passer une declaration inconditionnelle a cote ; ici l'apparence active ne peut pas
   exister hors condition. **Non-vacuite inchangee et toujours tenue** : au moins une classe
   conditionnelle existe.
   Ses deux preuves de morsure existantes s'adaptent sans travail neuf : la mutation qui retirait la
   regle de neutralisation devient la mutation qui **retire la condition** du selecteur ; le jeu fautif
   du point d'entree neuf reste valable tel quel.
4. **Clause F : inchangee.** Elle lit la regle de l'apparence active, qui existe toujours, seulement
   conditionnee.

## 2. Le couplage a la phrase : sain, et c'est le sens qui compte

Les deux etats possibles d'une incoherence ne sont pas symetriques, et c'est tout l'argument :

- **couleur sans phrase** : l'enfant voit une teinte qui ne dit rien, et l'accord de `nadia-prof` -
  pose sur trois jambes dont *"la phrase dit la meme chose dans la meme reponse"* - est **void sans
  qu'elle le sache**. C'est un defaut silencieux, et c'est le pire de la famille ;
- **phrase sans couleur** : l'increment est degrade, la phrase dit encore la verite, et l'ecart se voit
  a la premiere capture de `nadia-prof`.

**La regle rend le premier etat structurellement inatteignable et laisse le second visible.** Elle
supprime la defaillance muette et garde la bruyante : c'est le bon sens de couplage, et c'est
exactement la logique du couple declaratif d'`ADR-0022` - les deux termes ou aucun.

**Ce que je concede** : un lot qui retirerait la phrase eteindrait la couleur. Ce n'est pas une
fragilite mais une **dependance vraie**, et elle est dans le bon sens : la couleur n'a aucun sens sans
ce que la phrase dit. Ce qui serait fragile serait l'inverse - une couleur qui survit a la disparition
de son explication, c'est-a-dire l'etat du depot ce matin.

## 3. Le trou du selecteur compose : traite comme sa propre exigence, et je dis pourquoi il n'entre pas dans ce correctif

**C'est un defaut de ma derivation, trouve par `sofia-sm`, chiffre par `nadia-prof`, et il est reel** :
`etatsMaison` reduit un selecteur compose a son premier element (`elementDuCompose`), donc les etats
declares sur `.chip-niveau.est-actif:hover` sont credites a `.chip-niveau`. Retirer les etats du
compose laisse les sept tests verts parce que `.chip-niveau:hover` existe encore. Le correctif est de
traiter un selecteur compose comme un **sujet distinct** et non comme un alias de sa premiere classe.

**Pourquoi il n'entre pas dans le lot que l'utilisatrice attend, et ce n'est pas un report** - mesure,
methode comprise : enumeration des selecteurs maison composes de deux classes ou moins, commentaires
retires, sur la feuille servie de `65da40e`. Il y en a **six familles**, dont
`.chip-niveau.est-actif` (avec ses deux etats), `.signal.est-juste`, `.signal.est-faux` - non
pressables, donc hors de la population - et **`.proposition.deja-essayee`, pressable, composee, qui ne
declare aujourd'hui aucun `:hover` ni `:active`**.

La derivation corrigee la reclamerait donc immediatement, et **la reponse a cette reclamation n'est pas
la mienne** : une proposition deja essayee porte la marque de M9 ; savoir si elle doit declarer sa
propre apparence sous le doigt, ou si l'apparence de `.proposition` doit continuer de valoir pour elle,
est un jugement de `nadia-prof` avec `ines-design` sur la forme. **Corriger la derivation sans cet
arbitrage produirait soit un rouge que le lot devrait faire taire dans l'urgence, soit une exemption
ecrite a la hate - les deux etant pires que le trou.**

**Ce que j'exige donc, et le calendrier est nomme** : la correction est due **dans ce sprint**, pas
"plus tard", et son unique bloqueur est l'arbitrage ci-dessus, que je route par `sofia-sm`. Le
contre-exemple est deja fabrique et n'a pas a etre refait. **Ce n'est pas la dette d'`ADR-0042`** : il
n'y a ici aucune decision acceptee dont la regle manque - la regle existe, elle a un trou nomme,
mesure, avec son unique blocage identifie et son contre-exemple pret.

**En attendant, ce que le trou coute exactement** : l'apparence active peut perdre ses etats sans qu'un
test rougisse, et la puce active prend alors l'apparence d'une puce inactive sous le doigt. **Ma regle
de la section 1 ne le corrige pas et ne pretend pas le corriger** ; elle en reduit toutefois une cause
adjacente, la dependance a l'ordre de declaration, puisque la forme positive l'emporte par
specificite.

## 4. Le releve, dans les deux sens

**Methode** : `grep -rln "<motif>" --include=*.java` sur les sources de test des cinq modules, hors
`target/`, arbre `65da40e`. Motifs : `est-actif`, `filtre-niveau`, `niveauActif`. Plus l'enumeration
des selecteurs composes de la feuille servie, methode en section 3.

**Cote "l'etat change de conditions"** :

| Source | Ce qui bouge |
|---|---|
| `LEtatActifDUnPointDEntreeSeteintSurUnEcranDeJeuTest` | **le seul fichier de test qui nomme `est-actif`**, et il nomme aussi `ecran-de-jeu` comme sujet de la clause E. Il **doit** changer avec la regle : sa constante de marqueur, l'assertion de la clause E, et la mutation de sa preuve de morsure (retirer la condition au lieu de retirer la regle). Son nom promet "s'eteint sur un ecran de jeu", ce qui devient trop etroit : il s'eteint des que l'ecran ne montre pas le filtre |
| `AucunEtatDePressableNEstAbandonneAuTiersTest` | derive de la feuille : la regle conditionnelle y entre seule, et le trou de la section 3 est ce qui l'empeche aujourd'hui d'y voir le compose |

**Cote "un element devient porteur sans qu'aucun test ne le nomme"** - et c'est le fait que le releve
apporte :

| Motif | Constat |
|---|---|
| `filtre-niveau` | **aucune source de test ne le nomme.** La phrase devient pourtant la condition d'une apparence : elle passe d'un texte a un porteur de propriete. La clause E reformee sera **le premier garde-fou a la nommer**, et c'est une raison de plus de la reformer plutot que de la retirer |
| `niveauActif` | aucune source de test ne le nomme ; c'est une variable de modele, gardee indirectement par les tests de rendu de l'en-tete |

**Ce que le releve n'attrape pas, inchange** : un garde-fou dont la derivation lit une source dont
l'element serait la derniere instance. Ici c'est justement le cas inverse qui s'est produit et qu'aucune
commande n'aurait donne - le trou de la section 3 se voit en lisant `elementDuCompose`, pas en cherchant
un litteral.

## 5. Ce que cet avis ne fait pas

Il ne choisit ni teinte ni forme - `ines-design`, puis `nadia-prof`. Il ne prononce aucun Done
pedagogique et ne rejuge pas le VALIDE du lot precedent : le defaut trouve par l'utilisatrice est reel
et il n'etait dans aucune des sept conditions de `nadia-prof`, ce qui n'enleve rien a son verdict sur
ce qu'elle avait juge. Il ne repriorise rien.
