# [Sprint s25] Retrospective - 2026-09-01

- **Animee par** : `sofia-sm`, **casquette Scrum Master**.
- **Increment** : le premier palier visuel, quatre paquets, **31 / 41 = 75,6 %**.
- **Ce document ne prononce aucun Done, ne cree aucune decision, et n'arbitre aucun rang.** Il
  constate, il amende `squad/README.md` la ou c'est mon domaine, et il route le reste.

---

## 1. Le fait qui domine ce sprint, et il n'est pas de moi

`yanis-qa` a nomme la propriete commune aux **sept** defauts du sprint - quatre du sprint, trois des
siens :

> **Ils naissent tous de l'intervalle entre l'ecriture d'une clause et sa mesure sur l'objet
> motivant. Juste en intention, faux en extension.**

| Le defaut | Ce qu'il devait attraper | Ce qu'il faisait |
|---|---|---|
| **`CON-6`** | le paquet dont l'indice partitionnait les reponses | il le laissait passer **a exactement 50 %**, son plancher |
| **Le garde-fou de geometrie** | qu'un dessin declare une taille utile | il l'exigeait **dans le mauvais sens**, au moins le plafond au lieu d'au plus |
| **L'enveloppe de mesure** | qu'une mesure annoncee sur 21 s'execute sur 21 | **faux rouge** sur le cas juste, **et faux vert** sur le cas faux |
| **`CPL-3`** (s24) | une condition ecrite qu'un test execute | **troisieme et quatrieme recurrence** |

**Trois de ces quatre ont ete trouves par leurs propres auteurs. Aucun par un outil.**

**Ce que j'en retiens pour la conduite, et c'est le point que je porte** : ce depot a un reflexe
solide - *"un chiffre porte sa methode"* - et un reflexe absent : **une clause porte la mesure de son
objet motivant.** Ecrire une condition et ne pas la passer sur le cas qui l'a fait naitre est la
faute que ce sprint a commise quatre fois, chez quatre agents differents. **Aucun garde-fou ne
l'attrape parce qu'elle vit avant le garde-fou.**

---

## 2. Ce qui a marche

### 2.1 La chaine de correction de `CON-6` - le fait de process du sprint

**Quatre agents, aucun n'ayant eu raison seul :**

1. **`relecteur-pedagogique`** trouve, en relisant du contenu, que l'indice partitionne les reponses.
   *Aucun test ne le voyait, et ce n'etait pas son perimetre declare.*
2. **`nadia-prof`** ecrit `CON-6` sur ce constat.
3. **`sofia-sm`, casquette dev**, mesure sa conformite **avant d'ecrire**, obtient **100 %**, et
   **s'en mefie au lieu de s'en prevaloir.**
4. **`nadia-prof`** verifie et **trouve pire** : sa propre clause laissait passer le paquet qui
   l'avait motivee, a exactement 50 %.
5. **`lea-dev`** propose une propriete - l'independance de l'indice et de la reponse - que
   **`nadia-prof` prefere a ses propres bornes**, parce que *"mes deux bornes etaient des
   approximations de ce que ma propre phrase disait, et la sienne est cette phrase"*.
6. **`yanis-qa`** mesure les quatre clauses et corrige les deux dernieres.

**Le point 3 est celui que je veux voir se reproduire** : **un 100 % obtenu sur sa propre production
est un signal de suspicion, pas un resultat.** Il n'a rien coute et il a ouvert toute la chaine.

### 2.2 Trois auteurs ont sanctionne chez eux ce qu'ils sanctionnent chez les autres

- **`nadia-prof`**, sur `CON-6` : *"C'est la forme exacte de `CPL-3`. Elle est de moi, et il a fallu
  un tiers pour me la faire chercher."* Et sur son propre instrument : *"Un chiffre qui empire quand
  le paquet s'ameliore ne mesure pas le paquet. Le '8 sur 12' que j'avais verse au nom de
  l'honnetete etait sans valeur - **et le dire est la meme honnetete**."*
- **`lea-dev`**, sur son garde-fou : *"il exigeait au moins le plafond de rendu [...] Sa mesure dit
  l'inverse."*
- **`marc-dev`**, verse contre son propre lot et **ecrit pour servir a un autre que lui** :
  > **Une garantie approximative coute plus qu'une garantie absente : une absence fait refaire le
  > controle, une approximation le fait sauter.**

### 2.3 L'ordre des ceremonies, corrige, a servi des le premier essai

`yanis-qa` l'ecrit lui-meme : sa campagne **porte sur l'arbre que la cloture presente**, et non sur
un arbre intermediaire. **L'ecart de s24 - le lancer apres la review - ne s'est pas repete**, et le
gain est mesurable : ses onze mutations valent pour l'increment livre.

### 2.4 Trois mesures independantes du meme chiffre

**1 018, sur le meme commit, par le `relecteur-pedagogique`, par `yanis-qa` et par moi.** C'est la
premiere fois de ce sprint qu'un chiffre est **confirme par un tiers** plutot que recopie - et c'est
le contraire de ce que les quatre annonces a 1 011 avaient produit.

### 2.5 Le plafond a ete atteint

`DEC-0065` posait **31 / 41 = 75,6 %** comme plafond a l'ouverture, en ecrivant qu'il *"suppose que
quatre paquets passent la porte du premier coup, ce qui n'est jamais arrive sur ce produit"*. **Il a
ete atteint.** Cela merite d'etre note sans en tirer de regle : un refus a ete leve en cours de
sprint, ce qui n'est pas *"du premier coup"*.

---

## 3. Ce qui a coince

### 3.1 J'ai lu un rouge comme une preuve

**C'est ma faute du sprint et elle est ecrite dans la review, section 12.1.** Le lot F a refuse de
mesurer ; j'ai rapporte ce refus comme *"le lot F qui attrape un defaut reel"* et j'en ai tire une
consequence fausse sur trois mesures anterieures. **C'etait un faux rouge.**

> **Un garde-fou qui rougit sur ce qu'on attendait est le moment ou l'on verifie le moins.**

Et le defaut est a moi des deux cotes : le lot F est de ma casquette dev, et il **avait appris la
distinction des deux fichiers de toolchains a son diagnostic tout en la laissant ignorer de son
verdict**. `theo-archi` : *"le diagnostic savait ce que le verdict ne savait pas."*

### 3.2 Ma transcription d'une convention en avait chasse une forme

`squad/README.md` section 2 bis decrivait en forme 2 *"une mesure faite par l'auteur"* la ou
`DEC-0062` ecrit **`de l'utilisatrice`, ses mots cites, la date**. **Un arbitrage de l'utilisatrice
n'avait donc plus de forme du tout** - la moitie du trou que la convention existe pour fermer,
rouverte dans le fichier cense la diffuser. Trouve par `relecteur-pedagogique` **en verifiant la
charte qu'il applique**. Corrige a la cloture.

*Le motif de fond : j'avais loge la une exigence vraie mais d'une autre nature. **Une table
exhaustive de trois formes ne supporte pas qu'on y ajoute une exigence : elle en chasse une.***

### 3.3 Quatre agents ont annonce le meme chiffre faux

**1 011**, sur trois arbres differents. `yanis-qa` en a etabli la cause : **quatre classes portent
deux annotations de test**, produisent deux lignes de journal, et une agregation par nom de classe
perd exactement 7. **Artefact de methode, pas defaut de build** - l'obstacle 12, ouvert depuis s23,
est clos.

**Ce que je retiens** : l'obstacle etait connu, route, et documente comme une curiosite de comptage.
**Il a fallu qu'il produise quatre annonces identiques et fausses pour etre instruit.** Un defaut de
mesure connu et non instruit finit par produire un chiffre qui circule.

### 3.4 Trois ecarts muets ont failli fausser l'audit pedagogique

`nadia-prof` les nomme : **un jar de la veille qui attendait**, **douze dessins corriges pendant sa
passe**, et **une garantie fausse qui l'aurait fait reporter des verifications.**

**Aucun des trois n'etait visible depuis sa position.** Les deux premiers sont de la conduite de
sprint, donc de moi : j'ai fait jouer un auditeur pendant que l'arbre bougeait sous lui. **Je l'avais
pourtant su** - j'ai ecrit en section 11 de la review que *"l'ecran joue n'a pas bouge d'un pixel"*
pour justifier de ne pas l'interrompre, et c'etait vrai de l'ecran, **pas des douze fichiers.**

### 3.5 La dette n. 7 a demande un tiers pour se fermer

`relecteur-pedagogique` l'avait signalee a s24 puis a s25 sans pouvoir la combler - *"une charte qui
se donne ses criteres ne verifie plus rien"* - **et `camille-po` comme moi l'avions refuse, chacune
sur un motif juste** : ce n'est ni du perimetre produit, ni de la conduite de ceremonie. **Personne
n'avait tort et la dette restait.** Elle a ete fermee **par le fil d'animation, pas par un agent.**

> **Un objet qu'aucun role ne peut modifier n'a pas de proprietaire, et le routage ne peut rien pour
> lui.** C'est le seul cas de ce sprint ou ma table de routage n'avait pas de ligne.

---

## 4. Amendements a `squad/README.md`

**Faits dans le meme geste que cette retro** - c'est mon domaine et je ne les propose pas :

1. **Section 2 bis, forme 2 retablie** telle que `DEC-0062` l'ecrit, avec la correction datee et son
   motif.
2. **Meme bloc** : l'exigence de methode et de perimetre d'un chiffre vit en **section 4** et **n'est
   pas une forme d'attribution**.

**Proposes, avec qui decide :**

3. **Une clause porte la mesure de son objet motivant.** Quatre defauts de ce sprint viennent de son
   absence. **Faisabilite a `theo-archi`** - je soupconne qu'aucun automate ne peut l'attraper, une
   clause n'ayant pas d'objet motivant declare ; si c'est le cas, savoir si une regle de conduite non
   executable vaut d'etre ecrite revient a `camille-po`.
4. **Un increment ne bouge pas sous un auditeur qui joue.** Ma conduite en 3.4. **A moi**, mais je ne
   l'ecris pas sans l'avoir eprouve : la regle naive - geler l'arbre - couterait la correction de
   `lea-dev`, qui etait bonne. **Ce qui manque est un signal, pas un gel**, et je n'ai pas encore sa
   forme.

---

## 5. Ce que cette retro ne fait pas

- Elle **ne prononce aucun Done**, ne fusionne rien, et ne cree aucune `DEC` ni aucun `ADR`.
- Elle **ne clot aucun des obstacles ouverts** de `review.md` section 13.9.
- Elle **n'arbitre pas le rang de s26** : `DEC-0067` le pose, et il est a `camille-po`.
- Elle **n'ecrit aucun planning retrospectif** et ne fabrique aucune ceremonie qui n'a pas eu lieu.
