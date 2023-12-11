package com.example.controller;

import com.example.entities.Candidate;
import com.example.entities.Experience;
import com.example.entities.Roles;
import com.example.repositories.CandidateRepository;
import com.example.repositories.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CandidateController {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private ExperienceRepository experienceRepository;
//trang chinh
    @GetMapping("/index")

    public  String  showIndex(Model model){
        return "candidate/index";
    }

//list cadidate
    @GetMapping("/candidates")
    public   String  showCandidate(Model model){
        model.addAttribute("candidatePage",candidateRepository.findAll());
        return "candidate/list";
    }
    @GetMapping("/candidatesAll")
    public   String  showCandidate1(Model model){
        model.addAttribute("candidatePage",candidateRepository.findAll());
        return "candidate/listAll";
    }
// ứng với từng id hiển thì cadidate tương ứng
    @GetMapping("/candidates/{id}")
    public String showCandidateDetails(@PathVariable("id") Long id, Model model) {
        Candidate candidate = candidateRepository.findById(id).orElse(null);
        if (candidate != null) {

            model.addAttribute("candidateDetail", candidate);
            return "candidate/listCandidateDetail";
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

    @GetMapping("/roles")
    public String showRole(Model model){
        model.addAttribute("roleList", Roles.values());
        return "roles/list";
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


    ///add
    @GetMapping("/candidates/showformAdd")
    public String showFormAdd(Model model){
            Candidate candidate = new Candidate();
            model.addAttribute("candidateAdd",candidate);
        return "candidate/add";
    }
    @PostMapping("/candidates/add")
    public String addCandidate(
            @ModelAttribute("candidateAdd") Candidate candidate){
            candidateRepository.save(candidate);
        return "redirect:/candidates";
    }

    //update
    @GetMapping("/candidate/update/{id}")
    public String showFormUpdate( @PathVariable("id") long id,Model model){
            Candidate candidate = candidateRepository.findById(id).orElse(null);
            model.addAttribute("canUpp",candidate);
            return "candidate/update";
    }
    @PostMapping ("/cadidate/update/{id}")
    public String updateCandidate(@PathVariable("id") long id,
                                  @ModelAttribute("canUpp") Candidate candidateUpdate){
        Candidate candidate = candidateRepository.findById(id).orElse(null);
        if(candidate !=null){
            candidate.setFullName(candidateUpdate.getFullName());
            candidate.setEmail(candidateUpdate.getEmail());
            candidate.setPhone(candidateUpdate.getPhone());
            candidateRepository.save(candidate);
        }
        return "redirect:/candidatesAll";
    }
    //delete
    @GetMapping("/candidate/delete/{id}")
    public String deleteCandidate(@PathVariable("id") long id) {
        Candidate candidate = candidateRepository.findById(id).orElse(null);

        if (candidate != null) {
            // Lấy danh sách các Experience liên quan đến Candidate và xóa chúng trước
            List<Experience> experiences = candidate.getExperiences();
            experienceRepository.deleteAll(experiences);
            // Sau đó xóa Candidate
            candidateRepository.delete(candidate);
        }
        return "redirect:/candidatesAll";
    }

}