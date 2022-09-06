package com.ofBusiness.chatLog.service;

import com.ofBusiness.chatLog.customException.ChatLogCustomException;
import com.ofBusiness.chatLog.model.ChatLogRequest;
import com.ofBusiness.chatLog.model.ChatLogResponse;
import com.ofBusiness.chatLog.model.ChatLogs;
import com.ofBusiness.chatLog.repository.ChatLogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ChatLogServiceImpl implements ChatLogService {

    @Autowired
    ChatLogRepo chatLogRepo;

    @Override
    public String createChatLog(ChatLogRequest request, String user) throws ChatLogCustomException {
        String uniqueID = UUID.randomUUID().toString();
        String messageId = user.substring(0,user.length()/4) + uniqueID.substring(0,11) + user.substring(user.length()/4,user.length()/2);

        SimpleDateFormat df = new SimpleDateFormat("ddMMyyyyHHmmss");
        Date date = null;
        try {
            date = df.parse(request.getTimestamp().toString());
        } catch (ParseException e) {
            throw new ChatLogCustomException("400", "please provide time in ddMMyyyyHHmmss format");
        }
        long epoch = date.getTime();
        chatLogRepo.insertChatRepo(request, user, messageId,new Timestamp(epoch));

        return messageId;
    }

    @Override
    public List<ChatLogs> getChatLogs(String user, Integer limit, String start) throws ChatLogCustomException {

        int id =0;
        if (start!=null) {
            id = chatLogRepo.getIdForStart(user, start);
            if (id ==0){
                throw new ChatLogCustomException("400","No such messageId exists");
            }
        }
        List<ChatLogs> response = chatLogRepo.getChatLogs(user, limit, id);
        return response;
    }

    @Override
    public void deleteLogsForUser(String user) {
        chatLogRepo.deleteLogsForUser(user);

    }

    @Override
    public String deleteUserAndMessageId(String user, String messageId) {
        int rowsAffected = chatLogRepo.deleteUserAndMessageId(user, messageId);

        return rowsAffected>0 ? "successfully deleted" : "no such messageId exists for the user";
    }
}
