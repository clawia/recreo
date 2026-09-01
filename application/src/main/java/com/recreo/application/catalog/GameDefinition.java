package com.recreo.application.catalog;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.mechanic.MechanicId;

import java.util.Optional;

/**
 * ADR-0007 : un jeu n'est pas une classe, c'est une entree de catalogue qui associe une mecanique
 * et un paquet de contenu. La consigne est portee ici, jamais par la mecanique (ADR-0004).
 * ADR-0012 : nombreDeManches est un attribut de cette association (mecanique + paquet), pas du
 * contenu ni de la mecanique en general.
 *
 * <p>ADR-0075 section 7 : {@code consigneAdulte} porte la phrase qui dit a l'adulte ce qu'il ne
 * doit pas faire (VOI-12), sur le meme patron que {@code consigne} - invariante a l'interieur d'un
 * paquet, variable d'un paquet a l'autre - et pour le meme motif : deux paquets dont les interdits
 * different ne peuvent pas partager un libelle de gabarit (nadia-prof, VOI-12 point 3). Facultative,
 * comme les composantes optionnelles du contrat de contenu (ADR-0016) : un jeu qui ne s'appuie sur
 * aucune voix ne remplit aucun champ factice.</p>
 */
public record GameDefinition(
        String id,
        String titre,
        String consigne,
        MechanicId mecanique,
        ContentDescriptor descripteur,
        ContentSource contenu,
        int nombreDeManches,
        Optional<String> consigneAdulte) {

    /**
     * ADR-0016 : constructeur de commodite qui laisse {@code consigneAdulte} vide, pour ne pas
     * rouvrir les sites de construction qui n'associent aucune voix a ce jeu.
     */
    public GameDefinition(String id, String titre, String consigne, MechanicId mecanique,
                           ContentDescriptor descripteur, ContentSource contenu, int nombreDeManches) {
        this(id, titre, consigne, mecanique, descripteur, contenu, nombreDeManches, Optional.empty());
    }
}
