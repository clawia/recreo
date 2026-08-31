package com.recreo.domain.content;

import java.util.List;
import java.util.Optional;

/**
 * La seconde forme d'item du contrat de contenu : un enonce auquel l'enfant repond en ecrivant,
 * plutot qu'en choisissant parmi des propositions (ADR-0058 section 1).
 *
 * <p>ADR-0006 : que du texte, et un type du contrat. {@code formeDeReference} est ce que l'enfant
 * doit revoir integralement accentue et sans faute au moment du verdict (SAI-8, SAI-9b) ;
 * {@code formesAcceptees} enumere les ecritures que le paquet accepte en plus d'elle - les fautes
 * previsibles de ce mot precis, ecrites ou relues par nadia-prof avant tout Done (SAI-6, SAI-7).
 * <b>Cette liste est le bareme de l'exercice</b> : c'est pourquoi elle vit dans la donnee, ou une
 * relecture pedagogique l'atteint, et jamais dans le code, ou rien ne l'atteindrait.</p>
 *
 * <p>Elle n'a pas a repeter {@code formeDeReference} : une mecanique qui accepterait les formes
 * enumerees sans accepter la forme de reference refuserait la reponse la mieux ecrite, et aucun
 * paquet n'a a se premunir de cela item par item.</p>
 *
 * <p>ADR-0058 section 1 : ces composantes ne sont pas posees sur {@link Question}. Ce record-la est
 * traverse par tous les jeux du catalogue, et y poser une liste de formes acceptees obligerait
 * chaque paquet de choix a porter une cle qui ne le concerne pas - la dette 2 de la version
 * precedente, mot pour mot, deja tranchee par ADR-0024 pour l'explication.</p>
 *
 * <p>{@code indice} et {@code explication} sont facultatifs et se comportent exactement comme sur
 * {@link Question} : un item qui n'en porte pas ne remplit aucune valeur factice.</p>
 */
public record ReponseAEcrire(ItemId id, String enonce, String formeDeReference,
                              List<String> formesAcceptees, Optional<String> indice,
                              Optional<String> explication,
                              ExigenceDeForme exigenceDeForme) implements Item {

    public ReponseAEcrire {
        formesAcceptees = List.copyOf(formesAcceptees);
    }
}
