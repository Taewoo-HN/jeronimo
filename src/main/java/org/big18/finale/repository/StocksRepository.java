package org.big18.finale.repository;

import org.big18.finale.entity.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface StocksRepository extends JpaRepository<Stocks, Long> {
    Optional<Stocks> findByCode(String code);
}
