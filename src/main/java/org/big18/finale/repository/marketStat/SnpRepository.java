package org.big18.finale.repository.marketStat;

import org.big18.finale.entity.marketStat.Sp500;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface SnpRepository extends JpaRepository<Sp500, LocalDate> {
}
