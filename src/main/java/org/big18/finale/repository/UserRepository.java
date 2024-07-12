package org.big18.finale.repository;

import org.big18.finale.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends
        JpaRepository<Member, String> {
}
