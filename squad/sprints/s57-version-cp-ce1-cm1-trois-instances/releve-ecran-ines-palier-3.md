# s57 - palier 3 : releve a l'ecran du jar de `round-two` (pour `nadia-prof`)

- **Auteure** : `ines-design`. **Date** : 2026-10-21, 13:35 (UTC+02:00), lue par `date` (releve de 13:24 a 13:33).
- **Ce que ce fichier est** : ce que le jar de `round-two` affiche, releve dans le DOM (texte exact) et en captures PNG, aux points que `nadia-prof` ne peut pas jouer elle-meme (`veto-nadia-gabarit-pli.md` V5, `lecture-nadia-palier-3.md` S1 a S5). Il ne prononce aucun Done, ne juge aucun contenu et ne decide rien : le veto est a `nadia-prof`. Il cite PL-11 (regle de base du correctif du pli : en-tete 66 px, interligne 1,25) et PL-13 (restes sous le pli), rien d'autre.
- **Arbre** : `round-two` a `8407f73fea35ab9ff401f01f533f3056035caf33` (la porte technique est etablie au sommet `ad9ef86d`, le sommet actuel n'ajoute que des fichiers de `squad/`), worktree detache dedie `recreo-ines-r1`, branche `s57-ines-r1`. Les deux regles de `5302af53` y sont (`line-height: 1.25`, en-tete `0.5rem`), `edf7f344` n'y est pas.
- **Jar** : `bash outils/mvn-jdk21.sh -B clean package -DskipTests` (sortie 0), copie hors depot, SHA-256 `a7cc412ca57951b25c4c2bb5f81d6a420ade690c6e1a4026614bec78b33232c4`. Feuille servie `/css/recreo.css` : SHA-256 `1fa775cadc55f9b7a2252312340aae077c36218b019cdbf9ad2ebc11dc3654b5` (identique a celle de la branche `s57-ines-p3-b383`). Port 18711, PID 33452, arrete par ce PID.
- **Methode** : Chrome sans tete commande par le protocole devtools (pilote `releve.mjs`, hors depot), un seul navigateur a mon nom (PID 41044, profil jetable, port 19711, disparu a la fin du pilote). Fenetre emulee de **744 x 551** avec la barre de defilement classique, et de **720 x 551 sans barre** (`scrollbar-width: none` injecte), facteur 1, `mobile: false`. Les parties sont jouees par le DOM (`htmx.ajax`, puis `click()` sur les boutons de proposition) depuis `/niveaux/CE1`. Chaque item est identifie par son enonce, son texte-support et ses propositions, puis joue : arrivee, premiere proposition fausse servie, seconde proposition fausse, appui sur une proposition deja essayee (revelation), bonne reponse. **110 items sur 110, 0 anomalie** (`-07` 4 parties, `-06` 3, EMC 7, `-11` 10, `MAT-CE1-01` 8).
- **Captures** : 133 PNG, hors depot, dans le **dossier de captures** `r1-releve/`, defini ici une fois (dossier de travail de la session, hors depot ; amendement du 2026-10-21 en fin de fichier). Partout ailleurs une capture est citee par son seul nom de fichier, a lire dans ce dossier. Nom : `<paquet>-i<NN>-<etat>-<744 ou 720s>.png` (`07`, `06`, `11`, `emc`, `mat` ; `720s` = 720 x 551 sans barre). Elles se lisent avec l'outil de lecture d'image.
- **Ordre des propositions** : le serveur le tire au hasard. Chaque capture montre l'ordre servi ce jour-la, un seul par etat, sauf `-06` 14 que je montre dans ses deux dispositions (section B).
- **Etats** : *arrivee* = l'ecran d'une manche neuve (apres une bonne reponse, il porte encore le "Bravo !" de la manche precedente, sauf a la premiere manche) ; *erreur* = apres un appui sur la premiere proposition fausse servie ; *revelation* = apres une seconde erreur puis un appui sur une proposition deja essayee.

## A. `-07` `ce1-texte-documentaire` : l'etat a texte masque, 20 items

**Ce que "texte masque" veut dire ici.** Le produit ne sait pas masquer le texte-support : aucun etat, aucun gabarit, aucune regle ne le fait (le mot ne figure que dans un commentaire du YAML du paquet et dans ses tests). L'etat masque est **construit par moi** pour la verification que `nadia-prof` decrit (un enfant qui sait deja repondrait sans lire) : je pose, dans la page ouverte, une feuille de style qui rend `.texte-support` en `display: none`, rien d'autre. Tout le reste du DOM, la feuille servie et l'ordre des propositions sont ceux du jar. Ce n'est pas une capture du produit tel qu'un enfant le recoit.

Etats releves par item, en DOM texte (`innerText` de la zone de jeu, ce que l'oeil lit) : **V** texte visible a l'arrivee ; **M** texte masque a l'arrivee (celui ou l'enfant repond sans texte) ; **E** apres une erreur, texte visible ; **EM** apres une erreur, texte masque ; **R** apres la revelation, texte visible. Une capture PNG de **M** a 744 x 551 (barre classique) et a 720 x 551 sans barre. Aux manches 2 et suivantes, l'ecran d'arrivee porte encore le verdict de la reponse precedente ("Bravo !") : c'est l'etat reel et il figure dans les captures.

Constantes sur les 20 items : verdict apres une erreur "Ce n'est pas ça. Essaie encore !" ; a la revelation, verdict "Ce n'est pas ça.", puis "Appuie sur la bonne réponse :", puis la reponse attendue, puis l'indice ; aucune explication rendue ; l'indice rendu est egal a celui du YAML pour les 20 items ; en etat masque (EM) l'indice est rendu **tel quel** (voir la colonne "indice").

| Item | Enonce (etat M, texte exact) | Propositions (etat M, ordre servi) | Texte-support (etat V, absent en M) | Indice apres une erreur (E et EM) | Reponse attendue (R) | Capture M, 744 x 551 | Capture M, 720 x 551 sans barre |
|---|---|---|---|---|---|---|---|
| 1 | "Que mange le lapin ?" | "de la viande" ; "des carottes" ; "de l'herbe" | "Le renard mange de la viande. Le lapin mange de l'herbe. Le hibou chasse la nuit." | "Cherche le mot « lapin » dans le texte." | "de l'herbe" | `07-i01-masque-744.png` | `07-i01-masque-720s.png` |
| 2 | "Où pousse la fraise ?" | "près du sol" ; "sous la terre" ; "sur un pommier" | "La pomme pousse sur un pommier. La carotte pousse sous la terre. La fraise pousse près du sol." | "Cherche le mot « fraise » dans le texte." | "près du sol" | `07-i02-masque-744.png` | `07-i02-masque-720s.png` |
| 3 | "Où le crocodile pond-il ses œufs ?" | "dans un arbre" ; "dans la rivière" ; "dans le sable" | "Le crocodile nage dans la rivière. Il a de grandes dents. Il pond ses œufs dans le sable." | "Cherche le mot « pond » dans le texte." | "dans le sable" | `07-i03-masque-744.png` | `07-i03-masque-720s.png` |
| 4 | "Autour de quoi tourne la Lune ?" | "du Soleil" ; "de la Terre" ; "d'un nuage" | "La Lune tourne autour de la Terre. La Terre tourne autour du Soleil. Le Soleil est une étoile." | "Relis la première phrase." | "de la Terre" | `07-i04-masque-744.png` | `07-i04-masque-720s.png` |
| 5 | "Avec quoi fabrique-t-on le papier ?" | "du bois" ; "du sable" ; "de la laine" | "Le verre se fabrique avec du sable. Le papier se fabrique avec du bois. Le pull se fabrique avec de la laine." | "Cherche le mot « papier » dans le texte." | "du bois" | `07-i05-masque-744.png` | `07-i05-masque-720s.png` |
| 6 | "Comment les poissons respirent-ils ?" | "avec des branchies" ; "avec des poumons" ; "à la surface" | "Les baleines remontent à la surface pour respirer. Les poissons respirent sous l'eau avec des branchies." | "Cherche le mot « poissons » dans le texte." | "avec des branchies" | `07-i06-masque-744.png` | `07-i06-masque-720s.png` |
| 7 | "Que boit le papillon ?" | "du nectar" ; "du miel" ; "des feuilles" | "La chenille mange des feuilles. Le papillon boit du nectar sur les fleurs." | "Cherche le mot « papillon » dans le texte." | "du nectar" | `07-i07-masque-744.png` | `07-i07-masque-720s.png` |
| 8 | "Où le soleil se couche-t-il ?" | "à l'est" ; "à l'ouest" ; "au nord" | "Le soleil se lève à l'est. À midi, il est très haut dans le ciel. Il se couche à l'ouest." | "Relis la dernière phrase." | "à l'ouest" | `07-i08-masque-744.png` | `07-i08-masque-720s.png` |
| 9 | "Que fait la chauve-souris le jour ?" | "elle reste cachée" ; "elle sort" ; "elle mange des insectes" | "La chauve-souris dort la tête en bas. Le jour, elle reste cachée. Elle sort la nuit et mange des insectes." | "Cherche les mots « le jour » dans le texte." | "elle reste cachée" | `07-i09-masque-744.png` | `07-i09-masque-720s.png` |
| 10 | "À quelle température l'eau gèle-t-elle ?" | "à zéro degré" ; "à cent degrés" ; "à dix degrés" | "L'eau gèle à zéro degré. Elle bout à cent degrés. Quand il fait chaud, la glace fond." | "Relis la première phrase." | "à zéro degré" | `07-i10-masque-744.png` | `07-i10-masque-720s.png` |
| 11 | "Que mange le koala ?" | "de la viande" ; "des feuilles d'eucalyptus" ; "du bambou" | "Le koala vit en Australie. Le koala mange des feuilles d'eucalyptus. Le panda vit en Chine et mange du bambou." | "Cherche le mot « koala » dans le texte." | "des feuilles d'eucalyptus" | `07-i11-masque-744.png` | `07-i11-masque-720s.png` |
| 12 | "Que dessine-t-on sur le pli ?" | "un cœur" ; "une feuille" ; "un rond" | "Prends une feuille. Plie-la en deux. Dessine un cœur sur le pli. Découpe-le." | "Cherche le mot « pli » dans la consigne." | "un cœur" | `07-i12-masque-744.png` | `07-i12-masque-720s.png` |
| 13 | "Comment rince-t-on les mains ?" | "avec du savon" ; "à l'eau chaude" ; "à l'eau claire" | "Lave-toi les mains avec du savon. Rince-les à l'eau claire. Sèche-les avec une serviette." | "Relis la deuxième instruction." | "à l'eau claire" | `07-i13-masque-744.png` | `07-i13-masque-720s.png` |
| 14 | "Combien d'eau faut-il mettre ?" | "un peu" ; "beaucoup" ; "un pot" | "Pour planter une graine, remplis un pot de terre. Pose la graine dessus. Arrose un peu." | "Cherche le mot « arrose » dans la consigne." | "un peu" | `07-i14-masque-744.png` | `07-i14-masque-720s.png` |
| 15 | "De quelle couleur faut-il colorier le rond ?" | "en vert" ; "en rouge" ; "en bleu" | "Trace un trait en rouge. Colorie le rond en bleu. Écris ton prénom en haut." | "Cherche le mot « rond » dans la consigne." | "en bleu" | `07-i15-masque-744.png` | `07-i15-masque-720s.png` |
| 16 | "Que faut-il ranger ?" | "la trousse" ; "la chaise" ; "le cahier" | "Range le cahier. Ferme la trousse. Mets la chaise sous la table." | "Cherche le mot « range » dans la consigne." | "le cahier" | `07-i16-masque-744.png` | `07-i16-masque-720s.png` |
| 17 | "Que faut-il faire en dernier ?" | "Ajoute du sucre" ; "Mélange bien" ; "Casse deux œufs" | "Casse deux œufs dans un bol. Ajoute du sucre. Mélange bien." | "Les instructions se lisent dans l'ordre." | "Mélange bien" | `07-i17-masque-744.png` | `07-i17-masque-720s.png` |
| 18 | "Avec quoi trace-t-on le rond ?" | "un crayon" ; "un feutre" ; "des ciseaux" | "Trace un rond avec un crayon. Découpe-le avec des ciseaux. Colle-le sur une feuille." | "Cherche le mot « trace » dans la consigne." | "un crayon" | `07-i18-masque-744.png` | `07-i18-masque-720s.png` |
| 19 | "Où faut-il s'arrêter ?" | "à gauche" ; "jusqu'à la grille" ; "devant la porte rouge" | "Marche jusqu'à la grille. Tourne à gauche. Arrête-toi devant la porte rouge." | "Cherche le mot « arrête-toi » dans la consigne." | "devant la porte rouge" | `07-i19-masque-744.png` | `07-i19-masque-720s.png` |
| 20 | "Que met-on dans le verre en premier ?" | "de l'eau" ; "une cuillère" ; "du sirop" | "Mets de l'eau dans un verre. Ajoute du sirop. Mélange avec une cuillère." | "Relis la première instruction." | "de l'eau" | `07-i20-masque-744.png` | `07-i20-masque-720s.png` |

Verifications faites sur le DOM de chaque item : en M et en EM, `.texte-support` n'est pas rendu (`display: none`) et `innerText` ne contient pas son texte ; l'enonce et les propositions de M sont **egaux mot pour mot** a ceux de V (je ne les ai pas reecrits). Ce que l'enfant lit en M, par item, dans l'ordre de l'ecran :

- **Item 1**, etat M : "Score : 5 / Lis le petit texte, puis réponds à la question. / Que mange le lapin ? / de la viande des carottes de l'herbe / Bravo !"
  - etat EM : "Score : 5 / Lis le petit texte, puis réponds à la question. / Que mange le lapin ? / de la viande des carottes de l'herbe / Ce n'est pas ça. Essaie encore ! / Cherche le mot « lapin » dans le texte."
  - etat R : "Score : 5 / Lis le petit texte, puis réponds à la question. / Le renard mange de la viande. Le lapin mange de l'herbe. Le hibou chasse la nuit. / Que mange le lapin ? / de la viande des carottes de l'herbe / Ce n'est pas ça. / Appuie sur la bonne réponse : / de l'herbe / Cherche le mot « lapin » dans le texte."
- **Item 2**, etat M : "Score : 0 / Lis le petit texte, puis réponds à la question. / Où pousse la fraise ? / près du sol sous la terre sur un pommier"
  - etat EM : "Score : 0 / Lis le petit texte, puis réponds à la question. / Où pousse la fraise ? / près du sol sous la terre sur un pommier / Ce n'est pas ça. Essaie encore ! / Cherche le mot « fraise » dans le texte."
  - etat R : "Score : 0 / Lis le petit texte, puis réponds à la question. / La pomme pousse sur un pommier. La carotte pousse sous la terre. La fraise pousse près du sol. / Où pousse la fraise ? / près du sol sous la terre sur un pommier / Ce n'est pas ça. / Appuie sur la bonne réponse : / près du sol / Cherche le mot « fraise » dans le texte."
- **Item 3**, etat M : "Score : 0 / Lis le petit texte, puis réponds à la question. / Où le crocodile pond-il ses œufs ? / dans un arbre dans la rivière dans le sable"
  - etat EM : "Score : 0 / Lis le petit texte, puis réponds à la question. / Où le crocodile pond-il ses œufs ? / dans un arbre dans la rivière dans le sable / Ce n'est pas ça. Essaie encore ! / Cherche le mot « pond » dans le texte."
  - etat R : "Score : 0 / Lis le petit texte, puis réponds à la question. / Le crocodile nage dans la rivière. Il a de grandes dents. Il pond ses œufs dans le sable. / Où le crocodile pond-il ses œufs ? / dans un arbre dans la rivière dans le sable / Ce n'est pas ça. / Appuie sur la bonne réponse : / dans le sable / Cherche le mot « pond » dans le texte."
- **Item 4**, etat M : "Score : 6 / Lis le petit texte, puis réponds à la question. / Autour de quoi tourne la Lune ? / du Soleil de la Terre d'un nuage / Bravo !"
  - etat EM : "Score : 6 / Lis le petit texte, puis réponds à la question. / Autour de quoi tourne la Lune ? / du Soleil de la Terre d'un nuage / Ce n'est pas ça. Essaie encore ! / Relis la première phrase."
  - etat R : "Score : 6 / Lis le petit texte, puis réponds à la question. / La Lune tourne autour de la Terre. La Terre tourne autour du Soleil. Le Soleil est une étoile. / Autour de quoi tourne la Lune ? / du Soleil de la Terre d'un nuage / Ce n'est pas ça. / Appuie sur la bonne réponse : / de la Terre / Relis la première phrase."
- **Item 5**, etat M : "Score : 1 / Lis le petit texte, puis réponds à la question. / Avec quoi fabrique-t-on le papier ? / du bois du sable de la laine"
  - etat EM : "Score : 1 / Lis le petit texte, puis réponds à la question. / Avec quoi fabrique-t-on le papier ? / du bois du sable de la laine / Ce n'est pas ça. Essaie encore ! / Cherche le mot « papier » dans le texte."
  - etat R : "Score : 1 / Lis le petit texte, puis réponds à la question. / Le verre se fabrique avec du sable. Le papier se fabrique avec du bois. Le pull se fabrique avec de la laine. / Avec quoi fabrique-t-on le papier ? / du bois du sable de la laine / Ce n'est pas ça. / Appuie sur la bonne réponse : / du bois / Cherche le mot « papier » dans le texte."
- **Item 6**, etat M : "Score : 0 / Lis le petit texte, puis réponds à la question. / Comment les poissons respirent-ils ? / avec des branchies avec des poumons à la surface"
  - etat EM : "Score : 0 / Lis le petit texte, puis réponds à la question. / Comment les poissons respirent-ils ? / avec des branchies avec des poumons à la surface / Ce n'est pas ça. Essaie encore ! / Cherche le mot « poissons » dans le texte."
  - etat R : "Score : 0 / Lis le petit texte, puis réponds à la question. / Les baleines remontent à la surface pour respirer. Les poissons respirent sous l'eau avec des branchies. / Comment les poissons respirent-ils ? / avec des branchies avec des poumons à la surface / Ce n'est pas ça. / Appuie sur la bonne réponse : / avec des branchies / Cherche le mot « poissons » dans le texte."
- **Item 7**, etat M : "Score : 3 / Lis le petit texte, puis réponds à la question. / Que boit le papillon ? / du nectar du miel des feuilles / Bravo !"
  - etat EM : "Score : 3 / Lis le petit texte, puis réponds à la question. / Que boit le papillon ? / du nectar du miel des feuilles / Ce n'est pas ça. Essaie encore ! / Cherche le mot « papillon » dans le texte."
  - etat R : "Score : 3 / Lis le petit texte, puis réponds à la question. / La chenille mange des feuilles. Le papillon boit du nectar sur les fleurs. / Que boit le papillon ? / du nectar du miel des feuilles / Ce n'est pas ça. / Appuie sur la bonne réponse : / du nectar / Cherche le mot « papillon » dans le texte."
- **Item 8**, etat M : "Score : 0 / Lis le petit texte, puis réponds à la question. / Où le soleil se couche-t-il ? / à l'est à l'ouest au nord"
  - etat EM : "Score : 0 / Lis le petit texte, puis réponds à la question. / Où le soleil se couche-t-il ? / à l'est à l'ouest au nord / Ce n'est pas ça. Essaie encore ! / Relis la dernière phrase."
  - etat R : "Score : 0 / Lis le petit texte, puis réponds à la question. / Le soleil se lève à l'est. À midi, il est très haut dans le ciel. Il se couche à l'ouest. / Où le soleil se couche-t-il ? / à l'est à l'ouest au nord / Ce n'est pas ça. / Appuie sur la bonne réponse : / à l'ouest / Relis la dernière phrase."
- **Item 9**, etat M : "Score : 0 / Lis le petit texte, puis réponds à la question. / Que fait la chauve-souris le jour ? / elle reste cachée elle sort elle mange des insectes"
  - etat EM : "Score : 0 / Lis le petit texte, puis réponds à la question. / Que fait la chauve-souris le jour ? / elle reste cachée elle sort elle mange des insectes / Ce n'est pas ça. Essaie encore ! / Cherche les mots « le jour » dans le texte."
  - etat R : "Score : 0 / Lis le petit texte, puis réponds à la question. / La chauve-souris dort la tête en bas. Le jour, elle reste cachée. Elle sort la nuit et mange des insectes. / Que fait la chauve-souris le jour ? / elle reste cachée elle sort elle mange des insectes / Ce n'est pas ça. / Appuie sur la bonne réponse : / elle reste cachée / Cherche les mots « le jour » dans le texte."
- **Item 10**, etat M : "Score : 0 / Lis le petit texte, puis réponds à la question. / À quelle température l'eau gèle-t-elle ? / à zéro degré à cent degrés à dix degrés"
  - etat EM : "Score : 0 / Lis le petit texte, puis réponds à la question. / À quelle température l'eau gèle-t-elle ? / à zéro degré à cent degrés à dix degrés / Ce n'est pas ça. Essaie encore ! / Relis la première phrase."
  - etat R : "Score : 0 / Lis le petit texte, puis réponds à la question. / L'eau gèle à zéro degré. Elle bout à cent degrés. Quand il fait chaud, la glace fond. / À quelle température l'eau gèle-t-elle ? / à zéro degré à cent degrés à dix degrés / Ce n'est pas ça. / Appuie sur la bonne réponse : / à zéro degré / Relis la première phrase."
- **Item 11**, etat M : "Score : 0 / Lis le petit texte, puis réponds à la question. / Que mange le koala ? / de la viande des feuilles d'eucalyptus du bambou"
  - etat EM : "Score : 0 / Lis le petit texte, puis réponds à la question. / Que mange le koala ? / de la viande des feuilles d'eucalyptus du bambou / Ce n'est pas ça. Essaie encore ! / Cherche le mot « koala » dans le texte."
  - etat R : "Score : 0 / Lis le petit texte, puis réponds à la question. / Le koala vit en Australie. Le koala mange des feuilles d'eucalyptus. Le panda vit en Chine et mange du bambou. / Que mange le koala ? / de la viande des feuilles d'eucalyptus du bambou / Ce n'est pas ça. / Appuie sur la bonne réponse : / des feuilles d'eucalyptus / Cherche le mot « koala » dans le texte."
- **Item 12**, etat M : "Score : 0 / Lis le petit texte, puis réponds à la question. / Que dessine-t-on sur le pli ? / un cœur une feuille un rond"
  - etat EM : "Score : 0 / Lis le petit texte, puis réponds à la question. / Que dessine-t-on sur le pli ? / un cœur une feuille un rond / Ce n'est pas ça. Essaie encore ! / Cherche le mot « pli » dans la consigne."
  - etat R : "Score : 0 / Lis le petit texte, puis réponds à la question. / Prends une feuille. Plie-la en deux. Dessine un cœur sur le pli. Découpe-le. / Que dessine-t-on sur le pli ? / un cœur une feuille un rond / Ce n'est pas ça. / Appuie sur la bonne réponse : / un cœur / Cherche le mot « pli » dans la consigne."
- **Item 13**, etat M : "Score : 0 / Lis le petit texte, puis réponds à la question. / Comment rince-t-on les mains ? / avec du savon à l'eau chaude à l'eau claire"
  - etat EM : "Score : 0 / Lis le petit texte, puis réponds à la question. / Comment rince-t-on les mains ? / avec du savon à l'eau chaude à l'eau claire / Ce n'est pas ça. Essaie encore ! / Relis la deuxième instruction."
  - etat R : "Score : 0 / Lis le petit texte, puis réponds à la question. / Lave-toi les mains avec du savon. Rince-les à l'eau claire. Sèche-les avec une serviette. / Comment rince-t-on les mains ? / avec du savon à l'eau chaude à l'eau claire / Ce n'est pas ça. / Appuie sur la bonne réponse : / à l'eau claire / Relis la deuxième instruction."
- **Item 14**, etat M : "Score : 0 / Lis le petit texte, puis réponds à la question. / Combien d'eau faut-il mettre ? / un peu beaucoup un pot"
  - etat EM : "Score : 0 / Lis le petit texte, puis réponds à la question. / Combien d'eau faut-il mettre ? / un peu beaucoup un pot / Ce n'est pas ça. Essaie encore ! / Cherche le mot « arrose » dans la consigne."
  - etat R : "Score : 0 / Lis le petit texte, puis réponds à la question. / Pour planter une graine, remplis un pot de terre. Pose la graine dessus. Arrose un peu. / Combien d'eau faut-il mettre ? / un peu beaucoup un pot / Ce n'est pas ça. / Appuie sur la bonne réponse : / un peu / Cherche le mot « arrose » dans la consigne."
- **Item 15**, etat M : "Score : 3 / Lis le petit texte, puis réponds à la question. / De quelle couleur faut-il colorier le rond ? / en vert en rouge en bleu"
  - etat EM : "Score : 3 / Lis le petit texte, puis réponds à la question. / De quelle couleur faut-il colorier le rond ? / en vert en rouge en bleu / Ce n'est pas ça. Essaie encore ! / Cherche le mot « rond » dans la consigne."
  - etat R : "Score : 3 / Lis le petit texte, puis réponds à la question. / Trace un trait en rouge. Colorie le rond en bleu. Écris ton prénom en haut. / De quelle couleur faut-il colorier le rond ? / en vert en rouge en bleu / Ce n'est pas ça. / Appuie sur la bonne réponse : / en bleu / Cherche le mot « rond » dans la consigne."
- **Item 16**, etat M : "Score : 1 / Lis le petit texte, puis réponds à la question. / Que faut-il ranger ? / la trousse la chaise le cahier / Bravo !"
  - etat EM : "Score : 1 / Lis le petit texte, puis réponds à la question. / Que faut-il ranger ? / la trousse la chaise le cahier / Ce n'est pas ça. Essaie encore ! / Cherche le mot « range » dans la consigne."
  - etat R : "Score : 1 / Lis le petit texte, puis réponds à la question. / Range le cahier. Ferme la trousse. Mets la chaise sous la table. / Que faut-il ranger ? / la trousse la chaise le cahier / Ce n'est pas ça. / Appuie sur la bonne réponse : / le cahier / Cherche le mot « range » dans la consigne."
- **Item 17**, etat M : "Score : 0 / Lis le petit texte, puis réponds à la question. / Que faut-il faire en dernier ? / Ajoute du sucre Mélange bien Casse deux œufs"
  - etat EM : "Score : 0 / Lis le petit texte, puis réponds à la question. / Que faut-il faire en dernier ? / Ajoute du sucre Mélange bien Casse deux œufs / Ce n'est pas ça. Essaie encore ! / Les instructions se lisent dans l'ordre."
  - etat R : "Score : 0 / Lis le petit texte, puis réponds à la question. / Casse deux œufs dans un bol. Ajoute du sucre. Mélange bien. / Que faut-il faire en dernier ? / Ajoute du sucre Mélange bien Casse deux œufs / Ce n'est pas ça. / Appuie sur la bonne réponse : / Mélange bien / Les instructions se lisent dans l'ordre."
- **Item 18**, etat M : "Score : 3 / Lis le petit texte, puis réponds à la question. / Avec quoi trace-t-on le rond ? / un crayon un feutre des ciseaux / Bravo !"
  - etat EM : "Score : 3 / Lis le petit texte, puis réponds à la question. / Avec quoi trace-t-on le rond ? / un crayon un feutre des ciseaux / Ce n'est pas ça. Essaie encore ! / Cherche le mot « trace » dans la consigne."
  - etat R : "Score : 3 / Lis le petit texte, puis réponds à la question. / Trace un rond avec un crayon. Découpe-le avec des ciseaux. Colle-le sur une feuille. / Avec quoi trace-t-on le rond ? / un crayon un feutre des ciseaux / Ce n'est pas ça. / Appuie sur la bonne réponse : / un crayon / Cherche le mot « trace » dans la consigne."
- **Item 19**, etat M : "Score : 0 / Lis le petit texte, puis réponds à la question. / Où faut-il s'arrêter ? / à gauche jusqu'à la grille devant la porte rouge"
  - etat EM : "Score : 0 / Lis le petit texte, puis réponds à la question. / Où faut-il s'arrêter ? / à gauche jusqu'à la grille devant la porte rouge / Ce n'est pas ça. Essaie encore ! / Cherche le mot « arrête-toi » dans la consigne."
  - etat R : "Score : 0 / Lis le petit texte, puis réponds à la question. / Marche jusqu'à la grille. Tourne à gauche. Arrête-toi devant la porte rouge. / Où faut-il s'arrêter ? / à gauche jusqu'à la grille devant la porte rouge / Ce n'est pas ça. / Appuie sur la bonne réponse : / devant la porte rouge / Cherche le mot « arrête-toi » dans la consigne."
- **Item 20**, etat M : "Score : 2 / Lis le petit texte, puis réponds à la question. / Que met-on dans le verre en premier ? / de l'eau une cuillère du sirop / Bravo !"
  - etat EM : "Score : 2 / Lis le petit texte, puis réponds à la question. / Que met-on dans le verre en premier ? / de l'eau une cuillère du sirop / Ce n'est pas ça. Essaie encore ! / Relis la première instruction."
  - etat R : "Score : 2 / Lis le petit texte, puis réponds à la question. / Mets de l'eau dans un verre. Ajoute du sirop. Mélange avec une cuillère. / Que met-on dans le verre en premier ? / de l'eau une cuillère du sirop / Ce n'est pas ça. / Appuie sur la bonne réponse : / de l'eau / Relis la première instruction."

Lecture de l'etat EM, sans jugement : l'indice de **8 items sur 20** renvoie au texte ("dans le texte", "le texte dit") alors que le texte est masque.

## B. `-06`, `-11`, EMC, `MAT-CE1-01` : item par item, en DOM texte

Colonnes : l'enonce, le texte-support (`-06` seulement), les propositions dans l'ordre ou le serveur les a servies a l'arrivee, l'indice rendu apres une erreur, la reponse attendue rendue a la revelation. Le verdict apres une erreur, la phrase de revelation et l'absence d'explication sont constants dans les quatre paquets (plus bas). Les espaces insecables sont rendues par des espaces simples dans ces tables ; leurs code points sont en E.

### FRA-CE1-06, ce1-inference, 20 items

Constantes : verdict apres une erreur "Ce n'est pas ça. Essaie encore !" ; verdict a la revelation "Ce n'est pas ça." ; phrase "Appuie sur la bonne réponse :" ; explication rendue : aucune ; indice rendu et reponse attendue egaux a ceux du YAML pour 20 items sur 20 : oui.

| Item | Enonce | Texte-support | Propositions (ordre servi) | Indice | Reponse attendue |
|---|---|---|---|---|---|
| 1 | "Comment se sent Malo ?" | "Malo regarde son ballon crevé. Il baisse la tête et ne dit rien." | "il est content" ; "il a peur" ; "il est triste" | "Regarde ce que fait le personnage." | "il est triste" |
| 2 | "Comment se sent Lina ?" | "Lina ouvre un grand paquet. C'est le jouet qu'elle voulait. Elle saute et applaudit." | "elle a peur" ; "elle est contente" ; "elle est triste" | "Pense à ce qu'elle voulait." | "elle est contente" |
| 3 | "Comment se sent Sami ?" | "Il fait nuit. Sami entend un grand bruit. Il serre sa couverture et ferme les yeux." | "il est en colère" ; "il a peur" ; "il est content" | "Relis la dernière phrase et pense à ses gestes." | "il a peur" |
| 4 | "Comment se sent Hugo ?" | "Hugo a fini sa tour de cubes. Louna passe et la renverse. Hugo tape du pied et crie fort." | "il est content" ; "il est en colère" ; "il a peur" | "Regarde ce que fait le personnage." | "il est en colère" |
| 5 | "Comment se sent Paul ?" | "Après le match, Paul tient une grande coupe. Tout le monde l'applaudit. Il sourit de toutes ses dents." | "il est content" ; "il est triste" ; "il a peur" | "Relis la dernière phrase et pense à ses gestes." | "il est content" |
| 6 | "Comment se sent Mila ?" | "Mila a dessiné toute la matinée. La pluie tombe sur sa feuille. Les couleurs coulent, et elle pleure." | "elle est en colère" ; "elle est contente" ; "elle est triste" | "Pense à ce qui arrive à son dessin." | "elle est triste" |
| 7 | "Comment se sent Karim ?" | "Karim est au bord de la piscine. Il regarde l'eau très profonde. Il recule de deux pas." | "il est content" ; "il est triste" ; "il a peur" | "Regarde ce que fait le personnage." | "il a peur" |
| 8 | "Où est Nina maintenant ?" | "Nina quitte l'école avec son sac. Elle met son maillot et nage dans un grand bassin. L'eau est chaude." | "à l'école" ; "à la piscine" ; "à la plage" | "Les objets du texte te disent où l'on est." | "à la piscine" |
| 9 | "Où est Adrien ?" | "Adrien regarde un grand lion derrière une grille. Un singe grimpe à un arbre. Des gens prennent des photos." | "au zoo" ; "à la ferme" ; "dans un arbre" | "Cherche les indices : qu'y voit-on ?" | "au zoo" |
| 10 | "Où est Emma ?" | "Emma sent une bonne odeur de pain chaud. Elle donne deux euros. La dame lui tend une baguette." | "au cinéma" ; "à la boulangerie" ; "à la poste" | "Les objets du texte te disent où l'on est." | "à la boulangerie" |
| 11 | "Où est Samir ?" | "Samir parle tout bas. Il cherche un livre sur une grande étagère. On lui demande de ne pas faire de bruit." | "au stade" ; "à la cantine" ; "à la bibliothèque" | "Cherche les indices : qu'y fait-on ?" | "à la bibliothèque" |
| 12 | "Où sont Eva et Théo ?" | "Eva et Théo s'assoient dans le noir. Un grand écran s'allume devant eux. Ils mangent du maïs soufflé." | "au restaurant" ; "à l'école" ; "au cinéma" | "Cherche les indices : qu'y voit-on ?" | "au cinéma" |
| 13 | "Où est Nolan ?" | "Nolan attend sur le quai. Un train arrive en sifflant. Une voix annonce un retard." | "à l'aéroport" ; "à la gare" ; "au supermarché" | "Les objets du texte te disent où l'on est." | "à la gare" |
| 14 | "Pourquoi Inès ouvre-t-elle son parapluie au-dessus de lui ?" | "Ce matin, Inès part à l'école avec son frère. La pluie se met à tomber. Inès ouvre son parapluie au-dessus de lui. Ils arrivent tout secs." | "parce qu'il fait nuit" ; "pour qu'il ne soit pas mouillé" ; "pour aller à l'école" | "Demande-toi ce qui se passe juste avant." | "pour qu'il ne soit pas mouillé" |
| 15 | "Pourquoi Tidiane met-il une écharpe ?" | "Tidiane met un gros pull et une écharpe. Il souffle dans ses mains avant de sortir." | "parce qu'il a chaud" ; "parce qu'il a froid" ; "parce qu'il a faim" | "Pense à ce qu'il fait avec ses mains." | "parce qu'il a froid" |
| 16 | "Pourquoi Sara ouvre-t-elle sa boîte ?" | "Sara n'a rien mangé depuis ce matin. Son ventre gargouille. Elle ouvre sa boîte à goûter." | "parce qu'elle a sommeil" ; "parce qu'elle a faim" ; "parce que c'est le matin" | "Relis ce qui arrive à son ventre." | "parce qu'elle a faim" |
| 17 | "Pourquoi Ilan éteint-il la lumière ?" | "Ilan bâille et se frotte les yeux. Il met son pyjama et éteint la lumière." | "parce qu'il a faim" ; "parce qu'il met son pyjama" ; "parce qu'il va dormir" | "Demande-toi ce qui se passe juste avant." | "parce qu'il va dormir" |
| 18 | "Pourquoi Sofia court-elle ?" | "Sofia court vers l'arrêt. Le bus démarre. Elle arrive trop tard." | "pour aller jouer" ; "pour trouver l'arrêt" ; "pour attraper le bus" | "Pense à ce que fait le bus." | "pour attraper le bus" |
| 19 | "Pourquoi Nour verse-t-elle de l'eau ?" | "Nour pose une graine dans la terre. Chaque jour, elle verse un peu d'eau. Une petite tige sort de terre." | "pour que la graine pousse" ; "pour poser une graine" ; "pour jouer avec l'eau" | "Relis ce qui sort de terre à la fin." | "pour que la graine pousse" |
| 20 | "Pourquoi Zoé montre-t-elle sa feuille ?" | "Zoé revient de l'école avec une grande feuille. Elle la montre à tout le monde, les yeux brillants. Elle a une étoile en haut de la page." | "parce qu'elle est fière" ; "parce qu'elle a peur" ; "parce qu'elle revient de l'école" | "Regarde ses yeux et ce qu'il y a sur la page." | "parce qu'elle est fière" |

### FRA-CE1-11, ce1-negation, 24 items

Constantes : verdict apres une erreur "Ce n'est pas ça. Essaie encore !" ; verdict a la revelation "Ce n'est pas ça." ; phrase "Appuie sur la bonne réponse :" ; explication rendue : aucune ; indice rendu et reponse attendue egaux a ceux du YAML pour 24 items sur 24 : oui.

| Item | Enonce | Propositions (ordre servi) | Indice | Reponse attendue |
|---|---|---|---|---|
| 1 | "« Léo aime les épinards. » Quelle est la phrase négative ?" | "Léo ne aime pas les épinards." ; "Léo n'aime pas les épinards." ; "Léo aime pas les épinards." | "Cherche les deux petits mots qui entourent le verbe." | "Léo n'aime pas les épinards." |
| 2 | "« Inès écoute la radio. » Quelle est la phrase négative ?" | "Inès écoute pas la radio." ; "Inès n'écoute pas la radio." ; "Inès n'écoute la radio pas." | "Cherche les deux petits mots qui entourent le verbe." | "Inès n'écoute pas la radio." |
| 3 | "« Rex garde la maison. » Quelle est la phrase négative ?" | "Rex ne garde pas la maison." ; "Rex ne garde la maison pas." ; "Rex garde pas la maison." | "Lis les trois phrases et compare où sont les petits mots." | "Rex ne garde pas la maison." |
| 4 | "« Zoé ne mange pas la soupe. » Quelle est la phrase affirmative ?" | "Zoé mange pas la soupe." ; "Zoé ne mange la soupe." ; "Zoé mange la soupe." | "Enlève les petits mots qui disent non." | "Zoé mange la soupe." |
| 5 | "« Le chat n'aime pas la pluie. » Quelle est la phrase affirmative ?" | "Le chat aime la pluie." ; "Le chat n'aime la pluie." ; "Le chat aime pas la pluie." | "Enlève les petits mots qui disent non." | "Le chat aime la pluie." |
| 6 | "« Tom ouvre la fenêtre. » Quelle est la phrase négative ?" | "Tom ne ouvre pas la fenêtre." ; "Tom ouvre pas la fenêtre." ; "Tom n'ouvre pas la fenêtre." | "Cherche les deux petits mots qui entourent le verbe." | "Tom n'ouvre pas la fenêtre." |
| 7 | "« Mia ferme la porte. » Quelle est la phrase négative ?" | "Mia ferme pas la porte." ; "Mia ne ferme pas la porte." ; "Mia ne ferme la porte pas." | "Cherche les deux petits mots qui entourent le verbe." | "Mia ne ferme pas la porte." |
| 8 | "« Karim habite ici. » Quelle est la phrase négative ?" | "Karim habite pas ici." ; "Karim n'habite pas ici." ; "Karim ne habite pas ici." | "Lis les trois phrases et compare où sont les petits mots." | "Karim n'habite pas ici." |
| 9 | "« Tom ne ferme pas la porte. » Quelle est la phrase affirmative ?" | "Tom ferme pas la porte." ; "Tom ne ferme la porte." ; "Tom ferme la porte." | "Enlève les petits mots qui disent non." | "Tom ferme la porte." |
| 10 | "« Inès n'ouvre pas la boîte. » Quelle est la phrase affirmative ?" | "Inès ouvre la boîte." ; "Inès ouvre pas la boîte." ; "Inès n'ouvre la boîte." | "Enlève les petits mots qui disent non." | "Inès ouvre la boîte." |
| 11 | "« Sofia attend le bus. » Quelle est la phrase négative ?" | "Sofia attend pas le bus." ; "Sofia n'attend pas le bus." ; "Sofia n'attend le bus pas." | "Cherche les deux petits mots qui entourent le verbe." | "Sofia n'attend pas le bus." |
| 12 | "« Le vent souffle fort. » Quelle est la phrase négative ?" | "Le vent souffle pas fort." ; "Le vent ne souffle fort pas." ; "Le vent ne souffle pas fort." | "Cherche les deux petits mots qui entourent le verbe." | "Le vent ne souffle pas fort." |
| 13 | "« Yanis range ses crayons. » Quelle est la phrase négative ?" | "Yanis ne range ses pas crayons." ; "Yanis ne range pas ses crayons." ; "Yanis range pas ses crayons." | "Lis les trois phrases et compare où sont les petits mots." | "Yanis ne range pas ses crayons." |
| 14 | "« Les oiseaux ne chantent pas. » Quelle est la phrase affirmative ?" | "Les oiseaux chantent." ; "Les oiseaux ne chantent." ; "Les oiseaux chantent pas." | "Enlève les petits mots qui disent non." | "Les oiseaux chantent." |
| 15 | "« Karim n'attend pas le train. » Quelle est la phrase affirmative ?" | "Karim n'attend le train." ; "Karim attend le train." ; "Karim attend pas le train." | "Enlève les petits mots qui disent non." | "Karim attend le train." |
| 16 | "« Les enfants chantent. » Quelle est la phrase négative ?" | "Les enfants chantent pas." ; "Les enfants ne chantent pas." ; "Les enfants ne pas chantent." | "Cherche les deux petits mots qui entourent le verbe." | "Les enfants ne chantent pas." |
| 17 | "« Elle utilise le crayon. » Quelle est la phrase négative ?" | "Elle ne utilise pas le crayon." ; "Elle utilise pas le crayon." ; "Elle n'utilise pas le crayon." | "Cherche les deux petits mots qui entourent le verbe." | "Elle n'utilise pas le crayon." |
| 18 | "« Léa oublie son cartable. » Quelle est la phrase négative ?" | "Léa n'oublie son cartable pas." ; "Léa n'oublie pas son cartable." ; "Léa oublie pas son cartable." | "Lis les trois phrases et compare où sont les petits mots." | "Léa n'oublie pas son cartable." |
| 19 | "« Mia ne dessine pas la maison. » Quelle est la phrase affirmative ?" | "Mia ne dessine la maison." ; "Mia dessine pas la maison." ; "Mia dessine la maison." | "Enlève les petits mots qui disent non." | "Mia dessine la maison." |
| 20 | "« Il n'écoute pas la maîtresse. » Quelle est la phrase affirmative ?" | "Il écoute pas la maîtresse." ; "Il n'écoute la maîtresse." ; "Il écoute la maîtresse." | "Enlève les petits mots qui disent non." | "Il écoute la maîtresse." |
| 21 | "« Le train arrive ici. » Quelle est la phrase négative ?" | "Le train ne arrive pas ici." ; "Le train n'arrive pas ici." ; "Le train arrive pas ici." | "Cherche les deux petits mots qui entourent le verbe." | "Le train n'arrive pas ici." |
| 22 | "« Noé aime la pluie. » Quelle est la phrase négative ?" | "Noé aime pas la pluie." ; "Noé n'aime pas la pluie." ; "Noé ne aime pas la pluie." | "Cherche les deux petits mots qui entourent le verbe." | "Noé n'aime pas la pluie." |
| 23 | "« Le vent ne souffle pas. » Quelle est la phrase affirmative ?" | "Le vent ne souffle." ; "Le vent souffle pas." ; "Le vent souffle." | "Enlève les petits mots qui disent non." | "Le vent souffle." |
| 24 | "« Noé n'habite pas ici. » Quelle est la phrase affirmative ?" | "Noé habite ici." ; "Noé habite pas ici." ; "Noé n'habite ici." | "Enlève les petits mots qui disent non." | "Noé habite ici." |

### EMC-CE1-01, ce1-emc-biens-communs, 24 items

Constantes : verdict apres une erreur "Ce n'est pas ça. Essaie encore !" ; verdict a la revelation "Ce n'est pas ça." ; phrase "Appuie sur la bonne réponse :" ; explication rendue : aucune ; indice rendu et reponse attendue egaux a ceux du YAML pour 24 items sur 24 : oui.

| Item | Enonce | Propositions (ordre servi) | Indice | Reponse attendue |
|---|---|---|---|---|
| 1 | "Quel objet est à tous les élèves de l'école ?" | "le manteau de Jade" ; "le ballon de la cour" ; "le cartable de Tom" | "Demande-toi : un seul élève s'en sert, ou tous ?" | "le ballon de la cour" |
| 2 | "Parmi ces objets, lequel peut servir à tout le monde ?" | "le livre de la bibliothèque" ; "le manteau d'Omar" ; "le cahier d'Inès" | "Regarde à qui appartient chaque objet." | "le livre de la bibliothèque" |
| 3 | "Un seul de ces objets est à tout le monde. Lequel ?" | "la trousse de Mila" ; "la règle de la classe" ; "la gomme de Samir" | "Pense à celui dont tous les élèves peuvent se servir." | "la règle de la classe" |
| 4 | "Quel objet peut être utilisé par n'importe quel élève ?" | "le jeu de la cour" ; "le sac de Léo" ; "le bonnet de Zoé" | "Lis chaque objet et dis qui s'en sert." | "le jeu de la cour" |
| 5 | "Quel objet est à tout le monde dans l'école ?" | "le stylo d'Aya" ; "le feutre de Noé" ; "le crayon de la classe" | "Compare les trois objets : qui peut les utiliser ?" | "le crayon de la classe" |
| 6 | "Hugo veut un objet que tous les élèves partagent. Que choisit-il ?" | "le chapeau d'Adam" ; "le goûter de Mila" ; "le jeu de la classe" | "Pense à ce qui ne reste pas dans le sac d'un élève." | "le jeu de la classe" |
| 7 | "Quel objet est à une seule personne ?" | "la trousse de Lina" ; "la corde de la cour" ; "la règle de la classe" | "Demande-toi : qui a le droit de s'en servir ?" | "la trousse de Lina" |
| 8 | "Un de ces objets est à un seul élève. Lequel ?" | "le cartable d'Omar" ; "le crayon de la classe" ; "le livre de la bibliothèque" | "Lis le nom de chaque objet avec attention." | "le cartable d'Omar" |
| 9 | "Parmi ces objets, lequel est à un seul élève ?" | "la colle de la classe" ; "la balle de la cour" ; "la gourde de Zoé" | "Pense à l'objet qu'un seul élève utilise." | "la gourde de Zoé" |
| 10 | "Quel objet n'est pas à tout le monde ?" | "le jeu de la cour" ; "le stylo de Samir" ; "le livre de la classe" | "Demande-toi quel objet ne sert qu'à une personne." | "le stylo de Samir" |
| 11 | "Maya et Noé regardent trois objets. Lequel est à une seule personne ?" | "le ballon de la cour" ; "le livre de la bibliothèque" ; "le cahier d'Inès" | "Compare ce qui sert à beaucoup d'élèves et à un seul." | "le cahier d'Inès" |
| 12 | "Quel objet est à un élève, et pas à toute l'école ?" | "le manteau de Léo" ; "le feutre de la classe" ; "le jeu de la cour" | "Cherche ce que les autres élèves ne peuvent pas utiliser." | "le manteau de Léo" |
| 13 | "Lina lit un livre de la bibliothèque. Que fait-elle pour ne pas l'abîmer ?" | "elle écrit son prénom dedans" ; "elle tourne les pages doucement" ; "elle plie un coin" | "Pense à ce qui garde les pages en bon état." | "elle tourne les pages doucement" |
| 14 | "Omar voit une page déchirée dans un livre de la bibliothèque. Que fait-il ?" | "il prévient un adulte" ; "il cache la page" ; "il le rend sans rien dire" | "Demande-toi : qui doit savoir que le livre est abîmé ?" | "il prévient un adulte" |
| 15 | "Un ballon de la cour est crevé. Que fait Jade ?" | "elle prévient un adulte" ; "elle le cache dans un coin" ; "elle le garde pour elle" | "Pense à qui peut décider quoi faire du ballon." | "elle prévient un adulte" |
| 16 | "Mila a fini d'utiliser la colle de la classe. Que fait-elle ?" | "elle laisse le tube ouvert" ; "elle referme le tube" ; "elle presse le tube très fort" | "Pense à ce qui arrive à la colle à l'air." | "elle referme le tube" |
| 17 | "Samir veut lire un livre de la bibliothèque. Que fait-il avant de l'ouvrir ?" | "il mange en lisant" ; "il l'ouvre avec des doigts collants" ; "il se lave les mains" | "Pense aux traces que laissent les doigts." | "il se lave les mains" |
| 18 | "Noé veut dessiner. Où dessine-t-il pour ne pas abîmer le matériel de la classe ?" | "sur la table" ; "sur le mur" ; "sur une feuille" | "Pense à ce qui est fait pour dessiner." | "sur une feuille" |
| 19 | "Aya a des craies de la cour. Où dessine-t-elle ?" | "sur la porte de la classe" ; "sur le mur de l'école" ; "sur le sol de la cour" | "Pense à l'endroit qu'on peut laver sans l'abîmer." | "sur le sol de la cour" |
| 20 | "Inès a un livre de la bibliothèque et il pleut. Que fait-elle ?" | "elle le laisse sous la pluie" ; "elle le pose sur un banc" ; "elle le garde au sec" | "Pense à ce que l'eau fait au papier." | "elle le garde au sec" |
| 21 | "Hugo joue avec un jeu de la classe. Que fait-il quand la partie est finie ?" | "il laisse tout sur la table" ; "il garde une pièce pour lui" ; "il vérifie qu'il ne manque rien" | "Pense à ce qui montre qu'aucune pièce ne manque." | "il vérifie qu'il ne manque rien" |
| 22 | "Enzo voit une corde à sauter coupée dans la cour. Que fait-il ?" | "il prévient un adulte" ; "il la jette sous un banc" ; "il s'en sert quand même" | "Un jeu cassé n'est plus sûr : qui doit le savoir ?" | "il prévient un adulte" |
| 23 | "Zoé tient un livre de la bibliothèque à la main. Où le pose-t-elle ?" | "sur une table propre" ; "par terre dans la cour" ; "sur une chaise mouillée" | "Pense à un endroit sec et propre." | "sur une table propre" |
| 24 | "Adam utilise les feutres de la classe. Que fait-il quand il a fini ?" | "il referme chaque feutre" ; "il laisse les bouchons ouverts" ; "il appuie fort sur les pointes" | "Pense à ce qui garde l'encre dans le feutre." | "il referme chaque feutre" |

### MAT-CE1-01, ce1-nombres-jusqu-a-1000, 22 items

Constantes : verdict apres une erreur "Ce n'est pas ça. Essaie encore !" ; verdict a la revelation "Ce n'est pas ça." ; phrase "Appuie sur la bonne réponse :" ; explication rendue : aucune ; indice rendu et reponse attendue egaux a ceux du YAML pour 22 items sur 22 : oui.

| Item | Enonce | Propositions (ordre servi) | Indice | Reponse attendue |
|---|---|---|---|---|
| 1 | "Lis ce nombre : 305." | "trois cent quinze" ; "trois cent cinquante" ; "trois cent cinq" | "Dis la centaine, puis ce qui reste." | "trois cent cinq" |
| 2 | "Lis ce nombre : 407." | "quatre cent soixante-dix" ; "quatre cent sept" ; "quatre cent dix-sept" | "Regarde bien le chiffre du milieu." | "quatre cent sept" |
| 3 | "Lis ce nombre : 620." | "six cent deux" ; "six cent douze" ; "six cent vingt" | "Dis la centaine, puis la dizaine." | "six cent vingt" |
| 4 | "Lis ce nombre : 800." | "huit cents" ; "sept cents" ; "neuf cents" | "Il n'y a ni dizaine ni unité à dire." | "huit cents" |
| 5 | "Lis ce nombre : 372." | "trois cent soixante-deux" ; "trois cent soixante-douze" ; "trois cent quatre-vingt-douze" | "Après la centaine, lis les deux derniers chiffres." | "trois cent soixante-douze" |
| 6 | "Lis ce nombre : 497." | "quatre cent soixante-dix-sept" ; "quatre cent quatre-vingt-sept" ; "quatre cent quatre-vingt-dix-sept" | "Lis la centaine, puis les deux derniers chiffres ensemble." | "quatre cent quatre-vingt-dix-sept" |
| 7 | "Lis ce nombre : 1000." | "mille" ; "dix" ; "cent" | "Compte les chiffres du nombre." | "mille" |
| 8 | "Écris en chiffres : deux cent quarante-sept." | "427" ; "274" ; "247" | "Écris la centaine d'abord, puis la dizaine." | "247" |
| 9 | "Écris en chiffres : six cent quatre." | "614" ; "604" ; "640" | "Il n'y a pas de dizaine à dire." | "604" |
| 10 | "Écris en chiffres : neuf cent quatre-vingt-dix." | "910" ; "990" ; "980" | "Attention, quatre-vingt-dix ne s'écrit pas comme quatre-vingts." | "990" |
| 11 | "Écris en chiffres : cent quatre-vingt-trois." | "163" ; "183" ; "193" | "Cherche d'abord la place des centaines." | "183" |
| 12 | "Écris en chiffres : huit cent six." | "860" ; "816" ; "806" | "Une place du nombre reste vide." | "806" |
| 13 | "Écris en chiffres : cinq cents." | "500" ; "550" ; "505" | "Après la centaine, rien à ajouter." | "500" |
| 14 | "Écris en chiffres : sept cent soixante et onze." | "781" ; "761" ; "771" | "Soixante et onze commence par soixante, mais vaut plus." | "771" |
| 15 | "Quel nombre a 3 centaines, 4 dizaines et 7 unités ?" | "347" ; "374" ; "437" | "Écris les centaines, puis les dizaines, puis les unités." | "347" |
| 16 | "Quel nombre a 6 centaines et 5 unités ?" | "605" ; "650" ; "615" | "Chaque place du nombre doit avoir un chiffre." | "605" |
| 17 | "Dans 582, quel est le chiffre des dizaines ?" | "5" ; "8" ; "2" | "Les dizaines sont la place du milieu." | "8" |
| 18 | "Dans 745, combien de centaines ?" | "5 centaines" ; "7 centaines" ; "4 centaines" | "Les centaines sont la première place." | "7 centaines" |
| 19 | "Quel nombre a 4 centaines et 3 dizaines ?" | "340" ; "403" ; "430" | "Pense à la place des unités." | "430" |
| 20 | "358 = 300 + 50 + ?" | "8" ; "3" ; "5" | "Regarde le chiffre des unités." | "8" |
| 21 | "724 = 700 + ? + 4" | "70" ; "40" ; "20" | "Cherche ce qui manque entre la centaine et l'unité." | "20" |
| 22 | "Quel nombre est égal à 800 + 60 + 9 ?" | "869" ; "896" ; "689" | "Chaque terme donne un chiffre à sa place." | "869" |

### Captures demandees (744 x 551, barre classique ; 720 x 551 sans barre en plus pour `-06` 14)

Etats : **arrivee** (apres une bonne reponse sauf a la premiere manche d'une partie), **erreur** (premiere proposition fausse servie cliquee : signal, verdict, indice), **revelation** (apres la seconde erreur et un appui sur une proposition deja essayee : signal, phrase, reponse attendue, indice).

| Item | Etat | Capture 744 x 551 | Capture 720 x 551 sans barre | Notes mesurees (744, ordre servi) |
|---|---|---|---|---|
| `-06` 14 | arrivee | `06-i14-arrivee-744.png` | `06-i14-arrivee-720s.png` | signal - ; verdict - ; reponse attendue - ; indice - |
| `-06` 14 | erreur | `06-i14-erreur-744.png` | `06-i14-erreur-720s.png` | signal 524-600 ; verdict 532-592 ; reponse attendue - ; indice 524-574 |
| `-06` 14 | revelation | `06-i14-revelation-744.png` | `06-i14-revelation-720s.png` | signal 524-588 ; verdict 541-571 ; reponse attendue 558-606 (1 ligne(s)) ; indice 610-660 |
| `-06` 16 | arrivee | `06-i16-arrivee-744.png` | non demandee | signal - ; verdict - ; reponse attendue - ; indice - |
| `-06` 16 | erreur | `06-i16-erreur-744.png` | non demandee | signal 484-560 ; verdict 492-552 ; reponse attendue - ; indice 484-534 |
| `-06` 16 | revelation | `06-i16-revelation-744.png` | non demandee | signal 484-548 ; verdict 501-531 ; reponse attendue 518-566 (1 ligne(s)) ; indice 570-620 |
| `-06` 19 | arrivee | `06-i19-arrivee-744.png` | non demandee | signal - ; verdict - ; reponse attendue - ; indice - |
| `-06` 19 | erreur | `06-i19-erreur-744.png` | non demandee | signal 484-560 ; verdict 492-552 ; reponse attendue - ; indice 484-534 |
| `-06` 19 | revelation | `06-i19-revelation-744.png` | non demandee | signal 484-548 ; verdict 501-531 ; reponse attendue 518-566 (1 ligne(s)) ; indice 570-620 |
| `-06` 20 | arrivee | `06-i20-arrivee-744.png` | non demandee | signal 484-548 ; verdict 501-531 ; reponse attendue - ; indice - |
| `-06` 20 | erreur | `06-i20-erreur-744.png` | non demandee | signal 484-560 ; verdict 492-552 ; reponse attendue - ; indice 484-534 |
| `-06` 20 | revelation | `06-i20-revelation-744.png` | non demandee | signal 484-548 ; verdict 501-531 ; reponse attendue 518-566 (1 ligne(s)) ; indice 570-620 |
| EMC 13 | arrivee | `emc-i13-arrivee-744.png` | non demandee | signal - ; verdict - ; reponse attendue - ; indice - |
| EMC 13 | erreur | `emc-i13-erreur-744.png` | non demandee | signal 460-536 ; verdict 468-528 ; reponse attendue - ; indice 460-510 |
| EMC 13 | revelation | `emc-i13-revelation-744.png` | non demandee | signal 460-524 ; verdict 477-507 ; reponse attendue 494-590 (2 ligne(s)) ; indice 594-644 |
| EMC 8 | arrivee | `emc-i08-arrivee-744.png` | non demandee | signal - ; verdict - ; reponse attendue - ; indice - |
| EMC 8 | erreur | `emc-i08-erreur-744.png` | non demandee | signal 420-496 ; verdict 428-488 ; reponse attendue - ; indice 420-470 |
| EMC 8 | revelation | `emc-i08-revelation-744.png` | non demandee | signal 420-484 ; verdict 437-467 ; reponse attendue 454-502 (1 ligne(s)) ; indice 506-556 |
| EMC 9 | arrivee | `emc-i09-arrivee-744.png` | non demandee | signal - ; verdict - ; reponse attendue - ; indice - |
| EMC 9 | erreur | `emc-i09-erreur-744.png` | non demandee | signal 354-430 ; verdict 362-422 ; reponse attendue - ; indice 354-404 |
| EMC 9 | revelation | `emc-i09-revelation-744.png` | non demandee | signal 354-418 ; verdict 371-401 ; reponse attendue 388-436 (1 ligne(s)) ; indice 440-490 |
| EMC 14 | arrivee | `emc-i14-arrivee-744.png` | non demandee | signal 394-458 ; verdict 411-441 ; reponse attendue - ; indice - |
| EMC 14 | erreur | `emc-i14-erreur-744.png` | non demandee | signal 394-470 ; verdict 402-462 ; reponse attendue - ; indice 394-474 |
| EMC 14 | revelation | `emc-i14-revelation-744.png` | non demandee | signal 394-458 ; verdict 411-441 ; reponse attendue 428-476 (1 ligne(s)) ; indice 480-560 |
| `-11` 13 | arrivee | `11-i13-arrivee-744.png` | non demandee | signal 460-524 ; verdict 477-507 ; reponse attendue - ; indice - |
| `-11` 13 | erreur | `11-i13-erreur-744.png` | non demandee | signal 460-536 ; verdict 468-528 ; reponse attendue - ; indice 460-540 |
| `-11` 13 | revelation | `11-i13-revelation-744.png` | non demandee | signal 460-524 ; verdict 477-507 ; reponse attendue 494-590 (2 ligne(s)) ; indice 594-674 |
| `-07` 1 | arrivee | `07-i01-arrivee-744.png` | non demandee | signal 352-416 ; verdict 369-399 ; reponse attendue - ; indice - |
| `-07` 1 | erreur | `07-i01-erreur-744.png` | non demandee | signal 352-428 ; verdict 360-420 ; reponse attendue - ; indice 352-402 |
| `-07` 1 | revelation | `07-i01-revelation-744.png` | non demandee | signal 352-416 ; verdict 369-399 ; reponse attendue 386-434 (1 ligne(s)) ; indice 438-488 |
| `-06` 1 | arrivee | `06-i01-arrivee-744.png` | non demandee | signal 322-386 ; verdict 339-369 ; reponse attendue - ; indice - |
| `-06` 1 | erreur | `06-i01-erreur-744.png` | non demandee | signal 322-398 ; verdict 330-390 ; reponse attendue - ; indice 322-372 |
| `-06` 1 | revelation | `06-i01-revelation-744.png` | non demandee | signal 322-386 ; verdict 339-369 ; reponse attendue 356-404 (1 ligne(s)) ; indice 408-458 |
| EMC 1 | arrivee | `emc-i01-arrivee-744.png` | non demandee | signal 354-418 ; verdict 371-401 ; reponse attendue - ; indice - |
| EMC 1 | erreur | `emc-i01-erreur-744.png` | non demandee | signal 354-430 ; verdict 362-422 ; reponse attendue - ; indice 354-434 |
| EMC 1 | revelation | `emc-i01-revelation-744.png` | non demandee | signal 354-418 ; verdict 371-401 ; reponse attendue 388-436 (1 ligne(s)) ; indice 440-520 |
| `-11` 1 | arrivee | `11-i01-arrivee-744.png` | non demandee | signal 460-524 ; verdict 477-507 ; reponse attendue - ; indice - |
| `-11` 1 | erreur | `11-i01-erreur-744.png` | non demandee | signal 460-536 ; verdict 468-528 ; reponse attendue - ; indice 460-540 |
| `-11` 1 | revelation | `11-i01-revelation-744.png` | non demandee | signal 460-524 ; verdict 477-507 ; reponse attendue 494-542 (1 ligne(s)) ; indice 546-626 |
| MAT 1 | arrivee | `mat-i01-arrivee-744.png` | non demandee | signal 354-418 ; verdict 371-401 ; reponse attendue - ; indice - |
| MAT 1 | erreur | `mat-i01-erreur-744.png` | non demandee | signal 354-430 ; verdict 362-422 ; reponse attendue - ; indice 354-404 |
| MAT 1 | revelation | `mat-i01-revelation-744.png` | non demandee | signal 354-418 ; verdict 371-401 ; reponse attendue 388-436 (1 ligne(s)) ; indice 440-490 |

**`-06` item 14 dans les deux dispositions des propositions** (le serveur tire l'ordre au hasard ; je reordonne le DOM de la page ouverte, sans toucher au reste, pour montrer les deux dispositions). Le texte-support est celui qui suit la correction : "Ce matin, Inès part à l'école avec son frère. La pluie se met à tomber. Inès ouvre son parapluie au-dessus de lui. Ils arrivent tout secs.".

| Etat | Rangees de propositions | Ordre (indices dans l'ordre servi) | Capture 744 x 551 | Capture 720 x 551 sans barre | Signal | Verdict | Indice / reponse attendue |
|---|---|---|---|---|---|---|---|
| erreur | 2 | 021 | `06-i14-erreur-ordre021-2rangees-744.png` | `06-i14-erreur-ordre021-2rangees-720s.png` | 458-534 | 466-526 | indice 458-508 ; reponse attendue - |
| erreur | 3 | 012 | `06-i14-erreur-ordre012-3rangees-744.png` | `06-i14-erreur-ordre012-3rangees-720s.png` | 524-600 | 532-592 | indice 524-574 ; reponse attendue - |
| revelation | 2 | 021 | `06-i14-revelation-ordre021-2rangees-744.png` | `06-i14-revelation-ordre021-2rangees-720s.png` | 458-522 | 475-505 | indice 544-594 ; reponse attendue 492-540 |
| revelation | 3 | 012 | `06-i14-revelation-ordre012-3rangees-744.png` | `06-i14-revelation-ordre012-3rangees-720s.png` | 524-588 | 541-571 | indice 610-660 ; reponse attendue 558-606 |

## C. "decomposer" dans `MAT-CE1-01` et dans les ecrans parcourus

**Recherche** : la racine "decompos", insensible a la casse et aux accents (donc "decomposer", "decompose", "decomposition", "decomposons", "decomposez"), dans quatre portees pour chaque ecran : (1) `document.documentElement.outerHTML` du DOM, qui contient le texte, les attributs et les commentaires HTML servis ; (2) `document.body.innerText` ; (3) les attributs `title`, `aria-label`, `alt`, `value`, `placeholder`, `data-focus-cible` de tous les elements ; (4) pour les cinq pages de navigation, le **corps HTTP brut** de la reponse (`fetch`), avant tout script.

**Resultat : 0 occurrence** dans l'ensemble des ecrans ci-dessous.

| Ensemble d'ecrans parcourus | Nombre d'ecrans (instantanes du DOM) | Occurrences |
|---|---|---|
| Accueil `/`, niveau `/niveaux/CE1`, matieres `maths`, `francais`, `vivre-ensemble` (DOM et corps HTTP brut) | 5 | 0 |
| Jeu `bandit-manchot-nombres-jusqu-a-1000-ce1`, les 22 items : arrivee, apres une erreur, apres une seconde erreur, revelation | 88 | 0 |
| Partie complete du meme jeu, 10 manches puis ecran de fin ("Les jeux Tu as trouvé, tout seul : 10 Tu as joué toutes les questions : 10 Bravo, c'est juste ! C'est fini !") puis apres la derniere manche | 12 | 0 |
| Retour a l'accueil par le bouton "Les jeux" (texte de l'ecran : "Choisis ta classe.") | 1 | 0 |
| Les quatre autres paquets d'items (`-07`, `-06`, `-11`, EMC), tous etats, dont les etats masques de `-07` | 392 | 0 |
| Jeux de la section D, arrivee et apres un coup | 18 | 0 |

Total des ecrans fouilles : 516. Ce que cette recherche ne couvre pas : le texte des fichiers YAML du contenu (le mot y figure dans des commentaires de conformite, **non servis**), les feuilles CSS et le JavaScript servis, et tout ecran que je n'ai pas ouvert (les 143 autres jeux du catalogue, hors les 5 paquets et la section D, les ecrans CP et CM1 de navigation).

## D. Lecture a l'interligne 1,25 : une manche de chaque niveau

Le jar est celui de `round-two` : l'interligne de l'enonce et des propositions y est de 1,25 (`5302af53`). Valeurs lues dans le navigateur : `line-height` calcule de l'enonce et d'une proposition, hauteur de la boite de l'enonce, nombre de lignes de la consigne et de l'enonce, hauteur de chaque proposition. Captures de l'arrivee et de l'ecran qui suit un premier appui (juste ou faux selon l'item), a 744 x 551 et a 720 x 551 sans barre.

| Niveau | Jeu | Consigne (lignes) | Enonce (lignes ; hauteur) | `line-height` / taille (enonce ; proposition) | Hauteurs des propositions | Capture arrivee 744 | Capture arrivee 720 sans barre | Capture apres un coup 744 | Capture apres un coup 720 sans barre |
|---|---|---|---|---|---|---|---|---|---|
| CP | `bandit-manchot-nature-mot-cp` | "Le mot entre guillemets est-il un nom ou un verbe ?" (1) | "Les enfants « chantent » une chanson." (2 ; 46 px) | 40px / 32px ; 40px / 32px | 58, 58 px | `d-bandit-manchot-nature-mot-cp-arrivee-744.png` | `d-bandit-manchot-nature-mot-cp-arrivee-720s.png` | `d-bandit-manchot-nature-mot-cp-apres-un-coup-744.png` | `d-bandit-manchot-nature-mot-cp-apres-un-coup-720s.png` |
| CP | `bandit-manchot-texte-narratif-cp` | "Lis le petit texte, puis réponds à la question." (1) | "Où Elsa oublie-t-elle la craie ?" (1 ; 40 px) | 40px / 32px ; 40px / 32px | 58, 58, 58 px | `d-bandit-manchot-texte-narratif-cp-arrivee-744.png` | `d-bandit-manchot-texte-narratif-cp-arrivee-720s.png` | `d-bandit-manchot-texte-narratif-cp-apres-un-coup-744.png` | `d-bandit-manchot-texte-narratif-cp-apres-un-coup-720s.png` |
| CP | `bandit-manchot-position-syllabe-cp` | "Écoute l'adulte lire le mot, puis dis si le son cherché est au début, au milieu ou à la fin." (2) | "Où entends-tu ce son ?" (1 ; 40 px) | 40px / 32px ; 40px / 32px | 58, 58, 58 px | `d-bandit-manchot-position-syllabe-cp-arrivee-744.png` | `d-bandit-manchot-position-syllabe-cp-arrivee-720s.png` | `d-bandit-manchot-position-syllabe-cp-apres-un-coup-744.png` | `d-bandit-manchot-position-syllabe-cp-apres-un-coup-720s.png` |
| CP | `bandit-manchot-matiere-objets-cp` | "Lis bien, puis dis en quelle matière est fait l'objet, ou trie les matières selon leurs propriétés." (2) | "Les fenêtres de la maison laissent passer la lumière. En quelle matière sont-elles faites ?" (2 ; 80 px) | 40px / 32px ; 40px / 32px | 58, 58, 58 px | `d-bandit-manchot-matiere-objets-cp-arrivee-744.png` | `d-bandit-manchot-matiere-objets-cp-arrivee-720s.png` | `d-bandit-manchot-matiere-objets-cp-apres-un-coup-744.png` | `d-bandit-manchot-matiere-objets-cp-apres-un-coup-720s.png` |
| CP | `bandit-manchot-additions-cp` | "Combien ça fait ?" (1) | "3 + 5" (1 ; 40 px) | 40px / 32px ; 40px / 32px | 58, 58, 58 px | `d-bandit-manchot-additions-cp-arrivee-744.png` | `d-bandit-manchot-additions-cp-arrivee-720s.png` | `d-bandit-manchot-additions-cp-apres-un-coup-744.png` | `d-bandit-manchot-additions-cp-apres-un-coup-720s.png` |
| CE1 | `bandit-manchot-additions-ce1` | "Combien ça fait ?" (1) | "14 + 5 = ?" (1 ; 40 px) | 40px / 32px ; 40px / 32px | 58, 58, 58 px | `d-bandit-manchot-additions-ce1-arrivee-744.png` | `d-bandit-manchot-additions-ce1-arrivee-720s.png` | `d-bandit-manchot-additions-ce1-apres-un-coup-744.png` | `d-bandit-manchot-additions-ce1-apres-un-coup-720s.png` |
| CE1 | `bandit-manchot-complements-dizaine-ce1` | "Quel nombre manque ?" (1) | "76 + ? = 80" (1 ; 40 px) | 40px / 32px ; 40px / 32px | 58, 58, 58 px | `d-bandit-manchot-complements-dizaine-ce1-arrivee-744.png` | `d-bandit-manchot-complements-dizaine-ce1-arrivee-720s.png` | `d-bandit-manchot-complements-dizaine-ce1-apres-un-coup-744.png` | `d-bandit-manchot-complements-dizaine-ce1-apres-un-coup-720s.png` |
| CM1 | `bandit-manchot-homophones-cm1` | "Quel mot complète la phrase ?" (1) | "Papa ___ maman font la cuisine." (1 ; 40 px) | 40px / 32px ; 40px / 32px | 58, 58 px | `d-bandit-manchot-homophones-cm1-arrivee-744.png` | `d-bandit-manchot-homophones-cm1-arrivee-720s.png` | `d-bandit-manchot-homophones-cm1-apres-un-coup-744.png` | `d-bandit-manchot-homophones-cm1-apres-un-coup-720s.png` |
| CM1 | `bandit-manchot-melanges-cm1` | "Lis bien, puis dis si le mélange est homogène ou hétérogène, ou choisis le bon moyen de le séparer." (2) | "Dans un verre, on mélange de l'eau et du sable. Le sable retombe au fond du verre. Quel type de mélange est-ce ?" (3 ; 120 px) | 40px / 32px ; 40px / 32px | 58, 58 px | `d-bandit-manchot-melanges-cm1-arrivee-744.png` | `d-bandit-manchot-melanges-cm1-arrivee-720s.png` | `d-bandit-manchot-melanges-cm1-apres-un-coup-744.png` | `d-bandit-manchot-melanges-cm1-apres-un-coup-720s.png` |

`bandit-manchot-nature-mot-cp` : le mot designe dans la phrase (marque sur fond jaune) ; `bandit-manchot-position-syllabe-cp` et `bandit-manchot-matiere-objets-cp` : jeux CP dont la consigne passe sur deux lignes (le premier est un jeu a bloc adulte, de lecture ; le second est de "Questionner le monde" mais se lit : "Lis bien, ...") ; `bandit-manchot-melanges-cm1` : consigne sur deux lignes, enonce sur trois lignes.

## E. Espaces insecables avant : ; ? ! et chevrons

Methode : pour chaque `:` `;` `?` `!` `»`, le code point du caractere qui precede ; pour chaque `«`, celui du caractere qui suit ; lu dans le DOM (`textContent`) de l'enonce, de la consigne, du texte-support, des propositions et de l'indice. `U+00A0` = espace insecable ; `U+0020` = espace simple.

| Item | Champ | Contexte (9 caracteres) | Caractere | Code point voisin |
|---|---|---|---|---|
| FRA-CE1-07 1 (V) | enonce | "e lapin<U+00A0>?" | ? | avant : U+00A0 |
| FRA-CE1-07 1 (E) | indice | "«<U+00A0>lapin<U+00A0>»" | « | apres : U+00A0 |
| FRA-CE1-07 1 (E) | indice | "«<U+00A0>lapin<U+00A0>»" | » | avant : U+00A0 |
| FRA-CE1-11 1 (V) | enonce | "«<U+00A0>Léo aim" | « | apres : U+00A0 |
| FRA-CE1-11 1 (V) | enonce | "inards.<U+00A0>»" | » | avant : U+00A0 |
| FRA-CE1-11 1 (V) | enonce | "égative<U+00A0>?" | ? | avant : U+00A0 |
| MAT-CE1-01 1 (V) | enonce | " nombre<U+00A0>:" | : | avant : U+00A0 |
| FRA-CE1-06 9 (V) | enonce | " Adrien<U+00A0>?" | ? | avant : U+00A0 |
| FRA-CE1-06 9 (E) | indice | "indices<U+00A0>:" | : | avant : U+00A0 |
| FRA-CE1-06 9 (E) | indice | "voit-on<U+00A0>?" | ? | avant : U+00A0 |
| EMC-CE1-01 1 (V) | enonce | "l'école<U+00A0>?" | ? | avant : U+00A0 |
| EMC-CE1-01 1 (E) | indice | "nde-toi<U+00A0>:" | : | avant : U+00A0 |
| EMC-CE1-01 1 (E) | indice | "ou tous<U+00A0>?" | ? | avant : U+00A0 |

Total sur les 110 items (etat V, plus l'indice de l'etat E) :

| Cas | Occurrences |
|---|---|
| ":" apres U+00A0 | 14 |
| "?" apres U+00A0 | 96 |
| "«" avant U+00A0 | 24 |
| "»" apres U+00A0 | 24 |
| indice ":" apres U+00A0 | 8 |
| indice "?" apres U+00A0 | 8 |
| indice "«" avant U+00A0 | 14 |
| indice "»" apres U+00A0 | 14 |
| un des cinq signes precede de `U+0020` | 0 |

Je ne montre pas a l'ecran qu'une insecable empeche un retour a la ligne devant le signe : cela depend du moteur de rendu et de la largeur ; la capture `11-i01-arrivee-744.png` montre les chevrons de `-11` 1 sur une ligne. Sur les 110 items, aucun signe de ponctuation haute n'est precede d'une espace simple dans le DOM servi.

## F. La proposition a 58 px et la sortie "Les jeux"

Mesure dans le DOM de `bandit-manchot-nombres-jusqu-a-1000-ce1`, premiere manche, 744 x 551, barre classique (`getBoundingClientRect`) :

| Element | Hauteur | Largeur | `line-height` / taille |
|---|---|---|---|
| Proposition 1 ("3") | 58 px | 216 px | 40px / 32px |
| Proposition 2 ("5") | 58 px | 216 px | 40px / 32px |
| Proposition 3 ("8") | 58 px | 216 px | 40px / 32px |
| `a.btn.chip-niveau` ("CP") | 50 px | 56.4 px | - |
| `a.btn.chip-niveau.est-actif` ("CE1") | 50 px | 66.1 px | - |
| `a.btn.chip-niveau` ("CM1") | 50 px | 73.9 px | - |
| `a.btn.lien-accueil` ("Les jeux") | 50 px | 108.8 px | - |

**Rapport proposition / sortie "Les jeux" : 58 / 50 = 1.16.** Captures : `f-proposition-sortie-744.png` (744 x 551) et `f-proposition-sortie-720s.png` (720 x 551 sans barre).

## G. Ce que ce releve ne peut pas montrer

- **Un appareil reel** : la fenetre est un rectangle de 744 ou 720 pixels de large sur un ecran de poste, facteur 1. Je ne vois ni la taille physique d'une proposition sous un doigt, ni l'eclairage, ni la brillance d'une tablette, ni un ecran a haute densite de pixels.
- **Une police reelle** : tout est rendu avec la police du poste ; les polices que l'appareil d'un enfant servirait (pile imposee par `ADR-0054`) ne sont pas essayees. Une autre police change la largeur des lignes, donc les rangees de propositions et les retours a la ligne releves ici.
- **Le geste d'un enfant** : `click()` sur un bouton n'est ni un appui d'enfant, ni une hesitation, ni un appui rate a cote du bouton. Je ne mesure ni le temps de lecture, ni ce que l'enfant comprend, ni ce qu'il croit devoir faire apres "Essaie encore !".
- **L'etat "texte masque" du produit** : il n'existe pas (section A). Ce que j'ai montre est une page dont j'ai cache le texte-support par une feuille de style posee par moi.
- **Une lecture au lecteur d'ecran, au clavier, ou sous une vision des couleurs altérée** : non faites.
- **Le trait des lettres coupe par le bord de l'ecran** : les captures le montrent a l'oeil, les cotes de la section B sont des bas de boite et de rectangle de ligne, pas des bas de glyphe.
- **Une suite de manches vecue** : chaque capture est un etat isole, joue par un script.
- **Les 143 autres jeux du catalogue** : seuls les 5 paquets du palier et les 9 jeux de la section D sont ouverts.

## H. Isolation

Un jar (port 18711, PID 33452) et un Chrome (PID 41044, port 19711), arretes par PID, aucun processus arrete par nom d'image ; le Chrome a disparu a la fin du pilote, le port du jar ne repond plus. Aucun fichier de `domain`, `application`, `adapters`, `bootstrap` ni aucun gabarit modifie. Le seul fichier ajoute au depot est celui-ci, sur la branche `s57-ines-r1` (worktree `recreo-ines-r1`) ; aucune fusion, aucun push. `mvn -B verify` n'est pas lance et je n'annonce pas la porte technique : celle de `ad9ef86d` est celle que le coordinateur a etablie.

## Decisions prises

**Aucune.** Ce fichier ne cree ni n'amende aucune `ADR` ni `DEC`, ne leve aucune reserve, ne prononce aucun Done et ne cree aucun identifiant de condition.


---

# Releve a l'ecran du 2026-10-21 (suite) : `-07`, items 5, 9 et 17 apres la correction de `marc-dev`

- **Auteure** : `ines-design`. **Date** : 2026-10-21, 14:46 (UTC+02:00), lue par `date` (releve de 14:41 a 14:45). Cette section suit la section A ci-dessus, dont elle reprend la forme, sans en changer un chiffre. Elle demande a `nadia-prof` rien d'autre que de lire ; elle ne prononce aucun Done, ne juge aucun contenu, ne decide rien. Aucun identifiant nouveau.
- **Demande** : `verdict-nadia-palier-3-ce1.md`, derniere section (rejeu de `-07`, items 5, 9 et 17) : texte DOM des etats V, E et R, trois captures a 744 x 551 par item, l'etat texte masque en plus, et la comparaison au YAML.
- **Arbre** : `round-two` a `ea5d94292a54469c369717008abaee41806be4b7` (contient `afd33e88`, la correction des items 5, 9 et 17, et la fusion de mon releve), worktree detache dedie `recreo-ines-r2`, branche `s57-ines-r2`. Blob du YAML lu : `git rev-parse round-two:adapters/adapter-content/src/main/resources/contenu/ce1-texte-documentaire.yaml` = `5f3e6c953c5d72f5b6c531988bad45476715d8f4`.
- **Jar** : `bash outils/mvn-jdk21.sh -B clean package -DskipTests` (sortie 0), copie hors depot, SHA-256 `0b6fd178e7364331e983f1c10728857c56c71ea09dfaa9aef4f886f87f7f4b9f`. Feuille servie `/css/recreo.css` : SHA-256 `1fa775cadc55f9b7a2252312340aae077c36218b019cdbf9ad2ebc11dc3654b5` (identique a celle de la section A). Port 18721, PID 52152, arrete par ce PID.
- **Methode** : celle de la section A (pilote `releve2.mjs`, hors depot). Un seul Chrome sans tete (PID 23200, profil jetable, port 19721, disparu a la fin du pilote). Fenetre de 744 x 551 avec barre classique et de 720 x 551 sans barre, facteur 1. 26 parties jouees depuis `/niveaux/CE1/matieres/francais` ; a **chaque** rencontre d'un des trois items, je releve l'enonce, le texte-support et l'ordre des propositions a l'arrivee, j'appuie sur une proposition fausse et je releve l'indice, puis je donne la bonne reponse. La premiere rencontre de chaque item est jouee en entier (arrivee, erreur, seconde erreur, revelation) avec captures. 11, 16 et 15 rencontres : item 5, 9, 17. **Les six ordres possibles des trois propositions ont ete servis pour chacun des trois items** (6 sur 6).
- **Etat "texte masque"** : comme en section A, il n'existe pas dans le produit ; je cache `.texte-support` par une feuille de style posee dans la page ouverte (`display: none`), rien d'autre. Je le releve a l'arrivee (M), apres une erreur (EM) et a la revelation (RM). Le reste du DOM est celui du jar.
- **Captures** : 36 PNG (3 items x 6 etats x 2 largeurs), dans le dossier de captures `r1-releve/` defini en tete de ce fichier (meme dossier que la section A). Nom : `07-i<NN>-r2-<etat>-<744 ou 720s>.png`, `720s` = 720 x 551 sans barre. Chacune est citee ci-dessous par son nom de fichier.

## I. Le texte servi est-il celui du YAML ?

**Oui, identique, sur les trois items.** 15 comparaisons sur 15, caractere par caractere (espaces insecables U+00A0 comprises, aucune normalisation) entre le YAML de `round-two` et le DOM servi. **Aucun ecart.**

| Item | Champ du YAML | Valeur du YAML | Rendu identique (comparaison caractere par caractere, U+00A0 compris) | Releve sur |
|---|---|---|---|---|
| 5 | enonce | "Avec quoi fabrique-t-on le verre ?" | oui | V et 11 rencontres |
| 5 | texteSupport | "Le verre se fabrique avec du sable. Le papier se fabrique avec du bois. Le pull se fabrique avec de la laine." | oui | V et 11 rencontres ; le DOM garde le texte meme masque |
| 5 | bonneReponse | "du sable" | oui | R (reponse attendue) |
| 5 | distracteurs | "du bois ; de la laine" | oui | les 6 ordres servis : meme ensemble de 3 propositions que bonneReponse + distracteurs |
| 5 | indice | "Cherche le mot « verre » dans le texte." | oui | E et 11 rencontres, six ordres |
| 9 | enonce | "Comment dort le cheval ?" | oui | V et 16 rencontres |
| 9 | texteSupport | "La chauve-souris dort la tête en bas. Le cheval dort debout. Le chat dort en boule." | oui | V et 16 rencontres ; le DOM garde le texte meme masque |
| 9 | bonneReponse | "debout" | oui | R (reponse attendue) |
| 9 | distracteurs | "en boule ; la tête en bas" | oui | les 6 ordres servis : meme ensemble de 3 propositions que bonneReponse + distracteurs |
| 9 | indice | "Cherche le mot « cheval » dans le texte." | oui | E et 16 rencontres, six ordres |
| 17 | enonce | "Que faut-il faire juste après avoir cassé les œufs ?" | oui | V et 15 rencontres |
| 17 | texteSupport | "Casse deux œufs dans un bol. Ajoute du sucre. Mélange bien." | oui | V et 15 rencontres ; le DOM garde le texte meme masque |
| 17 | bonneReponse | "Ajoute du sucre" | oui | R (reponse attendue) |
| 17 | distracteurs | "Mélange bien ; Casse deux œufs" | oui | les 6 ordres servis : meme ensemble de 3 propositions que bonneReponse + distracteurs |
| 17 | indice | "Les instructions se lisent dans l'ordre." | oui | E et 15 rencontres, six ordres |

Ce que la comparaison couvre : l'enonce, le texte-support, la bonne reponse (telle que la revelation l'affiche), l'ensemble des trois propositions (la bonne et les deux distracteurs), l'indice (tel qu'il s'affiche apres une erreur). Ce qu'elle ne couvre pas : l'ordre des propositions, que le jeu tire au hasard (c'est l'objet des tables ci-dessous) ; et les mots que le gabarit ecrit lui-meme (verdict, phrase de revelation), qui ne viennent pas du YAML.

## J. Les trois items, etat par etat

### Item 5

Texte DOM exact (zone de jeu, `innerText`, ce que l'oeil lit). V = arrivee, M = arrivee texte masque, E = apres une erreur, EM = idem texte masque, R = revelation, RM = idem texte masque.

- **V** : "Score : 0 / Lis le petit texte, puis réponds à la question. / Le verre se fabrique avec du sable. Le papier se fabrique avec du bois. Le pull se fabrique avec de la laine. / Avec quoi fabrique-t-on le verre ? / du bois du sable de la laine"
- **M** : "Score : 0 / Lis le petit texte, puis réponds à la question. / Avec quoi fabrique-t-on le verre ? / du bois du sable de la laine"
- **E** : "Score : 0 / Lis le petit texte, puis réponds à la question. / Le verre se fabrique avec du sable. Le papier se fabrique avec du bois. Le pull se fabrique avec de la laine. / Avec quoi fabrique-t-on le verre ? / du bois du sable de la laine / Ce n'est pas ça. Essaie encore ! / Cherche le mot « verre » dans le texte."
- **EM** : "Score : 0 / Lis le petit texte, puis réponds à la question. / Avec quoi fabrique-t-on le verre ? / du bois du sable de la laine / Ce n'est pas ça. Essaie encore ! / Cherche le mot « verre » dans le texte."
- **R** : "Score : 0 / Lis le petit texte, puis réponds à la question. / Le verre se fabrique avec du sable. Le papier se fabrique avec du bois. Le pull se fabrique avec de la laine. / Avec quoi fabrique-t-on le verre ? / du bois du sable de la laine / Ce n'est pas ça. / Appuie sur la bonne réponse : / du sable / Cherche le mot « verre » dans le texte."
- **RM** : "Score : 0 / Lis le petit texte, puis réponds à la question. / Avec quoi fabrique-t-on le verre ? / du bois du sable de la laine / Ce n'est pas ça. / Appuie sur la bonne réponse : / du sable / Cherche le mot « verre » dans le texte."

Cotes a 744 x 551 (ordre servi, premiere rencontre) : en E, signal 352-428, verdict 360-420, indice 352-402 ; en R, phrase 352-382, reponse attendue 386-434, indice 438-488. Le bas du viewport est a 551.

| Etat | Capture 744 x 551, barre classique | Capture 720 x 551, sans barre |
|---|---|---|
| V arrivee | `07-i05-r2-arrivee-744.png` | `07-i05-r2-arrivee-720s.png` |
| M arrivee, texte masque | `07-i05-r2-masque-arrivee-744.png` | `07-i05-r2-masque-arrivee-720s.png` |
| E erreur | `07-i05-r2-erreur-744.png` | `07-i05-r2-erreur-720s.png` |
| EM erreur, texte masque | `07-i05-r2-masque-erreur-744.png` | `07-i05-r2-masque-erreur-720s.png` |
| R revelation | `07-i05-r2-revelation-744.png` | `07-i05-r2-revelation-720s.png` |
| RM revelation, texte masque | `07-i05-r2-masque-revelation-744.png` | `07-i05-r2-masque-revelation-720s.png` |

Les six ordres de propositions servis par le jeu (tires au hasard par le serveur ; 11 rencontres de l'item au total, chaque rencontre jouee jusqu'a une erreur pour relever l'indice) :

| Ordre servi (de gauche a droite et de haut en bas) | Rencontres | Indice rendu apres l'erreur |
|---|---|---|
| "de la laine / du bois / du sable" | 1 | "Cherche le mot « verre » dans le texte." |
| "de la laine / du sable / du bois" | 2 | "Cherche le mot « verre » dans le texte." |
| "du bois / de la laine / du sable" | 2 | "Cherche le mot « verre » dans le texte." |
| "du bois / du sable / de la laine" | 4 | "Cherche le mot « verre » dans le texte." |
| "du sable / de la laine / du bois" | 1 | "Cherche le mot « verre » dans le texte." |
| "du sable / du bois / de la laine" | 1 | "Cherche le mot « verre » dans le texte." |

### Item 9

Texte DOM exact (zone de jeu, `innerText`, ce que l'oeil lit). V = arrivee, M = arrivee texte masque, E = apres une erreur, EM = idem texte masque, R = revelation, RM = idem texte masque.

- **V** : "Score : 4 / Lis le petit texte, puis réponds à la question. / La chauve-souris dort la tête en bas. Le cheval dort debout. Le chat dort en boule. / Comment dort le cheval ? / en boule debout la tête en bas / Bravo !"
- **M** : "Score : 4 / Lis le petit texte, puis réponds à la question. / Comment dort le cheval ? / en boule debout la tête en bas / Bravo !"
- **E** : "Score : 4 / Lis le petit texte, puis réponds à la question. / La chauve-souris dort la tête en bas. Le cheval dort debout. Le chat dort en boule. / Comment dort le cheval ? / en boule debout la tête en bas / Ce n'est pas ça. Essaie encore ! / Cherche le mot « cheval » dans le texte."
- **EM** : "Score : 4 / Lis le petit texte, puis réponds à la question. / Comment dort le cheval ? / en boule debout la tête en bas / Ce n'est pas ça. Essaie encore ! / Cherche le mot « cheval » dans le texte."
- **R** : "Score : 4 / Lis le petit texte, puis réponds à la question. / La chauve-souris dort la tête en bas. Le cheval dort debout. Le chat dort en boule. / Comment dort le cheval ? / en boule debout la tête en bas / Ce n'est pas ça. / Appuie sur la bonne réponse : / debout / Cherche le mot « cheval » dans le texte."
- **RM** : "Score : 4 / Lis le petit texte, puis réponds à la question. / Comment dort le cheval ? / en boule debout la tête en bas / Ce n'est pas ça. / Appuie sur la bonne réponse : / debout / Cherche le mot « cheval » dans le texte."

Cotes a 744 x 551 (ordre servi, premiere rencontre) : en E, signal 352-428, verdict 360-420, indice 352-402 ; en R, phrase 352-382, reponse attendue 386-434, indice 438-488. Le bas du viewport est a 551.

| Etat | Capture 744 x 551, barre classique | Capture 720 x 551, sans barre |
|---|---|---|
| V arrivee | `07-i09-r2-arrivee-744.png` | `07-i09-r2-arrivee-720s.png` |
| M arrivee, texte masque | `07-i09-r2-masque-arrivee-744.png` | `07-i09-r2-masque-arrivee-720s.png` |
| E erreur | `07-i09-r2-erreur-744.png` | `07-i09-r2-erreur-720s.png` |
| EM erreur, texte masque | `07-i09-r2-masque-erreur-744.png` | `07-i09-r2-masque-erreur-720s.png` |
| R revelation | `07-i09-r2-revelation-744.png` | `07-i09-r2-revelation-720s.png` |
| RM revelation, texte masque | `07-i09-r2-masque-revelation-744.png` | `07-i09-r2-masque-revelation-720s.png` |

Les six ordres de propositions servis par le jeu (tires au hasard par le serveur ; 16 rencontres de l'item au total, chaque rencontre jouee jusqu'a une erreur pour relever l'indice) :

| Ordre servi (de gauche a droite et de haut en bas) | Rencontres | Indice rendu apres l'erreur |
|---|---|---|
| "debout / en boule / la tête en bas" | 1 | "Cherche le mot « cheval » dans le texte." |
| "debout / la tête en bas / en boule" | 4 | "Cherche le mot « cheval » dans le texte." |
| "en boule / debout / la tête en bas" | 4 | "Cherche le mot « cheval » dans le texte." |
| "en boule / la tête en bas / debout" | 5 | "Cherche le mot « cheval » dans le texte." |
| "la tête en bas / debout / en boule" | 1 | "Cherche le mot « cheval » dans le texte." |
| "la tête en bas / en boule / debout" | 1 | "Cherche le mot « cheval » dans le texte." |

### Item 17

Texte DOM exact (zone de jeu, `innerText`, ce que l'oeil lit). V = arrivee, M = arrivee texte masque, E = apres une erreur, EM = idem texte masque, R = revelation, RM = idem texte masque.

- **V** : "Score : 5 / Lis le petit texte, puis réponds à la question. / Casse deux œufs dans un bol. Ajoute du sucre. Mélange bien. / Que faut-il faire juste après avoir cassé les œufs ? / Casse deux œufs Ajoute du sucre Mélange bien / Bravo !"
- **M** : "Score : 5 / Lis le petit texte, puis réponds à la question. / Que faut-il faire juste après avoir cassé les œufs ? / Casse deux œufs Ajoute du sucre Mélange bien / Bravo !"
- **E** : "Score : 5 / Lis le petit texte, puis réponds à la question. / Casse deux œufs dans un bol. Ajoute du sucre. Mélange bien. / Que faut-il faire juste après avoir cassé les œufs ? / Casse deux œufs Ajoute du sucre Mélange bien / Ce n'est pas ça. Essaie encore ! / Les instructions se lisent dans l'ordre."
- **EM** : "Score : 5 / Lis le petit texte, puis réponds à la question. / Que faut-il faire juste après avoir cassé les œufs ? / Casse deux œufs Ajoute du sucre Mélange bien / Ce n'est pas ça. Essaie encore ! / Les instructions se lisent dans l'ordre."
- **R** : "Score : 5 / Lis le petit texte, puis réponds à la question. / Casse deux œufs dans un bol. Ajoute du sucre. Mélange bien. / Que faut-il faire juste après avoir cassé les œufs ? / Casse deux œufs Ajoute du sucre Mélange bien / Ce n'est pas ça. / Appuie sur la bonne réponse : / Ajoute du sucre / Les instructions se lisent dans l'ordre."
- **RM** : "Score : 5 / Lis le petit texte, puis réponds à la question. / Que faut-il faire juste après avoir cassé les œufs ? / Casse deux œufs Ajoute du sucre Mélange bien / Ce n'est pas ça. / Appuie sur la bonne réponse : / Ajoute du sucre / Les instructions se lisent dans l'ordre."

Cotes a 744 x 551 (ordre servi, premiere rencontre) : en E, signal 428-504, verdict 436-496, indice 428-478 ; en R, phrase 428-458, reponse attendue 462-510, indice 514-564. Le bas du viewport est a 551.

| Etat | Capture 744 x 551, barre classique | Capture 720 x 551, sans barre |
|---|---|---|
| V arrivee | `07-i17-r2-arrivee-744.png` | `07-i17-r2-arrivee-720s.png` |
| M arrivee, texte masque | `07-i17-r2-masque-arrivee-744.png` | `07-i17-r2-masque-arrivee-720s.png` |
| E erreur | `07-i17-r2-erreur-744.png` | `07-i17-r2-erreur-720s.png` |
| EM erreur, texte masque | `07-i17-r2-masque-erreur-744.png` | `07-i17-r2-masque-erreur-720s.png` |
| R revelation | `07-i17-r2-revelation-744.png` | `07-i17-r2-revelation-720s.png` |
| RM revelation, texte masque | `07-i17-r2-masque-revelation-744.png` | `07-i17-r2-masque-revelation-720s.png` |

Les six ordres de propositions servis par le jeu (tires au hasard par le serveur ; 15 rencontres de l'item au total, chaque rencontre jouee jusqu'a une erreur pour relever l'indice) :

| Ordre servi (de gauche a droite et de haut en bas) | Rencontres | Indice rendu apres l'erreur |
|---|---|---|
| "Ajoute du sucre / Casse deux œufs / Mélange bien" | 1 | "Les instructions se lisent dans l'ordre." |
| "Ajoute du sucre / Mélange bien / Casse deux œufs" | 2 | "Les instructions se lisent dans l'ordre." |
| "Casse deux œufs / Ajoute du sucre / Mélange bien" | 4 | "Les instructions se lisent dans l'ordre." |
| "Casse deux œufs / Mélange bien / Ajoute du sucre" | 2 | "Les instructions se lisent dans l'ordre." |
| "Mélange bien / Ajoute du sucre / Casse deux œufs" | 3 | "Les instructions se lisent dans l'ordre." |
| "Mélange bien / Casse deux œufs / Ajoute du sucre" | 3 | "Les instructions se lisent dans l'ordre." |

## K. Ce que ce releve ne montre pas

Meme liste que la section G (un appareil reel, une police reelle, le geste et la lecture d'un enfant, un lecteur d'ecran), plus : l'etat texte masque est construit par moi et n'est pas un etat du produit ; chaque capture montre un ordre de propositions parmi six ; le bas de l'`indice` et de la reponse attendue sont des bas de boite (le jar de cette section a la meme feuille que celui de la section A, l'interligne 1,25 y est). Pour les cotes du pli (en E et en R), c'est le tableau de chaque item qui fait foi ; je ne les juge pas.

## L. Isolation

Un jar (port 18721, PID 52152) et un Chrome (PID 23200, port 19721), arretes par PID, aucun processus arrete par nom d'image. Aucun fichier de `domain`, `application`, `adapters`, `bootstrap` ni aucun gabarit modifie. Le seul fichier modifie est celui-ci, sur la branche `s57-ines-r2` (worktree `recreo-ines-r2`) ; aucune fusion, aucun push. `mvn -B verify` n'est pas lance : je n'annonce pas la porte technique.

## Decisions prises (suite du releve)

**Aucune.** Cette section ne cree ni n'amende aucune `ADR` ni `DEC`, ne leve aucune reserve, ne prononce aucun Done et ne cree aucun identifiant.

## Amendements du 2026-10-21, 18:30 (UTC+02:00) : ecarts 26 a 30 du releve de cloture du palier 3

- **Auteure** : `ines-design`. Source : `releve-relecteur-cloture-palier-3.md`, section 3.4, ecarts 26 a 30. Le texte des sections A a L n'est pas reecrit, sauf l'ecart 26 ci-dessous. Aucun identifiant nouveau, aucun Done, aucune decision.
- **Ecart 26 (corrige en place, seul endroit ou l'original change)** : le prefixe de dossier de session de poste, repete 169 fois, est supprime ; les lignes 8 et 405 definissent une fois le **dossier de captures `r1-releve/`** (nom stable, sans chemin de session) ; chaque capture est ensuite citee par son seul nom de fichier. Les 169 noms de fichier sont inchanges.
- **Les captures aujourd'hui** : 169 PNG au total, soit les 133 de la section A et les 36 de la section J (`07-i05`, `07-i09`, `07-i17`, 3 items x 6 etats x 2 largeurs), tous dans le meme dossier `r1-releve/` du repertoire de travail de la session, qui est un dossier temporaire du poste, hors depot. Taille mesuree par `ls -l` : **6 923 455 octets (6,6 Mio)** pour 169 fichiers, 40 967 octets en moyenne, 61 762 au plus. Elles disparaissent avec le nettoyage du dossier temporaire ; aucun fichier du depot ne les contient.
- **Pour qu'elles survivent** : les copier dans le depot ajoute 6,6 Mio de binaires definitifs a l'historique (chaque clone les recoit, aucune suppression ulterieure ne les retire de l'historique). Ne pas les copier garde le depot leger et laisse les tables sans pieces : seuls les chiffres et le texte DOM de ce fichier restent verifiables. Le choix n'est pas le mien ; il va a `sofia-sm`.
- **Ecart 27** : ligne 388 (section H), "celle de `ad9ef86d` est celle que le coordinateur a etablie" se lit : celle de `ad9ef86d` est celle que `sofia-sm` a etablie, `rapport-sofia-dev-palier-3.md` section 13.
- **Ecart 28** : ligne 401 (section Decisions prises, suite), l'arbre `ea5d9429` contient `afd33e88`, qui est la **fusion** ; la correction des items 5, 9 et 17 est `ac986aca`.
- **Ecart 29** : ligne 106, "dans le texte", "le texte dit" se lit : "dans le texte". Les 8 indices de `-07` concernes (items 1, 2, 3, 5, 6, 7, 9, 11) disent "dans le texte" ; aucun ne dit "le texte dit" (`verdict-nadia-palier-3-ce1.md`, section 4.3). Le chiffre 8 est inchange.
- **Ecart 30** : dans les deux tableaux de la section E, "avant" et "apres" designaient le voisin dans un sens dans le tableau detaille et dans le sens inverse dans les totaux. Lecture corrigee, valable pour les deux : le chevron ouvrant est **suivi de** U+00A0 (24 occurrences dans le texte, 14 dans l'indice) ; le chevron fermant est **precede de** U+00A0 (24 et 14) ; ":" et "?" sont **precedes de** U+00A0 (14 et 96 dans le texte, 8 et 8 dans l'indice). Aucun chiffre ne change. Ligne 381 : "altérée" se lit "alteree".
