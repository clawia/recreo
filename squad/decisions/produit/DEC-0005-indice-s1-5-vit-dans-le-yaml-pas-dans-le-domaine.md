# DEC-0005 - L'indice affiche apres trois echecs sur le meme item est un contenu YAML, jamais une constante du domaine

- **Type** : perimetre produit (contenu vs code)
- **Date** : 2026-08-05
- **Auteur** : camille-po
- **Sprint** : s01
- **Competence programme visee** : transverse - comportement generique de la mecanique bandit
  manchot face a l'erreur repetee (meme perimetre que S1-5), applicable a tout contenu futur qui
  utilisera un indice.
- **Statut** : Acceptee

## Contexte

En review du sprint s01, avant passage devant `nadia-prof`, je releve que S1-5 (un item rate
trois fois revient avec un indice) a ete implementee en codant en dur, dans
`domain/src/main/java/com/recreo/domain/content/source/AdditionsBorneesContentSource.java`
ligne 34, la constante :

```java
private static final String INDICE = "Compte avec tes doigts pour t'aider.";
```

C'est un texte pedagogique neuf destine a l'enfant. Ni le backlog (`S1-5`,
`docs/backlog/backlog.md`), ni `DEC-0004` (qui fixe les deux seuils de S1-5 mais ne formule ni ne
route aucun texte d'indice, point 4) ne l'avaient prevu. Il n'est passe par aucune des deux portes
que pose `CLAUDE.md` pour tout contenu neuf : ni la porte produit (aucune ligne de backlog, aucune
`DEC`, aucune entree de `squad/conformite/FICHE-bandit-manchot-additions-cp.md` avant ce jour), ni
la porte pedagogique de `nadia-prof`.

## Decision

Je tranche la seule question qui m'appartient : ou ce texte doit vivre, pas s'il est bon.

**Un indice destine a l'enfant est du contenu, jamais une constante du domaine.** Il doit venir du
paquet de contenu YAML
(`adapters/adapter-content/src/main/resources/contenu/cp-additions-jusqu-a-10.yaml`), lu par la
fabrique qui construit `AdditionsBorneesContentSource`, exactement comme `sommeMaximale` et
`nombreDePropositions` y sont deja portes sous la cle `parametres` (`ADR-0006`). Je ne tranche pas
le nom exact de la cle YAML ni le detail de son cablage : c'est une decision technique, hors de
mon mandat, qui revient a `theo-archi` / `lea-dev`.

Motifs :

1. **Un precedent existe deja dans ce meme jeu pour exactement ce type de texte.** La consigne
   "Combien ça fait ?", lue par l'enfant a chaque manche, vit dans `catalogue.yaml`, pas dans le
   code Java de la mecanique ni du contenu. Un indice est un texte de meme nature - une phrase
   destinee a l'enfant, vouee a etre reformulee sans toucher au comportement du jeu - et doit
   suivre le meme chemin.
2. **`ADR-0006` a deja pose la regle pour ce cas precis.** Les parametres propres a l'unique
   implementation de `ContentSource` d'un contenu combinatoire "vivent dans son propre paquet YAML
   sous 'parametres' et sont lus par sa seule fabrique - aucun autre contenu ni aucune mecanique ne
   les voit." Un texte d'indice est un parametre de ce genre : propre a ce contenu, sans portee sur
   la mecanique generique (`Question.indice()` est deja typee `Optional<String>` et sa Javadoc
   documente deja que l'indice "vient du contenu, jamais de la mecanique" - la constante Java
   contredit cette documentation ecrite dans le meme module).
3. **C'est exactement la dette listee par `CLAUDE.md`, a ne pas reproduire : un comportement
   pedagogique loge la ou seul le code source, pas le contenu, peut le faire evoluer.** Aujourd'hui,
   corriger ou reformuler cet indice - ce que `nadia-prof` peut demander a l'audit - exige de
   modifier une classe Java du domaine et de reconstruire le module, alors qu'editer une ligne de
   YAML ne le demande pas. Ce n'est pas une preference de style : c'est le principe directeur du
   produit (mecanique reutilisable decouplee d'un contenu pilote par des regles) qui est
   directement en jeu.
4. **Je ne juge pas le texte lui-meme.** "Compte avec tes doigts pour t'aider." peut etre un tres
   bon indice pour un enfant de CP : ce jugement appartient a `nadia-prof`, seule autorite sur le
   Done pedagogique. Cette decision porte uniquement sur l'emplacement du texte, pas sur sa
   qualite.

### Le texte actuel est accepte comme candidat a soumettre a nadia-prof, sans attendre la correction de perimetre

Le rendu montre a l'enfant est identique, que le texte vienne d'une constante Java ou d'une entree
YAML : deplacer l'indice ne change rien a ce que `nadia-prof` verra en jouant l'increment. Je
n'impose donc pas de deplacer le code avant sa revue de ce sprint : elle peut juger "Compte avec
tes doigts pour t'aider." en l'etat, comme n'importe quel autre texte de ce jeu. C'est un jugement
disjoint de mon prealable produit.

## Impact

- `domain/src/main/java/com/recreo/domain/content/source/AdditionsBorneesContentSource.java` : la
  constante `INDICE` doit disparaitre, remplacee par un champ recu au constructeur (meme mecanisme
  que `sommeMaximale`, `nombreDePropositions`), au plus tard a l'ouverture du sprint s02. Je
  n'ecris pas ce code, il appartient a `lea-dev` / `theo-archi`.
- `adapters/adapter-content/src/main/resources/contenu/cp-additions-jusqu-a-10.yaml` : une entree
  nouvelle porte le texte d'indice, au meme niveau que `sommeMaximale` et `nombreDePropositions`.
- `squad/conformite/FICHE-bandit-manchot-additions-cp.md` : ligne ajoutee aux criteres
  pedagogiques attendus, signalant l'indice comme contenu neuf a auditer (mise a jour du meme
  jour que cette decision).
- `docs/backlog/backlog.md`, S1-5 : lien vers cette decision ajoute a cote de `DEC-0004`.
- Aucun changement a `squad/programme/MATRICE-couverture.md` : cette decision ne porte pas sur une
  competence, la ligne mathematiques CP reste `en cours`.

## Bloquant pour la cloture de s01, ou backlog de s02 ?

**Non bloquant pour la cloture de s01.** L'objectif gele du sprint
(`squad/sprints/s01-lever-refus-pedagogique/planning.md`) est de lever les trois motifs de refus
(S1-1 a S1-3) ; S1-4 et S1-5 sont des reserves explicitement non bloquantes pour la validation de
cet increment (`docs/backlog/backlog.md`, sprint 1, section Priorisation). Le defaut trouve ici
est un defaut de perimetre sur une reserve deja non bloquante, pas sur un des trois motifs geles.
Le deplacer maintenant retarderait la revue de ce sprint pour un gain nul sur ce que `nadia-prof`
doit juger cette semaine (voir la section precedente : le rendu est identique).

**Correction portee au backlog de s02**, en tete de sprint plutot qu'en fin de liste, parce
qu'elle touche un principe directeur du produit et une dette explicitement listee par `CLAUDE.md`
a ne pas laisser s'installer. Une story corrective sera ecrite dans `docs/backlog/backlog.md` a
l'ouverture du planning de s02.

## Ce qui reste non testable par construction

- Que l'indice aide reellement l'enfant plutot que de le frustrer davantage reste un jugement
  pedagogique de `nadia-prof`, deja identifie comme tel par `DEC-0004`, point 4. Cette decision
  n'y ajoute rien.
- Que le texte finalement retenu (apres audit) soit au bon niveau de langue pour un CP est, de la
  meme facon, hors de mon mandat.
