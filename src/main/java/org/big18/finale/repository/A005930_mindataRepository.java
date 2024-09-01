package org.big18.finale.repository;

import org.big18.finale.entity.A005930_mindata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface A005930_mindataRepository extends JpaRepository<A005930_mindata, Timestamp> {

    A005930_mindata findTopByOrderByJdateDesc();

    @Query(value = "SELECT * FROM A000660_mindata WHERE DATE_FORMAT(Jdate, '%Y-%m-%d %H:%i') = :date LIMIT 1", nativeQuery = true)
    A005930_mindata findFirstByJdateStartingWith(String date);
}
