package org.caskol.warcraft_database.controllers;

import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.repositories.ClassResourceRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/class_resource")
public class ClassResourceController {
    private final ClassResourceRepository classResourceRepository;
    @GetMapping()
    public String index()
    {
        return null;
    }
}
