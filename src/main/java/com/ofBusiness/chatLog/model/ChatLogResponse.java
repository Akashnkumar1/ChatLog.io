package com.ofBusiness.chatLog.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatLogResponse {
    private String userId;
    private List<ChatLogs> logs;

    public ChatLogResponse() {
    }

    public ChatLogResponse(String userId, List<ChatLogs> logs) {
        this.userId = userId;
        this.logs = logs;
    }
}
