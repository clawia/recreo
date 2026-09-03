# Fiche de conformite - bandit-manchot-histoire-geographie-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-histoire-geographie.yaml`.
- **Entree de catalogue** : `bandit-manchot-histoire-geographie-cm1`, titre servi *"Histoire et géographie"*, `nombreDeManches: 10`.
- **Statut global** : **VALIDE** (Done pedagogique prononce le 2026-09-03). **Ma reserve bloquante du 2026-09-03 sur l'item 5 est levee** : la reformulation est reprise mot pour mot. Lignes de matrice `non couvert` -> **`en cours`** - premier paquet d'une matiere neuve, aucune ligne ne se ferme.
- **Arbitrage prealable de `nadia-prof`** : `ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md` sections 5, 7.2, 7.4 et 7.5 (`HIS-1` a `HIS-3`, `BIA-5`) ; `ARBITRAGE-s27-s1-8-retrecie-unite-de-ma-borne-et-trois-faits-d-histoire.md` section 3.
- **Derniere mise a jour** : 2026-09-03 (**verdict de `nadia-prof` apres avoir joue**, s27).

## Competence visee

`Onze des douze lignes jouables du référentiel Histoire-géographie CM1`
(Histoire-géographie, CM1, les six domaines du programme de CM1).

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `d259b5f`, jar reconstruit et lance, parties reelles par appels
HTTP contre l'application, **y compris en me trompant volontairement**. Ce n'est pas un audit
degrade.

### 1. Exactitude - le controle que j'avais facture d'avance

**Sur une matiere ou je ne suis pas la source, c'est le poste de cout principal, et je l'ai
paye.** Les faits verifies un par un sur les items joues :

| Fait | Verdict |
|---|---|
| Revolution 1789 -> XVIIIe siecle | juste |
| Chambord -> Francois Ier, Renaissance | juste |
| Edit de Nantes -> Henri IV | juste |
| Croisade -> Louis IX | juste |
| Versailles -> Louis XIV | juste |
| Prise de la Bastille -> debut de la Revolution a Paris, 1789 | juste |
| Abolition des privileges (4 aout) -> **puis** Declaration des droits (26 aout), meme annee | **juste, et l'ordre est exact** |
| Napoleon sacre empereur 1804, monarchie abolie 1792 -> Empire | juste |
| Arenes et aqueducs en Gaule -> les Romains | juste (deja confirme le 2026-09-03) |
| Bastille -> pouvoir absolu du roi | juste |
| Declaration des droits -> 1789 | juste |

**Aucune erreur de fait. Aucune date fausse.**

### 2. L'item 5 - **ma reserve bloquante est levee**

Le 2026-09-03 j'avais bloque sur *"comment appelle-t-on **l'ensemble** de ces peuples"*, parce que
les peuples entres dans l'Empire au IVe siecle **ne sont pas tous germaniques**. La reformulation
que j'avais fournie est reprise **mot pour mot** :

> *"A partir du IVe siecle, des peuples venus du nord et de l'est s'installent dans l'Empire
> romain. Les Francs sont l'un d'eux. Comment appelle-t-on ces peuples ?"*

*L'ensemble* a disparu, la reponse ne pretend plus a une couverture totale, la repetition est
retiree. **L'enonce passe de 30 a 26 mots.** Reserve **levee**.

### 3. `HIS-1` - la clause qui devait empecher le quiz de dates

**Tenue : 5 items de restitution sur 20, soit 25,0 %**, sous mon plafond d'un tiers. Compte
mecanique, et **le test echoue aussi si un item n'a pas son marqueur** - ce qui ferme la porte a un
item qui echapperait au compte en oubliant sa marque. **C'est mieux que ce que j'avais demande.**

Et l'effet se voit en jouant : sur dix manches, j'ai eu des items d'**ordre** (*"lequel a eu lieu
le premier ?"*, *"quel evenement juste apres l'abolition des privileges ?"*), d'**association**
(roi -> realisation) et de **document** (deux textes supports). **Ce n'est pas un quiz de dates.**
C'etait mon inquietude principale sur cette matiere ; elle ne se realise pas.

### 4. `HIS-2` et `BIA-5`

**`HIS-2` tenue** sur les dix items joues : aucun n'a deux reponses defendables. `marc-dev` avait
ecarte *"pourquoi la Revolution a-t-elle eclate ?"* - mon propre exemple - et evite les dates
disputees. **Une clause qui change le contenu avant l'audit vaut mieux qu'une clause qui refuse
apres.**

**`BIA-5` tenue** : aucun item ne fait porter un jugement de valeur sur un roi ni sur un acteur de
la Revolution. Les enonces decrivent et n'apprecient pas.

### 5. Le titre - **verifie sur le contenu, ma reserve tombe**

*"Histoire et geographie"* nomme la matiere plutot que le jeu, ce qui est le defaut que j'ai
reproche a *"Calcul CP"*. **J'allais poser la reserve ; je suis allee compter d'abord.** Le paquet
porte bien des items de geographie - espace urbain, espace touristique, espace rural, production
d'energie - **quatre sur vingt**. Le titre ne promet donc rien que le contenu ne tienne. **Pas de
reserve.**

### 6. Joue jusqu'au bout

Partie complete, **10 manches**, ecran de fin atteint. Les deux textes supports font 27 et 36
mots, ce qui est la forme deja validee de `bandit-manchot-comprehension-cm1`.

**Un incident de mon cote que je consigne, parce qu'il aurait pu devenir un faux refus** : mon
pilote d'audit a d'abord rendu l'item de la Declaration des droits **impossible a resoudre** - la
revelation disait *"appuie sur la Declaration des droits de l'homme et du citoyen"* et le bouton
etait refuse. **C'etait mon outil, pas le produit** : l'apostrophe est echappee dans la valeur du
bouton, et un navigateur la decode avant d'envoyer. **Verifie en reposant la valeur decodee : la
reponse est acceptee.** Je l'ecris parce que j'ai failli porter comme defaut bloquant un artefact
de ma propre mesure.

### 7. Reserves non bloquantes

- **`HG-R1` - deux enonces a 26 mots** (l'item 5 reformule et l'item sur la production
  d'electricite), contre une mediane de 13 sur les vingt. C'est long pour un CM1, mais ce sont des
  items de raisonnement dont l'enonce porte la donnee necessaire. **Non bloquant, a surveiller si
  un troisieme apparait.**
- **`HG-R2` - la matiere penche vers l'histoire** : 16 items d'histoire pour 4 de geographie. C'est
  legitime pour un premier paquet, et les lignes de geographie les plus lourdes (carte,
  planisphere) sont hors de portee du palier 1 par mon propre arbitrage. **A rappeler quand la
  matiere sera etendue**, pour que la geographie ne reste pas le quart pauvre.

### 8. Ce dont je reste la seule garde

**L'exactitude factuelle de cette matiere n'est couverte par aucun test et ne le sera pas.**
`HIS-2` non plus. `HIS-1` est la seule des trois qu'un automate mord, et il ne le fait que parce
que le contenu declare le type de chaque item.

