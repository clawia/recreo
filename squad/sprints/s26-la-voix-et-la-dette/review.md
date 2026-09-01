# Review de s26 - la voix et la dette

> Ce fichier est ouvert par `nadia-prof` pour y deposer sa **conclusion pedagogique**, comme son
> prompt l'exige. **Le reste de la review appartient a `sofia-sm`** : demonstration, Done technique,
> etat des stories, suites. Rien de ce qui suit ne prejuge de ce qu'elle y ajoutera.

---

## Porte pedagogique - `nadia-prof`, 2026-09-01

**Increment juge** : `round-two` a `bc59642`. **J'ai joue**, jar reconstruit et lance par moi, sur le
JDK 21.0.10 rapporte par la machine virtuelle. **Cet audit n'est pas degrade.**

### Verdict : **REFUSE sur les cinq paquets. Aucun Done pedagogique n'est prononce.**

| Paquet | Items | Verdict | Motif |
|---|---|---|---|
| `bandit-manchot-position-syllabe-cp` | 18 | **REFUSE** | `VOI-12` seule - **sinon VALIDE sans reserve** |
| `bandit-manchot-son-dans-mot-cp` | 12 | **REFUSE** | `VOI-12` seule - **sinon VALIDE sans reserve** |
| `bandit-manchot-lettre-du-son-cp` | 18 | **REFUSE** | `VOI-12` **plus un second motif qui ne se leve pas avec elle** |
| `bandit-manchot-digraphe-du-son-cp` | 15 | **REFUSE** | `VOI-12` seule - **sinon VALIDE sans reserve** |
| `saisie-mot-dicte-cp` | 12 | **REFUSE** | `VOI-12` plus **une reserve nommee** qui ne bloque pas |

**Aucune ligne de `squad/programme/MATRICE-couverture.md` ne bouge. 31 / 41 reste 31 / 41.**

### Le motif bloquant

**Il manque la cinquieme phrase d'adulte**, celle que ma section 14.3 exige sur les cinq paquets et
que j'ai ecrite **avant que ce contenu n'existe** : *"Ne montrez pas cette ligne a l'enfant."* ou une
phrase qui dise la meme chose. Cherchee sur l'accueil, la page de niveau, l'ecran de jeu et les cinq
`consigneAdulte` : **elle n'existe nulle part.**

**Mesure sur les cinq, sans exception : un enfant qui lit la ligne de l'adulte gagne sans la
competence.** Il voit `pin` a la fin de `sapin`, `an` dans `manteau`, le `p` de `pomme`, le `ch` de
`chat` - et sur la dictee, **il recopie**. Un produit qui confie cette condition a l'adulte sans la
lui dire ne l'a pas confiee.

**Ce refus se leve avec une phrase par paquet.** Aucun contenu a reecrire, aucune mecanique a
toucher, **aucun audit a refaire** : tout ce que j'ai verifie est ecrit dans les cinq fiches
precisement pour ne pas avoir a l'etre deux fois.

### Ce que je dis a la decharge de l'increment, parce que c'est vrai

- **Les cinq points de 13.1 sont TENUS** sur les quatre phrases livrees. **Le point 3 n'echoue pas** :
  ce qui manque est la phrase supplementaire.
- **Mes cinq signaux du matin sont corriges**, et je les ai reverifies **a voix haute** : les cinq
  remplacants sont justes et aucun n'introduit le defaut qu'il repare.
- **`VOI-14` tient sur l'objet**, la branche `saisie` a bien disparu, `SAI-1`, `SAI-3` et `SAI-4` sont
  verifiees en jeu, et l'enfant bloque peut toujours avancer.
- **`cp-digraphe-du-son` est le plus propre des cinq** : 15 items sur 15 justes, 33,3 % par digraphe,
  aucune reponse ambigue, aucune signature.
- **Le chemin que j'avais route en 12.4 n'existe pas** : la revelation est dans la zone de l'enfant,
  pas dans celle de l'adulte. Je le ferme.

### Ce que je corrige contre moi

**`VOI-13` etait trop large.** Sa lettre interdisait le mot *"sur l'ecran de revelation"* ; or la
dictee ne l'y montre **qu'au moment ou l'item cesse d'etre jouable**, et une dictee qui ne montre
jamais l'orthographe apres l'echec ne corrige rien. **J'aurais refuse a tort sur ma propre lettre.**
Corrigee et datee ; l'ancienne reste lisible.

*Troisieme de mes clauses fausse en extension aujourd'hui, apres `VOI-2` et `VOI-10`.*

### Ce que le vert ne prouvait pas

**1 084 tests verts, et deux defauts qu'aucun n'attrape** : les 5 items sur 18 de `cp-lettre-du-son`
ou un enfant qui sait ses lettres est corrige d'un savoir juste, et `gomme` / `bulle` sur la dictee,
dont l'ecriture ne se deduit pas du son.

**Je reste seule garde de `VOI-3`, `VOI-5`, `VOI-8`, de la moitie non calculable de `VOI-13`, du
point 2 et de la moitie du point 3 de `VOI-12`.** Mes tables de "TENU" disent ce que j'ai verifie en
jouant - **pas ce qu'un test garde.**

### Arbitrages rendus dans le meme geste

- **`.etiquette-adulte` a 1,25rem** : confirme sans reserve. Et **une etiquette plus petite n'aurait
  rien achete sur `VOI-13`** - ce que l'enfant lit par-dessus l'epaule est le stimulus, pas
  l'etiquette.
- **Le texte *"Adulte, a voix haute :"*** : valide. Il tient mon point 2, et il est bien une
  etiquette et non une phrase.
- **La suite vide contigue partout** : **cote strict confirme**. Une regle qui ne peut produire qu'un
  refus ne peut pas produire un faux Done. **Et aucun paquet a voix en mathematiques ne passera ma
  porte tant que la comparaison sur la forme ecrite en lettres n'existe pas** - dit d'avance.
- **La lecture prudente sur la graphie affichee** : confirmee. C'est la condition d'existence de la
  ligne, pas une precaution.

### Ma borne

**75 items pour un plafond de 115.** `cp-le-bon-son.yaml` intact - `git diff` vide sur ce fichier -,
donc la clause 3 de ma barre du 2026-08-10 est tenue par constat et je n'ai pas rejoue ses 15 items,
exactement comme je l'avais ecrit d'avance. **Ma borne n'a pas ete le facteur limitant. Je n'ai rien
echantillonne et je n'ai pas eu a decliner.**

### Detail

`squad/conformite/ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md`, section 17, et le bloc de
validation des cinq `squad/conformite/FICHE-*.md`.
