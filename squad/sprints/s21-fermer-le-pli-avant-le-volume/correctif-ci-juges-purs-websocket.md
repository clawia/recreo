# Correctif de CI post-cloture - websocket au niveau du module rendait les juges purs inatteignables

Ce n'est pas une fusion de lot de sprint : `s21` est clos et pousse (`RAPPORT.md`, `review.md`,
`retro.md` existent, la cloture date du 2026-08-28). Ce journal documente un correctif de CI
constate apres cette poussee, sur `round-two` deja ferme.

- **Auteur du correctif** : `theo-archi`, branche `s21-theo-4`, worktree dedie, un seul commit
  (`00ff7a5`) reparti de `round-two` a `16f04da`.
- **Auteur de cette fusion et de ce journal** : moi (`sofia-sm`, casquette Scrum Master).
- **Convention de chiffres tenue dans tout le document** : chaque chiffre porte sa methode, son
  perimetre et sa date dans la meme phrase.

## 1. Le rouge

`round-two` a ete pousse a `6512e55` (cloture de `s21`), puis a `16f04da` (deux amendements hors
sprint a `squad/README.md`). La CI a rougi sur `6512e55` : un seul job en echec, **"Juges purs des
outils de poste"** - l'etape que `theo-archi` avait elargie a `squad/outils/*/*-test.sh` dans son
lot `S21-1`. **23 cas en echec, aucun juge execute.** Les trois autres jobs, `build` compris,
etaient verts.

Motif : `squad/outils/nadia-prof/balayage-largeur-rangee.py` faisait `import websocket` au niveau
du module. `balayage-largeur-rangee-test.sh` charge ce module par `importlib` pour atteindre ses
juges purs ; le runner de CI n'a pas cette bibliotheque tierce, donc le chargement du module
echouait avant le premier jugement, sur les 23 cas du fichier d'un coup.

## 2. Verification de provenance

```
bash squad/outils/sofia-sm/verifier-provenance-lots.sh "s21-theo-4=00ff7a5"
```

```
branche 's21-theo-4' : lignee confirmee, un ancetre commun avec round-two existe.
tous les lots verifies descendent de round-two avec la tete annoncee.
```

**Lignee reelle, verifiee au-dela du script.** `git merge-base s21-theo-4 16f04da` rend `16f04da` :
c'est exactement la tete de `round-two` local au moment de la fusion. La branche est un unique
commit repose directement sur cet etat, pas un worktree parti d'une tete plus ancienne.

## 3. Fusion

`git merge --ff-only 00ff7a5` sur `round-two` (local a `16f04da`) : `Fast-forward`,
`16f04da..00ff7a5`, aucun conflit possible - un seul commit, une seule branche, base = tete exacte.
`round-two` local est maintenant a `00ff7a5`, non pousse (la poussee reste la main de
l'utilisatrice).

## 4. Ce que corrige le commit

Diff verifie par lecture (`git diff 16f04da 00ff7a5`) : cinq fichiers touches, aucun code Java.

- `squad/outils/nadia-prof/balayage-largeur-rangee.py` : l'`import websocket` de tete de fichier
  est retire et remplace par une fonction `ouvrir_connexion(url_ws, secondes=30)`, **seul point du
  depot qui touche desormais `websocket`**. Sans la bibliotheque, c'est cette fonction qui echoue,
  bruyamment, avec la marche a suivre (`MesureNonFiable`) - pas le chargement du module. Les quatre
  juges purs du fichier (`juger_port_libre_avant_lancement`, `juger_port_du_profil`,
  `juger_regle_injectee`, `juger_viewport`) restent atteignables sans elle.
- `squad/outils/nadia-prof/lire-le-pli-a-l-ecran.py` : sa propre copie de
  `websocket.create_connection` est retiree, remplacee par un appel a
  `blr.ouvrir_connexion`, meme motif.
- `squad/outils/nadia-prof/balayage-largeur-rangee-test.sh` : un cas neuf charge le module dans un
  interpreteur ou tout import hors bibliotheque standard est refuse - perimetre **derive de
  `sys.stdlib_module_names`**, jamais une liste de noms ecrite a la main - puis appelle quatre
  juges. Morsure verifiee par mutation sur ce poste (ou `websocket` **est** installee) : remettre
  l'import en tete de fichier rend ce cas rouge et lui seul, 29 cas sur 30 restant verts.
- `squad/decisions/adr/ADR-0025-...md`, section 3 : second amendement date (2026-08-28). Clause
  ajoutee - un fichier dont un juge pur est teste par cette etape ne fait aucun import hors
  bibliotheque standard au niveau du module ; les dependances tierces vivent dans les fonctions qui
  en ont besoin. Installer `websocket-client` dans le workflow est explicitement refuse : cela
  rendrait le job vert et lui ferait perdre son sens de garde-fou.
- `squad/decisions/adr/ADR-index.md` : ligne `ADR-0025` mise a jour pour citer le second amendement.

## 5. Ce que le lot etablit, au-dela du symptome

**Le defaut n'etait pas isole.** Etabli par commande le 2026-08-28 dans le worktree `s21-theo-4` -
chaque module python de `outils/` et `squad/outils/` charge par `importlib` dans un interpreteur
qui ne voit pas les paquets installes (`python -S`) : **avant le correctif, 9 modules sur 17**
refusaient de se charger, tous sur `ModuleNotFoundError: No module named 'websocket'`, dont
`mesurer-candidats-rep-8.py` d'`ines-design` par import transitif. **Apres le correctif : zero.**
Une seule ligne deplacee dans le module de plomberie, plus une dans son frere, libere les neuf.

**Seuls 2 modules sur ces 17 sont charges par un test.** Verifie par la meme methode (`grep -oE
'[A-Za-z0-9_-]+\.py'` sur les six fichiers `*-test.sh` du depot) : `rapport-tokens.py` et
`balayage-largeur-rangee.py`. Les huit autres modules portaient la meme propriete sans que rien ne
puisse la voir. Comme le formule `theo-archi` : l'etape n'a pas trouve un defaut, elle a trouve le
seul de sa famille qui etait observable.

**Dette nommee, non corrigee, declencheur ecrit.** Cinq scripts de `squad/outils/nadia-prof/`
lisent `sys.argv` au niveau du module et echouent au chargement (`IndexError`) ; aucun n'est charge
par un test aujourd'hui, donc aucun n'est concerne par la clause ajoutee a `ADR-0025`. Le jour ou
l'un d'eux recoit un juge pur teste par cette etape, son corps de script passe sous
`if __name__ == "__main__":` comme les autres.

## 6. Deux mesures de `theo-archi` jetees plutot que citees, pour memoire

Sa premiere tentative de reproduction locale du rouge etait un **faux rouge** : 23 cas en echec,
mais sur `command not found`, pas sur l'import - par coincidence le meme compte qu'en CI. Une
seconde mesure a ete **contaminee par son propre `git stash`** lance pendant qu'elle tournait. Ni
l'une ni l'autre n'est citee comme preuve dans ce journal ; seule la mesure propre l'est.

## 7. Porte de sortie, mesuree par moi-meme sur l'etat fusionne

```
bash outils/mvn-jdk21.sh -B clean verify
```

A la racine du reacteur (six modules, aucun `-pl`), le 2026-08-28, sur `round-two` a `00ff7a5`.
`BUILD SUCCESS`. Blocs `Results:` releves dans le journal de cette commande, jamais deduits d'un
total :

| Module | Bloc `Results:` |
|---|---|
| `recreo-domain` | `Tests run: 49, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-application` | `Tests run: 13, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-adapter-web` | `Tests run: 332, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-adapter-content` | `Tests run: 224, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-bootstrap` (surefire) | `Tests run: 98, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-bootstrap` (failsafe) | `Tests run: 6, Failures: 0, Errors: 0, Skipped: 0` |
| **total, somme des six blocs** | **722** |

Total identique a celui de la cloture de `s21` (`RAPPORT.md`) : ce lot ne touche aucun code Java,
la mesure le confirme plutot que de le supposer.

```
bash squad/outils/nadia-prof/balayage-largeur-rangee-test.sh
```

et les cinq autres fichiers `outils/*-test.sh` / `squad/outils/*/*-test.sh`, avec exactement les
memes globs que l'etape CI "Juges purs des outils de poste" : **6 fichiers trouves, 6 verts, 0
echec**, execute par moi-meme le 2026-08-28 sur `round-two` a `00ff7a5`.

Le reacteur est libre.

## 8. Ce que cet incident etablit sur le Done technique de la cloture de `s21`

`RAPPORT.md` (section "Etat des deux Done") et `review.md` disaient deja, au moment de la cloture :
*"ce Done technique est une mesure locale concordante sur trois executions independantes, pas un
verdict de la CI sur cet arbre"*. **La CI a depuis rendu son verdict, et il etait rouge sur
`6512e55`.** Ce n'est pas une nuance a adoucir : le vert local etait juste - mesure trois fois de
maniere concordante par `sofia-sm`, `yanis-qa` et `theo-archi` - **et** dependant d'un poste ou
`websocket-client` est installee, une variable que ni `mvn verify` ni les trois mesures locales ne
faisaient varier. La CI a trouve exactement ce qu'un environnement different d'un poste de
developpement est fait pour trouver.

## 9. Ce qui reste hors du perimetre de ce correctif

- Les cinq scripts de `nadia-prof` qui s'executent a l'import : nommes en section 5, non corriges,
  declencheur ecrit dans `ADR-0025`.
- Les obstacles `s21` deja ouverts a la cloture (`review.md`, lignes 14, 16, 21 a 24) : aucun n'est
  touche par ce lot.

## 10. Etat de `round-two` apres cette fusion

`round-two` local est a `00ff7a5`, **non pousse** - la poussee reste la main de l'utilisatrice.
Porte technique verifiee vraie a la racine : `mvn verify` `BUILD SUCCESS`, 722 tests sur six blocs
`Results:`, 0 echec ; les six juges purs verts avec les globs de l'etape CI. Mesure par moi-meme le
2026-08-28.
