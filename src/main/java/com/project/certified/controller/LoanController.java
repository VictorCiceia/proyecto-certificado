package com.project.certified.controller;

import com.project.certified.dto.LoanDto;
import com.project.certified.services.LoanService;
import com.project.certified.services.LoanServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {

    private LoanService loanService;

    @Autowired
    public LoanController(LoanServiceFactory authServiceFactory) {
        this.loanService = authServiceFactory.getService();
    }

    @GetMapping("{id}")
    public ResponseEntity<LoanDto> findById(@PathVariable("id") final String id) {
        return ResponseEntity.ok(this.loanService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<LoanDto>> findAll() {
        return ResponseEntity.ok(this.loanService.findAll());
    }

    @PostMapping
    public ResponseEntity<LoanDto> save(@RequestBody final LoanDto loanDto) {
        return ResponseEntity.ok().body(this.loanService.save(loanDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<LoanDto> update(@PathVariable("id") final String id, @RequestBody final LoanDto loanDto) {
        return ResponseEntity.ok(this.loanService.update(loanDto, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") final String id) {
        this.loanService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
