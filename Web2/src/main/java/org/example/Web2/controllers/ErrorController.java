package org.example.Web2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null){
            Integer statusCode = Integer.parseInt(status.toString());
            return switch (statusCode) {
                case 404 -> "error/error-404";
                case 500 -> "error/error-500";
                case 403 -> "error/error-403";
                case 405 -> "error/error-405";
                case 400 -> "error/error-400";
                default -> "error/error";
            };
        }
        return "error/error";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}