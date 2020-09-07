package org.launchcode.shareservice.controllers;

import org.launchcode.shareservice.data.ProblemRepository;
import org.launchcode.shareservice.models.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("problems")
public class ProblemController {

    @Autowired
    private ProblemRepository problemRepository;

    @GetMapping()
    public String displayAllSkills(Model model) {
        model.addAttribute("title", "All Problems");
        model.addAttribute("problems", problemRepository.findAll());
        return "problems/index";
    }


    @GetMapping("add")
    public String displayAddSkillForm(Model model) {
        model.addAttribute("title", "Add Problem");
        model.addAttribute(new Problem());
        return "problems/add";
    }

    @PostMapping("add")
    public String processAddSkillForm(@ModelAttribute @Valid Problem newProblem,
                                      Errors errors) {
        if (errors.hasErrors()) {
            return "problems/add";
        }
        problemRepository.save(newProblem);
        return "redirect:";
    }

    @GetMapping("view/{problemId}")
    public String displayViewSkill(Model model, @PathVariable int problemId) {
        Optional optProblem = problemRepository.findById(problemId);
        if (optProblem.isPresent()) {
            Problem problem = (Problem) optProblem.get();
            model.addAttribute("problem", problem);
            return "problems/view";
        } else {
            return "redirect:../";
        }
    }

    @GetMapping("delete")
    public String renderDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Problem");
        model.addAttribute("problems", problemRepository.findAll());
        return "problems/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] problemIds) {
        if (problemIds != null) {
            for (int id : problemIds) {
                problemRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

}
