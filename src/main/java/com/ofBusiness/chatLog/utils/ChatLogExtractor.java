package com.ofBusiness.chatLog.utils;

import com.ofBusiness.chatLog.model.ChatLogResponse;
import com.ofBusiness.chatLog.model.ChatLogs;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ChatLogExtractor implements ResultSetExtractor<List<ChatLogs>> {

    @Override
    public List<ChatLogs> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<ChatLogs> chatLogs = new ArrayList<>();

        while (rs.next()){
            String message = rs.getString("message");
            Timestamp timestamp = rs.getTimestamp("timestamp");
            int isSent = rs.getInt("is_sent");
            String messageId = rs.getString("message_id");

            ChatLogs chat = new ChatLogs(message,timestamp,isSent, messageId);
            chatLogs.add(chat);
        }
        return chatLogs;
    }
}
