package org.big18.finale.repository.marketStat;

import org.big18.finale.entity.marketStat.Oil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface OilRepository extends JpaRepository<Oil, LocalDate> {
}
