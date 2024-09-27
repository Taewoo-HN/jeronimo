package org.big18.finale.repository.stocks;


import org.big18.finale.entity.stocks.A066570_mindata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface A066570_mindataRepository extends JpaRepository<A066570_mindata, Timestamp> {

}