package com.mytaxi.controller;

import javassist.NotFoundException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.controller
 * USER      : sean
 * DATE      : 07-Sun-Oct-2018
 * TIME      : 22:07
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@ApiIgnore
@Controller
public class ErrorPageCtrl implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @GetMapping(value = ERROR_PATH)
    public @ResponseBody
    ModelAndView handleError() {
        return new ModelAndView("404");
    }

    /**
     * Returns the path of the error page.
     *
     * @return the error path
     */
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
