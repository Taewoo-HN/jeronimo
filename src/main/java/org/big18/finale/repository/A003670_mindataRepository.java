package org.big18.finale.repository;

import org.big18.finale.entity.A003670_mindata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface A003670_mindataRepository extends JpaRepository<A003670_mindata, Timestamp> {

    A003670_mindata findTopByOrderByJdateDesc();

    @Query(value = "SELECT * FROM A000660_mindata WHERE DATE_FORMAT(Jdate, '%Y-%m-%d %H:%i') = :date LIMIT 1", nativeQuery = true)
    A003670_mindata findFirstByJdateStartingWith(String date);
}
