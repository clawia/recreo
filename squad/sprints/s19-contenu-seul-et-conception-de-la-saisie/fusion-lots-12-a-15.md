# S19 - fusion des lots 12 a 15 sur round-two (Done pedagogique, relecture, matrice, correctif)

Suite de `fusion-lots-10-a-11.md`. Quatre lots fusionnes, trois d'entre eux editant les memes
fiches de conformite en parallele - deux collisions dues au sequencement de la coordination, pas
au travail des agents. Toujours pas une review : ce fichier ferme le Done pedagogique de premiere
passe, la relecture, la matrice et le correctif ; reste la reaudition d'un paquet avant les
ceremonies.

## Les collisions, verifiees avant resolution

**Sur les quatre fiches de conformite**, `nadia-prof` (forkee de `51301d7`, avant la porte
technique) et `relecteur-pedagogique` (forke de `86837e1`, apres) editent des regions disjointes
sur trois fiches (`accords-groupe-nominal`, `antonymes`, `nature-mot`) : `nadia-prof` ajoute son
verdict au bloc de validation en pied de fiche, `relecteur-pedagogique` corrige le statut global en
tete, qui etait reste perime depuis la livraison des paquets. **Verifie par moi avant de fusionner** :
les deux touchent des lignes distinctes, confirme par lecture des deux diffs contre leur base
respective. Ces trois fiches se sont auto-fusionnees sans conflit git.

**La quatrieme fiche (`accord-sujet-verbe`) est touchee par les trois mains** : `nadia-prof` (bloc
de validation, verdict `REFUSE`), `relecteur-pedagogique` (statut global, avant de savoir qu'un
refus existait), et `marc-dev` (statut global, apres refus, decrivant le correctif). Conflit git
reel sur le statut global entre `relecteur-pedagogique` et `marc-dev`.

**Regle appliquee, donnee par la coordination et verifiee sur le contenu avant de l'appliquer** :
le bloc de validation de `nadia-prof` est intouche, il fait foi sans exception. Entre les deux
edits concurrents du statut global, celui de `marc-dev` l'emporte : lui seul connait le refus et le
correctif qui suit, `relecteur-pedagogique` ecrivait sur un arbre qui ne portait pas encore l'audit
et ne pouvait pas le savoir - ce n'est pas un verdict ecrase, c'est une version perimee remplacee
par une plus complete. Le detail factuel du relecteur (repartition des 21 items par temps) est
conserve dans le merge. La ligne "derniere mise a jour" est corrigee pour pointer sur le correctif
(dernier evenement reel) plutot que sur la livraison initiale, que personne n'avait mis a jour
depuis. **Verifie que la fiche raconte desormais l'histoire dans l'ordre demande** : refus de
`nadia-prof` avec ses motifs, puis correctif de `marc-dev`, puis une phrase explicite disant que la
reaudition n'a pas eu lieu.

## Ordre de fusion et resultat

| Ordre | Branche | Commit | Merge sur round-two | Conflit |
|---|---|---|---|---|
| 12 | `s19-nadia` | `ddb0fc4` | `81dcf75` | aucun |
| 13 | `s19-relecteur` | `de78975` | `8b5c0ef` | aucun (quatre fiches auto-fusionnees) |
| 14 | `s19-camille-3` | `739589d` | `078970d` | aucun |
| 15 | `s19-marc` | `998fabb` | `6d9c00a` | oui, sur la fiche accord-sujet-verbe, resolu comme decrit ci-dessus |

## Porte technique

`mvn clean verify` sur le reacteur complet, six modules, `BUILD SUCCESS`.

**Compte de tests, methode de reference, mesure par moi sur l'arbre fusionne : 666, inchange.**

`marc-dev` annoncait un delta de zero (correction de contenu pure). Verifie sur le diff reel :
`PaquetCm1AccordSujetVerbeTest.java` ne change que deux litteraux de donnees (deux enonces
remplaces pour changer le verbe muni d'une largeur excessive), aucune methode `@Test` ajoutee ni
retiree. 666 avant, 666 apres. `nadia-prof`, `relecteur-pedagogique` et `camille-po` n'ajoutent
aucun code de test (fiches, rapport, decisions, matrice).

**Largeur necessaire du paquet corrige** : `marc-dev` annonce 647 px pour 680 px offerts, zero item
sous le pli, mesure par l'outil de `nadia-prof` (`auditer-paquet.py`) plutot qu'a l'estime. Je ne
rejoue pas cette mesure moi-meme : c'est exactement la mesure que la reaudition de `nadia-prof` va
refaire sur l'arbre fusionne, et la dupliquer avant elle ajouterait une troisieme campagne du jour
sans rien trancher de plus tot.

**Couverture** : `camille-po` porte la matrice de 10/44 (22,7 %) a 12/44 (27,3 %), deux lignes
francais CM1 (synonymes+antonymes, accords du groupe nominal). Verifie en lisant l'entree datee
qu'elle a elle-meme ecrite dans `squad/programme/MATRICE-couverture.md` (section "Amendement du
2026-08-24 (troisieme passage)"), qui porte sa methode et l'historique des deux corrections de
denominateur du meme jour (45 puis 44) : je ne reconstruis pas ce chiffre par un comptage
independant, je verifie que la methode citee est bien celle appliquee.

## Ce qui n'est pas de la fusion : la retro de la coordination

Trois faits de gouvernance verses au dossier de retro, qui ne sont d'aucun agent fusionne ici :

1. Le meme obstacle (encodage d'espace insecable) route en parallele a `theo-archi` et `yanis-qa`
   (deja verse au journal precedent).
2. Le `relecteur-pedagogique` lance sur `round-two` a `86837e1`, un arbre qui ne portait pas encore
   l'audit de `nadia-prof` en cours - cause directe de la collision sur la fiche accord-sujet-verbe.
3. `NAT-5`, routee par `nadia-prof` a `ines-design`, jamais saisie par la coordination - reconnue et
   corrigee dans `DEC-0044` par `camille-po` elle-meme, avec un routage explicite a `sofia-sm` (moi,
   sequencer le chantier) et a `ines-design` (proposer le renfort visuel).

Les trois ont la meme forme : un orchestrateur qui lance en parallele sans tenir le graphe des
dependances entre lots. Deux des trois ont neanmoins produit une trouvaille reelle (la sensibilite
a la casse du garde-fou d'encodage, la remise a jour de fiches perimees) - **un benefice
accidentel, pas une preuve que la methode etait bonne**, matiere pour `retro.md`.

## Ce qui reste avant les ceremonies

**Une seule chose** : `nadia-prof` doit reauditer `cm1-accord-sujet-verbe` sur cet arbre fusionne
(commit `6d9c00a`), pas sur la branche `s19-marc` - c'est la regle etablie par la retro de s18
apres deux campagnes rejouees le meme jour sur des arbres differents. Je transmets ce commit pour
qu'elle mesure dessus.

Ensuite, dans l'ordre deja prescrit : review, `RAPPORT.md`, rapport de tokens, retro.
