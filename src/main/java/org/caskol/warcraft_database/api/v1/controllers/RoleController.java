package org.caskol.warcraft_database.api.v1.controllers;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.api.v1.dto.RoleDTO;
import org.caskol.warcraft_database.api.v1.services.RoleService;
import org.caskol.warcraft_database.utils.RestExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/role")
public class RoleController {
    private final RoleService roleService;
    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles(){
        return ResponseEntity.ok(roleService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleWithId(@PathVariable("id") int id){
        return ResponseEntity.ok(roleService.getById(id));
    }
    @PatchMapping("/{id}")
    public HttpStatus patchRole(@PathVariable("id") int id,@RequestBody RoleDTO roleDTO){
        roleDTO.setId(id);
        roleService.update(roleDTO);
        return HttpStatus.OK;
    }
    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@RequestBody @Valid RoleDTO roleDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            throw new ValidationException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG+RestExceptionHandler.getBindingErrorString(bindingResult));
        return ResponseEntity.ok(roleService.create(roleDTO));
    }

    @PutMapping("/{id}")
    public HttpStatus putRole(@PathVariable("id") int id, @RequestBody @Valid RoleDTO roleDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            throw new ValidationException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG+RestExceptionHandler.getBindingErrorString(bindingResult));
        roleDTO.setId(id);
        roleService.update(roleDTO);
        return HttpStatus.OK;
    }
    @DeleteMapping("/{id}")
    public HttpStatus deleteRole(@PathVariable("id") int id){
        roleService.delete(id);
        return HttpStatus.OK;
    }
}
