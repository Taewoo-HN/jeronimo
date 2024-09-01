package org.big18.finale.repository;

import org.big18.finale.DTO.ChatMessage;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableRedisRepositories
public interface ChatMessageRepository extends KeyValueRepository<ChatMessage, String> {
}
