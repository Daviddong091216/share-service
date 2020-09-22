package org.launchcode.shareservice.controllers;

import org.launchcode.shareservice.data.AcNeedsRepairingRepository;
import org.launchcode.shareservice.models.AcNeedsRepairing;
import org.launchcode.shareservice.models.AcNeedsRepairingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.launchcode.shareservice.controllers.ListController.columnChoices;

@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private AcNeedsRepairingRepository acNeedsRepairingRepository;

    @RequestMapping("")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "/search/search";
    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        Iterable<AcNeedsRepairing> jobs;
        if (searchType.toLowerCase().equals("all") && searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){
            jobs = acNeedsRepairingRepository.findAll();
        } else {
            jobs = AcNeedsRepairingData.findByColumnAndValue(searchType, searchTerm, acNeedsRepairingRepository.findAll());
        }
        model.addAttribute("columns", columnChoices);
        model.addAttribute("title", "AC needs Repairing with " + columnChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("jobs", jobs);

        return "/search/search";
    }


}
