package org.big18.finale.repository.stocks;

import org.big18.finale.entity.stocks.A247540Trends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface A247540TrendsRepository extends JpaRepository< A247540Trends, LocalDate> {
    @Query(value = "SELECT * FROM A247540_trends WHERE jdate = CURDATE() - INTERVAL 1 DAY", nativeQuery = true)
    Optional<A247540Trends> findByIdWithYesterdayDate();
}
