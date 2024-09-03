package org.big18.finale.repository.marketStat;

import org.big18.finale.entity.marketStat.Kosdaq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


@Repository
public interface KosdaqRepository extends JpaRepository<Kosdaq, LocalDate> {
}
