package org.big18.finale.repository;

import org.big18.finale.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository
        extends JpaRepository<Board,Long> {
}
