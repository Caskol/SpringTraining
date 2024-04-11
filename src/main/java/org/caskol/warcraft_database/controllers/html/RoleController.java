package org.caskol.warcraft_database.controllers.html;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.models.Role;
import org.caskol.warcraft_database.services.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;
    @GetMapping()
    public String index(Model model)
    {
        model.addAttribute("pageTitle", "Список школ заклинаний");
        List<Role> roles = roleService.getAll();
        model.addAttribute("objectsFromController", roles);
        return "showAllObjects";
    }
    @GetMapping("/add")
    public String addView(Model model){
        model.addAttribute("object", new Role());
        model.addAttribute("method", "POST");
        model.addAttribute("action", "/role/add");
        return "objectChangeForm";
    }

    @PostMapping("/add")
    public String createStat(@ModelAttribute("object") @Valid Role role, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
            return "objectChangeForm";
        roleService.save(role);
        return "redirect:/role";
    }

    @GetMapping("/change/{id}")
    public String changeView(@PathVariable("id") int id, Model model){
        model.addAttribute("object", roleService.getById(id));
        model.addAttribute("method", "PUT");
        model.addAttribute("action", "/role/change/"+id);
        return "objectChangeForm";
    }

    @PutMapping("/change/{id}")
    public String changeRole(@PathVariable("id") int id,@ModelAttribute("object") @Valid Role role, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
            return "objectChangeForm";
        role.setId(id);
        roleService.save(role);
        return "redirect:/role";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRole(@PathVariable("id") int id){
        roleService.delete(id);
        return "redirect:/role";
    }

    @ModelAttribute("type")
    public String getType()
    {
        return Role.class.getSimpleName();
    }
}
