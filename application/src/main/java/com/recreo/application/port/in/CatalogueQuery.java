package com.recreo.application.port.in;

import java.util.List;

/** Use case (lecture) : lister les entrees du catalogue de jeux. */
public interface CatalogueQuery {
    List<CatalogEntryView> entries();
}
