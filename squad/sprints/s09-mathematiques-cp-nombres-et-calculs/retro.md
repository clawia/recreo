# [Sprint s09] Retrospective - 2026-08-12

- **Anime par** : sofia-sm

## Ce qui a marche

- **Trois refus pedagogiques ont ete corriges le meme jour, chacun sur la correction exacte que
  `nadia-prof` avait ecrite dans son propre verdict de refus.** Aucun des trois n'a demande un
  second aller-retour de formulation : `marc-dev` a livre "Leve" -> "Lève" et "baisse-en" ->
  "baisses-en" au caractere pres, `sofia-sm` (casquette dev) a livre les deux enonces "de plus" au
  mot pres, `lea-dev` a retire le seul mot "juste" que le refus nommait. Un refus qui borne sa
  propre correction avant que le developpeur ne commence se leve en un geste, pas en negociation.
- **Un garde-fou a ete mute sur du contenu de production reel, pas seulement sur ses fixtures.**
  `yanis-qa` a duplique un item existant de `cp-comparer-nombres.yaml` pour prouver que
  `ADR-0032` mord au-dela des cinq cas ecrits pour l'`ADR` lui-meme - c'est la meme doctrine de
  preuve de morsure qui a deja tenu au sprint 8, rejouee sur un objet different.
- **`nadia-prof` a corrige quatre de ses propres chiffres avant de s'en servir**, dans les deux
  sens : une faute de plus qu'elle n'en avait comptee (48, pas 47, un participe en bas de casse
  hors de sa recherche capitalisee, trouve par `marc-dev`) ; un item de moins qu'elle n'en avait
  classe (9 items de dizaines, pas 10) ; une forme d'item livree differente de celle qu'on lui
  avait decrite (`10 + ? = 10`, pas `10 + ? = 0`, qu'elle a refuse de juger sur la description
  plutot que sur le fichier) ; et, apres la mutation de `yanis-qa`, la correction de trois fiches
  ou elle affirmait a tort qu'aucun test ne verifiait l'exactitude arithmetique. Aucune de ces
  corrections n'a ete demandee par un tiers avant qu'elle ne les publie.
- **`camille-po` a verse au backlog, avec leur perimetre exact, la totalite des reserves non
  bloquantes des quatre fiches** - jusqu'a la nuance la plus fine (la difficulte constante dans
  une partie est relevee sur deux paquets, sans objet sur un troisieme, non relevee sur le
  quatrieme) - plutot que de les laisser dormir dans des fiches de conformite que personne ne
  relit au hasard.
- **Un obstacle bloquant a ete leve par une decision de catalogue plutot que par un elargissement
  de perimetre pedagogique non demande.** `camille-po`, sur `DEC-0020`, a baisse `nombreDeManches`
  a 7 pour les complements a 10 plutot que d'elargir les formes d'items admises sans mandat de
  `nadia-prof` - et `nadia-prof` a ensuite valide explicitement ce choix en jouant, y compris
  l'asymetrie de duree de partie qu'il assume.

## Ce qui a coince

### 1. Un garde-fou traverse la production sans y examiner un seul indice, et sa clause de
non-vacuite ne peut pas le dire

`AucunIndiceNeContreditUnAutreItemDuMemePaquetTest.aucunIndiceDeProductionNeContreditUnAutreItemDuMemePaquet`
parcourt bien les quatre paquets de production, mais son assertion est enfermee dans un `if
(regle.find())` sur un motif de regle generale non temperee - et un test dedie du meme fichier
(`aucunIndiceDeProductionNeCorrespondALaFormeGeneraleAujourdHui`) epingle ce motif a **zero**
occurrence dans le contenu de production. Le corps de la comparaison ne s'execute donc jamais sur
du contenu reel aujourd'hui. Sa clause de non-vacuite (`ADR-0003`) verifie que la liste des
**paquets** n'est pas vide, jamais qu'un **indice** a ete examine : elle garde la mauvaise
population, et un garde-fou vide qui passe son propre controle de vacuite est pire qu'un
garde-fou absent, parce qu'il se lit comme une garantie. C'est ce garde-fou precis que trois des
quatre fiches de `nadia-prof` citaient comme tenant "une coherence entre items" avant qu'elle ne
verifie elle-meme le fichier de test et ne corrige les trois. Route a `theo-archi` ; le calendrier
de sa correction attend une reponse ecrite de l'utilisatrice.

### 2. Les fiches de conformite se trompaient sur ce qu'elles-memes deja gardaient

`nadia-prof` affirmait, dans les trois premieres fiches du sprint, qu'aucun test ne calculait
l'exactitude arithmetique d'un item. `yanis-qa` l'a refutee par trois mutations reelles (une bonne
reponse changee par paquet, test rouge, restauration), je l'ai verifiee moi-meme dans les fichiers
de test avant d'ecrire cette phrase, et `nadia-prof` a corrige 27 lignes apres avoir lu les
fichiers plutot que de me croire sur parole. Sa propre relecture, en corrigeant, a trouve deux
inexactitudes supplementaires, une dans chaque sens : une propriete qu'elle croyait non gardee et
qui l'est a moitie (la plausibilite d'un distracteur, sur les complements a 10), et le garde-fou
du point 1 ci-dessus, qu'elle creditait d'une coherence qu'il ne tient pas. **Une liste de "ce
dont je reste seule garde" n'a de valeur que si elle est exacte**, et personne ne l'avait
confrontee au code avant ce sprint.

### 3. Quatre agents ont corrige leur propre constat ou celui d'un autre, dans le meme sprint

`nadia-prof` sur trois de ses chiffres (section "ce qui a marche") ; `marc-dev`, qui trouve une
25e faute que le releve capitalise de `nadia-prof` ne pouvait pas voir ; `lea-dev`, qui trouve en
implementant que deux criteres de sa propre story (S9-3) se contredisaient ("deux nombres
distincts" contre "20 % d'egalites exigees") et le remonte plutot que de choisir seul lequel
suivre ; `camille-po`, qui etablit contre ma formulation de planning le perimetre exact du symptome
de difficulte constante (releve sur deux paquets sur quatre, pas sur les quatre comme je l'avais
generalise). Le depot tient parce qu'aucun agent n'accepte un fait sur la seule parole d'un autre,
moi comprise - voir point 4.

### 4. Mes propres erreurs d'etat, deuxieme sprint d'affilee

J'ai transmis a `nadia-prof` un item degenere decrit comme `10 + ? = 0` (aucune solution entiere,
aurait valu un refus immediat), alors que le paquet livre porte `10 + ? = 10` - notation reprise
d'un rapport amont sans la verifier contre le fichier reel. Elle l'a corrigee en jugeant le fichier
plutot que ma description. J'ai aussi generalise a quatre jeux un symptome de difficulte constante
qui n'en concernait que deux, corrige par `camille-po`. Au sprint 8 deja, cinq de mes affirmations
avaient ete corrigees par les agents qui les avaient verifiees. **Le dispositif tient precisement
parce que personne ne me croit sur parole non plus** : je porte cette regle telle qu'elle est,
sans en attenuer la portee sur mon propre travail.

### 5. Un defaut de poste, deja signale, toujours present

Le fichier de toolchains Maven du poste utilise pour ce sprint reste enregistre sous un nom mal
orthographie et n'est donc jamais lu par Maven. `JAVA_HOME` a du etre pointe a la main sur un JDK
21 pour chaque commande de cette cloture, comme aux sprints 7 et 8. Un agent a mesure sur un JDK 25
avant de rejouer sur le bon JDK, faute d'avoir su qu'un JDK 21 etait installe sur ce poste - deja
signale hors depot au sprint 8, toujours ouvert.

### 6. Trois interruptions techniques, une protegee par la consigne de commit par etapes

Une erreur reseau a coupe une tache apres que ses trois fusions aient atterri mais avant que son
rapport ne soit ecrit ; j'ai verifie l'arbre et la porte technique moi-meme plutot que de faire
recommencer le travail deja produit. La consigne de committer par etapes, deja retenue au sprint 8,
protege a nouveau du travail reel plutot que de le laisser dependre de la duree d'une session sans
incident.

### 7. Une note de securite non confirmee, rapportee sans etre validee

`yanis-qa` declare avoir vu, dans ses sorties d'outil, trois messages pretendant que ses propres
mutations de fichiers de test etaient intentionnelles et ne devaient pas etre restaurees ; il les a
ignores et a restaure par `git checkout` en verifiant l'arbre a chaque fois, ce que j'ai confirme
moi-meme etre propre. L'origine de ces messages n'est pas etablie - ni injection reelle confirmee,
ni avertissement du harnais mal interprete confirme non plus. Je le rapporte comme fait observe
sans le qualifier au-dela : son reflexe (ignorer, restaurer, verifier) etait le bon dans les deux
hypotheses.

### 8. Absence de `confirmation-archunit.md` dedie

A la difference des sprints precedents, `theo-archi` n'a pas ecrit de document de confirmation
post-developpement pour s09 : sa seule trace ecrite sur le compte de regles est son avis de
faisabilite, rendu avant tout code du sprint. Le contenu attendu (une position d'architecture sur
le compte de regles ArchUnit de l'increment livre) est couvert par la convergence de cet avis, du
rapport de `yanis-qa` et de ma propre reverification independante - donc rien n'est faux ni
manquant sur le fond - mais la forme de la ceremonie s'est appuyee sur une prevision plutot que sur
une confirmation posterieure, une premiere sur ce produit. Je ne fabrique pas retroactivement un
document que `theo-archi` n'a pas ecrit ; je le nomme ici pour que la prochaine cloture verifie si
une confirmation posterieure au code est disponible avant de conclure qu'elle n'existe pas.

## Amendements au process

**Aucun amendement a `squad/README.md` ce tour.** Les huit points ci-dessus sont classes sans
regle nouvelle : les points 1 a 4 relevent de la doctrine de preuve de morsure et de verification
croisee deja en vigueur (`ADR-0003`, "un chiffre annonce se reverifie, il ne se relaie pas") et
n'appellent pas un texte de plus, seulement son application continue ; le point 5 est une
anomalie de poste hors perimetre du depot, deja signalee ; le point 6 est un constat de methode
suivi sans regle nouvelle ; le point 7 est une observation non confirmee, rapportee pour tracabilite
et non pour fonder une regle ; le point 8 est une asymetrie de forme entre sprints, a surveiller
sans l'ecrire en regle avant qu'elle ne se reproduise une seconde fois.

## Decisions prises

Aucun `ADR` ni aucune `DEC` n'est cree pendant cette retro, et `squad/README.md` n'est pas amende :

1. **Aucun des huit points de "ce qui a coince" n'appelle un amendement au README** - decision
   explicite, motifs donnes point par point ci-dessus.
2. **Le garde-fou vide sur les indices (point 1) reste route a `theo-archi`**, sans arbitrage de
   calendrier de ma part : la reponse attendue est celle de l'utilisatrice, deja sollicitee.
3. **L'anomalie du fichier de toolchains est reportee a l'utilisatrice**, hors perimetre du depot,
   pour la troisieme fois consecutive.
