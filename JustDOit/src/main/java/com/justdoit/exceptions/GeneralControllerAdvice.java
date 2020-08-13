package com.justdoit.exceptions;

import com.justdoit.POJOs.ResponseObject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GeneralControllerAdvice {

    @ExceptionHandler(value=CustomTaskException.class)
    public ResponseObject handleCustomException(HttpServletRequest req, CustomTaskException e){
        ResponseObject<String> res = new ResponseObject();
        res.setMessage(e.getMessage());
        res.setResponse_code(312);
        return res;
    }

//    @ExceptionHandler(value=CustomMVCException.class)
//    public String handleMvcException(HttpServletRequest req, Model model, CustomTaskException e){
//        model.addAttribute("error_msg", e.getMessage());
//        return "404";
//    }

}
