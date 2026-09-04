package com.recreo.domain.content;

/**
 * ADR-0004 : une seule forme d'item est permise au sprint 0. Une deuxieme permission ne sera
 * ajoutee que par la mecanique qui en aura besoin.
 *
 * <p>s24 : cette mecanique-la est arrivee. {@link ReponseAEcrire} est la forme d'item de la
 * mecanique de saisie (ADR-0058 section 1). La couture scellee a fait son travail : ouvrir la
 * seconde permission arrete le compilateur aux deux endroits exacts ou une mecanique existante
 * supposait qu'il n'existait qu'une forme d'item, et nulle part ailleurs.</p>
 *
 * <p>s28 : une troisieme mecanique est arrivee. {@link ZoneADesigner} est la forme d'item de la
 * mecanique de designation (ADR-0080 decision 1) - l'enfant montre un endroit sur une image, il ne
 * choisit pas un texte et n'en ecrit pas un. La couture scellee a de nouveau fait son travail :
 * ouvrir la troisieme permission arrete le compilateur a huit endroits exacts (ADR-0080, decision
 * 1), et nulle part ailleurs.</p>
 */
public sealed interface Item permits Question, ReponseAEcrire, ZoneADesigner {

    ItemId id();
}
