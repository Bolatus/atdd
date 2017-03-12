package com.epam.jmp.bolat.atdd;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Bolat_Tussupzhanov on 3/12/2017.
 */
@Service
public class PersonStorage {
    private List<Person> people;
    private AtomicLong idCounter = new AtomicLong(0L);

    public PersonStorage() {
        this.people = new ArrayList<Person>();
    }

    public Long addPerson(Person p){
        p.setId(idCounter.incrementAndGet());
        people.add(p);
        return idCounter.get();
    }

    public List<Person> getPeople() {
        return people;
    }

    public Person getPersonById(Long personId){
        forea

    }
}
