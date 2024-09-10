package org.big18.finale.repository;

import org.big18.finale.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository
        extends JpaRepository<News, Integer> {

    List<News> findByNewsTitleContaining(String newsTitle);
}
