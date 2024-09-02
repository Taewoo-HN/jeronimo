package org.big18.finale.repository;

import org.big18.finale.entity.Kospi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface KospiRepository extends JpaRepository<Kospi, LocalDate> {
}
