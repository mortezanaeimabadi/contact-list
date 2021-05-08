package com.kn.model.dto;

import com.kn.model.entity.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseDto {

    private  List<Person> people;
    private  Map<String, Integer> page;

    public ResponseDto(List<Person> people, Map<String, Integer> page) {
        this.people = new ArrayList<>(people);
        this.page = new HashMap<>(page);
    }

    public static ResponseDto create(List<Person> people,  Map<String, Integer> page) {
        return new ResponseDto(people, page);
    }

    public List<Person> getPeople() {
        return people;
    }

    public Map<String, Integer> getPage() {
        return page;
    }
}
