# Arbitrage préalable - s51, instance A : la forme d'item `c`/`ç` de `FRA-CE1-22`, et les jetons d'adresse de `SCI-2`

`nadia-prof`, le 2026-10-06. Réponse à la question R2 de `theo-archi` (amendement A1 d'`ADR-0065`,
commit `c2802a32`) et aux questions Q1 et Q2 de l'avis de faisabilité de s51
(`squad/sprints/s51-trois-instances-francais-sciences-maths/avis-faisabilite.md`, section 7).

Audit sans jeu : aucun paquet de `FRA-CE1-22` n'existe encore. J'ai lu `GRL-CE1-1` à `GRL-CE1-3`
(`ARBITRAGE-PREALABLE-s48-francais-ce1.md`, section 5.13), l'amendement A1 et sa fixture
`contreexemple/AucunMotServiNeSEcarteDeSonAccentTest/cedille/contenu/ce1-graphie-cedille.yaml`, et la
fiche `FICHE-bandit-manchot-valeur-lettre-ce1.md` comme repère.

## 1. Décision : A1 tient `GRL-CE1-2`, la forme *un garçon* / *un garcon* est maintenue

**Une correction d'abord, sur mon propre texte.** Le 2026-10-03 (s48), j'ai écrit dans `GRL-CE1-2`
l'exemple *un garçon* / *un garcon* sans vérifier qu'`ADR-0065`, en vigueur depuis le 2026-08-30, refusait *garcon*.
La condition que j'ai posée n'était donc pas servable par construction, et je ne l'ai pas vu. C'est
la mesure de `theo-archi` qui l'a trouvé, pas moi.

**A1 tient `GRL-CE1-2` telle que je l'ai écrite.** `GRL-CE1-2` demande deux propositions, le même mot
écrit deux fois, ne différant que par la graphie que la règle décide. Pour `c`/`ç`, cette graphie est
la cédille, et A1 admet exactement cela : même longueur, une différence au moins, et à chaque
différence `ç` dans la bonne réponse et `c` dans le distracteur. Je ne change pas la forme : la barre
est la mienne, l'équipe l'atteint, je ne la déplace pas.

**J'accepte qu'un enfant de CE1 lise par construction un mot sans cédille dans un distracteur.**
Motifs :

1. C'est la nature de toute la ligne et de ses voisines. `GRL-CE1-2` fait déjà lire *une janbe*,
   `MIV-CE1-2` fait lire *souvant*, `FRA-CE1-09` montre une phrase fautive. En orthographe, choisir
   entre la forme juste et la forme fautive posées côte à côte est la pratique ordinaire de la classe.
2. *garcon* est le distracteur le plus plausible qui soit : c'est l'erreur que fait réellement un
   élève de CE1. Et c'est la compétence de la ligne elle-même, *"selon la lettre qui suit"* : devant
   `o`, un `c` sans cédille se lit comme dans *car*.
3. L'exposition est bornée par la forme : la forme fautive n'apparaît qu'une fois, à côté de la forme
   juste, dans un choix. A1 la refuse partout ailleurs (énoncé, indice, explication, bonne réponse).
4. Les deux propositions ont la même longueur : les items `ç` ne nourrissent pas le biais de longueur
   que `TRV-CE1-2` compte sur le paquet.

**Je refuse la forme *gar_on* avec les propositions `c` / `ç`.** Elle fait lire un mot troué, qui
n'est pas plus juste que *garcon*. Elle introduirait une deuxième forme d'item dans un paquet dont
les règles du `m` et de `g`/`ge`/`gu` restent en mots entiers : l'enfant devrait lire deux consignes
différentes pour une seule compétence. Et elle remplacerait l'exemple que j'ai écrit, ce qui serait
déplacer ma propre barre sans motif.

**Je ne demande aucune extension d'A1.** La cédille seule me suffit. *un zéro* / *un zero* reste
refusé, et je n'ai aucune ligne qui demande un distracteur privé d'accent.

## 2. Conditions opposables aux items `c`/`ç` de `FRA-CE1-22`

Elles précisent `GRL-CE1-2` pour la cédille, datées du 2026-10-06. Elles ne créent pas d'identifiant
neuf : on les cite comme *"section 2, point n, de ce fichier"*. `GRL-CE1-1`, `GRL-CE1-3`,
`TRV-CE1-2`, `TRV-CE1-3`, `TRV-CE1-5` et `LON-CE1-5` restent opposables telles quelles.

1. **Sens unique.** La bonne réponse porte `ç` devant `a`, `o` ou `u`. Le distracteur est la même
   chaîne, déterminant compris, avec `c` à la place de `ç`, rien d'autre. Aucun item où la bonne
   réponse porte `c` et le distracteur `ç` (*une çerise*, *un çamion*) : ce distracteur n'est pas une
   erreur d'élève, c'est une devinette. Le `ç` n'est jamais écrit devant `e` ou `i`, comme pour
   `VLE-CE1-3`.
2. **Le distracteur n'est pas un mot.** La forme sans cédille ne doit être aucun mot français juste,
   sinon l'item aurait deux réponses acceptables (`TRV-CE1-2`).
3. **Mots fréquents.** Le mot cible est un mot que l'enfant de CE1 rencontre en classe. Exemples
   recevables : *un garçon*, *une leçon*, *un glaçon*, *un maçon*, *une balançoire*, *le français*,
   *un reçu*, *déçu*. Un autre accent du mot (*déçu*) reste identique dans les deux propositions.
   Liste indicative, pas fermée.
4. **Le mot cible n'est écrit ni dans l'énoncé ni dans l'indice**, sous aucune forme. Avec cédille, il
   donnerait la réponse ; sans cédille, A1 le refuse déjà.
5. **Indice** : une méthode, pas la réponse, douze mots au plus (`TRV-CE1-5`), et une règle vraie. Le
   son se désigne par un mot de référence (`TRV-CE1-3`). Exemple recevable : *"Regarde la lettre qui
   suit le c."* Refusé : tout indice qui nomme *cédille* et la place dans le mot cible, car il donne la
   réponse.
6. **Explication** : elle redonne le mot avec sa cédille. L'enfant ne quitte pas la manche avec
   *garcon* comme dernière forme écrite sous les yeux (reprise de `TRV-CE1-5`).

**Ce que le garde-fou voit, et ce que je garde seule.** A1 et `ADR-0065` vérifient le point 1 (sens
unique, et une bonne réponse *çerise* est refusée par le lexique) et l'absence de la forme sans
cédille hors du distracteur (point 4 pour cette forme-là, point 6 pour l'explication). **Aucun test ne
voit** : le point 2 (distracteur qui serait un mot juste), le point 3 (fréquence), le mot cible écrit
*avec* cédille dans l'énoncé ou l'indice (point 4), l'indice qui donne la réponse (point 5), et le
quart de `GRL-CE1-1` tant que lea-dev ne l'asserte pas. Leçon de `FER-1` sur `FRA-CE1-02` : un `ç`
annoncé et non servi n'a été vu que par moi. Sur ces points, je reste la seule garde, en jouant.

**Ce que `theo-archi` doit faire : rien retirer.** A1 reste, avec ses deux clauses
(`leDistracteurSansCedilleNEstAdmisQueDansLaFormeDeGrlCe1Deux` et
`laFormeDeGrlCe1DeuxSeJugeSurLesDeuxPropositionsEntieres`) et sa fixture. La question Q1 de l'avis est
levée pour V1.

## 3. Q2 : les jetons d'adresse pour un test de `SCI-2`

`SCI-2` (`ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md`, section 2.5) demande que l'item soit écrit à la troisième
personne ou à l'impersonnel, sans responsabilité portée par l'enfant ou par son foyer. Je fixe la liste
maintenant.

**Jetons refusés**, en mot entier, sans distinction de casse, sous `UNICODE_CHARACTER_CLASS` :

- deuxième personne : *tu*, *te*, *toi*, *ta*, *ton*, *tes*, et l'élision *t'* (un `t` seul suivi
  d'une apostrophe droite ou typographique) ;
- deuxième personne du pluriel : *vous*, *votre*, *vos* ;
- première personne du pluriel : *nous*, *notre*, *nos*. *"Nos déchets"* met l'enfant dans le foyer
  en cause : ce n'est ni la troisième personne ni l'impersonnel.

*on* n'est pas refusé : c'est l'impersonnel que `SCI-2` admet.

**Champs** : tout texte que l'enfant lit, soit énoncé, propositions, indice, explication, et
`texteSupport` si l'item en porte un.

**Population** : les items de `SCI-CM1-16` et `SCI-CM1-18`, et elles seules. C'est le périmètre de
`SCI-2`. Je ne l'étends pas à d'autres lignes de sciences par ce document. Un faux rouge sur un
homonyme rare (*le ton*, *il s'est tu*) se corrige en réécrivant l'item : je ne veux pas d'exemption.

**Ce que ce test ne verra pas, et que je garde seule** : l'impératif sans pronom (*"Trie les
déchets."*), l'injonction impersonnelle (*"il faut"*, *"on doit"* adressés comme une morale), et les
présupposés de logement ou d'équipement (*"à la maison"*, *"la voiture"*) qui relèvent de `BIA-4`.

## 4. Amendement du 2026-10-06 : le point 6 de la section 2 (`nadia-prof`, s51, instance A)

**Le point 6 n'était pas servable avec le point 4, et je ne l'ai pas vu en l'écrivant.** Le garde-fou
des explications (`ChaquePaquetExpliqueSesItemsAVariationTest`, `ADR-0024`) lie toute explication à
une clé `graphie`, et à un énoncé qui contient le mot quand cette graphie diffère de la bonne
réponse ; le point 4 interdit d'écrire le mot dans l'énoncé. Le paquet livré ne porte donc aucune
explication.

**Je ne retire pas ce que le point 6 protège, je corrige sa lettre.** Il se lit désormais : *"Aucune
explication n'est exigée. Si le paquet en porte une, elle redonne le mot avec sa cédille."* Motif,
vérifié au jeu : le gabarit ne sert l'explication qu'avec la révélation, qui affiche déjà la forme
juste seule dans son élément (*"Appuie sur la bonne réponse :"*) ; sur les deux autres chemins,
l'enfant sort de la manche en pressant lui-même la forme juste. L'enfant ne quitte jamais une manche
avec la forme sans cédille comme dernière forme écrite. Détail : `FICHE-bandit-manchot-graphie-ce1.md`,
section 4.

**Ce que cet amendement ne fait pas** : il ne touche ni les points 1 à 5, ni `GRL-CE1-1` à `-3`, et
ne demande rien à `theo-archi`. Le texte de la section 2 n'est pas retouché : il reste lisible tel
que je l'ai écrit plus tôt le même jour.
