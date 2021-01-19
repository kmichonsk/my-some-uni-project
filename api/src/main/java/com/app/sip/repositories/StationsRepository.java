package com.app.sip.repositories;

import com.app.sip.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationsRepository extends JpaRepository<Station, Long> {
}
