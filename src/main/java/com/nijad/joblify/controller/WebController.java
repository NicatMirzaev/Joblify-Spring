package com.nijad.joblify.controller;

import com.nijad.joblify.entity.Company;
import com.nijad.joblify.entity.JobListing;
import com.nijad.joblify.service.CompanyService;
import com.nijad.joblify.service.JobListingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final CompanyService companyService;
    private final JobListingService jobListingService;

    @Value("${app.welcome-message:Welcome to Joblify!}")
    private String welcomeMessage;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("welcomeMessage", welcomeMessage);
        model.addAttribute("jobs", jobListingService.getAllJobListings());
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("company", new Company());
        return "register";
    }

    @PostMapping("/register")
    public String registerCompany(@Valid @ModelAttribute("company") Company company,
                                  BindingResult result,
                                  @RequestParam("logo") MultipartFile logo,
                                  Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        try {
            companyService.saveCompany(company, logo);
        } catch (IOException e) {
            model.addAttribute("error", "Failed to upload logo");
            return "register";
        }
        return "redirect:/?success=registered";
    }

    @GetMapping("/post-job")
    public String showJobForm(Model model) {
        model.addAttribute("job", new JobListing());
        model.addAttribute("companies", companyService.getAllCompanies());
        return "post-job";
    }

    @PostMapping("/post-job")
    public String postJob(@Valid @ModelAttribute("job") JobListing job,
                          BindingResult result,
                          Model model) {
        if (result.hasErrors()) {
            model.addAttribute("companies", companyService.getAllCompanies());
            return "post-job";
        }
        jobListingService.saveJobListing(job);
        return "redirect:/?success=posted";
    }
}
