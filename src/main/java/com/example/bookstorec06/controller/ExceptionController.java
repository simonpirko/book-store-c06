package com.example.book_store_user_account.controller;

import com.example.book_store_user_account.exception.UserNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.thymeleaf.exceptions.TemplateEngineException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
  private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView userNotFound(HttpServletRequest req, UserNotFoundException ex) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Error");
        mav.addObject("exception", ex.getMessage());
        mav.addObject("url", req.getRequestURL());
        mav.addObject("timestamp", LocalDateTime.now().format(format));
        mav.addObject("status", HttpStatus.NOT_FOUND);
        return mav;
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ModelAndView clientErrorEx(HttpClientErrorException ex) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Error");
        return mav;
    }

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView runtimeEx(RuntimeException ex){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Error");
        return mav;
    }

    @ExceptionHandler(TemplateEngineException.class)
    public ModelAndView templateEx(TemplateEngineException ex){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Error");
        return mav;
    }

}

