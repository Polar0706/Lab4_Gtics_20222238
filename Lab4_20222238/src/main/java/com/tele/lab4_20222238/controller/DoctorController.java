package com.tele.lab4_20222238.controller;

import com.tele.lab4_20222238.entity.Doctor;
import com.tele.lab4_20222238.repository.DoctorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping
    public String listDoctors(Model model) {
        model.addAttribute("doctorList", doctorRepository.findAll());
        return "doctor/list";
    }

    @GetMapping("/new")
    public String newDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "doctor/form";
    }

    @GetMapping("/edit/{id}")
    public String editDoctorForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("doctor", doctorRepository.findById(id).orElseThrow());
        return "doctor/form";
    }

    @PostMapping("/save")
    public String saveDoctor(@ModelAttribute("doctor") @Valid Doctor doctor,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "doctor/form";
        }

        try {
            doctorRepository.save(doctor);
            return "redirect:/doctors";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("dbError", "Error al guardar en la base de datos.");
            return "doctor/form";
        } catch (NumberFormatException e) {
            model.addAttribute("dbError", "Formato numérico inválido.");
            return "doctor/form";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable("id") Integer id) {
        doctorRepository.deleteById(id);
        return "redirect:/doctors";
    }
}