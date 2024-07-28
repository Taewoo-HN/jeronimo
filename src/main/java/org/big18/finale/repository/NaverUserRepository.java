package org.big18.finale.repository;

import org.big18.finale.entity.NaverUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NaverUserRepository extends JpaRepository<NaverUser, Long> {
    Optional<NaverUser> findByEmail(String email);

}
