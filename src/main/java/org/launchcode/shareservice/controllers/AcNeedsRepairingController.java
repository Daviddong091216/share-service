package org.launchcode.shareservice.controllers;


import org.launchcode.shareservice.data.*;
import org.launchcode.shareservice.models.AcNeedsRepairing;
import org.launchcode.shareservice.models.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "acNeedsRepairing")
public class AcNeedsRepairingController {

    private UserRepository userRepository;

    @Autowired
    private AcNeedsRepairingRepository acNeedsRepairingRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private ZipCodeRepository zipCodeRepository;

    @GetMapping("create")
    public String displayAddForm(Model model) {
        model.addAttribute("title", "AC Needs Repairing");
        model.addAttribute("acNeedsRepairing", new AcNeedsRepairing());
        model.addAttribute("states", stateRepository.findAll());
        model.addAttribute("zipCodes", zipCodeRepository.findAll());
        model.addAttribute("problems", problemRepository.findAll());
        return "/acNeedsRepairing/create-or-update";
    }

    @PostMapping("create")
//    we must put Errors errors parameter directly after the Job parameter for it to work properly
    public String processAddForm(@ModelAttribute @Valid AcNeedsRepairing newAcNeedsRepairing,
                                    Errors errors,
                                    @RequestParam int state,
                                    @RequestParam List<Integer> problems,
                                    Model model) {
        if(problems.isEmpty()){
            model.addAttribute("title", "AC Needs Repairing");
            model.addAttribute("states", stateRepository.findAll());
            model.addAttribute("zipCodes", zipCodeRepository.findAll());
            model.addAttribute("problems", problemRepository.findAll());
            return "/acNeedsRepairing/create-or-update";
        }

        if (errors.hasErrors()) {
            model.addAttribute("title", "AC Needs Repairing");
            model.addAttribute("states", stateRepository.findAll());
            model.addAttribute("zipCodes", zipCodeRepository.findAll());
            model.addAttribute("problems", problemRepository.findAll());
            return "/acNeedsRepairing/create-or-update";
        }


        Optional optState = stateRepository.findById(state);
        if (optState.isPresent()) {
            State aState = (State) optState.get();
            model.addAttribute("state", aState);
            acNeedsRepairingRepository.save(newAcNeedsRepairing);
            return "/acNeedsRepairing/view";
        } else {
            return "redirect:../";
        }
    }


    @GetMapping("delete")
    public String renderDeleteForm(Model model) {
        model.addAttribute("title", "Delete Job");
        model.addAttribute("acNeedsRepairings", acNeedsRepairingRepository.findAll());
        return "/acNeedsRepairing/delete";
    }

    @PostMapping("delete")
    public String processDeleteForm(@RequestParam(required = false) int[] acNeedsRepairingIds) {
        if (acNeedsRepairingIds != null) {
            for (int id : acNeedsRepairingIds) {
                acNeedsRepairingRepository.deleteById(id);
            }
        }
        return "redirect:";
    }


    @GetMapping("view/{acNeedsRepairingId}")
    public String displayView(Model model, @PathVariable int acNeedsRepairingId) {

        Optional optJob = acNeedsRepairingRepository.findById(acNeedsRepairingId);
        if (optJob.isPresent()) {
            AcNeedsRepairing aJob = (AcNeedsRepairing) optJob.get();
            model.addAttribute("acNeedsRepairing", aJob);
            return "/acNeedsRepairing/view";
        } else {
            return "redirect:../";
        }
    }


    @GetMapping(value = "update/{acNeedsRepairingId}")
    public String displayUpdateEventForm(@PathVariable int acNeedsRepairingId, Model model, HttpServletRequest request) {

        model.addAttribute("title", "Update AC Needs Repairing");
//        model.addAttribute("actionUrl", request.getRequestURI());

        Optional<AcNeedsRepairing> event = acNeedsRepairingRepository.findById(acNeedsRepairingId);
        if (event.isPresent()) {
            model.addAttribute("acNeedsRepairing",event.get() );
            model.addAttribute("states", stateRepository.findAll());
            model.addAttribute("zipCodes", zipCodeRepository.findAll());
            model.addAttribute("problems", problemRepository.findAll());
        }
//        else {
//            model.addAttribute(MESSAGE_KEY, "warning|No event found with id: " + Integer.toString(acNeedsRepairingId));
//        }

        return "acNeedsRepairing/create-or-update";
    }

    @PostMapping(value = "update/{acNeedsRepairingId}")
    public String processUpdateForm(@ModelAttribute @Valid AcNeedsRepairing newAcNeedsRepairing,
                                 Errors errors,
                                 @RequestParam int state,
                                 @RequestParam List<Integer> problems,
                                 Model model) {
        if(problems.isEmpty()){
            model.addAttribute("title", "AC Needs Repairing");
            model.addAttribute("states", stateRepository.findAll());
            model.addAttribute("zipCodes", zipCodeRepository.findAll());
            model.addAttribute("problems", problemRepository.findAll());
            return "/acNeedsRepairing/create-or-update";
        }

        if (errors.hasErrors()) {
            model.addAttribute("title", "AC Needs Repairing");
            model.addAttribute("states", stateRepository.findAll());
            model.addAttribute("zipCodes", zipCodeRepository.findAll());
            model.addAttribute("problems", problemRepository.findAll());
            return "/acNeedsRepairing/create-or-update";
        }


        Optional optState = stateRepository.findById(state);
        if (optState.isPresent()) {
            State aState = (State) optState.get();
            model.addAttribute("state", aState);
            acNeedsRepairingRepository.save(newAcNeedsRepairing);
            return "/acNeedsRepairing/view";
        } else {
            return "redirect:../";
        }
    }



}
