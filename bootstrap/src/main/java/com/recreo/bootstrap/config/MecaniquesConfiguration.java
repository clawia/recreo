package com.recreo.bootstrap.config;

import com.recreo.application.port.out.MechanicRegistryPort;
import com.recreo.domain.mechanic.GameMechanic;
import com.recreo.domain.mechanic.MechanicId;
import com.recreo.domain.mechanics.banditmanchot.BanditManchot;
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

    @Bean
    MechanicRegistryPort mechanicRegistryPort(List<GameMechanic> mecaniques) {
        Map<MechanicId, GameMechanic> parIdentifiant = mecaniques.stream()
                .collect(toMap(GameMechanic::id, Function.identity()));
        return parIdentifiant::get;
    }
}
