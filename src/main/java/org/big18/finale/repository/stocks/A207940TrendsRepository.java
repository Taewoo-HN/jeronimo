package org.big18.finale.repository.stocks;

import org.big18.finale.entity.stocks.A207940Trend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface A207940TrendsRepository extends JpaRepository<A207940Trend, LocalDate> {
}
