package com.recreo.domain.contreexemple;

import org.springframework.util.StringUtils;

/**
 * ADR-0003 : viole ADR-0002. Cette classe n'est jamais instanciee, elle existe pour etre refusee
 * par PureteDesCouchesTest.leDomaineNeDependQueDuJdk.
 *
 * <p>ADR-0021 : la pollution est un appel a un utilitaire de Spring, non une annotation de
 * stereotype. Le stereotype faisait de ce contre-exemple un vrai bean de l'application des qu'un
 * contexte complet demarrait, et la regle qu'il sert mord identiquement sur les deux formes : elle
 * refuse toute dependance hors du JDK et du domaine. Cette forme-ci est d'ailleurs la plus proche
 * de la dette reelle - personne ne pose un stereotype sur une entite de domaine, tout le monde est
 * tente d'y appeler un utilitaire de chaine.</p>
 */
public class DomainePollueContreExemple {

    boolean renseigne(String valeur) {
        return StringUtils.hasText(valeur);
    }
}
