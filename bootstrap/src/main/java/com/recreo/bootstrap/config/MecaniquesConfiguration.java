package com.recreo.bootstrap.config;

import com.recreo.application.port.out.MechanicRegistryPort;
import com.recreo.domain.mechanic.GameMechanic;
import com.recreo.domain.mechanic.MechanicId;
import com.recreo.domain.mechanics.banditmanchot.BanditManchot;
import com.recreo.domain.mechanics.designation.Designation;
import com.recreo.domain.mechanics.saisie.Saisie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

/**
 * bootstrap connait toutes les mecaniques (ADR-0004 : l'application ne les nomme pas). Ajouter
 * une mecanique se fait ici, d'un bean supplementaire, sans toucher au cas d'usage.
 */
@Configuration
class MecaniquesConfiguration {

    @Bean
    GameMechanic banditManchot() {
        return new BanditManchot();
    }

    @Bean
    GameMechanic saisie() {
        return new Saisie();
    }

    /**
     * ADR-0080, T4, lot L-a : le bean qui cable la troisieme mecanique. Sans lui, {@code Designation}
     * existe dans {@code domain} depuis s28 et n'est jouable par personne -
     * {@code ChaqueMecaniqueDuDomaineEstAtteignableDepuisLeProduitTest} le disait en rouge tant que ce
     * bean manquait.
     */
    @Bean
    GameMechanic designation() {
        return new Designation();
    }

    @Bean
    MechanicRegistryPort mechanicRegistryPort(List<GameMechanic> mecaniques) {
        Map<MechanicId, GameMechanic> parIdentifiant = mecaniques.stream()
                .collect(toMap(GameMechanic::id, Function.identity()));
        return parIdentifiant::get;
    }
}
