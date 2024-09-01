package org.big18.finale.repository;

import org.big18.finale.entity.Allcode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllcodeRepository extends JpaRepository<Allcode, String> {
}