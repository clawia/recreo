package com.recreo.bootstrap.config;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.HttpSessionMutexListener;

/**
 * ADR-0019 : EtatDePartieEnSession serialise les requetes d'une meme session sur le verrou que
 * WebUtils.getSessionMutex designe. Sans ce listener, ce verrou est l'objet HttpSession lui-meme,
 * dont le conteneur ne garantit pas de rendre la meme instance a deux requetes - et deux requetes
 * qui verrouillent deux objets differents ne se serialisent pas du tout. Le listener pose, a la
 * creation de la session, l'objet stable que WebUtils lit ensuite ; il meurt avec elle.
 *
 * <p>La panne que ce bean previent est silencieuse : le code resterait identique et le verrou ne
 * verrouillerait plus rien. Sa presence est donc verifiee par un test de contexte
 * (VerrouDeSessionEnregistreTest), pas seulement ecrite ici.</p>
 */
@Configuration
class SessionConfiguration {

    @Bean
    ServletListenerRegistrationBean<HttpSessionMutexListener> verrouDeSession() {
        return new ServletListenerRegistrationBean<>(new HttpSessionMutexListener());
    }
}
