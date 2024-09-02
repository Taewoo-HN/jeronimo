package org.big18.finale.repository;

import org.big18.finale.entity.Dollar;
import org.big18.finale.entity.Kosdaq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;


@Repository
public interface KosdaqRepository extends JpaRepository<Kosdaq, LocalDate> {
}
