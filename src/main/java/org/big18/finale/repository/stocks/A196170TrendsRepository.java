package org.big18.finale.repository.stocks;

import org.big18.finale.entity.stocks.A196170Trends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface A196170TrendsRepository extends JpaRepository<A196170Trends, LocalDate> {
    @Query(value = "SELECT * FROM A196170_trends WHERE jdate = CURDATE() - INTERVAL 1 DAY", nativeQuery = true)
    Optional<A196170Trends> findByIdWithYesterdayDate();
}
