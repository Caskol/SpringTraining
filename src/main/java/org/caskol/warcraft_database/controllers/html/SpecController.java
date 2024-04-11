package org.caskol.warcraft_database.controllers.html;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.models.Spec;
import org.caskol.warcraft_database.services.SpecService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/spec")
public class SpecController {
    private final SpecService specService;
    @GetMapping()
    public String index(Model model)
    {
        model.addAttribute("pageTitle", "Список школ заклинаний");
        List<Spec> specs = specService.getAll();
        model.addAttribute("objectsFromController", specs);
        return "showAllObjects";
    }
    @GetMapping("/add")
    public String addView(Model model){
        model.addAttribute("object", new Spec());
        model.addAttribute("method", "POST");
        model.addAttribute("action", "/specs/add");
        return "objectChangeForm";
    }

    @PostMapping("/add")
    public String createSpec(@ModelAttribute("object") @Valid Spec spec, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
            return "objectChangeForm";
        specService.save(spec);
        return "redirect:/spec";
    }

    @GetMapping("/change/{id}")
    public String changeView(@PathVariable("id") int id, Model model){
        model.addAttribute("object", specService.getById(id));
        model.addAttribute("method", "PUT");
        model.addAttribute("action", "/spec/change/"+id);
        return "objectChangeForm";
    }

    @PutMapping("/change/{id}")
    public String changeSpec(@PathVariable("id") int id,@ModelAttribute("object") @Valid Spec spec, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
            return "objectChangeForm";
        spec.setId(id);
        specService.save(spec);
        return "redirect:/class";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSpec(@PathVariable("id") int id){
        specService.delete(id);
        return "redirect:/spec";
    }

    @ModelAttribute("type")
    public String getType()
    {
        return Spec.class.getSimpleName();
    }
}
