package org.caskol.warcraft_database.api.v1.controllers;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.api.v1.dto.RoleDTO;
import org.caskol.warcraft_database.api.v1.services.RoleService;
import org.caskol.warcraft_database.utils.RestExceptionHandler;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
public class RoleController {
    private final RoleService roleService;
    private final MessageSource messageSource;
    @GetMapping
    public ResponseEntity<List<RoleDTO>> getRoles(@RequestParam(value = "page", required = false) Integer page,
                                                     @RequestParam(value = "pagesize", required = false) Integer pageSize){
        if ((page==null || page<0) || (pageSize==null || pageSize<2)){
            page=0;
            pageSize=10000;
        }
        return ResponseEntity.ok(roleService.getAll(PageRequest.of(page,pageSize)));
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
    public ResponseEntity<RoleDTO> createRole(@RequestBody @Valid RoleDTO roleDTO,
                                              BindingResult bindingResult, Locale locale){
        if (bindingResult.hasErrors())
            throw new ValidationException(messageSource.getMessage("validation.received_invalid_data",null, locale)+RestExceptionHandler.getBindingErrorString(bindingResult));
        return ResponseEntity.ok(roleService.create(roleDTO));
    }

    @PutMapping("/{id}")
    public HttpStatus putRole(@PathVariable("id") int id, @RequestBody @Valid RoleDTO roleDTO,
                              BindingResult bindingResult, Locale locale){
        if (bindingResult.hasErrors())
            throw new ValidationException(messageSource.getMessage("validation.received_invalid_data",null, locale)+RestExceptionHandler.getBindingErrorString(bindingResult));
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
