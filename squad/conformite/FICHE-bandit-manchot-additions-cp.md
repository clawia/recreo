# Fiche de conformite - bandit-manchot-additions-cp

- **Mecanique** : bandit manchot (mecanique generique - tirage d'une question, choix parmi
  plusieurs propositions dont une seule correcte, retour immediat juste/faux, enchainement de
  plusieurs manches avec score cumule).
- **Contenu(s)** : additions CP dont les deux operandes et la somme sont compris entre 0 et 10.
  Matiere premiere de reference (a repasser devant `nadia-prof` comme un contenu neuf, pas
  reprise telle quelle) : `contenu-repris/cp-calcul-mental.yml`.
- **Statut global** : REFUSE
- **Derniere mise a jour** : 2026-08-05

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| mathematiques | CP (cycle 2) | Nombres et calculs | Calcul mental - additions dans la limite de 10 (sous-partie de la competence referentiel, qui couvre aussi "puis 20", hors perimetre de ce jeu pour l'instant) | `squad/programme/mathematiques/competences-cp.md` |

## Criteres pedagogiques attendus - camille-po

- [x] Chaque manche presente une addition dont les deux operandes et la somme sont des entiers
      compris entre 0 et 10 (jamais de somme superieure a 10).
- [x] Chaque manche presente une unique bonne reponse parmi l'ensemble des propositions
      affichees.
- [x] Les propositions fausses (distracteurs) sont des nombres entiers plausibles, proches de la
      bonne reponse, jamais negatifs et jamais non entiers.
- [ ] La consigne et les retours (juste/faux) sont formules en une phrase courte, en vocabulaire
      courant de CP (pas de terme comme "somme" ; une formulation du type "combien ca fait ?").
- [x] Le retour juste/faux est accompagne d'un signal visuel (forme et/ou couleur) distinct du
      texte, sans emoji dans le gabarit (voir `DEC-0002`), pour un enfant qui ne dechiffre pas
      encore vite.
- [x] Les additions proposees varient d'une manche a l'autre sur une session de jeu (pas de
      question figee qui se repete en boucle).
- [x] Rien dans le code de la mecanique ne fait reference a "addition", "CP" ou aux bornes 0-10 :
      tout provient du contenu injecte (verifie par la regle ArchUnit de `theo-archi`, story
      S0-3 de `docs/backlog/backlog.md`).
- [ ] Tout texte destine a l'enfant (consigne, retours juste et faux, libelles de bouton,
      messages d'etat comme la fin de partie ou l'absence de partie) est redige en francais
      correct et integralement accentue, orthographe et ponctuation irreprochables (exigence de
      categorie B ajoutee par l'amendement du 2026-08-04 a `DEC-0002`, en reaction directe au
      premier motif de refus de ce sprint).
- [ ] Le decompte de score affiche a l'enfant utilise une forme qu'un enfant de CP sait lire
      (par exemple un nombre de reussites seul, ou un nombre d'etoiles/jetons), jamais une
      notation "x / y" ni de barre oblique (deuxieme motif de refus de ce sprint).
- [ ] Toute erreur cote serveur (session perdue, redemarrage) affiche a l'enfant un message
      court en francais avec un moyen visible de repartir, jamais le corps brut d'une erreur
      HTTP (troisieme motif de refus de ce sprint).
- [ ] Quand un meme item est rate a trois reprises (S1-5, `DEC-0004` point 4), l'indice affiche a
      la representation suivante de cet item est un contenu neuf, candidat a l'audit de
      `nadia-prof` au meme titre que la consigne ou le retour faux : sa formulation actuelle,
      "Compte avec tes doigts pour t'aider.", n'a encore ete jugee ni juste ni fausse
      pedagogiquement par personne. Prealable produit trouve en review et acte par `DEC-0005` :
      ce texte doit venir du paquet de contenu YAML (comme la consigne), jamais d'une constante du
      code du domaine ; correction de perimetre non bloquante pour le verdict de ce sprint,
      portee au backlog de s02.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : REFUSE
- **Date** : 2026-08-04
- **Audit joue** : oui. Fat jar `bootstrap/target/recreo.jar` lance sur le port 8099 avec le JDK
  21. Trois sessions reellement jouees : une session de 41 manches en repondant juste a chaque
  fois, une session de 20 manches en repondant faux a chaque fois, une session courte en
  alternant, plus des manches jouees a la main sur la page d'accueil, l'ecran de jeu, le retour
  juste et le retour faux. 61 manches relevees enonce par enonce, avec verification arithmetique
  de chaque somme, de chaque proposition et de la position de la bonne reponse. Lecture du HTML
  reellement servi, de `adapters/adapter-web/src/main/resources/templates/` (les trois gabarits)
  et de `adapters/adapter-web/src/main/resources/static/css/recreo.css` pour juger le rendu.
  Audit non degrade.

### Ce qui a ete verifie

- **Ancrage au programme** : la competence annoncee est bien celle travaillee. Chaque manche
  demande une somme de deux entiers dont le resultat reste dans la limite de 10, ce qui correspond
  a la ligne "Calcul mental - additions dans la limite de 10 (puis 20)" de
  `squad/programme/mathematiques/competences-cp.md`, domaine "Nombres et calculs". Ce n'est pas
  une competence voisine plus facile : l'enfant calcule, il ne reconnait pas et ne recite pas.
  Reserve d'ancrage cependant : le tirage est uniforme sur les 66 couples possibles, donc environ
  un tiers des manches comportent un operande nul (releve : 16 manches sur 61, soit 26 %, pour
  une esperance theorique de 21 couples sur 66, soit 32 %). Or "7 + 0" ne fait pas travailler le
  calcul mental, il fait constater une propriete du zero. Un quart a un tiers du temps de jeu ne
  travaille donc pas la competence visee. Le "puis 20" de la ligne du referentiel reste hors
  perimetre, conformement a `DEC-0001`.
- **Lisibilite au niveau vise** : la consigne est bonne et je la valide telle quelle. "Combien ça
  fait ?" fait trois mots, tous du vocabulaire oral et de lecture de CP, tous composes de
  graphemes travailles en CP, et elle evite le mot "somme" comme demande. Le reste du texte
  affiche a l'enfant n'est pas au niveau : "Manche suivante" emploie "manche" dans un sens de jeu
  que l'enfant de CP n'a pas (pour lui une manche est celle d'un vetement) et "suivante" est un
  mot abstrait ; "Score : 1 / 2" emploie la notation fractionnaire, qui n'est pas une notion de
  CP ; "Additions CP", libelle du bouton d'accueil, contient le grapheme "ti" prononce /si/, qui
  n'est pas une acquisition de CP (reconnaissance globale seulement) ; "La partie est terminee.
  Merci d'avoir joue !" et "Aucune partie disponible pour le moment." sont d'un registre
  d'adulte. Rendu : aucune regle CSS ne grossit l'enonce, la consigne ni le verdict, qui
  s'affichent donc a la taille par defaut de 16 px, soit plus petit que les boutons de
  proposition (1,1 rem). Le calcul a lire est le plus petit texte de l'ecran.
- **Charge cognitive** : correcte sur le coeur du jeu. Trois propositions, une seule question a
  l'ecran, une seule action possible, pas de chronometre, pas de score a interpreter pour agir.
  L'enfant tient en tete deux nombres et rien d'autre. Un element parasite : la ligne
  "Score : x / y" est posee juste au-dessus d'une expression arithmetique, dans un jeu de
  mathematiques, avec un symbole que l'enfant de CP ne sait pas interpreter - il y a un risque
  reel qu'il la lise comme une operation a faire.
- **Progression** : il n'y en a aucune. Le tirage est uniforme et sans memoire d'une manche a
  l'autre : la manche 1 peut etre "10 + 0" et la manche 40 "0 + 1". La difficulte ne monte pas,
  ne s'adapte pas a ce que l'enfant reussit ou rate, et la partie ne s'arrete jamais - verifie
  dans le jeu (41 manches enchainees sans fin) et confirme par la Javadoc de
  `AdditionsBorneesContentSource.next`, qui documente que ce tirage ne renvoie jamais de fin. Il
  n'existe par ailleurs aucun bouton pour revenir a l'accueil ni pour arreter : l'enfant n'a
  aucun moment "j'ai fini".
- **Traitement de l'erreur** : le retour faux fait le minimum utile - il ne dit pas seulement
  "faux", il donne la bonne reponse, ce qui vaut mieux qu'un simple verdict. Mais il s'arrete la :
  pas de deuxieme essai, pas d'aide pour recalculer, et surtout aucune adaptation. J'ai joue 20
  manches fausses d'affilee : l'enfant voit vingt fois la meme phrase et un score qui reste a
  0 sur 20, sans un mot d'encouragement, sans que le jeu ne facilite quoi que ce soit, et sans
  pouvoir s'arreter autrement qu'en fermant l'onglet. Un enfant en difficulte n'est pas aide, il
  est enferme. Second point sur ce chapitre : quand la session HTTP est perdue (onglet laisse
  ouvert, serveur redemarre), le clic suivant de l'enfant remplace le jeu par un bloc technique
  en anglais - verifie, la reponse servie est
  `{"timestamp":...,"status":500,"error":"Internal Server Error",...}`. L'enfant est bloque devant
  du texte qu'il ne peut pas lire et ne peut pas repartir seul. Je decris le symptome, la cause
  technique appartient a `theo-archi`.
- **Exactitude du contenu** : les mathematiques sont exactes, sans une seule faute sur 61 manches
  relevees. Aucune somme superieure a 10, aucun operande hors 0-10, la bonne reponse figure
  toujours exactement une fois, sa position varie (releve sur 41 manches : 16 fois en premier,
  14 fois en deuxieme, 11 fois en troisieme, donc aucune strategie de position exploitable),
  aucune proposition negative ni non entiere, et le libelle de chaque bouton est bien la valeur
  qu'il envoie. Les distracteurs sont toujours a un, deux ou trois de la bonne reponse : ils sont
  plausibles, jamais absurdes, et la bonne reponse n'est pas systematiquement la valeur du milieu.
  Les distracteurs qui depassent 10 (11 et 12) ne sont pas un defaut : ils empechent l'enfant de
  gagner par elimination sans calculer. Les 41 enonces d'une meme partie etaient tous differents.
  En revanche le texte francais affiche a l'enfant comporte trois fautes d'orthographe, detaillees
  dans le motif ci-dessous.
- **Biais et ton** : rien a signaler, et c'est a mettre au credit de l'increment. Aucun personnage,
  aucun prenom, aucun contexte familial, materiel ou culturel presuppose : des nombres nus, donc
  aucun stereotype de genre, d'origine ou de milieu possible. Le ton n'est ni condescendant ni
  infantilisant : "Bravo, c'est juste !" est juste, sobre et non exagere ; le retour faux ne
  culpabilise pas et ne juge pas l'enfant, il corrige la reponse. Le signal rouge est associe a
  la reponse, pas a l'enfant. Aucun classement, aucune comparaison a d'autres enfants.

### Motif

Le Done pedagogique est refuse pour trois motifs. Les deux premiers portent sur du texte lu par
l'enfant, le troisieme sur un ecran d'echec que l'enfant ne peut pas franchir.

1. **Le texte francais affiche a l'enfant comporte trois fautes d'orthographe, sur l'ecran qu'il
   voit a chaque erreur.** Dans
   `adapters/adapter-web/src/main/resources/templates/fragments/retour.html`, le retour faux est
   ecrit "Ce n'est pas ca. La bonne reponse etait X." Il faut lire "Ce n'est pas ça. La bonne
   réponse était X." : "ca" sans cedille n'est pas "ça", "reponse" et "etait" sans accent aigu ne
   sont pas "réponse" et "était" - sans accent, "etait" ne se lit meme pas comme un mot francais.
   Un enfant de CP est en train de construire son orthographe lexicale et ses correspondances
   graphemes-phonemes : tout texte mis sous ses yeux doit etre orthographiquement irreprochable,
   et l'accent aigu est un grapheme, pas une decoration. La meme faute est presente dans le
   message de fin de partie du meme gabarit, "La partie est terminee. Merci d'avoir joue !"
   (lire "terminée", "joué"). C'est une erreur de contenu scolaire, donc un motif de refus a lui
   seul. Precision importante pour la correction : le probleme est bien dans ces gabarits et non
   dans un encodage, puisque la consigne portee par `catalogue.yaml`, "Combien ça fait ?", est
   correctement accentuee et arrive intacte dans le HTML servi (en-tete
   `Content-Type: text/html;charset=UTF-8`). Correction attendue : accentuer tout le texte destine
   a l'enfant dans les trois gabarits. Voir aussi ma relecture de `DEC-0002` ci-dessous : cette
   faute est la consequence directe d'un manque de cette decision.
2. **Le score est affiche dans une notation que l'enfant de CP ne sait pas lire, au-dessus du
   calcul a faire.** L'ecran affiche "Score : 0 / 0" des la premiere manche, puis "Score : 1 / 2".
   La notation a / b comme rapport reussites sur essais n'est pas une notion de CP, et la barre
   oblique n'y a aucun sens etabli. Placee juste au-dessus d'une expression du type "6 + 3" dans
   un jeu de mathematiques, elle peut etre prise pour une operation. Correction attendue : afficher
   un decompte que l'enfant sait lire, par exemple un nombre de reussites seul ou un nombre de
   jetons ou d'etoiles gagnes, et ne pas montrer le nombre d'essais rates sous forme de rapport.
3. **Quand la partie est perdue cote serveur, l'enfant recoit un bloc technique en anglais et ne
   peut pas repartir.** Reproduit : un coup joue sans partie en cours renvoie un HTTP 500 dont le
   corps JSON est insere dans la zone de jeu, soit
   `{"timestamp":"...","status":500,"error":"Internal Server Error","path":"..."}`. Un enfant de CP
   ne peut ni lire ce texte ni comprendre qu'il doit recharger la page ; il est bloque sans issue,
   ce qui contredit la regle "un enfant bloque doit pouvoir avancer". Correction attendue : sur
   toute erreur, l'enfant voit un message court en francais et un bouton pour recommencer.
   Symptome constate cote ecran ; le traitement technique appartient a `theo-archi`.

### Reserves

Ces points ne sont pas des motifs de refus, mais ils devront etre traites pour que ce jeu soit
autre chose qu'un exerciseur brut. Ils sont nommes separement pour que `camille-po` en fasse des
stories distinctes.

1. **La partie n'a pas de fin et l'enfant n'a aucune sortie.** J'ai enchaine 41 manches sans que
   rien ne s'arrete, et il n'existe aucun bouton pour revenir a l'accueil. Un enfant de CP a
   besoin d'un format court et borne, et d'un moment de cloture ("tu as fini, tu as gagne tant").
   A trancher comme une question produit : longueur d'une partie (a mon avis 10 manches), ecran de
   fin, bouton d'arret toujours visible. A noter que le message de fin existe deja dans le
   gabarit mais qu'il est inatteignable avec ce contenu.
2. **Un quart a un tiers des manches comporte un operande nul.** Releve 16 fois sur 61 manches.
   "7 + 0" ne fait pas travailler le calcul mental dans la limite de 10. Le tirage doit limiter la
   proportion d'additions a operande nul, sans les supprimer completement (la propriete du zero a
   sa place, mais pas un tiers du temps).
3. **Aucune progression et aucune adaptation.** La difficulte est constante et aleatoire, et le jeu
   ne reagit pas a une serie de reussites ni a une serie d'echecs. Attendu a terme : commencer par
   les petites sommes et monter vers les sommes proches de 10, et proposer quelque chose de
   different a l'enfant qui rate plusieurs fois de suite.
4. **Le signal juste/faux est percu mais sa forme ne veut rien dire.** Verifie dans
   `recreo.css` : trois canaux simultanes (fond du bloc teinte, pastille coloree, forme de la
   pastille), donc l'enfant voit instantanement qu'il s'est passe quelque chose - le critere de
   `camille-po` est litteralement satisfait. Mais le sens ne repose que sur l'opposition vert
   (#2e7d32) / rouge (#c62828), et la forme n'apporte aucun sens : un disque et un triangle ne
   signifient ni "juste" ni "faux" pour un enfant de 6 ans, et le triangle rouge evoque plutot un
   panneau de danger. Consequence concrete : un enfant daltonien - environ un garcon sur douze,
   et le vert/rouge est precisement la paire qu'il confond - voit deux pastilles de meme teinte
   apparente et doit deviner laquelle veut dire "juste". Correction attendue, compatible avec
   `DEC-0002` puisqu'elle n'introduit aucun emoji dans le gabarit : remplacer le disque et le
   triangle par les deux signes que l'enfant connait deja de son cahier, une coche pour juste et
   une croix pour faux, dessines en CSS ou en SVG, et garder la couleur comme second canal. Le
   signal devient alors lisible sans couleur et sans lecture.
5. **Le texte a lire est trop petit et le signal trop discret pour un lecteur debutant.** L'enonce,
   la consigne et le verdict sont a la taille par defaut (16 px) et la pastille de signal fait
   1,5 rem, alors que les boutons de proposition sont a 1,1 rem : le calcul a lire est le plus
   petit texte de l'ecran. Attendu : grossir nettement l'enonce (au moins 2 rem), remonter le
   texte courant, et porter la pastille de signal a au moins 3 rem.
6. **Trois libelles a reecrire en vocabulaire de CP.** "Manche suivante" (le mot "manche" n'a pas
   ce sens pour un enfant de CP ; preferer "Encore !" ou "On continue") ; "Merci d'avoir joue !"
   et "Aucune partie disponible pour le moment." (registre d'adulte). Le retour faux gagnerait
   aussi a passer au present et a une seule phrase : "Non, c'est 4." plutot que "La bonne réponse
   était 4."
7. **La perte de la partie au rechargement de la page.** Si l'enfant appuie sur F5, il revient a
   l'accueil et son score repart de zero. Peu grave avec une partie sans fin, mais a revoir en
   meme temps que la reserve 1.

### Ce qu'il faut pour que ce jeu passe a VALIDE

Les trois motifs de refus corriges, c'est-a-dire : le texte de l'enfant correctement accentue, un
score lisible au CP, et un ecran d'erreur franchissable. Les reserves peuvent etre etalees sur les
sprints suivants. Le contenu mathematique lui-meme, la consigne et le ton n'ont pas besoin d'etre
retouches : ils sont bons.

### Suites hors de ma main

- `squad/programme/MATRICE-couverture.md` : la ligne mathematiques / CP / "Calcul mental -
  additions dans la limite de 10 (puis 20)" **reste `en cours`** et ne peut pas passer a
  `couvert-valide`. Je n'ai pas edite cette ligne, elle appartient a `camille-po`.
- Amendement demande a `DEC-0002`, via `sofia-sm` : voir ci-dessous.

### Relecture de DEC-0002 sur le fond pedagogique, a la demande de camille-po

Je valide la distinction des deux registres, et je confirme le choix technique retenu pour le
signal de S0-5 : pour un enfant qui ne dechiffre pas, le signal de reussite est appris par
repetition, donc il doit avoir exactement la meme apparence a chaque fois et sur tous les
appareils - ce qu'un caractere emoji ne garantit pas, en plus de ne pas permettre de maitriser sa
taille ni son contraste. Sur le fond, une regle de style d'ingenierie n'a effectivement rien a
dire sur la qualite d'un support pedagogique.

Mais `DEC-0002` a un manque, et ce sprint en donne la preuve. La decision **exempte** la
categorie B (le contenu vu par l'enfant) de la regle de redaction du depot, sans y mettre aucune
exigence positive a la place. Elle retire donc ce texte du controle du `relecteur-pedagogique` et
le confie a ma seule porte, qui ne s'exerce qu'une fois, en fin de sprint. Resultat observable :
l'habitude ASCII du depot a fuite dans le registre de l'enfant precisement la ou plus aucune
regle ne s'applique et ou plus personne ne regarde, et le retour faux affiche "reponse" et "etait"
a un enfant qui apprend a lire. Ce que je demande a `camille-po`, via `sofia-sm` : ajouter a
`DEC-0002` une exigence positive pour la categorie B, du type "le texte destine a l'enfant est
redige en francais correct et integralement accentue, orthographe et ponctuation irreprochables,
parce qu'il est lu par un enfant en cours d'apprentissage de la lecture", et faire verifier cette
exigence-la par le `relecteur-pedagogique` comme n'importe quelle autre. L'exemption de la regle
de style ne doit pas se lire comme une absence de regle. La meme precision devrait figurer dans
le complement de `CLAUDE.md` que `DEC-0002` signale deja.

Deuxieme point, sur les deux ambiguites que `DEC-0002` signale par avance dans
`contenu-repris/associer/cp-formes.yml`, pour que la future fiche ne corrige pas ce qui est bon :
les **deux triangles d'orientation differente associes au meme mot "triangle" ne sont pas un
defaut, c'est une qualite**. La competence de CP est "reconnaitre et nommer quelques figures
planes" : elle demande justement que l'enfant abstraie l'orientation, la taille et la couleur.
Ne montrer qu'un triangle toujours pointe vers le haut installerait un prototype faux, erreur
classique dont les eleves se defont ensuite difficilement. Il faut donc garder les deux
orientations. Le **carre bleu** est neutre a une seule condition, que je verifierai ce jour-la :
qu'aucun item du meme lot ne fasse jamais de la couleur le critere de tri, et que la consigne ne
parle jamais de couleur. Si ces deux conditions tiennent, la couleur est un simple attribut de
dessin et l'enfant ne peut pas se tromper de critere.
