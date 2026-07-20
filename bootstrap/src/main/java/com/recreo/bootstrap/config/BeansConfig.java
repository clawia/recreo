package com.recreo.bootstrap.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recreo.adapter.persistence.repository.SessionRepositoryJpaAdapter;
import com.recreo.adapter.persistence.repository.SpringDataSessionRepository;
import com.recreo.adapter.web.mapper.WebViewMapper;
import com.recreo.application.port.out.GameCatalog;
import com.recreo.application.port.out.SessionRepository;
import com.recreo.application.service.SessionService;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentGenerator;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.impl.AccordsCm1Generator;
import com.recreo.domain.content.impl.AnglaisLexiqueGenerator;
import com.recreo.domain.content.impl.ComparaisonGenerator;
import com.recreo.domain.content.impl.ComplementDixGenerator;
import com.recreo.domain.content.impl.ConjugaisonCm1Generator;
import com.recreo.domain.content.impl.EgaliteGenerator;
import com.recreo.domain.content.impl.HomophonesGenerator;
import com.recreo.domain.content.impl.LeBonSonGenerator;
import com.recreo.domain.content.impl.MotManquantCpGenerator;
import com.recreo.domain.content.impl.NatureMotGenerator;
import com.recreo.domain.content.impl.PremiereLettreGenerator;
import com.recreo.domain.content.impl.QuizGenerator;
import com.recreo.domain.mechanic.MechanicId;
import com.recreo.domain.mechanics.banditmanchot.BanditManchotMechanic;
import com.recreo.domain.mechanics.quizaventure.QuizAventureMechanic;
import com.recreo.domain.mechanics.texteatrou.TexteATrouMechanic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
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
    public TexteATrouMechanic texteATrouMechanic() {
        return new TexteATrouMechanic();
    }

    @Bean
    public QuizAventureMechanic quizAventureMechanic() {
        return new QuizAventureMechanic();
    }

    @Bean
    public GameCatalog gameCatalog(BanditManchotMechanic banditManchotMechanic,
                                   TexteATrouMechanic texteATrouMechanic,
                                   QuizAventureMechanic quizAventureMechanic) {
        ContentPackLoader loader = new ContentPackLoader();
        List<ContentPack> packs = List.of(
                loader.load("content/calcul-mental-cp.yml"),
                loader.load("content/calcul-mental-cm1.yml"),
                loader.load("content/tables-multiplication-cm1.yml"));

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

        // Declinaisons s04 : 6 jeux SANS reglage, MEME mecanique bandit manchot,
        // uniquement de nouveaux ContentGenerator (cf. US-s04 / ADR-0004/0005).
        defs.add(simpleGame(
                "complement-dix", "Maths", "CP", "complement-dix", "Compléments à 10",
                "Compléments à 10", "Trouve ce qu'il manque pour faire 10.",
                new ContentDescriptor("Mathématiques", "CP", "Nombres et calculs — compléments à 10"),
                ComplementDixGenerator::new));
        defs.add(simpleGame(
                "comparaison", "Maths", "CP", "comparaison", "Le plus grand nombre",
                "Le plus grand nombre", "Choisis le plus grand des deux nombres.",
                new ContentDescriptor("Mathématiques", "CP", "Nombres et calculs — comparer des nombres"),
                ComparaisonGenerator::new));
        defs.add(simpleGame(
                "premiere-lettre", "Français", "CP", "premiere-lettre", "La première lettre",
                "La première lettre", "Trouve la lettre qui commence le mot.",
                new ContentDescriptor("Français", "CP", "Lecture — principe alphabétique"),
                PremiereLettreGenerator::new));
        defs.add(simpleGame(
                "homophones", "Français", "CM1", "homophones", "Le bon petit mot",
                "Le bon petit mot", "Choisis le mot qui complète la phrase.",
                new ContentDescriptor("Français", "CM1", "Orthographe — homophones grammaticaux"),
                HomophonesGenerator::new));
        defs.add(simpleGame(
                "nature-mot", "Français", "CM1", "nature-mot", "La nature du mot",
                "La nature du mot", "Nom, verbe, adjectif ou déterminant ?",
                new ContentDescriptor("Français", "CM1", "Grammaire — classes de mots"),
                NatureMotGenerator::new));
        defs.add(simpleGame(
                "anglais-lexique", "Anglais", "CM1", "anglais-lexique", "Le bon mot en anglais",
                "Le bon mot en anglais", "Trouve la traduction anglaise.",
                new ContentDescriptor("Anglais", "CM1", "Lexique — vocabulaire courant"),
                AnglaisLexiqueGenerator::new));

        // Contenu s08 : "Le bon son" (Francais CP, decodage graphème/phonème). MEME
        // mecanique bandit manchot ; comble un trou de la matrice (phonologie CP).
        defs.add(simpleGame(
                "le-bon-son", "Français", "CP", "le-bon-son", "Le bon son",
                "Le bon son", "Quel son entends-tu dans le mot ?",
                new ContentDescriptor("Français", "CP", "Décodage — association graphème/phonème"),
                LeBonSonGenerator::new));

        // 2e mecanique (s06 / ADR-0007) : "texte a trous". MEME moteur de session
        // partage, presentation DISTINCTE (phrase a completer + mots a cliquer). Le
        // reglage commun "nombre de questions" est fourni par SimpleGameFactory.
        defs.add(texteATrouGame(
                "conjugaison-cm1", "Français", "CM1", "conjugaison", "Conjugaison (présent)",
                "Français CM1 — Conjugaison (présent)", "Complète la phrase avec le bon verbe.",
                new ContentDescriptor("Français", "CM1", "Conjugaison — présent de l'indicatif"),
                ConjugaisonCm1Generator::new));
        defs.add(texteATrouGame(
                "accords-cm1", "Français", "CM1", "accords-adjectif", "L'accord de l'adjectif",
                "Français CM1 — L'accord de l'adjectif", "Accorde l'adjectif dans la phrase.",
                new ContentDescriptor("Français", "CM1", "Grammaire — accord de l'adjectif"),
                AccordsCm1Generator::new));
        defs.add(texteATrouGame(
                "mot-manquant-cp", "Français", "CP", "mot-manquant", "Le mot qui manque",
                "Français CP — Le mot qui manque", "Trouve le mot qui complète la phrase.",
                new ContentDescriptor("Français", "CP", "Lecture — compréhension (sens)"),
                MotManquantCpGenerator::new));
        defs.add(texteATrouGame(
                "egalite-cp", "Maths", "CP", "egalite", "Complète l'égalité",
                "Maths CP — Complète l'égalité", "Trouve le nombre qui manque dans l'égalité.",
                new ContentDescriptor("Mathématiques", "CP", "Nombres et calculs — compléter une égalité"),
                EgaliteGenerator::new));

        // 3e mecanique (s07 / ADR-0007) : "quiz-aventure". Le CONTENU est desormais
        // EXTERNALISE (s09 / DEC-0006 / ADR-0009) : chaque jeu quiz est un fichier YAML
        // editable sous content/quiz/*.yml, charge et VALIDE au demarrage. Le domaine
        // n'expose qu'un QuizGenerator generique alimente par la liste chargee ; il n'y
        // a plus une sous-classe Java par jeu. Ajouter/editer un quiz = editer un YAML.
        for (QuizContentPack quiz : new QuizPackLoader().loadAll()) {
            defs.add(quizAventureGame(
                    quiz.entryId(), quiz.descriptor().matiere(), quiz.descriptor().niveau(),
                    quiz.gameKey(), quiz.gameLabel(), quiz.title(), quiz.description(),
                    quiz.descriptor(), descriptor -> new QuizGenerator(descriptor, quiz.questions())));
        }

        return new InMemoryGameCatalog(
                List.of(banditManchotMechanic, texteATrouMechanic, quizAventureMechanic), defs);
    }

    /**
     * Construit une entree de jeu SANS reglage : meme mecanique bandit manchot, un
     * {@link ContentGenerator} pilote par sa {@link ContentDescriptor}. La
     * {@link SimpleGameFactory} garantit « aucun reglage » (pas de bouton Paramètres).
     */
    private static CatalogEntryDef simpleGame(
            String entryId, String matiere, String niveau, String gameKey, String gameLabel,
            String title, String description, ContentDescriptor descriptor,
            Function<ContentDescriptor, ContentGenerator> generator) {
        SimpleGameFactory factory = new SimpleGameFactory(
                entryId, title, gameKey, niveau, BanditManchotMechanic.ID,
                () -> generator.apply(descriptor));
        return new CatalogEntryDef(
                entryId, matiere, niveau, gameKey, gameLabel, title, description,
                BanditManchotMechanic.ID, factory);
    }

    /**
     * Construit une entree de jeu "texte a trous" (2e mecanique, cf. ADR-0007) :
     * meme fabrique de reglage commun ({@link SimpleGameFactory}, nombre de questions
     * borne par le vivier), mais rattachee a {@link TexteATrouMechanic}. La
     * mecanique n'est PAS devinee : elle est portee par l'entree de catalogue.
     */
    private static CatalogEntryDef texteATrouGame(
            String entryId, String matiere, String niveau, String gameKey, String gameLabel,
            String title, String description, ContentDescriptor descriptor,
            Function<ContentDescriptor, ContentGenerator> generator) {
        SimpleGameFactory factory = new SimpleGameFactory(
                entryId, title, gameKey, niveau, TexteATrouMechanic.ID,
                () -> generator.apply(descriptor));
        return new CatalogEntryDef(
                entryId, matiere, niveau, gameKey, gameLabel, title, description,
                TexteATrouMechanic.ID, factory);
    }

    /**
     * Construit une entree de jeu "quiz-aventure" (3e mecanique, cf. ADR-0007) : meme
     * fabrique de reglage commun ({@link SimpleGameFactory}, nombre de questions borne
     * par le vivier), mais rattachee a {@link QuizAventureMechanic}. La mecanique n'est
     * PAS devinee : elle est portee par l'entree de catalogue.
     */
    private static CatalogEntryDef quizAventureGame(
            String entryId, String matiere, String niveau, String gameKey, String gameLabel,
            String title, String description, ContentDescriptor descriptor,
            Function<ContentDescriptor, ContentGenerator> generator) {
        SimpleGameFactory factory = new SimpleGameFactory(
                entryId, title, gameKey, niveau, QuizAventureMechanic.ID,
                () -> generator.apply(descriptor));
        return new CatalogEntryDef(
                entryId, matiere, niveau, gameKey, gameLabel, title, description,
                QuizAventureMechanic.ID, factory);
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
