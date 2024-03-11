package com.secondlife_ua.repositories;

import com.secondlife_ua.entities.Category;
import com.secondlife_ua.entities.Item;
import com.secondlife_ua.entities.Organization;
import com.secondlife_ua.entities.Volunteer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Calendar;
import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {
    @Query("select i from Item i where upper(i.name) like upper(concat('%', ?1, '%'))")
    List<Item> findByName(String name);

    @Query("select i from Item i where upper(i.organization.city) like upper(concat('%', ?1, '%')) and upper(i.name) like upper(concat('%', ?2, '%'))")
    List<Item> findByCityAndName(String city, String name);

    List<Item> findByOrganization_City(String city);

    List<Item> findByOrganization(Organization organization);

    List<Item> findByOrganizationAndCategory(Organization organization, Category category);

    List<Item> findByCategory(Category category);

    @Query("select i from Item i where i.category = ?1 and upper(i.organization.city) like upper(concat('%', ?2, '%'))")
    List<Item> findByCategoryAndCity(Category category, String city);

    @Query("select i from Item i where i.organization = ?1 and upper(i.name) like upper(concat('%', ?2, '%'))")
    List<Item> findByOrganizationAndName(Organization organization, String name);

    List<Item> findByVolunteer(Volunteer volunteer);

    long deleteByAdditionDateLessThan(Calendar additionDate);
}