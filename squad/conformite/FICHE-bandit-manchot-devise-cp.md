# Fiche de conformite - bandit-manchot-devise-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-emc-devise.yaml`
  (11 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-devise-cp`, titre servi *"La devise de la France"*,
  consigne servie *"Lis bien, puis dis la devise, ou choisis le bon exemple."*,
  `nombreDeManches: 10`.
- **Ligne declaree** : `EMC-CP-09`.
- **Statut global** : **VALIDE SOUS RESERVE**. Done pedagogique **prononce le 2026-09-23** par
  `nadia-prof`, apres avoir joue le jar servi. Une reserve, non bloquante, listee en section 3.
- **Arbitrage prealable** : `ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-
  civique-et-ma-borne.md`, ligne 235 - "la moitie exemple est ou EMC-5 mord le plus fort [...] la
  ligne ferme, mais elle coute cher a verifier". Referentiel : `squad/programme/enseignement-moral-
  et-civique/competences-cp.md`, ligne 9.
- **Derniere mise a jour** : 2026-09-23 (premier audit, s40).

---

## 1. Ce que j'ai joue

Reacteur construit (`mvn -B clean package -DskipTests` via `bash outils/mvn-jdk21.sh`, worktree
`s40-nadia-2` a `652cafc8`), `bootstrap/target/recreo.jar` lance sur le JDK 21 designe par la
toolchain. Partie complete de dix manches jouee jusqu'a l'ecran de fin (10 reussites sur 10),
l'item de restitution vu (*"Quelle est la devise de la France ?"*) et les trois familles d'exemple
vues (liberte, egalite, fraternite).

## 2. `EMC-5` - verifie item par item, comme annonce par l'arbitrage prealable

Les onze items relus un a un. Sur chacun des dix items d'exemple, les deux propositions ecartees
sont des actions du quotidien scolaire **sans rapport avec aucune des trois valeurs** (colorier,
ranger, compter, ouvrir un cahier, attacher ses lacets, ecrire son prenom...) - jamais une action
qui illustrerait une autre des trois valeurs ou la meme valeur sous un autre angle defendable.
Verifie specifiquement les trois items d'egalite (5 a 7), les plus exposes au risque parce que
l'egalite se lit facilement dans un partage ou une entraide qui evoquerait aussi la fraternite :
*"tous les eleves suivent la meme regle"*, *"les filles et les garcons font le meme sport"*,
*"chaque eleve recoit le meme nombre de feuilles"* portent chacun un marqueur d'identite stricte
(meme regle, meme activite, meme quantite) qui ne se confond pas avec l'entraide de la fraternite.
**`EMC-5` est tenu sur les onze items.**

## 3. Reserve non bloquante : le verbe *"illustre"*

**Constat.** Dix des onze enonces posent la question *"Quel exemple illustre [la valeur] ?"*. Le
verbe *illustrer* est un mot de registre plus soutenu que le vocabulaire habituel d'un enonce de
CP dans ce depot - compare aux enonces deja valides du meme niveau et de la meme matiere
(`cp-emc-regles-de-vie.yaml` : *"Que doit faire..."*, *"Pourquoi..."*), qui emploient des verbes
concrets et frequents. Un enfant de CP qui lit encore peu ne rencontre pas ce mot dans son
vocabulaire courant de premiere annee.

**Objet.** *L'enfant lit seul l'enonce et comprend ce qu'on lui demande.* `illustre` ne bloque pas
la comprehension globale - la question reste courte (`LON-1`, `LON-2` tenues, aucun enonce ne
depasse une phrase de question), et le motif de l'enonce (choisir un exemple parmi trois pour une
valeur nommee) devient vite previsible par repetition sur le paquet - mais c'est un mot que ce
depot n'a encore employe sur aucun autre paquet de CP, et je ne le laisse pas s'installer comme
motif sans le signaler.

**Correction demandee, pas bloquante** : remplacer *illustre* par un verbe concret et deja rencontre
en CP dans ce depot - par exemple *montre* (*"Quel exemple montre la liberte ?"*). Le mot n'apparait
que dans l'enonce des dix items d'exemple (items 2 a 11) ; aucune proposition ni indice n'est
concerne.

**Pourquoi ce n'est pas un refus.** Le mot est unique, repete a l'identique (pas de variation qui
imposerait de le redeviner a chaque item), la question reste courte et previsible, et rien de ce
paquet ne desserre `EMC-5`, `EMC-1` ou `TYP-1`. Refuser un paquet entierement solide pour un seul
mot repete serait le caprice que je m'interdis - la reserve est ecrite, datee, et vaudra pour tout
paquet de CP a partir d'ici.

## 4. `EMC-1`, `TYP-1`, `ADR-0086` clause 1 - verifies

- **`EMC-1`** : aucun exemple ne demande a l'enfant une situation tiree de sa propre vie - chaque
  exemple met en scene une situation generale de classe ou de cour.
- **`TYP-1`** : chaque enonce pose l'espace insecable devant son "?" final, verifie a l'ecran.
- **`ADR-0086` clause 1** : un seul item de restitution (*"liberte, egalite, fraternite"*), la
  clause ne s'applique qu'a partir de trois items partageant la meme bonne reponse.

## 5. Ancrage sur `EMC-CP-09`, exactitude, ton

La devise elle-meme (*"liberte, egalite, fraternite"*) est restituee exactement. Les dix exemples
sont chacun rattaches sans ambiguite a une seule des trois valeurs (verifie section 2). Aucun biais
de genre (l'exemple de l'egalite "filles et garcons font le meme sport" traite les deux
symetriquement, sans stereotype), aucun ton infantilisant.

## 6. Ce dont je reste la seule garde

`EMC-5` sur les dix items d'exemple (section 2) et le jugement de lisibilite du vocabulaire
(section 3) ne sont verifies par aucun garde-fou automatique. C'est moi qui les tiens, fait ce
jour.

---

## Bloc de validation

> **Done pedagogique : VALIDE SOUS RESERVE.**
>
> **Prononce le 2026-09-23 par `nadia-prof`, apres avoir joue le jar servi.**
>
> **Ce que j'ai verifie** : les 11 items, `EMC-5` (tenue sur les dix items d'exemple), `EMC-1`,
> `TYP-1`, `ADR-0086` clause 1, l'exactitude de la devise et des exemples, l'ancrage sur
> `EMC-CP-09`, l'absence de biais.
>
> **Reserve, non bloquante** : remplacer le verbe *illustre* (dix enonces, items 2 a 11) par un
> verbe concret deja rencontre en CP dans ce depot (*montre*, par exemple). Detail en section 3.
>
> **Ce que `camille-po` peut faire** : passer `EMC-CP-09` a `couvert-valide` dans
> `MATRICE-couverture.md`, avec la reserve ci-dessus versee au backlog. Je ne touche pas la
> matrice.
