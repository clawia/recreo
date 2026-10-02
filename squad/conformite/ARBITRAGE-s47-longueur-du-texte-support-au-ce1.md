# Arbitrage du 2026-10-02 - la borne de longueur du texte support au CE1

- **Auteur** : `nadia-prof`. **Date** : 2026-10-02. **Sprint** : s47, hors lot de contenu.
- **Ce qui m'est pose** : `DEC-0104`, section 1.1, point (B2). Je l'avais signale moi-meme dans
  `squad/programme/francais/competences-ce1.md` : *"La longueur du texte support des lignes
  `FRA-CE1-04` a `FRA-CE1-07` n'est bornee par rien."*
- **Ce document ne prononce aucun Done**, n'ecrit ni backlog ni garde-fou, et ne cree aucune ligne
  de matrice.
- **Je n'ai rien joue, et ce n'est pas un audit degrade : il n'y a rien a jouer.** Aucun paquet de
  francais CE1 n'existe. Ce document est une specification prealable, sur le patron de
  `ARBITRAGE-s36-longueur-d-enonce-au-ce1.md`.

---

## 1. Le manque est reel

`LON-CE1-1` et `LON-CE1-2` gouvernent le champ `enonce` d'un paquet CE1, et lui seul
(`ARBITRAGE-s36`, section 6). Sur une ligne de comprehension, l'enfant lit d'abord un texte, porte
par la cle `texteSupport` (`ADR-0050`), puis la question, portee par `enonce`. Le texte n'est borne
par rien au CE1. Le CP a sa borne (`NAR-2` : au plus 18 mots et deux phrases), posee pour
`FRA-CP-07`.

## 2. Ma decision

> **`LON-CE1-3`** - au CE1, le texte support d'un item qui declare `FRA-CE1-04`, `FRA-CE1-05`,
> `FRA-CE1-06` ou `FRA-CE1-07` compte **au plus 30 mots**.
>
> **`LON-CE1-4`** - ce meme texte compte **au plus quatre phrases**, et **aucune de ses phrases ne
> compte plus de 12 mots**.

**La convention de comptage est celle d'`ADR-0089`**, reprise sans changement, comme a s36 : un mot
contient au moins une lettre ou un chiffre, une forme elidee compte pour un mot, une fin de phrase
est un `.`, un `?` ou un `!` qui termine le texte ou qu'une majuscule suit.

**La question reste gouvernee par `LON-CE1-1` et `LON-CE1-2`.** Un item lu en entier compte donc au
plus 50 mots.

**Pour `FRA-CE1-07`, la consigne ecrite a comprendre est le texte support.** Ce n'est pas la
consigne du jeu, que cette borne ne gouverne pas.

## 3. Pourquoi 30 mots, et pourquoi 12 mots par phrase

**Mesure du 2026-10-02 sur les textes supports servis**, script de poste non commite, convention
ci-dessus :

| Paquet | Niveau | Textes | Mots (mediane / max) | Phrases (max) | Phrase la plus longue |
|---|---|---|---|---|---|
| `cp-texte-narratif` | CP | 20 | 16 / 18 | 2 | 12 mots |
| `cm1-comprehension` | CM1 | 20 | 29 / 38 | 2 | 29 mots |
| `cm1-substituts` | CM1 | 20 | 28 / 37 | 3 | 19 mots |
| `cm1-histoire-geographie-lire-un-document` | CM1 | 10 | 27,5 / 41 | 3 | 27 mots |
| `cm1-sciences-extraire-information` | CM1 | 6 | 38 / 40 | 3 | 19 mots |

**Le CE1 doit tenir entre les deux.** 18 mots, la borne du CP, ne suffit pas a `FRA-CE1-04` : un
ordre chronologique demande au moins trois evenements, et une inference (`FRA-CE1-06`) demande deux
faits a relier. 30 mots reste sous le plus long texte servi au CM1.

**Ce que les quatre lignes demandent tient sous la borne.** Deux textes, comptes sous la
convention :

| Texte | Mots | Phrases | Phrase la plus longue | Lignes servables |
|---|---|---|---|---|
| **Ce matin, Inès part à l'école avec son frère. Il se met à pleuvoir. Inès ouvre son parapluie au-dessus de lui. Ils arrivent tout secs.** | 25 | 4 | 9 | `FRA-CE1-04`, `-05`, `-06` |
| **Le hérisson dort tout l'hiver. Au printemps, il se réveille et cherche à manger. Il mange des vers, des limaces et des insectes. Il sort surtout la nuit.** | 28 | 4 | 9 | `FRA-CE1-05`, `-07` |

**Pourquoi une borne par phrase.** Un texte court peut rester illisible si une seule phrase porte
tout. Le CE1 lit des phrases courtes ; il ne lit pas encore une subordonnee longue. 12 mots, c'est la
plus longue phrase de texte deja validee au CP. Plus de phrases que le CM1, mais plus courtes : c'est
le bon sens pour ce niveau.

**Ce que la borne refuse, parce qu'une borne qui ne refuse rien n'est pas une borne** :

> *Quand Tom est arrivé à la piscine avec son père, il a vu que l'eau était froide. Il n'a pas
> voulu se baigner.*

**23 mots, 2 phrases** : il tient `LON-CE1-3`. **Sa premiere phrase compte 17 mots** : il echoue sur
`LON-CE1-4`. C'est la phrase a subordonnee que la clause existe pour ecarter.

**30 est un plafond, pas une cible.** Les deux exemples tiennent a 25 et 28.

## 4. La population, et je dis qu'elle est vide

**Population** : le champ `texteSupport` de tout item qui declare l'une des quatre lignes, dans le
contenu servi de chaque module declare au pom agregateur. **Elle est vide aujourd'hui** : aucun
paquet de francais CE1 n'existe. Les deux clauses se decident sur l'item seul, sans liste fermee
(`squad/README.md`, section 4).

**Ce que je n'ai pas fait** : je n'ai mesure aucun corpus de CE1, il n'y en a pas. Le chiffre est un
jugement de niveau, appuye sur les textes servis au CP et au CM1. **Si le premier paquet me montre
que 30 etait trop haut, j'ouvrirai une reserve neuve et datee ; je ne durcirai pas `LON-CE1-3`
apres coup.**

**La dette est nulle** : la borne entre avant le premier texte.

## 5. Ce que cette borne ne couvre pas

- **Les autres lignes de CE1.** Un texte support sur une autre ligne, dans n'importe quelle matiere,
  n'est pas gouverne ici. Il demandera sa propre decision.
- **Le CP et le CM1.** `NAR-2` reste inchange. Le CM1 n'a pas de borne de texte support, et ce
  document ne lui en donne pas.
- **Les propositions, l'indice, la consigne du jeu**, comme a s36.

## 6. Ce dont je reste la seule garde

Un texte conforme aux deux clauses peut rester illisible, et je le refuserai. Le compteur ne voit
ni un pronom dont le referent est ambigu, ni un mot hors de portee, ni trop de personnages, ni une
reponse que la question rend devinable sans lire. Restent dans ma seule garde, sur tout paquet de
ces quatre lignes :

1. la lisibilite reelle du texte ;
2. l'accessibilite du vocabulaire ;
3. la charge cognitive : nombre de personnages et d'evenements a tenir ;
4. l'exactitude du texte documentaire ;
5. le fait que l'item travaille bien la ligne qu'il declare.

## 7. Ce que je route, et a qui

| Fait | A qui | Pourquoi ce n'est pas a moi |
|---|---|---|
| `LON-CE1-3` et `LON-CE1-4` sont automatisables sur le patron d'`ADR-0089`. **Tant qu'aucun garde-fou ne les execute, je les tiens en comptant** | `theo-archi`, via `sofia-sm` | La forme du test lui appartient. La population etant vide, `ADR-0051` dit ce qu'un garde-fou ecrit aujourd'hui doit faire |
| Le critere d'acceptation de toute story qui servira `FRA-CE1-04` a `FRA-CE1-07` cite `LON-CE1-3` et `LON-CE1-4` | `camille-po` | Redaction de story |

**Identifiants** : deux neufs, `LON-CE1-3` et `LON-CE1-4`, forme copiee de `LON-CE1-1` et
`LON-CE1-2`. Alloues dans `squad/decisions/REGISTRE-identifiants.md`, section 4, dans le meme commit.

**Signe** : `nadia-prof`, s47, 2026-10-02.
