package com.ofBusiness.chatLog.customException;

import com.ofBusiness.chatLog.model.meta.Meta;
import com.ofBusiness.chatLog.model.meta.MetaDataResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

        List<String> errorList = new ArrayList<>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors())
            errorList.add(error.getField() + "::" + error.getDefaultMessage());
        logger.info(errorList);
        int startIndex = errorList.get(0).indexOf("::") + 2;
        int lastIndex = errorList.get(0).length();
        return new ResponseEntity<>(new MetaDataResponse<>(new Meta("400",errorList.get(0).substring(startIndex, lastIndex)),null), headers, HttpStatus.BAD_REQUEST);
    }
}
