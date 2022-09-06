package com.ofBusiness.chatLog.model;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class ChatLogRequest {
    @NotBlank(message = "please provide valid message")
    private String message;
    @Positive(message = "please provide valid timestamp")
    private Long timestamp;
    @Range(min = 0,max = 1,message = "isSent value must be 0 or 1")
    private int isSent;

}
