package com.ofBusiness.chatLog.controller;

import com.ofBusiness.chatLog.customException.ChatLogCustomException;
import com.ofBusiness.chatLog.model.ChatLogRequest;
import com.ofBusiness.chatLog.model.ChatLogResponse;
import com.ofBusiness.chatLog.model.ChatLogs;
import com.ofBusiness.chatLog.model.meta.Meta;
import com.ofBusiness.chatLog.model.meta.MetaDataResponse;
import com.ofBusiness.chatLog.service.ChatLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ChatLogController {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ChatLogController.class);

    @Autowired
    ChatLogService chatLogService;

    @PostMapping("/{user}")
    public ResponseEntity<?> createChatLog(@PathVariable("user") String user, @Valid @RequestBody ChatLogRequest request){
        try {
            String response = chatLogService.createChatLog(request,user);
            return new ResponseEntity<>(new MetaDataResponse<>(new Meta("200","success"),response),HttpStatus.OK);
        } catch (ChatLogCustomException e){
            return new ResponseEntity<>(new MetaDataResponse<>(new Meta(e.getErrorCode(),e.getMessageText()),null),HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new MetaDataResponse<>(new Meta("400",e.getMessage()),null),HttpStatus.OK);
        }
    }

    @GetMapping("/{user}")
    public ResponseEntity<?> getChatLogs(@PathVariable("user") String user, @RequestParam(required = false) Integer limit, @RequestParam(required = false) String start){


        try {
            List<ChatLogs> chatLogs = chatLogService.getChatLogs(user, limit, start);
            ChatLogResponse response = new ChatLogResponse(user,chatLogs);
            return new ResponseEntity<>(new MetaDataResponse<>(new Meta("200","success"),response),HttpStatus.OK);
        } catch (ChatLogCustomException e) {
            return new ResponseEntity<>(new MetaDataResponse<>(new Meta(e.getErrorCode(),e.getMessageText()),null),HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new MetaDataResponse<>(new Meta("400",e.getMessage()),null),HttpStatus.OK);
        }
    }

    @DeleteMapping("/{user}")
    public ResponseEntity<?> deleteLogs(@PathVariable("user") String user){
        chatLogService.deleteLogsForUser(user);
        return new ResponseEntity<>(new MetaDataResponse<>(new Meta("200","success"),null),HttpStatus.OK);
    }

    @DeleteMapping("/{user}/{msgid}")
    public ResponseEntity<?> deleteUserAndMessageId(@PathVariable("user") String user, @PathVariable("msgid") String messageId){
        String response = chatLogService.deleteUserAndMessageId(user, messageId);
        return new ResponseEntity<>(new MetaDataResponse<>(new Meta("200",response),null),HttpStatus.OK);
    }
}
