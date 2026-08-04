package com.recreo.application.contreexemple;

import org.springframework.stereotype.Service;

/**
 * ADR-0003 : viole ADR-0002. Symetrique de DomainePollueContreExemple pour la seconde regle de
 * PureteDesCouchesTest, lApplicationNeDependQueDuJdkEtDuDomaine, qui n'avait jamais eu a echouer.
 */
@Service
public class ApplicationPollueeContreExemple {
}
