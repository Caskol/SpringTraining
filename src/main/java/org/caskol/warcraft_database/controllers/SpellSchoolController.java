package org.caskol.warcraft_database.controllers;


import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.models.SpellSchool;
import org.caskol.warcraft_database.services.SpellSchoolService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/spell_schools")
public class SpellSchoolController {
    private final SpellSchoolService spellSchoolService;
    @GetMapping()
    public String index(Model model)
    {
        model.addAttribute("pageTitle", "Список школ заклинаний");
        List<SpellSchool> spellSchools = spellSchoolService.getAll();
        model.addAttribute("objectsFromController", spellSchools);
        return "index";
    }
}
