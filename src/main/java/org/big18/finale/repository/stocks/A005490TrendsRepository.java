package org.big18.finale.repository.stocks;

import org.big18.finale.entity.stocks.A005380Trends;
import org.big18.finale.entity.stocks.A005490Trends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface A005490TrendsRepository extends JpaRepository<A005490Trends, LocalDate> {
    @Query(value = "SELECT * FROM A005380_trends WHERE jdate = CURDATE() - INTERVAL 1 DAY", nativeQuery = true)
    Optional<A005380Trends> findByIdWithYesterdayDate();

}
