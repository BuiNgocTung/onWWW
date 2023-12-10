package com.example.controller;

import com.example.entities.Experience;
import com.example.entities.Roles;
import com.example.repositories.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExperienceController {
    @GetMapping("/roles")
    public String showRole(Model model){
        model.addAttribute("roleList", Roles.values());
        return "roles/list";
    }
}
