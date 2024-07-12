package org.big18.finale.repository;

import org.big18.finale.entity.MemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends
        JpaRepository<MemberDTO, String> {
}
