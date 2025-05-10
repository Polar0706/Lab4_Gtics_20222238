package com.tele.lab4_20222238.controller;

import com.tele.lab4_20222238.entity.Clinic;
import com.tele.lab4_20222238.repository.ClinicRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clinics")
public class ClinicController {
    @Autowired
    private ClinicRepository clinicRepository;

    @GetMapping
    public String listClinics(Model model) {
        model.addAttribute("clinicList", clinicRepository.findAll());
        return "clinic/list";
    }

    @GetMapping("/new")
    public String newClinicForm(Model model) {
        model.addAttribute("clinic", new Clinic());
        return "clinic/form";
    }

    @GetMapping("/edit/{id}")
    public String editClinicForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("clinic", clinicRepository.findById(id).orElseThrow());
        return "clinic/form";
    }

    @PostMapping("/save")
    public String saveClinic(@ModelAttribute("clinic") @Valid Clinic clinic,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "clinic/form";
        }

        try {
            clinicRepository.save(clinic);
            return "redirect:/clinics";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("dbError", "Error al guardar en la base de datos.");
            return "clinic/form";
        } catch (NumberFormatException e) {
            model.addAttribute("dbError", "Formato numérico inválido.");
            return "clinic/form";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteClinic(@PathVariable("id") Integer id) {
        clinicRepository.deleteById(id);
        return "redirect:/clinics";
    }
}
