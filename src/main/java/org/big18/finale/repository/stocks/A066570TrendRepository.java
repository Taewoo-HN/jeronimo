package org.big18.finale.repository.stocks;

import org.big18.finale.entity.stocks.A066570Trend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface A066570TrendRepository extends JpaRepository<A066570Trend, LocalDate> {
}
