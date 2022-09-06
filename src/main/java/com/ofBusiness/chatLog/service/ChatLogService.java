package com.ofBusiness.chatLog.service;

import com.ofBusiness.chatLog.customException.ChatLogCustomException;
import com.ofBusiness.chatLog.model.ChatLogRequest;
import com.ofBusiness.chatLog.model.ChatLogResponse;
import com.ofBusiness.chatLog.model.ChatLogs;

import java.util.List;

public interface ChatLogService {
    String createChatLog(ChatLogRequest request, String user) throws ChatLogCustomException;

    List<ChatLogs> getChatLogs(String user, Integer limit, String start) throws ChatLogCustomException;

    void deleteLogsForUser(String user);

    String deleteUserAndMessageId(String user, String messageId);
}
