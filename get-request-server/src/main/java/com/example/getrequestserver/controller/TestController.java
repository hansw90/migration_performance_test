package com.example.getrequestserver.controller;

import com.example.getrequestserver.service.TestService;
import com.example.getrequestserver.vo.TnRecdPrd;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Slf4j
@Controller
public class TestController {

    @Autowired
    private TestService testService;


    @RequestMapping("test")
    public @ResponseBody ResponseEntity<String> getTestData(String offsetOption) {
        String[] offsetOptionList = offsetOption.split(",");
        List<String> arr = new ArrayList<>(Arrays.asList(offsetOptionList));
        HttpHeaders resHeaders = new HttpHeaders();
        resHeaders.add("Content-Type", "application/json;charset=UTF-8");

        Collection<TnRecdPrd> collectionTest =  testService.getTestbyOffset(arr.get(0), arr.get(1));

        return new ResponseEntity<String>(new Gson().toJsonTree(collectionTest).toString(), resHeaders, HttpStatus.CREATED);
    }

}
