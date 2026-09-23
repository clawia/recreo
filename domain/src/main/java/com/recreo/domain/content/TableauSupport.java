package com.recreo.domain.content;

import java.util.ArrayList;
import java.util.List;

/**
 * ADR-0099 : le document en <b>grille</b> qu'un item donne a lire, quand la competence visee est
 * d'extraire une information d'un tableau (SCI-CM1-20). Septieme composante optionnelle de
 * {@link Question}, sur le patron exact des six precedentes (ADR-0024, ADR-0050, ADR-0062,
 * ADR-0073, ADR-0075) : la mecanique la transporte sans jamais la lire, et un item qui ne montre
 * aucune grille ne remplit aucun champ factice.
 *
 * <p><b>Pourquoi une composante structuree et non un texte.</b> {@code texteSupport} est rendu par
 * un {@code <p th:text>} : HTML replie les blancs, et un tableau aligne a l'espace y devient une
 * ligne courante. Meme si les blancs etaient preserves, une grille dont la structure repose sur la
 * largeur des caracteres n'est pas gardable - ADR-0092 l'a tranche : "la police servie est celle de
 * l'appareil". Une grille dont le produit connait l'en-tete et les lignes est rendue par un
 * {@code <table>}, dont la mise en colonnes est faite par le moteur de rendu et non par la police,
 * et dont une lecture d'ecran annonce chaque cellule sous l'intitule de sa colonne.</p>
 *
 * <p><b>Les quatre invariants sont poses ici et non dans la seule fabrique de contenu</b>, meme
 * geste que la clause 5 d'ADR-0073 : ils valent pour tout site de construction, y compris ceux qui
 * n'ont jamais lu un fichier. Une grille mal formee ne se charge pas ; elle ne parvient donc jamais
 * a un enfant.</p>
 *
 * @param enTete les intitules de colonnes, dans l'ordre. Deux au moins : une grille a une colonne
 *               est une liste, et une liste se dit deja en {@code texteSupport}.
 * @param lignes les lignes de corps, chacune dans l'ordre des colonnes. Une au moins.
 */
public record TableauSupport(List<String> enTete, List<List<String>> lignes) {

    private static final int COLONNES_MINIMUM = 2;

    /**
     * <b>Un record du contrat de contenu ne declare aucune methode</b> (ADR-0080, decision 6, clause
     * 11 de {@code DecouplageMecaniqueContenuTest}) : les quatre verifications vivent donc dans ce
     * constructeur, sans methode d'appoint, et rien ici ne prepare de valeur derivee pour un
     * appelant. La regle a mordu cette classe a l'ecriture, et elle avait raison - une methode de
     * lecture posee sur un record du contrat est du comportement loge la ou aucune clause de
     * decouplage ne le regarde.
     */
    public TableauSupport {
        if (enTete == null || enTete.size() < COLONNES_MINIMUM) {
            throw new IllegalArgumentException("ADR-0099 : un tableau-support declare au moins "
                    + COLONNES_MINIMUM + " colonnes d'en-tete - une grille a une colonne est une"
                    + " liste, et une liste se dit en texteSupport");
        }
        if (lignes == null || lignes.isEmpty()) {
            throw new IllegalArgumentException(
                    "ADR-0099 : un tableau-support declare au moins une ligne de corps");
        }
        // Une cellule vide ou blanche est refusee des la construction, en en-tete comme en corps.
        // Motif de rendu, pas de gout : un <th> vide ne donne a la lecture d'ecran aucun intitule
        // pour sa colonne, et un <td> vide se lit comme une donnee manquante alors que rien ne
        // distingue, dans un fichier, l'absence voulue de l'oubli. La population de grilles servies
        // est nulle au jour de cette decision : cette clause n'exempte aucune dette, elle en
        // empeche une.
        List<List<String>> aVerifier = new ArrayList<>();
        aVerifier.add(enTete);
        for (List<String> ligne : lignes) {
            if (ligne != null) {
                aVerifier.add(ligne);
            }
        }
        for (int rangee = 0; rangee < aVerifier.size(); rangee++) {
            List<String> cellules = aVerifier.get(rangee);
            String ou = rangee == 0 ? "l'en-tete" : "la ligne " + rangee;
            for (int colonne = 0; colonne < cellules.size(); colonne++) {
                String cellule = cellules.get(colonne);
                if (cellule == null || cellule.isBlank()) {
                    throw new IllegalArgumentException("ADR-0099 : " + ou + " porte une cellule vide"
                            + " en colonne " + (colonne + 1) + " - une cellule sans texte n'annonce"
                            + " rien a une lecture d'ecran");
                }
            }
        }
        // ADR-0099 decision 2, transposition d'ADR-0087 du markdown du depot au contenu servi : la
        // largeur attendue est celle de l'en-tete du tableau juge, jamais un nombre ecrit ici. La
        // comparaison est interne au tableau, comme celle d'ADR-0045.
        List<List<String>> copie = new ArrayList<>();
        for (int rang = 0; rang < lignes.size(); rang++) {
            List<String> ligne = lignes.get(rang);
            if (ligne == null || ligne.size() != enTete.size()) {
                throw new IllegalArgumentException("ADR-0099 : la ligne " + (rang + 1)
                        + " porte " + (ligne == null ? 0 : ligne.size()) + " cellule(s) pour "
                        + enTete.size() + " colonnes d'en-tete - une cellule en trop n'est affichee"
                        + " par aucun rendu, une cellule en moins laisse une valeur sans son"
                        + " intitule de colonne");
            }
            copie.add(List.copyOf(ligne));
        }
        enTete = List.copyOf(enTete);
        lignes = List.copyOf(copie);
    }
}
