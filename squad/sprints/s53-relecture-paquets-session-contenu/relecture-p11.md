# Relecture s53 - faits de P11 (`S53-REL-9`)

- **Auteur** : `relecteur-pedagogique`
- **Date** : 2026-10-07
- **Arbre mesure** : worktree `s53-rel-p11`, issu de `round-two` a `5c1353a9`. Aucune mesure n'est partielle : toutes
  sont des lectures de fichiers, aucun `mvn` n'a ete lance.
- **Decideur** : `nadia-prof`, arbitrage de l'utilisatrice du 2026-10-07 (provenance : consigne de l'agent lanceur de
  cette relecture, qui la rapporte ; je ne l'ai pas verifiee dans un fichier du depot). Ce document donne des faits et ne
  decide pas.

## 1. Les fichiers dont `competence` a une espace avant ":"

Commande, lancee a la racine du worktree :

```
grep -l ' competence: ".* :' adapters/adapter-content/src/main/resources/contenu/*.yaml
```

Sortie (13 fichiers, chemin raccourci du prefixe `adapters/adapter-content/src/main/resources/contenu/`) :

```
cm1-accord-sujet-verbe.yaml
cm1-arts-decrire-une-oeuvre-lexique.yaml
cm1-emc-principes-republique.yaml
cm1-hda-caracteristiques-techniques.yaml
cm1-hda-decrire-lexique.yaml
cm1-histoire-geographie-lire-un-document.yaml
cm1-histoire-geographie-nommer-en-geographie.yaml
cm1-histoire-geographie-nommer-en-histoire.yaml
cm1-histoire-geographie-situer-dans-le-temps.yaml
cm1-sciences-chaine-alimentaire.yaml
cp-decrire-une-oeuvre.yaml
cp-qlm-cycle-de-vie.yaml
cp-texte-narratif.yaml
```

**Ce que cette commande ne voit pas** : le motif `' :'` ne contient que l'espace ordinaire (U+0020). Un fichier dont
l'espace est insecable n'y figure pas (section 2). Le repertoire compte 182 fichiers `.yaml`, chacun avec une seule ligne
`competence:` (script de comptage, section 7).

## 2. Nature de l'espace, par les octets

Commande (pour chaque fichier de la section 1) : on isole le caractere qui precede " :" sur la premiere ligne
`competence:`, et on l'affiche en hexadecimal.

```
grep -m1 '^ *competence:' <fichier> | LC_ALL=C grep -o '[^ ] :' | od -An -tx1
```

Resultat : les 13 fichiers de la section 1 se terminent par `20 3a` (octet `20` = espace ordinaire U+0020, octet `3a` =
":"), precede de la derniere lettre du mot (par exemple `73 20 3a` pour "s :", `a9 20 3a` pour un "e" accentue en UTF-8
suivi de l'espace). **Les 13 sont des espaces ordinaires U+0020.**

Contre-verification, qui cherche l'autre forme : les octets `c2 a0` (U+00A0 en UTF-8) juste avant ":" sur une ligne
`competence:` :

```
LC_ALL=C grep -q "^ *competence:.*$(printf '\xc2\xa0'):" <fichier>
```

Quatre fichiers de plus en portent, tous quatre ajoutes le 2026-10-07 par s53 :

| Fichier | Espace | Ajoute par |
|---|---|---|
| `ce1-accord-groupe-nominal.yaml` | U+00A0 | `34fdd167` (`[MARC]`) |
| `ce1-classes-de-mots.yaml` | U+00A0 | `34fdd167` (`[MARC]`) |
| `ce1-inference.yaml` | U+00A0 | `8561498c` (`[LEA]`) |
| `ce1-texte-narratif.yaml` | U+00A0 | `8561498c` (`[LEA]`) |

Aucun fichier ne porte l'espace fine insecable U+202F ni la fine U+2009 avant ":" dans `competence` (balayage des trois
formes). Etat du champ, sur les 182 fichiers : **17 `competence` contiennent ":", 13 avec U+0020, 4 avec U+00A0, 0 sans
espace avant** ; les deux conventions coexistent donc deja.

Les quatre fichiers que P11 nomme sont de la premiere famille (U+0020) : `cm1-arts-decrire-une-oeuvre-lexique` (ligne 23),
`cm1-emc-principes-republique` (ligne 8), `cm1-hda-caracteristiques-techniques` (ligne 24), `cm1-hda-decrire-lexique`
(ligne 24). Ils ont ete ajoutes le 2026-10-07 par `10bad629` (`[LEA]`, les trois autres) et `d27ba381` (`[MARC]`,
`principes`). Les neuf autres fichiers de la liste datent du 2026-08-24 (`cm1-accord-sujet-verbe`, `1ff200dc`) au
2026-10-04 (`cp-decrire-une-oeuvre`, `1db6348f`).

## 3. Ecart entre les 13 fichiers mesures et les "onze plus anciens" du backlog

Le backlog (`docs/backlog/`, story `S53SPE-RELECTURE`, point P11) dit : *"l'espace avant ":" est ordinaire (onze paquets
plus anciens font de meme)"*. La mesure ci-dessus rend, hors les quatre fichiers du point, **neuf** fichiers a espace
ordinaire, tous plus anciens que `10bad629`. Elle rend en plus **quatre** fichiers a espace insecable, de la meme date
que les quatre du point.

**Je n'explique pas l'ecart de onze a neuf.** Verifie : ni 9 + 4 = 13 ni aucun autre regroupement des 17 fichiers a ":" ne
donne 11. Je ne connais pas la commande du backlog, je ne peux donc pas dire si elle comptait autre chose (un autre champ
que `competence`, ou une ponctuation autre que ":"). Fait voisin, mesure et non explicatif : quatre autres fichiers ont
une espace avant ";" dans `competence` (ordinaire dans `cm1-sciences-etats-matiere` et `cp-semaine`, U+00A0 dans
`ce1-comparer-ranger` et `ce1-graphie`) ; ils ne sont pas dans les 13 et ne font pas 11 non plus.

## 4. La regle de ponctuation posee pour ce champ

- **`CLAUDE.md`, "Conventions de redaction"** : la liste des textes gouvernes par le relecteur nomme code, commentaires,
  Javadoc, messages d'erreur techniques, README, `squad/`, ADR, DEC, fiches, commits, noms de fichiers. Le contenu destine
  a l'enfant en est exclu et releve de `nadia-prof`, **sauf** une exigence commune : texte "en francais correct et
  integralement accentue, orthographe et ponctuation irreprochables, parce qu'il est lu par un enfant". La section ne dit
  rien de l'espace avant un signe double, ni de `competence`.
- **`DEC-0002`** (amendement du 2026-08-04) : meme exigence, posee pour "tout texte de categorie B" (enonces, consignes,
  retours, libelles de bouton, messages d'etat). `competence` n'y est pas nomme.
- **`ADR-0067`** : le moyen de `TYP-1` ("un signe de ponctuation double (`?`, `!`, `;`, `:`) ne doit jamais pouvoir se
  retrouver seul en debut ou en fin de ligne") vit dans la donnee servie. Le choix du caractere (U+00A0, U+202F, ou retrait
  de l'espace) **reste ouvert** dans l'ADR. Son garde-fou est un cliquet sur "les chaines que l'enfant lit"
  (`AucunSigneDoubleNEstDetachableDeSonMotTest`).

**Ce champ traverse-t-il le port entrant ? Oui. S'affiche-t-il a l'enfant ? Pas dans l'etat du code lu.** Preuve :

1. `YamlContentPackLoader.java` ligne 89 lit `competence` dans le descripteur, qui devient
   `ContentDescriptor(matiere, niveau, domaine, competence)`.
2. `GameDefinition` (`application/.../catalog/GameDefinition.java`) porte ce descripteur, et `PartieUseCase` (port entrant)
   rend `List<GameDefinition>` (`jeuxDisponibles()`, `jeuxDuNiveau()`). **Le champ est donc expose au port entrant**, et
   `TexteAccentueExposeParLePortEntrantTest` l'inventorie par reflexion.
3. Dans le code de production, la seule lecture du descripteur est `jeu.descripteur().niveau()` (`PartieService.java`
   lignes 53 et 65). `grep` de `competence` et de `descripteur` dans `adapters/adapter-web/src/main` ne rend que des
   commentaires de feuille de style ; les gabarits ne lisent que `jeu.titre()` et `jeu.id()` (releve des `${jeu.xxx()}`
   dans `templates/`). **Aucune vue n'affiche `competence`.**
4. Le cliquet de `TYP-1` ne compte pas `competence` : sa Javadoc (`LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest`)
   exclut "le titre, la consigne et le descripteur" des textes d'items, et le cliquet n'ajoute au catalogue que
   `jeu.titre()` et `jeu.consigne()`. Je n'ai pas lu `textesServis` ligne a ligne : cette exclusion est lue dans la
   Javadoc et le code du cliquet, non verifiee par une mutation.

Conclusion de fait, sans decision : `competence` est un champ du catalogue destine a la squad (la ligne de matrice visee), que
la regle `TYP-1` ne mesure pas ; le texte de `DEC-0002` ne dit pas si un champ expose au port mais non affiche releve de la
categorie B. C'est le point que `nadia-prof` tranche.

## 5. Les deux issues et leur cout

| Issue | Fichiers touches | Geste |
|---|---|---|
| **Corriger les fichiers** | **13** (ou **4** si la correction se limite aux quatre fichiers que P11 nomme ; les 9 autres sont des paquets d'avant s53, hors du point) | 1 ligne par fichier, 1 caractere : U+0020 devient U+00A0 (ou U+202F si le choix d'`ADR-0067` va a l'etroite, ce qui change aussi les 4 fichiers deja a U+00A0) ou l'espace est retiree |
| **Accepter la convention** | **0** | une decision ecrite par `nadia-prof` qui nomme la forme admise pour `competence` |

Faits qui bornent le cout, mesures ou lus :

- Les 4 fichiers du point ont une seule ligne concernee chacun (lignes 23, 8, 24, 24).
- Accepter U+0020 laisse deux formes dans le meme champ (13 contre 4). Unifier sur U+0020 coute 4 fichiers (ceux a
  U+00A0) ; unifier sur U+00A0 coute 13.
- Deux gardes-fous lisent `competence` : `AucunMotServiNeSEcarteDeSonAccentTest` (accentuation, via `textesDuCatalogue`) et
  `ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest` (paquets de mathematiques CE1, qui ne sont pas dans les 13).
  Que remplacer l'espace modifie le verdict du premier n'est **pas mesure** ; la suite n'a pas ete lancee. Le cliquet
  de `TYP-1` n'est pas concerne selon la lecture du point 4 ci-dessus.
- Si la decision touche `cm1-emc-principes-republique.yaml`, l'ordre du planning est P3, P4, P11, puis P9, un commit
  par point.

## 6. Ce que je ne tranche pas

- Laquelle des deux issues, et quel caractere (U+00A0, U+202F, retrait) : `nadia-prof`.
- Que le 11 du backlog soit faux ou que le 9 mesure le soit : seule la commande du backlog le dirait.
- Si un champ expose au port mais jamais rendu est de la categorie B de `DEC-0002` : `nadia-prof`.

## 7. Methode

Le comptage des 182 fichiers, des 17 lignes a ":" et des ";" a ete fait par un script Python de lecture seule
(octets, expressions regulieres sur `\x20`, `\xc2\xa0`, `\xe2\x80\xaf`, `\xe2\x80\x89`), jetable et non commite. Les
sections 1 et 2 se refont avec les `grep` donnes. Aucun YAML n'est modifie par ce document.
