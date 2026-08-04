package com.recreo.application.port.out;

import com.recreo.application.catalog.GameDefinition;

import java.util.List;

/**
 * Port sortant du catalogue. ADR-0007 : sa seule implementation est
 * com.recreo.adapter.content.YamlContentPackLoader.
 */
public interface ContentCatalogPort {

    List<GameDefinition> games();
}
