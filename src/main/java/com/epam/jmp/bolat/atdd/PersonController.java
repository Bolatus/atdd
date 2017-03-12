package com.epam.jmp.bolat.atdd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Bolat_Tussupzhanov on 3/12/2017.
 */

@RestController
public class PersonController {

    @Autowired
    private PersonStorage personStorage;

    @RequestMapping("/person/{personId}")
    public Person getPerson(@PathVariable(value="personId") String personId) {
        return new Person(0L,String.format(template, name));
    }

    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public Long addPerson(@RequestBody Person person){
        return personStorage.addPerson(person);
    }
}
