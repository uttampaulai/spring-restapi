package com.myspringbootproject.spring_restapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(path = "/springapp")
public class HelloController {

    @GetMapping("/{message}/{msg}")
    public String getMessage(@PathVariable String message, @PathVariable String msg){
        return "Wellcome " + message + " "+ msg;
    }
}
