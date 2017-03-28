package com.epam.jmp.bolat.atdd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Bolat_Tussupzhanov on 3/12/2017.
 */

@RestController
public class PersonController {

    @Autowired
    private PersonStorage personStorage;

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public List<Person> getPeople() {
        return personStorage.getPeople();
    }

    @RequestMapping(value = "/person/{personId}", method = RequestMethod.GET)
    public ResponseEntity<Person> getPerson(@PathVariable(value = "personId") Long personId) {
        try {
            return ResponseEntity.ok(personStorage.getPersonById(personId));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public Long addPerson(@RequestBody(required = true) Person person) {
        return personStorage.addPerson(person);
    }

    @RequestMapping(value = "/person/{personId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletePerson(@PathVariable(value = "personId") Long personId) {
        try {
            personStorage.deletePerson(personId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/person/{personId}", method = RequestMethod.PUT)
    public ResponseEntity<String> updatePerson(@PathVariable(value = "personId") Long personId, @RequestBody Person personData) {
        try {
            personStorage.updatePerson(personId, personData);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
