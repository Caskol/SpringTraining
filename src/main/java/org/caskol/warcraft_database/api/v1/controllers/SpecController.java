package org.caskol.warcraft_database.api.v1.controllers;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.api.v1.dto.SpecDTO;
import org.caskol.warcraft_database.api.v1.services.SpecService;
import org.caskol.warcraft_database.utils.RestExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/spec")
public class SpecController {
    private final SpecService specService;
    @GetMapping
    public ResponseEntity<List<SpecDTO>> getAllSpecs(){
        return ResponseEntity.ok(specService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<SpecDTO> getSpecWithId(@PathVariable("id") int id){
        return ResponseEntity.ok(specService.getById(id));
    }
    @PatchMapping("/{id}")
    public HttpStatus patchSpec(@PathVariable("id") int id,@RequestBody SpecDTO specDTO){
        specDTO.setId(id);
        specService.update(specDTO);
        return HttpStatus.OK;
    }
    @PostMapping
    public ResponseEntity<SpecDTO> createSpec(@RequestBody @Valid SpecDTO specDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            throw new ValidationException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG+RestExceptionHandler.getBindingErrorString(bindingResult));
        return ResponseEntity.ok(specService.create(specDTO));
    }

    @PutMapping("/{id}")
    public HttpStatus putSpec(@PathVariable("id") int id, @RequestBody @Valid SpecDTO specDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            throw new ValidationException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG+RestExceptionHandler.getBindingErrorString(bindingResult));
        specDTO.setId(id);
        specService.update(specDTO);
        return HttpStatus.OK;
    }
    @DeleteMapping("/{id}")
    public HttpStatus deleteSpec(@PathVariable("id") int id){
        specService.delete(id);
        return HttpStatus.OK;
    }
}
