package com.recreo.bootstrap.config;

import com.recreo.domain.content.RandomSource;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import java.util.Random;

/**
 * ADR-0004 : RandomSource est un contrat ferme aux classes concretes, il ne peut donc pas vivre
 * dans com.recreo.domain.content. bootstrap est retenu comme le module qui l'implemente.
 * Auto-configuration (et non @Configuration simple) : ConditionalOnMissingBean n'est garanti que
 * face a un bean utilisateur si ce bean par defaut est traite apres lui, ce que seule
 * l'auto-configuration assure. Un test peut ainsi le remplacer par un tirage a graine fixe.
 */
@AutoConfiguration
class HasardConfiguration {

    @Bean
    @ConditionalOnMissingBean(RandomSource.class)
    RandomSource hasard() {
        Random alea = new Random();
        return alea::nextInt;
    }
}
