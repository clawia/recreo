# S21 - fusion des lots 1 a 3 sur round-two

Ceci n'est pas une ceremonie de review : le sprint n'est pas clos (`theo-archi` n'a pas fini
`S21-1`, `S21-5`, `S21-6` ; `s21-marc` est tenu ; `S21-3`, `S21-9` a `S21-11` n'ont pas commence).
C'est le journal de fusion que la gouvernance par depot exige pour que l'etat intermediaire ne
depende pas de la memoire de qui l'a fait.

## Verification de provenance

Avant toute fusion, les trois lots ont ete verifies par
`squad/outils/sofia-sm/verifier-provenance-lots.sh`, tetes annoncees confirmees et ancetre commun
avec `round-two` trouve pour les trois :

```
branche 's21-ines' : lignee confirmee, un ancetre commun avec round-two existe.
branche 's21-lea' : lignee confirmee, un ancetre commun avec round-two existe.
branche 's21-nadia' : lignee confirmee, un ancetre commun avec round-two existe.
tous les lots verifies descendent de round-two avec la tete annoncee.
```

`round-two` a l'ouverture de cette fusion : `caad2f0` (mon planning), arbre propre.

## Recoupement de fichiers, verifie avant fusion

Aucun des trois lots ne touche le meme fichier qu'un autre, et aucun des trois ne touche de fichier
Java de production ni de test avec une assertion modifiee (verifie ligne par ligne pour `s21-lea`,
seul lot a toucher un fichier `.java`) :

- `s21-ines` : `squad/outils/ines-design/mesurer-candidats-rep-8.py`,
  `squad/sprints/s21-fermer-le-pli-avant-le-volume/proposition-ines-rep-8.md`.
- `s21-lea` : `adapters/adapter-content/src/test/java/com/recreo/adapter/content/PaquetCm1ReperesTest.java`
  - Javadoc uniquement (deux blocs de commentaire, lignes 70-86 et 159-168 du fichier fusionne) : la
    citation fautive d'`ADR-0029` est retiree, remplacee par les trois motifs pedagogiques dates
    fournis par `nadia-prof`. Aucune ligne de code executable, aucune assertion, aucune constante
    modifiee - verifie par lecture du diff complet, pas seulement de son resume.
- `s21-nadia` : `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md`, dix
  releves dans `squad/conformite/releves-s21/`, `squad/outils/nadia-prof/mesurer-un-contenu-candidat.py`.

## Ordre de fusion et resultat

| Ordre | Branche | Commit fusionne | Merge sur round-two | Conflit |
|---|---|---|---|---|
| 1 | `s21-nadia` | `16bf561` | `830e003` | aucun |
| 2 | `s21-ines` | `3daf481` | `455875e` | aucun |
| 3 | `s21-lea` | `74e8f4a` | `4a5617a` | aucun |

`s21-nadia` d'abord parce que `S21-8` est sur le chemin critique du planning (aucune story de volume
ne peut commencer avant que cet arbitrage existe) ; les deux autres suivent, sans dependance entre
eux ni avec `s21-nadia`.

## Porte technique

`mvn clean verify` sur le reacteur complet, six modules (`recreo`, `recreo-domain`,
`recreo-application`, `recreo-adapter-web`, `recreo-adapter-content`, `recreo-bootstrap`),
`BUILD SUCCESS`, mesure par moi le 2026-08-27 a 16:38 sur `round-two` a `4a5617a` (jamais sur le
worktree de `theo-archi`, qui construit en parallele sur le sien).

**Compte de tests, methode de reference (somme du bloc `Results:` de chaque module apres `clean`,
reacteur complet), mesure par moi sur l'arbre fusionne le 2026-08-27 : 694.**

| Module | `Results:` |
|---|---|
| `recreo-domain` | 49 |
| `recreo-application` | 13 |
| `recreo-adapter-web` | 330 |
| `recreo-adapter-content` | 198 |
| `recreo-bootstrap` (surefire, unitaires) | 98 |
| `recreo-bootstrap` (failsafe, integration) | 6 |
| **Total** | **694** |

49 + 13 + 330 + 198 + 98 + 6 = 694.

**Ce chiffre est identique aux 694 tests annonces par `marc-dev` sur sa propre branche `s21-marc`,
non fusionnee ici** (voir la section suivante). Ce n'est pas une coincidence a signaler comme un
ecart : aucun des trois lots fusionnes ne touche de fichier Java (verifie ci-dessus), et le lot de
`marc-dev` est un changement CSS pur verifie par un script externe (`auditer-paquet.py`), pas par du
code de test neuf - les deux mesures portent donc sur le meme total de tests Java, par des chemins
independants. Base non remesuree separement avant fusion : les trois lots fusionnes n'ajoutant et ne
retranchant aucun test, la remesurer aurait rendu le meme chiffre.

## Etat de `s21-marc` (`S21-2`) - tenu, pas fusionne

Lot vert et confirme (694 tests, 26 items sur 56 fermes par la forme), mais **je ne le fusionne
pas** : en le livrant, `marc-dev` a trouve que sa propre regle viole `ADR-0017` en rendu reel.

Verifie par moi, sur sa branche et sur les sources citees :

- `adapters/adapter-web/src/main/resources/static/css/recreo.css`, diff `round-two`..`s21-marc` :
  `.proposition { padding: 0.5rem 1.25rem; }` est ajoute apres le bloc partage (qui declare
  `0.75rem 1.25rem` a la ligne 667), donc le surclasse a specificite egale par ordre de source
  (`ADR-0014` point 5). `.lien-accueil` (ligne 140, role `sortie`) reste a `0.6rem 1.1rem`, inchange.
  L'action (`.proposition`, `0.5rem`) declare donc moins que la sortie (`.lien-accueil`, `0.6rem`) -
  exactement l'ecart qu'`ADR-0017` ("Comparer deux boutons d'un meme ecran, et dire ce que cette
  comparaison ne voit pas") encode en garde-fou.
- `LActionNeDeclarePasMoinsQueLaSortieTest` (`adapters/adapter-web/src/test/java/.../`) reste vert
  sans voir cet ecart. Cause verifiee dans le code : `debutDeDeclaration`
  (`TailleDesTextesEtBoutonsTest.java`, ligne 241-251) lit la **premiere** occurrence de
  `padding:` dans le corps concatene par `blocsMaison` (`indexOf`, jamais la derniere), donc lit
  toujours le bloc partage a `0.75rem`, jamais la declaration `.proposition` de `s21-marc` qui
  gagne pourtant la cascade.

Deux questions distinctes, deux destinataires :

1. **La valeur elle-meme** (`0.5rem` contre `0.6rem`) : `nadia-prof` arbitre en ce moment
   (`ADR-0017` encode une regle pedagogique, pas une regle d'architecture). Je fusionne `s21-marc`
   apres son verdict, pas avant. Si le verdict change la valeur, `marc-dev` reprend son lot, pas moi.
2. **Le garde-fou lui-meme** est aveugle independamment de ce que `nadia-prof` decidera : il ne lit
   jamais la valeur qui gagne la cascade. Route a `theo-archi`, voir routage ci-dessous. Correction
   attendue : lire le dernier `padding` effectif du corps, pas le premier trouve - jamais la valeur,
   qui n'est pas son role.

`marc-dev` n'a tranche ni l'un ni l'autre seul, a raison : corriger la valeur romprait le gain que
`nadia-prof` vient d'accorder sur l'acquis 7 (2026-08-11, amende le 2026-08-27) ; corriger le
garde-fou n'est pas son lot.

## Obstacles routes

Trois obstacles surgis pendant la livraison de cette premiere vague, aucun connu a l'ouverture du
sprint. Portes au tableau des obstacles de `planning.md` (amendement date du 2026-08-27) ; le detail
et la verification vivent ici.

1. **La valeur `0.5rem` contre `0.6rem` d'`ADR-0017`** - route a `nadia-prof`. Arbitrage en cours au
   moment de cette fusion.
2. **Le garde-fou aveugle `LActionNeDeclarePasMoinsQueLaSortieTest` / `debutDeDeclaration`** - route
   a `theo-archi`. **Ma decision de perimetre : il entre dans s21**, comme prealable au deblocage de
   `s21-marc`, pas comme une story de volume nouvelle. Motif : un garde-fou qui ne voit pas la
   valeur qui gagne la cascade ne peut verifier aucun verdict que `nadia-prof` rendra sur le point 1
   - le laisser hors perimetre laisserait `s21-marc` bloque meme apres son arbitrage. C'est la meme
   famille de defaut que `CLAUDE.md` sanctionne (une regle non executable qui se croit executable) et
   elle mord exactement le sprint qui vient de fermer une premiere serie de dettes du meme genre
   (`S21-4`, `S21-6`).
3. **`S21-3` non livree.** `lea-dev` a verifie, sans editer aucun fichier de contenu, que les 12
   enonces vises tiennent deja sur une ligne (`.enonce.getClientRects().length` rend `lignes=1,
   hauteur=48` px sur les items concernes hors des deux exclus), que `.enonce` n'a ni `min-height` ni
   `line-height` fixe, et que le repli a deux rangees est force par
   `:has(.proposition:nth-child(4))` independamment de la longueur du texte. Elle conclut que le
   deficit residuel vient de la largeur des libelles de proposition, pas des enonces. Route a
   `nadia-prof`. Je ne reecris pas la story avant son verdict ; `lea-dev` est disponible pour
   reprendre des que l'arbitrage existe.

## Ce qui reste hors du perimetre de cette fusion

- `s21-marc` (`S21-2`), tenu - voir ci-dessus.
- `s21-theo`, non fini au moment de cette fusion : porte `S21-1`, `S21-5`, `S21-6`. Non touche.
- `S21-3`, `S21-9`, `S21-10`, `S21-11` : aucune n'a de code, aucune n'a ete affectee a tort.

## Etat de `round-two` apres cette fusion

`round-two` local est a `4a5617a`, non pousse. Trois lots fusionnes, `BUILD SUCCESS`, 694 tests,
methode et perimetre ci-dessus. Le sprint n'est pas clos : `s21-marc` attend l'arbitrage de
`nadia-prof` sur `ADR-0017`, `theo-archi` a trois stories en cours dont la correction du garde-fou
aveugle nouvellement entree en perimetre, et `S21-3`, `S21-9` a `S21-11` restent a ouvrir.
