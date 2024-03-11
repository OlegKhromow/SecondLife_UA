package com.secondlife_ua.repositories;

import com.secondlife_ua.entities.Item;
import com.secondlife_ua.entities.Organization;
import com.secondlife_ua.entities.Photo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PhotoRepository extends CrudRepository<Photo, Long> {
    List<Photo> findByItem(Item item);

    List<Photo> findByOrganization(Organization organization);
}