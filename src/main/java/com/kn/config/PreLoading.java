package com.kn.config;

import com.kn.model.entity.Person;
import com.kn.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Slf4j
@Configuration
public class PreLoading implements ApplicationRunner {

    @Autowired
    PersonRepository repository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        parseCSVFile();

    }

    private void parseCSVFile() throws Exception {
        try {
            Resource resource = new ClassPathResource("people.csv");
            InputStream inputStream = resource.getInputStream();
            final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            br.readLine();
            while ((line=br.readLine()) != null) {
                final String[] data=line.split(",");
                Person person=new Person();
                person.setName(data[0]);
                if(data[1].contains("http"))
                    person.setPhotoUrl(data[1]);
                else {
                    person.setName(data[0]+" "+data[1]);
                    person.setPhotoUrl(data[2]);
                }
                repository.save(person);
            }
        } catch(final Exception e) {
            log.error("Failed to parse CSV file {}", e);
            throw e;
        }
    }
}
