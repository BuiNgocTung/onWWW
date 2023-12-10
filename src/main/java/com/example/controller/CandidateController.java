package com.example.controller;

import com.example.entities.Candidate;
import com.example.entities.Roles;
import com.example.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CandidateController {
    @Autowired
    private CandidateRepository candidateRepository;
//trang chinh
    @GetMapping("/index")
    public   String  showIndex(Model model){
        return "candidate/index";
    }

//list cadidate
    @GetMapping("/candidates")
    public   String  showCandidate(Model model){
        model.addAttribute("candidatePage",candidateRepository.findAll());
        return "candidate/list";
    }
// ứng với từng id hiển thì cadidate tương ứng
    @GetMapping("/candidates/{id}")
    public String showCandidateDetails(@PathVariable("id") Long id, Model model) {
        Candidate candidate = candidateRepository.findById(id).orElse(null);
        if (candidate != null) {

            model.addAttribute("candidateDetail", candidate);
            return "candidate/listCandidateDetail"; // Trả về view để hiển thị chi tiết candidate
        } else {
            return "redirect:/candidates"; // Nếu không tìm thấy candidate, chuyển hướng về trang danh sách candidates
        }
    }
    //list cadidate use email
    @GetMapping("/candidatesUseEmail")
    public   String  showCandidateUseEmail(Model model){
        model.addAttribute("candidateEmail",candidateRepository.findByEmailNotNull());
        return "candidate/ListUseEmail";
    }

 //list cadidate làm role (ứng với role thì sẽ có list cadidate tương ứng)
    @GetMapping("/getCandidatesByExperienceRole")
    public String getCandidatesByExperienceRole(@RequestParam("role") String role, Model model) {
        Roles selectedRole = Roles.valueOf(role);

        // Lấy danh sách candidate dựa trên role từ repository
        List<Candidate> candidates = candidateRepository.findCandidatesByExperienceRole(selectedRole);

        // Đưa danh sách candidate vào Model để gửi về view template
        model.addAttribute("candidatesRole", candidates);

        return "roles/listCandidateByRoles"; // Trả về view template để hiển thị danh sách candidate
    }

}
