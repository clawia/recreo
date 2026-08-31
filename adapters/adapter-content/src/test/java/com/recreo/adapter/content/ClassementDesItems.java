package com.recreo.adapter.content;

import com.recreo.domain.content.Item;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.ReponseAEcrire;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * <b>Le classement des formes d'item, ecrit une fois pour les garde-fous de contenu</b> (lot 5a de
 * s24, section 2).
 *
 * <p>Depuis qu'{@link Item} permet deux formes (ADR-0058, ADR-0069), chaque garde-fou de ce module
 * doit dire ce qu'il fait d'un item de saisie. Le tri a ete ecrit garde-fou par garde-fou, avec le
 * motif de chacun, dans
 * {@code squad/sprints/s24-trois-paquets-et-ouverture-de-la-saisie/tri-des-garde-fous-de-contenu-face-a-un-item-de-saisie.md}.
 * Ce que ce fichier-ci porte est le <b>classement</b>, et rien d'autre.</p>
 *
 * <p><b>Ce qui est partage et ce qui ne l'est jamais.</b> Est partagee la question "cet item
 * presente-t-il des propositions, ou demande-t-il une reponse ecrite ?" - elle a une seule reponse,
 * et treize copies de cette reponse divergeraient. N'est jamais partage le <b>critere garde</b> :
 * chaque garde-fou declare son domaine par rapport a ce classement, avec son motif dans sa propre
 * Javadoc, et garde sa propre assertion de non-vacuite. C'est la meme regle que pour
 * {@code itemsDeclares} et {@code paquets}, deja partages sans qu'aucune assertion ne le soit.</p>
 *
 * <p><b>Ce que ce classement n'est pas</b> : un transtypage defensif silencieux. Un garde-fou qui
 * appelle {@link #presentantDesPropositions} sans ecrire pourquoi son critere ne dit rien d'un item
 * de saisie pose une exemption muette, et c'est exactement la famille de defaut que ce depot
 * sanctionne depuis s05. Le classement rend le geste possible ; il ne rend pas le motif inutile.</p>
 *
 * <p>Les deux methodes descendent par un {@code switch} exhaustif sur la scellure d'{@link Item}
 * (ADR-0004) : une troisieme forme d'item ne compilera pas tant que personne n'aura dit de quel cote
 * elle tombe.</p>
 */
final class ClassementDesItems {

    private ClassementDesItems() {
    }

    /** Les items qui presentent un choix parmi des propositions. */
    static List<Question> presentantDesPropositions(List<Item> items) {
        return items.stream().flatMap(item -> switch (item) {
            case Question question -> Stream.of(question);
            case ReponseAEcrire ignore -> Stream.<Question>empty();
        }).toList();
    }

    /**
     * Ce que <b>toute</b> forme d'item porte : l'enonce qui pose la manche, la reponse que le paquet
     * tient pour juste, et l'indice facultatif. Les garde-fous qui ne jugent que ces trois-la sont
     * etendus sans avoir a connaitre la forme de l'item - c'est ce qui distingue une extension d'une
     * exemption.
     *
     * <p>La reponse juste est {@code bonneReponse} sur un item de choix et {@code formeDeReference}
     * sur un item de saisie : deux noms pour la meme chose du point de vue d'un garde-fou qui
     * demande "ce que l'indice annonce est-il ce que le paquet attend ?".</p>
     */
    record ItemLu(String enonce, String reponseJuste, Optional<String> indice) {
    }

    /** La projection ci-dessus, pour chaque item tire, quelle que soit sa forme. */
    static List<ItemLu> lus(List<Item> items) {
        return items.stream().map(item -> switch (item) {
            case Question question -> new ItemLu(question.enonce(), question.bonneReponse(),
                    question.indice());
            case ReponseAEcrire reponse -> new ItemLu(reponse.enonce(), reponse.formeDeReference(),
                    reponse.indice());
        }).toList();
    }

    /** Les items auxquels l'enfant repond en ecrivant. */
    static List<ReponseAEcrire> demandantUneReponseEcrite(List<Item> items) {
        return items.stream().flatMap(item -> switch (item) {
            case Question ignore -> Stream.<ReponseAEcrire>empty();
            case ReponseAEcrire reponse -> Stream.of(reponse);
        }).toList();
    }
}
