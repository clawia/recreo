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
import com.recreo.domain.mechanic.MechanicId;
import com.recreo.domain.mechanics.banditmanchot.BanditManchotMechanic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
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

        List<CatalogEntryDef> defs = new ArrayList<>();

        // Calcul mental (CP, CM1) : difficulte de base pilotee par YAML, reglages
        // de session appliques par la MathGameFactory (fenetre + operations bornees).
        for (ContentPack pack : packs) {
            MechanicId mechanicId = new MechanicId(pack.mechanic());
            MathGameFactory factory = new MathGameFactory(
                    pack.entryId(), pack.title(), pack.gameKey(), mechanicId,
                    pack.descriptor(), pack.difficulty(), pack.bounds());
            defs.add(new CatalogEntryDef(
                    pack.entryId(), pack.descriptor().matiere(), pack.descriptor().niveau(),
                    pack.gameKey(), pack.gameLabel(), pack.title(), pack.description(),
                    mechanicId, factory));
        }

        // Francais CP (jeu "syllabes") : MEME mecanique bandit manchot, autre
        // ContentGenerator, reglage de session = nombre de syllabes max (filtre).
        ContentDescriptor syllabesDescriptor =
                new ContentDescriptor("Français", "CP", "Phonologie — dénombrer les syllabes");
        SyllabesGameFactory syllabesFactory = new SyllabesGameFactory(
                "francais-cp", "Français CP — Les syllabes", "syllabes",
                BanditManchotMechanic.ID, syllabesDescriptor,
                List.of(2, 3, 4), 1, 4);
        defs.add(new CatalogEntryDef(
                "francais-cp", "Français", "CP", "syllabes", "Les syllabes",
                "Français CP — Les syllabes", "Compte les syllabes des mots.",
                BanditManchotMechanic.ID, syllabesFactory));

        return new InMemoryGameCatalog(List.of(banditManchotMechanic), defs);
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
