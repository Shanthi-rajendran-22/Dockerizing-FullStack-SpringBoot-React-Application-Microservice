package com.css.unitconvertor.exception;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.css.unitconvertor.pojo.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;




/**
 * The Class GlobalExceptionHandler deals with exception response
 */
@ControllerAdvice

public class GlobalExceptionHandler {
    /** The timestamp. */
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    /**
     * Not found exception
     * @param ex the exception
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleUnknownException(Exception ex,
                                                                      HttpServletRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(timestamp.toString(),details,request.getRequestURL().toString(),HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}