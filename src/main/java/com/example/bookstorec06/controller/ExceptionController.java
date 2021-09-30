package com.example.bookstorec06.controller;

import com.example.bookstorec06.exception.MyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @ExceptionHandler(MyNotFoundException.class)
    public ModelAndView userNotFound(HttpServletRequest req, MyNotFoundException ex) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Error");
        mav.addObject("exception", ex.getMessage());
        mav.addObject("url", req.getRequestURL());
        mav.addObject("timestamp", LocalDateTime.now().format(format));
        mav.addObject("status", HttpStatus.NOT_FOUND);
        return mav;
    }

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView runtimeEx(RuntimeException ex) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Error");
        return mav;
    }
}

