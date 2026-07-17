package com.recreo.adapter.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

/**
 * Representation JPA d'une session. L'etat "riche" (item courant + rouleaux) est
 * serialise en JSON dans une colonne texte pour eviter un schema complexe en
 * Phase 0 ; les champs scalaires restent des colonnes simples.
 */
@Entity
@Table(name = "game_sessions")
public class SessionEntity {

    @Id
    @Column(name = "id", nullable = false, length = 64)
    private String id;

    @Column(name = "mechanic_id", nullable = false, length = 64)
    private String mechanicId;

    @Column(name = "catalog_entry_id", length = 128)
    private String catalogEntryId;

    @Column(name = "score", nullable = false)
    private int score;

    @Column(name = "round", nullable = false)
    private int round;

    @Column(name = "status", nullable = false, length = 32)
    private String status;

    @Lob
    @Column(name = "state_json", nullable = false, length = 100000)
    private String stateJson;

    protected SessionEntity() {
        // requis par JPA
    }

    public SessionEntity(String id, String mechanicId, String catalogEntryId,
                         int score, int round, String status, String stateJson) {
        this.id = id;
        this.mechanicId = mechanicId;
        this.catalogEntryId = catalogEntryId;
        this.score = score;
        this.round = round;
        this.status = status;
        this.stateJson = stateJson;
    }

    public String getId() {
        return id;
    }

    public String getMechanicId() {
        return mechanicId;
    }

    public String getCatalogEntryId() {
        return catalogEntryId;
    }

    public int getScore() {
        return score;
    }

    public int getRound() {
        return round;
    }

    public String getStatus() {
        return status;
    }

    public String getStateJson() {
        return stateJson;
    }
}
