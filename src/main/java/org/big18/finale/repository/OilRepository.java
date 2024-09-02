package org.big18.finale.repository;

import org.big18.finale.entity.Kospi;
import org.big18.finale.entity.Oil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface OilRepository extends JpaRepository<Oil, LocalDate> {
}
