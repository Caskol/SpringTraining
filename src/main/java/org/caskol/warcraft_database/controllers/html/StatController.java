package org.caskol.warcraft_database.controllers.html;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.models.Stat;
import org.caskol.warcraft_database.services.StatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/stat")
public class StatController {
    private final StatService statService;
    @GetMapping()
    public String index(Model model)
    {
        model.addAttribute("pageTitle", "Список школ заклинаний");
        List<Stat> stats = statService.getAll();
        model.addAttribute("objectsFromController", stats);
        return "showAllObjects";
    }
    @GetMapping("/add")
    public String addView(Model model){
        model.addAttribute("object", new Stat());
        model.addAttribute("method", "POST");
        model.addAttribute("action", "/stat/add");
        return "objectChangeForm";
    }

    @PostMapping("/add")
    public String createStat(@ModelAttribute("object") @Valid Stat stat, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
            return "objectChangeForm";
        statService.save(stat);
        return "redirect:/stat";
    }

    @GetMapping("/change/{id}")
    public String changeView(@PathVariable("id") int id, Model model){
        model.addAttribute("object", statService.getById(id));
        model.addAttribute("method", "PUT");
        model.addAttribute("action", "/stat/change/"+id);
        return "objectChangeForm";
    }

    @PutMapping("/change/{id}")
    public String changeStat(@PathVariable("id") int id,@ModelAttribute("object") @Valid Stat stat, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
            return "objectChangeForm";
        stat.setId(id);
        statService.save(stat);
        return "redirect:/stat";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStat(@PathVariable("id") int id){
        statService.delete(id);
        return "redirect:/stat";
    }

    @ModelAttribute("type")
    public String getType()
    {
        return Stat.class.getSimpleName();
    }
}
