package com.recreo.adapter.persistence.repository;

import com.recreo.adapter.persistence.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/** Repository Spring Data pour {@link SessionEntity}. */
public interface SpringDataSessionRepository extends JpaRepository<SessionEntity, String> {
}
