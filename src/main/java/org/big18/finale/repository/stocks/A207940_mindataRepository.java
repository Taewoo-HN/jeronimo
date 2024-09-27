package org.big18.finale.repository.stocks;

import org.big18.finale.entity.stocks.A207940_mindata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface A207940_mindataRepository extends JpaRepository<A207940_mindata, Instant> {
}