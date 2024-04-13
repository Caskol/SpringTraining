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
    public ResponseEntity<List<SpellSchoolDTO>> getIcons() {
        return ResponseEntity.ok(spellSchoolService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpellSchoolDTO> getIconWithId(@PathVariable("id") int id) {
        return ResponseEntity.ok(spellSchoolService.getById(id));
    }

    @PatchMapping("/change/{id}")
    public HttpStatus patchIcon(@PathVariable("id") int id, @RequestBody SpellSchoolDTO spellSchoolDTO){
        spellSchoolDTO.setId(id);
        spellSchoolService.update(spellSchoolDTO);
        return HttpStatus.OK;
    }
    @PutMapping("/change/{id}")
    public HttpStatus putIcon(@PathVariable("id") int id, @RequestBody @Valid SpellSchoolDTO spellSchoolDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            throw new ValidationException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG + RestExceptionHandler.getBindingErrorString(bindingResult));
        spellSchoolDTO.setId(id);
        spellSchoolService.update(spellSchoolDTO);
        return HttpStatus.OK;
    }

    @PostMapping("/add")
    public ResponseEntity<SpellSchoolDTO> createIcon(@RequestBody @Valid SpellSchoolDTO spellSchoolDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            throw new ValidationException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG + RestExceptionHandler.getBindingErrorString(bindingResult));
        return ResponseEntity.ok(spellSchoolService.create(spellSchoolDTO));
    }
    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteIcon(@PathVariable("id") int id){
        spellSchoolService.delete(id);
        return HttpStatus.OK;
    }


}
