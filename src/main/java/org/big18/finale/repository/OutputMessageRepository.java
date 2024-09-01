package org.big18.finale.repository;

import org.big18.finale.DTO.OutputMessage;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableRedisRepositories
public interface OutputMessageRepository extends KeyValueRepository<OutputMessage, String> {
}
