package org.caskol.warcraft_database.api.v1.controllers;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.api.v1.dto.IconDTO;
import org.caskol.warcraft_database.api.v1.mappers.IconMapper;
import org.caskol.warcraft_database.api.v1.services.IconService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/icon")
public class IconController {
    private final IconService iconService;
    private final IconMapper iconMapper;

    private static final String VALIDATION_EXCEPTION_MSG = "Поля объекта были неверно заполнены. Дополнительная информация: ";
    @GetMapping
    public List<IconDTO> getIcons() {
        return iconService.getAll();
    }

    @GetMapping("/{id}")
    public IconDTO getIconWithId(@PathVariable("id") int id) {
        return iconService.getById(id);
    }

    @PatchMapping("/change/{id}")
    public HttpStatus patchIcon(@PathVariable("id") int id, @RequestBody @Valid IconDTO iconDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new ValidationException(VALIDATION_EXCEPTION_MSG + RestExceptionHandler.getBindingErrors(bindingResult));
        }
        iconDTO.setId(id);
        iconService.update(iconDTO);
        return HttpStatus.OK;
    }
    @PostMapping("/add")
    public IconDTO createIcon(@RequestBody @Valid IconDTO iconDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            throw new ValidationException(VALIDATION_EXCEPTION_MSG + RestExceptionHandler.getBindingErrors(bindingResult));
        return iconService.create(iconDTO);
    }
    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteIcon(@PathVariable("id") int id){
        iconService.delete(id);
        return HttpStatus.OK;
    }


}
