package org.caskol.warcraft_database.api.v1.controllers;

import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.api.v1.dto.ClassResourceDTO;
import org.caskol.warcraft_database.api.v1.services.ClassResourceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/class")
public class ClassResourceController {
    private final ClassResourceService classResourceService;
    @GetMapping
    public List<ClassResourceDTO> getClassResources(){

        return classResourceService.getAll();
    }
}
