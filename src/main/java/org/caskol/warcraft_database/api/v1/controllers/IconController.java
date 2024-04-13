package org.caskol.warcraft_database.api.v1.controllers;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.api.v1.dto.IconDTO;
import org.caskol.warcraft_database.api.v1.mappers.IconMapper;
import org.caskol.warcraft_database.api.v1.services.IconService;
import org.caskol.warcraft_database.utils.RestExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/icon")
public class IconController {
    private final IconService iconService;
    private final IconMapper iconMapper;


    @GetMapping
    public ResponseEntity<List<IconDTO>> getIcons() {
        return ResponseEntity.ok(iconService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IconDTO> getIconWithId(@PathVariable("id") int id) {
        return ResponseEntity.ok(iconService.getById(id));
    }

    @PatchMapping("/change/{id}")
    public HttpStatus patchIcon(@PathVariable("id") int id, @RequestBody IconDTO iconDTO){
        iconDTO.setId(id);
        iconService.update(iconDTO);
        return HttpStatus.OK;
    }
    @PutMapping("/change/{id}")
    public HttpStatus putIcon(@PathVariable("id") int id, @RequestBody @Valid IconDTO iconDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            throw new ValidationException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG + RestExceptionHandler.getBindingErrorString(bindingResult));
        iconDTO.setId(id);
        iconService.update(iconDTO);
        return HttpStatus.OK;
    }

    @PostMapping("/add")
    public ResponseEntity<IconDTO> createIcon(@RequestBody @Valid IconDTO iconDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            throw new ValidationException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG + RestExceptionHandler.getBindingErrorString(bindingResult));
        return ResponseEntity.ok(iconService.create(iconDTO));
    }
    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteIcon(@PathVariable("id") int id){
        iconService.delete(id);
        return HttpStatus.OK;
    }


}
