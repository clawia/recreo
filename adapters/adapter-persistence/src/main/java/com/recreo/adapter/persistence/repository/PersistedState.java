package com.recreo.adapter.persistence.repository;

import com.recreo.domain.content.Item;
import com.recreo.domain.mechanic.Reel;

import java.util.List;

/** Partie "riche" de l'etat de session serialisee en JSON. */
record PersistedState(Item currentItem, List<Reel> reels) {
}
