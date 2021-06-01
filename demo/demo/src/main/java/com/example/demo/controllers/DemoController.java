package com.example.demo.controllers;

import com.example.demo.services.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/demo")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class DemoController {

    @Autowired
    ParserService parserService;

    @PostMapping("/parse")
    @ResponseBody
    public String parse(@RequestBody String input){
        return parserService.parse(input);
    }
}
