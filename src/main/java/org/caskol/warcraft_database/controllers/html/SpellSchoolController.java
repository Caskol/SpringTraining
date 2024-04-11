package org.caskol.warcraft_database.controllers.html;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.models.SpellSchool;
import org.caskol.warcraft_database.services.SpellSchoolService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/spell_school")
public class SpellSchoolController {
    private final SpellSchoolService spellSchoolService;
    @GetMapping()
    public String index(Model model)
    {
        model.addAttribute("pageTitle", "Список школ заклинаний");
        List<SpellSchool> spellSchools = spellSchoolService.getAll();
        model.addAttribute("objectsFromController", spellSchools);
        return "showAllObjects";
    }
    @GetMapping("/add")
    public String addView(Model model){
        model.addAttribute("object", new SpellSchool());
        model.addAttribute("method", "POST");
        model.addAttribute("action", "/spell_school/add");
        return "objectChangeForm";
    }

    @PostMapping("/add")
    public String createSpellSchool(@ModelAttribute("object") @Valid SpellSchool spellSchool, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
            return "objectChangeForm";
        spellSchoolService.save(spellSchool);
        return "redirect:/spell_school";
    }

    @GetMapping("/change/{id}")
    public String changeView(@PathVariable("id") int id, Model model){
        model.addAttribute("object", spellSchoolService.getById(id));
        model.addAttribute("method", "PUT");
        model.addAttribute("action", "/spell_school/change/"+id);
        return "objectChangeForm";
    }

    @PutMapping("/change/{id}")
    public String changeSpellSchool(@PathVariable("id") int id,@ModelAttribute("object") @Valid SpellSchool spellSchool, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
            return "objectChangeForm";
        spellSchool.setId(id);
        spellSchoolService.save(spellSchool);
        return "redirect:/spell_school";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSpellSchool(@PathVariable("id") int id){
        spellSchoolService.delete(id);
        return "redirect:/spell_school";
    }

    @ModelAttribute("type")
    public String getType()
    {
        return SpellSchool.class.getSimpleName();
    }
}
