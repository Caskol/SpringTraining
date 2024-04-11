package org.caskol.warcraft_database.controllers.html;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.models.WarcraftClass;
import org.caskol.warcraft_database.services.WacraftClassService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/class")
public class WarcraftClassController {
    private final WacraftClassService wacraftClassService;
    @GetMapping()
    public String index(Model model)
    {
        model.addAttribute("pageTitle", "Список школ заклинаний");
        List<WarcraftClass> classes = wacraftClassService.getAll();
        model.addAttribute("objectsFromController", classes);
        return "showAllObjects";
    }
    @GetMapping("/add")
    public String addView(Model model){
        model.addAttribute("object", new WarcraftClass());
        model.addAttribute("method", "POST");
        model.addAttribute("action", "/class/add");
        return "objectChangeForm";
    }

    @PostMapping("/add")
    public String createClass(@ModelAttribute("object") @Valid WarcraftClass warcraftClass, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
            return "objectChangeForm";
        wacraftClassService.save(warcraftClass);
        return "redirect:/class";
    }

    @GetMapping("/change/{id}")
    public String changeView(@PathVariable("id") int id, Model model){
        model.addAttribute("object", wacraftClassService.getById(id));
        model.addAttribute("method", "PUT");
        model.addAttribute("action", "/class/change/"+id);
        return "objectChangeForm";
    }

    @PutMapping("/change/{id}")
    public String changeClass(@PathVariable("id") int id,@ModelAttribute("object") @Valid WarcraftClass warcraftClass, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
            return "objectChangeForm";
        warcraftClass.setId(id);
        wacraftClassService.save(warcraftClass);
        return "redirect:/class";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteClass(@PathVariable("id") int id){
        wacraftClassService.delete(id);
        return "redirect:/class";
    }

    @ModelAttribute("type")
    public String getType()
    {
        return WarcraftClass.class.getSimpleName();
    }
}
