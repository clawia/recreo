# Arbitrage fautif

`ZZZ-1` - une condition allouee et ecrite ici, comme il faut.

`ZZZ-2` - une condition allouee deux fois par le registre, ce que la clause 1 refuse.

`ZZZ-9` - une condition ecrite sans etre allouee, ce que la clause 2 refuse.

`ZZZ-CP-3` - une condition a **trois segments**, ecrite sans etre allouee. Ce depot n'a aucun
referentiel de `squad/programme/`, donc ce code n'est pas non plus un code de ligne d'ADR-0081 :
personne ne le possede, et la clause 2 le refuse. Avant ADR-0088 clause D, ce fichier passait vert -
le motif d'identifiant n'examinait aucune forme a trois segments, donc ce fautif-ci n'etait meme pas
lu.
