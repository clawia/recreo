package com.recreo.domain.contreexemple;

import org.springframework.stereotype.Component;

/**
 * ADR-0003 : viole ADR-0002. Cette classe n'est jamais instanciee, elle existe pour etre refusee
 * par PureteDesCouchesTest.leDomaineNeDependQueDuJdk.
 */
@Component
public class DomainePollueContreExemple {
}
