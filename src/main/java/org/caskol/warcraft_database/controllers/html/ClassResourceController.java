package org.caskol.warcraft_database.controllers.html;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.models.ClassResource;
import org.caskol.warcraft_database.services.ClassResourceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/class_resource")
public class ClassResourceController {
    private final ClassResourceService classResourceService;
    @GetMapping()
    public String index(Model model) {
        List<ClassResource> classResources = classResourceService.getAll();
        model.addAttribute("pageTitle", "Class Resources");
        model.addAttribute("objectsFromController", classResources);
        return "showAllObjects";
    }
    @GetMapping("/add")
    public String addView(Model model){
        model.addAttribute("object", new ClassResource());
        model.addAttribute("method", "POST");
        model.addAttribute("action", "/class_resource/add");
        return "objectChangeForm";
    }

    @PostMapping("/add")
    public String createClassResource(@ModelAttribute("object") @Valid ClassResource classResource, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
            return "objectChangeForm";
        classResourceService.save(classResource);
        return "redirect:/class_resource";
    }

    @GetMapping("/change/{id}")
    public String changeView(@PathVariable("id") int id, Model model){
        model.addAttribute("object", classResourceService.getById(id));
        model.addAttribute("method", "PUT");
        model.addAttribute("action", "/class_resource/change/"+id);
        return "objectChangeForm";
    }

    @PutMapping("/change/{id}")
    public String changeClassResource(@PathVariable("id") int id,@ModelAttribute("object") @Valid ClassResource classResource, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
            return "objectChangeForm";
        classResource.setId(id);
        classResourceService.save(classResource);
        return "redirect:/class_resource";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteClassResource(@PathVariable("id") int id){
        classResourceService.delete(id);
        return "redirect:/icon";
    }

    @ModelAttribute("type")
    public String typeOfObject(){
        return ClassResource.class.getSimpleName();
    }
}
