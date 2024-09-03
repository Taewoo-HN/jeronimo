package org.big18.finale.repository.marketStat;

import org.big18.finale.entity.marketStat.Kospi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface KospiRepository extends JpaRepository<Kospi, LocalDate> {
}
