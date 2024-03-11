package com.secondlife_ua.repositories;

import com.secondlife_ua.entities.Item;
import com.secondlife_ua.entities.Message;
import com.secondlife_ua.entities.Organization;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findByItem(Item item);

    @Query("select m from Message m where m.organization = ?1 order by m.creationDate ")
    List<Message> findByOrganizationSortedByDate(Organization organization);

}