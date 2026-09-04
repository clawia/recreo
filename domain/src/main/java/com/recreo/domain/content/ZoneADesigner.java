package com.recreo.domain.content;

import java.util.List;
import java.util.Optional;

/**
 * ADR-0080, decision 1 : la troisieme forme d'item du contrat de contenu (`Item permits Question,
 * ReponseAEcrire, ZoneADesigner`). L'enfant ne choisit pas un texte et n'en ecrit pas un : il montre
 * un endroit sur une image fixe, parmi un ensemble fini de zones que le contenu declare.
 *
 * <p>Le critere d'ADR-0058 est rempli sans discussion : la facon dont l'enfant repond change. C'est
 * la difference que {@code nadia-prof} avait ecrite en refusant le quadrillage au palier 1 : "un
 * enfant qui repond 'A3' en lisant trois etiquettes n'a pas montre ou il est ; un enfant qui appuie
 * sur une case l'a montre."</p>
 *
 * <p><b>Trois choix a motiver, parce qu'ils ne vont pas de soi</b> :</p>
 * <ul>
 *   <li>{@code illustration} et {@code illustrationTexteAlternatif} sont <b>obligatoires</b> ici,
 *   alors qu'ils sont facultatifs sur {@link Question} (ADR-0073). Ce n'est pas une inconsistance :
 *   sur {@link Question} un item peut ne rien montrer ; ici, un item sans image ne veut rien dire.
 *   L'invariant est pose dans le constructeur compact, donc a tout site de construction, y compris
 *   ceux qui n'ont jamais lu un fichier ;</li>
 *   <li>{@code bonneZone} porte l'identifiant d'une zone, jamais un libelle et jamais une
 *   coordonnee. Invariants du constructeur compact : {@code bonneZone} est l'identifiant de l'une
 *   des {@code zones}, et deux zones d'un meme item n'ont jamais le meme identifiant ;</li>
 *   <li><b>ni {@code texteSupport}, ni {@code partDesignee}.</b> Aucun paquet n'en demande, et les
 *   ajouter maintenant serait la dette 2 de {@code CLAUDE.md} - une composante que chaque item
 *   devrait porter sans la concerner.</li>
 * </ul>
 *
 * <p>{@code indice}, {@code explication} et {@code texteAVoixHaute} sont facultatifs et se
 * comportent exactement comme sur {@link Question} et {@link ReponseAEcrire} : un item qui n'en
 * porte pas ne remplit aucune valeur factice.</p>
 */
public record ZoneADesigner(ItemId id, String enonce, String illustration,
                             String illustrationTexteAlternatif, List<Zone> zones, String bonneZone,
                             Optional<String> indice, Optional<String> explication,
                             Optional<String> texteAVoixHaute) implements Item {

    public ZoneADesigner {
        if (illustration == null || illustration.isBlank()) {
            throw new IllegalArgumentException(
                    "ADR-0080 : un item de designation montre toujours une image - item "
                            + id.value());
        }
        if (illustrationTexteAlternatif == null || illustrationTexteAlternatif.isBlank()) {
            throw new IllegalArgumentException(
                    "ADR-0080 : une illustration de designation porte toujours son texte alternatif -"
                            + " item " + id.value());
        }
        zones = List.copyOf(zones);
        long identifiantsDistincts = zones.stream().map(Zone::id).distinct().count();
        if (identifiantsDistincts != zones.size()) {
            throw new IllegalArgumentException(
                    "ADR-0080 : deux zones d'un meme item ne portent jamais le meme identifiant -"
                            + " item " + id.value());
        }
        if (zones.stream().noneMatch(zone -> zone.id().equals(bonneZone))) {
            throw new IllegalArgumentException(
                    "ADR-0080 : bonneZone doit etre l'identifiant d'une des zones de l'item - item "
                            + id.value() + ", bonneZone '" + bonneZone + "'");
        }
    }

    /** ADR-0016 : constructeur de commodite qui laisse {@code texteAVoixHaute} vide. */
    public ZoneADesigner(ItemId id, String enonce, String illustration,
                          String illustrationTexteAlternatif, List<Zone> zones, String bonneZone,
                          Optional<String> indice, Optional<String> explication) {
        this(id, enonce, illustration, illustrationTexteAlternatif, zones, bonneZone, indice,
                explication, Optional.empty());
    }
}
