# DEC-0094 - `SCI-CM1-20` exige texte et tableau ensemble, l'`ADR` de support tableau est route a `theo-archi`

- **Sprint** : s40
- **Date** : 2026-09-23
- **Statut** : Acceptee
- **Contexte** : `theo-archi`, dans son avis de faisabilite sur `S40-EXTRACTION`
  (`squad/sprints/s40-couverture-maximale-emc-histoire-sciences/avis-faisabilite.md`, section 6),
  trouve la moitie *texte* de `SCI-CM1-20` faisable en l'etat et sa moitie *tableau* infaisable
  sans `ADR` (aucune cle `tableau` au format de contenu actuel, `texteSupport` replie les blancs et
  n'affiche donc pas une grille). Il pose la question de perimetre sans la trancher : le texte
  seul suffit-il a fermer la ligne, ou faut-il les deux formes ?

## Ce que je verifie avant de trancher

Le critere d'acceptation de `S40-EXTRACTION`, deja ecrit dans `docs/backlog/backlog.md` avant cet
avis, demande explicitement les deux formes : *"il prend exclusivement la forme d'un texte court
ou d'un tableau rendu en texte [...] jamais celle d'un schema ou d'un graphique"*, avec la mention
*"document textuel et tableau seuls"*. Ce n'est pas mon invention : c'est la reprise litterale de
l'arbitrage pedagogique prealable de `nadia-prof`
(`ARBITRAGE-PREALABLE-s32-...-le-prix-d-une-ligne-marginale.md`, ligne 1033), et la meme mention
est deja portee par `squad/programme/MATRICE-couverture.md`, ligne 2004, ecrite le 2026-09-03,
avant que quiconque instruise s40 : *"jouable au choix textuel, sur le texte et le tableau [...]
document textuel et tableau seuls ; le schema et le graphique restent non couverts"*.

`nadia-prof` a donc deja pose que la fermeture textuelle de cette ligne se fait sur **texte et
tableau ensemble**, le tableau etant compte dans le "textuel" au sens ou elle l'emploie ici (par
opposition au schema et au graphique, qui restent hors de portee). Rien dans cet arbitrage ne dit
que le texte seul, sans tableau, satisferait la meme mention.

## Decision

1. **Le texte seul ne suffit pas a fermer `SCI-CM1-20` avec la mention deja arbitree.** Les deux
   formes (texte et tableau) sont necessaires. Je ne reduis pas le critere d'acceptation de
   `S40-EXTRACTION` a la seule moitie texte : le faire reviendrait a modifier une mention de
   couverture que `nadia-prof` a deja fixee, ce qui n'est pas de mon ressort - `theo-archi` l'a
   lui-meme note dans son avis, section 6.3, a propos de sa voie V1.
2. **Le cout de l'`ADR` de support tableau n'est pas disproportionne au regard du gain.** Tel que
   `theo-archi` le scope (section 6.3 de son avis) : une composante de plus au `Question` du
   domaine, sur le patron des six precedentes deja actees par `ADR-0024`, `ADR-0050`, `ADR-0062`,
   `ADR-0073`, `ADR-0075` ; une cle de schema lue par `YamlContentPackLoader` ; un bloc de gabarit ;
   une regle CSS ; un garde-fou transposable d'`ADR-0087`. Aucune matiere ni aucun niveau neuf.
   C'est le cout d'une extension deja pratiquee six fois, pas d'une invention.
3. **`S40-EXTRACTION` reste dans le lot instruit par `DEC-0093`**, sur son perimetre complet
   (texte et tableau), et non reduite. L'`ADR` de support tableau est route a `theo-archi` : c'est
   lui qui le redige, avant le paquet, conformement a sa propre regle de section 4.2 de son avis
   ("l'ADR s'ecrit avant le paquet, pas apres").
4. **Le calendrier de livraison** (l'`ADR` et son implementation tiennent-ils dans s40, ou
   glissent-ils avec `S40-EXTRACTION` seule a un sprint suivant, pendant que les quatre autres lots
   du meme `DEC-0093` avancent) est un arbitrage de capacite de sprint. Il revient a `sofia-sm`,
   qui affecte deja nominativement le developpement de ces cinq lots (`DEC-0093`, section 5). Ce
   document ne le tranche pas.

## Ce que je ne tranche pas ici

- Le contenu de l'`ADR` (forme de la composante, cle de schema, gabarit) : `theo-archi`.
- Le Done pedagogique et toute reformulation de la mention de couverture : `nadia-prof` seule.
- `DEC-0089` et `DEC-0075` ne sont pas rouvertes.

## Routage

- `theo-archi` : ecrire l'`ADR` de support tableau avant tout paquet `SCI-CM1-20`.
- `sofia-sm` : arbitrer si `S40-EXTRACTION` tient dans s40 ou glisse, sans reduire son perimetre
  aux quatre autres lots deja affectes.
