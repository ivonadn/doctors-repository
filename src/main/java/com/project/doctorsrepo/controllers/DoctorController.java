package com.project.doctorsrepo.controllers;

import com.project.doctorsrepo.models.dtos.DoctorDto;
import com.project.doctorsrepo.models.dtos.DoctorMakeNewDto;
import com.project.doctorsrepo.service.DoctorService;
import com.project.doctorsrepo.service.HospitalService;
import com.project.doctorsrepo.service.ProfessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DoctorController {
    private final DoctorService doctorService;
    private final HospitalService hospitalService;
    private final ProfessionService professionService;

    public DoctorController(DoctorService doctorService, HospitalService hospitalService, ProfessionService professionService) {
        this.doctorService = doctorService;
        this.hospitalService = hospitalService;
        this.professionService = professionService;
    }

    @GetMapping("/")
    public String getDoctors(Model model) throws Exception {
        hospitalService.seedHospitals();
        professionService.seedProfessions();
        doctorService.seedDoctors();
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "index";
    }


    @GetMapping("/hospital/")
    public String getHospital(@RequestParam int id, Model model){
        model.addAttribute("doctors", doctorService.getHospital(id));
        return "filtered";
    }

    @GetMapping("/profession/")
    public String getProfession(@RequestParam int id, Model model) {
        model.addAttribute("doctors", doctorService.getProfession(id));
        return "filtered";
    }

    @GetMapping("/add")
    public String getAddPage(Model model) {
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        model.addAttribute("professions", professionService.getAllProfessions());
        return "add";
    }

    @PostMapping("/add")
    public String addDoctor(DoctorMakeNewDto doctorMakeNewDto) {
        doctorService.createDoctor(doctorMakeNewDto);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String getDeletePage(Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "delete";
    }

    @PostMapping("/delete")
    public String deleteDoctor(DoctorDto doctorDto) {
        doctorService.deleteDoctor(doctorDto);
        return "redirect:/";
    }
}
