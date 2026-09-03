# Fiche de conformite - bandit-manchot-vivant-non-vivant-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-qlm-vivant-non-vivant.yaml`.
- **Entree de catalogue** : `bandit-manchot-vivant-non-vivant-cp`, titre servi *"Vivant ou objet"*, `nombreDeManches: 7`.
- **Statut global** : **VALIDE** (Done pedagogique prononce le 2026-09-03). **La ligne ne se ferme pas et ne doit pas se fermer** : premier paquet d'une matiere neuve, ligne de matrice `non couvert` -> **`en cours`**. C'est ce que j'avais ecrit d'avance (arbitrage prealable de s27, section 8.4).
- **Arbitrage prealable de `nadia-prof`** : `ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md`, sections 4 (ligne 1) et 7.1.
- **Derniere mise a jour** : 2026-09-03 (**verdict de `nadia-prof` apres avoir joue**, s27).

## Competence visee

`Distinguer les êtres vivants des objets et de la matière inerte sur des exemples du quotidien.`
(Questionner le monde, CP, Le vivant, la matière et les objets).

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `d259b5f`, jar reconstruit et lance, parties reelles par appels
HTTP contre l'application, **y compris en me trompant volontairement**. Ce n'est pas un audit
degrade.

### 1. Exactitude - huit items sur huit

chat, arbre, poisson, oiseau -> **etre vivant** ; chaise, ballon, voiture, stylo -> **objet**.
**Quatre et quatre, aucune erreur.** L'arbre est bien du cote du vivant, ce qui est l'erreur
classique a cet age et le seul piege reel de la ligne ; l'indice le justifie correctement
(*"un arbre nait, grandit et a des petits (ses graines)"*).

### 2. Deux propositions seulement - **accepte, et j'ai mesure le precedent avant de l'accepter**

Un item binaire se repond a 50 % au hasard, et c'est une objection serieuse. **Je l'ai instruite
sur le catalogue plutot que sur mon impression.**

| Paquet servi | Items | Propositions | Statut |
|---|---|---|---|
| `cp-nature-mot` | 16 | **2** | `couvert-valide`, valide par moi |
| `cm1-homophones` | 24 | **2** | `couvert-valide`, valide par moi |
| `cp-son-dans-mot` | 12 | **2** | `couvert-valide`, valide par moi **le 2026-09-01** |

**Trois paquets binaires, tous valides par moi, dont un il y a deux jours. Je ne deplace pas ce
poteau.** Et le motif de fond tient : *vivant* contre *objet* est une opposition **reellement
binaire**, et une troisieme proposition devrait etre inventee - c'est-a-dire fausse ou absurde,
ce que je refuse par ailleurs.

### 3. Lisibilite - mesuree, pas estimee

**Les huit enonces font 5 mots chacun**, forme identique (*"Un chat, qu'est-ce que c'est ?"*).
Pour un CP, c'est court, regulier, et la regularite aide : l'enfant lit la forme une fois et ne
lit plus ensuite que le mot qui change. **C'est le meilleur profil de lisibilite des trois paquets
de cette matiere.**

### 4. Biais

**`BIA-4` respectee** : aucun item n'interroge l'enfant sur sa propre vie. Les huit objets et
etres cites sont accessibles a tous les enfants sans supposer un foyer, un lieu ni un moyen.
**`lea-dev` a par ailleurs ecarte deliberement ma ligne 16** (*ici et ailleurs*), que j'avais
signalee comme la plus dangereuse du lot. **Le choix des lignes a evite les clauses de biais
plutot que de les affronter mal** : c'est la bonne conduite pour un premier paquet de matiere
neuve.

### 5. Joue jusqu'au bout

Partie complete, **7 manches**, ecran de fin atteint (*"C'est fini !"*), retour au catalogue
disponible. Erreurs volontaires : l'enfant reste sur l'item, recoit *"Ce n'est pas ca. Essaie
encore !"* et son indice. **L'indice ne donne jamais la reponse : il donne le critere** (*"un
stylo ne nait pas, ne grandit pas et ne se nourrit pas"*). C'est exactement ce que je demande d'un
indice, et c'est rare.

### 6. Reserve non bloquante

- **`VIV-R1` - `nombreDeManches: 7` n'est couvert par aucune decision produit ecrite.**
  `DEC-0064` exige que toute valeur autre que `10` soit portee par une decision qui nomme son
  motif ; `DEC-0020` ne couvre que les complements a 10. **Ce n'est pas un defaut pedagogique** -
  interroge, je reponds que **7 manches pour 8 items est la bonne valeur**, une partie de 10 en
  epuiserait le paquet et le ferait tourner en rond. **La ratification est donc facile ; elle
  manque.** Route a `camille-po`. Vaut identiquement pour les deux autres paquets de cette matiere.

