# DEC-0066 - Reclassement de deux lignes en `non couvrable en l'etat` sur arbitrage de l'utilisatrice : deux des quatre candidates ne remplissent pas le critere et ne sortent pas

- **Statut** : Acceptee
- **Date** : 2026-08-31
- **Auteur** : `camille-po`
- **Sprint** : s25 (ouverture)
- **Arbre de reference** : worktree `s25-camille`, ouvert sur `round-two` a `130ba64`
- **Arbitrage qui la fonde** : l'utilisatrice, 2026-08-31, verbatim : **"Reclasser, taux revu"**
- **Jugement pedagogique qui la fonde** : `nadia-prof`,
  `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md`,
  sections 2.1, 2.7, 3.5 et 9.1

## Contexte, et le fait qu'il faut lire avant le reste

J'ai propose quatre lignes au reclassement dans
`squad/programme/INSTRUCTION-s25-les-16-lignes-hors-couvert-valide.md`, section 5, en refusant de
le faire moi-meme : *"une reclassification qui ameliore mon propre indicateur, decidee par moi, le
jour ou on me demande de le maximiser, n'est pas lisible - meme si elle est juste."* `nadia-prof` a
refuse pour un motif voisin : *"cela porterait le taux sans qu'un enfant ait appris quoi que ce
soit."*

**L'utilisatrice a tranche malgre ces deux phrases, et elle a eu ce qu'il fallait pour le faire.**
La matrice ecrit que seule une decision d'elle peut faire sortir une ligne de cet etat ; c'est donc
son arbitrage qui est requis, pas notre confort.

**Mais son arbitrage porte sur un principe, pas sur une liste que je lui aurais fait signer.** Le
critere du statut est ecrit dans la matrice et il s'applique ligne par ligne. **Je l'ai applique aux
quatre, et deux ne le remplissent pas. Elles ne sortent pas.**

---

## 1. Le critere, recopie de la matrice, et non reformule

> `non couvrable en l'etat` (introduit au sprint 19, `DEC-0042`) : la competence exige quelque chose
> qu'aucune mecanique que ce produit pourrait construire ne peut fournir, **et non faute d'un
> contenu, d'une mecanique ou d'un arbitrage pedagogique qui manquerait encore**. Deux motifs
> distincts ont conduit a ce statut [...] : soit la competence exige de revenir sur une **contrainte
> fondatrice du produit** lui-meme (`CLAUDE.md`) ; soit elle exige un **jugement humain sur une
> production libre de l'enfant**, qu'aucun verdict serveur testable ne peut rendre (`ADR-0008`), et
> que ni le choix ni la saisie ne peuvent automatiser.

**Deux motifs, et rien d'autre.** Une ligne qui attend de l'audio, une architecture, un contenu ou
un arbitrage **ne remplit pas ce critere** : elle est en retard, pas hors d'atteinte.

---

## 2. Les quatre candidates, une par une

### 2.1 "Copier un mot, une phrase courte sans erreur, en respectant l'espacement" (francais CP) - **SORT**

- **Motif retenu** : contrainte fondatrice.
- **Le jugement pedagogique, de `nadia-prof`, 2026-08-31, section 2.7** : *"la competence est
  graphomotrice - tenir l'outil, former les lettres, espacer sur la ligne. **Ni un clavier ni un
  choix ne produisent un geste d'ecriture.** L'utilisatrice a arbitre a `DEC-0046` qu'aucun geste de
  trace n'est envisage pour ce produit ; **former des lettres est un geste de trace.**"*
- **Pourquoi le critere est rempli** : la contrainte fondatrice existe deja, elle est ecrite, et
  c'est **l'utilisatrice elle-meme** qui l'a posee a `DEC-0046` - aucun geste de trace. La ligne ne
  manque ni de contenu, ni de mecanique, ni d'arbitrage : les deux mecaniques du produit ont ete
  interrogees, `R1` pour la saisie le 2026-08-24, le choix le 2026-08-31, et les deux repondent non.
- **Nature** : **reversible** (distinction de `DEC-0046`, section 4.1). **La condition exacte qui la
  ferait sortir de cet etat** : une decision de l'utilisatrice revenant sur `DEC-0046` et ouvrant un
  geste de trace au produit - une surface ou l'enfant ecrit avec le doigt ou un stylet, et un moyen
  d'en juger la forme. **Ce jour-la, cette ligne redevient `non couvert` et rentre dans le
  denominateur.**

### 2.2 "Rediger un texte narratif court et coherent" (francais CM1) - **SORT**

- **Motif retenu** : jugement humain sur une production libre.
- **Le jugement pedagogique, meme section** : *"`R3` refusait par avance quelle que soit la qualite
  du lot. **Je le reconduis et je l'etends au choix.** Remettre des phrases dans l'ordre travaille la
  coherence, et c'est un bon exercice - **mais l'enfant ne redige pas**, et la ligne dit *rediger*."*
- **Pourquoi le critere est rempli** : la ligne exige que l'enfant produise un texte, et que
  quelqu'un juge sa coherence et le respect des temps du recit. C'est exactement le second motif du
  statut, celui qu'`ADR-0008` fonde et qui vaut deja pour *"Relire et corriger son texte"*, dans le
  meme domaine et pour la meme raison. **Les deux mecaniques ont ete interrogees et les deux
  repondent non.**
- **Nature** : **irreversible**, au sens de `DEC-0046`, section 4.1. **Aucune decision produit ne la
  fera sortir de cet etat** : juger la coherence d'un recit ecrit par un enfant de neuf ans demande
  un adulte qui lit. La seule sortie concevable serait un **amendement du referentiel** qui
  reformulerait la competence en un geste de reconnaissance - et ce serait alors une autre
  competence, pas celle-ci.

### 2.3 "Lire silencieusement un texte de longueur croissante et en restituer l'essentiel" (francais CM1) - **NE SORT PAS**

**Ma proposition du matin est fausse, et c'est `nadia-prof` qui l'a rendue fausse dans la journee.**

Le 2026-08-31, quelques heures apres que j'ai ecrit que cette ligne butait sur une *"production
libre jugee par un humain"*, elle a rendu : **`ACCEPTE SOUS CONDITIONS LIS-1 a LIS-10`**, avec
couverture partielle nommee. Son motif, section 9.1 :

> *"`R2` n'a ecarte que la saisie. [...] J'ai relu les vingt enonces de `cm1-comprehension` un par
> un : les vingt portent sur un detail. **Aucun ne demande de quoi le texte parle.** Restituer
> l'essentiel - trouver le titre, la phrase qui resume - est un autre geste, et c'est celui qu'un
> CM1 rate le plus souvent [...] **La ligne est distincte, et elle vaut d'etre ouverte.**"*

**Ce que j'avais mal fait** : j'ai lu *"restituer l'essentiel"* comme *"produire un resume"*, donc
comme une production libre. Elle a vu qu'on peut **faire choisir** entre trois formulations du sujet,
dont deux sont des details exacts promus au rang de sujet (`LIS-5`). **Le geste que la ligne nomme
est servable par le choix ; c'est ma lecture du verbe qui etait trop large, pas la ligne qui etait
fermee.**

**Cette ligne est ouvrable, elle a ses dix conditions ecrites, et elle est au backlog de s25.** La
reclasser aurait retire du produit une ligne que la squad peut atteindre. **C'est exactement le
defaut contre lequel l'instruction de reclasser me met en garde : une ligne atteignable qu'on
reclasse est retiree du produit, pas de l'affichage.**

### 2.4 "Ecrire de maniere autonome un mot simple en respectant les correspondances grapheme/phoneme connues" (francais CP) - **NE SORT PAS**

- **Ce qui la bloque, dans les mots de `nadia-prof`, section 2.1, ligne 13 de sa table** : *"saisie
  **et** audio noues [...] **il manque l'audio**"*.
- **Pourquoi le critere n'est pas rempli** : ecrire un mot en autonomie suppose qu'on le **dise** a
  l'enfant, sans le lui montrer ecrit. Ce qui manque est donc **l'audio**, et l'audio est un
  **obstacle de production** - il faut un enregistrement de voix humaine, et *"aucun agent de cette
  squad ne peut le produire"* - **que l'utilisatrice peut lever par une reponse**. Ce n'est ni une
  contrainte fondatrice du produit, ni un jugement humain sur une production libre. **C'est une ligne
  en retard, pas une ligne hors d'atteinte.**
- **Ce que `R6` disait vraiment, et pourquoi il ne suffit pas** : `R6` refusait cette ligne **a la
  mecanique de saisie**, parce que la correction automatique des appareils cibles produit les lettres
  a la place de l'enfant. C'est un refus de **moyen**, pas un constat d'impossibilite. Les trois
  autres lignes audio (`localiser une syllabe`, `identifier un son`, `decoder par assemblage`) sont
  `non couvert` pour le meme motif et personne n'a jamais propose de les reclasser. **Traiter
  celle-ci autrement serait incoherent.**
- **La condition qui la debloquerait** : une reponse de l'utilisatrice sur la source de la voix,
  **plus** une reponse sur le cout d'une lettre accentuee sur la tablette visee, question ouverte
  depuis le 2026-08-24 et jamais rendue.

---

## 3. Ce que la matrice porte apres cette decision

**Deux lignes sortent du denominateur. Deux y restent.**

| Ligne | Ancien statut | Nouveau statut | Motif du statut, porte en toutes lettres sur la ligne | Nature |
|---|---|---|---|---|
| Copier un mot, une phrase courte sans erreur (fr. CP) | `non couvert` | **`non couvrable en l'etat`** | contrainte fondatrice - geste de trace ecarte par `DEC-0046` | reversible |
| Rediger un texte narratif court et coherent (fr. CM1) | `non couvert` | **`non couvrable en l'etat`** | jugement humain sur une production libre (`ADR-0008`) | irreversible |
| Lire silencieusement, restituer l'essentiel (fr. CM1) | `non couvert` | **`non couvert`, inchange** | ouvrable, `LIS-1` a `LIS-10`, 2026-08-31 | - |
| Ecrire de maniere autonome un mot simple (fr. CP) | `non couvert` | **`non couvert`, inchange** | famille audio, obstacle de production | - |

### Les deux taux, cote a cote, avec leur date et leur denominateur

**C'est la partie de cette decision qui compte le plus dans six mois.**

| Date | Lignes vertes | Denominateur | Taux | Ce qui a change |
|---|---|---|---|---|
| **2026-08-31, avant cette decision** | **27** | **43** | **62,8 %** | - |
| **2026-08-31, apres cette decision** | **27** | **41** | **65,9 %** | **le denominateur seul** |

> **Le numerateur n'a pas bouge d'une unite. Aucun enfant ne peut jouer quoi que ce soit qu'il ne
> pouvait pas jouer ce matin. Le saut de 3,1 points est un changement de denominateur, pas un
> progres.**

Je le pose en ces termes parce que c'est exactement ce que `nadia-prof` a refuse de faire passer
pour un gain, et parce que c'est le seul cas de ce depot ou un taux monte sans qu'une ligne de code
soit ecrite. **Toute lecture ulterieure de la serie de couverture doit couper la serie au
2026-08-31** : avant, un pourcentage sur 43 ; apres, un pourcentage sur 41. Les deux ne se comparent
pas directement.

**Methode des deux chiffres** : comptage automatise des quatre tableaux de matiere de
`squad/programme/MATRICE-couverture.md`, colonne `Statut`, sur ce seul fichier. Avant : 27
`couvert-valide`, 15 `non couvert`, 1 `en cours`, 4 `non couvrable en l'etat`, total 47, denominateur
47 - 4 = 43. Apres : 27, 13, 1, 6, total 47, denominateur 47 - 6 = 41. **Aucun module compile, la
question du `-am` ne se pose pas.**

---

## 4. Une cinquieme ligne, qui ne bouge pas non plus, et le jugement qui l'y maintient

`nadia-prof` a rendu le 2026-08-31, section 3.5, un jugement que j'attendais depuis le 2026-08-25 et
que j'avais refuse de presumer.

**La symetrie axiale CM1 ne rouvre pas sur le seul verbe *reconnaitre*.** Son motif : *"reconnaitre
une symetrie et l'utiliser pour completer une figure ne sont pas deux objets distincts, c'est le
meme objet a deux degres, et le degre servi serait le plus facile."* **La ligne reste `non couvrable
en l'etat`**, avec son motif d'origine (`DEC-0046`, geste de trace ecarte).

**Je le porte ici plutot que de le laisser dormir** : j'avais ecrit que ce jugement n'existait pas et
que je ne le presumais pas. Il existe maintenant, et il ferme la porte que j'avais laissee
entrouverte.

---

## 5. Ce que cette decision ne fait pas

- Elle **ne retire aucune ligne du referentiel source**. Les six lignes `non couvrable en l'etat`
  restent ecrites dans `squad/programme/francais/competences-cp.md`,
  `competences-cm1.md` et `mathematiques/competences-cm1.md`, et un enfant reste en droit de les
  apprendre ailleurs. Le statut dit ce que **ce produit** peut servir, pas ce que le programme
  demande.
- Elle **ne fait passer aucune ligne a `couvert-valide`** et n'en retire aucune du vert.
- Elle **ne presente le nouveau taux comme un progres a aucun endroit**, et la section 3 est ecrite
  pour qu'aucune reprise ne le puisse.
- Elle **ne reclasse pas les deux lignes qui ne remplissent pas le critere**, malgre l'arbitrage -
  parce que l'arbitrage porte sur le principe et que le critere est ecrit dans la matrice. **Si
  l'utilisatrice veut que ces deux-la sortent aussi, il lui faut une decision qui nomme la contrainte
  fondatrice en cause, et je la lui redemanderai plutot que de l'appliquer.**
- Elle **ne juge la qualite pedagogique d'aucun contenu** : les quatre jugements de fond sont de
  `nadia-prof` et je n'ai fait que les appliquer au critere de la matrice.
