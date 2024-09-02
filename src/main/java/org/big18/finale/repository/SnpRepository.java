package org.big18.finale.repository;

import org.big18.finale.entity.Sp500;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SnpRepository extends JpaRepository<Sp500, LocalDate> {
}
