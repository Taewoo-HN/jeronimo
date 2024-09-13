package org.big18.finale.repository.stocks;

import org.big18.finale.entity.stocks.A022100Trends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface A022100TrendsRepository extends JpaRepository< A022100Trends, Instant> {
    @Query(value = "SELECT * FROM A022100_trends WHERE jdate = CURDATE() - INTERVAL 1 DAY", nativeQuery = true)
    Optional< A022100Trends> findByIdWithYesterdayDate();
}
