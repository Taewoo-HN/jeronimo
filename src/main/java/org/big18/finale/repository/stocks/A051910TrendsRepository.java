package org.big18.finale.repository.stocks;

import org.big18.finale.entity.stocks.A051910Trend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface A051910TrendsRepository extends JpaRepository<A051910Trend, Instant> {
}
