package com.recreo.faux;

/**
 * Source fictive, jamais compilee : elle est lue comme donnee. Elle borne ses tirages a un nombre
 * ecrit a la main, plus petit que les cinq items du paquet fictif du meme depot, et doit donc etre
 * nommee par le recensement d'ADR-0064.
 */
class TireurBorneALaMainContreExemple {

    private static final int TIRAGES = 3;

    void tirer() {
        ContenuFictif contenu = chargerPaquet("paquet-fictif");
        for (int tirage = 0; tirage < TIRAGES; tirage++) {
            Item item = contenu.next(hasard, dejaVus);
            dejaVus.add(item.id());
        }
    }
}
