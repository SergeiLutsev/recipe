package com.sergei.recipe.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHendler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handBadRequest(Exception ex){
        log.debug("Handeling Number Format Exception");
        log.debug(ex.getMessage());
        ModelAndView mv=new ModelAndView();
        mv.setViewName("badRequest");
        mv.addObject("exception",ex);
        return  mv;
    }
}
