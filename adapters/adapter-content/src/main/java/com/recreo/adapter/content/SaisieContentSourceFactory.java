package com.recreo.adapter.content;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.ExigenceDeForme;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.ReponseAEcrire;
import com.recreo.domain.content.source.ListContentSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * ADR-0007 : la fabrique du type de paquet 'saisie', soeur de {@link QuestionContentSourceFactory}
 * et enregistree dans la meme table. Les items sont ecrits a la main sous 'items' ; l'identifiant
 * de chaque item est derive de sa position, le schema n'en porte pas.
 *
 * <p><b>Un type neuf plutot qu'une extension du type 'question', et c'est une decision de ce
 * lot</b> (recommandation de theo-archi, avis de faisabilite de s24, section 2.1 point 3).
 * {@code YamlContentPackLoader} echoue avec un message nomme sur un type inconnu, alors qu'une
 * fabrique 'question' etendue accepterait sans un mot un paquet de saisie a moitie rempli - un
 * paquet sans forme de reference y rendrait des items de choix sans propositions.</p>
 *
 * <p><b>SAI-2a, et c'est un fait de schema, jamais un fait de domaine</b> (ADR-0058 section 2) : la
 * cle facultative 'exigenceDeForme' est lue <b>au niveau du paquet</b> et <b>au niveau de
 * l'item</b>, celle de l'item l'emportant. C'est le patron deja en service pour
 * 'indicesNommentLaReponse', a ceci pres que celle-la ne vit qu'au niveau du paquet.
 * Absente des deux niveaux, elle vaut {@link ExigenceDeForme#FORME_LIBRE} : un paquet qui ne
 * declare rien est en mode tolerant, et le defaut protege l'enfant, pas l'exercice. Une valeur
 * ecrite mais inconnue <b>arrete le chargement</b> en nommant la cle et la valeur, plutot que de
 * retomber sur le defaut - une faute de frappe sur 'exacte' rendrait sinon un paquet tolerant que
 * son auteur croit strict, et personne ne le verrait.</p>
 *
 * <p><b>'enonce' est le premier champ de chaque item, et c'est une decision de ce lot et non une
 * habitude</b> (avis de faisabilite de s24, section 2.1 point 5). {@code ChaquePaquetPorteUnIndiceTest}
 * borne ses tirages en comptant les lignes du YAML qui commencent par '- enonce:' ; un item de
 * saisie dont le premier champ differerait ferait rendre zero a ce comptage, et un garde-fou qui
 * borne a zero ne tire rien. C'est un vert silencieux, pas un rouge, et il se ferme a ce
 * prix-la. {@code SaisieContentSourceFactoryTest} verifie la propriete sur le texte du paquet, pas
 * sur la carte que SnakeYAML en tire - l'ordre des cles n'existe plus dans la carte.</p>
 *
 * <p>Une cle que cette fabrique ne connait pas est ignoree sans un mot, comme dans sa soeur ; ce
 * qui a change depuis ADR-0070, c'est qu'une telle cle fait desormais echouer le build tant
 * qu'aucune source du depot ne la nomme.</p>
 */
final class SaisieContentSourceFactory implements ContentSourceFactory {

    private static final String CLE_EXIGENCE_DE_FORME = "exigenceDeForme";

    private static final String EXIGENCE_LIBRE = "libre";

    private static final String EXIGENCE_EXACTE = "exacte";

    @Override
    public ContentSource creer(ContentDescriptor descripteur, Map<String, Object> corps) {
        Object brut = corps.get("items");
        if (!(brut instanceof List<?> items) || items.isEmpty()) {
            throw new IllegalStateException("champ obligatoire manquant ou vide : 'items'");
        }
        ExigenceDeForme exigenceDuPaquet = exigenceDe(corps.get(CLE_EXIGENCE_DE_FORME),
                ExigenceDeForme.FORME_LIBRE, "le paquet");
        List<ReponseAEcrire> reponses = new ArrayList<>();
        int position = 0;
        for (Object itemBrut : items) {
            reponses.add(reponse(itemBrut, position, exigenceDuPaquet));
            position++;
        }
        return new ListContentSource(descripteur, reponses, false);
    }

    private ReponseAEcrire reponse(Object itemBrut, int position, ExigenceDeForme exigenceDuPaquet) {
        if (!(itemBrut instanceof Map<?, ?> item)) {
            throw new IllegalStateException("items[" + position + "] : doit etre une correspondance (map)");
        }
        String enonce = texte(item, "enonce", position);
        String formeDeReference = texte(item, "formeDeReference", position);
        Object formesBrut = item.get("formesAcceptees");
        if (!(formesBrut instanceof List<?> formes)) {
            throw new IllegalStateException(
                    "items[" + position + "] : champ obligatoire manquant 'formesAcceptees'");
        }
        List<String> formesAcceptees = formes.stream().map(String::valueOf).toList();
        Object indice = item.get("indice");
        Object explication = item.get("explication");
        ExigenceDeForme exigence = exigenceDe(item.get(CLE_EXIGENCE_DE_FORME), exigenceDuPaquet,
                "items[" + position + "]");
        return new ReponseAEcrire(new ItemId("item-" + position), enonce, formeDeReference,
                formesAcceptees,
                indice == null ? Optional.empty() : Optional.of(String.valueOf(indice)),
                explication == null ? Optional.empty() : Optional.of(String.valueOf(explication)),
                exigence);
    }

    private ExigenceDeForme exigenceDe(Object declaree, ExigenceDeForme defaut, String ou) {
        if (declaree == null) {
            return defaut;
        }
        String valeur = String.valueOf(declaree);
        if (EXIGENCE_LIBRE.equals(valeur)) {
            return ExigenceDeForme.FORME_LIBRE;
        }
        if (EXIGENCE_EXACTE.equals(valeur)) {
            return ExigenceDeForme.FORME_EXACTE;
        }
        throw new IllegalStateException(ou + " : '" + CLE_EXIGENCE_DE_FORME + "' vaut '" + valeur
                + "', qui n'est ni '" + EXIGENCE_LIBRE + "' ni '" + EXIGENCE_EXACTE + "'");
    }

    private String texte(Map<?, ?> item, String champ, int position) {
        Object valeur = item.get(champ);
        if (valeur == null) {
            throw new IllegalStateException("items[" + position + "] : champ obligatoire manquant '" + champ + "'");
        }
        return String.valueOf(valeur);
    }
}
