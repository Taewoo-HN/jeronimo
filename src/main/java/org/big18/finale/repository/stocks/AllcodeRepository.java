package org.big18.finale.repository.stocks;

import org.big18.finale.entity.stocks.Allcode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllcodeRepository extends JpaRepository<Allcode, String> {

    @Query("SELECT a FROM Allcode a WHERE a.name LIKE %:term% OR a.code LIKE %:term%")
    List<Allcode> searchByNameOrCode(@Param("term") String term);

    @Query("SELECT a FROM Allcode a ORDER BY a.name ASC")
    List<Allcode> findTop5OrderByName(Pageable pageable);
}
