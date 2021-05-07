package com.kn.repository;

import com.kn.model.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Integer> {
    Page<Person> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
