package com.recreo.faux;

import java.util.Map;

/**
 * ADR-0003, ADR-0080 section 5 : jeu fautif, jamais compile - il vit sous src/test/resources. Le
 * paquet declare une zone dont le cadre porte "hauteurr" (faute de frappe sur "hauteur"), et cette
 * fabrique fictive cite en litteral toutes les autres cles du paquet - y compris "hauteur", jamais
 * ecrite dans le YAML - mais jamais "hauteurr". Sans la descente derivee d'ADR-0080, cette faute de
 * frappe imbriquee ne serait meme pas vue comme une cle de schema.
 */
public final class FabriqueDeZoneQuiOublieUneCleImbriqueeContreExemple {

    private FabriqueDeZoneQuiOublieUneCleImbriqueeContreExemple() {
    }

    public static String lireLItem(Map<String, Object> item) {
        return String.valueOf(item.get("enonce"))
                + item.get("illustration")
                + item.get("illustrationTexteAlternatif")
                + item.get("bonneZone")
                + item.get("zones")
                + item.get("indice")
                + item.get("explication")
                + item.get("texteAVoixHaute");
    }

    public static String lireLaZone(Map<String, Object> zone) {
        return String.valueOf(zone.get("id")) + zone.get("libelle") + zone.get("cadre");
    }

    public static String lireLeCadre(Map<String, Object> cadre) {
        return String.valueOf(cadre.get("x")) + cadre.get("y") + cadre.get("largeur")
                + cadre.get("hauteur");
    }

    public static String lireLEnveloppe(Map<String, Object> paquet) {
        return String.valueOf(paquet.get("schema"))
                + paquet.get("id")
                + paquet.get("type")
                + paquet.get("descripteur")
                + paquet.get("items")
                + paquet.get("matiere")
                + paquet.get("niveau")
                + paquet.get("domaine")
                + paquet.get("competence");
    }
}
