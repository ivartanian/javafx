package com.vartanian.javafx.adress.services.repository;

import com.vartanian.javafx.adress.model.Person;

import java.util.List;

/**
 * Created by vartanian on 26.11.2015.
 */
public interface PersonService {

    Person findPersonById(Long id);

    List<Person> getAllPersons();

    void savePerson(Person person);

    void deletePerson(Long id);


}
