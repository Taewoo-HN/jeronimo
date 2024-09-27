package org.big18.finale.repository.stocks;

import org.big18.finale.entity.stocks.A035720_mindata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface A035720_mindataRepository extends JpaRepository<A035720_mindata, Timestamp> {


}