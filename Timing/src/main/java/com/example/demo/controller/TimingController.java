package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author FSARSIGHT-IOT-01
 * @date Created in 2019-12-05 11:20
 */
@RestController
public class TimingController {

    @RequestMapping("test")
    public String get(){

        return "";
    }
}
