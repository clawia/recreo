package com.recreo.adapter.content;

import com.recreo.domain.content.CadreDeZone;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Zone;
import com.recreo.domain.content.ZoneADesigner;
import com.recreo.domain.content.source.ListContentSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * ADR-0080, decision 5 : la fabrique du type de paquet 'designation', quatrieme fabrique enregistree
 * dans le meme registre que ses trois soeurs (ADR-0007) - le mecanisme qu'ADR-0007 a concu, pas son
 * contournement. Les items sont ecrits a la main sous 'items' ; l'identifiant de chaque item est
 * derive de sa position, le schema n'en porte pas, meme patron que
 * {@link QuestionContentSourceFactory} et {@link SaisieContentSourceFactory}.
 *
 * <p><b>Cette fabrique ne construit aucune geometrie</b> : elle lit les quatre entiers d'un cadre
 * tels qu'ecrits dans le YAML et les passe a {@link CadreDeZone}, dont le constructeur compact porte
 * deja les invariants (ADR-0080, decision 2) - elle ne les revalide pas ici, et elle ne calcule
 * jamais une position.</p>
 *
 * <p><b>Les zones d'un item sont lues dans l'ordre ou elles apparaissent dans le YAML</b>
 * (ADR-0080, decision 3 et section 7.1) : cette fabrique ne les trie pas, ne les melange pas, et ne
 * leur applique aucune transformation.</p>
 *
 * <p>Une cle que cette fabrique ne connait pas est ignoree sans un mot, comme ses deux soeurs
 * 'question' et 'saisie' ; {@code AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest} fait echouer
 * le build tant qu'aucune source du depot ne la nomme.</p>
 */
final class DesignationContentSourceFactory implements ContentSourceFactory {

    @Override
    public ContentSource creer(ContentDescriptor descripteur, Map<String, Object> corps) {
        Object brut = corps.get("items");
        if (!(brut instanceof List<?> items) || items.isEmpty()) {
            throw new IllegalStateException("champ obligatoire manquant ou vide : 'items'");
        }
        List<ZoneADesigner> zonesADesigner = new ArrayList<>();
        int position = 0;
        for (Object itemBrut : items) {
            zonesADesigner.add(zoneADesigner(itemBrut, position));
            position++;
        }
        return new ListContentSource(descripteur, zonesADesigner, false);
    }

    private ZoneADesigner zoneADesigner(Object itemBrut, int position) {
        if (!(itemBrut instanceof Map<?, ?> item)) {
            throw new IllegalStateException("items[" + position + "] : doit etre une correspondance (map)");
        }
        String enonce = texte(item, "enonce", position);
        String illustration = texte(item, "illustration", position);
        String illustrationTexteAlternatif = texte(item, "illustrationTexteAlternatif", position);
        String bonneZone = texte(item, "bonneZone", position);
        Object zonesBrut = item.get("zones");
        if (!(zonesBrut instanceof List<?> zonesListe) || zonesListe.isEmpty()) {
            throw new IllegalStateException("items[" + position + "] : champ obligatoire manquant 'zones'");
        }
        List<Zone> zones = new ArrayList<>();
        int rang = 0;
        for (Object zoneBrut : zonesListe) {
            zones.add(zone(zoneBrut, position, rang));
            rang++;
        }
        Object indice = item.get("indice");
        Object explication = item.get("explication");
        Object texteAVoixHaute = item.get("texteAVoixHaute");
        return new ZoneADesigner(new ItemId("item-" + position), enonce, illustration,
                illustrationTexteAlternatif, zones, bonneZone,
                indice == null ? Optional.empty() : Optional.of(String.valueOf(indice)),
                explication == null ? Optional.empty() : Optional.of(String.valueOf(explication)),
                texteAVoixHaute == null
                        ? Optional.empty() : Optional.of(String.valueOf(texteAVoixHaute)));
    }

    private Zone zone(Object zoneBrut, int position, int rang) {
        if (!(zoneBrut instanceof Map<?, ?> zone)) {
            throw new IllegalStateException(
                    "items[" + position + "].zones[" + rang + "] : doit etre une correspondance (map)");
        }
        String id = texteDeZone(zone, "id", position, rang);
        String libelle = texteDeZone(zone, "libelle", position, rang);
        Object cadreBrut = zone.get("cadre");
        if (!(cadreBrut instanceof Map<?, ?> cadre)) {
            throw new IllegalStateException(
                    "items[" + position + "].zones[" + rang + "] : champ obligatoire manquant 'cadre'");
        }
        return new Zone(id, libelle, new CadreDeZone(
                entierDeCadre(cadre, "x", position, rang), entierDeCadre(cadre, "y", position, rang),
                entierDeCadre(cadre, "largeur", position, rang),
                entierDeCadre(cadre, "hauteur", position, rang)));
    }

    private String texte(Map<?, ?> item, String champ, int position) {
        Object valeur = item.get(champ);
        if (valeur == null) {
            throw new IllegalStateException("items[" + position + "] : champ obligatoire manquant '" + champ + "'");
        }
        return String.valueOf(valeur);
    }

    private String texteDeZone(Map<?, ?> zone, String champ, int position, int rang) {
        Object valeur = zone.get(champ);
        if (valeur == null) {
            throw new IllegalStateException(
                    "items[" + position + "].zones[" + rang + "] : champ obligatoire manquant '"
                            + champ + "'");
        }
        return String.valueOf(valeur);
    }

    private int entierDeCadre(Map<?, ?> cadre, String champ, int position, int rang) {
        Object valeur = cadre.get(champ);
        if (!(valeur instanceof Integer entier)) {
            throw new IllegalStateException("items[" + position + "].zones[" + rang + "].cadre : champ '"
                    + champ + "' doit etre un entier");
        }
        return entier;
    }
}
