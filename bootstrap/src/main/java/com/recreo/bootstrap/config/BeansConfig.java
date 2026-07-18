package com.recreo.bootstrap.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recreo.adapter.persistence.repository.SessionRepositoryJpaAdapter;
import com.recreo.adapter.persistence.repository.SpringDataSessionRepository;
import com.recreo.adapter.web.mapper.WebViewMapper;
import com.recreo.application.port.out.GameCatalog;
import com.recreo.application.port.out.SessionRepository;
import com.recreo.application.service.SessionService;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.impl.SyllabesCpGenerator;
import com.recreo.domain.mechanic.GameConfig;
import com.recreo.domain.mechanics.banditmanchot.BanditManchotMechanic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
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

        // Francais CP : la difficulte n'est pas pilotable par le YAML maths
        // (DifficultyConfig/operations) -> entree cablee en code (cf. ADR-0004),
        // reutilisant la MEME mecanique bandit manchot avec un autre ContentGenerator.
        SyllabesCpGenerator syllabesGenerator = new SyllabesCpGenerator(
                new ContentDescriptor("Français", "CP", "Phonologie — dénombrer les syllabes"));
        GameConfig francaisCp = new GameConfig(
                BanditManchotMechanic.ID, syllabesGenerator, Map.of("niveau", "CP"));
        List<InMemoryGameCatalog.CodedEntry> coded = List.of(
                new InMemoryGameCatalog.CodedEntry(
                        "francais-cp",
                        "Français CP — Les syllabes",
                        "Compte les syllabes des mots.",
                        francaisCp));

        return new InMemoryGameCatalog(List.of(banditManchotMechanic), packs, coded);
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
