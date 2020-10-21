package org.launchcode.shareservice.controllers;

import org.launchcode.shareservice.models.WebAPI.DadJoke;
import org.launchcode.shareservice.models.WebAPI.DadJokeSearch;
import org.launchcode.shareservice.models.WebAPI.DadJokeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;


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


    @GetMapping("getAJoke")
    public String Index(Model model) throws IOException {

        DadJokeService dadJokeService = new DadJokeService();
        DadJoke dadJoke = dadJokeService.GetDadJoke();

        model.addAttribute("dadJoke", dadJoke);

        return "recreation/getAJoke";
    }

    @RequestMapping("searchJokes")
    public String search(Model model) {
        return "recreation/searchJokes";
    }


    //    http://localhost:8080/searchJokes?searchTerm=cat
    @PostMapping("searchJokes")
    public String Index(Model model, @RequestParam String searchTerm,
                        @RequestParam int limit, @RequestParam int page) throws IOException {
        DadJokeService dadJokeService = new DadJokeService();
        DadJokeSearch dadJokes = dadJokeService.SearchDadJokes(searchTerm,limit,page);

        model.addAttribute("dadJokes", dadJokes);

        return "recreation/displaySearchJokes";
    }



}
