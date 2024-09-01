package org.big18.finale.repository;

import org.big18.finale.entity.A000660_mindata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface A000660_mindataRepository extends JpaRepository<A000660_mindata, Timestamp> {

    A000660_mindata findTopByOrderByJdateDesc();

    @Query(value = "SELECT * FROM A000660_mindata WHERE DATE_FORMAT(Jdate, '%Y-%m-%d %H:%i') = :date LIMIT 1", nativeQuery = true)
    A000660_mindata findFirstByJdateStartingWith(String date);
}
