package org.big18.finale.repository;

import org.big18.finale.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<Users, String> {
}
