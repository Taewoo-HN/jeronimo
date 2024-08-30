package org.big18.finale.repository;

import org.big18.finale.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Users, String> {
    boolean existsByEmail(String email);
    boolean existsByUserId(String userId);
    boolean existsByPhoneNumber(String phoneNumber);

    @Override
    Optional<Users> findById(String s);
}
