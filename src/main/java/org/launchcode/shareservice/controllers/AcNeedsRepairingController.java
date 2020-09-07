package org.launchcode.shareservice.controllers;


import org.launchcode.shareservice.data.AcNeedsRepairingRepository;
import org.launchcode.shareservice.data.CityRepository;
import org.launchcode.shareservice.data.ProblemRepository;
import org.launchcode.shareservice.data.StateRepository;
import org.launchcode.shareservice.models.AcNeedsRepairing;
import org.launchcode.shareservice.models.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "acNeedsRepairing")
public class AcNeedsRepairingController {

    @Autowired
    private AcNeedsRepairingRepository acNeedsRepairingRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private CityRepository cityRepository;

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "AC Needs Repairing");
        model.addAttribute("acNeedsRepairing", new AcNeedsRepairing());
        model.addAttribute("states", stateRepository.findAll());
        model.addAttribute("cities", cityRepository.findAll());
        model.addAttribute("problems", problemRepository.findAll());
        return "/acNeedsRepairing/add";
    }

    @PostMapping("add")
//    we must put Errors errors parameter directly after the Job parameter for it to work properly
    public String processAddJobForm(@ModelAttribute @Valid AcNeedsRepairing newAcNeedsRepairing,
                                    Errors errors,
                                    @RequestParam int state,
                                    @RequestParam List<Integer> problems,
                                    Model model) {
        if(problems.isEmpty()){
            model.addAttribute("title", "AC Needs Repairing");
            model.addAttribute("states", stateRepository.findAll());
            model.addAttribute("cities", cityRepository.findAll());
            model.addAttribute("problems", problemRepository.findAll());
            return "/acNeedsRepairing/add";
        }

        if (errors.hasErrors()) {
            model.addAttribute("title", "AC Needs Repairing");
            model.addAttribute("states", stateRepository.findAll());
            model.addAttribute("cities", cityRepository.findAll());
            model.addAttribute("problems", problemRepository.findAll());
            return "/acNeedsRepairing/add";
        }


//        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
//        newJob.setSkills(skillObjs);

        Optional optState = stateRepository.findById(state);
        if (optState.isPresent()) {
            State aState = (State) optState.get();
            model.addAttribute("employer", aState);
            acNeedsRepairingRepository.save(newAcNeedsRepairing);
            return "/acNeedsRepairing/view";
        } else {
            return "redirect:../";
        }
    }


    @GetMapping("delete")
    public String renderDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Job");
        model.addAttribute("acNeedsRepairings", acNeedsRepairingRepository.findAll());
        return "/acNeedsRepairing/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] acNeedsRepairingIds) {
        if (acNeedsRepairingIds != null) {
            for (int id : acNeedsRepairingIds) {
                acNeedsRepairingRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

}
