package com.secondlife_ua.repositories;

import com.secondlife_ua.entities.Volunteer;
import org.springframework.data.repository.CrudRepository;

public interface VolunteerRepository extends CrudRepository<Volunteer, Long> {
    Volunteer findByEmail(String email);
}