package com.secondlife_ua.repositories;

import com.secondlife_ua.entities.Organization;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrganizationRepositoryTest {
    @Autowired
    private OrganizationRepository organizationRepository;
    private List<Organization> organizationList = List.of(
            new Organization("Фонд Шевченка",
                    "вулиця Шевченка, 95",
                    51.50512993728873, 31.335320079463987,
                    "Чернігів, Чернігівська область","resources"),
            new Organization("Організація Марка",
                    "вулиця Івана Франка, 42",
                    50.45454266805665, 28.675722990482043,
                    "Черняхів, Житомирська область","resources"));

    @Test
    public void testCRUDOperations() {
        Iterable<Organization> organizations = organizationRepository.saveAll(organizationList);
        Organization testOrganization = organizations.iterator().next();
        assertNotNull(testOrganization.getId());

        Optional<Organization> optional = organizationRepository.findById(testOrganization.getId());
        Organization organization = optional.orElse(null);
        assertNotNull(organization);

        String newName = "Test";
        organization.setName(newName);
        Organization newOrganization = organizationRepository.save(organization);
        assertEquals(newName, newOrganization.getName());

        Long id = newOrganization.getId();
        organizationRepository.delete(newOrganization);
        optional = organizationRepository.findById(id);
        organization = optional.orElse(null);
        assertNull(organization);
    }

    @Test
    void findByCityLikeIgnoreCase() {
        organizationRepository.saveAll(organizationList);
        List<Organization> cityContains = organizationRepository.findByCity("черн");
        assertEquals(2, cityContains.size());
    }

    @Test
    void findByAddress() {
        organizationRepository.saveAll(organizationList);
        List<Organization> cityContains = organizationRepository.findByAddress("Шевченка");
        assertEquals(1, cityContains.size());
    }

    @Test
    void findByName() {
        organizationRepository.saveAll(organizationList);
        List<Organization> cityContains = organizationRepository.findByName("Організація");
        assertEquals(1, cityContains.size());
    }
}