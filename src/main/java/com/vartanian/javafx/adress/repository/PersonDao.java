package com.vartanian.javafx.adress.repository;

import com.vartanian.javafx.adress.model.Person;

import java.util.List;

/**
 * Created by vartanian on 26.11.2015.
 */
public interface PersonDao {

    Person findPersonById(Long id);

    List<Person> getAllPersons();

    void savePerson(Person person);

    void deletePerson(Person person);


}
