package com.ofBusiness.chatLog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatLogs {
    private String message;
    private Timestamp timestamp;
    private int isSent;
    private String messageId;


}
