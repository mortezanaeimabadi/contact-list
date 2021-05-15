package com.kn.controller;

import com.kn.model.dto.ResponseDto;
import com.kn.model.entity.Person;
import com.kn.service.PersonService;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/v1/person")
public class PersonController {


    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping("/find")
    public Page<Person> find(
            @RequestParam(defaultValue="") String name,
            @RequestParam Optional<Integer> pageNumber,
            @RequestParam Optional<Integer> pageSize) {

        return personService.find(name, pageNumber.orElse(null), pageSize.orElse(null));
    }

    @GetMapping("/view")
    public ModelAndView index(
            @RequestParam(defaultValue="") String name,
            @RequestParam Optional<Integer> pageNumber,
            @RequestParam Optional<Integer> pageSize,
            Model model) {

        Page<Person> people = personService.find(name, pageNumber.orElse(0), pageSize.orElse(50));
        model.addAttribute("responseEntity", createResponseDto(people, pageNumber.orElse(0)));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");
        return modelAndView;
    }

    private ResponseDto createResponseDto(Page<Person> personPage, Integer pageNumber) {
        Map<String, Integer> page = new HashMap<>();
        page.put("currentPage", pageNumber);
        page.put("totalPages", personPage.getTotalPages());
        page.put("totalElements", (int) personPage.getTotalElements());
        return ResponseDto.create(personPage.getContent(),page);
    }

}
