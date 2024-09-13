package org.big18.finale.repository.stocks;

import org.big18.finale.entity.stocks.A042700Trends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface A042700TrendsRepository extends JpaRepository< A042700Trends, LocalDate> {
    @Query(value = "SELECT * FROM A042700_trends WHERE jdate = CURDATE() - INTERVAL 1 DAY", nativeQuery = true)
    Optional<A042700Trends> findByIdWithYesterdayDate();
}
