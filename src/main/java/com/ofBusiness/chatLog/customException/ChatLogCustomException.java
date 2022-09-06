package com.ofBusiness.chatLog.customException;

import lombok.Getter;

@Getter
public class ChatLogCustomException extends Exception{

    private String errorCode;
    private String messageText;

    public ChatLogCustomException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.messageText = errorMessage;
    }
}
