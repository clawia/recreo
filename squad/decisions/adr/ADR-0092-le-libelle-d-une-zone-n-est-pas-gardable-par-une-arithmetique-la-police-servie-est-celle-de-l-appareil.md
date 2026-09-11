# ADR-0092 - Le libelle d'une zone designable n'est pas gardable par une arithmetique : la police servie est celle de l'appareil, et voici les deux mecanismes qui gardent ce qui reste gardable

- **Statut** : **`Accepte`** le 2026-09-11 (s35, confirmation de la porte technique), apres avoir
  ete `Propose` le meme jour, **et amende deux fois dans le geste qui l'accepte** : sa decision 3
  annoncait un levier *"gratuit"* qui ne l'est pas, et son mecanisme 2 prescrivait un nom de
  constante qui affirme un audit qui n'a pas eu lieu. Les deux corrections sont en fin de fichier,
  avec leur mesure. Etat a la redaction : *"Sa decision 2 est un refus d'ecrire une regle, motive
  par une mesure ; ses deux mecanismes de remplacement sont ecrits ci-dessous et dus avec le lot de
  `S35-QLM11-PLANCLASSE`. Il ne passera `Accepte` que quand ces deux mecanismes seront dans
  l'arbre."*
- **Sprint** : s35. **Date** : 2026-09-11. **Auteur** : `theo-archi`.
- **Route par** : la cloture de s34, obstacle encore ouvert - *"Le recouvrement des boutons de zone
  sur le dessin qu'ils designent, transverse a tout paquet de designation"*, route a `ines-design`
  et a `theo-archi`
  (`squad/sprints/s34-dernier-sprint-du-plan-2-palier-2-engage/RAPPORT.md`) - et par `ZON-15`,
  condition de `nadia-prof` du 2026-09-11 qui a retire `cp-plan-de-la-classe` du sprint.
- **Prolonge** : `ADR-0080` (une designation sur image est un choix parmi des zones enumerees ;
  **son amendement du 2026-09-11, section 3**, avait nomme cette propriete sans la tenir),
  `ADR-0025` (une mesure de rendu porte sa commande, et le debordement de mise en page est declare
  non gardable), `ADR-0054` et `ADR-0055` (une fonction epinglee, et les **deux** membres d'une
  comparaison epingles), `ADR-0067` (un cliquet dont le plafond vaut exactement la dette),
  `ADR-0013` (un garde-fou derive son perimetre et se prouve mordant), `ADR-0014` point 6 (les deux
  planchers d'un bouton actionnable).
- **Ce que cet `ADR` ne fait pas** : il ne choisit **aucune forme** - ou vit le libelle d'une zone,
  ce qu'un bouton montre du dessin, comment un cadre est redessine appartiennent a `ines-design`
  qui propose et a `nadia-prof` qui tranche. Il ne prononce aucun Done, il ne redessine rien, il
  n'amende ni `ZON-15` ni `ZON-16`, il ne rouvre pas le plancher de cible tactile, et il ne ferme
  aucune ligne de matrice.
- **Arbre mesure** : `round-two` a `60e1c836`, worktree dedie. **Toutes les mesures sont statiques**
  - lectures de la feuille de style, du gabarit et du repertoire des ressources tierces, et
  arithmetique sur ces valeurs. **Aucun build n'a ete lance, aucun navigateur n'a ete ouvert**, et
  aucune mesure de rendu n'est citee ici comme si elle avait ete refaite : les tailles en pixels
  viennent de `lea-dev` (mesure au jar du 2026-09-11) et de `nadia-prof`, nommees a chaque fois.

---

## Contexte

### 1. Deux faits, de deux personnes differentes, qui disent la meme chose

**`nadia-prof`, `ZON-15`, 2026-09-11** :

> *"Le libelle d'une zone est un texte qu'un enfant de CP doit lire pour presser le bon bouton : le
> plancher est 1,25rem [...] A la taille servie, le libelle tient entierement dans son cadre sans
> deborder ni toucher une zone voisine, et ne recouvre aucun trait du dessin que sa zone designe. Si
> les deux ne tiennent pas ensemble a 1,25rem, c'est le cadre ou le libelle qui change, jamais la
> police."*

**Le fait mesure** : sur les cadres de l'armoire (56 x 77 px) et de la porte (63 x 56 px), *"16 a
23 px de boite de contenu apres le pave de `.zone`, aucun libelle n'y tient a 1,25rem, meme d'un
mot"*.

**`ADR-0080`, amendement du 2026-09-11, section 3** - j'avais nomme la propriete et refuse de la
faire tenir par le garde-fou 3, en ecrivant : *"Elle est nommee ici pour ne pas etre decouverte une
seconde fois au jar."* **Nommer n'est pas garder.** Cet `ADR` paie cette phrase ou l'annule ; il ne
la repete pas une troisieme fois.

### 2. Le fait qui change la nature du probleme, et personne ne l'avait encore ecrit

**Mesure, avec sa methode dans le meme paragraphe.** Recherche des motifs `font-family` et
`@font-face` dans `adapters/adapter-web/src/main/resources/static/css/recreo.css`, le 2026-09-11 :
**aucune occurrence**. Inventaire de `static/vendor/` : **un seul fichier**, `bootstrap.min.css`
(`org.webjars:bootstrap:5.3.8`, empreinte dans `vendor/provenance.txt`), et **aucun fichier de
police** dans tout `static/`. La seule pile declaree est celle de cette bibliotheque :

```
--bs-font-sans-serif: system-ui, -apple-system, "Segoe UI", Roboto, "Helvetica Neue",
                      "Noto Sans", "Liberation Sans", Arial, sans-serif, ...
```

**Le texte servi est donc rendu par la police du systeme de l'enfant.** La largeur d'un libelle a
`1,25rem` **n'est pas une propriete du depot** : elle depend de l'appareil. A cadre identique et a
libelle identique, *"le libelle tient dans son cadre"* est vrai sur une machine et faux sur une
autre.

**Deux consequences, et elles decident tout le reste de cet `ADR`** :

1. **Aucune regle de CI ne peut tenir cette propriete.** Il faudrait epingler une avance par
   caractere. Ce nombre serait mesure sur **une** machine, et `ADR-0055` exige d'epingler **les
   deux** membres d'une comparaison : ici, le second membre n'existe nulle part dans le depot -
   aucune police n'y est versionnee. Un tel chiffre serait la faute de s05 sous une autre forme,
   **une mesure de ma main presentee comme une propriete du monde**, et il produirait des faux verts
   sur tous les systemes plus larges que le mien.
2. **Agrandir les cadres ne ferme pas la question, il la deplace.** Quel que soit le cadre choisi a
   la redaction d'un paquet, un systeme a police plus large le deborde. Ce n'est pas une objection a
   `ZON-15` - un libelle illisible reste illisible - c'est **l'information qui manquait pour choisir
   comment la tenir**, et elle appartient a `nadia-prof`.

### 3. Pourquoi c'est transverse et non le defaut d'un paquet

`ADR-0080`, decision 4, et le gabarit qui l'execute
(`adapters/adapter-web/src/main/resources/templates/fragments/designation.html`) : une zone est un
`<button type="submit">` en `position: absolute`, pose sur la boite de l'image a l'endroit exact que
son cadre declare, fond translucide (`rgba(255, 255, 255, 0.55)`), **son libelle a l'interieur**.

**Un bouton de zone recouvre donc le dessin qu'il designe par construction**, sur tout paquet de
designation, present et futur. Faire corriger cela par chaque paquet dans ses propres cadres, c'est
garantir qu'il rouvre au paquet suivant - et c'est aussi un perimetre enumere qui se croit general,
la famille de defaut que ce depot a deja sanctionnee plusieurs fois. **D'ou la decision 1.**

---

## Decision

### 1. Le recouvrement d'un bouton de zone sur son dessin est tranche ici, une fois, pour tout paquet de designation - et il n'est absorbe par aucune story de contenu

`S35-QLM11-PLANCLASSE` tient `ZON-15` **sur six cadres, un dessin, une taille de police** :
c'est ce qu'il faut pour fermer `QLM-CP-11`, et ce n'est pas la propriete transverse.
`S35-QLM14-FRISE` ne la touche pas du tout. **Les deux stories restent ce qu'elles sont** ; le
sujet transverse ne se dilue pas dans leurs criteres d'acceptation.

### 2. La propriete *"le libelle tient entierement dans son cadre a 1,25rem"* ne sera pas encodee, et ce refus est motive par la mesure de la section 2

Elle reste **jugee par l'oeil de `nadia-prof` sur l'ecran servi**, paquet par paquet - ce qui est
deja son geste, et ce qui vient de se produire. **Ce que cet `ADR` refuse, c'est de promettre un
garde-fou qui ne peut pas exister**, et de laisser croire qu'un chiffre epingle par un architecte
tiendrait une propriete qui varie avec l'appareil de l'enfant.

**Ce refus n'est pas un renoncement** : il a un prix, et la decision 4 le paie avec deux mecanismes
qui, eux, sont executables.

### 3. Trois leviers, pas deux - et le troisieme est mesure

`nadia-prof` en a nomme deux (*"c'est le cadre ou le libelle qui change, jamais la police"*). Il en
existe un troisieme, lu dans la feuille de style et le gabarit :

```
.zone { min-height: 2.75rem; min-width: 2.75rem; padding: 0.75rem 1.25rem;
        border: 0.125rem solid ...; font-size: 1.25rem; }     box-sizing: border-box
gabarit : style="left:..%; top:..%; width:..%; height:..%"   -> le cadre est une taille FIXE
```

Le pave horizontal coute **2 x 1,25rem = 40 px** et la bordure **2 x 2 px = 4 px** : **44 px de la
largeur d'un cadre partent avant le premier caractere**. Sur `armoire` (56 px de large), il reste
**12 px** ; sur `porte` (63 px), **19 px**. Cet ordre de grandeur concorde avec les *"16 a 23 px"* de
`nadia-prof`, obtenu par un autre chemin - deux methodes, un meme resultat.

**Le pave est donc un levier, et il ne coute rien sur une propriete deja gardee** : le plancher de
cible tactile est tenu par `min-height` et `min-width` (2,75rem), **jamais par le pave**, et le juge
du garde-fou 3 d'`ADR-0080` compare `min(largeur%, hauteur%) x plafond de rendu` au plancher - une
comparaison ou le pave n'entre pas. Passer le pave horizontal de `1,25rem` a `0` sur `armoire` fait
passer la boite de contenu de **12 px a 52 px**, sans toucher un cadre ni un libelle.

**Ce que cette decision fait, et ce qu'elle ne fait pas** : elle **ecrit** que le levier existe,
qu'il est mesure et qu'il ne casse rien. Elle **ne** change **pas** `.zone` : une regle d'habillage
appartient a `ines-design`, et le choix entre les trois leviers appartient au lot. Sans ce
paragraphe, le prochain arbitrage se ferait entre deux termes au lieu de trois, et le plus cher des
trois serait choisi par defaut.

### 4. Ce qui est executable est ecrit, et c'est la derniere clause d'`Accepte` d'`ADR-0080`

Tout ce qui **est** derivable de l'ecran de manche reellement servi d'un jeu de designation cesse
d'etre vu au jar : c'est exactement la clause qu'`ADR-0080` a nommee comme derniere condition de son
`Accepte`, en ecrivant qu'*"elle appartient au lot qui achevera `QLM-CP-11`"*. **Elle n'est pas un
travail neuf que cet `ADR` ajoute** : elle est au perimetre de ce lot depuis s34, et cet `ADR` la
rend opposable en la citant comme son propre mecanisme.

---

## Regle executable

**Aucune de ces deux garanties n'est une `ArchRule`, et le motif est celui d'`ADR-0080`** : ArchUnit
modelise des types Java et leurs relations. Les objets juges ici sont **un document HTML servi, une
feuille de style et des cles de YAML** - aucun n'est un type.

**Et la propriete centrale de cet `ADR` n'est encodee par rien du tout** : la section 2 dit
pourquoi, avec sa mesure. C'est la premiere fois qu'un `ADR` de ce depot conclut a un refus
d'encodage sur un motif de **variabilite d'appareil** plutot que de jugement de sens ; le motif est
ecrit pour que personne ne le prenne pour un oubli.

### Mecanisme 1 - `LEcranDeMancheDuJeuDeDesignationReelEstServiTest` (`bootstrap`)

**Fichier** :
`bootstrap/src/test/java/com/recreo/bootstrap/rendu/LEcranDeMancheDuJeuDeDesignationReelEstServiTest.java`

**Ce qu'il fait** : pour **chaque** entree du catalogue reel dont la mecanique est `designation`
- perimetre derive du catalogue, jamais une liste de noms -, il sert l'ecran de manche par MockMvc
et y rejoue les criteres des garde-fous 1 et 3 d'`ADR-0080`, qui mordent aujourd'hui sur un jeu
**fabrique** dans `adapter-web` :

1. chaque zone de l'item est **un et un seul** `<button type="submit" name="zoneDesignee">`, a
   valeur non vide et a nom accessible non vide, dans le formulaire du coup ;
2. le document ne porte ni `usemap`, ni `<map>`, ni `<area>`, ni attribut d'evenement en ligne ;
3. la taille rendue de chaque zone atteint le plancher de cible tactile, les deux membres derives
   (`.image-designation { max-height }` lu dans `recreo.css`,
   `TailleDesTextesEtBoutonsTest.CIBLE_TACTILE_REM`), en reutilisant le juge de
   `AucuneZoneDesignableNeDescendSousLaCibleTactileTest` plutot qu'en ecrivant un second.

**Pourquoi dans `bootstrap`, et pourquoi ce n'etait pas possible avant** : `adapter-web` ne depend
que de `recreo-application` et de `recreo-domain`, il **ne peut pas** charger un paquet
d'`adapter-content` - c'est structurel, et c'est ce qui a maintenu `ADR-0080` en `Propose`.
`bootstrap` voit les deux, et le mecanisme y est deja employe trois fois
(`LIllustrationDUnItemServiAtteintLEcranDeMancheTest`,
`LeTexteSupportDUnItemReelAtteintLEcranDeMancheTest`,
`LaPartDesigneeDUnItemServiEstMarqueeALEcranTest`).

**Le motif est mesure, pas theorique** : **sept** garde-fous de `bootstrap/rendu` ont recu a s34 une
branche `designation`, et **les sept l'ecartent**. Chaque exclusion est motivee prise une par une ;
leur effet cumule ne l'est pas - **aucun test du depot ne sert l'ecran de manche du paquet reel**.
C'est la forme exacte du defaut qu'`ADR-0088` clause C a paye sur cinq garde-fous de rendu, par un
autre chemin.

**Ce qu'il interdit reellement** : qu'un paquet de designation soit servi avec un ecran de manche que
personne n'a jamais assemble en CI. Il ne dit **rien** de la lisibilite du libelle - decision 2 - et
son Javadoc doit l'ecrire, sans quoi son nom promettrait plus que son perimetre, ce qu'`ADR-0015` a
deja sanctionne.

### Mecanisme 2 - le cliquet exact de la dette de lisibilite

**Fichier** :
`bootstrap/src/test/java/com/recreo/archi/AucunPaquetDeDesignationNEntreEnProductionSansLaDetteDeLisibiliteDuLibelleTest.java`

```java
/**
 * ADR-0092 : la propriete "le libelle d'une zone tient entierement dans son cadre a 1,25rem" n'est
 * tenue par aucun garde-fou et ne peut pas l'etre (police du systeme, ADR-0092 section 2). Elle est
 * tenue par l'oeil de nadia-prof, paquet par paquet. Ce cliquet empeche que cette dette soit
 * oubliee : il rougit le jour ou un paquet de designation de plus entre en production.
 *
 * Le perimetre et le comptage sont ceux
 * d'AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest, reutilises et non
 * recrits - un second recensement des paquets de designation divergerait un jour du premier.
 */
// Nom corrige par l'amendement du 2026-09-11, section 3 : ce compteur compte des paquets
// SERVIS, jamais des paquets audites, et ce nom-ci affirmait un audit qui n'a pas eu lieu.
private static final int PAQUETS_DE_DESIGNATION_PORTANT_LA_DETTE_DE_LISIBILITE = 2; // valeur
        // posee par le diff qui sert le second paquet ; 1 si QLM-CP-11 n'est pas livree

@Test
void aucunPaquetDeDesignationDePlusNEntreEnProductionSansQueCetteDetteSoitPayee() {
    Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
    List<Object> corps = AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest
            .corpsCharges(AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest
                    .repertoiresDeContenu(racine));
    long compte = AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest
            .paquetsDeDesignation(corps);
    assertThat(compte)
            .as("ADR-0092 : %d paquets de designation sont servis, et ce cliquet en portait %d."
                    + " Un paquet de plus entre en production avec une propriete de lisibilite"
                    + " que rien ne garde : soit son libelle est juge a l'ecran par nadia-prof et"
                    + " ce chiffre monte dans le meme diff, soit la propriete cesse de dependre de"
                    + " la police (ADR-0092, decisions 2 et 3). Ce cliquet compte des paquets"
                    + " servis, pas des paquets audites - il route l'audit, il ne le verifie pas",
                    compte, PAQUETS_DE_DESIGNATION_PORTANT_LA_DETTE_DE_LISIBILITE)
            .isEqualTo(PAQUETS_DE_DESIGNATION_PORTANT_LA_DETTE_DE_LISIBILITE);
}
```

**Egalite, jamais plafond** (`ADR-0087`, amendement de la cloture de s31, et `ADR-0067`) : un paquet
de plus est rouge, **et un chiffre plus haut que le compte reel est rouge aussi** - ce qui interdit
de le gonfler d'avance. **Le plafond vaut exactement la dette**, et c'est la lecon que je m'applique
a moi-meme : un cliquet dont le plafond depasse la dette n'est pas un cliquet.

**Deux compteurs sur la meme population, et c'est voulu.** Le cliquet existant
(`...SansEtendreLesTroisBalayagesTest`, plafond `1`) porte la dette des trois balayages de contenu ;
celui-ci porte la dette de lisibilite. **Les fondre en un seul chiffre ferait payer l'une par
l'autre** : le diff qui etend un balayage monterait le plafond commun et effacerait la trace de la
dette restante. Le recensement, lui, n'est pas duplique - il est appele.

**Mise a l'epreuve** (`ADR-0013` obligation 3) : le jeu fautif du cliquet existant sert les deux
sens (un compte qui monte, un plafond qu'aucun compte reel ne justifie), et ce test le reutilise
plutot que d'en fabriquer un second.

### Due avec le lot de `S35-QLM11-PLANCLASSE`

Les deux mecanismes appartiennent au lot qui sert `cp-plan-de-la-classe` : le mecanisme 1 parce
qu'`ADR-0080` l'y a deja place, le mecanisme 2 parce que son plafond n'est connu qu'une fois su si
le paquet est servi ou non. **Un garde-fou neuf ecrit apres la fusion des branches est du code
qu'aucune story ne porte** - `ADR-0080` l'ecrit, et cet `ADR` ne fait pas l'exception.

**Si le lot n'aboutit pas** : le mecanisme 2 est ecrit quand meme, avec le plafond `1`, et il rougit
au prochain paquet. C'est la seule facon connue de ce depot de faire payer une dette - un cliquet
dont le plafond vaut la dette du jour, jamais une ligne de rapport de sprint.

---

## Consequences

**Bonnes** :

- La propriete transverse cesse d'etre reportee de sprint en sprint sans mecanisme : elle est
  **nommee, mesuree, et bornee par un cliquet exact** qui rougit au troisieme paquet de designation.
- La derniere clause d'`Accepte` d'`ADR-0080` devient opposable par un second `ADR` qui la cite
  comme son propre mecanisme : elle ne peut plus glisser sans que deux `ADR` mentent.
- Le prochain arbitrage de forme dispose de **trois** leviers mesures au lieu de deux, et sait que
  le troisieme ne coute rien sur le plancher de cible tactile.
- Le depot gagne un fait qu'aucun de ses `ADR` ne portait : **il ne versionne aucune police**, donc
  aucune propriete de largeur de texte n'y est gardable. Ce fait vaut bien au-dela des zones
  designables.

**Mauvaises, et elles sont reelles** :

- **`ZON-15` reste tenue par un oeil.** Un paquet de designation peut entrer en production avec un
  libelle qui deborde sur un appareil que personne n'a regarde, et la CI sera verte. C'est le prix
  du refus de la decision 2, et il est ecrit ici plutot que decouvert.
- **Un cliquet de plus a maintenir**, avec le frottement de ceux d'`ADR-0013`, d'`ADR-0081` et
  d'`ADR-0080` : tout lot qui sert un paquet de designation devra monter deux chiffres au lieu d'un.
- **Cet `ADR` ne resout pas le recouvrement du dessin** : il le borne. La forme qui le supprimerait
  vraiment - la ou le libelle d'une zone ne recouvre plus le dessin qu'elle designe - reste a
  `ines-design` et a `nadia-prof`, et cet `ADR` ne prejuge pas laquelle.

---

## Alternatives considerees

**a) Epingler une avance par caractere, mesuree avec `outils/mesurer-rendu.sh`, et comparer.**
C'etait la premiere conception, et c'est celle qu'on attend d'un `ADR` de ce depot - patron
d'`ADR-0054`, une fonction epinglee. **Ecartee par la mesure de la section 2** : le second membre de
la comparaison n'existe pas dans le depot. La regle serait verte sur la machine de mesure et fausse
ailleurs, c'est-a-dire un **faux vert** - la forme de defaut que ce depot sanctionne le plus
durement, et la seule qui ne se remarque pas.

**b) Vendoriser une police et l'imposer par `font-family`.** Cela rendrait la propriete derivable :
avec un fichier de police versionne, les avances des glyphes se lisent **dans le depot**, sans
navigateur, et la regle de l'alternative (a) deviendrait juste. **Ecartee pour aujourd'hui, et ce
n'est pas un refus de fond** : c'est un changement d'apparence sur **tous** les ecrans du produit,
donc une decision de forme d'`ines-design` et une porte pedagogique de `nadia-prof` sur la
lisibilite d'une police pour un enfant de CP, plus un poids servi de plus a peser contre le cliquet
de poids existant. **Le declencheur, ecrit maintenant** : le jour ou une seconde propriete de
largeur de texte se revele non gardable pour le meme motif, la vendorisation d'une police cesse
d'etre une question d'apparence et devient une question d'architecture - et cet `ADR` sera son
contexte.

**c) Faire tenir la propriete au garde-fou 3 (cible tactile).** **Ecartee, deja, par la mesure** :
les six cadres du plan de classe ont tous un plus petit cote a 15 %, soit 52,8 px contre un plancher
de 44 px - **les six passent le garde-fou 3, et trois rendent un libelle qui deborde**. Les deux
proprietes sont distinctes ; les fondre donnerait un garde-fou dont le nom promet plus que son
perimetre.

**d) Accepter que le libelle deborde de son cadre** (le gabarit ecrirait `min-width`/`min-height`
plutot que `width`/`height`, et le bouton grandirait avec son texte). C'est la seule forme
**independante de l'appareil** ou un libelle n'est jamais coupe. **Ecartee comme decision de cet
`ADR`, et rendue a qui elle appartient** : elle contredit la seconde moitie de `ZON-15` (*"sans
deborder ni toucher une zone voisine"*), donc elle demanderait a `nadia-prof` de renverser sa propre
condition. Elle est **ecrite ici** pour que l'arbitrage de forme l'ait sous les yeux, avec son cout
et son gain, plutot que d'etre redecouverte au sprint suivant.

**e) Laisser l'obstacle route sans `ADR`, et le traiter au prochain paquet de designation.**
**Ecartee** : c'est ce que s34 a fait, apres que l'amendement d'`ADR-0080` l'avait deja nomme. Un
sujet route deux fois sans mecanisme est un sujet documente quatre fois et jamais paye - le motif
exact pour lequel `CLAUDE.md` porte une section de dettes.

---

## Amendement du 2026-09-11 (s35, `theo-archi`) - `Accepte`, et deux de mes propres phrases corrigees par la mesure d'autrui

**Arbre mesure** : `s35-integration` a `60e1c836`+8, worktree dedie `s35-theo-confirm`. **JDK
21.0.10**, version rapportee par le JDK que l'enveloppe a resolu (`ADR-0071`). Les mesures ci-dessous
sont des executions de Maven.

### 1. Le mecanisme 1 mord, et la mesure dit exactement ce qu'il ajoute

Methode : dans le worktree dedie, le cadre de la zone `armoire` de
`adapters/adapter-content/src/main/resources/contenu/cp-plan-de-la-classe.yaml` - **materiau de
production, pas une fixture** - ramene de `hauteur: 30` a `hauteur: 5` sur ses six occurrences, puis
`bash outils/mvn-jdk21.sh -B test` sur la **racine** du reacteur.

| Module | Tests | Echecs |
|---|---|---|
| `recreo-domain` | 111 | 0 |
| `recreo-application` | 13 | 0 |
| `recreo-adapter-web` | 397 | 0 |
| `recreo-adapter-content` | 705 | 0 |
| `recreo-bootstrap` | 247 | **1** |

**Un seul test du depot voit ce defaut**, et c'est
`LEcranDeMancheDuJeuDeDesignationReelEstServiTest.surChaqueJeuDeDesignationDuCatalogueReelLEcranDeMancheEstConforme`
: *"la zone `armoire` rend au plus 1.1rem (plafond de hauteur 22.0rem, plus petit cote 5.0%), sous
le plancher de cible tactile de 2.75rem (ADR-0080, garde-fou 3)"*. Les **1472** autres restent verts,
dont `AucuneZoneDesignableNeDescendSousLaCibleTactileTest` d'`adapter-web`, qui tient le meme critere
sur un jeu fabrique et ne pouvait pas voir celui-la.

**C'est la mesure que la section *"Le motif est mesure, pas theorique"* promettait**, obtenue par
l'autre bout : elle affirmait que sept garde-fous de `bootstrap/rendu` ecartent la designation et
qu'aucun test ne sert son ecran de manche. Un defaut de geometrie reel, introduit dans un paquet de
production, le confirme sans qu'on ait a recenser les exclusions une a une. Le mecanisme 1 n'est pas
un doublon des garde-fous 1 et 3 : c'est le seul chemin par lequel leurs criteres rencontrent le
catalogue servi.

Le materiau a ete remis en etat ; `git status` est propre avant les ecritures de cet amendement.

### 2. Correction 1 - la decision 3 annoncait un levier *"gratuit"*, et il ne l'est pas

`ines-design` me l'a remonte dans
`squad/sprints/s35-fermer-ce-qui-n-a-pas-recu-le-go-pedagogique/proposition-ines-s35-frise-et-plan-de-classe.md`,
section 2.2, en le trouvant par le reacteur et non par une lecture. **Verifie par moi plutot que
repris de son rapport** - meme discipline que celle appliquee au commit de `lea-dev` ci-contre :
le pave horizontal de `.zone` ramene a `0,25rem` dans `recreo.css` (materiau de production), puis
`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test -Dtest='LActionNeDeclarePasMoinsQueLaSortieTest'`
rend **`Tests run: 13, Failures: 1`**, avec six griefs sur
`templates/fragments/designation.html`, dont : *"la sortie `.btn.lien-accueil` "Les jeux" declare
rembourrage gauche et droite a 1,10rem quand l'action `.btn.zone` "" se contente de 0,25rem"*.

**Ce que ma decision 3 disait, et qui est faux** : *"le pave est donc un levier, et il ne coute rien
sur une propriete deja gardee"*, *"sans toucher un cadre ni un libelle"*, *"passer le pave horizontal
de `1,25rem` a `0` sur `armoire` fait passer la boite de contenu de 12 px a 52 px"*. **Ce qui est
juste et ce qui ne l'est pas se separent nettement** : l'arithmetique sur le garde-fou 3 est exacte
et reste vraie, mais *"il ne casse rien"* etait une conclusion tiree du **seul** garde-fou que
j'avais regarde. `ADR-0017` borne ce pave par en dessous a **1,1rem** - la plus large des sorties
servies sur un ecran de jeu (`.lien-accueil { padding: 0.5rem 1.1rem }`, `.chip-niveau { 0.5rem
0.9rem }`, valeurs lues dans `recreo.css`). Le gain reel du levier est donc
`(1,25 - 1,1) x 2 x 16 = 4,8 px`, pas 40 px.

**Ce que cela change a la decision 3** : le troisieme levier existe toujours, il reste mesure, mais
il est **presque nul** et il touche une regle d'habillage partagee par tout ecran de jeu. Il n'est
plus le levier qu'un futur arbitrage devrait preferer ; le cadre l'est. *La forme de ma faute est
nommee parce qu'elle se reproduira autrement* : **une propriete verifiee contre un garde-fou n'est
pas une propriete verifiee contre le depot**, et un architecte qui annonce a un lot qu'un changement
*"ne casse rien"* doit avoir lance le reacteur, pas lu une regle.

### 3. Correction 2 - le mecanisme 2 prescrivait un nom qui affirme un audit

`ADR-0092` ecrivait `private static final int PAQUETS_DE_DESIGNATION_AUDITES_A_L_OEIL = 2;` et
`lea-dev` l'a implemente tel quel, Javadoc comprise (*"`cp-plan-de-la-classe`, audite a l'oeil par
`nadia-prof` sur `ZON-15`"*). **Le fait, verifie dans l'arbre** : `squad/conformite/` porte
`FICHE-designation-frise-de-la-journee.md` et **aucune fiche pour `cp-plan-de-la-classe`** ; aucun
verdict de `nadia-prof` de s35 n'existe dans le depot. Au moment ou ce chiffre passe a 2, **2
paquets sont servis et 1 seul a ete audite**.

**La cause est dans cet `ADR`, pas dans son implementation** : le recensement que le cliquet
reutilise ne sait lire que les paquets **servis**. Aucun nom ne peut donc promettre l'audit sans
mentir, et j'ai prescrit celui qui le promet - `ADR-0015`, un nom qui promet plus que son perimetre,
applique a ma propre prescription.

**Corrige dans le meme geste** : la constante s'appelle
`PAQUETS_DE_DESIGNATION_PORTANT_LA_DETTE_DE_LISIBILITE`, sa Javadoc et son message d'echec disent
qu'elle compte des paquets servis. **La valeur `2` ne change pas** - elle est juste, et l'egalite
avec le compte reel est la propriete du cliquet.

**Ce que le mecanisme 2 fait reellement, ecrit maintenant plutot que deduit plus tard** : il oblige
le diff qui sert un paquet de designation de plus a toucher ce chiffre, ce qui **route** la question
de l'audit. Il ne verifie pas qu'un audit a eu lieu, et **il ne bloque pas un paquet dont l'audit est
en attente** - `cp-plan-de-la-classe` est dans ce cas aujourd'hui. Cette limite est du meme ordre que
la mauvaise consequence deja ecrite (*"`ZON-15` reste tenue par un oeil"*) ; elle est plus precise, et
elle interdit de lire ce cliquet comme une preuve d'audit.

### 4. L'obstacle transverse, et ou il en est

`ines-design` a ecarte mon alternative (d) - le bouton elastique - pour trois motifs (section 3.1 de
sa proposition), dont un que je n'avais pas vu et qui est de la nature de l'obstacle lui-meme : **un
bouton qui grandit pour loger son texte recouvre une part du dessin plus grande que son cadre**, donc
aggrave le fait que l'alternative pretendait attenuer. Je ne conteste pas ce refus ; il ne m'appartient
pas et son motif est ecrit.

**Ce que le lot ferme** : la lisibilite du libelle sur les deux cadres de `cp-plan-de-la-classe`,
par la geometrie seule - `armoire` 30 x 30 % et `porte` 32 x 17 %, verifies dans le `yaml` servi,
boite de contenu de 61,6 px et 68,64 px contre 12 px et 19 px. Verification de ma part sur ce que la
CI garde : les six cadres passent le garde-fou 3, et le mecanisme 1 le rejoue desormais sur l'ecran
reellement servi (section 1).

**Ce que le lot ne ferme pas, et qui reste donc ouvert** : le recouvrement du dessin par le bouton
lui-meme. Un cadre plus grand couvre **plus** de dessin, pas moins ; le fond translucide a 55 % reste
pose sur le trait qu'il designe, et `ines-design` le dit elle-meme en section 3.3. **L'obstacle
transverse n'est donc pas referme - il est borne**, par ce que la decision 1 et le mecanisme 2 de cet
`ADR` mettent en place, et il reste entier pour un futur paquet de designation. La difference avec
s34 est la seule qui compte : il n'est plus route sans mecanisme.
