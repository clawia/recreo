package com.recreo.faux;

import java.util.Map;

/**
 * ADR-0003, ADR-0070 : jeu fautif, jamais compile - il vit sous src/test/resources. Il lit les
 * cinq cles ordinaires d'un item et annonce la sixieme dans sa documentation sans jamais la lire.
 *
 * <p>La cle annoncee est "modeDeComparaisonAnnonce" : elle est ecrite ici entre guillemets, dans un
 * commentaire, exactement comme une conception ecrite avant son code la nomme. C'est le seul endroit
 * du depot ou elle figure, et le garde-fou doit la refuser malgre cette mention - sans quoi il
 * suffirait d'annoncer une cle pour que le paquet qui la porte passe pour lu.</p>
 */
public final class FabriqueQuiOublieUneCleContreExemple {

    private FabriqueQuiOublieUneCleContreExemple() {
    }

    public static String lire(Map<String, Object> item) {
        return String.valueOf(item.get("enonce"))
                + item.get("bonneReponse")
                + item.get("distracteurs")
                + item.get("indice");
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
