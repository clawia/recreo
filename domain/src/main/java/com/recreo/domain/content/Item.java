package com.recreo.domain.content;

/**
 * ADR-0004 : une seule forme d'item est permise au sprint 0. Une deuxieme permission ne sera
 * ajoutee que par la mecanique qui en aura besoin.
 *
 * <p>s24 : cette mecanique-la est arrivee. {@link ReponseAEcrire} est la forme d'item de la
 * mecanique de saisie (ADR-0058 section 1). La couture scellee a fait son travail : ouvrir la
 * seconde permission arrete le compilateur aux deux endroits exacts ou une mecanique existante
 * supposait qu'il n'existait qu'une forme d'item, et nulle part ailleurs.</p>
 */
public sealed interface Item permits Question, ReponseAEcrire {

    ItemId id();
}
