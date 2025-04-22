package com.neeraj.urltrim.urltrim.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping("/hello")
    public String printHellow() {
        return "Hello WOrld!!";
    }
}
