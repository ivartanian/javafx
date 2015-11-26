package com.vartanian.javafx.adress.repository;

import com.vartanian.javafx.adress.model.Person;

import javax.persistence.*;
import java.util.List;

/**
 * Created by vartanian on 26.11.2015.
 */
public class JpaPersonDaoImpl implements PersonDao {

    private EntityManagerFactory emf;
    private EntityManager em;

    public JpaPersonDaoImpl() {
        emf = Persistence.createEntityManagerFactory("javafx");
        em = emf.createEntityManager();
    }

    @Override
    public Person findPersonById(Long id) {
        TypedQuery<Person> query = this.em.createQuery("SELECT DISTINCT person FROM Person person WHERE person.id =:id", Person.class);
        query.setParameter("id", id);

        List<Person> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null; // handle no-results case
        } else {
            return resultList.get(0);
        }
    }

    @Override
    public List<Person> getAllPersons() {
        TypedQuery<Person> query = this.em.createQuery("SELECT DISTINCT person FROM Person person", Person.class);
        return query.getResultList();
    }

    @Override
    public void savePerson(Person person) {
        if (person == null){
            return;
        }
        em.getTransaction().begin();
        if (person.getId() == null) {
    		this.em.persist(person);
    	}
    	else {
    		this.em.merge(person);
    	}
        em.getTransaction().commit();
    }

    @Override
    public void deletePerson(Person person) {
        if (person == null){
            return;
        }
        em.getTransaction().begin();
        this.em.remove(person);
        em.getTransaction().commit();
    }
}
