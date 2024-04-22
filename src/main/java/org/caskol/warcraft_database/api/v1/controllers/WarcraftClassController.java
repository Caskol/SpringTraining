package org.caskol.warcraft_database.api.v1.controllers;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.api.v1.dto.WarcraftClassDTO;
import org.caskol.warcraft_database.api.v1.services.WarcraftClassService;
import org.caskol.warcraft_database.utils.RestExceptionHandler;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/classes")
public class WarcraftClassController {
    private final WarcraftClassService warcraftClassService;
    @GetMapping
    public ResponseEntity<List<WarcraftClassDTO>> getStats(@RequestParam(value = "page", required = false) Integer page,
                                                  @RequestParam(value = "pagesize", required = false) Integer pageSize){
        if ((page==null || page<0) || (pageSize==null || pageSize<2)){
            page=0;
            pageSize=10000;
        }
        return ResponseEntity.ok(warcraftClassService.getAll(PageRequest.of(page,pageSize)));
    }
    @GetMapping("/{id}")
    public ResponseEntity<WarcraftClassDTO> getStatWithId(@PathVariable("id") int id){
        return ResponseEntity.ok(warcraftClassService.getById(id));
    }
    @PatchMapping("/{id}")
    public HttpStatus patchStat(@PathVariable("id") int id,@RequestBody WarcraftClassDTO warcraftClassDTO){
        warcraftClassDTO.setId(id);
        warcraftClassService.update(warcraftClassDTO);
        return HttpStatus.OK;
    }
    @PostMapping
    public ResponseEntity<WarcraftClassDTO> createStat(@RequestBody @Valid WarcraftClassDTO warcraftClassDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            throw new ValidationException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG+RestExceptionHandler.getBindingErrorString(bindingResult));
        return ResponseEntity.ok(warcraftClassService.create(warcraftClassDTO));
    }

    @PutMapping("/{id}")
    public HttpStatus putStat(@PathVariable("id") int id, @RequestBody @Valid WarcraftClassDTO warcraftClassDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            throw new ValidationException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG+RestExceptionHandler.getBindingErrorString(bindingResult));
        warcraftClassDTO.setId(id);
        warcraftClassService.update(warcraftClassDTO);
        return HttpStatus.OK;
    }
    @DeleteMapping("/{id}")
    public HttpStatus deleteStat(@PathVariable("id") int id){
        warcraftClassService.delete(id);
        return HttpStatus.OK;
    }
}
