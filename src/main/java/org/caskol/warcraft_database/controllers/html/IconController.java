package org.caskol.warcraft_database.controllers.html;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.models.Icon;
import org.caskol.warcraft_database.services.IconService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/icon")
public class IconController {
    private final IconService iconService;
    @GetMapping()
    public String index(Model model) {
        List<Icon> icons = iconService.getAll();
        model.addAttribute("pageTitle", "Icons");
        model.addAttribute("objectsFromController", icons);
        return "showAllObjects";
    }
    @GetMapping("/add")
    public String addView(Model model){
        model.addAttribute("object", new Icon());
        model.addAttribute("method", "POST");
        model.addAttribute("action", "/icon/add");
        return "objectChangeForm";
    }

    @PostMapping("/add")
    public String createIcon(@ModelAttribute("object") @Valid Icon icon, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
            return "objectChangeForm";
        iconService.save(icon);
        return "redirect:/icon";
    }

    @GetMapping("/change/{id}")
    public String changeView(@PathVariable("id") int id, Model model){
        model.addAttribute("object", iconService.getById(id));
        model.addAttribute("method", "PUT");
        model.addAttribute("action", "/icon/change/"+id);
        return "objectChangeForm";
    }

    @PutMapping("/change/{id}")
    public String changeIcon(@PathVariable("id") int id,@ModelAttribute("object") @Valid Icon icon, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
            return "objectChangeForm";
        icon.setId(id);
        iconService.save(icon);
        return "redirect:/icon";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteIcon(@PathVariable("id") int id){
        iconService.delete(id);
        return "redirect:/icon";
    }

    @ModelAttribute("type")
    public String typeOfObject(){
        return Icon.class.getSimpleName();
    }
}
