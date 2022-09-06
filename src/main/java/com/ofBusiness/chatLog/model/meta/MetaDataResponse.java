package com.ofBusiness.chatLog.model.meta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class MetaDataResponse<T> {
    public Meta meta;
    public T data;


}
