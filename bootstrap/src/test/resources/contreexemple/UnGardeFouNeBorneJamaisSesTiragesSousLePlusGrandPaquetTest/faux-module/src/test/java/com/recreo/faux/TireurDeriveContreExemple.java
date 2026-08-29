package com.recreo.faux;

/**
 * Source fictive, jamais compilee : elle est lue comme donnee. Elle derive sa borne de ce que le
 * paquet declare, et ne doit donc jamais etre nommee par le recensement d'ADR-0064. Sa presence
 * est ce qui prouve que la regle n'echoue pas par inclusion.
 */
class TireurDeriveContreExemple {

    private static final int TIRAGES_SUR_UN_PAQUET_COMBINATOIRE = 3;

    void tirer() {
        ContenuFictif contenu = chargerPaquet("paquet-fictif");
        int declares = itemsDeclares("paquet-fictif");
        int tirages = declares > 0 ? declares + 1 : TIRAGES_SUR_UN_PAQUET_COMBINATOIRE;
        for (int tirage = 0; tirage < tirages; tirage++) {
            Item item = contenu.next(hasard, dejaVus);
            dejaVus.add(item.id());
        }
    }
}
