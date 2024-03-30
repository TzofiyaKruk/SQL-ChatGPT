package com.handson.sqlchatgpt.controller;

import com.handson.sqlchatgpt.service.ChatGPTService;
import com.handson.sqlchatgpt.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/api/students")
public class RequestController {

    @Autowired
    ChatGPTService chatGPTService;

    @Autowired
    DBService dbService;


    @RequestMapping(value = "/getquery", method = RequestMethod.GET)
    public ResponseEntity<?> getquery(@RequestParam String text) throws IOException {
        return new ResponseEntity<>(chatGPTService.createSQL(text), HttpStatus.OK);
    }

    @RequestMapping(value = "/performSQL", method = RequestMethod.GET)
    public ResponseEntity<?> performSQL(@RequestParam String SQLquery) throws IOException {
        return new ResponseEntity<>(dbService.performSQL(SQLquery), HttpStatus.OK);
    }
}