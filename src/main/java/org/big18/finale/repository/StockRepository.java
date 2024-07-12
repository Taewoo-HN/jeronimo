package org.big18.finale.repository;

import org.big18.finale.entity.StockDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository
        extends JpaRepository<StockDTO,String> {
}
