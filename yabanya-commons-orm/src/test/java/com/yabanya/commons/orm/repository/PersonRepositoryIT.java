package com.yabanya.commons.orm.repository;

import com.yabanya.commons.orm.entity.RecordStatus;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public class PersonRepositoryIT extends AbstractRepositoryCrudIT {

    @Autowired
    private PersonRepository personRepository;

    @Test
    @Transactional
    public void create_createPerson_returnCreatedPerson() {

        final Person person = new Person();
        person.setName(generateRandomString());
        person.setSurname(generateRandomString());

        final Person savedPerson = personRepository.save(person);

        Assert.assertNotNull(savedPerson);
        Assert.assertNotNull(savedPerson.getId());
    }

    @Test
    @Transactional
    public void findPerson_findPersonById_foundPerson() {

        final Person person = new Person();
        person.setName(generateRandomString());
        person.setSurname(generateRandomString());

        final Person savedPerson = personRepository.save(person);

        Assert.assertNotNull(savedPerson);
        Assert.assertNotNull(savedPerson.getId());

        final Optional<Person> foundPerson = personRepository.findById(savedPerson.getId());

        Assert.assertTrue(foundPerson.isPresent());
        Assert.assertNotNull(foundPerson.get());
        Assert.assertNotNull(foundPerson.get().getId());
    }

    @Test
    @Transactional
    public void updatePerson_updateExistingPersonName() {

        final Person person = new Person();
        final String personName = generateRandomString();
        person.setName(personName);
        person.setSurname(generateRandomString());

        final Person savedPerson = personRepository.save(person);
        personRepository.flush();

        Assert.assertNotNull(savedPerson);
        Assert.assertNotNull(savedPerson.getId());

        final Person foundPerson = personRepository.findById(savedPerson.getId())
                .orElseThrow(() -> new RuntimeException("Person not found"));

        Assert.assertNotNull(foundPerson);
        Assert.assertNotNull(foundPerson.getId());

        // Update found person
        foundPerson.setName(generateRandomString());
        personRepository.flush();

        final Person updatedPerson = personRepository.findById(savedPerson.getId())
                .orElseThrow(() -> new RuntimeException("Person not found"));

        Assert.assertNotEquals(personName, updatedPerson.getName());

    }

    @Test
    @Transactional
    public void deletePerson_deleteExistingPersonByIdSoftly() {

        final Person person = new Person();
        final String personName = generateRandomString();
        person.setName(personName);
        person.setSurname(generateRandomString());

        final Person savedPerson = personRepository.save(person);
        personRepository.flush();

        Assert.assertNotNull(savedPerson);
        Assert.assertNotNull(savedPerson.getId());

        personRepository.deleteSoftly(savedPerson.getId());

        final Person foundPerson = personRepository.findById(savedPerson.getId())
                .orElseThrow(() -> new RuntimeException("Person not found"));

        Assert.assertNotNull(foundPerson);
        Assert.assertNotNull(foundPerson.getId());
        Assert.assertEquals(RecordStatus.DELETED, foundPerson.getRecordStatus());
    }

    private String generateRandomString() {
        return UUID.randomUUID().toString().substring(0, 19);
    }
}
