package com.example.healthdkg.repository;

import com.example.healthdkg.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Integer> {
}
