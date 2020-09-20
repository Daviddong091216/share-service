package org.launchcode.shareservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("launchRocket")
public class LaunchRocketController {

    @GetMapping("index")
    public String displayPage(Model model) {
        return "launchRocket/index";
    }
}
