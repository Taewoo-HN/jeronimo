package org.big18.finale.repository;

import org.big18.finale.entity.Kospi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface KospiRepository extends JpaRepository<Kospi, Timestamp> {
}
