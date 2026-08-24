# Proposition - items du paquet "cm1-antonymes" (S19-1)

- **Auteur** : marc-dev.
- **Repond a** : `S19-1` (`docs/backlog/backlog.md`), `FICHE-bandit-manchot-antonymes-cm1.md`
  (`squad/conformite/`), arbitrage prealable de `nadia-prof` requis avant tout code de contenu
  (`planning.md`, sequencement, point 3).
- **Ce que ce document fait** : il propose des couples d'antonymes candidats, leurs phrases
  porteuses, leurs distracteurs et leurs indices. **Aucun paquet YAML n'est cree, aucune entree de
  catalogue n'est ecrite.** Le code de contenu attend le retour de `nadia-prof` sur ce document.
- **Ce que ce document ne tranche pas** : la justesse pedagogique de chaque antonyme, la
  profondeur des phrases pour un CM1, et la lisibilite du jeu comme distinct de "Les synonymes"
  restent le jugement de `nadia-prof`, seule autorite.

## 1. Reponses aux quatre questions que la fiche annonce

**Nombre d'items proposes : 18**, au-dessus du plancher de 16 (`FICHE-bandit-manchot-antonymes-
cm1.md`), pour laisser une marge si `nadia-prof` en retire un ou deux au jugement, comme elle l'a
fait sur "Les synonymes" (retrait de quatre items a l'audit, paquet final a 16).

**Registre de vocabulaire vise pour un CM1** : le meme registre que "Les synonymes"
(`cm1-synonymes.yaml`, deja arbitre et joue) - des adjectifs et des verbes d'usage courant
(chaud/froid, lourd/leger, monter/descendre), jamais un mot de registre soutenu ou une notion
abstraite (je n'ai pas propose "genereux/avare" ni "optimiste/pessimiste", qui demandent une
maturite de CM2 pour etre manipules sans ambiguite). Le vocabulaire est volontairement distinct de
celui deja cible par les mots de "Les synonymes" (aucun des dix-huit mots cibles ci-dessous ne
reprend un mot cible de `cm1-synonymes.yaml`), pour eviter qu'un enfant associe un mot a une seule
mecanique de jeu plutot qu'a une relation de sens.

**Ce que je fais des antonymes qui ne sont pas symetriques (un mot a plusieurs contraires selon le
sens)** : je resous cela exactement comme "Les synonymes" resout la polysemie (SYN-2/SYN-3) - la
phrase de contexte fixe un seul sens, donc un seul antonyme accepte. L'exemple le plus net de ma
liste est l'item 3 : "« claire »" dans "Cette piece est claire le matin" ne peut se lire que comme
"qui recoit de la lumiere", dont le seul contraire est "sombre" - jamais "confuse", qui est le
contraire de "claire" dans un tout autre sens ("une explication claire"). Le contexte "piece" et
"le matin" ecarte ce second sens avant meme que l'enfant ne lise les propositions.

**Comment un item d'antonyme se distingue sans ambiguite d'un item de synonyme pour un enfant qui
vient de jouer l'autre jeu** : par deux signaux distincts, sans changement de mecanique. Le titre
de l'entree de catalogue ("Les antonymes" propose, a confirmer par `camille-po`/`theo-archi`)
differe des l'ecran d'accueil. La consigne differe dans son verbe meme : celle des synonymes
demande "quel mot peut **remplacer** le mot entre guillemets **sans changer le sens**"
(`ADR-0052`) ; je propose pour les antonymes "Quel mot veut dire le **contraire** du mot entre
guillemets ?" - le mot "contraire" est la seule difference de forme necessaire, et elle porte a
elle seule toute la distinction de tache. Aucun autre mecanisme de rendu ne change (memes
guillemets francais, memes espaces insecables, meme structure de phrase) : c'est deliberement la
consigne, seule, qui porte la difference, sur le modele deja pose par `ADR-0052`.

## 2. Dix-huit items candidats

Format : enonce (mot cible entre guillemets francais), bonne reponse, deux distracteurs, indice.
Les espaces insecables a l'interieur des guillemets (SYN-9) seront ajoutes a l'ecriture du YAML
final ; ce document reste en espaces ordinaires pour rester lisible en revue.

1. Cette soupe est encore « chaude ».
   - Bonne reponse : froide
   - Distracteurs : salee, epaisse
   - Indice : C'est la sensation contraire de la chaleur, comme un glacon.

2. Ce coureur est tres « rapide ».
   - Bonne reponse : lent
   - Distracteurs : souple, discret
   - Indice : C'est le contraire de la vitesse : on met beaucoup de temps a arriver.

3. Cette piece est « claire » le matin.
   - Bonne reponse : sombre
   - Distracteurs : humide, silencieuse
   - Indice : C'est ce qui arrive quand on ferme les volets : il n'y a plus de lumiere.

4. Ce sac est « lourd ».
   - Bonne reponse : leger
   - Distracteurs : casse, colore
   - Indice : C'est ce qu'on ressent quand on porte une plume plutot qu'une pierre.

5. Ce verre est « plein » d'eau.
   - Bonne reponse : vide
   - Distracteurs : propre, chaud
   - Indice : C'est l'etat d'un verre qui ne contient plus rien.

6. Lea doit « ouvrir » la fenetre avant de partir.
   - Bonne reponse : fermer
   - Distracteurs : peindre, nettoyer
   - Indice : C'est le geste qu'on fait pour empecher l'air d'entrer.

7. Le chat va « monter » sur le toit.
   - Bonne reponse : descendre
   - Distracteurs : courir, dormir
   - Indice : C'est le mouvement inverse, vers le bas.

8. Le film va « commencer » dans cinq minutes.
   - Bonne reponse : finir
   - Distracteurs : durer, plaire
   - Indice : C'est ce qui arrive a la toute derniere minute d'un film.

9. Notre equipe va « gagner » le match.
   - Bonne reponse : perdre
   - Distracteurs : arbitrer, applaudir
   - Indice : C'est ce qui arrive a l'equipe qui n'a pas marque assez de points.

10. Ce mur est tres « haut ».
    - Bonne reponse : bas
    - Distracteurs : solide, peint
    - Indice : C'est la hauteur contraire : tout pres du sol.

11. Ce pain est devenu « dur ».
    - Bonne reponse : mou
    - Distracteurs : sucre, chaud
    - Indice : C'est ce qu'on ressent au toucher quand une chose s'ecrase facilement.

12. Cette route est « large ».
    - Bonne reponse : etroite
    - Distracteurs : droite, boueuse
    - Indice : C'est ce qu'on dit d'un passage ou deux voitures ne peuvent pas se croiser.

13. Les hiboux dorment le « jour ».
    - Bonne reponse : nuit
    - Distracteurs : matin, apres-midi
    - Indice : C'est le moment ou le soleil est couche.

14. Lea est arrivee « premiere » a la course.
    - Bonne reponse : derniere
    - Distracteurs : fatiguee, contente
    - Indice : C'est la position de celui qui arrive apres tous les autres.

15. Ce quartier est tres « silencieux » la nuit.
    - Bonne reponse : bruyant
    - Distracteurs : eclaire, anime
    - Indice : C'est ce qu'on dit d'un lieu plein de bruit.

16. Le clown est toujours « joyeux » avec les enfants.
    - Bonne reponse : triste
    - Distracteurs : bruyant, maladroit
    - Indice : C'est ce qu'on ressent quand on a envie de pleurer plutot que de rire.

17. Ce chien est « vieux ».
    - Bonne reponse : jeune
    - Distracteurs : obeissant, marron
    - Indice : C'est ce qu'on dit d'un animal qui vient de naitre il y a peu de temps.

18. Le nouvel eleve est « poli » avec la maitresse.
    - Bonne reponse : impoli
    - Distracteurs : timide, curieux
    - Indice : C'est ce qu'on dit de quelqu'un qui ne dit ni bonjour ni merci.

## 3. Points que je signale sans les trancher

- **Item 3** est le cas d'ecole d'antonyme non symetrique (section 1). Si `nadia-prof` le juge
  trop fin pour un CM1, je propose de le retirer plutot que de le simplifier : simplifier la
  phrase risquerait de rouvrir l'ambiguite qu'elle est censee fermer.
- **Item 13** ("jour"/"nuit") est le seul couple de noms de la liste, les dix-sept autres etant des
  adjectifs ou des verbes. Je le garde parce que la competence du referentiel ne restreint pas la
  classe grammaticale, mais je le signale au cas ou `nadia-prof` prefererait une liste
  grammaticalement homogene pour ce premier jeu d'antonymes.
- **Consigne proposee** ("Quel mot veut dire le contraire du mot entre guillemets ?") : c'est une
  proposition de developpeur, pas une decision. Le libelle final reste a confirmer par
  `camille-po`/`theo-archi` au moment de l'ecriture du catalogue, une fois l'arbitrage rendu.
- **Titre de jeu propose** ("Les antonymes") : meme reserve, simple proposition de coherence avec
  "Les synonymes".
