package org.caskol.warcraft_database.api.v1.controllers;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.api.v1.dto.SpellSchoolDTO;
import org.caskol.warcraft_database.api.v1.services.SpellSchoolService;
import org.caskol.warcraft_database.utils.RestExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/spell_school")
public class SpellSchoolController {
    private final SpellSchoolService spellSchoolService;
    @GetMapping
    public ResponseEntity<List<SpellSchoolDTO>> getSpellSchools() {
        return ResponseEntity.ok(spellSchoolService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<SpellSchoolDTO> getSpellSchoolWithId(@PathVariable("id") int id) {
        return ResponseEntity.ok(spellSchoolService.getById(id));
    }
    @PatchMapping("/{id}")
    public HttpStatus patchSpellSchool(@PathVariable("id") int id, @RequestBody SpellSchoolDTO spellSchoolDTO){
        spellSchoolDTO.setId(id);
        spellSchoolService.update(spellSchoolDTO);
        return HttpStatus.OK;
    }
    @PutMapping("/{id}")
    public HttpStatus putSpellSchool(@PathVariable("id") int id, @RequestBody @Valid SpellSchoolDTO spellSchoolDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            throw new ValidationException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG + RestExceptionHandler.getBindingErrorString(bindingResult));
        spellSchoolDTO.setId(id);
        spellSchoolService.update(spellSchoolDTO);
        return HttpStatus.OK;
    }
    @PostMapping
    public ResponseEntity<SpellSchoolDTO> createSpellSchool(@RequestBody @Valid SpellSchoolDTO spellSchoolDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            throw new ValidationException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG + RestExceptionHandler.getBindingErrorString(bindingResult));
        return ResponseEntity.ok(spellSchoolService.create(spellSchoolDTO));
    }
    @DeleteMapping("/{id}")
    public HttpStatus deleteSpellSchool(@PathVariable("id") int id){
        spellSchoolService.delete(id);
        return HttpStatus.OK;
    }
}
