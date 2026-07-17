package com.recreo.bootstrap.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recreo.adapter.persistence.repository.SessionRepositoryJpaAdapter;
import com.recreo.adapter.persistence.repository.SpringDataSessionRepository;
import com.recreo.adapter.web.mapper.WebViewMapper;
import com.recreo.application.port.out.GameCatalog;
import com.recreo.application.port.out.SessionRepository;
import com.recreo.application.service.SessionService;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.mechanics.banditmanchot.BanditManchotMechanic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Supplier;

/**
 * Wiring de l'application. Les use cases, le catalogue et les adapters sont
 * cables ici ; aucune annotation Spring n'est necessaire dans domain/application.
 */
@Configuration
public class BeansConfig {

    @Bean
    public BanditManchotMechanic banditManchotMechanic() {
        return new BanditManchotMechanic();
    }

    @Bean
    public GameCatalog gameCatalog(BanditManchotMechanic banditManchotMechanic) {
        ContentPackLoader loader = new ContentPackLoader();
        List<ContentPack> packs = List.of(
                loader.load("content/calcul-mental-cp.yml"),
                loader.load("content/calcul-mental-cm1.yml"));
        return new InMemoryGameCatalog(List.of(banditManchotMechanic), packs);
    }

    @Bean
    public Supplier<RandomSource> randomSourceFactory() {
        return JdkRandomSource::new;
    }

    @Bean
    public SessionRepository sessionRepository(SpringDataSessionRepository springDataRepository,
                                               ObjectMapper objectMapper) {
        return new SessionRepositoryJpaAdapter(springDataRepository, objectMapper);
    }

    @Bean
    public SessionService sessionService(GameCatalog gameCatalog,
                                         SessionRepository sessionRepository,
                                         Supplier<RandomSource> randomSourceFactory) {
        return new SessionService(gameCatalog, sessionRepository, randomSourceFactory);
    }

    @Bean
    public WebViewMapper webViewMapper() {
        return new WebViewMapper();
    }
}
