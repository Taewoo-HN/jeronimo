package org.big18.finale.repository.stocks;

import org.big18.finale.entity.stocks.A196170_mindata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface A196170_mindataRepository extends JpaRepository<A196170_mindata, Timestamp> {

    A196170_mindata findTopByOrderByJdateDesc();

    @Query(value = "SELECT * FROM A000660_mindata WHERE DATE_FORMAT(Jdate, '%Y-%m-%d %H:%i') = :date LIMIT 1", nativeQuery = true)
    A196170_mindata findFirstByJdateStartingWith(String date);
}
