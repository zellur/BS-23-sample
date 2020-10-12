package com.zellur.brainstation23.controller;


import com.zellur.brainstation23.model.CustomError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;

@Slf4j
@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    @ResponseBody
    public CustomError handleError(HttpServletRequest request) {
        log.info("{}", request);
        CustomError error = new CustomError();
        error.setStatusCode(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString());
        error.setMessage(request.getAttribute(RequestDispatcher.ERROR_MESSAGE).toString());
        error.setLocalDateTime(ZonedDateTime.now());
        return error;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
