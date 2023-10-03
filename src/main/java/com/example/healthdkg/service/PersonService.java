package com.example.healthdkg.service;

import com.example.healthdkg.model.Person;
import com.example.healthdkg.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public List<Person> createPeople (List<Person> people){
        return personRepository.saveAll(people);
    }

    public Person createPerson (Person person){
        return personRepository.save(person);
    }
}
