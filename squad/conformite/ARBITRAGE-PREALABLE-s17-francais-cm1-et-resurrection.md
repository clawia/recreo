# Arbitrage prealable s17 - francais CM1 (comprehension, synonymes) et resurrection du "Bon petit mot"

- **Auteur** : nadia-prof. **Date** : 2026-08-19. **Sprint** : s17.
- **Branche** : `s17-nadia`, worktree dedie, lignee verifiee par `outils/verifier-lignee.sh`.
- **Ce que ce document est** : les **trois arbitrages prealables bloquants** que `camille-po` a
  nommes comme conditions avant tout code, sur `S17-2` (comprehension de texte), `S17-3` (synonymes)
  et `S17-4` (retour du "Bon petit mot"). **Aucun code, aucun contenu n'est ecrit tant que ces trois
  reponses ne sont pas lues.**
- **Ce que ce document n'est pas** : ni un Done pedagogique, ni un jugement sur un increment - rien
  n'est livre. Les Done seront prononces en jouant, sur les fiches de conformite que `camille-po` a
  ouvertes, et **un lot qui tiendrait toutes mes conditions peut encore etre refuse pour ce que seul
  le rendu montre**.
- **Ou il vit, et pourquoi ici** : dans un fichier a part plutot que dans les deux fiches neuves,
  parce que le lot de `camille-po` n'est pas fusionne au moment ou j'ecris - creer les memes fichiers
  de mon cote produirait un conflit sur un fichier qu'elle vient d'ouvrir. Meme forme que
  `ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md` et les deux de s13. `camille-po` y renvoie depuis
  chaque fiche quand les deux lots se rejoignent.
- **`S17-1` n'est pas traite ici** : l'indicateur de focus clavier que j'ai trouve hier attend la
  proposition de forme d'`ines-design`, et je n'anticipe pas mon arbitrage sur sa story.

## 0. Ce que j'ai mesure avant de repondre, et la commande qui l'a produit

Deux des trois arbitrages dependent d'un chiffre que personne d'autre ne peut produire aujourd'hui.
Je l'ai donc produit **avant** de trancher, plutot que de renvoyer l'equipe a une mesure a faire
apres avoir ecrit vingt items.

Jar reconstruit sur le JDK de la toolchain 21, application servie sur un port a moi, **arretee par
son PID**. Une manche reelle de "Calcul CP" (trois propositions) est prise telle que le serveur la
rend ; seul le **texte de l'enonce** y est remplace par un texte de longueur croissante, les feuilles
reellement servies etant chargees depuis l'application en cours d'execution. Une sonde posee dans la
page lit `getBoundingClientRect()` de l'enonce et de la derniere proposition, compte les lignes par
l'interligne calcule, et **ecrit ces valeurs dans la page**, que je capture ensuite :

```
outils/mesurer-rendu.sh 360 <url> <png> 640
```

**Budget vertical mesure a 360 x 640, propositions entierement au-dessus du pli** (releves dans
`squad/conformite/releves-s17/`) :

| Style du texte | Lignes | Hauteur du texte | Bas de la derniere proposition | Visible sans defiler |
|---|---|---|---|---|
| enonce actuel (interligne **48 px**) | 4 | 192 px | y = 565 | **oui** |
| enonce actuel | **5** | 240 px | y = 613 | **oui**, 27 px de marge |
| enonce actuel | 6 | 288 px | y = 661 | **non** |
| enonce actuel | 7 | 336 px | y = 709 | **non** |
| taille de la consigne (interligne **30 px**) | **7** | 210 px | y = 583 | **oui**, 57 px de marge |
| taille de la consigne | 9 | 270 px | y = 643 | **non** |

**Les deux chiffres qui commandent tout ce qui suit** : avec le style d'enonce d'aujourd'hui, le
plafond est de **5 lignes, soit environ 18 a 20 mots**. Avec un style de texte-support a la taille de
la consigne, il est de **8 lignes, soit environ 45 mots**.

**Je ne compte ni caracteres ni mots comme critere** - `DEC-0016` l'interdit nommement et a raison :
un raisonnement par lettres manque son pire cas reel. **Mon unite est la ligne rendue a 360 px**, qui
est ce que l'enfant voit. Les nombres de mots ci-dessus sont donnes pour situer, jamais comme seuil.

## 1. S17-2 - comprehension d'un texte : le principe est ACCEPTE, la matiere premiere est REFUSEE, et un critere de la fiche est incompatible avec la competence

### 1.1 La question de `camille-po`, et je commence par la relire chez moi

Elle demande si une question a choix multiple sur un texte affiche peut verifier une comprehension
reelle, ou si c'est le defaut du "Bon son" sous un autre habillage. **Elle a raison de la poser, et
elle a raison de la comparer a celle-la.** Mon motif de s02, mot pour mot :

> *"Un exercice ou 23 items sur 24 se reussissent sans la competence visee, et ou le seul item qui
> l'exige risque de desapprendre, ne couvre pas 'associer les graphemes aux phonemes'."*

Le critere n'a jamais ete "l'exercice est-il facile" : c'est **peut-on le reussir sans exercer la
competence annoncee**. Je l'applique ici a l'identique.

### 1.2 Ce que j'ai trouve dans la matiere premiere, item par item

`contenu-repris/quiz/cm1-comprehension.yml`, huit items. J'ai lu les huit et je les ai passes a deux
epreuves symetriques.

**Premiere epreuve - le texte suffit-il, sans lire la question ?** Sur les **huit items sur huit**, la
bonne reponse est une chaine que le texte contient **mot pour mot** : "sur un arbre", "elle arrose",
"il a raté son bus", "le hibou", "trois", "le vent", "chez le dentiste", "la récréation". Et sur
**quatre items sur huit**, les distracteurs n'apparaissent **nulle part** dans le texte : un enfant
n'a donc meme pas besoin de lire la phrase, il lui suffit de reperer laquelle des quatre etiquettes
figure dans le passage. **C'est l'appariement visuel de s02, transpose du grapheme au mot.**

**Seconde epreuve, celle qui compte le plus - la question suffit-elle, texte masque ?** Sur au moins
**quatre items sur huit**, la reponse se donne par la seule connaissance du monde, sans texte : un
corbeau est perche dans un arbre, le hibou est l'animal qui veille la nuit, c'est le vent qui fait
monter un cerf-volant, et sur l'item de Tom deux des trois distracteurs sont absurdes ("il s'est leve
tot", "il a couru tres vite" n'expliquent pas un retard). **La moitie du paquet ne demande pas de
texte du tout.**

**Et un fait qui precede les deux epreuves : ce ne sont pas des textes.** Chaque support est **une
seule phrase**. La ligne du referentiel dit "comprendre un **texte** narratif ou documentaire
(questions explicites et implicites, **inferences simples**)". **Aucun des huit items ne demande la
moindre inference** : les huit sont du reperage litteral dans une phrase. Le critere de `camille-po`
qui exige un tiers d'items d'inference part donc de **zero sur huit**.

**Conclusion sur la matiere premiere : REFUSEE.** Non pas "a retoucher" : les huit items sont a
refaire, et les garder comme point de depart n'aide pas - leur forme meme (une phrase, une reponse
recopiee) est le defaut. **Ce n'est pas une reprise, c'est un paquet neuf**, et le fiche doit le dire
ainsi plutot que d'annoncer "8 items deja compatibles".

### 1.3 Le principe, lui, est accepte - et voici a quelle condition

**Oui, une question a choix multiple sur un texte affiche peut verifier une comprehension reelle.**
Ce n'est pas la forme qui decide, c'est la construction des items. Une question dont la reponse
demande de **relier deux informations situees a deux endroits du texte** ne se resout ni par
appariement ni par culture generale : il faut avoir lu, et avoir compris. C'est exactement ce que le
referentiel appelle une inference simple.

**Mes conditions, et les deux premieres sont les epreuves ci-dessus retournees en regle.**

- **COMP-1 (bloquante). Texte visible, question masquee : la reponse n'est pas trouvable.** Aucun item
  ne doit pouvoir se resoudre en cherchant laquelle des propositions figure dans le texte.
  **Consequence executable : toutes les propositions d'un item emploient des mots presents dans le
  texte**, ou aucune ne le fait ; jamais une seule. **Verification par un tiers** : masquer la
  question, essayer de repondre, item par item ; aucun item ne doit se laisser faire.
- **COMP-2 (bloquante). Question et propositions visibles, texte masque : la reponse n'est pas
  trouvable.** Un enfant qui n'a pas lu le texte ne doit pas faire mieux que le hasard. **Cela
  interdit deux choses precises** : les questions dont la reponse est un fait de culture generale
  (le hibou, le vent), et les distracteurs absurdes qui s'eliminent seuls. **Verification par un
  tiers** : masquer le texte, item par item.
- **COMP-3 (bloquante). Au moins un tiers des items exigent de relier deux informations distinctes du
  texte**, et pour ceux-la la bonne reponse **n'est pas** un fragment recopie du support. Je reprends
  le critere de `camille-po` en le rendant decidable : si la reponse se lit dans une seule phrase du
  texte, l'item ne compte pas dans le tiers.
- **COMP-4 (bloquante). Le support, la question et toutes les propositions sont entierement visibles a
  360 x 640 sans defiler**, mesure par la commande du 0 sur le rendu reel, **sur la totalite des
  items du paquet et non sur un echantillon**. C'est la meme exigence M4 que les autres jeux, et c'est
  la seule condition de ce document que je devrai mesurer moi-meme.

### 1.4 Le point ou je contredis un critere de la fiche, et il vaut mieux le savoir maintenant

La fiche de `camille-po` demande le paquet **"sans gabarit ni feuille de style neuve"**. **Ma mesure
du 0 dit que ce critere et la competence ne peuvent pas tenir ensemble.** Avec le style d'enonce
actuel, le budget est de **5 lignes, environ 18 a 20 mots** : c'est une phrase et demie. On peut y
mettre un reperage litteral - c'est-a-dire exactement le defaut que je viens de refuser - mais **pas
un texte narratif portant une inference**. Un enfant ne peut pas relier deux informations qui ne
tiennent pas a l'ecran.

**Avec un style de texte-support distinct, a la taille de la consigne, le budget passe a 8 lignes,
environ 45 mots** - deux ou trois phrases, assez pour poser deux informations et demander de les
relier. **C'est etroit mais c'est un texte.**

**Donc, et c'est une condition** :

- **COMP-5 (bloquante). Le texte-support recoit un style distinct de celui de l'enonce**, plus petit,
  et **le critere "sans feuille de style neuve" tombe pour cette story**. Je ne choisis ni la taille
  ni la forme : **c'est un arbitrage de forme qui appartient a `ines-design`**, et le mecanisme a
  `theo-archi`. Je donne les deux chiffres mesures pour qu'ils travaillent sur du reel, et je poserai
  mes conditions de lisibilite sur sa proposition quand elle me sera soumise - **un texte de
  comprehension reste du texte que lit un enfant de CM1, et je ne l'accepterai pas a n'importe quelle
  taille**.
- **Si ce critere est maintenu tel quel, ma reponse au principe devient NON** : le paquet ne pourra
  porter que du reperage litteral, et je le refuserai a l'audit pour le motif de s02. Autant le savoir
  avant d'ecrire vingt items.

### 1.5 La limite du proxy, que je nomme pour que la matrice ne l'oublie pas

**Un item = un texte de 45 mots maximum.** La mecanique tire une manche au hasard : elle ne sait pas
poser trois questions sur un meme texte plus long. **Un enfant ne lira donc jamais, dans ce jeu, un
texte assez long pour que la comprehension d'ensemble soit en jeu** - il lira vingt fois trois
phrases. C'est une couverture **partielle** de la ligne, et la mention exacte que j'exige sur la
matrice est : **"textes courts, un texte par question, sans lecture longue"**. Ce n'est pas un defaut
du lot a venir : c'est la limite du mecanisme, et elle doit etre ecrite le jour ou la ligne bouge.

## 2. S17-3 - synonymes : le proxy est ACCEPTE sous une condition de forme, la couverture partielle est ACCEPTEE mais ne compte pas comme couverture

### 2.1 Le proxy de production - c'est la cinquieme question de cette forme, et ma regle n'a pas change

Les quatre precedentes m'ont fait accepter deux fois et refuser deux fois ; le critere est le meme
depuis s12 et il n'a rien d'une preference : **un proxy de reconnaissance tient quand il n'existe
aucun chemin vers la bonne proposition qui contourne la connaissance nommee par le verbe**. Il tombe
quand un tel chemin existe - c'est pour cela que j'ai accepte la transcription des grands nombres
("il n'y a pas d'autre chemin vers la bonne proposition") et refuse l'encadrement des fractions ("au
deuxieme item l'enfant a compris qu'il peut repondre sans lire").

**Applique aux synonymes : le proxy tient.** Pour choisir le mot qui veut dire la meme chose, il faut
connaitre le sens des deux mots ; il n'y a pas de raccourci. Un enfant qui repond juste **sait**, et
savoir est la condition d'utiliser.

**Ce que le proxy ne travaille pas, et je le nomme comme j'ai nomme la fluence orale en s12** :
**l'emploi**. Choisir un synonyme dans une liste n'est pas ecrire une phrase en le remplacant. Le
verbe du referentiel est "utiliser... pour preciser ou nuancer un propos" ; ce jeu n'aura jamais de
production libre, et la ligne ne pourra jamais etre pleinement couverte par lui.

**Mais il y a un pas a faire, et il ne coute rien : la forme proposee est plus faible qu'elle n'a
besoin de l'etre.**

- **SYN-1 (bloquante). Le mot est presente dans une phrase, jamais isole.** La question devient : dans
  cette phrase, quel mot peut remplacer le mot souligne sans changer le sens ? **Motif** : le
  referentiel dit "pour preciser ou nuancer **un propos**", et un mot seul n'est pas un propos. La
  substitution en contexte est ce qui approche le plus l'emploi que la reconnaissance permette, et
  elle reste dans le schema `type: question` sans une ligne de code neuve. **Sans SYN-1, mon accord
  sur le proxy tombe** : "Quel mot veut dire la meme chose que *rapide* ?" travaille l'association de
  mots, pas l'usage.
- **SYN-2 (bloquante). La bonne reponse est reellement substituable dans la phrase donnee, et aucun
  distracteur ne l'est.** Verifiable phrase par phrase par n'importe quel enseignant. C'est le critere
  de `camille-po`, que SYN-1 rend enfin decidable : hors contexte, "aucun distracteur n'est un
  synonyme acceptable" est une question sans reponse ferme ; dans une phrase, elle en a une.
- **SYN-3 (bloquante). Aucun item ne presente comme interchangeables deux mots qu'un enseignant
  refuserait dans cette phrase.** Deux synonymes ne le sont jamais partout - c'est le coeur de
  "preciser ou nuancer". Un paquet qui enseigne "synonyme = pareil" enseigne quelque chose de faux, et
  une erreur de fait dans un contenu scolaire est chez moi un motif de refus a elle seule.

### 2.2 La couverture partielle - acceptee, et voici sous quel statut

**Oui, ouvrir les synonymes sans les antonymes est acceptable**, et `camille-po` a bien fait de nommer
la partialite plutot que de la taire. C'est le regime que ce produit applique deja cinq fois
(additions "jusqu'a 10", homophones "les principaux", conjugaison "un temps sur trois", tables "sans
mesure d'automatisation", fraction simple sans la droite graduee).

**Mais je marque une difference avec ces cinq-la, parce qu'elle change ce que la partialite coute.**
Dans les cinq cas precedents, la moitie manquante etait un **prolongement du meme geste** - des
nombres plus grands, davantage d'homophones, un temps de plus. Ici, les antonymes ne sont pas des
synonymes plus difficiles : ils sont **l'autre operation**, celle du contraste, et c'est en opposant
les deux qu'un enfant apprend a nuancer. La ligne perd donc plus que la moitie de son volume : elle
perd un de ses deux mouvements.

**Ma reponse a la question de statut, qui depasse cette story et que `camille-po` a raison de poser.**

- **La ligne reste `en cours`, jamais `couvert-valide`**, avec la mention **"synonymes seuls,
  antonymes non couverts"**. Un Done pedagogique sur ce paquet ne la fait pas basculer.
- **Et elle ne compte pas dans le pourcentage de competences validees.** Je suis explicite parce que
  le chiffre vient d'etre redresse : **46 competences, 9 validees, 19,6 %**, la ou 31,1 % avait
  circule dans trois documents. **Une ligne a moitie servie qui entrerait dans le numerateur
  refabriquerait exactement l'ecart que `camille-po` vient de fermer.** Si le format de la matrice ne
  sait pas exprimer un demi-statut aujourd'hui, la bonne reponse n'est pas d'arrondir vers le haut :
  c'est de laisser la ligne hors du compte et d'ecrire la mention. Le format de la matrice ne
  m'appartient pas ; **ce qu'il ne doit pas produire, si**.
- **SYN-4 (non bloquante, mais datee). La moitie manquante est ecrite comme une story, pas comme une
  intention.** Les antonymes sont la moitie **la moins couteuse a produire** - toute paire
  d'adjectifs usuels en fournit une - et l'absence de matiere premiere dans `contenu-repris/` n'est
  pas une raison de la differer indefiniment. Je ne fixe pas de sprint ; je demande que la story
  existe au backlog avant la cloture de s17.

## 3. S17-4 - le retour du "Bon petit mot" : ACCEPTE en principe, sous quatre conditions

### 3.1 J'ai relu `DEC-0024` plutot que mon souvenir, et le motif n'est pas celui du contenu

**Ce qui a ete retire, c'est un ecran, pas un contenu.** `DEC-0024` le dit en clair : le contenu est
valide, l'enonce le plus long occupe **six lignes a 2 rem, environ 288 px**, et le jeu ne pouvait pas
etre rachete par le resserrement de marges qui a sauve "Petits problemes" et "Le bon verbe". Mon
propre dossier concorde : le paquet a ete juge **VALIDE SOUS RESERVE** en s04, puis a 22 items en s05,
puis a **24 items en s06 ou ma condition de volume a ete levee**.

**Je n'ai donc pas a rejuger le contenu en principe** - il est juge - **mais tout item raccourci est
un contenu modifie**, et `DEC-0024` le dit aussi. C'est de cela que mes conditions parlent.

**Et je verifie le chiffre plutot que de le citer** : le paquet porte aujourd'hui **24 items**,
l'enonce le plus long fait **97 caracteres**, la mediane **40**, et **chaque item n'a que deux
propositions**.

### 3.2 Le risque que le raccourcissement fait courir, et que personne n'a nomme

**Raccourcir la phrase d'un item d'homophones peut supprimer l'indice qui rend la reponse
determinable.** C'est le coeur de ce jeu : ce qui permet de choisir entre `et` et `est`, entre `son`
et `sont`, c'est la **structure de la phrase**, pas le mot voisin. "Apres cette longue randonnee en
montagne, le vieux chien ne ___ plus vraiment courir comme avant." se raccourcit sans dommage - "Le
chien ne ___ plus courir." garde le "ne... plus" qui commande le verbe. **Mais un raccourcissement
fait au plus court, mot a mot, peut produire une phrase ou les deux propositions passent**, et j'ai
verifie en s05 qu'aucun item du paquet n'etait dans ce cas. **Cette propriete est ce que le
raccourcissement met en danger.**

**Mes conditions.**

- **BPM-1 (bloquante). La condition de retour de `DEC-0024` est mesuree, par moi, sur la totalite des
  items du paquet raccourci** - pas sur un echantillon, pas sur le pire cas suppose. L'etat le plus
  charge de chaque manche (message d'echec, indice, revelation) laisse les propositions entierement
  visibles a 360 x 640, par la commande du 0. **C'est la seule des quatre que personne d'autre ne peut
  produire aujourd'hui.**
- **BPM-2 (bloquante). Apres raccourcissement, chaque item garde exactement une proposition
  grammaticalement acceptable.** Aucun item ne devient ambigu. **Verification par un tiers** : lire
  les 24 phrases raccourcies et essayer la mauvaise proposition dans chacune ; aucune ne doit passer.
  C'est le controle que j'ai fait en s05 et il se refait sans moi.
- **BPM-3 (bloquante). Le paquet garde au moins 24 items.** Raccourcir n'est pas supprimer : retirer
  les quatre items les plus longs satisferait `DEC-0024` en cinq minutes et ferait retomber le paquet
  sous le volume dont ma condition de s06 a ete levee. **Le travail demande est une reecriture, pas
  une coupe.**
- **BPM-4 (bloquante). Chaque item raccourci repasse devant moi comme un contenu neuf**, ce que
  `DEC-0024` prevoit deja et que je confirme : la phrase que l'enfant lit change, donc le contenu
  change.

**Et je redis, sans la rouvrir, ma reserve 5 de s04** : deux propositions donnent une chance sur deux
au hasard, donc le score de ce jeu ne mesure pas grand-chose. Elle reste ouverte, elle n'est pas un
motif contre ce retour, et **le raccourcissement ne doit pas l'aggraver** en reduisant encore le
nombre de propositions.

## 4. Ce que je refuse, nommement

1. **Les huit items de `contenu-repris/quiz/cm1-comprehension.yml` en l'etat.** Huit sur huit
   resolubles par appariement de mots, au moins quatre sur huit resolubles sans texte, zero inference,
   et un support d'une seule phrase qui n'est pas un texte.
2. **Un paquet de comprehension ecrit sous le critere "sans feuille de style neuve".** Mesure : le
   budget de 5 lignes qu'impose le style d'enonce actuel ne porte pas un texte a inference. Ecrire
   vingt items dans cette contrainte, c'est preparer un refus.
3. **La forme "quel mot veut dire la meme chose que *X* ?" sur un mot isole.** Le referentiel demande
   un propos ; un mot seul n'en est pas un. La phrase coute zero ligne de code.
4. **Toute presentation de deux synonymes comme interchangeables partout.** C'est une erreur de fait,
   et une erreur de fait dans un contenu scolaire est chez moi un motif de refus par elle-meme.
5. **Le comptage d'une ligne a moitie servie dans le pourcentage de competences validees.** Le chiffre
   vient d'etre redresse de 31,1 % a 19,6 % ; je ne participerai pas a le regonfler par un
   arrondi de statut.
6. **Un raccourcissement du "Bon petit mot" obtenu en supprimant des items.**

## 5. La dette de capacite qui sort du perimetre de la squad - ce que cela change a ce que je garantis

On me demande si cela change quelque chose. **Oui, sur un point, et non sur l'autre.**

**La reserve F11 - ce qu'un lecteur d'ecran annonce.** Elle sort de mes mains et je l'accepte sans
reserve : je n'ai jamais pu l'instruire, je l'ai declaree degradee a chaque verdict depuis s10, et une
propriete que je ne peux pas mesurer n'a rien a gagner a rester inscrite a mon nom. **Ce qui ne change
pas : aucun de mes Done, passes ou a venir, ne couvre ce qu'un enfant entend.** Ils ne l'ont jamais
couvert et je l'ai toujours ecrit. Personne ne doit lire le transfert de cette reserve comme une
fermeture.

**La dependance a une seule personne pour un chiffre de rendu - elle est reassignee, elle n'est pas
levee.** Tant que je suis la seule a pouvoir lancer l'outil de mesure sur ce poste, **toute condition
de rendu que j'ecris reste une porte a une seule personne**, y compris COMP-4 et BPM-1 de ce document.
Ce que le transfert change pour moi est concret et je l'applique des maintenant : **je cesse de le
signaler a chaque verdict**, et **j'ecris les chiffres dans le depot au lieu de les renvoyer a une
mesure future**. C'est exactement pourquoi la section 0 existe et pourquoi les deux budgets - 5 lignes
et 8 lignes - sont ecrits ici, avant tout code : si je disparais demain, ce sprint garde ses deux
nombres.

**Ce que je peux garantir sur ce sprint, en une phrase** : l'exactitude et le niveau du contenu, la
lisibilite de ce que l'enfant lit, l'absence de chemin qui contourne la competence, et le rendu a
360 px des ecrans que je joue. **Ce que je ne garantis pas** : ce qu'un lecteur d'ecran annonce, ce
qu'un enfant reel comprend, et tout rendu sur une largeur ou une machine que je n'ai pas mesurees.

## 6. Ce que cet arbitrage ne tranche pas

- **Il ne prononce aucun Done pedagogique** : les trois stories seront jugees en jouant.
- **Il ne choisit ni la taille ni la forme du texte-support** (COMP-5) : c'est `ines-design`, et je
  jugerai sa proposition sur la lisibilite au niveau vise.
- **Il ne dit pas comment raccourcir les 24 phrases du "Bon petit mot"** : il dit ce que le
  raccourcissement ne doit pas detruire.
- **Il ne rouvre aucune de mes reserves ouvertes** - reserve 5 des homophones, reserve J des grands
  nombres, S15-C, S16-A - ni aucun arbitrage de l'utilisatrice.
- **Il n'anticipe pas `S17-1`.**
