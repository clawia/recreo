# Rapport de qualite fautif

Ce fichier ne definit aucune condition et n'en emploie aucune : il ne fait que **citer**, sous la
forme exacte d'un rapport de qualite reel, le message d'erreur d'une mutation qu'il a mesuree. La
clause 2 le refuse quand meme, et c'est l'amendement du 2026-09-08 qui le decide : un contexte
textuel n'exempte rien, parce que c'est exactement l'endroit ou un identifiant reellement non alloue
passerait sans bruit.

Mutation : un identifiant fictif jamais alloue ajoute a la fin d'un paquet. **Resultat : ROUGE.**

```
squad/conformite/ARBITRAGE-fautif.md ecrit l'identifiant ZZZ-7, que
squad/decisions/REGISTRE-identifiants.md n'alloue pas.
```

Restauration, tree propre confirme, reexecution : **VERT**.
