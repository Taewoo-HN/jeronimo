package org.big18.finale.repository.stocks;

import org.big18.finale.entity.stocks.A005490_mindata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface A005490_mindataRepository extends JpaRepository<A005490_mindata, Timestamp> {

    A005490_mindata findTopByOrderByJdateDesc();

    @Query(value = "SELECT * FROM A000660_mindata WHERE DATE_FORMAT(Jdate, '%Y-%m-%d %H:%i') = :date LIMIT 1", nativeQuery = true)
    A005490_mindata findFirstByJdateStartingWith(String date);
}
