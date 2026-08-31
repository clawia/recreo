# Proposition de rendu de l'ecran de saisie - forme, tailles, retour juste/faux, clavier

- **Auteure** : `ines-design`. **Date** : 2026-08-30. **Sprint** : s24 (ouverture).
- **Branche** : `s24-ines`, worktree dedie. **Arbre de depart** : `round-two` a `ea546cf`.
- **Repertoire renomme le 2026-08-30 par `sofia-sm`** au planning, sur la dependance `D7` de ce
  document : ce fichier, le prototype et le script de mesure pointent desormais
  `squad/sprints/s24-trois-paquets-et-ouverture-de-la-saisie/`. Seuls les chemins ont change, aucun
  contenu n'a ete deplace.
- **Ce que ce document est** : la dependance nommee par `theo-archi` comme bloquante de son lot 4
  (*"ce lot ne peut pas commencer avant cette proposition"*). Il donne la forme de l'ecran, le
  balisage Thymeleaf, les regles de feuille, et les chiffres releves sur un rendu reel.
- **Ce que ce document n'est pas** : ni un Done, ni une decision pedagogique, ni une decision
  d'architecture. **`nadia-prof` garde seule le veto**, y compris sur ce que je juge correct. Toute
  phrase adressee a l'enfant ecrite ici est un **candidat**, jamais un acquis.
- **Ce que je n'ai pas touche** : aucun fichier de `domain/`, `application/`, `bootstrap/`, ni
  aucun controleur, ni `adapters/adapter-web/src/main/resources/`. Ce commit n'ajoute rien a la
  production ; il ajoute un vehicule de mesure, son outil et ce document.

---

## 0. Pourquoi la production n'est pas touchee dans ce commit

Le fragment et les regles proposees ici sont le **lot 4** de `theo-archi`, et ce lot **depend du lot
3** (retypage d'`adapter-web` sur `GameSession`). Verser aujourd'hui un `fragments/saisie.html` dans
`templates/` poserait un gabarit qu'aucune route ne rend et qu'aucun controleur ne nourrit - un
drapeau mort, la forme exacte que `DEC-0009`/`DEC-0014` ont deja sanctionnee sur ce depot.

La proposition est donc **prete a coller** : le balisage de la section 3 et les regles de la
section 5.1 sont ecrits tels qu'ils doivent entrer dans `fragments/saisie.html` et `recreo.css`,
avec les selecteurs a etendre nommes un par un (section 7.4).

---

## 1. Ce qui contraint le rendu, trie par ce que chaque condition coute a l'ecran

`squad/conformite/ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md` (920 lignes, quatre blocs dates)
fait foi et **je ne le reformule pas**. Ce tableau ne dit que ce que chaque condition oblige a
**dessiner** ; il ne juge aucune d'elles.

| Condition | Ce qu'elle oblige a l'ecran |
|---|---|
| `SAI-1`, `SAI-3`, `SAI-4` | **rien de visible.** Ce sont des tolerances du verdict. Elles ne demandent aucune boite - et c'est important : le champ n'a a montrer aucune regle de forme a l'enfant |
| `SAI-2a` | **rien de visible non plus.** Le mode strict est declare par le contenu ; il ne se lit pas sur l'ecran. Il gouverne en revanche deux choses ci-dessous : `SAI-2c` et le rang de touches accentuees de la section 6 |
| `SAI-2c` | sur un item strict, **la chaine jugee est montree aussi quand la reponse est bonne**. Un bloc de plus dans le retour de reussite |
| `SAI-5` | **aucun troisieme etat de signal.** Deux etats, comme aujourd'hui |
| `SAI-8` | a la reussite par tolerance, **la forme de reference est montree**, integralement accentuee |
| `SAI-9a` | a chaque refus avant revelation : **la chaine de l'enfant reste visible, la reponse attendue n'est jamais nommee** - ni par un mot, ni par une marque, ni par une longueur de boite |
| `SAI-9b` | a la revelation seulement : **les deux chaines ensemble** |
| `SAI-10` | signal non textuel + texte, **jamais la couleur seule**. Deux etats, pas trois |
| `SAI-12` | aux 2e et 3e essais : **la liste de ce que l'enfant a deja ecrit**, dans l'ordre d'ecriture |
| `SEUIL-1` | l'enfant **voit ce qu'il a ecrit au moment du verdict** |
| `SEUIL-3` | il peut **effacer, retaper, relire avant d'envoyer** |
| `SEUIL-4` | le champ est **atteignable et validable au clavier seul**. Depuis l'arbitrage du 2026-08-25, ce n'est plus une precaution : le clavier est le seul geste |
| `M5` | trois essais, puis revelation |
| `M11` clause 1, `M13` | le signal ne repose jamais sur la couleur ; le point de focus est **declare par le serveur**, jamais devine par un script |
| `ADR-0017` | tout bouton porte son role, et **la sortie ne declare rien de superieur a une action du meme ecran** |

Deux conditions ne coutent rien a ce document et je le dis pour qu'on ne les cherche pas :
`SAI-6`/`SAI-7` (le contenu des listes de formes acceptees) et `SAI-2b` sont des regles d'ecriture de
contenu ; `R1` a `R6` sont des refus de ligne. Aucune n'a de forme.

---

## 2. La forme de l'ecran, etat par etat

**Un ecran de saisie n'est pas une branche de `fragments/manche.html`, c'est un second fragment**, et
je suis la lecture de `theo-archi` sur ce point (son avis, section 2.3 point 1). Ce qui s'y reprend a
l'identique est le bloc `.message-jeu` et ses enfants `.signal`, `.icone-signal`, `.verdict`,
`.explication`, `.indice` - **sans changer une seule de leurs regles**.

**Le cabinet ne s'y reprend pas.** `ADR-0030` point 1.d fait tenir la garantie que le meuble
n'atteint ni l'ecran de retour ni la fin de partie **par le seul fait que son balisage n'est ecrit
que dans `fragments/manche.html`**. Le recopier ici la casserait. Un ecran de saisie n'a d'ailleurs
pas de colonnes a encadrer : le cabinet est le meuble qui distribue des propositions, il ne dit rien
d'un enfant qui ecrit.

**`data-arrivee` n'est pas pose sur cet ecran, et ce n'est pas un oubli.** La population de
`LEcranDeJeuDeclareCommentIlEstArriveTest` est derivee du selecteur `.propositions`
(`offreDesPropositions`, meme fichier) : un ecran sans propositions en est hors par construction.
Poser un marqueur qu'aucune regle ne lit et qu'aucun garde-fou ne juge serait un drapeau mort.
**Limite a nommer plutot qu'a decouvrir** : si un mouvement est ajoute un jour a cet ecran, cette
population doit etre re-derivee dans le meme geste, sans quoi le mouvement ne sera garde par rien.

### 2.1 Manche fraiche

```
Score : 2
Écris le mot.                     <- la consigne, et c'est aussi l'etiquette du champ
Un ___ lève le doigt.
[  champ vide, 32 px, bordure bleue  ]  [ Je réponds ]
```

Rien d'autre. **Le jeu ne parle pas tant que l'enfant n'a rien envoye** : pas de message, pas de
consigne d'usage, pas de texte d'aide. C'est la meme regle qu'aujourd'hui sur la manche a boutons.

### 2.2 Premier refus

Le champ **garde ce que l'enfant a ecrit**, modifiable. Sous lui, le bloc de retour : croix, et une
phrase qui dit que ce n'est pas ca **sans nommer la reponse**. `SAI-9a` est tenue par le champ
lui-meme - la chaine est visible, et elle est en plus corrigeable, ce que `SEUIL-3` demande.

### 2.3 Deuxieme refus

Identique, plus la **liste des tentatives deja depensees**, sous le verdict, **barrees**. La tentative
que l'enfant peut encore corriger est dans le champ ; les depensees sont dans la liste. **Aucune
chaine n'apparait aux deux endroits a la fois** - c'est ce qui rend la liste lisible : ce qui y figure
est fini, ce qui est dans le champ ne l'est pas.

Le signe est celui de `M9` : un mot barre, la marque que `nadia-prof` a deja arbitree (condition F16)
pour dire *"tu as deja essaye ca"* sur une proposition pressee. **Meme sens, meme signe, deuxieme
mecanique.** C'est le seul endroit de cette proposition ou je reprends une forme deja jugee plutot
que d'en proposer une neuve, et c'est deliberement.

### 2.4 Revelation, apres le troisieme refus

**Le champ disparait** et un seul bouton reste, `Continuer`. Motif : la manche est resolue, il n'y a
plus rien a ecrire, et un champ qui subsisterait - meme desactive - resterait une zone que l'enfant
tape sans effet. Une seule action possible a la fois.

Le bloc de retour porte alors, dans cet ordre : la croix et la phrase, **la chaine jugee**, puis
**la forme de reference**, puis la liste des trois tentatives barrees.

**La forme de reference est le plus gros texte de la boite (32 px), la chaine de l'enfant reste a
20 px.** Ce que l'enfant doit lire en premier a ce moment est la bonne reponse, pas son erreur. Le
patron est celui de `.mot-designe` dans `.enonce` (`ADR-0062`) : un `<span>` plus grand a l'interieur
d'un paragraphe a la taille courante. Trois canaux separent les deux chaines - la taille, la
position, et le mot qui les introduit.

### 2.5 Reussite

Croix remplacee par la coche, phrase courte, et la forme de reference (`SAI-8`). Sur un item en mode
strict, la **chaine jugee** est montree en plus (`SAI-2c`).

### 2.6 Ce que je ne decide pas dans cette section

Les phrases. *"Ce n'est pas ça. Corrige et réessaie."*, *"Tu as déjà écrit :"*, *"On écrit :"*,
*"Continuer"*, *"Je réponds"* sont des **candidats**. `nadia-prof` seule arbitre ce qu'un enfant de
six ans lit, et elle a deja ecrit que `SAI-8` et `SAI-9` sont des proprietes dont elle reste la seule
garde. Deux points meritent qu'elle les tranche explicitement plutot qu'implicitement :

1. **Trois lignes barrees plus la reponse, sur le meme ecran de revelation, est-ce trop pour un
   enfant de CP ?** Une variante existe : ne montrer a la revelation que la derniere tentative et la
   reponse, la liste complete restant reservee aux essais 2 et 3. Je la mesure aussi (section 5.3) ;
   je ne la recommande pas, mais je ne veux pas qu'elle soit ecartee sans avoir ete vue.
2. **Le mot de l'action.** *"Je réponds"* est le candidat, parce que c'est une phrase d'enfant et non
   un verbe d'interface ; *"Valider"* et *"Envoyer"* sont les deux autres formes usuelles et sont
   plus abstraites. Aucune coche sur ce bouton : la coche est deja le signe du verdict juste, la
   mettre sur le bouton lui donnerait deux sens.

---

## 3. Le balisage propose

Ecrit pour `adapters/adapter-web/src/main/resources/templates/fragments/saisie.html`. Les
commentaires de gabarit ne sont pas recopies ici pour rester lisible ; le lot 4 les ecrira sur le
modele des quatre fragments existants.

```html
<div th:fragment="saisie" class="ecran-de-jeu" th:attr="data-focus=${focus}">
    <p class="score" th:text="'Score : ' + ${score.reussites()}"></p>

    <!-- L'invite EST la consigne : le meme texte, promu en etiquette du champ. Zero texte de plus
         a l'ecran, et le champ recoit un nom accessible reel. -->
    <label class="consigne" for="reponse-ecrite" th:text="${consigne}"></label>
    <p class="texte-support" th:if="${texteSupport != null}" th:text="${texteSupport}"></p>
    <p class="enonce" tabindex="-1"><th:block th:each="segment : ${segmentsEnonce}"><span th:if="${segment.designe()}" class="mot-designe" th:text="${segment.texte()}"></span><th:block th:unless="${segment.designe()}" th:text="${segment.texte()}"></th:block></th:block></p>

    <form class="saisie" th:unless="${mancheResolue}"
          th:attr="hx-post=@{'/parties/' + ${jeuId} + '/reponses'}"
          hx-target="#jeu" hx-swap="innerHTML">
        <input class="champ-reponse" id="reponse-ecrite" name="reponseEcrite" type="text"
               data-role="action" autocomplete="off"
               th:attr="inputmode=${modeDeClavier}, data-focus-cible=${focusSurLeChamp}"
               th:value="${brouillon}">
        <button type="submit" class="btn valider" data-role="action">Je réponds</button>
    </form>

    <button type="button" class="btn continuer-manche" data-role="action" th:if="${mancheResolue}"
            th:attr="hx-post=@{'/parties/' + ${jeuId} + '/manche'}, data-focus-cible=${focusSurLaSuite}"
            hx-target="#jeu" hx-swap="innerHTML">Continuer</button>

    <div class="message-jeu" role="status" th:if="${message != null}">
        <div class="signal" th:classappend="${signalJuste} ? 'est-juste' : 'est-faux'">
            <span class="icone-signal" aria-hidden="true"></span>
            <p class="verdict" th:text="${message}"></p>
        </div>
        <p class="chaine-jugee" th:if="${chaineJugee != null}"
           th:text="'Tu as écrit : ' + ${chaineJugee} + '.'"></p>
        <p class="forme-de-reference" th:if="${formeDeReference != null}">On écrit :
            <span class="forme-attendue" th:text="${formeDeReference}"></span></p>
        <ul class="tentatives" th:unless="${tentativesPassees.isEmpty()}">
            <li class="tentative-passee" th:each="tentative : ${tentativesPassees}"
                th:text="${tentative}"></li>
        </ul>
        <p class="explication" th:if="${explication != null}" th:text="${explication}"></p>
        <p class="indice" th:if="${indice != null}" th:text="${indice}"></p>
    </div>
</div>
```

### 3.1 Les huit points de ce balisage qui ne sont pas de la mise en page

1. **`<label class="consigne" for="reponse-ecrite">`** - la consigne devient l'etiquette du champ.
   Un `<input>` sans nom accessible est un defaut ; une etiquette de plus serait un second texte
   disant ce que la consigne dit deja, sur un ecran destine a un enfant qui lit peu. Ceci est la
   seule solution que je connaisse qui donne les deux : un nom accessible reel et **zero mot de
   plus**. Consequence : le libelle de l'invite est celui de la consigne du paquet, donc **il est
   ecrit par `nadia-prof`**, pas par le gabarit.
2. **Aucun `placeholder`.** Un texte fantome dans le champ est gris, donc sous le contraste ; il
   disparait des la premiere lettre, donc au moment ou l'enfant en aurait besoin ; et il double la
   consigne. Je le refuse explicitement pour qu'il ne soit pas ajoute par reflexe au lot 4.
3. **`type="text"`, jamais `type="number"`**, meme sur un paquet de calcul. `type="number"` refuse
   ou reformate certaines frappes sans que ni l'enfant ni le serveur ne le sachent - c'est
   exactement l'ecart *"ce que l'enfant a produit / ce que le serveur a recu"* que `nadia-prof` a
   nomme le 2026-08-25 comme un angle mort assume. Ne pas l'aggraver a un endroit ou le choix nous
   appartient. Le clavier numerique s'obtient par `inputmode`, sans ce cout.
4. **`inputmode` vient du modele (`${modeDeClavier}`), jamais d'une deduction sur la matiere.** Voir
   la dependance D2, section 8.
5. **`th:value="${brouillon}"`** - le champ est reserve avec la derniere tentative. Voir section 4.
6. **La liste ne contient pas la tentative du champ** (`tentativesPassees`, pas `tentatives`). Voir
   section 4.
7. **`.chaine-jugee` est un element distinct, pas une portion de la phrase de verdict.** Ce n'est pas
   du gout : `SEUIL-1` et `SAI-2c` deviennent testables structurellement (*"la chaine jugee est a
   l'ecran"*) alors qu'une chaine noyee dans un texte de modele ne l'est pas. A la revelation, le
   champ a disparu ; c'est le seul endroit qui porte alors ce que `SEUIL-1` exige.
8. **`autocomplete="off"`, et je nomme la limite.** Il ne desactive **pas** la correction
   automatique du clavier - l'utilisatrice a accepte cette correction le 2026-08-25 et `nadia-prof`
   a ecrit qu'elle ne demandait aucune neutralisation technique. Ce que cet attribut evite est la
   liste des valeurs deja tapees ailleurs, que le navigateur affiche **par-dessus l'ecran de jeu**
   et qui peut souffler une reponse. C'est un motif de forme, pas un motif de bareme. **Si
   `nadia-prof` ou l'utilisatrice le lisent comme une neutralisation rentree par la bande, il se
   retire sans rien changer d'autre.**

---

## 4. Ce qui se passe apres une tentative fausse

C'est le point ou la liste ordonnee d'`ADR-0058` (amendement 1) se voit, et il porte une decision que
je ne peux pas prendre seule.

### 4.1 Ce que la liste ordonnee implique a l'ecran

`ADR-0058` fait porter a la session **`List<String> tentatives`, dans l'ordre d'ecriture**, et
`derniereTentative()` devient une propriete calculee. A l'ecran, cela donne **au plus deux chaines
anterieures** (`ESSAIS_AVANT_REVELATION = 3`), plus celle du champ.

### 4.2 Les deux variantes, et je recommande la premiere

| | **Variante A - le champ est reserve** *(recommandee)* | **Variante B - le champ est vide** |
|---|---|---|
| Le champ apres un refus | contient la derniere tentative, corrigeable | vide |
| La liste sous le verdict | les tentatives **anterieures** seulement | **toutes** les tentatives |
| `SAI-9a` | tenue par le champ, et la chaine y est **corrigeable** - le mot exact de l'amendement du 2026-08-24 : *"pour qu'il le corrige plutot que de retaper a l'aveugle"* | tenue par la liste, mais l'enfant retape tout |
| `SAI-12` | tenue : au 3e essai il voit le 1er dans la liste et le 2e dans le champ | tenue plus franchement : tout est dans une liste, au meme endroit, avec le meme statut |
| `SEUIL-3` | servie au mieux : effacer une lettre suffit | l'enfant repart de zero a chaque essai |
| Rien n'est affiche deux fois | oui | oui |
| **Le risque, nomme** | la meme chaine est **a un appui d'etre renvoyee identique**, et `ADR-0058` confirme qu'une chaine retapee a l'identique **consomme un essai** | pas de ce risque ; en revanche un enfant de CP peut retaper la meme faute sans s'en apercevoir, ce qui est exactement ce que `SAI-12` existe pour eviter |

**Je recommande A** parce que la phrase qui fonde `SAI-9a` dit *corriger*, et qu'un champ vide ne se
corrige pas. **Le risque de A est reel et je ne le minimise pas** : il n'existe aucune parade dans
mon perimetre - deselectionner le texte, placer le curseur en fin de chaine sont les comportements
par defaut et ne suffisent pas a empecher un appui immediat.

**C'est un jugement sur ce qu'un enfant fait, donc c'est celui de `nadia-prof`, pas le mien.** Les
deux variantes different par un seul attribut de modele (`brouillon` present ou absent) et par le
contenu d'une liste : basculer de A a B coute une ligne au lot 4 tant que la decision arrive avant la
fusion.

---

## 5. Les regles de feuille proposees, et ce qu'elles donnent en pixels

### 5.1 Les regles

Ecrites pour `adapters/adapter-web/src/main/resources/static/css/recreo.css`. Aucune couleur neuve :
toutes viennent de la palette nommee de `:root` (`S2-2`).

```css
.saisie {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    align-items: center;
    gap: 1rem;
    margin: 0.75rem 0;
    padding: 0;
}

.champ-reponse {
    flex: 1 1 12rem;
    max-width: 24rem;
    min-height: 3.5rem;
    min-width: 2.75rem;
    padding: 0.5rem 1.25rem;
    font-size: 2rem;
    font-weight: bold;
    color: var(--recreo-texte);
    background-color: var(--recreo-accent-texte);
    border: 0.125rem solid var(--recreo-accent);
    border-radius: 0.5rem;
}

.tentatives {
    display: flex;
    flex-wrap: wrap;
    gap: 0.25rem 1rem;
    margin: 0.25rem 0 0;
    padding: 0;
    font-size: 1.25rem;
    list-style: none;
}

.tentative-passee {
    text-decoration: line-through;
    text-decoration-color: var(--recreo-texte);
    text-decoration-thickness: 0.1875rem;
    text-decoration-skip-ink: none;
}

.chaine-jugee,
.forme-de-reference {
    font-size: 1.25rem;
    color: var(--recreo-texte);
    margin: 0.25rem 0 0;
}

.forme-attendue {
    font-size: 2rem;
    font-weight: bold;
}
```

Plus **deux ajouts de selecteur a des blocs existants**, jamais un habillage a part :
`.valider` et `.continuer-manche` rejoignent les cinq boutons pleins (le bloc de repos, le bloc
d'etats neutres d'`ADR-0046`, le bloc de taille de police 1,25rem et le bloc d'anneau de focus de
`S17-1`), et `.champ-reponse` rejoint le seul bloc d'anneau de focus.

### 5.2 Les quatre choix de forme qui ne sont pas des gouts

1. **Le champ est a 2 rem**, la meme taille que `.enonce` et que le plancher general de
   `.proposition`. C'est le texte que l'enfant produit et relit : il ne peut pas etre plus petit que
   le bouton qui le valide (1,25 rem) ni que la sortie d'en-tete (1,25 rem).
2. **La largeur du champ ne depend jamais de la reponse attendue.** Un champ taille sur la reponse
   dirait a l'enfant combien de caracteres il doit ecrire - un second indice, gratuit, que rien dans
   le contenu n'a decide de lui donner. `flex: 1 1 12rem; max-width: 24rem` est une largeur
   generique : elle depend de la place disponible, jamais de l'item. **C'est la clause qui rend
   cette regle valable pour une mecanique et non pour un contenu**, et c'est la premiere chose que
   je verifierais si quelqu'un proposait de la changer.
3. **Le champ est blanc a bord bleu, jamais un aplat.** Les cinq boutons pleins de l'application sont
   des aplats ; les propositions du bandit manchot sont des aplats pastel. Une boite blanche cernee
   ne ressemble a aucun d'eux : elle se lit comme un endroit ou l'on ecrit, pas comme un endroit sur
   lequel on appuie.
4. **Le plafond de 24 rem** existe pour qu'un grand ecran ne rende pas un champ-bandeau de 1 200 px
   pour trois chiffres. Sa valeur est mesuree, pas choisie : voir 5.3.

### 5.3 Les chiffres, avec leur methode

**Methode.** Vehicule : `prototype-ines-ecran-de-saisie.html` (commite a cote de ce fichier), qui
charge **les deux feuilles reelles** de l'application par chemin relatif, dans l'ordre d'`ADR-0014`
(tierce puis maison), recopie la coquille de `templates/accueil.html` (en-tete, `#jeu.container
.zone-jeu`) et porte les regles candidates dans un `<style>` place apres la feuille maison - donc
soumises aux memes calculs de specificite et d'ordre qu'une fois versees. Outil :
`squad/outils/ines-design/mesurer-ecran-de-saisie.py`, navigateur reel pilote par CDP, qui importe
les juges purs de `squad/outils/nadia-prof/balayage-largeur-rangee.py` (port, viewport lu et non
suppose, un seul etat visible a la fois) plutot que de les recopier. Releve complet :
`squad/conformite/releves-s24/s24-ines-ecran-de-saisie-dix-largeurs-551.json`, 80 mesures.

**Ce que ces chiffres ne prouvent pas, ecrit avant qu'on me le reproche** : que l'application servie
rendra ces boites. Ils prouvent que **ces regles, sur ces feuilles, donnent ces pixels**. Le releve
refait sur le jar, une fois le lot 4 livre, reste du - et il reste a `nadia-prof`.

**Les boites, a la reference du produit (744 x 551, `DEC-0040`) :**

| Element | Boite rendue | Police | Hauteur minimale | Rembourrage |
|---|---|---|---|---|
| `.champ-reponse` | 384,00 x 68,00 px | 32 px | 56 px | 8 / 20 px |
| `.valider` | 143,86 x 56,00 px | 20 px | 44 px | 12 / 20 px |
| `.continuer-manche` | 135,27 x 56,00 px | 20 px | 44 px | 12 / 20 px |
| `.lien-accueil` (la sortie) | 108,83 x 50,00 px | 20 px | 44 px | 8 / 17,6 px |
| `.icone-signal` | 48,00 x 48,00 px | - | - | - |
| `.forme-attendue` | 78,06 x 43,00 px | 32 px | - | - |

Le champ est **la plus haute boite de l'ecran** (68 px contre 56 px pour le bouton qui le valide et
50 px pour la sortie), et le seul element a 32 px avec l'enonce et la forme de reference.

**Le bas de l'ecran, aux dix largeurs, hauteur 551 px** (le pli de `DEC-0040`) :

| Largeur | fraiche | echec 1 | echec 2 | revelation | reussite | reussite stricte | rang d'accents |
|---|---|---|---|---|---|---|---|
| 360 | 444 | 548 | **582** | 536 | 468 | 450 | **580** |
| 390 | 372 | 476 | 510 | 536 | 468 | 450 | 444 |
| 576 a 1440 | 314 | 390 | 424 | 478 | 410 | 392 | 386 |

**Lecture.** **A partir de 576 px, tous les etats tiennent au-dessus du pli**, avec 73 px de marge au
pire (la revelation, a 478 px) - et la valeur ne bouge plus d'une largeur a l'autre jusqu'a 1 440 px.
Les deux valeurs en gras sont sous le pli et sont **a 360 px seulement**, c'est-a-dire **hors du
perimetre de `DEC-0040`**, qui a sorti le telephone de la cible. Je les publie quand meme : elles
disent ou est la marge, et le repli du bouton `Je réponds` sur une seconde ligne se produit entre
360 et 390 px.

**Ce que cette table ne dit pas, et il faut le lire avec elle** : l'enonce du prototype tient sur
**une ligne rendue** a toutes ces largeurs. Un enonce plus long en prendrait deux ou trois et
descendrait tout le bloc d'autant - c'est le budget vertical deja connu de ce depot (`SYN-16`,
`DEC-0043`), qui n'appartient pas a cette proposition et que **je ne rouvre pas**. Ce que ces
chiffres etablissent est le cout **propre** de l'ecran de saisie : 424 px au deuxieme essai contre
478 px a la revelation, sur un enonce d'une ligne.

**Le rembourrage lateral, verifie aux dix largeurs** - la lecon de s05 : le bord gauche du champ
vaut exactement le bord gauche de `.entete-contenu` **plus 12 px** (le rembourrage de `.container`),
a chacune des dix largeurs. Aucune largeur ne fait partir le champ du bord de l'ecran pendant que
l'en-tete garde le sien.

**Combien de caracteres le champ montre**, a 744 px : sa largeur interieure est de **340,00 px**, et
30 caracteres `a` a 32 px en mesurent 516,56 - soit **environ 19 caracteres visibles a la fois**.
Mesure sur des chaines temoins : `205` (55,22 px), `élève` (78,06), `elleve` (87,16),
`deuxcentcinq` (201,59), `deux cent cinq` (219,92), `quatre-vingt-douze` (292,64) tiennent ;
**`trois cent soixante-quinze` (389,77 px) ne tient pas** et defile hors de vue.
**Je route ce fait plutot que d'elargir le champ** (l'elargir rendrait le champ-bandeau que le
plafond evite, et ne reglerait rien sous 744 px) : voir la dependance D4, section 8.

**La variante de la section 2.6** (revelation sans la liste complete) coute **34 px de moins** en
hauteur : l'ecran finirait au bas de la forme de reference, a 444 px au lieu de 478 px a la
reference. Les deux tiennent au-dessus du pli partout des 576 px ; **la hauteur ne departage donc
pas ces deux variantes**, seule `nadia-prof` le peut.

---

## 6. La lettre accentuee sur tablette - le point ouvert depuis le 2026-08-24

`nadia-prof` route cette question depuis six jours et elle l'a elargie deux fois. Elle est adressee a
l'utilisatrice ; **je l'instruis, je ne la tranche pas.**

### 6.1 Ce que je peux etablir, et ce que je ne peux pas

**Aucun agent de cette squad n'a l'appareil**, et je n'en ai pas davantage. Tout ce qui suit sur le
comportement reel d'un clavier tactile est un **raisonnement**, jamais un releve, et je marque
l'endroit exact ou cela compte plutot que de le noyer.

Ce qui est **etabli sur documents de ce depot**, pas sur un appareil :

- `DEC-0040` vise **l'ordinateur et la tablette**, et l'arbitrage du 2026-08-25 dit *"uniquement par
  le clavier"* sans dire lequel ;
- sur un clavier **physique** francais, `é è à ù ç` sont des touches directes ; `â ê î ô û ë ï` sont
  des touches mortes, donc deux appuis ; les majuscules accentuees n'ont pas de touche ;
- sur un clavier **virtuel**, la disposition francaise usuelle ne montre pas les lettres accentuees
  sur le plan primaire : elles s'obtiennent par un **appui maintenu** sur la lettre de base, qui
  ouvre une pastille de variantes, puis un **glissement du doigt et un relachement** sur la bonne. Ce
  n'est pas un appui, c'est un geste compose, et rien a l'ecran n'annonce que la pastille existe. Les
  dispositions varient d'un systeme et d'un clavier tiers a l'autre, et je ne sais pas dans quelles
  proportions.

### 6.2 Le point que personne n'a encore ecrit, et qui reduit la question

**La question ne pese pas partout : elle pese exactement la ou le mode strict est declare.**

Sur un paquet **tolerant** - le defaut, `SAI-1` - une reponse sans accent est **juste**. L'enfant n'a
donc jamais besoin de produire un accent, quel que soit son appareil ; le cout du geste est
pedagogiquement neutralise. Sur un paquet **strict** au titre de `SAI-2a`, l'accent decide du
verdict. Et `SAI-2b` a restreint le mode strict aux fautes qui sont **elles-memes des mots francais
valides** : `a`/`à`, `ou`/`où`, `la`/`là`, `sur`/`sûr`. **Ce sont precisement des lettres accentuees
que le clavier virtuel place derriere un appui maintenu.** L'intersection n'est pas un cas de bord :
c'est la famille entiere pour laquelle le mode strict a ete ecrit.

Consequence pour la forme, et c'est la seule que je propose : **tout dispositif d'aide a l'accent ne
doit exister que sur un item en mode strict**. Sur les autres, ce serait des boites de plus sur
l'ecran d'un enfant qui lit peu, pour un geste dont il n'a pas besoin. Et cela ne demande **aucune
donnee de contenu neuve** : le mode strict est deja declare, par `SAI-2a`, au niveau de l'item.

### 6.3 Trois options, avec ce que chacune coute

**Option 1 - ne rien ajouter, et fermer la question par le contenu.** `SAI-1` protege deja tous les
paquets tolerants. Il resterait a decider si un paquet **strict** peut etre servi a une tablette.
**Cout de rendu : zero.** Ce n'est pas une option de design, c'est une option de perimetre de
contenu : elle appartient a `camille-po` et `nadia-prof`, et je la nomme d'abord parce que c'est la
moins chere et qu'on l'oublie quand on cherche une solution technique.

**Option 2 - un rang de touches accentuees, rendu par le produit, sur les seuls items stricts.** Six
boutons `é è à ù ê ç` sous le champ, dans le meme formulaire, chacun renvoyant au serveur ce que
l'enfant a deja ecrit ; le serveur rend le champ complete de la lettre. **Aucun JavaScript** : c'est
du HTML dans un formulaire htmx, donc `ADR-0008` est servi et non contourne.
*Mesure, meme methode qu'a la section 5.3* : le rang coute **72 px de hauteur** (386 px de bas
d'ecran contre 314 px sans lui, a toute largeur des 576 px), tient **sur une seule ligne des 390 px**
et passe a deux lignes a 360 px, chaque touche mesurant 52,17 x 56,00 px - **au-dessus du plancher de
44 px**. A 360 px il tombe sous le pli (580 px), la seule largeur ou il le fait, et elle est hors
`DEC-0040`.
*Ce que cette option coute et que je n'ai pas le droit de cacher* :
- une **route de plus** et un **brouillon en session**, donc du contrat de controleur : ce n'est pas
  ma piece, c'est celle de `theo-archi` et du lot ;
- un **aller-retour serveur par lettre accentuee** ;
- la position du curseur est perdue : la lettre s'ajoute a la fin, pas la ou l'enfant ecrivait ;
- **le rembourrage lateral des touches doit valoir au moins 1,1 rem**, et pas la valeur compacte
  qu'on choisirait spontanement : `ADR-0017` compare les deux axes du raccourci `padding`, et la
  sortie d'en-tete `.lien-accueil` declare `0.5rem 1.1rem`. Une touche a `0.75rem` de rembourrage
  lateral ferait declarer a la sortie plus qu'a une action du meme ecran, donc rouge - ou obligerait
  a un **quatrieme `data-role`**, ce qui est un changement du vocabulaire d'`ADR-0017` et appartient
  a `theo-archi`. Le prototype est mesure a `0.5rem 1.1rem`, la forme qui ne demande ni l'un ni
  l'autre.

**Option 3 - repondre a la question de mesure.** Faire tenir a un adulte, sur la tablette reellement
visee, le geste de l'accent et le comportement de la correction automatique, et l'ecrire. C'est ce
que `nadia-prof` demande depuis le 2026-08-24 et que ni elle ni moi ne pouvons produire. **Elle
n'exclut aucune des deux autres** : c'est elle qui dit si l'option 2 est necessaire ou si l'option 1
suffit.

### 6.4 Ce que je recommande, et ce que je refuse de proposer

**Je recommande de ne pas cabler l'option 2 dans s24.** Motif : elle n'a de valeur que sur un paquet
strict, **aucun paquet de saisie n'existe encore**, et `nadia-prof` arbitre en ce moment meme le
contenu du premier - dont rien ne dit qu'il sera strict. Cabler d'avance un rang de touches pour un
contenu qui n'est pas ecrit est la forme exacte de l'abstraction ecrite pour un usage imagine. **La
forme est instruite, mesuree et disponible** ; elle se cable le jour ou un item strict entre au
catalogue, et pas avant.

**Ce que je ne propose pas, et je le dis pour qu'on puisse le verifier** : aucune neutralisation
technique du clavier. Ni `spellcheck="false"`, ni `autocorrect="off"`, ni `autocapitalize`. La
correction automatique a ete **acceptee** par l'utilisatrice le 2026-08-25 en connaissance de sa
consequence, et `nadia-prof` a ecrit qu'elle ne la reintroduisait pas par la bande. Je fais de meme.

---

## 7. Accessibilite visuelle

### 7.1 Contraste

Methode : formule de luminance relative, appliquee aux valeurs declarees dans la feuille, calculee
et non estimee.

| Paire | Rapport |
|---|---|
| texte du champ `#1c2733` / fond du champ `#ffffff` | **15,14:1** |
| bordure du champ `#2e6f9e` / fond de page `#f4f7fb` | **5,03:1** |
| bordure du champ `#2e6f9e` / fond du champ `#ffffff` | **5,41:1** |
| anneau de focus `#1c2733` / fond du champ `#ffffff` | **15,14:1** |
| texte de `.valider` `#ffffff` / fond `#2e6f9e` | **5,41:1** |
| tentative barree `#1c2733` / fond de page `#f4f7fb` | **14,09:1** |
| forme de reference `#1c2733` / fond de page `#f4f7fb` | **14,09:1** |
| touche accentuee `#1c2733` / fond `#ffffff` | **15,14:1** |

Aucune valeur sous 4,5:1 pour du texte, aucune sous 3:1 pour une frontiere. Le signal juste/faux
garde ses propres couleurs, deja mesurees en `S1-6` et inchangees ici (coche 4,56:1, croix 4,92:1
sur leurs aplats).

### 7.2 Rien ne repose sur la couleur seule

- **verdict** : coche ou croix (`M11` clause 1, `SAI-10`), inchange ;
- **tentative depensee** : un mot **barre**, jamais une teinte - le trait est une forme, il survit a
  toute cecite aux couleurs, et c'est le signe deja arbitre par `F16` pour ce sens ;
- **forme de reference** : distinguee de la chaine de l'enfant par la **taille** (32 px contre
  20 px), la **position** et le **mot** qui l'introduit. Trois canaux, aucun colore ;
- **champ** : distingue des boutons par sa **forme** (blanc cerne contre aplat), pas par sa teinte.

### 7.3 Tailles et cibles pressables

| Element | Police | Cible | Plancher |
|---|---|---|---|
| `.champ-reponse` | 2 rem | 68 px de haut rendus, `min-height` 3,5 rem | 1,25 rem / 44 px |
| `.valider`, `.continuer-manche` | 1,25 rem | 56 px rendus, `min-height` 2,75 rem | idem |
| `.tentatives`, `.chaine-jugee`, `.forme-de-reference` | 1,25 rem | - | plancher de lisibilite |
| `.forme-attendue` | 2 rem | - | - |

Le champ est **plus grand que tout element d'interaction secondaire de l'ecran** - c'est la regle de
mon mandat, et ici elle est litterale : 68 px contre 56 px pour le bouton de validation et 50 px pour
la sortie.

**`ADR-0017`, verifie composante par composante** contre la sortie `.lien-accueil`
(1,25 rem / 2,75 rem / 0,5 rem / 1,1 rem) : le champ declare 2 / 3,5 / 0,5 / 1,25 ; `.valider` et
`.continuer-manche` declarent 1,25 / 2,75 / 0,75 / 1,25 ; la touche accentuee declare 1,5 / 2,75 /
0,5 / 1,1. **Aucune composante de la sortie ne depasse celle d'une action du meme ecran**, aux
egalites pres, qui sont permises.

**Le rembourrage lateral du champ est a 1,25 rem et pas moins pour cette raison exacte**, pas par
gout. C'est le genre de valeur qu'un lot resserre en croyant gagner de la place et qui rend rouge un
garde-fou dont il ne voit pas le rapport.

### 7.4 « L'extension des pressables » de `theo-archi` : sa lecture, et ce qu'elle ne couvre pas

Sa lecture est **juste et necessaire**, et je la confirme sur l'arbre du jour : quatre garde-fous
derivent leurs pressables de `button, a[href]` - `TailleDesTextesEtBoutonsTest:489`,
`LActionNeDeclarePasMoinsQueLaSortieTest:348`, `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest:356`,
`LHabillageNeRogneJamaisUnElementPressableTest:88` (constante `PRESSABLES`). Sans extension, un champ
sous 1,25 rem ou sous 44 px passerait une CI verte.

**Elle ne couvre pas trois choses dont j'ai besoin, et deux d'entre elles font rougir un garde-fou
existant plutot que d'en laisser un muet.**

1. **`LHabillageResteImmobileTest` est un cinquieme consommateur de la meme constante**
   (`import static ...LHabillageNeRogneJamaisUnElementPressableTest.PRESSABLES`, ligne 19, employee
   ligne 133), et il derive l'habillage **par complementaire** : toute classe posee dans un gabarit,
   **moins** toute classe portee par un pressable. Tant que `input` n'est pas dans le selecteur,
   `.champ-reponse` est de l'**habillage** - et la regle `.champ-reponse:focus-visible` que la
   section 5.1 propose porte une pseudo-classe interactive sur une classe d'habillage, ce que ce
   garde-fou refuse nommement. **L'extension est donc obligatoire dans les deux sens** : sans elle,
   un garde-fou reste muet sur les tailles **et** un autre devient rouge sur l'anneau de focus. Le
   second se voit tout de suite ; c'est le premier qui est dangereux, mais les deux appellent le
   meme geste, dans le meme commit.
2. **Etendre `LActionNeDeclarePasMoinsQueLaSortieTest` rend `data-role` obligatoire sur le champ.**
   Ce test refuse tout element de sa population qui ne porte pas de role connu (`role(...)`, meme
   fichier). Le champ entre donc dans la comparaison action/sortie, avec les consequences chiffrees
   en 7.3 - dont le rembourrage lateral de 1,25 rem, qui n'a rien d'evident et se paierait en rouge.
   Le balisage de la section 3 porte `data-role="action"` sur le champ pour cette raison.
3. **`AucunEtatDePressableNEstAbandonneAuTiersTest` (`ADR-0046`) herite de la meme population**, sa
   liste de boutons rendus venant de `TailleDesTextesEtBoutonsTest.etatsRendus`. **Mesure, avec sa
   methode** : balayage des couples selecteur-corps de `static/vendor/bootstrap.min.css`, en
   retenant tout selecteur qui vise un `input` nu, un `[type=...]` ou un selecteur universel.
   **13 selecteurs nomment `input` ou un `[type=...]`** ; les seules regles qui s'appliquent
   reellement a un `<input type="text">` **sans classe tierce** sont `* { box-sizing: border-box }`
   et `button,input,optgroup,select,textarea { margin:0; font-family:inherit; font-size:inherit;
   line-height:inherit }` - **aucune des deux ne porte d'etat interactif**, et aucune regle de cette
   feuille ne porte `:hover`, `:active` ni `:focus-visible` sur un tel element. Aucune
   exigence neuve ne nait donc sur `.champ-reponse` a ces trois etats. C'est
   une bonne nouvelle mesuree, pas supposee - et elle ne tiendra plus le jour d'une montee de version
   de la feuille vendorisee, ce que cette derivation fera apparaitre d'elle-meme.

**Un fait que le lot 4 doit voir avant de le decouvrir : le champ portera deux marques de focus.**
Mesure sur le prototype, focus systeme force (`Emulation.setFocusEmulationEnabled`), a 744 px :
`:focus-visible` s'applique (`focusVisible: true`, `document.activeElement` est bien le champ), la
regle maison peint l'anneau interieur (`rgb(28,39,51) 0 0 0 3px inset`) **et** le contour par defaut
du navigateur reste (`outline-style: auto`, `1px`, `rgb(16,16,16)`). Sur les cinq boutons pleins,
`.btn:focus-visible` de la feuille vendorisee supprime ce contour ; **sur un `input` sans classe
tierce, rien ne le supprime**. Trois issues : garder les deux (le champ est le seul element ainsi
marque), retirer le contour du navigateur au profit du seul anneau maison, ou ne pas declarer
l'anneau maison sur le champ. **Je recommande de garder les deux et de ne rien declarer de plus** -
un indicateur de focus surnumeraire n'a jamais fait de mal a un enfant, et retirer un `outline`
natif est le genre de geste qu'on regrette. Mais c'est une difference visible d'un ecran a l'autre,
donc `nadia-prof` doit la voir sur le rendu avant de prononcer quoi que ce soit.

*Note de reference* : le premier releve de focus que ce script a produit disait `focusVisible: false`
et `boxShadow: none`, parce qu'une page sans tete n'a pas le focus systeme. Il decrivait l'etat de
repos en se presentant comme un releve de focus. Le script porte depuis deux temoins
(`document.activeElement`, `document.hasFocus()`) qui rendent ce silence impossible.

---

## 8. Les dependances que je laisse ouvertes plutot que de supposer une reponse

| | Ce qui manque | A qui |
|---|---|---|
| **D1** | **Le contenu du premier paquet de saisie** - strict ou tolerant, numerique ou alphabetique. Il decide si le rang de touches de la section 6 a une population, et si `SAI-2c` a un ecran. `nadia-prof` l'arbitre en parallele ; **je ne suppose pas sa reponse** | `nadia-prof` |
| **D2** | **Le mode de clavier de l'item** (`${modeDeClavier}`). Un clavier numerique sur une reponse chiffree supprime la chasse aux lettres et la correction automatique. Il doit venir du **contenu**, comme le mode strict, jamais d'une deduction sur la matiere ou le niveau - deviner *"c'est des maths, donc des chiffres"* est exactement ce que `SAI-2` interdit pour le mode strict, et pour le meme motif | `theo-archi` / lot 1, sous arbitrage de `nadia-prof` |
| **D3** | **Cinq attributs de modele que le controleur ne fournit pas aujourd'hui** : `tentativesPassees` (liste ordonnee), `brouillon`, `chaineJugee`, `formeDeReference`, `mancheResolue`, plus les deux valeurs de focus. Toutes derivent de ce qu'`ADR-0058` fait deja porter a la session et au verdict. **Je ne les ecris pas** : je ne touche ni un controleur ni un contrat | `sofia-sm`, pour affectation |
| **D4** | **La longueur maximale d'une reponse a ecrire.** Le champ montre environ 19 caracteres a la reference ; au-dela l'enfant perd le debut de sa reponse de vue. Elargir le champ ne regle rien (il faudrait le tailler sur la reponse, ce que 5.2 point 2 interdit) : la borne est une **regle d'ecriture de contenu**, de la famille de `SAI-6` | `nadia-prof`, `camille-po` |
| **D5** | **La variante A ou B** de la section 4 : le champ reserve ou vide apres un refus. Une ligne de difference au lot 4 si la reponse arrive avant la fusion | `nadia-prof` |
| **D6** | **Le nom de la classe `.saisie`.** Elle nomme le geste (ecrire), pas le type de la mecanique, comme `.propositions` nomme ce que l'enfant choisit. Si le lot 2 nomme la mecanique `Saisie`, la collision est nominale et `R-A` reste une regle Java - mais c'est a `theo-archi` de le confirmer plutot qu'a moi de le decreter | `theo-archi` |
| **D7** | **Le renommage du repertoire de sprint**, ce fichier et les deux artefacts qu'il cite compris | `sofia-sm` |

**Ce que je ne prononce pas** : le Done pedagogique. Cette proposition ne se valide pas contre l'avis
de `nadia-prof`, meme sur les points ou elle corrige exactement ce qu'un de ses arbitrages a demande.

---

## Porte technique

`bash outils/mvn-jdk21.sh -B clean verify` a la racine du reacteur, arbre de ce worktree :
**BUILD SUCCESS, 802 tests, 0 echec**, JDK **21.0.10** de la toolchain declaree. Perimetre : le
reacteur complet, aucun `-pl`, donc aucun `-am` a citer.

**Ce commit n'ajoute et ne retire aucun test**, et c'est attendu : il ne touche aucun module. Le
chiffre se lit contre les **807** que `theo-archi` mesure sur `s24-theo`, qui porte les cinq tests
de son lot 0 (les quatre d'`ADR-0070` et la morsure d'`ADR-0069`) ; 802 + 5 = 807. Les deux mesures
decrivent le meme arbre plus des lots disjoints.

---

## Impact

- `squad/sprints/s24-trois-paquets-et-ouverture-de-la-saisie/proposition-ines-ecran-de-saisie.md`
  (ce fichier) : cree.
- `squad/sprints/s24-trois-paquets-et-ouverture-de-la-saisie/prototype-ines-ecran-de-saisie.html` :
  cree - vehicule de mesure, jamais servi.
- `squad/outils/ines-design/mesurer-ecran-de-saisie.py` : cree.
- `squad/conformite/releves-s24/s24-ines-ecran-de-saisie-dix-largeurs-551.json` : cree, 80 mesures.
- **Aucun fichier de `domain/`, `application/`, `adapters/`, `bootstrap/` n'a change.** Aucun
  controleur, aucun contrat, aucun gabarit servi, aucune ligne de `recreo.css`.

---

## Correction du 2026-08-31 (`ines-design`, s25) - la formule de la porte technique ci-dessus

**Ce que la section "Porte technique" affirme est mal fonde, et le defaut est dans la methode, pas
dans le chiffre.** Elle ecrit *"JDK **21.0.10** de la toolchain declaree"*. `ADR-0071`, rendu le
2026-08-30 sur `s24-theo`, disqualifie exactement cette source : *"la version citee par une mesure
est celle que la machine virtuelle rapporte d'elle-meme"*, jamais celle qu'une toolchain declare -
*"ces sources disent ce qui est declare, et la declaration est exactement ce qui peut mentir"*.

`relecteur-pedagogique` a releve la phrase a la relecture de s24 (`relecture-s24.md`, ligne 194) et
**me l'a routee plutot que de la corriger** : le document est le mien, et deux agents avaient deja
refuse ce sprint-la de reecrire le document d'un autre. Il ecrit aussi que **le chiffre, lui, est
juste** - `ADR-0071` l'etablit sur le meme poste et la meme journee.

**Je ne remonte pas l'affirmation apres coup, et c'est le point.** Le 2026-08-30, rien dans le
depot ne lisait ce que la machine virtuelle des tests rapportait : le garde-fou d'`ADR-0071`
n'existait pas encore sur l'arbre que j'ai mesure. **Je ne peux donc pas dire aujourd'hui ce que ma
mesure d'hier a rapporte** - je peux seulement dire que ma phrase nommait une source qui ne prouve
rien. Reecrire "21.0.10 rapporte par la machine virtuelle" serait remplacer une affirmation sans
preuve par une autre, et c'est le defaut que cet `ADR` ferme.

**La formule reste donc barree, pas remplacee.** La porte technique de mon lot de s25 porte, elle,
un chiffre que le garde-fou d'`ADR-0071` a verifie a l'execution, et c'est la seule des deux qu'on
peut opposer a quelqu'un : voir `proposition-ines-palier-1-du-visuel.md`, section "Porte technique".

*Rien d'autre de ce document n'est modifie. Les chiffres de la section 5.3 sont ceux du prototype
statique de s24 ; ce que l'application servie rend depuis le lot 4 est mesure ailleurs et separement
(`squad/conformite/releves-s25/s25-ines-ecran-de-saisie-servi-dix-largeurs.json`).*
