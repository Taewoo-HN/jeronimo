package org.big18.finale.repository.stocks;

import org.big18.finale.entity.stocks.A247540_mindata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface A247540_mindataRepository extends JpaRepository<A247540_mindata, Timestamp> {

    A247540_mindata findTopByOrderByJdateDesc();

    @Query(value = "SELECT * FROM A000660_mindata WHERE DATE_FORMAT(Jdate, '%Y-%m-%d %H:%i') = :date LIMIT 1", nativeQuery = true)
    A247540_mindata findFirstByJdateStartingWith(String date);
}
