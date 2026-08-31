# Fiche de conformite - bandit-manchot-pluriel-cp

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du catalogue).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-pluriel.yaml`, `type:
  question`, **21 items**, story `S24-PLU` (`DEC-0059`). Ecriture neuve de `lea-dev` ; aucun fichier
  de `contenu-repris/` ne porte cette competence.
- **Entree de catalogue** : `bandit-manchot-pluriel-cp`, titre *"Un ou plusieurs ?"*, consigne
  *"Choisis la bonne écriture pour compléter la phrase."*, 10 manches, niveau CP.
- **Conditions applicables** : `PLU-1` a `PLU-9`, arbitrage prealable de `nadia-prof` du 2026-08-30
  (`ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md`, partie 4).
- **Statut global** : **Done pedagogique prononce le 2026-08-31.**

## Competences visees

Francais CP, orthographe et grammaire - *"Reconnaitre et utiliser les marques du pluriel les plus
frequentes (-s)"*. **Une seule ligne de matrice, et la fiche le porte ecrit** : ce jeu ne sert
aucune autre ligne.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

### Verdict du 2026-08-31 (s24) - `S24-PLU`

## Verdict : **VALIDE**

Done pedagogique prononce. `camille-po` peut passer la ligne de matrice a `couvert-valide`.

### 1. Ce que j'ai fait, et dans quel ordre

**J'ai joue.** Application lancee depuis le jar servi, construit sur l'arbre de cloture, sur le JDK
21 de la toolchain declaree. J'ai ouvert le jeu depuis la liste du niveau CP, joue des manches, et
**repondu faux volontairement** pour voir ce qui arrive apres une erreur, apres deux, apres trois.

**Ce que j'ai vu, ecran par ecran** : l'enonce porte le trou a sa place dans la phrase, les trois
ecritures sont proposees cote a cote, un appui faux rend *"Ce n'est pas ça. Essaie encore !"* et
fait apparaitre l'indice, **la proposition pressee reste marquee**, et au troisieme echec la reponse
est revelee et nommee. **Un enfant bloque peut avancer.**

**Puis j'ai verifie les 21 items, tous, et non un echantillon** - c'est ma condition depuis s17.

### 2. Mes neuf conditions, une par une

*Mesures faites le 2026-08-31 par lecture du fichier YAML servi, sur la totalite des 21 items.
Unites de compte : celles que mes conditions fixent - le mot est une suite de caracteres sans
espace ; le mot graphique de `PLU-7` est delimite par les espaces, les apostrophes et les traits
d'union, precision du 2026-08-30. Aucun build, aucun `mvn` : la question du `-am` ne se pose pas.*

| Condition | Verdict | Ce que j'ai mesure ou lu |
|---|---|---|
| **`PLU-1`** - la proposition porte le groupe entier ; la phrase ne porte **aucun autre indice de nombre** | **tenue** | Lu phrase par phrase, les 21. Le groupe a trou n'est jamais le sujet : le verbe est partout a la troisieme personne du singulier, avec un prenom pour sujet. Aucun adjectif accorde, aucun nombre ecrit, aucun autre determinant du meme groupe. **C'est l'accord interne au groupe qui decide, et rien d'autre.** *Condition dans ma seule main : aucun automate ne la voit* |
| **`PLU-2`** - six determinants, et aucun autre | **tenue** | Employes : `des`, `la`, `le`, `les`, `un`, `une`. Les six, et rien d'autre |
| **`PLU-3`** - noms a pluriel en `-s` seulement | **tenue** | Aucun nom en `-eau`, `-au`, `-eu`, `-al`, `-ail`, aucun des sept noms en `-ou`, aucun deja termine par `-s`, `-x` ou `-z` |
| **`PLU-4`** - distracteurs derives, derivation nommee | **tenue** | La derivation est nommee en tete de fichier, avec l'ordre fixe (a) puis (b). **Verifie par recalcul sur les 21 items : zero ecart.** Le premier distracteur est toujours la famille (a), le second la famille (b), sans exception |
| **`PLU-5`** - le `-s` ne decide pas | **tenue** | Bonne reponse avec marque `-s` : **10 items** ; sans : **11**. Plancher exige : 7 de chaque cote, partie entiere de 21/3 |
| **`PLU-6`** - longueur | **tenue** | Phrase la plus longue : **7 mots**, borne 10. Proposition la plus longue : **13 caracteres**, borne 15 |
| **`PLU-7`** - vocabulaire | **tenue** | Mot le plus long : **9 lettres**, borne 9. A la borne, pas au-dela |
| **`PLU-8`** - l'indice renvoie au determinant | **tenue** | Quatre indices tournants, tous sur le determinant, aucun ne nomme la reponse. Le plus long : **8 mots**, borne 8 |
| **`PLU-9`** - au moins vingt items, aucun nom plus de deux fois | **tenue** | 21 items ; aucun nom employe plus de deux fois |

### 3. Ce que j'ai cherche en plus, et qui n'etait exige par aucune condition

**Motif** : le garde-fou de rang de ce depot ne juge que des propositions numeriques. **Sur un
paquet de francais il ne garde rien**, et je suis donc la seule a pouvoir dire si une strategie de
surface gagne sans lire.

*Methode : pour chaque regle de surface, je compte les items ou **une seule** des trois propositions
la porte - ce sont les seuls ou la regle decide - puis je regarde combien de fois cette proposition
est la bonne. Population : les 21 items. Hasard : 33,3 %.*

| Strategie de surface | Items ou elle decide | Items gagnes |
|---|---|---|
| "toujours celle au determinant pluriel" | 11 | **0** |
| "toujours celle au determinant singulier" | 10 | **0** |
| "toujours celle dont le nom finit par `-s`" | 11 | **0** |
| "toujours celle dont le nom ne finit pas par `-s`" | 10 | **0** |
| "toujours la plus longue chaine", quand elle est unique | - | 6 sur 21, **28,6 %** |
| "toujours la plus courte chaine", quand elle est unique | - | 6 sur 21, **28,6 %** |

**Aucune strategie de surface ne fait mieux que le hasard, et les quatre strategies decisives
gagnent zero fois.** Ce n'est pas un accident : `PLU-5` a ete ecrite contre la strategie de
longueur, et `PLU-4` rend les deux distracteurs symetriques par construction, ce qui interdit
qu'une marque de surface designe la bonne reponse.

### 4. Contenu et niveau

- **Exactitude** : les 21 bonnes reponses sont justes ; les 42 distracteurs sont tous des accords
  fautifs et aucun n'est une ecriture correcte. **Aucune erreur factuelle.**
- **Lisibilite CP** : phrases de cinq a sept mots, une seule information, le trou au milieu. Un
  enfant de CP lit cela seul.
- **Charge cognitive** : une seule chose a tenir en tete - le petit mot devant commande le nom.
- **Traitement de l'erreur** : l'indice dit **ou regarder** sans dire quoi choisir, et la revelation
  debloque. C'est ce que je demande.
- **Biais et ton** : 20 prenoms distincts, **10 masculins et 10 feminins**, et aucun role, aucune
  activite, aucune emotion distribuee par genre - les filles rangent, cherchent, arrosent, ecrivent,
  remplissent ; les garcons posent, dessinent, mangent, portent, attrapent. Les situations sont
  scolaires ou neutres. **Aucune ne suppose une configuration familiale, un logement, un animal ou
  un objet possede.**

### 5. Sur quelles proprietes je reste la seule garde

- **`PLU-1`** - qu'aucun autre indice de nombre ne traine dans la phrase. Aucun test ne le voit.
- **`PLU-8`** - qu'un indice conduise sans donner.
- **L'absence de strategie de surface sur un paquet non numerique.** Le garde-fou de rang ne
  s'applique pas ici ; le chiffre de la section 3 est de moi et rien ne le refera a ma place.

### 6. Ce que ce verdict fait, et ce qu'il ne fait pas

- Il **prononce le Done pedagogique** sur `bandit-manchot-pluriel-cp`, et sur lui seul.
- Il **ne touche a aucune autre fiche** ni a aucun autre verdict.
- Il **ne fait bouger aucune ligne** de matrice par lui-meme : c'est `camille-po` qui la passe.
- Il **n'est pas fonde sur la CI.** La porte technique est verte et cela ne prononce rien ; ce qui
  prononce, c'est que j'ai joue et que j'ai lu les 21 items.
- Il **ne dit rien** du score de fin de partie, qui reste sous la reserve 5 elargie du 2026-08-30 et
  tranche par `DEC-0061` : sur un paquet a trois propositions, un enfant qui presse au hasard sans
  represser a necessairement raison au troisieme essai. **Je l'ai revu en jouant ce jeu-ci, et il
  n'est pas un defaut de ce contenu.**
