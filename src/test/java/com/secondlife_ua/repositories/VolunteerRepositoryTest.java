package com.secondlife_ua.repositories;

import com.secondlife_ua.entities.Volunteer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class VolunteerRepositoryTest {
    @Autowired
    private VolunteerRepository volunteerRepository;
    private Volunteer testVolunteer = new Volunteer("Oleh", "Khromov", Volunteer.Sex.MALE,
            "holeg133@gmail.com",
            "1111",
            "0984211915");

    @Test
    public void testCRUDOperations() {
        testVolunteer = volunteerRepository.save(testVolunteer);
        assertNotNull(testVolunteer.getId());

        Optional<Volunteer> optional = volunteerRepository.findById(testVolunteer.getId());
        Volunteer volunteer = optional.orElse(null);
        assertNotNull(volunteer);

        String newName = "Test";
        volunteer.setName(newName);
        Volunteer newVolunteer = volunteerRepository.save(volunteer);
        assertEquals(newName, newVolunteer.getName());

        Long id = newVolunteer.getId();
        volunteerRepository.delete(newVolunteer);
        optional = volunteerRepository.findById(id);
        volunteer = optional.orElse(null);
        assertNull(volunteer);
    }

    @Test
    public void testFindUserByEmail() {
        testVolunteer = volunteerRepository.save(testVolunteer);
        Volunteer volunteer = volunteerRepository.findByEmail("holeg133@gmail.com");
        assertEquals(testVolunteer, volunteer);
    }
}