package org.big18.finale.repository;

import org.big18.finale.entity.Sp500;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface SnpRepository extends JpaRepository<Sp500, Timestamp> {
}
