package com.recreo.adapter.web;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Marqueur exige par @WebMvcTest pour demarrer un contexte sans dependre de bootstrap, qui n'est
 * pas sur le classpath de ce module (ADR-0001 : les adaptateurs ne se voient pas entre eux).
 */
@SpringBootApplication
class ConfigurationDeTest {
}
