package com.example.contact.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfoController {

    @GetMapping("/Info")
    public String getReadMe() {
        return "readme";
    }
}
