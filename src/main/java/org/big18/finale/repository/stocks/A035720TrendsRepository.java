package org.big18.finale.repository.stocks;

import org.big18.finale.entity.stocks.A035720Trend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface A035720TrendsRepository extends JpaRepository<A035720Trend, LocalDate> {
}
