package com.hyj.mongostudy.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * Global exception handler<br><br>
 *
 * <b>0.0.4: </b>Add method argumentExceptionHandler
 *
 * @author lshaci
 * @since 0.0.3
 * @version 0.0.4
 */
@Slf4j
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResData handler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        log.error("GlobalExceptionHandler err:" + e);
        return new ResData(ErrorInfo.SYS_ERR.code, ErrorInfo.SYS_ERR.desc);
    }

}
