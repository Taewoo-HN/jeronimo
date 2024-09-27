package org.big18.finale.repository.stocks;


import org.big18.finale.entity.stocks.A051910_mindata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface A051910_mindataRepository extends JpaRepository<A051910_mindata, Timestamp> {

}