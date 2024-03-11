package com.secondlife_ua.repositories;

import com.secondlife_ua.entities.Organization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrganizationRepository extends CrudRepository<Organization, Long> {

    @Query("select o from Organization o where upper(o.city) like upper(concat('%', ?1, '%'))")
    List<Organization> findByCity(String city);

    @Query("select o from Organization o where upper(o.address) like upper(concat('%', ?1, '%'))")
    List<Organization> findByAddress(String address);

    @Query("select o from Organization o where upper(o.name) like upper(concat('%', ?1, '%'))")
    List<Organization> findByName(String name);

    List<Organization> findByNameAndCityContainsIgnoreCase(String name, String city);
}