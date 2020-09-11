package org.launchcode.shareservice.controllers;

import org.launchcode.shareservice.data.ZipCodeRepository;
import org.launchcode.shareservice.models.ZipCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("zipCodes")
public class ZipCodeController {

    @Autowired
    private ZipCodeRepository zipCodeRepository;

    @GetMapping()
    public String displayAllZipCodes(Model model) {
        model.addAttribute("title", "All Zip Codes");
        model.addAttribute("zipCodes", zipCodeRepository.findAll());
        return "zipCodes/index";
    }

    @GetMapping("add")
    public String displayAddZipCodeForm(Model model) {
        model.addAttribute("title", "Add Zip Code");
        model.addAttribute(new ZipCode());
        return "zipCodes/add";
    }

    @PostMapping("add")
    public String processAddZipCodeForm(@ModelAttribute @Valid ZipCode newZipCode,
                                     Errors errors) {
        if (errors.hasErrors()) {
            return "zipCodes/add";
        }
        zipCodeRepository.save(newZipCode);
        return "redirect:";
    }

    @GetMapping("view/{zipCodeId}")
    public String displayViewZipCode(Model model, @PathVariable int zipCodeId) {
        Optional optZipCode = zipCodeRepository.findById(zipCodeId);
        if (optZipCode.isPresent()) {
            ZipCode zipCode = (ZipCode) optZipCode.get();
            model.addAttribute("zipCode", zipCode);
            return "zipCodes/view";
        } else {
            return "redirect:../";
        }
    }

    @GetMapping("delete")
    public String renderDeleteZipCodeForm(Model model) {
        model.addAttribute("title", "Delete Zip Code");
        model.addAttribute("zipCodes", zipCodeRepository.findAll());
        return "zipCodes/delete";
    }

    @PostMapping("delete")
    public String processDeleteZipCodeForm(@RequestParam(required = false) int[] zipCodeIds) {
        if (zipCodeIds != null) {
            for (int id : zipCodeIds) {
                zipCodeRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

}
