package com.recreo.application.contreexemple;

import org.springframework.util.StringUtils;

/**
 * ADR-0003 : viole ADR-0002. Symetrique de DomainePollueContreExemple pour la seconde regle de
 * PureteDesCouchesTest, lApplicationNeDependQueDuJdkEtDuDomaine, qui n'avait jamais eu a echouer.
 *
 * <p>ADR-0021 : pollution par un utilitaire de Spring plutot que par un stereotype, meme motif que
 * DomainePollueContreExemple.</p>
 */
public class ApplicationPollueeContreExemple {

    boolean renseigne(String valeur) {
        return StringUtils.hasText(valeur);
    }
}
