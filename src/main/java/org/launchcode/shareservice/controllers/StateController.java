package org.launchcode.shareservice.controllers;

import org.launchcode.shareservice.data.CityRepository;
import org.launchcode.shareservice.data.StateRepository;
import org.launchcode.shareservice.models.City;
import org.launchcode.shareservice.models.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("states")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @GetMapping()
    public String displayAllStates(Model model) {
        model.addAttribute("title", "All States");
        model.addAttribute("states", stateRepository.findAll());
        return "states/index";
    }

    @GetMapping("add")
    public String displayAddStateForm(Model model) {
        model.addAttribute("title", "Add State");
        model.addAttribute(new State());
        return "states/add";
    }

    @PostMapping("add")
    public String processAddStateForm(@ModelAttribute @Valid State newState,
                                     Errors errors) {
        if (errors.hasErrors()) {
            return "states/add";
        }
        stateRepository.save(newState);
        return "redirect:";
    }

    @GetMapping("view/{stateId}")
    public String displayViewState(Model model, @PathVariable int stateId) {
        Optional optState = stateRepository.findById(stateId);
        if (optState.isPresent()) {
            State state = (State) optState.get();
            model.addAttribute("state", state);
            return "states/view";
        } else {
            return "redirect:../";
        }
    }

    @GetMapping("delete")
    public String renderDeleteStateForm(Model model) {
        model.addAttribute("title", "Delete States");
        model.addAttribute("states", stateRepository.findAll());
        return "states/delete";
    }

    @PostMapping("delete")
    public String processDeleteStateForm(@RequestParam(required = false) int[] stateIds) {
        if (stateIds != null) {
            for (int id : stateIds) {
                stateRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

}
