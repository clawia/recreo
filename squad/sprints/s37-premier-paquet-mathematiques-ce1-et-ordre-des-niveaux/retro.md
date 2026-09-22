# Retro - s37 - premier paquet mathematiques CE1 et ordre des niveaux

- **Date** : 2026-09-22.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Nature du sprint** : entierement pilote par agents, sans ceremonie de daily - conforme a
  `squad/README.md`. Huit worktrees provisionnes en tout (`s37-camille`, `s37-theo`, `s37-nadia`,
  `s37-theo-mecanique`, `s37-yanis`, `s37-nadia-audit`, `s37-camille-matrice`, `s37-sofia`), trois
  vagues de fusion. Ce document nomme ce qui a fonctionne dans cette orchestration et ce qui a
  coince.

---

## 1. Ce qui a marche

**Le blocage pedagogique pose au planning s'est referme avant tout developpement, dans l'ordre
prevu.** `S37-1` a ete gelee au planning comme *"bloquee sur deux reponses pedagogiques, pas
techniques"*, sans affecter de developpeur. `nadia-prof` a rendu son arbitrage prealable
(`ARBITRAGE-s37-operande-nul-et-forme-du-tirage-a-l-echelle-100.md`) avant qu'un seul item ne soit
ecrit, `theo-archi` l'a encode en `ADR-0096` (regle derivee de la forme des items, pas d'une liste
de paquets), et le paquet livre respecte les huit bandes a la lettre - verifie deux fois
independamment, par `theo-archi` en ecrivant le garde-fou et par `nadia-prof` en comptant sur la
liste des items avant d'ouvrir ce meme garde-fou. Aucune ligne de code n'a ete engagee avant que la
question pedagogique ne soit tranchee.

**`nadia-prof` a corrige sa propre mesure avant de s'appuyer dessus, dans le meme geste que son
audit.** Sa section 4.4 du 2026-09-21 annoncait qu'une partie sur trois environ ne contiendrait
aucune manche d'entree, a partir d'un modele de tirages independants ; en jouant 40 parties
reellement servies, elle a mesure 12 %, corrige explicitement le modele fautif ("la mecanique tire
sans remise, pas independamment") et n'a pas laisse son propre chiffre perime gouverner son
verdict. Meme discipline que celle deja notee pour d'autres agents a s35 et s36.

**Deux garde-fous neufs mordus sur le materiau reellement servi, pas sur une fixture.**
`yanis-qa` a mute le seul paquet CE1 en catalogue (operandes mis a zero pour `ADR-0096`, niveau
change en `"CE2"` pour `ADR-0095`) et restaure le fichier apres coup, verifie vide par `git diff`
- exactement la discipline de mutation sur objet reel qu'`ADR-0013` exige depuis s10.

**Trois lots de cloture disjoints par construction, verifies sans recoupement reel.** `s37-yanis`
(rapport qualite), `s37-nadia-audit` (fiche + registre), `s37-camille-matrice` (matrice de
couverture) ne touchaient chacun aucun fichier que les deux autres touchaient - verifie par diff
contre la base commune (`9412f659`) avant fusion, pas seulement sur l'enumeration du message de
routage. Les trois fusions se sont faites sans aucun conflit.

## 2. Ce qui a coince

**J'ai moi-meme reproduit, sous ma casquette Scrum Master, l'erreur de methode que ce depot a deja
sanctionnee a s11.** Apres la fusion de `s37-theo-mecanique`, j'ai recompte les tests par somme des
lignes `Tests run:` des rapports `.txt` Surefire - exactement la methode que `squad/README.md`
section 4 proscrit depuis l'amendement de s11, pour la raison precise qu'elle sous-compte les
classes melangeant `@ArchTest` et `@Test`. Le chiffre que j'ai annonce (1509) etait faux de 9,
exactement la taille de la cinquieme classe de ce type livree ce sprint
(`LOrdreDesNiveauxResteUneDonneeTest`). `yanis-qa` a trouve la cause et republi la methode correcte
- ce n'est pas la premiere fois que ce depot documente cette regle (s11, puis reaffirmee), et
c'est la premiere fois qu'elle me fait, moi, produire le chiffre faux plutot que de le detecter
chez un autre agent. Retenu pour la suite : blocs `Results:` ou somme `tests=` XML, jamais les
`.txt`, sans exception, y compris pour une mesure que je crois pouvoir faire vite.

**`MAT-CE1-18` reste sans ligne de matrice pour le deuxieme sprint consecutif.** Signale par
`nadia-prof` a la cloture de s36, note "pas une dette" par `camille-po` dans `DEC-0089` amendee, la
ligne est toujours absente de `squad/programme/MATRICE-couverture.md` a la cloture de s37 -
`camille-po` l'a de nouveau signale sans la corriger, cette fois en fusionnant un lot dont ce
n'etait pas le perimetre. Nommer un gap sans le corriger deux sprints de suite n'est pas encore un
report silencieux (il est ecrit a chaque fois), mais le motif se repete sans qu'aucune story ne
l'engage.

**`theo-archi` a livre `S37-1` entierement, hors du role habituel de l'avis de faisabilite.**
Comme pour `S36-ORDRE-NIVEAUX` au meme sprint, `theo-archi` a livre le paquet, le garde-fou et
l'`ADR` dans son propre worktree plutot que de s'arreter a l'avis de faisabilite et attendre une
affectation nominative de developpeur. Le resultat est verifie sans defaut (porte technique verte,
deux garde-fous mordus independamment par `yanis-qa` et `nadia-prof`), mais c'est la deuxieme fois
au meme sprint que la frontiere entre "instruire" et "livrer" se deplace sans qu'un planning ne
l'ait decide. Rien ne dit que cela ait coute quoi que ce soit ici ; rien ne garantit non plus que
la prochaine fois un developpeur affecte n'aurait pas fait un choix different sur un point que
l'`ADR` tranche (le paquet statique plutot que le generateur etendu, section 2 de l'avis de
faisabilite).

## 3. Amendements a `squad/README.md`

Aucun amendement propose. Le premier point (mon propre ecart de methode) ne revele pas un trou de
process : la regle est deja ecrite, deja appliquee par d'autres agents a d'autres sprints, et mon
propre role est de la faire respecter, pas de la reecrire parce que je l'ai moi-meme manquee une
fois. Le second (`MAT-CE1-18`) et le troisieme (le perimetre de `theo-archi`) sont des obstacles de
sprint, routables par les canaux existants (`camille-po` pour le premier via une story, discussion
de repartition de role pour le second si elle se reproduit une troisieme fois) plutot que des
lacunes du document lui-meme.

## Decisions prises

**Aucune decision de sprint neuve n'est prise dans cette retro.** Elle ne fait que constater ce qui
a fonctionne et ce qui a coince dans l'orchestration de s37 ; les decisions produit et
d'architecture du sprint (`ADR-0095`, `ADR-0096`) sont deja enregistrees dans `review.md`, section
"Decisions prises", et ne sont pas reprises ici une seconde fois.
