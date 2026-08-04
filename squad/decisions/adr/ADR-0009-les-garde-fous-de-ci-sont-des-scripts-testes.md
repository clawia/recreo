# ADR-0009 - Extraire les garde-fous de CI dans des scripts versionnes, et leur donner des contre-exemples

- **Statut** : Accepte
- **Date** : 2026-08-04
- **Auteur** : theo-archi
- **Sprint** : s00

## Contexte

Le troisieme critere d'acceptation de la story S0-1 demande que la CI echoue explicitement sur un
commit sans prefixe d'agent connu. Le garde-fou existe : c'est une trentaine de lignes de shell
ecrites en clair dans la section `run:` d'une etape de `.github/workflows/ci.yml`.

`yanis-qa` l'a verifie a la main sur six sujets de commit types (prefixe connu, inconnu, absent, en
minuscules, sans espace, commit de fusion) et il se comporte correctement. Mais rien ne le protege
d'une regression : si quelqu'un touche a la regex demain, personne ne l'apprend avant qu'un commit
fautif traverse la CI sans etre vu. Et la seule facon de constater qu'il fonctionne encore est
qu'il refuse quelque chose, c'est-a-dire au moment ou il est trop tard pour decouvrir qu'il ne
refuse plus rien.

C'est exactement la forme de la **dette n°7 de `main`** : la documentation y affirmait que la CI
rejetait les commits sans prefixe d'agent, et le workflow ne contenait aucune etape de ce genre. Sur
`round-two` l'etape existe. Ce qui manque encore, c'est la preuve qu'elle mord - la meme exigence
qu'ADR-0003 impose aux regles ArchUnit, appliquee a un garde-fou qui n'est pas en Java.

Deux proprietes distinctes sont en jeu, et les confondre est ce qui rendait le garde-fou
intestable :

1. **quelle plage de commits est jugee**, qui depend du contexte GitHub (`push` ou `pull_request`,
   branche neuve sans base comparable) et qu'on ne peut pas evaluer hors de GitHub Actions ;
2. **quel verdict est rendu sur un sujet de commit donne**, qui ne depend de rien d'autre que du
   sujet, et qui est donc testable partout - y compris sur le poste d'un developpeur.

Tant que les deux vivent dans le meme bloc `run:`, la seconde est prisonniere de la premiere.

## Decision

**Un garde-fou de CI qui rend un verdict est extrait dans un script versionne, et ce script a ses
contre-exemples, executes en CI comme une etape a part entiere.**

Appliquee au garde-fou de prefixes :

1. Le verdict vit dans `.github/scripts/verifier-prefixes.sh`. Il lit des sujets de commit sur
   l'entree standard, un par ligne, et ne connait rien du contexte GitHub. La liste des tags valides
   y est ecrite une fois, et une seule.
2. Le choix de la plage reste dans le workflow, seul endroit qui connait `github.event`. Le workflow
   se reduit a `git log --no-merges --format=%s ${RANGE} | bash .github/scripts/verifier-prefixes.sh`.
   Les prefixes acceptes, la plage comparee, le message d'erreur et le code de sortie sont
   inchanges : c'est un deplacement, pas une reecriture.
3. Les contre-exemples vivent dans `.github/scripts/verifier-prefixes-test.sh`, quatorze cas dont
   dix doivent etre **refuses** par le garde-fou. Le workflow l'execute **avant** l'etape de
   verification : une regex cassee est rouge sur le test, pas sur un commit fautif qui passe.

Le script de test ne depend d'aucun outil hors `bash` et `grep`, deja presents partout ou le
workflow tourne. Pas de `bats`, pas de dependance a installer : le cout d'un cadre de test shell
serait superieur au cout du garde-fou qu'il verifie.

## Regle executable

La decision n'est pas encodable en ArchUnit : ArchUnit lit du bytecode, et il n'y a pas de bytecode
ici. Le mecanisme automatique qui la garde est le script de contre-exemples lui-meme, invoque par la
CI au meme titre que `mvn verify`.

Fichier : `.github/scripts/verifier-prefixes-test.sh`

```bash
verifier "un prefixe connu est accepte" 0 '[THEO] socle
' "Tous les commits portent un prefixe d'agent connu."

verifier "un prefixe inconnu est refuse et le sujet est cite" 1 '[BOB] socle
' "::error::message sans prefixe d'agent connu : [BOB] socle"

verifier "un prefixe en minuscules est refuse" 1 '[theo] socle
'

verifier "un prefixe connu ailleurs qu'au debut est refuse" 1 'correction [THEO] socle
'

verifier "un seul sujet fautif parmi plusieurs suffit, et il est compte" 1 '[THEO] a
[BOB] b
[LEA] c
' "1 commit(s) sans prefixe."
```

Etape de workflow correspondante, dans `.github/workflows/ci.yml`, job `prefixes` :

```yaml
      - name: Tester le garde-fou de prefixes
        run: bash .github/scripts/verifier-prefixes-test.sh
```

Comme pour un contre-exemple ArchUnit, un cas qui echoue ne suffit pas : il doit echouer **pour le
bon motif**. Chaque cas attendu en echec verifie donc, en plus du code de sortie, la presence du
message attendu, sujet fautif cite. La demonstration de non-vacuite a ete faite par mutation du
script juge, et chacune de ces cinq mutations rend le test rouge :

| Mutation du garde-fou | Cas qui devient rouge |
|---|---|
| ancre `^` retiree de la regex | un prefixe connu ailleurs qu'au debut est refuse |
| `grep -Eq` devient `grep -Eqi` | un prefixe en minuscules est refuse |
| le compteur de fautifs n'est jamais incremente | dix cas |
| un tag inconnu ajoute a la liste | cinq cas |
| la ligne vide n'est plus ignoree | une ligne vide n'est pas un commit fautif |

Un point d'ecriture, qui est le pendant shell du piege de la couche vide d'ADR-0001 : le garde-fou
lit son entree avec `while IFS= read -r sujet || [ -n "${sujet}" ]`. Sans la seconde condition, un
dernier sujet sans retour a la ligne final serait **silencieusement ignore** - un commit non juge,
et aucun signal. Le cas est dans le script de test.

## Consequences

- Le garde-fou est desormais executable et testable hors CI : `bash
  .github/scripts/verifier-prefixes-test.sh` tourne sur le poste d'un developpeur en une seconde,
  sans GitHub.
- Trois fichiers la ou il y en avait un. C'est le meme cout qu'ADR-0003 fait payer aux regles
  ArchUnit, pour la meme raison.
- La plage de commits comparee reste, elle, non testee : elle depend de variables que seul GitHub
  Actions fournit. Je le nomme comme une limite assumee plutot que de fabriquer un simulacre de
  contexte GitHub, qui testerait mon simulacre et non la CI. Ce qui reste donc possible est un
  garde-fou qui juge correctement la mauvaise plage de commits. La contrepartie est faible : la
  plage est de six lignes, sans regex, et son mode de defaillance visible (elle juge trop peu de
  commits) se voit dans le journal de l'etape, qui affiche ce qu'elle a juge.
- Le job `prefixes` peut maintenant echouer pour deux raisons distinctes, et l'ordre des etapes les
  separe : d'abord "le garde-fou ne se comporte pas comme annonce", ensuite "un commit n'a pas de
  prefixe".
- Cette decision vaut pour tout garde-fou de CI a venir qui rend un verdict (par exemple un controle
  de convention de nommage de fichiers de contenu). Elle ne vaut pas pour les etapes de CI qui ne
  font qu'invoquer un outil : `mvn verify` n'a pas besoin d'un script d'enveloppe.

## Alternatives considerees

- **Laisser le shell dans le workflow et s'en remettre a la relecture.** C'est la situation qui a
  produit la dette n°7. Le cout d'une regression est un commit non conforme qui entre dans
  l'historique, et surtout la perte de confiance dans tous les autres garde-fous annonces. Ecarte.
- **Reecrire le garde-fou en Java, dans un test JUnit qui lit `git log`.** Le garde-fou serait alors
  couvert par `mvn verify`, ce qui est seduisant. Mais un test qui invoque `git` depuis Java est
  fragile (disponibilite de `git`, historique tronque, `fetch-depth`), et surtout ce n'est plus le
  meme code qui garde et qui est teste : la CI continuerait d'executer le shell. Ecarte : cela
  aurait teste une deuxieme implementation du meme verdict, ce qui est la pire des situations.
- **Un cadre de test shell (`bats`).** Il faudrait l'installer dans le job, donc dependre d'un
  paquet externe pour quatorze assertions. Le script de test fait 60 lignes et n'a besoin de rien.
  Ecarte, revisable si les garde-fous en shell se multiplient.
- **Un hook Git local `commit-msg`.** Utile en confort, sans valeur en gouvernance : un hook n'est
  pas versionne dans le depot cloné et se contourne par `--no-verify`. Ecarte comme substitut,
  possible comme complement si quelqu'un le demande.

## Ce qui me ferait changer d'avis

- Si un troisieme garde-fou en shell apparaissait, je regrouperais les cas de test dans un seul
  script d'entree et j'envisagerais alors `bats`.
- Si GitHub Actions offrait un jour un moyen de tester une etape de workflow, plage de commits
  comprise, je deplacerais la limite assumee ci-dessus.
