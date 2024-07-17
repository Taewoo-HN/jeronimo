package org.big18.finale.repository;

import org.big18.finale.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository
        extends JpaRepository<News, Integer> {
}
