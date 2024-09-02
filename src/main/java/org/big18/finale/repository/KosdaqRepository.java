package org.big18.finale.repository;

import org.big18.finale.entity.Kosdaq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;


@Repository
public interface KosdaqRepository extends JpaRepository<Kosdaq, Timestamp> {
}
