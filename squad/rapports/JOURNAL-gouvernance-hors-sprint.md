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
