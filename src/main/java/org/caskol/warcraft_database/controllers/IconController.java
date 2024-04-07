package org.caskol.warcraft_database.controllers;

import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.repositories.IconRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/icon")
public class IconController {
    private final IconRepository iconRepository;
    @GetMapping()
    public String index()
    {
        return null;
    }
}
