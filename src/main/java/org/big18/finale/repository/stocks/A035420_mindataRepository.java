package org.big18.finale.repository.stocks;


import org.big18.finale.entity.stocks.A035420_mindata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface A035420_mindataRepository extends JpaRepository<A035420_mindata, Instant> {
}