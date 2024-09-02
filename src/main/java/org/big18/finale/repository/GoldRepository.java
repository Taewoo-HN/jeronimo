package org.big18.finale.repository;

import org.big18.finale.entity.Gold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface GoldRepository extends JpaRepository<Gold, Timestamp> {
}
