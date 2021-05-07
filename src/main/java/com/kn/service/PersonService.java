package com.kn.service;

import com.kn.model.entity.Person;
import org.springframework.data.domain.Page;

public interface PersonService {
    Page<Person> find(String filter, Integer pageNumber, Integer pageSize);
}
