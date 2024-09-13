package org.big18.finale.repository.stocks;

import org.big18.finale.entity.stocks.A086520Trends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface A086520TrendsRepository extends JpaRepository<A086520Trends, LocalDate> {
    @Query(value = "SELECT * FROM A086520_trends WHERE jdate = CURDATE() - INTERVAL 1 DAY", nativeQuery = true)
    Optional<A086520Trends> findByIdWithYesterdayDate();
}
