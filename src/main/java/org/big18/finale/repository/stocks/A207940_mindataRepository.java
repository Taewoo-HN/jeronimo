package org.big18.finale.repository.stocks;

import org.big18.finale.entity.stocks.A207940_mindata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface A207940_mindataRepository extends JpaRepository<A207940_mindata, Timestamp> {
}