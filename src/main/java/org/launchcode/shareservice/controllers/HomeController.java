package org.launchcode.shareservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("mainpage")
public class HomeController {

    @GetMapping
    public String displayHomePage(Model model) {
        model.addAttribute("title","Welcome To Share Service.");
        return "homePage";
    }


    @GetMapping("index")
    public String displayMainPage(Model model) {
        model.addAttribute("title","Categories");
        return "index";
    }

}
