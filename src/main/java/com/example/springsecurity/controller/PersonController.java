package com.example.springsecurity.controller;


import com.example.springsecurity.entity.BasePerson;
import com.example.springsecurity.entity.Persons;
import com.example.springsecurity.repository.PersonRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonController {
    private PersonRepository repo;
    public PersonController(PersonRepository repository){
        this.repo = repository;
    }

    @GetMapping("/by-city")
    public List<Persons> getPersonsByCity(@RequestParam("city_of_living") String city) {
        return repo.findPersonsByCityOfLiving(city);
    }

    @GetMapping("/by-age")
    public List<Persons> getPersonsByAge(@RequestParam("age") int age) {
        return repo.findPersonsByAgeAsc(age);
    }

    @GetMapping("/baseperson/by-age")
    public List<BasePerson> getBasePersonsByAge(@RequestParam("age") int age) {
        return repo.findBasePersonByAge(age);
    }

    @GetMapping("/phone/by-age")
    public List<String> getPhoneByAge(@RequestParam("age") int age) {
        return repo.findPhoneNumberByAge(age);
    }

    @GetMapping("/by-fio")
    public List<Optional<Persons>> getPersonsByNameAndSurname(@RequestParam("name") String name, @RequestParam("surname") String surname) {
        return repo.findPersonsByFio(name, surname);
    }
}
