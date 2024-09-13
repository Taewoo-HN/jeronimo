package org.big18.finale.repository.stocks;

import org.big18.finale.entity.stocks.A000660Trends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;


@Repository
public interface A000660TrendsRepository extends JpaRepository<A000660Trends, LocalDate> {
    @Query(value = "SELECT * FROM A000660_trends WHERE jdate = CURDATE() - INTERVAL 1 DAY", nativeQuery = true)
    Optional<A000660Trends> findByIdWithYesterdayDate();
}


