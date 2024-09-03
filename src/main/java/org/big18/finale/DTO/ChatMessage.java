package org.big18.finale.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash
public class ChatMessage {

    @Id
    private String sender;

    private String content;

    private boolean senderIsServer;

    // Getters and Setters

    public boolean isSenderIsServer() {
        return "Server".equals(this.sender);
    }

}
