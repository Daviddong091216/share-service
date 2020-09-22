package org.launchcode.shareservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("recreation")
public class RecreationController {

    @GetMapping("mouseOverRace")
    public String displayMouseOverRacePage(Model model) {
        return "recreation/mouseOverRace";
    }

    @GetMapping("weatherCondition")
    public String displayWeatherConditionPage(Model model) {
        return "recreation/weatherCondition";
    }

    @GetMapping("launchRocket")
    public String displayLaunchRocketPage(Model model) {
        return "recreation/launchRocket";
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
