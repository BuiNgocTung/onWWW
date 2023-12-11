package com.example.controller;

import com.example.entities.Candidate;
import com.example.entities.Experience;
import com.example.entities.Roles;
import com.example.repositories.CandidateRepository;
import com.example.repositories.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ExperienceController {
    @Autowired
    private ExperienceRepository experienceRepository;
    @Autowired
    private CandidateRepository candidateRepository;

    @GetMapping("/experiences")
    public  String showListEx(Model model)
    {
        model.addAttribute("exPage",experienceRepository.findAll());
        return "experience/listAll";
    }
    @GetMapping("/experiences/delete/{id}")
    public String deleteEx(@PathVariable("id") long id){
        Experience experience = experienceRepository.findById(id).orElse(null);
        experienceRepository.delete(experience);
            return "redirect:/experiences";
    }
    @GetMapping("/experiences/showformAdd")
    public  String showFormAdd( Model model){
            Experience experience = new Experience();
            model.addAttribute("exADD",experience);
         model.addAttribute("roleList",Roles.values());

        model.addAttribute("candidateList",candidateRepository.findAll());
        return "experience/add";
    }
    @PostMapping("/showFormAdd/add")
    public String addEx(@ModelAttribute("exADD") Experience experience){
        experienceRepository.save(experience);
        return "redirect:/experiences";

    }
    @GetMapping("/experiences/showformUpdate/{id}")
    public String showFormUpdateEx(@PathVariable("id") long id, Model model){
        Experience experience = experienceRepository.findById(id).orElse(null);
         model.addAttribute("exUpp",experience);
        model.addAttribute("roleList",Roles.values());
        model.addAttribute("candidateList",candidateRepository.findAll());
        return "experience/update";
    }
    @PostMapping("/experiences/update/{id}")
    public String updateEx(@PathVariable("id") long id, @ModelAttribute Experience experienceupdate){
        Experience experience = experienceRepository.findById(id).orElse(null);
        if(experience!=null){
            experience.setCompannyName(experienceupdate.getCompannyName());
            experience.setFromDate(experienceupdate.getFromDate());
            experience.setToDate(experienceupdate.getToDate());
            experience.setRole(experienceupdate.getRole());
            experience.setWorkDescripton(experienceupdate.getWorkDescripton());
            experience.setCandidate(experienceupdate.getCandidate());
            experienceRepository.save(experience);
        }
        return "redirect:/experiences";
    }
}
