package org.caskol.warcraft_database.api.v1.controllers;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.api.v1.dto.IconDTO;
import org.caskol.warcraft_database.api.v1.services.IconService;
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
@RequestMapping("/api/v1/icons")
public class IconController {
    private final IconService iconService;
    private final MessageSource messageSource;

    @GetMapping
    public ResponseEntity<List<IconDTO>> getIcons(@RequestParam(value = "page", required = false) Integer page,
                                                  @RequestParam(value = "pagesize", required = false) Integer pageSize) {
        if ((page==null || page<0) || (pageSize==null || pageSize<2)){
            page=0;
            pageSize=10000;
        }
        return ResponseEntity.ok(iconService.getAll(PageRequest.of(page,pageSize)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IconDTO> getIconWithId(@PathVariable("id") int id) {
        return ResponseEntity.ok(iconService.getById(id));
    }

    @PatchMapping("/{id}")
    public HttpStatus patchIcon(@PathVariable("id") int id, @RequestBody IconDTO iconDTO){
        iconDTO.setId(id);
        iconService.update(iconDTO);
        return HttpStatus.OK;
    }
    @PutMapping("/{id}")
    public HttpStatus putIcon(@PathVariable("id") int id, @RequestBody @Valid IconDTO iconDTO,
                              BindingResult bindingResult, Locale locale){
        if (bindingResult.hasErrors())
            throw new ValidationException(messageSource.getMessage("validation.received_invalid_data",null, locale) + RestExceptionHandler.getBindingErrorString(bindingResult));
        iconDTO.setId(id);
        iconService.update(iconDTO);
        return HttpStatus.OK;
    }

    @PostMapping()
    public ResponseEntity<IconDTO> createIcon(@RequestBody @Valid IconDTO iconDTO,
                                              BindingResult bindingResult, Locale locale){
        if (bindingResult.hasErrors())
            throw new ValidationException(messageSource.getMessage("validation.received_invalid_data",null, locale) + RestExceptionHandler.getBindingErrorString(bindingResult));
        return ResponseEntity.ok(iconService.create(iconDTO));
    }
    @DeleteMapping("/{id}")
    public HttpStatus deleteIcon(@PathVariable("id") int id){
        iconService.delete(id);
        return HttpStatus.OK;
    }


}
