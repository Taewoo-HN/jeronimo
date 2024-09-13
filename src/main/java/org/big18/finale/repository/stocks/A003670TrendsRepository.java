package org.big18.finale.repository.stocks;

import org.big18.finale.entity.stocks.A003670Trends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface A003670TrendsRepository extends JpaRepository<A003670Trends, LocalDate> {
    @Query(value = "SELECT * FROM A003670_trends WHERE jdate = CURDATE() - INTERVAL 1 DAY", nativeQuery = true)
    Optional<A003670Trends> findByIdWithYesterdayDate();
}
