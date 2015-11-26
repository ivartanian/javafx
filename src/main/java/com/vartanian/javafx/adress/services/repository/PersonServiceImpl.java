package com.vartanian.javafx.adress.services.repository;

import com.vartanian.javafx.adress.model.Person;
import com.vartanian.javafx.adress.repository.JpaPersonDaoImpl;
import com.vartanian.javafx.adress.repository.PersonDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by vartanian on 26.11.2015.
 */
public class PersonServiceImpl implements PersonService {

    private PersonDao personDao;

    public PersonServiceImpl() {
        personDao = new JpaPersonDaoImpl();
    }

    @Override
    public Person findPersonById(Long id) {
        return personDao.findPersonById(id);
    }

    @Override
    public List<Person> getAllPersons() {
        return personDao.getAllPersons();
    }

    @Override
    public void savePerson(Person person) {
        personDao.savePerson(person);
    }

    @Override
    public void deletePerson(Long id) {
        Person person = personDao.findPersonById(id);
        if (person == null){
            return;
        }
        personDao.deletePerson(person);
    }
}
