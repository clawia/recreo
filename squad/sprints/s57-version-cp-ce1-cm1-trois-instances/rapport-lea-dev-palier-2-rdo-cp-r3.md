# s57 - palier 2, p2-12 : RDO-CP-R3, voie (iii) - rapport de `lea-dev`

- **Auteure** : `lea-dev`. **Date** : 2026-10-16.
- **Base** : `0ea24709` (`round-two`). Branche `s57-lea-rdo-cp-r3`, creee apres l'amendement de story (reflog de `round-two` : `b1b71f80` a 18:07:52, branche creee a 18:25). Les RD3-9 et RD3-10 lisent `78270b4c`, base ecrite par la story.
- **Commit livre** : `23222465` (`[LEA] s57: RDO-CP-R3 voie (iii) ...`). Un second commit `[LEA]` porte ce rapport seul.
- **Ce que ce fichier n'est pas** : aucun Done. Le Done technique est celui de la CI, le Done pedagogique est a `nadia-prof`.

## 1. Ce qui change

Cinq fichiers, une seule phrase : `Voici la bonne oeuvre. Appuie dessus.`, mots de `nadia-prof` (`reponse-nadia-pa7-pa8.md` 2.4), formes d'`ADR-0067` D.4 inchangees.

| Fichier | Changement |
|---|---|
| `adapters/adapter-web/src/main/resources/templates/fragments/manche.html` (ligne 214) | le noeud de texte de `.reponse-revelee` : trois `&nbsp;` dans la premiere phrase, un dans la seconde, une espace ordinaire entre les deux |
| `LaPhraseDeRevelationDUneMancheEnImageNeSeCoupeJamaisDansUnePhraseTest` | `PHRASE_DE_M4`, `PREMIERE_PHRASE`, compte litteral `!= 5` devenu `!= 3` et son message ; les cinq morsures reecrites sur la phrase neuve ; la morsure "phrase d'avant" devient "phrase de M4 sans aucune U+00A0" (voir section 5) |
| `PartieControllerPropositionsEnImageTest` | `containsExactly` sur la phrase neuve |
| `TexteAccentueDesGabaritsTest` | l'entree de l'inventaire |
| `LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest` | `PHRASE_DE_REVELATION` |

Aucune feuille de style, aucun autre gabarit, aucun fichier ArchUnit, pas de modification du garde-fou de l'accent, aucun ADR.

## 2. Commandes de la story, lues sur `23222465`

```
RD3-1  1      RD3-2  0      RD3-3  0      RD3-4  0      RD3-5  3
RD3-6  5      RD3-7  0      RD3-8  0      RD3-9  2      RD3-10 0
RD3-11 9      RD3-12 6      RD3-13 2
assertThat( : 9 (garde-fou de D.4), 22 (PartieControllerPropositionsEnImageTest), 23 (LesPropositionsEnImage...Test)
precondition de forme : git log --oneline 78270b4c..23222465 -- <gabarit> | wc -l  rend 1
```

`RD3-4` rendait 1 sur mon premier commit : la morsure "phrase d'avant" du garde-fou citait encore *C'est cette oeuvre qui va avec.* Corrigee avant livraison, commit amende (local, jamais pousse).

## 3. Tests

- **Delta : +0 test.** Aucune methode ajoutee ni retiree (`@Test` : 2 dans le garde-fou, avant et apres). Les cinq fichiers changent de texte et d'un compte litteral.
- **Mesure** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl`, sur `23222465` : `BUILD SUCCESS`, 5 min 30. JDK 21.0.10 (rapporte par la machine virtuelle des tests, ligne `Toolchain` de la sortie). Lignes `Tests run` du reacteur : 120, 31, 501, 1630, 381, 6 ; 0 echec, 0 erreur, 0 ignore. Somme 2669 : c'est la base plus mon lot, **pas un total d'arbre fusionne** ; `sofia-sm` l'etablit.
- **Rouge avant** : sur le seul gabarit change, la story a mesure 4 echecs (`adapter-web`) et 3 (`bootstrap`) ; je n'ai pas rejoue ce rouge.
- **Morsure rejouee** : une espace ordinaire remise entre *Voici* et *la* dans le gabarit (`Voici la&nbsp;bonne&nbsp;oeuvre.`), `LaPhraseDeRevelationDUneMancheEnImageNeSeCoupeJamaisDansUnePhraseTest` : `Tests run: 2, Failures: 1` (le test qui lit le gabarit rendu). Gabarit restaure ensuite : `git diff --stat` sur ce fichier, 1 ligne.
- **Avant la morsure** : les trois classes web (`LaPhrase...`, `TexteAccentueDesGabaritsTest`, `PartieControllerPropositionsEnImageTest`) : 2 + 9 + 5 = 16 tests, 0 echec, `-pl adapters/adapter-web -am`, mesure partielle.

## 4. Jobs de `ci.yml` lances

| Job | Lance | Sortie |
|---|---|---|
| `build` | oui, `mvn-jdk21.sh -B clean verify` | `BUILD SUCCESS`, 2669 tests, 0 echec |
| `chemins` | oui, les deux etapes (test du garde-fou, puis verification sur `git ls-files`) | codes de sortie 0 et 0 |
| `prefixes` | oui, ses quatre etapes en local (test des prefixes, test de plage, prefixes sur `0ea24709..HEAD`, test et verification du proprietaire du garde-fou de l'accent) | tous 0 ; "0 commit(s) le touchent dans la plage" |
| `outils` | oui, les trois motifs de balayage, 13 juges (6 + 2 + 5), `python` | aucun echec |

Limite : ces jobs sont lances sur mon poste (bash et Python locaux), pas sur `ubuntu-latest`. La plage de `prefixes` est `0ea24709..HEAD`, pas celle qu'un push donnerait.

## 5. Jeu joue sur le jar

- **Jar** : `bash outils/mvn-jdk21.sh -B clean verify` sur `23222465`, copie hors depot, SHA-256 `a9772590b03a2f42b482f2ad753f0679a27379771ca93269db611c80ff81e299`, JDK 21.0.10, port 18531, arret par le PID lu dans le journal. Chrome sans tete, Node v24.12.0.
- **Methode** : `squad/conformite/releves-s57/pilote-pa8.mjs`, sans variante, six items (les six oeuvres de reference), deux erreurs puis la revelation, 720, 744 et 768 x 551, avec barre classique et sans (`scrollbar-width: none`). Un mot est une suite sans espace ordinaire : chaque phrase est donc un mot, et `morceaux` est le nombre de rectangles de sa boite (1 = elle ne se coupe pas).
- **Critere 1 : 36 mesures sur 36.** Pour chacune : bord droit de chaque phrase au plus au bord droit de `.reponse-revelee`, `scrollWidth` egal a `clientWidth`, texte lu (U+00A0 lue comme espace) egal a `Voici la bonne oeuvre. Appuie dessus.`
- **Critere 3, situation (a).** Les deux phrases ont `morceaux` = 1 aux 36 mesures : aucun retour a la ligne dans une phrase. La coupure est entre les deux, sur deux lignes aux 36 mesures.
- **Cotes** (identiques sur les six items) : premiere phrase 191 px, seconde 131,9 px. Colonne 229 px a 720 avec barre (marge 38 px), 244 px ailleurs (marge 53 px). Marge minimale sur les 36 mesures : 38 px. Bas de `.reponse-revelee` : 523 a 720 avec barre, 528 sinon (744 : 528 aux deux modes).
- **Vu a l'ecran** (captures, hors depot, revelation du premier item, 744 et 720 avec barre) : la phrase est sur deux lignes dans la colonne a droite du signal, a droite du verdict, au bas de la zone ; pas de barre horizontale.
- **Critere 5** : `pilote-poste-8.mjs` sur le meme jar (720, 744, 768, 992, six items), puis `python outils/juger-pli-deux-oeuvres.py` : `VERDICT DES COTES : tout passe` (code 0).

## 6. Non mesure

1. **Aucune police imposee** : ni les neuf polices de `pilote-pa8.mjs` (`POLICES=1`), ni un appareil reel. 191 px est la largeur sous la police du poste de mesure, en Chrome ; la marge de 38 px ne vaut pas pour une autre police.
2. **Un seul navigateur** : Chrome 155 sans tete. Ni Firefox, ni Safari.
3. **Hauteurs autres que 551 et largeurs 992 et plus** : hors critere, non mesurees pour la phrase (le releve de 992 sert au seul juge de pli).
4. **Etats autres que la revelation** (juste, erreurs) : le texte de ce noeud n'y est pas servi ; non regardes pour cette phrase.
5. **Le rejeu de `nadia-prof`** : non fait.
6. **Le critere 5 repose sur le juge, qui ne voit pas le critere 1** (la story le dit) : `J2` lit le bas de `m4`, pas un mot contre sa boite.

## 7. Ouvert, a remonter

- **Q-T15** : la fusion exige la trace de lecture de `theo-archi` sur `23222465`. Non demandee par moi.
- **Commentaires devenus faux, non touches** : le commentaire de `manche.html` (lignes 207 a 213) et celui de `TexteAccentueDesGabaritsTest` (autour de la ligne 157) disent la phrase "de M4 ... copiee telle quelle" ; la phrase de `nadia-prof` est desormais celle de `reponse-nadia-pa7-pa8.md` 2.4. Toucher le commentaire du gabarit ferait passer `RD3-9` de 2 a plus de 2 ; je n'ai rien change et je demande a `sofia-sm` si elle veut un commentaire a jour, dans quel fichier.
- **Morsure "phrase d'avant"** : la story ne la nomme pas. Je l'ai remplacee par la phrase de M4 ecrite sans U+00A0 (texte juste, espaces fausses), pour que `RD3-4` rende 0 et que `RD3-12` reste a 6. C'est un choix de redaction de test, a relire.
