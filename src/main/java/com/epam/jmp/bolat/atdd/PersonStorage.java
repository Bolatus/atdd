package com.epam.jmp.bolat.atdd;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Bolat_Tussupzhanov on 3/12/2017.
 */
@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PersonStorage {

    private List<Person> people;

    private AtomicLong idCounter = new AtomicLong(10L);

    public PersonStorage() {

        this.people = new ArrayList<Person>();
        this.people.add(new Person(1L, "Max"));
        this.people.add(new Person(2L, "John"));

    }

    public Long addPerson(Person p) {
        p.setId(idCounter.incrementAndGet());
        people.add(p);
        return idCounter.get();
    }

    public List<Person> getPeople() {
        return people;
    }

    public Person getPersonById(Long personId) throws Exception {
        Person person = null;
        for (Person p : people) {
            if (p.getId() == personId) {
                person = p;
                break;
            }
        }
        if (person == null)
            throw new Exception();
        return person;
    }

    public void deletePerson(Long personId) throws Exception {
        Person person = getPersonById(personId);
        if (person == null)
            throw new Exception();
        else
            people.remove(person);
    }

    public void updatePerson(Long personId, Person personData) throws Exception {
        Person person = getPersonById(personId);
        if (person == null)
            throw new Exception();
        else {
            person.setName(personData.getName());
        }
    }
}
