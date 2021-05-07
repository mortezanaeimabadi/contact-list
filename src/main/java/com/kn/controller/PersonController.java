package com.kn.controller;

import com.kn.model.entity.Person;
import com.kn.service.PersonService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v1/person")
public class PersonController {


    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public Page<Person> find(
            @RequestParam(defaultValue="") String name,
            @RequestParam Optional<Integer> pageNumber,
            @RequestParam Optional<Integer> pageSize) {

        return personService.find(name, pageNumber.orElse(null), pageSize.orElse(null));
    }

}
