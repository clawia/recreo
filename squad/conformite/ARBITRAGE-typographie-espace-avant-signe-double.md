# Arbitrage - l'espace avant un signe de ponctuation double, dans le texte lu par un enfant

- **Auteure** : `nadia-prof`. **Date** : 2026-08-28.
- **Saisine** : `relecteur-pedagogique`, relecture de cloture de s21 (commit `10f5b4e`). Il a mesure
  plutot que suppose, il a route sans corriger, et il a eu raison : **`ADR-0060` nomme l'extension
  de la regle au-dela des guillemets comme une decision en attente de `nadia-prof`.**
- **Portee** : tout texte lu par un enfant dans le catalogue servi - enonces, propositions, indices,
  explications, titres et consignes. **Ne concerne ni le code, ni la documentation, ni `squad/`**,
  qui relevent des conventions de redaction de `CLAUDE.md`.
- **Decision** : **la regle s'etend** (issue 3 de la saisine) - **son application est bornee et
  ordonnee par l'effet mesure**, pas appliquee en bloc a la cloture.

## 1. Ce que j'ai mesure, et pourquoi j'ai mesure avant de trancher

Le principe typographique seul ne suffisait pas a decider : **l'espace insecable ne change rien au
sens**, elle ne change que ce qui se passe **en fin de ligne**. Une regle qui ne produit aucun effet
visible sur aucun ecran servi n'a pas le meme poids qu'une regle qui en produit. J'ai donc cherche
si un signe orphelin est **seulement possible**, et sur quels ecrans.

**Trois mesures, toutes du 2026-08-28, populations entieres et non echantillons.**

**(a) Le comptage, sur les seuls textes lus par un enfant** - champs `enonce`, `bonneReponse`,
`distracteurs`, `indice`, `explication`, `titre`, `consigne` de `catalogue.yaml` et des 20 paquets
de `adapters/adapter-content/src/main/resources/contenu/`, commentaires YAML exclus :

| | Occurrences |
|---|---|
| Espace **ordinaire** avant `?`, `!`, `;` ou `:` | **511**, reparties sur **17 fichiers** (16 paquets + `catalogue.yaml`) |
| Espace **insecable** avant l'un de ces signes | **1**, dans `cm1-reperes.yaml` |

**L'unique insecable du catalogue est celle que j'ai fait poser moi-meme** par `REP-6` a s20. Le
constat du relecteur est exact : **`cp-comparer` n'a devie d'aucune regle, il a suivi la pratique de
tout le catalogue.**

**(b) Le repli**, mesure dans un navigateur reel sur une **replique de la boite** de `.enonce`
(696 px de largeur offerte, police **700 32px**, pile de polices systeme de Reboot) et de
`.consigne` (400 20px), valeurs **lues du document servi le 2026-08-27** et non supposees :

```
CHROME=<navigateur> PORT_DEV=9453 python squad/outils/nadia-prof/chercher-un-signe-orphelin.py     squad/conformite/releves-s21/s21-nadia-signe-orphelin-744x551.json
```

Population : **les 402 textes distincts du catalogue qui portent une espace avant un signe**.
**204 se replient** a 696 px. Le repli n'est donc pas theorique. **L'outil et son releve sont
commites dans ce meme geste** : un chiffre dont l'outil n'est pas dans le depot est exactement ce
que je demande a l'equipe de ne plus produire.

**(c) Le signe orphelin.** Sur ces 402 textes, **14 placent le signe SEUL sur la derniere ligne** :

| Fichier | Signes orphelins | Niveau |
|---|---|---|
| `cm1-perimetre.yaml` | **9** | CM1 |
| `cp-problemes-additifs.yaml` | **5** | **CP** |
| **Total** | **14** | 5 CP, 9 CM1 |

**Les 14 sont des `enonce`**, aucun n'est un indice - verifie champ par champ, parce qu'un indice se
rend a 20px et non a 32px et qu'un faux positif y aurait ruine la mesure. **Les 14 sont tous des
points d'interrogation** ; aucun `!`, `:` ou `;` n'est orphelin aujourd'hui.

Exemples, tels qu'un enfant les voit :

- *"Quel est le perimetre d'un carre de cote 5 cm"* / **"?"** seul sur la ligne suivante ;
- *"Lea a 3 billes et en gagne 2. Combien en a-t-elle"* / **" ?"** seul sur la ligne suivante.

**(d) Confrontation au document reellement servi.** La replique reproduit la boite, pas le document.
Je l'ai donc confrontee au releve du **catalogue entier mesure dans l'application servie le
2026-08-27** (`squad/conformite/releves-s21/s21-nadia2-catalogue-entier-744x551.json`, 478 items) :
**les 14 enonces s'y replient egalement** (`hauteurEnonce` = 96 px et non 48), **14 sur 14, zero
divergence**. La replique est validee par le document servi.

## 2. Une correction que je porte contre moi, avant de m'appuyer sur `REP-6`

`REP-6` (2026-08-26) est redigee ainsi : *"L'item 15 emploie une espace ordinaire la ou la
typographie francaise demande une insecable - **et la ou le paquet voisin `cp-nature-mot` en emploie
32**."*

**Le chiffre est juste et le rapprochement est trompeur, et il est de moi.** Verifie a l'octet le
2026-08-28 : `cp-nature-mot.yaml` porte bien **32 espaces insecables**, mais **aucune n'est placee
devant un signe de ponctuation double** - **les 32 sont a l'interieur des guillemets francais**
(`«\u00a0chat\u00a0»`), un usage que `ADR-0060` avait deja tranche. Le meme fichier porte **zero**
insecable avant `?`, `!`, `;` ou `:`.

Ma phrase laissait croire que le catalogue employait deja l'insecable dans **cette** position et que
`cm1-reperes` s'en ecartait. **C'est l'inverse** : avant un signe double, la pratique du catalogue
etait uniformement l'espace ordinaire, et la seule exception est celle que `REP-6` a creee. Je
corrige avant de m'appuyer dessus - c'est la troisieme fois de ce sprint qu'un de mes propres ecrits
ne resiste pas a la verification, et le corriger avant de trancher est le minimum.

## 3. La decision

### `TYP-1` (2026-08-28) - la regle s'etend, et elle est ecrite comme une propriete, pas comme un procede

> **`TYP-1`** - Dans un texte lu par un enfant, **un signe de ponctuation double (`?`, `!`, `;`,
> `:`) ne doit jamais pouvoir se retrouver seul en debut ou en fin de ligne**, separe du mot qu'il
> accompagne.
>
> **Test verifiable** : au viewport de reference, **aucune ligne rendue d'un texte lu par un enfant
> ne se compose uniquement de ponctuation**. Ce test se lit dans le document rendu ; il ne suppose
> aucun jugement.

**J'ecris la propriete et non le moyen, et c'est delibere.** L'espace insecable (`U+00A0`) est le
moyen ordinaire et le moins risque - elle ne change rien a ce que l'enfant voit, seulement au droit
de couper. Mais **le choix du moyen appartient a `theo-archi` et `sofia-sm`**, pas a moi. C'est la
lecon que ce sprint m'a couteee trois fois : **j'ecris l'exemple qui illustre la propriete au lieu
d'ecrire la propriete**, et l'exemple se revele faux. `SEG-1` et le triplet
`crayon`/`feutre`/`pinceau` sont les deux precedents, dates, de ce meme defaut.

### Portee des signes

**La regle vaut pour `?`, `!`, `;` et `:`.** Le mecanisme est identique pour les quatre : une espace
secable devant un signe autorise la coupure. **Je precise le chiffre pour qu'on ne me lise pas comme
alarmiste : aujourd'hui, seul le `?` produit un orphelin, 14 fois.** Les trois autres ne coupent
sur aucun ecran servi. Ils entrent dans la regle parce que **rien ne les en distingue**, pas parce
qu'ils posent un probleme constate.

### Ce qui a fait pencher la decision, et ce qui ne l'a pas fait

- **Ce qui l'a fait pencher** : le defaut est **reel, visible et mesure sur 14 ecrans**, dont
  **5 servis a des enfants de CP**, c'est-a-dire a des lecteurs debutants pour qui un signe detache
  de sa phrase est un obstacle de lecture et non une coquetterie d'imprimeur. **Je ne pouvais pas
  ecrire que la regle ne s'etend pas apres avoir mesure cela.**
- **Ce qui ne l'a pas fait pencher** : le principe typographique seul. S'il n'y avait eu aucun repli
  nulle part, j'aurais tranche dans l'autre sens et borne `REP-6` a son item.
- **Une limite qui pousse dans le meme sens, et que je nomme** : **tout ceci est mesure a
  744 x 551 et a lui seul. 360 px n'est pas couvert**, et a cette largeur bien plus de textes se
  replient - le nombre d'orphelins y serait mecaniquement plus eleve, jamais moindre. **Je ne le
  chiffre pas parce que je ne l'ai pas mesure**, et je ne fais couvrir aucun chiffre par cette
  intuition.

## 4. L'application est bornee et ordonnee - ce n'est pas un geste de cloture

**511 occurrences sur 17 fichiers ne se corrigent pas a la cloture d'un sprint**, sur des paquets
`couvert-valide`, pour une regle dont **497 occurrences ne produisent aucun effet visible** au
viewport mesure. Ce depot a deja paye ce genre de balayage large et peu porteur. **L'ordre suit
l'effet mesure, et il tient en trois lots :**

| Lot | Perimetre | Occurrences | Motif |
|---|---|---|---|
| **1** | `cp-problemes-additifs.yaml` (5) et `cm1-perimetre.yaml` (9) | **14** | **Defaut visible et mesure.** Un signe seul sur sa ligne, aujourd'hui, sur un ecran servi. Les 5 de CP passent en premier : lecteurs debutants |
| **2** | les autres **enonces** qui se replient sans produire d'orphelin | le reste des **204** textes qui se replient | Aucun defaut visible aujourd'hui, mais **un mot de plus dans l'enonce deplace la coupure**. C'est une assurance, pas une correction |
| **3** | tout le reste, dont `cp-comparer.yaml` (18) et `catalogue.yaml` (21) | le solde des **511** | **Hygiene.** Ces textes ne se replient pas ; la regle n'y a aucun effet observable au viewport mesure |

**Ce que je demande pour la cloture de s21 : rien.** **Aucun lot n'est un prealable au Done
pedagogique d'aucun paquet**, et je ne rouvre aucun verdict rendu. `cp-comparer`, `cp-syllabes` et
`cp-mot-manquant` restent **valides sous reserve** ; `TYP-1` ne s'ajoute pas a leurs reserves comme
un motif, elle vaut pour le catalogue entier et se traite comme telle.

**Le lot 1 est une story a lui seul** et c'est le seul que je qualifierais d'urgent - au sens ou un
enfant le voit aujourd'hui. Les lots 2 et 3 sont du backlog ordinaire, a faire quand un fichier est
ouvert pour une autre raison plutot qu'en campagne dediee.

## 5. Ce que devient `REP-6`

**`REP-6` etait une exigence locale, et elle l'est restee jusqu'a aujourd'hui.** Sa justification
ecrite le 2026-08-26 tenait a **l'item 15 de `cm1-reperes`, dont la bonne reponse est "Cette phrase
est correcte."** : une phrase presentee a l'enfant **comme irreprochable** doit l'etre jusqu'a sa
typographie, parce que la typographie y est **l'objet meme du jugement demande a l'enfant**. **Cette
justification-la reste vraie et reste specifique** : elle vaudrait meme si `TYP-1` n'existait pas, et
elle vaudrait meme sur un texte qui ne se replie jamais.

**A compter du 2026-08-28, `REP-6` est absorbee par `TYP-1` sans etre annulee.** Les deux se lisent
ainsi, et je l'ecris pour qu'on ne relise pas `REP-6` comme generale au prochain sprint - **c'est
exactement ce qui est arrive a `SEG-1`, lue comme generale alors qu'elle etait trop large, et je ne
veux pas du defaut symetrique** :

- **`REP-6`** : locale, motivee par le **contenu** de l'item (une phrase donnee pour correcte).
  Elle exige la typographie juste **independamment de tout effet de rendu**.
- **`TYP-1`** : generale, motivee par le **rendu** (le signe orphelin). Elle exige qu'aucun signe ne
  se detache de sa phrase a l'ecran.

**Un item comme le 15 de `cm1-reperes` releve des deux ; la plupart des autres ne relevent que de
`TYP-1`.**

## 6. Ce qui appartient a d'autres, et que je ne touche pas

**Je n'ai touche aucun fichier de contenu.** Ni `cp-comparer.yaml`, ni `catalogue.yaml`, ni
`cm1-perimetre.yaml`, ni `cp-problemes-additifs.yaml`.

| Geste | A qui |
|---|---|
| `cp-comparer.yaml` et `catalogue.yaml` | **`sofia-sm`**, casquette developpeuse |
| `cm1-perimetre.yaml` et `cp-problemes-additifs.yaml` (lot 1) | l'auteur du paquet, designe par `sofia-sm` |
| Transformer les trois lots en stories, et les prioriser | **`camille-po`**. Je produis des motifs, je n'ecris pas le backlog et je ne repriorise pas |
| Choisir le **moyen** (insecable `U+00A0`, insecable etroite, ou autre) | **`theo-archi`** et `sofia-sm`. `TYP-1` dit la propriete, pas le procede |

**Sur un garde-fou : je n'en demande pas, et je dis pourquoi je le mentionne quand meme.**
`yanis-qa` a etabli que 3 conditions sur 27 seulement sont couvertes par un test, et `ADR-0028` a
tranche qu'un classifieur heuristique serait pire qu'une absence de garde. **Je ne reclame donc
rien.** Je note seulement, parce que c'est vrai et rare : **`TYP-1` est, de toutes mes conditions,
la seule qui soit exactement verifiable sans heuristique** - une ligne rendue composee uniquement de
ponctuation se constate, elle ne se juge pas. **Si `theo-archi` estime que cela merite un
garde-fou, la decision est la sienne et non une demande de ma part.**

## 7. Limites de cet arbitrage, avec l'etat exact qu'elles couvrent

- **Un seul viewport, 744 x 551.** **360 px n'est pas couvert** et le compte de 14 n'y vaut pas ; il
  y serait mecaniquement plus eleve.
- **La mesure du repli a ete faite sur une replique de la boite**, pas sur le document servi -
  limite levee par la confrontation du paragraphe 1(d), qui retrouve **14 sur 14** dans le releve du
  document reellement servi le 2026-08-27, sans divergence.
- **Aucun enfant n'a lu ces ecrans.** Que le signe orphelin gene un lecteur debutant est un
  **jugement de professeure**, pas une observation.
- **Le comptage exclut les commentaires YAML.** Un balayage brut du fichier rend d'autres chiffres :
  `cp-comparer.yaml` porte **19** occurrences de `" ?"` au fichier, dont **18** en texte lu par un
  enfant et **1 dans un commentaire**. Les deux chiffres sont vrais ; c'est la population qui
  differe. Le relecteur a compte le fichier, j'ai compte ce que l'enfant lit.
