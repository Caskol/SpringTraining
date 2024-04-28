package org.caskol.warcraft_database.api.v1.controllers;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.api.v1.dto.ClassResourceDTO;
import org.caskol.warcraft_database.api.v1.services.ClassResourceService;
import org.caskol.warcraft_database.utils.RestExceptionHandler;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/class_resources")
public class ClassResourceController {
    private final ClassResourceService classResourceService;
    @GetMapping
    public ResponseEntity<List<ClassResourceDTO>> getClassResources(@RequestParam(value = "page", required = false) Integer page,
                                                                    @RequestParam(value = "pagesize", required = false) Integer pageSize) {
        if ((page==null || page<0) || (pageSize==null || pageSize<2)){
            page=0;
            pageSize=10000;
        }
        return ResponseEntity.ok(classResourceService.getAll(PageRequest.of(page,pageSize)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassResourceDTO> getClassResourceWithId(@PathVariable("id") int id) {
        return ResponseEntity.ok(classResourceService.getById(id));
    }

    @PatchMapping("/{id}")
    public HttpStatus patchClassResource(@PathVariable("id") int id, @RequestBody ClassResourceDTO classResourceDTO){
        classResourceDTO.setId(id);
        classResourceService.update(classResourceDTO);
        return HttpStatus.OK;
    }
    @PutMapping("/{id}")
    public HttpStatus putClassResource(@PathVariable("id") int id, @RequestBody @Valid ClassResourceDTO classResourceDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            throw new ValidationException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG + RestExceptionHandler.getBindingErrorString(bindingResult));
        classResourceDTO.setId(id);
        classResourceService.update(classResourceDTO);
        return HttpStatus.OK;
    }

    @PostMapping()
    public ResponseEntity<ClassResourceDTO> createClassResource(@RequestBody @Valid ClassResourceDTO classResourceDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            throw new ValidationException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG + RestExceptionHandler.getBindingErrorString(bindingResult));
        return ResponseEntity.ok(classResourceService.create(classResourceDTO));
    }
    @DeleteMapping("/{id}")
    public HttpStatus deleteClassResource(@PathVariable("id") int id){
        classResourceService.delete(id);
        return HttpStatus.OK;
    }
}
