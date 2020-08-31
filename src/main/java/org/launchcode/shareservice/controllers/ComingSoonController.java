package org.launchcode.shareservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("comingsoon")
public class ComingSoonController {

    @GetMapping("index")
    public String displayMainPage(Model model) {
        return "comingsoon/index";
    }

}
