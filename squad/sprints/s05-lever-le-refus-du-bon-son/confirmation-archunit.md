# [Sprint s05] Confirmation ArchUnit - theo-archi

- **Date** : 2026-08-06
- **Auteur** : theo-archi
- **Arbre juge** : `round-two`, commit `099e750`, **plus les fichiers du present commit `[THEO]`** -
  `ADR-0018`, ses deux regles, ses deux contre-exemples, son test de comportement, la separation des
  deux canaux dans `adapter-web`, les amendements d'`ADR-0011` et d'`ADR-0013`, et l'index.
- **Prerequis** : `JAVA_HOME` sur un JDK 21, positionne pour la duree de la commande et jamais ecrit
  dans un fichier commite.
- **Ce que je ne fais pas ici** : aucun Done pedagogique ni son anticipation - il appartient a
  `nadia-prof` seule. Aucun arbitrage produit.

## Le resultat de la commande

`mvn -B clean verify`

```
recreo ............................................. SUCCESS
recreo-domain ...................................... SUCCESS      38 tests
recreo-application ................................. SUCCESS       7 tests
recreo-adapter-web ................................. SUCCESS      82 tests
recreo-adapter-content ............................. SUCCESS      16 tests
recreo-bootstrap ................................... SUCCESS   61 + 2 tests
BUILD SUCCESS
```

**206 tests, 0 echec.** Inventaire des regles : **29 `ArchRule` declarees**, toutes passees a un
`check` - verifie par `LesGardeFousSontMisALEpreuveTest`, pas par moi. Plafond de dette de balayage
inchange a **3**.

Deux echecs rencontres en chemin, tous deux miens et tous deux resolus, notes parce qu'un vert qui
n'a jamais rougi n'apprend rien :

1. Premier `verify` : `BUILD FAILURE` sur le `clean` de `bootstrap`. Cause reelle, pas supposee -
   une instance de l'application que j'avais lancee pour reproduire le defaut tenait `recreo.jar`
   verrouille. C'est exactement ce que la Javadoc de `JarRecreoIT.arreter` documente. Processus
   arrete, `verify` relance, vert.
2. Aucun echec de test.

## Ce que j'ai mesure avant de conclure, plutot que sur parole

Le defaut signale a ete reproduit sur le jar de `099e750`, pas admis :

| Mesure | Resultat |
|---|---|
| `propositionChoisie=a` sur `bandit-manchot-homophones-cm1` | verdict normal, HTTP 200 |
| `propositionChoisie=à`, session neuve | ecran d'erreur, HTTP 200 |
| Journal complet apres declenchement | **21 lignes, toutes de demarrage. Aucune trace.** |
| Type reel de l'exception (journal en `DEBUG`) | `org.apache.tomcat.util.http.InvalidParameterException` |
| Sa hierarchie (`javap` sur `tomcat-embed-core-11.0.22.jar` extrait du jar) | `extends java.lang.IllegalStateException` |
| Un troisieme type, hors sous-arbre, sur la route du jeu | `{"timestamp":...,"status":400,...}` en clair |

Le point decisif est la ligne `javap` : le gestionnaire n'attrapait pas deux situations, il attrapait
deux **sous-arbres**, et une exception du conteneur Tomcat empruntait celui de la session perdue.
`ADR-0018` en tire la decision et les regles.

Apres correction, sur le jar reconstruit et **au niveau de journalisation par defaut** : le meme coup
accentue produit une ligne `ERROR` avec la pile complete et le point d'entree ; un coup sans partie
en cours produit une ligne `INFO` ; une ressource statique absente reste sur le comportement par
defaut de Spring Boot, sans fragment de jeu. L'ecran rendu a l'enfant est inchange dans tous les cas.

## Ce qui reste ouvert, et a qui

- **La correction d'encodage elle-meme appartient a `lea-dev`** et n'est pas dans ce commit. Le
  defaut est toujours present ; ce qui change est qu'il ne peut plus etre silencieux.
- **Dette nommee, non fermee** : un parametre manquant sur la route du jeu rend encore du JSON brut
  en 400. Mesuree, ecrite dans `ADR-0018`, section "Ce que cette regle ne couvre pas". Aucune story
  ne la demande.
- **Meme forme ailleurs, non atteignable aujourd'hui** : un paquet de contenu epuise avant
  `nombreDeManches` rend l'ecran de fin de partie normal, sans trace. Les deux paquets du catalogue
  portent 22 items et une source infinie pour `nombreDeManches: 10` ; `CatalogueChargeableIT` ne
  verifie que la borne basse. Je n'ecris pas de regle sur un cas jamais constate. L'arbitrage de
  couverture revient a `yanis-qa` et `camille-po`.
- **Cinquieme occurrence du perimetre qui ne derive pas de la source**, portee dans `ADR-0013` par
  amendement plutot que laissee a une sixieme decouverte : les **donnees d'entree** d'un test font
  partie de son perimetre. `PartieHttpTest` joue une partie complete contre un catalogue ecrit a la
  main dont les propositions sont `"1"`, `"2"`, `"3"` ; `CatalogueChargeableIT` parcourt le vrai
  catalogue mais n'appelle jamais `jouer`. L'encodage de cette occurrence est le garde-fou de contenu
  de `lea-dev` ; la generalisation reste une doctrine de revue, et l'amendement le dit au lieu de la
  deguiser en regle.
