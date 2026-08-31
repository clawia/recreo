package com.recreo.domain.mechanics.saisie;

import java.text.Normalizer;
import java.util.Locale;

/**
 * Le repliage que cette mecanique possede, et qui tient a lui seul les trois tolerances de
 * l'arbitrage de nadia-prof du 2026-08-24 : les signes poses sur les lettres (SAI-1), la casse
 * (SAI-3) et les blancs (SAI-4).
 *
 * <p>ADR-0058 section 3 : il vit ici, dans le paquet de cette mecanique, et nulle part ailleurs. Il
 * n'est <b>pas partage</b> avec la mecanique voisine : si celle-ci en veut un un jour, elle ecrira
 * le sien. Trois lignes semblables valent mieux qu'une abstraction prematuree, et le seul endroit
 * ou l'on serait tente de la hisser est un contrat, ce que la clause 8 de
 * {@code DecouplageMecaniqueContenuTest} rend rouge depuis s19.</p>
 *
 * <p><b>Il ne connait pas le francais, et ce n'est pas une affirmation : c'est deja juge dans ce
 * depot.</b> Le repliage du garde-fou d'unicite d'ecriture d'une matiere dans le contenu servi fait
 * exactement ces trois gestes, et sa Javadoc, ratifiee par ADR-0049, ecrit que ce n'est pas une
 * normalisation du francais mais la seule facon de dire que deux chaines nomment la meme chose sans
 * tenir de table de correspondance. Aucune liste de mots n'entre ici, donc aucune matiere ne s'y
 * privilegie.</p>
 *
 * <p>Trois precisions qui sont de la decision et non du detail :</p>
 * <ul>
 *   <li>{@link Locale#ROOT} et jamais une langue. Un repliage de casse qui depend d'une langue est
 *   une regle de langue deguisee en appel de bibliotheque ;</li>
 *   <li>le blanc insecable compte comme un blanc. {@code Character.isWhitespace} ne le compte pas,
 *   par definition, et c'est exactement le blanc qu'un copier-coller depose : le predicat est donc
 *   {@code isWhitespace || isSpaceChar} ;</li>
 *   <li>les blancs sont <b>retires</b> et non reduits a un seul. C'est ce que SAI-4 demande dans
 *   ses quatre cas d'un coup - les blancs de bord, les blancs multiples internes, le blanc autour
 *   d'une ponctuation, et le blanc <b>manquant</b> entre deux mots.</li>
 * </ul>
 *
 * <p>Ce type n'est pas une abstraction offerte : il est de visibilite de paquet, et seule cette
 * mecanique s'en sert.</p>
 */
final class NormalisationDeSaisie {

    private NormalisationDeSaisie() {
    }

    /**
     * Replie une chaine sur ce qui reste quand on retire ce que SAI-1, SAI-3 et SAI-4 declarent
     * sans effet sur le verdict. Deux chaines dont le repliage est egal disent la meme chose au
     * sens de ces trois tolerances, et rien de plus.
     */
    static String replier(String texte) {
        String decompose = Normalizer.normalize(texte, Normalizer.Form.NFD);
        StringBuilder replie = new StringBuilder(decompose.length());
        for (int rang = 0; rang < decompose.length(); rang++) {
            char signe = decompose.charAt(rang);
            if (Character.getType(signe) == Character.NON_SPACING_MARK) {
                continue;
            }
            if (Character.isWhitespace(signe) || Character.isSpaceChar(signe)) {
                continue;
            }
            replie.append(signe);
        }
        return replie.toString().toLowerCase(Locale.ROOT);
    }
}
