package com.altisource.pos.advice;

import com.altisource.pos.exception.PosApplicationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Scanner;

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

    public static void main(String[] args) {
        long[] array = new long[5];
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            array[i] = in.nextLong();
        }

        int[] sumArray = new int[5];
        for (int i = 0; i < 5; i++) {
            sumArray[i] = 0;

            for (int j = 0; j < 5; j++) {
                if (i != j) {
                    sumArray[i] += array[j];
                }
            }
            Arrays.sort(sumArray);
            System.out.println(sumArray[0] + " " +sumArray[4]);
        }
    }
}
