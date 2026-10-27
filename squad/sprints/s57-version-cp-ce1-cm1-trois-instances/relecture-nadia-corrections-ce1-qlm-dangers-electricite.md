# s57 - relecture des corrections de `ce1-qlm-dangers-electricite` (`S57-BR-QLM-CE1-08`, `P5`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : debut 2026-10-27 14:49:34 (UTC+01:00), lu par `date` ; fin en section 7.
- **Arbre** : branche `s57-p5-nadia-corr08`, base `round-two` a `0286fde1`, lue par `git`.
- **Lu** : `git diff c8609344 HEAD` sur `adapters/adapter-content/src/main/resources/contenu/ce1-qlm-dangers-electricite.yaml`,
  `PaquetCe1QlmDangersElectriciteTest.java` et `dangers-fautifs.yaml` (correction de `marc-dev`, commit `fd24ca67`, seul commit
  sur le YAML depuis le premier jet), compares a la section 4 (textes) et a la section 6 (gardes `T1` a `T4`) de
  `lecture-nadia-premier-jet-ce1-qlm-dangers-electricite.md`. **Je n'ai pas relu le paquet** : seules les 13 lignes prescrites, les
  gardes ajustees et les sondes 71 et 78 a 94.
- **Type** : relecture des lignes corrigees, avant `verify`. **Aucun Done, aucun verdict de lot**, aucun identifiant neuf, aucune
  condition neuve. Je n'ai modifie ni le paquet, ni les tests.
- **Audit degrade, dit** : rien n'est joue a l'ecran (paquet hors catalogue). Les items corriges sont joues sur le papier.
- **Mesures lancees** :
  - comparaison caractere pour caractere des 13 textes de ma section 4 aux lignes du YAML, et recompte des longueurs et des
    distracteurs negatifs, par script (Python, hors du depot, non commite) ;
  - `bash outils/mvn-jdk21.sh -B -q -pl adapters/adapter-content -am test -Dtest=PaquetCe1QlmDangersElectriciteTest -Dsurefire.failIfNoSpecifiedTests=false` :
    sortie 0, rapport Surefire *tests="54" errors="0" skipped="0" failures="0"*. Perimetre : ce test, module `adapter-content` et
    ses dependances. Ce n'est ni un `verify`, ni la porte technique.

## Resume

| Point | Reponse |
|---|---|
| Conformite a la section 4 | **les 13 textes tiennent** : 12 identiques au caractere, l'enonce de Hugo identique sauf l'espace insecable avant `?`, que ma section 4 demandait (section 1) |
| Perimetre du diff | **seules les lignes prescrites changent** : 13 lignes, toutes dans ma section 4 (section 2) |
| Gardes `T1` a `T4` | **tiennent** (section 3) |
| Comptes de `marc-dev` | **confirmes** (section 4) |
| Raccourci *ne* sur 5 items | **inchange, toujours lu a l'ecran a l'audit** (section 4) |
| Ouverture | **aucune** ; une correction de mon propre constat sur l'echo de l'enonce (section 5) |
| Seconde lecture | faits en section 7 |

## 1. Comparaison a la section 4, element par element

| Point | Element, ligne | Verdict | Motif |
|---|---|---|---|
| 1 | Sami, `:78` : `["réparer la prise avec un tournevis", "ne pas déranger un adulte"]` | **tient** | Identique. *La prise* est dans un distracteur : la bonne reponse n'est plus la seule a la nommer. Le tournevis reste la manipulation qu'un enfant a vu faire |
| 2a | Zoe, `:96` : `# TRV-CE1-2 : b, b` | **tient** | Identique. Les deux distracteurs sont des imprudences (famille b) |
| 2b | Zoe, `:99` : `["brancher la lampe tout de suite", "secouer ses mains puis l'allumer"]` | **tient** | Identique. *Changer l'ampoule*, hors de la scene, est sorti. *Secouer ses mains* est le geste vrai d'un enfant, et faux : l'eau reste. *Les mains* est dans la bonne reponse et dans un distracteur |
| 2c | Zoe, `:100` : `"Pense à l'eau qui reste sur sa peau."` | **tient** | Identique. Montre le danger sans reprendre une proposition ; sert a ecarter *secouer ses mains*. *Eau* est admis dans l'indice de B (`T3`, sonde 85) |
| 3 | Maya, `:106` : `["une lampe branchée loin de l'évier", "une lampe débranchée près de l'évier"]` | **tient** | Identique. Chaque distracteur differe de la bonne reponse par un seul trait. Mots communs aux deux distracteurs : *une*, *lampe*, *de*, *l'évier*, tous dans la bonne reponse ; plus de *loin* commun. Une lampe debranchee pres de l'evier n'est pas un danger electrique : exact |
| 4a | Hugo, `:118` : `"Hugo veut poser la radio branchée au bord de l'évier. Que doit-il faire ?"` | **tient** | Identique, `?` precede de U+00A0. **La phrase de la vaisselle est sortie** : *ne pas deranger un adulte* n'est plus une conduite attendue dans la scene. ***Branchée*** est dans l'enonce : la radio a piles est ecartee, le danger electrique est affirme par la scene. 13 mots, une seule chose a tenir |
| 4b | Hugo, `:120` : `["la poser près du robinet ouvert", "ne rien dire à personne"]` | **tient** | Identique. Hugo n'a rien fait, rien a signaler : *ne rien dire a personne* se lit *le faire en cachette*, un adulte ne l'attend pas. Une des quatre formes admises. Passe `T2` (commence par *ne*, ne nomme pas d'adulte) |
| 4c | Hugo, `:121` : `"Pense à ce qui peut éclabousser la radio."` | **tient** | Identique. Ne depend plus d'une phrase retiree : l'evier suffit. Lieu du danger, sans le geste ; sert a ecarter *pres du robinet ouvert*. Aucun mot refuse par `T3` |
| 5 | Noe, `:127` : `["nettoyer la prise avec un chiffon", "ne pas en parler"]` | **tient** | Identique. Meme motif que Sami. L'indice inchange nomme *la prise*, desormais dans deux propositions : il ne designe plus la bonne reponse par le mot |
| 6a | Adam, `:134` : `["un sèche-cheveux débranché près du lavabo", "un sèche-cheveux branché loin du lavabo"]` | **tient** | Identique. Mots communs aux deux distracteurs : *un*, *sèche-cheveux*, *du*, *lavabo*, tous dans la bonne reponse ; plus de *debranche* commun. Exact |
| 6b | Adam, `:135` : `"Le danger vient de deux choses à la fois."` | **tient** | Identique. Une methode qui fait tenir *branche* et *pres de l'eau* ensemble sans nommer l'etat. Aucun mot refuse par `T3` en A |
| 7a | Nina, `:141` : `["l'utiliser avec les mains mouillées", "tirer sur le fil du mixeur"]` | **tient** | Identique. *Sans plus attendre* est sorti. L'imprudence dite en clair ; *les mains* n'est plus propre a la bonne reponse. Aucun mot commun aux deux distracteurs |
| 7b | Nina, `:142` : `"Pense aux gouttes d'eau sur ses doigts."` | **tient** | Identique. Ne suppose plus un fait que l'enonce ne dit pas ; lieu du danger, ne reprend aucune proposition |

Comparaison par script : chaque texte de la colonne *Texte a ecrire* de ma section 4 contre la ligne nommee, espaces de tete retires.
12 egaux ; l'enonce de Hugo differe par un seul caractere, U+00A0 au lieu de l'espace avant `?`, que ma phrase *"Chaque `?` garde
l'espace insecable"* demandait et que mon tableau ne portait pas. Les 12 enonces du fichier ont U+00A0 avant `?`.

## 2. Perimetre du diff

`git diff c8609344 HEAD` sur le YAML : *"1 file changed, 13 insertions(+), 13 deletions(-)"*, un seul commit (`fd24ca67`). Les 13
lignes : `:78`, `:96`, `:99`, `:100`, `:106`, `:118`, `:120`, `:121`, `:127`, `:134`, `:135`, `:141`, `:142`, exactement celles de
ma section 4. **Rien d'autre ne change** : ni bonne reponse, ni ordre des propositions, ni autre declaration, ni autre item. La
declaration de Nina (`:138`, *b, a*) est inchangee, comme prescrit.

## 3. Les gardes ajustees et leurs sondes

| Repere | Ce que je demandais (section 6) | Verdict | Motif |
|---|---|---|---|
| `T1` | *attendre* ancre au debut, borne `(?!\p{L})` ; sonde 71 refusee, sonde 70 passe | **tient** | `ABSTENTION` lit `^(?:s'eloigner\|attendre\|ne rien toucher\|ne pas s'approcher\|continuer a jouer ailleurs)(?!\p{L})`. La sonde 71 (*"attendre sans rien dire"*) est dans la liste des refusees avec le message *"est une abstention"* ; la sonde 70 (*"attendrir le fil"*) est dans la liste qui passe, la borne l'ecarte. Le paragraphe *"Une lecture a confirmer"* de la Javadoc est retire |
| `T2` | toute proposition qui contient *adulte* sans commencer par *ne* est refusee | **tient** | Remplace `ALERTE` : `adultes?` borne, et refus si la proposition ne commence pas par `^ne(?!\p{L})`. Sondes 89 a 93 refusees : mes trois exemples (*aller voir*, *avertir ... sans attendre*, *attendre un adulte*), la bonne reponse *aller voir un adulte* (92) et *demander à un adulte de ne pas toucher* (93, *ne* hors de tete), qui est le cas que ma forme visait. La sonde 91 est aussi refusee par `T1`. Sonde 94 passe : *ne pas deranger un adulte* et *Ne pas prévenir un adulte.* ; la seconde est une precaution niee, que ce test ne juge pas, et c'est juste : `T2` ne lit que l'alerte |
| `T3` | indice `geste` sans *toucher*, *approcher*, *brancher*, *sécher*, *prévenir*, *adulte* ; indice `danger` sans *abîmé*, *cassé*, *eau* | **tient** | Les deux listes sont mes listes, choisies par la tache declaree. Sondes 78, 79, 80 : mes trois exemples, refuses. Sondes 81 a 83 (*Sèche-toi*, *branche*, *cassée*) refusees ; 84 passe (*sèche-cheveux* n'est pas le geste de secher, juste) ; 85 passe (*eau* dans un indice de B, ma regle). Le paquet corrige passe |
| `T4` | en `danger`, refus d'un mot des deux distracteurs absent de la bonne reponse | **tient** | Sondes 86 et 87 : Maya et Adam du premier jet, a l'identique, refusees sur *[loin]* et *[debranche]*. Sonde 88 refusee sur *[un]* : l'article compte, ce que *par mots* veut dire. Les sondes 54 et 55 (tache geste) passent : la garde ne lit pas B, comme demande. Le paquet corrige passe, donc Jade, Mehdi, Maya et Adam corriges. Le trait hors du mot reste a moi |

## 4. Les comptes de `marc-dev` et le raccourci *ne*

**Distracteurs negatifs** [script] : *ne pas deranger un adulte* 1 (Sami), *ne rien dire a un adulte* 2 (Lea, Ines), *ne rien dire
a personne* 2 (Tom, Hugo), *ne pas en parler* 1 (Noe). **Confirme**, c'est le compte que j'annoncais apres correction. Aucun dans
les deux items des mains.

**Longueur** (`String.length`, en caracteres) [script] : bonne reponse strictement la plus courte dans **3** items (Jade 16, Tom 21,
Sami 23), strictement la plus longue dans **2** (Zoe 33 contre 31 et 32, Ines 30 contre 24 et 24). **Confirme.** Adam est a egalite
avec un distracteur (39), Maya aussi (34) : ni l'un ni l'autre ne compte.

**Le verbe des negatifs designe le bon geste** : toujours **5** items de B sur 8 (Tom, Sami, Lea, Hugo, Noe) ou la bonne reponse
commence par *ne* et le seul autre *ne* est un refus de signaler. La correction de Hugo change la forme (*deranger* devient *ne rien
dire a personne*), pas le compte. **Ma position est inchangee** : je ne corrige rien sur le papier, je le regarde a l'ecran a
l'audit ; s'il s'y voit, ce sera une reserve datee et la regle 3 sera rouverte a ce moment-la. Aucune garde ne le couvre.

## 5. Cette relecture rouvre-t-elle un item ou une formulation ?

**Non.** Aucun item, aucune formulation n'est rouvert ; les 13 textes sont les miens, a l'identique.

**Je corrige un constat de ma lecture du premier jet.** En 3.2, ligne *"Echo de l'enonce dans un distracteur"*, j'ai ecrit que mes
corrections de Zoe et de Nina *"n'en mettent jamais deux"*. Lu a la lettre, c'est faux : chez Zoe, *brancher la lampe* et *ses mains*
reprennent tous deux l'enonce ; chez Nina, *les mains mouillées* et *du mixeur* aussi. Ce que mon script avait verifie est plus
etroit : aucun mot commun aux deux distracteurs que la bonne reponse n'a pas. **Le motif tient sans la phrase** : chez Zoe et Nina, la
bonne reponse reprend elle aussi l'enonce (*les mains*), les trois propositions en portent chacune un mot, et aucune n'est designee
par l'echo. C'etait le but des corrections 2 et 7. Rien ne change dans le fichier.

Rien a reporter par `camille-po`.

**Ce dont je reste la seule garde sur les lignes relues** : la plausibilite de chaque distracteur neuf ; l'exactitude du danger dans
chaque etat (*debranchee pres de l'evier*, *branche loin du lavabo*, *radio branchée*) ; qu'un indice soit une methode ou un lieu du
danger au-dela des deux listes de `T3` ; le trait partage hors du mot en A ; le raccourci *ne* de la section 4. **Aucune n'est
couverte par un test.**

## 6. Ce que cette relecture ne fait pas

Elle ne relit aucun item hors des sept prescrits, ne pose aucune correction, ne prononce aucun Done et n'ecrit aucun identifiant.

## 7. Duree et faits pour le classement

Mesuree par `date` : debut **2026-10-27 14:49:34**, ecriture de ce fichier 14:56:34, fin **14:59:36**, avant le commit.
**Duree : 10 min 2 s**, dont la mesure du test du paquet (14:54:38 a 14:55:50), celle du garde-fou des identifiants
(`bash outils/mvn-jdk21.sh -B -q -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest -Dsurefire.failIfNoSpecifiedTests=false` :
sortie 0, rapport Surefire *tests="10" errors="0" skipped="0" failures="0"*, ce fichier present dans l'arbre ; il n'ecrit aucun
identifiant neuf) et `.github/scripts/verifier-chemins.sh`, entree standard fermee : *"Aucun chemin de poste de travail ni nom de
compte dans les fichiers verifies."* Aucun autre job de `.github/workflows/ci.yml` lance.

**Faits pour le classement par `camille-po`** : cette relecture ne porte que sur les 13 lignes que j'ai prescrites (section 2), sur
les gardes `T1` a `T4` que j'ai demandees et sur leurs sondes ; elle ne relit aucun autre item, ne pose aucune correction, ne rouvre
aucun verdict hors des lignes prescrites et n'ouvre aucun tour. La correction de la section 5 porte sur une phrase de ma lecture, pas
sur le fichier. C'est la forme d'une seconde lecture au sens de `DEC-0120` A.272 (0,5 hors marge). Le classement est a `camille-po` ;
je n'en convertis aucune duree.
