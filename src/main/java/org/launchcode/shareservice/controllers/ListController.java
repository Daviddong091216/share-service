package org.launchcode.shareservice.controllers;

import org.launchcode.shareservice.data.AcNeedsRepairingRepository;
import org.launchcode.shareservice.data.ProblemRepository;
import org.launchcode.shareservice.data.StateRepository;
import org.launchcode.shareservice.data.ZipCodeRepository;
import org.launchcode.shareservice.models.AcNeedsRepairing;
import org.launchcode.shareservice.models.AcNeedsRepairingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
@RequestMapping(value = "list")
public class ListController {


    @Autowired
    private AcNeedsRepairingRepository acNeedsRepairingRepository;

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ZipCodeRepository zipCodeRepository;

    static HashMap<String, String> columnChoices = new HashMap<>();

    public ListController() {
        columnChoices.put("all", "All");
        columnChoices.put("name", "User Name");
        columnChoices.put("problem", "Problems");
        columnChoices.put("state", "State");
        columnChoices.put("zipCode", "Zip Code");
    }

    @RequestMapping("")
    public String list(Model model) {
        model.addAttribute("acNeedsRepairings", acNeedsRepairingRepository.findAll());
        model.addAttribute("problems", problemRepository.findAll());
        model.addAttribute("states", stateRepository.findAll());
        model.addAttribute("zipCodes", zipCodeRepository.findAll());
        return "/list/list";
    }

    @RequestMapping(value = "jobs")
    public String listJobsByColumnAndValue(Model model, @RequestParam String column, @RequestParam String value) {
        Iterable<AcNeedsRepairing> jobs;
        if (column.toLowerCase().equals("all")) {
            jobs = acNeedsRepairingRepository.findAll();
            model.addAttribute("title", "All Jobs");
        } else {
            jobs = AcNeedsRepairingData.findByColumnAndValue(column, value, acNeedsRepairingRepository.findAll());
            model.addAttribute("title", "AC NeedsRepairing with " + columnChoices.get(column) + ": " + value);
        }
        model.addAttribute("jobs", jobs);

        return "/list/list-acNeedsRepairing";
    }
}
