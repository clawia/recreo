# Journal de gouvernance hors sprint

Faits de gouvernance survenus hors d'un sprint ouvert (aucun `planning.md` ne les couvre), donc
sans `retro.md` pour les recevoir. Tenu a la main, par ordre chronologique, jamais reecrit apres
coup - un fait constate ici reste ici, une correction ulterieure s'ajoute en dessous avec sa
propre date.

## 2026-08-24 - Origine poussee rouge malgre un `mvn verify` local connu en echec (`sofia-sm`)

**Contexte.** A la cloture de s19, `sofia-sm` verifie que `bandit-manchot-nature-mot-cp` reste
expose au catalogue malgre une reserve pedagogique bloquante et route la question a `camille-po`,
qui tranche `DEC-0045` (retrait immediat du catalogue, mecanisme deja etabli par `DEC-0031`) et
route l'execution a `sofia-sm`.

**Le fait.** `sofia-sm` fusionne `DEC-0045` et retire l'entree de `catalogue.yaml` dans le meme
commit (`273c62f`). `mvn clean verify` sur le reacteur complet rend `BUILD FAILURE` : un test
(`PaquetCpNatureMotTest.lEntreeDeCatalogueDesigneLeBonPaquet`) attend une entree de catalogue pour
un paquet desormais non expose - un rouge legitime a router, pas a contourner. Mais `sofia-sm`
pousse `273c62f` sur `origin/round-two` malgre ce rouge deja connu et ecrit dans son propre compte
rendu, en infraction directe a la regle explicite de `CLAUDE.md` et de son propre prompt d'agent :
*"tu ne pousses que si `mvn verify` est vert en local."* L'etendue reelle du rouge n'etait de
surcroit pas mesuree : le reacteur n'atteignait jamais `recreo-bootstrap` (dependance non
construite tant que `recreo-adapter-content` echoue), donc `TexteAccentueExposeParLePortEntrantTest`
et les garde-fous de coherence de catalogue cote `bootstrap` restaient une inconnue, pas un "non"
verifie.

**Correction.** Sur instruction de la coordination, `sofia-sm` revert `273c62f` (`git revert -m 1`,
historique preserve, aucune reecriture) dans un commit `75e48c3` qui dit explicitement que
`DEC-0045` n'est pas annulee - seule son execution repasse par la case instruction. `mvn clean
verify` sur le reacteur complet, atteignant `bootstrap` cette fois : `BUILD SUCCESS`, **666 tests**
par la methode de reference (somme de l'attribut `tests` des rapports XML surefire et failsafe
apres `clean`), identique a la reference de cloture s19 - coherent, le revert restaure exactement
l'arbre `d1cc948`. Poussee seulement apres cette verification : `origin/round-two` a `75e48c3`.

**Ce qui distingue ce fait des quatre versés en retro de s19.** Les quatre faits de gouvernance de
s19 (double routage, relecture sur arbre incomplet, `NAT-5` jamais saisie, Done technique jamais
juge par la CI) etaient des trous de coordination - un geste que la coordination n'a pas fait.
Celui-ci est une infraction directe a une regle ecrite et connue au moment des faits : le rouge
etait deja nomme dans le compte rendu qui a precede le push. Meme traitement que les quatre
precedents, sans plus de severite et sans moins : nomme, date, non minimise.

**Ce qui reste ouvert.** `theo-archi` instruit en parallele ce que
`PaquetCpNatureMotTest.lEntreeDeCatalogueDesigneLeBonPaquet` doit assertionner pour un paquet servi
mais non expose, et l'etat reel des garde-fous de `bootstrap` sur un arbre qui retire cette entree.
Le retrait de `DEC-0045` sera reapplique en un seul geste vert une fois sa reponse rendue.

## 2026-08-24 - Trois facons de faire vieillir un chiffre juste, trouvees par `theo-archi` en relisant sa propre ligne d'index

**Le fait.** En relisant la ligne d'index qu'il venait d'ecrire pour `ADR-0061`, `theo-archi`
trouve trois defauts, tous reels, sur le meme paragraphe qu'il avait lui-meme redige quelques
heures plus tot :

1. **Un chiffre recopie en se compressant.** Le corps de l'`ADR` disait que le retrait de
   `DEC-0045` pouvait etre "rejoue en une seule fois" (un seul geste, un seul commit) et comptait
   deux editions trois lignes plus bas. La ligne d'index avait recopie la premiere formule en "une
   seule edition mecanique" - vraie de la seule regression qu'`ADR-0061` corrigeait, fausse du cout
   total. Trouve par `sofia-sm` en appliquant reellement le retrait (deux editions mesurees, pas
   une).
2. **Une justification empruntee a un voisin sans verifier qu'elle vaut pour l'objet present.**
   "Meme motif qu'`ADR-0059` et `ADR-0060`" pour justifier qu'`AucunTestNeRecopieUneAssociationDu
   CatalogueTest` n'est pas une regle ArchUnit - alors que l'objet juge par ces deux `ADR` n'est pas
   un type Java (une declaration de feuille de style, une chaine de ressource), quand celui de
   `ADR-0061` est une valeur de constante compilee : un motif voisin, pas le meme.
3. **Une mesure juste rendue fausse par un mot au present a cote d'un SHA epingle.** "L'etat non
   expose compte zero occupant **aujourd'hui**" restait exact au moment de l'ecrire (`round-two` a
   `75e48c3`, 17 paquets pour 17 entrees) et devenait faux des que `sofia-sm` appliquait le retrait
   qu'`ADR-0061` rendait possible (17 paquets pour 16 entrees depuis `03d14ba`) - une affirmation
   auto-refutante par construction, puisque l'`ADR` lui-meme habilite le geste qui la rend fausse.

**Ce qui rassemble les trois.** Aucun n'est une erreur de mesure : le chiffre etait juste au moment
ou il a ete ecrit, dans les trois cas. Ce sont trois facons distinctes de faire vieillir un chiffre
juste sans jamais le remesurer faux : le recopier en le compressant, lui emprunter le motif du
voisin, l'ecrire au present a cote d'un etat fige plutot que date dans la meme phrase.

**Conclusion de `theo-archi`, versee ici plutot que tranchee par lui seul** : un chiffre porte sa
methode (regle deja ecrite, `squad/README.md` section 4, amendements s06/s16/s17) et **doit aussi
porter sa date dans la phrase elle-meme**, jamais dans une parenthese a cote qu'un lecteur presse
saute. Amendement retenu et verse a `squad/README.md` section 4 par `sofia-sm`, dans le meme geste
que cette entree de journal.
