package org.launchcode.shareservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("recreation")
public class RecreationController {

    @GetMapping("mouseOverRace")
    public String displayPage(Model model) {
        return "recreation/mouseOverRace";
    }

    @GetMapping("guessNumberJavaScript")
    public String displayGuessPage1(Model model) {
        return "recreation/guessNumberJavaScript";
    }

    @GetMapping("guessNumberJava")
    public String displayGuessPage2(Model model) {
        return "recreation/guessNumberJava";
    }

}
