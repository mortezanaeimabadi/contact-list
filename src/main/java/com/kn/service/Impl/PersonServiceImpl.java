package com.kn.service.Impl;

import com.kn.exception.BadRequestException;
import com.kn.model.entity.Person;
import com.kn.repository.PersonRepository;
import com.kn.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository repository;

    @Override
    public Page<Person> find(String nameFilter, Integer pageNumber, Integer pageSize) {

        validateInputs(pageNumber,pageSize);

        Pageable pageable = Pageable.unpaged();

        if(pageNumber != null && pageSize != null) {
            pageable = PageRequest.of(pageNumber, pageSize);
        }

        return repository.findByNameContainingIgnoreCase(nameFilter,pageable);
    }

    private void validateInputs(Integer pageNumber, Integer pageSize) {
        if(pageNumber!=null && pageSize==null)
            throw new BadRequestException("page size must be provided as well");
        if(pageNumber==null && pageSize!=null)
            throw new BadRequestException("page number must be provided as well");
    }
}
