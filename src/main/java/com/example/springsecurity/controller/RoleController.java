package com.example.springsecurity.controller;

import com.example.springsecurity.entity.BasePerson;
import com.example.springsecurity.entity.Persons;
import com.example.springsecurity.repository.PersonRepository;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class RoleController {
    private final PersonRepository repository;

    public RoleController(PersonRepository repository){
        this.repository = repository;
    }

    @GetMapping("/read-all")
    @Secured("ROLE_ADMIN")
    public List<Persons> readAll() {
        return repository.findAll();
    }

    @PostMapping("/save")
    @RolesAllowed("ROLE_WRITE")
    public ResponseEntity<Persons> savePersons(@RequestBody Persons person) {
        return ResponseEntity.ok(repository.save(person));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('ROLE_WRITE', 'ROLE_DELETE')")
    public ResponseEntity<BasePerson> deletePersons(@RequestBody BasePerson basePerson) {
        repository.deleteById(basePerson);
        return ResponseEntity.ok(basePerson);
    }

    @GetMapping("/user/{user}")
    @PreAuthorize("#username==authentication.principal.username")
    public String checkUser(@PathVariable("user") String username) {
        return "Hello from authorized user " + username;
    }
}
