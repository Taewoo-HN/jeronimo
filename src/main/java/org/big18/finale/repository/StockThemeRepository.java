package org.big18.finale.repository;

import org.big18.finale.entity.StockTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockThemeRepository extends JpaRepository<StockTheme, Long> {
}