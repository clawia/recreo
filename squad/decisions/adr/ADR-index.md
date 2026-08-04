# Index des ADR

Une ligne par decision d'architecture. Numeros jamais reutilises.

| Reference | Titre | Sprint | Date | Statut |
|---|---|---|---|---|
| [ADR-0001](ADR-0001-decoupage-en-modules-maven.md) | Decouper le build en cinq modules Maven et orienter les dependances vers l'interieur | s00 | 2026-08-04 | Accepte |
| [ADR-0002](ADR-0002-purete-du-domaine-et-de-application.md) | Interdire toute dependance externe dans `domain` et dans `application` | s00 | 2026-08-04 | Accepte |
| [ADR-0003](ADR-0003-prouver-que-chaque-regle-archunit-mord.md) | Prouver que chaque regle ArchUnit mord, par un contre-exemple qui doit la faire echouer | s00 | 2026-08-04 | Accepte |
| [ADR-0004](ADR-0004-decoupler-la-mecanique-du-contenu.md) | Decoupler la mecanique du contenu par deux contrats fermes et une regle bidirectionnelle | s00 | 2026-08-04 | Accepte |
| [ADR-0005](ADR-0005-abandonner-ruleset.md) | Abandonner `RuleSet` et interdire les abstractions fantomes | s00 | 2026-08-04 | Accepte |
| [ADR-0006](ADR-0006-la-difficulte-est-portee-par-le-paquet-de-contenu.md) | Supprimer la configuration de difficulte du domaine et la porter par le paquet de contenu | s00 | 2026-08-04 | Accepte |
| [ADR-0007](ADR-0007-un-seul-chargeur-de-contenu-pilote-par-un-schema.md) | Charger tout le contenu avec un seul chargeur pilote par un schema | s00 | 2026-08-04 | Accepte |
| [ADR-0008](ADR-0008-le-comportement-de-jeu-vit-sur-le-serveur.md) | Loger tout le comportement de jeu sur le serveur et brider le JavaScript par un budget | s00 | 2026-08-04 | Accepte |

## Lecture rapide

Les huit ADR du sprint 0 forment un socle indissociable, dans cet ordre de lecture :

1. **ADR-0001** dit ou vit le code (cinq modules) ;
2. **ADR-0002** dit ce que le coeur n'a pas le droit de connaitre (liste blanche, pas liste noire) ;
3. **ADR-0003** dit comment on sait qu'une regle mord (contre-exemple obligatoire) - c'est lui qui
   donne leur valeur aux sept autres ;
4. **ADR-0004** tient le principe directeur du produit : mecanique et contenu s'ignorent, dans les
   deux sens ;
5. **ADR-0005**, **ADR-0006**, **ADR-0007** tranchent trois dettes nommees de la version
   precedente : l'abstraction fantome, la difficulte arithmetique imposee a toutes les matieres,
   les neuf chargeurs ;
6. **ADR-0008** ramene le comportement de jeu la ou il est testable.
