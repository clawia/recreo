package com.recreo.application.catalog;

import java.util.List;

/**
 * ADR-0095 : la sequence scolaire des niveaux, declaree une fois et a un seul endroit.
 *
 * <p>DEC-0090 fixe l'ordre attendu dans l'en-tete - CP, CE1, CM1, CM2 - et laisse le mecanisme a
 * l'architecte. Le mecanique retenu est une cle de tri : le niveau reste la chaine que le paquet
 * declare (ADR-0006), et c'est cette liste, et elle seule, qui dit ou chaque chaine se range.
 *
 * <p>Un niveau absent de la sequence recoit le rang le plus grand : il se range apres tous les
 * niveaux connus, dans l'ordre du catalogue, plutot que de faire echouer l'accueil. Ce choix n'est
 * pas une tolerance - le build refuse un tel niveau
 * ({@code ChaqueNiveauServiEstNommeDansLaSequenceTest}) ; le rang de repli ne sert que si un
 * catalogue non servi, une fixture ou un contre-exemple en fabrique un.
 *
 * <p>Le blanc est rogne avant comparaison, pour le motif qu'ADR-0079 a mesure : un descripteur
 * ecrit a la main a deja porte {@code "CM1 "} et casse une cle de regroupement sans rien afficher.
 * La casse, elle, n'est pas normalisee : elle se voit a la lecture du YAML, et un {@code "cp"}
 * doit etre corrige dans le paquet, pas absorbe ici.
 */
public final class OrdreDesNiveaux {

    /** La sequence, dans l'ordre de la progression scolaire (DEC-0090). */
    public static final List<String> SEQUENCE = List.of("CP", "CE1", "CM1", "CM2");

    private OrdreDesNiveaux() {
    }

    /** Le rang du niveau dans la sequence, ou un rang de repli strictement superieur a tous. */
    public static int rang(String niveau) {
        int rang = niveau == null ? -1 : SEQUENCE.indexOf(niveau.strip());
        return rang < 0 ? SEQUENCE.size() : rang;
    }
}
