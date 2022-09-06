package com.ofBusiness.chatLog.model.meta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class Meta {
    public String code;
    public String message;

    public Meta(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Meta() {
    }
}
