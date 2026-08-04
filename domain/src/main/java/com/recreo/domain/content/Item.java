package com.recreo.domain.content;

/**
 * ADR-0004 : une seule forme d'item est permise au sprint 0. Une deuxieme permission ne sera
 * ajoutee que par la mecanique qui en aura besoin.
 */
public sealed interface Item permits Question {

    ItemId id();
}
