package org.caskol.warcraft_database.api.v1.controllers;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.api.v1.dto.StatDTO;
import org.caskol.warcraft_database.api.v1.services.StatService;
import org.caskol.warcraft_database.utils.RestExceptionHandler;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stats")
public class StatController {
    private final StatService statService;
    @GetMapping
    public ResponseEntity<List<StatDTO>> getStats(@RequestParam(value = "page", required = false) Integer page,
                                                  @RequestParam(value = "pagesize", required = false) Integer pageSize){
        if ((page==null || page<0) || (pageSize==null || pageSize<2)){
            page=0;
            pageSize=10000;
        }
        return ResponseEntity.ok(statService.getAll(PageRequest.of(page,pageSize)));
    }
    @GetMapping("/{id}")
    public ResponseEntity<StatDTO> getStatWithId(@PathVariable("id") int id){
        return ResponseEntity.ok(statService.getById(id));
    }
    @PatchMapping("/{id}")
    public HttpStatus patchStat(@PathVariable("id") int id,@RequestBody StatDTO statDTO){
        statDTO.setId(id);
        statService.update(statDTO);
        return HttpStatus.OK;
    }
    @PostMapping
    public ResponseEntity<StatDTO> createStat(@RequestBody @Valid StatDTO statDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            throw new ValidationException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG+RestExceptionHandler.getBindingErrorString(bindingResult));
        return ResponseEntity.ok(statService.create(statDTO));
    }

    @PutMapping("/{id}")
    public HttpStatus putStat(@PathVariable("id") int id, @RequestBody @Valid StatDTO statDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            throw new ValidationException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG+RestExceptionHandler.getBindingErrorString(bindingResult));
        statDTO.setId(id);
        statService.update(statDTO);
        return HttpStatus.OK;
    }
    @DeleteMapping("/{id}")
    public HttpStatus deleteStat(@PathVariable("id") int id){
        statService.delete(id);
        return HttpStatus.OK;
    }
}
