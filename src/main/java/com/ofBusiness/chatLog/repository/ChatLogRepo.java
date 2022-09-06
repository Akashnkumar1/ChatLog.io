package com.ofBusiness.chatLog.repository;

import com.ofBusiness.chatLog.model.ChatLogRequest;
import com.ofBusiness.chatLog.model.ChatLogResponse;
import com.ofBusiness.chatLog.model.ChatLogs;

import java.sql.Timestamp;
import java.util.List;

public interface ChatLogRepo {
    void insertChatRepo(ChatLogRequest request, String user, String messageId, Timestamp time);

    List<ChatLogs> getChatLogs(String user, Integer limit, int id);

    int getIdForStart(String user, String start);

    void deleteLogsForUser(String user);

    int deleteUserAndMessageId(String user, String messageId);
}
