package org.launchcode.shareservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("needsbabysitter")
public class NeedsBabysitterController {

    @GetMapping("index")
    public String displayMainPage(Model model) {
        return "needsbabysitter/index";
    }
}
