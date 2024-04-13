package org.caskol.warcraft_database.api.v1.controllers;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.api.v1.dto.ClassResourceDTO;
import org.caskol.warcraft_database.api.v1.services.ClassResourceService;
import org.caskol.warcraft_database.utils.RestExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/class_resource")
public class ClassResourceController {
    private final ClassResourceService classResourceService;
    @GetMapping
    public ResponseEntity<List<ClassResourceDTO>> getClassResources() {
        return ResponseEntity.ok(classResourceService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassResourceDTO> getClassResourceWithId(@PathVariable("id") int id) {
        return ResponseEntity.ok(classResourceService.getById(id));
    }

    @PatchMapping("/change/{id}")
    public HttpStatus patchClassResource(@PathVariable("id") int id, @RequestBody ClassResourceDTO classResourceDTO){
        classResourceDTO.setId(id);
        classResourceService.update(classResourceDTO);
        return HttpStatus.OK;
    }
    @PutMapping("/change/{id}")
    public HttpStatus putClassResource(@PathVariable("id") int id, @RequestBody @Valid ClassResourceDTO classResourceDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            throw new ValidationException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG + RestExceptionHandler.getBindingErrorString(bindingResult));
        classResourceDTO.setId(id);
        classResourceService.update(classResourceDTO);
        return HttpStatus.OK;
    }

    @PostMapping("/add")
    public ResponseEntity<ClassResourceDTO> createClassResource(@RequestBody @Valid ClassResourceDTO classResourceDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            throw new ValidationException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG + RestExceptionHandler.getBindingErrorString(bindingResult));
        return ResponseEntity.ok(classResourceService.create(classResourceDTO));
    }
    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteClassResource(@PathVariable("id") int id){
        classResourceService.delete(id);
        return HttpStatus.OK;
    }
}
