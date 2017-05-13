package com.example.pos.advice;

import com.example.pos.exception.PosApplicationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Created by rajeshkumar on 10/04/17.
 */
@ControllerAdvice
public class PosExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PosApplicationException.class)
    @ResponseBody
    ResponseEntity<Object> handleControllerException(HttpServletRequest req, PosApplicationException ex) {
        return new ResponseEntity<>(ex.getMessage(), INTERNAL_SERVER_ERROR);
    }

}
