package org.big18.finale.repository.stocks;

import org.big18.finale.entity.stocks.A068270Trend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;

@Repository
public interface A068270TrendsRepository extends JpaRepository<A068270Trend, LocalDate> {
}
