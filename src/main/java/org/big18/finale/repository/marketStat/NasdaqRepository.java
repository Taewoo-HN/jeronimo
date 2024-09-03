package org.big18.finale.repository.marketStat;

import org.big18.finale.entity.marketStat.Nasdaq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface NasdaqRepository extends JpaRepository<Nasdaq, LocalDate> {
}
